/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.card;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.message.Message;
import scb.com.vn.utility.MessageMB;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "BankCardDtlRp")
public class BankCardDtlRp {

    private String errorCode;
    private String errorMessage;
    private String cardType;
    private String cardName;
    private String cardNumber;
    private String issuedOrExpiredDate;
    private String cvcNumber;

    @XmlElement(name = "ErrorCode")
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @XmlElement(name = "ErrorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @XmlElement(name = "CardType", nillable = true)
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @XmlElement(name = "CardName", nillable = true)
    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @XmlElement(name = "CardNumber", nillable = true)
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @XmlElement(name = "IssuedOrExpiredDate", nillable = true)
    public String getIssuedOrExpiredDate() {
        return issuedOrExpiredDate;
    }

    public void setIssuedOrExpiredDate(String issuedOrExpiredDate) {
        this.issuedOrExpiredDate = issuedOrExpiredDate;
    }

    @XmlElement(name = "CVCNumber", nillable = true)
    public String getCvcNumber() {
        return cvcNumber;
    }

    public void setCvcNumber(String cvcNumber) {
        this.cvcNumber = cvcNumber;
    }
    
    /**
     *
     * @param msg
     * @return
     */
    public BankCardDtlRp getMBResponse(Message.PaymentResultEnum msg) {
        this.setErrorCode(msg.getValue());
        this.setErrorMessage(Message.getMessagePaymentResult(msg));
        return this;
    }

    /**
     *
     * @param msg
     * @return
     */
    public BankCardDtlRp getMBResponse(MessageMB.MobileResultEnum msg) {
        this.setErrorCode(msg.getValue());
        this.setErrorMessage(MessageMB.getMessageMBResult(msg));
        return this;
    }
    
}
