/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author kimncm
 */
public class DateUtils {
    //change format fate to yyyyMMdd

    /**
     *
     * @param oldDate
     * @param sourceFormatDate
     * @param destDate
     * @return
     */

    public static String changeFormatDate(String oldDate, String sourceFormatDate, String destDate) {
        try {
            DateFormat formatter = new SimpleDateFormat(sourceFormatDate);
            DateFormat formatter1 = new SimpleDateFormat(destDate);
            Date date = formatter.parse(oldDate);
            return formatter1.format(date);
        } catch (Exception e) {
            //Helper.writeLogError(this.getClass(), e);
            return oldDate;
        }
    }
}
