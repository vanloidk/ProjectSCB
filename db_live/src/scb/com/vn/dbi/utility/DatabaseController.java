/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.utility;

import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author minhndb
 */
public class DatabaseController {

    private static final Logger LOGGER = Logger.getLogger(DatabaseController.class);
    
    private static final HashMap<String, Boolean> CONNECTION_MANAGER = new HashMap<String, Boolean>();

    private static boolean CANACCESS_SMSSCB = true;
    private static boolean CANACCESS_FCDB = true;
    private static boolean CANACCESS_DOMINO = true;
    private static boolean CANACCESS_VNINFO = true;

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean putValueToConnectionManager(String key, boolean value) {
        LOGGER.info("putValueToConnectionManager --> key = [" + key + "], value = [" + value + "]");
        boolean result = false;
        try {
            CONNECTION_MANAGER.put(key, value);
            if (value) {
                switch(key) {
                    case "smsscb":
                        CANACCESS_SMSSCB = value;
                        break;
                    case "fcdb": //dbobdx
                        CANACCESS_FCDB = value;
                        break;
                    case "domino":
                        CANACCESS_DOMINO = value;
                        break;
                    case "vninfo":
                        CANACCESS_VNINFO = value;
                        break;
                    default:
                        break;
                }
            }
            result = true;
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }
    
    /**
     *
     * @param key
     * @return
     */
    public static boolean getValueFromConnectionManager(String key) {
        boolean result = false;
        try {
            if (CONNECTION_MANAGER.containsKey(key)) {
                result = CONNECTION_MANAGER.get(key);
            } else {
                LOGGER.warn("The CONNECTION_MANAGER does not contain " + key);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }
    
    public static boolean canGetConnection(String key) {
        boolean result = false;
        try {
            if (CONNECTION_MANAGER.containsKey(key)) {
                result = CONNECTION_MANAGER.get(key);
            } else {
                LOGGER.warn("The CONNECTION_MANAGER does not contain " + key + ". --> Thuc hien khoi tao ket noi");
                result = true;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }
    
    /**
     *
     * @param key
     * @return
     */
    public static int getRealValueFromConnectionManager(String key) {
        int result = -1;
        try {
            boolean temp = CONNECTION_MANAGER.get(key);
            result = temp ? 0 : 1;
        } catch (Exception e) {
            LOGGER.error("CONNECTION_MANAGER DOES NOT CONTAIN KEY = [" + key + "]. Ex: " + e);
        }
        return result;
    }
    

    /**
     *
     * @return
     */
    public static boolean isCANACCESS_SMSSCB() {
        return CANACCESS_SMSSCB;
    }

    /**
     *
     * @return
     */
    public static boolean isCANACCESS_FCDB() {
        return CANACCESS_FCDB;
    }

    /**
     *
     * @return
     */
    public static boolean isCANACCESS_DOMINO() {
        return CANACCESS_DOMINO;
    }

    /**
     *
     * @return
     */
    public static boolean isCANACCESS_VNINFO() {
        return CANACCESS_VNINFO;
    }

    /**
     *
     * @param CANACCESS_SMSSCB
     */
    public static void setCANACCESS_SMSSCB(boolean CANACCESS_SMSSCB) {
        DatabaseController.CANACCESS_SMSSCB = CANACCESS_SMSSCB;
    }

    /**
     *
     * @param CANACCESS_FCDB
     */
    public static void setCANACCESS_FCDB(boolean CANACCESS_FCDB) {
        DatabaseController.CANACCESS_FCDB = CANACCESS_FCDB;
    }

    /**
     *
     * @param CANACCESS_DOMINO
     */
    public static void setCANACCESS_DOMINO(boolean CANACCESS_DOMINO) {
        DatabaseController.CANACCESS_DOMINO = CANACCESS_DOMINO;
    }

    /**
     *
     * @param CANACCESS_VNINFO
     */
    public static void setCANACCESS_VNINFO(boolean CANACCESS_VNINFO) {
        DatabaseController.CANACCESS_VNINFO = CANACCESS_VNINFO;
    }
}
