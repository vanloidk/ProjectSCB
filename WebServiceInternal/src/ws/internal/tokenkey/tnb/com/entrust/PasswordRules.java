/**
 * PasswordRules.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb.com.entrust;

public class PasswordRules  implements java.io.Serializable {
    private int minimumLength;

    private boolean numberRequired;

    private boolean upperCaseRequired;

    private boolean lowerCaseRequired;

    private boolean specialCharRequired;

    public PasswordRules() {
    }

    public PasswordRules(
           int minimumLength,
           boolean numberRequired,
           boolean upperCaseRequired,
           boolean lowerCaseRequired,
           boolean specialCharRequired) {
           this.minimumLength = minimumLength;
           this.numberRequired = numberRequired;
           this.upperCaseRequired = upperCaseRequired;
           this.lowerCaseRequired = lowerCaseRequired;
           this.specialCharRequired = specialCharRequired;
    }


    /**
     * Gets the minimumLength value for this PasswordRules.
     * 
     * @return minimumLength
     */
    public int getMinimumLength() {
        return minimumLength;
    }


    /**
     * Sets the minimumLength value for this PasswordRules.
     * 
     * @param minimumLength
     */
    public void setMinimumLength(int minimumLength) {
        this.minimumLength = minimumLength;
    }


    /**
     * Gets the numberRequired value for this PasswordRules.
     * 
     * @return numberRequired
     */
    public boolean isNumberRequired() {
        return numberRequired;
    }


    /**
     * Sets the numberRequired value for this PasswordRules.
     * 
     * @param numberRequired
     */
    public void setNumberRequired(boolean numberRequired) {
        this.numberRequired = numberRequired;
    }


    /**
     * Gets the upperCaseRequired value for this PasswordRules.
     * 
     * @return upperCaseRequired
     */
    public boolean isUpperCaseRequired() {
        return upperCaseRequired;
    }


    /**
     * Sets the upperCaseRequired value for this PasswordRules.
     * 
     * @param upperCaseRequired
     */
    public void setUpperCaseRequired(boolean upperCaseRequired) {
        this.upperCaseRequired = upperCaseRequired;
    }


    /**
     * Gets the lowerCaseRequired value for this PasswordRules.
     * 
     * @return lowerCaseRequired
     */
    public boolean isLowerCaseRequired() {
        return lowerCaseRequired;
    }


    /**
     * Sets the lowerCaseRequired value for this PasswordRules.
     * 
     * @param lowerCaseRequired
     */
    public void setLowerCaseRequired(boolean lowerCaseRequired) {
        this.lowerCaseRequired = lowerCaseRequired;
    }


    /**
     * Gets the specialCharRequired value for this PasswordRules.
     * 
     * @return specialCharRequired
     */
    public boolean isSpecialCharRequired() {
        return specialCharRequired;
    }


    /**
     * Sets the specialCharRequired value for this PasswordRules.
     * 
     * @param specialCharRequired
     */
    public void setSpecialCharRequired(boolean specialCharRequired) {
        this.specialCharRequired = specialCharRequired;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PasswordRules)) return false;
        PasswordRules other = (PasswordRules) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.minimumLength == other.getMinimumLength() &&
            this.numberRequired == other.isNumberRequired() &&
            this.upperCaseRequired == other.isUpperCaseRequired() &&
            this.lowerCaseRequired == other.isLowerCaseRequired() &&
            this.specialCharRequired == other.isSpecialCharRequired();
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
        _hashCode += getMinimumLength();
        _hashCode += (isNumberRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isUpperCaseRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isLowerCaseRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSpecialCharRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PasswordRules.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "PasswordRules"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minimumLength");
        elemField.setXmlName(new javax.xml.namespace.QName("", "minimumLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upperCaseRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "upperCaseRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lowerCaseRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lowerCaseRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("specialCharRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "specialCharRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
