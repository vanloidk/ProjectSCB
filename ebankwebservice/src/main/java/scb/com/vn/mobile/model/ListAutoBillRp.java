/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "ListAutoBillRp")
public class ListAutoBillRp extends MobileResponse {

    private AutoBillDetail[] ListAutoBill;

    /**
     *
     */
    public ListAutoBillRp() {
    }

    /**
     * @return the ListAutoBill
     */
    @XmlElement(name = "AutoBillDetail")
    @XmlElementWrapper(name = "ListAutoBill", nillable = true)
    public AutoBillDetail[] getListAutoBill() {
        return ListAutoBill;
    }

    /**
     * @param ListAutoBill the ListAutoBill to set
     */
    public void setListAutoBill(AutoBillDetail[] ListAutoBill) {
        this.ListAutoBill = ListAutoBill;
    }
}
