/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thachdn
 */
public class BillEVNSPC_table2 {
    
    private int id;
    private String MA_KHANG;
    private int DIEN_TTHU;
    private Long DON_GIA;
    private Long SO_TIEN;
    private String LOAI;

    @XmlElement(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @XmlElement(name = "MA_KHANG")
    public String getMA_KHANG() {
        return MA_KHANG;
    }

    public void setMA_KHANG(String MA_KHANG) {
        this.MA_KHANG = MA_KHANG;
    }

    @XmlElement(name = "DIEN_TTHU")
    public int getDIEN_TTHU() {
        return DIEN_TTHU;
    }

    public void setDIEN_TTHU(int DIEN_TTHU) {
        this.DIEN_TTHU = DIEN_TTHU;
    }

    @XmlElement(name = "DON_GIA")
    public Long getDON_GIA() {
        return DON_GIA;
    }

    public void setDON_GIA(Long DON_GIA) {
        this.DON_GIA = DON_GIA;
    }

    @XmlElement(name = "SO_TIEN")
    public Long getSO_TIEN() {
        return SO_TIEN;
    }

    public void setSO_TIEN(Long SO_TIEN) {
        this.SO_TIEN = SO_TIEN;
    }

    

    @XmlElement(name = "LOAI")
    public String getLOAI() {
        return LOAI;
    }

    public void setLOAI(String LOAI) {
        this.LOAI = LOAI;
    }
    
}
