/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "GetAccountTypeAzInfoRq")
public class GetAccountTypeAzInfoRq {

    private String AccountTypeCode;
    private String Amount;
    private String Ccy;
    private String FromAccount;
    private String TenorType;

    /**
     * @return the Amount
     */
    @XmlElement(name = "Amount", nillable = true)
    public String getAmount() {
        return Amount;
    }

    /**
     * @param Amount the Amount to set
     */
    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    /**
     * @return the AccountTypeCode
     */
    @XmlElement(name = "AccountTypeCode", nillable = true)
    public String getAccountTypeCode() {
        return AccountTypeCode;
    }

    /**
     * @param AccountTypeCode the AccountTypeCode to set
     */
    public void setAccountTypeCode(String AccountTypeCode) {
        this.AccountTypeCode = AccountTypeCode;
    }

    /**
     * @return the Ccy
     */
    @XmlElement(name = "Ccy", nillable = true)
    public String getCcy() {
        return Ccy;
    }

    /**
     * @param Ccy the Ccy to set
     */
    public void setCcy(String Ccy) {
        this.Ccy = Ccy;
    }

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
     * @return the TenorType
     */
    @XmlElement(name = "TenorType", nillable = true)
    public String getTenorType() {
        return TenorType;
    }

    /**
     * @param TenorType the TenorType to set
     */
    public void setTenorType(String TenorType) {
        this.TenorType = TenorType;
    }
}
