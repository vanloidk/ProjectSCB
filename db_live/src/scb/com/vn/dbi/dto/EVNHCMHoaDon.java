/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto;

/**
 *
 * @author minhndb
 */
public class EVNHCMHoaDon implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private final String lineSeparator = System.getProperty("line.separator");
    private String maHD;
    private String moTa;
    private String tuNgay;
    private String denNgay;
    private String giaBieu;
    private String hoaDonID;
    private String soHo;
    private String DNTT;
    private String tienDien;
    private String tienThue;
    private String soTien;
    private String thueSuat;
    private String HDDT;
    private String kyHieuDH;
    private String seryHD;

    /*--- Phan tach tu maHD  ---*/
    private String nam;
    private String ky;
    private String dotHoaDon;
    private String loaiHoaDon;

    /**
     *
     * @return
     */
    public String getMaHD() {
        return maHD;
    }

    /**
     *
     * @return
     */
    public String getMoTa() {
        return moTa;
    }

    /**
     *
     * @return
     */
    public String getTuNgay() {
        return tuNgay;
    }

    /**
     *
     * @return
     */
    public String getDenNgay() {
        return denNgay;
    }

    /**
     *
     * @return
     */
    public String getGiaBieu() {
        return giaBieu;
    }

    /**
     *
     * @return
     */
    public String getHoaDonID() {
        return hoaDonID;
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
     * @return
     */
    public String getDNTT() {
        return DNTT;
    }

    /**
     *
     * @return
     */
    public String getTienDien() {
        return tienDien;
    }

    /**
     *
     * @return
     */
    public String getTienThue() {
        return tienThue;
    }

    /**
     *
     * @return
     */
    public String getSoTien() {
        return soTien;
    }

    /**
     *
     * @return
     */
    public String getThueSuat() {
        return thueSuat;
    }

    /**
     *
     * @return
     */
    public String getHDDT() {
        return HDDT;
    }

    /**
     *
     * @return
     */
    public String getKyHieuDH() {
        return kyHieuDH;
    }

    /**
     *
     * @return
     */
    public String getSeryHD() {
        return seryHD;
    }

    /**
     *
     * @param maHD
     */
    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    /**
     *
     * @param moTa
     */
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    /**
     *
     * @param tuNgay
     */
    public void setTuNgay(String tuNgay) {
        this.tuNgay = tuNgay;
    }

    /**
     *
     * @param denNgay
     */
    public void setDenNgay(String denNgay) {
        this.denNgay = denNgay;
    }

    /**
     *
     * @param giaBieu
     */
    public void setGiaBieu(String giaBieu) {
        this.giaBieu = giaBieu;
    }

    /**
     *
     * @param hoaDonID
     */
    public void setHoaDonID(String hoaDonID) {
        this.hoaDonID = hoaDonID;
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
     * @param DNTT
     */
    public void setDNTT(String DNTT) {
        this.DNTT = DNTT;
    }

    /**
     *
     * @param tienDien
     */
    public void setTienDien(String tienDien) {
        this.tienDien = tienDien;
    }

    /**
     *
     * @param tienThue
     */
    public void setTienThue(String tienThue) {
        this.tienThue = tienThue;
    }

    /**
     *
     * @param soTien
     */
    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    /**
     *
     * @param thueSuat
     */
    public void setThueSuat(String thueSuat) {
        this.thueSuat = thueSuat;
    }

    /**
     *
     * @param HDDT
     */
    public void setHDDT(String HDDT) {
        this.HDDT = HDDT;
    }

    /**
     *
     * @param kyHieuDH
     */
    public void setKyHieuDH(String kyHieuDH) {
        this.kyHieuDH = kyHieuDH;
    }

    /**
     *
     * @param seryHD
     */
    public void setSeryHD(String seryHD) {
        this.seryHD = seryHD;
    }

    /**
     *
     * @return
     */
    public String getNam() {
        return nam;
    }

    /**
     *
     * @return
     */
    public String getKy() {
        return ky;
    }

    /**
     *
     * @return
     */
    public String getDotHoaDon() {
        return dotHoaDon;
    }

    /**
     *
     * @return
     */
    public String getLoaiHoaDon() {
        return loaiHoaDon;
    }

    /**
     * format data
     */
    public void formatData() {
        this.maHD = formatData(this.maHD);
        this.moTa = formatData(this.moTa);
        this.tuNgay = formatData(this.tuNgay);
        this.denNgay = formatData(this.denNgay);
        this.giaBieu = formatData(this.giaBieu);
        this.hoaDonID = formatData(this.hoaDonID);
        this.soHo = formatData(this.soHo);
        this.DNTT = formatData(this.DNTT);
        this.tienDien = formatData(this.tienDien);
        this.tienThue = formatData(this.tienThue);
        this.soTien = formatData(this.soTien);
        this.thueSuat = formatData(this.thueSuat);
        this.HDDT = formatData(this.HDDT);
        this.kyHieuDH = formatData(this.kyHieuDH);
        this.seryHD = formatData(this.seryHD);
        AnalysisMaHD();
    }

    private String formatData(String data) {
        return data != null ? data.trim() : data;
    }

    private void AnalysisMaHD() {
        // 090710
        if (maHD.length() == 6) {
            this.nam = maHD.substring(0, 2);
            this.ky = maHD.substring(2, 4);
            this.dotHoaDon = maHD.substring(4, 5);
            this.loaiHoaDon = maHD.substring(5, 6);
        }
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder();

        print.append("maHD = [").append(maHD).append("]").append(lineSeparator);
        print.append("moTa = [").append(moTa).append("]").append(lineSeparator);
        print.append("tuNgay = [").append(tuNgay).append("]").append(lineSeparator);
        print.append("denNgay = [").append(denNgay).append("]").append(lineSeparator);
        print.append("giaBieu = [").append(giaBieu).append("]").append(lineSeparator);
        print.append("hoaDonID = [").append(hoaDonID).append("]").append(lineSeparator);
        print.append("soHo = [").append(soHo).append("]").append(lineSeparator);
        print.append("DNTT = [").append(DNTT).append("]").append(lineSeparator);
        print.append("tienDien = [").append(tienDien).append("]").append(lineSeparator);
        print.append("tienThue = [").append(tienThue).append("]").append(lineSeparator);
        print.append("soTien = [").append(soTien).append("]").append(lineSeparator);
        print.append("thueSuat = [").append(thueSuat).append("]").append(lineSeparator);
        print.append("HDDT = [").append(HDDT).append("]").append(lineSeparator);
        print.append("kyHieuDH = [").append(kyHieuDH).append("]").append(lineSeparator);
        print.append("seryHD = [").append(seryHD).append("]").append(lineSeparator);
        print.append("nam = [").append(nam).append("]").append(lineSeparator);
        print.append("ky = [").append(ky).append("]").append(lineSeparator);
        print.append("dotHoaDon = [").append(dotHoaDon).append("]").append(lineSeparator);
        print.append("loaiHoaDon = [").append(loaiHoaDon).append("]").append(lineSeparator);

        return print.toString();
    }
}
