/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.rmi.RemoteException;
import java.sql.SQLException;
import scb.com.vn.dbi.dao.FCCCustInfoDAO;

/**
 *
 * @author CORE77
 */
public class FCCCustInfoBO {

    private FCCCustInfoDAO fccCustInfoDAO;

    /**
     * Create a new instance of FCCCustInfoBO
     */
    public FCCCustInfoBO() {
        fccCustInfoDAO = new FCCCustInfoDAO();
    }

    /**
     *
     * @param CIF
     * @return
     * @throws Exception
     */
    public String GetCustomerInfoByCIF(String CIF) throws Exception {
        return fccCustInfoDAO.GetCustomerInfoByCIF(CIF);
    }

    /**
     *
     * @param CMND
     * @return
     * @throws Exception
     */
    public String GetCustomerInfoByIDCard(String CMND) throws Exception {
        return fccCustInfoDAO.GetCustomerInfoByIDCard(CMND);
    }

    /**
     *
     * @param phone
     * @return
     * @throws Exception
     */
    public String GetCustomerInfoByPhone(String phone) throws Exception {
        return fccCustInfoDAO.GetCustomerInfoByPhone(phone);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetListAccount(String param) throws Exception {
        return fccCustInfoDAO.CC_GetListAccount(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetRecentTransaction(String param) throws Exception {
        return fccCustInfoDAO.CC_GetRecentTransaction(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetTDAccounts(String param) throws Exception {
        return fccCustInfoDAO.CC_GetTDAccounts(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetTDAccountDetails(String param) throws Exception {
        return fccCustInfoDAO.CC_GetTDAccountDetails(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetTDAccountTranHistDetails(String param) throws Exception {
        return fccCustInfoDAO.CC_GetTDAccountTranHistDetails(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetCLAccounts(String param) throws Exception {
        return fccCustInfoDAO.CC_GetCLAccounts(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetCLAccountDetails(String param) throws Exception {
        return fccCustInfoDAO.CC_GetCLAccountDetails(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetCLHistPaymentDetails(String param) throws Exception {
        return fccCustInfoDAO.CC_GetCLHistPaymentDetails(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetCLAccountPayCalDetails(String param) throws Exception {
        return fccCustInfoDAO.CC_GetCLAccountPayCalDetails(param);
    }
}
