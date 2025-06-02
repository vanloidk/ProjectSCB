/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.vnpayqr;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import scb.com.vn.message.CommonMessage.CommontEnum;
import scb.com.vn.ultility.Common;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "CheckQRRq")
public class CheckQRRq implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String clientId;
    private String bankCode;
    private String payType;
    private QRItem qr;
    private String payCode;
    private String checkSum;
    private String promotionCode;
    private String mobile;
    private String userName;
    private String accountNo;
    private String debitAmount;
    private String merchantId;
    private String partner;

    @XmlElement(name = "ClientId", nillable = true)
    public String getClientId() {
        return clientId;
    }

    @XmlElement(name = "BankCode", nillable = true)
    public String getBankCode() {
        return bankCode;
    }

    @XmlElement(name = "PayType", nillable = true)
    public String getPayType() {
        return payType;
    }

    @XmlElement(name = "Qr", nillable = false)
    public QRItem getQr() {
        return qr;
    }

    @XmlElement(name = "PayCode", nillable = true)
    public String getPayCode() {
        return payCode;
    }

    @XmlElement(name = "CheckSum", nillable = true)
    public String getCheckSum() {
        return checkSum;
    }

    @XmlElement(name = "PromotionCode", nillable = true)
    public String getPromotionCode() {
        return promotionCode;
    }

    @XmlElement(name = "Mobile", nillable = true)
    public String getMobile() {
        return mobile;
    }

    @XmlElement(name = "UserName", nillable = true)
    public String getUserName() {
        return userName;
    }

    @XmlElement(name = "AccountNo", nillable = false)
    public String getAccountNo() {
        return accountNo;
    }

    @XmlElement(name = "DebitAmount", nillable = false)
    public String getDebitAmount() {
        return debitAmount;
    }

    @XmlElement(name = "MerchantId", nillable = false)
    public String getMerchantId() {
        return merchantId;
    }

    @XmlElement(name = "Partner", nillable = false)
    public String getPartner() {
        return partner;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setQr(QRItem qr) {
        this.qr = qr;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public void setPartner(String partner) {
        this.partner = partner;
    }
    
    public CheckQRJson convertCheckQrJson() {
        CheckQRJson checkQRJson = new CheckQRJson();
        checkQRJson.setBankCode(bankCode);
        checkQRJson.setCheckSum(checkSum);
        checkQRJson.setDebitAmount(debitAmount);
        checkQRJson.setItem(qr.getQrItem());
        checkQRJson.setMobile(mobile);
        checkQRJson.setPayCode(payCode);
        checkQRJson.setPayType(payType);
        checkQRJson.setVoucherCode(promotionCode);
        return checkQRJson;
    }
    
    public String exportCheckQrJson() {
        CheckQRJson checkQRJson = convertCheckQrJson();
        return checkQRJson.exportToJsonString();
    }
    
    public void preData(String bankCode, String accessKey) {
        if (payType == null) {
            payType = "";
        }
        this.bankCode = bankCode;
        this.checkSum = Common.EncriptMD5(bankCode + payType + accessKey);
    }
    
    public CommontEnum isValidate() {
        if (!Common.validateStringValue(clientId, 20)) {
            return CommontEnum.INVALID_CLIENTID;
        }
        if (!Common.validateStringValue(bankCode, 6)) {
            return CommontEnum.INVALID_BANKCODE;
        }
        /*
        if (!Common.validateStringValue(payCode, 20)) {
            return CommontEnum.INVALID_PAYCODE;
        }*/
        if (!Common.validateStringValue(checkSum)) {
            return CommontEnum.INVALID_CHECKSUM;
        }
        if (!Common.validateStringValue(mobile, 15)) {
            return CommontEnum.INVALID_MOBILE;
        }
        if (!Common.validateStringValue(userName, 100)) {
            return CommontEnum.INVALID_USERNAME;
        }
        if (!Common.validateStringValue(accountNo, 20)) {
            return CommontEnum.INVALID_ACCOUNTNO;
        }
        if (!Common.validateStringValue(debitAmount, 18)) {
            return CommontEnum.INVALID_DEBITAMOUNT;
        }
        if (!Common.validateStringValue(merchantId, 200)) {
            return CommontEnum.INVALID_MERCHANTID;
        }
        if (!Common.validateStringValue(partner, 50)) {
            return CommontEnum.INVALID_PARTNER;
        }
        if (qr == null || qr.getQrItem().length == 0) {
            return CommontEnum.INVALID_ITEM;
        }
        for(QrInfo item : qr.getQrItem()) {
            if (!item.isValidate()) {
                return CommontEnum.INVALID_ITEM;
            }
        }
        
        return CommontEnum.DATA_IS_VALID;
    }
}