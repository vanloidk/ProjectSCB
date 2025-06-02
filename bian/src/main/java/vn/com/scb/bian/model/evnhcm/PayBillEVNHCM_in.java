/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.model.evnhcm;

import java.io.Serializable;
import java.util.List;
import vn.com.scb.bian.BillPaymentDetailType;
import vn.com.scb.bian.model.evnhn.PayBill_in;

/**
 *
 * @author minhndb
 */
public class PayBillEVNHCM_in implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private PayBill_in payBill_in;

    public PayBillEVNHCM_in() {
    }

    public PayBillEVNHCM_in(String billTransCode, String clientCode, String idBillPaid, String paymentMethod
            , List<BillPaymentDetailType> billPaymentDetail) {
        this.payBill_in = new PayBill_in(billTransCode, clientCode, idBillPaid, paymentMethod, billPaymentDetail);
    }

    public PayBill_in getPayBill_in() {
        return payBill_in;
    }
}
