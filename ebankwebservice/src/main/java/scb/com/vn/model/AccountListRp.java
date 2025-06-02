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
@XmlRootElement(name = "AccountListRp")
public class AccountListRp extends MobileResponse {

    private AccountDetail[] _ListAccount;

    /**
     *
     */
    public AccountListRp() {
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
}
