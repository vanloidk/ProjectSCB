/**
 * UnLockFeeLocator.java
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
public class UnLockFeeLocator extends org.apache.axis.client.Service implements scb.com.vn.vninfo.UnLockFee {

    /**
     *
     */
    public UnLockFeeLocator() {
    }

    /**
     *
     * @param config
     */
    public UnLockFeeLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    /**
     *
     * @param wsdlLoc
     * @param sName
     * @throws ServiceException
     */
    public UnLockFeeLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UnLockFeeSoap
    private java.lang.String UnLockFeeSoap_address = "";

    /**
     *
     * @return
     */
    public java.lang.String getUnLockFeeSoapAddress() {
        return UnLockFeeSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UnLockFeeSoapWSDDServiceName = "UnLockFeeSoap";

    /**
     *
     * @return
     */
    public java.lang.String getUnLockFeeSoapWSDDServiceName() {
        return UnLockFeeSoapWSDDServiceName;
    }

    /**
     *
     * @param name
     */
    public void setUnLockFeeSoapWSDDServiceName(java.lang.String name) {
        UnLockFeeSoapWSDDServiceName = name;
    }

    /**
     *
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.UnLockFeeSoap getUnLockFeeSoap() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UnLockFeeSoap_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUnLockFeeSoap(endpoint);
    }

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.UnLockFeeSoap getUnLockFeeSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            scb.com.vn.vninfo.UnLockFeeSoapStub _stub = new scb.com.vn.vninfo.UnLockFeeSoapStub(portAddress, this);
            _stub.setPortName(getUnLockFeeSoapWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     *
     * @param address
     */
    public void setUnLockFeeSoapEndpointAddress(java.lang.String address) {
        UnLockFeeSoap_address = address;
    }

    // Use to get a proxy class for UnLockFeeSoap12
    private java.lang.String UnLockFeeSoap12_address = "";

    /**
     *
     * @return
     */
    public java.lang.String getUnLockFeeSoap12Address() {
        return UnLockFeeSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UnLockFeeSoap12WSDDServiceName = "UnLockFeeSoap12";

    /**
     *
     * @return
     */
    public java.lang.String getUnLockFeeSoap12WSDDServiceName() {
        return UnLockFeeSoap12WSDDServiceName;
    }

    /**
     *
     * @param name
     */
    public void setUnLockFeeSoap12WSDDServiceName(java.lang.String name) {
        UnLockFeeSoap12WSDDServiceName = name;
    }

    /**
     *
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.UnLockFeeSoap getUnLockFeeSoap12() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UnLockFeeSoap12_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUnLockFeeSoap12(endpoint);
    }

    /**
     *
     * @param portAddress
     * @return
     * @throws ServiceException
     */
    public scb.com.vn.vninfo.UnLockFeeSoap getUnLockFeeSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            scb.com.vn.vninfo.UnLockFeeSoap12Stub _stub = new scb.com.vn.vninfo.UnLockFeeSoap12Stub(portAddress, this);
            _stub.setPortName(getUnLockFeeSoap12WSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     *
     * @param address
     */
    public void setUnLockFeeSoap12EndpointAddress(java.lang.String address) {
        UnLockFeeSoap12_address = address;
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
            if (scb.com.vn.vninfo.UnLockFeeSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                scb.com.vn.vninfo.UnLockFeeSoapStub _stub = new scb.com.vn.vninfo.UnLockFeeSoapStub(new java.net.URL(UnLockFeeSoap_address), this);
                _stub.setPortName(getUnLockFeeSoapWSDDServiceName());
                return _stub;
            }
            if (scb.com.vn.vninfo.UnLockFeeSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                scb.com.vn.vninfo.UnLockFeeSoap12Stub _stub = new scb.com.vn.vninfo.UnLockFeeSoap12Stub(new java.net.URL(UnLockFeeSoap12_address), this);
                _stub.setPortName(getUnLockFeeSoap12WSDDServiceName());
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
        if ("UnLockFeeSoap".equals(inputPortName)) {
            return getUnLockFeeSoap();
        } else if ("UnLockFeeSoap12".equals(inputPortName)) {
            return getUnLockFeeSoap12();
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
        return new javax.xml.namespace.QName("https://vnpay.vn/", "UnLockFee");
    }

    private java.util.HashSet ports = null;

    /**
     *
     * @return
     */
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://vnpay.vn/", "UnLockFeeSoap"));
            ports.add(new javax.xml.namespace.QName("https://vnpay.vn/", "UnLockFeeSoap12"));
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

        if ("UnLockFeeSoap".equals(portName)) {
            setUnLockFeeSoapEndpointAddress(address);
        } else if ("UnLockFeeSoap12".equals(portName)) {
            setUnLockFeeSoap12EndpointAddress(address);
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
