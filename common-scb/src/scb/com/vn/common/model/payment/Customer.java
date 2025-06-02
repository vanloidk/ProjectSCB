/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author phucnnd
 */
//@XmlRootElement(name="CUSTOMER")
public class Customer {

    String CUSTOMERNAME;
    String POSITION;

    public String getCUSTOMERNAME() {
        return CUSTOMERNAME;
    }

    @XmlElement(name = "CUSTOMERNAME")
    public void setCUSTOMERNAME(String CUSTOMERNAME) {
        this.CUSTOMERNAME = CUSTOMERNAME;
    }

    public String getPOSITION() {
        return POSITION;
    }

    @XmlElement(name = "POSITION")
    public void setPOSITION(String POSITION) {
        this.POSITION = POSITION;
    }
}
