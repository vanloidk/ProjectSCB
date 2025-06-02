package scb.com.vn.controller;

import com.google.gson.Gson;
import com.visa.IVisaFactory;
import com.visa.VisaAPIFactory;
import com.visa.utils.MessageType;
import java.io.IOException;
import scb.com.vn.payment.Controller;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;
import scb.com.vn.common.model.cims.kht.KichHoatTheReq;
import scb.com.vn.common.model.cims.kht.KichHoatTheRes;
import scb.com.vn.common.model.cims.kt.KhoaTheReq;
import scb.com.vn.common.model.cims.kt.KhoaTheRes;
import scb.com.vn.common.model.cims.kt.MoKhoaTheReq;
import scb.com.vn.common.model.cims.kt.MoKhoaTheRes;
import scb.com.vn.common.model.cims.recieveFeedback.ReceiveFeedbackReq;
import scb.com.vn.common.model.cims.recieveFeedback.ReceiveFeedbackRes;
import scb.com.vn.common.model.cims.register.RegisterInfoReq;
import scb.com.vn.common.model.cims.register.RegisterInfoRes;
import scb.com.vn.common.model.cims.taikhoan.GiaiToaTKTTReq;
import scb.com.vn.common.model.cims.taikhoan.GiaiToaTKTTRes;
import scb.com.vn.common.model.cims.taikhoan.PhongToaTKTTReq;
import scb.com.vn.common.model.cims.taikhoan.PhongToaTKTTRes;
import scb.com.vn.common.model.cims.validatecmnd.ValidateCmndReq;
import scb.com.vn.common.model.cims.validatecmnd.ValidateCmndRes;
import scb.com.vn.common.model.cw.changepin.ErrorCodeEnum;
import scb.com.vn.common.model.cw.changepin.InitSessionToChangepinRq;
import scb.com.vn.common.model.cw.changepin.InitSessionToChangepinRs;
import scb.com.vn.common.model.email.SendEmailReq;
import scb.com.vn.common.model.email.SendEmailRes;
import scb.com.vn.common.model.info.GetSCBBranchRp;
import scb.com.vn.common.model.info.GetSCBBranchRq;
import scb.com.vn.common.model.collated.OneInventoryRequest;
import scb.com.vn.common.model.collated.OneInventoryResponse;
import scb.com.vn.common.model.mvisa.RequestMessage;
import scb.com.vn.common.model.mvisa.ResponseMessage;
import scb.com.vn.common.model.transfer.TransferMoneyEbankReq;
import scb.com.vn.common.model.transfer.TransferMoneyReq;
import scb.com.vn.common.model.transfer.TransferMoneyRes;
import scb.com.vn.common.model.transfer.internalapp.Transfer247Request;
import scb.com.vn.common.model.transfer.internalapp.Transfer247Response;
import scb.com.vn.common.model.transfer.napas.TransferMoney247EbankReq;
import scb.com.vn.common.model.transfer.napas.TransferMoney247Req;
import scb.com.vn.common.model.transfer.napas.TransferMoney247Res;
import scb.com.vn.common.model.transfer.status.GetListTransactionByDateRequest;
import scb.com.vn.common.model.transfer.status.GetListTransactionByDateResponse;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.dbi.dto.sms_partner_request;
import scb.com.vn.payment.IBTController;
import scb.com.vn.payment.OnlinePaymentController;
import scb.com.vn.enumUtils.CIMSResultEnum;
import scb.com.vn.enumUtils.EmailResultEnum;
import scb.com.vn.enumUtils.ResponseCodeEnum;
import scb.com.vn.model.CreditCardStatementRq;
import scb.com.vn.model.status.transferMoney.GetStatusOfTransferMoneyReq;
import scb.com.vn.model.status.transferMoney.GetStatusOfTransferMoneyRes;
import scb.com.vn.payment.AccountController;
import scb.com.vn.payment.EVNHCMController;
import scb.com.vn.payment.OnePayController;
import scb.com.vn.payment.HQController;
import scb.com.vn.payment.HQController247;
import scb.com.vn.payment.InternalController;
import scb.com.vn.payment.MomoService;
import scb.com.vn.payment.NPController;
import scb.com.vn.payment.ONLPblController;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import scb.com.vn.payment.SIController;
import scb.com.vn.payment.evnhcm.billing.DienLuc;
import scb.com.vn.utility.CimsUtils;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.EmailUtils;
import scb.com.vn.utility.SiUtils;
import scb.com.vn.utility.XMLUtils;

import scb.com.vn.payment.IBTHmap;
import scb.com.vn.payment.QRPaymentController;

import scb.com.vn.model.sms_partner_response;
import scb.com.vn.model.users.UserDtoRp;
import scb.com.vn.payment.ControllerUtil;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.VisaConstant;

/**
 *
 * @author minhndb
 */
public class ControllerImpl implements IController {

    private String _partnercode = null;

    /**
     *
     */
    public ControllerImpl() {
    }

    /**
     *
     * @param partnercode
     */
    public ControllerImpl(String partnercode) {
        _partnercode = partnercode;
    }

    /**
     * ***** SMS
     *
     *******
     * @param mobile
     * @param message
     * @return
     */
    @Override
    public int sendsms(String mobile, String message) {
        if (_partnercode.equalsIgnoreCase("EBK")) {
            // "SCB Ma xac thuc giao dich Internet Banking qua SMS cua khach hang luc"
            // "15:56:30 ngay 07/08/2012 la: 123456"
            String format = "SCB Ma xac thuc giao dich Internet Banking qua SMS cua khach hang luc %1$s ngay %2$s la %3$s";

            // Get current date/time and format it for output
            Date date = new Date();
            SimpleDateFormat formatDateNow = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatTimeNow = new SimpleDateFormat("HH:mm:ss");

            String dateNow = formatDateNow.format(date);
            String timeNow = formatTimeNow.format(date);

            String formatMsgOut = String.format(format, timeNow, dateNow, message);
            Sms sms = new Sms();
            return sms.sendsms(mobile, formatMsgOut, "MAXT", "FCDB");
        }

        Sms sms = new Sms();
        return sms.sendsms(mobile, message, "GW", "SCB");
    }

    /**
     *
     * @param mobile
     * @param msgcontents
     * @return
     */
    @Override
    public String sendsmstotnb(String mobile, String msgcontents) {
        Sendmsgtotnb sms = new Sendmsgtotnb();
        return sms.sendsmstotnb(mobile, msgcontents);
    }

    /**
     * ***** TOKEN
     *
     *******
     * @param userId
     * @param sOTP
     * @return
     */
    @Override
    public int checkAuthenTokenkey(String userId, String sOTP) {
        Tokenkey t = new Tokenkey();
        return t.checkAuthenTokenkey(userId, sOTP);
    }

