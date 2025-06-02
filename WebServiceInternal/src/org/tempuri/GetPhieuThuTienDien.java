/**
 * GetPhieuThuTienDien.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BillEVNSPC")
public class GetPhieuThuTienDien  implements java.io.Serializable {
    private java.lang.String bankID;

    private java.lang.String maGiaoDich;

    private java.lang.Integer hoaDonID;

    private java.lang.Long soTien;

    public GetPhieuThuTienDien() {
    }

    public GetPhieuThuTienDien(
           java.lang.String bankID,
           java.lang.String maGiaoDich,
           java.lang.Integer hoaDonID,
           java.lang.Long soTien) {
           this.bankID = bankID;
           this.maGiaoDich = maGiaoDich;
           this.hoaDonID = hoaDonID;
           this.soTien = soTien;
    }


    /**
     * Gets the bankID value for this GetPhieuThuTienDien.
     * 
     * @return bankID
     */
    @XmlElement(name = "BANKID")
    public java.lang.String getBankID() {
        return bankID;
    }


    /**
     * Sets the bankID value for this GetPhieuThuTienDien.
     * 
     * @param bankID
     */
    public void setBankID(java.lang.String bankID) {
        this.bankID = bankID;
    }


    /**
     * Gets the maGiaoDich value for this GetPhieuThuTienDien.
     * 
     * @return maGiaoDich
     */
    @XmlElement(name = "MAGIAODICH")
    public java.lang.String getMaGiaoDich() {
        return maGiaoDich;
    }


    /**
     * Sets the maGiaoDich value for this GetPhieuThuTienDien.
     * 
     * @param maGiaoDich
     */
    public void setMaGiaoDich(java.lang.String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }


    /**
     * Gets the hoaDonID value for this GetPhieuThuTienDien.
     * 
     * @return hoaDonID
     */
    @XmlElement(name = "HOADONID")
    public java.lang.Integer getHoaDonID() {
        return hoaDonID;
    }


    /**
     * Sets the hoaDonID value for this GetPhieuThuTienDien.
     * 
     * @param hoaDonID
     */
    public void setHoaDonID(java.lang.Integer hoaDonID) {
        this.hoaDonID = hoaDonID;
    }


    /**
     * Gets the soTien value for this GetPhieuThuTienDien.
     * 
     * @return soTien
     */
    @XmlElement(name = "SOTIEN")
    public java.lang.Long getSoTien() {
        return soTien;
    }


    /**
     * Sets the soTien value for this GetPhieuThuTienDien.
     * 
     * @param soTien
     */
    public void setSoTien(java.lang.Long soTien) {
        this.soTien = soTien;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetPhieuThuTienDien)) return false;
        GetPhieuThuTienDien other = (GetPhieuThuTienDien) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bankID==null && other.getBankID()==null) || 
             (this.bankID!=null &&
              this.bankID.equals(other.getBankID()))) &&
            ((this.maGiaoDich==null && other.getMaGiaoDich()==null) || 
             (this.maGiaoDich!=null &&
              this.maGiaoDich.equals(other.getMaGiaoDich()))) &&
            ((this.hoaDonID==null && other.getHoaDonID()==null) || 
             (this.hoaDonID!=null &&
              this.hoaDonID.equals(other.getHoaDonID()))) &&
            ((this.soTien==null && other.getSoTien()==null) || 
             (this.soTien!=null &&
              this.soTien.equals(other.getSoTien())));
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
        if (getBankID() != null) {
            _hashCode += getBankID().hashCode();
        }
        if (getMaGiaoDich() != null) {
            _hashCode += getMaGiaoDich().hashCode();
        }
        if (getHoaDonID() != null) {
            _hashCode += getHoaDonID().hashCode();
        }
        if (getSoTien() != null) {
            _hashCode += getSoTien().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPhieuThuTienDien.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetPhieuThuTienDien"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BankID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maGiaoDich");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MaGiaoDich"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hoaDonID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "HoaDonID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("soTien");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SoTien"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
