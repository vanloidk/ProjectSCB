/**
 * ExecutePaymentTransactionInternal_in_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class ExecutePaymentTransactionInternal_in_Type  implements java.io.Serializable {
    private vn.com.scb.bian.TransactionInfoType transactionInfo;

    private vn.com.scb.bian.AccountInfoType sourceAccount;

    private vn.com.scb.bian.AccountInfoType destinationAccount;

    private vn.com.scb.bian.FundTransferInfoType fundTransferInfo;

    private vn.com.scb.bian.BenificialInfoType benificialInfo;

    private vn.com.scb.bian.CoreBankAccountType coreBankAccount;

    private vn.com.scb.bian.SenderInfoType senderInfo;

      // Add a container for the root element
    public static class Container {
        public ExecutePaymentTransactionInternal_in_Type executePaymentTransactionInternal_in;
    }
    
    public ExecutePaymentTransactionInternal_in_Type() {
    }

    public ExecutePaymentTransactionInternal_in_Type(
           vn.com.scb.bian.TransactionInfoType transactionInfo,
           vn.com.scb.bian.AccountInfoType sourceAccount,
           vn.com.scb.bian.AccountInfoType destinationAccount,
           vn.com.scb.bian.FundTransferInfoType fundTransferInfo,
           vn.com.scb.bian.BenificialInfoType benificialInfo,
           vn.com.scb.bian.CoreBankAccountType coreBankAccount,
           vn.com.scb.bian.SenderInfoType senderInfo) {
           this.transactionInfo = transactionInfo;
           this.sourceAccount = sourceAccount;
           this.destinationAccount = destinationAccount;
           this.fundTransferInfo = fundTransferInfo;
           this.benificialInfo = benificialInfo;
           this.coreBankAccount = coreBankAccount;
           this.senderInfo = senderInfo;
    }


    /**
     * Gets the transactionInfo value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @return transactionInfo
     */
    public vn.com.scb.bian.TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }


    /**
     * Sets the transactionInfo value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @param transactionInfo
     */
    public void setTransactionInfo(vn.com.scb.bian.TransactionInfoType transactionInfo) {
        this.transactionInfo = transactionInfo;
    }


    /**
     * Gets the sourceAccount value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @return sourceAccount
     */
    public vn.com.scb.bian.AccountInfoType getSourceAccount() {
        return sourceAccount;
    }


    /**
     * Sets the sourceAccount value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @param sourceAccount
     */
    public void setSourceAccount(vn.com.scb.bian.AccountInfoType sourceAccount) {
        this.sourceAccount = sourceAccount;
    }


    /**
     * Gets the destinationAccount value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @return destinationAccount
     */
    public vn.com.scb.bian.AccountInfoType getDestinationAccount() {
        return destinationAccount;
    }


    /**
     * Sets the destinationAccount value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @param destinationAccount
     */
    public void setDestinationAccount(vn.com.scb.bian.AccountInfoType destinationAccount) {
        this.destinationAccount = destinationAccount;
    }


    /**
     * Gets the fundTransferInfo value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @return fundTransferInfo
     */
    public vn.com.scb.bian.FundTransferInfoType getFundTransferInfo() {
        return fundTransferInfo;
    }


    /**
     * Sets the fundTransferInfo value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @param fundTransferInfo
     */
    public void setFundTransferInfo(vn.com.scb.bian.FundTransferInfoType fundTransferInfo) {
        this.fundTransferInfo = fundTransferInfo;
    }


    /**
     * Gets the benificialInfo value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @return benificialInfo
     */
    public vn.com.scb.bian.BenificialInfoType getBenificialInfo() {
        return benificialInfo;
    }


    /**
     * Sets the benificialInfo value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @param benificialInfo
     */
    public void setBenificialInfo(vn.com.scb.bian.BenificialInfoType benificialInfo) {
        this.benificialInfo = benificialInfo;
    }


    /**
     * Gets the coreBankAccount value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @return coreBankAccount
     */
    public vn.com.scb.bian.CoreBankAccountType getCoreBankAccount() {
        return coreBankAccount;
    }


    /**
     * Sets the coreBankAccount value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @param coreBankAccount
     */
    public void setCoreBankAccount(vn.com.scb.bian.CoreBankAccountType coreBankAccount) {
        this.coreBankAccount = coreBankAccount;
    }


    /**
     * Gets the senderInfo value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @return senderInfo
     */
    public vn.com.scb.bian.SenderInfoType getSenderInfo() {
        return senderInfo;
    }


    /**
     * Sets the senderInfo value for this ExecutePaymentTransactionInternal_in_Type.
     * 
     * @param senderInfo
     */
    public void setSenderInfo(vn.com.scb.bian.SenderInfoType senderInfo) {
        this.senderInfo = senderInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExecutePaymentTransactionInternal_in_Type)) return false;
        ExecutePaymentTransactionInternal_in_Type other = (ExecutePaymentTransactionInternal_in_Type) obj;
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
            ((this.sourceAccount==null && other.getSourceAccount()==null) || 
             (this.sourceAccount!=null &&
              this.sourceAccount.equals(other.getSourceAccount()))) &&
            ((this.destinationAccount==null && other.getDestinationAccount()==null) || 
             (this.destinationAccount!=null &&
              this.destinationAccount.equals(other.getDestinationAccount()))) &&
            ((this.fundTransferInfo==null && other.getFundTransferInfo()==null) || 
             (this.fundTransferInfo!=null &&
              this.fundTransferInfo.equals(other.getFundTransferInfo()))) &&
            ((this.benificialInfo==null && other.getBenificialInfo()==null) || 
             (this.benificialInfo!=null &&
              this.benificialInfo.equals(other.getBenificialInfo()))) &&
            ((this.coreBankAccount==null && other.getCoreBankAccount()==null) || 
             (this.coreBankAccount!=null &&
              this.coreBankAccount.equals(other.getCoreBankAccount()))) &&
            ((this.senderInfo==null && other.getSenderInfo()==null) || 
             (this.senderInfo!=null &&
              this.senderInfo.equals(other.getSenderInfo())));
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
        if (getSourceAccount() != null) {
            _hashCode += getSourceAccount().hashCode();
        }
        if (getDestinationAccount() != null) {
            _hashCode += getDestinationAccount().hashCode();
        }
        if (getFundTransferInfo() != null) {
            _hashCode += getFundTransferInfo().hashCode();
        }
        if (getBenificialInfo() != null) {
            _hashCode += getBenificialInfo().hashCode();
        }
        if (getCoreBankAccount() != null) {
            _hashCode += getCoreBankAccount().hashCode();
        }
        if (getSenderInfo() != null) {
            _hashCode += getSenderInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExecutePaymentTransactionInternal_in_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionInternal_in_Type"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "TransactionInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "AccountInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinationAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "AccountInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "FundTransferInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("benificialInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "benificialInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "BenificialInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coreBankAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "coreBankAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CoreBankAccountType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "SenderInfoType"));
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
