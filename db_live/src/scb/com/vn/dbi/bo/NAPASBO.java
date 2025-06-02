/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import scb.com.vn.dbi.dao.NAPASDAO;
import scb.com.vn.dbi.dto.NapasCollatedId;
import scb.com.vn.dbi.utility.HibernateUtil;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dto.NAPAS_IBT;

/**
 *
 * @author lydty
 */
public class NAPASBO extends BaseSMSSCBBO {

    private static final Logger LOGGER = Logger.getLogger(NAPASBO.class);

    private Connection conn;
    private NAPASDAO dao = null;

    /**
     * Create a new instance of NAPASBO
     * @throws java.lang.Exception
     */
    public NAPASBO() throws Exception {
        dao = new NAPASDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactorySmsScb());
    }

    /**
     *
     * @param pCOLLATEDDATE
     * @param pFiletype
     * @param pTRANSTYPE
     * @param pSERVICE
     * @param pFileName
     * @param pPARTNERID
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String CHECK_LOG_DATE_COLLATED(String pCOLLATEDDATE,
            String pFiletype,
            String pTRANSTYPE,
            String pSERVICE,
            String pFileName,
            String pPARTNERID
    ) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.CHECK_LOG_DATE_COLLATED(pCOLLATEDDATE, pFiletype, pTRANSTYPE, pSERVICE, pFileName, pPARTNERID);
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
     * @param pTRANSTYPE
     * @param pCOLLATEDDATE
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<NapasCollatedId> getIBTCollateOut(
            String pTRANSTYPE,
            String pCOLLATEDDATE
    ) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getIBTCollateOut(pTRANSTYPE, pCOLLATEDDATE);
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
     * @throws RemoteException
     * @throws SQLException
     */
    public void InsertDataCollated(NapasCollatedId obj) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.InsertDataCollated(obj);
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
     * @param request
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String InsertIBTLog(String request) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return  dao.InsertIBTLog(request);
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
     * @param reponse
     * @param ID
     * @throws RemoteException
     * @throws SQLException
     */
    public void insertResponseIBT(String reponse, String ID) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertResponseIBT(reponse, ID);
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
     * @throws SQLException
     * @throws RemoteException
     */
    public String getResponseIBT(String ID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getResponseIBT(ID);
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
     * @return @throws SQLException
     * @throws RemoteException
     */
    public String getRequestIBT() throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getRequestIBT();
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
     * @throws RemoteException
     * @throws SQLException
     */
    public int InsertLOG(NAPAS_IBT obj) throws RemoteException, SQLException
    {
         try
        {
            conn = super.getConnection();
            dao.setConnection(conn);
            // dao.InsertLOGv2(obj);
            int id =dao.InsertIBTTranfer(obj);
            return id;
         }
        catch (Exception ex) {
            LOGGER.error(ex);
        }   
        finally {
            if (conn != null) {
                conn.close();
            }
        }
         return 0;
    }

    /**
     *
     * @param ID
     * @throws SQLException
     * @throws RemoteException
     */
    public int updateSendIBT(String ID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.updateSendIBT(ID);
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }

    /**
     *
     * @param pTRANSTYPE
     * @param isCollated
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<NapasCollatedId> getIBTCollateOut(
            String pTRANSTYPE,
            int isCollated
    ) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getIBTCollateOut(pTRANSTYPE, isCollated);
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
     * @param FromNumber
     * @param AuditNumber
     * @param MerchantType
     * @param Transdate
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public ArrayList<?> isExistTransfer(String FromNumber, String AuditNumber, String MerchantType,
            String Transdate) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.isExistTransfer(FromNumber, AuditNumber, MerchantType, Transdate);
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
     * @param refno
     * @return
     * @throws Exception
     */
    public Object[] getRequestTranfer(String trace,String status) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getRequestTranfer(trace,status);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    public void UPDATE_STATUS_TRANSFER(String pTrace ,
         String pStatus ,
         String pRefcore ,
         String pRefcoreRefund ,
         String pREF_NO_F37 ,
         String pSETT_DATE_F15 ,
         String pTypeTransfer ,
         String pResponseCode ,
         String pAuthorizationcode ,
         String pTransREFNOF63 ,
         String pF5 ) throws RemoteException, SQLException
     {
         try
        {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UPDATE_STATUS_TRANSFER(pTrace, pStatus, pRefcore, pRefcoreRefund, pREF_NO_F37, pSETT_DATE_F15, pTypeTransfer, pResponseCode, pAuthorizationcode, pTransREFNOF63, pF5);
         }
        catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }   
        finally {
            if (conn != null) {
                conn.close();
            }
        }  
     }
    public void UpdateRevertTransfer(String pTrace ,
         String pRefcore ,
         String pRefcoreRefund,String ID ) throws SQLException,RemoteException{
        try
        {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UpdateRevertTransfer(pTrace, pRefcore, pRefcoreRefund,ID);
         }
        catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }   
        finally {
            if (conn != null) {
                conn.close();
            }
        } 
    }  
    public int insertIBTRequestLog(String trace) throws Exception {
         try
        {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.insertIBTRequestLog(trace);
         }
        catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }   
        finally {
            if (conn != null) {
                conn.close();
            }
        } 
    }
     public void UPDATEIBTRANSFER(String pTrace ,
         String pStatus ,
         String pREF_NO_F37 ,
         String pSETT_DATE_F15 ,
         String pResponseCode ,
         String pAuthorizationcode ,
         String pTransREFNOF63 ,
         String pF5) throws Exception 
    {
        try
        {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.UPDATEIBTRANSFER(pTrace, pStatus, pREF_NO_F37, pSETT_DATE_F15, pResponseCode, pAuthorizationcode, pTransREFNOF63, pF5);
         }
        catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }   
        finally {
            if (conn != null) {
                conn.close();
            }
        } 
        
    }
     public void updateSendIBT_response(String ID,String response) throws Exception 
    {
        try
        {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.updateSendIBT_response(ID, response);
         }
        catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }   
        finally {
            if (conn != null) {
                conn.close();
            }
        } 
    }
     public int updateCannotSend(String trace) throws Exception
     {
         try
        {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.updateCannotSend(trace);
         }
        catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }   
        finally {
            if (conn != null) {
                conn.close();
            }
        } 
     }
     public String checkCifNoCredit(String cif) throws Exception
     {
          try
        {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkCifNoCredit(cif);
         }
        catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }   
        finally {
            if (conn != null) {
                conn.close();
            }
        } 
     }
}
