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
public class SiTrffromsiExtend implements Serializable {

    private static final long serialVersionUID = 1L;

    String id;
    String siId;
    String approved;
    String idUserMarker;
    String idUserChecker;
    String branchCode;
    String type;
    String passNo;
    String birthDate;
    String address;
    String national;
    String fullName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiId() {
        return siId;
    }

    public void setSiId(String siId) {
        this.siId = siId;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
