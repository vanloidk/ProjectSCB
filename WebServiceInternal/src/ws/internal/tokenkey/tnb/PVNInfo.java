/**
 * PVNInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class PVNInfo  implements java.io.Serializable {
    private boolean required;

    private java.lang.Boolean available;

    private java.lang.Boolean changeRequired;

    private int length;

    public PVNInfo() {
    }

    public PVNInfo(
           boolean required,
           java.lang.Boolean available,
           java.lang.Boolean changeRequired,
           int length) {
           this.required = required;
           this.available = available;
           this.changeRequired = changeRequired;
           this.length = length;
    }


    /**
     * Gets the required value for this PVNInfo.
     * 
     * @return required
     */
    public boolean isRequired() {
        return required;
    }


    /**
     * Sets the required value for this PVNInfo.
     * 
     * @param required
     */
    public void setRequired(boolean required) {
        this.required = required;
    }


    /**
     * Gets the available value for this PVNInfo.
     * 
     * @return available
     */
    public java.lang.Boolean getAvailable() {
        return available;
    }


    /**
     * Sets the available value for this PVNInfo.
     * 
     * @param available
     */
    public void setAvailable(java.lang.Boolean available) {
        this.available = available;
    }


    /**
     * Gets the changeRequired value for this PVNInfo.
     * 
     * @return changeRequired
     */
    public java.lang.Boolean getChangeRequired() {
        return changeRequired;
    }


    /**
     * Sets the changeRequired value for this PVNInfo.
     * 
     * @param changeRequired
     */
    public void setChangeRequired(java.lang.Boolean changeRequired) {
        this.changeRequired = changeRequired;
    }


    /**
     * Gets the length value for this PVNInfo.
     * 
     * @return length
     */
    public int getLength() {
        return length;
    }


    /**
     * Sets the length value for this PVNInfo.
     * 
     * @param length
     */
    public void setLength(int length) {
        this.length = length;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PVNInfo)) return false;
        PVNInfo other = (PVNInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.required == other.isRequired() &&
            ((this.available==null && other.getAvailable()==null) || 
             (this.available!=null &&
              this.available.equals(other.getAvailable()))) &&
            ((this.changeRequired==null && other.getChangeRequired()==null) || 
             (this.changeRequired!=null &&
              this.changeRequired.equals(other.getChangeRequired()))) &&
            this.length == other.getLength();
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
        _hashCode += (isRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getAvailable() != null) {
            _hashCode += getAvailable().hashCode();
        }
        if (getChangeRequired() != null) {
            _hashCode += getChangeRequired().hashCode();
        }
        _hashCode += getLength();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PVNInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "PVNInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("required");
        elemField.setXmlName(new javax.xml.namespace.QName("", "required"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("available");
        elemField.setXmlName(new javax.xml.namespace.QName("", "available"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changeRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "changeRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("length");
        elemField.setXmlName(new javax.xml.namespace.QName("", "length"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
