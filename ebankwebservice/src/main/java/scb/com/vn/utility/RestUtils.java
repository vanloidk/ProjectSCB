/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.log4j.Logger;

/**
 *
 * @author loinv3
 */
public class RestUtils {

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
    private static final Logger LOGGER = Logger.getLogger(RestUtils.class);

    /**
     * REST TEMPLATE instance is a thread-safe
     */
    private RestTemplate restTemplate;
    public SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

    /**
     * private constructor
     */
    public RestUtils() {
        try {
            factory.setReadTimeout(35000);
            factory.setConnectTimeout(35000);
            restTemplate = new RestTemplate(factory);
            ObjectMapper _mapper = new ObjectMapper();
            _mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
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
     * get instance of RestTemplate
     *
     * @return RestTemplate
     */
    public RestTemplate getRestTemplate00() {

        try {
            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            System.setProperty("jsse.enableSNIExtension", "false");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1");
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            //SSLContext.setDefault(ctx);
            HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
        }
        return restTemplate;
    }

    public RestTemplate getRestTemplate() {
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(null, new TrustManager[]{new DefaultTrustManager()}, null);
            SSLContext.setDefault(ctx);
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
        }
        return restTemplate;
    }

}
