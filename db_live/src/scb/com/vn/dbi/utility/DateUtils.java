/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author minhndb
 */
public class DateUtils {
    private static final Logger LOGGER = Logger.getLogger(DateUtils.class);
    
    /**
     *
     * @param format
     * @return
     */
    public static String getFormatDate(String format) {
        try {
            return new SimpleDateFormat(format).format(new Date());
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }
    
    public static String changeFormatDate(String oldDate, String sourceFormatDate, String destDate) {
        try {
            DateFormat formatter = new SimpleDateFormat(sourceFormatDate);
            DateFormat formatter1 = new SimpleDateFormat(destDate);
            Date date = formatter.parse(oldDate);
            return formatter1.format(date);
        } catch (Exception e) {
            return oldDate;
        }
    }
}
