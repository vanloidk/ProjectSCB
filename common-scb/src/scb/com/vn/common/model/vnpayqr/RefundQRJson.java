/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.vnpayqr;

import com.google.gson.Gson;
import scb.com.vn.ultility.Common;

/**
 *
 * @author minhndb
 */
public class RefundQRJson implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String refundTrace;
    private String bankCode;
    private String bankTrace;
    private String payCode;
    private String code;
    private String message;
    private String payDate;
    private String checkSum;

    public String getRefundTrace() {
        return refundTrace;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBankTrace() {
        return bankTrace;
    }

    public String getPayCode() {
        return payCode;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getPayDate() {
        return payDate;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setRefundTrace(String refundTrace) {
        this.refundTrace = refundTrace;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setBankTrace(String bankTrace) {
        this.bankTrace = bankTrace;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }
    
    public void preSetupData(String code, String message, String accessKey)
    {
        this.code = code;
        this.message = message;
        generateChecksum(accessKey);
    }
    
    private void generateChecksum(String accessKey)
    {
        this.checkSum = Common.EncriptMD5(bankCode + bankTrace + payCode + code + accessKey);
    }
    
    public String exportToJsonString()
    {
        Gson g = new Gson();
        return g.toJson(this);
    }
}