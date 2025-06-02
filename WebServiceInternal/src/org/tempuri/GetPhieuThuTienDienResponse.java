/**
 * GetPhieuThuTienDienResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class GetPhieuThuTienDienResponse  implements java.io.Serializable {
    private org.tempuri.GetPhieuThuTienDienResponseGetPhieuThuTienDienResult getPhieuThuTienDienResult;

    public GetPhieuThuTienDienResponse() {
    }

    public GetPhieuThuTienDienResponse(
           org.tempuri.GetPhieuThuTienDienResponseGetPhieuThuTienDienResult getPhieuThuTienDienResult) {
           this.getPhieuThuTienDienResult = getPhieuThuTienDienResult;
    }


    /**
     * Gets the getPhieuThuTienDienResult value for this GetPhieuThuTienDienResponse.
     * 
     * @return getPhieuThuTienDienResult
     */
    public org.tempuri.GetPhieuThuTienDienResponseGetPhieuThuTienDienResult getGetPhieuThuTienDienResult() {
        return getPhieuThuTienDienResult;
    }


    /**
     * Sets the getPhieuThuTienDienResult value for this GetPhieuThuTienDienResponse.
     * 
     * @param getPhieuThuTienDienResult
     */
    public void setGetPhieuThuTienDienResult(org.tempuri.GetPhieuThuTienDienResponseGetPhieuThuTienDienResult getPhieuThuTienDienResult) {
        this.getPhieuThuTienDienResult = getPhieuThuTienDienResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetPhieuThuTienDienResponse)) return false;
        GetPhieuThuTienDienResponse other = (GetPhieuThuTienDienResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getPhieuThuTienDienResult==null && other.getGetPhieuThuTienDienResult()==null) || 
             (this.getPhieuThuTienDienResult!=null &&
              this.getPhieuThuTienDienResult.equals(other.getGetPhieuThuTienDienResult())));
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
        if (getGetPhieuThuTienDienResult() != null) {
            _hashCode += getGetPhieuThuTienDienResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPhieuThuTienDienResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetPhieuThuTienDienResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getPhieuThuTienDienResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetPhieuThuTienDienResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetPhieuThuTienDienResponse>GetPhieuThuTienDienResult"));
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
