/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "SENDERINFO")
public class SenderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int MAX_NAME = 32;
    private static final int MAX_PASSNO = 30;
    private String firstName;
    private String lastName;
    private String address;
    private String countryIsoCode;
    private String accountNumber;
    private String senderIdentifier;
    private String birthDate;
    private String countryOfBitrh;
    private String idType;
    private String idNumber;

    @XmlElement(name = "FIRSTNAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "LASTNAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement(name = "COUNTRYISOCODE")
    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    @XmlElement(name = "ACCOUNTNUMBER")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @XmlElement(name = "SENDERIDENTIFIER")
    public String getSenderIdentifier() {
        return senderIdentifier;
    }

    public void setSenderIdentifier(String senderIdentifier) {
        this.senderIdentifier = senderIdentifier;
    }

    @XmlElement(name = "BIRTHDATE")
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @XmlElement(name = "COUNTRYOFBIRTH")
    public String getCountryOfBitrh() {
        return countryOfBitrh;
    }

    public void setCountryOfBitrh(String countryOfBitrh) {
        this.countryOfBitrh = countryOfBitrh;
    }

    @XmlElement(name = "IDTYPE")
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @XmlElement(name = "IDNUMBER")
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public boolean isValidSender() {
        return this.firstName != null && !"".equals(this.firstName.trim()) && this.firstName.length() <= MAX_NAME
                && (this.lastName == null || "".equals(this.lastName) || this.lastName.length() <= MAX_NAME)
                && (this.countryIsoCode != null || !"".equals(this.countryIsoCode))
                && (this.address != null || !"".equals(this.address));
    }
}
