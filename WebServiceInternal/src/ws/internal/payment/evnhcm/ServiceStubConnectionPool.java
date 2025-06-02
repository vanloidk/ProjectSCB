/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.internal.payment.evnhcm;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.Stub;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.log4j.Logger;
 
/**
 * <p><code>Service Stub Connection Pool</code> is used to pool
 * Axis 2 client service stubs. It provides functionality for
 * setting the service stubs to run with a multi-threaded
 * connection manager and for closing idle socket connections.</p>
 *
 * @author minhndb
 */
public class ServiceStubConnectionPool {
 
    /**
     * Creates a new instance of ServiceStubConnectionPool
     */
    private ServiceStubConnectionPool() {
        super();
    }
 
    // The logger for this class.
    private final static Logger logger = Logger.getLogger(ServiceStubConnectionPool.class);
 
    // The axis2 client configuration context - only one of these should be around 
    // for your whole application. This is staticly created on demand.
    private static ConfigurationContext context = null;
 
    // Http connection management - these two member variables are needed 
    // per remote service.
    private MultiThreadedHttpConnectionManager httpConnectionManager = null;
    private HttpClient httpClient = null;
 
    // Setup the multi-threaded connection manager. This is only done once 
    // per target service.
    private void setupHttpConnectionManager(String host, int port, String scheme) {
        if (httpConnectionManager == null) {
            logger.info("setupHttpConnectionManager:" + scheme + " " + host + " " + port);
            httpConnectionManager = new MultiThreadedHttpConnectionManager();
            HttpConnectionManagerParams params = new HttpConnectionManagerParams();
            HostConfiguration hostConfiguration = new HostConfiguration();
            hostConfiguration.setHost(host, port, scheme);
            params.setMaxTotalConnections(100);
            params.setMaxConnectionsPerHost(hostConfiguration, 25);
            httpConnectionManager.setParams(params);
            httpClient = new HttpClient(httpConnectionManager);
        }
    }
 
    // Configure the connection for the client stub to use.
    private void configureConnection(Stub client, String serviceURL) {
        logger.info("configureConnection: " + serviceURL);
        if (httpConnectionManager == null) {
            // Parse the hostname, protocol and port for the target service.
            String host = "";
            int port = 80;
            int portIndex = -1;
            String scheme = serviceURL.substring(0, serviceURL.indexOf( ":"));
            if (scheme.equalsIgnoreCase( "https")) {
                port = 443;
            }
            for (int i=scheme.length()+3; i<serviceURL.length(); i++) {
                if (serviceURL.charAt( i) == ':') {
                    portIndex = i + 1;
                }
                else if (serviceURL.charAt( i) == '/') {
                    if (portIndex > -1) {
                        port = Integer.parseInt(serviceURL.substring( portIndex, i));
                        host = serviceURL.substring(scheme.length()+3, portIndex - 1);
                    }
                    else {
                        host = serviceURL.substring(scheme.length()+3, i);
                    }
                    break;
                }
            }
            logger.info("Configuring connection manager for URL [" +
                serviceURL + "]: scheme = [" + scheme + "] host = [" + host +
                "] port = [" + port + "]");
            setupHttpConnectionManager(host, port, scheme);
        }
        Options options = client._getServiceClient().getOptions();
        options.setProperty( HTTPConstants.REUSE_HTTP_CLIENT, Constants.VALUE_TRUE);
        options.setProperty( HTTPConstants.CACHED_HTTP_CLIENT, httpClient);
    }
 
    // Get the axis 2 context.
    private static synchronized ConfigurationContext getContext(URL axis2confURL, 
        URL repositoryURL) throws AxisFault {
        if (context == null) {
            context = ConfigurationContextFactory.createConfigurationContextFromURIs(
                    axis2confURL, repositoryURL);
        }
        return context;
    }
 
    // Map of ServiceStubConnectionPool instances. Keyed on url.
    private final static Map<String, ServiceStubConnectionPool> factories =
        new HashMap<String, ServiceStubConnectionPool>();
 
    // Get a factory by url. Create one if it doesn't exist. These are required per
    // remote URL.
    public static synchronized final ServiceStubConnectionPool getInstance(String url) {
        ServiceStubConnectionPool connPool = factories.get(url);
        if (connPool == null) {
            connPool = new ServiceStubConnectionPool();
            factories.put(url, connPool);
        }
        return connPool;
    }
 
    // The client stub pool - one of these is required per connection pool.
    private final List<Stub> servicePool = new ArrayList();
 
    /**
     * Borrow a service stub from the pool. This creates stubs as
     * they are required. The stub MUST be returned to the pool after it has
     * been used (in a finally block if possible!)
     * @param axis2confURL the url location to the Axis 2 client configuration file
     * @param repositoryURL the url location to the Axis 2 repository (modules,conf etc)
     * @param serviceURL the http URL of the user authentication service
     * @param username
     * @param password
     * @return the stub
     * @throws org.apache.axis2.AxisFault
     */
    public synchronized Stub borrowService(
        String serviceURL, URL axis2confURL, URL repositoryURL,
        String username, String password) throws AxisFault {
        logger.debug("Method call: borrowService [repositoryURL = " + repositoryURL + 
                ", axis2confURL = " + axis2confURL + 
                ", servicePool = " + servicePool.size() + "]");
        Stub result;
        if (servicePool.size() > 0) {
            result = servicePool.remove(0);
        }
        else {
            // ******** NOTE: change the following line to use your own ServiceStub class
            result = new SBCServiceStub(getContext(axis2confURL, repositoryURL), serviceURL);
            // Configure connection management to support a multiple threaded, reusable
            // http connection client.
            configureConnection(result, serviceURL);
        }
        // Configure your outflow security (rampart/WS-Security) here!
        if (!username.isEmpty() && !password.isEmpty())
        {
            //configureOutflowSecurity(username, password, result);
        }

        logger.debug("Method exit: borrowService [" + result + "]");
        return result;
    }
 
    /**
    * Return a stub to the pool. Call this after you have made your client call.
    * @param service the service stub
    */
    public synchronized void returnService(SBCServiceStub service) {
        logger.debug("Method call: returnService [service = " +
                service + "]");
        // Clean up idle connections.
        if (service != null)
        {
            httpConnectionManager.closeIdleConnections(20000);
            servicePool.add(service);
        }
        logger.debug("Method exit: returnService");
    }
}