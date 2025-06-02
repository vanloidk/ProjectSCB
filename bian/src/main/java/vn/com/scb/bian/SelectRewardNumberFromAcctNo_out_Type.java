/**
 * InitiateCurrentAccountCASA_out_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import java.util.List;

public class SelectRewardNumberFromAcctNo_out_Type  implements java.io.Serializable {
    private vn.com.scb.bian.TransactionInfoType transactionInfo;

    private List<vn.com.scb.bian.RewardNumListType> rewardNumList;

    public SelectRewardNumberFromAcctNo_out_Type() {
    }

    /**
     * @return the rewardNumList
     */
    public List<vn.com.scb.bian.RewardNumListType> getRewardNumList() {
        return rewardNumList;
    }

    /**
     * @param rewardNumList the rewardNumList to set
     */
    public void setRewardNumList(List<vn.com.scb.bian.RewardNumListType> rewardNumList) {
        this.rewardNumList = rewardNumList;
    }
    
    public static class Container {
        public SelectRewardNumberFromAcctNo_out_Type selectRewardNumberFromAcctNo_out;
    }

    public SelectRewardNumberFromAcctNo_out_Type(
           vn.com.scb.bian.TransactionInfoType transactionInfo,
           List<vn.com.scb.bian.RewardNumListType> rewardNumList) {
           this.transactionInfo = transactionInfo;
           this.rewardNumList = rewardNumList;
    }


    /**
     * Gets the transactionInfo value for this InitiateCurrentAccountCASA_out_Type.
     * 
     * @return transactionInfo
     */
    public vn.com.scb.bian.TransactionInfoType getTransactionInfo() {
        return transactionInfo;
    }


    /**
     * Sets the transactionInfo value for this InitiateCurrentAccountCASA_out_Type.
     * 
     * @param transactionInfo
     */
    public void setTransactionInfo(vn.com.scb.bian.TransactionInfoType transactionInfo) {
        this.transactionInfo = transactionInfo;
    }


 

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectRewardNumberFromAcctNo_out_Type)) return false;
        SelectRewardNumberFromAcctNo_out_Type other = (SelectRewardNumberFromAcctNo_out_Type) obj;
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
            ((this.getRewardNumList()==null && other.getRewardNumList()==null) || 
             (this.getRewardNumList()!=null &&
                this.getRewardNumList().equals(other.getRewardNumList())));
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
        if (getRewardNumList() != null) {
            _hashCode += getRewardNumList().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SelectRewardNumberFromAcctNo_out_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "initiateCurrentAccountCASA_out_Type"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "TransactionInfoType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rewardNumList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rewardNumList"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "RewardNumListType"));
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
