package scb.com.vn.dbi.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import org.apache.log4j.Logger;

/**
 *
 * @author minhndb
 */
public class LogDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(LogDAO.class);

    final String SP_LOG = "INSERTLOG";

    /**
     *
     * @param logger
     * @param lvl
     * @param msg
     * @return
     * @throws Exception
     */
    public int insertLog(String logger, String lvl, String msg) throws Exception {
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + SP_LOG + " (?,?,?, ?,?); END;");
            calStmt.setString(1, logger);
            calStmt.setString(2, lvl);
            calStmt.setString(3, msg);
            calStmt.registerOutParameter(4, Types.INTEGER);
            calStmt.registerOutParameter(5, Types.NVARCHAR);
            calStmt.execute();

            if (calStmt.getInt(4) == -1) {
                throw new Exception(calStmt.getString(5));
            }
            return calStmt.getInt(4);
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
}
