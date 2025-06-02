/**
 * WSBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.vnpay.navigation;

public interface WSBean extends java.rmi.Remote {
    public java.lang.String getString(java.lang.String str) throws java.rmi.RemoteException;
    public java.lang.String requestTransaction(java.lang.String requestTrans) throws java.rmi.RemoteException;
    public java.lang.String requestUploadFileToVNPAY(java.lang.String filename) throws java.rmi.RemoteException;
    public java.lang.String requestDownloadFileToVNPAY(java.lang.String filename) throws java.rmi.RemoteException;
}
