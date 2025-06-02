/**
 * GetBillInfo_in_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class GetBillInfo_in_Type  implements java.io.Serializable {
    private vn.com.scb.bian.TransactionInfoType transactionInfo;

    private vn.com.scb.bian.AccountInfoType sourceAccount;
    

    private vn.com.scb.bian.BillPaymentInfoType billPaymentInfo;

    /**
     * @return the billPaymentInfo
     */
    public vn.com.scb.bian.BillPaymentInfoType getBillPaymentInfo() {
        return billPaymentInfo;
    }

    /**
     * @param billPaymentInfo the billPaymentInfo to set
     */
    public void setBillPaymentInfo(vn.com.scb.bian.BillPaymentInfoType billPaymentInfo) {
        this.billPaymentInfo = billPaymentInfo;
    }

   
      // Add a container for the root element
    public static class Container {
        public GetBillInfo_in_Type getBillInfo_in;
    }
    
    public GetBillInfo_in_Type() {
    }

    public GetBillInfo_in_Type(
           vn.com.scb.bian.TransactionInfoType transactionInfo,
           vn.com.scb.bian.AccountInfoType sourceAccount,
           vn.com.scb.bian.BillPaymentInfoType billPaymentInfo) {
           this.transactionInfo = transactionInfo;
           this.sourceAccount = sourceAccount;
            this.billPaymentInfo = billPaymentInfo;
          }


    /**
     * Gets the transactionInfo value for this GetBillInfo_in_Type.
     * 
     * @return transactionInfo
     */
    public vn.com.scb.bian.TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }


    /**
     * Sets the transactionInfo value for this GetBillInfo_in_Type.
     * 
     * @param transactionInfo
     */
    public void setTransactionInfo(vn.com.scb.bian.TransactionInfoType transactionInfo) {
        this.transactionInfo = transactionInfo;
    }


    /**
     * Gets the sourceAccount value for this GetBillInfo_in_Type.
     * 
     * @return sourceAccount
     */
    public vn.com.scb.bian.AccountInfoType getSourceAccount() {
        return sourceAccount;
    }


    /**
     * Sets the sourceAccount value for this GetBillInfo_in_Type.
     * 
     * @param sourceAccount
     */
    public void setSourceAccount(vn.com.scb.bian.AccountInfoType sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
     
    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBillInfo_in_Type)) return false;
        GetBillInfo_in_Type other = (GetBillInfo_in_Type) obj;
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
              this.sourceAccount.equals(other.getSourceAccount()))) ;
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
        
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetBillInfo_in_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "getBillInfo_in_Type"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billPaymentInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "billPaymentInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "BillPaymentInfoType"));
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
