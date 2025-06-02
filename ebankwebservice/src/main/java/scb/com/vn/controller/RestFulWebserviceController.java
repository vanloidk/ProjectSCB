/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Random;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author minhndb
 */
public class RestFulWebserviceController {
    private static final Logger LOGGER = Logger.getLogger(RestFulWebserviceController.class);

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

    public String postRequest(String url, String request) {
        String result = "";
        if (url.contains(":")) {
            String protocol = url.substring(0, url.indexOf(":"));
            switch (protocol) {
                case "http":
                    result = postRequestHttp(url, request);
                    break;
                case "https":
                    result = postRequestHttps(url, request);
                    break;
                default:
                    LOGGER.warn("Invalid Types of Network Protocols [" + url + "]");
                    break;
            }
        } else {
            LOGGER.warn("Invalid URL [" + url + "]");
        }
        return result;
    }

    public String getRequest(String url) {
        String result = "";
        if (url.contains(":")) {
            String protocol = url.substring(0, url.indexOf(":"));
            switch (protocol) {
                case "http":
                    result = getRequestHttp(url);
                    break;
                case "https":
                    result = getRequestHttps(url);
                    break;
                default:
                    LOGGER.warn("Invalid URL [" + url + "]");
                    break;
            }
        } else {
            LOGGER.warn("Invalid URL [" + url + "]");
        }
        return result;
    }

    public String getRequestHttps(String url) {
        StringBuilder response = new StringBuilder();
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            SSLContext.setDefault(ctx);

            URL urlForGetRequest = new URL(url);
            String readLine;
            HttpsURLConnection conection = (HttpsURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            conection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
            /* set user, password
            * conection.setRequestProperty("userId", "minhndb");
             */
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()))) {
                    while ((readLine = in.readLine()) != null) {
                        response.append(readLine);
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            } else {
                LOGGER.warn("GET NOT WORKED. HttpURLConnection = [" + responseCode + "]");
                /* Co gang ghi duoc gi thi ghi. Cai nay ko khuyen khich vi se quang exception */
                if (conection.getInputStream() != null) {
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()))) {
                        while ((readLine = in.readLine()) != null) {
                            response.append(readLine);
                        }
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.warn("Cannot execute get method. Ex: " + e);
        }
        return response.toString();
    }

    public String getRequestHttp(String url) {
        StringBuilder response = new StringBuilder();
        try {
            URL urlForGetRequest = new URL(url);
            String readLine;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            /* set user, password
            * conection.setRequestProperty("userId", "minhndb");
             */
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()))) {
                    while ((readLine = in.readLine()) != null) {
                        response.append(readLine);
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            } else {
                LOGGER.warn("GET NOT WORKED");
            }
        } catch (Exception e) {
            LOGGER.warn("Cannot execute get method. Ex: " + e);
        }
        return response.toString();
    }

    public String postRequestHttp(String url, String request) {
        StringBuilder response = new StringBuilder();
        /* Khoi tao id de ghi log cho de phan biet response nao cua request nao */
        int id = new Random().nextInt(99999999);
        try {
            URL obj = new URL(url);
            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setDoOutput(true);
            LOGGER.info("ID = [" + id + "] - Request: " + request);
            try (OutputStream os = postConnection.getOutputStream()) {
                os.write(request.getBytes());
                os.flush();
            } catch (Exception e) {
                LOGGER.error("ID = [" + id + "] - Ex: " + e);
            }
            int responseCode = postConnection.getResponseCode();
            LOGGER.info("ID = [" + id + "] - POST Response Code :  " + responseCode);
            LOGGER.info("ID = [" + id + "] - POST Response Message : " + postConnection.getResponseMessage());
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            } else {
                LOGGER.warn("ID =[" + id + "] - POST NOT WORKED");
            }
        } catch (Exception e) {
            LOGGER.error("ID = [" + id + "] - Ex: " + e);
        }
        return response.toString();
    }

    public String postRequestHttps(String url, String request) {
        StringBuilder response = new StringBuilder();
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            SSLContext.setDefault(ctx);

            URL obj = new URL(url);
            HttpsURLConnection postConnection = (HttpsURLConnection) obj.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            /* Thuc hien skip validate host name */
            postConnection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
            /* set user, password
            * postConnection.setRequestProperty("userId", "minhndb");
            * postConnection.setRequestProperty("Content-Type", "application/json");
             */

            postConnection.setDoOutput(true);
            try (OutputStream os = postConnection.getOutputStream()) {
                os.write(request.getBytes());
                os.flush();
            } catch (Exception e) {
                LOGGER.error(e);
            }
            int responseCode = postConnection.getResponseCode();
            LOGGER.info("POST Response Code :  " + responseCode);
            LOGGER.info("POST Response Message : " + postConnection.getResponseMessage());
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            } else {
                LOGGER.warn("POST NOT WORKED");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return response.toString();
    }
}
