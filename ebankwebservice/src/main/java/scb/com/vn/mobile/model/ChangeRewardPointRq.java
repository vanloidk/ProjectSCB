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
@XmlRootElement(name = "ChangeRewardPointRq")
public class ChangeRewardPointRq extends MobileRequest {

    private String AccountNo;
    private String CardAccountNumber;
    private String AccountBranchOpen;
    private String CardNo;
    private String ChangePointTotal;
    private GiftDetail[] GiftList;

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
     * @return the AccountBranchOpen
     */
    @XmlElement(name = "AccountBranchOpen", nillable = true)
    public String getAccountBranchOpen() {
        return AccountBranchOpen;
    }

    /**
     * @param AccountBranchOpen the AccountBranchOpen to set
     */
    public void setAccountBranchOpen(String AccountBranchOpen) {
        this.AccountBranchOpen = AccountBranchOpen;
    }

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
     * @return the ChangePointTotal
     */
    @XmlElement(name = "ChangePointTotal", nillable = true)
    public String getChangePointTotal() {
        return ChangePointTotal;
    }

    /**
     * @param ChangePointTotal the ChangePointTotal to set
     */
    public void setChangePointTotal(String ChangePointTotal) {
        this.ChangePointTotal = ChangePointTotal;
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

    /**
     * @return the CardAccountNumber
     */
    @XmlElement(name = "CardAccountNumer", nillable = true)
    public String getCardAccountNumber() {
        return CardAccountNumber;
    }

    /**
     * @param CardAccountNumber the CardAccountNumber to set
     */
    public void setCardAccountNumber(String CardAccountNumber) {
        this.CardAccountNumber = CardAccountNumber;
    }

}
