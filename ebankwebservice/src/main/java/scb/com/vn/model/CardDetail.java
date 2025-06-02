/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author KimNCM
 */
public class CardDetail {

    private String cardno;
    private String cardaccountnumber;
    private String cardname;
    private String accountno;
    private String masterslave;

    private String AccountBranchOpen;
    //private String ExpireDate;
    private String expire_date;

    private String e_name;
    private String dunocuoiky;
    private String ngaytttoithieu;
    private String dunodasudung;
    private String thanhtoantoithieu;
    private String hanmucconlai;
    private String tinhtrangthe;
    private String blockonline;
    private String brn;
    private String reasoncde;
    private String primarycif;
    private String sumAmtLimitPay;
    private String sumPaidAmount;
    private String sumPrincipalToPay;
    private String cardIPPStatementAmount;
    private String cardIPPCurrentAmount;
    private String imageid;
    private String cardNoHidden;
    private String cardactive;

    /**
     *
     */
    public CardDetail() {
    }

    /**
     * @return the cardno
     */
    @XmlElement(name = "CardNo")
    public String getCardno() {
        return cardno;
    }

    /**
     * @param cardno the cardno to set
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    /**
     * @return the cardaccountnumber
     */
    @XmlElement(name = "CardAccountNumer")
    public String getCardaccountnumber() {
        return cardaccountnumber;
    }

    /**
     * @param cardaccountnumber the cardaccountnumber to set
     */
    public void setCardaccountnumber(String cardaccountnumber) {
        this.cardaccountnumber = cardaccountnumber;
    }

    /**
     * @return the e_name
     */
    @XmlElement(name = "CustomerName", nillable = true)
    public String getE_name() {
        return e_name;
    }

    /**
     * @param e_name the e_name to set
     */
    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    /**
     * @return the accountno
     */
    @XmlElement(name = "AccountNo")
    public String getAccountno() {
        return accountno;
    }

    /**
     * @param accountno the accountno to set
     */
    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    /**
     * @return the masterslave
     */
    @XmlElement(name = "MasterSlave")
    public String getMasterslave() {
        return masterslave;
    }

    /**
     * @param masterslave the masterslave to set
     */
    public void setMasterslave(String masterslave) {
        this.masterslave = masterslave;
    }

    /**
     * @return the AccountBranchOpen
     */
    @XmlElement(name = "AccountBranchOpen")
    public String getAccountBranchOpen() {
        return AccountBranchOpen;
    }

    /**
     * @param AccountBranchOpen the AccountBranchOpen to set
     */
    public void setAccountBranchOpen(String AccountBranchOpen) {
        this.AccountBranchOpen = AccountBranchOpen;
    }

    /**
     * @return the expire_date
     */
    @XmlElement(name = "ExpireDate")
    public String getExpire_date() {
        return expire_date;
    }

    /**
     * @param expire_date the expire_date to set
     */
    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    /**
     * @return the accountno
     */
    /**
     * @return the dunocuoiky
     */
    @XmlElement(name = "PrincipalToPay", nillable = true)
    public String getDunocuoiky() {
        return dunocuoiky;
    }

    /**
     * @param dunocuoiky the dunocuoiky to set
     */
    public void setDunocuoiky(String dunocuoiky) {
        this.dunocuoiky = dunocuoiky;
    }

    /**
     * @return the ngaytttoithieu
     */
    @XmlElement(name = "MatDateCreditCard", nillable = true)
    public String getNgaytttoithieu() {
        return ngaytttoithieu;
    }

    /**
     * @param ngaytttoithieu the ngaytttoithieu to set
     */
    public void setNgaytttoithieu(String ngaytttoithieu) {
        this.ngaytttoithieu = ngaytttoithieu;
    }

    /**
     * @return the dunodasudung
     */
    @XmlElement(name = "PrincipalUsed", nillable = true)
    public String getDunodasudung() {
        return dunodasudung;
    }

    /**
     * @param dunodasudung the dunodasudung to set
     */
    public void setDunodasudung(String dunodasudung) {
        this.dunodasudung = dunodasudung;
    }

    /**
     * @return the thanhtoantoithieu
     */
    @XmlElement(name = "AmtLimitPay", nillable = true)
    public String getThanhtoantoithieu() {
        return thanhtoantoithieu;
    }

    /**
     * @param thanhtoantoithieu the thanhtoantoithieu to set
     */
    public void setThanhtoantoithieu(String thanhtoantoithieu) {
        this.thanhtoantoithieu = thanhtoantoithieu;
    }

    /**
     * @return the hanmucconlai
     */
    @XmlElement(name = "AvaliableLimit", nillable = true)
    public String getHanmucconlai() {
        return hanmucconlai;
    }

