package scb.com.vn.message;

public class Message {

    public enum WebserviceResultEnum {

        VALID("0"), AUTHEN_INVALID("-1"), OVERTIME("-5"), SOAPHEADER_INVALID("-6"), SOAPHEADERELELEMENT_INVALID("-7"), PARTNERCODE_INVALID(
                "-8"), VERIFYSIGN_INVALID("-9"), NOSUCHMETHOD("-10"), SYSTEM_ERR("-99");
        private String code;

        private WebserviceResultEnum(String c) {
            code = c;
        }

        public static WebserviceResultEnum get(String value) {
            for (WebserviceResultEnum pce : WebserviceResultEnum.values()) {
                if (pce.getValue().equals(value)) {
                    return pce;
                }
            }
            return null;
        }

        public String getValue() {
            return code;
        }
    }

    public enum PaymentResultEnum {

        OK("0"), OK_PAIDBILL_PARTNER("1"), OK_TRANSFER_FCUBS("2"),
        STATUS_IS_INACTIVE("-49"), NOTAPPROVED("-48"), BEFORE_VALIDDATE("-47"), AFTER_EXPIREDATE("-46"), NOTMATCH_DAYINMONTH("-45"),
        CANNOT_TRANSFERFCUBS("-59"),
        ACCNO_NOT_FOUND("-69"), NOT_ENOUGH_AMT_TO_PAY("-68"), NOT_MATCH_CCY_VND("-67"), ACCNO_NOTMATCH_TYPECASA("-66"),
        NO_CUSTOMER_EXISTS("-75"), POSTPAY_SUBSCRIBE("-74"), NO_VALID_SUBSCRIBE("-73"), NO_VALID_DENOMINATION("-72"), INVALID_PROCESS("-71"), INVALID_CONFIRMCODE("-70"),
        NO_ENOUGH_TRANS_LIMIT("-80"), BILLCODE_INVALID("-79"), BILL_NOTEXIST("-78"), BILL_PAID("-77"), CANNOT_PAIDBILL_PARTNER("-76"),
        HEADERPARAM_INVALID("-89"), CHANNEL_NOTEXSIST("-88"), SERVICECODE_NOTEXSIST("-87"), PROVIDERCODE_NOTEXSIST("-86"), PROCESSINGCODE_NOTEXSIST("-85"), REQUESTPARAM_INVALID("-84"),
        ERROR_PARTNER("-100"),ERROR_PARTNER_CASE_TO_REFUND("-110"), ERROR_PARTNER_REFUND("-120"), SYSTEM_ERROR("-99"), PARTNER_INVALID("-98"), TIMEOUT("-97"), TRANSCODE_LOOP("-96"),
        CANNOT_PAYMENT_NOT_REFUND("-101"), CANNOT_PAYMENT_REFUNDED("-102"), ERROR_BCN("-121"), PAYMENT_FAIL("-122"), DO_NOT_OWE("-123"), OUT_OF_STOCK("-126"), NOT_ENOUGH_QUANTITY("-127"),
        TOPUP_IN_PROCESSING("-128"), CUSTOMERCODE_INVALID("-129"), CHECK_LIMIT_OVER("-130"), QUANTITY_CARD_INVALID("-131"), EXCHANGE_NOT_EXIST("-132"),
        NOT_FOUND_CUSTOMER_INFO("-161"),BILLCODE_EXPIRED("-164"), BILLCODE_INCORRECT("-166"), SICODE_INVALID("-125"), PAYCREDITCARD_TIMEOUT("-165"), ACCOUNT_NOCREDIT("-167"), VNPT_CODE_NOT_EXISTED("-170");

        private String code;

        private PaymentResultEnum(String c) {
            code = c;
        }

        public static PaymentResultEnum get(String value) {
            for (PaymentResultEnum pce : PaymentResultEnum.values()) {
                if (pce.getValue().equals(value)) {
                    return pce;
                }
            }
            return null;
        }

        public String getValue() {
            return code;
        }
    }

