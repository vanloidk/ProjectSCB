/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.iibpayment;

public class GetBillInfo_inType  {

    
    private TransactionInfoType transactionInfo;
    private BillPaymentInfoType billPaymentInfo;

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