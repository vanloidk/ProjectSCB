/**
 * ConnectToEVN.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.evnit.webservice.service;

public interface ConnectToEVN extends java.rmi.Remote {
    public java.lang.String[][] getBill2(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException;
    public java.lang.String[] dopayment2(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String[][] billDetail(java.lang.String madvql, java.lang.String makh, java.lang.String mahdon) throws java.rmi.RemoteException;
    public java.lang.String[][] searchCutomer(java.lang.String name, java.lang.String addr, java.lang.String makh, java.lang.String sogcs, int max_result) throws java.rmi.RemoteException;
    public java.lang.String[] getBill(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.lang.String arg5) throws java.rmi.RemoteException;
    public java.lang.String[] doPayment(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.lang.String arg5, java.lang.String arg6, java.lang.String arg7, java.lang.String arg8, java.lang.String arg9, java.lang.String arg10, java.lang.String arg11) throws java.rmi.RemoteException;
    public java.lang.String[] revert(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.lang.String arg5) throws java.rmi.RemoteException;
}
