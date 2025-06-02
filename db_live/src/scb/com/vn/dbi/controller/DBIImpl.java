package scb.com.vn.dbi.controller;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.log4j.Level;
import scb.com.vn.common.model.cw.CardInfo;
import scb.com.vn.common.model.masterpass.MCPaymentRp;
import scb.com.vn.common.model.masterpass.PayByQRCodeRq;
import scb.com.vn.common.model.mvisa.MVISAQRRQ;
import scb.com.vn.common.model.mvisa.ResponseMessage;
import scb.com.vn.common.model.vnpayqr.CheckQRRp;
import scb.com.vn.common.model.vnpayqr.CheckQRRq;
import scb.com.vn.common.model.vnpayqr.PaymentQRJson;
import scb.com.vn.common.model.vnpayqr.PaymentQRRp;
import scb.com.vn.common.model.vnpayqr.PaymentQRRq;
import scb.com.vn.common.model.vnpayqr.RefundQRJson;
import scb.com.vn.common.model.vnpayqr.RefundQRRp;
import scb.com.vn.dbi.bo.*;
import scb.com.vn.dbi.dto.*;
import scb.com.vn.dbi.utility.HibernateUtil;
import scb.com.vn.dbi.utility.ProcessorReport;
import scb.com.vn.dbi.utility.fcdb.PasswordDigest;
import scb.com.vn.dbi.utility.Configuration;
import scb.com.vn.dbi.utility.Helper;
import scb.com.vn.dbi.utility.fcdb.GenOBDXPassword;
import scb.com.vn.dbi.dto.SMSService;
import scb.com.vn.dbi.dto.SMSServiceTK;
import scb.com.vn.dbi.dto.SMSServiceUser;
import java.util.Iterator;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateReq;
import scb.com.vn.common.model.ExchangeRate.ExchangeRateRes;
import scb.com.vn.common.model.SqlCommand;
import scb.com.vn.common.model.cims.kht.KichHoatTheInfo;
import scb.com.vn.common.model.cims.kt.KhoaTheDetailsInfo;
import scb.com.vn.common.model.cims.kt.KhoaTheInfo;
import scb.com.vn.common.model.cims.kt.KhoaTheReq;
import scb.com.vn.common.model.cims.kt.MoKhoaTheReq;
import scb.com.vn.common.model.cims.recieveFeedback.ReceiveFeedbackReq;
import scb.com.vn.common.model.cims.register.FeedbackInfo;
import scb.com.vn.common.model.cims.register.RegisterInfoDetail;
import scb.com.vn.common.model.cims.taikhoan.GiaiToaTKTTReq;
import scb.com.vn.common.model.cims.taikhoan.GiaiToaTKTTRes;

import scb.com.vn.common.model.cims.taikhoan.PhongToaTKTTRes;
import scb.com.vn.common.model.cims.taikhoan.PhongToaTKTTReq;
import scb.com.vn.common.model.collated.CollatedDetail;
import scb.com.vn.common.model.cw.CardAdjustmentReq;
import scb.com.vn.common.model.cw.CardAdjustmentRes;
import scb.com.vn.common.model.cw.DirectDebitRes;
import scb.com.vn.common.model.cw.SenderMSVSCardInfo;
import scb.com.vn.common.model.masterpass.MasterCardQrActionEnum;
import scb.com.vn.common.model.transfer.TransactionDetail;
import scb.com.vn.common.odbx.SCBBranch;
import scb.com.vn.dbi.utility.DatabaseController;
import scb.com.vn.ultility.DBActionEnum;
import scb.com.vn.common.model.info.SmsCustRegisInfo;
import scb.com.vn.common.model.sms.SmsDetail;
import scb.com.vn.common.model.sms.SmsInfo;
import scb.com.vn.common.model.transfer.SenderInfo;
import scb.com.vn.common.model.transfer.napas.TransferMoney247EbankReq;
import scb.com.vn.common.model.transfer.status.TransactionDetailByDate;
import scb.com.vn.common.odbx.IsExistUserEBRes;
import scb.com.vn.dbi.controller.IDBI;
import scb.com.vn.dbi.dao.CWDwhDAO;
import scb.com.vn.dbi.dto.PmtInfoV11ResDto;
import scb.com.vn.dbi.dto.PBLBillPaidCustomerDto.PBLBillPaidCusDto;
import scb.com.vn.dbi.dto.PBLBillPaidCustomerDto.PBLBillPaidCustomerDtlForJobDto;
import scb.com.vn.dbi.dto.BillPaidCustGrpDto;
import scb.com.vn.dbi.dto.InsuranceCustInfoDto;
import scb.com.vn.model.status.transferMoney.GetStatusOfTransferMoneyReq;
import scb.com.vn.model.status.transferMoney.TransferMoneyTransactionInfo;

/**
 *
 * @author phucnnd
 */
public class DBIImpl extends UnicastRemoteObject implements IDBI {

    private static final Logger LOGGER = Logger.getLogger(DBIImpl.class);

    private static final long serialVersionUID = -8623268800167213773L;

    /**
     *
     * @throws RemoteException
     */
    public DBIImpl() throws RemoteException {
        super(); // Thieu la bao loi a        
    }

    /**
     *
     * @param port
     * @throws RemoteException
     */
    public DBIImpl(int port) throws RemoteException {
        super(port); // Thieu la bao loi a        
    }

    /**
     *
     * @param test
     * @return
     * @throws RemoteException
     */
    @Override
    public String getString(String test) throws RemoteException {
        LOGGER.info("getString --> [IN] --> test = [" + test + "]");
        String result = "Chao ban " + test + ".";
        LOGGER.info("getString --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public String getString() throws RemoteException {
        LOGGER.info("getString --> [IN] --> input is empty");
        String result = "Chao ban. Da ket noi thanh cong.";
        LOGGER.info("getString --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /* ============ GENERIC ============ */
    /**
     *
     * @param xml
     * @param fileXslt
     * @param fileOut
     * @return
     * @throws RemoteException
     */
    @Override
    public int processorPDF(String xml, String fileXslt, String fileOut) throws RemoteException {
        ProcessorReport pr = new ProcessorReport();
        try {
            LOGGER.info("processorPDF --> [IN] --> xml = [" + xml + "], fileXslt = [" + fileXslt + "], fileOut = [" + fileOut + "]");
            int result = pr.processorPDF(xml, fileXslt, fileOut);
            LOGGER.info("processorPDF --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /* ============PARTNERINFO============ */
    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<Hashtable<String, String>> getallPartner() throws RemoteException {
        try {
            LOGGER.info("getallPartner --> [IN] --> input is empty");
            PartnerBO partnerBO = new PartnerBO();
            ArrayList<Hashtable<String, String>> result = partnerBO.getallPartner();
            LOGGER.info("getallPartner --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accesskey
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<Hashtable<String, String>> getPartnerByAccesskey(String accesskey) throws RemoteException {
        try {
            LOGGER.info("getPartnerByAccesskey --> [IN] --> accesskey = [" + accesskey + "]");
            PartnerBO partnerBO = new PartnerBO();
            PartnerDTO p = partnerBO.getPartnerByAccesskey(accesskey);

            if (p == null) {
                return null;
            }
            ArrayList<Hashtable<String, String>> a = new ArrayList<Hashtable<String, String>>();
            for (int i = 0; i < p.size(); i++) {
                a.add((Hashtable<String, String>) p.get(i));
            }
            LOGGER.info("getPartnerByAccesskey --> [OUT] --> Size = [" + a.size() + "]");
            return a;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ====== LOG ====== */
    /**
     *
     * @param logger
     * @param lvl
     * @param msg
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertLog(String logger, String lvl, String msg) throws RemoteException {
        try {
            LOGGER.info("insertLog --> [IN] --> logger = [" + logger + "], lvl = [" + lvl + "], msg = [" + msg + "]");
            LogBO logBO = new LogBO();
            int result = logBO.insertLog(logger, lvl, msg);
            LOGGER.info("insertLog --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ====== TOKENKEY ====== */
    /**
     *
     * @param serialno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getTokenkeybankBySerial(String serialno) throws RemoteException {
        try {
            LOGGER.info("getTokenkeybankBySerial --> [IN] --> serialno = [" + serialno + "]");
            TokenkeyBO tokenkeyBO = new TokenkeyBO();
            ArrayList result = tokenkeyBO.getTokenkeybankBySerial(serialno);
            LOGGER.info("getTokenkeybankBySerial --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ====== SMS ====== */
    /**
     *
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws RemoteException
     */
    @Override
    public int sendSMS(String mobile, String content, String servicecode, String requestid) throws RemoteException {
        try {
            LOGGER.info("sendSMS --> [IN] --> mobile = [" + mobile + "], content = [" + content + "], servicecode = [" + servicecode + "], requestid = [" + requestid + "]");
            SmsBO smsBO = new SmsBO();
            int result = smsBO.sendSMS(mobile, content, servicecode, requestid);
            LOGGER.info("sendSMS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws RemoteException
     */
    @Override
    public int sendSMS8149(String mobile, String content, String servicecode, String requestid) throws RemoteException {
        try {
            LOGGER.info("sendSMS8149 --> [IN] --> mobile = [" + mobile + "], content = [" + content + "], servicecode = [" + servicecode + "], requestid = [" + requestid + "]");
            SmsBO smsBO = new SmsBO();
            int result = smsBO.sendSMS8149(mobile, content, servicecode, requestid);
            LOGGER.info("sendSMS8149 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws RemoteException
     */
    @Override
    public int sendSMSTemp(String mobile, String content, String servicecode, String requestid) throws RemoteException {
        try {
            LOGGER.info("sendSMSTemp --> [IN] --> mobile = [" + mobile + "], content = [" + content + "], servicecode = [" + servicecode + "], requestid = [" + requestid + "]");
            SmsBO smsBO = new SmsBO();
            int result = smsBO.sendSMSTemp(mobile, content, servicecode, requestid);
            LOGGER.info("sendSMSTemp --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param mobile
     * @param content
     * @param servicecode
     * @param requestid
     * @return
     * @throws RemoteException
     */
    @Override
    public int sendSMSTNB(String mobile, String content, String servicecode, String requestid) throws RemoteException {
        try {
            LOGGER.info("sendSMSTNB --> [IN] --> mobile = [" + mobile + "], content = [" + content + "], servicecode = [" + servicecode + "], requestid = [" + requestid + "]");
            SmsBO smsBO = new SmsBO();
            int result = smsBO.sendSMSTNB(mobile, content, servicecode, requestid);
            LOGGER.info("sendSMSTNB --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //duytxa15072015 gui tin nhan sms khoa dich vu khi no phi
    /**
     *
     * @param mobile
     * @param custno
     * @return
     * @throws RemoteException
     */
    @Override
    public int lockSMS(String mobile, String custno) throws RemoteException {
        try {
            LOGGER.info("lockSMS --> [IN] --> mobile = [" + mobile + "], custno = [" + custno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.lockSMS(mobile, custno);
            LOGGER.info("lockSMS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custno
     * @param thangnam
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getmobileuserthuphisms(String custno, String thangnam) throws RemoteException {
        try {
            LOGGER.info("getmobileuserthuphisms --> [IN] --> custno = [" + custno + "], thangnam = [" + thangnam + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getmobileuserthuphisms(custno, thangnam);
            LOGGER.info("getmobileuserthuphisms --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    //duytxa15072015

    //duytxa15072015 gui tin nhan sms khoa dich vu khi no phi
    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    @Override
    public int lockEbankService(String custno) throws RemoteException {
        try {
            LOGGER.info("lockEbankService --> [IN] --> custno = [" + custno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.lockEbankService(custno);
            LOGGER.info("lockEbankService --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    //endduytxa28062017
    //duytxa28062017

//    @Override
//    public ArrayList<Hashtable<String, String>> getSMSFromTNB() throws RemoteException {
//        try {
//
//            SmsBO smsBO = new SmsBO();
//            SmsReceiverTNBDTO p = smsBO.getSMSFromTNB();
//
//            if (p == null) {
//                return null;
//            }
//            ArrayList<Hashtable<String, String>> a = new ArrayList<Hashtable<String, String>>();
//            for (int i = 0; i < p.size(); i++) {
//                a.add((Hashtable<String, String>) p.get(i));
//            }
//
//            return a;
//        } catch (Exception ex) {
//            throw new RemoteException(ex.getMessage(), ex);
//        }
//    }
    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateStatus(String id, String status) throws RemoteException {
        try {
            LOGGER.info("updateStatus --> [IN] --> id = [" + id + "], status = [" + status + "]");
            SmsBO smsBO = new SmsBO();
            int result = smsBO.updateStatus(id, status);
            LOGGER.info("updateStatus --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList getAllSmsReceiver() throws RemoteException {
        try {
            LOGGER.info("getAllSmsReceiver --> [IN] --> input is empty");
            SmsBO smsBO = new SmsBO();
            ArrayList result = smsBO.getAllSmsReceiver();
            LOGGER.info("getAllSmsReceiver --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    @Override
    public int doMoveSmsReceiverToHistory(String id, String status) throws RemoteException {
        try {
            LOGGER.info("doMoveSmsReceiverToHistory --> [IN] --> id = [" + id + "], status = [" + status + "]");
            SmsBO smsBO = new SmsBO();
            int result = smsBO.doMoveSmsReceiverToHistory(id, status);
            LOGGER.info("doMoveSmsReceiverToHistory --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ====== FCDB ====== */
    /**
     *
     * @param acccount
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAccountCASA(String acccount) throws RemoteException {
        try {
            LOGGER.info("getAccountCASA --> [IN] --> acccount = [" + acccount + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            ArrayList result = BO.getAccountCASA(acccount);
            LOGGER.info("getAccountCASA --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //duytxa 07/09/2015 for feeautosms   
    /**
     *
     * @param custno
     * @param acctype
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAccountCASAMaxbalanceFeesms(String custno, String acctype) throws RemoteException {
        try {
            LOGGER.info("getAccountCASAMaxbalanceFeesms --> [IN] --> custno = [" + custno + "], acctype = [" + acctype + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            ArrayList result = fcdbBO.getAccountCASAMaxbalanceFeesms(custno, acctype);
            LOGGER.info("getAccountCASAMaxbalanceFeesms --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    //endduytxa 07/09/2015 for feeautosms

    //duytxa 20/06/2017 for feeautosms   
    /**
     *
     * @param custno
     * @param acctype
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAccountCASAMaxbalanceFeesmsKHDN(String custno, String acctype) throws RemoteException {
        try {
            LOGGER.info("getAccountCASAMaxbalanceFeesmsKHDN --> [IN] --> custno = [" + custno + "], acctype = [" + acctype + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            ArrayList result = fcdbBO.getAccountCASAMaxbalanceFeesmsKHDN(custno, acctype);
            LOGGER.info("getAccountCASAMaxbalanceFeesmsKHDN --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    //duytxa 20/06/2017 for feeautosms   

    /**
     *
     * @param acccount
     * @param rownum
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getDetailsAccountCASA(String acccount, int rownum) throws RemoteException {
        try {
            LOGGER.info("getDetailsAccountCASA --> [IN] --> acccount = [" + acccount + "], rownum = [" + rownum + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            ArrayList result = BO.getDetailsAccountCASA(acccount, rownum);
            LOGGER.info("getDetailsAccountCASA --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param acccountTD
     * @param branchAcc
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAccountTD(String acccountTD, String branchAcc) throws RemoteException {
        try {
            LOGGER.info("getAccountTD --> [IN] --> acccountTD = [" + acccountTD + "], branchAcc = [" + branchAcc + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            ArrayList result = BO.getAccountTD(acccountTD, branchAcc);
            LOGGER.info("getAccountTD --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accounttd
     * @param cif
     * @param idcard
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getListAccountTDDetail(String accounttd, String cif, String idcard) throws RemoteException {
        try {
            LOGGER.info("getListAccountTDDetail --> [IN] --> accounttd = [" + accounttd + "], cif = [" + cif + "], idcard = [" + idcard + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getListAccountTDDetail(accounttd, cif, idcard);
            LOGGER.info("getListAccountTDDetail --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ccyfrom
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getFXRates(String ccyfrom) throws RemoteException {
        try {
            LOGGER.info("getFXRates --> [IN] --> ccyfrom = [" + ccyfrom + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getFXRates(ccyfrom);
            LOGGER.info("getFXRates --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param mobile
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCustomerSMSByMobile(String mobile) throws RemoteException {
        try {
            LOGGER.info("getCustomerSMSByMobile --> [IN] --> mobile = [" + mobile + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getCustomerSMSByMobile(mobile);
            LOGGER.info("getCustomerSMSByMobile --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idEntity
     * @param idChannel
     * @param idChanneluser
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCustomerInfo(String idEntity, String idChannel, String idChanneluser) throws RemoteException {
        try {
            LOGGER.info("getCustomerInfo --> [IN] --> idEntity = [" + idEntity + "], idChannel = [" + idChannel + "], idChanneluser = [" + idChanneluser + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getCustomerInfo(idEntity, idChannel, idChanneluser);
            LOGGER.info("getCustomerInfo --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idEntity
     * @param idChanneluser
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCustomerInfoByIdChannelUser(String idEntity, String idChanneluser) throws RemoteException {
        try {
            LOGGER.info("getCustomerInfoByIdChannelUser --> [IN] --> idEntity = [" + idEntity + "], idChanneluser = [" + idChanneluser + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getCustomerInfoByIdChannelUser(idEntity, idChanneluser);
            LOGGER.info("getCustomerInfoByIdChannelUser --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idEntity
     * @param userid
     * @param idChannel
     * @param idChanneluser
     * @param newpassword
     * @return
     * @throws RemoteException
     */
    @Override
    public String generatePassword(String idEntity, String userid, String idChannel, String idChanneluser,
            String newpassword) throws RemoteException {
        try {
            LOGGER.info("generatePassword --> [IN] --> idEntity = [" + idEntity + "], userid = [" + userid + "], idChannel = [" + idChannel + "], idChanneluser = [" + idChanneluser + "]");
            // Ma hoa password
            String result = (new PasswordDigest().getPasswordDigest(idEntity, userid, idChannel, newpassword));
            LOGGER.info("generatePassword --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idEntity
     * @param userid
     * @param idChannel
     * @param idChanneluser
     * @param newpassword
     * @return
     * @throws RemoteException
     */
    @Override
    public int changePassword(String idEntity, String userid, String idChannel, String idChanneluser, String newpassword)
            throws RemoteException {
        try {
            LOGGER.info("changePassword --> [IN] --> idEntity = [" + idEntity + "], userid = [" + userid + "], idChannel = [" + idChannel + "], idChanneluser = [" + idChanneluser + "], newpassword = [" + newpassword + "]");
            // Ma hoa password
            String newpassword_encrypted = new PasswordDigest().getPasswordDigest(idEntity, userid, idChannel,
                    newpassword);

            FcdbBO fcdbBO = new FcdbBO();
            int result = fcdbBO.changePassword(idEntity, userid, idChannel, idChanneluser, newpassword_encrypted);
            LOGGER.info("changePassword --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idEntity
     * @param userid
     * @param idChannel
     * @param idChanneluser
     * @param newpassword
     * @param flagforcechangepwd
     * @return
     * @throws RemoteException
     */
    @Override
    public int changePasswordWithFlagForce(String idEntity, String userid, String idChannel, String idChanneluser,
            String newpassword, String flagforcechangepwd) throws RemoteException {
        try {
            LOGGER.info("changePasswordWithFlagForce --> [IN] --> idEntity = [" + idEntity + "], userid = [" + userid + "], idChannel = [" + idChannel + "], idChanneluser = [" + idChanneluser + "], newpassword = [" + newpassword + "], flagforcechangepwd = [" + flagforcechangepwd + "]");
            // Ma hoa password
            String newpassword_encrypted = new PasswordDigest().getPasswordDigest(idEntity, userid, idChannel,
                    newpassword);

            FcdbBO fcdbBO = new FcdbBO();
            int result = fcdbBO.changePasswordWithFlagForce(idEntity, userid, idChannel, idChanneluser,
                    newpassword_encrypted, flagforcechangepwd);
            LOGGER.info("changePasswordWithFlagForce --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idEntity
     * @param idChannel
     * @param idUser
     * @param passInput
     * @param passData
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean checkAuthenPass(String idEntity, String idChannel, String idUser, String passInput, String passData)
            throws RemoteException {
        try {
            LOGGER.info("generatePassword --> [IN] --> idEntity = [" + idEntity + "], idChannel = [" + idChannel + "], idUser = [" + idUser + "], passInput = [" + passInput + "], passData = [" + passData + "]");
            boolean result = new PasswordDigest().checkAuthenPass(idEntity, idChannel, idUser, passInput, passData);
            LOGGER.info("generatePassword --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param identity
     * @param idchannel
     * @param iduser
     * @param usertype
     * @param idtxn
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getRoleTxnByTxnCode(String identity, String idchannel, String iduser, String usertype, String idtxn)
            throws RemoteException {
        try {
            LOGGER.info("getRoleTxnByTxnCode --> [IN] --> identity = [" + identity + "], idchannel = [" + idchannel + "], iduser = [" + iduser + "], usertype = [" + usertype + "], idtxn = [" + idtxn + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getRoleTxnByTxnCode(identity, idchannel, iduser, usertype, idtxn);
            LOGGER.info("getRoleTxnByTxnCode --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param identity
     * @param idchannel
     * @param iduser
     * @param usertype
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAllRoleTxn(String identity, String idchannel, String iduser, String usertype)
            throws RemoteException {
        try {
            LOGGER.info("getAllRoleTxn --> [IN] --> identity = [" + identity + "], idchannel = [" + idchannel + "], iduser = [" + iduser + "], usertype = [" + usertype + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getAllRoleTxn(identity, idchannel, iduser, usertype);
            LOGGER.info("getAllRoleTxn --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idcustomer
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCustomerInfoFCC(String idcustomer) throws RemoteException {
        try {
            LOGGER.info("getCustomerInfoFCC --> [IN] --> idcustomer = [" + idcustomer + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            ArrayList result = fcdbBO.getCustomerInfoFCC(idcustomer);
            LOGGER.info("getCustomerInfoFCC --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList getUserAdmin() throws RemoteException {
        try {
            LOGGER.info("getUserAdmin --> [IN] --> input is empty");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getUserAdmin();
            LOGGER.info("getUserAdmin --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param phonenum
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getLastestUserByPhoneNum(String phonenum) throws RemoteException {
        try {
            LOGGER.info("getLastestUserByPhoneNum --> [IN] --> phonenum = [" + phonenum + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getLastestUserByPhoneNum(phonenum);
            LOGGER.info("getLastestUserByPhoneNum --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchannel
     * @param iduser
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList isHaveRoleFTByIduser(String idchannel, String iduser) throws RemoteException {
        try {
            LOGGER.info("isHaveRoleFTByIduser --> [IN] --> idchannel = [" + idchannel + "], iduser = [" + iduser + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.isHaveRoleFTByIduser(idchannel, iduser);
            LOGGER.info("isHaveRoleFTByIduser --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    // @Override public ArrayList<?> getListAccountTDByAccounts(ArrayList<?>
    // listAccountTd) throws RemoteException {
    // try {
    // FcdbBO fcdbBO = new FcdbBO();
    // return fcdbBO.getListAccountTDByAccounts(listAccountTd);
    // } catch (Exception ex) {
    // throw new RemoteException(ex.getMessage(), ex);
    // }
    // }
    /**
     *
     * @param account
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getCustInfoIBByAccount(String account) throws RemoteException {
        try {
            LOGGER.info("getCustInfoIBByAccount --> [IN] --> account = [" + account + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList<?> result = fcdbBO.getCustInfoIBByAccount(account);
            LOGGER.info("getCustInfoIBByAccount --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param phoneNumber
     * @param defaultAcc
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean updateOperativeAccount(String phoneNumber, String defaultAcc) throws RemoteException {
        try {
            LOGGER.info("updateOperativeAccount --> [IN] --> phoneNumber = [" + phoneNumber + "], defaultAcc = [" + defaultAcc + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            boolean result = BO.updateOperativeAccount(phoneNumber, defaultAcc);
            LOGGER.info("updateOperativeAccount --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new RemoteException(e.getMessage(), e);
        }
    }

    /**
     *
     * @param kyhan
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getTermDepositRate(String kyhan) throws RemoteException {
        try {
            LOGGER.info("getTermDepositRate --> [IN] --> kyhan = [" + kyhan + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList<?> result = fcdbBO.getTermDepositRate(kyhan);
            LOGGER.info("getTermDepositRate --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<?> getListBranch() throws RemoteException {
        try {
            LOGGER.info("getListBranch --> [IN] --> input is empty");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            ArrayList<?> result = fcdbBO.getListBranch();
            LOGGER.info("getListBranch --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param account
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAccountBranch(String account) throws RemoteException {
        try {
            LOGGER.info("getAccountBranch --> [IN] --> account = [" + account + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getAccountBranch(account);
            LOGGER.info("getAccountBranch --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ====== EXTEND SCB BANKING====== */
    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList getListMstTxn() throws RemoteException {
        try {
            LOGGER.info("getListMstTxn --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getListMstTxn();
            LOGGER.info("getListMstTxn --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id_entity
     * @param usertype
     * @param idchannel
     * @param description
     * @param isdefault
     * @param createdby
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertMstRole(String id_entity, String usertype, String idchannel, String description, String isdefault,
            String createdby) throws RemoteException {
        try {
            LOGGER.info("insertMstRole --> [IN] --> id_entity = [" + id_entity + "], usertype = [" + usertype + "], idchannel = [" + idchannel + "], description = [" + description + "], isdefault = [" + isdefault + "], createdby = [" + createdby + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertMstRole(id_entity, usertype, idchannel, description, isdefault, createdby);
            LOGGER.info("insertMstRole --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id_entity
     * @param usertype
     * @param idchannel
     * @param idrole
     * @return
     * @throws RemoteException
     */
    @Override
    public int deleteMstRole(String id_entity, String usertype, String idchannel, String idrole) throws RemoteException {
        try {
            LOGGER.info("deleteMstRole --> [IN] --> id_entity = [" + id_entity + "], usertype = [" + usertype + "], idchannel = [" + idchannel + "], idrole = [" + idrole + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.deleteMstRole(id_entity, usertype, idchannel, idrole);
            LOGGER.info("deleteMstRole --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idrole
     * @return
     * @throws RemoteException
     */
    @Override
    public int deleteRoleTxn(String idrole) throws RemoteException {
        try {
            LOGGER.info("deleteRoleTxn --> [IN] --> idrole = [" + idrole + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.deleteRoleTxn(idrole);
            LOGGER.info("deleteRoleTxn --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idrole
     * @param idtxn
     * @param flginit
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertRoleTxn(String idrole, String idtxn, String flginit) throws RemoteException {
        try {
            LOGGER.info("insertRoleTxn --> [IN] --> idrole = [" + idrole + "], idtxn = [" + idtxn + "], flginit = [" + flginit + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertRoleTxn(idrole, idtxn, flginit);
            LOGGER.info("insertRoleTxn --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param usertype
     * @param description
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchMstRole(String usertype, String description) throws RemoteException {
        try {
            LOGGER.info("searchMstRole --> [IN] --> usertype = [" + usertype + "], description = [" + description + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchMstRole(usertype, description);
            LOGGER.info("searchMstRole --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idrole
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchRoleTxn(String idrole) throws RemoteException {
        try {
            LOGGER.info("searchRoleTxn --> [IN] --> idrole = [" + idrole + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchRoleTxn(idrole);
            LOGGER.info("searchRoleTxn --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param mstuser
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertMstUser(EtsMstuser mstuser) throws RemoteException {
        try {
            LOGGER.info("insertMstUser --> [IN] --> mstuser_iduser = [" + mstuser.getIduser() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertMstUser(mstuser);
            LOGGER.info("insertMstUser --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id_entity
     * @param iduser
     * @param idchanneluser
     * @param idchannel
     * @param password
     * @param lockflag
     * @param flagforcechanngepwd
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertChannelUserEBES(String id_entity, String iduser, String idchanneluser, String idchannel,
            String password, String lockflag, String flagforcechanngepwd) throws RemoteException {
        try {
            LOGGER.info("insertChannelUserEBES --> [IN] --> id_entity = [" + id_entity + "], iduser = [" + iduser + "], idchanneluser = [" + idchanneluser + "], idchannel = [" + idchannel + "], password = [" + password + "], lockflag = [" + lockflag + "], flagforcechanngepwd = [" + flagforcechanngepwd + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertChannelUser(id_entity, iduser, idchanneluser, idchannel, password, lockflag,
                    flagforcechanngepwd);
            LOGGER.info("insertChannelUserEBES --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id_entity
     * @param iduser
     * @param idchannel
     * @param idrole
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertUserRole(String id_entity, String iduser, String idchannel, String idrole) throws RemoteException {
        try {
            LOGGER.info("insertUserRole --> [IN] --> id_entity = [" + id_entity + "], iduser = [" + iduser + "], idchannel = [" + idchannel + "], idrole = [" + idrole + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertUserRole(id_entity, iduser, idchannel, idrole);
            LOGGER.info("insertUserRole --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchanneluser
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getUserByIdChannelUser(String idchanneluser) throws RemoteException {
        try {
            LOGGER.info("getUserByIdChannelUser --> [IN] --> idchanneluser = [" + idchanneluser + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getUserByIdChannelUser(idchanneluser);
            LOGGER.info("getUserByIdChannelUser --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accounttd
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> searchCustAlertTd(String accounttd) throws RemoteException {
        try {
            LOGGER.info("searchCustAlertTd --> [IN] --> accounttd = [" + accounttd + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.searchCustAlertTd(accounttd);
            LOGGER.info("searchCustAlertTd --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertAlertAccountTd(SmsCustAlertTd smscustalerttd) throws RemoteException {
        try {
            LOGGER.info("insertAlertAccountTd --> [IN] --> smscustalerttd_id = [" + smscustalerttd.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertAlertAccountTd(smscustalerttd);
            LOGGER.info("insertAlertAccountTd --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accounttd
     * @param cif
     * @param idcard
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> findListAccountTD(String accounttd, String cif, String idcard) throws RemoteException {
        try {
            LOGGER.info("findListAccountTD --> [IN] --> accounttd = [" + accounttd + "], cif = [" + cif + "], idcard = [" + idcard + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.findListAccountTD(accounttd, cif, idcard);
            LOGGER.info("findListAccountTD --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accounttd
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getAccountTDByAccountTd(String accounttd) throws RemoteException {
        try {
            LOGGER.info("getAccountTDByAccountTd --> [IN] --> accounttd = [" + accounttd + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getAccountTDByAccountTd(accounttd);
            LOGGER.info("getAccountTDByAccountTd --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id_entity
     * @param iduser
     * @param idchanneluser
     * @param idchannel
     * @return
     * @throws RemoteException
     */
    @Override
    public int deleteIdChanneluser_UserPin2(String id_entity, String iduser, String idchanneluser, String idchannel)
            throws RemoteException {
        try {
            LOGGER.info("deleteIdChanneluser_UserPin2 --> [IN] --> id_entity = [" + id_entity + "], iduser = [" + iduser + "], idchanneluser = [" + idchanneluser + "], idchannel = [" + idchannel + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.deleteIdChanneluser_UserPin2(id_entity, iduser, idchanneluser, idchannel);
            LOGGER.info("deleteIdChanneluser_UserPin2 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getAccountTDById(SmsCustAlertTd smscustalerttd) throws RemoteException {
        try {
            LOGGER.info("getAccountTDById --> [IN] --> smscustalerttd_id = [" + smscustalerttd.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getAccountTDById(smscustalerttd);
            LOGGER.info("getAccountTDById --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateAccountTDMobileById(SmsCustAlertTd smscustalerttd) throws RemoteException {
        try {
            LOGGER.info("updateAccountTDMobileById --> [IN] --> smscustalerttd_id = [" + smscustalerttd.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateAccountTDMobileById(smscustalerttd);
            LOGGER.info("updateAccountTDMobileById --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cust_no
     * @param registertype
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> findHistoryListAccountTD(String cust_no, String registertype) throws RemoteException {
        try {
            LOGGER.info("findHistoryListAccountTD --> [IN] --> cust_no = [" + cust_no + "], registertype = [" + registertype + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.findHistoryListAccountTD(cust_no, registertype);
            LOGGER.info("findHistoryListAccountTD --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cust_no
     * @param registertype
     * @param idcard
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> findHistoryListAccountTD(String cust_no, String registertype, String idcard) throws RemoteException {
        try {
            LOGGER.info("findHistoryListAccountTD --> [IN] --> cust_no = [" + cust_no + "], registertype = [" + registertype + "], idcard = [" + idcard + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.findHistoryListAccountTD(cust_no, registertype, idcard);
            LOGGER.info("findHistoryListAccountTD --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param mstchanneluser
     * @return
     * @throws RemoteException
     */
    @Override
    public int changePasswordEBES(EtsMstchanneluser mstchanneluser) throws RemoteException {
        try {
            LOGGER.info("changePasswordEBES --> [IN] --> smscustalerttd_iduser = [" + mstchanneluser.getIduser() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.changePasswordEBES(mstchanneluser);
            LOGGER.info("changePasswordEBES --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idalert
     * @param mobile
     * @param message
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertSmsSenderLog(String idalert, String mobile, String message) throws RemoteException {
        try {
            LOGGER.info("insertSmsSenderLog --> [IN] --> idalert = [" + idalert + "], mobile = [" + mobile + "], message = [" + message + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertSmsSenderLog(idalert, mobile, message);
            LOGGER.info("insertSmsSenderLog --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idservicetype
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getProviderByIdServiceType(String idservicetype) throws RemoteException {
        try {
            LOGGER.info("getProviderByIdServiceType --> [IN] --> idservicetype = [" + idservicetype + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getProviderByIdServiceType(idservicetype);
            LOGGER.info("getProviderByIdServiceType --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    // ============ EXTEND --> SMS ===============
    /**
     *
     * @param smsuserpin
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertUserPin2(Sms_UserPin2 smsuserpin) throws RemoteException {
        try {
            LOGGER.info("insertUserPin2 --> [IN] --> smsuserpin_iduser = [" + smsuserpin.getIduser() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertUserPin2(smsuserpin);
            LOGGER.info("insertUserPin2 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getUserPin2(String idchanneluser, String usertype) throws RemoteException {
        try {
            LOGGER.info("getUserPin2 --> [IN] --> idchanneluser = [" + idchanneluser + "], usertype = [" + usertype + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getUserPin2(idchanneluser, usertype);
            LOGGER.info("getUserPin2 --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @return
     * @throws RemoteException
     */
    @Override
    public int updAccessSucc_UserPin2(String idchanneluser, String usertype) throws RemoteException {
        try {
            LOGGER.info("updAccessSucc_UserPin2 --> [IN] --> idchanneluser = [" + idchanneluser + "], usertype = [" + usertype + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updAccessSucc_UserPin2(idchanneluser, usertype);
            LOGGER.info("updAccessSucc_UserPin2 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @return
     * @throws RemoteException
     */
    @Override
    public int updAccessFail_UserPin2(String idchanneluser, String usertype) throws RemoteException {
        try {
            LOGGER.info("updAccessFail_UserPin2 --> [IN] --> idchanneluser = [" + idchanneluser + "], usertype = [" + usertype + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updAccessFail_UserPin2(idchanneluser, usertype);
            LOGGER.info("updAccessFail_UserPin2 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchanneluser
     * @param usertype
     * @param pwdtxn
     * @param lenpwd
     * @param flagforcechangepwdtxn
     * @return
     * @throws RemoteException
     */
    @Override
    public int changePwd_UserPin2(String idchanneluser, String usertype, String pwdtxn, int lenpwd,
            String flagforcechangepwdtxn) throws RemoteException {
        try {
            LOGGER.info("changePwd_UserPin2 --> [IN] --> idchanneluser = [" + idchanneluser + "], usertype = [" + usertype + "], pwdtxn = [" + pwdtxn + "], lenpwd = [" + lenpwd + "], flagforcechangepwdtxn = [" + flagforcechangepwdtxn + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.changePwd_UserPin2(idchanneluser, usertype, pwdtxn, lenpwd, flagforcechangepwdtxn);
            LOGGER.info("changePwd_UserPin2 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchanneluserOld
     * @param idchanneluserNew
     * @param usertype
     * @return
     * @throws RemoteException
     */
    @Override
    public int changeIdChanneluser_UserPin2(String idchanneluserOld, String idchanneluserNew, String usertype)
            throws RemoteException {
        try {
            LOGGER.info("changeIdChanneluser_UserPin2 --> [IN] --> idchanneluserOld = [" + idchanneluserOld + "], idchanneluserNew = [" + idchanneluserNew + "], usertype = [" + usertype + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.changeIdChanneluser_UserPin2(idchanneluserOld, idchanneluserNew, usertype);
            LOGGER.info("changeIdChanneluser_UserPin2 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id_entity
     * @param idchannel
     * @param usertype
     * @param idchanneluser
     * @param typecmd
     * @param smsmessage
     * @param demonstring
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertWaitResponse(String id_entity, String idchannel, String usertype, String idchanneluser,
            String typecmd, String smsmessage, String demonstring) throws RemoteException {
        try {
            LOGGER.info("insertWaitResponse --> [IN] --> id_entity = [" + id_entity + "], idchannel = [" + idchannel + "], usertype = [" + usertype + "], idchanneluser = [" + idchanneluser + "], typecmd = [" + typecmd + "], smsmessage = [" + smsmessage + "], demonstring = [" + demonstring + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertWaitResponse(id_entity, idchannel, usertype, idchanneluser, typecmd, smsmessage,
                    demonstring);
            LOGGER.info("insertWaitResponse --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id_entity
     * @param idchannel
     * @param usertype
     * @param idchanneluser
     * @param typecmd
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAllListWaitResponse(String id_entity, String idchannel, String usertype, String idchanneluser,
            String typecmd) throws RemoteException {
        try {
            LOGGER.info("getAllListWaitResponse --> [IN] --> id_entity = [" + id_entity + "], idchannel = [" + idchannel + "], usertype = [" + usertype + "], idchanneluser = [" + idchanneluser + "], typecmd = [" + typecmd + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getAllListWaitResponse(id_entity, idchannel, usertype, idchanneluser, typecmd);
            LOGGER.info("getAllListWaitResponse --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param matinhthanh
     * @param quan
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAllListATM(String matinhthanh, String quan) throws RemoteException {
        try {
            LOGGER.info("getAllListATM --> [IN] --> matinhthanh = [" + matinhthanh + "], quan = [" + quan + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getAllListATM(matinhthanh, quan);
            LOGGER.info("getAllListATM --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param matinhthanh
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAllListATM_ext(String matinhthanh) throws RemoteException {
        try {
            LOGGER.info("getAllListATM_ext --> [IN] --> matinhthanh = [" + matinhthanh + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getAllListATM_ext(matinhthanh);
            LOGGER.info("getAllListATM_ext --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param matinhthanh
     * @param quan
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAllListBranch_main(String matinhthanh, String quan) throws RemoteException {
        try {
            LOGGER.info("getAllListBranch_main --> [IN] --> matinhthanh = [" + matinhthanh + "], quan = [" + quan + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getAllListBranch_main(matinhthanh, quan);
            LOGGER.info("getAllListBranch_main --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param matinhthanh
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAllListBranch_ext(String matinhthanh) throws RemoteException {
        try {
            LOGGER.info("getAllListBranch_ext --> [IN] --> matinhthanh = [" + matinhthanh + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getAllListBranch_ext(matinhthanh);
            LOGGER.info("getAllListBranch_ext --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList getAllListNHLM() throws RemoteException {
        try {
            LOGGER.info("getAllListNHLM --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getAllListNHLM();
            LOGGER.info("getAllListNHLM --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchanneluser
     * @param typeuser
     * @param result
     * @param msglog
     * @param idmarker
     * @return
     * @throws RemoteException
     */
    @Override
    public int insSmsLog(String idchanneluser, String typeuser, String result, String msglog, String idmarker)
            throws RemoteException {
        try {
            LOGGER.info("insSmsLog --> [IN] --> idchanneluser = [" + idchanneluser + "], typeuser = [" + typeuser + "], result = [" + result + "], msglog = [" + msglog + "], idmarker = [" + idmarker + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int res = smsscbBO.insSmsLog(idchanneluser, typeuser, result, msglog, idmarker);
            LOGGER.info("insSmsLog --> [OUT] --> res = [" + res + "]");
            return res;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList getSmsAlertNotify() throws RemoteException {
        try {
            LOGGER.info("getSmsAlertNotify --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getSmsAlertNotify();
            LOGGER.info("getSmsAlertNotify --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idmessage
     * @param status
     * @param msgnew
     * @return
     * @throws RemoteException
     */
    @Override
    public int updSmsAlertNotifyByIdMessage(String idmessage, String status, String msgnew) throws RemoteException {
        try {
            LOGGER.info("updSmsAlertNotifyByIdMessage --> [IN] --> idmessage = [" + idmessage + "], status = [" + status + "], msgnew = [" + msgnew + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updSmsAlertNotifyByIdMessage(idmessage, status, msgnew);
            LOGGER.info("updSmsAlertNotifyByIdMessage --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchanneluser
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getChannelUserByIdChannelUser(String idchanneluser) throws RemoteException {
        try {
            LOGGER.info("getChannelUserByIdChannelUser --> [IN] --> idchanneluser = [" + idchanneluser + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getChannelUserByIdChannelUser(idchanneluser);
            LOGGER.info("getChannelUserByIdChannelUser --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param iduser
     * @param id_entity
     * @param idchannel
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getListUserRoleByIduser(String iduser, String id_entity, String idchannel) throws RemoteException {
        try {
            LOGGER.info("getListUserRoleByIduser --> [IN] --> iduser = [" + iduser + "], id_entity = [" + id_entity + "], idchannel = [" + idchannel + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getListUserRoleByIduser(iduser, id_entity, idchannel);
            LOGGER.info("getListUserRoleByIduser --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchanneluser
     * @param firstname
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchUser(String idchanneluser, String firstname) throws RemoteException {
        try {
            LOGGER.info("searchUser --> [IN] --> idchanneluser = [" + idchanneluser + "], firstname = [" + firstname + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchUser(idchanneluser, firstname);
            LOGGER.info("searchUser --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     * **************** PAYBILL
     *
     *******************
     * @return
     * @throws java.rmi.RemoteException
     */
    @Override
    public ArrayList getAllListServiceType() throws RemoteException {
        LOGGER.info("getAllListServiceType --> [IN] --> input is empty");
        if (Configuration.listServiceType == null) {
            try {
                SmsSCBBO smsscbBO = new SmsSCBBO();
                Configuration.listServiceType = smsscbBO.getAllListServiceType();
            } catch (Exception ex) {
                LOGGER.error(ex);
                throw new RemoteException(ex.getMessage(), ex);
            }
        }

        ArrayList result = Configuration.listServiceType;
        LOGGER.info("getAllListServiceType --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
        return result;
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<?> getAllListServiceTypeOfAutoPay() throws RemoteException {
        LOGGER.info("getAllListServiceTypeOfAutoPay --> [IN] --> input is empty");
        if (Configuration.listServiceTypeAutoPay == null) {
            try {
                SmsSCBBO smsscbBO = new SmsSCBBO();
                return smsscbBO.getAllListServiceTypeOfAutoPay();
            } catch (Exception ex) {
                LOGGER.error(ex);
                throw new RemoteException(ex.getMessage(), ex);
            }
        }

        ArrayList result = Configuration.listServiceTypeAutoPay;
        LOGGER.info("getAllListServiceTypeOfAutoPay --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
        return result;
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList getAllListProvider() throws RemoteException {
        try {
            LOGGER.info("getAllListProvider --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getAllListProvider();
            LOGGER.info("getAllListProvider --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idservicetype
     * @param idprovider
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getPartnerServiceByPs(String idservicetype, String idprovider) throws RemoteException {
        try {
            LOGGER.info("getPartnerServiceByPs --> [IN] --> idservicetype = [" + idservicetype + "], idprovider = [" + idprovider + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getPartnerServiceByPs(idservicetype, idprovider);
            LOGGER.info("getPartnerServiceByPs --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ppsid
     * @return
     * @throws RemoteException
     */
    @Override
    public PblPartnerservice getPartnerServiceById(PblPartnerserviceId ppsid) throws RemoteException {
        try {
            LOGGER.info("getPartnerServiceById --> [IN] --> ppsid_idpartner = [" + ppsid.getIdpartner() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            PblPartnerservice result = smsscbBO.getPartnerServiceById(ppsid);
            LOGGER.info("getPartnerServiceById --> [OUT] --> result_id = [" + (result == null ? null : result.getId()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList getAllListPartnerService() throws RemoteException {
        try {
            LOGGER.info("getAllListPartnerService --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getAllListPartnerService();
            LOGGER.info("getAllListPartnerService --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idprovider
     * @param idservicetype
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getPartnerService(String idprovider, String idservicetype) throws RemoteException {
        try {
            LOGGER.info("getPartnerService --> [IN] --> idprovider = [" + idprovider + "], idservicetype = [" + idservicetype + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getPartnerService(idprovider, idservicetype);
            LOGGER.info("getPartnerService --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idpartner
     * @param idservicetype
     * @param idprovider
     * @param channel
     * @param result
     * @param msglog
     * @param idmarker
     * @param description
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertPaybillLogDB(String idpartner, String idservicetype, String idprovider, String channel, String result, String msglog,
            String idmarker, String description) throws RemoteException {
        try {
            LOGGER.info("insertPaybillLogDB --> [IN] --> idpartner = [" + idpartner + "], idservicetype = [" + idservicetype + "], idprovider = [" + idprovider + "], channel = [" + channel + "], result = [" + result + "], msglog = [" + msglog + "], idmarker = [" + idmarker + "], description = [" + description + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int res = smsscbBO.insertPaybillLogDB(idpartner, idservicetype, idprovider, channel, result, msglog, idmarker, description);
            LOGGER.info("insertPaybillLogDB --> [OUT] --> res = [" + res + "]");
            return res;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pb
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertPaybillBillPaid(PblBillpaid pb) throws RemoteException {
        try {
            LOGGER.info("insertPaybillBillPaid --> [IN] --> pb_idbillpaid = [" + pb.getIdbillpaid() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertPaybillBillPaid(pb);
            LOGGER.info("insertPaybillBillPaid --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pb
     * @throws RemoteException
     */
    @Override
    public void updatePaybillBillPaid(PblBillpaid pb) throws RemoteException {
        try {
            LOGGER.info("updatePaybillBillPaid --> [IN] --> pb_idbillpaid = [" + pb.getIdbillpaid() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updatePaybillBillPaid(pb);
            LOGGER.info("updatePaybillBillPaid --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //duytxa08072015
    /**
     *
     * @param pb
     * @throws RemoteException
     */
    @Override
    public void updateSmsThuPhi(SmsThuphi pb) throws RemoteException {
        try {
            LOGGER.info("updateSmsThuPhi --> [IN] --> pb_id = [" + pb.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updateSmsThuPhi(pb);
            LOGGER.info("updateSmsThuPhi --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int smsThuPhiDetails(SmsThuphi pb, SqlCommand action) throws RemoteException {
        try {
            LOGGER.info("smsThuPhiDetails --> [IN] --> pb_id = [" + pb.getId() + "], action = [" + action + "]");
            OnlinePaymentBO onlinePaymentBO = new OnlinePaymentBO();
            int result = onlinePaymentBO.smsThuPhiDetails(pb, action);
            LOGGER.info("smsThuPhiDetails --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    //endduytxa08072015

    /**
     *
     * @param idbillpaid
     * @return
     * @throws RemoteException
     */
    @Override
    public int deletePaybillBillPaid(String idbillpaid) throws RemoteException {
        try {
            LOGGER.info("deletePaybillBillPaid --> [IN] --> idbillpaid = [" + idbillpaid + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.deletePaybillBillPaid(idbillpaid);
            LOGGER.info("deletePaybillBillPaid --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idbillpaid
     * @param idbill
     * @param idcustomer
     * @param custname
     * @param address
     * @param period
     * @param amount
     * @param result
     * @param description
     * @param tracenumber
     * @param markerid
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertPaybillBillPaidDetail(int idbillpaid, String idbill, String idcustomer, String custname,
            String address, String period, long amount, int result, String description, String tracenumber,
            String markerid) throws RemoteException {
        try {
            LOGGER.info("insertPaybillBillPaidDetail --> [IN] --> idbillpaid = [" + idbillpaid + "], idbill = [" + idbill
                    + "], idcustomer = [" + idcustomer + "], custname = [" + custname + "], address = [" + address + "], period = [" + period
                    + "], amount = [" + amount + "], result = [" + result + "], description = [" + description + "], tracenumber = ["
                    + tracenumber + "], markerid = [" + markerid + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int res = smsscbBO.insertPaybillBillPaidDetail(idbillpaid, idbill, idcustomer, custname, address, period,
                    amount, result, description, tracenumber, markerid);
            LOGGER.info("insertPaybillBillPaidDetail --> [OUT] --> res = [" + res + "]");
            return res;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idpartner
     * @param idservicetype
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getPaybillAttributes(String idpartner, String idservicetype) throws RemoteException {
        try {
            LOGGER.info("getPaybillAttributes --> [IN] --> idpartner = [" + idpartner + "], idservicetype = [" + idservicetype + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getPaybillAttributes(idpartner, idservicetype);
            LOGGER.info("getPaybillAttributes --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idbillpaid
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getPaybillBillPaid(String idbillpaid) throws RemoteException {
        try {
            LOGGER.info("getPaybillBillPaid --> [IN] --> idbillpaid = [" + idbillpaid + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getPaybillBillPaid(idbillpaid);
            LOGGER.info("getPaybillBillPaid --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getPaybillInfoById(String id) throws RemoteException {
        try {
            LOGGER.info("getPaybillInfoById --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getPaybillInfoById(id);
            LOGGER.info("getPaybillInfoById --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public PblBillpaid getPaybillBillPaidById(int id) throws RemoteException {
        try {
            LOGGER.info("getPaybillBillPaidById --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            PblBillpaid result = smsscbBO.getPaybillBillPaidById(id);
            LOGGER.info("getPaybillBillPaidById --> [OUT] --> result_idbillpaid = [" + (result == null ? null : result.getIdbillpaid()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idbillpaid
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getPaybillBillPaidDetail(String idbillpaid) throws RemoteException {
        try {
            LOGGER.info("getPaybillBillPaidDetail --> [IN] --> idbillpaid = [" + idbillpaid + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getPaybillBillPaidDetail(idbillpaid);
            LOGGER.info("getPaybillBillPaidDetail --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int updRefFcubsByIdbillpaid(String ref_fcubs, String idbillpaid) throws RemoteException {
        try {
            LOGGER.info("updRefFcubsByIdbillpaid --> [IN] --> ref_fcubs = [" + ref_fcubs + "], idbillpaid = [" + idbillpaid + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updRefFcubsByIdbillpaid(ref_fcubs, idbillpaid);
            LOGGER.info("updRefFcubsByIdbillpaid --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ============ BAT DAU AUTOREG ============ */
    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public int getIdSeqAutoRegContract() throws RemoteException {
        try {
            LOGGER.info("getIdSeqAutoRegContract --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.getIdSeqAutoRegContract();
            LOGGER.info("getIdSeqAutoRegContract --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param parc
     * @return
     * @throws RemoteException
     */
    @Override
    public String insAutoRegContract(PblAutoRegContract parc) throws RemoteException {
        try {
            LOGGER.info("insAutoRegContract --> [IN] --> parc_idcontract = [" + parc.getIdcontract() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = (String) smsscbBO.create(parc);
            LOGGER.info("insAutoRegContract --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param parc
     * @throws RemoteException
     */
    @Override
    public void updAutoRegContract(PblAutoRegContract parc) throws RemoteException {
        try {
            LOGGER.info("updAutoRegContract --> [IN] --> parc_idcontract = [" + parc.getIdcontract() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.update(parc);
            LOGGER.info("updAutoRegContract --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param parc
     * @throws RemoteException
     */
    @Override
    public void delAutoRegContract(PblAutoRegContract parc) throws RemoteException {
        try {
            LOGGER.info("delAutoRegContract --> [IN] --> parc_idcontract = [" + parc.getIdcontract() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.delete(parc);
            LOGGER.info("delAutoRegContract --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    @Override
    public int insAutoReg(PblAutoReg pblautoreg) throws RemoteException {
        try {
            LOGGER.info("insAutoReg --> [IN] --> pblautoreg_id = [" + pblautoreg.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = (Integer) smsscbBO.create(pblautoreg);
            LOGGER.info("insAutoReg --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    @Override
    public int updAutoReg(PblAutoReg pblautoreg) throws RemoteException {
        try {
            LOGGER.info("updAutoReg --> [IN] --> pblautoreg_id = [" + pblautoreg.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.update(pblautoreg);
            int result = 1;
            LOGGER.info("updAutoReg --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    @Override
    public int delAutoReg(PblAutoReg pblautoreg) throws RemoteException {
        try {
            LOGGER.info("delAutoReg --> [IN] --> pblautoreg_id = [" + pblautoreg.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.delete(pblautoreg);
            int result = 1;
            LOGGER.info("delAutoReg --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param parc
     * @return
     * @throws RemoteException
     */
    @Override
    public String insAutoRegContractTemp(PblAutoRegContract parc) throws RemoteException {
        try {
            LOGGER.info("insAutoRegContractTemp --> [IN] --> parc_idcontract = [" + parc.getIdcontract() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            BPblAutoRegContract bparc = HibernateUtil.getMapper().map(parc, BPblAutoRegContract.class);
            String result = (String) smsscbBO.create(bparc);
            LOGGER.info("insAutoRegContractTemp --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //@Override
    /**
     *
     * @param parc
     * @throws RemoteException
     */
    public void updAutoRegContractTemp(BPblAutoRegContract parc) throws RemoteException {
        try {
            LOGGER.info("updAutoRegContractTemp --> [IN] --> parc_idcontract = [" + parc.getIdcontract() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.update(parc);
            LOGGER.info("updAutoRegContractTemp --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param bparc
     * @throws RemoteException
     */
    @Override
    public void delAutoRegContractTemp(BPblAutoRegContract bparc) throws RemoteException {
        try {
            LOGGER.info("delAutoRegContractTemp --> [IN] --> bparc_idcontract = [" + bparc.getIdcontract() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.delete(bparc);
            LOGGER.info("delAutoRegContractTemp --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    @Override
    public int insAutoRegTemp(PblAutoReg pblautoreg) throws RemoteException {
        try {
            LOGGER.info("insAutoRegTemp --> [IN] --> pblautoreg_id = [" + pblautoreg.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();

            BPblAutoRegContract bparc = new BPblAutoRegContract();
            bparc.setIdcontract(pblautoreg.getPblAutoRegContract().getIdcontract());

            BPblAutoReg bpar = HibernateUtil.getMapper().map(pblautoreg, BPblAutoReg.class);
            bpar.setBPblAutoRegContract(bparc);

            int result = (Integer) smsscbBO.create(bpar);
            LOGGER.info("insAutoRegTemp --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    @Override
    public int updAutoRegTemp(BPblAutoReg pblautoreg) throws RemoteException {
        try {
            LOGGER.info("updAutoRegTemp --> [IN] --> pblautoreg_id = [" + pblautoreg.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.update(pblautoreg);
            int result = 1;
            LOGGER.info("updAutoRegTemp --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    @Override
    public int delAutoRegTemp(BPblAutoReg pblautoreg) throws RemoteException {
        try {
            LOGGER.info("delAutoRegTemp --> [IN] --> pblautoreg_id = [" + pblautoreg.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.delete(pblautoreg);
            int result = 1;
            LOGGER.info("delAutoRegTemp --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idcontract
     * @return
     * @throws RemoteException
     */
    @Override
    public int execApproveAutoReg(String idcontract) throws RemoteException {
        try {
            LOGGER.info("execApproveAutoReg --> [IN] --> idcontract = [" + idcontract + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.execApproveAutoReg(idcontract);
            LOGGER.info("execApproveAutoReg --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pblautoreghist
     * @return
     * @throws RemoteException
     */
    @Override
    public int insAutoRegHistory(PblAutoRegHistory pblautoreghist) throws RemoteException {
        try {
            LOGGER.info("insAutoRegHistory --> [IN] --> pblautoreghist_idcontract = [" + pblautoreghist.getIdcontract() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = (Integer) smsscbBO.create(pblautoreghist);
            LOGGER.info("insAutoRegHistory --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idcontract
     * @return
     * @throws RemoteException
     */
    @Override
    public PblAutoRegContract getAutoRegContractByIdContract(String idcontract) throws RemoteException {
        try {
            LOGGER.info("getAutoRegContractByIdContract --> [IN] --> idcontract = [" + idcontract + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            PblAutoRegContract result = (PblAutoRegContract) smsscbBO.findById(PblAutoRegContract.class, idcontract);
            LOGGER.info("getAutoRegContractByIdContract --> [OUT] --> result_idcontract = [" + (result == null ? null : result.getIdcontract()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idcontract
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean isExsistContractByIdContract(String idcontract) throws RemoteException {
        try {
            LOGGER.info("isExsistContractByIdContract --> [IN] --> idcontract = [" + idcontract + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            boolean result = smsscbBO.isExsistContractByIdContract(idcontract);
            LOGGER.info("isExsistContractByIdContract --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idcontract
     * @return
     * @throws RemoteException
     */
    @Override
    public BPblAutoRegContract getAutoRegContractByIdContractTemp(String idcontract) throws RemoteException {
        try {
            LOGGER.info("getAutoRegContractByIdContractTemp --> [IN] --> idcontract = [" + idcontract + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            BPblAutoRegContract result = (BPblAutoRegContract) smsscbBO.findById(BPblAutoRegContract.class, idcontract);
            LOGGER.info("getAutoRegContractByIdContractTemp --> [OUT] --> result_idcontract = [" + (result == null ? null : result.getIdcontract()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pblautoreg
     * @return
     * @throws RemoteException
     */
    @Override
    public List searchAutoReg(PblAutoReg pblautoreg) throws RemoteException {
        try {
            LOGGER.info("searchAutoReg --> [IN] --> pblautoreg_id = [" + pblautoreg.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.searchAutoReg(pblautoreg);
            LOGGER.info("searchAutoReg --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param vwautoreg
     * @return
     * @throws RemoteException
     */
    @Override
    public List searchAutoReg(VwAutoReg vwautoreg) throws RemoteException {
        try {
            LOGGER.info("searchAutoReg --> [IN] --> vwautoreg_id = [" + vwautoreg.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.searchAutoReg(vwautoreg);
            LOGGER.info("searchAutoReg --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public PblAutoReg searchAutoRegById(int id) throws RemoteException {
        try {
            LOGGER.info("searchAutoRegById --> [IN] --> id = [" + id + "]");
            PblAutoReg result = (new SmsSCBBO()).searchAutoRegById(id);
            LOGGER.info("searchAutoRegById --> [OUT] --> result_id = [" + (result == null ? null : result.getId()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idcontract
     * @return
     * @throws RemoteException
     */
    @Override
    public PblAutoRegContract searchAutoRegContractById(String idcontract) throws RemoteException {
        try {
            LOGGER.info("searchAutoRegContractById --> [IN] --> idcontract = [" + idcontract + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            PblAutoRegContract result = (PblAutoRegContract) smsscbBO.findById(PblAutoRegContract.class, idcontract);
            LOGGER.info("searchAutoRegContractById --> [OUT] --> result_idcontract = [" + (result == null ? null : result.getIdcontract()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idcontract
     * @return
     * @throws RemoteException
     */
    @Override
    public BPblAutoRegContract searchAutoRegContractByIdTemp(String idcontract) throws RemoteException {
        try {
            LOGGER.info("searchAutoRegContractByIdTemp --> [IN] --> idcontract = [" + idcontract + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            BPblAutoRegContract result = (BPblAutoRegContract) smsscbBO.findById(BPblAutoRegContract.class, idcontract);
            LOGGER.info("searchAutoRegContractByIdTemp --> [OUT] --> result_idcontract = [" + (result == null ? null : result.getIdcontract()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ============ KET THUC AUTOREG ============ */
    /**
     *
     * @param branchcode
     * @return
     * @throws RemoteException
     */
    @Override
    public List getListTransActByBranchCode(String branchcode) throws RemoteException {
        try {
            LOGGER.info("getListTransActByBranchCode --> [IN] --> branchcode = [" + branchcode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.getListTransActByBranchCode(branchcode);
            LOGGER.info("getListTransActByBranchCode --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param transcode
     * @param branchcode
     * @return
     * @throws RemoteException
     */
    @Override
    public List getListTransActDetail(String transcode, String branchcode) throws RemoteException {
        try {
            LOGGER.info("getListTransActDetail --> [IN] --> transcode = [" + transcode + "], branchcode = [" + branchcode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.getListTransActDetail(transcode, branchcode);
            LOGGER.info("getListTransActDetail --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param date
     * @return
     * @throws RemoteException
     */
    @Override
    public List getListBillPaidByDate(String date) throws RemoteException {
        try {
            LOGGER.info("getListBillPaidByDate --> [IN] --> date = [" + date + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.getListBillPaidByDate(date);
            LOGGER.info("getListTransActDetail --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public List getListAutoRegToPay() throws RemoteException {
        try {
            LOGGER.info("getListAutoRegToPay --> [IN] --> input is empty");
            SmsSCBBO smsscbbo = new SmsSCBBO();
            smsscbbo.setPersistentClass(VwAutoregToPay.class);
            List result = smsscbbo.findAll();
            LOGGER.info("getListAutoRegToPay --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //duytxaduytxa08072015
    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public List getListAutoFeePay() throws RemoteException {
        try {
            LOGGER.info("getListAutoFeePay --> [IN] --> input is empty");
            SmsSCBBO smsscbbo = new SmsSCBBO();//neu connect fcdb dung :FcdbBO, gateway dung:SmsSCBBO
            smsscbbo.setPersistentClass(VwSmsThuphi.class);
            List result = smsscbbo.findAll();
            LOGGER.info("getListAutoFeePay --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    //endduytxaduytxa08072015

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public VwCustAccount getCustAccountByAccountNo(String accountno) throws RemoteException {
        try {
            LOGGER.info("getCustAccountByAccountNo --> [IN] --> accountno = [" + accountno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            VwCustAccount result = smsscbBO.getCustAccountByAccountNo(accountno);
            LOGGER.info("getCustAccountByAccountNo --> [OUT] --> result_custAcNo = [" + (result == null ? null : result.getCustAcNo()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public VwCustAccountNew getCustAccountByAccountNoNew(String accountno) throws RemoteException {
        try {
            LOGGER.info("getCustAccountByAccountNoNew --> [IN] --> accountno = [" + accountno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            VwCustAccountNew result = smsscbBO.getCustAccountByAccountNoNew(accountno);
            LOGGER.info("getCustAccountByAccountNoNew --> [OUT] --> result_custAcNo = [" + (result == null ? null : result.getCustNo()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public VwCustAccount getCustAccountFcdbByAccountNo(String accountno) throws RemoteException {
        try {
            LOGGER.info("getCustAccountFcdbByAccountNo --> [IN] --> accountno = [" + accountno + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            VwCustAccount result = fcdbBO.getCustAccountFcdbByAccountNo(accountno);
            LOGGER.info("getCustAccountFcdbByAccountNo --> [OUT] --> result_custAcNo = [" + (result == null ? null : result.getCustAcNo()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ============ IBS ============ */
    /**
     *
     * @param custid
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getIbsUserByCustId(String custid) throws RemoteException {
        try {
            LOGGER.info("getIbsUserByCustId --> [IN] --> custid = [" + custid + "]");
            IbsBO ibsbo = new IbsBO();
            ArrayList result = ibsbo.getIbsUserByCustId(custid);
            LOGGER.info("getIbsUserByCustId --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param isChangePwd
     * @param userid
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateIbsUserChangePwd(String isChangePwd, String userid) throws RemoteException {
        try {
            LOGGER.info("updateIbsUserChangePwd --> [IN] --> isChangePwd = [" + isChangePwd + "], userid = [" + userid + "]");
            IbsBO ibsbo = new IbsBO();
            int result = ibsbo.updateIbsUserChangePwd(isChangePwd, userid);
            LOGGER.info("updateIbsUserChangePwd --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ============ TNB ============ */
    /**
     *
     * @param mobile
     * @return
     * @throws RemoteException
     */
    @Override
    public int getCountMobile(String mobile) throws RemoteException {
        try {
            LOGGER.info("getCountMobile --> [IN] --> mobile = [" + mobile + "]");
            TnbBO tbnbo = new TnbBO();
            int result = tbnbo.getCountMobile(mobile);
            LOGGER.info("getCountMobile --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param vub
     * @return
     * @throws RemoteException
     */
    @Override
    public List getUserBranch(VwUserbranch vub) throws RemoteException {
        try {
            LOGGER.info("getUserBranch --> [IN] --> vub_idEntity = [" + vub.getIdEntity() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.getUserBranch(vub);
            LOGGER.info("getUserBranch --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ============ EBK_PROCESS ============ */
    /**
     *
     * @param pep
     * @return
     * @throws RemoteException
     */
    @Override
    public int insEbkProcess(PblEbkProcess pep) throws RemoteException {
        try {
            LOGGER.info("insEbkProcess --> [IN] --> pep_custAccNo = [" + pep.getCustAccNo() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            Random random = new Random();
            Date d = new Date();
            long lDateTime = d.getTime();
            String idKey = pep.getIdusr() + lDateTime + random.nextInt(999999999);
            pep.setIdkey(idKey);
            pep.setTxnDate(d);
            pep.setStatus("P");
            pep.setIdscreen("0");
            int result = smsscbBO.insEbkProcess(pep);
            LOGGER.info("insEbkProcess --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pep
     * @throws RemoteException
     */
    @Override
    public void updEbkProcess(PblEbkProcess pep) throws RemoteException {
        try {
            LOGGER.info("updEbkProcess --> [IN] --> pep_custAccNo = [" + pep.getCustAccNo() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updEbkProcess(pep);
            LOGGER.info("updEbkProcess --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pct
     * @return
     * @throws RemoteException
     */
    public int insPblCustTemplate(PblCustTemplate pct) throws RemoteException {
        try {
            LOGGER.info("insPblCustTemplate --> [IN] --> pct_id = [" + pct.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insPblCustTemplate(pct);
            LOGGER.info("insPblCustTemplate --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param pct
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean delPblCustTemplate(PblCustTemplate pct) throws RemoteException {
        boolean result = false;
        try {
            LOGGER.info("delPblCustTemplate --> [IN] --> pct_id = [" + pct.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.delPblCustTemplate(pct);
            result = true;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("delPblCustTemplate --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /**
     *
     * @param iduser
     * @param idservicetype
     * @param idprovider
     * @return
     * @throws Exception
     */
    @Override
    public List getPblCustTemplates(String iduser, String idservicetype, String idprovider) throws Exception {
        try {
            LOGGER.info("getPblCustTemplates --> [IN] --> iduser = [" + iduser + "], idservicetype = [" + idservicetype + "], idprovider = [" + idprovider + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.getPblCustTemplates(iduser, idservicetype, idprovider);
            LOGGER.info("getPblCustTemplates --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public PblCustTemplate getPblCustTemplate(int id) throws Exception {
        try {
            LOGGER.info("getPblCustTemplate --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            PblCustTemplate result = smsscbBO.getPblCustTemplate(id);
            LOGGER.info("getPblCustTemplate --> [OUT] --> result_id = [" + (result == null ? null : result.getId()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param transcode
     * @return
     * @throws RemoteException
     */
    @Override
    public PblEbkProcess getEbkProcessById(int transcode) throws RemoteException {
        try {
            LOGGER.info("getEbkProcessById --> [IN] --> transcode = [" + transcode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            PblEbkProcess result = smsscbBO.getEbkProcessById(transcode);
            LOGGER.info("getEbkProcessById --> [OUT] --> result_custAccNo = [" + (result == null ? null : result.getCustAccNo()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pc
     * @return
     * @throws RemoteException
     */
    @Override
    public int insPblCollated(PblCollated pc) throws RemoteException {
        try {
            LOGGER.info("insPblCollated --> [IN] --> pc_id = [" + pc.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = (Integer) smsscbBO.create(pc);
            LOGGER.info("insPblCollated --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pc
     * @throws RemoteException
     */
    @Override
    public void updPblCollated(PblCollated pc) throws RemoteException {
        try {
            LOGGER.info("updPblCollated --> [IN] --> pc_id = [" + pc.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.update(pc);
            LOGGER.info("updPblCollated --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

//    @Override
//    public PblCollated findPblCollatedById(int id) throws RemoteException {
//        try {
//            SmsSCBBO smsscbBO = new SmsSCBBO();
//            return smsscbBO.findPblCollatedById(id);
//        } catch (Exception ex) {
//            throw new RemoteException(ex.getMessage(), ex);
//        }
//    }
//
//    @Override
//    public List getListCollatedByDate(String date, String idpartner) throws RemoteException {
//        try {
//            SmsSCBBO smsscbBO = new SmsSCBBO();
//            return smsscbBO.getListCollatedByDate(date, idpartner);
//        } catch (Exception ex) {
//            throw new RemoteException(ex.getMessage(), ex);
//        }
//    }
    /**
     *
     * @param customercode
     * @param refpartner
     * @return
     * @throws RemoteException
     */
    @Override
    public List getPblBillPaidByRefPartner(String customercode, String refpartner) throws RemoteException {
        try {
            LOGGER.info("getPblBillPaidByRefPartner --> [IN] --> customercode = [" + customercode + "], refpartner = [" + refpartner + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.getPblBillPaidByRefPartner(customercode, refpartner);
            LOGGER.info("getPblBillPaidByRefPartner --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idscreen
     * @return
     * @throws RemoteException
     */
    @Override
    public PblEbkScreen getEbkScreenById(int idscreen) throws RemoteException {
        try {
            LOGGER.info("getEbkScreenById --> [IN] --> idscreen = [" + idscreen + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            PblEbkScreen result = smsscbBO.getEbkScreenById(idscreen);
            LOGGER.info("getEbkScreenById --> [OUT] --> result_id = [" + (result == null ? null : result.getId()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*chuong bo sung ham paybill*/
    /**
     *
     * @param iduser
     * @return
     * @throws RemoteException
     */
    @Override
    public VwMstchanneluser getVwMstchanneluser(String iduser) throws RemoteException {
        try {
            LOGGER.info("getVwMstchanneluser --> [IN] --> iduser = [" + iduser + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            VwMstchanneluser result = smsscbBO.getVwMstchanneluser(iduser);
            LOGGER.info("getVwMstchanneluser --> [OUT] --> result_idEntity = [" + (result == null ? null : result.getIdEntity()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idscreen
     * @return
     * @throws RemoteException
     */
    @Override
    public PblEbkScreen getEbkScreenById_new(PblEbkScreenId idscreen) throws RemoteException {
        try {
            LOGGER.info("getEbkScreenById_new --> [IN] --> idscreen_idscreen = [" + idscreen.getIdscreen() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            PblEbkScreen result = smsscbBO.getEbkScreenById_new(idscreen);
            LOGGER.info("getEbkScreenById_new --> [OUT] --> result_id = [" + (result == null ? null : result.getId()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //kiem tra userid va cust_acc co khop nhau khong
    /**
     *
     * @param pIduser
     * @param pCust_ac_no
     * @return
     * @throws RemoteException
     */
    @Override
    public int checkIduserAndCust_ac_no(String pIduser, String pCust_ac_no) throws RemoteException {
        try {
            LOGGER.info("checkIduserAndCust_ac_no --> [IN] --> pIduser = [" + pIduser + "], pCust_ac_no = [" + pCust_ac_no + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.checkIduserAndCust_ac_no(pIduser, pCust_ac_no);
            LOGGER.info("checkIduserAndCust_ac_no --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ipaddress
     * @param pIduser
     * @param pIdsession
     * @return
     * @throws RemoteException
     */
    @Override
    public int checkusersession_valid(String ipaddress, String pIduser, String pIdsession) throws RemoteException {
        try {
            LOGGER.info("checkusersession_valid --> [IN] --> ipaddress = [" + ipaddress + "], pIduser = [" + pIduser + "], pIdsession = [" + pIdsession + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.checkusersession_valid(ipaddress, pIduser, pIdsession);
            LOGGER.info("checkusersession_valid --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param pIdsession
     * @return
     * @throws RemoteException
     */
    @Override
    public String getsession_lang(String pIdsession) throws RemoteException {
        try {
            LOGGER.info("getsession_lang --> [IN] --> pIdsession = [" + pIdsession + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.getsession_lang(pIdsession);
            LOGGER.info("getsession_lang --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idUser
     * @param udfName
     * @return
     * @throws RemoteException
     */
    @Override
    public String getUdfValue_User(String idUser, String udfName) throws RemoteException {
        try {
            LOGGER.info("getUdfValue_User --> [IN] --> idUser = [" + idUser + "], udfName = [" + udfName + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.getUdfValue_User(idUser, udfName);
            LOGGER.info("getUdfValue_User --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idUser
     * @return
     * @throws RemoteException
     */
    @Override
    public String GetTypeConfirm_User(String idUser) throws RemoteException {
        try {
            LOGGER.info("GetTypeConfirm_User --> [IN] --> idUser = [" + idUser + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.GetTypeConfirm_User(idUser);
            LOGGER.info("GetTypeConfirm_User --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public HashMap<String, String> getPblPartnerservices() throws RemoteException {
        try {
            LOGGER.info("getPblPartnerservices --> [IN] --> input is empty");
            SmsSCBBO smsscbbo = new SmsSCBBO();
            ArrayList _arl;
            HashMap<String, String> _rshm = new HashMap<String, String>();
            String sKey = "";
            String sXml = "";

            _arl = smsscbbo.getPblPartnerservices();
            int size = _arl.size();
            for (int i = 0; i < size; i++) {

                HashMap _hm = (HashMap) (Map) _arl.get(i);
                String groupid = _hm.get("groupid").toString();
                String code = _hm.get("code").toString();
                String name = _hm.get("name").toString();
                //    System.out.println("sKey ="+sKey);
                if (i < size - 1) {
                    if (!sKey.equalsIgnoreCase(groupid) && !sKey.equalsIgnoreCase("")) {
                        _rshm.put(sKey, sXml);
                        // System.out.println("put groupid=" + sKey + "=" + sXml);
                        sKey = groupid;
                        sXml = "";
                        sXml = sXml + "<option value=\"" + code + "\">" + name + "</option>";
                    } else {
                        sKey = groupid;
                        sXml = sXml + "<option value=\"" + code + "\">" + name + "</option>";
                    }
                } else if (i == size - 1) {
                    _rshm.put(sKey, sXml);
                    // System.out.println("put groupid=" + sKey + "=" + sXml);
                    sKey = groupid;
                    sXml = "";
                    sXml = sXml + "<option value=\"" + code + "\">" + name + "</option>";
                    _rshm.put(sKey, sXml);
                    // System.out.println("put groupid=" + sKey + "=" + sXml);
                }
            }
            LOGGER.info("getPblPartnerservices --> [OUT] --> Size = [" + _rshm.size() + "]");
            return _rshm;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public HashMap<String, String> getPblEbkScreens() throws RemoteException {
        try {
            LOGGER.info("getPblEbkScreens --> [IN] --> input is empty");
            SmsSCBBO smsscbbo = new SmsSCBBO();
            ArrayList _arl;
            HashMap<String, String> _rshm = new HashMap<String, String>();
            String sKey = "";
            String sXml = "";

            _arl = smsscbbo.getPblEbkScreens();
            int size = _arl.size();
            for (int i = 0; i < size; i++) {

                HashMap _hm = (HashMap) (Map) _arl.get(i);
                String key = _hm.get("key").toString();
                String data = "";
                if (_hm.get("data") != null) {
                    data = (String) _hm.get("data");
                }
                if (_hm.get("data_01") != null) {
                    data = data + (String) _hm.get("data_01");
                }

                _rshm.put(key, data);
            }
            LOGGER.info("getPblEbkScreens --> [OUT] --> Size = [" + _rshm.size() + "]");
            return _rshm;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pl
     * @return
     * @throws RemoteException
     */
    @Override
    public int insPblLog(PblLog pl) throws RemoteException {
        try {
            LOGGER.info("insPblLog --> [IN] --> pl_id = [" + pl.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insPblLog(pl);
            LOGGER.info("insPblLog --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param table
     * @param field
     * @param idkey
     * @return
     * @throws RemoteException
     */
    @Override
    public String getmsglog(String table, String field, String idkey) throws RemoteException {
        try {
            LOGGER.info("getmsglog --> [IN] --> table = [" + table + "], field = [" + field + "], idkey = [" + idkey + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.getmsglog(table, field, idkey);
            LOGGER.info("getmsglog --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param iduser
     * @return
     * @throws RemoteException
     */
    @Override
    public String getuserphonenumber(String iduser) throws RemoteException {
        try {
            LOGGER.info("getuserphonenumber --> [IN] --> iduser = [" + iduser + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.getuserphonenumber(iduser);
            LOGGER.info("getuserphonenumber --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param code
     * @return
     * @throws RemoteException
     */
    @Override
    public EtsMstbranch getEtsMstBranch(String code) throws RemoteException {
        try {
            LOGGER.info("getEtsMstBranch --> [IN] --> code = [" + code + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            EtsMstbranch result = (EtsMstbranch) smsscbBO.findById(EtsMstbranch.class, code);
            LOGGER.info("getEtsMstBranch --> [OUT] --> result_code = [" + (result == null ? null : result.getCode()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param brn
     * @throws RemoteException
     */
    @Override
    public void insOrUpdEtsMstBranch(EtsMstbranch brn) throws RemoteException {
        try {
            LOGGER.info("insOrUpdEtsMstBranch --> [IN] --> brn_code = [" + brn.getCode() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.saveOrUpdate(brn);
            LOGGER.info("insOrUpdEtsMstBranch --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param paf
     * @throws RemoteException
     */
    @Override
    public void saveOrUpdatePblAutoregFail(PblAutoregFail paf) throws RemoteException {
        try {
            LOGGER.info("saveOrUpdatePblAutoregFail --> [IN] --> paf_id = [" + paf.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.saveOrUpdate(paf);
            LOGGER.info("saveOrUpdatePblAutoregFail --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList getListUserToNeedChgPwd() throws RemoteException {
        try {
            LOGGER.info("getListUserToNeedChgPwd --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getListUserToNeedChgPwd();
            LOGGER.info("getListUserToNeedChgPwd --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cif
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getBranchInfoByCif(String cif) throws RemoteException {
        try {
            LOGGER.info("getBranchInfoByCif --> [IN] --> cif = [" + cif + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getBranchInfoByCif(cif);
            LOGGER.info("getBranchInfoByCif --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param iduser
     * @param custaccno
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean isExistsAccCasaByIduserAccno(String iduser, String custaccno) throws RemoteException {
        try {
            LOGGER.info("isExistsAccCasaByIduserAccno --> [IN] --> iduser = [" + iduser + "], custaccno = [" + custaccno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            boolean result = fcdbBO.isExistsAccCasaByIduserAccno(iduser, custaccno);
            LOGGER.info("isExistsAccCasaByIduserAccno --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param p_entity
     * @param p_channel_id
     * @param p_txn_id
     * @param p_iduser
     * @param p_typeuser
     * @param p_txn_date
     * @param p_amount
     * @param p_ccy
     * @param p_cum
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean check_trans_limit(String p_entity, String p_channel_id, String p_txn_id, String p_iduser, String p_typeuser, Date p_txn_date, String p_amount, String p_ccy, int p_cum) throws RemoteException {
        try {
            LOGGER.info("check_trans_limit --> [IN] --> p_entity = [" + p_entity + "], p_channel_id = [" + p_channel_id + "], p_txn_id = [" + p_txn_id + "], p_iduser = [" + p_iduser + "], p_typeuser = [" + p_typeuser + "], p_txn_date = [" + p_txn_date + "], p_amount = [" + p_amount + "], p_ccy = [" + p_ccy + "], p_cum = [" + p_cum + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            boolean result = smsscbBO.check_trans_limit(p_entity, p_channel_id, p_txn_id, p_iduser, p_typeuser, p_txn_date, p_amount, p_ccy, p_cum);
            LOGGER.info("check_trans_limit --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param iduser
     * @param custaccno
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean isExistsAccTDByIduserAccno(String iduser, String custaccno) throws RemoteException {
        try {
            LOGGER.info("isExistsAccTDByIduserAccno --> [IN] --> iduser = [" + iduser + "], custaccno = [" + custaccno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            boolean result = fcdbBO.isExistsAccTDByIduserAccno(iduser, custaccno);
            LOGGER.info("isExistsAccTDByIduserAccno --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getBranchByAccno(String accountno) throws RemoteException {
        try {
            LOGGER.info("getBranchByAccno --> [IN] --> accountno = [" + accountno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getBranchByAccno(accountno);
            LOGGER.info("getBranchByAccno --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public List getGwPermissions() throws RemoteException {
        try {
            LOGGER.info("getGwPermissions --> [IN] --> input is empty");
            PartnerBO partnerbo = new PartnerBO();
            List<String> lstPermission = new ArrayList<String>();
            ArrayList arlPermission;
            arlPermission = partnerbo.getGwPermissions();
            for (int i = 0; i <= arlPermission.size() - 1; i++) {
                HashMap _hm = (HashMap) (Map) arlPermission.get(i);
                lstPermission.add(_hm.get("partner_id").toString() + "-" + _hm.get("function_name").toString());
            }
            LOGGER.info("getGwPermissions --> [OUT] --> Size = [" + lstPermission.size() + "]");
            return lstPermission;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public HashMap<String, String> getlist_telefirstnumber() throws RemoteException {
        try {
            LOGGER.info("getlist_telefirstnumber --> [IN] --> input is empty");
            SmsSCBBO smsscbbo = new SmsSCBBO();
            HashMap<String, String> _hmrs = new HashMap<String, String>();
            ArrayList arl;
            arl = smsscbbo.getlist_telefirstnumber();
            for (int i = 0; i <= arl.size() - 1; i++) {
                HashMap _hm = (HashMap) (Map) arl.get(i);
                String[] ar_firstnum = _hm.get("firstnumberlist").toString().split(",");
                String tetecode = _hm.get("telename").toString();
                for (int j = 0; j <= ar_firstnum.length - 1; j++) {
                    //System.out.println(ar_firstnum[j].trim()+"="+tetecode);
                    _hmrs.put(ar_firstnum[j].trim(), tetecode);
                }

            }
            LOGGER.info("getlist_telefirstnumber --> [OUT] --> Size = [" + _hmrs.size() + "]");
            return _hmrs;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param vgroup_id
     * @return
     * @throws RemoteException
     */
    @Override
    public HashMap<String, String> getlist_parainfo(String vgroup_id) throws RemoteException {
        try {
            LOGGER.info("getlist_parainfo --> [IN] --> vgroup_id = [" + vgroup_id + "]");
            SmsSCBBO smsscbbo = new SmsSCBBO();
            HashMap<String, String> _hmrs = new HashMap<String, String>();
            ArrayList arl;
            arl = smsscbbo.getlist_parainfo(vgroup_id);
            for (int i = 0; i <= arl.size() - 1; i++) {
                HashMap _hm = (HashMap) (Map) arl.get(i);
                String code = _hm.get("code").toString();
                String name = _hm.get("name").toString();
                _hmrs.put(code, name);
            }
            LOGGER.info("getlist_parainfo --> [OUT] --> Size = [" + _hmrs.size() + "]");
            return _hmrs;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param iduser
     * @param accountloan
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean isExistsAccLoanByCifLoan(String iduser, String accountloan) throws RemoteException {
        try {
            LOGGER.info("isExistsAccLoanByCifLoan --> [IN] --> iduser = [" + iduser + "], accountloan = [" + accountloan + "]");
            FcdbBO fcdbBO = new FcdbBO();
            boolean result = fcdbBO.isExistsAccLoanByCifLoan(iduser, accountloan);
            LOGGER.info("isExistsAccLoanByCifLoan --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountloan
     * @return
     * @throws RemoteException
     */
    @Override
    public List getInfoAccountLoan(String accountloan) throws RemoteException {
        try {
            LOGGER.info("getInfoAccountLoan --> [IN] --> accountloan = [" + accountloan + "]");
            FcdbBO fcdbBO = new FcdbBO();
            List result = fcdbBO.getInfoAccountLoan(accountloan);
            LOGGER.info("getInfoAccountLoan --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     * *****************************SEND
     * SMS*************************************
     */
    /**
     * Tim danh sach cac mau tin chua duoc goi den khach hang
     *
     * @param vstatus
     * @param numtrysend
     * @return
     * @throws java.rmi.RemoteException
     */
    @Override
    public List findsmssenders(char vstatus, long numtrysend) throws RemoteException {
        try {
            LOGGER.info("findsmssenders --> [IN] --> vstatus = [" + vstatus + "], numtrysend = [" + numtrysend + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.findsmssenders(vstatus, numtrysend);
            LOGGER.info("findsmssenders --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param _idpartner
     * @return
     * @throws RemoteException
     */
    @Override
    public SmsPartner getsmspartner(String _idpartner) throws RemoteException {
        try {
            LOGGER.info("getsmspartner --> [IN] --> _idpartner = [" + _idpartner + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            SmsPartner result = (SmsPartner) smsscbBO.findById(SmsPartner.class, _idpartner);
            LOGGER.info("getsmspartner --> [OUT] --> result_idpartner = [" + (result == null ? null : result.getIdpartner()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smsobj
     * @throws RemoteException
     */
    @Override
    public void upd_smssender(SmsSender smsobj) throws RemoteException {
        try {
            LOGGER.info("upd_smssender --> [IN] --> smsobj_idmessage = [" + smsobj.getIdmessage() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.upd_smssender(smsobj);
            LOGGER.info("upd_smssender --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smsobj
     * @return
     * @throws RemoteException
     */
    @Override
    public int ins_smssender(SmsSender smsobj) throws RemoteException {
        try {
            LOGGER.info("ins_smssender --> [IN] --> smsobj_idmessage = [" + smsobj.getIdmessage() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int rs = smsscbBO.ins_smssender(smsobj);
            LOGGER.info("ins_smssender --> [OUT] --> rs = [" + rs + "]");
            return rs;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smsobj
     * @throws RemoteException
     */
    @Override
    public void upd_smspartner(SmsPartner smsobj) throws RemoteException {
        try {
            LOGGER.info("upd_smspartner --> [IN] --> smsobj_idpartner = [" + smsobj.getIdpartner() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.upd_smspartner(smsobj);
            LOGGER.info("upd_smspartner --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     * ******************************************************************
     */
    /**
     *******************************************************************
     * BEGIN OF TIET KIEM TICH LUY
     *
     * @param accounttichluy
     * @return
     * @throws java.rmi.RemoteException
     */
    @Override
    public ArrayList<?> searchCustAlertTichLuy(String accounttichluy) throws RemoteException {
        try {
            LOGGER.info("searchCustAlertTichLuy --> [IN] --> accounttichluy = [" + accounttichluy + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.searchCustAlertTichLuy(accounttichluy);
            LOGGER.info("searchCustAlertTichLuy --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smscustalerttichluy
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertAlertAccountTichLuy(SmsCustAlertTichLuy smscustalerttichluy) throws RemoteException {
        try {
            LOGGER.info("insertAlertAccountTichLuy --> [IN] --> smscustalerttichluy_id = [" + smscustalerttichluy.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertAlertAccountTichLuy(smscustalerttichluy);
            LOGGER.info("insertAlertAccountTichLuy --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accounttd
     * @param cif
     * @param idcard
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> findListAccountTichLuy(String accounttd, String cif, String idcard) throws RemoteException {
        try {
            LOGGER.info("findListAccountTichLuy --> [IN] --> accounttd = [" + accounttd + "], cif = [" + cif + "], idcard = [" + idcard + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.findListAccountTichLuy(accounttd, cif, idcard);
            LOGGER.info("findListAccountTichLuy --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accounttichluy
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getAccountTDByAccountTichLuy(String accounttichluy) throws RemoteException {
        try {
            LOGGER.info("getAccountTDByAccountTichLuy --> [IN] --> accounttichluy = [" + accounttichluy + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getAccountTDByAccountTichLuy(accounttichluy);
            LOGGER.info("getAccountTDByAccountTichLuy --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smscustalerttichluy
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getAccountTichLuyById(SmsCustAlertTichLuy smscustalerttichluy) throws RemoteException {
        try {
            LOGGER.info("getAccountTichLuyById --> [IN] --> smscustalerttichluy_cust_ac_no = [" + smscustalerttichluy.getCust_ac_no() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getAccountTichLuyById(smscustalerttichluy);
            LOGGER.info("getAccountTichLuyById --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smscustalerttichluy
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateAccountTichLuyMobileById(SmsCustAlertTichLuy smscustalerttichluy) throws RemoteException {
        try {
            LOGGER.info("updateAccountTichLuyMobileById --> [IN] --> smscustalerttichluy_cust_ac_no = [" + smscustalerttichluy.getCust_ac_no() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateAccountTichLuyMobileById(smscustalerttichluy);
            LOGGER.info("updateAccountTichLuyMobileById --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cust_no
     * @param registertype
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> findHistoryListAccountTichLuy(String cust_no, String registertype) throws RemoteException {
        try {
            LOGGER.info("findHistoryListAccountTichLuy --> [IN] --> cust_no = [" + cust_no + "], registertype = [" + registertype + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.findHistoryListAccountTichLuy(cust_no, registertype);
            LOGGER.info("findHistoryListAccountTichLuy --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *******************************************************************
     * END OF TIET KIEM TICH LUY
     */
    /**
     *******************************************************************
     * BEGIN OF PAYOO PAYMENT
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @param billInfo
     * @return
     * @throws java.rmi.RemoteException
     */
    @Override
    public int insertPbl_PayooLog(String customercode,
            String serviceID, String providerID, String billInfo) throws RemoteException {
        try {
            LOGGER.info("insertPbl_PayooLog --> [IN] --> customercode = [" + customercode + "], serviceID = [" + serviceID + "], providerID = [" + providerID + "], billInfo = [" + billInfo + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertPbl_PayooLog(customercode, serviceID, providerID, billInfo);
            LOGGER.info("insertPbl_PayooLog --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getPbl_PayooLog(String customercode, String serviceID, String providerID) throws RemoteException {
        try {
            LOGGER.info("getPbl_PayooLog --> [IN] --> customercode = [" + customercode + "], serviceID = [" + serviceID + "], providerID = [" + providerID + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getPbl_PayooLog(customercode, serviceID, providerID);
            LOGGER.info("getPbl_PayooLog --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getPbl_PayooLogPrint(String customercode, String serviceID, String providerID) throws RemoteException {
        try {
            LOGGER.info("getPbl_PayooLogPrint --> [IN] --> customercode = [" + customercode + "], serviceID = [" + serviceID + "], providerID = [" + providerID + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getPbl_PayooLogPrint(customercode, serviceID, providerID);
            LOGGER.info("getPbl_PayooLogPrint --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param customercode
     * @param serviceID
     * @param providerID
     * @return
     * @throws RemoteException
     */
    @Override
    public String getBillInfo(String customercode, String serviceID, String providerID) throws RemoteException {
        try {
            LOGGER.info("getBillInfo --> [IN] --> customercode = [" + customercode + "], serviceID = [" + serviceID + "], providerID = [" + providerID + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.getBillInfo(customercode, serviceID, providerID);
            LOGGER.info("getBillInfo --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param seqname
     * @return
     * @throws RemoteException
     */
    @Override
    public int getIdSeqByName(String seqname) throws RemoteException {
        try {
            LOGGER.info("getIdSeqByName --> [IN] --> seqname = [" + seqname + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.getIdSeqByName(seqname);
            LOGGER.info("getIdSeqByName --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *******************************************************************
     * END OF PAYOO PAYMENT
     */
    /**
     *******************************************************************
     * END OF PAYOO PAYMENT
     */
    /**
     * Author: LyDTY BEGIN ONLINE PAYMENT Date: 10/Dec/2013
     *
     * @param PartnerID
     * @param ClientID
     * @param TransID
     * @param CardNumber
     * @param Amount
     * @param MerchantId
     * @param CardDate
     * @param CardHolderName
     * @param LocalDate
     * @param AddInfo
     * @param Language
     * @param CCY
     * @param ChannelID
     * @param URLAuthen
     * @param pIDVerify
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String checkCustomerInfo(String PartnerID, String TransID,
            String CardNumber, String CardHolderName,
            String CardDate, String MerchantId,
            Double Amount, String CCY, String Language,
            String ClientID, String LocalDate, String AddInfo,
            String ChannelID, String URLAuthen, String pIDVerify) throws Exception {
        try {
            LOGGER.info("checkCustomerInfo --> [IN] --> PartnerID = [" + PartnerID + "], TransID = ["
                    + TransID + "], CardNumber = [" + CardNumber + "], CardHolderName = ["
                    + CardHolderName + "], CardDate = [" + CardDate + "], MerchantId = [" + MerchantId
                    + "], Amount = [" + Amount + "], CCY = [" + CCY + "], Language = [" + Language
                    + "], ClientID = [" + ClientID + "], LocalDate = [" + LocalDate + "], AddInfo = ["
                    + AddInfo + "], ChannelID = [" + ChannelID + "], URLAuthen = [" + URLAuthen
                    + "], pIDVerify = [" + pIDVerify + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.checkCustomerInfo(PartnerID, TransID, CardNumber, CardHolderName, CardDate, MerchantId, Amount, CCY, Language, ClientID, LocalDate, AddInfo, ChannelID, URLAuthen, pIDVerify);
            LOGGER.info("checkCustomerInfo --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pRefID
     * @throws Exception
     */
    @Override
    public void TransferOnlinePayment(int pID, String pRefID
    ) throws Exception {
        try {
            LOGGER.info("TransferOnlinePayment --> [IN] --> pID = [" + pID + "], pRefID = [" + pRefID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.TransferOnlinePayment(pID, pRefID);
            LOGGER.info("TransferOnlinePayment --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pURLREDIREC
     * @param pStatus
     * @throws Exception
     */
    @Override
    public void CommitTransfer(int pID, String pURLREDIREC, String pStatus
    ) throws Exception {
        try {
            LOGGER.info("CommitTransfer --> [IN] --> pID = [" + pID + "], pURLREDIREC = [" + pURLREDIREC + "], pStatus = [" + pStatus + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.CommitTransfer(pID, pURLREDIREC, pStatus);
            LOGGER.info("CommitTransfer --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pPartnerID
     * @param pMerchanID
     * @param pTransID
     * @param pRefundTransID
     * @param pRefundAmount
     * @param pCCY
     * @param pADDINFO
     * @param pLocalDatetime
     * @return
     * @throws Exception
     */
    @Override
    public String[] CheckRefundTransfer(String pPartnerID, String pMerchanID,
            String pTransID, String pRefundTransID,
            BigDecimal pRefundAmount, String pCCY, String pADDINFO, String pLocalDatetime) throws Exception {
        try {
            LOGGER.info("CheckRefundTransfer --> [IN] --> pPartnerID = [" + pPartnerID
                    + "], pMerchanID = [" + pMerchanID + "], pTransID = [" + pTransID
                    + "], pRefundTransID = [" + pRefundTransID + "], pRefundAmount = [" + pRefundAmount
                    + "], pCCY = [" + pCCY + "], pADDINFO = [" + pADDINFO + "], pLocalDatetime = ["
                    + pLocalDatetime + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.CheckRefundTransfer(pPartnerID, pMerchanID, pTransID, pRefundTransID, pRefundAmount, pCCY, pADDINFO, pLocalDatetime);
            LOGGER.info("CheckRefundTransfer --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @param pCoreref
     * @param pStatus
     * @throws Exception
     */
    @Override
    public void UpdateRefundTransfer(String ID, String pCoreref, String pStatus) throws Exception {
        try {
            LOGGER.info("UpdateRefundTransfer --> [IN] --> ID = [" + ID + "], pCoreref = [" + pCoreref + "], pStatus = [" + pStatus + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.UpdateRefundTransfer(ID, pCoreref, pStatus);
            LOGGER.info("UpdateRefundTransfer --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PartnerID
     * @param TransID
     * @param MerchantID
     * @param Amount
     * @param CCY
     * @param QTransID
     * @return
     * @throws Exception
     */
    @Override
    public String[] QuerryTransfer(String PartnerID, String TransID, String MerchantID,
            Double Amount, String CCY, String QTransID
    ) throws Exception {
        try {
            LOGGER.info("QuerryTransfer --> [IN] --> PartnerID = [" + PartnerID + "], TransID = ["
                    + TransID + "], MerchantID = [" + MerchantID + "], Amount = [" + Amount
                    + "], CCY = [" + CCY + "], QTransID = [" + QTransID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.QuerryTransfer(PartnerID, TransID, MerchantID, Amount, CCY, QTransID);
            LOGGER.info("QuerryTransfer --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pEbankUserID
     * @param pIDVerify
     * @return
     * @throws Exception
     */
    @Override
    public String[] getPaymentOnlineInfo(String pEbankUserID, String pIDVerify)
            throws Exception {
        try {
            LOGGER.info("getPaymentOnlineInfo --> [IN] --> pEbankUserID = [" + pEbankUserID + "], pIDVerify = [" + pIDVerify + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.getPaymentOnlineInfo(pEbankUserID, pIDVerify);
            LOGGER.info("getPaymentOnlineInfo --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pPhoneNumber
     * @param pOTP
     * @param pIDAddress
     * @throws Exception
     */
    @Override
    public void insertOTPSMS(int pID, String pPhoneNumber, String pOTP, String pIDAddress)
            throws Exception {
        try {
            LOGGER.info("insertOTPSMS --> [IN] --> pID = [" + pID + "], pPhoneNumber = [" + pPhoneNumber
                    + "], pOTP = [" + pOTP + "], pIDAddress = [" + pIDAddress + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.insertOTPSMS(pID, pPhoneNumber, pOTP, pIDAddress);
            LOGGER.info("insertOTPSMS --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pTimeOut
     * @return
     * @throws Exception
     */
    @Override
    public String[] getOTPSMS(int pID, String pTimeOut)
            throws Exception {
        try {
            LOGGER.info("getOTPSMS --> [IN] --> pID = [" + pID + "], pTimeOut = [" + pTimeOut + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.getOTPSMS(pID, pTimeOut);
            LOGGER.info("getOTPSMS --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pUserID
     * @param pClienID
     * @return
     * @throws Exception
     */
    @Override
    public int checkSessionForTransfer(String pID, String pUserID, String pClienID) throws Exception {
        try {
            LOGGER.info("checkSessionForTransfer --> [IN] --> pID = [" + pID + "], pUserID = [" + pUserID + "], pClienID = [" + pClienID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.checkSessionForTransfer(pID, pUserID, pClienID);
            LOGGER.info("checkSessionForTransfer --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param collate
     * @throws RemoteException
     */
    @Override
    public void ReceiveCollateData(PayOnlineCollate collate) throws RemoteException {
        try {
            LOGGER.info("ReceiveCollateData --> [IN] --> collate_Cardnumber = [" + collate.getCardnumber() + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.ReceiveCollateData(collate);
            LOGGER.info("ReceiveCollateData --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pPartnerID
     * @param pTransdate
     * @return
     * @throws Exception
     */
    @Override
    public List<PayOnlineCollate> AnswerCollateData(String pPartnerID, String pTransdate) throws Exception {
        try {
            LOGGER.info("AnswerCollateData --> [IN] --> pPartnerID = [" + pPartnerID + "], pTransdate = [" + pTransdate + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<PayOnlineCollate> result = BO.AnswerCollateData(pPartnerID, pTransdate);
            LOGGER.info("AnswerCollateData --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param IDverify
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<?> getDataByVerifyID(String IDverify) throws Exception {
        try {
            LOGGER.info("getDataByVerifyID --> [IN] --> IDverify = [" + IDverify + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            ArrayList<?> result = BO.getDataByVerifyID(IDverify);
            LOGGER.info("getDataByVerifyID --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pPartnerID
     * @param pTransdate
     * @return
     * @throws Exception
     */
    @Override
    public int isCollate(String pPartnerID, String pTransdate) throws Exception {
        try {
            LOGGER.info("isCollate --> [IN] --> pPartnerID = [" + pPartnerID + "], pTransdate = [" + pTransdate + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.isCollate(pPartnerID, pTransdate);
            LOGGER.info("isCollate --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     * Author: LyDTY END ONLINE PAYMENT Date: 10/Dec/2013
     *
     * @param Trace_No
     * @param Order_No
     * @param Status
     * @param PartnerDatetime
     * @param Amount
     * @param PayooDatetime
     * @param TypeOfTrans
     * @param CheckSum
     * @param Note
     * @return
     * @throws java.rmi.RemoteException
     */
    //PAYOO COLLATE
    @Override
    public int insert_PayooCollateData(String Trace_No, String Order_No, BigDecimal Amount,
            Date PartnerDatetime, Date PayooDatetime, String TypeOfTrans, String Status,
            String Note, String CheckSum) throws RemoteException {
        try {
            LOGGER.info("insert_PayooCollateData --> [IN] --> Trace_No = [" + Trace_No
                    + "], Order_No = [" + Order_No + "], Amount = [" + Amount + "], PartnerDatetime = ["
                    + PartnerDatetime + "], PayooDatetime = [" + PayooDatetime + "], TypeOfTrans = ["
                    + TypeOfTrans + "], Status = [" + Status + "], Note = [" + Note + "], CheckSum = ["
                    + CheckSum + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insert_PayooCollateData(Trace_No, Order_No, Amount, PartnerDatetime,
                    PayooDatetime, TypeOfTrans, Status, Note, CheckSum);
            LOGGER.info("insert_PayooCollateData --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param date
     * @return
     * @throws RemoteException
     */
    @Override
    public int CollatePayooBillData(String date) throws RemoteException {
        try {
            LOGGER.info("CollatePayooBillData --> [IN] --> date = [" + date + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.CollatePayooBillData(date);
            LOGGER.info("CollatePayooBillData --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param date
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean isExsistPayooBillCollateData(String date) throws RemoteException {
        try {
            LOGGER.info("isExsistPayooBillCollateData --> [IN] --> date = [" + date + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            boolean result = smsscbBO.isExsistPayooBillCollateData(date);
            LOGGER.info("isExsistPayooBillCollateData --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //END PAYOO COLLATE
    /**
     *
     * @param pVerifyID
     * @param pTypeFailed
     * @return
     * @throws Exception
     */
    @Override
    public int checkFailed(String pVerifyID, String pTypeFailed) throws Exception {
        try {
            LOGGER.info("checkFailed --> [IN] --> pVerifyID = [" + pVerifyID + "], pTypeFailed = [" + pTypeFailed + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.checkFailed(pVerifyID, pTypeFailed);
            LOGGER.info("checkFailed --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pVerifyID
     * @param pTypeFailed
     * @throws Exception
     */
    @Override
    public void insertFailed(String pVerifyID, String pTypeFailed) throws Exception {
        try {
            LOGGER.info("insertFailed --> [IN] --> pVerifyID = [" + pVerifyID + "], pTypeFailed = [" + pTypeFailed + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.insertFailed(pVerifyID, pTypeFailed);
            LOGGER.info("insertFailed --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pTransID
     * @param pPartnerID
     * @return
     * @throws Exception
     */
    @Override
    public int CheckBeforeTransfer(String pTransID, String pPartnerID) throws Exception {
        try {
            LOGGER.info("CheckBeforeTransfer --> [IN] --> pTransID = [" + pTransID + "], pPartnerID = [" + pPartnerID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.CheckBeforeTransfer(pTransID, pPartnerID);
            LOGGER.info("CheckBeforeTransfer --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //BEGIN VNPAY COLLATE
    /**
     *
     * @param o
     * @throws Exception
     */
    @Override
    public void InsertPBL_VNPAY_COLLATED(PblCollated_VNPAY o) throws Exception {
        try {
            LOGGER.info("InsertPBL_VNPAY_COLLATED --> [IN] --> o_ACCOUNTNO = [" + o.getACCOUNTNO() + "]");
            SmsSCBBO BO = new SmsSCBBO();
            BO.InsertPBL_VNPAY_COLLATED(o);
            LOGGER.info("InsertPBL_VNPAY_COLLATED --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pParnertid
     * @param pTransDate
     * @throws Exception
     */
    @Override
    public void UPDATE_AFTER_COLLATED(String pParnertid, Date pTransDate) throws Exception {
        try {
            LOGGER.info("UPDATE_AFTER_COLLATED --> [IN] --> pParnertid = [" + pParnertid + "], pTransDate = [" + pTransDate + "]");
            SmsSCBBO BO = new SmsSCBBO();
            BO.UPDATE_AFTER_COLLATED(pParnertid, pTransDate);
            LOGGER.info("UPDATE_AFTER_COLLATED --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param datecollate
     * @param filename
     * @throws Exception
     */
    @Override
    public void INSERT_DATE_COLLATED(Date datecollate, String filename) throws Exception {
        try {
            LOGGER.info("INSERT_DATE_COLLATED --> [IN] --> datecollate = [" + datecollate + "], filename = [" + filename + "]");
            SmsSCBBO BO = new SmsSCBBO();
            BO.INSERT_DATE_COLLATED(datecollate, filename);
            LOGGER.info("INSERT_DATE_COLLATED --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public Date GET_VNPAY_COLLATEDATE() throws Exception {
        try {
            LOGGER.info("GET_VNPAY_COLLATEDATE --> [IN] --> input is empty");
            SmsSCBBO BO = new SmsSCBBO();
            Date result = BO.GET_VNPAY_COLLATEDATE();
            LOGGER.info("GET_VNPAY_COLLATEDATE --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    //END OF VNPAY COLLATE   

    //Begin SML
    /**
     *
     * @param pTYPETRANSFER
     * @param pFROMNUMBER
     * @param pPROCESSINGCODE
     * @param pTRANSAMOUNT
     * @param pTRANSDATE
     * @param pAUDITNUMBER
     * @param pMERCHANTTYPE
     * @param pACQUIRINGCODE
     * @param pAUTHORIZATIONCODE
     * @param pRESPONSECODE
     * @param pTERMID
     * @param pCARDACCEPTTOR
     * @param pDESTNUMBER
     * @param pNARRATION
     * @param pBENID
     * @param pTYPEFUNCTION
     * @param pStatus
     * @param pRefCore
     * @param pCustNo
     * @param RefCORE_REFUND
     * @param pREF_NO_F37
     * @param pSETT_DATE_F15
     * @throws Exception
     */
    @Override
    public int InsertSMLLOG(String pTYPETRANSFER, String pFROMNUMBER, String pPROCESSINGCODE,
            BigDecimal pTRANSAMOUNT, String pTRANSDATE,
            String pAUDITNUMBER, String pMERCHANTTYPE,
            String pACQUIRINGCODE, String pAUTHORIZATIONCODE, String pRESPONSECODE,
            String pTERMID, String pCARDACCEPTTOR, String pDESTNUMBER,
            String pNARRATION, String pBENID, String pTYPEFUNCTION, String pStatus,
            String pRefCore, String pCustNo, String RefCORE_REFUND, String pREF_NO_F37, String pSETT_DATE_F15) throws Exception {
        try {
            LOGGER.info("InsertSMLLOG --> [IN] --> pTYPETRANSFER = [" + pTYPETRANSFER
                    + "], pFROMNUMBER = [" + pFROMNUMBER + "], pPROCESSINGCODE = [" + pPROCESSINGCODE
                    + "], pTRANSAMOUNT = [" + pTRANSAMOUNT + "], pTRANSDATE = [" + pTRANSDATE
                    + "], pAUDITNUMBER = [" + pAUDITNUMBER + "], pMERCHANTTYPE = [" + pMERCHANTTYPE
                    + "], pACQUIRINGCODE = [" + pACQUIRINGCODE + "], pAUTHORIZATIONCODE = ["
                    + pAUTHORIZATIONCODE + "], pRESPONSECODE = [" + pRESPONSECODE + "], pTERMID = ["
                    + pTERMID + "], pCARDACCEPTTOR = [" + pCARDACCEPTTOR + "], pDESTNUMBER = ["
                    + pDESTNUMBER + "], pNARRATION = [" + pNARRATION + "], pBENID = [" + pBENID
                    + "], pTYPEFUNCTION = [" + pTYPEFUNCTION + "], pStatus = [" + pStatus
                    + "], pRefCore = [" + pRefCore + "], pCustNo = [" + pCustNo
                    + "], RefCORE_REFUND = [" + RefCORE_REFUND + "], pREF_NO_F37 = [" + pREF_NO_F37
                    + "], pSETT_DATE_F15 = [" + pSETT_DATE_F15 + "]");
            SmartLinkBO BO = new SmartLinkBO();
            int result = BO.InsertIBTLOG(pTYPETRANSFER, pFROMNUMBER, pPROCESSINGCODE, pTRANSAMOUNT,
                    pTRANSDATE, pAUDITNUMBER, pMERCHANTTYPE, pACQUIRINGCODE,
                    pAUTHORIZATIONCODE, pRESPONSECODE, pTERMID, pCARDACCEPTTOR,
                    pDESTNUMBER, pNARRATION, pBENID, pTYPEFUNCTION,
                    pStatus, pRefCore, pCustNo, RefCORE_REFUND, pREF_NO_F37, pSETT_DATE_F15);
            LOGGER.info("InsertSMLLOG --> [OUT] -->" + result);
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            // throw new RemoteException(ex.getMessage(), ex);
        }
        return 0;
    }

    /**
     *
     * @param pCardNumber
     * @param pCardHolderName
     * @param pAmount
     * @return
     * @throws Exception
     */
    @Override
    public String[] getCardInfo(String pCardNumber, String pCardHolderName, BigDecimal pAmount) throws Exception {
        try {
            LOGGER.info("getCardInfo --> [IN] --> pCardNumber = [" + pCardNumber + "], pCardHolderName = [" + pCardHolderName + "], pAmount = [" + pAmount + "]");
            SmartLinkBO BO = new SmartLinkBO();
            String[] result = BO.getCardInfo(pCardNumber, pCardHolderName, pAmount);
            LOGGER.info("getCardInfo --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param pTypeTransfer
     * @return
     * @throws Exception
     */
    @Override
    public String getSMLAccount(String pTypeTransfer) throws Exception {
        try {
            LOGGER.info("getSMLAccount --> [IN] --> pTypeTransfer = [" + pTypeTransfer + "]");
            SmartLinkBO BO = new SmartLinkBO();
            String result = BO.getSMLAccount(pTypeTransfer);
            LOGGER.info("getSMLAccount --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String getAuditNumber() throws Exception {
        try {
            LOGGER.info("getAuditNumber --> [IN] --> input is empty");
            SmartLinkBO BO = new SmartLinkBO();
            String result = BO.getAuditNumber();
            LOGGER.info("getAuditNumber --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String getAuthorizationCode() throws Exception {
        try {
            LOGGER.info("getAuthorizationCode --> [IN] --> input is empty");
            SmartLinkBO BO = new SmartLinkBO();
            String result = BO.getAuthorizationCode();
            LOGGER.info("getAuthorizationCode --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param pCardNumber
     * @return
     * @throws Exception
     */
    @Override
    public String[] getCardStatus(String pCardNumber) throws Exception {
        try {
            LOGGER.info("getCardStatus --> [IN] --> pCardNumber = [" + pCardNumber + "]");
            SmartLinkBO BO = new SmartLinkBO();
            String[] result = BO.getCardStatus(pCardNumber);
            LOGGER.info("getCardStatus --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pAuditNumber
     * @return
     * @throws Exception
     */
    @Override
    public boolean checkAuditNumberOfSML(String pAuditNumber) throws Exception {
        try {
            LOGGER.info("checkAuditNumberOfSML --> [IN] --> pAuditNumber = [" + pAuditNumber + "]");
            SmartLinkBO BO = new SmartLinkBO();
            boolean result = BO.checkAuditNumberOfSML(pAuditNumber);
            LOGGER.info("checkAuditNumberOfSML --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pCustNo
     * @param pAmount
     * @return
     * @throws Exception
     */
    @Override
    public String checkLimitAmount(String pCustNo, BigDecimal pAmount) throws Exception {
        try {
            LOGGER.info("checkLimitAmount --> [IN] --> pCustNo = [" + pCustNo + "], pAmount = [" + pAmount + "]");
            SmartLinkBO BO = new SmartLinkBO();
            String result = BO.checkLimitAmount(pCustNo, pAmount);
            LOGGER.info("checkLimitAmount --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param sml
     * @throws Exception
     */
    @Override
    public void InsertSMLCollated(SMLCollate sml) throws Exception {
        try {
            LOGGER.info("InsertSMLCollated --> [IN] --> sml_ACCOUNTNO = [" + sml.getACCOUNTNO() + "]");
            SmartLinkBO BO = new SmartLinkBO();
            BO.InsertSMLCollated(sml);
            LOGGER.info("InsertSMLCollated --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pcollatedate
     * @param pfilename
     * @param pTypefile
     * @param pSumrecord
     * @throws Exception
     */
    @Override
    public void insertCollateDate(Date pcollatedate, String pfilename, String pTypefile, int pSumrecord) throws Exception {
        try {
            LOGGER.info("insertCollateDate --> [IN] --> pcollatedate = [" + pcollatedate + "], pfilename = [" + pfilename + "], pTypefile = [" + pTypefile + "], pSumrecord = [" + pSumrecord + "]");
            SmartLinkBO BO = new SmartLinkBO();
            BO.insertCollateDate(pcollatedate, pfilename, pTypefile, pSumrecord);
            LOGGER.info("insertCollateDate --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pFile
     * @return
     * @throws Exception
     */
    @Override
    public int checkFile(String pFile) throws Exception {
        try {
            LOGGER.info("checkFile --> [IN] --> pFile = [" + pFile + "]");
            SmartLinkBO BO = new SmartLinkBO();
            int result = BO.checkFile(pFile);
            LOGGER.info("checkFile --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pTypefile
     * @return
     * @throws Exception
     */
    @Override
    public List<SMLCollate> getOutCollate(String pTypefile) throws Exception {
        try {
            LOGGER.info("getOutCollate --> [IN] --> pTypefile = [" + pTypefile + "]");
            SmartLinkBO BO = new SmartLinkBO();
            List<SMLCollate> result = BO.getOutCollate(pTypefile);
            LOGGER.info("getOutCollate --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //BEGIN CONTACT CENTER
    /**
     *
     * @param function
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public String queryContactCenterInfo(String function, String param) throws Exception {
        LOGGER.info("queryContactCenterInfo --> [IN] --> function = [" + function + "], param = [" + param + "]");
        String result;
        FCCCustInfoBO fccCustInfoBO;
        CCFcdbBO fcdbBO;
        CWLiveBO cwLiveBO;
        CWDwhBO cwDwhBO;
        SmsSCBBO smsSCBBO;
        try {
            switch (function.toUpperCase()) {
                case "CC_GETCUSTOMERINFOBYCIF":
                    fccCustInfoBO = new FCCCustInfoBO();
                    result = fccCustInfoBO.GetCustomerInfoByCIF(param);
                    break;
                case "CC_GETCUSTOMERINFOBYIDCARD":
                    fccCustInfoBO = new FCCCustInfoBO();
                    result = fccCustInfoBO.GetCustomerInfoByIDCard(param);
                    break;
                case "CC_GETCUSTOMERINFOBYSODIENTHOAI":
                    fccCustInfoBO = new FCCCustInfoBO();
                    result = fccCustInfoBO.GetCustomerInfoByPhone(param);
                    break;
                case "CC_GETLISTACCOUNT":
                    fccCustInfoBO = new FCCCustInfoBO();
                    result = fccCustInfoBO.CC_GetListAccount(param);
                    break;
                case "CC_GETRECENTTRANSACTION":
                    fccCustInfoBO = new FCCCustInfoBO();
                    result = fccCustInfoBO.CC_GetRecentTransaction(param);
                    break;
                case "CC_GETTDACCOUNTS":
                    fccCustInfoBO = new FCCCustInfoBO();
                    result = fccCustInfoBO.CC_GetTDAccounts(param);
                    break;
                case "CC_GETTDACCOUNTDETAILS":
                    fccCustInfoBO = new FCCCustInfoBO();
                    result = fccCustInfoBO.CC_GetTDAccountDetails(param);
                    break;
                case "CC_GETTDACCOUNTTRANHISTDETAILS":
                    fccCustInfoBO = new FCCCustInfoBO();
                    result = fccCustInfoBO.CC_GetTDAccountTranHistDetails(param);
                    break;
                case "CC_GETCLACCOUNTS":
                    fccCustInfoBO = new FCCCustInfoBO();
                    result = fccCustInfoBO.CC_GetCLAccounts(param);
                    break;
                case "CC_GETCLACCOUNTDETAILS":
                    fccCustInfoBO = new FCCCustInfoBO();
                    result = fccCustInfoBO.CC_GetCLAccountDetails(param);
                    break;
                case "CC_GETCLHISTPAYMENTDETAILS":
                    fccCustInfoBO = new FCCCustInfoBO();
                    result = fccCustInfoBO.CC_GetCLHistPaymentDetails(param);
                    break;
                case "CC_GETCLACCOUNTPAYCALDETAILS":
                    fccCustInfoBO = new FCCCustInfoBO();
                    result = fccCustInfoBO.CC_GetCLAccountPayCalDetails(param);
                    break;
                case "CC_IB_CUST_INFO":
                    fcdbBO = new CCFcdbBO();
                    result = fcdbBO.CC_IB_CUST_INFO(param);
                    break;
                case "CC_SMS_CUST_INFO":
                    fcdbBO = new CCFcdbBO();
                    result = fcdbBO.CC_SMS_CUST_INFO(param);
                    break;
                case "CC_SMSALERT_CUST_INFO":
                    fcdbBO = new CCFcdbBO();
                    result = fcdbBO.CC_SMSALERT_CUST_INFO(param);
                    break;
                case "CC_SMS_ALERT_TD_INFO":
                    fcdbBO = new CCFcdbBO();
                    result = fcdbBO.CC_SMS_ALERT_TD_INFO(param);
                    break;
                case "CC_CUST_MBANKING_INFO":
                    fcdbBO = new CCFcdbBO();
                    result = fcdbBO.CC_CUST_MBANKING_INFO(param);
                    break;
                case "CC_GETTTHD_TRANS_HIST":
                    fcdbBO = new CCFcdbBO();
                    result = fcdbBO.CC_GetTTHD_TRANS_HIST(param);
                    break;
                case "CC_GETTTHD_AUTO_CONTRACT":
                    fcdbBO = new CCFcdbBO();
                    result = fcdbBO.CC_GetTTHD_AUTO_CONTRACT(param);
                    break;
                case "CC_GETINTERNALCARDINFO":
                    cwLiveBO = new CWLiveBO();
                    result = cwLiveBO.CC_GetInternalCardInfo(param);
                    break;
                case "CC_GETINTERNALCARDTRANSACTION":
                    cwLiveBO = new CWLiveBO();
                    result = cwLiveBO.CC_GetInternalCardTransaction(param);
                    break;
                case "CC_GETINTERNALCARDINFO_MC":
                    cwLiveBO = new CWLiveBO();
                    result = cwLiveBO.CC_GetInternalCardInfo_MC(param);
                    break;
                case "CC_GETINTERNALCARDINFO_MCDB":
                    cwLiveBO = new CWLiveBO();
                    result = cwLiveBO.CC_GetInternalCardInfo_MCDB(param);
                    break;
                case "CC_GETCARD_PROFILE":
                    cwLiveBO = new CWLiveBO();
                    result = cwLiveBO.CC_GetCard_Profile(param);
                    break;
                case "CC_GETAWARD_POINT":
                    cwLiveBO = new CWLiveBO();
                    result = cwLiveBO.CC_GetAward_Point(param);
                    break;
                case "CC_GETINTERNALCARDTRAN_MC":
                    cwLiveBO = new CWLiveBO();
                    result = cwLiveBO.CC_GetInternalCardTran_MC(param);
                    break;
                case "CC_GET_TT_SAOKE":
                    cwDwhBO = new CWDwhBO();
                    result = cwDwhBO.CC_GET_TT_SaoKe(param);
                    break;
                case "CC_GETCREDITPAYTRANSACTION":
                    cwDwhBO = new CWDwhBO();
                    result = cwDwhBO.CC_GetCreditPayTransaction(param);
                    break;
                case "CC_GETMONTH":
                    cwDwhBO = new CWDwhBO();
                    result = cwDwhBO.CC_GetMonth(param);
                    break;
                case "CC_GETSMSHIST":
                    fcdbBO = new CCFcdbBO();
                    result = fcdbBO.CC_GET_SMS_HIST(param);
                    break;
                case "CC_GETEMAILHIST":
                    smsSCBBO = new SmsSCBBO();
                    result = smsSCBBO.CC_GET_EMAIL_HIST(param);
                    break;
                case "GETPMT_INFO":
                    cwDwhBO = new CWDwhBO();
                    result = cwDwhBO.getPMT_INFO(param);
                    break;
                case "GETIPP_PAYMENT_PROCESSOR":
                    cwDwhBO = new CWDwhBO();
                    result = cwDwhBO.getIPP_PAYMENT_PROCESSOR(param.split("#")[0], param.split("#")[1], param.split("#")[2], param.split("#")[3]);
                    break;
                case "CC_GETIPP":
                    cwLiveBO = new CWLiveBO();
                    result = cwLiveBO.CC_GetIPP(param);
                    break;
                case "CC_GETIPPDETAIL":
                    cwLiveBO = new CWLiveBO();
                    result = cwLiveBO.CC_GetIPPDetail(param);
                    break;
                case "CC_GETIPPHIST":
                    cwLiveBO = new CWLiveBO();
                    result = cwLiveBO.CC_GetIPPHist(param);
                    break;
                default:
                    result = "";
                    break;
            }

            LOGGER.info("queryContactCenterInfo --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //END CONTACT CENTER
    /**
     *******************************************************************
     * BEGIN OF MOBILE BANKING
     *
     * @param custno
     * @return
     * @throws java.rmi.RemoteException
     */
    @Override
    public List GetAccountListByCustNo(String custno) throws RemoteException {
        /*
        try {
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.GetAccountListByCustNo(custno);
        } catch (Exception ex) {
LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }*/
        try {
            LOGGER.info("GetAccountListByCustNo --> [IN] --> custno = [" + custno + "]");
            FCCCoreBO fccCore = new FCCCoreBO();
            List listFCC = fccCore.GetAccountListByCustNoFCC(custno);
            /*
            CWLiveBO cwLiveBO = new CWLiveBO();
            List listCard = cwLiveBO.GetAccountListByCustNoCard(custno);
            
            if (listCard != null && listCard.size() > 0) {
                listFCC.addAll(listCard);
            }*/
            //Helper.writeLog(FCCCoreBO.class, Level.INFO, "GetAccountListByCustNoNew -" + custno);
            LOGGER.info("GetAccountListByCustNo --> [OUT] --> Size = [" + (listFCC == null ? null : listFCC.size()) + "]");
            return listFCC;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public List getTdAccountByAccountNo(String accountno) throws RemoteException {
        try {
            LOGGER.info("getTdAccountByAccountNo --> [IN] --> accountno = [" + accountno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            List result = fcdbBO.getTdAccountByAccountNo(accountno);
            LOGGER.info("getTdAccountByAccountNo --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public List getCASAccountHasLimitMB(String accountno) throws RemoteException {
        try {
            LOGGER.info("getCASAccountHasLimitMB --> [IN] --> accountno = [" + accountno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            List result = fcdbBO.getCASAccountHasLimitMB(accountno);
            LOGGER.info("getCASAccountHasLimitMB --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getLoanAccountByAccountNo(String accountno) throws RemoteException {
        try {
            LOGGER.info("getLoanAccountByAccountNo --> [IN] --> accountno = [" + accountno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getLoanAccountByAccountNo(accountno);
            LOGGER.info("getLoanAccountByAccountNo --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param unique_name
     * @param unique_id
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCustomerInfoForMB(String unique_name, String unique_id) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.getCustomerInfoForMB(unique_name, unique_id);
             */
            LOGGER.info("getCustomerInfoForMB --> [IN] --> unique_name = [" + unique_name + "], unique_id = [" + unique_id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getCustomerInfoForMB(unique_name, unique_id);
            LOGGER.info("getCustomerInfoForMB --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @param fromDate
     * @param toDate
     * @param srno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getTransationListByAccountNo(String accountno, String fromDate, String toDate, String srno) throws RemoteException {
        try {
            LOGGER.info("getTransationListByAccountNo --> [IN] --> accountno = [" + accountno + "], fromDate = [" + fromDate + "], toDate = [" + toDate + "], srno = [" + srno + "]");
            //FcdbBO fcdbBO = new FcdbBO();
            //ArrayList result = fcdbBO.getTransationListByAccountNo(accountno, fromDate, toDate, srno);
            DwhBO dwhBO = new DwhBO();
            ArrayList result = dwhBO.getTransationListByAccNew(accountno, fromDate, toDate, srno);

            LOGGER.info("getTransationListByAccountNo --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getTDTransationListByAccountNo(String accountno) throws RemoteException {
        try {
            LOGGER.info("getTDTransationListByAccountNo --> [IN] --> accountno = [" + accountno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getTDTransationListByAccountNo(accountno);
            LOGGER.info("getTDTransationListByAccountNo --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCLSCHEDULEINTPAID(String accountno) throws RemoteException {
        try {
            LOGGER.info("getCLSCHEDULEINTPAID --> [IN] --> accountno = [" + accountno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getCLSCHEDULEINTPAID(accountno);
            LOGGER.info("getCLSCHEDULEINTPAID --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCLSCHEDULEINTUNPAID(String accountno) throws RemoteException {
        try {
            LOGGER.info("getCLSCHEDULEINTUNPAID --> [IN] --> accountno = [" + accountno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getCLSCHEDULEINTUNPAID(accountno);
            LOGGER.info("getCLSCHEDULEINTUNPAID --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCLSCHEDULEPRIPAIDSCB(String accountno) throws RemoteException {
        try {
            LOGGER.info("getCLSCHEDULEPRIPAIDSCB --> [IN] --> accountno = [" + accountno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getCLSCHEDULEPRIPAIDSCB(accountno);
            LOGGER.info("getCLSCHEDULEPRIPAIDSCB --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCLSCHEDULEPRIUNPAIDSCB(String accountno) throws RemoteException {
        try {
            LOGGER.info("getCLSCHEDULEPRIUNPAIDSCB --> [IN] --> accountno = [" + accountno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getCLSCHEDULEPRIUNPAIDSCB(accountno);
            LOGGER.info("getCLSCHEDULEPRIUNPAIDSCB --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param bankCode
     * @return
     * @throws RemoteException
     */
    @Override
    public List getBeneBank(String bankCode) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.getBeneBank(bankCode);
             */
            LOGGER.info("getBeneBank --> [IN] --> bankCode = [" + bankCode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.getBeneBank(bankCode);
            LOGGER.info("getBeneBank --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param codCity
     * @return
     * @throws RemoteException
     */
    @Override
    public List getBankCity(String codCity) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.getBankCity(codCity);
             */
            LOGGER.info("getBankCity --> [IN] --> codCity = [" + codCity + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.getBankCity(codCity);
            LOGGER.info("getBankCity --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param branchcode
     * @param accountclass
     * @param amount
     * @param ccy
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAccountClassInfo(String branchcode, String accountclass, String amount, String ccy, String cif) throws RemoteException {
        try {
//            FcdbBO fcdbBO = new FcdbBO();
//            return fcdbBO.getAccountClassInfo(branchcode, accountclass, amount, ccy);
            LOGGER.info("getAccountClassInfo --> [IN] --> branchcode = [" + branchcode + "], accountclass = [" + accountclass + "], amount = [" + amount + "], ccy = [" + ccy + "], cif = [" + cif + "]");
            FCCCoreBO fccCore = new FCCCoreBO();
            ArrayList result = fccCore.getAccountClassInfo(branchcode, accountclass, amount, ccy, cif);
            LOGGER.info("getAccountClassInfo --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param branchcode
     * @param accountclass
     * @param amount
     * @param ccy
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAccountClassInfoNew(String branchcode, String accountclass, String amount, String ccy) throws RemoteException {
        try {
//            FcdbBO fcdbBO = new FcdbBO();
//            return fcdbBO.getAccountClassInfo(branchcode, accountclass, amount, ccy);
            LOGGER.info("getAccountClassInfoNew --> [IN] --> branchcode = [" + branchcode + "], accountclass = [" + accountclass + "], amount = [" + amount + "], ccy = [" + ccy + "]");
            FCCCoreBO fccCore = new FCCCoreBO();
            ArrayList result = fccCore.getAccountClassInfoNew(branchcode, accountclass, amount, ccy);
            LOGGER.info("getAccountClassInfoNew --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cardno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getMaterCardDetailByCardno(String cardno) throws RemoteException {
        try {
            LOGGER.info("getMaterCardDetailByCardno --> [IN] --> cardno = [" + cardno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getMaterCardDetailByCardno(cardno);
            LOGGER.info("getMaterCardDetailByCardno --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cardno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getTransactionMaterCardByCardno(String cardno) throws RemoteException {
        try {
            LOGGER.info("getTransactionMaterCardByCardno --> [IN] --> cardno = [" + cardno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getTransactionMaterCardByCardno(cardno);
            LOGGER.info("getTransactionMaterCardByCardno --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getTokenbyCustno(String custno) throws RemoteException {
        try {
            LOGGER.info("getTokenbyCustno --> [IN] --> custno = [" + custno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getTokenbyCustno(custno);
            LOGGER.info("getTokenbyCustno --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param serialno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList checkTokenbySerialno(String serialno) throws RemoteException {
        try {
            LOGGER.info("checkTokenbySerialno --> [IN] --> serialno = [" + serialno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.checkTokenbySerialno(serialno);
            LOGGER.info("checkTokenbySerialno --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList checkAmountBeforePay(String accountno) throws RemoteException {
        try {
            LOGGER.info("checkAmountBeforePay --> [IN] --> accountno = [" + accountno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.checkAmountBeforePay(accountno);
            LOGGER.info("checkAmountBeforePay --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList getExchangeRate() throws RemoteException {
        try {
            /*
            DominoBO dominoBO = new DominoBO();
            return dominoBO.getExchangeRate();
            
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.getExchangeRate();
             */
            LOGGER.info("getExchangeRate --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getExchangeRate();
            LOGGER.info("getExchangeRate --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList getGoldRate() throws RemoteException {
        try {
            /*
            DominoBO dominoBO = new DominoBO();
            return dominoBO.getGoldRate();
            
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.getGoldRate();
             */
            LOGGER.info("getGoldRate --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getGoldRate();
            LOGGER.info("getGoldRate --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ccy
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getInterestRate(String ccy) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.getInterestRate(ccy);
             */
            LOGGER.info("getInterestRate --> [IN] --> ccy = [" + ccy + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getInterestRate(ccy);
            LOGGER.info("getInterestRate --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param account_class
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getProductAccountClass(String account_class) throws RemoteException {
        try {
//            FcdbBO fcdbBO = new FcdbBO();
//            return fcdbBO.getProductAccountClass(account_class);
            LOGGER.info("getProductAccountClass --> [IN] --> account_class = [" + account_class + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getProductAccountClass(account_class);
            LOGGER.info("getProductAccountClass --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param serialno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getValidToken(String serialno) throws RemoteException {
        try {
            LOGGER.info("getValidToken --> [IN] --> serialno = [" + serialno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getValidToken(serialno);
            LOGGER.info("getValidToken --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param account_class
     * @return
     * @throws RemoteException
     */
    @Override
    public List GetTDAccountBeforeRedemtion(String account_class) throws RemoteException {
        try {
            LOGGER.info("GetTDAccountBeforeRedemtion --> [IN] --> account_class = [" + account_class + "]");
            FcdbBO fcdbBO = new FcdbBO();
            List result = fcdbBO.GetTDAccountBeforeRedemtion(account_class);
            LOGGER.info("GetTDAccountBeforeRedemtion --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList getAllListServiceTypeNotTopup() throws RemoteException {
        try {
            LOGGER.info("getAllListServiceTypeNotTopup --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getAllListServiceTypeNotTopup();
            LOGGER.info("getAllListServiceTypeNotTopup --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param username
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCustomerFromMobile(String username) throws RemoteException {
        try {
            LOGGER.info("getCustomerFromMobile --> [IN] --> username = [" + username + "]");
            VninfoBO vninfoBO = new VninfoBO();
            ArrayList result = vninfoBO.getCustomerFromMobile(username);
            LOGGER.info("getCustomerFromMobile --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cif
     * @param status
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getMobileUserFromCif(String cif, String status) throws RemoteException {
        try {
            LOGGER.info("getMobileUserFromCif --> [IN] --> cif = [" + cif + "], status = [" + status + "]");
            VninfoBO vninfoBO = new VninfoBO();
            ArrayList result = vninfoBO.getMobileUserFromCif(cif, status);
            LOGGER.info("getMobileUserFromCif --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param acccount
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getSttmCustAccountSyn(String acccount) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.getSttmCustAccountSyn(acccount);
             */
            LOGGER.info("getSttmCustAccountSyn --> [IN] --> acccount = [" + acccount + "]");
            FCCCoreBO fccCore = new FCCCoreBO();
            ArrayList listFCC = fccCore.getSttmCustAccountSynFCC(acccount);
            LOGGER.info("getSttmCustAccountSyn --> [OUT] --> Size = [" + (listFCC == null ? null : listFCC.size()) + "]");
            return listFCC;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    @Override
    public List GetTemplateFromFCDB(String custno) throws RemoteException {
        try {
            LOGGER.info("GetTemplateFromFCDB --> [IN] --> custno = [" + custno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            List result = fcdbBO.GetTemplateFromFCDB(custno);
            LOGGER.info("GetTemplateFromFCDB --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getBeneficiaryFromFCDB(String custno) throws RemoteException {
        try {
            LOGGER.info("getBeneficiaryFromFCDB --> [IN] --> custno = [" + custno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getBeneficiaryFromFCDB(custno);
            LOGGER.info("getBeneficiaryFromFCDB --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //duytxa08072015
    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public SmsThuphi getSmsThuphiById(String id) throws RemoteException {
        try {
            LOGGER.info("getSmsThuphiById --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            SmsThuphi result = smsscbBO.getSmsThuphiById(id);
            LOGGER.info("getSmsThuphiById --> [OUT] --> result_custno = [" + result.getCustno() + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    //endduytxa08072015

    /**
     *******************************************************************
     * END OF MOBILE BANKING
     *
     * @param pPartnerID
     * @param pCollatedDate
     * @throws java.rmi.RemoteException
     */
    //BEGIN PAYOO ONLINE COLLATED
    @Override
    public void InsertCollateddate(String pPartnerID, Date pCollatedDate) throws RemoteException {
        try {
            LOGGER.info("InsertCollateddate --> [IN] --> pPartnerID = [" + pPartnerID + "], pCollatedDate = [" + pCollatedDate + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.InsertCollateddate(pPartnerID, pCollatedDate);
            LOGGER.info("InsertCollateddate --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pPartnerID
     * @return
     * @throws RemoteException
     */
    @Override
    public Date getMaxCollateDate(String pPartnerID) throws RemoteException {
        try {
            LOGGER.info("getMaxCollateDate --> [IN] --> pPartnerID = [" + pPartnerID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            Date result = BO.getMaxCollateDate(pPartnerID);
            LOGGER.info("getMaxCollateDate --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //END PAYOO ONLINE COLLATED   
    //SML Online Payment 
    /**
     *
     * @param PartnerID
     * @param TransID
     * @param CardNumber
     * @param CardName
     * @param MerchantId
     * @param Amount
     * @param CCY
     * @param LocalDate
     * @param AddInfo
     * @param ChannelID
     * @param URLAuthen
     * @param pIDVerify
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] SMLEcomCheckData(String PartnerID, String TransID,
            String CardNumber,
            String CardName,
            String MerchantId,
            Double Amount, String CCY,
            String LocalDate,
            String AddInfo,
            String ChannelID,
            String URLAuthen,
            String pIDVerify) throws RemoteException {
        try {
            LOGGER.info("SMLEcomCheckData --> [IN] --> PartnerID = [" + PartnerID + "], TransID = ["
                    + TransID + "], CardNumber = [" + CardNumber + "], CardName = [" + CardName
                    + "], MerchantId = [" + MerchantId + "], Amount = [" + Amount + "], CCY = ["
                    + CCY + "], LocalDate = [" + LocalDate + "], AddInfo = [" + AddInfo
                    + "], ChannelID = [" + ChannelID + "], URLAuthen = [" + URLAuthen
                    + "], pIDVerify = [" + pIDVerify + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.SMLCheckData(PartnerID, TransID,
                    CardNumber,
                    CardName,
                    MerchantId,
                    Amount,
                    CCY,
                    LocalDate,
                    AddInfo,
                    ChannelID,
                    URLAuthen,
                    pIDVerify);
            LOGGER.info("SMLEcomCheckData --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param sml
     * @return
     * @throws RemoteException
     */
    @Override
    public int InsertSMLEcomCollated(SMLCollate sml) throws RemoteException {
        try {
            LOGGER.info("InsertSMLEcomCollated --> [IN] --> sml_ACCOUNTNO = [" + sml.getACCOUNTNO() + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.InsertSMLEcomCollated(sml);
            LOGGER.info("InsertSMLEcomCollated --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public List<SMLCollate> getOutSMLEcomCollate() throws Exception {
        try {
            LOGGER.info("getOutSMLEcomCollate --> [IN] --> input is empty");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<SMLCollate> result = BO.getOutSMLEcomCollate();
            LOGGER.info("getOutSMLEcomCollate --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pUserID
     * @return
     * @throws RemoteException
     */
    @Override
    public int checkTokenByUserID(String pUserID) throws RemoteException {
        try {
            LOGGER.info("checkTokenByUserID --> [IN] --> pUserID = [" + pUserID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.checkTokenByUserID(pUserID);
            LOGGER.info("checkTokenByUserID --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //NOP THUE DIEN TU
    /*
     begin: NTDT
     date: 23/Mar/2015
     */
    /**
     *
     * @param pMA_GDICH
     * @param pNGAY_GUI_GDICH
     * @param pMA_GDICH_TCHIEU
     * @param pPBAN_TLIEU_XML
     * @param pMST
     * @param pTEN_NNT
     * @param pDIACHI_NNT
     * @param pMA_CQT
     * @param pEMAIL_NNT
     * @param pSDT_NNT
     * @param pTEN_LHE_NTHUE
     * @param pSERIAL_CERT_NTHUE
     * @param pSUBJECT_CERT_NTHUE
     * @param pISSUER_CERT_NTHUE
     * @param pMA_NHANG
     * @param pTEN_NHANG
     * @param pVAN_ID
     * @param pTEN_TVAN
     * @param pNGAY_GUI
     * @param pLOAITHONGTIN
     * @param pMSGID
     * @param pTRANSCODE
     * @param pCKS
     * @return
     * @throws Exception
     */
    @Override
    public String[] NTDT_InsertTHONGTIN_NNT(String pMA_GDICH,
            String pNGAY_GUI_GDICH,
            String pMA_GDICH_TCHIEU,
            String pPBAN_TLIEU_XML,
            String pMST,
            String pTEN_NNT,
            String pDIACHI_NNT,
            String pMA_CQT,
            String pEMAIL_NNT,
            String pSDT_NNT,
            String pTEN_LHE_NTHUE,
            String pSERIAL_CERT_NTHUE,
            String pSUBJECT_CERT_NTHUE,
            String pISSUER_CERT_NTHUE,
            String pMA_NHANG,
            String pTEN_NHANG,
            String pVAN_ID,
            String pTEN_TVAN,
            String pNGAY_GUI,
            String pLOAITHONGTIN,
            String pMSGID,
            String pTRANSCODE,
            String pCKS) throws Exception {
        try {
            LOGGER.info("NTDT_InsertTHONGTIN_NNT --> [IN] --> pMA_GDICH = [" + pMA_GDICH
                    + "], pNGAY_GUI_GDICH = [" + pNGAY_GUI_GDICH + "], pMA_GDICH_TCHIEU = ["
                    + pMA_GDICH_TCHIEU + "], pPBAN_TLIEU_XML = [" + pPBAN_TLIEU_XML + "], pMST = ["
                    + pMST + "], pTEN_NNT = [" + pTEN_NNT + "], pDIACHI_NNT = [" + pDIACHI_NNT
                    + "], pMA_CQT = [" + pMA_CQT + "], pEMAIL_NNT = [" + pEMAIL_NNT + "], pSDT_NNT = ["
                    + pSDT_NNT + "], pTEN_LHE_NTHUE = [" + pTEN_LHE_NTHUE + "], pSERIAL_CERT_NTHUE = ["
                    + pSERIAL_CERT_NTHUE + "], pSUBJECT_CERT_NTHUE = [" + pSUBJECT_CERT_NTHUE
                    + "], pISSUER_CERT_NTHUE = [" + pISSUER_CERT_NTHUE + "], pMA_NHANG = [" + pMA_NHANG
                    + "], pTEN_NHANG = [" + pTEN_NHANG + "], pVAN_ID = [" + pVAN_ID
                    + "], pTEN_TVAN = [" + pTEN_TVAN + "], pNGAY_GUI = [" + pNGAY_GUI
                    + "], pLOAITHONGTIN = [" + pLOAITHONGTIN + "], pMSGID = [" + pMSGID
                    + "], pTRANSCODE = [" + pTRANSCODE + "], pCKS = [" + pCKS + "]");
            NTDTBO BO = new NTDTBO();
            String[] result = BO.InsertTHONGTIN_NNT(pMA_GDICH, pNGAY_GUI_GDICH, pMA_GDICH_TCHIEU, pPBAN_TLIEU_XML, pMST, pTEN_NNT, pDIACHI_NNT, pMA_CQT, pEMAIL_NNT, pSDT_NNT, pTEN_LHE_NTHUE, pSERIAL_CERT_NTHUE, pSUBJECT_CERT_NTHUE, pISSUER_CERT_NTHUE, pMA_NHANG, pTEN_NHANG, pVAN_ID, pTEN_TVAN, pNGAY_GUI, pLOAITHONGTIN, pMSGID, pTRANSCODE, pCKS);
            LOGGER.info("NTDT_InsertTHONGTIN_NNT --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*
     pTypedata: INSERT/UPDATE
     */
    /**
     *
     * @param pAccountNo
     * @param pID_NNT
     * @param pIsDeleted
     * @param pAddinfo
     * @param pUSERID
     * @param pBRANCHID
     * @return
     * @throws Exception
     */
    @Override
    public String NTDT_InsertAccountNo(String pAccountNo,
            int pID_NNT,
            int pIsDeleted,
            String pAddinfo,
            String pUSERID,
            String pBRANCHID) throws Exception {
        try {
            LOGGER.info("NTDT_InsertAccountNo --> [IN] --> pAccountNo = [" + pAccountNo
                    + "], pID_NNT = [" + pID_NNT + "], pIsDeleted = [" + pIsDeleted
                    + "], pAddinfo = [" + pAddinfo + "], pUSERID = [" + pUSERID + "], pBRANCHID = [" + pBRANCHID + "]");
            NTDTBO BO = new NTDTBO();
            String result = BO.InsertAccountNo(pAccountNo, pID_NNT, pIsDeleted, pAddinfo, pUSERID, pBRANCHID);
            LOGGER.info("NTDT_InsertAccountNo --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_NNT
     * @throws Exception
     */
    @Override
    public void NTDT_THONGBAO_THAYDOI_STK(int pID_NNT) throws Exception {
        try {
            LOGGER.info("NTDT_THONGBAO_THAYDOI_STK --> [IN] --> pID_NNT = [" + pID_NNT + "]");
            NTDTBO BO = new NTDTBO();
            BO.THONGBAO_THAYDOI_STK(pID_NNT);
            LOGGER.info("NTDT_THONGBAO_THAYDOI_STK --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMST
     * @param pLYDONGUNG
     * @param pCLOSEDATE
     * @param MA_TBAO
     * @param MAU_TBAO
     * @param SO_TBAO
     * @param TEN_TBAO
     * @param NGAY_TBAO
     * @throws Exception
     */
    @Override
    public void NTDT_CloseNNT(String pMST,
            String pLYDONGUNG,
            String pCLOSEDATE,
            String MA_TBAO,
            String MAU_TBAO,
            String SO_TBAO,
            String TEN_TBAO,
            String NGAY_TBAO) throws Exception {
        try {
            LOGGER.info("NTDT_CloseNNT --> [IN] --> pMST = [" + pMST + "], pLYDONGUNG = [" + pLYDONGUNG
                    + "], pCLOSEDATE = [" + pCLOSEDATE + "], MA_TBAO = [" + MA_TBAO + "], MAU_TBAO = ["
                    + MAU_TBAO + "], SO_TBAO = [" + SO_TBAO + "], TEN_TBAO = [" + TEN_TBAO
                    + "], NGAY_TBAO = [" + NGAY_TBAO + "]");
            NTDTBO BO = new NTDTBO();
            BO.CloseNNT(pMST, pLYDONGUNG, pCLOSEDATE, MA_TBAO, MAU_TBAO, SO_TBAO, TEN_TBAO, NGAY_TBAO);
            LOGGER.info("NTDT_CloseNNT --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_TT_NNT
     * @param pCheckStatus
     * @param pAddinfo
     * @param pUserUD
     * @param pBranchID
     * @throws Exception
     */
    @Override
    public void NTDT_CheckTT_NNT(int pID_TT_NNT,
            String pCheckStatus,
            String pAddinfo,
            String pUserUD,
            String pBranchID) throws Exception {
        try {
            LOGGER.info("NTDT_CheckTT_NNT --> [IN] --> pID_TT_NNT = [" + pID_TT_NNT
                    + "], pCheckStatus = [" + pCheckStatus + "], pAddinfo = [" + pAddinfo
                    + "], pUserUD = [" + pUserUD + "], pBranchID = [" + pBranchID + "]");
            NTDTBO BO = new NTDTBO();
            BO.CheckTT_NNT(pID_TT_NNT, pCheckStatus, pAddinfo, pUserUD, pBranchID);
            LOGGER.info("NTDT_CheckTT_NNT --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_TT_NNT
     * @param pUserUD
     * @param pID_NNT
     * @throws Exception
     */
    @Override
    public void NTDT_Confirm_TT_NNT(int pID_TT_NNT,
            String pUserUD,
            int pID_NNT) throws Exception {
        try {
            LOGGER.info("NTDT_Confirm_TT_NNT --> [IN] --> pID_TT_NNT = [" + pID_TT_NNT
                    + "], pUserUD = [" + pUserUD + "], pID_NNT = [" + pID_NNT + "]");
            NTDTBO BO = new NTDTBO();
            BO.Confirm_TT_NNT(pID_TT_NNT, pUserUD, pID_NNT);
            LOGGER.info("NTDT_Confirm_TT_NNT --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMA_GDICH
     * @param pNGAY_GUI_GDICH
     * @param pMA_GDICH_TCHIEU
     * @param pMAHIEU_CTU
     * @param pSO_CTU
     * @param pPBAN_TLIEU_XML
     * @param pID_CTU
     * @param pSO_GNT
     * @param pMA_CTU
     * @param pHTHUC_NOP
     * @param pMST_NNOP
     * @param pTEN_NNOP
     * @param pDIACHI_NNOP
     * @param pMA_CQT
     * @param pTEN_CQT
     * @param pMA_XA_NNOP
     * @param pTEN_XA_NNOP
     * @param pMA_HUYEN_NNOP
     * @param pTEN_HUYEN_NNOP
     * @param pMA_TINH_NNOP
     * @param pTEN_TINH_NNOP
     * @param pMST_NTHAY
     * @param pTEN_NTHAY
     * @param pDIACHI_NTHAY
     * @param pTEN_HUYEN_NTHAY
     * @param pTEN_TINH_NTHAY
     * @param pMA_NHANG_NOP
     * @param pTEN_NHANG_NOP
     * @param pSTK_NHANG_NOP
     * @param pMA_HIEU_KBAC
     * @param pTEN_KBAC
     * @param pMA_TINH_KBAC
     * @param pTEN_TINH_KBAC
     * @param pLOAI_TK_THU
     * @param pTEN_TK_THU
     * @param pSTK_THU
     * @param pID_TK_KNGHI
     * @param pTK_KNGHI
     * @param pMA_CQTHU
     * @param pTEN_CQTHU
     * @param pNGAY_LAP
     * @param pTONG_TIEN
     * @param pVAN_ID
     * @param pMA_DBHC_THU
     * @param pTEN_DBHC_THU
     * @param pMA_LOAI_THUE
     * @param pMSGID
     * @param pTRANSCODE
     * @param pCKS
     * @return
     * @throws Exception
     */
    @Override
    public String[] NTDT_INSERT_GNT(String pMA_GDICH,
            String pNGAY_GUI_GDICH,
            String pMA_GDICH_TCHIEU,
            String pMAHIEU_CTU,
            String pSO_CTU,
            String pPBAN_TLIEU_XML,
            String pID_CTU,
            String pSO_GNT,
            String pMA_CTU,
            String pHTHUC_NOP,
            String pMST_NNOP,
            String pTEN_NNOP,
            String pDIACHI_NNOP,
            String pMA_CQT,
            String pTEN_CQT,
            String pMA_XA_NNOP,
            String pTEN_XA_NNOP,
            String pMA_HUYEN_NNOP,
            String pTEN_HUYEN_NNOP,
            String pMA_TINH_NNOP,
            String pTEN_TINH_NNOP,
            String pMST_NTHAY,
            String pTEN_NTHAY,
            String pDIACHI_NTHAY,
            String pTEN_HUYEN_NTHAY,
            String pTEN_TINH_NTHAY,
            String pMA_NHANG_NOP,
            String pTEN_NHANG_NOP,
            String pSTK_NHANG_NOP,
            String pMA_HIEU_KBAC,
            String pTEN_KBAC,
            String pMA_TINH_KBAC,
            String pTEN_TINH_KBAC,
            String pLOAI_TK_THU,
            String pTEN_TK_THU,
            String pSTK_THU,
            String pID_TK_KNGHI,
            String pTK_KNGHI,
            String pMA_CQTHU,
            String pTEN_CQTHU,
            String pNGAY_LAP,
            String pTONG_TIEN,
            String pVAN_ID,
            String pMA_DBHC_THU,
            String pTEN_DBHC_THU,
            String pMA_LOAI_THUE,
            String pMSGID,
            String pTRANSCODE,
            String pCKS) throws Exception {

        try {
            LOGGER.info("NTDT_INSERT_GNT --> [IN] --> pMA_GDICH = [" + pMA_GDICH
                    + "], pNGAY_GUI_GDICH = [" + pNGAY_GUI_GDICH + "], pMA_GDICH_TCHIEU = ["
                    + pMA_GDICH_TCHIEU + "], pMAHIEU_CTU = [" + pMAHIEU_CTU + "], pSO_CTU = ["
                    + pSO_CTU + "], pPBAN_TLIEU_XML = [" + pPBAN_TLIEU_XML + "], pID_CTU = [" + pID_CTU + "]");
            NTDTBO BO = new NTDTBO();
            String[] result = BO.INSERT_GNT(pMA_GDICH, pNGAY_GUI_GDICH, pMA_GDICH_TCHIEU, pMAHIEU_CTU, pSO_CTU, pPBAN_TLIEU_XML, pID_CTU, pSO_GNT, pMA_CTU, pHTHUC_NOP, pMST_NNOP, pTEN_NNOP, pDIACHI_NNOP, pMA_CQT, pTEN_CQT, pMA_XA_NNOP, pTEN_XA_NNOP, pMA_HUYEN_NNOP, pTEN_HUYEN_NNOP, pMA_TINH_NNOP, pTEN_TINH_NNOP, pMST_NTHAY, pTEN_NTHAY, pDIACHI_NTHAY, pTEN_HUYEN_NTHAY, pTEN_TINH_NTHAY, pMA_NHANG_NOP, pTEN_NHANG_NOP, pSTK_NHANG_NOP, pMA_HIEU_KBAC, pTEN_KBAC, pMA_TINH_KBAC, pTEN_TINH_KBAC, pLOAI_TK_THU, pTEN_TK_THU, pSTK_THU, pID_TK_KNGHI, pTK_KNGHI, pMA_CQTHU, pTEN_CQTHU, pNGAY_LAP, pTONG_TIEN, pVAN_ID, pMA_DBHC_THU, pTEN_DBHC_THU, pMA_LOAI_THUE, pMSGID, pTRANSCODE, pCKS);
            LOGGER.info("NTDT_INSERT_GNT --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_GNT
     * @param pID_CTU_CTIET
     * @param pID_CTU
     * @param pNDUNG_NOP
     * @param pMA_NDKT
     * @param pMA_CHUONG
     * @param pKY_THUE
     * @param pTIEN_PNOP
     * @param pGHI_CHU
     * @throws Exception
     */
    @Override
    public void NTDT_INSERT_CHUNGTU_CHIIET(int pID_GNT,
            String pID_CTU_CTIET,
            String pID_CTU,
            String pNDUNG_NOP,
            String pMA_NDKT,
            String pMA_CHUONG,
            String pKY_THUE,
            String pTIEN_PNOP,
            String pGHI_CHU) throws Exception {
        try {
            LOGGER.info("NTDT_INSERT_CHUNGTU_CHIIET --> [IN] --> pID_GNT = [" + pID_GNT
                    + "], pID_CTU_CTIET = [" + pID_CTU_CTIET + "], pID_CTU = [" + pID_CTU
                    + "], pNDUNG_NOP = [" + pNDUNG_NOP + "], pMA_NDKT = [" + pMA_NDKT
                    + "], pMA_CHUONG = [" + pMA_CHUONG + "], pKY_THUE = [" + pKY_THUE
                    + "], pTIEN_PNOP = [" + pTIEN_PNOP + "], pGHI_CHU = [" + pGHI_CHU + "]");
            NTDTBO BO = new NTDTBO();
            BO.INSERT_CHUNGTU_CHIIET(pID_GNT, pID_CTU_CTIET, pID_CTU, pNDUNG_NOP, pMA_NDKT, pMA_CHUONG, pKY_THUE, pTIEN_PNOP, pGHI_CHU);
            LOGGER.info("NTDT_INSERT_CHUNGTU_CHIIET --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param pID_THONGBAO
     * @param pMESS_CODE
     * @param pMESS_CONTENT
     * @throws Exception
     */
    @Override
    public void NTDT_CONFIRMTHONGBAO(String pID_THONGBAO,
            String pMESS_CODE,
            String pMESS_CONTENT) throws Exception {
        try {
            LOGGER.info("NTDT_CONFIRMTHONGBAO --> [IN] --> pID_THONGBAO = [" + pID_THONGBAO
                    + "], pMESS_CODE = [" + pMESS_CODE + "], pMESS_CONTENT = [" + pMESS_CONTENT + "]");
            NTDTBO BO = new NTDTBO();
            BO.CONFIRMTHONGBAO(pID_THONGBAO, pMESS_CODE, pMESS_CONTENT);
            LOGGER.info("NTDT_CONFIRMTHONGBAO --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_NNT
     * @param pIsApproved
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList NTDT_GET_ACCOUNNO(int pID_NNT, int pIsApproved) throws Exception {
        try {
            LOGGER.info("NTDT_GET_ACCOUNNO --> [IN] --> pID_NNT = [" + pID_NNT + "], pIsApproved = [" + pIsApproved + "]");
            NTDTBO BO = new NTDTBO();
            ArrayList result = BO.GET_ACCOUNNO(pID_NNT, pIsApproved);
            LOGGER.info("NTDT_GET_ACCOUNNO --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws Exception
     */
    @Override
    public List<NTDT_NNT> NTDT_GUITHONGBAO(String pLOAITHONGBAO, int pID_REF) throws Exception {
        try {
            LOGGER.info("NTDT_GUITHONGBAO --> [IN] --> pLOAITHONGBAO = [" + pLOAITHONGBAO + "], pID_REF = [" + pID_REF + "]");
            NTDTBO BO = new NTDTBO();
            List<NTDT_NNT> result = BO.GUITHONGBAO(pLOAITHONGBAO, pID_REF);
            LOGGER.info("NTDT_GUITHONGBAO --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws Exception
     */
    @Override
    public List<NTDT_TDSTK> NTDT_GUITHONGBAO_STK(String pLOAITHONGBAO, int pID_REF) throws Exception {
        try {
            LOGGER.info("NTDT_GUITHONGBAO_STK --> [IN] --> pLOAITHONGBAO = [" + pLOAITHONGBAO + "], pID_REF = [" + pID_REF + "]");
            NTDTBO BO = new NTDTBO();
            List<NTDT_TDSTK> result = BO.GUITHONGBAO_STK(pLOAITHONGBAO, pID_REF);
            LOGGER.info("NTDT_GUITHONGBAO_STK --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pLOAITHONGBAO
     * @param pID_REF
     * @return
     * @throws Exception
     */
    @Override
    public List<NTDT_GNT> NTDT_GUITHONGBAO_GNT(String pLOAITHONGBAO, int pID_REF) throws Exception {
        try {
            LOGGER.info("NTDT_GUITHONGBAO_GNT --> [IN] --> pLOAITHONGBAO = [" + pLOAITHONGBAO + "], pID_REF = [" + pID_REF + "]");
            NTDTBO BO = new NTDTBO();
            List<NTDT_GNT> result = BO.GUITHONGBAO_GNT(pLOAITHONGBAO, pID_REF);
            LOGGER.info("NTDT_GUITHONGBAO_STK --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //pID_DOICHIEU_GD     out number
    /**
     *
     * @param pPBAN_TLIEU_XML_DC
     * @param pMA_GDICH_DC
     * @param pNGAY_GDICH_DC
     * @param pMA_NHANG_DC
     * @param pNGAY_DC
     * @param pTU_NGAY_DC
     * @param pDEN_NGAY_DC
     * @param pMA_GDICH_TCHIEU_DC
     * @param pMSGID
     * @param pTRANSCODE
     * @return
     * @throws Exception
     */
    @Override
    public int NTDT_INSERT_DOICHIEU_GD(String pPBAN_TLIEU_XML_DC,
            String pMA_GDICH_DC,
            String pNGAY_GDICH_DC,
            String pMA_NHANG_DC,
            String pNGAY_DC,
            String pTU_NGAY_DC,
            String pDEN_NGAY_DC,
            String pMA_GDICH_TCHIEU_DC,
            String pMSGID,
            String pTRANSCODE) throws Exception {
        try {
            LOGGER.info("NTDT_INSERT_DOICHIEU_GD --> [IN] --> pPBAN_TLIEU_XML_DC = [" + pPBAN_TLIEU_XML_DC
                    + "], pMA_GDICH_DC = [" + pMA_GDICH_DC + "], pNGAY_GDICH_DC = [" + pNGAY_GDICH_DC
                    + "], pMA_NHANG_DC = [" + pMA_NHANG_DC + "], pNGAY_DC = [" + pNGAY_DC
                    + "], pTU_NGAY_DC = [" + pTU_NGAY_DC + "], pDEN_NGAY_DC = [" + pDEN_NGAY_DC
                    + "], pMA_GDICH_TCHIEU_DC = [" + pMA_GDICH_TCHIEU_DC + "], pMSGID = [" + pMSGID
                    + "], pTRANSCODE = [" + pTRANSCODE + "]");
            NTDTBO BO = new NTDTBO();
            int result = BO.INSERT_DOICHIEU_GD(pPBAN_TLIEU_XML_DC, pMA_GDICH_DC, pNGAY_GDICH_DC, pMA_NHANG_DC, pNGAY_DC, pTU_NGAY_DC, pDEN_NGAY_DC, pMA_GDICH_TCHIEU_DC, pMSGID, pTRANSCODE);
            LOGGER.info("NTDT_INSERT_DOICHIEU_GD --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pMA_GDICH
     * @param pNGAY_GUI_GDICH
     * @param pNGAY_NOP_GNT
     * @param pNGAY_NOP_THUE
     * @param pTTHAI_GDICH
     * @param pTTHAI_CTU
     * @param pMAHIEU_CTU
     * @param pSO_CTU
     * @param pPBAN_TLIEU_XML
     * @param pID_CTU
     * @param pSO_GNT
     * @param pMA_CTU
     * @param pHTHUC_NOP
     * @param pMST_NNOP
     * @param pTEN_NNOP
     * @param pMA_CQT
     * @param pTEN_CQT
     * @param pDIACHI_NNOP
     * @param pMA_XA_NNOP
     * @param pTEN_XA_NNOP
     * @param pMA_HUYEN_NNOP
     * @param pTEN_HUYEN_NNOP
     * @param pMA_TINH_NNOP
     * @param pTEN_TINH_NNOP
     * @param pMST_NTHAY
     * @param pTEN_NTHAY
     * @param pDIACHI_NTHAY
     * @param pTEN_HUYEN_NTHAY
     * @param pTEN_TINH_NTHAY
     * @param pMA_NHANG_NOP
     * @param pTEN_NHANG_NOP
     * @param pSTK_NHANG_NOP
     * @param pMA_HIEU_KBAC
     * @param pTEN_KBAC
     * @param pMA_TINH_KBAC
     * @param pTEN_TINH_KBAC
     * @param pLOAI_TK_THU
     * @param pTEN_TK_THU
     * @param pSTK_THU
     * @param pID_TK_KNGHI
     * @param pTK_KNGHI
     * @param pMA_CQTHU
     * @param pTEN_CQTHU
     * @param pNGAY_LAP
     * @param pTONG_TIEN
     * @param pVAN_ID
     * @return
     * @throws Exception
     */
    @Override
    public int NTDT_INSERT_DOICHIEU_GNT(int pID_DOICHIEU_GD,
            String pMA_GDICH,
            String pNGAY_GUI_GDICH,
            String pNGAY_NOP_GNT,
            String pNGAY_NOP_THUE,
            String pTTHAI_GDICH,
            String pTTHAI_CTU,
            String pMAHIEU_CTU,
            String pSO_CTU,
            String pPBAN_TLIEU_XML,
            String pID_CTU,
            String pSO_GNT,
            String pMA_CTU,
            String pHTHUC_NOP,
            String pMST_NNOP,
            String pTEN_NNOP,
            String pMA_CQT,
            String pTEN_CQT,
            String pDIACHI_NNOP,
            String pMA_XA_NNOP,
            String pTEN_XA_NNOP,
            String pMA_HUYEN_NNOP,
            String pTEN_HUYEN_NNOP,
            String pMA_TINH_NNOP,
            String pTEN_TINH_NNOP,
            String pMST_NTHAY,
            String pTEN_NTHAY,
            String pDIACHI_NTHAY,
            String pTEN_HUYEN_NTHAY,
            String pTEN_TINH_NTHAY,
            String pMA_NHANG_NOP,
            String pTEN_NHANG_NOP,
            String pSTK_NHANG_NOP,
            String pMA_HIEU_KBAC,
            String pTEN_KBAC,
            String pMA_TINH_KBAC,
            String pTEN_TINH_KBAC,
            String pLOAI_TK_THU,
            String pTEN_TK_THU,
            String pSTK_THU,
            String pID_TK_KNGHI,
            String pTK_KNGHI,
            String pMA_CQTHU,
            String pTEN_CQTHU,
            String pNGAY_LAP,
            String pTONG_TIEN,
            String pVAN_ID) throws Exception {
        try {
            LOGGER.info("NTDT_INSERT_DOICHIEU_GNT --> [IN] --> pID_DOICHIEU_GD = [" + pID_DOICHIEU_GD + "]");
            NTDTBO BO = new NTDTBO();
            int result = BO.INSERT_DOICHIEU_GNT(pID_DOICHIEU_GD, pMA_GDICH, pNGAY_GUI_GDICH, pNGAY_NOP_GNT, pNGAY_NOP_THUE, pTTHAI_GDICH, pTTHAI_CTU, pMAHIEU_CTU, pSO_CTU, pPBAN_TLIEU_XML, pID_CTU, pSO_GNT, pMA_CTU, pHTHUC_NOP, pMST_NNOP, pTEN_NNOP, pMA_CQT, pTEN_CQT, pDIACHI_NNOP, pMA_XA_NNOP, pTEN_XA_NNOP, pMA_HUYEN_NNOP, pTEN_HUYEN_NNOP, pMA_TINH_NNOP, pTEN_TINH_NNOP, pMST_NTHAY, pTEN_NTHAY, pDIACHI_NTHAY, pTEN_HUYEN_NTHAY, pTEN_TINH_NTHAY, pMA_NHANG_NOP, pTEN_NHANG_NOP, pSTK_NHANG_NOP, pMA_HIEU_KBAC, pTEN_KBAC, pMA_TINH_KBAC, pTEN_TINH_KBAC, pLOAI_TK_THU, pTEN_TK_THU, pSTK_THU, pID_TK_KNGHI, pTK_KNGHI, pMA_CQTHU, pTEN_CQTHU, pNGAY_LAP, pTONG_TIEN, pVAN_ID);
            LOGGER.info("NTDT_INSERT_DOICHIEU_GNT --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param pID_CTU_CTIET
     * @param pID_CTU
     * @param pNDUNG_NOP
     * @param pMA_NDKT
     * @param pMA_CHUONG
     * @param pTIEN_PNOP
     * @param pID_DOICHIEU_GNT
     * @throws Exception
     */
    @Override
    public void NTDT_INSERT_DOICHIEU_GNT_CHITIET(String pID_CTU_CTIET,
            String pID_CTU,
            String pNDUNG_NOP,
            String pMA_NDKT,
            String pMA_CHUONG,
            String pTIEN_PNOP,
            int pID_DOICHIEU_GNT) throws Exception {
        try {
            LOGGER.info("NTDT_INSERT_DOICHIEU_GNT_CHITIET --> [IN] --> pID_CTU_CTIET = [" + pID_CTU_CTIET
                    + "], pID_CTU = [" + pID_CTU + "], pNDUNG_NOP = [" + pNDUNG_NOP + "], pMA_NDKT = ["
                    + pMA_NDKT + "], pMA_CHUONG = [" + pMA_CHUONG + "], pTIEN_PNOP = [" + pTIEN_PNOP
                    + "], pID_DOICHIEU_GNT = [" + pID_DOICHIEU_GNT + "]");
            NTDTBO BO = new NTDTBO();
            BO.INSERT_DOICHIEU_GNT_CHITIET(pID_CTU_CTIET, pID_CTU, pNDUNG_NOP, pMA_NDKT, pMA_CHUONG, pTIEN_PNOP, pID_DOICHIEU_GNT);
            LOGGER.info("NTDT_INSERT_DOICHIEU_GNT_CHITIET --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pMABAOCAO
     * @return
     * @throws RemoteException
     */
    @Override
    public List<NTDT_DOICHIEUGD> NTDT_BAOCAODOICHIEU(int pID_DOICHIEU_GD, String pMABAOCAO) throws RemoteException {
        try {
            LOGGER.info("NTDT_BAOCAODOICHIEU --> [IN] --> pID_DOICHIEU_GD = [" + pID_DOICHIEU_GD + "], pMABAOCAO = [" + pMABAOCAO + "]");
            NTDTBO BO = new NTDTBO();
            List<NTDT_DOICHIEUGD> result = BO.BAOCAODOICHIEU(pID_DOICHIEU_GD, pMABAOCAO);
            LOGGER.info("NTDT_BAOCAODOICHIEU --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pReportType
     * @return
     * @throws RemoteException
     */
    @Override
    public List<NTDT_BCDC_02> NTDT_BCDC_02(int pID_DOICHIEU_GD, String pReportType) throws RemoteException {
        try {
            LOGGER.info("NTDT_BCDC_02 --> [IN] --> pID_DOICHIEU_GD = [" + pID_DOICHIEU_GD + "], pReportType = [" + pReportType + "]");
            NTDTBO BO = new NTDTBO();
            List<NTDT_BCDC_02> result = BO.BCDC_02(pID_DOICHIEU_GD, pReportType);
            LOGGER.info("NTDT_BCDC_02 --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pReportType
     * @return
     * @throws RemoteException
     */
    @Override
    public List<NTDT_BCDC_05> NTDT_BCDC_05(int pID_DOICHIEU_GD, String pReportType) throws RemoteException {
        try {
            LOGGER.info("NTDT_BCDC_05 --> [IN] --> pID_DOICHIEU_GD = [" + pID_DOICHIEU_GD + "], pReportType = [" + pReportType + "]");
            NTDTBO BO = new NTDTBO();
            List<NTDT_BCDC_05> result = BO.BCDC_05(pID_DOICHIEU_GD, pReportType);
            LOGGER.info("NTDT_BCDC_05 --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMST
     * @param pSTATUS
     * @return
     * @throws RemoteException
     */
    @Override
    public List<NTDT_searchNNT> NTDT_SearchNNT(String pMST, String pSTATUS) throws RemoteException {
        try {
            LOGGER.info("NTDT_SearchNNT --> [IN] --> pMST = [" + pMST + "], pSTATUS = [" + pSTATUS + "]");
            NTDTBO BO = new NTDTBO();
            List<NTDT_searchNNT> result = BO.SearchNNT(pMST, pSTATUS);
            LOGGER.info("NTDT_SearchNNT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public String NTDT_getIDThongBao() throws RemoteException {
        try {
            LOGGER.info("NTDT_getIDThongBao --> [IN] --> input is empty");
            NTDTBO BO = new NTDTBO();
            String result = BO.getIDThongBao();
            LOGGER.info("NTDT_getIDThongBao --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ref_id
     * @return
     * @throws RemoteException
     */
    @Override
    public String NTDT_getIDThongBaoDC(String ref_id) throws RemoteException {
        try {
            LOGGER.info("NTDT_getIDThongBaoDC --> [IN] --> ref_id = [" + ref_id + "]");
            NTDTBO BO = new NTDTBO();
            String result = BO.getIDThongBaoDC(ref_id);
            LOGGER.info("NTDT_getIDThongBaoDC --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_NNT
     * @param pUserApproves
     * @param pStatus
     * @throws RemoteException
     */
    @Override
    public void NTDT_ApproveAccounts(int pID_NNT, String pUserApproves, String pStatus) throws RemoteException {
        try {
            LOGGER.info("NTDT_ApproveAccounts --> [IN] --> pID_NNT = [" + pID_NNT + "], pUserApproves = [" + pUserApproves + "], pStatus = [" + pStatus + "]");
            NTDTBO BO = new NTDTBO();
            BO.ApproveAccounts(pID_NNT, pUserApproves, pStatus);
            LOGGER.info("NTDT_ApproveAccounts --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_GNT
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList NTDT_getChungTuGNT(int pID_GNT) throws RemoteException {
        try {
            LOGGER.info("NTDT_getChungTuGNT --> [IN] --> pID_GNT = [" + pID_GNT + "]");
            NTDTBO BO = new NTDTBO();
            ArrayList result = BO.getChungTuGNT(pID_GNT);
            LOGGER.info("NTDT_getChungTuGNT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_GNT
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList NTDT_getChungTuGNT_CT(int pID_GNT) throws RemoteException {
        try {
            LOGGER.info("NTDT_getChungTuGNT_CT --> [IN] --> pID_GNT = [" + pID_GNT + "]");
            NTDTBO BO = new NTDTBO();
            ArrayList result = BO.getChungTuGNT_CT(pID_GNT);
            LOGGER.info("NTDT_getChungTuGNT_CT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList NTDT_getAllThongBao() throws RemoteException {
        try {
            NTDTBO BO = new NTDTBO();
            ArrayList result = BO.getAllThongBao();
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMST
     * @param pSTATUS
     * @param branchID
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    @Override
    public List<NTDT_searchNNT> NTDT_SearchAllNNT(String pMST, String pSTATUS, String branchID, String fromdate, String todate) throws RemoteException {
        try {
            LOGGER.info("NTDT_SearchAllNNT --> [IN] --> pMST = [" + pMST + "], pSTATUS = [" + pSTATUS + "], branchID = [" + branchID + "], fromdate = [" + fromdate + "], todate = [" + todate + "]");
            NTDTBO BO = new NTDTBO();
            List<NTDT_searchNNT> result = BO.SearchAllNNT(pMST, pSTATUS, branchID, fromdate, todate);
            LOGGER.info("NTDT_SearchAllNNT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    @Override
    public NTDT_searchNNT NTDT_SearchIDNNT(String ID) throws RemoteException {
        try {
            LOGGER.info("NTDT_SearchIDNNT --> [IN] --> ID = [" + ID + "]");
            NTDTBO BO = new NTDTBO();
            NTDT_searchNNT result = BO.SearchIDNNT(ID);
            LOGGER.info("NTDT_SearchIDNNT --> [OUT] --> result_MST = [" + (result == null ? null : result.getMST()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateAccountAlertStatusById(SmsCustAlertTd smscustalerttd) throws RemoteException {
        try {
            LOGGER.info("updateAccountAlertStatusById --> [IN] --> smscustalerttd_cust_ac_no = [" + smscustalerttd.getCust_ac_no() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateAccountAlertStatusById(smscustalerttd);
            LOGGER.info("updateAccountAlertStatusById --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cif
     * @param idcard
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> findCustomer(String cif, String idcard) throws RemoteException {
        try {
            LOGGER.info("findCustomer --> [IN] --> cif = [" + cif + "], idcard = [" + idcard + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            ArrayList<?> result = fcdbBO.findCustomer(cif, idcard);
            LOGGER.info("findCustomer --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    @Override
    public List GetRedemptionAzListByCustNo(String custno) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.GetRedemptionAzListByCustNo(custno);
             */
            LOGGER.info("GetRedemptionAzListByCustNo --> [IN] --> custno = [" + custno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.GetRedemptionAzListByCustNo(custno);
            LOGGER.info("GetRedemptionAzListByCustNo --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param staffcode
     * @return
     * @throws RemoteException
     */
    @Override
    public String GetStaffDetail(String staffcode) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.GetStaffDetail(staffcode);
             */
            LOGGER.info("GetStaffDetail --> [IN] --> staffcode = [" + staffcode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.GetStaffDetail(staffcode);
            LOGGER.info("GetStaffDetail --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param subject
     * @param content
     * @param idchannel
     * @param idchanneluser
     * @return
     * @throws RemoteException
     */
    @Override
    public int InsertFeedback(String subject, String content, String idchannel, String idchanneluser) throws RemoteException {
        try {
            LOGGER.info("InsertFeedback --> [IN] --> subject = [" + subject + "], content = [" + content + "], idchannel = [" + idchannel + "], idchanneluser = [" + idchanneluser + "]");
            FcdbBO fcdbBO = new FcdbBO();
            int result = fcdbBO.InsertFeedback(subject, content, idchannel, idchanneluser);
            LOGGER.info("InsertFeedback --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param customercode
     * @param idchannel
     * @param idservicetype
     * @param statusBill
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param idpartner
     * @param idtransaction
     * @param beginRow
     * @param endRow
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchPaybill(String customercode, String idchannel, String idservicetype, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction, String beginRow, String endRow) throws RemoteException {
        try {
            LOGGER.info("searchPaybill --> [IN] --> customercode = [" + customercode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchPaybill(customercode, idchannel, idservicetype, statusBill, branchcode, fromdate, todate, idpartner, idtransaction, beginRow, endRow);
            LOGGER.info("searchPaybill --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param customercode
     * @param idchannel
     * @param idservicetype
     * @param statusBill
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param idpartner
     * @param idtransaction
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchPaybillAll(String customercode, String idchannel, String idservicetype, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction) throws RemoteException {
        try {
            LOGGER.info("searchPaybillAll --> [IN] --> customercode = [" + customercode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchPaybillAll(customercode, idchannel, idservicetype, statusBill, branchcode, fromdate, todate, idpartner, idtransaction);
            LOGGER.info("searchPaybillAll --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param channel
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchPaybillDiary(String channel, String fromdate, String todate) throws RemoteException {
        try {
            LOGGER.info("searchPaybillDiary --> [IN] --> channel = [" + channel + "], fromdate = [" + fromdate + "], todate = [" + todate + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchPaybillDiary(channel, fromdate, todate);
            LOGGER.info("searchPaybillDiary --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idlist
     * @param status
     * @param userid
     * @param idChanneluser
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateFeedback(String idlist, String status, String userid, String idChanneluser) throws RemoteException {
        try {
            LOGGER.info("updateFeedback --> [IN] --> idlist = [" + idlist + "], status = [" + status + "], userid = [" + userid + "], idChanneluser = [" + idChanneluser + "]");
            FcdbBO fcdbBO = new FcdbBO();
            int result = fcdbBO.updateFeedback(idlist, status, userid, idChanneluser);
            LOGGER.info("updateFeedback --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchannel
     * @param status
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchFeedback(String idchannel, String status, String fromdate, String todate, String beginRow, String endRow) throws RemoteException {
        try {
            LOGGER.info("searchFeedback --> [IN] --> idchannel = [" + idchannel + "], status = ["
                    + status + "], fromdate = [" + fromdate + "], todate = [" + todate
                    + "], beginRow = [" + beginRow + "], endRow = [" + endRow + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.searchFeedback(idchannel, status, fromdate, todate, beginRow, endRow);
            LOGGER.info("searchFeedback --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchannel
     * @param status
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchFeedbackAll(String idchannel, String status, String fromdate, String todate) throws RemoteException {
        try {
            LOGGER.info("searchFeedbackAll --> [IN] --> idchannel = [" + idchannel + "], status = [" + status + "], fromdate = [" + fromdate + "], todate = [" + todate + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.searchFeedbackAll(idchannel, status, fromdate, todate);
            LOGGER.info("searchFeedbackAll --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param txnType
     * @param idlist
     * @param status
     * @param userid
     * @param idChanneluser
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateStatusPaybill(String txnType, String idlist, String status, String userid, String idChanneluser) throws RemoteException {
        try {
            LOGGER.info("updateStatusPaybill --> [IN] --> txnType = [" + txnType + "], idlist = ["
                    + idlist + "], status = [" + status + "], userid = [" + userid
                    + "], idChanneluser = [" + idChanneluser + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateStatusPaybill(txnType, idlist, status, userid, idChanneluser);
            LOGGER.info("updateStatusPaybill --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param partner
     * @param customercode
     * @param statusBill
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param idpartner
     * @param idtransaction
     * @param beginRow
     * @param endRow
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchOnlBill(String id, String partner, String customercode, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction, String beginRow, String endRow) throws RemoteException {
        try {
            LOGGER.info("searchOnlBill --> [IN] --> id = [" + id + "], partner = ["
                    + partner + "], customercode = [" + customercode + "], statusBill = [" + statusBill
                    + "], branchcode = [" + branchcode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchOnlBill(id, partner, customercode, statusBill, branchcode, fromdate, todate, idpartner, idtransaction, beginRow, endRow);
            LOGGER.info("searchOnlBill --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param partner
     * @param customercode
     * @param statusBill
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param idpartner
     * @param idtransaction
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchOnlBillAll(String id, String partner, String customercode, String statusBill, String branchcode, String fromdate, String todate, String idpartner, String idtransaction) throws RemoteException {
        try {
            LOGGER.info("searchOnlBillAll --> [IN] --> id = [" + id + "], partner = ["
                    + partner + "], customercode = [" + customercode + "], statusBill = [" + statusBill
                    + "], branchcode = [" + branchcode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchOnlBillAll(id, partner, customercode, statusBill, branchcode, fromdate, todate, idpartner, idtransaction);
            LOGGER.info("searchOnlBillAll --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cifno
     * @param mobileno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchSMSAlert(String cifno, String mobileno) throws RemoteException {
        try {
            LOGGER.info("searchSMSAlert --> [IN] --> cifno = [" + cifno + "], mobileno = [" + mobileno + "]");
            SmsSCBBO fcdbBO = new SmsSCBBO();
            ArrayList result = fcdbBO.searchSMSAlert(cifno, mobileno);
            LOGGER.info("searchSMSAlert --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cifno
     * @param accountno
     * @param cardaccountno
     * @param registertype
     * @param issuetype
     * @param cardtype
     * @param fee
     * @param tax
     * @return
     * @throws RemoteException
     */
    @Override
    public int checkIssueATMCard(String cifno, String accountno, String cardaccountno, String registertype, String issuetype, String cardtype, String fee, String tax) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.checkIssueATMCard(cifno, accountno, cardaccountno,registertype, issuetype,cardtype, fee, tax);
             */
            LOGGER.info("checkIssueATMCard --> [IN] --> cifno = [" + cifno + "], accountno = [" + accountno + "], cardaccountno = [" + cardaccountno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.checkIssueATMCard(cifno, accountno, cardaccountno, registertype, issuetype, cardtype, fee, tax);
            LOGGER.info("checkIssueATMCard --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param issueatm
     * @return
     * @throws RemoteException
     */
    @Override
    public ProcedureCallDto insertEbIssuecard(EbIssuecard issueatm) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.insertEbIssuecard(issueatm);
             */
            LOGGER.info("insertEbIssuecard --> [IN] --> issueatm_sourceaccount = [" + issueatm.getSourceaccount() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ProcedureCallDto result = smsscbBO.insertEbIssuecard(issueatm);
            LOGGER.info("insertEbIssuecard --> [OUT] --> result_ErrorStatus = [" + (result == null ? null : result.getErrorStatus()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param issueatm
     * @throws RemoteException
     */
    @Override
    public void updateEbIssuecard(EbIssuecard issueatm) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            fcdbBO.updateEbIssuecard(issueatm);
             */
            LOGGER.info("updateEbIssuecard --> [IN] --> issueatm_sourceaccount = [" + issueatm.getSourceaccount() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updateEbIssuecard(issueatm);
            LOGGER.info("updateEbIssuecard --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param type
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCardType(String type) throws RemoteException {
        try {
            LOGGER.info("getCardType --> [IN] --> type = [" + type + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getCardType(type);
            //FcdbBO fcdbBO = new FcdbBO();
            //return fcdbBO.getCardType(type);
            LOGGER.info("getCardType --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cif
     * @param cardtype
     * @param groupcode
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCardList(String cif, String cardtype, String groupcode) throws RemoteException {
        try {
//            FcdbBO fcdbBO = new FcdbBO();
//            return fcdbBO.getCardList(cif, cardtype,groupcode);
            LOGGER.info("getCardList --> [IN] --> cif = [" + cif + "], cardtype = [" + cardtype + "], groupcode = [" + groupcode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getCardList(cif, cardtype, groupcode);
            LOGGER.info("getCardList --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cardaccountno
     * @param period
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getMCStateDetail(String cardaccountno, String period) throws RemoteException {
        try {
//            FcdbBO fcdbBO = new FcdbBO();
//            return fcdbBO.getMCStateDetail(cardaccountno, period);
            LOGGER.info("getMCStateDetail --> [IN] --> cardaccountno = [" + cardaccountno + "], period = [" + period + "]");
            //SmsSCBBO smsscbBO = new SmsSCBBO();
            //ArrayList result = smsscbBO.getMCStateDetail(cardaccountno, period);
            CWDwhBO cwdwhBO = new CWDwhBO();
            ArrayList result = cwdwhBO.getMCStateDetail(cardaccountno, period);

            LOGGER.info("getMCStateDetail --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*
    @Override
    public ArrayList getCCStatement(String cardaccountno, String fromdate, String todate, String srno, String rownum) throws RemoteException {
        try {
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.getCCStatement(cardaccountno, fromdate, todate, srno, rownum);
        } catch (Exception ex) {
LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
     */
    /**
     *
     * @param cardaccountno
     * @param period
     * @param srno
     * @param rownum
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCCStatement(String cardaccountno, String period, String srno, String rownum) throws RemoteException {
        try {
//            FcdbBO fcdbBO = new FcdbBO();
//            return fcdbBO.getCCStatement(cardaccountno, period, srno, rownum);
            LOGGER.info("getCCStatement --> [IN] --> cardaccountno = [" + cardaccountno
                    + "], period = [" + period + "], srno = [" + srno + "], rownum = [" + rownum + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getCCStatement(cardaccountno, period, srno, rownum);
            LOGGER.info("getCCStatement --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cardaccountno
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] getPointMC(String cardaccountno) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.getPointMC(cardaccountno);
             */
            LOGGER.info("getPointMC --> [IN] --> cardaccountno = [" + cardaccountno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String[] result = smsscbBO.getPointMC(cardaccountno);
            LOGGER.info("getPointMC --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param issueatm
     * @return
     * @throws RemoteException
     */
    @Override
    public List getEbIssuecard(EbIssuecard issueatm) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.getEbIssuecard(issueatm);
             */
            LOGGER.info("getEbIssuecard --> [IN] --> issueatm_sourceaccount = [" + issueatm.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.getEbIssuecard(issueatm);
            LOGGER.info("getEbIssuecard --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cardno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getMaterCardDetailByCardnoNew(String cardno) throws RemoteException {
        try {
            LOGGER.info("getMaterCardDetailByCardnoNew --> [IN] --> cardno = [" + cardno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getMaterCardDetailByCardnoNew(cardno);
            LOGGER.info("getMaterCardDetailByCardnoNew --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cardno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getTransactionMaterCardByCardnoNew(String cardno) throws RemoteException {
        try {
            LOGGER.info("getTransactionMaterCardByCardnoNew --> [IN] --> cardno = [" + cardno + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getTransactionMaterCardByCardnoNew(cardno);
            LOGGER.info("getTransactionMaterCardByCardnoNew --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    @Override
    public List GetAccountListByCustNoNew(String custno) throws RemoteException {
        try {
            /*
             FcdbBO fcdbBO = new FcdbBO();
             return fcdbBO.GetAccountListByCustNoNew(custno);
             */
            LOGGER.info("GetAccountListByCustNoNew --> [IN] --> custno = [" + custno + "]");
            FCCCoreBO fccCore = new FCCCoreBO();
            List listFCC = fccCore.GetAccountListByCustNoFCC(custno);

            CWLiveBO cwLiveBO = new CWLiveBO();
            List listCard = cwLiveBO.GetAccountListByCustNoCard(custno);

            if (listCard != null && listCard.size() > 0) {
                listFCC.addAll(listCard);
            }
            //Helper.writeLog(FCCCoreBO.class, Level.INFO, "GetAccountListByCustNoNew -" + custno);
            LOGGER.info("GetAccountListByCustNoNew --> [OUT] --> Size = [" + (listFCC == null ? null : listFCC.size()) + "]");
            return listFCC;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCardnoByAccountno(String accountno) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.getCardnoByAccountno(accountno);
             */
            LOGGER.info("getCardnoByAccountno --> [IN] --> accountno = [" + accountno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getCardnoByAccountno(accountno);
            LOGGER.info("getCardnoByAccountno --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cifno
     * @param mobileno
     * @param active
     * @return
     * @throws RemoteException
     */
    @Override
    public int regeisterAlertTDMB(String cifno, String mobileno, String active) throws RemoteException {
        try {
            LOGGER.info("regeisterAlertTDMB --> [IN] --> cifno = [" + cifno + "], mobileno = [" + mobileno + "], active = [" + active + "]");
            FcdbBO fcdbBO = new FcdbBO();
            int result = fcdbBO.regeisterAlertTDMB(cifno, mobileno, active);
            LOGGER.info("regeisterAlertTDMB --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idlist
     * @param status
     * @param userid
     * @param idChanneluser
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateRegisterinfo(String idlist, String status, String userid, String idChanneluser) throws RemoteException {
        try {
            LOGGER.info("updateRegisterinfo --> [IN] --> idlist = [" + idlist + "], status = [" + status + "], userid = [" + userid + "], idChanneluser = [" + idChanneluser + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            int result = fcdbBO.updateRegisterinfo(idlist, status, userid, idChanneluser);
            LOGGER.info("updateRegisterinfo --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchannel
     * @param status
     * @param branchcode
     * @param registerType
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchRegisterinfo(String idchannel, String status, String branchcode, String registerType, String fromdate, String todate, String beginRow, String endRow) throws RemoteException {
        try {
            LOGGER.info("searchRegisterinfo --> [IN] --> idchannel = [" + idchannel + "], status = [" + status + "], branchcode = [" + branchcode + "], registerType = [" + registerType + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            ArrayList result = fcdbBO.searchRegisterinfo(idchannel, status, branchcode, registerType, fromdate, todate, beginRow, endRow);
            LOGGER.info("searchRegisterinfo --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idchannel
     * @param status
     * @param branchcode
     * @param registerType
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchRegisterinfoAll(String idchannel, String status, String branchcode, String registerType, String fromdate, String todate) throws RemoteException {
        try {
            LOGGER.info("searchRegisterinfoAll --> [IN] --> idchannel = [" + idchannel + "], status = [" + status + "], branchcode = [" + branchcode + "], registerType = [" + registerType + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            ArrayList result = fcdbBO.searchRegisterinfoAll(idchannel, status, branchcode, registerType, fromdate, todate);
            LOGGER.info("searchRegisterinfoAll --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custno
     * @param custaccno
     * @param amount
     * @return
     * @throws RemoteException
     */
    @Override
    public ProcedureCallDto checkFeeMobile(String custno, String custaccno, String amount) throws RemoteException {
        try {
            /*
            FcdbBO fcdbBO = new FcdbBO();
            return fcdbBO.checkFeeMobile(custno,custaccno,amount);
             */
            LOGGER.info("checkFeeMobile --> [IN] --> custno = [" + custno + "], custaccno = [" + custaccno + "], amount = [" + amount + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ProcedureCallDto result = smsscbBO.checkFeeMobile(custno, custaccno, amount);
            LOGGER.info("checkFeeMobile --> [OUT] --> result_ErrorStatus = [" + (result == null ? null : result.getErrorStatus()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cif
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getPrimaryATMList(String cif) throws RemoteException {
        try {
            LOGGER.info("getPrimaryATMList --> [IN] --> cif = [" + cif + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getPrimaryATMList(cif);
            LOGGER.info("getPrimaryATMList --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*VIMO BEGIN*/
    /**
     *
     * @param PTRANSID
     * @param PTRANSDATE
     * @param PCARDNUMBER
     * @param PCARDNAME
     * @param POTPSMS
     * @param PPARTNERID
     * @param pDescription
     * @param pTypeCard
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_VERIFICARD(String PTRANSID,
            String PTRANSDATE,
            String PCARDNUMBER,
            String PCARDNAME,
            String POTPSMS,
            String PPARTNERID,
            String pDescription,
            String pTypeCard) throws RemoteException {
        try {
            LOGGER.info("ONL_VERIFICARD --> [IN] --> PTRANSID = [" + PTRANSID + "], PTRANSDATE = [" + PTRANSDATE + "], PCARDNUMBER = [" + PCARDNUMBER + "], PCARDNAME = [" + PCARDNAME + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.VERIFICARD(PTRANSID, PTRANSDATE, PCARDNUMBER, PCARDNAME, POTPSMS, PPARTNERID, pDescription, pTypeCard);
            LOGGER.info("ONL_VERIFICARD --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PTRANSDATE
     * @param PVERIFYTYPE
     * @param PREFTRANSID
     * @param POTPSMS
     * @param PPARTNERID
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_VERIFYOTPSMS(String PTRANSID,
            String PTRANSDATE,
            String PVERIFYTYPE,
            String PREFTRANSID,
            String POTPSMS,
            String PPARTNERID
    ) throws RemoteException {
        try {
            LOGGER.info("ONL_VERIFYOTPSMS --> [IN] --> PTRANSID = [" + PTRANSID + "], PTRANSDATE = [" + PTRANSDATE + "], PVERIFYTYPE = [" + PVERIFYTYPE + "], PREFTRANSID = [" + PREFTRANSID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.VERIFYOTPSMS(PTRANSID, PTRANSDATE, PVERIFYTYPE, PREFTRANSID, POTPSMS, PPARTNERID);
            LOGGER.info("ONL_VERIFYOTPSMS --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PPROFILEID
     * @param PAMOUNT
     * @param PCCY
     * @param PTRANSDATE
     * @param PPARTNERID
     * @param PDESCRIPTION
     * @param PMERCHANTID
     * @param pChannelID
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_PAYMENTWITHPROFILEID(String PTRANSID,
            String PPROFILEID,
            Double PAMOUNT,
            String PCCY,
            String PTRANSDATE,
            String PPARTNERID,
            String PDESCRIPTION,
            String PMERCHANTID,
            String pChannelID) throws RemoteException {
        try {
            LOGGER.info("ONL_PAYMENTWITHPROFILEID --> [IN] --> PTRANSID = [" + PTRANSID + "], PPROFILEID = [" + PPROFILEID + "], PAMOUNT = [" + PAMOUNT + "], PPARTNERID = [" + PPARTNERID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.PAYMENTWITHPROFILEID(PTRANSID, PPROFILEID, PAMOUNT, PCCY, PTRANSDATE, PPARTNERID, PDESCRIPTION, PMERCHANTID, pChannelID);
            LOGGER.info("ONL_PAYMENTWITHPROFILEID --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PTRANSID
     * @param pCardNumber
     * @param pCardHolderName
     * @param pCardDate
     * @param PAMOUNT
     * @param PCCY
     * @param PTRANSDATE
     * @param PPARTNERID
     * @param PDESCRIPTION
     * @param PMERCHANTID
     * @param pChannelID
     * @param OTP
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_PAYMENT(String PTRANSID,
            String pCardNumber,
            String pCardHolderName,
            String pCardDate,
            Double PAMOUNT,
            String PCCY,
            String PTRANSDATE,
            String PPARTNERID,
            String PDESCRIPTION,
            String PMERCHANTID,
            String pChannelID,
            String OTP
    ) throws RemoteException {
        try {
            LOGGER.info("ONL_PAYMENT --> [IN] --> PTRANSID = [" + PTRANSID + "], pCardNumber = [" + pCardNumber + "], PAMOUNT = [" + PAMOUNT + "], PPARTNERID = [" + PPARTNERID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.PAYMENT(PTRANSID, pCardNumber, pCardHolderName, pCardDate, PAMOUNT, PCCY, PTRANSDATE, PPARTNERID, PDESCRIPTION, PMERCHANTID, pChannelID, OTP);
            LOGGER.info("ONL_PAYMENT --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pBankid
     * @param pStatus
     * @param pRefcore
     * @throws RemoteException
     */
    @Override
    public void ONL_UpdatePayment(String pBankid,
            String pStatus,
            String pRefcore) throws RemoteException {
        try {
            LOGGER.info("ONL_UpdatePayment --> [IN] --> pBankid = [" + pBankid + "], pStatus = [" + pStatus + "], pRefcore = [" + pRefcore + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.UpdatePayment(pBankid, pStatus, pRefcore);
            LOGGER.info("ONL_UpdatePayment --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pDestNumber
     * @param Ptypedestnumber
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_checkDestNumber(String pDestNumber,
            String Ptypedestnumber) throws RemoteException {
        try {
            LOGGER.info("ONL_checkDestNumber --> [IN] --> pDestNumber = [" + pDestNumber + "], Ptypedestnumber = [" + Ptypedestnumber + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.checkDestNumber(pDestNumber, Ptypedestnumber);
            LOGGER.info("ONL_checkDestNumber --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PDESTNUMBER
     * @param pSOURCEACCOUNT
     * @param PAMOUNT
     * @param PCCY
     * @param PTRANSDATE
     * @param PDESRCRIPTION
     * @param PPARTNERID
     * @param Ptypedestnumber
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_TAKEOUTWALLET(String PTRANSID,
            String PDESTNUMBER,
            String pSOURCEACCOUNT,
            Double PAMOUNT,
            String PCCY,
            String PTRANSDATE,
            String PDESRCRIPTION,
            String PPARTNERID,
            String Ptypedestnumber) throws RemoteException {
        try {
            LOGGER.info("ONL_TAKEOUTWALLET --> [IN] --> PTRANSID = [" + PTRANSID + "], PDESTNUMBER = [" + PDESTNUMBER + "], pSOURCEACCOUNT = [" + pSOURCEACCOUNT + "], PAMOUNT = [" + PAMOUNT + "], PPARTNERID = [" + PPARTNERID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.TAKEOUTWALLET(PTRANSID, PDESTNUMBER, pSOURCEACCOUNT, PAMOUNT, PCCY, PTRANSDATE, PDESRCRIPTION, PPARTNERID, Ptypedestnumber);
            LOGGER.info("ONL_TAKEOUTWALLET --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PBANKID
     * @param PREFCORE
     * @throws RemoteException
     */
    @Override
    public void ONL_UPDATE_TAKEOUTWALLET(String PBANKID,
            String PREFCORE) throws RemoteException {
        try {
            LOGGER.info("ONL_UPDATE_TAKEOUTWALLET --> [IN] --> PBANKID = [" + PBANKID + "], PREFCORE = [" + PREFCORE + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.UPDATE_TAKEOUTWALLET(PBANKID, PREFCORE);
            LOGGER.info("ONL_UPDATE_TAKEOUTWALLET --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PPARTNERID
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_REVERT_TAKEOUTWALLET(String PTRANSID,
            String PPARTNERID) throws RemoteException {
        try {
            LOGGER.info("ONL_REVERT_TAKEOUTWALLET --> [IN] --> PTRANSID = [" + PTRANSID + "], PPARTNERID = [" + PPARTNERID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.REVERT_TAKEOUTWALLET(PTRANSID, PPARTNERID);
            LOGGER.info("ONL_REVERT_TAKEOUTWALLET --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PREVERTTRANSID
     * @param PPARTNERID
     * @param PTRANSDATE
     * @param PDESC
     * @throws RemoteException
     */
    @Override
    public void ONL_UPDATE_REVERT_TAKEOUTWALLET(String PTRANSID,
            String PREVERTTRANSID,
            String PPARTNERID,
            String PTRANSDATE,
            String PDESC) throws RemoteException {
        try {
            LOGGER.info("ONL_UPDATE_REVERT_TAKEOUTWALLET --> [IN] --> PTRANSID = [" + PTRANSID + "], PREVERTTRANSID = [" + PREVERTTRANSID + "], PPARTNERID = [" + PPARTNERID + "], PTRANSDATE = [" + PTRANSDATE + "], PDESC = [" + PDESC + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.UPDATE_REVERT_TAKEOUTWALLET(PTRANSID, PREVERTTRANSID, PPARTNERID, PTRANSDATE, PDESC);
            LOGGER.info("ONL_UPDATE_REVERT_TAKEOUTWALLET --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pTransid
     * @param pRefTransid
     * @param pAmount
     * @param pTransdate
     * @param pDescription
     * @param pPartnerid
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_REFUND(String pTransid,
            String pRefTransid,
            Double pAmount,
            String pTransdate,
            String pDescription,
            String pPartnerid) throws RemoteException {
        try {
            LOGGER.info("ONL_REFUND --> [IN] --> pTransid = [" + pTransid + "], pRefTransid = [" + pRefTransid + "], pAmount = [" + pAmount + "], pTransdate = [" + pTransdate + "], pDescription = [" + pDescription + "], pPartnerid = [" + pPartnerid + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.REFUND(pTransid, pRefTransid, pAmount, pTransdate, pDescription, pPartnerid);
            LOGGER.info("ONL_REFUND --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PID
     * @param PREFCORE
     * @throws RemoteException
     */
    @Override
    public void ONL_UPDATE_REFUND(String PID, String PREFCORE) throws RemoteException {
        try {
            LOGGER.info("ONL_UPDATE_REFUND --> [IN] --> PID = [" + PID + "], PREFCORE = [" + PREFCORE + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.UPDATE_REFUND(PID, PREFCORE);
            LOGGER.info("ONL_UPDATE_REFUND --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pRefTransid
     * @param pTRANSTYPE
     * @param pParnerid
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_queryTransaction(
            String pRefTransid,
            String pTRANSTYPE,
            String pParnerid) throws RemoteException {
        try {
            LOGGER.info("ONL_queryTransaction --> [IN] --> pRefTransid = [" + pRefTransid + "], pTRANSTYPE = [" + pTRANSTYPE + "], pParnerid = [" + pParnerid + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.queryTransaction(pRefTransid, pTRANSTYPE, pParnerid);
            LOGGER.info("ONL_queryTransaction --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PTRANSID
     * @param PTRANSDATE
     * @param pProfileID
     * @param pDescription
     * @param pPartnerid
     * @param pOTP
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_DestroyConnectCard(String PTRANSID,
            String PTRANSDATE,
            String pProfileID,
            String pDescription,
            String pPartnerid,
            String pOTP) throws RemoteException {
        try {
            LOGGER.info("ONL_DestroyConnectCard --> [IN] --> PTRANSID = [" + PTRANSID + "], PTRANSDATE = [" + PTRANSDATE + "], pProfileID = [" + pProfileID + "], pDescription = [" + pDescription + "], pPartnerid = [" + pPartnerid + "], pOTP = [" + pOTP + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.DestroyConnectCard(PTRANSID, PTRANSDATE, pProfileID, pDescription, pPartnerid, pOTP);
            LOGGER.info("ONL_DestroyConnectCard --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pPartnerid
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_GetMACkeys(String pPartnerid) throws RemoteException {
        try {
            LOGGER.info("ONL_GetMACkeys --> [IN] --> pPartnerid = [" + pPartnerid + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.GetMACkeys(pPartnerid);
            LOGGER.info("ONL_GetMACkeys --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*VIMO end*/
    //Cardword WS
    /**
     *
     * @param ID
     * @param SRCACCOUNT
     * @param PAN_LOC
     * @param CARD_BRN
     * @param CCY
     * @param EXP_DATE
     * @param AMOUNT
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @param SRCCHANNEL
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertCardReloadTrans(String ID, String SRCACCOUNT, String PAN_LOC,
            String CARD_BRN, String CCY, String EXP_DATE, Double AMOUNT, String REF_FCC,
            String REF_CW, String TRANS_STATUS, String SRCCHANNEL)
            throws RemoteException {
        try {
            LOGGER.info("insertCardReloadTrans --> [IN] --> ID = [" + ID + "], PAN_LOC = [" + PAN_LOC + "], AMOUNT = [" + AMOUNT + "], REF_CW = [" + REF_CW + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertCardReloadTrans(ID, SRCACCOUNT, PAN_LOC,
                    CARD_BRN, CCY, EXP_DATE, AMOUNT, REF_FCC,
                    REF_CW, TRANS_STATUS, SRCCHANNEL);
            LOGGER.info("insertCardReloadTrans --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //Long.Le edit updateCardReloadTrans
    /**
     *
     * @param ID
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateCardReloadTrans(String ID, String REF_FCC, String REF_CW, String TRANS_STATUS) throws RemoteException {
        try {
            LOGGER.info("updateCardReloadTrans --> [IN] --> ID = [" + ID + "], REF_FCC = [" + REF_FCC + "], REF_CW = [" + REF_CW + "], TRANS_STATUS = [" + TRANS_STATUS + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateCardReloadTrans(ID, REF_FCC, REF_CW, TRANS_STATUS);
            LOGGER.info("updateCardReloadTrans --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    //End of Cardword WS   

    //thay doi nguoi thu huong cho tich luy moi
    /**
     *
     * @param acccount
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList GetBeneficaryName(String acccount) throws RemoteException {
        try {
//            FcdbBO fcdbBO = new FcdbBO();
//            return fcdbBO.GetBeneficaryName(acccount);
            LOGGER.info("GetBeneficaryName --> [IN] --> acccount = [" + acccount + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.GetBeneficaryName(acccount);
            LOGGER.info("GetBeneficaryName --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //LongLe
    /**
     *
     * @param idschool
     * @return
     * @throws RemoteException
     */
    @Override
    public String getAccountProvider(String idschool) throws RemoteException {
        try {
            LOGGER.info("getAccountProvider --> [IN] --> idschool = [" + idschool + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.getAccountProvider(idschool);
            LOGGER.info("getAccountProvider --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idBillPaidinsertPaybillBillPaid
     * @param partnerDetailId
     * @param partnerId
     * @param accountTo
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertBillPaidDetail(int idBillPaid, String partnerDetailId, String partnerId, String accountTo) throws RemoteException {
        try {
            LOGGER.info("insertBillPaidDetail --> [IN] --> idBillPaid = [" + idBillPaid + "], partnerDetailId = [" + partnerDetailId + "], partnerId = [" + partnerId + "], accountTo = [" + accountTo + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertBillPaidDetail(idBillPaid, partnerDetailId, partnerId, accountTo);
            LOGGER.info("insertBillPaidDetail --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new RemoteException(e.getMessage(), e);
        }
    }

    //Long.Le (S)
    /**
     *
     * @param status
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getSMSFitcom(int status) throws RemoteException {
        try {
            LOGGER.info("getSMSFitcom --> [IN] --> status = [" + status + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.getSMSFitcom(status);
            LOGGER.info("getSMSFitcom --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateStatusSMSFitcom(int id, int status) throws RemoteException {
        try {
            LOGGER.info("updateStatusSMSFitcom --> [IN] --> id = [" + id + "], status = [" + status + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateStatusSMSFitcom(id, status);
            LOGGER.info("updateStatusSMSFitcom --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param mobile
     * @param content
     * @param serviceType
     * @param serviceCode
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertSMS(int id, String mobile, String content, String serviceType, String serviceCode) throws RemoteException {
        try {
            LOGGER.info("insertSMS --> [IN] --> id = [" + id + "], mobile = [" + mobile + "], content = [" + content + "], serviceType = [" + serviceType + "], serviceCode = [" + serviceCode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertSMS(id, mobile, content, serviceType, serviceCode);
            LOGGER.info("insertSMS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param mobile
     * @param content
     * @param serviceType
     * @param user
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertSmsSendLog(int id, String mobile, String content, String serviceType, String user) throws RemoteException {
        try {
            LOGGER.info("insertSmsSendLog --> [IN] --> id = [" + id + "], mobile = [" + mobile + "], content = [" + content + "], serviceType = [" + serviceType + "], user = [" + user + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertSmsSendLog(id, mobile, content, serviceType, user);
            LOGGER.info("insertSmsSendLog --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idmsg
     * @param mobile
     * @param content
     * @param idfile
     * @param typemessage
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertFileDetail(int idmsg, String mobile, String content, int idfile, String typemessage) throws RemoteException {
        try {
            LOGGER.info("insertFileDetail --> [IN] --> idmsg = [" + idmsg + "], mobile = [" + mobile + "], content = [" + content + "], idfile = [" + idfile + "], typemessage = [" + typemessage + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertFileDetail(idmsg, mobile, content, idfile, typemessage);
            LOGGER.info("insertFileDetail --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param messagetype
     * @param filename
     * @param iduser
     * @param desc
     * @return
     * @throws RemoteException
     */
    @Override
    public int uploadfileSMS(int id, String messagetype, String filename, String iduser, String desc) throws RemoteException {
        try {
            LOGGER.info("uploadfileSMS --> [IN] --> id = [" + id + "], messagetype = [" + messagetype + "], filename = [" + filename + "], iduser = [" + iduser + "], desc = [" + desc + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.uploadfileSMS(id, messagetype, filename, iduser, desc);
            LOGGER.info("uploadfileSMS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idfile
     * @param isapproved
     * @param iduser
     * @return
     * @throws RemoteException
     */
    @Override
    public int approveFileSMS(int idfile, String isapproved, String iduser) throws RemoteException {
        try {
            LOGGER.info("approveFileSMS --> [IN] --> idfile = [" + idfile + "], isapproved = [" + isapproved + "], iduser = [" + iduser + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.approveFileSMS(idfile, isapproved, iduser);
            LOGGER.info("approveFileSMS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idfile
     * @param isapproved
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateStatusSMS(int idfile, String isapproved) throws RemoteException {
        try {
            LOGGER.info("updateStatusSMS --> [IN] --> idfile = [" + idfile + "], isapproved = [" + isapproved + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateStatusSMS(idfile, isapproved);
            LOGGER.info("updateStatusSMS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idmsg
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateSendMSG(int idmsg) throws RemoteException {
        try {
            LOGGER.info("updateSendMSG --> [IN] --> idmsg = [" + idmsg + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateSendMSG(idmsg);
            LOGGER.info("updateSendMSG --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idfile
     * @param send_flag
     * @param iduser
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateSendFile(int idfile, String send_flag, String iduser) throws RemoteException {
        try {
            LOGGER.info("updateSendFile --> [IN] --> idfile = [" + idfile + "], send_flag = [" + send_flag + "], iduser = [" + iduser + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateSendFile(idfile, send_flag, iduser);
            LOGGER.info("updateSendFile --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param fromDateSearch
     * @param toDateSearch
     * @param statusfile
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> searchFileSMS(String fromDateSearch, String toDateSearch, String statusfile) throws RemoteException {
        try {
            LOGGER.info("searchFileSMS --> [IN] --> fromDateSearch = [" + fromDateSearch + "], toDateSearch = [" + toDateSearch + "], statusfile = [" + statusfile + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.searchFileSMS(fromDateSearch, toDateSearch, statusfile);
            LOGGER.info("searchFileSMS --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getFileDetail(String id) throws RemoteException {
        try {
            LOGGER.info("getFileDetail --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getFileDetail(id);
            LOGGER.info("getFileDetail --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*CK Tan Viet (S)*/
    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<?> getAllListPartnerFI() throws RemoteException {
        try {
            LOGGER.info("getAllListPartnerFI --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getAllListPartnerFI();
            LOGGER.info("getAllListPartnerFI --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param servicetype
     * @param branchcode
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getApproveTranferFI(String servicetype, String branchcode) throws RemoteException {
        try {
            LOGGER.info("getApproveTranferFI --> [IN] --> servicetype = [" + servicetype + "], branchcode = [" + branchcode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getApproveTranferFI(servicetype, branchcode);
            LOGGER.info("getApproveTranferFI --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param si
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertTrftToSI(SITrftToSIAuth si) throws RemoteException {
        try {
            LOGGER.info("insertTrftToSI --> [IN] --> SITrftToSIAuth_idbillpaid = [" + si.getIdbillpaid() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertTrftToSI(si);
            LOGGER.info("insertTrftToSI --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public SITrftToSIAuth getTrftToSIById(int id) throws RemoteException {
        try {
            LOGGER.info("getTrftToSIById --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            SITrftToSIAuth result = smsscbBO.getTrftToSIById(id);
            LOGGER.info("getTrftToSIById --> [OUT] --> result_idbillpaid = [" + (result == null ? null : result.getIdbillpaid()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param si
     * @throws RemoteException
     */
    @Override
    public void updateTrftToSI(SITrftToSIAuth si) throws RemoteException {
        try {
            LOGGER.info("updateTrftToSI --> [IN] --> SITrftToSIAuth_idbillpaid = [" + si.getIdbillpaid() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updateTrftToSI(si);
            LOGGER.info("updateTrftToSI --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param fromDateSearch
     * @param toDateSearch
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getlistAllSI(String fromDateSearch, String toDateSearch) throws RemoteException {
        try {
            LOGGER.info("getlistAllSI --> [IN] --> fromDateSearch = [" + fromDateSearch + "], toDateSearch = [" + toDateSearch + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getlistAllSI(fromDateSearch, toDateSearch);
            LOGGER.info("getlistAllSI --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param partnerId
     * @return
     * @throws RemoteException
     */
    @Override
    public String getPartnerNameById(String partnerId) throws RemoteException {
        try {
            LOGGER.info("getPartnerNameById --> [IN] --> partnerId = [" + partnerId + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.getPartnerNameById(partnerId);
            LOGGER.info("getPartnerNameById --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*CK Tan Viet (E)*/

 /*Mua Bao hiem (S)*/
    /**
     *
     * @param ins
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertInsuranceInfo(Insurance ins) throws RemoteException {
        try {
            LOGGER.info("insertInsuranceInfo --> [IN] --> Insurance_idREG = [" + ins.getIdREG() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertInsuranceInfo(ins);
            LOGGER.info("insertInsuranceInfo --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ins
     * @throws RemoteException
     */
    @Override
    public void updateStatusInsurance(Insurance ins) throws RemoteException {
        try {
            LOGGER.info("updateStatusInsurance --> [IN] --> Insurance_idREG = [" + ins.getIdREG() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updateStatusInsurance(ins);
            LOGGER.info("updateStatusInsurance --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param hour
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> GetlistInsuranceToExport(int hour) throws RemoteException {
        try {
            LOGGER.info("GetlistInsuranceToExport --> [IN] --> hour = [" + hour + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.GetlistInsuranceToExport(hour);
            LOGGER.info("GetlistInsuranceToExport --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*Mua Bao hiem (E)*/

 /*Mua the cao (S)*/
    /**
     *
     * @param bc
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertBuyCard(RTBuyCardDTO bc) throws RemoteException {
        try {
            LOGGER.info("insertBuyCard --> [IN] --> RTBuyCardDTO_id = [" + bc.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertBuyCard(bc);
            LOGGER.info("insertBuyCard --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cd
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertBuyCardDetail(RTCardDetailDTO cd) throws RemoteException {
        try {
            LOGGER.info("insertBuyCardDetail --> [IN] --> RTCardDetailDTO_id = [" + cd.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertBuyCardDetail(cd);
            LOGGER.info("insertBuyCardDetail --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param bc
     * @throws RemoteException
     */
    @Override
    public void updateStatusBuyCard(RTBuyCardDTO bc) throws RemoteException {
        try {
            LOGGER.info("updateStatusBuyCard --> [IN] --> RTBuyCardDTO_id = [" + bc.getId() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updateStatusBuyCard(bc);
            LOGGER.info("updateStatusBuyCard --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*Mua the cao (E)*
    
    /*Thanh toan Bao hiem (S)*/
    /**
     *
     * @param payins
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertPayIns(InsurancePayment payins) throws RemoteException {
        try {
            LOGGER.info("insertPayIns --> [IN] --> InsurancePayment_idinsurancepayment = [" + payins.getIdinsurancepayment() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertPayIns(payins);
            LOGGER.info("insertPayIns --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param payins
     * @throws RemoteException
     */
    @Override
    public void updateStatusPayIns(InsurancePayment payins) throws RemoteException {
        try {
            LOGGER.info("updateStatusPayIns --> [IN] --> InsurancePayment_idinsurancepayment = [" + payins.getIdinsurancepayment() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updateStatusPayIns(payins);
            LOGGER.info("updateStatusPayIns --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param amount
     * @throws RemoteException
     */
    @Override
    public void updateStatusBCBill(int id, long amount) throws RemoteException {
        try {
            LOGGER.info("updateStatusBCBill --> [IN] --> id = [" + id + "], amount = [" + amount + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updateStatusBCBill(id, amount);
            LOGGER.info("updateStatusBCBill --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custAcc
     * @param amount
     * @param content
     * @param user_maker
     * @param user_checker
     * @return
     * @throws RemoteException
     */
    @Override
    public String transferFCUBSCash(String custAcc, BigDecimal amount, String content, String user_maker, String user_checker) throws RemoteException {
        try {
            LOGGER.info("transferFCUBSCash --> [IN] --> custAcc = [" + custAcc + "], amount = [" + amount + "], content = [" + content + "], user_maker = [" + user_maker + "], user_checker = [" + user_checker + "]");
            FCCCoreBO fccCoreBO = new FCCCoreBO();
            String result = fccCoreBO.transferFCUBSCash(custAcc, amount, content, user_maker, user_checker);
            LOGGER.info("transferFCUBSCash --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custAcc
     * @param amount
     * @param content
     * @param user_maker
     * @param user_checker
     * @param noiCap
     * @param ngayCap
     * @param sodt
     * @param nguoiGiaoDich
     * @param diaChi
     * @param cmnd
     * @return
     * @throws RemoteException
     */
    @Override
    public String transferFCUBSCashv2(String custAcc, BigDecimal amount, String content, String user_maker, String user_checker, String noiCap, String ngayCap, String sodt, String nguoiGiaoDich, String diaChi, String cmnd) throws RemoteException {
        try {
            LOGGER.info("transferFCUBSCashv2 --> [IN] --> custAcc = [" + custAcc + "], amount = [" + amount + "], content = [" + content + "], user_maker = [" + user_maker + "], user_checker = [" + user_checker + "], noiCap = [" + noiCap + "], "
                    + "ngayCap = [" + ngayCap + "], sodt = [" + sodt + "],  nguoiGiaoDich = [" + nguoiGiaoDich + "], diaChi = [" + diaChi + "], cmnd = [" + cmnd + "] ");
            FCCCoreBO fccCoreBO = new FCCCoreBO();
            String result = fccCoreBO.transferFCUBSCashv2(custAcc, amount, content, user_maker, user_checker, noiCap, ngayCap, sodt, nguoiGiaoDich, diaChi, cmnd);
            LOGGER.info("transferFCUBSCashv2 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param P_TRN_REF
     * @param P_XREF
     * @param pm_gdv
     * @param pm_ksv
     * @return
     * @throws RemoteException
     */
    @Override
    public String revertTransferFCUBS(String P_TRN_REF, String P_XREF, String pm_gdv, String pm_ksv) throws RemoteException {
        try {
            LOGGER.info("revertTransferFCUBS --> [IN] --> P_TRN_REF = [" + P_TRN_REF + "], P_XREF = [" + P_XREF + "], pm_gdv = [" + pm_gdv + "], pm_ksv = [" + pm_ksv + "]");
            FCCCoreBO fccCoreBO = new FCCCoreBO();
            String result = fccCoreBO.revertTransferFCUBS(P_TRN_REF, P_XREF, pm_gdv, pm_ksv);
            LOGGER.info("revertTransferFCUBS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*Thanh toan Bao hiem (E)*/
 /*Thanh toán dư nợ thẻ tín dụng tại quầy (S)*/
    /**
     *
     * @param cif
     * @param loc
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getListCard(String cif, String loc) throws RemoteException {
        try {
            LOGGER.info("getListCard --> [IN] --> cif = [" + cif + "], loc = [" + loc + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            ArrayList<?> result = fcdbBO.getListCard(cif, loc);
            LOGGER.info("getListCard --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cif
     * @param loc
     * @param statuscode
     * @param branchcode
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> searchPayCCAll(String cif, String loc, String statuscode, String branchcode, String fromdate, String todate) throws RemoteException {
        try {
            LOGGER.info("searchPayCCAll --> [IN] --> cif = [" + cif + "], loc = [" + loc + "], statuscode = [" + statuscode + "], branchcode = [" + branchcode + "], fromdate = [" + fromdate + "], todate = [" + todate + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.searchPayCCAll(cif, loc, statuscode, branchcode, fromdate, todate);
            LOGGER.info("searchPayCCAll --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cif
     * @param loc
     * @param statuscode
     * @param branchcode
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> searchPayCreditCard(String cif, String loc, String statuscode, String branchcode, String fromdate, String todate, String beginRow, String endRow) throws RemoteException {
        try {
            LOGGER.info("searchPayCreditCard --> [IN] --> cif = [" + cif + "], loc = [" + loc + "], statuscode = [" + statuscode + "], branchcode = [" + branchcode + "], fromdate = [" + fromdate + "], todate = [" + todate + "], beginRow = [" + beginRow + "], endRow = [" + endRow + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.searchPayCreditCard(cif, loc, statuscode, branchcode, fromdate, todate, beginRow, endRow);
            LOGGER.info("searchPayCreditCard --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getCardInfoByID(String id) throws RemoteException {
        try {
            LOGGER.info("getCardInfoByID --> [IN] --> id = [" + id + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            ArrayList<?> result = fcdbBO.getCardInfoByID(id);
            LOGGER.info("getCardInfoByID --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
//            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getPayCCInfoByID(int id) throws RemoteException {
        try {
            LOGGER.info("getPayCCInfoByID --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getPayCCInfoByID(id);
            LOGGER.info("getPayCCInfoByID --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param iduser_checker
     * @param idchanneluser_checker
     * @param isapproved
     * @param ref_fcubs
     * @param status
     * @param id
     * @param so_ct
     * @throws RemoteException
     */
    @Override
    public void updatePayCC(String iduser_checker, String idchanneluser_checker, String isapproved, String ref_fcubs, String status, int id, String so_ct) throws RemoteException {
        try {
            LOGGER.info("updatePayCC --> [IN] --> iduser_checker = [" + iduser_checker + "], idchanneluser_checker = [" + idchanneluser_checker + "], isapproved = [" + isapproved + "], ref_fcubs = [" + ref_fcubs + "], status = [" + status + "], id = [" + id + "], so_ct = [" + so_ct + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updatePayCC(iduser_checker, idchanneluser_checker, isapproved, ref_fcubs, status, id, so_ct);
            LOGGER.info("updatePayCC --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param cif
     * @param loc
     * @param cardtype
     * @param cardno
     * @param expi_date
     * @param cardname
     * @param branch_card
     * @param sotien_dtt
     * @param sotien_min
     * @param sotien_sk
     * @param sotien_duno
     * @param sotien_tt
     * @param paymentmethod
     * @param custAcNo
     * @param fullname
     * @param address
     * @param custNo
     * @param iduser_marker
     * @param idchanneluser_maker
     * @param isapproved
     * @param branchcode
     * @param idcard
     * @param idcardDob
     * @param idcardName
     * @param idcardAddress
     * @param sdtKh
     * @param noiCapCMND
     * @return
     * @throws RemoteException
     */
    @Override
    public int insPayCreditCard(int id, String cif, String loc, String cardtype, String cardno, String expi_date, String cardname, String branch_card, BigDecimal sotien_dtt,
            BigDecimal sotien_min, BigDecimal sotien_sk, BigDecimal sotien_duno, BigDecimal sotien_tt, String paymentmethod, String custAcNo, String fullname, String address,
            String custNo, String iduser_marker, String idchanneluser_maker, String isapproved, String branchcode, String idcard, String idcardDob, String idcardName, String idcardAddress) throws RemoteException {
        try {
            LOGGER.info("insPayCreditCard --> [IN] --> id = [" + id + "], cif = [" + cif + "], loc = [" + loc + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insPayCreditCard(id, cif, loc, cardtype, cardno, expi_date, cardname, branch_card, sotien_dtt,
                    sotien_min, sotien_sk, sotien_duno, sotien_tt, paymentmethod, custAcNo, fullname, address, custNo,
                    iduser_marker, idchanneluser_maker, isapproved, branchcode, idcard, idcardDob, idcardName, idcardAddress);
            LOGGER.info("insPayCreditCard --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int insPayCreditCardv2(int id, String cif, String loc, String cardtype, String cardno, String expi_date, String cardname, String branch_card, BigDecimal sotien_dtt,
            BigDecimal sotien_min, BigDecimal sotien_sk, BigDecimal sotien_duno, BigDecimal sotien_tt, String paymentmethod, String custAcNo, String fullname, String address,
            String custNo, String iduser_marker, String idchanneluser_maker, String isapproved, String branchcode, String idcard, String idcardDob, String idcardName, String idcardAddress, String sdtKh, String noiCapCMND) throws RemoteException {
        try {
            LOGGER.info("insPayCreditCard --> [IN] --> id = [" + id + "], cif = [" + cif + "], loc = [" + loc + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insPayCreditCardv2(id, cif, loc, cardtype, cardno, expi_date, cardname, branch_card, sotien_dtt,
                    sotien_min, sotien_sk, sotien_duno, sotien_tt, paymentmethod, custAcNo, fullname, address, custNo,
                    iduser_marker, idchanneluser_maker, isapproved, branchcode, idcard, idcardDob, idcardName, idcardAddress, sdtKh, noiCapCMND);
            LOGGER.info("insPayCreditCard --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
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
     * @throws RemoteException
     */
    @Override
    public String transferFCUBS(String glAcc, BigDecimal amount, String content, String idcardName, String idcardAddress, String idcard, String idcardDob, String user_maker, String user_checker, String branchcard) throws RemoteException {
        try {
            /*
            SmsSCBBO smsscbBO = new SmsSCBBO();
            return smsscbBO.transferFCUBS(glAcc, amount, content, idcardName, idcardAddress, idcard, idcardDob, user_maker, user_checker, branchcard);
             */
            LOGGER.info("transferFCUBS --> [IN] --> glAcc = [" + glAcc + "], amount = [" + amount + "], content = [" + content + "], idcardName = [" + idcardName + "], idcardAddress = [" + idcardAddress + "], idcard = [" + idcard + "]");
            FCCCoreBO fccCoreBO = new FCCCoreBO();
            //Helper.writeLog(FCCCoreBO.class, Level.INFO, "transferFCUBS -" + content);
            String result = fccCoreBO.transferFCUBS(glAcc, amount, content, idcardName, idcardAddress, idcard, idcardDob, user_maker, user_checker, branchcard);
            LOGGER.info("transferFCUBS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*Thanh toán dư nợ thẻ tín dụng tại quầy (E)*/
    /**
     *
     * @param pPartnerID
     * @return
     * @throws RemoteException
     */
    @Override
    public String EVNHN_CreateTranscode(String pPartnerID) throws RemoteException {
        try {
            LOGGER.info("EVNHN_CreateTranscode --> [IN] --> pPartnerID = [" + pPartnerID + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.CreateTranscode(pPartnerID);
            LOGGER.info("EVNHN_CreateTranscode --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //Modify CustAlertTD
    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    @Override
    public int writelogSMSAlertTD(String cust_no) throws RemoteException {
        try {
            LOGGER.info("writelogSMSAlertTD --> [IN] --> cust_no = [" + cust_no + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.writelogSMSAlertTD(cust_no);
            LOGGER.info("writelogSMSAlertTD --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    //Modify CustAlertTD

    /*Đối soát Napas (S)*/
    /**
     *
     * @param np
     * @return
     * @throws RemoteException
     */
    @Override
    public int InsertNapasEcomCollated(NapasCollate np) throws RemoteException {
        try {
            LOGGER.info("InsertNapasEcomCollated --> [IN] --> NapasCollate_ACCOUNTNO = [" + np.getACCOUNTNO() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.InsertNapasEcomCollated(np);
            LOGGER.info("InsertNapasEcomCollated --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @param pCoreref
     * @param pStatus
     * @throws Exception
     */
    @Override
    public void UpdateRefundTransferNAPAS(String ID, String pCoreref, String pStatus) throws Exception {
        try {
            LOGGER.info("UpdateRefundTransferNAPAS --> [IN] --> ID = [" + ID + "], pCoreref = [" + pCoreref + "], pStatus = [" + pStatus + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.UpdateRefundTransferNAPAS(ID, pCoreref, pStatus);
            LOGGER.info("UpdateRefundTransferNAPAS --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pPartnerID
     * @param pMerchanID
     * @param pTransID
     * @param pRefundTransID
     * @param pRefundAmount
     * @param pCCY
     * @param pADDINFO
     * @param pLocalDatetime
     * @return
     * @throws Exception
     */
    @Override
    public String[] CheckRefundTransferBanknet(String pPartnerID, String pMerchanID, String pTransID, String pRefundTransID, BigDecimal pRefundAmount, String pCCY, String pADDINFO, String pLocalDatetime) throws Exception {
        try {
            LOGGER.info("CheckRefundTransferBanknet --> [IN] --> pPartnerID = [" + pPartnerID + "], pTransID = [" + pTransID + "], pRefundTransID = [" + pRefundTransID + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String[] result = smsscbBO.CheckRefundTransferBanknet(pPartnerID, pMerchanID, pTransID, pRefundTransID, pRefundAmount, pCCY, pADDINFO, pLocalDatetime);
            LOGGER.info("CheckRefundTransferBanknet --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public List<NapasCollate> getOutNapasEcomCollate() throws Exception {
        try {
            LOGGER.info("getOutNapasEcomCollate --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List<NapasCollate> result = smsscbBO.getOutNapasEcomCollate();
            LOGGER.info("getOutNapasEcomCollate --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*Đối soát Napas (E)*/
    //Long.Le (E)
    /**
     *
     * @param cif
     * @param cardaccountnumber
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getCreditCardList(String cif, String cardaccountnumber) throws RemoteException {
        try {
            LOGGER.info("getCreditCardList --> [IN] --> cif = [" + cif + "], cardaccountnumber = [" + cardaccountnumber + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getCreditCardList(cif, cardaccountnumber);
            LOGGER.info("getCreditCardList --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param dateFile
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList BCN_getlist_VW_BCN(String dateFile) throws RemoteException {
        try {
            LOGGER.info("BCN_getlist_VW_BCN --> [IN] --> dateFile = [" + dateFile + "]");
            PBL_BillPaidBO BO = new PBL_BillPaidBO();
            ArrayList result = BO.getlist_VW_BCN(dateFile);
            LOGGER.info("BCN_getlist_VW_BCN --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pRESPONSECODE
     * @param pTypeTrans
     * @param pCCY
     * @param pAmount
     * @param pTransdate
     * @param pTranscode
     * @param pPARTNERDETAILID
     * @param pIDBILLPAID
     * @param pACCOUNT
     * @throws RemoteException
     */
    @Override
    public void BCN_RECEIVECOLLATED(String pRESPONSECODE,
            String pTypeTrans,
            String pCCY,
            String pAmount,
            String pTransdate,
            String pTranscode,
            String pPARTNERDETAILID,
            String pIDBILLPAID,
            String pACCOUNT) throws RemoteException {
        try {
            LOGGER.info("BCN_RECEIVECOLLATED --> [IN] --> pRESPONSECODE = [" + pRESPONSECODE + "], pIDBILLPAID = [" + pIDBILLPAID + "], pAmount = [" + pAmount + "]");
            PBL_BillPaidBO BO = new PBL_BillPaidBO();
            BO.BCN_RECEIVECOLLATED(pRESPONSECODE, pTypeTrans, pCCY, pAmount, pTransdate, pTranscode, pPARTNERDETAILID, pIDBILLPAID, pACCOUNT);
            LOGGER.info("BCN_RECEIVECOLLATED --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*
           KET NOI CONG TY CHUNG KHOAN
     */
    /**
     *
     * @param PPARTNERID
     * @param PCUSTUMERACCOUNT
     * @param PCUSTUMERNAME
     * @param PAMOUNT
     * @param PCCY
     * @param PCHANNELID
     * @param PTRANSDATE
     * @param PADDINFO
     * @return
     * @throws RemoteException
     */
    @Override
    public Object[] SI_InsertSITRANFERTOSI(
            String PPARTNERID,
            String PCUSTUMERACCOUNT,
            String PCUSTUMERNAME,
            BigDecimal PAMOUNT,
            String PCCY,
            String PCHANNELID,
            String PTRANSDATE,
            String PADDINFO) throws RemoteException {
        try {
            LOGGER.info("SI_InsertSITRANFERTOSI --> [IN] --> PPARTNERID = [" + PPARTNERID + "], cPCUSTUMERACCOUNTif = [" + PCUSTUMERACCOUNT + "], PAMOUNT = [" + PAMOUNT + "]");
            SIBO BO = new SIBO();
            Object[] result = BO.InsertSITRANFERTOSI(PPARTNERID, PCUSTUMERACCOUNT, PCUSTUMERNAME, PAMOUNT, PCCY, PCHANNELID, PTRANSDATE, PADDINFO);
            LOGGER.info("SI_InsertSITRANFERTOSI --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PID
     * @param PREFCORE
     * @param pSTATUS
     * @param pRESPONSECODE
     * @param pTXNREF
     * @param pDESCRESPONSE
     * @throws RemoteException
     */
    @Override
    public void SI_CONFIRMSITRANFERTOSI(
            double PID,
            String PREFCORE,
            String pSTATUS,
            String pRESPONSECODE,
            String pTXNREF,
            String pDESCRESPONSE) throws RemoteException {
        try {
            LOGGER.info("SI_CONFIRMSITRANFERTOSI --> [IN] --> PID = [" + PID + "], PREFCORE = [" + PREFCORE + "], pTXNREF = [" + pTXNREF + "]");
            SIBO BO = new SIBO();
            BO.CONFIRMSITRANFERTOSI(PID, PREFCORE, pSTATUS, pRESPONSECODE, pTXNREF, pDESCRESPONSE);
            LOGGER.info("SI_CONFIRMSITRANFERTOSI --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pPARTNERID
     * @param pTRANSID
     * @param pTRANSDATE
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] SI_INSERTSITRANSFROMSI(
            String pPARTNERID,
            String pTRANSID,
            String pTRANSDATE
    ) throws RemoteException {
        try {
            LOGGER.info("SI_INSERTSITRANSFROMSI --> [IN] --> pPARTNERID = [" + pPARTNERID + "], pTRANSID = [" + pTRANSID + "], pTRANSDATE = [" + pTRANSDATE + "]");
            SIBO BO = new SIBO();
            String[] result = BO.INSERTSITRANSFROMSI(pPARTNERID, pTRANSID, pTRANSDATE);
            LOGGER.info("SI_INSERTSITRANSFROMSI --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pAccount
     * @param pAmount
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] SI_CHECKACCOUNT(
            String pAccount,
            double pAmount
    ) throws RemoteException {
        try {
            LOGGER.info("SI_CHECKACCOUNT --> [IN] --> pAccount = [" + pAccount + "], pAmount = [" + pAmount + "]");
            SIBO BO = new SIBO();

            Helper.writeLog(DBIImpl.class, Level.INFO, "SI_CHECKACCOUNT" + pAccount);

            String[] result = BO.CHECKACCOUNT(pAccount, pAmount);

            Helper.writeLog(DBIImpl.class, Level.INFO, "SI_CHECKACCOUNT: " + pAccount + "----" + result[0]);

            LOGGER.info("SI_CHECKACCOUNT --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_MASTER
     * @param pTXNDETAILID
     * @param pPARTNERACCOUNT
     * @param pCUSTUMERNAME
     * @param pCUSTUMERACCOUNT
     * @param pBANKCODE
     * @param pBranchname
     * @param pAmount
     * @param pCCY
     * @param pDescription
     * @param pTYPETRANSFER
     * @param pTYPECUSTACCOUNT
     * @return
     * @throws RemoteException
     */
    @Override
    public Object[] SI_INSERTTRANSFERFROMSIDETAIL(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,
            String pCUSTUMERNAME,
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            String pBranchname,
            double pAmount,
            String pCCY,
            String pDescription,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT
    ) throws RemoteException {
        try {
            LOGGER.info("SI_INSERTTRANSFERFROMSIDETAIL --> [IN] --> pID_MASTER = [" + pID_MASTER + "], pTXNDETAILID = [" + pTXNDETAILID + "], pPARTNERACCOUNT = [" + pPARTNERACCOUNT + "], pAmount = [" + pAmount + "]");
            SIBO BO = new SIBO();
            Object[] result = BO.INSERTTRANSFERFROMSIDETAIL(pID_MASTER, pTXNDETAILID, pPARTNERACCOUNT, pCUSTUMERNAME, pCUSTUMERACCOUNT, pBANKCODE, pBranchname, pAmount, pCCY, pDescription, pTYPETRANSFER, pTYPECUSTACCOUNT);
            LOGGER.info("SI_INSERTTRANSFERFROMSIDETAIL --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PID
     * @param pREFCORE
     * @param pVALIDDATECORE
     * @param pSTATUS
     * @throws RemoteException
     */
    @Override
    public void SI_UPDATETRANSFERFROMSIDETAIL(
            double PID,
            String pREFCORE,
            String pVALIDDATECORE,
            String pSTATUS) throws RemoteException {
        try {
            LOGGER.info("SI_UPDATETRANSFERFROMSIDETAIL --> [IN] --> PID = [" + PID + "], pREFCORE = [" + pREFCORE + "], pVALIDDATECORE = [" + pVALIDDATECORE + "], pSTATUS = [" + pSTATUS + "]");
            SIBO BO = new SIBO();
            BO.UPDATETRANSFERFROMSIDETAIL(PID, pREFCORE, pVALIDDATECORE, pSTATUS);
            LOGGER.info("SI_UPDATETRANSFERFROMSIDETAIL --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_MASTER
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList SI_GETLISTTRANSFROMSI(int pID_MASTER) throws RemoteException {
        try {
            LOGGER.info("SI_GETLISTTRANSFROMSI --> [IN] --> pID_MASTER = [" + pID_MASTER + "]");
            SIBO BO = new SIBO();
            ArrayList result = BO.GETLISTTRANSFROMSI(pID_MASTER);
            LOGGER.info("SI_GETLISTTRANSFROMSI --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList SI_getListBank() throws RemoteException {
        try {
            LOGGER.info("SI_getListBank --> [IN] --> input is empty");
            SIBO BO = new SIBO();
            ArrayList result = BO.getListBank();
            LOGGER.info("SI_getListBank --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pBANKCODE
     * @param pACCOUNTNO
     * @param pACCOUNTNAME
     * @param pACCOUNTSI
     * @param pNAMESI
     * @param pAMOUNT
     * @param pCCY
     * @param pSITRANSID
     * @param pTRANSDATE
     * @param pSCBTRANSID
     * @param pTYPETRANS
     * @throws RemoteException
     */
    @Override
    public void SI_InsertCollated(String pBANKCODE,
            String pACCOUNTNO,
            String pACCOUNTNAME,
            String pACCOUNTSI,
            String pNAMESI,
            String pAMOUNT,
            String pCCY,
            String pSITRANSID,
            String pTRANSDATE,
            String pSCBTRANSID,
            String pTYPETRANS) throws RemoteException {
        try {
            LOGGER.info("SI_InsertCollated --> [IN] --> pBANKCODE = [" + pBANKCODE + "], pSITRANSID = [" + pSITRANSID + "], pACCOUNTNO = [" + pACCOUNTNO + "]");
            SIBO BO = new SIBO();
            BO.SI_InsertCollated(pBANKCODE, pACCOUNTNO, pACCOUNTNAME, pACCOUNTSI, pNAMESI, pAMOUNT, pCCY, pSITRANSID, pTRANSDATE, pSCBTRANSID, pTYPETRANS);
            LOGGER.info("SI_InsertCollated --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pDate
     * @param pPartnerid
     * @param pType
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList SI_getOutCollated(Date pDate, String pPartnerid, String pType) throws RemoteException {
        try {
            LOGGER.info("SI_getOutCollated --> [IN] --> pDate = [" + pDate + "], pPartnerid = [" + pPartnerid + "], pType = [" + pType + "]");
            SIBO BO = new SIBO();
            ArrayList result = BO.getOutCollated(pDate, pPartnerid, pType);
            LOGGER.info("SI_getOutCollated --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ProcedureCallDto RegisterAutoBill(String idservicetype, String idprodvider, String customercode, String CIFNO, String UserName, String Mobile, String DebitAccount, String ValidDate, String ExpireDate, String PaymentType) throws RemoteException {
        try {
            LOGGER.info("RegisterAutoBill --> [IN] --> CIFNO = [" + CIFNO + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ProcedureCallDto result = smsscbBO.RegisterAutoBill(idservicetype, idprodvider, customercode, CIFNO, UserName, Mobile, DebitAccount, ValidDate, ExpireDate, PaymentType);
            LOGGER.info("RegisterAutoBill --> [OUT] --> result_ErrorStatus = [" + (result == null ? null : result.getErrorStatus()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_INSERT_NOPTIEN_HQ(HQ_NOPTIEN_HQ obj) throws RemoteException {
        try {
            LOGGER.info("HQ_INSERT_NOPTIEN_HQ --> [IN] --> obj = [" + obj + "]");
            HQBO BO = new HQBO();
            String[] result = BO.INSERT_NOPTIEN_HQ(obj);
            LOGGER.info("HQ_INSERT_NOPTIEN_HQ --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_INSERT_HQ_NOPTIEN_HQ_GNT(HQ_NOPTIEN_HQ_GNT obj) throws RemoteException {
        try {
            LOGGER.info("HQ_INSERT_HQ_NOPTIEN_HQ_GNT --> [IN] --> obj = [" + obj + "]");
            HQBO BO = new HQBO();
            String[] result = BO.INSERT_HQ_NOPTIEN_HQ_GNT(obj);
            LOGGER.info("HQ_INSERT_HQ_NOPTIEN_HQ_GNT --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    @Override
    public void HQ_INSERT_HQ_NOPTIEN_HQ_GNTCT(HQ_NOPTIEN_HQ_GNTCT obj) throws RemoteException {
        try {
            LOGGER.info("HQ_INSERT_HQ_NOPTIEN_HQ_GNTCT --> [IN] --> obj = [" + obj + "]");
            HQBO BO = new HQBO();
            BO.INSERT_HQ_NOPTIEN_HQ_GNTCT(obj);
            LOGGER.info("HQ_INSERT_HQ_NOPTIEN_HQ_GNTCT --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PIDREF
     * @param PTYPE
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_NOPTIEN_CORE(
            int PIDREF,
            String PTYPE //HQ/CQT
    ) throws RemoteException {
        try {
            LOGGER.info("HQ_NOPTIEN_CORE --> [IN] --> PIDREF = [" + PIDREF + "], PTYPE = [" + PTYPE + "]");
            HQBO BO = new HQBO();
            String[] result = BO.NOPTIEN_CORE(PIDREF, PTYPE);
            LOGGER.info("HQ_NOPTIEN_CORE --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_INSERT_HQ_NOPTIEN_CQQLT(HQ_NOPTIEN_CQQLT obj) throws RemoteException {
        try {
            LOGGER.info("HQ_INSERT_HQ_NOPTIEN_CQQLT --> [IN] --> obj = [" + obj + "]");
            HQBO BO = new HQBO();
            String[] result = BO.INSERT_HQ_NOPTIEN_CQQLT(obj);
            LOGGER.info("HQ_INSERT_HQ_NOPTIEN_CQQLT --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    @Override
    public void HQ_INSERT_HQ_NOPTIEN_CQQLT_CT(
            HQ_NOPTIEN_CQQLT_CT obj) throws RemoteException {
        try {
            LOGGER.info("HQ_INSERT_HQ_NOPTIEN_CQQLT_CT --> [IN] --> obj = [" + obj + "]");
            HQBO BO = new HQBO();
            BO.INSERT_HQ_NOPTIEN_CQQLT_CT(obj);
            LOGGER.info("HQ_INSERT_HQ_NOPTIEN_CQQLT_CT --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PIDREF
     * @param PTYPE
     * @param PCHECKERID
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_APPROVETRANSFER(
            int PIDREF,
            String PTYPE,// HQ/CQT
            String PCHECKERID) throws RemoteException {
        try {
            LOGGER.info("HQ_APPROVETRANSFER --> [IN] --> PIDREF = [" + PIDREF + "], PTYPE = [" + PTYPE + "], PCHECKERID = [" + PCHECKERID + "]");
            HQBO BO = new HQBO();
            String[] result = BO.APPROVETRANSFER(PIDREF, PTYPE, PCHECKERID);
            LOGGER.info("HQ_APPROVETRANSFER --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    @Override
    public void HQ_SEND_MESSAGE(
            HQ_MSG obj
    ) throws RemoteException {
        try {
            LOGGER.info("HQ_SEND_MESSAGE --> [IN] --> obj = [" + obj + "]");
            HQBO BO = new HQBO();
            BO.SEND_MESSAGE(obj);
            LOGGER.info("HQ_SEND_MESSAGE --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_NOPTIEN_HQ> HQ_getDataNOPTIEN_HQ(int pID, String pStatus) throws RemoteException {
        try {
            LOGGER.info("HQ_getDataNOPTIEN_HQ --> [IN] --> pID = [" + pID + "], pStatus = [" + pStatus + "]");
            HQBO BO = new HQBO();
            List<HQ_NOPTIEN_HQ> result = BO.getDataNOPTIEN_HQ(pID, pStatus);
            LOGGER.info("HQ_getDataNOPTIEN_HQ --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_NOPTIEN_HQ_GNT> HQ_getDataNOPTIEN_HQ_GNT(int pID, String pStatus) throws RemoteException {
        try {
            LOGGER.info("HQ_getDataNOPTIEN_HQ_GNT --> [IN] --> pID = [" + pID + "], pStatus = [" + pStatus + "]");
            HQBO BO = new HQBO();
            List<HQ_NOPTIEN_HQ_GNT> result = BO.getDataNOPTIEN_HQ_GNT(pID, pStatus);
            LOGGER.info("HQ_getDataNOPTIEN_HQ_GNT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_NOPTIEN_HQ_GNTCT> HQ_getDataNOPTIEN_HQ_GNTCT(int pID, String pStatus) throws RemoteException {
        try {
            LOGGER.info("HQ_getDataNOPTIEN_HQ_GNTCT --> [IN] --> pID = [" + pID + "], pStatus = [" + pStatus + "]");
            HQBO BO = new HQBO();
            List<HQ_NOPTIEN_HQ_GNTCT> result = BO.getDataNOPTIEN_HQ_GNTCT(pID, pStatus);
            LOGGER.info("HQ_getDataNOPTIEN_HQ_GNTCT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_INSERT_HQ_BAOLANH_TK(HQ_BAOLANH_TK obj) throws RemoteException {
        try {
            LOGGER.info("HQ_INSERT_HQ_BAOLANH_TK --> [IN] --> obj = [" + obj + "]");
            HQBO BO = new HQBO();
            String[] result = BO.INSERT_HQ_BAOLANH_TK(obj);
            LOGGER.info("HQ_INSERT_HQ_BAOLANH_TK --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_INSERT_HQ_BAOLANH_CHUNG(HQ_BAOLANH_CHUNG obj) throws RemoteException {
        try {
            LOGGER.info("HQ_INSERT_HQ_BAOLANH_CHUNG --> [IN] --> obj = [" + obj + "]");
            HQBO BO = new HQBO();
            String[] result = BO.INSERT_HQ_BAOLANH_CHUNG(obj);
            LOGGER.info("HQ_INSERT_HQ_BAOLANH_CHUNG --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_INSERT_HQ_BAOLANH_HDVD(HQ_BAOLANH_HDVD obj) throws RemoteException {
        try {
            LOGGER.info("HQ_INSERT_HQ_BAOLANH_HDVD --> [IN] --> obj = [" + obj + "]");
            HQBO BO = new HQBO();
            String[] result = BO.INSERT_HQ_BAOLANH_HDVD(obj);
            LOGGER.info("HQ_INSERT_HQ_BAOLANH_HDVD --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList HQ_selectCHUONG(String ID) throws RemoteException {
        try {
            LOGGER.info("HQ_selectCHUONG --> [IN] --> ID = [" + ID + "]");
            HQBO BO = new HQBO();
            ArrayList result = BO.selectCHUONG(ID);
            LOGGER.info("HQ_selectCHUONG --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList HQ_selectKBNN(String ID) throws RemoteException {
        try {
            LOGGER.info("HQ_selectKBNN --> [IN] --> ID = [" + ID + "]");
            HQBO BO = new HQBO();
            ArrayList result = BO.selectKBNN(ID);
            LOGGER.info("HQ_selectKBNN --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList HQ_selectNTK(String ID) throws RemoteException {
        try {
            LOGGER.info("HQ_selectNTK --> [IN] --> ID = [" + ID + "]");
            HQBO BO = new HQBO();
            ArrayList result = BO.selectNTK(ID);
            LOGGER.info("HQ_selectNTK --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList HQ_selectCCY(String ID) throws RemoteException {
        try {
            LOGGER.info("HQ_selectCCY --> [IN] --> ID = [" + ID + "]");
            HQBO BO = new HQBO();
            ArrayList result = BO.selectCCY(ID);
            LOGGER.info("HQ_selectCCY --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public String HQ_getTransID() throws RemoteException {
        try {
            LOGGER.info("HQ_getTransID --> [IN] --> input is empty");
            HQBO BO = new HQBO();
            String result = BO.getTransID();
            LOGGER.info("HQ_getTransID --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public String HQ_getSOCT() throws RemoteException {
        try {
            LOGGER.info("HQ_getSOCT --> [IN] --> input is empty");
            HQBO BO = new HQBO();
            String result = BO.getSOCT();
            LOGGER.info("HQ_getSOCT --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pMSGTYPE
     * @return
     * @throws RemoteException
     */
    @Override
    public String HQ_getREQUESTID(String pID, String pMSGTYPE) throws RemoteException {
        try {
            LOGGER.info("HQ_getREQUESTID --> [IN] --> pID = [" + pID + "], pMSGTYPE = [" + pMSGTYPE + "]");
            HQBO BO = new HQBO();
            String result = BO.getREQUESTID(pID, pMSGTYPE);
            LOGGER.info("HQ_getREQUESTID --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_NOPTIEN_CQQLT> HQ_getDataNOPTIEN_HQ_CQQLT(int pID, String pStatus) throws RemoteException {
        try {
            LOGGER.info("HQ_getDataNOPTIEN_HQ_CQQLT --> [IN] --> pID = [" + pID + "], pStatus = [" + pStatus + "]");
            HQBO BO = new HQBO();
            List<HQ_NOPTIEN_CQQLT> result = BO.getDataNOPTIEN_HQ_CQQLT(pID, pStatus);
            LOGGER.info("HQ_getDataNOPTIEN_HQ_CQQLT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_NOPTIEN_CQQLT_CT> HQ_getDataNOPTIEN_HQ_CQQLT_CT(int pID, String pStatus) throws RemoteException {
        try {
            LOGGER.info("HQ_getDataNOPTIEN_HQ_CQQLT_CT --> [IN] --> pID = [" + pID + "], pStatus = [" + pStatus + "]");
            HQBO BO = new HQBO();
            List<HQ_NOPTIEN_CQQLT_CT> result = BO.getDataNOPTIEN_HQ_CQQLT_CT(pID, pStatus);
            LOGGER.info("HQ_getDataNOPTIEN_HQ_CQQLT_CT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_BAOLANH_TK> HQ_getDataBAOLANH_TK(int pID, String pStatus) throws RemoteException {
        try {
            LOGGER.info("HQ_getDataBAOLANH_TK --> [IN] --> pID = [" + pID + "], pStatus = [" + pStatus + "]");
            HQBO BO = new HQBO();
            List<HQ_BAOLANH_TK> result = BO.getDataBAOLANH_TK(pID, pStatus);
            LOGGER.info("HQ_getDataBAOLANH_TK --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_BAOLANH_CHUNG> HQ_getDataBAOLANH_CHUNG(int pID, String pStatus) throws RemoteException {
        try {
            LOGGER.info("HQ_getDataBAOLANH_CHUNG --> [IN] --> pID = [" + pID + "], pStatus = [" + pStatus + "]");
            HQBO BO = new HQBO();
            List<HQ_BAOLANH_CHUNG> result = BO.getDataBAOLANH_CHUNG(pID, pStatus);
            LOGGER.info("HQ_getDataBAOLANH_CHUNG --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_BAOLANH_HDVD> HQ_getDataBAOLANH_HDVD(int pID, String pStatus) throws RemoteException {
        try {
            LOGGER.info("HQ_getDataBAOLANH_HDVD --> [IN] --> pID = [" + pID + "], pStatus = [" + pStatus + "]");
            HQBO BO = new HQBO();
            List<HQ_BAOLANH_HDVD> result = BO.getDataBAOLANH_HDVD(pID, pStatus);
            LOGGER.info("HQ_getDataBAOLANH_HDVD --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMADV
     * @param pSO_TK
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param FromRow
     * @param ToRow
     * @param typeTrans
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_NOPTIEN_HQ> HQ_searchNOPTIEN_HQ(String pMADV,
            String pSO_TK,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            String pSO_CT,
            String pKYHIEU_CT,
            int FromRow,
            int ToRow,
            String typeTrans) throws RemoteException {
        try {
            LOGGER.info("HQ_searchNOPTIEN_HQ --> [IN] --> pSO_CT = [" + pSO_CT + "], pSO_TK = [" + pSO_TK + "]");
            HQBO BO = new HQBO();
            List<HQ_NOPTIEN_HQ> result = BO.searchNOPTIEN_HQ(pMADV, pSO_TK, pFromdate, pTodate, pBranchCode, pStatus, pSO_CT, pKYHIEU_CT, FromRow, ToRow, typeTrans);
            LOGGER.info("HQ_searchNOPTIEN_HQ --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMADV
     * @param pSO_TK
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param typeTrans
     * @return
     * @throws RemoteException
     */
    @Override
    public int HQ_getSumRow(String pMADV,
            String pSO_TK,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            String pSO_CT,
            String pKYHIEU_CT,
            String typeTrans) throws RemoteException {
        try {
            LOGGER.info("HQ_getSumRow --> [IN] --> pSO_CT = [" + pSO_CT + "], pSO_TK = [" + pSO_TK + "]");
            HQBO BO = new HQBO();
            int result = BO.getSumRow(pMADV, pSO_TK, pFromdate, pTodate, pBranchCode, pStatus, pSO_CT, pKYHIEU_CT, typeTrans);
            LOGGER.info("HQ_getSumRow --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    @Override
    public void HQ_updateNOPTIEN_HQ(HQ_NOPTIEN_HQ obj) throws RemoteException {
        try {
            LOGGER.info("HQ_updateNOPTIEN_HQ --> [IN] --> obj = [" + obj + "]");
            HQBO BO = new HQBO();
            BO.updateNOPTIEN_HQ(obj);
            LOGGER.info("HQ_updateNOPTIEN_HQ --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pMsgType
     * @param pStatus
     * @throws RemoteException
     */
    @Override
    public void HQ_updateStatus(int pID, String pMsgType, String pStatus) throws RemoteException {
        try {
            LOGGER.info("HQ_updateStatus --> [IN] --> pID = [" + pID + "], pMsgType = [" + pMsgType + "], pStatus = [" + pStatus + "]");
            HQBO BO = new HQBO();
            BO.updateStatus(pID, pMsgType, pStatus);
            LOGGER.info("HQ_updateStatus --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pMsgType
     * @param pMakerID
     * @param pBranchID
     * @throws RemoteException
     */
    @Override
    public void HQ_deleteData(int pID, String pMsgType,
            String pMakerID,
            String pBranchID) throws RemoteException {
        try {
            LOGGER.info("HQ_deleteData --> [IN] --> pID = [" + pID + "], pMsgType = [" + pMsgType + "], pMakerID = [" + pMakerID + "], pBranchID = [" + pBranchID + "]");
            HQBO BO = new HQBO();
            BO.deleteData(pID, pMsgType, pMakerID, pBranchID);
            LOGGER.info("HQ_deleteData --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList selectNDKT(String ID) throws Exception {
        try {
            LOGGER.info("selectNDKT --> [IN] --> ID = [" + ID + "]");
            HQBO BO = new HQBO();
            ArrayList result = BO.selectNDKT(ID);
            LOGGER.info("selectNDKT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param BGNUMBER
     * @return
     * @throws Exception
     */
    @Override
    public String[] HQ_GETBGINFO(String BGNUMBER) throws Exception {
        try {
            LOGGER.info("HQ_GETBGINFO --> [IN] --> BGNUMBER = [" + BGNUMBER + "]");
            HQBO BO = new HQBO();
            String[] result = BO.GETBGINFO(BGNUMBER);
            LOGGER.info("HQ_GETBGINFO --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param MsgType
     * @param pDate
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_DOICHIEU> HQ_getDataDoichieu(String MsgType, Date pDate) throws RemoteException {
        try {
            LOGGER.info("HQ_getDataDoichieu --> [IN] --> MsgType = [" + MsgType + "], pDate = [" + pDate + "]");
            HQBO BO = new HQBO();
            List<HQ_DOICHIEU> result = BO.getDataDoichieu(MsgType, pDate);
            LOGGER.info("HQ_getDataDoichieu --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pNGAY_DC
     * @param pTRANSACTIONID
     * @param PMSGTYPE
     * @param status
     * @param errnumber
     * @param errmsg
     * @throws RemoteException
     */
    @Override
    public void HQ_InsertDoiChieu(String pNGAY_DC,
            String pTRANSACTIONID,
            String PMSGTYPE,
            String status,
            String errnumber,
            String errmsg) throws RemoteException {
        try {
            LOGGER.info("HQ_InsertDoiChieu --> [IN] --> pTRANSACTIONID = [" + pTRANSACTIONID + "], status = [" + status + "]");
            HQBO BO = new HQBO();
            BO.InsertDoiChieu(pNGAY_DC, pTRANSACTIONID, PMSGTYPE, status, errnumber, errmsg);
            LOGGER.info("HQ_InsertDoiChieu --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMsgType
     * @param pIDREF
     * @param pNGAY_DC
     * @param pKQDC
     * @param pTRANSACTIONID
     * @throws RemoteException
     */
    @Override
    public void HQ_InsertKQDoiChieu(
            String pMsgType,
            String pIDREF,
            String pNGAY_DC,
            String pKQDC,
            String pTRANSACTIONID
    ) throws RemoteException {
        try {
            LOGGER.info("HQ_InsertKQDoiChieu --> [IN] --> pIDREF = [" + pIDREF + "], pTRANSACTIONID = [" + pTRANSACTIONID + "]");
            HQBO BO = new HQBO();
            BO.InsertKQDoiChieu(pMsgType, pIDREF, pNGAY_DC, pKQDC, pTRANSACTIONID);
            LOGGER.info("HQ_InsertKQDoiChieu --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pMSGTYPE
     * @return
     * @throws RemoteException
     */
    @Override
    public String HQ_getSO_TN_CT(String pID,
            String pMSGTYPE) throws RemoteException {
        try {
            LOGGER.info("HQ_getSO_TN_CT --> [IN] --> pID = [" + pID + "], pMSGTYPE = [" + pMSGTYPE + "]");
            HQBO BO = new HQBO();
            String result = BO.getSO_TN_CT(pID, pMSGTYPE);
            LOGGER.info("HQ_getSO_TN_CT --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param LOAIDM
     * @param ID
     * @param ten
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList HQ_selectDANHMUC(String LOAIDM, String ID, String ten) throws Exception {
        try {
            LOGGER.info("HQ_selectDANHMUC --> [IN] --> LOAIDM = [" + LOAIDM + "], ID = [" + ID + "], ten = [" + ten + "]");
            HQBO BO = new HQBO();
            ArrayList result = BO.selectDANHMUC(LOAIDM, ID, ten);
            LOGGER.info("HQ_selectDANHMUC --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PIDREF
     * @param PTYPE
     * @param PCHECKERID
     * @return
     * @throws Exception
     */
    @Override
    public String[] HQ_APPROVEBAOLANH(
            int PIDREF,
            String PTYPE,// TK/CHUNG/HDVD
            String PCHECKERID) throws Exception {
        try {
            LOGGER.info("HQ_APPROVEBAOLANH --> [IN] --> PIDREF = [" + PIDREF + "], PTYPE = [" + PTYPE + "], PCHECKERID = [" + PCHECKERID + "]");
            HQBO BO = new HQBO();
            String[] result = BO.APPROVEBAOLANH(PIDREF, PTYPE, PCHECKERID);
            LOGGER.info("HQ_APPROVEBAOLANH --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pTRANSACTION_ID
     * @param pSO_TN_CT
     * @param pNGAY_TN_CT
     * @param pKQ_DC
     * @param pNGAY_DC
     * @param pTYPETRANS
     * @param pMSGTYPE
     * @throws RemoteException
     */
    @Override
    public void HQ_insertKQDOICHIEUHUY(
            String pTRANSACTION_ID,
            String pSO_TN_CT,
            String pNGAY_TN_CT,
            String pKQ_DC,
            String pNGAY_DC,
            String pTYPETRANS,
            String pMSGTYPE
    ) throws RemoteException {
        try {
            LOGGER.info("HQ_insertKQDOICHIEUHUY --> [IN] --> pTRANSACTION_ID = [" + pTRANSACTION_ID + "], pSO_TN_CT = [" + pSO_TN_CT
                    + "], pNGAY_TN_CT = [" + pNGAY_TN_CT + "], pKQ_DC = [" + pKQ_DC + "], pNGAY_DC = [" + pNGAY_DC + "], pTYPETRANS = ["
                    + pTYPETRANS + "], pMSGTYPE = [" + pMSGTYPE + "]");
            HQBO BO = new HQBO();
            BO.insertKQDOICHIEUHUY(pTRANSACTION_ID, pSO_TN_CT, pNGAY_TN_CT, pKQ_DC, pNGAY_DC, pTYPETRANS, pMSGTYPE);
            LOGGER.info("HQ_insertKQDOICHIEUHUY --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pSo_HS
     * @param pMST
     * @param pSO_CT
     * @param pKHCT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pFromRow
     * @param pToRow
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_NOPTIEN_CQQLT> HQ_searchNOPTIEN_CQQLT(
            String pSo_HS,
            String pMST,
            String pSO_CT,
            String pKHCT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws RemoteException {
        try {
            LOGGER.info("HQ_searchNOPTIEN_CQQLT --> [IN] --> pSo_HS = [" + pSo_HS + "], pMST = [" + pMST + "], pSO_CT = [" + pSO_CT
                    + "], pKHCT = [" + pKHCT + "], pFromdate = [" + pFromdate + "], pTodate = [" + pTodate + "], pBranchCode = ["
                    + pBranchCode + "], pStatus = [" + pStatus + "], pFromRow = [" + pFromRow + "], pToRow = [" + pToRow + "]");
            HQBO BO = new HQBO();
            List<HQ_NOPTIEN_CQQLT> result = BO.searchNOPTIEN_CQQLT(pSo_HS, pMST, pSO_CT, pKHCT, pFromdate, pTodate, pBranchCode, pStatus, pFromRow, pToRow);
            LOGGER.info("HQ_searchNOPTIEN_CQQLT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pSo_HS
     * @param pMST
     * @param pSO_CT
     * @param pKHCT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    @Override
    public int getSumRowNOPTIEN_CQQLT(
            String pSo_HS,
            String pMST,
            String pSO_CT,
            String pKHCT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus
    ) throws RemoteException {
        try {
            LOGGER.info("getSumRowNOPTIEN_CQQLT --> [IN] --> pSo_HS = [" + pSo_HS + "], pMST = [" + pMST + "], pSO_CT = [" + pSO_CT
                    + "], pKHCT = [" + pKHCT + "], pFromdate = [" + pFromdate + "], pTodate = [" + pTodate + "], pBranchCode = ["
                    + pBranchCode + "], pStatus = [" + pStatus + "]");
            HQBO BO = new HQBO();
            int result = BO.getSumRowNOPTIEN_CQQLT(pSo_HS, pMST, pSO_CT, pKHCT, pFromdate, pTodate, pBranchCode, pStatus);
            LOGGER.info("getSumRowNOPTIEN_CQQLT --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    @Override
    public void HQ_UPDATE_HQ_BAOLANH_TK(HQ_BAOLANH_TK obj) throws RemoteException {
        try {
            LOGGER.info("HQ_UPDATE_HQ_BAOLANH_TK --> [IN] --> HQ_BAOLANH_TK = [" + obj + "]");
            HQBO BO = new HQBO();
            BO.UPDATE_HQ_BAOLANH_TK(obj);
            LOGGER.info("HQ_UPDATE_HQ_BAOLANH_TK --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    @Override
    public void HQ_UPDATE_HQ_BAOLANH_CHUNG(HQ_BAOLANH_CHUNG obj) throws RemoteException {
        try {
            LOGGER.info("HQ_UPDATE_HQ_BAOLANH_CHUNG --> [IN] --> HQ_BAOLANH_CHUNG = [" + obj + "]");
            HQBO BO = new HQBO();
            BO.UPDATE_HQ_BAOLANH_CHUNG(obj);
            LOGGER.info("HQ_UPDATE_HQ_BAOLANH_CHUNG --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    @Override
    public void HQ_UPDATE_HQ_BAOLANH_HDVD(HQ_BAOLANH_HDVD obj) throws RemoteException {
        try {
            LOGGER.info("HQ_UPDATE_HQ_BAOLANH_HDVD --> [IN] --> HQ_BAOLANH_HDVD = [" + obj + "]");
            HQBO BO = new HQBO();
            BO.UPDATE_HQ_BAOLANH_HDVD(obj);
            LOGGER.info("HQ_UPDATE_HQ_BAOLANH_HDVD --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    @Override
    public void HQ_UPDATE_HQ_NOPTIEN_CQQLT(HQ_NOPTIEN_CQQLT obj) throws RemoteException {
        try {
            LOGGER.info("HQ_UPDATE_HQ_NOPTIEN_CQQLT --> [IN] --> HQ_NOPTIEN_CQQLT = [" + obj + "]");
            HQBO BO = new HQBO();
            BO.UPDATE_HQ_NOPTIEN_CQQLT(obj);
            LOGGER.info("HQ_UPDATE_HQ_NOPTIEN_CQQLT --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMST
     * @param pSO_TK
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pFromRow
     * @param pToRow
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_BAOLANH_TK> HQ_searchBAOLANH_TK(
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws RemoteException {
        try {
            LOGGER.info("HQ_searchBAOLANH_TK --> [IN] --> pMST = [" + pMST + "], pSO_TK = [" + pSO_TK + "], pSO_CT = [" + pSO_CT
                    + "], pKYHIEU_CT = [" + pKYHIEU_CT + "], pFromdate = [" + pFromdate + "], pTodate = [" + pTodate + "], pBranchCode = ["
                    + pBranchCode + "], pStatus = [" + pStatus + "], pFromRow = [" + pFromRow + "], pToRow = [" + pToRow + "]");
            HQBO BO = new HQBO();
            List<HQ_BAOLANH_TK> result = BO.searchBAOLANH_TK(pMST, pSO_TK, pSO_CT, pKYHIEU_CT, pFromdate, pTodate, pBranchCode, pStatus, pFromRow, pToRow);
            LOGGER.info("HQ_searchBAOLANH_TK --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMST
     * @param pSO_TK
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pFromRow
     * @param pToRow
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_BAOLANH_CHUNG> HQ_searchBAOLANH_CHUNG(
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws RemoteException {
        try {
            LOGGER.info("HQ_searchBAOLANH_CHUNG --> [IN] --> pMST = [" + pMST + "], pSO_TK = [" + pSO_TK + "], pSO_CT = [" + pSO_CT
                    + "], pKYHIEU_CT = [" + pKYHIEU_CT + "], pFromdate = [" + pFromdate + "], pTodate = [" + pTodate + "], pBranchCode = ["
                    + pBranchCode + "], pStatus = [" + pStatus + "], pFromRow = [" + pFromRow + "], pToRow = [" + pToRow + "]");
            HQBO BO = new HQBO();
            List<HQ_BAOLANH_CHUNG> result = BO.searchBAOLANH_CHUNG(pMST, pSO_TK, pSO_CT, pKYHIEU_CT, pFromdate, pTodate, pBranchCode, pStatus, pFromRow, pToRow);
            LOGGER.info("HQ_searchBAOLANH_CHUNG --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMST
     * @param pSO_TK
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @param pFromRow
     * @param pToRow
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_BAOLANH_HDVD> HQ_searchBAOLANH_HDVD(
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus,
            int pFromRow,
            int pToRow) throws RemoteException {
        try {
            LOGGER.info("HQ_searchBAOLANH_HDVD --> [IN] --> pMST = [" + pMST + "], pSO_TK = [" + pSO_TK + "], pSO_CT = [" + pSO_CT
                    + "], pKYHIEU_CT = [" + pKYHIEU_CT + "], pFromdate = [" + pFromdate + "], pTodate = [" + pTodate + "], pBranchCode = ["
                    + pBranchCode + "], pStatus = [" + pStatus + "], pFromRow = [" + pFromRow + "], pToRow = [" + pToRow + "]");
            HQBO BO = new HQBO();
            List<HQ_BAOLANH_HDVD> result = BO.searchBAOLANH_HDVD(pMST, pSO_TK, pSO_CT, pKYHIEU_CT, pFromdate, pTodate, pBranchCode, pStatus, pFromRow, pToRow);
            LOGGER.info("HQ_searchBAOLANH_HDVD --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMSGTYPE
     * @param pMST
     * @param pSO_TK
     * @param pSO_CT
     * @param pKYHIEU_CT
     * @param pFromdate
     * @param pTodate
     * @param pBranchCode
     * @param pStatus
     * @return
     * @throws RemoteException
     */
    @Override
    public int HQ_getSumSearchBAOLANH(
            String pMSGTYPE,
            String pMST,
            String pSO_TK,
            String pSO_CT,
            String pKYHIEU_CT,
            Date pFromdate,
            Date pTodate,
            String pBranchCode,
            String pStatus) throws RemoteException {
        try {
            LOGGER.info("HQ_getSumSearchBAOLANH --> [IN] --> pMST = [" + pMST + "], pSO_TK = [" + pSO_TK + "], pSO_CT = [" + pSO_CT
                    + "], pKYHIEU_CT = [" + pKYHIEU_CT + "], pFromdate = [" + pFromdate + "], pTodate = [" + pTodate + "], pBranchCode = ["
                    + pBranchCode + "], pStatus = [" + pStatus + "], pMSGTYPE = [" + pMSGTYPE + "]");
            HQBO BO = new HQBO();
            int result = BO.getSumSearchBAOLANH(pMSGTYPE, pMST, pSO_TK, pSO_CT, pKYHIEU_CT, pFromdate, pTodate, pBranchCode, pStatus);
            LOGGER.info("HQ_getSumSearchBAOLANH --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMA_LH
     * @param pTEN_LH
     * @param pSN_AH
     * @throws RemoteException
     */
    @Override
    public void HQ_insertDM_LH(String pMA_LH,
            String pTEN_LH,
            String pSN_AH) throws RemoteException {
        try {
            LOGGER.info("HQ_insertDM_LH --> [IN] --> pMA_LH = [" + pMA_LH + "], pTEN_LH = [" + pTEN_LH + "], pSN_AH = [" + pSN_AH + "]");
            HQBO BO = new HQBO();
            BO.insertDM_LH(pMA_LH, pTEN_LH, pSN_AH);
            LOGGER.info("HQ_insertDM_LH --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMa_HQ
     * @param pTen_HQ
     * @param pMa_Cu
     * @param pMa_QHNS
     * @throws RemoteException
     */
    @Override
    public void HQ_insertDM_HQ(String pMa_HQ,
            String pTen_HQ,
            String pMa_Cu,
            String pMa_QHNS) throws RemoteException {
        try {
            LOGGER.info("HQ_insertDM_HQ --> [IN] --> pMa_HQ = [" + pMa_HQ + "], pTen_HQ = [" + pTen_HQ + "], pMa_Cu = [" + pMa_Cu + "], pMa_QHNS = [" + pMa_QHNS + "]");
            HQBO BO = new HQBO();
            BO.insertDM_HQ(pMa_HQ, pTen_HQ, pMa_Cu, pMa_QHNS);
            LOGGER.info("HQ_insertDM_HQ --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMa_KB
     * @param pTen_KB
     * @throws RemoteException
     */
    @Override
    public void HQ_insertDM_KB(String pMa_KB,
            String pTen_KB
    ) throws RemoteException {
        try {
            LOGGER.info("HQ_insertDM_KB --> [IN] --> pMa_KB = [" + pMa_KB + "], pTen_KB = [" + pTen_KB + "]");
            HQBO BO = new HQBO();
            BO.insertDM_KB(pMa_KB, pTen_KB);
            LOGGER.info("HQ_insertDM_KB --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pTransID
     * @param pParnerID
     * @throws RemoteException
     */
    @Override
    public void PaymentAndRegister(String pTransID, String pParnerID) throws RemoteException {
        try {
            LOGGER.info("PaymentAndRegister --> [IN] --> pTransID = [" + pTransID + "], pParnerID = [" + pParnerID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.PaymentAndRegister(pTransID, pParnerID);
            LOGGER.info("PaymentAndRegister --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pCardNumber
     * @param pParnerid
     * @return
     * @throws RemoteException
     */
    @Override
    public String getProfileID(
            String pCardNumber,
            String pParnerid) throws RemoteException {
        try {
            LOGGER.info("getProfileID --> [IN] --> pCardNumber = [" + pCardNumber + "], pParnerid = [" + pParnerid + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.getProfileID(pCardNumber, pParnerid);
            LOGGER.info("getProfileID --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     */
    @Override
    public String getKMFromPhoneNumber(String phoneNumber) throws RemoteException {
        try {
            LOGGER.info("getKMFromPhoneNumber --> [IN] --> phoneNumber= [" + phoneNumber + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.getKMFromPhoneNumber(phoneNumber);
            LOGGER.info("getKMFromPhoneNumber --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param TransID
     * @param PartnerID
     * @param PhoneNumber
     * @param ChannelID
     * @param TransType
     * @param OTP
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] OTP_REQUEST(String TransID, String PartnerID, String PhoneNumber,
            String ChannelID, String TransType, String OTP) throws RemoteException {
        try {
            LOGGER.info("OTP_REQUEST --> [IN] --> TransID = [" + TransID + "], PartnerID = [" + PartnerID
                    + "], PhoneNumber = [" + PhoneNumber + "], ChannelID = [" + ChannelID
                    + "], TransType = [" + TransType + "], TransType = [" + TransType + "], OTP = [" + OTP + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.OTP_REQUEST(TransID, PartnerID, PhoneNumber, ChannelID, TransType, OTP);
            LOGGER.info("OTP_REQUEST --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param TransID
     * @param RefTransID
     * @param PartnerID
     * @param ChannelID
     * @param TransType
     * @param OTP
     * @param TransDate
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] OTP_RESPONSE(String TransID, String RefTransID, String PartnerID,
            String ChannelID, String TransType, String OTP, String TransDate) throws RemoteException {
        try {
            LOGGER.info("OTP_RESPONSE --> [IN] --> TransID = [" + TransID + "], PartnerID = [" + PartnerID
                    + "], RefTransID = [" + RefTransID + "], ChannelID = [" + ChannelID
                    + "], TransType = [" + TransType + "], TransType = [" + TransType + "], OTP = ["
                    + OTP + "] TransDate = [" + TransDate + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.OTP_RESPONSE(TransID, RefTransID, PartnerID, ChannelID, TransType, OTP, TransDate);
            LOGGER.info("OTP_RESPONSE --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    //endduytxaduytxa08072015
    //duytxa16062017
    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public List getListAutoFeePayKHCN2() throws RemoteException {
        try {
            LOGGER.info("getListAutoFeePayKHCN2 --> [IN] --> input is empty");
            SmsSCBBO smsscbbo = new SmsSCBBO();//neu connect fcdb dung :FcdbBO, gateway dung:SmsSCBBO
            smsscbbo.setPersistentClass(VwSmsThuphiKHCN2.class);
            List result = smsscbbo.findAll();
            LOGGER.info("getListAutoFeePayKHCN2 --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public List getListAutoFeePayKHDN() throws RemoteException {
        try {
            LOGGER.info("getListAutoFeePayKHDN --> [IN] --> input is empty");
            SmsSCBBO smsscbbo = new SmsSCBBO();//neu connect fcdb dung :FcdbBO, gateway dung:SmsSCBBO
            smsscbbo.setPersistentClass(VwSmsThuphiKHDN.class);
            List result = smsscbbo.findAll();
            LOGGER.info("getListAutoFeePayKHDN --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public List getListAutoFeeUnlock() throws RemoteException {
        try {
            LOGGER.info("getListAutoFeeUnlock --> [IN] --> input is empty");
            SmsSCBBO smsscbbo = new SmsSCBBO();//neu connect fcdb dung :FcdbBO, gateway dung:SmsSCBBO
            smsscbbo.setPersistentClass(VwSmsThuphiUnlock.class);
            List result = smsscbbo.findAll();
            LOGGER.info("getListAutoFeeUnlock --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //end duytxa16062017
    /**
     *
     * @param cifno
     * @return
     * @throws RemoteException
     */
    @Override
    public List<ThuPhiSMS> selectDSThuPhiEbank(String cifno) throws RemoteException {
        try {
            LOGGER.info("selectDSThuPhiEbank --> [IN] --> cifno = [" + cifno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List<ThuPhiSMS> result = smsscbBO.selectDSThuPhiEbank(cifno);
            LOGGER.info("selectDSThuPhiEbank --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cifno
     * @param refcore
     * @param usermaker
     * @param chekeruser
     * @param paydate
     * @return
     * @throws RemoteException
     */
    @Override
    public ProcedureCallDto UnlockUserThuPhi(String cifno, String refcore, String usermaker, String chekeruser, Date paydate) throws RemoteException {
        try {
            LOGGER.info("UnlockUserThuPhi --> [IN] --> cifno = [" + cifno + "], refcore = [" + refcore
                    + "], usermaker = [" + usermaker + "], chekeruser = [" + chekeruser
                    + "], paydate = [" + paydate + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ProcedureCallDto result = smsscbBO.UnlockUserThuPhi(cifno, refcore, usermaker, chekeruser, paydate);
            LOGGER.info("UnlockUserThuPhi --> [OUT] --> result_ErrorStatus = [" + (result == null ? null : result.getErrorStatus()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param feeTrans
     * @return
     * @throws Exception
     */
    @Override
    public int insertFeeTransactionUnlock(FeeTransactionUnlock feeTrans) throws Exception {
        try {
            LOGGER.info("insertFeeTransactionUnlock --> [IN] --> FeeTransactionUnlock = [" + feeTrans + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertFeeTransactionUnlock(feeTrans);
            LOGGER.info("insertFeeTransactionUnlock --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param feeTrans
     * @throws Exception
     */
    @Override
    public void updateFeeTransactionUnlock(FeeTransactionUnlock feeTrans) throws Exception {
        try {
            LOGGER.info("updateFeeTransactionUnlock --> [IN] --> FeeTransactionUnlock = [" + feeTrans + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updateFeeTransactionUnlock(feeTrans);
            LOGGER.info("updateFeeTransactionUnlock --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public FeeTransactionUnlock getFeeTransactionUnlockId(int id) throws Exception {
        try {
            LOGGER.info("getFeeTransactionUnlockId --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            FeeTransactionUnlock result = smsscbBO.getFeeTransactionUnlockId(id);
            LOGGER.info("getFeeTransactionUnlockId --> [OUT] --> result_cif = [" + (result == null ? null : result.getCif()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param feeTrans
     * @return
     * @throws Exception
     */
    @Override
    public List<FeeTransactionUnlock> getFeeTransactionUnlockList(FeeTransactionUnlock feeTrans) throws Exception {
        try {
            LOGGER.info("getFeeTransactionUnlockList --> [IN] --> FeeTransactionUnlock = [" + feeTrans + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List<FeeTransactionUnlock> result = smsscbBO.getFeeTransactionUnlockList(feeTrans);
            LOGGER.info("getFeeTransactionUnlockList --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cifno
     * @return
     * @throws RemoteException
     */
    @Override
    public ProcedureCallDto getAccThuPhi(String cifno) throws RemoteException {
        try {
            LOGGER.info("getAccThuPhi --> [IN] --> cifno = [" + cifno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ProcedureCallDto result = smsscbBO.getAccThuPhi(cifno);
            LOGGER.info("getAccThuPhi --> [OUT] --> result_ErrorStatus = [" + (result == null ? null : result.getErrorStatus()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cifno
     * @param refcore
     * @param paydate
     * @return
     * @throws RemoteException
     */
    @Override
    public ProcedureCallDto updateThuphi(String cifno, String refcore, Date paydate) throws RemoteException {
        try {
            LOGGER.info("updateThuphi --> [IN] --> cifno = [" + cifno + "], refcore = [" + refcore + "], paydate = [" + paydate + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ProcedureCallDto result = smsscbBO.updateThuphi(cifno, refcore, paydate);
            LOGGER.info("updateThuphi --> [OUT] --> result_ErrorStatus = [" + (result == null ? null : result.getErrorStatus()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param P_DATE
     * @return
     * @throws RemoteException
     */
    @Override
    public String working_day(Date P_DATE) throws RemoteException {
        try {
            LOGGER.info("working_day --> [IN] --> P_DATE = [" + P_DATE + "]");
            NTDTBO BO = new NTDTBO();
            String result = BO.working_day(P_DATE);
            LOGGER.info("working_day --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param P_DATE
     * @param P_TYPE
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getListINS(Date P_DATE, String P_TYPE) throws RemoteException {
        try {
            LOGGER.info("getListINS --> [IN] --> P_DATE = [" + P_DATE + "], P_TYPE = [" + P_TYPE + "]");
            NTDTBO BO = new NTDTBO();
            ArrayList<?> result = BO.getListINS(P_DATE, P_TYPE);
            LOGGER.info("getListINS --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param P_DATE
     * @param P_TYPE
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getListINS_BKN(Date P_DATE, String P_TYPE) throws RemoteException {
        try {
            LOGGER.info("getListINS_BKN --> [IN] --> P_DATE = [" + P_DATE + "], P_TYPE = [" + P_TYPE + "]");
            NTDTBO BO = new NTDTBO();
            ArrayList<?> result = BO.getListINS_BKN(P_DATE, P_TYPE);
            LOGGER.info("getListINS_BKN --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param servType
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getListPartner(String servType) throws RemoteException {
        try {
            LOGGER.info("getListPartner --> [IN] --> servType = [" + servType + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getListPartner(servType);
            LOGGER.info("getListPartner --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param dataxml
     * @param idbillpaid
     * @return
     * @throws RemoteException
     */
    @Override
    public int updDataxmlByIdbillpaid(String dataxml, String idbillpaid) throws RemoteException {
        try {
            LOGGER.info("updDataxmlByIdbillpaid --> [IN] --> dataxml = [" + dataxml + "], idbillpaid = [" + idbillpaid + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updDataxmlByIdbillpaid(dataxml, idbillpaid);
            LOGGER.info("updDataxmlByIdbillpaid --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param P_IDSERVICETYPE
     * @param P_IDPROVIDER
     * @param cusCode
     * @return
     * @throws RemoteException
     */
    @Override
    public ProcedureCallDto getListBill(String P_IDSERVICETYPE, String P_IDPROVIDER, String cusCode) throws RemoteException {
        try {
            LOGGER.info("getListBill --> [IN] --> P_IDSERVICETYPE = [" + P_IDSERVICETYPE + "], P_IDPROVIDER = [" + P_IDPROVIDER + "], cusCode = [" + cusCode + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ProcedureCallDto result = smsscbBO.getListBill(P_IDSERVICETYPE, P_IDPROVIDER, cusCode);
            LOGGER.info("getListBill --> [OUT] --> result_ErrorStatus = [" + (result == null ? null : result.getErrorStatus()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ref_fcubs
     * @param idbillpaid
     * @return
     * @throws RemoteException
     */
    @Override
    public int updStatusByIdbillpaid(String ref_fcubs, String idbillpaid) throws RemoteException {
        try {
            LOGGER.info("updStatusByIdbillpaid --> [IN] --> ref_fcubs = [" + ref_fcubs + "], idbillpaid = [" + idbillpaid + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updStatusByIdbillpaid(ref_fcubs, idbillpaid);
            LOGGER.info("updStatusByIdbillpaid --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchTBTBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws RemoteException {
        try {
            LOGGER.info("searchTBTBill --> [IN] --> id = [" + id + "], idtransaction = [" + idtransaction
                    + "], partner = [" + partner + "], customercode = [" + customercode
                    + "], statusBill = [" + statusBill + "], fromdate = [" + fromdate + "], todate = ["
                    + todate + "], beginRow = [" + beginRow + "], endRow = [" + endRow + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchTBTBill(id, idtransaction, partner, customercode, statusBill, fromdate, todate, beginRow, endRow);
            LOGGER.info("searchTBTBill --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateStatusTBT(String id, String status) throws RemoteException {
        try {
            LOGGER.info("updateStatusTBT --> [IN] --> id = [" + id + "], status = [" + status + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateStatusTBT(id, status);
            LOGGER.info("updateStatusTBT --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchTBTBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws RemoteException {
        try {
            LOGGER.info("searchTBTBillAll --> [IN] --> id = [" + id + "], idtransaction = [" + idtransaction
                    + "], partner = [" + partner + "], customercode = [" + customercode
                    + "], statusBill = [" + statusBill + "], fromdate = [" + fromdate
                    + "], todate = [" + todate + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchTBTBillAll(id, idtransaction, partner, customercode, statusBill, fromdate, todate);
            LOGGER.info("searchTBTBillAll --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchIBLBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws RemoteException {
        try {
            LOGGER.info("searchIBLBillAll --> [IN] --> id = [" + id + "], idtransaction = ["
                    + idtransaction + "], partner = [" + partner + "], customercode = [" + customercode
                    + "], statusBill = [" + statusBill + "], fromdate = [" + fromdate + "], todate = ["
                    + todate + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchIBLBillAll(id, idtransaction, partner, customercode, statusBill, fromdate, todate);
            LOGGER.info("searchIBLBillAll --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchIBLBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws RemoteException {
        try {
            LOGGER.info("searchIBLBill --> [IN] --> id = [" + id + "], idtransaction = [" + idtransaction + "], partner = [" + partner + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchIBLBill(id, idtransaction, partner, customercode, statusBill, fromdate, todate, beginRow, endRow);
            LOGGER.info("searchIBLBill --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateStatusIBL(String id, String status) throws RemoteException {
        try {
            LOGGER.info("updateStatusIBL --> [IN] --> id = [" + id + "], status = [" + status + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateStatusIBL(id, status);
            LOGGER.info("updateStatusIBL --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchTVSIBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws RemoteException {
        try {
            LOGGER.info("searchTVSIBillAll --> [IN] --> id = [" + id + "], idtransaction = [" + idtransaction + "], partner = [" + partner + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchTVSIBillAll(id, idtransaction, partner, customercode, statusBill, fromdate, todate);
            LOGGER.info("searchTVSIBillAll --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchTVSIBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws RemoteException {
        try {
            LOGGER.info("searchTVSIBill --> [IN] --> id = [" + id + "], idtransaction = [" + idtransaction + "], partner = [" + partner + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchTVSIBill(id, idtransaction, partner, customercode, statusBill, fromdate, todate, beginRow, endRow);
            LOGGER.info("searchTVSIBill --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateStatusTVSI(String id, String status) throws RemoteException {
        try {
            LOGGER.info("updateStatusTVSI --> [IN] --> id = [" + id + "], status = [" + status + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateStatusTVSI(id, status);
            LOGGER.info("updateStatusTVSI --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchTVSI_Bill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws RemoteException {
        try {
            LOGGER.info("searchTVSI_Bill --> [IN] --> id = [" + id + "], idtransaction = [" + idtransaction + "], partner = [" + partner + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchTVSI_Bill(id, idtransaction, partner, customercode, statusBill, fromdate, todate, beginRow, endRow);
            LOGGER.info("searchTVSI_Bill --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateStatus_TVSI(String id, String status) throws RemoteException {
        try {
            LOGGER.info("updateStatus_TVSI --> [IN] --> id = [" + id + "], status = [" + status + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateStatus_TVSI(id, status);
            LOGGER.info("updateStatus_TVSI --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchVTCBill(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws RemoteException {
        try {
            LOGGER.info("searchVTCBill --> [IN] --> id = [" + id + "], idtransaction = [" + idtransaction + "], partner = [" + partner + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchVTCBill(id, idtransaction, partner, customercode, statusBill, fromdate, todate, beginRow, endRow);
            LOGGER.info("searchVTCBill --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateStatusVTC(String id, String status) throws RemoteException {
        try {
            LOGGER.info("updateStatusVTC --> [IN] --> id = [" + id + "], status = [" + status + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateStatusVTC(id, status);
            LOGGER.info("updateStatusVTC --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchVTCBillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws RemoteException {
        try {
            LOGGER.info("searchVTCBillAll --> [IN] --> id = [" + id + "], idtransaction = [" + idtransaction + "], partner = [" + partner + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchVTCBillAll(id, idtransaction, partner, customercode, statusBill, fromdate, todate);
            LOGGER.info("searchVTCBillAll --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*Thanh toán phí bảo hiểm tại quầy (S) -- Thachdn*/
    /**
     *
     * @param ownerId
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getListPolNum(String ownerId) throws RemoteException {
        try {
            LOGGER.info("getListPolNum --> [IN] --> ownerId = [" + ownerId + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            ArrayList<?> result = fcdbBO.getListPolNum(ownerId);
            LOGGER.info("getListPolNum --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*Thanh toán phí bảo hiểm tại quầy (S) -- Thachdn*/
    /**
     *
     * @param premtyp
     * @param polNum
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getListDataPay(String premtyp, String polNum) throws RemoteException {
        try {
            LOGGER.info("getListDataPay --> [IN] --> premtyp = [" + premtyp + "], polNum = [" + polNum + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            ArrayList<?> result = fcdbBO.getListDataPay(premtyp, polNum);
            LOGGER.info("getListDataPay --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*Thanh toán phí bảo hiểm tại quầy (S) -- Thachdn*/
    /**
     *
     * @param polNum
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getListPremtyp(String polNum) throws RemoteException {
        try {
            LOGGER.info("getListPremtyp --> [IN] --> polNum = [" + polNum + "]");
            FCDBTOSMSSCBBO fcdbBO = new FCDBTOSMSSCBBO();
            ArrayList<?> result = fcdbBO.getListPremtyp(polNum);
            LOGGER.info("getListPremtyp --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getInsurencePayByID(int id) throws RemoteException {
        try {
            LOGGER.info("getInsurencePayByID --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getInsurencePayByID(id);
            LOGGER.info("getInsurencePayByID --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<?> getOnlTranStatus() throws RemoteException {
        try {
            LOGGER.info("getOnlTranStatus --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getOnlTranStatus();
            LOGGER.info("getOnlTranStatus --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<?> getRetailtatus() throws RemoteException {
        try {
            LOGGER.info("getRetailtatus --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getRetailtatus();
            LOGGER.info("getRetailtatus --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<?> getInsStatus() throws RemoteException {
        try {
            LOGGER.info("getInsStatus --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getInsStatus();
            LOGGER.info("getInsStatus --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<?> getSiStatus() throws RemoteException {
        try {
            LOGGER.info("getSiStatus --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getSiStatus();
            LOGGER.info("getSiStatus --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<?> getSmlStatus() throws RemoteException {
        try {
            LOGGER.info("getSmlStatus --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getSmlStatus();
            LOGGER.info("getSmlStatus --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList getAllListInsuranceTrn() throws RemoteException {
        try {
            LOGGER.info("getAllListInsuranceTrn --> [IN] --> input is empty");
            FcdbBO fcdbBO = new FcdbBO();
            ArrayList result = fcdbBO.getAllListInsuranceTrn();
            LOGGER.info("getAllListInsuranceTrn --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param bcbill
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertBCBill(BCBill bcbill) throws RemoteException {
        try {
            LOGGER.info("insertBCBill --> [IN] --> bcbill = [" + bcbill + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertBCBill(bcbill);
            LOGGER.info("insertBCBill --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param bcbill
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertBCBill(List<BCBill> bcbill) throws RemoteException {
        try {
            LOGGER.info("insertBCBill --> [IN] --> List<BCBill> = [" + bcbill.size() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertBCBill(bcbill);
            LOGGER.info("insertBCBill --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public int insertBCBillHistory() throws RemoteException {
        try {
            LOGGER.info("insertBCBillHistory --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertBCBillHistory();
            LOGGER.info("insertBCBillHistory --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public int insertIStatusNo() throws RemoteException {
        try {
            LOGGER.info("insertIStatusNo --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertIStatusNo();
            LOGGER.info("insertIStatusNo --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public int insertIStatusYes() throws RemoteException {
        try {
            LOGGER.info("insertIStatusYes --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertIStatusYes();
            LOGGER.info("insertIStatusYes --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public int deleteBCBill() throws RemoteException {
        try {
            LOGGER.info("deleteBCBill --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.deleteBCBill();
            LOGGER.info("deleteBCBill --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<?> getAllListpRoviderIns() throws RemoteException {
        try {
            LOGGER.info("getAllListpRoviderIns --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getAllListpRoviderIns();
            LOGGER.info("getAllListpRoviderIns --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getInsByidpayment(int id) throws RemoteException {
        try {
            LOGGER.info("getInsByidpayment --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getInsByidpayment(id);
            LOGGER.info("getInsByidpayment --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<?> getAllPartnerMNL() throws RemoteException {
        try {
            LOGGER.info("getAllPartnerMNL --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getAllPartnerMNL();
            LOGGER.info("getAllPartnerMNL --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pol
     * @param due
     * @param pre
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getBcBilId(String pol, String due, String pre) throws RemoteException {
        try {
            LOGGER.info("getBcBilId --> [IN] --> pol = [" + pol + "], due = [" + due + "], pre = [" + pre + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getBcBilId(pol, due, pre);
            LOGGER.info("getBcBilId --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param fromDateSearch
     * @param toDateSearch
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getlistAllInsPayment(String fromDateSearch, String toDateSearch) throws RemoteException {
        try {
            LOGGER.info("getlistAllInsPayment --> [IN] --> fromDateSearch = [" + fromDateSearch + "], toDateSearch = [" + toDateSearch + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getlistAllInsPayment(fromDateSearch, toDateSearch);
            LOGGER.info("getlistAllInsPayment --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*CK Tan Viet (S)*/
    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<?> getAllListInsurancePayment() throws RemoteException {
        try {
            LOGGER.info("getAllListInsurancePayment --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getAllListInsurancePayment();
            LOGGER.info("getAllListInsurancePayment --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*CK Tan Viet (S)*/
    /**
     *
     * @param sql
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getAllListIns(String sql) throws RemoteException {
        try {
            LOGGER.info("getAllListIns --> [IN] --> sql = [" + sql + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getAllListIns(sql);
            LOGGER.info("getAllListIns --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param idtransaction
     * @param partner
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchTVSI_BillAll(String id, String idtransaction, String partner, String customercode, String statusBill, String fromdate, String todate) throws RemoteException {
        try {
            LOGGER.info("searchTVSI_BillAll --> [IN] --> id = [" + id + "], idtransaction = [" + idtransaction + "], partner = [" + partner + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchTVSI_BillAll(id, idtransaction, partner, customercode, statusBill, fromdate, todate);
            LOGGER.info("searchTVSI_BillAll --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pbt
     * @return
     */
    @Override
    public int insertPblBillpaidTransReverse(PblBillpaidTransReverse pbt) {
        try {
            LOGGER.info("insertPblBillpaidTransReverse --> [IN] --> PblBillpaidTransReverse = [" + pbt + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertPblBillpaidTransReverse(pbt);
            LOGGER.info("insertPblBillpaidTransReverse --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        }
    }

    /**
     *
     * @param pbt
     */
    @Override
    public void updatePblBillpaidTransReverse(PblBillpaidTransReverse pbt) {
        try {
            LOGGER.info("updatePblBillpaidTransReverse --> [IN] --> PblBillpaidTransReverse = [" + pbt + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updatePblBillpaidTransReverse(pbt);
            LOGGER.info("updatePblBillpaidTransReverse --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);

        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public PblBillpaidTransReverse getPblBillpaidTransReverseById(int id) throws RemoteException {
        try {
            LOGGER.info("getPblBillpaidTransReverseById --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            PblBillpaidTransReverse result = smsscbBO.getPblBillpaidTransReverseById(id);
            LOGGER.info("function --> [OUT] --> result_idbillpaid = [" + (result == null ? null : result.getIdbillpaid()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param polnum
     * @param isapproved
     * @param status
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @param beginRow
     * @param endRow
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchMNLBill(String id, String polnum, String isapproved, String status, String customercode, String statusBill, String fromdate, String todate, String beginRow, String endRow) throws RemoteException {
        try {
            LOGGER.info("searchMNLBill --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchMNLBill(id, polnum, isapproved, status, customercode, statusBill, fromdate, todate, beginRow, endRow);
            LOGGER.info("searchMNLBill --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param status
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateStatusMNL(String id, String status) throws RemoteException {
        try {
            LOGGER.info("updateStatusMNL --> [IN] --> id = [" + id + "], status = [" + status + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateStatusMNL(id, status);
            LOGGER.info("updateStatusMNL --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param polnum
     * @param isapproved
     * @param status
     * @param customercode
     * @param statusBill
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList searchMNLBillAll(String id, String polnum, String isapproved, String status, String customercode, String statusBill, String fromdate, String todate) throws RemoteException {
        try {
            LOGGER.info("searchMNLBillAll --> [IN] --> id = [" + id + "], polnum = [" + polnum + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList result = smsscbBO.searchMNLBillAll(id, polnum, isapproved, status, customercode, statusBill, fromdate, todate);
            LOGGER.info("searchMNLBillAll --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param branchcode
     * @return
     * @throws RemoteException
     */
    @Override
    public List GetDanhMucQuaTang(String branchcode) throws RemoteException {
        try {
            LOGGER.info("GetDanhMucQuaTang --> [IN] --> branchcode = [" + branchcode + "]");
            DvkhBO BO = new DvkhBO();
            List result = BO.GetDanhMucQuaTang(branchcode);
            LOGGER.info("GetDanhMucQuaTang --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cusdetail
     * @param gitfdetail
     * @return
     * @throws RemoteException
     */
    @Override
    public String GhiNhanThongTinDoiQua(GifCustomerDetail cusdetail, GiftDetail gitfdetail) throws RemoteException {
        try {
            LOGGER.info("GhiNhanThongTinDoiQua --> [IN] --> cusdetail = [" + cusdetail + "], gitfdetail = [" + gitfdetail + "]");
            DvkhBO BO = new DvkhBO();
            String result = BO.GhiNhanThongTinDoiQua(cusdetail, gitfdetail);
            LOGGER.info("GhiNhanThongTinDoiQua --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
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
     * @throws RemoteException
     */
    @Override
    public String PaymentLoanAccount(String fromAcc, String loanAcc, BigDecimal laitronghan, BigDecimal vontronghan, BigDecimal laiphatlai, BigDecimal laitphatgoc,
            BigDecimal laiphatkhac, String maker, String checker) throws RemoteException {
        try {
            LOGGER.info("PaymentLoanAccount --> [IN] --> fromAcc = [" + fromAcc + "], loanAcc = [" + loanAcc + "], laitronghan = [" + laitronghan + "]");
            FCCCoreBO fccCoreBO = new FCCCoreBO();
            String result = fccCoreBO.PaymentLoanAccount(fromAcc, loanAcc, laitronghan, vontronghan, laiphatlai, laitphatgoc, laiphatkhac, maker, checker);
            LOGGER.info("PaymentLoanAccount --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param loc
     * @param pan
     * @param fromdate
     * @param todate
     * @return
     * @throws RemoteException
     */
    @Override
    public List LayLichSuDoiQua(String loc, String pan, String fromdate, String todate) throws RemoteException {
        try {
            LOGGER.info("LayLichSuDoiQua --> [IN] --> loc = [" + loc + "], pan = [" + pan + "], fromdate = [" + fromdate + "], todate = [" + todate + "]");
            DvkhBO BO = new DvkhBO();
            List result = BO.LayLichSuDoiQua(loc, pan, fromdate, todate);
            LOGGER.info("LayLichSuDoiQua --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* not use
    @Override
    public List GetAccountListByType(String custno, String grouptype) throws RemoteException {
        try {
            if (grouptype.contains("M")) {
                CWLiveBO cwLiveBO = new CWLiveBO();
                List listCard = cwLiveBO.GetAccountListByCustNoCardNew(custno);
                return listCard;
            } else {
                FcdbBO BO = new FcdbBO();
                List listAcc = BO.GetAccountListByType(custno, grouptype);
                return listAcc;
            }
        } catch (Exception ex) {
LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }*/
    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public List laydanhdachsanphambaohiem() throws RemoteException {
        try {
            LOGGER.info("laydanhdachsanphambaohiem --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.laydanhdachsanphambaohiem();
            LOGGER.info("laydanhdachsanphambaohiem --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param IdSanPham
     * @return
     * @throws RemoteException
     */
    @Override
    public List chitietsanphambaohiem(String IdSanPham) throws RemoteException {
        try {
            LOGGER.info("chitietsanphambaohiem --> [IN] --> IdSanPham = [" + IdSanPham + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.chitietsanphambaohiem(IdSanPham);
            LOGGER.info("chitietsanphambaohiem --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param Ownerid
     * @param Polnum
     * @return
     * @throws RemoteException
     */
    @Override
    public List thongtinkhmanulife(String Ownerid, String Polnum) throws RemoteException {
        try {
            LOGGER.info("thongtinkhmanulife --> [IN] --> Ownerid = [" + Ownerid + "], Polnum = [" + Polnum + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.thongtinkhmanulife(Ownerid, Polnum);
            LOGGER.info("thongtinkhmanulife --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    @Override
    public List danhsachhoadontudong(String custno) throws RemoteException {
        try {
            LOGGER.info("danhsachhoadontudong --> [IN] --> custno = [" + custno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.danhsachhoadontudong(custno);
            LOGGER.info("danhsachhoadontudong --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idautoreg
     * @return
     * @throws RemoteException
     */
    @Override
    public int huyhoadontudong(String idautoreg) throws RemoteException {
        try {
            LOGGER.info("huyhoadontudong --> [IN] --> idautoreg = [" + idautoreg + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.huyhoadontudong(idautoreg);
            LOGGER.info("huyhoadontudong --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* --- SUNRISE --- */
    /**
     *
     * @param maKH
     * @param cardNo
     * @param cardAccountNo
     * @param accountNo
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] QUERY_PERSONAL_INFORMATION(String maKH, String cardNo, String cardAccountNo, String accountNo) throws RemoteException {
        try {
            LOGGER.info("QUERY_PERSONAL_INFORMATION --> [IN] --> maKH = [" + maKH + "], cardNo = [" + cardNo + "], cardAccountNo = [" + cardAccountNo + "], accountNo = [" + accountNo + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.QUERY_PERSONAL_INFORMATION(maKH, cardNo, cardAccountNo, accountNo);
            LOGGER.info("QUERY_PERSONAL_INFORMATION --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param MaKH
     * @return
     * @throws RemoteException
     */
    @Override
    public int KIEM_TRA_THONG_TIN_KHACH_HANG_IS_EXISTING(String MaKH) throws RemoteException {
        try {
            LOGGER.info("KIEM_TRA_THONG_TIN_KHACH_HANG_IS_EXISTING --> [IN] --> MaKH = [" + MaKH + "]");
            DvkhBO BO = new DvkhBO();
            int result = BO.KIEM_TRA_THONG_TIN_KHACH_HANG_IS_EXISTING(MaKH);
            LOGGER.info("KIEM_TRA_THONG_TIN_KHACH_HANG_IS_EXISTING --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cardAccountNo
     * @return
     * @throws RemoteException
     */
    @Override
    public int GET_TOTAL_POINT_PENDING(String cardAccountNo) throws RemoteException {
        try {
            LOGGER.info("GET_TOTAL_POINT_PENDING --> [IN] --> cardAccountNo = [" + cardAccountNo + "]");
            DvkhBO BO = new DvkhBO();
            int result = BO.GET_TOTAL_POINT_PENDING(cardAccountNo);
            LOGGER.info("GET_TOTAL_POINT_PENDING --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @param MaKH
     * @param HoTen
     * @param CMND
     * @param DiaChi
     * @param NgayCap
     * @param NoiCap
     * @param GioiTinh
     * @param DienThoai
     * @param Loc
     * @param Pan
     * @param locPan
     * @param LoaiThe
     * @param HangThe
     * @param CrdStat
     * @param BrchCode
     * @param TheChinhPhu
     * @param TemLock
     * @param LocLMT
     * @param MaDVNhap
     * @param UidNhap
     * @param VNAID
     * @param Ho
     * @param TenTenDem
     * @return
     * @throws RemoteException
     */
    @Override
    public String INSERT_THONG_TIN_KHACH_HANG(int ID, String MaKH, String HoTen, String CMND,
            String DiaChi, String NgayCap, String NoiCap, String GioiTinh, String DienThoai,
            String Loc, String Pan, String locPan, int LoaiThe, int HangThe, String CrdStat, String BrchCode,
            String TheChinhPhu, String TemLock, String LocLMT, String MaDVNhap,
            int UidNhap, String VNAID, String Ho, String TenTenDem) throws RemoteException {
        try {
            LOGGER.info("INSERT_THONG_TIN_KHACH_HANG --> [IN] --> ID = [" + ID + "], MaKH = [" + MaKH + "], HoTen = [" + HoTen + "]");
            DvkhBO BO = new DvkhBO();
            String result = BO.INSERT_THONG_TIN_KHACH_HANG(ID, MaKH, HoTen, CMND, DiaChi, NgayCap, NoiCap,
                    GioiTinh, DienThoai, Loc, Pan, locPan, LoaiThe, HangThe, CrdStat, BrchCode, TheChinhPhu,
                    TemLock, LocLMT, MaDVNhap, UidNhap, VNAID, Ho, TenTenDem);
            LOGGER.info("INSERT_THONG_TIN_KHACH_HANG --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pTransid
     * @param pRefTransid
     * @param pAmount
     * @param pTransdate
     * @param pDescription
     * @param pPartnerid
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] REFUND_PAYMENT_WITH_ACCOUNT(String pTransid, String pRefTransid,
            Double pAmount, String pTransdate, String pDescription,
            String pPartnerid) throws RemoteException {
        try {
            LOGGER.info("REFUND_PAYMENT_WITH_ACCOUNT --> [IN] --> pTransid = [" + pTransid + "], pRefTransid = [" + pRefTransid + "], pAmount = [" + pAmount + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.REFUND_PAYMENT_WITH_ACCOUNT(pTransid, pRefTransid, pAmount, pTransdate, pDescription, pPartnerid);
            LOGGER.info("REFUND_PAYMENT_WITH_ACCOUNT --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* --- SUNRISE --- */
    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public String[] getListAccountClassRedemEbank() throws RemoteException {
        try {
//            FcdbBO fcdbBO = new FcdbBO();
//            return fcdbBO.getListAccountClassRedemEbank();
            LOGGER.info("getListAccountClassRedemEbank --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String[] result = smsscbBO.getListAccountClassRedemEbank();
            LOGGER.info("getListAccountClassRedemEbank --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ITRANSID
     * @param ICUSTACCCOUNT
     * @param IAMOUNT
     * @param ICCY
     * @param ITRANSDATE
     * @param IPARTNERID
     * @param IDESCRIPTION
     * @param IMERCHANTID
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_PAYMENTWITHACCOUNT(String ITRANSID, String ICUSTACCCOUNT,
            Double IAMOUNT, String ICCY, String ITRANSDATE, String IPARTNERID,
            String IDESCRIPTION, String IMERCHANTID) throws RemoteException {
        try {
            LOGGER.info("ONL_PAYMENTWITHACCOUNT --> [IN] --> ITRANSID = [" + ITRANSID + "], ICUSTACCCOUNT = [" + ICUSTACCCOUNT + "], IAMOUNT = [" + IAMOUNT + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.PAYMENTWITHACCOUNT(ITRANSID, ICUSTACCCOUNT, IAMOUNT, ICCY, ITRANSDATE, IPARTNERID, IDESCRIPTION, IMERCHANTID);
            LOGGER.info("ONL_PAYMENTWITHACCOUNT --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param fromDate
     * @param toDate
     * @return
     * @throws RemoteException
     */
    @Override
    public List getTransactionMaterCardByCardnoByTime(String panceCD, String fromDate, String toDate, String SR, String rownum) throws Exception {
        try {
            LOGGER.info("getTransactionMaterCardByCardnoByTime --> [IN] --> panceCD = [" + panceCD + "], fromDate = [" + fromDate + "], toDate = [" + toDate + "], SR = [" + SR + "], rownum = [" + rownum + "]");
            CWLiveBO cwLiveBo = new CWLiveBO();
            List result = cwLiveBo.getTransactionMaterCardByCardnoByTime(panceCD, fromDate, toDate, SR, rownum);
            LOGGER.info("getTransactionMaterCardByCardnoByTime --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cifno
     * @return
     * @throws RemoteException
     */
    @Override
    public List GetListInsuranceByCif(String cifno) throws RemoteException {
        try {
            LOGGER.info("GetListInsuranceByCif --> [IN] --> cifno = [" + cifno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.GetListInsuranceByCif(cifno);
            LOGGER.info("GetListInsuranceByCif --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param iddangky
     * @return
     * @throws RemoteException
     */
    @Override
    public Insurance GetInsuranceById(int iddangky) throws RemoteException {
        try {
            LOGGER.info("GetInsuranceById --> [IN] --> iddangky = [" + iddangky + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            Insurance result = smsscbBO.GetInsuranceById(iddangky);
            LOGGER.info("GetInsuranceById --> [OUT] --> result_idREG = [" + (result == null ? null : result.getIdREG()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param branchcode
     * @param accountclass
     * @param maturedate
     * @return
     * @throws RemoteException
     */
    @Override
    public ProcedureCallDto verifyOpenTD(String branchcode, String accountclass, String maturedate) throws RemoteException {
        try {
            LOGGER.info("verifyOpenTD --> [IN] --> branchcode = [" + branchcode + "], accountclass = [" + accountclass + "], maturedate = [" + maturedate + "]");
            FcdbBO fcdbBO = new FcdbBO();
            ProcedureCallDto result = fcdbBO.verifyOpenTD(branchcode, accountclass, maturedate);
            LOGGER.info("verifyOpenTD --> [OUT] --> result_ErrorStatus = [" + (result == null ? null : result.getErrorStatus()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    /**
     *
     * @param accountNumber
     * @return
     * @throws RemoteException
     */
    @Override
    public Object[] getAccountBalance(String accountNumber) throws RemoteException {
        try {
            LOGGER.info("getAccountBalance --> [IN] --> accountNumber = [" + accountNumber + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            Object[] result = BO.getAccountBalance(accountNumber);
            LOGGER.info("getAccountBalance --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ONEPAY */
    /**
     *
     * @param ITRANSID
     * @param IPARTNERID
     * @param ICARDNUMBER
     * @param ICARDNAME
     * @param ICARDDATE
     * @param ITRANSDATE
     * @param IDESCRIPTION
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONEPAY_CARD_VERIFICATION(String ITRANSID, String IPARTNERID,
            String ICARDNUMBER, String ICARDNAME, String ICARDDATE, String ITRANSDATE,
            String IDESCRIPTION) throws RemoteException {
        try {
            LOGGER.info("ONEPAY_CARD_VERIFICATION --> [IN] --> ITRANSID = [" + ITRANSID + "], IPARTNERID = [" + IPARTNERID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.ONEPAY_CARD_VERIFICATION(ITRANSID, IPARTNERID, ICARDNUMBER, ICARDNAME, ICARDDATE, ITRANSDATE, IDESCRIPTION);
            LOGGER.info("ONEPAY_CARD_VERIFICATION --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ITRANSID
     * @param IREFTRANSID
     * @param IPARTNERID
     * @param ICHANNELID
     * @param IVERIFYTYPE
     * @param IOTP
     * @param ITRANSDATE
     * @param IDESCRIPTION
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONEPAY_OTP_VERIFICATION(String ITRANSID, String IREFTRANSID,
            String IPARTNERID, String ICHANNELID, String IVERIFYTYPE, String IOTP,
            String ITRANSDATE, String IDESCRIPTION) throws RemoteException {
        try {
            LOGGER.info("ONEPAY_OTP_VERIFICATION --> [IN] --> ITRANSID = [" + ITRANSID + "], IREFTRANSID = [" + IREFTRANSID + "], IPARTNERID = [" + IPARTNERID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.ONEPAY_OTP_VERIFICATION(ITRANSID, IREFTRANSID, IPARTNERID, ICHANNELID,
                    IVERIFYTYPE, IOTP, ITRANSDATE, IDESCRIPTION);
            LOGGER.info("ONEPAY_OTP_VERIFICATION --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ITRANSID
     * @param IREFTRANSID
     * @param IPARTNERID
     * @param IMERCHANTID
     * @param ICARDNUMBER
     * @param ICARDNAME
     * @param ICARDDATE
     * @param IAMOUNT
     * @param ICCY
     * @param ITRANSDATE
     * @param ICHANNELID
     * @param IDESCRIPTION
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONEPAY_PAYMENT(String ITRANSID, String IREFTRANSID,
            String IPARTNERID, String IMERCHANTID, String ICARDNUMBER, String ICARDNAME,
            String ICARDDATE, Double IAMOUNT, String ICCY, String ITRANSDATE,
            String ICHANNELID, String IDESCRIPTION) throws RemoteException {
        try {
            LOGGER.info("ONEPAY_PAYMENT --> [IN] --> ITRANSID = [" + ITRANSID + "], IREFTRANSID = [" + IREFTRANSID + "], IPARTNERID = [" + IPARTNERID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.ONEPAY_PAYMENT(ITRANSID, IREFTRANSID, IPARTNERID, IMERCHANTID, ICARDNUMBER,
                    ICARDNAME, ICARDDATE, IAMOUNT, ICCY, ITRANSDATE, ICHANNELID, IDESCRIPTION);
            LOGGER.info("ONEPAY_PAYMENT --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ITRANSID
     * @param IREFTRANSID
     * @param IPARTNERID
     * @param IMERCHANTID
     * @param ICARDNUMBER
     * @param ICARDNAME
     * @param ICARDDATE
     * @param IAMOUNT
     * @param ICCY
     * @param ITRANSDATE
     * @param ICHANNELID
     * @param IDESCRIPTION
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONEPAY_PAYMENT_WITHOUT_OTP(String ITRANSID, String IREFTRANSID,
            String IPARTNERID, String IMERCHANTID, String ICARDNUMBER, String ICARDNAME,
            String ICARDDATE, Double IAMOUNT, String ICCY, String ITRANSDATE,
            String ICHANNELID, String IDESCRIPTION) throws RemoteException {
        try {
            LOGGER.info("ONEPAY_PAYMENT_WITHOUT_OTP --> [IN] --> ITRANSID = [" + ITRANSID + "], IREFTRANSID = [" + IREFTRANSID + "], IPARTNERID = [" + IPARTNERID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.ONEPAY_PAYMENT_WITHOUT_OTP(ITRANSID, IREFTRANSID, IPARTNERID, IMERCHANTID, ICARDNUMBER,
                    ICARDNAME, ICARDDATE, IAMOUNT, ICCY, ITRANSDATE, ICHANNELID, IDESCRIPTION);
            LOGGER.info("ONEPAY_PAYMENT_WITHOUT_OTP --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param RefTransID
     * @param PartnerID
     * @param OTP
     * @param PhoneNumber
     * @param ChannelID
     * @param TransType
     * @throws RemoteException
     */
    @Override
    public void PAYMENT_OTP_ADDING(String RefTransID, String PartnerID, String OTP,
            String PhoneNumber, String ChannelID, String TransType) throws RemoteException {
        try {
            LOGGER.info("PAYMENT_OTP_ADDING --> [IN] --> RefTransID = [" + RefTransID + "], PartnerID = [" + PartnerID + "], OTP = [" + OTP + "], PhoneNumber = [" + PhoneNumber + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.PAYMENT_OTP_ADDING(RefTransID, PartnerID, OTP, PhoneNumber, ChannelID, TransType);
            LOGGER.info("PAYMENT_OTP_ADDING --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* ONEPAY */

 /* DAWACO */
    /**
     *
     * @param date
     * @return
     * @throws RemoteException
     */
    @Override
    public List<DawacoCollate> getOutDAWACOCollate(String date) throws RemoteException {
        try {
            LOGGER.info("getOutDAWACOCollate --> [IN] --> date = [" + date + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<DawacoCollate> result = BO.getOutDAWACOCollate(date);
            LOGGER.info("getOutDAWACOCollate --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* DAWACO */

 /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    /**
     *
     * @param ID
     * @param SRCACCOUNT
     * @param PAN_LOC
     * @param CARD_BRN
     * @param CCY
     * @param EXP_DATE
     * @param AMOUNT
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @param SRCCHANNEL
     * @param debtPay
     * @param debtCur
     * @param IPPPayType
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertCardIPPTrans(String ID, String SRCACCOUNT, String PAN_LOC,
            String CARD_BRN, String CCY, String EXP_DATE, Double AMOUNT, String REF_FCC,
            String REF_CW, String TRANS_STATUS, String SRCCHANNEL, BigDecimal debtPay, BigDecimal debtCur, String IPPPayType)
            throws RemoteException {
        try {
            LOGGER.info("insertCardIPPTrans --> [IN] --> ID = [" + ID + "], SRCACCOUNT = [" + SRCACCOUNT + "], PAN_LOC = [" + PAN_LOC + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertCardIPPTrans(ID, SRCACCOUNT, PAN_LOC,
                    CARD_BRN, CCY, EXP_DATE, AMOUNT, REF_FCC,
                    REF_CW, TRANS_STATUS, SRCCHANNEL,
                    debtPay, debtCur, IPPPayType);
            LOGGER.info("insertCardIPPTrans --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //Long.Le edit updateCardReloadTrans
    /**
     *
     * @param ID
     * @param REF_FCC
     * @param REF_CW
     * @param TRANS_STATUS
     * @return
     * @throws RemoteException
     */
    @Override
    public int updateCardIPPTrans(String ID, String REF_FCC, String REF_CW, String TRANS_STATUS) throws RemoteException {
        try {
            LOGGER.info("updateCardIPPTrans --> [IN] --> ID = [" + ID + "], REF_FCC = [" + REF_FCC + "], REF_CW = [" + REF_CW + "], TRANS_STATUS = [" + TRANS_STATUS + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.updateCardIPPTrans(ID, REF_FCC, REF_CW, TRANS_STATUS);
            LOGGER.info("updateCardIPPTrans --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getListCWWS_CARD_IPP(int id) throws RemoteException {
        try {
            LOGGER.info("getListCWWS_CARD_IPP --> [IN] --> id = [" + id + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getListCWWS_CARD_IPP(id);
            LOGGER.info("getListCWWS_CARD_IPP --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public int CHECK_LASTMONTH_DATE() throws Exception {
        try {
            LOGGER.info("CHECK_LASTMONTH_DATE --> [IN] --> input is empty");
            SmsSCBBO BO = new SmsSCBBO();
            int result = BO.CHECK_LASTMONTH_DATE();
            LOGGER.info("CHECK_LASTMONTH_DATE --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param module
     * @param partner
     * @param serviceType
     * @param provider
     * @param merchant
     * @param customerCode
     * @param oldStatus
     * @param newStatus
     * @param userUpd
     * @throws RemoteException
     */
    @Override
    public void updateChangeStatusPaybill(String id, String module, String partner,
            String serviceType, String provider, String merchant,
            String customerCode, String oldStatus, String newStatus,
            String userUpd) throws RemoteException {
        try {
            LOGGER.info("updateChangeStatusPaybill --> [IN] --> id = [" + id + "], module = [" + module + "], partner = [" + partner + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updateChangeStatusPaybill(id, module, partner, serviceType,
                    provider, merchant, customerCode, oldStatus,
                    newStatus, userUpd);
            LOGGER.info("updateChangeStatusPaybill --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pCOLLATEDDATE
     * @param pFiletype
     * @param pTRANSTYPE
     * @param pSERVICE
     * @param pFileName
     * @param pPARTNERID
     * @return
     * @throws RemoteException
     */
    @Override
    public String NAPAS_CHECK_LOG_DATE_COLLATED(String pCOLLATEDDATE,
            String pFiletype,
            String pTRANSTYPE,
            String pSERVICE,
            String pFileName,
            String pPARTNERID
    ) throws RemoteException {
        try {
            LOGGER.info("NAPAS_CHECK_LOG_DATE_COLLATED --> [IN] --> pCOLLATEDDATE = [" + pCOLLATEDDATE + "], pFiletype = [" + pFiletype + "], pTRANSTYPE = [" + pTRANSTYPE + "], pPARTNERID = [" + pPARTNERID + "]");
            NAPASBO BO = new NAPASBO();
            String result = BO.CHECK_LOG_DATE_COLLATED(pCOLLATEDDATE, pFiletype, pTRANSTYPE, pSERVICE, pFileName, pPARTNERID);
            LOGGER.info("NAPAS_CHECK_LOG_DATE_COLLATED --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    @Override
    public void NAPAS_InsertDataCollated(NapasCollatedId obj) throws RemoteException {
        try {
            LOGGER.info("NAPAS_InsertDataCollated --> [IN] --> NapasCollatedId_id = [" + obj.getId() + "]");
            NAPASBO BO = new NAPASBO();
            BO.InsertDataCollated(obj);
            LOGGER.info("NAPAS_InsertDataCollated --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pTRANSTYPE
     * @param pCOLLATEDDATE
     * @return
     * @throws RemoteException
     */
    @Override
    public List<NapasCollatedId> NAPAS_getIBTCollateOut(
            String pTRANSTYPE,
            String pCOLLATEDDATE
    ) throws RemoteException {
        try {
            LOGGER.info("NAPAS_getIBTCollateOut --> [IN] --> pTRANSTYPE = [" + pTRANSTYPE + "], pCOLLATEDDATE = [" + pCOLLATEDDATE + "]");
            NAPASBO BO = new NAPASBO();
            List<NapasCollatedId> result = BO.getIBTCollateOut(pTRANSTYPE, pCOLLATEDDATE);
            LOGGER.info("NAPAS_getIBTCollateOut --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param userid
     * @return
     * @throws RemoteException
     */
    @Override
    public EBANKUSER ONL_getInfoEbankUser(String userid) throws RemoteException {
        try {
            LOGGER.info("ONL_getInfoEbankUser --> [IN] --> userid = [" + userid + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            EBANKUSER result = BO.getInfoEbankUser(userid);
            LOGGER.info("ONL_getInfoEbankUser --> [OUT] --> result_CUST_NO = [" + (result == null ? null : result.getCUST_NO()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pAccountID
     * @param pFullName
     * @param pPartnerID
     * @param Addinfo
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_RegisterProfileidWithAccount(String pAccountID,
            String pFullName,
            String pPartnerID, String Addinfo) throws RemoteException {
        try {
            LOGGER.info("ONL_RegisterProfileidWithAccount --> [IN] --> pAccountID = [" + pAccountID + "], pFullName = [" + pFullName + "], pPartnerID = [" + pPartnerID + "], Addinfo = [" + Addinfo + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.RegisterProfileidWithAccount(pAccountID, pFullName, pPartnerID, Addinfo);
            LOGGER.info("ONL_RegisterProfileidWithAccount --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PSCBID
     * @param pTRANSID
     * @param pPROFILEID
     * @param TransDate
     * @param PartnerID
     * @return
     * @throws RemoteException
     */
    @Override
    public String ONL_UpdateRegisterProfileidWithACC(String PSCBID,
            String pTRANSID,
            String pPROFILEID,
            String TransDate, String PartnerID) throws RemoteException {
        try {
            LOGGER.info("ONL_UpdateRegisterProfileidWithACC --> [IN] --> PSCBID = [" + PSCBID + "], pTRANSID = [" + pTRANSID + "], pPROFILEID = [" + pPROFILEID + "], TransDate = [" + TransDate + "], PartnerID = [" + PartnerID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.UpdateRegisterProfileidWithACC(PSCBID, pTRANSID, pPROFILEID, TransDate, PartnerID);
            LOGGER.info("ONL_UpdateRegisterProfileidWithACC --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    //HAI QUAN ONLINE

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_INSERT_DKNNT(HQ_DKNNT obj) throws RemoteException {
        try {
            LOGGER.info("HQ_INSERT_DKNNT --> [IN] --> HQ_DKNNT = [" + obj + "]");
            HQBO BO = new HQBO();
            String[] result = BO.INSERT_DKNNT(obj);
            LOGGER.info("HQ_INSERT_DKNNT --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pUSER
     * @param pBRANCHID
     * @param pSTATUS
     * @param pACCEPTED
     * @param pDesc
     * @throws RemoteException
     */
    @Override
    public void HQ_DUYET_DKNNT(
            String pID,
            String pUSER,
            String pBRANCHID,
            String pSTATUS,
            String pACCEPTED,
            String pDesc
    ) throws RemoteException {
        try {
            LOGGER.info("HQ_DUYET_DKNNT --> [IN] --> pID = [" + pID + "], pUSER = [" + pUSER + "], pBRANCHID = [" + pBRANCHID + "], pSTATUS = [" + pSTATUS + "], pACCEPTED = [" + pACCEPTED + "], pDesc = [" + pDesc + "]");
            HQBO BO = new HQBO();
            BO.DUYET_DKNNT(pID, pUSER, pBRANCHID, pSTATUS, pACCEPTED, pDesc);
            LOGGER.info("HQ_DUYET_DKNNT --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pType
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_getData213(String pID,
            String pType) throws RemoteException {
        try {
            LOGGER.info("HQ_getData213 --> [IN] --> pID = [" + pID + "], pType = [" + pType + "]");
            HQBO BO = new HQBO();
            String[] result = BO.getData213(pID, pType);
            LOGGER.info("HQ_getData213 --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pSO_HS
     * @param pMST
     * @param pBranchID
     * @param pStatus
     * @param FromDate
     * @param ToDate
     * @param STK
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_DKNNT> HQ_searchNNT(String pSO_HS,
            String pMST,
            String pBranchID,
            String pStatus,
            String FromDate,
            String ToDate,
            String STK) throws RemoteException {
        try {
            LOGGER.info("HQ_searchNNT --> [IN] --> pSO_HS = [" + pSO_HS + "], pMST = [" + pMST + "]");
            HQBO BO = new HQBO();
            List<HQ_DKNNT> result = BO.searchNNT(pSO_HS, pMST, pBranchID, pStatus, FromDate, FromDate, STK);
            LOGGER.info("HQ_searchNNT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_DKNNT> HQ_getData312(String pID) throws RemoteException {
        try {
            LOGGER.info("HQ_getData312 --> [IN] --> pID = [" + pID + "]");
            HQBO BO = new HQBO();
            List<HQ_DKNNT> result = BO.getData312(pID);
            LOGGER.info("HQ_getData312 --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pRequestID
     * @param pTransID
     * @param pSO_HS
     * @throws RemoteException
     */
    @Override
    public void HQ_UpdateDKNNT313(String pRequestID,
            String pTransID,
            String pSO_HS) throws RemoteException {
        try {
            LOGGER.info("HQ_UpdateDKNNT313 --> [IN] --> pRequestID = [" + pRequestID + "], pTransID = [" + pTransID + "], pSO_HS = [" + pSO_HS + "]");
            HQBO BO = new HQBO();
            BO.UpdateDKNNT313(pRequestID, pTransID, pSO_HS);
            LOGGER.info("HQ_UpdateDKNNT313 --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public String HQ_getSO_TN_CT_SCB() throws RemoteException {
        try {
            LOGGER.info("HQ_getSO_TN_CT_SCB --> [IN] --> input is empty");
            HQBO BO = new HQBO();
            String result = BO.getSO_TN_CT_SCB();
            LOGGER.info("HQ_getSO_TN_CT_SCB --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_INSERT_NOPTIEN_HQ_ONLINE(HQ_NOPTIEN_HQ obj) throws RemoteException {
        try {
            LOGGER.info("HQ_INSERT_NOPTIEN_HQ_ONLINE --> [IN] --> HQ_NOPTIEN_HQ = [" + obj + "]");
            HQBO BO = new HQBO();
            String[] result = BO.INSERT_NOPTIEN_HQ_ONLINE(obj);
            LOGGER.info("HQ_INSERT_NOPTIEN_HQ_ONLINE --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_INSERT_GNT_ONLINE(HQ_NOPTIEN_HQ_GNT obj) throws RemoteException {
        try {
            LOGGER.info("HQ_INSERT_GNT_ONLINE --> [IN] --> HQ_NOPTIEN_HQ_GNT = [" + obj + "]");
            HQBO BO = new HQBO();
            String[] result = BO.INSERT_GNT_ONLINE(obj);
            LOGGER.info("HQ_INSERT_GNT_ONLINE --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_INSERT_NOPTIEN_CQT_ONINE(HQ_NOPTIEN_CQQLT obj) throws RemoteException {
        try {
            LOGGER.info("HQ_INSERT_NOPTIEN_CQT_ONINE --> [IN] --> HQ_NOPTIEN_CQQLT = [" + obj + "]");
            HQBO BO = new HQBO();
            String[] result = BO.INSERT_NOPTIEN_CQT_ONINE(obj);
            LOGGER.info("HQ_INSERT_NOPTIEN_CQT_ONINE --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @param Type
     * @param xml
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] HQ_NOPTIEN_CORE_ONLINE(String ID, String Type, String xml) throws RemoteException {
        try {
            LOGGER.info("HQ_NOPTIEN_CORE_ONLINE --> [IN] --> ID = [" + ID + "], Type = [" + Type + "], xml = [" + xml + "]");
            HQBO BO = new HQBO();
            String[] result = BO.NOPTIEN_CORE_ONLINE(ID, Type, xml);
            LOGGER.info("HQ_NOPTIEN_CORE_ONLINE --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_NOPTIEN_HQ_GNT> HQ_getDataGNT_CT(String ID) throws RemoteException {
        try {
            LOGGER.info("HQ_getDataGNT_CT --> [IN] --> ID = [" + ID + "]");
            HQBO BO = new HQBO();
            List<HQ_NOPTIEN_HQ_GNT> result = BO.getDataGNT_CT(ID);
            LOGGER.info("HQ_getDataGNT_CT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_NOPTIEN_HQ_GNTCT> getDataTOKHAI_CT(String ID) throws RemoteException {
        try {
            LOGGER.info("getDataTOKHAI_CT --> [IN] --> ID = [" + ID + "]");
            HQBO BO = new HQBO();
            List<HQ_NOPTIEN_HQ_GNTCT> result = BO.getDataTOKHAI_CT(ID);
            LOGGER.info("getDataTOKHAI_CT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public List<HQ_NOPTIEN_HQ> HQ_getlistDataGNT() throws RemoteException {
        try {
            HQBO BO = new HQBO();
            List<HQ_NOPTIEN_HQ> result = BO.getlistDataGNT();
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public String HQ_getMaxDataCollated() throws RemoteException {
        try {
            LOGGER.info("HQ_getMaxDataCollated --> [IN] --> input is empty");
            HQBO BO = new HQBO();
            String result = BO.getMaxDataCollated();
            LOGGER.info("HQ_getMaxDataCollated --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @throws RemoteException
     */
    @Override
    public void HQ_UPDATE_DKNNT(HQ_DKNNT obj) throws RemoteException {
        try {
            LOGGER.info("HQ_UPDATE_DKNNT --> [IN] --> HQ_DKNNT = [" + obj + "]");
            HQBO BO = new HQBO();
            BO.UPDATE_DKNNT(obj);
            LOGGER.info("HQ_UPDATE_DKNNT --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @throws RemoteException
     */
    @Override
    public void HQ_SendGNTHQ(String ID) throws RemoteException {
        try {
            LOGGER.info("HQ_SendGNTHQ --> [IN] --> ID = [" + ID + "]");
            HQBO BO = new HQBO();
            BO.SendGNTHQ(ID);
            LOGGER.info("HQ_SendGNTHQ --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HQ_DKNNT> HQ_getDataDKNNT(String pID) throws RemoteException {
        try {
            LOGGER.info("HQ_getDataDKNNT --> [IN] --> pID = [" + pID + "]");
            HQBO BO = new HQBO();
            List<HQ_DKNNT> result = BO.getDataDKNNT(pID);
            LOGGER.info("HQ_getDataDKNNT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @param Type
     * @return
     * @throws RemoteException
     */
    @Override
    public int HQ_check213(String ID, String Type) throws RemoteException {
        try {
            LOGGER.info("HQ_check213 --> [IN] --> ID = [" + ID + "], Type = [" + Type + "]");
            HQBO BO = new HQBO();
            int result = BO.check213(ID, Type);
            LOGGER.info("HQ_check213 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMa_KB
     * @param pTen_KB
     * @throws RemoteException
     */
    @Override
    public void HQ_insertDM_ER(String pMa_KB,
            String pTen_KB
    ) throws RemoteException {
        try {
            LOGGER.info("HQ_insertDM_ER --> [IN] --> pMa_KB = [" + pMa_KB + "], pTen_KB = [" + pTen_KB + "]");
            HQBO BO = new HQBO();
            BO.insertDM_ER(pMa_KB, pTen_KB);
            LOGGER.info("HQ_insertDM_ER --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* MASTERPASS */
    /**
     *
     * @param sequenceNo
     * @param responseCode
     * @param responseDescription
     * @param approvalCode
     * @param typeTrans
     * @return
     * @throws RemoteException
     */
    @Override
    public int INSERTCWDIRECTDEBIT(String sequenceNo, String responseCode, String responseDescription, String approvalCode, String typeTrans) throws RemoteException {
        try {
            LOGGER.info("INSERTCWDIRECTDEBIT --> [IN] --> sequenceNo = [" + sequenceNo + "], responseCode = [" + responseCode + "], responseDescription = [" + responseDescription + "], approvalCode = [" + approvalCode + "], typeTrans = [" + typeTrans + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.INSERTCWDIRECTDEBIT(sequenceNo, responseCode, responseDescription, approvalCode, typeTrans);
            LOGGER.info("INSERTCWDIRECTDEBIT --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int MASTERPASSQR(PayByQRCodeRq req, MasterCardQrActionEnum action) throws RemoteException {
        try {
            LOGGER.info("MASTERPASSQR --> [IN] --> req = [" + req + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.MASTERPASSQR(req, action);
            LOGGER.info("MASTERPASSQR --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param sequenceNo
     * @param referenceNo
     * @param responseCode
     * @param responseDescription
     * @param typeTrans
     * @return
     * @throws RemoteException
     */
    @Override
    public int INSERTCWCARDADJUSTMENT(String sequenceNo, String referenceNo, String responseCode, String responseDescription, String typeTrans, String loc4Digits, String merPan, String refCore, String cardType) throws RemoteException {
        try {
            LOGGER.info("INSERTCWCARDADJUSTMENT --> [IN] --> sequenceNo = [" + sequenceNo + "], responseCode = [" + responseCode + "], responseDescription = [" + responseDescription + "], referenceNo = [" + referenceNo + "], typeTrans = [" + typeTrans + "], loc4Digits = [" + loc4Digits + "], merPan = [" + merPan + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.INSERTCWCARDADJUSTMENT(sequenceNo, referenceNo, responseCode, responseDescription, typeTrans, loc4Digits, merPan, refCore, cardType);
            LOGGER.info("INSERTCWCARDADJUSTMENT --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param sequenceNo
     * @param response
     * @return
     * @throws RemoteException
     */
    @Override
    public int INSERTMASTERCARDRES(String sequenceNo, MCPaymentRp response) throws RemoteException {
        try {
            LOGGER.info("INSERTMASTERCARDRES --> [IN] --> sequenceNo = [" + sequenceNo + "], response = [" + response + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.INSERTMASTERCARDRES(sequenceNo, response);
            LOGGER.info("INSERTMASTERCARDRES --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param req
     * @param senderInfor
     * @return
     * @throws RemoteException
     */
    @Override
    public String INSERTMASTERPASSQR(PayByQRCodeRq req, MasterSenderInfor senderInfor) throws RemoteException {
        try {
            LOGGER.info("INSERTMASTERPASSQR --> [IN] --> req = [" + req + "], senderInfor = [" + senderInfor + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.INSERTMASTERPASSQR(req, senderInfor);
            LOGGER.info("INSERTMASTERPASSQR --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    public String INSERTMASTERPASSQR(PayByQRCodeRq req) throws RemoteException {
        try {
            LOGGER.info("INSERTMASTERPASSQR --> [IN] --> req = [" + req + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.INSERTMASTERPASSQR(req);
            LOGGER.info("INSERTMASTERPASSQR --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param loc4Digit
     * @return
     * @throws RemoteException
     */
    @Override
    public MasterSenderInfor queryMasterpassCardInfor(String loc4Digit) throws RemoteException {
        try {
            LOGGER.info("queryMasterpassCardInfor --> [IN] --> loc4Digit = [" + loc4Digit + "]");
            CWLiveBO cwLiveBO = new CWLiveBO();
            MasterSenderInfor result = cwLiveBO.queryMasterpassCardInfor(loc4Digit);
            LOGGER.info("queryMasterpassCardInfor --> [OUT] --> result_loc = [" + (result == null ? null : result.getLoc()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public SenderMSVSCardInfo querySenderMSVSCardInfo(String loc4Digit) throws RemoteException {
        try {
            LOGGER.info("queryMasterpassCardInfor --> [IN] --> loc4Digit = [" + loc4Digit + "]");
            CWLiveBO cwLiveBO = new CWLiveBO();
            SenderMSVSCardInfo result = cwLiveBO.querySenderMSVSCardInfo(loc4Digit);
            LOGGER.info("queryMasterpassCardInfor --> [OUT] --> result_loc = [" + (result == null ? null : result.getLoc()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* MASTERPASS */
 /* VISA */
    /**
     *
     * @param req
     * @param senderInfor
     * @return
     * @throws RemoteException
     */
    @Override
    public String INSERTVISAQR(MVISAQRRQ req, MasterSenderInfor senderInfor) throws RemoteException {
        try {
            LOGGER.info("INSERTVISAQR --> [IN] --> req = [" + req + "], senderInfor = [" + senderInfor + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.INSERTVISAQR(req, senderInfor);
            LOGGER.info("INSERTVISAQR --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public String INSERTVISAQR(MVISAQRRQ req) throws RemoteException {
        try {
            LOGGER.info("INSERTVISAQR --> [IN] --> req = [" + req + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.INSERTVISAQR(req);
            LOGGER.info("INSERTVISAQR --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param sequenceNo
     * @param response
     * @return
     * @throws RemoteException
     */
    @Override
    public int INSERTVISAQRRES(String sequenceNo, ResponseMessage response) throws RemoteException {
        try {
            LOGGER.info("INSERTVISAQRRES --> [IN] --> sequenceNo = [" + sequenceNo + "], response = [" + response + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.INSERTVISAQRRES(sequenceNo, response);
            LOGGER.info("INSERTVISAQRRES --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* VISA */
 /* EVNHCM */
    /**
     *
     * @param partner
     * @param customerCode
     * @param totalAmount
     * @param refPartnerId
     * @param revertStatus
     * @return
     * @throws RemoteException
     */
    @Override
    public int deletePayooBill(String partner, String customerCode, String totalAmount, String refPartnerId, String revertStatus) throws RemoteException {
        try {
            LOGGER.info("deletePayooBill --> [IN] --> partner = [" + partner + "], customerCode = [" + customerCode + "], totalAmount = [" + totalAmount + "], refPartnerId = [" + refPartnerId + "], revertStatus = [" + revertStatus + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.deletePayooBill(partner, customerCode, totalAmount, refPartnerId, revertStatus);
            LOGGER.info("deletePayooBill --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param partner
     * @param customerCode
     * @param totalAmount
     * @param refPartnerId
     * @return
     * @throws RemoteException
     */
    @Override
    public String queryRefCustPayooBill(String partner, String customerCode, String totalAmount, String refPartnerId) throws RemoteException {
        try {
            LOGGER.info("queryRefCustPayooBill --> [IN] --> partner = [" + partner + "], customerCode = [" + customerCode + "], totalAmount = [" + totalAmount + "], refPartnerId = [" + refPartnerId + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.queryRefCustPayooBill(partner, customerCode, totalAmount, refPartnerId);
            LOGGER.info("queryRefCustPayooBill --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param partnerID
     * @return
     * @throws RemoteException
     */
    @Override
    public List<Pbl_billpaidCollate> getOutPbl_billpaidCollate(String partnerID) throws RemoteException {
        try {
            LOGGER.info("getOutPbl_billpaidCollate --> [IN] --> partnerID = [" + partnerID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<Pbl_billpaidCollate> result = BO.getOutPbl_billpaidCollate(partnerID);
            LOGGER.info("getOutPbl_billpaidCollate --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param partnerID
     * @param comeback
     * @return
     * @throws RemoteException
     */
    @Override
    public List<Pbl_billpaidCollate> getOutPbl_billpaidCollate(String partnerID, int comeback) throws RemoteException {
        try {
            LOGGER.info("getOutPbl_billpaidCollate --> [IN] --> partnerID = [" + partnerID + "], comeback = [" + comeback + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<Pbl_billpaidCollate> result = BO.getOutPbl_billpaidCollate(partnerID, comeback);
            LOGGER.info("getOutPbl_billpaidCollate --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param datas
     * @return
     * @throws RemoteException
     */
    @Override
    public List<EVNHCM> queryttttEVNHCM(List<Pbl_billpaidCollate> datas) throws RemoteException {
        try {
            LOGGER.info("queryttttEVNHCM --> [IN] --> List<Pbl_billpaidCollate> = [" + datas.size() + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<EVNHCM> result = BO.queryttttEVNHCM(datas);
            LOGGER.info("queryttttEVNHCM --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* EVNHCM */
    //Add new by LYDTY- 03/2018
    /**
     *
     * @param accountno
     * @return
     * @throws Exception
     */
    @Override
    public String GET_BRANCHCODE_FROM_FCC(String accountno) throws Exception {
        try {
            LOGGER.info("GET_BRANCHCODE_FROM_FCC --> [IN] --> accountno = [" + accountno + "]");
            FCCCoreBO BO = new FCCCoreBO();
            String result = BO.getBranchCode(accountno);
            LOGGER.info("GET_BRANCHCODE_FROM_FCC --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* MASTERPASS */
    //----------------FOR NEW 2018 BY LYDTY------------------------------
    //FOR TRANSFER 247 FROM PARTNER
    /**
     *
     * @param PTRANSID
     * @param pProfileID
     * @param PAMOUNT
     * @param PCCY
     * @param PTRANSDATE
     * @param PPARTNERID
     * @param PDESCRIPTION
     * @param PMERCHANTID
     * @param pChannelID
     * @param OTP
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_PAYMENTWITHPROFILEIDBYVERIFY(String PTRANSID,
            String pProfileID,
            Double PAMOUNT,
            String PCCY,
            String PTRANSDATE,
            String PPARTNERID,
            String PDESCRIPTION,
            String PMERCHANTID,
            String pChannelID,
            String OTP
    ) throws RemoteException {
        try {
            LOGGER.info("ONL_PAYMENTWITHPROFILEIDBYVERIFY --> [IN] --> PTRANSID = [" + PTRANSID + "], pProfileID = [" + pProfileID + "], PAMOUNT = [" + PAMOUNT + "], PPARTNERID = [" + PPARTNERID + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.PAYMENTWITHPROFILEIDBYVERIFY(PTRANSID, pProfileID, PAMOUNT, PCCY, PTRANSDATE, PPARTNERID, PDESCRIPTION, PMERCHANTID, pChannelID, OTP);
            LOGGER.info("ONL_PAYMENTWITHPROFILEIDBYVERIFY --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public ProcedureCallDto checkTDIsEOD(String accountno) throws RemoteException {
        try {
//            FcdbBO fcdbBO = new FcdbBO();
//            return fcdbBO.checkTDIsEOD(accountno);
            LOGGER.info("checkTDIsEOD --> [IN] --> accountno = [" + accountno + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ProcedureCallDto result = smsscbBO.checkTDIsEOD(accountno);
            LOGGER.info("checkTDIsEOD --> [OUT] --> result_ErrorStatus = [" + (result == null ? null : result.getErrorStatus()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertAlertAccountTd2(SmsCustAlertTd smscustalerttd) throws RemoteException {
        try {
            LOGGER.info("insertAlertAccountTd2 --> [IN] --> smscustalerttd = [" + smscustalerttd + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.insertAlertAccountTd2(smscustalerttd);
            LOGGER.info("insertAlertAccountTd2 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public String[] getListJoinAccount() throws RemoteException {
        try {
            LOGGER.info("getListJoinAccount --> [IN] --> input is empty");
            FcdbBO fcdbBO = new FcdbBO();
            String[] result = fcdbBO.getListJoinAccount();
            LOGGER.info("getListJoinAccount --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //baovq - sms & tp
    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> Find_List_Cust_No(String cust_no) throws RemoteException {
        try {
            LOGGER.info("Find_List_Cust_No --> [IN] --> cust_no = [" + cust_no + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.Find_List_Cust_No(cust_no);
            LOGGER.info("Find_List_Cust_No --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smscustalerttd
     * @return
     * @throws RemoteException
     */
    @Override
    public int update_REF_STATUS(SmsCustAlertTd smscustalerttd) throws RemoteException {
        try {
            LOGGER.info("update_REF_STATUS --> [IN] --> SmsCustAlertTd = [" + smscustalerttd + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            int result = smsscbBO.update_REF_STATUS(smscustalerttd);
            LOGGER.info("update_REF_STATUS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //evnhcm
    /*
    @Override
    public ArrayList<?> getACCNOFCUBS(String idpartner, String idservicetype, String idprovider) throws RemoteException {
         try {
            SmsSCBBO smsscbBO = new SmsSCBBO();
             return smsscbBO.getACCNOFCUBS(idpartner, idservicetype, idprovider);
        } catch (Exception ex) {
LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }*/
    /**
     *
     * @param pProfileID
     * @param PAMOUNT
     * @param PPARTNERID
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_checkProfileIDForPayment(
            String pProfileID,
            Double PAMOUNT,
            String PPARTNERID
    ) throws RemoteException {
        try {
            LOGGER.info("ONL_checkProfileIDForPayment --> [IN] --> pProfileID = [" + pProfileID + "], PAMOUNT = [" + PAMOUNT + "], PPARTNERID = [" + PPARTNERID + "]");
            OnlinePaymentBO bo = new OnlinePaymentBO();
            String[] result = bo.checkProfileIDForPayment(pProfileID, PAMOUNT, PPARTNERID);
            LOGGER.info("ONL_checkProfileIDForPayment --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param username
     * @param passInput
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean checkPassEB(String username, String passInput)
            throws RemoteException {
        try {
            LOGGER.info("checkPassEB --> [IN] --> username = [" + username + "], passInput = [" + passInput + "]");
            boolean result = new PasswordDigest().checkPass(username, passInput);
            LOGGER.info("checkPassEB --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param username
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getDetailUserEB(String username) throws RemoteException {
        try {
            LOGGER.info("getDetailUserEB --> [IN] --> username = [" + username + "]");
            BdobdxBO BO = new BdobdxBO();
            ArrayList result = BO.getDetailUserEB(username);
            LOGGER.info("getDetailUserEB --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
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
     * @throws RemoteException
     */
    @Override
    public ProcedureCallDto getFeeMobile(String brnTrn, String productcode, String ccy, String amt, String acNo, String acBrn) throws RemoteException {
        try {
            LOGGER.info("getFeeMobile --> [IN] --> brnTrn = [" + brnTrn + "], productcode = [" + productcode + "], ccy = [" + ccy + "], amt = [" + amt + "], acNo = [" + acNo + "], acBrn = [" + acBrn + "]");
            FCCCoreBO fccCore = new FCCCoreBO();
            ProcedureCallDto result = fccCore.getFeeMobile(brnTrn, productcode, ccy, amt, acNo, acBrn);
            LOGGER.info("getFeeMobile --> [OUT] --> brnTrn = [" + brnTrn + "], productcode = [" + productcode + "], ccy = [" + ccy + "], amt = [" + amt + "], acNo = [" + acNo + "], acBrn = [" + acBrn + "], result_ErrorStatus = [" + (result == null ? null : result.getErrorMessage()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    //dvsms - baovq

    /**
     *
     * @param custno
     * @param idcard
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> searchCustomerCore(String custno, String idcard) throws RemoteException {
        try {
            LOGGER.info("searchCustomerCore --> [IN] --> custno = [" + custno + "], idcard = [" + idcard + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.searchCustomerCore(custno, idcard);
            LOGGER.info("searchCustomerCore --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smsservice
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getAccountSMSService(SMSService smsservice) throws RemoteException {
        try {
            LOGGER.info("getAccountSMSService --> [IN] --> smsservice = [" + smsservice + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.getAccountSMSService(smsservice);
            LOGGER.info("getAccountSMSService --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cust_no
     * @param registertype
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> findHistoryListSMSService(String cust_no, String registertype) throws RemoteException {
        try {
            LOGGER.info("findHistoryListSMSService --> [IN] --> cust_no = [" + cust_no + "], registertype = [" + registertype + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.findHistoryListSMSService(cust_no, registertype);
            LOGGER.info("findHistoryListSMSService --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smsservice
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getAccountSMSSERVICEById(SMSService smsservice) throws RemoteException {
        try {
            LOGGER.info("getAccountSMSSERVICEById --> [IN] --> smsservice = [" + smsservice + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.getAccountSMSSERVICEById(smsservice);
            LOGGER.info("getAccountSMSSERVICEById --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param user
     * @return
     * @throws RemoteException
     */
    @Override
    public int insertSMSServiceUser(SMSServiceUser user) throws RemoteException {
        try {
            LOGGER.info("insertSMSServiceUser --> [IN] --> user = [" + user + "]");
            SmsSCBBO BO = new SmsSCBBO();
            int result = BO.insertSMSServiceUser(user);
            LOGGER.info("insertSMSServiceUser --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param userrefid
     * @param status
     * @param userid
     * @throws RemoteException
     */
    @Override
    public void approveSMSService(int userrefid, String status, String userid) throws RemoteException {
        try {
            LOGGER.info("approveSMSService --> [IN] --> userrefid = [" + userrefid + "], status = [" + status + "], userid = [" + userid + "]");
            SmsSCBBO BO = new SmsSCBBO();
            BO.approveSMSService(userrefid, status, userid);
            LOGGER.info("approveSMSService --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    public ArrayList<?> getAccountTK(String custno) throws RemoteException {
        try {
            LOGGER.info("getAccountTK --> [IN] --> custno = [" + custno + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.getAccountTK(custno);
            LOGGER.info("getAccountTK --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param branch
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> GET_TENNV(String branch) throws RemoteException {
        try {
            LOGGER.info("GET_TENNV --> [IN] --> branch = [" + branch + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.GET_TENNV(branch);
            LOGGER.info("GET_TENNV --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param manv
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> QUERY_TENNV(String manv) throws RemoteException {
        try {
            LOGGER.info("QUERY_TENNV --> [IN] --> manv = [" + manv + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.QUERY_TENNV(manv);
            LOGGER.info("QUERY_TENNV --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    // end dvsms - baovq 
//    public ArrayList<?> getListAccountTT(String custno) throws RemoteException {
//        try {
//            SmsSCBBO BO = new SmsSCBBO();
//            return BO.getListAccountTT(custno);
//        } catch (Exception ex) {
//            throw new RemoteException(ex.getMessage(), ex); 
//        }
//    }
    /**
     *
     * @param idpartner
     * @param idservicetype
     * @param idprovider
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getACCNOFCUBS(String idpartner, String idservicetype, String idprovider) throws RemoteException {
        try {
            LOGGER.info("getACCNOFCUBS --> [IN] --> idpartner = [" + idpartner + "], idservicetype = [" + idservicetype + "], idprovider = [" + idprovider + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.getACCNOFCUBS(idpartner, idservicetype, idprovider);
            LOGGER.info("getACCNOFCUBS --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smsservice
     * @param iduserref
     * @param cust_no
     * @throws RemoteException
     */
    public void insertSMSServiceTK(SMSServiceTK smsservice, int iduserref, String cust_no) throws RemoteException {
        try {
            LOGGER.info("insertSMSServiceTK --> [IN] --> smsservice = [" + smsservice + "], iduserref = [" + iduserref + "], cust_no = [" + cust_no + "]");
            SmsSCBBO BO = new SmsSCBBO();
            BO.insertSMSServiceTK(smsservice, iduserref, cust_no);
            LOGGER.info("insertSMSServiceTK --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] checkDKDVSMS(String custno) throws RemoteException {
        try {
            LOGGER.info("checkDKDVSMS --> [IN] --> custno = [" + custno + "]");
            SmsSCBBO BO = new SmsSCBBO();
            String[] result = BO.checkDKDVSMS(custno);
            LOGGER.info("checkDKDVSMS --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> get_data_approve(String id) throws RemoteException {
        try {
            LOGGER.info("get_data_approve --> [IN] --> id = [" + id + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.get_data_approve(id);
            LOGGER.info("get_data_approve --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> SMS_getListAccountTT(String custno) throws RemoteException {
        try {
            LOGGER.info("SMS_getListAccountTT --> [IN] --> custno = [" + custno + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.getListAccountTT(custno);
            LOGGER.info("SMS_getListAccountTT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param custno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> SMS_getAccountTK(String custno) throws RemoteException {
        try {
            LOGGER.info("SMS_getAccountTK --> [IN] --> custno = [" + custno + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.getAccountTK(custno);
            LOGGER.info("SMS_getAccountTK --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> getListAccountByCif(String cust_no) throws RemoteException {
        try {
            LOGGER.info("getListAccountByCif --> [IN] --> cust_no = [" + cust_no + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.getListAccountByCif(cust_no);
            LOGGER.info("getListAccountByCif --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*
    @Override
    public int update_REF_STATUS(SmsCustAlertTd smscustalerttd) throws RemoteException{
        try {
            SmsSCBBO BO = new SmsSCBBO();
            return BO.update_REF_STATUS(smscustalerttd);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex); 
        }
    }*/
 /*
    @Override
    public ArrayList<?> Find_List_Cust_No(String cust_no) throws RemoteException{
        try {
            SmsSCBBO BO = new SmsSCBBO();
            return BO.Find_List_Cust_No(cust_no);
        } catch (Exception ex) {
LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex); 
        }
    }*/
    /**
     *
     * @param username
     * @param newpass
     * @param channel
     * @return
     * @throws Exception
     */
    @Override
    public int resetPass(String username, String newpass, String channel) throws Exception {
        try {
            LOGGER.info("resetPass --> [IN] --> username = [" + username + "], newpass = [" + newpass + "], channel = [" + channel + "]");
            GenOBDXPassword BO = new GenOBDXPassword();
            int result = BO.resetPass(username, newpass, channel);
            LOGGER.info("resetPass --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param smsuser
     * @return
     * @throws Exception
     */
    @Override
    public int update_REF_STATUS_SMSTK(SMSServiceUser smsuser) throws Exception {
        try {
            LOGGER.info("update_REF_STATUS_SMSTK --> [IN] --> smsuser = [" + smsuser + "]");
            SmsSCBBO BO = new SmsSCBBO();
            int result = BO.update_REF_STATUS_SMSTK(smsuser);
            LOGGER.info("update_REF_STATUS_SMSTK --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> CHECK_POINT_REWARD_THU(String cust_no) throws RemoteException {
        try {
            LOGGER.info("CHECK_POINT_REWARD_THU --> [IN] --> cust_no = [" + cust_no + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.CHECK_POINT_REWARD_THU(cust_no);
            LOGGER.info("CHECK_POINT_REWARD_THU --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> CHECK_POINT_REWARD_CHI(String cust_no) throws RemoteException {
        try {
            LOGGER.info("CHECK_POINT_REWARD_CHI --> [IN] --> cust_no = [" + cust_no + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.CHECK_POINT_REWARD_CHI(cust_no);
            LOGGER.info("CHECK_POINT_REWARD_CHI --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> CHECK_POINT_REWARD_TC(String cust_no) throws RemoteException {
        try {
            LOGGER.info("CHECK_POINT_REWARD_TC --> [IN] --> cust_no = [" + cust_no + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList<?> result = BO.CHECK_POINT_REWARD_TC(cust_no);
            LOGGER.info("CHECK_POINT_REWARD_TC --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* VNPAY QR */
    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     */
    @Override
    public String INSERTCHECKQR(CheckQRRq req) throws RemoteException {
        try {
            LOGGER.info("INSERTCHECKQR --> [IN] --> CheckQRRq = [" + req + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.INSERTCHECKQR(req);
            LOGGER.info("INSERTCHECKQR --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param res
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean UPDATECHECKQR(CheckQRRp res) throws RemoteException {
        try {
            LOGGER.info("UPDATECHECKQR --> [IN] --> CheckQRRp = [" + res + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            boolean result = BO.UPDATECHECKQR(res);
            LOGGER.info("UPDATECHECKQR --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     */
    @Override
    public PaymentQRJson INSERTPAYMENTQR(PaymentQRRq req) throws RemoteException {
        try {
            LOGGER.info("INSERTPAYMENTQR --> [IN] --> PaymentQRRq = [" + req + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            PaymentQRJson result = BO.INSERTPAYMENTQR(req);
            LOGGER.info("INSERTPAYMENTQR --> [OUT] --> result_mobile = [" + (result == null ? null : result.getMobile()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param qrPayment
     * @param res
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean UPDATEPAYMENTQR(PaymentQRJson qrPayment, PaymentQRRp res) throws RemoteException {
        try {
            LOGGER.info("UPDATEPAYMENTQR --> [IN] --> qrPayment = [" + qrPayment + "], res = [" + res + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            boolean result = BO.UPDATEPAYMENTQR(qrPayment, res);
            LOGGER.info("UPDATEPAYMENTQR --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param qrPayment
     * @return
     * @throws RemoteException
     */
    @Override
    public RefundQRJson INSERTREFUNDQR(PaymentQRJson qrPayment) throws RemoteException {
        try {
            LOGGER.info("INSERTREFUNDQR --> [IN] --> qrPayment = [" + qrPayment + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            RefundQRJson result = BO.INSERTREFUNDQR(qrPayment);
            LOGGER.info("INSERTREFUNDQR --> [OUT] --> result_refundTrace = [" + (result == null ? null : result.getRefundTrace()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param qrRefund
     * @param res
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean UPDATEREFUNDQR(RefundQRJson qrRefund, RefundQRRp res) throws RemoteException {
        try {
            LOGGER.info("UPDATEREFUNDQR --> [IN] --> qrRefund = [" + qrRefund + "], res = [" + res + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            boolean result = BO.UPDATEREFUNDQR(qrRefund, res);
            LOGGER.info("UPDATEREFUNDQR --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /* TIMER_SMS */
    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean checkSCBCustomer(String phoneNumber) throws RemoteException {
        try {
            LOGGER.info("checkSCBCustomer --> [IN] --> phoneNumber = [" + phoneNumber + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            boolean result = BO.checkSCBCustomer(phoneNumber);
            LOGGER.info("checkSCBCustomer --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param phoneNumber
     * @param splitMessage
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] queryBalance(String phoneNumber, String[] splitMessage) throws RemoteException {
        try {
            LOGGER.info("queryBalance --> [IN] --> phoneNumber = [" + phoneNumber + "], splitMessage = [" + Arrays.toString(splitMessage) + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.queryBalance(phoneNumber, splitMessage);
            LOGGER.info("queryBalance --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     */
    @Override
    public String getAccDefaultFromMobile(String phoneNumber) throws RemoteException {
        try {
            LOGGER.info("getAccDefaultFromMobile --> [IN] --> phoneNumber = [" + phoneNumber + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.getAccDefaultFromMobile(phoneNumber);
            LOGGER.info("getAccDefaultFromMobile --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    @Override
    public String[] updatePhoneNumberTo10(String phoneNumber) throws RemoteException, SQLException {
        try {
            LOGGER.info("updatePhoneNumberTo10 --> [IN] --> phoneNumber = [" + phoneNumber + "]");
            String[] result = new String[3];
            result[0] = "-2";
            result[1] = result[2] = "";
            FCCCoreBO fccCore = new FCCCoreBO();
            boolean checked = fccCore.checkPhoneNumberAtCore(phoneNumber);
            if (!checked) {
                LOGGER.warn("updatePhoneNumberTo10 -> Ko tim thay [" + phoneNumber + "] tai CORE");
                CWLiveBO cwLiveBO = new CWLiveBO();
                checked = cwLiveBO.checkPhoneNumberAtCW(phoneNumber);
            }
            if (!checked) {
                LOGGER.warn("updatePhoneNumberTo10 -> Ko tim thay [" + phoneNumber + "] tai CW");
                OnlinePaymentBO BO = new OnlinePaymentBO();
                checked = BO.checkPhoneNumberAtGW(phoneNumber);
            }
            if (checked) {
                result[0] = "1";
            } else {
                LOGGER.warn("updatePhoneNumberTo10 -> Ko tim thay [" + phoneNumber + "] tai GW");
            }
            LOGGER.info("updatePhoneNumberTo10 --> phoneNumber = [" + phoneNumber + "], result = [" + result[0] + "]");
            LOGGER.info("updatePhoneNumberTo10 --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*
    ADD FUNCTION FOR ATMONLINE AND VAP
     */
    /**
     *
     * @param pPARTNERID
     * @param pTYPEFUNC
     * @param pAMOUNT
     * @param pCCY
     * @param pBANKCODE
     * @param pACCOUNTNO
     * @param pACCOUNTNAME
     * @param pSITRANSID
     * @param pSCBTRANSID
     * @param pTRANSDATE
     * @param pSTATUSTRANS
     * @throws RemoteException
     */
    @Override
    public void InsertCollated_TCH(String pPARTNERID,
            String pTYPEFUNC,
            String pAMOUNT,
            String pCCY,
            String pBANKCODE,
            String pACCOUNTNO,
            String pACCOUNTNAME,
            String pSITRANSID,
            String pSCBTRANSID,
            String pTRANSDATE,
            String pSTATUSTRANS) throws RemoteException {
        try {
            /*
            SmsSCBBO smsscbBO = new SmsSCBBO();
            return smsscbBO.transferFCUBS(glAcc, amount, content, idcardName, idcardAddress, idcard, idcardDob, user_maker, user_checker, branchcard);
             */
            LOGGER.info("InsertCollated_TCH --> [IN] --> pPARTNERID = [" + pPARTNERID + "], pAMOUNT = [" + pAMOUNT + "], pSITRANSID = [" + pSITRANSID + "], pSCBTRANSID = [" + pSCBTRANSID + "]");
            SIBO BO = new SIBO();
            //Helper.writeLog(FCCCoreBO.class, Level.INFO, "transferFCUBS -" + content);
            BO.InsertCollated_TCH(pPARTNERID, pTYPEFUNC, pAMOUNT, pCCY, pBANKCODE, pACCOUNTNO, pACCOUNTNAME, pSITRANSID, pSCBTRANSID, pTRANSDATE, pSTATUSTRANS);
            LOGGER.info("InsertCollated_TCH --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pDate
     * @param pPartnerid
     * @param pType
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList TCH_getOutCollated(Date pDate, String pPartnerid, String pType) throws RemoteException {
        try {
            LOGGER.info("TCH_getOutCollated --> [IN] --> pDate = [" + pDate + "], pPartnerid = [" + pPartnerid + "], pType = [" + pType + "]");
            SIBO BO = new SIBO();
            ArrayList result = BO.getOutCollated_TCH(pDate, pPartnerid, pType);
            LOGGER.info("TCH_getOutCollated --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList SI_getListBank247() throws RemoteException {
        try {
            LOGGER.info("SI_getListBank247 --> [IN] --> input is empty");
            SIBO BO = new SIBO();
            ArrayList result = BO.getListBank247();
            LOGGER.info("SI_getListBank247 --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pPARTNERID
     * @param Transid
     * @return
     * @throws RemoteException
     */
    @Override
    public List SI_querryTransfer(String pPARTNERID, String Transid) throws RemoteException {
        try {
            LOGGER.info("SI_querryTransfer --> [IN] --> pPARTNERID = [" + pPARTNERID + "], Transid = [" + Transid + "]");
            SIBO BO = new SIBO();
            List result = BO.querryTransfer(pPARTNERID, Transid);
            LOGGER.info("SI_querryTransfer --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*
    END ADD FUNCTION FOR ATMONLINE AND VAP
     */
 /*
     ADD IBT SOCKET NAPAS
     */
    /**
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public int NAPAS_INSERTIBTLOG(NAPAS_IBT obj) throws Exception {
        try {
            LOGGER.info("NAPAS_INSERTIBTLOG --> [IN] --> FROMNUMBER = [" + obj.getFROMNUMBER()
                    + "], DESTNUMBER = [" + obj.getDESTNUMBER() + "], TRANSAMOUNT = [" + obj.getTRANSAMOUNT()
                    + "], AUDITNUMBER = [" + obj.getAUDITNUMBER() + "], MERCHANTTYPE = [" + obj.getMERCHANTTYPE() + "]");
            NAPASBO BO = new NAPASBO();
            int result = BO.InsertLOG(obj);
            LOGGER.info("NAPAS_INSERTIBTLOG --> [OUT] --> FROMNUMBER = [" + obj.getFROMNUMBER()
                    + "], DESTNUMBER = [" + obj.getDESTNUMBER() + "], TRANSAMOUNT = [" + obj.getTRANSAMOUNT()
                    + "], AUDITNUMBER = [" + obj.getAUDITNUMBER() + "], result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return 0;
    }

    /**
     *
     * @param request
     * @throws RemoteException
     */
    @Override
    public String NAPAS_InsertRequestIBT(String request) throws RemoteException {
        try {
            LOGGER.info("NAPAS_InsertRequestIBT --> [IN] --> request = [" + request + "]");
            NAPASBO BO = new NAPASBO();
            LOGGER.info("NAPAS_InsertRequestIBT --> [OUT] --> does not return");
            return BO.InsertIBTLog(request);

        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param reponse
     * @param ID
     * @throws RemoteException
     */
    @Override
    public void NAPAS_insertResponseIBT(String reponse, String ID) throws RemoteException {
        try {
            LOGGER.info("NAPAS_insertResponseIBT --> [IN] --> reponse = [" + reponse + "], ID = [" + ID + "]");
            NAPASBO BO = new NAPASBO();
            BO.insertResponseIBT(reponse, ID);
            LOGGER.info("NAPAS_insertResponseIBT --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    @Override
    public String NAPAS_getResponseIBT(String ID) throws RemoteException {
        try {
            //HAM NAY KO GHI LOG
            // LOGGER.info("NAPAS_getResponseIBT --> [IN] --> ID = [" + ID + "]");
            NAPASBO BO = new NAPASBO();
            String result = BO.getResponseIBT(ID);
            //LOGGER.info("NAPAS_getResponseIBT --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public String NAPAS_getRequestIBT() throws RemoteException {
        try {
            // LOGGER.info("NAPAS_getRequestIBT --> [IN] --> input is empty");
            NAPASBO BO = new NAPASBO();
            String result = BO.getRequestIBT();
            //    LOGGER.info("NAPAS_getRequestIBT --> [OUT] --> Size = [" + (result == null ? null : result) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*
     END ADD IBT SOCKET NAPAS
     */
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
     * @throws RemoteException
     */
    @Override
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
            String pm_ksv) throws RemoteException {
        try {
            LOGGER.info("transferFCUBSForIBTCounter --> [IN] --> pm_product = [" + pm_product + "], pm_cr_acc = [" + pm_cr_acc + "], pm_amt = [" + pm_amt + "]");
            FCCCoreBO BO = new FCCCoreBO();
            String[] result = BO.transferFCUBSForIBTCounter(pm_product, pm_cr_brn, pm_cr_acc, pm_amt, pm_loaitien, pm_noidung, pm_tennguoinop, pm_diachinguoinop, pm_socmnd, pm_ngaycap, pm_noicap, pm_gdv, pm_ksv);
            LOGGER.info("transferFCUBSForIBTCounter --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getDetailDCbyID(String pID) throws RemoteException {
        try {
            LOGGER.info("getDetailDCbyID --> [IN] --> pID = [" + pID + "]");
            NTDTBO BO = new NTDTBO();
            ArrayList result = BO.getDetailDCbyID(pID);
            LOGGER.info("getDetailDCbyID --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param PPARTNERID
     * @param pProfileID
     * @param pCMND
     * @param pMobile
     * @param pAddInfo
     * @return
     * @throws RemoteException
     */
    @Override
    public String ONL_checkKYC(
            String PPARTNERID,
            String pProfileID,
            String pCMND,
            String pMobile,
            String pAddInfo) throws RemoteException {
        try {
            LOGGER.info("ONL_checkKYC --> [IN] --> PPARTNERID = [" + PPARTNERID + "], pProfileID = [" + pProfileID + "], pCMND = [" + pCMND + "], pMobile = [" + pMobile + "], pAddInfo = [" + pAddInfo + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.checkKYC(PPARTNERID, pProfileID, pCMND, pMobile, pAddInfo);
            LOGGER.info("ONL_checkKYC --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param phoneNumber
     * @return
     * @throws RemoteException
     */
    @Override
    public List<CardInfo> GetCardInfoByPhone(String phoneNumber) throws RemoteException {
        try {
            LOGGER.info("GetCardInfoByPhone --> [IN] --> phoneNumber = [" + phoneNumber + "]");
            CWLiveBO CWBO = new CWLiveBO();
            List<CardInfo> result = CWBO.GetCardInfoByPhone(phoneNumber);
            LOGGER.info("GetCardInfoByPhone --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param phoneNumber
     * @param last4Digit
     * @return
     * @throws RemoteException
     */
    @Override
    public List<CardInfo> GetCardInfoByPhone(String phoneNumber, String last4Digit) throws RemoteException {
        try {
            LOGGER.info("GetCardInfoByPhone --> [IN] --> phoneNumber = [" + phoneNumber + "], last4Digit = [" + last4Digit + "]");
            CWLiveBO CWBO = new CWLiveBO();
            List<CardInfo> result = CWBO.GetCardInfoByPhone(phoneNumber, last4Digit);
            LOGGER.info("GetCardInfoByPhone --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     */
    @Override
    public List<CardInfo> getCardInfo(KhoaTheReq req) throws RemoteException {
        try {
            LOGGER.info("getCardInfo --> [IN] --> KhoaTheReq = [" + req + "]");
            CWLiveBO CWBO = new CWLiveBO();
            List<CardInfo> result = CWBO.getCardInfo(req);
            LOGGER.info("getCardInfo --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param panEncrypt
     * @return
     * @throws RemoteException
     */
    @Override
    public String GetCardTransferStatus(String panEncrypt) throws RemoteException {
        try {
            LOGGER.info("GetCardTransferStatus --> [IN] --> panEncrypt = [" + panEncrypt + "]");
            CWDwhBO cwdwhBO = new CWDwhBO();
            String result = cwdwhBO.GetCardTransferStatus(panEncrypt);
            LOGGER.info("GetCardTransferStatus --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param card
     * @return
     * @throws RemoteException
     */
    @Override
    public String INSERTLOCKTHE8149(CardInfo card) throws RemoteException {
        try {
            LOGGER.info("INSERTLOCKTHE8149 --> [IN] --> CardInfo = [" + card + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.INSERTLOCKTHE8149(card);
            LOGGER.info("INSERTLOCKTHE8149 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param sequence
     * @param respCode
     * @param respDesc
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean UPDATELOCKTHE8149(String sequence, String respCode, String respDesc) throws RemoteException {
        try {
            LOGGER.info("UPDATELOCKTHE8149 --> [IN] --> sequence = [" + sequence + "], respCode = [" + respCode + "], respDesc = [" + respDesc + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            boolean result = BO.UPDATELOCKTHE8149(sequence, respCode, respDesc);
            LOGGER.info("UPDATELOCKTHE8149 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean INSERTKHOATHE(KhoaTheReq req) throws RemoteException {
        try {
            LOGGER.info("INSERTKHOATHE --> [IN] --> KhoaTheReq = [" + req + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            boolean result = BO.INSERTKHOATHE(req);
            LOGGER.info("INSERTKHOATHE --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param req
     * @param card
     * @return
     * @throws RemoteException
     */
    @Override
    public String INSERTKHOATHEDETAILS(KhoaTheReq req, CardInfo card) throws RemoteException {
        try {
            LOGGER.info("INSERTKHOATHEDETAILS --> [IN] --> KhoaTheReq = [" + req + "], CardInfo = [" + card + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.INSERTKHOATHEDETAILS(req, card);
            LOGGER.info("INSERTKHOATHEDETAILS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param refNo
     * @param req
     * @param respCode
     * @param respDesc
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean UPDATEKHOATHEDETAILS(String refNo, KhoaTheReq req, String respCode, String respDesc) throws RemoteException {
        try {
            LOGGER.info("UPDATEKHOATHEDETAILS --> [IN] --> refNo = [" + refNo + "], req = [" + req + "], respCode = [" + respCode + "], respDesc = [" + respDesc + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            boolean result = BO.UPDATEKHOATHEDETAILS(refNo, req, respCode, respDesc);
            LOGGER.info("UPDATEKHOATHEDETAILS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param acccountNo
     * @param branchcode
     * @param amount
     * @return
     * @throws RemoteException
     */
    @Override
    public ProcedureCallDto validateTopupTKTichLuy(String acccountNo, String branchcode, String amount) throws RemoteException {
        try {
            LOGGER.info("validateTopupTKTichLuy --> [IN] --> acccountNo = [" + acccountNo + "], branchcode = [" + branchcode + "], amount = [" + amount + "]");
            FCCCoreBO fccCore = new FCCCoreBO();
            ProcedureCallDto result = fccCore.validateTopupTKTichLuy(acccountNo, branchcode, amount);
            LOGGER.info("validateTopupTKTichLuy --> [OUT] --> result_ErrorStatus = [" + (result == null ? null : result.getErrorStatus()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    //IBT 247
    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public ArrayList<?> getIBTBank() throws RemoteException {
        try {
            LOGGER.info("getIBTBank --> [IN] --> input is empty");
            CTTQBO cttqbo = new CTTQBO();
            ArrayList<?> result = cttqbo.GET_BANK_LIST();
            LOGGER.info("getIBTBank --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cust_no
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList GET_MOBILE_NUMBER(String cust_no) throws RemoteException {
        try {
            LOGGER.info("GET_MOBILE_NUMBER --> [IN] --> cust_no = [" + cust_no + "]");
            CTTQBO BO = new CTTQBO();
            ArrayList result = BO.GET_MOBILE_NUMBER(cust_no);
            LOGGER.info("GET_MOBILE_NUMBER --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] IBT_INSERT_CTTQ(SmlFtCounter obj) throws RemoteException {
        try {
            LOGGER.info("IBT_INSERT_CTTQ --> [IN] --> SmlFtCounter = [" + obj + "]");
            CTTQBO BO = new CTTQBO();
            String[] result = BO.INSERT_CTTQ(obj);
            LOGGER.info("IBT_INSERT_CTTQ --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @return
     * @throws RemoteException
     */
    @Override
    public List<SmlFtCounter> IBT_getDataCTTQ(String pID) throws RemoteException {
        try {
            LOGGER.info("IBT_getDataCTTQ --> [IN] --> pID = [" + pID + "]");
            CTTQBO BO = new CTTQBO();
            List<SmlFtCounter> result = BO.getDataCTTQ(pID);
            LOGGER.info("IBT_getDataCTTQ --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param status
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> CHECK_STATUS_IBT(String status) throws RemoteException {
        try {
            LOGGER.info("CHECK_STATUS_IBT --> [IN] --> status = [" + status + "]");
            CTTQBO BO = new CTTQBO();
            ArrayList<?> result = BO.CHECK_STATUS_IBT(status);
            LOGGER.info("CHECK_STATUS_IBT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID
     * @param pUSER
     * @param pBRANCH_CODE
     * @param pSTATUS
     * @param pTYPEFUNCTION
     * @param pREFCORE
     * @throws RemoteException
     */
    @Override
    public int IBT_DUYET_CTTQ(String pID, String pUSER, String pBRANCH_CODE, String pSTATUS, String pTYPEFUNCTION, String pREFCORE) throws RemoteException {
        try {
            LOGGER.info("IBT_DUYET_CTTQ --> [IN] --> pID = [" + pID + "], pUSER = [" + pUSER + "], pBRANCH_CODE = [" + pBRANCH_CODE + "], pSTATUS = [" + pSTATUS + "], pTYPEFUNCTION = [" + pTYPEFUNCTION + "], pREFCORE = [" + pREFCORE + "]");
            CTTQBO BO = new CTTQBO();
            return BO.DUYET_CTTQ(pID, pUSER, pBRANCH_CODE, pSTATUS, pTYPEFUNCTION, pREFCORE);
            //LOGGER.info("IBT_DUYET_CTTQ --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @param user
     * @param branch
     * @param status
     * @param refcore
     * @throws RemoteException
     */
    @Override
    public void IBT_TUCHOI_CTTQ(String id, String user, String branch, String status, String refcore) throws RemoteException {
        try {
            LOGGER.info("IBT_TUCHOI_CTTQ --> [IN] --> id = [" + id + "], user = [" + user + "], branch = [" + branch + "], status = [" + status + "], refcore = [" + refcore + "]");
            CTTQBO BO = new CTTQBO();
            BO.IBT_TUCHOI_CTTQ(id, user, branch, status, refcore);
            LOGGER.info("IBT_TUCHOI_CTTQ --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param madv
     * @param trangthai
     * @param tungay
     * @param denngay
     * @param tknguon
     * @param tkdich
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> SEARCH_IBT(String madv, String trangthai, String tungay, String denngay, String tknguon, String tkdich) throws RemoteException {
        try {
            LOGGER.info("SEARCH_IBT --> [IN] --> madv = [" + madv + "], trangthai = [" + trangthai + "], tungay = [" + tungay + "], denngay = [" + denngay + "], tknguon = [" + tknguon + "], tkdich = [" + tkdich + "]");
            CTTQBO BO = new CTTQBO();
            ArrayList<?> result = BO.SEARCH_IBT(madv, trangthai, tungay, denngay, tknguon, tkdich);
            LOGGER.info("SEARCH_IBT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pCARDNO
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] checkCARD(String pCARDNO) throws RemoteException {
        try {
            LOGGER.info("checkCARD --> [IN] --> pCARDNO = [" + pCARDNO + "]");
            CWLiveBO cwLiveBO = new CWLiveBO();
            String[] result = cwLiveBO.checkCARD(pCARDNO);
            LOGGER.info("checkCARD --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @throws RemoteException
     */
    @Override
    public int NAPAS_updateSendIBT(String ID
    ) throws RemoteException {
        try {
            LOGGER.info("NAPAS_updateSendIBT --> [IN] --> ID = [" + ID + "]");
            NAPASBO BO = new NAPASBO();
            int result = BO.updateSendIBT(ID);
            LOGGER.info("NAPAS_updateSendIBT --> [OUT] --> " + result);
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<?> CHECK_TRANSFER(String ID) throws Exception {
        try {
            LOGGER.info("CHECK_TRANSFER --> [IN] --> ID = [" + ID + "]");
            CTTQBO BO = new CTTQBO();
            ArrayList<?> result = BO.CHECK_TRANSFER(ID);
            LOGGER.info("CHECK_TRANSFER --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ITRANSID
     * @param ISOURCEACCOUNT
     * @param IDESTACCCOUNT
     * @param IAMOUNT
     * @param ICCY
     * @param ITRANSDATE
     * @param IPARTNERID
     * @param IDESCRIPTION
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] MMSTRANSFERACCTOACC(String ITRANSID, String ISOURCEACCOUNT, String IDESTACCCOUNT,
            Double IAMOUNT, String ICCY, String ITRANSDATE, String IPARTNERID,
            String IDESCRIPTION) throws RemoteException {
        try {
            LOGGER.info("MMSTRANSFERACCTOACC --> [IN] --> ITRANSID = [" + ITRANSID + "], ISOURCEACCOUNT = [" + ISOURCEACCOUNT + "], IDESTACCCOUNT = [" + IDESTACCCOUNT + "], IAMOUNT = [" + IAMOUNT + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.MMSTRANSFERACCTOACC(ITRANSID, ISOURCEACCOUNT, IDESTACCCOUNT, IAMOUNT, ICCY,
                    ITRANSDATE, IPARTNERID, IDESCRIPTION);
            LOGGER.info("MMSTRANSFERACCTOACC --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param IBANKID
     * @param IREFCORE
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean UPDATEMMSTRANSFERACCTOACC(String IBANKID, String IREFCORE) throws RemoteException {
        try {
            LOGGER.info("UPDATEMMSTRANSFERACCTOACC --> [IN] --> IBANKID = [" + IBANKID + "], IREFCORE = [" + IREFCORE + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            boolean result = BO.UPDATEMMSTRANSFERACCTOACC(IBANKID, IREFCORE);
            LOGGER.info("UPDATEMMSTRANSFERACCTOACC --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param P_MERCHANT_CODE
     * @param P_TERMINAL_ID
     * @param P_ACCOUNT_MERCHANT
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean MMSCHECKACCOUNT(String P_MERCHANT_CODE, String P_TERMINAL_ID, String P_ACCOUNT_MERCHANT) throws RemoteException {
        try {
            LOGGER.info("MMSCHECKACCOUNT --> [IN] --> P_MERCHANT_CODE = [" + P_MERCHANT_CODE + "], P_TERMINAL_ID = [" + P_TERMINAL_ID + "], P_ACCOUNT_MERCHANT = [" + P_ACCOUNT_MERCHANT + "]");
            MmsBO mmsBo = new MmsBO();
            boolean result = mmsBo.MMSCHECKACCOUNT(P_MERCHANT_CODE, P_TERMINAL_ID, P_ACCOUNT_MERCHANT);
            LOGGER.info("UPDATEMMSTRANSFERACCTOACC --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    HashMap<String, String> hmap = new HashMap<String, String>();
    HashMap<String, String> hmap2 = new HashMap<String, String>();

    /**
     *
     * @param trace
     * @param reponse
     * @throws RemoteException
     */
    @Override
    public void napas_insertReponseHM(String trace, String reponse) throws RemoteException {
        LOGGER.info("napas_insertReponseHM --> [IN] --> trace = [" + trace + "], reponse = [" + reponse + "]");
        hmap.put(trace, reponse);
        LOGGER.info("napas_insertReponseHM --> [OUT] --> does not return");
    }

    /**
     *
     * @param trace
     * @return
     * @throws RemoteException
     */
    @Override
    public String napas_getReponseHM(String trace) throws RemoteException {
        LOGGER.info("napas_getReponseHM --> [IN] --> trace = [" + trace + "]");
        String result = null;
        if (hmap.get(trace) != null) {
            String response = (String) hmap.get(trace);
            hmap.remove(trace);
            result = response;
        }
        LOGGER.info("napas_getReponseHM --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /**
     *
     * @param trace
     * @param request
     * @throws RemoteException
     */
    @Override
    public void napas_insertRequestHM(String trace, String request) throws RemoteException {
        LOGGER.info("napas_insertRequestHM --> [IN] --> trace = [" + trace + "], request = [" + request + "]");
        hmap2.put(trace, request);
        LOGGER.info("napas_insertRequestHM --> [OUT] --> does not return");
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public List napas_getRequestHM() throws RemoteException {
        LOGGER.info("napas_getRequestHM --> [IN] --> input is empty");
        List list = new ArrayList();
        Iterator it = hmap2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            RequestIBT obj = new RequestIBT();
            obj.setTrace(pair.getKey().toString());
            obj.setRequest(pair.getValue().toString());
            list.add(obj);
            it.remove(); // avoids a ConcurrentModificationException
        }
        LOGGER.info("napas_getRequestHM --> [OUT] --> Size = [" + (list == null ? null : list.size()) + "]");
        return list;
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public SCBBranch getSCBBranch() throws RemoteException {
        try {
            LOGGER.info("getSCBBranch --> [IN] --> input is empty");
            OnlinePaymentBO onlinePaymentBO = new OnlinePaymentBO();
            SCBBranch result = onlinePaymentBO.getSCBBranch();
            LOGGER.info("getSCBBranch --> [OUT] --> result_scbBranch = [" + (result == null ? null : result.getScbBranch()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
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
     * @throws RemoteException
     */
    @Override
    public ProcedureCallDto getFeeMobileHasSFee(String brnTrn, String productcode, String ccy, String amt, String acNo, String acBrn) throws RemoteException {
        try {
            LOGGER.info("getFeeMobileHasSFee --> [IN] --> brnTrn = [" + brnTrn + "], productcode = [" + productcode + "], ccy = [" + ccy + "], amt = [" + amt + "], acNo = [" + acNo + "], acBrn = [" + acBrn + "]");
            FCCCoreBO fccCore = new FCCCoreBO();
            ProcedureCallDto result = fccCore.getFeeMobileHasSFee(brnTrn, productcode, ccy, amt, acNo, acBrn);
            LOGGER.info("getFeeMobileHasSFee --> [OUT] --> result_ErrorStatus = [" + (result == null ? null : result.getErrorStatus()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param accountno
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList getAvgBalanceFromCore(String accountno) throws RemoteException {
        try {
            LOGGER.info("getAvgBalanceFromCore --> [IN] --> accountno = [" + accountno + "]");
            FCCCoreBO fccCore = new FCCCoreBO();
            ArrayList result = fccCore.getAvgBalanceFromCore(accountno);
            LOGGER.info("getAvgBalanceFromCore --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pTransid
     * @param pRefTransid
     * @param pAmount
     * @param pTransdate
     * @param pDescription
     * @param pPartnerid
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] ONL_REFUND_FOR_TAKE_OUT(String pTransid,
            String pRefTransid,
            Double pAmount,
            String pTransdate,
            String pDescription,
            String pPartnerid) throws RemoteException {
        try {
            LOGGER.info("ONL_REFUND_FOR_TAKE_OUT --> [IN] --> pTransid = [" + pTransid + "], pRefTransid = [" + pRefTransid + "], pAmount = [" + pAmount + "], pTransdate = [" + pTransdate + "], pDescription = [" + pDescription + "], pPartnerid = [" + pPartnerid + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String[] result = BO.REFUND_FOR_TAKEOUT(pTransid, pRefTransid, pAmount, pTransdate, pDescription, pPartnerid);
            LOGGER.info("ONL_REFUND_FOR_TAKE_OUT --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public boolean isAlive() throws RemoteException {
        LOGGER.info("isAlive --> [IN] --> input is empty");
        boolean result = true;
        LOGGER.info("isAlive --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /**
     *
     * @param list
     * @throws RemoteException
     */
    @Override
    public void TVSI_insertKhachHang(
            List<TVSI_KHACHHANG> list) throws RemoteException {
        try {
            LOGGER.info("TVSI_insertKhachHang --> [IN] --> List<TVSI_KHACHHANG> = [" + list.size() + "]");
            TVSIDATABO bo = new TVSIDATABO();
            bo.insertKhachHang(list);
            LOGGER.info("TVSI_insertKhachHang --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param list
     * @throws RemoteException
     */
    @Override
    public void TVSI_insertDanhMuc(
            List<TVSI_DANHMUC> list) throws RemoteException {
        try {
            LOGGER.info("TVSI_insertDanhMuc --> [IN] --> List<TVSI_DANHMUC> = [" + list.size() + "]");
            TVSIDATABO bo = new TVSIDATABO();
            bo.insertDanhMuc(list);
            LOGGER.info("TVSI_insertDanhMuc --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param list
     * @throws RemoteException
     */
    @Override
    public void TVSI_insertGiaoDich(
            List<TVSI_GIAODICH> list) throws RemoteException {
        try {
            LOGGER.info("TVSI_insertGiaoDich --> [IN] --> List<TVSI_GIAODICH> = [" + list.size() + "]");
            TVSIDATABO bo = new TVSIDATABO();
            bo.insertGiaoDich(list);
            LOGGER.info("TVSI_insertGiaoDich --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param list
     * @throws RemoteException
     */
    @Override
    public void TVSI_insertGiaoDichHuy(
            List<TVSI_GIAODICHHUY> list) throws RemoteException {
        try {
            LOGGER.info("TVSI_insertGiaoDichHuy --> [IN] --> List<TVSI_GIAODICH> = [" + list.size() + "]");
            TVSIDATABO bo = new TVSIDATABO();
            bo.insertGiaoDichHuy(list);
            LOGGER.info("TVSI_insertGiaoDichHuy --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param list
     * @throws RemoteException
     */
    @Override
    public void TVSI_insertSaoKe(
            List<TVSI_SAOKE> list) throws RemoteException {
        try {
            LOGGER.info("TVSI_insertSaoKe --> [IN] --> List<TVSI_GIAODICH> = [" + list.size() + "]");
            TVSIDATABO bo = new TVSIDATABO();
            bo.insertSaoKe(list);
            LOGGER.info("TVSI_insertSaoKe --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String getTransIDMK() throws Exception {
        LOGGER.info("getTransIDMK --> [IN] --> input is empty");
        TokenkeyBO bo = new TokenkeyBO();
        String result = bo.getTransID();
        LOGGER.info("getTransIDMK --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /**
     *
     * @param serialno
     * @param challengeid
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList getMKVerifyInfo(String serialno, String challengeid) throws Exception {
        LOGGER.info("getMKVerifyInfo --> [IN] --> serialno = [" + serialno + "], challengeid = [" + challengeid + "]");
        TokenkeyBO bo = new TokenkeyBO();
        ArrayList result = bo.getMKVerifyInfo(serialno, challengeid);
        LOGGER.info("getMKVerifyInfo --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
        return result;
    }

    /**
     *
     * @param SERIAL
     * @param CHALLENGE
     * @param TRANSID
     * @param AUDITID
     * @return
     * @throws Exception
     */
    @Override
    public int INSERT_MK_TOKEN_LOG(String SERIAL, String CHALLENGE, String TRANSID, String AUDITID) throws Exception {
        LOGGER.info("INSERT_MK_TOKEN_LOG --> [IN] --> SERIAL = [" + SERIAL + "], CHALLENGE = [" + CHALLENGE + "], TRANSID = [" + TRANSID + "], AUDITID = [" + AUDITID + "]");
        TokenkeyBO bo = new TokenkeyBO();
        int result = bo.INSERT_MK_TOKEN_LOG(SERIAL, CHALLENGE, TRANSID, AUDITID);
        LOGGER.info("INSERT_MK_TOKEN_LOG --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /**
     *
     * @param idServiceCode
     * @param idProviderCode
     * @return
     * @throws RemoteException
     */
    @Override
    public String getProviderOriginalCode(String idServiceCode, String idProviderCode) throws RemoteException {
        try {
            LOGGER.info("getProviderOriginalCode --> [IN] --> idServiceCode = [" + idServiceCode + "], idProviderCode = [" + idProviderCode + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.getProviderOriginalCode(idServiceCode, idProviderCode);
            LOGGER.info("INSERT_MK_TOKEN_LOG --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idServiceCode
     * @param idProviderCode
     * @return
     * @throws RemoteException
     */
    @Override
    public String getPartnerCode(String idServiceCode, String idProviderCode) throws RemoteException {
        try {
            LOGGER.info("getPartnerCode --> [IN] --> idServiceCode = [" + idServiceCode + "], idProviderCode = [" + idProviderCode + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.getPartnerCode(idServiceCode, idProviderCode);
            LOGGER.info("getPartnerCode --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param userid
     * @param usertype
     * @return
     * @throws RemoteException
     */
    @Override
    public EBANKUSER ONL_getInfoEbankUser(String userid, String usertype) throws RemoteException {
        try {
            LOGGER.info("ONL_getInfoEbankUser --> [IN] --> userid = [" + userid + "], usertype = [" + usertype + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            EBANKUSER result = BO.getInfoEbankUser(userid, usertype);
            LOGGER.info("ONL_getInfoEbankUser --> [OUT] --> result_CUST_AC_NO = [" + (result == null ? null : result.getCUST_AC_NO()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param GNT
     * @return
     * @throws Exception
     */
    @Override
    public String[] NTDT_INSERT_GNT2019(NTDT_GNT GNT) throws Exception {
        try {
            LOGGER.info("NTDT_INSERT_GNT2019 --> [IN] --> NTDT_GNT = [" + GNT + "]");
            NTDTBO BO = new NTDTBO();
            String[] result = BO.INSERT_GNT2019(GNT);
            LOGGER.info("NTDT_INSERT_GNT2019 --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_GNT
     * @param pID_CTU_CTIET
     * @param pID_CTU
     * @param pNDUNG_NOP
     * @param pMA_NDKT
     * @param pMA_CHUONG
     * @param pKY_THUE
     * @param pTIEN_PNOP
     * @param pGHI_CHU
     * @param pSO_TK_TB_QD
     * @throws RemoteException
     */
    @Override
    public void NTDT_INSERT_CHUNGTU_CHIIET2019(int pID_GNT,
            String pID_CTU_CTIET,
            String pID_CTU,
            String pNDUNG_NOP,
            String pMA_NDKT,
            String pMA_CHUONG,
            String pKY_THUE,
            String pTIEN_PNOP,
            String pGHI_CHU,
            String pSO_TK_TB_QD) throws RemoteException {
        try {
            LOGGER.info("NTDT_INSERT_CHUNGTU_CHIIET2019 --> [IN] --> pID_GNT = [" + pID_GNT + "], pID_CTU_CTIET = [" + pID_CTU_CTIET + "], pID_CTU = [" + pID_CTU + "]");
            NTDTBO BO = new NTDTBO();
            BO.INSERT_CHUNGTU_CHIIET2019(pID_GNT, pID_CTU_CTIET, pID_CTU, pNDUNG_NOP, pMA_NDKT, pMA_CHUONG, pKY_THUE, pTIEN_PNOP, pGHI_CHU, pSO_TK_TB_QD);
            LOGGER.info("NTDT_INSERT_CHUNGTU_CHIIET2019 --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pMABAOCAO
     * @return
     * @throws RemoteException
     */
    @Override
    public List<NTDT_DOICHIEUGD> NTDT_BAOCAODOICHIEU_2019(int pID_DOICHIEU_GD, String pMABAOCAO) throws RemoteException {
        try {
            LOGGER.info("NTDT_BAOCAODOICHIEU_2019 --> [IN] --> pID_DOICHIEU_GD = [" + pID_DOICHIEU_GD + "], pMABAOCAO = [" + pMABAOCAO + "]");
            NTDTBO BO = new NTDTBO();
            List<NTDT_DOICHIEUGD> result = BO.BAOCAODOICHIEU_2019(pID_DOICHIEU_GD, pMABAOCAO);
            LOGGER.info("NTDT_BAOCAODOICHIEU_2019 --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pID_DOICHIEU_GD
     * @param pMA_GDICH
     * @param pNGAY_GUI_GDICH
     * @param pNGAY_NOP_GNT
     * @param pNGAY_NOP_THUE
     * @param pTTHAI_GDICH
     * @param pTTHAI_CTU
     * @param pMAHIEU_CTU
     * @param pSO_CTU
     * @param pPBAN_TLIEU_XML
     * @param pID_CTU
     * @param pSO_GNT
     * @param pMA_CTU
     * @param pHTHUC_NOP
     * @param pMST_NNOP
     * @param pTEN_NNOP
     * @param pMA_CQT
     * @param pTEN_CQT
     * @param pDIACHI_NNOP
     * @param pMA_XA_NNOP
     * @param pTEN_XA_NNOP
     * @param pMA_HUYEN_NNOP
     * @param pTEN_HUYEN_NNOP
     * @param pMA_TINH_NNOP
     * @param pTEN_TINH_NNOP
     * @param pMST_NTHAY
     * @param pTEN_NTHAY
     * @param pDIACHI_NTHAY
     * @param pTEN_HUYEN_NTHAY
     * @param pTEN_TINH_NTHAY
     * @param pMA_NHANG_NOP
     * @param pTEN_NHANG_NOP
     * @param pSTK_NHANG_NOP
     * @param pMA_HIEU_KBAC
     * @param pTEN_KBAC
     * @param pMA_TINH_KBAC
     * @param pTEN_TINH_KBAC
     * @param pLOAI_TK_THU
     * @param pTEN_TK_THU
     * @param pSTK_THU
     * @param pID_TK_KNGHI
     * @param pTK_KNGHI
     * @param pMA_CQTHU
     * @param pTEN_CQTHU
     * @param pNGAY_LAP
     * @param pTONG_TIEN
     * @param pVAN_ID
     * @param MaNguyente
     * @param Mathamchieu
     * @return
     * @throws Exception
     */
    @Override
    public int NTDT_INSERT_DOICHIEU_GNT2019(int pID_DOICHIEU_GD,
            String pMA_GDICH,
            String pNGAY_GUI_GDICH,
            String pNGAY_NOP_GNT,
            String pNGAY_NOP_THUE,
            String pTTHAI_GDICH,
            String pTTHAI_CTU,
            String pMAHIEU_CTU,
            String pSO_CTU,
            String pPBAN_TLIEU_XML,
            String pID_CTU,
            String pSO_GNT,
            String pMA_CTU,
            String pHTHUC_NOP,
            String pMST_NNOP,
            String pTEN_NNOP,
            String pMA_CQT,
            String pTEN_CQT,
            String pDIACHI_NNOP,
            String pMA_XA_NNOP,
            String pTEN_XA_NNOP,
            String pMA_HUYEN_NNOP,
            String pTEN_HUYEN_NNOP,
            String pMA_TINH_NNOP,
            String pTEN_TINH_NNOP,
            String pMST_NTHAY,
            String pTEN_NTHAY,
            String pDIACHI_NTHAY,
            String pTEN_HUYEN_NTHAY,
            String pTEN_TINH_NTHAY,
            String pMA_NHANG_NOP,
            String pTEN_NHANG_NOP,
            String pSTK_NHANG_NOP,
            String pMA_HIEU_KBAC,
            String pTEN_KBAC,
            String pMA_TINH_KBAC,
            String pTEN_TINH_KBAC,
            String pLOAI_TK_THU,
            String pTEN_TK_THU,
            String pSTK_THU,
            String pID_TK_KNGHI,
            String pTK_KNGHI,
            String pMA_CQTHU,
            String pTEN_CQTHU,
            String pNGAY_LAP,
            String pTONG_TIEN,
            String pVAN_ID,
            String MaNguyente,
            String Mathamchieu) throws Exception {
        try {
            LOGGER.info("NTDT_INSERT_DOICHIEU_GNT2019 --> [IN] --> pID_DOICHIEU_GD = [" + pID_DOICHIEU_GD + "], pMA_GDICH = [" + pMA_GDICH + "], pID_CTU = [" + pID_CTU + "]");
            NTDTBO BO = new NTDTBO();
            int result = BO.INSERT_DOICHIEU_GNT2019(pID_DOICHIEU_GD, pMA_GDICH, pNGAY_GUI_GDICH, pNGAY_NOP_GNT, pNGAY_NOP_THUE, pTTHAI_GDICH, pTTHAI_CTU, pMAHIEU_CTU, pSO_CTU, pPBAN_TLIEU_XML, pID_CTU, pSO_GNT, pMA_CTU, pHTHUC_NOP, pMST_NNOP, pTEN_NNOP, pMA_CQT, pTEN_CQT, pDIACHI_NNOP, pMA_XA_NNOP, pTEN_XA_NNOP, pMA_HUYEN_NNOP, pTEN_HUYEN_NNOP, pMA_TINH_NNOP, pTEN_TINH_NNOP, pMST_NTHAY, pTEN_NTHAY, pDIACHI_NTHAY, pTEN_HUYEN_NTHAY, pTEN_TINH_NTHAY, pMA_NHANG_NOP, pTEN_NHANG_NOP, pSTK_NHANG_NOP, pMA_HIEU_KBAC, pTEN_KBAC, pMA_TINH_KBAC, pTEN_TINH_KBAC, pLOAI_TK_THU, pTEN_TK_THU, pSTK_THU, pID_TK_KNGHI, pTK_KNGHI, pMA_CQTHU, pTEN_CQTHU, pNGAY_LAP, pTONG_TIEN, pVAN_ID, MaNguyente, Mathamchieu);
            LOGGER.info("NTDT_INSERT_DOICHIEU_GNT2019 --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param LOC
     * @param socuoi
     * @return
     * @throws Exception
     */
    @Override
    public String[] getDetailCard(String LOC, String socuoi) throws Exception {
        try {
            LOGGER.info("getDetailCard --> [IN] --> LOC = [" + LOC + "], socuoi = [" + socuoi + "]");
            CWDwhBO BO = new CWDwhBO();
            String[] result = BO.getDetailCard(LOC, socuoi);
            LOGGER.info("getDetailCard --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (RemoteException ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param LOC
     * @param Pass
     * @return
     * @throws Exception
     */
    @Override
    public int cw_checkPassFile(String LOC, String Pass) throws Exception {
        LOGGER.info("cw_checkPassFile --> [IN] --> LOC = [" + LOC + "], Pass = [" + Pass + "]");
        CWDwhBO bo = new CWDwhBO();
        int result = bo.checkPassFile(LOC, Pass);
        LOGGER.info("cw_checkPassFile --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /**
     *
     * @param LOC
     * @return
     * @throws Exception
     */
    @Override
    public Object[] cw_getPdfFile(String LOC) throws Exception {
        LOGGER.info("cw_getPdfFile --> [IN] --> LOC = [" + LOC + "]");
        CWDwhBO bo = new CWDwhBO();
        Object[] result = bo.getPdfFile(LOC);
        LOGGER.info("cw_getPdfFile --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
        return result;
    }

    /**
     *
     * @param req
     * @return
     * @throws RemoteException
     */
    @Override
    public ExchangeRateRes getExchangeRate(ExchangeRateReq req) throws RemoteException {
        try {
            LOGGER.info("getExchangeRate --> [IN] --> ExchangeRateReq = [" + req + "]");
            FCCCoreBO fccCore = new FCCCoreBO();
            ExchangeRateRes result = fccCore.getExchangeRate(req);
            LOGGER.info("getExchangeRate --> [OUT] --> result_moneyExchange = [" + result.getMoneyExchange() + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idTrans
     * @param res
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean updateRateToSI_TRFFROMSI_DETAIL(String idTrans, ExchangeRateRes res) throws RemoteException {
        try {
            LOGGER.info("updateRateToSI_TRFFROMSI_DETAIL --> [IN] --> idTrans = [" + idTrans + "], res = [" + res + "]");
            SIBO BO = new SIBO();
            boolean result = BO.updateRateToSI_TRFFROMSI_DETAIL(idTrans, res);
            LOGGER.info("updateRateToSI_TRFFROMSI_DETAIL --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param transDetail
     * @return
     * @throws RemoteException
     */
    @Override
    public Object[] insertDetailToSi(TransactionDetail transDetail) throws RemoteException {
        try {
            LOGGER.info("insertDetailToSi --> [IN] --> TransactionDetail = [" + transDetail + "]");
            SIBO BO = new SIBO();
            Object[] result = BO.insertDetailToSi(transDetail);
            LOGGER.info("insertDetailToSi --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pTRANSTYPE
     * @param isCollated
     * @return
     * @throws RemoteException
     */
    @Override
    public List<NapasCollatedId> NAPAS_getIBTCollateOut(
            String pTRANSTYPE,
            int isCollated
    ) throws RemoteException {
        try {
            LOGGER.info("NAPAS_getIBTCollateOut --> [IN] --> pTRANSTYPE = [" + pTRANSTYPE + "], isCollated = [" + isCollated + "]");
            NAPASBO BO = new NAPASBO();
            List<NapasCollatedId> result = BO.getIBTCollateOut(pTRANSTYPE, isCollated);
            LOGGER.info("NAPAS_getIBTCollateOut --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param CARDNUMBER
     * @param mobileno
     * @param fullname
     * @return
     * @throws Exception
     */
    @Override
    public String cw_INSERT_INFO_CHANGE_PIN(String CARDNUMBER, String mobileno, String fullname) throws Exception {
        LOGGER.info("cw_INSERT_INFO_CHANGE_PIN --> [IN] --> CARDNUMBER = [" + CARDNUMBER + "], mobileno = [" + mobileno + "], fullname = [" + fullname + "]");
        CWDwhBO bo = new CWDwhBO();
        String result = bo.INSERT_INFO_CHANGE_PIN(CARDNUMBER, mobileno, fullname);
        LOGGER.info("cw_INSERT_INFO_CHANGE_PIN --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /**
     *
     * @param FromNumber
     * @param AuditNumber
     * @param MerchantType
     * @param Transdate
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<?> NAPAS_isExistTransfer(String FromNumber, String AuditNumber, String MerchantType,
            String Transdate) throws Exception {
        LOGGER.info("NAPAS_isExistTransfer --> [IN] --> FromNumber = [" + FromNumber + "], AuditNumber = [" + AuditNumber + "], MerchantType = [" + MerchantType + "], Transdate = [" + Transdate + "]");
        NAPASBO BO = new NAPASBO();
        ArrayList<?> result = BO.isExistTransfer(FromNumber, AuditNumber, MerchantType, Transdate);
        LOGGER.info("NAPAS_isExistTransfer --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
        return result;
    }

    /**
     *
     * @param refno
     * @return
     * @throws Exception
     */
    @Override
    public Object[] IBT_getRequestTranfer(String trace, String status) throws Exception {
        LOGGER.info("IBT_getRequestTranfer --> [IN] --> refno = [" + trace + "]");
        NAPASBO BO = new NAPASBO();
        Object[] result = BO.getRequestTranfer(trace, status);
        LOGGER.info("IBT_getRequestTranfer --> [OUT] --> result_RefCore = [" + (result == null ? null : result.length) + "]");
        return result;
    }

    /**
     *
     * @param emailTd
     * @return
     * @throws RemoteException
     */
    @Override
    public BigDecimal insertGwEmailTd(GwEmailTd emailTd) throws RemoteException {
        try {
            LOGGER.info("insertGwEmailTd --> [IN] --> GwEmailTd = [" + emailTd + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            BigDecimal result = smsscbBO.insertGwEmailTd(emailTd);
            LOGGER.info("insertGwEmailTd --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param emailTd
     * @param FromDate
     * @param ToDate
     * @return
     * @throws RemoteException
     */
    @Override
    public List getListGwEmailTd(GwEmailTd emailTd, String FromDate, String ToDate) throws RemoteException {
        try {
            LOGGER.info("getListGwEmailTd --> [IN] --> FromDate = [" + FromDate + "], ToDate = [" + ToDate + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            List result = smsscbBO.getListGwEmailTd(emailTd, FromDate, ToDate);
            LOGGER.info("getListGwEmailTd --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param emailTd
     * @throws RemoteException
     */
    @Override
    public void updateGwEmailTd(GwEmailTd emailTd) throws RemoteException {
        try {
            LOGGER.info("updateGwEmailTd --> [IN] --> GwEmailTd = [" + emailTd + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updateGwEmailTd(emailTd);
            LOGGER.info("updateGwEmailTd --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param info
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean KICHHOATTHE(KichHoatTheInfo info) throws RemoteException {
        LOGGER.info("KICHHOATTHE --> [IN] --> KichHoatTheInfo = [" + info + "]");
        boolean result = false;
        try {
            OnlinePaymentBO BO = new OnlinePaymentBO();
            result = BO.KICHHOATTHE(info);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
        LOGGER.info("KICHHOATTHE --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /* bien action 
    * 0: khoi tao; 1: duyet gui tin nhan; 2: tu choi gui tin nhan
     */
    @Override
    public boolean CIMS_RESEND(SmsDetail info, int action) throws RemoteException {
        LOGGER.info("CIMS_RESEND --> [IN] --> SmsInfo = [" + info + "], action = [" + action + "]");
        boolean result = false;
        try {
            OnlinePaymentBO BO = new OnlinePaymentBO();
            result = BO.CIMS_RESEND(info, action);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
        LOGGER.info("CIMS_RESEND --> [OUT] --> result = [" + result + "]");
        return result;
    }

    @Override
    public List<SmsDetail> QUERY_SMS_TO_RESEND(String phone, String fromDate, String toDate) throws RemoteException {
        LOGGER.info("CIMS_RESEND --> [IN] --> phone = [" + phone + "], fromDate = [" + fromDate + "], toDate = {" + toDate + "]");
        List<SmsDetail> result = new ArrayList<>();
        try {
            OnlinePaymentBO BO = new OnlinePaymentBO();
            result = BO.QUERY_SMS_TO_RESEND(phone, fromDate, toDate);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("CIMS_RESEND --> [OUT] --> result = [" + result.size() + "]");
        return result;
    }

    @Override
    public SmsDetail QUERY_SMS_TO_RESEND_BY_ID(String id) throws RemoteException {
        LOGGER.info("CIMS_RESEND --> [IN] --> id = [" + id + "]");
        SmsDetail result = null;
        try {
            OnlinePaymentBO BO = new OnlinePaymentBO();
            result = BO.QUERY_SMS_TO_RESEND_BY_ID(id);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("CIMS_RESEND --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /**
     *
     * @param pID_MASTER
     * @param pTXNDETAILID
     * @param pPARTNERACCOUNT
     * @param pCUSTUMERNAME
     * @param pCUSTUMERACCOUNT
     * @param pBANKCODE
     * @param pBranchname
     * @param pAmount
     * @param pCCY
     * @param pDescription
     * @param pTYPETRANSFER
     * @param pTYPECUSTACCOUNT
     * @param rate
     * @param amtExchange
     * @param ccyExchange
     * @return
     * @throws RemoteException
     */
    @Override
    public Object[] SI_INSERTTRANSFERFROMSIDETAILKIEUHOI(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,
            String pCUSTUMERNAME,
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            String pBranchname,
            double pAmount,
            String pCCY,
            String pDescription,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT,
            BigDecimal rate,
            BigDecimal amtExchange,
            String ccyExchange,
            String personId,
            String firstName,
            String lastName,
            String passNo,
            String birthday,
            String address,
            String nationality,
            String custtype
    ) throws RemoteException {
        try {
            LOGGER.info("SI_INSERTTRANSFERFROMSIDETAILKIEUHOI --> [IN] --> pID_MASTER = [" + pID_MASTER + "], pTXNDETAILID = [" + pTXNDETAILID + "], pPARTNERACCOUNT = [" + pPARTNERACCOUNT + "]");
            SIBO BO = new SIBO();
            Object[] result = BO.INSERTTRANSFERFROMSIDETAILKIEUHOI(pID_MASTER, pTXNDETAILID, pPARTNERACCOUNT, pCUSTUMERNAME, pCUSTUMERACCOUNT, pBANKCODE, pBranchname, pAmount, pCCY, pDescription, pTYPETRANSFER, pTYPECUSTACCOUNT, rate, amtExchange, ccyExchange, personId, firstName, lastName, passNo, birthday, address, nationality, custtype);
            LOGGER.info("SI_INSERTTRANSFERFROMSIDETAILKIEUHOI --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param khoaTheInfo
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean INSERTKHOATHE(KhoaTheInfo khoaTheInfo) throws RemoteException {
        LOGGER.info("INSERTKHOATHE --> [IN] --> KhoaTheInfo = [" + khoaTheInfo + "]");
        boolean result = false;
        try {
            OnlinePaymentBO BO = new OnlinePaymentBO();
            result = BO.INSERTKHOATHE(khoaTheInfo);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
        LOGGER.info("INSERTKHOATHE --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /**
     *
     * @param khoaTheDetailsInfo
     * @return
     * @throws RemoteException
     */
    @Override
    public String INSERTKHOATHEDETAIL(KhoaTheDetailsInfo khoaTheDetailsInfo) throws RemoteException {
        try {
            LOGGER.info("INSERTKHOATHEDETAIL --> [IN] --> KhoaTheDetailsInfo = [" + khoaTheDetailsInfo + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.INSERTKHOATHEDETAIL(khoaTheDetailsInfo);
            LOGGER.info("INSERTKHOATHEDETAIL --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param khoaTheInfo
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean UPDATEKHOATHE(KhoaTheInfo khoaTheInfo) throws RemoteException {
        LOGGER.info("UPDATEKHOATHE --> [IN] --> KhoaTheInfo = [" + khoaTheInfo + "]");
        boolean result = false;
        try {
            OnlinePaymentBO BO = new OnlinePaymentBO();
            result = BO.UPDATEKHOATHE(khoaTheInfo);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
        LOGGER.info("UPDATEKHOATHE --> [OUT] --> result = [" + result + "]");
        return result;
    }

    /**
     *
     * @param khoaTheDetailsInfo
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean UPDATEKHOATHEDETAIL(KhoaTheDetailsInfo khoaTheDetailsInfo) throws RemoteException {
        LOGGER.info("UPDATEKHOATHEDETAIL --> [IN] --> KhoaTheDetailsInfo = [" + khoaTheDetailsInfo + "]");
        boolean result = false;
        try {
            OnlinePaymentBO BO = new OnlinePaymentBO();
            result = BO.UPDATEKHOATHEDETAIL(khoaTheDetailsInfo);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
        LOGGER.info("UPDATEKHOATHEDETAIL --> [OUT] --> result = [" + result + "]");
        return result;
    }

    //BEGIN:SONN5: 13/NOV/2019
    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public int CTTQ_DELETE(String id) throws RemoteException {
        LOGGER.info("CTTQ_DELETE --> [IN] --> id = [" + id + "]");
        int result = 0;
        try {
            CTTQBO bo = new CTTQBO();
            result = bo.DELETE_CTTQ_BO(id);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("CTTQ_DELETE --> [OUT] --> result = [" + result + "]");
        return result;
    }

    //son 28/10/2019
    /**
     *
     * @param pGL
     * @param brcode
     * @param ccy
     * @param type
     * @return
     * @throws RemoteException
     */
    @Override
    public float GET_GL_BALANCE(String pGL, String brcode, String ccy, String type) throws RemoteException {
        LOGGER.info("GET_GL_BALANCE --> [IN] --> pGL = [" + pGL + "], brcode = [" + brcode + "], ccy = [" + ccy + "], type = [" + type + "]");
        float result = -1;
        try {
            CTTQBO bo = new CTTQBO();
            result = bo.GET_GL_BALANCE_BO(pGL, brcode, ccy, type);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("GET_GL_BALANCE --> [OUT] --> result = [" + result + "]");
        return result;
    }
    //SON 01/nov/2019

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public int DELETE_INSURANCE_PAYMENT(String id) throws RemoteException {
        LOGGER.info("DELETE_INSURANCE_PAYMENT --> [IN] --> id = [" + id + "]");
        int result = 0;
        try {
            SmsSCBBO bo = new SmsSCBBO();
            result = bo.DELETE_INSURANCE_PAYMENT_BO(id);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("DELETE_INSURANCE_PAYMENT --> [OUT] --> result = [" + result + "]");
        return result;
    }
    //SON 01/nov/2019

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public int DELETE_PAYMENT_CREDITCARD(String id) throws RemoteException {
        LOGGER.info("DELETE_PAYMENT_CREDITCARD --> [IN] --> id = [" + id + "]");
        int result = 0;
        try {
            SmsSCBBO bo = new SmsSCBBO();
            result = bo.DELETE_PAYMENT_CREDITCARD_BO(id);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("DELETE_PAYMENT_CREDITCARD --> [OUT] --> result = [" + result + "]");
        return result;
    }
    //END:SONN5: 13/NOV/2019

    /**
     *
     * @param tdLuck
     * @return
     * @throws RemoteException
     */
    @Override
    public String insertGwTdLuck(GwTdLucky tdLuck) throws RemoteException {
        try {
            LOGGER.info("insertGwTdLuck --> [IN] --> GwTdLucky_custAcNo = [" + tdLuck.getCustAcNo() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            String result = smsscbBO.insertGwTdLuck(tdLuck);
            LOGGER.info("insertGwTdLuck --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param tdLuck
     * @throws RemoteException
     */
    @Override
    public void updateGwTdLuck(GwTdLucky tdLuck) throws RemoteException {
        try {
            LOGGER.info("updateGwTdLuck --> [IN] --> GwTdLucky_custAcNo = [" + tdLuck.getCustAcNo() + "]");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            smsscbBO.updateGwTdLuck(tdLuck);
            LOGGER.info("updateGwTdLuck --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @throws Exception
     */
    @Override
    public void HQ_UPDATE_HQ_ONLINE_201(String ID) throws Exception {
        try {
            LOGGER.info("HQ_UPDATE_HQ_ONLINE_201 --> [IN] --> ID = [" + ID + "]");
            HQBO BO = new HQBO();
            BO.UPDATE_HQ_ONLINE_201(ID);
            LOGGER.info("HQ_UPDATE_HQ_ONLINE_201 --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param SO_HS
     * @param MA_DV
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public List<HQ_DKNNT> HQ_searchDKUQ(String SO_HS, String MA_DV, String status) throws Exception {
        try {
            LOGGER.info("HQ_searchDKUQ --> [IN] --> SO_HS = [" + SO_HS + "], MA_DV = [" + MA_DV + "], status= [" + status + "]");
            HQBO BO = new HQBO();
            List<HQ_DKNNT> result = BO.searchDKUQ(SO_HS, MA_DV, status);
            LOGGER.info("HQ_searchDKUQ --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public String[] HQ_INSERT_HQ_NOPTIEN_HQ_GNT_201(HQ_NOPTIEN_HQ_GNT obj) throws Exception {
        try {
            LOGGER.info("HQ_INSERT_HQ_NOPTIEN_HQ_GNT_201 --> [IN] --> HQ_NOPTIEN_HQ_GNT_IDMASTER = [" + obj.getIDMASTER() + "]");
            HQBO BO = new HQBO();
            String[] result = BO.INSERT_HQ_NOPTIEN_HQ_GNT_201(obj);
            LOGGER.info("HQ_INSERT_HQ_NOPTIEN_HQ_GNT_201 --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }

    }

    /**
     *
     * @param branch
     * @param number
     * @return
     * @throws RemoteException
     */
    @Override
    public List<VwSmsThuphi> getThuPhiKhcnTheoBranch(String branch, int number) throws RemoteException {
        try {
            LOGGER.info("getThuPhiKhcnTheoBranch --> [IN] --> branch = [" + branch + "], number = [" + number + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<VwSmsThuphi> result = BO.getThuPhiKhcnTheoBranch(branch, number);
            LOGGER.info("getThuPhiKhcnTheoBranch --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<VwSmsThuphi> getThuPhiKhcn(String branch, int number) throws RemoteException {
        try {
            LOGGER.info("getThuPhiKhcn --> [IN] --> number = [" + number + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<VwSmsThuphi> result = BO.getThuPhiKhcn(branch, number);
            LOGGER.info("getThuPhiKhcn --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param branch
     * @param number
     * @return
     * @throws RemoteException
     */
    @Override
    public int isNoPhi(String custNo) throws RemoteException {
        try {
            LOGGER.info("isNoPhi --> [IN] --> custNo = [" + custNo + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.isNoPhi(custNo);
            LOGGER.info("isNoPhi --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @return @throws RemoteException
     */
    @Override
    public List<VwSmsThuphi> getThuPhiKhdn() throws RemoteException {
        try {
            LOGGER.info("getThuPhiKhdn --> [IN] --> input is empty");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<VwSmsThuphi> result = BO.getThuPhiKhdn();
            LOGGER.info("getThuPhiKhdn --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    @Override
    public int IBT_checkCounter(String ID) throws RemoteException {
        try {
            LOGGER.info("IBT_checkCounter --> [IN] --> ID = [" + ID + "]");
            SmartLinkBO BO = new SmartLinkBO();
            int result = BO.checkCounter(ID);
            LOGGER.info("IBT_checkCounter --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param idPartner
     * @return
     * @throws RemoteException
     */
    @Override
    public List<Pbl_partnercode> getPblPartnerCode(String idPartner) throws RemoteException {
        try {
            LOGGER.info("getPblPartnerCode --> [IN] --> idPartner = [" + idPartner + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<Pbl_partnercode> result = BO.getPblPartnerCode(idPartner);
            LOGGER.info("getPblPartnerCode --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param cif
     * @param number
     * @return
     * @throws RemoteException
     */
    @Override
    public List<VwSmsThuphi> getUnlockPhi(String cif, int number) throws RemoteException {
        try {
            LOGGER.info("getUnlockPhi --> [IN] --> cif = [" + cif + "], number = [" + number + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<VwSmsThuphi> result = BO.getUnlockPhi(cif, number);
            LOGGER.info("getUnlockPhi --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public GwEmailTd getSTKNeedToResend(String id) throws RemoteException {
        try {
            LOGGER.info("getSTKNeedToResend --> [IN] --> id = [" + id + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            GwEmailTd result = BO.getSTKNeedToResend(id);
            LOGGER.info("getSTKNeedToResend --> [OUT] --> result_customerNo = [" + (result == null ? null : result.getCustomerNo()) + "]");
            return result;
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param pMd5User
     * @return
     * @throws Exception
     */
    @Override
    public MBUser getUserMBInfo(String pMd5User) throws Exception {
        try {
            MB_ChangeUserBO BO = new MB_ChangeUserBO();
            return BO.getUserMBInfo(pMd5User);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList checkMBUser(String username) throws Exception {
        try {
            MB_ChangeUserBO BO = new MB_ChangeUserBO();
            return BO.checkMBUser(username);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param md5User
     * @return
     * @throws Exception
     */
    @Override
    public int updateChangeUser(String md5User) throws Exception {
        try {
            MB_ChangeUserBO BO = new MB_ChangeUserBO();
            return BO.updateChangeUser(md5User);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param userName
     * @param cif
     * @return
     * @throws RemoteException
     */
    @Override
    public IsExistUserEBRes isExistUserBanking(String userName, String cif) throws RemoteException {
        try {
            LOGGER.info("isExistUserBanking --> [IN] --> userName = [" + userName + "]");
            BdobdxBO bdobdxBO = new BdobdxBO();
            IsExistUserEBRes result = bdobdxBO.isExistUserBanking(userName, cif);
            LOGGER.info("isExistUserBanking --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param key
     * @param value
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean setConnectionManager(String key, boolean value) throws RemoteException {
        try {
            LOGGER.info("setConnectionManager --> [IN] --> key = [" + key + "], value = [" + value + "]");
            boolean result = DatabaseController.putValueToConnectionManager(key, value);
            LOGGER.info("setConnectionManager --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param key
     * @return
     * @throws RemoteException
     */
    @Override
    public int getConnectionManager(String key) throws RemoteException {
        try {
            LOGGER.info("getConnectionManager --> [IN] --> key = [" + key + "]");
            int result = DatabaseController.getRealValueFromConnectionManager(key);
            LOGGER.info("getConnectionManager --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param value
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean insertOrgBillPaid(OrgBillPaid value) throws RemoteException {
        try {
            LOGGER.info("insertOrgBillPaid --> [IN] --> req = [" + value.toString() + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            boolean result = BO.insertOrgBillPaid(value);
            LOGGER.info("insertOrgBillPaid --> [OUT] --> value_CustomerCode = [" + value.getCustomerCode() + "], result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param req
     * @param res
     * @param action
     * @return
     * @throws RemoteException
     */
    @Override
    public CardAdjustmentRes cardAdjustment(CardAdjustmentReq req, CardAdjustmentRes res, DBActionEnum action) throws RemoteException {
        try {
            LOGGER.info("cardAdjustment --> [IN] --> req = [" + req + "] res = [" + res + "], action = [" + action + "]");
            OnlinePaymentBO onlinePaymentBO = new OnlinePaymentBO();
            CardAdjustmentRes result = onlinePaymentBO.cardAdjustment(req, res, action);
            LOGGER.info("cardAdjustment --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<SmsOtt> getMessageOtt(int isMulti) throws Exception {
        try {
            SmsOttBO BO = new SmsOttBO();
            return BO.getMessageOtt(isMulti);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int updateSendOtt(int id, int pstatus, String code, String desc, int nultiid) throws Exception {
        try {
            SmsOttBO BO = new SmsOttBO();
            return BO.updateSendOtt(id, pstatus, code, desc, nultiid);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<PayOnlineCollate> ONL_AnswerCollateData(String pPartnerID, String pTransdate) throws Exception {
        try {
            LOGGER.info("AnswerCollateData --> [IN] --> pPartnerID = [" + pPartnerID + "], pTransdate = [" + pTransdate + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<PayOnlineCollate> result = BO.ONL_AnswerCollateData(pPartnerID, pTransdate);
            LOGGER.info("AnswerCollateData --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public void ONL_ReceiveCollateData(PayOnlineCollate collate) throws RemoteException {
        try {
            LOGGER.info("ReceiveCollateData --> [IN] --> collate_Cardnumber = [" + collate.getCardnumber() + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.ONL_ReceiveCollateData(collate);
            LOGGER.info("ReceiveCollateData --> [OUT] --> does not return");
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    public int VISAQR(MVISAQRRQ req, MasterCardQrActionEnum action) throws RemoteException {
        try {
            LOGGER.info("VISAQR --> [IN] --> req = [" + req + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            int result = BO.VISAQR(req, action);
            LOGGER.info("VISAQR --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public String PROCESS_SMS_ALERT(SmsCustRegisInfo obj) throws Exception {
        try {
            LOGGER.info("PROCESS_SMS_ALERT --> [IN] --> req = [" + obj + "]");
            SmsOttBO BO = new SmsOttBO();
            String result = BO.PROCESS_SMS_ALERT(obj);
            LOGGER.info("PROCESS_SMS_ALERT --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<SmsCustRegisInfo> getListRegisterSMS(String CustNo) throws Exception {
        try {
            SmsOttBO BO = new SmsOttBO();
            return BO.getListRegisterSMS(CustNo);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public void IBT_UPDATE_STATUS_TRANSFER(String pTrace,
            String pStatus,
            String pRefcore,
            String pRefcoreRefund,
            String pREF_NO_F37,
            String pSETT_DATE_F15,
            String pTypeTransfer,
            String pResponseCode,
            String pAuthorizationcode,
            String pTransREFNOF63,
            String pF5) throws Exception {
        try {
            NAPASBO BO = new NAPASBO();
            BO.UPDATE_STATUS_TRANSFER(pTrace, pStatus, pRefcore, pRefcoreRefund, pREF_NO_F37, pSETT_DATE_F15, pTypeTransfer, pResponseCode, pAuthorizationcode, pTransREFNOF63, pF5);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public CustomerInfoRsDto ONL_checkCustInfo(OnlinePCustomerInfoDto customerInfo) throws RemoteException {
        try {

            OnlinePaymentBO BO = new OnlinePaymentBO();
            CustomerInfoRsDto result = BO.checkCustInfo(customerInfo);
            Logger.getLogger("checkCustInfo --> [OUT] --> <RESPONSE><RESPONSECODE>[" + result.getRESPONSECODE() + "]</RESPONSECODE><BIRTHDAY>[" + result.getISBIRTHDAY() + "]</BIRTHDAY><CARDNAME>[" + result.getISCARDNAME() + "]</CARDNAME><ISCOUNTRY>[" + result.getISCOUNTRY() + "]</ISCOUNTRY><ISIDNUMBER>[" + result.getISIDNUMBER() + "]</ISIDNUMBER><ISMOBILENO>[" + result.getISMOBILENO() + "]</ISMOBILENO><PROFILEID>[" + result.getPROFILEID() + "]</PROFILEID><PROVIDERID>[" + result.getPROVIDERID() + "]</PROVIDERID></RESPONSE>");

            return result;
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public Boolean PBL_insertPBLBillPaidCusDtls(List<PBLBillPaidCustomerDtlForJobDto> billPaidCustDtls) throws Exception {

        try {

            LOGGER.info("Begin PBL_insertPBLBillPaidCusDtls method");
            PBL_BillPaidBO BO = new PBL_BillPaidBO();
            Boolean res = BO.insertPBLBillPaidCusDtls(billPaidCustDtls);
            LOGGER.info("End  PBL_insertPBLBillPaidCusDtl method");

            return res;

        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<PBLBillPaidCusDto> PBL_getAllPBLBillPaidCus(List<String> partnerIds, List<String> providerIds, List<String> servicetypeIds) throws Exception {

        try {
            PBL_BillPaidBO BO = new PBL_BillPaidBO();
            String msgBegin = String.format("Begin getAllPBLBillPaidCus method partnerIds: {0}, providerIds: {1}, servicetypeIds: {2}", partnerIds, providerIds, servicetypeIds);

            LOGGER.info(msgBegin);
            List<PBLBillPaidCusDto> rs = new ArrayList<>();
            rs = BO.PBL_getAllPBLBillPaidCus(partnerIds, providerIds, servicetypeIds);
            LOGGER.info("End getAllPBLBillPaidCus method");
            return rs;

        } catch (Exception ex) {

            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public void updateIBTResponse(String pTrace, String pStatus,
            String pResponseCode, String pRefcoreRefund, String pSett_date_F15) throws Exception {
        try {
            SmartLinkBO BO = new SmartLinkBO();
            BO.updateIBTResponse(pTrace, pStatus, pResponseCode, pRefcoreRefund, pSett_date_F15);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     * Get Parameter For BillPaidWS
     *
     * @return List<PBLBillPaidCusDto>
     * @throws Exception
     */
    @Override
    public List<BillPaidCustGrpDto> PBL_getParameterForBillPaidWS() throws Exception {

        try {
            PBL_BillPaidBO BO = new PBL_BillPaidBO();

            LOGGER.info("Begin PBL_getParameterForBillPaidWS method ");
            List<BillPaidCustGrpDto> rs = new ArrayList<>();
            rs = BO.PBL_getParameterForBillPaidWS();
            LOGGER.info("End PBL_getParameterForBillPaidWS method");

            return rs;
        } catch (Exception ex) {

            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public PmtInfoV11ResDto getPMT_INFOV11(String param) throws Exception {

        try {
            CWDwhBO BO = new CWDwhBO();

            LOGGER.info("queryContactCenterInfo --> [IN] --> function = [getPMT_INFOV11], param = [" + param + "]");
            PmtInfoV11ResDto result = new PmtInfoV11ResDto();
            result = BO.getPMT_INFOV11(param);
            LOGGER.info("queryContactCenterInfo --> [OUT] --> result = [" + result + "]");

            return result;
        } catch (Exception ex) {

            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public Boolean isExistPartner(String accountNo) throws Exception {

        try {

            LOGGER.info("Begin isExistPartner method");
            CommonBO BO = new CommonBO();
            Boolean res = BO.isExistPartner(accountNo);
            LOGGER.info("End  isExistPartner method");

            return res;

        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public String getSeqRefNo(String type) throws Exception {
        try {

            LOGGER.info("Begin getSeqRefNoIBT method");
            SmartLinkBO BO = new SmartLinkBO();
            return BO.getSeqRefNo(type);

        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    public int InsertSMLLOGXref(String pTYPETRANSFER, String pFROMNUMBER, String pPROCESSINGCODE,
            BigDecimal pTRANSAMOUNT, String pTRANSDATE,
            String pAUDITNUMBER, String pMERCHANTTYPE,
            String pACQUIRINGCODE, String pAUTHORIZATIONCODE, String pRESPONSECODE,
            String pTERMID, String pCARDACCEPTTOR, String pDESTNUMBER,
            String pNARRATION, String pBENID, String pTYPEFUNCTION, String pStatus,
            String pRefCore, String pCustNo, String RefCORE_REFUND, String pREF_NO_F37, String pSETT_DATE_F15, String Xref) throws Exception {
        try {
            LOGGER.info("InsertSMLLOG --> [IN] --> pTYPETRANSFER = [" + pTYPETRANSFER
                    + "], pFROMNUMBER = [" + pFROMNUMBER + "], pPROCESSINGCODE = [" + pPROCESSINGCODE
                    + "], pTRANSAMOUNT = [" + pTRANSAMOUNT + "], pTRANSDATE = [" + pTRANSDATE
                    + "], pAUDITNUMBER = [" + pAUDITNUMBER + "], pMERCHANTTYPE = [" + pMERCHANTTYPE
                    + "], pACQUIRINGCODE = [" + pACQUIRINGCODE + "], pAUTHORIZATIONCODE = ["
                    + pAUTHORIZATIONCODE + "], pRESPONSECODE = [" + pRESPONSECODE + "], pTERMID = ["
                    + pTERMID + "], pCARDACCEPTTOR = [" + pCARDACCEPTTOR + "], pDESTNUMBER = ["
                    + pDESTNUMBER + "], pNARRATION = [" + pNARRATION + "], pBENID = [" + pBENID
                    + "], pTYPEFUNCTION = [" + pTYPEFUNCTION + "], pStatus = [" + pStatus
                    + "], pRefCore = [" + pRefCore + "], pCustNo = [" + pCustNo
                    + "], RefCORE_REFUND = [" + RefCORE_REFUND + "], pREF_NO_F37 = [" + pREF_NO_F37
                    + "], pSETT_DATE_F15 = [" + pSETT_DATE_F15 + "]");
            SmartLinkBO BO = new SmartLinkBO();
            int result = BO.InsertIBTLOGXref(pTYPETRANSFER, pFROMNUMBER, pPROCESSINGCODE, pTRANSAMOUNT,
                    pTRANSDATE, pAUDITNUMBER, pMERCHANTTYPE, pACQUIRINGCODE,
                    pAUTHORIZATIONCODE, pRESPONSECODE, pTERMID, pCARDACCEPTTOR,
                    pDESTNUMBER, pNARRATION, pBENID, pTYPEFUNCTION,
                    pStatus, pRefCore, pCustNo, RefCORE_REFUND, pREF_NO_F37, pSETT_DATE_F15, Xref);
            LOGGER.info("InsertSMLLOG --> [OUT] -->" + result);
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            // throw new RemoteException(ex.getMessage(), ex);
        }
        return 0;
    }

    @Override
    public InsuranceCustInfoDto getCustInfoByCIFID(String cif) throws Exception {

        try {
            FCCCoreBO BO = new FCCCoreBO();
            LOGGER.info("Begin getCustInfoByCIFID method ");
            InsuranceCustInfoDto rs = BO.getCustInfoByCIFID(cif);
            LOGGER.info("End getCustInfoByCIFID method");

            return rs;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public void updateIBTResponse(String pTrace, String pStatus,
            String pResponseCode, String pRefcoreRefund, String pSett_date_F15, String ID) throws Exception {
        try {
            SmartLinkBO BO = new SmartLinkBO();
            BO.updateIBTResponse(pTrace, pStatus, pResponseCode, pRefcoreRefund, pSett_date_F15, ID);
        } catch (Exception ex) {
            // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int updateIBT(String AuditNumber, String Status, String RefCORE, String typeTransfer, String ID, String xref) throws Exception {
        try {
            LOGGER.info("updateIBT --> [IN] --> AuditNumber = [" + AuditNumber + "], Status = [" + Status
                    + "], RefCORE = [" + RefCORE + "], typeTransfer = [" + typeTransfer + "], ID = ["
                    + ID + "], xref = [" + xref + "]");
            SmartLinkBO BO = new SmartLinkBO();
            int result = BO.updateIBT(AuditNumber, Status, RefCORE, typeTransfer, ID, xref);
            LOGGER.info("updateIBT --> [OUT] --> result = [" + result + "], AuditNumber = [" + AuditNumber + "], RefCORE = [" + RefCORE + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return 0;
    }

    @Override
    public int checkPartnerPregolive(String partnerid) throws Exception {
        try {
            SIBO BO = new SIBO();
            return BO.checkPartnerPregolive(partnerid);
        } catch (Exception ex) {
            // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int checkAccountPregolive(String partnerid, String accountno) throws Exception {
        try {
            SIBO BO = new SIBO();
            return BO.checkAccountPregolive(partnerid, accountno);
        } catch (Exception ex) {
            // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int updateResultToSCB(String AuditNumber, String Status, String RefCORE, String ID, String responsecode) throws Exception {
        try {
            SmartLinkBO BO = new SmartLinkBO();
            return BO.updateResultToSCB(AuditNumber, Status, RefCORE, ID, responsecode);
        } catch (Exception ex) {
            // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public void UpdateRevertTransfer(String pTrace,
            String pRefcore,
            String pRefcoreRefund, String ID) throws RemoteException {
        try {
            NAPASBO BO = new NAPASBO();
            BO.UpdateRevertTransfer(pTrace, pRefcore, pRefcoreRefund, ID);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int insertIBTRequestLog(String trace) throws Exception {
        try {
            NAPASBO BO = new NAPASBO();
            return BO.insertIBTRequestLog(trace);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public void UPDATEIBTRANSFER(String pTrace,
            String pStatus,
            String pREF_NO_F37,
            String pSETT_DATE_F15,
            String pResponseCode,
            String pAuthorizationcode,
            String pTransREFNOF63,
            String pF5) throws Exception {
        try {
            NAPASBO BO = new NAPASBO();
            BO.UPDATEIBTRANSFER(pTrace, pStatus, pREF_NO_F37, pSETT_DATE_F15, pResponseCode, pAuthorizationcode, pTransREFNOF63, pF5);
        } catch (Exception ex) {
            // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public void updateSendIBT_response(String ID, String response) throws Exception {
        try {
            NAPASBO BO = new NAPASBO();
            BO.updateSendIBT_response(ID, response);
        } catch (Exception ex) {
            // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int IBT_updateCannotSend(String trace) throws Exception {
        try {
            NAPASBO BO = new NAPASBO();
            return BO.updateCannotSend(trace);
        } catch (Exception ex) {
            // LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public TransferMoneyTransactionInfo checkStatusTransferMoney(GetStatusOfTransferMoneyReq req) throws Exception {
        try {
            SIBO BO = new SIBO();
            return BO.checkStatusTransferMoney(req);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<FeedbackInfo> getListFeedback(String isChecked) throws Exception {
        try {
            BdobdxBO BO = new BdobdxBO();
            return BO.getListFeedback(isChecked);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<RegisterInfoDetail> getListRegisterDetail(String isChecked) throws Exception {
        try {
            SmsSCBBO BO = new SmsSCBBO();
            return BO.getListRegisterDetail(isChecked);
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int updateListReceiveFeedback(String isChecked, String id, String type, String idChecker, String idChannelChecker) throws Exception {
        try {
            switch (type) {
                case "1":
                    /*dong lai do DB standby ko the update cho gateway*/
 /* 
                    SmsSCBBO smsscbBO = new SmsSCBBO();
                    return smsscbBO.updateRegisterId(isChecked, id, idChecker, idChannelChecker);
                     */
                    return 1;
                case "2":
                    BdobdxBO bdobdxBO = new BdobdxBO();
                    return bdobdxBO.updateFeedbackId(isChecked, id, idChecker, idChannelChecker);
                default:
                    break;
            }
            return 0;
        } catch (Exception ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public boolean insertQRPayment(QRPAYMENT qrPayment) throws Exception {
        try {
            QRPaymentBO BO = new QRPaymentBO();
            return BO.insertQRPayment(qrPayment);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return false;
    }

    @Override
    public boolean updateQRPayment(QRPAYMENT qrPayment) throws Exception {
        try {
            QRPaymentBO BO = new QRPaymentBO();
            return BO.updateQRPayment(qrPayment);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return false;
        }
    }

    @Override
    public Object[] QR_CHECK_PCODE(QRPAYMENT qrPayment) throws Exception {
        try {
            QRPaymentBO BO = new QRPaymentBO();
            return BO.QR_CHECK_PCODE(qrPayment);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    @Override
    public CustomerInfo getCustInfoByCif(String cifno) throws Exception {
        try {
            FCCCoreBO BO = new FCCCoreBO();
            return BO.getCustInfoByCif(cifno);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    @Override
    public long insertHealthInsBHBL(BHBLHealthCareIns payins, BHBLHealthCareInsRq.Questions ques) throws RemoteException {
        try {
            LOGGER.info("Begin insertHealthInsBHBL");
            BHBLInsurancePaymentBO smsscbBO = new BHBLInsurancePaymentBO();
            long result = smsscbBO.insertHealthInsBHBL(payins, ques);
            LOGGER.info("End insertHealthInsBHBL --> [OUT] --> ID = [" + result + "]");

            return result;
        } catch (Exception ex) {
            LOGGER.error("insertHealthInsBHBL failed " + ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public void updateHealthInsBHBL(BHBLHealthCareIns payins) throws RemoteException {
        try {
            LOGGER.info("Begin updateHealthInsBHBL --> [IN] --> ID = " + payins.getId());
            BHBLInsurancePaymentBO healCareInsBO = new BHBLInsurancePaymentBO();
            long res = healCareInsBO.updateHealthInsBHBL(payins);
            if (res != -1) {
                LOGGER.info("End updateHealthInsBHBL --> SUCCEEDED");
            }

        } catch (Exception ex) {
            LOGGER.error("updateHealthInsBHBL failed");
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<BHBLPackageCostResp> BHBL_getBLPackageCost(int tuoi, int gioiTinh, String lang) throws RemoteException {
        try {
            List<BHBLPackageCostResp> packageCosts = new ArrayList<>();

            LOGGER.info("Begin BHBL_getBLPackageCost --> [IN] --> dotuoi, goitinh, lang: " + tuoi + " - " + gioiTinh + " - " + lang);
            BHBLInsurancePaymentBO healCareInsBO = new BHBLInsurancePaymentBO();
            packageCosts = healCareInsBO.getBLPackageCost(tuoi, gioiTinh, lang);
            LOGGER.info("End BHBL_getBLPackageCost");

            return packageCosts;
        } catch (Exception ex) {
            LOGGER.error("getBLPackageCost failed " + ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<BHBLMetadataRes> BHBL_getBLCategories(String type) throws RemoteException {
        try {
            List<BHBLMetadataRes> meatDatas = new ArrayList<>();

            LOGGER.info("Begin BHBL_getBLCategories --> [IN] --> type: " + type);
            BHBLInsurancePaymentBO healCareInsBO = new BHBLInsurancePaymentBO();
            meatDatas = healCareInsBO.getBLCategories(type);
            LOGGER.info("End BHBL_getBLCategories");

            return meatDatas;
        } catch (Exception ex) {
            LOGGER.error("BHBL_getBLCategories failed" + ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<BHBLQuestionResp> BHBL_getBLAllQuestions(String lang) throws RemoteException {
        try {
            List<BHBLQuestionResp> questions = new ArrayList<>();

            LOGGER.info("Begin BHBL_getBLAllQuestions --> [IN] -->  lang: " + lang);
            BHBLInsurancePaymentBO healCareInsBO = new BHBLInsurancePaymentBO();
            questions = healCareInsBO.getBLAllQuestions(lang);
            LOGGER.info("End BHBL_getBLAllQuestions");
            return questions;
        } catch (Exception ex) {
            LOGGER.error("BHBL_getBLAllQuestions failed " + ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public String BHBL_updatePaymentStatus(BHBLPaymentContractInfoRq contractInfo) throws RemoteException {
        try {
            LOGGER.info("Begin updateBLPaymentContract --> [IN] --> ID_CONTRACT_PARTNER = " + contractInfo.getInvoiceCode());
            BHBLInsurancePaymentBO healCareInsBO = new BHBLInsurancePaymentBO();
            String res = healCareInsBO.updatePaymentStatus(contractInfo);
            if (!"-1".equals(res)) {
                LOGGER.info("End updateBLPaymentContract --> SUCCEEDED");
            }
            return res;
        } catch (Exception ex) {
            LOGGER.error("updateBLPaymentContract failed");
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<BHBLCstContractCollectedByTKResp> BHBL_collectedCustConTractByTK() throws RemoteException {
        try {
            List<BHBLCstContractCollectedByTKResp> cstContractCollectedTK = new ArrayList<>();

            LOGGER.info("Begin getCusConTractForCollectedByTK");
            BHBLInsurancePaymentBO healCareInsBO = new BHBLInsurancePaymentBO();
            cstContractCollectedTK = healCareInsBO.collectedCustConTractByTK();
            LOGGER.info("End getCusConTractForCollectedByTK");

            return cstContractCollectedTK;
        } catch (Exception ex) {
            LOGGER.error("getCusConTractForCollectedByTK failed " + ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<BHBLCstContractCollectedByTheResp> BHBL_collectedCustConTractByThe() throws RemoteException {
        try {
            List<BHBLCstContractCollectedByTheResp> cstContractCollectedThe = new ArrayList<>();

            LOGGER.info("Begin getCusConTractForCollectedByThe");
            BHBLInsurancePaymentBO healCareInsBO = new BHBLInsurancePaymentBO();
            cstContractCollectedThe = healCareInsBO.collectedCustConTractByThe();
            LOGGER.info("End getCusConTractForCollectedByThe");

            return cstContractCollectedThe;
        } catch (Exception ex) {
            LOGGER.error("getCusConTractForCollectedByThe failed " + ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<BHBLGoiPhiHDResp> BHBL_getGoiPhiHD() throws RemoteException {
        try {
            List<BHBLGoiPhiHDResp> goiphiHDs = new ArrayList<>();

            LOGGER.info("Begin getGoiPhiHD");
            BHBLInsurancePaymentBO healCareInsBO = new BHBLInsurancePaymentBO();
            goiphiHDs = healCareInsBO.getGoiPhiHD();
            LOGGER.info("End getGoiPhiHD");

            return goiphiHDs;
        } catch (Exception ex) {
            LOGGER.error("getGoiPhiHD failed " + ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public List<BHBLHDAndGoiPhiResp> BHBL_getAllHDAndGoiPhi() throws RemoteException {
        try {
            List<BHBLHDAndGoiPhiResp> hdGoiPhis = new ArrayList<>();

            LOGGER.info("Begin getAllHDAndGoiPhi");
            BHBLInsurancePaymentBO healCareInsBO = new BHBLInsurancePaymentBO();
            hdGoiPhis = healCareInsBO.getAllHDAndGoiPhi();
            LOGGER.info("End getAllHDAndGoiPhi");

            return hdGoiPhis;
        } catch (Exception ex) {
            LOGGER.error("getAllHDAndGoiPhi failed " + ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
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
            String destBank) throws Exception {
        try {
            LOGGER.info("transferFCUBSForIBTCounter_247 IN");
            FCCCoreBO bo = new FCCCoreBO();
            return bo.transferFCUBSForIBTCounter_247(pm_product, pm_cr_brn, pm_cr_acc, pm_amt, pm_loaitien, pm_noidung, pm_tennguoinop, pm_diachinguoinop, pm_socmnd, pm_ngaycap, pm_noicap, pm_gdv, pm_ksv, destAccount, destBank);
        } catch (Exception ex) {
            LOGGER.error("transferFCUBSForIBTCounter_247 LOI:" + ex);
            return null;
        }
    }

    public Object[] DESTROY_QR_PAYMENT(String pTRANSID, String pOrderNo, String pPROVIDERID, String pDATETIME) throws Exception {
        LOGGER.info("DESTROY_QR_PAYMENT IN");
        QRPaymentBO BO = new QRPaymentBO();
        return BO.DESTROY_QR_PAYMENT(pTRANSID, pOrderNo, pPROVIDERID, pDATETIME);
    }

    public int UPDATE_DESTROY_QR_PAYMENT(String pRefcore, String pOrderNo, String pPROVIDERID) throws Exception {
        LOGGER.info("UPDATE_DESTROY_QR_PAYMENT IN");
        QRPaymentBO BO = new QRPaymentBO();
        int result = BO.UPDATE_DESTROY_QR_PAYMENT(pRefcore, pOrderNo, pPROVIDERID);
        LOGGER.info("UPDATE_DESTROY_QR_PAYMENT OUT:" + result);
        return result;
    }

    @Override
    public String[] INSERT_SMS_PARTNER(sms_partner_request request) throws Exception {
        try {
            LOGGER.info("INSERT_SMS_PARTNER --> [IN] --> idRequest = [" + request.getRequestid() + "]");
            SmsSCBBO BO = new SmsSCBBO();
            String[] result = BO.INSERT_SMS_PARTNER(request);
            LOGGER.info("INSERT_SMS_PARTNER --> [OUT] --> result[0] = [" + (result == null ? null : result[0]) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param userName
     * @param cif
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<?> isExistUserMBanking(String userName, String cif, String channel) throws RemoteException {
        try {
            LOGGER.info("isExistUserBanking --> [IN] --> userName = [" + userName + "]");
            VninfoBO vninfoBO = new VninfoBO();
            ArrayList result = vninfoBO.isExistUserMBanking(userName, cif, channel);
            LOGGER.info("isExistUserBanking --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList<?> getPass(String userName, String cif) throws RemoteException {
        try {
            LOGGER.info("getPass --> [IN] --> userName = [" + userName + "]");
            VninfoBO vninfoBO = new VninfoBO();
            ArrayList result = vninfoBO.getPass(userName, cif);
            LOGGER.info("isExistUserBanking --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList<?> IBT_searchBlackList(String destnumber, String bankcode, int isapprove) throws RemoteException {
        try {
            LOGGER.info("searchBlackList --> [IN] --> destnumber = [" + destnumber + "]");
            SmartLinkBO BO = new SmartLinkBO();
            ArrayList result = BO.searchBlackList(destnumber, bankcode, isapprove);
            LOGGER.info("searchBlackList --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int IBT_updateBlackList(String id, String userapprove, int isapprove) throws RemoteException {
        try {
            LOGGER.info("updateBlackList --> [IN] --> id = [" + id + "]");
            SmartLinkBO BO = new SmartLinkBO();
            int result = BO.updateBlackList(id, userapprove, isapprove);
            LOGGER.info("updateBlackList --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int insertBlackList(String destnumber, String type, String bankcode, String userid, String branchcode, String benename) throws Exception {
        try {
            LOGGER.info("insertBlackList --> [IN] --> id = [" + destnumber + "]");
            SmartLinkBO BO = new SmartLinkBO();
            int result = BO.insertBlackList(destnumber, type, bankcode, userid, branchcode, benename);
            LOGGER.info("insertBlackList --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList<?> IBT_getInforchBlackList(String id, int isapprove) throws RemoteException {
        try {
            LOGGER.info("getInforBlackList --> [IN] --> id = [" + id + "]");
            SmartLinkBO BO = new SmartLinkBO();
            ArrayList result = BO.getInforBlackList(id, isapprove);
            LOGGER.info("getInforBlackList --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public String checkOverKYC(String custAcc, String brnAcc, BigDecimal amount) throws RemoteException {
        try {
            LOGGER.info("checkOverKYC --> [IN] --> custAcc = [" + custAcc + "]");
            FCCCoreBO BO = new FCCCoreBO();
            String result = BO.checkOverKYC(custAcc, brnAcc, amount);
            LOGGER.info("checkOverKYC --> [OUT] --> Size = [" + (result == null ? null : result) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<CollatedDetail> getListDataCollated(String partnerId, String transDate) throws Exception {
        try {
            LOGGER.info("getListDataCollated --> [IN] --> PARTNERID = [" + partnerId + "], TRANSDATE = [" + transDate + "]");
            SmsBO BO = new SmsBO();
            return BO.getListDataCollated(partnerId, transDate);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public Object[] getPdfFileByMonth(String LOC, String Stmth) throws Exception {
        LOGGER.info("getListDataCollated -->[IN]-->LOC=[" + LOC + "], [Stmth=" + Stmth + "]");
        CWDwhBO BO = new CWDwhBO();
        Object[] result = BO.getPdfFileByMonth(LOC, Stmth);
        LOGGER.info("getListDataCollated -->[OUT]-->result=[" + result[1] + "]");
        return result;
    }

    @Override
    public String checkCifNoCredit(String cif) throws Exception {
        LOGGER.info("checkCifNoCredit -->[IN]-->CIF=[" + cif + "]");
        NAPASBO BO = new NAPASBO();
        String result = BO.checkCifNoCredit(cif);
        LOGGER.info("checkCifNoCredit -->[OUT]-->result=[" + result + "]");
        return result;
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public ArrayList<?> getOnlTranPartnersById(String idPartner) throws RemoteException {
        try {
            LOGGER.info("getOnlTranPartners --> [IN] --> input is empty");
            SmsSCBBO smsscbBO = new SmsSCBBO();
            ArrayList<?> result = smsscbBO.getOnlTranPartnersById(idPartner);
            LOGGER.info("getOnlTranPartners --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }


    /*dang test tren 73.20
    * doi tac: WU
     */
    @Override
    public int insertToSiSenderDetail(SenderInfo senderInfo, Long bankTransId) throws Exception {
        LOGGER.info("insertToSiSenderDetail -->[IN]:BankTransId: [" + bankTransId + "]");
        SIBO BO = new SIBO();
        int idSender = BO.insertToSiSenderDetail(senderInfo, bankTransId);
        LOGGER.info("insertToSiSenderDetail -->[OUT]-->result=[" + idSender + "]");
        return idSender;
    }

    /*dang test tren 73.20
    * doi tac: WU
     */
    @Override
    public List<TransactionDetailByDate> getListTransactionByDate(String providerId, String partnerAccount, String startDateTime, String endDateTime) throws Exception {
        LOGGER.info("getListTransactionByDate -->[IN]:providerId: [" + providerId + "]" + "- partnerAccount: [" + partnerAccount + "]" + "- startDateTime: [" + startDateTime + "]" + "- endDateTime: [" + endDateTime + "]");
        SIBO BO = new SIBO();
        List<TransactionDetailByDate> result = BO.getListTransactionByDate(providerId, partnerAccount, startDateTime, endDateTime);
        LOGGER.info("getListTransactionByDate -->[OUT]-->result=[" + result.size() + "]");
        return result;
    }

    /*dang test tren 73.20
    * doi tac: WU
     */
    @Override
    public String[] insertErrorKycSiTransDetail(String idMaster,
            String txnDetailId, String partnerAccount, String customerName, String customerAccount,
            String bankCode, String branchName, BigDecimal amount, String ccy, String description, String status,
            String typeTransfer, String typeCustAccount, BigDecimal rate, BigDecimal amtExchange, String ccyExchange, String personId,
            String firstName, String lastName, String passNo, String birthDate, String address, String nationality, String cusType) throws Exception {

        LOGGER.info("insertToSiTransDetail --> [IN]");
        SIBO BO = new SIBO();
        String[] bankTransId = BO.insertErrorKycSiTransDetail(idMaster, txnDetailId, partnerAccount, customerName, customerAccount, bankCode, branchName, amount, ccy,
                description, status, typeTransfer, typeCustAccount, rate, amtExchange, ccyExchange, personId, firstName, lastName,
                passNo, birthDate, address, nationality, cusType);
        LOGGER.info("insertToSiTransDetail --> [OUT]");
        return bankTransId;
    }

    @Override
    public ArrayList getUserOdbxDtl(String username) throws RemoteException {
        try {
            LOGGER.info("getUserOdbxDtl --> [IN] --> username = [" + username + "]");
            BdobdxBO BO = new BdobdxBO();
            ArrayList result = BO.getUserOdbxDtl(username);
            LOGGER.info("getUserOdbxDtl --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*WU - TEST CASE 73.20 test xong xoa*/
    @Override
    public String[] insertTestCaseToTransDetail(String idMaster, String txnDetailId, String partnerAccount, String customerName,
            String customerAccount, String bankCode, BigDecimal amount, String ccy, String description, String typeTransfer, String status) throws Exception {
        LOGGER.info("insertTestCaseToTransDetail --> [IN] --> transId = [" + txnDetailId + "]");
        SIBO BO = new SIBO();
        String[] result = BO.insertTestCaseToTransDetail(idMaster, txnDetailId, partnerAccount, customerName, customerAccount, bankCode, amount, ccy,
                description, typeTransfer, status);
        LOGGER.info("insertTestCaseToTransDetail --> [OUT] --> does not return");
        return result;
    }

    @Override
    public ArrayList getUserDeviceOdbxInfo(String soCif) throws RemoteException {
        try {
            LOGGER.info("getUserDeviceOdbxInfo --> [IN] --> socif = [" + soCif + "]");
            BdobdxBO BO = new BdobdxBO();
            ArrayList result = BO.getUserDeviceOdbxInfo(soCif);
            LOGGER.info("getUserDeviceOdbxInfo --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int updateUserAdditionalOdbx(String userName, String deviceAllowed) throws RemoteException {
        try {
            LOGGER.info("updateUserAdditionalOdbx --> [IN] --> id = [" + userName + "], deviceAllowed = [" + deviceAllowed + "]");
            BdobdxBO BO = new BdobdxBO();
            int result = BO.updateUserAdditionalOdbx(userName, deviceAllowed);
            LOGGER.info("updateUserAdditionalOdbx --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int insertUserOtherDeviceOdbx(UserOtherDeviceOdbxDto userDeviceOdbx) throws RemoteException {
        try {
            LOGGER.info("insertUserOtherDeviceOdbx --> [IN] --> username = [" + userDeviceOdbx.getUserName() + "]");
            SmsSCBBO BO = new SmsSCBBO();
            int result = BO.insertUserOtherDeviceOdbx(userDeviceOdbx);
            LOGGER.info("insertUserOtherDeviceOdbx --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int updateGrantUserOtherDevice(UserOtherDeviceOdbxDto userDeviceOdbx) throws RemoteException {
        try {
            LOGGER.info("updateUserOtherDeviceOdbx --> [IN] --> username = [" + userDeviceOdbx.getUserName() + "]");
            SmsSCBBO BO = new SmsSCBBO();
            int result = BO.updateGrantUserOtherDevice(userDeviceOdbx);
            LOGGER.info("updateUserOtherDeviceOdbx --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList getUserOtherDeviceOdbxById(String id) throws RemoteException {
        try {
            LOGGER.info("getUserOtherDeviceOdbxById --> [IN] --> id = [" + id + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList result = BO.getUserOtherDeviceOdbxById(id);
            LOGGER.info("getUserOtherDeviceOdbxById --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList checkOnOffUserDeviceOdbxApproved(String userName, String soCif, String approved) throws RemoteException {
        try {
            LOGGER.info("checkOnOffUserDeviceOdbxApproved --> [IN] --> userName = [" + userName + "], soCif = [" + soCif + "] , approved = [" + approved + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList result = BO.checkOnOffUserDeviceOdbxApproved(userName, soCif, approved);
            LOGGER.info("checkOnOffUserDeviceOdbxApproved --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList GetPaymentCardRegisteredByCif(String soCif) throws RemoteException {
        try {
            LOGGER.info("GetPaymentCardRegisteredByCif --> [IN] --> SoCif = [" + soCif + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList result = BO.GetPaymentCardRegisteredByCif(soCif);
            LOGGER.info("GetPaymentCardRegisteredByCif --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int insertPaymentCardTracking(PaymentByCardTracking paymentByCardtracking) throws RemoteException {
        try {
            LOGGER.info("insertPaymentCardTracking --> [IN] --> PaymentId = [" + paymentByCardtracking.getPaymentId() + "]");
            SmsSCBBO BO = new SmsSCBBO();
            int result = BO.insertPaymentCardTracking(paymentByCardtracking);
            LOGGER.info("insertPaymentCardTracking --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int updatePaymentCardTracking(String paymentTracking, String userChecker, String status) throws RemoteException {
        try {
            LOGGER.info("updatePaymentCardTracking --> [IN] --> payementid = [" + paymentTracking + "]");
            SmsSCBBO BO = new SmsSCBBO();
            int result = BO.updatePaymentCardTracking(paymentTracking, userChecker, status);
            LOGGER.info("updatePaymentCardTracking --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int updatePaymentByCardRegister(String payment, String status) throws RemoteException {
        try {
            LOGGER.info("updatePaymentByCardRegister --> [IN] --> pamentid = [" + payment + "]");
            SmsSCBBO BO = new SmsSCBBO();
            int result = BO.updatePaymentByCardRegister(payment, status);
            LOGGER.info("updatePaymentByCardRegister --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList checkPaymentCardApproved(String id, String approved) throws RemoteException {
        try {
            LOGGER.info("checkPaymentCardApproved --> [IN] --> id = [" + id + "], approved = [" + approved + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList result = BO.checkPaymentCardApproved(id, approved);
            LOGGER.info("checkPaymentCardApproved --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList getPaymentCardCancelById(String id) throws RemoteException {
        try {
            LOGGER.info("getPaymentCardRegisterdById --> [IN] --> id = [" + id + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList result = BO.getPaymentCardCancelById(id);
            LOGGER.info("getPaymentCardRegisterdById --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public String GetVCardDetail(String paneCd) throws Exception {
        try {
            LOGGER.info("GetVCardDetail --> [IN] --> paneCd = [" + paneCd + "]");
            CWDwhBO cwdwhBO = new CWDwhBO();
            String result = cwdwhBO.GetVCardDetail(paneCd);
            LOGGER.info("GetVCardDetail --> [OUT] --> result = [" + (result == null ? null : result) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*Baotbq - Huy chung tu*/
    @Override
    public ArrayList NTDT_PAYMENT(String refcore) throws RemoteException {
        try {
            LOGGER.info("NTDTPaymentByRefCore --> [IN] --> refcore = [" + refcore + "]");
            NTDTBO BO = new NTDTBO();
            ArrayList result = BO.NTDT_PERSONAL_PAYMENT(refcore);
            LOGGER.info("NTDTPaymentByRefCore --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int insertNtdtPaymentExtend(NtdtPaymentExtend ntdtPaymentExtend) throws Exception {
        try {
            LOGGER.info("insertNtdtPaymentExtend --> [IN] --> NtdtPaymentId = [" + ntdtPaymentExtend.getNtdtPaymentId() + "]");
            SmsSCBBO BO = new SmsSCBBO();
            int result = BO.insertNtdtPaymentExtend(ntdtPaymentExtend);
            LOGGER.info("insertNtdtPaymentExtend --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int updateNtdtPaymentExtend(String paymentExtend, String userChecker, String approved) throws RemoteException {
        try {
            LOGGER.info("updateNtdtPaymentExtend --> [IN] --> ntdtPayementId = [" + paymentExtend + "]");
            SmsSCBBO BO = new SmsSCBBO();
            int result = BO.updateNtdtPaymentExtend(paymentExtend, userChecker, approved);
            LOGGER.info("updateNtdtPaymentExtend --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int checkNtdtPaymentApproved(String id, String approved) throws RemoteException {
        try {
            LOGGER.info("checkNtdtPaymentApproved --> [IN] --> id = [" + id + "], approved = [" + approved + "]");
            SmsSCBBO BO = new SmsSCBBO();
            int result = BO.checkNtdtPaymentApproved(id, approved);
            LOGGER.info("checkNtdtPaymentApproved --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList getNtdtPaymentExtendById(String id) throws RemoteException {
        try {
            LOGGER.info("getNtdtPaymentExtendById --> [IN] --> id = [" + id + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList result = BO.getNtdtPaymentExtendById(id);
            LOGGER.info("getNtdtPaymentExtendById --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /*Baotbq - KieuHoi - 12/10/2022*/
    @Override
    public ArrayList getInforRemittanceById(String magd) throws RemoteException {
        try {
            LOGGER.info("getInforRemittance --> [IN] --> id = [" + magd + "]");
            SIBO BO = new SIBO();
            ArrayList result = BO.getInforRemittance(magd);
            LOGGER.info("getInforRemittance --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<TransfersRemittance> getInforRemittanceByDate(String tungay, String denngay) throws RemoteException {
        try {
            LOGGER.info("getInforRemittance --> [IN] --> tungay = [" + tungay + "] + denngay = [" + denngay + "]");
            SIBO BO = new SIBO();
            List<TransfersRemittance> result = BO.getInforRemittanceByDate(tungay, denngay);
            LOGGER.info("getInforRemittance --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int insertTransfersRemittanceExtend(SiTrffromsiExtend siTrffromsiExtend) throws Exception {
        try {
            LOGGER.info("insertTransfersRemittanceExtend --> [IN] --> NtdtPaymentId = [" + siTrffromsiExtend.getSiId() + "]");
            SIBO BO = new SIBO();
            int result = BO.insertTransfersRemittanceExtend(siTrffromsiExtend);
            LOGGER.info("insertTransfersRemittanceExtend --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int updateTransfersRemittanceExtend(String siIdExtend, String userChecker, String approved) throws RemoteException {
        try {
            LOGGER.info("updateTransfersRemittanceExtend --> [IN] --> SiId = [" + siIdExtend + "]");
            SIBO BO = new SIBO();
            int result = BO.updateTransfersRemittanceExtend(siIdExtend, userChecker, approved);
            LOGGER.info("updateTransfersRemittanceExtend --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public int checkTransfersRemittanceApproved(String id, String approved) throws RemoteException {
        try {
            LOGGER.info("checkNtdtPaymentApproved --> [IN] --> id = [" + id + "], approved = [" + approved + "]");
            SIBO BO = new SIBO();
            int result = BO.checkTransfersRemittanceApproved(id, approved);
            LOGGER.info("checkNtdtPaymentApproved --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList getSiTrffromSiById(String id) throws RemoteException {
        try {
            LOGGER.info("getSiTrffromSiById --> [IN] --> id = [" + id + "]");
            SIBO BO = new SIBO();
            ArrayList result = BO.getSiTrffromSiById(id);
            LOGGER.info("getSiTrffromSiById --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<TransferMoney247EbankReq> getTransfer247Ebank(String id) throws SQLException, Exception {
        try {
            LOGGER.info("getTransfer247Ebank ---> [IN] --->ID = [" + id + "]");
            SIBO BO = new SIBO();
            List<TransferMoney247EbankReq> result = BO.getTransfer247Ebank(id);
            LOGGER.info("getTransfer247Ebank --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public String getCifFromAccountNo(String accountNo) throws RemoteException {
        try {
            LOGGER.info("getCifFromAccountNo --> [IN] --> accountNo = [" + accountNo + "]");
            SmsSCBBO smsSCBBO = new SmsSCBBO();
            String cif = smsSCBBO.getCifFromAccountNo(accountNo);
            LOGGER.info("getCifFromAccountNo --> [OUT] --> cif = [" + cif + "]");
            return cif;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList<?> CollatedKieuHoiByEKYC(String partnerId, String status, String dateCollated) throws RemoteException {
        try {
            LOGGER.info("CollatedKieuHoiByEKYC --> [IN] --> partnerId = [" + partnerId + "], status = [" + status + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList result = BO.CollatedKieuHoiByEKYC(partnerId, status, dateCollated);
            LOGGER.info("CollatedKieuHoiByEKYC --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList CollatedKieuHoiByDate(String partnerId, String fromDate, String toDate) throws RemoteException {
        try {
            LOGGER.info("CollatedKieuHoiByDate --> [IN] --> partnerId = [" + partnerId + "], fromDate = [" + fromDate + "] , toDate = [" + toDate + "]");
            SmsSCBBO BO = new SmsSCBBO();
            ArrayList result = BO.CollatedKieuHoiByDate(partnerId, fromDate, toDate);
            LOGGER.info("CollatedKieuHoiByDate --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<VNPTMoneyCollate> AnswerCollateVNPT(String pPartnerID, String pTransdate) throws Exception {
        try {
            LOGGER.info("AnswerCollateVNPT --> [IN] --> pPartnerID = [" + pPartnerID + "], pTransdate = [" + pTransdate + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            List<VNPTMoneyCollate> result = BO.AnswerCollateDataVNPT(pPartnerID, pTransdate);
            LOGGER.info("AnswerCollateVNPT --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public void ReceiveCollateVNPT(VNPTMoneyCollate collate) throws RemoteException {
        try {
            LOGGER.info("ReceiveCollateVNPT --> [IN] --> collate_Cardnumber = [" + collate.getBankTransid() + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            BO.ReceiveCollateDataVNPT(collate);
            LOGGER.info("ReceiveCollateVNPT --> [OUT] --> " + collate.getBankTransid());
        } catch (Exception ex1) {
            LOGGER.error(ex1);
            throw new RemoteException(ex1.getMessage(), ex1);
        }
    }

    //Baotbq 23/12/2022 - Tra cứu hủy chứng từ
    @Override
    public ArrayList search_NTDT_PAYMENT_EXTEND(String refcore) throws RemoteException {
        try {
            LOGGER.info("searchNtdtPaymentExtendByRefCore --> [IN] --> refcore = [" + refcore + "]");
            NTDTBO BO = new NTDTBO();
            ArrayList result = BO.search_NTDT_PAYMENT_EXTEND(refcore);
            LOGGER.info("searchNtdtPaymentExtendByRefCore --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     * INSERTMOKHOATHE
     *
     * @param req
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean INSERTMOKHOATHE(MoKhoaTheReq req) throws RemoteException {
        try {
            LOGGER.info("INSERTMOKHOATHE --> [IN] --> MoTheReq = [" + req + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            boolean result = BO.INSERTMOKHOATHE(req);
            LOGGER.info("INSERTMOKHOATHE --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     * INSERTMOKHOATHEDETAILS
     *
     * @param req
     * @param card
     * @return
     * @throws RemoteException
     */
    @Override
    public String INSERTMOKHOATHEDETAILS(MoKhoaTheReq req, CardInfo card) throws RemoteException {
        try {
            LOGGER.info("INSERTMOKHOATHEDETAILS --> [IN] --> KhoaTheReq = [" + req + "], CardInfo = [" + card + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            String result = BO.INSERTMOKHOATHEDETAILS(req, card);
            LOGGER.info("INSERTMOKHOATHEDETAILS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     * UPDATEMOKHOATHEDETAILS
     *
     * @param refNo
     * @param req
     * @param respCode
     * @param respDesc
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean UPDATEMOKHOATHEDETAILS(String refNo, MoKhoaTheReq req, String respCode, String respDesc) throws RemoteException {
        try {
            LOGGER.info("UPDATEMOKHOATHEDETAILS --> [IN] --> refNo = [" + refNo + "], req = [" + req + "], respCode = [" + respCode + "], respDesc = [" + respDesc + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            boolean result = BO.UPDATEMOKHOATHEDETAILS(refNo, req, respCode, respDesc);
            LOGGER.info("UPDATEMOKHOATHEDETAILS --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    /**
     * GetCardLockedInfo
     *
     * @param req
     * @return
     * @throws RemoteException
     */
    @Override
    public List<CardInfo> getCardLockedInfo(MoKhoaTheReq req) throws RemoteException {
        try {
            LOGGER.info("getCardLockedInfo --> [IN] --> KhoaTheReq = [" + req + "]");
            CWLiveBO CWBO = new CWLiveBO();
            List<CardInfo> result = CWBO.getCardLockedInfo(req);
            LOGGER.info("getCardLockedInfo --> [OUT] --> Size = [" + (result == null ? null : result.size()) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public Object[] SI_INSERTTRANSFERFROMSIDETAILKIEUHOI_WU(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,
            String pCUSTUMERNAME,
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            String pBranchname,
            double pAmount,
            String pCCY,
            String pDescription,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT,
            BigDecimal rate,
            BigDecimal amtExchange,
            String ccyExchange,
            String personId,
            String firstName,
            String lastName,
            String passNo,
            String birthday,
            String address,
            String nationality,
            String custtype
    ) throws RemoteException {
        try {
            LOGGER.info("INSERTTRANSFERFROMSIDETAILKIEUHOI_WU --> [IN] --> pID_MASTER = [" + pID_MASTER + "], pTXNDETAILID = [" + pTXNDETAILID + "], pPARTNERACCOUNT = [" + pPARTNERACCOUNT + "]");
            SIBO BO = new SIBO();
            Object[] result = BO.INSERTTRANSFERFROMSIDETAILKIEUHOI_WU(pID_MASTER, pTXNDETAILID, pPARTNERACCOUNT, pCUSTUMERNAME, pCUSTUMERACCOUNT, pBANKCODE, pBranchname, pAmount, pCCY, pDescription, pTYPETRANSFER, pTYPECUSTACCOUNT, rate, amtExchange, ccyExchange, personId, firstName, lastName, passNo, birthday, address, nationality, custtype);
            LOGGER.info("INSERTTRANSFERFROMSIDETAILKIEUHOI_WU --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public PhongToaTKTTRes CC_PhongToaTKTT(PhongToaTKTTReq req) throws RemoteException {
        FCCCoreBO BO = new FCCCoreBO();
        try {
            LOGGER.info("CC_PhongToaTKTT --> [IN] --> Partner = [" + req.getPartner() + "], AccountNumberOrCif = [" + req.getAccounNumberOrCIF() + "], Type = [" + req.getType() + "], UserName = [" + req.getUserName() + "], ExpiryDate = [" + req.getExpiryDate() + "], MaDV = [" + req.getMaDonVi() + "]");
            PhongToaTKTTRes result= BO.phongToaTKTT(req);
            LOGGER.info("CC_PhongToaTKTT --> [OUT] --> result = [" + result.getErrorCode() + "]");
            
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public GiaiToaTKTTRes CC_GiaiToaTKTT(GiaiToaTKTTReq req) throws RemoteException {
        FCCCoreBO BO = new FCCCoreBO();
        try {
            LOGGER.info("CC_GiaiToaTKTT --> [IN] --> Partner = [" + req.getPartner() + "], Time = [" + req.getTime() + "], AccountNumber = [" + req.getAccounNumber()+ "], UserName = [" + req.getUserName() + "], MaDV = [" + req.getMaDonVi() + "]");
            GiaiToaTKTTRes result =  BO.giaiToaTKTT(req);
            LOGGER.info("CC_GiaiToaTKTT --> [OUT] --> result = [" + result.getErrorCode() + "]");
            
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public String CC_InsertPhongToaGiaiToaTKTT(String partner, String accountOrCif, String phongToaGiaiToa, String userName, String expiryDate, String maDV, String status, String type) throws RemoteException {
        try {
            LOGGER.info("CC_InsertPhongToaGiaiToaTKTT --> [IN] --> partner = [" + partner + "], "
                    + " accountOrCif = [" + accountOrCif + "],  phongToaGiaiToa = [" + phongToaGiaiToa + "],  userName = [" + userName + "],  expiryDate = [" + expiryDate + "], status = [" + status + "],type = [" + type + "] ");
            DvkhBO BO = new DvkhBO();
            String result = BO.CC_InsertPhongToaGiaiToaTKTT(partner, accountOrCif, phongToaGiaiToa, userName, expiryDate, maDV, status, type);
            LOGGER.info("CC_InsertPhongToaGiaiToaTKTT --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    @Override
    public long CC_UpdatePhongToaGiaiToaTKTT(long id, String status) throws RemoteException {
        try {
            LOGGER.info("CC_UpdatePhongToaGiaiToaTKTT --> [IN] --> id  = [" + id + "], status = [" + status + "]");
            DvkhBO BO = new DvkhBO();
            long result = BO.CC_UpdatePhongToaGiaiToaTKTT(id, status);
            LOGGER.info("CC_UpdatePhongToaGiaiToaTKTT --> [OUT] --> result = [" + result + "]");
            return result;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    
    
    /* Baotbq - 5/9/2023 - add update status trc khi goi qua napas */
    @Override
    public Object[] SI_UPDATETRANSFERFROMSIDETAILKIEUHOI_EBMR(
            double pID_MASTER,
            String pTXNDETAILID,
            String pPARTNERACCOUNT,
            String pCUSTUMERACCOUNT,
            String pBANKCODE,
            double pAmount,
            String pTYPETRANSFER,
            String pTYPECUSTACCOUNT,
            BigDecimal amtExchange
    ) throws Exception {
        try {
            LOGGER.info("SI_UPDATETRANSFERFROMSIDETAILKIEUHOI_EBMR --> [IN] --> pID_MASTER = [" + pID_MASTER + "], pTXNDETAILID = [" + pTXNDETAILID + "], pPARTNERACCOUNT = [" + pPARTNERACCOUNT + "], pCUSTUMERACCOUNT = [" + pCUSTUMERACCOUNT + "], pBANKCODE = [" + pBANKCODE + "], pAmount = [" + pAmount + "], pTYPETRANSFER = [" + pTYPETRANSFER + "], pTYPECUSTACCOUNT = [" + pTYPECUSTACCOUNT + "], amtExchange = [" + amtExchange + "]");
            SIBO BO = new SIBO();
            Object[] result = BO.UPDATETRANSFERFROMSIDETAILKIEUHOI_EBMR(pID_MASTER, pTXNDETAILID, pPARTNERACCOUNT, pCUSTUMERACCOUNT, pBANKCODE, pAmount, pTYPETRANSFER, pTYPECUSTACCOUNT, amtExchange);
            LOGGER.info("SI_UPDATETRANSFERFROMSIDETAILKIEUHOI_EBMR --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public Object[] getAccountBalance_wu(String accountNumber) throws RemoteException {
        try {
            LOGGER.info("getAccountBalance_wu --> [IN] --> accountNumber = [" + accountNumber + "]");
            OnlinePaymentBO BO = new OnlinePaymentBO();
            Object[] result = BO.getAccountBalance_wu(accountNumber);
            LOGGER.info("getAccountBalance_wu --> [OUT] --> result = [" + (result == null ? null : Arrays.toString(result)) + "]");
            
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public String checkIdPDF(String panMask, String idPdf) throws RemoteException
    {
        try {
            LOGGER.info("checkIdPDF --> [IN] --> PanMask = [" + panMask + "], IdPDF = [" + idPdf + "]");
            CWDwhBO CWBO = new CWDwhBO();
            String result = CWBO.checkIdPDF(panMask, idPdf);
            LOGGER.info("checkIdPDF --> [OUT] --> Size = [" +  result + "]");
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
}
