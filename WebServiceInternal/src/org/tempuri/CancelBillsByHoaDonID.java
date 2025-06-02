/**
 * CancelBillsByHoaDonID.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class CancelBillsByHoaDonID  implements java.io.Serializable {
    private java.lang.String bankID;

    private java.lang.String maDonViQL;

    private java.lang.String[] HDon_ID;

    private long[] amount;

    private java.util.Calendar cancelDate;

    private java.lang.String[] transactionCode;

    public CancelBillsByHoaDonID() {
    }

    public CancelBillsByHoaDonID(
           java.lang.String bankID,
           java.lang.String maDonViQL,
           java.lang.String[] HDon_ID,
           long[] amount,
           java.util.Calendar cancelDate,
           java.lang.String[] transactionCode) {
           this.bankID = bankID;
           this.maDonViQL = maDonViQL;
           this.HDon_ID = HDon_ID;
           this.amount = amount;
           this.cancelDate = cancelDate;
           this.transactionCode = transactionCode;
    }


    /**
     * Gets the bankID value for this CancelBillsByHoaDonID.
     * 
     * @return bankID
     */
    public java.lang.String getBankID() {
        return bankID;
    }


    /**
     * Sets the bankID value for this CancelBillsByHoaDonID.
     * 
     * @param bankID
     */
    public void setBankID(java.lang.String bankID) {
        this.bankID = bankID;
    }


    /**
     * Gets the maDonViQL value for this CancelBillsByHoaDonID.
     * 
     * @return maDonViQL
     */
    public java.lang.String getMaDonViQL() {
        return maDonViQL;
    }


    /**
     * Sets the maDonViQL value for this CancelBillsByHoaDonID.
     * 
     * @param maDonViQL
     */
    public void setMaDonViQL(java.lang.String maDonViQL) {
        this.maDonViQL = maDonViQL;
    }


    /**
     * Gets the HDon_ID value for this CancelBillsByHoaDonID.
     * 
     * @return HDon_ID
     */
    public java.lang.String[] getHDon_ID() {
        return HDon_ID;
    }


    /**
     * Sets the HDon_ID value for this CancelBillsByHoaDonID.
     * 
     * @param HDon_ID
     */
    public void setHDon_ID(java.lang.String[] HDon_ID) {
        this.HDon_ID = HDon_ID;
    }


    /**
     * Gets the amount value for this CancelBillsByHoaDonID.
     * 
     * @return amount
     */
    public long[] getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this CancelBillsByHoaDonID.
     * 
     * @param amount
     */
    public void setAmount(long[] amount) {
        this.amount = amount;
    }


    /**
     * Gets the cancelDate value for this CancelBillsByHoaDonID.
     * 
     * @return cancelDate
     */
    public java.util.Calendar getCancelDate() {
        return cancelDate;
    }


    /**
     * Sets the cancelDate value for this CancelBillsByHoaDonID.
     * 
     * @param cancelDate
     */
    public void setCancelDate(java.util.Calendar cancelDate) {
        this.cancelDate = cancelDate;
    }


    /**
     * Gets the transactionCode value for this CancelBillsByHoaDonID.
     * 
     * @return transactionCode
     */
    public java.lang.String[] getTransactionCode() {
        return transactionCode;
    }


    /**
     * Sets the transactionCode value for this CancelBillsByHoaDonID.
     * 
     * @param transactionCode
     */
    public void setTransactionCode(java.lang.String[] transactionCode) {
        this.transactionCode = transactionCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelBillsByHoaDonID)) return false;
        CancelBillsByHoaDonID other = (CancelBillsByHoaDonID) obj;
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
            ((this.HDon_ID==null && other.getHDon_ID()==null) || 
             (this.HDon_ID!=null &&
              java.util.Arrays.equals(this.HDon_ID, other.getHDon_ID()))) &&
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              java.util.Arrays.equals(this.amount, other.getAmount()))) &&
            ((this.cancelDate==null && other.getCancelDate()==null) || 
             (this.cancelDate!=null &&
              this.cancelDate.equals(other.getCancelDate()))) &&
            ((this.transactionCode==null && other.getTransactionCode()==null) || 
             (this.transactionCode!=null &&
              java.util.Arrays.equals(this.transactionCode, other.getTransactionCode())));
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
        if (getHDon_ID() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHDon_ID());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHDon_ID(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAmount() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAmount());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAmount(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCancelDate() != null) {
            _hashCode += getCancelDate().hashCode();
        }
        if (getTransactionCode() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTransactionCode());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTransactionCode(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelBillsByHoaDonID.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">CancelBillsByHoaDonID"));
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
        elemField.setFieldName("HDon_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "HDon_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "long"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CancelDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TransactionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
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
