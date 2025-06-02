/**
 * TransactionReceiptInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class TransactionReceiptInfo  implements java.io.Serializable {
    private java.lang.String transactionSignatureType;

    private java.lang.String transactionReceipt;

    private ws.internal.tokenkey.tnb.AuthenticationFault transactionSignatureFault;

    public TransactionReceiptInfo() {
    }

    public TransactionReceiptInfo(
           java.lang.String transactionSignatureType,
           java.lang.String transactionReceipt,
           ws.internal.tokenkey.tnb.AuthenticationFault transactionSignatureFault) {
           this.transactionSignatureType = transactionSignatureType;
           this.transactionReceipt = transactionReceipt;
           this.transactionSignatureFault = transactionSignatureFault;
    }


    /**
     * Gets the transactionSignatureType value for this TransactionReceiptInfo.
     * 
     * @return transactionSignatureType
     */
    public java.lang.String getTransactionSignatureType() {
        return transactionSignatureType;
    }


    /**
     * Sets the transactionSignatureType value for this TransactionReceiptInfo.
     * 
     * @param transactionSignatureType
     */
    public void setTransactionSignatureType(java.lang.String transactionSignatureType) {
        this.transactionSignatureType = transactionSignatureType;
    }


    /**
     * Gets the transactionReceipt value for this TransactionReceiptInfo.
     * 
     * @return transactionReceipt
     */
    public java.lang.String getTransactionReceipt() {
        return transactionReceipt;
    }


    /**
     * Sets the transactionReceipt value for this TransactionReceiptInfo.
     * 
     * @param transactionReceipt
     */
    public void setTransactionReceipt(java.lang.String transactionReceipt) {
        this.transactionReceipt = transactionReceipt;
    }


    /**
     * Gets the transactionSignatureFault value for this TransactionReceiptInfo.
     * 
     * @return transactionSignatureFault
     */
    public ws.internal.tokenkey.tnb.AuthenticationFault getTransactionSignatureFault() {
        return transactionSignatureFault;
    }


    /**
     * Sets the transactionSignatureFault value for this TransactionReceiptInfo.
     * 
     * @param transactionSignatureFault
     */
    public void setTransactionSignatureFault(ws.internal.tokenkey.tnb.AuthenticationFault transactionSignatureFault) {
        this.transactionSignatureFault = transactionSignatureFault;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TransactionReceiptInfo)) return false;
        TransactionReceiptInfo other = (TransactionReceiptInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.transactionSignatureType==null && other.getTransactionSignatureType()==null) || 
             (this.transactionSignatureType!=null &&
              this.transactionSignatureType.equals(other.getTransactionSignatureType()))) &&
            ((this.transactionReceipt==null && other.getTransactionReceipt()==null) || 
             (this.transactionReceipt!=null &&
              this.transactionReceipt.equals(other.getTransactionReceipt()))) &&
            ((this.transactionSignatureFault==null && other.getTransactionSignatureFault()==null) || 
             (this.transactionSignatureFault!=null &&
              this.transactionSignatureFault.equals(other.getTransactionSignatureFault())));
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
        if (getTransactionSignatureType() != null) {
            _hashCode += getTransactionSignatureType().hashCode();
        }
        if (getTransactionReceipt() != null) {
            _hashCode += getTransactionReceipt().hashCode();
        }
        if (getTransactionSignatureFault() != null) {
            _hashCode += getTransactionSignatureFault().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TransactionReceiptInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "TransactionReceiptInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionSignatureType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionSignatureType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionReceipt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionReceipt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionSignatureFault");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionSignatureFault"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationFault"));
        elemField.setNillable(true);
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
