/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.connection.ConnectionManager;

/**
 *
 * @author hieudt
 */
public class CCFcdbDAO {

    private static final Logger LOGGER = Logger.getLogger(CCFcdbDAO.class);
    // bo OBDX_ADMIN_PROD de test tren mt uat
    final String CC_IB_CUST_INFO = "{ ? = call OBDX_ADMIN_UAT.DVKH_PKG.IB_CUST_INFO(?)}";
    final String CC_SMS_CUST_INFO = "{ ? = call OBDX_ADMIN_PROD.DVKH_PKG.SMS_CUST_INFO(?)}";
    final String CC_SMSALERT_CUST_INFO = "{ ? = call SMS_SCB.PKG_DVKH_GW.SMSALERT_CUST_INFO(?)}";
    final String CC_SMS_ALERT_TD_INFO = " { ? = call SMS_SCB.PKG_DVKH_GW.SMS_ALERT_TD_INFO(?)}";
    final String CC_CUST_MBANKING_INFO = "{ ? = call SMS_SCB.PKG_DVKH_GW.CUST_MBANKING_INFO(?)}";
    final String CC_GET_CUST_SMS_HIST = "{ ? = call OBDX_ADMIN_PROD.DVKH_PKG.CUST_SMS_SENDER(?,?,?,?)}";
    final String CC_GetTTHD_TRANS_HIST = "{ ? = call SMS_SCB.PKG_DVKH_GW.TTHD_HIST_TRANS(?)}";
    final String CC_GetTTHD_AUTO_CONTRACT = "{ ? = call SMS_SCB.PKG_DVKH_GW.TTHD_AUTO_CONTRACT(?)}";

    /**
     *
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String CC_IB_CUST_INFO(String CIF) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fcdb");
            calStmt = connection.prepareCall(CC_IB_CUST_INFO);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
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
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String CC_SMS_CUST_INFO(String CIF) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fcdb");
            calStmt = connection.prepareCall(CC_SMS_CUST_INFO);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
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
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String CC_SMSALERT_CUST_INFO(String CIF) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("smsscb");
            calStmt = connection.prepareCall(CC_SMSALERT_CUST_INFO);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
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
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String CC_SMS_ALERT_TD_INFO(String CIF) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("smsscb");
            calStmt = connection.prepareCall(CC_SMS_ALERT_TD_INFO);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
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
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String CC_CUST_MBANKING_INFO(String CIF) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("smsscb");
            calStmt = connection.prepareCall(CC_CUST_MBANKING_INFO);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
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
    public String CC_GET_SMS_HIST(String param) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("fcdb");
            calStmt = connection.prepareCall(CC_GET_CUST_SMS_HIST);
            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CLOB);

            //Slit paramenters
            String[] params = param.split("#");

            calStmt.setString(2, params[0]);
            calStmt.setString(3, params[1]);

            SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd");
            Date fromDate = sdt.parse(params[2]);
            Date toDate = sdt.parse(params[3]);

            calStmt.setDate(4, new java.sql.Date(fromDate.getTime()));
            calStmt.setDate(5, new java.sql.Date(toDate.getTime()));

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
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String CC_GetTTHD_TRANS_HIST(String CIF) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("smsscb");
            calStmt = connection.prepareCall(CC_GetTTHD_TRANS_HIST);

            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
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
     * @param CIF
     * @return
     * @throws SQLException
     */
    public String CC_GetTTHD_AUTO_CONTRACT(String CIF) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("smsscb");
            calStmt = connection.prepareCall(CC_GetTTHD_AUTO_CONTRACT);

            calStmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
            calStmt.setString(2, CIF);
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
}
