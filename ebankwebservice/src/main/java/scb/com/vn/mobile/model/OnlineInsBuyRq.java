/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.model.MobileRequest;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "OnlineInsBuyRq")
public class OnlineInsBuyRq extends MobileRequest {

    private String idPartner;
    private String idProduct;
    private int idDiscount;
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
    private String feeName;
    private String businessCode;
    private String channel;

    private String refPartner;
    private String refFcubs;
    private String refBC;
    private Long sotien_bhbb;
    private Long sotien_bhtn;
    private Long phi_bhbb;
    private Long phi_bhtn;
    private String accCust;
    private String accIdaccount;
    private String bcAccount;
    private Long discountAamount;
    private Long totalamount;
    private String idchanneluserMaker;
    private String status;
    private String branchcode;
    private String NGAYSINH;
    private String NgayHieuLuc;
    private String IDSANPHAM;
    private String LOAIPHI;
    private String TENPHI;
    private String LOAIQUYENLOI;
    private String TENQUYENLOI;

    /**
     *
     * @return
     */
    public String getIdPartner() {
        return idPartner;
    }

    /**
     * @param idPartner the idPartner to set
     */
    public void setIdPartner(String idPartner) {
        this.idPartner = idPartner;
    }

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
     * @return the idDiscount
     */
    public int getIdDiscount() {
        return idDiscount;
    }

    /**
     * @param idDiscount the idDiscount to set
     */
    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
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
    @XmlElement(name = "TENPHI", nillable = true)
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
     * @return the feeName
     */
    @XmlElement(name = "TENQUYENLOI", nillable = true)
    public String getFeeName() {
        return feeName;
    }

    /**
     * @param feeName the feeName to set
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
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
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return the refPartner
     */
    public String getRefPartner() {
        return refPartner;
    }

    /**
     * @param refPartner the refPartner to set
     */
    public void setRefPartner(String refPartner) {
        this.refPartner = refPartner;
    }

    /**
     * @return the refFcubs
     */
    public String getRefFcubs() {
        return refFcubs;
    }

    /**
     * @param refFcubs the refFcubs to set
     */
    public void setRefFcubs(String refFcubs) {
        this.refFcubs = refFcubs;
    }

    /**
     * @return the refBC
     */
    public String getRefBC() {
        return refBC;
    }

    /**
     * @param refBC the refBC to set
     */
    public void setRefBC(String refBC) {
        this.refBC = refBC;
    }

    /**
     * @return the sotien_bhbb
     */
    public Long getSotien_bhbb() {
        return sotien_bhbb;
    }

    /**
     * @param sotien_bhbb the sotien_bhbb to set
     */
    public void setSotien_bhbb(Long sotien_bhbb) {
        this.sotien_bhbb = sotien_bhbb;
    }

    /**
     * @return the sotien_bhtn
     */
    public Long getSotien_bhtn() {
        return sotien_bhtn;
    }

    /**
     * @param sotien_bhtn the sotien_bhtn to set
     */
    public void setSotien_bhtn(Long sotien_bhtn) {
        this.sotien_bhtn = sotien_bhtn;
    }

    /**
     * @return the phi_bhbb
     */
    @XmlElement(name = "FORMAMT", nillable = true)
    public Long getPhi_bhbb() {
        return phi_bhbb;
    }

    /**
     * @param phi_bhbb the phi_bhbb to set
     */
    public void setPhi_bhbb(Long phi_bhbb) {
        this.phi_bhbb = phi_bhbb;
    }

    /**
     * @return the phi_bhtn
     */
    @XmlElement(name = "DUTYAMT", nillable = true)
    public Long getPhi_bhtn() {
        return phi_bhtn;
    }

    /**
     * @param phi_bhtn the phi_bhtn to set
     */
    public void setPhi_bhtn(Long phi_bhtn) {
        this.phi_bhtn = phi_bhtn;
    }

    /**
     * @return the accCust
     */
    public String getAccCust() {
        return accCust;
    }

    /**
     * @param accCust the accCust to set
     */
    public void setAccCust(String accCust) {
        this.accCust = accCust;
    }

    /**
     * @return the accIdaccount
     */
    @XmlElement(name = "DebitAccount", nillable = true)
    public String getAccIdaccount() {
        return accIdaccount;
    }

    /**
     * @param accIdaccount the accIdaccount to set
     */
    public void setAccIdaccount(String accIdaccount) {
        this.accIdaccount = accIdaccount;
    }

    /**
     * @return the bcAccount
     */
    public String getBcAccount() {
        return bcAccount;
    }

    /**
     * @param bcAccount the bcAccount to set
     */
    public void setBcAccount(String bcAccount) {
        this.bcAccount = bcAccount;
    }

    /**
     * @return the discountAamount
     */
    @XmlElement(name = "Discount", nillable = true)
    public Long getDiscountAamount() {
        return discountAamount;
    }

    /**
     * @param discountAamount the discountAamount to set
     */
    public void setDiscountAamount(Long discountAamount) {
        this.discountAamount = discountAamount;
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
     * @return the idchanneluserMaker
     */
    public String getIdchanneluserMaker() {
        return idchanneluserMaker;
    }

    /**
     * @param idchanneluserMaker the idchanneluserMaker to set
     */
    public void setIdchanneluserMaker(String idchanneluserMaker) {
        this.idchanneluserMaker = idchanneluserMaker;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the branchcode
     */
    public String getBranchcode() {
        return branchcode;
    }

    /**
     * @param branchcode the branchcode to set
     */
    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    /**
     * @return the NGAYSINH
     */
    @XmlElement(name = "NGAYSINH", nillable = true)
    public String getNGAYSINH() {
        return NGAYSINH;
    }

    /**
     * @param NGAYSINH the NGAYSINH to set
     */
    public void setNGAYSINH(String NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
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

}
