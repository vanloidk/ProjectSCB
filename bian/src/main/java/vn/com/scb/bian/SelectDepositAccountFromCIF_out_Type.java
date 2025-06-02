/**
 * SelectDepositAccountFromCIF_out_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import java.util.List;

public class SelectDepositAccountFromCIF_out_Type  implements java.io.Serializable {
    private vn.com.scb.bian.TransactionInfoType transactionInfo;

    private List<vn.com.scb.bian.AccountInfoType> accountInfo;

    /**
     * @return the accountInfo
     */
    public List<vn.com.scb.bian.AccountInfoType> getAccountInfo() {
        return accountInfo;
    }

    /**
     * @param accountInfo the accountInfo to set
     */
    public void setAccountInfo(List<vn.com.scb.bian.AccountInfoType> accountInfo) {
        this.accountInfo = accountInfo;
    }

     public static class Container {
        public SelectDepositAccountFromCIF_out_Type selectDepositAccountFromCIF_out;
    }
     
    public SelectDepositAccountFromCIF_out_Type() {
    }

    public SelectDepositAccountFromCIF_out_Type(
           vn.com.scb.bian.TransactionInfoType transactionInfo,
           List<vn.com.scb.bian.AccountInfoType> accountInfo) {
           this.transactionInfo = transactionInfo;
           this.accountInfo = accountInfo;
    }


    /**
     * Gets the transactionInfo value for this SelectDepositAccountFromCIF_out_Type.
     * 
     * @return transactionInfo
     */
    public vn.com.scb.bian.TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }


    /**
     * Sets the transactionInfo value for this SelectDepositAccountFromCIF_out_Type.
     * 
     * @param transactionInfo
     */
    public void setTransactionInfo(vn.com.scb.bian.TransactionInfoType transactionInfo) {
        this.transactionInfo = transactionInfo;
    }
  

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectDepositAccountFromCIF_out_Type)) return false;
        SelectDepositAccountFromCIF_out_Type other = (SelectDepositAccountFromCIF_out_Type) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.transactionInfo==null && other.getTransactionInfo()==null) || 
             (this.transactionInfo!=null &&
              this.transactionInfo.equals(other.getTransactionInfo()))) &&
            ((this.getAccountInfo()==null && other.getAccountInfo()==null) || 
             (this.getAccountInfo()!=null &&
                this.getAccountInfo().equals(other.getAccountInfo())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getTransactionInfo() != null) {
            _hashCode += getTransactionInfo().hashCode();
        }
        if (getAccountInfo() != null) {
            _hashCode += getAccountInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SelectDepositAccountFromCIF_out_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "selectDepositAccountFromCIF_out_Type"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "TransactionInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "AccountInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
