/**
 * CheckBill_Bankid.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class CheckBill_Bankid  implements java.io.Serializable {
    private java.lang.String ma_khang;

    private int hoadon_id;

    private java.lang.String bank_id;

    public CheckBill_Bankid() {
    }

    public CheckBill_Bankid(
           java.lang.String ma_khang,
           int hoadon_id,
           java.lang.String bank_id) {
           this.ma_khang = ma_khang;
           this.hoadon_id = hoadon_id;
           this.bank_id = bank_id;
    }


    /**
     * Gets the ma_khang value for this CheckBill_Bankid.
     * 
     * @return ma_khang
     */
    public java.lang.String getMa_khang() {
        return ma_khang;
    }


    /**
     * Sets the ma_khang value for this CheckBill_Bankid.
     * 
     * @param ma_khang
     */
    public void setMa_khang(java.lang.String ma_khang) {
        this.ma_khang = ma_khang;
    }


    /**
     * Gets the hoadon_id value for this CheckBill_Bankid.
     * 
     * @return hoadon_id
     */
    public int getHoadon_id() {
        return hoadon_id;
    }


    /**
     * Sets the hoadon_id value for this CheckBill_Bankid.
     * 
     * @param hoadon_id
     */
    public void setHoadon_id(int hoadon_id) {
        this.hoadon_id = hoadon_id;
    }


    /**
     * Gets the bank_id value for this CheckBill_Bankid.
     * 
     * @return bank_id
     */
    public java.lang.String getBank_id() {
        return bank_id;
    }


    /**
     * Sets the bank_id value for this CheckBill_Bankid.
     * 
     * @param bank_id
     */
    public void setBank_id(java.lang.String bank_id) {
        this.bank_id = bank_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckBill_Bankid)) return false;
        CheckBill_Bankid other = (CheckBill_Bankid) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ma_khang==null && other.getMa_khang()==null) || 
             (this.ma_khang!=null &&
              this.ma_khang.equals(other.getMa_khang()))) &&
            this.hoadon_id == other.getHoadon_id() &&
            ((this.bank_id==null && other.getBank_id()==null) || 
             (this.bank_id!=null &&
              this.bank_id.equals(other.getBank_id())));
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
        if (getMa_khang() != null) {
            _hashCode += getMa_khang().hashCode();
        }
        _hashCode += getHoadon_id();
        if (getBank_id() != null) {
            _hashCode += getBank_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckBill_Bankid.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">CheckBill_Bankid"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ma_khang");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ma_khang"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bank_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "bank_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
