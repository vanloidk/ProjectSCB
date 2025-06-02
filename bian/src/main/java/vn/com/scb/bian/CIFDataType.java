/**
 * CIFDataType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import com.google.gson.annotations.Expose;

public class CIFDataType  implements java.io.Serializable {
    /* Số CIF */
     @Expose
    private java.lang.String CIFNum;

    /* Ngày cấp số CIF */
    private java.lang.String CIFIssuedDate;

    /* Mã chi nhánh */
    private java.lang.String branchCode;

    /* Loại khách hàng (cá nhân hoặc doanh nghiệp) */
    private java.lang.String customerType;

    /* Nhóm nợ khách hàng vay */
    private java.lang.String CIFDebitClass;
    
    /* FieldName */
    private java.lang.String fieldName;
    
     /* fieldValue */
    private java.lang.String fieldValue;

    public CIFDataType() {
    }

    public CIFDataType(
           java.lang.String CIFNum,
           java.lang.String CIFIssuedDate,
           java.lang.String branchCode,
           java.lang.String customerType,
           java.lang.String CIFDebitClass,
           java.lang.String fieldName,
           java.lang.String fieldValue) {
           this.CIFNum = CIFNum;
           this.CIFIssuedDate = CIFIssuedDate;
           this.branchCode = branchCode;
           this.customerType = customerType;
           this.CIFDebitClass = CIFDebitClass;
           this.fieldName = fieldName;
           this.fieldValue = fieldValue;
    }


    /**
     * Gets the CIFNum value for this CIFDataType.
     * 
     * @return CIFNum   * Số CIF
     */
    public java.lang.String getCIFNum() {
        return CIFNum;
    }


    /**
     * Sets the CIFNum value for this CIFDataType.
     * 
     * @param CIFNum   * Số CIF
     */
    public void setCIFNum(java.lang.String CIFNum) {
        this.CIFNum = CIFNum;
    }


    /**
     * Gets the CIFIssuedDate value for this CIFDataType.
     * 
     * @return CIFIssuedDate   * Ngày cấp số CIF
     */
    public java.lang.String getCIFIssuedDate() {
        return CIFIssuedDate;
    }


    /**
     * Sets the CIFIssuedDate value for this CIFDataType.
     * 
     * @param CIFIssuedDate   * Ngày cấp số CIF
     */
    public void setCIFIssuedDate(java.lang.String CIFIssuedDate) {
        this.CIFIssuedDate = CIFIssuedDate;
    }


    /**
     * Gets the branchCode value for this CIFDataType.
     * 
     * @return branchCode   * Mã chi nhánh
     */
    public java.lang.String getBranchCode() {
        return branchCode;
    }


    /**
     * Sets the branchCode value for this CIFDataType.
     * 
     * @param branchCode   * Mã chi nhánh
     */
    public void setBranchCode(java.lang.String branchCode) {
        this.branchCode = branchCode;
    }


    /**
     * Gets the customerType value for this CIFDataType.
     * 
     * @return customerType   * Loại khách hàng (cá nhân hoặc doanh nghiệp)
     */
    public java.lang.String getCustomerType() {
        return customerType;
    }


    /**
     * Sets the customerType value for this CIFDataType.
     * 
     * @param customerType   * Loại khách hàng (cá nhân hoặc doanh nghiệp)
     */
    public void setCustomerType(java.lang.String customerType) {
        this.customerType = customerType;
    }


    /**
     * Gets the CIFDebitClass value for this CIFDataType.
     * 
     * @return CIFDebitClass   * Nhóm nợ khách hàng vay
     */
    public java.lang.String getCIFDebitClass() {
        return CIFDebitClass;
    }


    /**
     * Sets the CIFDebitClass value for this CIFDataType.
     * 
     * @param CIFDebitClass   * Nhóm nợ khách hàng vay
     */
    public void setCIFDebitClass(java.lang.String CIFDebitClass) {
        this.CIFDebitClass = CIFDebitClass;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CIFDataType)) return false;
        CIFDataType other = (CIFDataType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CIFNum==null && other.getCIFNum()==null) || 
             (this.CIFNum!=null &&
              this.CIFNum.equals(other.getCIFNum()))) &&
            ((this.CIFIssuedDate==null && other.getCIFIssuedDate()==null) || 
             (this.CIFIssuedDate!=null &&
              this.CIFIssuedDate.equals(other.getCIFIssuedDate()))) &&
            ((this.branchCode==null && other.getBranchCode()==null) || 
             (this.branchCode!=null &&
              this.branchCode.equals(other.getBranchCode()))) &&
            ((this.customerType==null && other.getCustomerType()==null) || 
             (this.customerType!=null &&
              this.customerType.equals(other.getCustomerType()))) &&
            ((this.CIFDebitClass==null && other.getCIFDebitClass()==null) || 
             (this.CIFDebitClass!=null &&
              this.CIFDebitClass.equals(other.getCIFDebitClass()))) &&
                ((this.fieldName==null && other.getFieldName()==null) || 
             (this.fieldName!=null &&
              this.fieldName.equals(other.getFieldName()))) &&
                ((this.fieldValue==null && other.getFieldValue()==null) || 
             (this.fieldValue!=null &&
              this.fieldValue.equals(other.getFieldValue()))) ;  
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
        if (getCIFNum() != null) {
            _hashCode += getCIFNum().hashCode();
        }
        if (getCIFIssuedDate() != null) {
            _hashCode += getCIFIssuedDate().hashCode();
        }
        if (getBranchCode() != null) {
            _hashCode += getBranchCode().hashCode();
        }
        if (getCustomerType() != null) {
            _hashCode += getCustomerType().hashCode();
        }
        if (getCIFDebitClass() != null) {
            _hashCode += getCIFDebitClass().hashCode();
        }
        if (getFieldName() != null) {
            _hashCode += getFieldName().hashCode();
        }
        if (getFieldValue() != null) {
            _hashCode += getFieldValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CIFDataType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CIFDataType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CIFNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CIFNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CIFIssuedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CIFIssuedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("branchCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "branchCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customerType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CIFDebitClass");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CIFDebitClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
         elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fieldName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
         elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldValue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fieldValue"));
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

    /**
     * @return the fieldName
     */
    public java.lang.String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(java.lang.String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return the fieldValue
     */
    public java.lang.String getFieldValue() {
        return fieldValue;
    }

    /**
     * @param fieldValue the fieldValue to set
     */
    public void setFieldValue(java.lang.String fieldValue) {
        this.fieldValue = fieldValue;
    }

}
