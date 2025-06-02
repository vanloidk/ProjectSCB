/**
 * BenificialInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class BenificialInfoType  implements java.io.Serializable {
    /* Loại tài khoản người hưởng. (1: Tài khoản chính mở tại SCB,
     * 2: tài khoản khác thuộc hệ thống SCB, 3: Người hưởng nhận tiền mặt
     * tại SCB, 4: Người hưởng tại NH khác) */
    private java.lang.String benificialType;

    /* Tên người hưởng. Tài khoản ngoài ngân hàng */
    private java.lang.String benifitCustomerName;

    /* Địa chỉ người thụ hưởng */
    private java.lang.String benifitCustomerAddress;

    /* Số điện thoại người hưởng */
    private java.lang.String benifitCustomerTEL;

    /* Mã thành phố của chi nhánh nhận chuyển khoản */
    private java.lang.String benefitCityCode;

    /* Chi nhánh nhận chuyển khoản */
    private java.lang.String benefitBranchName;

    /* Mã chi nhánh nơi nhận */
    private java.lang.String benefitBranchCode;

    private vn.com.scb.bian.IDInfoType IDInfo;

    /* Số tài khoản người thụ hưởng */
    private java.lang.String benifitAccountNum;

    private java.lang.String benefitCitadCode;

    /* Mã ngân hàng thụ hưởng */
    private java.lang.String benefitBankCode;

    private java.lang.String benefitBankName;

    public BenificialInfoType() {
    }

    public BenificialInfoType(
           java.lang.String benificialType,
           java.lang.String benifitCustomerName,
           java.lang.String benifitCustomerAddress,
           java.lang.String benifitCustomerTEL,
           java.lang.String benefitCityCode,
           java.lang.String benefitBranchName,
           java.lang.String benefitBranchCode,
           vn.com.scb.bian.IDInfoType IDInfo,
           java.lang.String benifitAccountNum,
           java.lang.String benefitCitadCode,
           java.lang.String benefitBankCode,
           java.lang.String benefitBankName) {
           this.benificialType = benificialType;
           this.benifitCustomerName = benifitCustomerName;
           this.benifitCustomerAddress = benifitCustomerAddress;
           this.benifitCustomerTEL = benifitCustomerTEL;
           this.benefitCityCode = benefitCityCode;
           this.benefitBranchName = benefitBranchName;
           this.benefitBranchCode = benefitBranchCode;
           this.IDInfo = IDInfo;
           this.benifitAccountNum = benifitAccountNum;
           this.benefitCitadCode = benefitCitadCode;
           this.benefitBankCode = benefitBankCode;
           this.benefitBankName = benefitBankName;
    }


    /**
     * Gets the benificialType value for this BenificialInfoType.
     * 
     * @return benificialType   * Loại tài khoản người hưởng. (1: Tài khoản chính mở tại SCB,
     * 2: tài khoản khác thuộc hệ thống SCB, 3: Người hưởng nhận tiền mặt
     * tại SCB, 4: Người hưởng tại NH khác)
     */
    public java.lang.String getBenificialType() {
        return benificialType;
    }


    /**
     * Sets the benificialType value for this BenificialInfoType.
     * 
     * @param benificialType   * Loại tài khoản người hưởng. (1: Tài khoản chính mở tại SCB,
     * 2: tài khoản khác thuộc hệ thống SCB, 3: Người hưởng nhận tiền mặt
     * tại SCB, 4: Người hưởng tại NH khác)
     */
    public void setBenificialType(java.lang.String benificialType) {
        this.benificialType = benificialType;
    }


    /**
     * Gets the benifitCustomerName value for this BenificialInfoType.
     * 
     * @return benifitCustomerName   * Tên người hưởng. Tài khoản ngoài ngân hàng
     */
    public java.lang.String getBenifitCustomerName() {
        return benifitCustomerName;
    }


    /**
     * Sets the benifitCustomerName value for this BenificialInfoType.
     * 
     * @param benifitCustomerName   * Tên người hưởng. Tài khoản ngoài ngân hàng
     */
    public void setBenifitCustomerName(java.lang.String benifitCustomerName) {
        this.benifitCustomerName = benifitCustomerName;
    }


    /**
     * Gets the benifitCustomerAddress value for this BenificialInfoType.
     * 
     * @return benifitCustomerAddress   * Địa chỉ người thụ hưởng
     */
    public java.lang.String getBenifitCustomerAddress() {
        return benifitCustomerAddress;
    }


    /**
     * Sets the benifitCustomerAddress value for this BenificialInfoType.
     * 
     * @param benifitCustomerAddress   * Địa chỉ người thụ hưởng
     */
    public void setBenifitCustomerAddress(java.lang.String benifitCustomerAddress) {
        this.benifitCustomerAddress = benifitCustomerAddress;
    }


    /**
     * Gets the benifitCustomerTEL value for this BenificialInfoType.
     * 
     * @return benifitCustomerTEL   * Số điện thoại người hưởng
     */
    public java.lang.String getBenifitCustomerTEL() {
        return benifitCustomerTEL;
    }


    /**
     * Sets the benifitCustomerTEL value for this BenificialInfoType.
     * 
     * @param benifitCustomerTEL   * Số điện thoại người hưởng
     */
    public void setBenifitCustomerTEL(java.lang.String benifitCustomerTEL) {
        this.benifitCustomerTEL = benifitCustomerTEL;
    }


    /**
     * Gets the benefitCityCode value for this BenificialInfoType.
     * 
     * @return benefitCityCode   * Mã thành phố của chi nhánh nhận chuyển khoản
     */
    public java.lang.String getBenefitCityCode() {
        return benefitCityCode;
    }


    /**
     * Sets the benefitCityCode value for this BenificialInfoType.
     * 
     * @param benefitCityCode   * Mã thành phố của chi nhánh nhận chuyển khoản
     */
    public void setBenefitCityCode(java.lang.String benefitCityCode) {
        this.benefitCityCode = benefitCityCode;
    }


    /**
     * Gets the benefitBranchName value for this BenificialInfoType.
     * 
     * @return benefitBranchName   * Chi nhánh nhận chuyển khoản
     */
    public java.lang.String getBenefitBranchName() {
        return benefitBranchName;
    }


    /**
     * Sets the benefitBranchName value for this BenificialInfoType.
     * 
     * @param benefitBranchName   * Chi nhánh nhận chuyển khoản
     */
    public void setBenefitBranchName(java.lang.String benefitBranchName) {
        this.benefitBranchName = benefitBranchName;
    }


    /**
     * Gets the benefitBranchCode value for this BenificialInfoType.
     * 
     * @return benefitBranchCode   * Mã chi nhánh nơi nhận
     */
    public java.lang.String getBenefitBranchCode() {
        return benefitBranchCode;
    }


    /**
     * Sets the benefitBranchCode value for this BenificialInfoType.
     * 
     * @param benefitBranchCode   * Mã chi nhánh nơi nhận
     */
    public void setBenefitBranchCode(java.lang.String benefitBranchCode) {
        this.benefitBranchCode = benefitBranchCode;
    }


    /**
     * Gets the IDInfo value for this BenificialInfoType.
     * 
     * @return IDInfo
     */
    public vn.com.scb.bian.IDInfoType getIDInfo() {
        return IDInfo;
    }


    /**
     * Sets the IDInfo value for this BenificialInfoType.
     * 
     * @param IDInfo
     */
    public void setIDInfo(vn.com.scb.bian.IDInfoType IDInfo) {
        this.IDInfo = IDInfo;
    }


    /**
     * Gets the benifitAccountNum value for this BenificialInfoType.
     * 
     * @return benifitAccountNum   * Số tài khoản người thụ hưởng
     */
    public java.lang.String getBenifitAccountNum() {
        return benifitAccountNum;
    }


    /**
     * Sets the benifitAccountNum value for this BenificialInfoType.
     * 
     * @param benifitAccountNum   * Số tài khoản người thụ hưởng
     */
    public void setBenifitAccountNum(java.lang.String benifitAccountNum) {
        this.benifitAccountNum = benifitAccountNum;
    }


    /**
     * Gets the benefitCitadCode value for this BenificialInfoType.
     * 
     * @return benefitCitadCode
     */
    public java.lang.String getBenefitCitadCode() {
        return benefitCitadCode;
    }


    /**
     * Sets the benefitCitadCode value for this BenificialInfoType.
     * 
     * @param benefitCitadCode
     */
    public void setBenefitCitadCode(java.lang.String benefitCitadCode) {
        this.benefitCitadCode = benefitCitadCode;
    }


    /**
     * Gets the benefitBankCode value for this BenificialInfoType.
     * 
     * @return benefitBankCode   * Mã ngân hàng thụ hưởng
     */
    public java.lang.String getBenefitBankCode() {
        return benefitBankCode;
    }


    /**
     * Sets the benefitBankCode value for this BenificialInfoType.
     * 
     * @param benefitBankCode   * Mã ngân hàng thụ hưởng
     */
    public void setBenefitBankCode(java.lang.String benefitBankCode) {
        this.benefitBankCode = benefitBankCode;
    }


    /**
     * Gets the benefitBankName value for this BenificialInfoType.
     * 
     * @return benefitBankName
     */
    public java.lang.String getBenefitBankName() {
        return benefitBankName;
    }


    /**
     * Sets the benefitBankName value for this BenificialInfoType.
     * 
     * @param benefitBankName
     */
    public void setBenefitBankName(java.lang.String benefitBankName) {
        this.benefitBankName = benefitBankName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BenificialInfoType)) return false;
        BenificialInfoType other = (BenificialInfoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.benificialType==null && other.getBenificialType()==null) || 
             (this.benificialType!=null &&
              this.benificialType.equals(other.getBenificialType()))) &&
            ((this.benifitCustomerName==null && other.getBenifitCustomerName()==null) || 
             (this.benifitCustomerName!=null &&
              this.benifitCustomerName.equals(other.getBenifitCustomerName()))) &&
            ((this.benifitCustomerAddress==null && other.getBenifitCustomerAddress()==null) || 
             (this.benifitCustomerAddress!=null &&
              this.benifitCustomerAddress.equals(other.getBenifitCustomerAddress()))) &&
            ((this.benifitCustomerTEL==null && other.getBenifitCustomerTEL()==null) || 
             (this.benifitCustomerTEL!=null &&
              this.benifitCustomerTEL.equals(other.getBenifitCustomerTEL()))) &&
            ((this.benefitCityCode==null && other.getBenefitCityCode()==null) || 
             (this.benefitCityCode!=null &&
              this.benefitCityCode.equals(other.getBenefitCityCode()))) &&
            ((this.benefitBranchName==null && other.getBenefitBranchName()==null) || 
             (this.benefitBranchName!=null &&
              this.benefitBranchName.equals(other.getBenefitBranchName()))) &&
            ((this.benefitBranchCode==null && other.getBenefitBranchCode()==null) || 
             (this.benefitBranchCode!=null &&
              this.benefitBranchCode.equals(other.getBenefitBranchCode()))) &&
            ((this.IDInfo==null && other.getIDInfo()==null) || 
             (this.IDInfo!=null &&
              this.IDInfo.equals(other.getIDInfo()))) &&
            ((this.benifitAccountNum==null && other.getBenifitAccountNum()==null) || 
             (this.benifitAccountNum!=null &&
              this.benifitAccountNum.equals(other.getBenifitAccountNum()))) &&
            ((this.benefitCitadCode==null && other.getBenefitCitadCode()==null) || 
             (this.benefitCitadCode!=null &&
              this.benefitCitadCode.equals(other.getBenefitCitadCode()))) &&
            ((this.benefitBankCode==null && other.getBenefitBankCode()==null) || 
             (this.benefitBankCode!=null &&
              this.benefitBankCode.equals(other.getBenefitBankCode()))) &&
            ((this.benefitBankName==null && other.getBenefitBankName()==null) || 
             (this.benefitBankName!=null &&
              this.benefitBankName.equals(other.getBenefitBankName())));
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
        if (getBenificialType() != null) {
            _hashCode += getBenificialType().hashCode();
        }
        if (getBenifitCustomerName() != null) {
            _hashCode += getBenifitCustomerName().hashCode();
        }
        if (getBenifitCustomerAddress() != null) {
            _hashCode += getBenifitCustomerAddress().hashCode();
        }
        if (getBenifitCustomerTEL() != null) {
            _hashCode += getBenifitCustomerTEL().hashCode();
        }
        if (getBenefitCityCode() != null) {
            _hashCode += getBenefitCityCode().hashCode();
        }
        if (getBenefitBranchName() != null) {
            _hashCode += getBenefitBranchName().hashCode();
        }
        if (getBenefitBranchCode() != null) {
            _hashCode += getBenefitBranchCode().hashCode();
        }
        if (getIDInfo() != null) {
            _hashCode += getIDInfo().hashCode();
        }
        if (getBenifitAccountNum() != null) {
            _hashCode += getBenifitAccountNum().hashCode();
        }
        if (getBenefitCitadCode() != null) {
            _hashCode += getBenefitCitadCode().hashCode();
        }
        if (getBenefitBankCode() != null) {
            _hashCode += getBenefitBankCode().hashCode();
        }
        if (getBenefitBankName() != null) {
            _hashCode += getBenefitBankName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BenificialInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "BenificialInfoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("benificialType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "benificialType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("benifitCustomerName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "benifitCustomerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("benifitCustomerAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "benifitCustomerAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("benifitCustomerTEL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "benifitCustomerTEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("benefitCityCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "benefitCityCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("benefitBranchName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "benefitBranchName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("benefitBranchCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "benefitBranchCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("benifitAccountNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "benifitAccountNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("benefitCitadCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "benefitCitadCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("benefitBankCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "benefitBankCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("benefitBankName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "benefitBankName"));
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
