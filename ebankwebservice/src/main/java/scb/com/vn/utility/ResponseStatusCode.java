/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

/**
 *
 * @author loinv3
 */
public class ResponseStatusCode {

    public static final String VALIDATION_SUCCEED = "00";
    public static final String VALIDATION_FAILED = "01";

    public static final String WRONG_MAC = "98";
    public static final String EXCEPTION = "99";

    public class RestransactionHistoryByDesc {

        public static final String ACCOUNTNO_NOT_EXIST_IN_PARTNER = "02";
        public static final String NOT_EXITS_ACCOUNT = "03";
        public static final String NOT_EXIST_PARTNER = "04";
    }

}
