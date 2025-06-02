/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer.napas;

import java.io.Serializable;
import java.math.BigDecimal;
import scb.com.vn.common.model.transfer.ReceivingInfo;
import scb.com.vn.common.model.transfer.SenderInfo;

/**
 *
 * @author tamnt12
 */
public class TransferMoney247EbankReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountPartner;
    private BigDecimal amount;
    private String ccy;
    private String description;
    private String msgId;
    private String providerId;
    private String transId;
    private String toNumber;
    private String typeToNumber;
    private String benId;
    private String id;
    private String idMaster;
    private String bankCode;
    private String branhName;
    private String typeTransfer;
    private String rate;
    private String amt_exchange;
    private String ccy_exchange;
    private String personID;
    private String lastName;
    private String firstName;
    private String passNo;
    private String birthDate;
    private String address;
    private String national;
    private String custType;

    private ReceivingInfo receivingInfo;
    private SenderInfo senderInfo;

    public String getAccountPartner() {
        return accountPartner;
    }

    public void setAccountPartner(String accountPartner) {
        this.accountPartner = accountPartner;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public SenderInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(SenderInfo senderInfo) {
        this.senderInfo = senderInfo;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    public String getTypeToNumber() {
        return typeToNumber;
    }

    public void setTypeToNumber(String typeToNumber) {
        this.typeToNumber = typeToNumber;
    }

    public String getBenId() {
        return benId;
    }

    public void setBenId(String benId) {
        this.benId = benId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isTransReq() {
        return "TRN".equals(msgId);
    }

    public ReceivingInfo getReceivingInfo() {
        return receivingInfo;
    }

    public void setReceivingInfo(ReceivingInfo receivingInfo) {
        this.receivingInfo = receivingInfo;
    }

    public String getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(String idMaster) {
        this.idMaster = idMaster;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranhName() {
        return branhName;
    }

    public void setBranhName(String branhName) {
        this.branhName = branhName;
    }

    public String getTypeTransfer() {
        return typeTransfer;
    }

    public void setTypeTransfer(String typeTransfer) {
        this.typeTransfer = typeTransfer;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmt_exchange() {
        return amt_exchange;
    }

    public void setAmt_exchange(String amt_exchange) {
        this.amt_exchange = amt_exchange;
    }

    public String getCcy_exchange() {
        return ccy_exchange;
    }

    public void setCcy_exchange(String ccy_exchange) {
        this.ccy_exchange = ccy_exchange;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassNo() {
        return passNo;
    }

    public void setPassNo(String passNo) {
        this.passNo = passNo;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

}
