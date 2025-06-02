/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "CancelAutoBillRp")
public class CancelAutoBillRp extends MobileResponse {

    private String AutoBillIds;

    /**
     * @return the AutoBillIds
     */
    @XmlElement(name = "AutoBillIds", nillable = true)
    public String getAutoBillIds() {
        return AutoBillIds;
    }

    /**
     * @param AutoBillIds the AutoBillIds to set
     */
    public void setAutoBillIds(String AutoBillIds) {
        this.AutoBillIds = AutoBillIds;
    }

}
