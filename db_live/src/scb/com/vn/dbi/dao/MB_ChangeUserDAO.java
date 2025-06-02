/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import scb.com.vn.dbi.connection.ConnectionManager;
import scb.com.vn.dbi.dto.MBUser;
import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author lydty
 */
public class MB_ChangeUserDAO extends BaseDAO{
    
    //Get thong tin user
    String getUserInfo="SELECT B.MOBILE_NO        UserName,\n" +
"       A.CIF_CORE         CifNo,\n" +
"       A.CUS_NAME         FullName,\n" +
"       A.CUS_ADDR         Address,\n" +
"       A.CUS_BIRTHDAY     BirthDay,\n" +
"       A.CUS_SEX          Sex,\n" +
"       A.CUS_IDNUMBER     IDNumber,\n" +
"       A.CUS_EMAIL        Email,\n" +
"       C.ACCOUNT_NO       AccountNo,\n" +
"       C.ACCOUNT_CODE     AccountTypeCode,\n" +
"       C.ACCOUNT_TYPE     AccountTypeName,\n" +
"       C.ACCOUNT_CURR     AccountCcy,\n" +
"       B.RECEIVED_OTP     MobileNo,\n" +
"       B.AUTH_OTP_SMS     AuthenUseSMS,\n" +
"       B.AUTH_TOKEN       AuthenUseToken,\n" +
"       B.AUTH_TOKEN_VALUE AuthenTokenValue,\n" +
"       A.CUS_TYPE         CusType,\n" +
"       A.STAFF_CODE       StaffCode,\n" +
"       A.STAFF_NAME       StaffName,\n" +
"       B.PACKAGE_CODE_SOTP     PackageCode,\n" +
"       A.CREATED_USER     UserRegister,\n" +
"       A.CONFIRM_USER     UserConfirm,\n" +
"       A.POS_CODE_CIF     BranchCodeCif,\n" +
"       A.POS_CODE         PosCodeUser,\n" +
"       A.CREATED_DATE     CreatedDate,\n" +
"       A.CONFIRM_DATE     ConfirmDate\n" +
"  FROM MB_CUSTOMER@MBANKING A, MB_MOBILES@MBANKING B, MB_ACCOUNTS@MBANKING  C,TBL_CHANGE_USERMB T\n" +
" WHERE A.CUS_ID = B.CUS_ID\n" +
"   AND A.CUS_ID = C.CUS_ID\n" +
"   --AND A.CIF_CORE = '0757159'\n" +
"   AND B.MOBILE_STATUS = '3'\n" +
" And T.Ischanged='N'"  +          
"   And T.CIF= A.CIF_CORE"
            + "  And T.MD5=?";
    String checkUserName="select * from MB_MOBILES@MBANKING B\n" +
" where B.mobile_no=?";
    
    String updateStatus="update TBL_CHANGE_USERMB T\n" +
"set T.Ischanged='Y'\n" +
"where T.Md5=?";
    
    /**
     *
     * @param md5User
     * @return
     * @throws SQLException
     */
    public int updateChangeUser(String md5User) throws SQLException
    {
        PreparedStatement preStatement = null;
        try
        {
            connection.setAutoCommit(true);
            preStatement = connection.prepareStatement(updateStatus);
            preStatement.setString(1,md5User);
            return preStatement.executeUpdate() > 0 ? 1 : 0;
        }
        catch (Exception ex)
        {
            return -1;
        }
        finally
        {
            if(preStatement != null)
            {
                preStatement.close();
            }
            if(connection != null)
            {
                connection.close();
            }
        }
    }

    /**
     *
     * @param pMd5User
     * @return
     * @throws Exception
     */
    public MBUser getUserMBInfo(String pMd5User) throws Exception
    {
        try
        {
        ArrayList<String> p = new ArrayList<String>();
        p.add(pMd5User);;
        ArrayList<?> a = JDBCEngine.executeQuery(getUserInfo, p, connection);
        if (a != null) {
            if(a.size() > 0)
            {
            // Co ton tai USERROLE da su dung ROLE nay. KHong the xoa
                HashMap<?, ?> hm = (HashMap<?, ?>) a.get(0);
                MBUser user= new MBUser();
                user.setUserName(hm.get("username").toString());
                user.setCifNo(hm.get("cifno").toString());
                user.setFullName(hm.get("fullname").toString());
                user.setAddress(hm.get("address").toString());
                user.setBirthDay(hm.get("birthday").toString());
                user.setSex(hm.get("sex").toString());
                user.setIDNumber(hm.get("idnumber").toString());
                user.setEmail(hm.get("email")==null?"":hm.get("email").toString());
                user.setAccountNo(hm.get("accountno")==null?"":hm.get("accountno").toString());
                user.setAccountTypeCode(hm.get("accounttypecode")==null?"":hm.get("accounttypecode").toString());
                user.setAccountTypeName(hm.get("acounttypename")==null?"":hm.get("acounttypename").toString());
                user.setAccountCcy(hm.get("acountccy")==null?"":hm.get("acountccy").toString());
                user.setMobileNo(hm.get("mobileno")==null?"":hm.get("mobileno").toString());
                user.setAuthenUseSMS(hm.get("authenusesms")==null?"":hm.get("authenusesms").toString());
                user.setAuthenUseToken(hm.get("authenusetoken")==null?"":hm.get("authenusetoken").toString());
                user.setAuthenTokenValue(hm.get("authentokenvalue")==null?"":hm.get("authentokenvalue").toString());
                user.setCusType(hm.get("custype")==null?"":hm.get("custype").toString());
                user.setStaffCode(hm.get("staffcode")==null?"":hm.get("staffcode").toString());
                user.setStaffName(hm.get("stafename")==null?"":hm.get("stafename").toString());
                user.setPackageCode(hm.get("packagecode")==null?"":hm.get("packagecode").toString());
                user.setUserRegister(hm.get("userregister")==null?"":hm.get("userregister").toString());
                user.setUserConfirm(hm.get("userconfirm")==null?"":hm.get("userconfirm").toString());
                user.setBranchCodeCif(hm.get("branchcodecif")==null?"":hm.get("branchcodecif").toString());
                user.setPosCodeUser(hm.get("poscodeuser")==null?"":hm.get("poscodeuser").toString());
                user.setCreatedDate(hm.get("createddate")==null?"":hm.get("createddate").toString());
                user.setConfirmDate(hm.get("confirmdate")==null?"":hm.get("confirmdate").toString());
                return user;
            }
        }
        return null;
        } catch (Exception ex) {
            throw new SQLException(ex);
        }
        finally {                          
            if (connection != null) {connection.close();}
        }   
    }

    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public ArrayList checkMBUser(String username) throws Exception {
        
        try {
            ArrayList<String> p_args = new ArrayList<String>();
            p_args.add(username);
            ArrayList list = JDBCEngine.executeQuery(checkUserName, p_args, connection);
            return list;
            } catch (Exception ex) 
            {
                throw new SQLException(ex);
            }finally {
           
            if (connection != null) {
                connection.close();
            }
        }
    }
}
