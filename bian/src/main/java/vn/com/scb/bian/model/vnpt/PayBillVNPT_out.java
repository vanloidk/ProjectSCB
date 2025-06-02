/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.model.vnpt;

/**
 *
 * @author minhndb
 */
public class PayBillVNPT_out implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private PayBill_out payBill_out;

    public PayBill_out getPayBill_out() {
        return payBill_out;
    }
    
    public String getTransactionReturn() {
        try {
            return payBill_out.getTransactionInfo().getTransactionReturn().toString();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String getTransactionReturnMsg() {
        try {
            return payBill_out.getTransactionInfo().getTransactionReturnMsg();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String getBillCustomerName() {
        try {
            return payBill_out.getBillPaymentInfo().getBillCustomerName();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String getBillCustomerAddress() {
        try {
            return payBill_out.getBillPaymentInfo().getBillCustomerAddress();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String getBillPayAmount() {
        try {
            return payBill_out.getBillPaymentInfo().getBillPayAmount();
        } catch (Exception e) {
            return null;
        }
    }
}
