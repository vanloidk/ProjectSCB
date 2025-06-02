package scb.com.vn.dbi.bo;

import scb.com.vn.dbi.connection.*;

import java.sql.Connection;

/**
 * @author Nguyen Ngo Duy Phuc
 */
public class BaseTNBBO {

    /**
     * Create a new instance of BaseTNBBO
     */
    public BaseTNBBO() {
    }

    /**
     *
     * @return @throws Exception
     */
    protected Connection getConnection() throws Exception {
        Connection conn = ConnectionManager.getConnection("ibs");
        return conn;
    }
}
