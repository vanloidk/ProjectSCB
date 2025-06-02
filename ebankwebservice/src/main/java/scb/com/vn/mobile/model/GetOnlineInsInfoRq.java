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
@XmlRootElement(name = "GetOnlineInsInfoRq")
public class GetOnlineInsInfoRq extends MobileModelRequest {

    private String IDSANPHAM;
    private String LOAIPHI;
    private String TENPHI;
    private String LOAIQUYENLOI;
    private String TENQUYENLOI;

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

    /**
     * @return the TENPHI
     */
    @XmlElement(name = "TENPHI", nillable = true)
    public String getTENPHI() {
        return TENPHI;
    }

    /**
     * @param TENPHI the TENPHI to set
     */
    public void setTENPHI(String TENPHI) {
        this.TENPHI = TENPHI;
    }

    /**
     * @return the LOAIQUYENLOI
     */
    @XmlElement(name = "LOAIQUYENLOI", nillable = true)
    public String getLOAIQUYENLOI() {
        return LOAIQUYENLOI;
    }

    /**
     * @param LOAIQUYENLOI the LOAIQUYENLOI to set
     */
    public void setLOAIQUYENLOI(String LOAIQUYENLOI) {
        this.LOAIQUYENLOI = LOAIQUYENLOI;
    }

    /**
     * @return the TENQUYENLOI
     */
    @XmlElement(name = "TENQUYENLOI", nillable = true)
    public String getTENQUYENLOI() {
        return TENQUYENLOI;
    }

    /**
     * @param TENQUYENLOI the TENQUYENLOI to set
     */
    public void setTENQUYENLOI(String TENQUYENLOI) {
        this.TENQUYENLOI = TENQUYENLOI;
    }

}
