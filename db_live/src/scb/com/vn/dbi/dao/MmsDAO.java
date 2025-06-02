/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.connection.ConnectionManager;

/**
 *
 * @author minhndb
 */
public class MmsDAO {

    private static final Logger LOGGER = Logger.getLogger(MmsDAO.class);

    final String MMSCHECKACCOUNT = "BEGIN PKG_LOCAL_SYSTEM.PROC_CHECK_ACCOUNT_MERCHANT (?,?,?,?); END;";

    /**
     *
     * @param P_MERCHANT_CODE
     * @param P_TERMINAL_ID
     * @param P_ACCOUNT_MERCHANT
     * @return
     * @throws SQLException
     */
    public boolean MMSCHECKACCOUNT(String P_MERCHANT_CODE, String P_TERMINAL_ID, String P_ACCOUNT_MERCHANT) throws SQLException {
        Connection connection = null;
        CallableStatement calStmt = null;
        try {
            connection = ConnectionManager.getConnection("mms");
            calStmt = connection.prepareCall(MMSCHECKACCOUNT);
            calStmt.setString(1, P_MERCHANT_CODE);
            calStmt.setString(2, P_TERMINAL_ID);
            calStmt.setString(3, P_ACCOUNT_MERCHANT);
            calStmt.registerOutParameter(4, OracleTypes.INTEGER);
            calStmt.execute();
            int count = calStmt.getInt(4);
            return count == 1;
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
