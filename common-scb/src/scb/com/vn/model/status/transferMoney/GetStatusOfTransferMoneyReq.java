/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.status.transferMoney;

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
public class GetStatusOfTransferMoneyReq implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(GetStatusOfTransferMoneyReq.class);
    private static final long serialVersionUID = 1L;

    private String providerId;
    private String transsactionId;
    private String transactionId;
    private String transId;
    private String txnDetailId;
    private String transDate;
    private String signature;

    @XmlElement(name = "PROVIDERID")
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @XmlElement(name = "TRANSACTIONID")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @XmlElement(name = "TRANSSACTIONID")
    public String getTranssactionId() {
        return transsactionId;
    }

    public void setTranssactionId(String transsactionId) {
        this.transsactionId = transsactionId;
    }

    @XmlElement(name = "TRANSID")
    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    @XmlElement(name = "TXNDETAILID")
    public String getTxnDetailId() {
        return txnDetailId;
    }

    public void setTxnDetailId(String txnDetailId) {
        this.txnDetailId = txnDetailId;
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

    public String getValueToBuildMac() {
        return new StringBuilder(this.transsactionId).append(this.transId).append(this.transDate).append(this.providerId).append(this.txnDetailId).toString();
    }

    /*Thay doi transsactionId -> transactionId dung cho cac doi tac:
    * Western Union: WU server 73.20
     */
    public String getValueToBuildMacV2() {
        return new StringBuilder(this.transactionId).append(this.transId).append(this.transDate).append(this.providerId).append(this.txnDetailId).toString();
    }

    /*Thay doi transsactionId -> transactionId dung cho cac doi tac:
    * Western Union: WU server 73.20
     */
    public boolean isInvalidValueV2() {
        return this.providerId == null
                || this.transactionId == null
                || this.signature == null
                || this.transDate == null
                || this.txnDetailId == null
                || this.transId == null;
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
            LOGGER.error(e);
        }
        return true;
    }

    public Date getFormatTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            return format.parse(this.transDate);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isInvalidValue() {
        return this.providerId == null
                || this.transsactionId == null
                || this.signature == null
                || this.transDate == null
                || this.txnDetailId == null
                || this.transId == null;
    }

}
