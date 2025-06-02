/**
 * Check_Status_Bills.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class Check_Status_Bills  implements java.io.Serializable {
    private java.lang.String bankid;

    private java.lang.String ma_dviqly;

    private int[] hoadon_id;

    public Check_Status_Bills() {
    }

    public Check_Status_Bills(
           java.lang.String bankid,
           java.lang.String ma_dviqly,
           int[] hoadon_id) {
           this.bankid = bankid;
           this.ma_dviqly = ma_dviqly;
           this.hoadon_id = hoadon_id;
    }


    /**
     * Gets the bankid value for this Check_Status_Bills.
     * 
     * @return bankid
     */
    public java.lang.String getBankid() {
        return bankid;
    }


    /**
     * Sets the bankid value for this Check_Status_Bills.
     * 
     * @param bankid
     */
    public void setBankid(java.lang.String bankid) {
        this.bankid = bankid;
    }


    /**
     * Gets the ma_dviqly value for this Check_Status_Bills.
     * 
     * @return ma_dviqly
     */
    public java.lang.String getMa_dviqly() {
        return ma_dviqly;
    }


    /**
     * Sets the ma_dviqly value for this Check_Status_Bills.
     * 
     * @param ma_dviqly
     */
    public void setMa_dviqly(java.lang.String ma_dviqly) {
        this.ma_dviqly = ma_dviqly;
    }


    /**
     * Gets the hoadon_id value for this Check_Status_Bills.
     * 
     * @return hoadon_id
     */
    public int[] getHoadon_id() {
        return hoadon_id;
    }


    /**
     * Sets the hoadon_id value for this Check_Status_Bills.
     * 
     * @param hoadon_id
     */
    public void setHoadon_id(int[] hoadon_id) {
        this.hoadon_id = hoadon_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Check_Status_Bills)) return false;
        Check_Status_Bills other = (Check_Status_Bills) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bankid==null && other.getBankid()==null) || 
             (this.bankid!=null &&
              this.bankid.equals(other.getBankid()))) &&
            ((this.ma_dviqly==null && other.getMa_dviqly()==null) || 
             (this.ma_dviqly!=null &&
              this.ma_dviqly.equals(other.getMa_dviqly()))) &&
            ((this.hoadon_id==null && other.getHoadon_id()==null) || 
             (this.hoadon_id!=null &&
              java.util.Arrays.equals(this.hoadon_id, other.getHoadon_id())));
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
        if (getBankid() != null) {
            _hashCode += getBankid().hashCode();
        }
        if (getMa_dviqly() != null) {
            _hashCode += getMa_dviqly().hashCode();
        }
        if (getHoadon_id() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHoadon_id());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHoadon_id(), i);
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
        new org.apache.axis.description.TypeDesc(Check_Status_Bills.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Check_Status_Bills"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "bankid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "int"));
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
