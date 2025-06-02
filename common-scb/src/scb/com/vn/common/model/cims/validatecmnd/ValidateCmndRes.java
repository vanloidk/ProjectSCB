/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.validatecmnd;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.cims.CardInfo;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "VALIDATECMNDRES")
public class ValidateCmndRes implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String phone;
    private String cmnd;
    private String status;
    private CardInfo cardInfo;

    public ValidateCmndRes() {}

    public ValidateCmndRes(String status) {
        this.status = status;
    }

    public ValidateCmndRes(String phone, String cmnd, String status) {
        this.phone = phone;
        this.cmnd = cmnd;
        this.status = status;
    }
    
    public ValidateCmndRes(ValidateCmndReq req, String status) {
        this.phone = req.getPhone();
        this.cmnd = req.getCmnd();
        this.status = status;
    }
    
    public ValidateCmndRes(ValidateCmndReq req) {
        this.phone = req.getPhone();
        this.cmnd = req.getCmnd();
        this.cardInfo = new CardInfo();
    }

    public ValidateCmndRes(String phone, String cmnd) {
        this.phone = phone;
        this.cmnd = cmnd;
        this.cardInfo = new CardInfo();
    }

    @XmlElement(name = "PHONE", nillable = true)
    public String getPhone() {
        return phone;
    }

    @XmlElement(name = "CMND", nillable = true)
    public String getCmnd() {
        return cmnd;
    }

    @XmlElement(name = "STATUS", nillable = false)
    public String getStatus() {
        return status;
    }

    @XmlElement(name = "CARDINFO", nillable = true)
    public CardInfo getCardInfo() {
        return cardInfo;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

}