/**
 * StaffType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class StaffType  implements java.io.Serializable {
    /* Mã nhân viên kinh doanh */
    private java.lang.String salesStaffCode;

    /* Tên nhân viên kinh doanh */
    private java.lang.String salesStaffName;

    public StaffType() {
    }

    public StaffType(
           java.lang.String salesStaffCode,
           java.lang.String salesStaffName) {
           this.salesStaffCode = salesStaffCode;
           this.salesStaffName = salesStaffName;
    }


    /**
     * Gets the salesStaffCode value for this StaffType.
     * 
     * @return salesStaffCode   * Mã nhân viên kinh doanh
     */
    public java.lang.String getSalesStaffCode() {
        return salesStaffCode;
    }


    /**
     * Sets the salesStaffCode value for this StaffType.
     * 
     * @param salesStaffCode   * Mã nhân viên kinh doanh
     */
    public void setSalesStaffCode(java.lang.String salesStaffCode) {
        this.salesStaffCode = salesStaffCode;
    }


    /**
     * Gets the salesStaffName value for this StaffType.
     * 
     * @return salesStaffName   * Tên nhân viên kinh doanh
     */
    public java.lang.String getSalesStaffName() {
        return salesStaffName;
    }


    /**
     * Sets the salesStaffName value for this StaffType.
     * 
     * @param salesStaffName   * Tên nhân viên kinh doanh
     */
    public void setSalesStaffName(java.lang.String salesStaffName) {
        this.salesStaffName = salesStaffName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StaffType)) return false;
        StaffType other = (StaffType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.salesStaffCode==null && other.getSalesStaffCode()==null) || 
             (this.salesStaffCode!=null &&
              this.salesStaffCode.equals(other.getSalesStaffCode()))) &&
            ((this.salesStaffName==null && other.getSalesStaffName()==null) || 
             (this.salesStaffName!=null &&
              this.salesStaffName.equals(other.getSalesStaffName())));
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
        if (getSalesStaffCode() != null) {
            _hashCode += getSalesStaffCode().hashCode();
        }
        if (getSalesStaffName() != null) {
            _hashCode += getSalesStaffName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StaffType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "StaffType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesStaffCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "salesStaffCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesStaffName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "salesStaffName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
