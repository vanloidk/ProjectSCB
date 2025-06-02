package scb.com.vn.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author minhndb
 */
@XmlRootElement(name = "AccountInfoRp")
public class AccountInfoRp extends MobileResponse {

    private String AccountNo;
    private String prdcode;
    private String prdname;
    private String codacctcurr;
    private String numbalance;
    private String numavailbal;
    private String codbranch;
    private String acctopendt;
    private String maturitydate;
    private String rate;
    private String Term;
    private String SavingType;
    private String ngayvay;
    private String ngaydaohan;
    private String thoihanvay;
    private String sotienduocduyet;
    private String sotiengiaingan;
    private String dunogoc;
    private String dunogocdatra;
    private String dunogocconlai;
    private String laivay;
    private String laivaydatra;
    private String laivayconlai;
    private BigDecimal laisuat;
    private String limit_amount;
    private String hanmuc;
    private String dunodasudung;
    private String dunocuoiky;
    private String thanhtoantoithieu;
    private String ngaytttoithieu;
    private String hanmucconlai;
    private String cardno;
    private String cardNoHidden;
    private String cardaccountno;
    private String hanmuckh;
    private String hanmucconlaikh;
    private String dunothekhac;
    private String expiredate;
    private String tinhchatthe;
    private String loaithe;
    private String DuNoGocDenHan;
    private String LaiPhaiTra;
    private String LaiDenHan;
    private String PhatChamTraLai;
    private String PhatChamTraGoc;
    private String blockonline;
    private String defaultcasa;
    private String FullName;
    private String branchname;
    private String IsRedem;
    private String tinhtrangthe;
    private String UnPostAmount;
    private String CardStatus;
    private String cardIPPStatementAmount;
    private String sumPrincipalToPay;
    private String sumAmtLimitPay;
    private String sumPaidAmount;
    private String cardIPPCurrentAmount;
    private String savingStatus;
    private String savingSerials;
    private String isOwner;
    private String lastMonthAvgBal;
    private String curMonthAvgBal;
    private String trnref;
    private String receiveName;
    private String providerName;
    private String description_1;
    private String description_2;

    private String cardInstallmentAmountPre;
    private String cardMinimumInstallmentAmountPre;
    private String cardPaidCurrentAmountPre;
    private String cardInstallmentDatePre;
    private String cardProduct;
    private String cardDescription;
    private String cardOwnerName;
    private String imageCard;
    //the ca nhan/doanh nghiep
    private String cardTypeCode;
    /**
     *
     * @return
     */
    @XmlElement(name = "AccountTypeCode", nillable = true)
    public String getPrdcode() {
        return this.prdcode;
    }

