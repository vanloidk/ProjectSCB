/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.vnpayqr;

import com.google.gson.Gson;

/**
 *
 * @author minhndb
 */
public class CheckQRJson implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String bankCode;
    private String payType;
    private QrInfo[] item;
    private String payCode;
    private String checkSum;
    private String voucherCode;
    private String mobile;
    private String debitAmount;

    public String getBankCode() {
        return bankCode;
    }

    public String getPayType() {
        return payType;
    }

    public QrInfo[] getItem() {
        return item;
    }

    public String getPayCode() {
        return payCode;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public String getMobile() {
        return mobile;
    }

    public String getDebitAmount() {
        return debitAmount;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setItem(QrInfo[] item) {
        this.item = item;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }
    
    public String exportToJsonString()
    {
        Gson g = new Gson();
        return g.toJson(this);
    }
}