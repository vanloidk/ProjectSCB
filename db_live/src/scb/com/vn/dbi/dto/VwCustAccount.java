package scb.com.vn.dbi.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author minhndb
 */
public class VwCustAccount implements java.io.Serializable {

    private String branchCode;
    private String custAcNo;
    private String acDesc;
    private String custNo;
    private String ccy;
    private String accountClass;
    private Date acOpenDate;
    private String altAcNo;
    private Character recordStat;
    private Character authStat;
    private String makerId;
    private Date makerDtStamp;
    private String checkerId;
    private Date checkerDtStamp;
    private Character onceAuth;
    private String limitCcy;
    private BigDecimal offlineLimit;
    private Character casAccount;
    private BigDecimal acyOpeningBal;
    private BigDecimal lcyOpeningBal;
    private BigDecimal acyCurrBalance;
    private BigDecimal lcyCurrBalance;
    private BigDecimal acyBlockedAmount;
    private BigDecimal acyAvlBal;
    private Date dateLastCrActivity;
    private Date dateLastDrActivity;
    private Date dateLastDr;
    private Date dateLastCr;
    private String fullName;
    private String idcard;
    private String idcardLoc;
    private String idcardDob;
    private String address;
    private Character customerType;
    private Set<VwCustAccount> pblAttributes = new HashSet<VwCustAccount>(0);

    /**
     * Create a new instance of VwCustAccount
     */
    public VwCustAccount() {
    }

    /**
     *
     * @return
     */
    public String getBranchCode() {
        return this.branchCode;
    }

    /**
     *
     * @param branchCode
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     *
     * @return
     */
    public String getCustAcNo() {
        return this.custAcNo;
    }

    /**
     *
     * @param custAcNo
     */
    public void setCustAcNo(String custAcNo) {
        this.custAcNo = custAcNo;
    }

    /**
     *
     * @return
     */
    public String getAcDesc() {
        return this.acDesc;
    }

    /**
     *
     * @param acDesc
     */
    public void setAcDesc(String acDesc) {
        this.acDesc = acDesc;
    }

    /**
     *
     * @return
     */
    public String getCustNo() {
        return this.custNo;
    }

    /**
     *
     * @param custNo
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    /**
     *
     * @return
     */
    public String getCcy() {
        return this.ccy;
    }

    /**
     *
     * @param ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     *
     * @return
     */
    public String getAccountClass() {
        return this.accountClass;
    }

    /**
     *
     * @param accountClass
     */
    public void setAccountClass(String accountClass) {
        this.accountClass = accountClass;
    }

    /**
     *
     * @return
     */
    public Date getAcOpenDate() {
        return this.acOpenDate;
    }

    /**
     *
     * @param acOpenDate
     */
    public void setAcOpenDate(Date acOpenDate) {
        this.acOpenDate = acOpenDate;
    }

    /**
     *
     * @return
     */
    public String getAltAcNo() {
        return this.altAcNo;
    }

    /**
     *
     * @param altAcNo
     */
    public void setAltAcNo(String altAcNo) {
        this.altAcNo = altAcNo;
    }

    /**
     *
     * @return
     */
    public Character getRecordStat() {
        return this.recordStat;
    }

    /**
     *
     * @param recordStat
     */
    public void setRecordStat(Character recordStat) {
        this.recordStat = recordStat;
    }

    /**
     *
     * @return
     */
    public Character getAuthStat() {
        return this.authStat;
    }

    /**
     *
     * @param authStat
     */
    public void setAuthStat(Character authStat) {
        this.authStat = authStat;
    }

    /**
     *
     * @return
     */
    public String getMakerId() {
        return this.makerId;
    }

    /**
     *
     * @param makerId
     */
    public void setMakerId(String makerId) {
        this.makerId = makerId;
    }

    /**
     *
     * @return
     */
    public Date getMakerDtStamp() {
        return this.makerDtStamp;
    }

    /**
     *
     * @param makerDtStamp
     */
    public void setMakerDtStamp(Date makerDtStamp) {
        this.makerDtStamp = makerDtStamp;
    }

    /**
     *
     * @return
     */
    public String getCheckerId() {
        return this.checkerId;
    }

    /**
     *
     * @param checkerId
     */
    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    /**
     *
     * @return
     */
    public Date getCheckerDtStamp() {
        return this.checkerDtStamp;
    }

    /**
     *
     * @param checkerDtStamp
     */
    public void setCheckerDtStamp(Date checkerDtStamp) {
        this.checkerDtStamp = checkerDtStamp;
    }

    /**
     *
     * @return
     */
    public Character getOnceAuth() {
        return this.onceAuth;
    }

    /**
     *
     * @param onceAuth
     */
    public void setOnceAuth(Character onceAuth) {
        this.onceAuth = onceAuth;
    }

    /**
     *
     * @return
     */
    public String getLimitCcy() {
        return this.limitCcy;
    }

