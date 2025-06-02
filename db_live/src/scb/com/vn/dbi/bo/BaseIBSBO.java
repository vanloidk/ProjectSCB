package scb.com.vn.dbi.bo;

import scb.com.vn.dbi.connection.*;

import java.sql.Connection;

/**
 * @author Nguyen Ngo Duy Phuc
 */
public class BaseIBSBO {

    /**
     * Create a new instance of BaseIBSBO
     */
    public BaseIBSBO() {
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
