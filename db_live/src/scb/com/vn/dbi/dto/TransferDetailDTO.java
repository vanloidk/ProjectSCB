/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author Administrator
 */
public class TransferDetailDTO {
    String ID;
    String ID_MASTER;
    String TXNDETAILID;
    String FROMACCOUNT;
    String DESTNAME;
    String DESTACCOUNT;
    String BANKCODE;
    String BRANCHNAME;
    String AMOUNT;
    String CCY;
    String DESCRIPTION;
    String STATUS;
    String TYPETRANSFER;
    String TYPEDESTACCOUNT;
    String Refcore;
    String ValidateCore;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID_MASTER() {
        return ID_MASTER;
    }

    public void setID_MASTER(String ID_MASTER) {
        this.ID_MASTER = ID_MASTER;
    }

    public String getTXNDETAILID() {
        return TXNDETAILID;
    }

    public void setTXNDETAILID(String TXNDETAILID) {
        this.TXNDETAILID = TXNDETAILID;
    }

    public String getFROMACCOUNT() {
        return FROMACCOUNT;
    }

    public void setFROMACCOUNT(String FROMACCOUNT) {
        this.FROMACCOUNT = FROMACCOUNT;
    }

    public String getDESTNAME() {
        return DESTNAME;
    }

    public void setDESTNAME(String DESTNAME) {
        this.DESTNAME = DESTNAME;
    }

    public String getDESTACCOUNT() {
        return DESTACCOUNT;
    }

    public void setDESTACCOUNT(String DESTACCOUNT) {
        this.DESTACCOUNT = DESTACCOUNT;
    }

    public String getBANKCODE() {
        return BANKCODE;
    }

    public void setBANKCODE(String BANKCODE) {
        this.BANKCODE = BANKCODE;
    }

    public String getBRANCHNAME() {
        return BRANCHNAME;
    }

    public void setBRANCHNAME(String BRANCHNAME) {
        this.BRANCHNAME = BRANCHNAME;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getCCY() {
        return CCY;
    }

    public void setCCY(String CCY) {
        this.CCY = CCY;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTYPETRANSFER() {
        return TYPETRANSFER;
    }

    public void setTYPETRANSFER(String TYPETRANSFER) {
        this.TYPETRANSFER = TYPETRANSFER;
    }

    public String getTYPEDESTACCOUNT() {
        return TYPEDESTACCOUNT;
    }

    public void setTYPEDESTACCOUNT(String TYPEDESTACCOUNT) {
        this.TYPEDESTACCOUNT = TYPEDESTACCOUNT;
    }

    public String getRefcore() {
        return Refcore;
    }

    public void setRefcore(String Refcore) {
        this.Refcore = Refcore;
    }

    public String getValidateCore() {
        return ValidateCore;
    }

    public void setValidateCore(String ValidateCore) {
        this.ValidateCore = ValidateCore;
    }
    
    
}
