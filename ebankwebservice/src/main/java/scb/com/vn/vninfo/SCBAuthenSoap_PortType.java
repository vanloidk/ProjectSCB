/**
 * SCBAuthenSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package scb.com.vn.vninfo;

/**
 *
 * @author minhndb
 */
public interface SCBAuthenSoap_PortType extends java.rmi.Remote {

    /**
     * Validate mật khẩu Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException
     */
    public java.lang.String validatePasswordMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Validate mpin Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException
     */
    public java.lang.String validatePinMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;
}
