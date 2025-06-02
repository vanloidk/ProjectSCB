/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visa.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import javax.net.ssl.HostnameVerifier;
import javax.security.auth.login.Configuration;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;

/**
 *
 * @author Visa
 * @modifier Nguyen Dac Binh Minh
 */
public class VisaAPIClient
{    
    final static Logger logger = Logger.getLogger(VisaAPIClient.class);
    
    private static CloseableHttpClient mutualAuthHttpClient;
    private static CloseableHttpClient XPayHttpClient;
    
    private CloseableHttpClient fetchXPayHttpClient()
    {    
        XPayHttpClient = HttpClients.createDefault();
        return XPayHttpClient;
    }
    
    private CloseableHttpClient fetchMutualAuthHttpClient() throws KeyManagementException, UnrecoverableKeyException,
    NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException
    {
        if (mutualAuthHttpClient == null)
        {
            HttpClientBuilder httpClient = HttpClients.custom().setSSLSocketFactory(getSSLSocketFactory());
            /*httpClient = HttpClients.custom().
                    setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).
                    setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
                    {
                        public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
                        {
                            return true;
                        }
                    }).build());*/
            String proxyHost = VisaProperties.getProperty(Property.HOST);
            String proxyPort = VisaProperties.getProperty(Property.PORT);
            if (proxyHost != null && !proxyHost.isEmpty() && proxyPort != null && !proxyPort.isEmpty())
            {
                HttpHost proxy = new HttpHost(VisaProperties.getProperty(Property.HOST)
                    , Integer.parseInt(VisaProperties.getProperty(Property.PORT)));
                httpClient.setProxy(proxy);
            }
            mutualAuthHttpClient = httpClient.build();
        }
        return mutualAuthHttpClient;
    }
    
    private SSLContext loadClientCertificate() throws KeyManagementException, UnrecoverableKeyException,
    NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException
    {
        SSLContext sslcontext = SSLContexts.custom()
                        .loadKeyMaterial(new File(VisaProperties.getProperty(Property.KEYSTORE_PATH)),
                                        VisaProperties.getProperty(Property.KEYSTORE_PASSWORD).toCharArray(),
                                        VisaProperties.getProperty(Property.PRIVATE_KEY_PASSWORD).toCharArray())        
                .build();
        return sslcontext;
    }
    
