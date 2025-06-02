/**
 * TransactionInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import com.google.gson.annotations.Expose;

public class TransactionInfoType  implements java.io.Serializable {
    /* Mã giao dịch tham chiếu */
  
    private java.lang.String coreRefNum;

    /* Mã giao dịch tham chiếu */
      @Expose
    private java.lang.String cRefNum;

    private java.lang.String pRefNum;

    /* Thời gian hoàn thành giao dịch */
    private java.lang.String transactionCompletedTime;

    /* Thời gian tính bằng giây tối đa cho dịch vụ, nếu
     *     					quá thời gian này xem như giao dịch timeout. Mặc
     *     					định truyền vào là 30. */
    private java.lang.String maxTimeout;

    /* ErrorCode String trả về của các hệ thống backend */
    private java.lang.String transactionErrorCode;

    /* ErrorMsg String trả về của các hệ thống backend */
    private java.lang.String transactionErrorMsg;

    /* true: mở thành công, false: mở tài khoản thất
     *     					bại */
    private java.lang.Integer transactionReturn;

    /* Nội dung trả về */
    private java.lang.String transactionReturnMsg;
    
    @Expose
    private vn.com.scb.bian.BranchInfoType branchInfo;

    /* Mã hệ thống gọi vào, tùy vào hệ thống gọi vào sẽ
     *     					output ra data khác nhau (output mapping). Lưu
     *     					ý, luồng xử lý truy vấn vào hệ thống cardWork sẽ
     *     					bị chậm. Chỉ query hệ thống cardwork khi có nhu
     *     					cầu từ kênh client tương ứng. */
    @Expose
    private java.lang.String clientCode;

    public TransactionInfoType() {
    }

    public TransactionInfoType(
           java.lang.String coreRefNum,
           java.lang.String cRefNum,
           java.lang.String pRefNum,
           java.lang.String transactionCompletedTime,
           java.lang.String maxTimeout,
           java.lang.String transactionErrorCode,
           java.lang.String transactionErrorMsg,
           java.lang.Integer transactionReturn,
           java.lang.String transactionReturnMsg,
           java.lang.String clientCode,
            vn.com.scb.bian.BranchInfoType branchInfo) {
           this.coreRefNum = coreRefNum;
           this.cRefNum = cRefNum;
           this.pRefNum = pRefNum;
           this.transactionCompletedTime = transactionCompletedTime;
           this.maxTimeout = maxTimeout;
           this.transactionErrorCode = transactionErrorCode;
           this.transactionErrorMsg = transactionErrorMsg;
           this.transactionReturn = transactionReturn;
           this.transactionReturnMsg = transactionReturnMsg;
           this.clientCode = clientCode;
           this.branchInfo = branchInfo;
    }


    /**
     * Gets the coreRefNum value for this TransactionInfoType.
     * 
     * @return coreRefNum   * Mã giao dịch tham chiếu
     */
    public java.lang.String getCoreRefNum() {
        return coreRefNum;
    }


    /**
     * Sets the coreRefNum value for this TransactionInfoType.
     * 
     * @param coreRefNum   * Mã giao dịch tham chiếu
     */
    public void setCoreRefNum(java.lang.String coreRefNum) {
        this.coreRefNum = coreRefNum;
    }


    /**
     * Gets the cRefNum value for this TransactionInfoType.
     * 
     * @return cRefNum   * Mã giao dịch tham chiếu
     */
    public java.lang.String getCRefNum() {
        return cRefNum;
    }


    /**
     * Sets the cRefNum value for this TransactionInfoType.
     * 
     * @param cRefNum   * Mã giao dịch tham chiếu
     */
    public void setCRefNum(java.lang.String cRefNum) {
        this.cRefNum = cRefNum;
    }


    /**
     * Gets the pRefNum value for this TransactionInfoType.
     * 
     * @return pRefNum
     */
    public java.lang.String getPRefNum() {
        return pRefNum;
    }


    /**
     * Sets the pRefNum value for this TransactionInfoType.
     * 
     * @param pRefNum
     */
    public void setPRefNum(java.lang.String pRefNum) {
        this.pRefNum = pRefNum;
    }


    /**
     * Gets the transactionCompletedTime value for this TransactionInfoType.
     * 
     * @return transactionCompletedTime   * Thời gian hoàn thành giao dịch
     */
    public java.lang.String getTransactionCompletedTime() {
        return transactionCompletedTime;
    }


    /**
     * Sets the transactionCompletedTime value for this TransactionInfoType.
     * 
     * @param transactionCompletedTime   * Thời gian hoàn thành giao dịch
     */
    public void setTransactionCompletedTime(java.lang.String transactionCompletedTime) {
        this.transactionCompletedTime = transactionCompletedTime;
    }


    /**
     * Gets the maxTimeout value for this TransactionInfoType.
     * 
     * @return maxTimeout   * Thời gian tính bằng giây tối đa cho dịch vụ, nếu
     *     					quá thời gian này xem như giao dịch timeout. Mặc
     *     					định truyền vào là 30.
     */
    public java.lang.String getMaxTimeout() {
        return maxTimeout;
    }


    /**
     * Sets the maxTimeout value for this TransactionInfoType.
     * 
     * @param maxTimeout   * Thời gian tính bằng giây tối đa cho dịch vụ, nếu
     *     					quá thời gian này xem như giao dịch timeout. Mặc
     *     					định truyền vào là 30.
     */
    public void setMaxTimeout(java.lang.String maxTimeout) {
        this.maxTimeout = maxTimeout;
    }


    /**
     * Gets the transactionErrorCode value for this TransactionInfoType.
     * 
     * @return transactionErrorCode   * ErrorCode String trả về của các hệ thống backend
     */
    public java.lang.String getTransactionErrorCode() {
        return transactionErrorCode;
    }


    /**
     * Sets the transactionErrorCode value for this TransactionInfoType.
     * 
     * @param transactionErrorCode   * ErrorCode String trả về của các hệ thống backend
     */
    public void setTransactionErrorCode(java.lang.String transactionErrorCode) {
        this.transactionErrorCode = transactionErrorCode;
    }


    /**
     * Gets the transactionErrorMsg value for this TransactionInfoType.
     * 
     * @return transactionErrorMsg   * ErrorMsg String trả về của các hệ thống backend
     */
    public java.lang.String getTransactionErrorMsg() {
        return transactionErrorMsg;
    }


    /**
     * Sets the transactionErrorMsg value for this TransactionInfoType.
     * 
     * @param transactionErrorMsg   * ErrorMsg String trả về của các hệ thống backend
     */
    public void setTransactionErrorMsg(java.lang.String transactionErrorMsg) {
        this.transactionErrorMsg = transactionErrorMsg;
    }


    /**
     * Gets the transactionReturn value for this TransactionInfoType.
     * 
     * @return transactionReturn   * true: mở thành công, false: mở tài khoản thất
     *     					bại
     */
    public java.lang.Integer getTransactionReturn() {
        return transactionReturn;
    }


    /**
     * Sets the transactionReturn value for this TransactionInfoType.
     * 
     * @param transactionReturn   * true: mở thành công, false: mở tài khoản thất
     *     					bại
     */
    public void setTransactionReturn(java.lang.Integer transactionReturn) {
        this.transactionReturn = transactionReturn;
    }


    /**
     * Gets the transactionReturnMsg value for this TransactionInfoType.
     * 
     * @return transactionReturnMsg   * Nội dung trả về
     */
    public java.lang.String getTransactionReturnMsg() {
        return transactionReturnMsg;
    }


    /**
     * Sets the transactionReturnMsg value for this TransactionInfoType.
     * 
     * @param transactionReturnMsg   * Nội dung trả về
     */
    public void setTransactionReturnMsg(java.lang.String transactionReturnMsg) {
        this.transactionReturnMsg = transactionReturnMsg;
    }


    /**
     * Gets the clientCode value for this TransactionInfoType.
     * 
     * @return clientCode   * Mã hệ thống gọi vào, tùy vào hệ thống gọi vào sẽ
     *     					output ra data khác nhau (output mapping). Lưu
     *     					ý, luồng xử lý truy vấn vào hệ thống cardWork sẽ
     *     					bị chậm. Chỉ query hệ thống cardwork khi có nhu
     *     					cầu từ kênh client tương ứng.
     */
    public java.lang.String getClientCode() {
        return clientCode;
    }


    /**
     * Sets the clientCode value for this TransactionInfoType.
     * 
     * @param clientCode   * Mã hệ thống gọi vào, tùy vào hệ thống gọi vào sẽ
     *     					output ra data khác nhau (output mapping). Lưu
     *     					ý, luồng xử lý truy vấn vào hệ thống cardWork sẽ
     *     					bị chậm. Chỉ query hệ thống cardwork khi có nhu
     *     					cầu từ kênh client tương ứng.
     */
    public void setClientCode(java.lang.String clientCode) {
        this.clientCode = clientCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TransactionInfoType)) return false;
        TransactionInfoType other = (TransactionInfoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.coreRefNum==null && other.getCoreRefNum()==null) || 
             (this.coreRefNum!=null &&
              this.coreRefNum.equals(other.getCoreRefNum()))) &&
            ((this.cRefNum==null && other.getCRefNum()==null) || 
             (this.cRefNum!=null &&
              this.cRefNum.equals(other.getCRefNum()))) &&
            ((this.pRefNum==null && other.getPRefNum()==null) || 
             (this.pRefNum!=null &&
              this.pRefNum.equals(other.getPRefNum()))) &&
            ((this.transactionCompletedTime==null && other.getTransactionCompletedTime()==null) || 
             (this.transactionCompletedTime!=null &&
              this.transactionCompletedTime.equals(other.getTransactionCompletedTime()))) &&
            ((this.maxTimeout==null && other.getMaxTimeout()==null) || 
             (this.maxTimeout!=null &&
              this.maxTimeout.equals(other.getMaxTimeout()))) &&
            ((this.transactionErrorCode==null && other.getTransactionErrorCode()==null) || 
             (this.transactionErrorCode!=null &&
              this.transactionErrorCode.equals(other.getTransactionErrorCode()))) &&
            ((this.transactionErrorMsg==null && other.getTransactionErrorMsg()==null) || 
             (this.transactionErrorMsg!=null &&
              this.transactionErrorMsg.equals(other.getTransactionErrorMsg()))) &&
            ((this.transactionReturn==null && other.getTransactionReturn()==null) || 
             (this.transactionReturn!=null &&
              this.transactionReturn.equals(other.getTransactionReturn()))) &&
            ((this.transactionReturnMsg==null && other.getTransactionReturnMsg()==null) || 
             (this.transactionReturnMsg!=null &&
              this.transactionReturnMsg.equals(other.getTransactionReturnMsg()))) &&
            ((this.clientCode==null && other.getClientCode()==null) || 
             (this.clientCode!=null &&
              this.clientCode.equals(other.getClientCode())));
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
        if (getCoreRefNum() != null) {
            _hashCode += getCoreRefNum().hashCode();
        }
        if (getCRefNum() != null) {
            _hashCode += getCRefNum().hashCode();
        }
        if (getPRefNum() != null) {
            _hashCode += getPRefNum().hashCode();
        }
        if (getTransactionCompletedTime() != null) {
            _hashCode += getTransactionCompletedTime().hashCode();
        }
        if (getMaxTimeout() != null) {
            _hashCode += getMaxTimeout().hashCode();
        }
        if (getTransactionErrorCode() != null) {
            _hashCode += getTransactionErrorCode().hashCode();
        }
        if (getTransactionErrorMsg() != null) {
            _hashCode += getTransactionErrorMsg().hashCode();
        }
        if (getTransactionReturn() != null) {
            _hashCode += getTransactionReturn().hashCode();
        }
        if (getTransactionReturnMsg() != null) {
            _hashCode += getTransactionReturnMsg().hashCode();
        }
        if (getClientCode() != null) {
            _hashCode += getClientCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TransactionInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "TransactionInfoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coreRefNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "coreRefNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CRefNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cRefNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRefNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pRefNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionCompletedTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionCompletedTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxTimeout");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxTimeout"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionErrorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionErrorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionErrorMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionErrorMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionReturnMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionReturnMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientCode"));
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
     * Gets the branchInfo value for this TransactionInfoType.
     * 
     * @return branchInfo
     */
    public vn.com.scb.bian.BranchInfoType getBranchInfo() {
        return branchInfo;
    }


    /**
     * Sets the branchInfo value for this TransactionInfoType.
     * 
     * @param branchInfo
     */
    public void setBranchInfo(vn.com.scb.bian.BranchInfoType branchInfo) {
        this.branchInfo = branchInfo;
    }


}
