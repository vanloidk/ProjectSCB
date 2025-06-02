/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.service;

import java.net.HttpURLConnection;
import java.net.URL;
import vn.com.scb.bian.RetrieveCurrentAccountCASA_out_Type;
import vn.com.scb.bian.TransactionInfoType;
import vn.com.scb.bian.utility.Helper;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.scb.bian.utility.IIBContext;

/**
 *
 * @author kimncm
 */
public class IIBService {

    
    public HttpURLConnection getHttpURLConnection(IIBContext iibContext,String resourcetype) {
        try {
            URL url = new URL(iibContext.getServiceEndpoint().concat(resourcetype));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(iibContext.getTimeout());
            String loginPassword = iibContext.getClientId() + ":" + iibContext.getClientPassword();
            String encoded = new sun.misc.BASE64Encoder().encode(loginPassword.getBytes());
            conn.setRequestProperty("Authorization", "Basic " + encoded);
            return conn;
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "getHttpURLConnection - Exception:" + iibContext);
            Helper.writeLogError(this.getClass(), "getHttpURLConnection - Exception:" + e.toString());
            return null;
        }
    }
    public HttpURLConnection getHttpURLConnectionBilling(IIBContext iibContext,String resourcetype) {
        try {
            URL url = new URL(iibContext.getBillingServiceEndpoint().concat(resourcetype));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(iibContext.getTimeout());
            String loginPassword = iibContext.getClientId() + ":" + iibContext.getClientPassword();
            String encoded = new sun.misc.BASE64Encoder().encode(loginPassword.getBytes());
            conn.setRequestProperty("Authorization", "Basic " + encoded);
            return conn;
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "getHttpURLConnection - Exception:" + iibContext);
            Helper.writeLogError(this.getClass(), "getHttpURLConnection - Exception:" + e.toString());
            return null;
        }
    }
}
