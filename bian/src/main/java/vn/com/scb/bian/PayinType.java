/**
 * PayinType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import com.google.gson.annotations.Expose;

public class PayinType  implements java.io.Serializable {
    /* Loại nguồn trích tiền ('C': tiền mặt; 'S':
     *     					chuyển khoản) */
     
    private java.lang.String srcPayinType;

    /* Mã đơn vị tài khoản nguồn */
      
    private java.lang.String srcAccountBranch;

    /* Số tài khoản nguồn */
       
    private java.lang.String srcAccountNum;

    /* Số tiền gửi vào */
        
    private java.lang.String accountOpeningAmount;
         
   private java.lang.String srcPayinPercentage;     
        
    public PayinType() {
    }

    public PayinType(
           java.lang.String srcPayinType,
           java.lang.String srcAccountBranch,
           java.lang.String srcAccountNum,
           java.lang.String accountOpeningAmount) {
           this.srcPayinType = srcPayinType;
           this.srcAccountBranch = srcAccountBranch;
           this.srcAccountNum = srcAccountNum;
           this.accountOpeningAmount = accountOpeningAmount;
    }


    /**
     * Gets the srcPayinType value for this PayinType.
     * 
     * @return srcPayinType   * Loại nguồn trích tiền ('C': tiền mặt; 'S':
     *     					chuyển khoản)
     */
    public java.lang.String getSrcPayinType() {
        return srcPayinType;
    }


    /**
     * Sets the srcPayinType value for this PayinType.
     * 
     * @param srcPayinType   * Loại nguồn trích tiền ('C': tiền mặt; 'S':
     *     					chuyển khoản)
     */
    public void setSrcPayinType(java.lang.String srcPayinType) {
        this.srcPayinType = srcPayinType;
    }


    /**
     * Gets the srcAccountBranch value for this PayinType.
     * 
     * @return srcAccountBranch   * Mã đơn vị tài khoản nguồn
     */
    public java.lang.String getSrcAccountBranch() {
        return srcAccountBranch;
    }


    /**
     * Sets the srcAccountBranch value for this PayinType.
     * 
     * @param srcAccountBranch   * Mã đơn vị tài khoản nguồn
     */
    public void setSrcAccountBranch(java.lang.String srcAccountBranch) {
        this.srcAccountBranch = srcAccountBranch;
    }


    /**
     * Gets the srcAccountNum value for this PayinType.
     * 
     * @return srcAccountNum   * Số tài khoản nguồn
     */
    public java.lang.String getSrcAccountNum() {
        return srcAccountNum;
    }


    /**
     * Sets the srcAccountNum value for this PayinType.
     * 
     * @param srcAccountNum   * Số tài khoản nguồn
     */
    public void setSrcAccountNum(java.lang.String srcAccountNum) {
        this.srcAccountNum = srcAccountNum;
    }


    /**
     * Gets the accountOpeningAmount value for this PayinType.
     * 
     * @return accountOpeningAmount   * Số tiền gửi vào
     */
    public java.lang.String getAccountOpeningAmount() {
        return accountOpeningAmount;
    }


    /**
     * Sets the accountOpeningAmount value for this PayinType.
     * 
     * @param accountOpeningAmount   * Số tiền gửi vào
     */
    public void setAccountOpeningAmount(java.lang.String accountOpeningAmount) {
        this.accountOpeningAmount = accountOpeningAmount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PayinType)) return false;
        PayinType other = (PayinType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.srcPayinType==null && other.getSrcPayinType()==null) || 
             (this.srcPayinType!=null &&
              this.srcPayinType.equals(other.getSrcPayinType()))) &&
            ((this.srcAccountBranch==null && other.getSrcAccountBranch()==null) || 
             (this.srcAccountBranch!=null &&
              this.srcAccountBranch.equals(other.getSrcAccountBranch()))) &&
            ((this.srcAccountNum==null && other.getSrcAccountNum()==null) || 
             (this.srcAccountNum!=null &&
              this.srcAccountNum.equals(other.getSrcAccountNum()))) &&
            ((this.accountOpeningAmount==null && other.getAccountOpeningAmount()==null) || 
             (this.accountOpeningAmount!=null &&
              this.accountOpeningAmount.equals(other.getAccountOpeningAmount())));
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
        if (getSrcPayinType() != null) {
            _hashCode += getSrcPayinType().hashCode();
        }
        if (getSrcAccountBranch() != null) {
            _hashCode += getSrcAccountBranch().hashCode();
        }
        if (getSrcAccountNum() != null) {
            _hashCode += getSrcAccountNum().hashCode();
        }
        if (getAccountOpeningAmount() != null) {
            _hashCode += getAccountOpeningAmount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PayinType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "PayinType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("srcPayinType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "srcPayinType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("srcAccountBranch");
        elemField.setXmlName(new javax.xml.namespace.QName("", "srcAccountBranch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("srcAccountNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "srcAccountNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountOpeningAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountOpeningAmount"));
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
     * @return the srcPayinPercentage
     */
    public java.lang.String getSrcPayinPercentage() {
        return srcPayinPercentage;
    }

    /**
     * @param srcPayinPercentage the srcPayinPercentage to set
     */
    public void setSrcPayinPercentage(java.lang.String srcPayinPercentage) {
        this.srcPayinPercentage = srcPayinPercentage;
    }

}
