/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

import java.util.List;

/**
 *
 * @author lydty
 */
public class BidiwacoPayment {
    private String total_rows;
    private String total_amount;
    private String block_trans;

    public String getBlock_trans() {
        return block_trans;
    }

    public void setBlock_trans(String block_trans) {
        this.block_trans = block_trans;
    }
    
    
    private List<BidiwacoPaymentDetail> payment_data;

    public String getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(String total_rows) {
        this.total_rows = total_rows;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public List<BidiwacoPaymentDetail> getPayment_data() {
        return payment_data;
    }

    public void setPayment_data(List<BidiwacoPaymentDetail> payment_data) {
        this.payment_data = payment_data;
    }
    
    
}
