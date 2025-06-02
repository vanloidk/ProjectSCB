/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "GetInsurancePartnerRp")
public class GetInsurancePartnerRp extends MobileResponse {

    //private String idservicetype;
    private String Language;
    private BillProviderDetail[] ListBillProvider;

    /**
     * @return the ListBillProvider
     */
    @XmlElement(name = "SecuritiesPartnerDetail")
    @XmlElementWrapper(name = "ListSecuritiesPartner", nillable = true)
    public BillProviderDetail[] getListBillProvider() {
        return ListBillProvider;
    }

    /**
     * @param ListBillProvider the ListBillProvider to set
     */
    public void setListBillProvider(BillProviderDetail[] ListBillProvider) {
        this.ListBillProvider = ListBillProvider;
    }

    /**
     * @return the idservicetype
     *
     * @XmlElement(name = "BillServiceId", nillable = true) public String
     * getIdservicetype() { return idservicetype; }
     *
     * /
     **
     * @param idservicetype the idservicetype to set
     *
     * public void setIdservicetype(String idservicetype) { this.idservicetype =
     * idservicetype; }
     */
    /**
     * @return the Language
     */
    @XmlElement(name = "Language", nillable = true)
    public String getLanguage() {
        return Language;
    }

    /**
     * @param Language the Language to set
     */
    public void setLanguage(String Language) {
        this.Language = Language;
    }
}
