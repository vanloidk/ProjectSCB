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
public class BHBLHDAndGoiPhiResp implements Serializable {

    private static final long serialVersionUID = -1L;
    private long IdHD;
    private long IdGoiPhi;
    private long DMHD;
    private long DMGoiPhi;
    private int TuoiTu;
    private int DenTuoi;
    private BigDecimal SoTien;

    public long getIdHD() {
        return IdHD;
    }

    public void setIdHD(long IdHD) {
        this.IdHD = IdHD;
    }

    public long getDMHD() {
        return DMHD;
    }

    public void setDMHD(long DMHD) {
        this.DMHD = DMHD;
    }

    public long getIdGoiPhi() {
        return IdGoiPhi;
    }

    public void setIdGoiPhi(long IdGoiPhi) {
        this.IdGoiPhi = IdGoiPhi;
    }

    public long getDMGoiPhi() {
        return DMGoiPhi;
    }

    public void setDMGoiPhi(long DMGoiPhi) {
        this.DMGoiPhi = DMGoiPhi;
    }

    public int getTuoiTu() {
        return TuoiTu;
    }

    public void setTuoiTu(int TuoiTu) {
        this.TuoiTu = TuoiTu;
    }

    public int getDenTuoi() {
        return DenTuoi;
    }

    public void setDenTuoi(int DenTuoi) {
        this.DenTuoi = DenTuoi;
    }

    public BigDecimal getSoTien() {
        return SoTien;
    }

    public void setSoTien(BigDecimal SoTien) {
        this.SoTien = SoTien;
    }

}
