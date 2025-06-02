/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import java.math.BigDecimal;

/**
 *
 * @author KimNCM
 */
public class BillDetail {

    private String BillId;
    private String ServiceId;
    private String ProviderId;
    private String Month;
    private BigDecimal MoneyAmount;
    private BigDecimal PaymentFee;
    private String CustomerName;
    private String CustomerId;
    private String billNo;

    /**
     *
     * @return
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     *
     * @param billNo
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * @return the BillId
     */
    public String getBillId() {
        return BillId;
    }

    /**
     * @param BillId the BillId to set
     */
    public void setBillId(String BillId) {
        this.BillId = BillId;
    }

    /**
     * @return the ServiceId
     */
    public String getServiceId() {
        return ServiceId;
    }

    /**
     * @param ServiceId the ServiceId to set
     */
    public void setServiceId(String ServiceId) {
        this.ServiceId = ServiceId;
    }

    /**
     * @return the ProviderId
     */
    public String getProviderId() {
        return ProviderId;
    }

    /**
     * @param ProviderId the ProviderId to set
     */
    public void setProviderId(String ProviderId) {
        this.ProviderId = ProviderId;
    }

    /**
     * @return the Month
     */
    public String getMonth() {
        return Month;
    }

    /**
     * @param Month the Month to set
     */
    public void setMonth(String Month) {
        this.Month = Month;
    }

    /**
     * @return the MoneyAmount
     */
    public BigDecimal getMoneyAmount() {
        return MoneyAmount;
    }

    /**
     * @param MoneyAmount the MoneyAmount to set
     */
    public void setMoneyAmount(BigDecimal MoneyAmount) {
        this.MoneyAmount = MoneyAmount;
    }

    /**
     * @return the PaymentFee
     */
    public BigDecimal getPaymentFee() {
        return PaymentFee;
    }

    /**
     * @param PaymentFee the PaymentFee to set
     */
    public void setPaymentFee(BigDecimal PaymentFee) {
        this.PaymentFee = PaymentFee;
    }

    /**
     * @return the CustomerName
     */
    public String getCustomerName() {
        return CustomerName;
    }

    /**
     * @param CustomerName the CustomerName to set
     */
    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    /**
     * @return the CustomerId
     */
    public String getCustomerId() {
        return CustomerId;
    }

    /**
     * @param CustomerId the CustomerId to set
     */
    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }
}
