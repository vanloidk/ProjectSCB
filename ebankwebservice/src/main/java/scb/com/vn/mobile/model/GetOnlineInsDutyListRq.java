/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimncm
 */
@XmlRootElement(name = "GetOnlineInsDutyListRq")
public class GetOnlineInsDutyListRq extends MobileModelRequest {

    private String IDSANPHAM;
    private String LOAIPHI;

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
