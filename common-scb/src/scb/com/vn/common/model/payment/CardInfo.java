/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author FICOMBANK
 */
public class CardInfo {

    private String orgTransID;
    private List<CardDetail> carddetail;

    @XmlElement(name = "ORGTRANSID", required = false, nillable = true)
    public String getOrgTransID() {
        return orgTransID;
    }

    public void setOrgTransID(String orgTransID) {
        this.orgTransID = orgTransID;
    }

    @XmlElement(name = "CARDDETAIL", required = false, nillable = true)
    public List<CardDetail> getCarddetail() {
        return carddetail;
    }

    public void setCarddetail(List<CardDetail> carddetail) {
        this.carddetail = carddetail;
    }
    
}