    public static String getMessagePaymentResult(PaymentResultEnum pre) {
        switch (pre) {
            case OK:
                return "Thanh toán hóa đơn thành công";
            case OK_PAIDBILL_PARTNER:
                return "Yêu cầu đối tác thanh toán hóa đơn thành công";
            case OK_TRANSFER_FCUBS:
                return "Yêu cầu core banking thanh toán hóa đơn thành công";
            case SYSTEM_ERROR:
                return "Hệ thống đang bảo trì. Vui lòng quay lại lần sau.";
            case ERROR_PARTNER:
                return "Hệ thống đối tác đang bảo trì. Vui lòng quay lại lần sau.";
            case ERROR_PARTNER_REFUND:
                return "Giao dịch không thành công. Quý khách vui lòng thực hiện lại giao dịch.";
            case ERROR_PARTNER_CASE_TO_REFUND:
                return "Giao dịch đang được xử lý. Ngân hàng sẽ tiến hành tra soát giao dịch của Quý khách tối đa 2 Ngày làm việc tiếp theo.";
            case PARTNER_INVALID:
                return "Đối tác không hợp lệ.";
            case TIMEOUT:
                return "Quá thời gian ngân hàng yêu cầu đối tác thực hiện hóa đơn. Vui lòng quay lại lần sau.";
            case TRANSCODE_LOOP:
                return "Bị lặp mã giao dịch.";
            case HEADERPARAM_INVALID:
                return "Tham số header không hợp lệ.";
            case CHANNEL_NOTEXSIST:
                return "Kênh không tồn tại.";
            case SERVICECODE_NOTEXSIST:
                return "Mã dịch vụ không tồn tại.";
            case PROVIDERCODE_NOTEXSIST:
                return "Mã nhà cung cấp không hợp lệ.";
            case PROCESSINGCODE_NOTEXSIST:
                return "Mã xử lý không hợp lệ.";
            case REQUESTPARAM_INVALID:
                return "Tham số yêu cầu không hợp lệ.";
            case BILLCODE_INVALID:
                return "Mã khách hàng không tồn tại.";
            case BILL_NOTEXIST:
                return "Không có hóa đơn nào để thanh toán.";
            case BILL_PAID:
                return "Hóa đơn đã được thanh toán.";
            case CANNOT_PAIDBILL_PARTNER:
                return "Không thể thanh toán hóa đơn với đối tác.";
            case ACCNO_NOT_FOUND:
                return "Không tìm thấy tài khoản của khách hàng.";
            case NOT_ENOUGH_AMT_TO_PAY:
                return "Tài khoản của khách hàng không đủ tiền để thanh toán.";
            case NOT_MATCH_CCY_VND:
                return "Tài khoản của khách hàng không phải loại tiền tệ 'VND' để thanh toán.";
            case ACCNO_NOTMATCH_TYPECASA:
                return "Loại tài khoản để thanh toán không hợp lệ.";
            case CANNOT_TRANSFERFCUBS:
                return "Không thể thực hiện chuyển khoản từ tài khoản này.";
            case NO_CUSTOMER_EXISTS:
                return "Mã khách hàng không tồn tại.";
            case POSTPAY_SUBSCRIBE:
                return "Thuê bao được nạp tiền là thuê bao trả sau.";
            case NO_VALID_SUBSCRIBE:
                return "Mạng di động chưa được hỗ trợ hoặc Mã khách hàng chưa đăng ký thương mại điện tử.";
            case NO_VALID_DENOMINATION:
                return "Mệnh giá tiền không hợp lệ.";
            case INVALID_PROCESS:
                return "Giao dịch đang thực hiện không hợp lệ.";
            case INVALID_CONFIRMCODE:
                return "Mã chứng thực không hợp lệ.";
            case NO_ENOUGH_TRANS_LIMIT:
                return "Giá trị giao dịch đã vượt quá hạn mức giao dịch trong ngày.";
            case STATUS_IS_INACTIVE:
                return "Không thể thanh toán mã hóa đơn này do khu vực chưa được hỗ trợ hoặc hóa đơn hết hạn/bị khóa/đã thanh toán.";
            case CANNOT_PAYMENT_NOT_REFUND:
                return "Ngân hàng sẽ tiến hành tra soát giao dịch của Quý khách tối đa 2 Ngày làm việc tiếp theo.";
            case CANNOT_PAYMENT_REFUNDED:
                return "Hóa đơn thanh toán không thành công – đã hoàn tiền cho khách hàng.";
            case ERROR_BCN:
                return "Hóa đơn không tồn tại";
            case PAYMENT_FAIL:
                return "Cập nhật thanh toán thất bại";
            case DO_NOT_OWE:
                return "Khách hàng không còn nợ tiền";
            case NOT_FOUND_CUSTOMER_INFO:
                return "Không tìm thấy thông tin khách hàng";
            case BILLCODE_INCORRECT:
                return "Mã hóa đơn đã được thanh toán hoặc mã hóa đơn đã hết hạn thanh toán";
            case BILLCODE_EXPIRED:
                return "Mã khách hàng đã hết thời gian hiệu lực thanh toán";
            case SICODE_INVALID:
                return "Tài khoản chứng khoán không tồn tại! Vui lòng kiểm tra lại.";
            case PAYCREDITCARD_TIMEOUT:
                return "Thanh toán không thành công, lỗi timeout, Kiểm tra tài khoản casa và CardWork để biết tình trạng của giao dịch trước khi thực hiện lại giao dịch.";
            case OUT_OF_STOCK:
                return "Số lượng thẻ tạm hết. Vui lòng chọn mệnh giá khác.";
            case NOT_ENOUGH_QUANTITY:
                return "Số lượng thẻ còn lại: ";
            case TOPUP_IN_PROCESSING:
                return "Thông tin lệnh nạp tiền hợp lệ. Vui lòng chờ kết quả xử lý";
            case CUSTOMERCODE_INVALID:
                return "Mã khách hàng không tồn tại hoặc không hợp lệ";
            case CHECK_LIMIT_OVER:
                return "Mã giao dịch này đã check quá tối đa số lần cho phép";
            case QUANTITY_CARD_INVALID:
                return "Số lượng thẻ không hợp lệ";
            case EXCHANGE_NOT_EXIST:
                return "Giao dịch không tồn tại";
            case ACCOUNT_NOCREDIT:
                return "Tai khoan thu huong co yeu to nuoc ngoai.";
            case VNPT_CODE_NOT_EXISTED:
                return "Mã thanh toán/SĐT hiện không tồn tại. Quý khách vui lòng kiểm tra lại.";
            default:
                return "";
        }
    }

