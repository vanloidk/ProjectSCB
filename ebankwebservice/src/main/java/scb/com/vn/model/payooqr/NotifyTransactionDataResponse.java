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
public class NotifyTransactionDataResponse {
    Object embeddedData;
    String orderNo;
    String transactionDate;
    String responseNo;

    public Object getEmbeddedData() {
        return embeddedData;
    }

    public void setEmbeddedData(Object embeddedData) {
        this.embeddedData = embeddedData;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getResponseNo() {
        return responseNo;
    }

    public void setResponseNo(String responseNo) {
        this.responseNo = responseNo;
    }
}
