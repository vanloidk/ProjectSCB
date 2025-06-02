/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.billingvnpt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "PaymentBillingVNPTRp")
public class PaymentBillingVNPTRp {

    private String responseCode;
    private String description;
    private String transDateTime;
    private String transRequestId;
    private String transResponseId;
    private String options;
    private String secureCode;

    @XmlElement(name = "ResponseCode", nillable = true)
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @XmlElement(name = "Description", nillable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "TransDateTime", nillable = true)
    public String getTransDateTime() {
        return transDateTime;
    }

    public void setTransDateTime(String transDateTime) {
        this.transDateTime = transDateTime;
    }

    @XmlElement(name = "TransRequestId", nillable = true)
    public String getTransRequestId() {
        return transRequestId;
    }

    public void setTransRequestId(String transRequestId) {
        this.transRequestId = transRequestId;
    }

    @XmlElement(name = "TransResponseId", nillable = true)
    public String getTransResponseId() {
        return transResponseId;
    }

    public void setTransResponseId(String transResponseId) {
        this.transResponseId = transResponseId;
    }

    @XmlElement(name = "Options", nillable = true)
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @XmlElement(name = "SecureCode", nillable = true)
    public String getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }

}
