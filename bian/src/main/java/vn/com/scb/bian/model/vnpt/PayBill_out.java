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
public class PayBill_out implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private vn.com.scb.bian.TransactionInfoType transactionInfo;
    private vn.com.scb.bian.BillPaymentInfoType billPaymentInfo;

    public TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }

    public BillPaymentInfoType getBillPaymentInfo() {
        return billPaymentInfo;
    }
    
    
}
