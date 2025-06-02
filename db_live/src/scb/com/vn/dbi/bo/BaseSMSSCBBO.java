package scb.com.vn.dbi.bo;

import java.io.Serializable;
import java.sql.Connection;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.connection.*;

/**
 * @author Nguyen Ngo Duy Phuc
 */
public class BaseSMSSCBBO extends GenericHibernateBO<Object, Serializable> {

    private static final Logger LOGGER = Logger.getLogger(BaseSMSSCBBO.class);

    /**
     * Create a new instance of BaseSMSSCBBO
     */
    public BaseSMSSCBBO() {
    }

    /**
     *
     * @return
     */
    protected Connection getConnection() {
        try {
            Connection conn = ConnectionManager.getConnection("smsscb");
            return conn;
        } catch (Exception ex) {
            LOGGER.error("Loi getConnection:" + ex);
            return null;
        }
    }
}
