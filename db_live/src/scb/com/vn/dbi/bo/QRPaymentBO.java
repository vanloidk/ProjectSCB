/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.sql.Connection;
import scb.com.vn.dbi.dao.QRPaymentDAO;
import scb.com.vn.dbi.dto.QRPAYMENT;
import scb.com.vn.dbi.utility.HibernateUtil;

/**
 *
 * @author lydty
 */
public class QRPaymentBO extends BaseSMSSCBBO{
    private Connection conn;
    private QRPaymentDAO dao = null;
     public QRPaymentBO() throws Exception {
        dao = new QRPaymentDAO();
        super.setSessionfactory(HibernateUtil.getSessionFactorySmsScb());
    }
      public boolean insertQRPayment(QRPAYMENT qrPayment) throws Exception {
          
          try
          {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.INSERT_QRPAYMENT(qrPayment);
          }
          catch(Exception ex)
          {
              return false;
          }
    }
      public boolean updateQRPayment(QRPAYMENT qrPayment) throws Exception {
        conn = super.getConnection();
        dao.setConnection(conn);
        return dao.UPDATE_QRPAYMENT(qrPayment);
    }
      public Object[] QR_CHECK_PCODE(QRPAYMENT qrPayment) throws Exception 
      {
          conn = super.getConnection();
            dao.setConnection(conn);
            return dao.PROC_QR_CHECK_PCODE(qrPayment);
      }
      public Object[] DESTROY_QR_PAYMENT(String pTRANSID,String pOrderNo,String pPROVIDERID,String pDATETIME) throws Exception
      {
          conn = super.getConnection();
          dao.setConnection(conn);
          return dao.DESTROY_QR_PAYMENT(pTRANSID, pOrderNo, pPROVIDERID, pDATETIME);
      }
      public int UPDATE_DESTROY_QR_PAYMENT(String pRefcore,String pOrderNo,String pPROVIDERID) throws Exception 
      {
          conn = super.getConnection();
          dao.setConnection(conn);
          return dao.UPDATE_DESTROY_QR_PAYMENT(pRefcore, pOrderNo, pPROVIDERID);
      }
}
