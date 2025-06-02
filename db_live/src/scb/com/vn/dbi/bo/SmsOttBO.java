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
import scb.com.vn.dbi.dao.SmsOttDAO;
import scb.com.vn.dbi.dto.SmsOtt;
import scb.com.vn.common.model.info.SmsCustRegisInfo;

/**
 *
 * @author lydty
 */
public class SmsOttBO extends BaseSMSSCBBO{
      private Connection conn;
    private SmsOttDAO dao = null;
    public SmsOttBO() {
        dao = new SmsOttDAO();
    }
    public List<SmsOtt> getMessageOtt(int isMulti) throws  Exception
    {
          try
        {  
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getMessageOtt(isMulti);
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
    public int updateSendOtt(int id,int pstatus,String code,String desc,int nultiid) throws  Exception {
         try
        {  
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.updateSendOtt(id, pstatus,code, desc,nultiid);
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
    public String PROCESS_SMS_ALERT(SmsCustRegisInfo obj) throws Exception
    {
        try
        {  
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.PROCESS_SMS_ALERT(obj);
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
    public List<SmsCustRegisInfo> getListRegisterSMS(String CustNo) throws  Exception 
    {
         try
        {  
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getListRegisterSMS(CustNo);
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
