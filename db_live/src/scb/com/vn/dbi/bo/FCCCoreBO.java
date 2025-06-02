/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateReq;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateRes;
import scb.com.vn.common.model.cims.taikhoan.GiaiToaTKTTReq;
import scb.com.vn.common.model.cims.taikhoan.GiaiToaTKTTRes;
import scb.com.vn.common.model.cims.taikhoan.PhongToaTKTTRes;
import scb.com.vn.common.model.cims.taikhoan.PhongToaTKTTReq;
import scb.com.vn.dbi.dao.FCCCoreDAO;
import scb.com.vn.dbi.dto.CustomerInfo;
import scb.com.vn.dbi.dto.InsuranceCustInfoDto;
import scb.com.vn.dbi.dto.ProcedureCallDto;

/**
 *
 *
 * @author minhndb
 */
public class FCCCoreBO {

    private FCCCoreDAO fccCoreDAO;

    /**
     * Create a new instance of FCCCoreBO
     */
    public FCCCoreBO() {
        fccCoreDAO = new FCCCoreDAO();
    }

    /**
     *
     * @param glAcc
     * @param amount
     * @param content
     * @param idcardName
     * @param idcardAddress
     * @param idcard
     * @param idcardDob
     * @param user_maker
     * @param user_checker
     * @param branchcard
     * @return
     * @throws Exception
     */
    public String transferFCUBS(String glAcc, BigDecimal amount, String content, String idcardName, String idcardAddress, String idcard, String idcardDob, String user_maker, String user_checker, String branchcard) throws Exception {
        return fccCoreDAO.transferFCUBS(glAcc, amount, content, idcardName, idcardAddress, idcard, idcardDob, user_maker, user_checker, branchcard);
    }

    /**
     *
     * @param custno
     * @return
     * @throws Exception
     */
    public List GetAccountListByCustNoFCC(String custno) throws Exception {
        return fccCoreDAO.GetAccountListByCustNoFCC(custno);
    }

    /**
     *
     * @param acccount
     * @return
     * @throws Exception
     */
    public ArrayList getSttmCustAccountSynFCC(String acccount) throws Exception {
        return fccCoreDAO.getSttmCustAccountSynFCC(acccount);
    }

    /**
     *
     * @param custAcc
     * @param amount
     * @param content
     * @param user_maker
     * @param user_checker
     * @return
     * @throws Exception
     */
    public String transferFCUBSCash(String custAcc, BigDecimal amount, String content, String user_maker, String user_checker) throws Exception {
        return fccCoreDAO.transferFCUBSCash(custAcc, amount, content, user_maker, user_checker);
    }
    
    public String transferFCUBSCashv2(String custAcc, BigDecimal amount, String content, String user_maker, String user_checker, String noiCap, String ngayCap, String sodt, String nguoiGiaoDich, String diaChi, String cmnd) throws Exception {
        return fccCoreDAO.transferFCUBSCashv2(custAcc, amount, content, user_maker, user_checker, noiCap, ngayCap, sodt, nguoiGiaoDich, diaChi, cmnd);
    }

    /**
     *
     * @param P_TRN_REF
     * @param P_XREF
     * @param pm_gdv
     * @param pm_ksv
     * @return
     * @throws Exception
     */
    public String revertTransferFCUBS(String P_TRN_REF, String P_XREF, String pm_gdv, String pm_ksv) throws Exception {
        return fccCoreDAO.revertTransferFCUBS(P_TRN_REF, P_XREF, pm_gdv, pm_ksv);
    }

