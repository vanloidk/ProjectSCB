/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.utility;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

/**
 *
 * @author hieudt
 */
public class Helper {

    /**
     *
     * @param clazz
     * @param lvl
     * @param msg
     * @return
     */
    public static int writeLog(Class<?> clazz, Level lvl, String msg) {
        Logger.getLogger(clazz).info(msg);
        return 1;
    }

    /**
     *
     * @param clazz
     * @param msg
     */
    public static void writeLogError(Class<?> clazz, Object msg) {
        Logger.getLogger(clazz).error(msg);
    }

}
