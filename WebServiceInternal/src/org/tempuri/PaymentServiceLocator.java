/**
 * PaymentServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class PaymentServiceLocator extends org.apache.axis.client.Service implements org.tempuri.PaymentService {

    public PaymentServiceLocator() {
    }


    public PaymentServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PaymentServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IPaymentService
    private java.lang.String BasicHttpBinding_IPaymentService_address = "http://192.168.56.22/PaymentEVNSPC_TEST/PaymentService.svc";

    public java.lang.String getBasicHttpBinding_IPaymentServiceAddress() {
        return BasicHttpBinding_IPaymentService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_IPaymentServiceWSDDServiceName = "BasicHttpBinding_IPaymentService";

    public java.lang.String getBasicHttpBinding_IPaymentServiceWSDDServiceName() {
        return BasicHttpBinding_IPaymentServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IPaymentServiceWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_IPaymentServiceWSDDServiceName = name;
    }

    public org.tempuri.IPaymentService getBasicHttpBinding_IPaymentService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IPaymentService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IPaymentService(endpoint);
    }

    public org.tempuri.IPaymentService getBasicHttpBinding_IPaymentService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.BasicHttpBinding_IPaymentServiceStub _stub = new org.tempuri.BasicHttpBinding_IPaymentServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IPaymentServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IPaymentServiceEndpointAddress(java.lang.String address) {
        BasicHttpBinding_IPaymentService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.IPaymentService.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.BasicHttpBinding_IPaymentServiceStub _stub = new org.tempuri.BasicHttpBinding_IPaymentServiceStub(new java.net.URL(BasicHttpBinding_IPaymentService_address), this);
                _stub.setPortName(getBasicHttpBinding_IPaymentServiceWSDDServiceName());
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
        if ("BasicHttpBinding_IPaymentService".equals(inputPortName)) {
            return getBasicHttpBinding_IPaymentService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "PaymentService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IPaymentService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_IPaymentService".equals(portName)) {
            setBasicHttpBinding_IPaymentServiceEndpointAddress(address);
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
