/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer.status;

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
@XmlRootElement(name = "REQUEST")
/*dang test tren 73.20
* doi tac: WU
 */
public class GetListTransactionByDateRequest {

    private static final Logger logger = Logger.getLogger(GetListTransactionByDateRequest.class);
    private static final int MAX_DATETIME = 12;

    private String providerId;
    private String transDate;
    private String startDateTime;
    private String endDateTime;
    private String mac;

    @XmlElement(name = "PROVIDERID")
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @XmlElement(name = "TRANSDATE")
    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    @XmlElement(name = "STARTDATETIME")
    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    @XmlElement(name = "ENDDATETIME")
    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @XmlElement(name = "MAC")
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public boolean isValid() {
        return this.providerId != null && this.transDate != null && this.startDateTime != null && this.endDateTime != null && 
                this.startDateTime.length() == MAX_DATETIME && this.endDateTime.length() == MAX_DATETIME && this.mac != null;
    }

    public String getValueToBuildMac() {
        return new StringBuilder().append(this.providerId).append(this.transDate)
                .append(this.startDateTime).append(this.endDateTime).toString();
    }

    public boolean isOutOfTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date executeTime = format.parse(this.transDate);
            Calendar now = Calendar.getInstance();
            long diffInMillies = Math.abs(now.getTime().getTime() - executeTime.getTime());
            if (diffInMillies <= 5 * 60 * 1000) { // 5 minutes
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    public boolean compareDateTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmm");
            Date startDate = format.parse(this.startDateTime);
            Date endDate = format.parse(this.endDateTime);
            /*Neu ngay bat dau ma > ngay ket thuc --> false*/
            if (startDate.compareTo(endDate) > 0) {
                logger.info("start date: " + startDate + "lon hon" + "end date" + endDate);
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }
}
