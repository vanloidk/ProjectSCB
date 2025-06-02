package scb.com.vn.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "REQUESTREFUNDONLTRANS")
public class RequestRefundOnlTrans {

    String CommandCode;
    String TransID;
    String MerchantId;
    String ProviderID;
    String Amount;
    String CURRENCYCODE;
    String RefundTransID;
    String LocalDateTime;
    String AddInfo;
    String MAC;

    /**
     *
     */
    public RequestRefundOnlTrans() {
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
    @XmlElement(name = "REFUNDTRANSID")
    public String getRefundTransID() {
        return this.RefundTransID;
    }

    /**
     *
     * @param RefundTransID
     */
    public void setRefundTransID(String RefundTransID) {
        this.RefundTransID = RefundTransID;
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
