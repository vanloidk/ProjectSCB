/**
 * BillInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC;

public class BillInfo  implements java.io.Serializable {
    private java.lang.Integer amount;

    private java.lang.String billCode;

    private java.util.Calendar denNgay;

    private java.lang.Integer hoaDonID;

    private java.lang.Integer month;

    private java.lang.Integer soHo;

    private java.util.Calendar tuNgay;

    private java.lang.String tyLeGia;

    private java.lang.Integer year;

    public BillInfo() {
    }

    public BillInfo(
           java.lang.Integer amount,
           java.lang.String billCode,
           java.util.Calendar denNgay,
           java.lang.Integer hoaDonID,
           java.lang.Integer month,
           java.lang.Integer soHo,
           java.util.Calendar tuNgay,
           java.lang.String tyLeGia,
           java.lang.Integer year) {
           this.amount = amount;
           this.billCode = billCode;
           this.denNgay = denNgay;
           this.hoaDonID = hoaDonID;
           this.month = month;
           this.soHo = soHo;
           this.tuNgay = tuNgay;
           this.tyLeGia = tyLeGia;
           this.year = year;
    }


    /**
     * Gets the amount value for this BillInfo.
     * 
     * @return amount
     */
    public java.lang.Integer getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this BillInfo.
     * 
     * @param amount
     */
    public void setAmount(java.lang.Integer amount) {
        this.amount = amount;
    }


    /**
     * Gets the billCode value for this BillInfo.
     * 
     * @return billCode
     */
    public java.lang.String getBillCode() {
        return billCode;
    }


    /**
     * Sets the billCode value for this BillInfo.
     * 
     * @param billCode
     */
    public void setBillCode(java.lang.String billCode) {
        this.billCode = billCode;
    }


    /**
     * Gets the denNgay value for this BillInfo.
     * 
     * @return denNgay
     */
    public java.util.Calendar getDenNgay() {
        return denNgay;
    }


    /**
     * Sets the denNgay value for this BillInfo.
     * 
     * @param denNgay
     */
    public void setDenNgay(java.util.Calendar denNgay) {
        this.denNgay = denNgay;
    }


    /**
     * Gets the hoaDonID value for this BillInfo.
     * 
     * @return hoaDonID
     */
    public java.lang.Integer getHoaDonID() {
        return hoaDonID;
    }


    /**
     * Sets the hoaDonID value for this BillInfo.
     * 
     * @param hoaDonID
     */
    public void setHoaDonID(java.lang.Integer hoaDonID) {
        this.hoaDonID = hoaDonID;
    }


    /**
     * Gets the month value for this BillInfo.
     * 
     * @return month
     */
    public java.lang.Integer getMonth() {
        return month;
    }


    /**
     * Sets the month value for this BillInfo.
     * 
     * @param month
     */
    public void setMonth(java.lang.Integer month) {
        this.month = month;
    }


    /**
     * Gets the soHo value for this BillInfo.
     * 
     * @return soHo
     */
    public java.lang.Integer getSoHo() {
        return soHo;
    }


    /**
     * Sets the soHo value for this BillInfo.
     * 
     * @param soHo
     */
    public void setSoHo(java.lang.Integer soHo) {
        this.soHo = soHo;
    }


    /**
     * Gets the tuNgay value for this BillInfo.
     * 
     * @return tuNgay
     */
    public java.util.Calendar getTuNgay() {
        return tuNgay;
    }


    /**
     * Sets the tuNgay value for this BillInfo.
     * 
     * @param tuNgay
     */
    public void setTuNgay(java.util.Calendar tuNgay) {
        this.tuNgay = tuNgay;
    }


    /**
     * Gets the tyLeGia value for this BillInfo.
     * 
     * @return tyLeGia
     */
    public java.lang.String getTyLeGia() {
        return tyLeGia;
    }


    /**
     * Sets the tyLeGia value for this BillInfo.
     * 
     * @param tyLeGia
     */
    public void setTyLeGia(java.lang.String tyLeGia) {
        this.tyLeGia = tyLeGia;
    }


    /**
     * Gets the year value for this BillInfo.
     * 
     * @return year
     */
    public java.lang.Integer getYear() {
        return year;
    }


    /**
     * Sets the year value for this BillInfo.
     * 
     * @param year
     */
    public void setYear(java.lang.Integer year) {
        this.year = year;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BillInfo)) return false;
        BillInfo other = (BillInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount()))) &&
            ((this.billCode==null && other.getBillCode()==null) || 
             (this.billCode!=null &&
              this.billCode.equals(other.getBillCode()))) &&
            ((this.denNgay==null && other.getDenNgay()==null) || 
             (this.denNgay!=null &&
              this.denNgay.equals(other.getDenNgay()))) &&
            ((this.hoaDonID==null && other.getHoaDonID()==null) || 
             (this.hoaDonID!=null &&
              this.hoaDonID.equals(other.getHoaDonID()))) &&
            ((this.month==null && other.getMonth()==null) || 
             (this.month!=null &&
              this.month.equals(other.getMonth()))) &&
            ((this.soHo==null && other.getSoHo()==null) || 
             (this.soHo!=null &&
              this.soHo.equals(other.getSoHo()))) &&
            ((this.tuNgay==null && other.getTuNgay()==null) || 
             (this.tuNgay!=null &&
              this.tuNgay.equals(other.getTuNgay()))) &&
            ((this.tyLeGia==null && other.getTyLeGia()==null) || 
             (this.tyLeGia!=null &&
              this.tyLeGia.equals(other.getTyLeGia()))) &&
            ((this.year==null && other.getYear()==null) || 
             (this.year!=null &&
              this.year.equals(other.getYear())));
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
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        if (getBillCode() != null) {
            _hashCode += getBillCode().hashCode();
        }
        if (getDenNgay() != null) {
            _hashCode += getDenNgay().hashCode();
        }
        if (getHoaDonID() != null) {
            _hashCode += getHoaDonID().hashCode();
        }
        if (getMonth() != null) {
            _hashCode += getMonth().hashCode();
        }
        if (getSoHo() != null) {
            _hashCode += getSoHo().hashCode();
        }
        if (getTuNgay() != null) {
            _hashCode += getTuNgay().hashCode();
        }
        if (getTyLeGia() != null) {
            _hashCode += getTyLeGia().hashCode();
        }
        if (getYear() != null) {
            _hashCode += getYear().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BillInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "BillInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "BillCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("denNgay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "DenNgay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("month");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Month"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("soHo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "SoHo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tuNgay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "TuNgay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tyLeGia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "TyLeGia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("year");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Year"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
