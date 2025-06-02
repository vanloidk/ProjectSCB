/**
 * MachineAuthenticationStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class MachineAuthenticationStatus  implements java.io.Serializable {
    private boolean machineNonceFailed;

    private boolean sequenceNonceFailed;

    private boolean appDataFailed;

    private int numRequiredApplicationData;

    private int numFailedApplicationData;

    private boolean secretExpired;

    public MachineAuthenticationStatus() {
    }

    public MachineAuthenticationStatus(
           boolean machineNonceFailed,
           boolean sequenceNonceFailed,
           boolean appDataFailed,
           int numRequiredApplicationData,
           int numFailedApplicationData,
           boolean secretExpired) {
           this.machineNonceFailed = machineNonceFailed;
           this.sequenceNonceFailed = sequenceNonceFailed;
           this.appDataFailed = appDataFailed;
           this.numRequiredApplicationData = numRequiredApplicationData;
           this.numFailedApplicationData = numFailedApplicationData;
           this.secretExpired = secretExpired;
    }


    /**
     * Gets the machineNonceFailed value for this MachineAuthenticationStatus.
     * 
     * @return machineNonceFailed
     */
    public boolean isMachineNonceFailed() {
        return machineNonceFailed;
    }


    /**
     * Sets the machineNonceFailed value for this MachineAuthenticationStatus.
     * 
     * @param machineNonceFailed
     */
    public void setMachineNonceFailed(boolean machineNonceFailed) {
        this.machineNonceFailed = machineNonceFailed;
    }


    /**
     * Gets the sequenceNonceFailed value for this MachineAuthenticationStatus.
     * 
     * @return sequenceNonceFailed
     */
    public boolean isSequenceNonceFailed() {
        return sequenceNonceFailed;
    }


    /**
     * Sets the sequenceNonceFailed value for this MachineAuthenticationStatus.
     * 
     * @param sequenceNonceFailed
     */
    public void setSequenceNonceFailed(boolean sequenceNonceFailed) {
        this.sequenceNonceFailed = sequenceNonceFailed;
    }


    /**
     * Gets the appDataFailed value for this MachineAuthenticationStatus.
     * 
     * @return appDataFailed
     */
    public boolean isAppDataFailed() {
        return appDataFailed;
    }


    /**
     * Sets the appDataFailed value for this MachineAuthenticationStatus.
     * 
     * @param appDataFailed
     */
    public void setAppDataFailed(boolean appDataFailed) {
        this.appDataFailed = appDataFailed;
    }


    /**
     * Gets the numRequiredApplicationData value for this MachineAuthenticationStatus.
     * 
     * @return numRequiredApplicationData
     */
    public int getNumRequiredApplicationData() {
        return numRequiredApplicationData;
    }


    /**
     * Sets the numRequiredApplicationData value for this MachineAuthenticationStatus.
     * 
     * @param numRequiredApplicationData
     */
    public void setNumRequiredApplicationData(int numRequiredApplicationData) {
        this.numRequiredApplicationData = numRequiredApplicationData;
    }


    /**
     * Gets the numFailedApplicationData value for this MachineAuthenticationStatus.
     * 
     * @return numFailedApplicationData
     */
    public int getNumFailedApplicationData() {
        return numFailedApplicationData;
    }


    /**
     * Sets the numFailedApplicationData value for this MachineAuthenticationStatus.
     * 
     * @param numFailedApplicationData
     */
    public void setNumFailedApplicationData(int numFailedApplicationData) {
        this.numFailedApplicationData = numFailedApplicationData;
    }


    /**
     * Gets the secretExpired value for this MachineAuthenticationStatus.
     * 
     * @return secretExpired
     */
    public boolean isSecretExpired() {
        return secretExpired;
    }


    /**
     * Sets the secretExpired value for this MachineAuthenticationStatus.
     * 
     * @param secretExpired
     */
    public void setSecretExpired(boolean secretExpired) {
        this.secretExpired = secretExpired;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MachineAuthenticationStatus)) return false;
        MachineAuthenticationStatus other = (MachineAuthenticationStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.machineNonceFailed == other.isMachineNonceFailed() &&
            this.sequenceNonceFailed == other.isSequenceNonceFailed() &&
            this.appDataFailed == other.isAppDataFailed() &&
            this.numRequiredApplicationData == other.getNumRequiredApplicationData() &&
            this.numFailedApplicationData == other.getNumFailedApplicationData() &&
            this.secretExpired == other.isSecretExpired();
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
        _hashCode += (isMachineNonceFailed() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSequenceNonceFailed() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isAppDataFailed() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getNumRequiredApplicationData();
        _hashCode += getNumFailedApplicationData();
        _hashCode += (isSecretExpired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MachineAuthenticationStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MachineAuthenticationStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machineNonceFailed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "machineNonceFailed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequenceNonceFailed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequenceNonceFailed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appDataFailed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appDataFailed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numRequiredApplicationData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numRequiredApplicationData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numFailedApplicationData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numFailedApplicationData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secretExpired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "secretExpired"));
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
