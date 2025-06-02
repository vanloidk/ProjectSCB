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
@XmlRootElement(name = "GetStaffRq")
public class GetStaffRq {

    private String StaffCode;

    /**
     * @return the StaffCode
     */
    @XmlElement(name = "StaffCode")
    public String getStaffCode() {
        return StaffCode;
    }

    /**
     * @param StaffCode the StaffCode to set
     */
    public void setStaffCode(String StaffCode) {
        this.StaffCode = StaffCode;
    }
}
