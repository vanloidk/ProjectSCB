/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.math.BigDecimal;

/**
 *
 * @author lydty
 */
public class HQ_NOPTIEN_HQ_GNT implements java.io.Serializable {

    int ID;
    int IDMASTER;
    String TTBUTTOAN;
    String MA_HQ;
    String MA_LH;
    String NAM_DK;
    String SO_TK;
    String MA_LT;
    String STATUS;
    BigDecimal FEE;
    BigDecimal VAT;
    String SOTIEN;
    String REFCORE;
    String ERRMSGCORE;
    String SOTIEN_NT;
    String NGAY_DK;
    String ID_HS;

    /**
     *
     * @return
     */
    public String getID_HS() {
        return ID_HS;
    }

    /**
     *
     * @param ID_HS
     */
    public void setID_HS(String ID_HS) {
        this.ID_HS = ID_HS;
    }

    /**
     *
     * @return
     */
    public String getSOTIEN_NT() {
        return SOTIEN_NT;
    }

    /**
     *
     * @param SOTIEN_NT
     */
    public void setSOTIEN_NT(String SOTIEN_NT) {
        this.SOTIEN_NT = SOTIEN_NT;
    }

    /**
     *
     * @return
     */
    public String getTen_HQ() {
        return Ten_HQ;
    }

    /**
     *
     * @param Ten_HQ
     */
    public void setTen_HQ(String Ten_HQ) {
        this.Ten_HQ = Ten_HQ;
    }

    /**
     *
     * @return
     */
    public String getTen_LH() {
        return Ten_LH;
    }

    /**
     *
     * @param Ten_LH
     */
    public void setTen_LH(String Ten_LH) {
        this.Ten_LH = Ten_LH;
    }
    String Ten_HQ;
    String Ten_LH;

    /**
     *
     * @return
     */
    public String getREFCORE() {
        return REFCORE;
    }

    /**
     *
     * @param REFCORE
     */
    public void setREFCORE(String REFCORE) {
        this.REFCORE = REFCORE;
    }

    /**
     *
     * @return
     */
    public String getERRMSGCORE() {
        return ERRMSGCORE;
    }

    /**
     *
     * @param ERRMSGCORE
     */
    public void setERRMSGCORE(String ERRMSGCORE) {
        this.ERRMSGCORE = ERRMSGCORE;
    }

    /**
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     *
     * @return
     */
    public int getIDMASTER() {
        return IDMASTER;
    }

    /**
     *
     * @param IDMASTER
     */
    public void setIDMASTER(int IDMASTER) {
        this.IDMASTER = IDMASTER;
    }

    /**
     *
     * @return
     */
    public String getTTBUTTOAN() {
        return TTBUTTOAN;
    }

    /**
     *
     * @param TTBUTTOAN
     */
    public void setTTBUTTOAN(String TTBUTTOAN) {
        this.TTBUTTOAN = TTBUTTOAN;
    }

    /**
     *
     * @return
     */
    public String getMA_HQ() {
        return MA_HQ;
    }

    /**
     *
     * @param MA_HQ
     */
    public void setMA_HQ(String MA_HQ) {
        this.MA_HQ = MA_HQ;
    }

    /**
     *
     * @return
     */
    public String getMA_LH() {
        return MA_LH;
    }

    /**
     *
     * @param MA_LH
     */
    public void setMA_LH(String MA_LH) {
        this.MA_LH = MA_LH;
    }

    /**
     *
     * @return
     */
    public String getNAM_DK() {
        return NAM_DK;
    }

    /**
     *
     * @param NAM_DK
     */
    public void setNAM_DK(String NAM_DK) {
        this.NAM_DK = NAM_DK;
    }

    /**
     *
     * @return
     */
    public String getSO_TK() {
        return SO_TK;
    }

    /**
     *
     * @param SO_TK
     */
    public void setSO_TK(String SO_TK) {
        this.SO_TK = SO_TK;
    }

    /**
     *
     * @return
     */
    public String getMA_LT() {
        return MA_LT;
    }

    /**
     *
     * @param MA_LT
     */
    public void setMA_LT(String MA_LT) {
        this.MA_LT = MA_LT;
    }

    /**
     *
     * @return
     */
    public String getSTATUS() {
        return STATUS;
    }

    /**
     *
     * @param STATUS
     */
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    /**
     *
     * @return
     */
    public BigDecimal getFEE() {
        return FEE;
    }

    /**
     *
     * @param FEE
     */
    public void setFEE(BigDecimal FEE) {
        this.FEE = FEE;
    }

    /**
     *
     * @return
     */
    public BigDecimal getVAT() {
        return VAT;
    }

    /**
     *
     * @param VAT
     */
    public void setVAT(BigDecimal VAT) {
        this.VAT = VAT;
    }

    /**
     *
     * @return
     */
    public String getSOTIEN() {
        return SOTIEN;
    }

    /**
     *
     * @param SOTIEN
     */
    public void setSOTIEN(String SOTIEN) {
        this.SOTIEN = SOTIEN;
    }

    /**
     *
     * @return
     */
    public String getNGAY_DK() {
        return NGAY_DK;
    }

    /**
     *
     * @param NGAY_DK
     */
    public void setNGAY_DK(String NGAY_DK) {
        this.NGAY_DK = NGAY_DK;
    }
}
