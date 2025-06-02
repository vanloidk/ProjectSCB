package scb.com.vn.dbi.bo;

import java.sql.Connection;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dao.LogDAO;

/**
 *
 * @author minhndb
 */
public class LogBO extends BaseGWBO {

    private static final Logger LOGGER = Logger.getLogger(LogBO.class);

    private Connection conn;
    private LogDAO logDAO;

    /**
     * Create a new instance of LogBO
     */
    public LogBO() {
        logDAO = new LogDAO();
    }

    /**
     *
     * @param logger
     * @param lvl
     * @param msg
     * @return
     * @throws Exception
     */
    public int insertLog(String logger, String lvl, String msg) throws Exception {
        try {
            conn = super.getConnection();
            logDAO.setConnection(conn);
            return logDAO.insertLog(logger, lvl, msg);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
                conn = null;
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
