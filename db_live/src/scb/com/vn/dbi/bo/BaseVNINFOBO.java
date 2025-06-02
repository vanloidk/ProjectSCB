package scb.com.vn.dbi.bo;

import java.io.Serializable;
import scb.com.vn.dbi.connection.*;

import java.sql.Connection;
import org.apache.log4j.Logger;

/**
 * @author Nguyen Ngo Duy Phuc
 */
public class BaseVNINFOBO extends GenericHibernateBO<Object, Serializable> {

    private static final Logger LOGGER = Logger.getLogger(BaseVNINFOBO.class);

    /**
     * Create a new instance of BaseVNINFOBO
     */
    public BaseVNINFOBO() {
    }

    /**
     *
     * @return
     */
    protected Connection getConnection() {
        Connection conn;
        try {
            conn = ConnectionManager.getConnection("vninfo");
            return conn;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }
}
