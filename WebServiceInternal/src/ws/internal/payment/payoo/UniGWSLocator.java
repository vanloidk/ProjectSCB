/**
 * UniGWSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.payoo;

public class UniGWSLocator extends org.apache.axis.client.Service implements ws.internal.payment.payoo.UniGWS {

    public UniGWSLocator() {
    }


    public UniGWSLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UniGWSLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UniGWSSoap12
    private java.lang.String UniGWSSoap12_address = "https://vusandbox.payoo.com.vn/UniGWS.asmx";

    public java.lang.String getUniGWSSoap12Address() {
        return UniGWSSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UniGWSSoap12WSDDServiceName = "UniGWSSoap12";

    public java.lang.String getUniGWSSoap12WSDDServiceName() {
        return UniGWSSoap12WSDDServiceName;
    }

    public void setUniGWSSoap12WSDDServiceName(java.lang.String name) {
        UniGWSSoap12WSDDServiceName = name;
    }

    public ws.internal.payment.payoo.UniGWSSoap_PortType getUniGWSSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UniGWSSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUniGWSSoap12(endpoint);
    }

    public ws.internal.payment.payoo.UniGWSSoap_PortType getUniGWSSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.payment.payoo.UniGWSSoap12Stub _stub = new ws.internal.payment.payoo.UniGWSSoap12Stub(portAddress, this);
            _stub.setPortName(getUniGWSSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUniGWSSoap12EndpointAddress(java.lang.String address) {
        UniGWSSoap12_address = address;
    }


    // Use to get a proxy class for UniGWSSoap
    private java.lang.String UniGWSSoap_address = "https://vusandbox.payoo.com.vn/UniGWS.asmx";

    public java.lang.String getUniGWSSoapAddress() {
        return UniGWSSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UniGWSSoapWSDDServiceName = "UniGWSSoap";

    public java.lang.String getUniGWSSoapWSDDServiceName() {
        return UniGWSSoapWSDDServiceName;
    }

    public void setUniGWSSoapWSDDServiceName(java.lang.String name) {
        UniGWSSoapWSDDServiceName = name;
    }

    public ws.internal.payment.payoo.UniGWSSoap_PortType getUniGWSSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UniGWSSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUniGWSSoap(endpoint);
    }

    public ws.internal.payment.payoo.UniGWSSoap_PortType getUniGWSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.payment.payoo.UniGWSSoap_BindingStub _stub = new ws.internal.payment.payoo.UniGWSSoap_BindingStub(portAddress, this);
            _stub.setPortName(getUniGWSSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUniGWSSoapEndpointAddress(java.lang.String address) {
        UniGWSSoap_address = address;
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
            if (ws.internal.payment.payoo.UniGWSSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.payment.payoo.UniGWSSoap12Stub _stub = new ws.internal.payment.payoo.UniGWSSoap12Stub(new java.net.URL(UniGWSSoap12_address), this);
                _stub.setPortName(getUniGWSSoap12WSDDServiceName());
                return _stub;
            }
            if (ws.internal.payment.payoo.UniGWSSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.payment.payoo.UniGWSSoap_BindingStub _stub = new ws.internal.payment.payoo.UniGWSSoap_BindingStub(new java.net.URL(UniGWSSoap_address), this);
                _stub.setPortName(getUniGWSSoapWSDDServiceName());
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
        if ("UniGWSSoap12".equals(inputPortName)) {
            return getUniGWSSoap12();
        }
        else if ("UniGWSSoap".equals(inputPortName)) {
            return getUniGWSSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("UniGWS", "UniGWS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("UniGWS", "UniGWSSoap12"));
            ports.add(new javax.xml.namespace.QName("UniGWS", "UniGWSSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UniGWSSoap12".equals(portName)) {
            setUniGWSSoap12EndpointAddress(address);
        }
        else 
if ("UniGWSSoap".equals(portName)) {
            setUniGWSSoapEndpointAddress(address);
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
