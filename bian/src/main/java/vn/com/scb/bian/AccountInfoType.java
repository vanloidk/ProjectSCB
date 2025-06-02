/**
 * AccountInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

import com.google.gson.annotations.Expose;
import java.math.BigDecimal;


/**
 * Số tài khoản 
 */
public class AccountInfoType  implements java.io.Serializable {
     
    private vn.com.scb.bian.CIFDataType CIFInfo;

     
    private vn.com.scb.bian.CustomerInfoType customerInfo;

    /* Số tài khoản */
    private java.lang.String accountNum;

    /* Ghi chú về tài khoản, nội dung do SCB nhập */
    private java.lang.String accountComment;

    /* Tên tài khoản */
    private java.lang.String accountName;

    /* Loại tài khoản (thanh toán, tiết kiệm…) */
    private java.lang.String accountType;

    /* Tên loại tài khoản */
    private java.lang.String accountTypeName;

    /* Loại tiền trong tài khoản */    
    private java.lang.String accountCurrency;

    /* Số tiền gửi vào */    
    private  BigDecimal accountOpeningAmount;

    /* Số dư tài khoản */    
    private  BigDecimal accountBalance;

    /* Số dư có thể sử dụng */
    private  BigDecimal accountBalanceAvailable;

    /* Số dư quy đổi ra tiền Việt */    
    private  BigDecimal accountExchangeBalanceEQV;

    /* Ngày mở tài khoản */
    private java.lang.String accountOpenDate;

    /* Ngày đến hạn */
    
    private java.lang.String accountMaturityDate;

    /* Tình trạng tài khoản (đóng, mở) */
    private java.lang.String accountStatus;

    /* Tên sản phẩm. Ví dụ: Tiết kiệm thông thường,
     *         				phát lộc phát tài… */
    private java.lang.String accountClassName;

    /* Mã sản phẩm */
    
    private java.lang.String accountClassCode;

    /* Lãi suất */
    private java.lang.String accountInterestRate;

    /* Đơn vị mở tài khoản */
    
    private java.lang.String accountOpenBrandCode;
    
      /* Đơn vị mở tài khoản */
    private java.lang.String accountOpenBrandName;

    /* Ngày giao dịch gần nhất */
    private java.lang.String accountLatestTransDate;

    /* Ngày cấp thấu chi */
    private java.lang.String accountOverdraftDate;

    /* Ngày hết hạn */
    private java.lang.String accountOverdraftExpiredDate;

    /* Hạn mức thấu chi */
    private java.lang.String accountOverdraftLimit;

    /* Tình trạng duyệt tài khoản */
    private java.lang.String accountAuthorizedStatus;

    /* Tên người được ủy quyền */
    private java.lang.String accountDelegatedPerson;

    /* Tên người đồng chủ sở hữu */
    private java.lang.String accountCoownerName;

    /* Kỳ hạn, 1 tháng ,2 tháng… Dành cho tài khoản
     *         				tiết kiệm */
    private java.lang.String accountTerm;

    /* Lãi dự chi - dự thu */
    private java.lang.String accountAccrued;

    /* lãi đã phân bổ */
    private java.lang.String savingApplicationInterest;

    /* Số Series Sổ tiết kiệm */
    private java.lang.String accountSavingSerials;

    /* Số tiền tái ký */
    private java.lang.String accountRolloverAmount;

    /* Mã nhân viên kinh doanh */
    private java.lang.String accountStaffCode;

    /* Số tài khoản nguồn */
    private java.lang.String srcAccountNum;

    /* Thông tin user nhập vào liên quan đến tài khoản
     *         				tiết kiệm */
    private java.lang.String accountNarrative;
    
    private vn.com.scb.bian.PayoutType payout;

    private vn.com.scb.bian.RedemptionType redemption;

    /* Dùng cho hàm terminateDepositAccount 1 phần: MP
     *         				toàn phần: TP chuyển khoản: CK */
    private java.lang.String terminationType;

    /* Số tiền có thể nhận được khi đến ngày tất toán
     *         				tài khoản. */
    private java.lang.String accountExpectedAmount;
    
    private vn.com.scb.bian.PayinType payin;

    /* ngày mở tk tiết kiệm danh nghĩa */
    private java.lang.String accountOfficialOpenDate;

    /* Hình thích lĩnh lãi */
    private java.lang.String accountInterestReceivableType;

    /* ngày lĩnh lãi định kỳ gần nhất */
    private java.lang.String accountLatestReceivableDate;

    /* Lý do phong tỏa tài khoản */
    private java.lang.String accountLockReason;

    /* ngày phong tỏa */
    private java.lang.String accountLockDate;

    /* ngày hết hạn phong tỏa */
    private java.lang.String accountLockExpireDate;

    /* Trạng thái tài khoản (phong tỏa hoặc không) */
    private java.lang.String accountLockStatus;

    /* đảm bảo cho các khoản vay nào (cầm cố hay thông
     *         				thường?) */
    private java.lang.String accountMorgateType;

    /* có bị hạn chế cho t/hợp vay cầm cố không? */
    private java.lang.String accountMorgateLimit;

    /* loại hình hạn chế trong vay cầm cố.STK loai hình
     *         				(accountClass) là trái phiếu hay tích lũy ==>
     *         				output = 'Y' */
    private java.lang.String accountMorgateLimitType;

    /* giá trị ngoại bảng đã nhập cho TK tiết kiệm này */
    private java.lang.String accountSheetBalance;

    /* nhóm nợ theo TK tiết kiệm này làm tsđb */
    private java.lang.String accountDebtGroup;

    /* Tài khoản trả lãi định kỳ */
     
    private java.lang.String tdBookAccount;

    /* Đơn vị tài khoản trả lãi */
      
    private java.lang.String bookAccBRN;

    /* Loại hình tái ký ( Y: tái ký; N: không tái ký) */
     
    private java.lang.String accountAutoRollType;

    /* Loại tái ký (P: tái ký gốc; I: tái ký gốc và
     *         				lãi) */
    
    private java.lang.String accountRollType;

     /* Số tiền tối thiểu của loại hình*/
    
    private java.lang.String accountClassMinBalance;
    
    /* Chỉ thị khi đáo hạn*/
    
    private java.lang.String accountDesignate;
    
    /* Số tài khoản ảo */
    private java.lang.String accountVirtualNum;
    
    /* Đối tác */
    private java.lang.String accountPartnerBankName;

    /* Cấp số dự thưởng Str */
    private java.lang.String accountInfoStr;
        
    public AccountInfoType() {
    }

