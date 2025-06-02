/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author minhndb
 */
public class EVNHCM implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private final String SEPE1 = "@";
    private final String SEPE2 = "%";
    private final String SEPE3 = "#";

    private String maKH;
    private String tenKH;
    private String diaChiKH;
    private String maDL;
    private String maSoThueKH;
    private String phien;
    private String loTrinh;
    private String soGhiCSCMIS;
    private String danhSo;
    private String soCongTo;
    private String soHo;
    private String nganhNghe;
    private String tyLeGia;
    private String dienThoai;

    private String hoaDonSize;
    private String totalAmount;

    private List<EVNHCMHoaDon> hoadonEVN = new ArrayList<>();

    private String ngay;
    private String maHinhThuc;

    /**
     * Create a new instance of EVNHCM
     */
    public EVNHCM() {
    }

    /**
     *
     * @param data
     */
    public EVNHCM(String data) {
        initData(data);
    }

    /**
     *
     * @param data
     * @param ngay
     * @param maHinhThuc
     */
    public EVNHCM(String data, String ngay, String maHinhThuc) {
        initData(data);
        this.ngay = ngay;
        this.maHinhThuc = maHinhThuc;
    }

    private void initData(String data) {
        if (data != null && !data.isEmpty()) {
            String[] datas = data.split(SEPE1);
            if (datas.length == 17) {
                initKH(datas);
                initHD(datas[16]);
            }

        }
    }

    private void initHD(String hoadons) {
        if (hoadons != null && !hoadons.isEmpty()) {
            String[] hoadon = hoadons.split(SEPE2);
            if (hoadon.length > 0) {
                for (String hd : hoadon) {
                    if (hd != null && !hd.isEmpty()) {
                        String[] hddt = hd.split(SEPE3);
                        if (hddt.length == 19) {
                            hoadonEVN.add(initHDDetails(hddt));
                        }
                    }
                }
            }
        }
    }

    private EVNHCMHoaDon initHDDetails(String[] hddt) {
        EVNHCMHoaDon hoadondetail = new EVNHCMHoaDon();

        hoadondetail.setMaHD(hddt[0]);
        hoadondetail.setMoTa(hddt[1]);
        hoadondetail.setTuNgay(hddt[2]);
        hoadondetail.setDenNgay(hddt[3]);
        hoadondetail.setGiaBieu(hddt[4]);
        hoadondetail.setHoaDonID(hddt[5]);
        hoadondetail.setDNTT(hddt[7]);
        hoadondetail.setTienDien(hddt[8]);
        hoadondetail.setTienThue(hddt[9]);
        hoadondetail.setSoTien(hddt[10]);
        hoadondetail.setThueSuat(hddt[11]);
        hoadondetail.setHDDT(hddt[12]);
        hoadondetail.setKyHieuDH(hddt[13]);
        hoadondetail.setSeryHD(hddt[14]);

        hoadondetail.formatData();

        return hoadondetail;
    }

    private void initKH(String[] kh) {
        maKH = kh[0];
        tenKH = kh[1];
        diaChiKH = kh[2];
        maDL = kh[3];
        maSoThueKH = kh[4];
        phien = kh[5];
        loTrinh = kh[6];
        soGhiCSCMIS = kh[7];
        danhSo = kh[8];
        soCongTo = kh[9];
        soHo = kh[10];
        nganhNghe = kh[11];
        tyLeGia = kh[12];
        dienThoai = kh[13];
        hoaDonSize = kh[14];
        totalAmount = kh[15];
    }

    /**
     *
     * @return
     */
    public String getNgay() {
        return ngay;
    }

    /**
     *
     * @param ngay
     */
    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    /**
     *
     * @return
     */
    public String getMaHinhThuc() {
        return maHinhThuc;
    }

    /**
     *
     * @param maHinhThuc
     */
    public void setMaHinhThuc(String maHinhThuc) {
        this.maHinhThuc = maHinhThuc;
    }

    /**
     *
     * @return
     */
    public String getMaKH() {
        return maKH;
    }

    /**
     *
     * @param maKH
     */
    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    /**
     *
     * @return
     */
    public String getTenKH() {
        return tenKH;
    }

    /**
     *
     * @param tenKH
     */
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    /**
     *
     * @return
     */
    public String getDiaChiKH() {
        return diaChiKH;
    }

    /**
     *
     * @param diaChiKH
     */
    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }

    /**
     *
     * @return
     */
    public String getMaDL() {
        return maDL;
    }

    /**
     *
     * @param maDL
     */
    public void setMaDL(String maDL) {
        this.maDL = maDL;
    }

    /**
     *
     * @return
     */
    public String getMaSoThueKH() {
        return maSoThueKH;
    }

    /**
     *
     * @param maSoThueKH
     */
    public void setMaSoThueKH(String maSoThueKH) {
        this.maSoThueKH = maSoThueKH;
    }

    /**
     *
     * @return
     */
    public String getPhien() {
        return phien;
    }

    /**
     *
     * @param phien
     */
    public void setPhien(String phien) {
        this.phien = phien;
    }

    /**
     *
     * @return
     */
    public String getLoTrinh() {
        return loTrinh;
    }

    /**
     *
     * @param loTrinh
     */
    public void setLoTrinh(String loTrinh) {
        this.loTrinh = loTrinh;
    }

    /**
     *
     * @return
     */
    public String getSoGhiCSCMIS() {
        return soGhiCSCMIS;
    }

    /**
     *
     * @param soGhiCSCMIS
     */
    public void setSoGhiCSCMIS(String soGhiCSCMIS) {
        this.soGhiCSCMIS = soGhiCSCMIS;
    }

    /**
     *
     * @return
     */
    public String getDanhSo() {
        return danhSo;
    }

    /**
     *
     * @param danhSo
     */
    public void setDanhSo(String danhSo) {
        this.danhSo = danhSo;
    }

    /**
     *
     * @return
     */
    public String getSoCongTo() {
        return soCongTo;
    }

    /**
     *
     * @param soCongTo
     */
    public void setSoCongTo(String soCongTo) {
        this.soCongTo = soCongTo;
    }

    /**
     *
     * @return
     */
    public String getSoHo() {
        return soHo;
    }

    /**
     *
     * @param soHo
     */
    public void setSoHo(String soHo) {
        this.soHo = soHo;
    }

    /**
     *
     * @return
     */
    public String getNganhNghe() {
        return nganhNghe;
    }

    /**
     *
     * @param nganhNghe
     */
    public void setNganhNghe(String nganhNghe) {
        this.nganhNghe = nganhNghe;
    }

    /**
     *
     * @return
     */
    public String getTyLeGia() {
        return tyLeGia;
    }

    /**
     *
     * @param tyLeGia
     */
    public void setTyLeGia(String tyLeGia) {
        this.tyLeGia = tyLeGia;
    }

    /**
     *
     * @return
     */
    public String getDienThoai() {
        return dienThoai;
    }

    /**
     *
     * @param dienThoai
     */
    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    /**
     *
     * @return
     */
    public String getHoaDonSize() {
        return hoaDonSize;
    }

    /**
     *
     * @param hoaDonSize
     */
    public void setHoaDonSize(String hoaDonSize) {
        this.hoaDonSize = hoaDonSize;
    }

    /**
     *
     * @return
     */
    public String getTotalAmount() {
        return totalAmount;
    }

    /**
     *
     * @param totalAmount
     */
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     *
     * @return
     */
    public List<EVNHCMHoaDon> getHoadonEVN() {
        return hoadonEVN;
    }

    /**
     *
     * @param hoadonEVN
     */
    public void setHoadonEVN(List<EVNHCMHoaDon> hoadonEVN) {
        this.hoadonEVN = hoadonEVN;
    }

}
