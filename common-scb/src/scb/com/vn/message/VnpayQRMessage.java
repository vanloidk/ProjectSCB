/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.message;

/**
 *
 * @author minhndb
 */
public class VnpayQRMessage {
    
    public enum CheckQREnum {
        SUCCESS("00"),
        THISQRCODEISPAID("01"),
        IPADDRESSISDENIED("02"),
        BANKCODEISNOTEXIT("03"),
        QRCODEISNOTINFORMAT("04"),
        LISTQRCODEISREQUIRED("05"),
        MERCHANTISNOTEXIT("07"),
        MERCHANTISNOTACTIVE("11"),
        FALSECHECKSUM("12"),
        TERMINALISNOTEXIT("21"),
        TERMINALISNOTACTIVE("24"),
        MASTERMERCHANTISNOTEXIST("25"),
        MASTERMERCHANTISNOTACTIVE("26"),
        QRCODEISNOTACTIVE("29"),
        DATAOFQRCODEISCHANGED("30"),
        THESEQRCODEDONTHAVETHESAMEMERCHANT("31"),
        MAKHACHHANGKOHOPLE("50"),
        THESEQRCODEDONTHAVETHESAMETERMINAL("32"),
        THESEISNOPROMOTION("60"),
        VOUCHERCODEDOESNTEXIST("61"),
        THEBANKDOESNTSUPPORTFORTHISPROMOTION("62"),
        MECHANT_TERMINALDOESNTSUPPORTTHEVOUCHER("63"),
        VOUCHERCODEWASUSEDUP("64"),
        DEBITAMOUNTISNTENOUGHTOGETTHECONDITION("65"),
        VOUCHEREXCEEDSNUMBEROFTIMESALLOWED("66"),
        VOUCHERISPENDING("67"),
        VOUCHERISLOCKED("68"),
        VOUCHERISOUTOFDATE("69"),
        USERNAMEISINVALID("70"),
        BANKNOTSUPPORT("76"),
        INTERNALERROR("99")
        ;
        
        private String code;

        private CheckQREnum(String c)
        {
            code = c;
        }
        
        public static CheckQREnum get(String value)
        {
            CheckQREnum[] values = CheckQREnum.values();
            for (CheckQREnum val : values)
            {
                if (val.getValue().equals(value))
                {
                    return val;
                }
            }
            return CheckQREnum.INTERNALERROR;
        }
        
        public String getValue()
        {
            return code;
        }
    }
    
    public enum PaymentQREnum {
        SUCCESS("00"),
        DATAISNOTINFORMAT("01"),
        IPADDRESSISDENIED("02"),
        BANKCODEISNOTEXIT("03"),
        MOBILEISINVALID("04"),
        POSTDATATOMERCHANTFAILED("05"),
        PAYDATEISNOTINFORMAT("06"),
        MERCHANTISNOTEXIT("07"),
        MERCHANTDONTRESPONSE("08"),
        MERCHANTISNOTACTIVE("11"),
        FALSECHECKSUM("12"),
        TRANSACTIONALREADYCONFIRMER("13"),
        INVALIDTRANSACTION("14"),
        TRANSACTIONNOTFOUND("15"),
        INVALIDAMOUNT("16"),
        TRANSACTIONEXPIRED("17"),
        INVALIDRESPONSECODE("18"),
        ACCOUNTISLOCKED("19"),
        MOBILEISLOCKED("20"),
        TERMINALISINVALID("21"),
        TRANSACTIONDUPLICATE("23"),
        TERMINALISINACTIVE("24"),
        MESSAGETYPEISINVALID("25"),
        NOTENOUGHPRODUCTINORDER("26"),
        OUTOFSTOCKINORDER("27"),
        THISORDERISPROCESSING("28"),
        QRCODEISNOTACTIVE("29"),
        MERCHANT_TERMINALISNOTAPPROVED("71"),
        THISBANKISNOTSUPPORT("76"),
        SOTIENGACHNOKHONGHOPLE("89"),
        SYSTEMISMAINTAINING("96"),
        INTERNALERROR("99")
        ;
        
        private String code;

        private PaymentQREnum(String c)
        {
            code = c;
        }
        
        public static PaymentQREnum get(String value)
        {
            PaymentQREnum[] values = PaymentQREnum.values();
            for (PaymentQREnum val : values)
            {
                if (val.getValue().equals(value))
                {
                    return val;
                }
            }
            return PaymentQREnum.INTERNALERROR;
        }
        
        public String getValue()
        {
            return code;
        }
    }
    
    public enum RefundQREnum {
        SUCCESS("00"),
        DATAINPUTISNOTINFORMAT("01"),
        IPADDRESSISDENIED("02"),
        BANKCODEISNOTEXIT("03"),
        BANKTRACEISREQUIRED("04"),
        PAYCODEISREQUIRED("05"),
        TIMEOUT("08"),
        FALSECHECKSUM("12"),
        TRANSACTIONNOTFOUND("13"),
        BANKNOTSUPPORT("76"),
        INTERNALERROR("99")
        ;
        
        private String code;

        private RefundQREnum(String c)
        {
            code = c;
        }
        
        public static RefundQREnum get(String value)
        {
            RefundQREnum[] values = RefundQREnum.values();
            for (RefundQREnum val : values)
            {
                if (val.getValue().equals(value))
                {
                    return val;
                }
            }
            return RefundQREnum.INTERNALERROR;
        }
        
        public String getValue()
        {
            return code;
        }
    }
    
    public static String getErrorCodeDescription(CheckQREnum index)
    {
        switch (index)
        {
            case INTERNALERROR:
                return CheckQREnum.INTERNALERROR.toString();
            default:
                return index.toString();
        }
    }
    
    public static String getErrorCodeDescription(PaymentQREnum index)
    {
        switch (index)
        {
            case INTERNALERROR:
                return PaymentQREnum.INTERNALERROR.toString();
            default:
                return index.toString();
        }
    }
    
    public static String getErrorCodeDescription(RefundQREnum index)
    {
        switch (index)
        {
            case INTERNALERROR:
                return RefundQREnum.INTERNALERROR.toString();
            default:
                return index.toString();
        }
    }
}
