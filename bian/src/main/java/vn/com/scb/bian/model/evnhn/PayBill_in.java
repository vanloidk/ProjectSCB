/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.model.evnhn;

import java.io.Serializable;
import java.util.List;
import vn.com.scb.bian.BillPaymentDetailType;
import vn.com.scb.bian.BillPaymentInfoType;
import vn.com.scb.bian.TransactionInfoType;

/**
 *
 * @author minhndb
 */
public class PayBill_in implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private TransactionInfoType transactionInfo;
    private BillPaymentInfoType billPaymentInfo;
    private List<BillPaymentDetailType> billPaymentDetail;

    public PayBill_in() {}

    public PayBill_in(String billTransCode, String clientCode, String idBillPaid
            , List<BillPaymentDetailType> billPaymentDetail) {
        this.billPaymentInfo = new BillPaymentInfoType();
        billPaymentInfo.setBillTransCode(billTransCode);
        billPaymentInfo.setIdBillPaid(idBillPaid);
        
        this.transactionInfo = new TransactionInfoType();
        transactionInfo.setClientCode(clientCode);
        
        this.billPaymentDetail = billPaymentDetail;
    }
    
    public PayBill_in(String billTransCode, String clientCode, String idBillPaid, String paymentMethod
            , List<BillPaymentDetailType> billPaymentDetail) {
        this.billPaymentInfo = new BillPaymentInfoType();
        billPaymentInfo.setBillTransCode(billTransCode);
        billPaymentInfo.setIdBillPaid(idBillPaid);
        billPaymentInfo.setBillPaymentMethod(paymentMethod);
        
        this.transactionInfo = new TransactionInfoType();
        transactionInfo.setClientCode(clientCode);
        
        this.billPaymentDetail = billPaymentDetail;
    }

    public TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }

    public BillPaymentInfoType getBillPaymentInfo() {
        return billPaymentInfo;
    }

    public List<BillPaymentDetailType> getBillPaymentDetail() {
        return billPaymentDetail;
    }
}