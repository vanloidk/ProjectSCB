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
@XmlRootElement(name = "GetMobileFeeRq")
public class GetMobileFeeRq extends MobileRequest {

    private String TxnFee;
    private String TxnTax;
    private String FromAccount;
    private String Remark;
    private String TxnType;
    private String BranchCode;

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
     * @param TxnType the TxnTax to set
     */
    public void setTxnType(String TxnType) {
        this.TxnType = TxnType;
    }

    /**
     * @return the BranchCode
     */
    @XmlElement(name = "BranchCode", nillable = true)
    public String getBranchCode() {
        return BranchCode;
    }

    /**
     * @param BranchCode the BranchCode to set
     */
    public void setBranchCode(String BranchCode) {
        this.BranchCode = BranchCode;
    }

}
