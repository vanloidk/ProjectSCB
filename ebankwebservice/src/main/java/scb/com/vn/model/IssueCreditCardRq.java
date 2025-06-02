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
@XmlRootElement(name = "IssueCreditCardRq")
public class IssueCreditCardRq extends MobileRequest {

    private String UserName;
    private String CifNo;
    private String cardtype;
    private String limitrequest;
    private String guaranteemode;
    private String guaranteemodeinfo;
    private String branchcode;
    private String address;

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
     *
     * @return
     */
    @XmlElement(name = "BranchCode", nillable = true)
    public String getBranchcode() {
        return branchcode;
    }

    /**
     * @param branchcode the branchcode to set
     */
    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    /**
     * @return the cardtype
     */
    @XmlElement(name = "CCType", nillable = true)
    public String getCardtype() {
        return cardtype;
    }

    /**
     * @param cardtype the cardtype to set
     */
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    /**
     * @return the limitrequest
     */
    @XmlElement(name = "LimitRequest", nillable = true)
    public String getLimitrequest() {
        return limitrequest;
    }

    /**
     * @param limitrequest the limitrequest to set
     */
    public void setLimitrequest(String limitrequest) {
        this.limitrequest = limitrequest;
    }

    /**
     * @return the guaranteemode
     */
    @XmlElement(name = "GuaranteeMode", nillable = true)
    public String getGuaranteemode() {
        return guaranteemode;
    }

    /**
     * @param guaranteemode the guaranteemode to set
     */
    public void setGuaranteemode(String guaranteemode) {
        this.guaranteemode = guaranteemode;
    }

    /**
     * @return the guaranteemodeinfo
     */
    @XmlElement(name = "GuaranteeModeInfo", nillable = true)
    public String getGuaranteemodeinfo() {
        return guaranteemodeinfo;
    }

    /**
     * @param guaranteemodeinfo the guaranteemodeinfo to set
     */
    public void setGuaranteemodeinfo(String guaranteemodeinfo) {
        this.guaranteemodeinfo = guaranteemodeinfo;
    }

    /**
     * @return the address
     */
    @XmlElement(name = "Address", nillable = true)
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
