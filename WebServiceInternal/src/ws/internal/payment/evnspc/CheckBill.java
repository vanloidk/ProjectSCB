/**
 * CheckBill.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class CheckBill  implements java.io.Serializable {
    private java.lang.String ma_dviqly;

    private int hoadon_id;

    public CheckBill() {
    }

    public CheckBill(
           java.lang.String ma_dviqly,
           int hoadon_id) {
           this.ma_dviqly = ma_dviqly;
           this.hoadon_id = hoadon_id;
    }


    /**
     * Gets the ma_dviqly value for this CheckBill.
     * 
     * @return ma_dviqly
     */
    public java.lang.String getMa_dviqly() {
        return ma_dviqly;
    }


    /**
     * Sets the ma_dviqly value for this CheckBill.
     * 
     * @param ma_dviqly
     */
    public void setMa_dviqly(java.lang.String ma_dviqly) {
        this.ma_dviqly = ma_dviqly;
    }


    /**
     * Gets the hoadon_id value for this CheckBill.
     * 
     * @return hoadon_id
     */
    public int getHoadon_id() {
        return hoadon_id;
    }


    /**
     * Sets the hoadon_id value for this CheckBill.
     * 
     * @param hoadon_id
     */
    public void setHoadon_id(int hoadon_id) {
        this.hoadon_id = hoadon_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckBill)) return false;
        CheckBill other = (CheckBill) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ma_dviqly==null && other.getMa_dviqly()==null) || 
             (this.ma_dviqly!=null &&
              this.ma_dviqly.equals(other.getMa_dviqly()))) &&
            this.hoadon_id == other.getHoadon_id();
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
        if (getMa_dviqly() != null) {
            _hashCode += getMa_dviqly().hashCode();
        }
        _hashCode += getHoadon_id();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckBill.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">CheckBill"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ma_dviqly");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ma_dviqly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hoadon_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "hoadon_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
