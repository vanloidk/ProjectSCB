/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cims.register.FeedbackInfo;
import scb.com.vn.common.odbx.IsExistUserEBRes;
import scb.com.vn.constant.CIMSConstant;
import scb.com.vn.dbi.connection.ConnectionManager;
import scb.com.vn.dbi.dto.EBANKUSER;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author lydty
 */
public class BdobdxDAO extends BaseDAO {

    private static final Logger LOGGER = Logger.getLogger(BdobdxDAO.class);

    //final String GET_USER_DETAIL = "select p.*,u.lock_status,u.user_id_fcdb iduser ,Isfirsttimelogin ,U.u_password,u.user_id_fcdb,u.migratedfrom,d.party_id cifno  from OBDX_ADMIN_PROD.digx_um_userprofile p, OBDX_ADMIN_PROD.users U, OBDX_ADMIN_PROD.digx_um_userparty_relation d where d.user_id=p.u_name and u.u_name=? and p.u_name=u.u_name and u.delete_status ='N'";
    //bo ra khi len live
    final String GET_USER_DETAIL = "select p.*, e.force_change_password, u.lock_status,u.user_id_fcdb iduser ,Isfirsttimelogin ,U.u_password,u.user_id_fcdb,u.migratedfrom,d.party_id cifno  from OBDX_ADMIN_UAT.digx_um_userprofile_live p, OBDX_ADMIN_UAT.users_live U, OBDX_ADMIN_UAT.digx_um_userparty_relation_live d, OBDX_ADMIN_UAT.digx_um_userappdata_live e where d.user_id=p.u_name and u.u_name=? and p.u_name=u.u_name and u.u_name=e.id and u.delete_status ='N'";

    final String GET_USER_OBDX_DTL = "SELECT a.u_name ib_username, a.lock_status, a.U_DESCRIPTION, u_password, b.party_id cifno, "
            + "       case FORCE_CHANGE_PASSWORD"
            + "         when 'Y' then "
            + "          'CHUAKICHHOAT'"
            + "         else"
            + "          'DAKICHHOAT'"
            + "       end as status"
            + "  FROM users_live a join digx_um_userparty_relation_live b  on a.u_name = b.user_id "
            + " where  b.relationship_type = 'I' and a.u_name = ?";

    final String PROC_IB_ACCOUNT = "BEGIN PROC_IB_ACCOUNT(?,?); END;";
    final String resetPass_customer = "update OBDX_ADMIN_PROD.users set u_password=?,isfirsttimelogin='N' where u_name=?  and u_name in (select g_member from OBDX_ADMIN_PROD.groupmembers t\n"
            + " where t.g_name   not in ('AdminChecker','Administrator','AdminMaker','AuthAdmin')\n"
            + " and t.g_member=u_name)";
    final String resetPass_admin = "update OBDX_ADMIN_PROD.users set u_password=?,isfirsttimelogin='N' where u_name=?  and u_name in (select g_member from OBDX_ADMIN_PROD.groupmembers t\n"
            + " where t.g_name  in ('AdminChecker','Administrator','AdminMaker','AuthAdmin')\n"
            + " and t.g_member=u_name)";

    final String GET_USER_DEVICE_OBDX_INFO = "select a.* from obdx_admin_uat.scb_user_additional_data a "
                                            + "left join obdx_admin_uat.users b on a.u_name=b.u_name "
                                            + "where b.delete_status='N' "
                                            + "and a.u_name in (select user_id from obdx_admin_uat.digx_um_userparty_relation "
                                            + "where relationship_type='I' and party_id= ?) ";

    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public ArrayList getDetailUserEB(String username) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("dbobdx");
            ArrayList<String> p_args = new ArrayList<String>();
            p_args.add(username);
            ArrayList list = JDBCEngine.executeQuery(GET_USER_DETAIL, p_args, connection);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     *
     * @param userid
     * @return
     * @throws SQLException
     */
    public EBANKUSER getInfoEbankUser(String userid) throws SQLException {
        CallableStatement calStmt;
        try {
            EBANKUSER user = new EBANKUSER();
            calStmt = connection.prepareCall(PROC_IB_ACCOUNT);
            calStmt.setString(1, userid);
            calStmt.registerOutParameter(2, OracleTypes.CURSOR);
            calStmt.executeQuery();

            ResultSet rs = (ResultSet) calStmt.getObject(2);
            if (rs == null) {
                throw new Exception("Not found");
            }
            List ArrListAc = new ArrayList();
            while (rs.next()) {
                ArrListAc.add(rs.getString("CUST_AC_NO"));
                if (user.getIduser() == null) {
                    user.setADDRESS(rs.getString("ADDRESS"));
                    user.setCHK_STATUS(rs.getString("CHK_STATUS"));
                    user.setFULL_NAME(rs.getString("FULLNAME"));
                    user.setIDNUMBER(rs.getString("IDNUMBER"));
                    user.setIDTYPE(rs.getString("IDTYPE"));
                    user.setISSUEDATE(rs.getString("ISSUEDATE"));
                    user.setISSUEPLACE(rs.getString("ISSUEPLACE"));
                    user.setPHONENUMBER(rs.getString("PHONENUMBER"));
                    user.setIduser(rs.getString("IDUSER"));
                    user.setCUST_NO(rs.getString("CUST_NO"));
                }
            }
            user.setCUST_AC_NO(ArrListAc);
            return user;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            connection.close();
        }
    }

