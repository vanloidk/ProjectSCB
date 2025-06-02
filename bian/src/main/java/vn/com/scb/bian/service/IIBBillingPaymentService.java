/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import scb.com.vn.common.model.payment.RequestPayment;
import scb.com.vn.common.model.payment.ResponsePayment;
import scb.com.vn.message.Message;
import scb.com.vn.ultility.Xml;
import vn.com.scb.bian.utility.Helper;
import vn.com.scb.bian.utility.IIBUtils;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.scb.bian.utility.IIBContext;


    /**
    * @author scb
    * Thanh toan hoa don gom 2 step: Step 1: Call PartnerGateway de hach toan
    * core ( TH: PAY) va lay thong tin doi tac can goi Step 2 - 1: Goi doi tac
    * de lay bill Step 2 - 2: Goi doi tac de gach no    
    */
    

public class IIBBillingPaymentService extends IIBService{
    public static final String RESOURCE_BILLING_PARNTERGATEWAY = "/partnergateway/v1.0/xml/executePayBill";    
    
    /**
     * Step 1: Call PartnerGateway de hach toan core ( TH: PAY) va lay thong tin
     * doi tac can goi
     *
     * @param requestPayment
     * @return
     */
    public ResponsePayment callPartnerGateway(IIBContext iibContext,RequestPayment requestPayment) {
         String _strrequestbilling = "";
        try {            
            String _strresponsepay = "";
            ResponsePayment responsepay = null;
            HttpURLConnection conn = this.getHttpURLConnectionBilling(iibContext, RESOURCE_BILLING_PARNTERGATEWAY);
            _strrequestbilling = Xml.Marshaller(requestPayment);
            OutputStream os = conn.getOutputStream();
            os.write(_strrequestbilling.getBytes());
            os.flush();
            
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - HttpURLConnection:" + conn.getResponseCode());
                Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);            
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String line;
            while ((line = br.readLine()) != null) {
                _strresponsepay = _strresponsepay.concat(line);
            }
            conn.disconnect();

            if (_strresponsepay == null || _strresponsepay.isEmpty()) {
                responsepay = new ResponsePayment();
                responsepay.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());                
                responsepay.setResultMessage("IIBBillingPaymentService - callPartnerGateway - _strresponsepay - null " + _strrequestbilling);
            } else {                
                responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, _strresponsepay);
            }
            return responsepay;
        } catch (MalformedURLException e) {
            ResponsePayment responsepay = new ResponsePayment();
            responsepay.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());            
            responsepay.setResultMessage("IIBBillingPaymentService - callPartnerGateway:" + e.getMessage());
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);            
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway:" + e.getMessage());
            return responsepay;
        } catch (IOException e) {
            ResponsePayment responsepay = new ResponsePayment();
            responsepay.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
            responsepay.setResultMessage("IIBBillingPaymentService - callPartnerGateway:" + e.getMessage());     
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);            
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway:" + e.getMessage());
            return responsepay;
        } catch (Exception e) {
            ResponsePayment responsepay = new ResponsePayment();
            responsepay.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
            responsepay.setResultMessage("IIBBillingPaymentService - callPartnerGateway:" + e.getMessage());  
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);                        
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway:" + e.getMessage());
            return responsepay;
        }
    }

    /**
     * Step 2 - 1: Goi doi tac de lay bill
     *
     * @param requestPayment
     * @return
     */    
    public ResponsePayment queryPartner(IIBContext iibContext,ResponsePayment requestPayment) {
        String _strrequestbilling = "";
        try {
            //URL url = new URL(iibContext.getBillingServiceEndpoint().concat(requestPayment.getResponse().getIdpartner()));            
            String _strresponsepay = "";
            ResponsePayment responsepay = null;
            HttpURLConnection conn = this.getHttpURLConnectionBilling(iibContext, requestPayment.getResponse().getIdpartner());
            _strrequestbilling = Xml.Marshaller(requestPayment);
            OutputStream os = conn.getOutputStream();
            os.write(_strrequestbilling.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - HttpURLConnection:" + conn.getResponseCode());
                Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);            
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String line;
            while ((line = br.readLine()) != null) {
                _strresponsepay = _strresponsepay.concat(line);
            }
            conn.disconnect();

            if (_strresponsepay == null || _strresponsepay.isEmpty()) {
                responsepay = new ResponsePayment();
                responsepay.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
                responsepay.setResultMessage("IIBBillingPaymentService - queryPartner - null");
                Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);                        
                Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - queryPartner - null");
            } else {
                responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, _strresponsepay);                
            }
            return responsepay;
        } catch (MalformedURLException e) {
            ResponsePayment responsepay = new ResponsePayment();
            responsepay.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
            responsepay.setResultMessage("IIBBillingPaymentService - queryPartner:"+ e.getMessage());                  
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);            
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - queryPartner:"+ e.getMessage());
            return responsepay;
        } catch (IOException e) {
            ResponsePayment responsepay = new ResponsePayment();
            responsepay.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());            
            responsepay.setResultMessage("IIBBillingPaymentService - queryPartner:" + e.getMessage());            
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);            
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - queryPartner:"+ e.getMessage());
            return responsepay;
        } catch (Exception e) {
            ResponsePayment responsepay = new ResponsePayment();
            responsepay.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());            
            responsepay.setResultMessage("IIBBillingPaymentService - queryPartner:" + e.getMessage());            
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);            
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - queryPartner:"+ e.getMessage());
            return responsepay;
        }
    }

    /**
     * Step 2 - 2: Goi doi tac de gach no
     *
     * @param requestPayment
     * @param iibContext
     * @return
     */
    public ResponsePayment payPartner(IIBContext iibContext,ResponsePayment requestPayment) {    
        String _strrequestbilling = "";
         try {
            //URL url = new URL(iibContext.getBillingServiceEndpoint().concat(requestPayment.getResponse().getIdpartner()));            
            String _strresponsepay = "";
            ResponsePayment responsepay =null;
            HttpURLConnection conn = this.getHttpURLConnectionBilling(iibContext, requestPayment.getResponse().getIdpartner());
            _strrequestbilling = Xml.Marshaller(requestPayment);
            OutputStream os = conn.getOutputStream();
            os.write(_strrequestbilling.getBytes());
            os.flush();

            //if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - HttpURLConnection:" + conn.getResponseCode());
                Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);                        
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String line;
            while ((line = br.readLine()) != null) {
                _strresponsepay = _strresponsepay.concat(line);
            }
            conn.disconnect();

            if (_strresponsepay == null || _strresponsepay.isEmpty()) {
                responsepay = new ResponsePayment();
                responsepay.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
                responsepay.setResultMessage("IIBBillingPaymentService - payPartner - null");
            } else {
                responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, _strresponsepay);
            }
            return responsepay;
        } catch (MalformedURLException e) {
            ResponsePayment responsepay = new ResponsePayment();
            responsepay.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
            responsepay.setResultMessage("IIBBillingPaymentService - payPartner:"+ e.getMessage());            
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - payPartner:" + e.getMessage());
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);                        
            return responsepay;
        } catch (IOException e) {
            ResponsePayment responsepay = new ResponsePayment();
            responsepay.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
            responsepay.setResultMessage("IIBBillingPaymentService - payPartner:" + e.getMessage());            
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - payPartner:" + e.getMessage());
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);                        
            return responsepay;
        } catch (Exception e) {
            ResponsePayment responsepay = new ResponsePayment();
            responsepay.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
            responsepay.setResultMessage("IIBBillingPaymentService - payPartner:" + e.getMessage());    
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - payPartner:" + e.getMessage());
            Helper.writeLogError(this.getClass(), "IIBBillingPaymentService - callPartnerGateway - REQUEST:"+ _strrequestbilling);                        
            return responsepay;
        }
    }
}
