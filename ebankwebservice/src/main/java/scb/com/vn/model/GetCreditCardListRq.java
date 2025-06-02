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
@XmlRootElement(name = "GetCreditCardListRq")
public class GetCreditCardListRq {

    private String _UserName;
    private String _CifNo;
    private String _CardType;
    private String _CardAccountNumer;

    /**
     *
     */
    public GetCreditCardListRq() {
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
     * @return the _CardType
     */
    @XmlElement(name = "CardType")
    public String getCardType() {
        return _CardType;
    }

    /**
     * @param CardType the _CardType to set
     */
    public void setCardType(String CardType) {
        this._CardType = CardType;
    }

    /**
     * @return the _CardAccountNumer
     */
    @XmlElement(name = "CardAccountNumer")
    public String getCardAccountNumer() {
        return _CardAccountNumer;
    }

    /**
     * @param CardAccountNumer the _CardAccountNumer to set
     */
    public void setCardAccountNumer(String CardAccountNumer) {
        this._CardAccountNumer = CardAccountNumer;
    }

}
