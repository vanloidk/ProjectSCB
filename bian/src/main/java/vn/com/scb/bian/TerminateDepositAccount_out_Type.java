/**
 * TerminateDepositAccount_out_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class TerminateDepositAccount_out_Type  implements java.io.Serializable {
    private vn.com.scb.bian.TransactionInfoType transactionInfo;

    private vn.com.scb.bian.AccountInfoType destinationAccount;

    private vn.com.scb.bian.AccountInfoType sourceAccount;

    private vn.com.scb.bian.AccountInfoType accountInfo;

    private vn.com.scb.bian.CIFDataType CIFInfo;

    public TerminateDepositAccount_out_Type() {
    }
    
    // Add a container for the root element
    public static class Container {
        public TerminateDepositAccount_out_Type terminateDepositAccount_out;
    }

    public TerminateDepositAccount_out_Type(
           vn.com.scb.bian.TransactionInfoType transactionInfo,
           vn.com.scb.bian.AccountInfoType destinationAccount,
           vn.com.scb.bian.AccountInfoType sourceAccount,
           vn.com.scb.bian.AccountInfoType accountInfo,
           vn.com.scb.bian.CIFDataType CIFInfo) {
           this.transactionInfo = transactionInfo;
           this.destinationAccount = destinationAccount;
           this.sourceAccount = sourceAccount;
           this.accountInfo = accountInfo;
           this.CIFInfo = CIFInfo;
    }


    /**
     * Gets the transactionInfo value for this TerminateDepositAccount_out_Type.
     * 
     * @return transactionInfo
     */
    public vn.com.scb.bian.TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }


    /**
     * Sets the transactionInfo value for this TerminateDepositAccount_out_Type.
     * 
     * @param transactionInfo
     */
    public void setTransactionInfo(vn.com.scb.bian.TransactionInfoType transactionInfo) {
        this.transactionInfo = transactionInfo;
    }


    /**
     * Gets the destinationAccount value for this TerminateDepositAccount_out_Type.
     * 
     * @return destinationAccount
     */
    public vn.com.scb.bian.AccountInfoType getDestinationAccount() {
        return destinationAccount;
    }


    /**
     * Sets the destinationAccount value for this TerminateDepositAccount_out_Type.
     * 
     * @param destinationAccount
     */
    public void setDestinationAccount(vn.com.scb.bian.AccountInfoType destinationAccount) {
        this.destinationAccount = destinationAccount;
    }


    /**
     * Gets the sourceAccount value for this TerminateDepositAccount_out_Type.
     * 
     * @return sourceAccount
     */
    public vn.com.scb.bian.AccountInfoType getSourceAccount() {
        return sourceAccount;
    }


    /**
     * Sets the sourceAccount value for this TerminateDepositAccount_out_Type.
     * 
     * @param sourceAccount
     */
    public void setSourceAccount(vn.com.scb.bian.AccountInfoType sourceAccount) {
        this.sourceAccount = sourceAccount;
    }


    /**
     * Gets the accountInfo value for this TerminateDepositAccount_out_Type.
     * 
     * @return accountInfo
     */
    public vn.com.scb.bian.AccountInfoType getAccountInfo() {
        return accountInfo;
    }


    /**
     * Sets the accountInfo value for this TerminateDepositAccount_out_Type.
     * 
     * @param accountInfo
     */
    public void setAccountInfo(vn.com.scb.bian.AccountInfoType accountInfo) {
        this.accountInfo = accountInfo;
    }


    /**
     * Gets the CIFInfo value for this TerminateDepositAccount_out_Type.
     * 
     * @return CIFInfo
     */
    public vn.com.scb.bian.CIFDataType getCIFInfo() {
        return CIFInfo;
    }


    /**
     * Sets the CIFInfo value for this TerminateDepositAccount_out_Type.
     * 
     * @param CIFInfo
     */
    public void setCIFInfo(vn.com.scb.bian.CIFDataType CIFInfo) {
        this.CIFInfo = CIFInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TerminateDepositAccount_out_Type)) return false;
        TerminateDepositAccount_out_Type other = (TerminateDepositAccount_out_Type) obj;
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
            ((this.destinationAccount==null && other.getDestinationAccount()==null) || 
             (this.destinationAccount!=null &&
              this.destinationAccount.equals(other.getDestinationAccount()))) &&
            ((this.sourceAccount==null && other.getSourceAccount()==null) || 
             (this.sourceAccount!=null &&
              this.sourceAccount.equals(other.getSourceAccount()))) &&
            ((this.accountInfo==null && other.getAccountInfo()==null) || 
             (this.accountInfo!=null &&
              this.accountInfo.equals(other.getAccountInfo()))) &&
            ((this.CIFInfo==null && other.getCIFInfo()==null) || 
             (this.CIFInfo!=null &&
              this.CIFInfo.equals(other.getCIFInfo())));
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
        if (getDestinationAccount() != null) {
            _hashCode += getDestinationAccount().hashCode();
        }
        if (getSourceAccount() != null) {
            _hashCode += getSourceAccount().hashCode();
        }
        if (getAccountInfo() != null) {
            _hashCode += getAccountInfo().hashCode();
        }
        if (getCIFInfo() != null) {
            _hashCode += getCIFInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TerminateDepositAccount_out_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "terminateDepositAccount_out_Type"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "TransactionInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinationAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "AccountInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "AccountInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "AccountInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CIFInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CIFInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CIFDataType"));
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
