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
import scb.com.vn.dbi.dao.CTTQDAO;
import scb.com.vn.dbi.utility.HibernateUtil;
import scb.com.vn.dbi.dto.SmlFtCounter;

/**
 *
 * @author minhndb
 */
public class CTTQBO extends BaseSMSSCBBO {

    private static final Logger LOGGER = Logger.getLogger(CTTQBO.class);

    private Connection conn;
    private CTTQDAO dao = null;

    /**
     * Create a new instance of CTTQBO
     * @throws java.lang.Exception
     */
    public CTTQBO() throws Exception {
        dao = new CTTQDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactorySmsScb());
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> GET_BANK_LIST() throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.GET_BANK_LIST();
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
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
     * @param cust_no
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public ArrayList GET_MOBILE_NUMBER(String cust_no) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.GET_MOBILE_NUMBER(cust_no);
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
     * @param obj
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String[] INSERT_CTTQ(SmlFtCounter obj) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_CTTQ(obj);
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
     * @param pID
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<SmlFtCounter> getDataCTTQ(String pID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getDataCTTQ(pID);
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
     * @param status
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public ArrayList<?> CHECK_STATUS_IBT(String status) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.CHECK_STATUS_IBT(status);
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
     * @param pID
     * @param pUSER
     * @param pBRANCHID
     * @param pSTATUS
     * @param pTYPEFUNCTION
     * @param pREFCORE
     * @throws SQLException
     * @throws RemoteException
     */
    public int DUYET_CTTQ(String pID, String pUSER, String pBRANCHID, String pSTATUS, String pTYPEFUNCTION, String pREFCORE) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.DUYET_CTTQ(pID, pUSER, pBRANCHID, pSTATUS, pTYPEFUNCTION, pREFCORE);
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
     * @param id
     * @param user
     * @param branch
     * @param status
     * @param refcore
     * @throws SQLException
     * @throws RemoteException
     */
    public void IBT_TUCHOI_CTTQ(String id, String user, String branch, String status, String refcore) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.IBT_TUCHOI_CTTQ(id, user, branch, status, refcore);
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
     * @param madv
     * @param trangthai
     * @param tungay
     * @param denngay
     * @param tknguon
     * @param tkdich
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public ArrayList<?> SEARCH_IBT(String madv, String trangthai, String tungay, String denngay, String tknguon, String tkdich) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.SEARCH_IBT(madv, trangthai, tungay, denngay, tknguon, tkdich);
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
     * @param ID
     * @return
     * @throws Exception
     */
    public ArrayList<?> CHECK_TRANSFER(String ID) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.CHECK_TRANSFER(ID);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    //son 28/10/2019

    /**
     *
     * @param pGL
     * @param brcode
     * @param ccy
     * @param type
     * @return
     * @throws Exception
     */
    public float GET_GL_BALANCE_BO(String pGL, String brcode, String ccy, String type) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.get_GL_BALANCE_DAO(pGL, brcode, ccy, type);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
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
     * @param ID
     * @return
     * @throws Exception
     */
    public int DELETE_CTTQ_BO(String ID) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.DELETE_CTTQ_Dao(ID);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error("Can't close connection: " + sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

}
