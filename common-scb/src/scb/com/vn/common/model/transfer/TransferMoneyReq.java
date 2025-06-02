/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cims.CIMSRequest;
import scb.com.vn.ultility.Xml;
import scb.com.vn.ultility.ZipUtils;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "REQUEST")
public class TransferMoneyReq extends CIMSRequest implements Serializable {

    private static final Logger logger = Logger.getLogger(TransferMoneyReq.class);
    private static final long serialVersionUID = 1L;

    private String msgId;
    private String txnRef;
    private String transDate;
    private String providerId;
    private String transData;
    private String signature;
    private ReceivingInfo receivingInfo;

    private boolean oldPartner = false;

    @XmlElement(name = "MSGID")
    public String getMsgId() {
        return msgId;
    }

    @XmlElement(name = "TXNREF")
    public String getTxnRef() {
        return txnRef;
    }

    @XmlElement(name = "TRANSDATE")
    public String getTransDate() {
        return transDate;
    }

    @XmlElement(name = "PROVIDERID")
    public String getProviderId() {
        return providerId;
    }

    @XmlElement(name = "TRANSDATA")
    public String getTransData() {
        return transData;
    }

    @XmlElement(name = "SIGNATURE")
    public String getSignature() {
        return signature;
    }

    @XmlElement(name = "RECEIVINGINFO")
    public ReceivingInfo getReceivingInfo() {
        return receivingInfo;
    }

    public boolean isOldPartner() {
        return oldPartner;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setTxnRef(String txnRef) {
        this.txnRef = txnRef;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public void setTransData(String transData) {
        this.transData = transData;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setOldPartner(boolean oldPartner) {
        this.oldPartner = oldPartner;
    }

    public void setReceivingInfo(ReceivingInfo receivingInfo) {
        this.receivingInfo = receivingInfo;
    }

    public int isValid() {
        if (this.msgId.endsWith("001")) {
            return 1;
        }
        return 0;
    }

    public String getDataToEncrypt() {
        return this.msgId + this.txnRef + this.transDate;
    }

    @Override
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

    @Override
    public String getValueToBuildMac() {
        return new StringBuilder().append(this.msgId).append(this.txnRef).append(this.transDate).toString();
    }

    public Transaction getTransDetails() {
        try {
            String str = ZipUtils.unZip(this.transData);
            setOldPartner(str);
            logger.info("du lieu sau khi unzip: " + str);
            /* Xu ly du lieu voi cac doi tac cu */
            if (oldPartner) {
                str = "<TRN>" + str + "</TRN>";
            }
            return (Transaction) Xml.unMarshaller(Transaction.class, str);
        } catch (Exception e) {
            logger.error(e);
            return new Transaction();
        }
    }

    private void setOldPartner(String transDataUnzipped) {
        if (transDataUnzipped.startsWith("<TRN_DETAIL>")) {
            oldPartner = true;
        }
    }
}
