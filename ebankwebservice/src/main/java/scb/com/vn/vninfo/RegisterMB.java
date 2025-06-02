/**
 * RegisterMB.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package scb.com.vn.vninfo;

import javax.xml.rpc.ServiceException;

/**
 *
 * @author minhndb
 */
public interface RegisterMB extends javax.xml.rpc.Service {

    /**
     *
     * @return
     */
    public java.lang.String getRegisterMBSoap12Address();

    /**
     *
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.RegisterMBSoap getRegisterMBSoap12() throws javax.xml.rpc.ServiceException;

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.RegisterMBSoap getRegisterMBSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;

    /**
     *
     * @return
     */
    public java.lang.String getRegisterMBSoapAddress();

    /**
     *
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.RegisterMBSoap getRegisterMBSoap() throws javax.xml.rpc.ServiceException;

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.RegisterMBSoap getRegisterMBSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
