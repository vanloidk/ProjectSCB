/**
 * LoanAccountInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class LoanAccountInfoType  implements java.io.Serializable {
    private vn.com.scb.bian.CIFDataType CIFInfo;

    private vn.com.scb.bian.CustomerInfoType customerInfo;

    /* Số tài khoản vay */
    private java.lang.String loanAccountNum;

    /* Dư nợ quy đổi */
    private java.lang.String accountBalanceEQV;

    /* dư nợ ban đầu */
    private java.lang.String loanInitialBalance;

    /* Mã sản phẩm vay */
    private java.lang.String loanProductCode;

    /* Tên sản phẩm vay */
    private java.lang.String loanProductName;

    /* Dư nợ tài khoản vay */
    private java.lang.String loanOutstandingBalance;

    /* Lãi suất vay */
    private java.lang.String loanInterestRate;

    /* Ngày hiệu lực vay, ngày vay */
    private java.lang.String loanEffectDate;

    /* Ngày đáo hạn khoản khoản vay */
    private java.lang.String loanMaturityDate;

    /* Loại tiền vay */
    private java.lang.String loanCurrency;

    /* Mã đơn vị cho vay */
    private java.lang.String loanBranchCode;

    /* Dư nợ gốc đã trả */
    private java.lang.String loanPrincipalPaid;

    /* Gốc vay đến hạn */
    private java.lang.String loanPrincipalDue;

    /* Lãi vay đã trả */
    private java.lang.String loanInterestPaid;

    /* phạt gốc chậm trả đến hạn */
    private java.lang.String loanPernalPrinDue;

    /* phạt lãi chậm trả đến hạn */
    private java.lang.String loanPernalIntDue;

    /* Số tiền giải ngân */
    private java.lang.String loanDisbursmentAmount;

    /* Lãi vay phải trả */
    private java.lang.String loanInterestDue;

    /* Số hợp đồng vay */
    private java.lang.String loanContract;

    /* Hạn mức vay */
    private java.lang.String loanLimit;

    /* Giá trị hợp đồng vay */
    private java.lang.String loanContractValue;

    /* Ngày giải ngân đầu tiên */
    private java.lang.String loanFirstDisbursmentDate;

    /* Lãi dự thu */
    private java.lang.String loanInterestAccrual;

    private vn.com.scb.bian.StaffType staff;

    private vn.com.scb.bian.DelegationType delegation;

    public LoanAccountInfoType() {
    }

    public LoanAccountInfoType(
           vn.com.scb.bian.CIFDataType CIFInfo,
           vn.com.scb.bian.CustomerInfoType customerInfo,
           java.lang.String loanAccountNum,
           java.lang.String accountBalanceEQV,
           java.lang.String loanInitialBalance,
           java.lang.String loanProductCode,
           java.lang.String loanProductName,
           java.lang.String loanOutstandingBalance,
           java.lang.String loanInterestRate,
           java.lang.String loanEffectDate,
           java.lang.String loanMaturityDate,
           java.lang.String loanCurrency,
           java.lang.String loanBranchCode,
           java.lang.String loanPrincipalPaid,
           java.lang.String loanPrincipalDue,
           java.lang.String loanInterestPaid,
           java.lang.String loanPernalPrinDue,
           java.lang.String loanPernalIntDue,
           java.lang.String loanDisbursmentAmount,
           java.lang.String loanInterestDue,
           java.lang.String loanContract,
           java.lang.String loanLimit,
           java.lang.String loanContractValue,
           java.lang.String loanFirstDisbursmentDate,
           java.lang.String loanInterestAccrual,
           vn.com.scb.bian.StaffType staff,
           vn.com.scb.bian.DelegationType delegation) {
           this.CIFInfo = CIFInfo;
           this.customerInfo = customerInfo;
           this.loanAccountNum = loanAccountNum;
           this.accountBalanceEQV = accountBalanceEQV;
           this.loanInitialBalance = loanInitialBalance;
           this.loanProductCode = loanProductCode;
           this.loanProductName = loanProductName;
           this.loanOutstandingBalance = loanOutstandingBalance;
           this.loanInterestRate = loanInterestRate;
           this.loanEffectDate = loanEffectDate;
           this.loanMaturityDate = loanMaturityDate;
           this.loanCurrency = loanCurrency;
           this.loanBranchCode = loanBranchCode;
           this.loanPrincipalPaid = loanPrincipalPaid;
           this.loanPrincipalDue = loanPrincipalDue;
           this.loanInterestPaid = loanInterestPaid;
           this.loanPernalPrinDue = loanPernalPrinDue;
           this.loanPernalIntDue = loanPernalIntDue;
           this.loanDisbursmentAmount = loanDisbursmentAmount;
           this.loanInterestDue = loanInterestDue;
           this.loanContract = loanContract;
           this.loanLimit = loanLimit;
           this.loanContractValue = loanContractValue;
           this.loanFirstDisbursmentDate = loanFirstDisbursmentDate;
           this.loanInterestAccrual = loanInterestAccrual;
           this.staff = staff;
           this.delegation = delegation;
    }


    /**
     * Gets the CIFInfo value for this LoanAccountInfoType.
     * 
     * @return CIFInfo
     */
    public vn.com.scb.bian.CIFDataType getCIFInfo() {
        return CIFInfo;
    }


    /**
     * Sets the CIFInfo value for this LoanAccountInfoType.
     * 
     * @param CIFInfo
     */
    public void setCIFInfo(vn.com.scb.bian.CIFDataType CIFInfo) {
        this.CIFInfo = CIFInfo;
    }


    /**
     * Gets the customerInfo value for this LoanAccountInfoType.
     * 
     * @return customerInfo
     */
    public vn.com.scb.bian.CustomerInfoType getCustomerInfo() {
        return customerInfo;
    }


    /**
     * Sets the customerInfo value for this LoanAccountInfoType.
     * 
     * @param customerInfo
     */
    public void setCustomerInfo(vn.com.scb.bian.CustomerInfoType customerInfo) {
        this.customerInfo = customerInfo;
    }


    /**
     * Gets the loanAccountNum value for this LoanAccountInfoType.
     * 
     * @return loanAccountNum   * Số tài khoản vay
     */
    public java.lang.String getLoanAccountNum() {
        return loanAccountNum;
    }


    /**
     * Sets the loanAccountNum value for this LoanAccountInfoType.
     * 
     * @param loanAccountNum   * Số tài khoản vay
     */
    public void setLoanAccountNum(java.lang.String loanAccountNum) {
        this.loanAccountNum = loanAccountNum;
    }


    /**
     * Gets the accountBalanceEQV value for this LoanAccountInfoType.
     * 
     * @return accountBalanceEQV   * Dư nợ quy đổi
     */
    public java.lang.String getAccountBalanceEQV() {
        return accountBalanceEQV;
    }


    /**
     * Sets the accountBalanceEQV value for this LoanAccountInfoType.
     * 
     * @param accountBalanceEQV   * Dư nợ quy đổi
     */
    public void setAccountBalanceEQV(java.lang.String accountBalanceEQV) {
        this.accountBalanceEQV = accountBalanceEQV;
    }


    /**
     * Gets the loanInitialBalance value for this LoanAccountInfoType.
     * 
     * @return loanInitialBalance   * dư nợ ban đầu
     */
    public java.lang.String getLoanInitialBalance() {
        return loanInitialBalance;
    }


    /**
     * Sets the loanInitialBalance value for this LoanAccountInfoType.
     * 
     * @param loanInitialBalance   * dư nợ ban đầu
     */
    public void setLoanInitialBalance(java.lang.String loanInitialBalance) {
        this.loanInitialBalance = loanInitialBalance;
    }


    /**
     * Gets the loanProductCode value for this LoanAccountInfoType.
     * 
     * @return loanProductCode   * Mã sản phẩm vay
     */
    public java.lang.String getLoanProductCode() {
        return loanProductCode;
    }


    /**
     * Sets the loanProductCode value for this LoanAccountInfoType.
     * 
     * @param loanProductCode   * Mã sản phẩm vay
     */
    public void setLoanProductCode(java.lang.String loanProductCode) {
        this.loanProductCode = loanProductCode;
    }


    /**
     * Gets the loanProductName value for this LoanAccountInfoType.
     * 
     * @return loanProductName   * Tên sản phẩm vay
     */
    public java.lang.String getLoanProductName() {
        return loanProductName;
    }


    /**
     * Sets the loanProductName value for this LoanAccountInfoType.
     * 
     * @param loanProductName   * Tên sản phẩm vay
     */
    public void setLoanProductName(java.lang.String loanProductName) {
        this.loanProductName = loanProductName;
    }


    /**
     * Gets the loanOutstandingBalance value for this LoanAccountInfoType.
     * 
     * @return loanOutstandingBalance   * Dư nợ tài khoản vay
     */
    public java.lang.String getLoanOutstandingBalance() {
        return loanOutstandingBalance;
    }


    /**
     * Sets the loanOutstandingBalance value for this LoanAccountInfoType.
     * 
     * @param loanOutstandingBalance   * Dư nợ tài khoản vay
     */
    public void setLoanOutstandingBalance(java.lang.String loanOutstandingBalance) {
        this.loanOutstandingBalance = loanOutstandingBalance;
    }


    /**
     * Gets the loanInterestRate value for this LoanAccountInfoType.
     * 
     * @return loanInterestRate   * Lãi suất vay
     */
    public java.lang.String getLoanInterestRate() {
        return loanInterestRate;
    }


    /**
     * Sets the loanInterestRate value for this LoanAccountInfoType.
     * 
     * @param loanInterestRate   * Lãi suất vay
     */
    public void setLoanInterestRate(java.lang.String loanInterestRate) {
        this.loanInterestRate = loanInterestRate;
    }


    /**
     * Gets the loanEffectDate value for this LoanAccountInfoType.
     * 
     * @return loanEffectDate   * Ngày hiệu lực vay, ngày vay
     */
    public java.lang.String getLoanEffectDate() {
        return loanEffectDate;
    }


    /**
     * Sets the loanEffectDate value for this LoanAccountInfoType.
     * 
     * @param loanEffectDate   * Ngày hiệu lực vay, ngày vay
     */
    public void setLoanEffectDate(java.lang.String loanEffectDate) {
        this.loanEffectDate = loanEffectDate;
    }


    /**
     * Gets the loanMaturityDate value for this LoanAccountInfoType.
     * 
     * @return loanMaturityDate   * Ngày đáo hạn khoản khoản vay
     */
    public java.lang.String getLoanMaturityDate() {
        return loanMaturityDate;
    }


    /**
     * Sets the loanMaturityDate value for this LoanAccountInfoType.
     * 
     * @param loanMaturityDate   * Ngày đáo hạn khoản khoản vay
     */
    public void setLoanMaturityDate(java.lang.String loanMaturityDate) {
        this.loanMaturityDate = loanMaturityDate;
    }


    /**
     * Gets the loanCurrency value for this LoanAccountInfoType.
     * 
     * @return loanCurrency   * Loại tiền vay
     */
    public java.lang.String getLoanCurrency() {
        return loanCurrency;
    }


    /**
     * Sets the loanCurrency value for this LoanAccountInfoType.
     * 
     * @param loanCurrency   * Loại tiền vay
     */
    public void setLoanCurrency(java.lang.String loanCurrency) {
        this.loanCurrency = loanCurrency;
    }


    /**
     * Gets the loanBranchCode value for this LoanAccountInfoType.
     * 
     * @return loanBranchCode   * Mã đơn vị cho vay
     */
    public java.lang.String getLoanBranchCode() {
        return loanBranchCode;
    }


    /**
     * Sets the loanBranchCode value for this LoanAccountInfoType.
     * 
     * @param loanBranchCode   * Mã đơn vị cho vay
     */
    public void setLoanBranchCode(java.lang.String loanBranchCode) {
        this.loanBranchCode = loanBranchCode;
    }


    /**
     * Gets the loanPrincipalPaid value for this LoanAccountInfoType.
     * 
     * @return loanPrincipalPaid   * Dư nợ gốc đã trả
     */
    public java.lang.String getLoanPrincipalPaid() {
        return loanPrincipalPaid;
    }


    /**
     * Sets the loanPrincipalPaid value for this LoanAccountInfoType.
     * 
     * @param loanPrincipalPaid   * Dư nợ gốc đã trả
     */
    public void setLoanPrincipalPaid(java.lang.String loanPrincipalPaid) {
        this.loanPrincipalPaid = loanPrincipalPaid;
    }


    /**
     * Gets the loanPrincipalDue value for this LoanAccountInfoType.
     * 
     * @return loanPrincipalDue   * Gốc vay đến hạn
     */
    public java.lang.String getLoanPrincipalDue() {
        return loanPrincipalDue;
    }


    /**
     * Sets the loanPrincipalDue value for this LoanAccountInfoType.
     * 
     * @param loanPrincipalDue   * Gốc vay đến hạn
     */
    public void setLoanPrincipalDue(java.lang.String loanPrincipalDue) {
        this.loanPrincipalDue = loanPrincipalDue;
    }


    /**
     * Gets the loanInterestPaid value for this LoanAccountInfoType.
     * 
     * @return loanInterestPaid   * Lãi vay đã trả
     */
    public java.lang.String getLoanInterestPaid() {
        return loanInterestPaid;
    }


    /**
     * Sets the loanInterestPaid value for this LoanAccountInfoType.
     * 
     * @param loanInterestPaid   * Lãi vay đã trả
     */
    public void setLoanInterestPaid(java.lang.String loanInterestPaid) {
        this.loanInterestPaid = loanInterestPaid;
    }


    /**
     * Gets the loanPernalPrinDue value for this LoanAccountInfoType.
     * 
     * @return loanPernalPrinDue   * phạt gốc chậm trả đến hạn
     */
    public java.lang.String getLoanPernalPrinDue() {
        return loanPernalPrinDue;
    }


    /**
     * Sets the loanPernalPrinDue value for this LoanAccountInfoType.
     * 
     * @param loanPernalPrinDue   * phạt gốc chậm trả đến hạn
     */
    public void setLoanPernalPrinDue(java.lang.String loanPernalPrinDue) {
        this.loanPernalPrinDue = loanPernalPrinDue;
    }


    /**
     * Gets the loanPernalIntDue value for this LoanAccountInfoType.
     * 
     * @return loanPernalIntDue   * phạt lãi chậm trả đến hạn
     */
    public java.lang.String getLoanPernalIntDue() {
        return loanPernalIntDue;
    }


    /**
     * Sets the loanPernalIntDue value for this LoanAccountInfoType.
     * 
     * @param loanPernalIntDue   * phạt lãi chậm trả đến hạn
     */
    public void setLoanPernalIntDue(java.lang.String loanPernalIntDue) {
        this.loanPernalIntDue = loanPernalIntDue;
    }


    /**
     * Gets the loanDisbursmentAmount value for this LoanAccountInfoType.
     * 
     * @return loanDisbursmentAmount   * Số tiền giải ngân
     */
    public java.lang.String getLoanDisbursmentAmount() {
        return loanDisbursmentAmount;
    }


    /**
     * Sets the loanDisbursmentAmount value for this LoanAccountInfoType.
     * 
     * @param loanDisbursmentAmount   * Số tiền giải ngân
     */
    public void setLoanDisbursmentAmount(java.lang.String loanDisbursmentAmount) {
        this.loanDisbursmentAmount = loanDisbursmentAmount;
    }


    /**
     * Gets the loanInterestDue value for this LoanAccountInfoType.
     * 
     * @return loanInterestDue   * Lãi vay phải trả
     */
    public java.lang.String getLoanInterestDue() {
        return loanInterestDue;
    }


    /**
     * Sets the loanInterestDue value for this LoanAccountInfoType.
     * 
     * @param loanInterestDue   * Lãi vay phải trả
     */
    public void setLoanInterestDue(java.lang.String loanInterestDue) {
        this.loanInterestDue = loanInterestDue;
    }


    /**
     * Gets the loanContract value for this LoanAccountInfoType.
     * 
     * @return loanContract   * Số hợp đồng vay
     */
    public java.lang.String getLoanContract() {
        return loanContract;
    }


    /**
     * Sets the loanContract value for this LoanAccountInfoType.
     * 
     * @param loanContract   * Số hợp đồng vay
     */
    public void setLoanContract(java.lang.String loanContract) {
        this.loanContract = loanContract;
    }


    /**
     * Gets the loanLimit value for this LoanAccountInfoType.
     * 
     * @return loanLimit   * Hạn mức vay
     */
    public java.lang.String getLoanLimit() {
        return loanLimit;
    }


    /**
     * Sets the loanLimit value for this LoanAccountInfoType.
     * 
     * @param loanLimit   * Hạn mức vay
     */
    public void setLoanLimit(java.lang.String loanLimit) {
        this.loanLimit = loanLimit;
    }


    /**
     * Gets the loanContractValue value for this LoanAccountInfoType.
     * 
     * @return loanContractValue   * Giá trị hợp đồng vay
     */
    public java.lang.String getLoanContractValue() {
        return loanContractValue;
    }


    /**
     * Sets the loanContractValue value for this LoanAccountInfoType.
     * 
     * @param loanContractValue   * Giá trị hợp đồng vay
     */
    public void setLoanContractValue(java.lang.String loanContractValue) {
        this.loanContractValue = loanContractValue;
    }


    /**
     * Gets the loanFirstDisbursmentDate value for this LoanAccountInfoType.
     * 
     * @return loanFirstDisbursmentDate   * Ngày giải ngân đầu tiên
     */
    public java.lang.String getLoanFirstDisbursmentDate() {
        return loanFirstDisbursmentDate;
    }


    /**
     * Sets the loanFirstDisbursmentDate value for this LoanAccountInfoType.
     * 
     * @param loanFirstDisbursmentDate   * Ngày giải ngân đầu tiên
     */
    public void setLoanFirstDisbursmentDate(java.lang.String loanFirstDisbursmentDate) {
        this.loanFirstDisbursmentDate = loanFirstDisbursmentDate;
    }


    /**
     * Gets the loanInterestAccrual value for this LoanAccountInfoType.
     * 
     * @return loanInterestAccrual   * Lãi dự thu
     */
    public java.lang.String getLoanInterestAccrual() {
        return loanInterestAccrual;
    }


    /**
     * Sets the loanInterestAccrual value for this LoanAccountInfoType.
     * 
     * @param loanInterestAccrual   * Lãi dự thu
     */
    public void setLoanInterestAccrual(java.lang.String loanInterestAccrual) {
        this.loanInterestAccrual = loanInterestAccrual;
    }


    /**
     * Gets the staff value for this LoanAccountInfoType.
     * 
     * @return staff
     */
    public vn.com.scb.bian.StaffType getStaff() {
        return staff;
    }


    /**
     * Sets the staff value for this LoanAccountInfoType.
     * 
     * @param staff
     */
    public void setStaff(vn.com.scb.bian.StaffType staff) {
        this.staff = staff;
    }


    /**
     * Gets the delegation value for this LoanAccountInfoType.
     * 
     * @return delegation
     */
    public vn.com.scb.bian.DelegationType getDelegation() {
        return delegation;
    }


    /**
     * Sets the delegation value for this LoanAccountInfoType.
     * 
     * @param delegation
     */
    public void setDelegation(vn.com.scb.bian.DelegationType delegation) {
        this.delegation = delegation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LoanAccountInfoType)) return false;
        LoanAccountInfoType other = (LoanAccountInfoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CIFInfo==null && other.getCIFInfo()==null) || 
             (this.CIFInfo!=null &&
              this.CIFInfo.equals(other.getCIFInfo()))) &&
            ((this.customerInfo==null && other.getCustomerInfo()==null) || 
             (this.customerInfo!=null &&
              this.customerInfo.equals(other.getCustomerInfo()))) &&
            ((this.loanAccountNum==null && other.getLoanAccountNum()==null) || 
             (this.loanAccountNum!=null &&
              this.loanAccountNum.equals(other.getLoanAccountNum()))) &&
            ((this.accountBalanceEQV==null && other.getAccountBalanceEQV()==null) || 
             (this.accountBalanceEQV!=null &&
              this.accountBalanceEQV.equals(other.getAccountBalanceEQV()))) &&
            ((this.loanInitialBalance==null && other.getLoanInitialBalance()==null) || 
             (this.loanInitialBalance!=null &&
              this.loanInitialBalance.equals(other.getLoanInitialBalance()))) &&
            ((this.loanProductCode==null && other.getLoanProductCode()==null) || 
             (this.loanProductCode!=null &&
              this.loanProductCode.equals(other.getLoanProductCode()))) &&
            ((this.loanProductName==null && other.getLoanProductName()==null) || 
             (this.loanProductName!=null &&
              this.loanProductName.equals(other.getLoanProductName()))) &&
            ((this.loanOutstandingBalance==null && other.getLoanOutstandingBalance()==null) || 
             (this.loanOutstandingBalance!=null &&
              this.loanOutstandingBalance.equals(other.getLoanOutstandingBalance()))) &&
            ((this.loanInterestRate==null && other.getLoanInterestRate()==null) || 
             (this.loanInterestRate!=null &&
              this.loanInterestRate.equals(other.getLoanInterestRate()))) &&
            ((this.loanEffectDate==null && other.getLoanEffectDate()==null) || 
             (this.loanEffectDate!=null &&
              this.loanEffectDate.equals(other.getLoanEffectDate()))) &&
            ((this.loanMaturityDate==null && other.getLoanMaturityDate()==null) || 
             (this.loanMaturityDate!=null &&
              this.loanMaturityDate.equals(other.getLoanMaturityDate()))) &&
            ((this.loanCurrency==null && other.getLoanCurrency()==null) || 
             (this.loanCurrency!=null &&
              this.loanCurrency.equals(other.getLoanCurrency()))) &&
            ((this.loanBranchCode==null && other.getLoanBranchCode()==null) || 
             (this.loanBranchCode!=null &&
              this.loanBranchCode.equals(other.getLoanBranchCode()))) &&
            ((this.loanPrincipalPaid==null && other.getLoanPrincipalPaid()==null) || 
             (this.loanPrincipalPaid!=null &&
              this.loanPrincipalPaid.equals(other.getLoanPrincipalPaid()))) &&
            ((this.loanPrincipalDue==null && other.getLoanPrincipalDue()==null) || 
             (this.loanPrincipalDue!=null &&
              this.loanPrincipalDue.equals(other.getLoanPrincipalDue()))) &&
            ((this.loanInterestPaid==null && other.getLoanInterestPaid()==null) || 
             (this.loanInterestPaid!=null &&
              this.loanInterestPaid.equals(other.getLoanInterestPaid()))) &&
            ((this.loanPernalPrinDue==null && other.getLoanPernalPrinDue()==null) || 
             (this.loanPernalPrinDue!=null &&
              this.loanPernalPrinDue.equals(other.getLoanPernalPrinDue()))) &&
            ((this.loanPernalIntDue==null && other.getLoanPernalIntDue()==null) || 
             (this.loanPernalIntDue!=null &&
              this.loanPernalIntDue.equals(other.getLoanPernalIntDue()))) &&
            ((this.loanDisbursmentAmount==null && other.getLoanDisbursmentAmount()==null) || 
             (this.loanDisbursmentAmount!=null &&
              this.loanDisbursmentAmount.equals(other.getLoanDisbursmentAmount()))) &&
            ((this.loanInterestDue==null && other.getLoanInterestDue()==null) || 
             (this.loanInterestDue!=null &&
              this.loanInterestDue.equals(other.getLoanInterestDue()))) &&
            ((this.loanContract==null && other.getLoanContract()==null) || 
             (this.loanContract!=null &&
              this.loanContract.equals(other.getLoanContract()))) &&
            ((this.loanLimit==null && other.getLoanLimit()==null) || 
             (this.loanLimit!=null &&
              this.loanLimit.equals(other.getLoanLimit()))) &&
            ((this.loanContractValue==null && other.getLoanContractValue()==null) || 
             (this.loanContractValue!=null &&
              this.loanContractValue.equals(other.getLoanContractValue()))) &&
            ((this.loanFirstDisbursmentDate==null && other.getLoanFirstDisbursmentDate()==null) || 
             (this.loanFirstDisbursmentDate!=null &&
              this.loanFirstDisbursmentDate.equals(other.getLoanFirstDisbursmentDate()))) &&
            ((this.loanInterestAccrual==null && other.getLoanInterestAccrual()==null) || 
             (this.loanInterestAccrual!=null &&
              this.loanInterestAccrual.equals(other.getLoanInterestAccrual()))) &&
            ((this.staff==null && other.getStaff()==null) || 
             (this.staff!=null &&
              this.staff.equals(other.getStaff()))) &&
            ((this.delegation==null && other.getDelegation()==null) || 
             (this.delegation!=null &&
              this.delegation.equals(other.getDelegation())));
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
        if (getCIFInfo() != null) {
            _hashCode += getCIFInfo().hashCode();
        }
        if (getCustomerInfo() != null) {
            _hashCode += getCustomerInfo().hashCode();
        }
        if (getLoanAccountNum() != null) {
            _hashCode += getLoanAccountNum().hashCode();
        }
        if (getAccountBalanceEQV() != null) {
            _hashCode += getAccountBalanceEQV().hashCode();
        }
        if (getLoanInitialBalance() != null) {
            _hashCode += getLoanInitialBalance().hashCode();
        }
        if (getLoanProductCode() != null) {
            _hashCode += getLoanProductCode().hashCode();
        }
        if (getLoanProductName() != null) {
            _hashCode += getLoanProductName().hashCode();
        }
        if (getLoanOutstandingBalance() != null) {
            _hashCode += getLoanOutstandingBalance().hashCode();
        }
        if (getLoanInterestRate() != null) {
            _hashCode += getLoanInterestRate().hashCode();
        }
        if (getLoanEffectDate() != null) {
            _hashCode += getLoanEffectDate().hashCode();
        }
        if (getLoanMaturityDate() != null) {
            _hashCode += getLoanMaturityDate().hashCode();
        }
        if (getLoanCurrency() != null) {
            _hashCode += getLoanCurrency().hashCode();
        }
        if (getLoanBranchCode() != null) {
            _hashCode += getLoanBranchCode().hashCode();
        }
        if (getLoanPrincipalPaid() != null) {
            _hashCode += getLoanPrincipalPaid().hashCode();
        }
        if (getLoanPrincipalDue() != null) {
            _hashCode += getLoanPrincipalDue().hashCode();
        }
        if (getLoanInterestPaid() != null) {
            _hashCode += getLoanInterestPaid().hashCode();
        }
        if (getLoanPernalPrinDue() != null) {
            _hashCode += getLoanPernalPrinDue().hashCode();
        }
        if (getLoanPernalIntDue() != null) {
            _hashCode += getLoanPernalIntDue().hashCode();
        }
        if (getLoanDisbursmentAmount() != null) {
            _hashCode += getLoanDisbursmentAmount().hashCode();
        }
        if (getLoanInterestDue() != null) {
            _hashCode += getLoanInterestDue().hashCode();
        }
        if (getLoanContract() != null) {
            _hashCode += getLoanContract().hashCode();
        }
        if (getLoanLimit() != null) {
            _hashCode += getLoanLimit().hashCode();
        }
        if (getLoanContractValue() != null) {
            _hashCode += getLoanContractValue().hashCode();
        }
        if (getLoanFirstDisbursmentDate() != null) {
            _hashCode += getLoanFirstDisbursmentDate().hashCode();
        }
        if (getLoanInterestAccrual() != null) {
            _hashCode += getLoanInterestAccrual().hashCode();
        }
        if (getStaff() != null) {
            _hashCode += getStaff().hashCode();
        }
        if (getDelegation() != null) {
            _hashCode += getDelegation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LoanAccountInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "LoanAccountInfoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CIFInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CIFInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CIFDataType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customerInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CustomerInfoType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanAccountNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanAccountNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountBalanceEQV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountBalanceEQV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanInitialBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanInitialBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanProductCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanProductCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanProductName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanProductName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanOutstandingBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanOutstandingBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanInterestRate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanInterestRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanEffectDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanEffectDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanMaturityDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanMaturityDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanBranchCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanBranchCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanPrincipalPaid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanPrincipalPaid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanPrincipalDue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanPrincipalDue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanInterestPaid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanInterestPaid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanPernalPrinDue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanPernalPrinDue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanPernalIntDue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanPernalIntDue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanDisbursmentAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanDisbursmentAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanInterestDue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanInterestDue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanContract");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanContract"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanContractValue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanContractValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanFirstDisbursmentDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanFirstDisbursmentDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loanInterestAccrual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loanInterestAccrual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("staff");
        elemField.setXmlName(new javax.xml.namespace.QName("", "staff"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "StaffType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delegation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "delegation"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "DelegationType"));
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
