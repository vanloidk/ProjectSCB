/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.sql.SQLException;
import java.util.List;
import scb.com.vn.dbi.dao.DvkhDAO;
import scb.com.vn.dbi.dto.GifCustomerDetail;
import scb.com.vn.dbi.dto.GiftDetail;

/**
 *
 *
 * @author minhndb
 */
public class DvkhBO {

    private DvkhDAO dvkhDAO;

    /**
     * Create a new instance of DvkhBO
     */
    public DvkhBO() {
        dvkhDAO = new DvkhDAO();
    }

    /**
     *
     * @param branchcode
     * @return
     * @throws Exception
     */
    public List GetDanhMucQuaTang(String branchcode) throws Exception {
        return dvkhDAO.GetDanhMucQuaTang(branchcode);
    }

    /**
     *
     * @param cusdetail
     * @param gitfdetail
     * @return
     * @throws Exception
     */
    public String GhiNhanThongTinDoiQua(GifCustomerDetail cusdetail, GiftDetail gitfdetail) throws Exception {
        return dvkhDAO.GhiNhanThongTinDoiQua(cusdetail, gitfdetail);
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
        return dvkhDAO.LayLichSuDoiQua(loc, pan, fromdate, todate);
    }

    /**
     *
     * @param MaKH
     * @return
     * @throws Exception
     */
    public int KIEM_TRA_THONG_TIN_KHACH_HANG_IS_EXISTING(String MaKH) throws Exception {
        return dvkhDAO.KIEM_TRA_THONG_TIN_KHACH_HANG_IS_EXISTING(MaKH);
    }

    /**
     *
     * @param cardAccountNo
     * @return
     * @throws Exception
     */
    public int GET_TOTAL_POINT_PENDING(String cardAccountNo) throws Exception {
        return dvkhDAO.GET_TOTAL_POINT_PENDING(cardAccountNo);
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
        return dvkhDAO.INSERT_THONG_TIN_KHACH_HANG(ID, MaKH, HoTen, CMND, DiaChi, NgayCap, NoiCap,
                GioiTinh, DienThoai, Loc, Pan, locPan, LoaiThe, HangThe, CrdStat, BrchCode, TheChinhPhu,
                TemLock, LocLMT, MaDVNhap, UidNhap, VNAID, Ho, TenTenDem);
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
    public String CC_InsertPhongToaGiaiToaTKTT(String partner, String accountOrCif, String phongToaGiaiToa, String userName, String expiryDate, String maDV, String status, String type ) throws SQLException {
        return dvkhDAO.CC_InsertPhongToaGiaiToaTKTT(partner, accountOrCif, phongToaGiaiToa, userName, expiryDate, maDV,status, type);
    }
    
    /**
     * CC_UpdatePhongToaGiaiToaTKTT
     * 
     * @param id
     * @param status
     * @return
     * @throws SQLException 
     */
     public long CC_UpdatePhongToaGiaiToaTKTT(long id, String status) throws  SQLException {
        return dvkhDAO.CC_UpdatePhongToaGiaiToaTKTT(id, status);
    }
}
