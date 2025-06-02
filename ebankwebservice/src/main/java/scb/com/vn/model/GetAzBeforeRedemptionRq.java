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
@XmlRootElement(name = "GetAzBeforeRedemptionRq")
public class GetAzBeforeRedemptionRq extends MobileResponse {

    private String UserName;
    private String CifNo;
    private String AzAccount;
    private String RedemptionMode;
    private String RedemptionAmount;
    private String NominateAcc;

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
     * @return the AzAccount
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAzAccount() {
        return AzAccount;
    }

    /**
     * @param AzAccount the AzAccount to set
     */
    public void setAzAccount(String AzAccount) {
        this.AzAccount = AzAccount;
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

}
