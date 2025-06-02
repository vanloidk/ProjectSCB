/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.status.transferMoney;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hienlt6
 */
@XmlRootElement(name = "RESPONSE")
public class GetStatusOfTransferMoneyRes implements Serializable {

    private static final long serialVersionUID = 1L;

    private String responseCode;
    private String description;
    private String providerId;
    private String transactionId;
    private String txnDetailId;
    private String transId;

    public GetStatusOfTransferMoneyRes() {
    }

    public GetStatusOfTransferMoneyRes(String responseCode, String description) {
        this.responseCode = responseCode;
        this.description = description;
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

    @XmlElement(name = "TXNDETAILID")
    public String getTxnDetailId() {
        return txnDetailId;
    }

    public void setTxnDetailId(String txnDetailId) {
        this.txnDetailId = txnDetailId;
    }

    @XmlElement(name = "TRANSID")
    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }
}
