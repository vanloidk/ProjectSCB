/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "LoanRegisterRq")
public class LoanRegisterRq {

    private String UserName;
    private String CifNo;
    private String loanpurpose;
    private String loanamount;
    private String expectedtime;
    private String expectinterest;
    private String repaymentsource;
    private String branchcode;
    private String address;

    /**
     * @return the UserName
     */
    @XmlElement(name = "UserName", nillable = true)
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @return the CifNo
     */
    @XmlElement(name = "CifNo", nillable = true)
    public String getCifNo() {
        return CifNo;
    }

    /**
     * @param CifNo the CifNo to set
     */
    public void setCifNo(String CifNo) {
        this.CifNo = CifNo;
    }

    /**
     * @return the loanlurpose
     */
    @XmlElement(name = "LoanPurpose", nillable = true)
    public String getLoanpurpose() {
        return loanpurpose;
    }

    /**
     * @param loanpurpose
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
}
