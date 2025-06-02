/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.util.List;

/**
 *
 * @author lydty
 */
public class HQ_DKNNT implements java.io.Serializable {

    String So_HS;
    String Loai_HS;
    String Ma_DV;
    String Ten_DV;
    String DiaChi;
    String So_CMT;
    String Ho_Ten;
    String NgaySinh;
    String NguyenQuan;
    String SerialNumber;
    String Noi_Cap;
    String Ngay_HL;
    String Ngay_HHL;
    String PublicKey;
    String Ma_NH_TH;
    String Ten_NH_TH;
    String TaiKhoan_TH;
    String Ten_TaiKhoan_TH;

    List<HQ_DKNNT_LIENHE> ThongTinLienHe;

    String UserID;
    String BranchID;
    String ChannelID;
    String Desc;
    String Status;
    String Ngay_HL_DK;
    String ID;
    String ACCEPTED;
    String NGAY_HL_DK;
    String ISDELETED;
    String CREATEDATE;
    String CHECKDATE;
    String APPROVEDATE;
    String STATUS;

    //NEW 2019
    String NGAY_HL_UQ;
    String NGAY_HHL_UQ;
    String MSGTYPE;
    String Transid;
    String requestid;

    /**
     *
     * @return
     */
    public String getNGAY_HL_UQ() {
        return NGAY_HL_UQ;
    }

    /**
     *
     * @param NGAY_HL_UQ
     */
    public void setNGAY_HL_UQ(String NGAY_HL_UQ) {
        this.NGAY_HL_UQ = NGAY_HL_UQ;
    }

    /**
     *
     * @return
     */
    public String getNGAY_HHL_UQ() {
        return NGAY_HHL_UQ;
    }

    /**
     *
     * @param NGAY_HHL_UQ
     */
    public void setNGAY_HHL_UQ(String NGAY_HHL_UQ) {
        this.NGAY_HHL_UQ = NGAY_HHL_UQ;
    }

    /**
     *
     * @return
     */
    public String getMSGTYPE() {
        return MSGTYPE;
    }

    /**
     *
     * @param MSGTYPE
     */
    public void setMSGTYPE(String MSGTYPE) {
        this.MSGTYPE = MSGTYPE;
    }

    /**
     *
     * @return
     */
    public String getRequestid() {
        return requestid;
    }

    /**
     *
     * @param requestid
     */
    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    /**
     *
     * @return
     */
    public String getSTATUS() {
        return STATUS;
    }

    /**
     *
     * @param STATUS
     */
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    /**
     *
     * @return
     */
    public String getCREATEDATE() {
        return CREATEDATE;
    }

    /**
     *
     * @param CREATEDATE
     */
    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    /**
     *
     * @return
     */
    public String getCHECKDATE() {
        return CHECKDATE;
    }

    /**
     *
     * @param CHECKDATE
     */
    public void setCHECKDATE(String CHECKDATE) {
        this.CHECKDATE = CHECKDATE;
    }

    /**
     *
     * @return
     */
    public String getAPPROVEDATE() {
        return APPROVEDATE;
    }

    /**
     *
     * @param APPROVEDATE
     */
    public void setAPPROVEDATE(String APPROVEDATE) {
        this.APPROVEDATE = APPROVEDATE;
    }

    /**
     *
     * @return
     */
    public String getISDELETED() {
        return ISDELETED;
    }

    /**
     *
     * @param ISDELETED
     */
    public void setISDELETED(String ISDELETED) {
        this.ISDELETED = ISDELETED;
    }

    /**
     *
     * @return
     */
    public String getNGAY_HL_DK() {
        return NGAY_HL_DK;
    }

    /**
     *
     * @param NGAY_HL_DK
     */
    public void setNGAY_HL_DK(String NGAY_HL_DK) {
        this.NGAY_HL_DK = NGAY_HL_DK;
    }

    /**
     *
     * @return
     */
    public String getACCEPTED() {
        return ACCEPTED;
    }

    /**
     *
     * @param ACCEPTED
     */
    public void setACCEPTED(String ACCEPTED) {
        this.ACCEPTED = ACCEPTED;
    }

