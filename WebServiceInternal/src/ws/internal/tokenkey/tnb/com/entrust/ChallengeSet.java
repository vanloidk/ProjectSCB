/**
 * ChallengeSet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb.com.entrust;

public class ChallengeSet  implements java.io.Serializable {
    private java.lang.String cardCellAlphabet;

    private int cardCellSize;

    private java.lang.String[] cardSerialNumbers;

    private ws.internal.tokenkey.tnb.com.entrust.Challenge[] challenge;

    private java.lang.String temporaryPinCellAlphabet;

    private int temporaryPinCellSize;

    private int temporaryPinChallengeSize;

    private boolean userHasTemporaryPin;

    public ChallengeSet() {
    }

    public ChallengeSet(
           java.lang.String cardCellAlphabet,
           int cardCellSize,
           java.lang.String[] cardSerialNumbers,
           ws.internal.tokenkey.tnb.com.entrust.Challenge[] challenge,
           java.lang.String temporaryPinCellAlphabet,
           int temporaryPinCellSize,
           int temporaryPinChallengeSize,
           boolean userHasTemporaryPin) {
           this.cardCellAlphabet = cardCellAlphabet;
           this.cardCellSize = cardCellSize;
           this.cardSerialNumbers = cardSerialNumbers;
           this.challenge = challenge;
           this.temporaryPinCellAlphabet = temporaryPinCellAlphabet;
           this.temporaryPinCellSize = temporaryPinCellSize;
           this.temporaryPinChallengeSize = temporaryPinChallengeSize;
           this.userHasTemporaryPin = userHasTemporaryPin;
    }


    /**
     * Gets the cardCellAlphabet value for this ChallengeSet.
     * 
     * @return cardCellAlphabet
     */
    public java.lang.String getCardCellAlphabet() {
        return cardCellAlphabet;
    }


    /**
     * Sets the cardCellAlphabet value for this ChallengeSet.
     * 
     * @param cardCellAlphabet
     */
    public void setCardCellAlphabet(java.lang.String cardCellAlphabet) {
        this.cardCellAlphabet = cardCellAlphabet;
    }


    /**
     * Gets the cardCellSize value for this ChallengeSet.
     * 
     * @return cardCellSize
     */
    public int getCardCellSize() {
        return cardCellSize;
    }


    /**
     * Sets the cardCellSize value for this ChallengeSet.
     * 
     * @param cardCellSize
     */
    public void setCardCellSize(int cardCellSize) {
        this.cardCellSize = cardCellSize;
    }


    /**
     * Gets the cardSerialNumbers value for this ChallengeSet.
     * 
     * @return cardSerialNumbers
     */
    public java.lang.String[] getCardSerialNumbers() {
        return cardSerialNumbers;
    }


    /**
     * Sets the cardSerialNumbers value for this ChallengeSet.
     * 
     * @param cardSerialNumbers
     */
    public void setCardSerialNumbers(java.lang.String[] cardSerialNumbers) {
        this.cardSerialNumbers = cardSerialNumbers;
    }


    /**
     * Gets the challenge value for this ChallengeSet.
     * 
     * @return challenge
     */
    public ws.internal.tokenkey.tnb.com.entrust.Challenge[] getChallenge() {
        return challenge;
    }


    /**
     * Sets the challenge value for this ChallengeSet.
     * 
     * @param challenge
     */
    public void setChallenge(ws.internal.tokenkey.tnb.com.entrust.Challenge[] challenge) {
        this.challenge = challenge;
    }


    /**
     * Gets the temporaryPinCellAlphabet value for this ChallengeSet.
     * 
     * @return temporaryPinCellAlphabet
     */
    public java.lang.String getTemporaryPinCellAlphabet() {
        return temporaryPinCellAlphabet;
    }


    /**
     * Sets the temporaryPinCellAlphabet value for this ChallengeSet.
     * 
     * @param temporaryPinCellAlphabet
     */
    public void setTemporaryPinCellAlphabet(java.lang.String temporaryPinCellAlphabet) {
        this.temporaryPinCellAlphabet = temporaryPinCellAlphabet;
    }


    /**
     * Gets the temporaryPinCellSize value for this ChallengeSet.
     * 
     * @return temporaryPinCellSize
     */
    public int getTemporaryPinCellSize() {
        return temporaryPinCellSize;
    }


    /**
     * Sets the temporaryPinCellSize value for this ChallengeSet.
     * 
     * @param temporaryPinCellSize
     */
    public void setTemporaryPinCellSize(int temporaryPinCellSize) {
        this.temporaryPinCellSize = temporaryPinCellSize;
    }


    /**
     * Gets the temporaryPinChallengeSize value for this ChallengeSet.
     * 
     * @return temporaryPinChallengeSize
     */
    public int getTemporaryPinChallengeSize() {
        return temporaryPinChallengeSize;
    }


    /**
     * Sets the temporaryPinChallengeSize value for this ChallengeSet.
     * 
     * @param temporaryPinChallengeSize
     */
    public void setTemporaryPinChallengeSize(int temporaryPinChallengeSize) {
        this.temporaryPinChallengeSize = temporaryPinChallengeSize;
    }


    /**
     * Gets the userHasTemporaryPin value for this ChallengeSet.
     * 
     * @return userHasTemporaryPin
     */
    public boolean isUserHasTemporaryPin() {
        return userHasTemporaryPin;
    }


    /**
     * Sets the userHasTemporaryPin value for this ChallengeSet.
     * 
     * @param userHasTemporaryPin
     */
    public void setUserHasTemporaryPin(boolean userHasTemporaryPin) {
        this.userHasTemporaryPin = userHasTemporaryPin;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ChallengeSet)) return false;
        ChallengeSet other = (ChallengeSet) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cardCellAlphabet==null && other.getCardCellAlphabet()==null) || 
             (this.cardCellAlphabet!=null &&
              this.cardCellAlphabet.equals(other.getCardCellAlphabet()))) &&
            this.cardCellSize == other.getCardCellSize() &&
            ((this.cardSerialNumbers==null && other.getCardSerialNumbers()==null) || 
             (this.cardSerialNumbers!=null &&
              java.util.Arrays.equals(this.cardSerialNumbers, other.getCardSerialNumbers()))) &&
            ((this.challenge==null && other.getChallenge()==null) || 
             (this.challenge!=null &&
              java.util.Arrays.equals(this.challenge, other.getChallenge()))) &&
            ((this.temporaryPinCellAlphabet==null && other.getTemporaryPinCellAlphabet()==null) || 
             (this.temporaryPinCellAlphabet!=null &&
              this.temporaryPinCellAlphabet.equals(other.getTemporaryPinCellAlphabet()))) &&
            this.temporaryPinCellSize == other.getTemporaryPinCellSize() &&
            this.temporaryPinChallengeSize == other.getTemporaryPinChallengeSize() &&
            this.userHasTemporaryPin == other.isUserHasTemporaryPin();
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
        if (getCardCellAlphabet() != null) {
            _hashCode += getCardCellAlphabet().hashCode();
        }
        _hashCode += getCardCellSize();
        if (getCardSerialNumbers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCardSerialNumbers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCardSerialNumbers(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
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
        if (getTemporaryPinCellAlphabet() != null) {
            _hashCode += getTemporaryPinCellAlphabet().hashCode();
        }
        _hashCode += getTemporaryPinCellSize();
        _hashCode += getTemporaryPinChallengeSize();
        _hashCode += (isUserHasTemporaryPin() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ChallengeSet.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "ChallengeSet"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardCellAlphabet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardCellAlphabet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardCellSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardCellSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardSerialNumbers");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardSerialNumbers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("challenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "challenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "Challenge"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("temporaryPinCellAlphabet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "temporaryPinCellAlphabet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("temporaryPinCellSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "temporaryPinCellSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("temporaryPinChallengeSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "temporaryPinChallengeSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userHasTemporaryPin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userHasTemporaryPin"));
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
