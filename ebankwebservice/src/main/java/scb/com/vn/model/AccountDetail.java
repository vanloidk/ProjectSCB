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
public class AccountDetail {

    private String accountno;
    private String cardaccountno;
    private String cardno;
    private String cardNoHidden;
    private String accountccy;
    private String accountbalance;
    private String accountbalanceavaliable;
    private String accountgroupcode;
    private String accountgroupname;
    private String branchcode;
    private String branchname;
    private String maturitydate;
    private String CreditType;
    private String CreditProperty;
    private String cardDescription;
    private String cardStatus;
    private String imageCard;

    /**
     *
     */
    public AccountDetail() {
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
     * @return the accountccy
     */
    @XmlElement(name = "AccountCcy")
    public String getAccountccy() {
        return accountccy;
    }

    /**
     * @param accountccy the accountccy to set
     */
    public void setAccountccy(String accountccy) {
        this.accountccy = accountccy;
    }

    /**
     * @return the accountbalance
     */
    @XmlElement(name = "AccountBalance")
    public String getAccountbalance() {
        return accountbalance;
    }

    /**
     * @param accountbalance the accountbalance to set
     */
    public void setAccountbalance(String accountbalance) {
        this.accountbalance = accountbalance;
    }

    /**
     * @return the accountbalanceavaliable
     */
    @XmlElement(name = "AccountBalanceAvaliable")
    public String getAccountbalanceavaliable() {
        return accountbalanceavaliable;
    }

    /**
     * @param accountbalanceavaliable the accountbalanceavaliable to set
     */
    public void setAccountbalanceavaliable(String accountbalanceavaliable) {
        this.accountbalanceavaliable = accountbalanceavaliable;
    }

    /**
     * @return the accountgroupcode
     */
    @XmlElement(name = "AccountGroupCode")
    public String getAccountgroupcode() {
        return accountgroupcode;
    }

    /**
     * @param accountgroupcode
     */
    public void setAccountgroupcode(String accountgroupcode) {
        this.accountgroupcode = accountgroupcode;
    }

    /**
     * @return the accountgroupname
     */
    @XmlElement(name = "AccountGroupName")
    public String getAccountgroupname() {
        return accountgroupname;
    }

    /**
     * @param accountgroupname the accountgroupname to set
     */
    public void setAccountgroupname(String accountgroupname) {
        this.accountgroupname = accountgroupname;
    }

    /**
     * @return the branchCode
     */
    @XmlElement(name = "BranchCode", nillable = true)
    public String getBranchcode() {
        return branchcode;
    }

    /**
     * @param branchcode
     */
    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    /**
     * @return the cardaccountno
     */
    @XmlElement(name = "CardAccountNo", nillable = true)
    public String getCardaccountno() {
        return cardaccountno;
    }

    /**
     * @param cardaccountno the cardaccountno to set
     */
    public void setCardaccountno(String cardaccountno) {
        this.cardaccountno = cardaccountno;
    }

    /**
     * @return the cardno
     */
    @XmlElement(name = "CardNo", nillable = true)
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
     * @return the branchname
     */
    @XmlElement(name = "BranchName", nillable = true)
    public String getBranchname() {
        return branchname;
    }

    /**
     * @param branchname the branchname to set
     */
    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    /**
     * @return the maturitydate
     */
    @XmlElement(name = "MaturityDate", nillable = true)
    public String getMaturitydate() {
        return maturitydate;
    }

    /**
     * @param maturitydate the maturitydate to set
     */
    public void setMaturitydate(String maturitydate) {
        this.maturitydate = maturitydate;
    }

    /**
     * @return the CreditType
     */
    @XmlElement(name = "CreditType", nillable = true)
    public String getCreditType() {
        return CreditType;
    }

    /**
     * @param CreditType the CreditType to set
     */
    public void setCreditType(String CreditType) {
        this.CreditType = CreditType;
    }

    /**
     * @return the CreditProperty
     */
    @XmlElement(name = "CreditProperty", nillable = true)
    public String getCreditProperty() {
        return CreditProperty;
    }

    /**
     * @param CreditProperty the CreditProperty to set
     */
    public void setCreditProperty(String CreditProperty) {
        this.CreditProperty = CreditProperty;
    }

    @XmlElement(name = "CardDescription", nillable = true)
    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    @XmlElement(name = "CardStatusName", nillable = true)
    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    @XmlElement(name = "ImageCard", nillable = true)
    public String getImageCard() {
        return imageCard;
    }

    public void setImageCard(String imageCard) {
        this.imageCard = imageCard;
    }
    
    @XmlElement(name = "CardNoHidden", nillable = true)
    public String getCardNoHidden() {
        return cardNoHidden;
    }
    public void setCardNoHidden(String cardNoHidden) {
        this.cardNoHidden = cardNoHidden;
    }
}
