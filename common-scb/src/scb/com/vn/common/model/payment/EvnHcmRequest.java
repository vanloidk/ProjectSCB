/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "EVNHCM")
public class EvnHcmRequest
{
    private String maKH;
    private String soTien;
    private String maGiaoDich;
    private String bankID;
    private String tenKh;
    private String diaChi;
    private String maDL;
    private String tuThoiDiem;
    private String denThoiDiem;

    @XmlElement(name = "MAKH")
    public String getMaKH()
    {
        return maKH;
    }

    public void setMaKH(String maKH)
    {
        this.maKH = maKH;
    }

    @XmlElement(name = "SOTIEN")
    public String getSoTien()
    {
        return soTien;
    }

    public void setSoTien(String soTien)
    {
        this.soTien = soTien;
    }

    @XmlElement(name = "MAGIAODICH")
    public String getMaGiaoDich()
    {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich)
    {
        this.maGiaoDich = maGiaoDich;
    }

    @XmlElement(name = "BANKID")
    public String getBankID()
    {
        return bankID;
    }

    public void setBankID(String bankID)
    {
        this.bankID = bankID;
    }

    @XmlElement(name = "TENKH")
    public String getTenKh()
    {
        return tenKh;
    }

    public void setTenKh(String tenKh)
    {
        this.tenKh = tenKh;
    }

    @XmlElement(name = "DIACHI")
    public String getDiaChi()
    {
        return diaChi;
    }

    public void setDiaChi(String diaChi)
    {
        this.diaChi = diaChi;
    }

    @XmlElement(name = "MADL")
    public String getMaDL()
    {
        return maDL;
    }

    public void setMaDL(String maDL)
    {
        this.maDL = maDL;
    }

    @XmlElement(name = "TUTHOIDIEM")
    public String getTuThoiDiem()
    {
        return tuThoiDiem;
    }

    public void setTuThoiDiem(String tuThoiDiem)
    {
        this.tuThoiDiem = tuThoiDiem;
    }

    @XmlElement(name = "DENTHOIDIEM")
    public String getDenThoiDiem()
    {
        return denThoiDiem;
    }

    public void setDenThoiDiem(String denThoiDiem)
    {
        this.denThoiDiem = denThoiDiem;
    }
    
}
