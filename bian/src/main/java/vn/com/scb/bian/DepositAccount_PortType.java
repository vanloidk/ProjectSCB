/**
 * DepositAccount_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public interface DepositAccount_PortType extends java.rmi.Remote {
    public vn.com.scb.bian.InitiateDepositAccountTD_out_type initiateDepositAccountTD(vn.com.scb.bian.InitiateDepositAccountTD_in_type parameters) throws java.rmi.RemoteException;
    public vn.com.scb.bian.RetrieveDepositAccountTD_out_Type retrieveDepositAccountTD(vn.com.scb.bian.RetrieveDepositAccountTD_in_Type parameters) throws java.rmi.RemoteException;
    public vn.com.scb.bian.SelectDepositAccountFromCIF_out_Type selectDepositAccountFromCIF(vn.com.scb.bian.SelectDepositAccountFromCIF_in_Type parameters) throws java.rmi.RemoteException;
    public vn.com.scb.bian.TerminateDepositAccount_out_Type terminateDepositAccount(vn.com.scb.bian.TerminateDepositAccount_in_Type parameters) throws java.rmi.RemoteException;
}
