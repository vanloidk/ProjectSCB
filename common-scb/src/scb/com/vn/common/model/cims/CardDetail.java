/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "CARDDETAIL")
public class CardDetail implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String last4Digits;
    private String cardType;
    private boolean active;
    private int cardTypeM; // 1: VSDB, 2: VSCD, 3: MCDB, 4: MCCD, 5: LCDB, 6: LCCD

    @XmlElement(name = "LAST4DIGITS", nillable = false)
    public String getLast4Digits() {
        return last4Digits;
    }

    @XmlElement(name = "CARDTYPE", nillable = false)
    public String getCardType() {
        return cardType;
    }

    @XmlElement(name = "CARDTYPEM", nillable = false)
    public int getCardTypeM() {
        return cardTypeM;
    }

    @XmlElement(name = "ISACTIVE", nillable = false)
    public boolean isActive() {
        return active;
    }

    public void setLast4Digits(String last4Digits) {
        this.last4Digits = last4Digits;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // 1: VSDB, 2: MCDB, 3: LCDB, 4: VSCD, 5: MCCD, 6: LCCD
    public void setCardTypeM(String locLimit, String cardBranch) {
        if ("0".equals(locLimit)) {
            switch (cardBranch) {
                case "VS":
                    this.cardTypeM = 1;
                    break;
                case "MC":
                    this.cardTypeM = 2;
                    break;
                default: // LC
                    this.cardTypeM = 3;
                    break;
            }
        } else {
            switch (cardBranch) {
                case "VS":
                    this.cardTypeM = 4;
                    break;
                case "MC":
                    this.cardTypeM = 5;
                    break;
                default: // LC
                    this.cardTypeM = 6;
                    break;
            }
        }
        
    }
}