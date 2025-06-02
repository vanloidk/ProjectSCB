/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "GifCustomerDetail")
public class GifCustomerDetail implements java.io.Serializable {

    private String AccountNo;
    private String CifNo;
    private String CardNo;
    private String CardAccountNumber;
    private String AccountBranchOpen;
    private String MakerId;

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
     * @return the CifNo
     */
    @XmlElement(name = "CifNo", nillable = true)
    public String getCifNo() {
        return CifNo;
    }

    /**
     * @param CifNo the CifNo to set
     */
    public void setCifNo(String CifNo) {
        this.CifNo = CifNo;
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
    @XmlElement(name = "CardAccountNumber", nillable = true)
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
     * @return the MakerId
     */
    @XmlElement(name = "MakerId", nillable = true)
    public String getMakerId() {
        return MakerId;
    }

    /**
     * @param MakerId the MakerId to set
     */
    public void setMakerId(String MakerId) {
        this.MakerId = MakerId;
    }

}
