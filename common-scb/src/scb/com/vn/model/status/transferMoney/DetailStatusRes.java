/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.status.transferMoney;

import org.apache.log4j.Logger;

/**
 *
 * @author hienlt6
 */
public class DetailStatusRes {

    private static final Logger logger = Logger.getLogger(DetailStatusRes.class);
    private static final long serialVersionUID = 1L;
    private String responseCode;
    private String description;
    private String partnerId;
    private String signature;
    private String txnDetailId;
    private String transId;

    public DetailStatusRes() {
    }

    public DetailStatusRes(String responseCode, String description) {
        this.responseCode = responseCode;
        this.description = description;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTxnDetailId() {
        return txnDetailId;
    }

    public void setTxnDetailId(String txnDetailId) {
        this.txnDetailId = txnDetailId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

}
