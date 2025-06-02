/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Administrator
 */
public class InterestRateDetail {

    private String product_description;
    private String term;
    private String interest;
    private String ccy_code;
    private String tenortype;
    private String amountslab;
    private String ratetruoc;
    private String ratesau;
    private String term_eng;
    private String product_des_eng;
    private String tenor;

    /**
     * @return the product_description
     */
    @XmlElement(name = "Type")
    public String getProduct_description() {
        return product_description;
    }

    /**
     * @param product_description the product_description to set
     */
    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    /**
     * @return the term
     */
    @XmlElement(name = "Tenor")
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
     * @return the interest
     */
    @XmlElement(name = "Rate")
    public String getInterest() {
        return interest;
    }

    /**
     * @param interest the interest to set
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

    /**
     * @return the ccy_code
     */
    @XmlElement(name = "Ccy")
    public String getCcy_code() {
        return ccy_code;
    }

    /**
     * @param ccy_code the ccy_code to set
     */
    public void setCcy_code(String ccy_code) {
        this.ccy_code = ccy_code;
    }

    /**
     * @return the tenortype
     */
    @XmlElement(name = "TenorType")
    public String getTenortype() {
        return tenortype;
    }

    /**
     * @param tenortype the tenortype to set
     */
    public void setTenortype(String tenortype) {
        this.tenortype = tenortype;
    }

    /**
     * @return the amountslab
     */
    @XmlElement(name = "AmountSlab")
    public String getAmountslab() {
        return amountslab;
    }

    /**
     * @param amountslab the amountslab to set
     */
    public void setAmountslab(String amountslab) {
        this.amountslab = amountslab;
    }

    /**
     * @return the ratetruoc
     */
    @XmlElement(name = "RateTruoc")
    public String getRatetruoc() {
        return ratetruoc;
    }

    /**
     * @param ratetruoc the ratetruoc to set
     */
    public void setRatetruoc(String ratetruoc) {
        this.ratetruoc = ratetruoc;
    }

    /**
     * @return the ratesau
     */
    @XmlElement(name = "RateSau")
    public String getRatesau() {
        return ratesau;
    }

    /**
     * @param ratesau the ratesau to set
     */
    public void setRatesau(String ratesau) {
        this.ratesau = ratesau;
    }

    /**
     * @return the term_eng
     */
    public String getTerm_eng() {
        return term_eng;
    }

    /**
     * @param term_eng the term_eng to set
     */
    public void setTerm_eng(String term_eng) {
        this.term_eng = term_eng;
    }

    /**
     * @return the product_des_eng
     */
    public String getProduct_des_eng() {
        return product_des_eng;
    }

    /**
     * @param product_des_eng the product_des_eng to set
     */
    public void setProduct_des_eng(String product_des_eng) {
        this.product_des_eng = product_des_eng;
    }

    /**
     * @return the tenor
     */
    public String getTenor() {
        return tenor;
    }

    /**
     * @param tenor the tenor to set
     */
    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

}
