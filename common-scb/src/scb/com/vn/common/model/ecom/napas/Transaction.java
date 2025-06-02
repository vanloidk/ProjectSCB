/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.ecom.napas;

/**
 *
 * @author tamnt12
 */
public class Transaction {
    private String trace;
    private String info;
    private String date;
    private String amount;
    private String ccy;
    private String serviceType;
    
    private String otp; // only use in VerifyOTP


    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
    
    public boolean inValidTrans(){
        return this.date == null || this.date.isEmpty() ||
                this.amount == null || this.amount.isEmpty() ||
                this.ccy == null || this.ccy.isEmpty();
    }
    
    public boolean inValidOtp(){
        return this.otp == null || this.otp.isEmpty();
    }
    
}
