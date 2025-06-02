/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author FICOMBANK
 */
public class Request {

    private Billing billing;
    private Vntopup vntopup;

    @XmlElement(name = "VNTOPUP", required = false, nillable = true)
    public Vntopup getVntopup() {
        return vntopup;
    }

    public void setVntopup(Vntopup vntopup) {
        this.vntopup = vntopup;
    }

    @XmlElement(name = "BILLING", required = false, nillable = true)
    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

}
