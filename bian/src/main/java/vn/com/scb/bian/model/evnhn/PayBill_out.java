/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.model.evnhn;

import java.io.Serializable;
import vn.com.scb.bian.BillPaymentInfoType;
import vn.com.scb.bian.TransactionInfoType;

/**
 *
 * @author minhndb
 */
public class PayBill_out implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private TransactionInfoType transactionInfo;
    private BillPaymentInfoType billPaymentInfo;

    public TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }

    public BillPaymentInfoType getBillPaymentInfo() {
        return billPaymentInfo;
    }
}