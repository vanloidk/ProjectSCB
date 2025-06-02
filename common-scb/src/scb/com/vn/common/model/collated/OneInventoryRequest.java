/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.collated;

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
@XmlRootElement(name = "REQUEST")
public class OneInventoryRequest implements Serializable{

    private static final Logger LOGGER = Logger.getLogger(OneInventoryRequest.class);

    private String transsactionId;
    private String providerId;
    private String transDate;
    private String signature;

    @XmlElement(name = "TRANSSACTIONID")
    public String getTranssactionId() {
        return transsactionId;
    }

    public void setTranssactionId(String transsactionId) {
        this.transsactionId = transsactionId;
    }

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

    @XmlElement(name = "SIGNATURE")
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getValueBuildMac() {
        return new StringBuilder(this.transsactionId).append(this.providerId).append(this.transDate).toString();
    }

    public boolean isValid() {
        return this.transsactionId != null && this.providerId != null && isValidTransDate();
    }

    public boolean isValidTransDate() {
        boolean result = false;
        try {
            if (this.transDate != null & !this.transDate.isEmpty()) {
                String partern = "yyyyMMdd";
                SimpleDateFormat format = new SimpleDateFormat(partern);
                format.parse(this.transDate);
                result = true;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }
}
