/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.ecom.napas;

import java.util.List;

/**
 *
 * @author tamnt12
 */
public class DataVerifyOtpRequest {
    private Transaction transaction;
    private List<OtherInfo> otherInfo;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<OtherInfo> getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(List<OtherInfo> otherInfo) {
        this.otherInfo = otherInfo;
    }
    
    public boolean inValid(){
        return this.transaction == null && this.transaction.inValidOtp();
    }
    
}
