package scb.com.vn.dbi.bo;

import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dao.VninfoDAO;

import scb.com.vn.dbi.utility.HibernateUtil;

/**
 *
 * @author minhndb
 */
public class VninfoBO extends BaseVNINFOBO {

    private static final Logger LOGGER = Logger.getLogger(VninfoBO.class);

    private Connection conn;
    private VninfoDAO vninfoDAO = null;

    /**
     * Create a new instance of VninfoBO
     * @throws java.lang.Exception
     */
    public VninfoBO() throws Exception {
        vninfoDAO = new VninfoDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactoryVninfo());

    }

    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCustomerFromMobile(String username) throws Exception {
        try {
            conn = super.getConnection();
            vninfoDAO.setConnection(conn);
            return vninfoDAO.getCustomerFromMobile(username);
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
    //duytxa add 30.08.2017

    /**
     *
     * @param cif
     * @param status
     * @return
     * @throws Exception
     */
    public ArrayList<?> getMobileUserFromCif(String cif, String status) throws Exception {
        try {
            conn = super.getConnection();
            vninfoDAO.setConnection(conn);
            return vninfoDAO.getMobileUserFromCif(cif, status);
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
     * @param userName
     * @param cif
     * @param channel
     * @return
     * @throws Exception
     */
    public ArrayList<?> isExistUserMBanking(String userName, String cif, String channel) throws Exception {
        try {
            conn = super.getConnection();
            vninfoDAO.setConnection(conn);
            return vninfoDAO.isExistUserMBanking(userName,cif,channel);
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
    
      public ArrayList<?> getPass(String userName, String cif) throws Exception {
        try {
            conn = super.getConnection();
            vninfoDAO.setConnection(conn);
            return vninfoDAO.getPass(userName,cif);
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
