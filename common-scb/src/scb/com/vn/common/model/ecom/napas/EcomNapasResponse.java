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
//@XmlRootElement(name = "EcomNapasResponse")
public class EcomNapasResponse {
    private String partnerTransId;
    private String requestorTransId;
    private String data; //encrypted by AES
    private String signature; 

//    @XmlElement(name = "PartnerTransId", required = true)
    public String getPartnerTransId() {
        return partnerTransId;
    }

    public void setPartnerTransId(String partnerTransId) {
        this.partnerTransId = partnerTransId;
    }

//    @XmlElement(name = "RequestorTransId", required = true)
    public String getRequestorTransId() {
        return requestorTransId;
    }

    public void setRequestorTransId(String requestorTransId) {
        this.requestorTransId = requestorTransId;
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
    
    
    
}
