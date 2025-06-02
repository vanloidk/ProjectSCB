/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.ecom.napas;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tamnt12
 */

//@XmlRootElement(name = "EcomNapasRequest")
public class EcomNapasRequest {
    private String requestorCode;
    private String requestorPassword;
    private String requestorTransid;
    private String data;
    private String signature;

//    @XmlElement(name = "RequestorCode", required = true)
    public String getRequestorCode() {
        return requestorCode;
    }

    public void setRequestorCode(String requestorCode) {
        this.requestorCode = requestorCode;
    }

//    @XmlElement(name = "RequestorPassword", required = true)
    public String getRequestorPassword() {
        return requestorPassword;
    }

    public void setRequestorPassword(String requestorPassword) {
        this.requestorPassword = requestorPassword;
    }

//    @XmlElement(name = "RequestorTransid", required = true)
    public String getRequestorTransid() {
        return requestorTransid;
    }

    public void setRequestorTransid(String requestorTransid) {
        this.requestorTransid = requestorTransid;
    }

//    @XmlElement(name = "Data", required = true)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

//    @XmlElement(name = "Signature", required = true)
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    public boolean inValid(){
        return this.requestorTransid == null || this.requestorTransid.isEmpty() 
                || this.requestorCode == null || this.requestorCode.isEmpty() 
                || this.requestorPassword == null || this.requestorPassword.isEmpty()
                || this.data == null || this.data.isEmpty() 
                ||  this.signature == null || this.signature.isEmpty();
    }
    
    
}
