/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hieudt
 */
@XmlRootElement(name = "ccinfo")
public class CIMSCustInfo implements Comparable<CIMSCustInfo> {

    private String CIF;
    private String HoTen;
    private String CMND;
    private String NgaySinh;
    private String GioiTinh;
    private String NganhNghe;
    private String SoDienThoai;
    private String DiaChi;
    private String Email;
    private String VIP;
    private String donViMoCif;
    private String vipInfo;

    /**
     * Create a new instance of CIMSCustInfo
     */
    public CIMSCustInfo() {
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CIF")
    public String getCif() {
        return this.CIF;
    }

    /**
     *
     * @param Cif
     */
    public void setCif(String Cif) {
        this.CIF = Cif;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "HoTen")
    public String getHoTen() {
        return this.HoTen;
    }

    /**
     *
     * @param HoTen
     */
    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CMND")
    public String getCMND() {
        return this.CMND;
    }

    /**
     *
     * @param CMND
     */
    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "NgaySinh")
    public String getNgaySinh() {
        return this.NgaySinh;
    }

    /**
     *
     * @param NgaySinh
     */
    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "GioiTinh")
    public String getGioiTinh() {
        return this.GioiTinh;
    }

    /**
     *
     * @param GioiTinh
     */
    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "NganhNghe")
    public String getNganhNghe() {
        return this.NganhNghe;
    }

    /**
     *
     * @param NganhNghe
     */
    public void setNganhNghe(String NganhNghe) {
        this.NganhNghe = NganhNghe;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "SoDienThoai")
    public String getSoDienThoai() {
        return this.SoDienThoai;
    }

    /**
     *
     * @param SoDienThoai
     */
    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "DiaChi")
    public String getDiaChi() {
        return this.DiaChi;
    }

    /**
     *
     * @param DiaChi
     */
    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "Email")
    public String getEmail() {
        return this.Email;
    }

    /**
     *
     * @param Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "VIP")
    public String getVIP() {
        return this.VIP;
    }

    /**
     *
     * @param VIP
     */
    public void setVIP(String VIP) {
        this.VIP = VIP;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "DON_VI")
    public String getDonViMoCif() {
        return donViMoCif;
    }

    /**
     *
     * @param donViMoCif
     */
    public void setDonViMoCif(String donViMoCif) {
        this.donViMoCif = donViMoCif;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "VIPInfo")
    public String getVipInfo() {
        return vipInfo;
    }

    /**
     *
     * @param vipInfo
     */
    public void setVipInfo(String vipInfo) {
        this.vipInfo = vipInfo;
    }

    @Override
    public int compareTo(CIMSCustInfo other) {
        return this.getCif().compareTo(other.getCif());
    }

    /**
     *
     * @param VIP
     */
    public void setVIPVipInfo(String VIP) {
        if (this.vipInfo == null || this.vipInfo.isEmpty()) {
            this.VIP = VIP;
        } else {
            StringBuilder str = new StringBuilder();
            str.append(VIP).append("_").append(this.vipInfo);
            this.VIP = str.toString();
        }
    }

}
