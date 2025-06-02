/**
 * AllowedAuthenticationTypes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class AllowedAuthenticationTypes  implements java.io.Serializable {
    private java.lang.String[] genericAuth;

    private java.lang.String[] enhancedGenericAuth;

    private java.lang.String[] machineAuth;

    private java.lang.String[] authTypeCanViewSecrets;

    private java.lang.String[] authTypeCanModifySecrets;

    private java.lang.String[] authTypesRequiringPVN;

    private int PVNLength;

    public AllowedAuthenticationTypes() {
    }

    public AllowedAuthenticationTypes(
           java.lang.String[] genericAuth,
           java.lang.String[] enhancedGenericAuth,
           java.lang.String[] machineAuth,
           java.lang.String[] authTypeCanViewSecrets,
           java.lang.String[] authTypeCanModifySecrets,
           java.lang.String[] authTypesRequiringPVN,
           int PVNLength) {
           this.genericAuth = genericAuth;
           this.enhancedGenericAuth = enhancedGenericAuth;
           this.machineAuth = machineAuth;
           this.authTypeCanViewSecrets = authTypeCanViewSecrets;
           this.authTypeCanModifySecrets = authTypeCanModifySecrets;
           this.authTypesRequiringPVN = authTypesRequiringPVN;
           this.PVNLength = PVNLength;
    }


    /**
     * Gets the genericAuth value for this AllowedAuthenticationTypes.
     * 
     * @return genericAuth
     */
    public java.lang.String[] getGenericAuth() {
        return genericAuth;
    }


    /**
     * Sets the genericAuth value for this AllowedAuthenticationTypes.
     * 
     * @param genericAuth
     */
    public void setGenericAuth(java.lang.String[] genericAuth) {
        this.genericAuth = genericAuth;
    }


    /**
     * Gets the enhancedGenericAuth value for this AllowedAuthenticationTypes.
     * 
     * @return enhancedGenericAuth
     */
    public java.lang.String[] getEnhancedGenericAuth() {
        return enhancedGenericAuth;
    }


    /**
     * Sets the enhancedGenericAuth value for this AllowedAuthenticationTypes.
     * 
     * @param enhancedGenericAuth
     */
    public void setEnhancedGenericAuth(java.lang.String[] enhancedGenericAuth) {
        this.enhancedGenericAuth = enhancedGenericAuth;
    }


    /**
     * Gets the machineAuth value for this AllowedAuthenticationTypes.
     * 
     * @return machineAuth
     */
    public java.lang.String[] getMachineAuth() {
        return machineAuth;
    }


    /**
     * Sets the machineAuth value for this AllowedAuthenticationTypes.
     * 
     * @param machineAuth
     */
    public void setMachineAuth(java.lang.String[] machineAuth) {
        this.machineAuth = machineAuth;
    }


    /**
     * Gets the authTypeCanViewSecrets value for this AllowedAuthenticationTypes.
     * 
     * @return authTypeCanViewSecrets
     */
    public java.lang.String[] getAuthTypeCanViewSecrets() {
        return authTypeCanViewSecrets;
    }


    /**
     * Sets the authTypeCanViewSecrets value for this AllowedAuthenticationTypes.
     * 
     * @param authTypeCanViewSecrets
     */
    public void setAuthTypeCanViewSecrets(java.lang.String[] authTypeCanViewSecrets) {
        this.authTypeCanViewSecrets = authTypeCanViewSecrets;
    }


    /**
     * Gets the authTypeCanModifySecrets value for this AllowedAuthenticationTypes.
     * 
     * @return authTypeCanModifySecrets
     */
    public java.lang.String[] getAuthTypeCanModifySecrets() {
        return authTypeCanModifySecrets;
    }


    /**
     * Sets the authTypeCanModifySecrets value for this AllowedAuthenticationTypes.
     * 
     * @param authTypeCanModifySecrets
     */
    public void setAuthTypeCanModifySecrets(java.lang.String[] authTypeCanModifySecrets) {
        this.authTypeCanModifySecrets = authTypeCanModifySecrets;
    }


    /**
     * Gets the authTypesRequiringPVN value for this AllowedAuthenticationTypes.
     * 
     * @return authTypesRequiringPVN
     */
    public java.lang.String[] getAuthTypesRequiringPVN() {
        return authTypesRequiringPVN;
    }


    /**
     * Sets the authTypesRequiringPVN value for this AllowedAuthenticationTypes.
     * 
     * @param authTypesRequiringPVN
     */
    public void setAuthTypesRequiringPVN(java.lang.String[] authTypesRequiringPVN) {
        this.authTypesRequiringPVN = authTypesRequiringPVN;
    }


    /**
     * Gets the PVNLength value for this AllowedAuthenticationTypes.
     * 
     * @return PVNLength
     */
    public int getPVNLength() {
        return PVNLength;
    }


    /**
     * Sets the PVNLength value for this AllowedAuthenticationTypes.
     * 
     * @param PVNLength
     */
    public void setPVNLength(int PVNLength) {
        this.PVNLength = PVNLength;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AllowedAuthenticationTypes)) return false;
        AllowedAuthenticationTypes other = (AllowedAuthenticationTypes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.genericAuth==null && other.getGenericAuth()==null) || 
             (this.genericAuth!=null &&
              java.util.Arrays.equals(this.genericAuth, other.getGenericAuth()))) &&
            ((this.enhancedGenericAuth==null && other.getEnhancedGenericAuth()==null) || 
             (this.enhancedGenericAuth!=null &&
              java.util.Arrays.equals(this.enhancedGenericAuth, other.getEnhancedGenericAuth()))) &&
            ((this.machineAuth==null && other.getMachineAuth()==null) || 
             (this.machineAuth!=null &&
              java.util.Arrays.equals(this.machineAuth, other.getMachineAuth()))) &&
            ((this.authTypeCanViewSecrets==null && other.getAuthTypeCanViewSecrets()==null) || 
             (this.authTypeCanViewSecrets!=null &&
              java.util.Arrays.equals(this.authTypeCanViewSecrets, other.getAuthTypeCanViewSecrets()))) &&
            ((this.authTypeCanModifySecrets==null && other.getAuthTypeCanModifySecrets()==null) || 
             (this.authTypeCanModifySecrets!=null &&
              java.util.Arrays.equals(this.authTypeCanModifySecrets, other.getAuthTypeCanModifySecrets()))) &&
            ((this.authTypesRequiringPVN==null && other.getAuthTypesRequiringPVN()==null) || 
             (this.authTypesRequiringPVN!=null &&
              java.util.Arrays.equals(this.authTypesRequiringPVN, other.getAuthTypesRequiringPVN()))) &&
            this.PVNLength == other.getPVNLength();
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
        if (getGenericAuth() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGenericAuth());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGenericAuth(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEnhancedGenericAuth() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEnhancedGenericAuth());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEnhancedGenericAuth(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMachineAuth() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMachineAuth());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMachineAuth(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAuthTypeCanViewSecrets() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthTypeCanViewSecrets());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthTypeCanViewSecrets(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAuthTypeCanModifySecrets() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthTypeCanModifySecrets());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthTypeCanModifySecrets(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAuthTypesRequiringPVN() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAuthTypesRequiringPVN());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAuthTypesRequiringPVN(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getPVNLength();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AllowedAuthenticationTypes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AllowedAuthenticationTypes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("genericAuth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "genericAuth"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enhancedGenericAuth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enhancedGenericAuth"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machineAuth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "machineAuth"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authTypeCanViewSecrets");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authTypeCanViewSecrets"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authTypeCanModifySecrets");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authTypeCanModifySecrets"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authTypesRequiringPVN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authTypesRequiringPVN"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PVNLength");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PVNLength"));
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
