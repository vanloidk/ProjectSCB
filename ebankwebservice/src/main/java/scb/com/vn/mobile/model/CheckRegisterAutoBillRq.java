/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "CheckRegisterAutoBillRq")
public class CheckRegisterAutoBillRq {

    private String UserName;
    private String CifNo;
    private String CustomerCode;
    private String idservicetype;
    private String idprovider;
    private String DebitAccount;
    private String CardAccountNumer;
    private String CardNo;
    private String StartDate;
    private String Enddate;

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
     * @return the CustomerCode
     */
    @XmlElement(name = "CustomerCode", nillable = true)
    public String getCustomerCode() {
        return CustomerCode;
    }

    /**
     * @param CustomerCode the CustomerCode to set
     */
    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    /**
     * @return the idservicetype
     */
    @XmlElement(name = "BillServiceId", nillable = true)
    public String getIdservicetype() {
        return idservicetype;
    }

    /**
     * @param idservicetype the idservicetype to set
     */
    public void setIdservicetype(String idservicetype) {
        this.idservicetype = idservicetype;
    }

    /**
     * @return the idprovider
     */
    @XmlElement(name = "BillProviderId", nillable = true)
    public String getIdprovider() {
        return idprovider;
    }

    /**
     * @param idprovider the idprovider to set
     */
    public void setIdprovider(String idprovider) {
        this.idprovider = idprovider;
    }

    /**
     * @return the DebitAccount
     */
    @XmlElement(name = "DebitAccount", nillable = true)
    public String getDebitAccount() {
        return DebitAccount;
    }

    /**
     * @param DebitAccount the DebitAccount to set
     */
    public void setDebitAccount(String DebitAccount) {
        this.DebitAccount = DebitAccount;
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
     * @return the StartDate
     */
    @XmlElement(name = "StartDate", nillable = true)
    public String getStartDate() {
        return StartDate;
    }

    /**
     * @param StartDate the StartDate to set
     */
    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    /**
     * @return the Enddate
     */
    @XmlElement(name = "Enddate", nillable = true)
    public String getEnddate() {
        return Enddate;
    }

    /**
     * @param Enddate the Enddate to set
     */
    public void setEnddate(String Enddate) {
        this.Enddate = Enddate;
    }
}
