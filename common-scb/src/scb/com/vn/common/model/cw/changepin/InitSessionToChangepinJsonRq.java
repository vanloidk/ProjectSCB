/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cw.changepin;

import java.io.Serializable;

/**
 *
 * @author minhndb
 */
public class InitSessionToChangepinJsonRq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String partnerId;
    private String loc;
    private String last4digits;
    private String createDate;
    private String transactionId;
    private String refTransactionId;
    private String mobileNo;
    private String signature;

    //need default constructor for JSON Parsing
    public InitSessionToChangepinJsonRq() {

    }

    public InitSessionToChangepinJsonRq(String username, String password, String refTransactionId, InitSessionToChangepinRq req) {
        this.username = username;
        this.password = password;
        this.refTransactionId = refTransactionId;
        this.partnerId = req.getPartnerId();
        this.loc = req.getLoc();
        this.last4digits = req.getLast4Digits();
        this.createDate = req.getCreateDate();
        this.transactionId = req.getTransactionId();
        this.mobileNo = req.getMobileNo();
        this.signature = req.getSignature();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLast4digits() {
        return last4digits;
    }

    public void setLast4digits(String last4digits) {
        this.last4digits = last4digits;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRefTransactionId() {
        return refTransactionId;
    }

    public void setRefTransactionId(String refTransactionId) {
        this.refTransactionId = refTransactionId;
    }
}
