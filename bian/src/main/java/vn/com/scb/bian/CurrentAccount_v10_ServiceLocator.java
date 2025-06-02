/**
 * CurrentAccount_v10_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class CurrentAccount_v10_ServiceLocator extends org.apache.axis.client.Service implements vn.com.scb.bian.CurrentAccount_v10_Service {

    public CurrentAccount_v10_ServiceLocator() {
    }


    public CurrentAccount_v10_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CurrentAccount_v10_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CurrentAccount_v10SOAP
    private java.lang.String CurrentAccount_v10SOAP_address = "http://www.example.org/";

    public java.lang.String getCurrentAccount_v10SOAPAddress() {
        return CurrentAccount_v10SOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CurrentAccount_v10SOAPWSDDServiceName = "CurrentAccount_v1.0SOAP";

    public java.lang.String getCurrentAccount_v10SOAPWSDDServiceName() {
        return CurrentAccount_v10SOAPWSDDServiceName;
    }

    public void setCurrentAccount_v10SOAPWSDDServiceName(java.lang.String name) {
        CurrentAccount_v10SOAPWSDDServiceName = name;
    }

    public vn.com.scb.bian.CurrentAccount_v10_PortType getCurrentAccount_v10SOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CurrentAccount_v10SOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCurrentAccount_v10SOAP(endpoint);
    }

    public vn.com.scb.bian.CurrentAccount_v10_PortType getCurrentAccount_v10SOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            vn.com.scb.bian.CurrentAccount_v10SOAPStub _stub = new vn.com.scb.bian.CurrentAccount_v10SOAPStub(portAddress, this);
            _stub.setPortName(getCurrentAccount_v10SOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCurrentAccount_v10SOAPEndpointAddress(java.lang.String address) {
        CurrentAccount_v10SOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (vn.com.scb.bian.CurrentAccount_v10_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                vn.com.scb.bian.CurrentAccount_v10SOAPStub _stub = new vn.com.scb.bian.CurrentAccount_v10SOAPStub(new java.net.URL(CurrentAccount_v10SOAP_address), this);
                _stub.setPortName(getCurrentAccount_v10SOAPWSDDServiceName());
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
        if ("CurrentAccount_v1.0SOAP".equals(inputPortName)) {
            return getCurrentAccount_v10SOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("bian.scb.com.vn", "CurrentAccount_v1.0");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("bian.scb.com.vn", "CurrentAccount_v1.0SOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CurrentAccount_v10SOAP".equals(portName)) {
            setCurrentAccount_v10SOAPEndpointAddress(address);
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
