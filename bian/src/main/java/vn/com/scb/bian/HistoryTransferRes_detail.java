/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian;

import java.util.List;

/**
 *
 * @author lydty
 */
public class HistoryTransferRes_detail {
    transactionInfo  transactionInfo;
    List<HistoryTransferRes_fundTransferInfo> fundTransferInfo;

    public transactionInfo getTransactionInfo() {
        return transactionInfo;
    }

    public void setTransactionInfo(transactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
    }

    public List<HistoryTransferRes_fundTransferInfo> getFundTransferInfo() {
        return fundTransferInfo;
    }

    public void setFundTransferInfo(List<HistoryTransferRes_fundTransferInfo> fundTransferInfo) {
        this.fundTransferInfo = fundTransferInfo;
    }

   

    
    
    
}
