/**
 * PasswordChallenge.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class PasswordChallenge  implements java.io.Serializable {
    private java.lang.Boolean changeRequired;

    private java.util.Calendar expiryDate;

    private java.util.Calendar allowChangeAfterDate;

    private ws.internal.tokenkey.tnb.com.entrust.PasswordRules passwordRules;

    public PasswordChallenge() {
    }

    public PasswordChallenge(
           java.lang.Boolean changeRequired,
           java.util.Calendar expiryDate,
           java.util.Calendar allowChangeAfterDate,
           ws.internal.tokenkey.tnb.com.entrust.PasswordRules passwordRules) {
           this.changeRequired = changeRequired;
           this.expiryDate = expiryDate;
           this.allowChangeAfterDate = allowChangeAfterDate;
           this.passwordRules = passwordRules;
    }


    /**
     * Gets the changeRequired value for this PasswordChallenge.
     * 
     * @return changeRequired
     */
    public java.lang.Boolean getChangeRequired() {
        return changeRequired;
    }


    /**
     * Sets the changeRequired value for this PasswordChallenge.
     * 
     * @param changeRequired
     */
    public void setChangeRequired(java.lang.Boolean changeRequired) {
        this.changeRequired = changeRequired;
    }


    /**
     * Gets the expiryDate value for this PasswordChallenge.
     * 
     * @return expiryDate
     */
    public java.util.Calendar getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate value for this PasswordChallenge.
     * 
     * @param expiryDate
     */
    public void setExpiryDate(java.util.Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * Gets the allowChangeAfterDate value for this PasswordChallenge.
     * 
     * @return allowChangeAfterDate
     */
    public java.util.Calendar getAllowChangeAfterDate() {
        return allowChangeAfterDate;
    }


    /**
     * Sets the allowChangeAfterDate value for this PasswordChallenge.
     * 
     * @param allowChangeAfterDate
     */
    public void setAllowChangeAfterDate(java.util.Calendar allowChangeAfterDate) {
        this.allowChangeAfterDate = allowChangeAfterDate;
    }


    /**
     * Gets the passwordRules value for this PasswordChallenge.
     * 
     * @return passwordRules
     */
    public ws.internal.tokenkey.tnb.com.entrust.PasswordRules getPasswordRules() {
        return passwordRules;
    }


    /**
     * Sets the passwordRules value for this PasswordChallenge.
     * 
     * @param passwordRules
     */
    public void setPasswordRules(ws.internal.tokenkey.tnb.com.entrust.PasswordRules passwordRules) {
        this.passwordRules = passwordRules;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PasswordChallenge)) return false;
        PasswordChallenge other = (PasswordChallenge) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.changeRequired==null && other.getChangeRequired()==null) || 
             (this.changeRequired!=null &&
              this.changeRequired.equals(other.getChangeRequired()))) &&
            ((this.expiryDate==null && other.getExpiryDate()==null) || 
             (this.expiryDate!=null &&
              this.expiryDate.equals(other.getExpiryDate()))) &&
            ((this.allowChangeAfterDate==null && other.getAllowChangeAfterDate()==null) || 
             (this.allowChangeAfterDate!=null &&
              this.allowChangeAfterDate.equals(other.getAllowChangeAfterDate()))) &&
            ((this.passwordRules==null && other.getPasswordRules()==null) || 
             (this.passwordRules!=null &&
              this.passwordRules.equals(other.getPasswordRules())));
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
        if (getChangeRequired() != null) {
            _hashCode += getChangeRequired().hashCode();
        }
        if (getExpiryDate() != null) {
            _hashCode += getExpiryDate().hashCode();
        }
        if (getAllowChangeAfterDate() != null) {
            _hashCode += getAllowChangeAfterDate().hashCode();
        }
        if (getPasswordRules() != null) {
            _hashCode += getPasswordRules().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PasswordChallenge.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "PasswordChallenge"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changeRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "changeRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiryDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ExpiryDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowChangeAfterDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AllowChangeAfterDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passwordRules");
        elemField.setXmlName(new javax.xml.namespace.QName("", "passwordRules"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "PasswordRules"));
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
