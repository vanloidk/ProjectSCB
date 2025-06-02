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
public class ResponsePayment {

    private String transcode;
    private String result;
    private Response response;
    private String servicecode;
    private String providercode;
    private String processingcode;
    private String paymentmethod;
    private String accountno;
    private String datetime;
    private String partnercode;
    private String refcore;
    private Request request;
    private String resultMessage;
    private String channel;
    private String ticketCode;
    private CoreBankAccount coreBankAccount;
    private BENIFICIALINFO benificialinfo;

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

    @XmlElement(name = "RESPONSE", required = false, nillable = true)
    public Response getResponse() {
        if (response == null) {
            Response r = new Response();
            response = r;
            return response;
        }
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
    
    @XmlElement(name = "SERVICECODE")
    public String getServicecode() {
        return servicecode;
    }

    public void setServicecode(String servicecode) {
        this.servicecode = servicecode;
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

    /**
     * @return the partnercode
     */
    @XmlElement(name = "PARTNERCODE")
    public String getPartnercode() {
        return partnercode;
    }

    /**
     * @param partnercode the partnercode to set
     */
    public void setPartnercode(String partnercode) {
        this.partnercode = partnercode;
    }
    
    @XmlElement(name = "REQUEST")
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * @return the refcore
     */
    @XmlElement(name = "REF_CORE", required = false, nillable = true)
    public String getRefcore() {
        return refcore;
    }

    /**
     * @param refcore the refcore to set
     */
    public void setRefcore(String refcore) {
        this.refcore = refcore;
    }

    /**
     * @return the resultMessage
     */
        @XmlElement(name = "RESULTMESSAGE", required = false, nillable = true)
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * @param resultMessage the resultMessage to set
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    
    @XmlElement(name = "CHANNEL")
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @XmlElement(name = "COREBANKACCOUNT")
    public CoreBankAccount getCoreBankAccount() {
        return coreBankAccount;
    }

    @XmlElement(name = "BENIFICIALINFO")
    public BENIFICIALINFO getBenificialinfo() {
        return benificialinfo;
    }

    @XmlElement(name = "TICKETCODE")
    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }
    
    

    public void setCoreBankAccount(CoreBankAccount coreBankAccount) {
        this.coreBankAccount = coreBankAccount;
    }

    public void setBenificialinfo(BENIFICIALINFO benificialinfo) {
        this.benificialinfo = benificialinfo;
    }
    
}
