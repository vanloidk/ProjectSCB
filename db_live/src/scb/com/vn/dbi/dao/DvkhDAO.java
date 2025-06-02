package scb.com.vn.dbi.dao;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.connection.ConnectionManager;
import scb.com.vn.dbi.dto.GifCustomerDetail;
import scb.com.vn.dbi.dto.GiftDetail;

import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author system
 */
public class DvkhDAO {

    private static final Logger LOGGER = Logger.getLogger(DvkhDAO.class);

    final String GET_DANHMUC_QUATANG = "SELECT * FROM [dbo].[VIEW_282_DDTTD_DANHMUC_QUATANG_TONKHO_MB] WHERE (MADV = ? AND SOLUONG > 0) OR LOAIQT = 2 OR LOAIQT = 4 OR (LOAIQT = 100 AND SOLUONG > 0) OR (LOAIQT = 5 AND ID <> 71) OR (LOAIQT = 6 AND SOLUONG > 0) ORDER BY DIEMQUYDOI ASC";
    final String GET_TOTAL_POINT_PENDING = "SELECT ISNULL(SUM(TONGDIEM),0) AS TONGDIEM FROM [dbo].[TABLE_282_DDTTD_DOT_DOIDIEM] WHERE LOCPAN = ? AND TINHTRANG NOT IN (2)";
    final String GHINHAN_THONGTIN_DOIDIEM = " {? = call [dbo].[TABLE_282_DDTTD_GHINHAN_THONGTIN_DOIDIEM_MB] (? ,? ,?,?,?,?,?,?,?,?,?)} ";
    // THONGTIN_GIAODICH_MASTER  them dieu kien TINHTRANG=2 chi lay nhung giao dich duyet success
    final String THONGTIN_GIAODICH_MASTER = "SELECT TITLE, SOLUONGDOI AS SOLUONG, DIEMQUYDOI, NGAY_NHAP NGAYNHAP FROM [dbo].[VIEW_282_DDTTD_THONGTIN_GIAODICH_MASTER]\n"
            + "WHERE LOC = ? AND PAN = ? AND CAST(NGAY_NHAP as DATE)>= ? AND CAST(NGAY_NHAP as DATE)<= ?\n"
            + "ORDER BY NGAY_NHAP DESC";
    final String KIEM_TRA_THONG_TIN_KHACH_HANG_IS_EXISTING = "SELECT CMND FROM [dbo].[TABLE_282_DDTTD_THONGTIN_KHACHHANG] WHERE MAKH = ?";
    final String INSERT_THONG_TIN_KHACH_HANG = " {? = call [dbo].[TABLE_282_DDTTD_GHINHAN_THONGTIN_KHACHHANG_MB] (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)} ";

