/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Kimncm
 */
public class BillInfo {

    private String Month;
    private BigDecimal MoneyAmount;
    private String PaymentFee;
    private String BillId;
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
     * @return the Month
     */
    @XmlElement(name = "Month", nillable = true)
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
    @XmlElement(name = "MoneyAmount", nillable = true)
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
    @XmlElement(name = "PaymentFee", nillable = true)
    public String getPaymentFee() {
        return PaymentFee;
    }

    /**
     * @param PaymentFee the PaymentFee to set
     */
    public void setPaymentFee(String PaymentFee) {
        this.PaymentFee = PaymentFee;
    }

    /**
     * @return the BillId
     */
    @XmlElement(name = "BillId", nillable = true)
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
     * @return the CustomerId
     */
    @XmlElement(name = "CustomerCode", nillable = true)
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
