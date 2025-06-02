/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.service.bill;

import org.apache.log4j.Logger;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import vn.com.scb.bian.model.evnhn.PayBillEVNHN_in;
import vn.com.scb.bian.model.evnhn.PayBillEVNHN_out;
import vn.com.scb.bian.model.evnhn.SelectBillEVNHNInfo_in;
import vn.com.scb.bian.model.evnhn.SelectBillEVNHNInfo_out;
import vn.com.scb.bian.service.IIBService;
import vn.com.scb.bian.utility.BianException;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.scb.bian.utility.IIBContext;

/**
 *
 * @author minhndb
 */
public class IIBEVNHNService extends IIBService {

    private static final Logger logger = Logger.getLogger(IIBEVNHNService.class);

    private static final String RESOURCE_SELECT_BILL = "/billevnhn/v1.0/rest/getBillInfoEVNHN";
    private static final String RESOURCE_PAY_BILL = "/billevnhn/v1.0/rest/payBillEVNHN";

    public SelectBillEVNHNInfo_out selectBillEVNHNInfo(IIBContext iibContext, SelectBillEVNHNInfo_in request) throws BianException {
        HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_SELECT_BILL);
        Gson gson = new Gson();
        String inputJson = gson.toJson(request);

        try {
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                logger.warn("selectBillEVNHNInfo: " + inputJson);
                logger.warn("selectBillEVNHNInfo: Failed : HTTP error code : " + conn.getResponseCode());
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

            SelectBillEVNHNInfo_out response = gson.fromJson(output.toString(), SelectBillEVNHNInfo_out.class);

            if (response != null) {
                if ("0".equals(response.getTransactionReturn())) {
                    logger.info("selectBillEVNHNInfo_in: " + inputJson);
                    logger.info("selectBillEVNHNInfo_out: " + output);
                    logger.info("selectBillEVNHNInfo_out: " + response.getTransactionReturn());
                    logger.info("selectBillEVNHNInfo_out: " + response.getTransactionReturnMsg());
                }
                return response;
            } else {
                logger.warn("selectBillEVNHNInfo_in: " + inputJson);
                logger.warn("selectBillEVNHNInfo_out is null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectBillEVNHNInfo_out is null");
            }
        } catch (Exception e) {
            logger.error("selectBillEVNHNInfo - Exception: " + inputJson);
            logger.error("selectBillEVNHNInfo - Exception: " + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectBillEVNHNInfo - Exception: " + e.toString());
        }
    }
    
    public PayBillEVNHN_out payBillEVNHN(IIBContext iibContext, PayBillEVNHN_in request) throws BianException {
        HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_PAY_BILL);
        Gson gson = new Gson();
        String inputJson = gson.toJson(request);
        
        try {
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                logger.warn("payBillEVNHN: " + inputJson);
                logger.warn("payBillEVNHN: Failed : HTTP error code : " + conn.getResponseCode());
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                output = output.append(line);
            }
            conn.disconnect();
            
            PayBillEVNHN_out response = gson.fromJson(output.toString(), PayBillEVNHN_out.class);
            
            if (response != null) {
                if ("1".equals(response.getTransactionReturn())) {
                    logger.info("PayBillEVNHN_in: " + inputJson);
                    logger.info("PayBillEVNHN_out: " + output);
                    logger.info("PayBillEVNHN_out: " + response.getTransactionReturn());
                    logger.info("PayBillEVNHN_out: " + response.getTransactionReturnMsg());
                }
                return response;
            } else {
                logger.warn("PayBillEVNHN_in: " + inputJson);
                logger.warn("PayBillEVNHN_out is null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "PayBillEVNHN_out is null");
            }
        } catch (Exception e) {
            logger.error("payBillEVNHN - Exception: " + inputJson);
            logger.error("payBillEVNHN - Exception: " + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "payBillEVNHN - Exception: " + e.toString());
        }
    }

}
