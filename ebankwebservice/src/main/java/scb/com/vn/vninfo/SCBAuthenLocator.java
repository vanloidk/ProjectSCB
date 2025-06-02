/**
 * SCBAuthenLocator.java
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
public class SCBAuthenLocator extends org.apache.axis.client.Service implements scb.com.vn.vninfo.SCBAuthen {

    /**
     *
     */
    public SCBAuthenLocator() {
    }

    /**
     *
     * @param config
     */
    public SCBAuthenLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    /**
     *
     * @param wsdlLoc
     * @param sName
     * @throws ServiceException
     */
    public SCBAuthenLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SCBAuthenSoap12
    private java.lang.String SCBAuthenSoap12_address = "http://192.168.57.17/SCBAuthen/SCBAuthen.asmx";

    /**
     *
     * @return
     */
    public java.lang.String getSCBAuthenSoap12Address() {
        return SCBAuthenSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SCBAuthenSoap12WSDDServiceName = "SCBAuthenSoap12";

    /**
     *
     * @return
     */
    public java.lang.String getSCBAuthenSoap12WSDDServiceName() {
        return SCBAuthenSoap12WSDDServiceName;
    }

    /**
     *
     * @param name
     */
    public void setSCBAuthenSoap12WSDDServiceName(java.lang.String name) {
        SCBAuthenSoap12WSDDServiceName = name;
    }

    /**
     *
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.SCBAuthenSoap_PortType getSCBAuthenSoap12() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SCBAuthenSoap12_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSCBAuthenSoap12(endpoint);
    }

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.SCBAuthenSoap_PortType getSCBAuthenSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            scb.com.vn.vninfo.SCBAuthenSoap12Stub _stub = new scb.com.vn.vninfo.SCBAuthenSoap12Stub(portAddress, this);
            _stub.setPortName(getSCBAuthenSoap12WSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     *
     * @param address
     */
    public void setSCBAuthenSoap12EndpointAddress(java.lang.String address) {
        SCBAuthenSoap12_address = address;
    }

    // Use to get a proxy class for SCBAuthenSoap
    private java.lang.String SCBAuthenSoap_address = "http://192.168.57.17/SCBAuthen/SCBAuthen.asmx";

    /**
     *
     * @return
     */
    public java.lang.String getSCBAuthenSoapAddress() {
        return SCBAuthenSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SCBAuthenSoapWSDDServiceName = "SCBAuthenSoap";

    /**
     *
     * @return
     */
    public java.lang.String getSCBAuthenSoapWSDDServiceName() {
        return SCBAuthenSoapWSDDServiceName;
    }

    /**
     *
     * @param name
     */
    public void setSCBAuthenSoapWSDDServiceName(java.lang.String name) {
        SCBAuthenSoapWSDDServiceName = name;
    }

    /**
     *
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.SCBAuthenSoap_PortType getSCBAuthenSoap() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SCBAuthenSoap_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSCBAuthenSoap(endpoint);
    }

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.SCBAuthenSoap_PortType getSCBAuthenSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            scb.com.vn.vninfo.SCBAuthenSoap_BindingStub _stub = new scb.com.vn.vninfo.SCBAuthenSoap_BindingStub(portAddress, this);
            _stub.setPortName(getSCBAuthenSoapWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     *
     * @param address
     */
    public void setSCBAuthenSoapEndpointAddress(java.lang.String address) {
        SCBAuthenSoap_address = address;
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
            if (scb.com.vn.vninfo.SCBAuthenSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                scb.com.vn.vninfo.SCBAuthenSoap12Stub _stub = new scb.com.vn.vninfo.SCBAuthenSoap12Stub(new java.net.URL(SCBAuthenSoap12_address), this);
                _stub.setPortName(getSCBAuthenSoap12WSDDServiceName());
                return _stub;
            }
            if (scb.com.vn.vninfo.SCBAuthenSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                scb.com.vn.vninfo.SCBAuthenSoap_BindingStub _stub = new scb.com.vn.vninfo.SCBAuthenSoap_BindingStub(new java.net.URL(SCBAuthenSoap_address), this);
                _stub.setPortName(getSCBAuthenSoapWSDDServiceName());
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
        if ("SCBAuthenSoap12".equals(inputPortName)) {
            return getSCBAuthenSoap12();
        } else if ("SCBAuthenSoap".equals(inputPortName)) {
            return getSCBAuthenSoap();
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
        return new javax.xml.namespace.QName("https://vnpay.vn/", "SCBAuthen");
    }

    private java.util.HashSet ports = null;

    /**
     *
     * @return
     */
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://vnpay.vn/", "SCBAuthenSoap12"));
            ports.add(new javax.xml.namespace.QName("https://vnpay.vn/", "SCBAuthenSoap"));
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

        if ("SCBAuthenSoap12".equals(portName)) {
            setSCBAuthenSoap12EndpointAddress(address);
        } else if ("SCBAuthenSoap".equals(portName)) {
            setSCBAuthenSoapEndpointAddress(address);
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
