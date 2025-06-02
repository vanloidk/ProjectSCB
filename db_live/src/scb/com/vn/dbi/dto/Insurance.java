package scb.com.vn.dbi.dto;

import java.util.Date;

/**
 *
 * @author minhndb
 */
public class Insurance implements java.io.Serializable {

    private int idREG;
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
    private Date dob;
    private String sex;
    private String idcard;
    private String feeName;
    private String businessCode;
    private String channel;
    private Date regDate;
    private Date validDate;
    private Date expireDate;
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
    private Date insdateMaker;
    private String idchanneluserChecker;
    private Date insdateChecker;
    private Date transdate;
    private Date paydateFcubs;
    private String status;
    private String branchcode;
    private String col_code;
    private String checksum;

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Create a new instance of Insurance
     */
    public Insurance() {
    }

    /**
     *
     * @param idREG
     * @param idPartner
     * @param idProduct
     * @param idDiscount
     * @param regName
     * @param benName
     * @param addService
     * @param addBen
     * @param address
     * @param regPhone
     * @param regMail
     * @param soxe
     * @param sokhung
     * @param somay
     * @param loaixe
     * @param dob
     * @param sex
     * @param idcard
     * @param feeName
     * @param businessCode
     * @param channel
     * @param regDate
     * @param validDate
     * @param expireDate
     * @param refPartner
     * @param refFcubs
     * @param refBC
     * @param sotien_bhbb
     * @param sotien_bhtn
     * @param phi_bhbb
     * @param phi_bhtn
     * @param accCust
     * @param accIdaccount
     * @param bcAccount
     * @param discountAamount
     * @param totalamount
     * @param idchanneluserMaker
     * @param insdateMaker
     * @param idchanneluserChecker
     * @param insdateChecker
     * @param transdate
     * @param paydateFcubs
     * @param status
     * @param branchcode
     * @param col_code
     * @param checksum
     */
    public Insurance(int idREG, String idPartner, String idProduct, int idDiscount, String regName, String benName, String addService, String addBen, String address, String regPhone, String regMail, String soxe, String sokhung, String somay, String loaixe, Date dob, String sex, String idcard, String feeName, String businessCode, String channel, Date regDate, Date validDate, Date expireDate, String refPartner, String refFcubs, String refBC, Long sotien_bhbb, Long sotien_bhtn, Long phi_bhbb, Long phi_bhtn, String accCust, String accIdaccount, String bcAccount, Long discountAamount, Long totalamount, String idchanneluserMaker, Date insdateMaker, String idchanneluserChecker, Date insdateChecker, Date transdate, Date paydateFcubs, String status, String branchcode, String col_code, String checksum) {
        this.idREG = idREG;
        this.idPartner = idPartner;
        this.idProduct = idProduct;
        this.idDiscount = idDiscount;
        this.regName = regName;
        this.benName = benName;
        this.addService = addService;
        this.addBen = addBen;
        this.address = address;
        this.regPhone = regPhone;
        this.regMail = regMail;
        this.soxe = soxe;
        this.sokhung = sokhung;
        this.somay = somay;
        this.loaixe = loaixe;
        this.dob = dob;
        this.sex = sex;
        this.idcard = idcard;
        this.feeName = feeName;
        this.businessCode = businessCode;
        this.channel = channel;
        this.regDate = regDate;
        this.validDate = validDate;
        this.expireDate = expireDate;
        this.refPartner = refPartner;
        this.refFcubs = refFcubs;
        this.refBC = refBC;
        this.sotien_bhbb = sotien_bhbb;
        this.sotien_bhtn = sotien_bhtn;
        this.phi_bhbb = phi_bhbb;
        this.phi_bhtn = phi_bhtn;
        this.accCust = accCust;
        this.accIdaccount = accIdaccount;
        this.bcAccount = bcAccount;
        this.discountAamount = discountAamount;
        this.totalamount = totalamount;
        this.idchanneluserMaker = idchanneluserMaker;
        this.insdateMaker = insdateMaker;
        this.idchanneluserChecker = idchanneluserChecker;
        this.insdateChecker = insdateChecker;
        this.transdate = transdate;
        this.paydateFcubs = paydateFcubs;
        this.status = status;
        this.branchcode = branchcode;
        this.col_code = col_code;
        this.checksum = checksum;
    }

