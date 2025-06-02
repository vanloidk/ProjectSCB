/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.partner.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "REQUEST")
public class PartnerSCBTransactionInfoDto {

    private String TRANSID = "";
    private String ACCOUNTNUM = "";
    private String DESCRIPTION = "";
    private String FROMDATE = "";
    private String TODATE = "";
    private String PARTNER = "";
    private String MAC = "";

    @XmlElement(name = "TRANSID")
    public String getTRANSID() {
        return TRANSID;
    }

    public void setTRANSID(String TRANSID) {
        this.TRANSID = TRANSID;
    }

    @XmlElement(name = "ACCOUNTNUM")
    public String getACCOUNTNUM() {
        return ACCOUNTNUM;
    }

    public void setACCOUNTNUM(String ACCOUNTNUM) {
        this.ACCOUNTNUM = ACCOUNTNUM;
    }

    @XmlElement(name = "DESCRIPTION")
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    @XmlElement(name = "FROMDATE")
    public String getFROMDATE() {
        return FROMDATE;
    }

    public void setFROMDATE(String FROMDATE) {
        this.FROMDATE = FROMDATE;
    }

    @XmlElement(name = "TODATE")
    public String getTODATE() {
        return TODATE;
    }

    public void setTODATE(String TODATE) {
        this.TODATE = TODATE;
    }

    @XmlElement(name = "PARTNER")
    public String getPARTNER() {
        return PARTNER;
    }

    public void setPARTNER(String PARTNER) {
        this.PARTNER = PARTNER;
    }

    @XmlElement(name = "MAC")
    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

}
