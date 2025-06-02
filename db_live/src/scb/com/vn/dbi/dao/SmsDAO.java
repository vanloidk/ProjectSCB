package scb.com.vn.dbi.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.collated.CollatedDetail;

/**
 *
 * @author minhndb
 */
public class SmsDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(SmsDAO.class.getName());
    final String SEND_SMS = "SEND_SMS";
    final String SEND_SMS_8149 = "SEND_SMS_8149";
    final String SEND_SMS_TEMP = "SEND_SMS_TEMP";
    final String SEND_SMS_TNB = "SEND_SMS_TNB";
    final String GETSMS_TNB_RECEIVER = "GETSMS_TNB_RECEIVER";
    final String DO_MOVE_SP_RECEIVER = "SP_MOVE_SMS_RECEIVER";
    final String SP_GET_ALL_RECEIVER = "GETALLRECEIVER";
    final String UPDATE_RECEIVER_HISTORY = "update SMS_RECEIVER_HISTORY t set t.status=? where t.id=?";

    /**
     *
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws Exception
     */
    public int sendSMS(String mobile, String content, String servicecode, String requestid) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + SEND_SMS + " (?,?,?,?, ?,?); END;");
            calStmt.setString(1, mobile);
            calStmt.setString(2, content);
            calStmt.setString(3, servicecode);
            calStmt.setString(4, requestid);
            calStmt.registerOutParameter(5, Types.INTEGER);
            calStmt.registerOutParameter(6, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(5) == -1) {
                LOGGER.error("sendSMS --> calStmt.getString(6): " + calStmt.getString(6));
                throw new Exception(calStmt.getString(6));
            }
            return calStmt.getInt(5);
        } catch (SQLException sqlEx) {
            LOGGER.error("sendSMS --> Ex: " + sqlEx);
            throw new Exception(sqlEx);
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
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws Exception
     */
    public int sendSMS8149(String mobile, String content, String servicecode, String requestid) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + SEND_SMS_8149 + " (?,?,?,?, ?,?); END;");
            calStmt.setString(1, mobile);
            calStmt.setString(2, content);
            calStmt.setString(3, servicecode);
            calStmt.setString(4, requestid);
            calStmt.registerOutParameter(5, Types.INTEGER);
            calStmt.registerOutParameter(6, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(5) == -1) {
                throw new Exception(calStmt.getString(6));
            }
            return calStmt.getInt(5);
        } catch (SQLException sqlEx) {
            LOGGER.error("sendSMS --> Ex: " + sqlEx);
            throw new Exception(sqlEx);
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
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws Exception
     */
    public int sendSMSTemp(String mobile, String content, String servicecode, String requestid) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + SEND_SMS_TEMP + " (?,?,?,?, ?,?); END;");
            calStmt.setString(1, mobile);
            calStmt.setString(2, content);
            calStmt.setString(3, servicecode);
            calStmt.setString(4, requestid);
            calStmt.registerOutParameter(5, Types.INTEGER);
            calStmt.registerOutParameter(6, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(5) == -1) {
                LOGGER.warn("sendSMSTemp --> calStmt.getString(6) = " + calStmt.getString(6));
                throw new Exception(calStmt.getString(6));
            }
            return calStmt.getInt(5);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws Exception
     */
    public int sendSMSTNB(String mobile, String content, String servicecode, String requestid) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + SEND_SMS_TNB + " (?,?,?,?, ?,?); END;");
            calStmt.setString(1, mobile);
            calStmt.setString(2, content);
            calStmt.setString(3, servicecode);
            calStmt.setString(4, requestid);
            calStmt.registerOutParameter(5, Types.INTEGER);
            calStmt.registerOutParameter(6, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(5) == -1) {
                LOGGER.warn("sendSMSTNB --> calStmt.getString(6) = " + calStmt.getString(6));
                throw new Exception(calStmt.getString(6));
            }

            return calStmt.getInt(5);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
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
     * @param id
     * @param status
     * @return
     * @throws SQLException
     */
    public int updateStatus(String id, String status) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(UPDATE_RECEIVER_HISTORY);
            preStatement.setString(1, status);
            preStatement.setString(2, id);
            int result = preStatement.executeUpdate();
            return result;
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
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

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getAllSmsReceiver() throws Exception {
        ArrayList list = new ArrayList();
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + SP_GET_ALL_RECEIVER + " (?); END;");
            calStmt.registerOutParameter(1, OracleTypes.CURSOR);  //
            calStmt.execute();
            rs = (ResultSet) calStmt.getObject(1);
            if (rs == null) {
                return list;
            }
            while (rs.next()) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("id", rs.getString("ID"));
                h.put("mobile", rs.getString("MOBILE"));
                h.put("contents", rs.getString("CONTENTS"));
                h.put("receiver_date", rs.getString("RECEIVER_DATE"));
                h.put("status", rs.getString("STATUS"));
                h.put("ins_date", rs.getString("INS_DATE"));
                list.add(h);
            }
            return list;
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        } finally {
            if (rs != null && rs.isClosed() == false) {
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
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int doMoveSmsReceiverToHistory(String id, String status) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + DO_MOVE_SP_RECEIVER + " (?,?, ?,?); END;");
            calStmt.setString(1, id);
            calStmt.setString(2, status);
            calStmt.registerOutParameter(3, Types.INTEGER);
            calStmt.registerOutParameter(4, Types.NVARCHAR);
            calStmt.execute();
            if (calStmt.getInt(3) != 0) {
                LOGGER.warn("doMoveSmsReceiverToHistory --> calStmt.getString(4) = " + calStmt.getString(4));
                throw new Exception(calStmt.getString(4));
            }
            return calStmt.getInt(3);
        } catch (SQLException sqlEx) {
            LOGGER.error(sqlEx);
            throw new Exception(sqlEx);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private final String GET_LIST_DATA_COLLATED = "begin GET_DATA_COLLATED(?,?,?,?); end;";

    public List<CollatedDetail> getListDataCollated(String partnerId, String transDate) throws SQLException {
        CallableStatement calStmt = connection.prepareCall(GET_LIST_DATA_COLLATED);
        ResultSet rs = null;
        List<CollatedDetail> result = new ArrayList<>();
        try {
            calStmt.setString(1, partnerId);
            calStmt.setString(2, transDate);
            calStmt.registerOutParameter(3, OracleTypes.NVARCHAR);
            calStmt.registerOutParameter(4, OracleTypes.CURSOR);
            calStmt.executeQuery();
            rs = (ResultSet) calStmt.getObject(4);
            String statusProc = calStmt.getString(3);
            CollatedDetail detail = new CollatedDetail();
            if (statusProc.equals("00")) {
                if (rs == null) {
                    return result;
                }
                while (rs.next()) {
                    detail.setResultCollated(rs.getString("RESULTCOLLATE"));
                    detail.setTransTime(rs.getString("TRANSTIME"));
                    detail.setTransId(rs.getString("TRANSID"));
                    detail.setOrderId(rs.getString("ORDERID"));
                    detail.setMercTransId(rs.getString("MERCTRANSID"));
                    detail.setAmount(rs.getString("AMOUNT"));
                    detail.setCcy("CCY");
                    detail.setStatus(rs.getString("STATUS"));
                    result.add(detail);
                }
            } 
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw ex;
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
}
