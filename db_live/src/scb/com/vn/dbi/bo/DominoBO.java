package scb.com.vn.dbi.bo;

import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dao.DominoDAO;
import scb.com.vn.dbi.utility.HibernateUtil;

/**
 *
 * @author minhndb
 */
public class DominoBO extends BaseDOMINOBO {

    private static final Logger LOGGER = Logger.getLogger(DominoBO.class);

    private Connection conn;
    private DominoDAO dominoDAO = null;

    /**
     * Create a new instance of DominoBO
     * @throws java.lang.Exception
     */
    public DominoBO() throws Exception {
        dominoDAO = new DominoDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactoryDomino());
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getExchangeRate() throws Exception {
        try {
            conn = super.getConnection();
            dominoDAO.setConnection(conn);
            return dominoDAO.getExchangeRate();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getGoldRate() throws Exception {
        try {
            conn = super.getConnection();
            dominoDAO.setConnection(conn);
            return dominoDAO.getGoldRate();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }

}
