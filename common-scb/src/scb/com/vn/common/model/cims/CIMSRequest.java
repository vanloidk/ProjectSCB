/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import org.apache.log4j.Logger;

/**
 *
 * @author minhndb
 */
public abstract class CIMSRequest implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(CIMSRequest.class);
    
    private String time;
    private String partner;
    private String mac;

    @XmlElement(name = "TIME", nillable = false)
    public String getTime() {
        return time;
    }

    @XmlElement(name = "PARTNER", nillable = false)
    public String getPartner() {
        return partner;
    }

    @XmlElement(name = "MAC", nillable = false)
    public String getMac() {
        return mac;
    }
    
    public Date getFormatTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            return format.parse(this.time);
        } catch (Exception e) {
            return null;
        }
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public boolean isOutOfTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date executeTime = format.parse(this.time);
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
    
    public abstract String getValueToBuildMac();
}