    public static String getMessagePaymentResult(PaymentResultEnum pre, String idlang) {
        if (idlang.equalsIgnoreCase("V")) {
            switch (pre) {
                case OK:
                    return "Thanh toán hóa đơn thành công";
                case OK_PAIDBILL_PARTNER:
                    return "Yêu cầu đối tác thanh toán hóa đơn thành công";
                case OK_TRANSFER_FCUBS:
                    return "Yêu cầu core banking thanh toán hóa đơn thành công";
                case SYSTEM_ERROR:
                    return "Hệ thống đang bảo trì. Vui lòng quay lại lần sau.";
                case ERROR_PARTNER:
                    return "Hệ thống nhà cung cấp dịch vụ đang bảo trì. Vui lòng quay lại lần sau.";
                case ERROR_PARTNER_REFUND:
                    return "Giao dịch không thành công. Quý khách vui lòng thực hiện lại giao dịch.";
                case ERROR_PARTNER_CASE_TO_REFUND:
                    return "Giao dịch đang được xử lý. Ngân hàng sẽ tiến hành tra soát giao dịch của Quý khách tối đa 2 Ngày làm việc tiếp theo.";
                case PARTNER_INVALID:
                    return "Đối tác không hợp lệ.";
                case TIMEOUT:
                    return "Quá thời gian thực hiện.";
                case TRANSCODE_LOOP:
                    return "Bị lặp mã giao dịch.";
                case HEADERPARAM_INVALID:
                    return "Tham số header không hợp lệ.";
                case CHANNEL_NOTEXSIST:
                    return "Kênh không tồn tại.";
                case SERVICECODE_NOTEXSIST:
                    return "Mã dịch vụ không tồn tại.";
                case PROVIDERCODE_NOTEXSIST:
                    return "Mã nhà cung cấp không hợp lệ.";
                case PROCESSINGCODE_NOTEXSIST:
                    return "Mã xử lý không hợp lệ.";
                case REQUESTPARAM_INVALID:
                    return "Tham số yêu cầu không hợp lệ.";
                case BILLCODE_INVALID:
                    return "Mã khách hàng không tồn tại.";
                case BILL_NOTEXIST:
                    return "Không có hóa đơn nào để thanh toán.";
                case BILL_PAID:
                    return "Hóa đơn đã được thanh toán.";
                case CANNOT_PAIDBILL_PARTNER:
                    return "Không thể thanh toán hóa đơn với đối tác.";
                case ACCNO_NOT_FOUND:
                    return "Không tìm thấy tài khoản của khách hàng.";
                case NOT_ENOUGH_AMT_TO_PAY:
                    return "Tài khoản không đủ số dư để thực hiện giao dịch.";
                case NOT_MATCH_CCY_VND:
                    return "Tài khoản của khách hàng không phải loại tiền tệ 'VND' để thanh toán.";
                case ACCNO_NOTMATCH_TYPECASA:
                    return "Loại tài khoản để thanh toán hóa đơn không hợp lệ.";
                case CANNOT_TRANSFERFCUBS:
                    return "Không thể thực hiện chuyển khoản từ tài khoản này.";
                case NO_CUSTOMER_EXISTS:
                    return "Mã khách hàng không tồn tại.";
                case POSTPAY_SUBSCRIBE:
                    return "Thuê bao được nạp tiền là thuê bao trả sau.";
                case NO_VALID_SUBSCRIBE:
                    return "Mạng di động chưa được hỗ trợ hoặc Mã khách hàng chưa đăng ký thương mại điện tử.";
                case NO_VALID_DENOMINATION:
                    return "Mệnh giá tiền không hợp lệ.";
                case INVALID_PROCESS:
                    return "Giao dịch đang thực hiện không hợp lệ.";
                case INVALID_CONFIRMCODE:
                    return "Mã chứng thực không hợp lệ.";
                case NO_ENOUGH_TRANS_LIMIT:
                    return "Giá trị giao dịch đã vượt quá hạn mức giao dịch trong ngày.";
                case STATUS_IS_INACTIVE:
                    return "Không thể thanh toán mã hóa đơn này do khu vực chưa được hỗ trợ hoặc hóa đơn hết hạn/bị khóa/đã thanh toán.";
                case CANNOT_PAYMENT_NOT_REFUND:
                    return "Ngân hàng sẽ tiến hành tra soát giao dịch của Quý khách tối đa 2 Ngày làm việc tiếp theo.";
                case CANNOT_PAYMENT_REFUNDED:
                    return "Hóa đơn thanh toán không thành công – đã hoàn tiền cho khách hàng.";
                case ERROR_BCN:
                    return "Hóa đơn không tồn tại";
                case PAYMENT_FAIL:
                    return "Cập nhật thanh toán thất bại";
                case DO_NOT_OWE:
                    return "Khách hàng không còn nợ tiền";
                case NOT_FOUND_CUSTOMER_INFO:
                    return "Không tìm thấy thông tin khách hàng";
                case BILLCODE_INCORRECT:
                    return "Mã hóa đơn đã được thanh toán hoặc mã hóa đơn đã hết hạn thanh toán";
                case BILLCODE_EXPIRED:
                    return "Mã khách hàng đã hết thời gian hiệu lực thanh toán";
                case SICODE_INVALID:
                    return "Tài khoản chứng khoán không tồn tại! Vui lòng kiểm tra lại.";
                case PAYCREDITCARD_TIMEOUT:
                    return "Thanh toán không thành công, lỗi timeout, Kiểm tra tài khoản casa và CardWork để biết tình trạng của giao dịch trước khi thực hiện lại giao dịch.";
                case OUT_OF_STOCK:
                    return "Số lượng thẻ tạm hết. Vui lòng chọn mệnh giá khác.";
                case NOT_ENOUGH_QUANTITY:
                    return "Số lượng thẻ còn lại: ";
                case TOPUP_IN_PROCESSING:
                    return "Thông tin lệnh nạp tiền hợp lệ. Vui lòng chờ kết quả xử lý";
                case CUSTOMERCODE_INVALID:
                    return "Mã khách hàng không tồn tại hoặc không hợp lệ";
                case CHECK_LIMIT_OVER:
                    return "Mã giao dịch này đã check quá tối đa số lần cho phép";
                case QUANTITY_CARD_INVALID:
                    return "Số lượng thẻ không hợp lệ";
                case EXCHANGE_NOT_EXIST:
                    return "Giao dịch không tồn tại";
                case VNPT_CODE_NOT_EXISTED:
                return "Mã thanh toán/SĐT hiện không tồn tại. Quý khách vui lòng kiểm tra lại.";
                default:
                    return "";
            }
        } else {
            switch (pre) {
                case OK:
                    return "Thanh toán hóa đơn thành công";
                case OK_PAIDBILL_PARTNER:
                    return "Yêu cầu đối tác thanh toán hóa đơn thành công";
                case OK_TRANSFER_FCUBS:
                    return "Yêu cầu core banking thanh toán hóa đơn thành công";
                case SYSTEM_ERROR:
                    return "Hệ thống đang bảo trì. Vui lòng quay lại lần sau.";
                case ERROR_PARTNER:
                    return "Hệ thống nhà cung cấp dịch vụ đang bảo trì. Vui lòng quay lại lần sau.";
                case ERROR_PARTNER_REFUND:
                    return "Giao dịch không thành công. Quý khách vui lòng thực hiện lại giao dịch.";
                case ERROR_PARTNER_CASE_TO_REFUND:
                    return "Giao dịch đang được xử lý. Ngân hàng sẽ tiến hành tra soát giao dịch của Quý khách tối đa 2 Ngày làm việc tiếp theo.";
                case PARTNER_INVALID:
                    return "Đối tác không hợp lệ.";
                case TIMEOUT:
                    return "Quá thời gian thực hiện.";
                case TRANSCODE_LOOP:
                    return "Bị lặp mã giao dịch.";
                case HEADERPARAM_INVALID:
                    return "Tham số header không hợp lệ.";
                case CHANNEL_NOTEXSIST:
                    return "Kênh không tồn tại.";
                case SERVICECODE_NOTEXSIST:
                    return "Mã dịch vụ không tồn tại.";
                case PROVIDERCODE_NOTEXSIST:
                    return "Mã nhà cung cấp không hợp lệ.";
                case PROCESSINGCODE_NOTEXSIST:
                    return "Mã xử lý không hợp lệ.";
                case REQUESTPARAM_INVALID:
                    return "Tham số yêu cầu không hợp lệ.";
                case BILLCODE_INVALID:
                    return "Mã khách hàng không tồn tại.";
                case BILL_NOTEXIST:
                    return "Không có hóa đơn nào để thanh toán.";
                case BILL_PAID:
                    return "Hóa đơn đã được thanh toán.";
                case CANNOT_PAIDBILL_PARTNER:
                    return "Không thể thanh toán hóa đơn với đối tác.";
                case ACCNO_NOT_FOUND:
                    return "Không tìm thấy tài khoản của khách hàng.";
                case NOT_ENOUGH_AMT_TO_PAY:
                    return "Tài khoản không đủ số dư để thực hiện giao dịch.";
                case NOT_MATCH_CCY_VND:
                    return "Tài khoản của khách hàng không phải loại tiền tệ 'VND' để thanh toán.";
                case ACCNO_NOTMATCH_TYPECASA:
                    return "Loại tài khoản để thanh toán hóa đơn không hợp lệ.";
                case CANNOT_TRANSFERFCUBS:
                    return "Không thể thực hiện chuyển khoản từ tài khoản này.";
                case NO_CUSTOMER_EXISTS:
                    return "Mã khách hàng không tồn tại.";
                case POSTPAY_SUBSCRIBE:
                    return "Thuê bao được nạp tiền là thuê bao trả sau.";
                case NO_VALID_SUBSCRIBE:
                    return "Mạng di động chưa được hỗ trợ hoặc Mã khách hàng chưa đăng ký thương mại điện tử.";
                case NO_VALID_DENOMINATION:
                    return "Mệnh giá tiền không hợp lệ.";
                case INVALID_PROCESS:
                    return "Giao dịch đang thực hiện không hợp lệ.";
                case INVALID_CONFIRMCODE:
                    return "Mã chứng thực không hợp lệ.";
                case NO_ENOUGH_TRANS_LIMIT:
                    return "Giá trị giao dịch đã vượt quá hạn mức giao dịch trong ngày.";
                case STATUS_IS_INACTIVE:
                    return "Không thể thanh toán mã hóa đơn này do khu vực chưa được hỗ trợ hoặc hóa đơn hết hạn/bị khóa/đã thanh toán.";
                case CANNOT_PAYMENT_NOT_REFUND:
                    return "Ngân hàng sẽ tiến hành tra soát giao dịch của Quý khách tối đa 2 Ngày làm việc tiếp theo.";
                case CANNOT_PAYMENT_REFUNDED:
                    return "Hóa đơn thanh toán không thành công – đã hoàn tiền cho khách hàng.";
                case ERROR_BCN:
                    return "Hóa đơn không tồn tại";
                case PAYMENT_FAIL:
                    return "Cập nhật thanh toán thất bại";
                case DO_NOT_OWE:
                    return "Khách hàng không còn nợ tiền";
                case NOT_FOUND_CUSTOMER_INFO:
                    return "Không tìm thấy thông tin khách hàng";
                case BILLCODE_INCORRECT:
                    return "Mã hóa đơn đã được thanh toán hoặc mã hóa đơn đã hết hạn thanh toán";
                case BILLCODE_EXPIRED:
                    return "Mã khách hàng đã hết thời gian hiệu lực thanh toán";
                case SICODE_INVALID:
                    return "Tài khoản chứng khoán không tồn tại! Vui lòng kiểm tra lại.";
                case PAYCREDITCARD_TIMEOUT:
                    return "Thanh toán không thành công, lỗi timeout, Kiểm tra tài khoản casa và CardWork để biết tình trạng của giao dịch trước khi thực hiện lại giao dịch.";
                case OUT_OF_STOCK:
                    return "Số lượng thẻ tạm hết. Vui lòng chọn mệnh giá khác.";
                case NOT_ENOUGH_QUANTITY:
                    return "Số lượng thẻ còn lại: ";
                case TOPUP_IN_PROCESSING:
                    return "Thông tin lệnh nạp tiền hợp lệ. Vui lòng chờ kết quả xử lý";
                case CUSTOMERCODE_INVALID:
                    return "Mã khách hàng không tồn tại hoặc không hợp lệ";
                case CHECK_LIMIT_OVER:
                    return "Mã giao dịch này đã check quá tối đa số lần cho phép";
                case QUANTITY_CARD_INVALID:
                    return "Số lượng thẻ không hợp lệ";
                case EXCHANGE_NOT_EXIST:
                    return "Giao dịch không tồn tại";
                case VNPT_CODE_NOT_EXISTED:
                    return "Mã thanh toán/SĐT hiện không tồn tại. Quý khách vui lòng kiểm tra lại.";
                default:
                    return "";
            }
        }
    }
}