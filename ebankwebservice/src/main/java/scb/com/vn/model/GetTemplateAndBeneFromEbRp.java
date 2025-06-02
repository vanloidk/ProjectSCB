/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "GetTemplateAndBeneFromEbRp")
public class GetTemplateAndBeneFromEbRp extends MobileResponse {

    private BeneName[] ListBeneName = new BeneName[0];
    private Template[] ListTemplate = new Template[0];
    private String CifNo;

    /**
     *
     */
    public GetTemplateAndBeneFromEbRp() {
    }

    /**
     * @return the ListBeneName
     */
    @XmlElement(name = "BeneName")
    @XmlElementWrapper(name = "ListBeneName", nillable = true)
    public BeneName[] getListBeneName() {
        return ListBeneName;
    }

    /**
     * @param ListBeneName the ListBeneName to set
     */
    public void setListBeneName(BeneName[] ListBeneName) {
        this.ListBeneName = ListBeneName;
    }

    /**
     * @return the ListTemplate
     */
    @XmlElement(name = "Template")
    @XmlElementWrapper(name = "ListTemplate", nillable = true)
    public Template[] getListTemplate() {
        return ListTemplate;
    }

    /**
     * @param ListTemplate the ListTemplate to set
     */
    public void setListTemplate(Template[] ListTemplate) {
        this.ListTemplate = ListTemplate;
    }

    /**
     * @return the CifNo
     */
    @XmlElement(name = "CifNo")
    public String getCifNo() {
        return CifNo;
    }

    /**
     * @param CifNo the CifNo to set
     */
    public void setCifNo(String CifNo) {
        this.CifNo = CifNo;
    }
}
