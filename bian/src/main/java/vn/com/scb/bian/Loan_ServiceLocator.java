/**
 * Loan_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class Loan_ServiceLocator extends org.apache.axis.client.Service implements vn.com.scb.bian.Loan_Service {

    public Loan_ServiceLocator() {
    }


    public Loan_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Loan_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LoanSOAP
    private java.lang.String LoanSOAP_address = "http://www.example.org/";

    public java.lang.String getLoanSOAPAddress() {
        return LoanSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LoanSOAPWSDDServiceName = "LoanSOAP";

    public java.lang.String getLoanSOAPWSDDServiceName() {
        return LoanSOAPWSDDServiceName;
    }

    public void setLoanSOAPWSDDServiceName(java.lang.String name) {
        LoanSOAPWSDDServiceName = name;
    }

    public vn.com.scb.bian.Loan_PortType getLoanSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LoanSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLoanSOAP(endpoint);
    }

    public vn.com.scb.bian.Loan_PortType getLoanSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            vn.com.scb.bian.LoanSOAPStub _stub = new vn.com.scb.bian.LoanSOAPStub(portAddress, this);
            _stub.setPortName(getLoanSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLoanSOAPEndpointAddress(java.lang.String address) {
        LoanSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (vn.com.scb.bian.Loan_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                vn.com.scb.bian.LoanSOAPStub _stub = new vn.com.scb.bian.LoanSOAPStub(new java.net.URL(LoanSOAP_address), this);
                _stub.setPortName(getLoanSOAPWSDDServiceName());
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
        if ("LoanSOAP".equals(inputPortName)) {
            return getLoanSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("bian.scb.com.vn", "Loan");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("bian.scb.com.vn", "LoanSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LoanSOAP".equals(portName)) {
            setLoanSOAPEndpointAddress(address);
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