    public AccountInfoType(
           vn.com.scb.bian.CIFDataType CIFInfo,
           vn.com.scb.bian.CustomerInfoType customerInfo,
           java.lang.String accountNum,
           java.lang.String accountComment,
           java.lang.String accountName,
           java.lang.String accountType,
           java.lang.String accountTypeName,
           java.lang.String accountCurrency,
           BigDecimal accountOpeningAmount,
           BigDecimal accountBalance,
           BigDecimal accountBalanceAvailable,
           BigDecimal accountExchangeBalanceEQV,
           java.lang.String accountOpenDate,
           java.lang.String accountMaturityDate,
           java.lang.String accountStatus,
           java.lang.String accountClassName,
           java.lang.String accountClassCode,
           java.lang.String accountInterestRate,
           java.lang.String accountOpenBrandCode,
           java.lang.String accountOpenBrandName,
           java.lang.String accountLatestTransDate,
           java.lang.String accountOverdraftDate,
           java.lang.String accountOverdraftExpiredDate,
           java.lang.String accountOverdraftLimit,
           java.lang.String accountAuthorizedStatus,
           java.lang.String accountDelegatedPerson,
           java.lang.String accountCoownerName,
           java.lang.String accountTerm,
           java.lang.String accountAccrued,
           java.lang.String savingApplicationInterest,
           java.lang.String accountSavingSerials,
           java.lang.String accountRolloverAmount,
           java.lang.String accountStaffCode,
           java.lang.String srcAccountNum,
           java.lang.String accountNarrative,
           vn.com.scb.bian.PayoutType payout,
           vn.com.scb.bian.RedemptionType redemption,
           java.lang.String terminationType,
           java.lang.String accountExpectedAmount,
           vn.com.scb.bian.PayinType payin,
           java.lang.String accountOfficialOpenDate,
           java.lang.String accountInterestReceivableType,
           java.lang.String accountLatestReceivableDate,
           java.lang.String accountLockReason,
           java.lang.String accountLockDate,
           java.lang.String accountLockExpireDate,
           java.lang.String accountLockStatus,
           java.lang.String accountMorgateType,
           java.lang.String accountMorgateLimit,
           java.lang.String accountMorgateLimitType,
           java.lang.String accountSheetBalance,
           java.lang.String accountDebtGroup,
           java.lang.String tdBookAccount,
           java.lang.String bookAccBRN,
           java.lang.String accountAutoRollType,
           java.lang.String accountRollType,
           java.lang.String accountClassMinBalance,
           java.lang.String accountDesignate,
           java.lang.String accountInfoStr) {
           this.CIFInfo = CIFInfo;
           this.customerInfo = customerInfo;
           this.accountNum = accountNum;
           this.accountComment = accountComment;
           this.accountName = accountName;
           this.accountType = accountType;
           this.accountTypeName = accountTypeName;
           this.accountCurrency = accountCurrency;
           this.accountOpeningAmount = accountOpeningAmount;
           this.accountBalance = accountBalance;
           this.accountBalanceAvailable = accountBalanceAvailable;
           this.accountExchangeBalanceEQV = accountExchangeBalanceEQV;
           this.accountOpenDate = accountOpenDate;
           this.accountMaturityDate = accountMaturityDate;
           this.accountStatus = accountStatus;
           this.accountClassName = accountClassName;
           this.accountClassCode = accountClassCode;
           this.accountInterestRate = accountInterestRate;
           this.accountOpenBrandCode = accountOpenBrandCode;
           this.accountOpenBrandName = accountOpenBrandName;
           this.accountLatestTransDate = accountLatestTransDate;
           this.accountOverdraftDate = accountOverdraftDate;
           this.accountOverdraftExpiredDate = accountOverdraftExpiredDate;
           this.accountOverdraftLimit = accountOverdraftLimit;
           this.accountAuthorizedStatus = accountAuthorizedStatus;
           this.accountDelegatedPerson = accountDelegatedPerson;
           this.accountCoownerName = accountCoownerName;
           this.accountTerm = accountTerm;
           this.accountAccrued = accountAccrued;
           this.savingApplicationInterest = savingApplicationInterest;
           this.accountSavingSerials = accountSavingSerials;
           this.accountRolloverAmount = accountRolloverAmount;
           this.accountStaffCode = accountStaffCode;
           this.srcAccountNum = srcAccountNum;
           this.accountNarrative = accountNarrative;
           this.payout = payout;
           this.redemption = redemption;
           this.terminationType = terminationType;
           this.accountExpectedAmount = accountExpectedAmount;
           this.payin = payin;
           this.accountOfficialOpenDate = accountOfficialOpenDate;
           this.accountInterestReceivableType = accountInterestReceivableType;
           this.accountLatestReceivableDate = accountLatestReceivableDate;
           this.accountLockReason = accountLockReason;
           this.accountLockDate = accountLockDate;
           this.accountLockExpireDate = accountLockExpireDate;
           this.accountLockStatus = accountLockStatus;
           this.accountMorgateType = accountMorgateType;
           this.accountMorgateLimit = accountMorgateLimit;
           this.accountMorgateLimitType = accountMorgateLimitType;
           this.accountSheetBalance = accountSheetBalance;
           this.accountDebtGroup = accountDebtGroup;
           this.tdBookAccount = tdBookAccount;
           this.bookAccBRN = bookAccBRN;
           this.accountAutoRollType = accountAutoRollType;
           this.accountRollType = accountRollType;
           this.accountClassMinBalance = accountClassMinBalance;
           this.accountDesignate = accountDesignate;
           this.accountInfoStr = accountInfoStr;
    }


    /**
     * Gets the CIFInfo value for this AccountInfoType.
     * 
     * @return CIFInfo
     */
    public vn.com.scb.bian.CIFDataType getCIFInfo() {
        return CIFInfo;
    }


    /**
     * Sets the CIFInfo value for this AccountInfoType.
     * 
     * @param CIFInfo
     */
    public void setCIFInfo(vn.com.scb.bian.CIFDataType CIFInfo) {
        this.CIFInfo = CIFInfo;
    }


    /**
     * Gets the customerInfo value for this AccountInfoType.
     * 
     * @return customerInfo
     */
    public vn.com.scb.bian.CustomerInfoType getCustomerInfo() {
        return customerInfo;
    }


    /**
     * Sets the customerInfo value for this AccountInfoType.
     * 
     * @param customerInfo
     */
    public void setCustomerInfo(vn.com.scb.bian.CustomerInfoType customerInfo) {
        this.customerInfo = customerInfo;
    }


    /**
     * Gets the accountNum value for this AccountInfoType.
     * 
     * @return accountNum   * Số tài khoản
     */
    public java.lang.String getAccountNum() {
        return accountNum;
    }


    /**
     * Sets the accountNum value for this AccountInfoType.
     * 
     * @param accountNum   * Số tài khoản
     */
    public void setAccountNum(java.lang.String accountNum) {
        this.accountNum = accountNum;
    }


    /**
     * Gets the accountComment value for this AccountInfoType.
     * 
     * @return accountComment   * Ghi chú về tài khoản, nội dung do SCB nhập
     */
    public java.lang.String getAccountComment() {
        return accountComment;
    }


    /**
     * Sets the accountComment value for this AccountInfoType.
     * 
     * @param accountComment   * Ghi chú về tài khoản, nội dung do SCB nhập
     */
    public void setAccountComment(java.lang.String accountComment) {
        this.accountComment = accountComment;
    }


    /**
     * Gets the accountName value for this AccountInfoType.
     * 
     * @return accountName   * Tên tài khoản
     */
    public java.lang.String getAccountName() {
        return accountName;
    }


    /**
     * Sets the accountName value for this AccountInfoType.
     * 
     * @param accountName   * Tên tài khoản
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }


    /**
     * Gets the accountType value for this AccountInfoType.
     * 
     * @return accountType   * Loại tài khoản (thanh toán, tiết kiệm…)
     */
    public java.lang.String getAccountType() {
        return accountType;
    }


    /**
     * Sets the accountType value for this AccountInfoType.
     * 
     * @param accountType   * Loại tài khoản (thanh toán, tiết kiệm…)
     */
    public void setAccountType(java.lang.String accountType) {
        this.accountType = accountType;
    }


    /**
     * Gets the accountTypeName value for this AccountInfoType.
     * 
     * @return accountTypeName   * Tên loại tài khoản
     */
    public java.lang.String getAccountTypeName() {
        return accountTypeName;
    }


    /**
     * Sets the accountTypeName value for this AccountInfoType.
     * 
     * @param accountTypeName   * Tên loại tài khoản
     */
    public void setAccountTypeName(java.lang.String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }


    /**
     * Gets the accountCurrency value for this AccountInfoType.
     * 
     * @return accountCurrency   * Loại tiền trong tài khoản
     */
    public java.lang.String getAccountCurrency() {
        return accountCurrency;
    }


    /**
     * Sets the accountCurrency value for this AccountInfoType.
     * 
     * @param accountCurrency   * Loại tiền trong tài khoản
     */
    public void setAccountCurrency(java.lang.String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }


    /**
     * Gets the accountOpeningAmount value for this AccountInfoType.
     * 
     * @return accountOpeningAmount   * Số tiền gửi vào
     */
    public BigDecimal getAccountOpeningAmount() {
        return accountOpeningAmount;
    }


    /**
     * Sets the accountOpeningAmount value for this AccountInfoType.
     * 
     * @param accountOpeningAmount   * Số tiền gửi vào
     */
    public void setAccountOpeningAmount(BigDecimal accountOpeningAmount) {
        this.accountOpeningAmount = accountOpeningAmount;
    }


