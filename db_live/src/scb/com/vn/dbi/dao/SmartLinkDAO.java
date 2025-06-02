/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dto.SMLCollate;
import scb.com.vn.dbi.utility.Helper;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author Administrator
 */
public class SmartLinkDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(SmartLinkDAO.class);

    final String InsertLOG = "BEGIN SML_IBT.PROC_INSERT_SML_FT_MASTER(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String getCardInfo = "BEGIN SML_IBT.getCardInfo(?,?,?,?,?); END;";
    final String getCardStatus = "BEGIN SML_IBT.getCardStatus(?,?,?,?); END;";
    final String getSMLAccount = "BEGIN SML_IBT.getSMLAccount(?,?); END;";
    final String getAuditNumber = "BEGIN SML_IBT.getAuditNumber(?); END;";
    final String getAuthorizationCode = "BEGIN SML_IBT.getAuthorizationCode(?); END;";
    final String checkAuditNumberOfSML = "BEGIN SML_IBT.checkAuditNumberOfSML(?,?); END;";
    final String checkLimitAmount = "BEGIN SML_IBT.checkLimitAmount(?,?,?); END;";
    final String insertSMLCollate = "BEGIN SML_IBT.insertSMLCollate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String checkFile = "BEGIN SML_IBT.checkFile(?,?); END;";
    final String insertCollateDate = "BEGIN SML_IBT.insertCollateDate(?,?,?,?); END;";
    final String getOutCollate = "BEGIN SML_IBT.getOutCollate(?,?); END;";

    final String updateIBT = "BEGIN PROC_IBT_UPDATE(?,?,?,?,?,?,?); END;";

    String searchBlackList = "select B.*, O.Bank_Name from SML_FT_BLACKLIST B \n" +
