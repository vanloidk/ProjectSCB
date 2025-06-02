/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "GetCreditCardInfoRp")
public class GetCreditCardInfoRp extends MobileResponse {

    private String cardno;
    private String e_name;
    private String dunocuoiky;
    private String ngaytttoithieu;
    private String dunodasudung;
    private String thanhtoantoithieu;
    private String hanmucconlai;

    /**
     * @return the cardno
     *
     */
    @XmlElement(name = "CardNo", nillable = true)
    public String getCardno() {
        return cardno;
    }

    /**
     * @param cardno the cardno to set
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    /**
     * @return the e_name
     */
    @XmlElement(name = "CardName", nillable = true)
    public String getE_name() {
        return e_name;
    }

    /**
     * @param e_name the e_name to set
     */
    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    /**
     * @return the dunocuoiky
     */
    @XmlElement(name = "PrincipalToPay", nillable = true)
    public String getDunocuoiky() {
        return dunocuoiky;
    }

    /**
     * @param dunocuoiky the dunocuoiky to set
     */
    public void setDunocuoiky(String dunocuoiky) {
        this.dunocuoiky = dunocuoiky;
    }

    /**
     * @return the ngaytttoithieu
     */
    @XmlElement(name = "MatDateCreditCard", nillable = true)
    public String getNgaytttoithieu() {
        return ngaytttoithieu;
    }

    /**
     * @param ngaytttoithieu the ngaytttoithieu to set
     */
    public void setNgaytttoithieu(String ngaytttoithieu) {
        this.ngaytttoithieu = ngaytttoithieu;
    }

    /**
     * @return the dunodasudung
     */
    @XmlElement(name = "PrincipalUsed", nillable = true)
    public String getDunodasudung() {
        return dunodasudung;
    }

    /**
     * @param dunodasudung the dunodasudung to set
     */
    public void setDunodasudung(String dunodasudung) {
        this.dunodasudung = dunodasudung;
    }

    /**
     * @return the thanhtoantoithieu
     */
    @XmlElement(name = "AmtLimitPay", nillable = true)
    public String getThanhtoantoithieu() {
        return thanhtoantoithieu;
    }

    /**
     * @param thanhtoantoithieu the thanhtoantoithieu to set
     */
    public void setThanhtoantoithieu(String thanhtoantoithieu) {
        this.thanhtoantoithieu = thanhtoantoithieu;
    }

    /**
     * @return the hanmucconlai
     */
    @XmlElement(name = "AvaliableLimit", nillable = true)
    public String getHanmucconlai() {
        return hanmucconlai;
    }

    /**
     * @param hanmucconlai the hanmucconlai to set
     */
    public void setHanmucconlai(String hanmucconlai) {
        this.hanmucconlai = hanmucconlai;
    }

}
