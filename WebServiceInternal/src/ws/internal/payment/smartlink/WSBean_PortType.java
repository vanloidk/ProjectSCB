/**
 * WSBean_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.smartlink;

public interface WSBean_PortType extends java.rmi.Remote {
    public java.lang.String getString(java.lang.String str) throws java.rmi.RemoteException;
    public java.lang.String requestDownloadFileFromPartner(java.lang.String filename) throws java.rmi.RemoteException;
    public java.lang.String requestUploadFileToPartner(java.lang.String filename) throws java.rmi.RemoteException;
    public java.lang.String callwsSmartlink(java.lang.String param) throws java.rmi.RemoteException;
    public java.lang.String callExecution(java.lang.String request) throws java.rmi.RemoteException;
}
