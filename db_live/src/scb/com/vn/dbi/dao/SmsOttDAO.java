/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import scb.com.vn.dbi.dto.SmsOtt;
import scb.com.vn.common.model.info.SmsCustRegisInfo;

/**
 *
 * @author lydty
 */
public class SmsOttDAO extends BaseDAO {
    String getMessageOtt="BEGIN SMS_OTT.getMessageOtt(?,?); END;";
    String updateSendOtt="BEGIN SMS_OTT.updateSendOtt(?,?,?,?,?); END;";
    public List<SmsOtt> getMessageOtt(int isMulti) throws SQLException, Exception {
        CallableStatement calStmt = null;
        
        try {
            calStmt = connection.prepareCall(getMessageOtt);
            calStmt.setInt(1, isMulti);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            
            ResultSet rs = (ResultSet) calStmt.getObject(2);
            if (rs == null) {
                throw new Exception("Not found");
            }
            List<SmsOtt> resultList =new ArrayList<SmsOtt>();
            while (rs.next()) 
            {
                SmsOtt ott=new SmsOtt();
                ott.setMESSAGEID(rs.getString("MESSAGEID"));
                ott.setTYPE(rs.getInt("TYPE"));
                ott.setMOBILE(rs.getString("MOBILE"));
                ott.setCIF(rs.getString("CIF"));
                ott.setCONTENT(rs.getString("CONTENT"));
                ott.setPRIORITY(rs.getInt("PRIORITY"));
                ott.setMEDIAURL(rs.getString("MEDIAURL"));
                ott.setMEDIATYPE(rs.getString("MEDIATYPE"));
                ott.setEXPIRETIME(rs.getString("EXPIRETIME"));
                ott.setMESSAGETIME(rs.getString("MESSAGETIME"));
                ott.setISENCRYPT(rs.getInt("ISENCRYPT"));
                ott.setMULTIID(rs.getInt("MULTIID"));
                resultList.add(ott);                                                                                                                                                                     
            }
            return resultList;
            
        } catch (Exception ex) {
            throw new SQLException(ex);
        } finally {
            if (calStmt!= null) {calStmt.close();}                            
            if (connection != null) {connection.close();}
        }    
     }
    public int updateSendOtt(int id,int pstatus,String code,String desc,int nultiid) throws SQLException, Exception {
        CallableStatement calStmt = null;
        
        try {
            calStmt = connection.prepareCall(updateSendOtt);
            calStmt.setInt(1, id);
            calStmt.setInt(2, pstatus);
            calStmt.setString(3, code);
            calStmt.setString(4, desc);
            calStmt.setInt(5, nultiid);
            
            calStmt.execute();
            return 1;
            } catch (Exception ex) {
            throw new SQLException(ex);
        } finally {
            if (calStmt!= null) 
            {calStmt.close();}                            
            if (connection != null) {connection.close();}
        }   
    }
    String getListRegisterSMS="BEGIN PKG_SMS_CUST_ALERT.getListRegister(?,?); END;";
    public List<SmsCustRegisInfo> getListRegisterSMS(String CustNo) throws SQLException, Exception {
        CallableStatement calStmt = null;
        
        try {
            calStmt = connection.prepareCall(getListRegisterSMS);
            calStmt.setString(1, CustNo);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.execute();
            
            ResultSet rs = (ResultSet) calStmt.getObject(2);
            if (rs == null) {
                return null;
            }
            List<SmsCustRegisInfo> resultList =new ArrayList<SmsCustRegisInfo>();
            while (rs.next()) 
            {
                SmsCustRegisInfo sms=new SmsCustRegisInfo();
                sms.setCustno(CustNo);
                sms.setCustaccno(rs.getString("CUST_AC_NO")==null?"":rs.getString("CUST_AC_NO"));
                sms.setPhoneno(rs.getString("MOBILE"));
                sms.setTypeacc(rs.getString("TYPEACCOUNT"));
                resultList.add(sms);                                                                                                                                                                     
            }
            return resultList;
            
        } catch (Exception ex) {
            throw new SQLException(ex);
        } finally {
            if (calStmt!= null) {calStmt.close();}                            
            if (connection != null) {connection.close();}
        }    
     }
    /*
     procedure INSERT_SMS_ALERT
  (
         pUSER number,
         pBRANCH VARCHAR2,
         pCUST_AC_NO  VARCHAR2,
         pCUST_NO  VARCHAR2,
         pTYPEACCOUNT  VARCHAR2,
         pMOBILE  VARCHAR2,
         pCHANNEL VARCHAR2,
         pSTATUS OUT VARCHAR2
  )
    */
    final String INSERT_SMS_ALERT = "PKG_SMS_CUST_ALERT.INSERT_SMS_ALERT";
    public String PROCESS_SMS_ALERT(SmsCustRegisInfo obj) throws Exception {
        
        
        CallableStatement calStmt = null;
        try {
            calStmt = (CallableStatement) connection.prepareCall("BEGIN " + INSERT_SMS_ALERT
                    + " (?,?,?,?,?,?,?,?); END;");
            calStmt.setString(1, obj.getUserregister());
            calStmt.setString(2, obj.getCustaccno()==null?"":obj.getCustaccno());
            calStmt.setString(3, obj.getCustno());
            calStmt.setString(4, obj.getTypeacc());
            calStmt.setString(5, obj.getPhoneno()==null?"":obj.getPhoneno());
            calStmt.setString(6, obj.getChannel());
            calStmt.setString(7, obj.getTypefunction());
            calStmt.registerOutParameter(8,OracleTypes.VARCHAR);
            calStmt.execute(); 
            String status = calStmt.getString(8);  
            return status;
        }
        catch (Exception ex) {
            throw new SQLException(ex);
        } finally {
            if (calStmt!= null) {calStmt.close();}                            
            if (connection != null) {connection.close();}
        }   
    }
}
