/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.payooqr;

/**
 *
 * @author Administrator
 */
public class CheckOrderDataResponse {
        String expiredDate;
    long moneyTotal;
    String orderNo;
    String responseNo;
    String merchantId;

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public long getMoneyTotal() {
        return moneyTotal;
    }

    public void setMoneyTotal(long moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getResponseNo() {
        return responseNo;
    }

    public void setResponseNo(String responseNo) {
        this.responseNo = responseNo;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    
}
