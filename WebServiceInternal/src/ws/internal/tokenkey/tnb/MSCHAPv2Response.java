/**
 * MSCHAPv2Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class MSCHAPv2Response  implements java.io.Serializable {
    private byte[] challenge;

    private byte[] peerChallenge;

    private byte[] response;

    private byte[] userName;

    public MSCHAPv2Response() {
    }

    public MSCHAPv2Response(
           byte[] challenge,
           byte[] peerChallenge,
           byte[] response,
           byte[] userName) {
           this.challenge = challenge;
           this.peerChallenge = peerChallenge;
           this.response = response;
           this.userName = userName;
    }


    /**
     * Gets the challenge value for this MSCHAPv2Response.
     * 
     * @return challenge
     */
    public byte[] getChallenge() {
        return challenge;
    }


    /**
     * Sets the challenge value for this MSCHAPv2Response.
     * 
     * @param challenge
     */
    public void setChallenge(byte[] challenge) {
        this.challenge = challenge;
    }


    /**
     * Gets the peerChallenge value for this MSCHAPv2Response.
     * 
     * @return peerChallenge
     */
    public byte[] getPeerChallenge() {
        return peerChallenge;
    }


    /**
     * Sets the peerChallenge value for this MSCHAPv2Response.
     * 
     * @param peerChallenge
     */
    public void setPeerChallenge(byte[] peerChallenge) {
        this.peerChallenge = peerChallenge;
    }


    /**
     * Gets the response value for this MSCHAPv2Response.
     * 
     * @return response
     */
    public byte[] getResponse() {
        return response;
    }


    /**
     * Sets the response value for this MSCHAPv2Response.
     * 
     * @param response
     */
    public void setResponse(byte[] response) {
        this.response = response;
    }


    /**
     * Gets the userName value for this MSCHAPv2Response.
     * 
     * @return userName
     */
    public byte[] getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this MSCHAPv2Response.
     * 
     * @param userName
     */
    public void setUserName(byte[] userName) {
        this.userName = userName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MSCHAPv2Response)) return false;
        MSCHAPv2Response other = (MSCHAPv2Response) obj;
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
              java.util.Arrays.equals(this.challenge, other.getChallenge()))) &&
            ((this.peerChallenge==null && other.getPeerChallenge()==null) || 
             (this.peerChallenge!=null &&
              java.util.Arrays.equals(this.peerChallenge, other.getPeerChallenge()))) &&
            ((this.response==null && other.getResponse()==null) || 
             (this.response!=null &&
              java.util.Arrays.equals(this.response, other.getResponse()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              java.util.Arrays.equals(this.userName, other.getUserName())));
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
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getChallenge());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getChallenge(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPeerChallenge() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPeerChallenge());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPeerChallenge(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getResponse() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResponse());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResponse(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUserName() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUserName());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUserName(), i);
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
        new org.apache.axis.description.TypeDesc(MSCHAPv2Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MSCHAPv2Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("challenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "challenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("peerChallenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "peerChallenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("response");
        elemField.setXmlName(new javax.xml.namespace.QName("", "response"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"));
        elemField.setNillable(false);
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
