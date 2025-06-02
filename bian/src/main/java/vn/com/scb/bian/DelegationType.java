/**
 * DelegationType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class DelegationType  implements java.io.Serializable {
    /* Số ủy quyền */
    private java.lang.String delegationNum;

    /* Ngày lập ủy quyền */
    private java.lang.String delegationDate;

    /* Nơi lập ủy quyền */
    private java.lang.String delegationLocation;

    /* Họ tên đại diện bên vay */
    private java.lang.String delegationFullname;

    public DelegationType() {
    }

    public DelegationType(
           java.lang.String delegationNum,
           java.lang.String delegationDate,
           java.lang.String delegationLocation,
           java.lang.String delegationFullname) {
           this.delegationNum = delegationNum;
           this.delegationDate = delegationDate;
           this.delegationLocation = delegationLocation;
           this.delegationFullname = delegationFullname;
    }


    /**
     * Gets the delegationNum value for this DelegationType.
     * 
     * @return delegationNum   * Số ủy quyền
     */
    public java.lang.String getDelegationNum() {
        return delegationNum;
    }


    /**
     * Sets the delegationNum value for this DelegationType.
     * 
     * @param delegationNum   * Số ủy quyền
     */
    public void setDelegationNum(java.lang.String delegationNum) {
        this.delegationNum = delegationNum;
    }


    /**
     * Gets the delegationDate value for this DelegationType.
     * 
     * @return delegationDate   * Ngày lập ủy quyền
     */
    public java.lang.String getDelegationDate() {
        return delegationDate;
    }


    /**
     * Sets the delegationDate value for this DelegationType.
     * 
     * @param delegationDate   * Ngày lập ủy quyền
     */
    public void setDelegationDate(java.lang.String delegationDate) {
        this.delegationDate = delegationDate;
    }


    /**
     * Gets the delegationLocation value for this DelegationType.
     * 
     * @return delegationLocation   * Nơi lập ủy quyền
     */
    public java.lang.String getDelegationLocation() {
        return delegationLocation;
    }


    /**
     * Sets the delegationLocation value for this DelegationType.
     * 
     * @param delegationLocation   * Nơi lập ủy quyền
     */
    public void setDelegationLocation(java.lang.String delegationLocation) {
        this.delegationLocation = delegationLocation;
    }


    /**
     * Gets the delegationFullname value for this DelegationType.
     * 
     * @return delegationFullname   * Họ tên đại diện bên vay
     */
    public java.lang.String getDelegationFullname() {
        return delegationFullname;
    }


    /**
     * Sets the delegationFullname value for this DelegationType.
     * 
     * @param delegationFullname   * Họ tên đại diện bên vay
     */
    public void setDelegationFullname(java.lang.String delegationFullname) {
        this.delegationFullname = delegationFullname;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DelegationType)) return false;
        DelegationType other = (DelegationType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.delegationNum==null && other.getDelegationNum()==null) || 
             (this.delegationNum!=null &&
              this.delegationNum.equals(other.getDelegationNum()))) &&
            ((this.delegationDate==null && other.getDelegationDate()==null) || 
             (this.delegationDate!=null &&
              this.delegationDate.equals(other.getDelegationDate()))) &&
            ((this.delegationLocation==null && other.getDelegationLocation()==null) || 
             (this.delegationLocation!=null &&
              this.delegationLocation.equals(other.getDelegationLocation()))) &&
            ((this.delegationFullname==null && other.getDelegationFullname()==null) || 
             (this.delegationFullname!=null &&
              this.delegationFullname.equals(other.getDelegationFullname())));
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
        if (getDelegationNum() != null) {
            _hashCode += getDelegationNum().hashCode();
        }
        if (getDelegationDate() != null) {
            _hashCode += getDelegationDate().hashCode();
        }
        if (getDelegationLocation() != null) {
            _hashCode += getDelegationLocation().hashCode();
        }
        if (getDelegationFullname() != null) {
            _hashCode += getDelegationFullname().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DelegationType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "DelegationType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delegationNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "delegationNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delegationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "delegationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delegationLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "delegationLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delegationFullname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "delegationFullname"));
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
