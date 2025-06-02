/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.payooqr;


/**
 *
 * @author lydty
 */
public class NotifyTransactionResponse extends Response{
    
    NotifyTransactionDataResponse responseData;

    public NotifyTransactionDataResponse getResponseData() {
        return responseData;
    }

    public void setResponseData(NotifyTransactionDataResponse responseData) {
        this.responseData = responseData;
    }
    
    
    
    
}
