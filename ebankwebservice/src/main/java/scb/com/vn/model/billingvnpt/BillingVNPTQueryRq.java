/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.billingvnpt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "BillingVNPTQueryRq")
public class BillingVNPTQueryRq {

    private String action;
    private String version;
    private String partnerId;
    private String serviceId;
    private String serviceProviderId;
    private String chanelId;
    private String paymentCode;
    private String transDateTime;
    private String transRequestId;
    private String additionInfo;
    private String secureCode;
    
    @XmlElement(name = "Action", nillable = true)
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @XmlElement(name = "Version", nillable = true)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlElement(name = "PartnerId", nillable = true)
    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @XmlElement(name = "ServiceId", nillable = true)
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @XmlElement(name = "ServiceProviderId", nillable = true)
    public String getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    @XmlElement(name = "ChanelId", nillable = true)
    public String getChanelId() {
        return chanelId;
    }

    public void setChanelId(String chanelId) {
        this.chanelId = chanelId;
    }

    @XmlElement(name = "PaymentCode", nillable = true)
    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    @XmlElement(name = "TransDateTime", nillable = true)
    public String getTransDateTime() {
        return transDateTime;
    }

    public void setTransDateTime(String transDateTime) {
        this.transDateTime = transDateTime;
    }

    @XmlElement(name = "TransRequestId", nillable = true)
    public String getTransRequestId() {
        return transRequestId;
    }

    public void setTransRequestId(String transRequestId) {
        this.transRequestId = transRequestId;
    }

    @XmlElement(name = "AdditionInfo", nillable = true)
    public String getAdditionInfo() {
        return additionInfo;
    }

    public void setAdditionInfo(String additionInfo) {
        this.additionInfo = additionInfo;
    }
    
    @XmlElement(name = "SecureCode", nillable = true)
    public String getSecureCode() {
        return secureCode;
    }
    
    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }
}
