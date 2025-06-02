/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author minhndb
 */
public class NTDT_searchNNT implements java.io.Serializable {

    private String MST;
    private String TEN_NNT;
    private String Diachi_Nnt;
    private String MA_CQT;
    private String EMAIL_NNT;
    private String SDT_NNT;
    private String Ten_Lhe_Nthue;
    private String SERIAL_CERT_NTHUE;
    private String SUBJECT_CERT_NTHUE;
    private String ISSUER_CERT_NTHUE;
    private String createdate;
    private String Closedate;
    private String STATUS;
    private String ID;
    private String ID_NNT;
    private String REASONCONFIRM;
    private String branchid;
    private String CHECKSTATUS;

    /**
     *
     * @param CHECKSTATUS
     */
    public void setCHECKSTATUS(String CHECKSTATUS) {
        this.CHECKSTATUS = CHECKSTATUS;
    }

    /**
     *
     * @return
     */
    public String getCHECKSTATUS() {
        return CHECKSTATUS;
    }

    /**
     *
     * @return
     */
    public String getMST() {
        return this.MST;
    }

    /**
     *
     * @param MST
     */
    public void setMST(String MST) {
        this.MST = MST;
    }

    /**
     *
     * @return
     */
    public String getTEN_NNT() {
        return this.TEN_NNT;
    }

    /**
     *
     * @param TEN_NNT
     */
    public void setTEN_NNT(String TEN_NNT) {
        this.TEN_NNT = TEN_NNT;
    }

    /**
     *
     * @return
     */
    public String getDiachi_Nnt() {
        return this.Diachi_Nnt;
    }

    /**
     *
     * @param Diachi_Nnt
     */
    public void setDiachi_Nnt(String Diachi_Nnt) {
        this.Diachi_Nnt = Diachi_Nnt;
    }

    /**
     *
     * @return
     */
    public String getMA_CQT() {
        return this.MA_CQT;
    }

    /**
     *
     * @param MA_CQT
     */
    public void setMA_CQT(String MA_CQT) {
        this.MA_CQT = MA_CQT;
    }

    /**
     *
     * @return
     */
    public String getEMAIL_NNT() {
        return this.EMAIL_NNT;
    }

    /**
     *
     * @param EMAIL_NNT
     */
    public void setEMAIL_NNT(String EMAIL_NNT) {
        this.EMAIL_NNT = EMAIL_NNT;
    }

    /**
     *
     * @return
     */
    public String getSDT_NNT() {
        return this.SDT_NNT;
    }

    /**
     *
     * @param SDT_NNT
     */
    public void setSDT_NNT(String SDT_NNT) {
        this.SDT_NNT = SDT_NNT;
    }

    /**
     *
     * @return
     */
    public String getTen_Lhe_Nthue() {
        return this.Ten_Lhe_Nthue;
    }

    /**
     *
     * @param Ten_Lhe_Nthue
     */
    public void setTen_Lhe_Nthue(String Ten_Lhe_Nthue) {
        this.Ten_Lhe_Nthue = Ten_Lhe_Nthue;
    }

    /**
     *
     * @return
     */
    public String getSERIAL_CERT_NTHUE() {
        return this.SERIAL_CERT_NTHUE;
    }

    /**
     *
     * @param SERIAL_CERT_NTHUE
     */
    public void setSERIAL_CERT_NTHUE(String SERIAL_CERT_NTHUE) {
        this.SERIAL_CERT_NTHUE = SERIAL_CERT_NTHUE;
    }

    /**
     *
     * @return
     */
    public String getSUBJECT_CERT_NTHUE() {
        return this.SUBJECT_CERT_NTHUE;
    }

    /**
     *
     * @param SUBJECT_CERT_NTHUE
     */
    public void setSUBJECT_CERT_NTHUE(String SUBJECT_CERT_NTHUE) {
        this.SUBJECT_CERT_NTHUE = SUBJECT_CERT_NTHUE;
    }

    /**
     *
     * @return
     */
    public String getISSUER_CERT_NTHUE() {
        return this.ISSUER_CERT_NTHUE;
    }

    /**
     *
     * @param ISSUER_CERT_NTHUE
     */
    public void setISSUER_CERT_NTHUE(String ISSUER_CERT_NTHUE) {
        this.ISSUER_CERT_NTHUE = ISSUER_CERT_NTHUE;
    }

    /**
     *
     * @return
     */
    public String getClosedate() {
        return this.Closedate;
    }

    /**
     *
     * @param Closedate
     */
    public void setClosedate(String Closedate) {
        this.Closedate = Closedate;
    }

    /**
     *
     * @return
     */
    public String getSTATUS() {
        return this.STATUS;
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
    public String getID() {
        return this.ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *
     * @return
     */
    public String getID_NNT() {
        return this.ID_NNT;
    }

    /**
     *
     * @param ID_NNT
     */
    public void setID_NNT(String ID_NNT) {
        this.ID_NNT = ID_NNT;
    }

    /**
     * @return the REASONCONFIRM
     */
    public String getREASONCONFIRM() {
        return REASONCONFIRM;
    }

    /**
     * @param REASONCONFIRM the REASONCONFIRM to set
     */
    public void setREASONCONFIRM(String REASONCONFIRM) {
        this.REASONCONFIRM = REASONCONFIRM;
    }

    /**
     * @return the createdate
     */
    public String getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate the createdate to set
     */
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    /**
     * @return the branchid
     */
    public String getBranchid() {
        return branchid;
    }

    /**
     * @param branchid the branchid to set
     */
    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

}
