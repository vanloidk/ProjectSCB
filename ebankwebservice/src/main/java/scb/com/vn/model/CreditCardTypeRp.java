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
 * @author Kimncm
 */
@XmlRootElement(name = "CreditCardTypeRp")
public class CreditCardTypeRp extends MobileResponse {

    private CreditCardType[] listCreditCardType;

    /**
     * @return the listCreditCardType
     */
    @XmlElement(name = "ListCreditCardType")
    @XmlElementWrapper(name = "CreditCardType", nillable = true)
    public CreditCardType[] getListCreditCardType() {
        return listCreditCardType;
    }

    /**
     * @param listCreditCardType the listCreditCardType to set
     */
    public void setListCreditCardType(CreditCardType[] listCreditCardType) {
        this.listCreditCardType = listCreditCardType;
    }

}
