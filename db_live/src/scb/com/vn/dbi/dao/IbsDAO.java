package scb.com.vn.dbi.dao;

import java.util.ArrayList;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author minhndb
 */
public class IbsDAO extends BaseDAO {

    final String SEL_USER_BY_CUSTID = "SELECT userid, custid, cftype, password FROM IBS_USER WHERE custid=?";
    final String UPD_USERCHANGEPW = "UPDATE IBS_USERCHANGEPASS SET ISCHANGEPASS=? WHERE USERID=?";

    /**
     *
     * @param custid
     * @return
     * @throws Exception
     */
    public ArrayList<?> getIbsUserByCustId(String custid) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(custid);
        return JDBCEngine.executeQuery(SEL_USER_BY_CUSTID, p_args, connection);
    }

    /**
     *
     * @param isChangePwd
     * @param userid
     * @return
     * @throws Exception
     */
    public int updateIbsUserChangePwd(String isChangePwd, String userid) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(isChangePwd);
        p_args.add(userid);
        return JDBCEngine.executeUpdate(UPD_USERCHANGEPW, p_args, connection);
    }
}