    /**
     *
     * @param idREG
     * @param idPartner
     * @param idProduct
     * @param idDiscount
     * @param regName
     * @param benName
     * @param addService
     * @param addBen
     * @param address
     * @param regPhone
     * @param regMail
     * @param soxe
     * @param sokhung
     * @param somay
     * @param loaixe
     * @param dob
     * @param sex
     * @param idcard
     * @param feeName
     * @param businessCode
     * @param channel
     * @param regDate
     * @param validDate
     * @param expireDate
     * @param refPartner
     * @param refFcubs
     * @param refBC
     * @param sotien_bhbb
     * @param sotien_bhtn
     * @param phi_bhbb
     * @param phi_bhtn
     * @param totalamount
     * @param discountAamount
     * @param accCust
     * @param accIdaccount
     * @param bcAccount
     * @param idchanneluserMaker
     * @param insdateMaker
     * @param idchanneluserChecker
     * @param insdateChecker
     * @param transdate
     * @param paydateFcubs
     * @param status
     * @param branchcode
     */
    public Insurance(int idREG, String idPartner, String idProduct, int idDiscount, String regName, String benName, String addService, String addBen, String address, String regPhone, String regMail, String soxe, String sokhung,
            String somay, String loaixe, Date dob, String sex, String idcard, String feeName, String businessCode, String channel, Date regDate, Date validDate, Date expireDate, String refPartner, String refFcubs, String refBC, Long sotien_bhbb,
            Long sotien_bhtn, Long phi_bhbb, Long phi_bhtn, Long totalamount, Long discountAamount, String accCust, String accIdaccount, String bcAccount, String idchanneluserMaker, Date insdateMaker, String idchanneluserChecker,
            Date insdateChecker, Date transdate, Date paydateFcubs, String status, String branchcode) {
        this.idREG = idREG;
        this.idPartner = idPartner;
        this.idProduct = idProduct;
        this.idDiscount = idDiscount;
        this.regName = regName;
        this.benName = benName;
        this.addService = addService;
        this.addBen = addBen;
        this.address = address;
        this.regPhone = regPhone;
        this.regMail = regMail;
        this.soxe = soxe;
        this.sokhung = sokhung;
        this.somay = somay;
        this.loaixe = loaixe;
        this.dob = dob;
        this.sex = sex;
        this.idcard = idcard;
        this.feeName = feeName;
        this.businessCode = businessCode;
        this.channel = channel;
        this.regDate = regDate;
        this.validDate = validDate;
        this.expireDate = expireDate;
        this.refPartner = soxe;
        this.refFcubs = soxe;
        this.refBC = soxe;
        this.sotien_bhbb = sotien_bhbb;
        this.sotien_bhtn = sotien_bhtn;
        this.phi_bhbb = phi_bhbb;
        this.phi_bhtn = phi_bhtn;
        this.idchanneluserMaker = idchanneluserMaker;
        this.insdateMaker = insdateMaker;
        this.idchanneluserChecker = idchanneluserChecker;
        this.insdateChecker = insdateChecker;
        this.totalamount = totalamount;
        this.discountAamount = discountAamount;
        this.accCust = accCust;
        this.accIdaccount = accIdaccount;
        this.bcAccount = bcAccount;
        this.transdate = transdate;
        this.paydateFcubs = paydateFcubs;
        this.status = status;
        this.branchcode = branchcode;
    }

    /**
     *
     * @return
     */
    public String getCol_code() {
        return col_code;
    }

    /**
     *
     * @param col_code
     */
    public void setCol_code(String col_code) {
        this.col_code = col_code;
    }

    /**
     *
     * @return
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     *
     * @param checksum
     */
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    /**
     *
     * @return
     */
    public int getIdREG() {
        return idREG;
    }

    /**
     *
     * @param idREG
     */
    public void setIdREG(int idREG) {
        this.idREG = idREG;
    }

    /**
     *
     * @return
     */
    public String getIdPartner() {
        return idPartner;
    }

