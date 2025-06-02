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
import scb.com.vn.dbi.dao.NTDTDAO;
import scb.com.vn.dbi.dto.NTDT_BCDC_02;
import scb.com.vn.dbi.dto.NTDT_BCDC_05;
import scb.com.vn.dbi.dto.NTDT_DOICHIEUGD;
import scb.com.vn.dbi.dto.NTDT_GNT;
import scb.com.vn.dbi.dto.NTDT_NNT;
import scb.com.vn.dbi.dto.NTDT_TDSTK;
import scb.com.vn.dbi.dto.NTDT_searchNNT;
import scb.com.vn.dbi.utility.HibernateUtil;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author Administrator
 */
public class NTDTBO extends BaseSMSSCBBO {

    private static final Logger LOGGER = Logger.getLogger(NTDTBO.class);

    private Connection conn;
    private NTDTDAO dao = null;

    /**
     * Create a new instance of NTDTBO
     * @throws java.lang.Exception
     */
    public NTDTBO() throws Exception {
        dao = new NTDTDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactorySmsScb());
    }

    /**
     *
     * @param pMA_GDICH
     * @param pNGAY_GUI_GDICH
     * @param pMA_GDICH_TCHIEU
     * @param pPBAN_TLIEU_XML
     * @param pMST
     * @param pTEN_NNT
     * @param pDIACHI_NNT
     * @param pMA_CQT
     * @param pEMAIL_NNT
     * @param pSDT_NNT
     * @param pTEN_LHE_NTHUE
     * @param pSERIAL_CERT_NTHUE
     * @param pSUBJECT_CERT_NTHUE
     * @param pISSUER_CERT_NTHUE
     * @param pMA_NHANG
     * @param pTEN_NHANG
     * @param pVAN_ID
     * @param pTEN_TVAN
     * @param pNGAY_GUI
     * @param pLOAITHONGTIN
     * @param pMSGID
     * @param pTRANSCODE
     * @param pCKS
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] InsertTHONGTIN_NNT(String pMA_GDICH,
            String pNGAY_GUI_GDICH,
            String pMA_GDICH_TCHIEU,
            String pPBAN_TLIEU_XML,
            String pMST,
            String pTEN_NNT,
            String pDIACHI_NNT,
            String pMA_CQT,
            String pEMAIL_NNT,
            String pSDT_NNT,
            String pTEN_LHE_NTHUE,
            String pSERIAL_CERT_NTHUE,
            String pSUBJECT_CERT_NTHUE,
            String pISSUER_CERT_NTHUE,
            String pMA_NHANG,
            String pTEN_NHANG,
            String pVAN_ID,
            String pTEN_TVAN,
            String pNGAY_GUI,
            String pLOAITHONGTIN,
            String pMSGID,
            String pTRANSCODE,
            String pCKS) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.InsertTHONGTIN_NNT(pMA_GDICH, pNGAY_GUI_GDICH, pMA_GDICH_TCHIEU, pPBAN_TLIEU_XML, pMST, pTEN_NNT, pDIACHI_NNT, pMA_CQT, pEMAIL_NNT, pSDT_NNT, pTEN_LHE_NTHUE, pSERIAL_CERT_NTHUE, pSUBJECT_CERT_NTHUE, pISSUER_CERT_NTHUE, pMA_NHANG, pTEN_NHANG, pVAN_ID, pTEN_TVAN, pNGAY_GUI, pLOAITHONGTIN, pMSGID, pTRANSCODE, pCKS);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /*
    pTypedata: INSERT/UPDATE
     */
    /**
     *
     * @param pAccountNo
     * @param pID_NNT
     * @param pIsDeleted
     * @param pAddinfo
     * @param pUSERID
     * @param pBRANCHID
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String InsertAccountNo(String pAccountNo,
            int pID_NNT,
            int pIsDeleted,
            String pAddinfo,
            String pUSERID,
            String pBRANCHID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.InsertAccountNo(pAccountNo, pID_NNT, pIsDeleted, pAddinfo, pUSERID, pBRANCHID);
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
     * @param pID_NNT
     * @param pUserApprove
     * @param pStatus
     * @throws SQLException
     */
    public void ApproveAccounts(int pID_NNT, String pUserApprove, String pStatus
    ) throws SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.ApproveAccounts(pID_NNT, pUserApprove, pStatus);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pID_NNT
     * @throws SQLException
     * @throws RemoteException
     */
    public void THONGBAO_THAYDOI_STK(int pID_NNT) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.THONGBAO_THAYDOI_STK(pID_NNT);
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
     * @param pLYDONGUNG
     * @param pCLOSEDATE
     * @param MA_TBAO
     * @param MAU_TBAO
     * @param SO_TBAO
     * @param TEN_TBAO
     * @param NGAY_TBAO
     * @throws SQLException
     * @throws RemoteException
     */
    public void CloseNNT(String pMST,
            String pLYDONGUNG,
            String pCLOSEDATE,
            String MA_TBAO,
            String MAU_TBAO,
            String SO_TBAO,
            String TEN_TBAO,
            String NGAY_TBAO) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.CloseNNT(pMST, pLYDONGUNG, pCLOSEDATE, MA_TBAO, MAU_TBAO, SO_TBAO, TEN_TBAO, NGAY_TBAO);
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
     * @param pID_TT_NNT
     * @param pCheckStatus
     * @param pAddinfo
     * @param pUserUD
     * @param pBranchID
     * @throws SQLException
     * @throws RemoteException
     */
    public void CheckTT_NNT(int pID_TT_NNT,
            String pCheckStatus,
            String pAddinfo,
            String pUserUD,
            String pBranchID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.CheckTT_NNT(pID_TT_NNT, pCheckStatus, pAddinfo, pUserUD, pBranchID);
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
     * @param pID_TT_NNT
     * @param pUserUD
     * @param pID_NNT
     * @throws SQLException
     * @throws RemoteException
     */
    public void Confirm_TT_NNT(int pID_TT_NNT,
            String pUserUD,
            int pID_NNT) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.Confirm_TT_NNT(pID_TT_NNT, pUserUD, pID_NNT);
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
     * @param pMA_GDICH
     * @param pNGAY_GUI_GDICH
     * @param pMA_GDICH_TCHIEU
     * @param pMAHIEU_CTU
     * @param pSO_CTU
     * @param pPBAN_TLIEU_XML
     * @param pID_CTU
     * @param pSO_GNT
     * @param pMA_CTU
     * @param pHTHUC_NOP
     * @param pMST_NNOP
     * @param pTEN_NNOP
     * @param pDIACHI_NNOP
     * @param pMA_CQT
     * @param pTEN_CQT
     * @param pMA_XA_NNOP
     * @param pTEN_XA_NNOP
     * @param pMA_HUYEN_NNOP
     * @param pTEN_HUYEN_NNOP
     * @param pMA_TINH_NNOP
     * @param pTEN_TINH_NNOP
     * @param pMST_NTHAY
     * @param pTEN_NTHAY
     * @param pDIACHI_NTHAY
     * @param pTEN_HUYEN_NTHAY
     * @param pTEN_TINH_NTHAY
     * @param pMA_NHANG_NOP
     * @param pTEN_NHANG_NOP
     * @param pSTK_NHANG_NOP
     * @param pMA_HIEU_KBAC
     * @param pTEN_KBAC
     * @param pMA_TINH_KBAC
     * @param pTEN_TINH_KBAC
     * @param pLOAI_TK_THU
     * @param pTEN_TK_THU
     * @param pSTK_THU
     * @param pID_TK_KNGHI
     * @param pTK_KNGHI
     * @param pMA_CQTHU
     * @param pTEN_CQTHU
     * @param pNGAY_LAP
     * @param pTONG_TIEN
     * @param pVAN_ID
     * @param pMA_DBHC_THU
     * @param pTEN_DBHC_THU
     * @param pMA_LOAI_THUE
     * @param pMSGID
     * @param pTRANSCODE
     * @param pCKS
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_GNT(String pMA_GDICH,
            String pNGAY_GUI_GDICH,
            String pMA_GDICH_TCHIEU,
            String pMAHIEU_CTU,
            String pSO_CTU,
            String pPBAN_TLIEU_XML,
            String pID_CTU,
            String pSO_GNT,
            String pMA_CTU,
            String pHTHUC_NOP,
            String pMST_NNOP,
            String pTEN_NNOP,
            String pDIACHI_NNOP,
            String pMA_CQT,
            String pTEN_CQT,
            String pMA_XA_NNOP,
            String pTEN_XA_NNOP,
            String pMA_HUYEN_NNOP,
            String pTEN_HUYEN_NNOP,
            String pMA_TINH_NNOP,
            String pTEN_TINH_NNOP,
            String pMST_NTHAY,
            String pTEN_NTHAY,
            String pDIACHI_NTHAY,
            String pTEN_HUYEN_NTHAY,
            String pTEN_TINH_NTHAY,
            String pMA_NHANG_NOP,
            String pTEN_NHANG_NOP,
            String pSTK_NHANG_NOP,
            String pMA_HIEU_KBAC,
            String pTEN_KBAC,
            String pMA_TINH_KBAC,
            String pTEN_TINH_KBAC,
            String pLOAI_TK_THU,
            String pTEN_TK_THU,
            String pSTK_THU,
            String pID_TK_KNGHI,
            String pTK_KNGHI,
            String pMA_CQTHU,
            String pTEN_CQTHU,
            String pNGAY_LAP,
            String pTONG_TIEN,
            String pVAN_ID,
            String pMA_DBHC_THU,
            String pTEN_DBHC_THU,
            String pMA_LOAI_THUE,
            String pMSGID,
            String pTRANSCODE,
            String pCKS) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_GNT(pMA_GDICH, pNGAY_GUI_GDICH, pMA_GDICH_TCHIEU, pMAHIEU_CTU, pSO_CTU, pPBAN_TLIEU_XML, pID_CTU, pSO_GNT, pMA_CTU, pHTHUC_NOP, pMST_NNOP, pTEN_NNOP, pDIACHI_NNOP, pMA_CQT, pTEN_CQT, pMA_XA_NNOP, pTEN_XA_NNOP, pMA_HUYEN_NNOP, pTEN_HUYEN_NNOP, pMA_TINH_NNOP, pTEN_TINH_NNOP, pMST_NTHAY, pTEN_NTHAY, pDIACHI_NTHAY, pTEN_HUYEN_NTHAY, pTEN_TINH_NTHAY, pMA_NHANG_NOP, pTEN_NHANG_NOP, pSTK_NHANG_NOP, pMA_HIEU_KBAC, pTEN_KBAC, pMA_TINH_KBAC, pTEN_TINH_KBAC, pLOAI_TK_THU, pTEN_TK_THU, pSTK_THU, pID_TK_KNGHI, pTK_KNGHI, pMA_CQTHU, pTEN_CQTHU, pNGAY_LAP, pTONG_TIEN, pVAN_ID, pMA_DBHC_THU, pTEN_DBHC_THU, pMA_LOAI_THUE, pMSGID, pTRANSCODE, pCKS);
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
     * @param pID_GNT
     * @param pID_CTU_CTIET
     * @param pID_CTU
     * @param pNDUNG_NOP
     * @param pMA_NDKT
     * @param pMA_CHUONG
     * @param pKY_THUE
     * @param pTIEN_PNOP
     * @param pGHI_CHU
     * @throws SQLException
     * @throws RemoteException
     */
    public void INSERT_CHUNGTU_CHIIET(int pID_GNT,
            String pID_CTU_CTIET,
            String pID_CTU,
            String pNDUNG_NOP,
            String pMA_NDKT,
            String pMA_CHUONG,
            String pKY_THUE,
            String pTIEN_PNOP,
            String pGHI_CHU) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.INSERT_CHUNGTU_CHIIET(pID_GNT, pID_CTU_CTIET, pID_CTU, pNDUNG_NOP, pMA_NDKT, pMA_CHUONG, pKY_THUE, pTIEN_PNOP, pGHI_CHU);
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
     * @param pID_THONGBAO
     * @param pMESS_CODE
     * @param pMESS_CONTENT
     * @throws SQLException
     * @throws RemoteException
     */
    public void CONFIRMTHONGBAO(String pID_THONGBAO,
            String pMESS_CODE,
            String pMESS_CONTENT) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.CONFIRMTHONGBAO(pID_THONGBAO, pMESS_CODE, pMESS_CONTENT);
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
     * @param pID_NNT
     * @param pIsApproved
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public ArrayList GET_ACCOUNNO(int pID_NNT, int pIsApproved) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.GET_ACCOUNNO(pID_NNT, pIsApproved);
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
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<NTDT_NNT> GUITHONGBAO(String pLOAITHONGBAO, int pID_REF) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.GUITHONGBAO(pLOAITHONGBAO, pID_REF);
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
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<NTDT_GNT> GUITHONGBAO_GNT(String pLOAITHONGBAO, int pID_REF) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.GUITHONGBAO_GNT(pLOAITHONGBAO, pID_REF);
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
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<NTDT_TDSTK> GUITHONGBAO_STK(String pLOAITHONGBAO, int pID_REF) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.GUITHONGBAO_STK(pLOAITHONGBAO, pID_REF);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    //pID_DOICHIEU_GD     out number

    /**
     *
     * @param pPBAN_TLIEU_XML_DC
     * @param pMA_GDICH_DC
     * @param pNGAY_GDICH_DC
     * @param pMA_NHANG_DC
     * @param pNGAY_DC
     * @param pTU_NGAY_DC
     * @param pDEN_NGAY_DC
     * @param pMA_GDICH_TCHIEU_DC
     * @param pMSGID
     * @param pTRANSCODE
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public int INSERT_DOICHIEU_GD(String pPBAN_TLIEU_XML_DC,
            String pMA_GDICH_DC,
            String pNGAY_GDICH_DC,
            String pMA_NHANG_DC,
            String pNGAY_DC,
            String pTU_NGAY_DC,
            String pDEN_NGAY_DC,
            String pMA_GDICH_TCHIEU_DC,
            String pMSGID,
            String pTRANSCODE) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_DOICHIEU_GD(pPBAN_TLIEU_XML_DC, pMA_GDICH_DC, pNGAY_GDICH_DC, pMA_NHANG_DC, pNGAY_DC, pTU_NGAY_DC, pDEN_NGAY_DC, pMA_GDICH_TCHIEU_DC, pMSGID, pTRANSCODE);
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
     * @param pID_DOICHIEU_GD
     * @param pMA_GDICH
     * @param pNGAY_GUI_GDICH
     * @param pNGAY_NOP_GNT
     * @param pNGAY_NOP_THUE
     * @param pTTHAI_GDICH
     * @param pTTHAI_CTU
     * @param pMAHIEU_CTU
     * @param pSO_CTU
     * @param pPBAN_TLIEU_XML
     * @param pID_CTU
     * @param pSO_GNT
     * @param pMA_CTU
     * @param pHTHUC_NOP
     * @param pMST_NNOP
     * @param pTEN_NNOP
     * @param pMA_CQT
     * @param pTEN_CQT
     * @param pDIACHI_NNOP
     * @param pMA_XA_NNOP
     * @param pTEN_XA_NNOP
     * @param pMA_HUYEN_NNOP
     * @param pTEN_HUYEN_NNOP
     * @param pMA_TINH_NNOP
     * @param pTEN_TINH_NNOP
     * @param pMST_NTHAY
     * @param pTEN_NTHAY
     * @param pDIACHI_NTHAY
     * @param pTEN_HUYEN_NTHAY
     * @param pTEN_TINH_NTHAY
     * @param pMA_NHANG_NOP
     * @param pTEN_NHANG_NOP
     * @param pSTK_NHANG_NOP
     * @param pMA_HIEU_KBAC
     * @param pTEN_KBAC
     * @param pMA_TINH_KBAC
     * @param pTEN_TINH_KBAC
     * @param pLOAI_TK_THU
     * @param pTEN_TK_THU
     * @param pSTK_THU
     * @param pID_TK_KNGHI
     * @param pTK_KNGHI
     * @param pMA_CQTHU
     * @param pTEN_CQTHU
     * @param pNGAY_LAP
     * @param pTONG_TIEN
     * @param pVAN_ID
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public int INSERT_DOICHIEU_GNT(int pID_DOICHIEU_GD,
            String pMA_GDICH,
            String pNGAY_GUI_GDICH,
            String pNGAY_NOP_GNT,
            String pNGAY_NOP_THUE,
            String pTTHAI_GDICH,
            String pTTHAI_CTU,
            String pMAHIEU_CTU,
            String pSO_CTU,
            String pPBAN_TLIEU_XML,
            String pID_CTU,
            String pSO_GNT,
            String pMA_CTU,
            String pHTHUC_NOP,
            String pMST_NNOP,
            String pTEN_NNOP,
            String pMA_CQT,
            String pTEN_CQT,
            String pDIACHI_NNOP,
            String pMA_XA_NNOP,
            String pTEN_XA_NNOP,
            String pMA_HUYEN_NNOP,
            String pTEN_HUYEN_NNOP,
            String pMA_TINH_NNOP,
            String pTEN_TINH_NNOP,
            String pMST_NTHAY,
            String pTEN_NTHAY,
            String pDIACHI_NTHAY,
            String pTEN_HUYEN_NTHAY,
            String pTEN_TINH_NTHAY,
            String pMA_NHANG_NOP,
            String pTEN_NHANG_NOP,
            String pSTK_NHANG_NOP,
            String pMA_HIEU_KBAC,
            String pTEN_KBAC,
            String pMA_TINH_KBAC,
            String pTEN_TINH_KBAC,
            String pLOAI_TK_THU,
            String pTEN_TK_THU,
            String pSTK_THU,
            String pID_TK_KNGHI,
            String pTK_KNGHI,
            String pMA_CQTHU,
            String pTEN_CQTHU,
            String pNGAY_LAP,
            String pTONG_TIEN,
            String pVAN_ID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_DOICHIEU_GNT(pID_DOICHIEU_GD, pMA_GDICH, pNGAY_GUI_GDICH, pNGAY_NOP_GNT, pNGAY_NOP_THUE, pTTHAI_GDICH, pTTHAI_CTU, pMAHIEU_CTU, pSO_CTU, pPBAN_TLIEU_XML, pID_CTU, pSO_GNT, pMA_CTU, pHTHUC_NOP, pMST_NNOP, pTEN_NNOP, pMA_CQT, pTEN_CQT, pDIACHI_NNOP, pMA_XA_NNOP, pTEN_XA_NNOP, pMA_HUYEN_NNOP, pTEN_HUYEN_NNOP, pMA_TINH_NNOP, pTEN_TINH_NNOP, pMST_NTHAY, pTEN_NTHAY, pDIACHI_NTHAY, pTEN_HUYEN_NTHAY, pTEN_TINH_NTHAY, pMA_NHANG_NOP, pTEN_NHANG_NOP, pSTK_NHANG_NOP, pMA_HIEU_KBAC, pTEN_KBAC, pMA_TINH_KBAC, pTEN_TINH_KBAC, pLOAI_TK_THU, pTEN_TK_THU, pSTK_THU, pID_TK_KNGHI, pTK_KNGHI, pMA_CQTHU, pTEN_CQTHU, pNGAY_LAP, pTONG_TIEN, pVAN_ID);
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
     * @param pID_CTU_CTIET
     * @param pID_CTU
     * @param pNDUNG_NOP
     * @param pMA_NDKT
     * @param pMA_CHUONG
     * @param pTIEN_PNOP
     * @param pID_DOICHIEU_GNT
     * @throws SQLException
     * @throws RemoteException
     */
    public void INSERT_DOICHIEU_GNT_CHITIET(String pID_CTU_CTIET,
            String pID_CTU,
            String pNDUNG_NOP,
            String pMA_NDKT,
            String pMA_CHUONG,
            String pTIEN_PNOP,
            int pID_DOICHIEU_GNT) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.INSERT_DOICHIEU_GNT_CHITIET(pID_CTU_CTIET, pID_CTU, pNDUNG_NOP, pMA_NDKT, pMA_CHUONG, pTIEN_PNOP, pID_DOICHIEU_GNT);
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
     * @param pID_DOICHIEU_GD
     * @param pMABAOCAO
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<NTDT_DOICHIEUGD> BAOCAODOICHIEU(int pID_DOICHIEU_GD, String pMABAOCAO) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.BAOCAODOICHIEU(pID_DOICHIEU_GD, pMABAOCAO);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pReportType
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<NTDT_BCDC_02> BCDC_02(int pID_DOICHIEU_GD, String pReportType) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.BCDC_02_2019(pID_DOICHIEU_GD, pReportType);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        }
    }

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pReportType
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<NTDT_BCDC_05> BCDC_05(int pID_DOICHIEU_GD, String pReportType) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.BCDC_05_2019(pID_DOICHIEU_GD, pReportType);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pMST
     * @param pSTATUS
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<NTDT_searchNNT> SearchNNT(String pMST, String pSTATUS) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.SearchNNT(pMST, pSTATUS);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
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
    public String getIDThongBao() throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getIDThongBao();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param ref_id
     * @return
     * @throws Exception
     */
    public String getIDThongBaoDC(String ref_id) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getIDThongBaoDC(ref_id);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pID_GNT
     * @return
     * @throws Exception
     */
    public ArrayList getChungTuGNT(int pID_GNT) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getChungTuGNT(pID_GNT);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pID_GNT
     * @return
     * @throws Exception
     */
    public ArrayList getChungTuGNT_CT(int pID_GNT) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getChungTuGNT_CT(pID_GNT);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
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
    public ArrayList getAllThongBao() throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getAllThongBao();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pMST
     * @param pSTATUS
     * @param branchID
     * @param fromdate
     * @param todate
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<NTDT_searchNNT> SearchAllNNT(String pMST, String pSTATUS, String branchID, String fromdate, String todate) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.SearchAllNNT(pMST, pSTATUS, branchID, fromdate, todate);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
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
    public NTDT_searchNNT SearchIDNNT(String ID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.SearchIDNNT(ID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param P_DATE
     * @param P_TYPE
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public ArrayList<?> getListINS(Date P_DATE, String P_TYPE) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getListINS(P_DATE, P_TYPE);
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
     * @param P_DATE
     * @param P_TYPE
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public ArrayList<?> getListINS_BKN(Date P_DATE, String P_TYPE) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getListINS_BKN(P_DATE, P_TYPE);
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
     * @param P_DATE
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String working_day(Date P_DATE) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.working_day(P_DATE);
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
    public ArrayList getDetailDCbyID(String pID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDetailDCbyID(pID);
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
     * @param GNT
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_GNT2019(NTDT_GNT GNT) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_GNT2019(GNT);
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
     * @param pID_GNT
     * @param pID_CTU_CTIET
     * @param pID_CTU
     * @param pNDUNG_NOP
     * @param pMA_NDKT
     * @param pMA_CHUONG
     * @param pKY_THUE
     * @param pTIEN_PNOP
     * @param pGHI_CHU
     * @param pSO_TK_TB_QD
     * @throws RemoteException
     * @throws SQLException
     */
    public void INSERT_CHUNGTU_CHIIET2019(int pID_GNT,
            String pID_CTU_CTIET,
            String pID_CTU,
            String pNDUNG_NOP,
            String pMA_NDKT,
            String pMA_CHUONG,
            String pKY_THUE,
            String pTIEN_PNOP,
            String pGHI_CHU,
            String pSO_TK_TB_QD) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.INSERT_CHUNGTU_CHIIET2019(pID_GNT, pID_CTU_CTIET, pID_CTU, pNDUNG_NOP, pMA_NDKT, pMA_CHUONG, pKY_THUE, pTIEN_PNOP, pGHI_CHU, pSO_TK_TB_QD);
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
     * @param pID_DOICHIEU_GD
     * @param pMABAOCAO
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<NTDT_DOICHIEUGD> BAOCAODOICHIEU_2019(int pID_DOICHIEU_GD, String pMABAOCAO) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.BAOCAODOICHIEU_2019(pID_DOICHIEU_GD, pMABAOCAO);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pMA_GDICH
     * @param pNGAY_GUI_GDICH
     * @param pNGAY_NOP_GNT
     * @param pNGAY_NOP_THUE
     * @param pTTHAI_GDICH
     * @param pTTHAI_CTU
     * @param pMAHIEU_CTU
     * @param pSO_CTU
     * @param pPBAN_TLIEU_XML
     * @param pID_CTU
     * @param pSO_GNT
     * @param pMA_CTU
     * @param pHTHUC_NOP
     * @param pMST_NNOP
     * @param pTEN_NNOP
     * @param pMA_CQT
     * @param pTEN_CQT
     * @param pDIACHI_NNOP
     * @param pMA_XA_NNOP
     * @param pTEN_XA_NNOP
     * @param pMA_HUYEN_NNOP
     * @param pTEN_HUYEN_NNOP
     * @param pMA_TINH_NNOP
     * @param pTEN_TINH_NNOP
     * @param pMST_NTHAY
     * @param pTEN_NTHAY
     * @param pDIACHI_NTHAY
     * @param pTEN_HUYEN_NTHAY
     * @param pTEN_TINH_NTHAY
     * @param pMA_NHANG_NOP
     * @param pTEN_NHANG_NOP
     * @param pSTK_NHANG_NOP
     * @param pMA_HIEU_KBAC
     * @param pTEN_KBAC
     * @param pMA_TINH_KBAC
     * @param pTEN_TINH_KBAC
     * @param pLOAI_TK_THU
     * @param pTEN_TK_THU
     * @param pSTK_THU
     * @param pID_TK_KNGHI
     * @param pTK_KNGHI
     * @param pMA_CQTHU
     * @param pTEN_CQTHU
     * @param pNGAY_LAP
     * @param pTONG_TIEN
     * @param pVAN_ID
     * @param pMaNguyenTe
     * @param pMathamchieu
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public int INSERT_DOICHIEU_GNT2019(int pID_DOICHIEU_GD,
            String pMA_GDICH,
            String pNGAY_GUI_GDICH,
            String pNGAY_NOP_GNT,
            String pNGAY_NOP_THUE,
            String pTTHAI_GDICH,
            String pTTHAI_CTU,
            String pMAHIEU_CTU,
            String pSO_CTU,
            String pPBAN_TLIEU_XML,
            String pID_CTU,
            String pSO_GNT,
            String pMA_CTU,
            String pHTHUC_NOP,
            String pMST_NNOP,
            String pTEN_NNOP,
            String pMA_CQT,
            String pTEN_CQT,
            String pDIACHI_NNOP,
            String pMA_XA_NNOP,
            String pTEN_XA_NNOP,
            String pMA_HUYEN_NNOP,
            String pTEN_HUYEN_NNOP,
            String pMA_TINH_NNOP,
            String pTEN_TINH_NNOP,
            String pMST_NTHAY,
            String pTEN_NTHAY,
            String pDIACHI_NTHAY,
            String pTEN_HUYEN_NTHAY,
            String pTEN_TINH_NTHAY,
            String pMA_NHANG_NOP,
            String pTEN_NHANG_NOP,
            String pSTK_NHANG_NOP,
            String pMA_HIEU_KBAC,
            String pTEN_KBAC,
            String pMA_TINH_KBAC,
            String pTEN_TINH_KBAC,
            String pLOAI_TK_THU,
            String pTEN_TK_THU,
            String pSTK_THU,
            String pID_TK_KNGHI,
            String pTK_KNGHI,
            String pMA_CQTHU,
            String pTEN_CQTHU,
            String pNGAY_LAP,
            String pTONG_TIEN,
            String pVAN_ID,
            String pMaNguyenTe,
            String pMathamchieu) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_DOICHIEU_GNT_2019(pID_DOICHIEU_GD, pMA_GDICH, pNGAY_GUI_GDICH, pNGAY_NOP_GNT, pNGAY_NOP_THUE, pTTHAI_GDICH, pTTHAI_CTU, pMAHIEU_CTU, pSO_CTU, pPBAN_TLIEU_XML, pID_CTU, pSO_GNT, pMA_CTU, pHTHUC_NOP, pMST_NNOP, pTEN_NNOP, pMA_CQT, pTEN_CQT, pDIACHI_NNOP, pMA_XA_NNOP, pTEN_XA_NNOP, pMA_HUYEN_NNOP, pTEN_HUYEN_NNOP, pMA_TINH_NNOP, pTEN_TINH_NNOP, pMST_NTHAY, pTEN_NTHAY, pDIACHI_NTHAY, pTEN_HUYEN_NTHAY, pTEN_TINH_NTHAY, pMA_NHANG_NOP, pTEN_NHANG_NOP, pSTK_NHANG_NOP, pMA_HIEU_KBAC, pTEN_KBAC, pMA_TINH_KBAC, pTEN_TINH_KBAC, pLOAI_TK_THU, pTEN_TK_THU, pSTK_THU, pID_TK_KNGHI, pTK_KNGHI, pMA_CQTHU, pTEN_CQTHU, pNGAY_LAP, pTONG_TIEN, pVAN_ID, pMaNguyenTe, pMathamchieu);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList<?> NTDT_PERSONAL_PAYMENT(String sochungtu) throws SQLException {
         try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.get_NTDT_PERSONAL_PAYMENT(sochungtu);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList search_NTDT_PAYMENT_EXTEND(String refcore) throws SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.search_NTDT_PAYMENT_EXTEND(refcore);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
