package scb.com.vn.dbi.bo;

import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import scb.com.vn.dbi.dao.IbsDAO;

/**
 *
 * @author minhndb
 */
public class IbsBO extends BaseIBSBO {

    private static final Logger LOGGER = Logger.getLogger(IbsBO.class);

    private Connection conn;
    private IbsDAO ibsdao = null;

    /**
     * Create a new instance of IbsBO
     */
    public IbsBO() {
        ibsdao = new IbsDAO();
    }

    /**
     *
     * @param custid
     * @return
     * @throws Exception
     */
    public ArrayList<?> getIbsUserByCustId(String custid) throws Exception {
        try {
            conn = super.getConnection();
            ibsdao.setConnection(conn);
            return ibsdao.getIbsUserByCustId(custid);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    if (conn != null) {
                        conn.close();
                    }
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
     * @param isChangePwd
     * @param userid
     * @return
     * @throws Exception
     */
    public int updateIbsUserChangePwd(String isChangePwd, String userid) throws Exception {
        try {
            conn = super.getConnection();
            ibsdao.setConnection(conn);
            return ibsdao.updateIbsUserChangePwd(isChangePwd, userid);
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
