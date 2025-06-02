/**
 * GetDonViQuanLyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class GetDonViQuanLyResponse  implements java.io.Serializable {
    private ws.internal.payment.evnspc.GetDonViQuanLyResponseGetDonViQuanLyResult getDonViQuanLyResult;

    public GetDonViQuanLyResponse() {
    }

    public GetDonViQuanLyResponse(
           ws.internal.payment.evnspc.GetDonViQuanLyResponseGetDonViQuanLyResult getDonViQuanLyResult) {
           this.getDonViQuanLyResult = getDonViQuanLyResult;
    }


    /**
     * Gets the getDonViQuanLyResult value for this GetDonViQuanLyResponse.
     * 
     * @return getDonViQuanLyResult
     */
    public ws.internal.payment.evnspc.GetDonViQuanLyResponseGetDonViQuanLyResult getGetDonViQuanLyResult() {
        return getDonViQuanLyResult;
    }


    /**
     * Sets the getDonViQuanLyResult value for this GetDonViQuanLyResponse.
     * 
     * @param getDonViQuanLyResult
     */
    public void setGetDonViQuanLyResult(ws.internal.payment.evnspc.GetDonViQuanLyResponseGetDonViQuanLyResult getDonViQuanLyResult) {
        this.getDonViQuanLyResult = getDonViQuanLyResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDonViQuanLyResponse)) return false;
        GetDonViQuanLyResponse other = (GetDonViQuanLyResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getDonViQuanLyResult==null && other.getGetDonViQuanLyResult()==null) || 
             (this.getDonViQuanLyResult!=null &&
              this.getDonViQuanLyResult.equals(other.getGetDonViQuanLyResult())));
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
        if (getGetDonViQuanLyResult() != null) {
            _hashCode += getGetDonViQuanLyResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDonViQuanLyResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetDonViQuanLyResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getDonViQuanLyResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetDonViQuanLyResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetDonViQuanLyResponse>GetDonViQuanLyResult"));
        elemField.setMinOccurs(0);
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
