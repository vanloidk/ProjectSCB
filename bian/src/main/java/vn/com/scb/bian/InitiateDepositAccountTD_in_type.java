/**
 * InitiateDepositAccountTD_in_type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import com.google.gson.annotations.Expose;

public class InitiateDepositAccountTD_in_type  implements java.io.Serializable {
    @Expose
    private vn.com.scb.bian.CIFDataType CIFInfo;
    @Expose
    private vn.com.scb.bian.AccountInfoType accountInfo;
    
    private vn.com.scb.bian.CoreBankAccountType coreBankAccount;
    @Expose
    private vn.com.scb.bian.TransactionInfoType transactionInfo;
    @Expose
    private vn.com.scb.bian.CustomerInfoType customerInfo;

    public static class Container {
    @Expose
    public InitiateDepositAccountTD_in_type initiateDepositAccountTD_in;
    }  
    
    
	
    public InitiateDepositAccountTD_in_type() {
    }

    public InitiateDepositAccountTD_in_type(
           vn.com.scb.bian.CIFDataType CIFInfo,
           vn.com.scb.bian.AccountInfoType accountInfo,
           vn.com.scb.bian.CoreBankAccountType coreBankAccount,
           vn.com.scb.bian.TransactionInfoType transactionInfo,
           vn.com.scb.bian.CustomerInfoType customerInfo) {
           this.CIFInfo = CIFInfo;
           this.accountInfo = accountInfo;
           this.coreBankAccount = coreBankAccount;
           this.transactionInfo = transactionInfo;
           this.customerInfo = customerInfo;
    }


    /**
     * Gets the CIFInfo value for this InitiateDepositAccountTD_in_type.
     * 
     * @return CIFInfo
     */
    public vn.com.scb.bian.CIFDataType getCIFInfo() {
        return CIFInfo;
    }


    /**
     * Sets the CIFInfo value for this InitiateDepositAccountTD_in_type.
     * 
     * @param CIFInfo
     */
    public void setCIFInfo(vn.com.scb.bian.CIFDataType CIFInfo) {
        this.CIFInfo = CIFInfo;
    }


    /**
     * Gets the accountInfo value for this InitiateDepositAccountTD_in_type.
     * 
     * @return accountInfo
     */
    public vn.com.scb.bian.AccountInfoType getAccountInfo() {
        return accountInfo;
    }


    /**
     * Sets the accountInfo value for this InitiateDepositAccountTD_in_type.
     * 
     * @param accountInfo
     */
    public void setAccountInfo(vn.com.scb.bian.AccountInfoType accountInfo) {
        this.accountInfo = accountInfo;
    }


    /**
     * Gets the coreBankAccount value for this InitiateDepositAccountTD_in_type.
     * 
     * @return coreBankAccount
     */
    public vn.com.scb.bian.CoreBankAccountType getCoreBankAccount() {
        return coreBankAccount;
    }


    /**
     * Sets the coreBankAccount value for this InitiateDepositAccountTD_in_type.
     * 
     * @param coreBankAccount
     */
    public void setCoreBankAccount(vn.com.scb.bian.CoreBankAccountType coreBankAccount) {
        this.coreBankAccount = coreBankAccount;
    }


    /**
     * Gets the transactionInfo value for this InitiateDepositAccountTD_in_type.
     * 
     * @return transactionInfo
     */
    public vn.com.scb.bian.TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }


    /**
     * Sets the transactionInfo value for this InitiateDepositAccountTD_in_type.
     * 
     * @param transactionInfo
     */
    public void setTransactionInfo(vn.com.scb.bian.TransactionInfoType transactionInfo) {
        this.transactionInfo = transactionInfo;
    }


    /**
     * Gets the customerInfo value for this InitiateDepositAccountTD_in_type.
     * 
     * @return customerInfo
     */
    public vn.com.scb.bian.CustomerInfoType getCustomerInfo() {
        return customerInfo;
    }


    /**
     * Sets the customerInfo value for this InitiateDepositAccountTD_in_type.
     * 
     * @param customerInfo
     */
    public void setCustomerInfo(vn.com.scb.bian.CustomerInfoType customerInfo) {
        this.customerInfo = customerInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InitiateDepositAccountTD_in_type)) return false;
        InitiateDepositAccountTD_in_type other = (InitiateDepositAccountTD_in_type) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CIFInfo==null && other.getCIFInfo()==null) || 
             (this.CIFInfo!=null &&
              this.CIFInfo.equals(other.getCIFInfo()))) &&
            ((this.accountInfo==null && other.getAccountInfo()==null) || 
             (this.accountInfo!=null &&
              this.accountInfo.equals(other.getAccountInfo()))) &&
            ((this.coreBankAccount==null && other.getCoreBankAccount()==null) || 
             (this.coreBankAccount!=null &&
              this.coreBankAccount.equals(other.getCoreBankAccount()))) &&
            ((this.transactionInfo==null && other.getTransactionInfo()==null) || 
             (this.transactionInfo!=null &&
              this.transactionInfo.equals(other.getTransactionInfo()))) &&
            ((this.customerInfo==null && other.getCustomerInfo()==null) || 
             (this.customerInfo!=null &&
              this.customerInfo.equals(other.getCustomerInfo())));
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
        if (getCIFInfo() != null) {
            _hashCode += getCIFInfo().hashCode();
        }
        if (getAccountInfo() != null) {
            _hashCode += getAccountInfo().hashCode();
        }
        if (getCoreBankAccount() != null) {
            _hashCode += getCoreBankAccount().hashCode();
        }
        if (getTransactionInfo() != null) {
            _hashCode += getTransactionInfo().hashCode();
        }
        if (getCustomerInfo() != null) {
            _hashCode += getCustomerInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InitiateDepositAccountTD_in_type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "initiateDepositAccountTD_in_type"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CIFInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CIFInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CIFDataType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "AccountInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coreBankAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "coreBankAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CoreBankAccountType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "TransactionInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customerInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CustomerInfoType"));
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
