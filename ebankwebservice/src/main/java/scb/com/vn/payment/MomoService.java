/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import java.io.ByteArrayOutputStream;
import java.security.KeyManagementException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.PGPHelper;

/**
 *
 * @author lydty
 */
public class MomoService {

    String urlPre = Configuration.getProperty("partner.vallet.url.MOMO");
    String momoCode = Configuration.getProperty("partner.vallet.code.MOMO");
    String MOMO_PRIVATE_KEY_PATH = Configuration.getProperty("partner.vallet.privatekey.path.MOMO");
    String MOMO_PUBLIC_KEY_PATH = Configuration.getProperty("partner.vallet.publickey.path.MOMO");
    String password = Configuration.getProperty("partner.vallet.privatekey.pass.MOMO");
    int timeout = Integer.valueOf(Configuration.getProperty("partner.vallet.timeout"));

    /**
     *
     * @param inputJson
     * @param apiname
     * @return
     */
    public String callMOMOService(String inputJson, String apiname) {
        Helper.writeLogErrorNonDB(MomoService.class, "input string for CallRegister: " + inputJson);
        try {

            Helper.writeLogErrorNonDB(MomoService.class, "Khoi tao tham so pgp");
            PGPHelper.init(MOMO_PRIVATE_KEY_PATH, MOMO_PUBLIC_KEY_PATH, password);
            Helper.writeLogErrorNonDB(MomoService.class, "Khoi tao xong tham so pgp");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PGPHelper.getInstance().encryptAndSign(inputJson.getBytes(), out);
            Helper.writeLogErrorNonDB(MomoService.class, "Encripting xong.");
            String encryptData = out.toString();
            out.close();

            //Step 3: Build a httpClient to send request
            //  HttpHost proxy = new HttpHost("192.168.59.36",3128);
            //  RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout * 1000).build();
            //  HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
            
            HttpPost post = new HttpPost(urlPre + apiname);
            post.addHeader("Content-Type", "application/pgp-encrypted");
            post.addHeader("Accept", "application/pgp-encrypted");
            post.addHeader("partner-code", momoCode);

            //  RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(timeout * 1000)
            //      .build();
            HttpHost proxy = new HttpHost("192.168.112.36",8992);
            RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
                    .build();
            post.setConfig(config);

            StringEntity entity = new StringEntity(encryptData, ContentType.TEXT_PLAIN);
            post.setEntity(entity);
            HttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpClient httpClient1 = getCloseableHttpClient();
            HttpResponse response = httpClient1.execute(post);
            String output = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("Data Response: " + output);
            //Read data Output

            ByteArrayOutputStream outStrResponse = new ByteArrayOutputStream();
            PGPHelper.getInstance().decryptAndVerifySignature(output.getBytes(), outStrResponse);
            String decryptData = outStrResponse.toString();
            Helper.writeLogErrorNonDB(MomoService.class, "output string for CallRegister: " + decryptData);
            return decryptData;

        } catch (Exception ex) {
            Helper.writeLogErrorNonDB(MomoService.class, "MOMO WS: " + ex.getMessage());
            return null;
        }
    }
    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException
            {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
