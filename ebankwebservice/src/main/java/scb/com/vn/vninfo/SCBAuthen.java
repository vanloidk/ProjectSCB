/**
 * SCBAuthen.java
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
public interface SCBAuthen extends javax.xml.rpc.Service {

    /**
     *
     * @return
     */
    public java.lang.String getSCBAuthenSoap12Address();

    /**
     *
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.SCBAuthenSoap_PortType getSCBAuthenSoap12() throws javax.xml.rpc.ServiceException;

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.SCBAuthenSoap_PortType getSCBAuthenSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;

    /**
     *
     * @return
     */
    public java.lang.String getSCBAuthenSoapAddress();

    /**
     *
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.SCBAuthenSoap_PortType getSCBAuthenSoap() throws javax.xml.rpc.ServiceException;

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.SCBAuthenSoap_PortType getSCBAuthenSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
