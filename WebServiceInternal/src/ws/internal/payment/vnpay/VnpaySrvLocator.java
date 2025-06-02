/**
 * VnpaySrvLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.vnpay;

public class VnpaySrvLocator extends org.apache.axis.client.Service implements VnpaySrv {

    public VnpaySrvLocator() {
    }


    public VnpaySrvLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public VnpaySrvLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for VnpaySrvSoap
    private java.lang.String VnpaySrvSoap_address = "http://210.245.12.220:19061/SCB/VnpaySrv.asmx";

    public java.lang.String getVnpaySrvSoapAddress() {
        return VnpaySrvSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String VnpaySrvSoapWSDDServiceName = "VnpaySrvSoap";

    public java.lang.String getVnpaySrvSoapWSDDServiceName() {
        return VnpaySrvSoapWSDDServiceName;
    }

    public void setVnpaySrvSoapWSDDServiceName(java.lang.String name) {
        VnpaySrvSoapWSDDServiceName = name;
    }

    public VnpaySrvSoap getVnpaySrvSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(VnpaySrvSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getVnpaySrvSoap(endpoint);
    }

    public VnpaySrvSoap getVnpaySrvSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            VnpaySrvSoapStub _stub = new VnpaySrvSoapStub(portAddress, this);
            _stub.setPortName(getVnpaySrvSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setVnpaySrvSoapEndpointAddress(java.lang.String address) {
        VnpaySrvSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (VnpaySrvSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                VnpaySrvSoapStub _stub = new VnpaySrvSoapStub(new java.net.URL(VnpaySrvSoap_address), this);
                _stub.setPortName(getVnpaySrvSoapWSDDServiceName());
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
        if ("VnpaySrvSoap".equals(inputPortName)) {
            return getVnpaySrvSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "VnpaySrv");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "VnpaySrvSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("VnpaySrvSoap".equals(portName)) {
            setVnpaySrvSoapEndpointAddress(address);
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
