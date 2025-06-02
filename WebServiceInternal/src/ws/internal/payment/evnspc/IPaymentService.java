/**
 * IPaymentService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc.payment.evnspc;

public interface IPaymentService extends java.rmi.Remote {
    public org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC.DonViInfo[] getDonViEVNSPC(java.lang.String maDonVi) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC.CustomerInfo getCustomerInfo(java.lang.String bankID, java.lang.String customerCode) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC.CustomerInfo[] getListCustomerInfo(java.lang.String bankID, java.lang.Integer hoaDonID, java.lang.String customerName, java.lang.String address, java.lang.String EVNSPCID) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC.PaymentInfo[] getPaymentInfos(java.lang.String bankID, java.util.Calendar fromPayDate, java.util.Calendar toPayDate, java.lang.String departCode) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.PayBillsByCustomerCodeResponsePayBillsByCustomerCodeResult payBillsByCustomerCode(java.lang.String bankID, java.lang.String customerCode, java.lang.String[] billCodes, long[] amount, java.util.Calendar payDate, java.lang.String[] transactionCode, java.lang.String kyHieuHoaDon, java.lang.String departCode, java.lang.String daInHD) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.PayBillsByHoaDonIDResponsePayBillsByHoaDonIDResult payBillsByHoaDonID(java.lang.String bankID, java.lang.String maDonViQL, java.lang.String[] HDon_ID, long[] amount, java.util.Calendar payDate, java.lang.String[] transactionCode, java.lang.String kyHieuHoaDon, java.lang.String departCode, java.lang.String daInHD) throws java.rmi.RemoteException;
    public java.lang.Integer cancelBillsByCustomerCode(java.lang.String bankID, java.lang.String customerCode, java.lang.String[] billCodes, long[] amount, java.util.Calendar cancelDate, java.lang.String[] transactionCode) throws java.rmi.RemoteException;
    public java.lang.Integer cancelBillsByHoaDonID(java.lang.String bankID, java.lang.String maDonViQL, java.lang.String[] HDon_ID, long[] amount, java.util.Calendar cancelDate, java.lang.String[] transactionCode) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.GetHanMucTienThuResponseGetHanMucTienThuResult getHanMucTienThu(java.lang.String bankID, java.lang.String maDonViQL) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.CheckBillResponseCheckBillResult checkBill(java.lang.String customerCode, java.lang.Integer hoaDonID, java.lang.String bankID) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.GetBillByBankResponseGetBillByBankResult getBillByBank(java.lang.String bankID, java.lang.String maDonViQL, java.lang.String tuNgay, java.lang.String denNgay, java.lang.String loaiGiaoDich) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.GetPhieuThuTienDienResponseGetPhieuThuTienDienResult getPhieuThuTienDien(java.lang.String bankID, java.lang.String maGiaoDich, java.lang.Integer hoaDonID, java.lang.Long soTien) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.GetPhieuThuTienCSPKResponseGetPhieuThuTienCSPKResult getPhieuThuTienCSPK(java.lang.String bankID, java.lang.String maGiaoDich, java.lang.Integer hoaDonID, java.lang.Long soTien) throws java.rmi.RemoteException;
}
