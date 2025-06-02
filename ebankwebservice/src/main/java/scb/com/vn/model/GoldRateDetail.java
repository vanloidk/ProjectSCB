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
public class GoldRateDetail {

    private String name;
    private String buying;
    private String selling;
    private String ordernumber;
    private String note;
    private String lastest;

    /**
     * @return the name
     */
    @XmlElement(name = "GoldCode")
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the buying
     */
    @XmlElement(name = "Buying")
    public String getBuying() {
        return buying;
    }

    /**
     * @param buying the buying to set
     */
    public void setBuying(String buying) {
        this.buying = buying;
    }

    /**
     * @return the selling
     */
    @XmlElement(name = "Selling")
    public String getSelling() {
        return selling;
    }

    /**
     * @param selling the selling to set
     */
    public void setSelling(String selling) {
        this.selling = selling;
    }

    /**
     * @return the ordernumber
     */
    @XmlElement(name = "DateEffect")
    public String getOrdernumber() {
        return ordernumber;
    }

    /**
     * @param ordernumber the ordernumber to set
     */
    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    /**
     * @return the note
     */
    @XmlElement(name = "Effective", nillable = true)
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the lastest
     */
    @XmlElement(name = "Lastest")
    public String getLastest() {
        return lastest;
    }

    /**
     * @param lastest the lastest to set
     */
    public void setLastest(String lastest) {
        this.lastest = lastest;
    }

}
