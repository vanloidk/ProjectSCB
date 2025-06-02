/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "CheckRegisterAutoBillRp")
public class CheckRegisterAutoBillRp extends MobileResponse {

    private String CustomerCode;
    private String idservicetype;
    private String idprovider;
    private String DebitAccount;
    private String CardAccountNumer;
    private String CardNo;
    private String customername;
    private String address;

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
    @XmlElement(name = "idprovider", nillable = true)
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
     * @return the customername
     */
    @XmlElement(name = "customername", nillable = true)
    public String getCustomername() {
        return customername;
    }

    /**
     * @param customername the customername to set
     */
    public void setCustomername(String customername) {
        this.customername = customername;
    }

    /**
     * @return the address
     */
    @XmlElement(name = "address", nillable = true)
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
