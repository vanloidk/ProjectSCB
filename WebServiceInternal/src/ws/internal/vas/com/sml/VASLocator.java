/**
 * VASLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.vas.com.sml;

public class VASLocator extends org.apache.axis.client.Service implements ws.internal.vas.com.sml.VAS {

    public VASLocator() {
    }


    public VASLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public VASLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for VASHttpSoap12Endpoint
    private java.lang.String VASHttpSoap12Endpoint_address = "http://10.255.252.99:7878/VAS/services/VAS.VASHttpSoap12Endpoint/";

    public java.lang.String getVASHttpSoap12EndpointAddress() {
        return VASHttpSoap12Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String VASHttpSoap12EndpointWSDDServiceName = "VASHttpSoap12Endpoint";

    public java.lang.String getVASHttpSoap12EndpointWSDDServiceName() {
        return VASHttpSoap12EndpointWSDDServiceName;
    }

    public void setVASHttpSoap12EndpointWSDDServiceName(java.lang.String name) {
        VASHttpSoap12EndpointWSDDServiceName = name;
    }

    public ws.internal.vas.com.sml.VASPortType getVASHttpSoap12Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(VASHttpSoap12Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getVASHttpSoap12Endpoint(endpoint);
    }

    public ws.internal.vas.com.sml.VASPortType getVASHttpSoap12Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.vas.com.sml.VASSoap12BindingStub _stub = new ws.internal.vas.com.sml.VASSoap12BindingStub(portAddress, this);
            _stub.setPortName(getVASHttpSoap12EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setVASHttpSoap12EndpointEndpointAddress(java.lang.String address) {
        VASHttpSoap12Endpoint_address = address;
    }


    // Use to get a proxy class for VASHttpSoap11Endpoint
    private java.lang.String VASHttpSoap11Endpoint_address = "http://10.255.252.99:7878/VAS/services/VAS.VASHttpSoap11Endpoint/";

    public java.lang.String getVASHttpSoap11EndpointAddress() {
        return VASHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String VASHttpSoap11EndpointWSDDServiceName = "VASHttpSoap11Endpoint";

    public java.lang.String getVASHttpSoap11EndpointWSDDServiceName() {
        return VASHttpSoap11EndpointWSDDServiceName;
    }

    public void setVASHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        VASHttpSoap11EndpointWSDDServiceName = name;
    }

    public ws.internal.vas.com.sml.VASPortType getVASHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(VASHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getVASHttpSoap11Endpoint(endpoint);
    }

    public ws.internal.vas.com.sml.VASPortType getVASHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.vas.com.sml.VASSoap11BindingStub _stub = new ws.internal.vas.com.sml.VASSoap11BindingStub(portAddress, this);
            _stub.setPortName(getVASHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setVASHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        VASHttpSoap11Endpoint_address = address;
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
            if (ws.internal.vas.com.sml.VASPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.vas.com.sml.VASSoap12BindingStub _stub = new ws.internal.vas.com.sml.VASSoap12BindingStub(new java.net.URL(VASHttpSoap12Endpoint_address), this);
                _stub.setPortName(getVASHttpSoap12EndpointWSDDServiceName());
                return _stub;
            }
            if (ws.internal.vas.com.sml.VASPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.vas.com.sml.VASSoap11BindingStub _stub = new ws.internal.vas.com.sml.VASSoap11BindingStub(new java.net.URL(VASHttpSoap11Endpoint_address), this);
                _stub.setPortName(getVASHttpSoap11EndpointWSDDServiceName());
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
        if ("VASHttpSoap12Endpoint".equals(inputPortName)) {
            return getVASHttpSoap12Endpoint();
        }
        else if ("VASHttpSoap11Endpoint".equals(inputPortName)) {
            return getVASHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sml.com.vn/", "VAS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sml.com.vn/", "VASHttpSoap12Endpoint"));
            ports.add(new javax.xml.namespace.QName("http://sml.com.vn/", "VASHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("VASHttpSoap12Endpoint".equals(portName)) {
            setVASHttpSoap12EndpointEndpointAddress(address);
        }
        else 
if ("VASHttpSoap11Endpoint".equals(portName)) {
            setVASHttpSoap11EndpointEndpointAddress(address);
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
