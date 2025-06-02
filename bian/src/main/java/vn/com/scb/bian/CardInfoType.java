/**
 * CardInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package vn.com.scb.bian;

public class CardInfoType implements java.io.Serializable {

    /* Số tài khoản thẻ. Số LOC */
    private java.lang.String cardAccountNum;

    /* Số thẻ (đã bị che) */
    private java.lang.String hiddenCardNum;

    /* Tình trạng thẻ (hết hạn, đang hoạt động) liên
     *     					quan đển từng thẻ */
    private java.lang.String cardStatus;

    /* Loại sản phẩm thẻ (tài lộc, phú quý…) */
    private java.lang.String cardType;

    /* Thẻ master, visa, local */
    private java.lang.String cardBrand;

    /* Hạng thẻ (gold,standard,platinum) */
    private java.lang.String cardProduct;

    /* ghi chú về loại thẻ */
    private java.lang.String cardDescription;

    /* Hạn mức thẻ */
    private java.lang.String cardLimit;

    /* Dư nợ đã sử dụng */
    private java.lang.String cardOutstandingAmount;

    /* Ngày giao dịch gần nhất */
    private java.lang.String cardLatestTransDate;

    /* Loại tiền trong thẻ */
    private java.lang.String cardCurrency;

    /* Mã chi nhánh mở thẻ */
    private java.lang.String cardOpenBranchCode;

    /* Ngày kích hoạt thẻ */
    private java.lang.String cardActivatedDate;

    /* Ngày phát hành thẻ */
    private java.lang.String cardIssueDate;

    /* Xác định thẻ chính hoặc thẻ phụ. 'P' Thẻ chính,
     *     					'S' Thẻ phụ */
    private java.lang.String cardProperty;

    /* Dư nợ phải trả */
    private java.lang.String cardInstallmentAmount;

    /* Số tiền tối thiểu phải thanh toán (thẻ tín dụng) */
    private java.lang.String cardMinimumInstallmentAmount;

    /* Ngày đến hạn thanh toán(thẻ tín dụng) */
    private java.lang.String cardInstallmentDate;

    /* Hạn mức còn lại (hiện tại) của thẻ */
    private java.lang.String cardCurrentLimit;

    /* Dư nợ đã sử dụng thẻ khác */
    private java.lang.String cardOtherAccountOutstanding;

    /* Tổng hạn mức thẻ của khách hàng */
    private java.lang.String cardCustomerTotalLimit;

    /* Hạn mức còn lại của khách hàng */
    private java.lang.String cardCustomerCurrentLimit;

    /* Ngày hết hạn thẻ (MM/YYYY) */
    private java.lang.String cardExpiredDate;

    /* Số Pane ID để truy vấn thông tin thẻ */
    private java.lang.String cardPaneID;

    /* Tên chủ thẻ */
    private java.lang.String cardOwnerName;

    /* Trạng thái thẻ (khóa thẻ hoặc đang hoạt động) */
    private java.lang.String cardLockStatus;

    /* Ngày khóa thẻ */
    private java.lang.String cardLockDate;

    /* Ngày hết hạn khóa thẻ */
    private java.lang.String cardLockExpDate;

    /* Số tiền đã trả trong kỳ sao kê */
    private java.lang.String cardPaidAmount;

    /* Tài khoản trích nợ tự động */
    private java.lang.String cardPaidAccount;

    /* Đăng ký trích nợ tối thiểu hay tối đa */
    private java.lang.String cardPaidType;

    /* TK thanh toán gắn với thẻ */
    private java.lang.String cardCasaAccount;

    /* Trạng thái thanh toán trực tuyến */
    private java.lang.String cardOnlineStatus;

    /* Dư nợ chưa hạch toán */
    private java.lang.String cardUnpostAmount;

    /* Dư nợ chưa hạch toán */
    private java.lang.String cardOutstandingAmountNotUnpost;

    /* Dư nợ thẻ đã thanh toán trong kỳ sao kê  */
    private java.lang.String cardPaidCurrentAmount;

    /* Dư nợ trả góp đã thanh toán trong kỳ sao kê*/
    private java.lang.String cardIPPPaidAmount;

    /* Dư nợ trả góp theo kỳ sao kê */
    private java.lang.String cardIPPStatementAmount;

    /* Dư nợ trả góp đến hiện tại */
    private java.lang.String cardIPPCurrentAmount;

    /* Tổng dư nợ kỳ sao kê trước  */
    private java.lang.String cardInstallmentAmountPre;

    /* Số tiền thanh toán tối thiểu kỳ trước */
    private java.lang.String cardMinimumInstallmentAmountPre;

    /* Số tiền đã thanh toán trong kỳ sao kê kỳ trước */
    private java.lang.String cardPaidCurrentAmountPre;

    /* Ngày đến hạn thanh toán kỳ trước */
    private java.lang.String cardInstallmentDatePre;

    /* Tính chất thẻ*/
    private java.lang.String cardImageId;

    private vn.com.scb.bian.CIFDataType CIFInfo;

    private vn.com.scb.bian.OTPDataType OTPData;
   
    /*The doanh nghiep hay ca nhan*/
    private java.lang.String cardTypeCode;
    public CardInfoType() {
    }
    
    public CardInfoType(
            java.lang.String cardAccountNum,
            java.lang.String hiddenCardNum,
            java.lang.String cardStatus,
            java.lang.String cardType,
            java.lang.String cardBrand,
            java.lang.String cardProduct,
            java.lang.String cardDescription,
            java.lang.String cardLimit,
            java.lang.String cardOutstandingAmount,
            java.lang.String cardLatestTransDate,
            java.lang.String cardCurrency,
            java.lang.String cardOpenBranchCode,
            java.lang.String cardActivatedDate,
            java.lang.String cardIssueDate,
            java.lang.String cardProperty,
            java.lang.String cardInstallmentAmount,
            java.lang.String cardMinimumInstallmentAmount,
            java.lang.String cardInstallmentDate,
            java.lang.String cardCurrentLimit,
            java.lang.String cardOtherAccountOutstanding,
            java.lang.String cardCustomerTotalLimit,
            java.lang.String cardCustomerCurrentLimit,
            java.lang.String cardExpiredDate,
            java.lang.String cardPaneID,
            java.lang.String cardOwnerName,
            java.lang.String cardLockStatus,
            java.lang.String cardLockDate,
            java.lang.String cardLockExpDate,
            java.lang.String cardPaidAmount,
            java.lang.String cardPaidAccount,
            java.lang.String cardPaidType,
            java.lang.String cardCasaAccount,
            java.lang.String cardOnlineStatus,
            java.lang.String cardUnpostAmount,
            java.lang.String cardOutstandingAmountNotUnpost,
            java.lang.String cardPaidCurrentAmount,
            java.lang.String cardIPPPaidAmount,
            java.lang.String cardIPPStatementAmount,
            java.lang.String cardIPPCurrentAmount,
            java.lang.String cardInstallmentAmountPre,
            java.lang.String cardMinimumInstallmentAmountPre,
            java.lang.String cardPaidCurrentAmountPre,
            java.lang.String cardInstallmentDatePre,
            vn.com.scb.bian.CIFDataType CIFInfo,
            vn.com.scb.bian.OTPDataType OTPData,
            java.lang.String cardImageId,
            java.lang.String cardTypeCode) {
        this.cardAccountNum = cardAccountNum;
        this.hiddenCardNum = hiddenCardNum;
        this.cardStatus = cardStatus;
        this.cardType = cardType;
        this.cardBrand = cardBrand;
        this.cardProduct = cardProduct;
        this.cardDescription = cardDescription;
        this.cardLimit = cardLimit;
        this.cardOutstandingAmount = cardOutstandingAmount;
        this.cardLatestTransDate = cardLatestTransDate;
        this.cardCurrency = cardCurrency;
        this.cardOpenBranchCode = cardOpenBranchCode;
        this.cardActivatedDate = cardActivatedDate;
        this.cardIssueDate = cardIssueDate;
        this.cardProperty = cardProperty;
        this.cardInstallmentAmount = cardInstallmentAmount;
        this.cardMinimumInstallmentAmount = cardMinimumInstallmentAmount;
        this.cardInstallmentDate = cardInstallmentDate;
        this.cardCurrentLimit = cardCurrentLimit;
        this.cardOtherAccountOutstanding = cardOtherAccountOutstanding;
        this.cardCustomerTotalLimit = cardCustomerTotalLimit;
        this.cardCustomerCurrentLimit = cardCustomerCurrentLimit;
        this.cardExpiredDate = cardExpiredDate;
        this.cardPaneID = cardPaneID;
        this.cardOwnerName = cardOwnerName;
        this.cardLockStatus = cardLockStatus;
        this.cardLockDate = cardLockDate;
        this.cardLockExpDate = cardLockExpDate;
        this.cardPaidAmount = cardPaidAmount;
        this.cardPaidAccount = cardPaidAccount;
        this.cardPaidType = cardPaidType;
        this.cardCasaAccount = cardCasaAccount;
        this.cardOnlineStatus = cardOnlineStatus;
        this.CIFInfo = CIFInfo;
        this.OTPData = OTPData;
        this.cardUnpostAmount = cardUnpostAmount;
        this.cardOutstandingAmountNotUnpost = cardOutstandingAmountNotUnpost;
        this.cardPaidCurrentAmount = cardPaidCurrentAmount;
        this.cardIPPPaidAmount = cardIPPPaidAmount;
        this.cardIPPStatementAmount = cardIPPStatementAmount;
        this.cardIPPCurrentAmount = cardIPPCurrentAmount;
        this.cardInstallmentAmountPre = cardInstallmentAmountPre;
        this.cardMinimumInstallmentAmountPre = cardMinimumInstallmentAmountPre;
        this.cardPaidCurrentAmountPre = cardPaidCurrentAmountPre;
        this.cardInstallmentDatePre = cardInstallmentDatePre;
        this.cardImageId = cardImageId;
        this.cardTypeCode = cardTypeCode;
    }

    /**
     * Gets the cardAccountNum value for this CardInfoType.
     *
     * @return cardAccountNum * Số tài khoản thẻ. Số LOC
     */
    public java.lang.String getCardAccountNum() {
        return cardAccountNum;
    }

    /**
     * Sets the cardAccountNum value for this CardInfoType.
     *
     * @param cardAccountNum * Số tài khoản thẻ. Số LOC
     */
    public void setCardAccountNum(java.lang.String cardAccountNum) {
        this.cardAccountNum = cardAccountNum;
    }

    /**
     * Gets the hiddenCardNum value for this CardInfoType.
     *
     * @return hiddenCardNum * Số thẻ (đã bị che)
     */
    public java.lang.String getHiddenCardNum() {
        return hiddenCardNum;
    }

    /**
     * Sets the hiddenCardNum value for this CardInfoType.
     *
     * @param hiddenCardNum * Số thẻ (đã bị che)
     */
    public void setHiddenCardNum(java.lang.String hiddenCardNum) {
        this.hiddenCardNum = hiddenCardNum;
    }

    /**
     * Gets the cardStatus value for this CardInfoType.
     *
     * @return cardStatus * Tình trạng thẻ (hết hạn, đang hoạt động) liên quan
     * đển từng thẻ
     */
    public java.lang.String getCardStatus() {
        return cardStatus;
    }

    /**
     * Sets the cardStatus value for this CardInfoType.
     *
     * @param cardStatus * Tình trạng thẻ (hết hạn, đang hoạt động) liên quan
     * đển từng thẻ
     */
    public void setCardStatus(java.lang.String cardStatus) {
        this.cardStatus = cardStatus;
    }

    /**
     * Gets the cardType value for this CardInfoType.
     *
     * @return cardType * Loại sản phẩm thẻ (tài lộc, phú quý…)
     */
    public java.lang.String getCardType() {
        return cardType;
    }

    /**
     * Sets the cardType value for this CardInfoType.
     *
     * @param cardType * Loại sản phẩm thẻ (tài lộc, phú quý…)
     */
    public void setCardType(java.lang.String cardType) {
        this.cardType = cardType;
    }

    /**
     * Gets the cardBrand value for this CardInfoType.
     *
     * @return cardBrand * Thẻ master, visa, local
     */
    public java.lang.String getCardBrand() {
        return cardBrand;
    }

    /**
     * Sets the cardBrand value for this CardInfoType.
     *
     * @param cardBrand * Thẻ master, visa, local
     */
    public void setCardBrand(java.lang.String cardBrand) {
        this.cardBrand = cardBrand;
    }

    /**
     * Gets the cardProduct value for this CardInfoType.
     *
     * @return cardProduct * Hạng thẻ (gold,standard,platinum)
     */
    public java.lang.String getCardProduct() {
        return cardProduct;
    }

    /**
     * Sets the cardProduct value for this CardInfoType.
     *
     * @param cardProduct * Hạng thẻ (gold,standard,platinum)
     */
    public void setCardProduct(java.lang.String cardProduct) {
        this.cardProduct = cardProduct;
    }

    /**
     * Gets the cardDescription value for this CardInfoType.
     *
     * @return cardDescription * ghi chú về loại thẻ
     */
    public java.lang.String getCardDescription() {
        return cardDescription;
    }

    /**
     * Sets the cardDescription value for this CardInfoType.
     *
     * @param cardDescription * ghi chú về loại thẻ
     */
    public void setCardDescription(java.lang.String cardDescription) {
        this.cardDescription = cardDescription;
    }

    /**
     * Gets the cardLimit value for this CardInfoType.
     *
     * @return cardLimit * Hạn mức thẻ
     */
    public java.lang.String getCardLimit() {
        return cardLimit;
    }

    /**
     * Sets the cardLimit value for this CardInfoType.
     *
     * @param cardLimit * Hạn mức thẻ
     */
    public void setCardLimit(java.lang.String cardLimit) {
        this.cardLimit = cardLimit;
    }

    /**
     * Gets the cardOutstandingAmount value for this CardInfoType.
     *
     * @return cardOutstandingAmount * Dư nợ đã sử dụng
     */
    public java.lang.String getCardOutstandingAmount() {
        return cardOutstandingAmount;
    }

    /**
     * Sets the cardOutstandingAmount value for this CardInfoType.
     *
     * @param cardOutstandingAmount * Dư nợ đã sử dụng
     */
    public void setCardOutstandingAmount(java.lang.String cardOutstandingAmount) {
        this.cardOutstandingAmount = cardOutstandingAmount;
    }

    /**
     * Gets the cardLatestTransDate value for this CardInfoType.
     *
     * @return cardLatestTransDate * Ngày giao dịch gần nhất
     */
    public java.lang.String getCardLatestTransDate() {
        return cardLatestTransDate;
    }

    /**
     * Sets the cardLatestTransDate value for this CardInfoType.
     *
     * @param cardLatestTransDate * Ngày giao dịch gần nhất
     */
    public void setCardLatestTransDate(java.lang.String cardLatestTransDate) {
        this.cardLatestTransDate = cardLatestTransDate;
    }

    /**
     * Gets the cardCurrency value for this CardInfoType.
     *
     * @return cardCurrency * Loại tiền trong thẻ
     */
    public java.lang.String getCardCurrency() {
        return cardCurrency;
    }

    /**
     * Sets the cardCurrency value for this CardInfoType.
     *
     * @param cardCurrency * Loại tiền trong thẻ
     */
    public void setCardCurrency(java.lang.String cardCurrency) {
        this.cardCurrency = cardCurrency;
    }

    /**
     * Gets the cardOpenBranchCode value for this CardInfoType.
     *
     * @return cardOpenBranchCode * Mã chi nhánh mở thẻ
     */
    public java.lang.String getCardOpenBranchCode() {
        return cardOpenBranchCode;
    }

    /**
     * Sets the cardOpenBranchCode value for this CardInfoType.
     *
     * @param cardOpenBranchCode * Mã chi nhánh mở thẻ
     */
    public void setCardOpenBranchCode(java.lang.String cardOpenBranchCode) {
        this.cardOpenBranchCode = cardOpenBranchCode;
    }

    /**
     * Gets the cardActivatedDate value for this CardInfoType.
     *
     * @return cardActivatedDate * Ngày kích hoạt thẻ
     */
    public java.lang.String getCardActivatedDate() {
        return cardActivatedDate;
    }

    /**
     * Sets the cardActivatedDate value for this CardInfoType.
     *
     * @param cardActivatedDate * Ngày kích hoạt thẻ
     */
    public void setCardActivatedDate(java.lang.String cardActivatedDate) {
        this.cardActivatedDate = cardActivatedDate;
    }

    /**
     * Gets the cardIssueDate value for this CardInfoType.
     *
     * @return cardIssueDate * Ngày phát hành thẻ
     */
    public java.lang.String getCardIssueDate() {
        return cardIssueDate;
    }

    /**
     * Sets the cardIssueDate value for this CardInfoType.
     *
     * @param cardIssueDate * Ngày phát hành thẻ
     */
    public void setCardIssueDate(java.lang.String cardIssueDate) {
        this.cardIssueDate = cardIssueDate;
    }

    /**
     * Gets the cardProperty value for this CardInfoType.
     *
     * @return cardProperty * Xác định thẻ chính hoặc thẻ phụ. 'P' Thẻ chính,
     * 'S' Thẻ phụ
     */
    public java.lang.String getCardProperty() {
        return cardProperty;
    }

    /**
     * Sets the cardProperty value for this CardInfoType.
     *
     * @param cardProperty * Xác định thẻ chính hoặc thẻ phụ. 'P' Thẻ chính, 'S'
     * Thẻ phụ
     */
    public void setCardProperty(java.lang.String cardProperty) {
        this.cardProperty = cardProperty;
    }

    /**
     * Gets the cardInstallmentAmount value for this CardInfoType.
     *
     * @return cardInstallmentAmount * Dư nợ phải trả
     */
    public java.lang.String getCardInstallmentAmount() {
        return cardInstallmentAmount;
    }

    /**
     * Sets the cardInstallmentAmount value for this CardInfoType.
     *
     * @param cardInstallmentAmount * Dư nợ phải trả
     */
    public void setCardInstallmentAmount(java.lang.String cardInstallmentAmount) {
        this.cardInstallmentAmount = cardInstallmentAmount;
    }

    /**
     * Gets the cardMinimumInstallmentAmount value for this CardInfoType.
     *
     * @return cardMinimumInstallmentAmount * Số tiền tối thiểu phải thanh toán
     * (thẻ tín dụng)
     */
    public java.lang.String getCardMinimumInstallmentAmount() {
        return cardMinimumInstallmentAmount;
    }

    /**
     * Sets the cardMinimumInstallmentAmount value for this CardInfoType.
     *
     * @param cardMinimumInstallmentAmount * Số tiền tối thiểu phải thanh toán
     * (thẻ tín dụng)
     */
    public void setCardMinimumInstallmentAmount(java.lang.String cardMinimumInstallmentAmount) {
        this.cardMinimumInstallmentAmount = cardMinimumInstallmentAmount;
    }

    /**
     * Gets the cardInstallmentDate value for this CardInfoType.
     *
     * @return cardInstallmentDate * Ngày đến hạn thanh toán(thẻ tín dụng)
     */
    public java.lang.String getCardInstallmentDate() {
        return cardInstallmentDate;
    }

    /**
     * Sets the cardInstallmentDate value for this CardInfoType.
     *
     * @param cardInstallmentDate * Ngày đến hạn thanh toán(thẻ tín dụng)
     */
    public void setCardInstallmentDate(java.lang.String cardInstallmentDate) {
        this.cardInstallmentDate = cardInstallmentDate;
    }

    /**
     * Gets the cardCurrentLimit value for this CardInfoType.
     *
     * @return cardCurrentLimit * Hạn mức còn lại (hiện tại) của thẻ
     */
    public java.lang.String getCardCurrentLimit() {
        return cardCurrentLimit;
    }

    /**
     * Sets the cardCurrentLimit value for this CardInfoType.
     *
     * @param cardCurrentLimit * Hạn mức còn lại (hiện tại) của thẻ
     */
    public void setCardCurrentLimit(java.lang.String cardCurrentLimit) {
        this.cardCurrentLimit = cardCurrentLimit;
    }

    /**
     * Gets the cardOtherAccountOutstanding value for this CardInfoType.
     *
     * @return cardOtherAccountOutstanding * Dư nợ đã sử dụng thẻ khác
     */
    public java.lang.String getCardOtherAccountOutstanding() {
        return cardOtherAccountOutstanding;
    }

    /**
     * Sets the cardOtherAccountOutstanding value for this CardInfoType.
     *
     * @param cardOtherAccountOutstanding * Dư nợ đã sử dụng thẻ khác
     */
    public void setCardOtherAccountOutstanding(java.lang.String cardOtherAccountOutstanding) {
        this.cardOtherAccountOutstanding = cardOtherAccountOutstanding;
    }

    /**
     * Gets the cardCustomerTotalLimit value for this CardInfoType.
     *
     * @return cardCustomerTotalLimit * Tổng hạn mức thẻ của khách hàng
     */
    public java.lang.String getCardCustomerTotalLimit() {
        return cardCustomerTotalLimit;
    }

    /**
     * Sets the cardCustomerTotalLimit value for this CardInfoType.
     *
     * @param cardCustomerTotalLimit * Tổng hạn mức thẻ của khách hàng
     */
    public void setCardCustomerTotalLimit(java.lang.String cardCustomerTotalLimit) {
        this.cardCustomerTotalLimit = cardCustomerTotalLimit;
    }

    /**
     * Gets the cardCustomerCurrentLimit value for this CardInfoType.
     *
     * @return cardCustomerCurrentLimit * Hạn mức còn lại của khách hàng
     */
    public java.lang.String getCardCustomerCurrentLimit() {
        return cardCustomerCurrentLimit;
    }

    /**
     * Sets the cardCustomerCurrentLimit value for this CardInfoType.
     *
     * @param cardCustomerCurrentLimit * Hạn mức còn lại của khách hàng
     */
    public void setCardCustomerCurrentLimit(java.lang.String cardCustomerCurrentLimit) {
        this.cardCustomerCurrentLimit = cardCustomerCurrentLimit;
    }

    /**
     * Gets the cardExpiredDate value for this CardInfoType.
     *
     * @return cardExpiredDate * Ngày hết hạn thẻ (MM/YYYY)
     */
    public java.lang.String getCardExpiredDate() {
        return cardExpiredDate;
    }

    /**
     * Sets the cardExpiredDate value for this CardInfoType.
     *
     * @param cardExpiredDate * Ngày hết hạn thẻ (MM/YYYY)
     */
    public void setCardExpiredDate(java.lang.String cardExpiredDate) {
        this.cardExpiredDate = cardExpiredDate;
    }

    /**
     * Gets the cardPaneID value for this CardInfoType.
     *
     * @return cardPaneID * Số Pane ID để truy vấn thông tin thẻ
     */
    public java.lang.String getCardPaneID() {
        return cardPaneID;
    }

    /**
     * Sets the cardPaneID value for this CardInfoType.
     *
     * @param cardPaneID * Số Pane ID để truy vấn thông tin thẻ
     */
    public void setCardPaneID(java.lang.String cardPaneID) {
        this.cardPaneID = cardPaneID;
    }

    /**
     * Gets the cardOwnerName value for this CardInfoType.
     *
     * @return cardOwnerName * Tên chủ thẻ
     */
    public java.lang.String getCardOwnerName() {
        return cardOwnerName;
    }

    /**
     * Sets the cardOwnerName value for this CardInfoType.
     *
     * @param cardOwnerName * Tên chủ thẻ
     */
    public void setCardOwnerName(java.lang.String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }

    /**
     * Gets the cardLockStatus value for this CardInfoType.
     *
     * @return cardLockStatus * Trạng thái thẻ (khóa thẻ hoặc đang hoạt động)
     */
    public java.lang.String getCardLockStatus() {
        return cardLockStatus;
    }

    /**
     * Sets the cardLockStatus value for this CardInfoType.
     *
     * @param cardLockStatus * Trạng thái thẻ (khóa thẻ hoặc đang hoạt động)
     */
    public void setCardLockStatus(java.lang.String cardLockStatus) {
        this.cardLockStatus = cardLockStatus;
    }

    /**
     * Gets the cardLockDate value for this CardInfoType.
     *
     * @return cardLockDate * Ngày khóa thẻ
     */
    public java.lang.String getCardLockDate() {
        return cardLockDate;
    }

    /**
     * Sets the cardLockDate value for this CardInfoType.
     *
     * @param cardLockDate * Ngày khóa thẻ
     */
    public void setCardLockDate(java.lang.String cardLockDate) {
        this.cardLockDate = cardLockDate;
    }

    /**
     * Gets the cardLockExpDate value for this CardInfoType.
     *
     * @return cardLockExpDate * Ngày hết hạn khóa thẻ
     */
    public java.lang.String getCardLockExpDate() {
        return cardLockExpDate;
    }

    /**
     * Sets the cardLockExpDate value for this CardInfoType.
     *
     * @param cardLockExpDate * Ngày hết hạn khóa thẻ
     */
    public void setCardLockExpDate(java.lang.String cardLockExpDate) {
        this.cardLockExpDate = cardLockExpDate;
    }

    /**
     * Gets the cardPaidAmount value for this CardInfoType.
     *
     * @return cardPaidAmount * Số tiền đã trả trong kỳ sao kê
     */
    public java.lang.String getCardPaidAmount() {
        return cardPaidAmount;
    }

    /**
     * Sets the cardPaidAmount value for this CardInfoType.
     *
     * @param cardPaidAmount * Số tiền đã trả trong kỳ sao kê
     */
    public void setCardPaidAmount(java.lang.String cardPaidAmount) {
        this.cardPaidAmount = cardPaidAmount;
    }

    /**
     * Gets the cardPaidAccount value for this CardInfoType.
     *
     * @return cardPaidAccount * Tài khoản trích nợ tự động
     */
    public java.lang.String getCardPaidAccount() {
        return cardPaidAccount;
    }

    /**
     * Sets the cardPaidAccount value for this CardInfoType.
     *
     * @param cardPaidAccount * Tài khoản trích nợ tự động
     */
    public void setCardPaidAccount(java.lang.String cardPaidAccount) {
        this.cardPaidAccount = cardPaidAccount;
    }

    /**
     * Gets the cardPaidType value for this CardInfoType.
     *
     * @return cardPaidType * Đăng ký trích nợ tối thiểu hay tối đa
     */
    public java.lang.String getCardPaidType() {
        return cardPaidType;
    }

    /**
     * Sets the cardPaidType value for this CardInfoType.
     *
     * @param cardPaidType * Đăng ký trích nợ tối thiểu hay tối đa
     */
    public void setCardPaidType(java.lang.String cardPaidType) {
        this.cardPaidType = cardPaidType;
    }

    /**
     * Gets the cardCasaAccount value for this CardInfoType.
     *
     * @return cardCasaAccount * TK thanh toán gắn với thẻ
     */
    public java.lang.String getCardCasaAccount() {
        return cardCasaAccount;
    }

    /**
     * Sets the cardCasaAccount value for this CardInfoType.
     *
     * @param cardCasaAccount * TK thanh toán gắn với thẻ
     */
    public void setCardCasaAccount(java.lang.String cardCasaAccount) {
        this.cardCasaAccount = cardCasaAccount;
    }

    /**
     * Gets the cardOnlineStatus value for this CardInfoType.
     *
     * @return cardOnlineStatus * Trạng thái thanh toán trực tuyến
     */
    public java.lang.String getCardOnlineStatus() {
        return cardOnlineStatus;
    }

    /**
     * Sets the cardOnlineStatus value for this CardInfoType.
     *
     * @param cardOnlineStatus * Trạng thái thanh toán trực tuyến
     */
    public void setCardOnlineStatus(java.lang.String cardOnlineStatus) {
        this.cardOnlineStatus = cardOnlineStatus;
    }

    /**
     * Gets the CIFInfo value for this CardInfoType.
     *
     * @return CIFInfo
     */
    public vn.com.scb.bian.CIFDataType getCIFInfo() {
        return CIFInfo;
    }

    /**
     * Sets the CIFInfo value for this CardInfoType.
     *
     * @param CIFInfo
     */
    public void setCIFInfo(vn.com.scb.bian.CIFDataType CIFInfo) {
        this.CIFInfo = CIFInfo;
    }

    /**
     * Gets the OTPData value for this CardInfoType.
     *
     * @return OTPData
     */
    public vn.com.scb.bian.OTPDataType getOTPData() {
        return OTPData;
    }

    /**
     * Sets the OTPData value for this CardInfoType.
     *
     * @param OTPData
     */
    public void setOTPData(vn.com.scb.bian.OTPDataType OTPData) {
        this.OTPData = OTPData;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CardInfoType)) {
            return false;
        }
        CardInfoType other = (CardInfoType) obj;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true
                && ((this.cardAccountNum == null && other.getCardAccountNum() == null)
                || (this.cardAccountNum != null
                && this.cardAccountNum.equals(other.getCardAccountNum())))
                && ((this.hiddenCardNum == null && other.getHiddenCardNum() == null)
                || (this.hiddenCardNum != null
                && this.hiddenCardNum.equals(other.getHiddenCardNum())))
                && ((this.cardStatus == null && other.getCardStatus() == null)
                || (this.cardStatus != null
                && this.cardStatus.equals(other.getCardStatus())))
                && ((this.cardType == null && other.getCardType() == null)
                || (this.cardType != null
                && this.cardType.equals(other.getCardType())))
                && ((this.cardBrand == null && other.getCardBrand() == null)
                || (this.cardBrand != null
                && this.cardBrand.equals(other.getCardBrand())))
                && ((this.cardProduct == null && other.getCardProduct() == null)
                || (this.cardProduct != null
                && this.cardProduct.equals(other.getCardProduct())))
                && ((this.cardDescription == null && other.getCardDescription() == null)
                || (this.cardDescription != null
                && this.cardDescription.equals(other.getCardDescription())))
                && ((this.cardLimit == null && other.getCardLimit() == null)
                || (this.cardLimit != null
                && this.cardLimit.equals(other.getCardLimit())))
                && ((this.cardOutstandingAmount == null && other.getCardOutstandingAmount() == null)
                || (this.cardOutstandingAmount != null
                && this.cardOutstandingAmount.equals(other.getCardOutstandingAmount())))
                && ((this.cardLatestTransDate == null && other.getCardLatestTransDate() == null)
                || (this.cardLatestTransDate != null
                && this.cardLatestTransDate.equals(other.getCardLatestTransDate())))
                && ((this.cardCurrency == null && other.getCardCurrency() == null)
                || (this.cardCurrency != null
                && this.cardCurrency.equals(other.getCardCurrency())))
                && ((this.cardOpenBranchCode == null && other.getCardOpenBranchCode() == null)
                || (this.cardOpenBranchCode != null
                && this.cardOpenBranchCode.equals(other.getCardOpenBranchCode())))
                && ((this.cardActivatedDate == null && other.getCardActivatedDate() == null)
                || (this.cardActivatedDate != null
                && this.cardActivatedDate.equals(other.getCardActivatedDate())))
                && ((this.cardIssueDate == null && other.getCardIssueDate() == null)
                || (this.cardIssueDate != null
                && this.cardIssueDate.equals(other.getCardIssueDate())))
                && ((this.cardProperty == null && other.getCardProperty() == null)
                || (this.cardProperty != null
                && this.cardProperty.equals(other.getCardProperty())))
                && ((this.cardInstallmentAmount == null && other.getCardInstallmentAmount() == null)
                || (this.cardInstallmentAmount != null
                && this.cardInstallmentAmount.equals(other.getCardInstallmentAmount())))
                && ((this.cardMinimumInstallmentAmount == null && other.getCardMinimumInstallmentAmount() == null)
                || (this.cardMinimumInstallmentAmount != null
                && this.cardMinimumInstallmentAmount.equals(other.getCardMinimumInstallmentAmount())))
                && ((this.cardInstallmentDate == null && other.getCardInstallmentDate() == null)
                || (this.cardInstallmentDate != null
                && this.cardInstallmentDate.equals(other.getCardInstallmentDate())))
                && ((this.cardCurrentLimit == null && other.getCardCurrentLimit() == null)
                || (this.cardCurrentLimit != null
                && this.cardCurrentLimit.equals(other.getCardCurrentLimit())))
                && ((this.cardOtherAccountOutstanding == null && other.getCardOtherAccountOutstanding() == null)
                || (this.cardOtherAccountOutstanding != null
                && this.cardOtherAccountOutstanding.equals(other.getCardOtherAccountOutstanding())))
                && ((this.cardCustomerTotalLimit == null && other.getCardCustomerTotalLimit() == null)
                || (this.cardCustomerTotalLimit != null
                && this.cardCustomerTotalLimit.equals(other.getCardCustomerTotalLimit())))
                && ((this.cardCustomerCurrentLimit == null && other.getCardCustomerCurrentLimit() == null)
                || (this.cardCustomerCurrentLimit != null
                && this.cardCustomerCurrentLimit.equals(other.getCardCustomerCurrentLimit())))
                && ((this.cardExpiredDate == null && other.getCardExpiredDate() == null)
                || (this.cardExpiredDate != null
                && this.cardExpiredDate.equals(other.getCardExpiredDate())))
                && ((this.cardPaneID == null && other.getCardPaneID() == null)
                || (this.cardPaneID != null
                && this.cardPaneID.equals(other.getCardPaneID())))
                && ((this.cardOwnerName == null && other.getCardOwnerName() == null)
                || (this.cardOwnerName != null
                && this.cardOwnerName.equals(other.getCardOwnerName())))
                && ((this.cardLockStatus == null && other.getCardLockStatus() == null)
                || (this.cardLockStatus != null
                && this.cardLockStatus.equals(other.getCardLockStatus())))
                && ((this.cardLockDate == null && other.getCardLockDate() == null)
                || (this.cardLockDate != null
                && this.cardLockDate.equals(other.getCardLockDate())))
                && ((this.cardLockExpDate == null && other.getCardLockExpDate() == null)
                || (this.cardLockExpDate != null
                && this.cardLockExpDate.equals(other.getCardLockExpDate())))
                && ((this.cardPaidAmount == null && other.getCardPaidAmount() == null)
                || (this.cardPaidAmount != null
                && this.cardPaidAmount.equals(other.getCardPaidAmount())))
                && ((this.cardPaidAccount == null && other.getCardPaidAccount() == null)
                || (this.cardPaidAccount != null
                && this.cardPaidAccount.equals(other.getCardPaidAccount())))
                && ((this.cardPaidType == null && other.getCardPaidType() == null)
                || (this.cardPaidType != null
                && this.cardPaidType.equals(other.getCardPaidType())))
                && ((this.cardCasaAccount == null && other.getCardCasaAccount() == null)
                || (this.cardCasaAccount != null
                && this.cardCasaAccount.equals(other.getCardCasaAccount())))
                && ((this.cardOnlineStatus == null && other.getCardOnlineStatus() == null)
                || (this.cardOnlineStatus != null
                && this.cardOnlineStatus.equals(other.getCardOnlineStatus())))
                && ((this.CIFInfo == null && other.getCIFInfo() == null)
                || (this.CIFInfo != null
                && this.CIFInfo.equals(other.getCIFInfo())))
                && ((this.OTPData == null && other.getOTPData() == null)
                || (this.OTPData != null
                && this.OTPData.equals(other.getOTPData())));
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
        if (getCardAccountNum() != null) {
            _hashCode += getCardAccountNum().hashCode();
        }
        if (getHiddenCardNum() != null) {
            _hashCode += getHiddenCardNum().hashCode();
        }
        if (getCardStatus() != null) {
            _hashCode += getCardStatus().hashCode();
        }
        if (getCardType() != null) {
            _hashCode += getCardType().hashCode();
        }
        if (getCardBrand() != null) {
            _hashCode += getCardBrand().hashCode();
        }
        if (getCardProduct() != null) {
            _hashCode += getCardProduct().hashCode();
        }
        if (getCardDescription() != null) {
            _hashCode += getCardDescription().hashCode();
        }
        if (getCardLimit() != null) {
            _hashCode += getCardLimit().hashCode();
        }
        if (getCardOutstandingAmount() != null) {
            _hashCode += getCardOutstandingAmount().hashCode();
        }
        if (getCardLatestTransDate() != null) {
            _hashCode += getCardLatestTransDate().hashCode();
        }
        if (getCardCurrency() != null) {
            _hashCode += getCardCurrency().hashCode();
        }
        if (getCardOpenBranchCode() != null) {
            _hashCode += getCardOpenBranchCode().hashCode();
        }
        if (getCardActivatedDate() != null) {
            _hashCode += getCardActivatedDate().hashCode();
        }
        if (getCardIssueDate() != null) {
            _hashCode += getCardIssueDate().hashCode();
        }
        if (getCardProperty() != null) {
            _hashCode += getCardProperty().hashCode();
        }
        if (getCardInstallmentAmount() != null) {
            _hashCode += getCardInstallmentAmount().hashCode();
        }
        if (getCardMinimumInstallmentAmount() != null) {
            _hashCode += getCardMinimumInstallmentAmount().hashCode();
        }
        if (getCardInstallmentDate() != null) {
            _hashCode += getCardInstallmentDate().hashCode();
        }
        if (getCardCurrentLimit() != null) {
            _hashCode += getCardCurrentLimit().hashCode();
        }
        if (getCardOtherAccountOutstanding() != null) {
            _hashCode += getCardOtherAccountOutstanding().hashCode();
        }
        if (getCardCustomerTotalLimit() != null) {
            _hashCode += getCardCustomerTotalLimit().hashCode();
        }
        if (getCardCustomerCurrentLimit() != null) {
            _hashCode += getCardCustomerCurrentLimit().hashCode();
        }
        if (getCardExpiredDate() != null) {
            _hashCode += getCardExpiredDate().hashCode();
        }
        if (getCardPaneID() != null) {
            _hashCode += getCardPaneID().hashCode();
        }
        if (getCardOwnerName() != null) {
            _hashCode += getCardOwnerName().hashCode();
        }
        if (getCardLockStatus() != null) {
            _hashCode += getCardLockStatus().hashCode();
        }
        if (getCardLockDate() != null) {
            _hashCode += getCardLockDate().hashCode();
        }
        if (getCardLockExpDate() != null) {
            _hashCode += getCardLockExpDate().hashCode();
        }
        if (getCardPaidAmount() != null) {
            _hashCode += getCardPaidAmount().hashCode();
        }
        if (getCardPaidAccount() != null) {
            _hashCode += getCardPaidAccount().hashCode();
        }
        if (getCardPaidType() != null) {
            _hashCode += getCardPaidType().hashCode();
        }
        if (getCardCasaAccount() != null) {
            _hashCode += getCardCasaAccount().hashCode();
        }
        if (getCardOnlineStatus() != null) {
            _hashCode += getCardOnlineStatus().hashCode();
        }
        if (getCIFInfo() != null) {
            _hashCode += getCIFInfo().hashCode();
        }
        if (getOTPData() != null) {
            _hashCode += getOTPData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc
            = new org.apache.axis.description.TypeDesc(CardInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CardInfoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardAccountNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardAccountNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hiddenCardNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hiddenCardNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardBrand");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardBrand"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardProduct");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardProduct"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardOutstandingAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardOutstandingAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardLatestTransDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardLatestTransDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardOpenBranchCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardOpenBranchCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardActivatedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardActivatedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardIssueDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardIssueDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardProperty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardInstallmentAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardInstallmentAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardMinimumInstallmentAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardMinimumInstallmentAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardInstallmentDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardInstallmentDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardCurrentLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardCurrentLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardOtherAccountOutstanding");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardOtherAccountOutstanding"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardCustomerTotalLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardCustomerTotalLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardCustomerCurrentLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardCustomerCurrentLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardExpiredDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardExpiredDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardPaneID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardPaneID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardOwnerName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardOwnerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardLockStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardLockStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardLockDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardLockDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardLockExpDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardLockExpDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardPaidAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardPaidAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardPaidAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardPaidAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardPaidType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardPaidType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardCasaAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardCasaAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardOnlineStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardOnlineStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CIFInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CIFInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "CIFDataType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OTPData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OTPData"));
        elemField.setXmlType(new javax.xml.namespace.QName("bian.scb.com.vn", "OTPDataType"));
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
        return new org.apache.axis.encoding.ser.BeanSerializer(
                _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
            java.lang.String mechType,
            java.lang.Class _javaType,
            javax.xml.namespace.QName _xmlType) {
        return new org.apache.axis.encoding.ser.BeanDeserializer(
                _javaType, _xmlType, typeDesc);
    }

    /**
     * @return the cardUnpostAmount
     */
    public java.lang.String getCardUnpostAmount() {
        return cardUnpostAmount;
    }

    /**
     * @param cardUnpostAmount the cardUnpostAmount to set
     */
    public void setCardUnpostAmount(java.lang.String cardUnpostAmount) {
        this.cardUnpostAmount = cardUnpostAmount;
    }

    /**
     * @return the cardOutstandingAmountNotUnpost
     */
    public java.lang.String getCardOutstandingAmountNotUnpost() {
        return cardOutstandingAmountNotUnpost;
    }

    /**
     * @param cardOutstandingAmountNotUnpost the cardOutstandingAmountNotUnpost
     * to set
     */
    public void setCardOutstandingAmountNotUnpost(java.lang.String cardOutstandingAmountNotUnpost) {
        this.cardOutstandingAmountNotUnpost = cardOutstandingAmountNotUnpost;
    }

    /**
     * @return the cardPaidCurrentAmount
     */
    public java.lang.String getCardPaidCurrentAmount() {
        return cardPaidCurrentAmount;
    }

    /**
     * @param cardPaidCurrentAmount the cardPaidCurrentAmount to set
     */
    public void setCardPaidCurrentAmount(java.lang.String cardPaidCurrentAmount) {
        this.cardPaidCurrentAmount = cardPaidCurrentAmount;
    }

    /**
     * @return the cardIPPPaidAmount
     */
    public java.lang.String getCardIPPPaidAmount() {
        return cardIPPPaidAmount;
    }

    /**
     * @param cardIPPPaidAmount the cardIPPPaidAmount to set
     */
    public void setCardIPPPaidAmount(java.lang.String cardIPPPaidAmount) {
        this.cardIPPPaidAmount = cardIPPPaidAmount;
    }

    /**
     * @return the cardIPPStatementAmount
     */
    public java.lang.String getCardIPPStatementAmount() {
        return cardIPPStatementAmount;
    }

    /**
     * @param cardIPPStatementAmount the cardIPPStatementAmount to set
     */
    public void setCardIPPStatementAmount(java.lang.String cardIPPStatementAmount) {
        this.cardIPPStatementAmount = cardIPPStatementAmount;
    }

    /**
     * @return the cardIPPCurrentAmount
     */
    public java.lang.String getCardIPPCurrentAmount() {
        return cardIPPCurrentAmount;
    }

    /**
     * @param cardIPPCurrentAmount the cardIPPCurrentAmount to set
     */
    public void setCardIPPCurrentAmount(java.lang.String cardIPPCurrentAmount) {
        this.cardIPPCurrentAmount = cardIPPCurrentAmount;
    }

    /**
     * @return the cardInstallmentAmountPre
     */
    public java.lang.String getCardInstallmentAmountPre() {
        return cardInstallmentAmountPre;
    }

    /**
     * @param cardInstallmentAmountPre the cardInstallmentAmountPre to set
     */
    public void setCardInstallmentAmountPre(java.lang.String cardInstallmentAmountPre) {
        this.cardInstallmentAmountPre = cardInstallmentAmountPre;
    }

    /**
     * @return the cardMinimumInstallmentAmountPre
     */
    public java.lang.String getCardMinimumInstallmentAmountPre() {
        return cardMinimumInstallmentAmountPre;
    }

    /**
     * @param cardMinimumInstallmentAmountPre the
     * cardMinimumInstallmentAmountPre to set
     */
    public void setCardMinimumInstallmentAmountPre(java.lang.String cardMinimumInstallmentAmountPre) {
        this.cardMinimumInstallmentAmountPre = cardMinimumInstallmentAmountPre;
    }

    /**
     * @return the cardPaidCurrentAmountPre
     */
    public java.lang.String getCardPaidCurrentAmountPre() {
        return cardPaidCurrentAmountPre;
    }

    /**
     * @param cardPaidCurrentAmountPre the cardPaidCurrentAmountPre to set
     */
    public void setCardPaidCurrentAmountPre(java.lang.String cardPaidCurrentAmountPre) {
        this.cardPaidCurrentAmountPre = cardPaidCurrentAmountPre;
    }

    /**
     * @return the cardInstallmentDatePre
     */
    public java.lang.String getCardInstallmentDatePre() {
        return cardInstallmentDatePre;
    }

    /**
     * @param cardInstallmentDatePre the cardInstallmentDatePre to set
     */
    public void setCardInstallmentDatePre(java.lang.String cardInstallmentDatePre) {
        this.cardInstallmentDatePre = cardInstallmentDatePre;
    }

    public String getCardImageId() {
        return cardImageId;
    }

    public void setCardImageId(String cardImageId) {
        this.cardImageId = cardImageId;
    }

     public String getCardTypeCode() {
        return cardTypeCode;
    }

    public void setCardTypeCode(String cardTypeCode) {
        this.cardTypeCode = cardTypeCode;
    }
}
