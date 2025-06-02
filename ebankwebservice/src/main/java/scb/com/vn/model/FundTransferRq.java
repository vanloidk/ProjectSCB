/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateRes;

/**
 *
 * @author KimNCM
 */
@XmlRootElement(name = "FundTransferRq")
public class FundTransferRq extends MobileRequest {

    private static final Logger logger = Logger.getLogger(FundTransferRq.class);

    private String Amount;
    /* Chi ho tro cho doi tac kieu hoi chuyen ngoai te ve VN */
    private BigDecimal amountConvert;
    private String TxnFee;
    private String TxnTax;
    private String FromAccount;
    private String ToAccount;
    private String Remark;
    private String BeneName;
    private String BeneTel;
    private String CityCode;
    private String BranchName;
    private String IDOpenDate;
    private String IDCityCode;
    private String BankCode;
    private String BranchCode;
    private String FinAmount;
    private String FinVatAmount;
    private String ProductCode;
    private String clientCode;
    private ExchangeRateRes resExchange;
    private String glAccount;
    private String channel;

    /**
     *
     */
    public FundTransferRq() {
    }

    /**
     *
     * @param transDetail
     */
    public FundTransferRq(scb.com.vn.common.model.transfer.TransactionDetail transDetail) {
        this.Amount = transDetail.getAmount();
        this.amountConvert = transDetail.getExchangeRateRes().getMoneyExchange();
        this.FromAccount = transDetail.getPartnerAccount();
        this.ToAccount = transDetail.getCustomerAccount();
        this.Remark = transDetail.getDescription();
        this.BeneName = transDetail.getCustomerName();
        this.BeneTel = transDetail.getMobileNo();
        this.CityCode = transDetail.getCityName();
        this.BranchName = transDetail.getBranch();
        this.IDOpenDate = transDetail.getOpenDate();
        this.IDCityCode = transDetail.getCityName();
        this.BankCode = transDetail.getBankCode();
        this.BranchCode = transDetail.getBranchCode();
        this.resExchange = transDetail.getExchangeRateRes();
        this.ProductCode = transDetail.getProduct();
        this.glAccount = transDetail.getGlAccount();
    }

    /**
     *
     * @param Amount
     * @param FromAccount
     * @param ToAccount
     * @param Remark
     * @param BeneName
     * @param BeneTel
     * @param CityCode
     * @param BranchName
     * @param IDOpenDate
     * @param IDCityCode
     * @param BankCode
     * @param BranchCode
     */
    public FundTransferRq(String Amount, String FromAccount, String ToAccount, String Remark, String BeneName, String BeneTel, String CityCode, String BranchName, String IDOpenDate, String IDCityCode, String BankCode, String BranchCode) {
        this.Amount = Amount;
        this.FromAccount = FromAccount;
        this.ToAccount = ToAccount;
        this.Remark = Remark;
        this.BeneName = BeneName;
        this.BeneTel = BeneTel;
        this.CityCode = CityCode;
        this.BranchName = BranchName;
        this.IDOpenDate = IDOpenDate;
        this.IDCityCode = IDCityCode;
        this.BankCode = BankCode;
        this.BranchCode = BranchCode;
    }

    /**
     * @return the Amount
     */
    @XmlElement(name = "Amount", nillable = true)
    public String getAmount() {
        return Amount;
    }

    /**
     * @param Amount the Amount to set
     */
    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    /**
     * @return the FromAccount
     */
    @XmlElement(name = "FromAccount", nillable = true)
    public String getFromAccount() {
        return FromAccount;
    }

    /**
     * @param FromAccount the FromAccount to set
     */
    public void setFromAccount(String FromAccount) {
        this.FromAccount = FromAccount;
    }

    /**
     * @return the ToAccount
     */
    @XmlElement(name = "ToAccount", nillable = true)
    public String getToAccount() {
        return ToAccount;
    }

    /**
     * @param ToAccount the ToAccount to set
     */
    public void setToAccount(String ToAccount) {
        this.ToAccount = ToAccount;
    }

    /**
     * @return the Remark
     */
    @XmlElement(name = "Remark", nillable = true)
    public String getRemark() {
        return Remark;
    }

    /**
     * @param Remark the Remark to set
     */
    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    /**
     * @return the BeneName
     */
    @XmlElement(name = "BeneName", nillable = true)
    public String getBeneName() {
        return BeneName;
    }

    /**
     * @param BeneName the BeneName to set
     */
    public void setBeneName(String BeneName) {
        this.BeneName = BeneName;
    }

    /**
     * @return the CityCode
     */
    @XmlElement(name = "CityCode", nillable = true)
    public String getCityCode() {
        return CityCode;
    }

    /**
     * @param CityCode the CityCode to set
     */
    public void setCityCode(String CityCode) {
        this.CityCode = CityCode;
    }

    /**
     * @return the BranchName
     */
    @XmlElement(name = "BranchName", nillable = true)
    public String getBranchName() {
        return BranchName;
    }

