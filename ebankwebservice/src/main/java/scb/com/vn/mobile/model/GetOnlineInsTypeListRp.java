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
@XmlRootElement(name = "GetOnlineInsTypeListRp")
public class GetOnlineInsTypeListRp extends MobileResponse {

    private InsOnlineProduct[] InsOnlineProductList;

    /**
     *
     */
    public GetOnlineInsTypeListRp() {
    }

    /**
     * @return the InsOnlineProduct
     */
    @XmlElement(name = "InsOnlineProduct")
    @XmlElementWrapper(name = "InsOnlineProductList", nillable = true)
    public InsOnlineProduct[] getInsOnlineProductList() {
        return InsOnlineProductList;
    }

    /**
     * @param InsOnlineProductList the InsOnlineProductList to set
     */
    public void setInsOnlineProductList(InsOnlineProduct[] InsOnlineProductList) {
        this.InsOnlineProductList = InsOnlineProductList;
    }

}
