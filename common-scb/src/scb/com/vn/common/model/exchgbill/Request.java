/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.exchgbill;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author phucnnd
 */
public class Request {

    private String idkey;
    private String idsession;

    public String getIdsession() {
        return idsession;
    }

    public void setIdsession(String idsession) {
        this.idsession = idsession;
    }

    private String type;  //loadpage | submit
    private String transcode;
    private String cust_acc_no;
    private String idservicetype;
    private String idprovider;
    private String customercode;
    private String amount;
    private Otp otp;
    String a;
    

   
    public void setIdkey(String idkey) {
        this.idkey = idkey;
    }

    @XmlElement(name = "idkey", nillable = true, required = false)
    public String getIdkey() {
        return idkey;
    }
    
    @XmlElement(name = "type", nillable = true, required = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "transcode", nillable = true, required = false)
    public String getTranscode() {
        return transcode;
    }

    public void setTranscode(String transcode) {
        this.transcode = transcode;
    }

    @XmlElement(name = "cust_acc_no", nillable = true, required = false)
    public String getCust_acc_no() {
        return cust_acc_no;
    }

    public void setCust_acc_no(String cust_acc_no) {
        this.cust_acc_no = cust_acc_no;
    }

    @XmlElement(name = "idservicetype", nillable = true, required = false)
    public String getIdservicetype() {
        return idservicetype;
    }

    public void setIdservicetype(String idservicetype) {
        this.idservicetype = idservicetype;
    }

    @XmlElement(name = "idprovider", nillable = true, required = false)
    public String getIdprovider() {
        return idprovider;
    }

    public void setIdprovider(String idprovider) {
        this.idprovider = idprovider;
    }

    @XmlElement(name = "customercode", nillable = true, required = false)
    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    @XmlElement(name = "otp", nillable = true, required = false)
    public Otp getOtp() {
        return otp;
    }

    public void setOtp(Otp otp) {
        this.otp = otp;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
