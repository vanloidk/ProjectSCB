/**
 * ConnectToEVNServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.evnit.webservice.service;

public class ConnectToEVNServiceLocator extends org.apache.axis.client.Service implements ws.internal.evnit.webservice.service.ConnectToEVNService {

    public ConnectToEVNServiceLocator() {
    }


    public ConnectToEVNServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConnectToEVNServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ConnectToEVNPort
    private java.lang.String ConnectToEVNPort_address = "http://10.9.155.52:8808/ConnectToEVN";

    public java.lang.String getConnectToEVNPortAddress() {
        return ConnectToEVNPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ConnectToEVNPortWSDDServiceName = "ConnectToEVNPort";

    public java.lang.String getConnectToEVNPortWSDDServiceName() {
        return ConnectToEVNPortWSDDServiceName;
    }

    public void setConnectToEVNPortWSDDServiceName(java.lang.String name) {
        ConnectToEVNPortWSDDServiceName = name;
    }

    public ws.internal.evnit.webservice.service.ConnectToEVN getConnectToEVNPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ConnectToEVNPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getConnectToEVNPort(endpoint);
    }

    public ws.internal.evnit.webservice.service.ConnectToEVN getConnectToEVNPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.evnit.webservice.service.ConnectToEVNPortBindingStub _stub = new ws.internal.evnit.webservice.service.ConnectToEVNPortBindingStub(portAddress, this);
            _stub.setPortName(getConnectToEVNPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setConnectToEVNPortEndpointAddress(java.lang.String address) {
        ConnectToEVNPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ws.internal.evnit.webservice.service.ConnectToEVN.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.evnit.webservice.service.ConnectToEVNPortBindingStub _stub = new ws.internal.evnit.webservice.service.ConnectToEVNPortBindingStub(new java.net.URL(ConnectToEVNPort_address), this);
                _stub.setPortName(getConnectToEVNPortWSDDServiceName());
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
        if ("ConnectToEVNPort".equals(inputPortName)) {
            return getConnectToEVNPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.webservice.evnit/", "ConnectToEVNService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.webservice.evnit/", "ConnectToEVNPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ConnectToEVNPort".equals(portName)) {
            setConnectToEVNPortEndpointAddress(address);
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
