/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import scb.com.vn.common.model.cims.kt.KhoaTheReq;
import scb.com.vn.common.model.cims.kt.MoKhoaTheReq;
import scb.com.vn.common.model.cw.CardInfo;
import scb.com.vn.common.model.cw.SenderMSVSCardInfo;
import scb.com.vn.dbi.dao.CWLiveDAO;
import scb.com.vn.dbi.dto.MasterSenderInfor;

/**
 *
 * @author CORE77
 */
public class CWLiveBO {

    private CWLiveDAO cwLiveDAO;

    /**
     * Create a new instance of CWLiveBO
     */
    public CWLiveBO() {
        cwLiveDAO = new CWLiveDAO();
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetInternalCardInfo(String param) throws Exception {
        return cwLiveDAO.CC_GetInternalCardInfo(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetInternalCardTransaction(String param) throws Exception {
        return cwLiveDAO.CC_GetInternalCardTransaction(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetInternalCardInfo_MC(String param) throws Exception {
        return cwLiveDAO.CC_GetInternalCardInfo_MC(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetInternalCardInfo_MCDB(String param) throws Exception {
        return cwLiveDAO.CC_GetInternalCardInfo_MCDB(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetCard_Profile(String param) throws Exception {
        return cwLiveDAO.CC_GetCard_Profile(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetAward_Point(String param) throws Exception {
        return cwLiveDAO.CC_GetAward_Point(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetInternalCardTran_MC(String param) throws Exception {
        return cwLiveDAO.CC_GetInternalCardTran_MC(param);
    }

    /**
     *
     * @param phone
     * @return
     * @throws Exception
     */
    public String CC_GetExtendedCustInfoByPhone(String phone) throws Exception {
        return cwLiveDAO.CC_GetExtendedCustInfoByPhone(phone);
    }

    /**
     *
     * @param cif
     * @return
     * @throws Exception
     */
    public String CC_GetExtendedCustInfo(String cif) throws Exception {
        return cwLiveDAO.CC_GetExtendedCustInfo(cif);
    }

    /**
     *
     * @param cif
     * @return
     * @throws Exception
     */
    public List GetAccountListByCustNoCard(String cif) throws Exception {
        return cwLiveDAO.GetAccountListByCustNoCard(cif);
    }

    /**
     *
     * @param panceCD
     * @param fromDate
     * @param toDate
     * @param SR
     * @param rownum
     * @return
     * @throws Exception
     */
    public List getTransactionMaterCardByCardnoByTime(String panceCD, String fromDate, String toDate, String SR, String rownum) throws Exception {
        return cwLiveDAO.getTransactionMaterCardByCardnoByTime(panceCD, fromDate, toDate, SR, rownum);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetIPP(String param) throws Exception {
        return cwLiveDAO.CC_GetIPP(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetIPPDetail(String param) throws Exception {
        return cwLiveDAO.CC_GetIPPDetail(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetIPPHist(String param) throws Exception {
        return cwLiveDAO.CC_GetIPPHist(param);
    }

    /* MASTERPASS */
    /**
     *
     * @param loc4Digit
     * @return
     * @throws Exception
     */
    public MasterSenderInfor queryMasterpassCardInfor(String loc4Digit) throws Exception {
        return cwLiveDAO.queryMasterpassCardInfor(loc4Digit);
    }
    
    public SenderMSVSCardInfo querySenderMSVSCardInfo(String loc4Digit) throws Exception {
        return cwLiveDAO.querySenderMSVSCardInfo(loc4Digit);
    }

    /* MASTERPASS */
    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean checkPhoneNumberAtCW(String phoneNumber) throws RemoteException, SQLException {
        return cwLiveDAO.checkPhoneNumberAtCW(phoneNumber);
    }

    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<CardInfo> GetCardInfoByPhone(String phoneNumber) throws RemoteException, SQLException {
        return cwLiveDAO.GetCardInfoByPhone(phoneNumber);
    }

    /**
     *
     * @param phoneNumber
     * @param last4Digit
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<CardInfo> GetCardInfoByPhone(String phoneNumber, String last4Digit) throws RemoteException, SQLException {
        return cwLiveDAO.GetCardInfoByPhone(phoneNumber, last4Digit);
    }

    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<CardInfo> getCardInfo(KhoaTheReq req) throws RemoteException, SQLException {
        return cwLiveDAO.getCardInfo(req);
    }
    
    /**
     *
     * @param pCARDNO
     * @return
     * @throws Exception
     */
    public String[] checkCARD(String pCARDNO) throws Exception {
        return cwLiveDAO.checkCARD(pCARDNO);
    }
    
    /**
     *GetCardLockedInfo
     * 
     * @param req
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public List<CardInfo> getCardLockedInfo(MoKhoaTheReq req) throws RemoteException, SQLException {
        return cwLiveDAO.getCardLockedInfo(req);
    }
}
