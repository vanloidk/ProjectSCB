/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer;

import java.util.List;

/**
 *
 * @author tamnt12
 */
public class TransferMoneyEbankReq {
    private String msgId;
    private String transDate;
    private String providerId;
    private String id;
    private String idMaster;
    private String txnDetailId;
    private String partnerAccount;
    private String customerName;
    private String customerAccount;
    private String mobileNo = "";
    private String branchCode = "";
    private String bankCode = "000000";
    private String branch = "";
    private String description = "";
    private String amount;
    private String ccy;
    private String typeCard = "ACCOUNT";
    private String addInfo;
    private String ADDINFO;
    private String scbDetailId = "0";
    private String responseCode;
    private String responseDesc;
    private ReceivingInfo receivingInfo;
    private SenderInfo senderInfo;
    private String cityName = "";
    private String openDate = "";
    private String typeTransfer = "01";
    private String typeFunction;
    private String typeCustAccount = "ACCOUNT";
    private String product;
    private String userId;
    private String glAccount;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(String idMaster) {
        this.idMaster = idMaster;
    }

    public String getTxnDetailId() {
        return txnDetailId;
    }

    public void setTxnDetailId(String txnDetailId) {
        this.txnDetailId = txnDetailId;
    }

    public String getPartnerAccount() {
        return partnerAccount;
    }

    public void setPartnerAccount(String partnerAccount) {
        this.partnerAccount = partnerAccount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(String typeCard) {
        this.typeCard = typeCard;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public String getADDINFO() {
        return ADDINFO;
    }

    public void setADDINFO(String ADDINFO) {
        this.ADDINFO = ADDINFO;
    }

    public String getScbDetailId() {
        return scbDetailId;
    }

    public void setScbDetailId(String scbDetailId) {
        this.scbDetailId = scbDetailId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public ReceivingInfo getReceivingInfo() {
        return receivingInfo;
    }

    public void setReceivingInfo(ReceivingInfo receivingInfo) {
        this.receivingInfo = receivingInfo;
    }

    public SenderInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(SenderInfo senderInfo) {
        this.senderInfo = senderInfo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getTypeTransfer() {
        return typeTransfer;
    }

    public void setTypeTransfer(String typeTransfer) {
        this.typeTransfer = typeTransfer;
    }

    public String getTypeFunction() {
        return typeFunction;
    }

    public void setTypeFunction(String typeFunction) {
        this.typeFunction = typeFunction;
    }

    public String getTypeCustAccount() {
        return typeCustAccount;
    }

    public void setTypeCustAccount(String typeCustAccount) {
        this.typeCustAccount = typeCustAccount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGlAccount() {
        return glAccount;
    }

    public void setGlAccount(String glAccount) {
        this.glAccount = glAccount;
    }

    public TransactionTypeEnum getTransactionType() {
        if (isTransferToInternalSCB()) {
            switch(this.typeCard) {
                case "CARD":
                    return TransactionTypeEnum.INTERNALCARD;
                case "IDNUMBER":
                    return TransactionTypeEnum.INTERNALCMND;
                case "ACCOUNT":
                default:
                    return TransactionTypeEnum.INTERNALACCOUNT;
            }
        } else {
            return TransactionTypeEnum.EXTERNALACCOUNT;
        }
    }
    
    public boolean isTransferToInternalSCB() {
        return "000000".equals(this.bankCode);
    }
    
    
    
    
    
}
