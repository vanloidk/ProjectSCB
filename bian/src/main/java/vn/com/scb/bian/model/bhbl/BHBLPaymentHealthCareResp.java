/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.model.bhbl;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "UrlOnepayRp")
@XmlType(propOrder = {"errorCode", "errorMsg", "payment_url"})
public class BHBLPaymentHealthCareResp implements Serializable {

    private static final long serialVersionUID = -1L;
    private String errorCode;
    private String errorMsg;
    private String payment_url;

    @XmlElement(name = "ErrorCode", nillable = true)
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @XmlElement(name = "ErrorMsg", nillable = true)
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @XmlElement(name = "payment_url")
    public String getPayment_url() {
        return payment_url;
    }

    public void setPayment_url(String payment_url) {
        this.payment_url = payment_url;
    }

}
