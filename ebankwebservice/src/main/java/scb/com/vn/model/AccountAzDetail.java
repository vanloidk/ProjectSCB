/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author KimNCM
 */
public class AccountAzDetail {

    private String accountno;
    private String accountccy;
    private String accountbalance;
    private String accountbalanceavaliable;
    private String branchcode;
    private String branchname;
    private String acctopendt;
    private String maturitydate;
    private String rate;
    private String term;
    private String termtype;
    private String prdname;

    ;
                               
    /**
     *
     */
    public AccountAzDetail() {
    }

    /**
     * @return the accountno
     */
    @XmlElement(name = "AccountNo")
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
     * @return the accountccy
     */
    @XmlElement(name = "AccountCcy")
    public String getAccountccy() {
        return accountccy;
    }

    /**
     * @param accountccy the accountccy to set
     */
    public void setAccountccy(String accountccy) {
        this.accountccy = accountccy;
    }

    /**
     * @return the accountbalance
     */
    @XmlElement(name = "AccountBalance")
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
     * @return the accountbalanceavaliable
     */
    @XmlElement(name = "AccountBalanceAvaliable")
    public String getAccountbalanceavaliable() {
        return accountbalanceavaliable;
    }

    /**
     * @param accountbalanceavaliable the accountbalanceavaliable to set
     */
    public void setAccountbalanceavaliable(String accountbalanceavaliable) {
        this.accountbalanceavaliable = accountbalanceavaliable;
    }

    /**
     * @return the branchCode
     */
    @XmlElement(name = "BranchCode", nillable = true)
    public String getBranchcode() {
        return branchcode;
    }

    /**
     * @param branchcode
     */
    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    /**
     * @return the branchname
     */
    @XmlElement(name = "BranchName", nillable = true)
    public String getBranchname() {
        return branchname;
    }

    /**
     * @param branchname the branchname to set
     */
    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    /**
     * @return the acctopendt
     */
    @XmlElement(name = "OpenDate", nillable = true)
    public String getAcctopendt() {
        return acctopendt;
    }

    /**
     * @param acctopendt the acctopendt to set
     */
    public void setAcctopendt(String acctopendt) {
        this.acctopendt = acctopendt;
    }

    /**
     * @return the maturitydate
     */
    @XmlElement(name = "MatDate", nillable = true)
    public String getMaturitydate() {
        return maturitydate;
    }

    /**
     * @param maturitydate the maturitydate to set
     */
    public void setMaturitydate(String maturitydate) {
        this.maturitydate = maturitydate;
    }

    /**
     * @return the rate
     */
    @XmlElement(name = "Interest", nillable = true)
    public String getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * @return the term
     */
    @XmlElement(name = "Term", nillable = true)
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return the termtype
     */
    public String getTermtype() {
        return termtype;
    }

    /**
     * @param termtype the termtype to set
     */
    public void setTermtype(String termtype) {
        this.termtype = termtype;
    }

    /**
     * @return the accounttypename
     */
    @XmlElement(name = "AccountTypeName", nillable = true)
    public String getPrdname() {
        return prdname;
    }

    /**
     * @param prdname
     */
    public void setPrdname(String prdname) {
        this.prdname = prdname;
    }
}
