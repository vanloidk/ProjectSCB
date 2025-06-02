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
public class CardDetail {

    private String cardcode;
    private String cardserial;
    private String expiredate;

    @XmlElement(name = "CARDCODE", required = false, nillable = true)
    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    @XmlElement(name = "CARDSERIAL", required = false, nillable = true)
    public String getCardserial() {
        return cardserial;
    }

    public void setCardserial(String cardserial) {
        this.cardserial = cardserial;
    }

    @XmlElement(name = "EXPIREDATE", required = false, nillable = true)
    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }
    
}
