/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.rmi.RemoteException;
import scb.com.vn.dbi.dao.TVSIDATADAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dto.TVSI_DANHMUC;
import scb.com.vn.dbi.dto.TVSI_GIAODICH;
import scb.com.vn.dbi.dto.TVSI_GIAODICHHUY;
import scb.com.vn.dbi.dto.TVSI_KHACHHANG;
import scb.com.vn.dbi.dto.TVSI_SAOKE;

/**
 *
 * @author lydty
 */
public class TVSIDATABO extends BaseDWH {

    private static final Logger LOGGER = Logger.getLogger(TVSIDATABO.class);

    private Connection conn;
    private TVSIDATADAO dao = null;

    /**
     * Create a new instance of TVSIDATABO
     */
    public TVSIDATABO() {
        dao = new TVSIDATADAO();
    }

    /**
     *
     * @param list
     * @throws RemoteException
     * @throws SQLException
     */
    public void insertKhachHang(List<TVSI_KHACHHANG> list) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertKhachHang(list);
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
     * @param list
     * @throws RemoteException
     * @throws SQLException
     */
    public void insertDanhMuc(
            List<TVSI_DANHMUC> list) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertDanhMuc(list);
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
     * @param list
     * @throws RemoteException
     * @throws SQLException
     */
    public void insertGiaoDich(
            List<TVSI_GIAODICH> list) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertGiaoDich(list);
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
     * @param list
     * @throws RemoteException
     * @throws SQLException
     */
    public void insertGiaoDichHuy(
            List<TVSI_GIAODICHHUY> list) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertGiaoDichHuy(list);
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
     * @param list
     * @throws RemoteException
     * @throws SQLException
     */
    public void insertSaoKe(
            List<TVSI_SAOKE> list) throws RemoteException, SQLException {
        try {
            conn = super.getConnection();
            dao.setConnection(conn);
            dao.insertSaoKe(list);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
