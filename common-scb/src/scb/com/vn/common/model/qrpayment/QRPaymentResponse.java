/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.qrpayment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lydty
 */
@XmlRootElement(name ="QRPaymentResponse")
public class QRPaymentResponse implements java.io.Serializable {
    String partnerid;
    String requestId;
    String status;
    String description;
    String refcore;
    String typeRequest; //CHECK/PAYMENT
    long amountOrder;
    long amountPayment;
    String expiredDate;
    String orderNo;
    String isRevert;
    String requestDate;
    String responseDate;
    
    
    @XmlElement(name = "status", nillable = true)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
     @XmlElement(name = "requestDate", nillable = true)
    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
   @XmlElement(name = "responseDate", nillable = true)
    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    @XmlElement(name = "isRevert", nillable = true)
    public String getIsRevert() {
        return isRevert;
    }

    public void setIsRevert(String isRevert) {
        this.isRevert = isRevert;
    }
    @XmlElement(name = "expiredDate", nillable = true)
    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }
    @XmlElement(name = "orderNo", nillable = true)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @XmlElement(name = "partnerid", nillable = true)
    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }
    @XmlElement(name = "requestId", nillable = true)
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

   
    @XmlElement(name = "description", nillable = true)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @XmlElement(name = "refcore", nillable = true)
    public String getRefcore() {
        return refcore;
    }

    public void setRefcore(String refcore) {
        this.refcore = refcore;
    }
    @XmlElement(name = "typeRequest", nillable = true)
    public String getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }
    @XmlElement(name = "amountOrder", nillable = true)
    public long getAmountOrder() {
        return amountOrder;
    }

    public void setAmountOrder(long amountOrder) {
        this.amountOrder = amountOrder;
    }
    @XmlElement(name = "amountPayment", nillable = true)
    public long getAmountPayment() {
        return amountPayment;
    }
    public void setAmountPayment(long amountPayment) {
        this.amountPayment = amountPayment;
    }

}
