/**
 * SCBWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.tvsi.www;

public interface SCBWS extends javax.xml.rpc.Service {
    public java.lang.String getSCBWSSoap12Address();

    public vn.com.tvsi.www.SCBWSSoap getSCBWSSoap12() throws javax.xml.rpc.ServiceException;

    public vn.com.tvsi.www.SCBWSSoap getSCBWSSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getSCBWSSoapAddress();

    public vn.com.tvsi.www.SCBWSSoap getSCBWSSoap() throws javax.xml.rpc.ServiceException;

    public vn.com.tvsi.www.SCBWSSoap getSCBWSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
