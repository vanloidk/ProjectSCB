/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "DutyDetail")
public class DutyDetail {

    private String loaiquyenloi;
    private String tenquyenloi;

    /**
     * @return the loaiquyenloi
     */
    @XmlElement(name = "LOAIQUYENLOI", nillable = true)
    public String getLoaiquyenloi() {
        return loaiquyenloi;
    }

    /**
     * @param loaiquyenloi the loaiquyenloi to set
     */
    public void setLoaiquyenloi(String loaiquyenloi) {
        this.loaiquyenloi = loaiquyenloi;
    }

    /**
     * @return the tenquyenloi
     */
    @XmlElement(name = "TENQUYENLOI", nillable = true)
    public String getTenquyenloi() {
        return tenquyenloi;
    }

    /**
     * @param tenquyenloi the tenquyenloi to set
     */
    public void setTenquyenloi(String tenquyenloi) {
        this.tenquyenloi = tenquyenloi;
    }

}
