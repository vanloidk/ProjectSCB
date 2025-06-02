/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.billingvnpt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PaymentBillingVNPTRq")
public class PaymentBillingVNPTRq {

    private String action;
    private String version;
    private String partnerId;
    private String serviceId;
    private String serviceProviderId;
    private String paymentCode;
    private String channelId;
    private String transRequestId;
    private String additionalInfo;
    private String options;
    private String billAmount;
    private String billDetail;
    private String transDateTime;
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

    @XmlElement(name = "PaymentCode", nillable = true)
    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    @XmlElement(name = "ChannelId", nillable = true)
    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @XmlElement(name = "TransRequestId", nillable = true)
    public String getTransRequestId() {
        return transRequestId;
    }

    public void setTransRequestId(String transRequestId) {
        this.transRequestId = transRequestId;
    }

    @XmlElement(name = "AdditionalInfo", nillable = true)
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @XmlElement(name = "Options", nillable = true)
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @XmlElement(name = "BillAmount", nillable = true)
    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    @XmlElement(name = "BillDetail", nillable = true)
    public String getBillDetail() {
        return billDetail;
    }

    public void setBillDetail(String billDetail) {
        this.billDetail = billDetail;
    }

    @XmlElement(name = "TransDateTime", nillable = true)
    public String getTransDateTime() {
        return transDateTime;
    }

    public void setTransDateTime(String transDateTime) {
        this.transDateTime = transDateTime;
    }

    @XmlElement(name = "SecureCode", nillable = true)
    public String getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }
    
}
