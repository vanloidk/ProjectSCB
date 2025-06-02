package scb.com.vn.dbi.bo;

import scb.com.vn.dbi.connection.*;

import java.sql.Connection;

/**
 * @author Nguyen Ngo Duy Phuc
 */
public class BaseSMSBO {

    /**
     * Create a new instance of BaseSMSBO
     */
    public BaseSMSBO() {
    }

    /**
     *
     * @return @throws Exception
     */
    protected Connection getConnection() throws Exception {
        Connection conn = ConnectionManager.getConnection("sms");
        return conn;
    }
}
