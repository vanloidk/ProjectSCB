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
public class PayBillVNPT_in implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private PayBill_in payBill_in;
    
    public PayBillVNPT_in(String transId, String customerCode, String amount
            , String information, String channel) {
        this.payBill_in = new PayBill_in(transId, customerCode, amount, information, channel);
    }

    public PayBill_in getPayBill_in() {
        return payBill_in;
    }   
}