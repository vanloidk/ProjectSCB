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
public class SelectBillVNPTInfo_in implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private GetBillInfo_in getBillInfo_in;

    public SelectBillVNPTInfo_in() {}

    public SelectBillVNPTInfo_in(String transId, String customerCode, String province, String channel) {
        this.getBillInfo_in = new GetBillInfo_in(transId, customerCode, province, channel);
    }

    public GetBillInfo_in getGetBillInfo_in() {
        return getBillInfo_in;
    }
}
