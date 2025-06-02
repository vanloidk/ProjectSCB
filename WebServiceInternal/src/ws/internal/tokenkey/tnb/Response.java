/**
 * Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class Response  implements java.io.Serializable {
    private java.lang.String PVN;

    private java.lang.String[] response;

    private ws.internal.tokenkey.tnb.RadiusResponse radiusResponse;

    public Response() {
    }

    public Response(
           java.lang.String PVN,
           java.lang.String[] response,
           ws.internal.tokenkey.tnb.RadiusResponse radiusResponse) {
           this.PVN = PVN;
           this.response = response;
           this.radiusResponse = radiusResponse;
    }


    /**
     * Gets the PVN value for this Response.
     * 
     * @return PVN
     */
    public java.lang.String getPVN() {
        return PVN;
    }


    /**
     * Sets the PVN value for this Response.
     * 
     * @param PVN
     */
    public void setPVN(java.lang.String PVN) {
        this.PVN = PVN;
    }


    /**
     * Gets the response value for this Response.
     * 
     * @return response
     */
    public java.lang.String[] getResponse() {
        return response;
    }


    /**
     * Sets the response value for this Response.
     * 
     * @param response
     */
    public void setResponse(java.lang.String[] response) {
        this.response = response;
    }


    /**
     * Gets the radiusResponse value for this Response.
     * 
     * @return radiusResponse
     */
    public ws.internal.tokenkey.tnb.RadiusResponse getRadiusResponse() {
        return radiusResponse;
    }


    /**
     * Sets the radiusResponse value for this Response.
     * 
     * @param radiusResponse
     */
    public void setRadiusResponse(ws.internal.tokenkey.tnb.RadiusResponse radiusResponse) {
        this.radiusResponse = radiusResponse;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Response)) return false;
        Response other = (Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.PVN==null && other.getPVN()==null) || 
             (this.PVN!=null &&
              this.PVN.equals(other.getPVN()))) &&
            ((this.response==null && other.getResponse()==null) || 
             (this.response!=null &&
              java.util.Arrays.equals(this.response, other.getResponse()))) &&
            ((this.radiusResponse==null && other.getRadiusResponse()==null) || 
             (this.radiusResponse!=null &&
              this.radiusResponse.equals(other.getRadiusResponse())));
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
        if (getPVN() != null) {
            _hashCode += getPVN().hashCode();
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
        if (getRadiusResponse() != null) {
            _hashCode += getRadiusResponse().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PVN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PVN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("response");
        elemField.setXmlName(new javax.xml.namespace.QName("", "response"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radiusResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "radiusResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "RadiusResponse"));
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
