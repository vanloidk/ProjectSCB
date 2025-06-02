/**
 * Bill_ServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.dawaco;

public interface Bill_ServiceSoap extends java.rmi.Remote {
    public java.lang.String DEBT_CHECK(java.lang.String CUST_ID, java.lang.String AGENT_CODE, java.lang.String USR, java.lang.String PASS) throws java.rmi.RemoteException;
    public java.lang.String DEBT_PAYMENT(java.lang.String CUST_ID, int PAY_MONEY, java.lang.String PAID_DATE, int PAY_F, java.lang.String TRANS_ID, java.lang.String AGENT_CODE, java.lang.String USR, java.lang.String PASS) throws java.rmi.RemoteException;
    public java.lang.String DEBT_CANCEL(java.lang.String CUST_ID, int PAY_MONEY, java.lang.String PAID_DATE, java.lang.String TRANS_ID, java.lang.String AGENT_CODE, java.lang.String USR, java.lang.String PASS) throws java.rmi.RemoteException;
    public java.lang.String INFOCUST_CHECK(java.lang.String CUST_ID, java.lang.String CUST_NAME, java.lang.String CUST_ADD, java.lang.String AGENT_CODE, java.lang.String USR, java.lang.String PASS) throws java.rmi.RemoteException;
}
