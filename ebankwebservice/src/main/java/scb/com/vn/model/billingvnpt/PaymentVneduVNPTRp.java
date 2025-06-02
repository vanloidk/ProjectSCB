/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.billingvnpt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PAYMENT")
public class PaymentVneduVNPTRp {

    private String partnercode;
    private String providercode;
    private String processingcode;
    private String accountno;
    private String refcore;
    private String result;
    private String resultMessage;
    private String transDatetime;
    private String transcode;
    private String responseCode;
    private String options;
    private String secureCode;

    @XmlElement(name = "PARTNERCODE", required = false, nillable = true)
    public String getPartnercode() {
        return partnercode;
    }

    public void setPartnercode(String partnercode) {
        this.partnercode = partnercode;
    }

    @XmlElement(name = "PROVIDERCODE", required = false, nillable = true)
    public String getProvidercode() {
        return providercode;
    }

    public void setProvidercode(String providercode) {
        this.providercode = providercode;
    }

    @XmlElement(name = "PROCESSINGCODE", required = false, nillable = true)
    public String getProcessingcode() {
        return processingcode;
    }

    public void setProcessingcode(String processingcode) {
        this.processingcode = processingcode;
    }

    @XmlElement(name = "ACCOUNTNO", required = false, nillable = true)
    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    @XmlElement(name = "REF_CORE", required = false, nillable = true)
    public String getRefcore() {
        return refcore;
    }

    public void setRefcore(String refcore) {
        this.refcore = refcore;
    }

    @XmlElement(name = "RESULT", required = false, nillable = true)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @XmlElement(name = "RESULTMESSAGE", required = false, nillable = true)
    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    @XmlElement(name = "DATETIME", required = false, nillable = true)
    public String getTransDatetime() {
        return transDatetime;
    }

    public void setTransDatetime(String transDatetime) {
        this.transDatetime = transDatetime;
    }

    @XmlElement(name = "TRANSCODE", required = false, nillable = true)
    public String getTranscode() {
        return transcode;
    }

    public void setTranscode(String transcode) {
        this.transcode = transcode;
    }

    @XmlElement(name = "RESPONSECODE", required = false, nillable = true)
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @XmlElement(name = "OPTIONS", required = false, nillable = true)
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @XmlElement(name = "SECURECODE", required = false, nillable = true)
    public String getSecureCode() {
        return secureCode;
    }
    
    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }
}
