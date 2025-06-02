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
public class SmsOtt implements java.io.Serializable{
    String MESSAGEID;
    int TYPE;
    String MOBILE;
    String CIF;
    String CONTENT;
    int PRIORITY;
    String MEDIAURL;
    String MEDIATYPE;
    String EXPIRETIME;
    String MESSAGETIME;
    int ISENCRYPT;
    int MULTIID;
    int TOTALMULTI;
    int ISMULTI;
    int STATUS;
    String CODE;
    String DESCTIOPTION;

    public int getISENCRYPT() {
        return ISENCRYPT;
    }

    public void setISENCRYPT(int ISENCRYPT) {
        this.ISENCRYPT = ISENCRYPT;
    }
    
    

    public String getMESSAGEID() {
        return MESSAGEID;
    }

    public void setMESSAGEID(String MESSAGEID) {
        this.MESSAGEID = MESSAGEID;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public int getPRIORITY() {
        return PRIORITY;
    }

    public void setPRIORITY(int PRIORITY) {
        this.PRIORITY = PRIORITY;
    }    

    public String getMEDIAURL() {
        return MEDIAURL;
    }

    public void setMEDIAURL(String MEDIAURL) {
        this.MEDIAURL = MEDIAURL;
    }

    public String getMEDIATYPE() {
        return MEDIATYPE;
    }

    public void setMEDIATYPE(String MEDIATYPE) {
        this.MEDIATYPE = MEDIATYPE;
    }

    public String getEXPIRETIME() {
        return EXPIRETIME;
    }

    public void setEXPIRETIME(String EXPIRETIME) {
        this.EXPIRETIME = EXPIRETIME;
    }

    public String getMESSAGETIME() {
        return MESSAGETIME;
    }

    public void setMESSAGETIME(String MESSAGETIME) {
        this.MESSAGETIME = MESSAGETIME;
    }



    public int getMULTIID() {
        return MULTIID;
    }

    public void setMULTIID(int MULTIID) {
        this.MULTIID = MULTIID;
    }

    public int getTOTALMULTI() {
        return TOTALMULTI;
    }

    public void setTOTALMULTI(int TOTALMULTI) {
        this.TOTALMULTI = TOTALMULTI;
    }

    public int getISMULTI() {
        return ISMULTI;
    }

    public void setISMULTI(int ISMULTI) {
        this.ISMULTI = ISMULTI;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }


    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getDESCTIOPTION() {
        return DESCTIOPTION;
    }

    public void setDESCTIOPTION(String DESCTIOPTION) {
        this.DESCTIOPTION = DESCTIOPTION;
    }
    

}
