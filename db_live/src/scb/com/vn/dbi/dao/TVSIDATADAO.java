/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.sql.CallableStatement;
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
public class TVSIDATADAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(TVSIDATADAO.class);

    final String insertKhachHang = "BEGIN pkg_tvsi.insertDataKhachHang(?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String insertDanhMuc = "BEGIN pkg_tvsi.insertDataDanhMuc(?,?,?,?,?,?,?,?,?,?); END;";
    final String insertGiaoDich = "BEGIN pkg_tvsi.insertDataGiaoDich(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String insertGiaoDichHuy = "BEGIN pkg_tvsi.insertDataGiaoDichHuy(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
    final String insertSaoKe = "BEGIN pkg_tvsi.insertDataSaoKe(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

    /**
     *
     * @param list
     * @throws SQLException
     */
    public void insertKhachHang(
            List<TVSI_KHACHHANG> list) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertKhachHang);
            for (int i = 0; i < list.size(); i++) {
                TVSI_KHACHHANG data = (TVSI_KHACHHANG) list.get(i);
                calStmt.setString(1, data.getMa_khach_hang());
                calStmt.setString(2, data.getTen_khach_hang());
                calStmt.setString(3, data.getNgay_sinh());
                calStmt.setString(4, data.getSo_cmnd());
                calStmt.setString(5, data.getNgay_cap());
                calStmt.setString(6, data.getNoi_cap());
                calStmt.setString(7, data.getDia_chi());
                calStmt.setString(8, data.getNgay_mo_hd());
                calStmt.setString(9, data.getTrang_thai());
                calStmt.setString(10, data.getSale_id());
                calStmt.setString(11, data.getMa_cn());
                calStmt.setString(12, data.getTen_cn());
                calStmt.setString(13, data.getNgay_cap_nhat_cuoi());
                calStmt.setString(14, data.getSo_tk_ngan_hang());
                calStmt.execute();
            }
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
     * @param list
     * @throws SQLException
     */
    public void insertDanhMuc(
            List<TVSI_DANHMUC> list) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertDanhMuc);
            for (int i = 0; i < list.size(); i++) {
                TVSI_DANHMUC data = (TVSI_DANHMUC) list.get(i);
                calStmt.setString(1, data.getMa_khach_hang());
                calStmt.setString(2, data.getMa_hop_dong());
                calStmt.setString(3, data.getMa_trai_phieu());
                calStmt.setString(4, data.getNgay_mua());
                calStmt.setString(5, data.getNgay_phat_hanh());
                calStmt.setString(6, data.getNgay_dao_han());
                calStmt.setString(7, data.getNgay_nhan_lai_coupon_ke());
                calStmt.setString(8, data.getSale_id());
                calStmt.setString(9, data.getMa_chi_nhanh());
                calStmt.setString(10, data.getSo_tk_tai_scb());
                calStmt.execute();
            }
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
     * @param list
     * @throws SQLException
     */
    public void insertGiaoDich(
            List<TVSI_GIAODICH> list) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertGiaoDich);
            for (int i = 0; i < list.size(); i++) {
                TVSI_GIAODICH data = (TVSI_GIAODICH) list.get(i);
                calStmt.setString(1, data.getMa_khach_hang());
                calStmt.setString(2, data.getMa_hop_dong());
                calStmt.setString(3, data.getLoai_giao_dich());
                calStmt.setString(4, data.getMa_trai_phieu());
                calStmt.setString(5, data.getNgay_phat_hanh());
                calStmt.setString(6, data.getNgay_dao_han());
                calStmt.setString(7, data.getGia_khop());
                calStmt.setString(8, data.getKhoi_luong_khop());
                calStmt.setString(9, data.getGia_tri_khop());
                calStmt.setString(10, data.getNgay_khop());
                calStmt.setString(11, data.getNgay_mua());
                calStmt.setString(12, data.getGia_tri_mua() == null ? "0" : data.getGia_tri_mua());
                calStmt.setString(13, data.getPhi());//phi chuyen nhuong
                calStmt.setString(14, data.getPhi());//phi nhan chuyen nhuong
                calStmt.setString(15, data.getThue());
                calStmt.setString(16, data.getLs_tuong_ung_thoi_gian_nam_giu() == null ? "0" : data.getLs_tuong_ung_thoi_gian_nam_giu());
                calStmt.setString(17, data.getNgay_nhan_coupon_ke_tiep());
                calStmt.setString(18, data.getSale_id());
                calStmt.setString(19, data.getMa_chi_nhanh());
                calStmt.setString(20, data.getSo_tk_tai_scb());
                calStmt.setString(21, data.getPhi_huy_hop_dong() == null ? "0" : data.getPhi_huy_hop_dong());
                calStmt.setString(22, data.getPhi_chuyen_nhuong_som());
                calStmt.setString(23, data.getChenh_lech_tinh_vao_quy() == null ? "0" : data.getChenh_lech_tinh_vao_quy());
                calStmt.setString(24, data.getTong_coupon_da_nhan_trong_ky() == null ? "0" : data.getTong_coupon_da_nhan_trong_ky());
                calStmt.setString(25, data.getThoi_gian_tu_ngay_phat_hanh_den_ngay_ban() == null ? "0" : data.getThoi_gian_tu_ngay_phat_hanh_den_ngay_ban());
                calStmt.setString(26, data.getThoi_gian_nam_giu_gd_ban() == null ? "0" : data.getThoi_gian_nam_giu_gd_ban());
                calStmt.setString(27, data.getTrang_thai());
                calStmt.setString(28, data.getMenh_gia());
                calStmt.setString(29, data.getNgay_tao());
                calStmt.setString(30, data.getMa_dv_mua());
                calStmt.setString(31, data.getMa_hop_dong_mua());
                calStmt.setString(32, data.getSo_tk_vay());
                calStmt.setString(33, data.getNgay_cap_nhat());
                calStmt.execute();
            }
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
     * @param list
     * @throws SQLException
     */
    public void insertGiaoDichHuy(
            List<TVSI_GIAODICHHUY> list) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertGiaoDichHuy);
            for (int i = 0; i < list.size(); i++) {
                TVSI_GIAODICHHUY data = (TVSI_GIAODICHHUY) list.get(i);
                calStmt.setString(1, data.getNgay_giao_dich());
                calStmt.setString(2, data.getNgay_mua());// ngay mua
                calStmt.setString(3, data.getNgay_huy()); //ngay huy dot sau
                calStmt.setString(4, data.getSo_hop_dong());
                calStmt.setString(5, data.getMa_khach_hang());
                calStmt.setString(6, data.getMa_trai_phieu());
                calStmt.setString(7, data.getLoai_giao_dich());
                calStmt.setString(8, data.getKhoi_luong());
                calStmt.setString(9, data.getGia_tri());
                calStmt.setString(10, data.getPhi_huy());
                calStmt.setString(11, data.getSale_id());
                calStmt.setString(12, data.getMa_chi_nhanh());//pMA_DV
                calStmt.setString(13, data.getGia_khop());
                calStmt.setString(14, data.getMo_ta());
                calStmt.setString(15, data.getTrang_thai());
                calStmt.execute();
            }
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
     * @param list
     * @throws SQLException
     */
    public void insertSaoKe(
            List<TVSI_SAOKE> list) throws SQLException {
        CallableStatement calStmt = null;
        try {
            calStmt = connection.prepareCall(insertSaoKe);
            for (int i = 0; i < list.size(); i++) {
                TVSI_SAOKE data = (TVSI_SAOKE) list.get(i);
                calStmt.setString(1, data.getMa_khach_hang());
                calStmt.setString(2, data.getMa_hop_dong());
                calStmt.setString(3, data.getMa_trai_phieu());
                calStmt.setString(4, data.getGia_khop());
                calStmt.setString(5, data.getKhoi_luong_khop());
                calStmt.setString(6, data.getGia_tri_nam_giu());
                calStmt.setString(7, data.getNgay_nhan());
                calStmt.setString(8, data.getNgay_phat_hanh());
                calStmt.setString(9, data.getNgay_dao_han());
                calStmt.setString(10, data.getSale_id());
                calStmt.setString(11, data.getMa_chi_nhanh());
                calStmt.setString(12, data.getCif());
                calStmt.setString(13, data.getMa_chi_nhanh_cif());//pMA_DV_CIF
                calStmt.setString(14, data.getMenh_gia());
                calStmt.setString(15, data.getTrang_thai());
                calStmt.execute();
            }
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
}
