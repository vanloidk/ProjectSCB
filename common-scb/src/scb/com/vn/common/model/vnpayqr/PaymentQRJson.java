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
public class PaymentQRJson implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String mobile;
    private String bankCode;
    private String accountNo;
    private String payDate;
    private String additionalData;
    private String debitAmount;
    private String respCode;
    private String respDesc;
    private String traceTransfer;
    private String messageType;
    private String orderCode;
    private String userName;
    private QrInfo[] item;
    private String checkSum;
    private String realAmount;
    private String promotionCode;
    private String rate;
    private String merchantId;

    public String getMobile() {
        return mobile;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getPayDate() {
        return payDate;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public String getDebitAmount() {
        return debitAmount;
    }

    public String getRespCode() {
        return respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public String getTraceTransfer() {
        return traceTransfer;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public String getUserName() {
        return userName;
    }

    public QrInfo[] getItem() {
        return item;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public String getRealAmount() {
        return realAmount;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public String getRate() {
        return rate;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public void setTraceTransfer(String traceTransfer) {
        this.traceTransfer = traceTransfer;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setItem(QrInfo[] item) {
        this.item = item;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String exportToJsonString()
    {
        Gson g = new Gson();
        return g.toJson(this);
    }
    
    public void preSetupData(String respCode, String respDesc, String accessKey)
    {
        this.merchantId = null;
        this.respCode = respCode;
        this.respDesc = respDesc;
        generateChecksum(accessKey);
    }
    
    private void generateChecksum(String accessKey)
    {
        this.checkSum = Common.EncriptMD5(mobile + bankCode + accountNo + payDate + debitAmount + respCode + traceTransfer
            + messageType + accessKey);
    }
}
