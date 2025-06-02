/**
 * Bill_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.dawaco;

public class Bill_ServiceLocator extends org.apache.axis.client.Service implements ws.internal.payment.dawaco.Bill_Service {

    public Bill_ServiceLocator() {
    }


    public Bill_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Bill_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Bill_ServiceSoap
    private java.lang.String Bill_ServiceSoap_address = "";

    public java.lang.String getBill_ServiceSoapAddress() {
        return Bill_ServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Bill_ServiceSoapWSDDServiceName = "Bill_ServiceSoap";

    public java.lang.String getBill_ServiceSoapWSDDServiceName() {
        return Bill_ServiceSoapWSDDServiceName;
    }

    public void setBill_ServiceSoapWSDDServiceName(java.lang.String name) {
        Bill_ServiceSoapWSDDServiceName = name;
    }

    public ws.internal.payment.dawaco.Bill_ServiceSoap getBill_ServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Bill_ServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBill_ServiceSoap(endpoint);
    }

    public ws.internal.payment.dawaco.Bill_ServiceSoap getBill_ServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.payment.dawaco.Bill_ServiceSoapStub _stub = new ws.internal.payment.dawaco.Bill_ServiceSoapStub(portAddress, this);
            _stub.setPortName(getBill_ServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBill_ServiceSoapEndpointAddress(java.lang.String address) {
        Bill_ServiceSoap_address = address;
    }


    // Use to get a proxy class for Bill_ServiceSoap12
    private java.lang.String Bill_ServiceSoap12_address = "http://101.99.52.241:88/Dawaco_Test/Bill_Service.asmx";

    public java.lang.String getBill_ServiceSoap12Address() {
        return Bill_ServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Bill_ServiceSoap12WSDDServiceName = "Bill_ServiceSoap12";

    public java.lang.String getBill_ServiceSoap12WSDDServiceName() {
        return Bill_ServiceSoap12WSDDServiceName;
    }

    public void setBill_ServiceSoap12WSDDServiceName(java.lang.String name) {
        Bill_ServiceSoap12WSDDServiceName = name;
    }

    public ws.internal.payment.dawaco.Bill_ServiceSoap getBill_ServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Bill_ServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBill_ServiceSoap12(endpoint);
    }

    public ws.internal.payment.dawaco.Bill_ServiceSoap getBill_ServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.payment.dawaco.Bill_ServiceSoap12Stub _stub = new ws.internal.payment.dawaco.Bill_ServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getBill_ServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBill_ServiceSoap12EndpointAddress(java.lang.String address) {
        Bill_ServiceSoap12_address = address;
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
            if (ws.internal.payment.dawaco.Bill_ServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.payment.dawaco.Bill_ServiceSoapStub _stub = new ws.internal.payment.dawaco.Bill_ServiceSoapStub(new java.net.URL(Bill_ServiceSoap_address), this);
                _stub.setPortName(getBill_ServiceSoapWSDDServiceName());
                return _stub;
            }
            if (ws.internal.payment.dawaco.Bill_ServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.payment.dawaco.Bill_ServiceSoap12Stub _stub = new ws.internal.payment.dawaco.Bill_ServiceSoap12Stub(new java.net.URL(Bill_ServiceSoap12_address), this);
                _stub.setPortName(getBill_ServiceSoap12WSDDServiceName());
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
        if ("Bill_ServiceSoap".equals(inputPortName)) {
            return getBill_ServiceSoap();
        }
        else if ("Bill_ServiceSoap12".equals(inputPortName)) {
            return getBill_ServiceSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "Bill_Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "Bill_ServiceSoap"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "Bill_ServiceSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Bill_ServiceSoap".equals(portName)) {
            setBill_ServiceSoapEndpointAddress(address);
        }
        else 
if ("Bill_ServiceSoap12".equals(portName)) {
            setBill_ServiceSoap12EndpointAddress(address);
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
