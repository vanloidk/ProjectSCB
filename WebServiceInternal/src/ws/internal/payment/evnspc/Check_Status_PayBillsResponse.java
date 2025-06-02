/**
 * Check_Status_PayBillsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class Check_Status_PayBillsResponse  implements java.io.Serializable {
    private ws.internal.payment.evnspc.Check_Status_PayBillsResponseCheck_Status_PayBillsResult check_Status_PayBillsResult;

    public Check_Status_PayBillsResponse() {
    }

    public Check_Status_PayBillsResponse(
           ws.internal.payment.evnspc.Check_Status_PayBillsResponseCheck_Status_PayBillsResult check_Status_PayBillsResult) {
           this.check_Status_PayBillsResult = check_Status_PayBillsResult;
    }


    /**
     * Gets the check_Status_PayBillsResult value for this Check_Status_PayBillsResponse.
     * 
     * @return check_Status_PayBillsResult
     */
    public ws.internal.payment.evnspc.Check_Status_PayBillsResponseCheck_Status_PayBillsResult getCheck_Status_PayBillsResult() {
        return check_Status_PayBillsResult;
    }


    /**
     * Sets the check_Status_PayBillsResult value for this Check_Status_PayBillsResponse.
     * 
     * @param check_Status_PayBillsResult
     */
    public void setCheck_Status_PayBillsResult(ws.internal.payment.evnspc.Check_Status_PayBillsResponseCheck_Status_PayBillsResult check_Status_PayBillsResult) {
        this.check_Status_PayBillsResult = check_Status_PayBillsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Check_Status_PayBillsResponse)) return false;
        Check_Status_PayBillsResponse other = (Check_Status_PayBillsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.check_Status_PayBillsResult==null && other.getCheck_Status_PayBillsResult()==null) || 
             (this.check_Status_PayBillsResult!=null &&
              this.check_Status_PayBillsResult.equals(other.getCheck_Status_PayBillsResult())));
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
        if (getCheck_Status_PayBillsResult() != null) {
            _hashCode += getCheck_Status_PayBillsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Check_Status_PayBillsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Check_Status_PayBillsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("check_Status_PayBillsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Check_Status_PayBillsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>Check_Status_PayBillsResponse>Check_Status_PayBillsResult"));
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
