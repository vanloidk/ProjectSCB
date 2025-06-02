/**
 * EVNCPCProxySoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evncpc;

public interface EVNCPCProxySoap_PortType extends java.rmi.Remote {
    public java.lang.String helloWorld() throws java.rmi.RemoteException;
    public java.lang.String bankRequest(java.lang.String strMaKH, java.lang.String strMaDonvi, int strBankID, java.lang.String strPassconn) throws java.rmi.RemoteException;
    public java.lang.String bankConfirm(java.lang.String strMaKH, java.lang.String strAllNoCuoc, java.lang.String strDatetime, java.lang.String strMaCN, java.lang.String strKieuthu, int intBankID, java.lang.String strPassconn) throws java.rmi.RemoteException;
}
