/**
 * Check_Status_PayBills.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class Check_Status_PayBills  implements java.io.Serializable {
    private java.lang.String bankid;

    private java.lang.String tu_ngay;

    private java.lang.String den_ngay;

    public Check_Status_PayBills() {
    }

    public Check_Status_PayBills(
           java.lang.String bankid,
           java.lang.String tu_ngay,
           java.lang.String den_ngay) {
           this.bankid = bankid;
           this.tu_ngay = tu_ngay;
           this.den_ngay = den_ngay;
    }


    /**
     * Gets the bankid value for this Check_Status_PayBills.
     * 
     * @return bankid
     */
    public java.lang.String getBankid() {
        return bankid;
    }


    /**
     * Sets the bankid value for this Check_Status_PayBills.
     * 
     * @param bankid
     */
    public void setBankid(java.lang.String bankid) {
        this.bankid = bankid;
    }


    /**
     * Gets the tu_ngay value for this Check_Status_PayBills.
     * 
     * @return tu_ngay
     */
    public java.lang.String getTu_ngay() {
        return tu_ngay;
    }


    /**
     * Sets the tu_ngay value for this Check_Status_PayBills.
     * 
     * @param tu_ngay
     */
    public void setTu_ngay(java.lang.String tu_ngay) {
        this.tu_ngay = tu_ngay;
    }


    /**
     * Gets the den_ngay value for this Check_Status_PayBills.
     * 
     * @return den_ngay
     */
    public java.lang.String getDen_ngay() {
        return den_ngay;
    }


    /**
     * Sets the den_ngay value for this Check_Status_PayBills.
     * 
     * @param den_ngay
     */
    public void setDen_ngay(java.lang.String den_ngay) {
        this.den_ngay = den_ngay;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Check_Status_PayBills)) return false;
        Check_Status_PayBills other = (Check_Status_PayBills) obj;
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
            ((this.tu_ngay==null && other.getTu_ngay()==null) || 
             (this.tu_ngay!=null &&
              this.tu_ngay.equals(other.getTu_ngay()))) &&
            ((this.den_ngay==null && other.getDen_ngay()==null) || 
             (this.den_ngay!=null &&
              this.den_ngay.equals(other.getDen_ngay())));
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
        if (getTu_ngay() != null) {
            _hashCode += getTu_ngay().hashCode();
        }
        if (getDen_ngay() != null) {
            _hashCode += getDen_ngay().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Check_Status_PayBills.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">Check_Status_PayBills"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "bankid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tu_ngay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "tu_ngay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("den_ngay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "den_ngay"));
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
