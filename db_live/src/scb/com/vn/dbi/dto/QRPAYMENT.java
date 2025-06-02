/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import scb.com.vn.common.model.qrpayment.QRPaymentRequest;
import scb.com.vn.common.model.qrpayment.QRPaymentResponse;

/**
 *
 * @author lydty
 */
public class QRPAYMENT  implements java.io.Serializable{
    QRPaymentRequest request;
    QRPaymentResponse response;
    String custno;
    String mobileno;

    public String getCustno() {
        return custno;
    }

    public void setCustno(String custno) {
        this.custno = custno;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
    
    

    public QRPaymentRequest getRequest() {
        return request;
    }

    public void setRequest(QRPaymentRequest request) {
        this.request = request;
    }

    public QRPaymentResponse getResponse() {
        return response;
    }

    public void setResponse(QRPaymentResponse response) {
        this.response = response;
    }

    
    
}
