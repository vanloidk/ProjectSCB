/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "ResgisterCardMB")
public class ResgisterCardMB {

    private String brn;
    private String issuetype;
    private String sourceaccount;
    private String cardaccountno;
    private String cardno;
    private String cardtype;
    private String cctype;
    private String fullname;
    private String idnumber;
    private String birthday;
    private String relationship;
    private String cardrecievemode;
    private String branchcode;
    private String address;
    private String limitrequest;
    private String guaranteemode;
    private String guaranteemodeinfo;
    private String loanpurpose;
    private String loanamount;
    private String expectedtime;
    private String expectinterest;
    private String repaymentsource;
    private String insdate;
    private String amountfee;
    private String amounttax;
    private String accountbalance;
    private String ccname;
    private Integer issueidDate;
    private String issueidPlace;
    private String sex;

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
     * @return the cardaccountno
     */
    @XmlElement(name = "CardAccountNo", nillable = true)
    public String getCardaccountno() {
        return cardaccountno;
    }

    /**
     * @param cardaccountno the cardaccountno to set
     */
    public void setCardaccountno(String cardaccountno) {
        this.cardaccountno = cardaccountno;
    }

    /**
     * @return the cardtype
     */
    @XmlElement(name = "ATMCardType", nillable = true)
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
     * @return the limitrequest
     */
    @XmlElement(name = "LimitRequest", nillable = true)
    public String getLimitrequest() {
        return limitrequest;
    }

    /**
     * @param limitrequest the limitrequest to set
     */
    public void setLimitrequest(String limitrequest) {
        this.limitrequest = limitrequest;
    }

    /**
     * @return the guaranteemode
     */
    @XmlElement(name = "GuaranteeMode", nillable = true)
    public String getGuaranteemode() {
        return guaranteemode;
    }

    /**
     * @param guaranteemode the guaranteemode to set
     */
    public void setGuaranteemode(String guaranteemode) {
        this.guaranteemode = guaranteemode;
    }

    /**
     * @return the guaranteemodeinfo
     */
    @XmlElement(name = "GuaranteeModeInfo", nillable = true)
    public String getGuaranteemodeinfo() {
        return guaranteemodeinfo;
    }

    /**
     * @param guaranteemodeinfo the guaranteemodeinfo to set
     */
    public void setGuaranteemodeinfo(String guaranteemodeinfo) {
        this.guaranteemodeinfo = guaranteemodeinfo;
    }

    /**
     * @return the loanpurpose
     */
    @XmlElement(name = "LoanPurpose", nillable = true)
    public String getLoanpurpose() {
        return loanpurpose;
    }

    /**
     * @param loanpurpose the loanpurpose to set
     */
    public void setLoanpurpose(String loanpurpose) {
        this.loanpurpose = loanpurpose;
    }

    /**
     * @return the loanamount
     */
    @XmlElement(name = "LoanAmount", nillable = true)
    public String getLoanamount() {
        return loanamount;
    }

    /**
     * @param loanamount the loanamount to set
     */
    public void setLoanamount(String loanamount) {
        this.loanamount = loanamount;
    }

    /**
     * @return the expectedtime
     */
    @XmlElement(name = "ExpectedTime", nillable = true)
    public String getExpectedtime() {
        return expectedtime;
    }

    /**
     * @param expectedtime the expectedtime to set
     */
    public void setExpectedtime(String expectedtime) {
        this.expectedtime = expectedtime;
    }

    /**
     * @return the expectinterest
     */
    @XmlElement(name = "ExpectInterest", nillable = true)
    public String getExpectinterest() {
        return expectinterest;
    }

    /**
     * @param expectinterest the expectinterest to set
     */
    public void setExpectinterest(String expectinterest) {
        this.expectinterest = expectinterest;
    }

    /**
     * @return the repaymentsource
     */
    @XmlElement(name = "RepaymentSource", nillable = true)
    public String getRepaymentsource() {
        return repaymentsource;
    }

    /**
     * @param repaymentsource the repaymentsource to set
     */
    public void setRepaymentsource(String repaymentsource) {
        this.repaymentsource = repaymentsource;
    }

    /**
     * @return the insdate
     */
    @XmlElement(name = "InsDate", nillable = true)
    public String getInsdate() {
        return insdate;
    }

    /**
     * @param insdate the insdate to set
     */
    public void setInsdate(String insdate) {
        this.insdate = insdate;
    }

    /**
     * @return the brn
     */
    @XmlElement(name = "CardType", nillable = true)
    public String getBrn() {
        return brn;
    }

    /**
     * @param brn the brn to set
     */
    public void setBrn(String brn) {
        this.brn = brn;
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
     * @return the cardno
     */
    @XmlElement(name = "CardNo", nillable = true)
    public String getCardno() {
        return cardno;
    }

    /**
     * @param cardno the cardno to set
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    /**
     * @return the cctype
     */
    @XmlElement(name = "CCType", nillable = true)
    public String getCctype() {
        return cctype;
    }

    /**
     * @param cctype the cctype to set
     */
    public void setCctype(String cctype) {
        this.cctype = cctype;
    }

    /**
     * @return the accountbalance
     */
    @XmlElement(name = "AccountBalance", nillable = true)
    public String getAccountbalance() {
        return accountbalance;
    }

    /**
     * @param accountbalance the accountbalance to set
     */
    public void setAccountbalance(String accountbalance) {
        this.accountbalance = accountbalance;
    }

    /**
     * @return the ccname
     */
    @XmlElement(name = "CCName", nillable = true)
    public String getCcname() {
        return ccname;
    }

    /**
     * @param ccname the ccname to set
     */
    public void setCcname(String ccname) {
        this.ccname = ccname;
    }

    /**
     * @return the issueidDate
     */
    @XmlElement(name = "IssueIdDate", nillable = true)
    public Integer getIssueidDate() {
        return issueidDate;
    }

    /**
     * @param issueidDate the issueidDate to set
     */
    public void setIssueidDate(Integer issueidDate) {
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
