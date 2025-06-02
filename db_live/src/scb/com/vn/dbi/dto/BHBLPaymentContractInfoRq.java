/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author loinv3
 */
@XmlRootElement(name = "PaymentStatusRq")
public class BHBLPaymentContractInfoRq implements Serializable {

    private static final long serialVersionUID = -1L;

    private String Signature;
    private String InvoiceCode;
    private long Amount;
    private int PaymentCode;
    private String Status;

    @XmlElement(name = "InvoiceCode", nillable = true)
    public String getInvoiceCode() {
        return InvoiceCode;
    }

    public void setInvoiceCode(String InvoiceCode) {
        this.InvoiceCode = InvoiceCode;
    }

    @XmlElement(name = "PaymentCode", nillable = true)
    public int getPaymentCode() {
        return PaymentCode;
    }

    public void setPaymentCode(int PaymentCode) {
        this.PaymentCode = PaymentCode;
    }

    @XmlElement(name = "Signature", nillable = false)
    public String getSignature() {
        return Signature;
    }

    public void setSignature(String Signature) {
        this.Signature = Signature;
    }

    @XmlElement(name = "Amount", nillable = true)
    public long getAmount() {
        return Amount;
    }

    public void setAmount(long Amount) {
        this.Amount = Amount;
    }

    @XmlElement(name = "Status", nillable = true)
    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
