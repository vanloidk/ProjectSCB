/**
 * IDInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class IDInfoType  implements java.io.Serializable {
    /* CMND/Hộ chiếu, số đăng ký kinh doanh nếu là
     *     					khách hàng doanh nghiệp */
    private java.lang.String IDNum;

    /* IDIssuedDate */
    private java.lang.String IDIssuedDate;

    /* Nơi cấp chứng minh nhân dân hoặc hộ chiếu */
    private java.lang.String IDIssuedLocation;

    /* Loại chứng minh nhân dân, hoặc hộ chiếu. 'C': Chứng minh nhân
     * dân, 'P' hộ chiếu */
    private java.lang.String IDType;

    public IDInfoType() {
    }

    public IDInfoType(
           java.lang.String IDNum,
           java.lang.String IDIssuedDate,
           java.lang.String IDIssuedLocation,
           java.lang.String IDType) {
           this.IDNum = IDNum;
           this.IDIssuedDate = IDIssuedDate;
           this.IDIssuedLocation = IDIssuedLocation;
           this.IDType = IDType;
    }


    /**
     * Gets the IDNum value for this IDInfoType.
     * 
     * @return IDNum   * CMND/Hộ chiếu, số đăng ký kinh doanh nếu là
     *     					khách hàng doanh nghiệp
     */
    public java.lang.String getIDNum() {
        return IDNum;
    }


    /**
     * Sets the IDNum value for this IDInfoType.
     * 
     * @param IDNum   * CMND/Hộ chiếu, số đăng ký kinh doanh nếu là
     *     					khách hàng doanh nghiệp
     */
    public void setIDNum(java.lang.String IDNum) {
        this.IDNum = IDNum;
    }


    /**
     * Gets the IDIssuedDate value for this IDInfoType.
     * 
     * @return IDIssuedDate   * IDIssuedDate
     */
    public java.lang.String getIDIssuedDate() {
        return IDIssuedDate;
    }


    /**
     * Sets the IDIssuedDate value for this IDInfoType.
     * 
     * @param IDIssuedDate   * IDIssuedDate
     */
    public void setIDIssuedDate(java.lang.String IDIssuedDate) {
        this.IDIssuedDate = IDIssuedDate;
    }


    /**
     * Gets the IDIssuedLocation value for this IDInfoType.
     * 
     * @return IDIssuedLocation   * Nơi cấp chứng minh nhân dân hoặc hộ chiếu
     */
    public java.lang.String getIDIssuedLocation() {
        return IDIssuedLocation;
    }


    /**
     * Sets the IDIssuedLocation value for this IDInfoType.
     * 
     * @param IDIssuedLocation   * Nơi cấp chứng minh nhân dân hoặc hộ chiếu
     */
    public void setIDIssuedLocation(java.lang.String IDIssuedLocation) {
        this.IDIssuedLocation = IDIssuedLocation;
    }


    /**
     * Gets the IDType value for this IDInfoType.
     * 
     * @return IDType   * Loại chứng minh nhân dân, hoặc hộ chiếu. 'C': Chứng minh nhân
     * dân, 'P' hộ chiếu
     */
    public java.lang.String getIDType() {
        return IDType;
    }


    /**
     * Sets the IDType value for this IDInfoType.
     * 
     * @param IDType   * Loại chứng minh nhân dân, hoặc hộ chiếu. 'C': Chứng minh nhân
     * dân, 'P' hộ chiếu
     */
    public void setIDType(java.lang.String IDType) {
        this.IDType = IDType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IDInfoType)) return false;
        IDInfoType other = (IDInfoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.IDNum==null && other.getIDNum()==null) || 
             (this.IDNum!=null &&
              this.IDNum.equals(other.getIDNum()))) &&
            ((this.IDIssuedDate==null && other.getIDIssuedDate()==null) || 
             (this.IDIssuedDate!=null &&
              this.IDIssuedDate.equals(other.getIDIssuedDate()))) &&
            ((this.IDIssuedLocation==null && other.getIDIssuedLocation()==null) || 
             (this.IDIssuedLocation!=null &&
              this.IDIssuedLocation.equals(other.getIDIssuedLocation()))) &&
            ((this.IDType==null && other.getIDType()==null) || 
             (this.IDType!=null &&
              this.IDType.equals(other.getIDType())));
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
        if (getIDNum() != null) {
            _hashCode += getIDNum().hashCode();
        }
        if (getIDIssuedDate() != null) {
            _hashCode += getIDIssuedDate().hashCode();
        }
        if (getIDIssuedLocation() != null) {
            _hashCode += getIDIssuedLocation().hashCode();
        }
        if (getIDType() != null) {
            _hashCode += getIDType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IDInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "IDInfoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IDNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDIssuedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IDIssuedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDIssuedLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IDIssuedLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IDType"));
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