    /**
     * ***** PAYMENT
     *
     *******
     * @param xml
     * @return
     */
    @Override
    public String requestPayment(String xml) {
        Controller payment = new Controller();
        try {
            Helper.writeLog(ControllerImpl.class, org.apache.log4j.Level.INFO, "***- LOG CALL (FUNCTION requestPayment): request xml: " + xml);
            String response = payment.requestPayment(xml);
            Helper.writeLog(ControllerImpl.class, org.apache.log4j.Level.INFO, "***- LOG CALL (FUNCTION requestPayment): response xml: " + response);
            return response;
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }
    
    /**
     *
     * @param accfrom
     * @param accto
     * @param amount
     * @param narative
     * @return
     */
    @Override
    public String transferFCUBS(String accfrom, String accto, BigDecimal amount, String narative) {
        Fcubs fcubs = new Fcubs();
        try {
            return fcubs.transferFCUBS(accfrom, accto, amount, narative);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param brnAccountFrom
     * @param accountFrom
     * @param brnAccountTo
     * @param accountTo
     * @param amount
     * @param narative
     * @return
     */
    @Override
    public String transferFCUBSWithBranch(String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo, BigDecimal amount, String narative) {
        Fcubs fcubs = new Fcubs();
        try {
            return fcubs.transferFCUBSWithBranch(brnAccountFrom, accountFrom, brnAccountTo, accountTo, amount, narative);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param fccRef
     * @param timeout
     * @return
     */
    @Override
    public String revertTransferFCUBS(String fccRef, int timeout) {
        Fcubs fcubs = new Fcubs();
        try {
            return fcubs.revertTransferFCUBS(fccRef, timeout);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param username
     * @param password
     * @param ip
     * @return
     */
    @Override
    public boolean checkpassTNB(String username, String password, String ip) {
        CheckPassTNB cptnb = new CheckPassTNB();
        try {
            return cptnb.checkpassTNB(username, password, ip);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return false;
        }
    }

    /**
     *
     * @param xml
     * @param _hm
     * @return
     */
    @Override
    public String exchgBill(String xml, HashMap _hm) {
        ExchangePaybill ep = new ExchangePaybill();
        try {
            return ep.exchgBill(xml, _hm);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param str
     * @return
     */
    @Override
    public String getString(String str) {
        return "| Return from callMethod WS service " + str;

    }

    @Override
    public String requestUploadFileToVNPAY(String idpartner, String filename) {
        CollatedPayment cp = new CollatedPayment(idpartner);
        return cp.requestUploadFileToVNPAY(filename);
    }

    @Override
    public String requestDownloadFileToVNPAY(String idpartner, String filename) {
        CollatedPayment cp = new CollatedPayment(idpartner);
        return cp.requestDownloadFileToVNPAY(filename);
    }

    /**
     *
     */
    @Override
    public void resetGwPermissions() {
        try {
            Configuration.lst_permission.clear();
            Configuration.lst_permission = Helper.getDBI().getGwPermissions();
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
        }
    }

    /**
     *
     */
    @Override
    public void resetExchgBill() {
        ExchangePaybill._reinit();
    }

    /*
     LyDTY
     */
    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String cardVerify(String xml) {
        try {
            OnlinePaymentController payment = new OnlinePaymentController();
            return payment.cardVerify(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
        }
        return null;
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String refundOnlTrans(String xml) {
        try {
            OnlinePaymentController payment = new OnlinePaymentController();
            return payment.refundOnlTrans(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
        }
        return null;
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String queryOnlTrans(String xml) {
        try {
            OnlinePaymentController payment = new OnlinePaymentController();
            return payment.queryOnlTrans(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
        }
        return null;
    }

    /**
     *
     * @param IDTrans
     * @param UserId
     * @param ClientID
     * @return
     */
    @Override
    public int sendOTP(String IDTrans, String UserId, String ClientID) {
        try {
            OnlinePaymentController payment = new OnlinePaymentController();
            return payment.sendOTP(IDTrans, UserId, ClientID);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return 0;
        }
    }

    /**
     *
     * @param merchantid
     * @param TransID
     * @param RefundTransID
     * @param Amount
     * @param CCY
     * @param AddInfo
     * @param Localdatatime
     * @return
     */
    @Override
    public String SMLREFUND(String merchantid, String TransID, String RefundTransID, BigDecimal Amount, String CCY, String AddInfo, String Localdatatime) {
        try {
            OnlinePaymentController payment = new OnlinePaymentController();
            return payment.SMLREFUND(merchantid, TransID, RefundTransID, Amount, CCY, AddInfo, Localdatatime);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    //@Override
    /**
     *
     * @param product
     * @param accfrom
     * @param accto
     * @param amount
     * @param narative
     * @return
     */
    public String transferFCUBSWithProduct(String product, String accfrom, String accto, BigDecimal amount, String narative) {
        Fcubs fcubs = new Fcubs();
        try {
            return fcubs.transferFCUBSWithProduct(product, accfrom, accto, amount, narative);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param userId
     * @param sOTP
     * @return
     */
    @Override
    public int checkAuthenTokenkeyforONLTRANS(String userId, String sOTP) {
        Tokenkey t = new Tokenkey();
        return t.checkAuthenTokenkeyforONLTRANS(userId, sOTP);
    }

    /**
     *
     * @param idpartner
     * @param filename
     * @return
     */
    @Override
    public String requestUploadFileToPartner(String idpartner, String filename) {
        CollatedPayment cp = new CollatedPayment(idpartner);
        return cp.requestUploadFileToPartner(filename);
    }

    /**
     *
     * @param idpartner
     * @param filename
     * @return
     */
    @Override
    public String requestDownloadFileFromPartner(String idpartner, String filename) {
        CollatedPayment cp = new CollatedPayment(idpartner);
        return cp.requestDownloadFileFromPartner(filename);
    }

    /**
     *
     * @param FromNumber
     * @param TypeFromNumber
     * @param FullName
     * @param ToNumber
     * @param TypeToNumber
     * @param BenID
     * @param Amount
     * @param CCY
     * @param channel
     * @param Desc
     * @param TermId
     * @param CardAcceptor
     * @param TypeFunction
     * @return
     */
    @Override
    public String SMLTransfeFromSCB(String FromNumber, String TypeFromNumber, String FullName, String ToNumber, String TypeToNumber,
            String BenID, BigDecimal Amount,
            String CCY, String channel, String Desc,
            String TermId, String CardAcceptor, String TypeFunction) {
        try {
            IBTController controller = new IBTController();
            return controller.transfeFromSCB(FromNumber, TypeFromNumber, FullName, ToNumber, TypeToNumber, BenID, Amount, CCY, channel, Desc, TermId, CardAcceptor, TypeFunction);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param Request
     * @return
     */
    @Override
    public String SMLTransfeToSCB(String Request) {
        try {
            IBTController controller = new IBTController();
            return controller.transferToSCB(Request);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    //CONTACT CENTER
    /**
     *
     * @param SoDienThoai
     * @return
     */
    public String CC_GetCustomerInfoBySoDienThoai(String SoDienThoai) {
        try {
            ContactCenter cc = new ContactCenter();
            String result = cc.CC_GetCustomerInfoBySoDienThoai(SoDienThoai);
            if (result.startsWith("<result code = \"1\">")) {
                String newPhone = CommonUtils.swapPhoneNumber(SoDienThoai);
                if (!SoDienThoai.equals(newPhone)) {
                    result = cc.CC_GetCustomerInfoBySoDienThoai(newPhone);
                }
            }
            return result;
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_GetCustomerInfoByCIF(String CIF) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetCustomerInfoByCIF(CIF);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param IDCard
     * @return
     */
    public String CC_GetCustomerInfoByIDCard(String IDCard) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetCustomerInfoByIDCard(IDCard);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }

    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetListAccount(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetListAccount(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetRecentTransaction(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetRecentTransaction(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetTDAccounts(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetTDAccounts(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetTDAccountDetails(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetTDAccountDetails(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetTDAccountTranHistDetails(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetTDAccountTranHistDetails(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetCLAccounts(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetCLAccounts(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetCLAccountDetails(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetCLAccountDetails(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetCLHistPaymentDetails(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetCLHistPaymentDetails(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetCLAccountPayCalDetails(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetCLAccountPayCalDetails(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    //Ebanking    
    /**
     *
     * @param CIF
     * @return
     */
    public String CC_IB_CUST_INFO(String CIF) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_IB_CUST_INFO(CIF);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_SMS_CUST_INFO(String CIF) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_SMS_CUST_INFO(CIF);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_SMSALERT_CUST_INFO(String CIF) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_SMSALERT_CUST_INFO(CIF);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_CUST_MBANKING_INFO(String CIF) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_CUST_MBANKING_INFO(CIF);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param CIF
     * @return
     */
    public String CC_SMS_ALERT_TD_INFO(String CIF) {
        try {
            ContactCenter cc = new ContactCenter();
            String result = cc.CC_SMS_ALERT_TD_INFO(CIF);
            if (result != null && !result.isEmpty() && result.contains("Result Code = '0'")) {
                result = result.replaceAll("NGAY_DANG_KY", "NGAY_DK");
            }
            return result;
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetSMSHist(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetSMSHist(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetEmailHist(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetEmailHist(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetTTHD_TRANS_HIST(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetTTHD_TRANS_HIST(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetTTHD_AUTO_CONTRACT(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetTTHD_AUTO_CONTRACT(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    //CARD
    /**
     *
     * @param CIF
     * @return
     */
    public String CC_GetInternalCardInfo(String CIF) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetInternalCardInfo(CIF);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetInternalCardTransaction(String CardNumber) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetInternalCardTransaction(CardNumber);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetInternalCardInfo_MC(String CardNumber) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetInternalCardInfo_MC(CardNumber);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetInternalCardInfo_MCDB(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetInternalCardInfo_MCDB(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetCard_Profile(String CardNumber) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetCard_Profile(CardNumber);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }
    
    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetAward_Point(String CardNumber) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetAward_Point(CardNumber);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetInternalCardTran_MC(String CardNumber) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetInternalCardTran_MC(CardNumber);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GET_TT_SaoKe(String CardNumber) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GET_TT_SaoKe(CardNumber);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetCreditPayTransaction(String CardNumber) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetCreditPayTransaction(CardNumber);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param CardNumber
     * @return
     */
    public String CC_GetMonth(String CardNumber) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetMonth(CardNumber);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    //END OF CONTACT CENTER
    /**
     * *mobile banking
     *
     * @param xml
     * @return
     */
    @Override
    public String GetCustomerInfo(String xml) {
        MobileController mb = new MobileController();
        return mb.GetCustomerInfo(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetAccountList(String xml) {
        MobileController mb = new MobileController();
        return mb.GetAccountList(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetAccountInfo(String xml) {
        MobileController mb = new MobileController();
        return mb.GetAccountInfo(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetAccountStmt(String xml) {
        MobileController mb = new MobileController();
        return mb.GetAccountStmt(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String FundTransfer(String xml) {
        MobileController mb = new MobileController();
        return mb.FundTransfer(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetAccountTypeAzInfo(String xml) {
        MobileController mb = new MobileController();
        return mb.GetAccountTypeAzInfo(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String OpenOnlineAz(String xml) {
        MobileController mb = new MobileController();
        return mb.OpenOnlineAzOLD(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CloseOnlineAz(String xml) {
        MobileController mb = new MobileController();
        return mb.CloseOnlineAzOLD(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetBeneficaryName(String xml) {
        MobileController mb = new MobileController();
        return mb.GetBeneficaryName(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String SendSMSToMB(String xml) {
        MobileController mb = new MobileController();
        return mb.SendSMSToMB(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetBillService(String xml) {
        MobileController mb = new MobileController();
        return mb.GetBillService(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetBillProvider(String xml) {
        MobileController mb = new MobileController();
        return mb.GetBillProvider(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetBillInfo(String xml) {
        MobileController mb = new MobileController();
        return mb.GetBillInfo(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String BillPayment(String xml) {
        MobileController mb = new MobileController();
        return mb.BillPayment(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetCreditCardInfo(String xml) {
        MobileController mb = new MobileController();
        return mb.GetCreditCardInfo(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CreditCardPay(String xml) {
        MobileController mb = new MobileController();
        return mb.CreditCardPay(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetToken(String xml) {
        MobileController mb = new MobileController();
        return mb.GetToken(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CheckBalanceBeforeTran(String xml) {
        MobileController mb = new MobileController();
        return mb.CheckBalanceBeforeTran(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String ExchangeRate(String xml) {
        MobileController mb = new MobileController();
        return mb.ExchangeRate(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GoldRate(String xml) {
        MobileController mb = new MobileController();
        return mb.GoldRate(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String InterestRate(String xml) {
        MobileController mb = new MobileController();
        return mb.InterestRate(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CheckToken(String xml) {
        MobileController mb = new MobileController();
        return mb.CheckToken(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetMobileFee(String xml) {
        MobileController mb = new MobileController();
        return mb.GetMobileFee(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetAzBeforeRedemption(String xml) {
        MobileController mb = new MobileController();
        return mb.GetAzBeforeRedemption(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetTemplateAndBeneFromEb(String xml) {
        MobileController mb = new MobileController();
        return mb.GetTemplateAndBeneFromEb(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetRedemptionAzList(String xml) {
        MobileController mb = new MobileController();
        return mb.GetRedemptionAzList(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String AddFeedback(String xml) {
        MobileController mb = new MobileController();
        return mb.AddFeedback(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetStaff(String xml) {
        MobileController mb = new MobileController();
        return mb.GetStaff(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String LoanRegister(String xml) {
        MobileController mb = new MobileController();
        return mb.LoanRegister(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CheckIssueATMCard(String xml) {
        MobileController mb = new MobileController();
        return mb.CheckIssueATMCard(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String IssueATMCard(String xml) {
        MobileController mb = new MobileController();
        return mb.IssueATMCard(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetCardList(String xml) {
        MobileController mb = new MobileController();
        return mb.GetCardList(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String IssueCreditCard(String xml) {
        MobileController mb = new MobileController();
        return mb.IssueCreditCard(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CreditCardType(String xml) {
        MobileController mb = new MobileController();
        return mb.CreditCardType(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String ReissuePIN(String xml) {
        MobileController mb = new MobileController();
        return mb.ReissuePIN(xml);
    }

    /**
     *
     * @param productname
     * @param brnAccountFrom
     * @param accountFrom
     * @param brnAccountTo
     * @param accountTo
     * @param amount
     * @param charge
     * @param tax
     * @param narative
     * @return
     */
    @Override
    public String transferFCUBSWithProductCharge(String productname, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, BigDecimal charge, BigDecimal tax, String narative) {
        Fcubs fcubs = new Fcubs();
        try {
            return fcubs.transferFCUBSWithProductCharge(productname, brnAccountFrom, accountFrom, brnAccountTo, accountTo,
                    amount, charge, tax, narative);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CreditCardStatement(String xml) {
        MobileController mb = new MobileController();
        return mb.CreditCardStatement(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String RewardPoint(String xml) {
        MobileController mb = new MobileController();
        return mb.RewardPoint(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetCCStatement(String xml) {
        MobileController mb = new MobileController();
        return mb.GetCCStatement(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String SearchResgisterCardMB(String xml) {
        MobileController mb = new MobileController();
        return mb.SearchResgisterCardMB(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CheckBalanceBeforeFee(String xml) {
        MobileController mb = new MobileController();
        return mb.CheckBalanceBeforeFee(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetAccountInfoNew(String xml) {
        MobileController mb = new MobileController();
        return mb.GetAccountInfoNew(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetAccountStmtNew(String xml) {
        MobileController mb = new MobileController();
        return mb.GetAccountStmtNew(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetAccountListNew(String xml) {
        MobileController mb = new MobileController();
        return mb.GetAccountListNew(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CheckAlertTD(String xml) {
        MobileController mb = new MobileController();
        return mb.CheckAlertTD(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String RegisterAlertTD(String xml) {
        MobileController mb = new MobileController();
        return mb.RegisterAlertTD(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetPrimaryCardList(String xml) {
        MobileController mb = new MobileController();
        return mb.GetPrimaryCardList(xml);
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String cardVerifyRegister(String strXML) {
        try {
            OnlinePaymentController controller = new OnlinePaymentController();
            return controller.cardVerifyRegister(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String otpVerify(String strXML) {
        try {
            OnlinePaymentController controller = new OnlinePaymentController();
            return controller.otpVerify(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String payment(String strXML) {
        try {
            OnlinePaymentController controller = new OnlinePaymentController();
            return controller.payment(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String paymentWithProfileID(String strXML) {
        try {
            OnlinePaymentController controller = new OnlinePaymentController();
            return controller.paymentWithProfileID(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String checkCard(String strXML) {
        try {
            OnlinePaymentController controller = new OnlinePaymentController();
            return controller.checkCard(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String takeOutWallet(String strXML) {
        try {
            OnlinePaymentController controller = new OnlinePaymentController();
            return controller.takeOutWallet(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String refundPayment(String strXML) {
        try {
            OnlinePaymentController controller = new OnlinePaymentController();
            return controller.refundPayment(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String revertTakeOutWallet(String strXML) {
        try {
            OnlinePaymentController controller = new OnlinePaymentController();
            return controller.revertTakeOutWallet(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String queryTransaction(String strXML) {
        try {
            OnlinePaymentController controller = new OnlinePaymentController();
            return controller.queryTransaction(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String destroyProfileID(String strXML) {
        try {
            OnlinePaymentController controller = new OnlinePaymentController();
            return controller.destroyProfileID(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /* CARDWORK WEB SERVICES */
    /**
     *
     * @param srcCasaAccount
     * @param pan
     * @param expDate
     * @param source
     * @param brandCode
     * @param currencyCode
     * @param amount
     * @param debtPay
     * @param debtCur
     * @param IPPPayType
     * @return
     */
    @Override
    public String cardReload(String srcCasaAccount, String pan, String expDate, String source, String brandCode,
            String currencyCode, BigDecimal amount, BigDecimal debtPay, BigDecimal debtCur, String IPPPayType) {
        try {
            CardwordController controller = new CardwordController();
            return controller.cardReload(srcCasaAccount, pan, expDate, source, brandCode,
                    currencyCode, amount, debtPay, debtCur, IPPPayType);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String cardRewardAdjustment(String xml) {
        try {
            CardwordController controller = new CardwordController();
            return controller.cardRewardAdjustment(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String cardBalanceAdjustment(String xml) {
        try {
            CardwordController controller = new CardwordController();
            return controller.cardBalanceAdjustment(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param pan
     * @param ActionType
     * @param ActionCode
     * @return
     */
    @Override
    public String cardProfileMaintenance(String pan, String ActionType, String ActionCode) {
        try {
            CardwordController controller = new CardwordController();
            return controller.cardProfileMaintenance(pan, ActionType, ActionCode);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String resetPINCard(String xml) {
        try {
            CardwordController controller = new CardwordController();
            return controller.resetPINCard(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param srcCasaAccount
     * @param pan
     * @param expDate
     * @param source
     * @param brandCode
     * @param currencyCode
     * @param amount
     * @param user_maker
     * @param user_checker
     * @param idcard
     * @param idcardDob
     * @param idcardName
     * @param idcardAddress
     * @param debtPay
     * @param debtCur
     * @param IPPPayType
     * @return
     */
    @Override
    public String cardReloadCash(String srcCasaAccount, String pan, String expDate, String source, String brandCode, String currencyCode,
            BigDecimal amount, String user_maker, String user_checker, String idcard, String idcardDob, String idcardName, String idcardAddress, BigDecimal debtPay, BigDecimal debtCur, String IPPPayType) {
        try {
            CardwordController controller = new CardwordController();
            return controller.cardReloadCash(srcCasaAccount, pan, expDate, source, brandCode,
                    currencyCode, amount, user_maker, user_checker, idcard, idcardDob, idcardName, idcardAddress, debtPay, debtCur, IPPPayType);
            //return null;
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /* CARDWORK WEB SERVICES */
    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetCreditCardList(String xml) {
        MobileController mb = new MobileController();
        return mb.GetCreditCardList(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CreditCardPayNew(String xml) {
        MobileController mb = new MobileController();
        return mb.CreditCardPayNew(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetStatementMonthList(String xml) {
        MobileController mb = new MobileController();
        return mb.GetStatementMonthList(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String requestTransfer(String xml) {
        SIController si = new SIController();
        try {
            return si.requestTransfer(xml);
        } catch (SAXException ex) {
            Logger.getLogger(ControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "99";
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String queryAccount(String strXML) {
        SIController si = new SIController();
        return si.queryAccount(strXML);
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String getListBankCode(String strXML) {
        SIController si = new SIController();
        return si.getListBankCode(strXML);
    }

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
     */
    @Override
    public String transferToSI(String PPARTNERID,
            String PCUSTUMERACCOUNT,
            String PCUSTUMERNAME,
            BigDecimal PAMOUNT,
            String PCCY,
            String PCHANNELID,
            String PTRANSDATE, //ddMMyyyhhmiss
            String PADDINFO) {
        SIController si = new SIController();
        return si.transferToSI(PPARTNERID, PCUSTUMERACCOUNT, PCUSTUMERNAME, PAMOUNT, PCCY, PCHANNELID, PTRANSDATE, PADDINFO);
    }

    /**
     *
     * @param strTKCK
     * @return
     */
    @Override
    public String getSIName(String strTKCK) {
        SIController si = new SIController();
        return si.getSIName(strTKCK);

    }

    //DOI SOAT NAPAS (S)
    /**
     *
     * @param merchantid
     * @param TransID
     * @param RefundTransID
     * @param Amount
     * @param CCY
     * @param AddInfo
     * @param Localdatatime
     * @param servicecode
     * @return
     */
    @Override
    public String BANKNETREFUND(String merchantid, String TransID, String RefundTransID, BigDecimal Amount, String CCY, String AddInfo, String Localdatatime, String servicecode) {
        try {
            Controller payment = new Controller();
            return payment.BANKNETREFUND(merchantid, TransID, RefundTransID, Amount, CCY, AddInfo, Localdatatime, servicecode);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    //DOI SOAT NAPAS (E)
    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String InsurancePayment(String xml) {
        try {
            InsuranceController insurance = new InsuranceController();
            return insurance.InsurancePayment(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    //Manulife
    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String PayIns(String xml) {
        try {
            InsPayment ins = new InsPayment();
            return ins.PayIns(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    /**
     *
     * @param CusAccount
     * @param PartnerAcc
     * @param totalamount
     * @param pol_num
     * @param idchannel
     * @return
     */
    @Override
    public String PaymentInsurance(String CusAccount, String PartnerAcc, BigDecimal totalamount, String pol_num, String idchannel) {
        try {
            InsPaymentUtil ipu = new InsPaymentUtil();
            return ipu.PaymentInsurance(CusAccount, PartnerAcc, totalamount, pol_num, idchannel);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    /**
     *
     * @param CusAccount
     * @param totalamount
     * @param user_maker
     * @param user_checker
     * @param pol_num
     * @param idchannel
     * @return
     */
    @Override
    public String PaymentInsuranceCash(String CusAccount, BigDecimal totalamount, String user_maker, String user_checker, String pol_num, String idchannel) {
        try {
            InsPaymentUtil ipu = new InsPaymentUtil();
            return ipu.PaymentInsuranceCash(CusAccount, totalamount, user_maker, user_checker, pol_num, idchannel);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetSecuritiesAccountName(String xml) {
        MobileUpgradeController mb = new MobileUpgradeController();
        return mb.GetSecuritiesAccountName(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetSecuritiesPartner(String xml) {
        MobileUpgradeController mb = new MobileUpgradeController();
        return mb.GetSecuritiesPartner(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String SecuritiesPayment(String xml) {
        MobileUpgradeController mb = new MobileUpgradeController();
        return mb.SecuritiesPayment(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String OpenLockCardFunc(String xml) {
        MobileUpgradeController mb = new MobileUpgradeController();
        return mb.OpenLockCardFunc(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CheckRegisterAutoBill(String xml) {
        MobileUpgradeController mb = new MobileUpgradeController();
        return mb.CheckRegisterAutoBill(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String RegisterAutoBill(String xml) {
        MobileUpgradeController mb = new MobileUpgradeController();
        return mb.RegisterAutoBill(xml);
    }
    //DOI SOAT NAPAS (E)
    //HAI QUAN

    /**
     *
     * @param msgType
     * @param objs
     * @return
     */
    public String getMessageQueryXML(String msgType, String objs) {
        try {
            HQController hq = new HQController();
            return hq.getMessageQueryXML(msgType, objs);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    /**
     *
     * @param msgType
     * @param ID
     * @return
     */
    public String getMessageXML(String msgType, String ID) {
        try {
            HQController hq = new HQController();
            return hq.getMessageXML(msgType, ID);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String paymentAndRegister(String strXML) {
        try {
            OnlinePaymentController hq = new OnlinePaymentController();
            return hq.paymentAndRegister(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String getProfileID(String strXML) {
        try {
            OnlinePaymentController hq = new OnlinePaymentController();
            return hq.getProfileID(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    //Mua the cao VTC
    /**
     *
     * @param xml
     * @param processingcode
     * @return
     */
    @Override
    public String BuycardRequest(String xml, String processingcode) {
        try {
            BuyCard bc = new BuyCard();
            return bc.BuycardRequest(xml, processingcode);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }
    //Mua the cao VTC

    //Tân Cảng
    /**
     *
     * @param xml
     * @param processingcode
     * @return
     */
    @Override
    public String newportPayment(String xml, String processingcode) {
        try {
            NPController npc = new NPController();
            return npc.newportPayment(xml, processingcode);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    //Tân Cảng
    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String RegisterIssueCard(String xml) {
        try {
            WebSCBController web = new WebSCBController();
            return web.RegisterIssueCard(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param UserName
     * @param CifNo
     * @param UserLock
     * @return
     */
    @Override
    public String LockFeeMobileBankingOfSCB(String UserName, String CifNo, String UserLock) {
        try {
            VnInfoGateway vninfo = new VnInfoGateway();
            return vninfo.LockFeeMobileBankingOfSCB(UserName, CifNo, UserLock);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param UserName
     * @param CifNo
     * @param UserLock
     * @return
     */
    @Override
    public String UnLockFeeMobileBankingOfSCB(String UserName, String CifNo, String UserLock) {
        try {
            VnInfoGateway vninfo = new VnInfoGateway();
            return vninfo.UnLockFeeMobileBankingOfSCB(UserName, CifNo, UserLock);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String OTPREQUEST(String xml) {
        InternalController controller = new InternalController();
        return controller.OTPREQUEST(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String OTPRESPONSE(String xml) {
        InternalController controller = new InternalController();
        return controller.OTPRESPONSE(xml);
    }

    /* ONEPAY */
    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CardVerification(String xml) {
        OnePayController controller = new OnePayController();
        return controller.CardVerification(xml);

    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String OTPVerification(String xml) {
        OnePayController controller = new OnePayController();
        return controller.OTPVerification(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String Payment(String xml) {
        OnePayController controller = new OnePayController();
        return controller.Payment(xml);
    }

    /* ONEPAY */

 /* VNPAY */
    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String getAccountBalance(String xml) {
        OnlinePaymentController hq = new OnlinePaymentController();
        return hq.getAccountBalance(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetChangGiftHistory(String xml) {
        MobileUpgradeController web = new MobileUpgradeController();
        return web.GetChangGiftHistory(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String ChangeRewardPoint(String xml) {
        MobileUpgradeController web = new MobileUpgradeController();
        return web.ChangeRewardPoint(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetGiftList(String xml) {
        MobileUpgradeController web = new MobileUpgradeController();
        return web.GetGiftList(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String paymentWithAccount(String xml) {

        OnlinePaymentController controller = new OnlinePaymentController();
        return controller.paymentWithAccount(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String refundPaymentWithAccount(String xml) {
        OnlinePaymentController controller = new OnlinePaymentController();
        return controller.refundPaymentWithAccount(xml);
    }

    /* VNPAY */

 /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String PayLoanAccount(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.PayLoanAccount(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountListByType(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.GetAccountListByType(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String OnlineInsBuy(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.OnlineInsBuy(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String GetOnlineInsInfo(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.GetOnlineInsInfo(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String GetOnlineInsTypeList(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.GetOnlineInsTypeList(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String InsuranceFeePay(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.InsuranceFeePay(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String GetInsurancePartner(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.GetInsurancePartner(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String GetInsuranceContractInfo(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.GetInsuranceContractInfo(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String ListAutoBill(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.ListAutoBill(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String CancelAutoBill(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.CancelAutoBill(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }

    }

    /**
     *
     * @param xml
     * @return
     */
    public String GetCardListNew(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.GetCardListNew(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }

    }

    /**
     *
     * @param xml
     * @return
     */
    public String ChangePin(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.ChangePin(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }

    }

    /**
     *
     * @param xml
     * @return
     */
    public String GetOnlineInsFormList(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.GetOnlineInsFormList(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }

    }

    /**
     *
     * @param xml
     * @return
     */
    public String GetOnlineInsDutyList(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.GetOnlineInsDutyList(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }

    }

    /**
     *
     * @param xml
     * @return
     */
    public String GetManulifeContractList(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.GetManulifeContractList(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String GetManulifeTypeList(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.GetManulifeTypeList(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String IssueDebitCard(String xml) {
        MobileUpgradeController mb = new MobileUpgradeController();
        return mb.IssueDebitCard(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CheckOpenOnlineAz(String xml) {
        MobileUpgradeController mb = new MobileUpgradeController();
        return mb.CheckOpenOnlineAz(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String SelectOnlineInsBuyHistory(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.SelectOnlineInsBuyHistory(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String RetrieveOnlineInsBuyHistory(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.RetrieveOnlineInsBuyHistory(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String VerifyTokenCode(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.VerifyTokenCode(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @param apiName
     * @return
     */
    @Override
    public String callMOMOService(String xml, String apiName) {
        try {
            MomoService web = new MomoService();
            return web.callMOMOService(xml, apiName);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param phonenumber
     * @param Content
     * @return
     */
    @Override
    public String sendSMSOTP(String phonenumber, String Content) {
        try {
            OnlinePaymentController cotroller = new OnlinePaymentController();
            return cotroller.sendSMSOTP(phonenumber, Content);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }
    //HQ 247

    /**
     *
     * @param msgType
     * @param ID
     * @return
     */
    @Override
    public String getMessageXML247(String msgType, String ID) {
        try {
            HQController247 hq = new HQController247();
            return hq.getMessageXML(msgType, ID);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    /* MASTERPASS QR*/
    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String PayByQRCode(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.PayByQRCode(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    @Override
    public String initSessionToChangepin(String xml) {
        /* Kiem tra du lieu co dung format hay ko */
        Object object = XMLUtils.unMarshaller(InitSessionToChangepinRq.class, xml);
        if (object == null) {
            InitSessionToChangepinRs info = new InitSessionToChangepinRs();
            info.setError(ErrorCodeEnum.INVALID_FORMAT);
            return XMLUtils.Marshaller(info);
        }

        /* Kiem tra request co bi qua han 5 phut hay ko */
        InitSessionToChangepinRq req = (InitSessionToChangepinRq) object;
        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            InitSessionToChangepinRs info = new InitSessionToChangepinRs(req, ErrorCodeEnum.OUT_OF_TIME);
            return XMLUtils.Marshaller(info);
        }

        /* Kiem tra xem doi tac co quyen truy cap hay ko va du lieu da ky co dung cua doi tac hay ko */
        boolean canAccess = CimsUtils.canAccess(req.getPartnerId(), req.getValueToBuildMac(), req.getSignature());
        if (!canAccess) {
            InitSessionToChangepinRs info = new InitSessionToChangepinRs(req, ErrorCodeEnum.MAC_IS_ERROR);
            return XMLUtils.Marshaller(info);
        }

        /* Thuc hien xu ly api */
        MobileUpgradeController mbController = new MobileUpgradeController();
        return mbController.initSessionToChangepin(req);
    }

    /* VISA QR*/
    /**
     *
     * @param xml
     * @return
     */
    /*
    @Override
    public String MvisaQRCode(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            IVisaFactory factory = new VisaAPIFactory();
            RequestMessage message = new RequestMessage();
            factory.execute(message, VisaConstant.TRANSACTIONINFO, MessageType.MERPUSHPAYMENT);
            return web.MvisaQRCode(xml);
            //return "test";
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }
    */
    @Override
    public String MvisaQRCode(String xml) {
        try {
            
            MobileUpgradeController web = new MobileUpgradeController();
            IVisaFactory factory = new VisaAPIFactory();
            RequestMessage message = new RequestMessage();
            factory.execute(message, VisaConstant.TRANSACTIONINFO, MessageType.MERPUSHPAYMENT);
            return web.MvisaQRCode(xml);
            //return "test";
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }
    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetCardListByType(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.GetCardListByType(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CheckBalanceMasterPassBeforeTran(String xml) {
        MobileUpgradeController mb = new MobileUpgradeController();
        return mb.CheckBalanceMasterPassBeforeTran(xml);
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetIPP(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetIPP(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetIPPDetail(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetIPPDetail(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param param
     * @return
     */
    public String CC_GetIPPHist(String param) {
        try {
            ContactCenter cc = new ContactCenter();
            return cc.CC_GetIPPHist(param);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param productname
     * @param userid
     * @param brnAccountFrom
     * @param accountFrom
     * @param brnAccountTo
     * @param accountTo
     * @param amount
     * @param narative
     * @param timeout
     * @return
     */
    @Override
    public String transferFCUBSWithTimeOut(String productname, String userid, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, String narative, int timeout) {
        Fcubs fcubs = new Fcubs();
        try {
            return fcubs.transferFCUBSWithTimeOut(productname, userid, brnAccountFrom, accountFrom, brnAccountTo, accountTo, amount, narative, timeout);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param maDL
     * @return
     */
    @Override
    public String bankRequestMaDL(String maDL) {
        try {
            EVNHCMController controller = new EVNHCMController();
            List<DienLuc> listDL = controller.bankRequestMaDL();
            for (DienLuc dl : listDL) {
                if (maDL.equals(dl.getMaDL())) {
                    String SEPE = "@";
                    return dl.getMaDL() + SEPE + dl.getTenDL() + SEPE + dl.getDiaChi() + SEPE + dl.getMaSoThue()
                            + SEPE + dl.getDienThoai() + SEPE + dl.getDtSuaChua() + SEPE + dl.getMaCMIS();
                }
            }
            return "";
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetBillInfoForONLPM(String xml) {
        ONLPblController mb = new ONLPblController();
        return mb.GetBillInfo(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String BillPaymentForONLPM(String xml) {
        ONLPblController mb = new ONLPblController();
        return mb.BillPayment(xml);
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String checkPayment(String strXML) {
        OnlinePaymentController onl = new OnlinePaymentController();
        return onl.checkPayment(strXML);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetFeeMobile(String xml) {
        MobileUpgradeController mb = new MobileUpgradeController();
        try {
            return mb.GetFeeMobile(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @Override
    public String transfer247FromPartner(String request) {
        try {
            SIController cotroller = new SIController();
            return cotroller.transfer247FromPartner(request);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String queryTCHTransaction(String strXML) {
        try {
            SIController controller = new SIController();
            return controller.queryTransaction(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String getListBankCode247(String strXML) {
        SIController si = new SIController();
        return si.getListBankCode247(strXML);
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String checkKYC(String strXML) {
        OnlinePaymentController onl = new OnlinePaymentController();
        return onl.checkKYC(strXML);
    }

    /**
     *
     * @param strXML
     * @return
     */
    @Override
    public String transferToAccount(String strXML) {
        OnlinePaymentController onl = new OnlinePaymentController();
        return onl.transferToAccount(strXML);
    }

    /**
     *
     * @param strXML
     * @return
     * @throws Exception
     */
    @Override
    public String revertTranferToAccount(String strXML) throws Exception {
        OnlinePaymentController onl = new OnlinePaymentController();
        return onl.revertTranferToAccount(strXML);
    }

    /* VNPAY QR*/
    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String checkVNPAYQRCode(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.checkVNPAYQRCode(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String paymentVNPAYQR(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.paymentVNPAYQR(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }
    }

    /**
     *
     * @param requestData
     * @return
     */
    @Override
    public String transferToSCBv2(String requestData) {
        try {
            IBTController cotroller = new IBTController();
            return cotroller.transferToSCBv2(requestData);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    /**
     *
     * @param requestData
     * @return
     */
    @Override
    public String getResponseEcho(String requestData) {
        try {
            IBTController cotroller = new IBTController();
            return cotroller.getResponseEcho(requestData);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    /**
     *
     * @param content
     * @return
     */
    @Override
    public String MmsTransferAccToAcc(String content) {
        OnlinePaymentController onl = new OnlinePaymentController();
        return onl.MmsTransferAccToAcc(content);
    }

    /**
     *
     * @param usename
     * @param cifno
     * @param passInput
     * @param typeCheck
     * @return
     */
    @Override
    public boolean checkAuthenPassMB(String usename, String cifno, String passInput, String typeCheck) {
        MobileUpgradeController mb = new MobileUpgradeController();
        try {
            return mb.checkAuthenPassMB(usename, cifno, passInput, typeCheck);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return false;
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String getSCBBranch(String xml) {
        Object object = XMLUtils.unMarshaller(GetSCBBranchRq.class, xml);
        if (object == null) {
            GetSCBBranchRp info = new GetSCBBranchRp(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
            return XMLUtils.Marshaller(info);
        }

        GetSCBBranchRq req = (GetSCBBranchRq) object;
        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            GetSCBBranchRp info = new GetSCBBranchRp(ResponseCodeEnum.OUTOFTIME.getText(), ResponseCodeEnum.OUTOFTIME.name());
            return XMLUtils.Marshaller(info);
        }

        boolean canAccess = SiUtils.canAccess(req.getProviderId(), req.getValueToBuildMac(), req.getSignature());
        if (!canAccess) {
            GetSCBBranchRp info = new GetSCBBranchRp(ResponseCodeEnum.MACISERROR.getText(), ResponseCodeEnum.MACISERROR.name());
            return XMLUtils.Marshaller(info);
        }

        OnlinePaymentController onl = new OnlinePaymentController();
        return onl.getSCBBranch(req);
    }

    /**
     *
     * @param usename
     * @param cifno
     * @param passInput
     * @param typeCheck
     * @return
     */
    @Override
    public boolean checkAuthenPassMBNew(String usename, String cifno, String passInput, String typeCheck) {
        MobileUpgradeController mb = new MobileUpgradeController();
        try {
            return mb.checkAuthenPassMBNew(usename, cifno, passInput, typeCheck);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return false;
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String validateCMND(String xml) {
        Object object = XMLUtils.unMarshaller(ValidateCmndReq.class, xml);
        if (object == null) {
            ValidateCmndRes info = new ValidateCmndRes(CIMSResultEnum.INVALIDVALUE.getText());
            return XMLUtils.Marshaller(info);
        }

        ValidateCmndReq req = (ValidateCmndReq) object;
        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            ValidateCmndRes info = new ValidateCmndRes(req, CIMSResultEnum.OUTOFTIME.getText());
            return XMLUtils.Marshaller(info);
        }

        boolean canAccess = CimsUtils.canAccess(req.getPartner(), req.getValueToBuildMac(), req.getMac());
        if (!canAccess) {
            ValidateCmndRes info = new ValidateCmndRes(req, CIMSResultEnum.MACISERROR.getText());
            return XMLUtils.Marshaller(info);
        }

        CIMSController cIMSController = new CIMSController();
        return cIMSController.validateCMND(req);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String khoaThe(String xml) {
        Object object = XMLUtils.unMarshaller(KhoaTheReq.class, xml);
        if (object == null) {
            KhoaTheRes info = new KhoaTheRes(CIMSResultEnum.INVALIDVALUE.getText());
            return XMLUtils.Marshaller(info);
        }

        KhoaTheReq req = (KhoaTheReq) object;
        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            KhoaTheRes info = new KhoaTheRes(req, CIMSResultEnum.OUTOFTIME.getText());
            return XMLUtils.Marshaller(info);
        }

        boolean canAccess = CimsUtils.canAccess(req.getPartner(), req.getValueToBuildMac(), req.getMac());
        if (!canAccess) {
            KhoaTheRes info = new KhoaTheRes(req, CIMSResultEnum.MACISERROR.getText());
            return XMLUtils.Marshaller(info);
        }

        CIMSController cIMSController = new CIMSController();
        return cIMSController.khoaThe(req);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String sendEmail(String xml) {
        Object object = XMLUtils.unMarshaller(SendEmailReq.class, xml);
        if (object == null) {
            SendEmailRes info = new SendEmailRes(EmailResultEnum.INVALIDVALUE.getText());
            return XMLUtils.Marshaller(info);
        }

        SendEmailReq req = (SendEmailReq) object;
        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            SendEmailRes info = new SendEmailRes(req, EmailResultEnum.OUTOFTIME.getText());
            return XMLUtils.Marshaller(info);
        }

        boolean canAccess = true; //CimsUtils.canAccess(req.getPartner(), req.getValueToBuildMac(), req.getMac());
        if (!canAccess) {
            SendEmailRes info = new SendEmailRes(req, EmailResultEnum.MACISERROR.getText());
            return XMLUtils.Marshaller(info);
        }

        boolean result = EmailUtils.sendEmail(req);
        SendEmailRes info = new SendEmailRes(req, result ? EmailResultEnum.SUCCESS.getText() : EmailResultEnum.FAILTOSENDEMAIL.getText());
        return XMLUtils.Marshaller(info);
    }

    //DOI SOAT NAPAS (E)
    /**
     *
     * @param cifno
     * @param refCore
     * @return
     */
    @Override
    public String sendEmailBaoLong(String cifno, String refCore) {
        try {
            InsuranceController insurance = new InsuranceController();
            return insurance.sendEmailBaoLong(cifno, refCore);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    /**
     *
     * @param serial
     * @param actionType
     * @return
     */
    @Override
    public String checkSerialToken(String serial, String actionType) {
        Tokenkey token = new Tokenkey();
        return token.checkSerialToken(serial, actionType);
    }

    /**
     *
     * @param typeDest
     * @param accountTo
     * @param amount
     * @param narative
     * @return
     */
    @Override
    public String IBTFccTransfer(String typeDest, String accountTo,
            BigDecimal amount, String narative) {

        try {
            Helper.writeLogErrorNonDB(ControllerImpl.class, "HACH TOAN IBT THU HUONG CHO ACCOUNT:" + accountTo + " LOAI TK:" + typeDest);
            String PAN = accountTo;
            String productname = "";
            if (typeDest.equals("01") || typeDest.equals("02") || typeDest.equals("04") || typeDest.equals("14")) {
                productname = Configuration.getProperty("fcubs.smartlink.product");
            }

            //Thuc hien cat tien Napas
            Fcubs fcubs = new Fcubs();
            String accountFrom = Configuration.getProperty("napas.ibt.account.master");
            String userid = Configuration.getProperty("fcubs.userid");
            String brnAccountTo = "";
            String refcore = "";
            if (typeDest.equals("01") || typeDest.equals("04") || typeDest.equals("14") || typeDest.equals("02")) {
                brnAccountTo = CommonUtils.getBranchAccount(accountTo);

                refcore = fcubs.transferFCUBSWithTimeOut(productname, userid, "000", accountFrom, brnAccountTo, accountTo, amount, narative, 30);
                Helper.writeLogErrorNonDB(ControllerImpl.class, "KET QUA HACH TOAN IBT THU HUONG CHO ACCOUNT:" + accountTo + " LOAI TK:" + typeDest + " MA DON VI:" + brnAccountTo + " REFCODE:" + refcore);
                if (refcore == null) {
                    // HACH TOAN QUA CORE KHONG THANH CONG
                    return "01"; // giao dich that bai
                }
                if (refcore.equals("TIMEOUT")) {
                    //HACH TOAN QUA CORE TIMEOUT, TRA LOI GIAO DICH THAI BAI
                    return "03"; // giao dich timeout cho doi soat
                }
            } else {
                String LOC = PAN.substring(0, 12);
                String TAIL = PAN.substring(16);
                String[] resultCard = Helper.getDBI().getDetailCard(LOC, TAIL);
                String ExpireDate = resultCard[0];
                brnAccountTo = resultCard[1];
                //call cardword
                CardwordController cw = new CardwordController();
                // Thanh toan IPP
                BigDecimal debtPay;
                BigDecimal debtCur;
                String PMT_INFO = Helper.getDBI().queryContactCenterInfo("getPMT_INFO", LOC);
                if (PMT_INFO == null || PMT_INFO.isEmpty()) {
                    debtPay = BigDecimal.ZERO;
                    debtCur = BigDecimal.ZERO;
                } else {
                    //debtPay --> Dư nợ trả góp phải thanh toán
                    //debtCur -->Dư nợ trả góp đến hiện tại                  
                    debtPay = new BigDecimal(PMT_INFO.split("#")[0]);
                    debtCur = new BigDecimal(PMT_INFO.split("#")[1]);
                }

                String result = cw.cardReloadWithNarative(accountFrom, PAN, ExpireDate, "FB",
                        brnAccountTo, "VND", amount, debtPay, debtCur, "0", narative);
                Helper.writeLogErrorNonDB(ControllerImpl.class, "KET QUA HACH TOAN IBT THU HUONG CHO PAN:" + accountTo + " LOAI THE:" + typeDest + " MA DON VI:" + brnAccountTo + " KETQUA:" + result);
                if (result == null) {
                    return "99";
                }
                return result;
            }
            Helper.writeLogErrorNonDB(ControllerImpl.class, "GIAO DICH IBT CHIEU NHAN THANH CONG:" + accountTo + " LOAI TK:" + typeDest + " REF:" + refcore);
            return "00#" + refcore;

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "01";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String getStatusOfTransferMoney(String xml) {
        Object object = XMLUtils.unMarshaller(GetStatusOfTransferMoneyReq.class, xml);
        if (object == null) {
            GetStatusOfTransferMoneyRes info = new GetStatusOfTransferMoneyRes(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
            return XMLUtils.Marshaller(info);
        }
        GetStatusOfTransferMoneyReq req = (GetStatusOfTransferMoneyReq) object;
        boolean inValidValue = true;
        if ("WU".equals(req.getProviderId()) || "RIA".equals(req.getProviderId())) {
            /*Thay doi Transsaction -> Transaction*/
            inValidValue = req.isInvalidValueV2();
        } else {
            inValidValue = req.isInvalidValue();
        }
        if (inValidValue) {
            GetStatusOfTransferMoneyRes info = new GetStatusOfTransferMoneyRes(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
            return XMLUtils.Marshaller(info);
        }
        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            GetStatusOfTransferMoneyRes info = new GetStatusOfTransferMoneyRes(ResponseCodeEnum.OUTOFTIME.getText(), ResponseCodeEnum.OUTOFTIME.name());
            return XMLUtils.Marshaller(info);
        }
        boolean canAccess = true;
        if ("WU".equals(req.getProviderId()) || "RIA".equals(req.getProviderId())) {
            /*Thay doi Transsaction -> Transaction nen doi sang build chu ky version 2*/
            SiUtils.canAccess(req.getProviderId(), req.getValueToBuildMacV2(), req.getSignature());
        } else {
            SiUtils.canAccess(req.getProviderId(), req.getValueToBuildMac(), req.getSignature());
        }
        if (!canAccess) {
            GetStatusOfTransferMoneyRes info = new GetStatusOfTransferMoneyRes(ResponseCodeEnum.MACISERROR.getText(), ResponseCodeEnum.MACISERROR.name());
            return XMLUtils.Marshaller(info);
        }

        SIController si = new SIController();
        return si.checkStatusTransferMoney(req);
    }

    @Override
    public String getInfoRegister(String xml) {
        Object object = XMLUtils.unMarshaller(RegisterInfoReq.class, xml);
        if (object == null) {
            RegisterInfoRes info = new RegisterInfoRes(CIMSResultEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
            return XMLUtils.Marshaller(info);
        }
        RegisterInfoReq req = (RegisterInfoReq) object;
        boolean inValidValue = req.isInvalidValue();
        if (inValidValue) {
            RegisterInfoRes info = new RegisterInfoRes(CIMSResultEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
            return XMLUtils.Marshaller(info);
        }
        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            RegisterInfoRes info = new RegisterInfoRes(CIMSResultEnum.OUTOFTIME.getText(), ResponseCodeEnum.OUTOFTIME.name());
            return XMLUtils.Marshaller(info);
        }
        boolean canAccess = CimsUtils.canAccess(req.getPartnerCode(), req.getValueToBuildMac(), req.getSignature());
        if (!canAccess) {
            RegisterInfoRes info = new RegisterInfoRes(CIMSResultEnum.MACISERROR.getText(), ResponseCodeEnum.MACISERROR.name());
            return XMLUtils.Marshaller(info);
        }
        CIMSController cims = new CIMSController();
        return cims.getInfoRegister(req);
    }

    @Override
    public String getReceiveFeedback(String xml) {
        Object object = XMLUtils.unMarshaller(ReceiveFeedbackReq.class, xml);
        if (object == null) {
            ReceiveFeedbackRes info = new ReceiveFeedbackRes(CIMSResultEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
            return XMLUtils.Marshaller(info);
        }
        ReceiveFeedbackReq req = (ReceiveFeedbackReq) object;
        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            ReceiveFeedbackRes info = new ReceiveFeedbackRes(CIMSResultEnum.OUTOFTIME.getText(), ResponseCodeEnum.OUTOFTIME.name());
            return XMLUtils.Marshaller(info);
        }
        boolean canAccess = CimsUtils.canAccess(req.getPartnerCode(), req.getValueToBuildMac(), req.getSignature());
        if (!canAccess) {
            ReceiveFeedbackRes info = new ReceiveFeedbackRes(CIMSResultEnum.MACISERROR.getText(), ResponseCodeEnum.MACISERROR.name());
            return XMLUtils.Marshaller(info);
        }
        CIMSController cims = new CIMSController();
        return cims.getReceiveFeedback(req);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String transferMoney(String xml) {
        Object object = XMLUtils.unMarshaller(TransferMoneyReq.class, xml);
        if (object == null) {
            TransferMoneyRes info = new TransferMoneyRes(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
            return XMLUtils.Marshaller(info);
        }

        TransferMoneyReq req = (TransferMoneyReq) object;
        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            TransferMoneyRes info = new TransferMoneyRes(ResponseCodeEnum.OUTOFTIME.getText(), ResponseCodeEnum.OUTOFTIME.name());
            return XMLUtils.Marshaller(info);
        }

        boolean canAccess = SiUtils.canAccess(req.getProviderId(), req.getValueToBuildMac(), req.getSignature());
        if (!canAccess) {
            TransferMoneyRes info = new TransferMoneyRes(ResponseCodeEnum.MACISERROR.getText(), ResponseCodeEnum.MACISERROR.name());
            return XMLUtils.Marshaller(info);
        }

        if (!"001".equals(req.getMsgId())) {
            TransferMoneyRes info = new TransferMoneyRes(ResponseCodeEnum.MSGIDISWRONG.getText(), ResponseCodeEnum.MSGIDISWRONG.name());
            return XMLUtils.Marshaller(info);
        }

        SIController si = new SIController();
        /*Neu doi tac co truyen cac tham so sender thi goi sang ham chuyen khoan co sender*/
        List<String> listPartner = new ArrayList<>();
        
        String[] _partnerKieuHoi = Configuration.getProperty("ws.partner.kieuhoi").split(";");
        Collections.addAll(listPartner, _partnerKieuHoi);
        if (listPartner.contains(req.getProviderId())) {
            return si.transferMoneyWithSender(req);
        } else {
            return si.transferMoney(req);
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String transferMoney247(String xml) {
        Object object = XMLUtils.unMarshaller(TransferMoney247Req.class, xml);
        if (object == null) {
            TransferMoney247Res info = new TransferMoney247Res(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
            return XMLUtils.Marshaller(info);
        }

        TransferMoney247Req req = (TransferMoney247Req) object;
        if (req.isInvalidValue()) {
            /* Kiem tra xem co can thiet hong. Neu bo duoc thi bo */
            TransferMoney247Res info = new TransferMoney247Res(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());

            /*Test for RIA*/
            if (req.getProviderId().equals("RIA") && req.isInvalidValue()) {
                info = new TransferMoney247Res(ResponseCodeEnum.INVALIDRECEIVINGINFO.getText(), ResponseCodeEnum.INVALIDRECEIVINGINFO.name());
            }
            return XMLUtils.Marshaller(info);
        }

        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            TransferMoney247Res info = new TransferMoney247Res(ResponseCodeEnum.OUTOFTIME.getText(), ResponseCodeEnum.OUTOFTIME.name());
            return XMLUtils.Marshaller(info);
        }

        boolean canAccess = SiUtils.canAccess(req.getProviderId(), req.getValueToBuildMac(), req.getMac());
        if (!canAccess) {
            TransferMoney247Res info = new TransferMoney247Res(ResponseCodeEnum.MACISERROR.getText(), ResponseCodeEnum.MACISERROR.name());
            return XMLUtils.Marshaller(info);
        }

        if (!"QUE".equals(req.getMsgId()) && !"TRN".equals(req.getMsgId())) {
            TransferMoney247Res info = new TransferMoney247Res(ResponseCodeEnum.MSGIDISWRONG.getText(), ResponseCodeEnum.MSGIDISWRONG.name());
            return XMLUtils.Marshaller(info);
        }

        SIController si = new SIController();
        /*Neu doi tac co truyen cac tham so sender thi goi sang ham chuyen khoan co sender*/
        List<String> listPartner = new ArrayList<>();
        String[] _partnerKieuHoi = Configuration.getProperty("ws.partner.kieuhoi").split(";");
        Collections.addAll(listPartner, _partnerKieuHoi);
        if (listPartner.contains(req.getProviderId())) {
            return si.transferMoney247WithSender(req);
        } else {
            return si.transferMoney247(req);
        }
    }

    /**
     *
     * @param FromNumber
     * @param TypeFromNumber
     * @param FullName
     * @param ToNumber
     * @param TypeToNumber
     * @param BenID
     * @param Amount
     * @param CCY
     * @param channel
     * @param Desc
     * @param TermId
     * @param CardAcceptor
     * @param TypeFunction
     * @param hidenCard
     * @return
     */
    @Override
    public String SMLTransfeFromSCBv2(String FromNumber, String TypeFromNumber, String FullName, String ToNumber, String TypeToNumber,
            String BenID, BigDecimal Amount,
            String CCY, String channel, String Desc,
            String TermId, String CardAcceptor, String TypeFunction, String hidenCard) {
        try {
            IBTController controller = new IBTController();
            return controller.transfeFromSCBv2(FromNumber, TypeFromNumber, FullName, ToNumber, TypeToNumber, BenID, Amount, CCY, channel, Desc, TermId, CardAcceptor, TypeFunction);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "99";
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String SendEmailOpenTD(String xml) {
        MobileControlExt mb = new MobileControlExt();
        return mb.sendEmailOpenTD(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GetSendEmailOpenTDList(String xml) {
        MobileControlExt mb = new MobileControlExt();
        return mb.GetSendEmailOpenTDList(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String UpdateCustomerInfo(String xml) {
        MobileControlExt mb = new MobileControlExt();
        return mb.UpdateCustomerInfo(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String InterestRateSlab(String xml) {
        MobileControlExt mb = new MobileControlExt();
        return mb.InterestRateSlab(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GrantLuckNumber(String xml) {
        MobileControlExt mb = new MobileControlExt();
        return mb.GrantLuckNumber(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String kichHoatThe(String xml) {
        Object object = XMLUtils.unMarshaller(KichHoatTheReq.class, xml);
        if (object == null) {
            KichHoatTheRes info = new KichHoatTheRes(CIMSResultEnum.INVALIDVALUE.getText());
            return XMLUtils.Marshaller(info);
        }

        KichHoatTheReq req = (KichHoatTheReq) object;
        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            KichHoatTheRes info = new KichHoatTheRes(req, CIMSResultEnum.OUTOFTIME.getText());
            return XMLUtils.Marshaller(info);
        }

        boolean canAccess = CimsUtils.canAccess(req.getPartner(), req.getValueToBuildMac(), req.getMac());
        if (!canAccess) {
            KichHoatTheRes info = new KichHoatTheRes(req, CIMSResultEnum.MACISERROR.getText());
            return XMLUtils.Marshaller(info);
        }

        CIMSController cIMSController = new CIMSController();
        return cIMSController.kichHoatThe(req);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String isExistUserBanking(String xml) {
        MobileController mb = new MobileController();
        return mb.isExistUserBanking(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String CasaRegister(String xml) {
        MobileControlExt mb = new MobileControlExt();
        return mb.CasaRegister(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    @Override
    public String GuaranteeRegister(String xml) {
        MobileControlExt mb = new MobileControlExt();
        return mb.GuaranteeRegister(xml);
    }

    /**
     *
     * @param xml
     * @return
     */
    public String cardAdjustment(String xml) {
        CardwordController cardwordController = new CardwordController();
        return cardwordController.cardAdjustment(xml);
    }

    @Override
    public String SMSBDSDList(String xml) {
        MobileControlExt mb = new MobileControlExt();
        return mb.SMSBDSDList(xml);
    }

    @Override
    public String RegisterSMSBDSD(String xml) {
        MobileControlExt mb = new MobileControlExt();
        return mb.RegisterSMSBDSD(xml);
    }

    @Override
    public String transactionHistoryByDesc(String xml) throws Exception {
        AccountController controler = new AccountController();
        return controler.transactionHistoryByDesc(xml);
    }

    @Override
    public void insertReponseIBT(String trace, String reponse) throws Exception {
        IBTHmap ibt = new IBTHmap();
        ibt.insertReponseIBT(trace, reponse);
    }

    @Override
    public String getRequestTraceIBT() throws Exception {
        IBTHmap ibt = new IBTHmap();
        return ibt.getRequestTraceIBT();
    }

    @Override
    public void insertRequestIBT(String trace) throws Exception {
        IBTHmap ibt = new IBTHmap();
        ibt.putRequestIBT(trace);
    }

    @Override
    public String getReponseIBT(String trace) throws Exception {
        IBTHmap ibt = new IBTHmap();
        return ibt.getReponseIBT(trace);
    }

    @Override
    public String checkCustInfo(String inputXml) {
        OnlinePaymentController onl = new OnlinePaymentController();
        return onl.checkCustInfo(inputXml);
    }

    @Override
    public String PreChangePin(String xml) {
        try {
            MobileUpgradeController web = new MobileUpgradeController();
            return web.PreChangePin(xml);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return "";
        }

    }

    @Override
    public String PaymentInsuranceCashv2(String CusAccount, BigDecimal totalamount, String user_maker, String user_checker, String pol_num, String idchannel, String noiCap, String ngayCap, String sodt, String nguoiGiaoDich, String diaChi, String cmnd) {
        try {
            InsPaymentUtil ipu = new InsPaymentUtil();
            return ipu.PaymentInsuranceCashv2(CusAccount, totalamount, user_maker, user_checker, pol_num, idchannel, noiCap, ngayCap, sodt, nguoiGiaoDich, diaChi, cmnd);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    @Override
    public String QRPaymentService(String xmlRequest) {
        try {
            QRPaymentController qr = new QRPaymentController();
            return qr.QRPaymentService(xmlRequest);
        } catch (Exception ex) {

            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    @Override
    public String CreateBLHealthCareContract(String xml) {
        try {
            BHBLCustomerContractController ins = new BHBLCustomerContractController();
            return ins.CreateBLHealthCareContract(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return "";
        }
    }

    @Override
    public String GetBLPackageCost(String xml) {
        try {
            BHBLCustomerContractController ins = new BHBLCustomerContractController();
            return ins.getBLPackageCost(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     * GetBLCategories
     *
     * @param xml
     * @return
     */
    @Override
    public String GetBLCategories(String xml) {
        try {
            BHBLCustomerContractController ins = new BHBLCustomerContractController();
            return ins.GetBLCategories(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     * GetBLAllQuestions
     *
     * @param xml
     * @return
     */
    @Override
    public String GetBLAllQuestions(String xml) {
        try {
            BHBLCustomerContractController ins = new BHBLCustomerContractController();
            return ins.getBLAllQuestions(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     * GetBLHealthCareContract
     *
     * @param xml
     * @return
     */
    @Override
    public String GetBLHealthCareContract(String xml) {
        try {
            BHBLCustomerContractController ins = new BHBLCustomerContractController();
            String rs = ins.GetBLHealthCareContract(xml);
            return rs;
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     * PaymentHealCareBL
     *
     * @param xml
     * @return
     */
    @Override
    public String GetUrlOnepayBL(String xml) {
        try {
            BHBLCustomerContractController ins = new BHBLCustomerContractController();
            return ins.GetUrlOnepayBL(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    @Override
    public String UpdatePaymentStatus(String xml) {
        try {
            BHBLCustomerContractController ins = new BHBLCustomerContractController();
            return ins.UpdatePaymentStatus(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    @Override
    public String revertPayment(String strXML) {
        try {
            OnlinePaymentController onl = new OnlinePaymentController();
            return onl.revertPayment(strXML);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    @Override
    public String sendSMSFromPartner(String xml) throws Exception {
        sms_partner_response rp = new sms_partner_response();
        try {
            Object object = XMLUtils.unMarshaller(sms_partner_request.class, xml);
            if (object == null) {
                rp.setResponsecode("96");
                return XMLUtils.Marshaller(rp);
            }
            sms_partner_request rq = (sms_partner_request) object;
            //check MAC
            String[] items = Helper.getDBI().ONL_GetMACkeys(rq.getPartnerid());
            String value;
            String md5Key;
            if (items.length > 0) {
                md5Key = items[0];
                value = rq.getPartnerid() + rq.getServicecode() + rq.getRequestid() + rq.getPhoneno();
                String macScb = ControllerUtil.EncriptMD5(value + md5Key);
                if (!macScb.equalsIgnoreCase(rq.getMac())) {
                    rp.setResponsecode("98");
                    return XMLUtils.Marshaller(rp);
                }
            } else {
                rp.setResponsecode("94");
                return XMLUtils.Marshaller(rp);
            }
            String[] resultInsert = Helper.getDBI().INSERT_SMS_PARTNER(rq);
            String responsecode = resultInsert[0];
            switch (responsecode) {
                case "1":
                    responsecode = "00";
                case "01":
                    responsecode = "95";
                case "02":
                    responsecode = "02";
                default:
                    responsecode = "01";
            }
            rp.setResponsecode(responsecode);
            rp.setPartnerid(rq.getPartnerid());
            rp.setServicecode(rq.getServicecode());
            rp.setServicetype(rq.getServicetype());
            rp.setRequestid(rq.getRequestid());
            String rpmac = ControllerUtil.EncriptMD5(value + responsecode + md5Key);
            rp.setMac(rpmac);
        } catch (Exception ex) {
            rp.setResponsecode("99");
            Helper.writeLogError(this.getClass(), ex);
        }

        return XMLUtils.Marshaller(rp);
    }

    @Override
    public String getDataCollated(String xml) {
        Object object = XMLUtils.unMarshaller(OneInventoryRequest.class, xml);
        if (object == null) {
            OneInventoryResponse res = new OneInventoryResponse(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
            return XMLUtils.Marshaller(res);
        }
        OneInventoryRequest req = (OneInventoryRequest) object;
        boolean isValid = req.isValid();
//        if (!isValid) {
//            OneInventoryResponse res = new OneInventoryResponse(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
//            return XMLUtils.Marshaller(res);
//        }
        boolean canAccess = SiUtils.canAccess(req.getProviderId(), req.getValueBuildMac(), req.getSignature());
        if (!canAccess) {
            OneInventoryResponse res = new OneInventoryResponse(ResponseCodeEnum.MACISERROR.getText(), ResponseCodeEnum.MACISERROR.name());
            return XMLUtils.Marshaller(res);
        }
        SIController si = new SIController();
        return si.getDataCollated(req);
    }

    /*
    *Doi tac dang su dung: Western Union (WU)
    * dang test tren 73.20 
     */
    @Override
    public String transferMoney247InternalApp(String xml) {

        try {
            Object object = XMLUtils.unMarshaller(Transfer247Request.class, xml);
            if (object == null) {
                Transfer247Response info = new Transfer247Response(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
                return XMLUtils.Marshaller(info);
            }
            Transfer247Request req = (Transfer247Request) object;
            if (req.isInvalidValue()) {
                /* Kiem tra xem co can thiet hong. Neu bo duoc thi bo */
                Transfer247Response info = new Transfer247Response(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
                return XMLUtils.Marshaller(info);
            }
            boolean outOfTime = req.isOutOfTime();
            if (outOfTime) {
                Transfer247Response info = new Transfer247Response(ResponseCodeEnum.OUTOFTIME.getText(), ResponseCodeEnum.OUTOFTIME.name());
                return XMLUtils.Marshaller(info);
            }

            if (!"QUE".equals(req.getTypeFunction()) && !"TRN".equals(req.getTypeFunction())) {
                TransferMoney247Res info = new TransferMoney247Res(ResponseCodeEnum.MSGIDISWRONG.getText(), ResponseCodeEnum.MSGIDISWRONG.name());
                return XMLUtils.Marshaller(info);
            }
            IBTController ibt = new IBTController();
            return ibt.Transfer247(xml);
        } catch (Exception e) {
            Helper.writeLogError(ControllerImpl.class, e);
            return null;
        }
    }

    /*
    *Doi tac dang su dung: Western Union (WU)
    * dang test tren 73.20 
     */
    @Override
    public String getListTransactionByDate(String xml) {
        Object object = XMLUtils.unMarshaller(GetListTransactionByDateRequest.class, xml);
        if (object == null) {
            GetListTransactionByDateResponse info = new GetListTransactionByDateResponse(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
            return XMLUtils.Marshaller(info);
        }
        GetListTransactionByDateRequest req = (GetListTransactionByDateRequest) object;
        boolean isValid = req.isValid();
        if (!isValid) {
            Transfer247Response info = new Transfer247Response(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
            return XMLUtils.Marshaller(info);
        }
        /*so sanh startDateTime va endDateTime*/
        boolean compareDateTime = req.compareDateTime();
        if (!compareDateTime) {
            Transfer247Response info = new Transfer247Response(ResponseCodeEnum.INVALIDVALUE.getText(), ResponseCodeEnum.INVALIDVALUE.name());
            return XMLUtils.Marshaller(info);
        }
        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            GetListTransactionByDateResponse info = new GetListTransactionByDateResponse(ResponseCodeEnum.OUTOFTIME.getText(), ResponseCodeEnum.OUTOFTIME.name());
            return XMLUtils.Marshaller(info);
        }

        boolean canAccess = true;//SiUtils.canAccess(req.getProviderId(), req.getValueToBuildMac(), req.getMac());
        if (!canAccess) {
            GetListTransactionByDateResponse info = new GetListTransactionByDateResponse(ResponseCodeEnum.MACISERROR.getText(), ResponseCodeEnum.MACISERROR.name());
            return XMLUtils.Marshaller(info);
        }
        SIController si = new SIController();
        return si.getListTransactionByDate(req);
    }

    @Override
    public String GetStatusUserOdbx(String xml) {
        try {
            UserOdbxController us = new UserOdbxController();
            String statusUser = us.GetStatusUserOdbx(xml);

            return statusUser;
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    @Override
    public String GetBankCardDetail(String xml) {
        try {
            
            BHBLCustomerContractController mb = new BHBLCustomerContractController();
            String result = mb.firebaseSendMsg(xml);
            
            /*
            MobileController mb = new MobileController();
            String result = mb.GetBankCardDetail(xml);
            */
            return result;
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }
    @Override
    public String transferMoney247_Ebanking(String request) {
        try {
            Gson gson = new Gson();
            TransferMoney247EbankReq req = gson.fromJson(request, TransferMoney247EbankReq.class);
            SIController si = new SIController();
            return si.transferMoney247_Ebanking(req);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     * MoThe
     *
     * @param xml
     * @return
     */
    @Override
    public String MoKhoaThe(String xml) {
        Object object = XMLUtils.unMarshaller(MoKhoaTheReq.class, xml);
        if (object == null) {
            MoKhoaTheRes info = new MoKhoaTheRes(CIMSResultEnum.INVALIDVALUE.getText());
            return XMLUtils.Marshaller(info);
        }
        MoKhoaTheReq req = (MoKhoaTheReq) object;
        boolean outOfTime = req.isOutOfTime();

        if (outOfTime) {
            MoKhoaTheRes info = new MoKhoaTheRes(req, CIMSResultEnum.OUTOFTIME.getText());
            return XMLUtils.Marshaller(info);
        }
        boolean canAccess = CimsUtils.canAccess(req.getPartner(), req.getValueToBuildMac(), req.getMac());

        if (!canAccess) {
            MoKhoaTheRes info = new MoKhoaTheRes(req, CIMSResultEnum.MACISERROR.getText());
            return XMLUtils.Marshaller(info);
        }
        CIMSController cIMSController = new CIMSController();

        return cIMSController.MoKhoaTheHotline(req);
    }
    
    
    /**
     * MoThe
     *
     * @param xml
     * @return
     */
    @Override
    public String MoKhoaTheCRM(String xml) {
        Object object = XMLUtils.unMarshaller(MoKhoaTheReq.class, xml);
        if (object == null) {
            MoKhoaTheRes info = new MoKhoaTheRes(CIMSResultEnum.INVALIDVALUE.getText());
            return XMLUtils.Marshaller(info);
        }
        MoKhoaTheReq req = (MoKhoaTheReq) object;
        boolean outOfTime = req.isOutOfTime();

        if (outOfTime) {
            MoKhoaTheRes info = new MoKhoaTheRes(req, CIMSResultEnum.OUTOFTIME.getText());
            return XMLUtils.Marshaller(info);
        }
        boolean canAccess = CimsUtils.canAccess(req.getPartner(), req.getValueToBuildMac(), req.getMac());

        if (!canAccess) {
            MoKhoaTheRes info = new MoKhoaTheRes(req, CIMSResultEnum.MACISERROR.getText());
            return XMLUtils.Marshaller(info);
        }
        CIMSController cIMSController = new CIMSController();

        return cIMSController.MoKhoaTheCRM(req);
    }
    
     /**
     *KhoaThe tren crm
     * 
     * @param xml
     * @return
     */
    @Override
    public String KhoaTheCRM(String xml) {
        Object object = XMLUtils.unMarshaller(KhoaTheReq.class, xml);
        if (object == null) {
            KhoaTheRes info = new KhoaTheRes(CIMSResultEnum.INVALIDVALUE.getText());
            return XMLUtils.Marshaller(info);
        }

        KhoaTheReq req = (KhoaTheReq) object;
        boolean outOfTime = req.isOutOfTime();
        if (outOfTime) {
            KhoaTheRes info = new KhoaTheRes(req, CIMSResultEnum.OUTOFTIME.getText());
            return XMLUtils.Marshaller(info);
        }

        boolean canAccess = CimsUtils.canAccess(req.getPartner(), req.getValueToBuildMac(), req.getMac());
        if (!canAccess) {
            KhoaTheRes info = new KhoaTheRes(req, CIMSResultEnum.MACISERROR.getText());
            return XMLUtils.Marshaller(info);
        }

        CIMSController cIMSController = new CIMSController();
        return cIMSController.KhoaTheCRM(req);
    }
    

    /**
     *
     * @param xml
     * @return
     */
    public String CC_PhongToaTKTT(String xml) {
        //PhongToaTKTTRq req = (PhongToaTKTTRq) XMLUtils.unMarshaller(PhongToaTKTTRq.class, xml);
        try {
            Object object = XMLUtils.unMarshaller(PhongToaTKTTReq.class, xml);
            if (object == null) {
                PhongToaTKTTRes info = new PhongToaTKTTRes(CIMSResultEnum.INVALIDVALUE.getText(), "INVALIDVALUE");
                return XMLUtils.Marshaller(info);
            }
            PhongToaTKTTReq req = (PhongToaTKTTReq) object;
            boolean outOfTime = req.isOutOfTime();
            if (outOfTime) {
                PhongToaTKTTRes info = new PhongToaTKTTRes(req, CIMSResultEnum.OUTOFTIME.getText(), "OUTOFTIME");
                return XMLUtils.Marshaller(info);
            }
            boolean canAccess = CimsUtils.canAccess(req.getPartner(), req.getValueToBuildMac(), req.getMac());

            if (!canAccess) {
                PhongToaTKTTRes info = new PhongToaTKTTRes(req, CIMSResultEnum.MACISERROR.getText(), "MACISERROR");
                return XMLUtils.Marshaller(info);
            }

            ContactCenter cc = new ContactCenter();
            return cc.CC_PhongToaTKTT(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String CC_GiaiToaTKTT(String xml) {
        try {
            Object object = XMLUtils.unMarshaller(GiaiToaTKTTReq.class, xml);
            if (object == null) {
                GiaiToaTKTTRes info = new GiaiToaTKTTRes(CIMSResultEnum.INVALIDVALUE.getText(), "INVALIDVALUE");
                return XMLUtils.Marshaller(info);
            }
            GiaiToaTKTTReq req = (GiaiToaTKTTReq) object;
            boolean outOfTime = req.isOutOfTime();
            if (outOfTime) {
                GiaiToaTKTTRes info = new GiaiToaTKTTRes(req, CIMSResultEnum.OUTOFTIME.getText(), "OUTOFTIME");
                return XMLUtils.Marshaller(info);
            }
            boolean canAccess = CimsUtils.canAccess(req.getPartner(), req.getValueToBuildMac(), req.getMac());

            if (!canAccess) {
                GiaiToaTKTTRes info = new GiaiToaTKTTRes(req, CIMSResultEnum.MACISERROR.getText(), "MACISERROR");
                return XMLUtils.Marshaller(info);
            }
            
            ContactCenter cc = new ContactCenter();
            return cc.CC_GiaiToaTKTT(xml);
        } catch (Exception ex) {
            Helper.writeLogError(this.getClass(), ex);
            return null;
        }
    }

    /**
     * *mobile banking
     *
     * @param xml
     * @return
     */
    @Override
    public String GetCustomerInfo2345(String xml) {
        MobileController mb = new MobileController();
        return mb.GetCustomerInfo2345(xml);
    }
    
}
