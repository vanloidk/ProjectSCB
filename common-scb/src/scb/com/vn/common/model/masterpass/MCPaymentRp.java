/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.masterpass;

import com.mastercard.api.core.exception.ApiException;
import com.mastercard.api.p2m.MerchantTransferPayment;

/**
 *
 * @author minhndb
 */
public class MCPaymentRp implements java.io.Serializable
{    
    private static final long serialVersionUID = 1L;
    
    private String sequenceNo;
    /**/
    private String id;
    private String resourceType;
    private String paymentType;
    private String created;
    private String tranHiId;
    private String tranHiResType;
    private String tranHiNetwork;
    private String tranHiType;
    private String tranHiCreatedTs;
    private String tranHiStatus;
    private String tranHiStatusRea;
    private String tranHiStatusTs;
    private String tranHiRetrieRef;
    private String tranHiSysTraAudit;
    private String tranHiSwitSeriNum;
    private String originalStatus;
    private String mastercardStatus;
    private String statusTs;
    
    /**/
    private String responseCode;
    private String errorCode;
    private String errorDesc;

    public MCPaymentRp() {}
    
    public MCPaymentRp(String sequenceNo, MerchantTransferPayment response)
    {
        this.sequenceNo = sequenceNo;
        this.id = response.get("merchant_transfer.id").toString();
        this.resourceType = response.get("merchant_transfer.resource_type").toString();
        this.paymentType = response.get("merchant_transfer.payment_type").toString();
        this.created = response.get("merchant_transfer.created").toString();
        this.tranHiId = response.get("merchant_transfer.transaction_history.data.transaction[0].id").toString();
        this.tranHiResType = response.get("merchant_transfer.transaction_history.data.transaction[0].resource_type").toString();
        this.tranHiNetwork = response.get("merchant_transfer.transaction_history.data.transaction[0].network").toString();
        this.tranHiType = response.get("merchant_transfer.transaction_history.data.transaction[0].type").toString();
        this.tranHiCreatedTs = response.get("merchant_transfer.transaction_history.data.transaction[0].create_timestamp").toString();
        this.tranHiStatus = response.get("merchant_transfer.transaction_history.data.transaction[0].status").toString();
        this.tranHiStatusRea = response.get("merchant_transfer.transaction_history.data.transaction[0].status_reason").toString();
        this.tranHiStatusTs = response.get("merchant_transfer.transaction_history.data.transaction[0].status_timestamp").toString();
        this.tranHiRetrieRef = response.get("merchant_transfer.transaction_history.data.transaction[0].retrieval_reference").toString();
        this.tranHiSysTraAudit = response.get("merchant_transfer.transaction_history.data.transaction[0].system_trace_audit_number").toString();
        this.tranHiSwitSeriNum = response.get("merchant_transfer.transaction_history.data.transaction[0].switch_serial_number").toString();
        this.originalStatus = response.get("merchant_transfer.original_status").toString();
        this.mastercardStatus = response.get("merchant_transfer.status").toString();
        this.statusTs = response.get("merchant_transfer.status_timestamp").toString();
    }
    
    public MCPaymentRp(MerchantTransferPayment response)
    {
        this.id = response.get("merchant_transfer.id").toString();
        this.resourceType = response.get("merchant_transfer.resource_type").toString();
        this.paymentType = response.get("merchant_transfer.payment_type").toString();
        this.created = response.get("merchant_transfer.created").toString();
        this.tranHiId = response.get("merchant_transfer.transaction_history.data.transaction[0].id").toString();
        this.tranHiResType = response.get("merchant_transfer.transaction_history.data.transaction[0].resource_type").toString();
        this.tranHiNetwork = response.get("merchant_transfer.transaction_history.data.transaction[0].network").toString();
        this.tranHiType = response.get("merchant_transfer.transaction_history.data.transaction[0].type").toString();
        this.tranHiCreatedTs = response.get("merchant_transfer.transaction_history.data.transaction[0].create_timestamp").toString();
        this.tranHiStatus = response.get("merchant_transfer.transaction_history.data.transaction[0].status").toString();
        this.tranHiStatusRea = response.get("merchant_transfer.transaction_history.data.transaction[0].status_reason").toString();
        this.tranHiStatusTs = response.get("merchant_transfer.transaction_history.data.transaction[0].status_timestamp").toString();
        this.tranHiRetrieRef = response.get("merchant_transfer.transaction_history.data.transaction[0].retrieval_reference").toString();
        this.tranHiSysTraAudit = response.get("merchant_transfer.transaction_history.data.transaction[0].system_trace_audit_number").toString();
        this.tranHiSwitSeriNum = response.get("merchant_transfer.transaction_history.data.transaction[0].switch_serial_number").toString();
        this.originalStatus = response.get("merchant_transfer.original_status").toString();
        this.mastercardStatus = response.get("merchant_transfer.status").toString();
        this.statusTs = response.get("merchant_transfer.status_timestamp").toString();
    }
    
    public MCPaymentRp(ApiException e)
    {
        this.errorCode = e.getReasonCode();
        this.errorDesc = e.getMessage() + " - " + e.getSource();
    }

