/**
 * Check_Status_BillsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class Check_Status_BillsResponse  implements java.io.Serializable {
    private ws.internal.payment.evnspc.Check_Status_BillsResponseCheck_Status_BillsResult check_Status_BillsResult;

    public Check_Status_BillsResponse() {
    }

    public Check_Status_BillsResponse(
           ws.internal.payment.evnspc.Check_Status_BillsResponseCheck_Status_BillsResult check_Status_BillsResult) {
           this.check_Status_BillsResult = check_Status_BillsResult;
    }


    /**
     * Gets the check_Status_BillsResult value for this Check_Status_BillsResponse.
     * 
     * @return check_Status_BillsResult
     */
    public ws.internal.payment.evnspc.Check_Status_BillsResponseCheck_Status_BillsResult getCheck_Status_BillsResult() {
        return check_Status_BillsResult;
    }


    /**
     * Sets the check_Status_BillsResult value for this Check_Status_BillsResponse.
     * 
     * @param check_Status_BillsResult
     */
    public void setCheck_Status_BillsResult(ws.internal.payment.evnspc.Check_Status_BillsResponseCheck_Status_BillsResult check_Status_BillsResult) {
        this.check_Status_BillsResult = check_Status_BillsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Check_Status_BillsResponse)) return false;
        Check_Status_BillsResponse other = (Check_Status_BillsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.check_Status_BillsResult==null && other.getCheck_Status_BillsResult()==null) || 
             (this.check_Status_BillsResult!=null &&
              this.check_Status_BillsResult.equals(other.getCheck_Status_BillsResult())));
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
        if (getCheck_Status_BillsResult() != null) {
            _hashCode += getCheck_Status_BillsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Check_Status_BillsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Check_Status_BillsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("check_Status_BillsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Check_Status_BillsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>Check_Status_BillsResponse>Check_Status_BillsResult"));
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
