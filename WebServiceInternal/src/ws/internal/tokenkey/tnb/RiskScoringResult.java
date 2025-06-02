/**
 * RiskScoringResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class RiskScoringResult  implements java.io.Serializable {
    private java.lang.Boolean IPAuthenticationPassed;

    private ws.internal.tokenkey.tnb.IPAuthenticationStatus IPAuthenticationStatus;

    private java.lang.Boolean machineAuthenticationPassed;

    private ws.internal.tokenkey.tnb.MachineAuthenticationStatus machineAuthenticationStatus;

    private java.lang.Boolean certificateAuthenticationPassed;

    private ws.internal.tokenkey.tnb.CertificateAuthenticationStatus certificateAuthenticationStatus;

    private java.lang.String externalRiskScoreStatus;

    public RiskScoringResult() {
    }

    public RiskScoringResult(
           java.lang.Boolean IPAuthenticationPassed,
           ws.internal.tokenkey.tnb.IPAuthenticationStatus IPAuthenticationStatus,
           java.lang.Boolean machineAuthenticationPassed,
           ws.internal.tokenkey.tnb.MachineAuthenticationStatus machineAuthenticationStatus,
           java.lang.Boolean certificateAuthenticationPassed,
           ws.internal.tokenkey.tnb.CertificateAuthenticationStatus certificateAuthenticationStatus,
           java.lang.String externalRiskScoreStatus) {
           this.IPAuthenticationPassed = IPAuthenticationPassed;
           this.IPAuthenticationStatus = IPAuthenticationStatus;
           this.machineAuthenticationPassed = machineAuthenticationPassed;
           this.machineAuthenticationStatus = machineAuthenticationStatus;
           this.certificateAuthenticationPassed = certificateAuthenticationPassed;
           this.certificateAuthenticationStatus = certificateAuthenticationStatus;
           this.externalRiskScoreStatus = externalRiskScoreStatus;
    }


    /**
     * Gets the IPAuthenticationPassed value for this RiskScoringResult.
     * 
     * @return IPAuthenticationPassed
     */
    public java.lang.Boolean getIPAuthenticationPassed() {
        return IPAuthenticationPassed;
    }


    /**
     * Sets the IPAuthenticationPassed value for this RiskScoringResult.
     * 
     * @param IPAuthenticationPassed
     */
    public void setIPAuthenticationPassed(java.lang.Boolean IPAuthenticationPassed) {
        this.IPAuthenticationPassed = IPAuthenticationPassed;
    }


    /**
     * Gets the IPAuthenticationStatus value for this RiskScoringResult.
     * 
     * @return IPAuthenticationStatus
     */
    public ws.internal.tokenkey.tnb.IPAuthenticationStatus getIPAuthenticationStatus() {
        return IPAuthenticationStatus;
    }


    /**
     * Sets the IPAuthenticationStatus value for this RiskScoringResult.
     * 
     * @param IPAuthenticationStatus
     */
    public void setIPAuthenticationStatus(ws.internal.tokenkey.tnb.IPAuthenticationStatus IPAuthenticationStatus) {
        this.IPAuthenticationStatus = IPAuthenticationStatus;
    }


    /**
     * Gets the machineAuthenticationPassed value for this RiskScoringResult.
     * 
     * @return machineAuthenticationPassed
     */
    public java.lang.Boolean getMachineAuthenticationPassed() {
        return machineAuthenticationPassed;
    }


    /**
     * Sets the machineAuthenticationPassed value for this RiskScoringResult.
     * 
     * @param machineAuthenticationPassed
     */
    public void setMachineAuthenticationPassed(java.lang.Boolean machineAuthenticationPassed) {
        this.machineAuthenticationPassed = machineAuthenticationPassed;
    }


    /**
     * Gets the machineAuthenticationStatus value for this RiskScoringResult.
     * 
     * @return machineAuthenticationStatus
     */
    public ws.internal.tokenkey.tnb.MachineAuthenticationStatus getMachineAuthenticationStatus() {
        return machineAuthenticationStatus;
    }


    /**
     * Sets the machineAuthenticationStatus value for this RiskScoringResult.
     * 
     * @param machineAuthenticationStatus
     */
    public void setMachineAuthenticationStatus(ws.internal.tokenkey.tnb.MachineAuthenticationStatus machineAuthenticationStatus) {
        this.machineAuthenticationStatus = machineAuthenticationStatus;
    }


    /**
     * Gets the certificateAuthenticationPassed value for this RiskScoringResult.
     * 
     * @return certificateAuthenticationPassed
     */
    public java.lang.Boolean getCertificateAuthenticationPassed() {
        return certificateAuthenticationPassed;
    }


    /**
     * Sets the certificateAuthenticationPassed value for this RiskScoringResult.
     * 
     * @param certificateAuthenticationPassed
     */
    public void setCertificateAuthenticationPassed(java.lang.Boolean certificateAuthenticationPassed) {
        this.certificateAuthenticationPassed = certificateAuthenticationPassed;
    }


    /**
     * Gets the certificateAuthenticationStatus value for this RiskScoringResult.
     * 
     * @return certificateAuthenticationStatus
     */
    public ws.internal.tokenkey.tnb.CertificateAuthenticationStatus getCertificateAuthenticationStatus() {
        return certificateAuthenticationStatus;
    }


    /**
     * Sets the certificateAuthenticationStatus value for this RiskScoringResult.
     * 
     * @param certificateAuthenticationStatus
     */
    public void setCertificateAuthenticationStatus(ws.internal.tokenkey.tnb.CertificateAuthenticationStatus certificateAuthenticationStatus) {
        this.certificateAuthenticationStatus = certificateAuthenticationStatus;
    }


    /**
     * Gets the externalRiskScoreStatus value for this RiskScoringResult.
     * 
     * @return externalRiskScoreStatus
     */
    public java.lang.String getExternalRiskScoreStatus() {
        return externalRiskScoreStatus;
    }


    /**
     * Sets the externalRiskScoreStatus value for this RiskScoringResult.
     * 
     * @param externalRiskScoreStatus
     */
    public void setExternalRiskScoreStatus(java.lang.String externalRiskScoreStatus) {
        this.externalRiskScoreStatus = externalRiskScoreStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RiskScoringResult)) return false;
        RiskScoringResult other = (RiskScoringResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.IPAuthenticationPassed==null && other.getIPAuthenticationPassed()==null) || 
             (this.IPAuthenticationPassed!=null &&
              this.IPAuthenticationPassed.equals(other.getIPAuthenticationPassed()))) &&
            ((this.IPAuthenticationStatus==null && other.getIPAuthenticationStatus()==null) || 
             (this.IPAuthenticationStatus!=null &&
              this.IPAuthenticationStatus.equals(other.getIPAuthenticationStatus()))) &&
            ((this.machineAuthenticationPassed==null && other.getMachineAuthenticationPassed()==null) || 
             (this.machineAuthenticationPassed!=null &&
              this.machineAuthenticationPassed.equals(other.getMachineAuthenticationPassed()))) &&
            ((this.machineAuthenticationStatus==null && other.getMachineAuthenticationStatus()==null) || 
             (this.machineAuthenticationStatus!=null &&
              this.machineAuthenticationStatus.equals(other.getMachineAuthenticationStatus()))) &&
            ((this.certificateAuthenticationPassed==null && other.getCertificateAuthenticationPassed()==null) || 
             (this.certificateAuthenticationPassed!=null &&
              this.certificateAuthenticationPassed.equals(other.getCertificateAuthenticationPassed()))) &&
            ((this.certificateAuthenticationStatus==null && other.getCertificateAuthenticationStatus()==null) || 
             (this.certificateAuthenticationStatus!=null &&
              this.certificateAuthenticationStatus.equals(other.getCertificateAuthenticationStatus()))) &&
            ((this.externalRiskScoreStatus==null && other.getExternalRiskScoreStatus()==null) || 
             (this.externalRiskScoreStatus!=null &&
              this.externalRiskScoreStatus.equals(other.getExternalRiskScoreStatus())));
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
        if (getIPAuthenticationPassed() != null) {
            _hashCode += getIPAuthenticationPassed().hashCode();
        }
        if (getIPAuthenticationStatus() != null) {
            _hashCode += getIPAuthenticationStatus().hashCode();
        }
        if (getMachineAuthenticationPassed() != null) {
            _hashCode += getMachineAuthenticationPassed().hashCode();
        }
        if (getMachineAuthenticationStatus() != null) {
            _hashCode += getMachineAuthenticationStatus().hashCode();
        }
        if (getCertificateAuthenticationPassed() != null) {
            _hashCode += getCertificateAuthenticationPassed().hashCode();
        }
        if (getCertificateAuthenticationStatus() != null) {
            _hashCode += getCertificateAuthenticationStatus().hashCode();
        }
        if (getExternalRiskScoreStatus() != null) {
            _hashCode += getExternalRiskScoreStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RiskScoringResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "RiskScoringResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPAuthenticationPassed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IPAuthenticationPassed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPAuthenticationStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IPAuthenticationStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "IPAuthenticationStatus"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machineAuthenticationPassed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MachineAuthenticationPassed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machineAuthenticationStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MachineAuthenticationStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MachineAuthenticationStatus"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificateAuthenticationPassed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CertificateAuthenticationPassed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificateAuthenticationStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CertificateAuthenticationStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "CertificateAuthenticationStatus"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("externalRiskScoreStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ExternalRiskScoreStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
