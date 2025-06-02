/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.register;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "REGISTERREQ")
public class RegisterInfoReq implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(RegisterInfoReq.class);
    private static final long serialVersionUID = 1L;
    
    private String partnerCode;
    private String time;
    private String signature;

    @XmlElement(name = "MA_DOI_TAC")
    public String getPartnerCode() {
        return partnerCode;
    }

    @XmlElement(name = "THOI_GIAN")
    public String getTime() {
        return time;
    }

    @XmlElement(name = "CHU_KY")
    public String getSignature() {
        return signature;
    }

    /*-----------------------Setter----------------------------*/
    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getValueToBuildMac() {
        return new StringBuilder(this.partnerCode).append(this.time).toString();
    }

    public boolean isOutOfTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date executeTime = format.parse(this.time);
            Calendar now = Calendar.getInstance();
            long diffInMillies = Math.abs(now.getTime().getTime() - executeTime.getTime());
            if (diffInMillies <= 5 * 60 * 1000) { // 5 minutes
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return true;
    }

    public boolean isInvalidValue() {
        return this.partnerCode == null
                || this.signature == null
                || this.time == null;
    }
}
