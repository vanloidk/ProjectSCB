/**
 * TTDTPortalSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package org.tempuri;

import java.rmi.RemoteException;

/**
 *
 * @author minhndb
 */
public interface TTDTPortalSoap extends java.rmi.Remote {

    /**
     *
     * @param publicKey
     * @param msg
     * @return
     * @throws RemoteException
     */
    public java.lang.String WSProcess(java.lang.String publicKey, java.lang.String msg) throws java.rmi.RemoteException;

    /**
     *
     * @return
     * @throws RemoteException
     */
    public java.lang.String getPublicKey() throws java.rmi.RemoteException;
}
