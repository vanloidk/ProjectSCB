/**
 * WSBeanService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package scb.com.vn.ws.gatewayscb;

public interface WSBeanService extends javax.xml.rpc.Service {
    public java.lang.String getWSBeanAddress();

    public scb.com.vn.ws.gatewayscb.WSBean getWSBean() throws javax.xml.rpc.ServiceException;

    public scb.com.vn.ws.gatewayscb.WSBean getWSBean(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
