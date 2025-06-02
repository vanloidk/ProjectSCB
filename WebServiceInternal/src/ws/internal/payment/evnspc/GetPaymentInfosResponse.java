/**
 * GetPaymentInfosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class GetPaymentInfosResponse  implements java.io.Serializable {
    private ws.internal.payment.evnspc.PaymentInfo[] getPaymentInfosResult;

    public GetPaymentInfosResponse() {
    }

    public GetPaymentInfosResponse(
           ws.internal.payment.evnspc.PaymentInfo[] getPaymentInfosResult) {
           this.getPaymentInfosResult = getPaymentInfosResult;
    }


    /**
     * Gets the getPaymentInfosResult value for this GetPaymentInfosResponse.
     * 
     * @return getPaymentInfosResult
     */
    public ws.internal.payment.evnspc.PaymentInfo[] getGetPaymentInfosResult() {
        return getPaymentInfosResult;
    }


    /**
     * Sets the getPaymentInfosResult value for this GetPaymentInfosResponse.
     * 
     * @param getPaymentInfosResult
     */
    public void setGetPaymentInfosResult(ws.internal.payment.evnspc.PaymentInfo[] getPaymentInfosResult) {
        this.getPaymentInfosResult = getPaymentInfosResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetPaymentInfosResponse)) return false;
        GetPaymentInfosResponse other = (GetPaymentInfosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getPaymentInfosResult==null && other.getGetPaymentInfosResult()==null) || 
             (this.getPaymentInfosResult!=null &&
              java.util.Arrays.equals(this.getPaymentInfosResult, other.getGetPaymentInfosResult())));
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
        if (getGetPaymentInfosResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGetPaymentInfosResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGetPaymentInfosResult(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPaymentInfosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetPaymentInfosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getPaymentInfosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetPaymentInfosResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "PaymentInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "PaymentInfo"));
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
