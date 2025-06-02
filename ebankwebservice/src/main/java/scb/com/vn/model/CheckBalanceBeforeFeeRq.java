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
@XmlRootElement(name = "CheckBalanceBeforeFeeRq")
public class CheckBalanceBeforeFeeRq {

    private String Amount;
    private String TxnFee;
    private String TxnTax;
    private String FromAccount;
    private String TxnType;
    private String CifNo;

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
     * @return the TxnType
     */
    @XmlElement(name = "TxnType", nillable = true)
    public String getTxnType() {
        return TxnType;
    }

    /**
     * @param TxnType the TxnTax to set
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

}
