/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.mvisa;

/**
 *
 * @author minhndb
 */
public class ResponseMessage implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String transactionIdentifier;
    private String actionCode;
    private String approvalCode;
    private String responseCode;
    private String transmissionDateTime;
    private String retrievalReferenceNumber;
    private PurchaseIdentifier purchaseIdentifier;
    private String feeProgramIndicator;
    private String merchantCategoryCode;
    private CardAcceptor cardAcceptor;
    private String merchantVerificationValue;
    private String statusIdentifier;
    private String errorMessage;
    private String responseMessage;
    
    private ResponseStatus responseStatus;

    public ResponseMessage() {
        this.transactionIdentifier = "";
        this.actionCode = "";
        this.approvalCode = "";
        this.responseCode = "";
        this.transmissionDateTime = "";
        this.retrievalReferenceNumber = "";
        this.purchaseIdentifier = new PurchaseIdentifier();
        this.feeProgramIndicator = "";
        this.merchantCategoryCode = "";
        this.cardAcceptor = new CardAcceptor();
        this.merchantVerificationValue = "";
        this.statusIdentifier = "";
        this.responseStatus = new ResponseStatus();
    }

    public String getResponseMessage()
    {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage)
    {
        this.responseMessage = responseMessage;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getTransactionIdentifier()
    {
        return transactionIdentifier;
    }

    public void setTransactionIdentifier(String transactionIdentifier)
    {
        this.transactionIdentifier = transactionIdentifier;
    }

    public String getActionCode()
    {
        return actionCode;
    }

    public void setActionCode(String actionCode)
    {
        this.actionCode = actionCode;
    }

    public String getApprovalCode()
    {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode)
    {
        this.approvalCode = approvalCode;
    }

    public String getResponseCode()
    {
        return responseCode;
    }

    public void setResponseCode(String responseCode)
    {
        this.responseCode = responseCode;
    }

    public String getTransmissionDateTime()
    {
        return transmissionDateTime;
    }

    public void setTransmissionDateTime(String transmissionDateTime)
    {
        this.transmissionDateTime = transmissionDateTime;
    }

    public String getRetrievalReferenceNumber()
    {
        return retrievalReferenceNumber;
    }

    public void setRetrievalReferenceNumber(String retrievalReferenceNumber)
    {
        this.retrievalReferenceNumber = retrievalReferenceNumber;
    }

    public PurchaseIdentifier getPurchaseIdentifier()
    {
        return purchaseIdentifier;
    }

    public void setPurchaseIdentifier(PurchaseIdentifier purchaseIdentifier)
    {
        this.purchaseIdentifier = purchaseIdentifier;
    }

    public String getFeeProgramIndicator()
    {
        return feeProgramIndicator;
    }

    public void setFeeProgramIndicator(String feeProgramIndicator)
    {
        this.feeProgramIndicator = feeProgramIndicator;
    }

    public String getMerchantCategoryCode()
    {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode)
    {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public CardAcceptor getCardAcceptor()
    {
        return cardAcceptor;
    }

    public void setCardAcceptor(CardAcceptor cardAcceptor)
    {
        this.cardAcceptor = cardAcceptor;
    }

    public String getMerchantVerificationValue()
    {
        return merchantVerificationValue;
    }

    public void setMerchantVerificationValue(String merchantVerificationValue)
    {
        this.merchantVerificationValue = merchantVerificationValue;
    }

    public String getStatusIdentifier()
    {
        return statusIdentifier;
    }

    public void setStatusIdentifier(String statusIdentifier)
    {
        this.statusIdentifier = statusIdentifier;
    }
    
    public ResponseStatus getResponseStatus()
    {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus)
    {
        this.responseStatus = responseStatus;
    }
    
    public void setError(String code, String info, String message, String severity, String status)
    {
        this.responseStatus.setCode(code);
        this.responseStatus.setInfo(info);
        this.responseStatus.setMessage(message);
        this.responseStatus.setSeverity(severity);
        this.responseStatus.setStatus(status);
    }
}