/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.qrpayment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.payment.*;

/**
 *
 * @author lydty
 */
@XmlRootElement(name ="QRPaymentRequest")
public class QRPaymentRequest  implements java.io.Serializable {
    String qrCodeValue;
    long amountOrder; 
    long amountPayment; //payment
    String chanelid; //MOBILE
    String accountno;
    String accounttype; //02: TK; 01: The noi dia ;04: The debit; 14: The Credit
    String promotionCode;
    String partnerid;
    String requestId;
    String typeRequest; //CHECK/PAYMENT
    int QRType;
    String note;
    String orderNo;
    
    @XmlElement(name = "orderNo", nillable = true)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    @XmlElement(name = "note", nillable = true)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
    
    @XmlElement(name = "QRType", nillable = true)
    public int getQRType() {
        return QRType;
    }

    public void setQRType(int QRType) {
        this.QRType = QRType;
    }
    
    @XmlElement(name = "amountPayment", nillable = true)
    public long getAmountPayment() {
        return amountPayment;
    }

    public void setAmountPayment(long amountPayment) {
        this.amountPayment = amountPayment;
    }

    @XmlElement(name = "qrCodeValue", nillable = true)
    public String getQrCodeValue() {
        return qrCodeValue;
    }

    public void setQrCodeValue(String qrCodeValue) {
        this.qrCodeValue = qrCodeValue;
    }
    @XmlElement(name = "amountOrder", nillable = true)
    public long getAmountOrder() {
        return amountOrder;
    }

    public void setAmountOrder(long amountOrder) {
        this.amountOrder = amountOrder;
    }
    @XmlElement(name = "chanelid", nillable = true)
    public String getChanelid() {
        return chanelid;
    }

    public void setChanelid(String chanelid) {
        this.chanelid = chanelid;
    }
    @XmlElement(name = "accountno", nillable = true)
    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }
    @XmlElement(name = "accounttype", nillable = true)
    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }
    @XmlElement(name = "promotionCode", nillable = true)
    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
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
    @XmlElement(name = "typeRequest", nillable = true)
    public String getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    
}
