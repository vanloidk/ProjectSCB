/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.model.evnhn;

import vn.com.scb.bian.BillPaymentInfoType;
import vn.com.scb.bian.TransactionInfoType;

/**
 *
 * @author minhndb
 */
public class GetBillInfo_in implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private static final String BILL_IS_PREPAID = "N";
    
    private TransactionInfoType transactionInfo;
    private BillPaymentInfoType billPaymentInfo;

    public GetBillInfo_in() {}
    
    public GetBillInfo_in(String customerCode, String channel) {
        billPaymentInfo = new BillPaymentInfoType();
        billPaymentInfo.setBillIsPrepaid(BILL_IS_PREPAID);
        billPaymentInfo.setBillCustomerCode(customerCode);
        
        transactionInfo = new TransactionInfoType();
        transactionInfo.setClientCode(channel);
    }

    public TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }

    public void setTransactionInfo(TransactionInfoType transactionInfo) {
        this.transactionInfo = transactionInfo;
    }

    public BillPaymentInfoType getBillPaymentInfo() {
        return billPaymentInfo;
    }

    public void setBillPaymentInfo(BillPaymentInfoType billPaymentInfo) {
        this.billPaymentInfo = billPaymentInfo;
    }
}