/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.model.MobileResponse;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "RetrieveOnlineInsBuyHistoryRp")
public class RetrieveOnlineInsBuyHistoryRp extends MobileResponse {

    private String idProduct;

    private String regName;
    private String benName;
    private String addService;
    private String addBen;
    private String address;
    private String regPhone;
    private String regMail;
    private String soxe;
    private String sokhung;
    private String somay;
    private String loaixe;
    private String sex;
    private String idcard;
    private String businessCode;
    private Long totalamount;
    private String dob;
    private String NgayHieuLuc;
    private String insdateMaker;
    private String DateBuy;
    private String ExpiredPeriod;

    /**
     * @return the idProduct
     */
    @XmlElement(name = "IDSANPHAM", nillable = true)
    public String getIdProduct() {
        return idProduct;
    }

    /**
     * @param idProduct the idProduct to set
     */
    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * @return the regName
     */
    @XmlElement(name = "BuyerFullName", nillable = true)
    public String getRegName() {
        return regName;
    }

    /**
     * @param regName the regName to set
     */
    public void setRegName(String regName) {
        this.regName = regName;
    }

    /**
     * @return the benName
     */
    @XmlElement(name = "BeneFullName", nillable = true)
    public String getBenName() {
        return benName;
    }

    /**
     * @param benName the benName to set
     */
    public void setBenName(String benName) {
        this.benName = benName;
    }

    /**
     * @return the addService
     */
    @XmlElement(name = "BuyerAddress", nillable = true)
    public String getAddService() {
        return addService;
    }

    /**
     * @param addService the addService to set
     */
    public void setAddService(String addService) {
        this.addService = addService;
    }

    /**
     * @return the addBen
     */
    @XmlElement(name = "BeneAddress", nillable = true)
    public String getAddBen() {
        return addBen;
    }

    /**
     * @param addBen the addBen to set
     */
    public void setAddBen(String addBen) {
        this.addBen = addBen;
    }

    /**
     * @return the address
     */
    @XmlElement(name = "OwnerAddress", nillable = true)
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the regPhone
     */
    @XmlElement(name = "BuyerPhone", nillable = true)
    public String getRegPhone() {
        return regPhone;
    }

    /**
     * @param regPhone the regPhone to set
     */
    public void setRegPhone(String regPhone) {
        this.regPhone = regPhone;
    }

    /**
     * @return the regMail
     */
    @XmlElement(name = "BuyerEmail", nillable = true)
    public String getRegMail() {
        return regMail;
    }

    /**
     * @param regMail the regMail to set
     */
    public void setRegMail(String regMail) {
        this.regMail = regMail;
    }

    /**
     * @return the soxe
     */
    @XmlElement(name = "SoXe", nillable = true)
    public String getSoxe() {
        return soxe;
    }

    /**
     * @param soxe the soxe to set
     */
    public void setSoxe(String soxe) {
        this.soxe = soxe;
    }

    /**
     * @return the sokhung
     */
    @XmlElement(name = "SoKhung", nillable = true)
    public String getSokhung() {
        return sokhung;
    }

    /**
     * @param sokhung the sokhung to set
     */
    public void setSokhung(String sokhung) {
        this.sokhung = sokhung;
    }

    /**
     * @return the somay
     */
    @XmlElement(name = "SoMay", nillable = true)
    public String getSomay() {
        return somay;
    }

    /**
     * @param somay the somay to set
     */
    public void setSomay(String somay) {
        this.somay = somay;
    }

    /**
     * @return the loaixe
     */
    @XmlElement(name = "LoaiPhi", nillable = true)
    public String getLoaixe() {
        return loaixe;
    }

    /**
     * @param loaixe the loaixe to set
     */
    public void setLoaixe(String loaixe) {
        this.loaixe = loaixe;
    }

    /**
     * @return the sex
     */
    @XmlElement(name = "GIOITINH", nillable = true)
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the idcard
     */
    @XmlElement(name = "CMND", nillable = true)
    public String getIdcard() {
        return idcard;
    }

    /**
     * @param idcard the idcard to set
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * @return the businessCode
     */
    @XmlElement(name = "NHANHIEU", nillable = true)
    public String getBusinessCode() {
        return businessCode;
    }

    /**
     * @param businessCode the businessCode to set
     */
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    /**
     * @return the totalamount
     */
    @XmlElement(name = "PayAmt", nillable = true)
    public Long getTotalamount() {
        return totalamount;
    }

    /**
     * @param totalamount the totalamount to set
     */
    public void setTotalamount(Long totalamount) {
        this.totalamount = totalamount;
    }

    /**
     * @return the NgayHieuLuc
     */
    @XmlElement(name = "NgayHieuLuc", nillable = true)
    public String getNgayHieuLuc() {
        return NgayHieuLuc;
    }

    /**
     * @param NgayHieuLuc the NgayHieuLuc to set
     */
    public void setNgayHieuLuc(String NgayHieuLuc) {
        this.NgayHieuLuc = NgayHieuLuc;
    }

    /**
     * @return the insdateMaker
     */
    public String getInsdateMaker() {
        return insdateMaker;
    }

    /**
     * @param insdateMaker the insdateMaker to set
     */
    public void setInsdateMaker(String insdateMaker) {
        this.insdateMaker = insdateMaker;
    }

    /**
     * @return the DateBuy
     */
    @XmlElement(name = "DateBuy", nillable = true)
    public String getDateBuy() {
        return DateBuy;
    }

    /**
     * @param DateBuy the DateBuy to set
     */
    public void setDateBuy(String DateBuy) {
        this.DateBuy = DateBuy;
    }

    /**
     * @return the ExpiredPeriod
     */
    @XmlElement(name = "ExpiredPeriod", nillable = true)
    public String getExpiredPeriod() {
        return ExpiredPeriod;
    }

    /**
     * @param ExpiredPeriod the ExpiredPeriod to set
     */
    public void setExpiredPeriod(String ExpiredPeriod) {
        this.ExpiredPeriod = ExpiredPeriod;
    }

    /**
     * @return the dob
     */
    @XmlElement(name = "NGAYSINH", nillable = true)
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

}
