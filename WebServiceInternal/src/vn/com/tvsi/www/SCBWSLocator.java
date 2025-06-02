/**
 * SCBWSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.tvsi.www;

public class SCBWSLocator extends org.apache.axis.client.Service implements vn.com.tvsi.www.SCBWS {

    public SCBWSLocator() {
    }


    public SCBWSLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SCBWSLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SCBWSSoap12
    private java.lang.String SCBWSSoap12_address = "http://192.168.253.38:5091/SCBWS.asmx";

    public java.lang.String getSCBWSSoap12Address() {
        return SCBWSSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SCBWSSoap12WSDDServiceName = "SCBWSSoap12";

    public java.lang.String getSCBWSSoap12WSDDServiceName() {
        return SCBWSSoap12WSDDServiceName;
    }

    public void setSCBWSSoap12WSDDServiceName(java.lang.String name) {
        SCBWSSoap12WSDDServiceName = name;
    }

    public vn.com.tvsi.www.SCBWSSoap getSCBWSSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SCBWSSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSCBWSSoap12(endpoint);
    }

    public vn.com.tvsi.www.SCBWSSoap getSCBWSSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            vn.com.tvsi.www.SCBWSSoap12Stub _stub = new vn.com.tvsi.www.SCBWSSoap12Stub(portAddress, this);
            _stub.setPortName(getSCBWSSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSCBWSSoap12EndpointAddress(java.lang.String address) {
        SCBWSSoap12_address = address;
    }


    // Use to get a proxy class for SCBWSSoap
    private java.lang.String SCBWSSoap_address = "http://192.168.253.38:5091/SCBWS.asmx";

    public java.lang.String getSCBWSSoapAddress() {
        return SCBWSSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SCBWSSoapWSDDServiceName = "SCBWSSoap";

    public java.lang.String getSCBWSSoapWSDDServiceName() {
        return SCBWSSoapWSDDServiceName;
    }

    public void setSCBWSSoapWSDDServiceName(java.lang.String name) {
        SCBWSSoapWSDDServiceName = name;
    }

    public vn.com.tvsi.www.SCBWSSoap getSCBWSSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SCBWSSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSCBWSSoap(endpoint);
    }

    public vn.com.tvsi.www.SCBWSSoap getSCBWSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            vn.com.tvsi.www.SCBWSSoapStub _stub = new vn.com.tvsi.www.SCBWSSoapStub(portAddress, this);
            _stub.setPortName(getSCBWSSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSCBWSSoapEndpointAddress(java.lang.String address) {
        SCBWSSoap_address = address;
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
            if (vn.com.tvsi.www.SCBWSSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                vn.com.tvsi.www.SCBWSSoap12Stub _stub = new vn.com.tvsi.www.SCBWSSoap12Stub(new java.net.URL(SCBWSSoap12_address), this);
                _stub.setPortName(getSCBWSSoap12WSDDServiceName());
                return _stub;
            }
            if (vn.com.tvsi.www.SCBWSSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                vn.com.tvsi.www.SCBWSSoapStub _stub = new vn.com.tvsi.www.SCBWSSoapStub(new java.net.URL(SCBWSSoap_address), this);
                _stub.setPortName(getSCBWSSoapWSDDServiceName());
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
        if ("SCBWSSoap12".equals(inputPortName)) {
            return getSCBWSSoap12();
        }
        else if ("SCBWSSoap".equals(inputPortName)) {
            return getSCBWSSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.tvsi.com.vn", "SCBWS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.tvsi.com.vn", "SCBWSSoap12"));
            ports.add(new javax.xml.namespace.QName("http://www.tvsi.com.vn", "SCBWSSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SCBWSSoap12".equals(portName)) {
            setSCBWSSoap12EndpointAddress(address);
        }
        else 
if ("SCBWSSoap".equals(portName)) {
            setSCBWSSoapEndpointAddress(address);
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
