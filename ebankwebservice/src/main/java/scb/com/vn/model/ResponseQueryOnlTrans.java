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
 * @author Administrator
 */
@XmlRootElement(name = "ResponseQueryOnlTrans")
public class ResponseQueryOnlTrans {

    String ResponseCode;
    String TRANSID;
    String BANKTRANSID;
    Double AMOUNT;
    String DESCRIPTION;
    String MAC;

    /**
     *
     * @return
     */
    @XmlElement(name = "RESPONSECODE")
    public String getResponseCode() {
        return this.ResponseCode;
    }

    /**
     *
     * @param ResponseCode
     */
    public void setResponseCode(String ResponseCode) {
        this.ResponseCode = ResponseCode;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AMOUNT")
    public Double getAmount() {
        return this.AMOUNT;
    }

    /**
     *
     * @param AMOUNT
     */
    public void setAmount(Double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "TRANSID")
    public String getTRANSID() {
        return this.TRANSID;
    }

    /**
     *
     * @param TRANSID
     */
    public void setTRANSID(String TRANSID) {
        this.TRANSID = TRANSID;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "BANKTRANSID")
    public String getTBANKTRANSID() {
        return this.BANKTRANSID;
    }

    /**
     *
     * @param BANKTRANSID
     */
    public void setBANKTRANSID(String BANKTRANSID) {
        this.BANKTRANSID = BANKTRANSID;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "DESCRIPTION")
    public String getDESCRIPTION() {
        return this.DESCRIPTION;
    }

    /**
     *
     * @param DESCRIPTION
     */
    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MAC")
    public String getMAC() {
        return this.MAC;
    }

    /**
     *
     * @param MAC
     */
    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

}
