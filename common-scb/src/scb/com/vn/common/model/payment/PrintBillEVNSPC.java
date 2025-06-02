/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author thachdn
 */
public class PrintBillEVNSPC {
    private String bankID;
    private String maGD;
    private String hoadonID;
    private String sotien;
    
    @XmlElement(name = "BANKID", required = false, nillable = true)
    public String getBankID() {
        return bankID;
    }

    public void setBankID(String testVal) {
        this.bankID = bankID;
    }

    @XmlElement(name = "MAGD", required = false, nillable = true)
    public String getMaGD() {
        return maGD;
    }

    public void setMaGD(String maGD) {
        this.maGD = maGD;
    }

    @XmlElement(name = "HOADONID", required = false, nillable = true)
    public String getHoadonID() {
        return hoadonID;
    }

    public void setHoadonID(String hoadonID) {
        this.hoadonID = hoadonID;
    }

    @XmlElement(name = "SOTIEN", required = false, nillable = true)
    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }
    
    
}
