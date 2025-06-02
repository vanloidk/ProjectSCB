/**
 * OTPChallenge.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class OTPChallenge  implements java.io.Serializable {
    private java.lang.Boolean manualDeliveryRequired;

    private ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanismUsed;

    private ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanismFailed;

    private ws.internal.tokenkey.tnb.AuthenticationFault[] deliveryMechanismFailureReason;

    private ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanism;

    private boolean dynamicRefresh;

    private java.lang.Boolean needsDeliveryForChallenge;

    private java.lang.Boolean needsDeliveryForAuthenticate;

    public OTPChallenge() {
    }

    public OTPChallenge(
           java.lang.Boolean manualDeliveryRequired,
           ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanismUsed,
           ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanismFailed,
           ws.internal.tokenkey.tnb.AuthenticationFault[] deliveryMechanismFailureReason,
           ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanism,
           boolean dynamicRefresh,
           java.lang.Boolean needsDeliveryForChallenge,
           java.lang.Boolean needsDeliveryForAuthenticate) {
           this.manualDeliveryRequired = manualDeliveryRequired;
           this.deliveryMechanismUsed = deliveryMechanismUsed;
           this.deliveryMechanismFailed = deliveryMechanismFailed;
           this.deliveryMechanismFailureReason = deliveryMechanismFailureReason;
           this.deliveryMechanism = deliveryMechanism;
           this.dynamicRefresh = dynamicRefresh;
           this.needsDeliveryForChallenge = needsDeliveryForChallenge;
           this.needsDeliveryForAuthenticate = needsDeliveryForAuthenticate;
    }


    /**
     * Gets the manualDeliveryRequired value for this OTPChallenge.
     * 
     * @return manualDeliveryRequired
     */
    public java.lang.Boolean getManualDeliveryRequired() {
        return manualDeliveryRequired;
    }


    /**
     * Sets the manualDeliveryRequired value for this OTPChallenge.
     * 
     * @param manualDeliveryRequired
     */
    public void setManualDeliveryRequired(java.lang.Boolean manualDeliveryRequired) {
        this.manualDeliveryRequired = manualDeliveryRequired;
    }


    /**
     * Gets the deliveryMechanismUsed value for this OTPChallenge.
     * 
     * @return deliveryMechanismUsed
     */
    public ws.internal.tokenkey.tnb.DeliveryMechanism[] getDeliveryMechanismUsed() {
        return deliveryMechanismUsed;
    }


    /**
     * Sets the deliveryMechanismUsed value for this OTPChallenge.
     * 
     * @param deliveryMechanismUsed
     */
    public void setDeliveryMechanismUsed(ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanismUsed) {
        this.deliveryMechanismUsed = deliveryMechanismUsed;
    }


    /**
     * Gets the deliveryMechanismFailed value for this OTPChallenge.
     * 
     * @return deliveryMechanismFailed
     */
    public ws.internal.tokenkey.tnb.DeliveryMechanism[] getDeliveryMechanismFailed() {
        return deliveryMechanismFailed;
    }


    /**
     * Sets the deliveryMechanismFailed value for this OTPChallenge.
     * 
     * @param deliveryMechanismFailed
     */
    public void setDeliveryMechanismFailed(ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanismFailed) {
        this.deliveryMechanismFailed = deliveryMechanismFailed;
    }


    /**
     * Gets the deliveryMechanismFailureReason value for this OTPChallenge.
     * 
     * @return deliveryMechanismFailureReason
     */
    public ws.internal.tokenkey.tnb.AuthenticationFault[] getDeliveryMechanismFailureReason() {
        return deliveryMechanismFailureReason;
    }


    /**
     * Sets the deliveryMechanismFailureReason value for this OTPChallenge.
     * 
     * @param deliveryMechanismFailureReason
     */
    public void setDeliveryMechanismFailureReason(ws.internal.tokenkey.tnb.AuthenticationFault[] deliveryMechanismFailureReason) {
        this.deliveryMechanismFailureReason = deliveryMechanismFailureReason;
    }


    /**
     * Gets the deliveryMechanism value for this OTPChallenge.
     * 
     * @return deliveryMechanism
     */
    public ws.internal.tokenkey.tnb.DeliveryMechanism[] getDeliveryMechanism() {
        return deliveryMechanism;
    }


    /**
     * Sets the deliveryMechanism value for this OTPChallenge.
     * 
     * @param deliveryMechanism
     */
    public void setDeliveryMechanism(ws.internal.tokenkey.tnb.DeliveryMechanism[] deliveryMechanism) {
        this.deliveryMechanism = deliveryMechanism;
    }


    /**
     * Gets the dynamicRefresh value for this OTPChallenge.
     * 
     * @return dynamicRefresh
     */
    public boolean isDynamicRefresh() {
        return dynamicRefresh;
    }


    /**
     * Sets the dynamicRefresh value for this OTPChallenge.
     * 
     * @param dynamicRefresh
     */
    public void setDynamicRefresh(boolean dynamicRefresh) {
        this.dynamicRefresh = dynamicRefresh;
    }


    /**
     * Gets the needsDeliveryForChallenge value for this OTPChallenge.
     * 
     * @return needsDeliveryForChallenge
     */
    public java.lang.Boolean getNeedsDeliveryForChallenge() {
        return needsDeliveryForChallenge;
    }


    /**
     * Sets the needsDeliveryForChallenge value for this OTPChallenge.
     * 
     * @param needsDeliveryForChallenge
     */
    public void setNeedsDeliveryForChallenge(java.lang.Boolean needsDeliveryForChallenge) {
        this.needsDeliveryForChallenge = needsDeliveryForChallenge;
    }


    /**
     * Gets the needsDeliveryForAuthenticate value for this OTPChallenge.
     * 
     * @return needsDeliveryForAuthenticate
     */
    public java.lang.Boolean getNeedsDeliveryForAuthenticate() {
        return needsDeliveryForAuthenticate;
    }


    /**
     * Sets the needsDeliveryForAuthenticate value for this OTPChallenge.
     * 
     * @param needsDeliveryForAuthenticate
     */
    public void setNeedsDeliveryForAuthenticate(java.lang.Boolean needsDeliveryForAuthenticate) {
        this.needsDeliveryForAuthenticate = needsDeliveryForAuthenticate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OTPChallenge)) return false;
        OTPChallenge other = (OTPChallenge) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.manualDeliveryRequired==null && other.getManualDeliveryRequired()==null) || 
             (this.manualDeliveryRequired!=null &&
              this.manualDeliveryRequired.equals(other.getManualDeliveryRequired()))) &&
            ((this.deliveryMechanismUsed==null && other.getDeliveryMechanismUsed()==null) || 
             (this.deliveryMechanismUsed!=null &&
              java.util.Arrays.equals(this.deliveryMechanismUsed, other.getDeliveryMechanismUsed()))) &&
            ((this.deliveryMechanismFailed==null && other.getDeliveryMechanismFailed()==null) || 
             (this.deliveryMechanismFailed!=null &&
              java.util.Arrays.equals(this.deliveryMechanismFailed, other.getDeliveryMechanismFailed()))) &&
            ((this.deliveryMechanismFailureReason==null && other.getDeliveryMechanismFailureReason()==null) || 
             (this.deliveryMechanismFailureReason!=null &&
              java.util.Arrays.equals(this.deliveryMechanismFailureReason, other.getDeliveryMechanismFailureReason()))) &&
            ((this.deliveryMechanism==null && other.getDeliveryMechanism()==null) || 
             (this.deliveryMechanism!=null &&
              java.util.Arrays.equals(this.deliveryMechanism, other.getDeliveryMechanism()))) &&
            this.dynamicRefresh == other.isDynamicRefresh() &&
            ((this.needsDeliveryForChallenge==null && other.getNeedsDeliveryForChallenge()==null) || 
             (this.needsDeliveryForChallenge!=null &&
              this.needsDeliveryForChallenge.equals(other.getNeedsDeliveryForChallenge()))) &&
            ((this.needsDeliveryForAuthenticate==null && other.getNeedsDeliveryForAuthenticate()==null) || 
             (this.needsDeliveryForAuthenticate!=null &&
              this.needsDeliveryForAuthenticate.equals(other.getNeedsDeliveryForAuthenticate())));
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
        if (getManualDeliveryRequired() != null) {
            _hashCode += getManualDeliveryRequired().hashCode();
        }
        if (getDeliveryMechanismUsed() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDeliveryMechanismUsed());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDeliveryMechanismUsed(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDeliveryMechanismFailed() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDeliveryMechanismFailed());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDeliveryMechanismFailed(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDeliveryMechanismFailureReason() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDeliveryMechanismFailureReason());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDeliveryMechanismFailureReason(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDeliveryMechanism() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDeliveryMechanism());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDeliveryMechanism(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += (isDynamicRefresh() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNeedsDeliveryForChallenge() != null) {
            _hashCode += getNeedsDeliveryForChallenge().hashCode();
        }
        if (getNeedsDeliveryForAuthenticate() != null) {
            _hashCode += getNeedsDeliveryForAuthenticate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OTPChallenge.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "OTPChallenge"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualDeliveryRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualDeliveryRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryMechanismUsed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryMechanismUsed"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "DeliveryMechanism"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryMechanismFailed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryMechanismFailed"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "DeliveryMechanism"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryMechanismFailureReason");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryMechanismFailureReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationFault"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryMechanism");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deliveryMechanism"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "DeliveryMechanism"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dynamicRefresh");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dynamicRefresh"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("needsDeliveryForChallenge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "needsDeliveryForChallenge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("needsDeliveryForAuthenticate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "needsDeliveryForAuthenticate"));
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
