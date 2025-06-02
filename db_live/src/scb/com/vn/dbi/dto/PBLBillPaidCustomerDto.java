/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author loinv3
 */
public class PBLBillPaidCustomerDto {

    /**
     * PBLBillPaidCustomerDtlsDto
     */
    public static class PBLBillPaidCustomerDtlsDto implements java.io.Serializable {

        private static final long serialVersionUID = -1L;
        private List<PBLBillPaidCustomerDtlForJobDto> billPaidCusts;

        public List<PBLBillPaidCustomerDtlForJobDto> getBillPaidCusts() {
            return billPaidCusts;
        }

        public void setBillPaidCusts(List<PBLBillPaidCustomerDtlForJobDto> billPaidCusts) {
            this.billPaidCusts = billPaidCusts;
        }

    }

    /**
     * PBLBillPaidCustomerDtlForJobDto
     */
    public static class PBLBillPaidCustomerDtlForJobDto implements java.io.Serializable {

        private static final long serialVersionUID = -1L;
        private Number billpaidcustId;
        private String partnerId;
        private String serviceTypeId;
        private String providerId;
        private String customerCode;
        private String periodPaid;
        private Date createdDate;
        private Date updatedDate;
        private BigDecimal totalAmount;
        private String cusNo;
        private String customer_name;

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }
        
        

        public PBLBillPaidCustomerDtlForJobDto() {
        }

        public String getPeriodPaid() {
            return periodPaid;
        }

        public void setPeriodPaid(String periodPaid) {
            this.periodPaid = periodPaid;
        }
        
       

        public Number getBillpaidcustId() {
            return billpaidcustId;
        }

        public void setBillpaidcustId(Number billpaidcustId) {
            this.billpaidcustId = billpaidcustId;
        }

        public String getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(String partnerId) {
            this.partnerId = partnerId;
        }

        public String getServiceTypeId() {
            return serviceTypeId;
        }

        public void setServiceTypeId(String serviceTypeId) {
            this.serviceTypeId = serviceTypeId;
        }

        public String getProviderId() {
            return providerId;
        }

        public void setProviderId(String providerId) {
            this.providerId = providerId;
        }

        public String getCustomerCode() {
            return customerCode;
        }

        public void setCustomerCode(String customerCode) {
            this.customerCode = customerCode;
        }

        public Date getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
        }

        public Date getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(Date updatedDate) {
            this.updatedDate = updatedDate;
        }

        public BigDecimal getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getCusNo() {
            return cusNo;
        }

        public void setCusNo(String cusNo) {
            this.cusNo = cusNo;
        }

    }

    public static class PBLBillPaidCusDto implements java.io.Serializable {

        private static final long serialVersionUID = -1L;
        private Number id;
        private String partnerId;
        private String serviceTypeId;
        private String providerId;
        private String customerCode;
        private Date createdDate;
        private Date updatedDate;
        private String cusNo;

        public PBLBillPaidCusDto() {
        }

        public Number getId() {
            return id;
        }

        public void setId(Number id) {
            this.id = id;
        }

        public String getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(String partnerId) {
            this.partnerId = partnerId;
        }

        public String getServiceTypeId() {
            return serviceTypeId;
        }

        public void setServiceTypeId(String serviceTypeId) {
            this.serviceTypeId = serviceTypeId;
        }

        public String getProviderId() {
            return providerId;
        }

        public void setProviderId(String providerId) {
            this.providerId = providerId;
        }

        public String getCustomerCode() {
            return customerCode;
        }

        public void setCustomerCode(String customerCode) {
            this.customerCode = customerCode;
        }

        public Date getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
        }

        public Date getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(Date updatedDate) {
            this.updatedDate = updatedDate;
        }

        public String getCusNo() {
            return cusNo;
        }

        public void setCusNo(String cusNo) {
            this.cusNo = cusNo;
        }
    }
}
