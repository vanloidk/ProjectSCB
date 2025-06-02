/**
 * SenderInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class SenderInfoType  implements java.io.Serializable {
    /* Tài khoản người gửi */
    private java.lang.String senderAccount;

    /* Tên người gửi */
    private java.lang.String senderName;

    /* Cif người gửi */
    private java.lang.String senderCIFNum;

    /* Địa chỉ người gửi */
    private java.lang.String senderAddress;

    public SenderInfoType() {
    }

    public SenderInfoType(
           java.lang.String senderAccount,
           java.lang.String senderName,
           java.lang.String senderCIFNum,
           java.lang.String senderAddress) {
           this.senderAccount = senderAccount;
           this.senderName = senderName;
           this.senderCIFNum = senderCIFNum;
           this.senderAddress = senderAddress;
    }


    /**
     * Gets the senderAccount value for this SenderInfoType.
     * 
     * @return senderAccount   * Tài khoản người gửi
     */
    public java.lang.String getSenderAccount() {
        return senderAccount;
    }


    /**
     * Sets the senderAccount value for this SenderInfoType.
     * 
     * @param senderAccount   * Tài khoản người gửi
     */
    public void setSenderAccount(java.lang.String senderAccount) {
        this.senderAccount = senderAccount;
    }


    /**
     * Gets the senderName value for this SenderInfoType.
     * 
     * @return senderName   * Tên người gửi
     */
    public java.lang.String getSenderName() {
        return senderName;
    }


    /**
     * Sets the senderName value for this SenderInfoType.
     * 
     * @param senderName   * Tên người gửi
     */
    public void setSenderName(java.lang.String senderName) {
        this.senderName = senderName;
    }


    /**
     * Gets the senderCIFNum value for this SenderInfoType.
     * 
     * @return senderCIFNum   * Cif người gửi
     */
    public java.lang.String getSenderCIFNum() {
        return senderCIFNum;
    }


    /**
     * Sets the senderCIFNum value for this SenderInfoType.
     * 
     * @param senderCIFNum   * Cif người gửi
     */
    public void setSenderCIFNum(java.lang.String senderCIFNum) {
        this.senderCIFNum = senderCIFNum;
    }


    /**
     * Gets the senderAddress value for this SenderInfoType.
     * 
     * @return senderAddress   * Địa chỉ người gửi
     */
    public java.lang.String getSenderAddress() {
        return senderAddress;
    }


    /**
     * Sets the senderAddress value for this SenderInfoType.
     * 
     * @param senderAddress   * Địa chỉ người gửi
     */
    public void setSenderAddress(java.lang.String senderAddress) {
        this.senderAddress = senderAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SenderInfoType)) return false;
        SenderInfoType other = (SenderInfoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.senderAccount==null && other.getSenderAccount()==null) || 
             (this.senderAccount!=null &&
              this.senderAccount.equals(other.getSenderAccount()))) &&
            ((this.senderName==null && other.getSenderName()==null) || 
             (this.senderName!=null &&
              this.senderName.equals(other.getSenderName()))) &&
            ((this.senderCIFNum==null && other.getSenderCIFNum()==null) || 
             (this.senderCIFNum!=null &&
              this.senderCIFNum.equals(other.getSenderCIFNum()))) &&
            ((this.senderAddress==null && other.getSenderAddress()==null) || 
             (this.senderAddress!=null &&
              this.senderAddress.equals(other.getSenderAddress())));
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
        if (getSenderAccount() != null) {
            _hashCode += getSenderAccount().hashCode();
        }
        if (getSenderName() != null) {
            _hashCode += getSenderName().hashCode();
        }
        if (getSenderCIFNum() != null) {
            _hashCode += getSenderCIFNum().hashCode();
        }
        if (getSenderAddress() != null) {
            _hashCode += getSenderAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SenderInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "SenderInfoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderCIFNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderCIFNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderAddress"));
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

}
