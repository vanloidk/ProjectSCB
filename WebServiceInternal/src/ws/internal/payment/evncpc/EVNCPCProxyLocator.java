/**
 * EVNCPCProxyLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evncpc;

public class EVNCPCProxyLocator extends org.apache.axis.client.Service implements ws.internal.payment.evncpc.EVNCPCProxy {

    public EVNCPCProxyLocator() {
    }


    public EVNCPCProxyLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EVNCPCProxyLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EVNCPCProxySoap12
    private java.lang.String EVNCPCProxySoap12_address = "http://192.168.112.4/EVNCPC/EVNCPCProxy.asmx";

    public java.lang.String getEVNCPCProxySoap12Address() {
        return EVNCPCProxySoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EVNCPCProxySoap12WSDDServiceName = "EVNCPCProxySoap12";

    public java.lang.String getEVNCPCProxySoap12WSDDServiceName() {
        return EVNCPCProxySoap12WSDDServiceName;
    }

    public void setEVNCPCProxySoap12WSDDServiceName(java.lang.String name) {
        EVNCPCProxySoap12WSDDServiceName = name;
    }

    public ws.internal.payment.evncpc.EVNCPCProxySoap_PortType getEVNCPCProxySoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EVNCPCProxySoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEVNCPCProxySoap12(endpoint);
    }

    public ws.internal.payment.evncpc.EVNCPCProxySoap_PortType getEVNCPCProxySoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.payment.evncpc.EVNCPCProxySoap12Stub _stub = new ws.internal.payment.evncpc.EVNCPCProxySoap12Stub(portAddress, this);
            _stub.setPortName(getEVNCPCProxySoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEVNCPCProxySoap12EndpointAddress(java.lang.String address) {
        EVNCPCProxySoap12_address = address;
    }


    // Use to get a proxy class for EVNCPCProxySoap
    private java.lang.String EVNCPCProxySoap_address = "http://192.168.112.4/EVNCPC/EVNCPCProxy.asmx";

    public java.lang.String getEVNCPCProxySoapAddress() {
        return EVNCPCProxySoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EVNCPCProxySoapWSDDServiceName = "EVNCPCProxySoap";

    public java.lang.String getEVNCPCProxySoapWSDDServiceName() {
        return EVNCPCProxySoapWSDDServiceName;
    }

    public void setEVNCPCProxySoapWSDDServiceName(java.lang.String name) {
        EVNCPCProxySoapWSDDServiceName = name;
    }

    public ws.internal.payment.evncpc.EVNCPCProxySoap_PortType getEVNCPCProxySoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EVNCPCProxySoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEVNCPCProxySoap(endpoint);
    }

    public ws.internal.payment.evncpc.EVNCPCProxySoap_PortType getEVNCPCProxySoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.payment.evncpc.EVNCPCProxySoap_BindingStub _stub = new ws.internal.payment.evncpc.EVNCPCProxySoap_BindingStub(portAddress, this);
            _stub.setPortName(getEVNCPCProxySoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEVNCPCProxySoapEndpointAddress(java.lang.String address) {
        EVNCPCProxySoap_address = address;
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
            if (ws.internal.payment.evncpc.EVNCPCProxySoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.payment.evncpc.EVNCPCProxySoap12Stub _stub = new ws.internal.payment.evncpc.EVNCPCProxySoap12Stub(new java.net.URL(EVNCPCProxySoap12_address), this);
                _stub.setPortName(getEVNCPCProxySoap12WSDDServiceName());
                return _stub;
            }
            if (ws.internal.payment.evncpc.EVNCPCProxySoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.payment.evncpc.EVNCPCProxySoap_BindingStub _stub = new ws.internal.payment.evncpc.EVNCPCProxySoap_BindingStub(new java.net.URL(EVNCPCProxySoap_address), this);
                _stub.setPortName(getEVNCPCProxySoapWSDDServiceName());
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
        if ("EVNCPCProxySoap12".equals(inputPortName)) {
            return getEVNCPCProxySoap12();
        }
        else if ("EVNCPCProxySoap".equals(inputPortName)) {
            return getEVNCPCProxySoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "EVNCPCProxy");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "EVNCPCProxySoap12"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "EVNCPCProxySoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EVNCPCProxySoap12".equals(portName)) {
            setEVNCPCProxySoap12EndpointAddress(address);
        }
        else 
if ("EVNCPCProxySoap".equals(portName)) {
            setEVNCPCProxySoapEndpointAddress(address);
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
