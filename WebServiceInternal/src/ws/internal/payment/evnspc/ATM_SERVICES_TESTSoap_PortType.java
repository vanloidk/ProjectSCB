/**
 * ATM_SERVICES_TESTSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public interface ATM_SERVICES_TESTSoap_PortType extends java.rmi.Remote {
    public org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC.CustomerInfo getCustomerInfo(int bankID, int serviceType, java.lang.String customerCode) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.Get_DS_CustomerInfoResponseGet_DS_CustomerInfoResult get_DS_CustomerInfo(java.lang.String bankID, int serviceType, java.lang.String customerCode, java.lang.String customerName, java.lang.String address, java.lang.String EVNSPCID) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.PayBillsResponsePayBillsResult payBills(java.lang.String bankID, int serviceType, java.lang.String customerCode, java.lang.String[] billCodes, int[] amount, java.util.Calendar payDate, java.lang.String[] transactionCode, java.lang.String kyHieuHoaDon, java.lang.String departCode, java.lang.String daInHD) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.GetDonViQuanLyResponseGetDonViQuanLyResult getDonViQuanLy() throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.PayBills_HDon_IDResponsePayBills_HDon_IDResult payBills_HDon_ID(java.lang.String bankID, int serviceType, java.lang.String customerCode, java.lang.String[] HDon_ID, int[] amount, java.util.Calendar payDate, java.lang.String[] transactionCode, java.lang.String kyHieuHoaDon, java.lang.String departCode, java.lang.String daInHD) throws java.rmi.RemoteException;
    public int cancelBills(java.lang.String bankID, int serviceType, java.lang.String customerCode, java.lang.String[] billCodes, int[] amount, java.util.Calendar cancelDate, java.lang.String[] transactionCode) throws java.rmi.RemoteException;
    public int cancelBills_HDon_ID(java.lang.String bankID, int serviceType, java.lang.String customerCode, java.lang.String[] HDon_ID, int[] amount, java.util.Calendar cancelDate, java.lang.String[] transactionCode) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.PaymentInfo[] getPaymentInfos(int bankID, int serviceType, java.util.Calendar fromPayDate, java.util.Calendar toPayDate, java.lang.String departCode2) throws java.rmi.RemoteException;
    public void testConnection() throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.CheckBillResponseCheckBillResult checkBill(java.lang.String ma_dviqly, int hoadon_id) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.CheckBill_BankidResponseCheckBill_BankidResult checkBill_Bankid(java.lang.String ma_khang, int hoadon_id, java.lang.String bank_id) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.Check_Status_BillsResponseCheck_Status_BillsResult check_Status_Bills(java.lang.String bankid, java.lang.String ma_dviqly, int[] hoadon_id) throws java.rmi.RemoteException;
    public ws.internal.payment.evnspc.Check_Status_PayBillsResponseCheck_Status_PayBillsResult check_Status_PayBills(java.lang.String bankid, java.lang.String tu_ngay, java.lang.String den_ngay) throws java.rmi.RemoteException;
}
