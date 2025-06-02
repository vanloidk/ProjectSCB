/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.payment;

/**
 *
 * @author lydty
 */
public class BidiwacoPaymentDetail {
    private String payment_id;
    private String payment_date;
    private String payment_amount;
    private String payment_comment;
    private String invoice_id;
    private String invoice_customer;
    private String invoice_date;
    private String invoice_no;

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(String payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getPayment_comment() {
        return payment_comment;
    }

    public void setPayment_comment(String payment_comment) {
        this.payment_comment = payment_comment;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getInvoice_customer() {
        return invoice_customer;
    }

    public void setInvoice_customer(String invoice_customer) {
        this.invoice_customer = invoice_customer;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }
    
}
