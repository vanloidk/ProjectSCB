/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import scb.com.vn.ultility.Common;
import scb.com.vn.ultility.Xml;
import scb.com.vn.ultility.ZipUtils;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "RESPONSE")
public class TransferMoneyRes implements Serializable {
    private static final Logger logger = Logger.getLogger(TransferMoneyRes.class);
    private static final long serialVersionUID = 1L;

    private String scbRef;
    private String responseCode;
    private String transData;
    private String txnRef;
    private String providerId;
    private String description;
    private String signatureScb;

    public TransferMoneyRes() {}
    
    public TransferMoneyRes(String responseCode,  String description) {
        this.responseCode = responseCode;
        this.description = description;
    }

    @XmlElement(name = "SCBREF")
    public String getScbRef() {
        return scbRef;
    }

    @XmlElement(name = "RESPONSECODE")
    public String getResponseCode() {
        return responseCode;
    }

    @XmlElement(name = "TRANSDATA")
    public String getTransData() {
        return transData;
    }

    @XmlElement(name = "TXNREF")
    public String getTxnRef() {
        return txnRef;
    }

    @XmlElement(name = "PROVIDERID")
    public String getProviderId() {
        return providerId;
    }

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @XmlElement(name = "SIGNATURESCB")
    public String getSignatureScb() {
        return signatureScb;
    }

    public void setScbRef(String scbRef) {
        this.scbRef = scbRef;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setTransData(String transData) {
        this.transData = transData;
    }

    public void setTxnRef(String txnRef) {
        this.txnRef = txnRef;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSignatureScb(String md5Key) {
        try {
            this.signatureScb = Common.EncriptMD5(md5Key + this.responseCode + this.txnRef);
        } catch (Exception e) {
            logger.error(e);
        }
    }
    
    public void setTransData(Transaction transaction, boolean oldPartner) {
        try {
            String str = Xml.Marshaller(transaction);
            if (oldPartner && !str.isEmpty()) {
                str = str.replace("<TRN>", "").replace("</TRN>", "");
            }
            logger.info("du lieu truoc khi zip: " + str);
            String data = ZipUtils.zip(str);
            this.transData = data;
        } catch (Exception e) {
            logger.error(e);
        }
    }
}