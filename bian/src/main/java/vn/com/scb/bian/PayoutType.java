/**
 * PayoutType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import com.google.gson.annotations.Expose;

public class PayoutType  implements java.io.Serializable {
    /* Loại chỉ định thanh toán lúc đáo hạn */
     
    private java.lang.String payoutType;

    /* loại hình tài khoản tái ký */
    private java.lang.String payoutAClass;

    /* Số tài khoản chỉ định lúc đáo hạn */
     
    private java.lang.String payoutAccount;

    /* Số tài khoản trả lãi định kỳ */
    private java.lang.String bookAccount;

    /* Hình thức tất toán tài khoản. Chỉ sử dụng khi
     *     					tất toán tài khoản. 'C' : Tiền mặt; 'S': Chuyển
     *     					khoản */
    private java.lang.String payoutMode;

    /* Số tiền tất toán - chỉ sử dụng khi tất toán tài
     *     					khoản. */
    private java.lang.String payoutAmount;

    /* Đơn vị tài khoản đích */
     
    private java.lang.String payoutAccountBRN;
      
     private java.lang.String payoutPercentage;

    public PayoutType() {
    }

    public PayoutType(
           java.lang.String payoutType,
           java.lang.String payoutAClass,
           java.lang.String payoutAccount,
           java.lang.String bookAccount,
           java.lang.String payoutMode,
           java.lang.String payoutAmount,
           java.lang.String payoutAccountBRN) {
           this.payoutType = payoutType;
           this.payoutAClass = payoutAClass;
           this.payoutAccount = payoutAccount;
           this.bookAccount = bookAccount;
           this.payoutMode = payoutMode;
           this.payoutAmount = payoutAmount;
           this.payoutAccountBRN = payoutAccountBRN;
    }


    /**
     * Gets the payoutType value for this PayoutType.
     * 
     * @return payoutType   * Loại chỉ định thanh toán lúc đáo hạn
     */
    public java.lang.String getPayoutType() {
        return payoutType;
    }


    /**
     * Sets the payoutType value for this PayoutType.
     * 
     * @param payoutType   * Loại chỉ định thanh toán lúc đáo hạn
     */
    public void setPayoutType(java.lang.String payoutType) {
        this.payoutType = payoutType;
    }


    /**
     * Gets the payoutAClass value for this PayoutType.
     * 
     * @return payoutAClass   * loại hình tài khoản tái ký
     */
    public java.lang.String getPayoutAClass() {
        return payoutAClass;
    }


    /**
     * Sets the payoutAClass value for this PayoutType.
     * 
     * @param payoutAClass   * loại hình tài khoản tái ký
     */
    public void setPayoutAClass(java.lang.String payoutAClass) {
        this.payoutAClass = payoutAClass;
    }


    /**
     * Gets the payoutAccount value for this PayoutType.
     * 
     * @return payoutAccount   * Số tài khoản chỉ định lúc đáo hạn
     */
    public java.lang.String getPayoutAccount() {
        return payoutAccount;
    }


    /**
     * Sets the payoutAccount value for this PayoutType.
     * 
     * @param payoutAccount   * Số tài khoản chỉ định lúc đáo hạn
     */
    public void setPayoutAccount(java.lang.String payoutAccount) {
        this.payoutAccount = payoutAccount;
    }


    /**
     * Gets the bookAccount value for this PayoutType.
     * 
     * @return bookAccount   * Số tài khoản trả lãi định kỳ
     */
    public java.lang.String getBookAccount() {
        return bookAccount;
    }


    /**
     * Sets the bookAccount value for this PayoutType.
     * 
     * @param bookAccount   * Số tài khoản trả lãi định kỳ
     */
    public void setBookAccount(java.lang.String bookAccount) {
        this.bookAccount = bookAccount;
    }


    /**
     * Gets the payoutMode value for this PayoutType.
     * 
     * @return payoutMode   * Hình thức tất toán tài khoản. Chỉ sử dụng khi
     *     					tất toán tài khoản. 'C' : Tiền mặt; 'S': Chuyển
     *     					khoản
     */
    public java.lang.String getPayoutMode() {
        return payoutMode;
    }


    /**
     * Sets the payoutMode value for this PayoutType.
     * 
     * @param payoutMode   * Hình thức tất toán tài khoản. Chỉ sử dụng khi
     *     					tất toán tài khoản. 'C' : Tiền mặt; 'S': Chuyển
     *     					khoản
     */
    public void setPayoutMode(java.lang.String payoutMode) {
        this.payoutMode = payoutMode;
    }


    /**
     * Gets the payoutAmount value for this PayoutType.
     * 
     * @return payoutAmount   * Số tiền tất toán - chỉ sử dụng khi tất toán tài
     *     					khoản.
     */
    public java.lang.String getPayoutAmount() {
        return payoutAmount;
    }


    /**
     * Sets the payoutAmount value for this PayoutType.
     * 
     * @param payoutAmount   * Số tiền tất toán - chỉ sử dụng khi tất toán tài
     *     					khoản.
     */
    public void setPayoutAmount(java.lang.String payoutAmount) {
        this.payoutAmount = payoutAmount;
    }


    /**
     * Gets the payoutAccountBRN value for this PayoutType.
     * 
     * @return payoutAccountBRN   * Đơn vị tài khoản đích
     */
    public java.lang.String getPayoutAccountBRN() {
        return payoutAccountBRN;
    }


    /**
     * Sets the payoutAccountBRN value for this PayoutType.
     * 
     * @param payoutAccountBRN   * Đơn vị tài khoản đích
     */
    public void setPayoutAccountBRN(java.lang.String payoutAccountBRN) {
        this.payoutAccountBRN = payoutAccountBRN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PayoutType)) return false;
        PayoutType other = (PayoutType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.payoutType==null && other.getPayoutType()==null) || 
             (this.payoutType!=null &&
              this.payoutType.equals(other.getPayoutType()))) &&
            ((this.payoutAClass==null && other.getPayoutAClass()==null) || 
             (this.payoutAClass!=null &&
              this.payoutAClass.equals(other.getPayoutAClass()))) &&
            ((this.payoutAccount==null && other.getPayoutAccount()==null) || 
             (this.payoutAccount!=null &&
              this.payoutAccount.equals(other.getPayoutAccount()))) &&
            ((this.bookAccount==null && other.getBookAccount()==null) || 
             (this.bookAccount!=null &&
              this.bookAccount.equals(other.getBookAccount()))) &&
            ((this.payoutMode==null && other.getPayoutMode()==null) || 
             (this.payoutMode!=null &&
              this.payoutMode.equals(other.getPayoutMode()))) &&
            ((this.payoutAmount==null && other.getPayoutAmount()==null) || 
             (this.payoutAmount!=null &&
              this.payoutAmount.equals(other.getPayoutAmount()))) &&
            ((this.payoutAccountBRN==null && other.getPayoutAccountBRN()==null) || 
             (this.payoutAccountBRN!=null &&
              this.payoutAccountBRN.equals(other.getPayoutAccountBRN())));
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
        if (getPayoutType() != null) {
            _hashCode += getPayoutType().hashCode();
        }
        if (getPayoutAClass() != null) {
            _hashCode += getPayoutAClass().hashCode();
        }
        if (getPayoutAccount() != null) {
            _hashCode += getPayoutAccount().hashCode();
        }
        if (getBookAccount() != null) {
            _hashCode += getBookAccount().hashCode();
        }
        if (getPayoutMode() != null) {
            _hashCode += getPayoutMode().hashCode();
        }
        if (getPayoutAmount() != null) {
            _hashCode += getPayoutAmount().hashCode();
        }
        if (getPayoutAccountBRN() != null) {
            _hashCode += getPayoutAccountBRN().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PayoutType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "PayoutType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payoutType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payoutType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payoutAClass");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payoutAClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payoutAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payoutAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bookAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payoutMode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payoutMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payoutAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payoutAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payoutAccountBRN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payoutAccountBRN"));
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
     * @return the payoutPercentage
     */
    public java.lang.String getPayoutPercentage() {
        return payoutPercentage;
    }

    /**
     * @param payoutPercentage the payoutPercentage to set
     */
    public void setPayoutPercentage(java.lang.String payoutPercentage) {
        this.payoutPercentage = payoutPercentage;
    }

}
