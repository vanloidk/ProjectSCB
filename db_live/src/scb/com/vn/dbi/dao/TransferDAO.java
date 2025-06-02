/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import scb.com.vn.dbi.dto.TransferDTO;
import scb.com.vn.dbi.dto.TransferDetailDTO;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author Administrator
 */
public class TransferDAO  extends BaseDAO {
    private static final Logger LOGGER = Logger.getLogger(TransferDAO.class);
    
     final String Insert_SI_TRANFERTOSI = "insert into SI_TRFFROMSI (ID, PARTNERID, TRANSID, TRANSDATE) values (?,?,?,?,)";
     final String Insert_SI_TRANFERTOSI_DETAIL="insert into SI_TRFFROMSI_DETAIL (ID, ID_MASTER, TXNDETAILID, PARTNERACCOUNT, CUSTUMERNAME, CUSTUMERACCOUNT, BANKCODE, BRANCHNAME, AMOUNT, CCY, DESCRIPTION,STATUS,TYPETRANSFER,TYPECUSTACCOUNT)\n" +
     " values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
     
     final String Update_SI_TRANFERTOSI_DETAIL="update SI_TRFFROMSI_DETAIL\n" +
            "   set\n" +
            "   REFCORE= ?,\n" +
            "   VALIDDATECORE=?,\n" +
            "   STATUS=?\n" +
            "   where ID= ?";
      public int getIdSeqByName(String seqname) throws Exception {
        String query = "select " + seqname + ".nextval from dual";
        SQLQuery q = getSession().createSQLQuery(query);
        List l = q.list();
        return Integer.parseInt(l.get(0).toString());
    }
     public int Insert_TRANFER(TransferDTO transfer) throws Exception {

        CallableStatement calStmt = null;
        try {
            
            int id= getIdSeqByName("SEQ_SI_FROMSI");
            calStmt = connection.prepareCall(Insert_SI_TRANFERTOSI);
            calStmt.setInt(1, id);
            calStmt.setString(2, transfer.getPARTNERID());
            calStmt.setString(3, transfer.getTRANSID());
            calStmt.setString(4, transfer.getTRANSDATE());
            calStmt.execute();
            connection.commit();
            return id;
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
     public int Insert_TRANFER_DETAIL(TransferDetailDTO transferdetail) throws Exception {

        CallableStatement calStmt = null;
        try {
            
            int id= getIdSeqByName("SEQ_SI_TRANSFROMSI_DETAIL");
            calStmt = connection.prepareCall(Insert_SI_TRANFERTOSI_DETAIL);
            
            calStmt.setInt(1, id);
            calStmt.setString(2,transferdetail.getID_MASTER());
            calStmt.setString(3, transferdetail.getTXNDETAILID());
            calStmt.setString(4, transferdetail.getFROMACCOUNT());
            calStmt.setString(5, transferdetail.getDESTNAME());
            calStmt.setString(6, transferdetail.getDESTACCOUNT());
            calStmt.setString(7, transferdetail.getBANKCODE());
            calStmt.setString(8, transferdetail.getBRANCHNAME());
            calStmt.setString(9, transferdetail.getAMOUNT());
            calStmt.setString(10, transferdetail.getCCY());
            calStmt.setString(11, transferdetail.getDESCRIPTION());
            calStmt.setString(12, transferdetail.getSTATUS());
            calStmt.setString(13, transferdetail.getTYPETRANSFER());
            calStmt.setString(14, transferdetail.getTYPEDESTACCOUNT());
            calStmt.execute();
            connection.commit();
            return id;
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
    public int Update_TRANFER_DETAIL(TransferDetailDTO transferdetail) throws Exception 
    {
        try
        {
            ArrayList<String> p_args = new ArrayList<>();           
            p_args.add(transferdetail.getRefcore());
            p_args.add(transferdetail.getValidateCore());
            p_args.add(transferdetail.getSTATUS());
            p_args.add(transferdetail.getID());
            int result =  JDBCEngine.executeUpdate(Update_SI_TRANFERTOSI_DETAIL, p_args, connection);
            connection.commit();
            return result;
        } catch (Exception ex) {
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
