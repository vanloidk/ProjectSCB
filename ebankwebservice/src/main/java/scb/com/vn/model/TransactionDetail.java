/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author KimNCM
 */
public class TransactionDetail {

    private String nbraccount;
    private String branchcode;
    private Date datvaluedate;
    private Date txndate;
    private Date postingdate;
    private String codtxncurr;
    private String nambranch;
    private BigDecimal txnamount;
    private Character coddrcr;
    private String txtreferenceno;
    private String codtxntype;
    private String description;
    private BigDecimal srno;
    private String txncode;

    /**
     * @return the nbraccount
     */
    @XmlElement(name = "nbraccount")
    public String getNbraccount() {
        return nbraccount;
    }

    /**
     * @param nbraccount the nbraccount to set
     */
    public void setNbraccount(String nbraccount) {
        this.nbraccount = nbraccount;
    }

    /**
     * @return the branchcode
     */
    @XmlElement(name = "branchcode")
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
     * @return the datvaluedate
     */
    @XmlElement(name = "datvaluedate")
    public Date getDatvaluedate() {
        return datvaluedate;
    }

    /**
     * @param datvaluedate the datvaluedate to set
     */
    public void setDatvaluedate(Date datvaluedate) {
        this.datvaluedate = datvaluedate;
    }

    /**
     * @return the txndate
     */
    @XmlElement(name = "txndate")
    public Date getTxndate() {
        return txndate;
    }

    /**
     * @param txndate the txndate to set
     */
    public void setTxndate(Date txndate) {
        this.txndate = txndate;
    }

    /**
     * @return the postingdate
     */
    @XmlElement(name = "postingdate")
    public Date getPostingdate() {
        return postingdate;
    }

    /**
     * @param postingdate the postingdate to set
     */
    public void setPostingdate(Date postingdate) {
        this.postingdate = postingdate;
    }

    /**
     * @return the codtxncurr
     */
    @XmlElement(name = "codtxncurr")
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
     * @return the nambranch
     */
    @XmlElement(name = "nambranch")
    public String getNambranch() {
        return nambranch;
    }

    /**
     * @param nambranch the nambranch to set
     */
    public void setNambranch(String nambranch) {
        this.nambranch = nambranch;
    }

    /**
     * @return the txnamount
     */
    @XmlElement(name = "txnamount")
    public BigDecimal getTxnamount() {
        return txnamount;
    }

    /**
     * @param txnamount the txnamount to set
     */
    public void setTxnamount(BigDecimal txnamount) {
        this.txnamount = txnamount;
    }

    /**
     * @return the coddrcr
     */
    @XmlElement(name = "coddrcr")
    public Character getCoddrcr() {
        return coddrcr;
    }

    /**
     * @param coddrcr the coddrcr to set
     */
    public void setCoddrcr(Character coddrcr) {
        this.coddrcr = coddrcr;
    }

    /**
     * @return the txtreferenceno
     */
    @XmlElement(name = "txtreferenceno")
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
     * @return the codtxntype
     */
    @XmlElement(name = "codtxntype")
    public String getCodtxntype() {
        return codtxntype;
    }

    /**
     * @param codtxntype the codtxntype to set
     */
    public void setCodtxntype(String codtxntype) {
        this.codtxntype = codtxntype;
    }

    /**
     * @return the description
     */
    @XmlElement(name = "description")
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
     * @return the srno
     */
    @XmlElement(name = "srno")
    public BigDecimal getSrno() {
        return srno;
    }

    /**
     * @param srno the srno to set
     */
    public void setSrno(BigDecimal srno) {
        this.srno = srno;
    }

    /**
     * @return the txncode
     */
    @XmlElement(name = "txncode")
    public String getTxncode() {
        return txncode;
    }

    /**
     * @param txncode the txncode to set
     */
    public void setTxncode(String txncode) {
        this.txncode = txncode;
    }
}
