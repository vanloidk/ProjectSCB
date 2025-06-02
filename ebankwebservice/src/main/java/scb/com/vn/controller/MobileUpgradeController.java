/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import com.google.gson.Gson;
import scb.com.vn.dbi.dto.GiftDetail;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Level;
import scb.com.vn.dbi.dto.CardType;
import scb.com.vn.dbi.dto.EbIssuecard;
import scb.com.vn.dbi.dto.GifCustomerDetail;
import scb.com.vn.dbi.dto.Insurance;
import scb.com.vn.dbi.dto.PblAutoReg;
import scb.com.vn.dbi.dto.PblProvider;
import scb.com.vn.dbi.dto.PblServicetype;
import scb.com.vn.dbi.dto.ProcedureCallDto;
import scb.com.vn.message.Message;
import scb.com.vn.message.Message.PaymentResultEnum;
import scb.com.vn.utility.Helper;
import scb.com.vn.mobile.model.*;
import scb.com.vn.model.AccountDetail;
import scb.com.vn.model.AccountInfoRp;
import scb.com.vn.model.AccountInfoRq;
import scb.com.vn.model.BillProviderDetail;
import scb.com.vn.model.CardDetail;
import scb.com.vn.model.InsPaymentDetailRp;
import scb.com.vn.model.InsPaymentRp;
import scb.com.vn.model.InsuranceRp;
import scb.com.vn.model.TxnCommitRp;
import scb.com.vn.payment.SIController;

import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.MessageMB;
import scb.com.vn.utility.MessageMB.MobileResultEnum;
import vn.com.scb.bian.*;
import scb.com.vn.dbi.dto.VwMbCustomer;
import java.sql.Timestamp;

import scb.com.vn.common.model.masterpass.PayByQRCodeRp;
import scb.com.vn.common.model.masterpass.PayByQRCodeRq;
import scb.com.vn.message.CardwordMessage.ErrorCodeEnum;
import scb.com.vn.message.CommonMessage.CommontEnum;
import scb.com.vn.message.MastercardMessage.ReasonCodeEnum;
import scb.com.vn.model.CheckOpenOnlineAzRp;
import scb.com.vn.model.CheckOpenOnlineAzRq;
import scb.com.vn.model.RequestChangePINCard;
import scb.com.vn.model.ResponseChangePINCard;
import scb.com.vn.utility.CommonUtils;

import vn.com.scb.bian.service.*;
import vn.com.scb.bian.utility.IIBConstants;
import java.text.DecimalFormat;
import my.com.webservices.CardActivationRespBean;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cims.kht.KichHoatTheInfo;
import scb.com.vn.common.model.cw.SenderMSVSCardInfo;
import scb.com.vn.common.model.cw.changepin.InitSessionToChangepinJsonRq;
import scb.com.vn.common.model.cw.changepin.InitSessionToChangepinJsonRs;
import scb.com.vn.common.model.cw.changepin.InitSessionToChangepinRq;
import scb.com.vn.common.model.cw.changepin.InitSessionToChangepinRs;
import scb.com.vn.common.model.masterpass.MasterCardQrActionEnum;
import scb.com.vn.common.model.mvisa.MVISAQRRP;
import scb.com.vn.common.model.mvisa.MVISAQRRQ;
import scb.com.vn.common.model.vnpayqr.CheckQRRp;
import scb.com.vn.common.model.vnpayqr.CheckQRRq;
import scb.com.vn.common.model.vnpayqr.PaymentQRJson;
import scb.com.vn.common.model.vnpayqr.PaymentQRRp;
import scb.com.vn.common.model.vnpayqr.PaymentQRRq;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.enumUtils.CIMSResultEnum;
import scb.com.vn.message.VisaMessage;
import scb.com.vn.payment.ControllerUtil;
import scb.com.vn.ultility.AES;
//import scb.com.vn.utility.EncryptDecrypt;
//import static scb.com.vn.utility.MessageMB.MobileResultEnum.NOTSUCCESS_REGISTER_AUTOBILL;

/**
 *
 * @author kimncm
 */
public class MobileUpgradeController {

    private static final Logger LOGGER = Logger.getLogger(MobileUpgradeController.class);

    final int _SUCESSFUL = 0, _UNSUCESSFUL = -1;
    final String DB_SUCESSS_MESSAGE = "1";
    final String ACCOUNT_CASA = "U", ACCOUNT_TD = "Y", ACCOUNT_LOAN = "L", ACCOUNT_MASTERCARD = "M",
            ACCOUNT_MASTERCARD_MC = "MC", ACCOUNT_MASTERCARD_MD = "MD";
    final String PHI_XEMAY = "XEMAY", PHI_AUTO = "AUTO", PHI_TAINAN24H = "TAINAN24H", PHI_NHATUNHAN = "NHATUNHAN";
    final String MAKEID_DVKH = Configuration.getProperty("fcubs.userid.makerid.dvkh");
    String useridMobile = Configuration.getProperty("fcubs.userid.mobile");
    final String ID_CHANNEL_MOBILE = "03";
    final String FIRST_MASTERCARD_MC = "8", FIRST_MASTERCARD_MD = "2";
    final String STATUS_CARD_HOATDONG = "O", STATUS_CARD_TAMKHOA = "L", STATUS_CARD_CHUAACTIVE = "D",
            STATUS_CARD_CLOSE = "C", STATUS_CARD_EXPIRED = "E";
    private final String IIB_CHANNEL_MOBILE = "MOBILE";

    /* CHANGE REWARD POINT */
    private final String MASTER = "MASTERCARD";
    private final String VISA = "VISA CARD";
    private final String CARD_TYPE_C = "CLASSIC CARD";
    private final String CARD_TYPE_G = "GOLD";
    private final String CARD_TYPE_P = "PLATINUM";
    private final String CARD_TYPE_W = "WORLD";
    final String producttransferMobileOut = Configuration.getProperty("fcubs.producttransfer.mobile.OUT");
    final String producttransferMobileIDRT = Configuration.getProperty("fcubs.producttransfer.mobile.ID.RT");
    String ProductFromSCB_MOBILE = Configuration.getProperty("fcubs.producttransfer.mobile.ibt");

    String productMobileCard = Configuration.getProperty("fcubs.producttransfer.mobile.Card");
    String GLPaymentCard = Configuration.getProperty("fcubs.GLPayment.Card");
    String myEncryptionKey = Configuration.getProperty("myEncryptionKey.mobile");
    String CHANGEPIN_URL = Configuration.getProperty("cw.changepin.url");
    String CHANGEPIN_USER = Configuration.getProperty("cw.changepin.user");
    String CHANGEPIN_PASS = Configuration.getProperty("cw.changepin.pass");
    String CHANGEPIN_VNPAYPARTNER = Configuration.getProperty("cw.changepin.VNPAY");
    String CHANGEPIN_KEYMD5 = Configuration.getProperty("cw.changepin.keyMd5");
    //String myEncryptionKey = "Scb@123456789123456789123456789";
    String MaturityInstr_AutoRoll_Both = "1";
    String MaturityInstr_AutoRoll_Principal = "2";
    String MaturityInstr_Close = "3";
    String MaturityInstr_AutoRoll_Special = "4";
    String MaturityInstr_Close_Principal = "5";
    String AccClassLuong = "CAI012";

    private final String MASTER_ACCOUNT_DEBIT = "459906055";
    private final String MASTER_DEBIT_PRODUCT = "MC23";
    private final String MASTER_DEBIT_REFUND_PRODUCT = "MC24";

    private final String VISA_ACCOUNT_DEBIT = "459906063";
    private final String VISA_DEBIT_PRODUCT = "VS16";
    private final String VISA_DEBIT_REFUND_PRODUCT = "VS17";
    final String productgetfreeVETC = Configuration.getProperty("fcubs.getfree.mobile.VETC");
    final String KHT_FAILED = "1";

    public enum PREM_TYPE {
        U("Phí định kỳ với sản phẩm UL007"), I("Phí đầu tiên"), P("Phí bảo hiểm định kỳ"), L("Trả vay/Tạm ứng");
        private final String name;

        private PREM_TYPE(String s) {
            name = s;
        }
    }

    public enum PREM_TYPE_ENG {
        U("Periodic  fee UL007"), I("Initial Fee"), P("Periodic premium"), L("Loan/Advance Payment");
        private final String name;

        private PREM_TYPE_ENG(String s) {
            name = s;
        }
    }

    /*
    public enum CARD_TYPE_ENUM {
      O("The Dang Hoat Dong"), L("The Tam Khoa"),D("The Chua Active"), C("The Het Han"),E("The Dong")
    }
     */
    public enum CARD_TYPE_NAME {
        O("Thẻ đang hoạt động"), L("Thẻ tạm khóa"), D("Thẻ chưa kích hoạt"), C("Thẻ đóng"), E("Thẻ hết hạn");
        public final String name;

        private CARD_TYPE_NAME(String s) {
            name = s;
        }

    }

    public enum CARD_TYPE_NAME_ENG {
        O("Active"), L("Locked"), D("Inactive"), C("Closed"), E("Expired");
        public final String name_eng;

        private CARD_TYPE_NAME_ENG(String s) {
            name_eng = s;
        }

    }

