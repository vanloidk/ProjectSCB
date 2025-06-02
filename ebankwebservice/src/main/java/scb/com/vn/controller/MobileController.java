/*
 * To change this template, choose Tools | Templates
 * and open the templimport java.util.List;ate in the editor.
 */
package scb.com.vn.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.beanutils.BeanUtils;
import scb.com.vn.utility.VNCharacterUtils;
import scb.com.vn.model.*;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.Helper;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.payment.Airline;
import scb.com.vn.common.model.payment.Billing;
import scb.com.vn.common.model.payment.Request;
import scb.com.vn.common.model.payment.RequestPayment;
import scb.com.vn.common.model.payment.Response;
import scb.com.vn.common.model.payment.ResponsePayment;
import scb.com.vn.common.model.payment.Schedule;
import scb.com.vn.common.model.payment.Vntopup;
import scb.com.vn.common.odbx.IsExistUserEBRes;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.constant.VNPTConstant;
import scb.com.vn.dbi.dto.CardType;
import scb.com.vn.dbi.dto.EbIssuecard;
import scb.com.vn.dbi.dto.PblBillpaid;
import scb.com.vn.dbi.dto.PblLog;
import scb.com.vn.dbi.dto.PblPartnerservice;
import scb.com.vn.dbi.dto.PblPartnerserviceId;
import scb.com.vn.dbi.dto.PblProvider;
import scb.com.vn.dbi.dto.PblServicetype;
import scb.com.vn.dbi.dto.PmtInfoV11ResDto;
import scb.com.vn.dbi.dto.ProcedureCallDto;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.dbi.dto.VwMbCustomer;
import scb.com.vn.message.Message.PaymentResultEnum;
import scb.com.vn.payment.ControllerUtil;
import scb.com.vn.payment.IBTController;
import scb.com.vn.payment.model.Processor;
import scb.com.vn.ultility.Common;
import scb.com.vn.utility.Configuration;
import scb.com.vn.message.Message;
import scb.com.vn.mobile.model.CreditCardTypeRq;
import scb.com.vn.mobile.model.GrantLuckNumberRq;
import scb.com.vn.model.billingvnpt.PaymentVneduVNPTRp;
import scb.com.vn.model.billingvnpt.PblPartnerService;
import scb.com.vn.model.card.BankCardDtlRp;
import scb.com.vn.model.card.BankCardDtlRq;
import scb.com.vn.payment.BillingDawacoController;
import scb.com.vn.payment.BillingVNPTController;
import scb.com.vn.payment.BillingVNPTMediaController;
import scb.com.vn.payment.Controller;
import scb.com.vn.payment.ONLPblController;
import static scb.com.vn.payment.ONLPblController.getPaymentMsgEnum;
//import scb.com.vn.payment.BillingVNPTController;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.CryptoUtils;
import scb.com.vn.utility.MessageMB;

import scb.com.vn.utility.MessageMB.MobileResultEnum;
import scb.com.vn.utility.TripleDESCard;
import vn.com.scb.bian.*;
import vn.com.scb.bian.service.IIBBillingPaymentService;
import vn.com.scb.bian.service.IIBCardService;
import vn.com.scb.bian.service.IIBDepositAccountService;
import vn.com.scb.bian.service.IIBCurrentAccountService;
import vn.com.scb.bian.service.IIBCustomerService;
import vn.com.scb.bian.service.IIBLoanAccountService;
import vn.com.scb.bian.service.IIBPaymentService;
import vn.com.scb.bian.service.IIBLetterOfCreditService;
import vn.com.scb.bian.utility.IIBConstants;

/**
 *
 * @author
 */
public class MobileController {

    private static String msgSendCore = "";
    private static final Logger logger = Logger.getLogger(MobileController.class);

    final int _SUCESSFUL = 0, _UNSUCESSFUL = -1;
    final char RELOAD_ACC_LIST = 1, UN_RELOAD_ACC_LIST = 0;
    final String ACCOUNT_CASA = "U", ACCOUNT_TD = "Y", ACCOUNT_LOAN = "L", ACCOUNT_MASTERCARD = "M", ACCOUNT_MASTERCARD_MC = "MC", ACCOUNT_MASTERCARD_MD = "MD",
            ACCOUNT_BL = "B";
    final String BILL_ISPREPAID = "1", BILL_NOT_ISPREPAID = "2";
    final String BILL_PAYMENTRULE_ALL = "1", BILL_PAYMENTRULE_ORDER = "2", BILL_PAYMENTRULE_ANY = "3", BILL_PAYMENTRULE_AMOUNT = "5";
    final String ATMISSUE_TYPE_NEW = "1", ATMISSUE_TYPE_REISSUE = "2", ATMISSUE_TYPE_EXT = "3", ATMISSUE_TYPE_SLAVE = "4";
    final String ID_CHANNEL_MOBILE = "03";
    final String ALERTTD_ON = "ON", ALERTTD_OFF = "OFF";
    String MaturityInstr_AutoRoll_Both = "1";
    String MaturityInstr_AutoRoll_Principal = "2";
    String MaturityInstr_Close = "3";
    String MaturityInstr_AutoRoll_Special = "4";
    String producttransferMobileIn = Configuration.getProperty("fcubs.producttransfer.mobile.IN");
    String producttransferMobilePrivate = Configuration.getProperty("fcubs.producttransfer.mobile.private");
    String productMobileFee = Configuration.getProperty("fcubs.producttransfer.mobile.Fee");
    String producttransferPaybill = Configuration.getProperty("fcubs.producttransfer.mobile.paybill");
    String useridMobile = Configuration.getProperty("fcubs.userid.mobile");
    String GLPaymentFee = Configuration.getProperty("fcubs.GLPayment.Fee");
    String productMobileCard = Configuration.getProperty("fcubs.producttransfer.mobile.Card");
    String GLPaymentCard = Configuration.getProperty("fcubs.GLPayment.Card");
    String producttransferMobileOut = Configuration.getProperty("fcubs.producttransfer.mobile.OUT");
    String GLPaymentDomestic = Configuration.getProperty("fcubs.GLPayment.domestic");
    String GLPaymentInternalId = Configuration.getProperty("fcubs.GLPayment.internalId");
    String producttransferMobileIDRT = Configuration.getProperty("fcubs.producttransfer.mobile.ID.RT");
    String TDAccountClassTennor = Configuration.getProperty("td.accountclass.tenor");
    String AccountClasssFEE = Configuration.getProperty("accountclass.sfee");
    String AccountClasssLOCPHAT = Configuration.getProperty("accountclass.locphat");
    String AccClassLuong = "CAI012";

    /**
     *
     */
    public static HashMap<String, String> hm_tetefirstnums = new HashMap<String, String>();

    /**
     *
     */
    public static List<String> arr_redemAccountclassList = null;

    //List<String> arr_accountclassTenor = null;
    final String FIRST_MASTERCARD_MC = "8", FIRST_MASTERCARD_MD = "2";
    final String IIB_CHANNEL_MOBILE = "MOBILE";
    final int SOA_SUCESSS_MESSAGE = 1;
    final String STATUS_CARD_HOATDONG = "O", STATUS_CARD_TAMKHOA = "L", STATUS_CARD_CHUAACTIVE = "D",
            STATUS_CARD_CLOSE = "C", STATUS_CARD_EXPIRED = "E";

    String TDCheckEODStart = Configuration.getProperty("td.checkEOD.start");
    String TDCheckEODEnd = Configuration.getProperty("td.checkEOD.end");
    Calendar calendarStart, calendarEnd;

    /**
     *
     */
    public static HashMap<String, ArrayList<BillServiceDetail>> hm_service;

    /**
     *
     */
    public static HashMap<String, ArrayList<BillProviderDetail>> hm_serviveprovider;

    /**
     *
     */
    public static boolean isInitial = false;

    /**
     *
     */
    public void init() {
        try {
            if (hm_service == null && !isInitial) {
                isInitial = true;
                String VN_NOTAUTO = "VN_N";
                String VN_AUTO = "VN_Y";
                String EN_NOTAUTO = "EN_N";
                String EN_AUTO = "EN_Y";

                hm_service = new HashMap<>();
                hm_service.put(VN_NOTAUTO, new ArrayList<BillServiceDetail>());
                hm_service.put(VN_AUTO, new ArrayList<BillServiceDetail>());
                hm_service.put(EN_NOTAUTO, new ArrayList<BillServiceDetail>());
                hm_service.put(EN_AUTO, new ArrayList<BillServiceDetail>());

                //all service key (LANGUAGE_HASAUTO : VN_Y  OR EN_N
                List allService = Helper.getDBI().getAllListServiceType();

                for (int i = 0; i < allService.size(); i++) {
                    HashMap _hm = (HashMap) allService.get(i);
                    String idservicetype = _hm.get("idservicetype").toString();
                    if (!"VNTOPUP".equals(idservicetype) && !"TRAFFICTOPUP".equals(idservicetype)) {
                        String name = _hm.get("name").toString();
                        String name_eng = _hm.get("name_eng").toString();

                        hm_service.get(VN_NOTAUTO).add(new BillServiceDetail(idservicetype, name));
                        hm_service.get(EN_NOTAUTO).add(new BillServiceDetail(idservicetype, name_eng));

                        String hasautopay = _hm.get("hasautopay").toString();
                        if ("Y".equals(hasautopay)) {
                            hm_service.get(VN_AUTO).add(new BillServiceDetail(idservicetype, name));
                            hm_service.get(EN_AUTO).add(new BillServiceDetail(idservicetype, name_eng));
                        }
                    }
                }

                //hm_service.get(VN_NOTAUTO).add(new BillServiceDetail("MNL", "Thanh toán phí bảo hiểm Manulife"));
                //hm_service.get(EN_NOTAUTO).add(new BillServiceDetail("MNL", "Manulife Vietnam"));
                List allProvider = Helper.getDBI().getAllListProvider();
                HashMap<String, BillProviderDetail> hm_provider = new HashMap<>();
                for (int i = 0; i < allProvider.size(); i++) {
                    HashMap _hm = (HashMap) allProvider.get(i);
                    String idpartner = _hm.get("idpartner") != null ? _hm.get("idpartner").toString() : "";
                    String note = _hm.get("note") != null ? _hm.get("note").toString() : "";
                    String idprovider = _hm.get("idprovider").toString();
                    String providername = _hm.get("providername").toString();
                    String providername_eng = _hm.get("providername_eng").toString();

                    hm_provider.put(idprovider.concat("_").concat(VN_NOTAUTO), new BillProviderDetail(idprovider, providername, idpartner, note));
                    hm_provider.put(idprovider.concat("_").concat(EN_NOTAUTO), new BillProviderDetail(idprovider, providername_eng, idpartner, note));

                    String autopay = _hm.get("autopay").toString();
                    if ("Y".equals(autopay)) {
                        hm_provider.put(idprovider.concat("_").concat(VN_AUTO), new BillProviderDetail(idprovider, providername, idpartner, note));
                        hm_provider.put(idprovider.concat("_").concat(EN_AUTO), new BillProviderDetail(idprovider, providername_eng, idpartner, note));
                    }
                }

                hm_serviveprovider = new HashMap<>();

                List allServiceProvider = Helper.getDBI().getAllListPartnerService();
                if (allServiceProvider != null && !allServiceProvider.isEmpty()) {
                    for (int i = 0; i < allServiceProvider.size(); i++) {
                        HashMap hm = (HashMap) allServiceProvider.get(i);
                        String idpartner = hm.get("idpartner").toString();
                        if (!"EVNHN".equals(idpartner) && !"EVNSPC".equals(idpartner)) {
                            String idservicetype = hm.get("idservicetype").toString();
                            String idprovider = hm.get("idprovider").toString();
                            String noteVn = Objects.toString(hm.get("note"), null);
                            String noteEn = Objects.toString(hm.get("note_eng"), null);

                            String key_VN_NOTAUTO = idservicetype.concat("_").concat(VN_NOTAUTO);
                            String key_VN_AUTO = idservicetype.concat("_").concat(VN_AUTO);
                            String key_EN_NOTAUTO = idservicetype.concat("_").concat(EN_NOTAUTO);
                            String key_EN_AUTO = idservicetype.concat("_").concat(EN_AUTO);

                            if (!hm_serviveprovider.containsKey(key_VN_NOTAUTO)) {
                                hm_serviveprovider.put(key_VN_NOTAUTO, new ArrayList<BillProviderDetail>());
                                hm_serviveprovider.put(key_VN_AUTO, new ArrayList<BillProviderDetail>());
                                hm_serviveprovider.put(key_EN_NOTAUTO, new ArrayList<BillProviderDetail>());
                                hm_serviveprovider.put(key_EN_AUTO, new ArrayList<BillProviderDetail>());
                            }

                            if (StringUtils.isNotBlank(noteVn)) {
                                BillProviderDetail providerVnNote = new BillProviderDetail();
                                BillProviderDetail initProviderVn = hm_provider.get(idprovider.concat("_").concat(VN_NOTAUTO));

                                providerVnNote.setIdprovider(initProviderVn.getIdprovider());
                                providerVnNote.setProvidername(initProviderVn.getProvidername());
                                providerVnNote.setIdPartner(initProviderVn.getIdPartner());
                                providerVnNote.setNote(noteVn);
                                hm_serviveprovider.get(key_VN_NOTAUTO).add(providerVnNote);

                                BillProviderDetail providerEnNote = new BillProviderDetail();
                                BillProviderDetail initProviderEn = hm_provider.get(idprovider.concat("_").concat(EN_NOTAUTO));

                                providerEnNote.setIdprovider(initProviderEn.getIdprovider());
                                providerEnNote.setProvidername(initProviderEn.getProvidername());
                                providerEnNote.setIdPartner(initProviderEn.getIdPartner());
                                providerEnNote.setNote(noteEn);
                                hm_serviveprovider.get(key_EN_NOTAUTO).add(providerEnNote);

                            } else {
                                hm_serviveprovider.get(key_VN_NOTAUTO).add(hm_provider.get(idprovider.concat("_").concat(VN_NOTAUTO)));
                                hm_serviveprovider.get(key_EN_NOTAUTO).add(hm_provider.get(idprovider.concat("_").concat(EN_NOTAUTO)));
                            }
                            if (hm_provider.containsKey(idprovider.concat("_").concat(VN_AUTO))) {
                                hm_serviveprovider.get(key_VN_AUTO).add(hm_provider.get(idprovider.concat("_").concat(VN_NOTAUTO)));
                                hm_serviveprovider.get(key_EN_AUTO).add(hm_provider.get(idprovider.concat("_").concat(EN_NOTAUTO)));
                            }
                        }
                    }
                } else {
                    Helper.writeLogError(MobileController.class, "- init getAllListPartnerService -null");
                }
            }
        } catch (Exception e) {
            Helper.writeLogError(MobileController.class, e);

        }
    }


