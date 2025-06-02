/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "CustomerRp")
@XmlAccessorType(XmlAccessType.NONE)
public class CustomerRp extends MobileResponse {

    private String txtcorpdesc;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String date_of_birth;
    private String sex;
    private String idcorporate;
    private String codbranch;
    private String postal_code;
    private String email;
    private String residentstatus;
    private String custcategory;
    private String isDomesticCust;
    private String flgsingle;
    private String issuedDate;

    /**
     *
     */
    public CustomerRp() {
    }

    /**
     * @return the txtcorpdesc
     */
    @XmlElement(name = "FullName")
    public String getTxtcorpdesc() {
        return txtcorpdesc;
    }

    /**
     * @param txtcorpdesc the txtcorpdesc to set
     */
    public void setTxtcorpdesc(String txtcorpdesc) {
        this.txtcorpdesc = txtcorpdesc;
    }

    /**
     * @return the address1
     */
    @XmlElement(name = "Address", nillable = true)
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return the address3
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * @param address3 the address3 to set
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    /**
     * @return the address4
     */
    public String getAddress4() {
        return address4;
    }

    /**
     * @param address4 the address4 to set
     */
    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    /**
     * @return the address5
     */
    public String getAddress5() {
        return address5;
    }

    /**
     * @param address5 the address5 to set
     */
    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    /**
     * @return the sex
     */
    @XmlElement(name = "Sex", nillable = true)
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
     * @return the idcorporate
     */
    @XmlElement(name = "CifNo", nillable = true)
    public String getIdcorporate() {
        return idcorporate;
    }

    /**
     * @param idcorporate the idcorporate to set
     */
    public void setIdcorporate(String idcorporate) {
        this.idcorporate = idcorporate;
    }

    /**
     * @return the codbranch
     */
    @XmlElement(name = "BranchCode", nillable = true)
    public String getCodbranch() {
        return codbranch;
    }

    /**
     * @param codbranch the codbranch to set
     */
    public void setCodbranch(String codbranch) {
        this.codbranch = codbranch;
    }

    /**
     * @return the email
     */
    @XmlElement(name = "Email")
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "IDNumber")
    /**
     * @return the postal_code
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     * @param postal_code the postal_code to set
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    /**
     * @return the date_of_birth
     */
    @XmlElement(name = "BirthDay", nillable = true)
    public String getDate_of_birth() {
        return date_of_birth;
    }

    /**
     * @param date_of_birth the date_of_birth to set
     */
    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    /**
     * @return the residentstatus
     */
    public String getResidentstatus() {
        return residentstatus;
    }

    /**
     * @param residentstatus the residentstatus to set
     */
    public void setResidentstatus(String residentstatus) {
        this.residentstatus = residentstatus;
    }

    /**
     * @return the custcategory
     */
    public String getCustcategory() {
        return custcategory;
    }

    /**
     * @param custcategory the custcategory to set
     */
    public void setCustcategory(String custcategory) {
        this.custcategory = custcategory;
    }

    /**
     * @return the isDomesticCust
     */
    @XmlElement(name = "IsDomesticCust", nillable = true)
    public String getIsDomesticCust() {
        return isDomesticCust;
    }    
    /**
     * @param isDomesticCust the isDomesticCust to set
     */
    public void setIsDomesticCust(String isDomesticCust) {
        this.isDomesticCust = isDomesticCust;
    }

     
    @XmlElement(name = "IDIssuedDate", nillable = true)
     public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }
    /**
     * @return the flgsingle
     */
    public String getFlgsingle() {
        return flgsingle;
    }

    /**
     * @param flgsingle the flgsingle to set
     */
    public void setFlgsingle(String flgsingle) {
        this.flgsingle = flgsingle;
    }
}
