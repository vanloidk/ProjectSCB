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
import java.sql.Types;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;
import scb.com.vn.dbi.connection.ConnectionManager;
import scb.com.vn.ultility.jdbc.JDBCEngine;
import java.util.HashMap;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dto.CardInfoDto;
import scb.com.vn.dbi.dto.PmtInfoV11ResDto;

/**
 *
 * @author CORE77
 */
public class CWDwhDAO {

    private static final Logger LOGGER = Logger.getLogger(CWDwhDAO.class);

    final String CC_GET_TT_SaoKe = "{ ? = call CCPS.CALL_CENTER_DW.GET_TT_SaoKe(?,?)}";
    final String CC_GetCreditPayTransaction = "{ ? = call CCPS.CALL_CENTER_DW.CC_GetCreditPayTransaction(?,?)}";
    final String CC_GetMonth = "{ ? = call CCPS.CALL_CENTER_DW.CC_GetMonth(?)}";
    final String IPP_PAYMENT_PROCESSOR = "{ ? = call CCPS.IPP_PAYMENT_PROCESSOR(?,?,?,?,?,?,?)}";
    final String getPMT_INFO = "BEGIN ccps.IPP_GET_PMT_INFO(?, ?, ?); END;";
    final String getPMT_INFO_V11 = "BEGIN ccps.IPP_GET_PMT_INFO_V11(?, ?, ?, ?, ?); END;";
    final String GETLOCIDMD = "BEGIN ccps.get_card_dbint_status(?,?,?,?,?); END;";
    final String CHECKCARDNO = "BEGIN ccps.get_card_status(?,?,?,?); END;";
    final String getDetailCard = "BEGIN ccps.GET_CARD_INFO_FOR_NAPAS(?,?,?,?,?); END;";
    final String checkPassFile = "select ccps.FN_STMT_PW_CHECK(?,?) from dual";
    final String getPdfFile = "select t.pdf,  substr(FILE_NAME, 0, 12) loc from ccps.STATEMENT_PDF t where id_pdf=?";
    final String GET_MC_INFO_STMT = "SELECT * FROM ccps.GET_MC_INFO_STMT WHERE ACCOUNTNO =? AND PERIOD =? ";
    final String getPdfFileByMonth = "select t.pdf,  substr(FILE_NAME, 0, 12) loc from ccps.STATEMENT_PDF t where loc=? and stmth=?";

    final String CC_GET_VCARD_INFO = "{ ? = call ccps.FN_GET_VCARD_INFO(?) }";
    
    final String CHECK_IDPDF = "BEGIN ccps.STMT_IDPDF_CHECK(?,?,?); END;";

