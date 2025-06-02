/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import oracle.jdbc.OracleTypes;
import scb.com.vn.ultility.jdbc.JDBCEngine;
import java.util.Date;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cw.CardInfo;
import scb.com.vn.common.model.masterpass.MCPaymentRp;
import scb.com.vn.common.model.masterpass.PayByQRCodeRq;
import scb.com.vn.common.model.mvisa.MVISAQRRQ;
import scb.com.vn.common.model.mvisa.ResponseMessage;
import scb.com.vn.common.model.vnpayqr.CheckQRRp;
import scb.com.vn.common.model.vnpayqr.CheckQRRq;
import scb.com.vn.common.model.vnpayqr.PaymentQRJson;
import scb.com.vn.common.model.vnpayqr.PaymentQRRp;
import scb.com.vn.common.model.vnpayqr.PaymentQRRq;
import scb.com.vn.common.model.vnpayqr.QrInfo;
import scb.com.vn.common.model.vnpayqr.RefundQRJson;
import scb.com.vn.common.model.vnpayqr.RefundQRRp;
import scb.com.vn.dbi.dto.DawacoCollate;
import scb.com.vn.dbi.dto.EBANKUSER;
import scb.com.vn.dbi.dto.EVNHCM;
import scb.com.vn.dbi.dto.MasterSenderInfor;
import scb.com.vn.dbi.dto.PayOnlineCollate;
import scb.com.vn.dbi.dto.Pbl_billpaidCollate;
import scb.com.vn.dbi.dto.SMLCollate;
import scb.com.vn.dbi.utility.Utils;
import scb.com.vn.ultility.Common;
import java.util.HashMap;
import scb.com.vn.common.model.SqlCommand;
import scb.com.vn.common.model.cims.kht.KichHoatTheInfo;
import scb.com.vn.common.model.cims.kt.KhoaTheDetailsInfo;
import scb.com.vn.common.model.cims.kt.KhoaTheInfo;
import scb.com.vn.common.model.cims.kt.KhoaTheReq;
import scb.com.vn.common.model.cims.kt.MoKhoaTheReq;
import scb.com.vn.common.model.cw.CardAdjustmentReq;
import scb.com.vn.common.model.cw.CardAdjustmentRes;
import scb.com.vn.common.model.cw.DirectDebitRes;
import scb.com.vn.common.model.masterpass.MasterCardQrActionEnum;
import scb.com.vn.common.model.sms.SmsDetail;
import scb.com.vn.common.model.sms.SmsInfo;
import scb.com.vn.common.odbx.SCBBranch;
import scb.com.vn.dbi.bo.SmsSCBBO;
import scb.com.vn.dbi.dto.CustomerInfoRsDto;
import scb.com.vn.dbi.dto.GwEmailTd;
import scb.com.vn.dbi.dto.OnlPaymentByCardDto;
import scb.com.vn.dbi.dto.OnlinePCustomerInfoDto;
import scb.com.vn.dbi.dto.OrgBillPaid;
import scb.com.vn.dbi.dto.Pbl_partnercode;
import scb.com.vn.dbi.dto.SmsThuphi;
import scb.com.vn.dbi.dto.VNPTMoneyCollate;
import scb.com.vn.dbi.dto.VwSmsThuphi;
import scb.com.vn.dbi.utility.DateUtils;
import scb.com.vn.ultility.DBActionEnum;

/**
 *
 * @author Administrator
 */
