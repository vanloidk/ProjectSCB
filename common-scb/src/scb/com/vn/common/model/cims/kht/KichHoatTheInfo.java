/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.cims.kht;

import java.io.Serializable;
import java.util.Date;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cw.CardInfo;

/**
 *
 * @author minhndb
 */
public class KichHoatTheInfo implements Serializable {
    private static final Logger logger = Logger.getLogger(KichHoatTheInfo.class);
    private static final long serialVersionUID = 1L;
    
    private static final String DELIVERED = "1";
    private static final String NOTDELIVERED = "0";
    
    private String transId;
    private String requester;
    private String phoneNumber;
    private String reqMessage;
    private Date reqTime;
    
    private String resMessage;
    private String status;
    
    private String expiryDate = "";
    private String cmnd = "";
    private String cardNumber = "";
    private String cusName = "";
    private String cif = "";
    private String cardLimit = "";
    private String loc = "";
    private String cardType = "";
    private String branchCode = "";
    private String last4Digits = "";
    private String smsId = "";
    private boolean delivered = false;
    
    private String responseCode = "";
    private String responseDescription = "";
    private String sequence = "";
    
    

    public KichHoatTheInfo(String transId, String requester, String phoneNumber, String reqMessage, Date reqTime) {
        this.transId = transId;
        this.requester = requester;
        this.phoneNumber = phoneNumber;
        this.reqMessage = reqMessage;
        this.reqTime = reqTime;
    }
    
    public KichHoatTheInfo(String transId, String requester, String phoneNumber, String reqMessage, Date reqTime, String smsId) {
        this.transId = transId;
        this.requester = requester;
        this.phoneNumber = phoneNumber;
        this.reqMessage = reqMessage;
        this.reqTime = reqTime;
        this.smsId = smsId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReqMessage() {
        return reqMessage;
    }

    public void setReqMessage(String reqMessage) {
        this.reqMessage = reqMessage;
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(String cardLimit) {
        this.cardLimit = cardLimit;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public boolean isDelivered() {
        return delivered;
    }
    
    public String getDelivered() {
        return delivered ? DELIVERED : NOTDELIVERED;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getLast4Digits() {
        return last4Digits;
    }

    public void setLast4Digits(String last4Digits) {
        this.last4Digits = last4Digits;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    
    public boolean isEqualsCmnd(String cmnd) {
        return this.cmnd.equalsIgnoreCase(cmnd);
    }
    
    public boolean isEqualsLast4Digits(String last4Digits) {
        return this.last4Digits.equalsIgnoreCase(last4Digits);
    }
    
    public boolean isCardNeedToActive(String cmnd, String last4Digits) {
        boolean check = this.cmnd.equalsIgnoreCase(cmnd)
                && this.last4Digits.equalsIgnoreCase(last4Digits);
        logger.info("Check = [" + check + "], CMND -> [" + this.cmnd
                + "] - [" + cmnd + "], Digit -> [" + this.last4Digits
                + "] - [" + last4Digits + "]");
        return check;
    }
    
    public boolean isCardLikeToActive(String cmnd, String last4Digits) {
        boolean check = false;
        try {
            if (this.last4Digits.equalsIgnoreCase(last4Digits)) {
                String uncharCmndO = this.cmnd.replaceAll("\\D+","");
                String uncharCmnd = cmnd.replaceAll("\\D+","");
                check = uncharCmndO.equalsIgnoreCase(uncharCmnd);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        logger.info("Check = [" + check + "], CMND -> [" + this.cmnd
                + "] - [" + cmnd + "], Digit -> [" + this.last4Digits
                + "] - [" + last4Digits + "]");
        return check;
    }
    
    public void setupInfoBeforeExec(CardInfo info) {
        this.cardNumber = info.getPan_mask();
        this.cusName = info.getCustomerName();
        this.cif = info.getCif();
        this.cardLimit = info.getLocLimit();
        this.loc = info.getLoc();
        this.expiryDate = info.getExpiryDate();
        this.cardType = info.getCardType();
        this.branchCode = info.getBranchCode();
        this.delivered = info.isIsTransfered();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("transId=[").append(transId).append("], ");
        str.append("requester").append(requester).append("], ");
        str.append("phoneNumber").append(phoneNumber).append("], ");
        str.append("reqMessage").append(reqMessage).append("], ");
        str.append("reqTime").append(reqTime).append("], ");
        str.append("resMessage").append(resMessage).append("], ");
        str.append("expiryDate").append(expiryDate).append("], ");
        str.append("cmnd").append(cmnd).append("], ");
        str.append("cardNumber").append(cardNumber).append("], ");
        str.append("last4Digits").append(last4Digits).append("], ");
        str.append("cusName").append(cusName).append("], ");
        str.append("cif").append(cif).append("], ");
        str.append("cardLimit").append(cardLimit).append("], ");
        str.append("loc").append(loc).append("], ");
        str.append("cardType").append(cardType).append("], ");
        str.append("smsId").append(smsId).append("], ");
        str.append("branchCode").append(branchCode).append("], ");
        str.append("delivered").append(delivered).append("], ");
        str.append("status").append(status).append("]");
        str.append("responseCode").append(responseCode).append("]");
        str.append("responseDescription").append(responseDescription).append("]");
        str.append("sequence").append(sequence).append("]");
        
        
        return str.toString();
    }
}