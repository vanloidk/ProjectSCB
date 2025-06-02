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
import scb.com.vn.common.model.ExchangeRate.ExchangeRateRes;
import scb.com.vn.common.model.transfer.SenderInfo;
import scb.com.vn.common.model.transfer.TransactionDetail;
import scb.com.vn.common.model.transfer.napas.TransferMoney247EbankReq;
import scb.com.vn.common.model.transfer.status.TransactionDetailByDate;
import scb.com.vn.dbi.dao.SIDAO;
import scb.com.vn.dbi.dto.SiTrffromsiExtend;
import scb.com.vn.dbi.dto.TransfersRemittance;
import scb.com.vn.dbi.utility.HibernateUtil;
import scb.com.vn.model.status.transferMoney.GetStatusOfTransferMoneyReq;
import scb.com.vn.model.status.transferMoney.TransferMoneyTransactionInfo;

/**
 *
 * @author lydty
 */
public class SIBO extends BaseSMSSCBBO {

    private static final Logger LOGGER = Logger.getLogger(SIBO.class);

    private Connection conn;
    private SIDAO dao = null;

    /**
     * Create a new instance of SIBO
     *
     * @throws java.lang.Exception
     */
    public SIBO() throws Exception {
        dao = new SIDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactorySmsScb());
    }

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
     * @throws RemoteException
     * @throws SQLException
     */
    public Object[] InsertSITRANFERTOSI(
            String PPARTNERID,
            String PCUSTUMERACCOUNT,
            String PCUSTUMERNAME,
            BigDecimal PAMOUNT,
            String PCCY,
            String PCHANNELID,
            String PTRANSDATE,
            String PADDINFO) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.InsertSITRANFERTOSI(PPARTNERID, PCUSTUMERACCOUNT, PCUSTUMERNAME, PAMOUNT, PCCY, PCHANNELID, PTRANSDATE, PADDINFO);
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
     * @param pSTATUS
     * @param pRESPONSECODE
     * @param pTXNREF
     * @param pDESCRESPONSE
     * @throws SQLException
     * @throws RemoteException
     */
    public void CONFIRMSITRANFERTOSI(
            double PID,
            String PREFCORE,
            String pSTATUS,
            String pRESPONSECODE,
            String pTXNREF,
            String pDESCRESPONSE) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.CONFIRMSITRANFERTOSI(PID, PREFCORE, pSTATUS, pRESPONSECODE, pTXNREF, pDESCRESPONSE);
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
     * @param pPARTNERID
     * @param pTRANSID
     * @param pTRANSDATE
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERTSITRANSFROMSI(
            String pPARTNERID,
            String pTRANSID,
            String pTRANSDATE
    ) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTSITRANSFROMSI(pPARTNERID, pTRANSID, pTRANSDATE);
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
     * @param pAccount
     * @param pAmount
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] CHECKACCOUNT(
            String pAccount,
            double pAmount
    ) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.CHECKACCOUNT(pAccount, pAmount);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    //Update 2018 by LYDTY

    /**
     *
     * @param pID_MASTER
     * @param pTXNDETAILID
     * @param pPARTNERACCOUNT
     * @param pCUSTUMERNAME
     * @param pCUSTUMERACCOUNT
     * @param pBANKCODE
     * @param pBranchname
     * @param pAmount
     * @param pCCY
     * @param pDescription
     * @param pTYPETRANSFER
     * @param pTYPECUSTACCOUNT
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public Object[] INSERTTRANSFERFROMSIDETAIL(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,
            String pCUSTUMERNAME,
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            String pBranchname,
            double pAmount,
            String pCCY,
            String pDescription,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT
    ) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTTRANSFERFROMSIDETAIL(pID_MASTER, pTXNDETAILID, pPARTNERACCOUNT, pCUSTUMERNAME, pCUSTUMERACCOUNT, pBANKCODE, pBranchname, pAmount, pCCY, pDescription, pTYPETRANSFER, pTYPECUSTACCOUNT);
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
     * @param pID_MASTER
     * @param pTXNDETAILID
     * @param pPARTNERACCOUNT
     * @param pCUSTUMERNAME
     * @param pCUSTUMERACCOUNT
     * @param pBANKCODE
     * @param pBranchname
     * @param pAmount
     * @param pCCY
     * @param pDescription
     * @param pTYPETRANSFER
     * @param pTYPECUSTACCOUNT
     * @param rate
     * @param amtExchange
     * @param ccyExchange
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public Object[] INSERTTRANSFERFROMSIDETAILKIEUHOI(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,
            String pCUSTUMERNAME,
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            String pBranchname,
            double pAmount,
            String pCCY,
            String pDescription,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT,
            BigDecimal rate,
            BigDecimal amtExchange,
            String ccyExchange,
            String personId,
            String firstName,
            String lastName,
            String passNo,
            String birthday,
            String address,
            String nationality,
            String custtype
    ) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTTRANSFERFROMSIDETAIL_KIEUHOI(pID_MASTER, pTXNDETAILID, pPARTNERACCOUNT,
                    pCUSTUMERNAME, pCUSTUMERACCOUNT, pBANKCODE, pBranchname, pAmount, pCCY,
                    pDescription, pTYPETRANSFER, pTYPECUSTACCOUNT, rate, amtExchange, ccyExchange, personId, firstName, lastName, passNo, birthday, address, nationality, custtype);
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
     * @param pREFCORE
     * @param pVALIDDATECORE
     * @param pSTATUS
     * @throws SQLException
     * @throws RemoteException
     */
    public void UPDATETRANSFERFROMSIDETAIL(
            double PID,
            String pREFCORE,
            String pVALIDDATECORE,
            String pSTATUS) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UPDATETRANSFERFROMSIDETAIL(PID, pREFCORE, pVALIDDATECORE, pSTATUS);
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
     * @param pID_MASTER
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public ArrayList GETLISTTRANSFROMSI(int pID_MASTER) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.GETLISTTRANSFROMSI(pID_MASTER);
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
    public ArrayList getListBank() throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getListBank();
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
     * @param pBANKCODE
     * @param pACCOUNTNO
     * @param pACCOUNTNAME
     * @param pACCOUNTSI
     * @param pNAMESI
     * @param pAMOUNT
     * @param pCCY
     * @param pSITRANSID
     * @param pTRANSDATE
     * @param pSCBTRANSID
     * @param pTYPETRANS
     * @throws SQLException
     * @throws RemoteException
     */
    public void SI_InsertCollated(String pBANKCODE,
            String pACCOUNTNO,
            String pACCOUNTNAME,
            String pACCOUNTSI,
            String pNAMESI,
            String pAMOUNT,
            String pCCY,
            String pSITRANSID,
            String pTRANSDATE,
            String pSCBTRANSID,
            String pTYPETRANS) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.SI_InsertCollated(pBANKCODE, pACCOUNTNO, pACCOUNTNAME, pACCOUNTSI, pNAMESI, pAMOUNT, pCCY, pSITRANSID, pTRANSDATE, pSCBTRANSID, pTYPETRANS);
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
     * @param pDate
     * @param pPartnerid
     * @param pType
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public ArrayList getOutCollated(Date pDate, String pPartnerid, String pType) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getOutCollated(pDate, pPartnerid, pType);
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
     * @param pPARTNERID
     * @param Transid
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List querryTransfer(String pPARTNERID, String Transid) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.querryTransfer(pPARTNERID, Transid);
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
    public ArrayList getListBank247() throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getListBank247();
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
     * @param pPARTNERID
     * @param pTYPEFUNC
     * @param pAMOUNT
     * @param pCCY
     * @param pBANKCODE
     * @param pACCOUNTNO
     * @param pACCOUNTNAME
     * @param pSITRANSID
     * @param pSCBTRANSID
     * @param pTRANSDATE
     * @param pSTATUSTRANS
     * @throws RemoteException
     * @throws SQLException
     */
    public void InsertCollated_TCH(String pPARTNERID,
            String pTYPEFUNC,
            String pAMOUNT,
            String pCCY,
            String pBANKCODE,
            String pACCOUNTNO,
            String pACCOUNTNAME,
            String pSITRANSID,
            String pSCBTRANSID,
            String pTRANSDATE,
            String pSTATUSTRANS) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.InsertCollated_TCH(pPARTNERID, pTYPEFUNC, pAMOUNT, pCCY, pBANKCODE, pACCOUNTNO, pACCOUNTNAME, pSITRANSID, pSCBTRANSID, pTRANSDATE, pSTATUSTRANS);
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
     * @param pDate
     * @param pPartnerid
     * @param pType
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public ArrayList getOutCollated_TCH(Date pDate, String pPartnerid, String pType) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getOutCollated_TCH(pDate, pPartnerid, pType);
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
     * @param idTrans
     * @param res
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public boolean updateRateToSI_TRFFROMSI_DETAIL(String idTrans, ExchangeRateRes res)
            throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.updateRateToSI_TRFFROMSI_DETAIL(idTrans, res);
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
     * @param transDetail
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public Object[] insertDetailToSi(TransactionDetail transDetail)
            throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.insertDetailToSi(transDetail);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int checkPartnerPregolive(String partnerid) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkPartnerPregolive(partnerid);
        } catch (Exception IBEx) {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int checkAccountPregolive(String partnerid, String accountno) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkAccountPregolive(partnerid, accountno);
        } catch (Exception IBEx) {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public TransferMoneyTransactionInfo checkStatusTransferMoney(GetStatusOfTransferMoneyReq req) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkStatusTransferMoney(req);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*dang test tren 73.20
    * doi tac: WU
     */
    public int insertToSiSenderDetail(SenderInfo senderInfo, Long bankTransId) throws Exception, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.insertToSiSenderDetail(senderInfo, bankTransId);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*dang test tren 73.20
    * doi tac: WU
     */
    public List<TransactionDetailByDate> getListTransactionByDate(String providerId, String partnerAccount, String startDateTime, String endDateTime) throws Exception, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getListTransactionByDate(providerId, partnerAccount, startDateTime, endDateTime);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*dang test tren 73.20
    * doi tac: WU
     */
    public String[] insertErrorKycSiTransDetail(String idMaster, String txnDetailId, String partnerAccount, String customerName, String customerAccount,
            String bankCode, String branchName, BigDecimal amount, String ccy, String description, String status, String typeTransfer,
            String typeCustAccount, BigDecimal rate, BigDecimal amtExchange, String ccyExchange, String personId, String firtName, String lastName,
            String passNo, String birthDate, String address, String nationlity, String custType) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.insertErrorKycSiTransDetail(idMaster, txnDetailId, partnerAccount, customerName, customerAccount, bankCode, branchName, amount,
                    ccy, description, status, typeTransfer, typeCustAccount, rate, amtExchange, ccyExchange, personId, firtName, lastName,
                    passNo, birthDate, address, nationlity, custType);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*WU - TEST CASE 73.20 test xong xoa*/
    public String[] insertTestCaseToTransDetail(String idMaster, String txnDetailId, String partnerAccount, String customerName,
            String customerAccount, String bankCode, BigDecimal amount, String ccy, String description, String typeTransfer, String status) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.insertTestCaseToTransDetail(idMaster, txnDetailId, partnerAccount, customerName, customerAccount, bankCode, amount, ccy,
                    description, typeTransfer, status);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*BAOTBQ - KIEUHOI*/
    public ArrayList getInforRemittance(String magd) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getInforRemittance(magd);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<TransfersRemittance> getInforRemittanceByDate(String tungay, String denngay) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getInforRemittanceByDate(tungay, denngay);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int insertTransfersRemittanceExtend(SiTrffromsiExtend siTrffromsiExtend) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.insertTransfersRemittanceExtend(siTrffromsiExtend);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int updateTransfersRemittanceExtend(String siIdExtend, String userChecker, String approved) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.updateTransfersRemittanceExtend(siIdExtend, userChecker, approved);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int checkTransfersRemittanceApproved(String id, String approved) throws SQLException, Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkTransfersRemittanceApproved(id, approved);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList getSiTrffromSiById(String id) throws SQLException, Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getSiTrffromSiById(id);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<TransferMoney247EbankReq> getTransfer247Ebank(String id) throws SQLException, Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getTransfer247Ebank(id);
        } catch (Exception e) {
            LOGGER.error(e);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Object[] INSERTTRANSFERFROMSIDETAILKIEUHOI_WU(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,
            String pCUSTUMERNAME,
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            String pBranchname,
            double pAmount,
            String pCCY,
            String pDescription,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT,
            BigDecimal rate,
            BigDecimal amtExchange,
            String ccyExchange,
            String personId,
            String firstName,
            String lastName,
            String passNo,
            String birthday,
            String address,
            String nationality,
            String custtype
    ) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERTTRANSFERFROMSIDETAIL_KIEUHOI_WU(pID_MASTER, pTXNDETAILID, pPARTNERACCOUNT,
                    pCUSTUMERNAME, pCUSTUMERACCOUNT, pBANKCODE, pBranchname, pAmount, pCCY,
                    pDescription, pTYPETRANSFER, pTYPECUSTACCOUNT, rate, amtExchange, ccyExchange, personId, firstName, lastName, passNo, birthday, address, nationality, custtype);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* Baotbq 5/9/2023 add check and update status trc khi goi qua napas*/
    public Object[] UPDATETRANSFERFROMSIDETAILKIEUHOI_EBMR(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            double pAmount,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT,
            BigDecimal amtExchange
    ) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.UPDATETRANSFERFROMSIDETAILKIEUHOI_EBMR(pID_MASTER, pTXNDETAILID, pPARTNERACCOUNT, pCUSTUMERACCOUNT, pBANKCODE, pAmount, pTYPETRANSFER, pTYPECUSTACCOUNT, amtExchange);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
