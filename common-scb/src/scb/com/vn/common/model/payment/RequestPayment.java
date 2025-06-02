/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phucnnd
 */
@XmlRootElement(name = "PAYMENT")
public class RequestPayment {

    private String channel;
    private String transcode;
    private String servicecode;
    private String providercode;
    private String processingcode;
    private String paymentmethod;
    private String accountno;
    private String province;
    private String datetime;
    private Request request;
    private RequestPrint requestPrint;
    private RequestCancelPay requestCancelPay;
    private CoreBankAccount coreBankAccount;
    private BENIFICIALINFO benificialinfo;
    
    private String dataInput;
   
    @XmlElement(name = "CHANNEL")
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @XmlElement(name = "TRANSCODE")
    public String getTranscode() {
        return transcode;
    }

    public void setTranscode(String transcode) {
        this.transcode = transcode;
    }

    @XmlElement(name = "SERVICECODE")
    public String getServicecode() {
        return servicecode;
    }

    public void setServicecode(String servicecode) {
        this.servicecode = servicecode;
    }
    
    @XmlElement(name = "REQUESTPRINT")
    public RequestPrint getRequestPrint() {
        return requestPrint;
    }

    public void setRequestPrint(RequestPrint requestPrint) {
        this.requestPrint = requestPrint;
    }
    
    @XmlElement(name = "REQUESTCANCELPAY")
    public RequestCancelPay getRequestCancelPay() {
        return requestCancelPay;
    }

    public void setRequestCancelPay(RequestCancelPay requestCancelPay) {
        this.requestCancelPay = requestCancelPay;
    }

    @XmlElement(name = "PROVIDERCODE")
    public String getProvidercode() {
        return providercode;
    }

    public void setProvidercode(String providercode) {
        this.providercode = providercode;
    }

    @XmlElement(name = "PROCESSINGCODE")
    public String getProcessingcode() {
        return processingcode;
    }

    public void setProcessingcode(String processingcode) {
        this.processingcode = processingcode;
    }

    @XmlElement(name = "PAYMENTMETHOD")
    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    @XmlElement(name = "ACCOUNTNO", required = false, nillable = true)
    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    @XmlElement(name = "DATETIME")
    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @XmlElement(name = "REQUEST")
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @XmlElement(name = "COREBANKACCOUNT")
    public CoreBankAccount getCoreBankAccount() {
        return coreBankAccount;
    }

    public void setCoreBankAccount(CoreBankAccount coreBankAccount) {
        this.coreBankAccount = coreBankAccount;
    }

    @XmlElement(name = "BENIFICIALINFO")
    public BENIFICIALINFO getBenificialinfo() {
        return benificialinfo;
    }

    public void setBenificialinfo(BENIFICIALINFO benificialinfo) {
        this.benificialinfo = benificialinfo;
    }
    
    @XmlElement(name = "PROVINCE", nillable = true)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    
    @XmlElement(name = "DATAINPUT", nillable = true)
    public String getDataInput() {
        return dataInput;
    }
    public void setDataInput(String dataInput) {
        this.dataInput = dataInput;
    }
}
