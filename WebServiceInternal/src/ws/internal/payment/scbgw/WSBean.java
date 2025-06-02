/**
 * WSBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.scbgw;

public interface WSBean extends java.rmi.Remote {
    public java.lang.String callExecutionSML(java.lang.String request) throws java.rmi.RemoteException;
    public java.lang.Object[] callExecution(java.lang.String methodname, java.lang.String partnercode, java.lang.Object[] arrObjParas) throws java.rmi.RemoteException;
    public java.lang.String getString(java.lang.String str) throws java.rmi.RemoteException;
}
