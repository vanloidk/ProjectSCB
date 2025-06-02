/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import scb.com.vn.dbi.dao.CWDwhDAO;
import scb.com.vn.dbi.dto.CardInfoDto;
import scb.com.vn.dbi.dto.PmtInfoV11ResDto;

/**
 *
 * @author CORE77
 */
//public class CWDwhBO extends BaseCWDWH{
public class CWDwhBO {

    private CWDwhDAO cwDwhDAO;

    /**
     * Create a new instance of CWDwhBO
     */
    public CWDwhBO() {
        cwDwhDAO = new CWDwhDAO();
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GET_TT_SaoKe(String param) throws Exception {
        return cwDwhDAO.CC_GET_TT_SaoKe(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetCreditPayTransaction(String param) throws Exception {
        return cwDwhDAO.CC_GetCreditPayTransaction(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String CC_GetMonth(String param) throws Exception {
        return cwDwhDAO.CC_GetMonth(param);
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    public String getPMT_INFO(String param) throws Exception {
        return cwDwhDAO.getPMT_INFO(param);
    }

    /**
     * get PMT_INFO V11
     * @param loc
     * @return
     * @throws Exception
     */
    public PmtInfoV11ResDto getPMT_INFOV11(String loc) throws Exception {
        return cwDwhDAO.getPMT_INFOV11(loc);
    }

    /**
     *
     * @param LOC
     * @param refCW
     * @param sotien
     * @param channel
     * @return
     * @throws Exception
     */
    public String getIPP_PAYMENT_PROCESSOR(String LOC, String refCW, String sotien, String channel) throws Exception {
        return cwDwhDAO.getIPP_PAYMENT_PROCESSOR(LOC, refCW, sotien, channel);
    }

    /**
     *
     * @param pCARDNO
     * @return
     * @throws Exception
     */
    public String[] checkCARD(String pCARDNO) throws Exception {
        return cwDwhDAO.checkCARD(pCARDNO);
    }

    /**
     *
     * @param panEncrypt
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public String GetCardTransferStatus(String panEncrypt) throws RemoteException, SQLException {
        return cwDwhDAO.GetCardTransferStatus(panEncrypt);
    }

    /**
     *
     * @param LOC
     * @param socuoi
     * @return
     * @throws Exception
     */
    public String[] getDetailCard(String LOC, String socuoi) throws Exception {
        return cwDwhDAO.getDetailCard(LOC, socuoi);
    }

    /**
     *
     * @param LOC
     * @param Pass
     * @return
     * @throws Exception
     */
    public int checkPassFile(String LOC, String Pass) throws Exception {
        return cwDwhDAO.checkPassFile(LOC, Pass);
    }

    /**
     *
     * @param LOC
     * @return
     * @throws Exception
     */
    public Object[] getPdfFile(String LOC) throws Exception {
        return cwDwhDAO.getPdfFile(LOC);
    }
    public Object[] getPdfFileByMonth(String LOC,String Stmth) throws Exception {
        return cwDwhDAO.getPdfFileByMonth(LOC,Stmth);
    }

    /**
     *
     * @param CARDNUMBER
     * @param mobileno
     * @param fullname
     * @return
     * @throws SQLException
     */
    public String INSERT_INFO_CHANGE_PIN(String CARDNUMBER, String mobileno, String fullname) throws SQLException {
        return cwDwhDAO.INSERT_INFO_CHANGE_PIN(CARDNUMBER, mobileno, fullname);
    }


    /**
     *
     * @param cardaccountno
     * @param period
     * @return
     * @throws Exception
     */
    public ArrayList getMCStateDetail(String cardaccountno, String period) throws Exception {
        return cwDwhDAO.getMCStateDetail(cardaccountno, period);
    }

    /**
     * GetVCardDetail
     * 
     * @param paneCd
     * @return
     * @throws Exception
     */
    public String GetVCardDetail(String paneCd) throws Exception {
        return cwDwhDAO.GetVCardDetail(paneCd);
    }

    /**
     * checkIdPDFs
     * @param panMask
     * @param idPdf
     * @return
     * @throws java.rmi.RemoteException
     * @throws java.sql.SQLException 
     */
     public String checkIdPDF(String panMask, String idPdf) throws RemoteException, SQLException  {
        return cwDwhDAO.checkIdPDF(panMask, idPdf);
    }
}
