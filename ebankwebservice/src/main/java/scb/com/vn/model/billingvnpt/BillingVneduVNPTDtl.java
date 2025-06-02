/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model.billingvnpt;


public class BillingVneduVNPTDtl {

    private hocSinhInfo hoc_sinh_info;
    private thanhToan thanh_toan;

    public hocSinhInfo getHoc_sinh_info() {
        return hoc_sinh_info;
    }

    public void setHoc_sinh_info(hocSinhInfo hoc_sinh_info) {
        this.hoc_sinh_info = hoc_sinh_info;
    }

    public thanhToan getThanh_toan() {
        return thanh_toan;
    }

    public void setThanh_toan(thanhToan thanh_toan) {
        this.thanh_toan = thanh_toan;
    }

    //session 01
    public class hocSinhInfo {

        private String ten_hoc_sinh;
        private String ten_lop;
        private String ten_truong;
        private String truong_id;
        private String tinh_id;

        public String getTen_hoc_sinh() {
            return ten_hoc_sinh;
        }

        public void setTen_hoc_sinh(String ten_hoc_sinh) {
            this.ten_hoc_sinh = ten_hoc_sinh;
        }

        public String getTen_lop() {
            return ten_lop;
        }

        public void setTen_lop(String ten_lop) {
            this.ten_lop = ten_lop;
        }

        public String getTen_truong() {
            return ten_truong;
        }

        public void setTen_truong(String ten_truong) {
            this.ten_truong = ten_truong;
        }

        public String getTruong_id() {
            return truong_id;
        }

        public void setTruong_id(String truong_id) {
            this.truong_id = truong_id;
        }

        public String getTinh_id() {
            return tinh_id;
        }

        public void setTinh_id(String tinh_id) {
            this.tinh_id = tinh_id;
        }
    }

    //session 02
    public class thanhToan {

        private String id;
        private String tong_tien;
        private String noi_dung;
        private String ten_thanh_toan;
        private String chi_tiet;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTong_tien() {
            return tong_tien;
        }

        public void setTong_tien(String tong_tien) {
            this.tong_tien = tong_tien;
        }

        public String getNoi_dung() {
            return noi_dung;
        }

        public void setNoi_dung(String noi_dung) {
            this.noi_dung = noi_dung;
        }

        public String getTen_thanh_toan() {
            return ten_thanh_toan;
        }

        public void setTen_thanh_toan(String ten_thanh_toan) {
            this.ten_thanh_toan = ten_thanh_toan;
        }

        public String getChi_tiet() {
            return chi_tiet;
        }

        public void setChi_tiet(String chi_tiet) {
            this.chi_tiet = chi_tiet;
        }
    }
}
