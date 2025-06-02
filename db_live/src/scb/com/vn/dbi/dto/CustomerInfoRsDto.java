/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "RESPONSE")
public class CustomerInfoRsDto implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    String RESPONSECODE;
    String PROVIDERID;
    String PROFILEID;
    String ISCARDNAME;
    String ISBIRTHDAY;
    String ISMOBILENO;
    String ISIDNUMBER;
    String ISCOUNTRY;

    @XmlElement(name = "RESPONSECODE", nillable = true)
    public String getRESPONSECODE() {
        return RESPONSECODE;
    }

    public void setRESPONSECODE(String RESPONSECODE) {
        this.RESPONSECODE = RESPONSECODE;
    }

    @XmlElement(name = "PROVIDERID", nillable = true)
    public String getPROVIDERID() {
        return PROVIDERID;
    }

    public void setPROVIDERID(String PROVIDERID) {
        this.PROVIDERID = PROVIDERID;
    }

    @XmlElement(name = "PROFILEID", nillable = true)
    public String getPROFILEID() {
        return PROFILEID;
    }

    public void setPROFILEID(String PROFILEID) {
        this.PROFILEID = PROFILEID;
    }

    @XmlElement(name = "ISCARDNAME", nillable = true)
    public String getISCARDNAME() {
        return ISCARDNAME;
    }

    public void setISCARDNAME(String ISCARDNAME) {
        this.ISCARDNAME = ISCARDNAME;
    }

    @XmlElement(name = "ISBIRTHDAY", nillable = true)
    public String getISBIRTHDAY() {
        return ISBIRTHDAY;
    }

    public void setISBIRTHDAY(String ISBIRTHDAY) {
        this.ISBIRTHDAY = ISBIRTHDAY;
    }

    @XmlElement(name = "ISMOBILENO", nillable = true)
    public String getISMOBILENO() {
        return ISMOBILENO;
    }

    public void setISMOBILENO(String ISMOBILENO) {
        this.ISMOBILENO = ISMOBILENO;
    }

    @XmlElement(name = "ISIDNUMBER", nillable = true)
    public String getISIDNUMBER() {
        return ISIDNUMBER;
    }

    public void setISIDNUMBER(String ISIDNUMBER) {
        this.ISIDNUMBER = ISIDNUMBER;
    }

    @XmlElement(name = "ISCOUNTRY", nillable = true)
    public String getISCOUNTRY() {
        return ISCOUNTRY;
    }

    public void setISCOUNTRY(String ISCOUNTRY) {
        this.ISCOUNTRY = ISCOUNTRY;
    }

    //constructor
    public CustomerInfoRsDto() {
    }

}
