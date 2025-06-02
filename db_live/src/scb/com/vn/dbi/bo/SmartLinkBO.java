/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dao.SmartLinkDAO;
import scb.com.vn.dbi.dto.SMLCollate;
import scb.com.vn.dbi.utility.HibernateUtil;

/**
 *
 * @author Administrator
 */
public class SmartLinkBO extends BaseSMSSCBBO {

    private static final Logger LOGGER = Logger.getLogger(SmartLinkBO.class);

    private Connection conn;
    private SmartLinkDAO dao = null;

    /**
     * Create a new instance of SmartLinkBO
     * @throws java.lang.Exception
     */
    public SmartLinkBO() throws Exception {
        dao = new SmartLinkDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactorySmsScb());
    }

    /**
     *
     * @param pTYPETRANSFER
     * @param pFROMNUMBER
     * @param pPROCESSINGCODE
     * @param pTRANSAMOUNT
     * @param pTRANSDATE
     * @param pAUDITNUMBER
     * @param pMERCHANTTYPE
     * @param pACQUIRINGCODE
     * @param pAUTHORIZATIONCODE
     * @param pRESPONSECODE
     * @param pTERMID
     * @param pCARDACCEPTTOR
     * @param pDESTNUMBER
     * @param pNARRATION
     * @param pBENID
     * @param pTYPEFUNCTION
     * @param pStatus
     * @param pRefCore
     * @param pCustNo
     * @param RefCORE_REFUND
     * @param pREF_NO_F37
     * @param pSETT_DATE_F15
     * @throws Exception
     */
    public void InsertLOG(String pTYPETRANSFER, String pFROMNUMBER, String pPROCESSINGCODE,
            BigDecimal pTRANSAMOUNT, String pTRANSDATE,
            String pAUDITNUMBER, String pMERCHANTTYPE,
            String pACQUIRINGCODE, String pAUTHORIZATIONCODE, String pRESPONSECODE,
            String pTERMID, String pCARDACCEPTTOR, String pDESTNUMBER,
            String pNARRATION, String pBENID, String pTYPEFUNCTION,
            String pStatus, String pRefCore, String pCustNo,
            String RefCORE_REFUND, String pREF_NO_F37, String pSETT_DATE_F15) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.InsertLOG(pTYPETRANSFER, pFROMNUMBER, pPROCESSINGCODE, pTRANSAMOUNT, pTRANSDATE, pAUDITNUMBER, pMERCHANTTYPE, pACQUIRINGCODE, pAUTHORIZATIONCODE, pRESPONSECODE, pTERMID, pCARDACCEPTTOR, pDESTNUMBER, pNARRATION, pBENID, pTYPEFUNCTION, pStatus, pRefCore, pCustNo, RefCORE_REFUND, pREF_NO_F37, pSETT_DATE_F15);
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
     * @param pCardNumber
     * @param pCardHolderName
     * @param pAmount
     * @return
     * @throws Exception
     */
    public String[] getCardInfo(String pCardNumber, String pCardHolderName, BigDecimal pAmount) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getCardInfo(pCardNumber, pCardHolderName, pAmount);
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
     * @param pTypeTransfer
     * @return
     * @throws Exception
     */
    public String getSMLAccount(String pTypeTransfer) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getSMLAccount(pTypeTransfer);
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
    public String getAuditNumber() throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getAuditNumber();
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
    public String getAuthorizationCode() throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getAuthorizationCode();
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
     * @param pCardNumber
     * @return
     * @throws Exception
     */
    public String[] getCardStatus(String pCardNumber) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getCardStatus(pCardNumber);
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
     * @param pAuditNumber
     * @return
     * @throws Exception
     */
    public boolean checkAuditNumberOfSML(String pAuditNumber) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkAuditNumberOfSML(pAuditNumber);
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
     * @param pCustNo
     * @param pAmount
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public String checkLimitAmount(String pCustNo, BigDecimal pAmount) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkLimitAmount(pCustNo, pAmount);
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
     * @param sml
     * @throws SQLException
     * @throws RemoteException
     */
    public void InsertSMLCollated(SMLCollate sml) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.InsertSMLCollated(sml);
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
     * @param pcollatedate
     * @param pfilename
     * @param pTypefile
     * @param pSumrecord
     * @throws SQLException
     * @throws RemoteException
     */
    public void insertCollateDate(Date pcollatedate, String pfilename, String pTypefile, int pSumrecord) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertCollateDate(pcollatedate, pfilename, pTypefile, pSumrecord);
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
     * @param pFile
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public int checkFile(String pFile) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkFile(pFile);
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
     * @param pTypefile
     * @return
     * @throws SQLException
     * @throws RemoteException
     */
    public List<SMLCollate> getOutCollate(String pTypefile) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getOutCollate(pTypefile);
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
    public int checkCounter(String ID) throws SQLException, RemoteException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkCounter(ID);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
     public void updateIBT(String pTrace ,String pStatus ,
  String pRefcore ,String pRefcoreRefund ,String pREF_NO_F37 ,
         String pSETT_DATE_F15 ,
         String pTypeTransfer ) throws RemoteException, SQLException
     {
      
         try {
            conn = super.getConnection();
            dao.setConnection(conn);
             dao.updateIBT(pTrace, pStatus, pRefcore, pRefcoreRefund, pREF_NO_F37, pSETT_DATE_F15, pTypeTransfer);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
     }
     public void updateIBTResponse(String pTrace ,String pStatus ,
  String pResponseCode ,String pRefcoreRefund, String pSett_date_F15 )
    throws RemoteException, SQLException
     {
      
         try {
            conn = super.getConnection();
            dao.setConnection(conn);
             dao.updateIBTResponse(pTrace, pStatus, pResponseCode, pRefcoreRefund, pSett_date_F15);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
     }
     public int InsertIBTLOG(String pTYPETRANSFER, String pFROMNUMBER, String pPROCESSINGCODE,
            BigDecimal pTRANSAMOUNT, String pTRANSDATE,
            String pAUDITNUMBER, String pMERCHANTTYPE,
            String pACQUIRINGCODE, String pAUTHORIZATIONCODE, String pRESPONSECODE,
            String pTERMID, String pCARDACCEPTTOR, String pDESTNUMBER,
            String pNARRATION, String pBENID, String pTYPEFUNCTION,
            String pStatus, String pRefCore, String pCustNo,
            String RefCORE_REFUND, String pREF_NO_F37, String pSETT_DATE_F15) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.InsertIBTLOG(pTYPETRANSFER, pFROMNUMBER, pPROCESSINGCODE, pTRANSAMOUNT, pTRANSDATE, pAUDITNUMBER, pMERCHANTTYPE, pACQUIRINGCODE, pAUTHORIZATIONCODE, pRESPONSECODE, pTERMID, pCARDACCEPTTOR, pDESTNUMBER, pNARRATION, pBENID, pTYPEFUNCTION, pStatus, pRefCore, pCustNo, RefCORE_REFUND, pREF_NO_F37, pSETT_DATE_F15);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
     public String getSeqRefNo(String type) throws Exception 
     {
         try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getSeqRefNo(type);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
     }
    public int InsertIBTLOGXref(String pTYPETRANSFER, String pFROMNUMBER, String pPROCESSINGCODE,
            BigDecimal pTRANSAMOUNT, String pTRANSDATE,
            String pAUDITNUMBER, String pMERCHANTTYPE,
            String pACQUIRINGCODE, String pAUTHORIZATIONCODE, String pRESPONSECODE,
            String pTERMID, String pCARDACCEPTTOR, String pDESTNUMBER,
            String pNARRATION, String pBENID, String pTYPEFUNCTION, String pStatus,
            String pRefCore, String pCUSTNO, String RefCORE_REFUND, String pREF_NO_F37, String pSETT_DATE_F15,String Xref)  throws Exception 
    {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.InsertIBTLOGXref(pTYPETRANSFER, pFROMNUMBER, pPROCESSINGCODE, pTRANSAMOUNT, pTRANSDATE, pAUDITNUMBER, pMERCHANTTYPE, pACQUIRINGCODE, pAUTHORIZATIONCODE, pRESPONSECODE, pTERMID, pCARDACCEPTTOR, pDESTNUMBER, pNARRATION, pBENID, pTYPEFUNCTION, pStatus, pRefCore, pCUSTNO, RefCORE_REFUND, pREF_NO_F37, pSETT_DATE_F15, Xref);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    public void updateIBTResponse(String pTrace ,String pStatus ,
  String pResponseCode ,String pRefcoreRefund, String pSett_date_F15 ,String ID)
    throws RemoteException, SQLException
     {
      
         try {
            conn = super.getConnection();
            dao.setConnection(conn);
             dao.updateIBTResponse(pTrace, pStatus, pResponseCode, pRefcoreRefund, pSett_date_F15,ID);
        } catch (Exception ex) {
           // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
     }
     public int updateIBT(String AuditNumber,String Status,String RefCORE,String typeTransfer,String ID,String xref) throws Exception 
     {
         try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.updateIBT(AuditNumber, Status, RefCORE, typeTransfer, ID,xref);
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
     }
     public int updateResultToSCB(String AuditNumber,String Status,String RefCORE,String ID,String responsecode) throws Exception {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.updateResultToSCB(AuditNumber, Status, RefCORE, ID, responsecode);
        } catch (Exception ex) {
           // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
     public ArrayList<?> searchBlackList(String destnumber, String bankcode, int isapprove) throws Exception {
       try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.searchBlackList(destnumber, bankcode,isapprove);
        } catch (Exception ex) {
           // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
     public int updateBlackList(String id,String userapprove,int isapprove) throws Exception {
       try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.updateBlackList(id, userapprove, isapprove);
        } catch (Exception ex) {
           // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
     
     public int insertBlackList (String destnumber, String type, String bankcode, String userid, String branchcode, String benename)throws Exception {
         try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.insertBlackList(destnumber, type, bankcode, userid, branchcode, benename);
        } catch (Exception ex) {
           // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
     }
    public ArrayList<?> getInforBlackList(String id, int isapprove) throws Exception {
       try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getInforBlackList(id,isapprove);
        } catch (Exception ex) {
           // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
