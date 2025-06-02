/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Level;
import scb.com.vn.payment.napas.utility.RSAAlgorithm;
import scb.com.vn.ultility.RsaUtils;
import scb.com.vn.ultility.rsa.EncodingType;
import scb.com.vn.ultility.rsa.SignatureKeyStoreMode;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.Configuration;
/**
 *
 * @author lydty
 */
public class QRPartnerService {
     public Object[] callService(String jsonString, String apiname, String partnerId) throws IOException
     {
         if(partnerId.equals("PAYOOQR"))
         {
             if(apiname.equals("CHECK"))  
             {
                 apiname="v3/check-order";
             }
             else
             if(apiname.equals("PAYMENT"))
             {
                 apiname="v3/notify-transaction";
             }
             else
             if(apiname.equals("GETORDER"))
             {
                 apiname="v3/get-order";
             }
             return callPayooService(jsonString,apiname);
         }
         return null;
     }
     
    public Object[] callPayooService(String jsonString, String apiname) throws UnsupportedEncodingException, IOException
    {
       Helper.writeLog(QRPartnerService.class, Level.INFO, "REQUEST["+apiname+"]:"+jsonString);
        //System.out.println("REQUEST["+apiname+"]:"+jsonString);
        int isCallService =0;
        Object[] result =new Object[2];
        try
        {
            String _wsbeanendpointaddress=Configuration.getProperty("payooqr.path");
            String credential=Configuration.getProperty("payooqr.credential");
            String username=Configuration.getProperty("payooqr.username");
            Date date = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddHHmmss");
            String requestTime = dt.format(date);
            String _keyPath=Configuration.getProperty("SCBPrivatekey.path");
            PrivateKey prvKey = RsaUtils.getPrivateKey(_keyPath,SignatureKeyStoreMode.STRINGFILE,EncodingType.HEX);
            String signature = RsaUtils.makeSignatureWithUtf8(jsonString, prvKey, RSAAlgorithm.SHA256RSA, EncodingType.BASE64);

            HttpPost httpPost = new HttpPost(_wsbeanendpointaddress+apiname);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("userName", username);
            httpPost.addHeader("credential", CommonUtils.getSHA512(credential));
            httpPost.addHeader("requestTime", requestTime);
            httpPost.addHeader("signature", signature);
            
            
            HttpHost proxy = new HttpHost("192.168.84.35", 9090 );
            RequestConfig config = RequestConfig.custom().setConnectTimeout(30 * 1000).setProxy(proxy)
                    .build();
            
            httpPost.setConfig(config);
            
            
            StringEntity entity = new StringEntity(jsonString,"UTF-8");
            httpPost.setEntity(entity);
            
            CloseableHttpClient httpClient = getCloseableHttpClient();
            isCallService =1;
            HttpResponse response = httpClient.execute(httpPost);
            
            int statusCode= response.getStatusLine().getStatusCode();
            Helper.writeLog(QRPartnerService.class, Level.INFO,"statusCode:"+statusCode);
            result[0] = statusCode;
            if(statusCode == 200)
            {
                String jsonOutPut = EntityUtils.toString(response.getEntity(), "UTF-8");
                result[1] = jsonOutPut;
                Helper.writeLog(QRPartnerService.class, Level.INFO,"RESPONSE["+apiname+"]:"+jsonOutPut);
            }
            else
            {
                Helper.writeLog(QRPartnerService.class, Level.ERROR,"Loi call seviceserpayoo- hold tien voi giao dich payment");
                result[0]=99;
            }
            
        }
        catch(Exception ex)
        {
            if(isCallService==1)
            {
                //System.out.println(ex.getMessage());
                Helper.writeLog(QRPartnerService.class, Level.ERROR,"LOI CALL PAYOO:"+ex.getMessage());
                result[0]=99;
            }
            else
            {
                Helper.writeLog(QRPartnerService.class, Level.ERROR,"error system:"+ex.getMessage());
                result[0]=-99;
            }
        }
        return result;
    }
    public static CloseableHttpClient getCloseableHttpClient()
    {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.custom().
                    setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).
                    setSSLContext(new org.apache.http.ssl.SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
                    {
                        public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
                        {
                            return true;
                        }
                    }).build()).build();
        } catch (Exception e) {
            System.out.println("KeyManagementException in creating http client instance" +e.getMessage());
        }   
        return httpClient;
    }
    
    
   
}
