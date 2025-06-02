/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;


import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author kimncm
 */

public class DawacoBillDetail {

    //  private List<ChungTuNo> ctNoList;
    private String maKH;  
    private String soHoaDon;          
    private String tenKH;
    private String diachiKH;
    private String soTien;
    private String thangno;
    private String loaiKH;    
    private String loai;
    private String ent_code;
    @XmlElement(name = "ENT_CODE", nillable = true) 
    public String getEnt_code() {
        return ent_code;
    }
    public void setEnt_code(String ent_code) {
        this.ent_code = ent_code;
    }

    /**
     * @return the maKH
     */
    @XmlElement(name = "MA_KHACH_HANG", nillable = true)
    public String getMaKH() {
        return maKH;
    }

    /**
     * @param maKH the maKH to set
     */
    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    /**
     * @return the soHoaDon
     */
    @XmlElement(name = "SO_HOA_DON", nillable = true)
    public String getSoHoaDon() {
        return soHoaDon;
    }

    /**
     * @param soHoaDon the soHoaDon to set
     */
    public void setSoHoaDon(String soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    /**
     * @return the tenKH
     */
    @XmlElement(name = "TEN_KHACH_HANG", nillable = true)    
    public String getTenKH() {
        return tenKH;
    }

    /**
     * @param tenKH the tenKH to set
     */
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    /**
     * @return the diachiKH
     */
    @XmlElement(name = "DIA_CHI_KHACH_HANG", nillable = true)    
    public String getDiachiKH() {
        return diachiKH;
    }

    /**
     * @param diachiKH the diachiKH to set
     */
    public void setDiachiKH(String diachiKH) {
        this.diachiKH = diachiKH;
    }

    /**
     * @return the soTien
     */
    @XmlElement(name = "SO_TIEN", nillable = true)    
    public String getSoTien() {
        return soTien;
    }

    /**
     * @param soTien the soTien to set
     */
    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    /**
     * @return the thangno
     */
    @XmlElement(name = "THANG_NO", nillable = true)    
    public String getThangno() {
        return thangno;
    }

    /**
     * @param thangno the thangno to set
     */
    public void setThangno(String thangno) {
        this.thangno = thangno;
    }

    /**
     * @return the loaiKH
     */
    @XmlElement(name = "LOAI_KHACH_HANG", nillable = true)
    public String getLoaiKH() {
        return loaiKH;
    }

    /**
     * @param loaiKH the loaiKH to set
     */
    public void setLoaiKH(String loaiKH) {
        this.loaiKH = loaiKH;
    }

    /**
     * @return the loai
     */
    @XmlElement(name = "LOAI", nillable = true)    
    public String getLoai() {
        return loai;
    }

    /**
     * @param loai the loai to set
     */
    public void setLoai(String loai) {
        this.loai = loai;
    }
 
   }
