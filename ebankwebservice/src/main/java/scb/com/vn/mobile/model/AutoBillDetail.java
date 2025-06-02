/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "AutoBillDetail")
public class AutoBillDetail {

    private String id;
    private String idservicetype;
    private String idprovider;
    private String customercode;
    private String validdate;
    private String expiredate;
    private String cust_acc_no;
    private String havesms;
    private String providername;
    private String servicename;
    private String PayType;
    private String mobile;
    private String status;
    private String active;

    /**
     * @return the id
     */
    @XmlElement(name = "AutoBillId", required = false, nillable = true)
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the idservicetype
     */
    @XmlElement(name = "BillServiceId", required = false, nillable = true)
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
    @XmlElement(name = "BillProviderId", required = false, nillable = true)
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
     * @return the customercode
     */
    @XmlElement(name = "CustomerCode", required = false, nillable = true)
    public String getCustomercode() {
        return customercode;
    }

    /**
     * @param customercode the customercode to set
     */
    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    /**
     * @return the validdate
     */
    @XmlElement(name = "StartDate", required = false, nillable = true)
    public String getValiddate() {
        return validdate;
    }

    /**
     * @param validdate the validdate to set
     */
    public void setValiddate(String validdate) {
        this.validdate = validdate;
    }

    /**
     * @return the expiredate
     */
    @XmlElement(name = "Enddate", required = false, nillable = true)
    public String getExpiredate() {
        return expiredate;
    }

    /**
     * @param expiredate the expiredate to set
     */
    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    /**
     * @return the cust_acc_no
     */
    @XmlElement(name = "DebitAccount", required = false, nillable = true)
    public String getCust_acc_no() {
        return cust_acc_no;
    }

    /**
     * @param cust_acc_no the cust_acc_no to set
     */
    public void setCust_acc_no(String cust_acc_no) {
        this.cust_acc_no = cust_acc_no;
    }

    /**
     * @return the havesms
     */
    @XmlElement(name = "HasSMS", required = false, nillable = true)
    public String getHavesms() {
        return havesms;
    }

    /**
     * @param havesms the havesms to set
     */
    public void setHavesms(String havesms) {
        this.havesms = havesms;
    }

    /**
     * @return the providername
     */
    @XmlElement(name = "BillProviderName", required = false, nillable = true)
    public String getProvidername() {
        return providername;
    }

    /**
     * @param providername the providername to set
     */
    public void setProvidername(String providername) {
        this.providername = providername;
    }

    /**
     * @return the servicename
     */
    @XmlElement(name = "BillServiceName", required = false, nillable = true)
    public String getServicename() {
        return servicename;
    }

    /**
     * @param servicename the servicename to set
     */
    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    /**
     * @return the PayType
     */
    @XmlElement(name = "PayType", required = false, nillable = true)
    public String getPayType() {
        return PayType;
    }

    /**
     * @param PayType the PayType to set
     */
    public void setPayType(String PayType) {
        this.PayType = PayType;
    }

    /**
     * @return the mobile
     */
    @XmlElement(name = "Mobile", required = false, nillable = true)
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the active
     */
    public String getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(String active) {
        this.active = active;
    }

}
