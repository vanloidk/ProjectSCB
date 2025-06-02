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
@XmlRootElement(name = "CustomerRq")
public class CustomerRq {

    private String RequestType;
    private String RequestValue;

    /**
     *
     */
    public CustomerRq() {
    }

    /**
     * @return the RequestType
     */
    @XmlElement(name = "RequestType")
    public String getRequestType() {
        return RequestType;
    }

    /**
     * @param RequestType the RequestType to set
     */
    public void setRequestType(String RequestType) {
        this.RequestType = RequestType;
    }

    /**
     * @return the RequestValue
     */
    @XmlElement(name = "RequestValue")
    public String getRequestValue() {
        return RequestValue;
    }

    /**
     * @param RequestValue the RequestValue to set
     */
    public void setRequestValue(String RequestValue) {
        this.RequestValue = RequestValue;
    }
}
