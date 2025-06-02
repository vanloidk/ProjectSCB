/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.SqlCommand;
import scb.com.vn.common.model.cims.kht.KichHoatTheInfo;
import scb.com.vn.common.model.cims.kt.KhoaTheDetailsInfo;
import scb.com.vn.common.model.cims.kt.KhoaTheInfo;
import scb.com.vn.common.model.cims.kt.KhoaTheReq;
import scb.com.vn.common.model.cims.kt.MoKhoaTheReq;
import scb.com.vn.common.model.cw.CardAdjustmentReq;
import scb.com.vn.common.model.cw.CardAdjustmentRes;
import scb.com.vn.common.model.cw.CardInfo;
import scb.com.vn.common.model.cw.DirectDebitRes;
import scb.com.vn.common.model.masterpass.MCPaymentRp;
import scb.com.vn.common.model.masterpass.MasterCardQrActionEnum;
import scb.com.vn.common.model.masterpass.PayByQRCodeRq;
import scb.com.vn.common.model.mvisa.MVISAQRRQ;
import scb.com.vn.common.model.mvisa.ResponseMessage;
import scb.com.vn.common.model.sms.SmsDetail;
import scb.com.vn.common.model.sms.SmsInfo;
import scb.com.vn.common.model.vnpayqr.CheckQRRp;
import scb.com.vn.common.model.vnpayqr.CheckQRRq;
import scb.com.vn.common.model.vnpayqr.PaymentQRJson;
import scb.com.vn.common.model.vnpayqr.PaymentQRRp;
import scb.com.vn.common.model.vnpayqr.PaymentQRRq;
import scb.com.vn.common.model.vnpayqr.RefundQRJson;
import scb.com.vn.common.model.vnpayqr.RefundQRRp;
import scb.com.vn.common.odbx.SCBBranch;
import scb.com.vn.dbi.dao.OnlinePaymentDAO;
import scb.com.vn.dbi.dto.CustomerInfoRsDto;
import scb.com.vn.dbi.dto.DawacoCollate;
import scb.com.vn.dbi.dto.EBANKUSER;
import scb.com.vn.dbi.dto.EVNHCM;
import scb.com.vn.dbi.dto.GwEmailTd;
import scb.com.vn.dbi.dto.MasterSenderInfor;
import scb.com.vn.dbi.dto.OnlPaymentByCardDto;
import scb.com.vn.dbi.dto.OnlinePCustomerInfoDto;
import scb.com.vn.dbi.dto.OrgBillPaid;
import scb.com.vn.dbi.dto.PayOnlineCollate;
import scb.com.vn.dbi.dto.Pbl_billpaidCollate;
import scb.com.vn.dbi.dto.Pbl_partnercode;
import scb.com.vn.dbi.dto.SMLCollate;
import scb.com.vn.dbi.dto.SmsThuphi;
import scb.com.vn.dbi.dto.VNPTMoneyCollate;
import scb.com.vn.dbi.dto.VwSmsThuphi;
import scb.com.vn.dbi.utility.HibernateUtil;
import scb.com.vn.ultility.DBActionEnum;

/**
 *
 * @author LyDTY
 */
public class OnlinePaymentBO extends BaseSMSSCBBO {

    private static final Logger LOGGER = Logger.getLogger(OnlinePaymentBO.class);

    private Connection conn;
    private OnlinePaymentDAO dao = null;