    /*
     * Query detail info of customer
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String GetCustomerInfo(String xml) {
        try {
            CustomerRq req = (CustomerRq) Xml.unMarshaller(CustomerRq.class, xml);
            CustomerRp response = new CustomerRp();

            List custInfo = Helper.getDBI().getCustomerInfoForMB(req.getRequestType(), req.getRequestValue());
            if (custInfo == null || custInfo.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.NO_CUSTOMER_EXISTS));
            }

            BeanUtils.copyProperties(response, custInfo.get(0));

            //change value again as desciption
            if (response.getSex() != null) {
                if (response.getSex().equals("F")) {
                    response.setSex("0");
                } else if (response.getSex().equals("M")) {
                    response.setSex("1");
                }
            } else {
                response.setSex("-1");
            }

            if (response.getDate_of_birth() != null) {
                response.setDate_of_birth(changeFormatDate(response.getDate_of_birth()));
            }

            //set address = address1+address2+address3+address4
            if (response.getAddress1() != null) {
                String address = response.getAddress1();
                if (response.getAddress2() != null) {
                    address += response.getAddress2();
                    if (response.getAddress3() != null) {
                        address += response.getAddress3();
                        if (response.getAddress4() != null) {
                            address += response.getAddress4();
                        }
                    }
                }
                response.setAddress1(address);
            }
            //modify temp get core 'ebanking@scb.com.vn'
            if (response.getEmail() != null
                    && response.getEmail().equalsIgnoreCase("ebanking@scb.com.vn")) {
                response.setEmail("");
            }

            if (response.getCustcategory().equals("I_11")) {
                response.setIsDomesticCust("Y");
            } else {
                response.setIsDomesticCust("N");
            }

            if (!response.getFlgsingle().equalsIgnoreCase("I")) {
                response.setIdcorporate(null);
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.NO_CUSTOMER_EXISTS));
            }

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * Query detail info of customer
     * 
     * @param xml
     * @return
     */
    public String GetCustomerInfoNew(String xml) {
        try {
            CustomerRq req = (CustomerRq) Xml.unMarshaller(CustomerRq.class, xml);
            CustomerRp response = new CustomerRp();

            IIBCustomerService iibCustomerService = new IIBCustomerService();
            RetrieveCustomerRefDataMgmt_out_Type retrieveCustomerRefDataMgmt = iibCustomerService.retrieveCustomerRefDataMgmtSimple(Configuration.getIIBContext(), req.getRequestValue(), IIB_CHANNEL_MOBILE);
            if (!retrieveCustomerRefDataMgmt.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            }
            CustomerInfoType customerinfo = retrieveCustomerRefDataMgmt.getCustomerInfo();
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * Get all accounts of CIF
     *
     * @param xml
     * @return
     */
    public String GetAccountListOLD(String xml) {
        try {
            AccountListRq req = (AccountListRq) Xml.unMarshaller(AccountListRq.class, xml);
            AccountListRp response = new AccountListRp();

            // NOT SUPPORT OLD VERSION
            //return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOTSUPPORT_OLD_VERSION));
            String custno = req.getCifNo();
            List listAccount = Helper.getDBI().GetAccountListByCustNo(custno);
            if (listAccount == null || listAccount.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }

            AccountDetail[] accountDetails = new AccountDetail[listAccount.size()];
            for (int i = 0; i < listAccount.size(); i++) {
                accountDetails[i] = new AccountDetail();
                BeanUtils.copyProperties(accountDetails[i], listAccount.get(i));
            }

            response.setListAccount(accountDetails);
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * Get all accounts of CIF
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountList(String xml) {
        try {
            AccountListRq req = (AccountListRq) Xml.unMarshaller(AccountListRq.class, xml);
            AccountListRp response = new AccountListRp();

            String custno = req.getCifNo();

            List listAccount = Helper.getDBI().GetAccountListByCustNo(custno);
            ArrayList<AccountDetail> accountDetailList = new ArrayList<AccountDetail>();
            if (listAccount != null && !listAccount.isEmpty()) {
                for (int i = 0; i < listAccount.size(); i++) {
                    AccountDetail accountDetail = new AccountDetail();
                    BeanUtils.copyProperties(accountDetail, listAccount.get(i));
                    accountDetailList.add(accountDetail);
                }
            }

            IIBCardService iibCardService = new IIBCardService();
            SelectCreditCardSumary_out_Type selectCreditCardSumary = iibCardService.selectCreditCardSumarySimple(Configuration.getIIBContext(), req.getCifNo(), null, IIB_CHANNEL_MOBILE);
            if (selectCreditCardSumary.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                List<CardInfoType> cardList = selectCreditCardSumary.getCardInfo();
                for (CardInfoType carddetail : cardList) {
                    AccountDetail detail = new AccountDetail();
                    //detail.setAccountbalance(String.valueOf(carddetail.getCardOutstandingAmount()));
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
                    detail.setBranchcode(carddetail.getCardOpenBranchCode());
                    detail.setCardDescription(carddetail.getCardType());
                    detail.setCardStatus(carddetail.getCardStatus());
                    detail.setCreditType(carddetail.getCardBrand());
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
                    accountDetailList.add(detail);
                }
            }
            if (accountDetailList.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            } else {
                response.setListAccount((AccountDetail[]) accountDetailList.toArray(new AccountDetail[accountDetailList.size()]));
            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * Get detail of account
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountInfo(String xml) {
        try {
            AccountInfoRq req = (AccountInfoRq) Xml.unMarshaller(AccountInfoRq.class, xml);
            AccountInfoRp response = new AccountInfoRp();
            String accountno = req.getAccountNo();
            String AccountGroupCode = req.getAccountGroupCode();
            response.setAccountNo(accountno);
            if (AccountGroupCode.equals(ACCOUNT_CASA)) {

                List accountCasa = Helper.getDBI().getCASAccountHasLimitMB(accountno);
                if (accountCasa == null || accountCasa.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }

                BeanUtils.copyProperties(response, accountCasa.get(0));
                response.setAcctopendt(changeFormatDate(response.getAcctopendt()));

            } else if (AccountGroupCode.equals(ACCOUNT_TD)) {
                List accountTd = Helper.getDBI().getTdAccountByAccountNo(accountno);
                if (accountTd == null || accountTd.isEmpty()) {
                    //check for TIET KIEM KKH
                    List accountCasa = Helper.getDBI().getCASAccountHasLimitMB(accountno);
                    if (accountCasa == null || accountCasa.isEmpty()) {
                        return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                    }
                    BeanUtils.copyProperties(response, accountCasa.get(0));
                    response.setAcctopendt(changeFormatDate(response.getAcctopendt()));
                    //return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                } else {
                    HashMap hm_Td = (HashMap) accountTd.get(0);
                    response.setPrdcode(hm_Td.get("accountclass").toString());
                    response.setPrdname(hm_Td.get("proddesc").toString());
                    response.setCodacctcurr(hm_Td.get("ccy").toString());
                    response.setNumbalance(hm_Td.get("depositamt").toString());
                    response.setCodbranch(hm_Td.get("codbranch").toString());
                    response.setNumavailbal(hm_Td.get("numavlbalance").toString());
                    response.setAcctopendt(hm_Td.get("depositdate").toString());
                    response.setMaturitydate(hm_Td.get("maturitydate").toString());
                    response.setRate(hm_Td.get("rate").toString());
                    response.setTerm(hm_Td.get("term").toString());
                    if (req.getLanguage() == null || req.getLanguage().isEmpty() || req.getLanguage().equals("VN")) {
                        response.setTerm(response.getTerm().concat(hm_Td.get("termtype").toString()));
                    } else {
                        String termtype = hm_Td.get("termtype").toString();
                        if (termtype.equals(" tháng")) {
                            response.setTerm(response.getTerm().concat(" months"));
                        } else if (termtype.equals(" ngày")) {
                            response.setTerm(response.getTerm().concat(" days"));
                        } else if (termtype.equals(" năm")) {
                            response.setTerm(response.getTerm().concat(" years"));
                        }
                    }

                    response.setAcctopendt(changeFormatDate(response.getAcctopendt()));
                    response.setMaturitydate(changeFormatDate(response.getMaturitydate()));
                }
            } else if (AccountGroupCode.equals(ACCOUNT_LOAN)) {
                List accountLoan = Helper.getDBI().getLoanAccountByAccountNo(accountno);
                if (accountLoan == null || accountLoan.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }

                HashMap hm_Loan = (HashMap) accountLoan.get(0);
                BeanUtils.copyProperties(response, accountLoan.get(0));
                response.setCodacctcurr(hm_Loan.get("loaitien").toString());//accountLoan.getLoaitien());
                response.setAcctopendt(hm_Loan.get("ngaymotaikhoan").toString());
                response.setCodbranch(hm_Loan.get("machinhanh").toString());
                response.setPrdcode(hm_Loan.get("masanpham").toString());
                response.setPrdname(hm_Loan.get("tensanpham").toString());
                //change format date
                response.setAcctopendt(changeFormatDate(response.getAcctopendt()));
                response.setNgaydaohan(changeFormatDate(response.getNgaydaohan()));
                response.setNgayvay(changeFormatDate(response.getNgayvay()));

            } else if (AccountGroupCode.equals(ACCOUNT_MASTERCARD) || AccountGroupCode.equals(ACCOUNT_MASTERCARD_MC) || AccountGroupCode.equals(ACCOUNT_MASTERCARD_MD)) {
                List accountMC = Helper.getDBI().getMaterCardDetailByCardno(accountno);
                if (accountMC == null || accountMC.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }

                HashMap hm_Loan = (HashMap) accountMC.get(0);
                BeanUtils.copyProperties(response, accountMC.get(0));
                response.setAcctopendt(hm_Loan.get("active_date").toString());
                response.setCodacctcurr("VND");//
                response.setPrdcode(hm_Loan.get("card_type").toString());
                response.setPrdname(hm_Loan.get("card_desc").toString());
                response.setCodbranch(hm_Loan.get("brch").toString().substring(0, 3));
                //response.setNgaytttoithieu(changeFormatDate(response.getNgaytttoithieu()));
            }

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String GetAccountInfoNew(String xml) {
        try {
            AccountInfoRq req = (AccountInfoRq) Xml.unMarshaller(AccountInfoRq.class, xml);
            AccountInfoRp response = new AccountInfoRp();
            String accountno = req.getAccountNo();
            String AccountGroupCode = req.getAccountGroupCode();
            response.setAccountNo(accountno);
            if (req.getLanguage() == null || req.getLanguage().isEmpty() || req.getLanguage().equals("VN")) {
                req.setLanguage("VN");
            }
            if (AccountGroupCode.equals(ACCOUNT_CASA)) {
                IIBCurrentAccountService iibAccService = new IIBCurrentAccountService();
                RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASA = iibAccService.retrieveCurrentAccountCASARestSimple(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);
                if (!retrieveCurrentAccountCASA.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                }
                AccountInfoType accountdetailIIB = retrieveCurrentAccountCASA.getAccountInfo();
                response.setAccountNo(accountdetailIIB.getAccountNum());
                response.setCodacctcurr(accountdetailIIB.getAccountCurrency());
                response.setLimit_amount(accountdetailIIB.getAccountOverdraftLimit());
                //response.setNumavailbal(BigDecimal.valueOf(accountdetailIIB.getAccountBalanceAvailable()).toPlainString());
                //response.setNumbalance(String.valueOf(accountdetailIIB.getAccountBalance().longValue()));
                response.setNumavailbal(accountdetailIIB.getAccountBalanceAvailable().toPlainString());
                if (accountdetailIIB.getAccountBalanceAvailable().compareTo(BigDecimal.ZERO) == -1) {
                    response.setNumavailbal("0");
                }
                response.setNumbalance(accountdetailIIB.getAccountBalance().toPlainString());

                response.setPrdcode(accountdetailIIB.getAccountClassCode());
                response.setPrdname(accountdetailIIB.getAccountClassName());
                response.setCodbranch(accountdetailIIB.getAccountOpenBrandCode());
                response.setAcctopendt(changeFormatDate(accountdetailIIB.getAccountOpenDate()));
                response.setFullName(accountdetailIIB.getCustomerInfo().getFullname());
                response.setBranchname(accountdetailIIB.getAccountOpenBrandName());
                if (AccountClasssFEE.contains(accountdetailIIB.getAccountClassCode()) || AccountClasssLOCPHAT.contains(accountdetailIIB.getAccountClassCode())) {
                    List avlAvgBal = Helper.getDBI().getAvgBalanceFromCore(accountno);
                    if (avlAvgBal == null || avlAvgBal.isEmpty()) {
                        response.setLastMonthAvgBal("0");
                        response.setCurMonthAvgBal("0");
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT_TYPE));
                    } else {
                        /*
                         if (AccountClasssLOCPHAT.contains(accountdetailIIB.getAccountClassCode())) {
                              HashMap hm_sfree = (HashMap) avlAvgBal.get(0); 
                              response.setLastMonthAvgBal(hm_sfree.get("lastMonthAvgBal").toString());                            
                         } else {
                             BeanUtils.copyProperties(response, avlAvgBal.get(0));
                         }*/
                        BeanUtils.copyProperties(response, avlAvgBal.get(0));
                    }
                }

            } else if (AccountGroupCode.equals(ACCOUNT_TD)) {
                IIBDepositAccountService iibAccService = new IIBDepositAccountService();
                RetrieveDepositAccountTD_out_Type retrieveDepositAccountTD_out_Type = iibAccService.retrieveDepositAccountTDSimple(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);
                if (retrieveDepositAccountTD_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    AccountInfoType accountdetailIIB = retrieveDepositAccountTD_out_Type.getAccountInfo();
                    response.setPrdcode(accountdetailIIB.getAccountClassCode());
                    response.setPrdname(accountdetailIIB.getAccountClassName());
                    response.setCodacctcurr(accountdetailIIB.getAccountCurrency());
                    response.setNumavailbal(accountdetailIIB.getAccountBalanceAvailable().toPlainString());
                    response.setNumbalance(accountdetailIIB.getAccountBalance().toPlainString());
                    //response.setAcctopendt(changeFormatDate(accountdetailIIB.getAccountOpenDate()));
                    response.setAcctopendt(changeFormatDate(accountdetailIIB.getAccountOfficialOpenDate()));
                    response.setMaturitydate(changeFormatDate(accountdetailIIB.getAccountMaturityDate()));
                    response.setRate(accountdetailIIB.getAccountInterestRate());
                    //them cho QR tiet kiem
                    response.setFullName(accountdetailIIB.getAccountName());

                    if (accountdetailIIB.getCustomerInfo() != null && accountdetailIIB.getCustomerInfo().getFullname() != null) {
                        response.setFullName(accountdetailIIB.getCustomerInfo().getFullname());
                    }

                    String acountCoownerName = accountdetailIIB.getAccountCoownerName();
                    if (acountCoownerName != null && !acountCoownerName.isEmpty()) {
                        response.setFullName(response.getFullName().concat("_").concat(acountCoownerName));
                    }
                    response.setSavingSerials(accountdetailIIB.getAccountSavingSerials());
                    String savingStatus = accountdetailIIB.getAccountLockStatus();

                    //set redemtion  
                    response.setIsRedem("N");
                    if (arr_redemAccountclassList == null) {
                        arr_redemAccountclassList = Arrays.asList(Helper.getDBI().getListAccountClassRedemEbank());
                    }
                    if (arr_redemAccountclassList.contains(accountdetailIIB.getAccountClassCode())) {
                        response.setIsRedem("Y");
                    }
                    response.setIsOwner("Y");
                    if (req.getLanguage().equals("VN")) {
                        response.setTerm(accountdetailIIB.getAccountTerm());
                        if (savingStatus.equals("N")) {
                            response.setSavingStatus("Hoạt động");
                        } else {
                            response.setSavingStatus("Phong tỏa");
                        }
                    } else {
                        if (accountdetailIIB.getAccountTerm() != null && !accountdetailIIB.getAccountTerm().isEmpty()) {
                            String[] accountterms = accountdetailIIB.getAccountTerm().trim().split(" ");
                            String termType = "";
                            if (accountterms.length > 0) {
                                termType = accountterms[0];
                            }
                            if (accountterms.length > 1) {
                                if (accountterms[1].equalsIgnoreCase("thang")) {
                                    termType = termType.concat(" months");
                                } else if (accountterms[1].equalsIgnoreCase("ngay")) {
                                    termType = termType.concat(" days");
                                } else if (accountterms[1].equalsIgnoreCase("nam")) {
                                    termType = termType.concat(" years");
                                }
                            }
                            response.setTerm(termType);
                        }
                        if (savingStatus.equals("N")) {
                            response.setSavingStatus("Active");
                        } else {
                            response.setSavingStatus("Block");
                        }
                    }
                } else {
                    IIBCurrentAccountService iibCurrAccService = new IIBCurrentAccountService();
                    RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASA = iibCurrAccService.retrieveCurrentAccountCASARestSimple(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);
                    if (!retrieveCurrentAccountCASA.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT_QR));
                    }
                    AccountInfoType accountdetailIIB = retrieveCurrentAccountCASA.getAccountInfo();
                    response.setAccountNo(accountdetailIIB.getAccountNum());
                    response.setCodacctcurr(accountdetailIIB.getAccountCurrency());
                    response.setNumavailbal(accountdetailIIB.getAccountBalanceAvailable().toPlainString());
                    response.setNumbalance(accountdetailIIB.getAccountBalance().toPlainString());
                    response.setPrdcode(accountdetailIIB.getAccountClassCode());
                    response.setPrdname(accountdetailIIB.getAccountClassName());
                    response.setCodbranch(accountdetailIIB.getAccountOpenBrandCode());
                    //them cho QR tiet kiem
                    response.setFullName(accountdetailIIB.getAccountName());
                    response.setSavingSerials(accountdetailIIB.getAccountSavingSerials());
                    String savingStatus = accountdetailIIB.getAccountLockStatus();
                    if (req.getLanguage().equals("VN")) {
                        if (savingStatus.equals("N")) {
                            response.setSavingStatus("Hoạt động");
                        } else {
                            response.setSavingStatus("Phong tỏa");
                        }
                    } else {
                        if (savingStatus.equals("N")) {
                            response.setSavingStatus("Active");
                        } else {
                            response.setSavingStatus("Block");
                        }
                    }
                }
            } else if (AccountGroupCode.equals(ACCOUNT_LOAN)) {
                IIBLoanAccountService iibAccService = new IIBLoanAccountService();
                RetrieveLoanSumary_out_Type retrieveLoanSumary = iibAccService.retrieveLoanSumaryRestSimple(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);
                if (!retrieveLoanSumary.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                }
                LoanAccountInfoType loanAccountInfo = retrieveLoanSumary.getLoanAccountInfo();
                response.setCodacctcurr(loanAccountInfo.getLoanCurrency());
                response.setAcctopendt(loanAccountInfo.getLoanEffectDate());
                response.setCodbranch(loanAccountInfo.getLoanBranchCode());
                response.setPrdcode(loanAccountInfo.getLoanProductCode());
                response.setPrdname(loanAccountInfo.getLoanProductName());
                //change format date
                response.setAcctopendt(changeFormatDate(response.getAcctopendt()));
                response.setNgaydaohan(changeFormatDate(loanAccountInfo.getLoanMaturityDate()));
                response.setNgayvay(changeFormatDate(loanAccountInfo.getLoanEffectDate()));
                response.setDunogocconlai(loanAccountInfo.getLoanOutstandingBalance());
                response.setLaisuat(BigDecimal.valueOf(Double.valueOf(loanAccountInfo.getLoanInterestRate())));
                //kimncm bo sung cac gia trị khac tk vay

                response.setDunogoc(loanAccountInfo.getLoanDisbursmentAmount());
                response.setNgayvay(changeFormatDate(loanAccountInfo.getLoanEffectDate()));
                response.setNgaydaohan(changeFormatDate(loanAccountInfo.getLoanMaturityDate()));
                response.setDunogocdatra(loanAccountInfo.getLoanPrincipalPaid());
                response.setLaivaydatra(loanAccountInfo.getLoanInterestPaid());
                response.setLaivay(loanAccountInfo.getLoanInterestDue());

                response.setDuNoGocDenHan(loanAccountInfo.getLoanPrincipalDue());
                response.setLaiDenHan(loanAccountInfo.getLoanInterestDue());
                response.setPhatChamTraLai(loanAccountInfo.getLoanPernalIntDue());
                response.setPhatChamTraGoc(loanAccountInfo.getLoanPernalPrinDue());
            } else if (AccountGroupCode.equals(ACCOUNT_MASTERCARD) || AccountGroupCode.equals(ACCOUNT_MASTERCARD_MC) || AccountGroupCode.equals(ACCOUNT_MASTERCARD_MD)) {
                IIBCardService iibCardService = new IIBCardService();
                RetrieveCreditCardDetail_out_Type retrieveCreditCardDetail_out_Type = iibCardService.retrieveCreditCardDetailSimple(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);
                if (!retrieveCreditCardDetail_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                }
                CardInfoType carddetailIIB = retrieveCreditCardDetail_out_Type.getCardInfo();

                Helper.writeLogError(this.getClass(), "KIM DETAIL - Tinh trang the: " + ToStringBuilder.reflectionToString(carddetailIIB));

                response.setAccountNo(req.getAccountNo());
                response.setAcctopendt(carddetailIIB.getCardActivatedDate());
                response.setCardaccountno(carddetailIIB.getCardAccountNum());
                response.setCardno(carddetailIIB.getHiddenCardNum());
                response.setCardNoHidden(carddetailIIB.getHiddenCardNum());
                response.setCodacctcurr(carddetailIIB.getCardCurrency());
                response.setDunocuoiky(carddetailIIB.getCardInstallmentAmount());
                //response.setDunodasudung(carddetailIIB.getCardOutstandingAmount());
                response.setDunodasudung(carddetailIIB.getCardOutstandingAmountNotUnpost());
                response.setDunothekhac(carddetailIIB.getCardOtherAccountOutstanding());
                response.setExpiredate(carddetailIIB.getCardExpiredDate());
                response.setHanmuc(carddetailIIB.getCardLimit());
                response.setHanmucconlai(carddetailIIB.getCardCurrentLimit());
                response.setHanmucconlaikh(carddetailIIB.getCardCustomerCurrentLimit());
                response.setNgaytttoithieu(carddetailIIB.getCardInstallmentDate());
                response.setPrdname(carddetailIIB.getCardType());
                response.setThanhtoantoithieu(carddetailIIB.getCardMinimumInstallmentAmount());
                response.setTinhchatthe(carddetailIIB.getCardProperty());
                response.setHanmuckh(carddetailIIB.getCardCustomerTotalLimit());

                response.setCardIPPCurrentAmount(carddetailIIB.getCardIPPCurrentAmount());
                response.setCardIPPStatementAmount(carddetailIIB.getCardIPPStatementAmount());
                //Tong Số tiền thanh toán tối thiểu
                BigDecimal SumAmtLimitPay = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardMinimumInstallmentAmount()));
                BigDecimal CardIPPStatementAmount = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardIPPStatementAmount()));
                SumAmtLimitPay = SumAmtLimitPay.add(CardIPPStatementAmount);
                //Tong Số tiền đã thanh toán trong kỳ sao kê
                BigDecimal SumPaidAmount = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardPaidCurrentAmount()));
                BigDecimal cardIPPPaidAmount = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardIPPPaidAmount()));
                SumPaidAmount = SumPaidAmount.add(cardIPPPaidAmount);
                //Tổng dư nợ kỳ sao ke gan nhat                
                BigDecimal SumPrincipalToPay = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardInstallmentAmount()));
                SumPrincipalToPay = SumPrincipalToPay.add(CardIPPStatementAmount);

                response.setSumAmtLimitPay(SumAmtLimitPay.toPlainString());
                response.setSumPaidAmount(SumPaidAmount.toPlainString());
                response.setSumPrincipalToPay(SumPrincipalToPay.toPlainString());

                if (carddetailIIB.getCardOpenBranchCode() != null && carddetailIIB.getCardOpenBranchCode().length() > 3) {
                    response.setCodbranch(carddetailIIB.getCardOpenBranchCode().substring(0, 3));
                }
                response.setTinhtrangthe(carddetailIIB.getCardStatus());
                if (carddetailIIB.getCardOnlineStatus().equals("0")) {
                    response.setBlockonline("O");
                } else {
                    response.setBlockonline("L");
                }
                if (carddetailIIB.getCardCasaAccount() != null) {
                    response.setDefaultcasa(carddetailIIB.getCardCasaAccount());
                }
                response.setUnPostAmount(carddetailIIB.getCardUnpostAmount());
                //do tablet ko lay unpost, mobile lay, nen fai tru
                /*
                if (carddetailIIB.getCardUnpostAmount() != null && carddetailIIB.getCardUnpostAmount().length() > 0) {
                    BigDecimal dunosudung = BigDecimal.valueOf(Double.valueOf(response.getDunodasudung()));
                    BigDecimal unpost = BigDecimal.valueOf(Double.valueOf(response.getUnPostAmount()));
                    dunosudung = dunosudung.subtract(unpost);
                    response.setDunodasudung(dunosudung.toBigInteger().toString());
                }
                 */
                if (response.getTinhtrangthe().endsWith("The Dang Hoat Dong")) {
                    response.setCardStatus("A");
                } else {
                    response.setCardStatus("U");
                }

                if (carddetailIIB.getCardBrand().toUpperCase().equals("MASTERCARD")) {
                    response.setLoaithe("MC");
                } else {
                    response.setLoaithe("VS");
                }
                //response.setLoaithe(carddetailIIB.getCardBrand());                 
                String tinhtrangthe = response.getTinhtrangthe();
                if (tinhtrangthe != null) {
                    tinhtrangthe = tinhtrangthe.trim();
                    if (tinhtrangthe.equals("The Dang Hoat Dong")) {
                        response.setTinhtrangthe(STATUS_CARD_HOATDONG);
                    } else if (tinhtrangthe.equals("The Tam Khoa")) {
                        response.setTinhtrangthe(STATUS_CARD_TAMKHOA);
                    } else if (tinhtrangthe.equals("The Chua Active")) {
                        response.setTinhtrangthe(STATUS_CARD_CHUAACTIVE);
                    } else if (tinhtrangthe.equals("The Het Han")) {
                        response.setTinhtrangthe(STATUS_CARD_EXPIRED);
                    } else if (tinhtrangthe.equals("The Dong")) {
                        response.setTinhtrangthe(STATUS_CARD_CLOSE);
                    } else {
                        Helper.writeLogError(this.getClass(), "GetAccountInfoNew - pancecd: " + req.getAccountNo());
                        Helper.writeLogError(this.getClass(), "GetAccountInfoNew - Tinh trang the: " + tinhtrangthe);
                        response.setTinhtrangthe("X");
                    }
                    if (!response.getTinhtrangthe().equals("X")) {
                        if (req.getLanguage().equals("VN")) {
                            response.setTinhtrangthe(MobileUpgradeController.CARD_TYPE_NAME.valueOf(response.getTinhtrangthe()).name);
                        } else {
                            response.setTinhtrangthe(MobileUpgradeController.CARD_TYPE_NAME_ENG.valueOf(response.getTinhtrangthe()).name_eng);
                        }
                    }
                }

                //thêm các field cho thẻ 68 ngày
                response.setCardProduct("THE");
                /*
                response.setCardProduct(carddetailIIB.getCardProduct());
                if (response.getCardProduct().equals("WORLD")){
                    response.setCardInstallmentAmountPre (carddetailIIB.getCardInstallmentAmountPre());
                    response.setCardMinimumInstallmentAmountPre(carddetailIIB.getCardMinimumInstallmentAmountPre());
                    response.setCardPaidCurrentAmountPre(carddetailIIB.getCardPaidCurrentAmountPre());
                    response.setCardInstallmentDatePre (carddetailIIB.getCardInstallmentDatePre());
                }*/
                BigDecimal DuNoTheDenHienTai = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardOutstandingAmountNotUnpost()));
                BigDecimal DuNoTraGopDenHienTai = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardIPPCurrentAmount()));
                BigDecimal SumDuNoDenHienTai = DuNoTheDenHienTai.add(DuNoTraGopDenHienTai);
                response.setNumbalance(SumDuNoDenHienTai.toPlainString());
                response.setCardDescription(carddetailIIB.getCardType());
                response.setCardTypeCode(carddetailIIB.getCardTypeCode());
                if (carddetailIIB.getCardOwnerName() != null) {
                    String ownerName = StringUtils.substringBefore(carddetailIIB.getCardOwnerName(), ",");
                    response.setCardOwnerName(ownerName);
                }
                if (!"VC".equals(carddetailIIB.getCardImageId())) {
                    if (tinhtrangthe != null && tinhtrangthe.equals("The Chua Active")) {
                        if (carddetailIIB.getHiddenCardNum() != null && carddetailIIB.getHiddenCardNum().length() > 1) {
                            String subCardNo = carddetailIIB.getHiddenCardNum().substring(carddetailIIB.getHiddenCardNum().length() - 2, carddetailIIB.getHiddenCardNum().length());
                            response.setCardNoHidden("XXXXXXXXXXXXXX".concat(subCardNo));
                        }
                    }
                    response.setImageCard("N"); // the vat ly
                } else {
                    response.setImageCard("VC");//the phi vat ly
                }
            } else if (AccountGroupCode.equals(ACCOUNT_BL)) {
                IIBLetterOfCreditService iibLCService = new IIBLetterOfCreditService();
                RetrieveLetterOfCreditInfo_out_Type retrieveLetterOfCreditInfo = iibLCService.retrieveLetterOfCredit(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);
                // do RetrieveLetterOfCreditInfo viet khong giong cac ham khac getTransactionErrorCode 00 thanh cong
                if (!retrieveLetterOfCreditInfo.getTransactionInfo().getTransactionErrorCode().equals("00")) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                }

                List<LetterOfCreditInfoType> letterOfCreditInfoList = retrieveLetterOfCreditInfo.getLetterOfCreditInfo();
                if (letterOfCreditInfoList != null && letterOfCreditInfoList.size() > 0) {
                    LetterOfCreditInfoType letterOfCreditInfo = letterOfCreditInfoList.get(0);
                    response.setSavingSerials(letterOfCreditInfo.getSerialNo());
                    response.setTrnref(letterOfCreditInfo.getTrnRef());
                    response.setNumbalance(letterOfCreditInfo.getAmount());
                    response.setCodacctcurr(letterOfCreditInfo.getCurrency());
                    response.setExpiredate(letterOfCreditInfo.getToDate());
                    response.setReceiveName(letterOfCreditInfo.getReceiveName());
                    response.setProviderName(letterOfCreditInfo.getProviderName());
                    response.setBranchname(letterOfCreditInfo.getBranchInfo().getBranchName());
                }

            }
            /*
             List accountMC = Helper.getDBI().getMaterCardDetailByCardnoNew(accountno);
             if (accountMC == null || accountMC.isEmpty()) {
             return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
             }

             HashMap hm_Loan = (HashMap) accountMC.get(0);
             BeanUtils.copyProperties(response, accountMC.get(0));
             response.setAcctopendt(hm_Loan.get("active_date").toString());
             response.setCodacctcurr("VND");//
             response.setPrdcode(hm_Loan.get("card_type").toString());
             response.setPrdname(hm_Loan.get("card_desc").toString());
             response.setCodbranch(hm_Loan.get("brch").toString().substring(0, 3));
             response.setBlockonline(hm_Loan.get("block_online").toString());
             if (hm_Loan.get("cardaccoutno") != null) {
             response.setCardaccountno(hm_Loan.get("cardaccoutno").toString());
             }
             response.setCardno(hm_Loan.get("cardno").toString());
                
             //response.setBlockonline("O");
             if (response.getBlockonline().equals("0")){
             response.setBlockonline("O");
             }else{
             response.setBlockonline("L");
             }
             if (hm_Loan.get("default_casa") != null) {
             response.setDefaultcasa(hm_Loan.get("default_casa").toString());
             }
             response.setUnPostAmount("1234567");
             if (response.getTinhtrangthe().endsWith("The Dang Hoat Dong")) {
             response.setCardStatus("A");
             }else{
             response.setCardStatus("U");
             }                                              
             }
             */
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String GetAccountInfoNew_OLD(String xml) {
        try {
            AccountInfoRq req = (AccountInfoRq) Xml.unMarshaller(AccountInfoRq.class, xml);
            AccountInfoRp response = new AccountInfoRp();
            String accountno = req.getAccountNo();
            String AccountGroupCode = req.getAccountGroupCode();
            response.setAccountNo(accountno);
            if (AccountGroupCode.equals(ACCOUNT_CASA)) {

                List accountCasa = Helper.getDBI().getSttmCustAccountSyn(accountno);
                if (accountCasa == null || accountCasa.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }
                HashMap hm_acc = (HashMap) accountCasa.get(0);
                //BeanUtils.copyProperties(response, accountCasa.get(0));
                response.setAccountNo(hm_acc.get("accountno").toString());
                response.setCodacctcurr(hm_acc.get("accountccy").toString());
                response.setLimit_amount(hm_acc.get("limit_num").toString());
                response.setNumavailbal(hm_acc.get("numavailbal").toString());
                response.setNumbalance(hm_acc.get("acycurrbalance").toString());
                response.setPrdcode(hm_acc.get("accountcls").toString());
                response.setPrdname(hm_acc.get("accountclsname").toString());
                response.setCodbranch(hm_acc.get("branchcode").toString());

                response.setAcctopendt(changeFormatDate(response.getAcctopendt()));
                response.setFullName(hm_acc.get("acdesc").toString());
                response.setBranchname(hm_acc.get("branchname").toString());
            } else if (AccountGroupCode.equals(ACCOUNT_TD)) {
                List accountTd = Helper.getDBI().getTdAccountByAccountNo(accountno);
                if (accountTd == null || accountTd.isEmpty()) {
                    //check for TIET KIEM KKH
                    List accountCasa = Helper.getDBI().getCASAccountHasLimitMB(accountno);
                    if (accountCasa == null || accountCasa.isEmpty()) {
                        return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                    }
                    BeanUtils.copyProperties(response, accountCasa.get(0));
                    response.setAcctopendt(changeFormatDate(response.getAcctopendt()));
                    //return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                } else {
                    HashMap hm_Td = (HashMap) accountTd.get(0);
                    response.setPrdcode(hm_Td.get("accountclass").toString());
                    response.setPrdname(hm_Td.get("proddesc").toString());
                    response.setCodacctcurr(hm_Td.get("ccy").toString());
                    response.setNumbalance(hm_Td.get("depositamt").toString());
                    response.setCodbranch(hm_Td.get("codbranch").toString());
                    response.setNumavailbal(hm_Td.get("numavlbalance").toString());
                    response.setAcctopendt(hm_Td.get("depositdate").toString());
                    response.setMaturitydate(hm_Td.get("maturitydate").toString());
                    response.setRate(hm_Td.get("rate").toString());
                    response.setTerm(hm_Td.get("term").toString());
                    if (req.getLanguage() == null || req.getLanguage().isEmpty() || req.getLanguage().equals("VN")) {
                        response.setTerm(response.getTerm().concat(hm_Td.get("termtype").toString()));
                    } else {
                        String termtype = hm_Td.get("termtype").toString();
                        if (termtype.equals(" tháng")) {
                            response.setTerm(response.getTerm().concat(" months"));
                        } else if (termtype.equals(" ngày")) {
                            response.setTerm(response.getTerm().concat(" days"));
                        } else if (termtype.equals(" năm")) {
                            response.setTerm(response.getTerm().concat(" years"));
                        }
                    }
                    response.setAcctopendt(changeFormatDate(response.getAcctopendt()));
                    response.setMaturitydate(changeFormatDate(response.getMaturitydate()));
                    //response.setIsRedem("Y");
                    response.setIsRedem(hm_Td.get("isredem").toString());
                }
            } else if (AccountGroupCode.equals(ACCOUNT_LOAN)) {

                List accountLoan = Helper.getDBI().getLoanAccountByAccountNo(accountno);
                if (accountLoan == null || accountLoan.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }

                HashMap hm_Loan = (HashMap) accountLoan.get(0);
                BeanUtils.copyProperties(response, accountLoan.get(0));
                response.setCodacctcurr(hm_Loan.get("loaitien").toString());//accountLoan.getLoaitien());
                response.setAcctopendt(hm_Loan.get("ngaymotaikhoan").toString());
                response.setCodbranch(hm_Loan.get("machinhanh").toString());
                response.setPrdcode(hm_Loan.get("masanpham").toString());
                response.setPrdname(hm_Loan.get("tensanpham").toString());
                //change format date
                response.setAcctopendt(changeFormatDate(response.getAcctopendt()));
                response.setNgaydaohan(changeFormatDate(response.getNgaydaohan()));
                response.setNgayvay(changeFormatDate(response.getNgayvay()));

            } else if (AccountGroupCode.equals(ACCOUNT_MASTERCARD) || AccountGroupCode.equals(ACCOUNT_MASTERCARD_MC) || AccountGroupCode.equals(ACCOUNT_MASTERCARD_MD)) {
                List accountMC = Helper.getDBI().getMaterCardDetailByCardnoNew(accountno);
                if (accountMC == null || accountMC.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }

                HashMap hm_Loan = (HashMap) accountMC.get(0);
                BeanUtils.copyProperties(response, accountMC.get(0));
                response.setAcctopendt(hm_Loan.get("active_date").toString());
                response.setCodacctcurr("VND");//
                response.setPrdcode(hm_Loan.get("card_type").toString());
                response.setPrdname(hm_Loan.get("card_desc").toString());
                response.setCodbranch(hm_Loan.get("brch").toString().substring(0, 3));
                if (hm_Loan.get("cardaccoutno") != null) {
                    response.setCardaccountno(hm_Loan.get("cardaccoutno").toString());
                }
                response.setCardno(hm_Loan.get("cardno").toString());

                //kim add temp
                response.setBlockonline("O");
                if (hm_Loan.get("default_casa") != null) {
                    response.setDefaultcasa(hm_Loan.get("default_casa").toString());
                }

            }

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*Note used for before PCICSS
     * Get history transactions of account
     * CASA account: from date - to date
     * other accounts: 10 newest transations
     */
    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountStmt(String xml) {
        try {

            AccountStmtRq req = (AccountStmtRq) Xml.unMarshaller(AccountStmtRq.class, xml);
            AccountStmtRp response = new AccountStmtRp();
            String accountno = req.getAccountNo();
            String AccountGroupCode = req.getAccountGroupCode();

            if (AccountGroupCode.equals(ACCOUNT_CASA)) {
                //neu = ngay hien tai thi cong them 10
                if (req.getToDate() != null) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                    String currentdate = format.format(new Date());

                    if (currentdate.equals(req.getToDate())) {
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.DATE, 10);
                        req.setToDate(format.format(cal.getTime()));
                    }
                }

                List transList = Helper.getDBI().getTransationListByAccountNo(accountno, req.getFromDate(), req.getToDate(), req.getSrno());
                if (transList == null || transList.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_TRANSACTION));
                } else {
                    TxnDetail[] txnList = new TxnDetail[transList.size()];
                    for (int i = 0; i < transList.size(); i++) {
                        txnList[i] = new TxnDetail();
                        BeanUtils.copyProperties(txnList[i], transList.get(i));
                        txnList[i].setTxndate(txnList[i].getTxndate().replace("-", "").substring(0, 8));
                    }
                    response.setTxnList(txnList);
                }
            } else if (AccountGroupCode.equals(ACCOUNT_TD)) {

                List transList = Helper.getDBI().getTDTransationListByAccountNo(accountno);
                if (transList == null || transList.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_TRANSACTION));
                } else {
                    TxnDetail[] txnList = new TxnDetail[transList.size()];
                    for (int i = 0; i < transList.size(); i++) {
                        txnList[i] = new TxnDetail();
                        BeanUtils.copyProperties(txnList[i], transList.get(i));
                        txnList[i].setTxndate(txnList[i].getTxndate().replace("-", "").substring(0, 8));
                    }
                    response.setTxnList(txnList);
                }
            } else if (AccountGroupCode.equals(ACCOUNT_LOAN)) {
                List paidIntList = Helper.getDBI().getCLSCHEDULEINTPAID(accountno);
                if (paidIntList != null && !paidIntList.isEmpty()) {
                    LoanTxnDetail[] paidLoanTxnList = new LoanTxnDetail[paidIntList.size()];
                    for (int i = 0; i < paidIntList.size(); i++) {
                        paidLoanTxnList[i] = new LoanTxnDetail();
                        BeanUtils.copyProperties(paidLoanTxnList[i], paidIntList.get(i));
                    }
                    response.setListPaidInterestLoanTxn(paidLoanTxnList);
                }

                List unPaidIntList = Helper.getDBI().getCLSCHEDULEINTUNPAID(accountno);
                if (unPaidIntList != null && !unPaidIntList.isEmpty()) {
                    LoanTxnDetail[] paidLoanTxnList = new LoanTxnDetail[unPaidIntList.size()];
                    for (int i = 0; i < unPaidIntList.size(); i++) {
                        paidLoanTxnList[i] = new LoanTxnDetail();
                        BeanUtils.copyProperties(paidLoanTxnList[i], unPaidIntList.get(i));
                    }
                    response.setListUnPaidInterestLoanTxn(paidLoanTxnList);
                }

                List paidPricibalList = Helper.getDBI().getCLSCHEDULEPRIPAIDSCB(accountno);
                if (paidPricibalList != null && !paidPricibalList.isEmpty()) {
                    LoanTxnDetail[] paidLoanTxnList = new LoanTxnDetail[paidPricibalList.size()];
                    for (int i = 0; i < paidPricibalList.size(); i++) {
                        paidLoanTxnList[i] = new LoanTxnDetail();
                        BeanUtils.copyProperties(paidLoanTxnList[i], paidPricibalList.get(i));
                    }
                    response.setListPaidPrincipalLoanTxn(paidLoanTxnList);
                }

                List unPaidPricibalList = Helper.getDBI().getCLSCHEDULEPRIUNPAIDSCB(accountno);
                if (unPaidPricibalList != null && !unPaidPricibalList.isEmpty()) {
                    LoanTxnDetail[] paidLoanTxnList = new LoanTxnDetail[unPaidPricibalList.size()];
                    for (int i = 0; i < unPaidPricibalList.size(); i++) {
                        paidLoanTxnList[i] = new LoanTxnDetail();
                        BeanUtils.copyProperties(paidLoanTxnList[i], unPaidPricibalList.get(i));
                    }
                    response.setListUnPaidPrincipalLoanTxn(paidLoanTxnList);
                }

            } else if (AccountGroupCode.equals(ACCOUNT_MASTERCARD)) {

                List transList = Helper.getDBI().getTransactionMaterCardByCardno(accountno);
                if (transList == null || transList.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_TRANSACTION));
                } else {
                    TxnDetail[] txnList = new TxnDetail[transList.size()];
                    for (int i = 0; i < transList.size(); i++) {
                        txnList[i] = new TxnDetail();
                        BeanUtils.copyProperties(txnList[i], transList.get(i));
                        // txnList[i].setTxndate(txnList[i].getTxndate().substring(0, 10));
                    }
                    response.setTxnList(txnList);
                }

            }

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * CHUYEN KHOAN
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String FundTransferOLD(String xml) {
        try {
            String[] ibtresult = null;
            FundTransferRq req = (FundTransferRq) Xml.unMarshaller(FundTransferRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                String TxnType = req.getTxnType();
                response.setAccountNo(req.getFromAccount());
                response.setCifNo(req.getCifNo());
                response.setTxnType(TxnType);
                req.setUserId(useridMobile);
                String fccxref = null;
                Fcubs fc = new Fcubs();
                //chuyen khoan noi bo bang tai khoan
                if (TxnType.equals(TxnTypeEnum.INTERNAL_ACC_TRANSFER.getValue()) || TxnType.equals(TxnTypeEnum.OWN_ACC_TRANSFER.getValue())) {
                    fccxref = fc.transferFCUBSWithProductUser(producttransferMobileIn, req.getUserId(), req.getFromAccount(), req.getToAccount(), BigDecimal.valueOf(Double.valueOf(req.getAmount())), req.getRemark());
                    //chuyen khoan ngoai he thong bang tai khoan hoac CMND    
                } else if (TxnType.equals(TxnTypeEnum.DOMESTIC_ACC_TRANSFER.getValue()) || TxnType.equals(TxnTypeEnum.DOMESTIC_ID_TRANSFER.getValue())) {
                    fccxref = fc.transferFCUBSDomesticWithProductAndCharge(req);
                    //chuyen khoan smartlink
                } else if (TxnType.equals(TxnTypeEnum.SML_ACC_TRANSFER.getValue()) || TxnType.equals(TxnTypeEnum.SML_CARD_TRANSFER.getValue())) {
                    IBTController ibt = new IBTController();
                    String TypeToNumber = null;
                    if (TxnType.equals(TxnTypeEnum.SML_ACC_TRANSFER.getValue())) {
                        TypeToNumber = "ACCOUNT";
                    } else if (TxnType.equals(TxnTypeEnum.SML_CARD_TRANSFER.getValue())) {
                        TypeToNumber = "CARD";
                    }
                    String TermID = "22222222";
                    String CardAcceptor = "SML INTERNET BANKING      HCM        VNM";
                    String TypeFunction = "TRN";

                    //set null before check 
                    if (req.getRemark() == null || req.getRemark().isEmpty()) {
                        req.setRemark("");
                    }

                    ibtresult = ibt.transfeFromSCBForMobile(req.getFromAccount(), "ACCOUNT", "", req.getToAccount(), TypeToNumber,
                            req.getBankCode(), BigDecimal.valueOf(Double.valueOf(req.getAmount())), "VND", "MB", req.getRemark(),
                            TermID, CardAcceptor, TypeFunction, BigDecimal.valueOf(Double.valueOf(req.getTxnFee())), BigDecimal.valueOf(Double.valueOf(req.getTxnTax()))).split("#");
                    //response.setBeneName(result);

                    if (!ibtresult[0].equals("00")) {
                        return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.get(ibtresult[0])));
                    }
                    //Lay ket qua
                    fccxref = ibtresult[2];
                    //chuyen khoan noi bo bang CMND 
                } else if (TxnType.equals(TxnTypeEnum.INTERNAL_ID_TRANSFER.getValue())) {
                    fccxref = fc.transferFCUBSInternalID(req);

                }
                if (fccxref == null) {
                    if (TxnType.equals(TxnTypeEnum.SML_ACC_TRANSFER.getValue()) || TxnType.equals(TxnTypeEnum.SML_CARD_TRANSFER.getValue())) {
                        return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.get(ibtresult[0].equals("-1") ? "-11" : ibtresult[0])));
                    } else {
                        return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                    }
                } else {
                    if (TxnType.equals(TxnTypeEnum.OWN_ACC_TRANSFER.getValue())) {
                        response.setReloadAccList(RELOAD_ACC_LIST);
                    }
                    response.setTxnID(fccxref);
                    response.setTxnCommitTime(getTime());
                }
                return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * CHUYEN KHOAN
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String FundTransfer(String xml) {
        try {
            String[] ibtresult = null;
            FundTransferRq req = (FundTransferRq) Xml.unMarshaller(FundTransferRq.class, xml);

            req.setTxnFee("0");
            req.setTxnTax("0");

            TxnCommitRp response = new TxnCommitRp();
            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                String TxnType = req.getTxnType();
                response.setAccountNo(req.getFromAccount());
                response.setCifNo(req.getCifNo());
                response.setTxnType(TxnType);
                req.setUserId(useridMobile);

                if (!TxnType.equals(TxnTypeEnum.OWN_ACC_TRANSFER.getValue())) {
                    String branchCust = CommonUtils.getBranchAccount(req.getFromAccount());
                    String resultCheck = CommonUtils.checkOverKYC(req.getFromAccount(), branchCust, BigDecimal.valueOf(Double.valueOf(req.getAmount())));
                    if (!resultCheck.equals("00")) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.IBT_OVERKYC));
                    }
                }

                String fccxref = null;
                //Fcubs fc = new Fcubs();
                IIBPaymentService iibPaymentService = new IIBPaymentService();
                //chuyen khoan noi bo bang tai khoan
                if (TxnType.equals(TxnTypeEnum.INTERNAL_ACC_TRANSFER.getValue())) {
                    //fccxref = fc.transferFCUBSWithProductUser(producttransferMobileIn, req.getUserId(), req.getFromAccount(), req.getToAccount(), BigDecimal.valueOf(Double.valueOf(req.getAmount())), req.getRemark());
                    //fccxref = iibPaymentService.executePaymentTransactionInternalRestSimple(Configuration.getIIBContext(), producttransferMobileIn, req.getUserId(), req.getFromAccount(), req.getToAccount(), BigDecimal.valueOf(Double.valueOf(req.getAmount())), req.getRemark(), IIB_CHANNEL_MOBILE);
                    //ExecutePaymentTransactionInternal_out_Type executePaymentTransactionInternal_out_Type = iibPaymentService.executePaymentTransactionInternalRestSimple(Configuration.getIIBContext(), producttransferMobileIn, req.getUserId(), req.getFromAccount(), req.getToAccount(), BigDecimal.valueOf(Double.valueOf(req.getAmount())), req.getRemark(), IIB_CHANNEL_MOBILE);
                    List custacc = Helper.getDBI().GetBeneficaryName(req.getToAccount());
                    if (custacc == null || custacc.isEmpty()) {
                        return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                    } else {
                        HashMap hm_Casa = (HashMap) custacc.get(0);
                        String accountclass = hm_Casa.get("accountclass").toString();
                        String custno = hm_Casa.get("idcustomer").toString();
                        if (accountclass.equals(AccClassLuong)) {
                            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_PAYMENT_SALARY));
                        }
                        String checkCif = Helper.getDBI().checkCifNoCredit(custno);
                        if (checkCif.equals("1")) {

                            return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCOUNT_NOCREDIT));
                        }

                    }
                    ExecutePaymentTransactionInternal_out_Type executePaymentTransactionInternal_out_Type = iibPaymentService.executePaymentInterRestSimpleWithBrn(Configuration.getIIBContext(), producttransferMobileIn, req.getUserId(), req.getFromAccount(), req.getToAccount(), BigDecimal.valueOf(Double.valueOf(req.getAmount())), req.getRemark(), IIB_CHANNEL_MOBILE,
                            CommonUtils.getBranchAccount(req.getFromAccount()), CommonUtils.getBranchAccount(req.getToAccount()));
                    if (executePaymentTransactionInternal_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                        fccxref = executePaymentTransactionInternal_out_Type.getTransactionInfo().getCoreRefNum();
                    } else {
                        fccxref = null;
                    }
                } else if (TxnType.equals(TxnTypeEnum.OWN_ACC_TRANSFER.getValue())) {
                    List custacc = Helper.getDBI().GetBeneficaryName(req.getToAccount());
                    if (custacc == null || custacc.isEmpty()) {
                        return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                    } else {
                        HashMap hm_Casa = (HashMap) custacc.get(0);
                        String accountclass = hm_Casa.get("accountclass").toString();

                        if (accountclass.equals(AccClassLuong)) {
                            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_PAYMENT_SALARY));
                        }

                    }
                    ExecutePaymentTransactionInternal_out_Type executePaymentTransactionInternal_out_Type = iibPaymentService.executePaymentInterRestSimpleWithBrn(Configuration.getIIBContext(), producttransferMobilePrivate, req.getUserId(), req.getFromAccount(), req.getToAccount(), BigDecimal.valueOf(Double.valueOf(req.getAmount())), req.getRemark(), IIB_CHANNEL_MOBILE,
                            CommonUtils.getBranchAccount(req.getFromAccount()), CommonUtils.getBranchAccount(req.getToAccount()));
                    if (executePaymentTransactionInternal_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                        fccxref = executePaymentTransactionInternal_out_Type.getTransactionInfo().getCoreRefNum();
                    } else {
                        fccxref = null;
                    }
                } else if (TxnType.equals(TxnTypeEnum.DOMESTIC_ACC_TRANSFER.getValue()) || TxnType.equals(TxnTypeEnum.DOMESTIC_ID_TRANSFER.getValue())) {
                    //fccxref = fc.transferFCUBSDomesticWithProductAndCharge(req);                
                    IIBPaymentService iibPayService = new IIBPaymentService();
                    ExecutePaymentTransactionExternal_in_Type external_input = new ExecutePaymentTransactionExternal_in_Type();
                    //Transaction
                    TransactionInfoType transactionType = new TransactionInfoType();
                    transactionType.setClientCode(IIB_CHANNEL_MOBILE);
                    String xref = "MB" + String.valueOf(System.currentTimeMillis());

                    transactionType.setCRefNum(xref);

                    BranchInfoType branchinfo = new BranchInfoType();
                    branchinfo.setBranchCode(CommonUtils.getBranchAccount(req.getFromAccount()));
                    transactionType.setBranchInfo(branchinfo);
                    external_input.setTransactionInfo(transactionType);
                    //thong tin chuyen
                    FundTransferInfoType fundTransferInfo = new FundTransferInfoType();
                    fundTransferInfo.setFundTransferAmount(Double.valueOf(req.getAmount()));
                    /*
                    fundTransferInfo.setFundTransferFeeAmount(Integer.valueOf(req.getTxnFee()));
                    fundTransferInfo.setFundTransferFeeChgComp("PHI CHUYEN TIEN EBANKING");
                    fundTransferInfo.setFundTransferVATAmount(Integer.valueOf(req.getTxnTax()));
                    fundTransferInfo.setFundTransferVATChgComp("VAT CHUYEN TIEN EBANKING");
                    if (TxnType.equals(TxnTypeEnum.DOMESTIC_ACC_TRANSFER.getValue())){
                        if (req.getFinAmount() == null || req.getFinVatAmount() == null) {
                            req.setFinAmount("0");
                            req.setFinVatAmount("0");
                        }
                        fundTransferInfo.setFundTransferFINAmount(Integer.valueOf(req.getFinAmount()));
                        fundTransferInfo.setFundTransferFINChgComp("PHI TU VAN TAI CHINH EBANKING");
                        fundTransferInfo.setFundTransferFINVATAmount(Integer.valueOf(req.getFinVatAmount()));
                        fundTransferInfo.setFundTransferFINVATChgComp("VAT TU VAN TAI CHINH EBANKING");
                    }*/
                    fundTransferInfo.setFundTransferNarative(req.getRemark());
                    fundTransferInfo.setFundTransferProductCode(producttransferMobileOut);
                    external_input.setFundTransferInfo(fundTransferInfo);
                    //tk nguon
                    AccountInfoType sourceAccount = new AccountInfoType();
                    sourceAccount.setAccountOpenBrandCode(CommonUtils.getBranchAccount(req.getFromAccount()));
                    sourceAccount.setAccountNum(req.getFromAccount());
                    sourceAccount.setAccountCurrency("VND");
                    external_input.setSourceAccount(sourceAccount);
                    //tk dich     
                    AccountInfoType toAccount = new AccountInfoType();
                    toAccount.setAccountOpenBrandCode("000");
                    toAccount.setAccountNum(GLPaymentDomestic);
                    toAccount.setAccountCurrency("VND");
                    external_input.setDestinationAccount(toAccount);
                    //Thong tin giao dich chuyen
                    BenificialInfoType benificialInfo = new BenificialInfoType();
                    if (TxnType.equals(TxnTypeEnum.DOMESTIC_ACC_TRANSFER.getValue())) {
                        benificialInfo.setBenifitAccountNum(req.getToAccount());
                    }
                    benificialInfo.setBenifitCustomerName(req.getBeneName());
                    List bankCity = Helper.getDBI().getBankCity(req.getCityCode());
                    if (bankCity != null && !bankCity.isEmpty()) {
                        HashMap hm_city = (HashMap) bankCity.get(0);
                        benificialInfo.setBenefitCityCode(hm_city.get("nam_city_full").toString());
                    }
                    benificialInfo.setBenefitBranchName(req.getBranchName());
                    if (req.getBeneTel() != null) {
                        benificialInfo.setBenifitCustomerTEL(req.getBeneTel());
                    }
                    //thong tin ngan hang thu huong
                    List benebank = Helper.getDBI().getBeneBank(req.getBankCode());
                    if (benebank != null && !benebank.isEmpty()) {
                        HashMap hm_bene = (HashMap) benebank.get(0);
                        benificialInfo.setBenefitBankCode(req.getBankCode());
                        benificialInfo.setBenefitBankName(hm_bene.get("bankname").toString());
                    }
                    if (req.getIDOpenDate() != null && !req.getIDOpenDate().isEmpty()) {
                        try {
                            //change format date from yyyyMMdd to yyyy-MM-dd                
                            DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                            DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = formatter.parse(req.getIDOpenDate());
                            IDInfoType infoType = new IDInfoType();
                            if (TxnType.equals(TxnTypeEnum.DOMESTIC_ID_TRANSFER.getValue())) {
                                infoType.setIDNum(req.getToAccount());
                            }
                            infoType.setIDIssuedDate(formatter1.format(date));
                            if (req.getIDCityCode() != null && !req.getIDCityCode().isEmpty()) {
                                infoType.setIDIssuedLocation(req.getIDCityCode());
                            }
                            benificialInfo.setIDInfo(infoType);
                        } catch (Exception e) {
                            Helper.writeLogError(this.getClass(), e);
                            return null;
                        }
                    }
                    external_input.setBenificialInfo(benificialInfo);

                    SenderInfoType senderInfo = new SenderInfoType();
                    senderInfo.setSenderAccount(req.getFromAccount());
                    List customer = Helper.getDBI().getCustomerInfoFCC(req.getCifNo());
                    if (customer != null && !customer.isEmpty()) {
                        HashMap hm_cust = (HashMap) customer.get(0);
                        senderInfo.setSenderName(hm_cust.get("namfirst").toString());
                    }
                    senderInfo.setSenderCIFNum(req.getCifNo());
                    external_input.setSenderInfo(senderInfo);
                    //user lam        
                    CoreBankAccountType coreBankAccount = new CoreBankAccountType();
                    coreBankAccount.setMakerAccount(useridMobile);
                    coreBankAccount.setSourceHeader("FCAT");
                    external_input.setCoreBankAccount(coreBankAccount);

                    ExecutePaymentTransactionExternal_out_Type execute_external_out = iibPayService.executePaymentTransactionExternal(Configuration.getIIBContext(), external_input);

                    if (execute_external_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                        fccxref = execute_external_out.getTransactionInfo().getCoreRefNum();
                    } else {
                        return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                    }
                    //chuyen khoan smartlink
                } else if (TxnType.equals(TxnTypeEnum.SML_ACC_TRANSFER.getValue()) || TxnType.equals(TxnTypeEnum.SML_CARD_TRANSFER.getValue())) {
                    req.setFinAmount("0");
                    req.setFinVatAmount("0");
                    //set client code for MB
                    String clientCode = req.getClientCode();
                    if (clientCode != null && clientCode.equals("EBANK")) {
                        clientCode = "EBANK";
                    } else {
                        clientCode = "MB";
                        if (req.getChannel() != null && req.getChannel().equals("QR")) {
                            clientCode = "QR";
                        }
                    }
                    String TypeToNumber = null;
                    IBTController ibt = new IBTController();
                    if (TxnType.equals(TxnTypeEnum.SML_ACC_TRANSFER.getValue())) {
                        TypeToNumber = "ACCOUNT";
                    } else if (TxnType.equals(TxnTypeEnum.SML_CARD_TRANSFER.getValue())) {
                        TypeToNumber = "CARD";
                    }
                    String TermID = "22222222";
                    String CardAcceptor = "SML INTERNET BANKING      HCM        VNM";
                    String TypeFunction = "TRN";

                    //set null before check 
                    if (req.getRemark() == null || req.getRemark().isEmpty()) {
                        req.setRemark("");
                    }
                    //upgrade IIB
                    /*
                    ibtresult = ibt.transfeFromSCBForMobile(req.getFromAccount(), "ACCOUNT", "", req.getToAccount(), TypeToNumber,
                            req.getBankCode(), BigDecimal.valueOf(Double.valueOf(req.getAmount())), "VND", "MB", req.getRemark(),
                            TermID, CardAcceptor, TypeFunction, BigDecimal.valueOf(Double.valueOf(req.getTxnFee())), BigDecimal.valueOf(Double.valueOf(req.getTxnTax()))).split("#");
                     */
                    if (req.getProductCode() == null || req.getProductCode().length() == 0) {
                        req.setProductCode(Configuration.getProperty("fcubs.producttransfer.mobile.ibt"));
                    }
                    req.setRemark(req.getRemark().concat("#").concat(req.getTxnFee()).concat("#").concat(req.getTxnTax()).concat("#").concat(req.getFinAmount()).concat("#").concat(req.getFinVatAmount()));
                    ibtresult = ibt.transfeFromSCB(req.getFromAccount(), "ACCOUNT", "", req.getToAccount(), TypeToNumber,
                            req.getBankCode(), BigDecimal.valueOf(Double.valueOf(req.getAmount())), "VND", clientCode, req.getRemark(),
                            TermID, CardAcceptor, TypeFunction).split("#");
                    //upgrade IIB

                    if (!ibtresult[0].equals("00")) {
                        String statuscode = ibtresult[0];
                        return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.get(statuscode)));
                    }
                    //Lay ket qua
                    fccxref = ibtresult[2];
                    //chuyen khoan noi bo bang CMND 
                } else if (TxnType.equals(TxnTypeEnum.INTERNAL_ID_TRANSFER.getValue())) {
                    //fccxref = fc.transferFCUBSInternalID(req);
                    if (req.getIDCityCode() != null) {
                        Helper.writeLog(getClass(), Level.INFO, "ck trong he thong cmnd  req.getIDCityCode() IN: " + req.getIDCityCode());
                        req.setIDCityCode(VNCharacterUtils.removeAccent(req.getIDCityCode()));
                        Helper.writeLog(getClass(), Level.INFO, "ck trong he thong cmnd  req.getIDCityCode() OUT:" + req.getIDCityCode());
                    }
                    IIBPaymentService iibPayService = new IIBPaymentService();
                    ExecutePaymentTransactionExternal_in_Type external_input = new ExecutePaymentTransactionExternal_in_Type();
                    //Transaction
                    TransactionInfoType transactionType = new TransactionInfoType();
                    transactionType.setClientCode(IIB_CHANNEL_MOBILE);
                    String xref = "MB" + String.valueOf(System.currentTimeMillis());
                    transactionType.setCRefNum(xref);

                    BranchInfoType branchinfo = new BranchInfoType();
                    branchinfo.setBranchCode(CommonUtils.getBranchAccount(req.getFromAccount()));
                    transactionType.setBranchInfo(branchinfo);

                    external_input.setTransactionInfo(transactionType);

                    IIBCustomerService iibCustomerService = new IIBCustomerService();
                    RetrieveCustomerRefDataMgmt_out_Type retrieveCustomerRefDataMgmt = iibCustomerService.retrieveCustomerRefDataMgmtSimple(Configuration.getIIBContext(), req.getCifNo(), IIB_CHANNEL_MOBILE);
                    if (!retrieveCustomerRefDataMgmt.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                    }

                    //thong tin chuyen
                    FundTransferInfoType fundTransferInfo = new FundTransferInfoType();
                    fundTransferInfo.setFundTransferAmount(Double.valueOf(req.getAmount()));
                    fundTransferInfo.setFundTransferTransactionType("ID");
                    /* obdx NOT SET FEE
                    fundTransferInfo.setFundTransferFeeAmount(Integer.valueOf(req.getTxnFee()));
                    fundTransferInfo.setFundTransferFeeChgComp("THU PHI CHUYEN TIEN MOBI BANKING BANG CMND");
                    fundTransferInfo.setFundTransferVATAmount(Integer.valueOf(req.getTxnTax()));
                    fundTransferInfo.setFundTransferVATChgComp("VAT CHUYEN TIEN MOBI BANKING BANG CMND");
                     */
                    //fundTransferInfo.setFundTransferProductCode(producttransferMobileIDRT);
                    if (req.getProductCode() == null || req.getProductCode().length() == 0) {
                        req.setProductCode(producttransferMobileIDRT);
                    }

                    fundTransferInfo.setFundTransferProductCode(req.getProductCode());
                    //tk nguon
                    AccountInfoType sourceAccount = new AccountInfoType();
                    sourceAccount.setAccountOpenBrandCode(CommonUtils.getBranchAccount(req.getFromAccount()));
                    sourceAccount.setAccountNum(req.getFromAccount());
                    sourceAccount.setAccountCurrency("VND");
                    external_input.setSourceAccount(sourceAccount);
                    //tk dich     
                    AccountInfoType toAccount = new AccountInfoType();
                    toAccount.setAccountOpenBrandCode(req.getBranchCode());
                    toAccount.setAccountNum(GLPaymentInternalId);
                    toAccount.setAccountCurrency("VND");
                    external_input.setDestinationAccount(toAccount);
                    //Thong tin giao dich chuyen
                    BenificialInfoType benificialInfo = new BenificialInfoType();
                    //benificialInfo.setBenifitAccountNum(req.getToAccount());
                    benificialInfo.setBenifitCustomerName(req.getBeneName());
                    List bankCity = Helper.getDBI().getBankCity(req.getCityCode());
                    if (bankCity != null && !bankCity.isEmpty()) {
                        HashMap hm_city = (HashMap) bankCity.get(0);
                        benificialInfo.setBenefitCityCode(hm_city.get("nam_city_full").toString());
                    }
                    benificialInfo.setBenefitBranchName(req.getBranchName());

                    if (req.getIDOpenDate() != null && !req.getIDOpenDate().isEmpty()) {
                        try {
                            //change format date from yyyyMMdd to yyyy-MM-dd                
                            DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                            DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = formatter.parse(req.getIDOpenDate());
                            IDInfoType infoType = new IDInfoType();
                            infoType.setIDIssuedDate(formatter1.format(date));
                            infoType.setIDNum(req.getToAccount());

                            req.setIDOpenDate(infoType.getIDIssuedDate());
                            if (req.getIDCityCode() != null && !req.getIDCityCode().isEmpty()) {
                                infoType.setIDIssuedLocation(req.getIDCityCode());
                            }
                            benificialInfo.setIDInfo(infoType);
                        } catch (Exception e) {
                            Helper.writeLogError(this.getClass(), e);
                            return null;
                        }
                    }
                    external_input.setBenificialInfo(benificialInfo);

                    CustomerInfoType customerinfo = retrieveCustomerRefDataMgmt.getCustomerInfo();
                    String narrative = customerinfo.getFullname();

                    narrative = narrative.concat(" CT CHO ").concat(req.getBeneName())
                            .concat(", CMND: ").concat(req.getToAccount())
                            .concat(", NC: ").concat(req.getIDOpenDate());

                    if (req.getIDCityCode() != null && !req.getIDCityCode().isEmpty()) {
                        narrative = narrative.concat(", NC: ").concat(req.getIDCityCode()).concat(". ");
                    }

                    narrative = narrative.concat(req.getRemark());
                    if (narrative.length() > 225) {
                        narrative = narrative.substring(0, 225);
                    }

                    fundTransferInfo.setFundTransferNarative(narrative);
                    external_input.setFundTransferInfo(fundTransferInfo);

                    SenderInfoType senderInfo = new SenderInfoType();
                    senderInfo.setSenderAccount(req.getFromAccount());
                    /*
                    List customer = Helper.getDBI().getCustomerInfoFCC(req.getCifNo());
                    if (customer != null && !customer.isEmpty()) {
                        HashMap hm_cust = (HashMap) customer.get(0);
                        //senderInfo.setSenderName(hm_cust.get("namfirst").toString());
                    }*/
                    senderInfo.setSenderName(customerinfo.getFullname());
                    senderInfo.setSenderCIFNum(req.getCifNo());
                    external_input.setSenderInfo(senderInfo);
                    //user lam        
                    CoreBankAccountType coreBankAccount = new CoreBankAccountType();
                    coreBankAccount.setMakerAccount(useridMobile);
                    coreBankAccount.setSourceHeader("FCAT");
                    external_input.setCoreBankAccount(coreBankAccount);

                    ExecutePaymentTransactionExternal_out_Type execute_external_out = iibPayService.executePaymentTransactionExternal(Configuration.getIIBContext(), external_input);

                    if (execute_external_out == null) {
                        return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                    } else {
                        if (!execute_external_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                            Helper.writeLogError(this.getClass(), execute_external_out.getTransactionInfo().getTransactionReturn());
                            Helper.writeLogError(this.getClass(), execute_external_out.getTransactionInfo().getTransactionReturnMsg());
                            return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                        }
                        fccxref = execute_external_out.getTransactionInfo().getCoreRefNum();
                    }
                }

                if (fccxref == null) {
                    if (TxnType.equals(TxnTypeEnum.SML_ACC_TRANSFER.getValue()) || TxnType.equals(TxnTypeEnum.SML_CARD_TRANSFER.getValue())) {
                        return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.get(ibtresult[0].equals("-1") ? "-11" : ibtresult[0])));
                    } else {
                        return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                    }
                } else {
                    if (TxnType.equals(TxnTypeEnum.OWN_ACC_TRANSFER.getValue())) {
                        response.setReloadAccList(RELOAD_ACC_LIST);
                        response.setReloadCasaAccList(RELOAD_ACC_LIST);
                    }
                    response.setTxnID(fccxref);
                    response.setTxnCommitTime(getTime());
                }
                return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));
            }
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
    public String GetMobileFee(String xml) {
        try {

            GetMobileFeeRq req = (GetMobileFeeRq) Xml.unMarshaller(GetMobileFeeRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();

            response.setAccountNo(req.getFromAccount());

            //CHECK BALANCE BEFORE GET FEE
            /*
             List account = Helper.getDBI().checkAmountBeforePay(req.getFromAccount());
             if (account == null || account.isEmpty()) {
             return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
             }
             HashMap hm_acc = (HashMap) account.get(0);

             //HieuDT update for Txn type
             BigDecimal avl_bal = BigDecimal.ZERO;
             //BigDecimal avl_bal = BigDecimal.valueOf(Double.valueOf(hm_acc.get("avl_balance").toString()));
             if (req.getTxnType() != null && (req.getTxnType().equals("AZ") || req.getTxnType().equals("CCP"))) {
             avl_bal = BigDecimal.valueOf(Double.valueOf(hm_acc.get("avl_balance").toString()));
             } else {
             avl_bal = BigDecimal.valueOf(Double.valueOf(hm_acc.get("avl_balance").toString()) + Double.valueOf(hm_acc.get("limit_amount").toString()));
             }

             BigDecimal fee = BigDecimal.ZERO;
             BigDecimal tax = BigDecimal.ZERO;

             if (req.getTxnFee() != null && !req.getTxnFee().isEmpty()) {
             fee = BigDecimal.valueOf(Double.valueOf(req.getTxnFee()));
             }

             if (req.getTxnTax() != null && !req.getTxnTax().isEmpty()) {
             tax = BigDecimal.valueOf(Double.valueOf(req.getTxnTax()));
             }
             BigDecimal sumPay = fee.add(tax);

             if (avl_bal.compareTo(sumPay) == -1) {
             return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY));
             }
             * */
            //GET FEE
            Fcubs fc = new Fcubs();
            /*
             String fccxref = fc.transferFCUBSWithProductCharge(productMobileFee, getBranchFromAccount(req.getFromAccount()), req.getFromAccount(),
             getBranchFromAccount(req.getFromAccount()), GLPaymentFee, BigDecimal.ZERO,
             BigDecimal.valueOf(Double.valueOf(req.getTxnFee())), BigDecimal.valueOf(Double.valueOf(req.getTxnTax())),
             req.getRemark());
             */
            String fccxref = fc.transferFCUBSWithProductCharge(productMobileFee, getBranchFromAccount(req.getFromAccount()), req.getFromAccount(),
                    req.getBranchCode(), GLPaymentFee, BigDecimal.ZERO,
                    BigDecimal.valueOf(Double.valueOf(req.getTxnFee())), BigDecimal.valueOf(Double.valueOf(req.getTxnTax())),
                    req.getRemark());

            if (fccxref == null) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.CANNOT_TRANSFERFCUBS));
            } else {
                response.setTxnID(fccxref);
                response.setTxnCommitTime(getTime());
            }

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * 
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String CheckBalanceBeforeTran(String xml) {
        try {

            CheckBalanceBeforeTranRq req = (CheckBalanceBeforeTranRq) Xml.unMarshaller(CheckBalanceBeforeTranRq.class, xml);
            CheckBalanceBeforeTranRp response = new CheckBalanceBeforeTranRp();

            IIBCurrentAccountService iibAccService = new IIBCurrentAccountService();
            RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASA = iibAccService.retrieveCurrentAccountCASARestSimple(Configuration.getIIBContext(), req.getFromAccount(), IIB_CHANNEL_MOBILE);
            AccountInfoType accountdetailIIB;
            if (retrieveCurrentAccountCASA.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                accountdetailIIB = retrieveCurrentAccountCASA.getAccountInfo();
                //modify dong chu so huu                
                if (accountdetailIIB.getAccountCoownerName() != null && accountdetailIIB.getAccountCoownerName().length() > 0) {
                    //if (!checkJoinForTrans(req.getFromAccount())) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.ERROR_TRANS_JOINT));
                    //}
                }
            } else {
                IIBDepositAccountService iibAccServiceTD = new IIBDepositAccountService();
                RetrieveDepositAccountTD_out_Type retrieveDepositAccountTD_out_Type = iibAccServiceTD.retrieveDepositAccountTDSimple(Configuration.getIIBContext(), req.getFromAccount(), IIB_CHANNEL_MOBILE);
                if (!retrieveDepositAccountTD_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }
                accountdetailIIB = retrieveDepositAccountTD_out_Type.getAccountInfo();
            }

            //KIM CAP CHECK CAC GIAO DICH DC LAM VOI TK CHI LUONG
            /*
            if (accountdetailIIB.getAccountClassCode().equals("CAI012")
                    && (req.getTxnType().equals("INACC") || req.getTxnType().equals("AZ")
                    || req.getTxnType().equals("SMLACC") || req.getTxnType().equals("SMLCARD") || req.getTxnType().equals("OUTACC") 
                    || req.getTxnType().equals("EFT") || req.getTxnType().equals("INSBUY") || req.getTxnType().equals("ATM") 
                    || req.getTxnType().equals("DEBITCARD") || req.getTxnType().equals("OUTID")  || req.getTxnType().equals("INID"))) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_PAYMENT_SALARY));
            }
             *///kimncm chan tat ca giao dich tu tk luong
//            if (accountdetailIIB.getAccountClassCode().equals("CAI012") && !req.getTxnType().equals(TxnTypeEnum.OWN_ACC_TRANSFER.getValue())) {
//                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_PAYMENT_SALARY));
//            }
            ///lydty :Tài khoản  vốn chuyên dùng không được giao dịch online
            if (accountdetailIIB.getAccountClassCode().equals("CDI008")) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_PAYMENT_WITH_SPECIALACCCLASS));
            }
            //HieuDT update for Txn type
            BigDecimal avl_bal = BigDecimal.ZERO;
            if (req.getTxnType().equals("AZ") || req.getTxnType().equals("CCP")) {
                //avl_bal = BigDecimal.valueOf(Double.valueOf(hm_acc.get("avl_balance").toString()));
                avl_bal = accountdetailIIB.getAccountBalanceAvailable();
                avl_bal = avl_bal.subtract(new BigDecimal(accountdetailIIB.getAccountOverdraftLimit()));
                //avl_bal = accountdetailIIB.getAccountBalance();
            } else {
                //avl_bal = BigDecimal.valueOf(Double.valueOf(hm_acc.get("avl_balance").toString()) + Double.valueOf(hm_acc.get("limit_amount").toString()));
                avl_bal = accountdetailIIB.getAccountBalanceAvailable();
            }

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
            response.setAvlBalance(avl_bal.toString());
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

    /*
     * 
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String CheckBalanceBeforeTran_OLD(String xml) {
        try {

            CheckBalanceBeforeTranRq req = (CheckBalanceBeforeTranRq) Xml.unMarshaller(CheckBalanceBeforeTranRq.class, xml);
            CheckBalanceBeforeTranRp response = new CheckBalanceBeforeTranRp();

            IIBCurrentAccountService iibAccService = new IIBCurrentAccountService();
            RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASA = iibAccService.retrieveCurrentAccountCASARestSimple(Configuration.getIIBContext(), req.getFromAccount(), IIB_CHANNEL_MOBILE);
            if (!retrieveCurrentAccountCASA.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }
            AccountInfoType accountdetailIIB = retrieveCurrentAccountCASA.getAccountInfo();
            //KIM CAP CHECK CAC GIAO DICH DC LAM VOI TK CHI LUONG
            if (accountdetailIIB.getAccountClassCode().equals("CAI012")
                    && (req.getTxnType().equals("OUTACC") || req.getTxnType().equals("AZ")
                    || req.getTxnType().equals("OUTID") || req.getTxnType().equals("SMLACC") || req.getTxnType().equals("SMLCARD")
                    || req.getTxnType().equals("EFT") || req.getTxnType().equals("INSBUY") || req.getTxnType().equals("ATM"))) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_PAYMENT_SALARY));
            }

            //HieuDT update for Txn type
            BigDecimal avl_bal = BigDecimal.ZERO;
            if (req.getTxnType().equals("AZ")) //|| req.getTxnType().equals("CCP")) 
            {
                //avl_bal = BigDecimal.valueOf(Double.valueOf(hm_acc.get("avl_balance").toString()));
                avl_bal = accountdetailIIB.getAccountBalanceAvailable();
                avl_bal = avl_bal.subtract(new BigDecimal(accountdetailIIB.getAccountOverdraftLimit()));

            } else {
                //avl_bal = BigDecimal.valueOf(Double.valueOf(hm_acc.get("avl_balance").toString()) + Double.valueOf(hm_acc.get("limit_amount").toString()));
                avl_bal = accountdetailIIB.getAccountBalanceAvailable();
            }

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
            response.setAvlBalance(avl_bal.toString());
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

    /*
     * GET INFO TD BEFORE REDEMPTION
     */
    /**
     *
     * @param xml
     * @return
     */
    public String GetAzBeforeRedemption(String xml) {
        try {

            GetAzBeforeRedemptionRq req = (GetAzBeforeRedemptionRq) Xml.unMarshaller(GetAzBeforeRedemptionRq.class, xml);
            GetAzBeforeRedemptionRp response = new GetAzBeforeRedemptionRp();

            response.setAzAccount(req.getAzAccount());
            IIBDepositAccountService iibAccService = new IIBDepositAccountService();
            RetrieveDepositAccountTD_out_Type retrieveDepositAccountTD_out_Type = iibAccService.retrieveDepositAccountTDSimple(Configuration.getIIBContext(), req.getAzAccount(), IIB_CHANNEL_MOBILE);
            if (!retrieveDepositAccountTD_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            }
            AccountInfoType accountdetailIIB = retrieveDepositAccountTD_out_Type.getAccountInfo();
            response.setCodacctcurr(accountdetailIIB.getAccountCurrency());
            response.setNumavailbal(accountdetailIIB.getAccountBalanceAvailable().toPlainString());
            response.setNumbalance(accountdetailIIB.getAccountBalance().toPlainString());
            response.setPrdcode(accountdetailIIB.getAccountClassCode());
            response.setPrdname(accountdetailIIB.getAccountClassName());
            response.setCodbranch(accountdetailIIB.getAccountOpenBrandCode());
            response.setMinamount(accountdetailIIB.getAccountClassMinBalance());
            response.setRate(accountdetailIIB.getAccountInterestRate());
            response.setPayinterest("0");
            response.setTerm(accountdetailIIB.getAccountTerm());
            //response.setAcctopendt(changeFormatDate(accountdetailIIB.getAccountOpenDate()));
            response.setAcctopendt(changeFormatDate(accountdetailIIB.getAccountOfficialOpenDate()));
            response.setMaturitydate(changeFormatDate(accountdetailIIB.getAccountMaturityDate()));

            if (req.getRedemptionMode().equals("Y")) {
                if (Integer.parseInt(response.getAcctopendt()) >= 20190705 && Integer.parseInt(response.getAcctopendt()) <= 20191128) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOT_REDEMTION));
                }
            }
            response.setIsredem("N");
            if (arr_redemAccountclassList == null) {
                arr_redemAccountclassList = Arrays.asList(Helper.getDBI().getListAccountClassRedemEbank());
            }
            if (arr_redemAccountclassList.contains(accountdetailIIB.getAccountClassCode())) {
                response.setIsredem("Y");
            }
            IIBCurrentAccountService iibCasaService = new IIBCurrentAccountService();
            RetrieveCurrentAccountCASA_out_Type retrieveCurrentAccountCASA = iibCasaService.retrieveCurrentAccountCASARestSimple(Configuration.getIIBContext(), req.getNominateAcc(), IIB_CHANNEL_MOBILE);
            if (!retrieveCurrentAccountCASA.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }
            AccountInfoType detaiCasa = retrieveCurrentAccountCASA.getAccountInfo();
            if (detaiCasa.getAccountClassCode().endsWith("CAI012")) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_PAYMENT_SALARY));
            }

            if (detaiCasa.getAccountCoownerName() != null && detaiCasa.getAccountCoownerName().length() > 0) {
                //if (!checkJoinForTrans(req.getNominateAcc())) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.ERROR_TRANS_JOINT));
                //}
            }

            if (!detaiCasa.getAccountCurrency().equals("VND")) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.IBT_CARDACCNONOTVALID_08));
            }
            if (req.getRedemptionMode() == null || req.getRedemptionMode().isEmpty()) {
                req.setRedemptionMode("N");
            }
            if (req.getRedemptionMode().equals("Y")) {
                BigDecimal redemamount = BigDecimal.valueOf(Double.valueOf(req.getRedemptionAmount()));
                BigDecimal Numavailbal = BigDecimal.valueOf(Double.valueOf(response.getNumavailbal()));
                BigDecimal RemainAmt = Numavailbal.subtract(redemamount);
                if (Numavailbal.compareTo(redemamount) != 1) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.AMTREDEMP_GREATER_AZAVL));
                }
                if (RemainAmt.compareTo(BigDecimal.valueOf(Double.valueOf(response.getMinamount()))) == -1) {
                    //ky han ngay co so tien toi thieu 20tr
                    /*
                    if (arr_accountclassTenor == null && TDAccountClassTennor != null) {
                        arr_accountclassTenor = new ArrayList<String>(Arrays.asList(TDAccountClassTennor.split(",")));
                    }
                    if (arr_accountclassTenor.contains(accountdetailIIB.getAccountClassCode())) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.INVALID_MINAMOUNT_TENOR_AZ));
                    } else {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.INVALID_MINAMOUNT_AZ));
                    }
                     */
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.INVALID_MINAMOUNT_AZ));
                }
            }
            if (response.getIsredem().equals("Y")) {
                //alert EOD
                if (verifyTimeEOD()) {
                    ProcedureCallDto proResult = Helper.getDBI().checkTDIsEOD(req.getAzAccount());
                    if (proResult.getErrorStatus().equals("00")) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
                    } else if (proResult.getErrorStatus().equals("01")) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CHECKTD_EOD));
                    } else if (proResult.getErrorStatus().equals("02")) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                    } else {
                        Helper.writeLogError(this.getClass(), "checkTDIsEOD:".concat(proResult.getErrorStatus()));
                        return null;
                    }
                }
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
            } else {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_REDEMP_ACCOUNTTYPE));
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * THANH TOAN DU NO TIN DUNG
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String CreditCardPay(String xml) {
        try {
            CreditCardPayRq req = (CreditCardPayRq) Xml.unMarshaller(CreditCardPayRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            response.setAccountNo(req.getFromAccount());
            response.setCifNo(req.getCifNo());
            response.setTxnType(req.getTxnType());
            //check trong truong hop nhap OTP su dung Token
            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                String fccxref = null;
                Fcubs fc = new Fcubs();
                fccxref = fc.paymentMatercard(req);
                if (fccxref == null || fccxref.equalsIgnoreCase("TIMEOUT")) {
                    return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                } else {
                    response.setCifNo(req.getCifNo());
                    response.setTxnType(req.getTxnType());
                    response.setTxnID(fccxref);
                    response.setTxnCommitTime(getTime());
                }

                return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * LAY THONG TIN CHI TIET LOAI HINH CAN MO TIET KIEM
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String GetAccountTypeAzInfo(String xml) {
        try {
            GetAccountTypeAzInfoRq request = (GetAccountTypeAzInfoRq) Xml.unMarshaller(GetAccountTypeAzInfoRq.class, xml);
            GetAccountTypeAzInfoRp response = new GetAccountTypeAzInfoRp();

            String branchcode, amount, cif;
            //Helper.writeLog(MobileController.class, Level.ALL, "KIm -loadBodyEmail request.getAmount(): "+ request.getAmount());
            if (request.getAmount() == null || request.getAmount().isEmpty() || request.getAmount().equals("0")) {
                logger.warn("not load amount - request.getAmount = [" + request.getAmount() + "]");
                amount = "1499999990";
            } else {
                amount = request.getAmount();
            }
            if (request.getFromAccount() == null || request.getFromAccount().isEmpty()) {
                logger.warn("From account is invalid. request.getFromAccount = [" + request.getFromAccount() + "]");
                branchcode = "000";
                cif = "";
            } else {
                switch (request.getFromAccount().length()) {
                    case 16: // case so tai khoan dai thi lay 7 so tu ky tu thu 6 tro di
                        cif = request.getFromAccount().substring(5, 12);
                        break;
                    case 11: // case so tai khoan ngan thi lay 7 so dau tien
                        cif = request.getFromAccount().substring(0, 7);
                        break;
                    default: // account nay chua ho tro de cat
                        cif = request.getFromAccount();
                        break;
                }
                //hieunt16 fix loi lay cif
                cif = Helper.getDBI().getCifFromAccountNo(request.getFromAccount());
                // branchcode = request.getFromAccount().substring(0, 3);
                branchcode = CommonUtils.getBranchAccount(request.getFromAccount());
            }

            List accountTypeInfo = Helper.getDBI().getAccountClassInfo(branchcode, request.getAccountTypeCode().trim(), amount, request.getCcy(), cif);
//            List accountTypeInfo = Helper.getDBI().getAccountClassInfoNew(branchcode, request.getAccountTypeCode().trim(), amount, request.getCcy());

            if (accountTypeInfo == null || accountTypeInfo.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT_TYPE));
            } else {
                response.setAccounttypecode(request.getAccountTypeCode());
                response.setCcy(request.getCcy());
                BeanUtils.copyProperties(response, accountTypeInfo.get(0));
                response.setMatdate(changeFormatDate(response.getMatdate(), "dd/MM/yyyy", "yyyyMMdd"));
                response.setOpendate(changeFormatDate(response.getOpendate(), "dd/MM/yyyy", "yyyyMMdd"));

            }

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String OpenOnlineAz(String xml) {
        try {
            OpenAzAccountRq req = (OpenAzAccountRq) Xml.unMarshaller(OpenAzAccountRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            response.setCifNo(req.getCifNo());
            response.setTxnType(req.getTxnType());
            response.setAccountNo(req.getFromAccount());
            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {

                //kimncm
                //CHECK MIN BAL OF ACCOUNT CLASS              
                List prodaccountclass = Helper.getDBI().getProductAccountClass(req.getAccountTypeCode());
                if (prodaccountclass == null || prodaccountclass.isEmpty()) {
                    return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.NO_EXISTS_ACCOUNT_TYPE));
                } else {
                    HashMap hm_prod = (HashMap) prodaccountclass.get(0);
                    BigDecimal MIN_OPEN_TD = new BigDecimal(hm_prod.get("min_amount").toString());
                    if (BigDecimal.valueOf(Double.valueOf(req.getAmount())).compareTo(MIN_OPEN_TD) == -1) {
                        return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.NO_VALID_AMTOPENTD));
                    }
                }
                IIBDepositAccountService iibAccService = new IIBDepositAccountService();
                InitiateDepositAccountTD_in_type openTDInType = new InitiateDepositAccountTD_in_type();
                //Transaction
                TransactionInfoType transactionType = new TransactionInfoType();
                transactionType.setClientCode(IIB_CHANNEL_MOBILE);
                BranchInfoType branchinfo = new BranchInfoType();
                branchinfo.setBranchCode(CommonUtils.getBranchAccount(req.getFromAccount()));
                transactionType.setBranchInfo(branchinfo);

                String fccxref = "MB" + String.valueOf(System.currentTimeMillis());
                transactionType.setCRefNum(fccxref);

                openTDInType.setTransactionInfo(transactionType);
                //CIF thogn tin
                CIFDataType cifType = new CIFDataType();
                cifType.setCIFNum(req.getCifNo());
                openTDInType.setCIFInfo(cifType);
                //CustomerInfo thogn tin
                CustomerInfoType customerInfoType = new CustomerInfoType();
                customerInfoType.setFullname("Account opened from Mobile Banking");
                openTDInType.setCustomerInfo(customerInfoType);

                AccountInfoType accountInfoType = new AccountInfoType();
                accountInfoType.setAccountOpenBrandCode(CommonUtils.getBranchAccount(req.getFromAccount()));
                accountInfoType.setAccountClassCode(req.getAccountTypeCode());
                accountInfoType.setAccountCurrency("VND");
                DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(req.getMatDate());
                accountInfoType.setAccountMaturityDate(formatter1.format(date));
                // accountInfoType.setAccountOpeningAmount(Double.NEGATIVE_INFINITY);
                // accountInfoType.setAccountBalance(Double.POSITIVE_INFINITY);
                // accountInfoType.setAccountBalanceAvailable(Double.MIN_NORMAL);
                //accountInfoType.setAccountExchangeBalanceEQV(Double.NaN);

                //PayIn TD
                PayinType payinType = new PayinType();
                payinType.setSrcPayinType("S");
                payinType.setSrcAccountNum(req.getFromAccount());
                payinType.setSrcAccountBranch(CommonUtils.getBranchAccount(req.getFromAccount()));
                payinType.setAccountOpeningAmount(req.getAmount());
                payinType.setSrcPayinPercentage("100");
                accountInfoType.setPayin(payinType);
                //PayOut TD                
                if (req.getMaturityInstr().equals(MaturityInstr_AutoRoll_Both)) {
                    accountInfoType.setAccountAutoRollType("Y");
                    accountInfoType.setAccountRollType("I");

                } else if (req.getMaturityInstr().equals(MaturityInstr_AutoRoll_Principal)) {
                    accountInfoType.setAccountAutoRollType("Y");
                    accountInfoType.setAccountRollType("P");
                    accountInfoType.setBookAccBRN(CommonUtils.getBranchAccount(req.getNominateAcc()));
                    accountInfoType.setTdBookAccount(req.getNominateAcc());
                } else if (req.getMaturityInstr().equals(MaturityInstr_Close)) {
                    accountInfoType.setAccountAutoRollType("N");
                    PayoutType payoutType = new PayoutType();

                    payoutType.setPayoutType("S");
                    payoutType.setPayoutPercentage("100");
                    payoutType.setPayoutAccount(req.getNominateAcc());
                    payoutType.setPayoutAccountBRN(CommonUtils.getBranchAccount(req.getNominateAcc()));
                    accountInfoType.setPayout(payoutType);

                }//hien tai ko tai ky voi so tien khac
                else if (req.getMaturityInstr().equals(MaturityInstr_AutoRoll_Special)) {

                }
                openTDInType.setAccountInfo(accountInfoType);

                //user lam        
                CoreBankAccountType coreBankAccount = new CoreBankAccountType();
                coreBankAccount.setMakerAccount(useridMobile);
                coreBankAccount.setSourceHeader("FCAT");

                openTDInType.setCoreBankAccount(coreBankAccount);

                InitiateDepositAccountTD_out_type initTD_out = iibAccService.initiateDepositAccountTD(Configuration.getIIBContext(), openTDInType);

                if (!initTD_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    Helper.writeLogError(this.getClass(), initTD_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), initTD_out.getTransactionInfo().getTransactionReturnMsg());
                    Helper.writeLogError(this.getClass(), initTD_out.getTransactionInfo().getTransactionErrorCode());
                    Helper.writeLogError(this.getClass(), initTD_out.getTransactionInfo().getTransactionErrorMsg());
                    return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.CANNOT_OPENTD_FCUBS));
                }
                response.setReloadAccList(RELOAD_ACC_LIST);
                response.setTxnID(fccxref);
                response.setTxnCommitTime(getTime());
                return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * MO SO TIET KIEM ONLINE
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String OpenOnlineAzOLD(String xml) {
        try {
            OpenAzAccountRq req = (OpenAzAccountRq) Xml.unMarshaller(OpenAzAccountRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            response.setCifNo(req.getCifNo());
            response.setTxnType(req.getTxnType());
            response.setAccountNo(req.getFromAccount());
            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                //CHECK MIN BAL OF ACCOUNT CLASS              
                List prodaccountclass = Helper.getDBI().getProductAccountClass(req.getAccountTypeCode());
                if (prodaccountclass == null || prodaccountclass.isEmpty()) {
                    return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.NO_EXISTS_ACCOUNT_TYPE));
                } else {
                    HashMap hm_prod = (HashMap) prodaccountclass.get(0);
                    BigDecimal MIN_OPEN_TD = new BigDecimal(hm_prod.get("min_amount").toString());
                    if (BigDecimal.valueOf(Double.valueOf(req.getAmount())).compareTo(MIN_OPEN_TD) == -1) {
                        return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.NO_VALID_AMTOPENTD));
                    }
                }

                String fccxref = null;
                Fcubs fc = new Fcubs();

                //change format date from yyyyMMdd to yyyy-MM-dd                
                DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(req.getMatDate());
                req.setMatDate(formatter1.format(date));

                fccxref = fc.OpenTD(req);

                if (fccxref == null) {
                    return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.CANNOT_OPENTD_FCUBS));
                } else {
                    response.setReloadAccList(RELOAD_ACC_LIST);
                    response.setTxnID(fccxref);
                    response.setTxnCommitTime(getTime());
                    //remove cap so su thuong

                    MobileControlExt ext = new MobileControlExt();
                    GrantLuckNumberRq reqLucky = new GrantLuckNumberRq();
                    reqLucky.setSrcChannel("MB");
                    reqLucky.setCifNo(req.getCifNo());
                    reqLucky.setAccountNo(fccxref);
                    ext.FuncGrantLuckNumber(reqLucky);

                }

                return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));
            }
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
    public String CloseOnlineAz(String xml) {
        try {
            CloseAzAccountRq req = (CloseAzAccountRq) Xml.unMarshaller(CloseAzAccountRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            response.setAccountNo(req.getNominateAcc());
            response.setCifNo(req.getCifNo());
            response.setTxnType(req.getTxnType());
            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                IIBDepositAccountService iibAccService = new IIBDepositAccountService();
                RetrieveDepositAccountTD_out_Type retrieveDepositAccountTD_out_Type = iibAccService.retrieveDepositAccountTDSimple(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);
                if (!retrieveDepositAccountTD_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                }
                if (req.getRedemptionMode() == null || req.getRedemptionMode().isEmpty()) {
                    req.setRedemptionMode("N");
                } else if (req.getRedemptionMode().equals("Y")) {
                    req.setRedemptionMode("M");
                }

                AccountInfoType accountdetailIIB = retrieveDepositAccountTD_out_Type.getAccountInfo();
                TerminateDepositAccount_in_Type terminateTD = new TerminateDepositAccount_in_Type();
                //Transaction
                TransactionInfoType transactionType = new TransactionInfoType();
                transactionType.setClientCode(IIB_CHANNEL_MOBILE);
                BranchInfoType branchinfo = new BranchInfoType();
                branchinfo.setBranchCode(accountdetailIIB.getAccountOpenBrandCode());
                transactionType.setBranchInfo(branchinfo);

                String fccxref = "MB" + String.valueOf(System.currentTimeMillis());
                transactionType.setCRefNum(fccxref);

                terminateTD.setTransactionInfo(transactionType);

                AccountInfoType accountInfoType = new AccountInfoType();
                accountInfoType.setAccountNum(accountdetailIIB.getAccountNum());
                accountInfoType.setAccountOpenBrandCode(accountdetailIIB.getAccountOpenBrandCode());
                accountInfoType.setTerminationType(req.getRedemptionMode());

                if (req.getRedemptionMode().equals("M")) {
                    RedemptionType redemType = new RedemptionType();
                    redemType.setRedemptionAmount(req.getRedemptionAmount());
                    accountInfoType.setRedemption(redemType);
                }

                PayoutType payoutType = new PayoutType();
                payoutType.setPayoutAccount(req.getNominateAcc());
                payoutType.setPayoutAccountBRN(CommonUtils.getBranchAccount(req.getNominateAcc()));

                accountInfoType.setPayout(payoutType);
                terminateTD.setAccountInfo(accountInfoType);

                //user lam        
                CoreBankAccountType coreBankAccount = new CoreBankAccountType();
                coreBankAccount.setMakerAccount(useridMobile);
                coreBankAccount.setSourceHeader("FCAT");

                terminateTD.setCoreBankAccount(coreBankAccount);

                TerminateDepositAccount_out_Type terminateTD_out = iibAccService.terminateDepositAccount(Configuration.getIIBContext(), terminateTD);

                if (!terminateTD_out.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                    Helper.writeLogError(this.getClass(), terminateTD_out.getTransactionInfo().getTransactionReturn());
                    Helper.writeLogError(this.getClass(), terminateTD_out.getTransactionInfo().getTransactionReturnMsg());
                    Helper.writeLogError(this.getClass(), terminateTD_out.getTransactionInfo().getTransactionErrorCode());
                    Helper.writeLogError(this.getClass(), terminateTD_out.getTransactionInfo().getTransactionErrorMsg());
                    return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.CANNOT_CLOSETD_FCUBS));
                }
                response.setReloadAccList(RELOAD_ACC_LIST);
                response.setTxnID(fccxref);
                response.setTxnCommitTime(getTime());

                if (req.getRedemptionMode().equals("M")) {
                    response.setAzbalance(String.valueOf(terminateTD_out.getSourceAccount().getAccountBalance().longValue()));
                    response.setAvailablebalance(String.valueOf(terminateTD_out.getDestinationAccount().getAccountBalanceAvailable().longValue()));
                    response.setNumbalance(String.valueOf(terminateTD_out.getDestinationAccount().getAccountBalance().longValue()));
                } else {
                    response.setAvailablebalance(String.valueOf(terminateTD_out.getAccountInfo().getAccountBalanceAvailable().longValue()));
                    response.setNumbalance(String.valueOf(terminateTD_out.getAccountInfo().getAccountBalance().longValue()));
                }

                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
            }
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
    public String CloseOnlineAzOLD(String xml) {
        try {
            CloseAzAccountRq req = (CloseAzAccountRq) Xml.unMarshaller(CloseAzAccountRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            response.setAccountNo(req.getNominateAcc());
            response.setCifNo(req.getCifNo());
            response.setTxnType(req.getTxnType());
            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {

                String fccxref = null;
                Fcubs fc = new Fcubs();
                if (req.getRedemptionMode() == null || req.getRedemptionMode().isEmpty()) {
                    req.setRedemptionMode("N");
                }
                /*
                IIBDepositAccountService iibAccService = new IIBDepositAccountService();
                RetrieveDepositAccountTD_out_Type retrieveDepositAccountTD_out_Type = iibAccService.retrieveDepositAccountTDSimple(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);                
                AccountInfoType accountdetailIIB = retrieveDepositAccountTD_out_Type.getAccountInfo();
                req.setBranchCode(accountdetailIIB.getAccountOpenBrandCode());
                 */
                req.setBranchCode(CommonUtils.getBranchAccount(req.getAccountNo()));
                fccxref = fc.CloseTD(req);
                if (fccxref == null) {
                    return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.CANNOT_CLOSETD_FCUBS));
                } else {
                    response.setReloadAccList(RELOAD_ACC_LIST);
                    response.setTxnID(fccxref);
                    response.setTxnCommitTime(getTime());
                    if (req.getRedemptionMode().equals("Y")) {
                        /*
                        List custacc = Helper.getDBI().getSttmCustAccountSyn(req.getAccountNo());
                        if (custacc == null || custacc.isEmpty()) {
                            return null;
                        } else {
                            HashMap hm_Casa = (HashMap) custacc.get(0);
                            //response.setAzbalance(hm_Casa.get("acy_curr_balance").toString());
                            response.setAzbalance(hm_Casa.get("acycurrbalance").toString());
                                                       
                        }
                         */
                        //getBalance again if redemption part
                        /*
                        retrieveDepositAccountTD_out_Type = iibAccService.retrieveDepositAccountTDSimple(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);
                        if (retrieveDepositAccountTD_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                            accountdetailIIB = retrieveDepositAccountTD_out_Type.getAccountInfo();                            
                            response.setAzbalance(accountdetailIIB.getAccountBalance().toPlainString());
                        }*/
                    }
                }
                return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));
            }
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
    public String GetBeneficaryName(String xml) {
        try {
            GetBeneficaryNameRq req = (GetBeneficaryNameRq) Xml.unMarshaller(GetBeneficaryNameRq.class, xml);
            GetBeneficaryNameRp response = new GetBeneficaryNameRp();
            String TxnType = req.getTxnType();
            response.setToAccount(req.getToAccount());
            response.setTxnType(TxnType);

            if (TxnType.equals(TxnTypeEnum.INTERNAL_ACC_TRANSFER.getValue()) || TxnType.equals(TxnTypeEnum.OWN_ACC_TRANSFER.getValue())) {

                List custacc = Helper.getDBI().GetBeneficaryName(req.getToAccount());
                if (custacc == null || custacc.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                } else {
                    HashMap hm_Casa = (HashMap) custacc.get(0);
                    String getType = hm_Casa.get("accounttype").toString();
                    String custno = hm_Casa.get("idcustomer").toString();
                    //nvloi bo sung chan ck voi tk NB, TR, GH 09112022
                    String accountClass = hm_Casa.get("accountclass").toString();
                    if (accountClass != null && accountClass.length() >= 2) {
                        String accountTemp = accountClass.substring(0, 2);
                        if ("NB".equals(accountTemp) || "TR".equals(accountTemp) || "GH".equals(accountTemp)) {
                            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_PAYMENT_SALARY));
                        }
                    }

                    if (getType.equalsIgnoreCase("Y")) {
                        String branchcode = hm_Casa.get("branchcode").toString();
                        String custno_hm = hm_Casa.get("idcustomer").toString();
                        if (!custno_hm.equals(req.getCifNo())) {
                            return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                        }
                        ProcedureCallDto proCall = Helper.getDBI().validateTopupTKTichLuy(req.getToAccount(), branchcode, req.getAmount());
                        if (!proCall.isSucess()) {
                            Helper.writeLogError(MobileController.class, "***-GetBeneficaryName - validateTopupTKTichLuy" + proCall.getErrorStatus() + " - " + proCall.getErrorMessage());
                            String ERR = proCall.getErrorMessage();
                            if (ERR.equals("ST-TOP-015")) {
                                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_TOPUPTD_15));
                            } else if (ERR.equals("ST-TOP-017")) {
                                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_TOPUPTD_17));
                            } else {
                                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.IBT_CARDACCDOUBT_11));
                            }

                        }
                    } else {
                        if (hm_Casa.get("accountclass").toString().equals(AccClassLuong)) {
                            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_PAYMENT_SALARY));
                        }
                    }
                    if (TxnType.equals(TxnTypeEnum.INTERNAL_ACC_TRANSFER.getValue())) {
                        String checkCif = Helper.getDBI().checkCifNoCredit(custno);
                        if (checkCif.equals("1")) {
                            return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCOUNT_NOCREDIT));
                        }
                    }
                    response.setBeneName(hm_Casa.get("acctdesc").toString());
                }
            } else if (TxnType.equals(TxnTypeEnum.SML_ACC_TRANSFER.getValue()) || TxnType.equals(TxnTypeEnum.SML_CARD_TRANSFER.getValue())) {
                IBTController ibt = new IBTController();
                String TypeToNumber = null;
                if (TxnType.equals(TxnTypeEnum.SML_ACC_TRANSFER.getValue())) {
                    TypeToNumber = "ACCOUNT";
                } else if (TxnType.equals(TxnTypeEnum.SML_CARD_TRANSFER.getValue())) {
                    TypeToNumber = "CARD";
                }
                String TermID = "22222222";
                String CardAcceptor = "SML INTERNET BANKING      HCM        VNM";
                String TypeFunction = "QUE";
                //set null before check 
                if (req.getRemark() == null || req.getRemark().isEmpty()) {
                    req.setRemark("");
                }

                if (req.getAmount() == null || req.getAmount().isEmpty()) {
                    req.setAmount("0");
                }
                //upgrade IBT 
                /*
                 String[] result = ibt.transfeFromSCBForMobile(req.getFromAccount(), "ACCOUNT", req.getFromAccount(), req.getToAccount(), TypeToNumber,
                        req.getBankCode(), BigDecimal.valueOf(Double.valueOf(req.getAmount())), "VND", "MB", req.getRemark(),
                        TermID, CardAcceptor, TypeFunction,BigDecimal.ZERO,BigDecimal.ZERO).split("#");
                 */
                req.setRemark(req.getRemark().concat("#").concat("0").concat("#").concat("0"));
                String clientCode = req.getClientCode();
                if (clientCode != null && clientCode.equals("EBANK")) {
                    clientCode = "EBANK";
                } else {
                    clientCode = "MB";
                    if (req.getChannel() != null && req.getChannel().equals("QR")) {
                        clientCode = "QR";
                    }
                }
                String[] result = ibt.transfeFromSCB(req.getFromAccount(), "ACCOUNT", req.getFromAccount(), req.getToAccount(), TypeToNumber,
                        req.getBankCode(), BigDecimal.valueOf(Double.valueOf(req.getAmount())), "VND", clientCode, req.getRemark(),
                        TermID, CardAcceptor, TypeFunction).split("#");

                //upgrade IBT 
                if (result[0].equals("00")) {
                    if (result.length == 1) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.IBT_CARDACCERROR_07));
                    }
                    response.setBeneName(result[1]);
                } else {
                    if (result[0].equals("08")) {
                        result[0] = "108";
                    }
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(result[0])));
                }
            }

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));

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
    public String GetCreditCardInfo(String xml) {
        try {
            GetCreditCardInfoRq req = (GetCreditCardInfoRq) Xml.unMarshaller(GetCreditCardInfoRq.class, xml);
            GetCreditCardInfoRp response = new GetCreditCardInfoRp();

            response.setCardno(req.getCardNo());
            // NOT SUPPORT OLD PAYMENT
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOTSUPPORT_OLD_PAYMENTCREDIT));
            /*
            List accountMC = Helper.getDBI().getMaterCardDetailByCardno(req.getCardNo());
            if (accountMC == null || accountMC.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }

            BeanUtils.copyProperties(response, accountMC.get(0));
           
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
             */
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
    public String SendSMSToMB(String xml) {
        try {
            SendSMSToMBRq req = (SendSMSToMBRq) Xml.unMarshaller(SendSMSToMBRq.class, xml);
            SendSMSToMBRp response = new SendSMSToMBRp();

            response.setCifNo(req.getCifNo());
            ControllerImpl ci = new ControllerImpl("");
            int result = ci.sendsms(req.getMobileNo(), req.getSMSContent());

            if (result != 0) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_SEND_SMS));
            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));

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
    public String GetBillService(String xml) {
        try {

            GetBillServiceRq req = (GetBillServiceRq) Xml.unMarshaller(GetBillServiceRq.class, xml);
            GetBillServiceRp response = new GetBillServiceRp();

            String languages = "VN";
            if (req.getLanguage() != null && req.getLanguage().equals("EN")) {
                languages = "EN";
            }
            response.setLanguage(languages);
            /*
            if (hm_service.isEmpty()) {
                init();
            }*/
 /*
            List billServiceList = null;
            if (req.getHasauto() != null && req.getHasauto().equals("Y")) {
                billServiceList = Helper.getDBI().getAllListServiceTypeOfAutoPay();
            } else {
                billServiceList = Helper.getDBI().getAllListServiceTypeNotTopup();
            }

            if (billServiceList == null || billServiceList.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.SERVICECODE_NOTEXSIST));
            } else {
                BillServiceDetail[] serviceList;//= new BillServiceDetail[billServiceList.size() +1];
                if (req.getHasauto() != null && req.getHasauto().equals("Y")) {
                    serviceList = new BillServiceDetail[billServiceList.size()];
                } else {
                    serviceList = new BillServiceDetail[billServiceList.size() + 1];
                }
                if (languages.equals("VN")) {
                    for (int i = 0; i < billServiceList.size(); i++) {
                        serviceList[i] = new BillServiceDetail();
                        BeanUtils.copyProperties(serviceList[i], billServiceList.get(i));
                    }
                    //add manulife
                    if (req.getHasauto() != null && !req.getHasauto().equals("Y")) {
                        BillServiceDetail mnlifeService = new BillServiceDetail();
                        mnlifeService.setName("Thanh toán phí bảo hiểm Manulife");
                        mnlifeService.setIdservicetype("MNL");
                        serviceList[billServiceList.size()] = mnlifeService;
                    }

                } else if (req.getLanguage().equals("EN")) {
                    for (int i = 0; i < billServiceList.size(); i++) {
                        HashMap hm_Provider = (HashMap) billServiceList.get(i);
                        serviceList[i] = new BillServiceDetail();
                        serviceList[i].setIdservicetype(hm_Provider.get("idservicetype").toString());
                        serviceList[i].setName(hm_Provider.get("name_eng").toString());
                    }
                    if (req.getHasauto() != null && !req.getHasauto().equals("Y")) {
                        BillServiceDetail mnlifeService = new BillServiceDetail();
                        mnlifeService.setName("Manulife Vietnam");
                        mnlifeService.setIdservicetype("MNL");
                        serviceList[billServiceList.size()] = mnlifeService;
                    }
                }
                response.setListBillService(serviceList);
             */
            if (req.getHasauto() == null || req.getHasauto().isEmpty()) {
                req.setHasauto("N");
            }
            //(BillProviderDetail[]) providerList.toArray(new BillProviderDetail[providerList.size()])
            String keyService = languages.concat("_").concat(req.getHasauto());
            ArrayList<BillServiceDetail> arrresult = hm_service.get(keyService);
            response.setListBillService((BillServiceDetail[]) arrresult.toArray(new BillServiceDetail[arrresult.size()]));

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String GetBillProvider(String xml) {
        try {
            GetBillProviderRq req = (GetBillProviderRq) Xml.unMarshaller(GetBillProviderRq.class, xml);
            GetBillProviderRp response = new GetBillProviderRp();
            //List billProviderList = Helper.getDBI().getProviderByIdServiceType(req.getIdservicetype());

            response.setIdservicetype(req.getIdservicetype());
            response.setLanguage(req.getLanguage());
            //get lay hoa don cho giao dich nao?
            String hasauto = "N";
            String language = "VN";

            if (req.getHasauto() != null && req.getHasauto().equals("Y")) {
                hasauto = "Y";
            }
            if (req.getLanguage() != null && req.getLanguage().equals("EN")) {
                language = "EN";
            }
            /*
            if (billProviderList == null || billProviderList.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.PROVIDERCODE_NOTEXSIST));
            } else {
                ArrayList<BillProviderDetail> providerList = new ArrayList<BillProviderDetail>();
                for (int i = 0; i < billProviderList.size(); i++) {
                    HashMap hm_Provider = (HashMap) billProviderList.get(i);
                    if (hasauto.equals("N")) {
                        providerList.add(copyDBToProviderDetail(billProviderList.get(i), language));

                    } else {
                        if (hm_Provider.get("autopay").toString().equals("Y")) {
                            providerList.add(copyDBToProviderDetail(billProviderList.get(i), language));
                        }
                    }
                }
                response.setListBillProvider((BillProviderDetail[]) providerList.toArray(new BillProviderDetail[providerList.size()]));                
            }*/
 /*
             if (hm_serviveprovider.isEmpty()) {
                   init();
            } */
            String keyService = req.getIdservicetype().concat("_").concat(language).concat("_").concat(hasauto);
            ArrayList<BillProviderDetail> arrresult = hm_serviveprovider.get(keyService);
            response.setListBillProvider((BillProviderDetail[]) arrresult.toArray(new BillProviderDetail[arrresult.size()]));
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String GetBillInfo(String xml) {
        try {
            GetBillInfoRq req = (GetBillInfoRq) Xml.unMarshaller(GetBillInfoRq.class, xml);
            GetBillInfoRp response = new GetBillInfoRp();
            BillPaymentRq billRequest = new BillPaymentRq();
            //bo sung tra cuu hoa don vnpt media
            String partnerId = ControllerUtil.getIdPartner(req.getIdservicetype(), req.getIdprovider());
            if ("VNPT_MEDIA".equals(partnerId)) {
                if (VNPTConstant.VNPT_SERVICE_HOADON.equals(req.getIdservicetype())) {
                    if (req.getAddInfo() != null && VNPTConstant.VNPT_SERVICE_TYPE_TRUOCCUOC.equals(req.getAddInfo())) {
                        req.setIdservicetype("HD_TC");
                    } else if (req.getAddInfo() != null && VNPTConstant.VNPT_SERVICE_TYPE_NOCUOC.equals(req.getAddInfo())) {
                        req.setIdservicetype("HD_NC");
                    } else if (req.getAddInfo() != null && VNPTConstant.VNPT_SERVICE_TYPE_TRUOCCUOC_CT.equals(req.getAddInfo())) {
                        req.setIdservicetype("HD_TC_CT");
                    }

                    BillingVNPTMediaController billingVNPT = new BillingVNPTMediaController();
                    // Call qua VNPT
                    String rs = billingVNPT.getBillingVNPTMedia(req, response, null);
                    return rs;
                }

                /*
                if ("NUOC".equals(req.getIdservicetype())) {
                    BillingVNPTController billingVNPT = new BillingVNPTController();
                    // Call qua VNPT
                    String rs = billingVNPT.getBillingWaterVNPT(req, response);
                    return rs;
                } else if ("HOCPHI".equals(req.getIdservicetype())) {
                    BillingVNPTController billingVNPT = new BillingVNPTController();
                    String rs = billingVNPT.getBillingHocPhiVNPT(req);
                    return rs;
                }
                 */
            }

            if (req.getIdprovider() != null && req.getIdprovider().equals(partnerId)) {
                if ("NUOC".equals(req.getIdservicetype()) && "DAWACO".equals(req.getIdprovider())) {
                    BillingDawacoController billingDawaco = new BillingDawacoController();
                    String rs = billingDawaco.getBillInfoDawaco(req, billRequest);
                    return rs;
                }
            }

            BeanUtils.copyProperties(billRequest, req);
            if (billRequest.getCustomerCode() != null) {
                billRequest.setCustomerCode(billRequest.getCustomerCode().trim());
            }

            callRequestPayment(billRequest, "QUERY");
            //Helper.writeLogError(MobileController.class,String.format("[ADD LOG KIMNCM - VE MAY BAY 7 -%1$s]", ToStringBuilder.reflectionToString(billRequest)));
            if (!billRequest.getResult().equals("0")) {
                insPblLog(billRequest, "Truy vấn Bill không thành công. - Lỗi giao dịch: " + Message.getMessagePaymentResult(PaymentResultEnum.get(billRequest.getResult())));
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.get(billRequest.getResult())));
            } else {
                Response paymentRes = billRequest.getRespay().getResponse();
                response.setIsPrepaid(BILL_NOT_ISPREPAID);
                response.setPaymentRule(BILL_PAYMENTRULE_ALL);
                response.setCustomerCode(req.getCustomerCode());
                if (req.getIdservicetype().equals(Processor.ServiceTypeEnum.DIEN.toString())
                        && !req.getIdprovider().equals("EVNHN")
                        && !req.getIdprovider().equals("EVNHCM")
                        && !req.getIdprovider().equals("EVNSPC")) {

                    response.setAddress(paymentRes.getElectric().getAddress());
                    response.setCustomername(paymentRes.getElectric().getName());
                    response.setBillAmt(paymentRes.getElectric().getAmount());
                } else if (req.getIdservicetype().equals("VNTOPUP")) {
                    response.setBillAmt(paymentRes.getVntopup().getAmount());
                } else if (req.getIdservicetype().equals("VEMAYBAY")) {
                    //modify for get info 
                    //Helper.writeLogError(MobileController.class,String.format("[ADD LOG KIMNCM - VE MAY BAY 8 -%1$s]", ToStringBuilder.reflectionToString(billRequest)));
                    //set again thon tin ve may bay VNPAY
                    if (req.getIdprovider().endsWith("VEMAYBAY")) {
                        Airline air = paymentRes.getAirline();
                        if (air != null && air.getListschedule() != null) {
                            for (int i = 0; i < air.getListschedule().size(); i++) {
                                Schedule schedu = air.getListschedule().get(i);
                                schedu.setCode(schedu.getBrand().concat("-").concat(schedu.getCode()));
                                air.getListschedule().set(i, schedu);
                            }
                        }
                        response.setAirline(air);
                        response.setBillAmt(paymentRes.getAirline().getAmount());
                    } else {
                        if (billRequest.getIdpartner().equals(IIBConstants.PARTNER_BANKNET)) {
                            response.setCustomername(paymentRes.getBilling().getCustomername());
                            response.setAddress(paymentRes.getBilling().getAddress());
                            response.setBillAmt(paymentRes.getBilling().getAmount());
                        } else {
                            response.setAirline(paymentRes.getAirline());
                            response.setBillAmt(paymentRes.getAirline().getAmount());
                        }
                    }
                    //response.setCustomername(paymentRes.getBilling().getCustomername());
                    //response.setAddress(paymentRes.getBilling().getAddress());

                } else if (req.getIdservicetype().equals("HOCPHI") & req.getIdprovider().equals("BCN")) {
                    //modify for get info 
                    response.setPhieuthu(paymentRes.getTtpt());
                    //response.setCustomername(paymentRes.getBilling().getCustomername());
                    //response.setAddress(paymentRes.getBilling().getAddress());
                    response.setBillAmt(String.valueOf(paymentRes.getTtpt().getTongthu()));
                } else {
                    String customername = paymentRes.getBilling().getCustomername();
                    if (isFlgPrepaid(customername, billRequest.getIdprovider())) {
                        String[] strTempArr = customername.split("@");
                        response.setPaymentRule(strTempArr[3]);
                        if (strTempArr[2].equalsIgnoreCase("true")) {
                            response.setIsPrepaid(BILL_ISPREPAID);
                            if (strTempArr.length == 6) {
                                response.setExpireDate(strTempArr[5]);
                            }
                            //response.setPaymentRule(null);
                        } else if (strTempArr[3].equalsIgnoreCase(BILL_PAYMENTRULE_ORDER) || strTempArr[3].equalsIgnoreCase(BILL_PAYMENTRULE_ANY)) {
                        } else if (strTempArr[3].equalsIgnoreCase(BILL_PAYMENTRULE_AMOUNT)) {
                            response.setPaymentRange(strTempArr[5]);
                        }
                        if (response.getIsPrepaid().equals(BILL_ISPREPAID) || response.getPaymentRule().equals(BILL_PAYMENTRULE_ORDER)
                                || response.getPaymentRule().equals(BILL_PAYMENTRULE_ANY) || response.getPaymentRule().equals(BILL_PAYMENTRULE_AMOUNT)) {
                            ArrayList<BillDetail> billList = convertCustomerNameToBill(customername);
                            BillInfo[] bills = new BillInfo[billList.size()];
                            for (int i = 0; i < billList.size(); i++) {
                                bills[i] = new BillInfo();
                                BeanUtils.copyProperties(bills[i], billList.get(i));
                                bills[i].setCustomerId(req.getCustomerCode());

                            }
                            response.setBills(bills);

                        }
                        response.setCustomername(strTempArr[0]);
                    } else {
                        // kiem tra xem co phai doi tac VNPT hay ko. Neu dung thi cat chuoi va chi tra ve ten khach hang. Ko tra cac thong tin khac khien khach hang confuse
                        if ("VNPT".equals(req.getIdservicetype())) {
                            String data = paymentRes.getBilling().getCustomername();
                            String[] temp = data.split("@");
                            response.setCustomername(temp[0]);
                        } else if ("TRAFFICTOPUP".equals(req.getIdservicetype())) {
                            response.setAddinfo(paymentRes.getBilling().getAddinfo());
                            response.setCustomername(paymentRes.getBilling().getCustomername().split("#")[0]);
                        } else { // cac doi tac khac ko phai VNPT thi tra ve chuoi ky tu binh thuong
                            response.setCustomername(paymentRes.getBilling().getCustomername());
                        }
                    }
                    response.setAddress(paymentRes.getBilling().getAddress());
                    response.setBillAmt(paymentRes.getBilling().getAmount());
                }
            }

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));

        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param objprovider
     * @param language
     * @return
     */
    public BillProviderDetail copyDBToProviderDetail(Object objprovider, String language) {
        try {
            BillProviderDetail provider = new BillProviderDetail();
            if (language.equals("VN")) {

                BeanUtils.copyProperties(provider, objprovider);
            } else {
                HashMap hm_Provider = (HashMap) objprovider;
                provider.setIdprovider(hm_Provider.get("idprovider").toString());
                provider.setProvidername(hm_Provider.get("providername_eng").toString());
            }
            return provider;
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
    public String BillPayment(String xml) {
        try {
            BillPaymentRq billRequest = (BillPaymentRq) Xml.unMarshaller(BillPaymentRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            response.setCifNo(billRequest.getCifNo());
            response.setTxnType(billRequest.getTxnType());
            response.setAccountNo(billRequest.getDebitAccount());

            int isValidAuth = billRequest.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {
                //Bo sung cung cap nuoc, hoc phi vnpt theo luong moi
                PblPartnerService partnerService = ControllerUtil.getPartnerInfo(billRequest.getIdservicetype(), billRequest.getIdprovider());
                if ("VNPT_MEDIA".equals(partnerService.getIdPartner())) {
                    billRequest.setIdpartner(partnerService.getIdPartner());
                    //dich vu hoa don vnpt media
                    if (VNPTConstant.VNPT_SERVICE_HOADON.equals(billRequest.getIdservicetype())) {
                        BillingVNPTMediaController billingVNPT = new BillingVNPTMediaController();
                        // Call qua VNPT
                        String payBillResp = billingVNPT.payBillingVNPTMedia(billRequest, response, partnerService.getAccFcus());
                        return payBillResp;
                    }

                    //dich vu hoc phi, nuoc 
                    if ("NUOC".equals(billRequest.getIdservicetype())) {
                        BillingVNPTController billingVNPT = new BillingVNPTController();
                        // Call qua VNPT
                        String payBillResp = billingVNPT.payBillingWaterVNPT(billRequest, response, partnerService.getAccFcus());
                        return payBillResp;
                    } else if ("HOCPHI".equals(billRequest.getIdservicetype())) {
                        BillingVNPTController billingVNPT = new BillingVNPTController();
                        PaymentVneduVNPTRp payResponse = new PaymentVneduVNPTRp();
                        // Call qua VNPT
                        String payBillResp = billingVNPT.payBillingHocPhiVNPT(billRequest, payResponse, partnerService.getAccFcus());
                        return payBillResp;
                    }
                }

                //thay doi luong thanh toan nuoc DAWACO
                if (billRequest.getIdpartner() != null && billRequest.getIdpartner().equals(partnerService.getIdPartner())) {
                    if ("NUOC".equals(billRequest.getIdservicetype()) && "DAWACO".equals(billRequest.getIdprovider())) {
                        BillingDawacoController billingDawaco = new BillingDawacoController();
                        String rs = billingDawaco.billPaymentDawaco(billRequest);
                        return rs;
                    }
                }

                //check special bill for get customer name: isprepaid, many bills, bill prudential
                //Support multi partner for topup
                if (billRequest.getCustomerCode() != null) {
                    billRequest.setCustomerCode(billRequest.getCustomerCode().trim());
                }

                if (billRequest.getIdservicetype()
                        .equalsIgnoreCase("VNTOPUP")) {
                    billRequest.setIdprovider(setTOPUPPartner(billRequest.getCustomerCode()));
                }
                if (billRequest.getIsPrepaid() != null && billRequest.getPaymentRule() != null && (billRequest.getIsPrepaid().equals(BILL_ISPREPAID) || billRequest.getPaymentRule().equals(BILL_PAYMENTRULE_ORDER)
                        || billRequest.getPaymentRule().equals(BILL_PAYMENTRULE_ANY) || billRequest.getPaymentRule().equals(BILL_PAYMENTRULE_AMOUNT))) {
                    convertBillToCustomerName(billRequest);
                } else {
                    billRequest.setCustomerNameBill(billRequest.getCustomerCode());
                }

                paybill(billRequest);

                if (!billRequest.getResult().equals("0")) {
                    return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.get(billRequest.getResult())));
                } else {
                    if (billRequest.getRefBillpaid() != null) {
                    }
                    if (billRequest.getRefBillpaid() == null) {
                        return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.get(billRequest.getResult())));
                    } else {
                        response.setTxnID(billRequest.getRefBillpaid());
                        response.setTxnCommitTime(getTime());
                    }
                }

                return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));
            }
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
    public String GetToken(String xml) {
        try {
            GetTokenRq tokenRq = (GetTokenRq) Xml.unMarshaller(GetTokenRq.class, xml);
            GetTokenRp response = new GetTokenRp();

            response.setCustomerNo(tokenRq.getCustomerNo());

            List custInfo = Helper.getDBI().getTokenbyCustno(tokenRq.getCustomerNo());
            if (custInfo == null || custInfo.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_TOKEN));
            }
            HashMap hm_Td = (HashMap) custInfo.get(0);

            response.setSerialno(hm_Td.get("serialno").toString());

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));

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
    public String CheckToken(String xml) {
        try {
            CheckTokenRq tokenRq = (CheckTokenRq) Xml.unMarshaller(CheckTokenRq.class, xml);
            CheckTokenRp response = new CheckTokenRp();

            String actionType = "GET";
            if (tokenRq.getActionType() != null && tokenRq.getActionType().equals("AUTH")) {
                actionType = "AUTH";
            }
            response.setSerialno(tokenRq.getSerialno());

            //UNCOMMENT AFTER --START
            //List token = Helper.getDBI().getValidToken(tokenRq.getSerialno());
            Tokenkey tkey = new Tokenkey();
            String result = tkey.checkSerialToken(tokenRq.getSerialno(), actionType);
            if (result == null || result.isEmpty()) {
                response.setIsValidToken("0");
            } else {
                response.setIsValidToken("1");

                response.setTokenUpg(result.split("#")[0]);
                if (actionType.equals("AUTH")) {
                    if (result.split("#").length == 1) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOT_GETCHALLENGCODE));
                    } else {
                        response.setChallengeCode(result.split("#")[1]);
                    }
                }

                /*
                List custInfo = Helper.getDBI().checkTokenbySerialno(tokenRq.getSerialno());
                if (custInfo == null || custInfo.isEmpty()) {
                } else {
                    HashMap hm_Td = (HashMap) custInfo.get(0);
                    response.setCustomerNo(hm_Td.get("idcust").toString());
                }*/
            }

            //UNCOMMENT AFTER --END
            //COMMENT AFTER --START
            /*
             List custInfo = Helper.getDBI().checkTokenbySerialno(tokenRq.getSerialno());
             if (custInfo == null || custInfo.isEmpty()) {
             } else {
             response.setIsValidToken("1");
             HashMap hm_Td = (HashMap) custInfo.get(0);
             response.setCustomerNo(hm_Td.get("idcust").toString());
             }

             if (response.getSerialno()
             .length() <= 15 && isNumeric(response.getSerialno())) {
             response.setIsValidToken("1");
             } else {
             response.setIsValidToken("0");
             }
             */
            //COMMENT AFTER --END
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public static boolean isNumeric(String str) {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    /**
     *
     * @param pepe
     * @param processingcode
     * @return
     */
    public static String callRequestPayment(BillPaymentRq pepe, String processingcode) {
        try {
//
            Request r = new Request();
            if (pepe.getIdservicetype().equals("VNTOPUP")) //&& pepe.getIdprovider().equals("VNPAY"))
            {
                Vntopup vntopup = new Vntopup();
                vntopup.setMobnoreq(pepe.getCustomerCode());
                vntopup.setMobnoload(pepe.getCustomerCode());
                vntopup.setAmount(pepe.getPayAmt());
                r.setVntopup(vntopup);

            } /*else if (pepe.getIdservicetype().equals("VNTOPUP") && pepe.getIdprovider().equals("PAYOO")) {
                Vntopup vntopup = new Vntopup();
                vntopup.setMobnoreq(pepe.getCustomerCode());
                vntopup.setMobnoload(pepe.getCustomerCode());
                vntopup.setAmount(pepe.getPayAmt());
                r.setVntopup(vntopup);
            }*/ else {
                pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));
                Billing b = new Billing();

                b.setCustomercode(pepe.getCustomerCode());
                //hieunt16 set them amount
                b.setAmount(pepe.getBillAmt());
                if (processingcode.equals("PAY")) {
                    //BIDIWACO/DNPWATER
                    if (pepe.getRespay() != null) {
                        if (pepe.getRespay().getResponse() != null) {
                            if (pepe.getRespay().getResponse().getBilling() != null) {
                                String billingAdinfo = "";
                                if (pepe.getIdprovider().equals("BIDIWACO")) {
                                    billingAdinfo = pepe.getRespay().getResponse().getBilling().getAddinfo() == null ? "" : pepe.getRespay().getResponse().getBilling().getAddinfo() + "%@" + msgSendCore;
                                    logger.info("msgSendCore: " + billingAdinfo);
                                } else {
                                    billingAdinfo = pepe.getRespay().getResponse().getBilling().getAddinfo() == null ? "" : pepe.getRespay().getResponse().getBilling().getAddinfo();
                                }

                                b.setAddinfo(billingAdinfo);
                            } else {
                                logger.warn("pepe.getRespay().getResponse().getBilling() is null");
                            }
                        } else {
                            logger.warn("pepe.getRespay().getResponse() is null");
                        }
                        if (pepe.getIdservicetype().equals("TRAFFICTOPUP")) {
                            b.setCustomername(pepe.getRespay().getResponse().getBilling().getCustomername());
                        }
                    } else {
                        logger.warn("getRespay is null");
                    }
                    b.setAmount(pepe.getPayAmt());

                    //Reset customer code for prepaid case, SCTV6, ACS
                    if (isFlgPrepaid(pepe.getCustomerCode(), pepe.getIdprovider())) {
                        b.setCustomername(pepe.getCustomerCode());
                        b.setCustomercode(pepe.getCustomerCode().split("@")[0]);
                    }
                } else {
                    if (pepe.getIdservicetype().equals("TRAFFICTOPUP")) {
                        b.setAmount(pepe.getBillAmt());
                    }
                }
                r.setBilling(b);
            }

            RequestPayment reqpay = new RequestPayment();
            //reqpay.setTranscode(String.valueOf("AT1"));
            //reqpay.setChannel("INTERNET");
            reqpay.setChannel("MOBILE");
            reqpay.setServicecode(pepe.getIdservicetype());
            reqpay.setProvidercode(pepe.getIdprovider());
            reqpay.setProcessingcode(processingcode);
            reqpay.setPaymentmethod("ACCOUNT");
            reqpay.setAccountno(pepe.getDebitAccount());
            //reqpay.setAccountno("0010100386600001");
            reqpay.setDatetime(Common.getDate("ddMMyyyyHHmmss"));
            reqpay.setRequest(r);

            //String xml = Xml.Marshaller(reqpay);
            //ControllerImpl ci = new ControllerImpl();
            //String rsxml = ci.requestPayment(xml);
            //ResponsePayment responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, rsxml);
            String rsxml = null;

            ResponsePayment responsepay = null;
            if (processingcode.equals("QUERY")) {
                //IIB Payment                                                            
                IIBBillingPaymentService iibBillingService = new IIBBillingPaymentService();
                responsepay = iibBillingService.callPartnerGateway(Configuration.getIIBContext(), reqpay);
                if (responsepay.getResult().equals(Message.PaymentResultEnum.OK.getValue())) {
                    if (responsepay.getPartnercode().equals(IIBConstants.PARTNER_BANKNET)) {
                        responsepay = iibBillingService.queryPartner(Configuration.getIIBContext(), responsepay);
                        rsxml = Xml.Marshaller(responsepay);
                    } else {
                        // da cau hinh trong partner code
                        String xml = Xml.Marshaller(reqpay);
                        ControllerImpl ci = new ControllerImpl();
                        rsxml = ci.requestPayment(xml);

                        //thiet lap gia tri tra ve
                        responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, rsxml);
                    }
                } // chưa cấu hình partner trong partner_code
                else if (responsepay.getResult().equals(Message.PaymentResultEnum.PARTNER_INVALID.getValue())) {
                    String xml = Xml.Marshaller(reqpay);

                    ControllerImpl ci = new ControllerImpl();
                    rsxml = ci.requestPayment(xml);

                    //thiet lap gia tri tra ve
                    responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, rsxml);

                } else {
                    logger.error("***- ERROR IB (FUNCTION callRequestPayment - iibBillingService - callPartnerGateway): " + responsepay.getResult() + responsepay.getResultMessage());
                }
            } else if (processingcode.equals("PAY")) {

                String xml = Xml.Marshaller(reqpay);
                logger.error("xml call requestPayment:" + xml);
                ControllerImpl ci = new ControllerImpl();
                rsxml = ci.requestPayment(xml);
                logger.error("xml response requestPayment:" + rsxml);
                //thiet lap gia tri tra ve
                responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, rsxml);
            }
            //logger.error("***- RETURN FROM WS " + rsxml);
            //thiet lap gia tri tra ve

            if (reqpay.getServicecode()
                    .equals("VNTOPUP")) {
                pepe.setRespay(responsepay);
            }

            if (isFlgPrepaid(pepe.getCustomerCode(), pepe.getIdprovider())) {
                pepe.setCustomerCode(pepe.getCustomerCode().split("@")[0]);
                if (responsepay.getResponse().getBilling() != null) {
                    responsepay.getResponse().getBilling().setAmount(pepe.getPayAmt());
                }
                pepe.setRespay(responsepay);
            }
            //Update for transaction code by Hieu

            if (pepe.getRespay() != null) {
                pepe.getRespay().setTranscode(responsepay.getTranscode());
            }

            pepe.setResult(responsepay.getResult());

            if (Common.isEmpty(rsxml)) {
                pepe.setResult(Message.PaymentResultEnum.TIMEOUT.getValue());
                //return;
            }
            //kimncm add setIdpartner for case set again ENVHN            
            pepe.setIdpartner(responsepay.getResponse().getIdpartner());
            pepe.setRespay(responsepay);

            if (processingcode.equals("QUERY")) {
                //setBillInfo(xml, pepe);   
                //pepe.setRespay(responsepay);

                checkAmount(pepe);

            }

            // Da thanh toan xong, REPONSE khong tra ket qua gi het.
            /*
            if (processingcode.equals("PAY")) {  
                pepe.setRespay(responsepay);
            }*/
            return rsxml;
        } catch (Exception ex) {
            logger.error("***- ERROR MB (FUNCTION callRequestPayment): " + ex.getMessage());
            pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue());

            return null;
        }
    }

    private String getResponseQueryAmount(BillPaymentRq pepe) {
        String amount = "";
        try {
            if (pepe.getIdservicetype().equals(Processor.ServiceTypeEnum.DIEN.toString())) { // && !pepe.getIdprovider().equals("EVNHN")
                amount = pepe.getRespay().getResponse().getElectric().getAmount();
            } else if (pepe.getIdservicetype().equals("VNTOPUP")) {
                amount = pepe.getRespay().getResponse().getVntopup().getAmount();
            } else if (pepe.getIdservicetype().equals("VEMAYBAY")) {
                if (pepe.getIdprovider().endsWith("VEMAYBAY")) {
                    amount = pepe.getRespay().getResponse().getAirline().getAmount();
                } else {
                    if (pepe.getIdpartner().equals(IIBConstants.PARTNER_BANKNET)) {
                        amount = pepe.getRespay().getResponse().getBilling().getAmount();
                    } else {
                        amount = pepe.getRespay().getResponse().getAirline().getAmount();
                    }
                }
            } else if (pepe.getIdservicetype().equals("HOCPHI") && pepe.getIdprovider().equals("BCN")) {
                amount = String.valueOf(pepe.getRespay().getResponse().getTtpt().getTongthu());
            } else {
                amount = pepe.getRespay().getResponse().getBilling().getAmount();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return amount;
    }

    private void paybill(BillPaymentRq pepe) throws Exception {
        //0. Lay so thong tin tu transcode.
        //PblEbkProcess pep = Helper.getDBI().getEbkProcessById(Integer.parseInt(reqexchg.getReq().getTranscode()));
        String callPay = "0";//0: chua hach toan; 1: hach toan;2: da call doi tac
        try {

            //1. Kiem tra Bill & tai khoan phai hop le
            //@TODO:
            //2. Xu ly thanh toan BILL & Chuyen tien.
            //2.1 Lay lai thong tin BIll             
            //IIB Payment   
            Request r = new Request();
            if (pepe.getIdservicetype().equals("VNTOPUP") && pepe.getIdprovider().equals("VNPAY")) {
                pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));
                Vntopup vntopup = new Vntopup();
                vntopup.setMobnoreq(pepe.getCustomerCode());
                vntopup.setMobnoload(pepe.getCustomerCode());
                vntopup.setAmount(pepe.getPayAmt());
                r.setVntopup(vntopup);

            } else {
                pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));
                Billing b = new Billing();
                b.setCustomercode(pepe.getCustomerCode());
                b.setAmount(pepe.getPayAmt());

                r.setBilling(b);
            }

            RequestPayment reqpay = new RequestPayment();
            //reqpay.setTranscode(String.valueOf("AT1"));
            //reqpay.setChannel("INTERNET");
            reqpay.setChannel("MOBILE");
            reqpay.setServicecode(pepe.getIdservicetype());
            reqpay.setProvidercode(pepe.getIdprovider());
            reqpay.setProcessingcode("QUERY");
            reqpay.setPaymentmethod("ACCOUNT");
            reqpay.setAccountno(pepe.getDebitAccount());
            //reqpay.setAccountno("0010100386600001");
            reqpay.setDatetime(Common.getDate("ddMMyyyyHHmmss"));
            reqpay.setRequest(r);

            ResponsePayment responsepay = null;

            //IIB Payment                                                 
            IIBBillingPaymentService iibBillingService = new IIBBillingPaymentService();
            logger.warn("truoc khi goi qua IIB");
            responsepay = iibBillingService.callPartnerGateway(Configuration.getIIBContext(), reqpay);
            logger.warn("sau khi goi qua IIB");

            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            Gson gson = builder.create();
            String jsonRequestBL = gson.toJson(responsepay);

            logger.warn("truoc khi goi qua IIB:" + gson.toJson(reqpay));
            logger.warn("sau khi goi qua IIB:" + jsonRequestBL);

            if (responsepay.getResult().equals(Message.PaymentResultEnum.OK.getValue()) && responsepay.getPartnercode().equals(IIBConstants.PARTNER_BANKNET)) {
                executePayBillIIB(reqpay, pepe);
            } else {
                if (!pepe.getIdservicetype().equals("VNTOPUP")) {
                    //Với các loại bill trả trước thì ko query lại, do IdBillPaid sẽ thay đổi
                    if (!isFlgPrepaid(pepe.getCustomerNameBill(), pepe.getIdprovider())
                            || pepe.getIdprovider().equals("BIDIWACO")
                            || pepe.getIdprovider().equals("DNPWATER")) {
                        logger.warn("GET LAI BILL:" + pepe.getIdprovider());
                        callRequestPayment(pepe, "QUERY");
                        //2.2 KIem tra tai khoan du thanh toan
                        // Kiem tra partner cua luong thanh toan nay la gi. Neu la VNPAY thi skip qua ko validate tong so tien
                        String idPartner = ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider());
                        String amountQuery = getResponseQueryAmount(pepe);
                        if (!pepe.getResult().equals("0")
                                || (BILL_PAYMENTRULE_ALL.equals(pepe.getPaymentRule())
                                && !"VNPAY".equalsIgnoreCase(idPartner)
                                && !pepe.getPayAmt().equalsIgnoreCase(amountQuery))) {
                            logger.warn("Khong the thuc hien thanh toan hoa don cho khach hang. Result = [" + pepe.getResult() + "], PaymentRule = [" + pepe.getPaymentRule() + "], PayAmt = [" + pepe.getPayAmt() + "], Response query amount = [" + amountQuery + "]");
                            //return showmessage(pepe.getPep(), PaymentResultEnum.get(pepe.getResult()), "3", "Truy vấn bill để thực hiện trong quá trình thanh toán không thành công " + getMsgResult(pepe.getResult(), idlanguage));
                            insPblLog(pepe, "Truy vấn bill để thực hiện trong quá trình thanh toán không thành công. " + Message.getMessagePaymentResult(PaymentResultEnum.get(pepe.getResult())));
                            return;
                        }
                    } else {
                        pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));
                    }
                } else {
                    pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));
                }

                //VwCustAccount custacc = Helper.getDBI().getCustAccountFcdbByAccountNo(pepe.getDebitAccount());
                /*
            VwCustAccount custacc = new VwCustAccount();
            //kimncm add more
            List acc = Helper.getDBI().getAccountCASA(pepe.getDebitAccount());
            if (acc == null || acc.isEmpty()) {
                //return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            } else {
                HashMap hm_Casa = (HashMap) acc.get(0);
                custacc.setCustAcNo(pepe.getDebitAccount());
                custacc.setCcy(hm_Casa.get("codacctcurr").toString());
                custacc.setAccountClass(hm_Casa.get("prdcode").toString());
                custacc.setAcyCurrBalance(BigDecimal.valueOf(Double.valueOf(hm_Casa.get("numbalance").toString())));
                custacc.setAcyAvlBal(BigDecimal.valueOf(Double.valueOf(hm_Casa.get("numavailbal").toString())));
                if (hm_Casa.get("add1") != null) {
                    custacc.setAddress(hm_Casa.get("add1").toString());
                }
                custacc.setAcDesc(hm_Casa.get("acctdesc").toString());
                custacc.setCustNo(hm_Casa.get("idcustomer").toString());

            }
                 */
                //kimncm add more 
                logger.warn("getCustAccountByAccountNo:" + pepe.getDebitAccount());
                VwCustAccount custacc = Helper.getDBI().getCustAccountByAccountNo(pepe.getDebitAccount());
                if (custacc == null) {
                    //return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                } else {
                    // HashMap hm_Casa = (HashMap) acc.get(0);
                    custacc.setCustAcNo(pepe.getDebitAccount());
                    custacc.setCcy(custacc.getCcy());
                    custacc.setAccountClass(custacc.getAccountClass());
                    custacc.setAcyCurrBalance(custacc.getAcyCurrBalance());
                    custacc.setAcyAvlBal(custacc.getAcyAvlBal());
                    if (custacc.getAddress() != null) {
                        custacc.setAddress(custacc.getAddress());
                    }
                    custacc.setAcDesc(custacc.getAcDesc());
                    custacc.setCustNo(custacc.getCustNo());
                }
                //kiem tra loai tien cua TK TT
                if (!custacc.getCcy().equals("VND")) {
                    pepe.setResult(PaymentResultEnum.NOT_MATCH_CCY_VND.getValue());
                    return;
                }
                logger.warn("checkAmountToPaybill:" + pepe.getPayAmt());
                PaymentResultEnum checkbill = checkAmountToPaybill(pepe.getPayAmt(), custacc);

                if (!checkbill.equals(PaymentResultEnum.OK)) {
                    pepe.setResult(PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY.getValue());
                    return;
                }

                //2.4 Thuc hien cap nhap process            
                //pepe.getPep().setOtptype(reqexchg.getReq().getOtp().getType());
                //pepe.getPep().setOtp(reqexchg.getReq().getOtp().getTxnpin());
                //2.3 Thuc hien chuyen tien
                PblBillpaid billpaid = new PblBillpaid();
                String refCub = "";
                String BranchDawaco = "";
                if (pepe.getIdpartner() != null && pepe.getIdpartner().equals("DAWACO")) {
                    BranchDawaco = pepe.getRespay().getProvidercode();
                }
                //pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));
                try {
                    logger.warn("transferPaybill:" + pepe.getPayAmt());
                    refCub = transferPaybill(pepe, custacc, billpaid, "transfer");
                } catch (Exception e) {
                    //**error 2**/
                    logger.error("***- ERROR MB (FUNCTION paybill): " + e.getMessage());
                    pepe.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
                    insPblLog(pepe, "Trích tiền từ tài khoản thanh toán xảy ra ngoại lệ error 2 exception=" + e.getMessage().substring(0, 500));
                    return;
                    //return showmessage(pepe.getPep(), PaymentResultEnum.SYSTEM_ERROR, "3", "Trích tiền từ tài khoản thanh toán xảy ra ngoại lệ error 2 exception=" + e.getMessage().substring(0, 500));
                }
                if (refCub == null || refCub.equalsIgnoreCase("")) {
                    pepe.setResult(PaymentResultEnum.CANNOT_TRANSFERFCUBS.getValue());
                    insPblLog(pepe, "Không thực hiện được trích tiền từ tài khoản thanh toán khách hàng.");
                    return;
                    //return showmessage(pepe.getPep(), PaymentResultEnum.CANNOT_TRANSFERFCUBS, "3", "Không thực hiện được trích tiền từ tài khoản thanh toán khách hàng.");
                }
                if (refCub.equalsIgnoreCase("TIMEOUT")) {
                    pepe.setResult(PaymentResultEnum.CANNOT_TRANSFERFCUBS.getValue());
                    insPblLog(pepe, "Trích tiền từ tài khoản thanh toán khách hàng timeout. Giao dịch thất bại.");
                    return;
                    //return showmessage(pepe.getPep(), PaymentResultEnum.CANNOT_TRANSFERFCUBS, "3", "Không thực hiện được trích tiền từ tài khoản thanh toán khách hàng.");
                }
                callPay = "1";
                //billpaid.setIdbillpaid(idBillPaid);

                //2.5 Thuc hien thanh toan Bill
                pepe.setResult("-99");

                //Gan customer code cho customer name
                if (isFlgPrepaid(pepe.getCustomerNameBill(), pepe.getIdprovider())) {
                    pepe.setCustomerCode(pepe.getCustomerNameBill());
                }
                callPay = "2";
                callRequestPayment(pepe, "PAY");

                //2.6 Cap nhap thong tin giao dich neu giao dich khong thanh cong thi tra lai hoa don cho KH
                //cap nhap lai thong tin billpaid
                //billpaid = Helper.getDBI().getPaybillBillPaidById(idBillPaid);
                Date d = new Date();
                billpaid.setPaydate(d);
                billpaid.setDataxml(Xml.Marshaller(pepe.getRespay()));
                if (pepe.getIdpartner().equals("DAWACO")) {
                    billpaid.setRefPartner(BranchDawaco + "#" + pepe.getRespay().getTranscode());
                } else {
                    billpaid.setRefPartner(pepe.getRespay().getTranscode());

                }
                //pepe.setResult("96");
                if (!pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())) {
                    //hoach toan tra lai tai khoan cho khach hang khi thanh toan hoa don khong thanh cong
                    //String idreturnrefcub = "";

                    //Update sua timeout by Hieu, 31/12/2013
                    //if (!pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.TIMEOUT.getValue())) {
                    //    idreturnrefcub = transferPaybill(reqexchg, pepe.getPep(), custacc, billpaid, "recieve");
                    //}                                
                    //billpaid.setStatus(Character.valueOf('H'));
                    /*
                 //Cap nhat code hoan tien moi.
                 if (!pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.TIMEOUT.getValue())
                 && !pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue())
                 && !pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.ERROR_PARTNER.getValue())) {
                 idreturnrefcub = transferPaybill(pepe, custacc, billpaid, "recieve");
                 }

                 if (idreturnrefcub == null || idreturnrefcub.equalsIgnoreCase("")) {
                 billpaid.setStatus(Character.valueOf('H'));
                 Helper.getDBI().updatePaybillBillPaid(billpaid);
                 pepe.setResult(PaymentResultEnum.CANNOT_PAYMENT_NOT_REFUND.getValue());
                 insPblLog(pepe, "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                 return;
                 //return showmessage(pep, PaymentResultEnum.get(scb.com.vn.message.Message.PaymentResultEnum.TIMEOUT.getValue()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                 } else {
                 billpaid.setStatus(Character.valueOf('F'));
                 Helper.getDBI().updatePaybillBillPaid(billpaid);
                 pepe.setResult(PaymentResultEnum.CANNOT_PAYMENT_REFUNDED.getValue());
                 insPblLog(pepe, "Hóa đơn thanh toán không thành công - Số giao dịch tham chiếu fcus trả lại tiền cho KH :" + idreturnrefcub);
                 return;
                 //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Số giao dịch tham chiếu fcus trả lại tiền cho KH :" + idreturnrefcub);
                 }
                     */
                    //Update code hoan tien moi
                    /**
                     * DIEU CHINH HOAN TIEN THEO QUY DINH DICH VU HOAN TIEN ->
                     * TRANG THAI: F TREO -> TRANG HAI: H
                     *
                     */
                    //pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())
                    if (pepe.getResult().equals(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue())) {
                        //Giao dich that bai, thuc hien hoan tien cho KH
                        String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong & hoan tien bill. [idbillpaid-%1$s]-[Trancode-%2$s]";
                        logger.info(String.format(a, billpaid.getIdbillpaid(), billpaid.getRefPartner()));

                        String _msgouttransfer = "TRANSFER UBS. Thuc hien HOAN TIEN HOAN TIEN %1$s. [So but toan-%2$s]";
                        String fccRef = billpaid.getRefFcubs();
                        String result = revertTransferFCUBS(fccRef, 60);

                        if (result == null) {
                            //Revert khong thanh cong, tra soat hoan tien cho KH
                            billpaid.setStatus(Character.valueOf('H'));
                            updateBillStatusToDB(billpaid, 'H');
                            pepe.setResult(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());
                            insPblLog(pepe, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                            logger.info(String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                            return;
                            //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                        } else if (result.equalsIgnoreCase("0")) {
                            billpaid.setStatus(Character.valueOf('F'));
                            updateBillStatusToDB(billpaid, 'F');
                            insPblLog(pepe, String.format(_msgouttransfer, "THANH CONG", fccRef));
                            logger.info(String.format(_msgouttransfer, "THANH CONG", fccRef));
                            return;
                            //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Số giao dịch tham chiếu fcus trả lại tiền cho KH :" + fccRef);
                        } else {
                            //Revert khong thanh cong, tra soat hoan tien cho KH
                            billpaid.setStatus(Character.valueOf('H'));
                            updateBillStatusToDB(billpaid, 'H');
                            pepe.setResult(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());
                            insPblLog(pepe, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                            logger.info(String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                            return;
                            //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                        }
                    } else if (pepe.getResult().equals(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue())) {
                        if (pepe.getIdservicetype().equals("VNTOPUP")) {
                            String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong & hoan tien bill. [idbillpaid-%1$s]-[Trancode-%2$s]";
                            logger.info(String.format(a, billpaid.getIdbillpaid(), billpaid.getRefPartner()));

                            String _msgouttransfer = "TRANSFER UBS. Thuc hien HOAN TIEN HOAN TIEN %1$s. [So but toan-%2$s]";
                            String fccRef = billpaid.getRefFcubs();
                            String result = revertTransferFCUBS(fccRef, 60);

                            if (result == null) {
                                //Revert khong thanh cong, tra soat hoan tien cho KH
                                billpaid.setStatus(Character.valueOf('H'));
                                updateBillStatusToDB(billpaid, 'H');
                                logger.info(String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                                insPblLog(pepe, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                                return;
                                //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                            } else if (result.equalsIgnoreCase("0")) {
                                billpaid.setStatus(Character.valueOf('F'));
                                updateBillStatusToDB(billpaid, 'F');
                                pepe.setResult(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue());
                                insPblLog(pepe, String.format(_msgouttransfer, "THANH CONG", fccRef));
                                logger.info(String.format(_msgouttransfer, "THANH CONG", fccRef));
                                return;
                                //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Số giao dịch tham chiếu fcus trả lại tiền cho KH :" + fccRef);
                            } else {
                                //Revert khong thanh cong, tra soat hoan tien cho KH
                                billpaid.setStatus(Character.valueOf('H'));
                                updateBillStatusToDB(billpaid, 'H');
                                logger.info(String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                                insPblLog(pepe, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                                return;
                                //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                            }
                        } else {
                            //Khong hoan tien, yeu cau doi tac gach bu giao dich
                            String a = "PAYBILL IS NOT SUCCESS. Hold tien KH, yeu cau doi tac gach bu tay. [idbillpaid-%1$s]";
                            billpaid.setStatus(Character.valueOf('H'));
                            updateBillStatusToDB(billpaid, 'H');
                            insPblLog(pepe, String.format(a, billpaid.getIdbillpaid()));
                            logger.info(String.format(a, billpaid.getIdbillpaid()));
                            return;
                            //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                        }
                    } else {
                        //Giao dich nghi van, thuc hien doi soat
                        //Hold toan bo giao dich
                        billpaid.setStatus(Character.valueOf('H'));
                        updateBillStatusToDB(billpaid, 'H');
                        pepe.setResult(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());
                        String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong, thuc hien doi soat. [idbillpaid-%1$s]";
                        insPblLog(pepe, String.format(a, billpaid.getIdbillpaid()));
                        logger.info(String.format(a, billpaid.getIdbillpaid()));
                        return;
                    }
                }
                //cap nhap thoi gian cho giao dich voi VNPAY           
                billpaid.setStatus(Character.valueOf('D'));
                Helper.getDBI().updatePaybillBillPaid(billpaid);
            }

        } catch (Exception ex) {
            //**error 3**/
            logger.error("***- ERROR MB (FUNCTION paybill 2): " + ex.getMessage());
            insPblLog(pepe, "Giao dịch thanh toán paybill của khách hàng đã xãy ra ngoại lệ error 3 exception=" + ex.getMessage());
            //return showmessage(pep, PaymentResultEnum.SYSTEM_ERROR, "4", "Giao dịch thanh toán paybill của khách hàng đã xãy ra ngoại lệ error 3 exception=" + ex.getMessage().substring(0, 500));
            if (callPay.equals("0")) {
                pepe.setResult(PaymentResultEnum.CANNOT_TRANSFERFCUBS.getValue());
            } else if (callPay.equals("1")) {
                pepe.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
            }
        }
    }

//Thuc hien hoac toan chuyen tien thanh toan hoa don
    private String transferPaybill(BillPaymentRq pep, VwCustAccount from_custacc, PblBillpaid billpaid, String transfer_type) {
        try {
            String msgpay = "";
            String msgback = "";
            if (pep.getIdservicetype().equalsIgnoreCase("VEMAYBAY")) {
                msgpay = String.format(getPaymentMsgEnum(PaymentMsgEnum.THANHTOAN_VMB), pep.getCustomerCode().toUpperCase());
                msgback = getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_VMB);//String.format(getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_VMB), pep.getCustomercode());
            } else if (pep.getIdservicetype().equalsIgnoreCase("VNTOPUP")) {
                msgpay = String.format(getPaymentMsgEnum(PaymentMsgEnum.THANHTOAN_VNTOPUP), pep.getCustomerCode().toUpperCase());
                msgback = getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_VNTOPUP);//String.format(getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_VNTOPUP), pep.getCustomercode());
            } else if ("BIDIWACO".equals(pep.getIdprovider()) && "NUOC".equals(pep.getIdservicetype())) {
                //msgpay = String.format(getPaymentMsgEnum(PaymentMsgEnum.THANHTOAN_BILL), pep.getCustomerCode().concat(",KY").concat(" ").concat(msgSendCore));
                msgpay = "TT hoa don. CAP NUOC BINH DINH. Ma KH: ".concat(pep.getCustomerCode()).concat(",KY ").concat(msgSendCore);
                msgback = getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_BILL);
                logger.info("msg send core:" + msgpay);
            } else {
                msgpay = String.format(getPaymentMsgEnum(PaymentMsgEnum.THANHTOAN_BILL), pep.getCustomerCode().toUpperCase());
                msgback = getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_BILL);//String.format(getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_BILL), pep.getCustomercode());
            }

            //1.1 Lay tai treo theo doi doi voi nha cung cap dich vu
            PblPartnerserviceId psid = new PblPartnerserviceId();
            psid.setIdpartner(pep.getIdpartner());
            psid.setIdprovider(pep.getIdprovider());
            psid.setIdservicetype(pep.getIdservicetype());
            PblPartnerservice ps = Helper.getDBI().getPartnerServiceById(psid);
            //1.2 Thuc hien chuyen khoan
            Fcubs fc = new Fcubs();
            IIBPaymentService iibPaymentService = new IIBPaymentService();
            BigDecimal billAmount = new BigDecimal(pep.getPayAmt());

            String refCub = "";
            int idbillPaid = -1;
            if (transfer_type.equalsIgnoreCase("transfer")) {

                //1.3 Insert bang BillPaid
                //PblBillpaid billpaid =new PblBillpaid();
                //kimncm - replace
                //VwMstchanneluser mstchanneluser = Helper.getDBI().getVwMstchanneluser(pep.getIdusr());
                //VwMstchanneluser mstchanneluser = Helper.getDBI().getVwMstchanneluser("1785");
                //MbMobiles mstchanneluser = Helper.getDBI().getCustomerFromMobile(pep.getUserName());
                List mbMobile = Helper.getDBI().getCustomerFromMobile(pep.getUserName());

                if (mbMobile == null || mbMobile.isEmpty()) {
                    logger.error("***- ERROR MB (FUNCTION transferPaybill): " + "not get customerid from DB VNINFO ");
                }
                HashMap hm_MB = (HashMap) mbMobile.get(0);

                billpaid.setIdpartner(pep.getIdpartner());

                PblServicetype pst = new PblServicetype();
                pst.setIdservicetype(pep.getIdservicetype());
                billpaid.setPblServicetype(pst);

                PblProvider pdr = new PblProvider();
                pdr.setIdprovider(pep.getIdprovider());

                billpaid.setPblProvider(pdr);

                billpaid.setCustomercode(pep.getCustomerCode());
                billpaid.setDataxml("");
                //kimncm - replace  
                /*
                 billpaid.setIduserMaker(mstchanneluser.getIduser());      
                 billpaid.setIdchanneluserMaker(mstchanneluser.getIdchanneluser());
                 billpaid.setIduserChecker(mstchanneluser.getIduser());            
                 billpaid.setIdchanneluserChecker(mstchanneluser.getIdchanneluser());
                 */
                billpaid.setIduserMaker(hm_MB.get("cus_id").toString());
                billpaid.setIdchanneluserMaker(pep.getUserName());
                billpaid.setIduserChecker(hm_MB.get("cus_id").toString());
                billpaid.setIdchanneluserChecker(pep.getUserName());

                billpaid.setIdchannel("03");
                billpaid.setUsertype("1");
                billpaid.setRefPartner("");
                billpaid.setRefFcubs("");
                billpaid.setTotalamount(Long.valueOf(pep.getPayAmt()));
                billpaid.setPaymentmethod(2);
                billpaid.setAccCust(from_custacc.getCustNo());
                billpaid.setAccIdaccount(from_custacc.getCustAcNo());
                //billpaid.setAccHoldername(from_custacc.getFullName());
                billpaid.setAccHoldername(from_custacc.getAcDesc());
                billpaid.setAccAddress(from_custacc.getAddress());
                Date d = new Date();
                billpaid.setInsdate(d);
                billpaid.setInsdateMaker(d);
                billpaid.setInsdateChecker(d);
                billpaid.setTransdate(d);
                billpaid.setPaydate(null);
                billpaid.setPaydateFcubs(d);
                billpaid.setIsapproved(Character.valueOf('A'));
                billpaid.setStatus(Character.valueOf('W'));
                //billpaid.setBranchcode(billpaid.getAccIdaccount().substring(0, 3));
                billpaid.setBranchcode(CommonUtils.getBranchAccount(billpaid.getAccIdaccount()));
                idbillPaid = Helper.getDBI().insertPaybillBillPaid(billpaid);

                if (idbillPaid <= 0) {
                    return "";
                } else {
                    //cap nhap process thanh cong
                    //pep.setIdscreen("4");
                    //pep.setStatus("A");
                    pep.setRefBillpaid(String.valueOf(idbillPaid));
                    //kimncm - comment
                    //Helper.getDBI().updEbkProcess(pep);
                }
                //Cat tien thanh toan hoa don cua KH
                //add product
                //refCub = fc.transferFCUBS(pep.getDebitAccount(), ps.getAccnofcubs(), billAmount, msgpay);
                //refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), ps.getAccnofcubs(), billAmount, msgpay);
                //BCN edit (S)

                if (pep.getIdpartner().equals("BCN")) {
                    String idschool = pep.getRespay().getResponse().getTtpt().getMatruong();
                    String accountTo = Helper.getDBI().getAccountProvider(idschool);
                    refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), accountTo, billAmount, msgpay);
                } else if (pep.getIdpartner().equals("EVNHN")) {
                    String partnerDetailId = pep.getCustomerCode().substring(0, 4);
                    String accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), accountTo, billAmount, msgpay);
                } else if (pep.getIdpartner().equals("EVNSPC")) {
                    String partnerDetailId = pep.getCustomerCode().substring(0, 6);
                    String accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), accountTo, billAmount, msgpay);
                } else if (pep.getIdpartner().equals("EVNCPC")) {
                    String partnerDetailId = pep.getCustomerCode().substring(0, 6);
                    String accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), accountTo, billAmount, msgpay);
                } else //LYDTY EDIT - 31/Dec/2019
                if (pep.getIdpartner().equals("DAWACO")) {
                    String partnerDetailId = pep.getRespay().getProvidercode();
                    String accountTo = Helper.getDBI().getAccountProvider("DAWACO_" + partnerDetailId);
                    refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), accountTo, billAmount, msgpay);
                } else //LYDTY EDIT
                if (pep.getIdpartner().equals("BIDIWACO") || pep.getIdpartner().equals("DNPWATER")) {
                    String partnerDetailId = pep.getIdpartner();
                    String accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), accountTo, billAmount, msgpay);
                    logger.info("msgSendCore: " + msgpay);
                } else if ("VNPAY".equals(pep.getIdpartner()) && "TRAFFICTOPUP".equals(pep.getIdservicetype()) && ("VETC".equals(pep.getIdprovider()) || "VDTC".equals(pep.getIdprovider()))) {
                    String partnerDetailId = pep.getIdpartner();
                    String accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    String productVETC = Configuration.getProperty("fcubs.producttransfer.mobile.paybill.VETC");
                    refCub = fc.transferFCUBSWithProductUser(productVETC, pep.getUserId(), pep.getDebitAccount(), accountTo, billAmount, msgpay);
                } else {
                    //refCub = iibPaymentService.executePaymentTransactionInternalRestSimple(Configuration.getIIBContext(), producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), ps.getAccnofcubs(), billAmount, msgpay, IIB_CHANNEL_MOBILE);
                    //refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), ps.getAccnofcubs(), billAmount, msgpay);
                    //ExecutePaymentTransactionInternal_out_Type executePaymentTransactionInternal_out_Type = iibPaymentService.executePaymentTransactionInternalRestSimple(Configuration.getIIBContext(), producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), ps.getAccnofcubs(), billAmount, msgpay, IIB_CHANNEL_MOBILE);
                    ExecutePaymentTransactionInternal_out_Type executePaymentTransactionInternal_out_Type = iibPaymentService.executePaymentInterRestSimpleWithBrn(Configuration.getIIBContext(), producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), ps.getAccnofcubs(), billAmount, msgpay, IIB_CHANNEL_MOBILE,
                            CommonUtils.getBranchAccount(pep.getDebitAccount()), CommonUtils.getBranchAccount(ps.getAccnofcubs()));

                    if (executePaymentTransactionInternal_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                        refCub = executePaymentTransactionInternal_out_Type.getTransactionInfo().getCoreRefNum();
                    } else {
                        refCub = null;
                    }

                }
                //BCN edit (S)   
                logger.info("***- LOG THANH TOAN MB (FUNCTION transferPaybill): Ma Khach Hang: " + billpaid.getCustomercode() + " -- So tham chieu trich tien REFCUBS= " + refCub + " -- From Account :" + from_custacc.getCustAcNo());
                if (refCub
                        == null || refCub.equalsIgnoreCase(
                                "")) {
                    return "";
                } else {
                    billpaid.setIdbillpaid(idbillPaid);
                    billpaid.setRefFcubs(refCub);
                    Helper.getDBI().updatePaybillBillPaid(billpaid);
                    pep.setRefBillpaid(refCub);
                    return refCub;
                }
            } else if (transfer_type.equalsIgnoreCase("recieve")) {
                //add product
                //refCub = fc.transferFCUBS(ps.getAccnofcubs(), pep.getDebitAccount(), billAmount, msgback);
                //refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, useridMobile, ps.getAccnofcubs(), pep.getDebitAccount(), billAmount, msgback);
                if (pep.getIdpartner().equals("BCN")) {
                    String idschool = pep.getRespay().getResponse().getTtpt().getMatruong();
                    String accountTo = Helper.getDBI().getAccountProvider(idschool);
                    refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, useridMobile, accountTo, pep.getDebitAccount(), billAmount, msgback);
                } else if (pep.getIdpartner().equals("EVNHN")) {
                    String partnerDetailId = pep.getCustomerCode().substring(0, 4);
                    String accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, useridMobile, accountTo, pep.getDebitAccount(), billAmount, msgback);
                } else if (pep.getIdpartner().equals("EVNSPC")) {
                    String partnerDetailId = pep.getCustomerCode().substring(0, 6);
                    String accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, useridMobile, accountTo, pep.getDebitAccount(), billAmount, msgback);
                } else {
                    refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, useridMobile, ps.getAccnofcubs(), pep.getDebitAccount(), billAmount, msgback);

                }

                Helper.writeLog(ExchangePaybill.class,
                        org.apache.log4j.Level.INFO, "***- LOG HOAN THANH TOAN MB (FUNCTION transferPaybill): Ma Khach hang: " + billpaid.getCustomercode() + " --  So tham chieu hoan tien  REFCUBS= " + refCub + " --  To Account :" + from_custacc.getCustAcNo());
                return refCub;
            }
            return null;

        } catch (Exception ex) {
            //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("***- ERROR MB (FUNCTION transferPaybill): " + ex.getMessage());

            return "";
        }
    }

    /*
     * History:
     * - 28/03/2014: Bo sung dieu kien kiem tra thau chi.
     */
    private PaymentResultEnum checkAmountToPaybill(String AmountBill, VwCustAccount custacc) {

        BigDecimal billAmount = new BigDecimal(AmountBill);
        // 2.1 Neu khong tra ve thong bao so du TK khong du de thuc hien GD
        if (billAmount.floatValue() <= 0) {
            return PaymentResultEnum.BILL_PAID;
        }

        if (custacc.getAccountClass().substring(0, 3).equals("CAI") || custacc.getAccountClass().substring(0, 3).equals("CAC")) {
            if (custacc.getAcyAvlBal().compareTo(billAmount) == -1) // 0:=,1: 1>2; -1:1<2
            {
                return PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY;
            }
        }

        return PaymentResultEnum.OK;
    }

    /**
     *
     * @param customername
     * @return
     * @throws Exception
     */
    public ArrayList<BillDetail> convertCustomerNameToBill(String customername) throws Exception {
        ArrayList<BillDetail> billList = new ArrayList<BillDetail>();
        String[] tempStr = customername.split("@");
        if (tempStr.length > 1) {
            String strBills = tempStr[4];
            for (String s : strBills.split("#")) {
                BillDetail billDetail = new BillDetail();
                String[] strBill = s.split("%");
                billDetail.setBillId(strBill[0]);
                billDetail.setMonth(strBill[1]);
                billDetail.setMoneyAmount(new BigDecimal(strBill[2]));
                //totalAmountOfBills = totalAmountOfBills.add(bill.getMoneyAmount());   

                billList.add(billDetail);
            }
        }
        return billList;
    }

    /**
     *
     * @param pe
     * @throws Exception
     */
    public void convertBillToCustomerName(BillPaymentRq pe) throws Exception {
        String newCustomerName = pe.getCustomerCode() + "@";
        if (pe.getIsPrepaid().equals(BILL_ISPREPAID)) {
            newCustomerName += "@true";
        } else {
            newCustomerName += "@false";
        }
        newCustomerName += "@" + pe.getPaymentRule();
        String strBills = "@" + "";
        if (pe.getIsPrepaid().equals(BILL_ISPREPAID) || pe.getPaymentRule().equals(BILL_PAYMENTRULE_ORDER)
                || pe.getPaymentRule().equals(BILL_PAYMENTRULE_ANY)) {

            BillInfo[] bills = pe.getBills();
            int cnt = 0;
            msgSendCore = "";
            for (BillInfo b : bills) {
                cnt++;
                strBills = strBills + b.getBillId() + "%";
                //Month Bill
                strBills = strBills + b.getMonth() + "%";
                //Amount                
                strBills = strBills + String.valueOf(b.getMoneyAmount().intValue()) + "%";
                strBills = strBills + b.getCustomerId() + "%";
                //BIDIWACO
                if (b.getBillNo() != null) {
                    strBills = strBills + b.getBillNo();
                }
                //totalAmountOfBills = totalAmountOfBills.add(b.getMoneyAmount());
                strBills = strBills + "#";
                /*
                LocalDateTime datePaid;
                if (b.getMonth() != null) {
                    datePaid = LocalDateTime.parse(b.getMonth());
                    msgSendCore = msgSendCore + datePaid.getMonthValue() + "/" + datePaid.getYear();
                    if (cnt < bills.length) {
                        msgSendCore = msgSendCore + "-";
                    }
                } */
                if ("BIDIWACO".equals(pe.getIdprovider())) { // cho doi tac cap nuoc
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM");
                    SimpleDateFormat sdfM = new SimpleDateFormat("MM");
                    SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
                    Date datePaid = formatter.parse(b.getMonth());
                    if (b.getMonth() != null) {
                        datePaid = formatter.parse(b.getMonth());
                        msgSendCore = msgSendCore + sdfM.format(datePaid) + "/" + sdfY.format(datePaid);
                        if (cnt < bills.length) {
                            msgSendCore = msgSendCore + "-";
                        }
                    }
                }

            }

        } else if (pe.getPaymentRule().equals(BILL_PAYMENTRULE_AMOUNT)) {
            BillInfo[] bills = pe.getBills();
            for (BillInfo b : bills) {
                strBills = strBills + b.getBillId() + "%";
                //Month Bill
                strBills = strBills + b.getMonth() + "%";
                //Amount                
                strBills = strBills + String.valueOf(pe.getPayAmt()) + "%";
                strBills = strBills + b.getCustomerId() + "%";
                //totalAmountOfBills = totalAmountOfBills.add(b.getMoneyAmount());
                strBills = strBills + "#";
            }

        }
        newCustomerName += strBills;
        pe.setCustomerNameBill(newCustomerName);
        //
    }

    private static void checkAmount(BillPaymentRq pepe) {
        //        paybill.setResult(Message.MobileResultEnum.OK.getValue());
//        paybill.getPbp().setIdpartner(respay.getResponse().getIdpartner());
//        paybill.getPbp().setDataxml(xml);

        if (pepe.getIdservicetype().equals("DIEN")) {
            if (pepe.getRespay().getResponse().getElectric() == null) {
                pepe.setResult(pepe.getRespay().getResult());//chuong
            }// else if (pepe.getRespay().getResponse().getElectric().getAmount().equals("0")) {
            else if (Integer.parseInt(pepe.getRespay().getResponse().getElectric().getAmount()) <= 0) {
                pepe.setBillAmt("0");
                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
            } else {
                pepe.setBillAmt(pepe.getRespay().getResponse().getElectric().getAmount());
            }
        } else if (pepe.getIdservicetype().equals("VEMAYBAY") && !pepe.getIdpartner().equals(IIBConstants.PARTNER_BANKNET)) {
            //Helper.writeLogError(MobileController.class,String.format("[ADD LOG KIMNCM - VE MAY BAY 1 -%1$s]", ToStringBuilder.reflectionToString(pepe.getRespay().getResponse())));
            if (pepe.getRespay().getResponse().getAirline() == null) {
                pepe.setResult(pepe.getRespay().getResult());//chuong  
            } else if (Integer.parseInt(pepe.getRespay().getResponse().getAirline().getAmount()) <= 0) {
                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
            } else if (pepe.getRespay().getResponse().getAirline().getStatus() != null && pepe.getRespay().getResponse().getAirline().getStatus().equals("1")) {
                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
            } else {
                pepe.setBillAmt(pepe.getRespay().getResponse().getAirline().getAmount());
            }

        } else if (pepe.getIdservicetype().equals("VNTOPUP")) {
            if (pepe.getRespay().getResponse().getVntopup() == null) {
                pepe.setResult(pepe.getRespay().getResult());//chuong
            } else if (Integer.parseInt(pepe.getRespay().getResponse().getVntopup().getAmount()) <= 0) {
                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
            } else {
                pepe.setBillAmt(pepe.getRespay().getResponse().getVntopup().getAmount());
            }

        } else {
            if (pepe.getRespay().getResponse().getBilling() == null) {
                //pepe.setResult(Message.PaymentResultEnum.BILL_PAID.getValue());
                pepe.setResult(pepe.getRespay().getResult());//chuong
            } else if (Integer.parseInt(pepe.getRespay().getResponse().getBilling().getAmount()) <= 0) {
                //[Change: case Prudential]
                //[Date & Who: Update 22/05/2014 by HieuDT]
                //[Description: Truong hop Prudential, luc nao cung tra ve Bill Amount = 0
                //              KH se thanh toan trong range cho ma he thong tra ve]
                if (pepe.getIdprovider().equals(Configuration.getProperty("provider.PF"))) {
                    pepe.setBillAmt(pepe.getRespay().getResponse().getBilling().getAmount());
                } else {
                    if (pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())) {
                        pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
                    }
                }
            } else {
                pepe.setBillAmt(pepe.getRespay().getResponse().getBilling().getAmount());
            }
        }
    }

    /**
     *
     * @param custname
     * @param idprovider
     * @return
     */
    public static boolean isFlgPrepaid(String custname, String idprovider) {
        String[] strTempArr = custname.split("@");
        //if (strTempArr.length > 1) {
        if (strTempArr.length >= 3) {
            if (strTempArr[2].equalsIgnoreCase("true")) {
                return true;
            } else if (idprovider.equals(Configuration.getProperty("provider.ACS"))
                    || idprovider.equals(Configuration.getProperty("provider.SCTV6"))
                    || idprovider.equals(Configuration.getProperty("provider.PF"))
                    || idprovider.equals("EVNHN")
                    || idprovider.equals("BIDIWACO")
                    || idprovider.equals("DNPWATER")
                    || idprovider.equals("EVNHCM")
                    || idprovider.equals("EVNSPC")) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param userId
     * @param sOTP
     * @return
     */
    public boolean verifyToken(String userId, String sOTP) {

        return true;
    }

    /**
     *
     * @return
     */
    public String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }

    /*
     private String getResultMB(String result) {
     return MobileResultEnum.get(result);
     }
     * */
    /**
     *
     * @param pre
     * @return
     */
    public static String getPaymentMsgEnum(PaymentMsgEnum pre) {
        switch (pre) {
            case THANHTOAN_BILL:
                return "THANH TOAN HOA DON MA KH %1$s";
            case THANHTOAN_VMB:
                return "THANH TOAN VE MAY BAY/VE TAU MA KH %1$s";
            case THANHTOAN_VNTOPUP:
                return "NAP TIEN CHO SO DIEN THOAI %1$s";
            case HOAN_THANHTOAN_BILL:
                return "HOAN TIEN DO THANH TOAN KHONG THANH CONG";
            case HOAN_THANHTOAN_VMB:
                return "HOAN TIEN DO THANH TOAN KHONG THANH CONG";
            case HOAN_THANHTOAN_VNTOPUP:
                return "HOAN TIEN DO THANH TOAN KHONG THANH CONG";
            default:
                return "";
        }
    }

    /**
     *
     * @param Customercode
     * @return
     */
    public String setTOPUPPartner(String Customercode) {
        //Lay thong tin nha mang
        String telname = get_telename(Customercode);
        String idProvider;
        if (telname == null || telname.equalsIgnoreCase("")) {
            return null;
        }

        if (telname.equalsIgnoreCase("Viettel")) {
            idProvider = Configuration.getProperty("ws.ebank.topup.viettel");

        } else if (telname.equalsIgnoreCase("Mobifone")) {
            idProvider = Configuration.getProperty("ws.ebank.topup.mobifone");
        } else if (telname.equalsIgnoreCase("Vinaphone")) {
            idProvider = Configuration.getProperty("ws.ebank.topup.vinaphone");
        } else if (telname.equalsIgnoreCase("G Mobile")) {
            idProvider = Configuration.getProperty("ws.ebank.topup.g_mobile");
        } else if (telname.equalsIgnoreCase("Vietnammobile")) {
            idProvider = Configuration.getProperty("ws.ebank.topup.vietnammobile");
        } else if (telname.equalsIgnoreCase("Sfone")) {
            idProvider = Configuration.getProperty("ws.ebank.topup.sfone");
        } else if (telname.equalsIgnoreCase("ITelecom")) {
            idProvider = Configuration.getProperty("ws.ebank.topup.ITelecom");
        } else {
            idProvider = "";
        }
        return idProvider;
    }

    /**
     *
     * @param phonenum
     * @return
     */
    public String get_telename(String phonenum) {
        try {
            if (hm_tetefirstnums.isEmpty()) {
                hm_tetefirstnums = Helper.getDBI().getlist_telefirstnumber();
            }
            if (phonenum.length() >= 9) {
                String firstnum1 = phonenum.substring(0, 3);
                String firstnum2 = phonenum.substring(0, 4);
                if (hm_tetefirstnums.containsKey(firstnum1)) {
                    System.out.println(firstnum1 + " la " + hm_tetefirstnums.get(firstnum1));
                    return hm_tetefirstnums.get(firstnum1);
                } else if (hm_tetefirstnums.containsKey(firstnum2)) {
                    System.out.println(firstnum2 + " la " + hm_tetefirstnums.get(firstnum2));
                    return hm_tetefirstnums.get(firstnum2);
                }
            } else {
                return "";
            }

            return "";

        } catch (RemoteException ex) {
            //Logger.getLogger(ExchangePaybill.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("***- ERROR MB (FUNCTION get_telename): " + ex.getMessage());

            return null;
        }
    }

    //change format fate to yyyyMMdd
    /**
     *
     * @param oldDate
     * @return
     */
    public String changeFormatDate(String oldDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            DateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
            Date date = formatter.parse(oldDate);
            return formatter1.format(date);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return oldDate;
        }
    }

    //change format fate to yyyyMMdd
    /**
     *
     * @param oldDate
     * @return
     */
    public String changeFormatTime(String oldDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            DateFormat formatter1 = new SimpleDateFormat("hh:mm:ss");
            Date date = formatter.parse(oldDate);
            return formatter1.format(date);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return oldDate;
        }
    }

    /**
     *
     * @param oldDate
     * @return
     */
    public String changeFormatDate2(String oldDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            DateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
            Date date = formatter.parse(oldDate);
            return formatter1.format(date);
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return oldDate;
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String ExchangeRate(String xml) {
        try {
            ExchangeRateRp response = new ExchangeRateRp();

            List exchangeRateList = Helper.getDBI().getExchangeRate();
            if (exchangeRateList == null || exchangeRateList.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_RATE));
            } else {
                ExchangeRateDetail[] listExchangeRate = new ExchangeRateDetail[exchangeRateList.size()];
                for (int i = 0; i < exchangeRateList.size(); i++) {
                    listExchangeRate[i] = new ExchangeRateDetail();
                    BeanUtils.copyProperties(listExchangeRate[i], exchangeRateList.get(i));
                }
                if (listExchangeRate[0] != null) {
                    response.setDateUpdate(listExchangeRate[0].getOrdernumber());
                    response.setTimeUpdate(changeFormatTime(listExchangeRate[0].getNote()));
                }
                response.setListExchangeRate(listExchangeRate);
            }

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String GoldRate(String xml) {
        try {
            GoldRateRp response = new GoldRateRp();

            List goldRateList = Helper.getDBI().getGoldRate();
            if (goldRateList == null || goldRateList.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_RATE));
            } else {
                GoldRateDetail[] listGoldRate = new GoldRateDetail[goldRateList.size()];
                for (int i = 0; i < goldRateList.size(); i++) {
                    listGoldRate[i] = new GoldRateDetail();
                    BeanUtils.copyProperties(listGoldRate[i], goldRateList.get(i));
                }
                if (listGoldRate[0] != null) {
                    response.setDateUpdate(listGoldRate[0].getOrdernumber());
                    response.setTimeUpdate(changeFormatTime(listGoldRate[0].getNote()));
                }
                response.setListGoldRate(listGoldRate);
            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String
            InterestRateOLD(String xml) {
        try {
            InterestRateRq req = (InterestRateRq) Xml.unMarshaller(InterestRateRq.class,
                    xml);
            InterestRateRp response = new InterestRateRp();
            List interestRateList = null;

            if (req.getCcy()
                    == null || req.getCcy().isEmpty()) {
                interestRateList = Helper.getDBI().getInterestRate("VND");
            } else {
                interestRateList = Helper.getDBI().getInterestRate(req.getCcy());
            }
            if (interestRateList
                    == null || interestRateList.size()
                    == 0) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_RATE));
            } else {
                InterestRateDetail[] listInterestRate = new InterestRateDetail[interestRateList.size()];
                if (req.getLanguage() == null || req.getLanguage().isEmpty() || req.getLanguage().equals("VN")) {
                    for (int i = 0; i < interestRateList.size(); i++) {
                        listInterestRate[i] = new InterestRateDetail();
                        BeanUtils.copyProperties(listInterestRate[i], interestRateList.get(i));
                    }
                } else if (req.getLanguage().equals("EN")) {
                    for (int i = 0; i < interestRateList.size(); i++) {
                        HashMap hm_Provider = (HashMap) interestRateList.get(i);
                        listInterestRate[i] = new InterestRateDetail();
                        listInterestRate[i].setProduct_description(hm_Provider.get("product_description").toString());
                        if (hm_Provider.get("term_eng") != null) {
                            listInterestRate[i].setTerm(hm_Provider.get("term_eng").toString());
                        }
                        listInterestRate[i].setInterest(hm_Provider.get("interest").toString());
                        listInterestRate[i].setCcy_code(hm_Provider.get("ccy_code").toString());
                        if (hm_Provider.get("tenortype") != null) {
                            listInterestRate[i].setTenortype(hm_Provider.get("tenortype").toString());
                        }
                    }
                }
                response.setListInterestRate(listInterestRate);
            }

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;

        }
    }

    private String getBranchFromAccount(String account) throws RemoteException {
        if (account == null) {
            return "";
        }
        if (account.length() < 3) {
            return "";
        }

        //return account.substring(0, 3);
        return CommonUtils.getBranchAccount(account);

    }

    /**
     *
     */
    public enum PaymentMsgEnum {

        /**
         *
         */
        THANHTOAN_BILL("1"),
        /**
         *
         */
        THANHTOAN_VMB("2"),
        /**
         *
         */
        THANHTOAN_VNTOPUP("3"),
        /**
         *
         */
        HOAN_THANHTOAN_BILL("-1"),
        /**
         *
         */
        HOAN_THANHTOAN_VMB("-2"),
        /**
         *
         */
        HOAN_THANHTOAN_VNTOPUP("-3");
        private String code;

        private PaymentMsgEnum(String c) {
            code = c;
        }

        /**
         *
         * @param value
         * @return
         */
        public static PaymentMsgEnum get(String value) {
            for (PaymentMsgEnum pce : PaymentMsgEnum.values()) {
                if (pce.getValue().equals(value)) {
                    return pce;
                }
            }
            return null;
        }

        /**
         *
         * @return
         */
        public String getValue() {
            return code;
        }
    }

    /**
     *
     */
    public enum TxnTypeEnum {

        /**
         *
         */
        OWN_ACC_TRANSFER("CN"),
        /**
         *
         */
        INTERNAL_ACC_TRANSFER("INACC"),
        /**
         *
         */
        INTERNAL_ID_TRANSFER("INID"),
        /**
         *
         */
        DOMESTIC_ACC_TRANSFER("OUTACC"),
        /**
         *
         */
        DOMESTIC_ID_TRANSFER("OUTID"),
        /**
         *
         */
        SML_CARD_TRANSFER("SMLCARD"),
        /**
         *
         */
        SML_ACC_TRANSFER("SMLACC");
        private String code;

        private TxnTypeEnum(String c) {
            code = c;
        }

        /**
         *
         * @param value
         * @return
         */
        public static TxnTypeEnum get(String value) {
            for (TxnTypeEnum pce : TxnTypeEnum.values()) {
                if (pce.getValue().equals(value)) {
                    return pce;
                }
            }
            return null;
        }

        /**
         *
         * @return
         */
        public String getValue() {
            return code;
        }
    }

    /*
     * Get all accounts of CIF
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String GetTemplateAndBeneFromEb(String xml) {
        try {
            GetTemplateAndBeneFromEbRp response = new GetTemplateAndBeneFromEbRp();
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            logger.error(e);
            return null;
        }

        //not used
        /*
        try {
           
            GetTemplateAndBeneFromEbRq req = (GetTemplateAndBeneFromEbRq) Xml.unMarshaller(GetTemplateAndBeneFromEbRq.class, xml);
            GetTemplateAndBeneFromEbRp response = new GetTemplateAndBeneFromEbRp();

            String custno = req.getCifNo();
            response.setCifNo(custno);
            // danh sach nguoi thu huong
            List listBeneName = Helper.getDBI().getBeneficiaryFromFCDB(custno);
            if (listBeneName != null && !listBeneName.isEmpty()) {
                BeneName[] beneList = new BeneName[listBeneName.size()];
                for (int i = 0; i < listBeneName.size(); i++) {
                    beneList[i] = new BeneName();
                    BeanUtils.copyProperties(beneList[i], listBeneName.get(i));
                }
                response.setListBeneName(beneList);
            }
            //danh sach mau giao dich
            List listTemplate = Helper.getDBI().GetTemplateFromFCDB(custno);
            if (listTemplate != null && !listTemplate.isEmpty()) {
                Template[] templateList = new Template[listTemplate.size()];
                for (int i = 0; i < listTemplate.size(); i++) {
                    templateList[i] = new Template();
                    BeanUtils.copyProperties(templateList[i], listTemplate.get(i));
                }
                response.setListTemplate(templateList);
            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
           
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        } */
    }

    /**
     *
     * @param pepe
     * @param description
     * @throws Exception
     */
    public static void insPblLog(BillPaymentRq pepe, String description) throws Exception {
        try {
            PblLog pl = new PblLog();
            pl.setChannel("03");
            pl.setIdpartner(pepe.getIdpartner());
            pl.setIdservicetype(pepe.getIdservicetype());
            pl.setIdprovider(pepe.getIdprovider());
            pl.setResult(pepe.getResult());
            pl.setInsdate(new Date());
            pl.setDescription(description);
            List mbMobile = Helper.getDBI().getCustomerFromMobile(pepe.getUserName());

            if (mbMobile == null || mbMobile.isEmpty()) {
                logger.error("***- ERROR MB (FUNCTION transferPaybill): " + "not get customerid from DB VNINFO ");
            } else {
                HashMap hm_MB = (HashMap) mbMobile.get(0);
                pl.setIdmarker(hm_MB.get("cus_id").toString());
            }

            String msg = String.format("[idbillpaid-%1$s]-[idpartner-%2$s]-[idservicetype-%3$s]-[idprovider-%4$s]"
                    + "-[customercode-%5$s]-[idmaker-%6$s]-[cust_acc_no-%7$s]",
                    pepe.getRefBillpaid(), pepe.getIdpartner(), pepe.getIdservicetype(), pepe.getIdprovider(),
                    pepe.getCustomerCode(), pl.getIdmarker(), pepe.getDebitAccount());
            pl.setMsglog(msg);

            Helper.getDBI().insPblLog(pl);
        } catch (Exception e) {
            logger.error("***- ERROR MB (FUNCTION insPblLog): " + e.getMessage());
        }
    }

    /*
     * Get all accounts of CIF
     * @kimncm
     */
    /**
     *
     * @param xml
     * @return
     */
    public String
            GetRedemptionAzListNEw(String xml) {
        try {
            GetRedemptionAzListRq req = (GetRedemptionAzListRq) Xml.unMarshaller(GetRedemptionAzListRq.class,
                    xml);
            GetRedemptionAzListRp response = new GetRedemptionAzListRp();

            IIBDepositAccountService iibAccService = new IIBDepositAccountService();
            SelectDepositAccountFromCIF_out_Type selectDepositAccountFromCIF_out_Type = iibAccService.selectDepositAccountFromCIFRestSimple(Configuration.getIIBContext(), req.getCifNo(), IIB_CHANNEL_MOBILE);
            if (!selectDepositAccountFromCIF_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            }

            List<AccountInfoType> tdAccList = selectDepositAccountFromCIF_out_Type.getAccountInfo();
            if (arr_redemAccountclassList == null) {
                arr_redemAccountclassList = Arrays.asList(Helper.getDBI().getListAccountClassRedemEbank());
            }
            ArrayList<AccountAzDetail> accountDetailList = new ArrayList<AccountAzDetail>();
            for (AccountInfoType accountinfodetail : tdAccList) {
                if (arr_redemAccountclassList.contains(accountinfodetail.getAccountClassCode())) {
                    AccountAzDetail detail = new AccountAzDetail();
                    detail.setPrdname(accountinfodetail.getAccountClassName());
                    detail.setAccountccy(accountinfodetail.getAccountCurrency());
                    detail.setAccountbalanceavaliable(accountinfodetail.getAccountBalanceAvailable().toPlainString());
                    detail.setAccountbalance(accountinfodetail.getAccountBalance().toPlainString());
                    detail.setAcctopendt(changeFormatDate(accountinfodetail.getAccountOpenDate()));
                    detail.setMaturitydate(changeFormatDate(accountinfodetail.getAccountMaturityDate()));
                    detail.setRate(accountinfodetail.getAccountInterestRate());
                    accountDetailList.add(detail);
                }

            }
            response.setListAzAccount((AccountAzDetail[]) accountDetailList.toArray(new AccountAzDetail[accountDetailList.size()]));

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * Get all accounts of CIF
     * @kimncm
     */
    /**
     *
     * @param xml
     * @return
     */
    public String
            GetRedemptionAzList(String xml) {
        try {
            GetRedemptionAzListRq req = (GetRedemptionAzListRq) Xml.unMarshaller(GetRedemptionAzListRq.class,
                    xml);
            GetRedemptionAzListRp response = new GetRedemptionAzListRp();

            String custno = req.getCifNo();

            List listAccount = Helper.getDBI().GetRedemptionAzListByCustNo(custno);
            if (listAccount == null || listAccount.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            }

            AccountAzDetail[] accountDetails = new AccountAzDetail[listAccount.size()];
            for (int i = 0; i < listAccount.size(); i++) {
                accountDetails[i] = new AccountAzDetail();
                BeanUtils.copyProperties(accountDetails[i], listAccount.get(i));
                accountDetails[i].setTerm(accountDetails[i].getTerm().concat(accountDetails[i].getTermtype()));
                accountDetails[i].setAcctopendt(changeFormatDate(accountDetails[i].getAcctopendt()));
                accountDetails[i].setMaturitydate(changeFormatDate(accountDetails[i].getMaturitydate()));

                /*
                 if (req.getLanguage() == null || req.getLanguage().isEmpty() || req.getLanguage().equals("VN")) {
                 response.setTerm(response.getTerm().concat(hm_Td.get("termtype").toString()));
                 } else {
                 String termtype = accountDetails[i].getTermtype();
                 if (termtype.equals(" tháng")) {
                 accountDetails[i].setTerm(accountDetails[i].getTerm().concat(" months"));
                 } else if (termtype.equals(" ngày")) {
                 accountDetails[i].setTerm(accountDetails[i].getTerm().concat(" days"));
                 } else if (termtype.equals(" năm")) {
                 accountDetails[i].setTerm(accountDetails[i].getTerm().concat(" years"));
                 }
                 }*/
            }

            response.setListAzAccount(accountDetails);
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param xml
     * @return
     *
     */
    public String
            AddFeedback(String xml) {
        try {
            AddFeedbackRq req = (AddFeedbackRq) Xml.unMarshaller(AddFeedbackRq.class,
                    xml);
            AddFeedbackRp response = new AddFeedbackRp();

            int result = Helper.getDBI().InsertFeedback(req.getSubject(), req.getContent(), "03", req.getUserName());
            response.setCifNo(req.getCifNo());
            if (result == -1) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_ADD_FEEDBACK));
            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param xml
     * @return
     *
     */
    public String
            GetStaff(String xml) {
        try {
            GetStaffRq req = (GetStaffRq) Xml.unMarshaller(GetStaffRq.class,
                    xml);
            GetStaffRp response = new GetStaffRp();
            response.setStaffCode(req.getStaffCode());
            String staffName = Helper.getDBI().GetStaffDetail(req.getStaffCode());
            if (Common.isEmpty(staffName)) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.STAFF_NOT_FOUND));
            }
            response.setStaffName(staffName);
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    private String revertTransferFCUBS(String fccRef, int timeout) {
        //Kiem tra so ref hop le
        if (fccRef == null) {
            return null;
        }

        Fcubs fc = new Fcubs();
        return fc.revertTransferFCUBS(fccRef, timeout);
    }

    private void updateBillStatusToDB(PblBillpaid billpaid, Character status) throws Exception {
        billpaid.setStatus(status);
        Helper.getDBI().updatePaybillBillPaid(billpaid);
    }

    /**
     *
     * @param xml
     * @return
     *
     */
    public String
            LoanRegister(String xml) {
        try {
            LoanRegisterRq req = (LoanRegisterRq) Xml.unMarshaller(LoanRegisterRq.class,
                    xml);
            LoanRegisterRp response = new LoanRegisterRp();

            EbIssuecard issueATM = new EbIssuecard();
            BeanUtils.copyProperties(issueATM, req);
            issueATM.setIschecked('N');
            //neu KH dang ky nhan the tai CN thi CN la don vi phat hanh the
            //nguoc lai lay don vi mo tk thanh toan lam don vi phat hanh the            
            issueATM.setIssuebranchcode(req.getBranchcode());
            VwMbCustomer customer = new VwMbCustomer();
            customer.setCustomerNo(req.getCifNo());
            issueATM.setCUSTOMERNO(customer);
            issueATM.setIdchanneluser(req.getUserName());
            issueATM.setRegistertype("LO");
            issueATM.setIdchannel(ID_CHANNEL_MOBILE);
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Timestamp timestamp = Timestamp.valueOf(sdfDate.format(now));
            issueATM.setInsdate(timestamp);
            //issueATM.setInsdate(null);
            //int result = Helper.getDBI().insertEbIssuecard(issueATM);
            ProcedureCallDto rep = Helper.getDBI().insertEbIssuecard(issueATM);
            if (!rep.isSucess()) {

                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_ADD_LOANREGISTER));
            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * 
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String
            CheckIssueATMCard(String xml) {
        try {

            CheckIssueATMCardRq req = (CheckIssueATMCardRq) Xml.unMarshaller(CheckIssueATMCardRq.class,
                    xml);
            CheckIssueATMCardRp response = new CheckIssueATMCardRp();

            //-- set temp for test
            /*
             if (req.getCifNo().equals("0125190")) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
                //return Xml.Marshaller(response.getMBResponse(MobileResultEnum.REPIN_EXPIREDCARD));
             }*/
            //------ set temp for test
            int result = Helper.getDBI().checkIssueATMCard(req.getCifNo(), req.getSourceAccount(), req.getAccountNo(),
                    req.getRegisterType(), req.getIssueType(), req.getCardtype(), req.getTxnFee(), req.getTxnTax());
            String strresult = String.valueOf(result);
            //ATMISSUE_TYPE_NEW = "1", ATMISSUE_TYPE_REISSUE = "2", ATMISSUE_TYPE_EXT = "3", ATMISSUE_TYPE_SLAVE = "4";                
            if (strresult.equals("0")) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
            } else if (strresult.equals(MobileResultEnum.CANNOT_EXTEND_ATM.getValue())) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_EXTEND_ATM));
            } else if (strresult.equals("-2")) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY));
            } else if (strresult.equals(MobileResultEnum.CANNOT_ISSUE_SLAVEATM.getValue())) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_ISSUE_SLAVEATM));
            } else if (strresult.equals(MobileResultEnum.REISSUE_CONFIRM.getValue())) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.REISSUE_CONFIRM));
            } else if (strresult.equals(MobileResultEnum.ISSUEATM_SAMEATMTYPE_CONFIRM.getValue())) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.ISSUEATM_SAMEATMTYPE_CONFIRM));
            } else if (strresult.equals(MobileResultEnum.REPIN_EXPIREDCARD.getValue())) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.REPIN_EXPIREDCARD));
            } else if (strresult.equals(MobileResultEnum.NOTVALID_CARD_ISSUE.getValue())) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NOTVALID_CARD_ISSUE));
            } else {
                Helper.writeLogError(this.getClass(), "CheckIssueATMCard - error" + strresult);
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.ERROR_SYSTEM));
            }
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
    public String
            IssueATMCard(String xml) {
        try {

            IssueATMCardRq req = (IssueATMCardRq) Xml.unMarshaller(IssueATMCardRq.class,
                    xml);
            TxnCommitRp response = new TxnCommitRp();
            response.setAccountNo(req.getSourceaccount());
            response.setCifNo(req.getCifNo());

            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            }
            //insert to DB
            EbIssuecard issueATM = new EbIssuecard();
            issueATM.setIschecked('N');
            VwMbCustomer customer = new VwMbCustomer();
            customer.setCustomerNo(req.getCifNo());
            issueATM.setCUSTOMERNO(customer);
            issueATM.setIdchanneluser(req.getUserName());
            issueATM.setIdchannel(ID_CHANNEL_MOBILE);
            issueATM.setRegistertype("IA");

            if (req.getAccountno() != null && req.getAccountno().length() > 0) {
                List li = Helper.getDBI().getCardnoByAccountno(req.getAccountno());
                if (li == null || li.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }
                HashMap hm_acc = (HashMap) li.get(0);
                issueATM.setCardno(hm_acc.get("cardno").toString());
                issueATM.setCardaccountno(Long.valueOf(hm_acc.get("cardaccountnumber").toString()));
                //4: phát hành thẻ phụ
                //if (req.getIssuetype().equals(ATMISSUE_TYPE_SLAVE)) {
                req.setCardtype(hm_acc.get("cardtype").toString().trim());
                //}
            }
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Timestamp timestamp = Timestamp.valueOf(sdfDate.format(now));
            issueATM.setInsdate(timestamp);
            BeanUtils.copyProperties(issueATM, req);
            //neu KH dang ky nhan the tai CN thi CN la don vi phat hanh the
            //nguoc lai lay don vi mo tk thanh toan lam don vi phat hanh the
            if (issueATM.getBranchcode() != null && issueATM.getBranchcode().length() > 0) {
                issueATM.setIssuebranchcode(issueATM.getBranchcode());
            } else {
                //issueATM.setIssuebranchcode(issueATM.getSourceaccount().substring(0, 3));
                issueATM.setIssuebranchcode(CommonUtils.getBranchAccount(issueATM.getSourceaccount()));
            }

            CardType card = new CardType();
            card.setCardtype(req.getCardtype());
            issueATM.setCARDTYPE(card);

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
                issueATM.setIschecked('H');
                //int result = Helper.getDBI().insertEbIssuecard(issueATM);
                // if (result == -1) {
                ProcedureCallDto rep = Helper.getDBI().insertEbIssuecard(issueATM);
                if (!rep.isSucess()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_ADD_ATMREGISTER));
                }
                issueATM.setId(Integer.parseInt(rep.getOther()));

                //GET FEE
                Fcubs fc = new Fcubs();

                String fccxref = fc.transferFCUBSWithProductCharge(productMobileCard, getBranchFromAccount(req.getSourceaccount()), req.getSourceaccount(),
                        issueATM.getIssuebranchcode(), GLPaymentCard, BigDecimal.ZERO,
                        fee, tax,
                        "THU PHÍ PHÁT HÀNH, GIA HẠN, CẤP LẠI THẺ TRÊN MB" // FOR LIVE
                //"THU PHI PHAT HANH, GIA HAN, CAP LAI THE/PIN TREN MB" //FOR TEST
                );

                if (fccxref == null) {
                    issueATM.setIschecked('D');
                    Helper.getDBI().updateEbIssuecard(issueATM);
                    return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                } else {
                    response.setTxnID(fccxref);
                    response.setTxnCommitTime(getTime());
                    TxnCommitRp resp = (TxnCommitRp) response.getMBFinResponse(MobileResultEnum.OK);

                    issueATM.setAccountbalance(Long.valueOf(resp.getNumbalance()));
                    issueATM.setRefFcubs(fccxref);
                    issueATM.setIschecked('N');
                    /*
                    int result = Helper.getDBI().insertEbIssuecard(issueATM);
                    if (result == -1) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_ADD_ATMREGISTER));
                    }*/
                    Helper.getDBI().updateEbIssuecard(issueATM);
                    return Xml.Marshaller(resp);
                }
            } else {
                //int result = Helper.getDBI().insertEbIssuecard(issueATM);
                //if (result == -1) {
                ProcedureCallDto rep = Helper.getDBI().insertEbIssuecard(issueATM);
                if (!rep.isSucess()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_ADD_ATMREGISTER));
                }
            }
            return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));
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
    public String CreditCardType(String xml) {
        try {
            String AccountGroupCode = "MC";
            CreditCardTypeRq req;
            String cifno = null;
            String custtype = null;

            if (xml.contains("anyType")) {
                req = (CreditCardTypeRq) Xml.unMarshaller(CreditCardTypeRq.class,
                        xml);
                if (req == null) {
                    req = new CreditCardTypeRq();

                }
            } else {
                req = (CreditCardTypeRq) Xml.unMarshaller(CreditCardTypeRq.class,
                        xml);
                AccountGroupCode = req.getAccountGroupCode();
            }

            cifno = req.getCifNo();
            Helper
                    .writeLogError(MobileController.class,
                            "CreditCardType - cifno: " + cifno);
            //get Type KH
            IIBCustomerService iibCustomerService = new IIBCustomerService();
            RetrieveCustomerRefDataMgmt_out_Type retrieveCustomerRefDataMgmt = iibCustomerService.retrieveCustomerRefDataMgmtSimple(Configuration.getIIBContext(), cifno, IIBConstants.CHANNEL_MOBILE);
            if (retrieveCustomerRefDataMgmt.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                CIFDataType cifdatatype = retrieveCustomerRefDataMgmt.getCIFInfo();
                custtype = cifdatatype.getCustomerType();
                Helper
                        .writeLogError(MobileController.class,
                                "CreditCardType - custtype: " + custtype);
            };

            CreditCardTypeRp response = new CreditCardTypeRp();
            /*
            if (req.getAccountGroupCode() != null && req.getAccountGroupCode().length() > 0) {
                AccountGroupCode = req.getAccountGroupCode();
            }
            //set for new version
            
            if (xml != null && xml.length() > 0) {
                CreditCardTypeRq request = (CreditCardTypeRq) Xml.unMarshaller(CreditCardTypeRq.class, xml);
                if (request.getAccountGroupCode() != null && request.getAccountGroupCode().length() > 0) {
                    AccountGroupCode = request.getAccountGroupCode();
                }
            }*/

            List creditCardTypeList = Helper.getDBI().getCardType(AccountGroupCode);
            if (creditCardTypeList == null || creditCardTypeList.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_CARDTYPE));
            } else {
                ArrayList<CreditCardType> listCreditCardType = new ArrayList<CreditCardType>();
                for (int i = 0; i < creditCardTypeList.size(); i++) {
                    CreditCardType detail = new CreditCardType();
                    BeanUtils.copyProperties(detail, creditCardTypeList.get(i));
                    if (custtype != null) {
                        if (detail.getCusttype().equalsIgnoreCase(custtype)) {
                            listCreditCardType.add(detail);
                        }
                    } else {
                        listCreditCardType.add(detail);
                    }
                }
                response.setListCreditCardType((CreditCardType[]) listCreditCardType.toArray(new CreditCardType[listCreditCardType.size()]));

            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String
            GetCardList(String xml) {
        /*
         try {
         GetCardListRq req = (GetCardListRq) Xml.unMarshaller(GetCardListRq.class, xml);
         GetCardListRp response = new GetCardListRp();

         String custno = req.getCifNo();
         String cardtype = "";
         if (req.getCardType().equals("1")) {
         cardtype = "'LC'";
         } else if (req.getCardType().equals("2")) {
         cardtype = "'MC','VS'";
         }

         List listCard = Helper.getDBI().getCardList(custno, cardtype);
         if (listCard == null || listCard.isEmpty()) {
         return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_CARDACCOUNT));
         }

         CardDetail[] cardDetails = new CardDetail[listCard.size()];
         for (int i = 0; i < listCard.size(); i++) {
         cardDetails[i] = new CardDetail();
         BeanUtils.copyProperties(cardDetails[i], listCard.get(i));
         }

         response.setListCard(cardDetails);
         return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
         } catch (Exception e) {
         Helper.writeLogError(this.getClass(), e);
         return null;
         }*/
        try {
            GetCardListRq req = (GetCardListRq) Xml.unMarshaller(GetCardListRq.class,
                    xml);
            GetCardListRp response = new GetCardListRp();
            String cardtype = null;
            String groupcode = null;

            if (req.getCardType().equals("1")) {
                cardtype = "'LC'";
            } else if (req.getCardType().equals("2")) {
                cardtype = "'MC','VS'";
            }
            //List listCard = Helper.getDBI().getCardList(req.getCifNo(), cardtype,groupcode);
            List listCard = Helper.getDBI().getCardList(req.getCifNo(), cardtype, groupcode);
            if (listCard == null || listCard.isEmpty()) {
                //return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            }

            CardDetail[] cardDetails = new CardDetail[listCard.size()];
            for (int i = 0; i < listCard.size(); i++) {
                cardDetails[i] = new CardDetail();
                BeanUtils.copyProperties(cardDetails[i], listCard.get(i));
                if (cardDetails[i].getTinhtrangthe() != null && cardDetails[i].getTinhtrangthe().equals("The Tam Khoa")) {
                    cardDetails[i].setTinhtrangthe("L");
                } else {
                    cardDetails[i].setTinhtrangthe("O");
                }

                if (cardDetails[i].getBlockonline() != null && cardDetails[i].getBlockonline().equals("1")) {
                    cardDetails[i].setBlockonline("L");
                } else {
                    cardDetails[i].setBlockonline("O");
                }

            }

            response.setListCard(cardDetails);
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String
            ReissuePIN(String xml) {
        try {

            ReissuePINRq req = (ReissuePINRq) Xml.unMarshaller(ReissuePINRq.class,
                    xml);
            TxnCommitRp response = new TxnCommitRp();
            response.setAccountNo(req.getSourceaccount());
            response.setCifNo(req.getCifNo());
            response.setTxnType("REPIN");
            // if reissuePIN not get fee
            if (req.getAuthenType() != null && req.getBrn().equals("1")) {
                int isValidAuth = req.validateAuth();
                if (isValidAuth != _SUCESSFUL) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
                }
            }
//
            //insert to DB
            EbIssuecard issueATM = new EbIssuecard();
            issueATM.setIschecked('N');
            issueATM.setIdchannel(ID_CHANNEL_MOBILE);
            issueATM.setRegistertype("IP");
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Timestamp timestamp = Timestamp.valueOf(sdfDate.format(now));
            issueATM.setInsdate(timestamp);
            //issueATM.setCustomerNo(req.getCifNo());
            VwMbCustomer customer = new VwMbCustomer();
            customer.setCustomerNo(req.getCifNo());
            issueATM.setCUSTOMERNO(customer);
            issueATM.setIdchanneluser(req.getUserName());
            BeanUtils.copyProperties(issueATM, req);
            if (!req.getAccountno().isEmpty()) {
                List li = Helper.getDBI().getCardnoByAccountno(req.getAccountno());
                if (li == null || li.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }
                HashMap hm_acc = (HashMap) li.get(0);
                issueATM.setCardno(hm_acc.get("cardno").toString());
                issueATM.setCardaccountno(Long.valueOf(hm_acc.get("cardaccountnumber").toString()));

                CardType card = new CardType();
                card.setCardtype(hm_acc.get("cardtype").toString().trim());
                issueATM.setCARDTYPE(card);

                //neu KH dang ky nhan the tai CN thi CN la don vi phat hanh the
                //nguoc lai lay don vi mo tk thanh toan lam don vi phat hanh the
                if (issueATM.getBranchcode() != null && issueATM.getBranchcode().length() > 0) {
                    issueATM.setIssuebranchcode(issueATM.getBranchcode());
                } else {
                    issueATM.setIssuebranchcode(hm_acc.get("branchcode").toString());
                }

            };
            /*
             if (result == -1) {
             return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_REISSUE_PIN));
             }*/
            //chi thu phi voi cap PIN cho ATM
            if (req.getBrn().equals("1")) {
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
                    //GET FEE
                    issueATM.setIschecked('H');
                    //int result = Helper.getDBI().insertEbIssuecard(issueATM);
                    //if (result == -1) {
                    ProcedureCallDto rep = Helper.getDBI().insertEbIssuecard(issueATM);
                    if (!rep.isSucess()) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_REISSUE_PIN));
                    }
                    issueATM.setId(Integer.parseInt(rep.getOther()));

                    Fcubs fc = new Fcubs();

                    String fccxref = fc.transferFCUBSWithProductCharge(productMobileCard, getBranchFromAccount(req.getSourceaccount()), req.getSourceaccount(),
                            issueATM.getIssuebranchcode(), GLPaymentCard, BigDecimal.ZERO,
                            fee, tax,
                            "THU PHÍ PHÁT HÀNH, GIA HẠN, CẤP LẠI THẺ/PIN TRÊN MB"); //for live
                    //"THU PHI PHAT HANH, GIA HAN, CAP LAI THE/PIN TREN MB");

                    if (fccxref == null) {
                        issueATM.setIschecked('D');
                        Helper.getDBI().updateEbIssuecard(issueATM);
                        return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                    } else {
                        response.setTxnID(fccxref);
                        response.setTxnCommitTime(getTime());
                        //update so ref
                        //issueATM.setId(result);
                        issueATM.setRefFcubs(fccxref);

                        TxnCommitRp resp = (TxnCommitRp) response.getMBFinResponse(MobileResultEnum.OK);
                        issueATM.setAccountbalance(Long.valueOf(resp.getNumbalance()));
                        issueATM.setIschecked('N');
                        Helper.getDBI().updateEbIssuecard(issueATM);
                        /*
                        int result = Helper.getDBI().insertEbIssuecard(issueATM);
                        if (result == -1) {
                            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_REISSUE_PIN));
                        }*/
                        return Xml.Marshaller(resp);
                    }
                } else {
                    //int result = Helper.getDBI().insertEbIssuecard(issueATM);
                    //if (result == -1) {
                    ProcedureCallDto rep = Helper.getDBI().insertEbIssuecard(issueATM);
                    if (!rep.isSucess()) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_REISSUE_PIN));
                    }
                }
            } else {
                //int result = Helper.getDBI().insertEbIssuecard(issueATM);
                //if (result == -1) {
                ProcedureCallDto rep = Helper.getDBI().insertEbIssuecard(issueATM);
                if (!rep.isSucess()) {

                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_REISSUE_PIN));
                }
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
            }
            return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));
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
    public String
            IssueCreditCard(String xml) {
        try {

            IssueCreditCardRq req = (IssueCreditCardRq) Xml.unMarshaller(IssueCreditCardRq.class,
                    xml);
            IssueCreditCardRp response = new IssueCreditCardRp();
            //insert to DB
            EbIssuecard issueMC = new EbIssuecard();
            issueMC.setRegistertype("IM");
            issueMC.setIschecked('N');
            issueMC.setIdchannel(ID_CHANNEL_MOBILE);
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Timestamp timestamp = Timestamp.valueOf(sdfDate.format(now));
            issueMC.setInsdate(timestamp);
            VwMbCustomer customer = new VwMbCustomer();
            customer.setCustomerNo(req.getCifNo());
            issueMC.setCUSTOMERNO(customer);
            issueMC.setIdchanneluser(req.getUserName());
            issueMC.setBranchcode(req.getBranchcode());
            issueMC.setIssuebranchcode(req.getBranchcode());
            CardType card = new CardType();
            card.setCardtype(req.getCardtype());
            //issueMC.setCardtype(req.getCardtype());
            issueMC.setCARDTYPE(card);
            issueMC.setLimitrequest(new BigDecimal(req.getLimitrequest()));
            issueMC.setGuaranteemode(req.getGuaranteemode());
            issueMC.setGuaranteemodeinfo(req.getGuaranteemodeinfo());
            issueMC.setIdchanneluser(req.getUserName());
            issueMC.setAddress(req.getAddress());

            //BeanUtils.copyProperties(issueMC, req);
            //int result = Helper.getDBI().insertEbIssuecard(issueMC);
            //if (result == -1) {
            ProcedureCallDto rep = Helper.getDBI().insertEbIssuecard(issueMC);
            if (!rep.isSucess()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_ADD_MCREGISTER));
            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * Get detail of account
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String
            CreditCardStatement(String xml) {
        try {
            CreditCardStatementRq req = (CreditCardStatementRq) Xml.unMarshaller(CreditCardStatementRq.class,
                    xml);
            CreditCardStatementRp response = new CreditCardStatementRp();
            //req.setAccountNo("3249257987D45137XXX");
            //req.setPeriod("201602");
            String period = req.getPeriod().trim();
            //add "0" for month 1 2 3 4
            if (period.length() < 6) {
                req.setPeriod(period.substring(0, 4).concat("0").concat(period.substring(4, period.length())));
            }

            List stmtedetail = Helper.getDBI().getMCStateDetail(req.getAccountNo(), req.getPeriod());
            if (stmtedetail == null || stmtedetail.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_STATEMENT));
            }
            logger.warn("getMCStateDetail done stmtedetail size:" + stmtedetail.size());

            BeanUtils.copyProperties(response, stmtedetail.get(0));
            response.setMatdatecreditcard(changeFormatDate(response.getMatdatecreditcard()));
            /*
            HashMap<?, ?> hm = (HashMap<?, ?>) stmtedetail.get(0);
            response.setAmtlimitpay(hm.get("amtlimitpay")==null?"":hm.get("amtlimitpay").toString());
            
            response.setAtmpayipp(hm.get("atmpayipp")==null?"":hm.get("atmpayipp").toString());
            
            response.setCardaccountno(hm.get("cardaccountno")==null?"":hm.get("cardaccountno").toString());
            
            response.setCardtype(hm.get("cardtype")==null?"":hm.get("cardtype").toString());
            
            response.setClosingbalance(hm.get("closingbalance")==null?"":hm.get("closingbalance").toString());
            
            response.setLimitcreditcard(hm.get("limitcreditcard")==null?"":hm.get("limitcreditcard").toString());
            
            response.setPeriod(hm.get("period")==null?"":hm.get("period").toString());
            
            response.setOpeningbalance(hm.get("openingbalance")==null?"":hm.get("openingbalance").toString());
            
            response.setStatementdate(hm.get("statementdate")==null?"":hm.get("statementdate").toString());
            
            response.setTotalpayamt(hm.get("totalamount")==null?"":hm.get("totalamount").toString());
            
            response.setMatdatecreditcard(changeFormatDate(hm.get("matdatecreditcard").toString()));
             */

            //SET FROM DATE
            String fromdate = req.getPeriod();

            if (response.getCardtype().equals("MC")) {
                fromdate = fromdate.concat("26");

            } else if (response.getCardtype().equals("VS")) {
                fromdate = fromdate.concat("16");
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(format.parse(fromdate));
            cal.add(Calendar.MONTH, -1);
            fromdate = format.format(cal.getTime());
            response.setStatementfromdate(fromdate);
            //add pdffile;
            logger.warn("getPdfFileByMonth");
            try {
                Object[] objData = Helper.getDBI().getPdfFileByMonth(response.getCardaccountno(), req.getPeriod());
                if (objData != null) {
                    logger.warn("getPdfFileByMonth not null");
                    byte[] fileData = (byte[]) objData[0];
                    response.setPdffile(fileData);
                }
            } catch (Exception ex) {
                logger.error("get getPdfFileByMonth has error:" + ex.getMessage());
            }

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String
            GetCCStatement(String xml) {
        try {

            GetCCStatementRq req = (GetCCStatementRq) Xml.unMarshaller(GetCCStatementRq.class,
                    xml);
            GetCCStatementRp response = new GetCCStatementRp();
            response.setAccountNo(req.getAccountNo());
            //req.setAccountNo("841E6B3B57ACED3BXXX");
            //req.setPeriod("201602");
            String period = req.getPeriod().trim();
            //add "0" for month 1 2 3 4
            if (period.length() < 6) {
                period = period.substring(0, 4).concat("0").concat(period.substring(4, period.length()));
                req.setPeriod(period);
            }
            /*  
            String year = String.valueOf(Integer.parseInt(period.substring(0, 4)));
            String month = String.valueOf(Integer.parseInt(period.substring(4,period.length())) - 1);
            
            if (month.length() == 1) {
                if (month.equals("0")) {
                    month = "12";
                    year = String.valueOf(Integer.parseInt(period.substring(0, 4)) - 1);
                } else {
                    month = "0".concat(month);
                }
            }
             
            String fromdate = year.concat(month);
            String todate = period;                        
                                            
            if (req.getCredittype() != null && req.getCredittype().length() > 0) {
                if (req.getCredittype().equals("MC")) {
                    fromdate = fromdate.concat("26");
                    todate = todate.concat("25");

                } else if (req.getCredittype().equals("VS")) {
                    fromdate = fromdate.concat("16");
                    todate = todate.concat("15");
                }
            } else {
                fromdate = fromdate.concat("26");
                todate = todate.concat("25");
            }
             */
            List transList = Helper.getDBI().getCCStatement(req.getAccountNo(), period, req.getSrno(), "300");
            if (transList == null || transList.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_STATEMENT));
            } else {
                MCTxnDetail[] txnList = new MCTxnDetail[transList.size()];
                for (int i = 0; i < transList.size(); i++) {
                    txnList[i] = new MCTxnDetail();
                    BeanUtils.copyProperties(txnList[i], transList.get(i));
                    // txnList[i].setTxndate(txnList[i].getTxndate().substring(0, 10));
                }
                response.setTxnList(txnList);
            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String
            RewardPoint(String xml) {
        try {
            RewardPointRq req = (RewardPointRq) Xml.unMarshaller(RewardPointRq.class,
                    xml);
            RewardPointRp response = new RewardPointRp();
            String[] point = Helper.getDBI().getPointMC(req.getAccountNo());
            if (point == null || point.length == 0) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.NO_CUSTOMER_EXISTS));
            }
            response.setAccountno(req.getAccountNo());
            response.setOpeningpoint(point[0]);
            response.setEarnedpoint(point[1]);
            response.setClosingpoint(point[2]);
            response.setPresentpoint(point[3]);

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String
            CheckBalanceBeforeFeeOLD(String xml) {

        try {

            CheckBalanceBeforeFeeRq req = (CheckBalanceBeforeFeeRq) Xml.unMarshaller(CheckBalanceBeforeFeeRq.class,
                    xml);
            CheckBalanceBeforeFeeRp response = new CheckBalanceBeforeFeeRp();

            response.setFromAccount(req.getFromAccount());

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

            ProcedureCallDto rep = Helper.getDBI().checkFeeMobile(req.getCifNo(), req.getFromAccount(), sumPay.toString());
            if (rep.isSucess()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
            } else {
                if (rep.getErrorStatus().equals(MobileResultEnum.EXISTS_OTHERACCOUNT_FORFEE.getValue())) {
                    response.setOtherAccount(rep.getOther());
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.EXISTS_OTHERACCOUNT_FORFEE));
                    // } else if (rep.getErrorStatus().equals(PaymentResultEnum.ACCNO_NOT_FOUND.getValue())) {
                    //     return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                } else if (rep.getErrorStatus().equals(MobileResultEnum.NO_EXISTS_OTHERACCOUNT_FORFEE.getValue())) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_OTHERACCOUNT_FORFEE));
                } else {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.ERROR_SYSTEM));
                }
            }
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
    public String
            CheckBalanceBeforeFee(String xml) {
        try {

            CheckBalanceBeforeFeeRq req = (CheckBalanceBeforeFeeRq) Xml.unMarshaller(CheckBalanceBeforeFeeRq.class,
                    xml);
            CheckBalanceBeforeFeeRp response = new CheckBalanceBeforeFeeRp();

            response.setFromAccount(req.getFromAccount());

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

            ProcedureCallDto rep = Helper.getDBI().checkFeeMobile(req.getCifNo(), req.getFromAccount(), sumPay.toString());
            if (rep.isSucess()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
            } else {
                if (rep.getErrorStatus().equals(MobileResultEnum.EXISTS_OTHERACCOUNT_FORFEE.getValue())) {
                    response.setOtherAccount(rep.getOther());
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.EXISTS_OTHERACCOUNT_FORFEE));
                    // } else if (rep.getErrorStatus().equals(PaymentResultEnum.ACCNO_NOT_FOUND.getValue())) {
                    //     return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                } else if (rep.getErrorStatus().equals(MobileResultEnum.NO_EXISTS_OTHERACCOUNT_FORFEE.getValue())) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_OTHERACCOUNT_FORFEE));
                } else {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.ERROR_SYSTEM));
                }
            }
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
    public String
            SearchResgisterCardMB(String xml) {
        try {
            SearchResgisterCardMBRq req = (SearchResgisterCardMBRq) Xml.unMarshaller(SearchResgisterCardMBRq.class,
                    xml);
            SearchResgisterCardMBRp response = new SearchResgisterCardMBRp();
            response.setCifNo(req.getCifNo());
            response.setRegistertype(req.getRegistertype());
            EbIssuecard issueCard = new EbIssuecard();
            //issueCard.setCustomerNo(req.getCifNo());
            VwMbCustomer customer = new VwMbCustomer();
            customer.setCustomerNo(req.getCifNo());
            issueCard.setCUSTOMERNO(customer);
            issueCard.setRegistertype(req.getRegistertype());
            issueCard.setIschecked('X');
            //issueCard.setId(652);
            List<EbIssuecard> transList = Helper.getDBI().getEbIssuecard(issueCard);
            if (transList == null || transList.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_INFOISSUE));
            } else {
                ResgisterCardMB[] txnList = new ResgisterCardMB[transList.size()];
                for (int i = 0; i < transList.size(); i++) {
                    txnList[i] = new ResgisterCardMB();

                    BeanUtils.copyProperties(txnList[i], transList.get(i));
                    txnList[i].setInsdate(changeFormatDate2(txnList[i].getInsdate()));
                    if (transList.get(i).getCARDTYPE() != null) {
                        txnList[i].setCardtype(transList.get(i).getCARDTYPE().getCardtype());
                        txnList[i].setCctype(transList.get(i).getCARDTYPE().getCardtype());
                        txnList[i].setCcname(transList.get(i).getCARDTYPE().getCardname());
                    }
                    // txnList[i].setTxndate(txnList[i].getTxndate().substring(0, 10));
                }
                response.setRegList(txnList);
            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * Get history transactions of account
     * CASA account: from date - to date
     * other accounts: 10 newest transations
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String
            GetAccountStmtNew(String xml) {
        try {

            AccountStmtRq req = (AccountStmtRq) Xml.unMarshaller(AccountStmtRq.class,
                    xml);
            AccountStmtRp response = new AccountStmtRp();
            String accountno = req.getAccountNo();
            String AccountGroupCode = req.getAccountGroupCode();

            if (AccountGroupCode.equals(ACCOUNT_CASA)) {
                //neu = ngay hien tai thi cong them 10
                if (req.getToDate() != null) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                    String currentdate = format.format(new Date());

                    if (currentdate.equals(req.getToDate())) {
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.DATE, 10);
                        req.setToDate(format.format(cal.getTime()));
                    }
                }

                List transList = Helper.getDBI().getTransationListByAccountNo(accountno, req.getFromDate(), req.getToDate(), req.getSrno());
                if (transList == null || transList.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_TRANSACTION));
                } else {
                    TxnDetail[] txnList = new TxnDetail[transList.size()];
                    for (int i = 0; i < transList.size(); i++) {
                        txnList[i] = new TxnDetail();
                        BeanUtils.copyProperties(txnList[i], transList.get(i));
                        txnList[i].setTxndate(txnList[i].getTxndate().replace("-", "").substring(0, 8));
                    }
                    response.setTxnList(txnList);
                }
            } else if (AccountGroupCode.equals(ACCOUNT_TD)) {

                List transList = Helper.getDBI().getTDTransationListByAccountNo(accountno);
                if (transList == null || transList.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_TRANSACTION));
                } else {
                    TxnDetail[] txnList = new TxnDetail[transList.size()];
                    for (int i = 0; i < transList.size(); i++) {
                        txnList[i] = new TxnDetail();
                        BeanUtils.copyProperties(txnList[i], transList.get(i));
                        txnList[i].setTxndate(txnList[i].getTxndate().replace("-", "").substring(0, 8));
                    }
                    response.setTxnList(txnList);
                }
            } else if (AccountGroupCode.equals(ACCOUNT_MASTERCARD) || AccountGroupCode.equals(ACCOUNT_MASTERCARD_MD) || AccountGroupCode.equals(ACCOUNT_MASTERCARD_MC)) {
                List transList;
                //case old app, top 20 giao dich gan nhat
                if (req.getFromDate() == null || req.getFromDate().isEmpty() || req.getToDate() == null || req.getToDate().isEmpty()) {
                    transList = Helper.getDBI().getTransactionMaterCardByCardnoNew(req.getAccountNo());
                    if (transList == null || transList.isEmpty()) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_TRANSACTION));
                    } else {
                        TxnDetail[] txnList = new TxnDetail[transList.size()];
                        for (int i = 0; i < transList.size(); i++) {
                            txnList[i] = new TxnDetail();
                            BeanUtils.copyProperties(txnList[i], transList.get(i));
                            // txnList[i].setTxndate(txnList[i].getTxndate().substring(0, 10));
                        }
                        response.setTxnList(txnList);
                    }
                } else {
                    String getALL = req.getGetAll();
                    if (getALL != null && getALL.equals("Y")) {
                        getALL = "300";
                    } else {
                        getALL = "10";
                    }
                    transList = Helper.getDBI().getTransactionMaterCardByCardnoByTime(req.getAccountNo(), req.getFromDate(), req.getToDate(), req.getSrno(), getALL);
                    //transList = Helper.getDBI().getTransactionMaterCardByCardnoByTime(req.getAccountNo(), req.getFromDate(), req.getToDate(), req.getSrno(), "10");
                    if (transList == null || transList.isEmpty()) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_TRANSACTION));
                    } else {
                        TxnDetail[] txnList = new TxnDetail[transList.size()];
                        for (int i = 0; i < transList.size(); i++) {
                            txnList[i] = new TxnDetail();
                            HashMap hm_Td = (HashMap) transList.get(i);
                            txnList[i].setCoddrcr(hm_Td.get("coddrcr").toString());
                            txnList[i].setDescription(hm_Td.get("description").toString());
                            txnList[i].setSrno(hm_Td.get("txn_tms").toString());
                            txnList[i].setTxnamount(hm_Td.get("txnamount").toString());
                            txnList[i].setTxndate(hm_Td.get("txndate").toString());
                            txnList[i].setTxtreferenceno(hm_Td.get("txn_tms").toString());
                            txnList[i].setCodtxncurr(hm_Td.get("cur_code").toString());
                            txnList[i].setTxnCode(hm_Td.get("category_id").toString());
                            txnList[i].setTxnamount_vnd(hm_Td.get("txnamount_vnd") == null ? "" : hm_Td.get("txnamount_vnd").toString());
                        }
                        response.setTxnList(txnList);
                    }
                }

                /*
                List transList = Helper.getDBI().getTransactionMaterCardByCardnoNew(accountno);
                if (transList == null || transList.isEmpty()) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_TRANSACTION));
                } else {
                    TxnDetail[] txnList = new TxnDetail[transList.size()];
                    for (int i = 0; i < transList.size(); i++) {
                        txnList[i] = new TxnDetail();
                        BeanUtils.copyProperties(txnList[i], transList.get(i));
                        // txnList[i].setTxndate(txnList[i].getTxndate().substring(0, 10));
                    }
                    response.setTxnList(txnList);
                    }*/
            } else if (AccountGroupCode.equals(ACCOUNT_LOAN)) {
                List paidIntList = Helper.getDBI().getCLSCHEDULEINTPAID(accountno);
                if (paidIntList != null && !paidIntList.isEmpty()) {
                    LoanTxnDetail[] paidLoanTxnList = new LoanTxnDetail[paidIntList.size()];
                    for (int i = 0; i < paidIntList.size(); i++) {
                        paidLoanTxnList[i] = new LoanTxnDetail();
                        BeanUtils.copyProperties(paidLoanTxnList[i], paidIntList.get(i));
                    }
                    response.setListPaidInterestLoanTxn(paidLoanTxnList);
                }

                List unPaidIntList = Helper.getDBI().getCLSCHEDULEINTUNPAID(accountno);
                if (unPaidIntList != null && !unPaidIntList.isEmpty()) {
                    LoanTxnDetail[] paidLoanTxnList = new LoanTxnDetail[unPaidIntList.size()];
                    for (int i = 0; i < unPaidIntList.size(); i++) {
                        paidLoanTxnList[i] = new LoanTxnDetail();
                        BeanUtils.copyProperties(paidLoanTxnList[i], unPaidIntList.get(i));
                    }
                    response.setListUnPaidInterestLoanTxn(paidLoanTxnList);
                }

                List paidPricibalList = Helper.getDBI().getCLSCHEDULEPRIPAIDSCB(accountno);
                if (paidPricibalList != null && !paidPricibalList.isEmpty()) {
                    LoanTxnDetail[] paidLoanTxnList = new LoanTxnDetail[paidPricibalList.size()];
                    for (int i = 0; i < paidPricibalList.size(); i++) {
                        paidLoanTxnList[i] = new LoanTxnDetail();
                        BeanUtils.copyProperties(paidLoanTxnList[i], paidPricibalList.get(i));
                    }
                    response.setListPaidPrincipalLoanTxn(paidLoanTxnList);
                }

                List unPaidPricibalList = Helper.getDBI().getCLSCHEDULEPRIUNPAIDSCB(accountno);
                if (unPaidPricibalList != null && !unPaidPricibalList.isEmpty()) {
                    LoanTxnDetail[] paidLoanTxnList = new LoanTxnDetail[unPaidPricibalList.size()];
                    for (int i = 0; i < unPaidPricibalList.size(); i++) {
                        paidLoanTxnList[i] = new LoanTxnDetail();
                        BeanUtils.copyProperties(paidLoanTxnList[i], unPaidPricibalList.get(i));
                    }
                    response.setListUnPaidPrincipalLoanTxn(paidLoanTxnList);
                }

            }

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /*
     * Get all accounts of CIF
     * 
     */
    /**
     *
     * @param xml
     * @return
     */
    public String
            GetAccountListNew(String xml) {
        try {
            AccountListRq req = (AccountListRq) Xml.unMarshaller(AccountListRq.class,
                    xml);
            AccountListRp response = new AccountListRp();

            String custno = req.getCifNo();

            List listAccount = Helper.getDBI().GetAccountListByCustNoNew(custno);
            if (listAccount == null || listAccount.isEmpty()) {
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
            }

            AccountDetail[] accountDetails = new AccountDetail[listAccount.size()];
            for (int i = 0; i < listAccount.size(); i++) {
                accountDetails[i] = new AccountDetail();
                BeanUtils.copyProperties(accountDetails[i], listAccount.get(i));
            }

            response.setListAccount(accountDetails);
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String
            CheckAlertTD(String xml) {
        try {
            CheckAlertTDRq alertRq = (CheckAlertTDRq) Xml.unMarshaller(CheckAlertTDRq.class,
                    xml);
            CheckAlertTDRp response = new CheckAlertTDRp();
            response.setCustomerNo(alertRq.getCustomerNo());

            List alerttd = Helper.getDBI().findHistoryListAccountTD(response.getCustomerNo(), "CIF", null);
            if (alerttd == null || alerttd.isEmpty()) {
                response.setRegisterStatus(ALERTTD_OFF);
            } else {
                HashMap hm_Td = (HashMap) alerttd.get(0);
                if (hm_Td.get("active").toString().equalsIgnoreCase("Y")) {
                    response.setRegisterStatus(ALERTTD_ON);
                } else {
                    response.setRegisterStatus(ALERTTD_OFF);
                }
            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String
            RegisterAlertTD(String xml) {
        try {
            RegisterAlertTDRq registerTdRq = (RegisterAlertTDRq) Xml.unMarshaller(RegisterAlertTDRq.class,
                    xml);
            RegisterAlertTDRp response = new RegisterAlertTDRp();
            if (registerTdRq.getRegisterStatus().equals(ALERTTD_ON)) {
                registerTdRq.setRegisterStatus("Y");
            } else if (registerTdRq.getRegisterStatus().equals(ALERTTD_OFF)) {
                registerTdRq.setRegisterStatus("N");
            }
            try {
                int result = Helper.getDBI().regeisterAlertTDMB(registerTdRq.getCustomerNo(), registerTdRq.getMobileNo(), registerTdRq.getRegisterStatus());
                if (result == 0) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
                } else {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.ERROR_ALERTTD));
                }
            } catch (Exception e) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.ERROR_ALERTTD));
            }

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
    public String
            GetPrimaryCardList(String xml) {
        try {
            GetPrimaryCardListRq req = (GetPrimaryCardListRq) Xml.unMarshaller(GetPrimaryCardListRq.class,
                    xml);
            GetPrimaryCardListRp response = new GetPrimaryCardListRp();
            /*
             List listCard = Helper.getDBI().getPrimaryATMList(custno);
             if (listCard == null || listCard.isEmpty()) {
             return Xml.Marshaller(response.getMBResponse(MobileResultEnum.CANNOT_ISSUE_SLAVEATM));
             }

             CardDetail[] cardDetails = new CardDetail[listCard.size()];
             for (int i = 0; i < listCard.size(); i++) {
             cardDetails[i] = new CardDetail();
             BeanUtils.copyProperties(cardDetails[i], listCard.get(i));
             }
             */
            String cardtype = "'LC'";
            String groupcode = null;
            if (req.getCardType() != null && req.getCardType().equals("MD")) {
                cardtype = "'VS','MC'";
                groupcode = "'MD'";
            }
            List listCard = Helper.getDBI().getCardList(req.getCifNo(), cardtype, groupcode);
            if (listCard == null || listCard.isEmpty()) {
                //return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));

            }

            ArrayList<CardDetail> cardDetails = new ArrayList<CardDetail>();
            for (int i = 0; i < listCard.size(); i++) {
                CardDetail cardDetail = new CardDetail();
                BeanUtils.copyProperties(cardDetail, listCard.get(i));
                if (cardDetail.getMasterslave() != null && cardDetail.getMasterslave().equalsIgnoreCase("P")) {
                    if (!cardDetail.getTinhtrangthe().equals("The Het Han")) {
                        cardDetails.add(cardDetail);
                    }
                }

            }
            if (cardDetails.size() == 0) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            }
            response.setListCard((CardDetail[]) cardDetails.toArray(new CardDetail[cardDetails.size()]));
            /*
             CardDetail[] cardDetails = new CardDetail[listCard.size()];
             for (int i = 0; i < listCard.size(); i++) {
             cardDetails[i] = new CardDetail();
             BeanUtils.copyProperties(cardDetails[i], listCard.get(i));
             if (cardDetails[i].getTinhtrangthe()!=null &&  cardDetails[i].getTinhtrangthe().equals("The Tam Khoa")){
             cardDetails[i].setTinhtrangthe("L");
             }else {
             cardDetails[i].setTinhtrangthe("O");
             }
                
             if (cardDetails[i].getBlockonline()!=null && cardDetails[i].getBlockonline().equals("1")){
             cardDetails[i].setBlockonline("L");
             }else {
             cardDetails[i].setBlockonline("O");
             } 
             response.setListCard(cardDetails);

             }*/

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String
            GetCreditCardList(String xml) {
        try {
            GetCreditCardListRq req = (GetCreditCardListRq) Xml.unMarshaller(GetCreditCardListRq.class,
                    xml);
            GetCreditCardListRp response = new GetCreditCardListRp();
            String cifno = null;
            String cardAccountNo = null;
            if (req.getCardAccountNumer() != null && req.getCardAccountNumer().length() > 0) {
                cardAccountNo = req.getCardAccountNumer();
            } else {
                cifno = req.getCifNo();
            }
            IIBCardService iibCardService = new IIBCardService();
            SelectCreditCardSumary_out_Type selectCreditCardSumary = iibCardService.selectCreditCardSumarySimple(Configuration.getIIBContext(), cifno, cardAccountNo, IIB_CHANNEL_MOBILE);
            if (!selectCreditCardSumary.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.INVALID_CREDITCARD_ACCOUNT_NO));
            }
            List<CardInfoType> cardList = selectCreditCardSumary.getCardInfo();
            ArrayList<CardDetail> accountDetailList = new ArrayList<CardDetail>();
            for (CardInfoType carddetail : cardList) {
                CardDetail detail = new CardDetail();
                detail.setAccountno(carddetail.getCardPaneID());
                detail.setCardaccountnumber(carddetail.getCardAccountNum());
                detail.setCardno(carddetail.getHiddenCardNum());
                detail.setE_name(carddetail.getCardOwnerName());
                detail.setDunocuoiky(carddetail.getCardInstallmentAmount());
                //detail.setDunodasudung(carddetail.getCardOutstandingAmount());
                detail.setDunodasudung(carddetail.getCardOutstandingAmountNotUnpost());
                detail.setNgaytttoithieu(carddetail.getCardInstallmentDate());
                detail.setThanhtoantoithieu(carddetail.getCardMinimumInstallmentAmount());
                detail.setAccountBranchOpen(carddetail.getCardOpenBranchCode());
                detail.setExpire_date(carddetail.getCardExpiredDate());

                detail.setCardIPPCurrentAmount(carddetail.getCardIPPCurrentAmount());
                detail.setCardIPPStatementAmount(carddetail.getCardIPPStatementAmount());
                //Tong Số tiền thanh toán tối thiểu
                BigDecimal SumAmtLimitPay = BigDecimal.valueOf(Double.valueOf(carddetail.getCardMinimumInstallmentAmount()));
                BigDecimal CardIPPStatementAmount = BigDecimal.valueOf(Double.valueOf(carddetail.getCardIPPStatementAmount()));
                SumAmtLimitPay = SumAmtLimitPay.add(CardIPPStatementAmount);
                //Tong Số tiền đã thanh toán trong kỳ sao kê
                BigDecimal SumPaidAmount = BigDecimal.valueOf(Double.valueOf(carddetail.getCardPaidCurrentAmount()));
                BigDecimal cardIPPPaidAmount = BigDecimal.valueOf(Double.valueOf(carddetail.getCardIPPPaidAmount()));
                SumPaidAmount = SumPaidAmount.add(cardIPPPaidAmount);
                //Tổng dư nợ kỳ sao ke gan nhat                
                BigDecimal SumPrincipalToPay = BigDecimal.valueOf(Double.valueOf(carddetail.getCardInstallmentAmount()));
                SumPrincipalToPay = SumPrincipalToPay.add(CardIPPStatementAmount);

                detail.setSumAmtLimitPay(SumAmtLimitPay.toPlainString());
                detail.setSumPaidAmount(SumPaidAmount.toPlainString());
                detail.setSumPrincipalToPay(SumPrincipalToPay.toPlainString());

                if (carddetail.getCardAccountNum().substring(0, 1).equals(FIRST_MASTERCARD_MC)) {
                    accountDetailList.add(detail);
                }
            }
            if (accountDetailList.size() == 0) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.INVALID_CREDITCARD_ACCOUNT_NO));
            }
            response.setListCard((CardDetail[]) accountDetailList.toArray(new CardDetail[accountDetailList.size()]));
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String
            GetCreditCardListOLD(String xml) {
        try {
            GetCreditCardListRq req = (GetCreditCardListRq) Xml.unMarshaller(GetCreditCardListRq.class,
                    xml);
            GetCreditCardListRp response = new GetCreditCardListRp();

            /*
             String custno = req.getCifNo();
             String cardtype = "";
             if (req.getCardType().equals("1")) {
             cardtype = "LC";
             } else if (req.getCardType().equals("2")) {
             cardtype = "MC";
             }
             */
            List listCard = Helper.getDBI().getCreditCardList(req.getCifNo(), req.getCardAccountNumer());
            if (listCard == null || listCard.isEmpty()) {
                if (req.getCardAccountNumer() == null || req.getCardAccountNumer().length() == 0) {
                    return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                } else {
                    return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                }
            }

            CardDetail[] cardDetails = new CardDetail[listCard.size()];
            for (int i = 0; i < listCard.size(); i++) {
                cardDetails[i] = new CardDetail();
                BeanUtils.copyProperties(cardDetails[i], listCard.get(i));

                HashMap hm_Td = (HashMap) listCard.get(i);
                cardDetails[i].setAccountno(hm_Td.get("panecd").toString());
                cardDetails[i].setCardaccountnumber(hm_Td.get("cardaccountno").toString());
                cardDetails[i].setCardname(hm_Td.get("e_name").toString());
                //tem comment
                //cardDetails[i].setCardname(hm_Td.get("card_desc").toString());
                cardDetails[i].setMasterslave(hm_Td.get("tinhchatthe").toString());
                cardDetails[i].setCardno(hm_Td.get("cardno").toString());
                cardDetails[i].setExpire_date(hm_Td.get("expiredate").toString());
                cardDetails[i].setAccountBranchOpen(hm_Td.get("brch").toString());

            }

            response.setListCard(cardDetails);
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
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
    public String
            CreditCardPayNew(String xml) {
        try {
            CreditCardPayNewRq req = (CreditCardPayNewRq) Xml.unMarshaller(CreditCardPayNewRq.class,
                    xml);
            //CreditCardPayRq req = new CreditCardPayRq();
            //BeanUtils.copyProperties(req, req2);
            TxnCommitRp response = new TxnCommitRp();
            response.setAccountNo(req.getFromAccount());
            response.setCifNo(req.getCifNo());
            response.setTxnType(req.getTxnType());
            //check trong truong hop nhap OTP su dung Token
            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {

                CardwordController cardcontroller = new CardwordController();
                // Thanh toan IPP
                BigDecimal debtPay;
                BigDecimal debtCur;
                String PMT_INFO = Helper.getDBI().queryContactCenterInfo("getPMT_INFO", req.getCardAccountNumer());
                if (PMT_INFO == null || PMT_INFO.isEmpty()) {
                    debtPay = BigDecimal.ZERO;
                    debtCur = BigDecimal.ZERO;
                } else {
                    //debtPay --> Dư nợ trả góp phải thanh toán
                    //debtCur -->Dư nợ trả góp đến hiện tại                  
                    debtPay = new BigDecimal(PMT_INFO.split("#")[0]);
                    debtCur = new BigDecimal(PMT_INFO.split("#")[1]);
                }
                String PANCW = req.getCardAccountNumer().concat(req.getCardNo().substring(0, 4)).concat(req.getCardNo().substring(req.getCardNo().length() - 4));
                String result = cardcontroller.cardReload(req.getFromAccount(), PANCW, req.getExpireDate(), "MB",
                        req.getAccountBranchOpen(), "VND", BigDecimal.valueOf(Double.valueOf(req.getAmount())), debtPay, debtCur, "0");
                String resultArr[] = result.split("#");
                String resultcode = resultArr[0];
                if (resultcode.equals("00")) {
                    response.setTxnID(resultArr[1]);
                    response.setTxnCommitTime(getTime());
                    response.setReloadAccList(RELOAD_ACC_LIST);
                    //response.setCardbalance(resultArr[3]);
                    /*
                    List accountMC = Helper.getDBI().getMaterCardDetailByCardnoNew(req.getAccountNo());
                    if (accountMC == null || accountMC.isEmpty()) {
                        //return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                    }else{
                        HashMap hm_Td = (HashMap) accountMC.get(0);
                        response.setCardbalance(hm_Td.get("dunodasudung").toString());                    
                    }*/
                    IIBCardService iibCardService = new IIBCardService();
                    RetrieveCreditCardDetail_out_Type retrieveCreditCardDetail_out_Type = iibCardService.retrieveCreditCardDetailSimple(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);
                    if (!retrieveCreditCardDetail_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                    }
                    CardInfoType carddetailIIB = retrieveCreditCardDetail_out_Type.getCardInfo();
                    //response.setCardbalance(carddetailIIB.getCardOutstandingAmount());  
                    //response.setDunodasudung(carddetailIIB.getCardOutstandingAmount());    
                    response.setCardbalance(carddetailIIB.getCardOutstandingAmountNotUnpost());
                    response.setDunodasudung(carddetailIIB.getCardOutstandingAmountNotUnpost());
                    //thêm các field cho thẻ 68 ngày
                    response.setCardProduct("THE");
                    /*
                    response.setCardProduct(carddetailIIB.getCardProduct());
                    if (response.getCardProduct().equals("WORLD")){
                        response.setCardInstallmentAmountPre (carddetailIIB.getCardInstallmentAmountPre());
                        response.setCardMinimumInstallmentAmountPre(carddetailIIB.getCardMinimumInstallmentAmountPre());
                        response.setCardPaidCurrentAmountPre(carddetailIIB.getCardPaidCurrentAmountPre());
                        response.setCardInstallmentDatePre (carddetailIIB.getCardInstallmentDatePre());
                    }*/
                    response.setNgaytttoithieu(carddetailIIB.getCardInstallmentDate());
                    //Tong Số tiền thanh toán tối thiểu
                    BigDecimal SumAmtLimitPay = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardMinimumInstallmentAmount()));
                    BigDecimal CardIPPStatementAmount = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardIPPStatementAmount()));
                    SumAmtLimitPay = SumAmtLimitPay.add(CardIPPStatementAmount);
                    //Tong Số tiền đã thanh toán trong kỳ sao kê
                    BigDecimal SumPaidAmount = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardPaidCurrentAmount()));
                    BigDecimal cardIPPPaidAmount = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardIPPPaidAmount()));
                    SumPaidAmount = SumPaidAmount.add(cardIPPPaidAmount);
                    //Tổng dư nợ kỳ sao ke gan nhat                
                    BigDecimal SumPrincipalToPay = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardInstallmentAmount()));
                    SumPrincipalToPay = SumPrincipalToPay.add(CardIPPStatementAmount);
                    response.setSumAmtLimitPay(SumAmtLimitPay.toPlainString());
                    response.setSumPaidAmount(SumPaidAmount.toPlainString());
                    response.setSumPrincipalToPay(SumPrincipalToPay.toPlainString());

                    BigDecimal DuNoTheDenHienTai = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardOutstandingAmountNotUnpost()));
                    BigDecimal DuNoTraGopDenHienTai = BigDecimal.valueOf(Double.valueOf(carddetailIIB.getCardIPPCurrentAmount()));
                    BigDecimal SumDuNoDenHienTai = DuNoTheDenHienTai.add(DuNoTraGopDenHienTai);
                    response.setSumCardBalance(SumDuNoDenHienTai.toPlainString());

                    return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));

                } else {
                    //hoan tien
                    if (resultcode.equals("01")) {
                        return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.CANNOT_PAYMENTCREDIT_REFUNDED));
                        //cho tra soat    
                    } else if (resultcode.equals("03") || resultcode.equals("04")) {
                        return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.CANNOT_PAYMENTCREDIT_NOT_REFUND));
                    } else if (resultcode.equals("02")) {
                        return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY));
                    } else if (resultcode.equals("05")) {
                        //THE CHUA ACTIVE DA HOAN TIEN
                        return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.CANNOT_PAYMENTCREDIT_NOTACTIVE));
                    } else {
                        return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                    }
                }
            }
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
    public String
            CreditCardPayNew_New(String xml) {
        try {
            CreditCardPayNewRq req = (CreditCardPayNewRq) Xml.unMarshaller(CreditCardPayNewRq.class,
                    xml);
            //CreditCardPayRq req = new CreditCardPayRq();
            //BeanUtils.copyProperties(req, req2);
            TxnCommitRp response = new TxnCommitRp();
            response.setAccountNo(req.getFromAccount());
            response.setCifNo(req.getCifNo());
            response.setTxnType(req.getTxnType());
            //check trong truong hop nhap OTP su dung Token
            int isValidAuth = req.validateAuth();
            if (isValidAuth != _SUCESSFUL) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.get(String.valueOf(isValidAuth))));
            } else {

                CardwordController cardcontroller = new CardwordController();
                // Thanh toan IPP
                PmtInfoV11ResDto pmtInfo = Helper.getDBI().getPMT_INFOV11(req.getCardAccountNumer());

                String PANCW = req.getCardAccountNumer().concat(req.getCardNo().substring(0, 4)).concat(req.getCardNo().substring(req.getCardNo().length() - 4));
                String result = cardcontroller.CreditCardPayMent(req.getFromAccount(), PANCW, req.getExpireDate(), "MB",
                        req.getAccountBranchOpen(), "VND", BigDecimal.valueOf(Double.valueOf(req.getAmount())), pmtInfo, "0");

                String resultArr[] = result.split("#");
                String resultcode = resultArr[0];
                if (resultcode.equals("00")) {
                    response.setTxnID(resultArr[1]);
                    response.setTxnCommitTime(getTime());
                    response.setReloadAccList(RELOAD_ACC_LIST);
                    //response.setCardbalance(resultArr[3]);
                    /*
                    List accountMC = Helper.getDBI().getMaterCardDetailByCardnoNew(req.getAccountNo());
                    if (accountMC == null || accountMC.isEmpty()) {
                        //return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.ACCNO_NOT_FOUND));
                    }else{
                        HashMap hm_Td = (HashMap) accountMC.get(0);
                        response.setCardbalance(hm_Td.get("dunodasudung").toString());                    
                    }*/
                    IIBCardService iibCardService = new IIBCardService();
                    RetrieveCreditCardDetail_out_Type retrieveCreditCardDetail_out_Type = iibCardService.retrieveCreditCardDetailSimple(Configuration.getIIBContext(), req.getAccountNo(), IIB_CHANNEL_MOBILE);
                    if (!retrieveCreditCardDetail_out_Type.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
                    }
                    CardInfoType carddetailIIB = retrieveCreditCardDetail_out_Type.getCardInfo();
                    //response.setCardbalance(carddetailIIB.getCardOutstandingAmount());  
                    //response.setDunodasudung(carddetailIIB.getCardOutstandingAmount());    
                    response.setCardbalance(carddetailIIB.getCardOutstandingAmountNotUnpost());
                    response.setDunodasudung(carddetailIIB.getCardOutstandingAmountNotUnpost());

                    return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.OK));

                } else {
                    //hoan tien
                    if (resultcode.equals("01")) {
                        return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.CANNOT_PAYMENTCREDIT_REFUNDED));
                        //cho tra soat    
                    } else if (resultcode.equals("03") || resultcode.equals("04")) {
                        return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.CANNOT_PAYMENTCREDIT_NOT_REFUND));
                    } else if (resultcode.equals("02")) {
                        return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY));
                    } else if (resultcode.equals("05")) {
                        //THE CHUA ACTIVE DA HOAN TIEN
                        return Xml.Marshaller(response.getMBFinResponse(MobileResultEnum.CANNOT_PAYMENTCREDIT_NOTACTIVE));
                    } else {
                        return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.CANNOT_TRANSFERFCUBS));
                    }
                }
            }
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
    public String
            GetStatementMonthList(String xml) {
        try {
            GetStatementMonthListRq req = (GetStatementMonthListRq) Xml.unMarshaller(GetStatementMonthListRq.class,
                    xml);
            GetStatementMonthListRp response = new GetStatementMonthListRp();
            String statementmonth = "";
            String DateFrom = "26";
            String DateTo = "25";
            //-	VS: visa, MC:master
            Date date = new Date();
            //dt.get
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            //cal.add(Calendar.DATE, days); //minus number would decrement the days
            if (req.getCreditType() != null && req.getCreditType().length() > 0) {
                if (req.getCreditType().equals("MC") || req.getCreditType().equals("MASTERCARD")) {
                    //26 thang trc - 25 thang nay
                    DateFrom = "26";
                    DateTo = "25";
                    if (dayOfMonth <= 25) {
                        calendar.add(Calendar.MONTH, - 1);
                    }
                } else if (req.getCreditType().equals("VS") || req.getCreditType().equals("VISA CARD")) {
                    DateFrom = "16";
                    DateTo = "15";
                    if (dayOfMonth <= 15) {
                        calendar.add(Calendar.MONTH, - 1);
                    }
                }
            } else {
                if (dayOfMonth <= 25) {
                    calendar.add(Calendar.MONTH, - 1);
                }
            }
            List<CreditCardStatementRp> statementList = new ArrayList<>();
            CreditCardStatementRp crRq = new CreditCardStatementRp();

            statementmonth = statementmonth.concat(Integer.toString(calendar.get(Calendar.MONTH) + 1)).concat("/").concat(Integer.toString(calendar.get(Calendar.YEAR)));
            //crRq.setStatementfromdate(Integer.toString(calendar.get(Calendar.YEAR)).concat(Integer.toString(calendar.get(Calendar.MONTH))).concat(DateFrom));
            crRq.setStatementdate(Integer.toString(calendar.get(Calendar.YEAR)).concat(addZeroMonthState(Integer.toString(calendar.get(Calendar.MONTH) + 1))).concat(DateTo));
            crRq.setPeriod(Integer.toString(calendar.get(Calendar.YEAR)).concat(Integer.toString(calendar.get(Calendar.MONTH) + 1)));

            calendar.add(Calendar.MONTH, - 1);
            statementmonth = statementmonth.concat("#").concat(Integer.toString(calendar.get(Calendar.MONTH) + 1)).concat("/").concat(Integer.toString(calendar.get(Calendar.YEAR)));
            crRq.setStatementfromdate(Integer.toString(calendar.get(Calendar.YEAR)).concat(addZeroMonthState(Integer.toString(calendar.get(Calendar.MONTH) + 1))).concat(DateFrom));
            statementList.add(crRq);
            crRq = new CreditCardStatementRp();
            crRq.setStatementdate(Integer.toString(calendar.get(Calendar.YEAR)).concat(addZeroMonthState(Integer.toString(calendar.get(Calendar.MONTH) + 1))).concat(DateTo));
            crRq.setPeriod(Integer.toString(calendar.get(Calendar.YEAR)).concat(Integer.toString(calendar.get(Calendar.MONTH) + 1)));

            calendar.add(Calendar.MONTH, - 1);
            statementmonth = statementmonth.concat("#").concat(Integer.toString(calendar.get(Calendar.MONTH) + 1)).concat("/").concat(Integer.toString(calendar.get(Calendar.YEAR)));

            crRq.setStatementfromdate(Integer.toString(calendar.get(Calendar.YEAR)).concat(addZeroMonthState(Integer.toString(calendar.get(Calendar.MONTH) + 1))).concat(DateFrom));
            statementList.add(crRq);
            crRq = new CreditCardStatementRp();
            crRq.setPeriod(Integer.toString(calendar.get(Calendar.YEAR)).concat(Integer.toString(calendar.get(Calendar.MONTH) + 1)));
            crRq.setStatementdate(Integer.toString(calendar.get(Calendar.YEAR)).concat(addZeroMonthState(Integer.toString(calendar.get(Calendar.MONTH) + 1))).concat(DateTo));

            calendar.add(Calendar.MONTH, - 1);
            crRq.setStatementfromdate(Integer.toString(calendar.get(Calendar.YEAR)).concat(addZeroMonthState(Integer.toString(calendar.get(Calendar.MONTH) + 1))).concat(DateFrom));
            statementList.add(crRq);

            response.setStatementList(statementList);
            response.setStatementMonthList(statementmonth);

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param strMonth
     * @return
     */
    public static String addZeroMonthState(String strMonth) {
        if (strMonth.length() < 2) {
            return "0".concat(strMonth);
        } else {
            return strMonth;
        }

    }

    /**
     *
     * Ham này thực hiện hạch toán core và gạch nợ đối tác Step 1: Insert
     * PblBillPaid Step 2: Call IIB hach toan Step 3: Call IIB de gach no doi
     * tac
     *
     * @param reqexchg
     * @param pep
     * @param pepe
     * @param from_custacc
     * @param billpaid
     * @param transfer_type
     * @return
     */
    private void executePayBillIIB(RequestPayment reqpay, BillPaymentRq pep) {
        try {

            String refCub = "";
            int idbillPaid = -1;

            //1.3 Insert bang BillPaid
            PblBillpaid billpaid = new PblBillpaid();
            List mbMobile = Helper.getDBI().getCustomerFromMobile(pep.getUserName());

            if (mbMobile == null || mbMobile.isEmpty()) {
                logger.error("***- ERROR MB (FUNCTION transferPaybill): " + "not get customerid from DB VNINFO ");
            }
            HashMap hm_MB = (HashMap) mbMobile.get(0);

            billpaid.setIdpartner(pep.getIdpartner());

            PblServicetype pst = new PblServicetype();
            pst.setIdservicetype(pep.getIdservicetype());
            billpaid.setPblServicetype(pst);

            PblProvider pdr = new PblProvider();
            pdr.setIdprovider(pep.getIdprovider());
            billpaid.setPblProvider(pdr);

            billpaid.setCustomercode(pep.getCustomerCode());
            billpaid.setDataxml("");
            billpaid.setIduserMaker(hm_MB.get("cus_id").toString());
            billpaid.setIdchanneluserMaker(pep.getUserName());
            billpaid.setIduserChecker(hm_MB.get("cus_id").toString());
            billpaid.setIdchanneluserChecker(pep.getUserName());

            billpaid.setIdchannel("03");
            billpaid.setUsertype("1");
            billpaid.setRefPartner("");
            billpaid.setRefFcubs("");
            billpaid.setTotalamount(Long.valueOf(pep.getPayAmt()));
            billpaid.setPaymentmethod(2);
            billpaid.setAccCust(pep.getCifNo());

            IIBCustomerService iibCustomerService = new IIBCustomerService();
            RetrieveCustomerRefDataMgmt_out_Type retrieveCustomerRefDataMgmt = iibCustomerService.retrieveCustomerRefDataMgmtSimple(Configuration.getIIBContext(), pep.getCifNo(), IIBConstants.CHANNEL_MOBILE);
            if (retrieveCustomerRefDataMgmt.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                CustomerInfoType customerinfo = retrieveCustomerRefDataMgmt.getCustomerInfo();
                billpaid.setAccHoldername(customerinfo.getFullname());
                billpaid.setAccAddress(customerinfo.getAddress().getAddress1());

            } else {
                Helper.writeLogError(MobileController.class,
                        "executePayBillIIB không tìm thấy tài khoản KH - :" + pep.getCifNo());
                pep.setResult(PaymentResultEnum.NO_CUSTOMER_EXISTS.getValue());
            }

            billpaid.setAccIdaccount(pep.getDebitAccount());
            Date d = new Date();
            billpaid.setInsdate(d);
            billpaid.setInsdateMaker(d);
            billpaid.setInsdateChecker(d);
            billpaid.setTransdate(d);
            billpaid.setPaydate(null);
            billpaid.setPaydateFcubs(d);
            billpaid.setIsapproved(Character.valueOf('A'));
            billpaid.setStatus(Character.valueOf('W'));
            // billpaid.setBranchcode(billpaid.getAccIdaccount().substring(0, 3));
            billpaid.setBranchcode(CommonUtils.getBranchAccount(billpaid.getAccIdaccount()));
            idbillPaid = Helper.getDBI().insertPaybillBillPaid(billpaid);

            if (idbillPaid <= 0) {
                logger.error(" Thực hiện insert vào idbillPaid không thành công:" + idbillPaid + pep.getCustomerCode());
                return;
            } else {
                pep.setRefBillpaid(String.valueOf(idbillPaid));
            }
            //STEP 2: Cat tien thanh toan hoa don cua KH

            reqpay.setProcessingcode("PAY");
            reqpay.setDatetime(Common.getDate("ddMMyyyyHHmmss"));
            reqpay.setTranscode("EB" + String.valueOf(System.currentTimeMillis()));

            IIBBillingPaymentService iibBillingService = new IIBBillingPaymentService();
            ResponsePayment responsepay = iibBillingService.callPartnerGateway(Configuration.getIIBContext(), reqpay);

            if (responsepay.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())) {
                refCub = responsepay.getRefcore();
                logger.info("***- LOG THANH TOAN MB FUNCTION executePayBillIIB  iibBillingService): Ma Khach Hang: " + billpaid.getCustomercode() + " -- So tham chieu trich tien REFCUBS= " + refCub + " -- From Account :" + pep.getDebitAccount());
                billpaid.setIdbillpaid(idbillPaid);
                billpaid.setRefFcubs(refCub);
                Helper.getDBI().updatePaybillBillPaid(billpaid);
                pep.setRefBillpaid(String.valueOf(refCub));
            } else {
                logger.error("***- LOG THANH TOAN MB FUNCTION executePayBillIIB  hach toan khong thanh cong: Ma Khach Hang: " + billpaid.getCustomercode() + " - Ma loi:" + responsepay.getResult() + responsepay.getResultMessage());
                pep.setResult(scb.com.vn.message.Message.PaymentResultEnum.CANNOT_TRANSFERFCUBS.getValue());
                return;
            }
            //STEP 3: GACH NO DOI TAC
            responsepay = iibBillingService.payPartner(Configuration.getIIBContext(), responsepay);
            pep.setRespay(responsepay);
            pep.getRespay().setTranscode(responsepay.getTranscode());
            pep.setResult(responsepay.getResult());
            pep.setRefBillpaid(String.valueOf(refCub));
            billpaid.setDataxml(Xml.Marshaller(responsepay));

            if (responsepay.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())) {
                billpaid.setPaydate(new Date());
                billpaid.setRefPartner(responsepay.getTranscode());
                billpaid.setStatus('D');
                Helper.getDBI().updatePaybillBillPaid(billpaid);
            } else {
                //giao dich that bai đã hoàn tiền cho KH
                if (pep.getResult().equals(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue())) {
                    String _msgouttransfer = "TRANSFER UBS. Thuc hien HOAN TIEN HOAN TIEN %1$s. [So but toan-%2$s]";
                    logger.info(String.format(_msgouttransfer, "THANH CONG", refCub));
                    updateBillStatusToDB(billpaid, 'F');
                    pep.setResult(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue());
                } else if (pep.getResult().equals(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue())) {
                    //Khong hoan tien, yeu cau doi tac gach bu giao dich
                    String a = "PAYBILL IS NOT SUCCESS. Hold tien KH, yeu cau doi tac gach bu tay Result %1$s. [idbillpaid-%2$s]";
                    logger.info(String.format(a, pep.getResult(), billpaid.getIdbillpaid()));
                    updateBillStatusToDB(billpaid, 'H');
                    pep.setResult(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());
                } else {
                    //Hold toan bo giao dich
                    String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong, thuc hien doi soat Result %1$s. [idbillpaid-%2$s]";
                    logger.info(String.format(a, pep.getResult(), billpaid.getIdbillpaid()));
                    updateBillStatusToDB(billpaid, 'H');
                    pep.setResult(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());

                }
            }
        } catch (RemoteException ex) {
            Helper.writeLogError(MobileController.class,
                    "***- ERROR IB (FUNCTION executePayBillIIB): " + ex.getMessage());
            pep.setResult(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue());

        } catch (Exception ex) {
            Helper.writeLogError(MobileController.class,
                    "***- ERROR IB (FUNCTION executePayBillIIB): " + ex.getMessage());
            pep.setResult(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue());

        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String
            InterestRate(String xml) {
        try {
            InterestRateRq req = (InterestRateRq) Xml.unMarshaller(InterestRateRq.class,
                    xml);
            InterestRateRp response = new InterestRateRp();
            List interestRateList = null;
            boolean ccyVND = false;
            if (req.getCcy()
                    == null || req.getCcy().isEmpty()) {
                ccyVND = true;
                interestRateList = Helper.getDBI().getInterestRate("VND");
            } else {
                if (req.getCcy().equals("VND")) {
                    ccyVND = true;
                }
                interestRateList = Helper.getDBI().getInterestRate(req.getCcy());
            }

            if (interestRateList
                    == null || interestRateList.size()
                    == 0) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_RATE));
            } else {
                ArrayList<InterestRateDetail> listInterestRate = new ArrayList<InterestRateDetail>();
                InterestRateDetail ratePrevious = null;
                if (req.getLanguage() == null || req.getLanguage().isEmpty() || req.getLanguage().equals("VN")) {
                    for (int i = 0; i < interestRateList.size(); i++) {
                        InterestRateDetail rate = new InterestRateDetail();
                        BeanUtils.copyProperties(rate, interestRateList.get(i));
                        if (ccyVND) {
                            if (ratePrevious == null) {
                                ratePrevious = rate;
                            }
                            if (!ratePrevious.getInterest().equals(rate.getInterest()) || !ratePrevious.getTenor().equals(rate.getTenor())) {
                                listInterestRate.add(ratePrevious);
                            }
                            if (i == (interestRateList.size() - 1)) {
                                listInterestRate.add(rate);
                            }
                            ratePrevious = rate;
                        } else {
                            listInterestRate.add(rate);
                        }
                    }
                } else if (req.getLanguage().equals("EN")) {
                    for (int i = 0; i < interestRateList.size(); i++) {
                        InterestRateDetail rate = new InterestRateDetail();
                        BeanUtils.copyProperties(rate, interestRateList.get(i));
                        rate.setTerm(rate.getTerm_eng());
                        rate.setProduct_description(rate.getProduct_des_eng());
                        if (ccyVND) {
                            if (ratePrevious == null) {
                                ratePrevious = rate;
                            }
                            if (!ratePrevious.getInterest().equals(rate.getInterest()) || !ratePrevious.getTenor().equals(rate.getTenor())) {
                                listInterestRate.add(ratePrevious);
                            }
                            if (i == (interestRateList.size() - 1)) {
                                listInterestRate.add(rate);
                            }
                            ratePrevious = rate;
                        } else {
                            listInterestRate.add(rate);
                        }
                    }
                }
                response.setListInterestRate((InterestRateDetail[]) listInterestRate.toArray(new InterestRateDetail[listInterestRate.size()]));
                //response.setListInterestRate(listInterestRate);
            }
            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;

        }
    }

    /**
     *
     * @return
     */
    public boolean verifyTimeEOD() {
        try {

            Calendar calendar = Calendar.getInstance();

            if (calendarStart == null) {
                Helper.writeLogError(MobileController.class,
                        "***- calendarStart: " + calendarStart);
                calendarStart = Calendar.getInstance();
                String[] strStartTime = TDCheckEODStart.split(":");
                calendarStart.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strStartTime[0]));
                calendarStart.set(Calendar.MINUTE, Integer.parseInt(strStartTime[1]));

                calendarEnd = Calendar.getInstance();
                String[] strEndTime = TDCheckEODEnd.split(":");
                calendarEnd.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strEndTime[0]));
                calendarEnd.set(Calendar.MINUTE, Integer.parseInt(strEndTime[1]));
            }

            Date timenow = calendar.getTime();

            if (timenow.after(calendarStart.getTime()) || timenow.before(calendarEnd.getTime())) {
                //Helper.writeLogError(MobileController.class, "***- calendarStart - RETURN - TRUE: "  );
                return true;
            } else {
                //Helper.writeLogError(MobileController.class, "***- calendarStart - RETURN - FALSE: ");
                return false;

            }

        } catch (Exception ex) {
            Helper.writeLogError(MobileController.class,
                    "***- verifyTimeEOD: " + ex.getMessage());
            return false;
        }
    }

    /**
     *
     * @param accountJoin
     * @return
     */
    public boolean checkJoinForTrans(String accountJoin) {
        try {
            List<String> arr_joinAcc = Arrays.asList(Helper.getDBI().getListJoinAccount());

            if (arr_joinAcc == null || arr_joinAcc.isEmpty()) {
                Helper.writeLog(MobileController.class,
                        Level.INFO, "arr_joinAcc.size: - null");
                return false;

            } else {
                Helper.writeLog(MobileController.class,
                        Level.INFO, "arr_joinAcc.size:" + arr_joinAcc.size());
                if (arr_joinAcc.contains(accountJoin)) {
                    return true;
                } else {
                    return false;

                }
            }
        } catch (Exception ex) {
            Helper.writeLogError(MobileController.class,
                    "***- checkJoinForTrans: " + ex.getMessage());
            return false;
        }
    }

    /**
     *
     * @param dateSrc
     * @param formatDateSrc
     * @param fromatDateDes
     * @return
     */
    public String changeFormatDate(String dateSrc, String formatDateSrc, String fromatDateDes) {
        try {
            DateFormat formatter = new SimpleDateFormat(formatDateSrc);
            DateFormat formatter1 = new SimpleDateFormat(fromatDateDes);
            Date date = formatter.parse(dateSrc);
            return formatter1.format(date);

        } catch (Exception ex) {
            Helper.writeLogError(MobileController.class,
                    "***- checkJoinForTrans: " + dateSrc);
            return dateSrc;
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    /**
     *
     * @param xml
     * @return
     */
    public String
            isExistUserBanking(String xml) {
        try {
            IsExistUserBankingRq req = (IsExistUserBankingRq) Xml.unMarshaller(IsExistUserBankingRq.class,
                    xml);
            IsExistUserBankingRp response = new IsExistUserBankingRp();
            String userName = req.getUserName();
            String cif = req.getCifNo();
            String channel = req.getChannel();

            if (channel == null || channel.isEmpty()) {
                channel = "MB";
            }
            if (userName != null && !userName.isEmpty()) {
                if (channel.equalsIgnoreCase("MB")) {
                    IsExistUserEBRes result = Helper.getDBI().isExistUserBanking(userName, cif);
                    switch (result.getErrorCode()) {
                        case 0:
                            response.setErrorCode("00");
                            response.setErrorMsg(result.getUserList().get(0));
                            break;
                        case 1:
                            response.setErrorCode("-30");
                            String s1 = "Duoc phep tao. User phai giong voi IB username sau: ";
                            for (int i = 0; i < result.getUserList().size(); i++) {
                                s1 = s1 + result.getUserList().get(i) + " ";
                            }
                            response.setErrorMsg(s1);
                            break;
                        case 2:
                            response.setErrorCode("00");
                            String s = "Khong duoc phep tao user. User phai giong IB username sau: ";
                            for (int i = 0; i < result.getUserList().size(); i++) {
                                s = s + result.getUserList().get(i) + " ; ";
                            }
                            response.setErrorMsg(s);
                            break;
                        case 3:
                            response.setErrorCode("-30");
                            String s3 = "Duoc phep tao. Cif chua co user IB hoat đong: ";
                            for (int i = 0; i < result.getUserList().size(); i++) {
                                s3 = s3 + result.getUserList().get(i) + " ";
                            }
                            response.setErrorMsg(s3);
                            break;
                        case -1:
                        default:
                            response.getMBResponse(MobileResultEnum.ERROR_SYSTEM);
                            break;
                    }

                } else if (channel.equalsIgnoreCase("IB")) {
                    List resultIB = Helper.getDBI().isExistUserMBanking(userName, cif, channel);

                    if (resultIB == null || resultIB.isEmpty()) {
                        return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
                    } else {
                        MessageMB.MobileResultEnum msg = null;
                        response.setCifNo(cif);

                        for (int i = 0; i < resultIB.size(); i++) {
                            HashMap hm_Td = (HashMap) resultIB.get(i);
                            String usernameTemp = hm_Td.get("mobile_no").toString();
                            String cifTemp = hm_Td.get("cif_core").toString();
                            Helper.writeLog(getClass(), Level.INFO, "isExistUserBanking:" + usernameTemp + " " + cifTemp);
                            //Case 1: username & cif ton tai trên core
                            if (usernameTemp.equals(userName) && cifTemp.equals(cif)) {
                                response.setUserName(userName);
                                msg = MobileResultEnum.OK;
                                break;
                            } else if (!usernameTemp.equals(userName) && cifTemp.equals(cif)) {
                                if (usernameTemp.length() < 6 || usernameTemp.length() > 20) {
                                    msg = MobileResultEnum.OK;
                                } else {
                                    msg = MobileResultEnum.IB_NEWUSER_EXITCIF;
                                    response.setUserName(usernameTemp);
                                }
                                //break;
                            } else if (usernameTemp.equals(userName) && !cifTemp.equals(cif)) {
                                msg = MobileResultEnum.IB_NEWUSER_EXITUSER;
                                break;
                            }
                        }
                        response.getMBResponse(msg);
                    }
                }
            } else {
                response.getMBResponse(MobileResultEnum.ERROR_SYSTEM);
            }
            return Xml.Marshaller(response);
        } catch (Exception e) {
            logger.error(e);
            String error = "<ISEXISTUSERBANKINGRP> <ErrorCode>-99</ErrorCode> <ErrorMsg>Loi he thong.</ErrorMsg> </ISEXISTUSERBANKINGRP>";
            return error;
        }
    }

    /**
     * GetBankCardDetail
     *
     * @param xml
     * @return
     * @throws java.lang.Exception
     */
    public String GetBankCardDetail(String xml) throws Exception {
        try {
            BankCardDtlRp response = new BankCardDtlRp();
            BankCardDtlRq req = (BankCardDtlRq) Xml.unMarshaller(BankCardDtlRq.class,
                    xml);

            //validate token
            if (req.getAuthenType() != null && req.getAuthenType().equals("1")) {
                //xac thuc giao dich
                int isValidAuth = req.validateAuth();
                if (isValidAuth != _SUCESSFUL) {
                    return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.get(String.valueOf(isValidAuth))));
                }
            }

            //lay thong tin the
            String rsCardInfo = Helper.getDBI().GetVCardDetail(req.getAccountNo());
            //rsCardInfo = "00489518XXXXXX6717032129XXXX202202";

            if (rsCardInfo.length() < 33) {
                response.setErrorCode(CommonConstant.RESPONSE_FAILED);
                response.setErrorMessage("Gia tri tra ve tu api the khong dung dinh dang hoac khong tim thay thong tin the.");
                return Xml.Marshaller(response);
            }
            String vPanMask = rsCardInfo.substring(2, 18);
            String vMid = rsCardInfo.substring(18, 24);
            String vCValue = rsCardInfo.substring(24, 27);
            String issueDate = rsCardInfo.substring(27, 33);
            String soPane = vPanMask.substring(0, 6) + vMid + vPanMask.substring(12, 16);
            response.setCardNumber("".equals(CommonUtils.trim(soPane)) ? soPane : TripleDESCard.encrypt(soPane));
            response.setCvcNumber("".equals(CommonUtils.trim(vCValue)) ? vCValue : TripleDESCard.encrypt(vCValue));
            response.setIssuedOrExpiredDate("".equals(issueDate) ? "" : TripleDESCard.encrypt(issueDate.substring(4, 6) + "/" + issueDate.substring(0, 4)));
            response.setErrorCode(CommonConstant.RESPONSE_SUCCEED);
            response.setErrorMessage("Thong tin chi tiet the.");

            String encriptStr = Xml.Marshaller(response);

            return encriptStr;
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            BankCardDtlRp responseEx = new BankCardDtlRp();
            responseEx.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
            responseEx.setErrorMessage("Loi exception!");
            return Xml.Marshaller(responseEx);
        }
    }

    /*
     * Query detail info of customer
     * 
     * @param xml
     * @return
     */
    public String GetCustomerInfo2345(String xml) {
        try {
            CustomerRq req = (CustomerRq) Xml.unMarshaller(CustomerRq.class, xml);
            CustomerRp response = new CustomerRp();

            IIBCustomerService iibCustomerService = new IIBCustomerService();
            RetrieveCustomerRefDataMgmt_out_Type retrieveCustomerRefDataMgmt = iibCustomerService.retrieveCustomerRefDataMgmtSimple(Configuration.getIIBContext(), req.getRequestValue(), IIB_CHANNEL_MOBILE);

            retrieveCustomerRefDataMgmt.getCIFInfo().getCIFNum();
            if (!retrieveCustomerRefDataMgmt.getTransactionInfo().getTransactionReturn().equals(IIBConstants.TRANSACTION_SUCCESS)) {
                return Xml.Marshaller(response.getMBResponse(MobileResultEnum.NO_EXISTS_ACCOUNT));
            }

            CIFDataType cifData = retrieveCustomerRefDataMgmt.getCIFInfo();
            CustomerInfoType customerinfo = retrieveCustomerRefDataMgmt.getCustomerInfo();
            response.setIdcorporate(cifData.getCIFNum());
            response.setCodbranch(cifData.getBranchCode());
            response.setPostal_code(customerinfo.getIDInfo().getIDNum());
            response.setTxtcorpdesc(customerinfo.getFullname());
            response.setDate_of_birth(customerinfo.getBirthDay());
            response.setIssuedDate(customerinfo.getIDInfo().getIDIssuedDate());
            response.setSex(customerinfo.getGender());
            //customerinfo.getSegmentType()
            response.setAddress1(customerinfo.getAddress().getAddress_vn());

            return Xml.Marshaller(response.getMBResponse(MobileResultEnum.OK));
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

}
