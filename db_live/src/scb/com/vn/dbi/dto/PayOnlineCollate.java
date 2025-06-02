/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
public class PayOnlineCollate implements java.io.Serializable {

    private static final long serialVersionUID = -3749324374371216317L;
    String PartnerID;
    String ResultCollate;
    String TypeTrans;
    String CCY;
    BigDecimal Amount;
    String Transid;
    String Transdate;//ddMMyyyyhhmmss
    String Merchantid;
    String BankTransid;
    String Responsecode;
    String Cardnumber;
    String Typedevice;
    String PaymentTransid;
    String TypeService;
    String Mac;
    String FILENAME;

    /**
     *
     * @return
     */
    public String getPartnerID() {
        return PartnerID;
    }

    /**
     *
     * @param PartnerID
     */
    public void setPartnerID(String PartnerID) {
        this.PartnerID = PartnerID;
    }

    /**
     *
     * @return
     */
    public String getResultCollate() {
        return ResultCollate;
    }

    /**
     *
     * @param ResultCollate
     */
    public void setResultCollate(String ResultCollate) {
        this.ResultCollate = ResultCollate;
    }

    /**
     *
     * @return
     */
    public String getTypeTrans() {
        return TypeTrans;
    }

    /**
     *
     * @param TypeTrans
     */
    public void setTypeTrans(String TypeTrans) {
        this.TypeTrans = TypeTrans;
    }

    /**
     *
     * @return
     */
    public String getCCY() {
        return CCY;
    }

    /**
     *
     * @param CCY
     */
    public void setCCY(String CCY) {
        this.CCY = CCY;
    }

    /**
     *
     * @return
     */
    public BigDecimal getAmount() {
        return Amount;
    }

    /**
     *
     * @param Amount
     */
    public void setAmount(BigDecimal Amount) {
        this.Amount = Amount;
    }

    /**
     *
     * @return
     */
    public String getTransid() {
        return Transid;
    }

    /**
     *
     * @param Transid
     */
    public void setTransid(String Transid) {
        this.Transid = Transid;
    }

    /**
     *
     * @return
     */
    public String getTransdate() {
        return Transdate;
    }

    /**
     *
     * @param Transdate
     */
    public void setTransdate(String Transdate) {
        this.Transdate = Transdate;
    }

    /**
     *
     * @return
     */
    public String getMerchantid() {
        return Merchantid;
    }

    /**
     *
     * @param Merchantid
     */
    public void setMerchantid(String Merchantid) {
        this.Merchantid = Merchantid;
    }

    /**
     *
     * @return
     */
    public String getBankTransid() {
        return BankTransid;
    }

    /**
     *
     * @param BankTransid
     */
    public void setBankTransid(String BankTransid) {
        this.BankTransid = BankTransid;
    }

    /**
     *
     * @return
     */
    public String getResponsecode() {
        return Responsecode;
    }

    /**
     *
     * @param Responsecode
     */
    public void setResponsecode(String Responsecode) {
        this.Responsecode = Responsecode;
    }

    /**
     *
     * @return
     */
    public String getCardnumber() {
        return Cardnumber;
    }

    /**
     *
     * @param Cardnumber
     */
    public void setCardnumber(String Cardnumber) {
        this.Cardnumber = Cardnumber;
    }

    /**
     *
     * @return
     */
    public String getTypedevice() {
        return Typedevice;
    }

    /**
     *
     * @param Typedevice
     */
    public void setTypedevice(String Typedevice) {
        this.Typedevice = Typedevice;
    }

    /**
     *
     * @return
     */
    public String getPaymentTransid() {
        return PaymentTransid;
    }

    /**
     *
     * @param PaymentTransid
     */
    public void setPaymentTransid(String PaymentTransid) {
        this.PaymentTransid = PaymentTransid;
    }

    /**
     *
     * @return
     */
    public String getTypeService() {
        return TypeService;
    }

    /**
     *
     * @param TypeService
     */
    public void setTypeService(String TypeService) {
        this.TypeService = TypeService;
    }

    /**
     *
     * @return
     */
    public String getMac() {
        return Mac;
    }

    /**
     *
     * @param Mac
     */
    public void setMac(String Mac) {
        this.Mac = Mac;
    }

    /**
     *
     * @return
     */
    public String getFILENAME() {
        return FILENAME;
    }

    /**
     *
     * @param FILENAME
     */
    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
    }

}
