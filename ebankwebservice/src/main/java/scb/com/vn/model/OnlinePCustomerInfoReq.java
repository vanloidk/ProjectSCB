/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "REQUEST")
public class OnlinePCustomerInfoReq {

    private static final long serialVersionUID = 1L;
    String COMMANDCODE;
    String PROVIDERID;
    String PROFILEID;
    String CARDNAME;
    String BIRTHDAY;
    String MOBILENO;
    String IDNUMBER;
    String COUNTRY;
    String MAC;

    @XmlElement(name = "COMMANDCODE", nillable = true)
    public String getCOMMANDCODE() {
        return COMMANDCODE;
    }

    public void setCOMMANDCODE(String cOMMANDCODE) {
        this.COMMANDCODE = cOMMANDCODE;
    }

    //constructor
    public OnlinePCustomerInfoReq() {
    }

    @XmlElement(name = "PROVIDERID", nillable = true)
    public String getPROVIDERID() {
        return PROVIDERID;
    }

    public void setPROVIDERID(String pROVIDERID) {
        this.PROVIDERID = pROVIDERID;
    }

    @XmlElement(name = "PROFILEID", nillable = true)
    public String getPROFILEID() {
        return PROFILEID;
    }

    public void setPROFILEID(String pROFILEID) {
        this.PROFILEID = pROFILEID;
    }

    @XmlElement(name = "CARDNAME", nillable = true)
    public String getCARDNAME() {
        return CARDNAME;
    }

    public void setCARDNAME(String cARDNAME) {
        this.CARDNAME = cARDNAME;
    }

    @XmlElement(name = "BIRTHDAY", nillable = true)
    public String getBIRTHDAY() {
        return BIRTHDAY;
    }

    public void setBIRTHDAY(String bIRTHDAY) {
        this.BIRTHDAY = bIRTHDAY;
    }

    @XmlElement(name = "MOBILENO", nillable = true)
    public String getMOBILENO() {
        return MOBILENO;
    }

    public void setMOBILENO(String mOBILENO) {
        this.MOBILENO = mOBILENO;
    }

    @XmlElement(name = "IDNUMBER", nillable = true)
    public String getIDNUMBER() {
        return IDNUMBER;
    }

    public void setIDNUMBER(String iDNUMBER) {
        this.IDNUMBER = iDNUMBER;
    }

    @XmlElement(name = "COUNTRY", nillable = true)
    public String getCOUNTRY() {
        return COUNTRY;
    }

    public void setCOUNTRY(String cOUNTRY) {
        this.COUNTRY = cOUNTRY;
    }
    
     @XmlElement(name = "MAC", nillable = true)
    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
}
