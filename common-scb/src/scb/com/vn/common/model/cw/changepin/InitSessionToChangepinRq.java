/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cw.changepin;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "INITSESSIONTOCHANGEPINRQ")
public class InitSessionToChangepinRq implements Serializable {
    private static final Logger logger = Logger.getLogger(InitSessionToChangepinRq.class);
    private static final long serialVersionUID = 1L;
    
    private String partnerId;
    private String loc;
    private String last4Digits;
    private String createDate;
    private String transactionId;
    private String mobileNo;
    private String signature;

    @XmlElement(name = "PARTNERID")
    public String getPartnerId() {
        return partnerId;
    }

    @XmlElement(name = "LOC")
    public String getLoc() {
        return loc;
    }

    @XmlElement(name = "LAST4DIGITS")
    public String getLast4Digits() {
        return last4Digits;
    }

    @XmlElement(name = "CREATEDATE")
    public String getCreateDate() {
        return createDate;
    }

    @XmlElement(name = "TRANSACTIONID")
    public String getTransactionId() {
        return transactionId;
    }

    @XmlElement(name = "MOBILENO")
    public String getMobileNo() {
        return mobileNo;
    }

    @XmlElement(name = "SIGNATURE")
    public String getSignature() {
        return signature;
    }
    
    /********************************************/
    
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setLast4Digits(String last4Digits) {
        this.last4Digits = last4Digits;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    public boolean isValid() {
        return this.partnerId != null && !this.partnerId.isEmpty()
                && this.loc != null && !this.loc.isEmpty()
                && this.last4Digits != null && !this.last4Digits.isEmpty()
                && this.createDate != null && !this.createDate.isEmpty()
                && this.transactionId != null && !this.transactionId.isEmpty()
                && this.mobileNo != null && !this.mobileNo.isEmpty()
                && this.signature != null && !this.signature.isEmpty();
    }
    
    public Date getFormatTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            return format.parse(this.createDate);
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean isOutOfTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date executeTime = format.parse(this.createDate);
            Calendar now = Calendar.getInstance();
            long diffInMillies = Math.abs(now.getTime().getTime() - executeTime.getTime());
            if(diffInMillies <= 5 * 60 * 1000) { // 5 minutes
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }
    
    public String getValueToBuildMac() {
        return this.partnerId + this.loc + this.last4Digits + this.createDate + this.transactionId + this.mobileNo;
    }
}
