/**
 * CancelBillsByCustomerCodeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class CancelBillsByCustomerCodeResponse  implements java.io.Serializable {
    private java.lang.Integer cancelBillsByCustomerCodeResult;

    public CancelBillsByCustomerCodeResponse() {
    }

    public CancelBillsByCustomerCodeResponse(
           java.lang.Integer cancelBillsByCustomerCodeResult) {
           this.cancelBillsByCustomerCodeResult = cancelBillsByCustomerCodeResult;
    }


    /**
     * Gets the cancelBillsByCustomerCodeResult value for this CancelBillsByCustomerCodeResponse.
     * 
     * @return cancelBillsByCustomerCodeResult
     */
    public java.lang.Integer getCancelBillsByCustomerCodeResult() {
        return cancelBillsByCustomerCodeResult;
    }


    /**
     * Sets the cancelBillsByCustomerCodeResult value for this CancelBillsByCustomerCodeResponse.
     * 
     * @param cancelBillsByCustomerCodeResult
     */
    public void setCancelBillsByCustomerCodeResult(java.lang.Integer cancelBillsByCustomerCodeResult) {
        this.cancelBillsByCustomerCodeResult = cancelBillsByCustomerCodeResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelBillsByCustomerCodeResponse)) return false;
        CancelBillsByCustomerCodeResponse other = (CancelBillsByCustomerCodeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cancelBillsByCustomerCodeResult==null && other.getCancelBillsByCustomerCodeResult()==null) || 
             (this.cancelBillsByCustomerCodeResult!=null &&
              this.cancelBillsByCustomerCodeResult.equals(other.getCancelBillsByCustomerCodeResult())));
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
        if (getCancelBillsByCustomerCodeResult() != null) {
            _hashCode += getCancelBillsByCustomerCodeResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelBillsByCustomerCodeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">CancelBillsByCustomerCodeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelBillsByCustomerCodeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CancelBillsByCustomerCodeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
