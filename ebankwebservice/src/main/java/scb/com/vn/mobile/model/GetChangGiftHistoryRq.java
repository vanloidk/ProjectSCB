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
@XmlRootElement(name = "GetChangGiftHistoryRq")
public class GetChangGiftHistoryRq extends MobileModelRequest {

    private String AccountNo;
    private String CardAccountNumber;
    private String AccountBranchOpen;
    private String CardNo;
    private String FromDate;
    private String ToDate;

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

    /**
     * @return the FromDate
     */
    @XmlElement(name = "FromDate", nillable = true)
    public String getFromDate() {
        return FromDate;
    }

    /**
     * @param FromDate the FromDate to set
     */
    public void setFromDate(String FromDate) {
        this.FromDate = FromDate;
    }

    /**
     * @return the ToDate
     */
    @XmlElement(name = "ToDate", nillable = true)
    public String getToDate() {
        return ToDate;
    }

    /**
     * @param ToDate the ToDate to set
     */
    public void setToDate(String ToDate) {
        this.ToDate = ToDate;
    }

}
