/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "CheckOpenOnlineAzRq")
public class CheckOpenOnlineAzRq {

    private String FromAccount;
    private String AccountTypeCode;
    private String Amount;
    private String MaturityInstr;
    private String NominateAcc;
    private String InterestAcc;
    private String MatDate;
    private String Interest;
    private String RolloverAmount;

    private String TxnFee;
    private String TxnTax;
    private String TxnType;
    private String CifNo;
    private String TenorType;
    //private String Ccy;

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
     * @return the MaturityInstr
     */
    @XmlElement(name = "MaturityInstr", nillable = true)
    public String getMaturityInstr() {
        return MaturityInstr;
    }

    /**
     * @param MaturityInstr the MaturityInstr to set
     */
    public void setMaturityInstr(String MaturityInstr) {
        this.MaturityInstr = MaturityInstr;
    }

    /**
     * @return the NominateAcc
     */
    @XmlElement(name = "NominateAcc", nillable = true)
    public String getNominateAcc() {
        return NominateAcc;
    }

    /**
     * @param NominateAcc the NominateAcc to set
     */
    public void setNominateAcc(String NominateAcc) {
        this.NominateAcc = NominateAcc;
    }

    /**
     * @return the MatDate
     */
    @XmlElement(name = "MatDate", nillable = true)
    public String getMatDate() {
        return MatDate;
    }

    /**
     * @param MatDate the MatDate to set
     */
    public void setMatDate(String MatDate) {
        this.MatDate = MatDate;
    }

    /**
     * @return the Interest
     */
    @XmlElement(name = "Interest", nillable = true)
    public String getInterest() {
        return Interest;
    }

    /**
     * @param Interest the Interest to set
     */
    public void setInterest(String Interest) {
        this.Interest = Interest;
    }

    /**
     * @return the AccountTypeCode
     */
    @XmlElement(name = "AccountTypeCode", nillable = true)
    public String getAccountTypeCode() {
        return AccountTypeCode;
    }

    /**
     * @param AccountTypeCode the AccountTypeCode to set
     */
    public void setAccountTypeCode(String AccountTypeCode) {
        this.AccountTypeCode = AccountTypeCode;
    }

    /**
     * @return the InterestAcc
     */
    @XmlElement(name = "InterestAcc", nillable = true)
    public String getInterestAcc() {
        return InterestAcc;
    }

    /**
     * @param InterestAcc the InterestAcc to set
     */
    public void setInterestAcc(String InterestAcc) {
        this.InterestAcc = InterestAcc;
    }

    /**
     * @return the RolloverAmount
     */
    @XmlElement(name = "RolloverAmount", nillable = true)
    public String getRolloverAmount() {
        return RolloverAmount;
    }

    /**
     * @param RolloverAmount the RolloverAmount to set
     */
    public void setRolloverAmount(String RolloverAmount) {
        this.RolloverAmount = RolloverAmount;
    }

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
     * @return the TxnType
     */
    @XmlElement(name = "TxnType", nillable = true)
    public String getTxnType() {
        return TxnType;
    }

    /**
     * @param TxnType the TxnType to set
     */
    public void setTxnType(String TxnType) {
        this.TxnType = TxnType;
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
     * @return the TenorType
     */
    @XmlElement(name = "TenorType", nillable = true)
    public String getTenorType() {
        return TenorType;
    }

    /**
     * @param TenorType the TenorType to set
     */
    public void setTenorType(String TenorType) {
        this.TenorType = TenorType;
    }

}
