/**
 * PaymentInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class PaymentInfo  implements java.io.Serializable {
    private java.lang.String customerCode;

    private java.lang.String name;

    private java.lang.String address;

    private int hoaDonID;

    private int ky;

    private int thang;

    private int nam;

    private int maLoaiHD;

    private java.lang.String maLoaiDVu;

    private int amount;

    private java.lang.String transacsionCode;

    private java.util.Calendar payDate;

    private ws.internal.payment.evnspc.CustomerInfo customerInfo;

    public PaymentInfo() {
    }

    public PaymentInfo(
           java.lang.String customerCode,
           java.lang.String name,
           java.lang.String address,
           int hoaDonID,
           int ky,
           int thang,
           int nam,
           int maLoaiHD,
           java.lang.String maLoaiDVu,
           int amount,
           java.lang.String transacsionCode,
           java.util.Calendar payDate,
           ws.internal.payment.evnspc.CustomerInfo customerInfo) {
           this.customerCode = customerCode;
           this.name = name;
           this.address = address;
           this.hoaDonID = hoaDonID;
           this.ky = ky;
           this.thang = thang;
           this.nam = nam;
           this.maLoaiHD = maLoaiHD;
           this.maLoaiDVu = maLoaiDVu;
           this.amount = amount;
           this.transacsionCode = transacsionCode;
           this.payDate = payDate;
           this.customerInfo = customerInfo;
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
     * Gets the hoaDonID value for this PaymentInfo.
     * 
     * @return hoaDonID
     */
    public int getHoaDonID() {
        return hoaDonID;
    }


    /**
     * Sets the hoaDonID value for this PaymentInfo.
     * 
     * @param hoaDonID
     */
    public void setHoaDonID(int hoaDonID) {
        this.hoaDonID = hoaDonID;
    }


    /**
     * Gets the ky value for this PaymentInfo.
     * 
     * @return ky
     */
    public int getKy() {
        return ky;
    }


    /**
     * Sets the ky value for this PaymentInfo.
     * 
     * @param ky
     */
    public void setKy(int ky) {
        this.ky = ky;
    }


    /**
     * Gets the thang value for this PaymentInfo.
     * 
     * @return thang
     */
    public int getThang() {
        return thang;
    }


    /**
     * Sets the thang value for this PaymentInfo.
     * 
     * @param thang
     */
    public void setThang(int thang) {
        this.thang = thang;
    }


    /**
     * Gets the nam value for this PaymentInfo.
     * 
     * @return nam
     */
    public int getNam() {
        return nam;
    }


    /**
     * Sets the nam value for this PaymentInfo.
     * 
     * @param nam
     */
    public void setNam(int nam) {
        this.nam = nam;
    }


    /**
     * Gets the maLoaiHD value for this PaymentInfo.
     * 
     * @return maLoaiHD
     */
    public int getMaLoaiHD() {
        return maLoaiHD;
    }


    /**
     * Sets the maLoaiHD value for this PaymentInfo.
     * 
     * @param maLoaiHD
     */
    public void setMaLoaiHD(int maLoaiHD) {
        this.maLoaiHD = maLoaiHD;
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
     * Gets the amount value for this PaymentInfo.
     * 
     * @return amount
     */
    public int getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this PaymentInfo.
     * 
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
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
     * Gets the customerInfo value for this PaymentInfo.
     * 
     * @return customerInfo
     */
    public ws.internal.payment.evnspc.CustomerInfo getCustomerInfo() {
        return customerInfo;
    }


    /**
     * Sets the customerInfo value for this PaymentInfo.
     * 
     * @param customerInfo
     */
    public void setCustomerInfo(ws.internal.payment.evnspc.CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
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
            ((this.customerCode==null && other.getCustomerCode()==null) || 
             (this.customerCode!=null &&
              this.customerCode.equals(other.getCustomerCode()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            this.hoaDonID == other.getHoaDonID() &&
            this.ky == other.getKy() &&
            this.thang == other.getThang() &&
            this.nam == other.getNam() &&
            this.maLoaiHD == other.getMaLoaiHD() &&
            ((this.maLoaiDVu==null && other.getMaLoaiDVu()==null) || 
             (this.maLoaiDVu!=null &&
              this.maLoaiDVu.equals(other.getMaLoaiDVu()))) &&
            this.amount == other.getAmount() &&
            ((this.transacsionCode==null && other.getTransacsionCode()==null) || 
             (this.transacsionCode!=null &&
              this.transacsionCode.equals(other.getTransacsionCode()))) &&
            ((this.payDate==null && other.getPayDate()==null) || 
             (this.payDate!=null &&
              this.payDate.equals(other.getPayDate()))) &&
            ((this.customerInfo==null && other.getCustomerInfo()==null) || 
             (this.customerInfo!=null &&
              this.customerInfo.equals(other.getCustomerInfo())));
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
        if (getCustomerCode() != null) {
            _hashCode += getCustomerCode().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        _hashCode += getHoaDonID();
        _hashCode += getKy();
        _hashCode += getThang();
        _hashCode += getNam();
        _hashCode += getMaLoaiHD();
        if (getMaLoaiDVu() != null) {
            _hashCode += getMaLoaiDVu().hashCode();
        }
        _hashCode += getAmount();
        if (getTransacsionCode() != null) {
            _hashCode += getTransacsionCode().hashCode();
        }
        if (getPayDate() != null) {
            _hashCode += getPayDate().hashCode();
        }
        if (getCustomerInfo() != null) {
            _hashCode += getCustomerInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PaymentInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "PaymentInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CustomerCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hoaDonID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "HoaDonID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ky");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Ky"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thang");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Thang"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nam");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Nam"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maLoaiHD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MaLoaiHD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maLoaiDVu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MaLoaiDVu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transacsionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TransacsionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PayDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CustomerInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "CustomerInfo"));
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
