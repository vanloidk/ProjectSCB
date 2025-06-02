/**
 * DeliveryMechanism.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class DeliveryMechanism  implements java.io.Serializable {
    private java.lang.String contactInfoLabel;

    private java.lang.String deliveryConfigurationName;

    private boolean defaultContactInfo;

    public DeliveryMechanism() {
    }

    public DeliveryMechanism(
           java.lang.String contactInfoLabel,
           java.lang.String deliveryConfigurationName,
           boolean defaultContactInfo) {
           this.contactInfoLabel = contactInfoLabel;
           this.deliveryConfigurationName = deliveryConfigurationName;
           this.defaultContactInfo = defaultContactInfo;
    }


    /**
     * Gets the contactInfoLabel value for this DeliveryMechanism.
     * 
     * @return contactInfoLabel
     */
    public java.lang.String getContactInfoLabel() {
        return contactInfoLabel;
    }


    /**
     * Sets the contactInfoLabel value for this DeliveryMechanism.
     * 
     * @param contactInfoLabel
     */
    public void setContactInfoLabel(java.lang.String contactInfoLabel) {
        this.contactInfoLabel = contactInfoLabel;
    }


    /**
     * Gets the deliveryConfigurationName value for this DeliveryMechanism.
     * 
     * @return deliveryConfigurationName
     */
    public java.lang.String getDeliveryConfigurationName() {
        return deliveryConfigurationName;
    }


    /**
     * Sets the deliveryConfigurationName value for this DeliveryMechanism.
     * 
     * @param deliveryConfigurationName
     */
    public void setDeliveryConfigurationName(java.lang.String deliveryConfigurationName) {
        this.deliveryConfigurationName = deliveryConfigurationName;
    }


    /**
     * Gets the defaultContactInfo value for this DeliveryMechanism.
     * 
     * @return defaultContactInfo
     */
    public boolean isDefaultContactInfo() {
        return defaultContactInfo;
    }


    /**
     * Sets the defaultContactInfo value for this DeliveryMechanism.
     * 
     * @param defaultContactInfo
     */
    public void setDefaultContactInfo(boolean defaultContactInfo) {
        this.defaultContactInfo = defaultContactInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeliveryMechanism)) return false;
        DeliveryMechanism other = (DeliveryMechanism) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contactInfoLabel==null && other.getContactInfoLabel()==null) || 
             (this.contactInfoLabel!=null &&
              this.contactInfoLabel.equals(other.getContactInfoLabel()))) &&
            ((this.deliveryConfigurationName==null && other.getDeliveryConfigurationName()==null) || 
             (this.deliveryConfigurationName!=null &&
              this.deliveryConfigurationName.equals(other.getDeliveryConfigurationName()))) &&
            this.defaultContactInfo == other.isDefaultContactInfo();
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
        if (getContactInfoLabel() != null) {
            _hashCode += getContactInfoLabel().hashCode();
        }
        if (getDeliveryConfigurationName() != null) {
            _hashCode += getDeliveryConfigurationName().hashCode();
        }
        _hashCode += (isDefaultContactInfo() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeliveryMechanism.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "DeliveryMechanism"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactInfoLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contactInfoLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryConfigurationName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryConfigurationName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultContactInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "defaultContactInfo"));
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
