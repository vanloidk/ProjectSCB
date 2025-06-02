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
@XmlRootElement(name = "GuaranteeRegisterRq")
public class GuaranteeRegisterRq {

    private String UserName;
    private String CifNo;
    private String guaranteePurpose;
    private String guaranteeAmount;
    private String guaranteeTime;
    private String guaranteeType;
    private String guaranteeMode;
    private String idchannel;
    private String collateral;
    private String address01;
    private String guaranteeFromDate;
    private String guaranteeToDate;
    private String collateralDesc;

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
     * @return the guaranteePurpose
     */
    @XmlElement(name = "GuaranteePurpose", nillable = true)
    public String getGuaranteePurpose() {
        return guaranteePurpose;
    }

    /**
     * @param guaranteePurpose the guaranteePurpose to set
     */
    public void setGuaranteePurpose(String guaranteePurpose) {
        this.guaranteePurpose = guaranteePurpose;
    }

    /**
     * @return the guaranteeAmount
     */
    @XmlElement(name = "GuaranteeAmount", nillable = true)
    public String getGuaranteeAmount() {
        return guaranteeAmount;
    }

    /**
     * @param guaranteeAmount the guaranteeAmount to set
     */
    public void setGuaranteeAmount(String guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    /**
     * @return the guaranteeTime
     */
    @XmlElement(name = "GuaranteeTime", nillable = true)
    public String getGuaranteeTime() {
        return guaranteeTime;
    }

    /**
     * @param guaranteeTime the guaranteeTime to set
     */
    public void setGuaranteeTime(String guaranteeTime) {
        this.guaranteeTime = guaranteeTime;
    }

    /**
     * @return the guaranteeType
     */
    @XmlElement(name = "GaranteeType", nillable = true)
    public String getGuaranteeType() {
        return guaranteeType;
    }

    /**
     * @param guaranteeType the guaranteeType to set
     */
    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    /**
     * @return the guaranteeMode
     */
    @XmlElement(name = "GuaranteeMode", nillable = true)
    public String getGuaranteeMode() {
        return guaranteeMode;
    }

    /**
     * @param guaranteeMode the guaranteeMode to set
     */
    public void setGuaranteeMode(String guaranteeMode) {
        this.guaranteeMode = guaranteeMode;
    }

    /**
     * @return the idchannel
     */
    public String getIdchannel() {
        return idchannel;
    }

    /**
     * @param idchannel the idchannel to set
     */
    @XmlElement(name = "Idchannel", nillable = true)
    public void setIdchannel(String idchannel) {
        this.idchannel = idchannel;
    }

    @XmlElement(name = "Collateral", nillable = true)
    public String getCollateral() {
        return collateral;
    }

    public void setCollateral(String collateral) {
        this.collateral = collateral;
    }

    @XmlElement(name = "Address01", nillable = true)
    public String getAddress01() {
        return address01;
    }

    public void setAddress01(String address01) {
        this.address01 = address01;
    }

    @XmlElement(name = "GuaranteeFromDate", nillable = true)
    public String getGuaranteeFromDate() {
        return guaranteeFromDate;
    }

    public void setGuaranteeFromDate(String guaranteeFromDate) {
        this.guaranteeFromDate = guaranteeFromDate;
    }

    @XmlElement(name = "GuaranteeToDate", nillable = true)
    public String getGuaranteeToDate() {
        return guaranteeToDate;
    }

    public void setGuaranteeToDate(String guaranteeToDate) {
        this.guaranteeToDate = guaranteeToDate;
    }
    
    @XmlElement(name = "CollateralDesc", nillable = true)
    public String getCollateralDesc() {
        return collateralDesc;
    }
    
    public void setCollateralDesc(String collateralDesc) {
        this.collateralDesc = collateralDesc;
    }

}