    /**
     * @param hanmucconlai the hanmucconlai to set
     */
    public void setHanmucconlai(String hanmucconlai) {
        this.hanmucconlai = hanmucconlai;
    }

    /**
     * @return the cardname
     */
    @XmlElement(name = "CardName", nillable = true)
    public String getCardname() {
        return cardname;
    }

    /**
     * @param cardname the cardname to set
     */
    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    /**
     * @return the tinhtrangthe
     */
    @XmlElement(name = "CardStatus")
    public String getTinhtrangthe() {
        return tinhtrangthe;
    }

    /**
     * @param tinhtrangthe the tinhtrangthe to set
     */
    public void setTinhtrangthe(String tinhtrangthe) {
        this.tinhtrangthe = tinhtrangthe;
    }

    /**
     * @return the blockonline
     */
    @XmlElement(name = "ONLStatus")
    public String getBlockonline() {
        return blockonline;
    }

    /**
     * @param blockonline the blockonline to set
     */
    public void setBlockonline(String blockonline) {
        this.blockonline = blockonline;
    }

    /**
     * @return the brn
     */
    @XmlElement(name = "CardTypeGroup")
    public String getBrn() {
        return brn;
    }

    /**
     * @param brn the brn to set
     */
    public void setBrn(String brn) {
        this.brn = brn;
    }

    /**
     * @return the reasoncde
     */
    public String getReasoncde() {
        return reasoncde;
    }

    /**
     * @param reasoncde the reasoncde to set
     */
    public void setReasoncde(String reasoncde) {
        this.reasoncde = reasoncde;
    }

    /**
     * @return the primarycif
     */
    public String getPrimarycif() {
        return primarycif;
    }

    /**
     * @param primarycif the primarycif to set
     */
    public void setPrimarycif(String primarycif) {
        this.primarycif = primarycif;
    }

    /**
     * @return the sumAmtLimitPay
     */
    @XmlElement(name = "SumAmtLimitPay", nillable = true)
    public String getSumAmtLimitPay() {
        return sumAmtLimitPay;
    }

    /**
     * @param sumAmtLimitPay the sumAmtLimitPay to set
     */
    public void setSumAmtLimitPay(String sumAmtLimitPay) {
        this.sumAmtLimitPay = sumAmtLimitPay;
    }

    /**
     * @return the sumPaidAmount
     */
    @XmlElement(name = "SumPaidAmount", nillable = true)
    public String getSumPaidAmount() {
        return sumPaidAmount;
    }

    /**
     * @param sumPaidAmount the sumPaidAmount to set
     */
    public void setSumPaidAmount(String sumPaidAmount) {
        this.sumPaidAmount = sumPaidAmount;
    }

    /**
     * @return the sumPrincipalToPay
     */
    @XmlElement(name = "SumPrincipalToPay", nillable = true)
    public String getSumPrincipalToPay() {
        return sumPrincipalToPay;
    }

    /**
     * @param sumPrincipalToPay the sumPrincipalToPay to set
     */
    public void setSumPrincipalToPay(String sumPrincipalToPay) {
        this.sumPrincipalToPay = sumPrincipalToPay;
    }

    /**
     * @return the cardIPPCurrentAmount
     */
    @XmlElement(name = "CardIPPCurrentAmount", nillable = true)
    public String getCardIPPCurrentAmount() {
        return cardIPPCurrentAmount;
    }

    /**
     * @param cardIPPCurrentAmount the cardIPPCurrentAmount to set
     */
    public void setCardIPPCurrentAmount(String cardIPPCurrentAmount) {
        this.cardIPPCurrentAmount = cardIPPCurrentAmount;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CardIPPStatementAmount", nillable = true)
    public String getCardIPPStatementAmount() {
        return cardIPPStatementAmount;
    }

    /**
     * @param cardIPPStatementAmount the cardIPPStatementAmount to set
     */
    public void setCardIPPStatementAmount(String cardIPPStatementAmount) {
        this.cardIPPStatementAmount = cardIPPStatementAmount;
    }

    @XmlElement(name = "ImageId", nillable = true)
    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    @XmlElement(name = "CardNoHidden", nillable = true)
    public String getCardNoHidden() {
        return cardNoHidden;
    }

    public void setCardNoHidden(String cardNoHidden) {
        this.cardNoHidden = cardNoHidden;
    }

    @XmlElement(name = "CardActive", nillable = true)
    public String getCardactive() {
        return cardactive;
    }

    public void setCardactive(String cardactive) {
        this.cardactive = cardactive;
    }
}
