/**
 * CancelBills.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class CancelBills  implements java.io.Serializable {
    private java.lang.String bankID;

    private int serviceType;

    private java.lang.String customerCode;

    private java.lang.String[] billCodes;

    private int[] amount;

    private java.util.Calendar cancelDate;

    private java.lang.String[] transactionCode;

    public CancelBills() {
    }

    public CancelBills(
           java.lang.String bankID,
           int serviceType,
           java.lang.String customerCode,
           java.lang.String[] billCodes,
           int[] amount,
           java.util.Calendar cancelDate,
           java.lang.String[] transactionCode) {
           this.bankID = bankID;
           this.serviceType = serviceType;
           this.customerCode = customerCode;
           this.billCodes = billCodes;
           this.amount = amount;
           this.cancelDate = cancelDate;
           this.transactionCode = transactionCode;
    }


    /**
     * Gets the bankID value for this CancelBills.
     * 
     * @return bankID
     */
    public java.lang.String getBankID() {
        return bankID;
    }


    /**
     * Sets the bankID value for this CancelBills.
     * 
     * @param bankID
     */
    public void setBankID(java.lang.String bankID) {
        this.bankID = bankID;
    }


    /**
     * Gets the serviceType value for this CancelBills.
     * 
     * @return serviceType
     */
    public int getServiceType() {
        return serviceType;
    }


    /**
     * Sets the serviceType value for this CancelBills.
     * 
     * @param serviceType
     */
    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }


    /**
     * Gets the customerCode value for this CancelBills.
     * 
     * @return customerCode
     */
    public java.lang.String getCustomerCode() {
        return customerCode;
    }


    /**
     * Sets the customerCode value for this CancelBills.
     * 
     * @param customerCode
     */
    public void setCustomerCode(java.lang.String customerCode) {
        this.customerCode = customerCode;
    }


    /**
     * Gets the billCodes value for this CancelBills.
     * 
     * @return billCodes
     */
    public java.lang.String[] getBillCodes() {
        return billCodes;
    }


    /**
     * Sets the billCodes value for this CancelBills.
     * 
     * @param billCodes
     */
    public void setBillCodes(java.lang.String[] billCodes) {
        this.billCodes = billCodes;
    }


    /**
     * Gets the amount value for this CancelBills.
     * 
     * @return amount
     */
    public int[] getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this CancelBills.
     * 
     * @param amount
     */
    public void setAmount(int[] amount) {
        this.amount = amount;
    }


    /**
     * Gets the cancelDate value for this CancelBills.
     * 
     * @return cancelDate
     */
    public java.util.Calendar getCancelDate() {
        return cancelDate;
    }


    /**
     * Sets the cancelDate value for this CancelBills.
     * 
     * @param cancelDate
     */
    public void setCancelDate(java.util.Calendar cancelDate) {
        this.cancelDate = cancelDate;
    }


    /**
     * Gets the transactionCode value for this CancelBills.
     * 
     * @return transactionCode
     */
    public java.lang.String[] getTransactionCode() {
        return transactionCode;
    }


    /**
     * Sets the transactionCode value for this CancelBills.
     * 
     * @param transactionCode
     */
    public void setTransactionCode(java.lang.String[] transactionCode) {
        this.transactionCode = transactionCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelBills)) return false;
        CancelBills other = (CancelBills) obj;
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
        new org.apache.axis.description.TypeDesc(CancelBills.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">CancelBills"));
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
        elemField.setFieldName("cancelDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CancelDate"));
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
