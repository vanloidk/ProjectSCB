/**
 * MachineSecret.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class MachineSecret  implements java.io.Serializable {
    private java.lang.String machineLabel;

    private java.lang.String machineNonce;

    private java.lang.String sequenceNonce;

    private ws.internal.tokenkey.tnb.com.entrust.NameValue[] applicationData;

    public MachineSecret() {
    }

    public MachineSecret(
           java.lang.String machineLabel,
           java.lang.String machineNonce,
           java.lang.String sequenceNonce,
           ws.internal.tokenkey.tnb.com.entrust.NameValue[] applicationData) {
           this.machineLabel = machineLabel;
           this.machineNonce = machineNonce;
           this.sequenceNonce = sequenceNonce;
           this.applicationData = applicationData;
    }


    /**
     * Gets the machineLabel value for this MachineSecret.
     * 
     * @return machineLabel
     */
    public java.lang.String getMachineLabel() {
        return machineLabel;
    }


    /**
     * Sets the machineLabel value for this MachineSecret.
     * 
     * @param machineLabel
     */
    public void setMachineLabel(java.lang.String machineLabel) {
        this.machineLabel = machineLabel;
    }


    /**
     * Gets the machineNonce value for this MachineSecret.
     * 
     * @return machineNonce
     */
    public java.lang.String getMachineNonce() {
        return machineNonce;
    }


    /**
     * Sets the machineNonce value for this MachineSecret.
     * 
     * @param machineNonce
     */
    public void setMachineNonce(java.lang.String machineNonce) {
        this.machineNonce = machineNonce;
    }


    /**
     * Gets the sequenceNonce value for this MachineSecret.
     * 
     * @return sequenceNonce
     */
    public java.lang.String getSequenceNonce() {
        return sequenceNonce;
    }


    /**
     * Sets the sequenceNonce value for this MachineSecret.
     * 
     * @param sequenceNonce
     */
    public void setSequenceNonce(java.lang.String sequenceNonce) {
        this.sequenceNonce = sequenceNonce;
    }


    /**
     * Gets the applicationData value for this MachineSecret.
     * 
     * @return applicationData
     */
    public ws.internal.tokenkey.tnb.com.entrust.NameValue[] getApplicationData() {
        return applicationData;
    }


    /**
     * Sets the applicationData value for this MachineSecret.
     * 
     * @param applicationData
     */
    public void setApplicationData(ws.internal.tokenkey.tnb.com.entrust.NameValue[] applicationData) {
        this.applicationData = applicationData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MachineSecret)) return false;
        MachineSecret other = (MachineSecret) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.machineLabel==null && other.getMachineLabel()==null) || 
             (this.machineLabel!=null &&
              this.machineLabel.equals(other.getMachineLabel()))) &&
            ((this.machineNonce==null && other.getMachineNonce()==null) || 
             (this.machineNonce!=null &&
              this.machineNonce.equals(other.getMachineNonce()))) &&
            ((this.sequenceNonce==null && other.getSequenceNonce()==null) || 
             (this.sequenceNonce!=null &&
              this.sequenceNonce.equals(other.getSequenceNonce()))) &&
            ((this.applicationData==null && other.getApplicationData()==null) || 
             (this.applicationData!=null &&
              java.util.Arrays.equals(this.applicationData, other.getApplicationData())));
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
        if (getMachineLabel() != null) {
            _hashCode += getMachineLabel().hashCode();
        }
        if (getMachineNonce() != null) {
            _hashCode += getMachineNonce().hashCode();
        }
        if (getSequenceNonce() != null) {
            _hashCode += getSequenceNonce().hashCode();
        }
        if (getApplicationData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getApplicationData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getApplicationData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MachineSecret.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MachineSecret"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machineLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "machineLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machineNonce");
        elemField.setXmlName(new javax.xml.namespace.QName("", "machineNonce"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequenceNonce");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequenceNonce"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationData"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "NameValue"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
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
