/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.model.evnhn;

import java.io.Serializable;

/**
 *
 * @author minhndb
 */
public class PayBillEVNHN_out implements Serializable {
    private static final long serialVersionUId = 1L;
    
    private PayBill_out payBill_out;

    public PayBill_out getPayBill_out() {
        return payBill_out;
    }
    
    public String getTransactionReturn() {
        try {
            return payBill_out.getTransactionInfo().getTransactionReturn().toString();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getTransactionReturnMsg() {
        try {
            return payBill_out.getTransactionInfo().getTransactionReturnMsg();
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getBillTransCode() {
        try {
            return payBill_out.getBillPaymentInfo().getBillTransCode();
        } catch (Exception e) {
            return "";
        }
    }
}