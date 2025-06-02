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
@XmlRootElement(name = "GetCardListByTypeRp")
public class GetCardListByTypeRp extends MobileResponse {

    private AccountDetail[] _ListAccount;
    private String CardGroupCode;

    /**
     *
     */
    public GetCardListByTypeRp() {
    }

    /**
     * @return the _ListAccount
     */
    @XmlElement(name = "AccountDetail")
    @XmlElementWrapper(name = "ListAccount", nillable = true)
    public AccountDetail[] getListAccount() {
        return _ListAccount;
    }

    /**
     * @param ListAccount the _ListAccount to set
     */
    public void setListAccount(AccountDetail[] ListAccount) {
        this._ListAccount = ListAccount;
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

}
