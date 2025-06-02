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
@XmlRootElement(name = "FormDetail")
public class FormDetail {

    private String loaiphi;
    private String tenphi;
    private String formamt;

    /**
     * @return the loaiphi
     */
    @XmlElement(name = "LOAIPHI", nillable = true)
    public String getLoaiphi() {
        return loaiphi;
    }

    /**
     * @param loaiphi the loaiphi to set
     */
    public void setLoaiphi(String loaiphi) {
        this.loaiphi = loaiphi;
    }

    /**
     * @return the tenphi
     */
    @XmlElement(name = "TENPHI", nillable = true)
    public String getTenphi() {
        return tenphi;
    }

    /**
     * @param tenphi the tenphi to set
     */
    public void setTenphi(String tenphi) {
        this.tenphi = tenphi;
    }

    /**
     * @return the formamt
     */
    @XmlElement(name = "FORMAMT", nillable = true)
    public String getFormamt() {
        return formamt;
    }

    /**
     * @param formamt the formamt to set
     */
    public void setFormamt(String formamt) {
        this.formamt = formamt;
    }
}
