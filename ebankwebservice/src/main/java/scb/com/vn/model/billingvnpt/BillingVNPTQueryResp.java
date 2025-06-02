/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.billingvnpt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BillingVNPTQueryResp")
public class BillingVNPTQueryResp {
    
    private String responseCode;
    private String description;
    private String transRequestId;
    private String transResponseId;
    private String option;
    private String serviceId;
    private String paymentCode;
    private String billAmount;
    private String billFree;
    private String billDetail;
    private String secureCode;
    
    @XmlElement(name = "ResponseCode", nillable = true)
    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    
    @XmlElement(name = "Description", nillable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "TransRequestId", nillable = true)
    public String getTransRequestId() {
        return transRequestId;
    }

    public void setTransRequestId(String transRequestId) {
        this.transRequestId = transRequestId;
    }

    @XmlElement(name = "TransResponseId", nillable = true)
    public String getTransResponseId() {
        return transResponseId;
    }

    public void setTransResponseId(String transResponseId) {
        this.transResponseId = transResponseId;
    }

    @XmlElement(name = "Options", nillable = true)
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @XmlElement(name = "ServiceId", nillable = true)
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @XmlElement(name = "PaymentCode", nillable = true)
    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    @XmlElement(name = "BillAmount", nillable = true)
    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    @XmlElement(name = "BillFree", nillable = true)
    public String getBillFree() {
        return billFree;
    }

    public void setBillFree(String billFree) {
        this.billFree = billFree;
    }

    @XmlElement(name = "BillDetail", nillable = true)
    public String getBillDetail() {
        return billDetail;
    }

    public void setBillDetail(String billDetail) {
        this.billDetail = billDetail;
    }

    @XmlElement(name = "SecureCode", nillable = true)
    public String getSecureCode() {
        return secureCode;
    }
    
    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }
}
