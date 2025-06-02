/**
 * GetHanMucTienThuResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class GetHanMucTienThuResponse  implements java.io.Serializable {
    private org.tempuri.GetHanMucTienThuResponseGetHanMucTienThuResult getHanMucTienThuResult;

    public GetHanMucTienThuResponse() {
    }

    public GetHanMucTienThuResponse(
           org.tempuri.GetHanMucTienThuResponseGetHanMucTienThuResult getHanMucTienThuResult) {
           this.getHanMucTienThuResult = getHanMucTienThuResult;
    }


    /**
     * Gets the getHanMucTienThuResult value for this GetHanMucTienThuResponse.
     * 
     * @return getHanMucTienThuResult
     */
    public org.tempuri.GetHanMucTienThuResponseGetHanMucTienThuResult getGetHanMucTienThuResult() {
        return getHanMucTienThuResult;
    }


    /**
     * Sets the getHanMucTienThuResult value for this GetHanMucTienThuResponse.
     * 
     * @param getHanMucTienThuResult
     */
    public void setGetHanMucTienThuResult(org.tempuri.GetHanMucTienThuResponseGetHanMucTienThuResult getHanMucTienThuResult) {
        this.getHanMucTienThuResult = getHanMucTienThuResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetHanMucTienThuResponse)) return false;
        GetHanMucTienThuResponse other = (GetHanMucTienThuResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getHanMucTienThuResult==null && other.getGetHanMucTienThuResult()==null) || 
             (this.getHanMucTienThuResult!=null &&
              this.getHanMucTienThuResult.equals(other.getGetHanMucTienThuResult())));
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
        if (getGetHanMucTienThuResult() != null) {
            _hashCode += getGetHanMucTienThuResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetHanMucTienThuResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetHanMucTienThuResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getHanMucTienThuResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetHanMucTienThuResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetHanMucTienThuResponse>GetHanMucTienThuResult"));
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