    /**
     *
     * @param prdcode
     */
    public void setPrdcode(String prdcode) {
        this.prdcode = prdcode;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AccountTypeName", nillable = true)
    public String getPrdname() {
        return this.prdname;
    }

    /**
     *
     * @param prdname
     */
    public void setPrdname(String prdname) {
        this.prdname = prdname;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AccountCcy", nillable = true)
    public String getCodacctcurr() {
        return this.codacctcurr;
    }

    /**
     *
     * @param codacctcurr
     */
    public void setCodacctcurr(String codacctcurr) {
        this.codacctcurr = codacctcurr;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AccountBalance", nillable = true)
    public String getNumbalance() {
        return this.numbalance;
    }

    /**
     *
     * @param numbalance
     */
    public void setNumbalance(String numbalance) {
        this.numbalance = numbalance;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AccountBalanceAvaliable", nillable = true)
    public String getNumavailbal() {
        return this.numavailbal;
    }

    /**
     *
     * @param numavailbal
     */
    public void setNumavailbal(String numavailbal) {
        this.numavailbal = numavailbal;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AccountBranchOpen", nillable = true)
    public String getsetCodbranch() {
        return this.codbranch;
    }

    /**
     *
     * @param codbranch
     */
    public void setCodbranch(String codbranch) {
        this.codbranch = codbranch;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "OpenDate", nillable = true)
    public String getAcctopendt() {
        return this.acctopendt;
    }

    /**
     *
     * @param acctopendt
     */
    public void setAcctopendt(String acctopendt) {
        this.acctopendt = acctopendt;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MatDate", nillable = true)
    public String getMaturitydate() {
        return this.maturitydate;
    }

    /**
     *
     * @param maturitydate
     */
    public void setMaturitydate(String maturitydate) {
        this.maturitydate = maturitydate;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "Interest", nillable = true)
    public String getRate() {
        return this.rate;
    }

    /**
     *
     * @param rate
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "Term", nillable = true)
    public String getTerm() {
        return this.Term;
    }

    /**
     *
     * @param Term
     */
    public void setTerm(String Term) {
        this.Term = Term;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "SavingType", nillable = true)
    public String getSavingType() {
        return this.SavingType;
    }

    /**
     *
     * @param SavingType
     */
    public void setSavingType(String SavingType) {
        this.SavingType = SavingType;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "LoanDate", nillable = true)
    public String getNgayvay() {
        return this.ngayvay;
    }

    /**
     *
     * @param ngayvay
     */
    public void setNgayvay(String ngayvay) {
        this.ngayvay = ngayvay;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "MatLoanDate", nillable = true)
    public String getNgaydaohan() {
        return this.ngaydaohan;
    }

    /**
     *
     * @param ngaydaohan
     */
    public void setNgaydaohan(String ngaydaohan) {
        this.ngaydaohan = ngaydaohan;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PrincipalBalance", nillable = true)
    public String getDunogoc() {
        return this.dunogoc;
    }

    /**
     *
     * @param dunogoc
     */
    public void setDunogoc(String dunogoc) {
        this.dunogoc = dunogoc;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PrincipalPaid", nillable = true)
    public String getDunogocdatra() {
        return this.dunogocdatra;
    }

    /**
     *
     * @param dunogocdatra
     */
    public void setDunogocdatra(String dunogocdatra) {
        this.dunogocdatra = dunogocdatra;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PrincipalNotPaid", nillable = true)
    public String getDunogocconlai() {
        return this.dunogocconlai;
    }

    /**
     *
     * @param dunogocconlai
     */
    public void setDunogocconlai(String dunogocconlai) {
        this.dunogocconlai = dunogocconlai;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "InterestAmt", nillable = true)
    public String getLaivay() {
        return this.laivay;
    }

    /**
     *
     * @param laivay
     */
    public void setLaivay(String laivay) {
        this.laivay = laivay;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "InterestAmtPaid", nillable = true)
    public String getLaivaydatra() {
        return this.laivaydatra;
    }

    /**
     *
     * @param laivaydatra
     */
    public void setLaivaydatra(String laivaydatra) {
        this.laivaydatra = laivaydatra;
    }

    /**
     *
     * @return
     */
    public String getLaivayconlai() {
        return this.laivayconlai;
    }

    /**
     *
     * @param laivayconlai
     */
    public void setLaivayconlai(String laivayconlai) {
        this.laivayconlai = laivayconlai;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "InterestLoan", nillable = true)
    public BigDecimal getLaisuat() {
        return this.laisuat;
    }

    /**
     *
     * @param laisuat
     */
    public void setLaisuat(BigDecimal laisuat) {
        this.laisuat = laisuat;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "LimitAmount", nillable = true)
    public String getLimit_amount() {
        return this.limit_amount;
    }

    /**
     *
     * @param limit_amount
     */
    public void setLimit_amount(String limit_amount) {
        this.limit_amount = limit_amount;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "LimitCreditCard", nillable = true)
    public String getHanmuc() {
        return this.hanmuc;
    }

    /**
     *
     * @param hanmuc
     */
    public void setHanmuc(String hanmuc) {
        this.hanmuc = hanmuc;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PrincipalUsed", nillable = true)
    public String getDunodasudung() {
        return this.dunodasudung;
    }

    /**
     *
     * @param dunodasudung
     */
    public void setDunodasudung(String dunodasudung) {
        this.dunodasudung = dunodasudung;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "PrincipalToPay", nillable = true)
    public String getDunocuoiky() {
        return this.dunocuoiky;
    }

    /**
     *
     * @param dunocuoiky
     */
    public void setDunocuoiky(String dunocuoiky) {
        this.dunocuoiky = dunocuoiky;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AmtLimitPay", nillable = true)
    public String getThanhtoantoithieu() {
        return this.thanhtoantoithieu;
    }

    /**
     *
     * @param thanhtoantoithieu
     */
    public void setThanhtoantoithieu(String thanhtoantoithieu) {
        this.thanhtoantoithieu = thanhtoantoithieu;
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
     *
     * @return
     */
    @XmlElement(name = "AccountNo", nillable = true)
    public String getAccountNo() {
        return this.AccountNo;
    }

    /**
     *
     * @param AccountNo
     */
    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "AvaliableLimit", nillable = true)
    public String getHanmucconlai() {
        return this.hanmucconlai;
    }

    /**
     *
     * @param hanmucconlai
     */
    public void setHanmucconlai(String hanmucconlai) {
        this.hanmucconlai = hanmucconlai;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CardAccountNo", nillable = true)
    public String getCardaccountno() {
        return this.cardaccountno;
    }

    /**
     *
     * @param cardaccountno
     */
    public void setCardaccountno(String cardaccountno) {
        this.cardaccountno = cardaccountno;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "CardNo", nillable = true)
    public String getCardno() {
        return this.cardno;
    }

    /**
     *
     * @param cardno
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    /**
     * @return the hanmuckh
     */
    @XmlElement(name = "LimitCreditCust", nillable = true)
    public String getHanmuckh() {
        return hanmuckh;
    }

    /**
     * @param hanmuckh the hanmuckh to set
     */
    public void setHanmuckh(String hanmuckh) {
        this.hanmuckh = hanmuckh;
    }

    /**
     * @return the hanmucconlaikh
     */
    @XmlElement(name = "AvaliableLimitCust", nillable = true)
    public String getHanmucconlaikh() {
        return hanmucconlaikh;
    }

    /**
     * @param hanmucconlaikh the hanmucconlaikh to set
     */
    public void setHanmucconlaikh(String hanmucconlaikh) {
        this.hanmucconlaikh = hanmucconlaikh;
    }

    /**
     * @return the dunothekhac
     */
    @XmlElement(name = "PrincipalUsedOther", nillable = true)
    public String getDunothekhac() {
        return dunothekhac;
    }

    /**
     * @param dunothekhac the dunothekhac to set
     */
    public void setDunothekhac(String dunothekhac) {
        this.dunothekhac = dunothekhac;
    }

    /**
     * @return the expiredate
     */
    @XmlElement(name = "ExpireDate", nillable = true)
    public String getExpiredate() {
        return expiredate;
    }

    /**
     * @param expiredate the expiredate to set
     */
    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    /**
     * @return the tinhchatthe
     */
    @XmlElement(name = "CreditProperty", nillable = true)
    public String getTinhchatthe() {
        return tinhchatthe;
    }

    /**
     * @param tinhchatthe the tinhchatthe to set
     */
    public void setTinhchatthe(String tinhchatthe) {
        this.tinhchatthe = tinhchatthe;
    }

    /**
     * @return the loaithe
     */
    @XmlElement(name = "CreditType", nillable = true)
    public String getLoaithe() {
        return loaithe;
    }

    /**
     * @param loaithe the loaithe to set
     */
    public void setLoaithe(String loaithe) {
        this.loaithe = loaithe;
    }

    /**
     * @return the DuNoGocDenHan
     */
    @XmlElement(name = "DuNoGocDenHan", nillable = true)
    public String getDuNoGocDenHan() {
        return DuNoGocDenHan;
    }

    /**
     * @param DuNoGocDenHan the DuNoGocDenHan to set
     */
    public void setDuNoGocDenHan(String DuNoGocDenHan) {
        this.DuNoGocDenHan = DuNoGocDenHan;
    }

    /**
     * @return the LaiPhaiTra
     */
    @XmlElement(name = "LaiPhaiTra", nillable = true)
    public String getLaiPhaiTra() {
        return LaiPhaiTra;
    }

    /**
     * @param LaiPhaiTra the LaiPhaiTra to set
     */
    public void setLaiPhaiTra(String LaiPhaiTra) {
        this.LaiPhaiTra = LaiPhaiTra;
    }

    /**
     * @return the LaiDenHan
     */
    @XmlElement(name = "LaiDenHan", nillable = true)
    public String getLaiDenHan() {
        return LaiDenHan;
    }

    /**
     * @param LaiDenHan the LaiDenHan to set
     */
    public void setLaiDenHan(String LaiDenHan) {
        this.LaiDenHan = LaiDenHan;
    }

    /**
     * @return the PhatChamTraLai
     */
    @XmlElement(name = "PhatChamTraLai", nillable = true)
    public String getPhatChamTraLai() {
        return PhatChamTraLai;
    }

    /**
     * @param PhatChamTraLai the PhatChamTraLai to set
     */
    public void setPhatChamTraLai(String PhatChamTraLai) {
        this.PhatChamTraLai = PhatChamTraLai;
    }

    /**
     * @return the PhatChamTraGoc
     */
    @XmlElement(name = "PhatChamTraGoc", nillable = true)
    public String getPhatChamTraGoc() {
        return PhatChamTraGoc;
    }

    /**
     * @param PhatChamTraGoc the PhatChamTraGoc to set
     */
    public void setPhatChamTraGoc(String PhatChamTraGoc) {
        this.PhatChamTraGoc = PhatChamTraGoc;
    }

    /**
     * @return the blockonline
     */
    @XmlElement(name = "ONLStatus")
    public String getBlockonline() {
        return blockonline;
    }

    /**
     * @param blockonline the blockonline to set
     */
    public void setBlockonline(String blockonline) {
        this.blockonline = blockonline;
    }

    /**
     * @return the defaultcasa
     */
    @XmlElement(name = "DefaultCasa")
    public String getDefaultcasa() {
        return defaultcasa;
    }

    /**
     * @param defaultcasa the defaultcasa to set
     */
    public void setDefaultcasa(String defaultcasa) {
        this.defaultcasa = defaultcasa;
    }

    /**
     * @return the FullName
     */
    @XmlElement(name = "FullName")
    public String getFullName() {
        return FullName;
    }

    /**
     * @param FullName the FullName to set
     */
    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    /**
     * @return the branchname
     */
    @XmlElement(name = "BranchName")
    public String getBranchname() {
        return branchname;
    }

    /**
     * @param branchname the branchname to set
     */
    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    /**
     * @return the IsRedem
     */
    @XmlElement(name = "IsRedem")
    public String getIsRedem() {
        return IsRedem;
    }

    /**
     * @param IsRedem the IsRedem to set
     */
    public void setIsRedem(String IsRedem) {
        this.IsRedem = IsRedem;
    }

    /**
     * @return the tinhtrangthe
     */
    @XmlElement(name = "CardStatusName", nillable = true)
    public String getTinhtrangthe() {
        return tinhtrangthe;
    }

    /**
     * @param tinhtrangthe the tinhtrangthe to set
     */
    public void setTinhtrangthe(String tinhtrangthe) {
        this.tinhtrangthe = tinhtrangthe;
    }

    /**
     * @return the UnPostAmount
     */
    @XmlElement(name = "UnPostAmount", nillable = true)
    public String getUnPostAmount() {
        return UnPostAmount;
    }

    /**
     * @param UnPostAmount the UnPostAmount to set
     */
    public void setUnPostAmount(String UnPostAmount) {
        this.UnPostAmount = UnPostAmount;
    }

    /**
     * @return the CardStatus
     */
    @XmlElement(name = "CardStatus", nillable = true)
    public String getCardStatus() {
        return CardStatus;
    }

    /**
     * @param CardStatus the CardStatus to set
     */
    public void setCardStatus(String CardStatus) {
        this.CardStatus = CardStatus;
    }

    /**
     * @return the cardIPPStatementAmount
     */
    @XmlElement(name = "CardIPPStatementAmount", nillable = true)
    public String getCardIPPStatementAmount() {
        return cardIPPStatementAmount;
    }

    /**
     * @param cardIPPStatementAmount the cardIPPStatementAmount to set
     */
    public void setCardIPPStatementAmount(String cardIPPStatementAmount) {
        this.cardIPPStatementAmount = cardIPPStatementAmount;
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
     * @return the cardIPPCurrentAmount
     */
    @XmlElement(name = "CardIPPCurrentAmount", nillable = true)
    public String getCardIPPCurrentAmount() {
        return cardIPPCurrentAmount;
    }

    /**
     * @param cardIPPCurrentAmount the cardIPPCurrentAmount to set
     */
    public void setCardIPPCurrentAmount(String cardIPPCurrentAmount) {
        this.cardIPPCurrentAmount = cardIPPCurrentAmount;
    }

    /**
     * @return the savingStatus
     */
    @XmlElement(name = "SavingStatus", nillable = true)
    public String getSavingStatus() {
        return savingStatus;
    }

    /**
     * @param savingStatus the savingStatus to set
     */
    public void setSavingStatus(String savingStatus) {
        this.savingStatus = savingStatus;
    }

    /**
     * @return the savingSerials
     */
    @XmlElement(name = "SavingSerials", nillable = true)
    public String getSavingSerials() {
        return savingSerials;
    }

    /**
     * @param savingSerials the savingSerials to set
     */
    public void setSavingSerials(String savingSerials) {
        this.savingSerials = savingSerials;
    }

    /**
     * @return the isOwner
     */
    @XmlElement(name = "IsOwner", nillable = true)
    public String getIsOwner() {
        return isOwner;
    }

    /**
     * @param isOwner the isOwner to set
     */
    public void setIsOwner(String isOwner) {
        this.isOwner = isOwner;
    }

    /**
     * @return the lastMonthAvgBal
     */
    @XmlElement(name = "LastMonthAvgBal", nillable = true)
    public String getLastMonthAvgBal() {
        return lastMonthAvgBal;
    }

    /**
     * @param lastMonthAvgBal the lastMonthAvgBal to set
     */
    public void setLastMonthAvgBal(String lastMonthAvgBal) {
        this.lastMonthAvgBal = lastMonthAvgBal;
    }

    /**
     * @return the curMonthAvgBal
     */
    @XmlElement(name = "CurMonthAvgBal", nillable = true)
    public String getCurMonthAvgBal() {
        return curMonthAvgBal;
    }

    /**
     * @param curMonthAvgBal the curMonthAvgBal to set
     */
    public void setCurMonthAvgBal(String curMonthAvgBal) {
        this.curMonthAvgBal = curMonthAvgBal;
    }

    /**
     * @return the trnref
     */
    @XmlElement(name = "Trnref", nillable = true)
    public String getTrnref() {
        return trnref;
    }

    /**
     * @param trnref the trnref to set
     */
    public void setTrnref(String trnref) {
        this.trnref = trnref;
    }

    /**
     * @return the receiveName
     */
    @XmlElement(name = "ReceiveName", nillable = true)
    public String getReceiveName() {
        return receiveName;
    }

    /**
     * @param receiveName the receiveName to set
     */
    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    /**
     * @return the providerName
     */
    @XmlElement(name = "ProviderName", nillable = true)
    public String getProviderName() {
        return providerName;
    }

    /**
     * @param providerName the providerName to set
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    /**
     * @return the description_1
     */
    @XmlElement(name = "Description_1", nillable = true)
    public String getDescription_1() {
        return description_1;
    }

    /**
     * @param description_1 the description_1 to set
     */
    public void setDescription_1(String description_1) {
        this.description_1 = description_1;
    }

    /**
     * @return the description_2
     */
    @XmlElement(name = "Description_2", nillable = true)
    public String getDescription_2() {
        return description_2;
    }

    /**
     * @param description_2 the description_2 to set
     */
    public void setDescription_2(String description_2) {
        this.description_2 = description_2;
    }

    /**
     * @return the cardInstallmentAmountPre
     */
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
     * @param cardMinimumInstallmentAmountPre the
     * cardMinimumInstallmentAmountPre to set
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

    @XmlElement(name = "CardDescription", nillable = true)
    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    @XmlElement(name = "CardOwnerName", nillable = true)
    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }
    
    @XmlElement(name = "ImageCard", nillable = true)
     public String getImageCard() {
        return imageCard;
    }

    public void setImageCard(String imageCard) {
        this.imageCard = imageCard;
    }
    
    @XmlElement(name = "CardNoHidden", nillable = true)
    public String getCardNoHidden() {
        return cardNoHidden;
    }
    public void setCardNoHidden(String cardNoHidden) {
        this.cardNoHidden = cardNoHidden;
    }
    
    @XmlElement(name = "CardTypeCode", nillable = true)
    public String getCardTypeCode() {
        return cardTypeCode;
    }
    public void setCardTypeCode(String cardTypeCode) {
        this.cardTypeCode = cardTypeCode;
    }
    
}
