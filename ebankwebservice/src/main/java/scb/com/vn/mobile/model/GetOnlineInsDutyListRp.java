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
@XmlRootElement(name = "GetOnlineInsDutyListRp")
public class GetOnlineInsDutyListRp extends MobileResponse {

    private String IDSANPHAM;
    private String LOAIPHI;
    private String Discount;

    private DutyDetail[] DutyList;

    /**
     *
     */
    public GetOnlineInsDutyListRp() {
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
     * @return the DutyList
     */
    @XmlElement(name = "DutyDetail")
    @XmlElementWrapper(name = "DutyList", nillable = true)
    public DutyDetail[] getDutyList() {
        return DutyList;
    }

    /**
     * @param DutyList the DutyList to set
     */
    public void setDutyList(DutyDetail[] DutyList) {
        this.DutyList = DutyList;
    }

    /**
     * @return the LOAIPHI
     */
    @XmlElement(name = "LOAIPHI", nillable = true)
    public String getLOAIPHI() {
        return LOAIPHI;
    }

    /**
     * @param LOAIPHI the LOAIPHI to set
     */
    public void setLOAIPHI(String LOAIPHI) {
        this.LOAIPHI = LOAIPHI;
    }

}
