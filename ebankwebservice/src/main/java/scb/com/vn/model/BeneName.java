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
public class BeneName {

    private String benename;
    private String accountno;
    private String txntype;

    /**
     *
     */
    public BeneName() {
    }

    /**
     * @return the accountno
     */
    @XmlElement(name = "ACCOUNTNO")
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
     * @return the benename
     */
    @XmlElement(name = "BENENAME")
    public String getBenename() {
        return benename;
    }

    /**
     * @param benename the benename to set
     */
    public void setBenename(String benename) {
        this.benename = benename;
    }

    /**
     * @return the txntype
     */
    @XmlElement(name = "TXNTYPE")
    public String getTxntype() {
        return txntype;
    }

    /**
     * @param txntype the txntype to set
     */
    public void setTxntype(String txntype) {
        this.txntype = txntype;
    }
}
