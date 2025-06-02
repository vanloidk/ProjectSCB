/**
 * AuthenticateAnonymousChallengeCallParms.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class AuthenticateAnonymousChallengeCallParms  implements java.io.Serializable {
    private java.lang.String userId;

    private ws.internal.tokenkey.tnb.com.entrust.ChallengeSet challengeSet;

    private ws.internal.tokenkey.tnb.Response response;

    private ws.internal.tokenkey.tnb.GenericAuthenticateParms parms;

    public AuthenticateAnonymousChallengeCallParms() {
    }

    public AuthenticateAnonymousChallengeCallParms(
           java.lang.String userId,
           ws.internal.tokenkey.tnb.com.entrust.ChallengeSet challengeSet,
           ws.internal.tokenkey.tnb.Response response,
           ws.internal.tokenkey.tnb.GenericAuthenticateParms parms) {
           this.userId = userId;
           this.challengeSet = challengeSet;
           this.response = response;
           this.parms = parms;
    }


    /**
     * Gets the userId value for this AuthenticateAnonymousChallengeCallParms.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this AuthenticateAnonymousChallengeCallParms.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the challengeSet value for this AuthenticateAnonymousChallengeCallParms.
     * 
     * @return challengeSet
     */
    public ws.internal.tokenkey.tnb.com.entrust.ChallengeSet getChallengeSet() {
        return challengeSet;
    }


    /**
     * Sets the challengeSet value for this AuthenticateAnonymousChallengeCallParms.
     * 
     * @param challengeSet
     */
    public void setChallengeSet(ws.internal.tokenkey.tnb.com.entrust.ChallengeSet challengeSet) {
        this.challengeSet = challengeSet;
    }


    /**
     * Gets the response value for this AuthenticateAnonymousChallengeCallParms.
     * 
     * @return response
     */
    public ws.internal.tokenkey.tnb.Response getResponse() {
        return response;
    }


    /**
     * Sets the response value for this AuthenticateAnonymousChallengeCallParms.
     * 
     * @param response
     */
    public void setResponse(ws.internal.tokenkey.tnb.Response response) {
        this.response = response;
    }


    /**
     * Gets the parms value for this AuthenticateAnonymousChallengeCallParms.
     * 
     * @return parms
     */
    public ws.internal.tokenkey.tnb.GenericAuthenticateParms getParms() {
        return parms;
    }


    /**
     * Sets the parms value for this AuthenticateAnonymousChallengeCallParms.
     * 
     * @param parms
     */
    public void setParms(ws.internal.tokenkey.tnb.GenericAuthenticateParms parms) {
        this.parms = parms;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AuthenticateAnonymousChallengeCallParms)) return false;
        AuthenticateAnonymousChallengeCallParms other = (AuthenticateAnonymousChallengeCallParms) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.challengeSet==null && other.getChallengeSet()==null) || 
             (this.challengeSet!=null &&
              this.challengeSet.equals(other.getChallengeSet()))) &&
            ((this.response==null && other.getResponse()==null) || 
             (this.response!=null &&
              this.response.equals(other.getResponse()))) &&
            ((this.parms==null && other.getParms()==null) || 
             (this.parms!=null &&
              this.parms.equals(other.getParms())));
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
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getChallengeSet() != null) {
            _hashCode += getChallengeSet().hashCode();
        }
        if (getResponse() != null) {
            _hashCode += getResponse().hashCode();
        }
        if (getParms() != null) {
            _hashCode += getParms().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AuthenticateAnonymousChallengeCallParms.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticateAnonymousChallengeCallParms"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("challengeSet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "challengeSet"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "ChallengeSet"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("response");
        elemField.setXmlName(new javax.xml.namespace.QName("", "response"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "Response"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parms");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parms"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericAuthenticateParms"));
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