    public String getSequenceNo()
    {
        return sequenceNo;
    }

    public void setSequenceNo(String sequenceNo)
    {
        this.sequenceNo = sequenceNo;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }

    public String getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType(String paymentType)
    {
        this.paymentType = paymentType;
    }

    public String getCreated()
    {
        return created;
    }

    public void setCreated(String created)
    {
        this.created = created;
    }

    public String getTranHiId()
    {
        return tranHiId;
    }

    public void setTranHiId(String tranHiId)
    {
        this.tranHiId = tranHiId;
    }

    public String getTranHiResType()
    {
        return tranHiResType;
    }

    public void setTranHiResType(String tranHiResType)
    {
        this.tranHiResType = tranHiResType;
    }

    public String getTranHiNetwork()
    {
        return tranHiNetwork;
    }

    public void setTranHiNetwork(String tranHiNetwork)
    {
        this.tranHiNetwork = tranHiNetwork;
    }

    public String getTranHiType()
    {
        return tranHiType;
    }

    public void setTranHiType(String tranHiType)
    {
        this.tranHiType = tranHiType;
    }

    public String getTranHiCreatedTs()
    {
        return tranHiCreatedTs;
    }

    public void setTranHiCreatedTs(String tranHiCreatedTs)
    {
        this.tranHiCreatedTs = tranHiCreatedTs;
    }

    public String getTranHiStatus()
    {
        return tranHiStatus;
    }

    public void setTranHiStatus(String tranHiStatus)
    {
        this.tranHiStatus = tranHiStatus;
    }

    public String getTranHiStatusRea()
    {
        return tranHiStatusRea;
    }

    public void setTranHiStatusRea(String tranHiStatusRea)
    {
        this.tranHiStatusRea = tranHiStatusRea;
    }

    public String getTranHiStatusTs()
    {
        return tranHiStatusTs;
    }

    public void setTranHiStatusTs(String tranHiStatusTs)
    {
        this.tranHiStatusTs = tranHiStatusTs;
    }

    public String getTranHiRetrieRef()
    {
        return tranHiRetrieRef;
    }

    public void setTranHiRetrieRef(String tranHiRetrieRef)
    {
        this.tranHiRetrieRef = tranHiRetrieRef;
    }

    public String getTranHiSysTraAudit()
    {
        return tranHiSysTraAudit;
    }

    public void setTranHiSysTraAudit(String tranHiSysTraAudit)
    {
        this.tranHiSysTraAudit = tranHiSysTraAudit;
    }

    public String getTranHiSwitSeriNum()
    {
        return tranHiSwitSeriNum;
    }

    public void setTranHiSwitSeriNum(String tranHiSwitSeriNum)
    {
        this.tranHiSwitSeriNum = tranHiSwitSeriNum;
    }

    public String getOriginalStatus()
    {
        return originalStatus;
    }

    public void setOriginalStatus(String originalStatus)
    {
        this.originalStatus = originalStatus;
    }

    public String getMastercardStatus()
    {
        return mastercardStatus;
    }

    public void setMastercardStatus(String mastercardStatus)
    {
        this.mastercardStatus = mastercardStatus;
    }

    public String getStatusTs()
    {
        return statusTs;
    }

    public void setStatusTs(String statusTs)
    {
        this.statusTs = statusTs;
    }

    public String getResponseCode()
    {
        return responseCode;
    }

    public void setResponseCode(String responseCode)
    {
        this.responseCode = responseCode;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorDesc()
    {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc)
    {
        this.errorDesc = errorDesc;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append("sequenceNo = [").append(sequenceNo).append("], ");
        result.append("id = [").append(id).append("], ");
        result.append("resourceType = [").append(resourceType).append("], ");
        result.append("paymentType = [").append(paymentType).append("], ");
        result.append("created = [").append(created).append("], ");
        result.append("tranHiId = [").append(tranHiId).append("], ");
        result.append("tranHiResType = [").append(tranHiResType).append("], ");
        result.append("tranHiNetwork = [").append(tranHiNetwork).append("], ");
        result.append("tranHiType = [").append(tranHiType).append("], ");
        result.append("tranHiCreatedTs = [").append(tranHiCreatedTs).append("], ");
        result.append("tranHiStatus = [").append(tranHiStatus).append("], ");
        result.append("tranHiStatusRea = [").append(tranHiStatusRea).append("], ");
        result.append("tranHiStatusTs = [").append(tranHiStatusTs).append("], ");
        result.append("tranHiRetrieRef = [").append(tranHiRetrieRef).append("], ");
        result.append("tranHiSysTraAudit = [").append(tranHiSysTraAudit).append("], ");
        result.append("tranHiSwitSeriNum = [").append(tranHiSwitSeriNum).append("], ");
        result.append("originalStatus = [").append(originalStatus).append("], ");
        result.append("mastercardStatus = [").append(mastercardStatus).append("], ");
        result.append("statusTs = [").append(statusTs).append("], ");
        result.append("responseCode = [").append(responseCode).append("], ");
        result.append("errorCode = [").append(errorCode).append("], ");
        result.append("errorDesc = [").append(errorDesc).append("]");
        
        return result.toString();
    }
}
