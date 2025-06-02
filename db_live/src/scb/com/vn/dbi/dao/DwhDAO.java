/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import scb.com.vn.dbi.connection.ConnectionManager;
import scb.com.vn.ultility.jdbc.JDBCEngine;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.controller.DBIImpl;
import scb.com.vn.dbi.controller.IDBI;

/**
 *
 *
 * @author kimncm
 */
public class DwhDAO {

    private static final Logger LOGGER = Logger.getLogger(DwhDAO.class);
    final String FCC_VW_TXNACCOUNTACTIVITY_SCB = " SELECT * FROM FCC_VW_TXNACCOUNTACTIVITY_SCB(to_date(?,'yyyyMMdd'),to_date(?,'yyyyMMdd'),?,?,?,?)";

    /**
     * Lay lich su giao dich tk tt tu DWH
     * @param accountno
     * @param fromDate
     * @param toDate
     * @param srno
     * @return
     * @throws SQLException
     */
    public ArrayList getTransationListByAccNew(String accountno, String fromDate, String toDate, String srno) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("dwh");
            ArrayList p_args = new ArrayList();
            p_args.add(fromDate);
            p_args.add(toDate);
            if (srno == null || srno.isEmpty() || srno.equals("0")) {
                srno = "0";
            }
            IDBI dbi = new DBIImpl();
            String accbrn = dbi.GET_BRANCHCODE_FROM_FCC(accountno);
            p_args.add(accbrn);
            p_args.add(accountno);
            p_args.add(srno);
            p_args.add("10");

            ArrayList list = JDBCEngine.executeQuery(FCC_VW_TXNACCOUNTACTIVITY_SCB, p_args, connection);
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
}
