/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.mobile.model;

import scb.com.vn.model.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "GetOnlineInsInfoRp")
public class GetOnlineInsInfoRp extends MobileResponse {

    private String IDSANPHAM;
    private String LOAIPHI;
    private String TENPHI;
    private String LOAIQUYENLOI;
    private String TENQUYENLOI;
    private String FORMAMT;
    private String DUTYAMT;
    private String Discount;
    private String PayAmt;

    /**
     *
     */
    public GetOnlineInsInfoRp() {
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
     * @return the FORMAMT
     */
    @XmlElement(name = "FORMAMT", nillable = true)
    public String getFORMAMT() {
        return FORMAMT;
    }

    /**
     * @param FORMAMT the FORMAMT to set
     */
    public void setFORMAMT(String FORMAMT) {
        this.FORMAMT = FORMAMT;
    }

    /**
     * @return the DUTYAMT
     */
    @XmlElement(name = "DUTYAMT", nillable = true)
    public String getDUTYAMT() {
        return DUTYAMT;
    }

    /**
     * @param DUTYAMT the DUTYAMT to set
     */
    public void setDUTYAMT(String DUTYAMT) {
        this.DUTYAMT = DUTYAMT;
    }

    /**
     * @return the PayAmt
     */
    @XmlElement(name = "PayAmt", nillable = true)
    public String getPayAmt() {
        return PayAmt;
    }

    /**
     * @param PayAmt the PayAmt to set
     */
    public void setPayAmt(String PayAmt) {
        this.PayAmt = PayAmt;
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
