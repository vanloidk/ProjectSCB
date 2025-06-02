package scb.com.vn.dbi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author system
 */
public class TokenkeyDAO extends BaseDAO {

    private final String SELECT_SQL_GETTKBANKBYSERIALNO = "select username, serialno, bankname from GW_TOKENOTP WHERE serialno=?";

    /**
     * Create a new instance of TokenkeyDAO
     */
    public TokenkeyDAO() {
    }

    /**
     *
     * @param serialno
     * @return
     * @throws Exception
     */
    public ArrayList getTokenkeybankBySerial(String serialno) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(serialno);
        return JDBCEngine.executeQuery(SELECT_SQL_GETTKBANKBYSERIALNO, p_args, connection);
    }
    String SELECT_TRANSID = "select SEQ_MK_TRANSID.Nextval transid from dual";

    /**
     *
     * @return @throws Exception
     */
    public String getTransID() throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        ArrayList list = JDBCEngine.executeQuery(SELECT_TRANSID, p_args, connection);
        if (!list.isEmpty()) {
            HashMap hm = (HashMap) list.get(0);
            return hm.get("transid").toString();
        }
        return null;
    }
    String SELECT_MKVerifyInfo = "select transid, auditid "
            + "  from MK_TOKEN_LOG M\n"
            + "  where trunc(M.Createdate)=trunc(sysdate)\n"
            + "  and M.Serial=?"
            + "  and M.Challenge=?";

    /**
     *
     * @param serialno
     * @param challengeid
     * @return
     * @throws Exception
     */
    public ArrayList getMKVerifyInfo(String serialno, String challengeid) throws Exception {
        ArrayList p_args = new ArrayList();
        p_args.add(serialno);
        p_args.add(challengeid);
        return JDBCEngine.executeQuery(SELECT_MKVerifyInfo, p_args, connection);
    }
    String INSERT_MK_TOKEN_LOG = "Insert into MK_TOKEN_LOG(SERIAL,CHALLENGE,TRANSID,AUDITID) values(?,?,?,?)";

    /**
     *
     * @param SERIAL
     * @param CHALLENGE
     * @param TRANSID
     * @param AUDITID
     * @return
     * @throws Exception
     */
    public int INSERT_MK_TOKEN_LOG(String SERIAL, String CHALLENGE, String TRANSID, String AUDITID) throws Exception {
        ArrayList<String> params = new ArrayList<String>();
        params.add(SERIAL);
        params.add(CHALLENGE);
        params.add(TRANSID);
        params.add(AUDITID);
        return JDBCEngine.executeUpdate(INSERT_MK_TOKEN_LOG, params, connection);
    }
}
