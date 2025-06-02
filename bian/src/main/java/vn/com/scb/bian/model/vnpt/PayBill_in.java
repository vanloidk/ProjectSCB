/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.model.vnpt;

import vn.com.scb.bian.BillPaymentInfoType;
import vn.com.scb.bian.TransactionInfoType;

/**
 *
 * @author minhndb
 */
public class PayBill_in implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private vn.com.scb.bian.TransactionInfoType transactionInfo;
    private vn.com.scb.bian.BillPaymentInfoType billPaymentInfo;
    
    public PayBill_in(TransactionInfoType transactionInfo, BillPaymentInfoType billPaymentInfo) {
        this.transactionInfo = transactionInfo;
        this.billPaymentInfo = billPaymentInfo;
    }
    
    public PayBill_in(String transId, String customerCode, String amount
            , String information, String channel) {
        this.transactionInfo = new TransactionInfoType();
        this.transactionInfo.setClientCode(channel);
        
        this.billPaymentInfo = new BillPaymentInfoType();
        this.billPaymentInfo.setBillTransCode(transId);
        this.billPaymentInfo.setBillCustomerCode(customerCode);
        this.billPaymentInfo.setBillMoreInfomation(information);
        this.billPaymentInfo.setBillPayAmount(amount);
    }

    public TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }

    public BillPaymentInfoType getBillPaymentInfo() {
        return billPaymentInfo;
    }
}