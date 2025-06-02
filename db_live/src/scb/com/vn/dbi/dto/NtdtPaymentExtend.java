/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.io.Serializable;

/**
 *
 * @author baotbq
 */
public class NtdtPaymentExtend implements Serializable{

    private static final long serialVersionUID = 1L;

    String id;
    String ntdtPaymentId;
    String approved;
    String idUserMarker;
    String idUserChecker;
    String branchCode;
    String type;
    String refCore;
    String accountNo;
    String thoigianGD;
    String verifyDate;
    String sotien;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNtdtPaymentId() {
        return ntdtPaymentId;
    }

    public void setNtdtPaymentId(String ntdtPaymentId) {
        this.ntdtPaymentId = ntdtPaymentId;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getIdUserMarker() {
        return idUserMarker;
    }

    public void setIdUserMarker(String idUserMarker) {
        this.idUserMarker = idUserMarker;
    }

    public String getIdUserChecker() {
        return idUserChecker;
    }

    public void setIdUserChecker(String idUserChecker) {
        this.idUserChecker = idUserChecker;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRefCore() {
        return refCore;
    }

    public void setRefCore(String refCore) {
        this.refCore = refCore;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getThoigianGD() {
        return thoigianGD;
    }

    public void setThoigianGD(String thoigianGD) {
        this.thoigianGD = thoigianGD;
    }

    public String getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(String verifyDate) {
        this.verifyDate = verifyDate;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }

}
