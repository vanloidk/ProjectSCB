package scb.com.vn.dbi.bo;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.collated.CollatedDetail;

import scb.com.vn.dbi.dao.SmsDAO;

/**
 *
 * @author minhndb
 */
public class SmsBO extends BaseSMSBO {

    private static final Logger LOGGER = Logger.getLogger(SmsBO.class);

    private Connection conn;
    private SmsDAO smsDAO;

    /**
     * Create a new instance of SmsBO
     */
    public SmsBO() {
        smsDAO = new SmsDAO();
    }

    /**
     *
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws Exception
     */
    public int sendSMS(String mobile, String content, String servicecode, String requestid) throws Exception {
        try {
            conn = super.getConnection();
            smsDAO.setConnection(conn);
            return smsDAO.sendSMS(mobile, content, servicecode, requestid);
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
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws Exception
     */
    public int sendSMS8149(String mobile, String content, String servicecode, String requestid) throws Exception {
        try {
            conn = super.getConnection();
            smsDAO.setConnection(conn);
            return smsDAO.sendSMS8149(mobile, content, servicecode, requestid);
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
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws Exception
     */
    public int sendSMSTemp(String mobile, String content, String servicecode, String requestid) throws Exception {
        try {
            conn = super.getConnection();
            smsDAO.setConnection(conn);
            return smsDAO.sendSMSTemp(mobile, content, servicecode, requestid);
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
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws Exception
     */
    public int sendSMSTNB(String mobile, String content, String servicecode, String requestid) throws Exception {
        try {
            conn = super.getConnection();
            smsDAO.setConnection(conn);
            return smsDAO.sendSMSTNB(mobile, content, servicecode, requestid);
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
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public int updateStatus(String id, String status) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            smsDAO.setConnection(conn);
            return smsDAO.updateStatus(id, status);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
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
    public ArrayList getAllSmsReceiver() throws Exception {
        try {
            conn = super.getConnection();
            smsDAO.setConnection(conn);
            return smsDAO.getAllSmsReceiver();
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
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    public int doMoveSmsReceiverToHistory(String id, String status) throws Exception {
        try {
            conn = super.getConnection();
            smsDAO.setConnection(conn);
            return smsDAO.doMoveSmsReceiverToHistory(id, status);
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
    
     public List<CollatedDetail> getListDataCollated(String partnerId, String transDate) throws Exception {
        try {
            conn = super.getConnection();
            smsDAO.setConnection(conn);
            return smsDAO.getListDataCollated(partnerId, transDate);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
