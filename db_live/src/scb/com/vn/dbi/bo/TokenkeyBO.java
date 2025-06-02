package scb.com.vn.dbi.bo;

import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import scb.com.vn.dbi.dao.TokenkeyDAO;

/**
 *
 * @author minhndb
 */
public class TokenkeyBO extends BaseSMSSCBBO {

    private static final Logger LOGGER = Logger.getLogger(TokenkeyBO.class);

    private Connection conn;
    private TokenkeyDAO tokenkeyDAO;

    /**
     * Create a new instance of TokenkeyBO
     */
    public TokenkeyBO() {
        tokenkeyDAO = new TokenkeyDAO();
    }

    /**
     *
     * @param serialno
     * @return
     * @throws Exception
     */
    public ArrayList getTokenkeybankBySerial(String serialno) throws Exception {
        try {
            conn = super.getConnection();
            tokenkeyDAO.setConnection(conn);
            return tokenkeyDAO.getTokenkeybankBySerial(serialno);
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
                conn.close();
            }
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public String getTransID() throws Exception {
        try {
            conn = super.getConnection();
            tokenkeyDAO.setConnection(conn);
            return tokenkeyDAO.getTransID();
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
                conn.close();
            }
        }
    }

    /**
     *
     * @param serialno
     * @param challengeid
     * @return
     * @throws Exception
     */
    public ArrayList getMKVerifyInfo(String serialno, String challengeid) throws Exception {
        try {
            conn = super.getConnection();
            tokenkeyDAO.setConnection(conn);
            return tokenkeyDAO.getMKVerifyInfo(serialno, challengeid);
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
                conn.close();
            }
        }
    }

    /**
     *
     * @param SERIAL
     * @param CHALLENGE
     * @param TRANSID
     * @param AUDITID
     * @return
     * @throws Exception
     */
    public int INSERT_MK_TOKEN_LOG(String SERIAL, String CHALLENGE, String TRANSID, String AUDITID) throws Exception {
        try {
            conn = super.getConnection();
            tokenkeyDAO.setConnection(conn);
            return tokenkeyDAO.INSERT_MK_TOKEN_LOG(SERIAL, CHALLENGE, TRANSID, AUDITID);
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
                conn.close();
            }
        }
    }
}