    /**
     *
     * @return
     */
    public String getID() {
        return ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *
     * @return
     */
    public String getNgay_HL_DK() {
        return Ngay_HL_DK;
    }

    /**
     *
     * @param Ngay_HL_DK
     */
    public void setNgay_HL_DK(String Ngay_HL_DK) {
        this.Ngay_HL_DK = Ngay_HL_DK;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return Status;
    }

    /**
     *
     * @param Status
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     *
     * @return
     */
    public String getDesc() {
        return Desc;
    }

    /**
     *
     * @param Desc
     */
    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    /**
     *
     * @return
     */
    public String getTransid() {
        return Transid;
    }

    /**
     *
     * @param Transid
     */
    public void setTransid(String Transid) {
        this.Transid = Transid;
    }

    /**
     *
     * @return
     */
    public List<HQ_DKNNT_LIENHE> getThongTinLienHe() {
        return ThongTinLienHe;
    }

    /**
     *
     * @param ThongTinLienHe
     */
    public void setThongTinLienHe(List<HQ_DKNNT_LIENHE> ThongTinLienHe) {
        this.ThongTinLienHe = ThongTinLienHe;
    }

    /**
     *
     * @return
     */
    public String getUserID() {
        return UserID;
    }

    /**
     *
     * @param UserID
     */
    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    /**
     *
     * @return
     */
    public String getBranchID() {
        return BranchID;
    }

    /**
     *
     * @param BranchID
     */
    public void setBranchID(String BranchID) {
        this.BranchID = BranchID;
    }

    /**
     *
     * @return
     */
    public String getChannelID() {
        return ChannelID;
    }

    /**
     *
     * @param ChannelID
     */
    public void setChannelID(String ChannelID) {
        this.ChannelID = ChannelID;
    }

    /**
     *
     * @return
     */
    public String getSo_HS() {
        return So_HS;
    }

    /**
     *
     * @param So_HS
     */
    public void setSo_HS(String So_HS) {
        this.So_HS = So_HS;
    }

    /**
     *
     * @return
     */
    public String getLoai_HS() {
        return Loai_HS;
    }

    /**
     *
     * @param Loai_HS
     */
    public void setLoai_HS(String Loai_HS) {
        this.Loai_HS = Loai_HS;
    }

    /**
     *
     * @return
     */
    public String getMa_DV() {
        return Ma_DV;
    }

    /**
     *
     * @param Ma_DV
     */
    public void setMa_DV(String Ma_DV) {
        this.Ma_DV = Ma_DV;
    }

    /**
     *
     * @return
     */
    public String getTen_DV() {
        return Ten_DV;
    }

    /**
     *
     * @param Ten_DV
     */
    public void setTen_DV(String Ten_DV) {
        this.Ten_DV = Ten_DV;
    }

    /**
     *
     * @return
     */
    public String getDiaChi() {
        return DiaChi;
    }

    /**
     *
     * @param DiaChi
     */
    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    /**
     *
     * @return
     */
    public String getSo_CMT() {
        return So_CMT;
    }

    /**
     *
     * @param So_CMT
     */
    public void setSo_CMT(String So_CMT) {
        this.So_CMT = So_CMT;
    }

    /**
     *
     * @return
     */
    public String getHo_Ten() {
        return Ho_Ten;
    }

    /**
     *
     * @param Ho_Ten
     */
    public void setHo_Ten(String Ho_Ten) {
        this.Ho_Ten = Ho_Ten;
    }

    /**
     *
     * @return
     */
    public String getNgaySinh() {
        return NgaySinh;
    }

    /**
     *
     * @param NgaySinh
     */
    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    /**
     *
     * @return
     */
    public String getNguyenQuan() {
        return NguyenQuan;
    }

    /**
     *
     * @param NguyenQuan
     */
    public void setNguyenQuan(String NguyenQuan) {
        this.NguyenQuan = NguyenQuan;
    }

    /**
     *
     * @return
     */
    public String getSerialNumber() {
        return SerialNumber;
    }

    /**
     *
     * @param SerialNumber
     */
    public void setSerialNumber(String SerialNumber) {
        this.SerialNumber = SerialNumber;
    }

    /**
     *
     * @return
     */
    public String getNoi_Cap() {
        return Noi_Cap;
    }

    /**
     *
     * @param Noi_Cap
     */
    public void setNoi_Cap(String Noi_Cap) {
        this.Noi_Cap = Noi_Cap;
    }

    /**
     *
     * @return
     */
    public String getNgay_HL() {
        return Ngay_HL;
    }

    /**
     *
     * @param Ngay_HL
     */
    public void setNgay_HL(String Ngay_HL) {
        this.Ngay_HL = Ngay_HL;
    }

    /**
     *
     * @return
     */
    public String getNgay_HHL() {
        return Ngay_HHL;
    }

    /**
     *
     * @param Ngay_HHL
     */
    public void setNgay_HHL(String Ngay_HHL) {
        this.Ngay_HHL = Ngay_HHL;
    }

    /**
     *
     * @return
     */
    public String getPublicKey() {
        return PublicKey;
    }

    /**
     *
     * @param PublicKey
     */
    public void setPublicKey(String PublicKey) {
        this.PublicKey = PublicKey;
    }

    /**
     *
     * @return
     */
    public String getMa_NH_TH() {
        return Ma_NH_TH;
    }

    /**
     *
     * @param Ma_NH_TH
     */
    public void setMa_NH_TH(String Ma_NH_TH) {
        this.Ma_NH_TH = Ma_NH_TH;
    }

    /**
     *
     * @return
     */
    public String getTen_NH_TH() {
        return Ten_NH_TH;
    }

    /**
     *
     * @param Ten_NH_TH
     */
    public void setTen_NH_TH(String Ten_NH_TH) {
        this.Ten_NH_TH = Ten_NH_TH;
    }

    /**
     *
     * @return
     */
    public String getTaiKhoan_TH() {
        return TaiKhoan_TH;
    }

    /**
     *
     * @param TaiKhoan_TH
     */
    public void setTaiKhoan_TH(String TaiKhoan_TH) {
        this.TaiKhoan_TH = TaiKhoan_TH;
    }

    /**
     *
     * @return
     */
    public String getTen_TaiKhoan_TH() {
        return Ten_TaiKhoan_TH;
    }

    /**
     *
     * @param Ten_TaiKhoan_TH
     */
    public void setTen_TaiKhoan_TH(String Ten_TaiKhoan_TH) {
        this.Ten_TaiKhoan_TH = Ten_TaiKhoan_TH;
    }

}
