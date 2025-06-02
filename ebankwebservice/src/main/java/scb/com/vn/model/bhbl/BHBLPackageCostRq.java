/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.bhbl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "PackageCostRq")
public class BHBLPackageCostRq {

    private String goiHD;
    private String goiPhiHD;
    private int tuoi;
    private int gioiTinh;
    private String lang;

    public BHBLPackageCostRq() {
    }

    @XmlElement(name = "PackageContract", required = false, nillable = true)
    public String getGoiHD() {
        return goiHD;
    }

    public void setGoiHD(String goiHD) {
        this.goiHD = goiHD;
    }

    @XmlElement(name = "PackageCostContract", required = false, nillable = true)
    public String getGoiPhiHD() {
        return goiPhiHD;
    }

    public void setGoiPhiHD(String goiPhiHD) {
        this.goiPhiHD = goiPhiHD;
    }

    @XmlElement(name = "Age", required = false, nillable = true)
    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    @XmlElement(name = "Gender", required = false, nillable = true)
    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @XmlElement(name = "Lang", required = false, nillable = true)
    public String getLang() {
        return lang;
    }
    
    public void setLang(String lang) {
        this.lang = lang;
    }

}
