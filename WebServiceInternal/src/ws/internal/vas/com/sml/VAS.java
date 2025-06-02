/**
 * VAS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.vas.com.sml;

public interface VAS extends javax.xml.rpc.Service {
    public java.lang.String getVASHttpSoap12EndpointAddress();

    public ws.internal.vas.com.sml.VASPortType getVASHttpSoap12Endpoint() throws javax.xml.rpc.ServiceException;

    public ws.internal.vas.com.sml.VASPortType getVASHttpSoap12Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getVASHttpSoap11EndpointAddress();

    public ws.internal.vas.com.sml.VASPortType getVASHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException;

    public ws.internal.vas.com.sml.VASPortType getVASHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
