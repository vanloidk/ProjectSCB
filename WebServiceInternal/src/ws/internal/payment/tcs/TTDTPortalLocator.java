/**
 * TTDTPortalLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.tcs;

public class TTDTPortalLocator extends org.apache.axis.client.Service implements ws.internal.payment.tcs.TTDTPortal {

    public TTDTPortalLocator() {
    }


    public TTDTPortalLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TTDTPortalLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TTDTPortalSoap
    private java.lang.String TTDTPortalSoap_address = "https://192.168.1.3/TTDT3/TTDTPortal.asmx";

    public java.lang.String getTTDTPortalSoapAddress() {
        return TTDTPortalSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TTDTPortalSoapWSDDServiceName = "TTDTPortalSoap";

    public java.lang.String getTTDTPortalSoapWSDDServiceName() {
        return TTDTPortalSoapWSDDServiceName;
    }

    public void setTTDTPortalSoapWSDDServiceName(java.lang.String name) {
        TTDTPortalSoapWSDDServiceName = name;
    }

    public ws.internal.payment.tcs.TTDTPortalSoap getTTDTPortalSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TTDTPortalSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTTDTPortalSoap(endpoint);
    }

    public ws.internal.payment.tcs.TTDTPortalSoap getTTDTPortalSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.payment.tcs.TTDTPortalSoapStub _stub = new ws.internal.payment.tcs.TTDTPortalSoapStub(portAddress, this);
            _stub.setPortName(getTTDTPortalSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTTDTPortalSoapEndpointAddress(java.lang.String address) {
        TTDTPortalSoap_address = address;
    }


    // Use to get a proxy class for TTDTPortalSoap12
    private java.lang.String TTDTPortalSoap12_address = "https://192.168.1.3/TTDT3/TTDTPortal.asmx";

    public java.lang.String getTTDTPortalSoap12Address() {
        return TTDTPortalSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TTDTPortalSoap12WSDDServiceName = "TTDTPortalSoap12";

    public java.lang.String getTTDTPortalSoap12WSDDServiceName() {
        return TTDTPortalSoap12WSDDServiceName;
    }

    public void setTTDTPortalSoap12WSDDServiceName(java.lang.String name) {
        TTDTPortalSoap12WSDDServiceName = name;
    }

    public ws.internal.payment.tcs.TTDTPortalSoap getTTDTPortalSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TTDTPortalSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTTDTPortalSoap12(endpoint);
    }

    public ws.internal.payment.tcs.TTDTPortalSoap getTTDTPortalSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.payment.tcs.TTDTPortalSoap12Stub _stub = new ws.internal.payment.tcs.TTDTPortalSoap12Stub(portAddress, this);
            _stub.setPortName(getTTDTPortalSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTTDTPortalSoap12EndpointAddress(java.lang.String address) {
        TTDTPortalSoap12_address = address;
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
            if (ws.internal.payment.tcs.TTDTPortalSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.payment.tcs.TTDTPortalSoapStub _stub = new ws.internal.payment.tcs.TTDTPortalSoapStub(new java.net.URL(TTDTPortalSoap_address), this);
                _stub.setPortName(getTTDTPortalSoapWSDDServiceName());
                return _stub;
            }
            if (ws.internal.payment.tcs.TTDTPortalSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.payment.tcs.TTDTPortalSoap12Stub _stub = new ws.internal.payment.tcs.TTDTPortalSoap12Stub(new java.net.URL(TTDTPortalSoap12_address), this);
                _stub.setPortName(getTTDTPortalSoap12WSDDServiceName());
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
        if ("TTDTPortalSoap".equals(inputPortName)) {
            return getTTDTPortalSoap();
        }
        else if ("TTDTPortalSoap12".equals(inputPortName)) {
            return getTTDTPortalSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "TTDTPortal");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "TTDTPortalSoap"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "TTDTPortalSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TTDTPortalSoap".equals(portName)) {
            setTTDTPortalSoapEndpointAddress(address);
        }
        else 
if ("TTDTPortalSoap12".equals(portName)) {
            setTTDTPortalSoap12EndpointAddress(address);
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
