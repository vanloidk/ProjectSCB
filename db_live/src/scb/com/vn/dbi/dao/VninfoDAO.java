package scb.com.vn.dbi.dao;

import java.util.ArrayList;

import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author system
 */
public class VninfoDAO extends BaseDAO {

    // get info customer from VNinfo
    final String GET_CUST_INFO = " SELECT * from MB_MOBILES t where MOBILE_STATUS = '3' and t.mobile_no=?";
    //duytxa add 30.08.2017
    final String GET_MOBILE_USER = " select b.mobile_no from  vninfoscb.mb_Customer a "
            + " left join vninfoscb.mb_mobiles b on a.cus_id=b.cus_id \n"
            + " where cif_core=?  \n"
            + " and b.mobile_status=? ";
    // get  customer from VNinfo
    final String GET_USER_INFO = " SELECT  A.MOBILE_NO,B.CIF_CORE  from MB_MOBILES A, MB_Customer B where MOBILE_STATUS <> '9' and MOBILE_STATUS <> '0' and A.CUS_ID = B.CUS_ID and ( A.MOBILE_NO=? OR B.CIF_CORE=? )";

    final String GET_PASS ="SELECT MB_PASS  from MB_MOBILES A, MB_Customer B, mb_otp_profile C\n" +
                            " WHERE  A.CUS_ID = B.CUS_ID and a.mobile_id = c.mobile_id and  MOBILE_STATUS <> '9' and MOBILE_STATUS <> '0' and  A.MOBILE_NO=? AND B.CIF_CORE=?";
    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public ArrayList<?> getCustomerFromMobile(String username) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(username);
        return JDBCEngine.executeQuery(GET_CUST_INFO, p_args, connection);
    }
    //duytxa add 30.08.2017

    /**
     *
     * @param cif
     * @param status
     * @return
     * @throws Exception
     */
    public ArrayList<?> getMobileUserFromCif(String cif, String status) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(cif);
        p_args.add(status);
        return JDBCEngine.executeQuery(GET_MOBILE_USER, p_args, connection);
    }
    
    
    /**
     *
     * @param username
     * @param cif
     * @param channel
     * @return
     * @throws Exception
     */
    public ArrayList<?> isExistUserMBanking(String userName, String cif, String channel) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(userName);
        p_args.add(cif);
        return JDBCEngine.executeQuery(GET_USER_INFO, p_args, connection);
    }
    
    /**
     * 
     * @param userName
     * @param cif
     * @return
     * @throws Exception 
     */
     public ArrayList<?> getPass(String userName, String cif) throws Exception {
        ArrayList<String> p_args = new ArrayList<String>();
        p_args.add(userName);
        p_args.add(cif);
        return JDBCEngine.executeQuery(GET_PASS, p_args, connection);
    }
}
