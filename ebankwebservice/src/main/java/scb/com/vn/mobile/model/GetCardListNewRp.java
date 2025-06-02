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
@XmlRootElement(name = "GetCardListNewRp")
public class GetCardListNewRp extends MobileResponse {

    private CardDetail[] ListCard;

    /**
     *
     */
    public GetCardListNewRp() {
    }

    /**
     * @return the ListCard
     */
    @XmlElement(name = "CardDetail")
    @XmlElementWrapper(name = "ListCard", nillable = true)
    public CardDetail[] getListCard() {
        return ListCard;
    }

    /**
     * @param ListCard the ListCard to set
     */
    public void setListCard(CardDetail[] ListCard) {
        this.ListCard = ListCard;
    }
}
