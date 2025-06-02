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
@XmlRootElement(name = "ManulifeType")
public class ManulifeType {

    private String PREM_TYPE;
    private String PREM_NAME;

    /**
     * @return the PREM_TYPE
     */
    @XmlElement(name = "PREM_TYPE", nillable = true)
    public String getPREM_TYPE() {
        return PREM_TYPE;
    }

    /**
     * @param PREM_TYPE the PREM_TYPE to set
     */
    public void setPREM_TYPE(String PREM_TYPE) {
        this.PREM_TYPE = PREM_TYPE;
    }

    /**
     * @return the PREM_NAME
     */
    @XmlElement(name = "PREM_NAME", nillable = true)
    public String getPREM_NAME() {
        return PREM_NAME;
    }

    /**
     * @param PREM_NAME the PREM_NAME to set
     */
    public void setPREM_NAME(String PREM_NAME) {
        this.PREM_NAME = PREM_NAME;
    }

}
