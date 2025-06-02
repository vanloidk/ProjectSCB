/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.model.MobileRequest;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "OpenLockCardFuncRq")
public class OpenLockCardFuncRq extends MobileRequest {

    private String CardNo;
    private String AccountNo;
    private String CardAccountNumer;
    private String TxnType;
    private String ActionCodeCloseOpen;
    private String ExpireDate;

    /**
     * @return the CardNo
     */
    @XmlElement(name = "CardNo", nillable = true)
    public String getCardNo() {
        return CardNo;
    }

    /**
     * @param CardNo the CardNo to set
     */
    public void setCardNo(String CardNo) {
        this.CardNo = CardNo;
    }

    /**
     * @return the AccountNo
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAccountNo() {
        return AccountNo;
    }

    /**
     * @param AccountNo the AccountNo to set
     */
    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    /**
     * @return the CardAccountNumer
     */
    @XmlElement(name = "CardAccountNumer", nillable = true)
    public String getCardAccountNumer() {
        return CardAccountNumer;
    }

    /**
     * @param CardAccountNumer the CardAccountNumer to set
     */
    public void setCardAccountNumer(String CardAccountNumer) {
        this.CardAccountNumer = CardAccountNumer;
    }

    /**
     * @return the TxnType
     */
    @XmlElement(name = "TxnType", nillable = true)
    public String getTxnType() {
        return TxnType;
    }

    /**
     * @param TxnType the TxnType to set
     */
    public void setTxnType(String TxnType) {
        this.TxnType = TxnType;
    }

    /**
     * @return the ActionCodeCloseOpen
     */
    @XmlElement(name = "ActionCode", nillable = true)
    public String getActionCodeCloseOpen() {
        return ActionCodeCloseOpen;
    }

    /**
     * @param ActionCodeCloseOpen the ActionCodeCloseOpen to set
     */
    public void setActionCodeCloseOpen(String ActionCodeCloseOpen) {
        this.ActionCodeCloseOpen = ActionCodeCloseOpen;
    }

    /**
     * @return the ExpireDate
     */
    @XmlElement(name = "ExpireDate", nillable = true)
    public String getExpireDate() {
        return ExpireDate;
    }

    /**
     * @param ExpireDate the ExpireDate to set
     */
    public void setExpireDate(String ExpireDate) {
        this.ExpireDate = ExpireDate;
    }

}
