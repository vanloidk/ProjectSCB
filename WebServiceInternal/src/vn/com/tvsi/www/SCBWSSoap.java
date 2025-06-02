/**
 * SCBWSSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.tvsi.www;

public interface SCBWSSoap extends java.rmi.Remote {

    /**
     * Chuyển tiền từ TK SCB sang TK TVSI
     */
    public java.lang.String SCBRequest(java.lang.String SCBRef, java.lang.String partnerAccount, java.lang.String customerAccount, java.lang.String customerName, java.math.BigDecimal amount, java.lang.String ccy, java.lang.String channelID, java.lang.String transDate, java.lang.String addInfo, java.lang.String providerID, java.lang.String signatureSCB) throws java.rmi.RemoteException;

    /**
     * Lấy tên khách hàng TVSI từ số tài khoản
     */
    public java.lang.String getCustomerName(java.lang.String soTaiKhoan) throws java.rmi.RemoteException;
}
