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
 * @author KimNCM
 */
@XmlRootElement(name = "GetOnlineInsFormListRp")
public class GetOnlineInsFormListRp extends MobileResponse {

    private String IDSANPHAM;
    private String Discount;

    private FormDetail[] FormList;

    /**
     *
     */
    public GetOnlineInsFormListRp() {
    }

    /**
     * @return the IDSANPHAM
     */
    @XmlElement(name = "IDSANPHAM", nillable = true)
    public String getIDSANPHAM() {
        return IDSANPHAM;
    }

    /**
     * @param IDSANPHAM the IDSANPHAM to set
     */
    public void setIDSANPHAM(String IDSANPHAM) {
        this.IDSANPHAM = IDSANPHAM;
    }

    /**
     * @return the Discount
     */
    @XmlElement(name = "Discount", nillable = true)
    public String getDiscount() {
        return Discount;
    }

    /**
     * @param Discount the Discount to set
     */
    public void setDiscount(String Discount) {
        this.Discount = Discount;
    }

    /**
     * @return the FormList
     */
    @XmlElement(name = "FormDetail")
    @XmlElementWrapper(name = "FormList", nillable = true)
    public FormDetail[] getFormList() {
        return FormList;
    }

    /**
     * @param FormList the FormList to set
     */
    public void setFormList(FormDetail[] FormList) {
        this.FormList = FormList;
    }

}
