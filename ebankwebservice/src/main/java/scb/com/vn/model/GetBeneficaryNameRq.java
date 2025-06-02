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
@XmlRootElement(name = "GetBeneficaryNameRq")
public class GetBeneficaryNameRq {

    private String UserName;
    private String CifNo;
    private String TxnType;
    private String FromAccount;
    private String ToAccount;
    private String Remark;
    private String Amount;
    private String BankCode;
    private String clientCode;
    private String channel;    
    
    
    @XmlElement(name = "clientCode", nillable = true)
    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }
    
    

    /**
     * @return the UserName
     */
    @XmlElement(name = "UserName", nillable = true)
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
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
     * @return the ToAccount
     */
    @XmlElement(name = "ToAccount", nillable = true)
    public String getToAccount() {
        return ToAccount;
    }

    /**
     * @param ToAccount the ToAccount to set
     */
    public void setToAccount(String ToAccount) {
        this.ToAccount = ToAccount;
    }

    /**
     * @return the Remark
     */
    @XmlElement(name = "Remark", nillable = true)
    public String getRemark() {
        return Remark;
    }

    /**
     * @param Remark the Remark to set
     */
    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

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
     * @return the BankCode
     */
    @XmlElement(name = "BankCode", nillable = true)
    public String getBankCode() {
        return BankCode;
    }

    /**
     * @param BankCode the BankCode to set
     */
    public void setBankCode(String BankCode) {
        this.BankCode = BankCode;
    }
    
   /**
     * @return the channel
     */
    @XmlElement(name = "Channel", nillable = true)    
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }    
}
