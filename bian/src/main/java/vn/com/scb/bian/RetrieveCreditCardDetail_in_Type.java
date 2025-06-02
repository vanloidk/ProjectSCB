/**
 * RetrieveCreditCardDetail_in_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class RetrieveCreditCardDetail_in_Type  implements java.io.Serializable {
    private vn.com.scb.bian.TransactionInfoType transactionInfo;

    private vn.com.scb.bian.CardInfoType cardInfo;

    // Add a container for the root element
    public static class Container {
        public RetrieveCreditCardDetail_in_Type retrieveCreditCardDetail_in;
    }
    
    public RetrieveCreditCardDetail_in_Type() {
    }

    public RetrieveCreditCardDetail_in_Type(
           vn.com.scb.bian.TransactionInfoType transactionInfo,
           vn.com.scb.bian.CardInfoType cardInfo) {
           this.transactionInfo = transactionInfo;
           this.cardInfo = cardInfo;
    }


    /**
     * Gets the transactionInfo value for this RetrieveCreditCardDetail_in_Type.
     * 
     * @return transactionInfo
     */
    public vn.com.scb.bian.TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }


    /**
     * Sets the transactionInfo value for this RetrieveCreditCardDetail_in_Type.
     * 
     * @param transactionInfo
     */
    public void setTransactionInfo(vn.com.scb.bian.TransactionInfoType transactionInfo) {
        this.transactionInfo = transactionInfo;
    }


    /**
     * Gets the cardInfo value for this RetrieveCreditCardDetail_in_Type.
     * 
     * @return cardInfo
     */
    public vn.com.scb.bian.CardInfoType getCardInfo() {
        return cardInfo;
    }


    /**
     * Sets the cardInfo value for this RetrieveCreditCardDetail_in_Type.
     * 
     * @param cardInfo
     */
    public void setCardInfo(vn.com.scb.bian.CardInfoType cardInfo) {
        this.cardInfo = cardInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetrieveCreditCardDetail_in_Type)) return false;
        RetrieveCreditCardDetail_in_Type other = (RetrieveCreditCardDetail_in_Type) obj;
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
            ((this.cardInfo==null && other.getCardInfo()==null) || 
             (this.cardInfo!=null &&
              this.cardInfo.equals(other.getCardInfo())));
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
        if (getCardInfo() != null) {
            _hashCode += getCardInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetrieveCreditCardDetail_in_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "retrieveCreditCardDetail_in_Type"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "TransactionInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CardInfoType"));
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
