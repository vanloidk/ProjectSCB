/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cims.kt.KhoaTheReq;
import scb.com.vn.common.model.cims.kt.MoKhoaTheReq;
import scb.com.vn.common.model.cw.CardInfo;
import scb.com.vn.common.model.cw.SenderMSVSCardInfo;
import scb.com.vn.dbi.connection.ConnectionManager;
import scb.com.vn.dbi.dto.MasterSenderInfor;
import scb.com.vn.dbi.utility.Utils;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author CORE77
 */
public class CWLiveDAO {

    private static final Logger LOGGER = Logger.getLogger(CWLiveDAO.class);
    /*    LIVE */
    final String CC_GetInternalCardInfo = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetInternalCardInfo(?)}";
    final String CC_GetInternalCardTransaction = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetInternalCardTransaction(?)}";
    final String CC_GetInternalCardInfo_MC = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetInternalCardInfo_MC(?)}";
    final String CC_GetCard_Profile = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetCard_Profile(?)}";
    final String CC_GetAward_Point = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetAward_Point(?)}";
    final String CC_GetInternalCardTran_MC = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetInternalCardTran_MC(?)}";
    final String CC_GetExtendedCustInfo = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetCardCus(?)}";
    final String CC_GetInternalCardInfo_MCDB = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetInternalCardInfo_MCDB(?)}";
    final String CC_GetIPP = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetIPP(?)}";
    final String CC_GetIPPDetail = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetIPPDetail(?)}";
    final String CC_GetIPPHist = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetIPPHist(?)}";
    final String FN_GET_SUMMARY_CARD_MB = " SELECT * FROM TABLE (CCPS.FN_GET_SUMMARY_CARD_MB(?))";
    final String FN_GET_CARD_STMT_IIB = "SELECT * FROM TABLE (CCPS.FN_GET_CARD_STMT_IIB(?,?,?,?,?))";
    /* MASTERPASS */
    final String QUERYMASTERPASSCARDINFOR = "BEGIN CCPS.GET_CARD_INFO_BY_LOC (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    /*Chuyen tu dw->Im*/
    final String GETLOCIDMD = "BEGIN ccps.get_card_dbint_status(?,?,?,?,?); END;";
    final String CHECKCARDNO = "BEGIN ccps.get_card_status(?,?,?,?); END;";

    /* MASTERPASS */
    /**
     *
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetInternalCardInfo(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(CC_GetInternalCardInfo);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetInternalCardTransaction(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(CC_GetInternalCardTransaction);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetInternalCardInfo_MC(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(CC_GetInternalCardInfo_MC);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetInternalCardInfo_MCDB(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(CC_GetInternalCardInfo_MCDB);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetCard_Profile(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(CC_GetCard_Profile);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetAward_Point(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(CC_GetAward_Point);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetInternalCardTran_MC(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(CC_GetInternalCardTran_MC);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param cif
     * @return
     * @throws SQLException
     */
    public String CC_GetExtendedCustInfo(String cif) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        int id = new Random().nextInt(99999);
        try {
            LOGGER.info("[IN] - CC_GetExtendedCustInfo - cif = [" + cif + "] - id = [" + id + "]");
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(CC_GetExtendedCustInfo);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, cif);
            calStmt.executeQuery();
            String result = calStmt.getString(1);
            LOGGER.info("[OUT] - CC_GetExtendedCustInfo - cif = [" + cif + "] - id = [" + id + "] - result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return "";
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private final String CC_GetExtendedCustInfoByPhone = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetCard_Phone(?)}";

    /**
     *
     * @param phone
     * @return
     * @throws SQLException
     */
    public String CC_GetExtendedCustInfoByPhone(String phone) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(CC_GetExtendedCustInfoByPhone);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, phone);
            calStmt.executeQuery();
            return calStmt.getString(1);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return "";
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
     * @param custno
     * @return
     * @throws Exception
     */
    public List GetAccountListByCustNoCard(String custno) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            ArrayList p_args = new ArrayList();
            p_args.add(custno);
            ArrayList list = JDBCEngine.executeQuery(FN_GET_SUMMARY_CARD_MB, p_args, connection);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param panceCD
     * @param fromDate
     * @param toDate
     * @param SR
     * @param rownum
     * @return
     * @throws Exception
     */
    public List getTransactionMaterCardByCardnoByTime(String panceCD, String fromDate, String toDate, String SR, String rownum) throws Exception {
        Connection connection = null;
        try {
            if (SR == null || SR.length() == 0) {
                SR = "0";
            }
            connection = ConnectionManager.getConnection("cwlive");
            ArrayList p_args = new ArrayList();
            p_args.add(panceCD);
            p_args.add(fromDate);
            p_args.add(toDate);
            p_args.add(SR);
            p_args.add(rownum);
            ArrayList list = JDBCEngine.executeQuery(FN_GET_CARD_STMT_IIB, p_args, connection);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param cif
     * @return
     * @throws SQLException
     */
    public String CC_GetIPP(String cif) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(CC_GetIPP);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, cif);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetIPPDetail(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(CC_GetIPPDetail);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GetIPPHist(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(CC_GetIPPHist);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param);
            calStmt.executeQuery();
            return calStmt.getString(1);
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

    /* MASTERPASS */
    /**
     *
     * @param loc4Digit
     * @return
     * @throws Exception
     */
    public MasterSenderInfor queryMasterpassCardInfor(String loc4Digit) throws Exception {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            if (loc4Digit == null || loc4Digit.length() != 16) {
                return null;
            }
            connection = ConnectionManager.getConnection("cwlive");
            String loc = loc4Digit.substring(0, 12);
            String fourDigit = loc4Digit.substring(12, 16);
            calStmt = connection.prepareCall(QUERYMASTERPASSCARDINFOR);
            calStmt.setString(1, loc);
            calStmt.setString(2, fourDigit);
            calStmt.registerOutParameter(3, OracleTypes.NUMBER);
            calStmt.registerOutParameter(4, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(5, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(6, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(7, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(8, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(9, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(11, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(12, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(13, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(14, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(15, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(16, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(17, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(18, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(19, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(20, OracleTypes.VARCHAR);

            /* Prepare for future */
            calStmt.registerOutParameter(21, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(22, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(23, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(24, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(25, OracleTypes.VARCHAR);

            calStmt.execute();

            MasterSenderInfor infor = new MasterSenderInfor();

            infor.setLoc(calStmt.getString(3));
            infor.setPan_encr(calStmt.getString(4));
            infor.setPan_mask(calStmt.getString(5));
            infor.setPan_clear(calStmt.getString(6));
            infor.setFrist_name(calStmt.getString(7));
            infor.setLast_name(calStmt.getString(8));
            infor.setHme_addr(calStmt.getString(9));
            infor.setHme_town(calStmt.getString(10));
            infor.setExpi_date(calStmt.getString(13));
            infor.setBrch_cde(calStmt.getString(14));
            infor.setSenderName(calStmt.getString(15));
            infor.setEmail(calStmt.getString(16));
            infor.setPhone(calStmt.getString(17));
            infor.setMiddleName(calStmt.getString(18));
            infor.setCreditType(calStmt.getString(19));
            infor.setCardType(calStmt.getString(20));
            infor.setAccountDebit(calStmt.getString(21));

            infor.setCardName(infor.getLast_name() + " " + infor.getMiddleName()
                    + " " + infor.getFrist_name());

            return infor;
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

    public SenderMSVSCardInfo querySenderMSVSCardInfo(String loc4Digit) throws Exception {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            if (loc4Digit == null || loc4Digit.length() != 16) {
                return null;
            }
            connection = ConnectionManager.getConnection("cwlive");
            String loc = loc4Digit.substring(0, 12);
            String fourDigit = loc4Digit.substring(12, 16);
            calStmt = connection.prepareCall(QUERYMASTERPASSCARDINFOR);
            calStmt.setString(1, loc);
            calStmt.setString(2, fourDigit);
            calStmt.registerOutParameter(3, OracleTypes.NUMBER);
            calStmt.registerOutParameter(4, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(5, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(6, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(7, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(8, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(9, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(10, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(11, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(12, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(13, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(14, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(15, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(16, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(17, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(18, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(19, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(20, OracleTypes.VARCHAR);

            /* Prepare for future */
            calStmt.registerOutParameter(21, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(22, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(23, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(24, OracleTypes.VARCHAR);
            calStmt.registerOutParameter(25, OracleTypes.VARCHAR);

            calStmt.execute();

            SenderMSVSCardInfo infor = new SenderMSVSCardInfo();

            infor.setLoc(calStmt.getString(3));
            infor.setPan_encr(calStmt.getString(4));
            infor.setPan_mask(calStmt.getString(5));
            infor.setPan_clear(calStmt.getString(6));
            infor.setFrist_name(calStmt.getString(7));
            infor.setLast_name(calStmt.getString(8));
            infor.setHme_addr(calStmt.getString(9));
            infor.setHme_town(calStmt.getString(10));
            infor.setExpi_date(calStmt.getString(13));
            infor.setBrch_cde(calStmt.getString(14));
            infor.setSenderName(calStmt.getString(15));
            infor.setEmail(calStmt.getString(16));
            infor.setPhone(calStmt.getString(17));
            infor.setMiddleName(calStmt.getString(18));
            infor.setCreditType(calStmt.getString(19));
            infor.setCardType(calStmt.getString(20));
            infor.setAccountDebit(calStmt.getString(21));

            infor.setCardName(infor.getLast_name() + " " + infor.getMiddleName()
                    + " " + infor.getFrist_name());

            return infor;
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

    /* MASTERPASS */
    private final String checkPhoneNumberAtCW = "{ ? = call CCPS.CALL_CENTER_LIVE.CC_GetCard_Phone(?)}";

    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean checkPhoneNumberAtCW(String phoneNumber) throws RemoteException, SQLException {
        boolean status = false;
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            calStmt = connection.prepareCall(checkPhoneNumberAtCW);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, phoneNumber);
            calStmt.executeQuery();
            String info = calStmt.getString(1);
            if (info != null && !info.isEmpty()) {
                status = true;
            }
        } catch (Exception ex) {
            LOGGER.error("checkPhoneNumberAtCW --> Ex: " + ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        if (!status) {
            try {
                connection = ConnectionManager.getConnection("cwlive");
                String newPhone = Utils.swapPhoneNumber(phoneNumber);
                calStmt = connection.prepareCall(checkPhoneNumberAtCW);
                calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
                calStmt.setString(2, newPhone);
                calStmt.executeQuery();
                String info = calStmt.getString(1);
                if (info != null && !info.isEmpty()) {
                    status = true;
                }
            } catch (Exception ex) {
                LOGGER.error("checkPhoneNumberAtCW --> Ex: " + ex);
            } finally {
                if (calStmt != null) {
                    calStmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        }
        return status;
    }

    /* Lấy thông tin thẻ thông qua số điện thoại */
    //private final String GETCARDINFOBYPHONE = "SELECT fx_ir056_new_id AS CMND,(SELECT px_irpanmap_panmask FROM ccps.ir_pan_map WHERE px_irpanmap_pan = PAN) AS PAN_MASK, STATUS_CARD, LOC AS LOC, ATV_DT AS ACTIVE_DATE, EMB_CUS_NAME AS CUS_NAME, FX_IR056_CIF_NO AS CIF_NO, CRD_PGM AS CARD_TYPE, (select F9_IR134_LOC_LMT from ccps.ir134 where P9_IR134_LOC_ACCT = LOC) AS LOC_LIMIT, BRCH_CDE AS BRCH_CODE, CRD_BRN AS CARD_BRCH, PAN_ENC AS PAN_ENC, NVL((select trim(PX_OA063_PAN) from ccps.oa063@am.world where trim(PX_OA063_PAN) = trim(PAN_ENC)), 0) AS KHOA_THE FROM ccps.ir056 INNER JOIN (SELECT f9_ir025_crn CRN, px_ir025_pan PAN, (trim(FX_IR025_CLS)||trim(FX_IR025_LOC)) AS STATUS_CARD, f9_ir025_loc_acct LOC, f9_ir025_crd_atv_dt ATV_DT, FX_IR025_CRD_PGM CRD_PGM, FX_IR025_BRCH_CDE BRCH_CDE, FX_IR025_CRD_BRN CRD_BRN, PX_IR025_PAN PAN_ENC, (trim(FX_IR025_EMB_LST_NM) || ' ' || trim(FX_IR025_EMB_MID_NM) || ' ' || trim(FX_IR025_EMB_NAME)) EMB_CUS_NAME FROM ccps.ir025 UNION ALL SELECT f9_ir275_crn CRN, px_ir275_own_pan PAN, (trim(FX_IR275_CLS)||trim(FX_IR275_LOC)) AS STATUS_CARD, f9_ir275_loc_acct LOC, f9_ir275_crd_atv_dt ATV_DT, FX_IR275_CRD_PGM CRD_PGM, FX_IR275_BRCH_CDE BRCH_CDE, FX_IR275_CRD_BRN CRD_BRN, PX_IR275_OWN_PAN PAN_ENC, (trim(FX_IR275_EMB_LST_NM) || ' ' || trim(FX_IR275_EMB_MID_NM) || ' ' || trim(FX_IR275_EMB_NAME)) EMB_CUS_NAME FROM ccps.ir275) CARD ON p9_ir056_crn = CARD.CRN WHERE TRIM(fx_ir056_hp) = ?";
    //mt uat
    private final String GETCARDINFOBYPHONE = " SELECT "
            + " fx_ir056_new_id AS CMND "
            + " ,(SELECT px_irpanmap_panmask FROM ccps.ir_pan_map WHERE px_irpanmap_pan = PAN) AS PAN_MASK "
            + " , STATUS_CARD "
            + " , LOC AS LOC "
            + " , ATV_DT AS ACTIVE_DATE "
            + " , EMB_CUS_NAME AS CUS_NAME "
            + " , FX_IR056_CIF_NO AS CIF_NO "
            + " , CRD_PGM AS CARD_TYPE "
            + " , (select F9_IR134_LOC_LMT from ccps.ir134 where P9_IR134_LOC_ACCT = LOC) AS LOC_LIMIT "
            + " , BRCH_CDE AS BRCH_CODE "
            + " , CRD_BRN AS CARD_BRCH, PAN_ENC AS PAN_ENC "
            + " , NVL((select trim(PX_OA063_PAN) from ccps.oa063@amtest where trim(PX_OA063_PAN) = trim(PAN_ENC)), 0) AS KHOA_THE "
            + " FROM ccps.ir056"
            + " INNER JOIN "
            + " ( SELECT f9_ir025_crn CRN, px_ir025_pan PAN, (trim(FX_IR025_CLS)||trim(FX_IR025_LOC)) AS STATUS_CARD, f9_ir025_loc_acct LOC, f9_ir025_crd_atv_dt ATV_DT, FX_IR025_CRD_PGM CRD_PGM, FX_IR025_BRCH_CDE BRCH_CDE, FX_IR025_CRD_BRN CRD_BRN, PX_IR025_PAN PAN_ENC, (trim(FX_IR025_EMB_LST_NM) || ' ' || trim(FX_IR025_EMB_MID_NM) || ' ' || trim(FX_IR025_EMB_NAME)) EMB_CUS_NAME FROM ccps.ir025 "
            + " UNION ALL"
            + " SELECT f9_ir275_crn CRN, px_ir275_own_pan PAN, (trim(FX_IR275_CLS)||trim(FX_IR275_LOC)) AS STATUS_CARD, f9_ir275_loc_acct LOC, f9_ir275_crd_atv_dt ATV_DT, FX_IR275_CRD_PGM CRD_PGM, FX_IR275_BRCH_CDE BRCH_CDE, FX_IR275_CRD_BRN CRD_BRN, PX_IR275_OWN_PAN PAN_ENC, (trim(FX_IR275_EMB_LST_NM) || ' ' || trim(FX_IR275_EMB_MID_NM) || ' ' || trim(FX_IR275_EMB_NAME)) EMB_CUS_NAME FROM ccps.ir275) CARD"
            + " ON p9_ir056_crn = CARD.CRN "
            + " WHERE TRIM(fx_ir056_hp) = ?";

    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<CardInfo> GetCardInfoByPhone(String phoneNumber) throws RemoteException, SQLException {
        List<CardInfo> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            preStatement = connection.prepareStatement(GETCARDINFOBYPHONE);
            preStatement.setString(1, phoneNumber);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                CardInfo item = new CardInfo();
                item.setPhoneNumber(phoneNumber);
                item.setCmnd(rs.getString("CMND"));
                item.setPan_mask(rs.getString("PAN_MASK"));
                item.setLoc(rs.getString("LOC"));
                item.setActiveDate(rs.getString("ACTIVE_DATE"));
                item.setCustomerName(rs.getString("CUS_NAME"));
                item.setCif(rs.getString("CIF_NO"));
                item.setCardType(rs.getString("CARD_TYPE"));
                item.setLocLimit(rs.getString("LOC_LIMIT"));
                item.setBranchCode(rs.getString("BRCH_CODE"));
                item.setCardBranch(rs.getString("CARD_BRCH"));
                item.setPanEncrypt(rs.getString("PAN_ENC"));
                item.setLocked(rs.getString("KHOA_THE"));
                item.setClosed(rs.getString("STATUS_CARD"));
                result.add(item);
            }

        } catch (Exception e) {
            LOGGER.error("GetCardInfoByPhone --> " + e);
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

    /* Lấy thông tin thẻ thông qua số điện thoại */
    private final String GETCARDINFOBYPHONEAND4DIGIT = "select fx_ir056_new_id AS CMND, PAN_MASK, STATUS_CARD, LOC, ACTIVE_DATE, EXPI_DT\n"
            + "       , EMB_CUS_NAME AS CUS_NAME, FX_IR056_CIF_NO AS CIF_NO, CRD_PGM AS CARD_TYPE\n"
            + "       , (select F9_IR134_LOC_LMT from ccps.ir134@im where P9_IR134_LOC_ACCT = LOC) AS LOC_LIMIT\n"
            + "       ,  BRCH_CDE AS BRCH_CODE, CRD_BRN AS CARD_BRCH, PAN_ENC\n"
            + "       , NVL((select trim(PX_OA063_PAN) from ccps.oa063@am.world where trim(PX_OA063_PAN) = trim(PAN_ENC)), 0) AS KHOA_THE \n"
            + "from\n"
            + "(\n"
            + "    select F9_IR025_CRN CRN, PX_IR025_PAN PAN_ENC, F9_IR025_LOC_ACCT LOC, FX_IR025_CRD_PGM CRD_PGM\n"
            + "           , FX_IR025_CRD_BRN CRD_BRN, F9_IR025_CRD_ATV_DT ACTIVE_DATE, F9_IR025_EXPI_DT EXPI_DT\n"
            + "           , FX_IR025_BRCH_CDE BRCH_CDE, (trim(FX_IR025_CLS)||trim(FX_IR025_LOC)) AS STATUS_CARD\n"
            + "           , (SELECT px_irpanmap_panmask FROM ccps.ir_pan_map@im WHERE px_irpanmap_pan = PX_IR025_PAN) AS PAN_MASK\n"
            + "           , (trim(FX_IR025_EMB_LST_NM) || ' ' || trim(FX_IR025_EMB_MID_NM) || ' ' || trim(FX_IR025_EMB_NAME)) AS EMB_CUS_NAME\n"
            + "    from ccps.ir025@im\n"
            + "    where F9_IR025_LOC_ACCT in \n"
            + "    (\n"
            + "      select F9_IRPANMAP_LOC \n"
            + "      from ccps.ir_pan_map@im\n"
            + "      where F9_IRPANMAP_CRN in (select P9_IR056_CRN from ccps.ir056@im where FX_IR056_HP = '%s')\n"
            + "    )\n"
            + "    union all\n"
            + "    select F9_IR275_CRN CRN, PX_IR275_OWN_PAN PAN_ENC, F9_IR275_LOC_ACCT LOC, FX_IR275_CRD_PGM CRD_PGM\n"
            + "           , FX_IR275_CRD_BRN CRD_BRN, F9_IR275_CRD_ATV_DT ACTIVE_DATE, F9_IR275_EXPI_DT EXPI_DT\n"
            + "           , FX_IR275_BRCH_CDE BRCH_CDE, (trim(FX_IR275_CLS)||trim(FX_IR275_LOC)) AS STATUS_CARD\n"
            + "           , (SELECT px_irpanmap_panmask FROM ccps.ir_pan_map@im WHERE px_irpanmap_pan = PX_IR275_OWN_PAN) AS PAN_MASK\n"
            + "           , (trim(FX_IR275_EMB_LST_NM) || ' ' || trim(FX_IR275_EMB_MID_NM) || ' ' || trim(FX_IR275_EMB_NAME)) AS EMB_CUS_NAME\n"
            + "    from ccps.ir275@im\n"
            + "    where F9_IR275_LOC_ACCT in \n"
            + "    (\n"
            + "      select F9_IRPANMAP_LOC \n"
            + "      from ccps.ir_pan_map@im\n"
            + "      where F9_IRPANMAP_CRN in (select P9_IR056_CRN from ccps.ir056@im where FX_IR056_HP = '%s')\n"
            + "    )\n"
            + ")CARD INNER JOIN ccps.ir056@im ir56 ON ir56.p9_ir056_crn = CARD.CRN \n"
            + "where substr(PAN_MASK, -4) = '%s'";

    /**
     *
     * @param phoneNumber
     * @param last4Digit
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<CardInfo> GetCardInfoByPhone(String phoneNumber, String last4Digit) throws RemoteException, SQLException {
        List<CardInfo> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            String strBuilder = String.format(GETCARDINFOBYPHONEAND4DIGIT, phoneNumber, phoneNumber, last4Digit);
            preStatement = connection.prepareStatement(strBuilder);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                CardInfo item = new CardInfo();
                item.setPhoneNumber(phoneNumber);
                item.setCmnd(rs.getString("CMND"));
                item.setPan_mask(rs.getString("PAN_MASK"));
                item.setLoc(rs.getString("LOC"));
                item.setActiveDate(rs.getString("ACTIVE_DATE"));
                item.setCustomerName(rs.getString("CUS_NAME"));
                item.setExpiryDate(rs.getString("EXPI_DT"));
                item.setCif(rs.getString("CIF_NO"));
                item.setCardType(rs.getString("CARD_TYPE"));
                item.setLocLimit(rs.getString("LOC_LIMIT"));
                item.setBranchCode(rs.getString("BRCH_CODE"));
                item.setCardBranch(rs.getString("CARD_BRCH"));
                item.setPanEncrypt(rs.getString("PAN_ENC"));
                item.setLocked(rs.getString("KHOA_THE"));
                item.setClosed(rs.getString("STATUS_CARD"));
                result.add(item);
            }

        } catch (Exception e) {
            LOGGER.error("GetCardInfoByPhone --> " + e);
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

    /* Lấy thông tin thẻ thông qua số điện thoại */
    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<CardInfo> getCardInfo(KhoaTheReq req) throws RemoteException, SQLException {
        return req.isLockAll() ? getAllCardInfo(req.getPhone(), req.getCmnd())
                : getCardInfo(req.getPhone(), req.getCmnd(), req.getLast4Digits());
    }

    private final String GetCardInfo = "SELECT fx_ir056_new_id AS CMND,(SELECT px_irpanmap_panmask FROM ccps.ir_pan_map WHERE px_irpanmap_pan = PAN) AS PAN_MASK, STATUS_CARD, LOC AS LOC, ATV_DT AS ACTIVE_DATE, EMB_CUS_NAME AS CUS_NAME, FX_IR056_CIF_NO AS CIF_NO, CRD_PGM AS CARD_TYPE, (select F9_IR134_LOC_LMT from ccps.ir134 where P9_IR134_LOC_ACCT = LOC) AS LOC_LIMIT, BRCH_CDE AS BRCH_CODE, CRD_BRN AS CARD_BRCH, PAN_ENC AS PAN_ENC, NVL((select trim(PX_OA063_PAN) from ccps.oa063@amtest where trim(PX_OA063_PAN) = trim(PAN_ENC)), 0) AS KHOA_THE FROM ccps.ir056 INNER JOIN (SELECT f9_ir025_crn CRN, px_ir025_pan PAN, (trim(FX_IR025_CLS)||trim(FX_IR025_LOC)) AS STATUS_CARD, f9_ir025_loc_acct LOC, f9_ir025_crd_atv_dt ATV_DT, FX_IR025_CRD_PGM CRD_PGM, FX_IR025_BRCH_CDE BRCH_CDE, FX_IR025_CRD_BRN CRD_BRN, PX_IR025_PAN PAN_ENC, (trim(FX_IR025_EMB_LST_NM) || ' ' || trim(FX_IR025_EMB_MID_NM) || ' ' || trim(FX_IR025_EMB_NAME)) EMB_CUS_NAME FROM ccps.ir025 UNION ALL SELECT f9_ir275_crn CRN, px_ir275_own_pan PAN, (trim(FX_IR275_CLS)||trim(FX_IR275_LOC)) AS STATUS_CARD, f9_ir275_loc_acct LOC, f9_ir275_crd_atv_dt ATV_DT, FX_IR275_CRD_PGM CRD_PGM, FX_IR275_BRCH_CDE BRCH_CDE, FX_IR275_CRD_BRN CRD_BRN, PX_IR275_OWN_PAN PAN_ENC, (trim(FX_IR275_EMB_LST_NM) || ' ' || trim(FX_IR275_EMB_MID_NM) || ' ' || trim(FX_IR275_EMB_NAME)) EMB_CUS_NAME FROM ccps.ir275) CARD ON p9_ir056_crn = CARD.CRN WHERE TRIM(fx_ir056_hp) = ?";

    /**
     *
     * @param phone
     * @param cmnd
     * @param last4Digits
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<CardInfo> getCardInfo(String phone, String cmnd, String last4Digits) throws RemoteException, SQLException {
        List<CardInfo> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            preStatement = connection.prepareStatement(GetCardInfo);
            preStatement.setString(1, phone);
            rs = preStatement.executeQuery();
            String uncharCmnd = cmnd.replaceAll("\\D+", "");
            while (rs.next()) {
                String cmndO = rs.getString("CMND");
                String uncharcmndO = cmndO != null ? cmndO.replaceAll("\\D+", "") : cmndO;
                if (uncharCmnd.equalsIgnoreCase(uncharcmndO)) {
                    String panMask = rs.getString("PAN_MASK");
                    if (panMask.length() > 12 && last4Digits.equals(panMask.substring(12))) {
                        CardInfo item = new CardInfo();
                        item.setPhoneNumber(phone);
                        item.setCmnd(cmndO);
                        item.setPan_mask(panMask);
                        item.setLoc(rs.getString("LOC"));
                        item.setActiveDate(rs.getString("ACTIVE_DATE"));
                        item.setCustomerName(rs.getString("CUS_NAME"));
                        item.setCif(rs.getString("CIF_NO"));
                        item.setCardType(rs.getString("CARD_TYPE"));
                        item.setLocLimit(rs.getString("LOC_LIMIT"));
                        item.setBranchCode(rs.getString("BRCH_CODE"));
                        item.setCardBranch(rs.getString("CARD_BRCH"));
                        item.setPanEncrypt(rs.getString("PAN_ENC"));
                        item.setLocked(rs.getString("KHOA_THE"));
                        item.setClosed(rs.getString("STATUS_CARD"));
                        if (!item.isLocked() && !item.isClosed()) {
                            result.add(item);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("GetCardInfo --> " + e);
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

    private final String GetAllCardInfo = "SELECT fx_ir056_new_id AS CMND,(SELECT px_irpanmap_panmask FROM ccps.ir_pan_map WHERE px_irpanmap_pan = PAN) AS PAN_MASK, STATUS_CARD, LOC AS LOC, ATV_DT AS ACTIVE_DATE, EMB_CUS_NAME AS CUS_NAME, FX_IR056_CIF_NO AS CIF_NO, CRD_PGM AS CARD_TYPE, (select F9_IR134_LOC_LMT from ccps.ir134 where P9_IR134_LOC_ACCT = LOC) AS LOC_LIMIT, BRCH_CDE AS BRCH_CODE, CRD_BRN AS CARD_BRCH, PAN_ENC AS PAN_ENC, NVL((select trim(PX_OA063_PAN) from ccps.oa063@amtest where trim(PX_OA063_PAN) = trim(PAN_ENC)), 0) AS KHOA_THE FROM ccps.ir056 INNER JOIN (SELECT f9_ir025_crn CRN, px_ir025_pan PAN, (trim(FX_IR025_CLS)||trim(FX_IR025_LOC)) AS STATUS_CARD, f9_ir025_loc_acct LOC, f9_ir025_crd_atv_dt ATV_DT, FX_IR025_CRD_PGM CRD_PGM, FX_IR025_BRCH_CDE BRCH_CDE, FX_IR025_CRD_BRN CRD_BRN, PX_IR025_PAN PAN_ENC, (trim(FX_IR025_EMB_LST_NM) || ' ' || trim(FX_IR025_EMB_MID_NM) || ' ' || trim(FX_IR025_EMB_NAME)) EMB_CUS_NAME FROM ccps.ir025 UNION ALL SELECT f9_ir275_crn CRN, px_ir275_own_pan PAN, (trim(FX_IR275_CLS)||trim(FX_IR275_LOC)) AS STATUS_CARD, f9_ir275_loc_acct LOC, f9_ir275_crd_atv_dt ATV_DT, FX_IR275_CRD_PGM CRD_PGM, FX_IR275_BRCH_CDE BRCH_CDE, FX_IR275_CRD_BRN CRD_BRN, PX_IR275_OWN_PAN PAN_ENC, (trim(FX_IR275_EMB_LST_NM) || ' ' || trim(FX_IR275_EMB_MID_NM) || ' ' || trim(FX_IR275_EMB_NAME)) EMB_CUS_NAME FROM ccps.ir275) CARD ON p9_ir056_crn = CARD.CRN WHERE TRIM(fx_ir056_hp) = ?";

    /**
     *
     * @param phone
     * @param cmnd
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<CardInfo> getAllCardInfo(String phone, String cmnd) throws RemoteException, SQLException {
        List<CardInfo> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            String uncharCmnd = cmnd.replaceAll("\\D+", "");
            connection = ConnectionManager.getConnection("cwlive");
            preStatement = connection.prepareStatement(GetAllCardInfo);
            preStatement.setString(1, phone);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                String cmndO = rs.getString("CMND");
                String uncharcmndO = cmndO != null ? cmndO.replaceAll("\\D+", "") : cmndO;
                if (uncharCmnd.equalsIgnoreCase(uncharcmndO)) {
                    CardInfo item = new CardInfo();
                    item.setPhoneNumber(phone);
                    item.setCmnd(cmndO);
                    item.setPan_mask(rs.getString("PAN_MASK"));
                    item.setLoc(rs.getString("LOC"));
                    item.setActiveDate(rs.getString("ACTIVE_DATE"));
                    item.setCustomerName(rs.getString("CUS_NAME"));
                    item.setCif(rs.getString("CIF_NO"));
                    item.setCardType(rs.getString("CARD_TYPE"));
                    item.setLocLimit(rs.getString("LOC_LIMIT"));
                    item.setBranchCode(rs.getString("BRCH_CODE"));
                    item.setCardBranch(rs.getString("CARD_BRCH"));
                    item.setPanEncrypt(rs.getString("PAN_ENC"));
                    item.setLocked(rs.getString("KHOA_THE"));
                    item.setClosed(rs.getString("STATUS_CARD"));
                    if (!item.isLocked() && !item.isClosed()) {
                        result.add(item);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("GetAllCardInfo --> " + e);
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
     * @param pCARDNO
     * @return
     * @throws SQLException
     */
    public String[] checkCARD(String pCARDNO) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        String[] ArrayResult;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            if ("970429".equals(pCARDNO.substring(0, 6))) {
                calStmt = connection.prepareCall(CHECKCARDNO);
                calStmt.setString(1, pCARDNO);
                calStmt.registerOutParameter(2, OracleTypes.VARCHAR);
                calStmt.registerOutParameter(3, OracleTypes.VARCHAR);
                calStmt.registerOutParameter(4, OracleTypes.VARCHAR);
                calStmt.execute();
                ArrayResult = new String[3];
                ArrayResult[0] = calStmt.getString(2);
                ArrayResult[1] = calStmt.getString(3);
                ArrayResult[2] = calStmt.getString(4);
            } else {
                calStmt = connection.prepareCall(GETLOCIDMD);
                calStmt.setString(1, pCARDNO);
                calStmt.registerOutParameter(2, OracleTypes.VARCHAR);
                calStmt.registerOutParameter(3, OracleTypes.VARCHAR);
                calStmt.registerOutParameter(4, OracleTypes.VARCHAR);
                calStmt.registerOutParameter(5, OracleTypes.VARCHAR);
                calStmt.execute();
                ArrayResult = new String[4];
                ArrayResult[0] = calStmt.getString(2);
                ArrayResult[1] = calStmt.getString(3);
                ArrayResult[2] = calStmt.getString(4);
                ArrayResult[3] = calStmt.getString(5);
            }
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

    /* Lấy thông tin thẻ tam khóa thông qua số điện thoại */
    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<CardInfo> getCardLockedInfo(MoKhoaTheReq req) throws RemoteException, SQLException {
        return "Y".equals(req.getOpenAll()) ? getAllCardLockedInfo(req.getPhone(), req.getCmnd())
                : getCardLockedInfo(req.getPhone(), req.getCmnd(), req.getLast4Digits());
    }

    /**
     * GetCardLockedInfo
     *
     * @param phone
     * @param cmnd
     * @param last4Digits
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<CardInfo> getCardLockedInfo(String phone, String cmnd, String last4Digits) throws RemoteException, SQLException {
        List<CardInfo> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionManager.getConnection("cwlive");
            preStatement = connection.prepareStatement(GetCardInfo);
            preStatement.setString(1, phone);
            rs = preStatement.executeQuery();
            String uncharCmnd = cmnd.replaceAll("\\D+", "");
            while (rs.next()) {
                String cmndO = rs.getString("CMND");
                String uncharcmndO = cmndO != null ? cmndO.replaceAll("\\D+", "") : cmndO;
                if (uncharCmnd.equalsIgnoreCase(uncharcmndO)) {
                    String panMask = rs.getString("PAN_MASK");
                    if (panMask.length() > 12 && last4Digits.equals(panMask.substring(12))) {
                        CardInfo item = new CardInfo();
                        item.setPhoneNumber(phone);
                        item.setCmnd(cmndO);
                        item.setPan_mask(panMask);
                        item.setLoc(rs.getString("LOC"));
                        item.setActiveDate(rs.getString("ACTIVE_DATE"));
                        item.setCustomerName(rs.getString("CUS_NAME"));
                        item.setCif(rs.getString("CIF_NO"));
                        item.setCardType(rs.getString("CARD_TYPE"));
                        item.setLocLimit(rs.getString("LOC_LIMIT"));
                        item.setBranchCode(rs.getString("BRCH_CODE"));
                        item.setCardBranch(rs.getString("CARD_BRCH"));
                        item.setPanEncrypt(rs.getString("PAN_ENC"));
                        item.setLocked(rs.getString("KHOA_THE"));
                        item.setClosed(rs.getString("STATUS_CARD"));
                        if (item.isLocked() && !item.isClosed()) {
                            result.add(item);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("GetCardInfo --> " + e);
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
     * GetAllCardLockedInfo
     *
     * @param phone
     * @param cmnd
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<CardInfo> getAllCardLockedInfo(String phone, String cmnd) throws RemoteException, SQLException {
        List<CardInfo> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            String uncharCmnd = cmnd.replaceAll("\\D+", "");
            connection = ConnectionManager.getConnection("cwlive");
            preStatement = connection.prepareStatement(GetAllCardInfo);
            preStatement.setString(1, phone);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                String cmndO = rs.getString("CMND");
                String uncharcmndO = cmndO != null ? cmndO.replaceAll("\\D+", "") : cmndO;
                if (uncharCmnd.equalsIgnoreCase(uncharcmndO)) {
                    CardInfo item = new CardInfo();
                    item.setPhoneNumber(phone);
                    item.setCmnd(cmndO);
                    item.setPan_mask(rs.getString("PAN_MASK"));
                    item.setLoc(rs.getString("LOC"));
                    item.setActiveDate(rs.getString("ACTIVE_DATE"));
                    item.setCustomerName(rs.getString("CUS_NAME"));
                    item.setCif(rs.getString("CIF_NO"));
                    item.setCardType(rs.getString("CARD_TYPE"));
                    item.setLocLimit(rs.getString("LOC_LIMIT"));
                    item.setBranchCode(rs.getString("BRCH_CODE"));
                    item.setCardBranch(rs.getString("CARD_BRCH"));
                    item.setPanEncrypt(rs.getString("PAN_ENC"));
                    item.setLocked(rs.getString("KHOA_THE"));
                    item.setClosed(rs.getString("STATUS_CARD"));
                    if (item.isLocked() && !item.isClosed()) {
                        result.add(item);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("GetAllCardInfo --> " + e);
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
    
}
