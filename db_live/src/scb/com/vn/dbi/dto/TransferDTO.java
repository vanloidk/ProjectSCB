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
public class TransferDTO {
    String ID;
    String PARTNERID;
    String TRANSID;
    String TRANSDATE;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPARTNERID() {
        return PARTNERID;
    }

    public void setPARTNERID(String PARTNERID) {
        this.PARTNERID = PARTNERID;
    }

    public String getTRANSID() {
        return TRANSID;
    }

    public void setTRANSID(String TRANSID) {
        this.TRANSID = TRANSID;
    }

    public String getTRANSDATE() {
        return TRANSDATE;
    }

    public void setTRANSDATE(String TRANSDATE) {
        this.TRANSDATE = TRANSDATE;
    }
    
    
}
