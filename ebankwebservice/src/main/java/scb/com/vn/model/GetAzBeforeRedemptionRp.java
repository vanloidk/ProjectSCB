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
@XmlRootElement(name = "GetAzBeforeRedemptionRp")
public class GetAzBeforeRedemptionRp extends MobileResponse {

    private String AzAccount;
    private String prdcode;
    private String prdname;
    private String codacctcurr;
    private String numbalance;
    private String numavailbal;
    private String codbranch;
    private String acctopendt;
    private String maturitydate;
    private String rate;
    private String term;
    private String SavingType;
    private String payinterest;
    private String isredem;
    private String minamount;

    /**
     *
     */
    public GetAzBeforeRedemptionRp() {
    }

    /**
     * @return the accountclass
     */
    @XmlElement(name = "AccountTypeCode", nillable = true)
    public String getPrdcode() {
        return prdcode;
    }

    /**
     * @param prdcode
     */
    public void setPrdcode(String prdcode) {
        this.prdcode = prdcode;
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

    /**
     * @return the AccountCcy
     */
    @XmlElement(name = "AccountCcy", nillable = true)
    public String getCodacctcurr() {
        return codacctcurr;
    }

    /**
     * @param codacctcurr
     */
    public void setCodacctcurr(String codacctcurr) {
        this.codacctcurr = codacctcurr;
    }

    /**
     * @return the numbalance
     */
    @XmlElement(name = "AccountBalance", nillable = true)
    public String getNumbalance() {
        return numbalance;
    }

    /**
     * @param numbalance the numbalance to set
     */
    public void setNumbalance(String numbalance) {
        this.numbalance = numbalance;
    }

    /**
     * @return the numavailbal
     */
    @XmlElement(name = "AccountBalanceAvaliable", nillable = true)
    public String getNumavailbal() {
        return numavailbal;
    }

    /**
     * @param numavailbal the acyAvlBal to set
     */
    public void setNumavailbal(String numavailbal) {
        this.numavailbal = numavailbal;
    }

    /**
     * @return the codbranch
     */
    @XmlElement(name = "AccountBranchOpen", nillable = true)
    public String getsetCodbranch() {
        return codbranch;
    }

    /**
     * @param codbranch the codbranch to set
     */
    public void setCodbranch(String codbranch) {
        this.codbranch = codbranch;
    }

    /**
     * @return the Acctopendt
     */
    @XmlElement(name = "OpenDate", nillable = true)
    public String getAcctopendt() {
        return acctopendt;
    }

    /**
     * @param acctopendt
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
     * @return the SavingType
     */
    @XmlElement(name = "SavingType", nillable = true)
    public String getSavingType() {
        return SavingType;
    }

    /**
     * @param SavingType the SavingType to set
     */
    public void setSavingType(String SavingType) {
        this.SavingType = SavingType;
    }

    /**
     * @return the AccountNo
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAzAccount() {
        return AzAccount;
    }

    /**
     * @param AzAccount
     */
    public void setAzAccount(String AzAccount) {
        this.AzAccount = AzAccount;
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
     * @return the payinterest
     */
    @XmlElement(name = "PayInterest", nillable = true)
    public String getPayinterest() {
        return payinterest;
    }

    /**
     * @param payinterest the payinterest to set
     */
    public void setPayinterest(String payinterest) {
        this.payinterest = payinterest;
    }

    /**
     * @return the isredem
     */
    public String getIsredem() {
        return isredem;
    }

    /**
     * @param isredem the isredem to set
     */
    public void setIsredem(String isredem) {
        this.isredem = isredem;
    }

    /**
     * @return the minamount
     */
    public String getMinamount() {
        return minamount;
    }

    /**
     * @param minamount the minamount to set
     */
    public void setMinamount(String minamount) {
        this.minamount = minamount;
    }
}
