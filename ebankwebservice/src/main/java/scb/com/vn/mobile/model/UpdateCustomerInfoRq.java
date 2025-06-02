/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "UpdateCustomerInfoRq")
public class UpdateCustomerInfoRq extends MobileModelRequest {

    private String EmailCust;

    /**
     * @return the EmailCust
     */
    @XmlElement(name = "EmailCust", nillable = true)
    public String getEmailCust() {
        return EmailCust;
    }

    /**
     * @param EmailCust the EmailCust to set
     */
    public void setEmailCust(String EmailCust) {
        this.EmailCust = EmailCust;
    }
}
