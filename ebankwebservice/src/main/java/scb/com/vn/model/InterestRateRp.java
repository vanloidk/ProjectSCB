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
@XmlRootElement(name = "InterestRateRp")
public class InterestRateRp extends MobileResponse {

    private InterestRateDetail[] listInterestRate;

    /**
     * @return the listExchangeRate
     */
    @XmlElement(name = "InterestRateDetail")
    @XmlElementWrapper(name = "ListInterestRate", nillable = true)
    public InterestRateDetail[] getListInterestRate() {
        return listInterestRate;
    }

    /**
     * @param listInterestRate
     */
    public void setListInterestRate(InterestRateDetail[] listInterestRate) {
        this.listInterestRate = listInterestRate;
    }
}
