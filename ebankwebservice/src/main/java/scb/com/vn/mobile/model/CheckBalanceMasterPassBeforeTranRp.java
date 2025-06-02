/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.model.MobileResponse;

/**
 *
 * @author Kimncmss
 */
@XmlRootElement(name = "CheckBalanceMasterPassBeforeTranRp")
public class CheckBalanceMasterPassBeforeTranRp extends MobileResponse {

    private String FromAccount;
    private String SumPayAmount;
    private String AvlBalance;

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
}
