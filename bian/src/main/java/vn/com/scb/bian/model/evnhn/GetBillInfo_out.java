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
public class GetBillInfo_out implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private TransactionInfoType transactionInfo;
    private BillPaymentInfoType billPaymentInfo;
    private List<BillPaymentDetailType> billPaymentDetail;

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

    public List<BillPaymentDetailType> getBillPaymentDetail() {
        return billPaymentDetail;
    }

    public void setBillPaymentDetail(List<BillPaymentDetailType> billPaymentDetail) {
        this.billPaymentDetail = billPaymentDetail;
    }
}