/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.billingvnpt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.payment.Response;

@XmlRootElement(name = "PAYMENT")
public class BillingVneduRp {

    private String result;
    private String resultMessage;
    private String transcode;
    private String responseCode;
    private String options;
    private String servicecode;
    private String paymentcode;
    private String billAmount;
    private String billFree;
    private String billDetails;
    private String SecureCode;

    @XmlElement(name = "TRANSCODE", required = false, nillable = true)
    public String getTranscode() {
        return transcode;
    }

    public void setTranscode(String transcode) {
        this.transcode = transcode;
    }

    @XmlElement(name = "RESULT", required = false, nillable = true)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @XmlElement(name = "SERVICECODE", required = false, nillable = true)
    public String getServicecode() {
        return servicecode;
    }

    public void setServicecode(String servicecode) {
        this.servicecode = servicecode;
    }

    @XmlElement(name = "RESULTMESSAGE", required = false, nillable = true)
    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    @XmlElement(name = "BILLDETAILS")
    public String getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(String billDetails) {
        this.billDetails = billDetails;
    }

    @XmlElement(name = "RESPONSECODE")
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @XmlElement(name = "OPTIONS")
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @XmlElement(name = "PAYMENTCODE")
    public String getPaymentcode() {
        return paymentcode;
    }

    public void setPaymentcode(String paymentcode) {
        this.paymentcode = paymentcode;
    }

    @XmlElement(name = "BILLAMOUNT")
    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    @XmlElement(name = "BILLFREE")
    public String getBillFree() {
        return billFree;
    }

    public void setBillFree(String billFree) {
        this.billFree = billFree;
    }

    @XmlElement(name = "SECURECODE")
    public String getSecureCode() {
        return SecureCode;
    }

    public void setSecureCode(String SecureCode) {
        this.SecureCode = SecureCode;
    }

}
