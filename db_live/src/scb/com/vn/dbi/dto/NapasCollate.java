/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author longlt
 */
public class NapasCollate implements java.io.Serializable {

    private String ACCOUNTNO;
    private String PROCESSINGCODDE;
    private String AMOUNT;
    private String AUDITNUMBER;
    private String TRANSTIME;
    private String TRANSDATE;
    private String PAYDATE;
    private String MERCHANTTYPE;
    private String ACQUIRINGCODE;
    private String AUTHORIZATIONNUMBER;
    private String TERMID;
    private String CCY;
    private String SOURCEACCOUNT;
    private String DESTACCOUNT;
    private String BENID;
    private String RC;
    private String CHECKSUM;
    private String TYPE;
    private String FILETYPE;

    /**
     * Create a new instance of NapasCollate
     */
    public NapasCollate() {
    }

    /**
     *
     * @return
     */
    public String getACCOUNTNO() {
        return ACCOUNTNO;
    }

    /**
     *
     * @param ACCOUNTNO
     */
    public void setACCOUNTNO(String ACCOUNTNO) {
        this.ACCOUNTNO = ACCOUNTNO;
    }

    /**
     *
     * @return
     */
    public String getPROCESSINGCODDE() {
        return PROCESSINGCODDE;
    }

    /**
     *
     * @param PROCESSINGCODDE
     */
    public void setPROCESSINGCODDE(String PROCESSINGCODDE) {
        this.PROCESSINGCODDE = PROCESSINGCODDE;
    }

    /**
     *
     * @return
     */
    public String getAMOUNT() {
        return AMOUNT;
    }

    /**
     *
     * @param AMOUNT
     */
    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    /**
     *
     * @return
     */
    public String getAUDITNUMBER() {
        return AUDITNUMBER;
    }

    /**
     *
     * @param AUDITNUMBER
     */
    public void setAUDITNUMBER(String AUDITNUMBER) {
        this.AUDITNUMBER = AUDITNUMBER;
    }

    /**
     *
     * @return
     */
    public String getTRANSTIME() {
        return TRANSTIME;
    }

    /**
     *
     * @param TRANSTIME
     */
    public void setTRANSTIME(String TRANSTIME) {
        this.TRANSTIME = TRANSTIME;
    }

    /**
     *
     * @return
     */
    public String getTRANSDATE() {
        return TRANSDATE;
    }

    /**
     *
     * @param TRANSDATE
     */
    public void setTRANSDATE(String TRANSDATE) {
        this.TRANSDATE = TRANSDATE;
    }

    /**
     *
     * @return
     */
    public String getPAYDATE() {
        return PAYDATE;
    }

    /**
     *
     * @param PAYDATE
     */
    public void setPAYDATE(String PAYDATE) {
        this.PAYDATE = PAYDATE;
    }

    /**
     *
     * @return
     */
    public String getMERCHANTTYPE() {
        return MERCHANTTYPE;
    }

    /**
     *
     * @param MERCHANTTYPE
     */
    public void setMERCHANTTYPE(String MERCHANTTYPE) {
        this.MERCHANTTYPE = MERCHANTTYPE;
    }

    /**
     *
     * @return
     */
    public String getACQUIRINGCODE() {
        return ACQUIRINGCODE;
    }

    /**
     *
     * @param ACQUIRINGCODE
     */
    public void setACQUIRINGCODE(String ACQUIRINGCODE) {
        this.ACQUIRINGCODE = ACQUIRINGCODE;
    }

    /**
     *
     * @return
     */
    public String getAUTHORIZATIONNUMBER() {
        return AUTHORIZATIONNUMBER;
    }

    /**
     *
     * @param AUTHORIZATIONNUMBER
     */
    public void setAUTHORIZATIONNUMBER(String AUTHORIZATIONNUMBER) {
        this.AUTHORIZATIONNUMBER = AUTHORIZATIONNUMBER;
    }

    /**
     *
     * @return
     */
    public String getTERMID() {
        return TERMID;
    }

    /**
     *
     * @param TERMID
     */
    public void setTERMID(String TERMID) {
        this.TERMID = TERMID;
    }

    /**
     *
     * @return
     */
    public String getCCY() {
        return CCY;
    }

    /**
     *
     * @param CCY
     */
    public void setCCY(String CCY) {
        this.CCY = CCY;
    }

    /**
     *
     * @return
     */
    public String getSOURCEACCOUNT() {
        return SOURCEACCOUNT;
    }

    /**
     *
     * @param SOURCEACCOUNT
     */
    public void setSOURCEACCOUNT(String SOURCEACCOUNT) {
        this.SOURCEACCOUNT = SOURCEACCOUNT;
    }

    /**
     *
     * @return
     */
    public String getDESTACCOUNT() {
        return DESTACCOUNT;
    }

    /**
     *
     * @param DESTACCOUNT
     */
    public void setDESTACCOUNT(String DESTACCOUNT) {
        this.DESTACCOUNT = DESTACCOUNT;
    }

    /**
     *
     * @return
     */
    public String getBENID() {
        return BENID;
    }

    /**
     *
     * @param BENID
     */
    public void setBENID(String BENID) {
        this.BENID = BENID;
    }

    /**
     *
     * @return
     */
    public String getRC() {
        return RC;
    }

    /**
     *
     * @param RC
     */
    public void setRC(String RC) {
        this.RC = RC;
    }

    /**
     *
     * @return
     */
    public String getCHECKSUM() {
        return CHECKSUM;
    }

    /**
     *
     * @param CHECKSUM
     */
    public void setCHECKSUM(String CHECKSUM) {
        this.CHECKSUM = CHECKSUM;
    }

    /**
     *
     * @return
     */
    public String getTYPE() {
        return TYPE;
    }

    /**
     *
     * @param TYPE
     */
    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    /**
     *
     * @return
     */
    public String getFILETYPE() {
        return FILETYPE;
    }

    /**
     *
     * @param FILETYPE
     */
    public void setFILETYPE(String FILETYPE) {
        this.FILETYPE = FILETYPE;
    }
}