    /**
     * Lay ten tai khoan chung khoan
     *
     * @param xml
     * @return Ten tai khoan chung khoan
     */
    public String GetSecuritiesAccountName(String xml) {
        try {
            GetSecuritiesAccountNameRq req = (GetSecuritiesAccountNameRq) Xml.unMarshaller(GetSecuritiesAccountNameRq.class, xml);
            GetSecuritiesAccountNameRp response = new GetSecuritiesAccountNameRp();

            BeanUtils.copyProperties(response, req);

            SIController sicontrol = new SIController();
            String result = sicontrol.getSIName(req.getToAccount());
            String resultArr[] = result.split("#");
            if (resultArr.length > 0) {
                if (resultArr[0].equals("00")) {
                    response.setBeneName(resultArr[1]);
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
                } else if (resultArr[0].equals("99")) {
                    return Xml.Marshaller(response.getMBResponse(Message.PaymentResultEnum.ACCNO_NOT_FOUND));
                } else {
                    Helper.writeLogError(MobileUpgradeController.class, "GetSecuritiesAccountName - " + resultArr);
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                }
            }
            Helper.writeLogError(MobileUpgradeController.class, "GetSecuritiesAccountName - " + resultArr);
            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     * Lay danh sach cong ty chung khoan
     *
     * @param xml
     * @return
     */
    public String GetSecuritiesPartner(String xml) {
        try {
            GetSecuritiesPartnerRq req = (GetSecuritiesPartnerRq) Xml.unMarshaller(GetSecuritiesPartnerRq.class, xml);
            GetSecuritiesPartnerRp response = new GetSecuritiesPartnerRp();

            req.setIdservicetype("TVSI");

            /*
            List billProviderList = Helper.getDBI().getProviderByIdServiceType(req.getIdservicetype());
            response.setLanguage(req.getLanguage());
            if (billProviderList == null || billProviderList.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(Message.PaymentResultEnum.PROVIDERCODE_NOTEXSIST));
            } else {
                BillProviderDetail[] providerList = new BillProviderDetail[billProviderList.size()];
                if (req.getLanguage() == null || req.getLanguage().isEmpty() || req.getLanguage().equals("VN")) {
                    for (int i = 0; i < billProviderList.size(); i++) {
                        providerList[i] = new BillProviderDetail();
                        BeanUtils.copyProperties(providerList[i], billProviderList.get(i));
                    }
                } else if (req.getLanguage().equals("EN")) {
                    for (int i = 0; i < billProviderList.size(); i++) {
                        HashMap hm_Provider = (HashMap) billProviderList.get(i);
                        providerList[i] = new BillProviderDetail();
                        providerList[i].setIdprovider(hm_Provider.get("idprovider").toString());//accountLoan.getLoaitien());
                        providerList[i].setProvidername(hm_Provider.get("providername_eng").toString());
                    }
                }

                response.setListBillProvider(providerList);
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
            }*/
            ArrayList billProviderList = Helper.getDBI().getAllListPartnerFI();
            response.setLanguage(req.getLanguage());
            if (billProviderList == null || billProviderList.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(Message.PaymentResultEnum.PROVIDERCODE_NOTEXSIST));
            } else {
                //BillProviderDetail[] providerList = new BillProviderDetail[billProviderList.size()];
                //do add nham MNL
                BillProviderDetail[] providerList = new BillProviderDetail[1];
                if (req.getLanguage() == null || req.getLanguage().isEmpty() || req.getLanguage().equals("VN")) {
                    for (int i = 0; i < billProviderList.size(); i++) {
                        HashMap hm_Provider = (HashMap) billProviderList.get(i);
                        if (hm_Provider.get("idpartner").toString().equals("TVSI")) {
                            providerList[0] = new BillProviderDetail();
                            providerList[0].setIdprovider(hm_Provider.get("idpartner").toString());//accountLoan.getLoaitien());
                            providerList[0].setProvidername(hm_Provider.get("name").toString());
                        }
                    }
                } else if (req.getLanguage().equals("EN")) {
                    for (int i = 0; i < billProviderList.size(); i++) {
                        HashMap hm_Provider = (HashMap) billProviderList.get(i);
                        if (hm_Provider.get("idpartner").toString().equals("TVSI")) {
                            providerList[0] = new BillProviderDetail();
                            providerList[0].setIdprovider(hm_Provider.get("idpartner").toString());//accountLoan.getLoaitien());
                            providerList[0].setProvidername(hm_Provider.get("name_eng").toString());
                        }
                    }
                }

                response.setListBillProvider(providerList);
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     * Nop tien vao tai khoan chung khoan
     *
     * @param xml
     * @return
     */
    public String SecuritiesPayment(String xml) {
        try {
            SecuritiesPaymentRq req = (SecuritiesPaymentRq) Xml.unMarshaller(SecuritiesPaymentRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            int isValidAuth = req.validateAuth();
            //xac thuc giao dich
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                String TxnType = req.getTxnType();
                response.setAccountNo(req.getFromAccount());
                response.setCifNo(req.getCifNo());
                response.setTxnType(TxnType);

                SIController sicontrol = new SIController();
                SimpleDateFormat s = new SimpleDateFormat("yyyyMMddhhmmss");
                String format = s.format(new Date());
                String narrative = req.getToAccount().concat("|").concat(req.getBeneName()).concat("|");
                if (req.getRemark() != null) {
                    narrative = narrative.concat(req.getRemark());
                }

                IIBCurrentAccountService iibAccService = new IIBCurrentAccountService();
                RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASA = iibAccService.retrieveCurrentAccountCASARestSimple(Configuration.getIIBContext(), req.getFromAccount(), IIB_CHANNEL_MOBILE);
                if (!retrieveCurrentAccountCASA.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }
                /*               
                VwCustAccount custacc = Helper.getDBI().getCustAccountFcdbByAccountNo(req.getFromAccount());
                
                if (custacc == null) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }
                
                String result = sicontrol.transferToSI(req.getBillProviderId(), req.getFromAccount(), custacc.getFullName(),
                        BigDecimal.valueOf(Double.valueOf(req.getAmount())), "VND", "101", format, narrative);
                 */
                //100: EBANKING; 101: MOBILEBANKING
                String clientCode = req.getClientCode();
                if (clientCode != null && clientCode.equals("EBANK")) {
                    clientCode = "100";
                } else {
                    clientCode = "101";
                }

                String result = sicontrol.transferToSI(req.getBillProviderId(), req.getFromAccount(), retrieveCurrentAccountCASA.getAccountInfo().getCustomerInfo().getFullname(),
                        BigDecimal.valueOf(Double.valueOf(req.getAmount())), "VND", clientCode, format, narrative);

                String resultArr[] = result.split("#");
                String resultcode = resultArr[0];
                if (resultcode.length() > 0) {

                    if (resultcode.equals("00")) {
                        response.setTxnID(resultArr[1]);
                        response.setTxnCommitTime(getTime());
                        return Xml.Marshaller(response.getMBFinResponse(MessageMB.MobileResultEnum.OK));
                    } else if (resultcode.equals("09")) {
                        return Xml.Marshaller(response.getMBFinResponse(Message.PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                    } else if (resultcode.equals("14")) {
                        return Xml.Marshaller(response.getMBFinResponse(MessageMB.MobileResultEnum.SECURITIES_WRONG_PARAMETER));
                    } else if (resultcode.equals("11")) {
                        return Xml.Marshaller(response.getMBFinResponse(MessageMB.MobileResultEnum.SECURITIES_NOTSUCCESS_REFUNDED));
                    } else if (resultcode.equals("12")) {
                        return Xml.Marshaller(response.getMBFinResponse(MessageMB.MobileResultEnum.SECURITIES_NOTSUCCESS_NOREFUNDED));

                    } else if (resultcode.equals("2800")) {
                        return Xml.Marshaller(response.getMBFinResponse(MessageMB.MobileResultEnum.IBT_OVERKYC));
                    } else {
                        Helper.writeLogError(MobileUpgradeController.class, "SecuritiesPayment -result " + result);
                        return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                    }
                    //return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                } else {
                    Helper.writeLogError(MobileUpgradeController.class, "SecuritiesPayment - result " + result);
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                }
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     * - Khoa / mo chuc nang thanh toan truc tuyen - Khoa / mo the Loại giao
     * dịch: CD – Thẻ; OP – Chi tiêu qua mạng ActionCode: B - Đóng, U – Mở
     *
     * @param xml
     * @return
     */
    String OpenLockCardFunc(String xml) {
        try {
            OpenLockCardFuncRq req = (OpenLockCardFuncRq) Xml.unMarshaller(OpenLockCardFuncRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            BeanUtils.copyProperties(response, req);
            if (req.getActionCodeCloseOpen() != null && (req.getActionCodeCloseOpen().equals("O") || req.getActionCodeCloseOpen().equals("A"))) {
                //xac thuc giao dich
                int isValidAuth = req.validateAuth();
                if (isValidAuth != _SUCESSFUL) {
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.get(String.valueOf(isValidAuth))));
                }
            }
            CardwordController cardControl = new CardwordController();

            String ActionType = "";
            if (req.getTxnType().equals("ONLFUNC")) {
                ActionType = "OP";
            } else if (req.getTxnType().equals("CARDFUNC")) {
                ActionType = "CD";
            }
            String ActionCode = "";
            if (req.getActionCodeCloseOpen() != null && req.getActionCodeCloseOpen().equals("O")) {
                ActionCode = "U";
            } else if (req.getActionCodeCloseOpen() != null && req.getActionCodeCloseOpen().equals("L")) {
                ActionCode = "B";
            }

            if (req.getTxnType().equals("ACTIVEBANKCARD")) {
                SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                String strDate = formater.format(date);
                KichHoatTheInfo info = new KichHoatTheInfo(String.valueOf(Helper.getDBI().getIdSeqByName("SQ_CWWSREFNO")), "MB", "-", "SCB KHT " + req.getCardNo().substring(req.getCardNo().length() - 4), formater.parse(strDate));
                
                info.setLast4Digits(req.getCardNo().substring(req.getCardNo().length() - 4));
                info.setExpiryDate(req.getExpireDate());
                info.setLoc(req.getCardAccountNumer());
                
                info.setDelivered(true);
                boolean insert = Helper.getDBI().KICHHOATTHE(info);
                
                if (!insert) {
                    LOGGER.error("Cannot insert to database");
                    response.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
                    response.setErrorMsg("Cannot insert to database.");
                    return Xml.Marshaller(response);
                }
                CardActivationRespBean cardActive = cardControl.callCwKichHoatThe02(info);
                Helper.writeLogError(MobileUpgradeController.class, "OpenLockCardFunc - result: " + cardActive.getResponseCode());
                
                info.setCusName(req.getUserName());
                info.setCif(req.getCifNo());
                info.setLoc(req.getCardAccountNumer());
                info.setCardNumber(req.getCardNo());
                info.setExpiryDate(req.getExpireDate());
                info.setSequence(String.valueOf(Helper.getDBI().getIdSeqByName("SQ_CWWSREFNO")));
                if ("000".equals(cardActive.getResponseCode())) {
                    info.setStatus(CIMSResultEnum.SUCCESS.getText());
                    info.setResponseCode(cardActive.getResponseCode());
                    info.setResponseDescription(cardActive.getResponseDescription());
                    info.setResMessage(String.format("The SCB %s da duoc kich hoat thanh cong.", info.getLast4Digits()));
                    response.setErrorCode("00");
                    response.setErrorMsg("Kich hoat the thanh cong!");
                } else {
                    info.setStatus(KHT_FAILED);
                    info.setResponseCode(cardActive.getResponseCode());
                    info.setResponseDescription(cardActive.getResponseDescription());
                    info.setResMessage(String.format("The SCB %s kich hoat khong thanh cong.", info.getLast4Digits()));
                    response.setErrorCode(cardActive.getResponseCode());
                    response.setErrorMsg(cardActive.getResponseDescription());
                }
                //update data
                boolean update = Helper.getDBI().KICHHOATTHE(info);
                if (!update) {
                    LOGGER.error("Cannot update to database!");
                    response.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
                    response.setErrorMsg("Cannot update to database!");
                    return Xml.Marshaller(response);
                }

                return Xml.Marshaller(response);
            }

            //validate the khoa tai quay ko?
            if (ActionCode.equalsIgnoreCase("U") && ActionType.equalsIgnoreCase("CD")) {
                List listCard = Helper.getDBI().getCardList(req.getAccountNo(), null, null);
                if (listCard == null || listCard.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                }
                CardDetail cardDetail = new CardDetail();
                BeanUtils.copyProperties(cardDetail, listCard.get(0));
                if (cardDetail.getReasoncde() != null && !cardDetail.getReasoncde().endsWith("TB")) {
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.CANNOT_LOCK_CARDTB));
                }
            }

            //validate the khoa tai quay ko? END
            String PANCW = req.getCardAccountNumer().concat(req.getCardNo().substring(0, 4)).concat(req.getCardNo().substring(req.getCardNo().length() - 4));

            String result = "";
            //TH kich hoat the
            if (req.getTxnType().equals("ACTIVECARD")) {
                //req.setExpiryDate(info.getExpiryDate());
                //req.setFi(CWFI);
                //req.setPan(info.getLoc() + info.getLast4Digits());
                //req.setSequenceNo(info.getTransId());                    

                KichHoatTheInfo kichoatthe = new KichHoatTheInfo(null, null, null, null, null);
                kichoatthe.setExpiryDate(req.getExpireDate());
                kichoatthe.setLoc(req.getCardAccountNumer());
                kichoatthe.setLast4Digits(req.getCardNo().substring(req.getCardNo().length() - 4));
                kichoatthe.setTransId(String.valueOf(Helper.getDBI().getIdSeqByName("SQ_CWWSREFNO")));
                boolean resultActive = cardControl.callCwKichHoatThe(kichoatthe);
                if (resultActive) {
                    result = "00";
                } else {
                    Helper.writeLogError(MobileUpgradeController.class, "OpenLockCardFunc - result: " + resultActive);
                    result = "01";
                }
            } else {
                result = cardControl.cardProfileMaintenance(PANCW, ActionType, ActionCode);
            }
            if (result.equals("00")) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
            } else {
                Helper.writeLogError(MobileUpgradeController.class, "OpenLockCardFunc - result " + result);
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                //return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OPENCLOSE_CARD_NOTSUCCESS));
            }

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     * Kiem tra thong tin khach hang truoc khi dang ky thanh toan hoa don tu
     * dong 1: kiểm tra mã hóa đơn có đăng ký ở SCB 2: check đối tác mã hóa đơn
     * hợp lệ?
     *
     * @param xml
     * @return
     */
    public String CheckRegisterAutoBill(String xml) {
        try {
            CheckRegisterAutoBillRq req = (CheckRegisterAutoBillRq) Xml.unMarshaller(CheckRegisterAutoBillRq.class, xml);
            CheckRegisterAutoBillRp response = new CheckRegisterAutoBillRp();

            BeanUtils.copyProperties(response, req);

            //1:kiem tra du lieu ma hoa da dang ky o SCB
            PblAutoReg autoreg = new PblAutoReg();
            autoreg.setCustomercode(req.getCustomerCode().toUpperCase());
            autoreg.setPblProvider(new PblProvider(req.getIdprovider()));
            autoreg.setPblServicetype(new PblServicetype(req.getIdservicetype()));
            ArrayList<PblAutoReg> _arrpar = (ArrayList<PblAutoReg>) Helper.getDBI().searchAutoReg(autoreg);
            if (!_arrpar.isEmpty()) {
                Date date = new Date();
                for (PblAutoReg par : _arrpar) {
                    if (par.getPblAutoRegContract().getActive() == 'Y' && par.getStatus() == 'Y'
                            && (par.getExpiredate() == null || par.getExpiredate().compareTo(date) > 0)) {
                        return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.VNPT_EXISTED_CUSTOMERCODE_BILL));
                        //return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
                    }
                }
            }

            //3: check ma hoa don hop le voi doi tac
            //bo ko can check
            /*
            BillPaymentRq billRequest = new BillPaymentRq();
             BeanUtils.copyProperties(billRequest, req);
             if (billRequest.getCustomerCode() != null) {
                billRequest.setCustomerCode(billRequest.getCustomerCode().trim());
            }
            MobileController.callRequestPayment(billRequest, "QUERY");
            if (!(billRequest.getResult().equals("0") || billRequest.getResult().equals(Message.PaymentResultEnum.BILL_PAID.getValue()))) {
                MobileController.insPblLog(billRequest, "Truy vấn Bill không thành công. - Lỗi giao dịch: " + Message.getMessagePaymentResult(Message.PaymentResultEnum.get(billRequest.getResult())));
                return Xml.Marshaller(response.getMBResponse(Message.PaymentResultEnum.get(billRequest.getResult())));
            } else {
                Response paymentRes = billRequest.getRespay().getResponse();

                if (req.getIdservicetype().equals(Processor.ServiceTypeEnum.DIEN.toString()) && paymentRes.getElectric() != null) {
                    response.setAddress(paymentRes.getElectric().getAddress());
                    response.setCustomername(paymentRes.getElectric().getName());
                } else {
                    response.setCustomername(paymentRes.getBilling().getCustomername());
                    response.setAddress(paymentRes.getBilling().getAddress());
                }
            }*/
            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     * Dang ky thanh toan hoa don tu dong
     *
     *     * @param xml
     * @return
     */
    public String RegisterAutoBill(String xml) {
        try {
            RegisterAutoBillRq req = (RegisterAutoBillRq) Xml.unMarshaller(RegisterAutoBillRq.class, xml);
            RegisterAutoBillRp response = new RegisterAutoBillRp();
            BeanUtils.copyProperties(response, req);
            int isValidAuth = req.validateAuth();
            //xac thuc giao dich
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                ProcedureCallDto dto = Helper.getDBI().RegisterAutoBill(req.getIdservicetype(), req.getIdprovider(), req.getCustomerCode(), req.getCifNo(), req.getUserName(), req.getMobileNumber(), req.getDebitAccount(), req.getStartDate(), req.getEnddate(), req.getPaymentType());
                if (dto.isSucess()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
                } else if (dto.getErrorStatus().equals(MobileResultEnum.NOTSUCCESS_REGISTER_AUTOBILL.getValue())) {
                    Helper.writeLog(MobileUpgradeController.class, Level.INFO, "RegisterAutoBill.ErrorStatus = " + dto.getErrorStatus());
                    Helper.writeLog(MobileUpgradeController.class, Level.INFO, "RegisterAutoBill.ErrorMessage = " + dto.getErrorMessage());
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOTSUCCESS_REGISTER_AUTOBILL));
                } else {
                    Helper.writeLog(MobileUpgradeController.class, Level.INFO, "RegisterAutoBill.ErrorStatus = " + dto.getErrorStatus());
                    Helper.writeLog(MobileUpgradeController.class, Level.INFO, "RegisterAutoBill.ErrorMessage = " + dto.getErrorMessage());
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.ERROR_SYSTEM));
                }
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     * Lay gio hien hanh
     *
     * @return
     */
    public String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }

    public String PayLoanAccount(String xml) {
        try {
            PayLoanAccountRq req = (PayLoanAccountRq) Xml.unMarshaller(PayLoanAccountRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                String TxnType = req.getTxnType();
                response.setAccountNo(req.getFromAccount());
                response.setCifNo(req.getCifNo());
                response.setTxnType(TxnType);
                //BigDecimal.valueOf(Double.valueOf(loanAccountInfo.getLoanInterestRate()))
                String retPayLoan = Helper.getDBI().PaymentLoanAccount(req.getFromAccount(), req.getToAccount(),
                        //BigDecimal.valueOf(Double.valueOf(req.getDuNoGocDenHan())),
                        //BigDecimal.valueOf(Double.valueOf(req.getLaiDenHan())),
                        // BigDecimal.valueOf(Double.valueOf(req.getPhatChamTraLai())),
                        //BigDecimal.valueOf(Double.valueOf(req.getPhatChamTraGoc())),

                        BigDecimal.valueOf(Double.valueOf(req.getPrincipalAmount())),
                        BigDecimal.valueOf(Double.valueOf(req.getInterestAmount())),
                        BigDecimal.valueOf(Double.valueOf("0")),
                        BigDecimal.valueOf(Double.valueOf("0")),
                        BigDecimal.ZERO, useridMobile, useridMobile);
                if (retPayLoan != null && retPayLoan.equals("SUCCESS")) {
                    AccountInfoRq accInforeq = new AccountInfoRq();
                    accInforeq.setAccountGroupCode(ACCOUNT_LOAN);
                    accInforeq.setAccountNo(req.getToAccount());
                    MobileController mbControl = new MobileController();
                    String accInorepStr = mbControl.GetAccountInfoNew(Xml.Marshaller(accInforeq));
                    AccountInfoRp accInorep = (AccountInfoRp) Xml.unMarshaller(AccountInfoRp.class, accInorepStr);
                    response.setDunogocconlai(accInorep.getDunogocconlai());
                    response.setPrepaidFee("0");
                } else {
                    return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.NOTSUCCESS_PAYLOAN));
                }
                return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String GetAccountListByType(String xml) {
        try {
            GetAccountListByTypeRq req = (GetAccountListByTypeRq) Xml.unMarshaller(GetAccountListByTypeRq.class, xml);
            GetAccountListByTypeRp response = new GetAccountListByTypeRp();

            String custno = req.getCifNo();
            if (req.getAccountGroupCode().equalsIgnoreCase(ACCOUNT_CASA)) {
                IIBCurrentAccountService iibAccService = new IIBCurrentAccountService();
                SelectCurrentAccountFromCIF_out_Type selectCurrentAccountFromCIF = iibAccService.selectCurrentAccountFromCIFRestSimple(Configuration.getIIBContext(), custno, IIBConstants.CHANNEL_MOBILE);
                if (!selectCurrentAccountFromCIF.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    if (selectCurrentAccountFromCIF.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_NOTEXIST)) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                    } else {
                        Helper.writeLogError(this.getClass(), "IIB - khong lay dc tai khoan ");
                        return null;
                    }
                }
                List<AccountInfoType> accountInfoList = selectCurrentAccountFromCIF.getAccountInfo();
                ArrayList<AccountDetail> accountDetailList = new ArrayList<AccountDetail>();
                for (AccountInfoType accountinfodetail : accountInfoList) {
                    AccountDetail detail = new AccountDetail();
                    detail.setAccountbalance(accountinfodetail.getAccountBalance().toPlainString());
                    detail.setAccountbalanceavaliable(accountinfodetail.getAccountBalanceAvailable().toPlainString());
                    if (accountinfodetail.getAccountBalanceAvailable().compareTo(BigDecimal.ZERO) == -1) {
                        detail.setAccountbalanceavaliable("0");
                    }
                    detail.setAccountgroupcode(accountinfodetail.getAccountType());
                    detail.setAccountgroupname(accountinfodetail.getAccountTypeName());
                    detail.setAccountccy(accountinfodetail.getAccountCurrency());
                    detail.setAccountno(accountinfodetail.getAccountNum());
                    //set branchcode va branchname
                    detail.setBranchcode(accountinfodetail.getAccountOpenBrandCode());
                    detail.setBranchname(accountinfodetail.getAccountOpenBrandName());
                    accountDetailList.add(detail);
                }
                response.setListAccount((AccountDetail[]) accountDetailList.toArray(new AccountDetail[accountDetailList.size()]));
            } else if (req.getAccountGroupCode().equalsIgnoreCase(ACCOUNT_TD)) {
                IIBDepositAccountService iibAccService = new IIBDepositAccountService();
                SelectDepositAccountFromCIF_out_Type selectDepositAccountFromCIF_out_Type = iibAccService.selectDepositAccountFromCIFRestSimple(Configuration.getIIBContext(), req.getCifNo(), IIBConstants.CHANNEL_MOBILE);
                if (!selectDepositAccountFromCIF_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                }

                List<AccountInfoType> tdAccList = selectDepositAccountFromCIF_out_Type.getAccountInfo();
                if (tdAccList == null || tdAccList.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                } else {
                    ArrayList<AccountDetail> accountDetailList = new ArrayList<AccountDetail>();
                    for (AccountInfoType accountinfodetail : tdAccList) {
                        AccountDetail detail = new AccountDetail();
                        detail.setAccountbalance(accountinfodetail.getAccountBalance().toPlainString());
                        detail.setAccountbalanceavaliable(accountinfodetail.getAccountBalanceAvailable().toPlainString());
                        detail.setAccountgroupcode(accountinfodetail.getAccountType());
                        detail.setAccountgroupname(accountinfodetail.getAccountTypeName());
                        detail.setAccountccy(accountinfodetail.getAccountCurrency());
                        detail.setAccountno(accountinfodetail.getAccountNum());
                        detail.setMaturitydate(changeFormatDate(accountinfodetail.getAccountMaturityDate()));
                        accountDetailList.add(detail);
                    }
                    response.setListAccount((AccountDetail[]) accountDetailList.toArray(new AccountDetail[accountDetailList.size()]));
                }
            } else if (req.getAccountGroupCode().equalsIgnoreCase(ACCOUNT_LOAN)) {
                IIBLoanAccountService iibAccService = new IIBLoanAccountService();
                SelectLoanFromCIF_out_Type selectLoanFromCIF = iibAccService.selectLoanFromCIFRestSimple(Configuration.getIIBContext(), req.getCifNo(), IIBConstants.CHANNEL_MOBILE);
                if (!selectLoanFromCIF.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                }
                List<LoanAccountInfoType> loanAccList = selectLoanFromCIF.getLoanAccountInfo();
                ArrayList<AccountDetail> accountDetailList = new ArrayList<AccountDetail>();
                for (LoanAccountInfoType accountinfodetail : loanAccList) {
                    AccountDetail detail = new AccountDetail();
                    detail.setAccountbalance(String.valueOf(accountinfodetail.getLoanOutstandingBalance()));
                    detail.setAccountbalanceavaliable(String.valueOf(accountinfodetail.getLoanOutstandingBalance()));
                    detail.setAccountgroupcode(ACCOUNT_LOAN);
                    detail.setAccountgroupname("TIEN VAY");
                    detail.setAccountccy(accountinfodetail.getLoanCurrency());
                    detail.setAccountno(accountinfodetail.getLoanAccountNum());
                    accountDetailList.add(detail);
                }
                response.setListAccount((AccountDetail[]) accountDetailList.toArray(new AccountDetail[accountDetailList.size()]));
            } else if (req.getAccountGroupCode().equalsIgnoreCase(ACCOUNT_MASTERCARD)) {
                IIBCardService iibCardService = new IIBCardService();
                SelectCreditCardSumary_out_Type selectCreditCardSumary = iibCardService.selectCreditCardSumarySimple(Configuration.getIIBContext(), req.getCifNo(), null, IIBConstants.CHANNEL_MOBILE);
                if (!selectCreditCardSumary.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                }
                List<CardInfoType> cardList = selectCreditCardSumary.getCardInfo();
                ArrayList<AccountDetail> accountDetailList = new ArrayList<AccountDetail>();
                for (CardInfoType carddetail : cardList) {
                    AccountDetail detail = new AccountDetail();
                    //detail.setAccountbalance(String.valueOf(carddetail.getCardOutstandingAmountNotUnpost()));
                    detail.setAccountbalanceavaliable(String.valueOf(carddetail.getCardLimit()));

                    BigDecimal DuNoTheDenHienTai = BigDecimal.valueOf(Double.valueOf(carddetail.getCardOutstandingAmountNotUnpost()));
                    BigDecimal DuNoTraGopDenHienTai = BigDecimal.valueOf(Double.valueOf(carddetail.getCardIPPCurrentAmount()));
                    BigDecimal SumDuNoDenHienTai = DuNoTheDenHienTai.add(DuNoTraGopDenHienTai);
                    detail.setAccountbalance(SumDuNoDenHienTai.toPlainString());

                    if (carddetail.getCardAccountNum().substring(0, 1).equals(FIRST_MASTERCARD_MC)) {
                        detail.setAccountgroupcode(ACCOUNT_MASTERCARD_MC);
                        detail.setAccountgroupname("THE CREDIT");
                    } else {
                        detail.setAccountgroupcode(ACCOUNT_MASTERCARD_MD);
                        detail.setAccountgroupname("THE DEDIT");
                    }
                    detail.setAccountccy("VND");
                    detail.setAccountno(carddetail.getCardPaneID());
                    detail.setCardaccountno(carddetail.getCardAccountNum());
                    detail.setCardno(carddetail.getHiddenCardNum());
                    detail.setCardNoHidden(carddetail.getHiddenCardNum());
                    if (!"VC".equals(carddetail.getCardImageId())) {
                        detail.setImageCard("N"); // the vat ly
                        if (carddetail.getCardStatus() != null && "The Chua Active".equals(carddetail.getCardStatus().trim())) {
                            if (carddetail.getHiddenCardNum() != null && carddetail.getHiddenCardNum().length() > 1) {
                                String subCardNo = carddetail.getHiddenCardNum().substring(carddetail.getHiddenCardNum().length() - 2, carddetail.getHiddenCardNum().length());
                                detail.setCardNoHidden("XXXXXXXXXXXXXX".concat(subCardNo));
                            }
                        }
                    } else {
                        detail.setImageCard("VC");//the phi vat ly
                    }
                    detail.setBranchcode(carddetail.getCardOpenBranchCode());
                    detail.setCardDescription(carddetail.getCardType());
                    detail.setCardStatus(carddetail.getCardStatus());
                    detail.setCreditType(carddetail.getCardBrand());
                    accountDetailList.add(detail);
                }
                response.setListAccount((AccountDetail[]) accountDetailList.toArray(new AccountDetail[accountDetailList.size()]));
            }/*
             else {
             listAccount = Helper.getDBI().GetAccountListByType(custno, req.getAccountGroupCode());
             if (listAccount == null || listAccount.isEmpty()) {
             return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
             }
             AccountDetail[] accountDetails = new AccountDetail[listAccount.size()];
             for (int i = 0; i < listAccount.size(); i++) {
             accountDetails[i] = new AccountDetail();
             BeanUtils.copyProperties(accountDetails[i], listAccount.get(i));
             }
             response.setListAccount(accountDetails);
             }*/

            response.setAccountGroupCode(req.getAccountGroupCode());
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    // chi dung cho masterpass
    public String GetCardListByType(String xml) {
        try {
            GetCardListByTypeRq req = (GetCardListByTypeRq) Xml.unMarshaller(GetCardListByTypeRq.class, xml);
            GetCardListByTypeRp response = new GetCardListByTypeRp();

            IIBCardService iibCardService = new IIBCardService();
            String creditType = "";
            String cardGroupCode = "";
            if (req.getCardGroupCode() != null && !req.getCardGroupCode().isEmpty()) {
                cardGroupCode = req.getCardGroupCode();
            }
            if (req.getCreditType() != null && !req.getCreditType().isEmpty()) {
                creditType = req.getCreditType();
            }
            SelectCreditCardSumary_out_Type selectCreditCardSumary = iibCardService.selectCreditCardSumarySimple(Configuration.getIIBContext(), req.getCifNo(), null, IIBConstants.CHANNEL_MOBILE);
            if (!selectCreditCardSumary.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            }
            List<CardInfoType> cardList = selectCreditCardSumary.getCardInfo();
            ArrayList<AccountDetail> accountDetailList = new ArrayList<AccountDetail>();
            for (CardInfoType carddetail : cardList) {
                if (carddetail.getCIFInfo().getCIFNum().equalsIgnoreCase(req.getCifNo())) {
                    AccountDetail detail = new AccountDetail();
                    detail.setAccountbalance(String.valueOf(carddetail.getCardOutstandingAmount()));
                    //detail.setAccountbalanceavaliable(String.valueOf(carddetail.getCardLimit()));

                    BigDecimal CardOutstandingAmount = BigDecimal.valueOf(Double.valueOf(carddetail.getCardOutstandingAmount()));
                    BigDecimal CardLimit = BigDecimal.valueOf(Double.valueOf(carddetail.getCardLimit()));
                    BigDecimal DuNoTraGopDenHienTai = BigDecimal.valueOf(Double.valueOf(carddetail.getCardIPPCurrentAmount()));
                    BigDecimal CardCurrentLimit = CardLimit.subtract(CardOutstandingAmount).subtract(DuNoTraGopDenHienTai);
                    //substract đi dư nơ thẻ
                    //BigDecimal CardCurrentLimit = CardLimit.subtract(CardOutstandingAmount);
                    detail.setAccountbalanceavaliable(CardCurrentLimit.toPlainString());
                    if (carddetail.getCardAccountNum().substring(0, 1).equals(FIRST_MASTERCARD_MC)) {
                        detail.setAccountgroupcode(ACCOUNT_MASTERCARD_MC);
                        detail.setAccountgroupname("THE CREDIT");
                    } else {
                        detail.setAccountgroupcode(ACCOUNT_MASTERCARD_MD);
                        detail.setAccountgroupname("THE DEDIT");
                    }

                    detail.setAccountccy("VND");
                    detail.setAccountno(carddetail.getCardPaneID());
                    detail.setCardaccountno(carddetail.getCardAccountNum());
                    detail.setCardno(carddetail.getHiddenCardNum());
                    detail.setBranchcode(carddetail.getCardOpenBranchCode());
                    detail.setCreditType(carddetail.getCardBrand());

                    String cifOfCard = carddetail.getCIFInfo().getCIFNum();
                    if (carddetail.getCardStatus().equals("The Dang Hoat Dong") && req.getCifNo().equals(cifOfCard)) {

                        if (cardGroupCode.isEmpty()) {
                            accountDetailList.add(detail);
                        } else {
                            if (detail.getAccountgroupcode().equals(cardGroupCode)) {
                                if (creditType.isEmpty()) {
                                    accountDetailList.add(detail);
                                } else {
                                    if (detail.getCreditType().equals(creditType)) {
                                        accountDetailList.add(detail);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            response.setCardGroupCode(req.getCardGroupCode());

            if (accountDetailList.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            }
            response.setListAccount((AccountDetail[]) accountDetailList.toArray(new AccountDetail[accountDetailList.size()]));

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
    public String GetAccountListByTypeOLD(String xml) {
        try {
            GetAccountListByTypeRq req = (GetAccountListByTypeRq) Xml.unMarshaller(GetAccountListByTypeRq.class, xml);
            GetAccountListByTypeRp response = new GetAccountListByTypeRp();

            String custno = req.getCifNo();
            List listAccount = null;

            listAccount = Helper.getDBI().GetAccountListByType(custno, req.getAccountGroupCode());
            if (listAccount == null || listAccount.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            }
            AccountDetail[] accountDetails = new AccountDetail[listAccount.size()];
            for (int i = 0; i < listAccount.size(); i++) {
                accountDetails[i] = new AccountDetail();
                
                BeanUtils.copyProperties(accountDetails[i], listAccount.get(i));

                accountDetails[i].setMaturitydate(changeFormatDate(accountDetails[i].getMaturitydate()));
            }
            response.setListAccount(accountDetails);

            response.setAccountGroupCode(req.getAccountGroupCode());
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
}*/
    public String GetOnlineInsTypeList(String xml) {
        try {
            GetOnlineInsTypeListRq req = (GetOnlineInsTypeListRq) Xml.unMarshaller(GetOnlineInsTypeListRq.class, xml);
            GetOnlineInsTypeListRp response = new GetOnlineInsTypeListRp();

            List listtype = Helper.getDBI().laydanhdachsanphambaohiem();
            if (listtype == null || listtype.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }

            InsOnlineProduct[] productlist = new InsOnlineProduct[listtype.size()];
            for (int i = 0; i < listtype.size(); i++) {
                productlist[i] = new InsOnlineProduct();
                BeanUtils.copyProperties(productlist[i], listtype.get(i));

            }
            response.setInsOnlineProductList(productlist);
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     * *
     *
     * @param xml
     * @return
     */
    public String GetOnlineInsInfo(String xml) {
        try {
            GetOnlineInsInfoRq req = (GetOnlineInsInfoRq) Xml.unMarshaller(GetOnlineInsInfoRq.class, xml);
            GetOnlineInsInfoRp response = new GetOnlineInsInfoRp();

            //response.setIDSANPHAM(req.getIDSANPHAM());
            BeanUtils.copyProperties(response, req);
            List listtype = Helper.getDBI().chitietsanphambaohiem(req.getIDSANPHAM());
            if (listtype == null || listtype.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }

            if (req.getIDSANPHAM().equals(PHI_XEMAY)) {
                for (int i = 0; i < listtype.size(); i++) {
                    HashMap hm_detail = (HashMap) listtype.get(i);
                    if (hm_detail.get("idsanpham").toString().equals(PHI_XEMAY)) {
                        if (hm_detail.get("loaiphi").toString().equals("HINHTHUC")) {
                            if (hm_detail.get("tenphi").toString().equals(req.getLOAIPHI())) {
                                response.setFORMAMT(hm_detail.get("sotienphi1").toString());
                            }
                        } else if (hm_detail.get("loaiphi").toString().equals("QUYENLOI")) {
                            if (hm_detail.get("tenphi").toString().equals(req.getTENQUYENLOI())) {
                                response.setDUTYAMT(hm_detail.get("sotienphi1").toString());
                                response.setDiscount(hm_detail.get("phantram").toString());
                            }
                        }
                    }
                }
            } else if (req.getIDSANPHAM().equals(PHI_AUTO)) {
                for (int i = 0; i < listtype.size(); i++) {
                    HashMap hm_detail = (HashMap) listtype.get(i);
                    if (hm_detail.get("idsanpham").toString().equals(PHI_AUTO)) {
                        if (hm_detail.get("loaiphi").toString().equals("HINHTHUC")) {
                            if (hm_detail.get("tenphi").toString().equals(req.getLOAIPHI())) {
                                response.setFORMAMT(hm_detail.get("sotienphi1").toString());
                            }
                        } else if (hm_detail.get("loaiphi").toString().equals("QUYENLOI")) {
                            if (hm_detail.get("tenphi").toString().equals(req.getTENQUYENLOI())) {
                                BigDecimal dutyamt = BigDecimal.valueOf(Double.valueOf(hm_detail.get("sotienphi1").toString()));
                                dutyamt = dutyamt.multiply(BigDecimal.valueOf(Double.valueOf(req.getLOAIPHI())));                               
                                //response.setDUTYAMT(dutyamt.toString());
                                //fix so tien duty baotbq - 2023
                                DecimalFormat df = new DecimalFormat("#");
                                String dutyamtformat = df.format(dutyamt);
                                Helper.writeLogError(this.getClass(), "dutyamtformat: " + dutyamtformat);
                                response.setDUTYAMT(dutyamtformat);
                                response.setDiscount(hm_detail.get("phantram").toString());
                            }
                        }
                    }
                }
            } else if (req.getIDSANPHAM().equals(PHI_TAINAN24H)) {
                response.setFORMAMT("0");
                for (int i = 0; i < listtype.size(); i++) {
                    HashMap hm_detail = (HashMap) listtype.get(i);
                    if (hm_detail.get("idsanpham").toString().equals(PHI_TAINAN24H)) {
                        if (hm_detail.get("loaiphi").toString().equals(req.getLOAIQUYENLOI()) && hm_detail.get("tenphi").toString().equals(req.getTENQUYENLOI())) {
                            response.setDUTYAMT(hm_detail.get("sotienphi1").toString());
                            response.setDiscount(hm_detail.get("phantram").toString());
                        }
                    }
                }
            } else if (req.getIDSANPHAM().equals(PHI_NHATUNHAN)) {
                response.setFORMAMT("0");

                for (int i = 0; i < listtype.size(); i++) {
                    HashMap hm_detail = (HashMap) listtype.get(i);
                    if (hm_detail.get("idsanpham").toString().equals(PHI_NHATUNHAN)) {
                        if (hm_detail.get("loaiphi").toString().equals(req.getLOAIQUYENLOI()) && hm_detail.get("tenphi").toString().equals(req.getTENQUYENLOI())) {
                            response.setDUTYAMT(hm_detail.get("sotienphi1").toString());
                            response.setDiscount(hm_detail.get("phantram").toString());
                        }
                    }
                }
            }
            // tinh tong phi: formamt +  dutyamt*discount
            BigDecimal formamt = BigDecimal.valueOf(Double.valueOf(response.getFORMAMT()));
            BigDecimal discount = new BigDecimal(1).subtract(BigDecimal.valueOf(Double.valueOf(response.getDiscount())).divide(new BigDecimal(100)));
            BigDecimal dutyamt = BigDecimal.valueOf(Double.valueOf(response.getDUTYAMT())).multiply(discount);
            BigDecimal sumPay = formamt.add(dutyamt);

            response.setDiscount(response.getDiscount());
            response.setPayAmt(sumPay.toString());
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String GetOnlineInsFormList(String xml) {
        try {
            GetOnlineInsFormListRq req = (GetOnlineInsFormListRq) Xml.unMarshaller(GetOnlineInsFormListRq.class, xml);
            GetOnlineInsFormListRp response = new GetOnlineInsFormListRp();

            response.setIDSANPHAM(req.getIDSANPHAM());
            List listtype = Helper.getDBI().chitietsanphambaohiem(req.getIDSANPHAM());
            if (listtype == null || listtype.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }

            ArrayList<FormDetail> formlist = new ArrayList<FormDetail>();

            String discount = "";

            if (req.getIDSANPHAM().equals(PHI_NHATUNHAN)) {
                FormDetail formdetail1 = new FormDetail();
                formdetail1.setLoaiphi("QUYENLOIKINHDOANH");
                formdetail1.setTenphi("Bảo vệ cơ bản (cháy, nổ, sét đánh).");
                formlist.add(formdetail1);
                //
                FormDetail formdetail2 = new FormDetail();
                formdetail2.setLoaiphi("QUYENLOIVANPHONG");
                formdetail2.setTenphi("Bảo vệ toàn diện.");
                formlist.add(formdetail2);
            } else {
                for (int i = 0; i < listtype.size(); i++) {
                    HashMap hm_detail = (HashMap) listtype.get(i);
                    if (hm_detail.get("loaiphi").toString().equals("HINHTHUC")) {
                        if (hm_detail.get("idsanpham").toString().equals(req.getIDSANPHAM())) {
                            FormDetail formdetail = new FormDetail();
                            //formdetail.setLoaiphi(hm_detail.get("loaiphi").toString());                            
                            formdetail.setLoaiphi(hm_detail.get("tenphi").toString());
                            if (req.getIDSANPHAM().equals(PHI_AUTO)) {
                                formdetail.setTenphi(hm_detail.get("tenphi").toString() + " chỗ");
                            } else {
                                formdetail.setTenphi(hm_detail.get("tenphi").toString());
                            }
                            //formdetail.setFormamt(hm_detail.get("sotienphi1").toString());
                            //discount = hm_detail.get("phantram").toString();
                            formlist.add(formdetail);
                        }
                    }
                }
            }
            response.setFormList((FormDetail[]) formlist.toArray(new FormDetail[formlist.size()]));
            response.setDiscount(discount);
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String GetOnlineInsDutyList(String xml) {
        try {
            GetOnlineInsDutyListRq req = (GetOnlineInsDutyListRq) Xml.unMarshaller(GetOnlineInsDutyListRq.class, xml);
            GetOnlineInsDutyListRp response = new GetOnlineInsDutyListRp();

            response.setIDSANPHAM(req.getIDSANPHAM());
            response.setLOAIPHI(req.getLOAIPHI());
            List listtype = Helper.getDBI().chitietsanphambaohiem(req.getIDSANPHAM());
            if (listtype == null || listtype.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }

            ArrayList<DutyDetail> dutylist = new ArrayList<DutyDetail>();

            for (int i = 0; i < listtype.size(); i++) {
                HashMap hm_detail = (HashMap) listtype.get(i);
                if (hm_detail.get("loaiphi").toString().contains("QUYENLOI")) {
                    if (req.getIDSANPHAM().equals(PHI_NHATUNHAN)) {
                        if (hm_detail.get("loaiphi").toString().equals(req.getLOAIPHI())) {
                            DutyDetail dutydetail = new DutyDetail();
                            dutydetail.setLoaiquyenloi(hm_detail.get("loaiphi").toString());
                            dutydetail.setTenquyenloi(hm_detail.get("tenphi").toString());
                            //dutydetail.setDutyamt(hm_detail.get("sotienphi1").toString());
                            //dutydetail.setDiscount(hm_detail.get("phantram").toString());
                            dutylist.add(dutydetail);
                        }
                    } else {
                        if (hm_detail.get("idsanpham").toString().equals(req.getIDSANPHAM())) {
                            DutyDetail dutydetail = new DutyDetail();
                            dutydetail.setLoaiquyenloi(hm_detail.get("loaiphi").toString());
                            dutydetail.setTenquyenloi(hm_detail.get("tenphi").toString());
                            //dutydetail.setDutyamt(hm_detail.get("sotienphi1").toString());
                            //dutydetail.setDiscount(hm_detail.get("phantram").toString());
                            dutylist.add(dutydetail);
                        }
                    }
                }
            }
            response.setDutyList((DutyDetail[]) dutylist.toArray(new DutyDetail[dutylist.size()]));
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String OnlineInsBuy(String xml) {
        try {
            OnlineInsBuyRq req = (OnlineInsBuyRq) Xml.unMarshaller(OnlineInsBuyRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            int isValidAuth = req.validateAuth();
            response.setCifNo(req.getCifNo());
            String TxnType = req.getTxnType();
            response.setTxnType(TxnType);
            response.setAccountNo(req.getAccIdaccount());

            //xac thuc giao dich
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {

                InsuranceController inscontroller = new InsuranceController();
                InsuranceRp insRp = new InsuranceRp();
                BeanUtils.copyProperties(insRp, req);
                //fix temp for mobile android 
                //<TENQUYENLOI>10.000.000 VND</TENQUYENLOI>
                //--> <TENQUYENLOI>10000000</TENQUYENLOI>
                try {
                    String FeeName = insRp.getFeeName().replaceAll("[\\< VND>]", "").replaceAll("\\.", "");
                    Helper.writeLog(this.getClass(),Level.INFO, "FeeName: "+ FeeName);
                    insRp.setSotien_bhtn(Long.valueOf(FeeName));
                    insRp.setFeeName(FeeName);
                } catch (Exception e) {
                    Helper.writeLogError(this.getClass(), e);
                }

                //set some value default before 
                DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                insRp.setIdPartner("BAOLONG");
                insRp.setBcAccount("1560101266370001");
                insRp.setIdDiscount(1);
                insRp.setChannel("MB");
                insRp.setRegDate(formatter.parse(formatter.format(new Date()).toString()));
                //kimncm - 26-feb-2019 MB ko phan biet hoa thuong
                req.setUserName(req.getUserName().toLowerCase());

                List mbMobile = Helper.getDBI().getCustomerFromMobile(req.getUserName());

                if (mbMobile == null || mbMobile.isEmpty()) {
                    Helper.writeLogError(ExchangePaybill.class, "***- ERROR MB (FUNCTION transferPaybill): " + "not get customerid from DB VNINFO ");
                }
                HashMap hm_MB = (HashMap) mbMobile.get(0);
                insRp.setIdchanneluserMaker(hm_MB.get("cus_id").toString());
                insRp.setIdchanneluserChecker(hm_MB.get("cus_id").toString());

                if (req.getNGAYSINH() != null && req.getNGAYSINH().length() > 0) {
                    insRp.setDob(formatter.parse(req.getNGAYSINH()));
                }
                if (req.getNgayHieuLuc() != null && req.getNgayHieuLuc().length() > 0) {
                    insRp.setValidDate(formatter.parse(req.getNgayHieuLuc()));
                }
                Helper.writeLog(this.getClass(),Level.INFO, "insRp: "+ Xml.Marshaller(insRp));
                String result = inscontroller.InsurancePayment(Xml.Marshaller(insRp));
                String resultArr[] = result.split("#");
                String resultcode = resultArr[0];

                if (resultcode.length() > 0) {
                    if (resultcode.equals("00")) {
                        response.setTxnID(resultArr[1]);
                        response.setTxnCommitTime(getTime());
                        return Xml.Marshaller(response.getMBFinResponse(MessageMB.MobileResultEnum.OK));
                    } else if (resultcode.equals("01")) {
                        return Xml.Marshaller(response.getMBFinResponse(Message.PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                    } else if (resultcode.equals("03")) {
                        return Xml.Marshaller(response.getMBFinResponse(MessageMB.MobileResultEnum.SECURITIES_WRONG_PARAMETER));
                    } else {
                        Helper.writeLogError(MobileUpgradeController.class, "OnlineInsBuy -result " + result);
                        return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                    }
                    //return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                } else {
                    Helper.writeLogError(MobileUpgradeController.class, "OnlineInsBuy - result " + result);
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                }
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String GetInsurancePartner(String xml) {
        try {
            MobileModelRequest req = (MobileModelRequest) Xml.unMarshaller(MobileModelRequest.class, xml);
            GetSecuritiesPartnerRp response = new GetSecuritiesPartnerRp();

            BillProviderDetail prodvider = new BillProviderDetail();
            prodvider.setIdprovider("MNL");
            prodvider.setProvidername("Bảo hiểm Manulife");
            BillProviderDetail[] providerList = new BillProviderDetail[1];
            providerList[0] = prodvider;
            response.setListBillProvider(providerList);
            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
            /*
            ArrayList billProviderList = Helper.getDBI().getAllListPartnerFI();
            response.setLanguage(req.getLanguage());
            if (billProviderList == null || billProviderList.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(Message.PaymentResultEnum.PROVIDERCODE_NOTEXSIST));
            } else {
                BillProviderDetail[] providerList = new BillProviderDetail[billProviderList.size()];
                if (req.getLanguage() == null || req.getLanguage().isEmpty() || req.getLanguage().equals("VN")) {
                    for (int i = 0; i < billProviderList.size(); i++) {
                        HashMap hm_Provider = (HashMap) billProviderList.get(i);
                        providerList[i] = new BillProviderDetail();
                        providerList[i].setIdprovider(hm_Provider.get("idpartner").toString());//accountLoan.getLoaitien());
                        providerList[i].setProvidername(hm_Provider.get("name").toString());
                    }
                } else if (req.getLanguage().equals("EN")) {
                    for (int i = 0; i < billProviderList.size(); i++) {
                        HashMap hm_Provider = (HashMap) billProviderList.get(i);
                        providerList[i] = new BillProviderDetail();
                        providerList[i].setIdprovider(hm_Provider.get("idpartner").toString());//accountLoan.getLoaitien());
                        providerList[i].setProvidername(hm_Provider.get("name").toString());
                    }
                }

                response.setListBillProvider(providerList);
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
            }
             */
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String GetInsuranceContractInfo(String xml) {
        try {
            GetInsuranceContractInfoRq req = (GetInsuranceContractInfoRq) Xml.unMarshaller(GetInsuranceContractInfoRq.class, xml);
            GetInsuranceContractInfoRp response = new GetInsuranceContractInfoRp();

            List Inspaydetaillist = Helper.getDBI().thongtinkhmanulife(null, req.getPolnum());
            if (Inspaydetaillist == null || Inspaydetaillist.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOT_EXIST_INSURANCECONTRACT));
            }

            ArrayList<InsPaymentDetailRp> manulifeList = new ArrayList<InsPaymentDetailRp>();
            boolean checkDebtFee = false;
            if (!req.getPREM_TYPE().equalsIgnoreCase(PREM_TYPE.L.toString()) && !req.getPREM_TYPE().endsWith(PREM_TYPE.P.toString())) {
                checkDebtFee = true;
            }
            int countDebt = 0;
            for (int i = 0; i < Inspaydetaillist.size(); i++) {
                InsPaymentDetailRp detail = new InsPaymentDetailRp();
                HashMap hm_ins = (HashMap) Inspaydetaillist.get(i);
                if (hm_ins.get("prem_typ").toString().equals(req.getPREM_TYPE())) {
                    BeanUtils.copyProperties(detail, hm_ins);
                    //detail.setAmount();

                    detail.setAmount(detail.getAmount());
                    detail.setTerm(hm_ins.get("due_dt").toString());
                    response.setOwner_name(hm_ins.get("owner_name").toString());
                    if (hm_ins.get("owner_id") != null) {
                        response.setOwner_id(hm_ins.get("owner_id").toString());
                    }
                    long paidAmt = 0;
                    try {
                        paidAmt = new Long(detail.getAmountpaid());
                    } catch (Exception e) {
                        Helper.writeLogError(this.getClass(), e);
                    }
                    long willAmt = new Long(detail.getAmount()) - paidAmt;
                    if (willAmt > 0) {
                        checkDebtFee = true;
                        manulifeList.add(detail);
                        countDebt += 1;
                    } else {
                        if (!req.getPREM_TYPE().equalsIgnoreCase(PREM_TYPE.P.toString())) {
                            manulifeList.add(detail);
                            countDebt += 1;
                        }
                    }
                    if (countDebt > 3) {
                        break;
                    }
                }
            }
            response.setInspaydetaillist((InsPaymentDetailRp[]) manulifeList.toArray(new InsPaymentDetailRp[manulifeList.size()]));
            response.setPol_num(req.getPolnum());
            if (checkDebtFee) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
            } else {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOT_DEBT_INSURANCECONTRACT));
            }

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String InsuranceFeePay(String xml) {
        try {
            InsuranceFeePayRq req = (InsuranceFeePayRq) Xml.unMarshaller(InsuranceFeePayRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            int isValidAuth = req.validateAuth();
            response.setCifNo(req.getCifNo());
            String TxnType = req.getTxnType();
            response.setTxnType(TxnType);
            response.setAccountNo(req.getAccIdaccount());

            //xac thuc giao dich
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                InsPaymentRp insRp = new InsPaymentRp();
                BeanUtils.copyProperties(insRp, req);

                InsPaymentDetailRp[] detailist = req.getInsdetaillist();
                if (detailist == null || detailist.length == 0) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOT_EXIST_INSURANCECONTRACT));
                }

                //cap nhap col_code, ref_num, checksum trc khi thanh toan - star
                List Inspaydetaillist = Helper.getDBI().thongtinkhmanulife(null, req.getPol_num());
                if (Inspaydetaillist == null || Inspaydetaillist.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }
                for (InsPaymentDetailRp insdetail : detailist) {
                    for (int i = 0; i < Inspaydetaillist.size(); i++) {
                        HashMap hm_ins = (HashMap) Inspaydetaillist.get(i);
                        if (hm_ins.get("prem_typ").toString().equals(req.getPrem_typ()) && hm_ins.get("due_dt").toString().equals(insdetail.getTerm())) {
                            if (hm_ins.get("col_code") != null) {
                                insdetail.setCol_code(hm_ins.get("col_code").toString());
                            }
                            insdetail.setChecksum(hm_ins.get("checksum").toString());
                            insdetail.setId(Integer.valueOf(hm_ins.get("id").toString()));
                            insdetail.setRef_num(hm_ins.get("ref_num").toString());
                            if (hm_ins.get("area") != null) {
                                insRp.setArea(hm_ins.get("area").toString());
                            }
                            insRp.setOwnerName(hm_ins.get("owner_name").toString());
                            break;
                        }
                    }
                }
                //cap nhap col_code, ref_num, checksum trc khi thanh toan - end                                                                              
                List<InsPaymentDetailRp> inspaydetaillist = new ArrayList<InsPaymentDetailRp>(Arrays.asList(detailist));

                //kimncm - 26-feb-2019 MB ko phan biet hoa thuong
                req.setUserName(req.getUserName().toLowerCase());
                List mbMobile = Helper.getDBI().getCustomerFromMobile(req.getUserName());

                if (mbMobile == null || mbMobile.isEmpty()) {
                    Helper.writeLogError(ExchangePaybill.class, "***- ERROR MB (FUNCTION transferPaybill): " + "not get customerid from DB VNINFO ");
                }
                HashMap hm_MB = (HashMap) mbMobile.get(0);

                insRp.setInspaydetaillist(inspaydetaillist);

                String AcountPartner;
                ArrayList mnlAccount = Helper.getDBI().getAllPartnerMNL();
                if (mnlAccount == null || mnlAccount.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                }
                HashMap _h1 = (HashMap) mnlAccount.get(0);
                AcountPartner = _h1.get("accountno").toString();

                insRp.setAccountTo(AcountPartner);
                insRp.setPaymentmethod(2);

                insRp.setIdchannel("03");
                insRp.setIdchanneluserMaker(req.getUserName());
                insRp.setIdchanneluserChecker(req.getUserName());

                insRp.setIduserMaker(hm_MB.get("cus_id").toString());
                insRp.setIduserChecker(hm_MB.get("cus_id").toString());
                //insRp.setBranchcode(insRp.getAccIdaccount().substring(0, 3));
                insRp.setBranchcode(CommonUtils.getBranchAccount(insRp.getAccIdaccount()));

                InsPayment inspayment = new InsPayment();
                String result = inspayment.PayIns(Xml.Marshaller(insRp));
                String resultArr[] = result.split("#");
                String resultcode = resultArr[0];

                if (resultcode.length() > 0) {
                    if (resultcode.equals("00")) {
                        response.setTxnID(resultArr[1]);
                        response.setTxnCommitTime(getTime());
                        return Xml.Marshaller(response.getMBFinResponse(MessageMB.MobileResultEnum.OK));
                    } else if (resultcode.equals("01")) {
                        return Xml.Marshaller(response.getMBFinResponse(Message.PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                    } else if (resultcode.equals("03")) {
                        return Xml.Marshaller(response.getMBFinResponse(MessageMB.MobileResultEnum.SECURITIES_WRONG_PARAMETER));
                    } else {
                        Helper.writeLogError(MobileUpgradeController.class, "OnlineInsBuy -result " + result);
                        return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                    }
                    //return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                } else {
                    Helper.writeLogError(MobileUpgradeController.class, "OnlineInsBuy - result " + result);
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.ERROR_SYSTEM));
                }
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String ListAutoBill(String xml) {
        try {
            MobileModelRequest req = (MobileModelRequest) Xml.unMarshaller(MobileModelRequest.class, xml);
            ListAutoBillRp response = new ListAutoBillRp();

            List listautobill = Helper.getDBI().danhsachhoadontudong(req.getCifNo());
            if (listautobill == null || listautobill.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOT_EXIST_AUTOBILLS));
            }
            ArrayList<AutoBillDetail> autoDetails = new ArrayList<AutoBillDetail>();

            for (int i = 0; i < listautobill.size(); i++) {
                AutoBillDetail autoDetail = new AutoBillDetail();
                BeanUtils.copyProperties(autoDetail, listautobill.get(i));
                if (autoDetail.getActive().equals("Y") && autoDetail.getStatus().equals("Y")) {
                    autoDetail.setValiddate(changeFormatDate(autoDetail.getValiddate()));
                    autoDetail.setExpiredate(changeFormatDate(autoDetail.getExpiredate()));
                    autoDetail.setPayType("U");
                    autoDetails.add(autoDetail);
                }
            }
            if (autoDetails.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOT_EXIST_AUTOBILLS));
            }
            response.setListAutoBill((AutoBillDetail[]) autoDetails.toArray(new AutoBillDetail[autoDetails.size()]));
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String CancelAutoBill(String xml) {
        try {
            CancelAutoBillRq req = (CancelAutoBillRq) Xml.unMarshaller(CancelAutoBillRq.class, xml);
            CancelAutoBillRp response = new CancelAutoBillRp();
            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            }
            BeanUtils.copyProperties(response, req);
            String autobillId[] = req.getAutoBillIds().split("#", -1);
            for (int i = 0; i < autobillId.length; i++) {
                if (autobillId[i] != null && autobillId[i].length() > 0) {
                    if (!autobillId[i].isEmpty()) {
                        int result = Helper.getDBI().huyhoadontudong(autobillId[i]);
                        if (result != 1) {
                            Helper.writeLogError(this.getClass(), "CancelAutoBill - failed:".concat(String.valueOf(result)));
                            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.NOTSUCCESS));
                        }
                    }
                }
            }
            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String GetCardListNew(String xml) {
        try {
            GetCardListNewRq req = (GetCardListNewRq) Xml.unMarshaller(GetCardListNewRq.class, xml);
            GetCardListNewRp response = new GetCardListNewRp();
            String cardtype = null;
            String groupcode = null;
            if (req.getTxnType().equals("DEBITCARD")) {
                cardtype = "'VS','MC'";
                groupcode = "'MD'";
            } else if (req.getTxnType().equals("ONLFUNC")) {
                cardtype = "'VS','MC'";
            }

            List listCard = Helper.getDBI().getCardList(req.getCifNo(), cardtype, groupcode);
            if (listCard == null || listCard.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            }

            ArrayList<CardDetail> carddetaillist = new ArrayList<CardDetail>();

            for (int i = 0; i < listCard.size(); i++) {
                CardDetail cardDetail = new CardDetail();
                BeanUtils.copyProperties(cardDetail, listCard.get(i));
                cardDetail.setCardNoHidden(cardDetail.getCardno());
                //The vat ly chua active
                if (!"VC".equals(cardDetail.getImageid().trim())) {
                    cardDetail.setImageid("N");
                    if (cardDetail.getCardactive().equals("Y")) {
                        if (cardDetail.getCardNoHidden() != null && cardDetail.getCardNoHidden().length() > 2) {
                            String subCardNo = cardDetail.getCardNoHidden().substring(cardDetail.getCardNoHidden().length() - 2, cardDetail.getCardNoHidden().length());
                            cardDetail.setCardNoHidden("XXXXXXXXXXXXXX".concat(subCardNo));
                        }
                    }
                } else {
                    cardDetail.setImageid("VC");
                }

                if (cardDetail.getTinhtrangthe() != null) {
                    if (cardDetail.getTinhtrangthe().equals("The Dang Hoat Dong")) {
                        cardDetail.setTinhtrangthe(STATUS_CARD_HOATDONG);
                    } else if (cardDetail.getTinhtrangthe().equals("The Tam Khoa")) {
                        cardDetail.setTinhtrangthe(STATUS_CARD_TAMKHOA);
                    } else if (cardDetail.getTinhtrangthe().equals("The Chua Active")) {
                        cardDetail.setTinhtrangthe(STATUS_CARD_CHUAACTIVE);
                    } else if (cardDetail.getTinhtrangthe().equals("The Het Han")) {
                        cardDetail.setTinhtrangthe(STATUS_CARD_EXPIRED);
                    } else if (cardDetail.getTinhtrangthe().equals("The Dong")) {
                        cardDetail.setTinhtrangthe(STATUS_CARD_CLOSE);
                    } else {
                        cardDetail.setTinhtrangthe("X");
                    }
                }
                if (cardDetail.getBlockonline() != null && cardDetail.getBlockonline().equals("1")) {
                    cardDetail.setBlockonline("L");
                } else {
                    cardDetail.setBlockonline("O");
                }
                if (req.getTxnType().equals("REPIN")) {
                    if (cardDetail.getTinhtrangthe().endsWith(STATUS_CARD_HOATDONG)) {
                        //Giang yeu cau ci lay the chinh chu moi dc doi PIN
                        carddetaillist.add(cardDetail);
                        /*
                        if (cardDetail.getPrimarycif().equals(req.getCifNo())) {
                            carddetaillist.add(cardDetail);
                        }*/
                    }
                } else if (req.getTxnType().equals("CARDFUNC")) {
                    if (cardDetail.getTinhtrangthe().endsWith(STATUS_CARD_TAMKHOA)) {
                        if (cardDetail.getReasoncde().endsWith("TB")) {
                            carddetaillist.add(cardDetail);
                        }
                    } else if (cardDetail.getTinhtrangthe().endsWith(STATUS_CARD_HOATDONG) || cardDetail.getTinhtrangthe().endsWith(STATUS_CARD_CHUAACTIVE)) {
                        cardDetail.setTinhtrangthe(STATUS_CARD_HOATDONG);
                        carddetaillist.add(cardDetail);
                    }

                } else if (req.getTxnType().equals("ONLFUNC")) {
                    if (!cardDetail.getTinhtrangthe().endsWith(STATUS_CARD_EXPIRED) && !cardDetail.getTinhtrangthe().endsWith(STATUS_CARD_CLOSE)) {
                        carddetaillist.add(cardDetail);
                    }
                } else if (req.getTxnType().equals("DEBITCARD")) {
                    //if (cardDetail.getTinhtrangthe().endsWith(STATUS_CARD_HOATDONG) || cardDetail.getTinhtrangthe().endsWith(STATUS_CARD_TAMKHOA)) {                    
                    cardDetail.setTinhtrangthe(STATUS_CARD_HOATDONG);
                    carddetaillist.add(cardDetail);
                    //}
                } else {
                    carddetaillist.add(cardDetail);
                }

            }
            response.setListCard((CardDetail[]) carddetaillist.toArray(new CardDetail[carddetaillist.size()]));
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String ChangePin(String xml) {
        try {
            ChangePinRq req = (ChangePinRq) Xml.unMarshaller(ChangePinRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            int isValidAuth = req.validateAuth();

            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                CardwordController cardControl = new CardwordController();

                String PANCW = req.getCardAccountNumer().concat(req.getCardNo().substring(req.getCardNo().length() - 4));
                RequestChangePINCard reqChangPin = new RequestChangePINCard();
                reqChangPin.setPan(PANCW);
                reqChangPin.setMobileNo(req.getSodt());
                reqChangPin.setNewPIN(req.getPIN());
                //(RequestChangePINCard) Xml.unMarshaller(RequestChangePINCard.class, xml);

                String result = cardControl.resetPINCard(Xml.Marshaller(reqChangPin));
                ResponseChangePINCard resChangePin = (ResponseChangePINCard) Xml.unMarshaller(ResponseChangePINCard.class, result);

                if (resChangePin.getResponseCode().equals("000")) {
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));
                } else {
                    Helper.writeLogError(MobileUpgradeController.class, "ChangePin - result " + resChangePin.getResponseCode() + resChangePin.getResponseDescription());
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.NOTSUCCESS));

                }
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String GetManulifeContractList(String xml) {
        try {
            GetManulifeContractListRq req = (GetManulifeContractListRq) Xml.unMarshaller(GetManulifeContractListRq.class, xml);
            GetManulifeContractListRp response = new GetManulifeContractListRp();

            List Inspaydetaillist = Helper.getDBI().thongtinkhmanulife(req.getOwnerid(), null);
            if (Inspaydetaillist == null || Inspaydetaillist.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOT_EXIST_INSURANCECONTRACT));
            }
            String PolNumList = "";
            for (int i = 0; i < Inspaydetaillist.size(); i++) {
                HashMap hm_ins = (HashMap) Inspaydetaillist.get(i);
                if (!PolNumList.contains(hm_ins.get("pol_num").toString())) {
                    response.setOwner_name(hm_ins.get("owner_name").toString());
                    PolNumList = PolNumList.concat(hm_ins.get("pol_num").toString()).concat("#");
                }
            }
            response.setPolNumList(PolNumList);
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String GetManulifeTypeList(String xml) {
        try {
            GetManulifeTypeListRq req = (GetManulifeTypeListRq) Xml.unMarshaller(GetManulifeTypeListRq.class, xml);
            GetManulifeTypeListRp response = new GetManulifeTypeListRp();

            List Inspaydetaillist = Helper.getDBI().thongtinkhmanulife(null, req.getPolnum());
            if (Inspaydetaillist == null || Inspaydetaillist.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOT_EXIST_INSURANCECONTRACT));
            }

            String manulifeTypeListStr = "";

            ArrayList<ManulifeType> manulifeTypeList = new ArrayList<ManulifeType>();
            for (int i = 0; i < Inspaydetaillist.size(); i++) {
                ManulifeType type = new ManulifeType();
                HashMap hm_ins = (HashMap) Inspaydetaillist.get(i);
                type.setPREM_TYPE(hm_ins.get("prem_typ").toString());
                if (req.getLanguage() == null || req.getLanguage().equalsIgnoreCase("VN")) {
                    type.setPREM_NAME(PREM_TYPE.valueOf(type.getPREM_TYPE()).name);
                } else {
                    type.setPREM_NAME(PREM_TYPE_ENG.valueOf(type.getPREM_TYPE()).name);
                }
                if (!manulifeTypeListStr.contains(type.getPREM_TYPE())) {
                    manulifeTypeListStr = manulifeTypeListStr.concat(type.getPREM_TYPE()).concat("#");
                    manulifeTypeList.add(type);
                    response.setOwner_name(hm_ins.get("owner_name").toString());
                    if (hm_ins.get("owner_id") != null) {
                        response.setOwner_id(hm_ins.get("owner_id").toString());
                    }
                }
            }
            response.setManulifeTypeList((ManulifeType[]) manulifeTypeList.toArray(new ManulifeType[manulifeTypeList.size()]));
            response.setPol_num(req.getPolnum());

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String IssueDebitCard(String xml) {
        try {

            IssueDebitCardRq req = (IssueDebitCardRq) Xml.unMarshaller(IssueDebitCardRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            response.setAccountNo(req.getSourceaccount());
            response.setCifNo(req.getCifNo());

            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            }
            //insert to DB
            EbIssuecard issueCard = new EbIssuecard();
            issueCard.setIschecked('N');
            VwMbCustomer customer = new VwMbCustomer();
            customer.setCustomerNo(req.getCifNo());
            issueCard.setCUSTOMERNO(customer);
            issueCard.setIdchanneluser(req.getUserName());
            issueCard.setIdchannel(ID_CHANNEL_MOBILE);
            issueCard.setRegistertype("MD");

            if (req.getIssueidDate() != null && req.getIssueidDate().contains("-")) {
                req.setIssueidDate(req.getIssueidDate().replaceAll("-", ""));
            }
            if (req.getAccountno() != null && req.getAccountno().length() > 0) {
                List li = Helper.getDBI().getCardnoByAccountno(req.getAccountno());
                if (li == null || li.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }
                HashMap hm_acc = (HashMap) li.get(0);
                issueCard.setCardno(hm_acc.get("cardno").toString());
                issueCard.setCardaccountno(Long.valueOf(hm_acc.get("cardaccountnumber").toString()));
                //4: phát hành thẻ phụ
                //if (req.getIssuetype().equals(ATMISSUE_TYPE_SLAVE)) {
                req.setCardtype(hm_acc.get("cardtype").toString().trim());
                //}
            }
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Timestamp timestamp = Timestamp.valueOf(sdfDate.format(now));
            issueCard.setInsdate(timestamp);
            BeanUtils.copyProperties(issueCard, req);
            //neu KH dang ky nhan the tai CN thi CN la don vi phat hanh the
            //nguoc lai lay don vi mo tk thanh toan lam don vi phat hanh the
            if (issueCard.getBranchcode() != null && issueCard.getBranchcode().length() > 0) {
                issueCard.setIssuebranchcode(issueCard.getBranchcode());
            } else {
                // issueCard.setIssuebranchcode(issueCard.getSourceaccount().substring(0, 3));
                issueCard.setIssuebranchcode(CommonUtils.getBranchAccount(issueCard.getSourceaccount()));
            }

            CardType card = new CardType();
            card.setCardtype(req.getCardtype());
            issueCard.setCARDTYPE(card);

            BigDecimal fee = BigDecimal.ZERO;
            BigDecimal tax = BigDecimal.ZERO;

            if (req.getAmountfee() != null && !req.getAmountfee().isEmpty()) {
                fee = BigDecimal.valueOf(Double.valueOf(req.getAmountfee()));
            }

            if (req.getAmounttax() != null && !req.getAmounttax().isEmpty()) {
                tax = BigDecimal.valueOf(Double.valueOf(req.getAmounttax()));
            }
            BigDecimal sumPay = fee.add(tax);
            //if (!sumPay.equals(BigDecimal.ZERO)) {
            if (sumPay.compareTo(BigDecimal.ZERO) > 0) {
                issueCard.setIschecked('H');
                //int result = Helper.getDBI().insertEbIssuecard(issueCard);
                // if (result == -1) {
                ProcedureCallDto rep = Helper.getDBI().insertEbIssuecard(issueCard);
                if (!rep.isSucess()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_ADD_ATMREGISTER));
                }
                issueCard.setId(Integer.parseInt(rep.getOther()));
                //GET FEE
                Fcubs fc = new Fcubs();

                String fccxref = fc.transferFCUBSWithProductCharge(productMobileCard, CommonUtils.getBranchAccount(req.getSourceaccount()), req.getSourceaccount(),
                        issueCard.getIssuebranchcode(), GLPaymentCard, BigDecimal.ZERO,
                        fee, tax,
                        "THU PHÍ PHÁT HÀNH, GIA HẠN, CẤP LẠI THẺ TRÊN MB" // FOR LIVE
                //"THU PHI PHAT HANH, GIA HAN, CAP LAI THE TREN MB" //FOR TEST
                );

                if (fccxref == null) {
                    issueCard.setIschecked('D');
                    Helper.getDBI().updateEbIssuecard(issueCard);
                    return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                } else {
                    response.setTxnID(fccxref);
                    response.setTxnCommitTime(getTime());
                    TxnCommitRp resp = (TxnCommitRp) response.getMBFinResponse(MobileResultEnum.OK);

                    issueCard.setAccountbalance(Long.valueOf(resp.getNumbalance()));
                    issueCard.setRefFcubs(fccxref);
                    issueCard.setIschecked('N');
                    /*
                     int result = Helper.getDBI().insertEbIssuecard(issueCard);
                     if (result == -1) {
                     return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_ADD_ATMREGISTER));
                     }*/
                    Helper.getDBI().updateEbIssuecard(issueCard);
                    return Xml.Marshaller(resp);
                }
            } else {
                //int result = Helper.getDBI().insertEbIssuecard(issueCard);
                //if (result == -1) {
                ProcedureCallDto rep = Helper.getDBI().insertEbIssuecard(issueCard);
                if (!rep.isSucess()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_ADD_ATMREGISTER));
                }
            }
            // //ngay 2908 Phuong noi ko can tai nguon -- ko thu phi nua
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
            //return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    //change format fate to yyyyMMdd
    public String changeFormatDate(String oldDate) {
        try {
            if (oldDate != null) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                DateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
                Date date = formatter.parse(oldDate);
                return formatter1.format(date);
            } else {
                return oldDate;
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return oldDate;
        }
    }

    //change format fate to yyyyMMdd
    public String changeFormatDateIIB(String oldDate) {
        try {
            if (oldDate != null) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                DateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
                Date date = formatter.parse(oldDate);
                return formatter1.format(date);
            } else {
                return oldDate;
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return oldDate;
        }
    }

    public String changeFormatDatePoint(String oldDate) {
        try {
            if (oldDate != null) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = formatter.parse(oldDate);
                return formatter1.format(date);
            } else {
                return oldDate;
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return oldDate;
        }
    }

    public String CheckOpenOnlineAz(String xml) {
        try {

            CheckOpenOnlineAzRq req = (CheckOpenOnlineAzRq) Xml.unMarshaller(CheckOpenOnlineAzRq.class, xml);
            CheckOpenOnlineAzRp response = new CheckOpenOnlineAzRp();

            //kimncm - tem app chec
            List custInfo = Helper.getDBI().getCustomerInfoForMB("1", req.getCifNo());
            if (custInfo == null || custInfo.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.NO_CUSTOMER_EXISTS));
            }
            HashMap hm_customer = (HashMap) custInfo.get(0);
            String residentStatus = hm_customer.get("residentstatus").toString();
            String domesticCust = hm_customer.get("custcategory").toString();

            if (!domesticCust.equals("I_11")) {
                Helper.writeLogError(MobileController.class, "***-req.getCifNo()" + req.getCifNo() + residentStatus + " - " + domesticCust);
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOT_DOMESTICCUST));
            }

            List prodaccountclass = Helper.getDBI().getProductAccountClass(req.getAccountTypeCode());
            if (prodaccountclass == null || prodaccountclass.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT_TYPE));
            } else {
                HashMap hm_prod = (HashMap) prodaccountclass.get(0);
                BigDecimal MIN_OPEN_TD = new BigDecimal(hm_prod.get("min_amount").toString());
                if (BigDecimal.valueOf(Double.valueOf(req.getAmount())).compareTo(MIN_OPEN_TD) == -1) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_VALID_AMTOPENTD));
                }
                if (req.getTenorType() != null && req.getTenorType().equals("Y")) {
                    // ProcedureCallDto proResult = Helper.getDBI().verifyOpenTD(req.getFromAccount().substring(0, 3), req.getAccountTypeCode(), req.getMatDate());
                    ProcedureCallDto proResult = Helper.getDBI().verifyOpenTD(CommonUtils.getBranchAccount(req.getFromAccount()), req.getAccountTypeCode(), req.getMatDate());
                    if (!proResult.getErrorStatus().equals(DB_SUCESSS_MESSAGE)) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.INVALID_MATURITYDATE_TD));
                    }
                }
            }
            IIBCurrentAccountService iibAccService = new IIBCurrentAccountService();
            RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASA = iibAccService.retrieveCurrentAccountCASARestSimple(Configuration.getIIBContext(), req.getFromAccount(), IIBConstants.CHANNEL_MOBILE);
            if (!retrieveCurrentAccountCASA.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }
            AccountInfoType accountdetailIIB = retrieveCurrentAccountCASA.getAccountInfo();
            //CHECK CAC GIAO DICH DC LAM VOI TK CHI LUONG
            if (accountdetailIIB.getAccountClassCode().equals("CAI012")) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_PAYMENT_SALARY));
            }
            MobileController mb = new MobileController();
            //modify dong chu so huu                
            if (accountdetailIIB.getAccountCoownerName() != null && accountdetailIIB.getAccountCoownerName().length() > 0) {
                //if (!mb.checkJoinForTrans(req.getFromAccount())) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.ERROR_TRANS_JOINT));
                //}
            }

            //validte den han tk luong   
            int validateIntAcc = validateTKLuongOpenTD(req);
            if (validateIntAcc == 1) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_PAYMENT_SALARY));
            } else if (validateIntAcc == 2) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.ERROR_TRANS_JOINT));
            }

            BigDecimal avl_bal = BigDecimal.ZERO;
            avl_bal = accountdetailIIB.getAccountBalanceAvailable();
            avl_bal = avl_bal.subtract(new BigDecimal(accountdetailIIB.getAccountOverdraftLimit()));

            BigDecimal amount = BigDecimal.ZERO;

            if (req.getAmount() != null && !req.getAmount().isEmpty()) {
                amount = BigDecimal.valueOf(Double.valueOf(req.getAmount()));
            }

            response.setSumPayAmount(amount.toString());
            response.setAvlBalance(avl_bal.toString());
            response.setFromAccount(req.getFromAccount());
            if (avl_bal.compareTo(amount) == -1) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY));
            } else {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String VerifyTokenCode(String xml) {
        try {
            VerifyTokenCodeRq req = (VerifyTokenCodeRq) Xml.unMarshaller(VerifyTokenCodeRq.class, xml);
            VerifyTokenCodeRp response = new VerifyTokenCodeRp();
            BeanUtils.copyProperties(req, response);

            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String SelectOnlineInsBuyHistory(String xml) {
        try {
            MobileModelRequest req = (MobileModelRequest) Xml.unMarshaller(MobileModelRequest.class, xml);
            SelectOnlineInsBuyHistoryRp response = new SelectOnlineInsBuyHistoryRp();

            List listInsBuy = Helper.getDBI().GetListInsuranceByCif(req.getCifNo());
            String listInBuyStr = "";
            if (listInsBuy == null || listInsBuy.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_INFOISSUE));
            }
            for (int i = 0; i < listInsBuy.size(); i++) {

                HashMap hm_ins = (HashMap) listInsBuy.get(i);
                listInBuyStr = listInBuyStr.concat(hm_ins.get("iddangky").toString()).concat("%");
                listInBuyStr = listInBuyStr.concat(hm_ins.get("idsanpham").toString()).concat("%");
                listInBuyStr = listInBuyStr.concat(changeFormatDateIIB(hm_ins.get("date_maker").toString())).concat("#");
                // GET_CARD_LIST_TEMP = StringUtils.stripBack(GET_CARD_LIST_TEMP, "AND");                                   
            }
            listInBuyStr = listInBuyStr.substring(0, listInBuyStr.length() - 1);
            response.setListInsBuy(listInBuyStr);
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String RetrieveOnlineInsBuyHistory(String xml) {
        try {
            RetrieveOnlineInsBuyHistoryRq req = (RetrieveOnlineInsBuyHistoryRq) Xml.unMarshaller(RetrieveOnlineInsBuyHistoryRq.class, xml);
            RetrieveOnlineInsBuyHistoryRp response = new RetrieveOnlineInsBuyHistoryRp();

            Insurance ins = Helper.getDBI().GetInsuranceById(Integer.parseInt(req.getInsBuyId()));
            if (ins == null) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }
            BeanUtils.copyProperties(response, ins);
            DateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
            response.setDateBuy(formatter1.format(ins.getInsdateMaker()));
            if (ins.getValidDate() != null) {
                response.setNgayHieuLuc(formatter1.format(ins.getValidDate()));
            }
            if (ins.getDob() != null) {
                response.setDob(formatter1.format(ins.getDob()));
            }
            response.setExpiredPeriod("Một năm kể từ ngày hiệu lực");
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /* ****************************** NGUYEN DAC BINH MINH ****************************** */
 /* VNPAY */
    public String GetChangGiftHistory(String xml) {
        try {
            GetChangGiftHistoryRq req = (GetChangGiftHistoryRq) Xml.unMarshaller(GetChangGiftHistoryRq.class, xml);
            GetChangGiftHistoryRp response = new GetChangGiftHistoryRp();

            List listgift = Helper.getDBI().LayLichSuDoiQua(req.getCardAccountNumber(),
                    req.getCardNo(), req.getFromDate(), req.getToDate());
            if (listgift == null || listgift.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_TRANSACTION));
            }

            GiftDetail[] gifDetails = new GiftDetail[listgift.size()];
            for (int i = 0; i < listgift.size(); i++) {
                gifDetails[i] = new GiftDetail();
                BeanUtils.copyProperties(gifDetails[i], listgift.get(i));
                gifDetails[i].setNgaynhap(CommonUtils.changeFormatDatePoint(gifDetails[i].getNgaynhap()));
            }

            response.setGiftList(gifDetails);
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String ChangeRewardPoint(String xml) {
        try {
            ChangeRewardPointRq req = (ChangeRewardPointRq) Xml.unMarshaller(ChangeRewardPointRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOTSUCCESS));
            } else {
                GiftDetail[] gifDetails = req.getGiftList();
                GifCustomerDetail custdetail = new GifCustomerDetail();
                BeanUtils.copyProperties(custdetail, req);
                custdetail.setMakerId(MAKEID_DVKH);

                IIBCardService iibCardService = new IIBCardService();
                RetrieveCreditCardDetail_out_Type retrieveCreditCardDetail_out_Type = iibCardService.retrieveCreditCardDetailSimple(Configuration.getIIBContext(),
                        req.getAccountNo(), IIB_CHANNEL_MOBILE);
                CardInfoType carddetailIIB = retrieveCreditCardDetail_out_Type.getCardInfo();
                if (retrieveCreditCardDetail_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    String cardStatus = carddetailIIB.getCardStatus();

                    if ("THE DANG HOAT DONG".equals(cardStatus.toUpperCase())) {
                        String locPan = custdetail.getCifNo()
                                .concat(custdetail.getCardAccountNumber()
                                        .concat(custdetail.getCardNo().substring(12)));

                        String[] point = Helper.getDBI().getPointMC(req.getAccountNo());
                        if (point == null || point.length != 4) {
                            // update error code
                            Helper.writeLog(MobileUpgradeController.class, Level.INFO, "Ko lay duoc so diem hien tai cua account = [" + req.getAccountNo() + "]. getPointMC = [" + Arrays.toString(point) + "]");
                            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOTSUCCESS));
                        }
                        String totalPoint = point[3];
                        int totalPointPending = Helper.getDBI().GET_TOTAL_POINT_PENDING(locPan);
                        if (Integer.parseInt(totalPoint) - (Integer.parseInt(req.getChangePointTotal()) + totalPointPending) < 0) {
                            // update error code
                            Helper.writeLog(MobileUpgradeController.class, Level.INFO, "So diem khong du de thuc hien giao dich. Current point = [" + totalPoint + "], pending point = [" + totalPointPending + "], changing Point = [" + req.getChangePointTotal() + "]");
                            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOTSUCCESS));
                        }

                        int khIsExist = Helper.getDBI()
                                .KIEM_TRA_THONG_TIN_KHACH_HANG_IS_EXISTING(req.getCifNo());
                        if (khIsExist != 1) // khach hang chua ton tai
                        {
                            IIBCustomerService iibCustService = new IIBCustomerService();
                            RetrieveCustomerRefDataMgmt_out_Type retrieveCustomer = iibCustService.retrieveCustomerRefDataMgmtSimple(Configuration.getIIBContext(),
                                    req.getCifNo(), IIBConstants.CHANNEL_MOBILE);

                            if (!retrieveCustomer.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                                //Helper.writeLog(this.getClass(), Level.INFO, "customerDetailIIB result = [null], CIFNo = [" + req.getCifNo() + "], AccountNo = [" + req.getAccountNo() + "]");
                            } else {
                                CustomerInfoType customerDetailIIB = retrieveCustomer.getCustomerInfo();
                                String hoTen = customerDetailIIB.getFullname();
                                String cmnd = customerDetailIIB.getIDInfo().getIDNum();
                                String ngayCap = customerDetailIIB.getIDInfo().getIDIssuedDate();
                                String noiCap = customerDetailIIB.getIDInfo().getIDIssuedLocation();
                                String diaChi = customerDetailIIB.getAddress().getAddress1();
                                String ho = "";
                                String tenTenDem = "";
                                String[] temp = customerDetailIIB.getFullname().split(" ");
                                if (temp != null && temp.length >= 2) {
                                    ho += temp[0];
                                    for (int i = 1; i < temp.length; i++) {
                                        if (i == (temp.length - 1)) {
                                            tenTenDem += temp[i];
                                        } else {
                                            tenTenDem += temp[i] + " ";
                                        }
                                    }
                                } else {
                                    tenTenDem = customerDetailIIB.getFullname();
                                }

                                String cardTypeOracle = carddetailIIB.getCardProduct();
                                String loaiTheOracle = carddetailIIB.getCardBrand();
                                String brchCode = carddetailIIB.getCardOpenBranchCode();
                                if (brchCode != null && brchCode.length() >= 3) {
                                    brchCode = brchCode.substring(0, 3);
                                }
                                String theChinhPhu = carddetailIIB.getCardProperty();
                                String crdStat = carddetailIIB.getCardLockStatus();
                                String gioiTinh = customerDetailIIB.getGender(); //"M";
                                String dienThoai = customerDetailIIB.getAddress().getMobileNum();
                                String tempLock = "";
                                String locLMT = carddetailIIB.getCardCustomerTotalLimit();
                                String maDVNhap = "000";
                                int uIDNhap = Integer.parseInt(custdetail.getMakerId());

                                String VNAID = "";
                                int id = 0;

                                int loaiThe;
                                int hangThe;

                                switch (loaiTheOracle.toUpperCase()) {
                                    case MASTER: {
                                        loaiThe = 1;
                                        hangThe = 1;
                                        switch (cardTypeOracle.toUpperCase()) {
                                            case CARD_TYPE_G:
                                                hangThe = 2;
                                                break;
                                            case CARD_TYPE_W:
                                                hangThe = 3;
                                                break;
                                        }
                                    }
                                    break;
                                    case VISA: {
                                        loaiThe = 2;
                                        hangThe = 4;
                                        switch (cardTypeOracle.toUpperCase()) {
                                            case CARD_TYPE_G:
                                                hangThe = 5;
                                                break;
                                            case CARD_TYPE_P:
                                                hangThe = 6;
                                                break;
                                        }
                                    }
                                    break;
                                    default:
                                        Helper.writeLog(this.getClass(), Level.INFO, "Loai the Oracle = [" + loaiTheOracle + "], AccountNo = [" + req.getAccountNo() + "]");
                                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.IBT_CARDACCNONOTVALID_08));
                                }

                                Helper.writeLog(this.getClass(), Level.INFO, "ID = [" + id + "], MAKH = ["
                                        + req.getCifNo() + "], HOTEN = [" + hoTen + "], CMND = [" + cmnd + "], DIACHI = ["
                                        + diaChi + "], NGAYCAP = [" + ngayCap + "], NOICAP = [" + noiCap + "], GIOITINH = ["
                                        + gioiTinh + "], DIENTHOAI = [" + dienThoai + "], LOC = [" + req.getCardAccountNumber()
                                        + "], PAN = [" + req.getCardNo() + "], LOCPAN = [" + locPan
                                        + "], LOAITHE = [" + loaiThe + "], HANGTHE = [" + hangThe + "], CRD_STAT = ["
                                        + crdStat + "], BRCH_CODE = [" + brchCode + "], THECHINHPHU = [" + theChinhPhu
                                        + "], TEMP_LOCK = [" + tempLock + "], LOC_LMT = [" + locLMT + "], MADV_NHAP = ["
                                        + maDVNhap + "], UID_NHAP = [" + uIDNhap + "], VNAID = [" + VNAID + "], HO = ["
                                        + ho + "], TENDEM_TEN = [" + tenTenDem + "], CARDTYPEORACLE = [" + cardTypeOracle
                                        + "], LOAITHEORACLE = [" + loaiTheOracle + "], CURRENTPOINT = [" + totalPoint
                                        + "], PENDINGPOINT = [" + totalPointPending + "], CHANGINGPOINT = ["
                                        + req.getChangePointTotal() + "]");

                                String resultInsert = Helper.getDBI().INSERT_THONG_TIN_KHACH_HANG(id,
                                        req.getCifNo(), hoTen, cmnd, diaChi, ngayCap, noiCap, gioiTinh,
                                        dienThoai, req.getCardAccountNumber(), req.getCardNo(), locPan,
                                        loaiThe, hangThe, crdStat, brchCode, theChinhPhu, tempLock, locLMT,
                                        maDVNhap, uIDNhap, VNAID, ho, tenTenDem);

                                if (!"0".equals(resultInsert)) {
                                    Helper.writeLog(this.getClass(), Level.INFO, "Insert thong tin khach hang ko thanh cong. CIF = [" + req.getCifNo() + "], AccountNo = [" + req.getAccountNo() + "]");
                                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOTSUCCESS));
                                }
                            }
                        }

                        for (GiftDetail gifDetail : gifDetails) {
                            String result = Helper.getDBI().GhiNhanThongTinDoiQua(custdetail, gifDetail);
                            if (!"0".equals(result)) {
                                Helper.writeLog(this.getClass(), Level.INFO, "Ghi nhan thong tin doi qua khong thanh cong. ID = [" + gifDetail.getId()
                                        + "], Title = [" + gifDetail.getTitle() + "], So luong = [" + gifDetail.getSoluong() + "], Diem quy doi = ["
                                        + gifDetail.getDiemquydoi() + "], Loai QT = [" + gifDetail.getLoaiqt() + "], Ghi chu = [" + gifDetail.getGhichu() + "]");
                                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOTSUCCESS));
                            }
                        }
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
                    } else {
                        Helper.writeLog(this.getClass(), Level.INFO, "Card status = [" + cardStatus + "], AccountNo = [" + req.getAccountNo() + "]");
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.IBT_CARDEXPIRED));
                    }
                } else {
                    Helper.writeLog(this.getClass(), Level.INFO, "retrieveCreditCardDetailSimple result = [" + retrieveCreditCardDetail_out_Type.getTransactionInfo().getTransactionReturn() + "], AccountNo = [" + req.getAccountNo() + "]");
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                }
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String GetGiftList(String xml) {
        try {
            GetGiftListRq req = (GetGiftListRq) Xml.unMarshaller(GetGiftListRq.class, xml);
            GetGiftListRp response = new GetGiftListRp();

            IIBCardService iibCardService = new IIBCardService();
            RetrieveCreditCardDetail_out_Type retrieveCreditCardDetail_out_Type = iibCardService.retrieveCreditCardDetailSimple(Configuration.getIIBContext(),
                    req.getAccountNo(), IIB_CHANNEL_MOBILE);
            if (retrieveCreditCardDetail_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                CardInfoType carddetailIIB = retrieveCreditCardDetail_out_Type.getCardInfo();
                String branchCode = carddetailIIB.getCardOpenBranchCode();

                if (branchCode != null && branchCode.length() >= 3) {
                    branchCode = branchCode.substring(0, 3);
                }

                List listgift = Helper.getDBI().GetDanhMucQuaTang(branchCode);
                if (listgift == null || listgift.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.NOT_ENOUGH_QUANTITY));
                }

                GiftDetail[] gifDetails = new GiftDetail[listgift.size()];
                for (int i = 0; i < listgift.size(); i++) {
                    gifDetails[i] = new GiftDetail();
                    BeanUtils.copyProperties(gifDetails[i], listgift.get(i));
                    if (gifDetails[i].getCheck_sum().equals("1")) {
                        gifDetails[i].setCheck_sum("Y");
                    } else {
                        gifDetails[i].setCheck_sum("N");
                    }
                }

                response.setGiftList(filterGiftList(gifDetails, carddetailIIB));
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
            } else {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    private GiftDetail[] filterGiftList(GiftDetail[] gifDetails, CardInfoType carddetailIIB) {
        String loaiTheOracle = carddetailIIB.getCardBrand();
        String cardTypeOracle = carddetailIIB.getCardProduct();

        if ((MASTER.equalsIgnoreCase(loaiTheOracle) && CARD_TYPE_W.equalsIgnoreCase(cardTypeOracle))
                || (VISA.equalsIgnoreCase(loaiTheOracle) && CARD_TYPE_P.equalsIgnoreCase(cardTypeOracle))) {
            return gifDetails;
        }

        List result = new ArrayList();

        for (GiftDetail gifDetail : gifDetails) {
            if (!"48".equalsIgnoreCase(gifDetail.getId())) {
                result.add(gifDetail);
            }
        }

        return (GiftDetail[]) result.toArray(new GiftDetail[result.size()]);
    }

    private PayByQRCodeRp payByQRCodeCredit(PayByQRCodeRq req) {
        // Khai bao cac tham so dung sau nay
        PayByQRCodeRp response = new PayByQRCodeRp();
        CardwordController cwController = new CardwordController();
        MasterCardController masterCardController = new MasterCardController();

        // Thuc hien goi qua CW de cat tien cua khach hang
        ErrorCodeEnum directDebit = cwController.executeDirectDebit(req);
        // Kiem tra ket qua tra ve sau khi thuc hien cat tien
        switch (directDebit) {
            case CARDWORD_OK: {
                // Thuc hien goi qua doi tac Master de thong bao cho merchant
                ReasonCodeEnum resMaster = masterCardController.executePayment(req);
                // Kiem tra ket qua sau khi thuc hien notify cho doi tac Master
                switch (resMaster) {
                    case MASTERCARD_OK: // Notify cho doi tac Master thanh cong
                        response.getMBResponse(CommontEnum.TRANSACTION_OK);
                        break;
                    case MASTERCARD_UNKNOWN: // Loi Master tra ve ko co trong danh sach loi da duoc define
                        response.getMBResponse(CommontEnum.MASTERCARD_UNKNOWN);
                        break;
                    case MASTERCARD_OK_DBI_FAILED: // Thuc hien notify cho Master thanh cong nhung cap nhat database that bai
                        response.getMBResponse(CommontEnum.MASTERCARD_OK_DBI_FAILED);
                        break;
                    case MASTERCARD_RETURN_DBI_FAILED: // Master tra ve loi va cap nhat database that bai
                        response.getMBResponse(CommontEnum.MASTERCARD_RETURN_DBI_FAILED);
                        break;
                    case MASTERCARD_FAILED: // Master thuc hien tra ve ma loi trong danh sach da duoc define va thuc hien hoan tien cho khach hang
                        response.getMBResponse(CommontEnum.MASTERCARD_FAILED);
                        // Thuc hien goi qua CW de hoan tien cho khach hang
                        cwController.executeCardAdjustment(req);
                        break;
                    default: // cac truoc hop con lai chua xac dinh
                        response.getMBResponse(resMaster);
                        break;
                }
                break;
            }
            default: { // thuc hien cat tien tai CW ko thanh cong
                response.getMBResponse(directDebit);
                break;
            }
        }
        return response;
    }

    private boolean payByQRCodeDebitCore(PayByQRCodeRq req) {
        boolean result = false;
        try {
            // thuc hien goi qua core tru tien cua tai khoan lien ket
            Fcubs fcubs = new Fcubs();
            String refCore = fcubs.transferFCUBSWithProduct(MASTER_DEBIT_PRODUCT, req.getSenderMSVSCardInfo().getAccountDebit(), MASTER_ACCOUNT_DEBIT, new BigDecimal(req.getAmount()), "THANH TOAN QR CODE TAI " + req.getMerFName());
            // kiem tra xem tru tien co thanh cong hay ko?
            if (refCore != null && !refCore.isEmpty() && !"TIMEOUT".equalsIgnoreCase(refCore)) {
                req.setCoreRef(refCore);
                req.setStatus(CommonConstant.MASTERQR_STATUS_DIRECTDEBIT_SUCCESS);
            } else {
                req.setStatus(CommonConstant.MASTERQR_STATUS_DIRECTDEBIT_FAILED);
            }
            // thuc hien cap nhat xuong database
            int resultDB = Helper.getDBI().MASTERPASSQR(req, MasterCardQrActionEnum.UPDATE_CORE);
            // kiem tra xem cap nhat xuong database co thanh cong hay ko?
            if (resultDB == 1) {
                if (CommonConstant.MASTERQR_STATUS_DIRECTDEBIT_SUCCESS.equals(req.getStatus())) {
                    result = true;
                } else {
                    LOGGER.warn("Thuc hien goi qua core tru tien that bai");
                }
            } else {
                LOGGER.warn("Cap nhat database that bai");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

    private boolean payByQRCodeDebitRefundCore(PayByQRCodeRq req) {
        boolean result = false;
        try {
            // thuc hien goi qua core hoan tien cua tai khoan lien ket
            Fcubs fcubs = new Fcubs();
            String refundRefCore = fcubs.transferFCUBSWithProduct(MASTER_DEBIT_REFUND_PRODUCT, MASTER_ACCOUNT_DEBIT, req.getSenderMSVSCardInfo().getAccountDebit(), new BigDecimal(req.getAmount()), "HOAN TIEN THANH TOAN QR CODE TAI " + req.getMerFName());
            // kiem tra xem hoan tien co thanh cong hay ko?
            if (refundRefCore != null && !refundRefCore.isEmpty() && !"TIMEOUT".equalsIgnoreCase(refundRefCore)) {
                req.setRefundCoreRef(refundRefCore);
                req.setStatus(CommonConstant.MASTERQR_STATUS_REFUND_SUCCESS);
            } else {
                req.setStatus(CommonConstant.MASTERQR_STATUS_REFUND_FAILED);
            }
            // thuc hien cap nhat xuong database
            int resultDB = Helper.getDBI().MASTERPASSQR(req, MasterCardQrActionEnum.UPDATE_REFUNDCODE);
            // kiem tra xem cap nhat xuong database co thanh cong hay ko?
            if (resultDB == 1) {
                result = true;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

    private PayByQRCodeRp payByQRCodeDebit(PayByQRCodeRq req) {
        // khoi tao cac bien dung sau nay
        PayByQRCodeRp response = new PayByQRCodeRp();
        MasterCardController masterCardController = new MasterCardController();

        try {
            // thuc hien goi qua core tru tien cua tai khoan lien ket
            boolean resultCore = payByQRCodeDebitCore(req);
            if (resultCore) {
                // thuc hien goi qua CW de cap nhat thong tin lich su giao dich
                // Thuc hien goi qua doi tac Master de thong bao cho merchant
                ReasonCodeEnum resMaster = masterCardController.executePayment(req);
                // Kiem tra ket qua sau khi thuc hien notify cho doi tac Master
                switch (resMaster) {
                    case MASTERCARD_OK: // Notify cho doi tac Master thanh cong
                        response.getMBResponse(CommontEnum.TRANSACTION_OK);
                        break;
                    case MASTERCARD_UNKNOWN: // Loi Master tra ve ko co trong danh sach loi da duoc define
                        response.getMBResponse(CommontEnum.MASTERCARD_UNKNOWN);
                        break;
                    case MASTERCARD_OK_DBI_FAILED: // Thuc hien notify cho Master thanh cong nhung cap nhat database that bai
                        response.getMBResponse(CommontEnum.MASTERCARD_OK_DBI_FAILED);
                        break;
                    case MASTERCARD_RETURN_DBI_FAILED: // Master tra ve loi va cap nhat database that bai
                        response.getMBResponse(CommontEnum.MASTERCARD_RETURN_DBI_FAILED);
                        break;
                    case MASTERCARD_FAILED: // Master thuc hien tra ve ma loi trong danh sach da duoc define va thuc hien hoan tien cho khach hang
                        response.getMBResponse(CommontEnum.MASTERCARD_FAILED);
                        // Thuc hien goi qua core de hoan tien cho khach hang
                        payByQRCodeDebitRefundCore(req);
                        // Thuc hien goi qua ben CW de cap nhat sao ke
                        break;
                    default: // cac truoc hop con lai chua xac dinh
                        response.getMBResponse(resMaster);
                        break;
                }
            } else { // goi qua core tru tien that bai
                response.getMBResponse("16", "Thuc hien goi qua core tru tien that bai");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return response;
    }

    /* MASTERPASS */
    public String PayByQRCode(String xml) {
        try {
            PayByQRCodeRp response = new PayByQRCodeRp();
            PayByQRCodeRq req = (PayByQRCodeRq) Xml.unMarshaller(PayByQRCodeRq.class, xml);

            /* Cau hinh VNM cho cac doi tac cua hang tai Viet Nam */
            if (req.getMerCountry() != null && !req.getMerCountry().isEmpty() && "VN".equals(req.getMerCountry())) {
                req.setMerCountry("VNM");
            }

            // Goi qua database cua CW de lay thong tin the dung cho thanh toan Master
            SenderMSVSCardInfo senderInfor = Helper.getDBI().querySenderMSVSCardInfo(req.getLoc4Digit());
            // Kiem tra xem co lay duoc thong tin tu database the hay ko
            if (senderInfor != null && !senderInfor.isInvalidData()) {
                req.setSenderMSVSCardInfo(senderInfor);
                // Kiem tra xem thong tin request co valid hay ko
                CommontEnum isValidData = req.validate();
                // Xu ly cac case sau khi kiem tra du lieu request va thong tin tu database the
                switch (isValidData) {
                    case DATA_IS_VALID: { // du lieu valid
                        // cau hinh status ve trang thai khoi tao
                        req.setStatus(CommonConstant.MASTERQR_STATUS_INIT);
                        // luu tru du lieu xuong database
                        String sequenceNo = Helper.getDBI().INSERTMASTERPASSQR(req);
                        // kiem tra xem viec luu tru du lieu co thanh cong hay ko?
                        if (sequenceNo != null && !sequenceNo.isEmpty()) {
                            req.setSequenceNo(sequenceNo);
                            /* kiem tra xem co phai la the credit hay ko
                            * C ~ credit; D ~ debit
                             */
                            if (req.getSenderMSVSCardInfo().isCreditCard()) {
                                response = payByQRCodeCredit(req);
                            } else {
                                /* comment lai cho go-live
                                // cau hinh lai bien funding source
                                req.setFundingS("DEBIT");
                                // thuc hien luong thanh toan qua the debit
                                response = payByQRCodeDebit(req);
                                 */
                                response.getMBResponse(CommontEnum.BANKNOTSUPPORT);
                            }
                        } else {
                            response.getMBResponse(CommontEnum.DUPLICATE_SEQUENCENO);
                        }
                        break;
                    }
                    default: { // moi truong hop con lai de tra ve invalid data
                        response.getMBResponse(isValidData);
                        break;
                    }
                }
            } else {
                // ko tim thay duoc du lieu ben database the
                response.getMBResponse(CommontEnum.LOC4DIGIT_DOES_NOT_FOUND);
            }
            return Xml.Marshaller(response);
        } catch (Exception e) {
            LOGGER.error("XML request is invalid or DBI (queryMasterpassCardInfor or INSERTMASTERPASSQR) failed. Ex = [" + e.toString() + "]");
            return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><PayByQRCodeRp><ErrorCode>088</ErrorCode><ErrorMsg>UNKNOW_MASTER_MESSAGE</ErrorMsg></PayByQRCodeRp>";
        }
    }

    public String initSessionToChangepin(InitSessionToChangepinRq req) {
        InitSessionToChangepinRs response = new InitSessionToChangepinRs();
        /* Thuc hien gan bien transactionId cho response tra ve cho doi tac */
        response.setTransactionId(req.getTransactionId());
        Gson gson = new Gson();
        try {
            /* Kiem tra du lieu cua request co valid hay ko */
            if (req.isValid()) {
                /* Thuc hien lay so sequence de call qua CW */
                String refTransactionId = String.valueOf(Helper.getDBI().getIdSeqByName("SQ_CWWSREFNO"));
                /* Khoi tao request json de goi qua api restful */
                InitSessionToChangepinJsonRq request = new InitSessionToChangepinJsonRq(CHANGEPIN_USER, CHANGEPIN_PASS, refTransactionId, req);
                /* Convert object ve chuoi string dung de call restful */
                String jsonRequest = gson.toJson(request);
                /* Thuc hien call restful api */
                RestFulWebserviceController rfController = new RestFulWebserviceController();
                String jsonResponse = rfController.postRequest(CHANGEPIN_URL, jsonRequest);
                /* Convert resonse cua restful api ve object */
                InitSessionToChangepinJsonRs apiResponse = gson.fromJson(jsonResponse, InitSessionToChangepinJsonRs.class);
                /* Thuc hien cau hinh response phan hoi cho doi tac */
                response.setError(scb.com.vn.common.model.cw.changepin.ErrorCodeEnum.SUCCESS);
                response.setAccessTokenKey(apiResponse.getAccessToken());
                response.setPublicKey(apiResponse.getPublicKey());
                response.setRefTransactionId(apiResponse.getRefTransactionId());
            } else {
                /* Request invalid nen thuc hien cau hinh ma loi tra ve cho doi tac */
                response.setError(scb.com.vn.common.model.cw.changepin.ErrorCodeEnum.INVALID_REQUEST_MESSAGE);
            }
            /* Thuc hien convert response phan hoi cho doi tac ve chuoi string */
            return Xml.Marshaller(response);
        } catch (Exception e) {
            /* Co loi xay ra nen mac dinh tra ve ma loi 99 */
            LOGGER.error(e);
            return "<INITSESSIONTOCHANGEPINRS><ERROR_CODE>99</ERROR_CODE><ERROR_DESCRIPTION>UNKNOW_ERROR_CODE</ERROR_DESCRIPTION></INITSESSIONTOCHANGEPINRS>";
        }
    }

    public String CheckBalanceMasterPassBeforeTran(String xml) {
        try {

            CheckBalanceMasterPassBeforeTranRq req = (CheckBalanceMasterPassBeforeTranRq) Xml.unMarshaller(CheckBalanceMasterPassBeforeTranRq.class, xml);
            CheckBalanceMasterPassBeforeTranRp response = new CheckBalanceMasterPassBeforeTranRp();

            IIBCardService iibCardService = new IIBCardService();
            RetrieveCreditCardDetail_out_Type retrieveCreditCardDetail_out_Type = iibCardService.retrieveCreditCardDetailSimple(Configuration.getIIBContext(), req.getFromAccount(), IIB_CHANNEL_MOBILE);
            if (!retrieveCreditCardDetail_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            }
            CardInfoType carddetailIIB = retrieveCreditCardDetail_out_Type.getCardInfo();

            BigDecimal percent = new BigDecimal(0.05);

            BigDecimal CardLimit = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardLimit()));
            BigDecimal CardCurrentLimit = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardCurrentLimit()));
            BigDecimal avl_bal = CardCurrentLimit.add(percent.multiply(CardLimit));

            DecimalFormat df2 = new DecimalFormat(".##");
            BigDecimal amount = BigDecimal.ZERO;
            BigDecimal fee = BigDecimal.ZERO;
            BigDecimal tax = BigDecimal.ZERO;

            if (req.getAmount() != null && !req.getAmount().isEmpty()) {
                amount = BigDecimal.valueOf(Double.valueOf(req.getAmount()));
            }

            if (req.getTxnFee() != null && !req.getTxnFee().isEmpty()) {
                fee = BigDecimal.valueOf(Double.valueOf(req.getTxnFee()));
            }

            if (req.getTxnTax() != null && !req.getTxnTax().isEmpty()) {
                tax = BigDecimal.valueOf(Double.valueOf(req.getTxnTax()));
            }
            BigDecimal sumPay = amount.add(fee).add(tax);

            response.setSumPayAmount(sumPay.toString());
            response.setAvlBalance(df2.format(avl_bal));
            response.setFromAccount(req.getFromAccount());
            if (avl_bal.compareTo(sumPay) == -1) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY));
            } else {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String MvisaQRCode02(String req) {
        try {
            //MVISAQRRQ req = (MVISAQRRQ) Xml.unMarshaller(MVISAQRRQ.class, xml);
            Object[] objs = new Object[1];
            objs[0] = Xml.Marshaller(req);
            Object[] obj_result = null;//Helper.callWsVisa("MvisaQRCode", objs);
            LOGGER.info(obj_result);
            
            return Xml.Marshaller(obj_result);
        } catch (Exception e) {
            LOGGER.error("co loi goi ham MvisaQRCode02 " + e.getMessage());
            return "-99";
        }
    }
    
    /* MASTERPASS */
    /* VISA */
    public String MvisaQRCode(String xml) {
        try {
            // khoi tao cac bien dung
            MVISAQRRP response = new MVISAQRRP();
            // convert xml cua doi tac thanh object de xu ly
            MVISAQRRQ req = (MVISAQRRQ) Xml.unMarshaller(MVISAQRRQ.class, xml);
            // Goi qua database cua CW de lay thong tin the dung cho thanh toan Master
            SenderMSVSCardInfo senderInfor = Helper.getDBI().querySenderMSVSCardInfo(req.getLoc4digit());
            // Kiem tra xem co lay duoc thong tin tu database the hay ko
            if (senderInfor != null && !senderInfor.isInvalidData()) {
                req.setSenderMSVSCardInfo(senderInfor);
                // Kiem tra xem thong tin request co valid hay ko
                CommontEnum isValidData = req.validate();
                // Xu ly cac case sau khi kiem tra du lieu request va thong tin tu database the
                switch (isValidData) {
                    case DATA_IS_VALID: { // du lieu valid
                        // cau hinh status ve trang thai khoi tao
                        req.setStatus(CommonConstant.MASTERQR_STATUS_INIT);
                        // luu tru du lieu xuong database
                        String sequence = Helper.getDBI().INSERTVISAQR(req);
                        if (sequence != null && !sequence.isEmpty()) {
                            // cau hinh tham so sau khi co so sequence
                            req.setSequenceNo(sequence);
                            req.setSysTraceAuditNum(sequence);
                            // tao so retrievalRefNum = so ngay trong nam + so sequence
                            String retrievalRefNum = CommonUtils.getJulianFromDate() + sequence;
                            req.setRetrievalRefNum(retrievalRefNum);
                            /* khong cau hinh thu phi cho thanh toan QR. Su dung phi tu doi tac truyen qua
                            * req.setFeeProgramIndicator(VisaConstant.FEEPROGRAMINDICATOR);
                             */
                            if (req.getSenderMSVSCardInfo().isCreditCard()) {
                                response = mvisaQRCodeCredit(req);
                            } else {
                                /* comment lai cho go-live
                                // thuc hien luong thanh toan qua the debit
                                response = mvisaQRCodeDebit(req);
                                 */
                                response.getMBResponse(CommontEnum.BANKNOTSUPPORT);
                            }
                        } else {
                            response.getMBResponse(CommontEnum.DUPLICATE_SEQUENCENO);
                        }
                        break;
                    }
                    default: {
                        response.getMBResponse(isValidData);
                        break;
                    }
                }
            } else {
                response.getMBResponse(CommontEnum.LOC4DIGIT_DOES_NOT_FOUND);
            }
            return Xml.Marshaller(response);
        } catch (Exception e) {
            LOGGER.error("XML request is invalid or DBI (queryMasterpassCardInfor or INSERTVISAQR) failed. Ex = [" + e.toString() + "]");
            return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><MVISAQRRP><ErrorCode>1999</ErrorCode><ErrorMsg>He thong loi</ErrorMsg></MVISAQRRP>";
        }
    }

    private MVISAQRRP mvisaQRCodeDebit(MVISAQRRQ req) {
        // Khai bao cac tham so dung sau nay
        MVISAQRRP response = new MVISAQRRP();
        VisaController visaController = new VisaController();
        try {
            // thuc hien goi qua core tru tien cua tai khoan lien ket
            boolean resultCore = mvisaQRCodeDebitCore(req);
            if (resultCore) {
                // Thuc hien goi qua doi tac Visa de thong bao cho merchant
                VisaMessage.VisaCodeEnum resMaster = visaController.executePayment(req);
                // Kiem tra ket qua sau khi thuc hien notify cho doi tac Visa
                switch (resMaster) {
                    case VISA_OK: // Notify cho doi tac Visa thanh cong
                        response.getMBResponse(CommontEnum.TRANSACTION_OK);
                        break;
                    case VISA_FAILED: // Visa thuc hien tra ve ma loi trong danh sach da duoc define va thuc hien hoan tien cho khach hang
                        response.getMBResponse(resMaster);
                        // Thuc hien goi qua core de hoan tien cho khach hang
                        mvisaQRCodeDebitRefundCore(req);
                        break;
                    case VISA_OK_DBI_FAILED: // Thuc hien notify cho Visa thanh cong nhung cap nhat database that bai
                    case VISA_RETURN_DBI_FAILED: // Visa tra ve loi va cap nhat database that bai
                    default: // cac truoc hop con lai chua xac dinh
                        response.getMBResponse(resMaster);
                        break;
                }
            } else { // goi qua core tru tien that bai
                response.getMBResponse("16", "Thuc hien goi qua core tru tien that bai");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return response;
    }

    private boolean mvisaQRCodeDebitRefundCore(MVISAQRRQ req) {
        boolean result = false;
        try {
            // thuc hien goi qua core hoan tien cua tai khoan lien ket
            Fcubs fcubs = new Fcubs();
            // Cong so tien va phi (neu co)
            BigDecimal total = new BigDecimal(req.getAmount()).add(new BigDecimal(req.getTransFeeAmt()));
            String refundRefCore = fcubs.transferFCUBSWithProduct(VISA_DEBIT_REFUND_PRODUCT, VISA_ACCOUNT_DEBIT, req.getSenderMSVSCardInfo().getAccountDebit(), total, "HOAN TIEN THANH TOAN QR CODE TAI " + req.getCardAccName());
            // kiem tra xem hoan tien co thanh cong hay ko?
            if (refundRefCore != null && !refundRefCore.isEmpty() && !"TIMEOUT".equalsIgnoreCase(refundRefCore)) {
                req.setRefundCoreRef(refundRefCore);
                req.setStatus(CommonConstant.MASTERQR_STATUS_REFUND_SUCCESS);
            } else {
                req.setStatus(CommonConstant.MASTERQR_STATUS_REFUND_FAILED);
            }
            // thuc hien cap nhat xuong database
            int resultDB = Helper.getDBI().VISAQR(req, MasterCardQrActionEnum.UPDATE_REFUNDCODE);
            // kiem tra xem cap nhat xuong database co thanh cong hay ko?
            if (resultDB == 1) {
                result = true;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

    private boolean mvisaQRCodeDebitCore(MVISAQRRQ req) {
        boolean result = false;
        try {
            // thuc hien goi qua core tru tien cua tai khoan lien ket
            Fcubs fcubs = new Fcubs();
            // Cong so tien va phi (neu co)
            BigDecimal total = new BigDecimal(req.getAmount()).add(new BigDecimal(req.getTransFeeAmt()));
            String refCore = fcubs.transferFCUBSWithProduct(VISA_DEBIT_PRODUCT, req.getSenderMSVSCardInfo().getAccountDebit(), VISA_ACCOUNT_DEBIT, total, "THANH TOAN QR CODE TAI " + req.getCardAccName());
            // kiem tra xem tru tien co thanh cong hay ko?
            if (refCore != null && !refCore.isEmpty() && !"TIMEOUT".equalsIgnoreCase(refCore)) {
                req.setCoreRef(refCore);
                req.setStatus(CommonConstant.MASTERQR_STATUS_DIRECTDEBIT_SUCCESS);
            } else {
                req.setStatus(CommonConstant.MASTERQR_STATUS_DIRECTDEBIT_FAILED);
            }
            // thuc hien cap nhat xuong database
            int resultDB = Helper.getDBI().VISAQR(req, MasterCardQrActionEnum.UPDATE_CORE);
            // kiem tra xem cap nhat xuong database co thanh cong hay ko?
            if (resultDB == 1) {
                if (CommonConstant.MASTERQR_STATUS_DIRECTDEBIT_SUCCESS.equals(req.getStatus())) {
                    result = true;
                } else {
                    LOGGER.warn("Thuc hien goi qua core tru tien that bai");
                }
            } else {
                LOGGER.warn("Cap nhat database that bai");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result;
    }

    private MVISAQRRP mvisaQRCodeCredit(MVISAQRRQ req) {
        // Khai bao cac tham so dung sau nay
        MVISAQRRP response = new MVISAQRRP();
//        CardwordController cwController = new CardwordController();
        VisaController visaController = new VisaController();
        // thuc hien goi qua CW tru tien cua khach hang tren the credit
     //    ErrorCodeEnum directDebit = cwController.executeDirectDebitForVisa(req);
//giả lập để gọi visa
        ErrorCodeEnum directDebit = ErrorCodeEnum.CARDWORD_OK;
        // Kiem tra ket qua tra ve sau khi thuc hien cat tien
        switch (directDebit) {
            case CARDWORD_OK: {
                // Thuc hien goi qua doi tac Visa de thong bao cho merchant

                VisaMessage.VisaCodeEnum resMaster = visaController.executePayment(req);
                // Kiem tra ket qua sau khi thuc hien notify cho doi tac Visa
                switch (resMaster) {
                    case VISA_OK: // Notify cho doi tac Visa thanh cong
                        response.getMBResponse(CommontEnum.TRANSACTION_OK);
                        break;
                    case VISA_FAILED: // Visa thuc hien tra ve ma loi trong danh sach da duoc define va thuc hien hoan tien cho khach hang
                        response.getMBResponse(resMaster);
//                        cwController.executeCardAdjustmentForVisa(req);
                        break;
                    case VISA_OK_DBI_FAILED: // Thuc hien notify cho Visa thanh cong nhung cap nhat database that bai
                    case VISA_RETURN_DBI_FAILED: // Visa tra ve loi va cap nhat database that bai
                    default: // cac truoc hop con lai chua xac dinh
                        response.getMBResponse(resMaster);
                        break;
                }
                break;
            }
            default: { // thuc hien cat tien tai CW ko thanh cong
                response.getMBResponse(directDebit);
                break;
            }
        }
        return response;
    }
    /* VISA */

 /* VNPAY QR */
    final String vnpayqrBankCode = Configuration.getProperty("vnpayQR.bankCode");
    final String vnpayqrAccessKey = Configuration.getProperty("vnpayQR.accessKey");

    public String checkVNPAYQRCode(String xml) {
        VnpayController vnpayController = new VnpayController();
        CheckQRRp response = new CheckQRRp();
        try {
            CheckQRRq req = (CheckQRRq) Xml.unMarshaller(CheckQRRq.class, xml);
            req.preData(vnpayqrBankCode, vnpayqrAccessKey);
            CommontEnum dataIsValid = req.isValidate();
            switch (dataIsValid) {
                case DATA_IS_VALID:
                    String payCode = Helper.getDBI().INSERTCHECKQR(req);
                    if (payCode != null && !payCode.isEmpty()) {
                        req.setPayCode(payCode);
                        response = vnpayController.checkVNPAYQRCode(req);
                    } else {
                        response.getMBResponse(CommontEnum.DUPLICATE_SEQUENCENO);
                    }
                    break;
                default:
                    response.getMBResponse(dataIsValid);
                    break;
            }
            return Xml.Marshaller(response);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public String paymentVNPAYQR(String xml) {
        VnpayController vnpayController = new VnpayController();
        PaymentQRRp response = new PaymentQRRp();
        try {
            PaymentQRRq req = (PaymentQRRq) Xml.unMarshaller(PaymentQRRq.class, xml);
            CommontEnum dataIsValid = req.isValidate();
            switch (dataIsValid) {
                case DATA_IS_VALID:
                    PaymentQRJson qrPayment = Helper.getDBI().INSERTPAYMENTQR(req);
                    if (qrPayment != null) {
                        response = vnpayController.paymentVNPAYQR(req, qrPayment, vnpayqrAccessKey);
                    } else {
                        response.getMBResponse(CommontEnum.DUPLICATE_SEQUENCENO);
                    }
                    break;
                default:
                    response.getMBResponse(dataIsValid);
                    break;
            }
            return Xml.Marshaller(response);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    public String GetFeeMobile(String xml) {
        try {

            GetFeeMobileRq req = (GetFeeMobileRq) Xml.unMarshaller(GetFeeMobileRq.class, xml);
            GetFeeMobileRp response = new GetFeeMobileRp();

            response.setFromAccount(req.getFromAccount());
            response.setAmount(req.getAmount());
            response.setTxnTax("0");
            String branchCode = CommonUtils.getBranchAccount(req.getFromAccount());
            String product_code = "";
            if (req.getTxnType().equals("SMLACC") || req.getTxnType().equals("SMLCARD")) {
                product_code = ProductFromSCB_MOBILE;
            } else if (req.getTxnType().equals("OUTACC") || req.getTxnType().equals("OUTID")) {
                product_code = producttransferMobileOut;
            } else if (req.getTxnType().equals("INID")) {
                product_code = producttransferMobileIDRT;
            } else if ("VETC".equals(req.getTxnType())) {
                product_code = productgetfreeVETC;
            }

            String listToAccountFee = Configuration.getProperty("toaccount.forvacxin.freefee");
            String toaccount = req.getToAccount() + "#" + req.getBankCode();
            if (listToAccountFee != null && !"#".equals(toaccount) && listToAccountFee.contains(toaccount)) {
                response.setTxnFee("0");
            } else {
                if (!product_code.isEmpty()) {
                    ProcedureCallDto proReturn = Helper.getDBI().getFeeMobile(branchCode, product_code, "VND", req.getAmount(), req.getFromAccount(), branchCode);
                    // ProcedureCallDto proReturn = Helper.getDBI().getFeeMobileHasSFee(branchCode, product_code, "VND", req.getAmount(), req.getFromAccount(), branchCode);
                    if (proReturn.isSucess()) {
                        response.setTxnFee(proReturn.getErrorMessage());

                    } else {
                        Helper.writeLog(MobileUpgradeController.class, Level.INFO, "GetFeeMobile.ErrorStatus = " + proReturn.getErrorStatus());
                        Helper.writeLog(MobileUpgradeController.class, Level.INFO, "GetFeeMobile.ErrorMessage = " + proReturn.getErrorMessage());
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.ERROR_SYSTEM));
                    }
                } else {
                    response.setTxnFee("0");
                }
            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    public boolean checkAuthenPassMB(String usename, String cifno, String passInput, String typeCheck) {
        VnInfoGateway vnifo = new VnInfoGateway();
        try {
            String resultStr = "";
            if (typeCheck.equals("MBPASS")) {
                resultStr = vnifo.ValidatePasswordMobileBanking(passInput, usename, cifno);
            } else if (typeCheck.equals("MPIN")) {
                resultStr = vnifo.ValidatePinMobileBanking(passInput, usename, cifno);
            }
            String[] arrayResult = resultStr.split("#");
            if (arrayResult.length > 0 && arrayResult[0].equals("00")) {
                return true;
            } else {
                Helper.writeLogError(this.getClass(), "checkAuthenPass: arrayResult -  " + Arrays.toString(arrayResult) + " | " + usename + " | " + cifno + " | " + passInput);
                return false;
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "checkAuthenPass:" + e.toString());
            return false;
        }
    }

    public boolean checkAuthenPassMBNew(String usename, String cifno, String passInput, String typeCheck) {
        VnInfoGateway vnifo = new VnInfoGateway();
        try {
            passInput = AES.decrypt(passInput, myEncryptionKey);
            String resultStr = "";
            if (typeCheck.equals("MBPASS")) {
                resultStr = vnifo.ValidatePasswordMobileBanking(passInput, usename, cifno);
            } else if (typeCheck.equals("MPIN")) {
                resultStr = vnifo.ValidatePinMobileBanking(passInput, usename, cifno);
            }
            String[] arrayResult = resultStr.split("#");
            if (arrayResult.length > 0 && arrayResult[0].equals("00")) {
                return true;
            } else {
                Helper.writeLogError(this.getClass(), "checkAuthenPass: arrayResult[0] -  " + arrayResult.toString() + " | " + usename + " | " + cifno + " | " + passInput);
                return false;
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), "checkAuthenPass:" + e.toString());
            return false;
        }
    }

    public int validateTKLuongOpenTD(CheckOpenOnlineAzRq openReq) {
        try {
            if (openReq.getMaturityInstr().equals(MaturityInstr_AutoRoll_Principal) || openReq.getMaturityInstr().equals(MaturityInstr_Close) || openReq.getMaturityInstr().equals(MaturityInstr_Close_Principal)) {
                String account = null;
                if (openReq.getNominateAcc() != null && !openReq.getNominateAcc().isEmpty()) {
                    account = openReq.getNominateAcc();
                }
                if (openReq.getInterestAcc() != null && !openReq.getInterestAcc().isEmpty()) {
                    account = openReq.getInterestAcc();
                }
                if (account != null) {
                    IIBCurrentAccountService iibAccService = new IIBCurrentAccountService();
                    RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASA = iibAccService.retrieveCurrentAccountCASARestSimple(Configuration.getIIBContext(), account, IIB_CHANNEL_MOBILE);
                    if (retrieveCurrentAccountCASA.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                        if (retrieveCurrentAccountCASA.getAccountInfo().getAccountClassCode().equals(AccClassLuong)) {
                            return 1;
                        } else if (retrieveCurrentAccountCASA.getAccountInfo().getAccountCoownerName() != null && retrieveCurrentAccountCASA.getAccountInfo().getAccountCoownerName().length() > 0) {
                            return 2;
                        }
                    }
                }
            }
            return 0;
        } catch (Exception ex) {
            Helper.writeLogError(MobileController.class, "***- validateTKLuongOpenTD: " + ex.getMessage());
            return 1;
        }
    }

    public String PreChangePin(String xml) {
        try {
            PreChangePinRq req = (PreChangePinRq) Xml.unMarshaller(PreChangePinRq.class, xml);
            PreChangePinRp response = new PreChangePinRp();
            int isValidAuth = req.validateAuth();

            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                InitSessionToChangepinRq initSessionToChangepinRq = new InitSessionToChangepinRq();
                initSessionToChangepinRq.setPartnerId(CHANGEPIN_VNPAYPARTNER);
                initSessionToChangepinRq.setLoc(req.getCardAccountNumer());
                initSessionToChangepinRq.setLast4Digits(req.getCardNo().substring(req.getCardNo().length() - 4, req.getCardNo().length()));
                initSessionToChangepinRq.setMobileNo(req.getSodt());

                SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
                initSessionToChangepinRq.setCreateDate(s.format(new Date()));

                //String CHANGEPIN_KEYMD5 = "B0DC03E45CB8831F5CCCF9763H974587";
                String TRANSACTIONID = initSessionToChangepinRq.getLast4Digits().concat(String.valueOf(System.currentTimeMillis()));
                initSessionToChangepinRq.setTransactionId(TRANSACTIONID);

                String signate = ControllerUtil.EncriptMD5(initSessionToChangepinRq.getPartnerId()
                        .concat(initSessionToChangepinRq.getLoc())
                        .concat(initSessionToChangepinRq.getLast4Digits())
                        .concat(initSessionToChangepinRq.getCreateDate())
                        .concat(initSessionToChangepinRq.getTransactionId())
                        .concat(initSessionToChangepinRq.getMobileNo()).concat(CHANGEPIN_KEYMD5));
                initSessionToChangepinRq.setSignature(signate);

                IController controller = new ControllerImpl();
                String changePinStr = controller.initSessionToChangepin(Xml.Marshaller(initSessionToChangepinRq));
                InitSessionToChangepinRs intResponse = (InitSessionToChangepinRs) Xml.unMarshaller(InitSessionToChangepinRs.class, changePinStr);

                if (intResponse.getErrorCode().equals("00")) {
                    response.setAccessToken(intResponse.getAccessTokenKey());
                    response.setPublicKey(intResponse.getPublicKey());
                    response.setTxnID(intResponse.getRefTransactionId());
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
                } else {
                    Helper.writeLogError(MobileController.class, "***- PreChangePin: " + intResponse.getErrorCode());
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.NOTSUCCESS));
                }
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }
}
