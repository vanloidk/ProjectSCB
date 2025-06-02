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
@XmlRootElement(name = "ResponseCardVerify")
public class ResponseCardVerify {

    String ResponseCode;
    String MERCHANTID;
    String TRANSACTIONID;
    String AUTHENTICATIONURL;
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
    @XmlElement(name = "TRANSACTIONID")
    public String getTRANSACTIONID() {
        return this.TRANSACTIONID;
    }

    /**
     *
     * @param TRANSACTIONID
     */
    public void setTRANSACTIONID(String TRANSACTIONID) {
        this.TRANSACTIONID = TRANSACTIONID;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AUTHENTICATIONURL")
    public String getAUTHENTICATIONURL() {
        return this.AUTHENTICATIONURL;
    }

    /**
     *
     * @param AUTHENTICATIONURL
     */
    public void setAUTHENTICATIONURL(String AUTHENTICATIONURL) {
        this.AUTHENTICATIONURL = AUTHENTICATIONURL;
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
