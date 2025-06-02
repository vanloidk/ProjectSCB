/**
 * Loan_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public interface Loan_PortType extends java.rmi.Remote {
    public vn.com.scb.bian.RetrieveLoanSumary_out_Type retrieveLoanSumary(vn.com.scb.bian.RetrieveLoanSumary_in_Type parameters) throws java.rmi.RemoteException;
    public vn.com.scb.bian.SelectLoanFromCIF_out_Type selectLoanFromCIF(vn.com.scb.bian.SelectLoanFromCIF_in_Type parameters) throws java.rmi.RemoteException;
}
