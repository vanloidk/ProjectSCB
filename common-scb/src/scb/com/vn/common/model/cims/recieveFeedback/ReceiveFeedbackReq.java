/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.recieveFeedback;

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
@XmlRootElement(name = "RECEIVEREQ")
public class ReceiveFeedbackReq implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ReceiveFeedbackReq.class);
    private static final long serialVersionUID = 1L;

    private String partnerCode;
    private String time;
    private String typeId;
    private String type;
    private String signature;
    private String username;
    private String idUsername;

    @XmlElement(name = "MA_DOI_TAC")
    public String getPartnerCode() {
        return partnerCode;
    }

    @XmlElement(name = "THOI_GIAN")
    public String getTime() {
        return time;
    }

    @XmlElement(name = "MA_LOAI")
    public String getTypeId() {
        return typeId;
    }

    @XmlElement(name = "LOAI")
    public String getType() {
        return type;
    }

    @XmlElement(name = "CHU_KY")
    public String getSignature() {
        return signature;
    }

    @XmlElement(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    @XmlElement(name = "ID_USERNAME")
    public String getIdUsername() {
        return idUsername;
    }

    /*-----------------------Setter------------------------------*/
    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIdUsername(String idUsername) {
        this.idUsername = idUsername;
    }

    public String getValueToBuildMac() {
        return new StringBuilder(this.partnerCode)
                .append(this.time)
                .append(this.typeId)
                .append(this.type).toString();
    }
    
    public boolean isValid () {
        return partnerCode != null && !partnerCode.isEmpty()
                && time != null && !time.isEmpty()
                && typeId != null && !typeId.isEmpty()
                && type != null && !type.isEmpty()
                && signature != null && !signature.isEmpty()
                && username != null && !username.isEmpty();
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
}
