/**
 * GoodsPaygateLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.retail.vtc;

public class GoodsPaygateLocator extends org.apache.axis.client.Service implements ws.internal.retail.vtc.GoodsPaygate {

    public GoodsPaygateLocator() {
    }


    public GoodsPaygateLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GoodsPaygateLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GoodsPaygateSoap12
    private java.lang.String GoodsPaygateSoap12_address = "http://192.168.112.36:8080/vtcgoods/GoodsPaygate.asmx";

    public java.lang.String getGoodsPaygateSoap12Address() {
        return GoodsPaygateSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GoodsPaygateSoap12WSDDServiceName = "GoodsPaygateSoap12";

    public java.lang.String getGoodsPaygateSoap12WSDDServiceName() {
        return GoodsPaygateSoap12WSDDServiceName;
    }

    public void setGoodsPaygateSoap12WSDDServiceName(java.lang.String name) {
        GoodsPaygateSoap12WSDDServiceName = name;
    }

    public ws.internal.retail.vtc.GoodsPaygateSoap getGoodsPaygateSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GoodsPaygateSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGoodsPaygateSoap12(endpoint);
    }

    public ws.internal.retail.vtc.GoodsPaygateSoap getGoodsPaygateSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.retail.vtc.GoodsPaygateSoap12Stub _stub = new ws.internal.retail.vtc.GoodsPaygateSoap12Stub(portAddress, this);
            _stub.setPortName(getGoodsPaygateSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGoodsPaygateSoap12EndpointAddress(java.lang.String address) {
        GoodsPaygateSoap12_address = address;
    }


    // Use to get a proxy class for GoodsPaygateSoap
    private java.lang.String GoodsPaygateSoap_address = "http://192.168.112.36:8080/vtcgoods/GoodsPaygate.asmx";

    public java.lang.String getGoodsPaygateSoapAddress() {
        return GoodsPaygateSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GoodsPaygateSoapWSDDServiceName = "GoodsPaygateSoap";

    public java.lang.String getGoodsPaygateSoapWSDDServiceName() {
        return GoodsPaygateSoapWSDDServiceName;
    }

    public void setGoodsPaygateSoapWSDDServiceName(java.lang.String name) {
        GoodsPaygateSoapWSDDServiceName = name;
    }

    public ws.internal.retail.vtc.GoodsPaygateSoap getGoodsPaygateSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GoodsPaygateSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGoodsPaygateSoap(endpoint);
    }

    public ws.internal.retail.vtc.GoodsPaygateSoap getGoodsPaygateSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.retail.vtc.GoodsPaygateSoapStub _stub = new ws.internal.retail.vtc.GoodsPaygateSoapStub(portAddress, this);
            _stub.setPortName(getGoodsPaygateSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGoodsPaygateSoapEndpointAddress(java.lang.String address) {
        GoodsPaygateSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ws.internal.retail.vtc.GoodsPaygateSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.retail.vtc.GoodsPaygateSoap12Stub _stub = new ws.internal.retail.vtc.GoodsPaygateSoap12Stub(new java.net.URL(GoodsPaygateSoap12_address), this);
                _stub.setPortName(getGoodsPaygateSoap12WSDDServiceName());
                return _stub;
            }
            if (ws.internal.retail.vtc.GoodsPaygateSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.retail.vtc.GoodsPaygateSoapStub _stub = new ws.internal.retail.vtc.GoodsPaygateSoapStub(new java.net.URL(GoodsPaygateSoap_address), this);
                _stub.setPortName(getGoodsPaygateSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("GoodsPaygateSoap12".equals(inputPortName)) {
            return getGoodsPaygateSoap12();
        }
        else if ("GoodsPaygateSoap".equals(inputPortName)) {
            return getGoodsPaygateSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "GoodsPaygate");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "GoodsPaygateSoap12"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "GoodsPaygateSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GoodsPaygateSoap12".equals(portName)) {
            setGoodsPaygateSoap12EndpointAddress(address);
        }
        else 
if ("GoodsPaygateSoap".equals(portName)) {
            setGoodsPaygateSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
