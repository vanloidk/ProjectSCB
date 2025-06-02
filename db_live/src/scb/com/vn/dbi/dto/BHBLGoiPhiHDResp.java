/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author loinv3
 */
public class BHBLGoiPhiHDResp implements Serializable {
    private static final long serialVersionUID = -1L;
    private long Id;
    private String TenHD;
    private String TenGoiPhiHD;
    private int IdHD;
    private int IdGoiPhiHD;
    private BigDecimal SoTien;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getTenHD() {
        return TenHD;
    }

    public void setTenHD(String TenHD) {
        this.TenHD = TenHD;
    }

    public String getTenGoiPhiHD() {
        return TenGoiPhiHD;
    }

    public void setTenGoiPhiHD(String TenGoiPhiHD) {
        this.TenGoiPhiHD = TenGoiPhiHD;
    }

    public int getIdHD() {
        return IdHD;
    }

    public void setIdHD(int IdHD) {
        this.IdHD = IdHD;
    }

    public int getIdGoiPhiHD() {
        return IdGoiPhiHD;
    }

    public void setIdGoiPhiHD(int IdGoiPhiHD) {
        this.IdGoiPhiHD = IdGoiPhiHD;
    }

    public BigDecimal getSoTien() {
        return SoTien;
    }

    public void setSoTien(BigDecimal SoTien) {
        this.SoTien = SoTien;
    }
}
