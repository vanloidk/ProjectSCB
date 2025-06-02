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
public class SelectBillEVNHNInfo_in implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private GetBillInfo_in getBillInfo_in;

    public SelectBillEVNHNInfo_in() {}

    public SelectBillEVNHNInfo_in(String customerCode, String channel) {
        this.getBillInfo_in = new GetBillInfo_in(customerCode, channel);
    }

    public GetBillInfo_in getGetBillInfo_in() {
        return getBillInfo_in;
    }
}