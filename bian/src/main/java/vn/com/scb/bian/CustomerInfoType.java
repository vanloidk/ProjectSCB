/**
 * CustomerInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import com.google.gson.annotations.Expose;

public class CustomerInfoType  implements java.io.Serializable {
    /* Họ và tên */
     @Expose
    private java.lang.String fullname;
     
     /* Họ và tên vn */
     @Expose
    private java.lang.String fullname_vn;

    /* Ngày sinh */
    private java.lang.String birthDay;

    /* Sex - Giới tính */
    private java.lang.String gender;

    /* Loai VIP khách hàng */
    private java.lang.String customerVIPType;

    /* manageStaffID */
    private java.lang.String manageStaffID;

    private vn.com.scb.bian.JobInfoType jobInfo;

    private vn.com.scb.bian.IDInfoType IDInfo;

    private vn.com.scb.bian.AddressType address;
    
    private java.lang.String isStaff    ;
    
    private java.lang.String segmentType;

    public CustomerInfoType() {
    }

    public CustomerInfoType(
           java.lang.String fullname,
            java.lang.String fullname_vn,
           java.lang.String birthDay,
           java.lang.String gender,
           java.lang.String customerVIPType,
           java.lang.String manageStaffID,
           vn.com.scb.bian.JobInfoType jobInfo,
           vn.com.scb.bian.IDInfoType IDInfo,
           vn.com.scb.bian.AddressType address,
            java.lang.String isStaff,
           java.lang.String segmentType) {
           this.fullname = fullname;
           this.fullname_vn = fullname_vn;
           this.birthDay = birthDay;
           this.gender = gender;
           this.customerVIPType = customerVIPType;
           this.manageStaffID = manageStaffID;
           this.jobInfo = jobInfo;
           this.IDInfo = IDInfo;
           this.address = address;
           this.isStaff = isStaff;
           this.segmentType = segmentType;
    }


    /**
     * Gets the fullname value for this CustomerInfoType.
     * 
     * @return fullname   * Họ và tên
     */
    public java.lang.String getFullname() {
        return fullname;
    }


    /**
     * Sets the fullname value for this CustomerInfoType.
     * 
     * @param fullname   * Họ và tên
     */
    public void setFullname(java.lang.String fullname) {
        this.fullname = fullname;
    }


    /**
     * Gets the birthDay value for this CustomerInfoType.
     * 
     * @return birthDay   * Ngày sinh
     */
    public java.lang.String getBirthDay() {
        return birthDay;
    }


    /**
     * Sets the birthDay value for this CustomerInfoType.
     * 
     * @param birthDay   * Ngày sinh
     */
    public void setBirthDay(java.lang.String birthDay) {
        this.birthDay = birthDay;
    }


    /**
     * Gets the gender value for this CustomerInfoType.
     * 
     * @return gender   * Sex - Giới tính
     */
    public java.lang.String getGender() {
        return gender;
    }


    /**
     * Sets the gender value for this CustomerInfoType.
     * 
     * @param gender   * Sex - Giới tính
     */
    public void setGender(java.lang.String gender) {
        this.gender = gender;
    }


    /**
     * Gets the customerVIPType value for this CustomerInfoType.
     * 
     * @return customerVIPType   * Loai VIP khách hàng
     */
    public java.lang.String getCustomerVIPType() {
        return customerVIPType;
    }


    /**
     * Sets the customerVIPType value for this CustomerInfoType.
     * 
     * @param customerVIPType   * Loai VIP khách hàng
     */
    public void setCustomerVIPType(java.lang.String customerVIPType) {
        this.customerVIPType = customerVIPType;
    }


    /**
     * Gets the manageStaffID value for this CustomerInfoType.
     * 
     * @return manageStaffID   * manageStaffID
     */
    public java.lang.String getManageStaffID() {
        return manageStaffID;
    }


    /**
     * Sets the manageStaffID value for this CustomerInfoType.
     * 
     * @param manageStaffID   * manageStaffID
     */
    public void setManageStaffID(java.lang.String manageStaffID) {
        this.manageStaffID = manageStaffID;
    }


    /**
     * Gets the jobInfo value for this CustomerInfoType.
     * 
     * @return jobInfo
     */
    public vn.com.scb.bian.JobInfoType getJobInfo() {
        return jobInfo;
    }


    /**
     * Sets the jobInfo value for this CustomerInfoType.
     * 
     * @param jobInfo
     */
    public void setJobInfo(vn.com.scb.bian.JobInfoType jobInfo) {
        this.jobInfo = jobInfo;
    }


    /**
     * Gets the IDInfo value for this CustomerInfoType.
     * 
     * @return IDInfo
     */
    public vn.com.scb.bian.IDInfoType getIDInfo() {
        return IDInfo;
    }


    /**
     * Sets the IDInfo value for this CustomerInfoType.
     * 
     * @param IDInfo
     */
    public void setIDInfo(vn.com.scb.bian.IDInfoType IDInfo) {
        this.IDInfo = IDInfo;
    }


    /**
     * Gets the address value for this CustomerInfoType.
     * 
     * @return address
     */
    public vn.com.scb.bian.AddressType getAddress() {
        return address;
    }


    /**
     * Sets the address value for this CustomerInfoType.
     * 
     * @param address
     */
    public void setAddress(vn.com.scb.bian.AddressType address) {
        this.address = address;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CustomerInfoType)) return false;
        CustomerInfoType other = (CustomerInfoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fullname==null && other.getFullname()==null) || 
             (this.fullname!=null &&
              this.fullname.equals(other.getFullname()))) &&
             ((this.fullname_vn==null && other.getFullname_vn()==null) || 
             (this.fullname_vn!=null &&
              this.fullname_vn.equals(other.getFullname_vn()))) &&
            ((this.birthDay==null && other.getBirthDay()==null) || 
             (this.birthDay!=null &&
              this.birthDay.equals(other.getBirthDay()))) &&
            ((this.gender==null && other.getGender()==null) || 
             (this.gender!=null &&
              this.gender.equals(other.getGender()))) &&
            ((this.customerVIPType==null && other.getCustomerVIPType()==null) || 
             (this.customerVIPType!=null &&
              this.customerVIPType.equals(other.getCustomerVIPType()))) &&
            ((this.manageStaffID==null && other.getManageStaffID()==null) || 
             (this.manageStaffID!=null &&
              this.manageStaffID.equals(other.getManageStaffID()))) &&
            ((this.jobInfo==null && other.getJobInfo()==null) || 
             (this.jobInfo!=null &&
              this.jobInfo.equals(other.getJobInfo()))) &&
            ((this.IDInfo==null && other.getIDInfo()==null) || 
             (this.IDInfo!=null &&
              this.IDInfo.equals(other.getIDInfo()))) &&
                ((this.isStaff==null && other.getIsStaff()==null) || 
             (this.isStaff!=null &&
              this.isStaff.equals(other.getIsStaff()))) &&
                ((this.segmentType==null && other.getSegmentType()==null) || 
             (this.segmentType!=null &&
              this.segmentType.equals(other.getSegmentType()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress())));
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
        if (getFullname() != null) {
            _hashCode += getFullname().hashCode();
        }
         if (getFullname_vn()!= null) {
            _hashCode += getFullname_vn().hashCode();
        }
        if (getBirthDay() != null) {
            _hashCode += getBirthDay().hashCode();
        }
        if (getGender() != null) {
            _hashCode += getGender().hashCode();
        }
        if (getCustomerVIPType() != null) {
            _hashCode += getCustomerVIPType().hashCode();
        }
        if (getManageStaffID() != null) {
            _hashCode += getManageStaffID().hashCode();
        }
        if (getJobInfo() != null) {
            _hashCode += getJobInfo().hashCode();
        }
        if (getIDInfo() != null) {
            _hashCode += getIDInfo().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CustomerInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CustomerInfoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fullname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);    
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullname_vn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fullname_vn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthDay");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthDay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerVIPType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customerVIPType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manageStaffID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manageStaffID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jobInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "JobInfoType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IDInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "IDInfoType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "address"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "AddressType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
          elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isStaff");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isStaff"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
           elemField.setFieldName("segmentType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "segmentType"));
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
     * @return the fullname_vn
     */
    public java.lang.String getFullname_vn() {
        return fullname_vn;
    }

    /**
     * @param fullname_vn the fullname_vn to set
     */
    public void setFullname_vn(java.lang.String fullname_vn) {
        this.fullname_vn = fullname_vn;
    }

    /**
     * @return the isStaff
     */
    public java.lang.String getIsStaff() {
        return isStaff;
    }

    /**
     * @param isStaff the isStaff to set
     */
    public void setIsStaff(java.lang.String isStaff) {
        this.isStaff = isStaff;
    }

    /**
     * @return the segmentType
     */
    public java.lang.String getSegmentType() {
        return segmentType;
    }

    /**
     * @param segmentType the segmentType to set
     */
    public void setSegmentType(java.lang.String segmentType) {
        this.segmentType = segmentType;
    }

}
