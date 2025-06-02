/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer.napas;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.transfer.TransferMoneyRes;
import scb.com.vn.ultility.Common;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "RESPONSE")
public class TransferMoney247Res implements Serializable {
    private static final Logger logger = Logger.getLogger(TransferMoneyRes.class);
    private static final long serialVersionUID = 1L;
    
    private String responseCode;
    private String destName;
    private String bankTransId = "0";
    private String transId;
    private String description;
    private String provideId;
    private String mac;

    public TransferMoney247Res() {}

    public TransferMoney247Res(String responseCode, String description) {
        this.responseCode = responseCode;
        this.description = description;
    }

    @XmlElement(name = "RESPONSECODE")
    public String getResponseCode() {
        return responseCode;
    }

    @XmlElement(name = "DESTNAME")
    public String getDestName() {
        return destName;
    }

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }
    
    @XmlElement(name = "BANKTRANSID")
    public String getBankTransId() {
        return bankTransId;
    }
    
    public Double getBankTransIdDoubleType() {
        return new Double(bankTransId);
    }

    @XmlElement(name = "TRANSID")
    public String getTransId() {
        return transId;
    }

    @XmlElement(name = "PROVIDEID")
    public String getProvideId() {
        return provideId;
    }

    @XmlElement(name = "MAC")
    public String getMac() {
        return mac;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public void setBankTransId(String bankTransId) {
        this.bankTransId = bankTransId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public void setProvideId(String provideId) {
        this.provideId = provideId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
    
    public void signMac(String md5Key) {
        try {
            this.mac = Common.EncriptMD5(md5Key + this.responseCode + this.destName
                    + this.bankTransId + this.transId + this.description + this.provideId);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}