    /**
     *
     * @param cusdetail
     * @param gitfdetail
     * @return
     * @throws SQLException
     */
    public String GhiNhanThongTinDoiQua(GifCustomerDetail cusdetail, GiftDetail gitfdetail) throws SQLException {
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("dvkh");
            calStmt = connection.prepareCall(GHINHAN_THONGTIN_DOIDIEM);
            calStmt.setString(2, cusdetail.getCifNo());
            calStmt.setString(3, cusdetail.getCifNo().concat(cusdetail.getCardAccountNumber().concat(cusdetail.getCardNo().substring(12))));
            calStmt.setInt(4, Integer.parseInt(cusdetail.getMakerId()));
            calStmt.setString(5, cusdetail.getAccountBranchOpen());
            calStmt.setInt(6, Integer.parseInt(gitfdetail.getDiemquydoi()));
            calStmt.setInt(7, Integer.parseInt(gitfdetail.getId()));
            calStmt.setInt(8, Integer.parseInt(gitfdetail.getSoluong()));
            calStmt.setString(9, cusdetail.getAccountBranchOpen());
            calStmt.setString(10, cusdetail.getMakerId());
            String idGenerator = String.valueOf(System.nanoTime()) + String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
            String bookingId = idGenerator.substring(0, 50);
            calStmt.setString(11, bookingId);
            calStmt.setString(12, gitfdetail.getGhichu());
            calStmt.registerOutParameter(1, Types.VARCHAR); //status
            calStmt.execute();
            String ref = calStmt.getString(1);
            return ref;
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
     * @param branchcode
     * @return
     * @throws Exception
     */
    public List GetDanhMucQuaTang(String branchcode) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("dvkh");
            ArrayList p_args = new ArrayList();
            p_args.add(branchcode);
            ArrayList list = JDBCEngine.executeQuery(GET_DANHMUC_QUATANG, p_args, connection);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param loc
     * @param pan
     * @param fromdate
     * @param todate
     * @return
     * @throws Exception
     */
    public List LayLichSuDoiQua(String loc, String pan, String fromdate, String todate) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("dvkh");
            ArrayList p_args = new ArrayList();
            p_args.add(loc);
            p_args.add(pan);
            p_args.add(fromdate.substring(0, 4).concat("-").concat(fromdate.substring(4, 6).concat("-").concat(fromdate.substring(6))));
            p_args.add(todate.substring(0, 4).concat("-").concat(todate.substring(4, 6).concat("-").concat(todate.substring(6))));
            ArrayList list = JDBCEngine.executeQuery(THONGTIN_GIAODICH_MASTER, p_args, connection);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param MaKH
     * @return
     * @throws Exception
     */
    public int KIEM_TRA_THONG_TIN_KHACH_HANG_IS_EXISTING(String MaKH) throws Exception {
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionManager.getConnection("dvkh");
            preStatement = connection.prepareStatement(KIEM_TRA_THONG_TIN_KHACH_HANG_IS_EXISTING);
            preStatement.setString(1, MaKH);
            rs = preStatement.executeQuery();
            if (rs.getRow() == 0) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param cardAccountNo
     * @return
     * @throws Exception
     */
    public int GET_TOTAL_POINT_PENDING(String cardAccountNo) throws Exception {
        Connection connection = null;
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionManager.getConnection("dvkh");
            preStatement = connection.prepareStatement(GET_TOTAL_POINT_PENDING);
            preStatement.setString(1, cardAccountNo);
            rs = preStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param ID
     * @param MaKH
     * @param HoTen
     * @param CMND
     * @param DiaChi
     * @param NgayCap
     * @param NoiCap
     * @param GioiTinh
     * @param DienThoai
     * @param Loc
     * @param Pan
     * @param locPan
     * @param LoaiThe
     * @param HangThe
     * @param CrdStat
     * @param BrchCode
     * @param TheChinhPhu
     * @param TemLock
     * @param LocLMT
     * @param MaDVNhap
     * @param UidNhap
     * @param VNAID
     * @param Ho
     * @param TenTenDem
     * @return
     * @throws Exception
     */
    public String INSERT_THONG_TIN_KHACH_HANG(int ID, String MaKH, String HoTen, String CMND,
            String DiaChi, String NgayCap, String NoiCap, String GioiTinh, String DienThoai,
            String Loc, String Pan, String locPan, int LoaiThe, int HangThe, String CrdStat, String BrchCode,
            String TheChinhPhu, String TemLock, String LocLMT, String MaDVNhap,
            int UidNhap, String VNAID, String Ho, String TenTenDem) throws Exception {
        CallableStatement calStmt = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("dvkh");
            calStmt = connection.prepareCall(INSERT_THONG_TIN_KHACH_HANG);
            calStmt.setInt(2, ID);
            calStmt.setString(3, MaKH);
            calStmt.setString(4, HoTen);
            calStmt.setString(5, CMND);
            calStmt.setString(6, DiaChi);
            NgayCap = null;
            if (NgayCap != null && !NgayCap.isEmpty()) {
                calStmt.setDate(7, java.sql.Date.valueOf(NgayCap));
            } else {
                calStmt.setDate(7, null);
            }
            calStmt.setString(8, NoiCap);
            calStmt.setString(9, GioiTinh);
            calStmt.setString(10, DienThoai);
            calStmt.setString(11, Loc);
            calStmt.setString(12, Pan);
            calStmt.setString(13, locPan);
            calStmt.setInt(14, LoaiThe);
            calStmt.setInt(15, HangThe);
            calStmt.setString(16, CrdStat);
            calStmt.setString(17, BrchCode);
            calStmt.setString(18, TheChinhPhu);
            calStmt.setString(19, TemLock);
            calStmt.setString(20, LocLMT);
            calStmt.setString(21, MaDVNhap);
            calStmt.setInt(22, UidNhap);
            calStmt.setString(23, VNAID);
            calStmt.setString(24, Ho);
            calStmt.setString(25, TenTenDem);
            calStmt.registerOutParameter(1, Types.VARCHAR); //status
            calStmt.execute();
            String ref = calStmt.getString(1);
            return ref;
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

    final String S_CC_KHOATKTT = "select * from SMS_SCB.CC_KHOAMOKHOATKTT dd where DD.PARTNER='MPC' and DD.ACCOUNTORCIF = ? and DD.USERNAME = ? and DD.EXPIRYDATE = ? and DD.MADV = ? and DD.TYPE = ? and DD.STATUS='00' and DD.LOCK_OPENLOCK ='PHONGTOA'";

    /**
     * CheckPhongToaTKTT
     *
     * @param stk
     * @param userName
     * @param expiryDate
     * @param maDV
     * @param type
     * @return
     * @throws Exception
     */
    public boolean checkPhongToaTKTT(String stk, String userName, String expiryDate, String maDV, String type) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("dvkh");
            ArrayList p_args = new ArrayList();
            p_args.add(stk);
            p_args.add(userName);
            p_args.add(expiryDate);
            p_args.add(maDV);
            p_args.add(type);

            ArrayList list = JDBCEngine.executeQuery(S_CC_KHOATKTT, p_args, connection);
            return (list.size() > 0);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    final String S_CC_MOKHOATKTT = "select * from SMS_SCB.CC_KHOAMOKHOATKTT dd  where DD.LOCK_OPENLOCK ='GIAIPHONGTOA' and DD.PARTNER='MPC'  and DD.ACCOUNTORCIF = ? and DD.USERNAME = ? and DD.MADV = ? and DD.STATUS='00'";

    /**
     * CheckGiaiToaTKTT
     *
     * @param stk
     * @param userName
     * @param maDV
     * @return
     * @throws Exception
     */
    public boolean checkGiaiToaTKTT(String stk, String userName, String maDV) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("dvkh");
            ArrayList p_args = new ArrayList();
            p_args.add(stk);
            p_args.add(userName);
            p_args.add(maDV);

            ArrayList list = JDBCEngine.executeQuery(S_CC_MOKHOATKTT, p_args, connection);
            return (list.size() > 0);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * CC_InsertPhongToaGiaiToaTKTT
     *
     * @param partner
     * @param accountOrCif
     * @param phongToaGiaiToa
     * @param userName
     * @param expiryDate
     * @param maDV
     * @param status
     * @param type
     * @return
     * @throws java.sql.SQLException
     */
    final String INS_CC_KHOAMOKHOATKTT = "INSERT INTO SMS_SCB.CC_KHOAMOKHOATKTT(PARTNER,ACCOUNTORCIF,LOCK_OPENLOCK, USERNAME, EXPIRYDATE, MADV, STATUS, TYPE, CHANNEL, CREATED_DATE) VALUES(?,?,?,?,?,?,?,?,?, SYSDATE)";

    public String CC_InsertPhongToaGiaiToaTKTT(String partner, String accountOrCif, String phongToaGiaiToa, String userName, String expiryDate, String maDV, String status, String type) throws SQLException {

        long id = -1;
        /*
        boolean isPhongToa = false;
        boolean isGiaiToa = false;
        switch (phongToaGiaiToa) {
            case "PHONGTOA": {
                try {
                    isPhongToa = checkPhongToaTKTT(accountOrCif, userName, expiryDate, maDV, type);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                    return String.valueOf(id);
                }
            }
            break;
            case "GIAIPHONGTOA": {
                try {
                    isGiaiToa = checkGiaiToaTKTT(accountOrCif, userName, maDV);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                    return String.valueOf(id);
                }
            }
            break;
            default:
                break;
        }

        if (isPhongToa || isGiaiToa) {//tai khoan đa phong toa hoac giai toa truoc đo roi
            return "-02";
        }
         */
        Connection connection = null;
        PreparedStatement preStatement = null;
        try {
            connection = ConnectionManager.getConnection("dvkh");
            String generatedColumns[] = {"ID"};
            preStatement = connection.prepareStatement(INS_CC_KHOAMOKHOATKTT, generatedColumns);
            preStatement.setString(1, partner);
            preStatement.setString(2, accountOrCif);
            preStatement.setString(3, phongToaGiaiToa);
            preStatement.setString(4, userName);
            preStatement.setString(5, expiryDate);
            preStatement.setString(6, maDV);
            preStatement.setString(7, status);
            preStatement.setString(8, type);
            preStatement.setString(9, "CRM");

            preStatement.execute();
            connection.commit();
            ResultSet rs = preStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }

            return String.valueOf(id);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return "-1";
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * CC_UpdatePhongToaGiaiToaTKTT
     *
     * @param status
     * @return
     * @throws SQLException
     */
    final String UD_CC_KHOAMOKHOATKTT = "UPDATE SMS_SCB.CC_KHOAMOKHOATKTT SET STATUS = ?, UPDATED_DATE = SYSDATE where ID = ?";

    public long CC_UpdatePhongToaGiaiToaTKTT(long id, String status) throws SQLException {
        Connection connection = null;
        PreparedStatement preStatement = null;
        try {
            connection = ConnectionManager.getConnection("dvkh");
            preStatement = connection.prepareStatement(UD_CC_KHOAMOKHOATKTT);
            preStatement.setString(1, status);
            preStatement.setLong(2, id);

            if (preStatement.executeUpdate() <= 0) {
                return 0;
            }

            return id;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        } finally {
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
