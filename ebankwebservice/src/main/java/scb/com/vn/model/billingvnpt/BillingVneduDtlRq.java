/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.billingvnpt;

public class BillingVneduDtlRq {
    private long Amount;
    private String Month;
    private String Info;

    public long getAmount() {
        return Amount;
    }
    
    public void setAmount(long Amount) {
        this.Amount = Amount;
    }
    
    public String getMonth() {
        return Month;
    }

    public void setMonth(String Month) {
        this.Month = Month;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String Info) {
        this.Info = Info;
    }
    
}
