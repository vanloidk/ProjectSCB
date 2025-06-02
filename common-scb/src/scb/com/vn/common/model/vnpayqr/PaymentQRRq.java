/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.vnpayqr;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.message.CommonMessage.CommontEnum;
import scb.com.vn.ultility.Common;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "PaymentQRRq")
public class PaymentQRRq implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String clientId;
    private String traceTransfer;
    private String payCode;
    private String payDate;
    private String additionalData;
    private String messageType;
    private String realAmount;
    private String rate;
    private String description;
    private String partner;

    @XmlElement(name = "ClientId", nillable = true)
    public String getClientId() {
        return clientId;
    }

    @XmlElement(name = "TraceTransfer", nillable = true)
    public String getTraceTransfer() {
        return traceTransfer;
    }
    
    @XmlElement(name = "PayCode", nillable = true)
    public String getPayCode() {
        return payCode;
    }

    @XmlElement(name = "PayDate", nillable = true)
    public String getPayDate() {
        return payDate;
    }

    @XmlElement(name = "AdditionalData", nillable = true)
    public String getAdditionalData() {
        return additionalData;
    }

    @XmlElement(name = "MessageType", nillable = true)
    public String getMessageType() {
        return messageType;
    }

    @XmlElement(name = "RealAmount", nillable = true)
    public String getRealAmount() {
        return realAmount;
    }

    @XmlElement(name = "Rate", nillable = false)
    public String getRate() {
        return rate;
    }

    @XmlElement(name = "Description", nillable = true)
    public String getDescription() {
        return description;
    }

    @XmlElement(name = "Partner", nillable = true)
    public String getPartner() {
        return partner;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setTraceTransfer(String traceTransfer) {
        this.traceTransfer = traceTransfer;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }
    
    public CommontEnum isValidate() {
        if (!Common.validateStringValue(clientId, 20)) {
            return CommontEnum.INVALID_CLIENTID;
        }
        if (!Common.validateStringValue(payCode, 20)) {
            return CommontEnum.INVALID_PAYCODE;
        }
        if (!Common.validateStringValue(payDate, 14)) {
            return CommontEnum.INVALID_PAYDATE;
        }
        /*
        if (!Common.validateStringValue(additionalData, 500)) {
            return CommontEnum.INVALID_ADDITIONALDATA;
        }*/
        if (!Common.validateStringValue(messageType, 2)) {
            return CommontEnum.INVALID_MESSAGETYPE;
        }
        if (!Common.validateStringValue(realAmount, 20)) {
            return CommontEnum.INVALID_REALAMOUNT;
        }
        if (!Common.validateStringValue(partner, 50)) {
            return CommontEnum.INVALID_PARTNER;
        }
        if (!Common.validateStringValue(description, 200)) {
            return CommontEnum.INVALID_DESCRIPTION;
        }
        return CommontEnum.DATA_IS_VALID;
    }
}