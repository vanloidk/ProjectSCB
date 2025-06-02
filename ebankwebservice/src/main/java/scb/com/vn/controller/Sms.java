package scb.com.vn.controller;

import java.util.Locale;

import scb.com.vn.utility.Helper;

/**
 *
 * @author minhndb
 */
public class Sms {

//    static Logger logger = Logger.getLogger(Sms.class);
    static char arr_char[][] = {
        {'a', 'á', 'à', 'ả', 'ã', 'ạ', 'ă', 'ắ', 'ằ', 'ẳ', 'ẵ', 'ặ', 'â', 'ấ', 'ầ', 'ẩ', 'ẫ', 'ậ'},
        {'i', 'í', 'ì', 'ỉ', 'ĩ', 'ị'},
        {'o', 'ó', 'ò', 'ỏ', 'õ', 'ọ', 'ơ', 'ớ', 'ờ', 'ở', 'ỡ', 'ợ', 'ô', 'ố', 'ồ', 'ổ', 'ỗ', 'ộ'},
        {'e', 'é', 'è', 'ẻ', 'ẽ', 'ẹ', 'ê', 'ế', 'ề', 'ể', 'ễ', 'ệ'},
        {'u', 'ú', 'ù', 'ủ', 'ũ', 'ụ', 'ư', 'ứ', 'ừ', 'ử', 'ữ', 'ự'}, {'y', 'ý', 'ỳ', 'ỷ', 'ỹ', 'ỵ'},
        {'d', 'đ'}};

    /**
     *
     */
    public Sms() {
    }

    /**
     *
     * @param mobile
     * @param message
     * @param servicecode
     * @param requestid
     * @return
     */
    public int sendsms(String mobile, String message, String servicecode, String requestid) {
        String _mobile = "", _message = "";
        try {

            // Mobile
            if ((mobile == null) || (mobile.length() == 0)) {
                return -2;
            }
            _mobile = mobile.trim();
            if (_mobile.length() < 3) {
                return -2;
            }

            // Message
            if ((message == null) || (message.length() == 0)) {
                return -2;
            }
            _message = message.trim();
            if ((_message.length() < 5) || (_message.length() > 800)) {
                return -2;
            }

            // if (_mobile.charAt(0) == '0')
            // _mobile = "84" + _mobile.substring(1);
            // else
            // return -2;
            // Cat chuoi thanh nhieu tin. Moi tin co do dai toi da: 160
            int iMaxLenMsg = 160;
            int timeSend = (int) Math.ceil((float) ((float) message.length() / (float) iMaxLenMsg));
            int iFirst = 0, iLast = 0, iCurr = 0;

            for (int i = 0; i < timeSend; i++) {

                iFirst = iLast;

                if ((iFirst + iMaxLenMsg) > message.length()) {
                    iLast = message.length();
                } else {
                    iLast = message.substring(0, ((iFirst + iMaxLenMsg) - 1)).trim().lastIndexOf(" ");
                }

                String newmsg = convertToUnSign(message.substring(iFirst, iLast).trim());
                // System.out.println("GW WS SMS: " + newmsg);
                //dbi.sendSMS(_mobile, newmsg, servicecode, requestid);
                int iRe = Helper.getDBI().insertSmsSenderLog(servicecode, _mobile, newmsg);
                if (iRe > 0) {
                    return 0;
                } else {
                    return -1;
                }
            }

            // dbi = (IDBI) Naming.lookup(Configuration.getUrl_dbi());
            // return dbi.sendSMS(_mobile, convertToUnSign(_message),
            // servicecode,
            // requestid);
            return 0;
        } catch (Exception se) {
            Helper.writeLogError(Sms.class, se.getMessage());
            return -99;
        }
    }

//    public int sendsmstnb(String mobile, String message, String servicecode, String requestid) {
//
//        IDBI dbi = null;
//        String _mobile = "", _message = "";
//        try {
//
//            // Mobile
//            if ((mobile == null) || (mobile.length() == 0)) {
//                return -2;
//            }
//            _mobile = mobile.trim();
//            if (_mobile.length() < 3) {
//                return -2;
//            }
//
//            // Message
//            if ((message == null) || (message.length() == 0)) {
//                return -2;
//            }
//            _message = message.trim();
//            if ((_message.length() < 10) || (_message.length() > 800)) {
//                return -2;
//            }
//
//            // if (_mobile.charAt(0) == '0')
//            // _mobile = "84" + _mobile.substring(1);
//            // else
//            // return -2;
//
//            dbi = (IDBI) Naming.lookup(Configuration.getUrl_dbi());
//            // return dbi.sendSMSTNB(_mobile, convertToUnSign(_message),
//            // servicecode, requestid);
//            int i = dbi.insertSmsSenderLog("WS_TNB", _mobile, convertToUnSign(_message));
//            if (i > 0) {
//                return 0;
//            } else {
//                return -1;
//            }
//        } catch (Exception se) {
//            logger.error(se);
//            se.printStackTrace();
//            return -1;
//        }
//    }

    /**
     *
     * @param text
     * @return
     */
    public String convertToUnSign(String text) {
        String result = "";
        char cursor;
        if (text.matches("[a-zA-Z_0-9~!@#$%&]")) {
            return text;
        }

        for (int i = 0; i < text.length(); i++) {
            cursor = text.charAt(i);
            // String.valueOf(cursor).matches("[a-zA-Z_0-9~!@#$%&]");
            if (!String.valueOf(cursor).matches("[a-zA-Z_0-9~!@#$%&]")) {
                result += quet_tim_chu_trong_mangdau(text.charAt(i)) + "";
            } else {
                result += text.charAt(i) + "";
            }
        }
        return result;
    }

    private char quet_tim_chu_trong_mangdau(char c) {
        String s = "";
        boolean flag = false;
        Locale loc = Locale.FRANCE;

        for (int k = 0; k < arr_char.length; k++) {
            for (int i = 1; i < arr_char[k].length; i++) {
                // Kiem tra ky tu la uppercase, neu co, bat co`
                s = String.valueOf(c);
                if (Character.isUpperCase(c)) {
                    s = s.toLowerCase(loc);
                    flag = true;
                }

                if (s.charAt(0) == arr_char[k][i]) {
                    if (flag == false) {
                        return arr_char[k][0];
                    } else {
                        return ((arr_char[k][0] + "").toUpperCase()).charAt(0);
                    }

                }
                flag = false;
            }
        }
        return c;
    }
    // public static void main(String[] args)
    // {
    // Sms s= new Sms();
    // System.out.println(s.convertToUnSign("."));
    // }
}
