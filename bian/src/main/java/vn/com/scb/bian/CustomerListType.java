/**
 * CustomerListType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class CustomerListType  implements java.io.Serializable {
    private vn.com.scb.bian.CIFDataType CIFInfo;

    private vn.com.scb.bian.CustomerInfoType customerInfo;

    public CustomerListType() {
    }

    public CustomerListType(
           vn.com.scb.bian.CIFDataType CIFInfo,
           vn.com.scb.bian.CustomerInfoType customerInfo) {
           this.CIFInfo = CIFInfo;
           this.customerInfo = customerInfo;
    }


    /**
     * Gets the CIFInfo value for this CustomerListType.
     * 
     * @return CIFInfo
     */
    public vn.com.scb.bian.CIFDataType getCIFInfo() {
        return CIFInfo;
    }


    /**
     * Sets the CIFInfo value for this CustomerListType.
     * 
     * @param CIFInfo
     */
    public void setCIFInfo(vn.com.scb.bian.CIFDataType CIFInfo) {
        this.CIFInfo = CIFInfo;
    }


    /**
     * Gets the customerInfo value for this CustomerListType.
     * 
     * @return customerInfo
     */
    public vn.com.scb.bian.CustomerInfoType getCustomerInfo() {
        return customerInfo;
    }


    /**
     * Sets the customerInfo value for this CustomerListType.
     * 
     * @param customerInfo
     */
    public void setCustomerInfo(vn.com.scb.bian.CustomerInfoType customerInfo) {
        this.customerInfo = customerInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CustomerListType)) return false;
        CustomerListType other = (CustomerListType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CIFInfo==null && other.getCIFInfo()==null) || 
             (this.CIFInfo!=null &&
              this.CIFInfo.equals(other.getCIFInfo()))) &&
            ((this.customerInfo==null && other.getCustomerInfo()==null) || 
             (this.customerInfo!=null &&
              this.customerInfo.equals(other.getCustomerInfo())));
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
        if (getCIFInfo() != null) {
            _hashCode += getCIFInfo().hashCode();
        }
        if (getCustomerInfo() != null) {
            _hashCode += getCustomerInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CustomerListType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CustomerListType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CIFInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CIFInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CIFDataType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customerInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CustomerInfoType"));
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
