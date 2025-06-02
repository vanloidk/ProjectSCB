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
@XmlRootElement(name = "InterestRateRq")
public class InterestRateRq {

    private String ccy;
    private String language;
    private String typetd;
    private String cifNo;

    /**
     * @return the ccy
     */
    @XmlElement(name = "Ccy", nillable = true)
    public String getCcy() {
        return ccy;
    }

    /**
     * @param ccy the ccy to set
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * @return the language
     */
    @XmlElement(name = "Language", nillable = true)
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the typetd
     */
    @XmlElement(name = "Typetd", nillable = true)
    public String getTypetd() {
        return typetd;
    }

    /**
     * @param typetd the typetd to set
     */
    public void setTypetd(String typetd) {
        this.typetd = typetd;
    }

    /**
     * @return the cifNo
     */
    @XmlElement(name = "CifNo", nillable = true)
    public String getCifNo() {
        return cifNo;
    }

    /**
     * @param cifNo the cifNo to set
     */
    public void setCifNo(String cifNo) {
        this.cifNo = cifNo;
    }
}
