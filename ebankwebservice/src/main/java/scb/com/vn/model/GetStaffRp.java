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
 *
 */
@XmlRootElement(name = "GetStaffRp")
public class GetStaffRp extends MobileResponse {

    private String _StaffCode;
    private String _StaffName;

    /**
     * @return the _StaffCode
     */
    @XmlElement(name = "StaffCode", nillable = true)
    public String getStaffCode() {
        return _StaffCode;
    }

    /**
     * @param StaffCode the _StaffCode to set
     */
    public void setStaffCode(String StaffCode) {
        this._StaffCode = StaffCode;
    }

    /**
     * @return the _StaffName
     */
    @XmlElement(name = "StaffName", nillable = true)
    public String getStaffName() {
        return _StaffName;
    }

    /**
     * @param StaffName the _StaffName to set
     */
    public void setStaffName(String StaffName) {
        this._StaffName = StaffName;
    }
}
