/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.sms;

/**
 *
 * @author minhndb
 */
public class SmsDetail implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String idRef;
    private String phoneNumber;
    private String message;
    private String messageEncypt;
    private String time;
    private String idUserMaker;
    private String idChannelUserMaker;
    private String insDateMaker;
    private String branchCode;
    private String idUserChecker;
    private String idChannelUserChecker;
    private String status;
    private String messageStatus;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdRef() {
        return idRef;
    }

    public void setIdRef(String idRef) {
        this.idRef = idRef;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIdUserMaker() {
        return idUserMaker;
    }

    public void setIdUserMaker(String idUserMaker) {
        this.idUserMaker = idUserMaker;
    }

    public String getIdChannelUserMaker() {
        return idChannelUserMaker;
    }

    public void setIdChannelUserMaker(String idChannelUserMaker) {
        this.idChannelUserMaker = idChannelUserMaker;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getIdUserChecker() {
        return idUserChecker;
    }

    public void setIdUserChecker(String idUserChecker) {
        this.idUserChecker = idUserChecker;
    }

    public String getIdChannelUserChecker() {
        return idChannelUserChecker;
    }

    public void setIdChannelUserChecker(String idChannelUserChecker) {
        this.idChannelUserChecker = idChannelUserChecker;
    }

    public String getInsDateMaker() {
        return insDateMaker;
    }

    public void setInsDateMaker(String insDateMaker) {
        this.insDateMaker = insDateMaker;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessageEncypt() {
        return messageEncypt;
    }

    public void setMessageEncypt(String messageEncypt) {
        this.messageEncypt = messageEncypt;
    }
}
