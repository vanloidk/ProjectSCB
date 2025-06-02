/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "CreditCardPayNewRq")
public class CreditCardPayNewRq extends MobileRequest {

    private String FromAccount;
    private String CardNo;
    private String Amount;
    private String Remark;

    private String AccountNo;
    private String CardAccountNumer;
    private String ExpireDate;
    private String AccountBranchOpen;

    /**
     * @return the Amount
     */
    @XmlElement(name = "Amount", nillable = true)
    public String getAmount() {
        return Amount;
    }

    /**
     * @param Amount the Amount to set
     */
    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    /**
     * @return the FromAccount
     */
    @XmlElement(name = "FromAccount", nillable = true)
    public String getFromAccount() {
        return FromAccount;
    }

    /**
     * @param FromAccount the FromAccount to set
     */
    public void setFromAccount(String FromAccount) {
        this.FromAccount = FromAccount;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CardNo", nillable = true)
    public String getCardNo() {
        return CardNo;
    }

    /**
     * @param CardNo the CardNo to set
     */
    public void setCardNo(String CardNo) {
        this.CardNo = CardNo;
    }

    /**
     * @return the Remark
     */
    @XmlElement(name = "Remark", nillable = true)
    public String getRemark() {
        return Remark;
    }

    /**
     * @param Remark the Remark to set
     */
    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    /**
     * @return the AccountNo
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAccountNo() {
        return AccountNo;
    }

    /**
     * @param AccountNo the AccountNo to set
     */
    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    /**
     * @return the CardAccountNumer
     */
    @XmlElement(name = "CardAccountNumer", nillable = true)
    public String getCardAccountNumer() {
        return CardAccountNumer;
    }

    /**
     * @param CardAccountNumer the CardAccountNumer to set
     */
    public void setCardAccountNumer(String CardAccountNumer) {
        this.CardAccountNumer = CardAccountNumer;
    }

    /**
     * @return the ExpireDate
     */
    @XmlElement(name = "ExpireDate", nillable = true)
    public String getExpireDate() {
        return ExpireDate;
    }

    /**
     * @param ExpireDate the ExpireDate to set
     */
    public void setExpireDate(String ExpireDate) {
        this.ExpireDate = ExpireDate;
    }

    /**
     * @return the AccountBranchOpen
     */
    @XmlElement(name = "AccountBranchOpen", nillable = true)
    public String getAccountBranchOpen() {
        return AccountBranchOpen;
    }

    /**
     * @param AccountBranchOpen the AccountBranchOpen to set
     */
    public void setAccountBranchOpen(String AccountBranchOpen) {
        this.AccountBranchOpen = AccountBranchOpen;
    }
}
