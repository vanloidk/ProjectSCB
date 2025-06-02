/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dto.NTDT_BCDC_02;
import scb.com.vn.dbi.dto.NTDT_BCDC_05;
import scb.com.vn.dbi.dto.NTDT_DOICHIEUGD;
import scb.com.vn.dbi.dto.NTDT_GNT;
import scb.com.vn.dbi.dto.NTDT_NNT;
import scb.com.vn.dbi.dto.NTDT_TDSTK;
import scb.com.vn.dbi.dto.NTDT_searchNNT;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author LyDTY CreateDate: 19/Mar/2015
 */
public class NTDTDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(NTDTDAO.class);

    final String GET_TMP_GD_MANULIFE = "select * from tmp_gd_manulife";
    final String GET_TMP_SP_MANULIFE_BKN = "select * from tmp_sp_manulife";
    final String FN_GD_MANULIFE = "begin PROC_GD_MANULIFE(?,?); end;";
    final String FN_SP_MANULIFE = "begin PROC_SP_MANULIFE(?,?); end;";
    final String PROC_WORKING_DAY = "{ ? = call PROC_WORKING_DAY(?, ?)}";
    final String THONGTIN_NNT = "BEGIN NTDT.THONGTIN_NNT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String InsertAccountNo = "BEGIN NTDT.InsertAccountNo(?,?,?,?,?,?,?); END;";
    final String ApproveAccounts = "BEGIN NTDT.ApproveAccounts(?,?,?); END;";
    final String THONGBAO_THAYDOI_STK = "BEGIN NTDT.THONGBAO_THAYDOI_STK(?); END;";
    final String CloseNNT = "BEGIN NTDT.CloseNNT(?,?,?,?,?,?,?,?); END;";
    final String CheckTT_NNT = "BEGIN NTDT.CheckTT_NNT(?,?,?,?,?); END;";
    final String Confirm_TT_NNT = "BEGIN NTDT.Confirm_TT_NNT(?,?,?); END;";
    final String INSERT_GNT = "BEGIN NTDT.INSERT_GNT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    final String INSERT_CHUNGTU_CHIIET = "BEGIN NTDT.INSERT_CHUNGTU_CHIIET(?,?,?,?,?,?,?,?,?); END;";
    final String GUITHONGBAO = "BEGIN NTDT.GUITHONGBAO(?,?,?); END;";
    final String GET_ACCOUNNO = "BEGIN NTDT.GET_ACCOUNNO(?,?,?); END;";
    final String CONFIRMTHONGBAO = "BEGIN NTDT.CONFIRMTHONGBAO(?,?,?); END;";
    final String INSERT_DOICHIEU_GD = "BEGIN NTDT.INSERT_DOICHIEU_GD(?,?,?,?,?,?,?,?,?,?,?); END;";
    final String INSERT_DOICHIEU_GNT = "BEGIN NTDT.INSERT_DOICHIEU_GNT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String INSERT_DOICHIEU_GNT_CHITIET = "BEGIN NTDT.INSERT_DOICHIEU_GNT_CHITIET(?,?,?,?,?,?,?); END;";
    final String BAOCAODOICHIEU = "BEGIN NTDT.BAOCAODOICHIEU(?,?,?); END;";
    final String BAOCAODOICHIEU_CHITIET = "BEGIN NTDT.BAOCAODOICHIEU_CHITIET(?,?,?); END;";
    final String SearchNNT = "BEGIN NTDT.SearchNNT(?,?,?); END;";
    final String getIDThongBao = "select 'NTDT_SCB'||Seq_Ntdt_Thongbao.Nextval idthongbao from dual";
    final String getIDThongBaoDC = "select id from NTDT_THONGBAO where ID_REF=? and LOAITHONGBAO='DC'";
    final String getChungTuGNT = "BEGIN NTDT.getChungTuGNT(?,?); END;";
    final String getChungTuGNT_CT = "BEGIN NTDT.getChungTuGNT_CT(?,?); END;";
    final String getAllThongBao = "BEGIN NTDT.getAllThongBao(?); END;";
    final String SearchAllNNT = "BEGIN NTDT.SearchAllNNT(?,?,?,?,?,?); END;";
    final String SearchIDNNT = "BEGIN NTDT.SearchIDNNT(?,?); END;";
    //add new 2018 by LYDTY
    final String getdetaildcbyid = "BEGIN NTDT.getdetaildcbyid(?,?); END;";
    //add new 20189 by LYDTY
    final String INSERT_CHUNGTU_CHIIET2 = "BEGIN NTDT.INSERT_CHUNGTU_CHIIET2019(?,?,?,?,?,?,?,?,?,?); END;";
    final String INSERT_GNT2 = "BEGIN NTDT.INSERT_GNT2019(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String INSERT_DOICHIEU_GNT2 = "BEGIN NTDT.INSERT_DOICHIEU_GNT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    //Baotbq - Huychungtu - 8/9/2022
    final String getNtdtPersonalPayment = "select * from NTDT_PERSONAL_PAYMENT t where t.refcore =?";

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
            String pCKS) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(THONGTIN_NNT);
            calStmt.setString(1, pMA_GDICH);
            calStmt.setString(2, pNGAY_GUI_GDICH);
            calStmt.setString(3, pMA_GDICH_TCHIEU);
            calStmt.setString(4, pPBAN_TLIEU_XML);
            calStmt.setString(5, pMST);
            calStmt.setString(6, pTEN_NNT);
            calStmt.setString(7, pDIACHI_NNT);
            calStmt.setString(8, pMA_CQT);
            calStmt.setString(9, pEMAIL_NNT);
            calStmt.setString(10, pSDT_NNT);
            calStmt.setString(11, pTEN_LHE_NTHUE);
            calStmt.setString(12, pSERIAL_CERT_NTHUE);
            calStmt.setString(13, pSUBJECT_CERT_NTHUE);
            calStmt.setString(14, pISSUER_CERT_NTHUE);
            calStmt.setString(15, pMA_NHANG);
            calStmt.setString(16, pTEN_NHANG);
            calStmt.setString(17, pVAN_ID);
            calStmt.setString(18, pTEN_TVAN);
            calStmt.setString(19, pNGAY_GUI);
            calStmt.setString(20, pLOAITHONGTIN);
            calStmt.setString(21, pMSGID);
            calStmt.setString(22, pTRANSCODE);
            calStmt.setString(23, pCKS);
            calStmt.registerOutParameter(24, Types.NUMERIC); //pID_NNT
            calStmt.registerOutParameter(25, Types.NUMERIC); //pID_TT_NNT
            calStmt.registerOutParameter(26, Types.VARCHAR); //pStatus
            calStmt.execute();
            String[] arrResult = new String[3];
            arrResult[0] = calStmt.getString(24);
            arrResult[1] = calStmt.getString(25);
            arrResult[2] = calStmt.getString(26);
            return arrResult;
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
     */
    public String InsertAccountNo(String pAccountNo,
            int pID_NNT,
            int pIsDeleted,
            String pAddinfo,
            String pUSERID,
            String pBRANCHID
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(InsertAccountNo);
            calStmt.setString(1, pAccountNo);
            calStmt.setInt(2, pID_NNT);
            calStmt.setInt(3, pIsDeleted);
            calStmt.setString(4, pAddinfo);
            calStmt.setString(5, pUSERID);
            calStmt.setString(6, pBRANCHID);
            calStmt.registerOutParameter(7, Types.VARCHAR); //pStatus
            calStmt.execute();
            return calStmt.getString(7);
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
     * @param pID_NNT
     * @throws SQLException
     */
    public void THONGBAO_THAYDOI_STK(int pID_NNT) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(THONGBAO_THAYDOI_STK);
            calStmt.setInt(1, pID_NNT);
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
     * @param pMST
     * @param pLYDONGUNG
     * @param pCLOSEDATE
     * @param MA_TBAO
     * @param MAU_TBAO
     * @param SO_TBAO
     * @param TEN_TBAO
     * @param NGAY_TBAO
     * @throws SQLException
     */
    public void CloseNNT(String pMST,
            String pLYDONGUNG,
            String pCLOSEDATE,
            String MA_TBAO,
            String MAU_TBAO,
            String SO_TBAO,
            String TEN_TBAO,
            String NGAY_TBAO) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(CloseNNT);
            calStmt.setString(1, pMST);
            calStmt.setString(2, pLYDONGUNG);
            calStmt.setString(3, pCLOSEDATE);
            calStmt.setString(4, MA_TBAO);
            calStmt.setString(5, MAU_TBAO);
            calStmt.setString(6, SO_TBAO);
            calStmt.setString(7, TEN_TBAO);
            calStmt.setString(8, NGAY_TBAO);
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
     * @param pID_TT_NNT
     * @param pCheckStatus
     * @param pAddinfo
     * @param pUserUD
     * @param pBranchID
     * @throws SQLException
     */
    public void CheckTT_NNT(int pID_TT_NNT,
            String pCheckStatus,
            String pAddinfo,
            String pUserUD,
            String pBranchID) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(CheckTT_NNT);
            calStmt.setInt(1, pID_TT_NNT);
            calStmt.setString(2, pCheckStatus);
            calStmt.setString(3, pAddinfo);
            calStmt.setString(4, pUserUD);
            calStmt.setString(5, pBranchID);
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
     * @param pID_TT_NNT
     * @param pUserUD
     * @param pID_NNT
     * @throws SQLException
     */
    public void Confirm_TT_NNT(int pID_TT_NNT,
            String pUserUD,
            int pID_NNT) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(Confirm_TT_NNT);
            calStmt.setInt(1, pID_TT_NNT);
            calStmt.setString(2, pUserUD);
            calStmt.setInt(3, pID_NNT);
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
     * @param pTransCode
     * @param pCKS
     * @return
     * @throws SQLException
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
            String pTransCode,
            String pCKS) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(INSERT_GNT);
            calStmt.setString(1, pMA_GDICH);
            calStmt.setString(2, pNGAY_GUI_GDICH);
            calStmt.setString(3, pMA_GDICH_TCHIEU);

            calStmt.setString(4, pMAHIEU_CTU);
            calStmt.setString(5, pSO_CTU);
            calStmt.setString(6, pPBAN_TLIEU_XML);
            calStmt.setString(7, pID_CTU);
            calStmt.setString(8, pSO_GNT);
            calStmt.setString(9, pMA_CTU);

            calStmt.setString(10, pHTHUC_NOP);
            calStmt.setString(11, pMST_NNOP);
            calStmt.setString(12, pTEN_NNOP);
            calStmt.setString(13, pDIACHI_NNOP);
            calStmt.setString(14, pMA_CQT);
            calStmt.setString(15, pTEN_CQT);

            calStmt.setString(16, pMA_XA_NNOP);
            calStmt.setString(17, pTEN_XA_NNOP);
            calStmt.setString(18, pMA_HUYEN_NNOP);
            calStmt.setString(19, pTEN_HUYEN_NNOP);
            calStmt.setString(20, pMA_TINH_NNOP);
            calStmt.setString(21, pTEN_TINH_NNOP);

            calStmt.setString(22, pMST_NTHAY);
            calStmt.setString(23, pTEN_NTHAY);
            calStmt.setString(24, pDIACHI_NTHAY);
            calStmt.setString(25, pTEN_HUYEN_NTHAY);
            calStmt.setString(26, pTEN_TINH_NTHAY);

            calStmt.setString(27, pMA_NHANG_NOP);
            calStmt.setString(28, pTEN_NHANG_NOP);
            calStmt.setString(29, pSTK_NHANG_NOP);

            calStmt.setString(30, pMA_HIEU_KBAC);
            calStmt.setString(31, pTEN_KBAC);
            calStmt.setString(32, pMA_TINH_KBAC);
            calStmt.setString(33, pTEN_TINH_KBAC);

            calStmt.setString(34, pLOAI_TK_THU);
            calStmt.setString(35, pTEN_TK_THU);
            calStmt.setString(36, pSTK_THU);

            calStmt.setString(37, pID_TK_KNGHI);
            calStmt.setString(38, pTK_KNGHI);

            calStmt.setString(39, pMA_CQTHU);
            calStmt.setString(40, pTEN_CQTHU);

            calStmt.setString(41, pNGAY_LAP);
            calStmt.setString(42, pTONG_TIEN);
            calStmt.setString(43, pVAN_ID);
            calStmt.setString(44, pMA_DBHC_THU);
            calStmt.setString(45, pTEN_DBHC_THU);
            calStmt.setString(46, pMA_LOAI_THUE);
            calStmt.setString(47, pMSGID);
            calStmt.setString(48, pTransCode);
            calStmt.setString(49, pCKS);

            calStmt.registerOutParameter(50, Types.NUMERIC); //pID_GNT
            calStmt.registerOutParameter(51, Types.VARCHAR); //pStatus

            calStmt.execute();

            String[] arrResult = new String[2];
            arrResult[0] = String.valueOf(calStmt.getInt(50));
            arrResult[1] = calStmt.getString(51);
            return arrResult;
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
     */
    public void INSERT_CHUNGTU_CHIIET(int pID_GNT,
            String pID_CTU_CTIET,
            String pID_CTU,
            String pNDUNG_NOP,
            String pMA_NDKT,
            String pMA_CHUONG,
            String pKY_THUE,
            String pTIEN_PNOP,
            String pGHI_CHU) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(INSERT_CHUNGTU_CHIIET);
            calStmt.setInt(1, pID_GNT);
            calStmt.setString(2, pID_CTU_CTIET);
            calStmt.setString(3, pID_CTU);
            calStmt.setString(4, pNDUNG_NOP);
            calStmt.setString(5, pMA_NDKT);
            calStmt.setString(6, pMA_CHUONG);
            calStmt.setString(7, pKY_THUE);
            calStmt.setString(8, pTIEN_PNOP);
            calStmt.setString(9, pGHI_CHU);
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
     * @param pID_THONGBAO
     * @param pMESS_CODE
     * @param pMESS_CONTENT
     * @throws SQLException
     */
    public void CONFIRMTHONGBAO(String pID_THONGBAO,
            String pMESS_CODE,
            String pMESS_CONTENT) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(CONFIRMTHONGBAO);
            calStmt.setString(1, pID_THONGBAO);
            calStmt.setString(2, pMESS_CODE);
            calStmt.setString(3, pMESS_CONTENT);
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
     * @param pID_NNT
     * @param pIsApproved
     * @return
     * @throws SQLException
     */
    public ArrayList GET_ACCOUNNO(int pID_NNT, int pIsApproved) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(GET_ACCOUNNO);
            calStmt.setInt(1, pID_NNT);
            calStmt.setInt(2, pIsApproved);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();

            rs = (ResultSet) calStmt.getObject(3);
            int numcols = rs.getMetaData().getColumnCount();
            ArrayList result = new ArrayList();

            while (rs.next()) {
                List row = new ArrayList();
                for (int i = 1; i <= numcols; i++) {  // don't skip the last column, use <=
                    row.add(rs.getString(i));
                }
                result.add(row); // add it to the result
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

    /**
     *
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws SQLException
     */
    public List<NTDT_NNT> GUITHONGBAO(String pLOAITHONGBAO, int pID_REF) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(GUITHONGBAO);
            calStmt.setString(1, pLOAITHONGBAO);
            calStmt.setInt(2, pID_REF);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(3);
            List<NTDT_NNT> resultList = new ArrayList<NTDT_NNT>();
            while (rs.next()) {
                NTDT_NNT obj = new NTDT_NNT();
                obj.setMA_GDICH(rs.getString("MA_GDICH"));
                obj.setNGAY_GUI_GDICH(rs.getString("NGAY_GUI_GDICH"));
                obj.setMA_GDICH_TCHIEU(rs.getString("MA_GDICH_TCHIEU"));
                obj.setPBAN_TLIEU_XML(rs.getString("PBAN_TLIEU_XML"));
                obj.setMA_TBAO(rs.getString("MA_TBAO"));
                obj.setMAU_TBAO(rs.getString("MAU_TBAO"));
                obj.setSO_TBAO(rs.getString("SO_TBAO"));
                obj.setTEN_TBAO(rs.getString("TEN_TBAO"));
                obj.setNGAY_TBAO(rs.getString("NGAY_TBAO"));
                obj.setMST(rs.getString("MST"));
                obj.setTEN_NNT(rs.getString("TEN_NNT"));
                obj.setDIACHI_NNT(rs.getString("DIACHI_NNT"));
                obj.setMA_CQT(rs.getString("MA_CQT"));
                obj.setEMAIL_NNT(rs.getString("EMAIL_NNT"));
                obj.setSDT_NNT(rs.getString("SDT_NNT"));
                obj.setTEN_LHE_NTHUE(rs.getString("TEN_LHE_NTHUE"));
                obj.setSERIAL_CERT_NTHUE(rs.getString("SERIAL_CERT_NTHUE"));
                obj.setSubject_Cert_Nthue(rs.getString("Subject_Cert_Nthue"));
                obj.setISSUER_CERT_NTHUE(rs.getString("ISSUER_CERT_NTHUE"));
                obj.setMA_NHANG(rs.getString("MA_NHANG"));
                obj.setTEN_NHANG(rs.getString("TEN_NHANG"));
                obj.setLDO_TCHOI(rs.getString("LDO_TCHOI"));
                obj.setTEN_TVAN(rs.getString("TEN_TVAN"));
                obj.setTTHAI_XNHAN(rs.getString("TTHAI_XNHAN"));
                obj.setVAN_ID(rs.getString("VAN_ID"));
                obj.setMSGID(rs.getString("REF_MSGID"));
                obj.setLISTMAGD(rs.getString("LISTMAGD"));
                resultList.add(obj);
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
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws SQLException
     */
    public List<NTDT_GNT> GUITHONGBAO_GNT_OLD(String pLOAITHONGBAO, int pID_REF) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(GUITHONGBAO);
            calStmt.setString(1, pLOAITHONGBAO);
            calStmt.setInt(2, pID_REF);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(3);
            List<NTDT_GNT> resultList = new ArrayList<NTDT_GNT>();
            while (rs.next()) {
                NTDT_GNT obj = new NTDT_GNT();
                obj.setMA_GDICH(rs.getString("MA_GDICH"));
                obj.setNGAY_GUI_GDICH(rs.getString("NGAY_GUI_GDICH"));
                obj.setMA_GDICH_TCHIEU(rs.getString("MA_GDICH_TCHIEU"));
                obj.setPBAN_TLIEU_XML(rs.getString("PBAN_TLIEU_XML"));
                obj.setMA_THONGBAO(rs.getString("MA_THONGBAO"));
                obj.setMAU_THONGBAO(rs.getString("MAU_THONGBAO"));
                obj.setTEN_THONGBAO(rs.getString("TEN_THONGBAO"));
                obj.setSO_THONGBAO(rs.getString("SO_THONGBAO"));
                obj.setNGAY_THONGBAO(rs.getString("NGAY_THONGBAO"));
                obj.setMST_NNOP(rs.getString("MST_NNOP"));
                obj.setTEN_NNOP(rs.getString("TEN_NNOP"));
                obj.setMA_CQT(rs.getString("MA_CQT"));
                obj.setMST_NTHAY(rs.getString("MST_NTHAY"));
                obj.setTEN_NTHAY(rs.getString("Ten_Nthay"));
                obj.setID_CHUNGTU(rs.getString("ID_CHUNGTU"));
                obj.setSO_GNT(rs.getString("SO_GNT"));
                obj.setMAHIEU_CTU(rs.getString("MAHIEU_CTU"));
                obj.setSO_CTU(rs.getString("So_Ctu"));
                obj.setSHIEU_KHOBAC_NOP(rs.getString("SHIEU_KHOBAC_NOP"));
                obj.setTEN_KHOBAC_NOP(rs.getString("TEN_KHOBAC_NOP"));
                obj.setSTK_KHOBAC_NOP(rs.getString("STK_KHOBAC_NOP"));
                obj.setMA_NHANG_NOP(rs.getString("Ma_Nhang_Nop"));
                obj.setSTK_NHANG_NOP(rs.getString("Stk_Nhang_Nop"));
                obj.setTONGSOMON(rs.getString("TONGSOMON"));
                obj.setTONG_TIEN(rs.getString("Tong_Tien"));
                obj.setPHI(rs.getString("PHI"));
                obj.setNGAYXULY(rs.getString("NGAYXULY"));
                obj.setMA_TTHAI(rs.getString("MA_TTHAI"));
                obj.setTEN_TTHAI(rs.getString("TEN_TTHAI"));
                obj.setMSGID(rs.getString("REF_MSGID"));
                obj.setLISTMAGD(rs.getString("LISTMAGD"));
                resultList.add(obj);
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
            String pTRANSCODE) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(INSERT_DOICHIEU_GD);
            calStmt.setString(1, pPBAN_TLIEU_XML_DC);
            calStmt.setString(2, pMA_GDICH_DC);
            calStmt.setString(3, pNGAY_GDICH_DC);
            calStmt.setString(4, pMA_NHANG_DC);
            calStmt.setString(5, pNGAY_DC);
            calStmt.setString(6, pTU_NGAY_DC);
            calStmt.setString(7, pDEN_NGAY_DC);
            calStmt.setString(8, pMA_GDICH_TCHIEU_DC);
            calStmt.setString(9, pMSGID);
            calStmt.setString(10, pTRANSCODE);
            calStmt.registerOutParameter(11, OracleTypes.INTEGER); //pID_NNT
            calStmt.execute();
            return calStmt.getInt(11);
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
            String pVAN_ID) throws SQLException {

        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(INSERT_DOICHIEU_GNT);
            calStmt.setInt(1, pID_DOICHIEU_GD);
            calStmt.setString(2, pMA_GDICH);
            calStmt.setString(3, pNGAY_GUI_GDICH);

            calStmt.setString(4, pNGAY_NOP_GNT);
            calStmt.setString(5, pNGAY_NOP_THUE);
            calStmt.setString(6, pTTHAI_GDICH);
            calStmt.setString(7, pTTHAI_CTU);
            calStmt.setString(8, pMAHIEU_CTU);
            calStmt.setString(9, pSO_CTU);
            calStmt.setString(10, pPBAN_TLIEU_XML);

            calStmt.setString(11, pID_CTU);
            calStmt.setString(12, pSO_GNT);
            calStmt.setString(13, pMA_CTU);
            calStmt.setString(14, pHTHUC_NOP);
            calStmt.setString(15, pMST_NNOP);

            calStmt.setString(16, pTEN_NNOP);
            calStmt.setString(17, pMA_CQT);
            calStmt.setString(18, pTEN_CQT);
            calStmt.setString(19, pDIACHI_NNOP);
            calStmt.setString(20, pMA_XA_NNOP);
            calStmt.setString(21, pTEN_XA_NNOP);

            calStmt.setString(22, pMA_HUYEN_NNOP);
            calStmt.setString(23, pTEN_HUYEN_NNOP);
            calStmt.setString(24, pMA_TINH_NNOP);
            calStmt.setString(25, pTEN_TINH_NNOP);
            calStmt.setString(26, pMST_NTHAY);

            calStmt.setString(27, pTEN_NTHAY);
            calStmt.setString(28, pDIACHI_NTHAY);
            calStmt.setString(29, pTEN_HUYEN_NTHAY);

            calStmt.setString(30, pTEN_TINH_NTHAY);
            calStmt.setString(31, pMA_NHANG_NOP);
            calStmt.setString(32, pTEN_NHANG_NOP);
            calStmt.setString(33, pSTK_NHANG_NOP);

            calStmt.setString(34, pMA_HIEU_KBAC);
            calStmt.setString(35, pTEN_KBAC);
            calStmt.setString(36, pMA_TINH_KBAC);

            calStmt.setString(37, pTEN_TINH_KBAC);
            calStmt.setString(38, pLOAI_TK_THU);

            calStmt.setString(39, pTEN_TK_THU);
            calStmt.setString(40, pSTK_THU);

            calStmt.setString(41, pID_TK_KNGHI);
            calStmt.setString(42, pTK_KNGHI);
            calStmt.setString(43, pMA_CQTHU);
            calStmt.setString(44, pTEN_CQTHU);
            calStmt.setString(45, pNGAY_LAP);
            calStmt.setString(46, pTONG_TIEN);
            calStmt.setString(47, pVAN_ID);

            calStmt.registerOutParameter(48, Types.INTEGER); //pStatus

            calStmt.execute();

            return calStmt.getInt(48);
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
     * @param pID_CTU_CTIET
     * @param pID_CTU
     * @param pNDUNG_NOP
     * @param pMA_NDKT
     * @param pMA_CHUONG
     * @param pTIEN_PNOP
     * @param pID_DOICHIEU_GNT
     * @throws SQLException
     */
    public void INSERT_DOICHIEU_GNT_CHITIET(String pID_CTU_CTIET,
            String pID_CTU,
            String pNDUNG_NOP,
            String pMA_NDKT,
            String pMA_CHUONG,
            String pTIEN_PNOP,
            int pID_DOICHIEU_GNT) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(INSERT_DOICHIEU_GNT_CHITIET);
            calStmt.setString(1, pID_CTU_CTIET);
            calStmt.setString(2, pID_CTU);
            calStmt.setString(3, pNDUNG_NOP);
            calStmt.setString(4, pMA_NDKT);
            calStmt.setString(5, pMA_CHUONG);
            calStmt.setString(6, pTIEN_PNOP);
            calStmt.setInt(7, pID_DOICHIEU_GNT);
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
     * @param pID_DOICHIEU_GD
     * @param pMABAOCAO
     * @return
     * @throws SQLException
     */
    public List<NTDT_DOICHIEUGD> BAOCAODOICHIEU(int pID_DOICHIEU_GD, String pMABAOCAO) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(BAOCAODOICHIEU);
            calStmt.setInt(1, pID_DOICHIEU_GD);
            calStmt.setString(2, pMABAOCAO);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(3);
            List<NTDT_DOICHIEUGD> resultList = new ArrayList<NTDT_DOICHIEUGD>();
            while (rs.next()) {
                NTDT_DOICHIEUGD obj = new NTDT_DOICHIEUGD();
                obj.setPBAN_TLIEU_XML(rs.getString("PBAN_TLIEU_XML"));
                obj.setMA_BCAO(rs.getString("MA_BCAO"));
                obj.setTEN_BCAO(rs.getString("TEN_BCAO"));
                obj.setTGIAN_DCHIEU(rs.getString("TGIAN_DCHIEU"));
                obj.setMA_NHANG_DCHIEU(rs.getString("MA_NHANG_DCHIEU"));
                obj.setTEN_NHANG_DCHIEU(rs.getString("TEN_NHANG_DCHIEU"));
                obj.setTONGDIEN_LECH(rs.getString("TONGDIEN_LECH"));
                obj.setTONGTIEN_LECH(rs.getString("TONGTIEN_LECH"));
                obj.setTONGDIEN_TCTCO_NHTMKO(rs.getString("TONGDIEN_TCTCO_NHTMKO"));
                obj.setTONGTIEN_TCTCO_NHTMKO(rs.getString("TONGTIEN_TCTCO_NHTMKO"));
                obj.setTONGDIEN_NHTMCO_TCTKO(rs.getString("TONGDIEN_NHTMCO_TCTKO"));
                obj.setTONGTIEN_NHTMCO_TCTKO(rs.getString("TONGTIEN_NHTMCO_TCTKO"));
                obj.setTONGDIEN_LECH_TTHAI(rs.getString("TONGDIEN_LECH_TTHAI"));
                obj.setTONGTIEN_LECH_TTHAI(rs.getString("TONGTIEN_LECH_TTHAI"));
                resultList.add(obj); // add it to the result
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
     * @param pID_DOICHIEU_GD
     * @param pReportType
     * @return
     * @throws SQLException
     */
    public List<NTDT_BCDC_02> BCDC_02(int pID_DOICHIEU_GD, String pReportType) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(BAOCAODOICHIEU_CHITIET);
            calStmt.setInt(1, pID_DOICHIEU_GD);
            calStmt.setString(2, pReportType);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.execute();

            rs = (ResultSet) calStmt.getObject(3);
            List<NTDT_BCDC_02> resultList = new ArrayList<NTDT_BCDC_02>();

            while (rs.next()) {
                NTDT_BCDC_02 obj = new NTDT_BCDC_02();
                obj.setSO_CTU(rs.getString("SO_CTU"));
                obj.setMAHIEU_CTU(rs.getString("MAHIEU_CTU"));
                obj.setSO_GNT(rs.getString("SO_GNT"));
                obj.setTEN_NNT(rs.getString("TEN_NNT"));
                obj.setMST(rs.getString("MST"));
                obj.setMa_Cqt(rs.getString("Ma_Cqt"));
                obj.setMa_Cqthu(rs.getString("Ma_Cqthu"));
                obj.setNgay_Nop_Gnt(rs.getString("Ngay_Nop_Gnt"));
                obj.setNgay_Nop_Thue(rs.getString("Ngay_Nop_Thue"));
                obj.setTGIAN_TCT(rs.getString("TGIAN_TCT"));
                obj.setTGIAN_NHTM(rs.getString("TGIAN_NHTM"));
                obj.setSO_TIEN(rs.getString("SO_TIEN"));
                obj.setTTHAI_TCT(rs.getString("TTHAI_TCT"));
                obj.setTTHAI_NHTM(rs.getString("TTHAI_NHTM"));
                resultList.add(obj); // add it to the result
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
     * @param pID_DOICHIEU_GD
     * @param pReportType
     * @return
     * @throws SQLException
     */
    public List<NTDT_BCDC_05> BCDC_05(int pID_DOICHIEU_GD, String pReportType) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(BAOCAODOICHIEU_CHITIET);
            calStmt.setInt(1, pID_DOICHIEU_GD);
            calStmt.setString(2, pReportType);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.execute();

            rs = (ResultSet) calStmt.getObject(3);
            List<NTDT_BCDC_05> resultList = new ArrayList<NTDT_BCDC_05>();

            while (rs.next()) {
                NTDT_BCDC_05 obj = new NTDT_BCDC_05();
                obj.setSO_CTU(rs.getString("SO_CTU"));
                obj.setMAHIEU_CTU(rs.getString("MAHIEU_CTU"));
                obj.setSO_GNT(rs.getString("SO_GNT"));
                obj.setTEN_NNT(rs.getString("TEN_NNT"));
                obj.setMST(rs.getString("MST"));
                obj.setMa_Cqt(rs.getString("Ma_Cqt"));
                obj.setMa_Cqthu(rs.getString("Ma_Cqthu"));
                obj.setNgay_Nop_Gnt(rs.getString("Ngay_Nop_Gnt"));
                obj.setNgay_Nop_Thue(rs.getString("Ngay_Nop_Thue"));
                obj.setMA_KBNN(rs.getString("MA_KBNN"));
                obj.setTCT_MA_CHUONG(rs.getString("TCT_MA_CHUONG"));
                obj.setTCT_MA_TMUC(rs.getString("TCT_MA_TMUC"));
                obj.setTCT_SO_TIEN(rs.getString("TCT_SO_TIEN"));
                obj.setTCT_TTHAI(rs.getString("TCT_TTHAI"));
                obj.setNHTM_MA_CHUONG(rs.getString("NHTM_MA_CHUONG"));
                obj.setNHTM_MA_TMUC(rs.getString("NHTM_MA_TMUC"));
                obj.setNHTM_SO_TIEN(rs.getString("NHTM_SO_TIEN"));
                obj.setNHTM_TTHAI(rs.getString("NHTM_TTHAI"));
                resultList.add(obj); // add it to the result
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
     * @param pMST
     * @param pSTATUS
     * @return
     * @throws SQLException
     */
    public List<NTDT_searchNNT> SearchNNT(String pMST, String pSTATUS) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(SearchNNT);
            calStmt.setString(1, pMST);
            calStmt.setString(2, pSTATUS);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(3);
            int numcols = rs.getMetaData().getColumnCount();
            List<NTDT_searchNNT> resultList = new ArrayList<NTDT_searchNNT>();
            while (rs.next()) {
                NTDT_searchNNT obj = new NTDT_searchNNT();
                obj.setMST(rs.getString("MST"));
                obj.setTEN_NNT(rs.getString("TEN_NNT"));
                obj.setDiachi_Nnt(rs.getString("Diachi_Nnt"));
                obj.setMA_CQT(rs.getString("MA_CQT"));
                obj.setEMAIL_NNT(rs.getString("EMAIL_NNT"));
                obj.setSDT_NNT(rs.getString("SDT_NNT"));
                obj.setTen_Lhe_Nthue(rs.getString("Ten_Lhe_Nthue"));
                obj.setSERIAL_CERT_NTHUE(rs.getString("SERIAL_CERT_NTHUE"));
                obj.setSUBJECT_CERT_NTHUE(rs.getString("SUBJECT_CERT_NTHUE"));
                obj.setISSUER_CERT_NTHUE(rs.getString("ISSUER_CERT_NTHUE"));
                obj.setCreatedate(rs.getString("Createdate"));
                obj.setClosedate(rs.getString("Closedate"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setID(rs.getString("ID"));
                obj.setID_NNT(rs.getString("ID_NNT"));
                obj.setREASONCONFIRM(rs.getString("REASONCONFIRM"));
                obj.setBranchid(rs.getString("Branchid"));
                obj.setCHECKSTATUS(rs.getString("CHECKSTATUS"));
                resultList.add(obj);
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
     * @return @throws Exception
     */
    public String getIDThongBao() throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        ArrayList List = JDBCEngine.executeQuery(getIDThongBao, p_args, connection);
        HashMap<?, ?> HM = (HashMap<?, ?>) List.get(0);
        return HM.get("idthongbao").toString();
    }

    /**
     *
     * @param ID_REF
     * @return
     * @throws Exception
     */
    public String getIDThongBaoDC(String ID_REF) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(ID_REF);
        ArrayList List = JDBCEngine.executeQuery(getIDThongBaoDC, p_args, connection);
        HashMap<?, ?> HM = (HashMap<?, ?>) List.get(0);
        return HM.get("id").toString();
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
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(ApproveAccounts);
            calStmt.setInt(1, pID_NNT);
            calStmt.setString(2, pUserApprove);
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
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws SQLException
     */
    public List<NTDT_TDSTK> GUITHONGBAO_STK(String pLOAITHONGBAO, int pID_REF) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(GUITHONGBAO);
            calStmt.setString(1, pLOAITHONGBAO);
            calStmt.setInt(2, pID_REF);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(3);
            List<NTDT_TDSTK> resultList = new ArrayList<NTDT_TDSTK>();
            while (rs.next()) {
                NTDT_TDSTK obj = new NTDT_TDSTK();
                obj.setMA_GDICH(rs.getString("MA_GDICH"));
                obj.setNGAY_GUI_GDICH(rs.getString("NGAY_GUI_GDICH"));
                obj.setMA_GDICH_TCHIEU(rs.getString("MA_GDICH_TCHIEU"));
                obj.setPBAN_TLIEU_XML(rs.getString("PBAN_TLIEU_XML"));
                obj.setMST(rs.getString("MST"));
                obj.setTEN_NNT(rs.getString("TEN_NNT"));
                obj.setDIACHI_NNT(rs.getString("DIACHI_NNT"));
                obj.setMA_CQT(rs.getString("MA_CQT"));
                obj.setEMAIL_NNT(rs.getString("EMAIL_NNT"));
                obj.setSDT_NNT(rs.getString("SDT_NNT"));
                obj.setTEN_LHE_NTHUE(rs.getString("TEN_LHE_NTHUE"));
                obj.setSERIAL_CERT_NTHUE(rs.getString("SERIAL_CERT_NTHUE"));
                obj.setSubject_Cert_Nthue(rs.getString("Subject_Cert_Nthue"));
                obj.setISSUER_CERT_NTHUE(rs.getString("ISSUER_CERT_NTHUE"));
                obj.setMA_NHANG(rs.getString("MA_NHANG"));
                obj.setTEN_NHANG(rs.getString("TEN_NHANG"));
                obj.setVAN_ID(rs.getString("VAN_ID"));
                obj.setTEN_TVAN(rs.getString("TEN_TVAN"));
                obj.setREF_MSGID(rs.getString("REF_MSGID"));
                obj.setLISTMAGD(rs.getString("LISTMAGD"));
                resultList.add(obj);
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
     * @param pID_GNT
     * @return
     * @throws SQLException
     */
    public ArrayList getChungTuGNT(int pID_GNT) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(getChungTuGNT);
            calStmt.setInt(1, pID_GNT);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(2);
            int numcols = rs.getMetaData().getColumnCount();
            ArrayList result = new ArrayList();
            while (rs.next()) {
                List row = new ArrayList();
                for (int i = 1; i <= numcols; i++) {  // don't skip the last column, use <=
                    row.add(rs.getString(i));
                }
                result.add(row); // add it to the result
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

    /**
     *
     * @param pID_GNT
     * @return
     * @throws SQLException
     */
    public ArrayList getChungTuGNT_CT(int pID_GNT) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(getChungTuGNT_CT);
            calStmt.setInt(1, pID_GNT);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(2);
            int numcols = rs.getMetaData().getColumnCount();
            ArrayList result = new ArrayList();
            while (rs.next()) {
                List row = new ArrayList();
                for (int i = 1; i <= numcols; i++) {  // don't skip the last column, use <=
                    row.add(rs.getString(i));
                }
                result.add(row); // add it to the result
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

    /**
     *
     * @return @throws SQLException
     */
    public ArrayList getAllThongBao() throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(getAllThongBao);
            calStmt.registerOutParameter(1, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(1);
            int numcols = rs.getMetaData().getColumnCount();
            ArrayList result = new ArrayList();
            while (rs.next()) {
                List row = new ArrayList();
                for (int i = 1; i <= numcols; i++) {  // don't skip the last column, use <=
                    row.add(rs.getString(i));
                }
                result.add(row); // add it to the result
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

    /**
     *
     * @param pMST
     * @param pSTATUS
     * @param branchID
     * @param fromdate
     * @param todate
     * @return
     * @throws SQLException
     */
    public List<NTDT_searchNNT> SearchAllNNT(String pMST, String pSTATUS, String branchID, String fromdate, String todate) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(SearchAllNNT);
            calStmt.setString(1, pMST);
            calStmt.setString(2, pSTATUS);
            calStmt.setString(3, branchID);
            calStmt.setString(4, fromdate);
            calStmt.setString(5, todate);
            calStmt.registerOutParameter(6, OracleTypes.CURSOR);
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(6);
            int numcols = rs.getMetaData().getColumnCount();
            List<NTDT_searchNNT> resultList = new ArrayList<NTDT_searchNNT>();
            while (rs.next()) {
                NTDT_searchNNT obj = new NTDT_searchNNT();
                obj.setMST(rs.getString("MST"));
                obj.setTEN_NNT(rs.getString("TEN_NNT"));
                obj.setDiachi_Nnt(rs.getString("Diachi_Nnt"));
                obj.setMA_CQT(rs.getString("MA_CQT"));
                obj.setEMAIL_NNT(rs.getString("EMAIL_NNT"));
                obj.setSDT_NNT(rs.getString("SDT_NNT"));
                obj.setTen_Lhe_Nthue(rs.getString("Ten_Lhe_Nthue"));
                obj.setSERIAL_CERT_NTHUE(rs.getString("SERIAL_CERT_NTHUE"));
                obj.setSUBJECT_CERT_NTHUE(rs.getString("SUBJECT_CERT_NTHUE"));
                obj.setISSUER_CERT_NTHUE(rs.getString("ISSUER_CERT_NTHUE"));
                obj.setCreatedate(rs.getString("Createdate"));
                obj.setClosedate(rs.getString("Closedate"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setID(rs.getString("ID"));
                obj.setID_NNT(rs.getString("ID_NNT"));
                obj.setREASONCONFIRM(rs.getString("REASONCONFIRM"));
                obj.setBranchid(rs.getString("Branchid"));
                resultList.add(obj);
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
     * @param ID
     * @return
     * @throws SQLException
     */
    public NTDT_searchNNT SearchIDNNT(String ID) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(SearchIDNNT);
            calStmt.setString(1, ID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(2);
            NTDT_searchNNT obj = null;
            while (rs.next()) {
                obj = new NTDT_searchNNT();
                obj.setMST(rs.getString("MST"));
                obj.setTEN_NNT(rs.getString("TEN_NNT"));
                obj.setDiachi_Nnt(rs.getString("Diachi_Nnt"));
                obj.setMA_CQT(rs.getString("MA_CQT"));
                obj.setEMAIL_NNT(rs.getString("EMAIL_NNT"));
                obj.setSDT_NNT(rs.getString("SDT_NNT"));
                obj.setTen_Lhe_Nthue(rs.getString("Ten_Lhe_Nthue"));
                obj.setSERIAL_CERT_NTHUE(rs.getString("SERIAL_CERT_NTHUE"));
                obj.setSUBJECT_CERT_NTHUE(rs.getString("SUBJECT_CERT_NTHUE"));
                obj.setISSUER_CERT_NTHUE(rs.getString("ISSUER_CERT_NTHUE"));
                obj.setCreatedate(rs.getString("Createdate"));
                obj.setClosedate(rs.getString("Closedate"));
                obj.setSTATUS(rs.getString("STATUS"));
                obj.setID(rs.getString("ID"));
                obj.setID_NNT(rs.getString("ID_NNT"));
                obj.setREASONCONFIRM(rs.getString("REASONCONFIRM"));
                obj.setBranchid(rs.getString("Branchid"));
                return obj;
            }
            return obj;
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
     * @param P_DATE
     * @return
     * @throws SQLException
     */
    public String working_day(Date P_DATE) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(PROC_WORKING_DAY);
            calStmt.setDate(1, (java.sql.Date) P_DATE);
            calStmt.registerOutParameter(2, Types.VARCHAR);
            calStmt.execute();
            String p_type = calStmt.getString(2);
            return p_type;
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
     * @param P_DATE
     * @param P_TYPE
     * @return
     * @throws SQLException
     */
    public ArrayList<?> getListINS(Date P_DATE, String P_TYPE) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            ArrayList result = new ArrayList();
            calStmt = connection.prepareCall(FN_GD_MANULIFE);
            java.sql.Date sqlFromdate = new java.sql.Date(P_DATE.getTime());
            calStmt.setDate(1, sqlFromdate);
            calStmt.setString(2, P_TYPE);
            calStmt.execute();
            ArrayList<String> p_args = new ArrayList<String>();
            return JDBCEngine.executeQuery(GET_TMP_GD_MANULIFE, p_args, connection);
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
     * @param P_DATE
     * @param P_TYPE
     * @return
     * @throws SQLException
     */
    public ArrayList<?> getListINS_BKN(Date P_DATE, String P_TYPE) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            ArrayList result = new ArrayList();
            calStmt = connection.prepareCall(FN_SP_MANULIFE);
            java.sql.Date sqlFromdate = new java.sql.Date(P_DATE.getTime());
            calStmt.setDate(1, sqlFromdate);
            calStmt.setString(2, P_TYPE);
            calStmt.execute();
            ArrayList<String> p_args = new ArrayList<String>();
            return JDBCEngine.executeQuery(GET_TMP_SP_MANULIFE_BKN, p_args, connection);
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
     * @return
     * @throws SQLException
     */
    public ArrayList getDetailDCbyID(String pID) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(getdetaildcbyid);
            calStmt.setString(1, pID);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(2);
            ArrayList result = new ArrayList();
            while (rs.next()) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("PBAN_TLIEU_XML_DC", rs.getString("PBAN_TLIEU_XML_DC"));
                h.put("MA_GDICH_DC", rs.getString("MA_GDICH_DC"));
                h.put("NGAY_GDICH_DC", rs.getString("NGAY_GDICH_DC"));
                h.put("TU_NGAY_DC", rs.getString("TU_NGAY_DC"));
                h.put("DEN_NGAY_DC", rs.getString("DEN_NGAY_DC"));
                h.put("MSGID", rs.getString("MSGID"));
                result.add(h);
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

    /**
     *
     * @param GNT
     * @return
     * @throws SQLException
     */
    public String[] INSERT_GNT2019(NTDT_GNT GNT) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(INSERT_GNT2);
            calStmt.setString(1, GNT.getMA_GDICH());
            calStmt.setString(2, GNT.getNGAY_GUI_GDICH());
            calStmt.setString(3, GNT.getMA_GDICH_TCHIEU());

            calStmt.setString(4, GNT.getMAHIEU_CTU());
            calStmt.setString(5, GNT.getSO_CTU());
            calStmt.setString(6, GNT.getPBAN_TLIEU_XML());
            calStmt.setString(7, GNT.getID_CHUNGTU());
            calStmt.setString(8, GNT.getSO_GNT());
            calStmt.setString(9, GNT.getMA_CTU());

            calStmt.setString(10, GNT.getHTHUC_NOP());
            calStmt.setString(11, GNT.getMST_NNOP());
            calStmt.setString(12, GNT.getTEN_NNOP());
            calStmt.setString(13, GNT.getDIACHI_NNOP());
            calStmt.setString(14, GNT.getMA_CQT());
            calStmt.setString(15, GNT.getTEN_CQT());

            calStmt.setString(16, GNT.getMA_XA_NNOP());
            calStmt.setString(17, GNT.getTEN_XA_NNOP());
            calStmt.setString(18, GNT.getMA_HUYEN_NNOP());
            calStmt.setString(19, GNT.getTEN_HUYEN_NNOP());
            calStmt.setString(20, GNT.getMA_TINH_NNOP());
            calStmt.setString(21, GNT.getTEN_TINH_NNOP());

            calStmt.setString(22, GNT.getMST_NTHAY());
            calStmt.setString(23, GNT.getTEN_NTHAY());
            calStmt.setString(24, GNT.getDIACHI_NTHAY());
            calStmt.setString(25, GNT.getTEN_HUYEN_NTHAY());
            calStmt.setString(26, GNT.getTEN_TINH_NTHAY());

            calStmt.setString(27, GNT.getMA_NHANG_NOP());
            calStmt.setString(28, GNT.getTEN_NHANG_NOP());
            calStmt.setString(29, GNT.getSTK_NHANG_NOP());

            calStmt.setString(30, GNT.getMA_HIEU_KBAC());
            calStmt.setString(31, GNT.getTEN_KBAC());
            calStmt.setString(32, GNT.getMA_TINH_KBAC());
            calStmt.setString(33, GNT.getTEN_TINH_KBAC());

            calStmt.setString(34, GNT.getLOAI_TK_THU());
            calStmt.setString(35, GNT.getTEN_TK_THU());
            calStmt.setString(36, GNT.getSTK_THU());

            calStmt.setString(37, GNT.getID_TK_KNGHI());
            calStmt.setString(38, GNT.getTK_KNGHI());

            calStmt.setString(39, GNT.getMA_CQTHU());
            calStmt.setString(40, GNT.getTEN_CQTHU());

            calStmt.setString(41, GNT.getNGAY_LAP());
            calStmt.setString(42, GNT.getTONG_TIEN());
            calStmt.setString(43, GNT.getVAN_ID());
            calStmt.setString(44, GNT.getMA_DBHC_THU());
            calStmt.setString(45, GNT.getTEN_DBHC_THU());
            calStmt.setString(46, GNT.getMA_LOAI_THUE());
            calStmt.setString(47, GNT.getMSGID());
            calStmt.setString(48, "03002");
            calStmt.setString(49, GNT.getCKS());

            calStmt.registerOutParameter(50, Types.NUMERIC); //pID_GNT
            calStmt.registerOutParameter(51, Types.VARCHAR); //pStatus

            calStmt.setString(52, GNT.getMA_NGUYENTE());
            calStmt.setString(53, GNT.getMA_NHANG_UNT());
            calStmt.setString(54, GNT.getTEN_NHANG_UNT());
            calStmt.setString(55, GNT.getMA_THAMCHIEU());

            calStmt.execute();

            String[] arrResult = new String[2];
            arrResult[0] = String.valueOf(calStmt.getInt(50));
            arrResult[1] = calStmt.getString(51);
            return arrResult;
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
            String pSO_TK_TB_QD) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(INSERT_CHUNGTU_CHIIET2);
            calStmt.setInt(1, pID_GNT);
            calStmt.setString(2, pID_CTU_CTIET);
            calStmt.setString(3, pID_CTU);
            calStmt.setString(4, pNDUNG_NOP);
            calStmt.setString(5, pMA_NDKT);
            calStmt.setString(6, pMA_CHUONG);
            calStmt.setString(7, pKY_THUE);
            calStmt.setString(8, pTIEN_PNOP);
            calStmt.setString(9, pGHI_CHU);
            calStmt.setString(10, pSO_TK_TB_QD);
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
     * @param pID_DOICHIEU_GD
     * @param pReportType
     * @return
     * @throws SQLException
     */
    public List<NTDT_BCDC_02> BCDC_02_2019(int pID_DOICHIEU_GD, String pReportType) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(BAOCAODOICHIEU_CHITIET);
            calStmt.setInt(1, pID_DOICHIEU_GD);
            calStmt.setString(2, pReportType);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(3);
            List<NTDT_BCDC_02> resultList = new ArrayList<NTDT_BCDC_02>();
            while (rs.next()) {
                NTDT_BCDC_02 obj = new NTDT_BCDC_02();
                obj.setSO_CTU(rs.getString("SO_CTU"));
                obj.setMAHIEU_CTU(rs.getString("MAHIEU_CTU"));
                obj.setSO_GNT(rs.getString("SO_GNT"));
                obj.setTEN_NNT(rs.getString("TEN_NNT"));
                obj.setMST(rs.getString("MST"));
                obj.setMa_Cqt(rs.getString("Ma_Cqt"));
                obj.setMa_Cqthu(rs.getString("Ma_Cqthu"));
                obj.setNgay_Nop_Gnt(rs.getString("Ngay_Nop_Gnt"));
                obj.setNgay_Nop_Thue(rs.getString("Ngay_Nop_Thue"));
                obj.setTGIAN_TCT(rs.getString("TGIAN_TCT"));
                obj.setTGIAN_NHTM(rs.getString("TGIAN_NHTM"));
                obj.setSO_TIEN(rs.getString("SO_TIEN"));
                obj.setTTHAI_TCT(rs.getString("TTHAI_TCT"));
                obj.setTTHAI_NHTM(rs.getString("TTHAI_NHTM"));
                obj.setMA_NGUYENTE(rs.getString("MA_NGUYENTE"));
                obj.setMA_THAMCHIEU(rs.getString("MA_THAMCHIEU"));
                resultList.add(obj); // add it to the result
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
     * @param pID_DOICHIEU_GD
     * @param pReportType
     * @return
     * @throws SQLException
     */
    public List<NTDT_BCDC_05> BCDC_05_2019(int pID_DOICHIEU_GD, String pReportType) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(BAOCAODOICHIEU_CHITIET);
            calStmt.setInt(1, pID_DOICHIEU_GD);
            calStmt.setString(2, pReportType);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(3);
            List<NTDT_BCDC_05> resultList = new ArrayList<NTDT_BCDC_05>();
            while (rs.next()) {
                NTDT_BCDC_05 obj = new NTDT_BCDC_05();
                obj.setSO_CTU(rs.getString("SO_CTU"));
                obj.setMAHIEU_CTU(rs.getString("MAHIEU_CTU"));
                obj.setSO_GNT(rs.getString("SO_GNT"));
                obj.setTEN_NNT(rs.getString("TEN_NNT"));
                obj.setMST(rs.getString("MST"));
                obj.setMa_Cqt(rs.getString("Ma_Cqt"));
                obj.setMa_Cqthu(rs.getString("Ma_Cqthu"));
                obj.setNgay_Nop_Gnt(rs.getString("Ngay_Nop_Gnt"));
                obj.setNgay_Nop_Thue(rs.getString("Ngay_Nop_Thue"));
                obj.setMA_KBNN(rs.getString("MA_KBNN"));
                obj.setTCT_MA_CHUONG(rs.getString("TCT_MA_CHUONG"));
                obj.setTCT_MA_TMUC(rs.getString("TCT_MA_TMUC"));
                obj.setTCT_SO_TIEN(rs.getString("TCT_SO_TIEN"));
                obj.setTCT_TTHAI(rs.getString("TCT_TTHAI"));
                obj.setNHTM_MA_CHUONG(rs.getString("NHTM_MA_CHUONG"));
                obj.setNHTM_MA_TMUC(rs.getString("NHTM_MA_TMUC"));
                obj.setNHTM_SO_TIEN(rs.getString("NHTM_SO_TIEN"));
                obj.setNHTM_TTHAI(rs.getString("NHTM_TTHAI"));
                obj.setMA_THAMCHIEU(rs.getString("MA_THAMCHIEU"));
                obj.setNHTM_MA_NGUYENTE(rs.getString("NHTM_MA_NGUYENTE"));
                obj.setTCT_MA_NGUYENTE(rs.getString("TCT_MA_NGUYENTE"));
                resultList.add(obj); // add it to the result
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
     * @param pID_DOICHIEU_GD
     * @param pMABAOCAO
     * @return
     * @throws SQLException
     */
    public List<NTDT_DOICHIEUGD> BAOCAODOICHIEU_2019(int pID_DOICHIEU_GD, String pMABAOCAO) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(BAOCAODOICHIEU);
            calStmt.setInt(1, pID_DOICHIEU_GD);
            calStmt.setString(2, pMABAOCAO);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(3);
            List<NTDT_DOICHIEUGD> resultList = new ArrayList<NTDT_DOICHIEUGD>();
            while (rs.next()) {
                NTDT_DOICHIEUGD obj = new NTDT_DOICHIEUGD();
                obj.setPBAN_TLIEU_XML(rs.getString("PBAN_TLIEU_XML"));
                obj.setMA_BCAO(rs.getString("MA_BCAO"));
                obj.setTEN_BCAO(rs.getString("TEN_BCAO"));
                obj.setTGIAN_DCHIEU(rs.getString("TGIAN_DCHIEU"));
                obj.setMA_NHANG_DCHIEU(rs.getString("MA_NHANG_DCHIEU"));
                obj.setTEN_NHANG_DCHIEU(rs.getString("TEN_NHANG_DCHIEU"));
                obj.setTONGDIEN_LECH(rs.getString("TONGDIEN_LECH"));
                obj.setTONGTIEN_LECH(rs.getString("TONGTIEN_LECH"));
                obj.setTONGTIEN_LECH_VND(rs.getString("TONGTIEN_LECH_CT_VND"));
                obj.setTONGTIEN_LECH_USD(rs.getString("TONGTIEN_LECH_CT_USD"));
                obj.setTONGDIEN_TCTCO_NHTMKO(rs.getString("TONGDIEN_TCTCO_NHTMKO"));
                obj.setTONGTIEN_TCTCO_NHTMKO(rs.getString("TONGTIEN_TCTCO_NHTMKO"));
                obj.setTONGTIEN_TCTCO_NHTMKO_VND(rs.getString("TONGTIEN_TCTCO_NHTMKO_CT_VND"));
                obj.setTONGTIEN_TCTCO_NHTMKO_USD(rs.getString("TONGTIEN_TCTCO_NHTMKO_CT_USD"));
                obj.setTONGDIEN_NHTMCO_TCTKO(rs.getString("TONGDIEN_NHTMCO_TCTKO"));
                obj.setTONGTIEN_NHTMCO_TCTKO(rs.getString("TONGTIEN_NHTMCO_TCTKO"));
                obj.setTONGTIEN_NHTMCO_TCTKO_VND(rs.getString("TONGTIEN_NHTMCO_TCTKO_CT_VND"));
                obj.setTONGTIEN_NHTMCO_TCTKO_USD(rs.getString("TONGTIEN_NHTMCO_TCTKO_CT_USD"));
                obj.setTONGDIEN_LECH_TTHAI(rs.getString("TONGDIEN_LECH_TTHAI"));
                obj.setTONGTIEN_LECH_TTHAI(rs.getString("TONGTIEN_LECH_TTHAI"));
                obj.setTONGTIEN_LECH_TTHAI_VND(rs.getString("TONGTIEN_LECH_TTHAI_CT_VND"));
                obj.setTONGTIEN_LECH_TTHAI_USD(rs.getString("TONGTIEN_LECH_TTHAI_CT_USD"));
                resultList.add(obj); // add it to the result
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
     * @param pMA_NGUYENTE
     * @param pSO_THAMCHIEU
     * @return
     * @throws SQLException
     */
    public int INSERT_DOICHIEU_GNT_2019(int pID_DOICHIEU_GD,
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
            String pMA_NGUYENTE,
            String pSO_THAMCHIEU) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(INSERT_DOICHIEU_GNT2);
            calStmt.setInt(1, pID_DOICHIEU_GD);
            calStmt.setString(2, pMA_GDICH);
            calStmt.setString(3, pNGAY_GUI_GDICH);

            calStmt.setString(4, pNGAY_NOP_GNT);
            calStmt.setString(5, pNGAY_NOP_THUE);
            calStmt.setString(6, pTTHAI_GDICH);
            calStmt.setString(7, pTTHAI_CTU);
            calStmt.setString(8, pMAHIEU_CTU);
            calStmt.setString(9, pSO_CTU);
            calStmt.setString(10, pPBAN_TLIEU_XML);

            calStmt.setString(11, pID_CTU);
            calStmt.setString(12, pSO_GNT);
            calStmt.setString(13, pMA_CTU);
            calStmt.setString(14, pHTHUC_NOP);
            calStmt.setString(15, pMST_NNOP);

            calStmt.setString(16, pTEN_NNOP);
            calStmt.setString(17, pMA_CQT);
            calStmt.setString(18, pTEN_CQT);
            calStmt.setString(19, pDIACHI_NNOP);
            calStmt.setString(20, pMA_XA_NNOP);
            calStmt.setString(21, pTEN_XA_NNOP);

            calStmt.setString(22, pMA_HUYEN_NNOP);
            calStmt.setString(23, pTEN_HUYEN_NNOP);
            calStmt.setString(24, pMA_TINH_NNOP);
            calStmt.setString(25, pTEN_TINH_NNOP);
            calStmt.setString(26, pMST_NTHAY);

            calStmt.setString(27, pTEN_NTHAY);
            calStmt.setString(28, pDIACHI_NTHAY);
            calStmt.setString(29, pTEN_HUYEN_NTHAY);

            calStmt.setString(30, pTEN_TINH_NTHAY);
            calStmt.setString(31, pMA_NHANG_NOP);
            calStmt.setString(32, pTEN_NHANG_NOP);
            calStmt.setString(33, pSTK_NHANG_NOP);

            calStmt.setString(34, pMA_HIEU_KBAC);
            calStmt.setString(35, pTEN_KBAC);
            calStmt.setString(36, pMA_TINH_KBAC);

            calStmt.setString(37, pTEN_TINH_KBAC);
            calStmt.setString(38, pLOAI_TK_THU);

            calStmt.setString(39, pTEN_TK_THU);
            calStmt.setString(40, pSTK_THU);

            calStmt.setString(41, pID_TK_KNGHI);
            calStmt.setString(42, pTK_KNGHI);
            calStmt.setString(43, pMA_CQTHU);
            calStmt.setString(44, pTEN_CQTHU);
            calStmt.setString(45, pNGAY_LAP);
            calStmt.setString(46, pTONG_TIEN);
            calStmt.setString(47, pVAN_ID);

            calStmt.registerOutParameter(48, Types.INTEGER); //pStatus

            calStmt.setString(49, pMA_NGUYENTE);
            calStmt.setString(50, pSO_THAMCHIEU);

            calStmt.execute();

            return calStmt.getInt(48);
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
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws SQLException
     */
    public List<NTDT_GNT> GUITHONGBAO_GNT(String pLOAITHONGBAO, int pID_REF) throws SQLException {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(GUITHONGBAO);
            calStmt.setString(1, pLOAITHONGBAO);
            calStmt.setInt(2, pID_REF);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR); //pID_NNT
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(3);
            List<NTDT_GNT> resultList = new ArrayList<NTDT_GNT>();
            while (rs.next()) {
                NTDT_GNT obj = new NTDT_GNT();
                obj.setMA_GDICH(rs.getString("MA_GDICH"));
                obj.setNGAY_GUI_GDICH(rs.getString("NGAY_GUI_GDICH"));
                obj.setMA_GDICH_TCHIEU(rs.getString("MA_GDICH_TCHIEU"));
                obj.setPBAN_TLIEU_XML(rs.getString("PBAN_TLIEU_XML"));
                obj.setMA_THONGBAO(rs.getString("MA_THONGBAO"));
                obj.setMAU_THONGBAO(rs.getString("MAU_THONGBAO"));
                obj.setTEN_THONGBAO(rs.getString("TEN_THONGBAO"));
                obj.setSO_THONGBAO(rs.getString("SO_THONGBAO"));
                obj.setNGAY_THONGBAO(rs.getString("NGAY_THONGBAO"));
                obj.setMST_NNOP(rs.getString("MST_NNOP"));
                obj.setTEN_NNOP(rs.getString("TEN_NNOP"));
                obj.setMA_CQT(rs.getString("MA_CQT"));
                obj.setMST_NTHAY(rs.getString("MST_NTHAY"));
                obj.setTEN_NTHAY(rs.getString("Ten_Nthay"));
                obj.setID_CHUNGTU(rs.getString("ID_CHUNGTU"));
                obj.setSO_GNT(rs.getString("SO_GNT"));
                obj.setMAHIEU_CTU(rs.getString("MAHIEU_CTU"));
                obj.setSO_CTU(rs.getString("So_Ctu"));
                obj.setSHIEU_KHOBAC_NOP(rs.getString("SHIEU_KHOBAC_NOP"));
                obj.setTEN_KHOBAC_NOP(rs.getString("TEN_KHOBAC_NOP"));
                obj.setSTK_KHOBAC_NOP(rs.getString("STK_KHOBAC_NOP"));
                obj.setMA_NHANG_NOP(rs.getString("Ma_Nhang_Nop"));
                obj.setSTK_NHANG_NOP(rs.getString("Stk_Nhang_Nop"));
                obj.setTONGSOMON(rs.getString("TONGSOMON"));
                obj.setTONG_TIEN(rs.getString("Tong_Tien"));
                obj.setPHI(rs.getString("PHI"));
                obj.setNGAYXULY(rs.getString("NGAYXULY"));
                obj.setMA_TTHAI(rs.getString("MA_TTHAI"));
                obj.setTEN_TTHAI(rs.getString("TEN_TTHAI"));
                obj.setMSGID(rs.getString("REF_MSGID"));
                obj.setLISTMAGD(rs.getString("LISTMAGD"));
                obj.setMA_NGUYENTE(rs.getString("MA_NGUYENTE"));
                obj.setMA_THAMCHIEU(rs.getString("MA_THAMCHIEU"));
                resultList.add(obj);
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

    public ArrayList<?> get_NTDT_PERSONAL_PAYMENT(String sochungtu) throws Exception {
        try {
            ArrayList<String> p_args = new ArrayList<String>();
            p_args.add(sochungtu);
            return JDBCEngine.executeQuery(getNtdtPersonalPayment, p_args, connection);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String searchNtdtPaymentExtend = "select * from NTDT_PERSONAL_PAYMENT_EXTEND e where e.refcore = ?";
    public ArrayList<?> search_NTDT_PAYMENT_EXTEND(String sochungtu) throws SQLException {
        try {
            ArrayList<String> p_args = new ArrayList<String>();
            p_args.add(sochungtu);
            return JDBCEngine.executeQuery(searchNtdtPaymentExtend, p_args, connection);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