"       left join obdx_bene_bank O\n" +
"       on B.BANKCODE = O.BANK_CODE  where 1=1";
    String getInforBlackList = "select B.*,o.BANK_NAME from SML_FT_BLACKLIST B left join obdx_bene_bank O on B.BANKCODE = O.BANK_CODE  where id = ? and isapprove = ?";
    String updateBlackList = "update SML_FT_BLACKLIST set USERAPPROVE=?, isapprove=?, DATEAPPROVE=sysdate where id=?";

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
     * @param pCUSTNO
     * @param RefCORE_REFUND
     * @param pREF_NO_F37
     * @param pSETT_DATE_F15
     * @throws SQLException
     */
    public void InsertLOG(String pTYPETRANSFER, String pFROMNUMBER, String pPROCESSINGCODE,
            BigDecimal pTRANSAMOUNT, String pTRANSDATE,
            String pAUDITNUMBER, String pMERCHANTTYPE,
            String pACQUIRINGCODE, String pAUTHORIZATIONCODE, String pRESPONSECODE,
            String pTERMID, String pCARDACCEPTTOR, String pDESTNUMBER,
            String pNARRATION, String pBENID, String pTYPEFUNCTION, String pStatus,
            String pRefCore, String pCUSTNO, String RefCORE_REFUND, String pREF_NO_F37, String pSETT_DATE_F15) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(InsertLOG);
            calStmt.setString(1, pTYPETRANSFER);
            calStmt.setString(2, pFROMNUMBER);
            calStmt.setString(3, pPROCESSINGCODE);
            calStmt.setBigDecimal(4, pTRANSAMOUNT);
            calStmt.setString(5, pTRANSDATE);
            calStmt.setString(6, pAUDITNUMBER);
            calStmt.setString(7, pMERCHANTTYPE);
            calStmt.setString(8, pACQUIRINGCODE);
            calStmt.setString(9, pAUTHORIZATIONCODE);
            calStmt.setString(10, pRESPONSECODE);
            calStmt.setString(11, pTERMID);
            calStmt.setString(12, pCARDACCEPTTOR);
            calStmt.setString(13, pDESTNUMBER);
            calStmt.setString(14, pNARRATION);
            calStmt.setString(15, pBENID);
            calStmt.setString(16, pTYPEFUNCTION);
            calStmt.setString(17, pStatus);
            calStmt.setString(18, pRefCore);
            calStmt.setString(19, pCUSTNO);
            calStmt.setString(20, RefCORE_REFUND);
            calStmt.setString(21, pREF_NO_F37);
            calStmt.setString(22, pSETT_DATE_F15);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pCardNumber
     * @param pCardHolderName
     * @param pAmount
     * @return
     * @throws SQLException
     */
    public String[] getCardInfo(String pCardNumber, String pCardHolderName, BigDecimal pAmount) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getCardInfo);
            calStmt.setString(1, pCardNumber);
            calStmt.setString(2, pCardHolderName);
            calStmt.setBigDecimal(3, pAmount);
            calStmt.registerOutParameter(4, Types.VARCHAR); //account of cardnumber
            calStmt.registerOutParameter(5, Types.VARCHAR); //status
            calStmt.execute();
            String[] ArrayResult = new String[2];
            ArrayResult[0] = calStmt.getString(4);
            ArrayResult[1] = calStmt.getString(5);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pCardNumber
     * @return
     * @throws SQLException
     */
    public String[] getCardStatus(String pCardNumber) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getCardStatus);
            calStmt.setString(1, pCardNumber);
            calStmt.registerOutParameter(2, Types.VARCHAR); //Status
            calStmt.registerOutParameter(3, Types.VARCHAR); //Name
            calStmt.registerOutParameter(4, Types.VARCHAR); //Account
            calStmt.execute();
            String[] ArrayResult = new String[3];
            ArrayResult[0] = calStmt.getString(2);
            ArrayResult[1] = calStmt.getString(3);
            ArrayResult[2] = calStmt.getString(4);
            return ArrayResult;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public String getAuditNumber() throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getAuditNumber);
            calStmt.registerOutParameter(1, Types.VARCHAR);
            calStmt.execute();
            String result = calStmt.getString(1);
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    /**
     *
     * @return @throws SQLException
     */
    public String getAuthorizationCode() throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getAuthorizationCode);
            calStmt.registerOutParameter(1, Types.VARCHAR);
            calStmt.execute();
            String result = calStmt.getString(1);
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pTypeTransfer
     * @return
     * @throws SQLException
     */
    public String getSMLAccount(String pTypeTransfer) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getSMLAccount);
            calStmt.setString(1, pTypeTransfer);
            calStmt.registerOutParameter(2, Types.VARCHAR); //account of SML
            calStmt.execute();
            return calStmt.getString(2);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pAuditNumber
     * @return
     * @throws SQLException
     */
    public boolean checkAuditNumberOfSML(String pAuditNumber) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(checkAuditNumberOfSML);
            calStmt.setString(1, pAuditNumber);
            calStmt.registerOutParameter(2, Types.INTEGER); //account of SML
            calStmt.execute();
            int rs = calStmt.getInt(2);
            return rs == 0;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pCustNo
     * @param pAmount
     * @return
     * @throws SQLException
     */
    public String checkLimitAmount(String pCustNo, BigDecimal pAmount) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(checkLimitAmount);
            calStmt.setString(1, pCustNo);
            calStmt.setBigDecimal(2, pAmount);
            calStmt.registerOutParameter(3, Types.VARCHAR); //account of SML
            calStmt.execute();
            String rs = calStmt.getString(3);
            return rs; // 01: Nho hon muc toi thieu; 02: Vuot han muc 1 lan; 03: Vuot han muc 1 ngay
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param sml
     * @throws SQLException
     */
    public void InsertSMLCollated(SMLCollate sml) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertSMLCollate);
            calStmt.setString(1, sml.getACCOUNTNO());
            calStmt.setString(2, sml.getPROCESSINGCODDE());
            calStmt.setString(3, sml.getAMOUNT());
            calStmt.setString(4, sml.getAUDITNUMBER());
            calStmt.setString(5, sml.getTRANSTIME());
            calStmt.setString(6, sml.getTRANSDATE());
            calStmt.setString(7, sml.getPAYDATE());
            calStmt.setString(8, sml.getMERCHANTTYPE());
            calStmt.setString(9, sml.getACQUIRINGCODE());
            calStmt.setString(10, sml.getAUTHORIZATIONNUMBER());
            calStmt.setString(11, sml.getTERMID());
            calStmt.setString(12, sml.getCCY());
            calStmt.setString(13, sml.getSOURCEACCOUNT());
            calStmt.setString(14, sml.getDESTACCOUNT());
            calStmt.setString(15, sml.getBENID());
            calStmt.setString(16, sml.getRC());
            calStmt.setString(17, sml.getCHECKSUM());
            calStmt.setString(18, sml.getTYPE());
            calStmt.setString(19, sml.getFILETYPE());
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
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
     */
    public void insertCollateDate(Date pcollatedate, String pfilename, String pTypefile, int pSumrecord) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertCollateDate);
            java.sql.Date sqlDate = new java.sql.Date(pcollatedate.getTime());
            calStmt.setDate(1, sqlDate);
            calStmt.setString(2, pfilename);
            calStmt.setString(3, pTypefile);
            calStmt.setInt(4, pSumrecord);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pTypefile
     * @return
     * @throws SQLException
     */
    public int checkFile(String pTypefile) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(checkFile);
            calStmt.setString(1, pTypefile);
            calStmt.registerOutParameter(2, Types.INTEGER);
            calStmt.execute();
            return calStmt.getInt(2);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pTypefile
     * @return
     * @throws SQLException
     */
    public List<SMLCollate> getOutCollate(String pTypefile) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getOutCollate);
            calStmt.setString(1, pTypefile);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.executeQuery();
            List<SMLCollate> resultList = new ArrayList<SMLCollate>();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(2);
            if (rs == null) {
                LOGGER.warn("Not found");
                throw new Exception("Not found");
            }
            while (rs.next()) {
                SMLCollate collate = new SMLCollate();
                collate.setACCOUNTNO(rs.getString("ACCOUNTNUMBER"));
                collate.setPROCESSINGCODDE(rs.getString("PROCESSINGCODE"));
                collate.setAMOUNT(rs.getString("AMOUNT"));
                collate.setAUDITNUMBER(rs.getString("AUDITNUMBER"));
                collate.setTRANSTIME(rs.getString("TRANSTIME"));
                collate.setTRANSDATE(rs.getString("TRANSDATE"));
                collate.setPAYDATE(rs.getString("PAYDATE"));
                collate.setMERCHANTTYPE(rs.getString("MERCHANTTYPE"));
                collate.setACQUIRINGCODE(rs.getString("ACQUIRINGCODE"));
                collate.setAUTHORIZATIONNUMBER(rs.getString("AUTHORIZATIONNUMBER"));
                collate.setTERMID(rs.getString("TERMID"));
                collate.setCCY(rs.getString("CCY"));
                collate.setSOURCEACCOUNT(rs.getString("SOURCEACCOUNT"));
                collate.setDESTACCOUNT(rs.getString("DESTACCOUNT"));
                collate.setBENID(rs.getString("BENID"));
                collate.setRC(rs.getString("RC"));
                collate.setCHECKSUM(rs.getString("CHECKSUM"));
                collate.setTYPE(rs.getString("TYPE"));
                resultList.add(collate);
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    private static String SQL_GETLISTCOUNTER = "select IBT_CHECKCOUNTER(?) as returnval from dual";

    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    public int checkCounter(String ID) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(ID);
        ArrayList List = JDBCEngine.executeQuery(SQL_GETLISTCOUNTER, p_args, connection);
        HashMap<?, ?> HMDate = (HashMap<?, ?>) List.get(0);
        int returnVal = Integer.parseInt(HMDate.get("returnval").toString());
        return returnVal;
    }

    public void updateIBT(String pTrace, String pStatus,
            String pRefcore, String pRefcoreRefund, String pREF_NO_F37,
            String pSETT_DATE_F15,
            String pTypeTransfer) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(updateIBT);
            calStmt.setString(1, pTrace);
            calStmt.setString(2, pStatus);
            calStmt.setString(3, pRefcore);
            calStmt.setString(4, pRefcoreRefund);
            calStmt.setString(5, pREF_NO_F37);
            calStmt.setString(6, pSETT_DATE_F15);
            calStmt.setString(7, pTypeTransfer);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    final String updateIBTResponse = "BEGIN PROC_IBT_INSERT_RESPONSECODE(?,?,?,?,?); END;";

    public void updateIBTResponse(String pTrace, String pStatus,
            String pResponseCode, String pRefcoreRefund, String pSett_date_F15) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(updateIBTResponse);
            calStmt.setString(1, pTrace);
            calStmt.setString(2, pStatus);
            calStmt.setString(3, pResponseCode);
            calStmt.setString(4, pRefcoreRefund);
            calStmt.setString(5, pSett_date_F15);
            calStmt.execute();
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    final String InsertIBTLOG = "BEGIN SML_IBT.PROC_INSERT_SML_FT_MASTER(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    public int InsertIBTLOG(String pTYPETRANSFER, String pFROMNUMBER, String pPROCESSINGCODE,
            BigDecimal pTRANSAMOUNT, String pTRANSDATE,
            String pAUDITNUMBER, String pMERCHANTTYPE,
            String pACQUIRINGCODE, String pAUTHORIZATIONCODE, String pRESPONSECODE,
            String pTERMID, String pCARDACCEPTTOR, String pDESTNUMBER,
            String pNARRATION, String pBENID, String pTYPEFUNCTION, String pStatus,
            String pRefCore, String pCUSTNO, String RefCORE_REFUND, String pREF_NO_F37, String pSETT_DATE_F15) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(InsertIBTLOG);
            calStmt.setString(1, pTYPETRANSFER);
            calStmt.setString(2, pFROMNUMBER);
            calStmt.setString(3, pPROCESSINGCODE);
            calStmt.setBigDecimal(4, pTRANSAMOUNT);
            calStmt.setString(5, pTRANSDATE);
            calStmt.setString(6, pAUDITNUMBER);
            calStmt.setString(7, pMERCHANTTYPE);
            calStmt.setString(8, pACQUIRINGCODE);
            calStmt.setString(9, pAUTHORIZATIONCODE);
            calStmt.setString(10, pRESPONSECODE);
            calStmt.setString(11, pTERMID);
            calStmt.setString(12, pCARDACCEPTTOR);
            calStmt.setString(13, pDESTNUMBER);
            calStmt.setString(14, pNARRATION);
            calStmt.setString(15, pBENID);
            calStmt.setString(16, pTYPEFUNCTION);
            calStmt.setString(17, pStatus);
            calStmt.setString(18, pRefCore);
            calStmt.setString(19, pCUSTNO);
            calStmt.setString(20, RefCORE_REFUND);
            calStmt.setString(21, pREF_NO_F37);
            calStmt.setString(22, pSETT_DATE_F15);
            calStmt.registerOutParameter(23, OracleTypes.INTEGER);
            calStmt.execute();
            return calStmt.getInt(23);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public String getSeqRefNo(String type) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        String seqRefNo = "select seq_refno_" + type + ".Nextval seqrefno from dual";
        ArrayList List = JDBCEngine.executeQuery(seqRefNo, p_args, connection);
        HashMap<?, ?> HM = (HashMap<?, ?>) List.get(0);
        return HM.get("seqrefno").toString();
    }
    final String InsertIBTLOGXref = "BEGIN SML_IBT.INSERT_SML_FT_MASTER_XREF(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    public int InsertIBTLOGXref(String pTYPETRANSFER, String pFROMNUMBER, String pPROCESSINGCODE,
            BigDecimal pTRANSAMOUNT, String pTRANSDATE,
            String pAUDITNUMBER, String pMERCHANTTYPE,
            String pACQUIRINGCODE, String pAUTHORIZATIONCODE, String pRESPONSECODE,
            String pTERMID, String pCARDACCEPTTOR, String pDESTNUMBER,
            String pNARRATION, String pBENID, String pTYPEFUNCTION, String pStatus,
            String pRefCore, String pCUSTNO, String RefCORE_REFUND, String pREF_NO_F37, String pSETT_DATE_F15, String Xref) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(InsertIBTLOGXref);
            calStmt.setString(1, pTYPETRANSFER);
            calStmt.setString(2, pFROMNUMBER);
            calStmt.setString(3, pPROCESSINGCODE);
            calStmt.setBigDecimal(4, pTRANSAMOUNT);
            calStmt.setString(5, pTRANSDATE);
            calStmt.setString(6, pAUDITNUMBER);
            calStmt.setString(7, pMERCHANTTYPE);
            calStmt.setString(8, pACQUIRINGCODE);
            calStmt.setString(9, pAUTHORIZATIONCODE);
            calStmt.setString(10, pRESPONSECODE);
            calStmt.setString(11, pTERMID);
            calStmt.setString(12, pCARDACCEPTTOR);
            calStmt.setString(13, pDESTNUMBER);
            calStmt.setString(14, pNARRATION);
            calStmt.setString(15, pBENID);
            calStmt.setString(16, pTYPEFUNCTION);
            calStmt.setString(17, pStatus);
            calStmt.setString(18, pRefCore);
            calStmt.setString(19, pCUSTNO);
            calStmt.setString(20, RefCORE_REFUND);
            calStmt.setString(21, pREF_NO_F37);
            calStmt.setString(22, pSETT_DATE_F15);
            calStmt.registerOutParameter(23, OracleTypes.INTEGER);
            calStmt.setString(24, Xref);
            calStmt.execute();
            return calStmt.getInt(23);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    final String updateIBTResponse2 = "update SML_FT_MASTER set status=?, responsecode=?, Refcore_refund=?, Sett_date_F15=? where Typetransfer='FROM_SCB' and auditnumber=? and trunc(Createdate) >= sysdate-1 and typefunction='TRN' and ID=?";

    public void updateIBTResponse(String pTrace, String pStatus,
            String pResponseCode, String pRefcoreRefund, String pSett_date_F15, String ID) throws SQLException, Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(pStatus);
        p_args.add(pResponseCode);
        p_args.add(pRefcoreRefund);
        p_args.add(pSett_date_F15);
        p_args.add(pTrace);
        p_args.add(ID);
        JDBCEngine.executeUpdate(updateIBTResponse2, p_args, connection);
        connection.commit();
    }
    String updateRefCore = "update SML_FT_MASTER set status=?, Refcore=?, xref=? where Typetransfer=? and auditnumber=? and trunc(Createdate) >= sysdate-1 and typefunction='TRN' and ID=?";

    public int updateIBT(String AuditNumber, String Status, String RefCORE, String typeTransfer, String ID, String xref) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(Status);
        p_args.add(RefCORE);
        p_args.add(xref);
        p_args.add(typeTransfer);
        p_args.add(AuditNumber);
        p_args.add(ID);
        int result = JDBCEngine.executeUpdate(updateRefCore, p_args, connection);
        connection.commit();
        return result;
    }
    String updateRefCoreToSCB = "update SML_FT_MASTER set status=?, Refcore=?, RESPONSECODE=?  where Typetransfer='TO_SCB' and auditnumber=? and trunc(Createdate) >= sysdate-1 and typefunction='TRN' and ID=?";

    public int updateResultToSCB(String AuditNumber, String Status, String RefCORE, String ID, String responsecode) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(Status);
        p_args.add(RefCORE);
        p_args.add(responsecode);
        p_args.add(AuditNumber);
        p_args.add(ID);
        int result = JDBCEngine.executeUpdate(updateRefCoreToSCB, p_args, connection);
        connection.commit();
        return result;
    }

    public ArrayList<?> searchBlackList(String destnumber, String bankcode, int isapprove) throws Exception {
        ArrayList p_args = new ArrayList();
        if (destnumber != null) 
        {
            searchBlackList = searchBlackList + " and destnumber=?";
            p_args.add(destnumber);
            if (bankcode != null) 
            {
                searchBlackList = searchBlackList + " and typedestnumber='ACCOUNT' and bankcode=?";
                p_args.add(bankcode);

            }
            else
            {
                searchBlackList = searchBlackList + " and typedestnumber='CARD'";
            }
            

        }
        if (isapprove >= 0) {
            searchBlackList = searchBlackList + " and isapprove=?";
            p_args.add(isapprove);
        }
        return JDBCEngine.executeQuery(searchBlackList, p_args, connection);
    }

    public int updateBlackList(String id, String userapprove, int isapprove) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(userapprove);
        p_args.add(isapprove);
       
        p_args.add(id);
        int result = JDBCEngine.executeUpdate(updateBlackList, p_args, connection);
        connection.commit();
        return result;
    }

    String insertBlackList = "insert into SML_FT_BLACKLIST (DESTNUMBER, TYPEDESTNUMBER, BANKCODE, USERIDCREATE, BRANCHCODE, BENENAME) values (?,?,?,?,?,?) ";

    public int insertBlackList(String destnumber, String type, String bankcode, String userid, String branchcode, String benename) throws Exception {
        ArrayList<Comparable> params = new ArrayList<Comparable>();
        params.add(destnumber);
        params.add(type);
        params.add(bankcode);
        params.add(userid);
        params.add(branchcode);
        params.add(benename);
        int result = JDBCEngine.executeUpdate(insertBlackList, params, connection);
        connection.commit();
        return result;

    }

    public ArrayList<?> getInforBlackList(String id, int isapprove) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(id);
        p_args.add(isapprove);
        return JDBCEngine.executeQuery(getInforBlackList, p_args, connection);
    }
}
