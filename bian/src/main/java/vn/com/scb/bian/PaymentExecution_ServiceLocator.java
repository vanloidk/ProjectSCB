/**
 * PaymentExecution_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class PaymentExecution_ServiceLocator extends org.apache.axis.client.Service implements vn.com.scb.bian.PaymentExecution_Service {

    public PaymentExecution_ServiceLocator() {
    }


    public PaymentExecution_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PaymentExecution_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PaymentExecutionSOAP
    private java.lang.String PaymentExecutionSOAP_address = "http://www.example.org/";

    public java.lang.String getPaymentExecutionSOAPAddress() {
        return PaymentExecutionSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PaymentExecutionSOAPWSDDServiceName = "PaymentExecutionSOAP";

    public java.lang.String getPaymentExecutionSOAPWSDDServiceName() {
        return PaymentExecutionSOAPWSDDServiceName;
    }

    public void setPaymentExecutionSOAPWSDDServiceName(java.lang.String name) {
        PaymentExecutionSOAPWSDDServiceName = name;
    }

    public vn.com.scb.bian.PaymentExecution_PortType getPaymentExecutionSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PaymentExecutionSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPaymentExecutionSOAP(endpoint);
    }

    public vn.com.scb.bian.PaymentExecution_PortType getPaymentExecutionSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            vn.com.scb.bian.PaymentExecutionSOAPStub _stub = new vn.com.scb.bian.PaymentExecutionSOAPStub(portAddress, this);
            _stub.setPortName(getPaymentExecutionSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPaymentExecutionSOAPEndpointAddress(java.lang.String address) {
        PaymentExecutionSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (vn.com.scb.bian.PaymentExecution_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                vn.com.scb.bian.PaymentExecutionSOAPStub _stub = new vn.com.scb.bian.PaymentExecutionSOAPStub(new java.net.URL(PaymentExecutionSOAP_address), this);
                _stub.setPortName(getPaymentExecutionSOAPWSDDServiceName());
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
        if ("PaymentExecutionSOAP".equals(inputPortName)) {
            return getPaymentExecutionSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("bian.scb.com.vn", "PaymentExecution");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("bian.scb.com.vn", "PaymentExecutionSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PaymentExecutionSOAP".equals(portName)) {
            setPaymentExecutionSOAPEndpointAddress(address);
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
