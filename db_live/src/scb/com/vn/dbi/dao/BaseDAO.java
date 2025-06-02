package scb.com.vn.dbi.dao;

import java.sql.Connection;
import org.hibernate.Session;

/**
 *
 * @author system
 */
public abstract class BaseDAO {

    /**
     *
     */
    public Connection connection = null;
    private Session session;

    /**
     * Create a new instance of BaseDAO
     */
    public BaseDAO() {
    }

    /**
     *
     * @param connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @return
     */
    public Session getSession() {
        return session;
    }

    /**
     *
     * @param session
     */
    public void setSession(Session session) {
        this.session = session;
    }
}
