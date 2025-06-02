/**
 * CustomerInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC;

public class CustomerInfo  implements java.io.Serializable {
    private java.lang.String address;

    private org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC.BillInfo[] bills;

    private java.lang.String customerCode;

    private java.lang.String danhSo;

    private java.lang.String maSoThue;

    private java.lang.String maTram;

    private java.lang.String name;

    private java.lang.String nganhNghe;

    private java.lang.String phienGCS;

    private java.lang.String session;

    private java.lang.String soCongTo;

    private java.lang.String soGhiChiSo;

    public CustomerInfo() {
    }

    public CustomerInfo(
           java.lang.String address,
           org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC.BillInfo[] bills,
           java.lang.String customerCode,
           java.lang.String danhSo,
           java.lang.String maSoThue,
           java.lang.String maTram,
           java.lang.String name,
           java.lang.String nganhNghe,
           java.lang.String phienGCS,
           java.lang.String session,
           java.lang.String soCongTo,
           java.lang.String soGhiChiSo) {
           this.address = address;
           this.bills = bills;
           this.customerCode = customerCode;
           this.danhSo = danhSo;
           this.maSoThue = maSoThue;
           this.maTram = maTram;
           this.name = name;
           this.nganhNghe = nganhNghe;
           this.phienGCS = phienGCS;
           this.session = session;
           this.soCongTo = soCongTo;
           this.soGhiChiSo = soGhiChiSo;
    }


    /**
     * Gets the address value for this CustomerInfo.
     * 
     * @return address
     */
    public java.lang.String getAddress() {
        return address;
    }


    /**
     * Sets the address value for this CustomerInfo.
     * 
     * @param address
     */
    public void setAddress(java.lang.String address) {
        this.address = address;
    }


    /**
     * Gets the bills value for this CustomerInfo.
     * 
     * @return bills
     */
    public org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC.BillInfo[] getBills() {
        return bills;
    }


    /**
     * Sets the bills value for this CustomerInfo.
     * 
     * @param bills
     */
    public void setBills(org.datacontract.schemas._2004._07.PaymentGatewayEVNSPC.BillInfo[] bills) {
        this.bills = bills;
    }


    /**
     * Gets the customerCode value for this CustomerInfo.
     * 
     * @return customerCode
     */
    public java.lang.String getCustomerCode() {
        return customerCode;
    }


    /**
     * Sets the customerCode value for this CustomerInfo.
     * 
     * @param customerCode
     */
    public void setCustomerCode(java.lang.String customerCode) {
        this.customerCode = customerCode;
    }


    /**
     * Gets the danhSo value for this CustomerInfo.
     * 
     * @return danhSo
     */
    public java.lang.String getDanhSo() {
        return danhSo;
    }


    /**
     * Sets the danhSo value for this CustomerInfo.
     * 
     * @param danhSo
     */
    public void setDanhSo(java.lang.String danhSo) {
        this.danhSo = danhSo;
    }


    /**
     * Gets the maSoThue value for this CustomerInfo.
     * 
     * @return maSoThue
     */
    public java.lang.String getMaSoThue() {
        return maSoThue;
    }


    /**
     * Sets the maSoThue value for this CustomerInfo.
     * 
     * @param maSoThue
     */
    public void setMaSoThue(java.lang.String maSoThue) {
        this.maSoThue = maSoThue;
    }


    /**
     * Gets the maTram value for this CustomerInfo.
     * 
     * @return maTram
     */
    public java.lang.String getMaTram() {
        return maTram;
    }


    /**
     * Sets the maTram value for this CustomerInfo.
     * 
     * @param maTram
     */
    public void setMaTram(java.lang.String maTram) {
        this.maTram = maTram;
    }


    /**
     * Gets the name value for this CustomerInfo.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this CustomerInfo.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the nganhNghe value for this CustomerInfo.
     * 
     * @return nganhNghe
     */
    public java.lang.String getNganhNghe() {
        return nganhNghe;
    }


    /**
     * Sets the nganhNghe value for this CustomerInfo.
     * 
     * @param nganhNghe
     */
    public void setNganhNghe(java.lang.String nganhNghe) {
        this.nganhNghe = nganhNghe;
    }


    /**
     * Gets the phienGCS value for this CustomerInfo.
     * 
     * @return phienGCS
     */
    public java.lang.String getPhienGCS() {
        return phienGCS;
    }


    /**
     * Sets the phienGCS value for this CustomerInfo.
     * 
     * @param phienGCS
     */
    public void setPhienGCS(java.lang.String phienGCS) {
        this.phienGCS = phienGCS;
    }


    /**
     * Gets the session value for this CustomerInfo.
     * 
     * @return session
     */
    public java.lang.String getSession() {
        return session;
    }


    /**
     * Sets the session value for this CustomerInfo.
     * 
     * @param session
     */
    public void setSession(java.lang.String session) {
        this.session = session;
    }


    /**
     * Gets the soCongTo value for this CustomerInfo.
     * 
     * @return soCongTo
     */
    public java.lang.String getSoCongTo() {
        return soCongTo;
    }


    /**
     * Sets the soCongTo value for this CustomerInfo.
     * 
     * @param soCongTo
     */
    public void setSoCongTo(java.lang.String soCongTo) {
        this.soCongTo = soCongTo;
    }


    /**
     * Gets the soGhiChiSo value for this CustomerInfo.
     * 
     * @return soGhiChiSo
     */
    public java.lang.String getSoGhiChiSo() {
        return soGhiChiSo;
    }


    /**
     * Sets the soGhiChiSo value for this CustomerInfo.
     * 
     * @param soGhiChiSo
     */
    public void setSoGhiChiSo(java.lang.String soGhiChiSo) {
        this.soGhiChiSo = soGhiChiSo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CustomerInfo)) return false;
        CustomerInfo other = (CustomerInfo) obj;
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
            ((this.bills==null && other.getBills()==null) || 
             (this.bills!=null &&
              java.util.Arrays.equals(this.bills, other.getBills()))) &&
            ((this.customerCode==null && other.getCustomerCode()==null) || 
             (this.customerCode!=null &&
              this.customerCode.equals(other.getCustomerCode()))) &&
            ((this.danhSo==null && other.getDanhSo()==null) || 
             (this.danhSo!=null &&
              this.danhSo.equals(other.getDanhSo()))) &&
            ((this.maSoThue==null && other.getMaSoThue()==null) || 
             (this.maSoThue!=null &&
              this.maSoThue.equals(other.getMaSoThue()))) &&
            ((this.maTram==null && other.getMaTram()==null) || 
             (this.maTram!=null &&
              this.maTram.equals(other.getMaTram()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.nganhNghe==null && other.getNganhNghe()==null) || 
             (this.nganhNghe!=null &&
              this.nganhNghe.equals(other.getNganhNghe()))) &&
            ((this.phienGCS==null && other.getPhienGCS()==null) || 
             (this.phienGCS!=null &&
              this.phienGCS.equals(other.getPhienGCS()))) &&
            ((this.session==null && other.getSession()==null) || 
             (this.session!=null &&
              this.session.equals(other.getSession()))) &&
            ((this.soCongTo==null && other.getSoCongTo()==null) || 
             (this.soCongTo!=null &&
              this.soCongTo.equals(other.getSoCongTo()))) &&
            ((this.soGhiChiSo==null && other.getSoGhiChiSo()==null) || 
             (this.soGhiChiSo!=null &&
              this.soGhiChiSo.equals(other.getSoGhiChiSo())));
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
        if (getBills() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBills());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBills(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCustomerCode() != null) {
            _hashCode += getCustomerCode().hashCode();
        }
        if (getDanhSo() != null) {
            _hashCode += getDanhSo().hashCode();
        }
        if (getMaSoThue() != null) {
            _hashCode += getMaSoThue().hashCode();
        }
        if (getMaTram() != null) {
            _hashCode += getMaTram().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getNganhNghe() != null) {
            _hashCode += getNganhNghe().hashCode();
        }
        if (getPhienGCS() != null) {
            _hashCode += getPhienGCS().hashCode();
        }
        if (getSession() != null) {
            _hashCode += getSession().hashCode();
        }
        if (getSoCongTo() != null) {
            _hashCode += getSoCongTo().hashCode();
        }
        if (getSoGhiChiSo() != null) {
            _hashCode += getSoGhiChiSo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CustomerInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "CustomerInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bills");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Bills"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "BillInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "BillInfo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "CustomerCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("danhSo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "DanhSo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maSoThue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "MaSoThue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maTram");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "MaTram"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nganhNghe");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "NganhNghe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phienGCS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "PhienGCS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("session");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "Session"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("soCongTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "SoCongTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("soGhiChiSo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/PaymentGatewayEVNSPC", "SoGhiChiSo"));
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
