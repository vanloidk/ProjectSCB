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
public class IIBDepositAccountService  extends IIBService{
   public static final String RESOURCE_SELECT_DEPOSITACCOUNT = "/depositaccount/v1.0/rest/selectDepositAccountFromCIF";
   public static final String RESOURCE_RETRIEVE_DEPOSITACCOUNT = "/depositaccount/v1.0/rest/retrieveDepositAccountTD";   
   public static final String RESOURCE_TERMINATE_DEPOSITACCOUNT = "/depositaccount/v1.0/rest/terminateDepositAccount";
   public static final String RESOURCE_INIT_DEPOSITACCOUNT = "/depositaccount/v1.0/rest/initiateDepositAccountTD";   
    /**
     * Lay chi tiet toan tiet kiem voi it tham so dau vaos
     *
     * @param accountNo
     * @param channel
     * @return
     */
    public RetrieveDepositAccountTD_out_Type retrieveDepositAccountTDSimple(IIBContext iibContext, String accountNo, String channel) throws BianException {
        String inputJson = "";
        try {           
            RetrieveDepositAccountTD_in_Type retrieveDepositAccountTD_in = new RetrieveDepositAccountTD_in_Type();

            AccountInfoType accountinfo = new AccountInfoType();
            accountinfo.setAccountNum(accountNo);
            retrieveDepositAccountTD_in.setAccountInfo(accountinfo);

            retrieveDepositAccountTD_in.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(channel));

            RetrieveDepositAccountTD_in_Type.Container container = new RetrieveDepositAccountTD_in_Type.Container();
            container.retrieveDepositAccountTD_in = retrieveDepositAccountTD_in;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_RETRIEVE_DEPOSITACCOUNT);
            Gson gson = new Gson();            
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "retrieveDepositAccountTDSimple:" + inputJson);
                Helper.writeLogError(this.getClass(), "retrieveDepositAccountTDSimple: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "Failed : HTTP error code :");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()),"UTF-8"));

            String output = "";
            String line;
            while ((line = br.readLine()) != null) {
                output = output.concat(line);
            }
            conn.disconnect();
            RetrieveDepositAccountTD_out_Type.Container retrieveDepositAccountTD_out_Type_con = gson.fromJson(output, RetrieveDepositAccountTD_out_Type.Container.class);           
            if (retrieveDepositAccountTD_out_Type_con != null) {
                RetrieveDepositAccountTD_out_Type retrieveDepositAccountTD_out = retrieveDepositAccountTD_out_Type_con.retrieveDepositAccountTD_out;
                if (retrieveDepositAccountTD_out.getTransactionInfo() != null && retrieveDepositAccountTD_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return retrieveDepositAccountTD_out;
                } else {
                    Helper.writeLogError(this.getClass(), "retrieveDepositAccountTDSimple:" + inputJson);
                    Helper.writeLogError(this.getClass(), "retrieveDepositAccountTDSimple:" + output);
                    Helper.writeLogError(this.getClass(), "retrieveDepositAccountTDSimple:" + retrieveDepositAccountTD_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "retrieveDepositAccountTDSimple:" + retrieveDepositAccountTD_out.getTransactionInfo().getTransactionReturnMsg());
                    return retrieveDepositAccountTD_out;
                }
            } else {
                Helper.writeLogError(this.getClass(), "retrieveDepositAccountTDSimple - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "retrieveDepositAccountTDSimple - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "retrieveDepositAccountTDSimple - out null");
            }
    } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "retrieveDepositAccountTDSimple - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "retrieveDepositAccountTDSimple - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "retrieveDepositAccountTDSimple - Exception:" + e.toString());
        }
    }

    /**
     * Lay danh sach tai khoan tiet kiem voi it tham so dau vao
     *
     * @param cifNo
     * @param channel
     * @return
     */
    public SelectDepositAccountFromCIF_out_Type selectDepositAccountFromCIFRestSimple(IIBContext iibContext, String cifNo, String channel) throws BianException {
        String inputJson = "";
        try {
            SelectDepositAccountFromCIF_in_Type tdAccList_inType = new SelectDepositAccountFromCIF_in_Type();

            CIFDataType cifType = new CIFDataType();
            cifType.setCIFNum(cifNo);
            tdAccList_inType.setCIFInfo(cifType);
            tdAccList_inType.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(channel));

            SelectDepositAccountFromCIF_in_Type.Container container = new SelectDepositAccountFromCIF_in_Type.Container();
            container.selectDepositAccountFromCIF_in = tdAccList_inType;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_SELECT_DEPOSITACCOUNT);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                 Helper.writeLogError(this.getClass(), "selectDepositAccountFromCIFRestSimple:" + inputJson);
                Helper.writeLogError(this.getClass(), "selectDepositAccountFromCIFRestSimple: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
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
            SelectDepositAccountFromCIF_out_Type.Container selectDepositAccountFromCIF_out_container = gson.fromJson(output, SelectDepositAccountFromCIF_out_Type.Container.class);            
            if (selectDepositAccountFromCIF_out_container != null) {
                SelectDepositAccountFromCIF_out_Type tdAccList_outType = selectDepositAccountFromCIF_out_container.selectDepositAccountFromCIF_out;
                if (tdAccList_outType.getTransactionInfo() != null && tdAccList_outType.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return tdAccList_outType;
                } else {
                    Helper.writeLogError(this.getClass(), "selectDepositAccountFromCIFRestSimple:" + inputJson);
                    Helper.writeLogError(this.getClass(), "selectDepositAccountFromCIFRestSimple:" + output);
                    Helper.writeLogError(this.getClass(), "selectDepositAccountFromCIFRestSimple:" + tdAccList_outType.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "selectDepositAccountFromCIFRestSimple:" + tdAccList_outType.getTransactionInfo().getTransactionReturnMsg());
                    return tdAccList_outType;
                }
            } else {
                Helper.writeLogError(this.getClass(), "selectDepositAccountFromCIFRestSimple - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "selectDepositAccountFromCIFRestSimple - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "retrieveDepositAccountTDSimple - out null");
            }
       } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "selectDepositAccountFromCIFRestSimple - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "selectDepositAccountFromCIFRestSimple - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectDepositAccountFromCIFRestSimple - Exception:" + e.toString());
        }
    }

    public InitiateDepositAccountTD_out_type initiateDepositAccountTD(IIBContext iibContext, InitiateDepositAccountTD_in_type initiateDepositAccountTD_in) throws BianException {
        String inputJson = "";
        try {
            InitiateDepositAccountTD_in_type.Container container = new InitiateDepositAccountTD_in_type.Container();
            container.initiateDepositAccountTD_in = initiateDepositAccountTD_in;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_INIT_DEPOSITACCOUNT);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "retrieveCustomerRefDataMgmtSimple:" + inputJson);
                Helper.writeLogError(this.getClass(), "retrieveCustomerRefDataMgmtSimple: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
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
            InitiateDepositAccountTD_out_type.Container initiateDepositAccountTD_out_con = new Gson().fromJson(output, InitiateDepositAccountTD_out_type.Container.class);
                       
             if (initiateDepositAccountTD_out_con != null) {
                InitiateDepositAccountTD_out_type initiateDepositAccountTD_out = initiateDepositAccountTD_out_con.initiateDepositAccountTD_out;
                if (initiateDepositAccountTD_out.getTransactionInfo() != null && initiateDepositAccountTD_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return initiateDepositAccountTD_out;
                } else {
                    Helper.writeLogError(this.getClass(), "initiateDepositAccountTD:" + inputJson);
                    Helper.writeLogError(this.getClass(), "initiateDepositAccountTD:" + output);
                    Helper.writeLogError(this.getClass(), "initiateDepositAccountTD:" + initiateDepositAccountTD_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "initiateDepositAccountTD:" + initiateDepositAccountTD_out.getTransactionInfo().getTransactionReturnMsg());
                    return initiateDepositAccountTD_out;
                }
            } else {
                Helper.writeLogError(this.getClass(), "initiateDepositAccountTD - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "initiateDepositAccountTD - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "initiateDepositAccountTD - out null");
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "initiateDepositAccountTD - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "initiateDepositAccountTD - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "initiateDepositAccountTD - Exception:" + e.toString());
        }
    }

    public TerminateDepositAccount_out_Type terminateDepositAccount(IIBContext iibContext, TerminateDepositAccount_in_Type terminateDepositAccount_in) throws BianException {
        String inputJson = "";
        try {            
            TerminateDepositAccount_in_Type.Container container = new TerminateDepositAccount_in_Type.Container();
            container.terminateDepositAccount_in = terminateDepositAccount_in;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_TERMINATE_DEPOSITACCOUNT);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                 Helper.writeLogError(this.getClass(), "terminateDepositAccount:" + inputJson);
                Helper.writeLogError(this.getClass(), "terminateDepositAccount: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
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
            TerminateDepositAccount_out_Type.Container terminateDepositAccount_out_con = new Gson().fromJson(output, TerminateDepositAccount_out_Type.Container.class);
                       
            if (terminateDepositAccount_out_con != null) {
                TerminateDepositAccount_out_Type terminateDepositAccount_out = terminateDepositAccount_out_con.terminateDepositAccount_out;
                if (terminateDepositAccount_out.getTransactionInfo() != null && terminateDepositAccount_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return terminateDepositAccount_out;
                } else {
                    Helper.writeLogError(this.getClass(), "terminateDepositAccount:" + inputJson);
                    Helper.writeLogError(this.getClass(), "terminateDepositAccount:" + output);
                    Helper.writeLogError(this.getClass(), "terminateDepositAccount:" + terminateDepositAccount_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "terminateDepositAccount:" + terminateDepositAccount_out.getTransactionInfo().getTransactionReturnMsg());
                    return terminateDepositAccount_out;
                }
            } else {
                Helper.writeLogError(this.getClass(), "terminateDepositAccount - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "terminateDepositAccount - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "terminateDepositAccount - out null");
            }            
       } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "terminateDepositAccount - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "terminateDepositAccount - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "terminateDepositAccount - Exception:" + e.toString());
        }
    }

}