    /**
     * Create a new instance of OnlinePaymentBO
     *
     * @throws java.lang.Exception
     */
    public OnlinePaymentBO() throws Exception {
        dao = new OnlinePaymentDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactorySmsScb());
    }

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
     * @throws Exception
     */
    public String checkCustomerInfo(String PartnerID, String TransID,
            String CardNumber, String CardHolderName,
            String CardDate, String MerchantId,
            Double Amount, String CCY, String Language,
            String ClientID, String LocalDate, String AddInfo,
            String ChannelID, String URLAuthen, String pIDVerify) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkCustomerInfo(PartnerID, TransID, CardNumber, CardHolderName, CardDate, MerchantId, Amount, CCY, Language, ClientID, LocalDate, AddInfo, ChannelID, URLAuthen, pIDVerify);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pID
     * @param pRefID
     * @throws Exception
     */
    public void TransferOnlinePayment(int pID, String pRefID
    ) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.TransferOnlinePayment(pID, pRefID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pID
     * @param pURLREDIREC
     * @param pStatus
     * @throws Exception
     */
    public void CommitTransfer(int pID, String pURLREDIREC, String pStatus
    ) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.CommitTransfer(pID, pURLREDIREC, pStatus);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws Exception
     */
    public String[] CheckRefundTransfer(String pPartnerID, String pMerchanID,
            String pTransID, String pRefundTransID,
            BigDecimal pRefundAmount, String pCCY, String pADDINFO, String pLocalDatetime) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.CheckRefundTransfer(pPartnerID, pMerchanID, pTransID, pRefundTransID, pRefundAmount, pCCY, pADDINFO, pLocalDatetime);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param ID
     * @param pCoreref
     * @param pStatus
     * @throws Exception
     */
    public void UpdateRefundTransfer(String ID, String pCoreref, String pStatus) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UpdateRefundTransfer(ID, pCoreref, pStatus);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws Exception
     */
    public String[] QuerryTransfer(String PartnerID, String TransID, String MerchantID,
            Double Amount, String CCY, String QTransID
    ) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.QuerryTransfer(PartnerID, TransID, MerchantID, Amount, CCY, QTransID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getPaymentOnlineInfo(pEbankUserID, pIDVerify);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pID
     * @param pPhoneNumber
     * @param pOTP
     * @param pIDAddress
     * @throws Exception
     */
    public void insertOTPSMS(int pID, String pPhoneNumber, String pOTP, String pIDAddress)
            throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertOTPSMS(pID, pPhoneNumber, pOTP, pIDAddress);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pID
     * @param pTimeOut
     * @return
     * @throws Exception
     */
    public String[] getOTPSMS(int pID, String pTimeOut)
            throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getOTPSMS(pID, pTimeOut);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pID
     * @param pUserID
     * @param pClienID
     * @return
     * @throws Exception
     */
    public int checkSessionForTransfer(String pID, String pUserID, String pClienID) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkSessionForTransfer(pID, pUserID, pClienID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param collate
     * @throws SQLException
     * @throws RemoteException
     */
    public void ReceiveCollateData(PayOnlineCollate collate) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.ReceiveCollateData(collate);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pPartnerID
     * @param pTransdate
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public List<PayOnlineCollate> AnswerCollateData(String pPartnerID, String pTransdate) throws SQLException, Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.AnswerCollateData(pPartnerID, pTransdate);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataByVerifyID(IDverify);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pPartnerID
     * @param pTransdate
     * @return
     * @throws Exception
     */
    public int isCollate(String pPartnerID, String pTransdate) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.isCollate(pPartnerID, pTransdate);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pVerifyID
     * @param pTypeFailed
     * @return
     * @throws Exception
     */
    public int checkFailed(String pVerifyID, String pTypeFailed) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkFailed(pVerifyID, pTypeFailed);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pVerifyID
     * @param pTypeFailed
     * @throws Exception
     */
    public void insertFailed(String pVerifyID, String pTypeFailed) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertFailed(pVerifyID, pTypeFailed);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pTransID
     * @param pPartnerID
     * @return
     * @throws Exception
     */
    public int CheckBeforeTransfer(String pTransID, String pPartnerID) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.CheckBeforeTransfer(pTransID, pPartnerID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] SMLCheckData(String PartnerID, String TransID,
            String CardNumber,
            String CardName,
            String MerchantId,
            Double Amount, String CCY,
            String LocalDate,
            String AddInfo,
            String ChannelID,
            String URLAuthen,
            String pIDVerify) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.SMLCheckData(PartnerID, TransID,
                    CardNumber,
                    CardName,
                    MerchantId,
                    Amount, CCY,
                    LocalDate,
                    AddInfo,
                    ChannelID,
                    URLAuthen,
                    pIDVerify);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pPartnerID
     * @param pCollatedDate
     * @throws SQLException
     * @throws RemoteException
     */
    public void InsertCollateddate(String pPartnerID, Date pCollatedDate) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.InsertCollateddate(pPartnerID, pCollatedDate);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pPartnerID
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public Date getMaxCollateDate(String pPartnerID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getMaxCollateDate(pPartnerID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param sml
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public int InsertSMLEcomCollated(SMLCollate sml) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.InsertSMLEComCollated(sml);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws SQLException
     * @throws RemoteException
     */
    public List<SMLCollate> getOutSMLEcomCollate() throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getOutSMLEcomCollate();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pUserID
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public int checkTokenByUserID(String pUserID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkTokenByUserID(pUserID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] VERIFICARD(String PTRANSID,
            String PTRANSDATE,
            String PCARDNUMBER,
            String PCARDNAME,
            String POTPSMS,
            String PPARTNERID,
            String pDescription,
            String pTypeCard) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.VERIFICARD(PTRANSID, PTRANSDATE, PCARDNUMBER, PCARDNAME, POTPSMS, PPARTNERID, pDescription, pTypeCard);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] VERIFYOTPSMS(String PTRANSID,
            String PTRANSDATE,
            String PVERIFYTYPE,
            String PREFTRANSID,
            String POTPSMS,
            String PPARTNERID
    ) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.VERIFYOTPSMS(PTRANSID, PTRANSDATE, PVERIFYTYPE, PREFTRANSID, POTPSMS, PPARTNERID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
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
            String pChannelID) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.PAYMENTWITHPROFILEID(PTRANSID, PPROFILEID, PAMOUNT, PCCY, PTRANSDATE, PPARTNERID, PDESCRIPTION, PMERCHANTID, pChannelID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] PAYMENTWITHACCOUNT(String ITRANSID, String ICUSTACCCOUNT,
            Double IAMOUNT, String ICCY, String ITRANSDATE, String IPARTNERID,
            String IDESCRIPTION, String IMERCHANTID) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.PAYMENTWITHACCOUNT(ITRANSID, ICUSTACCCOUNT, IAMOUNT, ICCY, ITRANSDATE, IPARTNERID, IDESCRIPTION, IMERCHANTID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
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
    ) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.PAYMENT(PTRANSID, pCardNumber, pCardHolderName, pCardDate, PAMOUNT, PCCY, PTRANSDATE, PPARTNERID, PDESCRIPTION, PMERCHANTID, pChannelID, OTP);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pBankid
     * @param pStatus
     * @param pRefcore
     * @throws RemoteException
     * @throws SQLException
     */
    public void UpdatePayment(String pBankid,
            String pStatus,
            String pRefcore) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UpdatePayment(pBankid, pStatus, pRefcore);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pDestNumber
     * @param Ptypedestnumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] checkDestNumber(String pDestNumber,
            String Ptypedestnumber) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkDestNumber(pDestNumber, Ptypedestnumber);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
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
            String Ptypedestnumber) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.TAKEOUTWALLET(PTRANSID, PDESTNUMBER, pSOURCEACCOUNT, PAMOUNT, PCCY, PTRANSDATE, PDESRCRIPTION, PPARTNERID, Ptypedestnumber);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param PBANKID
     * @param PREFCORE
     * @throws RemoteException
     * @throws SQLException
     */
    public void UPDATE_TAKEOUTWALLET(String PBANKID,
            String PREFCORE) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UPDATE_TAKEOUTWALLET(PBANKID, PREFCORE);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PPARTNERID
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] REVERT_TAKEOUTWALLET(String PTRANSID,
            String PPARTNERID) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.REVERT_TAKEOUTWALLET(PTRANSID, PPARTNERID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public void UPDATE_REVERT_TAKEOUTWALLET(String PTRANSID,
            String PREVERTTRANSID,
            String PPARTNERID,
            String PTRANSDATE,
            String PDESC) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UPDATE_REVERT_TAKEOUTWALLET(PTRANSID, PREVERTTRANSID, PPARTNERID, PTRANSDATE, PDESC);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] REFUND(String pTransid,
            String pRefTransid,
            Double pAmount,
            String pTransdate,
            String pDescription,
            String pPartnerid) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.REFUND(pTransid, pRefTransid, pAmount, pTransdate, pDescription, pPartnerid);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param PID
     * @param PREFCORE
     * @throws RemoteException
     * @throws SQLException
     */
    public void UPDATE_REFUND(String PID, String PREFCORE) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UPDATE_REFUND(PID, PREFCORE);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     */
    public String[] queryTransaction(
            String pRefTransid,
            String pTRANSTYPE,
            String pParnerid) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            switch (pTRANSTYPE) {
                case "01":
                    return dao.queryONL_PAYMENT_BYCARD(pRefTransid, pParnerid);

                case "02":
                    return dao.queryOnl_Takeoutwallet(pRefTransid, pParnerid);
                case "03":
                    return dao.queryONL_PAYMENT_BYCARD_REFUND(pRefTransid, pParnerid);
                case "04":
                    return dao.queryRevertOnl_Takeoutwallet(pRefTransid, pParnerid);
                default:
                    break;
            }
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     */
    public String[] DestroyConnectCard(String PTRANSID,
            String PTRANSDATE,
            String pProfileID,
            String pDescription,
            String pPartnerid,
            String pOTP) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.DestroyConnectCard(PTRANSID, PTRANSDATE, pProfileID, pDescription, pPartnerid, pOTP);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pPartnerid
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] GetMACkeys(String pPartnerid) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.GetMACkeys(pPartnerid);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pTransID
     * @param pParnerID
     * @throws SQLException
     * @throws RemoteException
     */
    public void PaymentAndRegister(String pTransID, String pParnerID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.PaymentAndRegister(pTransID, pParnerID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pCardNumber
     * @param pParnerid
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String getProfileID(
            String pCardNumber,
            String pParnerid) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getProfileID(pCardNumber, pParnerid);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] QUERY_PERSONAL_INFORMATION(String maKH, String cardNo, String cardAccountNo, String accountNo) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.QUERY_PERSONAL_INFORMATION(maKH, cardNo, cardAccountNo, accountNo);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param IACCOUNT
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] CHECK_ACCOUNT_BALANCE(String IACCOUNT) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.CHECK_ACCOUNT_BALANCE(IACCOUNT);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] REFUND_PAYMENT_WITH_ACCOUNT(String pTransid, String pRefTransid,
            Double pAmount, String pTransdate, String pDescription,
            String pPartnerid) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.REFUND_PAYMENT_WITH_ACCOUNT(pTransid, pRefTransid, pAmount, pTransdate, pDescription, pPartnerid);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param accountNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public Object[] getAccountBalance(String accountNumber) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getAccountBalance(accountNumber);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] OTP_REQUEST(String TransID, String PartnerID, String PhoneNumber,
            String ChannelID, String TransType, String OTP) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.OTP_REQUEST(TransID, PartnerID, PhoneNumber, ChannelID, TransType, OTP);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] OTP_RESPONSE(String TransID, String RefTransID, String PartnerID,
            String ChannelID, String TransType, String OTP, String TransDate) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.OTP_RESPONSE(TransID, RefTransID, PartnerID, ChannelID, TransType, OTP, TransDate);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* ONEPAY */
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] ONEPAY_CARD_VERIFICATION(String ITRANSID, String IPARTNERID,
            String ICARDNUMBER, String ICARDNAME, String ICARDDATE, String ITRANSDATE,
            String IDESCRIPTION) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.ONEPAY_CARD_VERIFICATION(ITRANSID, IPARTNERID, ICARDNUMBER, ICARDNAME, ICARDDATE, ITRANSDATE, IDESCRIPTION);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] ONEPAY_OTP_VERIFICATION(String ITRANSID, String IREFTRANSID,
            String IPARTNERID, String ICHANNELID, String IVERIFYTYPE, String IOTP,
            String ITRANSDATE, String IDESCRIPTION) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.ONEPAY_OTP_VERIFICATION(ITRANSID, IREFTRANSID, IPARTNERID, ICHANNELID,
                    IVERIFYTYPE, IOTP, ITRANSDATE, IDESCRIPTION);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] ONEPAY_PAYMENT(String ITRANSID, String IREFTRANSID,
            String IPARTNERID, String IMERCHANTID, String ICARDNUMBER, String ICARDNAME,
            String ICARDDATE, Double IAMOUNT, String ICCY, String ITRANSDATE,
            String ICHANNELID, String IDESCRIPTION) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.ONEPAY_PAYMENT(ITRANSID, IREFTRANSID, IPARTNERID, IMERCHANTID, ICARDNUMBER,
                    ICARDNAME, ICARDDATE, IAMOUNT, ICCY, ITRANSDATE, ICHANNELID, IDESCRIPTION);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] ONEPAY_PAYMENT_WITHOUT_OTP(String ITRANSID, String IREFTRANSID,
            String IPARTNERID, String IMERCHANTID, String ICARDNUMBER, String ICARDNAME,
            String ICARDDATE, Double IAMOUNT, String ICCY, String ITRANSDATE,
            String ICHANNELID, String IDESCRIPTION) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.ONEPAY_PAYMENT_WITHOUT_OTP(ITRANSID, IREFTRANSID, IPARTNERID, IMERCHANTID, ICARDNUMBER,
                    ICARDNAME, ICARDDATE, IAMOUNT, ICCY, ITRANSDATE, ICHANNELID, IDESCRIPTION);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public void PAYMENT_OTP_ADDING(String RefTransID, String PartnerID, String OTP,
            String PhoneNumber, String ChannelID, String TransType) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.PAYMENT_OTP_ADDING(RefTransID, PartnerID, OTP, PhoneNumber, ChannelID, TransType);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* ONEPAY */

 /* DAWACO */
    /**
     *
     * @param date
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<DawacoCollate> getOutDAWACOCollate(String date) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getOutDAWACOCollate(date);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* DAWACO */

 /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    /**
     *
     * @param userid
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public EBANKUSER getInfoEbankUser(String userid) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getInfoEbankUser(userid);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pAccountID
     * @param pFullName
     * @param pPartnerID
     * @param Addinfo
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] RegisterProfileidWithAccount(String pAccountID,
            String pFullName,
            String pPartnerID, String Addinfo) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.RegisterProfileidWithAccount(pAccountID, pFullName, pPartnerID, Addinfo);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param PSCBID
     * @param pTRANSID
     * @param pPROFILEID
     * @param TransDate
     * @param partnerid
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String UpdateRegisterProfileidWithACC(String PSCBID,
            String pTRANSID,
            String pPROFILEID,
            String TransDate, String partnerid) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.UpdateRegisterProfileidWithACC(PSCBID, pTRANSID, pPROFILEID, TransDate, partnerid);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* MASTERPASS */
    /**
     *
     * @param sequenceNo
     * @param responseCode
     * @param responseDescription
     * @param approvalCode
     * @param typeTrans
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public int INSERTCWDIRECTDEBIT(String sequenceNo, String responseCode, String responseDescription, String approvalCode, String typeTrans) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTCWDIRECTDEBIT(sequenceNo, responseCode, responseDescription, approvalCode, typeTrans);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int MASTERPASSQR(PayByQRCodeRq req, MasterCardQrActionEnum action) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.MASTERPASSQR(req, action);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public int INSERTCWCARDADJUSTMENT(String sequenceNo, String referenceNo, String responseCode, String responseDescription, String typeTrans, String loc4Digits, String merPan, String refCore, String cardType) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTCWCARDADJUSTMENT(sequenceNo, referenceNo, responseCode, responseDescription, typeTrans, loc4Digits, merPan, refCore, cardType);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param sequenceNo
     * @param response
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public int INSERTMASTERCARDRES(String sequenceNo, MCPaymentRp response) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTMASTERCARDRES(sequenceNo, response);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param req
     * @param senderInfor
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String INSERTMASTERPASSQR(PayByQRCodeRq req, MasterSenderInfor senderInfor) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTMASTERPASSQR(req, senderInfor);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String INSERTMASTERPASSQR(PayByQRCodeRq req) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTMASTERPASSQR(req);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String INSERTVISAQR(MVISAQRRQ req, MasterSenderInfor senderInfor) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTVISAQR(req, senderInfor);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String INSERTVISAQR(MVISAQRRQ req) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTVISAQR(req);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param sequenceNo
     * @param response
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public int INSERTVISAQRRES(String sequenceNo, ResponseMessage response) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTVISAQRRES(sequenceNo, response);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* VISA */
 /*
    LYDTY; 03/2018
     */
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
     * @throws RemoteException
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
    ) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.PAYMENTWITHPROFILEIDBYVERIFY(PTRANSID, pProfileID, PAMOUNT, PCCY, PTRANSDATE, PPARTNERID, PDESCRIPTION, PMERCHANTID, pChannelID, OTP);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* EVNHCM */
    /**
     *
     * @param partnerID
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<Pbl_billpaidCollate> getOutPbl_billpaidCollate(String partnerID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getOutPbl_billpaidCollate(partnerID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param partnerID
     * @param comeback
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<Pbl_billpaidCollate> getOutPbl_billpaidCollate(String partnerID, int comeback) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getOutPbl_billpaidCollate(partnerID, comeback);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param datas
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<EVNHCM> queryttttEVNHCM(List<Pbl_billpaidCollate> datas) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.queryttttEVNHCM(datas);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* EVNHCM */
    /**
     *
     * @param pProfileID
     * @param PAMOUNT
     * @param PPARTNERID
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] checkProfileIDForPayment(
            String pProfileID,
            Double PAMOUNT,
            String PPARTNERID
    ) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkProfileIDForPayment(pProfileID, PAMOUNT, PPARTNERID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* TIMER_SMS */
    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean checkSCBCustomer(String phoneNumber) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkSCBCustomer(phoneNumber);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param phoneNumber
     * @param splitMessage
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] queryBalance(String phoneNumber, String[] splitMessage) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.queryBalance(phoneNumber, splitMessage);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param acccount
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountCASA(String acccount) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getAccountCASA(acccount);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String getAccDefaultFromMobile(String phoneNumber) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getAccDefaultFromMobile(phoneNumber);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param phoneNumber
     * @param defaultAcc
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean updateOperativeAccount(String phoneNumber, String defaultAcc) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.updateOperativeAccount(phoneNumber, defaultAcc);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* TIMER_SMS */
 /* VNPAY QR */
    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String INSERTCHECKQR(CheckQRRq req) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTCHECKQR(req);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param res
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean UPDATECHECKQR(CheckQRRp res) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.UPDATECHECKQR(res);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public PaymentQRJson INSERTPAYMENTQR(PaymentQRRq req) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTPAYMENTQR(req);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param qrPayment
     * @param res
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean UPDATEPAYMENTQR(PaymentQRJson qrPayment, PaymentQRRp res) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.UPDATEPAYMENTQR(qrPayment, res);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param qrPayment
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public RefundQRJson INSERTREFUNDQR(PaymentQRJson qrPayment) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTREFUNDQR(qrPayment);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param qrRefund
     * @param res
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean UPDATEREFUNDQR(RefundQRJson qrRefund, RefundQRRp res) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.UPDATEREFUNDQR(qrRefund, res);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* TIMER SMS */
    /**
     *
     * @param acccount
     * @param rownum
     * @return
     * @throws Exception
     */
    public ArrayList<?> getDetailsAccountCASA(String acccount, int rownum) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            ArrayList<?> result = dao.getDetailsAccountCASADaily(acccount, rownum);
            return result;
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param acccountTD
     * @param branchAcc
     * @return
     * @throws Exception
     */
    public ArrayList<?> getAccountTD(String acccountTD, String branchAcc) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getAccountTD(acccountTD, branchAcc);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    // CHECK KYC
    /**
     *
     * @param PPARTNERID
     * @param pProfileID
     * @param pCMND
     * @param pMobile
     * @param pAddInfo
     * @return
     * @throws Exception
     */
    public String checkKYC(
            String PPARTNERID,
            String pProfileID,
            String pCMND,
            String pMobile,
            String pAddInfo) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkKYC(PPARTNERID, pProfileID, pCMND, pMobile, pAddInfo);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean checkPhoneNumberAtGW(String phoneNumber) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkPhoneNumberAtGW(phoneNumber);
        } catch (SQLException | NullPointerException ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param card
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String INSERTLOCKTHE8149(CardInfo card) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTLOCKTHE8149(card);
        } catch (SQLException | NullPointerException ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
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
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.UPDATELOCKTHE8149(sequence, respCode, respDesc);
        } catch (SQLException | NullPointerException ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean INSERTKHOATHE(KhoaTheReq req) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTKHOATHE(req);
        } catch (SQLException | NullPointerException ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param req
     * @param card
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String INSERTKHOATHEDETAILS(KhoaTheReq req, CardInfo card) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTKHOATHEDETAILS(req, card);
        } catch (SQLException | NullPointerException ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

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
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.UPDATEKHOATHEDETAILS(refNo, req, respCode, respDesc);
        } catch (SQLException | NullPointerException ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] MMSTRANSFERACCTOACC(String ITRANSID, String ISOURCEACCOUNT, String IDESTACCCOUNT,
            Double IAMOUNT, String ICCY, String ITRANSDATE, String IPARTNERID,
            String IDESCRIPTION) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.MMSTRANSFERACCTOACC(ITRANSID, ISOURCEACCOUNT, IDESTACCCOUNT, IAMOUNT, ICCY,
                    ITRANSDATE, IPARTNERID, IDESCRIPTION);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param IBANKID
     * @param IREFCORE
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean UPDATEMMSTRANSFERACCTOACC(String IBANKID, String IREFCORE) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.UPDATEMMSTRANSFERACCTOACC(IBANKID, IREFCORE);
        } catch (SQLException | NullPointerException ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] REFUND_FOR_TAKEOUT(String pTransid,
            String pRefTransid,
            Double pAmount,
            String pTransdate,
            String pDescription,
            String pPartnerid) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.REFUND_FOR_TAKEOUT(pTransid, pRefTransid, pAmount, pTransdate, pDescription, pPartnerid);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idServiceCode
     * @param idProviderCode
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String getProviderOriginalCode(String idServiceCode, String idProviderCode) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getProviderOriginalCode(idServiceCode, idProviderCode);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idServiceCode
     * @param idProviderCode
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String getPartnerCode(String idServiceCode, String idProviderCode) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getPartnerCode(idServiceCode, idProviderCode);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param userid
     * @param usertype
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public EBANKUSER getInfoEbankUser(String userid, String usertype) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getInfoEbankUser(userid, usertype);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
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
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.KICHHOATTHE(info);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean CIMS_RESEND(SmsDetail info, int action) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.CIMS_RESEND(info, action);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<SmsDetail> QUERY_SMS_TO_RESEND(String phone, String fromDate, String toDate) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.QUERY_SMS_TO_RESEND(phone, fromDate, toDate);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public SmsDetail QUERY_SMS_TO_RESEND_BY_ID(String id) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.QUERY_SMS_TO_RESEND_BY_ID(id);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param khoaTheInfo
     * @return
     * @throws Exception
     */
    public boolean INSERTKHOATHE(KhoaTheInfo khoaTheInfo) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTKHOATHE(khoaTheInfo);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param khoaTheDetailsInfo
     * @return
     * @throws Exception
     */
    public String INSERTKHOATHEDETAIL(KhoaTheDetailsInfo khoaTheDetailsInfo) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTKHOATHEDETAIL(khoaTheDetailsInfo);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param khoaTheInfo
     * @return
     * @throws Exception
     */
    public boolean UPDATEKHOATHE(KhoaTheInfo khoaTheInfo) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.UPDATEKHOATHE(khoaTheInfo);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param khoaTheDetailsInfo
     * @return
     * @throws Exception
     */
    public boolean UPDATEKHOATHEDETAIL(KhoaTheDetailsInfo khoaTheDetailsInfo) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.UPDATEKHOATHEDETAIL(khoaTheDetailsInfo);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param branch
     * @param number
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<VwSmsThuphi> getThuPhiKhcnTheoBranch(String branch, int number) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getThuPhiKhcnTheoBranch(branch, number);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<VwSmsThuphi> getThuPhiKhcn(String branch, int number) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getThuPhiKhcn(branch, number);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param branch
     * @param number
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public int isNoPhi(String custNo) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.isNoPhi(custNo);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws RemoteException
     * @throws SQLException
     */
    public List<VwSmsThuphi> getThuPhiKhdn() throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getThuPhiKhdn();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param idPartner
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<Pbl_partnercode> getPblPartnerCode(String idPartner) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getPblPartnerCode(idPartner);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param cif
     * @param number
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<VwSmsThuphi> getUnlockPhi(String cif, int number) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getUnlockPhi(cif, number);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public GwEmailTd getSTKNeedToResend(String id) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getSTKNeedToResend(id);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param value
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean insertOrgBillPaid(OrgBillPaid value) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.insertOrgBillPaid(value);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param req
     * @param res
     * @param action
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public CardAdjustmentRes cardAdjustment(CardAdjustmentReq req, CardAdjustmentRes res, DBActionEnum action) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.cardAdjustment(req, res, action);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public SCBBranch getSCBBranch() throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getSCBBranch();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<PayOnlineCollate> ONL_AnswerCollateData(String pPartnerID, String pTransdate) throws SQLException, Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.ONL_AnswerCollateData(pPartnerID, pTransdate);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void ONL_ReceiveCollateData(PayOnlineCollate collate) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.ONL_ReceiveCollateData(collate);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int VISAQR(MVISAQRRQ req, MasterCardQrActionEnum action) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.VISAQR(req, action);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int smsThuPhiDetails(SmsThuphi pb, SqlCommand action) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.smsThuPhiDetails(pb, action);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public CustomerInfoRsDto checkCustInfo(OnlinePCustomerInfoDto cusDto) throws Exception {
        try {
            //String input 
            conn = super.getConnection();
            dao.setConnection(conn);

            return dao.checkCustInfo(cusDto);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<VNPTMoneyCollate> AnswerCollateDataVNPT(String pPartnerID, String pTransdate) throws SQLException, Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.AnswerCollateDataVNPT(pPartnerID, pTransdate);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void ReceiveCollateDataVNPT(VNPTMoneyCollate collate) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.ReceiveCollateVNPT(collate);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * INSERTMOTHE
     *
     * @param req
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean INSERTMOKHOATHE(MoKhoaTheReq req) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTMOKHOATHE(req);
        } catch (SQLException | NullPointerException ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    /**
     * INSERTMOTHEDETAILS
     *
     * @param req
     * @param card
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String INSERTMOKHOATHEDETAILS(MoKhoaTheReq req, CardInfo card) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTMOTHEDETAILS(req, card);
        } catch (SQLException | NullPointerException ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * UPDATEMOTHEDETAILS
     * 
     * @param refNo
     * @param req
     * @param respCode
     * @param respDesc
     * @return
     * @throws SQLException 
     */
    public boolean UPDATEMOKHOATHEDETAILS(String refNo, MoKhoaTheReq req, String respCode, String respDesc) throws SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.UPDATEMOTHEDETAILS(refNo, req, respCode, respDesc);
        } catch (SQLException | NullPointerException ex) {
            LOGGER.error(ex);
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
 
    /**
     *
     * @param accountNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public Object[] getAccountBalance_wu(String accountNumber) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getAccountBalance_wu(accountNumber);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
}
