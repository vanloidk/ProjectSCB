/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author FICOMBANK
 */
public class Electric {

    private String name;
    private String address;
    private String amount;
    private String addinfo;
    private String maHD;

    @XmlElement(name = "ADDINFO", required = false, nillable = true)
    public String getAddinfo() {
        return addinfo;
    }

    public void setAddinfo(String addinfo) {
        this.addinfo = addinfo;
    }

    @XmlElement(name = "CUSTOMERNAME", required = false, nillable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "ADDRESS", required = false, nillable = true)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement(name = "AMOUNT", required = false, nillable = true)
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the maHD
     */
    @XmlElement(name = "MAHD", required = false, nillable = true)
    public String getMaHD() {
        return maHD;
    }

    /**
     * @param maHD the maHD to set
     */
    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }
}
