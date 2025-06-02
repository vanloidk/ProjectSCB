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
@XmlRootElement(name = "RegisterAlertTDRq")
public class RegisterAlertTDRq {

    private String CustomerNo;
    private String RegisterStatus;
    private String MobileNo;

    /**
     *
     */
    public RegisterAlertTDRq() {
    }

    /**
     * @return the CustomerNo
     */
    @XmlElement(name = "CustomerNo")
    public String getCustomerNo() {
        return CustomerNo;
    }

    /**
     * @param CustomerNo the CustomerNo to set
     */
    public void setCustomerNo(String CustomerNo) {
        this.CustomerNo = CustomerNo;
    }

    /**
     * @return the RegisterStatus
     */
    @XmlElement(name = "RegisterStatus")
    public String getRegisterStatus() {
        return RegisterStatus;
    }

    /**
     * @param RegisterStatus the RegisterStatus to set
     */
    public void setRegisterStatus(String RegisterStatus) {
        this.RegisterStatus = RegisterStatus;
    }

    /**
     * @return the MobileNo
     */
    @XmlElement(name = "MobileNo", nillable = true)
    public String getMobileNo() {
        return MobileNo;
    }

    /**
     * @param MobileNo the MobileNo to set
     */
    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }
}
