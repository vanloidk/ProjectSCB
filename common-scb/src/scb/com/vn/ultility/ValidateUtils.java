/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.ultility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 *
 * @author minhndb
 */
public class ValidateUtils {

    public static boolean validateStringValue(String data) {
        return (data != null && !data.isEmpty());
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
    
    public static boolean isDouble(String value) {
        try {
            new Double(value);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    
    public static boolean isDateTime(String value) {
        TimeZone tz = TimeZone.getTimeZone("Asia/Bangkok");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        df.setTimeZone(tz);
        try {
            df.parse(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
