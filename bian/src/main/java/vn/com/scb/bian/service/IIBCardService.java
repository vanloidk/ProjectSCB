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
public class IIBCardService extends IIBService{
    public static final String RESOURCE_SELECT_CREDITCARD = "/creditcard/v1.0/rest/selectCreditCardSumary";
    public static final String RESOURCE_RETRIEVE_CREDITCARD = "/creditcard/v1.0/rest/retrieveCreditCardDetail";   
    /**
     * 
     * Lay chi tiet tai khoan thẻ it tham so    
     * @param iibContext
     * @param cardPaneID
     * @param channel
     * @return 
     */
    public RetrieveCreditCardDetail_out_Type retrieveCreditCardDetailSimple(IIBContext iibContext, String cardPaneID, String channel) throws BianException {
        String inputJson = "";
        try {                       
            RetrieveCreditCardDetail_in_Type retrieveCreditCardDetail_in = new RetrieveCreditCardDetail_in_Type();

            CardInfoType cardinfo = new CardInfoType();
            cardinfo.setCardPaneID(cardPaneID);
            retrieveCreditCardDetail_in.setCardInfo(cardinfo);

            retrieveCreditCardDetail_in.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(channel));

            RetrieveCreditCardDetail_in_Type.Container container = new RetrieveCreditCardDetail_in_Type.Container();
            container.retrieveCreditCardDetail_in = retrieveCreditCardDetail_in;

            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_RETRIEVE_CREDITCARD);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "RetrieveCreditCardDetail_out_Type:" + inputJson);
                Helper.writeLogError(this.getClass(), "RetrieveCreditCardDetail_out_Type: Failed : HTTP error code :" + conn.getResponseCode());                
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
            RetrieveCreditCardDetail_out_Type.Container retrieveCreditCardDetail_out_con = gson.fromJson(output, RetrieveCreditCardDetail_out_Type.Container.class);
          
            if (retrieveCreditCardDetail_out_con != null) {
                RetrieveCreditCardDetail_out_Type retrieveCreditCardDetail_out_Type = retrieveCreditCardDetail_out_con.retrieveCreditCardDetail_out;
                if (retrieveCreditCardDetail_out_Type.getTransactionInfo() != null && retrieveCreditCardDetail_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return retrieveCreditCardDetail_out_Type;
                } else {
                    Helper.writeLogError(this.getClass(), "retrieveCreditCardDetail_out_Type:" + inputJson);
                    Helper.writeLogError(this.getClass(), "retrieveCreditCardDetail_out_Type:" + output);
                    Helper.writeLogError(this.getClass(), "retrieveCreditCardDetail_out_Type:" + retrieveCreditCardDetail_out_Type.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), "retrieveCreditCardDetail_out_Type:" + retrieveCreditCardDetail_out_Type.getTransactionInfo().getTransactionReturnMsg());
                    return retrieveCreditCardDetail_out_Type;
                }
            } else {
                Helper.writeLogError(this.getClass(), "retrieveCreditCardDetail_out_Type - in:" + inputJson);                
                Helper.writeLogError(this.getClass(), "retrieveCreditCardDetail_out_Type - out null" );                
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "retrieveCreditCardDetail_out_Type - null");
            }            
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "RetrieveCreditCardDetail_out_Type - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "RetrieveCreditCardDetail_out_Type - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "RetrieveCreditCardDetail_out_Type - Exception:" + e.toString());
        }
    }
  
    /**
     * 
     * Lay danh sach tai khoan thẻ
     * @param iibContext
     * @param cifNum
     * @param cardAccountNum
     * @param channel
     * @return 
     */
    
       public SelectCreditCardSumary_out_Type selectCreditCardSumarySimple(IIBContext iibContext, String cifNum,String cardAccountNum, String channel) throws BianException{
        String inputJson = "";
        try {                       
            SelectCreditCardSumary_in_Type selectCreditCardSumary_in = new SelectCreditCardSumary_in_Type();            
            if (cifNum!=null && cifNum.length()>0){
                CIFDataType cifInfo = new CIFDataType();
                cifInfo.setCIFNum(cifNum);                
                selectCreditCardSumary_in.setCIFInfo(cifInfo);
            }
            
            if (cardAccountNum!=null && cardAccountNum.length()>0){
                CardInfoType cardInfo = new CardInfoType();
                cardInfo.setCardAccountNum(cardAccountNum);
                selectCreditCardSumary_in.setCardInfo(cardInfo);
            }
                        
            selectCreditCardSumary_in.setTransactionInfo(IIBUtils.getTransInfpTypeCommon(channel));

            SelectCreditCardSumary_in_Type.Container container = new SelectCreditCardSumary_in_Type.Container();
            container.selectCreditCardSumary_in = selectCreditCardSumary_in;
            HttpURLConnection conn = this.getHttpURLConnection(iibContext, RESOURCE_SELECT_CREDITCARD);
            Gson gson = new Gson();
            inputJson = gson.toJson(container);
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "selectCreditCardSumarySimple:" + inputJson);
                Helper.writeLogError(this.getClass(), "selectCreditCardSumarySimple: Failed : HTTP error code :" + conn.getResponseCode());                
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
            SelectCreditCardSumary_out_Type.Container selectCreditCardSumary_out_con = gson.fromJson(output, SelectCreditCardSumary_out_Type.Container.class);          
            if (selectCreditCardSumary_out_con != null) {
                SelectCreditCardSumary_out_Type selectCreditCardSumary_out_Type = selectCreditCardSumary_out_con.selectCreditCardSumary_out;
                if (selectCreditCardSumary_out_Type.getTransactionInfo() != null && selectCreditCardSumary_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return selectCreditCardSumary_out_Type;
                } else {
                    Helper.writeLogError(this.getClass(), "selectCreditCardSumary_out_Type:" + inputJson.concat(output));                    
                    Helper.writeLogError(this.getClass(), "selectCreditCardSumary_out_Type:" + selectCreditCardSumary_out_Type.getTransactionInfo().getTransactionReturn().toString().concat(selectCreditCardSumary_out_Type.getTransactionInfo().getTransactionReturnMsg()));                    
                    return selectCreditCardSumary_out_Type;
                }
            } else {                
                Helper.writeLogError(this.getClass(), "selectCreditCardSumary_out_Type - in:" + inputJson);                
                Helper.writeLogError(this.getClass(), "selectCreditCardSumary_out_Type - out null");                
                throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectCreditCardSumary_out_Type - null");
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "selectCreditCardSumary_out_Type - Exception:" + inputJson);
            Helper.writeLogError(this.getClass(), "selectCreditCardSumary_out_Type - Exception:" + e.toString());
            throw new BianException(IIBConstants.TRANSACTION_ERROR_SYSTEM, "selectCreditCardSumary_out_Type - Exception:" + e.toString());
        }
    }
  
}
