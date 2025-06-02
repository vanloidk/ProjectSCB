/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dto.QRPAYMENT;

/**
 *
 * @author lydty
 */
public class QRPaymentDAO  extends BaseDAO{
    private static final Logger LOGGER = Logger.getLogger(QRPaymentDAO.class);
    
    final String INSERT_QRPAYMENT = "insert into QR_PAYMENT (REQUESTID, QRSTRING, QRPARTNER, ACCTCUSTOMER, QRTYPE, CHANNELID, AMOUNTORDER, AMOUNTPAYMENT, PROMOTIONCODE,ACCTTYPE,REQUESTDATE,REFCORE,STATUS,CUSTNO,MOBILENO)\n" +
    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
    
    public boolean INSERT_QRPAYMENT(QRPAYMENT qrPayment) throws Exception {
        try {
            CallableStatement calStmt = connection.prepareCall(INSERT_QRPAYMENT);
            calStmt.setString(1, qrPayment.getRequest().getRequestId());
            calStmt.setString(2, qrPayment.getRequest().getQrCodeValue());
            calStmt.setString(3, qrPayment.getRequest().getPartnerid());
            calStmt.setString(4, qrPayment.getRequest().getAccountno());
            calStmt.setInt(5, qrPayment.getRequest().getQRType());
            calStmt.setString(6, qrPayment.getRequest().getChanelid());
            calStmt.setLong(7, qrPayment.getRequest().getAmountOrder());
            calStmt.setLong(8, qrPayment.getRequest().getAmountPayment());
            calStmt.setString(9, qrPayment.getRequest().getPromotionCode());
            calStmt.setString(10, qrPayment.getRequest().getAccounttype());
            calStmt.setString(11, qrPayment.getResponse().getRequestDate());
            calStmt.setString(12, qrPayment.getResponse().getRefcore());
            calStmt.setString(13, qrPayment.getResponse().getStatus());
            calStmt.setString(14, qrPayment.getCustno());
            calStmt.setString(15, qrPayment.getMobileno());
            calStmt.execute();
            connection.commit();
            return true;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return false;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    final String UPDATE_QRPAYMENT = "Update QR_PAYMENT Q\n" +
    "set Q.Status=?,\n" +
    "    Q.ISREVERT=?,\n" +
    "    Q.REPONSEDATE=?,\n" +
    "    Q.orderNo=?\n" +
    " where Q.REQUESTID= ?\n" +
    " and Q.Channelid=?";
    
    public boolean UPDATE_QRPAYMENT(QRPAYMENT qrPayment) throws Exception {
        try {
            CallableStatement calStmt = connection.prepareCall(UPDATE_QRPAYMENT);
            calStmt.setString(1, qrPayment.getResponse().getStatus());
            calStmt.setString(2, qrPayment.getResponse().getIsRevert());
            calStmt.setString(3, qrPayment.getResponse().getResponseDate());
            calStmt.setString(4, qrPayment.getResponse().getOrderNo());
            calStmt.setString(5, qrPayment.getResponse().getRequestId());
            calStmt.setString(6, qrPayment.getRequest().getChanelid());
            calStmt.execute();
            connection.commit();
            if(qrPayment.getResponse().getStatus().equals("0")||qrPayment.getResponse().getStatus().equals("-1"))
            {
                Insert_ONL_PAYMENT_BY_CARD(qrPayment);
            }
            return true;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return false;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    final String PROC_QR_CHECK_PCODE = "BEGIN PROC_QR_CHECK_PCODE(?,?,?,?,?,?,?); END;";
    public Object[] PROC_QR_CHECK_PCODE(QRPAYMENT qrPayment) throws Exception {
        CallableStatement calStmt = null;
        ResultSet rs = null;
        try {
            calStmt = connection.prepareCall(PROC_QR_CHECK_PCODE);
            calStmt.setString(1, qrPayment.getRequest().getPartnerid());
            calStmt.setString(2, qrPayment.getRequest().getPromotionCode());
            calStmt.setLong(3, qrPayment.getRequest().getAmountOrder());
            calStmt.setString(4, qrPayment.getCustno());
            calStmt.setString(5, qrPayment.getMobileno());
            calStmt.registerOutParameter(6, OracleTypes.NUMBER); //status
            calStmt.registerOutParameter(7, OracleTypes.NUMBER); //discount amount
            calStmt.execute();
            
            Object[] result = new Object[2];
            result[0] = calStmt.getInt(6);
            result[1] = calStmt.getBigDecimal(7);
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (calStmt != null) {
                calStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    final String Insert_ONL_PAYMENT_BY_CARD="insert into ONL_PAYMENT_BYCARD \n" +
"(ID,TRANSID, CARDNUMBER,AMOUNT, CCY, TRANSDATE, PARTNERID, DESCTIOPTION, STATUS, REFCORE,   CUSTACCOUNT, TYPECARD)\n" +
"values (Seq_Onl_Payment_Bycard.Nextval,?,?,?,'VND',?,?,?,'00',?,?,?)";
    public boolean Insert_ONL_PAYMENT_BY_CARD(QRPAYMENT qrPayment) throws Exception {
        try 
        {
            CallableStatement calStmt = connection.prepareCall(Insert_ONL_PAYMENT_BY_CARD);
            calStmt.setString(1, qrPayment.getResponse().getOrderNo());
            calStmt.setString(2, qrPayment.getRequest().getAccountno());
            calStmt.setLong(3, qrPayment.getResponse().getAmountPayment());
            calStmt.setString(4, qrPayment.getResponse().getRequestDate());
            calStmt.setString(5, qrPayment.getRequest().getPartnerid());
            calStmt.setString(6, qrPayment.getResponse().getDescription());
            calStmt.setString(7, qrPayment.getResponse().getRefcore());
            calStmt.setString(8, qrPayment.getRequest().getAccountno());
            calStmt.setString(9, qrPayment.getRequest().getAccounttype());
            calStmt.execute();
            connection.commit();
            return true;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return false;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    final String DESTROY_QR_PAYMENT = "BEGIN PKG_QR_PAYMENT.PROC_DESTROY_PAYMENT(?,?,?,?,?,?,?); END;";
            /*procedure PROC_DESTROY_PAYMENT(
  pTRANSID varchar2,
  pOrderNo varchar2,
  pPROVIDERID varchar2,
  pDATETIME varchar2,
  pSTATUS out varchar2,
  pSourceAccount out varchar2,
  pDestAccount out varchar2,
  pRefundAmount out number,
  pDesc out varchar2
  )*/
    public Object[] DESTROY_QR_PAYMENT(String pTRANSID,String pOrderNo,String pPROVIDERID,String pDATETIME) throws Exception {
        try 
        {
            CallableStatement calStmt = connection.prepareCall(DESTROY_QR_PAYMENT);
            calStmt.setString(1, pTRANSID);
            calStmt.setString(2, pOrderNo);
            calStmt.setString(3, pPROVIDERID);
            calStmt.setString(4, pDATETIME);
            calStmt.registerOutParameter(5, Types.VARCHAR);
            calStmt.registerOutParameter(6, Types.NUMERIC);
            calStmt.registerOutParameter(7, Types.VARCHAR);
            calStmt.execute();
            Object[] result=new Object[3];
            result[0]=calStmt.getString(5);
            result[1]=calStmt.getBigDecimal(6);
            result[2]=calStmt.getString(7);
            
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    final String UPDATE_DESTROY_QR_PAYMENT = "BEGIN PKG_QR_PAYMENT.PROC_DESTROY_PAYMENT_UPDATE(?,?,?); END;";
            /* procedure PROC_DESTROY_PAYMENT_UPDATE(
  pRefcore varchar2,
  pOrderNo varchar2,
  pPartnerID varchar
  )*/
    public int UPDATE_DESTROY_QR_PAYMENT(String pRefcore,String pOrderNo,String pPROVIDERID) throws Exception {
        try 
        {
            CallableStatement calStmt = connection.prepareCall(UPDATE_DESTROY_QR_PAYMENT);
            calStmt.setString(1, pRefcore);
            calStmt.setString(2, pOrderNo);
            calStmt.setString(3, pPROVIDERID);
            calStmt.execute();
            return 1;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return 0;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
