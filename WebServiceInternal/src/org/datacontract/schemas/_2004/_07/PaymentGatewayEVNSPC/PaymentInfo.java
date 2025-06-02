/**
 * PaymentInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC;

public class PaymentInfo  implements java.io.Serializable {
    private java.lang.String address;

    private java.lang.Integer amount;

    private java.lang.String customerCode;

    private java.lang.Integer hoaDonID;

    private java.lang.Integer ky;

    private java.lang.String maLoaiDVu;

    private java.lang.Integer maLoaiHD;

    private java.lang.Integer nam;

    private java.lang.String name;

    private java.util.Calendar payDate;

    private java.lang.Integer thang;

    private java.lang.String transacsionCode;

    public PaymentInfo() {
    }

    public PaymentInfo(
           java.lang.String address,
           java.lang.Integer amount,
           java.lang.String customerCode,
           java.lang.Integer hoaDonID,
           java.lang.Integer ky,
           java.lang.String maLoaiDVu,
           java.lang.Integer maLoaiHD,
           java.lang.Integer nam,
           java.lang.String name,
           java.util.Calendar payDate,
           java.lang.Integer thang,
           java.lang.String transacsionCode) {
           this.address = address;
           this.amount = amount;
           this.customerCode = customerCode;
           this.hoaDonID = hoaDonID;
           this.ky = ky;
           this.maLoaiDVu = maLoaiDVu;
           this.maLoaiHD = maLoaiHD;
           this.nam = nam;
           this.name = name;
           this.payDate = payDate;
           this.thang = thang;
           this.transacsionCode = transacsionCode;
    }


    /**
     * Gets the address value for this PaymentInfo.
     * 
     * @return address
     */
    public java.lang.String getAddress() {
        return address;
    }


    /**
     * Sets the address value for this PaymentInfo.
     * 
     * @param address
     */
    public void setAddress(java.lang.String address) {
        this.address = address;
    }


    /**
     * Gets the amount value for this PaymentInfo.
     * 
     * @return amount
     */
    public java.lang.Integer getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this PaymentInfo.
     * 
     * @param amount
     */
    public void setAmount(java.lang.Integer amount) {
        this.amount = amount;
    }


    /**
     * Gets the customerCode value for this PaymentInfo.
     * 
     * @return customerCode
     */
    public java.lang.String getCustomerCode() {
        return customerCode;
    }


    /**
     * Sets the customerCode value for this PaymentInfo.
     * 
     * @param customerCode
     */
    public void setCustomerCode(java.lang.String customerCode) {
        this.customerCode = customerCode;
    }


    /**
     * Gets the hoaDonID value for this PaymentInfo.
     * 
     * @return hoaDonID
     */
    public java.lang.Integer getHoaDonID() {
        return hoaDonID;
    }


    /**
     * Sets the hoaDonID value for this PaymentInfo.
     * 
     * @param hoaDonID
     */
    public void setHoaDonID(java.lang.Integer hoaDonID) {
        this.hoaDonID = hoaDonID;
    }


    /**
     * Gets the ky value for this PaymentInfo.
     * 
     * @return ky
     */
    public java.lang.Integer getKy() {
        return ky;
    }


    /**
     * Sets the ky value for this PaymentInfo.
     * 
     * @param ky
     */
    public void setKy(java.lang.Integer ky) {
        this.ky = ky;
    }


    /**
     * Gets the maLoaiDVu value for this PaymentInfo.
     * 
     * @return maLoaiDVu
     */
    public java.lang.String getMaLoaiDVu() {
        return maLoaiDVu;
    }


    /**
     * Sets the maLoaiDVu value for this PaymentInfo.
     * 
     * @param maLoaiDVu
     */
    public void setMaLoaiDVu(java.lang.String maLoaiDVu) {
        this.maLoaiDVu = maLoaiDVu;
    }


    /**
     * Gets the maLoaiHD value for this PaymentInfo.
     * 
     * @return maLoaiHD
     */
    public java.lang.Integer getMaLoaiHD() {
        return maLoaiHD;
    }


    /**
     * Sets the maLoaiHD value for this PaymentInfo.
     * 
     * @param maLoaiHD
     */
    public void setMaLoaiHD(java.lang.Integer maLoaiHD) {
        this.maLoaiHD = maLoaiHD;
    }


    /**
     * Gets the nam value for this PaymentInfo.
     * 
     * @return nam
     */
    public java.lang.Integer getNam() {
        return nam;
    }


    /**
     * Sets the nam value for this PaymentInfo.
     * 
     * @param nam
     */
    public void setNam(java.lang.Integer nam) {
        this.nam = nam;
    }


    /**
     * Gets the name value for this PaymentInfo.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this PaymentInfo.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the payDate value for this PaymentInfo.
     * 
     * @return payDate
     */
    public java.util.Calendar getPayDate() {
        return payDate;
    }


    /**
     * Sets the payDate value for this PaymentInfo.
     * 
     * @param payDate
     */
    public void setPayDate(java.util.Calendar payDate) {
        this.payDate = payDate;
    }


    /**
     * Gets the thang value for this PaymentInfo.
     * 
     * @return thang
     */
    public java.lang.Integer getThang() {
        return thang;
    }


    /**
     * Sets the thang value for this PaymentInfo.
     * 
     * @param thang
     */
    public void setThang(java.lang.Integer thang) {
        this.thang = thang;
    }


    /**
     * Gets the transacsionCode value for this PaymentInfo.
     * 
     * @return transacsionCode
     */
    public java.lang.String getTransacsionCode() {
        return transacsionCode;
    }


    /**
     * Sets the transacsionCode value for this PaymentInfo.
     * 
     * @param transacsionCode
     */
    public void setTransacsionCode(java.lang.String transacsionCode) {
        this.transacsionCode = transacsionCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PaymentInfo)) return false;
        PaymentInfo other = (PaymentInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount()))) &&
            ((this.customerCode==null && other.getCustomerCode()==null) || 
             (this.customerCode!=null &&
              this.customerCode.equals(other.getCustomerCode()))) &&
            ((this.hoaDonID==null && other.getHoaDonID()==null) || 
             (this.hoaDonID!=null &&
              this.hoaDonID.equals(other.getHoaDonID()))) &&
            ((this.ky==null && other.getKy()==null) || 
             (this.ky!=null &&
              this.ky.equals(other.getKy()))) &&
            ((this.maLoaiDVu==null && other.getMaLoaiDVu()==null) || 
             (this.maLoaiDVu!=null &&
              this.maLoaiDVu.equals(other.getMaLoaiDVu()))) &&
            ((this.maLoaiHD==null && other.getMaLoaiHD()==null) || 
             (this.maLoaiHD!=null &&
              this.maLoaiHD.equals(other.getMaLoaiHD()))) &&
            ((this.nam==null && other.getNam()==null) || 
             (this.nam!=null &&
              this.nam.equals(other.getNam()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.payDate==null && other.getPayDate()==null) || 
             (this.payDate!=null &&
              this.payDate.equals(other.getPayDate()))) &&
            ((this.thang==null && other.getThang()==null) || 
             (this.thang!=null &&
              this.thang.equals(other.getThang()))) &&
            ((this.transacsionCode==null && other.getTransacsionCode()==null) || 
             (this.transacsionCode!=null &&
              this.transacsionCode.equals(other.getTransacsionCode())));
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
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        if (getCustomerCode() != null) {
            _hashCode += getCustomerCode().hashCode();
        }
        if (getHoaDonID() != null) {
            _hashCode += getHoaDonID().hashCode();
        }
        if (getKy() != null) {
            _hashCode += getKy().hashCode();
        }
        if (getMaLoaiDVu() != null) {
            _hashCode += getMaLoaiDVu().hashCode();
        }
        if (getMaLoaiHD() != null) {
            _hashCode += getMaLoaiHD().hashCode();
        }
        if (getNam() != null) {
            _hashCode += getNam().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getPayDate() != null) {
            _hashCode += getPayDate().hashCode();
        }
        if (getThang() != null) {
            _hashCode += getThang().hashCode();
        }
        if (getTransacsionCode() != null) {
            _hashCode += getTransacsionCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PaymentInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "PaymentInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "CustomerCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hoaDonID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "HoaDonID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ky");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Ky"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maLoaiDVu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "MaLoaiDVu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maLoaiHD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "MaLoaiHD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nam");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Nam"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "PayDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thang");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Thang"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transacsionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "TransacsionCode"));
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
