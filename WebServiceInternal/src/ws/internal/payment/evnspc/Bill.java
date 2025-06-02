/**
 * Bill.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class Bill  implements java.io.Serializable {
    private java.lang.String billCode;

    private int month;

    private int year;

    private int amount;

    private int hoaDonID;

    private int soHo;

    private java.lang.String tiLeGia;

    private java.util.Calendar tuNgay;

    private java.util.Calendar denNgay;

    public Bill() {
    }

    public Bill(
           java.lang.String billCode,
           int month,
           int year,
           int amount,
           int hoaDonID,
           int soHo,
           java.lang.String tiLeGia,
           java.util.Calendar tuNgay,
           java.util.Calendar denNgay) {
           this.billCode = billCode;
           this.month = month;
           this.year = year;
           this.amount = amount;
           this.hoaDonID = hoaDonID;
           this.soHo = soHo;
           this.tiLeGia = tiLeGia;
           this.tuNgay = tuNgay;
           this.denNgay = denNgay;
    }


    /**
     * Gets the billCode value for this Bill.
     * 
     * @return billCode
     */
    public java.lang.String getBillCode() {
        return billCode;
    }


    /**
     * Sets the billCode value for this Bill.
     * 
     * @param billCode
     */
    public void setBillCode(java.lang.String billCode) {
        this.billCode = billCode;
    }


    /**
     * Gets the month value for this Bill.
     * 
     * @return month
     */
    public int getMonth() {
        return month;
    }


    /**
     * Sets the month value for this Bill.
     * 
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }


    /**
     * Gets the year value for this Bill.
     * 
     * @return year
     */
    public int getYear() {
        return year;
    }


    /**
     * Sets the year value for this Bill.
     * 
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }


    /**
     * Gets the amount value for this Bill.
     * 
     * @return amount
     */
    public int getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this Bill.
     * 
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }


    /**
     * Gets the hoaDonID value for this Bill.
     * 
     * @return hoaDonID
     */
    public int getHoaDonID() {
        return hoaDonID;
    }


    /**
     * Sets the hoaDonID value for this Bill.
     * 
     * @param hoaDonID
     */
    public void setHoaDonID(int hoaDonID) {
        this.hoaDonID = hoaDonID;
    }


    /**
     * Gets the soHo value for this Bill.
     * 
     * @return soHo
     */
    public int getSoHo() {
        return soHo;
    }


    /**
     * Sets the soHo value for this Bill.
     * 
     * @param soHo
     */
    public void setSoHo(int soHo) {
        this.soHo = soHo;
    }


    /**
     * Gets the tiLeGia value for this Bill.
     * 
     * @return tiLeGia
     */
    public java.lang.String getTiLeGia() {
        return tiLeGia;
    }


    /**
     * Sets the tiLeGia value for this Bill.
     * 
     * @param tiLeGia
     */
    public void setTiLeGia(java.lang.String tiLeGia) {
        this.tiLeGia = tiLeGia;
    }


    /**
     * Gets the tuNgay value for this Bill.
     * 
     * @return tuNgay
     */
    public java.util.Calendar getTuNgay() {
        return tuNgay;
    }


    /**
     * Sets the tuNgay value for this Bill.
     * 
     * @param tuNgay
     */
    public void setTuNgay(java.util.Calendar tuNgay) {
        this.tuNgay = tuNgay;
    }


    /**
     * Gets the denNgay value for this Bill.
     * 
     * @return denNgay
     */
    public java.util.Calendar getDenNgay() {
        return denNgay;
    }


    /**
     * Sets the denNgay value for this Bill.
     * 
     * @param denNgay
     */
    public void setDenNgay(java.util.Calendar denNgay) {
        this.denNgay = denNgay;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Bill)) return false;
        Bill other = (Bill) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.billCode==null && other.getBillCode()==null) || 
             (this.billCode!=null &&
              this.billCode.equals(other.getBillCode()))) &&
            this.month == other.getMonth() &&
            this.year == other.getYear() &&
            this.amount == other.getAmount() &&
            this.hoaDonID == other.getHoaDonID() &&
            this.soHo == other.getSoHo() &&
            ((this.tiLeGia==null && other.getTiLeGia()==null) || 
             (this.tiLeGia!=null &&
              this.tiLeGia.equals(other.getTiLeGia()))) &&
            ((this.tuNgay==null && other.getTuNgay()==null) || 
             (this.tuNgay!=null &&
              this.tuNgay.equals(other.getTuNgay()))) &&
            ((this.denNgay==null && other.getDenNgay()==null) || 
             (this.denNgay!=null &&
              this.denNgay.equals(other.getDenNgay())));
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
        if (getBillCode() != null) {
            _hashCode += getBillCode().hashCode();
        }
        _hashCode += getMonth();
        _hashCode += getYear();
        _hashCode += getAmount();
        _hashCode += getHoaDonID();
        _hashCode += getSoHo();
        if (getTiLeGia() != null) {
            _hashCode += getTiLeGia().hashCode();
        }
        if (getTuNgay() != null) {
            _hashCode += getTuNgay().hashCode();
        }
        if (getDenNgay() != null) {
            _hashCode += getDenNgay().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Bill.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Bill"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BillCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("month");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Month"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("year");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Year"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hoaDonID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "HoaDonID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("soHo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SoHo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tiLeGia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TiLeGia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tuNgay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TuNgay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("denNgay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DenNgay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
