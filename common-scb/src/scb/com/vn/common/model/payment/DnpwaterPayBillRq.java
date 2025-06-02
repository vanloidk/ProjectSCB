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
public class DnpwaterPayBillRq {
    List<DnpwaterBillPayDetail> billing_list;
    String signature;

    public List<DnpwaterBillPayDetail> getBilling_list() {
        return billing_list;
    }

    public void setBilling_list(List<DnpwaterBillPayDetail> billing_list) {
        this.billing_list = billing_list;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
}
