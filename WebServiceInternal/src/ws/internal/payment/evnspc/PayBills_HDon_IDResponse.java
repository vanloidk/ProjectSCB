/**
 * PayBills_HDon_IDResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class PayBills_HDon_IDResponse  implements java.io.Serializable {
    private ws.internal.payment.evnspc.PayBills_HDon_IDResponsePayBills_HDon_IDResult payBills_HDon_IDResult;

    public PayBills_HDon_IDResponse() {
    }

    public PayBills_HDon_IDResponse(
           ws.internal.payment.evnspc.PayBills_HDon_IDResponsePayBills_HDon_IDResult payBills_HDon_IDResult) {
           this.payBills_HDon_IDResult = payBills_HDon_IDResult;
    }


    /**
     * Gets the payBills_HDon_IDResult value for this PayBills_HDon_IDResponse.
     * 
     * @return payBills_HDon_IDResult
     */
    public ws.internal.payment.evnspc.PayBills_HDon_IDResponsePayBills_HDon_IDResult getPayBills_HDon_IDResult() {
        return payBills_HDon_IDResult;
    }


    /**
     * Sets the payBills_HDon_IDResult value for this PayBills_HDon_IDResponse.
     * 
     * @param payBills_HDon_IDResult
     */
    public void setPayBills_HDon_IDResult(ws.internal.payment.evnspc.PayBills_HDon_IDResponsePayBills_HDon_IDResult payBills_HDon_IDResult) {
        this.payBills_HDon_IDResult = payBills_HDon_IDResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PayBills_HDon_IDResponse)) return false;
        PayBills_HDon_IDResponse other = (PayBills_HDon_IDResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.payBills_HDon_IDResult==null && other.getPayBills_HDon_IDResult()==null) || 
             (this.payBills_HDon_IDResult!=null &&
              this.payBills_HDon_IDResult.equals(other.getPayBills_HDon_IDResult())));
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
        if (getPayBills_HDon_IDResult() != null) {
            _hashCode += getPayBills_HDon_IDResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PayBills_HDon_IDResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">PayBills_HDon_IDResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payBills_HDon_IDResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PayBills_HDon_IDResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>PayBills_HDon_IDResponse>PayBills_HDon_IDResult"));
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
