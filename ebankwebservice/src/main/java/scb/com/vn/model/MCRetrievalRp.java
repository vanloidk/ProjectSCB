/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import com.mastercard.api.core.exception.ApiException;
import com.mastercard.api.p2m.MerchantRetrieval;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "MCRetrievalRp")
public class MCRetrievalRp {

    private String id;
    private String resourceType;
    private String transferRef;
    private String paymentType;
    private String senderAcc;
    private String senderFName;
    private String senderLName;
    private String merAcc;
    private String merFName;
    private String merLName;
    private String merCountrySub;
    private String merCategory;
    private String amount;
    private String ccy;
    private String created;
    private String thId;
    private String thResourceType;
    private String thAcc;
    private String thAmount;
    private String thCcy;
    private String thNetwork;
    private String thNetworkSta;
    private String thNetworkDes;
    private String thType;
    private String thCreateTs;
    private String thStatus;
    private String thStatusReason;
    private String thStatusTs;
    private String thRetrievalRef;
    private String thSysTraceAudit;
    private String channel;
    private String originalStatus;
    private String status;
    private String statusTs;
    private String transDateTime;
    private String fundingSource;
    private String merCardName;
    private String errorCode;
    private String errorDesc;

    /**
     *
     */
    public MCRetrievalRp() {
    }

    /* index: 1 ~ byReference, 2 ~ byId */

    /**
     *
     * @param response
     * @param index
     */

    public MCRetrievalRp(MerchantRetrieval response, int index) {
        switch (index) {
            case 1:
                loadByRef(response);
                break;
            case 2:
                loadById(response);
                break;
            default:
                loadByRef(response);
                break;
        }
    }

    /**
     *
     * @param e
     */
    public MCRetrievalRp(ApiException e) {
        this.errorCode = e.getReasonCode();
        this.errorDesc = e.getMessage() + " - " + e.getSource();
    }

    private void loadById(MerchantRetrieval response) {
        this.id = this.id = response.get("merchant_transfer.id").toString();
        this.resourceType = response.get("merchant_transfer.resource_type").toString();
        this.transferRef = response.get("merchant_transfer.transfer_reference").toString();
        this.paymentType = response.get("merchant_transfer.payment_type").toString();
        this.senderAcc = response.get("merchant_transfer.sender_account_uri").toString();
        this.senderFName = response.get("merchant_transfer.sender.first_name").toString();
        this.senderLName = response.get("merchant_transfer.sender.last_name").toString();
        this.merAcc = response.get("merchant_transfer.recipient_account_uri").toString();
        this.merFName = response.get("merchant_transfer.recipient.first_name").toString();
        this.merLName = response.get("merchant_transfer.recipient.last_name").toString();
        this.merCountrySub = response.get("merchant_transfer.recipient.address.country_subdivision").toString();
        this.merCategory = response.get("merchant_transfer.recipient.merchant_category_code").toString();
        this.amount = response.get("merchant_transfer.transfer_amount.value").toString();
        this.ccy = response.get("merchant_transfer.transfer_amount.currency").toString();
        this.created = response.get("merchant_transfer.created").toString();
        this.thId = response.get("merchant_transfer.transaction_history.data.transaction[0].id").toString();
        this.thResourceType = response.get("merchant_transfer.transaction_history.data.transaction[0].resource_type").toString();
        this.thAcc = response.get("merchant_transfer.transaction_history.data.transaction[0].account_uri").toString();
        this.thAmount = response.get("merchant_transfer.transaction_history.data.transaction[0].transaction_amount.value").toString();
        this.thCcy = response.get("merchant_transfer.transaction_history.data.transaction[0].transaction_amount.currency").toString();
        this.thNetwork = response.get("merchant_transfer.transaction_history.data.transaction[0].network").toString();
        this.thType = response.get("merchant_transfer.transaction_history.data.transaction[0].type").toString();
        this.thCreateTs = response.get("merchant_transfer.transaction_history.data.transaction[0].create_timestamp").toString();
        this.thStatus = response.get("merchant_transfer.transaction_history.data.transaction[0].status").toString();
        this.thStatusReason = response.get("merchant_transfer.transaction_history.data.transaction[0].status_reason").toString();
        this.thStatusTs = response.get("merchant_transfer.transaction_history.data.transaction[0].status_timestamp").toString();
        this.thRetrievalRef = response.get("merchant_transfer.transaction_history.data.transaction[0].retrieval_reference").toString();
        this.thSysTraceAudit = response.get("merchant_transfer.transaction_history.data.transaction[0].system_trace_audit_number").toString();
        this.channel = response.get("merchant_transfer.channel").toString();
        this.originalStatus = response.get("merchant_transfer.original_status").toString();
        this.status = response.get("merchant_transfer.status").toString();
        this.statusTs = response.get("merchant_transfer.status_timestamp").toString();
        this.transDateTime = response.get("merchant_transfer.transaction_local_date_time").toString();
        this.fundingSource = response.get("merchant_transfer.funding_source").toString();
        this.merCardName = response.get("merchant_transfer.participant.card_acceptor_name").toString();
    }

