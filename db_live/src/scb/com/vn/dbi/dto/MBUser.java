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
 * @author lydty
 */
@XmlRootElement(name = "CustomerRq")
public class MBUser implements java.io.Serializable{
      String UserName;
      String CifNo;
      String FullName;
      String Address;
      String BirthDay;
      String Sex;
      String IDNumber;
      String Email;
      String AccountNo;
      String AccountTypeCode;
      String AccountTypeName;
      String  AccountCcy;
      String  MobileNo;
      String  AuthenUseSMS;
      String  AuthenUseToken;
      String AuthenTokenValue;
      String   CusType;
      String    StaffCode;
      String   StaffName;
      String  PackageCode;
      String  UserRegister;
      String  UserConfirm;
      String BranchCodeCif;
      String  PosCodeUser;
      String  CreatedDate;
      String ConfirmDate;
    
    /**
     *
     * @return
     */
    @XmlElement(name = "UserName")
    public String getUserName() {
        return UserName;
    }

    /**
     *
     * @param UserName
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CifNo")
    public String getCifNo() {
        return CifNo;
    }

    /**
     *
     * @param CifNo
     */
    public void setCifNo(String CifNo) {
        this.CifNo = CifNo;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "FullName")
    public String getFullName() {
        return FullName;
    }

    /**
     *
     * @param FullName
     */
    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "Address")
    public String getAddress() {
        return Address;
    }

    /**
     *
     * @param Address
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "BirthDay")
    public String getBirthDay() {
        return BirthDay;
    }

    /**
     *
     * @param BirthDay
     */
    public void setBirthDay(String BirthDay) {
        this.BirthDay = BirthDay;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "Sex")
    public String getSex() {
        return Sex;
    }

    /**
     *
     * @param Sex
     */
    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "IDNumber")
    public String getIDNumber() {
        return IDNumber;
    }

    /**
     *
     * @param IDNumber
     */
    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "Email")
    public String getEmail() {
        return Email;
    }

    /**
     *
     * @param Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AccountNo")
    public String getAccountNo() {
        return AccountNo;
    }

    /**
     *
     * @param AccountNo
     */
    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AccountTypeCode")
    public String getAccountTypeCode() {
        return AccountTypeCode;
    }

    /**
     *
     * @param AccountTypeCode
     */
    public void setAccountTypeCode(String AccountTypeCode) {
        this.AccountTypeCode = AccountTypeCode;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AccountTypeName")
    public String getAccountTypeName() {
        return AccountTypeName;
    }

    /**
     *
     * @param AccountTypeName
     */
    public void setAccountTypeName(String AccountTypeName) {
        this.AccountTypeName = AccountTypeName;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AccountCcy")
    public String getAccountCcy() {
        return AccountCcy;
    }

    /**
     *
     * @param AccountCcy
     */
    public void setAccountCcy(String AccountCcy) {
        this.AccountCcy = AccountCcy;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MobileNo")
    public String getMobileNo() {
        return MobileNo;
    }

    /**
     *
     * @param MobileNo
     */
    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AuthenUseSMS")
    public String getAuthenUseSMS() {
        return AuthenUseSMS;
    }

    /**
     *
     * @param AuthenUseSMS
     */
    public void setAuthenUseSMS(String AuthenUseSMS) {
        this.AuthenUseSMS = AuthenUseSMS;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AuthenUseToken")
    public String getAuthenUseToken() {
        return AuthenUseToken;
    }

    /**
     *
     * @param AuthenUseToken
     */
    public void setAuthenUseToken(String AuthenUseToken) {
        this.AuthenUseToken = AuthenUseToken;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AuthenTokenValue")
    public String getAuthenTokenValue() {
        return AuthenTokenValue;
    }

    /**
     *
     * @param AuthenTokenValue
     */
    public void setAuthenTokenValue(String AuthenTokenValue) {
        this.AuthenTokenValue = AuthenTokenValue;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CusType")
    public String getCusType() {
        return CusType;
    }

    /**
     *
     * @param CusType
     */
    public void setCusType(String CusType) {
        this.CusType = CusType;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "StaffCode")
    public String getStaffCode() {
        return StaffCode;
    }

    /**
     *
     * @param StaffCode
     */
    public void setStaffCode(String StaffCode) {
        this.StaffCode = StaffCode;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "StaffName")
    public String getStaffName() {
        return StaffName;
    }

    /**
     *
     * @param StaffName
     */
    public void setStaffName(String StaffName) {
        this.StaffName = StaffName;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PackageCode")
    public String getPackageCode() {
        return PackageCode;
    }

    /**
     *
     * @param PackageCode
     */
    public void setPackageCode(String PackageCode) {
        this.PackageCode = PackageCode;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "UserRegister")
    public String getUserRegister() {
        return UserRegister;
    }

    /**
     *
     * @param UserRegister
     */
    public void setUserRegister(String UserRegister) {
        this.UserRegister = UserRegister;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "UserConfirm")
    public String getUserConfirm() {
        return UserConfirm;
    }

    /**
     *
     * @param UserConfirm
     */
    public void setUserConfirm(String UserConfirm) {
        this.UserConfirm = UserConfirm;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "BranchCodeCif")
    public String getBranchCodeCif() {
        return BranchCodeCif;
    }

    /**
     *
     * @param BranchCodeCif
     */
    public void setBranchCodeCif(String BranchCodeCif) {
        this.BranchCodeCif = BranchCodeCif;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PosCodeUser")
    public String getPosCodeUser() {
        return PosCodeUser;
    }

    /**
     *
     * @param PosCodeUser
     */
    public void setPosCodeUser(String PosCodeUser) {
        this.PosCodeUser = PosCodeUser;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CreatedDate")
    public String getCreatedDate() {
        return CreatedDate;
    }

    /**
     *
     * @param CreatedDate
     */
    public void setCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ConfirmDate")
    public String getConfirmDate() {
        return ConfirmDate;
    }

    /**
     *
     * @param ConfirmDate
     */
    public void setConfirmDate(String ConfirmDate) {
        this.ConfirmDate = ConfirmDate;
    }
      
}
