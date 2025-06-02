/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer.napas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cims.CIMSRequest;
import scb.com.vn.common.model.transfer.ReceivingInfo;
import scb.com.vn.common.model.transfer.SenderInfo;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "REQUEST")
public class TransferMoney247Req extends CIMSRequest implements Serializable {

    private static final Logger logger = Logger.getLogger(TransferMoney247Req.class);
    private static final long serialVersionUID = 1L;

    private String msgId;
    private String providerId;
    private String transId;
    private String dateTime;
    private String toNumber;
    private String typeToNumber;
    private String amount;
    private String ccy;
    private String benId;
    private String description;
    private String mac;

    private ReceivingInfo receivingInfo;
    private SenderInfo senderInfo;

    @XmlElement(name = "MSGID")
    public String getMsgId() {
        return msgId;
    }

    @XmlElement(name = "PROVIDERID")
    public String getProviderId() {
        return providerId;
    }

    @XmlElement(name = "TRANSID")
    public String getTransId() {
        return transId;
    }

    @XmlElement(name = "DATETIME")
    public String getDateTime() {
        return dateTime;
    }

    @XmlElement(name = "TONUMBER")
    public String getToNumber() {
        return toNumber;
    }

    @XmlElement(name = "TYPETONUMBER")
    public String getTypeToNumber() {
        return typeToNumber;
    }

    @XmlElement(name = "AMOUNT")
    public String getAmount() {
        return amount;
    }

    public Double getAmountDoubleType() {
        return new Double(amount);
    }

    public BigDecimal getAmountBigDecimalType() {
        return new BigDecimal(amount);
    }

    @XmlElement(name = "CCY")
    public String getCcy() {
        return ccy;
    }

    @XmlElement(name = "BENID")
    public String getBenId() {
        return benId;
    }

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @XmlElement(name = "RECEIVINGINFO")
    public ReceivingInfo getReceivingInfo() {
        return receivingInfo;
    }

    @XmlElement(name = "SENDERINFO")
    public SenderInfo getSenderInfo() {
        return senderInfo;
    }
    public void setSenderInfo(SenderInfo senderInfo) {
        this.senderInfo = senderInfo;
    }

    @XmlElement(name = "MAC")
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
    
    
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    public void setTypeToNumber(String typeToNumber) {
        this.typeToNumber = typeToNumber;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public void setBenId(String benId) {
        this.benId = benId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReceivingInfo(ReceivingInfo receivingInfo) {
        this.receivingInfo = receivingInfo;
    }

    @Override
    public boolean isOutOfTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date executeTime = format.parse(this.dateTime);
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

    @Override
    public String getValueToBuildMac() {
        return new StringBuilder().append(this.msgId).append(this.providerId)
                .append(this.transId).append(this.dateTime).append(this.toNumber)
                .append(this.typeToNumber).append(this.amount).append(this.ccy).toString();
    }

    public boolean isInvalidValue() {
        return this.msgId == null
                || this.providerId == null
                || this.transId == null
                || this.dateTime == null
                || this.toNumber == null
                || isInvalidTypeToNumber()
                || isInvalidAmount()
                || this.ccy == null
                || this.getMac() == null;
    }

    private boolean isInvalidAmount() {
        boolean isInvalid = true;
        try {
            getAmountBigDecimalType();
            isInvalid = false;
        } catch (Exception e) {
            logger.error(e);
        }
        return isInvalid;
    }

    private boolean isInvalidTypeToNumber() {
        boolean isInvalid = true;
        if (this.typeToNumber != null) {
            switch (this.typeToNumber) {
                case "ACCOUNT":
                    if (this.benId != null) {
                        isInvalid = false;
                    }
                    break;
                case "CARD":
                    isInvalid = false;
                    break;
                default:
                    break;
            }
        }
        return isInvalid;
    }

    public boolean isTransReq() {
        return "TRN".equals(msgId);
    }

    public boolean isNotVnd() {
        return !"VND".equals(ccy);
    }

    public boolean isVnd() {
        return "VND".equals(ccy);
    }

}
