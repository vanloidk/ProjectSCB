/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.sms;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement
public class SmsInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String phoneNumber;
    private String fromDate;
    private String toDate;
    private String timeQuery;
    private List<SmsDetail> smsDetails = new ArrayList<>();
    private SmsDetail resendInfo;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<SmsDetail> getSmsDetails() {
        return smsDetails;
    }

    public void setSmsDetails(List<SmsDetail> smsDetails) {
        this.smsDetails = smsDetails;
    }

    public String getTimeQuery() {
        return timeQuery;
    }

    public void setTimeQuery(String timeQuery) {
        this.timeQuery = timeQuery;
    }

    public SmsDetail getResendInfo() {
        return resendInfo;
    }

    public void setResendInfo(SmsDetail resendInfo) {
        this.resendInfo = resendInfo;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
