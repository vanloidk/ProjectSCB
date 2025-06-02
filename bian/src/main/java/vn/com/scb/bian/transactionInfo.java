/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian;

/**
 *
 * @author lydty
 */
public class transactionInfo {
     

     String transactionErrorCode;
     String transactionErrorMsg;
     String transactionReturnCode;
     String transactionReturnMsg;

    public String getTransactionErrorCode() {
        return transactionErrorCode;
    }

    public void setTransactionErrorCode(String transactionErrorCode) {
        this.transactionErrorCode = transactionErrorCode;
    }

    public String getTransactionErrorMsg() {
        return transactionErrorMsg;
    }

    public void setTransactionErrorMsg(String transactionErrorMsg) {
        this.transactionErrorMsg = transactionErrorMsg;
    }

    public String getTransactionReturnCode() {
        return transactionReturnCode;
    }

    public void setTransactionReturnCode(String transactionReturnCode) {
        this.transactionReturnCode = transactionReturnCode;
    }

    public String getTransactionReturnMsg() {
        return transactionReturnMsg;
    }

    public void setTransactionReturnMsg(String transactionReturnMsg) {
        this.transactionReturnMsg = transactionReturnMsg;
    }
     
     
     
}