    /**
     * @param BranchName the BranchName to set
     */
    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }

    /**
     * @return the IDOpenDate
     */
    @XmlElement(name = "IDOpenDate", nillable = true)
    public String getIDOpenDate() {
        return IDOpenDate;
    }

    /**
     * @param IDOpenDate the IDOpenDate to set
     */
    public void setIDOpenDate(String IDOpenDate) {
        this.IDOpenDate = IDOpenDate;
    }

    /**
     * @return the IDCityCode
     */
    @XmlElement(name = "IDCityCode", nillable = true)
    public String getIDCityCode() {
        return IDCityCode;
    }

    /**
     * @param IDCityCode the IDCityCode to set
     */
    public void setIDCityCode(String IDCityCode) {
        this.IDCityCode = IDCityCode;
    }

    /**
     * @return the BankCode
     */
    @XmlElement(name = "BankCode", nillable = true)
    public String getBankCode() {
        return BankCode;
    }

    /**
     * @param BankCode the BankCode to set
     */
    public void setBankCode(String BankCode) {
        this.BankCode = BankCode;
    }

    /**
     * @return the TxnFee
     */
    @XmlElement(name = "TxnFee", nillable = true)
    public String getTxnFee() {
        return TxnFee;
    }

    /**
     * @param TxnFee the TxnFee to set
     */
    public void setTxnFee(String TxnFee) {
        this.TxnFee = TxnFee;
    }

    /**
     * @return the TxnTax
     */
    @XmlElement(name = "TxnTax", nillable = true)
    public String getTxnTax() {
        return TxnTax;
    }

    /**
     * @param TxnTax the TxnTax to set
     */
    public void setTxnTax(String TxnTax) {
        this.TxnTax = TxnTax;
    }

    /**
     * @return the BeneTel
     */
    @XmlElement(name = "BeneTel", nillable = true)
    public String getBeneTel() {
        return BeneTel;
    }

    /**
     * @param BeneTel the BeneTel to set
     */
    public void setBeneTel(String BeneTel) {
        this.BeneTel = BeneTel;
    }

    /**
     * @return the BranchCode
     */
    @XmlElement(name = "BranchCode", nillable = true)
    public String getBranchCode() {
        return BranchCode;
    }

    /**
     * @param BranchCode the BranchCode to set
     */
    public void setBranchCode(String BranchCode) {
        this.BranchCode = BranchCode;
    }

    /**
     * @return the FinAmount
     */
    @XmlElement(name = "FinAmount", nillable = true)
    public String getFinAmount() {
        return FinAmount;
    }

    /**
     * @param FinAmount the FinAmount to set
     */
    public void setFinAmount(String FinAmount) {
        this.FinAmount = FinAmount;
    }

    /**
     * @return the FinVatAmount
     */
    @XmlElement(name = "FinVatAmount", nillable = true)
    public String getFinVatAmount() {
        return FinVatAmount;
    }

    /**
     * @param FinVatAmount the FinVatAmount to set
     */
    public void setFinVatAmount(String FinVatAmount) {
        this.FinVatAmount = FinVatAmount;
    }

    /**
     * @return the ProductCode
     */
    @XmlElement(name = "ProductCode", nillable = true)
    public String getProductCode() {
        return ProductCode;
    }

    /**
     * @param ProductCode the ProductCode to set
     */
    public void setProductCode(String ProductCode) {
        this.ProductCode = ProductCode;
    }

    /**
     * @return the clientCode
     */
    @XmlElement(name = "clientCode", nillable = true)
    public String getClientCode() {
        return clientCode;
    }

    /**
     * @param clientCode the clientCode to set
     */
    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    /**
     *
     * @return
     */
    public ExchangeRateRes getResExchange() {
        return resExchange;
    }

    /**
     *
     * @param resExchange
     */
    public void setResExchange(ExchangeRateRes resExchange) {
        this.resExchange = resExchange;
    }

    /**
     *
     * @return
     */
    public String getGlAccount() {
        return glAccount;
    }

    /**
     *
     * @param glAccount
     */
    public void setGlAccount(String glAccount) {
        this.glAccount = glAccount;
    }

    /**
     *
     * @return
     */
    public BigDecimal getAmountConvert() {
        return amountConvert;
    }

    /**
     *
     * @param amountConvert
     */
    public void setAmountConvert(BigDecimal amountConvert) {
        this.amountConvert = amountConvert;
    }

    /**
     *
     * @param newPartern
     * @return
     */
    public String getIdOpenDate(String newPartern) {
        try {
            DateFormat oldP = new SimpleDateFormat("yyyyMMdd");
            DateFormat newP = new SimpleDateFormat(newPartern);
            Date date = oldP.parse(IDOpenDate);
            return newP.format(date);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public boolean isValidIDCityCode() {
        return IDCityCode != null && !IDCityCode.isEmpty();
    }

    /**
     *
     * @return
     */
    public boolean isValidIDOpenDate() {
        return IDOpenDate != null && !IDOpenDate.isEmpty();
    }

    /**
     *
     * @return
     */
    public String buildNarativeForTransferInternalId() {
        String narrative = getUserName();
        narrative = narrative.concat(" CT CHO ").concat(BeneName)
                .concat(", CMND: ").concat(ToAccount)
                .concat(", NC: ").concat(IDOpenDate);
        if (isValidIDCityCode()) {
            narrative = narrative.concat(", NC: ").concat(IDCityCode).concat(". ");
        }
        narrative = narrative.concat(Remark);
        if (narrative.length() > 225) {
            narrative = narrative.substring(0, 225);
        }
        return narrative;
    }

    /**
     * @return the channel
     */
    @XmlElement(name = "Channel", nillable = true)    
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }
}
