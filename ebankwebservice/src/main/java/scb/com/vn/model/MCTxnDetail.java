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
public class MCTxnDetail {

    private String txndate;
    private String postdate;
    private String txnamount;
    private String codtxncurr;
    private String txncontent;
    private String txnrefno;
    private String coddrcr;
    private String srno;

    /**
     */
    public MCTxnDetail() {
        this.codtxncurr = "VND";
        //this.coddrcr = "D";
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "TxnDate", nillable = true)
    public String getTxndate() {
        return txndate;
    }

    /**
     * @param txndate the txndate to set
     */
    public void setTxndate(String txndate) {
        this.txndate = txndate;
    }

    /**
     * @return the txnamount
     */
    @XmlElement(name = "TxnAmount", nillable = true)
    public String getTxnamount() {
        return txnamount;
    }

    /**
     * @param txnamount the txnamount to set
     */
    public void setTxnamount(String txnamount) {
        this.txnamount = txnamount;
    }

    /**
     * @return the codtxncurr
     */
    @XmlElement(name = "TxnCcy", nillable = true)
    public String getCodtxncurr() {
        return codtxncurr;
    }

    /**
     * @param codtxncurr the codtxncurr to set
     */
    public void setCodtxncurr(String codtxncurr) {
        this.codtxncurr = codtxncurr;
    }

    /**
     * @return the coddrcr
     */
    @XmlElement(name = "CrDr", nillable = true)
    public String getCoddrcr() {
        return coddrcr;
    }

    /**
     * @param coddrcr the coddrcr to set
     */
    public void setCoddrcr(String coddrcr) {
        this.coddrcr = coddrcr;
    }

    /**
     * @return the srno
     */
    @XmlElement(name = "SRNO", nillable = true)
    public String getSrno() {
        return srno;
    }

    /**
     * @param srno the srno to set
     */
    public void setSrno(String srno) {
        this.srno = srno;
    }

    /**
     * @return the postdate
     */
    @XmlElement(name = "PostDate", nillable = true)
    public String getPostdate() {
        return postdate;
    }

    /**
     * @param postdate the postdate to set
     */
    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    /**
     * @return the txncontent
     */
    @XmlElement(name = "TxnContent", nillable = true)
    public String getTxncontent() {
        return txncontent;
    }

    /**
     * @param txncontent the txncontent to set
     */
    public void setTxncontent(String txncontent) {
        this.txncontent = txncontent;
    }

    /**
     * @return the txnrefno
     */
    @XmlElement(name = "TxnRefNo", nillable = true)
    public String getTxnrefno() {
        return txnrefno;
    }

    /**
     * @param txnrefno the txnrefno to set
     */
    public void setTxnrefno(String txnrefno) {
        this.txnrefno = txnrefno;
    }
}
