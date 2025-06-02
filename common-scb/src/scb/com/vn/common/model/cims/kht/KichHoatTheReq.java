/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.kht;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.cims.CIMSRequest;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "KICHHOATTHEREQ")
public class KichHoatTheReq extends CIMSRequest implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String sequence;
    private String cmnd;
    private String phone;
    private String last4Digits;

    @XmlElement(name = "SEQUENCE", nillable = false)
    public String getSequence() {
        return sequence;
    }

    @XmlElement(name = "CMND", nillable = false)
    public String getCmnd() {
        return cmnd;
    }

    @XmlElement(name = "PHONE", nillable = false)
    public String getPhone() {
        return phone;
    }

    @XmlElement(name = "LAST4DIGITS" , nillable = true)
    public String getLast4Digits() {
        return last4Digits;
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

    @Override
    public String getValueToBuildMac() {
        return this.last4Digits + this.phone + this.sequence + this.cmnd + this.getTime();
    }
}
