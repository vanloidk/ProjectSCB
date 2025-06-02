/**
 * CurrentAccount_v10_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public interface CurrentAccount_v10_PortType extends java.rmi.Remote {
    public vn.com.scb.bian.InitiateCurrentAccountCASA_out_Type initiateCurrentAccountCASA(vn.com.scb.bian.InitiateCurrentAccountCASA_in_Type parameters) throws java.rmi.RemoteException;
    public vn.com.scb.bian.RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASA(vn.com.scb.bian.RetrieveCurrentAccountCASA_in_Type parameters) throws java.rmi.RemoteException;
    public vn.com.scb.bian.SelectCurrentAccountFromCIF_out_Type selectCurrentAccountFromCIF(vn.com.scb.bian.SelectCurrentAccountFromCIF_in_Type parameters) throws java.rmi.RemoteException;
    public vn.com.scb.bian.TerminateCurrentAccount_out_Type terminateCurrentAccount(vn.com.scb.bian.TerminateCurrentAccount_in_Type parameters) throws java.rmi.RemoteException;
}
