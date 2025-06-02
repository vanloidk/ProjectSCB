/**
 * InitiateCurrentAccountCASA_in_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import com.google.gson.annotations.Expose;

public class CreateRewardNumber_in_Type  implements java.io.Serializable {
    private vn.com.scb.bian.TransactionInfoType transactionInfo;   

    private vn.com.scb.bian.AccountInfoType accountInfo;
    
    private vn.com.scb.bian.RewardNumInfoType rewardNumInfo;

    /**
     * @return the rewardNumInfo
     */
    public vn.com.scb.bian.RewardNumInfoType getRewardNumInfo() {
        return rewardNumInfo;
    }

    /**
     * @param rewardNumInfo the rewardNumInfo to set
     */
    public void setRewardNumInfo(vn.com.scb.bian.RewardNumInfoType rewardNumInfo) {
        this.rewardNumInfo = rewardNumInfo;
    }
    
    public static class Container {
    @Expose
    public CreateRewardNumber_in_Type createRewardNumber_in;
    }  
    public CreateRewardNumber_in_Type() {
    }

    public CreateRewardNumber_in_Type(
           vn.com.scb.bian.TransactionInfoType transactionInfo,
           vn.com.scb.bian.CIFDataType CIFInfo,
           vn.com.scb.bian.AccountInfoType accountInfo,
           vn.com.scb.bian.CoreBankAccountType coreBankAccount,
           vn.com.scb.bian.RewardNumInfoType rewardNumInfo) {
           this.transactionInfo = transactionInfo;          
           this.accountInfo = accountInfo;           
    }


    /**
     * Gets the transactionInfo value for this InitiateCurrentAccountCASA_in_Type.
     * 
     * @return transactionInfo
     */
    public vn.com.scb.bian.TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }


    /**
     * Sets the transactionInfo value for this InitiateCurrentAccountCASA_in_Type.
     * 
     * @param transactionInfo
     */
    public void setTransactionInfo(vn.com.scb.bian.TransactionInfoType transactionInfo) {
        this.transactionInfo = transactionInfo;
    }


   

    /**
     * Gets the accountInfo value for this InitiateCurrentAccountCASA_in_Type.
     * 
     * @return accountInfo
     */
    public vn.com.scb.bian.AccountInfoType getAccountInfo() {
        return accountInfo;
    }


    /**
     * Sets the accountInfo value for this InitiateCurrentAccountCASA_in_Type.
     * 
     * @param accountInfo
     */
    public void setAccountInfo(vn.com.scb.bian.AccountInfoType accountInfo) {
        this.accountInfo = accountInfo;
    }


 
    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateRewardNumber_in_Type)) return false;
        CreateRewardNumber_in_Type other = (CreateRewardNumber_in_Type) obj;
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
        
            ((this.accountInfo==null && other.getAccountInfo()==null) || 
             (this.accountInfo!=null &&
              this.accountInfo.equals(other.getAccountInfo()))) &&
           
         ((this.rewardNumInfo==null && other.getRewardNumInfo()==null) || 
             (this.rewardNumInfo!=null &&
              this.rewardNumInfo.equals(other.getRewardNumInfo()))) ;
           
         
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
      
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateRewardNumber_in_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "initiateCurrentAccountCASA_in_Type"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "TransactionInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("rewardNumInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rewardNumInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "RewardNumInfoType"));
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
