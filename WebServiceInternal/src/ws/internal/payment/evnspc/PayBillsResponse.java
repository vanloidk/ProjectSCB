/**
 * PayBillsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class PayBillsResponse  implements java.io.Serializable {
    private ws.internal.payment.evnspc.PayBillsResponsePayBillsResult payBillsResult;

    public PayBillsResponse() {
    }

    public PayBillsResponse(
           ws.internal.payment.evnspc.PayBillsResponsePayBillsResult payBillsResult) {
           this.payBillsResult = payBillsResult;
    }


    /**
     * Gets the payBillsResult value for this PayBillsResponse.
     * 
     * @return payBillsResult
     */
    public ws.internal.payment.evnspc.PayBillsResponsePayBillsResult getPayBillsResult() {
        return payBillsResult;
    }


    /**
     * Sets the payBillsResult value for this PayBillsResponse.
     * 
     * @param payBillsResult
     */
    public void setPayBillsResult(ws.internal.payment.evnspc.PayBillsResponsePayBillsResult payBillsResult) {
        this.payBillsResult = payBillsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PayBillsResponse)) return false;
        PayBillsResponse other = (PayBillsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.payBillsResult==null && other.getPayBillsResult()==null) || 
             (this.payBillsResult!=null &&
              this.payBillsResult.equals(other.getPayBillsResult())));
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
        if (getPayBillsResult() != null) {
            _hashCode += getPayBillsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PayBillsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">PayBillsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payBillsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PayBillsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>PayBillsResponse>PayBillsResult"));
        elemField.setMinOccurs(0);
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
