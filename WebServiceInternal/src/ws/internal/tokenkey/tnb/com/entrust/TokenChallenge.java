/**
 * TokenChallenge.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb.com.entrust;

public class TokenChallenge  implements java.io.Serializable {
    private java.lang.String challenge;

    private ws.internal.tokenkey.tnb.com.entrust.TokenData[] tokens;

    private java.lang.Boolean hasTemporaryPIN;

    private java.lang.String temporaryPINCellAlphabet;

    private java.lang.Integer temporaryPINCellSize;

    private java.lang.Integer temporaryPINChallengeSize;

    private java.lang.Boolean allowDataSignature;

    public TokenChallenge() {
    }

    public TokenChallenge(
           java.lang.String challenge,
           ws.internal.tokenkey.tnb.com.entrust.TokenData[] tokens,
           java.lang.Boolean hasTemporaryPIN,
           java.lang.String temporaryPINCellAlphabet,
           java.lang.Integer temporaryPINCellSize,
           java.lang.Integer temporaryPINChallengeSize,
           java.lang.Boolean allowDataSignature) {
           this.challenge = challenge;
           this.tokens = tokens;
           this.hasTemporaryPIN = hasTemporaryPIN;
           this.temporaryPINCellAlphabet = temporaryPINCellAlphabet;
           this.temporaryPINCellSize = temporaryPINCellSize;
           this.temporaryPINChallengeSize = temporaryPINChallengeSize;
           this.allowDataSignature = allowDataSignature;
    }


    /**
     * Gets the challenge value for this TokenChallenge.
     * 
     * @return challenge
     */
    public java.lang.String getChallenge() {
        return challenge;
    }


    /**
     * Sets the challenge value for this TokenChallenge.
     * 
     * @param challenge
     */
    public void setChallenge(java.lang.String challenge) {
        this.challenge = challenge;
    }


    /**
     * Gets the tokens value for this TokenChallenge.
     * 
     * @return tokens
     */
    public ws.internal.tokenkey.tnb.com.entrust.TokenData[] getTokens() {
        return tokens;
    }


    /**
     * Sets the tokens value for this TokenChallenge.
     * 
     * @param tokens
     */
    public void setTokens(ws.internal.tokenkey.tnb.com.entrust.TokenData[] tokens) {
        this.tokens = tokens;
    }


    /**
     * Gets the hasTemporaryPIN value for this TokenChallenge.
     * 
     * @return hasTemporaryPIN
     */
    public java.lang.Boolean getHasTemporaryPIN() {
        return hasTemporaryPIN;
    }


    /**
     * Sets the hasTemporaryPIN value for this TokenChallenge.
     * 
     * @param hasTemporaryPIN
     */
    public void setHasTemporaryPIN(java.lang.Boolean hasTemporaryPIN) {
        this.hasTemporaryPIN = hasTemporaryPIN;
    }


    /**
     * Gets the temporaryPINCellAlphabet value for this TokenChallenge.
     * 
     * @return temporaryPINCellAlphabet
     */
    public java.lang.String getTemporaryPINCellAlphabet() {
        return temporaryPINCellAlphabet;
    }


    /**
     * Sets the temporaryPINCellAlphabet value for this TokenChallenge.
     * 
     * @param temporaryPINCellAlphabet
     */
    public void setTemporaryPINCellAlphabet(java.lang.String temporaryPINCellAlphabet) {
        this.temporaryPINCellAlphabet = temporaryPINCellAlphabet;
    }


    /**
     * Gets the temporaryPINCellSize value for this TokenChallenge.
     * 
     * @return temporaryPINCellSize
     */
    public java.lang.Integer getTemporaryPINCellSize() {
        return temporaryPINCellSize;
    }


    /**
     * Sets the temporaryPINCellSize value for this TokenChallenge.
     * 
     * @param temporaryPINCellSize
     */
    public void setTemporaryPINCellSize(java.lang.Integer temporaryPINCellSize) {
        this.temporaryPINCellSize = temporaryPINCellSize;
    }


    /**
     * Gets the temporaryPINChallengeSize value for this TokenChallenge.
     * 
     * @return temporaryPINChallengeSize
     */
    public java.lang.Integer getTemporaryPINChallengeSize() {
        return temporaryPINChallengeSize;
    }


    /**
     * Sets the temporaryPINChallengeSize value for this TokenChallenge.
     * 
     * @param temporaryPINChallengeSize
     */
    public void setTemporaryPINChallengeSize(java.lang.Integer temporaryPINChallengeSize) {
        this.temporaryPINChallengeSize = temporaryPINChallengeSize;
    }


    /**
     * Gets the allowDataSignature value for this TokenChallenge.
     * 
     * @return allowDataSignature
     */
    public java.lang.Boolean getAllowDataSignature() {
        return allowDataSignature;
    }


    /**
     * Sets the allowDataSignature value for this TokenChallenge.
     * 
     * @param allowDataSignature
     */
    public void setAllowDataSignature(java.lang.Boolean allowDataSignature) {
        this.allowDataSignature = allowDataSignature;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TokenChallenge)) return false;
        TokenChallenge other = (TokenChallenge) obj;
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
            ((this.tokens==null && other.getTokens()==null) || 
             (this.tokens!=null &&
              java.util.Arrays.equals(this.tokens, other.getTokens()))) &&
            ((this.hasTemporaryPIN==null && other.getHasTemporaryPIN()==null) || 
             (this.hasTemporaryPIN!=null &&
              this.hasTemporaryPIN.equals(other.getHasTemporaryPIN()))) &&
            ((this.temporaryPINCellAlphabet==null && other.getTemporaryPINCellAlphabet()==null) || 
             (this.temporaryPINCellAlphabet!=null &&
              this.temporaryPINCellAlphabet.equals(other.getTemporaryPINCellAlphabet()))) &&
            ((this.temporaryPINCellSize==null && other.getTemporaryPINCellSize()==null) || 
             (this.temporaryPINCellSize!=null &&
              this.temporaryPINCellSize.equals(other.getTemporaryPINCellSize()))) &&
            ((this.temporaryPINChallengeSize==null && other.getTemporaryPINChallengeSize()==null) || 
             (this.temporaryPINChallengeSize!=null &&
              this.temporaryPINChallengeSize.equals(other.getTemporaryPINChallengeSize()))) &&
            ((this.allowDataSignature==null && other.getAllowDataSignature()==null) || 
             (this.allowDataSignature!=null &&
              this.allowDataSignature.equals(other.getAllowDataSignature())));
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
        if (getTokens() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTokens());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTokens(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHasTemporaryPIN() != null) {
            _hashCode += getHasTemporaryPIN().hashCode();
        }
        if (getTemporaryPINCellAlphabet() != null) {
            _hashCode += getTemporaryPINCellAlphabet().hashCode();
        }
        if (getTemporaryPINCellSize() != null) {
            _hashCode += getTemporaryPINCellSize().hashCode();
        }
        if (getTemporaryPINChallengeSize() != null) {
            _hashCode += getTemporaryPINChallengeSize().hashCode();
        }
        if (getAllowDataSignature() != null) {
            _hashCode += getAllowDataSignature().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TokenChallenge.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "TokenChallenge"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("challenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "challenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tokens");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tokens"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "TokenData"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasTemporaryPIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hasTemporaryPIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("temporaryPINCellAlphabet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "temporaryPINCellAlphabet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("temporaryPINCellSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "temporaryPINCellSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("temporaryPINChallengeSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "temporaryPINChallengeSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowDataSignature");
        elemField.setXmlName(new javax.xml.namespace.QName("", "allowDataSignature"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
