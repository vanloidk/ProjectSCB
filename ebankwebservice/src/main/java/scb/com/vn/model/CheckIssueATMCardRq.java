/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kimncm
 */
@XmlRootElement(name = "CheckIssueATMCardRq")
public class CheckIssueATMCardRq {

    private String UserName;
    private String CifNo;
    private String TxnFee;
    private String TxnTax;
    private String SourceAccount;
    private String IssueType;
    private String AccountNo;
    private String RegisterType;
    private String cardtype;

    /**
     * @return the TxnFee
     */
    @XmlElement(name = "TxnFee", nillable = true)
    public String getTxnFee() {
        return TxnFee;
    }

    /**
     * @param TxnFee the TxnFee to set
     */
    public void setTxnFee(String TxnFee) {
        this.TxnFee = TxnFee;
    }

    /**
     * @return the TxnTax
     */
    @XmlElement(name = "TxnTax", nillable = true)
    public String getTxnTax() {
        return TxnTax;
    }

    /**
     * @param TxnTax the TxnTax to set
     */
    public void setTxnTax(String TxnTax) {
        this.TxnTax = TxnTax;
    }

    /**
     * @return the UserName
     */
    @XmlElement(name = "UserName", nillable = true)
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @return the CifNo
     */
    @XmlElement(name = "CifNo", nillable = true)
    public String getCifNo() {
        return CifNo;
    }

    /**
     * @param CifNo the CifNo to set
     */
    public void setCifNo(String CifNo) {
        this.CifNo = CifNo;
    }

    /**
     * @return the SourceAccount
     */
    @XmlElement(name = "SourceAccount", nillable = true)
    public String getSourceAccount() {
        return SourceAccount;
    }

    /**
     * @param SourceAccount the SourceAccount to set
     */
    public void setSourceAccount(String SourceAccount) {
        this.SourceAccount = SourceAccount;
    }

    /**
     * @return the IssueType
     */
    @XmlElement(name = "IssueType", nillable = true)
    public String getIssueType() {
        return IssueType;
    }

    /**
     * @param IssueType the IssueType to set
     */
    public void setIssueType(String IssueType) {
        this.IssueType = IssueType;
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
     * @return the RegisterType
     */
    @XmlElement(name = "RegisterType", nillable = true)
    public String getRegisterType() {
        return RegisterType;
    }

    /**
     * @param RegisterType the RegisterType to set
     */
    public void setRegisterType(String RegisterType) {
        this.RegisterType = RegisterType;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ATMCardType", nillable = true)
    public String getCardtype() {
        return cardtype;
    }

    /**
     * @param cardtype the cardtype to set
     */
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
}
