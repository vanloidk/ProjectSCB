/**
 * RadiusResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class RadiusResponse  implements java.io.Serializable {
    private ws.internal.tokenkey.tnb.PAPResponse pap;

    private ws.internal.tokenkey.tnb.CHAPResponse chap;

    private ws.internal.tokenkey.tnb.MSCHAPv1Response MSCHAPv1;

    private ws.internal.tokenkey.tnb.MSCHAPv2Response MSCHAPv2;

    public RadiusResponse() {
    }

    public RadiusResponse(
           ws.internal.tokenkey.tnb.PAPResponse pap,
           ws.internal.tokenkey.tnb.CHAPResponse chap,
           ws.internal.tokenkey.tnb.MSCHAPv1Response MSCHAPv1,
           ws.internal.tokenkey.tnb.MSCHAPv2Response MSCHAPv2) {
           this.pap = pap;
           this.chap = chap;
           this.MSCHAPv1 = MSCHAPv1;
           this.MSCHAPv2 = MSCHAPv2;
    }


    /**
     * Gets the pap value for this RadiusResponse.
     * 
     * @return pap
     */
    public ws.internal.tokenkey.tnb.PAPResponse getPap() {
        return pap;
    }


    /**
     * Sets the pap value for this RadiusResponse.
     * 
     * @param pap
     */
    public void setPap(ws.internal.tokenkey.tnb.PAPResponse pap) {
        this.pap = pap;
    }


    /**
     * Gets the chap value for this RadiusResponse.
     * 
     * @return chap
     */
    public ws.internal.tokenkey.tnb.CHAPResponse getChap() {
        return chap;
    }


    /**
     * Sets the chap value for this RadiusResponse.
     * 
     * @param chap
     */
    public void setChap(ws.internal.tokenkey.tnb.CHAPResponse chap) {
        this.chap = chap;
    }


    /**
     * Gets the MSCHAPv1 value for this RadiusResponse.
     * 
     * @return MSCHAPv1
     */
    public ws.internal.tokenkey.tnb.MSCHAPv1Response getMSCHAPv1() {
        return MSCHAPv1;
    }


    /**
     * Sets the MSCHAPv1 value for this RadiusResponse.
     * 
     * @param MSCHAPv1
     */
    public void setMSCHAPv1(ws.internal.tokenkey.tnb.MSCHAPv1Response MSCHAPv1) {
        this.MSCHAPv1 = MSCHAPv1;
    }


    /**
     * Gets the MSCHAPv2 value for this RadiusResponse.
     * 
     * @return MSCHAPv2
     */
    public ws.internal.tokenkey.tnb.MSCHAPv2Response getMSCHAPv2() {
        return MSCHAPv2;
    }


    /**
     * Sets the MSCHAPv2 value for this RadiusResponse.
     * 
     * @param MSCHAPv2
     */
    public void setMSCHAPv2(ws.internal.tokenkey.tnb.MSCHAPv2Response MSCHAPv2) {
        this.MSCHAPv2 = MSCHAPv2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RadiusResponse)) return false;
        RadiusResponse other = (RadiusResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pap==null && other.getPap()==null) || 
             (this.pap!=null &&
              this.pap.equals(other.getPap()))) &&
            ((this.chap==null && other.getChap()==null) || 
             (this.chap!=null &&
              this.chap.equals(other.getChap()))) &&
            ((this.MSCHAPv1==null && other.getMSCHAPv1()==null) || 
             (this.MSCHAPv1!=null &&
              this.MSCHAPv1.equals(other.getMSCHAPv1()))) &&
            ((this.MSCHAPv2==null && other.getMSCHAPv2()==null) || 
             (this.MSCHAPv2!=null &&
              this.MSCHAPv2.equals(other.getMSCHAPv2())));
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
        if (getPap() != null) {
            _hashCode += getPap().hashCode();
        }
        if (getChap() != null) {
            _hashCode += getChap().hashCode();
        }
        if (getMSCHAPv1() != null) {
            _hashCode += getMSCHAPv1().hashCode();
        }
        if (getMSCHAPv2() != null) {
            _hashCode += getMSCHAPv2().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RadiusResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "RadiusResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pap");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pap"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "PAPResponse"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chap");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chap"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "CHAPResponse"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MSCHAPv1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MSCHAPv1"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MSCHAPv1Response"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MSCHAPv2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MSCHAPv2"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MSCHAPv2Response"));
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
