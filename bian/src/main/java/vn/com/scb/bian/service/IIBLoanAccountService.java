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
public class IIBLoanAccountService extends IIBService {

    public static final String RESOURCE_SELECT_LOANACCOUNT = "/loan/v1.0/rest/selectLoanFromCIF";
    public static final String RESOURCE_RETRIEVE_LOANACCOUNT = "/loan/v1.0/rest/retrieveLoanSumary";

    /**
     * Lay danh sach tai khoan vay
     *
     * @param cifNo
     * @param channel
     * @param iibContext
     * @return
     */
    public SelectLoanFromCIF_out_Type selectLoanFromCIFRestSimple(IIBContext iibContext, String cifNo, String channel) throws BianException {
        String inputJson = "";
        try {
            SelectLoanFromCIF_in_Type loanAccList_inType = new SelectLoanFromCIF_in_Type();
            CIFDataType cifType = new CIFDataType();
            cifType.setCIFNum(cifNo);
            loanAccList_inType.setCIFInfo(cifType);
            loanAccList_inType.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(channel));

            SelectLoanFromCIF_in_Type.Container container = new SelectLoanFromCIF_in_Type.Container();
            container.selectLoanFromCIF_in = loanAccList_inType;
            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_SELECT_LOANACCOUNT);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "selectLoanFromCIFRestSimple:" + inputJson);
                Helper.writeLogError(this.getClass(), "selectLoanFromCIFRestSimple: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
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
            SelectLoanFromCIF_out_Type.Container selectLoanAccountFromCIF_out_container = gson.fromJson(output, SelectLoanFromCIF_out_Type.Container.class);
            if (selectLoanAccountFromCIF_out_container != null) {
                SelectLoanFromCIF_out_Type loanAccList_outType = selectLoanAccountFromCIF_out_container.selectLoanFromCIF_out;
                if (loanAccList_outType.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return loanAccList_outType;
                } else {
                    Helper.writeLogError(this.getClass(), "selectLoanFromCIFRestSimple:" + inputJson);
                    Helper.writeLogError(this.getClass(), "selectLoanFromCIFRestSimple:" + output);
                    Helper.writeLogError(this.getClass(), "selectLoanFromCIFRestSimple:" + loanAccList_outType.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "selectLoanFromCIFRestSimple:" + loanAccList_outType.getTransactionInfo().getTransactionReturnMsg());
                    return loanAccList_outType;
                }
            } else {
                Helper.writeLogError(this.getClass(), "selectLoanFromCIFRestSimple - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "selectLoanFromCIFRestSimple - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectLoanFromCIFRestSimple - out null");
            }

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "selectLoanFromCIFRestSimple - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "selectLoanFromCIFRestSimple - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectLoanFromCIFRestSimple - Exception:" + e.toString());
        }
    }

  /**
   * Lay chi tiet tai khoan vay
   * @param iibContext
   * @param accountNo
   * @param channel
   * @return
   * @throws BianException 
   */
    public RetrieveLoanSumary_out_Type retrieveLoanSumaryRestSimple(IIBContext iibContext, String accountNo, String channel) throws BianException {
        String inputJson = "";
        try {
            RetrieveLoanSumary_in_Type retrieveLoanSumary_in_Type = new RetrieveLoanSumary_in_Type();
            LoanAccountInfoType accountinfo = new LoanAccountInfoType();
            accountinfo.setLoanAccountNum(accountNo);
            retrieveLoanSumary_in_Type.setLoanAccountInfo(accountinfo);

            retrieveLoanSumary_in_Type.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(channel));

            RetrieveLoanSumary_in_Type.Container container = new RetrieveLoanSumary_in_Type.Container();
            container.retrieveLoanSumary_in = retrieveLoanSumary_in_Type;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_RETRIEVE_LOANACCOUNT);
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
            RetrieveLoanSumary_out_Type.Container retrieveLoanAcc_out_Type_con = gson.fromJson(output, RetrieveLoanSumary_out_Type.Container.class);
            if (retrieveLoanAcc_out_Type_con != null) {
                RetrieveLoanSumary_out_Type retrieveLoanAccount_out = retrieveLoanAcc_out_Type_con.retrieveLoanSumary_out;
                if (retrieveLoanAccount_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return retrieveLoanAccount_out;

                } else {
                    Helper.writeLogError(this.getClass(), "retrieveLoanSumaryRestSimple:" + inputJson);
                    Helper.writeLogError(this.getClass(), "retrieveLoanSumaryRestSimple:" + output);
                    Helper.writeLogError(this.getClass(), "retrieveLoanSumaryRestSimple:" + retrieveLoanAccount_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "retrieveLoanSumaryRestSimple:" + retrieveLoanAccount_out.getTransactionInfo().getTransactionReturnMsg());
                    return retrieveLoanAccount_out;
                }
            } else {
                Helper.writeLogError(this.getClass(), "retrieveLoanSumaryRestSimple - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "retrieveLoanSumaryRestSimple - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "retrieveLoanSumaryRestSimple - out null");
            }

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "retrieveLoanSumaryRestSimple - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "retrieveLoanSumaryRestSimple - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "retrieveCustomerRefDataMgmtSimple - Exception:" + e.toString());
        }
    }

}
