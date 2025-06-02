/**
 * @author Nguyen Ngo Duy Phuc
 */
package scb.com.vn.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import scb.com.vn.common.model.transfer.napas.TransferMoney247EbankReq;
import scb.com.vn.dbi.dto.PmtInfoV11ResDto;

//Luu y, phai kiem tra primitive type class
/**
 *
 * @author minhndb
 */
public interface IController {

    /**
     * ***** SMS
     *
     *******
     * @param mobile
     * @param message
     * @return
     */
    public int sendsms(String mobile, String message);

    /**
     *
     * @param mobile
     * @param msgcontents
     * @return
     */
    public String sendsmstotnb(String mobile, String msgcontents);

    /**
     * ***** TOKEN
     *
     *******
     * @param userId
     * @param sOTP
     * @return
     */
    public int checkAuthenTokenkey(String userId, String sOTP);

    /**
     *
     * @param xml
     * @return
     */
    public String requestPayment(String xml);

    /**
     *
     * @param accfrom
     * @param accto
     * @param amount
     * @param narative
     * @return
     */
    public String transferFCUBS(String accfrom, String accto, BigDecimal amount, String narative);

    /**
     *
     * @param brnAccountFrom
     * @param accountFrom
     * @param brnAccountTo
     * @param accountTo
     * @param amount
     * @param narative
     * @return
     */
    public String transferFCUBSWithBranch(String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo, BigDecimal amount, String narative);

    /**
     *
     * @param fccRef
     * @param timeout
     * @return
     */
    public String revertTransferFCUBS(String fccRef, int timeout);

    /**
     *
     * @param username
     * @param password
     * @param ip
     * @return
     */
    public boolean checkpassTNB(String username, String password, String ip);

    /**
     *
     * @param xml
     * @param _hm
     * @return
     */
    public String exchgBill(String xml, HashMap _hm);

    /**
     *
     * @param str
     * @return
     */
    public String getString(String str);

    /**
     *
     */
    public void resetGwPermissions();

    /**
     *
     */
    public void resetExchgBill();

    /**
     *
     * @param idpartner
     * @param filename
     * @return
     */
    public String requestUploadFileToVNPAY(String idpartner, String filename);

    /**
     *
     * @param idpartner
     * @param filename
     * @return
     */
    public String requestDownloadFileToVNPAY(String idpartner, String filename);

