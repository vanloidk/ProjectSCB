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
public class GetOrderResponse extends Response{
    GetOrderDataResponse responseData;

    public GetOrderDataResponse getResponseData() {
        return responseData;
    }

    public void setResponseData(GetOrderDataResponse responseData) {
        this.responseData = responseData;
    }
    
    
    
    
}
