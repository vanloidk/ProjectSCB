/**
 * PayBills.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class PayBills  implements java.io.Serializable {
    private java.lang.String bankID;

    private int serviceType;

    private java.lang.String customerCode;

    private java.lang.String[] billCodes;

    private int[] amount;

    private java.util.Calendar payDate;

    private java.lang.String[] transactionCode;

    private java.lang.String kyHieuHoaDon;

    private java.lang.String departCode;

    private java.lang.String daInHD;

    public PayBills() {
    }

    public PayBills(
           java.lang.String bankID,
           int serviceType,
           java.lang.String customerCode,
           java.lang.String[] billCodes,
           int[] amount,
           java.util.Calendar payDate,
           java.lang.String[] transactionCode,
           java.lang.String kyHieuHoaDon,
           java.lang.String departCode,
           java.lang.String daInHD) {
           this.bankID = bankID;
           this.serviceType = serviceType;
           this.customerCode = customerCode;
           this.billCodes = billCodes;
           this.amount = amount;
           this.payDate = payDate;
           this.transactionCode = transactionCode;
           this.kyHieuHoaDon = kyHieuHoaDon;
           this.departCode = departCode;
           this.daInHD = daInHD;
    }


    /**
     * Gets the bankID value for this PayBills.
     * 
     * @return bankID
     */
    public java.lang.String getBankID() {
        return bankID;
    }


    /**
     * Sets the bankID value for this PayBills.
     * 
     * @param bankID
     */
    public void setBankID(java.lang.String bankID) {
        this.bankID = bankID;
    }


    /**
     * Gets the serviceType value for this PayBills.
     * 
     * @return serviceType
     */
    public int getServiceType() {
        return serviceType;
    }


    /**
     * Sets the serviceType value for this PayBills.
     * 
     * @param serviceType
     */
    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }


    /**
     * Gets the customerCode value for this PayBills.
     * 
     * @return customerCode
     */
    public java.lang.String getCustomerCode() {
        return customerCode;
    }


    /**
     * Sets the customerCode value for this PayBills.
     * 
     * @param customerCode
     */
    public void setCustomerCode(java.lang.String customerCode) {
        this.customerCode = customerCode;
    }


    /**
     * Gets the billCodes value for this PayBills.
     * 
     * @return billCodes
     */
    public java.lang.String[] getBillCodes() {
        return billCodes;
    }


    /**
     * Sets the billCodes value for this PayBills.
     * 
     * @param billCodes
     */
    public void setBillCodes(java.lang.String[] billCodes) {
        this.billCodes = billCodes;
    }


    /**
     * Gets the amount value for this PayBills.
     * 
     * @return amount
     */
    public int[] getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this PayBills.
     * 
     * @param amount
     */
    public void setAmount(int[] amount) {
        this.amount = amount;
    }


    /**
     * Gets the payDate value for this PayBills.
     * 
     * @return payDate
     */
    public java.util.Calendar getPayDate() {
        return payDate;
    }


    /**
     * Sets the payDate value for this PayBills.
     * 
     * @param payDate
     */
    public void setPayDate(java.util.Calendar payDate) {
        this.payDate = payDate;
    }


    /**
     * Gets the transactionCode value for this PayBills.
     * 
     * @return transactionCode
     */
    public java.lang.String[] getTransactionCode() {
        return transactionCode;
    }


    /**
     * Sets the transactionCode value for this PayBills.
     * 
     * @param transactionCode
     */
    public void setTransactionCode(java.lang.String[] transactionCode) {
        this.transactionCode = transactionCode;
    }


    /**
     * Gets the kyHieuHoaDon value for this PayBills.
     * 
     * @return kyHieuHoaDon
     */
    public java.lang.String getKyHieuHoaDon() {
        return kyHieuHoaDon;
    }


    /**
     * Sets the kyHieuHoaDon value for this PayBills.
     * 
     * @param kyHieuHoaDon
     */
    public void setKyHieuHoaDon(java.lang.String kyHieuHoaDon) {
        this.kyHieuHoaDon = kyHieuHoaDon;
    }


    /**
     * Gets the departCode value for this PayBills.
     * 
     * @return departCode
     */
    public java.lang.String getDepartCode() {
        return departCode;
    }


    /**
     * Sets the departCode value for this PayBills.
     * 
     * @param departCode
     */
    public void setDepartCode(java.lang.String departCode) {
        this.departCode = departCode;
    }


    /**
     * Gets the daInHD value for this PayBills.
     * 
     * @return daInHD
     */
    public java.lang.String getDaInHD() {
        return daInHD;
    }


    /**
     * Sets the daInHD value for this PayBills.
     * 
     * @param daInHD
     */
    public void setDaInHD(java.lang.String daInHD) {
        this.daInHD = daInHD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PayBills)) return false;
        PayBills other = (PayBills) obj;
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
            this.serviceType == other.getServiceType() &&
            ((this.customerCode==null && other.getCustomerCode()==null) || 
             (this.customerCode!=null &&
              this.customerCode.equals(other.getCustomerCode()))) &&
            ((this.billCodes==null && other.getBillCodes()==null) || 
             (this.billCodes!=null &&
              java.util.Arrays.equals(this.billCodes, other.getBillCodes()))) &&
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              java.util.Arrays.equals(this.amount, other.getAmount()))) &&
            ((this.payDate==null && other.getPayDate()==null) || 
             (this.payDate!=null &&
              this.payDate.equals(other.getPayDate()))) &&
            ((this.transactionCode==null && other.getTransactionCode()==null) || 
             (this.transactionCode!=null &&
              java.util.Arrays.equals(this.transactionCode, other.getTransactionCode()))) &&
            ((this.kyHieuHoaDon==null && other.getKyHieuHoaDon()==null) || 
             (this.kyHieuHoaDon!=null &&
              this.kyHieuHoaDon.equals(other.getKyHieuHoaDon()))) &&
            ((this.departCode==null && other.getDepartCode()==null) || 
             (this.departCode!=null &&
              this.departCode.equals(other.getDepartCode()))) &&
            ((this.daInHD==null && other.getDaInHD()==null) || 
             (this.daInHD!=null &&
              this.daInHD.equals(other.getDaInHD())));
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
        _hashCode += getServiceType();
        if (getCustomerCode() != null) {
            _hashCode += getCustomerCode().hashCode();
        }
        if (getBillCodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBillCodes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBillCodes(), i);
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
        if (getPayDate() != null) {
            _hashCode += getPayDate().hashCode();
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
        if (getKyHieuHoaDon() != null) {
            _hashCode += getKyHieuHoaDon().hashCode();
        }
        if (getDepartCode() != null) {
            _hashCode += getDepartCode().hashCode();
        }
        if (getDaInHD() != null) {
            _hashCode += getDaInHD().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PayBills.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">PayBills"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BankID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ServiceType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CustomerCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billCodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BillCodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PayDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TransactionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kyHieuHoaDon");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "KyHieuHoaDon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("departCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DepartCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("daInHD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DaInHD"));
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
