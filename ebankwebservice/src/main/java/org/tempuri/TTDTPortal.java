/**
 * TTDTPortal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package org.tempuri;

import javax.xml.rpc.ServiceException;

/**
 *
 * @author minhndb
 */
public interface TTDTPortal extends javax.xml.rpc.Service {

    /**
     *
     * @return
     */
    public java.lang.String getTTDTPortalSoapAddress();

    /**
     *
     * @return
     * @throws ServiceException
     */
    public org.tempuri.TTDTPortalSoap getTTDTPortalSoap() throws javax.xml.rpc.ServiceException;

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public org.tempuri.TTDTPortalSoap getTTDTPortalSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;

    /**
     *
     * @return
     */
    public java.lang.String getTTDTPortalSoap12Address();

    /**
     *
     * @return
     * @throws ServiceException
     */
    public org.tempuri.TTDTPortalSoap getTTDTPortalSoap12() throws javax.xml.rpc.ServiceException;

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public org.tempuri.TTDTPortalSoap getTTDTPortalSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
