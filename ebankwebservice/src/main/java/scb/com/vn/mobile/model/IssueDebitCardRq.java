/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "IssueDebitCardRq")
public class IssueDebitCardRq extends MobileRequest {

    private String issuetype;
    private String sourceaccount;
    private String accountno;
    private String amountfee;
    private String amounttax;
    private String cardtype;
    private String fullname;
    private String idnumber;
    private String birthday;
    private String relationship;
    private String cardrecievemode;
    private String branchcode;
    private String address;
    private String issueidDate;
    private String issueidPlace;
    private String sex;

    /**
     * @return the issuetype
     */
    @XmlElement(name = "IssueType", nillable = true)
    public String getIssuetype() {
        return issuetype;
    }

    /**
     * @param issuetype the issuetype to set
     */
    public void setIssuetype(String issuetype) {
        this.issuetype = issuetype;
    }

    /**
     * @return the sourceaccount
     */
    @XmlElement(name = "SourceAccount", nillable = true)
    public String getSourceaccount() {
        return sourceaccount;
    }

    /**
     * @param sourceaccount the sourceaccount to set
     */
    public void setSourceaccount(String sourceaccount) {
        this.sourceaccount = sourceaccount;
    }

    /**
     * @return the fullname
     */
    @XmlElement(name = "FullName", nillable = true)
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the idnumber
     */
    @XmlElement(name = "IDNumber", nillable = true)
    public String getIdnumber() {
        return idnumber;
    }

    /**
     * @param idnumber the idnumber to set
     */
    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    /**
     * @return the birthday
     */
    @XmlElement(name = "BirthDay", nillable = true)
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the relationship
     */
    @XmlElement(name = "Relationship", nillable = true)
    public String getRelationship() {
        return relationship;
    }

    /**
     * @param relationship the relationship to set
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    /**
     * @return the cardrecievemode
     */
    @XmlElement(name = "CardRecieveMode", nillable = true)
    public String getCardrecievemode() {
        return cardrecievemode;
    }

    /**
     * @param cardrecievemode the cardrecievemode to set
     */
    public void setCardrecievemode(String cardrecievemode) {
        this.cardrecievemode = cardrecievemode;
    }

    /**
     * @return the branchcode
     */
    @XmlElement(name = "BranchCode", nillable = true)
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
     * @return the address
     */
    @XmlElement(name = "Address", nillable = true)
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
     * @return the amountfee
     */
    @XmlElement(name = "AmountFee", nillable = true)
    public String getAmountfee() {
        return amountfee;
    }

    /**
     * @param amountfee the amountfee to set
     */
    public void setAmountfee(String amountfee) {
        this.amountfee = amountfee;
    }

    /**
     * @return the amounttax
     */
    @XmlElement(name = "AmountTax", nillable = true)
    public String getAmounttax() {
        return amounttax;
    }

    /**
     * @param amounttax the amounttax to set
     */
    public void setAmounttax(String amounttax) {
        this.amounttax = amounttax;
    }

    /**
     * @return the accountno
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAccountno() {
        return accountno;
    }

    /**
     * @param accountno the accountno to set
     */
    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    /**
     * @return the FromAccount
     */
    //@XmlElement(name = "FromAccount", nillable = true)

    /**
     * @return the cardtype
     */
    @XmlElement(name = "CCType", nillable = true)
    public String getCardtype() {
        return cardtype;
    }

    /**
     * @param cardtype the cardtype to set
     */
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    /**
     * @return the issueidDate
     */
    @XmlElement(name = "IssueIdDate", nillable = true)
    public String getIssueidDate() {
        return issueidDate;
    }

    /**
     * @param issueidDate the issueidDate to set
     */
    public void setIssueidDate(String issueidDate) {
        this.issueidDate = issueidDate;
    }

    /**
     * @return the issueidPlace
     */
    @XmlElement(name = "IssueIdPlace", nillable = true)
    public String getIssueidPlace() {
        return issueidPlace;
    }

    /**
     * @param issueidPlace the issueidPlace to set
     */
    public void setIssueidPlace(String issueidPlace) {
        this.issueidPlace = issueidPlace;
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
}
