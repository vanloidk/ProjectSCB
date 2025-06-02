/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.internal.evnit.webservice.service;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author thachdn
 */
public class Bill {
    private String madvql;
    private String hoadonid;
    private String loaihoadon;
    private String ps;
    private String makh;
    
    private String tenkh;
    private String diachikh;
    private String nam;
    private String thang;
    private String ky;
    
    private String soseri;
    private String ngaydauky;
    private String ngaycuoiky;
    private String no;
    private String tienno;
    private String tiennostr;
    private String thueno;
    
    private String socongto;
    private String soho;
    private String dientieuthu;
    private String chisodauky;
    private String chisocuoiky;
    
    private String chitietgia;
    private String chitietdntt;
    private String chitiettien;
    private String hinhthucthanhtoan;
    private String sdt;
    
    private String magcs;

    public String getTienno() {
        return tienno;
    }

    public void setTienno(String tienno) {
        this.tienno = tienno;
    }

    public String getTiennostr() {
        return tiennostr;
    }

    public void setTiennostr(String tiennostr) {
        this.tiennostr = tiennostr;
    }
    
    @XmlElement(name = "madvql")
    public String getMadvql() {
        return madvql;
    }

    public void setMadvql(String madvql) {
        this.madvql = madvql;
    }

    @XmlElement(name = "hoadonid")
    public String getHoadonid() {
        return hoadonid;
    }

    public void setHoadonid(String hoadonid) {
        this.hoadonid = hoadonid;
    }

    @XmlElement(name = "loaihoadon")
    public String getLoaihoadon() {
        return loaihoadon;
    }

    public void setLoaihoadon(String loaihoadon) {
        this.loaihoadon = loaihoadon;
    }

    @XmlElement(name = "ps")
    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    @XmlElement(name = "makh")
    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    @XmlElement(name = "tenkh")
    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    @XmlElement(name = "diachikh")
    public String getDiachikh() {
        return diachikh;
    }

    public void setDiachikh(String diachikh) {
        this.diachikh = diachikh;
    }

    @XmlElement(name = "nam")
    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    @XmlElement(name = "thang")
    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    @XmlElement(name = "ky")
    public String getKy() {
        return ky;
    }

    public void setKy(String ky) {
        this.ky = ky;
    }

    @XmlElement(name = "soseri")
    public String getSoseri() {
        return soseri;
    }

    public void setSoseri(String soseri) {
        this.soseri = soseri;
    }

    @XmlElement(name = "ngaydauky")
    public String getNgaydauky() {
        return ngaydauky;
    }

    public void setNgaydauky(String ngaydauky) {
        this.ngaydauky = ngaydauky;
    }

    @XmlElement(name = "ngaycuoiky")
    public String getNgaycuoiky() {
        return ngaycuoiky;
    }

    public void setNgaycuoiky(String ngaycuoiky) {
        this.ngaycuoiky = ngaycuoiky;
    }

    @XmlElement(name = "no")
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @XmlElement(name = "thueno")
    public String getThueno() {
        return thueno;
    }

    public void setThueno(String thueno) {
        this.thueno = thueno;
    }

    @XmlElement(name = "socongto")
    public String getSocongto() {
        return socongto;
    }

    public void setSocongto(String socongto) {
        this.socongto = socongto;
    }

    @XmlElement(name = "soho")
    public String getSoho() {
        return soho;
    }

    public void setSoho(String soho) {
        this.soho = soho;
    }

    @XmlElement(name = "dientieuthu")
    public String getDientieuthu() {
        return dientieuthu;
    }

    public void setDientieuthu(String dientieuthu) {
        this.dientieuthu = dientieuthu;
    }

    @XmlElement(name = "chisodauky")
    public String getChisodauky() {
        return chisodauky;
    }

    public void setChisodauky(String chisodauky) {
        this.chisodauky = chisodauky;
    }

    @XmlElement(name = "chisocuoiky")
    public String getChisocuoiky() {
        return chisocuoiky;
    }

    public void setChisocuoiky(String chisocuoiky) {
        this.chisocuoiky = chisocuoiky;
    }

    @XmlElement(name = "chitietgia")
    public String getChitietgia() {
        return chitietgia;
    }

    public void setChitietgia(String chitietgia) {
        this.chitietgia = chitietgia;
    }

    @XmlElement(name = "chitietdntt")
    public String getChitietdntt() {
        return chitietdntt;
    }

    public void setChitietdntt(String chitietdntt) {
        this.chitietdntt = chitietdntt;
    }

    @XmlElement(name = "chitiettien")
    public String getChitiettien() {
        return chitiettien;
    }

    public void setChitiettien(String chitiettien) {
        this.chitiettien = chitiettien;
    }

    @XmlElement(name = "hinhthucthanhtoan")
    public String getHinhthucthanhtoan() {
        return hinhthucthanhtoan;
    }

    public void setHinhthucthanhtoan(String hinhthucthanhtoan) {
        this.hinhthucthanhtoan = hinhthucthanhtoan;
    }

    @XmlElement(name = "sdt")
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @XmlElement(name = "magcs")
    public String getMagcs() {
        return magcs;
    }

    public void setMagcs(String magcs) {
        this.magcs = magcs;
    }
    
    
    
}
