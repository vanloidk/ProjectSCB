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
@XmlRootElement(name = "CheckAlertTDRp")
public class CheckAlertTDRp extends MobileResponse {

    private String CustomerNo;
    private String RegisterStatus;

    /**
     * @return the CustomerNo
     */
    @XmlElement(name = "CustomerNo", nillable = true)
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
    @XmlElement(name = "RegisterStatus", nillable = true)
    public String getRegisterStatus() {
        return RegisterStatus;
    }

    /**
     * @param RegisterStatus the RegisterStatus to set
     */
    public void setRegisterStatus(String RegisterStatus) {
        this.RegisterStatus = RegisterStatus;
    }

}
