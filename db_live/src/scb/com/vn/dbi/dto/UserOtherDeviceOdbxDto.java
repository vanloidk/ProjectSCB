/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.io.Serializable;

/**
 *
 * @author loinv3
 */
public class UserOtherDeviceOdbxDto implements Serializable {

    private static final long serialVersionUID = 1L;
    String id;
    String soCif;
    String userName;
    String accessOtherDevice;
    String approved;
    String idUserMarker;
    String idUserChecker;
    String branchCode;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoCif() {
        return soCif;
    }

    public void setSoCif(String soCif) {
        this.soCif = soCif;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccessOtherDevice() {
        return accessOtherDevice;
    }

    public void setAccessOtherDevice(String accessOtherDevice) {
        this.accessOtherDevice = accessOtherDevice;
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
}
