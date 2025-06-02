package scb.com.vn.dbi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author system
 */
public class TnbDAO extends BaseDAO {

    final String GET_COUNT_MOBILE = "select count(CUSTCODE) as TOTAL from VIEW_FCB where SMSAUTHORISE=? or TELNO=?";

    /**
     *
     * @param mobile
     * @return
     * @throws Exception
     */
    public int getCountMobile(String mobile) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(mobile);
        p_args.add(mobile);
        ArrayList<?> list = JDBCEngine.executeQuery(GET_COUNT_MOBILE, p_args, connection);
        HashMap hm = (HashMap) list.get(0);
        return Integer.parseInt(hm.get("total").toString());
    }
}
