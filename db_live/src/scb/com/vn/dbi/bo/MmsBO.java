/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.rmi.RemoteException;
import java.sql.SQLException;
import scb.com.vn.dbi.dao.MmsDAO;

/**
 *
 * @author minhndb
 */
public class MmsBO {

    private MmsDAO mmsDao;

    /**
     * Create a new instance of MmsBO
     */
    public MmsBO() {
        this.mmsDao = new MmsDAO();
    }

    /**
     *
     * @param P_MERCHANT_CODE
     * @param P_TERMINAL_ID
     * @param P_ACCOUNT_MERCHANT
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean MMSCHECKACCOUNT(String P_MERCHANT_CODE, String P_TERMINAL_ID, String P_ACCOUNT_MERCHANT) throws RemoteException, SQLException {
        return mmsDao.MMSCHECKACCOUNT(P_MERCHANT_CODE, P_TERMINAL_ID, P_ACCOUNT_MERCHANT);
    }
}
