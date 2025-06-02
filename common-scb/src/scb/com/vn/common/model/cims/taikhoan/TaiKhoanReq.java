package scb.com.vn.common.model.cims.taikhoan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import org.apache.log4j.Logger;

public class TaiKhoanReq implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(TaiKhoanReq.class);
    private String time;
    private String partner;
    private String mac;

    @XmlElement(name = "Mac", nillable = false)
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @XmlElement(name = "Time", nillable = false)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @XmlElement(name = "Partner", nillable = false)
    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public Date getFormatTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            return format.parse(this.time);
        } catch (Exception e) {
            return null;
        }
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
            logger.error(e);
        }
        return true;
    }
}
