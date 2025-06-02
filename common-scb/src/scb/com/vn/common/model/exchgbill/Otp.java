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
public class Otp {

    private String type; //sms | token
    private String txnpin;

    @XmlElement(name = "type", nillable = true, required = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "txnpin", nillable = true, required = false)
    public String getTxnpin() {
        return txnpin;
    }

    public void setTxnpin(String txnpin) {
        this.txnpin = txnpin;
    }
}
