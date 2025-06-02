package scb.com.vn.dbi.connection;

import java.util.HashMap;
import scb.com.vn.dbi.utility.Configuration;
import com.jolbox.bonecp.BoneCP;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.utility.DatabaseController;

/**
 *
 * @author system
 */
public class ConnectionManager {

    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);
    //http://kahimyang.info/kauswagan/howto_blogs/1017-using_tomcat_jdbc_connection_pool_as_standalone_instantiated_bean

    private static final HashMap connectionTable = new HashMap();

    /**
     * Create a new instance of ConnectionManager
     */
    ConnectionManager() {
    }

    /**
     *
     * @param appid
     * @return
     * @throws Exception
     */
    public static java.sql.Connection getConnection(String appid) throws Exception {
        if (DatabaseController.canGetConnection(appid)) {
            BoneCP _connectionPool = (BoneCP) connectionTable.get(appid);
            if (_connectionPool == null) {
                String username = Configuration.getProperty("conn.jdbc.username." + appid);
                String password = Configuration.getProperty("conn.jdbc.password." + appid);
                String driver = Configuration.getProperty("conn.jdbc.driver." + appid);
                String url = Configuration.getProperty("conn.jdbc.url." + appid);
                int maxconn = Integer.parseInt(Configuration.getProperty("conn.jdbc.maxconn." + appid));

                //Bo sung them thong so            
                int minconn = Integer.parseInt(Configuration.getProperty("conn.jdbc.minconn." + appid));
                int parcount = Integer.parseInt(Configuration.getProperty("conn.jdbc.parcount." + appid));

                SetupConnection sc = new SetupConnection(url, driver, username, password, maxconn, minconn, parcount, appid);
                _connectionPool = sc.getConnectionBoneCP();
                connectionTable.put(appid, _connectionPool);
                if (DatabaseController.getRealValueFromConnectionManager(appid) == -1) {
                    DatabaseController.putValueToConnectionManager(appid, true);
                } else {
                    LOGGER.info("Does not put " + appid + " to DatabaseController because it is available with value = [" + DatabaseController.getValueFromConnectionManager(appid) + "]");
                }
                LOGGER.info("===============Hoàn tất khởi tạo kết nối: " + appid);
                LOGGER.info("===============Size: " + String.valueOf(connectionTable.size()));
            }
            try {
                return (java.sql.Connection) _connectionPool.getConnection();
            } catch (Exception ex) {
                LOGGER.error(ex);
                throw new Exception(ex);
            }
        } else {
            throw new Exception("Database " + appid + " is off");
        }
    }

    /**
     *
     * @param appid
     * @throws Exception
     */
    public static void initConnection(String appid) throws Exception {
        LOGGER.info("Start connection " + appid);
        BoneCP _connectionPool = (BoneCP) connectionTable.get(appid);
        if (_connectionPool == null) {
            String username = Configuration.getProperty("conn.jdbc.username." + appid);
            String password = Configuration.getProperty("conn.jdbc.password." + appid);
            String driver = Configuration.getProperty("conn.jdbc.driver." + appid);
            String url = Configuration.getProperty("conn.jdbc.url." + appid);
            int maxconn = Integer.parseInt(Configuration.getProperty("conn.jdbc.maxconn." + appid));

            //Bo sung them thong so            
            int minconn = Integer.parseInt(Configuration.getProperty("conn.jdbc.minconn." + appid));
            int parcount = Integer.parseInt(Configuration.getProperty("conn.jdbc.parcount." + appid));

            SetupConnection sc = new SetupConnection(url, driver, username, password, maxconn, minconn, parcount, appid);
            _connectionPool = sc.getConnectionBoneCP();
            connectionTable.put(appid, _connectionPool);
            LOGGER.info("===============Hoàn tất khởi tạo kết nối: " + appid);
            LOGGER.info("===============Size: " + String.valueOf(connectionTable.size()));
        }
    }
}
