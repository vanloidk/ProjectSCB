/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

/**
 *
 * @author KimNCM
 */
public class MessageMB {

    /**
     *
     */
    public enum MobileResultEnum {

        /**
         *
         */
        OK("00"),

        /**
         *
         */
        INVALID_TOKEN("-1"),

        /**
         *
         */
        LOCK_TOKEN("-3"),

        /**
         *
         */
        NO_EXISTS_TRANSACTION("-25"),

        /**
         *
         */
        NO_EXISTS_ACCOUNT_TYPE("-20"),

        /**
         *
         */
        NO_EXISTS_RATE("-21"),

        /**
         *
         */
        CANNOT_OPENTD_FCUBS("-22"),

        /**
         *
         */
        CANNOT_CLOSETD_FCUBS("-23"),

        /**
         *
         */
        CANNOT_SEND_SMS("-24"),

        /**
         *
         */
        NO_VALID_AMTOPENTD("-26"),

        /**
         *
         */
        CANNOT_REDEMP_ACCOUNTTYPE("-27"),

        /**
         *
         */
        STAFF_NOT_FOUND("-29"),

        /**
         *
         */
        CANNOT_ADD_FEEDBACK("-28"),
        //NO_EXISTS_AZ_ACCOUNT("-30"),

        /**
         *
         */
        NO_EXISTS_ACCOUNT("-30"),

        /**
         *
         */
        NO_EXISTS_TOKEN("79"),

        /**
         *
         */
        IBT_CARDACCNONOTCORRECT("09"),

        /**
         *
         */
        IBT_CARDACCNONOTVALID_08("108"),

        /**
         *
         */
        IBT_CARDACCNONOTVALID_10("10"),

        /**
         *
         */
        IBT_CARDACCNONOTVALID_13("13"),

        /**
         *
         */
        IBT_CARDACCNONOTVALID_39("39"),

        /**
         *
         */
        IBT_CARDEXPIRED("12"),

        /**
         *
         */
        IBT_BENEBANKINVALID("14"),

        /**
         *
         */
        IBT_CARDACCNOOFSCB_05("05"),

        /**
         *
         */
        IBT_CARDACCNOOFSCB_25("25"),

        /**
         *
         */
        IBT_INSUFFIAMT("04"),

        /**
         *
         */
        IBT_INVALIDSRCACCNO("06"),

        /**
         *
         */
        IBT_TRAN_ERROR_19("19"),

        /**
         *
         */
        IBT_CARDACCDOUBT_11("11"),

        /**
         *
         */
        IBT_CARDACCERROR_15("15"),

        /**
         *
         */
        IBT_CARDACCERROR_07("07"),

        /**
         *
         */
        IBT_CARDACCEXCEEDAMT("23"),

        /**
         *
         */
        IBT_CARDACCBELOWAMT("22"),

        /**
         *
         */
        IBT_TRAN_ERROR("-11"),

        /**
         *
         */
        IBT_TRAN_ERROR_02("02"),

        /**
         *
         */
        IBT_TRAN_ERROR_03("03"),

        /**
         *
         */
        IBT_TRAN_ERROR_16("16"),

        /**
         *
         */
        IBT_TRAN_INSUFFIAMT("04"),

        /**
         *
         */
        IBT_TRAN_TIMEOUT_18("18"),

        /**
         *
         */
        IBT_TRAN_TIMEOUT_24("24"),

        /**
         *
         */
        CANNOT_ADD_LOANREGISTER("-138"),

        /**
         *
         */
        CANNOT_EXTEND_ATM("-149"),

        /**
         *
         */
        CANNOT_ADD_ATMREGISTER("-131"),

        /**
         *
         */
        CANNOT_ADD_MCREGISTER("-132"),

        /**
         *
         */
        NO_EXISTS_CARDTYPE("-134"),

        /**
         *
         */
        CANNOT_REISSUE_PIN("-135"),

        /**
         *
         */
        NO_EXISTS_STATEMENT("-136"),

        /**
         *
         */
        NO_EXISTS_INFOISSUE("-139"),

