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
public class SelectBillVNPTInfo_out implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private GetBillInfo_out getBillInfo_out;

    public GetBillInfo_out getGetBillInfo_out() {
        return getBillInfo_out;
    }
    
    public String getTransactionReturn() {
        try {
            return getBillInfo_out.getTransactionInfo().getTransactionReturn().toString();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String getTransactionInfo() {
        try {
            return getBillInfo_out.getTransactionInfo().toString();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String getTransactionReturnMsg() {
        try {
            return getBillInfo_out.getTransactionInfo().getTransactionReturnMsg();
        } catch (Exception e) {
            return null;
        }
    }
}