    /**
     *
     * @param fromAcc
     * @param loanAcc
     * @param laitronghan
     * @param vontronghan
     * @param laiphatlai
     * @param laitphatgoc
     * @param laiphatkhac
     * @param maker
     * @param checker
     * @return
     * @throws Exception
     */
    public String PaymentLoanAccount(String fromAcc, String loanAcc, BigDecimal laitronghan, BigDecimal vontronghan, BigDecimal laiphatlai, BigDecimal laitphatgoc,
            BigDecimal laiphatkhac, String maker, String checker) throws Exception {
        return fccCoreDAO.PaymentLoanAccount(fromAcc, loanAcc, laitronghan, vontronghan, laiphatlai, laitphatgoc, laiphatkhac, maker, checker);
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public String getBranchCode(String accountno) throws Exception {
        return fccCoreDAO.getBranchCode(accountno);
    }

    /**
     *
     * @param glAcc
     * @param amount
     * @param content
     * @param idcardName
     * @param idcardAddress
     * @param idcard
     * @param idcardDob
     * @param user_maker
     * @param user_checker
     * @param branchcard
     * @param Fee
     * @param Vat
     * @return
     * @throws Exception
     */
    public String transferFCUBSWithFee(String glAcc, BigDecimal amount, String content, String idcardName,
            String idcardAddress, String idcard, String idcardDob, String user_maker,
            String user_checker, String branchcard, BigDecimal Fee, BigDecimal Vat) throws Exception {
        return fccCoreDAO.transferFCUBSWithFee(glAcc, amount, content, idcardName, idcardAddress, idcard, idcardDob, user_maker,
                user_checker, branchcard, Fee, Vat);
    }

    /**
     *
     * @param branchcode
     * @param accountclass
     * @param amount
     * @param ccy
     * @return
     * @throws Exception
     */
    public ArrayList getAccountClassInfo(String branchcode, String accountclass, String amount, String ccy, String cif) throws Exception {
        {
            return fccCoreDAO.getAccountClassInfo(branchcode, accountclass, amount, ccy, cif);
        }
    }

    /**
     *
     * @param branchcode
     * @param accountclass
     * @param amount
     * @param ccy
     * @return
     * @throws Exception
     */
    public ArrayList getAccountClassInfoNew(String branchcode, String accountclass, String amount, String ccy) throws Exception {
        {
            return fccCoreDAO.getAccountClassInfoNew(branchcode, accountclass, amount, ccy);
        }
    }

    /**
     *
     * @param brnTrn
     * @param productcode
     * @param ccy
     * @param amt
     * @param acNo
     * @param acBrn
     * @return
     * @throws Exception
     */
    public ProcedureCallDto getFeeMobile(String brnTrn, String productcode, String ccy, String amt, String acNo, String acBrn) throws Exception {
        return fccCoreDAO.getFeeMobile(brnTrn, productcode, ccy, amt, acNo, acBrn);
    }

    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    public boolean checkPhoneNumberAtCore(String phoneNumber) throws RemoteException, SQLException {
        return fccCoreDAO.checkPhoneNumberAtCore(phoneNumber);
    }

    /**
     *
     * @param pm_product
     * @param pm_cr_brn
     * @param pm_cr_acc
     * @param pm_amt
     * @param pm_loaitien
     * @param pm_noidung
     * @param pm_tennguoinop
     * @param pm_diachinguoinop
     * @param pm_socmnd
     * @param pm_ngaycap
     * @param pm_noicap
     * @param pm_gdv
     * @param pm_ksv
     * @return
     * @throws SQLException
     */
    public String[] transferFCUBSForIBTCounter(String pm_product,
            String pm_cr_brn,
            String pm_cr_acc,
            String pm_amt,
            String pm_loaitien,
            String pm_noidung,
            String pm_tennguoinop,
            String pm_diachinguoinop,
            String pm_socmnd,
            String pm_ngaycap,
            String pm_noicap,
            String pm_gdv,
            String pm_ksv) throws SQLException {
        return fccCoreDAO.transferFCUBSForIBTCounter(pm_product, pm_cr_brn, pm_cr_acc, pm_amt, pm_loaitien, pm_noidung, pm_tennguoinop, pm_diachinguoinop, pm_socmnd, pm_ngaycap, pm_noicap, pm_gdv, pm_ksv);
    }

    /**
     *
     * @param acccountNo
     * @param branchcode
     * @param amount
     * @return
     * @throws Exception
     */
    public ProcedureCallDto validateTopupTKTichLuy(String acccountNo, String branchcode, String amount) throws Exception {
        return fccCoreDAO.validateTopupTKTichLuy(acccountNo, branchcode, amount);
    }

    /**
     *
     * @param brnTrn
     * @param productcode
     * @param ccy
     * @param amt
     * @param acNo
     * @param acBrn
     * @return
     * @throws Exception
     */
    public ProcedureCallDto getFeeMobileHasSFee(String brnTrn, String productcode, String ccy, String amt, String acNo, String acBrn) throws Exception {
        return fccCoreDAO.getFeeMobileHasSFee(brnTrn, productcode, ccy, amt, acNo, acBrn);
    }

    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    public ArrayList getAvgBalanceFromCore(String accountno) throws Exception {
        return fccCoreDAO.getAvgBalanceFromCore(accountno);
    }

    /**
     *
     * @param req
     * @return
     * @throws Exception
     */
    public ExchangeRateRes getExchangeRate(ExchangeRateReq req) throws Exception {
        return fccCoreDAO.getExchangeRate(req);
    }

    public InsuranceCustInfoDto getCustInfoByCIFID(String cif) throws Exception {
        return fccCoreDAO.getCustInfoByCIFID(cif);
    }

    public CustomerInfo getCustInfoByCif(String cifno) throws Exception {
        return fccCoreDAO.getCustInfoByCif(cifno);
    }

    public String[] transferFCUBSForIBTCounter_247(String pm_product,
            String pm_cr_brn,
            String pm_cr_acc,
            String pm_amt,
            String pm_loaitien,
            String pm_noidung,
            String pm_tennguoinop,
            String pm_diachinguoinop,
            String pm_socmnd,
            String pm_ngaycap,
            String pm_noicap,
            String pm_gdv,
            String pm_ksv,
            String destAccount,
            String destBank) throws SQLException {
        return fccCoreDAO.transferFCUBSForIBTCounter_247(pm_product, pm_cr_brn, pm_cr_acc, pm_amt, pm_loaitien, pm_noidung, pm_tennguoinop, pm_diachinguoinop, pm_socmnd, pm_ngaycap, pm_noicap, pm_gdv, pm_ksv, destAccount, destBank);
    }

    public String checkOverKYC(String custAcc, String brnAcc, BigDecimal amount) throws Exception {
        return fccCoreDAO.checkOverKYC(custAcc, brnAcc, amount);
    }
    
    public PhongToaTKTTRes phongToaTKTT(PhongToaTKTTReq req) throws Exception {
        return fccCoreDAO.phongToaTKTT(req);
    }

    public GiaiToaTKTTRes giaiToaTKTT(GiaiToaTKTTReq req) throws Exception {
        return fccCoreDAO.giaiToaTKTT(req);
    }
}