        /**
         *
         */
        ERROR_ALERTTD("-141"),

        /**
         *
         */
        AMTREDEMP_GREATER_AZAVL("-143"),

        /**
         *
         */
        NO_EXISTS_CARDACCOUNT("-144"),

        /**
         *
         */
        REISSUE_CONFIRM("-145"),

        /**
         *
         */
        ISSUEATM_SAMEATMTYPE_CONFIRM("-146"),

        /**
         *
         */
        REPIN_EXPIREDCARD("-147"),

        /**
         *
         */
        NO_EXISTS_OTHERACCOUNT_FORFEE("-148"),

        /**
         *
         */
        CANNOT_PAYMENT_SALARY("-150"),

        /**
         *
         */
        EXISTS_OTHERACCOUNT_FORFEE("-151"),

        /**
         *
         */
        CANNOT_PAYMENTCREDIT_NOT_REFUND("-152"),

        /**
         *
         */
        CANNOT_PAYMENTCREDIT_REFUNDED("-153"),

        /**
         *
         */
        NOTSUPPORT_OLD_PAYMENTCREDIT("-154"),

        /**
         *
         */
        CANNOT_ISSUE_SLAVEATM("-155"),

        /**
         *
         */
        CANNOT_PAYMENTCREDIT_NOTACTIVE("-156"),

        /**
         *
         */
        NOTSUPPORT_OLD_VERSION("-157"),

        /**
         *
         */
        SECURITIES_WRONG_PARAMETER("-1014"),

        /**
         *
         */
        SECURITIES_NOTSUCCESS_REFUNDED("-1011"),

        /**
         *
         */
        SECURITIES_NOTSUCCESS_NOREFUNDED("-1012"),

        /**
         *
         */
        OPENCLOSE_CARD_NOTSUCCESS("-1020"),

        /**
         *
         */
        EXISTED_CUSTOMERCODE_BILL("-1030"),

        /**
         *
         */
        NOTSUCCESS_REGISTER_AUTOBILL("-1031"),

        /**
         *
         */
        NOTSUCCESS_PAYLOAN("-1032"),

        /**
         *
         */
        NOTSUCCESS("-1033"),

        /**
         *
         */
        INVALID_MINAMOUNT_AZ("-1034"),

        /**
         *
         */
        NOT_EXIST_AUTOBILLS("-1035"),

        /**
         *
         */
        NOT_EXIST_INSURANCECONTRACT("-1036"),

        /**
         *
         */
        NOT_DEBT_INSURANCECONTRACT("-1037"),

        /**
         *
         */
        NOTVALID_CARD_ISSUE("-1038"),

        /**
         *
         */
        INVALID_MINAMOUNT_TENOR_AZ("-1039"),

        /**
         *
         */
        INVALID_CREDITCARD_ACCOUNT_NO("-1040"),

        /**
         *
         */
        INVALID_MATURITYDATE_TD("-1041"),

        /**
         *
         */
        CHECKTD_EOD("-1042"),

        /**
         *
         */
        ERROR_TRANS_JOINT("-1043"),

        /**
         *
         */
        NO_EXISTS_ACCOUNT_QR("-1044"),

        /**
         *
         */
        ERROR_SYSTEM("-99"),

        /**
         *
         */
        IBT_TRAN_ERROR_96("96"),

        /**
         *
         */
        CANNOT_TOPUPTD_15("-1045"),

        /**
         *
         */
        CANNOT_TOPUPTD_17("-1046"),

        /**
         *
         */
        CANNOT_TOPUPTD_18("-1051"),

        /**
         *
         */
        NO_LOGIN_ACCOUNT_QR("-1047"),

        /**
         *
         */
        NOT_GETCHALLENGCODE("-1048"),

        /**
         *
         */
        NOT_DOMESTICCUST("-1049"),

        /**
         *
         */
        NOT_REDEMTION("-1050"),

        /**
         *
         */
        NOT_SENDEMAIL_OPENTD("-1051"),

        /**
         *
         */
        NOT_UPDATE_EMAIL("-1052"),

        /**
         *
         */
        CANNOT_ADD_CASAREGISTER("-1053"),

