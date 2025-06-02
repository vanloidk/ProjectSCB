/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian.model.evnhn;

import java.util.List;
import vn.com.scb.bian.BillPaymentDetailType;

/**
 *
 * @author minhndb
 */
public class BillPaymentDetailsType {
    private List<BillPaymentDetailType> billPaymentDetail;

    public List<BillPaymentDetailType> getBillPaymentDetail() {
        return billPaymentDetail;
    }

    public void setBillPaymentDetail(List<BillPaymentDetailType> billPaymentDetail) {
        this.billPaymentDetail = billPaymentDetail;
    }
}