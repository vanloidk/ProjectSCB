/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.scb.bian;

import java.util.List;

public class RetrieveLetterOfCreditInfo_out_Type implements java.io.Serializable {

    private vn.com.scb.bian.TransactionInfoType transactionInfo;

    private List<vn.com.scb.bian.LetterOfCreditInfoType> letterOfCreditInfo;
   

    public RetrieveLetterOfCreditInfo_out_Type() {
    }

    public RetrieveLetterOfCreditInfo_out_Type(
            vn.com.scb.bian.TransactionInfoType transactionInfo,
            List<vn.com.scb.bian.LetterOfCreditInfoType> letterOfCreditInfo) {
        this.transactionInfo = transactionInfo;
        this.letterOfCreditInfo = letterOfCreditInfo;
    }

    /**
     * @return the transactionInfo
     */
    public vn.com.scb.bian.TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }

    /**
     * @param transactionInfo the transactionInfo to set
     */
    public void setTransactionInfo(vn.com.scb.bian.TransactionInfoType transactionInfo) {
        this.transactionInfo = transactionInfo;
    }

    /**
     * @return the letterOfCreditInfo
     */
    public List<vn.com.scb.bian.LetterOfCreditInfoType> getLetterOfCreditInfo() {
        return letterOfCreditInfo;
    }

    /**
     * @param letterOfCreditInfo the letterOfCreditInfo to set
     */
    public void setLetterOfCreditInfo(List<vn.com.scb.bian.LetterOfCreditInfoType> letterOfCreditInfo) {
        this.letterOfCreditInfo = letterOfCreditInfo;
    }
   
   
    // Add a container for the root element
    public static class Container {

        public RetrieveLetterOfCreditInfo_out_Type retrieveLetterOfCreditInfo_out;
    }
    
    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetrieveLetterOfCreditInfo_out_Type)) return false;
        RetrieveLetterOfCreditInfo_out_Type other = (RetrieveLetterOfCreditInfo_out_Type) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.transactionInfo==null && other.getTransactionInfo()==null) || 
             (this.transactionInfo!=null &&
              this.transactionInfo.equals(other.getTransactionInfo()))) &&
            ((this.getLetterOfCreditInfo()==null && other.getLetterOfCreditInfo()==null) || 
             (this.getLetterOfCreditInfo()!=null &&
                this.getLetterOfCreditInfo().equals(other.getLetterOfCreditInfo())));
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
        if (getTransactionInfo() != null) {
            _hashCode += getTransactionInfo().hashCode();
        }
        if (getLetterOfCreditInfo() != null) {
            _hashCode += getLetterOfCreditInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetrieveLetterOfCreditInfo_out_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "retrieveLetterOfCreditInfo_out_Type"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "TransactionInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("letterOfCreditInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "letterOfCreditInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "LetterOfCreditInfoType"));
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
