/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import scb.com.vn.dbi.dao.MB_ChangeUserDAO;
import scb.com.vn.dbi.dto.MBUser;

/**
 *
 * @author lydty
 */
public class MB_ChangeUserBO extends BaseSMSSCBBO {
    private Connection conn;
    private MB_ChangeUserDAO dao = null;

    /**
     *
     */
    public MB_ChangeUserBO() {
        dao = new MB_ChangeUserDAO();
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
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.getUserMBInfo(pMd5User);
        }
        catch (Exception ex) 
        {
            throw new RemoteException(ex.getMessage(), ex);
        }
        finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public ArrayList checkMBUser(String username) throws Exception
    {
        try
        {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.checkMBUser(username);
        }
        catch (Exception ex) 
        {
            throw new RemoteException(ex.getMessage(), ex);
        }
        finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     *
     * @param md5User
     * @return
     * @throws Exception
     */
    public int updateChangeUser(String md5User) throws Exception
    {
        try
        {
            conn = super.getConnection();
            dao.setConnection(conn);
            return dao.updateChangeUser(md5User);
        }
        catch (Exception ex) 
        {
            throw new RemoteException(ex.getMessage(), ex);
        }
        finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
   
}
