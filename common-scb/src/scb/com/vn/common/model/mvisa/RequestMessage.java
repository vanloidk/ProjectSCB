/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.mvisa;

import com.google.gson.Gson;

/**
 *
 * @author minhndb
 */
public class RequestMessage implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    private String acquirerCountryCode;
    private String acquiringBin;
    private String localTransactionDateTime;
    private String amount;
    private String transactionFeeAmt;
    private String businessApplicationId;
    private CardAcceptor cardAcceptor;
    private String merchantCategoryCode;
    private String feeProgramIndicator;
    private PointOfServiceData pointOfServiceData;
    private PurchaseIdentifier purchaseIdentifier;
    private String recipientName;
    private String recipientPrimaryAccountNumber;
    private String retrievalReferenceNumber;
    private String senderAccountNumber;
    private String senderAddress;
    private String senderCity;
    private String senderCountryCode;
    private String senderName;
    private String senderReference;
    private String senderStateCode;
    private String sourceOfFundsCode;
    private String systemsTraceAuditNumber;
    private String transactionCurrencyCode;
    private String transactionIdentifier;
    private String secondaryId;
    
    private Address address;
    
    public String getAcquirerCountryCode()
    {
        return acquirerCountryCode;
    }

    public void setAcquirerCountryCode(String acquirerCountryCode)
    {
        this.acquirerCountryCode = acquirerCountryCode;
    }

    public String getAcquiringBin()
    {
        return acquiringBin;
    }

    public void setAcquiringBin(String acquiringBin)
    {
        this.acquiringBin = acquiringBin;
    }

    public String getSecondaryId()
    {
        return secondaryId;
    }

    public void setSecondaryId(String secondaryId)
    {
        this.secondaryId = secondaryId;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getTransactionFeeAmt()
    {
        return transactionFeeAmt;
    }

    public void setTransactionFeeAmt(String transactionFeeAmt)
    {
        this.transactionFeeAmt = transactionFeeAmt;
    }

    public String getFeeProgramIndicator()
    {
        return feeProgramIndicator;
    }

    public void setFeeProgramIndicator(String feeProgramIndicator)
    {
        this.feeProgramIndicator = feeProgramIndicator;
    }

    public String getBusinessApplicationId()
    {
        return businessApplicationId;
    }

    public void setBusinessApplicationId(String businessApplicationId)
    {
        this.businessApplicationId = businessApplicationId;
    }

    public CardAcceptor getCardAcceptor()
    {
        return cardAcceptor;
    }

    public void setCardAcceptor(CardAcceptor cardAcceptor)
    {
        this.cardAcceptor = cardAcceptor;
    }

    public String getLocalTransactionDateTime()
    {
        return localTransactionDateTime;
    }

    public void setLocalTransactionDateTime(String localTransactionDateTime)
    {
        this.localTransactionDateTime = localTransactionDateTime;
    }

    public String getMerchantCategoryCode()
    {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode)
    {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public String getRecipientPrimaryAccountNumber()
    {
        return recipientPrimaryAccountNumber;
    }

    public void setRecipientPrimaryAccountNumber(String recipientPrimaryAccountNumber)
    {
        this.recipientPrimaryAccountNumber = recipientPrimaryAccountNumber;
    }

    public String getRetrievalReferenceNumber()
    {
        return retrievalReferenceNumber;
    }

    public void setRetrievalReferenceNumber(String retrievalReferenceNumber)
    {
        this.retrievalReferenceNumber = retrievalReferenceNumber;
    }

    public String getSenderAccountNumber()
    {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber)
    {
        this.senderAccountNumber = senderAccountNumber;
    }

    public String getSenderName()
    {
        return senderName;
    }

    public void setSenderName(String senderName)
    {
        this.senderName = senderName;
    }

    public String getSenderReference()
    {
        return senderReference;
    }

    public void setSenderReference(String senderReference)
    {
        this.senderReference = senderReference;
    }

    public String getSystemsTraceAuditNumber()
    {
        return systemsTraceAuditNumber;
    }

    public void setSystemsTraceAuditNumber(String systemsTraceAuditNumber)
    {
        this.systemsTraceAuditNumber = systemsTraceAuditNumber;
    }

    public String getTransactionCurrencyCode()
    {
        return transactionCurrencyCode;
    }

    public void setTransactionCurrencyCode(String transactionCurrencyCode)
    {
        this.transactionCurrencyCode = transactionCurrencyCode;
    }

    public String getTransactionIdentifier()
    {
        return transactionIdentifier;
    }

    public void setTransactionIdentifier(String transactionIdentifier)
    {
        this.transactionIdentifier = transactionIdentifier;
    }

    public String getRecipientName()
    {
        return recipientName;
    }

    public void setRecipientName(String recipientName)
    {
        this.recipientName = recipientName;
    }

    public String getSenderAddress()
    {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress)
    {
        this.senderAddress = senderAddress;
    }

    public String getSenderCity()
    {
        return senderCity;
    }

    public void setSenderCity(String senderCity)
    {
        this.senderCity = senderCity;
    }

    public String getSenderCountryCode()
    {
        return senderCountryCode;
    }

    public void setSenderCountryCode(String senderCountryCode)
    {
        this.senderCountryCode = senderCountryCode;
    }

    public String getSenderStateCode()
    {
        return senderStateCode;
    }

    public void setSenderStateCode(String senderStateCode)
    {
        this.senderStateCode = senderStateCode;
    }

    public String getSourceOfFundsCode()
    {
        return sourceOfFundsCode;
    }

    public void setSourceOfFundsCode(String sourceOfFundsCode)
    {
        this.sourceOfFundsCode = sourceOfFundsCode;
    }

    public PointOfServiceData getPointOfServiceData()
    {
        return pointOfServiceData;
    }

    public void setPointOfServiceData(PointOfServiceData pointOfServiceData)
    {
        this.pointOfServiceData = pointOfServiceData;
    }

    public PurchaseIdentifier getPurchaseIdentifier()
    {
        return purchaseIdentifier;
    }

    public void setPurchaseIdentifier(PurchaseIdentifier purchaseIdentifier)
    {
        this.purchaseIdentifier = purchaseIdentifier;
    }
    
    public String exportToJsonString()
    {
        Gson g = new Gson();
        return g.toJson(this);
    }
}