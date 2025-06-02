package scb.com.vn.dbi.connection;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

/**
 *
 * @author system
 */
public class SetupConnection {

    private static final Logger LOGGER = Logger.getLogger(SetupConnection.class);

    private String url, username, password, driver, appid;
    private int maxconn, minconn, parcount;

    /**
     *
     * @param url
     * @param driver
     * @param username
     * @param password
     * @param maxconn
     * @param minconn
     * @param parcount
     * @param appid
     */
    public SetupConnection(String url, String driver, String username, String password, int maxconn, int minconn, int parcount, String appid) {
        this.url = url;
        this.driver = driver;
        this.username = username;
        this.password = password;
        this.maxconn = maxconn;
        this.minconn = minconn;
        this.parcount = parcount;
        this.appid = appid;
    }

    /**
     *
     * @return @throws Exception
     */
    public synchronized BoneCP getConnectionBoneCP() throws Exception {
        BoneCP connectionPool = null;
        try {
            Class.forName(this.driver);
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        }
        try {
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(this.url); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
            config.setUsername(this.username);
            config.setPassword(this.password);

            //HieuDT: Nang cap thong so he thong
            config.setMinConnectionsPerPartition(this.minconn);
            config.setMaxConnectionsPerPartition(this.maxconn);
            config.setPartitionCount(this.parcount);
            config.setIdleConnectionTestPeriodInMinutes(3);
            config.setIdleMaxAgeInMinutes(0);
            config.setConnectionTimeout(1, TimeUnit.MINUTES);

            //Theo doi ket noi bi timeout
            if (!this.appid.equalsIgnoreCase("gw") && !this.appid.equalsIgnoreCase("tnb")
                    && !this.appid.equalsIgnoreCase("domino")) {
                //Oracle database
                config.setConnectionTestStatement("select 1 from dual");
            }
            connectionPool = new BoneCP(config);
            return connectionPool;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
