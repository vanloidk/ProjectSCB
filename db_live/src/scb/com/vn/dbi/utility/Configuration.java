package scb.com.vn.dbi.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author minhndb
 */
public class Configuration {

    private static Properties configProp = null;

    /**
     *
     */
    public static ArrayList listServiceType = null;

    /**
     *
     */
    public static ArrayList listServiceTypeAutoPay = null;

    /**
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return configProp.getProperty(key);
    }

    /**
     *
     * @return
     */
    public static ArrayList getAllServiceType() {
        return listServiceType;
    }

    /**
     *
     * @param pathFile
     * @throws Exception
     */
    public static void initLoadProperty(String pathFile) throws Exception {
        if (configProp != null) {
            return;
        }

        File file = new File(pathFile);
        if (!file.exists()) {
            throw new Exception("Khong tim thay path file.");
        }
        FileInputStream in = new FileInputStream(file);
        Properties property = new Properties();
        property.load(in);
        in.close();

        if (property.isEmpty()) {
            throw new Exception("Khong load duoc properties theo duong dan: " + file.getAbsolutePath());
        }
        configProp = property;
    }
}
