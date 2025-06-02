/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "ACCOUNT")
public class AccountXML {

    private String MAKH;
    private String SEGMENT_KH;
    private String HOTEN;
    private String DIACHI;
    private String CMND;
    private String NGAYCAP;
    private String DIENTHOAI;
    private String LOAIKH;
    private String GIOITINH;
    private String NGAYSINH;
    private String NGAY_MO_CIF;
    private String SOTAIKHOAN;
    
    private String KY_HAN;
    private String LAI_SUAT;
    private String NGAYMO;
    private String NGAYDENHAN;
    private String MASP;
    private String TENSP;
    
    private String CHANNEL;    
    private String SODU_MENHGIA;
    private String SODU_MENHGIA_QD;
    private String CCY;
    private String DONVIMO;    
    private String LOAIGIAODICH;
    private String ISSTAFF;
    
    /**
     * @param NGAYSINH the NGAYSINH to set
     */
    public void setNGAYSINH(String NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    /**
     * @return the MAKH
     */
    @XmlElement(name = "MAKH")
    public String getMAKH() {
        return MAKH;
    }

    /**
     * @param MAKH the MAKH to set
     */
    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    /**
     * @return the SEGMENT_KH
     */
    @XmlElement(name = "SEGMENT_KH")
    public String getSEGMENT_KH() {
        return SEGMENT_KH;
    }

    /**
     * @param SEGMENT_KH the SEGMENT_KH to set
     */
    public void setSEGMENT_KH(String SEGMENT_KH) {
        this.SEGMENT_KH = SEGMENT_KH;
    }

    /**
     * @return the HOTEN
     */
    @XmlElement(name = "HOTEN")
    public String getHOTEN() {
        return HOTEN;
    }

    /**
     * @param HOTEN the HOTEN to set
     */
    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    /**
     * @return the DIACHI
     */
    @XmlElement(name = "DIACHI")
    public String getDIACHI() {
        return DIACHI;
    }

    /**
     * @param DIACHI the DIACHI to set
     */
    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    /**
     * @return the CMND
     */
    @XmlElement(name = "CMND")
    public String getCMND() {
        return CMND;
    }

    /**
     * @param CMND the CMND to set
     */
    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    /**
     * @return the NGAYCAP
     */
    @XmlElement(name = "NGAYCAP")
    public String getNGAYCAP() {
        return NGAYCAP;
    }

    /**
     * @param NGAYCAP the NGAYCAP to set
     */
    public void setNGAYCAP(String NGAYCAP) {
        this.NGAYCAP = NGAYCAP;
    }

    /**
     * @return the DIENTHOAI
     */
    @XmlElement(name = "DIENTHOAI")
    public String getDIENTHOAI() {
        return DIENTHOAI;
    }

    /**
     * @param DIENTHOAI the DIENTHOAI to set
     */
    public void setDIENTHOAI(String DIENTHOAI) {
        this.DIENTHOAI = DIENTHOAI;
    }

    /**
     * @return the LOAIKH
     */
    @XmlElement(name = "LOAIKH")
    public String getLOAIKH() {
        return LOAIKH;
    }

    /**
     * @param LOAIKH the LOAIKH to set
     */
    public void setLOAIKH(String LOAIKH) {
        this.LOAIKH = LOAIKH;
    }

    /**
     * @return the GIOITINH
     */
    @XmlElement(name = "GIOITINH")
    public String getGIOITINH() {
        return GIOITINH;
    }

    /**
     * @param GIOITINH the GIOITINH to set
     */
    public void setGIOITINH(String GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    /**
     * @return the NGAY_MO_CIF
     */
    @XmlElement(name = "NGAY_MO_CIF")
    public String getNGAY_MO_CIF() {
        return NGAY_MO_CIF;
    }

    /**
     * @param NGAY_MO_CIF the NGAY_MO_CIF to set
     */
    public void setNGAY_MO_CIF(String NGAY_MO_CIF) {
        this.NGAY_MO_CIF = NGAY_MO_CIF;
    }

    /**
     * @return the SOTAIKHOAN
     */
    @XmlElement(name = "SOTAIKHOAN")
    public String getSOTAIKHOAN() {
        return SOTAIKHOAN;
    }

    /**
     * @param SOTAIKHOAN the SOTAIKHOAN to set
     */
    public void setSOTAIKHOAN(String SOTAIKHOAN) {
        this.SOTAIKHOAN = SOTAIKHOAN;
    }

  
    /**
     * @return the KY_HAN
     */
    @XmlElement(name = "KY_HAN")
    public String getKY_HAN() {
        return KY_HAN;
    }

    /**
     * @param KY_HAN the KY_HAN to set
     */
    public void setKY_HAN(String KY_HAN) {
        this.KY_HAN = KY_HAN;
    }

    /**
     * @return the LAI_SUAT
     */
    @XmlElement(name = "LAI_SUAT")
    public String getLAI_SUAT() {
        return LAI_SUAT;
    }

    /**
     * @param LAI_SUAT the LAI_SUAT to set
     */
    public void setLAI_SUAT(String LAI_SUAT) {
        this.LAI_SUAT = LAI_SUAT;
    }

    /**
     * @return the NGAYMO
     */
    @XmlElement(name = "NGAYMO")
    public String getNGAYMO() {
        return NGAYMO;
    }

    /**
     * @param NGAYMO the NGAYMO to set
     */
    public void setNGAYMO(String NGAYMO) {
        this.NGAYMO = NGAYMO;
    }

    /**
     * @return the NGAYDENHAN
     */
    @XmlElement(name = "NGAYDENHAN")
    public String getNGAYDENHAN() {
        return NGAYDENHAN;
    }

    /**
     * @param NGAYDENHAN the NGAYDENHAN to set
     */
    public void setNGAYDENHAN(String NGAYDENHAN) {
        this.NGAYDENHAN = NGAYDENHAN;
    }

    /**
     * @return the MASP
     */
    @XmlElement(name = "MASP")
    public String getMASP() {
        return MASP;
    }

    /**
     * @param MASP the MASP to set
     */
    public void setMASP(String MASP) {
        this.MASP = MASP;
    }

    /**
     * @return the TENSP
     */
    @XmlElement(name = "TENSP")
    public String getTENSP() {
        return TENSP;
    }

    /**
     * @param TENSP the TENSP to set
     */
    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    /**
     * @return the NGAYSINH
     */
    @XmlElement(name = "NGAYSINH")
    public String getNGAYSINH() {
        return NGAYSINH;
    }  

    /**
     * @return the CHANNEL
     */
    @XmlElement(name = "CHANNEL")
    public String getCHANNEL() {
        return CHANNEL;
    }

    /**
     * @param CHANNEL the CHANNEL to set
     */
    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }

    /**
     * @return the SODU_MENHGIA
     */
    @XmlElement(name = "SODU_MENHGIA")
    public String getSODU_MENHGIA() {
        return SODU_MENHGIA;
    }

    /**
     * @param SODU_MENHGIA the SODU_MENHGIA to set
     */
    public void setSODU_MENHGIA(String SODU_MENHGIA) {
        this.SODU_MENHGIA = SODU_MENHGIA;
    }

    /**
     * @return the SODU_MENHGIA_QD
     */
    @XmlElement(name = "SODU_MENHGIA_QD")
    public String getSODU_MENHGIA_QD() {
        return SODU_MENHGIA_QD;
    }

    /**
     * @param SODU_MENHGIA_QD the SODU_MENHGIA_QD to set
     */
    public void setSODU_MENHGIA_QD(String SODU_MENHGIA_QD) {
        this.SODU_MENHGIA_QD = SODU_MENHGIA_QD;
    }

    /**
     * @return the CCY
     */
    @XmlElement(name = "CCY")
    public String getCCY() {
        return CCY;
    }

    /**
     * @param CCY the CCY to set
     */
    public void setCCY(String CCY) {
        this.CCY = CCY;
    }

    /**
     * @return the DONVIMO
     */
            @XmlElement(name = "DONVIMO")
    public String getDONVIMO() {
        return DONVIMO;
    }

    /**
     * @param DONVIMO the DONVIMO to set
     */
    public void setDONVIMO(String DONVIMO) {
        this.DONVIMO = DONVIMO;
    }

    /**
     * @return the LOAIGIAODICH
     */
        @XmlElement(name = "LOAIGIAODICH")
    public String getLOAIGIAODICH() {
        return LOAIGIAODICH;
    }

    /**
     * @param LOAIGIAODICH the LOAIGIAODICH to set
     */
    public void setLOAIGIAODICH(String LOAIGIAODICH) {
        this.LOAIGIAODICH = LOAIGIAODICH;
    }

    /**
     * @return the ISSTAFF
     */
    @XmlElement(name = "ISSTAFF")
    public String getISSTAFF() {
        return ISSTAFF;
    }

    /**
     * @param ISSTAFF the ISSTAFF to set
     */
    public void setISSTAFF(String ISSTAFF) {
        this.ISSTAFF = ISSTAFF;
    }

}
