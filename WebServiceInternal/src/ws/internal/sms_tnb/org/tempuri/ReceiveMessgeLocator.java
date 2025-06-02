/**
 * ReceiveMessgeLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.sms_tnb.org.tempuri;

public class ReceiveMessgeLocator extends org.apache.axis.client.Service implements ws.internal.sms_tnb.org.tempuri.ReceiveMessge {

    public ReceiveMessgeLocator() {
    }


    public ReceiveMessgeLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ReceiveMessgeLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ReceiveMessgeSoap
    private java.lang.String ReceiveMessgeSoap_address = "http://192.168.17.60/ReceiverSMS/ReceiveMessge.asmx";

    public java.lang.String getReceiveMessgeSoapAddress() {
        return ReceiveMessgeSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ReceiveMessgeSoapWSDDServiceName = "ReceiveMessgeSoap";

    public java.lang.String getReceiveMessgeSoapWSDDServiceName() {
        return ReceiveMessgeSoapWSDDServiceName;
    }

    public void setReceiveMessgeSoapWSDDServiceName(java.lang.String name) {
        ReceiveMessgeSoapWSDDServiceName = name;
    }

    public ws.internal.sms_tnb.org.tempuri.ReceiveMessgeSoap getReceiveMessgeSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReceiveMessgeSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReceiveMessgeSoap(endpoint);
    }

    public ws.internal.sms_tnb.org.tempuri.ReceiveMessgeSoap getReceiveMessgeSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	ws.internal.sms_tnb.org.tempuri.ReceiveMessgeSoapStub _stub = new ws.internal.sms_tnb.org.tempuri.ReceiveMessgeSoapStub(portAddress, this);
            _stub.setPortName(getReceiveMessgeSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setReceiveMessgeSoapEndpointAddress(java.lang.String address) {
        ReceiveMessgeSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ws.internal.sms_tnb.org.tempuri.ReceiveMessgeSoap.class.isAssignableFrom(serviceEndpointInterface)) {
            	    ws.internal.sms_tnb.org.tempuri.ReceiveMessgeSoapStub _stub = new ws.internal.sms_tnb.org.tempuri.ReceiveMessgeSoapStub(new java.net.URL(ReceiveMessgeSoap_address), this);
                _stub.setPortName(getReceiveMessgeSoapWSDDServiceName());
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
        if ("ReceiveMessgeSoap".equals(inputPortName)) {
            return getReceiveMessgeSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ReceiveMessge");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ReceiveMessgeSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ReceiveMessgeSoap".equals(portName)) {
            setReceiveMessgeSoapEndpointAddress(address);
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
