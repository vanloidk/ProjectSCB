/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author KimNCM
 */
public class LoanTxnDetail {

    private String machinhanh;
    private String ngaydenhan;
    private String sotiendenhan;
    private String sotiendatra;
    private String sotienchuatra;
    private String loaitien;
    private String loailich;

    /**
     *
     */
    public LoanTxnDetail() {
    }

    /**
     * @return the machinhanh
     */
    @XmlElement(name = "BrnCode", nillable = true)
    public String getMachinhanh() {
        return machinhanh;
    }

    /**
     * @param machinhanh the machinhanh to set
     */
    public void setMachinhanh(String machinhanh) {
        this.machinhanh = machinhanh;
    }

    /**
     * @return the ngaydenhan
     */
    @XmlElement(name = "MaturityDate", nillable = true)
    public String getNgaydenhan() {
        return ngaydenhan;
    }

    /**
     * @param ngaydenhan the ngaydenhan to set
     */
    public void setNgaydenhan(String ngaydenhan) {
        this.ngaydenhan = ngaydenhan;
    }

    /**
     * @return the sotiendenhan
     */
    @XmlElement(name = "MaturityAmount", nillable = true)
    public String getSotiendenhan() {
        return sotiendenhan;
    }

    /**
     * @param sotiendenhan the sotiendenhan to set
     */
    public void setSotiendenhan(String sotiendenhan) {
        this.sotiendenhan = sotiendenhan;
    }

    /**
     * @return the sotiendatra
     */
    @XmlElement(name = "PaidAmount", nillable = true)
    public String getSotiendatra() {
        return sotiendatra;
    }

    /**
     * @param sotiendatra the sotiendatra to set
     */
    public void setSotiendatra(String sotiendatra) {
        this.sotiendatra = sotiendatra;
    }

    /**
     * @return the sotienchuatra
     */
    @XmlElement(name = "UnpaidAmount", nillable = true)
    public String getSotienchuatra() {
        return sotienchuatra;
    }

    /**
     * @param sotienchuatra the sotienchuatra to set
     */
    public void setSotienchuatra(String sotienchuatra) {
        this.sotienchuatra = sotienchuatra;
    }

    /**
     * @return the loaitien
     */
    @XmlElement(name = "AccountCcy", nillable = true)
    public String getLoaitien() {
        return loaitien;
    }

    /**
     * @param loaitien the loaitien to set
     */
    public void setLoaitien(String loaitien) {
        this.loaitien = loaitien;
    }

    /**
     * @return the loailich
     */
    @XmlElement(name = "TypeSchedule", nillable = true)
    public String getLoailich() {
        return loailich;
    }

    /**
     * @param loailich the loailich to set
     */
    public void setLoailich(String loailich) {
        this.loailich = loailich;
    }

}
