/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.log4j.Logger;

/**
 *
 * @author loinv3
 */
public class VNPTUtilies {

    private class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
    /**
     * LOGGER instance
     */
    private static final Logger LOGGER = Logger.getLogger(VNPTUtilies.class);

    /**
     * REST TEMPLATE instance is a thread-safe
     */
    //private RestTemplate restTemplate;
    private RestTemplate restTemplate;

    private SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

    /**
     * private constructor
     */
    public VNPTUtilies() {
        try {
            //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.112.7", 3443));
            //factory.setProxy(proxy);
            factory.setReadTimeout(4800000);
            factory.setConnectTimeout(4800000);
            restTemplate = new RestTemplate(factory);
            ObjectMapper _mapper = new ObjectMapper();
            _mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            _mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setObjectMapper(_mapper);

            for (int i = 0; i < restTemplate.getMessageConverters().size(); i++) {
                final HttpMessageConverter<?> httpMessageConverter = restTemplate.getMessageConverters().get(i);
                if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                    restTemplate.getMessageConverters().set(i, converter);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
        }
    }

    /**
     * Get instance of RestTemplate -- Ham dung duong link public internet
     *
     * @return RestTemplate
     */
    public RestTemplate getRestTemplate009() {
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(null, new TrustManager[]{new DefaultTrustManager()}, null);
            SSLContext.setDefault(ctx);
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
        }
        return restTemplate;
    }

    /**
     * get instance of RestTemplate
     *
     * @return RestTemplate
     */
    public RestTemplate getRestTemplate() {

        try {
            
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(null, new TrustManager[]{new DefaultTrustManager()}, null);
            //ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            SSLContext.setDefault(ctx);
            //HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
            
            
            /*
            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            */
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
        }
        return restTemplate;
    }
    
}