    private void loadByRef(MerchantRetrieval response) {
        this.id = response.get("merchant_transfers.data.merchant_transfer[0].id").toString();
        this.resourceType = response.get("merchant_transfers.data.merchant_transfer[0].resource_type").toString();
        this.transferRef = response.get("merchant_transfers.data.merchant_transfer[0].transfer_reference").toString();
        this.paymentType = response.get("merchant_transfers.data.merchant_transfer[0].payment_type").toString();
        this.senderAcc = response.get("merchant_transfers.data.merchant_transfer[0].sender_account_uri").toString();
        this.senderFName = response.get("merchant_transfers.data.merchant_transfer[0].sender.first_name").toString();
        this.senderLName = response.get("merchant_transfers.data.merchant_transfer[0].sender.last_name").toString();
        this.merAcc = response.get("merchant_transfers.data.merchant_transfer[0].recipient_account_uri").toString();
        this.merFName = response.get("merchant_transfers.data.merchant_transfer[0].recipient.first_name").toString();
        this.merLName = response.get("merchant_transfers.data.merchant_transfer[0].recipient.last_name").toString();
        this.merCountrySub = response.get("merchant_transfers.data.merchant_transfer[0].recipient.address.country_subdivision").toString();
        this.merCategory = response.get("merchant_transfers.data.merchant_transfer[0].recipient.merchant_category_code").toString();
        this.amount = response.get("merchant_transfers.data.merchant_transfer[0].transfer_amount.value").toString();
        this.ccy = response.get("merchant_transfers.data.merchant_transfer[0].transfer_amount.currency").toString();
        this.created = response.get("merchant_transfers.data.merchant_transfer[0].created").toString();
        this.thId = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].id").toString();
        this.thResourceType = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].resource_type").toString();
        this.thAcc = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].account_uri").toString();
        this.thAmount = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].transaction_amount.value").toString();
        this.thCcy = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].transaction_amount.currency").toString();
        this.thNetwork = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].network").toString();
        this.thNetworkSta = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].network_status_code").toString();
        this.thNetworkDes = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].network_status_description").toString();
        this.thType = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].type").toString();
        this.thCreateTs = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].create_timestamp").toString();
        this.thStatus = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].status").toString();
        this.thStatusReason = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].status_reason").toString();
        this.thStatusTs = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].status_timestamp").toString();
        this.thRetrievalRef = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].retrieval_reference").toString();
        this.thSysTraceAudit = response.get("merchant_transfers.data.merchant_transfer[0].transaction_history.data.transaction[0].system_trace_audit_number").toString();
        this.channel = response.get("merchant_transfers.data.merchant_transfer[0].channel").toString();
        this.originalStatus = response.get("merchant_transfers.data.merchant_transfer[0].original_status").toString();
        this.status = response.get("merchant_transfers.data.merchant_transfer[0].status").toString();
        this.statusTs = response.get("merchant_transfers.data.merchant_transfer[0].status_timestamp").toString();
        this.transDateTime = response.get("merchant_transfers.data.merchant_transfer[0].transaction_local_date_time").toString();
        this.fundingSource = response.get("merchant_transfers.data.merchant_transfer[0].funding_source").toString();
        this.merCardName = response.get("merchant_transfers.data.merchant_transfer[0].participant.card_acceptor_name").toString();
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ID", nillable = true)
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "RESOURCETYPE", nillable = true)
    public String getResourceType() {
        return resourceType;
    }

    /**
     *
     * @param resourceType
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "TRANSFERREF", nillable = true)
    public String getTransferRef() {
        return transferRef;
    }

    /**
     *
     * @param transferRef
     */
    public void setTransferRef(String transferRef) {
        this.transferRef = transferRef;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PAYMENTTYPE", nillable = true)
    public String getPaymentType() {
        return paymentType;
    }

    /**
     *
     * @param paymentType
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "SENDERACC", nillable = true)
    public String getSenderAcc() {
        return senderAcc;
    }

    /**
     *
     * @param senderAcc
     */
    public void setSenderAcc(String senderAcc) {
        this.senderAcc = senderAcc;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "SENDERFNAME", nillable = true)
    public String getSenderFName() {
        return senderFName;
    }

    /**
     *
     * @param senderFName
     */
    public void setSenderFName(String senderFName) {
        this.senderFName = senderFName;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "SENDERLNAME", nillable = true)
    public String getSenderLName() {
        return senderLName;
    }

    /**
     *
     * @param senderLName
     */
    public void setSenderLName(String senderLName) {
        this.senderLName = senderLName;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MERACC", nillable = true)
    public String getMerAcc() {
        return merAcc;
    }

    /**
     *
     * @param merAcc
     */
    public void setMerAcc(String merAcc) {
        this.merAcc = merAcc;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MERFNAME", nillable = true)
    public String getMerFName() {
        return merFName;
    }

    /**
     *
     * @param merFName
     */
    public void setMerFName(String merFName) {
        this.merFName = merFName;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MERLNAME", nillable = true)
    public String getMerLName() {
        return merLName;
    }

    /**
     *
     * @param merLName
     */
    public void setMerLName(String merLName) {
        this.merLName = merLName;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MERCOUNTRYSUB", nillable = true)
    public String getMerCountrySub() {
        return merCountrySub;
    }

    /**
     *
     * @param merCountrySub
     */
    public void setMerCountrySub(String merCountrySub) {
        this.merCountrySub = merCountrySub;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MERCATEGORY", nillable = true)
    public String getMerCategory() {
        return merCategory;
    }

    /**
     *
     * @param merCategory
     */
    public void setMerCategory(String merCategory) {
        this.merCategory = merCategory;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AMOUNT", nillable = true)
    public String getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CCY", nillable = true)
    public String getCcy() {
        return ccy;
    }

    /**
     *
     * @param ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CREATED", nillable = true)
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THID", nillable = true)
    public String getThId() {
        return thId;
    }

    /**
     *
     * @param thId
     */
    public void setThId(String thId) {
        this.thId = thId;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THRESOURCETYPE", nillable = true)
    public String getThResourceType() {
        return thResourceType;
    }

    /**
     *
     * @param thResourceType
     */
    public void setThResourceType(String thResourceType) {
        this.thResourceType = thResourceType;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THACC", nillable = true)
    public String getThAcc() {
        return thAcc;
    }

    /**
     *
     * @param thAcc
     */
    public void setThAcc(String thAcc) {
        this.thAcc = thAcc;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THAMOUNT", nillable = true)
    public String getThAmount() {
        return thAmount;
    }

    /**
     *
     * @param thAmount
     */
    public void setThAmount(String thAmount) {
        this.thAmount = thAmount;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THCCY", nillable = true)
    public String getThCcy() {
        return thCcy;
    }

    /**
     *
     * @param thCcy
     */
    public void setThCcy(String thCcy) {
        this.thCcy = thCcy;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THNETWORK", nillable = true)
    public String getThNetwork() {
        return thNetwork;
    }

    /**
     *
     * @param thNetwork
     */
    public void setThNetwork(String thNetwork) {
        this.thNetwork = thNetwork;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THNETWORKSTA", nillable = true)
    public String getThNetworkSta() {
        return thNetworkSta;
    }

    /**
     *
     * @param thNetworkSta
     */
    public void setThNetworkSta(String thNetworkSta) {
        this.thNetworkSta = thNetworkSta;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THNETWORKDES", nillable = true)
    public String getThNetworkDes() {
        return thNetworkDes;
    }

    /**
     *
     * @param thNetworkDes
     */
    public void setThNetworkDes(String thNetworkDes) {
        this.thNetworkDes = thNetworkDes;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THTYPE", nillable = true)
    public String getThType() {
        return thType;
    }

    /**
     *
     * @param thType
     */
    public void setThType(String thType) {
        this.thType = thType;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THCTEATETS", nillable = true)
    public String getThCreateTs() {
        return thCreateTs;
    }

    /**
     *
     * @param thCreateTs
     */
    public void setThCreateTs(String thCreateTs) {
        this.thCreateTs = thCreateTs;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THSTATUS", nillable = true)
    public String getThStatus() {
        return thStatus;
    }

    /**
     *
     * @param thStatus
     */
    public void setThStatus(String thStatus) {
        this.thStatus = thStatus;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THSTATUSREASON", nillable = true)
    public String getThStatusReason() {
        return thStatusReason;
    }

    /**
     *
     * @param thStatusReason
     */
    public void setThStatusReason(String thStatusReason) {
        this.thStatusReason = thStatusReason;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THSTATUSTS", nillable = true)
    public String getThStatusTs() {
        return thStatusTs;
    }

    /**
     *
     * @param thStatusTs
     */
    public void setThStatusTs(String thStatusTs) {
        this.thStatusTs = thStatusTs;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THRETRIEVALREF", nillable = true)
    public String getThRetrievalRef() {
        return thRetrievalRef;
    }

    /**
     *
     * @param thRetrievalRef
     */
    public void setThRetrievalRef(String thRetrievalRef) {
        this.thRetrievalRef = thRetrievalRef;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "THSYSTRACEAUDIT", nillable = true)
    public String getThSysTraceAudit() {
        return thSysTraceAudit;
    }

    /**
     *
     * @param thSysTraceAudit
     */
    public void setThSysTraceAudit(String thSysTraceAudit) {
        this.thSysTraceAudit = thSysTraceAudit;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CHANNEL", nillable = true)
    public String getChannel() {
        return channel;
    }

    /**
     *
     * @param channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ORIGINALSTATUS", nillable = true)
    public String getOriginalStatus() {
        return originalStatus;
    }

    /**
     *
     * @param originalStatus
     */
    public void setOriginalStatus(String originalStatus) {
        this.originalStatus = originalStatus;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "STATUS", nillable = true)
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "STATUSTS", nillable = true)
    public String getStatusTs() {
        return statusTs;
    }

    /**
     *
     * @param statusTs
     */
    public void setStatusTs(String statusTs) {
        this.statusTs = statusTs;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "TRANSDATETIME", nillable = true)
    public String getTransDateTime() {
        return transDateTime;
    }

    /**
     *
     * @param transDateTime
     */
    public void setTransDateTime(String transDateTime) {
        this.transDateTime = transDateTime;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "FUNDINGSOURCE", nillable = true)
    public String getFundingSource() {
        return fundingSource;
    }

    /**
     *
     * @param fundingSource
     */
    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MERCARDNAME", nillable = true)
    public String getMerCardName() {
        return merCardName;
    }

    /**
     *
     * @param merCardName
     */
    public void setMerCardName(String merCardName) {
        this.merCardName = merCardName;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ERRORCODE", nillable = true)
    public String getErrorCode() {
        return errorCode;
    }

    /**
     *
     * @param errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ERRORDESC", nillable = true)
    public String getErrorDesc() {
        return errorDesc;
    }

    /**
     *
     * @param errorDesc
     */
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

}