    /**
     *
     * @param param
     * @return
     * @throws SQLException
     */
    public String CC_GET_TT_SaoKe(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwdwh");
            calStmt = connection.prepareCall(CC_GET_TT_SaoKe);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param.split("#")[0]);
            calStmt.setString(3, param.split("#")[1]);
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
    public String CC_GetCreditPayTransaction(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwdwh");
            calStmt = connection.prepareCall(CC_GetCreditPayTransaction);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param.split("#")[0]);
            calStmt.setString(3, param.split("#")[1]);
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
    public String CC_GetMonth(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwdwh");
            calStmt = connection.prepareCall(CC_GetMonth);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, param.split("#")[0]);
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
     * @param LOC
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public String getPMT_INFO(String LOC) throws SQLException, Exception {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwdwh");
            calStmt = connection.prepareCall(getPMT_INFO);
            calStmt.setString(1, LOC);
            calStmt.registerOutParameter(2, Types.NUMERIC);
            calStmt.registerOutParameter(3, Types.NUMERIC);
            calStmt.execute();
            String P_STMT_CLS_BAL = calStmt.getString(2);
            String P_CUR_CLS_BAL = calStmt.getString(3);
            String result = P_STMT_CLS_BAL + "#" + P_CUR_CLS_BAL;
            LOGGER.info("getPMT_INFO --> LOC = [" + LOC + "], result = [" + result + "]");
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
     * @param LOC
     * @param refCW
     * @param sotien
     * @param channel
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public String getIPP_PAYMENT_PROCESSOR(String LOC, String refCW, String sotien, String channel) throws SQLException, Exception {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwdwh");
            calStmt = connection.prepareCall(IPP_PAYMENT_PROCESSOR);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, channel);
            calStmt.setString(3, LOC);
            calStmt.setString(4, null);
            calStmt.setString(5, null);
            calStmt.setString(6, null);
            calStmt.setString(7, refCW);
            calStmt.setString(8, sotien);
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
     * @param pCARDNO
     * @return
     * @throws SQLException
     */
    public String[] checkCARD(String pCARDNO) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        String[] ArrayResult;
        try {
            connection = ConnectionManager.getConnection("cwdwh");
            if (pCARDNO.substring(0, 6).equals("970429")) {
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

    private final String GETCARDTRANSFERSTATUS = "SELECT TRANS_CUST_STATUS AS STATUS FROM fpt.ppt_crd_detail WHERE PAN = ?";

    /**
     * --01 giao the thanh cong, con lai chua giao the KH
     *
     * @param panEncrypt
     * @return
     * @throws SQLException
     */
    public String GetCardTransferStatus(String panEncrypt) throws SQLException {
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String result = "";
        try {
            connection = ConnectionManager.getConnection("cwdwh");
            preStatement = connection.prepareStatement(GETCARDTRANSFERSTATUS);
            preStatement.setString(1, panEncrypt);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                LOGGER.info("STATUS = [" + rs.getString("STATUS") + "]");
                result = rs.getString("STATUS");
                break;
            }
            LOGGER.info("result = [" + result + "]");
        } catch (Exception e) {
            LOGGER.error("GetCardTransferStatus --> " + e);
            throw new SQLException(e);
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
     * @param LOC
     * @param socuoi
     * @return
     * @throws SQLException
     */
    public String[] getDetailCard(String LOC, String socuoi) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        String[] ArrayResult;
        try {
            connection = ConnectionManager.getConnection("cwdwh");
            calStmt = connection.prepareCall(getDetailCard);
            calStmt.setString(1, LOC);
            calStmt.setString(2, socuoi);
            calStmt.registerOutParameter(3, OracleTypes.VARCHAR); // Ngay het han
            calStmt.registerOutParameter(4, OracleTypes.VARCHAR); // Chi nhanh mo the
            calStmt.registerOutParameter(5, OracleTypes.VARCHAR); // Chi nhanh mo the

            calStmt.execute();
            ArrayResult = new String[3];
            ArrayResult[0] = calStmt.getString(3); // Ngay het hen
            ArrayResult[1] = calStmt.getString(4); // Chi nhanh mo the
            ArrayResult[2] = calStmt.getString(5); // Chi nhanh mo the
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
     * @param LOC
     * @param Pass
     * @return
     * @throws Exception
     */
    public int checkPassFile(String LOC, String Pass) throws Exception {
        Connection connection = ConnectionManager.getConnection("cwdwh");
        ArrayList<String> p = new ArrayList<String>();
        p.add(LOC);
        p.add(Pass);
        ArrayList<?> a = JDBCEngine.executeQuery(checkPassFile, p, connection);
        if (a != null && a.size() > 0) {
            HashMap<?, ?> hm = (HashMap<?, ?>) a.get(0);
            String check = hm.get("ccps.fn_stmt_pw_check(:1,:2)").toString();
            if (check.equals("00")) {
                return 1;
            }
        }
        return 0;
    }

    /**
     *
     * @param LOC
     * @return
     * @throws Exception
     */
    public Object[] getPdfFile(String LOC) throws Exception {
        Connection connection = ConnectionManager.getConnection("cwdwh");
        try {
            ArrayList<String> p = new ArrayList<String>();
            p.add(LOC);
            ArrayList<?> a = JDBCEngine.executeQuery(getPdfFile, p, connection);
            Object[] result = new Object[2];
            if (a != null && a.size() > 0) {
                HashMap<?, ?> hm = (HashMap<?, ?>) a.get(0);
                byte[] bBlob = (byte[]) hm.get("pdf");
                result[0] = bBlob;
                result[1] = hm.get("loc").toString();
                return result;
            }
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public Object[] getPdfFileByMonth(String LOC,String Stmth) throws Exception {
        Connection connection = ConnectionManager.getConnection("cwdwh");
        try {
            ArrayList<String> p = new ArrayList<String>();
            p.add(LOC);
            p.add(Stmth);
            ArrayList<?> a = JDBCEngine.executeQuery(getPdfFileByMonth, p, connection);
            Object[] result = new Object[2];
            if (a != null && a.size() > 0) {
                HashMap<?, ?> hm = (HashMap<?, ?>) a.get(0);
                byte[] bBlob = (byte[]) hm.get("pdf");
                result[0] = bBlob;
                result[1] = hm.get("loc").toString();
                return result;
            }
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    final String INSERT_INFO_CHANGE_PIN = "BEGIN ccps.INSERT_INFO_CHANGE_PIN(?,?,?,?); END;";

    /**
     *
     * @param CARDNUMBER
     * @param mobileno
     * @param fullname
     * @return
     * @throws SQLException
     */
    public String INSERT_INFO_CHANGE_PIN(String CARDNUMBER, String mobileno, String fullname) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("cwdwh");
            calStmt = connection.prepareCall(INSERT_INFO_CHANGE_PIN);
            calStmt.setString(1, CARDNUMBER);
            calStmt.setString(2, mobileno);
            calStmt.setString(3, fullname);
            calStmt.registerOutParameter(4, OracleTypes.VARCHAR); // status
            calStmt.execute();
            String result = calStmt.getString(4);
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
     * get PMT_INFO V11
     * @param LOC
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public PmtInfoV11ResDto getPMT_INFOV11(String LOC) throws SQLException, Exception {
        Connection connection = null;
        CallableStatement calStmt = null;
        PmtInfoV11ResDto resDto = new PmtInfoV11ResDto();

        try {
            connection = ConnectionManager.getConnection("cwdwh");
            calStmt = connection.prepareCall(getPMT_INFO_V11);
            calStmt.setString(1, LOC);
            calStmt.registerOutParameter(2, Types.NUMERIC);
            calStmt.registerOutParameter(3, Types.NUMERIC);
            calStmt.registerOutParameter(4, Types.NUMERIC);
            calStmt.registerOutParameter(5, Types.NUMERIC);
            calStmt.execute();
            // set properties
            resDto.setPrevStmtClsBal(calStmt.getBigDecimal(2));
            resDto.setStmtClsBal(calStmt.getBigDecimal(3));
            resDto.setPrevCardStmtClsBal(calStmt.getBigDecimal(4));
            resDto.setCurClsBal(calStmt.getBigDecimal(5));
            LOGGER.info("getPMT_INFOV11 --> LOC = [" + LOC + "], result = [" + resDto + "]");

            return resDto;
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
     * lay sao ke the
     * @param cardaccountno
     * @param period
     * @return
     * @throws Exception
     */
    public ArrayList<?> getMCStateDetail(String cardaccountno, String period) throws Exception {
        Connection connection = null;
        CallableStatement calStmt = null;
        ArrayList p_args = new ArrayList();

        try {
            connection = ConnectionManager.getConnection("cwdwh");
            p_args.add(cardaccountno);
            p_args.add(period);
            LOGGER.info("GET_MC_INFO_STMT --> cardaccountno = [" + cardaccountno + "]");
            return JDBCEngine.executeQuery(GET_MC_INFO_STMT, p_args, connection);
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
     * GetVCardDetail
     *
     * @param paneCd
     * @return
     * @throws Exception
     */
    public String GetVCardDetail(String paneCd) throws Exception {
        Connection connection = null;
        CallableStatement calStmt = null;
        String vCard = "";
        try {
            connection = ConnectionManager.getConnection("cwdwh");
            calStmt = connection.prepareCall(CC_GET_VCARD_INFO);
            calStmt.registerOutParameter(1, Types.NVARCHAR);
            calStmt.setString(2, paneCd);
            calStmt.execute();
            vCard = calStmt.getString(1);
            LOGGER.info("GetVCardDetail --> paneCd = [" + paneCd + "], result = [" + vCard + "]");
            return vCard;
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
     * checkIdPDF
     * @param panMask
     * @param idPdf
     * @return
     * @throws SQLException 
     */
    public String checkIdPDF(String panMask, String idPdf) throws RemoteException, SQLException  {
        Connection connection = null;
        CallableStatement calStmt = null;
        String result;
        try {
            connection = ConnectionManager.getConnection("cwdwh");
            calStmt = connection.prepareCall(CHECK_IDPDF);
            calStmt.setString(1, panMask);
            calStmt.setString(2, idPdf);
            calStmt.registerOutParameter(3, OracleTypes.VARCHAR);
            calStmt.execute();
            
            result = calStmt.getString(3);
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
}
