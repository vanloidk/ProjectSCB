/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author loinv3
 */
public class BHBLCstContractCollectedByTheResp implements Serializable {

    private static final long serialVersionUID = -1L;
    private String EnterFromBL;
    private String ContractBL;
    private String ContractSCB;
    private BigDecimal TotalCost;
    private Timestamp PaymentDate;
    private String Status;
    private int PaymentCode;
    private String HoTen1;
    private String sdt;
    private String HoTen2;
    private String GioiTinh;
    private String NgaySinh;
    private String CMND;
    private String email;
    private Long IdGoiSP;
    private String GoiSP;
    private BigDecimal NgoaiTru;
    private BigDecimal NhaKhoa;
    private BigDecimal TaiNan;
    private BigDecimal SinhMang;
    private BigDecimal ThaiSan;
    private BigDecimal BHChinh;
    private BigDecimal TongPhi;
    private Timestamp NgayHieuLuc;
    private Timestamp NgayKetThuc;
    private Timestamp NgayTao;
    private String HTThanhToan;
    private Timestamp NgayTT;
    private String MaGDSCB;
    private int Tuoi;

    public String getEnterFromBL() {
        return EnterFromBL;
    }

    public void setEnterFromBL(String EnterFromBL) {
        this.EnterFromBL = EnterFromBL;
    }

    public String getContractBL() {
        return ContractBL;
    }

    public void setContractBL(String ContractBL) {
        this.ContractBL = ContractBL;
    }

    public String getContractSCB() {
        return ContractSCB;
    }

    public void setContractSCB(String ContractSCB) {
        this.ContractSCB = ContractSCB;
    }

    public BigDecimal getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(BigDecimal TotalCost) {
        this.TotalCost = TotalCost;
    }

    public Timestamp getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(Timestamp PaymentDate) {
        this.PaymentDate = PaymentDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getPaymentCode() {
        return PaymentCode;
    }

    public void setPaymentCode(int PaymentCode) {
        this.PaymentCode = PaymentCode;
    }

    public String getHoTen1() {
        return HoTen1;
    }

    public void setHoTen1(String HoTen1) {
        this.HoTen1 = HoTen1;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getHoTen2() {
        return HoTen2;
    }

    public void setHoTen2(String HoTen2) {
        this.HoTen2 = HoTen2;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdGoiSP() {
        return IdGoiSP;
    }

    public void setIdGoiSP(Long IdGoiSP) {
        this.IdGoiSP = IdGoiSP;
    }

    public String getGoiSP() {
        return GoiSP;
    }

    public void setGoiSP(String GoiSP) {
        this.GoiSP = GoiSP;
    }

    public BigDecimal getNgoaiTru() {
        return NgoaiTru;
    }

    public void setNgoaiTru(BigDecimal NgoaiTru) {
        this.NgoaiTru = NgoaiTru;
    }

    public BigDecimal getNhaKhoa() {
        return NhaKhoa;
    }

    public void setNhaKhoa(BigDecimal NhaKhoa) {
        this.NhaKhoa = NhaKhoa;
    }

    public BigDecimal getTaiNan() {
        return TaiNan;
    }

    public void setTaiNan(BigDecimal TaiNan) {
        this.TaiNan = TaiNan;
    }

    public BigDecimal getSinhMang() {
        return SinhMang;
    }

    public void setSinhMang(BigDecimal SinhMang) {
        this.SinhMang = SinhMang;
    }

    public BigDecimal getThaiSan() {
        return ThaiSan;
    }

    public void setThaiSan(BigDecimal ThaiSan) {
        this.ThaiSan = ThaiSan;
    }

    public BigDecimal getBHChinh() {
        return BHChinh;
    }

    public void setBHChinh(BigDecimal BHChinh) {
        this.BHChinh = BHChinh;
    }

    public BigDecimal getTongPhi() {
        return TongPhi;
    }

    public void setTongPhi(BigDecimal TongPhi) {
        this.TongPhi = TongPhi;
    }

    public Timestamp getNgayHieuLuc() {
        return NgayHieuLuc;
    }

    public void setNgayHieuLuc(Timestamp NgayHieuLuc) {
        this.NgayHieuLuc = NgayHieuLuc;
    }

    public Timestamp getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Timestamp NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public Timestamp getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Timestamp NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getHTThanhToan() {
        return HTThanhToan;
    }

    public void setHTThanhToan(String HTThanhToan) {
        this.HTThanhToan = HTThanhToan;
    }

    public Timestamp getNgayTT() {
        return NgayTT;
    }

    public void setNgayTT(Timestamp NgayTT) {
        this.NgayTT = NgayTT;
    }

    public String getMaGDSCB() {
        return MaGDSCB;
    }

    public void setMaGDSCB(String MaGDSCB) {
        this.MaGDSCB = MaGDSCB;
    }

    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int Tuoi) {
        this.Tuoi = Tuoi;
    }

}
