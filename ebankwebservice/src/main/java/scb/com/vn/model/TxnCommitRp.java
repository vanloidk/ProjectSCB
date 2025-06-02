/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import vn.com.scb.bian.service.IIBCurrentAccountService;
import scb.com.vn.message.Message;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.MessageMB;
import vn.com.scb.bian.AccountInfoType;
import vn.com.scb.bian.RetrieveCurrentAccountCASA_out_Type;
import vn.com.scb.bian.utility.IIBConstants;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name = "TxnCommitRp")
public class TxnCommitRp extends MobileResponse {

    private String TxnType;
    private String CifNo;
    private String TxnID;
    private String TxnCommitTime;
    private char ReloadAccList;
    private String numbalance;
    private String availablebalance;
    private String AccountNo;
    private String azbalance;
    private String cardbalance;
    private String dunogocconlai;
    private String PrepaidFee;
    private char ReloadCasaAccList;
    private String dunodasudung;

    private String cardInstallmentAmountPre;
    private String cardMinimumInstallmentAmountPre;
    private String cardPaidCurrentAmountPre;
    private String cardInstallmentDatePre;
    private String cardProduct;
    private String ngaytttoithieu;
    private String sumPrincipalToPay;
    private String sumAmtLimitPay;
    private String sumPaidAmount;
    private String sumCardBalance;
    private String TxnIdPartner;

    

    /**
     *
     */
    public TxnCommitRp() {
        ReloadAccList = 0;
        ReloadCasaAccList = 0;
    }

    /**
     * @return the TxnID
     */
    @XmlElement(name = "TxnID", nillable = true)
    public String getTxnID() {
        return TxnID;
    }

    /**
     * @param TxnID the TxnID to set
     */
    public void setTxnID(String TxnID) {
        this.TxnID = TxnID;
    }

    /**
     * @return the TxnCommitTime
     */
    @XmlElement(name = "TxnCommitTime", nillable = true)
    public String getTxnCommitTime() {
        return TxnCommitTime;
    }

    /**
     * @param TxnCommitTime the TxnCommitTime to set
     */
    public void setTxnCommitTime(String TxnCommitTime) {
        this.TxnCommitTime = TxnCommitTime;
    }

    /**
     * @return the TxnType
     */
    @XmlElement(name = "TxnType", nillable = true)
    public String getTxnType() {
        return TxnType;
    }

    /**
     * @param TxnType the TxnType to set
     */
    public void setTxnType(String TxnType) {
        this.TxnType = TxnType;
    }

    /**
     * @return the CifNo
     */
    @XmlElement(name = "CifNo", nillable = true)
    public String getCifNo() {
        return CifNo;
    }

    /**
     * @param CifNo the CifNo to set
     */
    public void setCifNo(String CifNo) {
        this.CifNo = CifNo;
    }

    /**
     * @return the numbalance
     */
    @XmlElement(name = "AccountBalance", nillable = true)
    public String getNumbalance() {
        return numbalance;
    }

    /**
     * @param numbalance the numbalance to set
     */
    public void setNumbalance(String numbalance) {
        this.numbalance = numbalance;
    }

    /**
     * @return the AccountNo
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAccountNo() {
        return AccountNo;
    }

    /**
     * @param AccountNo the AccountNo to set
     */
    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    /**
     * @return the ReloadAccList
     */
    @XmlElement(name = "ReloadAccList", nillable = true)
    public char getReloadAccList() {
        return ReloadAccList;
    }

    /**
     * @param ReloadAccList the ReloadAccList to set
     */
    public void setReloadAccList(char ReloadAccList) {
        this.ReloadAccList = ReloadAccList;
    }