    /**
     * Gets the accountBalance value for this AccountInfoType.
     * 
     * @return accountBalance   * Số dư tài khoản
     */
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }


    /**
     * Sets the accountBalance value for this AccountInfoType.
     * 
     * @param accountBalance   * Số dư tài khoản
     */
    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }


    /**
     * Gets the accountBalanceAvailable value for this AccountInfoType.
     * 
     * @return accountBalanceAvailable   * Số dư có thể sử dụng
     */
    public BigDecimal getAccountBalanceAvailable() {
        return accountBalanceAvailable;
    }


    /**
     * Sets the accountBalanceAvailable value for this AccountInfoType.
     * 
     * @param accountBalanceAvailable   * Số dư có thể sử dụng
     */
    public void setAccountBalanceAvailable(BigDecimal accountBalanceAvailable) {
        this.accountBalanceAvailable = accountBalanceAvailable;
    }


    /**
     * Gets the accountExchangeBalanceEQV value for this AccountInfoType.
     * 
     * @return accountExchangeBalanceEQV   * Số dư quy đổi ra tiền Việt
     */
    public BigDecimal getAccountExchangeBalanceEQV() {
        return accountExchangeBalanceEQV;
    }


    /**
     * Sets the accountExchangeBalanceEQV value for this AccountInfoType.
     * 
     * @param accountExchangeBalanceEQV   * Số dư quy đổi ra tiền Việt
     */
    public void setAccountExchangeBalanceEQV(BigDecimal accountExchangeBalanceEQV) {
        this.accountExchangeBalanceEQV = accountExchangeBalanceEQV;
    }


    /**
     * Gets the accountOpenDate value for this AccountInfoType.
     * 
     * @return accountOpenDate   * Ngày mở tài khoản
     */
    public java.lang.String getAccountOpenDate() {
        return accountOpenDate;
    }


    /**
     * Sets the accountOpenDate value for this AccountInfoType.
     * 
     * @param accountOpenDate   * Ngày mở tài khoản
     */
    public void setAccountOpenDate(java.lang.String accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
    }


    /**
     * Gets the accountMaturityDate value for this AccountInfoType.
     * 
     * @return accountMaturityDate   * Ngày đến hạn
     */
    public java.lang.String getAccountMaturityDate() {
        return accountMaturityDate;
    }


    /**
     * Sets the accountMaturityDate value for this AccountInfoType.
     * 
     * @param accountMaturityDate   * Ngày đến hạn
     */
    public void setAccountMaturityDate(java.lang.String accountMaturityDate) {
        this.accountMaturityDate = accountMaturityDate;
    }


    /**
     * Gets the accountStatus value for this AccountInfoType.
     * 
     * @return accountStatus   * Tình trạng tài khoản (đóng, mở)
     */
    public java.lang.String getAccountStatus() {
        return accountStatus;
    }


    /**
     * Sets the accountStatus value for this AccountInfoType.
     * 
     * @param accountStatus   * Tình trạng tài khoản (đóng, mở)
     */
    public void setAccountStatus(java.lang.String accountStatus) {
        this.accountStatus = accountStatus;
    }


    /**
     * Gets the accountClassName value for this AccountInfoType.
     * 
     * @return accountClassName   * Tên sản phẩm. Ví dụ: Tiết kiệm thông thường,
     *         				phát lộc phát tài…
     */
    public java.lang.String getAccountClassName() {
        return accountClassName;
    }


    /**
     * Sets the accountClassName value for this AccountInfoType.
     * 
     * @param accountClassName   * Tên sản phẩm. Ví dụ: Tiết kiệm thông thường,
     *         				phát lộc phát tài…
     */
    public void setAccountClassName(java.lang.String accountClassName) {
        this.accountClassName = accountClassName;
    }


    /**
     * Gets the accountClassCode value for this AccountInfoType.
     * 
     * @return accountClassCode   * Mã sản phẩm
     */
    public java.lang.String getAccountClassCode() {
        return accountClassCode;
    }


    /**
     * Sets the accountClassCode value for this AccountInfoType.
     * 
     * @param accountClassCode   * Mã sản phẩm
     */
    public void setAccountClassCode(java.lang.String accountClassCode) {
        this.accountClassCode = accountClassCode;
    }


    /**
     * Gets the accountInterestRate value for this AccountInfoType.
     * 
     * @return accountInterestRate   * Lãi suất
     */
    public java.lang.String getAccountInterestRate() {
        return accountInterestRate;
    }


    /**
     * Sets the accountInterestRate value for this AccountInfoType.
     * 
     * @param accountInterestRate   * Lãi suất
     */
    public void setAccountInterestRate(java.lang.String accountInterestRate) {
        this.accountInterestRate = accountInterestRate;
    }


    /**
     * Gets the accountOpenBrandCode value for this AccountInfoType.
     * 
     * @return accountOpenBrandCode   * Đơn vị mở tài khoản
     */
    public java.lang.String getAccountOpenBrandCode() {
        return accountOpenBrandCode;
    }


    /**
     * Sets the accountOpenBrandCode value for this AccountInfoType.
     * 
     * @param accountOpenBrandCode   * Đơn vị mở tài khoản
     */
    public void setAccountOpenBrandCode(java.lang.String accountOpenBrandCode) {
        this.accountOpenBrandCode = accountOpenBrandCode;
    }


    /**
     * Gets the accountLatestTransDate value for this AccountInfoType.
     * 
     * @return accountLatestTransDate   * Ngày giao dịch gần nhất
     */
    public java.lang.String getAccountLatestTransDate() {
        return accountLatestTransDate;
    }


    /**
     * Sets the accountLatestTransDate value for this AccountInfoType.
     * 
     * @param accountLatestTransDate   * Ngày giao dịch gần nhất
     */
    public void setAccountLatestTransDate(java.lang.String accountLatestTransDate) {
        this.accountLatestTransDate = accountLatestTransDate;
    }


    /**
     * Gets the accountOverdraftDate value for this AccountInfoType.
     * 
     * @return accountOverdraftDate   * Ngày cấp thấu chi
     */
    public java.lang.String getAccountOverdraftDate() {
        return accountOverdraftDate;
    }


    /**
     * Sets the accountOverdraftDate value for this AccountInfoType.
     * 
     * @param accountOverdraftDate   * Ngày cấp thấu chi
     */
    public void setAccountOverdraftDate(java.lang.String accountOverdraftDate) {
        this.accountOverdraftDate = accountOverdraftDate;
    }


    /**
     * Gets the accountOverdraftExpiredDate value for this AccountInfoType.
     * 
     * @return accountOverdraftExpiredDate   * Ngày hết hạn
     */
    public java.lang.String getAccountOverdraftExpiredDate() {
        return accountOverdraftExpiredDate;
    }


    /**
     * Sets the accountOverdraftExpiredDate value for this AccountInfoType.
     * 
     * @param accountOverdraftExpiredDate   * Ngày hết hạn
     */
    public void setAccountOverdraftExpiredDate(java.lang.String accountOverdraftExpiredDate) {
        this.accountOverdraftExpiredDate = accountOverdraftExpiredDate;
    }


    /**
     * Gets the accountOverdraftLimit value for this AccountInfoType.
     * 
     * @return accountOverdraftLimit   * Hạn mức thấu chi
     */
    public java.lang.String getAccountOverdraftLimit() {
        return accountOverdraftLimit;
    }


    /**
     * Sets the accountOverdraftLimit value for this AccountInfoType.
     * 
     * @param accountOverdraftLimit   * Hạn mức thấu chi
     */
    public void setAccountOverdraftLimit(java.lang.String accountOverdraftLimit) {
        this.accountOverdraftLimit = accountOverdraftLimit;
    }


    /**
     * Gets the accountAuthorizedStatus value for this AccountInfoType.
     * 
     * @return accountAuthorizedStatus   * Tình trạng duyệt tài khoản
     */
    public java.lang.String getAccountAuthorizedStatus() {
        return accountAuthorizedStatus;
    }


    /**
     * Sets the accountAuthorizedStatus value for this AccountInfoType.
     * 
     * @param accountAuthorizedStatus   * Tình trạng duyệt tài khoản
     */
    public void setAccountAuthorizedStatus(java.lang.String accountAuthorizedStatus) {
        this.accountAuthorizedStatus = accountAuthorizedStatus;
    }


    /**
     * Gets the accountDelegatedPerson value for this AccountInfoType.
     * 
     * @return accountDelegatedPerson   * Tên người được ủy quyền
     */
    public java.lang.String getAccountDelegatedPerson() {
        return accountDelegatedPerson;
    }


    /**
     * Sets the accountDelegatedPerson value for this AccountInfoType.
     * 
     * @param accountDelegatedPerson   * Tên người được ủy quyền
     */
    public void setAccountDelegatedPerson(java.lang.String accountDelegatedPerson) {
        this.accountDelegatedPerson = accountDelegatedPerson;
    }


    /**
     * Gets the accountCoownerName value for this AccountInfoType.
     * 
     * @return accountCoownerName   * Tên người đồng chủ sở hữu
     */
    public java.lang.String getAccountCoownerName() {
        return accountCoownerName;
    }


    /**
     * Sets the accountCoownerName value for this AccountInfoType.
     * 
     * @param accountCoownerName   * Tên người đồng chủ sở hữu
     */
    public void setAccountCoownerName(java.lang.String accountCoownerName) {
        this.accountCoownerName = accountCoownerName;
    }


    /**
     * Gets the accountTerm value for this AccountInfoType.
     * 
     * @return accountTerm   * Kỳ hạn, 1 tháng ,2 tháng… Dành cho tài khoản
     *         				tiết kiệm
     */
    public java.lang.String getAccountTerm() {
        return accountTerm;
    }


    /**
     * Sets the accountTerm value for this AccountInfoType.
     * 
     * @param accountTerm   * Kỳ hạn, 1 tháng ,2 tháng… Dành cho tài khoản
     *         				tiết kiệm
     */
    public void setAccountTerm(java.lang.String accountTerm) {
        this.accountTerm = accountTerm;
    }


    /**
     * Gets the accountAccrued value for this AccountInfoType.
     * 
     * @return accountAccrued   * Lãi dự chi - dự thu
     */
    public java.lang.String getAccountAccrued() {
        return accountAccrued;
    }


    /**
     * Sets the accountAccrued value for this AccountInfoType.
     * 
     * @param accountAccrued   * Lãi dự chi - dự thu
     */
    public void setAccountAccrued(java.lang.String accountAccrued) {
        this.accountAccrued = accountAccrued;
    }


    /**
     * Gets the savingApplicationInterest value for this AccountInfoType.
     * 
     * @return savingApplicationInterest   * lãi đã phân bổ
     */
    public java.lang.String getSavingApplicationInterest() {
        return savingApplicationInterest;
    }


    /**
     * Sets the savingApplicationInterest value for this AccountInfoType.
     * 
     * @param savingApplicationInterest   * lãi đã phân bổ
     */
    public void setSavingApplicationInterest(java.lang.String savingApplicationInterest) {
        this.savingApplicationInterest = savingApplicationInterest;
    }


    /**
     * Gets the accountSavingSerials value for this AccountInfoType.
     * 
     * @return accountSavingSerials   * Số Series Sổ tiết kiệm
     */
    public java.lang.String getAccountSavingSerials() {
        return accountSavingSerials;
    }


    /**
     * Sets the accountSavingSerials value for this AccountInfoType.
     * 
     * @param accountSavingSerials   * Số Series Sổ tiết kiệm
     */
    public void setAccountSavingSerials(java.lang.String accountSavingSerials) {
        this.accountSavingSerials = accountSavingSerials;
    }


    /**
     * Gets the accountRolloverAmount value for this AccountInfoType.
     * 
     * @return accountRolloverAmount   * Số tiền tái ký
     */
    public java.lang.String getAccountRolloverAmount() {
        return accountRolloverAmount;
    }


    /**
     * Sets the accountRolloverAmount value for this AccountInfoType.
     * 
     * @param accountRolloverAmount   * Số tiền tái ký
     */
    public void setAccountRolloverAmount(java.lang.String accountRolloverAmount) {
        this.accountRolloverAmount = accountRolloverAmount;
    }


    /**
     * Gets the accountStaffCode value for this AccountInfoType.
     * 
     * @return accountStaffCode   * Mã nhân viên kinh doanh
     */
    public java.lang.String getAccountStaffCode() {
        return accountStaffCode;
    }


    /**
     * Sets the accountStaffCode value for this AccountInfoType.
     * 
     * @param accountStaffCode   * Mã nhân viên kinh doanh
     */
    public void setAccountStaffCode(java.lang.String accountStaffCode) {
        this.accountStaffCode = accountStaffCode;
    }


    /**
     * Gets the srcAccountNum value for this AccountInfoType.
     * 
     * @return srcAccountNum   * Số tài khoản nguồn
     */
    public java.lang.String getSrcAccountNum() {
        return srcAccountNum;
    }


    /**
     * Sets the srcAccountNum value for this AccountInfoType.
     * 
     * @param srcAccountNum   * Số tài khoản nguồn
     */
    public void setSrcAccountNum(java.lang.String srcAccountNum) {
        this.srcAccountNum = srcAccountNum;
    }


    /**
     * Gets the accountNarrative value for this AccountInfoType.
     * 
     * @return accountNarrative   * Thông tin user nhập vào liên quan đến tài khoản
     *         				tiết kiệm
     */
    public java.lang.String getAccountNarrative() {
        return accountNarrative;
    }


    /**
     * Sets the accountNarrative value for this AccountInfoType.
     * 
     * @param accountNarrative   * Thông tin user nhập vào liên quan đến tài khoản
     *         				tiết kiệm
     */
    public void setAccountNarrative(java.lang.String accountNarrative) {
        this.accountNarrative = accountNarrative;
    }


    /**
     * Gets the payout value for this AccountInfoType.
     * 
     * @return payout
     */
    public vn.com.scb.bian.PayoutType getPayout() {
        return payout;
    }


   


    /**
     * Gets the redemption value for this AccountInfoType.
     * 
     * @return redemption
     */
    public vn.com.scb.bian.RedemptionType getRedemption() {
        return redemption;
    }


    /**
     * Sets the redemption value for this AccountInfoType.
     * 
     * @param redemption
     */
    public void setRedemption(vn.com.scb.bian.RedemptionType redemption) {
        this.redemption = redemption;
    }


    /**
     * Gets the terminationType value for this AccountInfoType.
     * 
     * @return terminationType   * Dùng cho hàm terminateDepositAccount 1 phần: MP
     *         				toàn phần: TP chuyển khoản: CK
     */
    public java.lang.String getTerminationType() {
        return terminationType;
    }


    /**
     * Sets the terminationType value for this AccountInfoType.
     * 
     * @param terminationType   * Dùng cho hàm terminateDepositAccount 1 phần: MP
     *         				toàn phần: TP chuyển khoản: CK
     */
    public void setTerminationType(java.lang.String terminationType) {
        this.terminationType = terminationType;
    }


    /**
     * Gets the accountExpectedAmount value for this AccountInfoType.
     * 
     * @return accountExpectedAmount   * Số tiền có thể nhận được khi đến ngày tất toán
     *         				tài khoản.
     */
    public java.lang.String getAccountExpectedAmount() {
        return accountExpectedAmount;
    }


    /**
     * Sets the accountExpectedAmount value for this AccountInfoType.
     * 
     * @param accountExpectedAmount   * Số tiền có thể nhận được khi đến ngày tất toán
     *         				tài khoản.
     */
    public void setAccountExpectedAmount(java.lang.String accountExpectedAmount) {
        this.accountExpectedAmount = accountExpectedAmount;
    }
   
    /**
     * Gets the accountOfficialOpenDate value for this AccountInfoType.
     * 
     * @return accountOfficialOpenDate   * ngày mở tk tiết kiệm danh nghĩa
     */
    public java.lang.String getAccountOfficialOpenDate() {
        return accountOfficialOpenDate;
    }


    /**
     * Sets the accountOfficialOpenDate value for this AccountInfoType.
     * 
     * @param accountOfficialOpenDate   * ngày mở tk tiết kiệm danh nghĩa
     */
    public void setAccountOfficialOpenDate(java.lang.String accountOfficialOpenDate) {
        this.accountOfficialOpenDate = accountOfficialOpenDate;
    }


    /**
     * Gets the accountInterestReceivableType value for this AccountInfoType.
     * 
     * @return accountInterestReceivableType   * Hình thích lĩnh lãi
     */
    public java.lang.String getAccountInterestReceivableType() {
        return accountInterestReceivableType;
    }


    /**
     * Sets the accountInterestReceivableType value for this AccountInfoType.
     * 
     * @param accountInterestReceivableType   * Hình thích lĩnh lãi
     */
    public void setAccountInterestReceivableType(java.lang.String accountInterestReceivableType) {
        this.accountInterestReceivableType = accountInterestReceivableType;
    }


    /**
     * Gets the accountLatestReceivableDate value for this AccountInfoType.
     * 
     * @return accountLatestReceivableDate   * ngày lĩnh lãi định kỳ gần nhất
     */
    public java.lang.String getAccountLatestReceivableDate() {
        return accountLatestReceivableDate;
    }


    /**
     * Sets the accountLatestReceivableDate value for this AccountInfoType.
     * 
     * @param accountLatestReceivableDate   * ngày lĩnh lãi định kỳ gần nhất
     */
    public void setAccountLatestReceivableDate(java.lang.String accountLatestReceivableDate) {
        this.accountLatestReceivableDate = accountLatestReceivableDate;
    }


    /**
     * Gets the accountLockReason value for this AccountInfoType.
     * 
     * @return accountLockReason   * Lý do phong tỏa tài khoản
     */
    public java.lang.String getAccountLockReason() {
        return accountLockReason;
    }


    /**
     * Sets the accountLockReason value for this AccountInfoType.
     * 
     * @param accountLockReason   * Lý do phong tỏa tài khoản
     */
    public void setAccountLockReason(java.lang.String accountLockReason) {
        this.accountLockReason = accountLockReason;
    }


    /**
     * Gets the accountLockDate value for this AccountInfoType.
     * 
     * @return accountLockDate   * ngày phong tỏa
     */
    public java.lang.String getAccountLockDate() {
        return accountLockDate;
    }


    /**
     * Sets the accountLockDate value for this AccountInfoType.
     * 
     * @param accountLockDate   * ngày phong tỏa
     */
    public void setAccountLockDate(java.lang.String accountLockDate) {
        this.accountLockDate = accountLockDate;
    }


    /**
     * Gets the accountLockExpireDate value for this AccountInfoType.
     * 
     * @return accountLockExpireDate   * ngày hết hạn phong tỏa
     */
    public java.lang.String getAccountLockExpireDate() {
        return accountLockExpireDate;
    }


    /**
     * Sets the accountLockExpireDate value for this AccountInfoType.
     * 
     * @param accountLockExpireDate   * ngày hết hạn phong tỏa
     */
    public void setAccountLockExpireDate(java.lang.String accountLockExpireDate) {
        this.accountLockExpireDate = accountLockExpireDate;
    }


    /**
     * Gets the accountLockStatus value for this AccountInfoType.
     * 
     * @return accountLockStatus   * Trạng thái tài khoản (phong tỏa hoặc không)
     */
    public java.lang.String getAccountLockStatus() {
        return accountLockStatus;
    }


    /**
     * Sets the accountLockStatus value for this AccountInfoType.
     * 
     * @param accountLockStatus   * Trạng thái tài khoản (phong tỏa hoặc không)
     */
    public void setAccountLockStatus(java.lang.String accountLockStatus) {
        this.accountLockStatus = accountLockStatus;
    }


    /**
     * Gets the accountMorgateType value for this AccountInfoType.
     * 
     * @return accountMorgateType   * đảm bảo cho các khoản vay nào (cầm cố hay thông
     *         				thường?)
     */
    public java.lang.String getAccountMorgateType() {
        return accountMorgateType;
    }


    /**
     * Sets the accountMorgateType value for this AccountInfoType.
     * 
     * @param accountMorgateType   * đảm bảo cho các khoản vay nào (cầm cố hay thông
     *         				thường?)
     */
    public void setAccountMorgateType(java.lang.String accountMorgateType) {
        this.accountMorgateType = accountMorgateType;
    }


    /**
     * Gets the accountMorgateLimit value for this AccountInfoType.
     * 
     * @return accountMorgateLimit   * có bị hạn chế cho t/hợp vay cầm cố không?
     */
    public java.lang.String getAccountMorgateLimit() {
        return accountMorgateLimit;
    }


    /**
     * Sets the accountMorgateLimit value for this AccountInfoType.
     * 
     * @param accountMorgateLimit   * có bị hạn chế cho t/hợp vay cầm cố không?
     */
    public void setAccountMorgateLimit(java.lang.String accountMorgateLimit) {
        this.accountMorgateLimit = accountMorgateLimit;
    }


    /**
     * Gets the accountMorgateLimitType value for this AccountInfoType.
     * 
     * @return accountMorgateLimitType   * loại hình hạn chế trong vay cầm cố.STK loai hình
     *         				(accountClass) là trái phiếu hay tích lũy ==>
     *         				output = 'Y'
     */
    public java.lang.String getAccountMorgateLimitType() {
        return accountMorgateLimitType;
    }


    /**
     * Sets the accountMorgateLimitType value for this AccountInfoType.
     * 
     * @param accountMorgateLimitType   * loại hình hạn chế trong vay cầm cố.STK loai hình
     *         				(accountClass) là trái phiếu hay tích lũy ==>
     *         				output = 'Y'
     */
    public void setAccountMorgateLimitType(java.lang.String accountMorgateLimitType) {
        this.accountMorgateLimitType = accountMorgateLimitType;
    }


    /**
     * Gets the accountSheetBalance value for this AccountInfoType.
     * 
     * @return accountSheetBalance   * giá trị ngoại bảng đã nhập cho TK tiết kiệm này
     */
    public java.lang.String getAccountSheetBalance() {
        return accountSheetBalance;
    }


    /**
     * Sets the accountSheetBalance value for this AccountInfoType.
     * 
     * @param accountSheetBalance   * giá trị ngoại bảng đã nhập cho TK tiết kiệm này
     */
    public void setAccountSheetBalance(java.lang.String accountSheetBalance) {
        this.accountSheetBalance = accountSheetBalance;
    }


    /**
     * Gets the accountDebtGroup value for this AccountInfoType.
     * 
     * @return accountDebtGroup   * nhóm nợ theo TK tiết kiệm này làm tsđb
     */
    public java.lang.String getAccountDebtGroup() {
        return accountDebtGroup;
    }


    /**
     * Sets the accountDebtGroup value for this AccountInfoType.
     * 
     * @param accountDebtGroup   * nhóm nợ theo TK tiết kiệm này làm tsđb
     */
    public void setAccountDebtGroup(java.lang.String accountDebtGroup) {
        this.accountDebtGroup = accountDebtGroup;
    }


    /**
     * Gets the tdBookAccount value for this AccountInfoType.
     * 
     * @return tdBookAccount   * Tài khoản trả lãi định kỳ
     */
    public java.lang.String getTdBookAccount() {
        return tdBookAccount;
    }


    /**
     * Sets the tdBookAccount value for this AccountInfoType.
     * 
     * @param tdBookAccount   * Tài khoản trả lãi định kỳ
     */
    public void setTdBookAccount(java.lang.String tdBookAccount) {
        this.tdBookAccount = tdBookAccount;
    }


    /**
     * Gets the bookAccBRN value for this AccountInfoType.
     * 
     * @return bookAccBRN   * Đơn vị tài khoản trả lãi
     */
    public java.lang.String getBookAccBRN() {
        return bookAccBRN;
    }


    /**
     * Sets the bookAccBRN value for this AccountInfoType.
     * 
     * @param bookAccBRN   * Đơn vị tài khoản trả lãi
     */
    public void setBookAccBRN(java.lang.String bookAccBRN) {
        this.bookAccBRN = bookAccBRN;
    }


    /**
     * Gets the accountAutoRollType value for this AccountInfoType.
     * 
     * @return accountAutoRollType   * Loại hình tái ký ( Y: tái ký; N: không tái ký)
     */
    public java.lang.String getAccountAutoRollType() {
        return accountAutoRollType;
    }


    /**
     * Sets the accountAutoRollType value for this AccountInfoType.
     * 
     * @param accountAutoRollType   * Loại hình tái ký ( Y: tái ký; N: không tái ký)
     */
    public void setAccountAutoRollType(java.lang.String accountAutoRollType) {
        this.accountAutoRollType = accountAutoRollType;
    }


    /**
     * Gets the accountRollType value for this AccountInfoType.
     * 
     * @return accountRollType   * Loại tái ký (P: tái ký gốc; I: tái ký gốc và
     *         				lãi)
     */
    public java.lang.String getAccountRollType() {
        return accountRollType;
    }


    /**
     * Sets the accountRollType value for this AccountInfoType.
     * 
     * @param accountRollType   * Loại tái ký (P: tái ký gốc; I: tái ký gốc và
     *         				lãi)
     */
    public void setAccountRollType(java.lang.String accountRollType) {
        this.accountRollType = accountRollType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountInfoType)) return false;
        AccountInfoType other = (AccountInfoType) obj;
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
            ((this.accountNum==null && other.getAccountNum()==null) || 
             (this.accountNum!=null &&
              this.accountNum.equals(other.getAccountNum()))) &&
            ((this.accountComment==null && other.getAccountComment()==null) || 
             (this.accountComment!=null &&
              this.accountComment.equals(other.getAccountComment()))) &&
            ((this.accountName==null && other.getAccountName()==null) || 
             (this.accountName!=null &&
              this.accountName.equals(other.getAccountName()))) &&
            ((this.accountType==null && other.getAccountType()==null) || 
             (this.accountType!=null &&
              this.accountType.equals(other.getAccountType()))) &&
            ((this.accountTypeName==null && other.getAccountTypeName()==null) || 
             (this.accountTypeName!=null &&
              this.accountTypeName.equals(other.getAccountTypeName()))) &&
                
            ((this.accountCurrency==null && other.getAccountCurrency()==null) || 
             (this.accountCurrency!=null &&
              this.accountCurrency.equals(other.getAccountCurrency())))&&
                
            ((this.accountOpeningAmount==null && other.getAccountOpeningAmount()==null) || 
             (this.accountOpeningAmount!=null &&                
              this.accountOpeningAmount.equals(other.getAccountOpeningAmount())))&&
                
             ((this.accountBalance==null && other.getAccountBalance()==null) || 
             (this.accountBalance!=null &&                
              this.accountBalance.equals(other.getAccountBalance())))&&                
             ((this.accountBalanceAvailable==null && other.getAccountBalanceAvailable()==null) || 
             (this.accountBalanceAvailable!=null &&                
              this.accountBalanceAvailable.equals(other.getAccountBalanceAvailable())))&&
                
                /*
                BigDecimal.
                && ((Double.isNaN(this.accountOpeningAmount) && BigDecimal.isNaN(other.getAccountOpeningAmount()))
                || (!Double.isNaN(this.accountOpeningAmount)
                && this.accountOpeningAmount == other.getAccountOpeningAmount()))
                && ((Double.isNaN(this.accountBalance) && BigDecimal.isNaN(other.getAccountBalance()))
                || (!Double.isNaN(this.accountBalance)
                && this.accountBalance == other.getAccountBalance()))
                && ((Double.isNaN(this.accountBalanceAvailable) && BigDecimal.isNaN(other.getAccountBalanceAvailable()))
                || (!Double.isNaN(this.accountBalanceAvailable)
                && this.accountBalanceAvailable == other.getAccountBalanceAvailable()))
                && ((Double.isNaN(this.accountExchangeBalanceEQV) && BigDecimal.isNaN(other.getAccountExchangeBalanceEQV()))
                || (!Double.isNaN(this.accountExchangeBalanceEQV)
                && this.accountExchangeBalanceEQV == other.getAccountExchangeBalanceEQV()))*/
                
            ((this.accountOpenDate==null && other.getAccountOpenDate()==null) || 
             (this.accountOpenDate!=null &&
              this.accountOpenDate.equals(other.getAccountOpenDate()))) &&
            ((this.accountMaturityDate==null && other.getAccountMaturityDate()==null) || 
             (this.accountMaturityDate!=null &&
              this.accountMaturityDate.equals(other.getAccountMaturityDate()))) &&
            ((this.accountStatus==null && other.getAccountStatus()==null) || 
             (this.accountStatus!=null &&
              this.accountStatus.equals(other.getAccountStatus()))) &&
            ((this.accountClassName==null && other.getAccountClassName()==null) || 
             (this.accountClassName!=null &&
              this.accountClassName.equals(other.getAccountClassName()))) &&
            ((this.accountClassCode==null && other.getAccountClassCode()==null) || 
             (this.accountClassCode!=null &&
              this.accountClassCode.equals(other.getAccountClassCode()))) &&
            ((this.accountInterestRate==null && other.getAccountInterestRate()==null) || 
             (this.accountInterestRate!=null &&
              this.accountInterestRate.equals(other.getAccountInterestRate()))) &&
            ((this.accountOpenBrandCode==null && other.getAccountOpenBrandCode()==null) || 
             (this.accountOpenBrandCode!=null &&
              this.accountOpenBrandCode.equals(other.getAccountOpenBrandCode()))) &&
                ((this.accountOpenBrandName==null && other.getAccountOpenBrandName()==null) || 
             (this.accountOpenBrandName!=null &&
              this.accountOpenBrandName.equals(other.getAccountOpenBrandName()))) &&
            ((this.accountLatestTransDate==null && other.getAccountLatestTransDate()==null) || 
             (this.accountLatestTransDate!=null &&
              this.accountLatestTransDate.equals(other.getAccountLatestTransDate()))) &&
            ((this.accountOverdraftDate==null && other.getAccountOverdraftDate()==null) || 
             (this.accountOverdraftDate!=null &&
              this.accountOverdraftDate.equals(other.getAccountOverdraftDate()))) &&
            ((this.accountOverdraftExpiredDate==null && other.getAccountOverdraftExpiredDate()==null) || 
             (this.accountOverdraftExpiredDate!=null &&
              this.accountOverdraftExpiredDate.equals(other.getAccountOverdraftExpiredDate()))) &&
            ((this.accountOverdraftLimit==null && other.getAccountOverdraftLimit()==null) || 
             (this.accountOverdraftLimit!=null &&
              this.accountOverdraftLimit.equals(other.getAccountOverdraftLimit()))) &&
            ((this.accountAuthorizedStatus==null && other.getAccountAuthorizedStatus()==null) || 
             (this.accountAuthorizedStatus!=null &&
              this.accountAuthorizedStatus.equals(other.getAccountAuthorizedStatus()))) &&
            ((this.accountDelegatedPerson==null && other.getAccountDelegatedPerson()==null) || 
             (this.accountDelegatedPerson!=null &&
              this.accountDelegatedPerson.equals(other.getAccountDelegatedPerson()))) &&
            ((this.accountCoownerName==null && other.getAccountCoownerName()==null) || 
             (this.accountCoownerName!=null &&
              this.accountCoownerName.equals(other.getAccountCoownerName()))) &&
            ((this.accountTerm==null && other.getAccountTerm()==null) || 
             (this.accountTerm!=null &&
              this.accountTerm.equals(other.getAccountTerm()))) &&
            ((this.accountAccrued==null && other.getAccountAccrued()==null) || 
             (this.accountAccrued!=null &&
              this.accountAccrued.equals(other.getAccountAccrued()))) &&
            ((this.savingApplicationInterest==null && other.getSavingApplicationInterest()==null) || 
             (this.savingApplicationInterest!=null &&
              this.savingApplicationInterest.equals(other.getSavingApplicationInterest()))) &&
            ((this.accountSavingSerials==null && other.getAccountSavingSerials()==null) || 
             (this.accountSavingSerials!=null &&
              this.accountSavingSerials.equals(other.getAccountSavingSerials()))) &&
            ((this.accountRolloverAmount==null && other.getAccountRolloverAmount()==null) || 
             (this.accountRolloverAmount!=null &&
              this.accountRolloverAmount.equals(other.getAccountRolloverAmount()))) &&
            ((this.accountStaffCode==null && other.getAccountStaffCode()==null) || 
             (this.accountStaffCode!=null &&
              this.accountStaffCode.equals(other.getAccountStaffCode()))) &&
            ((this.srcAccountNum==null && other.getSrcAccountNum()==null) || 
             (this.srcAccountNum!=null &&
              this.srcAccountNum.equals(other.getSrcAccountNum()))) &&
            ((this.accountNarrative==null && other.getAccountNarrative()==null) || 
             (this.accountNarrative!=null &&
              this.accountNarrative.equals(other.getAccountNarrative()))) &&
            ((this.getPayout()==null && other.getPayout()==null) || 
             (this.getPayout()!=null &&
              this.getPayout().equals(other.getPayout()))) &&
            ((this.redemption==null && other.getRedemption()==null) || 
             (this.redemption!=null &&
              this.redemption.equals(other.getRedemption()))) &&
            ((this.terminationType==null && other.getTerminationType()==null) || 
             (this.terminationType!=null &&
              this.terminationType.equals(other.getTerminationType()))) &&
            ((this.accountExpectedAmount==null && other.getAccountExpectedAmount()==null) || 
             (this.accountExpectedAmount!=null &&
              this.accountExpectedAmount.equals(other.getAccountExpectedAmount()))) &&
            ((this.getPayin()==null && other.getPayin()==null) || 
             (this.getPayin()!=null &&
              this.getPayin().equals(other.getPayin()))) &&
            ((this.accountOfficialOpenDate==null && other.getAccountOfficialOpenDate()==null) || 
             (this.accountOfficialOpenDate!=null &&
              this.accountOfficialOpenDate.equals(other.getAccountOfficialOpenDate()))) &&
            ((this.accountInterestReceivableType==null && other.getAccountInterestReceivableType()==null) || 
             (this.accountInterestReceivableType!=null &&
              this.accountInterestReceivableType.equals(other.getAccountInterestReceivableType()))) &&
            ((this.accountLatestReceivableDate==null && other.getAccountLatestReceivableDate()==null) || 
             (this.accountLatestReceivableDate!=null &&
              this.accountLatestReceivableDate.equals(other.getAccountLatestReceivableDate()))) &&
            ((this.accountLockReason==null && other.getAccountLockReason()==null) || 
             (this.accountLockReason!=null &&
              this.accountLockReason.equals(other.getAccountLockReason()))) &&
            ((this.accountLockDate==null && other.getAccountLockDate()==null) || 
             (this.accountLockDate!=null &&
              this.accountLockDate.equals(other.getAccountLockDate()))) &&
            ((this.accountLockExpireDate==null && other.getAccountLockExpireDate()==null) || 
             (this.accountLockExpireDate!=null &&
              this.accountLockExpireDate.equals(other.getAccountLockExpireDate()))) &&
            ((this.accountLockStatus==null && other.getAccountLockStatus()==null) || 
             (this.accountLockStatus!=null &&
              this.accountLockStatus.equals(other.getAccountLockStatus()))) &&
            ((this.accountMorgateType==null && other.getAccountMorgateType()==null) || 
             (this.accountMorgateType!=null &&
              this.accountMorgateType.equals(other.getAccountMorgateType()))) &&
            ((this.accountMorgateLimit==null && other.getAccountMorgateLimit()==null) || 
             (this.accountMorgateLimit!=null &&
              this.accountMorgateLimit.equals(other.getAccountMorgateLimit()))) &&
            ((this.accountMorgateLimitType==null && other.getAccountMorgateLimitType()==null) || 
             (this.accountMorgateLimitType!=null &&
              this.accountMorgateLimitType.equals(other.getAccountMorgateLimitType()))) &&
            ((this.accountSheetBalance==null && other.getAccountSheetBalance()==null) || 
             (this.accountSheetBalance!=null &&
              this.accountSheetBalance.equals(other.getAccountSheetBalance()))) &&
            ((this.accountDebtGroup==null && other.getAccountDebtGroup()==null) || 
             (this.accountDebtGroup!=null &&
              this.accountDebtGroup.equals(other.getAccountDebtGroup()))) &&
            ((this.tdBookAccount==null && other.getTdBookAccount()==null) || 
             (this.tdBookAccount!=null &&
              this.tdBookAccount.equals(other.getTdBookAccount()))) &&
            ((this.bookAccBRN==null && other.getBookAccBRN()==null) || 
             (this.bookAccBRN!=null &&
              this.bookAccBRN.equals(other.getBookAccBRN()))) &&
            ((this.accountAutoRollType==null && other.getAccountAutoRollType()==null) || 
             (this.accountAutoRollType!=null &&
              this.accountAutoRollType.equals(other.getAccountAutoRollType()))) &&
            ((this.accountRollType==null && other.getAccountRollType()==null) || 
             (this.accountRollType!=null &&
              this.accountRollType.equals(other.getAccountRollType()))) && 
             ((this.accountDesignate==null && other.getAccountDesignate()==null) || 
             (this.accountDesignate!=null &&
              this.accountDesignate.equals(other.getAccountDesignate())))    ;
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
        if (getAccountNum() != null) {
            _hashCode += getAccountNum().hashCode();
        }
        if (getAccountComment() != null) {
            _hashCode += getAccountComment().hashCode();
        }
        if (getAccountName() != null) {
            _hashCode += getAccountName().hashCode();
        }
        if (getAccountType() != null) {
            _hashCode += getAccountType().hashCode();
        }
        if (getAccountTypeName() != null) {
            _hashCode += getAccountTypeName().hashCode();
        }
        if (getAccountCurrency() != null) {
            _hashCode += getAccountCurrency().hashCode();
        }
        if (getAccountOpeningAmount() != null) {
            _hashCode += getAccountOpeningAmount().hashCode();
        }
        if (getAccountBalance() != null) {
            _hashCode += getAccountBalance().hashCode();
        }
        if (getAccountBalanceAvailable() != null) {
            _hashCode += getAccountBalanceAvailable().hashCode();
        }
        if (getAccountExchangeBalanceEQV() != null) {
            _hashCode += getAccountExchangeBalanceEQV().hashCode();
        }
        if (getAccountOpenDate() != null) {
            _hashCode += getAccountOpenDate().hashCode();
        }
        if (getAccountMaturityDate() != null) {
            _hashCode += getAccountMaturityDate().hashCode();
        }
        if (getAccountStatus() != null) {
            _hashCode += getAccountStatus().hashCode();
        }
        if (getAccountClassName() != null) {
            _hashCode += getAccountClassName().hashCode();
        }
        if (getAccountClassCode() != null) {
            _hashCode += getAccountClassCode().hashCode();
        }
        if (getAccountInterestRate() != null) {
            _hashCode += getAccountInterestRate().hashCode();
        }
        if (getAccountOpenBrandCode() != null) {
            _hashCode += getAccountOpenBrandCode().hashCode();
        }
         if (getAccountOpenBrandName() != null) {
            _hashCode += getAccountOpenBrandName().hashCode();
        }
        if (getAccountLatestTransDate() != null) {
            _hashCode += getAccountLatestTransDate().hashCode();
        }
        if (getAccountOverdraftDate() != null) {
            _hashCode += getAccountOverdraftDate().hashCode();
        }
        if (getAccountOverdraftExpiredDate() != null) {
            _hashCode += getAccountOverdraftExpiredDate().hashCode();
        }
        if (getAccountOverdraftLimit() != null) {
            _hashCode += getAccountOverdraftLimit().hashCode();
        }
        if (getAccountAuthorizedStatus() != null) {
            _hashCode += getAccountAuthorizedStatus().hashCode();
        }
        if (getAccountDelegatedPerson() != null) {
            _hashCode += getAccountDelegatedPerson().hashCode();
        }
        if (getAccountCoownerName() != null) {
            _hashCode += getAccountCoownerName().hashCode();
        }
        if (getAccountTerm() != null) {
            _hashCode += getAccountTerm().hashCode();
        }
        if (getAccountAccrued() != null) {
            _hashCode += getAccountAccrued().hashCode();
        }
        if (getSavingApplicationInterest() != null) {
            _hashCode += getSavingApplicationInterest().hashCode();
        }
        if (getAccountSavingSerials() != null) {
            _hashCode += getAccountSavingSerials().hashCode();
        }
        if (getAccountRolloverAmount() != null) {
            _hashCode += getAccountRolloverAmount().hashCode();
        }
        if (getAccountStaffCode() != null) {
            _hashCode += getAccountStaffCode().hashCode();
        }
        if (getSrcAccountNum() != null) {
            _hashCode += getSrcAccountNum().hashCode();
        }
        if (getAccountNarrative() != null) {
            _hashCode += getAccountNarrative().hashCode();
        }
        if (getPayout() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPayout());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPayout(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRedemption() != null) {
            _hashCode += getRedemption().hashCode();
        }
        if (getTerminationType() != null) {
            _hashCode += getTerminationType().hashCode();
        }
        if (getAccountExpectedAmount() != null) {
            _hashCode += getAccountExpectedAmount().hashCode();
        }
        if (getPayin() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPayin());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPayin(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAccountOfficialOpenDate() != null) {
            _hashCode += getAccountOfficialOpenDate().hashCode();
        }
        if (getAccountInterestReceivableType() != null) {
            _hashCode += getAccountInterestReceivableType().hashCode();
        }
        if (getAccountLatestReceivableDate() != null) {
            _hashCode += getAccountLatestReceivableDate().hashCode();
        }
        if (getAccountLockReason() != null) {
            _hashCode += getAccountLockReason().hashCode();
        }
        if (getAccountLockDate() != null) {
            _hashCode += getAccountLockDate().hashCode();
        }
        if (getAccountLockExpireDate() != null) {
            _hashCode += getAccountLockExpireDate().hashCode();
        }
        if (getAccountLockStatus() != null) {
            _hashCode += getAccountLockStatus().hashCode();
        }
        if (getAccountMorgateType() != null) {
            _hashCode += getAccountMorgateType().hashCode();
        }
        if (getAccountMorgateLimit() != null) {
            _hashCode += getAccountMorgateLimit().hashCode();
        }
        if (getAccountMorgateLimitType() != null) {
            _hashCode += getAccountMorgateLimitType().hashCode();
        }
        if (getAccountSheetBalance() != null) {
            _hashCode += getAccountSheetBalance().hashCode();
        }
        if (getAccountDebtGroup() != null) {
            _hashCode += getAccountDebtGroup().hashCode();
        }
        if (getTdBookAccount() != null) {
            _hashCode += getTdBookAccount().hashCode();
        }
        if (getBookAccBRN() != null) {
            _hashCode += getBookAccBRN().hashCode();
        }
        if (getAccountAutoRollType() != null) {
            _hashCode += getAccountAutoRollType().hashCode();
        }
        if (getAccountRollType() != null) {
            _hashCode += getAccountRollType().hashCode();
        }
        if (getAccountDesignate() != null) {
            _hashCode += getAccountDesignate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "AccountInfoType"));
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
        elemField.setFieldName("accountNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountComment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountComment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountTypeName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountTypeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountOpeningAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountOpeningAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountBalanceAvailable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountBalanceAvailable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountExchangeBalanceEQV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountExchangeBalanceEQV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountOpenDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountOpenDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountMaturityDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountMaturityDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountClassName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountClassName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountClassCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountClassCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountInterestRate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountInterestRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountOpenBrandCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountOpenBrandCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountOpenBrandName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountOpenBrandName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountLatestTransDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountLatestTransDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountOverdraftDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountOverdraftDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountOverdraftExpiredDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountOverdraftExpiredDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountOverdraftLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountOverdraftLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountAuthorizedStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountAuthorizedStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountDelegatedPerson");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountDelegatedPerson"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCoownerName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountCoownerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountTerm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountTerm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountAccrued");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountAccrued"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("savingApplicationInterest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "savingApplicationInterest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountSavingSerials");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountSavingSerials"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountRolloverAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountRolloverAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountStaffCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountStaffCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("srcAccountNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "srcAccountNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNarrative");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountNarrative"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payout");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payout"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "PayoutType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("redemption");
        elemField.setXmlName(new javax.xml.namespace.QName("", "redemption"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "RedemptionType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminationType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "terminationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountExpectedAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountExpectedAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payin"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "PayinType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountOfficialOpenDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountOfficialOpenDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountInterestReceivableType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountInterestReceivableType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountLatestReceivableDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountLatestReceivableDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountLockReason");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountLockReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountLockDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountLockDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountLockExpireDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountLockExpireDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountLockStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountLockStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountMorgateType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountMorgateType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountMorgateLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountMorgateLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountMorgateLimitType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountMorgateLimitType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountSheetBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountSheetBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountDebtGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountDebtGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tdBookAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tdBookAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookAccBRN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bookAccBRN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountAutoRollType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountAutoRollType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountRollType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountRollType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountDesignate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountDesignate"));
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
     * @return the accountOpenBrandName
     */
    public java.lang.String getAccountOpenBrandName() {
        return accountOpenBrandName;
    }

    /**
     * @param accountOpenBrandName the accountOpenBrandName to set
     */
    public void setAccountOpenBrandName(java.lang.String accountOpenBrandName) {
        this.accountOpenBrandName = accountOpenBrandName;
    }

    /**
     * @param payout the payout to set
     */
    public void setPayout(vn.com.scb.bian.PayoutType payout) {
        this.payout = payout;
    }

    /**
     * @return the payin
     */
    public vn.com.scb.bian.PayinType getPayin() {
        return payin;
    }

    /**
     * @param payin the payin to set
     */
    public void setPayin(vn.com.scb.bian.PayinType payin) {
        this.payin = payin;
    }

    /**
     * @return the accountClassMinBalance
     */
    public java.lang.String getAccountClassMinBalance() {
        return accountClassMinBalance;
    }

    /**
     * @param accountClassMinBalance the accountClassMinBalance to set
     */
    public void setAccountClassMinBalance(java.lang.String accountClassMinBalance) {
        this.accountClassMinBalance = accountClassMinBalance;
    }

    /**
     * @return the accountDesignate
     */
    public java.lang.String getAccountDesignate() {
        return accountDesignate;
    }

    /**
     * @param accountDesignate the accountDesignate to set
     */
    public void setAccountDesignate(java.lang.String accountDesignate) {
        this.accountDesignate = accountDesignate;
    }

    /**
     * @return the accountVirtualNum
     */
    public java.lang.String getAccountVirtualNum() {
        return accountVirtualNum;
    }

    /**
     * @param accountVirtualNum the accountVirtualNum to set
     */
    public void setAccountVirtualNum(java.lang.String accountVirtualNum) {
        this.accountVirtualNum = accountVirtualNum;
    }

    /**
     * @return the accountPartnerBankName
     */
    public java.lang.String getAccountPartnerBankName() {
        return accountPartnerBankName;
    }

    /**
     * @param accountPartnerBankName the accountPartnerBankName to set
     */
    public void setAccountPartnerBankName(java.lang.String accountPartnerBankName) {
        this.accountPartnerBankName = accountPartnerBankName;
    }

    /**
     * @return the accountInfoStr
     */
    public java.lang.String getAccountInfoStr() {
        return accountInfoStr;
    }

    /**
     * @param accountInfoStr the accountInfoStr to set
     */
    public void setAccountInfoStr(java.lang.String accountInfoStr) {
        this.accountInfoStr = accountInfoStr;
    }

}
