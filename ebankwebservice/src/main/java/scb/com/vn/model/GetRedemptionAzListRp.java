/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author KimNCM
 *
 */
@XmlRootElement(name = "GetRedemptionAzListRp")
public class GetRedemptionAzListRp extends MobileResponse {

    private AccountAzDetail[] _ListAzAccount;

    /**
     *
     */
    public GetRedemptionAzListRp() {
    }

    /**
     * @return the _ListAccount
     */
    @XmlElement(name = "AccountAzDetail")
    @XmlElementWrapper(name = "ListAzAccount", nillable = true)
    public AccountAzDetail[] getListAzAccount() {
        return _ListAzAccount;
    }

    /**
     * @param ListAccount the _ListAccount to set
     */
    public void setListAzAccount(AccountAzDetail[] ListAccount) {
        this._ListAzAccount = ListAccount;
    }
}
