/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author loinv3
 */
public class OnlinePCustomerInfoDto implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private String providerID;
    private String profileid;
    private String cardName;
    private String birthDay;
    private String mobileNo;
    private String idNumber;
    private String country;
    private String commondCode;   

    public String getCommondCode() {
        return commondCode;
    }

    public void setCommondCode(String commondCode) {
        this.commondCode = commondCode;
    }
    
    
    //constructor
    public OnlinePCustomerInfoDto(){}
    
    public String getProviderID() {
        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public String getProfileid() {
        return profileid;
    }

    public void setProfileid(String profileid) {
        this.profileid = profileid;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

