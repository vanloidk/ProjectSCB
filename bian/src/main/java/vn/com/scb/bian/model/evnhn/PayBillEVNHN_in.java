/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.model.evnhn;

import java.io.Serializable;
import java.util.List;
import vn.com.scb.bian.BillPaymentDetailType;

/**
 *
 * @author minhndb
 */
public class PayBillEVNHN_in implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private PayBill_in payBill_in;

    public PayBillEVNHN_in() {
    }

    public PayBillEVNHN_in(String billTransCode, String clientCode, String idBillPaid
            , List<BillPaymentDetailType> billPaymentDetail) {
        this.payBill_in = new PayBill_in(billTransCode, clientCode, idBillPaid, billPaymentDetail);
    }

    public PayBill_in getPayBill_in() {
        return payBill_in;
    }
}