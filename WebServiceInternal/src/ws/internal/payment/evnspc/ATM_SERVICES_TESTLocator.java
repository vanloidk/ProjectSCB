/**
 * ATM_SERVICES_TESTLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class ATM_SERVICES_TESTLocator extends org.apache.axis.client.Service implements ws.internal.payment.evnspc.ATM_SERVICES_TEST {

    public ATM_SERVICES_TESTLocator() {
    }


    public ATM_SERVICES_TESTLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ATM_SERVICES_TESTLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ATM_SERVICES_TESTSoap12
    private java.lang.String ATM_SERVICES_TESTSoap12_address = "http://10.195.196.9/ATM_SERVICES_TEST/ATM_SERVICES_TEST.asmx";

    public java.lang.String getATM_SERVICES_TESTSoap12Address() {
        return ATM_SERVICES_TESTSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ATM_SERVICES_TESTSoap12WSDDServiceName = "ATM_SERVICES_TESTSoap12";

    public java.lang.String getATM_SERVICES_TESTSoap12WSDDServiceName() {
        return ATM_SERVICES_TESTSoap12WSDDServiceName;
    }

    public void setATM_SERVICES_TESTSoap12WSDDServiceName(java.lang.String name) {
        ATM_SERVICES_TESTSoap12WSDDServiceName = name;
    }

    public ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap_PortType getATM_SERVICES_TESTSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ATM_SERVICES_TESTSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getATM_SERVICES_TESTSoap12(endpoint);
    }

    public ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap_PortType getATM_SERVICES_TESTSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap12Stub _stub = new ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap12Stub(portAddress, this);
            _stub.setPortName(getATM_SERVICES_TESTSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setATM_SERVICES_TESTSoap12EndpointAddress(java.lang.String address) {
        ATM_SERVICES_TESTSoap12_address = address;
    }


    // Use to get a proxy class for ATM_SERVICES_TESTSoap
    private java.lang.String ATM_SERVICES_TESTSoap_address = "http://10.195.196.9/ATM_SERVICES_TEST/ATM_SERVICES_TEST.asmx";

    public java.lang.String getATM_SERVICES_TESTSoapAddress() {
        return ATM_SERVICES_TESTSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ATM_SERVICES_TESTSoapWSDDServiceName = "ATM_SERVICES_TESTSoap";

    public java.lang.String getATM_SERVICES_TESTSoapWSDDServiceName() {
        return ATM_SERVICES_TESTSoapWSDDServiceName;
    }

    public void setATM_SERVICES_TESTSoapWSDDServiceName(java.lang.String name) {
        ATM_SERVICES_TESTSoapWSDDServiceName = name;
    }

    public ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap_PortType getATM_SERVICES_TESTSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ATM_SERVICES_TESTSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getATM_SERVICES_TESTSoap(endpoint);
    }

    public ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap_PortType getATM_SERVICES_TESTSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap_BindingStub _stub = new ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap_BindingStub(portAddress, this);
            _stub.setPortName(getATM_SERVICES_TESTSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setATM_SERVICES_TESTSoapEndpointAddress(java.lang.String address) {
        ATM_SERVICES_TESTSoap_address = address;
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
            if (ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap12Stub _stub = new ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap12Stub(new java.net.URL(ATM_SERVICES_TESTSoap12_address), this);
                _stub.setPortName(getATM_SERVICES_TESTSoap12WSDDServiceName());
                return _stub;
            }
            if (ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap_BindingStub _stub = new ws.internal.payment.evnspc.ATM_SERVICES_TESTSoap_BindingStub(new java.net.URL(ATM_SERVICES_TESTSoap_address), this);
                _stub.setPortName(getATM_SERVICES_TESTSoapWSDDServiceName());
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
        if ("ATM_SERVICES_TESTSoap12".equals(inputPortName)) {
            return getATM_SERVICES_TESTSoap12();
        }
        else if ("ATM_SERVICES_TESTSoap".equals(inputPortName)) {
            return getATM_SERVICES_TESTSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ATM_SERVICES_TEST");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ATM_SERVICES_TESTSoap12"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ATM_SERVICES_TESTSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ATM_SERVICES_TESTSoap12".equals(portName)) {
            setATM_SERVICES_TESTSoap12EndpointAddress(address);
        }
        else 
if ("ATM_SERVICES_TESTSoap".equals(portName)) {
            setATM_SERVICES_TESTSoapEndpointAddress(address);
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
