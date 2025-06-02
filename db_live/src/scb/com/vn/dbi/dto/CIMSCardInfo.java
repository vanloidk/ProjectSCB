/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hieudt
 */
@XmlRootElement(name = "CardInfo")
public class CIMSCardInfo {

    private String Phone;
    private String CustType;

    /**
     * Create a new instance of CIMSCardInfo
     */
    public CIMSCardInfo() {
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "Phone")
    public String getPhone() {
        return this.Phone;
    }

    /**
     *
     * @param Phone
     */
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CustType")
    public String getCustType() {
        return this.CustType;
    }

    /**
     *
     * @param CustType
     */
    public void setCustType(String CustType) {
        this.CustType = CustType;
    }

}
