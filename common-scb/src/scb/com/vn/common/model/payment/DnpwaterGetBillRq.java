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
public class DnpwaterGetBillRq {
    List<DnpwaterSearchRq> filter;
    String signature;

    public List<DnpwaterSearchRq> getFilter() {
        return filter;
    }

    public void setFilter(List<DnpwaterSearchRq> filter) {
        this.filter = filter;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) 
    {
        this.signature = signature;
    }
}
