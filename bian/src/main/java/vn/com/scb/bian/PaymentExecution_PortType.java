/**
 * PaymentExecution_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public interface PaymentExecution_PortType extends java.rmi.Remote {
    public vn.com.scb.bian.EvaluatePaymentExecution_out_Type evaluatePaymentExecution(vn.com.scb.bian.EvaluatePaymentExecution_in_Type parameters) throws java.rmi.RemoteException;
    public vn.com.scb.bian.ExecutePaymentTransactionExternal_out_Type executePaymentTransactionExternal(vn.com.scb.bian.ExecutePaymentTransactionExternal_in_Type parameters) throws java.rmi.RemoteException;
    public vn.com.scb.bian.ExecutePaymentTransactionInternal_out_Type executePaymentTransactionInternal(vn.com.scb.bian.ExecutePaymentTransactionInternal_in_Type parameters) throws java.rmi.RemoteException;
    public vn.com.scb.bian.RequestPaymentTransactionCancelation_out_Type requestPaymentTransactionCancelation(vn.com.scb.bian.RequestPaymentTransactionCancelation_in_Type parameters) throws java.rmi.RemoteException;
}
