/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.mvisa;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.common.model.cw.CardAdjRes;
import scb.com.vn.common.model.cw.DirectDebitRes;
import scb.com.vn.common.model.cw.SenderMSVSCardInfo;
import scb.com.vn.message.CommonMessage;
import scb.com.vn.ultility.ValidateUtils;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "MVISAQRRQ")
public class MVISAQRRQ implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String refNo;
    private String sequenceNo;
    private String sysTraceAuditNum;
    private String retrievalRefNum;
    private String loc4digit;
    private String merPrimaryAccNum;
    private String amount;
    private String localTransDate;
    private String merCategoryCode;
    private String acquirerCountryCode;
    private String transFeeAmt;
    private String feeProgramIndicator;
    private String acquiringBin;
    private String transCurrencyCode;
    private String secondaryId;
    private String businessAppID;
    private String senderRef;
    private String senderAccNum;
    private String senderExpDate;
    private String senderName;
    private String senderPhone;
    private String senderEmail;
    private String cardAccName;
    private String cardAccTerId;
    private String cardAccIdCode;
    private String cardAccCity;
    private String cardAccState;
    private String cardAccCounty;
    private String cardAccCountry;
    private String cardAccZipCode;
    private String PIType;
    private String PIRefNum;
    private String provider;

    private SenderMSVSCardInfo senderMSVSCardInfo;
    private DirectDebitRes directDebitRes = new DirectDebitRes();
    private CardAdjRes cardAdjRes = new CardAdjRes();
    private ResponseMessage visaResponse;
    private String status;

    private String coreRef;
    private String refundCoreRef;

    @XmlElement(name = "REFERENCENO", nillable = true)
    public String getRefNo() {
        return refNo;
    }

    @XmlElement(name = "SYSTRACEAUDITNUM", nillable = true)
    public String getSysTraceAuditNum() {
        return sysTraceAuditNum;
    }

    @XmlElement(name = "LOC4DIGIT", nillable = true)
    public String getLoc4digit() {
        return loc4digit;
    }

    @XmlElement(name = "SEQUENSENO", nillable = true)
    public String getSequenceNo() {
        return sequenceNo;
    }

    @XmlElement(name = "RETRIEVALREFNUM", nillable = true)
    public String getRetrievalRefNum() {
        return retrievalRefNum;
    }

    @XmlElement(name = "MERPRIMARYACCNUM", nillable = true)
    public String getMerPrimaryAccNum() {
        return merPrimaryAccNum;
    }

    @XmlElement(name = "FEEPROGRAMINDICATOR", nillable = true)
    public String getFeeProgramIndicator() {
        return feeProgramIndicator;
    }

    @XmlElement(name = "AMOUNT", nillable = true)
    public String getAmount() {
        return amount;
    }

    @XmlElement(name = "LOCALTRANSDATE", nillable = true)
    public String getLocalTransDate() {
        return localTransDate;
    }

    @XmlElement(name = "MERCATEGORYCODE", nillable = true)
    public String getMerCategoryCode() {
        return merCategoryCode;
    }

    @XmlElement(name = "ACQUIRERCOUNTRYCODE", nillable = true)
    public String getAcquirerCountryCode() {
        return acquirerCountryCode;
    }

    @XmlElement(name = "TRANSFEEAMT", nillable = true)
    public String getTransFeeAmt() {
        return transFeeAmt;
    }

    @XmlElement(name = "ACQUIRINGBIN", nillable = true)
    public String getAcquiringBin() {
        return acquiringBin;
    }

    @XmlElement(name = "TRANSCURRENCYCODE", nillable = true)
    public String getTransCurrencyCode() {
        return transCurrencyCode;
    }

    @XmlElement(name = "SECONDARYID", nillable = true)
    public String getSecondaryId() {
        return secondaryId;
    }

    @XmlElement(name = "BUSINESSAPPID", nillable = true)
    public String getBusinessAppID() {
        return businessAppID;
    }

    @XmlElement(name = "SENDERREF", nillable = true)
    public String getSenderRef() {
        return senderRef;
    }

    @XmlElement(name = "SENDERACCNUM", nillable = true)
    public String getSenderAccNum() {
        return senderAccNum;
    }

    @XmlElement(name = "SENDEREXPDATE", nillable = true)
    public String getSenderExpDate() {
        return senderExpDate;
    }

    @XmlElement(name = "SENDERNAME", nillable = true)
    public String getSenderName() {
        return senderName;
    }

    @XmlElement(name = "SENDERPHONE", nillable = true)
    public String getSenderPhone() {
        return senderPhone;
    }

    @XmlElement(name = "SENDEREMAIL", nillable = true)
    public String getSenderEmail() {
        return senderEmail;
    }

    @XmlElement(name = "CARDACCNAME", nillable = true)
    public String getCardAccName() {
        return cardAccName;
    }

    @XmlElement(name = "CARDACCTERID", nillable = true)
    public String getCardAccTerId() {
        return cardAccTerId;
    }

    @XmlElement(name = "CARDACCIDCODE", nillable = true)
    public String getCardAccIdCode() {
        return cardAccIdCode;
    }

    @XmlElement(name = "CARDACCCITY", nillable = true)
    public String getCardAccCity() {
        return cardAccCity;
    }

    @XmlElement(name = "CARDACCSTATE", nillable = true)
    public String getCardAccState() {
        return cardAccState;
    }

    @XmlElement(name = "CARDACCCOUNTY", nillable = true)
    public String getCardAccCounty() {
        return cardAccCounty;
    }

    @XmlElement(name = "CARDACCCOUNTRY", nillable = true)
    public String getCardAccCountry() {
        return cardAccCountry;
    }

    @XmlElement(name = "CARDACCZIPCODE", nillable = true)
    public String getCardAccZipCode() {
        return cardAccZipCode;
    }

    @XmlElement(name = "PITYPE", nillable = true)
    public String getPIType() {
        return PIType;
    }

    @XmlElement(name = "PIREFNUM", nillable = true)
    public String getPIRefNum() {
        return PIRefNum;
    }

    @XmlElement(name = "PROVIDER", nillable = true)
    public String getProvider() {
        return provider;
    }

    public SenderMSVSCardInfo getSenderMSVSCardInfo() {
        return senderMSVSCardInfo;
    }

    public DirectDebitRes getDirectDebitRes() {
        return directDebitRes;
    }

    public CardAdjRes getCardAdjRes() {
        return cardAdjRes;
    }

    public String getStatus() {
        return status;
    }

    public String getCoreRef() {
        return coreRef;
    }

    public String getRefundCoreRef() {
        return refundCoreRef;
    }

    public ResponseMessage getVisaResponse() {
        return visaResponse;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public void setSysTraceAuditNum(String sysTraceAuditNum) {
        this.sysTraceAuditNum = sysTraceAuditNum;
    }

    public void setLoc4digit(String loc4digit) {
        this.loc4digit = loc4digit;
    }

    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public void setRetrievalRefNum(String retrievalRefNum) {
        this.retrievalRefNum = retrievalRefNum;
    }

    public void setMerPrimaryAccNum(String merPrimaryAccNum) {
        this.merPrimaryAccNum = merPrimaryAccNum;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setLocalTransDate(String localTransDate) {
        this.localTransDate = localTransDate;
    }

    public void setMerCategoryCode(String merCategoryCode) {
        this.merCategoryCode = merCategoryCode;
    }

    public void setFeeProgramIndicator(String feeProgramIndicator) {
        this.feeProgramIndicator = feeProgramIndicator;
    }

    public void setAcquirerCountryCode(String acquirerCountryCode) {
        this.acquirerCountryCode = acquirerCountryCode;
    }

    public void setTransFeeAmt(String transFeeAmt) {
        this.transFeeAmt = transFeeAmt;
    }

    public void setAcquiringBin(String acquiringBin) {
        this.acquiringBin = acquiringBin;
    }

    public void setTransCurrencyCode(String transCurrencyCode) {
        this.transCurrencyCode = transCurrencyCode;
    }

    public void setSecondaryId(String secondaryId) {
        this.secondaryId = secondaryId;
    }

    public void setBusinessAppID(String businessAppID) {
        this.businessAppID = businessAppID;
    }

    public void setSenderRef(String senderRef) {
        this.senderRef = senderRef;
    }

    public void setSenderAccNum(String senderAccNum) {
        this.senderAccNum = senderAccNum;
    }

    public void setSenderExpDate(String senderExpDate) {
        this.senderExpDate = senderExpDate;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public void setCardAccName(String cardAccName) {
        this.cardAccName = cardAccName;
    }

    public void setCardAccTerId(String cardAccTerId) {
        this.cardAccTerId = cardAccTerId;
    }

    public void setCardAccIdCode(String cardAccIdCode) {
        this.cardAccIdCode = cardAccIdCode;
    }

    public void setCardAccCity(String cardAccCity) {
        this.cardAccCity = cardAccCity;
    }

    public void setCardAccState(String cardAccState) {
        this.cardAccState = cardAccState;
    }

    public void setCardAccCounty(String cardAccCounty) {
        this.cardAccCounty = cardAccCounty;
    }

    public void setCardAccCountry(String cardAccCountry) {
        this.cardAccCountry = cardAccCountry;
    }

    public void setCardAccZipCode(String cardAccZipCode) {
        this.cardAccZipCode = cardAccZipCode;
    }

    public void setPIType(String PIType) {
        this.PIType = PIType;
    }

    public void setPIRefNum(String PIRefNum) {
        this.PIRefNum = PIRefNum;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSenderMSVSCardInfo(SenderMSVSCardInfo senderMSVSCardInfo) {
        this.senderMSVSCardInfo = senderMSVSCardInfo;
    }

    public void setDirectDebitRes(DirectDebitRes directDebitRes) {
        this.directDebitRes = directDebitRes;
    }

    public void setCardAdjRes(CardAdjRes cardAdjRes) {
        this.cardAdjRes = cardAdjRes;
    }

    public void setCoreRef(String coreRef) {
        this.coreRef = coreRef;
    }

    public void setRefundCoreRef(String refundCoreRef) {
        this.refundCoreRef = refundCoreRef;
    }

    public void setVisaResponse(ResponseMessage visaResponse) {
        this.visaResponse = visaResponse;
    }

    public CommonMessage.CommontEnum validate() {
        if (!ValidateUtils.validateStringValue(refNo)) {
            return CommonMessage.CommontEnum.INVALID_REFNO;
        }
        if (!ValidateUtils.validateStringValue(merPrimaryAccNum)) {
            return CommonMessage.CommontEnum.INVALID_MERPRIMARYACCNUM;
        }
        if (!ValidateUtils.validateStringValue(amount)
                || !ValidateUtils.isDouble(amount)) {
            return CommonMessage.CommontEnum.INVALID_AMOUNT;
        }
        if (!ValidateUtils.validateStringValue(localTransDate)) {
            return CommonMessage.CommontEnum.INVALID_LOCALTRANSDATE;
        }
        if (!ValidateUtils.validateStringValue(acquirerCountryCode)
                || acquirerCountryCode.length() != 3
                || !"704".equals(acquirerCountryCode)
                || !ValidateUtils.isNumeric(acquirerCountryCode)) {
            return CommonMessage.CommontEnum.INVALID_ACQUIRERCOUNTRYCODE;
        }
        if (!ValidateUtils.validateStringValue(transCurrencyCode)
                || transCurrencyCode.length() != 3
                || !"VND".equals(transCurrencyCode)) {
            return CommonMessage.CommontEnum.INVALID_TRANSCURRENCYCODE;
        }
        if (!ValidateUtils.validateStringValue(provider)) {
            return CommonMessage.CommontEnum.INVALID_PROVIDER;
        }
        if (!ValidateUtils.validateStringValue(senderMSVSCardInfo.getPan_clear())) {
            return CommonMessage.CommontEnum.INVALID_PANCLEAR;
        }
        if (!ValidateUtils.validateStringValue(senderMSVSCardInfo.getCardName())) {
            return CommonMessage.CommontEnum.INVALID_CARDNAME;
        }
        if (!"VS".equalsIgnoreCase(senderMSVSCardInfo.getCreditType())) {
            return CommonMessage.CommontEnum.INVALID_CREDITCARD;
        }
        if (!"C".equalsIgnoreCase(senderMSVSCardInfo.getCardType()) && !"D".equalsIgnoreCase(senderMSVSCardInfo.getCardType())) {
            return CommonMessage.CommontEnum.INVALID_CARDTYPE;
        }

        return CommonMessage.CommontEnum.DATA_IS_VALID;
    }
}
