/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cims.register.FeedbackInfo;
import scb.com.vn.common.odbx.IsExistUserEBRes;
import scb.com.vn.common.odbx.SCBBranch;
import scb.com.vn.dbi.dao.BdobdxDAO;
import scb.com.vn.dbi.dto.EBANKUSER;

/**
 *
 * @author lydty
 */
public class BdobdxBO extends BaseBbobdxBO {

    private static final Logger LOGGER = Logger.getLogger(BdobdxBO.class);
    private Connection conn;
    private BdobdxDAO dao = null;

    /**
     * Create a new instance of BdobdxBO
     */
    public BdobdxBO() {
        dao = new BdobdxDAO();
    }

    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public ArrayList getDetailUserEB(String username) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDetailUserEB(username);
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
     * @param userid
     * @return
     * @throws Exception
     */
    public EBANKUSER getInfoEbankUser(String userid) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getInfoEbankUser(userid);
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
     * @param username
     * @param newpass
     * @param channel
     * @return
     * @throws Exception
     */
    public int resetPass(String username, String newpass, String channel) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.resetPass(username, newpass, channel);
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
     * @param userName
     * @param cif
     * @return
     * @throws Exception
     */
    public IsExistUserEBRes isExistUserBanking(String userName, String cif) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.isExistUserBanking(userName, cif);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<FeedbackInfo> getListFeedback(String isChecked) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getListFeedback(isChecked);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int updateFeedbackId(String isChecked, String idFeedback, String idChecker, String idChannelChecker) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.updateFeedbackId(isChecked, idFeedback, idChecker, idChannelChecker);
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
     * GetUserOdbxDtl
     * 
     * @param username
     * @return
     * @throws Exception
     */
    public ArrayList getUserOdbxDtl(String username) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getUserOdbxDtl(username);
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
     * GetUserDeviceOdbxInfo
     * 
     * @param soCif
     * @return
     * @throws Exception
     */
    public ArrayList getUserDeviceOdbxInfo(String soCif) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getUserDeviceOdbxInfo(soCif);
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
     * UpdateUserAdditionalOdbx
     * 
     * @param userName
     * @param deviceAllowed
     * @return
     * @throws RemoteException
     * @throws SQLException 
     */
      public int updateUserAdditionalOdbx(String userName, String deviceAllowed) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.updateUserAdditionalOdbx(userName, deviceAllowed);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
      
}