    /**
     *
     * @param username
     * @param newpass
     * @param channel
     * @return
     * @throws Exception
     */
    public int resetPass(String username, String newpass, String channel) throws Exception {
        ArrayList<Comparable> p_args = new ArrayList<Comparable>();
        p_args.add(newpass);
        p_args.add(username);

        if (channel.equals("01")) {

            return JDBCEngine.executeUpdate(resetPass_customer, p_args, connection);
        } else if (channel.equals("11")) {
            return JDBCEngine.executeUpdate(resetPass_admin, p_args, connection);
        }
        return 0;
    }

    String ISEXISTUSERBANKINGUSER = "SELECT party_id , lower(user_id) as user_id FROM USERS a, digx_um_userparty_relation b WHERE lower(a.u_name) = lower(b.user_id) AND a.delete_status = 'N' AND lower(a.u_name) = ?";
    String ISEXISTUSERBANKINGCIF = "SELECT party_id , lower(user_id) as user_id FROM USERS a, digx_um_userparty_relation b WHERE  lower(a.u_name) = lower(b.user_id) AND a.delete_status = 'N' AND lower(b.party_id) = ?";

    /**
     *
     * @param userName
     * @param cif
     * @return
     * @throws SQLException
     */
    public IsExistUserEBRes isExistUserBanking(String userName, String cif) throws SQLException {
        String cifT = "";
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        ArrayList<String> userListT = new ArrayList<>();
        IsExistUserEBRes res = new IsExistUserEBRes();
        try {
            connection.setAutoCommit(false);
            preStatement = connection.prepareStatement(ISEXISTUSERBANKINGUSER);
            preStatement.setString(1, userName);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                cifT = rs.getString("PARTY_ID");
                if (cifT.equals(cif)) {
                    userListT.add(userName);
                    res.setErrorCode(1);
                    res.setUserList(userListT);
                    return res;
                } else {
                    userListT.add("Khong duoc tao user do da  ton tai User " + userName + " ben IB");
                    res.setErrorCode(0);
                    res.setUserList(userListT);
                    return res;
                }
            }
            userListT.clear();
            preStatement = connection.prepareStatement(ISEXISTUSERBANKINGCIF);
            preStatement.setString(1, cif);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                userListT.add(rs.getString("USER_ID"));
            }
            if (userListT.isEmpty()) {
                res.setErrorCode(3);
                userListT.add(userName);
                res.setUserList(userListT);
                return res;
            } else if (userListT.contains(userName)) {
//                userListT.clear();
                res.setErrorCode(1);
                res.setUserList(userListT);
                return res;
            } else {
                res.setErrorCode(2);
                res.setUserList(userListT);
                return res;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            connection.rollback();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        res.setErrorCode(-1);
        return res;
    }

    private final String GET_LIST_FEEDBACK = "select * from feedback where ischecked = ? ";

    public List<FeedbackInfo> getListFeedback(String isChecked) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        List<FeedbackInfo> result = new ArrayList<>();
        try {
            preStatement = connection.prepareStatement(GET_LIST_FEEDBACK);
            preStatement.setString(1, isChecked);
            rs = preStatement.executeQuery();
            while (rs.next()) {
                FeedbackInfo items = new FeedbackInfo();
                items.setFeedbackId(rs.getString("IDFEEDBACK"));
                items.setTypeFeedback(CIMSConstant.GOP_Y);
                items.setSubject(rs.getString("SUBJECT"));
                items.setContent(rs.getString("CONTENT"));
                items.setInsDate(rs.getString("INSDATE"));
                items.setIdChannel(rs.getString("IDCHANNEL"));
                items.setUserId(rs.getString("IDCHANNELUSER"));
                if (rs.getString("IDCHANNEL") != null) {
                    switch (rs.getString("IDCHANNEL")) {
                        case "03":
                            items.setIdChannel("MOBILE");
                            break;
                        default:
                            break;
                    }
                }
                result.add(items);
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private final String UPDATE_ISCHECKED_FEEDBACK = "update Feedback f set f.ischecked = ?, f.idchecker = ?, f.idchannelchecker = ?  where f.idfeedback = ? ";

    public int updateFeedbackId(String isChecked, String feedbackId, String idChecker, String idChannelChecker) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareStatement(UPDATE_ISCHECKED_FEEDBACK);
            preStatement.setString(1, isChecked);
            preStatement.setString(2, idChecker);
            preStatement.setString(3, idChannelChecker);
            preStatement.setString(4, feedbackId);
            int result = preStatement.executeUpdate();
            return result;
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ArrayList getUserOdbxDtl(String username) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("dbobdx");
            ArrayList<String> p_args = new ArrayList<String>();
            p_args.add(username);
            ArrayList list = JDBCEngine.executeQuery(GET_USER_OBDX_DTL, p_args, connection);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ArrayList getUserDeviceOdbxInfo(String soCif) throws Exception {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection("dbobdx");
            ArrayList<String> p_args = new ArrayList<String>();
            p_args.add(soCif);
            ArrayList list = JDBCEngine.executeQuery(GET_USER_DEVICE_OBDX_INFO, p_args, connection);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new SQLException(ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
            
    private final String UPDATE_USER_ADDITION = "update obdx_admin_uat.scb_user_additional_data f set f.NEW_DEVICE_LOGIN_ALLOWED  = ? where f.U_NAME = ? ";

    public int updateUserAdditionalOdbx(String userName, String deviceAllowed) throws SQLException {
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            preStatement = connection.prepareStatement(UPDATE_USER_ADDITION);
            preStatement.setString(1, deviceAllowed);
            preStatement.setString(2, userName);

            int result = preStatement.executeUpdate();
            return result;
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStatement != null) {
                preStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
