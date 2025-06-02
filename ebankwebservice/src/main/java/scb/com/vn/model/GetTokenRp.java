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
@XmlRootElement(name = "GetTokenRp")
public class GetTokenRp extends MobileResponse {

    private String CustomerNo;
    private String serialno;

    /**
     * @return the CustomerNo
     */
    @XmlElement(name = "CustomerNo", nillable = true)
    public String getCustomerNo() {
        return CustomerNo;
    }

    /**
     * @param CustomerNo the CustomerNo to set
     */
    public void setCustomerNo(String CustomerNo) {
        this.CustomerNo = CustomerNo;
    }

    /**
     * @return the serialno
     */
    @XmlElement(name = "SerialNo", nillable = true)
    public String getSerialno() {
        return serialno;
    }

    /**
     * @param serialno the serialno to set
     */
    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }
}
