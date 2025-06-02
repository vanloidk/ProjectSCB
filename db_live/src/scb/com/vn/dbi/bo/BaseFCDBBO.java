package scb.com.vn.dbi.bo;

import java.io.Serializable;
import scb.com.vn.dbi.connection.*;

import java.sql.Connection;
import org.jboss.logging.Logger;

/**
 * @author Nguyen Ngo Duy Phuc
 */
public class BaseFCDBBO extends GenericHibernateBO<Object, Serializable> {

    private static final Logger LOGGER = Logger.getLogger(BaseFCDBBO.class);

    /**
     * Create a new instance of BaseFCDBBO
     */
    public BaseFCDBBO() {
    }

    /**
     *
     * @return
     */
    protected Connection getConnection() {
        Connection conn;
        try {
            conn = ConnectionManager.getConnection("fcdb");
            return conn;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }
}
