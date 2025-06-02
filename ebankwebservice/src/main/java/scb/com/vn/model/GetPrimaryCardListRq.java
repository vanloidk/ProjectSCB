/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "GetPrimaryCardListRq")
public class GetPrimaryCardListRq {

    private String _UserName;
    private String _CifNo;
    private String CardType;

    /**
     *
     */
    public GetPrimaryCardListRq() {
    }

    /**
     * @return the _UserName
     */
    @XmlElement(name = "UserName")
    public String getUserName() {
        return _UserName;
    }

    /**
     * @param UserName the _UserName to set
     */
    public void setUserName(String UserName) {
        this._UserName = UserName;
    }

    /**
     * @return the _CifNo
     */
    @XmlElement(name = "CifNo")
    public String getCifNo() {
        return _CifNo;
    }

    /**
     * @param CifNo the _CifNo to set
     */
    public void setCifNo(String CifNo) {
        this._CifNo = CifNo;
    }

    /**
     * @return the CardType
     */
    @XmlElement(name = "CardType")
    public String getCardType() {
        return CardType;
    }

    /**
     * @param CardType the CardType to set
     */
    public void setCardType(String CardType) {
        this.CardType = CardType;
    }

}
