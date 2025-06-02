/**
 * TSCServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.vn.edu.sc.ws;

public class TSCServiceLocator extends org.apache.axis.client.Service implements ws.internal.vn.edu.sc.ws.TSCService {

    public TSCServiceLocator() {
    }


    public TSCServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TSCServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TSCServiceSoap12
    private java.lang.String TSCServiceSoap12_address = "https://192.168.56.4/ws/tscservice.asmx";

    public java.lang.String getTSCServiceSoap12Address() {
        return TSCServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TSCServiceSoap12WSDDServiceName = "TSCServiceSoap12";

    public java.lang.String getTSCServiceSoap12WSDDServiceName() {
        return TSCServiceSoap12WSDDServiceName;
    }

    public void setTSCServiceSoap12WSDDServiceName(java.lang.String name) {
        TSCServiceSoap12WSDDServiceName = name;
    }

    public ws.internal.vn.edu.sc.ws.TSCServiceSoap getTSCServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TSCServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTSCServiceSoap12(endpoint);
    }

    public ws.internal.vn.edu.sc.ws.TSCServiceSoap getTSCServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.vn.edu.sc.ws.TSCServiceSoap12Stub _stub = new ws.internal.vn.edu.sc.ws.TSCServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getTSCServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTSCServiceSoap12EndpointAddress(java.lang.String address) {
        TSCServiceSoap12_address = address;
    }


    // Use to get a proxy class for TSCServiceSoap
    private java.lang.String TSCServiceSoap_address = "https://192.168.56.4/ws/tscservice.asmx";

    public java.lang.String getTSCServiceSoapAddress() {
        return TSCServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TSCServiceSoapWSDDServiceName = "TSCServiceSoap";

    public java.lang.String getTSCServiceSoapWSDDServiceName() {
        return TSCServiceSoapWSDDServiceName;
    }

    public void setTSCServiceSoapWSDDServiceName(java.lang.String name) {
        TSCServiceSoapWSDDServiceName = name;
    }

    public ws.internal.vn.edu.sc.ws.TSCServiceSoap getTSCServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TSCServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTSCServiceSoap(endpoint);
    }

    public ws.internal.vn.edu.sc.ws.TSCServiceSoap getTSCServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.internal.vn.edu.sc.ws.TSCServiceSoapStub _stub = new ws.internal.vn.edu.sc.ws.TSCServiceSoapStub(portAddress, this);
            _stub.setPortName(getTSCServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTSCServiceSoapEndpointAddress(java.lang.String address) {
        TSCServiceSoap_address = address;
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
            if (ws.internal.vn.edu.sc.ws.TSCServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.vn.edu.sc.ws.TSCServiceSoap12Stub _stub = new ws.internal.vn.edu.sc.ws.TSCServiceSoap12Stub(new java.net.URL(TSCServiceSoap12_address), this);
                _stub.setPortName(getTSCServiceSoap12WSDDServiceName());
                return _stub;
            }
            if (ws.internal.vn.edu.sc.ws.TSCServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.internal.vn.edu.sc.ws.TSCServiceSoapStub _stub = new ws.internal.vn.edu.sc.ws.TSCServiceSoapStub(new java.net.URL(TSCServiceSoap_address), this);
                _stub.setPortName(getTSCServiceSoapWSDDServiceName());
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
        if ("TSCServiceSoap12".equals(inputPortName)) {
            return getTSCServiceSoap12();
        }
        else if ("TSCServiceSoap".equals(inputPortName)) {
            return getTSCServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "TSCService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "TSCServiceSoap12"));
            ports.add(new javax.xml.namespace.QName("http://sc.edu.vn/ws/", "TSCServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TSCServiceSoap12".equals(portName)) {
            setTSCServiceSoap12EndpointAddress(address);
        }
        else 
if ("TSCServiceSoap".equals(portName)) {
            setTSCServiceSoapEndpointAddress(address);
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
