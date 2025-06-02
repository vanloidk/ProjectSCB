package scb.com.vn.dbi.bo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import org.apache.log4j.Logger;

import scb.com.vn.dbi.dao.PartnerDAO;
import scb.com.vn.dbi.dto.PartnerDTO;

/**
 *
 * @author minhndb
 */
public class PartnerBO extends BaseGWBO {

    private static final Logger LOGGER = Logger.getLogger(PartnerBO.class);

    private Connection conn;
    private PartnerDAO partnerDAO;

    /**
     * Create a new instance of PartnerBO
     */
    public PartnerBO() {
        partnerDAO = new PartnerDAO();
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<Hashtable<String, String>> getallPartner() throws Exception {
        try {
            conn = super.getConnection();
            partnerDAO.setConnection(conn);
            return partnerDAO.getallPartner();
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
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }

    /**
     *
     * @param accesskey
     * @return
     * @throws Exception
     */
    public PartnerDTO getPartnerByAccesskey(String accesskey) throws Exception {
        try {
            conn = super.getConnection();
            partnerDAO.setConnection(conn);
            return partnerDAO.getPartnerByAccesskey(accesskey);
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

    /**
     *
     * @return @throws Exception
     */
    public ArrayList getGwPermissions() throws Exception {
        try {
            conn = super.getConnection();
            partnerDAO.setConnection(conn);
            return partnerDAO.getGwPermissions();
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
