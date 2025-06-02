/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.common.model.transfer.napas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateRes;
import scb.com.vn.ultility.Common;
import scb.com.vn.ultility.RsaUtils;
import scb.com.vn.ultility.rsa.EncodingType;
import scb.com.vn.ultility.rsa.RsaAlgorithm;

/**
 *
 * @author minhndb
 */
public class TransferMoney247DetailReq implements Serializable {

    private static final Logger logger = Logger.getLogger(TransferMoney247DetailReq.class);
    private static final long serialVersionUID = 1L;

    private String bankTransId;
    private String fromNumber; // so tai khoan cat tien
    private String typeFromNumber; // hardcode tai khoan
    private String fullName; // ProviderId + "#" + "VIETNAM"
    private String toNumber; // so tai khoan nhan tien
    private String typeToNumber; // tai khoan nhan tien la so the hay tai khoan
    private String benId; // ma ngan hang cua tai khoan nhan tien
    private BigDecimal amount; // so tien chuyen khoan
    private String ccy;
    /* Code cu ko dung bien CCY */ // loai hinh so tien chuyen khoan
    private String description; // mo ta thong tin chuyen khoan
    private String termId; // hardcode = 11111111
    private String cardAcceptor; // hardcode = SML INTERNET BANKING      HCM        VNM
    private String typeFunction; // query hay la transaction
    private final Date transDate = new Date();

    private String product; // ma san pham
    private String userIdFcubs; // ma fcubs.userid duoc cau hinh trong file configuration

    private String merchantType; // hardcode ma 7399 cho kenh EBANK
    private String cif; // so cif
    private String accountClass; // class cua tai khoan
    private BigDecimal availableBalance; // so tien kha dung co trong tai khoan
    private String processingCode; // dau so cho cac loai hinh khi goi qua NAPAS
    private String branchCust; // branch code cua tai khoan cat tien
    private String napasBranch = "000";
    private String napasAccount; // so tai khoan treo cua NAPAS

    private ExchangeRateRes exchangeRateRes;

    public TransferMoney247DetailReq() {
    }

    public TransferMoney247DetailReq(String fromNumber, String typeFromNumber, String fullName,
            String toNumber, String typeToNumber, String benId, BigDecimal amount, String ccy,
            String description, String termId, String cardAcceptor, String typeFunction) {
        this.fromNumber = fromNumber;
        this.typeFromNumber = typeFromNumber;
        this.fullName = fullName;
        this.toNumber = toNumber;
        this.typeToNumber = typeToNumber;
        this.benId = benId;
        this.amount = amount;
        this.ccy = ccy;
        this.description = description;
        this.termId = termId;
        this.cardAcceptor = cardAcceptor;
        this.typeFunction = typeFunction;
    }

