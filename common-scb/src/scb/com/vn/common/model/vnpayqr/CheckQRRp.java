/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.vnpayqr;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.MobileResponse;
import scb.com.vn.message.CommonMessage;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "CheckQRRp")
public class CheckQRRp extends MobileResponse implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String clientId;
    private String payCode;
    private String resCode;
    private String resDesc;
    private String additionalData;
    private String promotionValue;
    private String providerCode;
    private String serviceCode;
    private String customerName;
    private String customerAddress;
    private String totalAmount;

    @XmlElement(name = "ClientId", nillable = true)
    public String getClientId() {
        return clientId;
    }

    @XmlElement(name = "PayCode", nillable = true)
    public String getPayCode() {
        return payCode;
    }

    @XmlElement(name = "ResCode", nillable = true)
    public String getResCode() {
        return resCode;
    }

    @XmlElement(name = "ResDesc", nillable = true)
    public String getResDesc() {
        return resDesc;
    }

    @XmlElement(name = "AdditionalData", nillable = true)
    public String getAdditionalData() {
        return additionalData;
    }

    @XmlElement(name = "PromotionValue", nillable = true)
    public String getPromotionValue() {
        return promotionValue;
    }

    @XmlElement(name = "ProviderCode", nillable = true)
    public String getProviderCode() {
        return providerCode;
    }

    @XmlElement(name = "ServiceCode", nillable = true)
    public String getServiceCode() {
        return serviceCode;
    }

    @XmlElement(name = "CustomerName", nillable = true)
    public String getCustomerName() {
        return customerName;
    }

    @XmlElement(name = "CustomerAddress", nillable = true)
    public String getCustomerAddress() {
        return customerAddress;
    }

    @XmlElement(name = "TotalAmount", nillable = true)
    public String getTotalAmount() {
        return totalAmount;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public void setPromotionValue(String promotionValue) {
        this.promotionValue = promotionValue;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public void setupBeforeReturn(String clientId, String payCode) {
        this.clientId = clientId;
        this.payCode = payCode;
        setupEmpty();
    }
    
    private String setupEmpty(String value) {
        return value != null ? value : "";
    }
    
    private void setupEmpty() {
        if (resCode == null) {
            resCode = "";
        }
        if (resDesc == null) {
            resDesc = "";
        }
        if (additionalData == null) {
            additionalData = "";
        }
        if (promotionValue == null) {
            promotionValue = "";
        }
        if (providerCode == null) {
            providerCode = "";
        }
        if (serviceCode == null) {
            serviceCode = "";
        }
        if (customerName == null) {
            customerName = "";
        }
        if (customerAddress == null) {
            customerAddress = "";
        }
        if (totalAmount == null) {
            totalAmount = "";
        }
    }
    
    @Override
    public MobileResponse getMBResponse(CommonMessage.CommontEnum msg)
    {
        this.resCode = msg.getValue();
        this.resDesc = CommonMessage.getCommontEnumDescription(msg);
        return this;
    }
}