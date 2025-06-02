/**
 * ReceiveMessgeSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.sms_tnb.org.tempuri;

public interface ReceiveMessgeSoap extends java.rmi.Remote {

    /**
     * Receive SMS
     */
    public java.lang.String messageReceiver(java.lang.String userID, java.lang.String serviceID, java.lang.String message, java.lang.String moID, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
}