    //LyDTY added on 13Jan2014
    /**
     *
     * @param xml
     * @return
     */
    public String cardVerify(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String refundOnlTrans(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String queryOnlTrans(String xml);

    /**
     *
     * @param IDTrans
     * @param UserId
     * @param ClientID
     * @return
     */
    public int sendOTP(String IDTrans, String UserId, String ClientID);

    /**
     *
     * @param product
     * @param accfrom
     * @param accto
     * @param amount
     * @param narative
     * @return
     */
    public String transferFCUBSWithProduct(String product, String accfrom, String accto, BigDecimal amount, String narative);

    /**
     *
     * @param userId
     * @param sOTP
     * @return
     */
    public int checkAuthenTokenkeyforONLTRANS(String userId, String sOTP);

    /**
     *
     * @param idpartner
     * @param filename
     * @return
     */
    public String requestUploadFileToPartner(String idpartner, String filename);

    /**
     *
     * @param idpartner
     * @param filename
     * @return
     */
    public String requestDownloadFileFromPartner(String idpartner, String filename);

    /**
     *
     * @param FromNumber
     * @param TypeFromNumber
     * @param FullName
     * @param ToNumber
     * @param TypeToNumber
     * @param BenID
     * @param Amount
     * @param CCY
     * @param channel
     * @param Desc
     * @param TermId
     * @param CardAcceptor
     * @param TypeFunction
     * @return
     */
    public String SMLTransfeFromSCB(String FromNumber, String TypeFromNumber, String FullName, String ToNumber, String TypeToNumber,
            String BenID, BigDecimal Amount,
            String CCY, String channel, String Desc,
            String TermId, String CardAcceptor, String TypeFunction);

    /**
     *
     * @param Request
     * @return
     */
    public String SMLTransfeToSCB(String Request);

    //CONTACT CENTER
    /**
     *
     * @param SoDienThoai
     * @return
     */
    public String CC_GetCustomerInfoBySoDienThoai(String SoDienThoai);

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_GetCustomerInfoByCIF(String CIF);

    /**
     *
     * @param IDCard
     * @return
     */
    public String CC_GetCustomerInfoByIDCard(String IDCard);

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_GetRecentTransaction(String CIF);

    //public String CC_GetInternalCardInfo(String CIF);
    //public String CC_GetInternalCardTransaction(String CardNumber);
    //public String CC_GetInternationalCardInfo(String CIF);
    /**
     *
     * @param CIF
     * @return
     */
    public String CC_IB_CUST_INFO(String CIF);

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_SMS_CUST_INFO(String CIF);

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_SMSALERT_CUST_INFO(String CIF);

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_GetInternalCardInfo(String CIF);

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetInternalCardTransaction(String CardNumber);

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetInternalCardInfo_MC(String CardNumber);

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetCard_Profile(String CardNumber);

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetAward_Point(String CardNumber);

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetInternalCardTran_MC(String CardNumber);

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GET_TT_SaoKe(String CardNumber);

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetCreditPayTransaction(String CardNumber);

    //END OF CONTACT CENTER
    //Mobile banking    
    /**
     *
     * @param xml
     * @return
     */
    public String GetCustomerInfo(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountList(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountInfo(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountStmt(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String FundTransfer(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountTypeAzInfo(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String OpenOnlineAz(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CloseOnlineAz(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetBeneficaryName(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String SendSMSToMB(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetBillService(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetBillProvider(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetBillInfo(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String BillPayment(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetCreditCardInfo(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CreditCardPay(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetToken(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CheckBalanceBeforeTran(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String ExchangeRate(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GoldRate(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String InterestRate(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CheckToken(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetMobileFee(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetAzBeforeRedemption(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetTemplateAndBeneFromEb(String xml);

    /**
     *
     * @param merchantid
     * @param TransID
     * @param RefundTransID
     * @param Amount
     * @param CCY
     * @param AddInfo
     * @param Localdatatime
     * @return
     */
    public String SMLREFUND(String merchantid, String TransID, String RefundTransID, BigDecimal Amount, String CCY, String AddInfo, String Localdatatime);

    //DOI SOAT NAPAS (S)
    /**
     *
     * @param merchantid
     * @param TransID
     * @param RefundTransID
     * @param Amount
     * @param CCY
     * @param AddInfo
     * @param Localdatatime
     * @param servicecode
     * @return
     */
    public String BANKNETREFUND(String merchantid, String TransID, String RefundTransID, BigDecimal Amount, String CCY, String AddInfo, String Localdatatime, String servicecode);
    //DOI SOAT NAPAS (E)

    /**
     *
     * @param xml
     * @return
     */
    public String GetRedemptionAzList(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String AddFeedback(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetStaff(String xml);

    /**
     *
     * @param productname
     * @param brnAccountFrom
     * @param accountFrom
     * @param brnAccountTo
     * @param accountTo
     * @param amount
     * @param charge
     * @param tax
     * @param narative
     * @return
     */
    public String transferFCUBSWithProductCharge(String productname, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, BigDecimal charge, BigDecimal tax, String narative);
    //Nang cap mobile banking lan 1    

    /**
     *
     * @param xml
     * @return
     */
    public String LoanRegister(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CheckIssueATMCard(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String IssueATMCard(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetCardList(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CreditCardType(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String IssueCreditCard(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String ReissuePIN(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CreditCardStatement(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String RewardPoint(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetCCStatement(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String SearchResgisterCardMB(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CheckBalanceBeforeFee(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountInfoNew(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountStmtNew(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountListNew(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CheckAlertTD(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String RegisterAlertTD(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetPrimaryCardList(String xml);

    /**
     *
     * @param strXML
     * @return
     */
    public String cardVerifyRegister(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String otpVerify(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String payment(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String paymentWithProfileID(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String checkCard(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String takeOutWallet(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String refundPayment(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String revertTakeOutWallet(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String queryTransaction(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String destroyProfileID(String strXML);

    /* CARDWORK WEB SERVICES */
    /**
     *
     * @param srcCasaAccount
     * @param pan
     * @param expDate
     * @param source
     * @param brandCode
     * @param currencyCode
     * @param amount
     * @param debtPay
     * @param debtCur
     * @param IPPPayType
     * @return
     */
    public String cardReload(String srcCasaAccount, String pan, String expDate, String source, String brandCode,
            String currencyCode, BigDecimal amount, BigDecimal debtPay, BigDecimal debtCur, String IPPPayType);

    /**
     *
     * @param xml
     * @return
     */
    public String cardRewardAdjustment(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String cardBalanceAdjustment(String xml);

    /**
     *
     * @param pan
     * @param ActionType
     * @param ActionCode
     * @return
     */
    public String cardProfileMaintenance(String pan, String ActionType, String ActionCode);

    /**
     *
     * @param xml
     * @return
     */
    public String resetPINCard(String xml);

    /**
     *
     * @param srcCasaAccount
     * @param pan
     * @param expDate
     * @param source
     * @param brandCode
     * @param currencyCode
     * @param amount
     * @param user_maker
     * @param user_checker
     * @param idcard
     * @param idcardDob
     * @param idcardName
     * @param idcardAddress
     * @param debtPay
     * @param debtCur
     * @param IPPPayType
     * @return
     */
    public String cardReloadCash(String srcCasaAccount, String pan, String expDate, String source, String brandCode, String currencyCode,
            BigDecimal amount, String user_maker, String user_checker, String idcard, String idcardDob, String idcardName, String idcardAddress, BigDecimal debtPay, BigDecimal debtCur, String IPPPayType);

    /* CARDWORK WEB SERVICES */
    // cardreload for mobile
    /**
     *
     * @param xml
     * @return
     */
    public String GetCreditCardList(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CreditCardPayNew(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetStatementMonthList(String xml);
    // cardreload for mobile

//    mua the cao VTC
    /**
     *
     * @param xml
     * @param processingcode
     * @return
     */
    public String BuycardRequest(String xml, String processingcode);
//    mua the cao VTC
// Tân Cảng

    /**
     *
     * @param xml
     * @param processingcode
     * @return
     */
    public String newportPayment(String xml, String processingcode);
// Tân Cảng

    /**
     *
     * @param xml
     * @return
     */
    public String requestTransfer(String xml);

    /**
     *
     * @param strXML
     * @return
     */
    public String queryAccount(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String getListBankCode(String strXML);

    /**
     *
     * @param PPARTNERID
     * @param PCUSTUMERACCOUNT
     * @param PCUSTUMERNAME
     * @param PAMOUNT
     * @param PCCY
     * @param PCHANNELID
     * @param PTRANSDATE
     * @param PADDINFO
     * @return
     */
    public String transferToSI(String PPARTNERID,
            String PCUSTUMERACCOUNT,
            String PCUSTUMERNAME,
            BigDecimal PAMOUNT,
            String PCCY,
            String PCHANNELID,
            String PTRANSDATE, //ddMMyyyhhmiss
            String PADDINFO);

    /**
     *
     * @param strTKCK
     * @return
     */
    public String getSIName(String strTKCK);

    /**
     *
     * @param xml
     * @return
     */
    public String InsurancePayment(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String PayIns(String xml);

    /**
     *
     * @param CusAccount
     * @param PartnerAcc
     * @param totalamount
     * @param pol_num
     * @param idchannel
     * @return
     */
    public String PaymentInsurance(String CusAccount, String PartnerAcc, BigDecimal totalamount, String pol_num, String idchannel);

    /**
     *
     * @param accountTo
     * @param totalamount
     * @param user_maker
     * @param user_checker
     * @param pol_num
     * @param idchannel
     * @return
     */
    public String PaymentInsuranceCash(String accountTo, BigDecimal totalamount, String user_maker, String user_checker, String pol_num, String idchannel);

    /**
     *
     * @param strxml
     * @return
     */
    public String GetSecuritiesAccountName(String strxml);

    /**
     *
     * @param strxml
     * @return
     */
    public String GetSecuritiesPartner(String strxml);

    /**
     *
     * @param strxml
     * @return
     */
    public String SecuritiesPayment(String strxml);

    /**
     *
     * @param strxml
     * @return
     */
    public String OpenLockCardFunc(String strxml);

    /**
     *
     * @param strxml
     * @return
     */
    public String CheckRegisterAutoBill(String strxml);

    /**
     *
     * @param strxml
     * @return
     */
    public String RegisterAutoBill(String strxml);

    /**
     *
     * @param msgType
     * @param objs
     * @return
     */
    public String getMessageQueryXML(String msgType, String objs);

    /**
     *
     * @param msgType
     * @param ID
     * @return
     */
    public String getMessageXML(String msgType, String ID);

    /**
     *
     * @param strXML
     * @return
     */
    public String paymentAndRegister(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String getProfileID(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String RegisterIssueCard(String strXML);

    /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    /**
     *
     * @param xml
     * @return
     */
    public String OTPREQUEST(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String OTPRESPONSE(String xml);

    /* ONEPAY */
    /**
     *
     * @param xml
     * @return
     */
    public String CardVerification(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String OTPVerification(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String Payment(String xml);

    /* ONEPAY */

 /* VNPAY */
    /**
     *
     * @param xml
     * @return
     */
    public String getAccountBalance(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetChangGiftHistory(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String ChangeRewardPoint(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetGiftList(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String paymentWithAccount(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String refundPaymentWithAccount(String xml);

    /* VNPAY */

 /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    /**
     *
     * @param UserName
     * @param CifNo
     * @param UserLock
     * @return
     */
    public String LockFeeMobileBankingOfSCB(String UserName, String CifNo, String UserLock);

    /**
     *
     * @param UserName
     * @param CifNo
     * @param UserLock
     * @return
     */
    public String UnLockFeeMobileBankingOfSCB(String UserName, String CifNo, String UserLock);

    /**
     *
     * @param strXML
     * @return
     */
    public String PayLoanAccount(String strXML);

    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountListByType(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String OnlineInsBuy(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetOnlineInsInfo(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetOnlineInsTypeList(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String ChangePin(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetInsurancePartner(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetInsuranceContractInfo(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String InsuranceFeePay(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String ListAutoBill(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CancelAutoBill(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetCardListNew(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetOnlineInsFormList(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetOnlineInsDutyList(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetManulifeContractList(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetManulifeTypeList(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String IssueDebitCard(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CheckOpenOnlineAz(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String VerifyTokenCode(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String SelectOnlineInsBuyHistory(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String RetrieveOnlineInsBuyHistory(String xml);

    /**
     *
     * @param xml
     * @param apiName
     * @return
     */
    public String callMOMOService(String xml, String apiName);

    /**
     *
     * @param phonenumber
     * @param Content
     * @return
     */
    public String sendSMSOTP(String phonenumber, String Content);

    /**
     *
     * @param msgType
     * @param ID
     * @return
     */
    public String getMessageXML247(String msgType, String ID);

    /* MASTERPASS QR*/
    /**
     *
     * @param xml
     * @return
     */
    public String PayByQRCode(String xml);

    public String initSessionToChangepin(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String MvisaQRCode(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetCardListByType(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CheckBalanceMasterPassBeforeTran(String xml);

    //Bao addd
    /**
     *
     * @param productname
     * @param userid
     * @param brnAccountFrom
     * @param accountFrom
     * @param brnAccountTo
     * @param accountTo
     * @param amount
     * @param narative
     * @param timeout
     * @return
     */
    public String transferFCUBSWithTimeOut(String productname,
            String userid,
            String brnAccountFrom,
            String accountFrom,
            String brnAccountTo,
            String accountTo,
            BigDecimal amount, String narative, int timeout);

    /**
     *
     * @param maDL
     * @return
     */
    public String bankRequestMaDL(String maDL);

    /**
     *
     * @param xml
     * @return
     */
    public String GetBillInfoForONLPM(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String BillPaymentForONLPM(String xml);

    /**
     *
     * @param strXML
     * @return
     */
    public String checkPayment(String strXML);

    /**
     *
     * @param xml
     * @return
     */
    public String GetFeeMobile(String xml);

    /**
     *
     * @param request
     * @return
     */
    public String transfer247FromPartner(String request);

    /**
     *
     * @param strXML
     * @return
     */
    public String queryTCHTransaction(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String getListBankCode247(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String checkKYC(String strXML);

    /**
     *
     * @param strXML
     * @return
     */
    public String transferToAccount(String strXML);

    /**
     *
     * @param strXML
     * @return
     * @throws Exception
     */
    public String revertTranferToAccount(String strXML) throws Exception;

    /* VNPAY QR */
    /**
     *
     * @param xml
     * @return
     */
    public String checkVNPAYQRCode(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String paymentVNPAYQR(String xml);

    /**
     *
     * @param requestData
     * @return
     */
    public String transferToSCBv2(String requestData);

    /**
     *
     * @param requestData
     * @return
     */
    public String getResponseEcho(String requestData);

    /**
     *
     * @param content
     * @return
     */
    public String MmsTransferAccToAcc(String content);

    /**
     *
     * @param usename
     * @param cifno
     * @param passInput
     * @param typeCheck
     * @return
     */
    public boolean checkAuthenPassMB(String usename, String cifno, String passInput, String typeCheck);

    /**
     *
     * @param xml
     * @return
     */
    public String getSCBBranch(String xml);

    /**
     *
     * @param usename
     * @param cifno
     * @param passInput
     * @param typeCheck
     * @return
     */
    public boolean checkAuthenPassMBNew(String usename, String cifno, String passInput, String typeCheck);

    /**
     *
     * @param xml
     * @return
     */
    public String validateCMND(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String khoaThe(String xml);

    //public String KHT(String cmnd, String phone, String cardType, String last4Digits, String partner, String mac);
    /**
     *
     * @param xml
     * @return
     */
    public String sendEmail(String xml);

    /**
     *
     * @param cifno
     * @param refCore
     * @return
     */
    public String sendEmailBaoLong(String cifno, String refCore);

    /**
     *
     * @param serial
     * @param actionType
     * @return
     */
    public String checkSerialToken(String serial, String actionType);

    /**
     *
     * @param typeDest
     * @param accountTo
     * @param amount
     * @param narative
     * @return
     */
    public String IBTFccTransfer(String typeDest, String accountTo,
            BigDecimal amount, String narative);

    /**
     *
     * @param xml
     * @return
     */
    public String transferMoney(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String transferMoney247(String xml);

    /**
     *
     * @param FromNumber
     * @param TypeFromNumber
     * @param FullName
     * @param ToNumber
     * @param TypeToNumber
     * @param BenID
     * @param Amount
     * @param CCY
     * @param channel
     * @param Desc
     * @param TermId
     * @param CardAcceptor
     * @param TypeFunction
     * @param hidenCard
     * @return
     */
    public String SMLTransfeFromSCBv2(String FromNumber, String TypeFromNumber, String FullName, String ToNumber, String TypeToNumber,
            String BenID, BigDecimal Amount,
            String CCY, String channel, String Desc,
            String TermId, String CardAcceptor, String TypeFunction, String hidenCard);

    /**
     *
     * @param xml
     * @return
     */
    public String SendEmailOpenTD(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GetSendEmailOpenTDList(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String UpdateCustomerInfo(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String InterestRateSlab(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GrantLuckNumber(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String kichHoatThe(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String isExistUserBanking(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String CasaRegister(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String GuaranteeRegister(String xml);

    /**
     *
     * @param xml
     * @return
     */
    public String SMSBDSDList(String xml);

    public String RegisterSMSBDSD(String xml);

    public String transactionHistoryByDesc(String xml) throws Exception;

    public void insertReponseIBT(String trace, String reponse) throws Exception;

    public String getRequestTraceIBT() throws Exception;

    public void insertRequestIBT(String trace) throws Exception;

    public String getReponseIBT(String trace) throws Exception;

    public String checkCustInfo(String inputXml);

    public String PreChangePin(String xml);

    public String getStatusOfTransferMoney(String xml);

    public String getInfoRegister(String xml);

    public String getReceiveFeedback(String xml);

    public String PaymentInsuranceCashv2(String CusAccount, BigDecimal totalamount, String user_maker, String user_checker, String pol_num, String idchannel, String noiCap, String ngayCap, String sodt, String nguoiGiaoDich, String diaChi, String cmnd);

    public String QRPaymentService(String xmlRequest);

    
    public String CreateBLHealthCareContract(String xml);

    public String GetBLPackageCost(String xml);

    public String GetBLCategories(String xml);

    public String GetBLAllQuestions(String xml);

    public String GetBLHealthCareContract(String xml);

    public String GetUrlOnepayBL(String xml);

    public String UpdatePaymentStatus(String xml);
    
    public String getDataCollated(String xml);
    public String revertPayment(String strXML);
    public String sendSMSFromPartner(String xml) throws Exception;
    /*
    *Doi tac dang su dung: Western Union (WU)
    * dang test tren 73.20 
     */
    public String transferMoney247InternalApp(String xml);
    /*
    *Doi tac dang su dung: Western Union (WU)
    * dang test tren 73.20 
     */
    public String getListTransactionByDate(String xml);
    public String GetStatusUserOdbx(String xml) throws Exception; 
    public String GetBankCardDetail(String xml) throws Exception;
            
    
    /*
    dung cho cap nhat giao dich trong nuoc/kieu hoi tren ebanking mo rong
    */
//    public String transferMoney_Ebanking(String request);
    /*
    dung cho cap nhat giao dich kieu hoi tren ebanking mo rong
    */
    public String transferMoney247_Ebanking(String request);
     
    public String MoKhoaThe(String xml);
    public String KhoaTheCRM(String xml);
    public String MoKhoaTheCRM(String xml);
    
    public String GetCustomerInfo2345(String xml);
}
