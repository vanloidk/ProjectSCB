/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.constant;

/**
 *
 * @author minhndb
 */
public class CommonConstant {

    
    public static final String SERVICE_PARTNER_FAILED = "-999";
            
    /**
     *
     */
    public static final String MASTERQR_STATUS_INIT = "06";

    /**
     *
     */
    public static final String MASTERQR_STATUS_DIRECTDEBIT_FAILED = "05";

    /**
     *
     */
    public static final String MASTERQR_STATUS_DIRECTDEBIT_SUCCESS = "04";

    /**
     *
     */
    public static final String MASTERQR_STATUS_REFUND_FAILED = "03";

    /**
     *
     */
    public static final String MASTERQR_STATUS_REFUND_SUCCESS = "02";

    /**
     *
     */
    public static final String MASTERQR_STATUS_MASTER_FAILED = "01";

    /**
     *
     */
    public static final String MASTERQR_STATUS_MASTER_SUCCESS = "00";

    public static final String RESPONSE_SUCCEED = "00";
    public static final String RESPONSE_FAILED = "01";
    public static final String THANH_TOAN_TRAGOP_NOTEXISTED = "03";
    public static final String THANH_TOAN_TRAGOP_TIMEOUT = "04";
    public static final String THANH_TOAN_TRAGOP_ERROR = "05";

    public static final String HACHTOAN_CORE_TIMEOUT = "03";
    public static final String THANH_TOAN_DUNO_TIMEOUT = "04";
    public static final String THANH_TOAN_DUNO_ERROR = "05";

    public static final String HOANTIEN_KH_ERROR = "04";

    public static final String HOANTIEN_KH_KENH_MB_SUCCEED = "05";
    public static final String HOANTIEN_KH_KENH_SUCCEED = "01";
    public static final String PAYMENT_METHOD_CARD = "01";
    public static final String PAYMENT_METHOD_ACCOUNT = "02";


    public static final String BHBL_PAYMENT_EMPTY = "02";
    public static final String BHBL_PAYMENT_TIMEOUT = "03";
    public static final String BHBL_CALL_API_BL_ERROR = "04";
    public static final String BHBL_TIME_OUT = "05";
    public static final String BHBL_VERIFY_DATA_FAILED = "06";
    public static final String BHBL_HOAN_TIEN_ERROR = "09";
    public static final String BHBL_VALIDATION_FAILED = "10";
    public static final String BHBL_TO_API_CALLED = "22";

    public static final String BHBL_VERIFY_PAYMENT_FAILED = "100";

    public static final String EXCEPTION_GATEWAY = "-99";
    public static final String EXCEPTION_DBI = "10";
    public static final String EXCEPTION_CALL_API_PARTNER = "-111";
    

    public static final String USER_CHANGE_PASS_YES = "1";
    public static final String USER_CHANGE_PASS_NO = "2";
    public static final String USER_INVALID_PASS = "3";
    public static final String USER_LOCK = "4";
    public static final String USER_INVALID_NOT_FOUND = "5";
    public static final String USER_UNKNOWN = "6";
    
    public static final String USERLOCK_PASSYES_CHANGEPASSNO = "8";
    public static final String USERLOCK_PASSYES_CHANGEPASSYES = "7";
    public static final String USERLOCK_PASSNO = "9";
    
    public static final String PAYMENT_CORE_EMPTY = "002";
    public static final String PAYMENT_CORE_TIMEOUT = "003";
    public static final String SERVICE_VNPT_TIMEOUT = "98";
    public static final String PARTNER_HOAN_TIEN_ERROR = "009";
    
    public static final String KHOA_MOKHOA_TKTT_EXITS_DBI = "-02";
    public static final String KHOA_MOKHOA_TKTT_EXITS_GW = "02";
   
    public static final String VNPT_TIENTT_KHAC_TIENHT = "-77";
    public static final String VNPT_IS_CHECK_BILL_TC = "1";
    public static final String VNPT_IS_CHECK_BILL_KTC = "0";
    
    public static final String VNPT_CODE_NOT_EXISTED = "-170";
    
}
