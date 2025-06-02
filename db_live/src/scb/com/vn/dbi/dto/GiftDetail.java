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
 * @author kimncm
 */
@XmlRootElement(name = "GiftDetail")
public class GiftDetail implements java.io.Serializable {

    private String id;
    private String title;
    private String codecust;
    private String firsname;
    private String lastname;
    private String ghichu;
    private String loaiqt;
    private String diemquydoi;
    private String soluong;
    private String ngaynhap;
    private String check_sum;

    /**
     * @return the id
     */
    @XmlElement(name = "ID", nillable = true)
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    @XmlElement(name = "TITLE", nillable = true)
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the codecust
     */
    @XmlElement(name = "CodeCust", nillable = true)
    public String getCodecust() {
        return codecust;
    }

    /**
     * @param codecust the codecust to set
     */
    public void setCodecust(String codecust) {
        this.codecust = codecust;
    }

    /**
     * @return the firsname
     */
    @XmlElement(name = "FirstName", nillable = true)
    public String getFirsname() {
        return firsname;
    }

    /**
     * @param firsname the firsname to set
     */
    public void setFirsname(String firsname) {
        this.firsname = firsname;
    }

    /**
     * @return the lastname
     */
    @XmlElement(name = "LastName", nillable = true)
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the ghichu
     */
    @XmlElement(name = "GHICHU", nillable = true)
    public String getGhichu() {
        return ghichu;
    }

    /**
     * @param ghichu the ghichu to set
     */
    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    /**
     * @return the loaiqt
     */
    @XmlElement(name = "LOAIQT", nillable = true)
    public String getLoaiqt() {
        return loaiqt;
    }

    /**
     * @param loaiqt the loaiqt to set
     */
    public void setLoaiqt(String loaiqt) {
        this.loaiqt = loaiqt;
    }

    /**
     * @return the diemquydoi
     */
    @XmlElement(name = "DIEMQUYDOI", nillable = true)
    public String getDiemquydoi() {
        return diemquydoi;
    }

    /**
     * @param diemquydoi the diemquydoi to set
     */
    public void setDiemquydoi(String diemquydoi) {
        this.diemquydoi = diemquydoi;
    }

    /**
     * @return the soluong
     */
    @XmlElement(name = "SOLUONG", nillable = true)
    public String getSoluong() {
        return soluong;
    }

    /**
     * @param soluong the soluong to set
     */
    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    /**
     * @return the ngaynhap
     */
    @XmlElement(name = "NGAYNHAP", nillable = true)
    public String getNgaynhap() {
        return ngaynhap;
    }

    /**
     * @param ngaynhap the ngaynhap to set
     */
    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    /**
     * @return the check_sum
     */
    @XmlElement(name = "CHECKSUM", nillable = true)
    public String getCheck_sum() {
        return check_sum;
    }

    /**
     * @param check_sum the check_sum to set
     */
    public void setCheck_sum(String check_sum) {
        this.check_sum = check_sum;
    }

}
