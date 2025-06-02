/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author kimncm
 */
public class InsOnlineProduct {

    private String idsanpham;
    private String tensanpham;

    /**
     * @return the idsanpham
     */
    @XmlElement(name = "IDSANPHAM", nillable = true)
    public String getIdsanpham() {
        return idsanpham;
    }

    /**
     * @param idsanpham the idsanpham to set
     */
    public void setIdsanpham(String idsanpham) {
        this.idsanpham = idsanpham;
    }

    /**
     * @return the tensanpham
     */
    @XmlElement(name = "TENSANPHAM", nillable = true)
    public String getTensanpham() {
        return tensanpham;
    }

    /**
     * @param tensanpham the tensanpham to set
     */
    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }
}
