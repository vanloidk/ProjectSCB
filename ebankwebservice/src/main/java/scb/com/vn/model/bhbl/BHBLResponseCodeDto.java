/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.bhbl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "BLHealthCareContractRp")
@XmlType(propOrder = {"errorCode", "errorMsg", "txnIDSCB", "txnIdPartner", "paymenmtUrl"})
public class BHBLResponseCodeDto {

    private String errorCode;
    private String errorMsg;
    private String txnIDSCB;
    private String txnIdPartner;
    private String paymenmtUrl;

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

    @XmlElement(name = "TxnIDSCB", nillable = true)
    public String getTxnIDSCB() {
        return txnIDSCB;
    }

    public void setTxnIDSCB(String txnIDSCB) {
        this.txnIDSCB = txnIDSCB;
    }

    @XmlElement(name = "TxnIdPartner", nillable = true)
    public String getTxnIdPartner() {
        return txnIdPartner;
    }

    public void setTxnIdPartner(String txnIdPartner) {
        this.txnIdPartner = txnIdPartner;
    }

    @XmlElement(name = "Payment_url", nillable = true)
    public String getPaymenmtUrl() {
        return paymenmtUrl;
    }

    public void setPaymenmtUrl(String paymenmtUrl) {
        this.paymenmtUrl = paymenmtUrl;
    }
    
}
