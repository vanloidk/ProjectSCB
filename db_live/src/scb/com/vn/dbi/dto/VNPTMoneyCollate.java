/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.math.BigDecimal;

public class VNPTMoneyCollate implements java.io.Serializable {

    private static final long serialVersionUID = -1L;

    String PartnerID;
    String ResultCollate;
    String ResultConfirm;
    String CCY;
    BigDecimal Amount;
    String BankTransid;
    String Transid;
    String IdServiceType;
    String Transdate; //ddMMyyyyhhmmss
    String AddInfo;
    String Mac;
    String Merchantid;
    String Cardnumber;
    String PaymentTransid;
    String Responsecode;
    String TypeTrans;
    String FileName;
    String PartnerCollated;

    public String getPartnerID() {
        return PartnerID;
    }

    public void setPartnerID(String PartnerID) {
        this.PartnerID = PartnerID;
    }

    public String getResultCollate() {
        return ResultCollate;
    }

    public void setResultCollate(String ResultCollate) {
        this.ResultCollate = ResultCollate;
    }

    public String getResultConfirm() {
        return ResultConfirm;
    }

    public void setResultConfirm(String ResultConfirm) {
        this.ResultConfirm = ResultConfirm;
    }

    public String getCCY() {
        return CCY;
    }

    public void setCCY(String CCY) {
        this.CCY = CCY;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal Amount) {
        this.Amount = Amount;
    }

    public String getBankTransid() {
        return BankTransid;
    }

    public void setBankTransid(String BankTransid) {
        this.BankTransid = BankTransid;
    }

    public String getTransid() {
        return Transid;
    }

    public void setTransid(String Transid) {
        this.Transid = Transid;
    }

    public String getIdServiceType() {
        return IdServiceType;
    }

    public void setIdServiceType(String IdServiceType) {
        this.IdServiceType = IdServiceType;
    }

    public String getTransdate() {
        return Transdate;
    }

    public void setTransdate(String Transdate) {
        this.Transdate = Transdate;
    }

    public String getAddInfo() {
        return AddInfo;
    }

    public void setAddInfo(String AddInfo) {
        this.AddInfo = AddInfo;
    }

    public String getMac() {
        return Mac;
    }

    public void setMac(String Mac) {
        this.Mac = Mac;
    }

    public String getMerchantid() {
        return Merchantid;
    }

    public void setMerchantid(String Merchantid) {
        this.Merchantid = Merchantid;
    }

    public String getCardnumber() {
        return Cardnumber;
    }

    public void setCardnumber(String Cardnumber) {
        this.Cardnumber = Cardnumber;
    }

    public String getPaymentTransid() {
        return PaymentTransid;
    }

    public void setPaymentTransid(String PaymentTransid) {
        this.PaymentTransid = PaymentTransid;
    }

    public String getResponsecode() {
        return Responsecode;
    }

    public void setResponsecode(String Responsecode) {
        this.Responsecode = Responsecode;
    }

    public String getTypeTrans() {
        return TypeTrans;
    }

    public void setTypeTrans(String TypeTrans) {
        this.TypeTrans = TypeTrans;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getPartnerCollated() {
        return PartnerCollated;
    }

    public void setPartnerCollated(String PartnerCollated) {
        this.PartnerCollated = PartnerCollated;
    }
}
