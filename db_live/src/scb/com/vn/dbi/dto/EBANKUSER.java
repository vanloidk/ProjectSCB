/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.util.List;

/**
 *
 * @author lydty
 */
public class EBANKUSER implements java.io.Serializable {

    private String CUST_NO;
    private List CUST_AC_NO;
    private String FULL_NAME;
    private String IDNUMBER;
    private String IDTYPE;
    private String ISSUEDATE;
    private String ISSUEPLACE;
    private String ADDRESS;
    private String PHONENUMBER;
    private String CHK_STATUS;
    private String Iduser;
    private String PARTNER;
    private String IDTrans;
    private String OTP;

    /**
     *
     * @return
     */
    public String getOTP() {
        return OTP;
    }

    /**
     *
     * @param OTP
     */
    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    /**
     *
     * @return
     */
    public String getIDTrans() {
        return IDTrans;
    }

    /**
     *
     * @param IDTrans
     */
    public void setIDTrans(String IDTrans) {
        this.IDTrans = IDTrans;
    }

    /**
     *
     * @return
     */
    public String getPARTNER() {
        return PARTNER;
    }

    /**
     *
     * @param PARTNER
     */
    public void setPARTNER(String PARTNER) {
        this.PARTNER = PARTNER;
    }

    /**
     *
     * @return
     */
    public String getIduser() {
        return Iduser;
    }

    /**
     *
     * @param Iduser
     */
    public void setIduser(String Iduser) {
        this.Iduser = Iduser;
    }

    /**
     *
     * @return
     */
    public String getCUST_NO() {
        return CUST_NO;
    }

    /**
     *
     * @param CUST_NO
     */
    public void setCUST_NO(String CUST_NO) {
        this.CUST_NO = CUST_NO;
    }

    /**
     *
     * @return
     */
    public List getCUST_AC_NO() {
        return CUST_AC_NO;
    }

    /**
     *
     * @param CUST_AC_NO
     */
    public void setCUST_AC_NO(List CUST_AC_NO) {
        this.CUST_AC_NO = CUST_AC_NO;
    }

    /**
     *
     * @return
     */
    public String getFULL_NAME() {
        return FULL_NAME;
    }

    /**
     *
     * @param FULL_NAME
     */
    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    /**
     *
     * @return
     */
    public String getIDNUMBER() {
        return IDNUMBER;
    }

    /**
     *
     * @param IDNUMBER
     */
    public void setIDNUMBER(String IDNUMBER) {
        this.IDNUMBER = IDNUMBER;
    }

    /**
     *
     * @return
     */
    public String getIDTYPE() {
        return IDTYPE;
    }

    /**
     *
     * @param IDTYPE
     */
    public void setIDTYPE(String IDTYPE) {
        this.IDTYPE = IDTYPE;
    }

    /**
     *
     * @return
     */
    public String getISSUEDATE() {
        return ISSUEDATE;
    }

    /**
     *
     * @param ISSUEDATE
     */
    public void setISSUEDATE(String ISSUEDATE) {
        this.ISSUEDATE = ISSUEDATE;
    }

    /**
     *
     * @return
     */
    public String getISSUEPLACE() {
        return ISSUEPLACE;
    }

    /**
     *
     * @param ISSUEPLACE
     */
    public void setISSUEPLACE(String ISSUEPLACE) {
        this.ISSUEPLACE = ISSUEPLACE;
    }

    /**
     *
     * @return
     */
    public String getADDRESS() {
        return ADDRESS;
    }

    /**
     *
     * @param ADDRESS
     */
    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    /**
     *
     * @return
     */
    public String getPHONENUMBER() {
        return PHONENUMBER;
    }

    /**
     *
     * @param PHONENUMBER
     */
    public void setPHONENUMBER(String PHONENUMBER) {
        this.PHONENUMBER = PHONENUMBER;
    }

    /**
     *
     * @return
     */
    public String getCHK_STATUS() {
        return CHK_STATUS;
    }

    /**
     *
     * @param CHK_STATUS
     */
    public void setCHK_STATUS(String CHK_STATUS) {
        this.CHK_STATUS = CHK_STATUS;
    }

}
