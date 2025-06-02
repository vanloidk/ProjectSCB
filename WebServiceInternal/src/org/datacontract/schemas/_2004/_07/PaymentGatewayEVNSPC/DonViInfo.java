/**
 * DonViInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC;

public class DonViInfo  implements java.io.Serializable {
    private java.lang.String diaChi;

    private java.lang.String maDonvi;

    private java.lang.String tenDonVi;

    public DonViInfo() {
    }

    public DonViInfo(
           java.lang.String diaChi,
           java.lang.String maDonvi,
           java.lang.String tenDonVi) {
           this.diaChi = diaChi;
           this.maDonvi = maDonvi;
           this.tenDonVi = tenDonVi;
    }


    /**
     * Gets the diaChi value for this DonViInfo.
     * 
     * @return diaChi
     */
    public java.lang.String getDiaChi() {
        return diaChi;
    }


    /**
     * Sets the diaChi value for this DonViInfo.
     * 
     * @param diaChi
     */
    public void setDiaChi(java.lang.String diaChi) {
        this.diaChi = diaChi;
    }


    /**
     * Gets the maDonvi value for this DonViInfo.
     * 
     * @return maDonvi
     */
    public java.lang.String getMaDonvi() {
        return maDonvi;
    }


    /**
     * Sets the maDonvi value for this DonViInfo.
     * 
     * @param maDonvi
     */
    public void setMaDonvi(java.lang.String maDonvi) {
        this.maDonvi = maDonvi;
    }


    /**
     * Gets the tenDonVi value for this DonViInfo.
     * 
     * @return tenDonVi
     */
    public java.lang.String getTenDonVi() {
        return tenDonVi;
    }


    /**
     * Sets the tenDonVi value for this DonViInfo.
     * 
     * @param tenDonVi
     */
    public void setTenDonVi(java.lang.String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DonViInfo)) return false;
        DonViInfo other = (DonViInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.diaChi==null && other.getDiaChi()==null) || 
             (this.diaChi!=null &&
              this.diaChi.equals(other.getDiaChi()))) &&
            ((this.maDonvi==null && other.getMaDonvi()==null) || 
             (this.maDonvi!=null &&
              this.maDonvi.equals(other.getMaDonvi()))) &&
            ((this.tenDonVi==null && other.getTenDonVi()==null) || 
             (this.tenDonVi!=null &&
              this.tenDonVi.equals(other.getTenDonVi())));
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
        if (getDiaChi() != null) {
            _hashCode += getDiaChi().hashCode();
        }
        if (getMaDonvi() != null) {
            _hashCode += getMaDonvi().hashCode();
        }
        if (getTenDonVi() != null) {
            _hashCode += getTenDonVi().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DonViInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "DonViInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diaChi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "DiaChi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maDonvi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "MaDonvi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tenDonVi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "TenDonVi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
