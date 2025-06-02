/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.service.bill;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import org.apache.log4j.Logger;
import vn.com.scb.bian.model.evnhcm.PayBillEVNHCM_in;
import vn.com.scb.bian.model.evnhcm.PayBillEVNHCM_out;
import vn.com.scb.bian.model.evnhcm.SelectBillEVNHCMInfo_in;
import vn.com.scb.bian.model.evnhcm.SelectBillEVNHCMInfo_out;
import vn.com.scb.bian.service.IIBService;
import vn.com.scb.bian.utility.BianException;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.scb.bian.utility.IIBContext;

/**
 *
 * @author minhndb
 */
public class IIBEVNHCMService extends IIBService {
    
    private static final Logger LOGGER = Logger.getLogger(IIBEVNHCMService.class);

    private static final String RESOURCE_SELECT_BILL = "/billevnhcm/v1.0/rest/getBillInfoEVNHCM";
    private static final String RESOURCE_PAY_BILL = "/billevnhcm/v1.0/rest/payBillEVNHCM";
    
    public SelectBillEVNHCMInfo_out selectBillEVNHCMInfo(IIBContext iibContext, SelectBillEVNHCMInfo_in request) throws BianException {
        HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_SELECT_BILL);
        Gson gson = new Gson();
        String inputJson = gson.toJson(request);

        try {
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                LOGGER.warn("selectBillEVNHCMInfo: " + inputJson);
                LOGGER.warn("selectBillEVNHCMInfo: Failed : HTTP error code : " + conn.getResponseCode());
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                output = output.append(line);
            }
            conn.disconnect();

            SelectBillEVNHCMInfo_out response = gson.fromJson(output.toString(), SelectBillEVNHCMInfo_out.class);

            if (response != null) {
                if ("0".equals(response.getTransactionReturn())) {
                    LOGGER.info("selectBillEVNHCMInfo_in: " + inputJson);
                    LOGGER.info("selectBillEVNHCMInfo_out: " + output);
                    LOGGER.info("selectBillEVNHCMInfo_out: " + response.getTransactionReturn());
                    LOGGER.info("selectBillEVNHCMInfo_out: " + response.getTransactionReturnMsg());
                }
                return response;
            } else {
                LOGGER.warn("selectBillEVNHCMInfo_in: " + inputJson);
                LOGGER.warn("selectBillEVNHCMInfo_out is null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectBillEVNHCMInfo_out is null");
            }
        } catch (Exception e) {
            LOGGER.error("selectBillEVNHCMInfo - Exception: " + inputJson);
            LOGGER.error("selectBillEVNHCMInfo - Exception: " + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectBillEVNHCMInfo - Exception: " + e.toString());
        }
    }
    
    public PayBillEVNHCM_out payBillEVNHCM(IIBContext iibContext, PayBillEVNHCM_in request) throws BianException {
        HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_PAY_BILL);
        Gson gson = new Gson();
        String inputJson = gson.toJson(request);
        
        try {
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                LOGGER.warn("payBillEVNHCM: " + inputJson);
                LOGGER.warn("payBillEVNHCM: Failed : HTTP error code : " + conn.getResponseCode());
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                output = output.append(line);
            }
            conn.disconnect();
            
            PayBillEVNHCM_out response = gson.fromJson(output.toString(), PayBillEVNHCM_out.class);
            
            if (response != null) {
                if ("1".equals(response.getTransactionReturn())) {
                    LOGGER.info("PayBillEVNHCM_in: " + inputJson);
                    LOGGER.info("PayBillEVNHCM_out: " + output);
                    LOGGER.info("PayBillEVNHCM_out: " + response.getTransactionReturn());
                    LOGGER.info("PayBillEVNHCM_out: " + response.getTransactionReturnMsg());
                }
                return response;
            } else {
                LOGGER.warn("PayBillEVNHCM_in: " + inputJson);
                LOGGER.warn("PayBillEVNHCM_out is null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "PayBillEVNHCM_out is null");
            }
        } catch (Exception e) {
            LOGGER.error("payBillEVNHCM - Exception: " + inputJson);
            LOGGER.error("payBillEVNHCM - Exception: " + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "payBillEVNHCM - Exception: " + e.toString());
        }
    }
    
    
    
    
    
    
    
    
    
}
