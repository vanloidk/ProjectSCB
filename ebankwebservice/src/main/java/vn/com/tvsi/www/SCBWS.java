/**
 * SCBWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package vn.com.tvsi.www;

import javax.xml.rpc.ServiceException;

/**
 *
 * @author minhndb
 */
public interface SCBWS extends javax.xml.rpc.Service {

    /**
     *
     * @return
     */
    public java.lang.String getSCBWSSoap12Address();

    /**
     *
     * @return
     * @throws ServiceException
     */
    public vn.com.tvsi.www.SCBWSSoap getSCBWSSoap12() throws javax.xml.rpc.ServiceException;

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public vn.com.tvsi.www.SCBWSSoap getSCBWSSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;

    /**
     *
     * @return
     */
    public java.lang.String getSCBWSSoapAddress();

    /**
     *
     * @return
     * @throws ServiceException
     */
    public vn.com.tvsi.www.SCBWSSoap getSCBWSSoap() throws javax.xml.rpc.ServiceException;

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public vn.com.tvsi.www.SCBWSSoap getSCBWSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
