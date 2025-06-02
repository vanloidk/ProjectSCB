/**
 * RedemptionType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class RedemptionType  implements java.io.Serializable {
    /* Hình thức tất toán (Một phần hay toàn bộ). 'P' Một phần; 'F'
     * Toàn bộ */
    private java.lang.String redemptionMode;

    /* Só tiền tất toán. Chỉ có giá trị khi chọn hình thức tất toán
     * một phần */
    private java.lang.String redemptionAmount;

    public RedemptionType() {
    }

    public RedemptionType(
           java.lang.String redemptionMode,
           java.lang.String redemptionAmount) {
           this.redemptionMode = redemptionMode;
           this.redemptionAmount = redemptionAmount;
    }


    /**
     * Gets the redemptionMode value for this RedemptionType.
     * 
     * @return redemptionMode   * Hình thức tất toán (Một phần hay toàn bộ). 'P' Một phần; 'F'
     * Toàn bộ
     */
    public java.lang.String getRedemptionMode() {
        return redemptionMode;
    }


    /**
     * Sets the redemptionMode value for this RedemptionType.
     * 
     * @param redemptionMode   * Hình thức tất toán (Một phần hay toàn bộ). 'P' Một phần; 'F'
     * Toàn bộ
     */
    public void setRedemptionMode(java.lang.String redemptionMode) {
        this.redemptionMode = redemptionMode;
    }


    /**
     * Gets the redemptionAmount value for this RedemptionType.
     * 
     * @return redemptionAmount   * Só tiền tất toán. Chỉ có giá trị khi chọn hình thức tất toán
     * một phần
     */
    public java.lang.String getRedemptionAmount() {
        return redemptionAmount;
    }


    /**
     * Sets the redemptionAmount value for this RedemptionType.
     * 
     * @param redemptionAmount   * Só tiền tất toán. Chỉ có giá trị khi chọn hình thức tất toán
     * một phần
     */
    public void setRedemptionAmount(java.lang.String redemptionAmount) {
        this.redemptionAmount = redemptionAmount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RedemptionType)) return false;
        RedemptionType other = (RedemptionType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.redemptionMode==null && other.getRedemptionMode()==null) || 
             (this.redemptionMode!=null &&
              this.redemptionMode.equals(other.getRedemptionMode()))) &&
            ((this.redemptionAmount==null && other.getRedemptionAmount()==null) || 
             (this.redemptionAmount!=null &&
              this.redemptionAmount.equals(other.getRedemptionAmount())));
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
        if (getRedemptionMode() != null) {
            _hashCode += getRedemptionMode().hashCode();
        }
        if (getRedemptionAmount() != null) {
            _hashCode += getRedemptionAmount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RedemptionType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "RedemptionType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("redemptionMode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "redemptionMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("redemptionAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "redemptionAmount"));
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
