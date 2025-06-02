/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateRes;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "TRN_DETAIL")
public class TransactionDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    
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
    
    private ExchangeRateRes exchangeRateRes;

    @XmlElement(name = "TXNDETAILID")
    public String getTxnDetailId() {
        return txnDetailId;
    }

    @XmlElement(name = "PARTNERACCOUNT")
    public String getPartnerAccount() {
        return partnerAccount;
    }

    @XmlElement(name = "CUSTUMERNAME")
    public String getCustomerName() {
        return customerName;
    }

    @XmlElement(name = "CUSTUMERACCOUNT")
    public String getCustomerAccount() {
        return customerAccount;
    }

    @XmlElement(name = "MOBILENO")
    public String getMobileNo() {
        return mobileNo;
    }

    @XmlElement(name = "BRANCHCODE")
    public String getBranchCode() {
        return branchCode;
    }

    @XmlElement(name = "BANKCODE")
    public String getBankCode() {
        return bankCode;
    }

    @XmlElement(name = "BRANCH")
    public String getBranch() {
        return branch;
    }

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @XmlElement(name = "AMOUNT")
    public String getAmount() {
        return amount;
    }
    
    public Double getAmountDoubleType() {
        return Double.valueOf(this.amount);
    }
    
    public BigDecimal getAmountBigDecimalType() {
        return new BigDecimal(this.amount);
    }

    @XmlElement(name = "CCY")
    public String getCcy() {
        return ccy;
    }

    @XmlElement(name = "TYPECARD")
    public String getTypeCard() {
        return typeCard;
    }

    @XmlElement(name = "AddInfo")
    public String getAddInfo() {
        return addInfo;
    }

    @XmlElement(name = "ADDINFO")
    public String getADDINFO() {
        return ADDINFO;
    }

    @XmlElement(name = "RECEIVINGINFO")
    public ReceivingInfo getReceivingInfo() {
        return receivingInfo;
    }

    @XmlElement(name = "SENDERINFO")
    public SenderInfo getSenderInfo() {
        return senderInfo;
    }
    @XmlElement(name = "SCBDETAILID")
    public String getScbDetailId() {
        return scbDetailId;
    }
    
    public int getScbDetailIdIntegerType() {
        return Integer.valueOf(this.scbDetailId);
    }

    @XmlElement(name = "RESPONSECODE")
    public String getResponseCode() {
        return responseCode;
    }
    
    @XmlElement(name = "RESPONSEDESC")
    public String getResponseDesc() {
        return responseDesc;
    }

    @XmlElement(name = "ISSUEDPLACE")
    public String getCityName() {
        return cityName;
    }

    @XmlElement(name = "ISSUEDDATE")
    public String getOpenDate() {
        return openDate;
    }

    @XmlElement(name = "TRANSACTIONTYPE")
    public String getTypeTransfer() {
        return typeTransfer;
    }

    public String getTypeFunction() {
        return typeFunction;
    }

    public String getTypeCustAccount() {
        return typeCustAccount;
    }

    public String getIdMaster() {
        return idMaster;
    }

    public ExchangeRateRes getExchangeRateRes() {
        return exchangeRateRes;
    }

    public String getProduct() {
        return product;
    }

    public String getUserId() {
        return userId;
    }

    public String getGlAccount() {
        return glAccount;
    }
    
    public void setTxnDetailId(String txnDetailId) {
        this.txnDetailId = txnDetailId;
    }

    public void setPartnerAccount(String partnerAccount) {
        this.partnerAccount = partnerAccount;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public void setTypeCard(String typeCard) {
        this.typeCard = typeCard;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public void setADDINFO(String ADDINFO) {
        this.ADDINFO = ADDINFO;
    }

    public void setScbDetailId(String scbDetailId) {
        this.scbDetailId = scbDetailId;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    
    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public void setReceivingInfo(ReceivingInfo receivingInfo) {
        this.receivingInfo = receivingInfo;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public void setTypeTransfer(String typeTransfer) {
        this.typeTransfer = typeTransfer;
    }

    public void setTypeFunction(String typeFunction) {
        this.typeFunction = typeFunction;
    }

    public void setTypeCustAccount(String typeCustAccount) {
        this.typeCustAccount = typeCustAccount;
    }

    public void setIdMaster(String idMaster) {
        this.idMaster = idMaster;
    }

    public void setExchangeRateRes(ExchangeRateRes exchangeRateRes) {
        this.exchangeRateRes = exchangeRateRes;
    }
    
    public void setDefaultExchangeRateRes() {
        this.exchangeRateRes = new ExchangeRateRes(this.getAmountBigDecimalType(), BigDecimal.ONE);
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setGlAccount(String glAccount) {
        this.glAccount = glAccount;
    }
    
    public void setSenderInfo(SenderInfo senderInfo) {
        this.senderInfo = senderInfo;
    }
    public void setupForOldPartner() {
        String addinfo = (this.ADDINFO != null) ? this.ADDINFO : this.addInfo;
        if (addinfo != null && !addinfo.trim().isEmpty()) {
            addinfo = addinfo.replace("|", "#");
            String[] parts = addinfo.split("#", -1);
            
            if ("01".equals(this.typeTransfer) && !"01".equals(parts[0])) {
                this.typeTransfer = parts[0];
            }

            if(this.cityName.isEmpty() && parts.length > 1) {
                this.cityName = parts[1];
            }

            if(this.openDate.isEmpty() && parts.length > 2) {
                this.openDate = parts[2];
            }
        }
        
        setupTypeFunction();
        setupTypeCustAccount();
    }
    
    private void setupTypeFunction() {
        typeFunction = isTransferToInternalSCB() ? "01" : "02";
    }
    
    private void setupTypeCustAccount() {
        if("02".equals(this.typeTransfer) && !"IDNUMBER".equals(this.typeCard)) {
            this.typeCard = "IDNUMBER";
        }
        switch (this.typeCard) {
            case "CARD":
                typeCustAccount = "01";
                break;
            case "ACCOUNT":
                typeCustAccount = "02";
                break;
            case "IDNUMBER":
                typeCustAccount = "03";
                break;
            default:
                typeCustAccount = this.typeCard;
                break;
        }
    }
    
    public boolean isVND() {
        return "VND".equals(this.ccy);
    }
    
    public boolean isTransferToInternalSCB() {
        return "000000".equals(this.bankCode);
    }
    
    public boolean isTransferByAccount() {
        return "ACCOUNT".equals(this.typeCard);
    }
    
    public boolean isTransferByCmnd() {
        return "IDNUMBER".equals(this.typeCard);
    }
    
    public boolean isTransferByCardNumber() {
        return "CARD".equals(this.typeCard);
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
    
    public boolean isNotVnd() {
        return !"VND".equals(ccy);
    }
    
    public boolean isVnd() {
        return "VND".equals(ccy);
    }
    
    public void clearBeforeResponse() { 
        this.partnerAccount = null;
        this.customerName = null;
        this.customerAccount = null;
        this.bankCode = null;
        this.branch = null;
        this.amount = null;
        this.ccy = null;
        this.addInfo = null;
        this.typeCard = null;
        this.mobileNo = null;
        this.branchCode = null;
        this.ADDINFO = null;
        this.receivingInfo = null;
        this.senderInfo = null;
        this.cityName = null;
        this.openDate = null;
        this.typeTransfer = null;
        this.typeFunction = null;
        this.typeCustAccount = null;
        this.product = null;
        this.userId = null;
        this.exchangeRateRes = null;
        this.glAccount = null;
    }
}
