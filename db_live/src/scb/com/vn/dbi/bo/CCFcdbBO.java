/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import scb.com.vn.dbi.dao.CCFcdbDAO;

/**
 *
 * @author hieudt
 */
public class CCFcdbBO {

    private CCFcdbDAO fcdbDAO = null;

    /**
     * Create a new instance of CCFcdbBO
     */
    public CCFcdbBO() {
        fcdbDAO = new CCFcdbDAO();
    }

    //Contact center
    /**
     *
     * @param CIF
     * @return
     * @throws Exception
     */
    public String CC_IB_CUST_INFO(String CIF) throws Exception {
        return fcdbDAO.CC_IB_CUST_INFO(CIF);
    }

    /**
     *
     * @param CIF
     * @return
     * @throws Exception
     */
    public String CC_SMS_CUST_INFO(String CIF) throws Exception {
        return fcdbDAO.CC_SMS_CUST_INFO(CIF);
    }

    /**
     *
     * @param CIF
     * @return
     * @throws Exception
     */
    public String CC_SMSALERT_CUST_INFO(String CIF) throws Exception {
        return fcdbDAO.CC_SMSALERT_CUST_INFO(CIF);
    }

    /**
     *
     * @param CIF
     * @return
     * @throws Exception
     */
    public String CC_SMS_ALERT_TD_INFO(String CIF) throws Exception {
        return fcdbDAO.CC_SMS_ALERT_TD_INFO(CIF);
    }

    /**
     *
     * @param CIF
     * @return
     * @throws Exception
     */
    public String CC_CUST_MBANKING_INFO(String CIF) throws Exception {
        return fcdbDAO.CC_CUST_MBANKING_INFO(CIF);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GET_SMS_HIST(String param) throws Exception {
        return fcdbDAO.CC_GET_SMS_HIST(param);
    }

    /**
     *
     * @param CIF
     * @return
     * @throws Exception
     */
    public String CC_GetTTHD_TRANS_HIST(String CIF) throws Exception {
        return fcdbDAO.CC_GetTTHD_TRANS_HIST(CIF);
    }

    /**
     *
     * @param CIF
     * @return
     * @throws Exception
     */
    public String CC_GetTTHD_AUTO_CONTRACT(String CIF) throws Exception {
        return fcdbDAO.CC_GetTTHD_AUTO_CONTRACT(CIF);
    }

    //end of contact center     
}
