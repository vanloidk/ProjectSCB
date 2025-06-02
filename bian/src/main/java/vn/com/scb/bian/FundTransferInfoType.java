/**
 * FundTransferInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import java.math.BigDecimal;

public class FundTransferInfoType  implements java.io.Serializable {
    /* Loại giao dịch chuyển khoản. (String) */
    private java.lang.String fundTransferTransactionType;

    /* Tên sản phẩm chuyển khoản */
    private java.lang.String fundTransferProductCode;

    /* Số tiền chuyển khoản */
    private java.lang.Double fundTransferAmount;
    
    /* Số tiền chuyển khoản da convert ve VND */
    private java.math.BigDecimal fundTransferAmountStr;

    /* Phí chuyển khoản */
    private java.lang.Integer fundTransferFeeAmount;

    /* Diễn giải thu phí */
    private java.lang.String fundTransferFeeChgComp;

    /* Thuế dịch vụ chuyển khoản */
    private java.lang.Integer fundTransferVATAmount;

    /* Diễn giải thu VAT */
    private java.lang.String fundTransferVATChgComp;
    
    /* Thu phi tai chinh */
    private java.lang.Integer fundTransferFINAmount;

    /* Diễn giải thu phi tai chinh */
    private java.lang.String fundTransferFINChgComp;
    
     /* Thu phi tai chinh */
    private java.lang.Integer fundTransferFINVATAmount;

    /* Diễn giải thu phi tai chinh */
    private java.lang.String fundTransferFINVATChgComp;

    /* Ghi chú nội dung chuyển khoản */
    private java.lang.String fundTransferNarative;

    /* Ngày thực hiện chuyển khoản (DD/MM/YYYY). Nếu
     *     					không truyền thì xem như ngày hiện tại. Trường
     *     					này optional cho client */
    private java.lang.String fundTransferActiveDate;

    /* Bằng tổng của (fundTransferAmount + Fee + VAT) */
    private java.lang.Integer fundTransferActualAmount;

    /* Thông tin thêm bất kỳ mà SCB muốn đưa vào */
    private java.lang.String fundTransferComment;

    private java.lang.String fundTransferFeeType;

    /* Phân biệt người trả phí khi thực hiện giao dịch
     *     					- 1: Phí người chuyển trả; 2: phí người nhận trả */
    private java.lang.String fundTransferFeePayee;

    private java.lang.String fundTransferServiceType;

    private java.lang.String fundTransferChanelId;

    /* Phân biệt RT (= 0 hoặc null) hay FT (=1) */
    private java.lang.String fundTransferFT;
    
    private ExchangeRateInfoType exchangeRateInfo;

    public FundTransferInfoType() {}

    public FundTransferInfoType(
           java.lang.String fundTransferTransactionType,
           java.lang.String fundTransferProductCode,
           java.lang.Double fundTransferAmount,
           java.lang.Integer fundTransferFeeAmount,
           java.lang.String fundTransferFeeChgComp,
           java.lang.Integer fundTransferVATAmount,
           java.lang.String fundTransferVATChgComp,
           java.lang.Integer fundTransferFINAmount,
           java.lang.String fundTransferFINChgComp,
           java.lang.Integer fundTransferFINVATAmount,
           java.lang.String fundTransferFINVATChgComp,
           java.lang.String fundTransferNarative,
           java.lang.String fundTransferActiveDate,
           java.lang.Integer fundTransferActualAmount,
           java.lang.String fundTransferComment,
           java.lang.String fundTransferFeeType,
           java.lang.String fundTransferFeePayee,
           java.lang.String fundTransferServiceType,
           java.lang.String fundTransferChanelId,
           java.lang.String fundTransferFT) {
           this.fundTransferTransactionType = fundTransferTransactionType;
           this.fundTransferProductCode = fundTransferProductCode;
           this.fundTransferAmount = fundTransferAmount;
           this.fundTransferFeeAmount = fundTransferFeeAmount;
           this.fundTransferFeeChgComp = fundTransferFeeChgComp;
           this.fundTransferVATAmount = fundTransferVATAmount;
           this.fundTransferVATChgComp = fundTransferVATChgComp;
           
           this.fundTransferFINAmount = fundTransferFINAmount;
           this.fundTransferFINChgComp = fundTransferFINChgComp;
           this.fundTransferFINVATAmount = fundTransferFINVATAmount;
           this.fundTransferFINVATChgComp = fundTransferFINVATChgComp;
           
           this.fundTransferNarative = fundTransferNarative;
           this.fundTransferActiveDate = fundTransferActiveDate;
           this.fundTransferActualAmount = fundTransferActualAmount;
           this.fundTransferComment = fundTransferComment;
           this.fundTransferFeeType = fundTransferFeeType;
           this.fundTransferFeePayee = fundTransferFeePayee;
           this.fundTransferServiceType = fundTransferServiceType;
           this.fundTransferChanelId = fundTransferChanelId;
           this.fundTransferFT = fundTransferFT;
    }


    /**
     * Gets the fundTransferTransactionType value for this FundTransferInfoType.
     * 
     * @return fundTransferTransactionType   * Loại giao dịch chuyển khoản. (String)
     */
    public java.lang.String getFundTransferTransactionType() {
        return fundTransferTransactionType;
    }


    /**
     * Sets the fundTransferTransactionType value for this FundTransferInfoType.
     * 
     * @param fundTransferTransactionType   * Loại giao dịch chuyển khoản. (String)
     */
    public void setFundTransferTransactionType(java.lang.String fundTransferTransactionType) {
        this.fundTransferTransactionType = fundTransferTransactionType;
    }


    /**
     * Gets the fundTransferProductCode value for this FundTransferInfoType.
     * 
     * @return fundTransferProductCode   * Tên sản phẩm chuyển khoản
     */
    public java.lang.String getFundTransferProductCode() {
        return fundTransferProductCode;
    }


    /**
     * Sets the fundTransferProductCode value for this FundTransferInfoType.
     * 
     * @param fundTransferProductCode   * Tên sản phẩm chuyển khoản
     */
    public void setFundTransferProductCode(java.lang.String fundTransferProductCode) {
        this.fundTransferProductCode = fundTransferProductCode;
    }


    /**
     * Gets the fundTransferAmount value for this FundTransferInfoType.
     * 
     * @return fundTransferAmount   * Số tiền chuyển khoản
     */
    public java.lang.Double getFundTransferAmount() {
        return fundTransferAmount;
    }


    /**
     * Sets the fundTransferAmount value for this FundTransferInfoType.
     * 
     * @param fundTransferAmount   * Số tiền chuyển khoản
     */
    public void setFundTransferAmount(java.lang.Double fundTransferAmount) {
        this.fundTransferAmount = fundTransferAmount;
    }


    /**
     * Gets the fundTransferFeeAmount value for this FundTransferInfoType.
     * 
     * @return fundTransferFeeAmount   * Phí chuyển khoản
     */
    public java.lang.Integer getFundTransferFeeAmount() {
        return fundTransferFeeAmount;
    }


    /**
     * Sets the fundTransferFeeAmount value for this FundTransferInfoType.
     * 
     * @param fundTransferFeeAmount   * Phí chuyển khoản
     */
    public void setFundTransferFeeAmount(java.lang.Integer fundTransferFeeAmount) {
        this.fundTransferFeeAmount = fundTransferFeeAmount;
    }


    /**
     * Gets the fundTransferFeeChgComp value for this FundTransferInfoType.
     * 
     * @return fundTransferFeeChgComp   * Diễn giải thu phí
     */
    public java.lang.String getFundTransferFeeChgComp() {
        return fundTransferFeeChgComp;
    }


    /**
     * Sets the fundTransferFeeChgComp value for this FundTransferInfoType.
     * 
     * @param fundTransferFeeChgComp   * Diễn giải thu phí
     */
    public void setFundTransferFeeChgComp(java.lang.String fundTransferFeeChgComp) {
        this.fundTransferFeeChgComp = fundTransferFeeChgComp;
    }


    /**
     * Gets the fundTransferVATAmount value for this FundTransferInfoType.
     * 
     * @return fundTransferVATAmount   * Thuế dịch vụ chuyển khoản
     */
    public java.lang.Integer getFundTransferVATAmount() {
        return fundTransferVATAmount;
    }


    /**
     * Sets the fundTransferVATAmount value for this FundTransferInfoType.
     * 
     * @param fundTransferVATAmount   * Thuế dịch vụ chuyển khoản
     */
    public void setFundTransferVATAmount(java.lang.Integer fundTransferVATAmount) {
        this.fundTransferVATAmount = fundTransferVATAmount;
    }


    /**
     * Gets the fundTransferVATChgComp value for this FundTransferInfoType.
     * 
     * @return fundTransferVATChgComp   * Diễn giải thu VAT
     */
    public java.lang.String getFundTransferVATChgComp() {
        return fundTransferVATChgComp;
    }


    /**
     * Sets the fundTransferVATChgComp value for this FundTransferInfoType.
     * 
     * @param fundTransferVATChgComp   * Diễn giải thu VAT
     */
    public void setFundTransferVATChgComp(java.lang.String fundTransferVATChgComp) {
        this.fundTransferVATChgComp = fundTransferVATChgComp;
    }


    /**
     * Gets the fundTransferNarative value for this FundTransferInfoType.
     * 
     * @return fundTransferNarative   * Ghi chú nội dung chuyển khoản
     */
    public java.lang.String getFundTransferNarative() {
        return fundTransferNarative;
    }


    /**
     * Sets the fundTransferNarative value for this FundTransferInfoType.
     * 
     * @param fundTransferNarative   * Ghi chú nội dung chuyển khoản
     */
    public void setFundTransferNarative(java.lang.String fundTransferNarative) {
        this.fundTransferNarative = fundTransferNarative;
    }


    /**
     * Gets the fundTransferActiveDate value for this FundTransferInfoType.
     * 
     * @return fundTransferActiveDate   * Ngày thực hiện chuyển khoản (DD/MM/YYYY). Nếu
     *     					không truyền thì xem như ngày hiện tại. Trường
     *     					này optional cho client
     */
    public java.lang.String getFundTransferActiveDate() {
        return fundTransferActiveDate;
    }


    /**
     * Sets the fundTransferActiveDate value for this FundTransferInfoType.
     * 
     * @param fundTransferActiveDate   * Ngày thực hiện chuyển khoản (DD/MM/YYYY). Nếu
     *     					không truyền thì xem như ngày hiện tại. Trường
     *     					này optional cho client
     */
    public void setFundTransferActiveDate(java.lang.String fundTransferActiveDate) {
        this.fundTransferActiveDate = fundTransferActiveDate;
    }


    /**
     * Gets the fundTransferActualAmount value for this FundTransferInfoType.
     * 
     * @return fundTransferActualAmount   * Bằng tổng của (fundTransferAmount + Fee + VAT)
     */
    public java.lang.Integer getFundTransferActualAmount() {
        return fundTransferActualAmount;
    }


    /**
     * Sets the fundTransferActualAmount value for this FundTransferInfoType.
     * 
     * @param fundTransferActualAmount   * Bằng tổng của (fundTransferAmount + Fee + VAT)
     */
    public void setFundTransferActualAmount(java.lang.Integer fundTransferActualAmount) {
        this.fundTransferActualAmount = fundTransferActualAmount;
    }


    /**
     * Gets the fundTransferComment value for this FundTransferInfoType.
     * 
     * @return fundTransferComment   * Thông tin thêm bất kỳ mà SCB muốn đưa vào
     */
    public java.lang.String getFundTransferComment() {
        return fundTransferComment;
    }


    /**
     * Sets the fundTransferComment value for this FundTransferInfoType.
     * 
     * @param fundTransferComment   * Thông tin thêm bất kỳ mà SCB muốn đưa vào
     */
    public void setFundTransferComment(java.lang.String fundTransferComment) {
        this.fundTransferComment = fundTransferComment;
    }


    /**
     * Gets the fundTransferFeeType value for this FundTransferInfoType.
     * 
     * @return fundTransferFeeType
     */
    public java.lang.String getFundTransferFeeType() {
        return fundTransferFeeType;
    }


    /**
     * Sets the fundTransferFeeType value for this FundTransferInfoType.
     * 
     * @param fundTransferFeeType
     */
    public void setFundTransferFeeType(java.lang.String fundTransferFeeType) {
        this.fundTransferFeeType = fundTransferFeeType;
    }


    /**
     * Gets the fundTransferFeePayee value for this FundTransferInfoType.
     * 
     * @return fundTransferFeePayee   * Phân biệt người trả phí khi thực hiện giao dịch
     *     					- 1: Phí người chuyển trả; 2: phí người nhận trả
     */
    public java.lang.String getFundTransferFeePayee() {
        return fundTransferFeePayee;
    }


    /**
     * Sets the fundTransferFeePayee value for this FundTransferInfoType.
     * 
     * @param fundTransferFeePayee   * Phân biệt người trả phí khi thực hiện giao dịch
     *     					- 1: Phí người chuyển trả; 2: phí người nhận trả
     */
    public void setFundTransferFeePayee(java.lang.String fundTransferFeePayee) {
        this.fundTransferFeePayee = fundTransferFeePayee;
    }


    /**
     * Gets the fundTransferServiceType value for this FundTransferInfoType.
     * 
     * @return fundTransferServiceType
     */
    public java.lang.String getFundTransferServiceType() {
        return fundTransferServiceType;
    }


    /**
     * Sets the fundTransferServiceType value for this FundTransferInfoType.
     * 
     * @param fundTransferServiceType
     */
    public void setFundTransferServiceType(java.lang.String fundTransferServiceType) {
        this.fundTransferServiceType = fundTransferServiceType;
    }


    /**
     * Gets the fundTransferChanelId value for this FundTransferInfoType.
     * 
     * @return fundTransferChanelId
     */
    public java.lang.String getFundTransferChanelId() {
        return fundTransferChanelId;
    }


    /**
     * Sets the fundTransferChanelId value for this FundTransferInfoType.
     * 
     * @param fundTransferChanelId
     */
    public void setFundTransferChanelId(java.lang.String fundTransferChanelId) {
        this.fundTransferChanelId = fundTransferChanelId;
    }


    /**
     * Gets the fundTransferFT value for this FundTransferInfoType.
     * 
     * @return fundTransferFT   * Phân biệt RT (= 0 hoặc null) hay FT (=1)
     */
    public java.lang.String getFundTransferFT() {
        return fundTransferFT;
    }


    /**
     * Sets the fundTransferFT value for this FundTransferInfoType.
     * 
     * @param fundTransferFT   * Phân biệt RT (= 0 hoặc null) hay FT (=1)
     */
    public void setFundTransferFT(java.lang.String fundTransferFT) {
        this.fundTransferFT = fundTransferFT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FundTransferInfoType)) return false;
        FundTransferInfoType other = (FundTransferInfoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fundTransferTransactionType==null && other.getFundTransferTransactionType()==null) || 
             (this.fundTransferTransactionType!=null &&
              this.fundTransferTransactionType.equals(other.getFundTransferTransactionType()))) &&
            ((this.fundTransferProductCode==null && other.getFundTransferProductCode()==null) || 
             (this.fundTransferProductCode!=null &&
              this.fundTransferProductCode.equals(other.getFundTransferProductCode()))) &&
            ((this.fundTransferAmount==null && other.getFundTransferAmount()==null) || 
             (this.fundTransferAmount!=null &&
              this.fundTransferAmount.equals(other.getFundTransferAmount()))) &&
            ((this.fundTransferFeeAmount==null && other.getFundTransferFeeAmount()==null) || 
             (this.fundTransferFeeAmount!=null &&
              this.fundTransferFeeAmount.equals(other.getFundTransferFeeAmount()))) &&
            ((this.fundTransferFeeChgComp==null && other.getFundTransferFeeChgComp()==null) || 
             (this.fundTransferFeeChgComp!=null &&
              this.fundTransferFeeChgComp.equals(other.getFundTransferFeeChgComp()))) &&
            ((this.fundTransferVATAmount==null && other.getFundTransferVATAmount()==null) || 
             (this.fundTransferVATAmount!=null &&
              this.fundTransferVATAmount.equals(other.getFundTransferVATAmount()))) &&
            ((this.fundTransferVATChgComp==null && other.getFundTransferVATChgComp()==null) || 
             (this.fundTransferVATChgComp!=null &&
              this.fundTransferVATChgComp.equals(other.getFundTransferVATChgComp()))) &&
                
            ((this.fundTransferFINAmount==null && other.getFundTransferFINAmount()==null) || 
             (this.fundTransferFINAmount!=null &&
              this.fundTransferFINAmount.equals(other.getFundTransferFINAmount()))) &&
            ((this.fundTransferFINChgComp==null && other.getFundTransferFINChgComp()==null) || 
             (this.fundTransferFINChgComp!=null &&
              this.fundTransferFINChgComp.equals(other.getFundTransferFINChgComp()))) &&
            ((this.fundTransferFINVATAmount==null && other.getFundTransferFINVATAmount()==null) || 
             (this.fundTransferFINVATAmount!=null &&
              this.fundTransferFINVATAmount.equals(other.getFundTransferFINVATAmount()))) &&
            ((this.fundTransferFINVATChgComp==null && other.getFundTransferFINVATChgComp()==null) || 
             (this.fundTransferFINVATChgComp!=null &&
              this.fundTransferFINVATChgComp.equals(other.getFundTransferFINVATChgComp()))) &&
                
            ((this.fundTransferNarative==null && other.getFundTransferNarative()==null) || 
             (this.fundTransferNarative!=null &&
              this.fundTransferNarative.equals(other.getFundTransferNarative()))) &&
            ((this.fundTransferActiveDate==null && other.getFundTransferActiveDate()==null) || 
             (this.fundTransferActiveDate!=null &&
              this.fundTransferActiveDate.equals(other.getFundTransferActiveDate()))) &&
            ((this.fundTransferActualAmount==null && other.getFundTransferActualAmount()==null) || 
             (this.fundTransferActualAmount!=null &&
              this.fundTransferActualAmount.equals(other.getFundTransferActualAmount()))) &&
            ((this.fundTransferComment==null && other.getFundTransferComment()==null) || 
             (this.fundTransferComment!=null &&
              this.fundTransferComment.equals(other.getFundTransferComment()))) &&
            ((this.fundTransferFeeType==null && other.getFundTransferFeeType()==null) || 
             (this.fundTransferFeeType!=null &&
              this.fundTransferFeeType.equals(other.getFundTransferFeeType()))) &&
            ((this.fundTransferFeePayee==null && other.getFundTransferFeePayee()==null) || 
             (this.fundTransferFeePayee!=null &&
              this.fundTransferFeePayee.equals(other.getFundTransferFeePayee()))) &&
            ((this.fundTransferServiceType==null && other.getFundTransferServiceType()==null) || 
             (this.fundTransferServiceType!=null &&
              this.fundTransferServiceType.equals(other.getFundTransferServiceType()))) &&
            ((this.fundTransferChanelId==null && other.getFundTransferChanelId()==null) || 
             (this.fundTransferChanelId!=null &&
              this.fundTransferChanelId.equals(other.getFundTransferChanelId()))) &&
            ((this.exchangeRateInfo==null && other.getExchangeRateInfo()==null) || 
             (this.exchangeRateInfo!=null &&
              this.exchangeRateInfo.equals(other.getExchangeRateInfo()))) &&
            ((this.fundTransferFT==null && other.getFundTransferFT()==null) || 
             (this.fundTransferFT!=null &&
              this.fundTransferFT.equals(other.getFundTransferFT())));
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
        if (getFundTransferTransactionType() != null) {
            _hashCode += getFundTransferTransactionType().hashCode();
        }
        if (getFundTransferProductCode() != null) {
            _hashCode += getFundTransferProductCode().hashCode();
        }
        if (getFundTransferAmount() != null) {
            _hashCode += getFundTransferAmount().hashCode();
        }
        if (getFundTransferFeeAmount() != null) {
            _hashCode += getFundTransferFeeAmount().hashCode();
        }
        if (getFundTransferFeeChgComp() != null) {
            _hashCode += getFundTransferFeeChgComp().hashCode();
        }
        if (getFundTransferVATAmount() != null) {
            _hashCode += getFundTransferVATAmount().hashCode();
        }
        if (getFundTransferVATChgComp() != null) {
            _hashCode += getFundTransferVATChgComp().hashCode();
        }
        
        if (getFundTransferFINAmount() != null) {
            _hashCode += getFundTransferFINAmount().hashCode();
        }
        if (getFundTransferFINChgComp() != null) {
            _hashCode += getFundTransferFINChgComp().hashCode();
        }
        if (getFundTransferFINVATAmount() != null) {
            _hashCode += getFundTransferFINVATAmount().hashCode();
        }
        if (getFundTransferFINVATChgComp() != null) {
            _hashCode += getFundTransferFINVATChgComp().hashCode();
        }
        
        if (getFundTransferNarative() != null) {
            _hashCode += getFundTransferNarative().hashCode();
        }
        if (getFundTransferActiveDate() != null) {
            _hashCode += getFundTransferActiveDate().hashCode();
        }
        if (getFundTransferActualAmount() != null) {
            _hashCode += getFundTransferActualAmount().hashCode();
        }
        if (getFundTransferComment() != null) {
            _hashCode += getFundTransferComment().hashCode();
        }
        if (getFundTransferFeeType() != null) {
            _hashCode += getFundTransferFeeType().hashCode();
        }
        if (getFundTransferFeePayee() != null) {
            _hashCode += getFundTransferFeePayee().hashCode();
        }
        if (getFundTransferServiceType() != null) {
            _hashCode += getFundTransferServiceType().hashCode();
        }
        if (getFundTransferChanelId() != null) {
            _hashCode += getFundTransferChanelId().hashCode();
        }
        if (getFundTransferFT() != null) {
            _hashCode += getFundTransferFT().hashCode();
        }
        if (getExchangeRateInfo()!= null) {
            _hashCode += getExchangeRateInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FundTransferInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "FundTransferInfoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferTransactionType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferTransactionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferProductCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferProductCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferFeeAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferFeeAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferFeeChgComp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferFeeChgComp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferVATAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferVATAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferVATChgComp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferVATChgComp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferFINAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferFINAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferFINChgComp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferFINChgComp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferFINVATAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferFINVATAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferFINVATChgComp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferFINVATChgComp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        
        
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferNarative");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferNarative"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferActiveDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferActiveDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferActualAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferActualAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferComment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferComment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferFeeType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferFeeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferFeePayee");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferFeePayee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferServiceType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferServiceType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferChanelId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferChanelId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exchangeRateInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exchangeRateInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransferFT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fundTransferFT"));
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
     * @return the fundTransferFINAmount
     */
    public java.lang.Integer getFundTransferFINAmount() {
        return fundTransferFINAmount;
    }

    /**
     * @param fundTransferFINAmount the fundTransferFINAmount to set
     */
    public void setFundTransferFINAmount(java.lang.Integer fundTransferFINAmount) {
        this.fundTransferFINAmount = fundTransferFINAmount;
    }

    /**
     * @return the fundTransferFINChgComp
     */
    public java.lang.String getFundTransferFINChgComp() {
        return fundTransferFINChgComp;
    }

    /**
     * @param fundTransferFINChgComp the fundTransferFINChgComp to set
     */
    public void setFundTransferFINChgComp(java.lang.String fundTransferFINChgComp) {
        this.fundTransferFINChgComp = fundTransferFINChgComp;
    }

    /**
     * @return the fundTransferFINVATAmount
     */
    public java.lang.Integer getFundTransferFINVATAmount() {
        return fundTransferFINVATAmount;
    }

    /**
     * @param fundTransferFINVATAmount the fundTransferFINVATAmount to set
     */
    public void setFundTransferFINVATAmount(java.lang.Integer fundTransferFINVATAmount) {
        this.fundTransferFINVATAmount = fundTransferFINVATAmount;
    }

    /**
     * @return the fundTransferFINVATChgComp
     */
    public java.lang.String getFundTransferFINVATChgComp() {
        return fundTransferFINVATChgComp;
    }

    /**
     * @param fundTransferFINVATChgComp the fundTransferFINVATChgComp to set
     */
    public void setFundTransferFINVATChgComp(java.lang.String fundTransferFINVATChgComp) {
        this.fundTransferFINVATChgComp = fundTransferFINVATChgComp;
    }

    public ExchangeRateInfoType getExchangeRateInfo() {
        return exchangeRateInfo;
    }

    public void setExchangeRateInfo(ExchangeRateInfoType exchangeRateInfo) {
        this.exchangeRateInfo = exchangeRateInfo;
    }

    public BigDecimal getFundTransferAmountStr() {
        return fundTransferAmountStr;
    }

    public void setFundTransferAmountStr(BigDecimal fundTransferAmountStr) {
        this.fundTransferAmountStr = fundTransferAmountStr;
    }
}
