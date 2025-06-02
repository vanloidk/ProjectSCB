/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author kimncm
 */
public class ThuPhiSMS implements java.io.Serializable {

    private String thangnam;
    private String thangnamretry;
    private String lockdate;
    private String phoneno_user;
    private String loaidv;
    private String cif;
    private String custname;
    private String loaikh;
    private String feeamount;
    private String ngaythuphi;
    private String sogiaodich;
    private String tkmd;
    private String matrangthaithuphi;
    private String trangthaithuphi;
    private String matrangthaikhoa;
    private String trangthaikhoa;
    private String tkthuphi;
    private String sdtsms;
    private String ngayhachtoan;

    /**
     * @return the thangnam
     */
    public String getThangnam() {
        return thangnam;
    }

    /**
     * @param thangnam the thangnam to set
     */
    public void setThangnam(String thangnam) {
        this.thangnam = thangnam;
    }

    /**
     * @return the lockdate
     */
    public String getLockdate() {
        return lockdate;
    }

    /**
     * @param lockdate the lockdate to set
     */
    public void setLockdate(String lockdate) {
        this.lockdate = lockdate;
    }

    /**
     * @return the phoneno_user
     */
    public String getPhoneno_user() {
        return phoneno_user;
    }

    /**
     * @param phoneno_user the phoneno_user to set
     */
    public void setPhoneno_user(String phoneno_user) {
        this.phoneno_user = phoneno_user;
    }

    /**
     * @return the loaidv
     */
    public String getLoaidv() {
        return loaidv;
    }

    /**
     * @param loaidv the loaidv to set
     */
    public void setLoaidv(String loaidv) {
        this.loaidv = loaidv;
    }

    /**
     * @return the cif
     */
    public String getCif() {
        return cif;
    }

    /**
     * @param cif the cif to set
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * @return the custname
     */
    public String getCustname() {
        return custname;
    }

    /**
     * @param custname the custname to set
     */
    public void setCustname(String custname) {
        this.custname = custname;
        if (this.custname != null) {
            this.custname = this.custname.replace("&", " va ");
        }
    }

    /**
     * @return the loaikh
     */
    public String getLoaikh() {
        return loaikh;
    }

    /**
     * @param loaikh the loaikh to set
     */
    public void setLoaikh(String loaikh) {
        this.loaikh = loaikh;
    }

    /**
     * @return the feeamount
     */
    public String getFeeamount() {
        return feeamount;
    }

    /**
     * @param feeamount the feeamount to set
     */
    public void setFeeamount(String feeamount) {
        this.feeamount = feeamount;
    }

    /**
     * @return the ngaythuphi
     */
    public String getNgaythuphi() {
        return ngaythuphi;
    }

    /**
     * @param ngaythuphi the ngaythuphi to set
     */
    public void setNgaythuphi(String ngaythuphi) {
        this.ngaythuphi = ngaythuphi;
    }

    /**
     * @return the sogiaodich
     */
    public String getSogiaodich() {
        return sogiaodich;
    }

    /**
     * @param sogiaodich the sogiaodich to set
     */
    public void setSogiaodich(String sogiaodich) {
        this.sogiaodich = sogiaodich;
    }

    /**
     * @return the tkmd
     */
    public String getTkmd() {
        return tkmd;
    }

    /**
     * @param tkmd the tkmd to set
     */
    public void setTkmd(String tkmd) {
        this.tkmd = tkmd;
    }

    /**
     * @return the matrangthaithuphi
     */
    public String getMatrangthaithuphi() {
        return matrangthaithuphi;
    }

    /**
     * @param matrangthaithuphi the matrangthaithuphi to set
     */
    public void setMatrangthaithuphi(String matrangthaithuphi) {
        this.matrangthaithuphi = matrangthaithuphi;
    }

    /**
     * @return the trangthaithuphi
     */
    public String getTrangthaithuphi() {
        return trangthaithuphi;
    }

    /**
     * @param trangthaithuphi the trangthaithuphi to set
     */
    public void setTrangthaithuphi(String trangthaithuphi) {
        this.trangthaithuphi = trangthaithuphi;
    }

    /**
     * @return the matrangthaikhoa
     */
    public String getMatrangthaikhoa() {
        return matrangthaikhoa;
    }

    /**
     * @param matrangthaikhoa the matrangthaikhoa to set
     */
    public void setMatrangthaikhoa(String matrangthaikhoa) {
        this.matrangthaikhoa = matrangthaikhoa;
    }

    /**
     * @return the trangthaikhoa
     */
    public String getTrangthaikhoa() {
        return trangthaikhoa;
    }

    /**
     * @param trangthaikhoa the trangthaikhoa to set
     */
    public void setTrangthaikhoa(String trangthaikhoa) {
        this.trangthaikhoa = trangthaikhoa;
    }

    /**
     * @return the tkthuphi
     */
    public String getTkthuphi() {
        return tkthuphi;
    }

    /**
     * @param tkthuphi the tkthuphi to set
     */
    public void setTkthuphi(String tkthuphi) {
        this.tkthuphi = tkthuphi;
    }

    /**
     * @return the sdtsms
     */
    public String getSdtsms() {
        return sdtsms;
    }

    /**
     * @param sdtsms the sdtsms to set
     */
    public void setSdtsms(String sdtsms) {
        this.sdtsms = sdtsms;
    }

    /**
     * @return the ngayhachtoan
     */
    public String getNgayhachtoan() {
        return ngayhachtoan;
    }

    /**
     * @param ngayhachtoan the ngayhachtoan to set
     */
    public void setNgayhachtoan(String ngayhachtoan) {
        this.ngayhachtoan = ngayhachtoan;
    }

    /**
     * @return the thangnamretry
     */
    public String getThangnamretry() {
        return thangnamretry;
    }

    /**
     * @param thangnamretry the thangnamretry to set
     */
    public void setThangnamretry(String thangnamretry) {
        this.thangnamretry = thangnamretry;
    }

}
