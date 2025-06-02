/**
 * CertificateChallenge.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb.com.entrust;

public class CertificateChallenge  implements java.io.Serializable {
    private java.lang.String challenge;

    private java.lang.String hashingAlgorithm;

    private ws.internal.tokenkey.tnb.com.entrust.CertificateData[] certificates;

    public CertificateChallenge() {
    }

    public CertificateChallenge(
           java.lang.String challenge,
           java.lang.String hashingAlgorithm,
           ws.internal.tokenkey.tnb.com.entrust.CertificateData[] certificates) {
           this.challenge = challenge;
           this.hashingAlgorithm = hashingAlgorithm;
           this.certificates = certificates;
    }


    /**
     * Gets the challenge value for this CertificateChallenge.
     * 
     * @return challenge
     */
    public java.lang.String getChallenge() {
        return challenge;
    }


    /**
     * Sets the challenge value for this CertificateChallenge.
     * 
     * @param challenge
     */
    public void setChallenge(java.lang.String challenge) {
        this.challenge = challenge;
    }


    /**
     * Gets the hashingAlgorithm value for this CertificateChallenge.
     * 
     * @return hashingAlgorithm
     */
    public java.lang.String getHashingAlgorithm() {
        return hashingAlgorithm;
    }


    /**
     * Sets the hashingAlgorithm value for this CertificateChallenge.
     * 
     * @param hashingAlgorithm
     */
    public void setHashingAlgorithm(java.lang.String hashingAlgorithm) {
        this.hashingAlgorithm = hashingAlgorithm;
    }


    /**
     * Gets the certificates value for this CertificateChallenge.
     * 
     * @return certificates
     */
    public ws.internal.tokenkey.tnb.com.entrust.CertificateData[] getCertificates() {
        return certificates;
    }


    /**
     * Sets the certificates value for this CertificateChallenge.
     * 
     * @param certificates
     */
    public void setCertificates(ws.internal.tokenkey.tnb.com.entrust.CertificateData[] certificates) {
        this.certificates = certificates;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CertificateChallenge)) return false;
        CertificateChallenge other = (CertificateChallenge) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.challenge==null && other.getChallenge()==null) || 
             (this.challenge!=null &&
              this.challenge.equals(other.getChallenge()))) &&
            ((this.hashingAlgorithm==null && other.getHashingAlgorithm()==null) || 
             (this.hashingAlgorithm!=null &&
              this.hashingAlgorithm.equals(other.getHashingAlgorithm()))) &&
            ((this.certificates==null && other.getCertificates()==null) || 
             (this.certificates!=null &&
              java.util.Arrays.equals(this.certificates, other.getCertificates())));
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
        if (getChallenge() != null) {
            _hashCode += getChallenge().hashCode();
        }
        if (getHashingAlgorithm() != null) {
            _hashCode += getHashingAlgorithm().hashCode();
        }
        if (getCertificates() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCertificates());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCertificates(), i);
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
        new org.apache.axis.description.TypeDesc(CertificateChallenge.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "CertificateChallenge"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("challenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "challenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hashingAlgorithm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hashingAlgorithm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificates");
        elemField.setXmlName(new javax.xml.namespace.QName("", "certificates"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "CertificateData"));
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
