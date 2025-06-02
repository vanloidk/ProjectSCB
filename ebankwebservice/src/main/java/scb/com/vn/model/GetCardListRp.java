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
 * @author KimNCM
 */
@XmlRootElement(name = "GetCardListRp")
public class GetCardListRp extends MobileResponse {

    private CardDetail[] _ListCard;

    /**
     *
     */
    public GetCardListRp() {
    }

    /**
     * @return the _ListCard
     */
    @XmlElement(name = "CardDetail")
    @XmlElementWrapper(name = "ListCard", nillable = true)
    public CardDetail[] getListCard() {
        return _ListCard;
    }

    /**
     * @param ListCard the _ListCard to set
     */
    public void setListCard(CardDetail[] ListCard) {
        this._ListCard = ListCard;
    }
    /**
     * @return the _ListAccount
     */
    // @XmlElement(name = "AccountDetail")
    // @XmlElementWrapper(name = "ListAccount", nillable = true)
}
