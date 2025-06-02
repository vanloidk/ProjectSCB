/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author minhndb
 */
public class OrgBillPaid implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String customerNo;
    private String customerCode;
    private String serviceCode;
    private String paidPeriod;
    private Date paidDate;
    private Date updDate;
    private String providerCode;
    private BigDecimal amount;

    /**
     *
     * @return
     */
    public String getCustomerNo() {
        return customerNo;
    }

    /**
     *
     * @return
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     *
     * @return
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     *
     * @return
     */
    public String getPaidPeriod() {
        return paidPeriod;
    }

    /**
     *
     * @return
     */
    public Date getPaidDate() {
        return paidDate;
    }

    /**
     *
     * @return
     */
    public Date getUpdDate() {
        return updDate;
    }

    /**
     *
     * @return
     */
    public String getProviderCode() {
        return providerCode;
    }

    /**
     *
     * @return
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     *
     * @param customerNo
     */
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    /**
     *
     * @param customerCode
     */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     *
     * @param serviceCode
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     *
     * @param paidPeriod
     */
    public void setPaidPeriod(String paidPeriod) {
        this.paidPeriod = paidPeriod;
    }

    /**
     *
     * @param paidDate
     */
    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    /**
     *
     * @param updDate
     */
    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    /**
     *
     * @param providerCode
     */
    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("customerNo = [").append(customerNo).append("], ");
        str.append("customerCode = [").append(customerCode).append("], ");
        str.append("serviceCode = [").append(serviceCode).append("], ");
        str.append("paidPeriod = [").append(paidPeriod).append("], ");
        str.append("paidDate = [").append(paidDate).append("], ");
        str.append("updDate = [").append(updDate).append("], ");
        str.append("providerCode = [").append(providerCode).append("], ");
        str.append("amount = [").append(amount).append("]");

        return str.toString();
    }

}