    /**
     *
     * @param limitCcy
     */
    public void setLimitCcy(String limitCcy) {
        this.limitCcy = limitCcy;
    }

    /**
     *
     * @return
     */
    public BigDecimal getOfflineLimit() {
        return this.offlineLimit;
    }

    /**
     *
     * @param offlineLimit
     */
    public void setOfflineLimit(BigDecimal offlineLimit) {
        this.offlineLimit = offlineLimit;
    }

    /**
     *
     * @return
     */
    public Character getCasAccount() {
        return this.casAccount;
    }

    /**
     *
     * @param casAccount
     */
    public void setCasAccount(Character casAccount) {
        this.casAccount = casAccount;
    }

    /**
     *
     * @return
     */
    public BigDecimal getAcyOpeningBal() {
        return this.acyOpeningBal;
    }

    /**
     *
     * @param acyOpeningBal
     */
    public void setAcyOpeningBal(BigDecimal acyOpeningBal) {
        this.acyOpeningBal = acyOpeningBal;
    }

    /**
     *
     * @return
     */
    public BigDecimal getLcyOpeningBal() {
        return this.lcyOpeningBal;
    }

    /**
     *
     * @param lcyOpeningBal
     */
    public void setLcyOpeningBal(BigDecimal lcyOpeningBal) {
        this.lcyOpeningBal = lcyOpeningBal;
    }

    /**
     *
     * @return
     */
    public BigDecimal getAcyCurrBalance() {
        return this.acyCurrBalance;
    }

    /**
     *
     * @param acyCurrBalance
     */
    public void setAcyCurrBalance(BigDecimal acyCurrBalance) {
        this.acyCurrBalance = acyCurrBalance;
    }

    /**
     *
     * @return
     */
    public BigDecimal getLcyCurrBalance() {
        return this.lcyCurrBalance;
    }

    /**
     *
     * @param lcyCurrBalance
     */
    public void setLcyCurrBalance(BigDecimal lcyCurrBalance) {
        this.lcyCurrBalance = lcyCurrBalance;
    }

    /**
     *
     * @return
     */
    public BigDecimal getAcyBlockedAmount() {
        return this.acyBlockedAmount;
    }

    /**
     *
     * @param acyBlockedAmount
     */
    public void setAcyBlockedAmount(BigDecimal acyBlockedAmount) {
        this.acyBlockedAmount = acyBlockedAmount;
    }

    /**
     *
     * @return
     */
    public BigDecimal getAcyAvlBal() {
        return this.acyAvlBal;
    }

    /**
     *
     * @param acyAvlBal
     */
    public void setAcyAvlBal(BigDecimal acyAvlBal) {
        this.acyAvlBal = acyAvlBal;
    }

    /**
     *
     * @return
     */
    public Date getDateLastCrActivity() {
        return this.dateLastCrActivity;
    }

    /**
     *
     * @param dateLastCrActivity
     */
    public void setDateLastCrActivity(Date dateLastCrActivity) {
        this.dateLastCrActivity = dateLastCrActivity;
    }

    /**
     *
     * @return
     */
    public Date getDateLastDrActivity() {
        return this.dateLastDrActivity;
    }

    /**
     *
     * @param dateLastDrActivity
     */
    public void setDateLastDrActivity(Date dateLastDrActivity) {
        this.dateLastDrActivity = dateLastDrActivity;
    }

    /**
     *
     * @return
     */
    public Date getDateLastDr() {
        return this.dateLastDr;
    }

    /**
     *
     * @param dateLastDr
     */
    public void setDateLastDr(Date dateLastDr) {
        this.dateLastDr = dateLastDr;
    }

    /**
     *
     * @return
     */
    public Date getDateLastCr() {
        return this.dateLastCr;
    }

    /**
     *
     * @param dateLastCr
     */
    public void setDateLastCr(Date dateLastCr) {
        this.dateLastCr = dateLastCr;
    }

    /**
     *
     * @return
     */
    public Set<VwCustAccount> getPblAttributes() {
        return pblAttributes;
    }

    /**
     *
     * @param pblAttributes
     */
    public void setPblAttributes(Set<VwCustAccount> pblAttributes) {
        this.pblAttributes = pblAttributes;
    }

    /**
     *
     * @return
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    public String getIdcardLoc() {
        return idcardLoc;
    }

    /**
     *
     * @param idcardLoc
     */
    public void setIdcardLoc(String idcardLoc) {
        this.idcardLoc = idcardLoc;
    }

    /**
     *
     * @return
     */
    public String getIdcardDob() {
        return idcardDob;
    }

    /**
     *
     * @param idcardDob
     */
    public void setIdcardDob(String idcardDob) {
        this.idcardDob = idcardDob;
    }

    /**
     *
     * @return
     */
    public Character getCustomerType() {
        return customerType;
    }

    /**
     *
     * @param customerType
     */
    public void setCustomerType(Character customerType) {
        this.customerType = customerType;
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
}
