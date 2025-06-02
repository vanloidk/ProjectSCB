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
public class Template {

    private String formname;
    private String txntype;
    private String fromaccount;
    private String toaccount;
    private String ccy;
    private String amount;
    private String remark;
    private String benename;
    private String citycode;
    private String cityname;
    private String branchcode;
    private String branchname;
    private String idopendate;
    private String idcitycode;
    private String idcityname;
    private String bankcode;
    private String bankname;

    /**
     *
     */
    public Template() {
    }

    /**
     * @return the txntype
     */
    @XmlElement(name = "TXNTYPE", nillable = true)
    public String getTxntype() {
        return txntype;
    }

    /**
     * @param txntype the txntype to set
     */
    public void setTxntype(String txntype) {
        this.txntype = txntype;
    }

    /**
     * @return the formname
     */
    @XmlElement(name = "FORMNAME", nillable = true)
    public String getFormname() {
        return formname;
    }

    /**
     * @param formname the formname to set
     */
    public void setFormname(String formname) {
        this.formname = formname;
    }

    /**
     * @return the fromaccount
     */
    @XmlElement(name = "FROMACCOUNT", nillable = true)
    public String getFromaccount() {
        return fromaccount;
    }

    /**
     * @param fromaccount the fromaccount to set
     */
    public void setFromaccount(String fromaccount) {
        this.fromaccount = fromaccount;
    }

    /**
     * @return the toaccount
     */
    @XmlElement(name = "TOACCOUNT", nillable = true)
    public String getToaccount() {
        return toaccount;
    }

    /**
     * @param toaccount the toaccount to set
     */
    public void setToaccount(String toaccount) {
        this.toaccount = toaccount;
    }

    /**
     * @return the ccy
     */
    @XmlElement(name = "CCY", nillable = true)
    public String getCcy() {
        return ccy;
    }

    /**
     * @param ccy the ccy to set
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * @return the amount
     */
    @XmlElement(name = "AMOUNT", nillable = true)
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the remark
     */
    @XmlElement(name = "REMARK", nillable = true)
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the benename
     */
    @XmlElement(name = "BENENAME", nillable = true)
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
     * @return the citycode
     */
    @XmlElement(name = "CITYCODE", nillable = true)
    public String getCitycode() {
        return citycode;
    }

    /**
     * @param citycode the citycode to set
     */
    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    /**
     * @return the cityname
     */
    @XmlElement(name = "CITYNAME", nillable = true)
    public String getCityname() {
        return cityname;
    }

    /**
     * @param cityname the cityname to set
     */
    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    /**
     * @return the branchcode
     */
    @XmlElement(name = "BRANCHCODE", nillable = true)
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
     * @return the branchname
     */
    @XmlElement(name = "BRANCHNAME", nillable = true)
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
     * @return the idopendate
     */
    @XmlElement(name = "IDOPENDATE", nillable = true)
    public String getIdopendate() {
        return idopendate;
    }

    /**
     * @param idopendate the idopendate to set
     */
    public void setIdopendate(String idopendate) {
        this.idopendate = idopendate;
    }

    /**
     * @return the idcitycode
     */
    @XmlElement(name = "IDCITYCODE", nillable = true)
    public String getIdcitycode() {
        return idcitycode;
    }

    /**
     * @param idcitycode the idcitycode to set
     */
    public void setIdcitycode(String idcitycode) {
        this.idcitycode = idcitycode;
    }

    /**
     * @return the idcityname
     */
    @XmlElement(name = "IDCITYNAME", nillable = true)
    public String getIdcityname() {
        return idcityname;
    }

    /**
     * @param idcityname the idcityname to set
     */
    public void setIdcityname(String idcityname) {
        this.idcityname = idcityname;
    }

    /**
     * @return the bankcode
     */
    @XmlElement(name = "BANKCODE", nillable = true)
    public String getBankcode() {
        return bankcode;
    }

    /**
     * @param bankcode the bankcode to set
     */
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    /**
     * @return the bankname
     */
    @XmlElement(name = "BANKNAME", nillable = true)
    public String getBankname() {
        return bankname;
    }

    /**
     * @param bankname the bankname to set
     */
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
}
