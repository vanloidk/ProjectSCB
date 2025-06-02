/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "REQUESTCARDVERIFY")
public class RequestCardVerify {

    String CommandCode;
    String TransID;
    String CardNumber;
    String CardHolderName;
    String CardDate; //dd/MM/yyyy
    String MerchantId;
    String ProviderID;
    String Amount;
    String CURRENCYCODE;
    String Language;
    String ClientID;
    String LocalDateTime;
    String AddInfo;
    String MAC;

    /**
     *
     */
    public RequestCardVerify() {
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "COMMANDCODE")
    public String getCommandCode() {
        return this.CommandCode;
    }

    /**
     *
     * @param CommandCode
     */
    public void setCommandCode(String CommandCode) {
        this.CommandCode = CommandCode;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "TRANSID")
    public String getTransID() {
        return this.TransID;
    }

    /**
     *
     * @param TransID
     */
    public void setTransID(String TransID) {
        this.TransID = TransID;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CARDNUMBER")
    public String getCardNumber() {
        return this.CardNumber;
    }

    /**
     *
     * @param CardNumber
     */
    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CARDHOLDERNAME")
    public String getCardHolderName() {
        return this.CardHolderName;
    }

    /**
     *
     * @param CardHolderName
     */
    public void setCardHolderName(String CardHolderName) {
        this.CardHolderName = CardHolderName;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CARDDATE")
    public String getCardDate() {
        return this.CardDate;
    }

    /**
     *
     * @param CardDate
     */
    public void setCardDate(String CardDate) {
        this.CardDate = CardDate;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MERCHANTID")
    public String getMerchantId() {
        return this.MerchantId;
    }

    /**
     *
     * @param MerchantId
     */
    public void setMerchantId(String MerchantId) {
        this.MerchantId = MerchantId;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PROVIDERID")
    public String getProviderID() {
        return this.ProviderID;
    }

    /**
     *
     * @param ProviderID
     */
    public void setProviderID(String ProviderID) {
        this.ProviderID = ProviderID;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AMOUNT")

    public String getAmount() {
        return this.Amount;
    }

    /**
     *
     * @param Amount
     */
    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CURRENCYCODE")
    public String getCURRENCYCODE() {
        return this.CURRENCYCODE;
    }

    /**
     *
     * @param CURRENCYCODE
     */
    public void setCURRENCYCODE(String CURRENCYCODE) {
        this.CURRENCYCODE = CURRENCYCODE;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "LANGUAGE")

    public String getLanguage() {
        return this.Language;
    }

    /**
     *
     * @param Language
     */
    public void setLanguage(String Language) {
        this.Language = Language;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CLIENTID")

    public String getClientID() {
        return this.ClientID;
    }

    /**
     *
     * @param ClientID
     */
    public void setClientID(String ClientID) {
        this.ClientID = ClientID;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "LOCALDATETIME")

    public String getLocalDateTime() {
        return this.LocalDateTime;
    }

    /**
     *
     * @param LocalDateTime
     */
    public void setLocalDateTime(String LocalDateTime) {
        this.LocalDateTime = LocalDateTime;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ADDINFO")

    public String getAddInfo() {
        return this.AddInfo;
    }

    /**
     *
     * @param AddInfo
     */
    public void setAddInfo(String AddInfo) {
        this.AddInfo = AddInfo;
    }

    /**
     *
     * @param MAC
     */
    @XmlElement(name = "MAC")
    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    /**
     *
     * @return
     */
    public String getMAC() {
        return this.MAC;
    }

}
