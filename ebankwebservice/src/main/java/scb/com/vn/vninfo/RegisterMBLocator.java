/**
 * RegisterMBLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package scb.com.vn.vninfo;

import javax.xml.rpc.ServiceException;

/**
 *
 * @author minhndb
 */
public class RegisterMBLocator extends org.apache.axis.client.Service implements scb.com.vn.vninfo.RegisterMB {

    /**
     *
     */
    public RegisterMBLocator() {
    }

    /**
     *
     * @param config
     */
    public RegisterMBLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    /**
     *
     * @param wsdlLoc
     * @param sName
     * @throws ServiceException
     */
    public RegisterMBLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RegisterMBSoap12
    private java.lang.String RegisterMBSoap12_address = "http://192.168.73.22/VNINFOSCBGW/RegisterMB.asmx";

    /**
     *
     * @return
     */
    public java.lang.String getRegisterMBSoap12Address() {
        return RegisterMBSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RegisterMBSoap12WSDDServiceName = "RegisterMBSoap12";

    /**
     *
     * @return
     */
    public java.lang.String getRegisterMBSoap12WSDDServiceName() {
        return RegisterMBSoap12WSDDServiceName;
    }

    /**
     *
     * @param name
     */
    public void setRegisterMBSoap12WSDDServiceName(java.lang.String name) {
        RegisterMBSoap12WSDDServiceName = name;
    }

    /**
     *
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.RegisterMBSoap getRegisterMBSoap12() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RegisterMBSoap12_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRegisterMBSoap12(endpoint);
    }

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.RegisterMBSoap getRegisterMBSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            scb.com.vn.vninfo.RegisterMBSoap12Stub _stub = new scb.com.vn.vninfo.RegisterMBSoap12Stub(portAddress, this);
            _stub.setPortName(getRegisterMBSoap12WSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     *
     * @param address
     */
    public void setRegisterMBSoap12EndpointAddress(java.lang.String address) {
        RegisterMBSoap12_address = address;
    }

    // Use to get a proxy class for RegisterMBSoap
    private java.lang.String RegisterMBSoap_address = "http://192.168.73.22/VNINFOSCBGW/RegisterMB.asmx";

    /**
     *
     * @return
     */
    public java.lang.String getRegisterMBSoapAddress() {
        return RegisterMBSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RegisterMBSoapWSDDServiceName = "RegisterMBSoap";

    /**
     *
     * @return
     */
    public java.lang.String getRegisterMBSoapWSDDServiceName() {
        return RegisterMBSoapWSDDServiceName;
    }

    /**
     *
     * @param name
     */
    public void setRegisterMBSoapWSDDServiceName(java.lang.String name) {
        RegisterMBSoapWSDDServiceName = name;
    }

    /**
     *
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.RegisterMBSoap getRegisterMBSoap() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RegisterMBSoap_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRegisterMBSoap(endpoint);
    }

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.RegisterMBSoap getRegisterMBSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            scb.com.vn.vninfo.RegisterMBSoapStub _stub = new scb.com.vn.vninfo.RegisterMBSoapStub(portAddress, this);
            _stub.setPortName(getRegisterMBSoapWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     *
     * @param address
     */
    public void setRegisterMBSoapEndpointAddress(java.lang.String address) {
        RegisterMBSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation. If this service has
     * no port for the given interface, then ServiceException is thrown. This
     * service has multiple ports for a given interface; the proxy
     * implementation returned may be indeterminate.
     * @param serviceEndpointInterface
     * @return 
     * @throws javax.xml.rpc.ServiceException
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (scb.com.vn.vninfo.RegisterMBSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                scb.com.vn.vninfo.RegisterMBSoap12Stub _stub = new scb.com.vn.vninfo.RegisterMBSoap12Stub(new java.net.URL(RegisterMBSoap12_address), this);
                _stub.setPortName(getRegisterMBSoap12WSDDServiceName());
                return _stub;
            }
            if (scb.com.vn.vninfo.RegisterMBSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                scb.com.vn.vninfo.RegisterMBSoapStub _stub = new scb.com.vn.vninfo.RegisterMBSoapStub(new java.net.URL(RegisterMBSoap_address), this);
                _stub.setPortName(getRegisterMBSoapWSDDServiceName());
                return _stub;
            }
        } catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation. If this service has
     * no port for the given interface, then ServiceException is thrown.
     * @param portName
     * @param serviceEndpointInterface
     * @return 
     * @throws javax.xml.rpc.ServiceException 
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RegisterMBSoap12".equals(inputPortName)) {
            return getRegisterMBSoap12();
        } else if ("RegisterMBSoap".equals(inputPortName)) {
            return getRegisterMBSoap();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    /**
     *
     * @return
     */
    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "RegisterMB");
    }

    private java.util.HashSet ports = null;

    /**
     *
     * @return
     */
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "RegisterMBSoap12"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "RegisterMBSoap"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     * @param portName
     * @param address
     * @throws javax.xml.rpc.ServiceException
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("RegisterMBSoap12".equals(portName)) {
            setRegisterMBSoap12EndpointAddress(address);
        } else if ("RegisterMBSoap".equals(portName)) {
            setRegisterMBSoapEndpointAddress(address);
        } else { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
     * Set the endpoint address for the specified port name.
     * @param portName
     * @param address
     * @throws javax.xml.rpc.ServiceException
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
