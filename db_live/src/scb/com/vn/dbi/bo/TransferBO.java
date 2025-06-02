/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.sql.Connection;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dao.TransferDAO;
import scb.com.vn.dbi.dto.TransferDTO;
import scb.com.vn.dbi.dto.TransferDetailDTO;


/**
 *
 * @author Administrator
 */
public class TransferBO extends BaseSMSSCBBO {

    private static final Logger LOGGER = Logger.getLogger(TransferBO.class);

    private Connection conn;
    private TransferDAO dao = null;
    public int Insert_TRANFER(TransferDTO transfer) throws Exception {

        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.Insert_TRANFER(transfer);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
     public int Insert_TRANFER_DETAIL(TransferDetailDTO transferdetail) throws Exception {

        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.Insert_TRANFER_DETAIL(transferdetail);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    public int Update_TRANFER_DETAIL(TransferDetailDTO transferdetail) throws Exception 
    {
         try {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.Update_TRANFER_DETAIL(transferdetail);
        } catch (Exception IBEx) {
            LOGGER.error(IBEx);
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqlEx) {
                LOGGER.error(sqlEx);
            }
            throw IBEx;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
