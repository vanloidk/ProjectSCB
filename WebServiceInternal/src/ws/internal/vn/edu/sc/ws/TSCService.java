/**
 * TSCService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.vn.edu.sc.ws;

public interface TSCService extends javax.xml.rpc.Service {
    public java.lang.String getTSCServiceSoap12Address();

    public ws.internal.vn.edu.sc.ws.TSCServiceSoap getTSCServiceSoap12() throws javax.xml.rpc.ServiceException;

    public ws.internal.vn.edu.sc.ws.TSCServiceSoap getTSCServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getTSCServiceSoapAddress();

    public ws.internal.vn.edu.sc.ws.TSCServiceSoap getTSCServiceSoap() throws javax.xml.rpc.ServiceException;

    public ws.internal.vn.edu.sc.ws.TSCServiceSoap getTSCServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
