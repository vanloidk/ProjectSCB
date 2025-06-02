/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.register;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "THONG_TIN_DANG_KY")
public class RegisterInfoDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    private String registerId;
    private String registerType;
    private String insDate;
    private String idChannel;
    private String customerNo;
    private String custName;
    private String custTel;
    private String custAddress;
    private String sourceAccount;
    private String issueType;
    private String cardType;
    private String fullName;
    private String relationship;
    private String idNumber;
    private String issueIdDate;
    private String issueIdPlace;
    private String sex;
    private String cardAccountNo;
    private String cardNo;
    private String cardRecieveMode;
    private String address;
    private String branchCode;
    private String loanPurpose;
    private String loanAmount;
    private String expectedTime;
    private String expectInterest;
    private String rePaymentSource;
    private String idChannelUser;

    @XmlElement(name = "MA_DANG_KY")
    public String getRegisterId() {
        return registerId;
    }

    @XmlElement(name = "LOAI_HINH_DANG_KY")
    public String getRegisterType() {
        return registerType;
    }

    @XmlElement(name = "NGAY_DANG_KY")
    public String getInsDate() {
        return insDate;
    }

    @XmlElement(name = "KENH_TIEP_NHAN")
    public String getIdChannel() {
        return idChannel;
    }

    @XmlElement(name = "MA_KHACH_HANG")
    public String getCustomerNo() {
        return customerNo;
    }

    @XmlElement(name = "TEN_KHACH_HANG")
    public String getCustName() {
        return custName;
    }

    @XmlElement(name = "DIEN_THOAI_KH")
    public String getCustTel() {
        return custTel;
    }

    @XmlElement(name = "DIA_CHI_KH")
    public String getCustAddress() {
        return custAddress;
    }

    @XmlElement(name = "TAI_KHOAN_NGUON")
    public String getSourceAccount() {
        return sourceAccount;
    }

    @XmlElement(name = "HINH_THUC_PHAT_HANH")
    public String getIssueType() {
        return issueType;
    }

    @XmlElement(name = "TIN_DUNG")
    public String getCardType() {
        return cardType;
    }

    @XmlElement(name = "HO_VA_TEN")
    public String getFullName() {
        return fullName;
    }

    @XmlElement(name = "QUAN_HE")
    public String getRelationship() {
        return relationship;
    }

    @XmlElement(name = "CMND")
    public String getIdNumber() {
        return idNumber;
    }

    @XmlElement(name = "NGAY_CAP")
    public String getIssueIdDate() {
        return issueIdDate;
    }

    @XmlElement(name = "NOI_CAP")
    public String getIssueIdPlace() {
        return issueIdPlace;
    }

    @XmlElement(name = "GIOI_TINH")
    public String getSex() {
        return sex;
    }

    @XmlElement(name = "SO_TAI_KHOAN")
    public String getCardAccountNo() {
        return cardAccountNo;
    }

    @XmlElement(name = "SO_THE")
    public String getCardNo() {
        return cardNo;
    }

    @XmlElement(name = "HINH_THUC_NHAN_THE")
    public String getCardRecieveMode() {
        return cardRecieveMode;
    }

    @XmlElement(name = "DIA_CHI_CN")
    public String getAddress() {
        return address;
    }

    @XmlElement(name = "DON_VI")
    public String getBranchCode() {
        return branchCode;
    }

    @XmlElement(name = "MUC_DICH")
    public String getLoanPurpose() {
        return loanPurpose;
    }

    @XmlElement(name = "SO_TIEN")
    public String getLoanAmount() {
        return loanAmount;
    }

    @XmlElement(name = "THOI_HAN_VAY")
    public String getExpectedTime() {
        return expectedTime;
    }

    @XmlElement(name = "LAI_SUAT_VAY")
    public String getExpectInterest() {
        return expectInterest;
    }

    @XmlElement(name = "NGUON_TRA_NO")
    public String getRePaymentSource() {
        return rePaymentSource;
    }

    @XmlElement(name = "MA_NGUOI_DUNG")
    public String getIdChannelUser() {
        return idChannelUser;
    }

    /*-----------------------Setter-------------------------------*/
    public void setIdChannelUser(String idChannelUser) {
        this.idChannelUser = idChannelUser;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public void setInsDate(String insDate) {
        this.insDate = insDate;
    }

    public void setIdChannel(String idChannel) {
        this.idChannel = idChannel;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setIssueIdDate(String issueIdDate) {
        this.issueIdDate = issueIdDate;
    }

    public void setIssueIdPlace(String issueIdPlace) {
        this.issueIdPlace = issueIdPlace;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCardAccountNo(String cardAccountNo) {
        this.cardAccountNo = cardAccountNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setCardRecieveMode(String cardRecieveMode) {
        this.cardRecieveMode = cardRecieveMode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setExpectedTime(String expectedTime) {
        this.expectedTime = expectedTime;
    }

    public void setExpectInterest(String expectInterest) {
        this.expectInterest = expectInterest;
    }

    public void setRePaymentSource(String rePaymentSource) {
        this.rePaymentSource = rePaymentSource;
    }

}
