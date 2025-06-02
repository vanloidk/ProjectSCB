/**
 * CheckBill_BankidResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.payment.evnspc;

public class CheckBill_BankidResponse  implements java.io.Serializable {
    private ws.internal.payment.evnspc.CheckBill_BankidResponseCheckBill_BankidResult checkBill_BankidResult;

    public CheckBill_BankidResponse() {
    }

    public CheckBill_BankidResponse(
           ws.internal.payment.evnspc.CheckBill_BankidResponseCheckBill_BankidResult checkBill_BankidResult) {
           this.checkBill_BankidResult = checkBill_BankidResult;
    }


    /**
     * Gets the checkBill_BankidResult value for this CheckBill_BankidResponse.
     * 
     * @return checkBill_BankidResult
     */
    public ws.internal.payment.evnspc.CheckBill_BankidResponseCheckBill_BankidResult getCheckBill_BankidResult() {
        return checkBill_BankidResult;
    }


    /**
     * Sets the checkBill_BankidResult value for this CheckBill_BankidResponse.
     * 
     * @param checkBill_BankidResult
     */
    public void setCheckBill_BankidResult(ws.internal.payment.evnspc.CheckBill_BankidResponseCheckBill_BankidResult checkBill_BankidResult) {
        this.checkBill_BankidResult = checkBill_BankidResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckBill_BankidResponse)) return false;
        CheckBill_BankidResponse other = (CheckBill_BankidResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.checkBill_BankidResult==null && other.getCheckBill_BankidResult()==null) || 
             (this.checkBill_BankidResult!=null &&
              this.checkBill_BankidResult.equals(other.getCheckBill_BankidResult())));
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
        if (getCheckBill_BankidResult() != null) {
            _hashCode += getCheckBill_BankidResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckBill_BankidResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">CheckBill_BankidResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkBill_BankidResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CheckBill_BankidResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>CheckBill_BankidResponse>CheckBill_BankidResult"));
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
