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
public class DnpwaterBillPayDetail {
    
    int billing_id;
    String billing_code;
    String customer_code;
    String customer_name;
    String customer_address;
    String period;
    int amount_total;
    String state;
    String company_code;
    String pay_date_time;
    String local_date_time;
    String channel;
    String account_no;
    String bank_name;
    String trace;

    public int getBilling_id() {
        return billing_id;
    }

    public void setBilling_id(int billing_id) {
        this.billing_id = billing_id;
    }

    public int getAmount_total() {
        return amount_total;
    }

    public void setAmount_total(int amount_total) {
        this.amount_total = amount_total;
    }

    

    public String getBilling_code() {
        return billing_code;
    }

    public void setBilling_code(String billing_code) {
        this.billing_code = billing_code;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getPay_date_time() {
        return pay_date_time;
    }

    public void setPay_date_time(String pay_date_time) {
        this.pay_date_time = pay_date_time;
    }

    public String getLocal_date_time() {
        return local_date_time;
    }

    public void setLocal_date_time(String local_date_time) {
        this.local_date_time = local_date_time;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

   
    
    
}
