/**
 * GetBillByBank.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class GetBillByBank  implements java.io.Serializable {
    private java.lang.String bankID;

    private java.lang.String maDonViQL;

    private java.lang.String tuNgay;

    private java.lang.String denNgay;

    private java.lang.String loaiGiaoDich;

    public GetBillByBank() {
    }

    public GetBillByBank(
           java.lang.String bankID,
           java.lang.String maDonViQL,
           java.lang.String tuNgay,
           java.lang.String denNgay,
           java.lang.String loaiGiaoDich) {
           this.bankID = bankID;
           this.maDonViQL = maDonViQL;
           this.tuNgay = tuNgay;
           this.denNgay = denNgay;
           this.loaiGiaoDich = loaiGiaoDich;
    }


    /**
     * Gets the bankID value for this GetBillByBank.
     * 
     * @return bankID
     */
    public java.lang.String getBankID() {
        return bankID;
    }


    /**
     * Sets the bankID value for this GetBillByBank.
     * 
     * @param bankID
     */
    public void setBankID(java.lang.String bankID) {
        this.bankID = bankID;
    }


    /**
     * Gets the maDonViQL value for this GetBillByBank.
     * 
     * @return maDonViQL
     */
    public java.lang.String getMaDonViQL() {
        return maDonViQL;
    }


    /**
     * Sets the maDonViQL value for this GetBillByBank.
     * 
     * @param maDonViQL
     */
    public void setMaDonViQL(java.lang.String maDonViQL) {
        this.maDonViQL = maDonViQL;
    }


    /**
     * Gets the tuNgay value for this GetBillByBank.
     * 
     * @return tuNgay
     */
    public java.lang.String getTuNgay() {
        return tuNgay;
    }


    /**
     * Sets the tuNgay value for this GetBillByBank.
     * 
     * @param tuNgay
     */
    public void setTuNgay(java.lang.String tuNgay) {
        this.tuNgay = tuNgay;
    }


    /**
     * Gets the denNgay value for this GetBillByBank.
     * 
     * @return denNgay
     */
    public java.lang.String getDenNgay() {
        return denNgay;
    }


    /**
     * Sets the denNgay value for this GetBillByBank.
     * 
     * @param denNgay
     */
    public void setDenNgay(java.lang.String denNgay) {
        this.denNgay = denNgay;
    }


    /**
     * Gets the loaiGiaoDich value for this GetBillByBank.
     * 
     * @return loaiGiaoDich
     */
    public java.lang.String getLoaiGiaoDich() {
        return loaiGiaoDich;
    }


    /**
     * Sets the loaiGiaoDich value for this GetBillByBank.
     * 
     * @param loaiGiaoDich
     */
    public void setLoaiGiaoDich(java.lang.String loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBillByBank)) return false;
        GetBillByBank other = (GetBillByBank) obj;
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
            ((this.maDonViQL==null && other.getMaDonViQL()==null) || 
             (this.maDonViQL!=null &&
              this.maDonViQL.equals(other.getMaDonViQL()))) &&
            ((this.tuNgay==null && other.getTuNgay()==null) || 
             (this.tuNgay!=null &&
              this.tuNgay.equals(other.getTuNgay()))) &&
            ((this.denNgay==null && other.getDenNgay()==null) || 
             (this.denNgay!=null &&
              this.denNgay.equals(other.getDenNgay()))) &&
            ((this.loaiGiaoDich==null && other.getLoaiGiaoDich()==null) || 
             (this.loaiGiaoDich!=null &&
              this.loaiGiaoDich.equals(other.getLoaiGiaoDich())));
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
        if (getMaDonViQL() != null) {
            _hashCode += getMaDonViQL().hashCode();
        }
        if (getTuNgay() != null) {
            _hashCode += getTuNgay().hashCode();
        }
        if (getDenNgay() != null) {
            _hashCode += getDenNgay().hashCode();
        }
        if (getLoaiGiaoDich() != null) {
            _hashCode += getLoaiGiaoDich().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetBillByBank.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetBillByBank"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BankID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maDonViQL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MaDonViQL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tuNgay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TuNgay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("denNgay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DenNgay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loaiGiaoDich");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LoaiGiaoDich"));
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
