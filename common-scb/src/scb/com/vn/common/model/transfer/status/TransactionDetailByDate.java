/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer.status;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "TRN_DETAIL")
/*dang test tren 73.20
 * doi tac: WU
 */
public class TransactionDetailByDate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String typeTransfer; // 01: trong he thong, 02: citad, 03: 247
    private BigDecimal amount; // so tien
    private String ccy; // don vi
    private String bankCode; // ma ngan hang
    private String transId; //ma gd cua doi tac
    private String txnDetailId;
    private String bankTransId; // ma gd cua scb
    private String customerAccount;// stk khach hang
    private String customerName; // ten khach hang
    private Date createDate; //Ngay khoi tao gd
    private String content; // noi dung giao dich
    private String status; // trang thai cua gd
    private String description; // mo ta ma loi

    @XmlElement(name = "TYPETRANSFER")
    public String getTypeTransfer() {
        return typeTransfer;
    }

    public void setTypeTransfer(String typeTransfer) {
        this.typeTransfer = typeTransfer;
    }

    @XmlElement(name = "AMOUNT")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @XmlElement(name = "CCY")
    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    @XmlElement(name = "BANKCODE")
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @XmlElement(name = "TRANSID")
    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    @XmlElement(name = "TXNDETAILID")
    public String getTxnDetailId() {
        return txnDetailId;
    }

    public void setTxnDetailId(String txnDetailId) {
        this.txnDetailId = txnDetailId;
    }

    @XmlElement(name = "BANKTRANSID")
    public String getBankTransId() {
        return bankTransId;
    }

    public void setBankTransId(String bankTransId) {
        this.bankTransId = bankTransId;
    }

    @XmlElement(name = "CUSTOMERACCOUNT")
    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount;
    }

    @XmlElement(name = "CUSTOMERNAME")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @XmlElement(name = "CREATEDATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @XmlElement(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlElement(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }        
}
