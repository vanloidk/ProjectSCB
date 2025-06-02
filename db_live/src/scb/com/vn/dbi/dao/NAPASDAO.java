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

import java.util.HashMap;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;

import scb.com.vn.dbi.dto.NAPAS_IBT;
import scb.com.vn.dbi.dto.NapasCollatedId;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author lydty
 */
public class NAPASDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(NAPASDAO.class);

    final String CHECK_LOG_DATE_COLLATED = "BEGIN NAPAS_COLLATE.CHECK_LOG_DATE_COLLATED(?,?,?,?,?,?,?); END;";
    final String getIBTCollateOut = "BEGIN NAPAS_COLLATE.getIBTCollateOut(?,?,?); END;";
    final String InsertDataCollated = "BEGIN NAPAS_COLLATE.InsertDataCollated(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    final String InsertIBTLog = "BEGIN PKG_NAPAS.InsertIBTLog(?,?); END;";
    final String insertResponseIBT = "BEGIN PKG_NAPAS.insertResponseIBT(?,?); END;";
    final String getResponseIBT = "BEGIN PKG_NAPAS.getResponseIBT(?,?); END;";
    final String getRequestIBT = "BEGIN PKG_NAPAS.getRequestIBT(?); END;";
    final String InsertLOG = "BEGIN PKG_NAPAS.PROC_INSERT_SML_FT_MASTERv2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String InsertTransfer = "BEGIN PKG_NAPAS.INSERT_SML_FT_MASTER(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String getRequestTranfer = "BEGIN PKG_NAPAS.getRequestTranfer(?,?,?); END;";
    final String UpdateTransfer = "BEGIN PKG_NAPAS.UPDATE_STATUS_TRANSFER(?,?,?,?,?,?,?,?,?,?,?); END;";
    final String checkCifNoCredit="select FN_CHECK_CIF_NO_CREDIT(?) check_result from dual";

    /**
     *
     * @param pCOLLATEDDATE
     * @param pFiletype
     * @param pTRANSTYPE
     * @param pSERVICE
     * @param pFileName
     * @param pPARTNERID
     * @return
     * @throws SQLException
     */
    public String CHECK_LOG_DATE_COLLATED(String pCOLLATEDDATE,
            String pFiletype,
            String pTRANSTYPE,
            String pSERVICE,
            String pFileName,
            String pPARTNERID
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(CHECK_LOG_DATE_COLLATED);
            calStmt.setString(1, pCOLLATEDDATE);
            calStmt.setString(2, pFiletype);
            calStmt.setString(3, pTRANSTYPE);
            calStmt.setString(4, pSERVICE);
            calStmt.setString(5, pFileName);
            calStmt.setString(6, pPARTNERID);
            calStmt.registerOutParameter(7, Types.VARCHAR);
            calStmt.execute();
            return calStmt.getString(7);
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
     * @param pTRANSTYPE
     * @param pCOLLATEDDATE
     * @return
     * @throws SQLException
     */
    public List<NapasCollatedId> getIBTCollateOut(
            String pTRANSTYPE,
            String pCOLLATEDDATE
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getIBTCollateOut);
            calStmt.setString(1, pTRANSTYPE);
            calStmt.setString(2, pCOLLATEDDATE);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(3);
            List<NapasCollatedId> resultList = new ArrayList<NapasCollatedId>();
            while (rs.next()) {
                NapasCollatedId obj = new NapasCollatedId();
                obj.setAcqIss(rs.getString("ACQ_ISS"));
                obj.setRcv1(rs.getString("RCV1"));
                obj.setRcv2(rs.getString("RCV2"));
                obj.setRcv3(rs.getString("RCV3"));
                obj.setAddinfo(rs.getString("ADDINFO"));
                obj.setAddinfoFee(rs.getString("ADDINFO_FEE"));
                obj.setBnb(rs.getString("BNB"));
                obj.setF102(rs.getString("F102"));
                obj.setF103Thedich(rs.getString("F103_THEDICH"));
                obj.setF10Tygia(rs.getString("F10_TYGIA"));
                obj.setF11Trace(rs.getString("F11_TRACE"));
                obj.setF12Giogd(rs.getString("F12_GIOGD"));
                obj.setF13Ngaygd(rs.getString("F13_NGAYGD"));
                obj.setF15Ngayqt(rs.getString("F15_NGAYQT"));
                obj.setF18Mct(rs.getString("F18_MCT"));
                obj.setF2Sothe(rs.getString("F2_SOTHE"));
                obj.setF37(rs.getString("F37"));
                obj.setF38(rs.getString("F38"));
                obj.setF3Maxuly(rs.getString("F3_MAXULY"));
                obj.setF41(rs.getString("F41"));
                obj.setF49Ccy(rs.getString("F49_CCY"));
                obj.setF4Rta(rs.getString("F4_RTA"));
                obj.setF4Sotien(rs.getString("F4_SOTIEN"));
                obj.setF50Ccy(rs.getString("F50_CCY"));
                obj.setF51Ccy(rs.getString("F51_CCY"));
                obj.setF5Stqt(rs.getString("F5_STQT"));
                obj.setF60Tcc(rs.getString("F60_TCC"));
                obj.setF62Svc(rs.getString("F62_SVC"));
                obj.setF6Stchuthe(rs.getString("F6_STCHUTHE"));
                obj.setF6Thucte(rs.getString("F6_THUCTE"));
                obj.setF9Tygia(rs.getString("F9_TYGIA"));
                obj.setMid(rs.getString("MID"));
                obj.setMti(rs.getString("MTI"));
                obj.setRrc(rs.getString("RRC"));
                obj.setService(rs.getString("SERVICE"));
                obj.setTranstype(rs.getString("TRANSTYPE"));
                obj.setTrn(rs.getString("TRN"));
                obj.setFiletype(rs.getString("FILETYPE"));
                obj.setHIDENCARD(rs.getString("HIDENCARD"));
                obj.setHIDENCARD_F(rs.getString("HIDENCARD_F"));
                resultList.add(obj);
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

    /**
     *
     * @param obj
     * @throws SQLException
     */
    public void InsertDataCollated(NapasCollatedId obj) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(InsertDataCollated);
            calStmt.setString(1, obj.getFiletype());
            calStmt.setString(2, obj.getTranstype());
            calStmt.setString(3, obj.getService());
            calStmt.setString(4, obj.getMti());
            calStmt.setString(5, obj.getF2Sothe());
            calStmt.setString(6, obj.getF3Maxuly());
            calStmt.setString(7, obj.getF62Svc());
            calStmt.setString(8, obj.getF60Tcc());
            calStmt.setString(9, obj.getF4Sotien());
            calStmt.setString(10, obj.getF4Rta());
            calStmt.setString(11, obj.getF49Ccy());
            calStmt.setString(12, obj.getF5Stqt());
            calStmt.setString(13, obj.getF50Ccy());
            calStmt.setString(14, obj.getF9Tygia());
            calStmt.setString(15, obj.getF6Stchuthe());
            calStmt.setString(16, obj.getF6Thucte());
            calStmt.setString(17, obj.getF51Ccy());
            calStmt.setString(18, obj.getF10Tygia());
            calStmt.setString(19, obj.getF11Trace());
            calStmt.setString(20, obj.getF12Giogd());
            calStmt.setString(21, obj.getF13Ngaygd());
            calStmt.setString(22, obj.getF15Ngayqt());
            calStmt.setString(23, obj.getF18Mct());
            calStmt.setString(24, obj.getAddinfo());
            calStmt.setString(25, obj.getF41());
            calStmt.setString(26, obj.getAcqIss());
            calStmt.setString(27, obj.getMid());
            calStmt.setString(28, obj.getBnb());
            calStmt.setString(29, obj.getF102());
            calStmt.setString(30, obj.getF103Thedich());
            calStmt.setString(31, obj.getAddinfoFee());
            calStmt.setString(32, obj.getF37());
            calStmt.setString(33, obj.getF38());
            calStmt.setString(34, obj.getTrn());
            calStmt.setString(35, obj.getRrc());
            calStmt.setString(36, obj.getRcv1());
            calStmt.setString(37, obj.getRcv2());
            calStmt.setString(38, obj.getRcv3());
            calStmt.setString(39, obj.getDateFile());
            calStmt.setString(40, obj.getHIDENCARD());
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
     * @param request
     * @return
     * @throws SQLException
     */
     public String InsertIBTLog(String request) throws SQLException
    {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(InsertIBTLog);
            calStmt.setString(1, request);
            calStmt.registerOutParameter(2, Types.VARCHAR);
            
            calStmt.execute();
            String result=calStmt.getString(2);
            
            return result;
        } catch (Exception ex) {
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
     * @param reponse
     * @param ID
     * @throws SQLException
     */
    public void insertResponseIBT(String reponse, String ID) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertResponseIBT);
            calStmt.setString(1, reponse);
            calStmt.setString(2, ID);
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
     * @param ID
     * @return
     * @throws SQLException
     */
    public String getResponseIBT(String ID) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getResponseIBT);
            calStmt.setString(1, ID);
            calStmt.registerOutParameter(2, Types.VARCHAR);
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
     * @return @throws SQLException
     */
    public String getRequestIBT() throws SQLException
    {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getRequestIBT);
            calStmt.registerOutParameter(1, OracleTypes.VARCHAR);
            calStmt.execute();
            String result = calStmt.getString(1);
            return result;
            
        } catch (Exception ex) {
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
     * @param obj
     * @throws SQLException
     */
    public void InsertLOGv2(NAPAS_IBT obj) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(InsertTransfer);
            calStmt.setString(1, obj.getTYPETRANSFER());
            calStmt.setString(2, obj.getFROMNUMBER());
            calStmt.setString(3, obj.getPROCESSINGCODE());
            calStmt.setBigDecimal(4, obj.getTRANSAMOUNT());
            calStmt.setString(5, obj.getTRANSDATE());
            calStmt.setString(6, obj.getAUDITNUMBER());
            calStmt.setString(7, obj.getMERCHANTTYPE());
            calStmt.setString(8, obj.getACQUIRINGCODE());
            calStmt.setString(9, obj.getAUTHORIZATIONCODE());
            calStmt.setString(10, obj.getRESPONSECODE());
            calStmt.setString(11, obj.getTERMID());
            calStmt.setString(12, obj.getCARDACCEPTTOR());
            calStmt.setString(13, obj.getDESTNUMBER());
            calStmt.setString(14, obj.getNARRATION());
            calStmt.setString(15, obj.getBENID());
            calStmt.setString(16, obj.getTYPEFUNCTION());
            calStmt.setString(17, obj.getStatus());
            calStmt.setString(18, obj.getRefCore());
            calStmt.setString(19, obj.getCUSTNO());
            calStmt.setString(20, obj.getRefCORE_REFUND());
            calStmt.setString(21, obj.getREF_NO_F37());
            calStmt.setString(22, obj.getSETT_DATE_F15());
            calStmt.setString(23, obj.getPANF2());
            calStmt.setString(24, obj.getAddDataF48());
            calStmt.setString(25, obj.getTransREFNOF63());
            calStmt.setString(26, obj.getCCYF49());
            calStmt.setString(27, obj.getF22());
            calStmt.setString(28, obj.getF25());
            calStmt.setString(29, obj.getF60());
            calStmt.setBigDecimal(30, obj.getF5());
            calStmt.setString(31, obj.getSCBTypeCard());
            calStmt.setString(32, obj.getSCBAccount());
            calStmt.setString(33, obj.getHIDENCARD());
            calStmt.setString(34, obj.getLOC());
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
   // final String updateSendIBT = "BEGIN PKG_NAPAS.updateSendIBT(?); END;";

    /**
     *
     * @param ID
     * @throws SQLException
     */
   /* public void updateSendIBT(String ID) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(updateSendIBT);
            calStmt.setString(1, ID);
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
    }*/
    // final String updateSendIBT ="update NAPAS_IBT_RESPONSE t set status = '1',updatedate= sysdate where TRACE = ? and t.createdate>= sysdate-1";
   final String updateSendIBT ="update SML_FT_MASTER t set status = '18' where typetransfer='FROM_SCB' and auditnumber = ? and t.createdate>= sysdate-10/1440 and status='16'";
     public int updateSendIBT(String ID) throws SQLException {
        CallableStatement calStmt = null;
        try {
            /*
            calStmt = connection.prepareCall(updateSendIBT);
            calStmt.setString(1,ID);
            calStmt.execute();*/
            ArrayList<String> p_args = new ArrayList<>();           
            p_args.add(ID);
            int result =  JDBCEngine.executeUpdate(updateSendIBT, p_args, connection);  
            connection.commit();
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return 0;
    }

    /**
     *
     * @param pTRANSTYPE
     * @param isCollated
     * @return
     * @throws SQLException
     */
    public List<NapasCollatedId> getIBTCollateOut(
            String pTRANSTYPE,
            int isCollated
    ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(getIBTCollateOut);
            calStmt.setString(1, pTRANSTYPE);
            calStmt.setInt(2, isCollated);
            calStmt.registerOutParameter(3, OracleTypes.CURSOR);
            calStmt.execute();
            ResultSet rs = null;
            rs = (ResultSet) calStmt.getObject(3);
            List<NapasCollatedId> resultList = new ArrayList<NapasCollatedId>();
            while (rs.next()) {
                NapasCollatedId obj = new NapasCollatedId();
                obj.setAcqIss(rs.getString("ACQ_ISS"));
                obj.setRcv1(rs.getString("RCV1"));
                obj.setRcv2(rs.getString("RCV2"));
                obj.setRcv3(rs.getString("RCV3"));
                obj.setAddinfo(rs.getString("ADDINFO"));
                obj.setAddinfoFee(rs.getString("ADDINFO_FEE"));
                obj.setBnb(rs.getString("BNB"));
                obj.setF102(rs.getString("F102"));
                obj.setF103Thedich(rs.getString("F103_THEDICH"));
                obj.setF10Tygia(rs.getString("F10_TYGIA"));
                obj.setF11Trace(rs.getString("F11_TRACE"));
                obj.setF12Giogd(rs.getString("F12_GIOGD"));
                obj.setF13Ngaygd(rs.getString("F13_NGAYGD"));
                obj.setF15Ngayqt(rs.getString("F15_NGAYQT"));
                obj.setF18Mct(rs.getString("F18_MCT"));
                obj.setF2Sothe(rs.getString("F2_SOTHE"));
                obj.setF37(rs.getString("F37"));
                obj.setF38(rs.getString("F38"));
                obj.setF3Maxuly(rs.getString("F3_MAXULY"));
                obj.setF41(rs.getString("F41"));
                obj.setF49Ccy(rs.getString("F49_CCY"));
                obj.setF4Rta(rs.getString("F4_RTA"));
                obj.setF4Sotien(rs.getString("F4_SOTIEN"));
                obj.setF50Ccy(rs.getString("F50_CCY"));
                obj.setF51Ccy(rs.getString("F51_CCY"));
                obj.setF5Stqt(rs.getString("F5_STQT"));
                obj.setF60Tcc(rs.getString("F60_TCC"));
                obj.setF62Svc(rs.getString("F62_SVC"));
                obj.setF6Stchuthe(rs.getString("F6_STCHUTHE"));
                obj.setF6Thucte(rs.getString("F6_THUCTE"));
                obj.setF9Tygia(rs.getString("F9_TYGIA"));
                obj.setMid(rs.getString("MID"));
                obj.setMti(rs.getString("MTI"));
                obj.setRrc(rs.getString("RRC"));
                obj.setService(rs.getString("SERVICE"));
                obj.setTranstype(rs.getString("TRANSTYPE"));
                obj.setTrn(rs.getString("TRN"));
                obj.setFiletype(rs.getString("FILETYPE"));
                resultList.add(obj);
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
    final String checkExistTransfer = "select * from\n"
            + "SML_FT_MASTER F\n"
            + "where \n"
            + "F.createdate >=sysdate-1\n"
            + "and F.TYPETRANSFER='TO_SCB'\n"
            + "and F.typefunction='TRN'\n"
            + "and F.Fromnumber=?\n"
            + "and F.Auditnumber=?\n"
            + "and F.merchanttype=?\n"
            + "and F.TRANSDATE=?";

    /**
     *
     * @param FromNumber
     * @param AuditNumber
     * @param MerchantType
     * @param Transdate
     * @return
     * @throws Exception
     */
    public ArrayList<?> isExistTransfer(String FromNumber, String AuditNumber,
            String MerchantType, String Transdate) throws Exception {
        ArrayList<String> p_args = new ArrayList<>();
        p_args.add(FromNumber);
        p_args.add(AuditNumber);
        p_args.add(MerchantType);
        p_args.add(Transdate);
        return JDBCEngine.executeQuery(checkExistTransfer, p_args, connection);
    }
    
    
   public Object[] getRequestTranfer(String trace,String status) throws SQLException
    {
        CallableStatement calStmt = null;
        try {
            /*
            calStmt = connection.prepareCall(getRequestTranfer);
            calStmt.setString(1, refno);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.registerOutParameter(3, OracleTypes.INTEGER);
            calStmt.execute();
            ResultSet         rs = null;
            rs = (ResultSet) calStmt.getObject(2);
            NAPAS_IBT obj=new NAPAS_IBT();
            while (rs.next()) 
            {
                obj.setPROCESSINGCODE(rs.getString("PROCESSINGCODE"));
                obj.setTRANSAMOUNT(BigDecimal.valueOf(Double.valueOf(rs.getString("TRANSAMOUNT"))));
                obj.setTRANSDATE(rs.getString("TRANSDATE"));
                obj.setAUDITNUMBER(rs.getString("AUDITNUMBER"));
                obj.setMERCHANTTYPE(rs.getString("MERCHANTTYPE"));
                obj.setACQUIRINGCODE(rs.getString("ACQUIRINGCODE"));
                obj.setAUTHORIZATIONCODE(rs.getString("AUTHORIZATIONCODE"));
                obj.setTERMID(rs.getString("TERMID"));
                obj.setCARDACCEPTTOR(rs.getString("CARDACCEPTTOR"));
                obj.setDESTNUMBER(rs.getString("DESTNUMBER"));
                obj.setNARRATION(rs.getString("NARRATION"));
                obj.setBENID(rs.getString("BENID"));
                obj.setREF_NO_F37(rs.getString("REF_NO_F37")); 
                obj.setFROMNUMBER(rs.getString("FROMNUMBER"));
                obj.setTYPEFUNCTION(rs.getString("TYPEFUNCTION"));
                obj.setF60(rs.getString("F60"));
                obj.setHIDENCARD(rs.getString("HIDENCARD"));
                obj.setRefCore(rs.getString("REFCORE"));
                obj.setAddDataF48(rs.getString("ADDDATAF48"));
            }
            Object[] listObj=new Object[2];
            listObj[0]=obj;
            listObj[1]=calStmt.getInt(3);
            return listObj;*/
             NAPAS_IBT obj = getDetailTransfer(trace,status);
            Object[] listObj=new Object[2];
            listObj[0]=obj;
            listObj[1]=obj==null?0:1;
            return listObj;
        } catch (Exception ex) {
            throw new SQLException(ex);
           
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }/*
    procedure UPDATE_STATUS_TRANSFER(pTrace varchar2, 
          pStatus varchar2,
          pRefcore varchar2,
          pRefcoreRefund varchar2,
          pREF_NO_F37 varchar2,
          pSETT_DATE_F15 varchar2,
          pTypeTransfer varchar2,
          pResponseCode varchar2,
          pAuthorizationcode varchar2,
          pTransREFNOF63 varchar2,
          pF5 varchar2)
    */
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
         String pF5 ) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(UpdateTransfer);
            calStmt.setString(1, pTrace);
            calStmt.setString(2, pStatus);
            calStmt.setString(3, pRefcore);
            calStmt.setString(4, pRefcoreRefund);
            calStmt.setString(5, pREF_NO_F37);
            calStmt.setString(6, pSETT_DATE_F15);
            calStmt.setString(7, pTypeTransfer);
            calStmt.setString(8, pResponseCode);
            calStmt.setString(9, pAuthorizationcode);
            calStmt.setString(10, pTransREFNOF63);
            calStmt.setString(11, pF5);
            calStmt.execute();
        } catch (Exception ex) {
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
    final String InsertTransferXref = "BEGIN PKG_NAPAS.INSERT_SML_FT_MASTER_2020(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    public int InsertIBTTranfer(NAPAS_IBT obj) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(InsertTransferXref);
            calStmt.setString(1, obj.getTYPETRANSFER());
            calStmt.setString(2, obj.getFROMNUMBER());
            calStmt.setString(3, obj.getPROCESSINGCODE());
            calStmt.setBigDecimal(4, obj.getTRANSAMOUNT());
            calStmt.setString(5, obj.getTRANSDATE());
            calStmt.setString(6, obj.getAUDITNUMBER());
            calStmt.setString(7, obj.getMERCHANTTYPE());
            calStmt.setString(8, obj.getACQUIRINGCODE());
            calStmt.setString(9, obj.getAUTHORIZATIONCODE());
            calStmt.setString(10, obj.getRESPONSECODE());
            calStmt.setString(11, obj.getTERMID());
            calStmt.setString(12, obj.getCARDACCEPTTOR());
            calStmt.setString(13, obj.getDESTNUMBER());
            calStmt.setString(14, obj.getNARRATION());
            calStmt.setString(15, obj.getBENID());
            calStmt.setString(16, obj.getTYPEFUNCTION());
            calStmt.setString(17, obj.getStatus());
            
            calStmt.setString(18, obj.getCUSTNO());
            
            calStmt.setString(19, obj.getREF_NO_F37());
            calStmt.setString(20, obj.getSETT_DATE_F15());
            calStmt.setString(21, obj.getPANF2());
            calStmt.setString(22, obj.getAddDataF48());
            calStmt.setString(23, obj.getTransREFNOF63());
            calStmt.setString(24, obj.getCCYF49());
            calStmt.setString(25, obj.getF22());
            calStmt.setString(26, obj.getF25());
            calStmt.setString(27, obj.getF60());
            calStmt.setBigDecimal(28, obj.getF5());
            calStmt.setString(29, obj.getSCBTypeCard());
            calStmt.setString(30, obj.getSCBAccount());
            calStmt.setString(31, obj.getHIDENCARD());
            calStmt.setString(32, obj.getLOC());
            calStmt.setString(33, obj.getREFNO());
            calStmt.registerOutParameter(34, OracleTypes.INTEGER);
            calStmt.execute();
            int id= calStmt.getInt(34);
            return id;
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return 0;
    }
    final String UpdateTransferXref = "BEGIN PKG_NAPAS.UPDATE_STATUS_TRANSFER_2020(?,?,?,?,?,?,?,?,?,?,?,?); END;";
    public void UPDATEIBTRANSFER(String pTrace ,
         String pStatus ,
         String pRefcore ,
         String pRefcoreRefund ,
         String pREF_NO_F37 ,
         String pSETT_DATE_F15 ,
         String pTypeTransfer ,
         String pResponseCode ,
         String pAuthorizationcode ,
         String pTransREFNOF63 ,
         String pF5,
         String XREF) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(UpdateTransferXref);
            calStmt.setString(1, pTrace);
            calStmt.setString(2, pStatus);
            calStmt.setString(3, pRefcore);
            calStmt.setString(4, pRefcoreRefund);
            calStmt.setString(5, pREF_NO_F37);
            calStmt.setString(6, pSETT_DATE_F15);
            calStmt.setString(7, pTypeTransfer);
            calStmt.setString(8, pResponseCode);
            calStmt.setString(9, pAuthorizationcode);
            calStmt.setString(10, pTransREFNOF63);
            calStmt.setString(11, pF5);
            calStmt.setString(12, XREF);
            calStmt.execute();
        } catch (Exception ex) {
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
     final String UpdateRevertTransfer = "Update SML_FT_MASTER F set F.Refcore_Refund= ? where F.Auditnumber = ? and F.TYPETRANSFER = 'FROM_SCB' and F.Typefunction='TRN' and F.Refcore=? and F.Createdate > = sysdate -1 and F.ID=?";
    public void UpdateRevertTransfer(String pTrace ,
         String pRefcore ,
         String pRefcoreRefund ,String ID) throws SQLException {
        try {
             ArrayList<String> p_args = new ArrayList<>();           
            p_args.add(pRefcoreRefund);
            p_args.add(pTrace);
            p_args.add(pRefcore);
            p_args.add(ID);
            JDBCEngine.executeUpdate(UpdateRevertTransfer, p_args, connection);  
        } catch (Exception ex) {
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    final String insertIBTRequestLog = "Insert into NAPAS_IBT_RESPONSE (TRACE,STATUS,ID) values (?, '0',SEQ_NAPAS_IBT_RESPONSE.Nextval)";
    public int insertIBTRequestLog(String trace) throws Exception {
        try 
        {
            ArrayList<String> params = new ArrayList<String>();
            params.add(trace);
            return JDBCEngine.executeUpdate(insertIBTRequestLog, params, connection);
        } catch (Exception ex) {
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        
    }
    
    /*procedure UPDATE_REFID(pID varchar2,pChannel varchar2, pRefcore varchar2,pRefNo varchar2,pStatus varchar2)*/
     final String updateResponseFromSCB ="Update SML_FT_MASTER F\n" +
"      set F.Status = ?,\n" +
"          F.Sett_Date_F15= ?,\n" +
"          F.Responsecode=?,\n" +
"          F.Authorizationcode=?,\n" +
"          F.Transrefnof63=?,\n" +
"          F.F5=?\n" +
"      where F.Auditnumber = ? and F.ref_no_f37=?" +
"      and F.TYPETRANSFER = 'FROM_SCB'\n" +
"      and F.Createdate > = sysdate -10/1440";
    public void UPDATEIBTRANSFER(String pTrace ,
         String pStatus ,
         String pREF_NO_F37 ,
         String pSETT_DATE_F15 ,
         String pResponseCode ,
         String pAuthorizationcode ,
         String pTransREFNOF63 ,
         String pF5) throws SQLException {
        try {
             ArrayList<String> p_args = new ArrayList<>();           
            p_args.add(pStatus);
            p_args.add(pSETT_DATE_F15);
            p_args.add(pResponseCode);
            p_args.add(pAuthorizationcode);
            p_args.add(pTransREFNOF63);
            p_args.add(pF5);
            p_args.add(pTrace);
            p_args.add(pREF_NO_F37);
            JDBCEngine.executeUpdate(updateResponseFromSCB, p_args, connection);
        } catch (Exception ex) {
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    final String updateSendIBT_response ="update NAPAS_IBT_RESPONSE t set RESPONSE = ?,updatedate= sysdate where TRACE = ? and t.createdate>= sysdate-10/1440";
    public void updateSendIBT_response(String ID,String response) throws SQLException 
    {
        CallableStatement calStmt = null;
        try {
            /*
            calStmt = connection.prepareCall(updateSendIBT);
            calStmt.setString(1,ID);
            calStmt.execute();*/
            ArrayList<String> p_args = new ArrayList<>();           
            p_args.add(response);
            p_args.add(ID);
            JDBCEngine.executeUpdate(updateSendIBT_response, p_args, connection);  
        } catch (Exception ex) {
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
    final String getDetailTransfer = "select * from sml_ft_master where typetransfer='FROM_SCB' and auditnumber=? and status=? and createdate>= sysdate -10/1440";
    public  NAPAS_IBT getDetailTransfer(String trace,String status) throws SQLException {
        try
        {
            ArrayList<String> p_args = new ArrayList<String>();
            p_args.add(trace);
            p_args.add(status);
            ArrayList List = JDBCEngine.executeQuery(getDetailTransfer, p_args, connection);
            if(List.size()>0)
            {
            HashMap<?, ?> HM = (HashMap<?, ?>) List.get(0);
            NAPAS_IBT obj=new NAPAS_IBT();
            obj.setAUDITNUMBER(HM.get("auditnumber").toString());
            obj.setPROCESSINGCODE(HM.get("processingcode").toString());
            obj.setTRANSAMOUNT(BigDecimal.valueOf(Double.valueOf(HM.get("transamount").toString())));
            obj.setTRANSDATE(HM.get("transdate").toString());
            
            obj.setMERCHANTTYPE(HM.get("merchanttype").toString());
            obj.setACQUIRINGCODE(HM.get("acquiringcode").toString());
            obj.setAUTHORIZATIONCODE(HM.get("authorizationcode")==null?"":HM.get("authorizationcode").toString());
            obj.setTERMID(HM.get("termid").toString());
            obj.setCARDACCEPTTOR(HM.get("cardaccepttor").toString());
            obj.setDESTNUMBER(HM.get("destnumber").toString());
            obj.setNARRATION(HM.get("narration")==null?"":HM.get("narration").toString());
            obj.setBENID(HM.get("benid")==null?"":HM.get("benid").toString());
            obj.setREF_NO_F37(HM.get("ref_no_f37").toString()); 
            obj.setFROMNUMBER(HM.get("fromnumber").toString());
            obj.setTYPEFUNCTION(HM.get("typefunction").toString());
            obj.setF60(HM.get("f60")==null?"":HM.get("f60").toString());
            obj.setHIDENCARD(HM.get("hidencard")==null?"":HM.get("hidencard").toString());
            obj.setRefCore(HM.get("refcore")==null?"":HM.get("refcore").toString());
            obj.setAddDataF48(HM.get("adddataf48")==null?"":HM.get("adddataf48").toString());
            return obj;
            }
            return null;
        } catch (Exception ex) {
            throw new SQLException(ex);
        }
        finally {                         
            if (connection != null) {connection.close();}
        }         
    }
     final String updateCannotSend = "update sml_ft_master set status='19' where trace=? and typetransfer='FROM_SCB' and createdate>=sysdate-10/1440 and status='16'";
    public int updateCannotSend(String trace) throws SQLException
    {
        try {
            ArrayList<String> p_args = new ArrayList<>();           
            p_args.add(trace);
            return JDBCEngine.executeUpdate(updateCannotSend, p_args, connection);  
        } catch (Exception ex) {
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    public String checkCifNoCredit(String cif) throws SQLException
    {
        try
        {
            ArrayList<String> p = new ArrayList<String>();
            p.add(cif);
            ArrayList<?> a = JDBCEngine.executeQuery(checkCifNoCredit, p, connection);
            if (a != null && a.size() > 0) {
                HashMap<?, ?> hm = (HashMap<?, ?>) a.get(0);
                String check = hm.get("check_result").toString();
                return check;
            }
        }
        catch (Exception ex) {
            LOGGER.error(ex);
          //  throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return "0";
    }   
}
