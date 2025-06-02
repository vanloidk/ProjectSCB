/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phucnnd
 */
public class RequestCancelPay {
    
    private String maKH;
    private String servicetype;
    private String provider;
    private String hoadonID;
    private String other;

    @XmlElement(name = "MAKH", required = false, nillable = true)
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    @XmlElement(name = "SERVICE", required = false, nillable = true)
    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    @XmlElement(name = "PROVIDER", required = false, nillable = true)
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @XmlElement(name = "HOADONID", required = false, nillable = true)
    public String getHoadonID() {
        return hoadonID;
    }

    public void setHoadonID(String hoadonID) {
        this.hoadonID = hoadonID;
    }

    @XmlElement(name = "OTHER", required = false, nillable = true)
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
    
    
}
