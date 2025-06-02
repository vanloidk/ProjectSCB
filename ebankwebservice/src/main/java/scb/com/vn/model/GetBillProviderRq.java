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
@XmlRootElement(name = "GetBillProviderRq")
public class GetBillProviderRq {

    private String idservicetype;
    private String language;
    private String hasauto;

    /**
     * @return the idservicetype
     */
    @XmlElement(name = "BillServiceId", nillable = true)
    public String getIdservicetype() {
        return idservicetype;
    }

    /**
     * @param idservicetype the idservicetype to set
     */
    public void setIdservicetype(String idservicetype) {
        this.idservicetype = idservicetype;
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
