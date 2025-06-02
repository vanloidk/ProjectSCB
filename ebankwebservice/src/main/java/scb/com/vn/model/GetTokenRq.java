/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "GetTokenRq")
public class GetTokenRq {

    private String CustomerNo;

    /**
     *
     */
    public GetTokenRq() {
    }

    /**
     * @return the CustomerNo
     */
    @XmlElement(name = "CustomerNo")
    public String getCustomerNo() {
        return CustomerNo;
    }

    /**
     * @param CustomerNo the CustomerNo to set
     */
    public void setCustomerNo(String CustomerNo) {
        this.CustomerNo = CustomerNo;
    }
}