    private SSLConnectionSocketFactory getSSLSocketFactory() throws KeyManagementException, UnrecoverableKeyException,
    NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException
    {
        SSLContext sslContext = loadClientCertificate();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext
                , new String[] { "TLSv1.2","TLSv1.1","TLSv1" }
                        
                , null /*new String[]{"TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256",
                "TLS_RSA_WITH_AES_128_CBC_SHA256",
                
                "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
                "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
                "TLS_RSA_WITH_AES_256_CBC_SHA",
                "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA",
                "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA",
                "TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
                "TLS_DHE_DSS_WITH_AES_256_CBC_SHA",
                
                "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
                "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
                "TLS_RSA_WITH_AES_128_CBC_SHA",
                "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",
                "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
                "TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
                "TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
                "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA",
                "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA",
                "SSL_RSA_WITH_3DES_EDE_CBC_SHA",
                "TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA",
                "TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA",
                "SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA",
                "SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA",
                "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
                "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
                "SSL_RSA_WITH_RC4_128_SHA",
                "TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
                "TLS_ECDH_RSA_WITH_RC4_128_SHA",
                "SSL_RSA_WITH_RC4_128_MD5",
                "TLS_EMPTY_RENEGOTIATION_INFO_SCSV"}*/
                , hostnameVerifier);
        return sslSocketFactory;
    }
    
    private HttpRequest createHttpRequest(MethodTypes methodType, String url) throws Exception
    {
    	HttpRequest request = null;
    	switch (methodType)
        {
            case GET:
                    request = new HttpGet(url);
                break;
            case POST:
                    request = new HttpPost(url);
                break;
            case PUT:
                    request = new HttpPut(url);
                break;
            case DELETE:
                    request = new HttpDelete(url);
                break;
            default:
                    logger.error("Incompatible HTTP request method " + methodType);
                break;
    	}
    	return request;
   }
    
    public String doMutualAuthRequest(String request, String testInfo, MessageType messageType) throws Exception
    {   
        String resourcePath = Utils.buildResourcePath(messageType);
        if (resourcePath.isEmpty())
        {
            return "800";
        }
        
        CloseableHttpResponse response = doMutualAuthRequest(resourcePath, testInfo, request, MethodTypes.POST, new HashMap<String, String>());
        
        String result = Utils.getResponseMessage(response);
        logger.info("RESPONSE = [" + result + "]");
        response.close();
        
        return result;
    }
    
    public CloseableHttpResponse doMutualAuthRequest(String path, String testInfo, String body, MethodTypes methodType, Map<String, String> headers) 
                    throws Exception
    {
        try
        {
            String url = VisaProperties.getProperty(Property.END_POINT) + path;
            logRequestBody(body);
            HttpRequest request = createHttpRequest(methodType, url);
            request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            request.setHeader(HttpHeaders.ACCEPT, "application/json");
            request.setHeader(HttpHeaders.AUTHORIZATION, BasicAuthHeaderGenerator.getBasicAuthHeader());
            request.setHeader("ex-correlation-id", getCorrelationId());
            request.setHeader("keyId", VisaProperties.getProperty(Property.KEY_MLE_ID));
//        logger.info("Header : " + Arrays.toString(request.getAllHeaders()));
        if (headers != null && headers.isEmpty() == false)
        {
                for (Entry<String, String> header : headers.entrySet())
                {
                    request.setHeader(header.getKey(), header.getValue());
                }
            }

            if (request instanceof HttpPost)
            {
                ((HttpPost) request).setEntity(new StringEntity(body, "UTF-8"));
                
            }
            else
                if (request instanceof HttpPut)
                {
                    ((HttpPut) request).setEntity(new StringEntity(body, "UTF-8"));
                   
                }
            //CloseableHttpClient httpClient = getCloseableHttpClient();
          
           // CloseableHttpResponse response = httpClient.execute((HttpUriRequest) request);
             CloseableHttpResponse response = fetchMutualAuthHttpClient().execute((HttpUriRequest) request);
            return response;
        }
        catch(Exception ex)
        {
            logger.error(ex);
            throw ex;
        }
    }

    public CloseableHttpResponse doXPayTokenRequest(String baseUri, String resourcePath, String queryParams, String testInfo, String body, MethodTypes methodType, Map<String, String> headers)
                    throws Exception
    {
        try
        {
            String url = VisaProperties.getProperty(Property.END_POINT) + baseUri + resourcePath + "?" + queryParams;
            logRequestBody(body);

            String apikey = VisaProperties.getProperty(Property.API_KEY);

            HttpRequest request = createHttpRequest(methodType, url);
            request.setHeader("content-type", "application/json");
            String xPayToken = XPayTokenGenerator.generateXpaytoken(resourcePath, "apikey=" + apikey, body);
            request.setHeader("x-pay-token", xPayToken);
            request.setHeader("ex-correlation-id", getCorrelationId());

            if (headers != null && headers.isEmpty() == false)
            {
                for (Entry<String, String> header : headers.entrySet())
                {
                    request.setHeader(header.getKey(), header.getValue());
                }
            }

            if (request instanceof HttpPost)
            {
                ((HttpPost) request).setEntity(new StringEntity(body, "UTF-8"));
                
            }
            else
                if (request instanceof HttpPut)
                {
                    ((HttpPut) request).setEntity(new StringEntity(body, "UTF-8"));
                    
                }
            //CloseableHttpClient httpClient = getCloseableHttpClient();
            //CloseableHttpResponse response = httpClient.execute((HttpUriRequest) request);
            CloseableHttpResponse response = fetchXPayHttpClient().execute((HttpUriRequest) request);
            logResponse(response);
            return response;
        }
        catch(Exception ex)
        {
            logger.error(ex);
            throw ex;
        }
        
    }
    
    private static void logResponse(CloseableHttpResponse response) throws IOException
    {
        Header[] h = response.getAllHeaders();
        
        // Get the response json object
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null)
        {    
            result.append(line);
        }
        
        // Print the response details
        HttpEntity entity = response.getEntity();
        logger.info("Response status : " + response.getStatusLine() + "\n");
        
        logger.info("Response Headers: \n");
        
        for (Header h1 : h)
        {
            logger.info(h1.getName() + ":" + h1.getValue());
        }
        logger.info("\n Response Body:");
        
        if(!StringUtils.isEmpty(result.toString()))
        {
            ObjectMapper mapper = getObjectMapperInstance();
            Object tree;
            try
            {
                tree = mapper.readValue(result.toString(), Object.class);
                logger.info("ResponseBody: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree));
            }
            catch (JsonProcessingException e)
            {
                logger.error(e.getMessage());
            }
            catch (IOException e)
            {
                logger.error(e.getMessage());
            }
        }
        
        EntityUtils.consume(entity);
    }
    
    private static void logRequestBody(String payload)
    {
        ObjectMapper mapper = getObjectMapperInstance();
        Object tree;
        if(!StringUtils.isEmpty(payload))
        {
            try
            {
                tree = mapper.readValue(payload,Object.class);
                logger.info("RequestBody: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree));
            }
            catch (JsonProcessingException e)
            {
                logger.error(e.getMessage());
            }
            catch (IOException e)
            {
                logger.error(e.getMessage());
            }
        }
    }
    
    /**
     * Get Correlation Id for the API Call.
     * @return correlation Id  
     */
    private String getCorrelationId()
    {
        //Passing correlation Id header is optional while making an API call. 
        return RandomStringUtils.random(10, true, true) + "_SC";
    }
    /**
     * Get New Instance of ObjectMapper
     * @return
     */
    protected static ObjectMapper getObjectMapperInstance()
    {    
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true); // format json
        return mapper;
    }
     public static CloseableHttpClient getCloseableHttpClient()
    {
        CloseableHttpClient httpClient = null;
        try {
            
            httpClient = HttpClients.custom().
                    setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).
                    setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
                    {
                        public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
                        {
                            return true;
                        }
                    }).build()).build();
        } catch (Exception e) {
            System.out.println("KeyManagementException in creating http client instance" +e.getMessage());
        }     return httpClient;
    }
}