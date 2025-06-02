/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kimncm
 */
@XmlRootElement(name = "CheckOpenOnlineAzRp")
public class CheckOpenOnlineAzRp extends MobileResponse {

    private String FromAccount;
    private String SumPayAmount;
    private String AvlBalance;
    private String OtherAccount;

    /**
     * @return the FromAccount
     */
    @XmlElement(name = "FromAccount", nillable = true)
    public String getFromAccount() {
        return FromAccount;
    }

    /**
     * @param FromAccount the FromAccount to set
     */
    public void setFromAccount(String FromAccount) {
        this.FromAccount = FromAccount;
    }

    /**
     * @return the SumPayAmount
     */
    public String getSumPayAmount() {
        return SumPayAmount;
    }

    /**
     * @param SumPayAmount the SumPayAmount to set
     */
    public void setSumPayAmount(String SumPayAmount) {
        this.SumPayAmount = SumPayAmount;
    }

    /**
     * @return the AvlBalance
     */
    public String getAvlBalance() {
        return AvlBalance;
    }

    /**
     * @param AvlBalance the AvlBalance to set
     */
    public void setAvlBalance(String AvlBalance) {
        this.AvlBalance = AvlBalance;
    }

    /**
     * @return the OtherAccount
     */
    @XmlElement(name = "OtherAccount", nillable = true)
    public String getOtherAccount() {
        return OtherAccount;
    }

    /**
     * @param OtherAccount the OtherAccount to set
     */
    public void setOtherAccount(String OtherAccount) {
        this.OtherAccount = OtherAccount;
    }
}