    public String getBankTransId() {
        return bankTransId;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public String getTypeFromNumber() {
        return typeFromNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getToNumber() {
        return toNumber;
    }

    public String getTypeToNumber() {
        return typeToNumber;
    }

    public String getBenId() {
        return benId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAmountStringType() {
        return amount.toString();
    }

    public String getAmountToSendNapas() {
        String result;
        if ("QUE".equals(typeFunction)) {
            result = "000000000000";
        } else {
            DecimalFormat formatter = new DecimalFormat("0.00");
            result = Common.addExtraChar(
                    formatter.format(this.amount).replace(".", "").replace(",", ""), 12, "0");
        }
        return result;
    }

    public String getCcy() {
        return ccy;
    }

    public String getTermId() {
        return termId;
    }

    public String getCardAcceptor() {
        return cardAcceptor;
    }

    public String getTypeFunction() {
        return typeFunction;
    }

    public Date getTransDate() {
        return transDate;
    }

    public String getTransDate(String partern) {
        SimpleDateFormat formatter;
        try {
            formatter = new SimpleDateFormat(partern);
        } catch (Exception e) {
            logger.error(e);
            formatter = new SimpleDateFormat("MMddHHmmss");
        }
        return formatter.format(this.transDate);
    }

    public String getDescription() {
        return description;
    }

    public String getProduct() {
        return product;
    }

    public String getUserIdFcubs() {
        return userIdFcubs;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public String getCif() {
        return cif;
    }

    public String getAccountClass() {
        return accountClass;
    }

    public String getSubAccountClass() {
        if (accountClass != null && accountClass.length() >= 3) {
            return accountClass.substring(0, 3);
        } else {
            logger.warn("accountClass = [" + accountClass + "] is invalid when getting substring (0,3)");
            return "";
        }
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public String getProcessingCode() {
        return processingCode;
    }

    public String getBranchCust() {
        return branchCust;
    }

    public String getNapasAccount() {
        return napasAccount;
    }

    public String getNapasBranch() {
        return napasBranch;
    }

    public ExchangeRateRes getExchangeRateRes() {
        return exchangeRateRes;
    }

    public void setBankTransId(String bankTransId) {
        this.bankTransId = bankTransId;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public void setTypeFromNumber(String typeFromNumber) {
        this.typeFromNumber = typeFromNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    public void setTypeToNumber(String typeToNumber) {
        this.typeToNumber = typeToNumber;
    }

    public void setBenId(String benId) {
        this.benId = benId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public void setCardAcceptor(String cardAcceptor) {
        this.cardAcceptor = cardAcceptor;
    }

    public void setTypeFunction(String typeFunction) {
        this.typeFunction = typeFunction;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setProductWithCheckEmpty(String product) {
        if (product.isEmpty()) {
            this.product = product;
        }
    }

    public void setUserIdFcubs(String userIdFcubs) {
        this.userIdFcubs = userIdFcubs;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setAccountClass(String accountClass) {
        this.accountClass = accountClass;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public void setProcessingCode(String processingCode) {
        this.processingCode = processingCode;
    }

    public void setBranchCust(String branchCust) {
        this.branchCust = branchCust;
    }

    public void setNapasAccount(String napasAccount) {
        this.napasAccount = napasAccount;
    }

    public void setNapasBranch(String napasBranch) {
        this.napasBranch = napasBranch;
    }

    public void setExchangeRateRes(ExchangeRateRes exchangeRateRes) {
        this.exchangeRateRes = exchangeRateRes;
    }

    public String getDateRefNo() {
        String refNoF37 = "";
        try {
            SimpleDateFormat fDateF37 = new SimpleDateFormat("DDDHH");
            SimpleDateFormat fDateF37Y = new SimpleDateFormat("Y");
            refNoF37 = fDateF37Y.format(this.transDate).substring(3, 4) + fDateF37.format(this.transDate);
        } catch (Exception e) {
            logger.error(e);
        }
        return refNoF37;
    }

    public String getDateRefNo(String auditNumber) {
        String refNoF37 = "";
        try {
            SimpleDateFormat fDateF37 = new SimpleDateFormat("DDDHH");
            SimpleDateFormat fDateF37Y = new SimpleDateFormat("Y");
            refNoF37 = fDateF37Y.format(this.transDate).substring(3, 4)
                    + fDateF37.format(this.transDate) + auditNumber;
        } catch (Exception e) {
            logger.error(e);
        }
        return refNoF37;
    }

    public void setupProcessingCode() {
        if ("QUE".equals(typeFunction)) {
            if ("CARD".equals(typeToNumber)) {
                processingCode = "430000";
            } else {
                processingCode = "CARD".equals(typeFromNumber) ? "430020" : "432020";
            }
        } else {
            if ("CARD".equals(typeToNumber)) {
                processingCode = "910000";
            } else {
                processingCode = "CARD".equals(typeFromNumber) ? "910020" : "912020";
            }
        }
    }

    private boolean dataIsValid() {
        boolean result = !fromNumber.isEmpty()
                && !typeFromNumber.isEmpty()
                && !toNumber.isEmpty()
                && !typeToNumber.isEmpty()
                && !typeFunction.isEmpty();
        if (!result) {
            logger.warn("data is invalid --> fromNumber = [" + fromNumber + "], typeFromNumber = [" + typeFromNumber
                    + "], toNumber = [" + toNumber + "], typeToNumber = [" + typeToNumber + "], typeFunction = [" + typeFunction + "]");
        }
        return result;
    }

    private boolean benIdIsValid() {
        if ("ACCOUNT".equals(typeToNumber)) {
            if (benId.isEmpty()) {
                logger.warn("typeToNumber: " + typeToNumber + ", benId is empty");
                return false;
                /* BenId is invalid */
            }
        }
        return true;
    }

    private boolean amountIsValid() {
        /* Kiem tra lai tinh hop ly cho nay vi so tien phai la so duong va > 0 */
        boolean result = amount.compareTo(new BigDecimal(BigInteger.ZERO)) != -1;
        if (!result) {
            logger.warn("amount = [" + amount + "] is invalid");
        }
        return result;
    }

    private boolean typeFunctionIsValid() {
        boolean result = false;
        switch (typeFunction) {
            case "QUE":
            case "TRN":
                result = true;
                break;
            default:
                /* Sai TypeFunction */
                logger.warn("typeFunction: " + typeFunction);
                break;
        }
        return result;
    }

    private boolean typeFromNumberIsValid() {
        boolean result = false;
        switch (typeFromNumber) {
            case "ACCOUNT":
            case "CARD":
                result = true;
                break;
            default:
                /* Sai TypeNumber */
                logger.warn("typeFromNumber: " + typeFromNumber);
                break;
        }
        return result;
    }

    private boolean typeToNumberIsValid() {
        boolean result = false;
        switch (typeToNumber) {
            case "ACCOUNT":
            case "CARD":
                result = true;
                break;
            default:
                /* Sai TypeNumber */
                logger.warn("typeToNumber: " + typeToNumber);
                break;
        }
        return result;
    }

    public boolean isValid() {
        return dataIsValid()
                && benIdIsValid()
                && amountIsValid()
                && typeFunctionIsValid()
                && typeFromNumberIsValid()
                && typeToNumberIsValid();
    }

    private String buildSignature(String auditNumber, PrivateKey privateKey, String acquiringCode,
            String transdate, String amount) throws Exception {
        StringBuilder data = new StringBuilder();
        data.append("0200").append(fromNumber).append(processingCode).append(amount)
                .append(transdate).append(auditNumber).append(merchantType)
                .append(acquiringCode).append(termId).append(toNumber);
        if ("ACCOUNT".equals(typeToNumber)) {
            data.append(benId);
        }
        return RsaUtils.makeSignature(data.toString(), privateKey,
                RsaAlgorithm.MD5RSA, EncodingType.BASE64);
    }

    public String buildMessageToCallNapas(String auditNumber, PrivateKey privateKey,
            String acquiringCode, String transdate, String refNoF37) throws Exception {
        StringBuilder message = new StringBuilder();
        String amountFormat = this.getAmountToSendNapas();
        String signature = buildSignature(auditNumber, privateKey, acquiringCode, transdate, amountFormat);
        message.append("0200")
                .append("|").append(fromNumber)
                .append("|").append(processingCode)
                .append("|").append(amountFormat)
                .append("|").append(transdate)
                .append("|").append(auditNumber)
                .append("|").append(merchantType)
                .append("|").append(acquiringCode)
                .append("|").append(termId)
                .append("|").append(cardAcceptor)
                .append("|").append(toNumber)
                .append("|").append(description);
        if ("ACCOUNT".equals(typeToNumber)) {
            message.append("|").append(benId);
        }
        return message.append("|").append(refNoF37).append("||").append(signature).toString();
    }

    public boolean isTransReq() {
        return "TRN".equals(typeFunction);
    }

    public boolean isQueryReq() {
        return "QUE".equals(typeFunction);
    }

    public boolean isNotVnd() {
        return !"VND".equals(ccy);
    }

    public boolean isVnd() {
        return "VND".equals(ccy);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        
        str.append("bankTransId = [").append(bankTransId).append("], ");
        str.append("fromNumber = [").append(fromNumber).append("], ");
        str.append("typeFromNumber = [").append(typeFromNumber).append("], ");
        str.append("fullName = [").append(fullName).append("], ");
        str.append("toNumber = [").append(toNumber).append("], ");
        str.append("typeToNumber = [").append(typeToNumber).append("], ");
        str.append("benId = [").append(benId).append("], ");
        str.append("amount = [").append(amount).append("], ");
        str.append("ccy = [").append(ccy).append("], ");
        str.append("description = [").append(description).append("], ");
        str.append("termId = [").append(termId).append("], ");
        str.append("cardAcceptor = [").append(cardAcceptor).append("], ");
        str.append("typeFunction = [").append(typeFunction).append("], ");
        str.append("transDate = [").append(transDate).append("], ");
        str.append("product = [").append(product).append("], ");
        str.append("userIdFcubs = [").append(userIdFcubs).append("], ");
        str.append("merchantType = [").append(merchantType).append("], ");
        str.append("accountClass = [").append(accountClass).append("], ");
        str.append("availableBalance = [").append(availableBalance).append("], ");
        str.append("processingCode = [").append(processingCode).append("], ");
        str.append("branchCust = [").append(branchCust).append("], ");
        str.append("napasBranch = [").append(napasBranch).append("], ");
        str.append("napasAccount = [").append(napasAccount).append("], ");
        str.append("exchangeRateRes = [").append(exchangeRateRes).append("]");
        return str.toString();
    }
}
