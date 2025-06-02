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
@XmlRootElement(name = "GetBillServiceRq")
public class GetBillServiceRq {

    private String language;
    private String hasauto;

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
     * @return the hasauto
     */
    @XmlElement(name = "hasauto", nillable = true)
    public String getHasauto() {
        return hasauto;
    }

    /**
     * @param hasauto the hasauto to set
     */
    public void setHasauto(String hasauto) {
        this.hasauto = hasauto;
    }
}
