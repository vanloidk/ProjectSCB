/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "AccountStmtRp")
public class AccountStmtRp extends MobileResponse {

    /**
     *
     */
    public AccountStmtRp() {
    }
    private TxnDetail[] txnList;
    private LoanTxnDetail[] ListPaidInterestLoanTxn;
    private LoanTxnDetail[] ListUnPaidInterestLoanTxn;
    private LoanTxnDetail[] ListPaidPrincipalLoanTxn;
    private LoanTxnDetail[] ListUnPaidPrincipalLoanTxn;

    /**
     * @return the txnList
     */
    @XmlElement(name = "TxnDetail")
    @XmlElementWrapper(name = "ListTxn", nillable = true)
    public TxnDetail[] getTxnList() {
        return txnList;
    }

    /**
     * @param txnList the txnList to set
     */
    public void setTxnList(TxnDetail[] txnList) {
        this.txnList = txnList;
    }

    /**
     * @return the ListPaidInterestLoanTxn
     */
    @XmlElement(name = "LoanTxnDetail")
    @XmlElementWrapper(name = "ListPaidInterestLoanTxn", nillable = true)
    public LoanTxnDetail[] getListPaidInterestLoanTxn() {
        return ListPaidInterestLoanTxn;
    }

    /**
     * @param ListPaidInterestLoanTxn the ListPaidInterestLoanTxn to set
     */
    public void setListPaidInterestLoanTxn(LoanTxnDetail[] ListPaidInterestLoanTxn) {
        this.ListPaidInterestLoanTxn = ListPaidInterestLoanTxn;
    }

    /**
     * @return the ListUnPaidInterestLoanTxn
     */
    @XmlElement(name = "LoanTxnDetail")
    @XmlElementWrapper(name = "ListUnPaidInterestLoanTxn", nillable = true)
    public LoanTxnDetail[] getListUnPaidInterestLoanTxn() {
        return ListUnPaidInterestLoanTxn;
    }

    /**
     * @param ListUnPaidInterestLoanTxn the ListUnPaidInterestLoanTxn to set
     */
    public void setListUnPaidInterestLoanTxn(LoanTxnDetail[] ListUnPaidInterestLoanTxn) {
        this.ListUnPaidInterestLoanTxn = ListUnPaidInterestLoanTxn;
    }

    /**
     * @return the ListPaidPrincipalLoanTxn
     */
    @XmlElement(name = "LoanTxnDetail")
    @XmlElementWrapper(name = "ListPaidPrincipalLoanTxn", nillable = true)
    public LoanTxnDetail[] getListPaidPrincipalLoanTxn() {
        return ListPaidPrincipalLoanTxn;
    }

    /**
     * @param ListPaidPrincipalLoanTxn the ListPaidPrincipalLoanTxn to set
     */
    public void setListPaidPrincipalLoanTxn(LoanTxnDetail[] ListPaidPrincipalLoanTxn) {
        this.ListPaidPrincipalLoanTxn = ListPaidPrincipalLoanTxn;
    }

    /**
     * @return the ListUnPaidPrincipalLoanTxn
     */
    @XmlElement(name = "LoanTxnDetail")
    @XmlElementWrapper(name = "ListUnPaidPrincipalLoanTxn", nillable = true)
    public LoanTxnDetail[] getListUnPaidPrincipalLoanTxn() {
        return ListUnPaidPrincipalLoanTxn;
    }

    /**
     * @param ListUnPaidPrincipalLoanTxn the ListUnPaidPrincipalLoanTxn to set
     */
    public void setListUnPaidPrincipalLoanTxn(LoanTxnDetail[] ListUnPaidPrincipalLoanTxn) {
        this.ListUnPaidPrincipalLoanTxn = ListUnPaidPrincipalLoanTxn;
    }
}
