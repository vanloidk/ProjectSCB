/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.masterpass;

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
@XmlRootElement(name = "PayByQRCodeRq")
public class PayByQRCodeRq implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String sequenceNo;
    private String refNo;
    private String amount;
    private String ccy;
    private String ccyCode;
    private String dateTime;
    private String fundingS;
    private String loc4Digit;
    private String merchantId;
    private String merFName;
    private String merLName;
    private String merCategory;
    private String merCountrySub;
    private String merCardName;
    private String channel;
    private String provider;
    private String add_msg;
    
    private SenderMSVSCardInfo senderMSVSCardInfo;
    private DirectDebitRes directDebitRes = new DirectDebitRes();
    private CardAdjRes cardAdjRes = new CardAdjRes();
    private MCPaymentRp mastercardPaymentRp;
    private String status;
    
    private String coreRef;
    private String refundCoreRef;
    
    /* NOT NECCESSARY */
    private String merAddress;
    private String merCity;
    private String merCountry = "VNM";
    private String merPostalCode;
    private String merCardId = "";
    private String deviceId = "";
    private String merMName = "";
    

    @XmlElement(name = "MERCARDNAME", nillable = true)
    public String getMerCardName()
    {
        return merCardName;
    }

    public void setMerCardName(String merCardName)
    {
        this.merCardName = merCardName;
    }

    @XmlElement(name = "MERCARDID", nillable = true)
    public String getMerCardId() {
        return merCardId;
    }

    public void setMerCardId(String merCardId) {
        this.merCardId = merCardId;
    }

    @XmlElement(name = "DEVICEID", nillable = true)
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    

    @XmlElement(name = "PROVIDER", nillable = true)
    public String getProvider()
    {
        return provider;
    }

    public void setProvider(String provider)
    {
        this.provider = provider;
    }

    @XmlElement(name = "ADDITIONAL", nillable = true)
    public String getAdd_msg()
    {
        return add_msg;
    }

    public void setAdd_msg(String add_msg)
    {
        this.add_msg = add_msg;
    }
    
    @XmlElement(name = "CHANNEL", nillable = true)
    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }
    
    
    public String getSequenceNo()
    {
        return sequenceNo;
    }

    public void setSequenceNo(String sequenceNo)
    {
        this.sequenceNo = sequenceNo;
    }

    @XmlElement(name = "SEQUENCENO", nillable = true)
    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }
    
    

    @XmlElement(name = "FUNDINGSOURCE", nillable = true)
    public String getFundingS()
    {
        return fundingS;
    }

    public void setFundingS(String fundingS)
    {
        this.fundingS = fundingS;
    }

    @XmlElement(name = "LOC4DIGIT", nillable = true)
    public String getLoc4Digit()
    {
        return loc4Digit;
    }

    public void setLoc4Digit(String loc4Digit)
    {
        this.loc4Digit = loc4Digit;
    }

    @XmlElement(name = "AMOUNT", nillable = true)
    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }
    
    @XmlElement(name = "CCYCODE", nillable = true)
    public String getCcyCode()
    {
        return ccyCode;
    }

    public void setCcyCode(String ccyCode)
    {
        this.ccyCode = ccyCode;
    }

    @XmlElement(name = "CCY", nillable = true)
    public String getCcy()
    {
        return ccy;
    }

    public void setCcy(String ccy)
    {
        this.ccy = ccy;
    }

    @XmlElement(name = "MERCHANTID", nillable = true)
    public String getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(String merchantId)
    {
        this.merchantId = merchantId;
    }

    @XmlElement(name = "DATETIME", nillable = true)
    public String getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(String dateTime)
    {
        this.dateTime = dateTime;
    }

    @XmlElement(name = "MERFNAME", nillable = true)
    public String getMerFName()
    {
        return merFName;
    }

    public void setMerFName(String merFName)
    {
        this.merFName = merFName;
    }

    @XmlElement(name = "MERLNAME", nillable = true)
    public String getMerLName()
    {
        return merLName;
    }

    public void setMerLName(String merLName)
    {
        this.merLName = merLName;
    }

    @XmlElement(name = "MERMNAME", nillable = true)
    public String getMerMName()
    {
        return merMName;
    }

    public void setMerMName(String merMName)
    {
        this.merMName = merMName;
    }

    @XmlElement(name = "MERADDRESS", nillable = true)
    public String getMerAddress()
    {
        return merAddress;
    }

    public void setMerAddress(String merAddress)
    {
        this.merAddress = merAddress;
    }

    @XmlElement(name = "MERCITY", nillable = true)
    public String getMerCity()
    {
        return merCity;
    }

    public void setMerCity(String merCity)
    {
        this.merCity = merCity;
    }

    @XmlElement(name = "MERCOUNTRY", nillable = true)
    public String getMerCountry()
    {
        return merCountry;
    }

    public void setMerCountry(String merCountry)
    {
        this.merCountry = merCountry;
    }

    @XmlElement(name = "MERCOUNTRYSUB", nillable = true)
    public String getMerCountrySub()
    {
        return merCountrySub;
    }

    public void setMerCountrySub(String merCountrySub)
    {
        this.merCountrySub = merCountrySub;
    }

    @XmlElement(name = "MERPOSTALCODE", nillable = true)
    public String getMerPostalCode()
    {
        return merPostalCode;
    }

    public void setMerPostalCode(String merPostalCode)
    {
        this.merPostalCode = merPostalCode;
    }

    @XmlElement(name = "MERCATEGORY", nillable = true)
    public String getMerCategory()
    {
        return merCategory;
    }

    public void setMerCategory(String merCategory)
    {
        this.merCategory = merCategory;
    }

    public SenderMSVSCardInfo getSenderMSVSCardInfo() {
        return senderMSVSCardInfo;
    }

    public void setSenderMSVSCardInfo(SenderMSVSCardInfo senderMSVSCardInfo) {
        this.senderMSVSCardInfo = senderMSVSCardInfo;
    }

    public DirectDebitRes getDirectDebitRes() {
        return directDebitRes;
    }

    public void setDirectDebitRes(DirectDebitRes directDebitRes) {
        this.directDebitRes = directDebitRes;
    }

    public CardAdjRes getCardAdjRes() {
        return cardAdjRes;
    }

    public void setCardAdjRes(CardAdjRes cardAdjRes) {
        this.cardAdjRes = cardAdjRes;
    }

    public MCPaymentRp getMastercardPaymentRp() {
        return mastercardPaymentRp;
    }

    public void setMastercardPaymentRp(MCPaymentRp mastercardPaymentRp) {
        this.mastercardPaymentRp = mastercardPaymentRp;
    }

    public String getCoreRef() {
        return coreRef;
    }

    public void setCoreRef(String coreRef) {
        this.coreRef = coreRef;
    }

    public String getRefundCoreRef() {
        return refundCoreRef;
    }

    public void setRefundCoreRef(String refundCoreRef) {
        this.refundCoreRef = refundCoreRef;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public CommonMessage.CommontEnum validate() {
        if (!ValidateUtils.validateStringValue(provider)) {
            return CommonMessage.CommontEnum.INVALID_PROVIDER;
        }
        if (!ValidateUtils.validateStringValue(merCardName)) {
            return CommonMessage.CommontEnum.INVALID_MERCARDNAME;
        }
        if (!ValidateUtils.validateStringValue(fundingS)) {
            return CommonMessage.CommontEnum.INVALID_FUNDINGSOURCE;
        }
        if (!ValidateUtils.validateStringValue(channel)) {
            return CommonMessage.CommontEnum.INVALID_CHANNEL;
        }
        if (refNo == null || refNo.length() > 16) {
            return CommonMessage.CommontEnum.INVALID_REFERENCENO;
        }
        if (loc4Digit == null || loc4Digit.length() != 16) {
            return CommonMessage.CommontEnum.INVALID_LOC4DIGIT;
        }
        if(!ValidateUtils.isDouble(amount)) {
            return CommonMessage.CommontEnum.INVALID_AMOUNT;
        }
        if (merCountry == null || merCountry.length() != 3) {
            return CommonMessage.CommontEnum.INVALID_MERCOUNTRY;
        }
        if (ccy == null || ccy.length() != 3 || !"VND".equals(ccy)) {
            return CommonMessage.CommontEnum.INVALID_CCY;
        }
        if (ccyCode == null || ccyCode.length() != 3 || !"704".equals(ccyCode)) {
            return CommonMessage.CommontEnum.INVALID_CCYCODE;
        }
        if (!ValidateUtils.validateStringValue(merchantId) || merchantId.length() > 20) {
            return CommonMessage.CommontEnum.INVALID_MERID;
        }
        if (!ValidateUtils.isDateTime(dateTime)) {
            return CommonMessage.CommontEnum.INVALID_DATETIME;
        }
        if (!ValidateUtils.validateStringValue(merFName) || merFName.length() > 40) {
            return CommonMessage.CommontEnum.INVALID_MERFNAME;
        }
        if (!ValidateUtils.validateStringValue(merLName) || merLName.length() > 40) {
            return CommonMessage.CommontEnum.INVALID_MERLNAME;
        }
        if (!ValidateUtils.validateStringValue(merCategory) || merCategory.length() > 4) {
            return CommonMessage.CommontEnum.INVALID_MERCATEGORY;
        }
        if (senderMSVSCardInfo == null) {
            return CommonMessage.CommontEnum.INVALID_CWINFO;
        }
        if (!ValidateUtils.validateStringValue(senderMSVSCardInfo.getPan_clear()) || senderMSVSCardInfo.getPan_clear().length() != 16) {
            return CommonMessage.CommontEnum.INVALID_SENDERACC;
        }
        if (!ValidateUtils.validateStringValue(senderMSVSCardInfo.getFrist_name()) || senderMSVSCardInfo.getFrist_name().length() > 40) {
            return CommonMessage.CommontEnum.INVALID_SENDERFNAME;
        }
        if (!ValidateUtils.validateStringValue(senderMSVSCardInfo.getLast_name()) || senderMSVSCardInfo.getLast_name().length() > 16) {
            return CommonMessage.CommontEnum.INVALID_SENDERLNAME;
        }
        if (!ValidateUtils.validateStringValue(senderMSVSCardInfo.getHme_addr()) || senderMSVSCardInfo.getHme_addr().length() > 50) {
            return CommonMessage.CommontEnum.INVALID_SENDERADDRESS;
        }
        if (!ValidateUtils.validateStringValue(senderMSVSCardInfo.getHme_town()) || senderMSVSCardInfo.getHme_town().length() > 25) {
            return CommonMessage.CommontEnum.INVALID_SENDERCITY;
        }
        if (!ValidateUtils.validateStringValue(senderMSVSCardInfo.getHme_zip()) || senderMSVSCardInfo.getHme_zip().length() > 10) {
            return CommonMessage.CommontEnum.INVALID_SENDERPOSTALCODE;
        }
        if (!ValidateUtils.validateStringValue(senderMSVSCardInfo.getHme_cntry()) || senderMSVSCardInfo.getHme_cntry().length() > 3) {
            return CommonMessage.CommontEnum.INVALID_SENDERCOUNTRY;
        }
        if (!ValidateUtils.validateStringValue(senderMSVSCardInfo.getExpi_date()) || senderMSVSCardInfo.getExpi_date().length() > 10) {
            return CommonMessage.CommontEnum.INVALID_SENDEREXPIRE;
        }
        if (!ValidateUtils.validateStringValue(senderMSVSCardInfo.getBrch_cde()) || senderMSVSCardInfo.getBrch_cde().length() > 10) {
            return CommonMessage.CommontEnum.INVALID_CARDBRAND;
        }
        if (!ValidateUtils.validateStringValue(senderMSVSCardInfo.getSenderCountrySub()) || senderMSVSCardInfo.getSenderCountrySub().length() > 3) {
            return CommonMessage.CommontEnum.INVALID_SENDERCOUNTRYSUB;
        }
        if (!ValidateUtils.validateStringValue(add_msg) || add_msg.length() > 65) {
            return CommonMessage.CommontEnum.INVALID_ADDITIONAL_MSG;
        }
        if (!"MC".equalsIgnoreCase(senderMSVSCardInfo.getCreditType())) {
            return CommonMessage.CommontEnum.INVALID_CREDITCARD;
        }
        if (!"C".equalsIgnoreCase(senderMSVSCardInfo.getCardType()) && !"D".equalsIgnoreCase(senderMSVSCardInfo.getCardType())) {
            return CommonMessage.CommontEnum.INVALID_CARDTYPE;
        }
        
        return CommonMessage.CommontEnum.DATA_IS_VALID;
    }
}
