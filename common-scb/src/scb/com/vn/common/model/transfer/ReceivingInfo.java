/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import scb.com.vn.ultility.Constant;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "RECEIVINGINFO")
public class ReceivingInfo implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(ReceivingInfo.class);
    
    private static final long serialVersionUID = 1L;
    
    private static final String KHCN = "I";
    private static final String KHDN = "C";
    private static final String DCTC = "B";
    private static final int MAX_NAME = 32;
    private static final int MAX_PASSNO = 30;
    private static final int MAX_ADDRESS = 2;
    private static final int DEFAULT_PERSONID = 16;
    
    private String personId;
    private String firstName;
    private String lastName;
    private String passNo;
    private String issueDate;
    private String issuePlace;
    private String birthDate;
    private String address;
    private String nationality;
    private String occupation = "";
    private String custType;

    public String getPersonId() {
        return personId;
    }

    

    @XmlElement(name = "FIRSTNAME")
    public String getFirstName() {
        return firstName;
    }

    @XmlElement(name = "LASTNAME")
    public String getLastName() {
        return lastName;
    }

    @XmlElement(name = "PASSNO")
    public String getPassNo() {
        return passNo;
    }

    @XmlElement(name = "ISSUEDATE")
    public String getIssueDate() {
        return issueDate;
    }

    @XmlElement(name = "ISSUEPLACE")
    public String getIssuePlace() {
        return issuePlace;
    }

    @XmlElement(name = "BIRTHDATE")
    public String getBirthDate() {
        return birthDate;
    }

    @XmlElement(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    @XmlElement(name = "NATIONALITY")
    public String getNationality() {
        return nationality;
    }

    @XmlElement(name = "OCCUPATION")
    public String getOccupation() {
        return occupation;
    }

    @XmlElement(name = "CUSTTYPE")
    public String getCustType() {
        return custType;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassNo(String passNo) {
        this.passNo = passNo;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }



    public void setCustType(String custType) {
        this.custType = custType;
    }
    
    public boolean isValid() {
        return this.personId != null && !"".equals(this.personId.trim()) && this.personId.length() == DEFAULT_PERSONID
                && this.firstName != null && !"".equals(this.firstName.trim()) && this.firstName.length() <= MAX_NAME
                && (this.lastName == null || "".equals(this.lastName) || this.lastName.length() <= MAX_NAME)
                && this.custType != null
                && (KHCN.equals(this.custType) || KHDN.equals(this.custType) || DCTC.equals(this.custType))
                && this.address != null && this.address.trim().length() == MAX_ADDRESS
                && this.nationality != null && this.nationality.trim().length() == MAX_ADDRESS;
    }
    
    public boolean isValidForWesternUnion() {
        return this.personId != null && !"".equals(this.personId.trim()) && this.personId.length() == DEFAULT_PERSONID
                && this.firstName != null && !"".equals(this.firstName.trim()) && this.firstName.length() <= MAX_NAME
                && (this.lastName == null || "".equals(this.lastName) || this.lastName.length() <= MAX_NAME)
                && this.custType != null
                && (KHCN.equals(this.custType) || KHDN.equals(this.custType) || DCTC.equals(this.custType));
    }
    private boolean isValidBirthDate() {
        boolean result = false;
        try {
            if (this.birthDate != null && !this.birthDate.isEmpty()) {
                String partern = "yyyyMMdd";
                SimpleDateFormat format = new SimpleDateFormat(partern);
                format.parse(this.birthDate);
                result = true;
            } else {
                if (KHDN.equals(this.custType)) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("personId = [").append(personId).append("]").append(Constant.LINESEPERATOR);
        str.append("firstName = [").append(firstName).append("]").append(Constant.LINESEPERATOR);
        str.append("lastName = [").append(lastName).append("]").append(Constant.LINESEPERATOR);
        str.append("passNo = [").append(passNo).append("]").append(Constant.LINESEPERATOR);
        str.append("issueDate = [").append(issueDate).append("]").append(Constant.LINESEPERATOR);
        str.append("issuePlace = [").append(issuePlace).append("]").append(Constant.LINESEPERATOR);
        str.append("birthDate = [").append(birthDate).append("]").append(Constant.LINESEPERATOR);
        str.append("address = [").append(address).append("]").append(Constant.LINESEPERATOR);
        str.append("nationality = [").append(nationality).append("]").append(Constant.LINESEPERATOR);
        str.append("occupation = [").append(occupation).append("]").append(Constant.LINESEPERATOR);
        str.append("custType = [").append(custType).append("]").append(Constant.LINESEPERATOR);
        return str.toString();
    }
    
    /*hienlt6 bo sung them ham tach ten gui sang KYC*/
    public String[] getName() {      
        String[] s = new String[2];// s[0] FirstName, s[1] LastName
        s[0] = "";
        String[] str = getFirstName().split(" ");
        for (int i = str.length - 1; i >= 0; i--) {
            /*Kiem tra neu firstName co du 32 ky tu ko*/
            if (getFirstName().length() == 32) {
                /* Lay ra chuoi truoc vi tri cuoi cung */
                if (i > 0) {
                    s[0] = str[i - 1] + " " + s[0];
                }
                if (i == str.length - 1) {
                    /* kiem tra khoang trong o dau va cuoi cua fistName va lastName*/
                    if (!getFirstName().endsWith(" ") || getLastName().startsWith(" ")) {
                        s[1] = str[i] + getLastName();
                    } else if (getFirstName().endsWith(" ")) {
                        s[1] = str[i] + " " + getLastName();
                    }
                }
            } else {
                s[0] = str[i] + " " + s[0];
                s[1] = getLastName();
            }
        }
        return s;
    }
}