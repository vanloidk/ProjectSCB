/**
 * Loan_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public interface Loan_Service extends javax.xml.rpc.Service {
    public java.lang.String getLoanSOAPAddress();

    public vn.com.scb.bian.Loan_PortType getLoanSOAP() throws javax.xml.rpc.ServiceException;

    public vn.com.scb.bian.Loan_PortType getLoanSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
