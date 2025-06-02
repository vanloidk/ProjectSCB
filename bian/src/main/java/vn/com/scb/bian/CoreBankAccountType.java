/**
 * CoreBankAccountType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class CoreBankAccountType  implements java.io.Serializable {
    /* User đăng nhập vào core bank, nếu là mobile
     *     					banking thì được cung cấp sẵn 1 account */
    private java.lang.String makerAccount;

    private java.lang.String checkerAccount;

    private vn.com.scb.bian.BranchInfoType branchInfo;

    /* Tên người dùng. */
    private java.lang.String userFullName;

    /* User corebank, có thể là maker hoặc checker */
    private java.lang.String userAccount;

    /* Loại người dùng trong core bank, maker hoặc
     *     					checker */
    private java.lang.String userType;

    /* Dùng cho các giao dịch PaymentExe, PaymentInt, initateTDAccount */
    private java.lang.String sourceHeader;

    public CoreBankAccountType() {
    }

    public CoreBankAccountType(
           java.lang.String makerAccount,
           java.lang.String checkerAccount,
           vn.com.scb.bian.BranchInfoType branchInfo,
           java.lang.String userFullName,
           java.lang.String userAccount,
           java.lang.String userType,
           java.lang.String sourceHeader) {
           this.makerAccount = makerAccount;
           this.checkerAccount = checkerAccount;
           this.branchInfo = branchInfo;
           this.userFullName = userFullName;
           this.userAccount = userAccount;
           this.userType = userType;
           this.sourceHeader = sourceHeader;
    }


    /**
     * Gets the makerAccount value for this CoreBankAccountType.
     * 
     * @return makerAccount   * User đăng nhập vào core bank, nếu là mobile
     *     					banking thì được cung cấp sẵn 1 account
     */
    public java.lang.String getMakerAccount() {
        return makerAccount;
    }


    /**
     * Sets the makerAccount value for this CoreBankAccountType.
     * 
     * @param makerAccount   * User đăng nhập vào core bank, nếu là mobile
     *     					banking thì được cung cấp sẵn 1 account
     */
    public void setMakerAccount(java.lang.String makerAccount) {
        this.makerAccount = makerAccount;
    }


    /**
     * Gets the checkerAccount value for this CoreBankAccountType.
     * 
     * @return checkerAccount
     */
    public java.lang.String getCheckerAccount() {
        return checkerAccount;
    }


    /**
     * Sets the checkerAccount value for this CoreBankAccountType.
     * 
     * @param checkerAccount
     */
    public void setCheckerAccount(java.lang.String checkerAccount) {
        this.checkerAccount = checkerAccount;
    }


    /**
     * Gets the branchInfo value for this CoreBankAccountType.
     * 
     * @return branchInfo
     */
    public vn.com.scb.bian.BranchInfoType getBranchInfo() {
        return branchInfo;
    }


    /**
     * Sets the branchInfo value for this CoreBankAccountType.
     * 
     * @param branchInfo
     */
    public void setBranchInfo(vn.com.scb.bian.BranchInfoType branchInfo) {
        this.branchInfo = branchInfo;
    }


    /**
     * Gets the userFullName value for this CoreBankAccountType.
     * 
     * @return userFullName   * Tên người dùng.
     */
    public java.lang.String getUserFullName() {
        return userFullName;
    }


    /**
     * Sets the userFullName value for this CoreBankAccountType.
     * 
     * @param userFullName   * Tên người dùng.
     */
    public void setUserFullName(java.lang.String userFullName) {
        this.userFullName = userFullName;
    }


    /**
     * Gets the userAccount value for this CoreBankAccountType.
     * 
     * @return userAccount   * User corebank, có thể là maker hoặc checker
     */
    public java.lang.String getUserAccount() {
        return userAccount;
    }


    /**
     * Sets the userAccount value for this CoreBankAccountType.
     * 
     * @param userAccount   * User corebank, có thể là maker hoặc checker
     */
    public void setUserAccount(java.lang.String userAccount) {
        this.userAccount = userAccount;
    }


    /**
     * Gets the userType value for this CoreBankAccountType.
     * 
     * @return userType   * Loại người dùng trong core bank, maker hoặc
     *     					checker
     */
    public java.lang.String getUserType() {
        return userType;
    }


    /**
     * Sets the userType value for this CoreBankAccountType.
     * 
     * @param userType   * Loại người dùng trong core bank, maker hoặc
     *     					checker
     */
    public void setUserType(java.lang.String userType) {
        this.userType = userType;
    }


    /**
     * Gets the sourceHeader value for this CoreBankAccountType.
     * 
     * @return sourceHeader   * Dùng cho các giao dịch PaymentExe, PaymentInt, initateTDAccount
     */
    public java.lang.String getSourceHeader() {
        return sourceHeader;
    }


    /**
     * Sets the sourceHeader value for this CoreBankAccountType.
     * 
     * @param sourceHeader   * Dùng cho các giao dịch PaymentExe, PaymentInt, initateTDAccount
     */
    public void setSourceHeader(java.lang.String sourceHeader) {
        this.sourceHeader = sourceHeader;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CoreBankAccountType)) return false;
        CoreBankAccountType other = (CoreBankAccountType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.makerAccount==null && other.getMakerAccount()==null) || 
             (this.makerAccount!=null &&
              this.makerAccount.equals(other.getMakerAccount()))) &&
            ((this.checkerAccount==null && other.getCheckerAccount()==null) || 
             (this.checkerAccount!=null &&
              this.checkerAccount.equals(other.getCheckerAccount()))) &&
            ((this.branchInfo==null && other.getBranchInfo()==null) || 
             (this.branchInfo!=null &&
              this.branchInfo.equals(other.getBranchInfo()))) &&
            ((this.userFullName==null && other.getUserFullName()==null) || 
             (this.userFullName!=null &&
              this.userFullName.equals(other.getUserFullName()))) &&
            ((this.userAccount==null && other.getUserAccount()==null) || 
             (this.userAccount!=null &&
              this.userAccount.equals(other.getUserAccount()))) &&
            ((this.userType==null && other.getUserType()==null) || 
             (this.userType!=null &&
              this.userType.equals(other.getUserType()))) &&
            ((this.sourceHeader==null && other.getSourceHeader()==null) || 
             (this.sourceHeader!=null &&
              this.sourceHeader.equals(other.getSourceHeader())));
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
        if (getMakerAccount() != null) {
            _hashCode += getMakerAccount().hashCode();
        }
        if (getCheckerAccount() != null) {
            _hashCode += getCheckerAccount().hashCode();
        }
        if (getBranchInfo() != null) {
            _hashCode += getBranchInfo().hashCode();
        }
        if (getUserFullName() != null) {
            _hashCode += getUserFullName().hashCode();
        }
        if (getUserAccount() != null) {
            _hashCode += getUserAccount().hashCode();
        }
        if (getUserType() != null) {
            _hashCode += getUserType().hashCode();
        }
        if (getSourceHeader() != null) {
            _hashCode += getSourceHeader().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CoreBankAccountType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CoreBankAccountType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("makerAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "makerAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkerAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "checkerAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("branchInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "branchInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "BranchInfoType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userFullName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userFullName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceHeader"));
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
