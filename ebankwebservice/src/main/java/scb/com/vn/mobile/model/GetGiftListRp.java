/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.dbi.dto.GiftDetail;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "GetGiftListRp")
public class GetGiftListRp extends MobileResponse {

    private GiftDetail[] GiftList;

    /**
     *
     */
    public GetGiftListRp() {
    }

    /**
     * @return the GiftList
     */
    @XmlElement(name = "GiftDetail")
    @XmlElementWrapper(name = "GiftList", nillable = true)
    public GiftDetail[] getGiftList() {
        return GiftList;
    }

    /**
     * @param GiftList the GiftList to set
     */
    public void setGiftList(GiftDetail[] GiftList) {
        this.GiftList = GiftList;
    }
}
