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
@XmlRootElement(name = "ResponseRefundOnlTrans")
public class ResponseRefundOnlTrans {

    String ResponseCode;
    String MERCHANTID;
    String TRANSID;
    String BANKTRANSID;
    String REFUNDTRANSID;
    String ADDINFO;
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
    @XmlElement(name = "MERCHANTID")
    public String getMERCHANTID() {
        return this.MERCHANTID;
    }

    /**
     *
     * @param MERCHANTID
     */
    public void setMERCHANTID(String MERCHANTID) {
        this.MERCHANTID = MERCHANTID;
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
    @XmlElement(name = "REFUNDTRANSID")
    public String getREFUNDTRANSID() {
        return this.REFUNDTRANSID;
    }

    /**
     *
     * @param REFUNDTRANSID
     */
    public void setREFUNDTRANSID(String REFUNDTRANSID) {
        this.REFUNDTRANSID = REFUNDTRANSID;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ADDINFO")
    public String getADDINFO() {
        return this.ADDINFO;
    }

    /**
     *
     * @param ADDINFO
     */
    public void setADDINFO(String ADDINFO) {
        this.ADDINFO = ADDINFO;
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