public class OnlinePaymentDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(OnlinePaymentDAO.class);

    final String checkData = "BEGIN PAYMENTONLINE.checkData(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String TransferData = "BEGIN PAYMENTONLINE.TransferData(?,?); END;";
    final String CommitTransfer = "BEGIN PAYMENTONLINE.CommitTransfer(?,?,?); END;";
    final String CheckRefundTransfer = "BEGIN PAYMENTONLINE.CheckRefundTransfer(?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String UpdateRefundTransfer = "BEGIN PAYMENTONLINE.UpDateRefundTransfer(?,?,?); END;";
    final String QuerryTransfer = "BEGIN PAYMENTONLINE.QuerryTransfer(?,?,?,?,?,?,?,?,?); END;";
    final String getPaymentOnlineInfo = "BEGIN PAYMENTONLINE.getPaymentOnlineInfo(?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String insertOTPSMS = "BEGIN PAYMENTONLINE.insertOTPSMS(?,?,?,?); END;";
    final String getOTPSMS = "BEGIN PAYMENTONLINE.getOTPSMS(?,?,?,?); END;";
    final String getPaymentOnlineInfoByID = "BEGIN PAYMENTONLINE.getPaymentOnlineInfoByID(?,?,?); END;";
    final String checkSessionForTransfer = "BEGIN PAYMENTONLINE.checkSessionForTransfer(?,?,?,?); END;";
    final String isCollate = "BEGIN PAYMENTONLINE.isCollate(?,?,?); END;";
    final String receiveCollateData = "BEGIN PAYMENTONLINE.ReceiveCollateData(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String answerCollateData = "BEGIN PAYMENTONLINE.AnswerCollateData(?,?,?); END;";
    final String checkFailed = "BEGIN PAYMENTONLINE.checkFailed(?,?,?); END;";
    final String insertFailed = "BEGIN PAYMENTONLINE.insertFailed(?,?); END;";
    final String CheckBeforeTransfer = "BEGIN PAYMENTONLINE.CheckBeforeTransfer(?,?,?); END;";
    final String insertSMLCollate = "BEGIN PAYMENTONLINE.insertSMLECOMCollate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String getOutSMLEComCollate = "BEGIN PAYMENTONLINE.getOutSMLEcomCollate(?); END;";
    final String getDataByVerifyID = "Select M.MERCHANTname,O.TRANSAMOUNT, O.Currencycode CCY,(Sysdate - O.Transdate)*24*60  TimeSpace, O.PartnerID,O.TransactionID,O.MerchantID,O.ID,O.CUSTACCOUNTNO,O.CardNumber from ONL_TRANS_MASTER O, ONL_TRANS_MERCHT M where O.Merchantid = M.Merchantid and O.Status='02' and O.IDVERIFY=?";
    final String checkTokenByUserID = "BEGIN PAYMENTONLINE.checkTokenByUserID(?,?); END;";
    final String SMLCheckData = "BEGIN PAYMENTONLINE.SMLCheckData(?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    /*
     Insertdate: 02/Feb/2015
     */
    final String InsertCollateddate = "BEGIN PAYMENTONLINE.InsertCollateddate(?,?); END;";
    final String getMaxCollateDate = "BEGIN SMS_SCB.PAYMENTONLINE.getMaxCollateDate(?,?); END;";
    /*
     * Date: 18/Aug/2015
     * Desc: Thanh toan khong can Ebanking
     */

    final String VERIFICARD = "BEGIN PAYMENTBYCARD.VERIFICARD(?,?,?,?,?,?,?,?,?,?,?); END;";
    final String VERIFYOTPSMS = "BEGIN PAYMENTBYCARD.VERIFYOTPSMS(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String PAYMENTWITHPROFILEID = "BEGIN PAYMENTBYCARD.PAYMENTWITHPROFILEID(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String PAYMENTWITHACCOUNT = "BEGIN PAYMENT_WITH_ACCOUNT(?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String PAYMENT = "BEGIN PAYMENTBYCARD.PAYMENT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String UPDATEPAYMENT = "UPDATE SMS_SCB.ONL_PAYMENT_BYCARD SET STATUS = ?, REFCORE = ? WHERE ID = ?";
    final String checkDestNumber = "BEGIN PAYMENTBYCARD.checkDestNumber(?,?,?,?,?); END;";
    final String TAKEOUTWALLET = "BEGIN PAYMENTBYCARD.TAKEOUTWALLET(?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String UPDATE_TAKEOUTWALLET = "BEGIN PAYMENTBYCARD.UPDATE_TAKEOUTWALLET(?,?); END;";
    final String REVERT_TAKEOUTWALLET = "BEGIN PAYMENTBYCARD.REVERT_TAKEOUTWALLET(?,?,?,?,?,?,?); END;";
    final String UPDATE_REVERT_TAKEOUTWALLET = "BEGIN PAYMENTBYCARD.UPDATE_REVERT_TAKEOUTWALLET(?,?,?,?,?); END;";
    final String REFUND = "BEGIN PAYMENTBYCARD.REFUND(?,?,?,?,?,?,?,?,?,?); END;";
    final String REFUND_PAYMENT_WITH_ACCOUNT = "BEGIN PAYMENTBYCARD.REFUND_PAYMENT_WITH_ACCOUNT(?,?,?,?,?,?,?,?,?,?); END;";
    final String UPDATE_REFUND = "BEGIN PAYMENTBYCARD.UPDATE_REFUND(?,?); END;";
    final String queryTransaction = "BEGIN PAYMENTBYCARD.queryTransaction(?,?,?,?,?,?,?); END;";
    final String DestroyConnectCard = "BEGIN PAYMENTBYCARD.DestroyConnectCard(?,?,?,?,?,?,?,?); END;";
    final String getMACkeys = "BEGIN PAYMENTONLINE.getMACkeys(?,?); END;";
    final String PaymentAndRegister = "BEGIN PAYMENTBYCARD.PaymentAndRegister(?,?); END;";
    final String getProfileID = "BEGIN PAYMENTBYCARD.querryProfileID(?,?,?); END;";

    /* VNPAY */
    private static final String QUERY_PERSONAL_INFORMATION = "BEGIN PAYMENTONLINE.QUERY_PERSONAL_INFORMATION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    private static final String CHECK_ACCOUNT_BALANCE = "BEGIN PAYMENTBYCARD.CHECK_ACCOUNT_BALANCE(?,?,?,?,?); END;";
    private static final String GET_ACCOUNT_BALANCE = "BEGIN PKG_CHECK_ACCOUNT.checkCustAccount(?,?,?,?); END;";
    /* VNPAY */

    private static final String OTP_REQUEST = "BEGIN PAYMENTBYCARD.OTP_REQUEST(?,?,?,?,?,?,?,?); END;";
    private static final String OTP_RESPONSE = "BEGIN PAYMENTBYCARD.OTP_RESPONSE(?,?,?,?,?,?,?,?,?,?); END;";

    /* ONEPAY */
    private static final String ONEPAY_CARD_VERIFICATION = "BEGIN PAYMENTONLINE.CARD_VER(?,?,?,?,?,?,?,?,?,?,?); END;";
    private static final String ONEPAY_OTP_VERIFICATION = "BEGIN PAYMENTONLINE.OTP_VER(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    private static final String ONEPAY_PAYMENT = "BEGIN PAYMENTONLINE.PAYMENT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    private static final String ONEPAY_PAYMENT_WITHOUT_OTP = "BEGIN PAYMENTONLINE.PAYMENT_WITHOUT_OTP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    private static final String PAYMENT_OTP_ADDING = "BEGIN PAYMENTONLINE.PAYMENT_OTP_ADDING(?,?,?,?,?,?); END;";
    /* ONEPAY */

 /* DAWACO */
    private static final String GETOUTDAWACOCOLLATE = "SELECT * FROM SMS_SCB.PBL_BILLPAID T WHERE T.IDPARTNER = 'DAWACO' AND T.REF_FCUBS IS NOT NULL AND TO_CHAR(T.TRANSDATE,'ddmmyyyy') = '%s' AND T.STATUS IN ('D', 'H') AND T.DATAXML IS NOT NULL ORDER BY T.TRANSDATE DESC";
    /* DAWACO */

 /* MASTERPASS */
    final String INSERTCWDIRECTDEBIT = "INSERT INTO MASTERDIRECDEBIT (SEQUENCENO, RESPONSECODE, RESPONSEDESCRIPTION, APPROVALCODE, TYPETRANS) VALUES (?, ?, ?, ?, ?)";
    final String UPDATEMASTERSTATUS = "UPDATE MASTERPASSQR SET STATUS = ? WHERE SEQUENCENO = ?";
    final String INSERTCWCARDADJ = "INSERT INTO MASTERADJUSTMENT (SEQUENCENO, REFERENCENO, RESPONSECODE, RESPONSEDESCRIPTION, TYPETRANS, LOC4DIGIT, MER_PAN, REFCODE, CARDTYPE, CREATEDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
    final String INSERTMASTERCARDRES = "INSERT INTO MASTERCARDRES (TRANSFER_REFERENCE, ID, RESOURCE_TYPE, PAYMENT_TYPE, CREATED, TH_ID, TH_RESOURCETYPE, TH_NETWORK, TH_TYPE, TH_CREATETIMES, TH_STATUS, TH_STATUSREASON, TH_STATUSTIMES, TH_RETRIEREF, TH_SYSTRACEAUDIT, TH_SWITCHSERIALNUM, ORIGINALSTATUS, STATUS, STATUSTS, ERRORCODE, ERRORDESC) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String GETMASTERREFNO = "SELECT * FROM MASTERPASSQR WHERE REFERENCENO = ? AND PARTNER = ?";
    final String GETDIRECTDEBITSEQNO = "SELECT SEQ_DIRECTDEBIT.NEXTVAL AS SEQUENCENO FROM DUAL";
    final String GETMASTERCARDSEQNO = "SELECT SEQ_MASTERPASSQR.NEXTVAL AS SEQUENCENO FROM DUAL";
    final String INSERTMASTERPASSQR = "INSERT INTO MASTERPASSQR (SEQUENCENO, SENDER_FNAME, SENDER_MNAME, SENDER_LNAME, MER_FNAME, MER_MNAME, MER_LNAME, AMOUNT, CCY, CCYCODE, MER_ADDRESS, MER_CITY, MER_COUNTRY, MER_COUNTRYSUB, MER_CATEGORYCODE, SENDER_ADDRESS, SENDER_CITY, SENDER_POSTALCODE, SENDER_COUNTRY, DATETIME, MER_PAN, SENDER_PAN, MER_POSTALCODE, EXPIREDATE, REFERENCENO, CARDBRAND, LOC4DIGIT, PARTNER, CHANNEL, FUNDING_SOURCE, MER_CARDNAME, SENDER_COUTRY_SUB, MER_CARD_ID, DEVICE_ID, ADD_MSG, ACCOUNTDEBIT, STATUS, CREATEDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '06', SYSDATE)";
    /* MASTERPASS */

 /* VISA */
    final String GETVISAREFNO = "SELECT * FROM MVISAQR WHERE REFNO = ? AND PARTNER = ?";
    final String INSERTVISAQR = "INSERT INTO MVISAQR (SEQUENCENO, REFNO, SYSTRACEAUDITNUM, RETRIEVALREFNUM, LOC4DIGIT, MERPRIMARYACCNUM, AMOUNT, LOCALTRANSDATE, MERCATEGORYCODE, ACQUIRERCOUNTRYCODE, TRANSFEEAMT, ACQUIRINGBIN, TRANSCURRENCYCODE, SECONDARYID, BUSINESSAPPID, SENDERREF, SENDERACCNUM, SENDEREXPDATE, SENDERNAME, SENDERPHONE, SENDEREMAIL, CARDACCNAME, CARDACCTERID, CARDACCIDCODE, CARDACCCITY, CARDACCCOUNTY, CARDACCCOUNTRY, CARDACCZIPCODE, PITYPE, PIREFNUM, PARTNER, ACCOUNTDEBIT, STATUS, CREATEDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '06', SYSDATE)";
    final String INSERTVISAQRRES = "INSERT INTO MVISAQRRES (SEQUENCENO, TRANSACTIONID, TRANSMISSIONDATE, RETRIEVALREFNUM, MERCATEGORYCODE, APPROVALCODE, ACTIONCODE, RESPONSECODE, STATUSID, MERVERVALUE, FEEPROIND, CARDACCNAME, CARDACCTERID, CARDACCIDCODE, CARDACCCITY, CARDACCSTATE, CARDACCCOUNTY, CARDACCCOUNTRY, CARDACCZIPCODE, PITYPE, PIREFNUM, ERRORMESSAGE, RESPONSEMESSAGE, RESSTACODE, RESSTASEVERITY, RESSTAINFO, RESSTASTATUS, RESSTAMESSAGE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATEVISAQRSTATUS = "UPDATE MVISAQR SET STATUS = ? WHERE SEQUENCENO = ?";
    /* VISA */

 /* EVNHCM */
    private static final String GETOUTPBLBILLPAIDCOLLATECB = "SELECT * FROM SMS_SCB.PBL_BILLPAID T WHERE T.IDPARTNER = '%s' AND T.REF_FCUBS IS NOT NULL AND TRUNC(T.TRANSDATE) = TRUNC(SYSDATE-%s) AND T.STATUS IN ('D', 'H') AND T.DATAXML IS NOT NULL ORDER BY T.TRANSDATE DESC";
    private static final String GETOUTPBLBILLPAIDCOLLATE = "SELECT * FROM SMS_SCB.PBL_BILLPAID T WHERE T.IDPARTNER = '%s' AND T.REF_FCUBS IS NOT NULL AND T.STATUS IN ('D', 'H') AND T.DATAXML IS NOT NULL ORDER BY T.TRANSDATE DESC";
    private static final String GETPBLBILLPAIDPAYOOLOG = "SELECT * FROM PBL_PAYOOLOG T WHERE T.IDSERVICETYPE = 'DIEN' AND T.IDPROVIDER = 'EVNHCM' AND T.CUSTOMERCODE = '%s' ORDER BY T.INSDATE DESC";
    /* EVNHCM */

 /* TIMER_SMS */
    private static final String CHECKREGISTERSMS = "SELECT * FROM SMS_SCB.SMS_CUST_ALERTTD_PHONE T WHERE T.ISDELETED = 0 AND T.MOBILE = ?";
    private static final String CHECKSMSCUSTOMERVIP = "SELECT * FROM SMS_SCB.SMS_RECEIVER_PHONE T WHERE T.MOBILE = ?";
    private static final String GETDEFAULTPHONE = "SELECT T.OPERATIVEACCTNO AS OPERATIVEACCTNO FROM SMS_SCB.SMS_DEFAULT_ACC T WHERE T.IDCHANNELUSER = ?";
    private static final String GETACCBALANCE = "select acy_avl_bal as numavailbal, ccy as codacctcurr from VW_CUST_ACCOUNT where cust_ac_no=?";
    private static final String UPDATEREGISTERSMS = "UPDATE SMS_SCB.SMS_DEFAULT_ACC SET OPERATIVEACCTNO = ? WHERE IDCHANNELUSER = ?";
    private static final String INSERTDEFAULTACC = "INSERT INTO SMS_SCB.SMS_DEFAULT_ACC (OPERATIVEACCTNO, IDCHANNELUSER, CREATEDATE) VALUES (?, ?, SYSDATE)";
    private static final String CHECKDEFAUTLACC = "SELECT * FROM SMS_SCB.SMS_DEFAULT_ACC T WHERE T.IDCHANNELUSER = ?";

    /*
     * Author: LYDTY
     * Desc: Check Customerinfo with ATM System
     * @return 
     */
    final String PROC_IB_ACCOUNT = "BEGIN PROC_IB_ACCOUNT(?,?); END;";
    final String RegisterProfileidWithAccount = "BEGIN PAYMENTBYCARD.RegisterProfileidWithAccount(?,?,?,?,?,?,?); END;";
    final String UpdateRegisterProfileidWithACC = "BEGIN PAYMENTBYCARD.UpdateRegisterProfileidWithACC(?,?,?,?,?,?); END;";

    // THANH TOAN BANG PROFILEID CO XAC THUC
    final String PAYMENTWITHPROFILEIDBYVERIFY = "BEGIN  PAYMENTBYCARD.PAYMENTWITHPROFILEIDBYVERIFY(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    //THEM HAM CHECK PROFILEID
    final String checkProfileIDForPayment = "BEGIN  PAYMENTBYCARD.checkProfileIDForPayment(?,?,?,?,?); END;";

    //THEM HAM CHECK KYC
    final String checkKYC = "BEGIN  PAYMENTBYCARD.checkKYC(?,?,?,?,?,?); END;";
    final String REFUND_FOR_TAKEOUT = "BEGIN PAYMENTBYCARD.REFUND_FOR_TAKEOUT(?,?,?,?,?,?,?,?,?,?); END;";

    final String onl_answerCollateData = "BEGIN PROC_ONL_CollatedData(?,?,?); END;";

    /**
     *
     * @param PartnerID
     * @param TransID
     * @param CardNumber
     * @param CardHolderName
     * @param CardDate
     * @param MerchantId
     * @param Amount
     * @param CCY
     * @param Language
     * @param ClientID
     * @param LocalDate
     * @param AddInfo
     * @param ChannelID
     * @param URLAuthen
     * @param pIDVerify
     * @return
     * @throws SQLException
     */
    public String checkCustomerInfo(String PartnerID, String TransID,
            String CardNumber, String CardHolderName,
            String CardDate, String MerchantId,
            Double Amount, String CCY, String Language,
            String ClientID, String LocalDate, String AddInfo,
            String ChannelID, String URLAuthen, String pIDVerify) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(checkData);
            calStmt.setString(1, PartnerID);
            calStmt.setString(2, TransID);
            calStmt.setString(3, CardNumber);
            calStmt.setString(4, CardHolderName);
            calStmt.setString(5, CardDate);
            calStmt.setString(6, MerchantId);
            calStmt.setDouble(7, Amount);
            calStmt.setString(8, CCY);
            calStmt.setString(9, Language);
            calStmt.setString(10, ClientID);
            calStmt.setString(11, LocalDate);
            calStmt.setString(12, AddInfo);
            calStmt.setString(13, ChannelID);
            calStmt.setString(14, URLAuthen);
            calStmt.setString(15, pIDVerify);
            calStmt.registerOutParameter(16, Types.VARCHAR);
            calStmt.execute();
            return calStmt.getString(16);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pID
     * @param pRefID
     * @throws SQLException
     */
    public void TransferOnlinePayment(int pID, String pRefID
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(TransferData);
            calStmt.setInt(1, pID);
            calStmt.setString(2, pRefID);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pID
     * @param pURLREDIREC
     * @param pStatus
     * @throws SQLException
     */
    public void CommitTransfer(int pID, String pURLREDIREC, String pStatus
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(CommitTransfer);
            calStmt.setInt(1, pID);
            calStmt.setString(2, pURLREDIREC);
            calStmt.setString(3, pStatus);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param ID
     * @param pCoreref
     * @param pStatus
     * @throws SQLException
     */
    public void UpdateRefundTransfer(String ID, String pCoreref, String pStatus) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(UpdateRefundTransfer);
            calStmt.setString(1, ID);
            calStmt.setString(2, pCoreref);
            calStmt.setString(3, pStatus);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pPartnerID
     * @param pMerchanID
     * @param pTransID
     * @param pRefundTransID
     * @param pRefundAmount
     * @param pCCY
     * @param pADDINFO
     * @param pLocalDatetime
     * @return
     * @throws SQLException
     */
    public String[] CheckRefundTransfer(String pPartnerID, String pMerchanID,
            String pTransID, String pRefundTransID,
            BigDecimal pRefundAmount, String pCCY, String pADDINFO, String pLocalDatetime) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(CheckRefundTransfer);
            calStmt.setString(1, pPartnerID);
            calStmt.setString(2, pMerchanID);
            calStmt.setString(3, pTransID);
            calStmt.setString(4, pRefundTransID);
            calStmt.setBigDecimal(5, pRefundAmount);
            calStmt.setString(6, pCCY);
            calStmt.setString(7, pADDINFO);
            calStmt.setString(8, pLocalDatetime);
            calStmt.registerOutParameter(9, Types.VARCHAR); //status
            calStmt.registerOutParameter(10, Types.VARCHAR); //acc customer
            calStmt.registerOutParameter(11, Types.VARCHAR); //acc partner
            calStmt.registerOutParameter(12, Types.VARCHAR); //ID
            calStmt.execute();
            String Status = calStmt.getString(9);
            String AccCustomer = calStmt.getString(10);
            String AccPartner = calStmt.getString(11);
            String ID = calStmt.getString(12);

            String[] ArrayResult = new String[4];
            ArrayResult[0] = Status;
            ArrayResult[1] = AccCustomer;
            ArrayResult[2] = AccPartner;
            ArrayResult[3] = ID;
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param PartnerID
     * @param TransID
     * @param MerchantID
     * @param Amount
     * @param CCY
     * @param QTransID
     * @return
     * @throws SQLException
     */
    public String[] QuerryTransfer(String PartnerID, String TransID, String MerchantID,
            Double Amount, String CCY, String QTransID
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(QuerryTransfer);
            calStmt.setString(1, TransID);
            calStmt.setString(2, MerchantID);
            calStmt.setString(3, PartnerID);
            calStmt.setDouble(4, Amount);
            calStmt.setString(5, CCY);
            calStmt.setString(6, QTransID);
            calStmt.registerOutParameter(7, Types.VARCHAR);
            calStmt.registerOutParameter(8, Types.VARCHAR);
            calStmt.registerOutParameter(9, Types.VARCHAR);
            calStmt.execute();
            String Status = calStmt.getString(7);
            String Desc = calStmt.getString(8);
            String BankID = calStmt.getString(9);
            String[] ArrayResult = new String[3];
            ArrayResult[0] = Status;
            ArrayResult[1] = Desc;
            ArrayResult[2] = BankID;
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pEbankUserID
     * @param pIDVerify
     * @return
     * @throws Exception
     */
    public String[] getPaymentOnlineInfo(String pEbankUserID, String pIDVerify)
            throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getPaymentOnlineInfo);
            calStmt.setString(1, pEbankUserID);
            calStmt.setString(2, pIDVerify);
            calStmt.registerOutParameter(3, Types.DOUBLE);
            calStmt.registerOutParameter(4, Types.VARCHAR);
            calStmt.registerOutParameter(5, Types.VARCHAR);
            calStmt.registerOutParameter(6, Types.VARCHAR);
            calStmt.registerOutParameter(7, Types.VARCHAR);
            calStmt.registerOutParameter(8, Types.VARCHAR);
            calStmt.registerOutParameter(9, Types.VARCHAR);//partnerid
            calStmt.registerOutParameter(10, Types.VARCHAR);//transid
            calStmt.registerOutParameter(11, Types.VARCHAR);//cardnumber
            calStmt.registerOutParameter(12, Types.VARCHAR); //merchantname
            calStmt.registerOutParameter(13, Types.VARCHAR); //merchantid
            calStmt.registerOutParameter(14, Types.VARCHAR); //CCY
            calStmt.execute();
            String Amount = calStmt.getString(3);
            String PartnerName = calStmt.getString(4);
            String ID = calStmt.getString(5);
            String Status = calStmt.getString(6);
            String AccCust = calStmt.getString(7);
            String AccPart = calStmt.getString(8);
            String PartnerID = calStmt.getString(9);
            String TransID = calStmt.getString(10);
            String CardNumber = calStmt.getString(11);
            String MerchantName = calStmt.getString(12);
            String MerchantID = calStmt.getString(13);
            String CCY = calStmt.getString(14);

            String[] ArrayResult = new String[12];
            ArrayResult[3] = Status;
            ArrayResult[2] = ID;
            ArrayResult[1] = PartnerName;
            ArrayResult[0] = Amount;
            ArrayResult[4] = AccCust;
            ArrayResult[5] = AccPart;
            ArrayResult[6] = PartnerID;
            ArrayResult[7] = TransID;
            ArrayResult[8] = CardNumber;
            ArrayResult[9] = MerchantName;
            ArrayResult[10] = MerchantID;
            ArrayResult[11] = CCY;
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pID
     * @param pPhoneNumber
     * @param pOTP
     * @param pIDAddress
     * @throws SQLException
     */
    public void insertOTPSMS(int pID, String pPhoneNumber, String pOTP, String pIDAddress)
            throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertOTPSMS);
            calStmt.setInt(1, pID);
            calStmt.setString(2, pPhoneNumber);
            calStmt.setString(3, pOTP);
            calStmt.setString(4, pIDAddress);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /* pID number,
     pTimeOut number,
     pStatus out int,
     pOTPSMS out varchar2*/
    /**
     *
     * @param pID
     * @param pTimeOut
     * @return
     * @throws SQLException
     */
    public String[] getOTPSMS(int pID, String pTimeOut)
            throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getOTPSMS);
            calStmt.setInt(1, pID);
            calStmt.setString(2, pTimeOut);
            calStmt.registerOutParameter(3, Types.INTEGER); //pStatus
            calStmt.registerOutParameter(4, Types.VARCHAR); //pOTPSMS
            calStmt.execute();
            String Status = calStmt.getString(3);
            String OPTSMS = calStmt.getString(4);

            String[] ArrayResult = new String[2];
            ArrayResult[0] = Status;
            ArrayResult[1] = OPTSMS;
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pID
     * @param pUserID
     * @param pClienID
     * @return
     * @throws SQLException
     */
    public int checkSessionForTransfer(String pID, String pUserID, String pClienID) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(checkSessionForTransfer);
            calStmt.setString(1, pID);
            calStmt.setString(2, pUserID);
            calStmt.setString(3, pClienID);
            calStmt.registerOutParameter(4, Types.INTEGER); //pResultRow
            calStmt.execute();
            int ResultRow = calStmt.getInt(4);
            return ResultRow;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }
    //Doi soat

    /*
     Ghi nhan du lieu tu file doi soat cua Partner
     */
    /**
     *
     * @param collate
     * @throws SQLException
     */
    public void ReceiveCollateData(PayOnlineCollate collate) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(receiveCollateData);
            calStmt.setString(1, collate.getPartnerID());
            calStmt.setString(2, collate.getResultCollate());
            calStmt.setString(3, collate.getTypeTrans());
            calStmt.setString(4, collate.getCCY());
            calStmt.setBigDecimal(5, collate.getAmount());
            calStmt.setString(6, collate.getTransid());
            calStmt.setString(7, collate.getTransdate());
            calStmt.setString(8, collate.getMerchantid());
            calStmt.setString(9, collate.getBankTransid());
            calStmt.setString(10, collate.getResponsecode());
            calStmt.setString(11, collate.getCardnumber());
            calStmt.setString(12, collate.getTypedevice());
            calStmt.setString(13, collate.getPaymentTransid());
            calStmt.setString(14, collate.getTypeService());
            calStmt.setString(15, collate.getMac());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /*
     Tra loi file doi soat cho Partner
     */
    /**
     *
     * @param pPartnerID
     * @param pTransdate
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public List<PayOnlineCollate> AnswerCollateData(String pPartnerID, String pTransdate) throws SQLException, Exception {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            List<PayOnlineCollate> resultList = new ArrayList<PayOnlineCollate>();
            calStmt = connection.prepareCall(answerCollateData);
            calStmt.setString(1, pPartnerID);
            calStmt.setString(2, pTransdate);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.executeQuery();
            rs = (ResultSet) calStmt.getObject(3);
            if (rs == null) {
                LOGGER.warn("Not found");
                throw new Exception("Not found");
            }
            while (rs.next()) {
                PayOnlineCollate collate = new PayOnlineCollate();
                collate.setPartnerID(rs.getString("PARTNERID"));
                collate.setResultCollate(rs.getString("RESULTCOLLATE"));
                collate.setTypeTrans(rs.getString("TYPETRANS"));
                collate.setCCY(rs.getString("CCY"));
                collate.setAmount(rs.getBigDecimal("Amount"));
                collate.setTransid(rs.getString("TRANSID"));
                collate.setTransdate(rs.getString("Transdate"));
                collate.setMerchantid(rs.getString("MERCHANTID") == null ? "" : rs.getString("MERCHANTID"));
                collate.setBankTransid(rs.getString("Banktransid") == null ? "" : rs.getString("Banktransid"));
                collate.setResponsecode(rs.getString("RESPONSECODE") == null ? "" : rs.getString("RESPONSECODE"));
                collate.setCardnumber(rs.getString("CARDNUMBER") == null ? "" : rs.getString("CARDNUMBER"));
                collate.setTypedevice(rs.getString("TYPEDEVICE") == null ? "" : rs.getString("TYPEDEVICE"));
                collate.setPaymentTransid(rs.getString("PAYMENTTRANSID") == null ? "" : rs.getString("PAYMENTTRANSID"));
                collate.setTypeService(rs.getString("TYPESERVICE") == null ? "" : rs.getString("TYPESERVICE"));
                resultList.add(collate);
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param IDverify
     * @return
     * @throws Exception
     */
    public ArrayList<?> getDataByVerifyID(String IDverify) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(IDverify);
        return JDBCEngine.executeQuery(getDataByVerifyID, p_args, connection);
    }

    /**
     *
     * @param pPartnerID
     * @param pTransdate
     * @return
     * @throws SQLException
     */
    public int isCollate(String pPartnerID, String pTransdate) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(isCollate);
            calStmt.setString(1, pPartnerID);
            calStmt.setString(2, pTransdate);
            calStmt.registerOutParameter(3, Types.INTEGER); //pCount
            calStmt.execute();
            int ResultRow = calStmt.getInt(3);
            return ResultRow;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    /**
     *
     * @param pVerifyID
     * @param pTypeFailed
     * @throws SQLException
     */
    public void insertFailed(String pVerifyID, String pTypeFailed) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertFailed);
            calStmt.setString(1, pVerifyID);
            calStmt.setString(2, pTypeFailed);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    /**
     *
     * @param pVerifyID
     * @param pTypeFailed
     * @return
     * @throws SQLException
     */
    public int checkFailed(String pVerifyID, String pTypeFailed) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(checkFailed);
            calStmt.setString(1, pVerifyID);
            calStmt.setString(2, pTypeFailed);
            calStmt.registerOutParameter(3, Types.INTEGER); //pResultRow
            calStmt.execute();
            int ResultRow = calStmt.getInt(3);
            return ResultRow;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pTransID
     * @param pPartnerID
     * @return
     * @throws SQLException
     */
    public int CheckBeforeTransfer(String pTransID, String pPartnerID) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(CheckBeforeTransfer);
            calStmt.setString(1, pTransID);
            calStmt.setString(2, pPartnerID);
            calStmt.registerOutParameter(3, Types.INTEGER); //pResultRow
            calStmt.execute();
            int ResultRow = calStmt.getInt(3);
            return ResultRow;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param PartnerID
     * @param TransID
     * @param CardNumber
     * @param CardName
     * @param MerchantId
     * @param Amount
     * @param CCY
     * @param LocalDate
     * @param AddInfo
     * @param ChannelID
     * @param URLAuthen
     * @param pIDVerify
     * @return
     * @throws SQLException
     */
    public String[] SMLCheckData(String PartnerID, String TransID,
            String CardNumber,
            String CardName,
            String MerchantId,
            Double Amount,
            String CCY,
            String LocalDate,
            String AddInfo,
            String ChannelID,
            String URLAuthen,
            String pIDVerify) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(SMLCheckData);
            calStmt.setString(1, PartnerID);
            calStmt.setString(2, TransID);
            calStmt.setString(3, CardNumber);
            calStmt.setString(4, CardName);
            calStmt.setString(5, MerchantId);
            calStmt.setDouble(6, Amount);
            calStmt.setString(7, CCY);
            calStmt.setString(8, LocalDate);
            calStmt.setString(9, AddInfo);
            calStmt.setString(10, ChannelID);
            calStmt.setString(11, URLAuthen);
            calStmt.setString(12, pIDVerify);
            calStmt.registerOutParameter(13, Types.VARCHAR); //status
            calStmt.registerOutParameter(14, Types.VARCHAR); // banktransid
            calStmt.execute();

            String[] ArrayResult = new String[2];
            ArrayResult[0] = calStmt.getString(13);
            ArrayResult[1] = calStmt.getString(14);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pPartnerID
     * @param pCollatedDate
     * @throws SQLException
     */
    public void InsertCollateddate(String pPartnerID, Date pCollatedDate) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(InsertCollateddate);
            calStmt.setString(1, pPartnerID);
            java.sql.Date sqlDate = new java.sql.Date(pCollatedDate.getTime());
            calStmt.setDate(2, sqlDate);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pPartnerID
     * @return
     * @throws SQLException
     */
    public Date getMaxCollateDate(String pPartnerID) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getMaxCollateDate);
            calStmt.setString(1, pPartnerID);
            calStmt.registerOutParameter(2, Types.DATE); //pResultRow
            calStmt.execute();
            Date ResultRow = calStmt.getDate(2);
            return ResultRow;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param sml
     * @return
     * @throws SQLException
     */
    public int InsertSMLEComCollated(SMLCollate sml) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertSMLCollate);
            calStmt.setString(1, sml.getACCOUNTNO());
            calStmt.setString(2, sml.getPROCESSINGCODDE());
            calStmt.setString(3, sml.getAMOUNT());
            calStmt.setString(4, sml.getAUDITNUMBER());
            calStmt.setString(5, sml.getTRANSTIME());
            calStmt.setString(6, sml.getTRANSDATE());
            calStmt.setString(7, sml.getPAYDATE());
            calStmt.setString(8, sml.getMERCHANTTYPE());
            calStmt.setString(9, sml.getACQUIRINGCODE());
            calStmt.setString(10, sml.getAUTHORIZATIONNUMBER());
            calStmt.setString(11, sml.getTERMID());
            calStmt.setString(12, sml.getCCY());
            calStmt.setString(13, sml.getSOURCEACCOUNT());
            calStmt.setString(14, sml.getDESTACCOUNT());
            calStmt.setString(15, sml.getRC());
            calStmt.setString(16, sml.getCHECKSUM());
            calStmt.setString(17, sml.getFILETYPE());
            calStmt.registerOutParameter(18, OracleTypes.NUMBER);
            calStmt.execute();
            int result = calStmt.getInt(18);
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public List<SMLCollate> getOutSMLEcomCollate() throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(getOutSMLEComCollate);
            calStmt.registerOutParameter(1, OracleTypes.CURSOR);
            calStmt.executeQuery();
            List<SMLCollate> resultList = new ArrayList<SMLCollate>();
            rs = (ResultSet) calStmt.getObject(1);
            if (rs == null) {
                LOGGER.warn("Not found");
                throw new Exception("Not found");
            }
            while (rs.next()) {
                SMLCollate collate = new SMLCollate();
                collate.setACCOUNTNO(rs.getString("CARDNUMBER"));
                collate.setPROCESSINGCODDE(rs.getString("PROCESSINGCODE"));
                collate.setAMOUNT(rs.getString("AMOUNT"));
                collate.setAUDITNUMBER(rs.getString("AUDITNUMBER"));
                collate.setTRANSTIME(rs.getString("TRANSTIME"));
                collate.setTRANSDATE(rs.getString("TRANSDATE"));
                collate.setPAYDATE(rs.getString("PAYDATE"));
                collate.setMERCHANTTYPE(rs.getString("MERCHANTTYPE"));
                collate.setACQUIRINGCODE(rs.getString("ACQUIRINGCODE"));
                collate.setAUTHORIZATIONNUMBER(rs.getString("AUTHORIZATIONNUMBER"));
                collate.setTERMID(rs.getString("TERMID"));
                collate.setCCY(rs.getString("CCY"));
                collate.setSOURCEACCOUNT(rs.getString("SOURCEACCOUNT"));
                collate.setDESTACCOUNT(rs.getString("DESTACCOUNT"));
                collate.setRC(rs.getString("RC"));
                resultList.add(collate);
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pUserID
     * @return
     * @throws SQLException
     */
    public int checkTokenByUserID(String pUserID) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(checkTokenByUserID);
            calStmt.setString(1, pUserID);
            calStmt.registerOutParameter(2, OracleTypes.NUMBER);
            calStmt.executeQuery();
            int result = calStmt.getInt(2);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return 0;
    }

    /**
     *
     * @param PTRANSID
     * @param PTRANSDATE
     * @param PVERIFYTYPE
     * @param PREFTRANSID
     * @param POTPSMS
     * @param PPARTNERID
     * @return
     * @throws SQLException
     */
    public String[] VERIFYOTPSMS(String PTRANSID,
            String PTRANSDATE,
            String PVERIFYTYPE,
            String PREFTRANSID,
            String POTPSMS,
            String PPARTNERID
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(VERIFYOTPSMS);
            calStmt.setString(1, PTRANSID);
            calStmt.setString(2, PTRANSDATE);
            calStmt.setString(3, PVERIFYTYPE);
            calStmt.setString(4, PREFTRANSID);
            calStmt.setString(5, POTPSMS);
            calStmt.setString(6, PPARTNERID);
            calStmt.registerOutParameter(7, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(8, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(9, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(11, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(12, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(13, OracleTypes.VARCHAR);

            calStmt.execute();
            String[] ArrayResult = new String[7];
            ArrayResult[0] = calStmt.getString(7);;
            ArrayResult[1] = calStmt.getString(8);;
            ArrayResult[2] = calStmt.getString(9);
            ArrayResult[3] = calStmt.getString(10);
            ArrayResult[4] = calStmt.getString(11);
            ArrayResult[5] = calStmt.getString(12);
            ArrayResult[6] = calStmt.getString(13);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PPROFILEID
     * @param PAMOUNT
     * @param PCCY
     * @param PTRANSDATE
     * @param PPARTNERID
     * @param PDESCRIPTION
     * @param PMERCHANTID
     * @param pChannelID
     * @return
     * @throws SQLException
     */
    public String[] PAYMENTWITHPROFILEID(String PTRANSID,
            String PPROFILEID,
            Double PAMOUNT,
            String PCCY,
            String PTRANSDATE,
            String PPARTNERID,
            String PDESCRIPTION,
            String PMERCHANTID,
            String pChannelID
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(PAYMENTWITHPROFILEID);
            calStmt.setString(1, PTRANSID);
            calStmt.setString(2, PPROFILEID);
            calStmt.setDouble(3, PAMOUNT);
            calStmt.setString(4, PCCY);
            calStmt.setString(5, PTRANSDATE);
            calStmt.setString(6, PPARTNERID);
            calStmt.setString(7, PDESCRIPTION);
            calStmt.setString(8, PMERCHANTID);//VIMO_01: payment/VIMO_02: wallet
            calStmt.setString(9, pChannelID);//MOB/INT
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(11, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(12, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(13, OracleTypes.VARCHAR);

            calStmt.execute();
            String[] ArrayResult = new String[4];
            ArrayResult[0] = calStmt.getString(10);
            ArrayResult[1] = calStmt.getString(11);
            ArrayResult[2] = calStmt.getString(12);
            ArrayResult[3] = calStmt.getString(13);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param ITRANSID
     * @param ICUSTACCCOUNT
     * @param IAMOUNT
     * @param ICCY
     * @param ITRANSDATE
     * @param IPARTNERID
     * @param IDESCRIPTION
     * @param IMERCHANTID
     * @return
     * @throws SQLException
     */
    public String[] PAYMENTWITHACCOUNT(String ITRANSID, String ICUSTACCCOUNT,
            Double IAMOUNT, String ICCY, String ITRANSDATE, String IPARTNERID,
            String IDESCRIPTION, String IMERCHANTID) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(PAYMENTWITHACCOUNT);
            calStmt.setString(1, ITRANSID);
            calStmt.setString(2, ICUSTACCCOUNT);
            calStmt.setDouble(3, IAMOUNT);
            calStmt.setString(4, ICCY);
            calStmt.setString(5, ITRANSDATE);
            calStmt.setString(6, IPARTNERID);
            calStmt.setString(7, IDESCRIPTION);
            calStmt.setString(8, IMERCHANTID);
            calStmt.registerOutParameter(9, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(11, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(12, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[4];
            ArrayResult[0] = calStmt.getString(9);
            ArrayResult[1] = calStmt.getString(10);
            ArrayResult[2] = calStmt.getString(11);
            ArrayResult[3] = calStmt.getString(12);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param PTRANSID
     * @param pCardNumber
     * @param pCardHolderName
     * @param pCardDate
     * @param PAMOUNT
     * @param PCCY
     * @param PTRANSDATE
     * @param PPARTNERID
     * @param PDESCRIPTION
     * @param PMERCHANTID
     * @param pChannelID
     * @param OTP
     * @return
     * @throws SQLException
     */
    public String[] PAYMENT(String PTRANSID,
            String pCardNumber,
            String pCardHolderName,
            String pCardDate,
            Double PAMOUNT,
            String PCCY,
            String PTRANSDATE,
            String PPARTNERID,
            String PDESCRIPTION,
            String PMERCHANTID,
            String pChannelID,
            String OTP
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(PAYMENT);
            calStmt.setString(1, PTRANSID);
            calStmt.setString(2, pCardNumber);
            calStmt.setString(3, pCardHolderName);
            calStmt.setString(4, pCardDate);
            calStmt.setDouble(5, PAMOUNT);
            calStmt.setString(6, PCCY);
            calStmt.setString(7, PTRANSDATE);
            calStmt.setString(8, PPARTNERID);
            calStmt.setString(9, PDESCRIPTION);
            calStmt.setString(10, PMERCHANTID);//VIMO_01: payment/VIMO_02: wallet
            calStmt.setString(11, pChannelID);//MOB/INT
            calStmt.setString(12, OTP);
            calStmt.registerOutParameter(13, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(14, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(15, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[3];
            ArrayResult[0] = calStmt.getString(13);
            ArrayResult[1] = calStmt.getString(14);
            ArrayResult[2] = calStmt.getString(15);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pBankid
     * @param pStatus
     * @param pRefcore
     * @throws SQLException
     */
    public void UpdatePayment(String pBankid, String pStatus, String pRefcore) throws SQLException {
        PreparedStatement preStatement = null;
        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(UPDATEPAYMENT);
            preStatement.setString(1, pStatus);
            preStatement.setString(2, pRefcore);
            preStatement.setString(3, pBankid);
            preStatement.executeUpdate();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pDestNumber
     * @param Ptypedestnumber
     * @return
     * @throws SQLException
     */
    public String[] checkDestNumber(String pDestNumber,
            String Ptypedestnumber) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(checkDestNumber);
            calStmt.setString(1, pDestNumber);
            calStmt.setString(2, Ptypedestnumber);
            calStmt.registerOutParameter(3, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(4, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(5, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[3];
            ArrayResult[0] = calStmt.getString(3); //pDestName
            ArrayResult[1] = calStmt.getString(4);//status
            ArrayResult[2] = calStmt.getString(5);//pAccount
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PDESTNUMBER
     * @param pSOURCEACCOUNT
     * @param PAMOUNT
     * @param PCCY
     * @param PTRANSDATE
     * @param PDESRCRIPTION
     * @param PPARTNERID
     * @param Ptypedestnumber
     * @return
     * @throws SQLException
     */
    public String[] TAKEOUTWALLET(String PTRANSID,
            String PDESTNUMBER,
            String pSOURCEACCOUNT,
            Double PAMOUNT,
            String PCCY,
            String PTRANSDATE,
            String PDESRCRIPTION,
            String PPARTNERID,
            String Ptypedestnumber) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(TAKEOUTWALLET);
            calStmt.setString(1, PTRANSID);
            calStmt.setString(2, PDESTNUMBER);
            calStmt.setString(3, pSOURCEACCOUNT);
            calStmt.setDouble(4, PAMOUNT);
            calStmt.setString(5, PCCY);
            calStmt.setString(6, PTRANSDATE);
            calStmt.setString(7, PDESRCRIPTION);
            calStmt.setString(8, PPARTNERID);
            calStmt.setString(9, Ptypedestnumber);
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(11, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(12, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[3];
            ArrayResult[0] = calStmt.getString(10); //pDestName
            ArrayResult[1] = calStmt.getString(11);//status
            ArrayResult[2] = calStmt.getString(12);//pAccount
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param PBANKID
     * @param PREFCORE
     * @throws SQLException
     */
    public void UPDATE_TAKEOUTWALLET(String PBANKID,
            String PREFCORE) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(UPDATE_TAKEOUTWALLET);
            calStmt.setString(1, PBANKID);
            calStmt.setString(2, PREFCORE);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PPARTNERID
     * @return
     * @throws SQLException
     */
    public String[] REVERT_TAKEOUTWALLET(String PTRANSID,
            String PPARTNERID) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(REVERT_TAKEOUTWALLET);
            calStmt.setString(1, PTRANSID);
            calStmt.setString(2, PPARTNERID);
            calStmt.registerOutParameter(3, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(4, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(5, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(6, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(7, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[5];
            ArrayResult[0] = calStmt.getString(3); //refcore
            ArrayResult[1] = calStmt.getString(4);//status
            ArrayResult[2] = calStmt.getString(5);//Amount
            ArrayResult[3] = calStmt.getString(6);//Destnumber
            ArrayResult[4] = calStmt.getString(7);//SourceAccount
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PREVERTTRANSID
     * @param PPARTNERID
     * @param PTRANSDATE
     * @param PDESC
     * @throws SQLException
     */
    public void UPDATE_REVERT_TAKEOUTWALLET(String PTRANSID,
            String PREVERTTRANSID,
            String PPARTNERID,
            String PTRANSDATE,
            String PDESC) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(UPDATE_REVERT_TAKEOUTWALLET);
            calStmt.setString(1, PTRANSID);
            calStmt.setString(2, PREVERTTRANSID);
            calStmt.setString(3, PPARTNERID);
            calStmt.setString(4, PTRANSDATE);
            calStmt.setString(5, PDESC);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pTransid
     * @param pRefTransid
     * @param pAmount
     * @param pTransdate
     * @param pDescription
     * @param pPartnerid
     * @return
     * @throws SQLException
     */
    public String[] REFUND(String pTransid,
            String pRefTransid,
            Double pAmount,
            String pTransdate,
            String pDescription,
            String pPartnerid) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(REFUND);
            calStmt.setString(1, pTransid);
            calStmt.setString(2, pRefTransid);
            calStmt.setDouble(3, pAmount);
            calStmt.setString(4, pTransdate);
            calStmt.setString(5, pDescription);
            calStmt.setString(6, pPartnerid);
            calStmt.registerOutParameter(7, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(8, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(9, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[4];
            ArrayResult[0] = calStmt.getString(7);
            ArrayResult[1] = calStmt.getString(8);
            ArrayResult[2] = calStmt.getString(9);
            ArrayResult[3] = calStmt.getString(10);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param PID
     * @param PREFCORE
     * @throws SQLException
     */
    public void UPDATE_REFUND(String PID, String PREFCORE) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(UPDATE_REFUND);
            calStmt.setString(1, PID);
            calStmt.setString(2, PREFCORE);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pRefTransid
     * @param pTRANSTYPE
     * @param pParnerid
     * @return
     * @throws SQLException
     */
    public String[] queryTransaction(
            String pRefTransid,
            String pTRANSTYPE,
            String pParnerid) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(queryTransaction);
            calStmt.setString(1, pRefTransid);
            calStmt.setString(2, pTRANSTYPE);
            calStmt.setString(3, pParnerid);
            calStmt.registerOutParameter(4, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(5, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(6, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(7, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[4];
            ArrayResult[0] = calStmt.getString(4);
            ArrayResult[1] = calStmt.getString(5);
            ArrayResult[2] = calStmt.getString(6);
            ArrayResult[3] = calStmt.getString(7);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PTRANSDATE
     * @param pProfileID
     * @param pDescription
     * @param pPartnerid
     * @param pOTP
     * @return
     * @throws SQLException
     */
    public String[] DestroyConnectCard(String PTRANSID,
            String PTRANSDATE,
            String pProfileID,
            String pDescription,
            String pPartnerid,
            String pOTP) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(DestroyConnectCard);
            calStmt.setString(1, PTRANSID);
            calStmt.setString(2, PTRANSDATE);
            calStmt.setString(3, pProfileID);
            calStmt.setString(4, pDescription);
            calStmt.setString(5, pPartnerid);
            calStmt.setString(6, pOTP);
            calStmt.registerOutParameter(7, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(8, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[2];
            ArrayResult[0] = calStmt.getString(7);//status
            ArrayResult[1] = calStmt.getString(8);//phone
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pPartnerid
     * @return
     * @throws SQLException
     */
    public String[] GetMACkeys(String pPartnerid) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getMACkeys);
            calStmt.setString(1, pPartnerid);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            ResultSet rs = (ResultSet) calStmt.getObject(2);
            while (rs.next()) {
                String[] ArrayResult = new String[6];
                ArrayResult[0] = rs.getString("PRIVATEKEY");
                ArrayResult[1] = rs.getString("PUBLICKEY");
                ArrayResult[2] = rs.getString("ISVERIFIEDFORPAYBYPROFILEID");
                ArrayResult[3] = rs.getString("LIMITAMOUNTFORVERIFIED");
                ArrayResult[4] = rs.getString("ACCOUNTNO");
                ArrayResult[5] = rs.getString("KYCID");
                return ArrayResult;
            }
            return null;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pTransID
     * @param pParnerID
     * @throws SQLException
     */
    public void PaymentAndRegister(String pTransID, String pParnerID) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(PaymentAndRegister);
        try {
            calStmt.setString(1, pTransID);
            calStmt.setString(2, pParnerID);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            connection.close();
        }
    }

    /**
     *
     * @param pCardNumber
     * @param pParnerid
     * @return
     * @throws SQLException
     */
    public String getProfileID(
            String pCardNumber,
            String pParnerid) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(getProfileID);
        try {
            calStmt.setString(1, pCardNumber);
            calStmt.setString(2, pParnerid);
            calStmt.registerOutParameter(3, OracleTypes.VARCHAR);
            calStmt.execute();
            return calStmt.getString(3);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            connection.close();
        }
    }

    /* ****************************** NGUYEN DAC BINH MINH ****************************** */
 /* VNPAY */
    /**
     *
     * @param maKH
     * @param cardNo
     * @param cardAccountNo
     * @param accountNo
     * @return
     * @throws SQLException
     */
    public String[] QUERY_PERSONAL_INFORMATION(String maKH, String cardNo, String cardAccountNo, String accountNo) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(QUERY_PERSONAL_INFORMATION);
            calStmt.setString(1, maKH);
            calStmt.setString(2, cardNo);
            calStmt.setString(3, cardAccountNo);
            calStmt.setString(4, accountNo);
            calStmt.registerOutParameter(5, Types.INTEGER); // RESULT
            calStmt.registerOutParameter(6, Types.VARCHAR); // HOTEN
            calStmt.registerOutParameter(7, Types.VARCHAR); // CMND
            calStmt.registerOutParameter(8, Types.VARCHAR); // NGAYCAP
            calStmt.registerOutParameter(9, Types.VARCHAR); // NOICAP
            calStmt.registerOutParameter(10, Types.VARCHAR); // DIACHI
            calStmt.registerOutParameter(11, Types.VARCHAR); // HO
            calStmt.registerOutParameter(12, Types.VARCHAR); // TENDEM_TEN
            calStmt.registerOutParameter(13, Types.VARCHAR); // CARD_TYPE
            calStmt.registerOutParameter(14, Types.VARCHAR); // LOAITHE
            calStmt.registerOutParameter(15, Types.VARCHAR); // BRCH_CODE
            calStmt.registerOutParameter(16, Types.VARCHAR); // THECHINHPHU
            calStmt.registerOutParameter(17, Types.VARCHAR); // TINHTRANGTHE
            calStmt.execute();

            String[] ArrayResult = new String[13];
            ArrayResult[0] = String.valueOf(calStmt.getInt(5));
            ArrayResult[1] = calStmt.getString(6);
            ArrayResult[2] = calStmt.getString(7);
            ArrayResult[3] = calStmt.getString(8);
            ArrayResult[4] = calStmt.getString(9);
            ArrayResult[5] = calStmt.getString(10);
            ArrayResult[6] = calStmt.getString(11);
            ArrayResult[7] = calStmt.getString(12);
            ArrayResult[8] = calStmt.getString(13);
            ArrayResult[9] = calStmt.getString(14);
            ArrayResult[10] = calStmt.getString(15);
            ArrayResult[11] = calStmt.getString(16);
            ArrayResult[12] = calStmt.getString(17);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param IACCOUNT
     * @return
     * @throws SQLException
     */
    public String[] CHECK_ACCOUNT_BALANCE(String IACCOUNT) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(CHECK_ACCOUNT_BALANCE);
            calStmt.setString(1, IACCOUNT);
            calStmt.registerOutParameter(2, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(3, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(4, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(5, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[4];
            ArrayResult[0] = calStmt.getString(2);
            ArrayResult[1] = calStmt.getString(3);
            ArrayResult[2] = calStmt.getString(4);
            ArrayResult[3] = calStmt.getString(5);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pTransid
     * @param pRefTransid
     * @param pAmount
     * @param pTransdate
     * @param pDescription
     * @param pPartnerid
     * @return
     * @throws SQLException
     */
    public String[] REFUND_PAYMENT_WITH_ACCOUNT(String pTransid, String pRefTransid,
            Double pAmount, String pTransdate, String pDescription,
            String pPartnerid) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(REFUND_PAYMENT_WITH_ACCOUNT);
            calStmt.setString(1, pTransid);
            calStmt.setString(2, pRefTransid);
            calStmt.setDouble(3, pAmount);
            calStmt.setString(4, pTransdate);
            calStmt.setString(5, pDescription);
            calStmt.setString(6, pPartnerid);
            calStmt.registerOutParameter(7, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(8, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(9, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[4];
            ArrayResult[0] = calStmt.getString(7);
            ArrayResult[1] = calStmt.getString(8);
            ArrayResult[2] = calStmt.getString(9);
            ArrayResult[3] = calStmt.getString(10);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param accountNumber
     * @return
     * @throws SQLException
     */
    public Object[] getAccountBalance(String accountNumber) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(GET_ACCOUNT_BALANCE);
            calStmt.setString(1, accountNumber);
            calStmt.registerOutParameter(2, OracleTypes.NUMBER); // available balance
            calStmt.registerOutParameter(3, OracleTypes.NUMBER); // current balance
            calStmt.registerOutParameter(4, OracleTypes.VARCHAR); // status
            calStmt.execute();
            Object[] ArrayResult = new Object[3];
            ArrayResult[0] = BigDecimal.valueOf(calStmt.getDouble(2));
            ArrayResult[1] = BigDecimal.valueOf(calStmt.getDouble(3));
            ArrayResult[2] = calStmt.getString(4);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /* VNPAY */
    /**
     *
     * @param TransID
     * @param PartnerID
     * @param PhoneNumber
     * @param ChannelID
     * @param TransType
     * @param OTP
     * @return
     * @throws SQLException
     */
    public String[] OTP_REQUEST(String TransID, String PartnerID, String PhoneNumber,
            String ChannelID, String TransType, String OTP) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(OTP_REQUEST);
            calStmt.setString(1, TransID);
            calStmt.setString(2, PartnerID);
            calStmt.setString(3, PhoneNumber);
            calStmt.setString(4, ChannelID);
            calStmt.setString(5, TransType);
            calStmt.setString(6, OTP);
            calStmt.registerOutParameter(7, OracleTypes.VARCHAR); // status
            calStmt.registerOutParameter(8, OracleTypes.VARCHAR); // bank id
            calStmt.execute();
            String[] ArrayResult = new String[2];
            ArrayResult[0] = calStmt.getString(7);
            ArrayResult[1] = calStmt.getString(8);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param TransID
     * @param RefTransID
     * @param PartnerID
     * @param ChannelID
     * @param TransType
     * @param OTP
     * @param TransDate
     * @return
     * @throws SQLException
     */
    public String[] OTP_RESPONSE(String TransID, String RefTransID, String PartnerID,
            String ChannelID, String TransType, String OTP, String TransDate) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(OTP_RESPONSE);
            calStmt.setString(1, TransID);
            calStmt.setString(2, RefTransID);
            calStmt.setString(3, PartnerID);
            calStmt.setString(4, ChannelID);
            calStmt.setString(5, TransType);
            calStmt.setString(6, OTP);
            calStmt.setString(7, TransDate);
            calStmt.registerOutParameter(8, OracleTypes.VARCHAR); // status
            calStmt.registerOutParameter(9, OracleTypes.VARCHAR); // bank id
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR); // description
            calStmt.execute();
            String[] ArrayResult = new String[3];
            ArrayResult[0] = calStmt.getString(8);
            ArrayResult[1] = calStmt.getString(9);
            ArrayResult[2] = calStmt.getString(10);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /*  ONEPAY */
    /**
     *
     * @param ITRANSID
     * @param IPARTNERID
     * @param ICARDNUMBER
     * @param ICARDNAME
     * @param ICARDDATE
     * @param ITRANSDATE
     * @param IDESCRIPTION
     * @return
     * @throws SQLException
     */
    public String[] ONEPAY_CARD_VERIFICATION(String ITRANSID, String IPARTNERID,
            String ICARDNUMBER, String ICARDNAME, String ICARDDATE, String ITRANSDATE,
            String IDESCRIPTION) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(ONEPAY_CARD_VERIFICATION);
            calStmt.setString(1, ITRANSID);
            calStmt.setString(2, IPARTNERID);
            calStmt.setString(3, ICARDNUMBER);
            calStmt.setString(4, ICARDNAME);
            calStmt.setString(5, ICARDDATE);
            calStmt.setDouble(6, Double.valueOf("00"));
            calStmt.setString(7, ITRANSDATE);
            calStmt.setString(8, IDESCRIPTION);
            calStmt.registerOutParameter(9, OracleTypes.VARCHAR); // status
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR); // bankid
            calStmt.registerOutParameter(11, OracleTypes.VARCHAR); // description
            calStmt.execute();
            String[] ArrayResult = new String[3];
            ArrayResult[0] = calStmt.getString(9);
            ArrayResult[1] = calStmt.getString(10);
            ArrayResult[2] = calStmt.getString(11);
            return ArrayResult;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param ITRANSID
     * @param IREFTRANSID
     * @param IPARTNERID
     * @param ICHANNELID
     * @param IVERIFYTYPE
     * @param IOTP
     * @param ITRANSDATE
     * @param IDESCRIPTION
     * @return
     * @throws SQLException
     */
    public String[] ONEPAY_OTP_VERIFICATION(String ITRANSID, String IREFTRANSID,
            String IPARTNERID, String ICHANNELID, String IVERIFYTYPE, String IOTP,
            String ITRANSDATE, String IDESCRIPTION) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(ONEPAY_OTP_VERIFICATION);
            calStmt.setString(1, ITRANSID);
            calStmt.setString(2, IREFTRANSID);
            calStmt.setString(3, IPARTNERID);
            calStmt.setString(4, ICHANNELID);
            calStmt.setString(5, IVERIFYTYPE);
            calStmt.setString(6, IOTP);
            calStmt.setString(7, ITRANSDATE);
            calStmt.setString(8, IDESCRIPTION);
            calStmt.registerOutParameter(9, OracleTypes.VARCHAR); // status
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR); // bankid
            calStmt.registerOutParameter(11, OracleTypes.NUMBER); // amount
            calStmt.registerOutParameter(12, OracleTypes.VARCHAR); // custAccount
            calStmt.registerOutParameter(13, OracleTypes.VARCHAR); // partnerAccount
            calStmt.execute();
            String[] ArrayResult = new String[5];
            ArrayResult[0] = calStmt.getString(9);
            ArrayResult[1] = calStmt.getString(10);
            ArrayResult[2] = calStmt.getString(11);
            ArrayResult[3] = calStmt.getString(12);
            ArrayResult[4] = calStmt.getString(13);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param ITRANSID
     * @param IREFTRANSID
     * @param IPARTNERID
     * @param IMERCHANTID
     * @param ICARDNUMBER
     * @param ICARDNAME
     * @param ICARDDATE
     * @param IAMOUNT
     * @param ICCY
     * @param ITRANSDATE
     * @param ICHANNELID
     * @param IDESCRIPTION
     * @return
     * @throws SQLException
     */
    public String[] ONEPAY_PAYMENT(String ITRANSID, String IREFTRANSID,
            String IPARTNERID, String IMERCHANTID, String ICARDNUMBER, String ICARDNAME,
            String ICARDDATE, Double IAMOUNT, String ICCY, String ITRANSDATE,
            String ICHANNELID, String IDESCRIPTION) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(ONEPAY_PAYMENT);
            calStmt.setString(1, ITRANSID);
            calStmt.setString(2, IREFTRANSID);
            calStmt.setString(3, IPARTNERID);
            calStmt.setString(4, IMERCHANTID);
            calStmt.setString(5, ICARDNUMBER);
            calStmt.setString(6, ICARDNAME);
            calStmt.setString(7, ICARDDATE);
            calStmt.setDouble(8, IAMOUNT);
            calStmt.setString(9, ICCY);
            calStmt.setString(10, ITRANSDATE);
            calStmt.setString(11, ICHANNELID);
            calStmt.setString(12, IDESCRIPTION);
            calStmt.registerOutParameter(13, OracleTypes.VARCHAR); // status
            calStmt.registerOutParameter(14, OracleTypes.VARCHAR); // bankid
            calStmt.registerOutParameter(15, OracleTypes.VARCHAR); // phone number
            calStmt.execute();
            String[] ArrayResult = new String[3];
            ArrayResult[0] = calStmt.getString(13);
            ArrayResult[1] = calStmt.getString(14);
            ArrayResult[2] = calStmt.getString(15);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param ITRANSID
     * @param IREFTRANSID
     * @param IPARTNERID
     * @param IMERCHANTID
     * @param ICARDNUMBER
     * @param ICARDNAME
     * @param ICARDDATE
     * @param IAMOUNT
     * @param ICCY
     * @param ITRANSDATE
     * @param ICHANNELID
     * @param IDESCRIPTION
     * @return
     * @throws SQLException
     */
    public String[] ONEPAY_PAYMENT_WITHOUT_OTP(String ITRANSID, String IREFTRANSID,
            String IPARTNERID, String IMERCHANTID, String ICARDNUMBER, String ICARDNAME,
            String ICARDDATE, Double IAMOUNT, String ICCY, String ITRANSDATE,
            String ICHANNELID, String IDESCRIPTION) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(ONEPAY_PAYMENT_WITHOUT_OTP);
            calStmt.setString(1, ITRANSID);
            calStmt.setString(2, IREFTRANSID);
            calStmt.setString(3, IPARTNERID);
            calStmt.setString(4, IMERCHANTID);
            calStmt.setString(5, ICARDNUMBER);
            calStmt.setString(6, ICARDNAME);
            calStmt.setString(7, ICARDDATE);
            calStmt.setDouble(8, IAMOUNT);
            calStmt.setString(9, ICCY);
            calStmt.setString(10, ITRANSDATE);
            calStmt.setString(11, ICHANNELID);
            calStmt.setString(12, IDESCRIPTION);
            calStmt.registerOutParameter(13, OracleTypes.VARCHAR); // status
            calStmt.registerOutParameter(14, OracleTypes.VARCHAR); // bankid
            calStmt.registerOutParameter(15, OracleTypes.VARCHAR); // phone number
            calStmt.registerOutParameter(16, OracleTypes.VARCHAR); // phone number
            calStmt.execute();
            String[] ArrayResult = new String[4];
            ArrayResult[0] = calStmt.getString(13);
            ArrayResult[1] = calStmt.getString(14);
            ArrayResult[2] = calStmt.getString(15);
            ArrayResult[3] = calStmt.getString(16);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param RefTransID
     * @param PartnerID
     * @param OTP
     * @param PhoneNumber
     * @param ChannelID
     * @param TransType
     * @throws SQLException
     */
    public void PAYMENT_OTP_ADDING(String RefTransID, String PartnerID, String OTP,
            String PhoneNumber, String ChannelID, String TransType) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(PAYMENT_OTP_ADDING);
            calStmt.setString(1, RefTransID);
            calStmt.setString(2, PartnerID);
            calStmt.setString(3, OTP);
            calStmt.setString(4, PhoneNumber);
            calStmt.setString(5, ChannelID);
            calStmt.setString(6, TransType);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /*  ONEPAY */

 /* DAWACO */
    /**
     *
     * @param date
     * @return
     * @throws SQLException
     */
    public List<DawacoCollate> getOutDAWACOCollate(String date) throws SQLException {
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(String.format(GETOUTDAWACOCOLLATE, date));
            List<DawacoCollate> resultList = new ArrayList<>();
            while (rs.next()) {
                DawacoCollate collate = new DawacoCollate();
                collate.setIdBillPaid(rs.getString("IDBILLPAID"));
                collate.setIdPartner(rs.getString("IDPARTNER"));
                collate.setIdServiceType(rs.getString("IDSERVICETYPE"));
                collate.setIdProvider(rs.getString("IDPROVIDER"));
                collate.setCustomerCode(rs.getString("CUSTOMERCODE"));
                collate.setIdUserMaker(rs.getString("IDUSER_MAKER"));
                collate.setIdChanelUserMaker(rs.getString("IDCHANNELUSER_MAKER"));
                collate.setInsdateMaker(rs.getString("INSDATE_MAKER"));
                collate.setIdUserChecker(rs.getString("IDUSER_CHECKER"));
                collate.setIdChanelUserChecker(rs.getString("IDCHANNELUSER_CHECKER"));
                collate.setInsDateChecker(rs.getString("INSDATE_CHECKER"));
                collate.setIdChanel(rs.getString("IDCHANNEL"));
                collate.setUserType(rs.getString("USERTYPE"));
                collate.setRefPartner(rs.getString("REF_PARTNER"));
                collate.setRefFubs(rs.getString("REF_FCUBS"));
                collate.setTotalAmount(rs.getString("TOTALAMOUNT"));
                collate.setPaymentMethod(rs.getString("PAYMENTMETHOD"));
                collate.setAccCust(rs.getString("ACC_CUST"));
                collate.setAccIdAccount(rs.getString("ACC_IDACCOUNT"));
                collate.setAccHolderName(rs.getString("ACC_HOLDERNAME"));
                collate.setAccAddress(rs.getString("ACC_ADDRESS"));
                collate.setTransDate(rs.getString("TRANSDATE"));
                collate.setPayDate(rs.getString("PAYDATE"));
                collate.setPayDateFcubs(rs.getString("PAYDATE_FCUBS"));
                collate.setIsApprove(rs.getString("ISAPPROVED"));
                collate.setStatus(rs.getString("STATUS"));
                collate.setBranchCode(rs.getString("BRANCH_CODE"));
                collate.setDataXML(rs.getString("DATAXML"));
                resultList.add(collate);
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /* DAWACO */

 /* MASTERPASS */
    /**
     *
     * @param sequenceNo
     * @param responseCode
     * @param responseDescription
     * @param approvalCode
     * @param typeTrans
     * @return
     * @throws SQLException
     */
    public int INSERTCWDIRECTDEBIT(String sequenceNo, String responseCode, String responseDescription, String approvalCode, String typeTrans) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(INSERTCWDIRECTDEBIT);
            preStatement.setString(1, sequenceNo);
            preStatement.setString(2, responseCode);
            preStatement.setString(3, responseDescription);
            preStatement.setString(4, approvalCode);
            preStatement.setString(5, typeTrans);
            if (preStatement.executeUpdate() > 0) {
                String directStatus = "000".equals(responseCode) ? "04" : "05";
                if ("MASTER".equalsIgnoreCase(typeTrans)) {
                    preStatement = connection.prepareStatement(UPDATEMASTERSTATUS);
                } else {
                    preStatement = connection.prepareStatement(UPDATEVISAQRSTATUS);
                }
                preStatement.setString(1, directStatus);
                preStatement.setString(2, sequenceNo);
                if (preStatement.executeUpdate() > 0) {
                    connection.commit();
                    return 1;
                }
            }
            return 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            return -1;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String MASTERPASSQR_UPDATE_DIRECTDEBIT = "UPDATE MASTERCARDMERCHANTPRESENTEDQR SET DIDEBIT_RESCODE = ?, DIDEBIT_RESDESC = ?, DIDEBIT_APPPCODE = ?, STATUS = ? WHERE SEQUENCENO = ?";
    final String MASTERPASSQR_UPDATE_CORE = "UPDATE MASTERCARDMERCHANTPRESENTEDQR SET COREREF = ?, STATUS = ? WHERE SEQUENCENO = ?";
    final String MASTERPASSQR_UPDATE_MASTER = "UPDATE MASTERCARDMERCHANTPRESENTEDQR SET ID = ?, RESOURCE_TYPE = ?, PAYMENT_TYPE = ?, CREATED = ?, TH_ID = ?, TH_RESOURCETYPE = ?, TH_NETWORK = ?, TH_TYPE = ?, TH_CREATETIMES = ?, TH_STATUS = ?, TH_STATUSREASON = ?, TH_STATUSTIMES = ?, TH_RETRIEREF = ?, TH_SYSTRACEAUDIT = ?, TH_SWITCHSERIALNUM = ?, ORIGINALSTATUS = ?, MASTERSTATUS = ?, STATUSTS = ?, ERRORCODE = ?, ERRORDESC = ?, STATUS = ? WHERE SEQUENCENO = ?";
    final String MASTERPASSQR_UPDATE_CARDADJUSTMENT = "UPDATE MASTERCARDMERCHANTPRESENTEDQR SET CARDADJ_SEQ = ?, CARDADJ_RESCODE = ?, CARDADJ_RESDESC = ?, STATUS = ? WHERE SEQUENCENO = ?";
    final String MASTERPASSQR_UPDATE_REFUNDCORE = "UPDATE MASTERCARDMERCHANTPRESENTEDQR SET REFUNDCOREREF = ?, STATUS = ? WHERE SEQUENCENO = ?";

    public int MASTERPASSQR(PayByQRCodeRq req, MasterCardQrActionEnum action) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        int result = 0;
        try {
            connection.setAutoCommit(false);

            switch (action) {
                case UPDATE_DIRECTDEBIT: {
                    preStatement = connection.prepareStatement(MASTERPASSQR_UPDATE_DIRECTDEBIT);
                    preStatement.setString(1, req.getDirectDebitRes().getResponseCode());
                    preStatement.setString(2, req.getDirectDebitRes().getResponseDescription());
                    preStatement.setString(3, req.getDirectDebitRes().getApprovalCode());
                    preStatement.setString(4, req.getStatus());
                    preStatement.setString(5, req.getSequenceNo());

                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        result = 1;
                    }
                    break;
                }
                case UPDATE_CORE: {
                    preStatement = connection.prepareStatement(MASTERPASSQR_UPDATE_CORE);
                    preStatement.setString(1, req.getCoreRef());
                    preStatement.setString(2, req.getStatus());
                    preStatement.setString(3, req.getSequenceNo());

                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        result = 1;
                    }
                    break;
                }
                case UPDATE_MASTER: {
                    preStatement = connection.prepareStatement(MASTERPASSQR_UPDATE_MASTER);
                    preStatement.setString(1, req.getMastercardPaymentRp().getId());
                    preStatement.setString(2, req.getMastercardPaymentRp().getResourceType());
                    preStatement.setString(3, req.getMastercardPaymentRp().getPaymentType());
                    preStatement.setString(4, req.getMastercardPaymentRp().getCreated());
                    preStatement.setString(5, req.getMastercardPaymentRp().getTranHiId());
                    preStatement.setString(6, req.getMastercardPaymentRp().getTranHiResType());
                    preStatement.setString(7, req.getMastercardPaymentRp().getTranHiNetwork());
                    preStatement.setString(8, req.getMastercardPaymentRp().getTranHiType());
                    preStatement.setString(9, req.getMastercardPaymentRp().getTranHiCreatedTs());
                    preStatement.setString(10, req.getMastercardPaymentRp().getTranHiStatus());
                    preStatement.setString(11, req.getMastercardPaymentRp().getTranHiStatusRea());
                    preStatement.setString(12, req.getMastercardPaymentRp().getTranHiStatusTs());
                    preStatement.setString(13, req.getMastercardPaymentRp().getTranHiRetrieRef());
                    preStatement.setString(14, req.getMastercardPaymentRp().getTranHiSysTraAudit());
                    preStatement.setString(15, req.getMastercardPaymentRp().getTranHiSwitSeriNum());
                    preStatement.setString(16, req.getMastercardPaymentRp().getOriginalStatus());
                    preStatement.setString(17, req.getMastercardPaymentRp().getMastercardStatus());
                    preStatement.setString(18, req.getMastercardPaymentRp().getStatusTs());
                    preStatement.setString(19, req.getMastercardPaymentRp().getErrorCode());
                    preStatement.setString(20, req.getMastercardPaymentRp().getErrorDesc());
                    preStatement.setString(21, req.getStatus());
                    preStatement.setString(22, req.getSequenceNo());

                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        result = 1;
                    }
                    break;
                }
                case UPDATE_CARDADJUSTMENT: {
                    preStatement = connection.prepareStatement(MASTERPASSQR_UPDATE_CARDADJUSTMENT);
                    preStatement.setString(1, req.getCardAdjRes().getCardAdjSequence());
                    preStatement.setString(2, req.getCardAdjRes().getResponseCode());
                    preStatement.setString(3, req.getCardAdjRes().getResponseDescription());
                    preStatement.setString(4, req.getStatus());
                    preStatement.setString(5, req.getSequenceNo());

                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        result = 1;
                    }
                    break;
                }
                case UPDATE_REFUNDCODE: {
                    preStatement = connection.prepareStatement(MASTERPASSQR_UPDATE_REFUNDCORE);
                    preStatement.setString(1, req.getRefundCoreRef());
                    preStatement.setString(2, req.getStatus());
                    preStatement.setString(3, req.getSequenceNo());

                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        result = 1;
                    }
                    break;
                }
                case INSERT:
                default:
                    result = -2;
                    break;
            }

            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            return -1;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param sequenceNo
     * @param referenceNo
     * @param responseCode
     * @param responseDescription
     * @param typeTrans
     * @return
     * @throws SQLException
     */
    public int INSERTCWCARDADJUSTMENT(String sequenceNo, String referenceNo, String responseCode, String responseDescription, String typeTrans, String loc4Digits, String merPan, String refCore, String cardType) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(INSERTCWCARDADJ);
            preStatement.setString(1, sequenceNo);
            preStatement.setString(2, referenceNo);
            preStatement.setString(3, responseCode);
            preStatement.setString(4, responseDescription);
            preStatement.setString(5, typeTrans);
            preStatement.setString(6, loc4Digits);
            preStatement.setString(7, merPan);
            preStatement.setString(8, refCore);
            preStatement.setString(9, cardType);
            if (preStatement.executeUpdate() > 0) {
                String adjustStatus = "000".equals(responseCode) ? "02" : "03";
                if ("MASTER".equalsIgnoreCase(typeTrans)) {
                    preStatement = connection.prepareStatement(UPDATEMASTERSTATUS);
                } else {
                    preStatement = connection.prepareStatement(UPDATEVISAQRSTATUS);
                }
                preStatement.setString(1, adjustStatus);
                preStatement.setString(2, sequenceNo);
                if (preStatement.executeUpdate() > 0) {
                    connection.commit();
                    return 1;
                }
            }
            return 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            return -1;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param sequenceNo
     * @param response
     * @return
     * @throws SQLException
     */
    public int INSERTMASTERCARDRES(String sequenceNo, MCPaymentRp response) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            String errorCode = response.getErrorCode();
            String errorDesc = response.getErrorDesc();
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(INSERTMASTERCARDRES);
            preStatement.setString(1, sequenceNo);
            preStatement.setString(2, response.getId());
            preStatement.setString(3, response.getResourceType());
            preStatement.setString(4, response.getPaymentType());
            preStatement.setString(5, response.getCreated());
            preStatement.setString(6, response.getTranHiId());
            preStatement.setString(7, response.getTranHiResType());
            preStatement.setString(8, response.getTranHiNetwork());
            preStatement.setString(9, response.getTranHiType());
            preStatement.setString(10, response.getTranHiCreatedTs());
            preStatement.setString(11, response.getTranHiStatus());
            preStatement.setString(12, response.getTranHiStatusRea());
            preStatement.setString(13, response.getTranHiStatusTs());
            preStatement.setString(14, response.getTranHiRetrieRef());
            preStatement.setString(15, response.getTranHiSysTraAudit());
            preStatement.setString(16, response.getTranHiSwitSeriNum());
            preStatement.setString(17, response.getOriginalStatus());
            preStatement.setString(18, response.getMastercardStatus());
            preStatement.setString(19, response.getStatusTs());
            preStatement.setString(20, errorCode);
            preStatement.setString(21, errorDesc);
            if (preStatement.executeUpdate() > 0) {
                String masterStatus = ((errorCode == null || errorCode.isEmpty()) && (errorDesc == null || errorDesc.isEmpty())) ? "00" : "01";
                preStatement = connection.prepareStatement(UPDATEMASTERSTATUS);
                preStatement.setString(1, masterStatus);
                preStatement.setString(2, sequenceNo);
                if (preStatement.executeUpdate() > 0) {
                    connection.commit();
                    return 1;
                }
            }
            return 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            return -1;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param req
     * @param senderInfor
     * @return
     * @throws SQLException
     */
    public String INSERTMASTERPASSQR(PayByQRCodeRq req, MasterSenderInfor senderInfor) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String sequenceNo = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(GETMASTERREFNO);
            preStatement.setString(1, req.getRefNo());
            preStatement.setString(2, req.getProvider());
            rs = preStatement.executeQuery();
            if (!rs.next()) {
                preStatement = connection.prepareStatement(GETMASTERCARDSEQNO);
                rs = preStatement.executeQuery();
                while (rs.next()) {
                    sequenceNo = rs.getString("SEQUENCENO");
                }
                if (sequenceNo != null && !sequenceNo.isEmpty()) {
                    preStatement = connection.prepareStatement(INSERTMASTERPASSQR);
                    preStatement.setString(1, sequenceNo);
                    preStatement.setString(2, senderInfor.getFrist_name());
                    preStatement.setString(3, senderInfor.getMiddleName());
                    preStatement.setString(4, senderInfor.getLast_name());
                    preStatement.setString(5, req.getMerFName());
                    preStatement.setString(6, req.getMerMName());
                    preStatement.setString(7, req.getMerLName());
                    preStatement.setString(8, req.getAmount());
                    preStatement.setString(9, req.getCcy());
                    preStatement.setString(10, req.getCcyCode());
                    preStatement.setString(11, req.getMerAddress());
                    preStatement.setString(12, req.getMerCity());
                    preStatement.setString(13, req.getMerCountry());
                    preStatement.setString(14, req.getMerCountrySub());
                    preStatement.setString(15, req.getMerCategory());
                    preStatement.setString(16, senderInfor.getHme_addr());
                    preStatement.setString(17, senderInfor.getHme_town());
                    preStatement.setString(18, senderInfor.getHme_zip());
                    preStatement.setString(19, senderInfor.getHme_cntry());
                    preStatement.setString(20, req.getDateTime());
                    preStatement.setString(21, req.getMerchantId());
                    preStatement.setString(22, Utils.unHideCardnumber(senderInfor.getPan_clear()));
                    preStatement.setString(23, req.getMerPostalCode());
                    preStatement.setString(24, senderInfor.getExpi_date());
                    preStatement.setString(25, req.getRefNo());
                    preStatement.setString(26, senderInfor.getBrch_cde());
                    preStatement.setString(27, req.getLoc4Digit());
                    preStatement.setString(28, req.getProvider());
                    preStatement.setString(29, req.getChannel());
                    preStatement.setString(30, req.getFundingS());
                    preStatement.setString(31, req.getMerCardName());
                    preStatement.setString(32, senderInfor.getSenderCountrySub());
                    preStatement.setString(33, req.getMerCardId());
                    preStatement.setString(34, req.getDeviceId());
                    preStatement.setString(35, senderInfor.getAccountDebit() != null ? senderInfor.getAccountDebit() : "");

                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        return sequenceNo;
                    }
                }
            }
            return "";
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String INSERTMASTERPASSQRNEW = "INSERT INTO MASTERCARDMERCHANTPRESENTEDQR (SEQUENCENO, SENDER_FNAME, SENDER_MNAME, SENDER_LNAME, MER_FNAME, MER_MNAME, MER_LNAME, AMOUNT, CCY, CCYCODE, MER_ADDRESS, MER_CITY, MER_COUNTRY, MER_COUNTRYSUB, MER_CATEGORYCODE, SENDER_ADDRESS, SENDER_CITY, SENDER_POSTALCODE, SENDER_COUNTRY, DATETIME, MER_PAN, SENDER_PAN, MER_POSTALCODE, EXPIREDATE, REFERENCENO, CARDBRAND, LOC4DIGIT, PARTNER, CHANNEL, FUNDING_SOURCE, MER_CARDNAME, SENDER_COUTRY_SUB, MER_CARD_ID, DEVICE_ID, ADD_MSG, ACCOUNTDEBIT, STATUS, CREATEDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
    final String GETMASTERREFNONEW = "SELECT * FROM MASTERCARDMERCHANTPRESENTEDQR WHERE REFERENCENO = ? AND PARTNER = ?";

    public String INSERTMASTERPASSQR(PayByQRCodeRq req) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String sequenceNo = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(GETMASTERREFNONEW);
            preStatement.setString(1, req.getRefNo());
            preStatement.setString(2, req.getProvider());
            rs = preStatement.executeQuery();
            if (!rs.next()) {
                preStatement = connection.prepareStatement(GETMASTERCARDSEQNO);
                rs = preStatement.executeQuery();
                while (rs.next()) {
                    sequenceNo = rs.getString("SEQUENCENO");
                }
                if (sequenceNo != null && !sequenceNo.isEmpty()) {
                    preStatement = connection.prepareStatement(INSERTMASTERPASSQRNEW);
                    preStatement.setString(1, sequenceNo);
                    preStatement.setString(2, req.getSenderMSVSCardInfo().getFrist_name());
                    preStatement.setString(3, req.getSenderMSVSCardInfo().getMiddleName());
                    preStatement.setString(4, req.getSenderMSVSCardInfo().getLast_name());
                    preStatement.setString(5, req.getMerFName());
                    preStatement.setString(6, req.getMerMName());
                    preStatement.setString(7, req.getMerLName());
                    preStatement.setString(8, req.getAmount());
                    preStatement.setString(9, req.getCcy());
                    preStatement.setString(10, req.getCcyCode());
                    preStatement.setString(11, req.getMerAddress());
                    preStatement.setString(12, req.getMerCity());
                    preStatement.setString(13, req.getMerCountry());
                    preStatement.setString(14, req.getMerCountrySub());
                    preStatement.setString(15, req.getMerCategory());
                    preStatement.setString(16, req.getSenderMSVSCardInfo().getHme_addr());
                    preStatement.setString(17, req.getSenderMSVSCardInfo().getHme_town());
                    preStatement.setString(18, req.getSenderMSVSCardInfo().getHme_zip());
                    preStatement.setString(19, req.getSenderMSVSCardInfo().getHme_cntry());
                    preStatement.setString(20, req.getDateTime());
                    preStatement.setString(21, req.getMerchantId());
                    preStatement.setString(22, Utils.unHideCardnumber(req.getSenderMSVSCardInfo().getPan_clear()));
                    preStatement.setString(23, req.getMerPostalCode());
                    preStatement.setString(24, req.getSenderMSVSCardInfo().getExpi_date());
                    preStatement.setString(25, req.getRefNo());
                    preStatement.setString(26, req.getSenderMSVSCardInfo().getBrch_cde());
                    preStatement.setString(27, req.getLoc4Digit());
                    preStatement.setString(28, req.getProvider());
                    preStatement.setString(29, req.getChannel());
                    preStatement.setString(30, req.getFundingS());
                    preStatement.setString(31, req.getMerCardName());
                    preStatement.setString(32, req.getSenderMSVSCardInfo().getSenderCountrySub());
                    preStatement.setString(33, req.getMerCardId());
                    preStatement.setString(34, req.getDeviceId());
                    preStatement.setString(35, req.getAdd_msg());
                    preStatement.setString(36, req.getSenderMSVSCardInfo().getAccountDebit() != null ? req.getSenderMSVSCardInfo().getAccountDebit() : "");
                    preStatement.setString(37, req.getStatus());

                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        return sequenceNo;
                    }
                }
            }
            return "";
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /* MASTERPASS */
 /* VISA */
    /**
     *
     * @param req
     * @param senderInfor
     * @return
     * @throws SQLException
     */
    public String INSERTVISAQR(MVISAQRRQ req, MasterSenderInfor senderInfor) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String sequenceNo = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(GETVISAREFNO);
            preStatement.setString(1, req.getRefNo());
            preStatement.setString(2, req.getProvider());
            rs = preStatement.executeQuery();
            if (!rs.next()) {
                preStatement = connection.prepareStatement(GETDIRECTDEBITSEQNO);
                rs = preStatement.executeQuery();
                while (rs.next()) {
                    sequenceNo = rs.getString("SEQUENCENO");
                }
                if (sequenceNo != null && !sequenceNo.isEmpty()) {
                    preStatement = connection.prepareStatement(INSERTVISAQR);
                    preStatement.setString(1, sequenceNo);
                    preStatement.setString(2, req.getRefNo());
                    preStatement.setString(3, req.getSysTraceAuditNum());
                    preStatement.setString(4, req.getRetrievalRefNum());
                    preStatement.setString(5, req.getLoc4digit());
                    preStatement.setString(6, req.getMerPrimaryAccNum());
                    preStatement.setString(7, req.getAmount());
                    preStatement.setString(8, req.getLocalTransDate());
                    preStatement.setString(9, req.getMerCategoryCode());
                    preStatement.setString(10, req.getAcquirerCountryCode());
                    preStatement.setString(11, req.getTransFeeAmt());
                    preStatement.setString(12, req.getAcquiringBin());
                    preStatement.setString(13, req.getTransCurrencyCode());
                    preStatement.setString(14, req.getSecondaryId());
                    preStatement.setString(15, req.getBusinessAppID());
                    preStatement.setString(16, req.getSenderRef());
                    preStatement.setString(17, Utils.unHideCardnumber(senderInfor.getPan_clear()));
                    preStatement.setString(18, senderInfor.getExpi_date());
                    preStatement.setString(19, senderInfor.getSenderName());
                    preStatement.setString(20, senderInfor.getPhone());
                    preStatement.setString(21, senderInfor.getEmail());
                    preStatement.setString(22, req.getCardAccName());
                    preStatement.setString(23, req.getCardAccTerId());
                    preStatement.setString(24, req.getCardAccIdCode());
                    preStatement.setString(25, req.getCardAccCity());
                    preStatement.setString(26, req.getCardAccCounty());
                    preStatement.setString(27, req.getCardAccCountry());
                    preStatement.setString(28, req.getCardAccZipCode());
                    preStatement.setString(29, req.getPIType());
                    preStatement.setString(30, req.getPIRefNum());
                    preStatement.setString(31, req.getProvider());
                    preStatement.setString(32, senderInfor.getAccountDebit() != null ? senderInfor.getAccountDebit() : "");
                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        return sequenceNo;
                    }
                }
            }
            return "";
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String GETVISAREFNONEW = "SELECT * FROM VISAMERCHANTPUSHPAYMENTS WHERE REFERENCENO = ? AND PARTNER = ?";
    final String INSERTVISAQRNEW = "INSERT INTO VISAMERCHANTPUSHPAYMENTS (SEQUENCENO, REFERENCENO, LOC4DIGIT, MERPRIMARYACCNUM, AMOUNT, LOCALTRANSDATE, MERCATEGORYCODE, ACQUIRERCOUNTRYCODE, TRANSFEEAMT, ACQUIRINGBIN, TRANSCURRENCYCODE, SECONDARYID, BUSINESSAPPID, SENDERREF, SENDERACCNUM, SENDEREXPDATE, SENDERNAME, SENDERPHONE, SENDEREMAIL, CARDACCNAME, CARDACCTERID, CARDACCIDCODE, CARDACCCITY, CARDACCCOUNTY, CARDACCCOUNTRY, CARDACCZIPCODE, PITYPE, PIREFNUM, PARTNER, ACCOUNTDEBIT, STATUS, CREATEDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

    public String INSERTVISAQR(MVISAQRRQ req) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String sequenceNo = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(GETVISAREFNONEW);
            preStatement.setString(1, req.getRefNo());
            preStatement.setString(2, req.getProvider());
            rs = preStatement.executeQuery();
            if (!rs.next()) {
                preStatement = connection.prepareStatement(GETDIRECTDEBITSEQNO);
                rs = preStatement.executeQuery();
                while (rs.next()) {
                    sequenceNo = rs.getString("SEQUENCENO");
                }
                if (sequenceNo != null && !sequenceNo.isEmpty()) {
                    preStatement = connection.prepareStatement(INSERTVISAQRNEW);
                    preStatement.setString(1, sequenceNo);
                    preStatement.setString(2, req.getRefNo());
                    preStatement.setString(3, req.getLoc4digit());
                    preStatement.setString(4, req.getMerPrimaryAccNum());
                    preStatement.setString(5, req.getAmount());
                    preStatement.setString(6, req.getLocalTransDate());
                    preStatement.setString(7, req.getMerCategoryCode());
                    preStatement.setString(8, req.getAcquirerCountryCode());
                    preStatement.setString(9, req.getTransFeeAmt());
                    preStatement.setString(10, req.getAcquiringBin());
                    preStatement.setString(11, req.getTransCurrencyCode());
                    preStatement.setString(12, req.getSecondaryId());
                    preStatement.setString(13, req.getBusinessAppID());
                    preStatement.setString(14, req.getSenderRef());
                    preStatement.setString(15, Utils.hideCardnumber(req.getSenderMSVSCardInfo().getPan_clear()));
                    preStatement.setString(16, req.getSenderMSVSCardInfo().getExpi_date());
                    preStatement.setString(17, req.getSenderMSVSCardInfo().getSenderName());
                    preStatement.setString(18, req.getSenderMSVSCardInfo().getPhone());
                    preStatement.setString(19, req.getSenderMSVSCardInfo().getEmail());
                    preStatement.setString(20, req.getCardAccName());
                    preStatement.setString(21, req.getCardAccTerId());
                    preStatement.setString(22, req.getCardAccIdCode());
                    preStatement.setString(23, req.getCardAccCity());
                    preStatement.setString(24, req.getCardAccCounty());
                    preStatement.setString(25, req.getCardAccCountry());
                    preStatement.setString(26, req.getCardAccZipCode());
                    preStatement.setString(27, req.getPIType());
                    preStatement.setString(28, req.getPIRefNum());
                    preStatement.setString(29, req.getProvider());
                    preStatement.setString(30, req.getSenderMSVSCardInfo().getAccountDebit());
                    preStatement.setString(31, req.getStatus());
                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        return sequenceNo;
                    }
                }
            }
            return "";
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param sequenceNo
     * @param response
     * @return
     * @throws SQLException
     */
    public int INSERTVISAQRRES(String sequenceNo, ResponseMessage response) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(INSERTVISAQRRES);
            preStatement.setString(1, sequenceNo);
            preStatement.setString(2, response.getTransactionIdentifier());
            preStatement.setString(3, response.getTransmissionDateTime());
            preStatement.setString(4, response.getRetrievalReferenceNumber());
            preStatement.setString(5, response.getMerchantCategoryCode());
            preStatement.setString(6, response.getApprovalCode());
            preStatement.setString(7, response.getActionCode());
            preStatement.setString(8, response.getResponseCode());
            preStatement.setString(9, response.getStatusIdentifier());
            preStatement.setString(10, response.getMerchantVerificationValue());
            preStatement.setString(11, response.getFeeProgramIndicator());
            preStatement.setString(12, response.getCardAcceptor().getName());
            preStatement.setString(13, response.getCardAcceptor().getTerminalId());
            preStatement.setString(14, response.getCardAcceptor().getIdCode());
            preStatement.setString(15, response.getCardAcceptor().getAddress().getCity());
            preStatement.setString(16, response.getCardAcceptor().getAddress().getState());
            preStatement.setString(17, response.getCardAcceptor().getAddress().getCounty());
            preStatement.setString(18, response.getCardAcceptor().getAddress().getCountry());
            preStatement.setString(19, response.getCardAcceptor().getAddress().getZipCode());
            preStatement.setString(20, response.getPurchaseIdentifier().getType());
            preStatement.setString(21, response.getPurchaseIdentifier().getReferenceNumber());
            preStatement.setString(22, response.getErrorMessage());
            preStatement.setString(23, response.getResponseMessage());
            if (response.getResponseStatus() != null) {
                preStatement.setString(24, response.getResponseStatus().getCode());
                preStatement.setString(25, response.getResponseStatus().getSeverity());
                preStatement.setString(26, response.getResponseStatus().getInfo());
                preStatement.setString(27, response.getResponseStatus().getStatus());
                preStatement.setString(28, response.getResponseStatus().getMessage());
            } else {
                preStatement.setString(24, "");
                preStatement.setString(25, "");
                preStatement.setString(26, "");
                preStatement.setString(27, "");
                preStatement.setString(28, "");
            }
            if (preStatement.executeUpdate() > 0) {
                String masterStatus = ("00".equals(response.getActionCode())) ? "00" : "01";
                preStatement = connection.prepareStatement(UPDATEVISAQRSTATUS);
                preStatement.setString(1, masterStatus);
                preStatement.setString(2, sequenceNo);

                if (preStatement.executeUpdate() > 0) {
                    connection.commit();
                    return 1;
                }
            }
            return 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            return -1;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /* VISA */
 /* EVNHCM */
    /**
     *
     * @param partnerID
     * @param comeback
     * @return
     * @throws SQLException
     */
    public List<Pbl_billpaidCollate> getOutPbl_billpaidCollate(String partnerID, int comeback) throws SQLException {
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(String.format(GETOUTPBLBILLPAIDCOLLATECB, partnerID, String.valueOf(comeback)));
            List<Pbl_billpaidCollate> resultList = new ArrayList<>();
            while (rs.next()) {
                Pbl_billpaidCollate collate = new Pbl_billpaidCollate();
                collate.setIdBillPaid(rs.getString("IDBILLPAID"));
                collate.setIdPartner(rs.getString("IDPARTNER"));
                collate.setIdServiceType(rs.getString("IDSERVICETYPE"));
                collate.setIdProvider(rs.getString("IDPROVIDER"));
                collate.setCustomerCode(rs.getString("CUSTOMERCODE"));
                collate.setIdUserMaker(rs.getString("IDUSER_MAKER"));
                collate.setIdChanelUserMaker(rs.getString("IDCHANNELUSER_MAKER"));
                collate.setInsdateMaker(rs.getString("INSDATE_MAKER"));
                collate.setIdUserChecker(rs.getString("IDUSER_CHECKER"));
                collate.setIdChanelUserChecker(rs.getString("IDCHANNELUSER_CHECKER"));
                collate.setInsDateChecker(rs.getString("INSDATE_CHECKER"));
                collate.setIdChanel(rs.getString("IDCHANNEL"));
                collate.setUserType(rs.getString("USERTYPE"));
                collate.setRefPartner(rs.getString("REF_PARTNER"));
                collate.setRefFubs(rs.getString("REF_FCUBS"));
                collate.setTotalAmount(rs.getString("TOTALAMOUNT"));
                collate.setPaymentMethod(rs.getString("PAYMENTMETHOD"));
                collate.setAccCust(rs.getString("ACC_CUST"));
                collate.setAccIdAccount(rs.getString("ACC_IDACCOUNT"));
                collate.setAccHolderName(rs.getString("ACC_HOLDERNAME"));
                collate.setAccAddress(rs.getString("ACC_ADDRESS"));
                collate.setTransDate(rs.getString("TRANSDATE"));
                collate.setPayDate(rs.getString("PAYDATE"));
                collate.setPayDateFcubs(rs.getString("PAYDATE_FCUBS"));
                collate.setIsApprove(rs.getString("ISAPPROVED"));
                collate.setStatus(rs.getString("STATUS"));
                collate.setBranchCode(rs.getString("BRANCH_CODE"));
                collate.setDataXML(rs.getString("DATAXML"));
                resultList.add(collate);
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param partnerID
     * @return
     * @throws SQLException
     */
    public List<Pbl_billpaidCollate> getOutPbl_billpaidCollate(String partnerID) throws SQLException {
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(String.format(GETOUTPBLBILLPAIDCOLLATE, partnerID));
            List<Pbl_billpaidCollate> resultList = new ArrayList<>();
            while (rs.next()) {
                Pbl_billpaidCollate collate = new Pbl_billpaidCollate();
                collate.setIdBillPaid(rs.getString("IDBILLPAID"));
                collate.setIdPartner(rs.getString("IDPARTNER"));
                collate.setIdServiceType(rs.getString("IDSERVICETYPE"));
                collate.setIdProvider(rs.getString("IDPROVIDER"));
                collate.setCustomerCode(rs.getString("CUSTOMERCODE"));
                collate.setIdUserMaker(rs.getString("IDUSER_MAKER"));
                collate.setIdChanelUserMaker(rs.getString("IDCHANNELUSER_MAKER"));
                collate.setInsdateMaker(rs.getString("INSDATE_MAKER"));
                collate.setIdUserChecker(rs.getString("IDUSER_CHECKER"));
                collate.setIdChanelUserChecker(rs.getString("IDCHANNELUSER_CHECKER"));
                collate.setInsDateChecker(rs.getString("INSDATE_CHECKER"));
                collate.setIdChanel(rs.getString("IDCHANNEL"));
                collate.setUserType(rs.getString("USERTYPE"));
                collate.setRefPartner(rs.getString("REF_PARTNER"));
                collate.setRefFubs(rs.getString("REF_FCUBS"));
                collate.setTotalAmount(rs.getString("TOTALAMOUNT"));
                collate.setPaymentMethod(rs.getString("PAYMENTMETHOD"));
                collate.setAccCust(rs.getString("ACC_CUST"));
                collate.setAccIdAccount(rs.getString("ACC_IDACCOUNT"));
                collate.setAccHolderName(rs.getString("ACC_HOLDERNAME"));
                collate.setAccAddress(rs.getString("ACC_ADDRESS"));
                collate.setTransDate(rs.getString("TRANSDATE"));
                collate.setPayDate(rs.getString("PAYDATE"));
                collate.setPayDateFcubs(rs.getString("PAYDATE_FCUBS"));
                collate.setIsApprove(rs.getString("ISAPPROVED"));
                collate.setStatus(rs.getString("STATUS"));
                collate.setBranchCode(rs.getString("BRANCH_CODE"));
                collate.setDataXML(rs.getString("DATAXML"));
                resultList.add(collate);
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param datas
     * @return
     * @throws SQLException
     */
    public List<EVNHCM> queryttttEVNHCM(List<Pbl_billpaidCollate> datas) throws SQLException {
        Statement statement = null;
        ResultSet rs = null;
        List<EVNHCM> result = new ArrayList<>();
        try {
            statement = connection.createStatement();
            for (Pbl_billpaidCollate billpaid : datas) {
                rs = statement.executeQuery(String.format(GETPBLBILLPAIDPAYOOLOG, billpaid.getCustomerCode()));
                while (rs.next()) {
                    String data = rs.getString("BILLINFO");
                    String transdate = billpaid.getTransDate();
                    String channel = Utils.convertChannelOfEVN(billpaid.getIdChanel());
                    EVNHCM evnHCM = new EVNHCM(data, transdate, channel);
                    result.add(evnHCM);
                    break;
                }
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /* EVNHCM */
 /* TIMER_SMS */
    /**
     *
     * @param phoneNumber
     * @return
     * @throws SQLException
     */
    public boolean checkSCBCustomer(String phoneNumber) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareStatement(CHECKREGISTERSMS);
            preStatement.setString(1, phoneNumber);
            rs = preStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
            preStatement = connection.prepareStatement(CHECKSMSCUSTOMERVIP);
            preStatement.setString(1, phoneNumber);
            rs = preStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("checkSCBCustomer --> Phone = [" + phoneNumber
                    + "], Ex = [" + e + "]");
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

    /**
     *
     * @param phoneNumber
     * @param splitMessage
     * @return
     * @throws SQLException
     */
    public String[] queryBalance(String phoneNumber, String[] splitMessage) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String[] result = new String[4];
        String account = "";
        String numavailbal = "";
        String codacctcurr = "";
        try {
            switch (splitMessage.length) {
                case 2:
                    preStatement = connection.prepareStatement(GETDEFAULTPHONE);
                    preStatement.setString(1, phoneNumber);
                    rs = preStatement.executeQuery();
                    while (rs.next()) {
                        account = rs.getString("OPERATIVEACCTNO");
                        break;
                    }
                    break;
                case 3:
                    account = splitMessage[2];
                    break;
                default:
                    break;
            }
            if (account != null && !account.isEmpty()) {
                preStatement = connection.prepareStatement(GETACCBALANCE);
                preStatement.setString(1, account);
                rs = preStatement.executeQuery();
                while (rs.next()) {
                    numavailbal = rs.getString("numavailbal");
                    codacctcurr = rs.getString("codacctcurr");
                    break;
                }
                if (!numavailbal.isEmpty() && !codacctcurr.isEmpty()) {
                    result[0] = "0";
                    result[1] = account;
                    result[2] = numavailbal;
                    result[3] = codacctcurr;
                } else {
                    result[0] = "-2"; // loi lay so du tai khoan
                }
            } else {
                result[0] = "-1"; // chua co so tai khoan mac dinh hoac so tai khoan input sai
            }
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    /**
     *
     * @param acccount
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountCASA(String acccount) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(acccount);
        return JDBCEngine.executeQuery(GETACCBALANCE, p_args, connection);
    }

    /**
     *
     * @param phoneNumber
     * @return
     * @throws SQLException
     */
    public String getAccDefaultFromMobile(String phoneNumber) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String account = "";
        try {
            preStatement = connection.prepareStatement(GETDEFAULTPHONE);
            preStatement.setString(1, phoneNumber);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                account = rs.getString("OPERATIVEACCTNO");
                break;
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("checkSCBCustomer --> Phone = [" + phoneNumber
                    + "], Ex = [" + e + "]");
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return account;
    }

    /**
     *
     * @param phoneNumber
     * @param defaultAcc
     * @return
     * @throws SQLException
     */
    public boolean updateOperativeAccount(String phoneNumber, String defaultAcc) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareStatement(CHECKDEFAUTLACC);
            preStatement.setString(1, phoneNumber);
            rs = preStatement.executeQuery();
            if (rs.next()) {
                preStatement = connection.prepareStatement(UPDATEREGISTERSMS);
                preStatement.setString(1, defaultAcc);
                preStatement.setString(2, phoneNumber);
                if (preStatement.executeUpdate() > 0) {
                    return true;
                }
            } else {
                preStatement = connection.prepareStatement(INSERTDEFAULTACC);
                preStatement.setString(1, defaultAcc);
                preStatement.setString(2, phoneNumber);
                if (preStatement.executeUpdate() > 0) {
                    return true;
                }
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("checkSCBCustomer --> Phone = [" + phoneNumber
                    + "], Ex = [" + e + "]");
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

    /* TIMER_SMS */
 /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    /**
     *
     * @param pAccountID
     * @param pFullName
     * @param pPartnerID
     * @param AddInfo
     * @return
     * @throws SQLException
     */
    public String[] RegisterProfileidWithAccount(String pAccountID,
            String pFullName,
            String pPartnerID, String AddInfo) throws SQLException {
        CallableStatement calStmt;
        try {
            calStmt = connection.prepareCall(RegisterProfileidWithAccount);
            calStmt.setString(1, pAccountID);
            calStmt.setString(2, pFullName);
            calStmt.setString(3, pPartnerID);
            calStmt.setString(4, AddInfo);
            calStmt.registerOutParameter(5, OracleTypes.VARCHAR); // pStatus 
            calStmt.registerOutParameter(6, OracleTypes.VARCHAR); // PSCBID
            calStmt.registerOutParameter(7, OracleTypes.VARCHAR); // pDesc
            calStmt.execute();
            String[] ArrayResult = new String[3];
            ArrayResult[0] = calStmt.getString(5);
            ArrayResult[1] = calStmt.getString(6);
            ArrayResult[2] = calStmt.getString(7);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            connection.close();
        }
    }

    /**
     *
     * @param PSCBID
     * @param pTRANSID
     * @param pPROFILEID
     * @param TransDate
     * @param PartnerID
     * @return
     * @throws SQLException
     */
    public String UpdateRegisterProfileidWithACC(String PSCBID,
            String pTRANSID,
            String pPROFILEID, String TransDate, String PartnerID) throws SQLException {
        CallableStatement calStmt;
        try {
            calStmt = connection.prepareCall(UpdateRegisterProfileidWithACC);
            calStmt.setString(1, PSCBID);
            calStmt.setString(2, pTRANSID);
            calStmt.setString(3, pPROFILEID);
            calStmt.setString(4, TransDate);
            calStmt.setString(5, PartnerID);
            calStmt.registerOutParameter(6, OracleTypes.VARCHAR);
            calStmt.execute();
            return calStmt.getString(6);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            connection.close();
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PTRANSDATE
     * @param PCARDNUMBER
     * @param PCARDNAME
     * @param POTPSMS
     * @param PPARTNERID
     * @param pDescription
     * @param pTypeCard
     * @return
     * @throws SQLException
     */
    public String[] VERIFICARD(String PTRANSID,
            String PTRANSDATE,
            String PCARDNUMBER,
            String PCARDNAME,
            String POTPSMS,
            String PPARTNERID,
            String pDescription,
            String pTypeCard) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(VERIFICARD);
            calStmt.setString(1, PTRANSID);
            calStmt.setString(2, PTRANSDATE);
            calStmt.setString(3, PCARDNUMBER);
            calStmt.setString(4, PCARDNAME);
            calStmt.setString(5, POTPSMS);
            calStmt.setString(6, PPARTNERID);
            calStmt.setString(7, pDescription);
            calStmt.setString(8, pTypeCard);
            calStmt.registerOutParameter(9, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(11, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[3];
            ArrayResult[0] = calStmt.getString(9);//status
            ArrayResult[1] = calStmt.getString(10);//banktransid
            ArrayResult[2] = calStmt.getString(11);//banktransid
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param userid
     * @return
     * @throws SQLException
     */
    public EBANKUSER getInfoEbankUser(String userid) throws SQLException {
        CallableStatement calStmt;
        try {
            EBANKUSER user = new EBANKUSER();
            calStmt = connection.prepareCall(PROC_IB_ACCOUNT);
            calStmt.setString(1, userid);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.executeQuery();
            ResultSet rs = (ResultSet) calStmt.getObject(2);
            if (rs == null) {
                LOGGER.warn("Not found");
                throw new Exception("Not found");
            }
            List ArrListAc = new ArrayList();
            while (rs.next()) {
                ArrListAc.add(rs.getString("CUST_AC_NO"));
                if (user.getIduser() == null) {
                    user.setADDRESS(rs.getString("ADDRESS"));
                    user.setCHK_STATUS(rs.getString("CHK_STATUS"));
                    user.setFULL_NAME(rs.getString("FULLNAME"));
                    user.setIDNUMBER(rs.getString("IDNUMBER"));
                    user.setIDTYPE(rs.getString("IDTYPE"));
                    user.setISSUEDATE(rs.getString("ISSUEDATE"));
                    user.setISSUEPLACE(rs.getString("ISSUEPLACE"));
                    user.setPHONENUMBER(rs.getString("PHONENUMBER"));
                    user.setIduser(userid);
                    user.setCUST_NO(rs.getString("CUST_NO"));
                }
            }
            user.setCUST_AC_NO(ArrListAc);
            return user;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            connection.close();
        }
    }

    /* --- SUNRISE --- */
    /**
     *
     * @param PTRANSID
     * @param pProfileID
     * @param PAMOUNT
     * @param PCCY
     * @param PTRANSDATE
     * @param PPARTNERID
     * @param PDESCRIPTION
     * @param PMERCHANTID
     * @param pChannelID
     * @param OTP
     * @return
     * @throws SQLException
     */
    public String[] PAYMENTWITHPROFILEIDBYVERIFY(String PTRANSID,
            String pProfileID,
            Double PAMOUNT,
            String PCCY,
            String PTRANSDATE,
            String PPARTNERID,
            String PDESCRIPTION,
            String PMERCHANTID,
            String pChannelID,
            String OTP
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(PAYMENTWITHPROFILEIDBYVERIFY);
            calStmt.setString(1, PTRANSID);
            calStmt.setString(2, pProfileID);
            calStmt.setDouble(3, PAMOUNT);
            calStmt.setString(4, PCCY);
            calStmt.setString(5, PTRANSDATE);
            calStmt.setString(6, PPARTNERID);
            calStmt.setString(7, PDESCRIPTION);
            calStmt.setString(8, PMERCHANTID);//VIMO_01: payment/VIMO_02: wallet
            calStmt.setString(9, pChannelID);//MOB/INT
            calStmt.setString(10, OTP);
            calStmt.registerOutParameter(11, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(12, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(13, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[3];
            ArrayResult[0] = calStmt.getString(11);
            ArrayResult[1] = calStmt.getString(12);
            ArrayResult[2] = calStmt.getString(13);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pProfileID
     * @param PAMOUNT
     * @param PPARTNERID
     * @return
     * @throws SQLException
     */
    public String[] checkProfileIDForPayment(
            String pProfileID,
            Double PAMOUNT,
            String PPARTNERID
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(checkProfileIDForPayment);
            calStmt.setString(1, pProfileID);
            calStmt.setDouble(2, PAMOUNT);
            calStmt.setString(3, PPARTNERID);
            calStmt.registerOutParameter(4, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(5, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[2];
            ArrayResult[0] = calStmt.getString(4);
            ArrayResult[1] = calStmt.getString(5);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /* VNPAY QR */
    final String GETCHECKQRSEQNO = "SELECT SEQ_CHECKQR.NEXTVAL AS SEQUENCE FROM DUAL";
    final String GETQRINFOSEQNO = "SELECT SEQ_QRINFO.NEXTVAL AS SEQUENCE FROM DUAL";
    final String GETQRPAYMENTSEQNO = "SELECT SEQ_QRPAYMENT.NEXTVAL AS SEQUENCE FROM DUAL";
    final String GETQRREFUNDSEQNO = "SELECT SEQ_QRREFUND.NEXTVAL AS SEQUENCE FROM DUAL";

    final String GETQRINFO = "SELECT T.QUANTITY AS QUANTITY, T.QRINFO AS QRINFO, T.NOTE AS NOTE FROM VNPAYQRQRINFO T WHERE T.PAYCODE = ?";

    final String VALIDATECHECKQR = "SELECT * FROM VNPAYQRCHECKQR T WHERE T.CLIENTID = ? AND T.PARTNER = ?";
    final String VALIDATEPAYMENTQR = "SELECT T.BANKCODE AS BANKCODE, T.PROMOTIONCODE AS PROMOTIONCODE, T.MOBILE AS MOBILE, T.USERNAME AS USERNAME, T.DEBITAMOUNT AS DEBITAMOUNT, T.ACCOUNTNO AS ACCOUNTNO, T.MERCHANTID AS MERCHANTID\n"
            + "FROM VNPAYQRCHECKQR T WHERE T.CLIENTID = ? AND T.PARTNER = ? AND T.RESPCODE = '00' AND T.PAYCODE = ? AND T.PAYCODE NOT IN (SELECT PAYCODE FROM VNPAYQRPAYMENT)";
    final String VALIDATEREFUNDQR = "SELECT * FROM VNPAYQRPAYMENT T WHERE T.traceTransfer = ? AND T.bankRespCode = '00' AND T.respCode <> '00' AND T.TRACETRANSFER NOT IN (SELECT TRACETRANSFER FROM VNPAYQRREFUND)";

    final String INSERTCHECKQR = "INSERT INTO VNPAYQRCHECKQR (payCode, clientId, bankCode, payType, promotionCode, mobile, userName, debitAmount, accountNo, merchantId, partner, createDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
    final String INSERTQRINFO = "INSERT INTO VNPAYQRQRINFO (qrID, payCode, quantity, qrInfo, note) VALUES (?, ?, ?, ?, ?)";
    final String INSERTQRPAYMENT = "INSERT INTO VNPAYQRPAYMENT (traceTransfer, payCode, payDate, additionalData, messageType, orderCode, realAmount, rate, createDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
    final String INSERTQRREFUND = "INSERT INTO VNPAYQRREFUND (refundTrace, traceTransfer, payDate, createDate) VALUES (?, ?, ?, SYSDATE)";

    final String UPDATECHECKQR = "UPDATE VNPAYQRCHECKQR SET respCode = ?, respDesc = ?, additionalData = ?, promotionValue = ?, providerCode = ?, serviceCode = ?, customerName = ?, customerAddress = ?, totalAmount = ? WHERE payCode = ?";
    final String UPDATEPAYMENTQR = "UPDATE VNPAYQRPAYMENT SET bankRespCode = ?, bankRespDesc = ?, respCode = ?, respDesc = ? WHERE traceTransfer = ?";
    final String UPDATEREFUNDQR = "UPDATE VNPAYQRREFUND SET code = ?, message = ?, respCode = ?, respDesc = ?, traceBank = ?, orderCode = ? WHERE refundTrace = ?";

    /**
     *
     * @param req
     * @return
     * @throws SQLException
     */
    public String INSERTCHECKQR(CheckQRRq req) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String sequence = null;
        String qrSequence = null;

        String currentTime = "";//Common.getJulianFromDate();
        connection.setAutoCommit(false);
        try {
            preStatement = connection.prepareStatement(VALIDATECHECKQR);
            preStatement.setString(1, req.getClientId());
            preStatement.setString(2, req.getPartner());
            rs = preStatement.executeQuery();
            if (!rs.next()) {
                preStatement = connection.prepareStatement(GETCHECKQRSEQNO);
                rs = preStatement.executeQuery();
                while (rs.next()) {
                    sequence = rs.getString("SEQUENCE");
                    break;
                }
                if (sequence != null && !sequence.isEmpty()) {
                    String payCode = currentTime + sequence;
                    preStatement = connection.prepareStatement(INSERTCHECKQR);
                    preStatement.setString(1, payCode);
                    preStatement.setString(2, req.getClientId());
                    preStatement.setString(3, req.getBankCode());
                    preStatement.setString(4, req.getPayType());
                    preStatement.setString(5, req.getPromotionCode());
                    preStatement.setString(6, req.getMobile());
                    preStatement.setString(7, req.getUserName());
                    preStatement.setString(8, req.getDebitAmount());
                    preStatement.setString(9, req.getAccountNo());
                    preStatement.setString(10, req.getMerchantId());
                    preStatement.setString(11, req.getPartner());
                    if (preStatement.executeUpdate() > 0) {
                        for (QrInfo item : req.getQr().getQrItem()) {
                            preStatement = connection.prepareStatement(GETQRINFOSEQNO);
                            rs = preStatement.executeQuery();
                            while (rs.next()) {
                                qrSequence = rs.getString("SEQUENCE");
                                break;
                            }
                            if (qrSequence != null && !qrSequence.isEmpty()) {
                                String qrID = currentTime + qrSequence;
                                preStatement = connection.prepareStatement(INSERTQRINFO);
                                preStatement.setString(1, qrID);
                                preStatement.setString(2, payCode);
                                preStatement.setString(3, item.getQuantity());
                                preStatement.setString(4, item.getQrInfor());
                                preStatement.setString(5, item.getNote());
                                if (preStatement.executeUpdate() <= 0) {
                                    connection.rollback();
                                    return "";
                                }
                            } else {
                                connection.rollback();
                                return "";
                            }
                        }
                        connection.commit();
                        return payCode;
                    } else {
                        connection.rollback();
                    }
                }
            }
            return "";
        } catch (Exception ex) {
            connection.rollback();
            LOGGER.error("INSERTCHECKQR --> " + ex);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param res
     * @return
     * @throws SQLException
     */
    public boolean UPDATECHECKQR(CheckQRRp res) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(false);
        try {
            preStatement = connection.prepareStatement(UPDATECHECKQR);
            preStatement.setString(1, res.getResCode());
            preStatement.setString(2, res.getResDesc());
            preStatement.setString(3, res.getAdditionalData());
            preStatement.setString(4, res.getPromotionValue());
            preStatement.setString(5, res.getProviderCode());
            preStatement.setString(6, res.getServiceCode());
            preStatement.setString(7, res.getCustomerName());
            preStatement.setString(8, res.getCustomerAddress());
            preStatement.setString(9, res.getTotalAmount());
            preStatement.setString(10, res.getPayCode());
            if (preStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
            return false;
        } catch (Exception ex) {
            LOGGER.error("UPDATECHECKQR --> " + ex);
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param req
     * @return
     * @throws SQLException
     */
    public PaymentQRJson INSERTPAYMENTQR(PaymentQRRq req) throws SQLException {
        PaymentQRJson result = new PaymentQRJson();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String sequence = null;
        String currentTime = "";// Common.getJulianFromDate();
        List<QrInfo> qrInfo = new ArrayList<>();
        connection.setAutoCommit(false);
        try {
            preStatement = connection.prepareStatement(VALIDATEPAYMENTQR);
            preStatement.setString(1, req.getClientId());
            preStatement.setString(2, req.getPartner());
            preStatement.setString(3, req.getPayCode());
            rs = preStatement.executeQuery();
            while (rs.next()) {
                result.setBankCode(rs.getString("BANKCODE"));
                result.setPromotionCode(rs.getString("PROMOTIONCODE"));
                result.setMobile(rs.getString("MOBILE"));
                result.setUserName(rs.getString("USERNAME"));
                result.setDebitAmount(rs.getString("DEBITAMOUNT"));
                result.setAccountNo(rs.getString("ACCOUNTNO"));
                result.setMerchantId(rs.getString("MERCHANTID"));

                preStatement = connection.prepareStatement(GETQRINFO);
                preStatement.setString(1, req.getPayCode());
                rs = preStatement.executeQuery();
                while (rs.next()) {
                    QrInfo item = new QrInfo();
                    item.setQuantity(rs.getString("QUANTITY"));
                    item.setQrInfor(rs.getString("QRINFO"));
                    item.setNote(rs.getString("NOTE"));
                    qrInfo.add(item);
                }

                if (!qrInfo.isEmpty()) {
                    QrInfo[] items = new QrInfo[qrInfo.size()];
                    for (int i = 0; i < items.length; i++) {
                        items[i] = qrInfo.get(i);
                    }
                    result.setItem(items);
                    preStatement = connection.prepareStatement(GETQRPAYMENTSEQNO);
                    rs = preStatement.executeQuery();
                    while (rs.next()) {
                        sequence = rs.getString("SEQUENCE");
                        break;
                    }
                    if (sequence != null && !sequence.isEmpty()) {
                        result.setTraceTransfer(currentTime + sequence);
                        result.setPayDate(req.getPayDate());
                        result.setAdditionalData(req.getAdditionalData());
                        result.setMessageType(req.getMessageType());
                        result.setOrderCode(result.getTraceTransfer());
                        result.setRealAmount(req.getRealAmount());
                        result.setRate(req.getRate());

                        preStatement = connection.prepareStatement(INSERTQRPAYMENT);
                        preStatement.setString(1, result.getTraceTransfer());
                        preStatement.setString(2, req.getPayCode());
                        preStatement.setString(3, result.getPayDate());
                        preStatement.setString(4, result.getAdditionalData());
                        preStatement.setString(5, result.getMessageType());
                        preStatement.setString(6, result.getOrderCode());
                        preStatement.setString(7, result.getRealAmount());
                        preStatement.setString(8, result.getRate());
                        if (preStatement.executeUpdate() > 0) {
                            connection.commit();
                            return result;
                        } else {
                            connection.rollback();
                        }
                    }
                }
                break;
            }
            return null;
        } catch (Exception ex) {
            connection.rollback();
            LOGGER.error("INSERTPAYMENTQR --> " + ex);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param qrPayment
     * @param res
     * @return
     * @throws SQLException
     */
    public boolean UPDATEPAYMENTQR(PaymentQRJson qrPayment, PaymentQRRp res) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(false);
        try {
            preStatement = connection.prepareStatement(UPDATEPAYMENTQR);
            preStatement.setString(1, qrPayment.getRespCode());
            preStatement.setString(2, qrPayment.getRespDesc());
            preStatement.setString(3, res == null ? "" : res.getResCode());
            preStatement.setString(4, res == null ? "" : res.getResDesc());
            preStatement.setString(5, qrPayment.getTraceTransfer());
            if (preStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
            return false;
        } catch (Exception ex) {
            LOGGER.error("UPDATECHECKQR --> " + ex);
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param qrPayment
     * @return
     * @throws SQLException
     */
    public RefundQRJson INSERTREFUNDQR(PaymentQRJson qrPayment) throws SQLException {
        RefundQRJson result = new RefundQRJson();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String sequence = null;
        String currentTime = "";//Common.getJulianFromDate();
        connection.setAutoCommit(false);
        try {
            preStatement = connection.prepareStatement(VALIDATEREFUNDQR);
            preStatement.setString(1, qrPayment.getTraceTransfer());
            rs = preStatement.executeQuery();
            if (rs.next()) {
                preStatement = connection.prepareStatement(GETQRREFUNDSEQNO);
                rs = preStatement.executeQuery();
                while (rs.next()) {
                    sequence = rs.getString("SEQUENCE");
                    break;
                }
                if (sequence != null && !sequence.isEmpty()) {
                    result.setRefundTrace(currentTime + sequence);
                    result.setBankCode(qrPayment.getBankCode());
                    result.setBankTrace(qrPayment.getTraceTransfer());
                    result.setPayCode(qrPayment.getOrderCode());
                    result.setPayDate(qrPayment.getPayDate());

                    preStatement = connection.prepareStatement(INSERTQRREFUND);
                    preStatement.setString(1, result.getRefundTrace());
                    preStatement.setString(2, result.getBankTrace());
                    preStatement.setString(3, result.getPayCode());
                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        return result;
                    } else {
                        connection.rollback();
                    }
                }
            }
            return null;
        } catch (Exception ex) {
            LOGGER.error("INSERTREFUNDQR --> " + ex);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param qrRefund
     * @param res
     * @return
     * @throws SQLException
     */
    public boolean UPDATEREFUNDQR(RefundQRJson qrRefund, RefundQRRp res) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(false);
        try {
            preStatement = connection.prepareStatement(UPDATEREFUNDQR);
            preStatement.setString(1, qrRefund.getCode());
            preStatement.setString(2, qrRefund.getMessage());
            preStatement.setString(3, res.getResCode());
            preStatement.setString(4, res.getResDesc());
            preStatement.setString(5, res.getTraceBank());
            preStatement.setString(6, res.getPayCode());
            preStatement.setString(7, qrRefund.getRefundTrace());
            if (preStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
            return false;
        } catch (Exception ex) {
            LOGGER.error("UPDATEREFUNDQR --> " + ex);
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /* TIMER SMS */
    final String GET_DETAILS_ACCOUNTCASA_DAILY = "select trn_dt as txndate ,ac_ccy as coddrcr, lcy_amount as txnamount from fcusr01.actb_daily_log@fcatfcclink  where ac_no=?";
    final String GET_DETAILS_ACCOUNTCASA_HISTORY = "select trn_dt as txndate ,ac_ccy as coddrcr, lcy_amount as txnamount from dwprod.actb_history@dwhodbx  where ac_no=?";
    final String GET_ACCOUNTTD = "SELECT A.CCY, A.TD_AMOUNT - NVL(A.REDEM_AMT,0) TD_AMOUNT, B.MATURITY_DATE FROM fcusr01.ICTM_TD_DETAILS@FCATFCCLINK A, fcusr01.ICTM_ACC@FCATFCCLINK B WHERE A.ACC = B.ACC AND A.BRN = B.BRN AND A.ACC = ? AND A.BRN = ?";

    /**
     *
     * @param acccount
     * @param rownum
     * @return
     * @throws Exception
     */
    public ArrayList<?> getDetailsAccountCASADaily(String acccount, int rownum) throws Exception {
        ArrayList result = new ArrayList();
        ArrayList p_args = new ArrayList();
        p_args.add(acccount);
        ArrayList temp = JDBCEngine.executeQueryWithoutCloseConnection(GET_DETAILS_ACCOUNTCASA_DAILY, p_args, connection, ArrayList.class);
        int next = (temp.size() <= rownum) ? temp.size() : rownum;
        for (int i = 0; i < next; i++) {
            result.add(temp.get(i));
        }
        if (result.size() < rownum) {
            temp = JDBCEngine.executeQuery(GET_DETAILS_ACCOUNTCASA_HISTORY, p_args, connection);
            next = (temp.size() <= (rownum - result.size())) ? temp.size() : (rownum - result.size());
            for (int i = 0; i < next; i++) {
                result.add(temp.get(i));
            }
        }
        return result;
    }

    /**
     *
     * @param acccountTD
     * @param branchAcc
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTD(String acccountTD, String branchAcc) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(acccountTD);
        p_args.add(branchAcc);
        return JDBCEngine.executeQuery(GET_ACCOUNTTD, p_args, connection);
    }

    /*CHECK KYC*/
    /**
     *
     * @param PPARTNERID
     * @param pProfileID
     * @param pCMND
     * @param pMobile
     * @param pAddInfo
     * @return
     * @throws SQLException
     */
    public String checkKYC(
            String PPARTNERID,
            String pProfileID,
            String pCMND,
            String pMobile,
            String pAddInfo
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(checkKYC);
            calStmt.setString(1, PPARTNERID);
            calStmt.setString(2, pProfileID);
            calStmt.setString(3, pCMND);
            calStmt.setString(4, pMobile);
            calStmt.setString(5, pAddInfo);
            calStmt.registerOutParameter(6, OracleTypes.VARCHAR);
            calStmt.execute();
            return calStmt.getString(6);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static final String MOBILEVALIDATION = "select * from MB_MOBILES@mbanking t where t.mobile_status <> '9' and t.RECEIVED_OTP = ?";
    private static final String BDSDVALIDATION = "select * from SMS_SCB.sms_cust_alerttd_phone t where (t.Tk_Momoi <> 'N' or t.isdeleted <> '1') and t.mobile = ?";

    /**
     *
     * @param phoneNumber
     * @return
     * @throws SQLException
     */
    public boolean checkPhoneNumberAtGW(String phoneNumber) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(true);
        try {
            preStatement = connection.prepareStatement(MOBILEVALIDATION);
            preStatement.setString(1, phoneNumber);
            rs = preStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
            preStatement = connection.prepareStatement(BDSDVALIDATION);
            preStatement.setString(1, phoneNumber);
            rs = preStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
            String newPhone = Utils.swapPhoneNumber(phoneNumber);
            preStatement = connection.prepareStatement(MOBILEVALIDATION);
            preStatement.setString(1, newPhone);
            rs = preStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
            preStatement = connection.prepareStatement(BDSDVALIDATION);
            preStatement.setString(1, newPhone);
            rs = preStatement.executeQuery();
            return rs.next();
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("insertTopupMBDVKH --> ex = [" + e + "]");
            connection.rollback();
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static final String INSERTLOCKTHE = "INSERT INTO KHOATHE (FI, PAN, ACTIND, ACTTYPE, ACTCDE, PARTNERID, REFNO, CREATEDATE) VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE)";

    /**
     *
     * @param req
     * @param card
     * @return
     * @throws SQLException
     */
    public String INSERTLOCKTHE(KhoaTheReq req, CardInfo card) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(true);
        try {
            preStatement = connection.prepareStatement(INSERTLOCKTHE);
            preStatement.setString(1, "970429");
            preStatement.setString(2, card.getLoc() + card.getLast4Digits());
            preStatement.setString(3, "3");
            preStatement.setString(4, "OP");
            preStatement.setString(5, "B");
            preStatement.setString(6, req.getPartner());
            preStatement.setString(7, req.getSequence());
            if (preStatement.executeUpdate() > 0) {
                preStatement = connection.prepareStatement(GETSEQLOCKTHE);
                preStatement.setString(1, req.getPartner());
                preStatement.setString(2, req.getSequence());
                rs = preStatement.executeQuery();
                while (rs.next()) {
                    return rs.getString("SEQUENSENO");
                }
            }
            return "";
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("INSERTLOCKTHE --> ex = [" + e + "]");
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static final String GETSEQLOCKTHE8149 = "SELECT SEQ_LOCKTHE8149.NEXTVAL AS SEQUENSE FROM DUAL";
    private static final String INSERTLOCKTHE8149 = "INSERT INTO KHOATHE8149 (SEQUENSENO, FI, PAN, ACTIND, ACTTYPE, ACTCDE, CREATEDATE) VALUES (?, ?, ?, ?, ?, ?, SYSDATE)";
    private static final String UPDATELOCKTHE8149 = "UPDATE KHOATHE8149 SET RESPCODE = ?, RESPDESC = ? WHERE SEQUENSENO = ?";

    /**
     *
     * @param card
     * @return
     * @throws SQLException
     */
    public String INSERTLOCKTHE8149(CardInfo card) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String sequence = "";
        connection.setAutoCommit(true);
        try {
            preStatement = connection.prepareStatement(GETSEQLOCKTHE8149);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                sequence = rs.getString("SEQUENSE");
                break;
            }
            if (!sequence.isEmpty()) {
                preStatement = connection.prepareStatement(INSERTLOCKTHE8149);
                preStatement.setString(1, sequence);
                preStatement.setString(2, "970429");
                preStatement.setString(3, card.getLoc() + card.getLast4Digits());
                preStatement.setString(4, "3");
                preStatement.setString(5, "OP");
                preStatement.setString(6, "B");
                if (preStatement.executeUpdate() > 0) {
                    return sequence;
                } else {
                    return "";
                }
            }
            return "";
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("INSERTLOCKTHE8149 --> ex = [" + e + "]");
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param sequence
     * @param respCode
     * @param respDesc
     * @return
     * @throws SQLException
     */
    public boolean UPDATELOCKTHE8149(String sequence, String respCode, String respDesc) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(true);
        try {
            preStatement = connection.prepareStatement(UPDATELOCKTHE8149);
            preStatement.setString(1, respCode);
            preStatement.setString(2, respDesc);
            preStatement.setString(3, sequence);
            return (preStatement.executeUpdate() > 0);
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("UPDATELOCKTHE8149 --> ex = [" + e + "]");
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static final String INSERTKHOATHE = "INSERT INTO SMS_SCB.KHOATHE (PARTNERID, SEQUENCENO, CMND, PHONE, LAST4DIGITS, CARDTYPE, LOCKALL, CHANNEL, CREATEDATE) VALUES (?, ?, ?, ?, ?, ?, ?,?, SYSDATE)";

    /**
     *
     * @param req
     * @return
     * @throws SQLException
     */
    public boolean INSERTKHOATHE(KhoaTheReq req) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(true);
        try {
            String last4Digits = req.getLast4Digits() == null ? "" : req.getLast4Digits();
            String cardType = req.getCardType() == null ? "" : req.getCardType();
            preStatement = connection.prepareStatement(INSERTKHOATHE);
            preStatement.setString(1, req.getPartner());
            preStatement.setString(2, req.getSequence());
            preStatement.setString(3, req.getCmnd());
            preStatement.setString(4, req.getPhone());
            preStatement.setString(5, last4Digits);
            preStatement.setString(6, cardType);
            preStatement.setString(7, req.isLockAll() ? "Y" : "N");
            preStatement.setString(8, req.getChannel());
            return preStatement.executeUpdate() > 0;
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("INSERTKHOATHE --> ex = [" + e + "]");
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static final String GETSEQLOCKTHE = "SELECT SQ_CWWSREFNO.NEXTVAL AS REFNO FROM DUAL";
    private static final String INSERTKHOATHEDETAILS = "INSERT INTO SMS_SCB.KHOATHEDETAILS (PARTNERID, SEQUENCENO, REFNO, FI, PAN, ACTIND, ACTTYPE, ACTCDE, CREATEDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

    /**
     *
     * @param req
     * @param card
     * @return
     * @throws SQLException
     */
    public String INSERTKHOATHEDETAILS(KhoaTheReq req, CardInfo card) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(true);
        try {
            String refCode = "";
            preStatement = connection.prepareStatement(GETSEQLOCKTHE);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                refCode = rs.getString("REFNO");
                break;
            }
            if (!refCode.isEmpty()) {
                String fi = "970429";
                String pan = card.getLoc() + card.getLast4Digits();
                String actind = "3";
                String acttype = "CD";
                String actcde = "B";
                preStatement = connection.prepareStatement(INSERTKHOATHEDETAILS);
                preStatement.setString(1, req.getPartner());
                preStatement.setString(2, req.getSequence());
                preStatement.setString(3, refCode);
                preStatement.setString(4, fi);
                preStatement.setString(5, pan);
                preStatement.setString(6, actind);
                preStatement.setString(7, acttype);
                preStatement.setString(8, actcde);
                if (preStatement.executeUpdate() > 0) {
                    return refCode;
                }
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("INSERTLOCKTHEDETAILS --> ex = [" + e + "]");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return "";
    }

    private static final String UPDATELOCKTHEDETAILS = "UPDATE SMS_SCB.KHOATHEDETAILS SET RESPCODE = ?, RESPDESC = ? WHERE SEQUENCENO = ? AND PARTNERID = ? AND REFNO = ?";

    /**
     *
     * @param refNo
     * @param req
     * @param respCode
     * @param respDesc
     * @return
     * @throws SQLException
     */
    public boolean UPDATEKHOATHEDETAILS(String refNo, KhoaTheReq req, String respCode, String respDesc) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(true);
        try {
            preStatement = connection.prepareStatement(UPDATELOCKTHEDETAILS);
            preStatement.setString(1, respCode);
            preStatement.setString(2, respDesc);
            preStatement.setString(3, req.getSequence());
            preStatement.setString(4, req.getPartner());
            preStatement.setString(5, refNo);
            return (preStatement.executeUpdate() > 0);
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("UPDATELOCKTHE8149 --> ex = [" + e + "]");
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String MMSTRANSFERACCTOACC = "BEGIN PROC_MMS_ACC_TO_ACC(?,?,?,?,?,?,?,?,?,?,?); END;";

    /**
     *
     * @param ITRANSID
     * @param ISOURCEACCOUNT
     * @param IDESTACCCOUNT
     * @param IAMOUNT
     * @param ICCY
     * @param ITRANSDATE
     * @param IPARTNERID
     * @param IDESCRIPTION
     * @return
     * @throws SQLException
     */
    public String[] MMSTRANSFERACCTOACC(String ITRANSID, String ISOURCEACCOUNT, String IDESTACCCOUNT,
            Double IAMOUNT, String ICCY, String ITRANSDATE, String IPARTNERID,
            String IDESCRIPTION) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(MMSTRANSFERACCTOACC);
            calStmt.setString(1, ITRANSID);
            calStmt.setString(2, ISOURCEACCOUNT);
            calStmt.setString(3, IDESTACCCOUNT);
            calStmt.setDouble(4, IAMOUNT);
            calStmt.setString(5, ICCY);
            calStmt.setString(6, ITRANSDATE);
            calStmt.setString(7, IPARTNERID);
            calStmt.setString(8, IDESCRIPTION);
            calStmt.registerOutParameter(9, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(11, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[3];
            ArrayResult[0] = calStmt.getString(9) != null ? calStmt.getString(9) : "";
            ArrayResult[1] = calStmt.getString(10) != null ? calStmt.getString(10) : "";
            ArrayResult[2] = calStmt.getString(11) != null ? calStmt.getString(11) : "";
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String UPDATEMMSTRANSFERACCTOACC = "UPDATE ONL_TAKEOUTWALLET SET REFCORE = ? WHERE ID = ?";

    /**
     *
     * @param IBANKID
     * @param IREFCORE
     * @return
     * @throws SQLException
     */
    public boolean UPDATEMMSTRANSFERACCTOACC(String IBANKID, String IREFCORE) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(true);
        try {
            preStatement = connection.prepareStatement(UPDATEMMSTRANSFERACCTOACC);
            preStatement.setString(1, IREFCORE);
            preStatement.setString(2, IBANKID);
            return (preStatement.executeUpdate() > 0);
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("UPDATEMMSTRANSFERACCTOACC --> ex = [" + e + "]");
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pTransid
     * @param pRefTransid
     * @param pAmount
     * @param pTransdate
     * @param pDescription
     * @param pPartnerid
     * @return
     * @throws SQLException
     */
    public String[] REFUND_FOR_TAKEOUT(String pTransid,
            String pRefTransid,
            Double pAmount,
            String pTransdate,
            String pDescription,
            String pPartnerid) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(REFUND_FOR_TAKEOUT);
            calStmt.setString(1, pTransid);
            calStmt.setString(2, pRefTransid);
            calStmt.setDouble(3, pAmount);
            calStmt.setString(4, pTransdate);
            calStmt.setString(5, pDescription);
            calStmt.setString(6, pPartnerid);
            calStmt.registerOutParameter(7, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(8, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(9, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR);
            calStmt.execute();
            String[] ArrayResult = new String[4];
            ArrayResult[0] = calStmt.getString(7);
            ArrayResult[1] = calStmt.getString(8);
            ArrayResult[2] = calStmt.getString(9);
            ArrayResult[3] = calStmt.getString(10);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String getProviderOriginalCode = "SELECT PROVIDERCODE AS PROVIDERCODE FROM SMS_SCB.PBL_PARTNERCODE WHERE IDSERVICETYPE = ? AND IDPROVIDER = ?";

    /**
     *
     * @param idServiceCode
     * @param idProviderCode
     * @return
     * @throws SQLException
     */
    public String getProviderOriginalCode(String idServiceCode, String idProviderCode) throws SQLException {
        String result = "";
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareStatement(getProviderOriginalCode);
            preStatement.setString(1, idServiceCode);
            preStatement.setString(2, idProviderCode);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                result = rs.getString("PROVIDERCODE");
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("getProviderOriginalCode --> ex = [" + e + "]");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    final String getPartnerCode = "SELECT IDPARTNER AS IDPARTNER FROM SMS_SCB.PBL_PARTNERCODE WHERE IDSERVICETYPE = ? AND IDPROVIDER = ?";

    /**
     *
     * @param idServiceCode
     * @param idProviderCode
     * @return
     * @throws SQLException
     */
    public String getPartnerCode(String idServiceCode, String idProviderCode) throws SQLException {
        String result = "";
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareStatement(getPartnerCode);
            preStatement.setString(1, idServiceCode);
            preStatement.setString(2, idProviderCode);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                result = rs.getString("IDPARTNER");
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("getPartnerCode --> ex = [" + e + "]");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    final String getInfoEbankUser = "BEGIN getInfoEbankUser(?,?,?); END;";

    /**
     *
     * @param userid
     * @param usertype
     * @return
     * @throws SQLException
     */
    public EBANKUSER getInfoEbankUser(String userid, String usertype) throws SQLException {
        CallableStatement calStmt;
        try {
            EBANKUSER user = null;
            calStmt = connection.prepareCall(getInfoEbankUser);
            calStmt.setString(1, userid);
            calStmt.setString(2, usertype);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.executeQuery();
            ResultSet rs = (ResultSet) calStmt.getObject(3);
            if (rs == null) {
                LOGGER.warn("Not found");
                throw new Exception("Not found");
            }
            while (rs.next()) {
                user = new EBANKUSER();
                user.setADDRESS(rs.getString("ADDRESS"));
                user.setCHK_STATUS(rs.getString("CHK_STATUS"));
                user.setFULL_NAME(rs.getString("FULLNAME"));
                user.setIDNUMBER(rs.getString("IDNUMBER"));
                user.setIDTYPE(rs.getString("IDTYPE"));
                user.setISSUEDATE(rs.getString("ISSUEDATE"));
                user.setISSUEPLACE(rs.getString("ISSUEPLACE"));
                user.setPHONENUMBER(rs.getString("PHONENUMBER"));
                user.setIduser(userid);
                user.setCUST_NO(rs.getString("CUST_NO"));
            }
            if (user != null) {
                SmsSCBBO smsBO = new SmsSCBBO();
                ArrayList arrAccNo = smsBO.getListAccountByCif(user.getCUST_NO());
                List listAc = new ArrayList();
                for (int i = 0; i < arrAccNo.size(); i++) {
                    HashMap<?, ?> HMUser = (HashMap<?, ?>) arrAccNo.get(i);
                    listAc.add(HMUser.get("cust_ac_no").toString());
                }
                user.setCUST_AC_NO(listAc);
            }
            return user;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            connection.close();
        }
    }

    private final String INSERTKICHHOATTHE = "INSERT INTO KICHHOATTHE (TRANSID, REQUESTER, PHONENUMBER, REQ_MESSAGE, REQ_TIME, SMS_ID) VALUES (?, ?, ?, ?, ?, ?)";

    
    
            
    /**
     *
     * @param info
     * @return
     * @throws Exception
     */
    public boolean INSERTKICHHOATTHE(KichHoatTheInfo info) throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(INSERTKICHHOATTHE);
            preStatement.setString(1, info.getTransId());
            preStatement.setString(2, info.getRequester());
            preStatement.setString(3, info.getPhoneNumber());
            preStatement.setString(4, info.getReqMessage());
            preStatement.setDate(5, new java.sql.Date(info.getReqTime().getTime()));
            preStatement.setString(6, info.getSmsId() != null ? info.getSmsId() : "");
            return preStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private final String UPDATEKICHHOATTHE = "UPDATE KICHHOATTHE SET RES_MESSAGE = ?, EXEC_TIME = SYSDATE, CMND = ?, CARDNUMBER = ?, CUS_NAME = ?, CIF = ?, CARDLIMIT = ?, LOC = ?, CARDTYPE = ?, BRANCHCODE = ?, DELIVERED = ?, STATUS = ?, CWRESPCODE = ?, CWRESPDESC = ?, CWRESPSEQ = ?, EXPI_DT = ? WHERE TRANSID = ? AND REQUESTER = ?";

    /**
     *
     * @param info
     * @return
     * @throws Exception
     */
    public boolean UPDATEKICHHOATTHE(KichHoatTheInfo info) throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(UPDATEKICHHOATTHE);
            preStatement.setString(1, info.getResMessage());
            preStatement.setString(2, info.getCmnd());
            preStatement.setString(3, info.getCardNumber());
            preStatement.setString(4, info.getCusName());
            preStatement.setString(5, info.getCif());
            preStatement.setString(6, info.getCardLimit());
            preStatement.setString(7, info.getLoc());
            preStatement.setString(8, info.getCardType());
            preStatement.setString(9, info.getBranchCode());
            //preStatement.setString(10, info.getDelivered());
            preStatement.setString(10, "1");
            preStatement.setString(11, info.getStatus());
            preStatement.setString(12, info.getResponseCode());
            preStatement.setString(13, info.getResponseDescription());
            preStatement.setString(14, info.getSequence());
            preStatement.setString(15, info.getExpiryDate());
            preStatement.setString(16, info.getTransId());
            preStatement.setString(17, info.getRequester());
            return preStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param info
     * @return
     * @throws Exception
     */
    public boolean KICHHOATTHE(KichHoatTheInfo info) throws Exception {
        return (info.getStatus() == null || info.getStatus().isEmpty())
                ? INSERTKICHHOATTHE(info)
                : UPDATEKICHHOATTHE(info);
    }

    private final String INSERTKHOATHE_REPORT = "INSERT INTO KHOATHE_REPORT (KHOATHEID, REQUESTER, PHONENUMBER, REQ_MESSAGE, REQ_TIME, SMS_ID) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     *
     * @param khoaTheInfo
     * @return
     * @throws Exception
     */
    public boolean INSERTKHOATHE(KhoaTheInfo khoaTheInfo) throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareStatement(INSERTKHOATHE_REPORT);
            preStatement.setString(1, khoaTheInfo.getKhoaTheId());
            preStatement.setString(2, khoaTheInfo.getRequester());
            preStatement.setString(3, khoaTheInfo.getPhoneNumber());
            preStatement.setString(4, khoaTheInfo.getReqMessage());
            preStatement.setDate(5, new java.sql.Date(khoaTheInfo.getReqTime().getTime()));
            preStatement.setString(6, khoaTheInfo.getSmsId());
            return preStatement.executeUpdate() > 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private final String UPDATEKHOATHE_REPORT = "UPDATE KHOATHE_REPORT SET RES_MESSAGE = ?, EXEC_TIME = SYSDATE WHERE KHOATHEID = ? AND REQUESTER = ?";

    /**
     *
     * @param khoaTheInfo
     * @return
     * @throws Exception
     */
    public boolean UPDATEKHOATHE(KhoaTheInfo khoaTheInfo) throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareStatement(UPDATEKHOATHE_REPORT);
            preStatement.setString(1, khoaTheInfo.getResMessage());
            preStatement.setString(2, khoaTheInfo.getKhoaTheId());
            preStatement.setString(3, khoaTheInfo.getRequester());
            return preStatement.executeUpdate() > 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private final String QUERY_SMS_TO_RESEND = "BEGIN PROC_LIST_SMS(?,?,?,?); END;";

    public List<SmsDetail> QUERY_SMS_TO_RESEND(String phone, String fromDate, String toDate) throws SQLException, Exception {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        List<SmsDetail> result = new ArrayList<>();
        try {
            calStmt = connection.prepareCall(QUERY_SMS_TO_RESEND);
            calStmt.setString(1, phone);
            calStmt.setDate(2, java.sql.Date.valueOf(fromDate));
            calStmt.setDate(3, java.sql.Date.valueOf(toDate));
            calStmt.registerOutParameter(4, OracleTypes.CURSOR);
            calStmt.executeQuery();
            rs = (ResultSet) calStmt.getObject(4);
            if (rs != null) {
                int i = 0;
                while (rs.next()) {
                    SmsDetail smsDetail = new SmsDetail();
                    smsDetail.setId(String.valueOf(i++));
                    smsDetail.setIdRef(rs.getString("ID"));
                    smsDetail.setPhoneNumber(rs.getString("MOBILE"));
                    smsDetail.setMessage(rs.getString("CONTENT"));
                    smsDetail.setMessageEncypt(rs.getString("CONTENT_ENCRYPT"));
                    String sendTime = rs.getString("SEND_TIME");
                    String dateStr = DateUtils.changeFormatDate(sendTime, "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy HH:mm:ss");
                    smsDetail.setTime(dateStr);
                    String trangThai = rs.getString("TT");
                    switch (trangThai) {
                        case "1":
                            smsDetail.setMessageStatus("Thành công");
                            break;
                        default:
                            smsDetail.setMessageStatus("Chưa thành công");
                            break;
                    }
                    result.add(smsDetail);
                }
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private final String QUERY_SMS_TO_RESEND_BY_ID = "SELECT * FROM SMS_RESEND T WHERE T.ID = ?";

    public SmsDetail QUERY_SMS_TO_RESEND_BY_ID(String id) throws SQLException, Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        SmsDetail result = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(QUERY_SMS_TO_RESEND_BY_ID);
            preStatement.setString(1, id);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                result = new SmsDetail();
                result.setId(rs.getString("ID"));
                result.setBranchCode(rs.getString("BRANCH_CODE"));
                result.setIdChannelUserMaker(rs.getString("IDCHANNELUSER_MAKER"));
                result.setIdRef(rs.getString("IDREF"));
                result.setIdUserMaker(rs.getString("IDUSER_MAKER"));
                result.setInsDateMaker(rs.getString("INSDATE_MAKER"));
                result.setMessage(rs.getString("MESSAGE"));
                result.setMessageEncypt(rs.getString("MESSAGE_ENCRYPT"));
                result.setPhoneNumber(rs.getString("PHONENUMBER"));
                result.setTime(rs.getString("SENDED_TIME"));
                break;
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public boolean CIMS_RESEND(SmsDetail info, int action) throws Exception {
        switch (action) {
            case 0:
                /* Khoi tao trang thai lap lenh */
                return INSERT_CIMS_RESEND(info);
            case 1:
            /* Cap nhat user duyet va thuc hien duyet */
            case 2:
                /* Cap nhat user duyet va tu choi gui tin nhan */
                return UPDATE_CIMS_RESEND(info, action);
            default:
                /* Action is out of scope */
                return false;
        }
    }

    private static final String GETSEQCIMS_RESEND = "SELECT SEQ_SMS_RESEND.NEXTVAL AS SEQUENSE FROM DUAL";
    private static final String INSERT_CIMS_RESEND = "INSERT INTO SMS_RESEND (ID, IDREF, PHONENUMBER, MESSAGE, MESSAGE_ENCRYPT, IDUSER_MAKER, IDCHANNELUSER_MAKER, INSDATE_MAKER, BRANCH_CODE, SENDED_TIME, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?, 0)";

    public boolean INSERT_CIMS_RESEND(SmsDetail info) throws Exception {
        boolean result = false;
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String sequence = "";
        try {
            preStatement = connection.prepareStatement(GETSEQCIMS_RESEND);
            rs = preStatement.executeQuery();
            connection.commit();
            while (rs.next()) {
                sequence = rs.getString("SEQUENSE");
                break;
            }
            if (!sequence.isEmpty()) {
                preStatement = connection.prepareStatement(INSERT_CIMS_RESEND);
                preStatement.setString(1, sequence);
                preStatement.setString(2, info.getIdRef());
                preStatement.setString(3, info.getPhoneNumber());
                preStatement.setString(4, info.getMessage());
                preStatement.setString(5, info.getMessageEncypt());
                preStatement.setString(6, info.getIdUserMaker());
                preStatement.setString(7, info.getIdChannelUserMaker());
                preStatement.setString(8, info.getBranchCode());
                preStatement.setString(9, info.getTime());
                result = preStatement.executeUpdate() > 0;
                connection.commit();
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private final String QUERY_SMS_TO_RESEND_BY_ID_WITH_STATUS_INIT = "SELECT COUNT(*) FROM SMS_RESEND T WHERE T.ID = ? AND T.STATUS = '0'";
    private static final String GET_SEQ_SMS_SENDER = "SELECT SEQ_SMS_SENDER.NEXTVAL@SMS_VNPAY AS SEQUENSE FROM DUAL";
    private final String INSERT_SMS_TO_RESEND = "INSERT INTO SMS_SENDER@SMS_VNPAY (ID, ACCOUNT, MOBILE, CONTENT, SERVICE_TYPE, SERVICE_CODE, REQUEST_ID, SEND_FLAG, ENCRYPT, INS_TIME) VALUES (?, '1', ?, ?, 'GLAI', 'GLAI', 'SCB', 0, 0, SYSDATE)";
    private final String UPDATE_CIMS_RESEND = "UPDATE SMS_RESEND SET IDUSER_CHECKER = ?, IDCHANNELUSER_CHECKER = ?, INSDATE_CHECKER = SYSDATE, STATUS = ? WHERE ID = ?";

    public boolean UPDATE_CIMS_RESEND(SmsDetail info, int action) throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            /* bien status
            * 0: khoi tao; 1: da gui thanh cong; 2: da gui that bai; 3: da tu choi
             */
            preStatement = connection.prepareStatement(QUERY_SMS_TO_RESEND_BY_ID_WITH_STATUS_INIT);
            preStatement.setString(1, info.getId());
            rs = preStatement.executeQuery();
            if (rs.next()) {
                /* Co ton tai giao dich cho duyet */
                switch (action) {
                    case 1:
                        /* Cap nhat user duyet va thuc hien duyet */
                        String sequence = "";
                        preStatement = connection.prepareStatement(GET_SEQ_SMS_SENDER);
                        rs = preStatement.executeQuery();
                        connection.commit();
                        while (rs.next()) {
                            sequence = rs.getString("SEQUENSE");
                            break;
                        }
                        if (!sequence.isEmpty()) {
                            /* Thuc hien gui sms */
                            preStatement = connection.prepareStatement(INSERT_SMS_TO_RESEND);
                            preStatement.setString(1, sequence);
                            preStatement.setString(2, info.getPhoneNumber());
                            preStatement.setString(3, info.getMessage());
                            int resultUpdate = preStatement.executeUpdate();
                            connection.commit();
                            if (resultUpdate > 0) {
                                /* Gui thanh cong */
                                info.setStatus("1");
                            } else {
                                /* Gui that bai */
                                info.setStatus("2");
                            }
                        } else {
                            /* Gui that bai */
                            info.setStatus("2");
                        }
                        break;
                    case 2:
                        /* Cap nhat user duyet va tu choi gui tin nhan */
                        info.setStatus("3");
                        break;
                    default:
                        /* Ko thuc hien gi, giu nguyen trang status = khoi tao */
                        info.setStatus("0");
                        break;
                }
                if (!"0".equals(info.getStatus())) {
                    /* Thuc hien cap nhat trang thai */
                    preStatement = connection.prepareStatement(UPDATE_CIMS_RESEND);
                    preStatement.setString(1, info.getIdUserChecker());
                    preStatement.setString(2, info.getIdChannelUserChecker());
                    preStatement.setString(3, info.getStatus());
                    preStatement.setString(4, info.getId());
                    result = preStatement.executeUpdate() > 0;
                    connection.commit();
                } else {
                    LOGGER.warn("Vui long thuc hien duyet lai giao dich gui 1 tin nhan do luong xu ly hien tai chua handle");
                }
            } else {
                LOGGER.warn("Giao dich da duoc duyet. Vui long chon giao dich khac de duyet");
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static final String GETSEQKHOATHEDETAIL = "SELECT SEQ_LOCKTHE8149.NEXTVAL AS SEQUENSE FROM DUAL";
    private final String INSERTKHOATHEDETAIL_REPORT = "INSERT INTO KHOATHEDETAILS_REPORT (KHOATHEDETAILSID, KHOATHEID, REQUESTER, LAST4DIGITS, CARDTYPE, CMND, CARDNUMBER, CUS_NAME, PAN_MASK, ACTIVATE_DATE, LOC, BRANCHCODE, CIF, CARDLIMIT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     *
     * @param khoaTheDetailsInfo
     * @return
     * @throws Exception
     */
    public String INSERTKHOATHEDETAIL(KhoaTheDetailsInfo khoaTheDetailsInfo) throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String sequence = "";
        try {
            preStatement = connection.prepareStatement(GETSEQKHOATHEDETAIL);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                sequence = khoaTheDetailsInfo.getKhoaTheDetailsId() + rs.getString("SEQUENSE");
                break;
            }
            if (!sequence.isEmpty()) {
                preStatement = connection.prepareStatement(INSERTKHOATHEDETAIL_REPORT);
                preStatement.setString(1, sequence);
                preStatement.setString(2, khoaTheDetailsInfo.getKhoaTheId());
                preStatement.setString(3, khoaTheDetailsInfo.getRequester());
                preStatement.setString(4, khoaTheDetailsInfo.getLast4Digits());
                preStatement.setString(5, khoaTheDetailsInfo.getCardInfo().getCardType());
                preStatement.setString(6, khoaTheDetailsInfo.getCardInfo().getCmnd());
                preStatement.setString(7, khoaTheDetailsInfo.getCardInfo().getPan_mask());
                preStatement.setString(8, khoaTheDetailsInfo.getCardInfo().getCustomerName());
                preStatement.setString(9, khoaTheDetailsInfo.getCardInfo().getPan_mask());
                preStatement.setString(10, khoaTheDetailsInfo.getCardInfo().getActiveDate());
                preStatement.setString(11, khoaTheDetailsInfo.getCardInfo().getLoc());
                preStatement.setString(12, khoaTheDetailsInfo.getCardInfo().getBranchCode());
                preStatement.setString(13, khoaTheDetailsInfo.getCardInfo().getCif());
                preStatement.setString(14, khoaTheDetailsInfo.getCardInfo().getLocLimit());
                if (preStatement.executeUpdate() > 0) {
                    return sequence;
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return "";
    }

    private final String UPDATEKHOATHEDETAIL_REPORT = "UPDATE KHOATHEDETAILS_REPORT SET STATUS = ?, CWCODE = ?, CWDESC = ? WHERE KHOATHEDETAILSID = ? AND REQUESTER = ?";

    /**
     *
     * @param khoaTheDetailsInfo
     * @return
     * @throws Exception
     */
    public boolean UPDATEKHOATHEDETAIL(KhoaTheDetailsInfo khoaTheDetailsInfo) throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareStatement(UPDATEKHOATHEDETAIL_REPORT);
            preStatement.setString(1, khoaTheDetailsInfo.getStatus());
            preStatement.setString(2, khoaTheDetailsInfo.getResponseCode());
            preStatement.setString(3, khoaTheDetailsInfo.getResponseDesc());
            preStatement.setString(4, khoaTheDetailsInfo.getKhoaTheDetailsId());
            preStatement.setString(5, khoaTheDetailsInfo.getRequester());
            return preStatement.executeUpdate() > 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static final String GETTHUPHIKHCNTHEOBRANCH = "SELECT \"ID\", \"CUSTNO\", \"CUSTNAME\", \"ACCTNO\", \"PHONENO\", \"RETRY\", \"STATUS\", \"NOTE\", \"NOPHI\", \"SENDSMS\", \"THANGNAM\", \"PAYDATE_FCUBS\"\n"
            + "	, \"DATEFAIL\", \"FEEAMOUNT\", \"REF_FCUBS\", \"BRANCH_CODE\", \"THANGNAMRETRY\", \"LOCKDATE\", \"LOCKSTATUS\"\n"
            + "FROM (\n"
            + "	SELECT * FROM (\n"
            + "		SELECT b.local_branch, a.*\n"
            + "		FROM sms_scb.SMS_THUPHI a\n"
            + "		LEFT JOIN sttms_customer@fcatfcclink b\n"
            + "		ON a.custno = b.customer_no\n"
            + "		WHERE RETRY = 0\n"
            + "			AND NOPHI = 0\n"
            + "			AND status = 'W'\n"
            + "			AND LOCKSTATUS <> 'Y'\n"
            + "			AND THANGNAM = TO_CHAR (SYSDATE, 'MM/yyyy')\n"
            + "			AND LOAISMS = 'KHCN'\n"
            + "			AND ref_fcubs IS NULL\n"
            + "			AND b.local_branch = ?\n"
            + "		\n"
            + "		UNION ALL\n"
            + "		\n"
            + "		SELECT b.local_branch, a.*\n"
            + "		FROM sms_scb.SMS_THUPHI a\n"
            + "		LEFT JOIN sttms_customer@fcatfcclink b\n"
            + "		ON a.custno = b.customer_no\n"
            + "		WHERE RETRY <= 3\n"
            + "			AND NOPHI = 0\n"
            + "			AND status = 'F'\n"
            + "			AND LOCKSTATUS <> 'Y'\n"
            + "			AND THANGNAM = TO_CHAR (SYSDATE, 'MM/yyyy')\n"
            + "			AND TRUNC (datefail) <> TRUNC (SYSDATE)\n"
            + "			AND LOAISMS = 'KHCN'\n"
            + "			AND ref_fcubs IS NULL\n"
            + "			AND b.local_branch = ?\n"
            + "		\n"
            + "		UNION ALL\n"
            + "		\n"
            + "		SELECT b.local_branch, a.*\n"
            + "		FROM sms_scb.SMS_THUPHI a\n"
            + "		LEFT JOIN sttms_customer@fcatfcclink b\n"
            + "		ON a.custno = b.customer_no\n"
            + "		WHERE RETRY <= 3\n"
            + "			AND NOPHI = 1\n"
            + "			AND status = 'F'\n"
            + "			AND LOCKSTATUS <> 'Y'\n"
            + "			AND THANGNAMRETRY = TO_CHAR (SYSDATE, 'MM/yyyy')\n"
            + "			AND TRUNC (datefail) <> TRUNC (SYSDATE)\n"
            + "			AND LOAISMS = 'KHCN'\n"
            + "			AND ref_fcubs IS NULL\n"
            + "			AND b.local_branch = ?\n"
            + "	)\n"
            + "	WHERE TO_CHAR (SYSDATE, 'dd') >= '01'\n"
            + "	AND TO_CHAR (SYSDATE, 'DY') <> 'SUN'\n"
            + "	ORDER BY branch_code, CUSTNO ASC\n"
            + "	)\n"
            + "	WHERE ROWNUM <= ?";

    private static final String ISNOPHI = "SELECT * FROM SMS_THUPHI T WHERE T.CUSTNO = ? AND T.STATUS IN ('W', 'F')";

    /**
     *
     * @param branch
     * @param number
     * @return
     * @throws SQLException
     */
    public List<VwSmsThuphi> getThuPhiKhcnTheoBranch(String branch, int number) throws SQLException {
        LOGGER.info("getThuPhiKhcnTheoBranch - IN --> branch =[" + branch + "], number = [" + number + "]");
        List<VwSmsThuphi> result = new ArrayList();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(GETTHUPHIKHCNTHEOBRANCH);
            preStatement.setString(1, branch);
            preStatement.setString(2, branch);
            preStatement.setString(3, branch);
            preStatement.setInt(4, number);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                VwSmsThuphi item = new VwSmsThuphi();
                item.setId(rs.getString("ID"));
                item.setCustno(rs.getString("CUSTNO"));
                item.setCustname(rs.getString("CUSTNAME"));
                item.setAcctno(rs.getString("ACCTNO"));
                item.setPhoneno(rs.getString("PHONENO"));
                item.setRetry(rs.getInt("RETRY"));
                item.setStatus(rs.getString("STATUS").charAt(0));
                item.setNote(rs.getString("NOTE"));
                item.setNophi(rs.getInt("NOPHI"));
                item.setSendsms(rs.getString("SENDSMS").charAt(0));
                item.setThangnam(rs.getString("THANGNAM"));
                item.setPaydateFcubs(rs.getDate("PAYDATE_FCUBS"));
                item.setDatefail(rs.getDate("DATEFAIL"));
                item.setFeeamount(rs.getLong("FEEAMOUNT"));
                item.setRefFcubs(rs.getString("REF_FCUBS"));
                item.setBranchCode(rs.getString("BRANCH_CODE"));
                item.setThangnamretry(rs.getString("THANGNAMRETRY"));
                item.setLockdate(rs.getDate("LOCKDATE"));
                item.setLockstatus(rs.getString("LOCKSTATUS").charAt(0));
                result.add(item);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.gc();
        }
        LOGGER.info("getThuPhiKhcnTheoBranch - OUT --> branch =[" + branch + "], number = [" + number + "], size = [" + result.size() + "]");
        return result;
    }

    private static final String GETTHUPHIKHCN = "SELECT * FROM VW_SMS_THUPHI_KHCN WHERE ROWNUM <= ?";

    private static final String GETTHUPHIKHCNTHEOBRANCHV2 = "SELECT * FROM VW_SMS_THUPHI_KHCN WHERE BRANCH_CODE = ? AND ROWNUM <= ?";

    public List<VwSmsThuphi> getThuPhiKhcn(String branch, int number) throws SQLException {
        List<VwSmsThuphi> result = new ArrayList();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            if (branch != null && !branch.isEmpty()) {
                preStatement = connection.prepareStatement(GETTHUPHIKHCNTHEOBRANCHV2);
                preStatement.setString(1, branch);
                preStatement.setInt(2, number);
            } else {
                preStatement = connection.prepareStatement(GETTHUPHIKHCN);
                preStatement.setInt(1, number);
            }
            rs = preStatement.executeQuery();
            while (rs.next()) {
                VwSmsThuphi item = new VwSmsThuphi();
                item.setId(rs.getString("ID"));
                item.setCustno(rs.getString("CUSTNO"));
                item.setCustname(rs.getString("CUSTNAME"));
                item.setAcctno(rs.getString("ACCTNO"));
                item.setPhoneno(rs.getString("PHONENO"));
                item.setRetry(rs.getInt("RETRY"));
                item.setStatus(rs.getString("STATUS").charAt(0));
                item.setNote(rs.getString("NOTE"));
                item.setNophi(rs.getInt("NOPHI"));
                item.setSendsms(rs.getString("SENDSMS").charAt(0));
                item.setThangnam(rs.getString("THANGNAM"));
                item.setPaydateFcubs(rs.getDate("PAYDATE_FCUBS"));
                item.setDatefail(rs.getDate("DATEFAIL"));
                item.setFeeamount(rs.getLong("FEEAMOUNT"));
                item.setRefFcubs(rs.getString("REF_FCUBS"));
                item.setBranchCode(rs.getString("BRANCH_CODE"));
                item.setThangnamretry(rs.getString("THANGNAMRETRY"));
                item.setLockdate(rs.getDate("LOCKDATE"));
                item.setLockstatus(rs.getString("LOCKSTATUS").charAt(0));
                result.add(item);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.gc();
        }
        return result;
    }

    public int isNoPhi(String custNo) throws SQLException {
        LOGGER.info("isNoPhi - IN --> custNo =[" + custNo + "]");
        int result = -1;
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(ISNOPHI);
            preStatement.setString(1, custNo);
            rs = preStatement.executeQuery();
            result = rs.next() ? 1 : 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        LOGGER.info("isNoPhi - OUT --> custNo =[" + custNo + "], result = [" + result + "]");
        return result;
    }

    private static final String GETTHUPHIKHDN = "SELECT * FROM VW_SMS_THUPHI_KHDN";

    private final String UPDATE_S_COMBO = "BEGIN PKG_FEE_EBANK.PROC_UPDATE_UUDAI(?); END;";

    /**
     *
     * @return @throws SQLException
     */
    public List<VwSmsThuphi> getThuPhiKhdn() throws SQLException {
        List<VwSmsThuphi> result = new ArrayList();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        CallableStatement calStmt = null;
        String thangNam = DateUtils.getFormatDate("MM/yyyy");
        try {
            calStmt = connection.prepareCall(UPDATE_S_COMBO);
            calStmt.setString(1, thangNam); // format: 01/2018
            calStmt.execute();

            preStatement = connection.prepareStatement(GETTHUPHIKHDN);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                VwSmsThuphi item = new VwSmsThuphi();
                item.setId(rs.getString("ID"));
                item.setCustno(rs.getString("CUSTNO"));
                item.setCustname(rs.getString("CUSTNAME"));
                item.setAcctno(rs.getString("ACCTNO"));
                item.setPhoneno(rs.getString("PHONENO"));
                item.setRetry(rs.getInt("RETRY"));
                item.setStatus(rs.getString("STATUS").charAt(0));
                item.setNote(rs.getString("NOTE"));
                item.setNophi(rs.getInt("NOPHI"));
                item.setSendsms(rs.getString("SENDSMS").charAt(0));
                item.setThangnam(rs.getString("THANGNAM"));
                item.setPaydateFcubs(rs.getDate("PAYDATE_FCUBS"));
                item.setDatefail(rs.getDate("DATEFAIL"));
                item.setFeeamount(rs.getLong("FEEAMOUNT"));
                item.setRefFcubs(rs.getString("REF_FCUBS"));
                item.setBranchCode(rs.getString("BRANCH_CODE"));
                item.setThangnamretry(rs.getString("THANGNAMRETRY"));
                item.setLockdate(rs.getDate("LOCKDATE"));
                item.setLockstatus(rs.getString("LOCKSTATUS").charAt(0));
                result.add(item);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.gc();
        }
        return result;
    }

    private static final String GETPBLPARTNERCODE = "select * from SMS_SCB.PBL_PARTNERCODE where idpartner=?";

    /**
     *
     * @param idPartner
     * @return
     * @throws SQLException
     */
    public List<Pbl_partnercode> getPblPartnerCode(String idPartner) throws SQLException {
        List<Pbl_partnercode> result = new ArrayList();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(GETPBLPARTNERCODE);
            preStatement.setString(1, idPartner);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                Pbl_partnercode item = new Pbl_partnercode();
                item.setCommonProviderId(rs.getString("COMMONPROVIDERID"));
                item.setIdPartner(rs.getString("IDPARTNER"));
                item.setIdProvider(rs.getString("IDPROVIDER"));
                item.setIdServiceType(rs.getString("IDSERVICETYPE"));
                item.setProviderCode(rs.getString("PROVIDERCODE"));
                item.setServiceCode(rs.getString("SERVICECODE"));
                result.add(item);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.gc();
        }
        return result;
    }

    private static final String GETUNLOCKPHIALL = "SELECT * FROM VW_SMS_THUPHI_UNLOCK WHERE ROWNUM <= ? ORDER BY THANGNAMRETRY ASC";

    private static final String GETUNLOCKPHICIF = "SELECT * FROM VW_SMS_THUPHI_UNLOCK WHERE CUSTNO = ? ORDER BY THANGNAMRETRY ASC";

    /**
     *
     * @param cif
     * @param number
     * @return
     * @throws SQLException
     */
    public List<VwSmsThuphi> getUnlockPhi(String cif, int number) throws SQLException {
        List<VwSmsThuphi> result = new ArrayList();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            if (cif == null || cif.isEmpty()) {
                preStatement = connection.prepareStatement(GETUNLOCKPHIALL);
                preStatement.setInt(1, number);
                rs = preStatement.executeQuery();
            } else {
                preStatement = connection.prepareStatement(GETUNLOCKPHICIF);
                preStatement.setString(1, cif);
                rs = preStatement.executeQuery();
            }
            while (rs.next()) {
                VwSmsThuphi item = new VwSmsThuphi();
                item.setId(rs.getString("ID"));
                item.setCustno(rs.getString("CUSTNO"));
                item.setCustname(rs.getString("CUSTNAME"));
                item.setAcctno(rs.getString("ACCTNO"));
                item.setPhoneno(rs.getString("PHONENO"));
                item.setRetry(rs.getInt("RETRY"));
                item.setStatus(rs.getString("STATUS").charAt(0));
                item.setNote(rs.getString("NOTE"));
                item.setNophi(rs.getInt("NOPHI"));
                item.setSendsms(rs.getString("SENDSMS").charAt(0));
                item.setThangnam(rs.getString("THANGNAM"));
                item.setPaydateFcubs(rs.getDate("PAYDATE_FCUBS"));
                item.setDatefail(rs.getDate("DATEFAIL"));
                item.setFeeamount(rs.getLong("FEEAMOUNT"));
                item.setRefFcubs(rs.getString("REF_FCUBS"));
                item.setBranchCode(rs.getString("BRANCH_CODE"));
                item.setThangnamretry(rs.getString("THANGNAMRETRY"));
                item.setLockdate(rs.getDate("LOCKDATE"));
                item.setLockstatus(rs.getString("LOCKSTATUS").charAt(0));
                result.add(item);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.gc();
        }
        return result;
    }

    private static final String GETSTKNEEDTORESEND = "select * from SMS_SCB.GW_EMAIL_TD where id = ?";

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public GwEmailTd getSTKNeedToResend(String id) throws SQLException {
        GwEmailTd result = new GwEmailTd();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareStatement(GETSTKNEEDTORESEND);
            preStatement.setString(1, id);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                result.setCreatedDate(rs.getDate("CREATED_DATE"));
                result.setCustAcNo(rs.getString("CUST_AC_NO"));
                result.setCustomerNo(rs.getString("CUSTOMER_NO"));
                result.setEmail(rs.getString("EMAIL"));
                result.setId(rs.getBigDecimal("ID"));
                result.setSerialno(rs.getString("SERIALNO"));
                result.setSrcchannel(rs.getString("SRCCHANNEL"));
                result.setStatus(rs.getString("STATUS"));
                result.setTdAmount(rs.getLong("TDAMOUNT"));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.gc();
        }
        return result;
    }

    final String INSERTORGBILLPAID = "INSERT INTO T_ORG_BILLPAID (CUSTOMER_NO, CUSTOMER_CODE, SERVICE_CODE, PAID_PERIOD, PAID_DATE, UPD_DATE, PROVIDER_CODE, AMOUNT) VALUES (?, ?, ?, ?, SYSDATE, SYSDATE, ?, ?)";

    /**
     *
     * @param value
     * @return
     * @throws SQLException
     */
    public boolean insertOrgBillPaid(OrgBillPaid value) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareStatement(INSERTORGBILLPAID);
            preStatement.setString(1, value.getCustomerNo());
            preStatement.setString(2, value.getCustomerCode());
            preStatement.setString(3, value.getServiceCode());
            preStatement.setString(4, value.getPaidPeriod());
            preStatement.setString(5, value.getProviderCode());
            preStatement.setBigDecimal(6, value.getAmount());
            return preStatement.executeUpdate() > 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String CARD_ADJUSTMENT_CHECK = "SELECT * FROM CARDADJUSTMENT WHERE REFERENCENO = ? AND PARTNER = ?";
    final String CARD_ADJUSTMENT_SEQUENCE = "SELECT SEQ_CARDADJUSTMENT.NEXTVAL AS SEQUENCENO FROM DUAL";
    final String CARD_ADJUSTMENT_INSERT = "INSERT INTO CARDADJUSTMENT (SEQUENCENO, REFERENCENO, PARTNER, ACTIND, ADJCDE, FI, PAN, ADJAMT, BRCHCDE, MERCHANTID, TERMID, OFFICERID, REMARKS, STATUS, CREATEDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '02', SYSDATE)";
    final String CARD_ADJUSTMENT_UPDATE = "UPDATE CARDADJUSTMENT SET RESPCODE = ?, RESPDESC = ?, STATUS = ? WHERE SEQUENCENO = ?";

    /**
     *
     * @param req
     * @param res
     * @param action
     * @return
     * @throws SQLException
     */
    public CardAdjustmentRes cardAdjustment(CardAdjustmentReq req, CardAdjustmentRes res, DBActionEnum action) throws SQLException {
        // Khoi tao response
        CardAdjustmentRes result = new CardAdjustmentRes();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String sequenceNo = null;
        try {
            /*
            * Kiem tra xem dang thuc hien thao tac gi
             */
            switch (action) {
                case INSERT: {
                    // Cau hinh so refNo doi tac truyen qua
                    result.setRefNo(req.getRefNo());
                    // Kiem tra xem so refno co bi duplicate hay ko
                    preStatement = connection.prepareStatement(CARD_ADJUSTMENT_CHECK);
                    preStatement.setString(1, req.getRefNo());
                    preStatement.setString(2, req.getPartner());
                    rs = preStatement.executeQuery();
                    // Neu ko co bi trung so refno
                    if (!rs.next()) {
                        // Thuc hien lay so sequence de goi qua CW
                        preStatement = connection.prepareStatement(GETDIRECTDEBITSEQNO);
                        rs = preStatement.executeQuery();
                        while (rs.next()) {
                            sequenceNo = rs.getString("SEQUENCENO");
                        }
                        // Kiem tra xem co cap duoc so sequence hay ko
                        if (sequenceNo != null && !sequenceNo.isEmpty()) {
                            // Thuc hien insert du lieu vao database
                            preStatement = connection.prepareStatement(CARD_ADJUSTMENT_INSERT);
                            preStatement.setString(1, sequenceNo);
                            preStatement.setString(2, req.getRefNo());
                            preStatement.setString(3, req.getPartner());
                            preStatement.setString(4, req.getActInd());
                            preStatement.setString(5, req.getAdjCde());
                            preStatement.setString(6, req.getFi());
                            preStatement.setString(7, req.getPan());
                            preStatement.setString(8, req.getAdjAmt());
                            preStatement.setString(9, req.getBrchCde());
                            preStatement.setString(10, req.getMerchantId());
                            preStatement.setString(11, req.getTermId());
                            preStatement.setString(12, req.getOfficerId());
                            preStatement.setString(13, req.getRemarks());
                            // Kiem tra xem viec insert du lieu xuong database co thanh cong hay ko
                            if (preStatement.executeUpdate() > 0) {
                                connection.commit();
                                result.setSequenceNo(sequenceNo);
                            } else {
                                result.setErrorCode("14");
                                result.setErrorMsg("Cannot insert data of cardAdjustment to database");
                            }
                        } else {
                            // Cannot get the sequence
                            result.setErrorCode("11");
                            result.setErrorMsg("RefNo cannot mapping the sequenceNo");
                        }
                    } else {
                        // Duplicate refcode
                        result.setErrorCode("10");
                        result.setErrorMsg("Duplicate RefNo");
                    }
                    break;
                }
                case UPDATE: {
                    // Cau hinh so refno de tra ve
                    result.setRefNo(res.getRefNo());
                    result.setSequenceNo(res.getSequenceNo());
                    // Kiem tra xem CW tra ve thanh cong hay that bai
                    String status = "000".equals(res.getErrorCode()) ? "00" : "01";
                    // Cap nhat du lieu tra ve tu CW xuong database
                    preStatement = connection.prepareStatement(CARD_ADJUSTMENT_UPDATE);
                    preStatement.setString(1, res.getErrorCode());
                    preStatement.setString(2, res.getErrorMsg());
                    preStatement.setString(3, status);
                    preStatement.setString(4, res.getSequenceNo());
                    // Kiem tra cap nhat thanh cong hay that bai
                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        // Kiem tra xem CW tra ve thanh cong hay that bai de tu do cau hinh ket qua tra ve cho user
                        if ("000".equals(res.getErrorCode())) {
                            result.setErrorCode("00");
                            result.setErrorMsg("Card adjustment successfully");
                        } else {
                            result.setErrorCode("01");
                            result.setErrorMsg("Card adjustment failed");
                        }
                    } else { // Cap nhat xuong database that bai
                        LOGGER.warn("Cannot update card adjustment to database. Code = [" + res.getErrorCode() + "], Desc = [" + res.getErrorMsg() + "], Status = [" + status + "], Sequence = [" + res.getSequenceNo() + "]");
                        result.setErrorCode("15");
                        result.setErrorMsg("Cannot update data of cardAdjustment to database");
                    }
                    break;
                }
                case DELETE: // Chua ho tro
                default:
                    result.setErrorCode("13");
                    result.setErrorMsg("Action does not support");
                    break;
            }

            return result;
        } catch (Exception ex) { // Loi trong qua trinh xu ly, tra ve ket qua 99
            LOGGER.error(ex);
            result.setErrorCode("99");
            result.setErrorMsg("SYSTEM IS ERROR");
            return result;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static final String GETSCBBRANCH = "SELECT BRANCH_CODE AS BRANCHCODE, BRANCH_NAME AS BRANCHNAME FROM FCUSR01.STTM_BRANCH@FCATFCCLINK";

    /**
     *
     * @return @throws Exception
     */
    public SCBBranch getSCBBranch() throws Exception {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        SCBBranch scbBranch = new SCBBranch();
        try {
            preStatement = connection.prepareStatement(GETSCBBRANCH);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                String branchCode = rs.getString("BRANCHCODE");
                String branchName = rs.getString("BRANCHNAME");
                scbBranch.addItem(branchCode, branchName);
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("getSCBBranch --> ex = [" + e + "]");
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return scbBranch;
    }

    final String VISAQR_UPDATE_DIRECTDEBIT = "UPDATE VISAMERCHANTPUSHPAYMENTS SET DIDEBIT_RESCODE = ?, DIDEBIT_RESDESC = ?, DIDEBIT_APPPCODE = ?, STATUS = ? WHERE SEQUENCENO = ?";
    final String VISAQR_UPDATE_CORE = "UPDATE VISAMERCHANTPUSHPAYMENTS SET COREREF = ?, STATUS = ? WHERE SEQUENCENO = ?";
    final String VISAQR_UPDATE_MASTER = "UPDATE VISAMERCHANTPUSHPAYMENTS SET TRANSACTIONID = ?, TRANSMISSIONDATE = ?, SYSTRACEAUDITNUM = ?, RETRIEVALREFNUM = ?, APPROVALCODE = ?, ACTIONCODE = ?, RESPONSECODE = ?, STATUSID = ?, MERVERVALUE = ?, FEEPROIND = ?, CARDACCSTATE = ?, ERRORMESSAGE = ?, RESPONSEMESSAGE = ?, RESSTACODE = ?, RESSTASEVERITY = ?, RESSTAINFO = ?, RESSTASTATUS = ?, RESSTAMESSAGE = ?, STATUS = ? WHERE SEQUENCENO = ?";
    final String VISAQR_UPDATE_CARDADJUSTMENT = "UPDATE VISAMERCHANTPUSHPAYMENTS SET CARDADJ_SEQ = ?, CARDADJ_RESCODE = ?, CARDADJ_RESDESC = ?, STATUS = ? WHERE SEQUENCENO = ?";
    final String VISAQR_UPDATE_REFUNDCORE = "UPDATE VISAMERCHANTPUSHPAYMENTS SET REFUNDCOREREF = ?, STATUS = ? WHERE SEQUENCENO = ?";

    public int VISAQR(MVISAQRRQ req, MasterCardQrActionEnum action) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        int result = 0;
        try {
            connection.setAutoCommit(false);

            switch (action) {
                case UPDATE_DIRECTDEBIT: {
                    preStatement = connection.prepareStatement(VISAQR_UPDATE_DIRECTDEBIT);
                    preStatement.setString(1, req.getDirectDebitRes().getResponseCode());
                    preStatement.setString(2, req.getDirectDebitRes().getResponseDescription());
                    preStatement.setString(3, req.getDirectDebitRes().getApprovalCode());
                    preStatement.setString(4, req.getStatus());
                    preStatement.setString(5, req.getSequenceNo());

                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        result = 1;
                    }
                    break;
                }
                case UPDATE_CORE: {
                    preStatement = connection.prepareStatement(VISAQR_UPDATE_CORE);
                    preStatement.setString(1, req.getCoreRef());
                    preStatement.setString(2, req.getStatus());
                    preStatement.setString(3, req.getSequenceNo());

                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        result = 1;
                    }
                    break;
                }
                case UPDATE_MASTER: {
                    preStatement = connection.prepareStatement(VISAQR_UPDATE_MASTER);
                    preStatement.setString(1, req.getVisaResponse().getTransactionIdentifier());
                    preStatement.setString(2, req.getVisaResponse().getTransmissionDateTime());
                    preStatement.setString(3, req.getSysTraceAuditNum());
                    preStatement.setString(4, req.getRetrievalRefNum());
                    preStatement.setString(5, req.getVisaResponse().getApprovalCode());
                    preStatement.setString(6, req.getVisaResponse().getActionCode());
                    preStatement.setString(7, req.getVisaResponse().getResponseCode());
                    preStatement.setString(8, req.getVisaResponse().getStatusIdentifier());
                    preStatement.setString(9, req.getVisaResponse().getMerchantVerificationValue());
                    preStatement.setString(10, req.getVisaResponse().getFeeProgramIndicator());
                    preStatement.setString(11, req.getVisaResponse().getCardAcceptor().getAddress().getState());
                    preStatement.setString(12, req.getVisaResponse().getErrorMessage());
                    preStatement.setString(13, req.getVisaResponse().getResponseMessage());
                    preStatement.setString(14, req.getVisaResponse().getResponseStatus().getCode());
                    preStatement.setString(15, req.getVisaResponse().getResponseStatus().getSeverity());
                    preStatement.setString(16, req.getVisaResponse().getResponseStatus().getInfo());
                    preStatement.setString(17, req.getVisaResponse().getResponseStatus().getStatus());
                    preStatement.setString(18, req.getVisaResponse().getResponseStatus().getMessage());
                    preStatement.setString(19, req.getStatus());
                    preStatement.setString(20, req.getSequenceNo());

                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        result = 1;
                    }
                    break;
                }
                case UPDATE_CARDADJUSTMENT: {
                    preStatement = connection.prepareStatement(VISAQR_UPDATE_CARDADJUSTMENT);
                    preStatement.setString(1, req.getCardAdjRes().getCardAdjSequence());
                    preStatement.setString(2, req.getCardAdjRes().getResponseCode());
                    preStatement.setString(3, req.getCardAdjRes().getResponseDescription());
                    preStatement.setString(4, req.getStatus());
                    preStatement.setString(5, req.getSequenceNo());

                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        result = 1;
                    }
                    break;
                }
                case UPDATE_REFUNDCODE: {
                    preStatement = connection.prepareStatement(VISAQR_UPDATE_REFUNDCORE);
                    preStatement.setString(1, req.getRefundCoreRef());
                    preStatement.setString(2, req.getStatus());
                    preStatement.setString(3, req.getSequenceNo());

                    if (preStatement.executeUpdate() > 0) {
                        connection.commit();
                        result = 1;
                    }
                    break;
                }
                case INSERT:
                default:
                    result = -2;
                    break;
            }

            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
            return -1;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String SMS_THUPHI_DETAIL_INSERT = "INSERT INTO SMS_SCB.SMS_THUPHI_DETAIL (ID, ID_THUPHI, CUSTNO, FEEAMOUNT, THANGNAM, NOTE, EXECUTEMONTH) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String SMS_THUPHI_DETAIL_CHECK = "SELECT T.ID AS ID, T.NOTE AS NOTE FROM SMS_SCB.SMS_THUPHI_DETAIL T WHERE T.ID_THUPHI = ? AND T.EXECUTEMONTH = ? AND T.FEEAMOUNT = ? AND T.THANGNAM = ?";
    final String SMS_THUPHI_DETAIL_UPDATENOTE = "UPDATE SMS_SCB.SMS_THUPHI_DETAIL T SET T.NOTE = ? WHERE T.ID = ?";

    public int smsThuPhiDetails(SmsThuphi pb, SqlCommand action) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        int result = 0;
        try {
//            connection.setAutoCommit(false);
            switch (action) {
                case INSERT: {
                    String id = "", note = "";

                    preStatement = connection.prepareStatement(SMS_THUPHI_DETAIL_CHECK);
                    preStatement.setString(1, pb.getId());
                    preStatement.setString(2, pb.getExecuteMonth());
                    preStatement.setLong(3, pb.getFeeamount());
                    preStatement.setString(4, pb.getThangnam());
                    rs = preStatement.executeQuery();
                    while (rs.next()) {
                        id = rs.getString("ID");
                        note = rs.getString("NOTE");
                        break;
                    }

                    if (!id.isEmpty()) {
                        preStatement = connection.prepareStatement(SMS_THUPHI_DETAIL_UPDATENOTE);
                        preStatement.setString(1, note + '_' + pb.getGhichu());
                        preStatement.setString(2, id);
                        if (preStatement.executeUpdate() > 0) {
//                            connection.commit();
                            result = 1;
                        }
                    } else {
                        preStatement = connection.prepareStatement(SMS_THUPHI_DETAIL_INSERT);
                        id = pb.getId() + Utils.getRandomNumber();
                        preStatement.setString(1, id);
                        preStatement.setString(2, pb.getId());
                        preStatement.setString(3, pb.getCustno());
                        preStatement.setLong(4, pb.getFeeamount());
                        preStatement.setString(5, pb.getThangnam());
                        preStatement.setString(6, pb.getGhichu());
                        preStatement.setString(7, pb.getExecuteMonth());

                        if (preStatement.executeUpdate() > 0) {
//                            connection.commit();
                            result = 1;
                        }
                    }
                    break;
                }
                case DELETE:
                case QUERY:
                case UPDATE:
                default:
                    result = -2;
                    break;
            }

            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
//            connection.rollback();
            return -1;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<PayOnlineCollate> ONL_AnswerCollateData(String pPartnerID, String pTransdate) throws SQLException, Exception {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            List<PayOnlineCollate> resultList = new ArrayList<PayOnlineCollate>();
            calStmt = connection.prepareCall(onl_answerCollateData);
            calStmt.setString(1, pPartnerID);
            calStmt.setString(2, pTransdate);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.executeQuery();
            rs = (ResultSet) calStmt.getObject(3);
            if (rs == null) {
                LOGGER.warn("Not found");
                throw new Exception("Not found");
            }
            while (rs.next()) {
                PayOnlineCollate collate = new PayOnlineCollate();
                collate.setPartnerID(rs.getString("PARTNERID"));
                collate.setResultCollate(rs.getString("RESULTCOLLATE"));
                collate.setTypeTrans(rs.getString("TYPETRANS"));
                collate.setCCY(rs.getString("CCY"));
                collate.setAmount(rs.getBigDecimal("Amount"));
                collate.setTransid(rs.getString("TRANSID"));
                collate.setTransdate(rs.getString("Transdate"));
                collate.setMerchantid(rs.getString("MERCHANTID") == null ? "" : rs.getString("MERCHANTID"));
                collate.setBankTransid(rs.getString("Banktransid") == null ? "" : rs.getString("Banktransid"));
                collate.setResponsecode(rs.getString("RESPONSECODE") == null ? "" : rs.getString("RESPONSECODE"));
                collate.setCardnumber(rs.getString("CARDNUMBER") == null ? "" : rs.getString("CARDNUMBER"));
                collate.setTypedevice(rs.getString("TYPEDEVICE") == null ? "" : rs.getString("TYPEDEVICE"));
                collate.setPaymentTransid(rs.getString("PAYMENTTRANSID") == null ? "" : rs.getString("PAYMENTTRANSID"));
                collate.setTypeService(rs.getString("TYPESERVICE") == null ? "" : rs.getString("TYPESERVICE"));
                resultList.add(collate);
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String onl_receiveCollateData = "BEGIN proc_insert_OnlCollated(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    public void ONL_ReceiveCollateData(PayOnlineCollate collate) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(onl_receiveCollateData);
            calStmt.setString(1, collate.getPartnerID());
            calStmt.setString(2, collate.getResultCollate());
            calStmt.setString(3, collate.getTypeTrans());
            calStmt.setString(4, collate.getCCY());
            calStmt.setBigDecimal(5, collate.getAmount());
            calStmt.setString(6, collate.getTransid());
            calStmt.setString(7, collate.getTransdate());
            calStmt.setString(8, collate.getMerchantid());
            calStmt.setString(9, collate.getBankTransid());
            calStmt.setString(10, collate.getResponsecode());
            calStmt.setString(11, collate.getCardnumber());
            calStmt.setString(12, collate.getTypedevice());
            calStmt.setString(13, collate.getPaymentTransid());
            calStmt.setString(14, collate.getTypeService());
            calStmt.setString(15, collate.getMac());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    final String CHECK_CUS_INFO = "BEGIN PAYMENTBYCARD.checkcustinfo(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    public CustomerInfoRsDto checkCustInfo(OnlinePCustomerInfoDto customerInfo) throws SQLException {

        CustomerInfoRsDto resp = new CustomerInfoRsDto();

        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(CHECK_CUS_INFO);
            calStmt.setString(1, customerInfo.getProviderID());
            calStmt.setString(2, customerInfo.getProfileid());
            calStmt.setString(3, customerInfo.getCardName());
            calStmt.setString(4, customerInfo.getBirthDay());
            calStmt.setString(5, customerInfo.getMobileNo());
            calStmt.setString(6, customerInfo.getIdNumber());
            calStmt.setString(7, customerInfo.getCountry());

            calStmt.registerOutParameter(8, Types.VARCHAR);
            calStmt.registerOutParameter(9, Types.VARCHAR);
            calStmt.registerOutParameter(10, Types.VARCHAR);
            calStmt.registerOutParameter(11, Types.VARCHAR);
            calStmt.registerOutParameter(12, Types.VARCHAR);
            calStmt.registerOutParameter(13, Types.VARCHAR);

            calStmt.execute();

            resp.setRESPONSECODE(calStmt.getString(13));
            resp.setPROFILEID(customerInfo.getProfileid());
            resp.setPROVIDERID(customerInfo.getProviderID());
            resp.setISCARDNAME(calStmt.getString(8));
            resp.setISBIRTHDAY(calStmt.getString(9));
            resp.setISMOBILENO(calStmt.getString(10));
            resp.setISIDNUMBER(calStmt.getString(11));
            resp.setISCOUNTRY(calStmt.getString(12));

            return resp;
        } catch (SQLException ex) {
            LOGGER.error("checkCustInfo --> Ex: " + ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /*
     * Author: TAMNT12
     * Desc: Edit function queryTransaction - 14/07/2022
     */
    private static final String QUERY_ONL_PAYMENT_BYCARD = "SELECT P.AMOUNT, P.REFCORE, P.ID, P.STATUS, P.CCY FROM ONL_PAYMENT_BYCARD P WHERE TRANSID = ? AND P.PARTNERID = ?";
    private static final String QUERY_Onl_Takeoutwallet = "SELECT P.AMOUNT, P.REFCORE, P.ID FROM onl_takeoutwallet P WHERE TRANSID = ? AND P.PARTNERID = ?";
    private static final String QUERY_ONL_PAYMENT_BYCARD_REFUND = "SELECT P.AMOUNT, P.REFCORE, P.ID, P.STATUS FROM ONL_PAYMENT_BYCARD_REFUND P WHERE TRANSID = ? AND P.PARTNERID = ?";
    private static final String QUERY_RevertOnl_Takeoutwallet = "SELECT P.AMOUNT, P.REFCORE, P.ID FROM onl_takeoutwallet P WHERE p.reverttransid = ? AND P.PARTNERID = ? and p.isrevert = 1";

    public String[] queryONL_PAYMENT_BYCARD(String pRefTransid, String pParnerid) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String[] ArrayResult = new String[5];
        try {
            preStatement = connection.prepareStatement(QUERY_ONL_PAYMENT_BYCARD);
            preStatement.setString(1, pRefTransid);
            preStatement.setString(2, pParnerid);
            rs = preStatement.executeQuery();

            if (rs.next()) {
                ArrayResult[0] = rs.getString("AMOUNT");
                ArrayResult[1] = rs.getString("REFCORE");
                ArrayResult[2] = rs.getString("ID");
                ArrayResult[3] = rs.getString("STATUS");
                ArrayResult[4] = rs.getString("CCY");
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return ArrayResult;

    }

    public String[] queryOnl_Takeoutwallet(String pRefTransid, String pParnerid) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String[] ArrayResult = new String[3];
        try {
            preStatement = connection.prepareStatement(QUERY_Onl_Takeoutwallet);
            preStatement.setString(1, pRefTransid);
            preStatement.setString(2, pParnerid);
            rs = preStatement.executeQuery();
            if (rs.next()) {
                ArrayResult[0] = rs.getString("AMOUNT");
                ArrayResult[1] = rs.getString("REFCORE");
                ArrayResult[2] = rs.getString("ID");
                ArrayResult[3] = rs.getString("ID") == null ? "02" : "00";
                ArrayResult[4] = "VND";
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return ArrayResult;

    }

    public String[] queryONL_PAYMENT_BYCARD_REFUND(String pRefTransid, String pParnerid) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String[] ArrayResult = new String[3];
        try {
            preStatement = connection.prepareStatement(QUERY_ONL_PAYMENT_BYCARD_REFUND);
            preStatement.setString(1, pRefTransid);
            preStatement.setString(2, pParnerid);
            rs = preStatement.executeQuery();
            if (rs.next()) {
                ArrayResult[0] = rs.getString("AMOUNT");
                ArrayResult[1] = rs.getString("REFCORE");
                ArrayResult[2] = rs.getString("ID");
                ArrayResult[3] = rs.getString("STATUS");
                ArrayResult[4] = "VND";
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return ArrayResult;

    }

    public String[] queryRevertOnl_Takeoutwallet(String pRefTransid, String pParnerid) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String[] ArrayResult = new String[3];
        try {
            preStatement = connection.prepareStatement(QUERY_RevertOnl_Takeoutwallet);
            preStatement.setString(1, pRefTransid);
            preStatement.setString(2, pParnerid);
            rs = preStatement.executeQuery();
            if (rs.next()) {
                ArrayResult[0] = rs.getString("AMOUNT");
                ArrayResult[1] = rs.getString("REFCORE");
                ArrayResult[2] = rs.getString("ID");
                ArrayResult[3] = rs.getString("ID") == null ? "02" : "00";
                ArrayResult[4] = "VND";
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return ArrayResult;

    }

    final String onl_answerCollateVNPT = "BEGIN VNPTCOLLATED.AnswerCollateData(?,?,?); END;";

    /**
     *
     * @param pPartnerID
     * @param pTransdate
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public List<VNPTMoneyCollate> AnswerCollateDataVNPT(String pPartnerID, String pTransdate) throws SQLException, Exception {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            List<VNPTMoneyCollate> resultList = new ArrayList<VNPTMoneyCollate>();
            calStmt = connection.prepareCall(onl_answerCollateVNPT);
            calStmt.setString(1, pPartnerID);
            calStmt.setString(2, pTransdate);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.executeQuery();
            rs = (ResultSet) calStmt.getObject(3);
            if (rs == null) {
                LOGGER.warn("Not found");
                throw new Exception("Not found");
            }
            while (rs.next()) {
                VNPTMoneyCollate collate = new VNPTMoneyCollate();
                collate.setPartnerID(rs.getString("PARTNERID"));
                collate.setResultCollate(rs.getString("RESULTCOLLATE"));
                collate.setResultConfirm(rs.getString("RESULTCONFIRM"));
                collate.setTypeTrans(rs.getString("TYPETRANS"));
                collate.setCCY(rs.getString("CCY"));
                collate.setAmount(rs.getBigDecimal("Amount"));
                collate.setTransid(rs.getString("TRANSID"));
                collate.setTransdate(rs.getString("Transdate"));
                collate.setMerchantid(rs.getString("MERCHANTID") == null ? "" : rs.getString("MERCHANTID"));
                collate.setBankTransid(rs.getString("Banktransid") == null ? "" : rs.getString("Banktransid"));
                collate.setResponsecode(rs.getString("RESPONSECODE") == null ? "" : rs.getString("RESPONSECODE"));
                collate.setCardnumber(rs.getString("CARDNUMBER") == null ? "" : rs.getString("CARDNUMBER"));
                collate.setIdServiceType(rs.getString("TYPESERVICE") == null ? "" : rs.getString("TYPESERVICE"));
                resultList.add(collate);
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String onl_receiveCollateDataVNPT = "BEGIN VNPTCOLLATED.ReceiveCollateData(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    /**
     *
     * @param collate
     * @throws SQLException
     */
    public void ReceiveCollateVNPT(VNPTMoneyCollate collate) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(onl_receiveCollateDataVNPT);
            calStmt.setString(1, collate.getPartnerID());
            calStmt.setString(2, collate.getPartnerCollated());
            calStmt.setString(3, collate.getTypeTrans());
            calStmt.setString(4, collate.getCCY());
            calStmt.setBigDecimal(5, collate.getAmount());
            calStmt.setString(6, collate.getTransid());
            calStmt.setString(7, collate.getTransdate());
            calStmt.setString(8, collate.getMerchantid());
            calStmt.setString(9, collate.getBankTransid());
            calStmt.setString(10, collate.getResponsecode());
            calStmt.setString(11, collate.getCardnumber());
            calStmt.setString(12, collate.getAddInfo());
            calStmt.setString(13, collate.getIdServiceType());
            calStmt.setString(14, collate.getFileName());
            calStmt.setString(15, collate.getMac());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static final String INSERTMOKHOATHE = "INSERT INTO SMS_SCB.KHOATHE (PARTNERID, SEQUENCENO, CMND, PHONE, LAST4DIGITS, CARDTYPE, OPENALL, CHANNEL, CREATEDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

    /**
     * INSERTMOTHE
     *
     * @param req
     * @return
     * @throws SQLException
     */
    public boolean INSERTMOKHOATHE(MoKhoaTheReq req) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(true);
        try {
            String last4Digits = req.getLast4Digits() == null ? "" : req.getLast4Digits();
            String cardType = req.getCardType() == null ? "" : req.getCardType();
            preStatement = connection.prepareStatement(INSERTMOKHOATHE);
            preStatement.setString(1, req.getPartner());
            preStatement.setString(2, req.getSequence());
            preStatement.setString(3, req.getCmnd());
            preStatement.setString(4, req.getPhone());
            preStatement.setString(5, last4Digits);
            preStatement.setString(6, cardType);
            preStatement.setString(7, req.getOpenAll());
            preStatement.setString(8, req.getChannel());
            return preStatement.executeUpdate() > 0;
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("INSERTMOTHE --> ex = [" + e + "]");
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static final String INSERTMOTHEDETAILS = "INSERT INTO SMS_SCB.KHOATHEDETAILS (PARTNERID, SEQUENCENO, REFNO, FI, PAN, ACTIND, ACTTYPE, ACTCDE, CREATEDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
     /**
     *INSERTMOTHEDETAILS
     * 
     * 
     * @param req
     * @param card
     * @return
     * @throws SQLException
     */
    public String INSERTMOTHEDETAILS(MoKhoaTheReq req, CardInfo card) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(true);
        try {
            String refCode = "";
            preStatement = connection.prepareStatement(GETSEQLOCKTHE);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                refCode = rs.getString("REFNO");
                break;
            }
            if (!refCode.isEmpty()) {
                String fi = "970429";
                String pan = card.getLoc() + card.getLast4Digits();
                String actind = "3";
                String acttype = "CD";
                String actcde = "U";
                preStatement = connection.prepareStatement(INSERTMOTHEDETAILS);
                preStatement.setString(1, req.getPartner());
                preStatement.setString(2, req.getSequence());
                preStatement.setString(3, refCode);
                preStatement.setString(4, fi);
                preStatement.setString(5, pan);
                preStatement.setString(6, actind);
                preStatement.setString(7, acttype);
                preStatement.setString(8, actcde);
                if (preStatement.executeUpdate() > 0) {
                    return refCode;
                }
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("INSERTMOTHEDETAILS --> ex = [" + e + "]");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return "";
    }

     private static final String UPDATEMOTHEDETAILS = "UPDATE SMS_SCB.KHOATHEDETAILS SET RESPCODE = ?, RESPDESC = ? WHERE SEQUENCENO = ? AND PARTNERID = ? AND REFNO = ?";

    /**
     *
     * @param refNo
     * @param req
     * @param respCode
     * @param respDesc
     * @return
     * @throws SQLException
     */
    public boolean UPDATEMOTHEDETAILS(String refNo, MoKhoaTheReq req, String respCode, String respDesc) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        connection.setAutoCommit(true);
        try {
            preStatement = connection.prepareStatement(UPDATEMOTHEDETAILS);
            preStatement.setString(1, respCode);
            preStatement.setString(2, respDesc);
            preStatement.setString(3, req.getSequence());
            preStatement.setString(4, req.getPartner());
            preStatement.setString(5, refNo);
            return (preStatement.executeUpdate() > 0);
        } catch (SQLException | NullPointerException e) {
            LOGGER.error("UPDATEMOTHEDETAILS8149 --> ex = [" + e + "]");
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    private static final String GET_ACCOUNT_BALANCE_WU = "BEGIN PKG_CHECK_ACCOUNT_WU.checkCustAccount(?,?,?,?); END;";
    public Object[] getAccountBalance_wu(String accountNumber) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(GET_ACCOUNT_BALANCE_WU);
            calStmt.setString(1, accountNumber);
            calStmt.registerOutParameter(2, OracleTypes.NUMBER); // available balance
            calStmt.registerOutParameter(3, OracleTypes.NUMBER); // current balance
            calStmt.registerOutParameter(4, OracleTypes.VARCHAR); // status
            calStmt.execute();
            Object[] ArrayResult = new Object[3];
            ArrayResult[0] = BigDecimal.valueOf(calStmt.getDouble(2));
            ArrayResult[1] = BigDecimal.valueOf(calStmt.getDouble(3));
            ArrayResult[2] = calStmt.getString(4);
            return ArrayResult;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
}
