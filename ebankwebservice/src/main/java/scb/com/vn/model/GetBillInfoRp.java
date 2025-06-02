/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.payment.Airline;
import scb.com.vn.common.model.payment.ThongTinPhieuThu;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "GetBillInfoRp")
public class GetBillInfoRp extends MobileResponse {

    private String CustomerCode;
    private String customername;
    private String address;
    private String BillAmt;
    private String TxnFee;
    private String TxnFeeNote;
    private String PaymentRule;
    private String PaymentRange;
    private String IsPrepaid;
    private String expireDate;
    private BillInfo[] bills;
    private Airline airline;
    private ThongTinPhieuThu phieuthu;
    private String Options;
    private String BillDetails;
    private String addinfo;
    private String transId;
    private String transResponseId;    
    /**
     * @return the CustomerCode
     */
    @XmlElement(name = "CustomerCode", nillable = true)
    public String getCustomerCode() {
        return CustomerCode;
    }

    /**
     * @param CustomerCode the CustomerCode to set
     */
    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    /**
     * @return the customername
     */
    @XmlElement(name = "customername", nillable = true)
    public String getCustomername() {
        return customername;
    }

    /**
     * @param customername the customername to set
     */
    public void setCustomername(String customername) {
        this.customername = customername;
    }

    /**
     * @return the address
     */
    @XmlElement(name = "address", nillable = true)
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the BillAmt
     */
    @XmlElement(name = "BillAmt", nillable = true)
    public String getBillAmt() {
        return BillAmt;
    }

    /**
     * @param BillAmt the BillAmt to set
     */
    public void setBillAmt(String BillAmt) {
        this.BillAmt = BillAmt;
    }

    /**
     * @return the TxnFee
     */
    @XmlElement(name = "TxnFee", nillable = true)
    public String getTxnFee() {
        return TxnFee;
    }

    /**
     * @param TxnFee the TxnFee to set
     */
    public void setTxnFee(String TxnFee) {
        this.TxnFee = TxnFee;
    }

    /**
     * @return the TxnFeeNote
     */
    @XmlElement(name = "TxnFeeNote", nillable = true)
    public String getTxnFeeNote() {
        return TxnFeeNote;
    }

    /**
     * @param TxnFeeNote the TxnFeeNote to set
     */
    public void setTxnFeeNote(String TxnFeeNote) {
        this.TxnFeeNote = TxnFeeNote;
    }

    /**
     * @return the PaymentRule
     */
    @XmlElement(name = "PaymentRule", nillable = true)
    public String getPaymentRule() {
        return PaymentRule;
    }

    /**
     * @param PaymentRule the PaymentRule to set
     */
    public void setPaymentRule(String PaymentRule) {
        this.PaymentRule = PaymentRule;
    }

    /**
     * @return the PaymentRange
     */
    @XmlElement(name = "PaymentRange", nillable = true)
    public String getPaymentRange() {
        return PaymentRange;
    }

    /**
     * @param PaymentRange the PaymentRange to set
     */
    public void setPaymentRange(String PaymentRange) {
        this.PaymentRange = PaymentRange;
    }

    /**
     * @return the IsPrepaid
     */
    @XmlElement(name = "IsPrepaid", nillable = true)
    public String getIsPrepaid() {
        return IsPrepaid;
    }

    /**
     * @param IsPrepaid the IsPrepaid to set
     */
    public void setIsPrepaid(String IsPrepaid) {
        this.IsPrepaid = IsPrepaid;
    }

    /**
     * @return the bills
     */
    @XmlElement(name = "BillDetail")
    @XmlElementWrapper(name = "Bills", nillable = true)
    public BillInfo[] getBills() {
        return bills;
    }

    /**
     * @param bills the bills to set
     */
    public void setBills(BillInfo[] bills) {
        this.bills = bills;
    }

    /**
     * @return the expireDate
     */
    @XmlElement(name = "expireDate", nillable = true)
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate the expireDate to set
     */
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * @return the airline
     */
    @XmlElement(name = "Airline", nillable = true)
    public Airline getAirline() {
        return airline;
    }

    /**
     * @param airline the airline to set
     */
    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    /**
     * @return the phieuthu
     */
    public ThongTinPhieuThu getPhieuthu() {
        return phieuthu;
    }

    /**
     * @param phieuthu the phieuthu to set
     */
    public void setPhieuthu(ThongTinPhieuThu phieuthu) {
        this.phieuthu = phieuthu;
    }

    @XmlElement(name = "Options", nillable = true)
    public String getOptions() {
        return Options;
    }

    public void setOptions(String Options) {
        this.Options = Options;
    }

    @XmlElement(name = "BillDetails")
    public String getBillDetails() {
        return BillDetails;
    }

    public void setBillDetails(String BillDetails) {
        this.BillDetails = BillDetails;
    }

    @XmlElement(name = "AddInfo", nillable = true)
    public String getAddinfo() {
        return addinfo;
    }

    public void setAddinfo(String addinfo) {
        this.addinfo = addinfo;
    }
    
    @XmlElement(name = "TransRequestId", nillable = true)
    public String getTransId() {
        return transId;
    }
    public void setTransId(String transId) {
        this.transId = transId;
    }
    
    public String getTransResponseId() {
        return transResponseId;
    }
    public void setTransResponseId(String transResponseId) {
        this.transResponseId = transResponseId;
    }
}
