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
import vn.com.scb.bian.model.vnpt.PayBillVNPT_in;
import vn.com.scb.bian.model.vnpt.PayBillVNPT_out;
import vn.com.scb.bian.model.vnpt.SelectBillVNPTInfo_in;
import vn.com.scb.bian.model.vnpt.SelectBillVNPTInfo_out;
import vn.com.scb.bian.service.IIBService;
import vn.com.scb.bian.utility.BianException;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.scb.bian.utility.IIBContext;
import vn.com.scb.bian.utility.IIBUtilsConstants;

/**
 *
 * @author minhndb
 */
public class IIBVNPTService extends IIBService {

    private static final Logger logger = Logger.getLogger(IIBVNPTService.class);

    public static final String RESOURCE_SELECT_BILL = "/billvnpt/v1.0/rest/getBillInfoVNPT";
    public static final String RESOURCE_PAY_BILL = "/billvnpt/v1.0/rest/payBillVNPT";

    public SelectBillVNPTInfo_out selectBillVNPTInfo(IIBContext iibContext, String transId,
             String customerCode, String province, String channel) throws BianException {

        StringBuilder str = new StringBuilder();
        SelectBillVNPTInfo_in request = new SelectBillVNPTInfo_in(transId, customerCode, province, channel);
        HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_SELECT_BILL);
        Gson gson = new Gson();
        String inputJson = gson.toJson(request);

        try {
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                str.append("selectBillVNPTInfo: ").append(inputJson)
                        .append(IIBUtilsConstants.LINE_SEPERATE)
                        .append("selectBillVNPTInfo: Failed : HTTP error code : ")
                        .append(conn.getResponseCode());
                logger.warn(str);
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, str.toString());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                output = output.append(line);
            }
            conn.disconnect();

            SelectBillVNPTInfo_out response = gson.fromJson(output.toString(), SelectBillVNPTInfo_out.class);

            if (response != null) {
                if ("0".equals(response.getTransactionReturn())) {
                    str.append("selectBillVNPTInfo_in: ").append(inputJson)
                            .append(IIBUtilsConstants.LINE_SEPERATE)
                            .append("selectBillVNPTInfo_out: " + output)
                            .append(IIBUtilsConstants.LINE_SEPERATE)
                            .append("selectBillVNPTInfo_out: " + response.getTransactionReturn())
                            .append(IIBUtilsConstants.LINE_SEPERATE)
                            .append("selectBillVNPTInfo_out: " + response.getTransactionReturnMsg());
                }
                return response;
            } else {
                logger.warn("selectBillVNPTInfo_in: " + inputJson);
                logger.warn("selectBillVNPTInfo_out is null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectBillVNPTInfo_out is null");
            }
        } catch (Exception e) {
            logger.error("selectBillVNPTInfo - Exception: " + inputJson);
            logger.error("selectBillVNPTInfo - Exception: " + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectBillVNPTInfo - Exception: " + e.toString());
        }
    }

    public PayBillVNPT_out payBillVNPT(IIBContext iibContext, String transId,
             String customerCode, String amount, String information, String channel) throws BianException {

        PayBillVNPT_in request = new PayBillVNPT_in(transId, customerCode, amount, information, channel);
        HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_PAY_BILL);
        Gson gson = new Gson();
        String inputJson = gson.toJson(request);

        try {
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                logger.warn("payBillVNPT: " + inputJson);
                logger.warn("payBillVNPT: Failed : HTTP error code : " + conn.getResponseCode());
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

            PayBillVNPT_out response = gson.fromJson(output.toString(), PayBillVNPT_out.class);

            if (response != null) {
                if ("1".equals(response.getTransactionReturn())) {
                    logger.info("PayBillVNPT_in: " + inputJson);
                    logger.info("PayBillVNPT_out: " + output);
                    logger.info("PayBillVNPT_out: " + response.getTransactionReturn());
                    logger.info("PayBillVNPT_out: " + response.getTransactionReturnMsg());
                }
                return response;
            } else {
                logger.warn("PayBillVNPT_in: " + inputJson);
                logger.warn("PayBillVNPT_out is null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "PayBillVNPT_out is null");
            }
        } catch (Exception e) {
            logger.error("payBillVNPT - Exception: " + inputJson);
            logger.error("payBillVNPT - Exception: " + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "payBillVNPT - Exception: " + e.toString());
        }
    }
}
