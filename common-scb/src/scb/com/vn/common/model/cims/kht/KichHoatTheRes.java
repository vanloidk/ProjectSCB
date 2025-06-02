/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.kht;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "KICHHOATRES")
public class KichHoatTheRes implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String sequence;
    private String cmnd;
    private String phone;
    private String last4Digits;
    private String status;

    public KichHoatTheRes() {}

    public KichHoatTheRes(String status) {
        this.status = status;
    }
    
    public KichHoatTheRes(KichHoatTheReq req) {
        this.sequence = req.getSequence();
        this.cmnd = req.getCmnd();
        this.phone = req.getPhone();
        this.last4Digits = req.getLast4Digits();
    }

    public KichHoatTheRes(KichHoatTheReq req, String status) {
        this.sequence = req.getSequence();
        this.cmnd = req.getCmnd();
        this.phone = req.getPhone();
        this.last4Digits = req.getLast4Digits();
        this.status = status;
    }

    @XmlElement(name = "SEQUENCE", nillable = false)
    public String getSequence() {
        return sequence;
    }

    @XmlElement(name = "CMND", nillable = true)
    public String getCmnd() {
        return cmnd;
    }

    @XmlElement(name = "PHONE", nillable = true)
    public String getPhone() {
        return phone;
    }

    @XmlElement(name = "LAST4DIGITS", nillable = true)
    public String getLast4Digits() {
        return last4Digits;
    }

    @XmlElement(name = "STATUS", nillable = false)
    public String getStatus() {
        return status;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLast4Digits(String last4Digits) {
        this.last4Digits = last4Digits;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
