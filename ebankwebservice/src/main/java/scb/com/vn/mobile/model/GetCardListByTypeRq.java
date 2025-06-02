/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "GetCardListByTypeRq")
public class GetCardListByTypeRq {

    private String _UserName;
    private String _CifNo;
    private String CardGroupCode;
    private String CreditType;

    /**
     *
     */
    public GetCardListByTypeRq() {
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
     * @return the CardGroupCode
     */
    @XmlElement(name = "CardGroupCode")
    public String getCardGroupCode() {
        return CardGroupCode;
    }

    /**
     * @param CardGroupCode the CardGroupCode to set
     */
    public void setCardGroupCode(String CardGroupCode) {
        this.CardGroupCode = CardGroupCode;
    }

    /**
     * @return the CreditType
     */
    @XmlElement(name = "CreditType")
    public String getCreditType() {
        return CreditType;
    }

    /**
     * @param CreditType the CreditType to set
     */
    public void setCreditType(String CreditType) {
        this.CreditType = CreditType;
    }

}
