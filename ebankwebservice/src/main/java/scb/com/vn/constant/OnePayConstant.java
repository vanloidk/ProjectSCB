/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.constant;

/**
 *
 * @author Administrator
 */
public class OnePayConstant {

    //public static final String PRODUCTTRANSFER = Configuration.getProperty("fcubs.producttransfer.onlinetransfer");

    /**
     *
     */
    public static final String PRODUCTTRANSFER = "PBLP";

    /* XML element name */

    /**
     *
     */

    public static final String REQUEST = "REQUEST";

    /**
     *
     */
    public static final String RESPONSE = "RESPONSE";

    /**
     *
     */
    public static final String COMMANDCODE = "COMMANDCODE";

    /**
     *
     */
    public static final String RESPONSECODE = "RESPONSECODE";

    /**
     *
     */
    public static final String TRANSID = "TRANSID";

    /**
     *
     */
    public static final String BANKTRANSID = "BANKTRANSID";

    /**
     *
     */
    public static final String CARDNUMBER = "CARDNUMBER";

    /**
     *
     */
    public static final String CARDHOLDERNAME = "CARDHOLDERNAME";

    /**
     *
     */
    public static final String CARDDATE = "CARDDATE";

    /**
     *
     */
    public static final String AMOUNT = "AMOUNT";

    /**
     *
     */
    public static final String CURRENCYCODE = "CURRENCYCODE";

    /**
     *
     */
    public static final String MERCHANTID = "MERCHANTID";

    /**
     *
     */
    public static final String WITHOUTOTP = "WITHOUTOTP";

    /**
     *
     */
    public static final String DATETIME = "DATETIME";

    /**
     *
     */
    public static final String DESCRIPTION = "DESCRIPTION";

    /**
     *
     */
    public static final String PROVIDERID = "PROVIDERID";

    /**
     *
     */
    public static final String VERIFYTYPE = "VERIFYTYPE";

    /**
     *
     */
    public static final String REFTRANSID = "REFTRANSID";

    /**
     *
     */
    public static final String OTP = "OTP";

    /**
     *
     */
    public static final String MAC = "MAC";
    /* XML element name */

 /* XML element value */

    /**
     *
     */

    public static final String CARD_VER_COMMANDCODE = "001";

    /**
     *
     */
    public static final String OTP_VER_COMMANDCODE = "002";

    /**
     *
     */
    public static final String PAYMENT_COMMANDCODE = "003";

    /**
     *
     */
    public static final String PAYMENT_WITHOUT_OTP = "111";

    /**
     *
     */
    public static final String PAYMENT_WITH_OTP = "205";
    /* XML element value */

    /**
     *
     */
    public static final String CARD_VER_TRANSTYPE = "06";

    /**
     *
     */
    public static final String PAYMENT_VER_TRANSTYPE = "07";

    /**
     *
     */
    public static final String ONEPAY_CHANNELID = "02";

    /* Error code */

    /**
     *
     */

    public static String SYSTEM_IS_ERROR_DEFAULT = "94";
    //public static String SYSTEM_IS_ERROR = "99";
    //public static String MAC_IS_ERROR = "98";

    /**
     *
     */
    public static String SECURITY_IS_ERROR = "63";

    /**
     *
     */
    public static String FORMAT_IS_ERROR = "30";

    /**
     *
     */
    public static String MONEY_IS_ERROR = "13";
    /* Error code */

 /* Result code*/

    /**
     *
     */

    public static String TRANSACTION_IS_SUCCEED = "00";

    /**
     *
     */
    public static String NOT_ENOUGH_CONDITION_TO_PAY = "01";

    /**
     *
     */
    public static String WAITING_FOR_OTP = "02";

    /**
     *
     */
    public static String THE_SERVICE_IS_NOT_REGISTERED = "05";

    /**
     *
     */
    public static String OTP_IS_EXPRIDED = "08";

    /**
     *
     */
    public static String AMOUNT_IS_NOT_ACCEPTED = "13";

    /**
     *
     */
    public static String THE_CARD_INFO_IS_WRONG = "14";

    /**
     *
     */
    public static String OTP_IS_WRONG = "20";

    /**
     *
     */
    public static String THE_CARD_IS_LOCKED = "41";

    /**
     *
     */
    public static String THE_CARD_IS_NOT_ENOUGH_MONEY = "51";

    /**
     *
     */
    public static String THE_CARD_IS_EXPRIDED = "54";

    /**
     *
     */
    public static String PASS_THE_LIMITED_IN_THE_DAY = "61";

    /**
     *
     */
    public static String THE_LIMITED_IN_TRANSACTION = "64";
    /* Result code*/
}
