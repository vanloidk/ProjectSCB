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
@XmlRootElement(name = "SelectOnlineInsBuyHistoryRp")
public class SelectOnlineInsBuyHistoryRp extends MobileResponse {

    private String ListInsBuy;

    /**
     * @return the CustomerCode
     */
    /**
     * @return the ListInsBuy
     */
    @XmlElement(name = "ListInsBuy", nillable = true)
    public String getListInsBuy() {
        return ListInsBuy;
    }

    /** 
     * @param ListInsBuy the ListInsBuy to set
     */
    public void setListInsBuy(String ListInsBuy) {
        this.ListInsBuy = ListInsBuy;
    }

}
