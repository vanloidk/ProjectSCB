/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author baotbq
 */
public class TransfersRemittance implements Serializable {

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
    private String passNo;
    private String birthDay;
    private String address;
    private String national;
    private String tnxTransId;
    private String status;
    private String firstName;
    private String lastName;

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

    public String getPassNo() {
        return passNo;
    }

    public void setPassNo(String passNo) {
        this.passNo = passNo;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
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

    public String getTnxTransId() {
        return tnxTransId;
    }

    public void setTnxTransId(String tnxTransId) {
        this.tnxTransId = tnxTransId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
