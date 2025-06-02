/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer.status;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import scb.com.vn.ultility.Common;
import scb.com.vn.ultility.Xml;
import scb.com.vn.ultility.ZipUtils;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "RESPONSE")
/*dang test tren 73.20
* doi tac: WU
 */
public class GetListTransactionByDateResponse {

    private static final Logger logger = Logger.getLogger(GetListTransactionByDateResponse.class);

    private String providerId;
    private String transDate;
    private String startDateTime;
    private String endDateTime;
    private String transData;
    private String mac;
    private String responseCode;
    private String description;

    public GetListTransactionByDateResponse() {

    }

    public GetListTransactionByDateResponse(String responseCode, String description) {
        this.responseCode = responseCode;
        this.description = description;
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

    @XmlElement(name = "RESPONSECODE")
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "TRANSDATA")
    public String getTransData() {
        return transData;
    }

    public void setTransData(String transData) {
        this.transData = transData;
    }

    public void setTransData(TransactionByDate transaction) {
        try {
            String str = Xml.Marshaller(transaction);
            if (!str.isEmpty()) {
//                str = str.replace("<TRN>", "").replace("</TRN>", "");
            }
            logger.info("du lieu truoc khi zip: " + str);
            String data = ZipUtils.zip(str);
            this.transData = data;
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void signMac(String md5Key) {
        try {
            this.mac = Common.EncriptMD5(md5Key + this.providerId
                    + this.transDate + this.startDateTime + this.endDateTime + this.responseCode + this.description);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
