/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hieudt
 */
@XmlRootElement(name = "REQUESTCHANGEPINCARD")
public class RequestChangePINCard {

    private String Pan;
    private String MobileNo;
    private String NewPIN;

    /**
     *
     */
    public RequestChangePINCard() {
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PAN")
    public String getPan() {
        return this.Pan;
    }

    /**
     *
     * @param Pan
     */
    public void setPan(String Pan) {
        this.Pan = Pan;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MOBILENO")
    public String getMobileNo() {
        return this.MobileNo;
    }

    /**
     *
     * @param MobileNo
     */
    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "NEWPIN")
    public String getNewPIN() {
        return this.NewPIN;
    }

    /**
     *
     * @param NewPIN
     */
    public void setNewPIN(String NewPIN) {
        this.NewPIN = NewPIN;
    }

}
