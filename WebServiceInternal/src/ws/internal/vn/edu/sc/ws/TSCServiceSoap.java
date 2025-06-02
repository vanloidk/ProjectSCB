/**
 * TSCServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.vn.edu.sc.ws;

public interface TSCServiceSoap extends java.rmi.Remote {
    public ws.internal.vn.edu.sc.ws.ThongTinPhieuThu layThongTinPhieuThu(java.lang.String tendangnhap, java.lang.String matkhau, java.lang.String strmaphieuthu) throws java.rmi.RemoteException;
    public int capNhatTinhTrangPhieuThu(java.lang.String tendangnhap, java.lang.String matkhau, java.lang.String strmaphieuthu) throws java.rmi.RemoteException;
    public ws.internal.vn.edu.sc.ws.ThongTinChiTietPhieuThu[] layDanhSachChiTietPhieuThu(java.lang.String strmaphieuthu) throws java.rmi.RemoteException;
}