    /**
     *
     * @param accountNo
     * @return
     */
    public String GetBalanceAccountAfterTran(String accountNo) {
        try {
            List custacc = Helper.getDBI().getSttmCustAccountSyn(accountNo);
            if (custacc == null || custacc.isEmpty()) {
                return null;
            } else {
                HashMap hm_Casa = (HashMap) custacc.get(0);
                //return hm_Casa.get("acy_curr_balance").toString();
                return hm_Casa.get("acycurrbalance").toString();
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param msg
     * @return
     */
    public MobileResponse getMBFinResponse(Message.PaymentResultEnum msg) {
        this.setErrorCode(msg.getValue());
        this.setErrorMsg(Message.getMessagePaymentResult(msg));
        try {
            IIBCurrentAccountService iibAccService = new IIBCurrentAccountService();
            RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASA = iibAccService.retrieveCurrentAccountCASARestSimple(Configuration.getIIBContext(), this.getAccountNo(), IIBConstants.CHANNEL_MOBILE);
            if (retrieveCurrentAccountCASA.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                AccountInfoType accountdetailIIB = retrieveCurrentAccountCASA.getAccountInfo();
                this.setAvailablebalance(accountdetailIIB.getAccountBalanceAvailable().toPlainString());
                if (accountdetailIIB.getAccountBalanceAvailable().compareTo(BigDecimal.ZERO) == -1) {
                    this.setAvailablebalance("0");
                }
                this.setNumbalance(accountdetailIIB.getAccountBalance().toPlainString());
            } else {
                Helper.writeLogError(this.getClass(), retrieveCurrentAccountCASA.getTransactionInfo().getTransactionReturn());
                return null;
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
        return this;
    }

    /**
     *
     * @param msg
     * @return
     */
    public MobileResponse getMBFinResponse(MessageMB.MobileResultEnum msg) {
        this.setErrorCode(msg.getValue());
        this.setErrorMsg(MessageMB.getMessageMBResult(msg));
        try {
            IIBCurrentAccountService iibAccService = new IIBCurrentAccountService();
            RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASA = iibAccService.retrieveCurrentAccountCASARestSimple(Configuration.getIIBContext(), this.getAccountNo(), IIBConstants.CHANNEL_MOBILE);
            if (retrieveCurrentAccountCASA.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                AccountInfoType accountdetailIIB = retrieveCurrentAccountCASA.getAccountInfo();
                this.setAvailablebalance(accountdetailIIB.getAccountBalanceAvailable().toPlainString());
                if (accountdetailIIB.getAccountBalanceAvailable().compareTo(BigDecimal.ZERO) == -1) {
                    this.setAvailablebalance("0");
                }
                this.setNumbalance(accountdetailIIB.getAccountBalance().toPlainString());
            } else {
                Helper.writeLogError(this.getClass(), retrieveCurrentAccountCASA.getTransactionInfo().getTransactionReturn());
                return null;
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
        return this;
    }

    /**
     *
     * @param msg
     * @return
     */
    public MobileResponse getMBFinResponseOLD(MessageMB.MobileResultEnum msg) {
        this.setErrorCode(msg.getValue());
        this.setErrorMsg(MessageMB.getMessageMBResult(msg));
        //Lay so du sau giao dich
        /*
         String balAfter = GetBalanceAccountAfterTran(this.getAccountNo());
         if (balAfter != null) {
         this.setNumbalance(balAfter);
         }
         */
        try {
            List custacc = Helper.getDBI().getSttmCustAccountSyn(this.getAccountNo());
            if (custacc == null || custacc.isEmpty()) {
                return null;
            } else {
                HashMap hm_Casa = (HashMap) custacc.get(0);
                //this.setNumbalance(hm_Casa.get("acy_curr_balance").toString());
                //this.setAvailablebalance(hm_Casa.get("acy_avl_bal").toString());
                this.setNumbalance(hm_Casa.get("acycurrbalance").toString());
                this.setAvailablebalance(hm_Casa.get("numavailbal").toString());
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            //return null;
        }
        return this;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AccountBalanceAvailable", nillable = true)
    public String getAvailablebalance() {
        return availablebalance;
    }

    /**
     * @param availablebalance the availablebalance to set
     */
    public void setAvailablebalance(String availablebalance) {
        this.availablebalance = availablebalance;
    }

    /**
     * @return the azbalance
     */
    @XmlElement(name = "AzBalance", nillable = true)
    public String getAzbalance() {
        return azbalance;
    }

    /**
     * @param azbalance the azbalance to set
     */
    public void setAzbalance(String azbalance) {
        this.azbalance = azbalance;
    }

    /**
     * @return the cardbalance
     */
    @XmlElement(name = "PrincipalToPay", nillable = true)
    public String getCardbalance() {
        return cardbalance;
    }

    /**
     * @param cardbalance the cardbalance to set
     */
    public void setCardbalance(String cardbalance) {
        this.cardbalance = cardbalance;
    }

    /**
     * @return the dunogocconlai
     */
    @XmlElement(name = "PrincipalNotPaid", nillable = true)
    public String getDunogocconlai() {
        return dunogocconlai;
    }

    /**
     * @param dunogocconlai the dunogocconlai to set
     */
    public void setDunogocconlai(String dunogocconlai) {
        this.dunogocconlai = dunogocconlai;
    }

    /**
     * @return the PrepaidFee
     */
    @XmlElement(name = "PrepaidFee", nillable = true)
    public String getPrepaidFee() {
        return PrepaidFee;
    }

    /**
     * @param PrepaidFee the PrepaidFee to set
     */
    public void setPrepaidFee(String PrepaidFee) {
        this.PrepaidFee = PrepaidFee;
    }

    /**
     * @return the ReloadAccCasaList
     */
    @XmlElement(name = "ReloadCasaAccList", nillable = true)
    public char getReloadCasaAccList() {
        return ReloadCasaAccList;
    }

    /**
     * @param ReloadCasaAccList
     */
    public void setReloadCasaAccList(char ReloadCasaAccList) {
        this.ReloadCasaAccList = ReloadCasaAccList;
    }

    /**
     * @return the dunodasudung
     */
    @XmlElement(name = "PrincipalUsed", nillable = true)
    public String getDunodasudung() {
        return dunodasudung;
    }

    /**
     * @param dunodasudung the dunodasudung to set
     */
    public void setDunodasudung(String dunodasudung) {
        this.dunodasudung = dunodasudung;
    }

    @XmlElement(name = "CardInstallmentAmountPre", nillable = true)
    public String getCardInstallmentAmountPre() {
        return cardInstallmentAmountPre;
    }

    /**
     * @param cardInstallmentAmountPre the cardInstallmentAmountPre to set
     */
    public void setCardInstallmentAmountPre(String cardInstallmentAmountPre) {
        this.cardInstallmentAmountPre = cardInstallmentAmountPre;
    }

    /**
     * @return the cardMinimumInstallmentAmountPre
     */
    @XmlElement(name = "CardMinimumInstallmentAmountPre", nillable = true)
    public String getCardMinimumInstallmentAmountPre() {
        return cardMinimumInstallmentAmountPre;
    }

    /**
     * @param cardMinimumInstallmentAmountPre the cardMinimumInstallmentAmountPre to set
     */
    public void setCardMinimumInstallmentAmountPre(String cardMinimumInstallmentAmountPre) {
        this.cardMinimumInstallmentAmountPre = cardMinimumInstallmentAmountPre;
    }

    /**
     * @return the cardPaidCurrentAmountPre
     */
    @XmlElement(name = "CardPaidCurrentAmountPre", nillable = true)
    public String getCardPaidCurrentAmountPre() {
        return cardPaidCurrentAmountPre;
    }

    /**
     * @param cardPaidCurrentAmountPre the cardPaidCurrentAmountPre to set
     */
    public void setCardPaidCurrentAmountPre(String cardPaidCurrentAmountPre) {
        this.cardPaidCurrentAmountPre = cardPaidCurrentAmountPre;
    }

    /**
     * @return the cardInstallmentDatePre
     */
    @XmlElement(name = "CardInstallmentDatePre", nillable = true)
    public String getCardInstallmentDatePre() {
        return cardInstallmentDatePre;
    }

    /**
     * @param cardInstallmentDatePre the cardInstallmentDatePre to set
     */
    public void setCardInstallmentDatePre(String cardInstallmentDatePre) {
        this.cardInstallmentDatePre = cardInstallmentDatePre;
    }

    /**
     * @return the cardProduct
     */
    @XmlElement(name = "CardProduct", nillable = true)
    public String getCardProduct() {
        return cardProduct;
    }

    /**
     * @param cardProduct the cardProduct to set
     */
    public void setCardProduct(String cardProduct) {
        this.cardProduct = cardProduct;
    }
    /**
     *
     * @return
     */
    @XmlElement(name = "MatDateCreditCard", nillable = true)
    public String getNgaytttoithieu() {
        return this.ngaytttoithieu;
    }

    /**
     *
     * @param ngaytttoithieu
     */
    public void setNgaytttoithieu(String ngaytttoithieu) {
        this.ngaytttoithieu = ngaytttoithieu;
    }

    /**
     * @return the sumAmtLimitPay
     */
    @XmlElement(name = "SumAmtLimitPay", nillable = true)
    public String getSumAmtLimitPay() {
        return sumAmtLimitPay;
    }

    /**
     * @param sumAmtLimitPay the sumAmtLimitPay to set
     */
    public void setSumAmtLimitPay(String sumAmtLimitPay) {
        this.sumAmtLimitPay = sumAmtLimitPay;
    }

    /**
     * @return the sumPaidAmount
     */
    @XmlElement(name = "SumPaidAmount", nillable = true)
    public String getSumPaidAmount() {
        return sumPaidAmount;
    }

    /**
     * @param sumPaidAmount the sumPaidAmount to set
     */
    public void setSumPaidAmount(String sumPaidAmount) {
        this.sumPaidAmount = sumPaidAmount;
    }

    /**
     * @return the sumPrincipalToPay
     */
    @XmlElement(name = "SumPrincipalToPay", nillable = true)
    public String getSumPrincipalToPay() {
        return sumPrincipalToPay;
    }

    /**
     * @param sumPrincipalToPay the sumPrincipalToPay to set
     */
    public void setSumPrincipalToPay(String sumPrincipalToPay) {
        this.sumPrincipalToPay = sumPrincipalToPay;
    }

    /**
     * @return the sumCardBalance
     */
    @XmlElement(name = "SumCardBalance", nillable = true)
    public String getSumCardBalance() {
        return sumCardBalance;
    }

    /**
     * @param sumCardBalance the sumCardBalance to set
     */
    public void setSumCardBalance(String sumCardBalance) {
        this.sumCardBalance = sumCardBalance;
    }

    @XmlElement(name = "TxnIdPartner", nillable = true)
    public String getTxnIdPartner() {
        return TxnIdPartner;
    }

    public void setTxnIdPartner(String TxnIdPartner) {
        this.TxnIdPartner = TxnIdPartner;
    }
}
