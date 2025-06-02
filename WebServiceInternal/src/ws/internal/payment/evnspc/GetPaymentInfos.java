/**
 * GetPaymentInfos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class GetPaymentInfos  implements java.io.Serializable {
    private int bankID;

    private int serviceType;

    private java.util.Calendar fromPayDate;

    private java.util.Calendar toPayDate;

    private java.lang.String departCode2;

    public GetPaymentInfos() {
    }

    public GetPaymentInfos(
           int bankID,
           int serviceType,
           java.util.Calendar fromPayDate,
           java.util.Calendar toPayDate,
           java.lang.String departCode2) {
           this.bankID = bankID;
           this.serviceType = serviceType;
           this.fromPayDate = fromPayDate;
           this.toPayDate = toPayDate;
           this.departCode2 = departCode2;
    }


    /**
     * Gets the bankID value for this GetPaymentInfos.
     * 
     * @return bankID
     */
    public int getBankID() {
        return bankID;
    }


    /**
     * Sets the bankID value for this GetPaymentInfos.
     * 
     * @param bankID
     */
    public void setBankID(int bankID) {
        this.bankID = bankID;
    }


    /**
     * Gets the serviceType value for this GetPaymentInfos.
     * 
     * @return serviceType
     */
    public int getServiceType() {
        return serviceType;
    }


    /**
     * Sets the serviceType value for this GetPaymentInfos.
     * 
     * @param serviceType
     */
    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }


    /**
     * Gets the fromPayDate value for this GetPaymentInfos.
     * 
     * @return fromPayDate
     */
    public java.util.Calendar getFromPayDate() {
        return fromPayDate;
    }


    /**
     * Sets the fromPayDate value for this GetPaymentInfos.
     * 
     * @param fromPayDate
     */
    public void setFromPayDate(java.util.Calendar fromPayDate) {
        this.fromPayDate = fromPayDate;
    }


    /**
     * Gets the toPayDate value for this GetPaymentInfos.
     * 
     * @return toPayDate
     */
    public java.util.Calendar getToPayDate() {
        return toPayDate;
    }


    /**
     * Sets the toPayDate value for this GetPaymentInfos.
     * 
     * @param toPayDate
     */
    public void setToPayDate(java.util.Calendar toPayDate) {
        this.toPayDate = toPayDate;
    }


    /**
     * Gets the departCode2 value for this GetPaymentInfos.
     * 
     * @return departCode2
     */
    public java.lang.String getDepartCode2() {
        return departCode2;
    }


    /**
     * Sets the departCode2 value for this GetPaymentInfos.
     * 
     * @param departCode2
     */
    public void setDepartCode2(java.lang.String departCode2) {
        this.departCode2 = departCode2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetPaymentInfos)) return false;
        GetPaymentInfos other = (GetPaymentInfos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.bankID == other.getBankID() &&
            this.serviceType == other.getServiceType() &&
            ((this.fromPayDate==null && other.getFromPayDate()==null) || 
             (this.fromPayDate!=null &&
              this.fromPayDate.equals(other.getFromPayDate()))) &&
            ((this.toPayDate==null && other.getToPayDate()==null) || 
             (this.toPayDate!=null &&
              this.toPayDate.equals(other.getToPayDate()))) &&
            ((this.departCode2==null && other.getDepartCode2()==null) || 
             (this.departCode2!=null &&
              this.departCode2.equals(other.getDepartCode2())));
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
        _hashCode += getBankID();
        _hashCode += getServiceType();
        if (getFromPayDate() != null) {
            _hashCode += getFromPayDate().hashCode();
        }
        if (getToPayDate() != null) {
            _hashCode += getToPayDate().hashCode();
        }
        if (getDepartCode2() != null) {
            _hashCode += getDepartCode2().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetPaymentInfos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetPaymentInfos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BankID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ServiceType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromPayDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FromPayDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toPayDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ToPayDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("departCode2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DepartCode2"));
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
