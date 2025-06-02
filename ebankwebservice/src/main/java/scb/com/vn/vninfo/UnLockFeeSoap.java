/**
 * UnLockFeeSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package scb.com.vn.vninfo;

/**
 *
 * @author minhndb
 */
public interface UnLockFeeSoap extends java.rmi.Remote {

    /**
     * Khóa dịch vụ Mobile Banking từ hệ thống thu phí của SCB
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException
     */
    public java.lang.String lockFeeMobileBankingOfSCB(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Mở Khóa dịch vụ Mobile Banking từ hệ thống thu phí của SCB
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException
     */
    public java.lang.String unLockFeeMobileBankingOfSCB(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;
}
