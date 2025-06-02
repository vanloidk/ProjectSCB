/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.kt;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author minhndb
 */
public class KhoaTheInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String khoaTheId;
    private String requester;
    private String phoneNumber;
    private String reqMessage;
    private Date reqTime;
    private String resMessage;
    private Date execTime;
    private String smsId;
    private String status = "2";

    public String getKhoaTheId() {
        return khoaTheId;
    }

    public void setKhoaTheId(String khoaTheId) {
        this.khoaTheId = khoaTheId;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReqMessage() {
        return reqMessage;
    }

    public void setReqMessage(String reqMessage) {
        this.reqMessage = reqMessage;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public Date getExecTime() {
        return execTime;
    }

    public void setExecTime(Date execTime) {
        this.execTime = execTime;
    }
}