        /**
         *
         */
        CANNOT_ADD_GUARANTEEREGISTER("-1054"),
        

        /**
         *
         */
        CANNOT_LOCK_CARDTB("-1055"),
        CANNOT_PAYMENT_WITH_SPECIALACCCLASS("-1056"),
        TEMP_CODE("-6666"),
        CARD_MAINTENANCE("6666"),
        IB_NEWUSER_EXITCIF("-1058"),
        IB_NEWUSER_EXITUSER("-1059"),
        VNPT_EXISTED_CUSTOMERCODE_BILL("-3030"),
        IBT_OVERKYC("2800");
        
        
        
         
        private String code;

        private MobileResultEnum(String c) {
            code = c;
        }

        /**
         *
         * @param value
         * @return
         */
        public static MobileResultEnum get(String value) {
            for (MobileResultEnum pce : MobileResultEnum.values()) {
                if (pce.getValue().equals(value)) {
                    return pce;
                }
            }
            return null;
        }

        /**
         *
         * @return
         */
        public String getValue() {
            return code;
        }
    }

    /**
     *
     * @param pre
     * @return
     */
    public static String getMessageMBResult(MobileResultEnum pre) {
        switch (pre) {
            case OK:
                return "Giao dịch thành công.";
            case NO_EXISTS_RATE:
                return "Không tồn tại bảng tỉ giá.";
            case CANNOT_OPENTD_FCUBS:
                return "Không thực hiện được giao dịch mở tài khoản.";
            case CANNOT_CLOSETD_FCUBS:
                return "Không thực hiện được giao dịch đóng tài khoản.";
            case CHECKTD_EOD:
                return "Hệ thống đang xử lý dữ liệu EOD.";
            case CANNOT_SEND_SMS:
                return "Không gửi được tin nhắn.";
            case NO_VALID_AMTOPENTD:
                return "Số tiền gửi tiết kiệm nhỏ hơn số tiền gửi tối thiểu.";
            case NO_EXISTS_TRANSACTION:
                return "Không tồn tại giao dịch.";
            case NO_EXISTS_TOKEN:
                return "Không tồn tại token.";
            case NO_EXISTS_ACCOUNT_TYPE:
                return "Không tồn tại loại hình tiết kiệm.";
            //case NO_EXISTS_AZ_ACCOUNT:
            //return "Không tồn tại tài khoản tiết kiệm cần tất toán.";
            case NO_EXISTS_ACCOUNT:
                return "Không tồn tại tài khoản cho chức năng này";
            case INVALID_TOKEN:
                return "Mã chứng thực không hợp lệ.";
            case LOCK_TOKEN:
                return "Token đã bị khóa";
            case CANNOT_REDEMP_ACCOUNTTYPE:
                return "Bạn không thể tất toán loại hình tiền gửi này";
            case IBT_CARDACCNONOTCORRECT:
                return "Số thẻ đích/tài khoản đích không đúng, vui lòng nhập lại";
            case IBT_CARDACCNONOTVALID_08:
                return "Thẻ đích/Tài khoản đích không hợp lệ.";
            case IBT_CARDACCNONOTVALID_10:
                return "Thẻ đích/Tài khoản đích không hợp lệ.";
            case IBT_CARDACCNONOTVALID_13:
                return "Thẻ đích/Tài khoản đích không hợp lệ.";
            case IBT_CARDACCNONOTVALID_39:
                return "Thẻ đích/Tài khoản đích không hợp lệ.";
            case IBT_CARDEXPIRED:
                return "Thẻ đích đã hết hạn sử dụng.";
            case IBT_BENEBANKINVALID:
                return "Ngân hàng thụ hưởng chưa tham gia dịch vụ.";
            case IBT_CARDACCNOOFSCB_05:
                return "Giao dịch không thực hiện được do thẻ/tài khoản nguồn không hợp lệ.";
            case IBT_CARDACCNOOFSCB_25:
                return "Giao dịch không thực hiện được do thẻ/tài khoản đích thuộc cùng SCB.";
            case IBT_INSUFFIAMT:
                return "Số dư tài khoản nguồn không đủ để thực hiện giao dịch.";
            case IBT_INVALIDSRCACCNO:
                return "Tài khoản nguồn không hợp lệ, vui lòng kiểm tra lại.";
            case IBT_TRAN_ERROR_19:
                //return "Thẻ đích/tài khoản đích ở dạng nghi vấn.";
                return "Giao dịch lỗi, quý khách vui lòng thực hiện lại giao dịch.";
            case IBT_CARDACCDOUBT_11:
                return "Thẻ đích/tài khoản đích ở dạng nghi vấn.";
            case IBT_CARDACCERROR_15:
                return "Giao dịch lỗi, quý khách vui lòng thực hiện lại giao dịch.";
            case IBT_CARDACCERROR_07:
                return "Giao dịch lỗi, quý khách vui lòng thực hiện lại giao dịch.";
            case IBT_CARDACCEXCEEDAMT:
                return "Số tiền giao dịch vượt quá hạn mức cho phép cho 01 giao dịch (50.000.000 VND).";
            case IBT_CARDACCBELOWAMT:
                return "Số tiền giao dịch ít hơn hạn mức quy định.";
            case IBT_TRAN_ERROR:
                return "Giao dịch lỗi, quý khách vui lòng thực hiện lại giao dịch.";
            case IBT_TRAN_ERROR_02:
                return "Giao dịch lỗi, quý khách vui lòng thực hiện lại giao dịch.";
            case IBT_TRAN_ERROR_03:
                return "Giao dịch lỗi, quý khách vui lòng thực hiện lại giao dịch.";
            case IBT_TRAN_INSUFFIAMT:
                return "Giao dịch lỗi, tài khoản không đủ số dư để thực hiện lại giao dịch.";
            case IBT_TRAN_ERROR_16:
                return "Giao dịch lỗi, quý khách vui lòng thực hiện lại giao dịch. Trong trường hợp tài khoản của Quý khách đã bị trừ tiền, chúng tôi sẽ tiến hành hoàn trả sau thời gian tra soát.";
            case IBT_TRAN_TIMEOUT_18:
                return "Giao dịch đang được xử lý tại ngân hàng thụ hưởng, quý khách vui lòng kiểm tra với người nhận.";
            case IBT_TRAN_TIMEOUT_24:
                return "Giao dịch đang được xử lý tại ngân hàng thụ hưởng, quý khách vui lòng kiểm tra với người nhận.";
            case STAFF_NOT_FOUND:
                return "Không tìm thấy mã số nhân viên.";
            case CANNOT_ADD_FEEDBACK:
                return "Không lưu được góp ý.";
            case CANNOT_ADD_LOANREGISTER:
                return "Không lưu được thông tin vay.";
            case CANNOT_EXTEND_ATM:
                return "Thẻ của Quý Khách vẫn còn trong thời hạn hiệu lực hoặc đã quá thời gian gia hạn thẻ.";
            case CANNOT_ISSUE_SLAVEATM:
                return "Thẻ của quý khách đã đóng/ khóa hoặc không tồn tại. Quý khách phải phát hành thẻ chính trước khi phát hành thẻ phụ. LH 1800545438 để biết thêm chi tiết";
            case CANNOT_ADD_ATMREGISTER:
                return "Không lưu dc thông tin phát hành thẻ ATM.";
            case NO_EXISTS_CARDTYPE:
                return "Không lấy dc danh sách loại hình thẻ tín dụng.";
            case CANNOT_ADD_MCREGISTER:
                return "Không lưu dc thông tin phát hành thẻ tín dụng.";
            case CANNOT_REISSUE_PIN:
                return "Không lưu dc thông tin đổi PIN.";
            case NO_EXISTS_STATEMENT:
                return "Không tồn tại sao kê thẻ tín dụng.";
            case NO_EXISTS_INFOISSUE:
                return "Không tồn tại sao thông tin tra cứu.";
            case ERROR_ALERTTD:
                return "Lỗi đăng ký thông báo đến hạn.";
            case AMTREDEMP_GREATER_AZAVL:
                return "Số tiền tất toán phải nhỏ hơn số dư khả dụng của tài khoản.";
            case NO_EXISTS_CARDACCOUNT:
                return "Thẻ của quý khách đã đóng hoặc không tồn tại. Quý khách chỉ có thể phát hành Thẻ mới. LH 1800545438 để biết thêm chi tiết.";
            case REISSUE_CONFIRM:
                return "Thẻ của quý khách sắp hết hạn, thẻ mới được cấp lại có hiệu lực tương đương thẻ cũ (cảnh báo).";
            case ISSUEATM_SAMEATMTYPE_CONFIRM:
                return "Quý khách hiện đã sở hữu loại thẻ này. Phát hành thẻ mới sẽ làm thẻ hiện tại mất hiệu lực. LH 1800545438 để biết thêm chi tiết (cảnh báo).";
            case REPIN_EXPIREDCARD:
                return "Thẻ của quý khách đã hết hạn. Quý khách có thể chọn chức năng Gia hạn thẻ hoặc Cấp lại thẻ. LH 1800545438 để biết thêm chi tiết.";
            case NO_EXISTS_OTHERACCOUNT_FORFEE:
                return "Không tồn tại tài khoản khác thỏa điều kiện thu phí.";
            case CANNOT_PAYMENT_SALARY:
                return "Không thể thực hiện giao dịch từ tài khoản lương. Vui lòng chọn lại tài khoản.";
            case EXISTS_OTHERACCOUNT_FORFEE:
                return "Tồn tại tài khoản khác thỏa điều kiện thu phí";
            case CANNOT_PAYMENTCREDIT_NOT_REFUND:
                return "Ngân hàng sẽ tiến hành tra soát giao dịch của Quý khách tối đa 2 Ngày làm việc tiếp theo.";
            case CANNOT_PAYMENTCREDIT_REFUNDED:
                return "Thanh toán thẻ tín dụng không thành công – đã hoàn tiền cho khách hàng.";
            case NOTSUPPORT_OLD_PAYMENTCREDIT:
                return "Không hỗ trợ thanh toán thẻ tín dụng theo chuẩn cũ.";
            case CANNOT_PAYMENTCREDIT_NOTACTIVE:
                return "Thanh toán thẻ không thành công do thẻ chưa kích hoạt. Vui lòng LH 1800545438 để được kích hoạt.";
            case NOTSUPPORT_OLD_VERSION:
                return "Quý khách vui lòng nâng cấp ứng dụng trước khi tiếp tục sử dụng dịch vụ.";
            case ERROR_SYSTEM:
                return "Loi he thong.";
            //upgrade mobile               
            case SECURITIES_WRONG_PARAMETER:
                return "Nộp tiền chứng khoán sai thông số.";
            case SECURITIES_NOTSUCCESS_REFUNDED:
                return "Nộp tiền chứng khoán không thành công – đã hoàn tiền cho khách hàng.";
            case SECURITIES_NOTSUCCESS_NOREFUNDED:
                return "Nộp tiền chứng khoán không thành công – chờ tra soát.";
            case OPENCLOSE_CARD_NOTSUCCESS:
                return "Khóa/Mở thẻ không thành công.";
            case EXISTED_CUSTOMERCODE_BILL:
                return "Mã khách hàng đã tồn tại trong hợp đồng khác.";
                
            case VNPT_EXISTED_CUSTOMERCODE_BILL:
                return "Mã thanh toán/SĐT đã tồn tại trong hợp đồng khác.";   
                
            case NOTSUCCESS_REGISTER_AUTOBILL:
                return "Đăng ký thanh toán hóa đơn tự động không thành công.";
            case NOTSUCCESS_PAYLOAN:
                return "Thanh toán khoản vay không thành công.";
            case NOTSUCCESS:
                return "Giao dich không thành công.";
            case INVALID_MINAMOUNT_AZ:
                return "Số tiền còn lại phải lớn hơn số dư tối thiểu tiết kiệm.";
            case NOT_EXIST_AUTOBILLS:
                return "Quý khách chưa đăng ký hợp đồng hóa đơn tự động";
            case NOT_EXIST_INSURANCECONTRACT:
                return "Giao dịch không thành công do không tìm thấy thông tin khách hàng tại nhà cung cấp. Quý khách vui lòng kiểm tra lại.";
            case NOT_DEBT_INSURANCECONTRACT:
                return "Quý Khách đã hoàn thành thanh toán.";
            case NOTVALID_CARD_ISSUE:
                return "Thẻ của quý khách không hợp lệ (chưa kích hoạt/khóa). Quý khách vui lòng chọn lại thẻ khác.";
            case INVALID_MINAMOUNT_TENOR_AZ:
                return "Số dư tài khoản tiền gửi tiết kiệm tối thiếu là 20.000.000 sau khi tất toán 1 phần.";
            case INVALID_CREDITCARD_ACCOUNT_NO:
                return "Số tài khoản thẻ không đúng. Quý khách vui lòng kiểm tra lại.";
            case INVALID_MATURITYDATE_TD:
                return "Ngày đáo hạn không đúng theo kỳ hạn đã chọn hoặc trùng vào ngày nghỉ/lễ. Quý khách vui lòng kiểm tra lại.";
            case NO_EXISTS_ACCOUNT_QR:
                return "Sổ không tồn tại hoặc đã tất toán";
            case ERROR_TRANS_JOINT:
                return "Giao dich không được thực hiện với tài khoản đồng chủ sở hữu. Quý khách vui lòng liên hệ SCB nếu muốn tiếp tục giao dịch này.";
            case IBT_TRAN_ERROR_96:
                return "Giao dịch đang được xử lý tại ngân hàng thụ hưởng, quý khách vui lòng kiểm tra với người nhận.";
            case CANNOT_TOPUPTD_15:
                return "Top-Up not allowed for the account after crossing block duration as on maturity date.";
            case CANNOT_TOPUPTD_17:
                return "The allowed maximum currency restriction amount for the account class";
            case CANNOT_TOPUPTD_18:
                return "Quý khách không thể nộp tiền vào tk tích lũy của người khác.";
            case NO_LOGIN_ACCOUNT_QR:
                return "Quý khách vui lòng đăng nhập ứng dụng để tiếp tục thực hiện giao dịch này";
            case NOT_GETCHALLENGCODE:
                return "Không lấy được Challenge Code";
            case NOT_DOMESTICCUST:
                return "KH nuoc ngoai ko dc mo tiet kiem";
            case NOT_REDEMTION:
                return "Khong dc rut 1 phan";
            case NOT_SENDEMAIL_OPENTD:
                return "Không gửi được email mở tiết kiệm";
            case NOT_UPDATE_EMAIL:
                return "Không cập nhật email KH";
            case CANNOT_ADD_CASAREGISTER:
                return "Đăng ký mở tài khoản thanh toán thất bại";
            case CANNOT_ADD_GUARANTEEREGISTER:
                return "Đăng ký bảo lãnh thất bại";
            case CANNOT_LOCK_CARDTB:
                return "Thẻ này không được hỗ trợ mở khóa online. Vui lòng liên hệ hotline hoặc đến quầy giao dịch để được hỗ trợ.";
            case CANNOT_PAYMENT_WITH_SPECIALACCCLASS:
                return "Tài khoản  vốn chuyên dùng không được giao dịch online. Vui lòng liên hệ hotline hoặc đến quầy giao dịch để được hỗ trợ.";
            case TEMP_CODE:
                    return "Giao dịch đang được xử lý.";
            case CARD_MAINTENANCE:
                return "Thẻ bảo trì";
            case IB_NEWUSER_EXITCIF:
                return "KH đã tồn tại Username khác trên MB. Vui lòng tạo giống Username";          
            case IB_NEWUSER_EXITUSER:
                return "Đã tồn tại Username này trên MB của KH khác. Vui lòng chọn Username khác";                                
            case IBT_OVERKYC:
                return "Vượt hạn mức giao dịch (eKYC).";
            default:
                return "Loi khac";

        }
    }
}
