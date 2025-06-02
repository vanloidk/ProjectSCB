/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.bhbl;

/**
 *
 * @author loinv3
 */
public class BHBLSingnaturePaymentDto {

    private String InvoiceCode;
    private long Amount;
    private String PaymentCode;
    private String Status;

    public String getInvoiceCode() {
        return InvoiceCode;
    }

    public void setInvoiceCode(String InvoiceCode) {
        this.InvoiceCode = InvoiceCode;
    }

    public long getAmount() {
        return Amount;
    }

    public void setAmount(long Amount) {
        this.Amount = Amount;
    }

    public String getPaymentCode() {
        return PaymentCode;
    }

    public void setPaymentCode(String PaymentCode) {
        this.PaymentCode = PaymentCode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

}
