/**
 * UnLockFee.java
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
public interface UnLockFee extends javax.xml.rpc.Service {

    /**
     *
     * @return
     */
    public java.lang.String getUnLockFeeSoapAddress();

    /**
     *
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.UnLockFeeSoap getUnLockFeeSoap() throws javax.xml.rpc.ServiceException;

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.UnLockFeeSoap getUnLockFeeSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;

    /**
     *
     * @return
     */
    public java.lang.String getUnLockFeeSoap12Address();

    /**
     *
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.UnLockFeeSoap getUnLockFeeSoap12() throws javax.xml.rpc.ServiceException;

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.UnLockFeeSoap getUnLockFeeSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
