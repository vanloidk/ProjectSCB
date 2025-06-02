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
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.transfer.TransactionDetail;
import vn.com.scb.bian.*;
import vn.com.scb.bian.utility.BianException;
import vn.com.scb.bian.utility.Helper;
import vn.com.scb.bian.utility.IIBUtils;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.scb.bian.utility.IIBContext;

/**
 *
 * @author scb
 */
public class IIBPaymentService extends IIBService {
    public static final Logger logger = Logger.getLogger(IIBPaymentService.class);

    public static final String RESOURCE_TRANSFER_INTERNAL = "/paymentexecution/v1.0/rest/executePaymentTransactionInternal";
    public static final String RESOURCE_TRANSFER_EXTERNAL = "/paymentexecution/v1.0/rest/executePaymentTransactionExternal";

    /** NOT USED
     *  * Chuyen khoan trong he thong voi it tham so  
     *
     * @param iibContext
     * @param productName
     * @param userid
     * @param accountFrom
     * @param accountTo
     * @param amount
     * @param narative
     * @param channel
     * @return
     */
    /*
    public ExecutePaymentTransactionInternal_out_Type executePaymentTransactionInternalRestSimple(IIBContext iibContext, String productName, String userid, String accountFrom, String accountTo, BigDecimal amount, String narative, String channel) throws BianException {
        String inputJson = "";
        try {
            ExecutePaymentTransactionInternal_in_Type internal_input = new ExecutePaymentTransactionInternal_in_Type();

            TransactionInfoType transactionInfo = IIBUtils.getTransInfpTypeCommon(channel);
            BranchInfoType branchinfo = new BranchInfoType();
            branchinfo.setBranchCode(IIBUtils.getBranchAccount(accountFrom));
            transactionInfo.setBranchInfo(branchinfo);

            internal_input.setTransactionInfo(transactionInfo);

            //tk nguon
            AccountInfoType sourceAccount = new AccountInfoType();
            sourceAccount.setAccountOpenBrandCode(IIBUtils.getBranchAccount(accountFrom));
            sourceAccount.setAccountNum(accountFrom);
            internal_input.setSourceAccount(sourceAccount);
            //tk dich     
            AccountInfoType toAccount = new AccountInfoType();
            toAccount.setAccountOpenBrandCode(IIBUtils.getBranchAccount(accountTo));
            toAccount.setAccountNum(accountTo);
            internal_input.setDestinationAccount(toAccount);
            //thong tin chuyen
            FundTransferInfoType fundTransferInfo = new FundTransferInfoType();
            fundTransferInfo.setFundTransferProductCode(productName);
            fundTransferInfo.setFundTransferAmount(amount.doubleValue());
            fundTransferInfo.setFundTransferNarative(narative);
            internal_input.setFundTransferInfo(fundTransferInfo);
            //user lam        
            CoreBankAccountType coreBankAccount = new CoreBankAccountType();
            coreBankAccount.setMakerAccount(userid);
            coreBankAccount.setSourceHeader("ODC1");
            internal_input.setCoreBankAccount(coreBankAccount);

            ExecutePaymentTransactionInternal_in_Type.Container container = new ExecutePaymentTransactionInternal_in_Type.Container();
            container.executePaymentTransactionInternal_in = internal_input;
            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_TRANSFER_INTERNAL);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple:" + inputJson);
                Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
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
            ExecutePaymentTransactionInternal_out_Type.Container internal_out_con = gson.fromJson(output, ExecutePaymentTransactionInternal_out_Type.Container.class);

            if (internal_out_con != null) {
                ExecutePaymentTransactionInternal_out_Type internal_out = internal_out_con.executePaymentTransactionInternal_out;
                if (internal_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return internal_out;
                } else {
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple:" + inputJson);
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple:" + output);
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple:" + internal_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple:" + internal_out.getTransactionInfo().getTransactionReturnMsg());
                    return internal_out;
                }
            } else {
                Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "executePaymentFeeSMS - out null");
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "executePaymentTransactionInternalRestSimple - Exception:" + e.toString());
        }
    }
    */
    /**
     * Chuyen khoan trong he thong voi day du tham so
     *
     * @param executePaymentTransactionInternal_in
     * @return
     */
    public ExecutePaymentTransactionInternal_out_Type executePaymentTransactionInternal(IIBContext iibContext, ExecutePaymentTransactionInternal_in_Type executePaymentTransactionInternal_in) throws BianException {
        String inputJson = "";
        try {
            ExecutePaymentTransactionInternal_in_Type.Container container = new ExecutePaymentTransactionInternal_in_Type.Container();
            container.executePaymentTransactionInternal_in = executePaymentTransactionInternal_in;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_TRANSFER_INTERNAL);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "executePaymentTransactionInternal:" + inputJson);
                Helper.writeLogError(this.getClass(), "executePaymentTransactionInternal: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
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
            ExecutePaymentTransactionInternal_out_Type.Container internal_out_con = gson.fromJson(output, ExecutePaymentTransactionInternal_out_Type.Container.class);

            if (internal_out_con != null) {
                ExecutePaymentTransactionInternal_out_Type internal_out = internal_out_con.executePaymentTransactionInternal_out;
                if (internal_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return internal_out;
                } else {
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionInternal:" + inputJson);
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionInternal:" + output);
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionInternal:" + internal_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionInternal:" + internal_out.getTransactionInfo().getTransactionReturnMsg());
                    return internal_out;
                }
            } else {
                Helper.writeLogError(this.getClass(), "executePaymentTransactionInternal - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "executePaymentTransactionInternal - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "executePaymentFeeSMS - out null");
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "executePaymentTransactionInternal - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "executePaymentTransactionInternal - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "executePaymentTransactionInternal - Exception:" + e.toString());
        }
    }

    /**
     * Chuyen khoan ngoai he thong
     *
     * @param ExecutePaymentTransactionExternal_in
     * @return
     */
    public ExecutePaymentTransactionExternal_out_Type executePaymentTransactionExternal(IIBContext iibContext, ExecutePaymentTransactionExternal_in_Type ExecutePaymentTransactionExternal_in) throws BianException {
        String inputJson = "";
        try {
            ExecutePaymentTransactionExternal_in_Type.Container container = new ExecutePaymentTransactionExternal_in_Type.Container();
            container.executePaymentTransactionExternal_in = ExecutePaymentTransactionExternal_in;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_TRANSFER_EXTERNAL);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "executePaymentTransactionExternal:" + inputJson);
                Helper.writeLogError(this.getClass(), "executePaymentTransactionExternal: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
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
            ExecutePaymentTransactionExternal_out_Type.Container external_out_con = gson.fromJson(output, ExecutePaymentTransactionExternal_out_Type.Container.class);
                       
            if (external_out_con != null) {
                ExecutePaymentTransactionExternal_out_Type external_out = external_out_con.executePaymentTransactionExternal_out;
                if (external_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return external_out;
                } else {
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionExternal:" + inputJson);
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionExternal:" + output);
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionExternal:" + external_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionExternal:" + external_out.getTransactionInfo().getTransactionReturnMsg());
                    return external_out;
                }
            } else {
                Helper.writeLogError(this.getClass(), "executePaymentTransactionExternal - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "executePaymentTransactionExternal - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "executePaymentTransactionExternal - out null");
            }            
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "executePaymentTransactionExternal - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "executePaymentTransactionExternal - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "executePaymentTransactionExternal - Exception:" + e.toString());
        }
    }

    public ExecutePaymentTransactionInternal_out_Type executePaymentFeeSMS(IIBContext iibContext, String productname, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, BigDecimal charge, BigDecimal tax, String narative, String channelID, String makerid) throws BianException {
        String inputJson = "";
        try {
            ExecutePaymentTransactionInternal_in_Type internal_input = new ExecutePaymentTransactionInternal_in_Type();
            //Transaction
            TransactionInfoType transactionType = new TransactionInfoType();
            transactionType.setClientCode(channelID);
            String xref = "MB" + String.valueOf(System.currentTimeMillis());
            transactionType.setCRefNum(xref);
            internal_input.setTransactionInfo(transactionType);
            //thong tin chuyen
            FundTransferInfoType fundTransferInfo = new FundTransferInfoType();
            fundTransferInfo.setFundTransferAmount(charge.doubleValue());
            fundTransferInfo.setFundTransferVATAmount(tax.intValue());
            fundTransferInfo.setFundTransferVATChgComp("THU PHI DICH VU SMS");
            fundTransferInfo.setFundTransferNarative(narative);
            fundTransferInfo.setFundTransferProductCode(productname);
            internal_input.setFundTransferInfo(fundTransferInfo);
            //tk nguon
            AccountInfoType sourceAccount = new AccountInfoType();
            sourceAccount.setAccountOpenBrandCode(brnAccountFrom);
            sourceAccount.setAccountNum(accountFrom);
            sourceAccount.setAccountCurrency("VND");
            internal_input.setSourceAccount(sourceAccount);
            //tk dich     
            AccountInfoType toAccount = new AccountInfoType();
            toAccount.setAccountOpenBrandCode(brnAccountTo);
            toAccount.setAccountNum(accountTo);
            toAccount.setAccountCurrency("VND");
            internal_input.setDestinationAccount(toAccount);

            //user lam        
            CoreBankAccountType coreBankAccount = new CoreBankAccountType();
            coreBankAccount.setMakerAccount(makerid);
            internal_input.setCoreBankAccount(coreBankAccount);

            ExecutePaymentTransactionInternal_in_Type.Container container = new ExecutePaymentTransactionInternal_in_Type.Container();
            container.executePaymentTransactionInternal_in = internal_input;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_TRANSFER_INTERNAL);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "executePaymentFeeSMS:" + inputJson);
                Helper.writeLogError(this.getClass(), "executePaymentFeeSMS: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
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
            ExecutePaymentTransactionInternal_out_Type.Container internal_out_con = gson.fromJson(output, ExecutePaymentTransactionInternal_out_Type.Container.class);

            if (internal_out_con != null) {
                ExecutePaymentTransactionInternal_out_Type internal_out = internal_out_con.executePaymentTransactionInternal_out;
                if (internal_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return internal_out;
                } else {
                    Helper.writeLogError(this.getClass(), "executePaymentFeeSMS:" + inputJson);
                    Helper.writeLogError(this.getClass(), "executePaymentFeeSMS:" + output);
                    Helper.writeLogError(this.getClass(), "executePaymentFeeSMS:" + internal_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "executePaymentFeeSMS:" + internal_out.getTransactionInfo().getTransactionReturnMsg());
                    return internal_out;
                }
            } else {
                Helper.writeLogError(this.getClass(), "executePaymentFeeSMS - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "executePaymentFeeSMS - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "executePaymentFeeSMS - out null");
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "executePaymentFeeSMS - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "executePaymentFeeSMS - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "executePaymentFeeSMS - Exception:" + e.toString());
        }
    }
    
    /** 
     *  * Chuyen khoan trong he thong voi it tham so  
     *
     * @param iibContext
     * @param productName
     * @param userid
     * @param accountFrom
     * @param accountTo
     * @param amount
     * @param narative
     * @param channel
     * @return
     */
    public ExecutePaymentTransactionInternal_out_Type executePaymentInterRestSimpleWithBrn(IIBContext iibContext, String productName, String userid, String accountFrom, String accountTo, BigDecimal amount, String narative, String channel, String BrnFrom,  String BrnTo) throws BianException {
        String inputJson = "";
        try {
            ExecutePaymentTransactionInternal_in_Type internal_input = new ExecutePaymentTransactionInternal_in_Type();

            TransactionInfoType transactionInfo = IIBUtils.getTransInfpTypeCommon(channel);
            BranchInfoType branchinfo = new BranchInfoType();
            //branchinfo.setBranchCode(IIBUtils.getBranchAccount(accountFrom));
            branchinfo.setBranchCode(BrnFrom);
            transactionInfo.setBranchInfo(branchinfo);

            internal_input.setTransactionInfo(transactionInfo);

            //tk nguon
            AccountInfoType sourceAccount = new AccountInfoType();
            sourceAccount.setAccountOpenBrandCode(BrnFrom);
            sourceAccount.setAccountNum(accountFrom);
            internal_input.setSourceAccount(sourceAccount);
            //tk dich     
            AccountInfoType toAccount = new AccountInfoType();
            //toAccount.setAccountOpenBrandCode(IIBUtils.getBranchAccount(toAccount));
            toAccount.setAccountOpenBrandCode(BrnTo);
            toAccount.setAccountNum(accountTo);
            internal_input.setDestinationAccount(toAccount);
            //thong tin chuyen
            FundTransferInfoType fundTransferInfo = new FundTransferInfoType();
            fundTransferInfo.setFundTransferProductCode(productName);
            fundTransferInfo.setFundTransferAmount(amount.doubleValue());
            fundTransferInfo.setFundTransferNarative(narative);
            internal_input.setFundTransferInfo(fundTransferInfo);
            //user lam        
            CoreBankAccountType coreBankAccount = new CoreBankAccountType();
            coreBankAccount.setMakerAccount(userid);
            coreBankAccount.setSourceHeader("ODC1");
            internal_input.setCoreBankAccount(coreBankAccount);

            ExecutePaymentTransactionInternal_in_Type.Container container = new ExecutePaymentTransactionInternal_in_Type.Container();
            container.executePaymentTransactionInternal_in = internal_input;
            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_TRANSFER_INTERNAL);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple:" + inputJson);
                Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
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
            ExecutePaymentTransactionInternal_out_Type.Container internal_out_con = gson.fromJson(output, ExecutePaymentTransactionInternal_out_Type.Container.class);

            if (internal_out_con != null) {
                ExecutePaymentTransactionInternal_out_Type internal_out = internal_out_con.executePaymentTransactionInternal_out;
                if (internal_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return internal_out;
                } else {
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple:" + inputJson);
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple:" + output);
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple:" + internal_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple:" + internal_out.getTransactionInfo().getTransactionReturnMsg());
                    return internal_out;
                }
            } else {
                Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "executePaymentFeeSMS - out null");
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "executePaymentTransactionInternalRestSimple - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "executePaymentTransactionInternalRestSimple - Exception:" + e.toString());
        }
    }
    
    public ExecutePaymentTransactionInternal_out_Type executePaymentInterRestSimpleWithBrn(
            IIBContext iibContext, ExecutePaymentTransactionInternal_in_Type internal_input) throws BianException {
        String inputJson = "";
        try {
            ExecutePaymentTransactionInternal_in_Type.Container container = new ExecutePaymentTransactionInternal_in_Type.Container();
            container.executePaymentTransactionInternal_in = internal_input;
            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_TRANSFER_INTERNAL);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                logger.warn("executePaymentTransactionInternalRestSimple:" + inputJson);
                logger.warn("executePaymentTransactionInternalRestSimple: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "Failed : HTTP error code :");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output = "";
            String line;
            while ((line = br.readLine()) != null) {
                output = output.concat(line);
            }
            conn.disconnect();
            ExecutePaymentTransactionInternal_out_Type.Container internal_out_con = gson.fromJson(output, ExecutePaymentTransactionInternal_out_Type.Container.class);

            if (internal_out_con != null) {
                ExecutePaymentTransactionInternal_out_Type internal_out = internal_out_con.executePaymentTransactionInternal_out;
                if (internal_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return internal_out;
                } else {
                    logger.warn("executePaymentTransactionInternalRestSimple:" + inputJson);
                    logger.warn("executePaymentTransactionInternalRestSimple:" + output);
                    logger.warn("executePaymentTransactionInternalRestSimple:" + internal_out.getTransactionInfo().getTransactionReturn());
                    logger.warn("executePaymentTransactionInternalRestSimple:" + internal_out.getTransactionInfo().getTransactionReturnMsg());
                    return internal_out;
                }
            } else {
                logger.warn("executePaymentTransactionInternalRestSimple - in:" + inputJson);
                logger.warn("executePaymentTransactionInternalRestSimple - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "executePaymentFeeSMS - out null");
            }
        } catch (Exception e) {
            logger.error("executePaymentTransactionInternalRestSimple - Exception:" + inputJson);
            logger.error("executePaymentTransactionInternalRestSimple - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "executePaymentTransactionInternalRestSimple - Exception:" + e.toString());
        }
    }
}