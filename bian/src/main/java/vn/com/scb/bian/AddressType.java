/**
 * AddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class AddressType  implements java.io.Serializable {
    /* address1+ address2+ address3+ address4+ address5 */
    private java.lang.String address1;
    
    private java.lang.String address_vn;

    private java.lang.String address2;

    private java.lang.String ward;

    private java.lang.String city;

    private java.lang.String country;

    /* email */
    private java.lang.String email;

    /* Mobile Number */
    private java.lang.String mobileNum;

    public AddressType() {
    }

    public AddressType(
           java.lang.String address1,
           java.lang.String address_vn,
           java.lang.String address2,
           java.lang.String ward,
           java.lang.String city,
           java.lang.String country,
           java.lang.String email,
           java.lang.String mobileNum) {
           this.address1 = address1;
           this.address_vn = address_vn;            
           this.address2 = address2;
           this.ward = ward;
           this.city = city;
           this.country = country;
           this.email = email;
           this.mobileNum = mobileNum;
    }


    /**
     * Gets the address1 value for this AddressType.
     * 
     * @return address1   * address1+ address2+ address3+ address4+ address5
     */
    public java.lang.String getAddress1() {
        return address1;
    }


    /**
     * Sets the address1 value for this AddressType.
     * 
     * @param address1   * address1+ address2+ address3+ address4+ address5
     */
    public void setAddress1(java.lang.String address1) {
        this.address1 = address1;
    }


    /**
     * Gets the address2 value for this AddressType.
     * 
     * @return address2
     */
    public java.lang.String getAddress2() {
        return address2;
    }


    /**
     * Sets the address2 value for this AddressType.
     * 
     * @param address2
     */
    public void setAddress2(java.lang.String address2) {
        this.address2 = address2;
    }


    /**
     * Gets the ward value for this AddressType.
     * 
     * @return ward
     */
    public java.lang.String getWard() {
        return ward;
    }


    /**
     * Sets the ward value for this AddressType.
     * 
     * @param ward
     */
    public void setWard(java.lang.String ward) {
        this.ward = ward;
    }


    /**
     * Gets the city value for this AddressType.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this AddressType.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the country value for this AddressType.
     * 
     * @return country
     */
    public java.lang.String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this AddressType.
     * 
     * @param country
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }


    /**
     * Gets the email value for this AddressType.
     * 
     * @return email   * email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this AddressType.
     * 
     * @param email   * email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the mobileNum value for this AddressType.
     * 
     * @return mobileNum   * Mobile Number
     */
    public java.lang.String getMobileNum() {
        return mobileNum;
    }


    /**
     * Sets the mobileNum value for this AddressType.
     * 
     * @param mobileNum   * Mobile Number
     */
    public void setMobileNum(java.lang.String mobileNum) {
        this.mobileNum = mobileNum;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddressType)) return false;
        AddressType other = (AddressType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.address1==null && other.getAddress1()==null) || 
             (this.address1!=null &&
              this.address1.equals(other.getAddress1()))) &&
             ((this.address_vn==null && other.getAddress_vn()==null) || 
             (this.address_vn!=null &&
              this.address1.equals(other.getAddress1()))) &&
            ((this.address2==null && other.getAddress2()==null) || 
             (this.address2!=null &&
              this.address2.equals(other.getAddress2()))) &&
            ((this.ward==null && other.getWard()==null) || 
             (this.ward!=null &&
              this.ward.equals(other.getWard()))) &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.mobileNum==null && other.getMobileNum()==null) || 
             (this.mobileNum!=null &&
              this.mobileNum.equals(other.getMobileNum())));
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
        if (getAddress1() != null) {
            _hashCode += getAddress1().hashCode();
        }
         if (getAddress_vn() != null) {
            _hashCode += getAddress_vn().hashCode();
        }
        if (getAddress2() != null) {
            _hashCode += getAddress2().hashCode();
        }
        if (getWard() != null) {
            _hashCode += getWard().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getMobileNum() != null) {
            _hashCode += getMobileNum().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddressType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "AddressType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "address1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address_vn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "address_vn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "address2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ward");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ward"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("", "city"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mobileNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mobileNum"));
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

    /**
     * @return the address_vn
     */
    public java.lang.String getAddress_vn() {
        return address_vn;
    }

    /**
     * @param address_vn the address_vn to set
     */
    public void setAddress_vn(java.lang.String address_vn) {
        this.address_vn = address_vn;
    }

}