    /**
     *
     * @param idPartner
     */
    public void setIdPartner(String idPartner) {
        this.idPartner = idPartner;
    }

    /**
     *
     * @return
     */
    public String getIdProduct() {
        return idProduct;
    }

    /**
     *
     * @param idProduct
     */
    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    /**
     *
     * @return
     */
    public int getIdDiscount() {
        return idDiscount;
    }

    /**
     *
     * @param idDiscount
     */
    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    /**
     *
     * @return
     */
    public String getRegName() {
        return regName;
    }

    /**
     *
     * @param regName
     */
    public void setRegName(String regName) {
        this.regName = regName;
    }

    /**
     *
     * @return
     */
    public String getBenName() {
        return benName;
    }

    /**
     *
     * @param benName
     */
    public void setBenName(String benName) {
        this.benName = benName;
    }

    /**
     *
     * @return
     */
    public String getAddService() {
        return addService;
    }

    /**
     *
     * @param addService
     */
    public void setAddService(String addService) {
        this.addService = addService;
    }

    /**
     *
     * @return
     */
    public String getAddBen() {
        return addBen;
    }

    /**
     *
     * @param addBen
     */
    public void setAddBen(String addBen) {
        this.addBen = addBen;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getRegPhone() {
        return regPhone;
    }

    /**
     *
     * @param regPhone
     */
    public void setRegPhone(String regPhone) {
        this.regPhone = regPhone;
    }

    /**
     *
     * @return
     */
    public String getRegMail() {
        return regMail;
    }

    /**
     *
     * @param regMail
     */
    public void setRegMail(String regMail) {
        this.regMail = regMail;
    }

    /**
     *
     * @return
     */
    public String getSoxe() {
        return soxe;
    }

    /**
     *
     * @param soxe
     */
    public void setSoxe(String soxe) {
        this.soxe = soxe;
    }

    /**
     *
     * @return
     */
    public String getSokhung() {
        return sokhung;
    }

    /**
     *
     * @param sokhung
     */
    public void setSokhung(String sokhung) {
        this.sokhung = sokhung;
    }

    /**
     *
     * @return
     */
    public String getSomay() {
        return somay;
    }

    /**
     *
     * @param somay
     */
    public void setSomay(String somay) {
        this.somay = somay;
    }

    /**
     *
     * @return
     */
    public String getLoaixe() {
        return loaixe;
    }

    /**
     *
     * @param loaixe
     */
    public void setLoaixe(String loaixe) {
        this.loaixe = loaixe;
    }

    /**
     *
     * @return
     */
    public Date getDob() {
        return dob;
    }

    /**
     *
     * @param dob
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     *
     * @return
     */
    public String getSex() {
        return sex;
    }

    /**
     *
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     *
     * @return
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     *
     * @param idcard
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     *
     * @return
     */
    public String getFeeName() {
        return feeName;
    }

    /**
     *
     * @param feeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    /**
     *
     * @return
     */
    public String getBusinessCode() {
        return businessCode;
    }

    /**
     *
     * @param businessCode
     */
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    /**
     *
     * @return
     */
    public String getChannel() {
        return channel;
    }

    /**
     *
     * @param channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     *
     * @return
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     *
     * @param regDate
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    /**
     *
     * @return
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     *
     * @param validDate
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     *
     * @return
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     *
     * @param expireDate
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     *
     * @return
     */
    public String getRefPartner() {
        return refPartner;
    }

    /**
     *
     * @param refPartner
     */
    public void setRefPartner(String refPartner) {
        this.refPartner = refPartner;
    }

    /**
     *
     * @return
     */
    public String getRefFcubs() {
        return refFcubs;
    }

    /**
     *
     * @param refFcubs
     */
    public void setRefFcubs(String refFcubs) {
        this.refFcubs = refFcubs;
    }

    /**
     *
     * @return
     */
    public String getRefBC() {
        return refBC;
    }

    /**
     *
     * @param refBC
     */
    public void setRefBC(String refBC) {
        this.refBC = refBC;
    }

    /**
     *
     * @return
     */
    public Long getSotien_bhbb() {
        return sotien_bhbb;
    }

    /**
     *
     * @param sotien_bhbb
     */
    public void setSotien_bhbb(Long sotien_bhbb) {
        this.sotien_bhbb = sotien_bhbb;
    }

    /**
     *
     * @return
     */
    public Long getSotien_bhtn() {
        return sotien_bhtn;
    }

    /**
     *
     * @param sotien_bhtn
     */
    public void setSotien_bhtn(Long sotien_bhtn) {
        this.sotien_bhtn = sotien_bhtn;
    }

    /**
     *
     * @return
     */
    public Long getPhi_bhbb() {
        return phi_bhbb;
    }

    /**
     *
     * @param phi_bhbb
     */
    public void setPhi_bhbb(Long phi_bhbb) {
        this.phi_bhbb = phi_bhbb;
    }

    /**
     *
     * @return
     */
    public Long getPhi_bhtn() {
        return phi_bhtn;
    }

    /**
     *
     * @param phi_bhtn
     */
    public void setPhi_bhtn(Long phi_bhtn) {
        this.phi_bhtn = phi_bhtn;
    }

    /**
     *
     * @return
     */
    public String getAccCust() {
        return accCust;
    }

    /**
     *
     * @param accCust
     */
    public void setAccCust(String accCust) {
        this.accCust = accCust;
    }

    /**
     *
     * @return
     */
    public String getAccIdaccount() {
        return accIdaccount;
    }

    /**
     *
     * @param accIdaccount
     */
    public void setAccIdaccount(String accIdaccount) {
        this.accIdaccount = accIdaccount;
    }

    /**
     *
     * @return
     */
    public String getBcAccount() {
        return bcAccount;
    }

    /**
     *
     * @param bcAccount
     */
    public void setBcAccount(String bcAccount) {
        this.bcAccount = bcAccount;
    }

    /**
     *
     * @return
     */
    public Long getDiscountAamount() {
        return discountAamount;
    }

    /**
     *
     * @param discountAamount
     */
    public void setDiscountAamount(Long discountAamount) {
        this.discountAamount = discountAamount;
    }

    /**
     *
     * @return
     */
    public Long getTotalamount() {
        return totalamount;
    }

    /**
     *
     * @param totalamount
     */
    public void setTotalamount(Long totalamount) {
        this.totalamount = totalamount;
    }

    /**
     *
     * @return
     */
    public String getIdchanneluserMaker() {
        return idchanneluserMaker;
    }

    /**
     *
     * @param idchanneluserMaker
     */
    public void setIdchanneluserMaker(String idchanneluserMaker) {
        this.idchanneluserMaker = idchanneluserMaker;
    }

    /**
     *
     * @return
     */
    public Date getInsdateMaker() {
        return insdateMaker;
    }

    /**
     *
     * @param insdateMaker
     */
    public void setInsdateMaker(Date insdateMaker) {
        this.insdateMaker = insdateMaker;
    }

    /**
     *
     * @return
     */
    public String getIdchanneluserChecker() {
        return idchanneluserChecker;
    }

    /**
     *
     * @param idchanneluserChecker
     */
    public void setIdchanneluserChecker(String idchanneluserChecker) {
        this.idchanneluserChecker = idchanneluserChecker;
    }

    /**
     *
     * @return
     */
    public Date getInsdateChecker() {
        return insdateChecker;
    }

    /**
     *
     * @param insdateChecker
     */
    public void setInsdateChecker(Date insdateChecker) {
        this.insdateChecker = insdateChecker;
    }

    /**
     *
     * @return
     */
    public Date getTransdate() {
        return transdate;
    }

    /**
     *
     * @param transdate
     */
    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    /**
     *
     * @return
     */
    public Date getPaydateFcubs() {
        return paydateFcubs;
    }

    /**
     *
     * @param paydateFcubs
     */
    public void setPaydateFcubs(Date paydateFcubs) {
        this.paydateFcubs = paydateFcubs;
    }

    /**
     *
     * @return
     */
    public String getBranchcode() {
        return branchcode;
    }

    /**
     *
     * @param branchcode
     */
    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

}
