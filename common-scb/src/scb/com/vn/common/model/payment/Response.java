/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author FICOMBANK
 */
public class Response {

    private String idpartner;
    private Electric electric;
    private ThongTinPhieuThu ttpt;
    private ThongTinChiTietPhieuThu ttctpt;
    private Airline airline;
    private Vntopup vntopup;
    private Billing billing;
    private Logistics logistics;
    private CardInfo cardinfo;

    @XmlElement(name = "LOGISTICS", required = false, nillable = true)
    public Logistics getLogistics() {
        return logistics;
    }

    public void setLogistics(Logistics logistics) {
        this.logistics = logistics;
    }

    @XmlElement(name = "CARDINFO", required = false, nillable = true)
    public CardInfo getCardinfo() {
        return cardinfo;
    }

    public void setCardinfo(CardInfo cardinfo) {
        this.cardinfo = cardinfo;
    }

    @XmlElement(name = "PARTNER", required = false, nillable = true)
    public String getIdpartner() {
        return idpartner;
    }

    public void setIdpartner(String idpartner) {
        this.idpartner = idpartner;
    }

    @XmlElement(name = "AIRLINE", required = false, nillable = true)
    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

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

    @XmlElement(name = "ELECTRIC", required = false, nillable = true)
    public Electric getElectric() {
        return electric;
    }

    public void setElectric(Electric electric) {
        this.electric = electric;
    }
    
    @XmlElement(name = "TTPT", required = false, nillable = true)
    public ThongTinPhieuThu getTtpt() {
        return ttpt;
    }

    public void setTtpt(ThongTinPhieuThu ttpt) {
        this.ttpt = ttpt;
    }
    
    @XmlElement(name = "TTCTPT", required = false, nillable = true)
    public ThongTinChiTietPhieuThu getTtctpt() {
        return ttctpt;
    }

    public void setTtctpt(ThongTinChiTietPhieuThu ttctpt) {
        this.ttctpt = ttctpt;
    }
//
//    @XmlElement(name = "Item")
//    @XmlElementWrapper(name = "Data", nillable = true)
//    public List<CardDetail> getCarddetail() {
//        return carddetail;
//    }
//
//    public void setCarddetail(List<CardDetail> carddetail) {
//        this.carddetail = carddetail;
//    }
    
}
