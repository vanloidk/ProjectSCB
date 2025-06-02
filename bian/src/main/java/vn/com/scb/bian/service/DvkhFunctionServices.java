/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import org.apache.log4j.Logger;
import vn.com.scb.bian.CreateRewardNumber_in_Type;
import vn.com.scb.bian.CreateRewardNumber_out_Type;
import vn.com.scb.bian.SelectRewardNumberFromAcctNo_in_Type;
import vn.com.scb.bian.SelectRewardNumberFromAcctNo_out_Type;

import vn.com.scb.bian.utility.BianException;
import vn.com.scb.bian.utility.Helper;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.scb.bian.utility.IIBContext;

/**
 *
 * @author kimncm
 */
public class DvkhFunctionServices extends IIBService {

    public static final Logger logger = Logger.getLogger(IIBPaymentService.class);
    public static final String RESOURCE_CREATE_REWARDNUMBER = "/dvkhfunction/v1.0/rest/createRewardNumber";
    public static final String RESOURCE_SELECT_REWARDNUMBER = "/dvkhfunction/v1.0/rest/selectRewardNumberFromAcctNo";

    /**
     * Cáº¥p sá»‘ dá»± thÆ°á»Ÿng
     * @param iibContext
     * @param createRewardNumber_in_Type
     * @return
     * @throws BianException 
     */
    public CreateRewardNumber_out_Type createRewardNumber(IIBContext iibContext, CreateRewardNumber_in_Type createRewardNumber_in_Type) throws BianException {
        String inputJson = "";
        try {
            CreateRewardNumber_in_Type.Container container = new CreateRewardNumber_in_Type.Container();
            container.createRewardNumber_in = createRewardNumber_in_Type;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_CREATE_REWARDNUMBER);
            conn.setRequestProperty("Encoding", "UTF-8");
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "createRewardNumber:" + inputJson);
                Helper.writeLogError(this.getClass(), "createRewardNumber: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "Failed : HTTP error code :");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output = "";
            String line;
            while ((line = br.readLine()) != null) {
                output = output.concat(line);
            }
            conn.disconnect();
            CreateRewardNumber_out_Type.Container createRewardNumber_out_Container = gson.fromJson(output, CreateRewardNumber_out_Type.Container.class);
            if (createRewardNumber_out_Container != null) {
                CreateRewardNumber_out_Type createRewardNumber_out_Type = createRewardNumber_out_Container.createRewardNumber_out;
                if (createRewardNumber_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    //Helper.writeLogError(this.getClass(), "createRewardNumber - in:" + inputJson);
                    //Helper.writeLogError(this.getClass(), "createRewardNumber - OUT:" + output);
                    return createRewardNumber_out_Type;
                } else {
                    Helper.writeLogError(this.getClass(), "createRewardNumber:" + inputJson);
                    Helper.writeLogError(this.getClass(), "createRewardNumber" + output);
                    Helper.writeLogError(this.getClass(), "createRewardNumber" + createRewardNumber_out_Type.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "createRewardNumber" + createRewardNumber_out_Type.getTransactionInfo().getTransactionReturnMsg());
                    return createRewardNumber_out_Type;
                }
            } else {
                Helper.writeLogError(this.getClass(), "createRewardNumber - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "createRewardNumber - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "createRewardNumber - out null");
            }

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "createRewardNumber - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "createRewardNumber - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "createRewardNumber - Exception:" + e.toString());
        }
    }
     /**
     * Láº¥y sá»‘ dá»± thÆ°á»Ÿng Ä‘Ã£ cáº¥p
     * @param iibContext
     * @param selectRewardNumber_in_Type
     * @return
     * @throws BianException 
     */
    public SelectRewardNumberFromAcctNo_out_Type selectRewardNumberFromAcctNo(IIBContext iibContext, SelectRewardNumberFromAcctNo_in_Type selectRewardNumber_in_Type) throws BianException {
        String inputJson = "";
        try {
            SelectRewardNumberFromAcctNo_in_Type.Container container = new SelectRewardNumberFromAcctNo_in_Type.Container();
            container.selectRewardNumberFromAcctNo_in  = selectRewardNumber_in_Type;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_SELECT_REWARDNUMBER);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "selectRewardNumberFromAcctNo:" + inputJson);
                Helper.writeLogError(this.getClass(), "selectRewardNumberFromAcctNo: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "Failed : HTTP error code :");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output = "";
            String line;
            while ((line = br.readLine()) != null) {
                output = output.concat(line);
            }
            conn.disconnect();
            SelectRewardNumberFromAcctNo_out_Type.Container selectRewardNumber_out_Container = gson.fromJson(output, SelectRewardNumberFromAcctNo_out_Type.Container.class);
            if (selectRewardNumber_out_Container != null) {
                SelectRewardNumberFromAcctNo_out_Type selectRewardNumber_out_Type = selectRewardNumber_out_Container.selectRewardNumberFromAcctNo_out;
                if (selectRewardNumber_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    //Helper.writeLogError(this.getClass(), "selectRewardNumber - in:" + inputJson);
                   // Helper.writeLogError(this.getClass(), "selectRewardNumber - out:" + output);
                    return selectRewardNumber_out_Type;
                } else {
                    Helper.writeLogError(this.getClass(), "selectRewardNumber:" + inputJson);
                    Helper.writeLogError(this.getClass(), "selectRewardNumber" + output);
                    Helper.writeLogError(this.getClass(), "selectRewardNumber" + selectRewardNumber_out_Type.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "selectRewardNumber" + selectRewardNumber_out_Type.getTransactionInfo().getTransactionReturnMsg());
                    return selectRewardNumber_out_Type;
                }
            } else {
                Helper.writeLogError(this.getClass(), "selectRewardNumber - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "selectRewardNumber - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectRewardNumber - out null");
            }

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "selectRewardNumber - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "selectRewardNumber - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectRewardNumber - Exception:" + e.toString());
        }
    }
}
