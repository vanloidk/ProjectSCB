/**
 * UniGWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.payoo;

public interface UniGWS extends javax.xml.rpc.Service {
    public java.lang.String getUniGWSSoap12Address();

    public ws.internal.payment.payoo.UniGWSSoap_PortType getUniGWSSoap12() throws javax.xml.rpc.ServiceException;

    public ws.internal.payment.payoo.UniGWSSoap_PortType getUniGWSSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getUniGWSSoapAddress();

    public ws.internal.payment.payoo.UniGWSSoap_PortType getUniGWSSoap() throws javax.xml.rpc.ServiceException;

    public ws.internal.payment.payoo.UniGWSSoap_PortType getUniGWSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
