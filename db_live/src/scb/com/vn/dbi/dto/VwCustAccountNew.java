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
 * @author minhndb
 */
public class VwCustAccountNew implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String ccy;
    private String custNo;
    private String fullName;
    private BigDecimal acyAvlBal;
    private String accountClass;
    private String address;
    private Character recordStat;
    private String acDesc;
    
    /* check lai sau */
    private String custAcNo;
    private String idcard;

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getAcyAvlBal() {
        return acyAvlBal;
    }

    public void setAcyAvlBal(BigDecimal acyAvlBal) {
        this.acyAvlBal = acyAvlBal;
    }

    public String getAccountClass() {
        return accountClass;
    }

    public void setAccountClass(String accountClass) {
        this.accountClass = accountClass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Character getRecordStat() {
        return recordStat;
    }

    public void setRecordStat(Character recordStat) {
        this.recordStat = recordStat;
    }

    public String getAcDesc() {
        return acDesc;
    }

    public void setAcDesc(String acDesc) {
        this.acDesc = acDesc;
    }

    public String getCustAcNo() {
        return custAcNo;
    }

    public void setCustAcNo(String custAcNo) {
        this.custAcNo = custAcNo;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }    
}