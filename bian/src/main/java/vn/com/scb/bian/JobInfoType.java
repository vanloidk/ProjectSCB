/**
 * JobInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class JobInfoType  implements java.io.Serializable {
    /* Nghề nghiệp */
    private java.lang.String professionalName;

    /* ProfessionalCode */
    private java.lang.String professionalCode;

    public JobInfoType() {
    }

    public JobInfoType(
           java.lang.String professionalName,
           java.lang.String professionalCode) {
           this.professionalName = professionalName;
           this.professionalCode = professionalCode;
    }


    /**
     * Gets the professionalName value for this JobInfoType.
     * 
     * @return professionalName   * Nghề nghiệp
     */
    public java.lang.String getProfessionalName() {
        return professionalName;
    }


    /**
     * Sets the professionalName value for this JobInfoType.
     * 
     * @param professionalName   * Nghề nghiệp
     */
    public void setProfessionalName(java.lang.String professionalName) {
        this.professionalName = professionalName;
    }


    /**
     * Gets the professionalCode value for this JobInfoType.
     * 
     * @return professionalCode   * ProfessionalCode
     */
    public java.lang.String getProfessionalCode() {
        return professionalCode;
    }


    /**
     * Sets the professionalCode value for this JobInfoType.
     * 
     * @param professionalCode   * ProfessionalCode
     */
    public void setProfessionalCode(java.lang.String professionalCode) {
        this.professionalCode = professionalCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof JobInfoType)) return false;
        JobInfoType other = (JobInfoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.professionalName==null && other.getProfessionalName()==null) || 
             (this.professionalName!=null &&
              this.professionalName.equals(other.getProfessionalName()))) &&
            ((this.professionalCode==null && other.getProfessionalCode()==null) || 
             (this.professionalCode!=null &&
              this.professionalCode.equals(other.getProfessionalCode())));
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
        if (getProfessionalName() != null) {
            _hashCode += getProfessionalName().hashCode();
        }
        if (getProfessionalCode() != null) {
            _hashCode += getProfessionalCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(JobInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "JobInfoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("professionalName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProfessionalName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("professionalCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProfessionalCode"));
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
