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
public class TxnDetail {

    private String txndate;
    private String txnamount;
    private String codtxncurr;
    private String description;
    private String txtreferenceno;
    private String coddrcr;
    private String srno;
    private String txnCode;
    private String txnamount_vnd;

    /**
     */
    
    
    public TxnDetail() {
        this.codtxncurr = "VND";
        this.coddrcr = "D";
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
     * @return the description
     */
    @XmlElement(name = "TxnContent", nillable = true)
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the txtreferenceno
     */
    @XmlElement(name = "TxnRefNo", nillable = true)
    public String getTxtreferenceno() {
        return txtreferenceno;
    }

    /**
     * @param txtreferenceno the txtreferenceno to set
     */
    public void setTxtreferenceno(String txtreferenceno) {
        this.txtreferenceno = txtreferenceno;
    }

    /**
     * @return the coddrcr
     */
    @XmlElement(name = "DrCr", nillable = true)
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
    @XmlElement(name = "srno", nillable = true)
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
     * @return the txnCode
     */
    @XmlElement(name = "TxnCode", nillable = true)
    public String getTxnCode() {
        return txnCode;
    }

    /**
     * @param txnCode the txnCode to set
     */
    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }
    
    @XmlElement(name = "Txnamount_vnd", nillable = true)
    public String getTxnamount_vnd() {
        return txnamount_vnd;
    }

    public void setTxnamount_vnd(String txnamount_vnd) {
        this.txnamount_vnd = txnamount_vnd;
    }
    
    
}
