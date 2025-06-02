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
public class CreditCardType {

    private String cardtype;
    private String cardname;
    private String custtype;

    /**
     * @return the cardtype
     */
    @XmlElement(name = "CCType")
    public String getCardtype() {
        return cardtype;
    }

    /**
     * @param cardtype the cardtype to set
     */
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    /**
     * @return the cardname
     */
    @XmlElement(name = "CCName")
    public String getCardname() {
        return cardname;
    }

    /**
     * @param cardname the cardname to set
     */
    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    /**
     * @return the custtype
     */
    public String getCusttype() {
        return custtype;
    }

    /**
     * @param custtype the custtype to set
     */
    public void setCusttype(String custtype) {
        this.custtype = custtype;
    }

    /**
     * @return the name
     */
    // @XmlElement(name = "GoldCode")
}
