package scb.com.vn.dbi.bo;

import java.sql.Connection;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.dao.TnbDAO;

/**
 *
 * @author minhndb
 */
public class TnbBO extends BaseTNBBO {

    private static final Logger LOGGER = Logger.getLogger(TnbBO.class);

    private TnbDAO tbndao = null;
    private Connection conn = null;

    /**
     * Create a new instance of TnbBO
     */
    public TnbBO() {
        tbndao = new TnbDAO();
    }

    /**
     *
     * @param mobile
     * @return
     * @throws Exception
     */
    public int getCountMobile(String mobile) throws Exception {
        try {
            conn = super.getConnection();
            tbndao.setConnection(conn);
            return tbndao.getCountMobile(mobile);
        } catch (Exception e) {
            LOGGER.error(e);
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
