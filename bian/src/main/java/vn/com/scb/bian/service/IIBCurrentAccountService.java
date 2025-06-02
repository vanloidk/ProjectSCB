/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.service;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import vn.com.scb.bian.utility.Helper;
import vn.com.scb.bian.utility.IIBUtils;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.scb.bian.*;
import vn.com.scb.bian.utility.BianException;
import vn.com.scb.bian.utility.IIBContext;

/**
 *
 * @author scb
 */
public class IIBCurrentAccountService extends IIBService {

    public static final String RESOURCE_SELECT_CURRENTACCOUNT = "/currentaccount/v1.0/rest/selectCurrentAccountFromCIF";
    public static final String RESOURCE_RETRIEVE_CURRENTACCOUNT = "/currentaccount/v1.0/rest/retrieveCurrentAccountCASA";

    /**
     *
     * Lay chi tiet tai khoan casa it tham so
     *
     * @param accountNo
     * @param channel
     * @param iibContext
     * @return
     */
    public RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASARestSimple(IIBContext iibContext, String accountNo, String channel) throws BianException {
        String inputJson = "";
        try {
            RetrieveCurrentAccountCASA_in_Type retrieveCurrentAccountCASA_in = new RetrieveCurrentAccountCASA_in_Type();
            AccountInfoType accountinfo = new AccountInfoType();
            accountinfo.setAccountNum(accountNo);
            retrieveCurrentAccountCASA_in.setAccountInfo(accountinfo);
            retrieveCurrentAccountCASA_in.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(channel));
            RetrieveCurrentAccountCASA_in_Type.Container container = new RetrieveCurrentAccountCASA_in_Type.Container();
            container.retrieveCurrentAccountCASA_in = retrieveCurrentAccountCASA_in;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_RETRIEVE_CURRENTACCOUNT);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "retrieveCurrentAccountCASARestSimple:" + inputJson);
                Helper.writeLogError(this.getClass(), "retrieveCurrentAccountCASARestSimple: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
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
            RetrieveCurrentAccountCASA_out_Type.Container retrieveCurrentAccountCASA_out_Type_con = gson.fromJson(output, RetrieveCurrentAccountCASA_out_Type.Container.class);
            if (retrieveCurrentAccountCASA_out_Type_con != null) {
                RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASA_out_Type = retrieveCurrentAccountCASA_out_Type_con.retrieveCurrentAccountCASA_out;
                if (retrieveCurrentAccountCASA_out_Type.getTransactionInfo() != null && retrieveCurrentAccountCASA_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return retrieveCurrentAccountCASA_out_Type;
                } else {
                    Helper.writeLogError(this.getClass(), "retrieveCurrentAccountCASARestSimple:" + inputJson);
                    Helper.writeLogError(this.getClass(), "retrieveCurrentAccountCASARestSimple:" + output);
                    Helper.writeLogError(this.getClass(), "retrieveCurrentAccountCASARestSimple:" + retrieveCurrentAccountCASA_out_Type.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "retrieveCurrentAccountCASARestSimple:" + retrieveCurrentAccountCASA_out_Type.getTransactionInfo().getTransactionReturnMsg());
                    return retrieveCurrentAccountCASA_out_Type;
                }
            } else {
                Helper.writeLogError(this.getClass(), "retrieveCurrentAccountCASARestSimple - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "retrieveCurrentAccountCASARestSimple - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "retrieveCurrentAccountCASARestSimple - out null");
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "retrieveCurrentAccountCASARestSimple - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "retrieveCurrentAccountCASARestSimple - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "retrieveCurrentAccountCASARestSimple - Exception:" + e.toString());
        }
    }

    /**
     * Lay danh sach tai khoan thanh toan voi it tham so dau vao
     *
     * @param cifNo
     * @param channel
     * @param iibContext
     * @return
     */
    public SelectCurrentAccountFromCIF_out_Type selectCurrentAccountFromCIFRestSimple(IIBContext iibContext, String cifNo, String channel) throws BianException {
        String inputJson = "";
        try {
            SelectCurrentAccountFromCIF_in_Type selectCurrentAccountFromCIF_in = new SelectCurrentAccountFromCIF_in_Type();
            CIFDataType cifType = new CIFDataType();
            cifType.setCIFNum(cifNo);
            cifType.setBranchCode("");
            selectCurrentAccountFromCIF_in.setCIFInfo(cifType);

            AccountInfoType accountinfo = new AccountInfoType();
            accountinfo.setAccountCurrency("");
            selectCurrentAccountFromCIF_in.setAccountInfo(accountinfo);

            selectCurrentAccountFromCIF_in.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(channel));
            SelectCurrentAccountFromCIF_in_Type.Container container = new SelectCurrentAccountFromCIF_in_Type.Container();
            container.selectCurrentAccountFromCIF_in = selectCurrentAccountFromCIF_in;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_SELECT_CURRENTACCOUNT);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "SelectCurrentAccountFromCIF_out_Type:" + inputJson);
                Helper.writeLogError(this.getClass(), "SelectCurrentAccountFromCIF_out_Type: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
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
            SelectCurrentAccountFromCIF_out_Type.Container selectCurrentAccountFromCIF_out__con = gson.fromJson(output, SelectCurrentAccountFromCIF_out_Type.Container.class);
            if (selectCurrentAccountFromCIF_out__con != null) {
                SelectCurrentAccountFromCIF_out_Type selectCurrentAccountFromCIF_out = selectCurrentAccountFromCIF_out__con.selectCurrentAccountFromCIF_out;
                if (selectCurrentAccountFromCIF_out.getTransactionInfo() != null && selectCurrentAccountFromCIF_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return selectCurrentAccountFromCIF_out;
                } else {
                    Helper.writeLogError(this.getClass(), "SelectCurrentAccountFromCIF_out_Type:" + inputJson);
                    Helper.writeLogError(this.getClass(), "SelectCurrentAccountFromCIF_out_Type:" + output);
                    Helper.writeLogError(this.getClass(), selectCurrentAccountFromCIF_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), selectCurrentAccountFromCIF_out.getTransactionInfo().getTransactionReturnMsg());

                    return selectCurrentAccountFromCIF_out;
                }
            } else {
                Helper.writeLogError(this.getClass(), "SelectCurrentAccountFromCIF_out_Type - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "SelectCurrentAccountFromCIF_out_Type - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "SelectCurrentAccountFromCIF_out_Type - null");
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "SelectCurrentAccountFromCIF_out_Type - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "SelectCurrentAccountFromCIF_out_Type - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "SelectCurrentAccountFromCIF_out_Type - Exception:" + e.toString());
        }
    }

}
