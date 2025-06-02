/**
 * CheckBillResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class CheckBillResponse  implements java.io.Serializable {
    private org.tempuri.CheckBillResponseCheckBillResult checkBillResult;

    public CheckBillResponse() {
    }

    public CheckBillResponse(
           org.tempuri.CheckBillResponseCheckBillResult checkBillResult) {
           this.checkBillResult = checkBillResult;
    }


    /**
     * Gets the checkBillResult value for this CheckBillResponse.
     * 
     * @return checkBillResult
     */
    public org.tempuri.CheckBillResponseCheckBillResult getCheckBillResult() {
        return checkBillResult;
    }


    /**
     * Sets the checkBillResult value for this CheckBillResponse.
     * 
     * @param checkBillResult
     */
    public void setCheckBillResult(org.tempuri.CheckBillResponseCheckBillResult checkBillResult) {
        this.checkBillResult = checkBillResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckBillResponse)) return false;
        CheckBillResponse other = (CheckBillResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.checkBillResult==null && other.getCheckBillResult()==null) || 
             (this.checkBillResult!=null &&
              this.checkBillResult.equals(other.getCheckBillResult())));
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
        if (getCheckBillResult() != null) {
            _hashCode += getCheckBillResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckBillResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">CheckBillResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkBillResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CheckBillResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>CheckBillResponse>CheckBillResult"));
        elemField.setMinOccurs(0);
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
