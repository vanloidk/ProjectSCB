/**
 * EVNCPCProxy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evncpc;

public interface EVNCPCProxy extends javax.xml.rpc.Service {
    public java.lang.String getEVNCPCProxySoap12Address();

    public ws.internal.payment.evncpc.EVNCPCProxySoap_PortType getEVNCPCProxySoap12() throws javax.xml.rpc.ServiceException;

    public ws.internal.payment.evncpc.EVNCPCProxySoap_PortType getEVNCPCProxySoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getEVNCPCProxySoapAddress();

    public ws.internal.payment.evncpc.EVNCPCProxySoap_PortType getEVNCPCProxySoap() throws javax.xml.rpc.ServiceException;

    public ws.internal.payment.evncpc.EVNCPCProxySoap_PortType getEVNCPCProxySoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
