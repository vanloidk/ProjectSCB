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
public class IIBCustomerService extends IIBService{
       
    public static final String RESOURCE_SELECT_CUSTOMER = "";
    public static final String RESOURCE_RETRIEVE_CUSTOMER = "/customerrefdatamgmt/v1.0/rest/retrieveCustomerRefDataMgmt";          
    public static final String RESOURCE_UPDATE_CUSTOMER = "/customerrefdatamgmt/v1.0/rest/updateCIFInfo";          
    
     /**
   * 
   * Lấy thong tin chi tiet theo CIF
   * @param iibContext
   * @param cifnumber   
   * @param channel
   * @return 
   */
   
    public RetrieveCustomerRefDataMgmt_out_Type retrieveCustomerRefDataMgmtSimple(IIBContext iibContext, String cifnumber, String channel) throws BianException{
        String inputJson ="";
        try {                        
            RetrieveCustomerRefDataMgmt_in_Type retrieveCustomerRefDataMgmt_in = new RetrieveCustomerRefDataMgmt_in_Type();

            CIFDataType cifinfo = new CIFDataType();
            cifinfo.setCIFNum(cifnumber);
            retrieveCustomerRefDataMgmt_in.setCIFInfo(cifinfo);

            retrieveCustomerRefDataMgmt_in.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(channel));

            RetrieveCustomerRefDataMgmt_in_Type.Container container = new RetrieveCustomerRefDataMgmt_in_Type.Container();
            container.retrieveCustomerRefDataMgmt_in = retrieveCustomerRefDataMgmt_in;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_RETRIEVE_CUSTOMER);
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
                    (conn.getInputStream()),"UTF-8"));

            String output = "";
            String line;
            while ((line = br.readLine()) != null) {
                output = output.concat(line);
            }
            conn.disconnect();
            RetrieveCustomerRefDataMgmt_out_Type.Container retrieveCustomerRefDataMgmt_in_Type_con = gson.fromJson(output, RetrieveCustomerRefDataMgmt_out_Type.Container.class);            
            
            Helper.writeLogError(this.getClass(), "retrieveCustomerRefDataMgmtSimple - Result:" + output);
            
            if (retrieveCustomerRefDataMgmt_in_Type_con != null) {
                RetrieveCustomerRefDataMgmt_out_Type retrieveCustomerRefDataMgmt_out_Type = retrieveCustomerRefDataMgmt_in_Type_con.retrieveCustomerRefDataMgmt_out;
                if (retrieveCustomerRefDataMgmt_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {                    
                    return retrieveCustomerRefDataMgmt_out_Type;
                } else {
                    Helper.writeLogError(this.getClass(), "retrieveCustomerRefDataMgmtSimple:" + inputJson);
                    Helper.writeLogError(this.getClass(), "retrieveCustomerRefDataMgmtSimple:" + output);
                    Helper.writeLogError(this.getClass(), "retrieveCustomerRefDataMgmtSimple:" + retrieveCustomerRefDataMgmt_out_Type.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "retrieveCustomerRefDataMgmtSimple:" + retrieveCustomerRefDataMgmt_out_Type.getTransactionInfo().getTransactionReturnMsg());
                    return retrieveCustomerRefDataMgmt_out_Type;
                }
            } else {
                Helper.writeLogError(this.getClass(), "retrieveCustomerRefDataMgmtSimple - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "retrieveCustomerRefDataMgmtSimple - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "retrieveCustomerRefDataMgmtSimple - out null");
            }

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "retrieveCustomerRefDataMgmtSimple - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "retrieveCustomerRefDataMgmtSimple - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "retrieveCustomerRefDataMgmtSimple - Exception:" + e.toString());
        }
    }
   
       /**
   * 
   * Cập nhật  thông tin KH
   * @param iibContext
   * @param cifnumber   
   * @param channel
   * @return 
   */
   
    public UpdateCIFInfo_out_Type updateCIFInfo(IIBContext iibContext, CIFDataType CIFInfo, String channel) throws BianException{
        String inputJson ="";
        try {                        
            UpdateCIFInfo_in_Type updateCIFInfo_in = new UpdateCIFInfo_in_Type();

            
            updateCIFInfo_in.setCIFInfo(CIFInfo);

            updateCIFInfo_in.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(channel));

            UpdateCIFInfo_in_Type.Container container = new UpdateCIFInfo_in_Type.Container();
            container.updateCIFInfo_in = updateCIFInfo_in;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_UPDATE_CUSTOMER);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "updateCIFInfo:" + inputJson);
                Helper.writeLogError(this.getClass(), "updateCIFInfo: Failed : HTTP error code :" + conn.getResponseCode() + conn.getResponseCode());
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
            UpdateCIFInfo_out_Type.Container updateCIFInfo_out_Type_con = gson.fromJson(output, UpdateCIFInfo_out_Type.Container.class);            
            if (updateCIFInfo_out_Type_con != null) {
                UpdateCIFInfo_out_Type updateCIFInfo_out_Type = updateCIFInfo_out_Type_con.updateCIFInfo_out;
                if (updateCIFInfo_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {                    
                    return updateCIFInfo_out_Type;
                } else {
                    Helper.writeLogError(this.getClass(), "updateCIFInfo:" + inputJson);
                    Helper.writeLogError(this.getClass(), "updateCIFInfo:" + output);
                    Helper.writeLogError(this.getClass(), "updateCIFInfo:" + updateCIFInfo_out_Type.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "updateCIFInfo:" + updateCIFInfo_out_Type.getTransactionInfo().getTransactionReturnMsg());
                    return updateCIFInfo_out_Type;
                }
            } else {
                Helper.writeLogError(this.getClass(), "updateCIFInfo - in:" + inputJson);
                Helper.writeLogError(this.getClass(), "updateCIFInfo - out null");
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "updateCIFInfo - out null");
            }

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "updateCIFInfo - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "updateCIFInfo - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "updateCIFInfo - Exception:" + e.toString());
        }
    }
    
  /**
     * 
     * Lay thong tin CIF KH
     * @param accountNo
     * @param channel
     * @return 
     */
    /*
    public SelectCustomerRefDataMgmtCIFNum_out_Type selectCustomerRefDataMgmtCIFNumSimple(IIBContext iibContext, String custNo, String ID, String channel) {
        try {            
            URL url = new URL(iibContext.getServiceEndpoint().concat("/customerrefdatamgmt/v1.0/rest/selectCustomerRefDataMgmtCIFNum"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String loginPassword = iibContext.getClientId() + ":" +  iibContext.getClientPassword();
            String encoded = new sun.misc.BASE64Encoder().encode(loginPassword.getBytes());
            conn.setRequestProperty("Authorization", "Basic " + encoded);
            Gson gson = new Gson();

            SelectCustomerRefDataMgmtCIFNum_in_Type selectCustomerRefDataMgmtCIFNum_in = new SelectCustomerRefDataMgmtCIFNum_in_Type();

            AccountInfoType accountinfo = new AccountInfoType();
            accountinfo.setAccountNum(accountNo);
            retrieveCurrentAccountCASA_in.setAccountInfo(accountinfo);

            retrieveCurrentAccountCASA_in.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(channel));

            RetrieveCurrentAccountCASA_in_Type.Container container = new RetrieveCurrentAccountCASA_in_Type.Container();
            container.retrieveCurrentAccountCASA_in = retrieveCurrentAccountCASA_in;

            String inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output = "";
            String line;
            while ((line = br.readLine()) != null) {
                output = output.concat(line);
            }
            conn.disconnect();
            SelectCustomerRefDataMgmtCIFNum_out_Type.Container selectCustomerRefDataMgmtCIFNum_out_Type_con = gson.fromJson(output, SelectCustomerRefDataMgmtCIFNum_out_Type.Container.class);

            SelectCustomerRefDataMgmtCIFNum_out_Type selectCustomerRefDataMgmtCIFNum_out_Type = selectCustomerRefDataMgmtCIFNum_out_Type_con.selectCustomerRefDataMgmtCIFNum_out;
            if (selectCustomerRefDataMgmtCIFNum_out_Type != null) {
                if (selectCustomerRefDataMgmtCIFNum_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    AccountInfoType accountInfo = selectCustomerRefDataMgmtCIFNum_out_Type.getCustomerList();                    
                    return accountInfo;
                } else {
                    Helper.writeLogError(this.getClass(), retrieveCurrentAccountCASA_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), retrieveCurrentAccountCASA_out.getTransactionInfo().getTransactionReturnMsg());
                    return null;
                }
            } else {
                Helper.writeLogError(this.getClass(), inputJson);
                Helper.writeLogError(this.getClass(), output);
                return null;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    */
 

}
