/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author lydty
 */
public class HQ_MSG implements java.io.Serializable {

    int ID;
    String SENDER_CODE;
    String SENDER_NAME;
    String MESSAGE_VERSION;
    String MESSAGE_TYPE;
    String MESSAGE_NAME;
    String TRANSACTION_DATE;
    String TRANSACTION_ID;
    String REQUEST_ID;
    String MSGDATA;
    String STATUS;
    String IDREF;
    String ERRORNUMBER;
    String ERRORMESSAGE;
    String SO_TN_CT;
    String NGAY_TN_CT;

    /**
     *
     * @return
     */
    public String getSO_TN_CT() {
        return SO_TN_CT;
    }

    /**
     *
     * @param SO_TN_CT
     */
    public void setSO_TN_CT(String SO_TN_CT) {
        this.SO_TN_CT = SO_TN_CT;
    }

    /**
     *
     * @return
     */
    public String getNGAY_TN_CT() {
        return NGAY_TN_CT;
    }

    /**
     *
     * @param NGAY_TN_CT
     */
    public void setNGAY_TN_CT(String NGAY_TN_CT) {
        this.NGAY_TN_CT = NGAY_TN_CT;
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
    public String getSENDER_CODE() {
        return SENDER_CODE;
    }

    /**
     *
     * @param SENDER_CODE
     */
    public void setSENDER_CODE(String SENDER_CODE) {
        this.SENDER_CODE = SENDER_CODE;
    }

    /**
     *
     * @return
     */
    public String getSENDER_NAME() {
        return SENDER_NAME;
    }

    /**
     *
     * @param SENDER_NAME
     */
    public void setSENDER_NAME(String SENDER_NAME) {
        this.SENDER_NAME = SENDER_NAME;
    }

    /**
     *
     * @return
     */
    public String getMESSAGE_VERSION() {
        return MESSAGE_VERSION;
    }

    /**
     *
     * @param MESSAGE_VERSION
     */
    public void setMESSAGE_VERSION(String MESSAGE_VERSION) {
        this.MESSAGE_VERSION = MESSAGE_VERSION;
    }

    /**
     *
     * @return
     */
    public String getMESSAGE_TYPE() {
        return MESSAGE_TYPE;
    }

    /**
     *
     * @param MESSAGE_TYPE
     */
    public void setMESSAGE_TYPE(String MESSAGE_TYPE) {
        this.MESSAGE_TYPE = MESSAGE_TYPE;
    }

    /**
     *
     * @return
     */
    public String getMESSAGE_NAME() {
        return MESSAGE_NAME;
    }

    /**
     *
     * @param MESSAGE_NAME
     */
    public void setMESSAGE_NAME(String MESSAGE_NAME) {
        this.MESSAGE_NAME = MESSAGE_NAME;
    }

    /**
     *
     * @return
     */
    public String getTRANSACTION_DATE() {
        return TRANSACTION_DATE;
    }

    /**
     *
     * @param TRANSACTION_DATE
     */
    public void setTRANSACTION_DATE(String TRANSACTION_DATE) {
        this.TRANSACTION_DATE = TRANSACTION_DATE;
    }

    /**
     *
     * @return
     */
    public String getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    /**
     *
     * @param TRANSACTION_ID
     */
    public void setTRANSACTION_ID(String TRANSACTION_ID) {
        this.TRANSACTION_ID = TRANSACTION_ID;
    }

    /**
     *
     * @return
     */
    public String getREQUEST_ID() {
        return REQUEST_ID;
    }

    /**
     *
     * @param REQUEST_ID
     */
    public void setREQUEST_ID(String REQUEST_ID) {
        this.REQUEST_ID = REQUEST_ID;
    }

    /**
     *
     * @return
     */
    public String getMSGDATA() {
        return MSGDATA;
    }

    /**
     *
     * @param MSGDATA
     */
    public void setMSGDATA(String MSGDATA) {
        this.MSGDATA = MSGDATA;
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
    public String getIDREF() {
        return IDREF;
    }

    /**
     *
     * @param IDREF
     */
    public void setIDREF(String IDREF) {
        this.IDREF = IDREF;
    }

    /**
     *
     * @return
     */
    public String getERRORNUMBER() {
        return ERRORNUMBER;
    }

    /**
     *
     * @param ERRORNUMBER
     */
    public void setERRORNUMBER(String ERRORNUMBER) {
        this.ERRORNUMBER = ERRORNUMBER;
    }

    /**
     *
     * @return
     */
    public String getERRORMESSAGE() {
        return ERRORMESSAGE;
    }

    /**
     *
     * @param ERRORMESSAGE
     */
    public void setERRORMESSAGE(String ERRORMESSAGE) {
        this.ERRORMESSAGE = ERRORMESSAGE;
    }

}
