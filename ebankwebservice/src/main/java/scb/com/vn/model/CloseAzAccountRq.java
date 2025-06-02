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
@XmlRootElement(name = "CloseAzAccountRq")
public class CloseAzAccountRq extends MobileRequest {

    private String AccountNo;
    private String NominateAcc;
    private String RedemptionMode;
    private String RedemptionAmount;
    private String BranchCode;

    /**
     * @return the FromAccount
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAccountNo() {
        return AccountNo;
    }

    /**
     * @param AccountNo
     */
    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
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
     * @return the RedemptionMode
     */
    @XmlElement(name = "RedemptionMode", nillable = true)
    public String getRedemptionMode() {
        return RedemptionMode;
    }

    /**
     * @param RedemptionMode the RedemptionMode to set
     */
    public void setRedemptionMode(String RedemptionMode) {
        this.RedemptionMode = RedemptionMode;
    }

    /**
     * @return the RedemptionAmount
     */
    @XmlElement(name = "RedemptionAmount", nillable = true)
    public String getRedemptionAmount() {
        return RedemptionAmount;
    }

    /**
     * @param RedemptionAmount the RedemptionAmount to set
     */
    public void setRedemptionAmount(String RedemptionAmount) {
        this.RedemptionAmount = RedemptionAmount;
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
