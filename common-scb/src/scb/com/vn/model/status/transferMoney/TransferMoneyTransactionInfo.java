/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.status.transferMoney;

import java.io.Serializable;

/**
 *
 * @author minhndb
 */
public class TransferMoneyTransactionInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}