/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.enumUtils;

/**
 *
 * @author baotbq
 */
public enum NapasResultEnum {

    /**
     *
     */
    SUCCESSFULL("0", "Successful"), //Thành công

    /**
     *
     */
    INVALID_REQUEST("1", "Invalid request"), //Yêu cầu không hợp lệ

    /**
     *
     */
    WRONG_PARTNER_PASSWORD("2", "Wrong partner password"), //Sai mật khẩu đối tác

    /**
     *
     */
    EXPIRED_TRANSACTION("3", "Expired transaction"), //Giao dịch đã hết hạn

    /**
     *
     */
    WRONG_SIGNATURE("4", "Wrong signature"), //Sai chữ kí

    /**
     *
     */
    TRANSACTION_DOESNOT_EXSIT("5", "Transaction does not exist"), //Giao dịch không tồn tại

    /**
     *
     */
    INVALID_TRANSACTION("6", "Invalid transaction"), //Giao dịch không hợp lệ

    /**
     *
     */
    INVALID_TRANSACTION_STATUS("7", "Invalid transaction status"), //Trạng thái giao dịch không hợp lệ

    /**
     *
     */
    DUPLICATE_TRANSACTION("8", "Duplicate transaction"), //Giao dịch bị trùng lặp

    /**
     *
     */
    WRONG_CARD_INFO("9", "Wrong card info"), //Sai thông tin thẻ

    /**
     *
     */
    WRONG_NUMBER_ON_CARDACCOUNT_NUMBER("10", "Wrong card number/account number"), //Sai số thẻ/số tk

    /**
     *
     */
    WRONG_NAME_ON_CARDACCOUNT_NAME("11", "Wrong name on card/account name"), //Sai tên trên thẻ/tên trên tk

    /**
     *
     */
    WRONG_CARD_EXPIRY("12", "Wrong card expiry"), //Sai thẻ hết hạn

    /**
     *
     */
    WRONG_ISSUE_DATE("13", "Wrong issue date"), //Sai ngày hết hạn

    /**
     *
     */
    CARD_ACCOUNT_IS_INVALID("14", "Card/Account is invalid"), //Tài khoản không hợp lệ

    /**
     *
     */
    CARD_ACCOUNT_IS_LOCKED("15", "Card/Account is locked"), //Thẻ/Tk bị khóa

    /**
     *
     */
    CARD_ACCOUNT_NOT_REGISTERED_ONLINEPAYMENT("16", "Card/Account haven’t been registered for online payment"), //Thẻ/Tài khoản chưa được đăng ký để thanh toán trực tuyến

    /**
     *
     */
    TRANSACTION_AMOUNT_EXCEEDS_ONE_TRANSACTION_LIMIT("17", "Transaction amount exceeds one transaction limit"), //Số tiền giao dịch vượt quá một giới hạn giao dịch

    /**
     *
     */
    TRANSACTION_AMOUNT_EXCEEDS_LIMIT_PERDAY("18", "Transaction amount exceeds limit per day"), //Số tiền giao dịch vượt quá giới hạn mỗi ngày

    /**
     *
     */
    CARD_ACCOUNT_INSUFFICIENT_BALANCE("19", "Card/Account has insufficient balance"), //Thẻ/Tk không đủ số dư

    /**
     *
     */
    WRONG_OTP("20", "Wrong OTP"), //Sai OTP

    /**
     *
     */
    EXPIRED_OTP("21", "Expired OTP"), //OTP hết hạn

    /**
     *
     */
    NOT_ALLOWED_TRANSACTION("97", "Not allowed transaction"), //Giao dịch không được cho phép

    /**
     *
     */
    OTHER_ERROR("98", "Other error"), //Lỗi khác

    /**
     *
     */
    INTERNAL_ERROR("99", "Internal error"); //Lỗi nội bộ
    
    public static NapasResultEnum get(String value) {
        for (NapasResultEnum pce : NapasResultEnum.values()) {
            if (pce.getErrorcode().equals(value)) {
                return pce;
            }
        }
        return null;
    }
	String code ;
	  private NapasResultEnum(String c) {
          code = c;
      }  
	public String getValue() {
        return code;
    }

    private String errorcode;
    private String description;

    private NapasResultEnum(String errorcode, String description) {
        this.errorcode = errorcode;
        this.description = description;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return errorcode + ": " + description;
    }

}
