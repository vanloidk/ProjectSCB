/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dao.HQDAO;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_CQQLT;
import scb.com.vn.dbi.dto.HQ_BAOLANH_CHUNG;
import scb.com.vn.dbi.dto.HQ_BAOLANH_HDVD;
import scb.com.vn.dbi.dto.HQ_BAOLANH_TK;
import scb.com.vn.dbi.dto.HQ_DOICHIEU;
import scb.com.vn.dbi.dto.HQ_MSG;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_CQQLT_CT;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_HQ;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_HQ_GNT;
import scb.com.vn.dbi.dto.HQ_NOPTIEN_HQ_GNTCT;
import scb.com.vn.dbi.utility.HibernateUtil;
import scb.com.vn.dbi.dto.HQ_DKNNT;

/**
 *
 * @author lydty
 */
public class HQBO extends BaseSMSSCBBO {

    private static final Logger LOGGER = Logger.getLogger(HQBO.class);

    private Connection conn;
    private HQDAO dao = null;

    /**
     * Create a new instance of HQBO
     * @throws java.lang.Exception
     */
    public HQBO() throws Exception {
        dao = new HQDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactorySmsScb());
    }

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_NOPTIEN_HQ(HQ_NOPTIEN_HQ obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_NOPTIEN_HQ(obj);
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
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_HQ_NOPTIEN_HQ_GNT(HQ_NOPTIEN_HQ_GNT obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_HQ_NOPTIEN_HQ_GNT(obj);
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
     * @param obj
     * @throws SQLException
     * @throws RemoteException
     */
    public void INSERT_HQ_NOPTIEN_HQ_GNTCT(HQ_NOPTIEN_HQ_GNTCT obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.INSERT_HQ_NOPTIEN_HQ_GNTCT(obj);
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
     * @param PIDREF
     * @param PTYPE
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] NOPTIEN_CORE(
            int PIDREF,
            String PTYPE //HQ/CQT

    ) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.NOPTIEN_CORE(PIDREF, PTYPE);
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
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_HQ_NOPTIEN_CQQLT(HQ_NOPTIEN_CQQLT obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_HQ_NOPTIEN_CQQLT(obj);
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
     * @param obj
     * @throws SQLException
     * @throws RemoteException
     */
    public void INSERT_HQ_NOPTIEN_CQQLT_CT(
            HQ_NOPTIEN_CQQLT_CT obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.INSERT_HQ_NOPTIEN_CQQLT_CT(obj);
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
     * @param PIDREF
     * @param PTYPE
     * @param PCHECKERID
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] APPROVETRANSFER(
            int PIDREF,
            String PTYPE,// HQ/CQT
            String PCHECKERID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.APPROVETRANSFER(PIDREF, PTYPE, PCHECKERID);
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
     * @param obj
     * @throws SQLException
     * @throws RemoteException
     */
    public void SEND_MESSAGE(
            HQ_MSG obj
    ) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.SEND_MESSAGE(obj);
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
     * @param pStatus
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_HQ> getDataNOPTIEN_HQ(int pID, String pStatus) throws SQLException, RemoteException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataNOPTIEN_HQ(pID, pStatus);
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
     * @param pStatus
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_HQ_GNT> getDataNOPTIEN_HQ_GNT(int pID, String pStatus) throws SQLException, RemoteException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataNOPTIEN_HQ_GNT(pID, pStatus);
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
     * @param pStatus
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_HQ_GNTCT> getDataNOPTIEN_HQ_GNTCT(int pID, String pStatus) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataNOPTIEN_HQ_GNTCT(pID, pStatus);
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
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_HQ_BAOLANH_TK(HQ_BAOLANH_TK obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_HQ_BAOLANH_TK(obj);
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
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_HQ_BAOLANH_CHUNG(HQ_BAOLANH_CHUNG obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_HQ_BAOLANH_CHUNG(obj);
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
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_HQ_BAOLANH_HDVD(HQ_BAOLANH_HDVD obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_HQ_BAOLANH_HDVD(obj);
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
     * @return
     * @throws Exception
     */
    public ArrayList selectCHUONG(String ID) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.selectCHUONG(ID);
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
     * @return
     * @throws Exception
     */
    public ArrayList selectKBNN(String ID) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.selectKBNN(ID);
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
     * @return
     * @throws Exception
     */
    public ArrayList selectNTK(String ID) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.selectNTK(ID);
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
     * @return
     * @throws Exception
     */
    public ArrayList selectCCY(String ID) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.selectCCY(ID);
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
    public String getTransID() throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getTransID();
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
    public String getSOCT() throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getSOCT();
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
     * @param pMSGTYPE
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String getREQUESTID(String pID, String pMSGTYPE) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getREQUESTID(pID, pMSGTYPE);
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
     * @param pStatus
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<HQ_NOPTIEN_CQQLT> getDataNOPTIEN_HQ_CQQLT(int pID, String pStatus) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataNOPTIEN_HQ_CQQLT(pID, pStatus);
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
     * @param pStatus
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_CQQLT_CT> getDataNOPTIEN_HQ_CQQLT_CT(int pID, String pStatus) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataNOPTIEN_HQ_CQQLT_CT(pID, pStatus);
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
     * @param pStatus
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_BAOLANH_TK> getDataBAOLANH_TK(int pID, String pStatus) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataBAOLANH_TK(pID, pStatus);
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
     * @param pStatus
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_BAOLANH_CHUNG> getDataBAOLANH_CHUNG(int pID, String pStatus) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataBAOLANH_CHUNG(pID, pStatus);
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
     * @param pStatus
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_BAOLANH_HDVD> getDataBAOLANH_HDVD(int pID, String pStatus) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataBAOLANH_HDVD(pID, pStatus);
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
     * @param pMADV
     * @param pSO_TK
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param FromRow
     * @param ToRow
     * @param typeTrans
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<HQ_NOPTIEN_HQ> searchNOPTIEN_HQ(String pMADV,
            String pSO_TK,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            String pSO_CT,
            String pKYHIEU_CT,
            int FromRow,
            int ToRow,
            String typeTrans) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.searchNOPTIEN_HQ(pMADV, pSO_TK, pFromdate, pTodate, pBranchCode, pStatus, pSO_CT, pKYHIEU_CT, FromRow, ToRow, typeTrans);
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
     * @param pMADV
     * @param pSO_TK
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param typeTrans
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public int getSumRow(String pMADV,
            String pSO_TK,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            String pSO_CT,
            String pKYHIEU_CT,
            String typeTrans) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getSumRow(pMADV, pSO_TK, pFromdate, pTodate, pBranchCode, pStatus, pSO_CT, pKYHIEU_CT, typeTrans);
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
     * @param obj
     * @throws SQLException
     * @throws RemoteException
     */
    public void updateNOPTIEN_HQ(HQ_NOPTIEN_HQ obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.updateNOPTIEN_HQ(obj);
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
     * @param pMsgType
     * @param pStatus
     * @throws RemoteException
     * @throws SQLException
     */
    public void updateStatus(int pID, String pMsgType, String pStatus) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.updateStatus(pID, pMsgType, pStatus);
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
     * @param pMsgType
     * @param pMakerID
     * @param pBranchID
     * @throws SQLException
     * @throws RemoteException
     */
    public void deleteData(int pID, String pMsgType,
            String pMakerID,
            String pBranchID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.deleteData(pID, pMsgType, pMakerID, pBranchID);
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
     * @return
     * @throws Exception
     */
    public ArrayList selectNDKT(String ID) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.selectNDKT(ID);
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
     * @param BGNUMBER
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String[] GETBGINFO(String BGNUMBER) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.GETBGINFO(BGNUMBER);
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
     * @param MsgType
     * @param pDate
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_DOICHIEU> getDataDoichieu(String MsgType, Date pDate) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataDoichieu(MsgType, pDate);
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
     * @param pNGAY_DC
     * @param pTRANSACTIONID
     * @param PMSGTYPE
     * @param status
     * @param errnumber
     * @param errmsg
     * @throws SQLException
     * @throws RemoteException
     */
    public void InsertDoiChieu(String pNGAY_DC,
            String pTRANSACTIONID,
            String PMSGTYPE,
            String status,
            String errnumber,
            String errmsg) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.InsertDoiChieu(pNGAY_DC, pTRANSACTIONID, PMSGTYPE, status, errnumber, errmsg);
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
     * @param pMsgType
     * @param pIDREF
     * @param pNGAY_DC
     * @param pKQDC
     * @param pTRANSACTIONID
     * @throws SQLException
     * @throws RemoteException
     */
    public void InsertKQDoiChieu(
            String pMsgType,
            String pIDREF,
            String pNGAY_DC,
            String pKQDC,
            String pTRANSACTIONID
    ) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.InsertKQDoiChieu(pMsgType, pIDREF, pNGAY_DC, pKQDC, pTRANSACTIONID);
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
     * @param pMSGTYPE
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String getSO_TN_CT(String pID,
            String pMSGTYPE) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getSO_TN_CT(pID, pMSGTYPE);
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
     * @param LOAIDM
     * @param ID
     * @param ten
     * @return
     * @throws Exception
     */
    public ArrayList selectDANHMUC(String LOAIDM, String ID, String ten) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.selectDANHMUC(LOAIDM, ID, ten);
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
     * @param PIDREF
     * @param PTYPE
     * @param PCHECKERID
     * @return
     * @throws Exception
     */
    public String[] APPROVEBAOLANH(
            int PIDREF,
            String PTYPE,// TK/CHUNG/HDVD
            String PCHECKERID) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.APPROVEBAOLANH(PIDREF, PTYPE, PCHECKERID);
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
     * @param pTRANSACTION_ID
     * @param pSO_TN_CT
     * @param pNGAY_TN_CT
     * @param pKQ_DC
     * @param pNGAY_DC
     * @param pTYPETRANS
     * @param pMSGTYPE
     * @throws RemoteException
     * @throws SQLException
     */
    public void insertKQDOICHIEUHUY(
            String pTRANSACTION_ID,
            String pSO_TN_CT,
            String pNGAY_TN_CT,
            String pKQ_DC,
            String pNGAY_DC,
            String pTYPETRANS,
            String pMSGTYPE
    ) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertKQDOICHIEUHUY(pTRANSACTION_ID, pSO_TN_CT, pNGAY_TN_CT, pKQ_DC, pNGAY_DC, pTYPETRANS, pMSGTYPE);
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
     * @param pSo_HS
     * @param pMST
     * @param pSO_CT
     * @param pKHCT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pFromRow
     * @param pToRow
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<HQ_NOPTIEN_CQQLT> searchNOPTIEN_CQQLT(
            String pSo_HS,
            String pMST,
            String pSO_CT,
            String pKHCT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.searchNOPTIEN_CQQLT(pSo_HS, pMST, pSO_CT, pKHCT, pFromdate, pTodate, pBranchCode, pStatus, pFromRow, pToRow);
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
     * @param pSo_HS
     * @param pMST
     * @param pSO_CT
     * @param pKHCT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public int getSumRowNOPTIEN_CQQLT(
            String pSo_HS,
            String pMST,
            String pSO_CT,
            String pKHCT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus
    ) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getSumRowNOPTIEN_CQQLT(pSo_HS, pMST, pSO_CT, pKHCT, pFromdate, pTodate, pBranchCode, pStatus);
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
     * @param obj
     * @throws SQLException
     * @throws RemoteException
     */
    public void UPDATE_HQ_BAOLANH_TK(HQ_BAOLANH_TK obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UPDATE_HQ_BAOLANH_TK(obj);
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
     * @param obj
     * @throws SQLException
     * @throws RemoteException
     */
    public void UPDATE_HQ_BAOLANH_CHUNG(HQ_BAOLANH_CHUNG obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UPDATE_HQ_BAOLANH_CHUNG(obj);
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
     * @param obj
     * @throws SQLException
     * @throws RemoteException
     */
    public void UPDATE_HQ_BAOLANH_HDVD(HQ_BAOLANH_HDVD obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UPDATE_HQ_BAOLANH_HDVD(obj);
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
     * @param obj
     * @throws SQLException
     * @throws RemoteException
     */
    public void UPDATE_HQ_NOPTIEN_CQQLT(HQ_NOPTIEN_CQQLT obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UPDATE_HQ_NOPTIEN_CQQLT(obj);
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
     * @param pMST
     * @param pSO_TK
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pFromRow
     * @param pToRow
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_BAOLANH_TK> searchBAOLANH_TK(
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.searchBAOLANH_TK(pMST, pSO_TK, pSO_CT, pKYHIEU_CT, pFromdate, pTodate, pBranchCode, pStatus, pFromRow, pToRow);
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
     * @param pMST
     * @param pSO_TK
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pFromRow
     * @param pToRow
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_BAOLANH_CHUNG> searchBAOLANH_CHUNG(
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.searchBAOLANH_CHUNG(pMST, pSO_TK, pSO_CT, pKYHIEU_CT, pFromdate, pTodate, pBranchCode, pStatus, pFromRow, pToRow);
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
     * @param pMST
     * @param pSO_TK
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pFromRow
     * @param pToRow
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_BAOLANH_HDVD> searchBAOLANH_HDVD(
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.searchBAOLANH_HDVD(pMST, pSO_TK, pSO_CT, pKYHIEU_CT, pFromdate, pTodate, pBranchCode, pStatus, pFromRow, pToRow);
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
     * @param pMSGTYPE
     * @param pMST
     * @param pSO_TK
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public int getSumSearchBAOLANH(
            String pMSGTYPE,
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getSumSearchBAOLANH(pMSGTYPE, pMST, pSO_TK, pSO_CT, pKYHIEU_CT, pFromdate, pTodate, pBranchCode, pStatus);
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
     * @param pMA_LH
     * @param pTEN_LH
     * @param pSN_AH
     * @throws SQLException
     * @throws RemoteException
     */
    public void insertDM_LH(String pMA_LH,
            String pTEN_LH,
            String pSN_AH) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertDM_LH(pMA_LH, pTEN_LH, pSN_AH);
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
     * @param pMa_HQ
     * @param pTen_HQ
     * @param pMa_Cu
     * @param pMa_QHNS
     * @throws SQLException
     * @throws RemoteException
     */
    public void insertDM_HQ(String pMa_HQ,
            String pTen_HQ,
            String pMa_Cu,
            String pMa_QHNS) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertDM_HQ(pMa_HQ, pTen_HQ, pMa_Cu, pMa_QHNS);
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
     * @param pMa_KB
     * @param pTen_KB
     * @throws SQLException
     * @throws RemoteException
     */
    public void insertDM_KB(String pMa_KB,
            String pTen_KB
    ) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertDM_KB(pMa_KB, pTen_KB);
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
     * @param pMa_KB
     * @param pTen_KB
     * @throws SQLException
     * @throws RemoteException
     */
    public void insertDM_ER(String pMa_KB,
            String pTen_KB
    ) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertDM_ER(pMa_KB, pTen_KB);
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
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_DKNNT(HQ_DKNNT obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_DKNNT(obj);
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
     * @param pUSER
     * @param pBRANCHID
     * @param pSTATUS
     * @param pACCEPTED
     * @param pDesc
     * @throws SQLException
     * @throws RemoteException
     */
    public void DUYET_DKNNT(
            String pID,
            String pUSER,
            String pBRANCHID,
            String pSTATUS,
            String pACCEPTED,
            String pDesc
    )
            throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.DUYET_DKNNT(pID, pUSER, pBRANCHID, pSTATUS, pACCEPTED, pDesc);
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
     * @param pType
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] getData213(String pID,
            String pType) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getData213(pID, pType);
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
     * @param pSO_HS
     * @param pMST
     * @param pBranchID
     * @param pStatus
     * @param FromDate
     * @param ToDate
     * @param STK
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<HQ_DKNNT> searchNNT(String pSO_HS,
            String pMST,
            String pBranchID,
            String pStatus,
            String FromDate,
            String ToDate,
            String STK) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.searchNNT(pSO_HS, pMST, pBranchID, pStatus, FromDate, ToDate, STK
            );
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
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_DKNNT> getData312(String pID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getData312(pID);
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
     * @param pRequestID
     * @param pTransID
     * @param pSO_HS
     * @throws SQLException
     * @throws RemoteException
     */
    public void UpdateDKNNT313(String pRequestID,
            String pTransID,
            String pSO_HS) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UpdateDKNNT313(pRequestID, pTransID, pSO_HS);
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
    public String getSO_TN_CT_SCB() throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getSO_TN_CT_SCB();
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
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_NOPTIEN_HQ_ONLINE(HQ_NOPTIEN_HQ obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_NOPTIEN_HQ_ONLINE(obj);
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
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_GNT_ONLINE(HQ_NOPTIEN_HQ_GNT obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_GNT_ONLINE(obj);
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
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_NOPTIEN_CQT_ONINE(HQ_NOPTIEN_CQQLT obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_NOPTIEN_CQT_ONINE(obj);
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
     * @param Type
     * @param xml
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] NOPTIEN_CORE_ONLINE(String ID, String Type, String xml) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.NOPTIEN_CORE_ONLINE(ID, Type, xml);
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
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_HQ_GNT> getDataGNT_CT(String ID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataGNT_CT(ID);
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
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_NOPTIEN_HQ_GNTCT> getDataTOKHAI_CT(String ID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataTOKHAI_CT(ID);
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
    public List<HQ_NOPTIEN_HQ> getlistDataGNT() throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getlistDataGNT();
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
    public String getMaxDataCollated() throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getMaxDataCollated();
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
     * @param obj
     * @throws SQLException
     * @throws RemoteException
     */
    public void UPDATE_DKNNT(HQ_DKNNT obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UPDATE_DKNNT(obj);
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
     * @throws SQLException
     * @throws RemoteException
     */
    public void SendGNTHQ(String ID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.SendGNTHQ(ID);
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
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<HQ_DKNNT> getDataDKNNT(String pID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataDKNNT(pID);
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
     * @param Type
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public int check213(String ID, String Type) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.check213(ID, Type);
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
     * @throws Exception
     */
    public void UPDATE_HQ_ONLINE_201(String ID) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UPDATE_HQ_ONLINE_201(ID);
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
     * @param SO_HS
     * @param MA_DV
     * @param status
     * @return
     * @throws Exception
     */
    public List<HQ_DKNNT> searchDKUQ(String SO_HS, String MA_DV, String status) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.searchDKUQ(SO_HS, MA_DV, status);
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
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_HQ_NOPTIEN_HQ_GNT_201(HQ_NOPTIEN_HQ_GNT obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_HQ_NOPTIEN_HQ_GNT_201(obj);
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
