package scb.com.vn.payment;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Level;
import scb.com.vn.common.model.payment.*;
import scb.com.vn.message.Message.PaymentResultEnum;
import scb.com.vn.payment.vnpay.billing.Payment;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import scb.com.vn.controller.Fcubs;
import scb.com.vn.utility.CommonUtils;
import vn.com.scb.bian.BillPaymentDetailType;
import vn.com.scb.bian.model.evnhn.PayBillEVNHN_in;
import vn.com.scb.bian.model.evnhn.PayBillEVNHN_out;
import vn.com.scb.bian.model.evnhn.SelectBillEVNHNInfo_in;
import vn.com.scb.bian.model.evnhn.SelectBillEVNHNInfo_out;
import vn.com.scb.bian.model.vnpt.PayBillVNPT_out;
import vn.com.scb.bian.model.vnpt.SelectBillVNPTInfo_out;
import vn.com.scb.bian.service.bill.IIBEVNHNService;
import vn.com.scb.bian.service.bill.IIBVNPTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import scb.com.vn.common.model.iibpayment.GetBillInfo_in;
import scb.com.vn.common.model.iibpayment.GetBillInfo_out;
import scb.com.vn.common.model.iibpayment.PayBill_inConvertType;
import scb.com.vn.common.model.iibpayment.PayBill_out;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.constant.VNPTConstant;
import scb.com.vn.dbi.dto.Pbl_partnercode;
import scb.com.vn.message.Message;
import scb.com.vn.model.BillPaymentRq;
import scb.com.vn.model.GetBillInfoRp;
import scb.com.vn.model.GetBillInfoRq;
import scb.com.vn.model.TxnCommitRp;
import scb.com.vn.model.billingvnpt.vnptMediaBillDetail;
import scb.com.vn.payment.model.vnpt.PaymentVNPTMediaResp;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.VnpayUtils;
import vn.com.scb.bian.model.evnhcm.PayBillEVNHCM_in;
import vn.com.scb.bian.model.evnhcm.PayBillEVNHCM_out;
import vn.com.scb.bian.model.evnhcm.SelectBillEVNHCMInfo_in;
import vn.com.scb.bian.model.evnhcm.SelectBillEVNHCMInfo_out;
import vn.com.scb.bian.service.bill.IIBBillingService;
import vn.com.scb.bian.service.bill.IIBEVNHCMService;

/**
 *
 * @author minhndb
 */
public class Controller {

    private static final Logger logger = Logger.getLogger(Controller.class);

    String IDPARTNER;
    String[] amount_topup_vnpay = {"10000", "20000", "30000", "50000", "100000", "200000", "300000", "500000"};

    /**
     *
     */
    protected static String[] servicecode = null;

    /**
     *
     */
    protected static String[] providercode = null;
    String wsurlvnpayaddress = Configuration.getProperty("ws.url.vnpay.address");
    String wsvnpayislive = Configuration.getProperty("ws.vnpay.islive");
    int wstimeoutvnpay = Integer.parseInt(Configuration.getProperty("ws.url.vnpay.timeout"));

    //Tham so BCN
    String wsurlbcnaddress = Configuration.getProperty("ws.url.bcn.address");
    int wstimeoutbcn = Integer.parseInt(Configuration.getProperty("ws.url.bcn.timeout"));
    String userbcn = Configuration.getProperty("bcn.user");
    String passbcn = Configuration.getProperty("bcn.pass");
    //Tham so EVNHN
    String wsurlevnhnaddress = Configuration.getProperty("ws.url.evnhn.address");
    int wstimeoutevnhn = Integer.parseInt(Configuration.getProperty("ws.url.evnhn.timeout"));
    String evnhn_sid = Configuration.getProperty("ws.evnhn.sid");
    String evnhn_bankId = Configuration.getProperty("ws.evnhn.bankId");
    String evnhn_posType = Configuration.getProperty("ws.evnhn.posType");
    String evnhn_posId = Configuration.getProperty("ws.evnhn.posId");
    String evnhn_pos_add = Configuration.getProperty("ws.evnhn.pos_add");
    String evnhn_bill_id = Configuration.getProperty("ws.evnhn.bill_id");
    String evnhn_invoice_print = Configuration.getProperty("ws.evnhn.invoice_print");
    String evnhn_provId = Configuration.getProperty("ws.evnhn.provId");
    String evnhn_listbranch = Configuration.getProperty("ws.evnhn.listbranch");
    List<String> arrEVNHNlistbranch = new ArrayList<String>(Arrays.asList(evnhn_listbranch.split(",")));

    //Tham so Banknet
    String ProductTransfer = Configuration.getProperty("fcubs.producttransfer.paybill");
    String wsurlbanknetaddress = Configuration.getProperty("ws.url.banknet.address");
    int wstimeoutbanknet = Integer.parseInt(Configuration.getProperty("ws.url.banknet.timeout"));
    String card_acceptor = Configuration.getProperty("card_Acceptor_Name");
    String term_id = Configuration.getProperty("term_id");
    String mti = Configuration.getProperty("mti");
    String privatekey = Configuration.getProperty("ws.testpayment.napas.privatekey");
    String publickey = Configuration.getProperty("ws.testpayment.napas.publickey");
    String proc_code_query = Configuration.getProperty("processing_code_query");
    String proc_code_payment = Configuration.getProperty("processing_code_payment");
    String merchant_Type;
    String acquiring_Code = Configuration.getProperty("acquiring_Code");
    String amount = "000000000000";
    Date date = new Date();
    SimpleDateFormat dt = new SimpleDateFormat("MMddHHmmss");
    String trans_date = dt.format(date);
    
    /**
     *
     */
    public static HashMap<String, String> hm_tetefirstnums = new HashMap<String, String>();

    //Tham so Payoo
    String wsurlPayooAddress = Configuration.getProperty("ws.url.payoo.address");
    String wsPayooIslive = Configuration.getProperty("ws.payoo.islive");
    int wstimeoutPayoo = Integer.parseInt(Configuration.getProperty("ws.url.payoo.timeout"));
    String wsPayooPrivateKeyPassword = Configuration.getProperty("ws.payoo.privatekey.password");
    String wsPayooClientPassword = Configuration.getProperty("ws.payoo.clientpassword");
    String wsPayooAgentID = Configuration.getProperty("ws.payoo.agentid");
    String wsPayooClientID = Configuration.getProperty("ws.payoo.clientid");
    KeyStore keyStore = Configuration.keyStore;
    X509Certificate publicCert = Configuration.publicCert;

    //Tham so EVN MIEN NAM
    String wsEvnspcUrladdress = Configuration.getProperty("ws.evnspc.url.address");
    int wstimeoutevnspc = Integer.parseInt(Configuration.getProperty("ws.evnspc.url.timeout"));
    String evnspc_bankId = Configuration.getProperty("ws.evnspc.bankId");
    String evnspc_invoice_print = Configuration.getProperty("ws.evnspc.invoice_print");
    String evnspc_servicecode = Configuration.getProperty("ws.evnspc.servicecode");
    String evnspc_listbranch = Configuration.getProperty("ws.evnspc.listbranch");
    List<String> arrEVNSPClistbranch = new ArrayList<String>(Arrays.asList(evnspc_listbranch.split(",")));

    //Tham so EVN MIEN NAM
    String wsEvncpcUrladdress = Configuration.getProperty("ws.evncpc.url.address");
    int wstimeoutevncpc = Integer.parseInt(Configuration.getProperty("ws.evncpc.url.timeout"));
    String evncpc_bankId = Configuration.getProperty("ws.evncpc.bankid");
    String evncpc_pass_conn = Configuration.getProperty("ws.evncpc.pass.conn");
    String evncpc_listbranch = Configuration.getProperty("ws.evncpc.listbranch");
    List<String> arrEVNCPClistbranch = new ArrayList<String>(Arrays.asList(evncpc_listbranch.split(",")));

    //Tham so dawaco    
    String dawacoWsUrladdress = Configuration.getProperty("ws.url.dawaco.address");
    int dawacowstimeout = Integer.parseInt(Configuration.getProperty("ws.url.dawaco.timeout"));
    String dawacoClientid = ControllerUtil.EncriptMD5(Configuration.getProperty("ws.dawaco.clientid"));
    String dawacoClientpassword = ControllerUtil.EncriptMD5(Configuration.getProperty("ws.dawaco.clientpassword"));
    String dawacoAgentCode = Configuration.getProperty("ws.dawaco.agentcode");

    // THAM SO EVN HCM
    private final String EVNHCM_NAME = "EVNHCM";
    String EVNHCM_URLADDRESS = Configuration.getProperty("ws.evnhcm.url.address");
    int WSTIMEOUT_EVNHCM = Integer.parseInt(Configuration.getProperty("ws.evnhcm.url.timeout"));
    int EVNHCM_BANKID = Integer.parseInt(Configuration.getProperty("ws.evnhcm.bankid"));
    String EVNHCM_MADIEMTHU = Configuration.getProperty("ws.evnhcm.madiemthu");
    String EVNHCM_MACN = Configuration.getProperty("ws.evnhcm.macn");

    private final String VNPT_NAME = "VNPT";
    private final String ATSIGN = "@";
    private final String NUMBERSIGN = "#";
    private final String PERCENT = "%";

    private final String VNPT_Media_Name = "VNPT_MEDIA";

    //BIDIWACO
    String bidiwacoPassword = Configuration.getProperty("bidiwaco.secretkey");
    String bidiwacoClientid = Configuration.getProperty("bidiwaco.partner_id");
    String bidiwacoURLAddress = Configuration.getProperty("bidiwaco.urladdress");
    int bidiwacoTimeout = Integer.parseInt("40");

    //Dnpwater
    String dnpwaterPassword = Configuration.getProperty("dnpwater.authorization");
    String dnpwaterClientid = Configuration.getProperty("dnpwater.company_code");
    String dnpwaterURLAddress = Configuration.getProperty("dnpwater.urladdress");
    int dnpwaterTimeout = Integer.parseInt("40");
    String dnpwaterKeyPath = Configuration.getProperty("dnpwater.key.path");

    /**
     *
     */
    public Controller() {
        loadListPartnerCode();
    }

    private void loadListPartnerCode() {
        try {
            if ((servicecode != null) && (providercode != null)) {
                return;
            }

            servicecode = null;
            providercode = null;

            // SERVICECODE
            ArrayList listServiceType = Helper.getDBI().getAllListServiceType();
            servicecode = new String[listServiceType.size()];
            for (int i = 0; i < listServiceType.size(); i++) {
                HashMap hm = (HashMap) listServiceType.get(i);
                servicecode[i] = ((String) hm.get("idservicetype"));
            }

            // PROVIDERCODE
            ArrayList listProvider = Helper.getDBI().getAllListProvider();
            providercode = new String[listProvider.size()];
            for (int i = 0; i < listProvider.size(); i++) {
                HashMap hm = (HashMap) listProvider.get(i);
                providercode[i] = ((String) hm.get("idprovider"));
            }
        } catch (Exception ex) {
            throw new PaymentException(ex);
        }
    }

    /**
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public String requestPayment(String xml) throws Exception {
        //1. Check valid input para
        RequestPayment requestpay;
        try {
            requestpay = ControllerUtil.unMarshallerPayment(xml);
        } catch (Exception ex) {
            Helper.writeLogErrorNonDB(Controller.class, "Loi check xml. Msg error: " + PaymentResultEnum.REQUESTPARAM_INVALID.getValue());
            return ControllerUtil.createResponseFromResultCode(PaymentResultEnum.REQUESTPARAM_INVALID.getValue());
        }

        String errorvalue = ControllerUtil.checkPreValidation(requestpay, servicecode, providercode);
        if (errorvalue != null) {
            Helper.writeLogErrorNonDB(Controller.class, "Loi khi check PreValidation. Msg error:" + PaymentResultEnum.REQUESTPARAM_INVALID.getValue() + " errorvalue:" + errorvalue);
            return ControllerUtil.createResponseFromResultCode(PaymentResultEnum.REQUESTPARAM_INVALID.getValue());
        }

        //3 Get IDPARTNER        
        this.IDPARTNER = ControllerUtil.getIdPartner(requestpay.getServicecode(), requestpay.getProvidercode());
        if (this.IDPARTNER == null) {
            Helper.writeLogErrorNonDB(Controller.class, "Loi get idpartner. :" + PaymentResultEnum.PARTNER_INVALID.getValue());
            return ControllerUtil.createResponseFromResultCode(PaymentResultEnum.PARTNER_INVALID.getValue());
        }

        if (requestpay.getProcessingcode().equals("QUERY")) {
            ResponsePayment r = requestQuery(requestpay);
            return ControllerUtil.createResponse(r);
        }
        if (requestpay.getProcessingcode().equals("PAY")) {
            ResponsePayment r = requestPay(requestpay);
            return ControllerUtil.createResponse(r);
        }

        return ControllerUtil.createResponseFromResultCode(PaymentResultEnum.PROCESSINGCODE_NOTEXSIST.getValue());
    }

    /* 
     * 17/10/2013: Phan luong cho giao dich Payoo
     * + Thuc hien luu cac thong tin BillID, Month of Bill, 
     * + vi he thong hien tai khac biet so voi chuan payoo
     * 26/03/2014: Nang cap he thong thanh toan tra truoc, ACS
     * 
     */
    private ResponsePayment requestQuery(RequestPayment requestpay) throws RemoteException, Exception {
        ResponsePayment _rp = new ResponsePayment();
        IPayment payment = null;

        mapIDPartnerForServices(requestpay);
        List<Pbl_partnercode> partnerCode = new ArrayList<>();
        Pbl_partnercode partnerInfo = null;
        if (this.IDPARTNER.equals("VNPAY") || this.IDPARTNER.equals("PAYOO")) {
            partnerCode = Helper.getDBI().getPblPartnerCode(IDPARTNER);
            partnerInfo = VnpayUtils.getPartnercode(partnerCode, requestpay);
        }

        if (VNPT_Media_Name.equals(this.IDPARTNER)) {
            GetBillInfoRq req = new GetBillInfoRq();
            GetBillInfoRp response = new GetBillInfoRp();
            req.setIdPartner(this.IDPARTNER);
            req.setIdprovider(requestpay.getProvidercode());
            req.setIdservicetype(requestpay.getServicecode());
            req.setCustomerCode(requestpay.getRequest().getBilling().getCustomercode());
            //req.setDataInsput(requestpay.getDataInput());
            req.setAddInfo(VNPTConstant.VNPT_SERVICE_TYPE_NOCUOC);
            req.setDataInput(requestpay.getDataInput());
            req.setChannel(requestpay.getChannel());//kenh tai quay
            // call api vnpt
            BillingVNPTMediaController billingVNPT = new BillingVNPTMediaController();
            String rs = billingVNPT.getBillingVNPTMedia(req, response, _rp);

            logger.info("Response call api vnpt: " + rs);
            _rp.setResultMessage(response.getErrorMsg());
            _rp.setResult(CommonConstant.RESPONSE_SUCCEED.equals(response.getErrorCode()) ? Message.PaymentResultEnum.OK.getValue() : response.getErrorCode());
            _rp.setProvidercode(requestpay.getProvidercode());

            return _rp;
        }

        if (this.IDPARTNER.equals("VNPAY")) {
            //Updated by Hieu, Get trans code from sequence, remove ramdom transcode

            String transcode = String.valueOf(getIdSeqByName("SQ_VNPAYREFNO"));
            payment = new scb.com.vn.payment.vnpay.billing.Payment(this.wsurlvnpayaddress, this.wsvnpayislive, this.wstimeoutvnpay, requestpay, transcode, partnerInfo.getServiceCode(), partnerInfo.getProviderCode());
            try {
                _rp = payment.requestQuery();

                _rp.getResponse().setIdpartner(this.IDPARTNER);
                return _rp;
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", new Object[]{payment.getResponse()}));
            }
        } else if (this.IDPARTNER.equals("PAYOO")) {
            //Need to be done
            //1. KHOI TAO KET NOI PAYOO: CHU KY, CLIENT ID, ...       
            /*payment = new scb.com.vn.payment.payoo.billing.Payment(this.wsurlPayooAddress, this.wsPayooIslive,
                    this.wstimeoutPayoo, this.keyStore, this.publicCert, this.wsPayooPrivateKeyPassword,
                    this.wsPayooClientPassword, this.wsPayooAgentID, this.wsPayooClientID, requestpay);*/
            payment = new scb.com.vn.payment.payoo.billing.Payment(this.wsurlPayooAddress,
                    this.wsPayooIslive,
                    this.wstimeoutPayoo,
                    this.keyStore,
                    this.publicCert,
                    this.wsPayooPrivateKeyPassword,
                    this.wsPayooClientPassword,
                    this.wsPayooAgentID,
                    this.wsPayooClientID,
                    requestpay,
                    partnerInfo.getServiceCode(),
                    partnerInfo.getProviderCode());

            try {
                _rp = payment.requestQuery();

                if (_rp.getResult().equalsIgnoreCase(PaymentResultEnum.OK.getValue()) && !requestpay.getServicecode().equalsIgnoreCase("VNTOPUP")) {
                    //Save ext information needed for payment later
                    String[] strTempArr = _rp.getResponse().getBilling().getCustomername().split("@");
                    if (strTempArr[4] != null
                            && insertPayooLog(_rp.getResponse().getBilling().getCustomercode(),
                                    requestpay.getServicecode(), requestpay.getProvidercode(),
                                    _rp.getResponse().getBilling().getCustomername()) == 1) {
                        //Sucess
                        //If prepaid, keep all the format.
                        if (strTempArr[2].equals("false")) {
                            if (!requestpay.getProvidercode().equals("SCTV6") && !requestpay.getProvidercode().equals("ACS")
                                    && !requestpay.getProvidercode().equals("PF")) {
                                _rp.getResponse().getBilling().setCustomername(strTempArr[0]);
                            }
                        }

                        _rp.getResponse().setIdpartner(this.IDPARTNER);
                        return _rp;
                    } else {
                        //Not sucess, return invalid partner
                        _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                        return _rp;
                    }
                } else if (_rp.getResult().equalsIgnoreCase(PaymentResultEnum.OK.getValue())) {
                    _rp.getResponse().setIdpartner(this.IDPARTNER);
                    return _rp;
                } else {
                    return _rp;
                }
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", new Object[]{payment.getResponse()}));
            }

        } else if (this.IDPARTNER.equals("BCN")) {
            payment = new scb.com.vn.payment.bcn.billing.Paymentbcn(this.wsurlbcnaddress, this.wstimeoutbcn, requestpay, userbcn, passbcn);
            try {
                _rp = payment.requestQuery();

                _rp.getResponse().setIdpartner(this.IDPARTNER);
                return _rp;
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", new Object[]{payment.getResponse()}));
            }
        } else if (this.IDPARTNER.equals("EVNHN")) {
            try {
                String customerCode = requestpay.getRequest().getBilling().getCustomercode().trim();
                String serviceCode = requestpay.getServicecode();
                String providerCode = requestpay.getProvidercode();
                String channel = requestpay.getChannel();
                SelectBillEVNHNInfo_in request = new SelectBillEVNHNInfo_in(customerCode, channel);
                IIBEVNHNService iIBEVNHNService = new IIBEVNHNService();
                logger.info("Request: " + request);
                SelectBillEVNHNInfo_out out = iIBEVNHNService.selectBillEVNHNInfo(Configuration.getIIBContext(), request);
                logger.info("Response: " + out);
                switch (out.getTransactionReturn()) {
                    case "1": // OK
                        StringBuilder str = new StringBuilder();
                        str.append(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerName())
                                .append(ATSIGN)
                                .append(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerCode())
                                .append(ATSIGN)
                                .append("false")
                                .append(ATSIGN)
                                .append("2") // BILL_PAYMENTRULE_ALL = "1", BILL_PAYMENTRULE_ORDER = "2", BILL_PAYMENTRULE_ANY = "3", BILL_PAYMENTRULE_AMOUNT = "5";
                                .append(ATSIGN);

                        List<BillPaymentDetailType> items = out.getGetBillInfo_out().getBillPaymentDetail();

                        for (int i = 0; i < items.size(); i++) {
                            if (i > 0) {
                                str.append(NUMBERSIGN);
                            }
                            str.append(items.get(i).getBillDetailId())
                                    .append(PERCENT)
                                    .append(items.get(i).getBillDetailMonth())
                                    .append(PERCENT)
                                    .append(items.get(i).getBillDetailMoneyAmount())
                                    .append(PERCENT)
                                    .append(items.get(i).getBillCustomerCode());
                        }

                        Response response = new Response();
                        Billing billing = new Billing();
                        Electric elc = new Electric();

                        billing.setCustomercode(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerCode());
                        billing.setAddress(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerAddress());
                        billing.setCurrentdebit("VND");
                        billing.setAmount(out.getGetBillInfo_out().getBillPaymentInfo().getBillPayAmount());
                        billing.setCustomername(str.toString());

                        elc.setName(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerName());
                        elc.setAddress(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerAddress());
                        elc.setAmount(out.getGetBillInfo_out().getBillPaymentInfo().getBillPayAmount());

                        response.setBilling(billing);
                        response.setElectric(elc);

                        _rp.setResult(PaymentResultEnum.OK.getValue());
                        _rp.setResponse(response);

                        if (insertPayooLog(customerCode, serviceCode, providerCode, str.toString()) == 1) {
                            _rp.getResponse().setIdpartner(this.IDPARTNER);
                        } else {
                            _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                        }
                        return _rp;
                    case "-77": // BILL_PAID
                        _rp.setResult(PaymentResultEnum.BILL_PAID.getValue());
                        break;
                    case "-79": // BILLCODE_INVALID
                        _rp.setResult(PaymentResultEnum.BILLCODE_INVALID.getValue());
                        break;
                    case "-97": // TIMEOUT
                        _rp.setResult(PaymentResultEnum.TIMEOUT.getValue());
                        break;
                    case "-99": // SYSTEM_ERROR
                    default:
                        _rp.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
                        break;
                }
                return _rp;
            } catch (Exception e) {
                logger.error(e);

            }
        } else if (this.IDPARTNER.equals(this.EVNHCM_NAME)) {
            try {
                String customerCode = requestpay.getRequest().getBilling().getCustomercode().toUpperCase().trim();
                String serviceCode = requestpay.getServicecode();
                String providerCode = requestpay.getProvidercode();
                String channel = requestpay.getChannel();

                SelectBillEVNHCMInfo_in request = new SelectBillEVNHCMInfo_in(customerCode, channel);
                IIBEVNHCMService iIBEVNHCMService = new IIBEVNHCMService();
                logger.info("Request: " + request);
                SelectBillEVNHCMInfo_out out = iIBEVNHCMService.selectBillEVNHCMInfo(Configuration.getIIBContext(), request);
                logger.info("Response: " + out);
                switch (out.getTransactionReturn()) {
                    case "1": // OK
                        StringBuilder str = new StringBuilder();
                        str.append(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerName())
                                .append(ATSIGN)
                                .append(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerCode())
                                .append(ATSIGN)
                                .append("false")
                                .append(ATSIGN)
                                .append("2") // BILL_PAYMENTRULE_ALL = "1", BILL_PAYMENTRULE_ORDER = "2", BILL_PAYMENTRULE_ANY = "3", BILL_PAYMENTRULE_AMOUNT = "5";
                                .append(ATSIGN);

                        List<BillPaymentDetailType> items = out.getGetBillInfo_out().getBillPaymentDetail();

                        for (int i = 0; i < items.size(); i++) {
                            if (i > 0) {
                                str.append(NUMBERSIGN);
                            }
                            str.append(items.get(i).getBillDetailId())
                                    .append(PERCENT)
                                    .append(items.get(i).getBillDetailMonth())
                                    .append(PERCENT)
                                    .append(items.get(i).getBillDetailMoneyAmount())
                                    .append(PERCENT)
                                    .append(items.get(i).getBillCustomerCode());
                        }

                        Response response = new Response();
                        Billing billing = new Billing();
                        Electric elc = new Electric();

                        billing.setCustomercode(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerCode());
                        billing.setAddress(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerAddress());
                        billing.setCurrentdebit("VND");
                        billing.setAmount(out.getGetBillInfo_out().getBillPaymentInfo().getBillPayAmount());
                        billing.setCustomername(str.toString());

                        elc.setName(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerName());
                        elc.setAddress(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerAddress());
                        elc.setAmount(out.getGetBillInfo_out().getBillPaymentInfo().getBillPayAmount());

                        response.setBilling(billing);
                        response.setElectric(elc);

                        _rp.setResult(PaymentResultEnum.OK.getValue());
                        _rp.setResponse(response);

                        if (insertPayooLog(customerCode, serviceCode, providerCode, str.toString()) == 1) {
                            _rp.getResponse().setIdpartner(this.IDPARTNER);
                        } else {
                            _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                        }
                        return _rp;
                    case "-77": // BILL_PAID
                        _rp.setResult(PaymentResultEnum.BILL_PAID.getValue());
                        break;
                    case "-79": // BILLCODE_INVALID
                        _rp.setResult(PaymentResultEnum.BILLCODE_INVALID.getValue());
                        break;
                    case "-97": // TIMEOUT
                        _rp.setResult(PaymentResultEnum.TIMEOUT.getValue());
                        break;
                    case "-110": // GACH NO KO THANH CONG. CHO TRA SOAT
                        _rp.setResult(PaymentResultEnum.CANNOT_PAYMENT_NOT_REFUND.getValue());
                        break;
                    case "-99": // SYSTEM_ERROR
                    default:
                        _rp.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
                        break;
                }
                return _rp;
            } catch (Exception e) {
                logger.error(e);
            }
        } else if (this.IDPARTNER.equals("EVNSPC")) {
            //EVN Mien Nam khong dung transcode de truy van            

            //Set chu hoa truoc khi chuyen sang EVN SPC
            String strCustcode = requestpay.getRequest().getBilling().getCustomercode();
            requestpay.getRequest().getBilling().setCustomercode(strCustcode.toUpperCase());

            //Kiem tra trong ds da co trong he thong thanh toan chua.            
            String branch_code = requestpay.getRequest().getBilling().getCustomercode().substring(0, 6);
            if (!arrEVNSPClistbranch.contains(branch_code)) {
                Helper.writeLogError(Controller.class, "Ma Don vi EVNSPC chua map");
                _rp.setResult(PaymentResultEnum.NO_CUSTOMER_EXISTS.getValue());
                return _rp;
            }

            payment = new scb.com.vn.payment.evnspc.billing.Payment(this.wsEvnspcUrladdress, Integer.parseInt(this.evnspc_servicecode),
                    Integer.parseInt(evnspc_bankId), wstimeoutevnspc, "", requestpay);
            try {
                _rp = payment.requestQuery();

                if (_rp.getResult().equalsIgnoreCase(PaymentResultEnum.OK.getValue())) {
                    //Save ext information needed for payment later
                    String strTempArr = _rp.getResponse().getBilling().getCustomername();
                    if (strTempArr != null
                            && insertPayooLog(_rp.getResponse().getBilling().getCustomercode(),
                                    requestpay.getServicecode(), requestpay.getProvidercode(),
                                    _rp.getResponse().getBilling().getCustomername()) == 1) {

                        _rp.getResponse().setIdpartner(this.IDPARTNER);
                        return _rp;
                    } else {
                        //Not sucess, return invalid partner
                        _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                        return _rp;
                    }
                } else if (_rp.getResult().equalsIgnoreCase(PaymentResultEnum.OK.getValue())) {
                    _rp.getResponse().setIdpartner(this.IDPARTNER);
                    return _rp;
                } else {
                    return _rp;
                }
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", new Object[]{payment.getResponse()}));
            }
        } else if (this.IDPARTNER.equals("BANKNET")) {
            String transcode = String.valueOf(getIdSeqByName("SEQ_NAPAS"));
            hm_tetefirstnums = Helper.getDBI().getlist_telefirstnumber();
            if (requestpay.getChannel().equals("COUNTER")) {
                merchant_Type = Configuration.getProperty("merchant_Type_Counter");
            } else if (requestpay.getChannel().equals("INTERNET")) {
                merchant_Type = Configuration.getProperty("merchant_Type_Ebank");
            } else {
                merchant_Type = Configuration.getProperty("merchant_Type_Mobile");
            }
            payment = new scb.com.vn.payment.napas.billing.PaymentNapas(this.wsurlbanknetaddress, this.wstimeoutbanknet, requestpay, transcode, card_acceptor,
                    term_id, mti, proc_code_query, merchant_Type, acquiring_Code, trans_date, amount, publickey, privatekey, hm_tetefirstnums);
            try {
                _rp = payment.requestQuery();
                _rp.getResponse().setIdpartner(this.IDPARTNER);
                return _rp;
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", new Object[]{payment.getResponse()}));
            }
        } else if (this.IDPARTNER.equals("EVNCPC")) {

            //EVN Mien trung khong dung transcode de truy van                                    
            String strCustcode = requestpay.getRequest().getBilling().getCustomercode();
            requestpay.getRequest().getBilling().setCustomercode(strCustcode.toUpperCase());

            //Kiem tra trong ds da co trong he thong thanh toan chua.
            String branch_code = requestpay.getRequest().getBilling().getCustomercode().substring(0, 6);
            if (!arrEVNCPClistbranch.contains(branch_code)) {
                Helper.writeLogError(Controller.class, "Ma Don vi EVNCPC chua map");
                _rp.setResult(PaymentResultEnum.NO_CUSTOMER_EXISTS.getValue());
                return _rp;
            }

            //String wsaddress, int service_code, int bankid, int timeoutmilis, 
            //String transcode, String strMaDiemThu, String strBranchID, 
            //String strChannelID, RequestPayment requestpay
            payment = new scb.com.vn.payment.evncpc.billing.Payment(this.wsEvncpcUrladdress, 0,
                    Integer.parseInt(evncpc_bankId), wstimeoutevncpc, "", "000",
                    "000", "IB", evncpc_pass_conn, requestpay);
            try {
                _rp = payment.requestQuery();

                if (_rp.getResult().equalsIgnoreCase(PaymentResultEnum.OK.getValue())) {
                    //Save ext information needed for payment later
                    String strTempArr = _rp.getResponse().getBilling().getCustomername();
                    if (strTempArr != null
                            && insertPayooLog(_rp.getResponse().getBilling().getCustomercode(),
                                    requestpay.getServicecode(), requestpay.getProvidercode(),
                                    _rp.getResponse().getBilling().getCustomername()) == 1) {

                        _rp.getResponse().setIdpartner(this.IDPARTNER);
                        return _rp;
                    } else {
                        //Not sucess, return invalid partner
                        _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                        return _rp;
                    }
                } else if (_rp.getResult().equalsIgnoreCase(PaymentResultEnum.OK.getValue())) {
                    _rp.getResponse().setIdpartner(this.IDPARTNER);
                    return _rp;
                } else {
                    return _rp;
                }
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", new Object[]{payment.getResponse()}));
            }
        } else if (this.IDPARTNER.equals("CHOBINHDIEN")) {
            return requestQueryCHOBINHDIEN(requestpay, _rp, payment);
        } else if (this.IDPARTNER.equals("DAWACO")) {
            //Updated by Hieu, Get trans code from sequence, remove ramdom transcode
            String transcode = String.valueOf(getIdSeqByName("SQ_DAWACOREFNO"));
            Helper.writeLog(this.getClass(), Level.WARN, "BEGIN: " + this.dawacoWsUrladdress);
            payment = new scb.com.vn.payment.dawaco.billing.Payment(this.dawacoWsUrladdress, this.dawacowstimeout, this.dawacoClientid, this.dawacoClientpassword, this.dawacoAgentCode, requestpay, transcode);
            try {
                _rp = payment.requestQuery();
                Helper.writeLog(this.getClass(), Level.WARN, "END requestQuery: " + this.dawacoWsUrladdress);
                if (_rp.getResult().equalsIgnoreCase(PaymentResultEnum.OK.getValue())) {
                    //Save ext information needed for payment later
                    String strTempArr = _rp.getResponse().getBilling().getCustomername().concat("#").concat(_rp.getResponse().getBilling().getAddinfo());
                    if (strTempArr != null
                            && insertPayooLog(_rp.getResponse().getBilling().getCustomercode(),
                                    requestpay.getServicecode(), requestpay.getProvidercode(),
                                    strTempArr) == 1) {

                        _rp.getResponse().setIdpartner(this.IDPARTNER);
                        return _rp;
                    } else {
                        //Not sucess, return invalid partner
                        _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                        return _rp;
                    }
                } else {
                    return _rp;
                }
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", new Object[]{payment.getResponse()}));
            }

        } else if (VNPT_NAME.equals(this.IDPARTNER)) {
            /* Code VNPT tai day */
            try {
                String transId = CommonUtils.getJulianFromDate() + String.valueOf(getIdSeqByName("SQ_VNPTREFNO"));
                String customerCode = requestpay.getRequest().getBilling().getCustomercode().trim();
                String serviceCode = requestpay.getServicecode();
                String providerCode = requestpay.getProvidercode();
                String channel = requestpay.getChannel();
                String province = Helper.getDBI().getProviderOriginalCode(serviceCode, providerCode);
                IIBVNPTService iIBVNPTService = new IIBVNPTService();
                SelectBillVNPTInfo_out out = iIBVNPTService.selectBillVNPTInfo(Configuration.getIIBContext(),
                        transId, customerCode, province, channel);
                Helper.writeLog(this.getClass(), Level.INFO, out.toString());

                switch (out.getTransactionReturn()) {
                    case "1": // OK
                        Response response = new Response();
                        Billing billing = new Billing();
                        Electric elc = new Electric();

                        billing.setCustomercode(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerCode());
                        billing.setAddress(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerAddress());
                        billing.setCurrentdebit("VND");

                        // log for table pbl_log
                        billing.setCustomername(out.getGetBillInfo_out().getBillPaymentInfo().getBillMoreInfomation());

                        billing.setAmount(out.getGetBillInfo_out().getBillPaymentInfo().getBillPayAmount());
                        response.setBilling(billing);

                        elc.setName(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerName());
                        elc.setAddress(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerAddress());
                        elc.setAmount(out.getGetBillInfo_out().getBillPaymentInfo().getBillPayAmount());
                        response.setElectric(elc);

                        _rp.setResult(PaymentResultEnum.OK.getValue());
                        _rp.setResponse(response);
                        //String customerName = _rp.getResponse().getBilling().getCustomername();
                        if (insertPayooLog(customerCode, serviceCode, providerCode, out.getGetBillInfo_out().getBillPaymentInfo().getBillMoreInfomation()) == 1) {
                            _rp.getResponse().setIdpartner(this.IDPARTNER);
                        } else {
                            _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                        }
                        break;
                    case "-77": // BILL_PAID
                        // Không nợ cước.
                        _rp.setResult(PaymentResultEnum.BILL_PAID.getValue());
                        break;
                    case "-79": // BILLCODE_INVALID
                        // Không tồn tại Thông tin KH
                        _rp.setResult(PaymentResultEnum.BILLCODE_INVALID.getValue());
                        break;
                    case "-97": // TIMEOUT
                        _rp.setResult(PaymentResultEnum.TIMEOUT.getValue());
                        break;
                    case "-99": // SYSTEM_ERROR
                    default:
                        _rp.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
                        break;
                }
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
            }
            return _rp;
        } else if (this.IDPARTNER.equals("BIDIWACO") || this.IDPARTNER.equals("DNPWATER")) {
            /*
            payment = new scb.com.vn.payment.bidiwaco.billing.Payment(this.bidiwacoURLAddress, this.bidiwacoTimeout, this.bidiwacoClientid, this.bidiwacoPassword, requestpay, null);
            _rp = payment.requestQuery();
            _rp.getResponse().setIdpartner(this.IDPARTNER);*/
            try {
                String customerCode = requestpay.getRequest().getBilling().getCustomercode().trim();
                String serviceCode = requestpay.getServicecode();
                String providerCode = requestpay.getProvidercode();
                String channel = requestpay.getChannel();

                IIBBillingService iib = new IIBBillingService();
                GetBillInfo_in request = iib.createQueryRequest(customerCode, channel);

                logger.info(this.IDPARTNER + " Get bill Request: " + customerCode);
                GetBillInfo_out out = iib.getBillInfo(Configuration.getIIBContext(), request, this.IDPARTNER.toLowerCase());

                String returnCode = String.valueOf(out.getGetBillInfo_out().getTransactionInfo().getTransactionReturn() == 1 ? 0 : out.getGetBillInfo_out().getTransactionInfo().getTransactionReturn());
                logger.info(this.IDPARTNER + " Get bill Response " + customerCode + ":" + returnCode);
                _rp.setResult(String.valueOf(returnCode));

                if (returnCode.equals(PaymentResultEnum.OK.getValue())) {

                    StringBuilder str = new StringBuilder();
                    str.append(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerName())
                            .append(ATSIGN)
                            .append(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerCode())
                            .append(ATSIGN)
                            .append("false")
                            .append(ATSIGN)
                            .append("3") // BILL_PAYMENTRULE_ANY = "3"
                            .append(ATSIGN);

                    List<scb.com.vn.common.model.iibpayment.BillPaymentDetailType> items = out.getGetBillInfo_out().getBillPaymentDetail();

                    for (int i = 0; i < items.size(); i++) {
                        if (i > 0) {
                            str.append(NUMBERSIGN);
                        }
                        str.append(items.get(i).getBillDetailId())
                                .append(PERCENT)
                                .append(items.get(i).getBillDetailMonth())
                                .append(PERCENT)
                                .append(items.get(i).getBillDetailMoneyAmount())
                                .append(PERCENT)
                                .append(items.get(i).getBillCustomerCode())
                                .append(PERCENT);
                    }

                    Response response = new Response();
                    Billing billing = new Billing();

                    billing.setCustomercode(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerCode());
                    billing.setAddress(out.getGetBillInfo_out().getBillPaymentInfo().getBillCustomerAddress());
                    billing.setCurrentdebit("VND");
                    billing.setAmount(out.getGetBillInfo_out().getBillPaymentInfo().getBillPayAmount());
                    billing.setCustomername(str.toString());
                    billing.setAddinfo(out.getGetBillInfo_out().getBillPaymentInfo().getBillMoreInfomation());

                    response.setBilling(billing);

                    _rp.setResponse(response);

                    if (insertPayooLog(customerCode, serviceCode, providerCode, str.toString()) == 1) {
                        _rp.getResponse().setIdpartner(this.IDPARTNER);
                    } else {
                        _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());

                    }
                }
            } catch (Exception e) {
                logger.error(e);

            }

        }/* else if (this.IDPARTNER.equals("DNPWATER")) {
            payment = new scb.com.vn.payment.dnpwater.billing.Payment(this.dnpwaterURLAddress, this.dnpwaterTimeout, this.dnpwaterClientid, this.dnpwaterPassword, requestpay, dnpwaterKeyPath, null);
            _rp = payment.requestQuery();
            _rp.getResponse().setIdpartner(this.IDPARTNER);
        } */ else {
            _rp.setResult(PaymentResultEnum.PARTNER_INVALID.getValue());
        }
        return _rp;
    }

    private ResponsePayment requestQueryCHOBINHDIEN(RequestPayment requestpay, ResponsePayment _rp,
            IPayment payment) throws RemoteException {

        //Lay thong tin bills: strCustomername @ Bill Ngan han @ Buil dai han
        //BuildID
        //Phi ngắn hạn
        //Phi mat bang + Phi quan ly
        //Ngay het han thanh toán
        //Phi dien nuoc
        //1000000%30112016#500000
        //Phí dài hạn:
        //2000000%30112016#800000        
        //KET NOI DICH VU CHO BINH DIEN, LAY DANH SACH BILLS            
        String strCust1 = "BD123456";
        String strCust1Name = "NGUYEN VAN A";
        String strCust1Bills = "BIN001%1000000%30-11-2016#BIN002%500000"
                + "@BID001%2000000%30-11-2016#BID002%800000";

        String strCust2 = "BD123457";
        String strCust2Name = "NGUYEN THI C";
        String strCust2BillsKy1 = "BID003%500000%30-11-2016#BID004%200000";
        String strCust2BillsKy2 = "BID005%600000%30-11-2016#BID006%250000";

        try {

            String strCustcode = requestpay.getRequest().getBilling().getCustomercode();

            //customercode = customercode + '#' + idmaovua + '#' + idkythanhtoan;
            String[] arrTemp = strCustcode.split("#");
            requestpay.getRequest().getBilling().setCustomercode(arrTemp[0]);

            Billing b = new Billing();

            if (arrTemp[0].equalsIgnoreCase(strCust1)) {
                b.setCustomername(arrTemp[0] + "@" + strCust1Name + "@" + arrTemp[1] + "@" + arrTemp[2] + "@" + strCust1Bills);
                b.setAddress("TP.HCM");
                b.setAmount("4300000");
                _rp.setResult(PaymentResultEnum.OK.getValue());
            } else if (arrTemp[0].equalsIgnoreCase(strCust2)) {
                if (arrTemp[2].equalsIgnoreCase("Ky1")) {
                    b.setCustomername(arrTemp[0] + "@" + strCust2Name + "@" + arrTemp[1] + "@" + arrTemp[2] + "@" + strCust2BillsKy1);
                    b.setAddress("TP.HCM");
                    b.setAmount("70000");

                } else {
                    b.setCustomername(arrTemp[0] + "@" + strCust2Name + "@" + arrTemp[1] + "@" + arrTemp[2] + "@" + strCust2BillsKy2);
                    b.setAddress("TP.HCM");
                    b.setAmount("850000");
                }
                _rp.setResult(PaymentResultEnum.OK.getValue());
            } else {
                _rp.setResult(PaymentResultEnum.BILL_NOTEXIST.getValue());
            }

            _rp.getResponse().setBilling(b);

            //_rp = payment.requestQuery();
            _rp.getResponse().setIdpartner(this.IDPARTNER);
            return _rp;
        } catch (Exception ex) {
            Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
            _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
            return _rp;
        } finally {
            //Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{payment.getRequest()}));
            //Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", new Object[]{payment.getResponse()}));
        }
    }

    private ResponsePayment requestPaymentCHOBINHDIEN(RequestPayment requestpay, ResponsePayment _rp,
            IPayment payment) throws RemoteException {

        //String invoiceNo = "BDP" + String.valueOf(getIdSeqByName("SQ_CHOBINHDIENREFNO"));;
        String[] strTempArr = requestpay.getRequest().getBilling().getCustomername().split("@");

        //Lay ma KH
        String strCustCode = strTempArr[0];
        String strBills = strTempArr[1];

        //payment = new Payment(this.wsurlvnpayaddress, this.wsvnpayislive, this.wstimeoutvnpay, requestpay, transcode);
        try {
            //_rp = payment.requestPay();

            //Set billing
            Billing b = new Billing();
            b.setCustomername(strTempArr[1]);
            b.setAddress("TP.HCM");
            b.setAmount(requestpay.getRequest().getBilling().getAmount());

            _rp.getResponse().setBilling(b);

            _rp.setResult(PaymentResultEnum.OK.getValue());
            _rp.getResponse().setIdpartner(this.IDPARTNER);
            return _rp;
        } catch (Exception ex) {
            Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE PAY-Loi: %1$s]", new Object[]{ex}));
            _rp.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
            return _rp;
        } finally {
            //Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST PAY-%1$s]", new Object[]{payment.getRequest()}));
            //Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE PAY-%1$s]", new Object[]{payment.getResponse()}));
        }
    }

    private ResponsePayment requestPay(RequestPayment requestpay) throws Exception {
        IPayment payment = null;
        ResponsePayment _rp = new ResponsePayment();
        mapIDPartnerForServices(requestpay);
        List<Pbl_partnercode> partnerCode = new ArrayList<>();
        Pbl_partnercode partnerInfo = null;
        if (this.IDPARTNER.equals("VNPAY") || this.IDPARTNER.equals("PAYOO")) {
            partnerCode = Helper.getDBI().getPblPartnerCode(IDPARTNER);
            partnerInfo = VnpayUtils.getPartnercode(partnerCode, requestpay);
        }

        if (VNPT_Media_Name.equals(this.IDPARTNER)) {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            Gson gson = builder.create();
            BillPaymentRq billRequest = new BillPaymentRq();
            //GetBillInfoRp response = new GetBillInfoRp();
            billRequest.setIdprovider(requestpay.getProvidercode());
            billRequest.setIdservicetype(requestpay.getServicecode());
            billRequest.setCustomerCode(requestpay.getRequest().getBilling().getCustomercode());           
            billRequest.setOptions(requestpay.getDataInput());
            billRequest.setPayAmt(requestpay.getRequest().getBilling().getAmount());
            vnptMediaBillDetail billDtl = new vnptMediaBillDetail();
            billDtl.setAMOUNT(requestpay.getRequest().getBilling().getAmount());
            billRequest.setBillDetails("[" + gson.toJson(billDtl) + "]");
            billRequest.setChannel(requestpay.getChannel());// kenh tai quay
            billRequest.setVNPTMediaType("IN");
            billRequest.setAddInfo(VNPTConstant.VNPT_SERVICE_TYPE_NOCUOC);           
            // call api vnpt
            BillingVNPTMediaController billingVNPT = new BillingVNPTMediaController();
            PaymentVNPTMediaResp rs = billingVNPT.payBillingVNPTMedia_TQ(billRequest, _rp, requestpay.getTranscode());
            _rp.getResponse().setIdpartner(this.IDPARTNER);
            _rp.setProvidercode(requestpay.getProvidercode());
            _rp.setChannel(requestpay.getChannel());
            _rp.setAccountno(requestpay.getAccountno());           
            Billing billing = requestpay.getRequest().getBilling();
            _rp.getResponse().setBilling(billing);

            logger.info("Response call api vnpt: " + gson.toJson(rs));

            return _rp;
        }

        if (this.IDPARTNER.equals("VNPAY")) {
            String transcode = String.valueOf(getIdSeqByName("SQ_VNPAYREFNO"));
            payment = new Payment(this.wsurlvnpayaddress, this.wsvnpayislive, this.wstimeoutvnpay, requestpay, transcode, partnerInfo.getServiceCode(), partnerInfo.getProviderCode());
            try {
                _rp = payment.requestPay();
                _rp.getResponse().setIdpartner(this.IDPARTNER);
                return _rp;
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE PAY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST PAY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE PAY-%1$s]", new Object[]{payment.getResponse()}));
            }
        } else if (this.IDPARTNER.equals("PAYOO")) {
            //Need to be done
            //1. KHOI TAO KET NOI PAYOO: CHU KY, CLIENT ID, ... 
            /* payment = new scb.com.vn.payment.payoo.billing.Payment(this.wsurlPayooAddress, this.wsPayooIslive,
                    this.wstimeoutPayoo, this.keyStore, this.publicCert, this.wsPayooPrivateKeyPassword,
                    this.wsPayooClientPassword, this.wsPayooAgentID, this.wsPayooClientID, requestpay);*/
            payment = new scb.com.vn.payment.payoo.billing.Payment(this.wsurlPayooAddress, this.wsPayooIslive,
                    this.wstimeoutPayoo, this.keyStore, this.publicCert, this.wsPayooPrivateKeyPassword,
                    this.wsPayooClientPassword, this.wsPayooAgentID, this.wsPayooClientID, requestpay, partnerInfo.getServiceCode(), partnerInfo.getProviderCode());

            try {
                //Gan them trans code de tra soat khi can  
                String invoiceNo = "PYP" + String.valueOf(getIdSeqByName("SQ_PAYOOREFNO"));

                if (!requestpay.getServicecode().equalsIgnoreCase("VNTOPUP")) {
                    String currCustomerName = "";
                    //Lay loai dich vu tra truoc hay tra sau tu customer name

                    //Neu la tra truoc: Lay du lieu thanh toan tu customer name
                    if (requestpay.getRequest().getBilling().getCustomername().contains("@")) {
                        currCustomerName = invoiceNo + "@" + requestpay.getRequest().getBilling().getCustomername();
                    } //Neu la tra sau: Lay du lieu live tu database
                    else {
                        //Gan cac gia tri theo chuan cua Payoo
                        String payooLog = getPbl_PayooLog(requestpay.getRequest().getBilling().getCustomercode(),
                                requestpay.getServicecode(), requestpay.getProvidercode());

                        currCustomerName = invoiceNo + "@" + payooLog;
                    }

                    requestpay.getRequest().getBilling().setCustomername(currCustomerName);
                } else {
                    String mobileno = requestpay.getRequest().getVntopup().getMobnoreq();
                    requestpay.getRequest().getVntopup().setMobnoreq(mobileno + "#" + invoiceNo);
                }

                //Gui y/c thanh toan sang Payoo
                _rp = payment.requestPay();
                _rp.getResponse().setIdpartner(this.IDPARTNER);
                return _rp;
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE PAY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST PAY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE PAY-%1$s]", new Object[]{payment.getResponse()}));
            }

        } else if (this.IDPARTNER.equals("BCN")) {
            payment = new scb.com.vn.payment.bcn.billing.Paymentbcn(this.wsurlbcnaddress, this.wstimeoutbcn, requestpay, userbcn, passbcn);
            try {
                _rp = payment.requestPay();

                _rp.getResponse().setIdpartner(this.IDPARTNER);
                return _rp;
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", new Object[]{payment.getResponse()}));
            }
        } else if (this.IDPARTNER.equals("EVNHN")) {
            String billTransCode = CommonUtils.getJulianFromDate() + String.valueOf(getIdSeqByName("SQ_EVNHNREFNO"));
            String clientCode = requestpay.getChannel();
            String idBillPaid = "";
            List<BillPaymentDetailType> details = new ArrayList<>();

            String data = requestpay.getRequest().getBilling().getCustomername();
            String[] datas = data.split(ATSIGN);
            if (datas.length >= 5 && !datas[4].isEmpty()) {
                String[] items = datas[4].split(NUMBERSIGN);
                for (String item : items) {
                    String[] values = item.split(PERCENT);
                    if (values.length >= 4) {
                        BillPaymentDetailType detail = new BillPaymentDetailType();
                        detail.setBillDetailId(values[0]);
                        detail.setBillDetailMonth(values[1]);
                        detail.setBillDetailMoneyAmount(values[2]);
                        detail.setBillCustomerCode(values[3]);
                        details.add(detail);
                    } else {
                        logger.warn("values.length = " + values.length + ", Cannot process because invalid value");
                        _rp.setResult(PaymentResultEnum.CANNOT_PAYMENT_REFUNDED.getValue());
                        return _rp;
                    }
                }

                if (details.size() > 0) {
                    PayBillEVNHN_in req = new PayBillEVNHN_in(billTransCode, clientCode, idBillPaid, details);
                    IIBEVNHNService iIBEVNHNService = new IIBEVNHNService();
                    PayBillEVNHN_out out = iIBEVNHNService.payBillEVNHN(Configuration.getIIBContext(), req);
                    _rp.setTranscode(out.getBillTransCode());
                    Response response = new Response();
                    Electric elc = new Electric();
                    Billing bill = new Billing();
                    elc.setName(requestpay.getRequest().getBilling().getCustomername());
                    elc.setAddress(requestpay.getRequest().getBilling().getAddress());
                    elc.setAmount(requestpay.getRequest().getBilling().getAmount());
                    response.setBilling(bill);
                    response.setElectric(elc);
                    response.setIdpartner(this.IDPARTNER);
                    _rp.setResponse(response);

                    switch (out.getTransactionReturn()) {
                        case "1":
                            _rp.setResult(PaymentResultEnum.OK.getValue());
                            break;
                        case "-120":
                            _rp.setResult(PaymentResultEnum.BILLCODE_INCORRECT.getValue());
                            break;
                        case "-110":
                        default:
                            _rp.setResult(PaymentResultEnum.TIMEOUT.getValue());
                            break;
                    }
                    return _rp;
                } else {
                    logger.warn("Cannot process because details size = " + details.size());
                    _rp.setResult(PaymentResultEnum.CANNOT_PAYMENT_REFUNDED.getValue());
                    return _rp;
                }
            } else {
                _rp.setResult(PaymentResultEnum.CANNOT_PAYMENT_REFUNDED.getValue());
                logger.warn("Invalid request value. datas = [" + Arrays.toString(datas) + "] is not enough length or datas[4].isEmpty()");
                return _rp;
            }
        } else if (this.IDPARTNER.equals(this.EVNHCM_NAME)) {
            try {
                String billTransCode = CommonUtils.getJulianFromDate() + String.valueOf(getIdSeqByName("SQ_EVNHCMREFNO"));
                String clientCode = requestpay.getChannel();
                String paymentMethod = requestpay.getPaymentmethod();
                String idBillPaid = "";
                List<BillPaymentDetailType> details = new ArrayList<>();
                String data = requestpay.getRequest().getBilling().getCustomername();
                String[] datas = data.split(ATSIGN);
                if (datas.length >= 5 && !datas[4].isEmpty()) {
                    String[] items = datas[4].split(NUMBERSIGN);
                    for (String item : items) {
                        String[] values = item.split(PERCENT);
                        if (values.length >= 4) {
                            BillPaymentDetailType detail = new BillPaymentDetailType();
                            detail.setBillDetailId(values[0]);
                            detail.setBillDetailMonth(values[1]);
                            detail.setBillDetailMoneyAmount(values[2]);
                            detail.setBillCustomerCode(values[3]);
                            details.add(detail);
                        } else {
                            logger.warn("values.length = " + values.length + ", Cannot process because invalid value");
                            _rp.setResult(PaymentResultEnum.CANNOT_PAYMENT_REFUNDED.getValue());
                            return _rp;
                        }
                    }

                    if (details.size() > 0) {
                        IIBEVNHCMService iIBEVNHCMService = new IIBEVNHCMService();
                        /* Kiem tra danh sach co theo thu tu hay ko */
                        String customerCode = details.get(0).getBillCustomerCode();
                        String channel = requestpay.getChannel();
                        if ("MOBILE".equals(channel) || "COUNTER".equals(channel)) {
                            SelectBillEVNHCMInfo_in request = new SelectBillEVNHCMInfo_in(customerCode, channel);
                            SelectBillEVNHCMInfo_out out = iIBEVNHCMService.selectBillEVNHCMInfo(Configuration.getIIBContext(), request);
                            if ("1".equals(out.getTransactionReturn())) {
                                List<BillPaymentDetailType> itemDetails = out.getGetBillInfo_out().getBillPaymentDetail();
                                for (int i = 0; i < details.size(); i++) {
                                    if (!details.get(i).getBillDetailId().equals(itemDetails.get(i).getBillDetailId()) || !details.get(i).getBillDetailMoneyAmount().equals(itemDetails.get(i).getBillDetailMoneyAmount())) {
                                        logger.warn("MaKH = [" + customerCode + "], thuc hien hoan tien vi khong the process thanh toan bill vi khach hang ko chon thanh toan theo thu tu. Bill select thu [" + (i + 1) + "] = [" + details.get(i).getBillDetailId() + "] != Bill [" + itemDetails.get(i).getBillDetailId() + "], Amount = [" + details.get(i).getBillDetailMoneyAmount() + "] != Amount original [" + itemDetails.get(i).getBillDetailMoneyAmount() + "]");
                                        _rp.setTranscode("");
                                        Response response = new Response();
                                        Electric elc = new Electric();
                                        Billing bill = new Billing();

                                        bill.setCustomername(requestpay.getRequest().getBilling().getCustomername());
                                        bill.setAddress(requestpay.getRequest().getBilling().getAddress());
                                        bill.setAmount(requestpay.getRequest().getBilling().getAmount());

                                        elc.setName(requestpay.getRequest().getBilling().getCustomername());
                                        elc.setAddress(requestpay.getRequest().getBilling().getAddress());
                                        elc.setAmount(requestpay.getRequest().getBilling().getAmount());

                                        response.setBilling(bill);
                                        response.setElectric(elc);
                                        response.setIdpartner(this.IDPARTNER);
                                        _rp.setResponse(response);

                                        _rp.setResult(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue());
                                        return _rp;
                                    }
                                }
                                logger.warn("MaKH = [" + customerCode + "] --> Da kiem tra va khach hang chon dung thu tu hoa don");
                            } else {
                                logger.warn("MaKH = [" + customerCode + "], thuc hien hoan tien vi khong the process thanh toan bill do ko tim thay no cuoc. Transaction return = [" + out.getTransactionReturn() + "]");

                                _rp.setTranscode("");
                                Response response = new Response();
                                Electric elc = new Electric();
                                Billing bill = new Billing();

                                bill.setCustomername(requestpay.getRequest().getBilling().getCustomername());
                                bill.setAddress(requestpay.getRequest().getBilling().getAddress());
                                bill.setAmount(requestpay.getRequest().getBilling().getAmount());

                                elc.setName(requestpay.getRequest().getBilling().getCustomername());
                                elc.setAddress(requestpay.getRequest().getBilling().getAddress());
                                elc.setAmount(requestpay.getRequest().getBilling().getAmount());

                                response.setBilling(bill);
                                response.setElectric(elc);
                                response.setIdpartner(this.IDPARTNER);
                                _rp.setResponse(response);

                                _rp.setResult(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue());
                                return _rp;
                            }
                        } else {
                            logger.info("MaKH = [" + customerCode + "], Khong phai kenh MOBILE va TAI QUAY nen skip validate. Kenh = [" + channel + "]");
                        }

                        PayBillEVNHCM_in req = new PayBillEVNHCM_in(billTransCode, clientCode, idBillPaid, paymentMethod, details);
                        PayBillEVNHCM_out out = iIBEVNHCMService.payBillEVNHCM(Configuration.getIIBContext(), req);
                        _rp.setTranscode(out.getBillTransCode());
                        Response response = new Response();
                        Electric elc = new Electric();
                        Billing bill = new Billing();

                        bill.setCustomername(requestpay.getRequest().getBilling().getCustomername());
                        bill.setAddress(requestpay.getRequest().getBilling().getAddress());
                        bill.setAmount(requestpay.getRequest().getBilling().getAmount());

                        elc.setName(requestpay.getRequest().getBilling().getCustomername());
                        elc.setAddress(requestpay.getRequest().getBilling().getAddress());
                        elc.setAmount(requestpay.getRequest().getBilling().getAmount());

                        response.setBilling(bill);
                        response.setElectric(elc);
                        response.setIdpartner(this.IDPARTNER);
                        _rp.setResponse(response);

                        switch (out.getTransactionReturn()) {
                            case "1":
                                _rp.setResult(PaymentResultEnum.OK.getValue());
                                break;
                            case "-166":
                                _rp.setResult(PaymentResultEnum.BILLCODE_INCORRECT.getValue());
                                break;
                            case "-110": // GACH NO KO THANH CONG. CHO TRA SOAT
                                _rp.setResult(PaymentResultEnum.CANNOT_PAYMENT_NOT_REFUND.getValue());
                                break;
                            case "-98":
                            default:
                                _rp.setResult(PaymentResultEnum.TIMEOUT.getValue());
                                break;
                        }
                        return _rp;
                    } else {
                        logger.warn("Cannot process because details size = " + details.size());
                        _rp.setResult(PaymentResultEnum.CANNOT_PAYMENT_REFUNDED.getValue());
                        return _rp;
                    }
                } else {
                    _rp.setResult(PaymentResultEnum.CANNOT_PAYMENT_REFUNDED.getValue());
                    logger.warn("Invalid request value. datas = [" + Arrays.toString(datas) + "] is not enough length or datas[4].isEmpty()");
                    return _rp;
                }
            } catch (Exception e) {
                logger.error(e);
            }
        } else if (this.IDPARTNER.equals("EVNSPC")) {
            try {
                String strCustcode = requestpay.getRequest().getBilling().getCustomercode();
                requestpay.getRequest().getBilling().setCustomercode(strCustcode.toUpperCase());

                //Gan them trans code de tra soat khi can  
                //Ktra kenh giao dich
                //Ref: 2 : ký tự đầu kenh + Ma giao dich (YYYYMMDD + Ref Sequence) + 10 Ky tu Hoa don ID                                                
                String evnspcLog = getPbl_PayooLog(requestpay.getRequest().getBilling().getCustomercode(),
                        requestpay.getServicecode(), requestpay.getProvidercode());

                String invoiceNo = initEVNSPCTranscode(evnspcLog, requestpay);
                String billCodes = getBillCode(evnspcLog, requestpay);
                payment = new scb.com.vn.payment.evnspc.billing.Payment(this.wsEvnspcUrladdress, Integer.parseInt(this.evnspc_servicecode),
                        Integer.parseInt(evnspc_bankId), wstimeoutevnspc, billCodes, invoiceNo, requestpay);

                //Format: Bill ID % Bill Code % Amount % #                                                
                requestpay.getRequest().getBilling().setCustomername(evnspcLog);

                //Gui y/c thanh toan sang Payoo
                _rp = payment.requestPay();
                _rp.getResponse().setIdpartner(this.IDPARTNER);
                return _rp;
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE PAY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST PAY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE PAY-%1$s]", new Object[]{payment.getResponse()}));
            }
        } else if (this.IDPARTNER.equals("BANKNET")) {
            String transcode = String.valueOf(getIdSeqByName("SEQ_NAPAS"));
            hm_tetefirstnums = Helper.getDBI().getlist_telefirstnumber();
            if (requestpay.getChannel().equals("COUNTER")) {
                merchant_Type = Configuration.getProperty("merchant_Type_Counter");
            } else if (requestpay.getChannel().equals("INTERNET")) {
                merchant_Type = Configuration.getProperty("merchant_Type_Ebank");
            } else {
                merchant_Type = Configuration.getProperty("merchant_Type_Mobile");
            }
            if (requestpay.getServicecode().equals("VNTOPUP")) {
                amount = requestpay.getRequest().getVntopup().getAmount() + "00";
            } else {
                amount = requestpay.getRequest().getBilling().getAmount() + "00";
            }
            payment = new scb.com.vn.payment.napas.billing.PaymentNapas(this.wsurlbanknetaddress, this.wstimeoutbanknet, requestpay, transcode, card_acceptor,
                    term_id, mti, proc_code_payment, merchant_Type, acquiring_Code, trans_date, amount, publickey, privatekey, hm_tetefirstnums);
            try {
                _rp = payment.requestPay();
                _rp.getResponse().setIdpartner(this.IDPARTNER);
                return _rp;
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", new Object[]{payment.getResponse()}));
            }
        } else if (this.IDPARTNER.equals("EVNCPC")) {
            try {
                String strCustcode = requestpay.getRequest().getBilling().getCustomercode();
                requestpay.getRequest().getBilling().setCustomercode(strCustcode.toUpperCase());

                //Lay so ref theo cac hoa don ID
                String evncpcLog = getPbl_PayooLog(requestpay.getRequest().getBilling().getCustomercode(),
                        requestpay.getServicecode(), requestpay.getProvidercode());

                int intNoOfBills = evncpcLog.split("#").length;

                String trace_no = "";

                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdt = new SimpleDateFormat("yyMMdd");
                String dt = sdt.format(c.getTime());

                for (int i = 0; i < intNoOfBills; i++) {
                    String seq = String.valueOf(getIdSeqByName("SQ_EVNCPCREFNO"));
                    if (trace_no.equals("")) {
                        trace_no = dt + seq;
                    } else {
                        trace_no = trace_no + "#" + dt + seq;
                    }
                }

                //Thiet lap thong so Buils
                requestpay.getRequest().getBilling().setCustomername(evncpcLog);

                payment = new scb.com.vn.payment.evncpc.billing.Payment(this.wsEvncpcUrladdress, 0,
                        Integer.parseInt(evncpc_bankId), wstimeoutevncpc, trace_no, "000",
                        "000", requestpay.getChannel(), evncpc_pass_conn, requestpay);

                //Gui y/c thanh toan sang evn mien trung.
                _rp = payment.requestPay();
                _rp.getResponse().setIdpartner(this.IDPARTNER);
                return _rp;
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE PAY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST PAY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE PAY-%1$s]", new Object[]{payment.getResponse()}));
            }
        } else if (this.IDPARTNER.equals("CHOBINHDIEN")) {
            return requestPaymentCHOBINHDIEN(requestpay, _rp, payment);
        } else if (this.IDPARTNER.equals("DAWACO")) {
            String transcode = String.valueOf(getIdSeqByName("SQ_DAWACOREFNO"));
            String[] billinfo = getPbl_PayooLog(requestpay.getRequest().getBilling().getCustomercode(),
                    requestpay.getServicecode(), requestpay.getProvidercode()).split("#");

            requestpay.getRequest().getBilling().setCustomername(billinfo[0]);
            requestpay.getRequest().getBilling().setAddinfo(billinfo[1]);
            payment = new scb.com.vn.payment.dawaco.billing.Payment(this.dawacoWsUrladdress, this.dawacowstimeout, this.dawacoClientid, this.dawacoClientpassword, this.dawacoAgentCode, requestpay, transcode);
            try {
                _rp = payment.requestPay();

                _rp.getResponse().setIdpartner(this.IDPARTNER);
                return _rp;
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.ERROR_PARTNER.getValue());
                return _rp;
            } finally {
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", new Object[]{payment.getRequest()}));
                Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", new Object[]{payment.getResponse()}));
            }
        } else if (VNPT_NAME.equals(this.IDPARTNER)) {
            try {
                String serviceCode = requestpay.getServicecode();
                String providerCode = requestpay.getProvidercode();
                String customerCode = requestpay.getRequest().getBilling().getCustomercode().trim();
                String partner = Helper.getDBI().getPartnerCode(serviceCode, providerCode);
                String information = getPbl_PayooLog(customerCode, serviceCode, providerCode);
                if (!information.isEmpty()) {
                    String transId = CommonUtils.getJulianFromDate() + String.valueOf(getIdSeqByName("SQ_VNPTREFNO"));
                    String billAmount = requestpay.getRequest().getBilling().getAmount();
                    String channel = requestpay.getChannel();
                    IIBVNPTService iIBVNPTService = new IIBVNPTService();
                    PayBillVNPT_out out = iIBVNPTService.payBillVNPT(Configuration.getIIBContext(),
                            transId, customerCode, billAmount, information, channel);

                    _rp.setTranscode(transId + "#" + out.getTransactionReturnMsg());

                    Response response = new Response();
                    Electric elc = new Electric();
                    elc.setName(out.getBillCustomerName());
                    elc.setAddress(out.getBillCustomerAddress() != null ? out.getBillCustomerAddress() : "");
                    elc.setAmount(out.getBillPayAmount());
                    response.setElectric(elc);
                    response.setIdpartner(partner);
                    _rp.setResponse(response);

                    switch (out.getTransactionReturn()) {
                        case "1": // Thanh cong
                            _rp.setResult(PaymentResultEnum.OK.getValue());
                            break;
                        case "-120": // That bai --> Hoan tien
                            _rp.setResult(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue());
                            break;
                        case "-110": // Hold tien
                        default:
                            _rp.setResult(PaymentResultEnum.TIMEOUT.getValue());
                            break;
                    }
                    return _rp;
                } /*
                else if (this.IDPARTNER.equals("DNPWATER")) {
                    String Addinfo = requestpay.getRequest().getBilling().getAddinfo();
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayList<DnpwaterBillDetail> listBillDetail = mapper.readValue(Addinfo, mapper.getTypeFactory().constructCollectionType(List.class, DnpwaterBillDetail.class));

                    String[] arrBillRequest = requestpay.getRequest().getBilling().getCustomername().split("@");
                    String[] listBillRequest = arrBillRequest[4].split("#");

                    Date dDate = new Date();
                    String paymentdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dDate);;//yyyyMMdd
                    List<DnpwaterBillPayDetail> listBillPayDetal = new ArrayList<DnpwaterBillPayDetail>();
                    String refpartner = "";
                    for (int j = 0; j < listBillRequest.length; j++) {
                        String[] bills = listBillRequest[j].split("%");
                        int bill_id = Integer.valueOf(bills[0]);
                        String bill_date = bills[1];
                        String bill_amount = bills[2];
                        String bill_no = bills[4];
                        for (int i = 0; i < listBillDetail.size(); i++) {
                            DnpwaterBillDetail billdetail = listBillDetail.get(i);
                            if (billdetail.getBilling_id() == bill_id) {
                                String transcode = String.valueOf(getIdSeqByName("SQ_DNPWATERREFNO"));
                                refpartner = refpartner + transcode + "#" + bill_id + "@";
                                DnpwaterBillPayDetail billPayDetail = new DnpwaterBillPayDetail();
                                billPayDetail.setBilling_id(bill_id);
                                billPayDetail.setBilling_code(billdetail.getBilling_code());
                                billPayDetail.setCustomer_code(billdetail.getCustomer_code());
                                billPayDetail.setCustomer_address(billdetail.getCustomer_address());
                                billPayDetail.setPeriod(billdetail.getPeriod());
                                billPayDetail.setAmount_total(Integer.valueOf(billdetail.getAmount_total()));
                                billPayDetail.setState(billdetail.getState());
                                billPayDetail.setCompany_code(billdetail.getCompany_code());
                                billPayDetail.setCustomer_name(billdetail.getCustomer_name());

                                String channel = requestpay.getChannel();
                                if (channel.equals("COUNTER")) {
                                    channel = "TELLER";
                                }
                                billPayDetail.setPay_date_time(paymentdate);
                                billPayDetail.setLocal_date_time(paymentdate);
                                billPayDetail.setChannel(channel);
                                billPayDetail.setAccount_no(requestpay.getAccountno());
                                billPayDetail.setBank_name("SCB");
                                billPayDetail.setTrace(transcode);

                                listBillPayDetal.add(billPayDetail);
                            }
                        }

                    }

                    payment = new scb.com.vn.payment.dnpwater.billing.Payment(this.dnpwaterURLAddress, this.dnpwaterTimeout, this.dnpwaterClientid, this.dnpwaterPassword, requestpay, dnpwaterKeyPath, listBillPayDetal);
                    _rp = payment.requestPay();

                    _rp.setTranscode(refpartner);
                    _rp.getResponse().setIdpartner(this.IDPARTNER);

                }*/ else {
                    _rp.setResult(PaymentResultEnum.BILLCODE_INCORRECT.getValue());
                }
            } catch (Exception ex) {
                Helper.writeLogError(Controller.class, String.format("[TYPE-MSG]-[RESPONSE QUERY-Loi: %1$s]", new Object[]{ex}));
                _rp.setResult(PaymentResultEnum.SYSTEM_ERROR.getValue());
                return _rp;
            }
        } else if (this.IDPARTNER.equals("BIDIWACO") || this.IDPARTNER.equals("DNPWATER")) {
            BigDecimal total_amount = new BigDecimal(requestpay.getRequest().getBilling().getAmount());
            String billTransCode = CommonUtils.getJulianFromDate() + String.valueOf(getIdSeqByName("SQ_BILLINGREFNO"));
            String clientCode = requestpay.getChannel();
            String idBillPaid = "";
            List<scb.com.vn.common.model.iibpayment.BillPaymentDetailType> details = new ArrayList<>();

            String data = requestpay.getRequest().getBilling().getCustomername();
            String[] datas = data.split(ATSIGN);
            String moreinfo = requestpay.getRequest().getBilling().getAddinfo();
            if (datas.length >= 5 && !datas[4].isEmpty()) {
                String[] items = datas[4].split(NUMBERSIGN);
                for (String item : items) {
                    String[] values = item.split(PERCENT);
                    if (values.length >= 4) {
                        scb.com.vn.common.model.iibpayment.BillPaymentDetailType detail = new scb.com.vn.common.model.iibpayment.BillPaymentDetailType();
                        detail.setBillDetailId(values[0]);
                        detail.setBillDetailMonth(values[1]);
                        detail.setBillDetailMoneyAmount(values[2]);
                        detail.setBillCustomerCode(values[3]);
                        details.add(detail);
                    } else {
                        logger.warn("values.length = " + values.length + ", Cannot process because invalid value");
                        _rp.setResult(PaymentResultEnum.CANNOT_PAYMENT_REFUNDED.getValue());
                        return _rp;
                    }
                }
                if (details.size() > 0) {
                    IIBBillingService iib = new IIBBillingService();
                    PayBill_inConvertType req = iib.createPayRequest(billTransCode, String.valueOf(total_amount), details, moreinfo, clientCode);
                    //PayBillEVNHN_in req = new PayBillEVNHN_in(billTransCode, clientCode, idBillPaid, details);

                    PayBill_out out = iib.payBill(Configuration.getIIBContext(), req, this.IDPARTNER.toLowerCase());

                    _rp.getResponse().setBilling(requestpay.getRequest().getBilling());
                    _rp.setTranscode(billTransCode);
                    _rp.setResult(String.valueOf(out.getPayBill_out().getTransactionInfo().getTransactionReturn() == 1 ? 0 : out.getPayBill_out().getTransactionInfo().getTransactionReturn()));
                    _rp.getResponse().setIdpartner(this.IDPARTNER);
                    _rp.setServicecode(requestpay.getServicecode());

                    String rpXml = Xml.Marshaller(_rp);
                    logger.warn(this.IDPARTNER + " ResponsePayment: " + rpXml);
                    return _rp;
                } else {
                    logger.warn("Cannot process because details size = " + details.size());
                    _rp.setResult(PaymentResultEnum.CANNOT_PAYMENT_REFUNDED.getValue());
                    return _rp;
                }
            } else {
                _rp.setResult(PaymentResultEnum.CANNOT_PAYMENT_REFUNDED.getValue());
                logger.warn("Invalid request value. datas = [" + Arrays.toString(datas) + "] is not enough length or datas[4].isEmpty()");
                return _rp;
            }

            /*
                    // String Addinfo = requestpay.getRequest().getBilling().getAddinfo();
                    //  BidiwacoBill bill = new ObjectMapper().readValue(Addinfo, BidiwacoBill.class);
                    
                    BidiwacoPayment bidiwacoBill = new BidiwacoPayment();

                    BigDecimal total_amount = new BigDecimal(requestpay.getRequest().getBilling().getAmount());

                    String[] arrBillRequest = requestpay.getRequest().getBilling().getCustomername().split("@");
                    String[] listBillRequest = arrBillRequest[4].split("#");
                    int total_rows = listBillRequest.length;

                    // List<BidiwacoBillDetail> listbill= bill.getData();
                    String customerid = requestpay.getRequest().getBilling().getCustomercode();
                    Date dDate = new Date();
                    String paymentdate = new SimpleDateFormat("yyyyMMddHHmmss").format(dDate);;//yyyyMMdd
                    List<BidiwacoPaymentDetail> listBillDetal = new ArrayList<BidiwacoPaymentDetail>();
                    String refpartner = "";
                    for (int j = 0; j < listBillRequest.length; j++) {
                        String[] bills = listBillRequest[j].split("%");
                        String invoice_id = bills[0];
                        String invoice_date = bills[1];
                        String invoice_amount = bills[2];
                        String invoice_no = bills[4];;
      
                        String transcode = String.valueOf(getIdSeqByName("SQ_BIDIWACOREFNO"));
                        refpartner = refpartner + transcode + "#" + invoice_id + "@";
                        BidiwacoPaymentDetail billDetail = new BidiwacoPaymentDetail();
                        billDetail.setPayment_id(transcode);
                        billDetail.setInvoice_id(invoice_id);
                        billDetail.setPayment_amount(invoice_amount);
                        billDetail.setInvoice_customer(customerid);
                        billDetail.setInvoice_no(invoice_no);
                        billDetail.setInvoice_date(invoice_date);
                        billDetail.setPayment_date(paymentdate);
                        billDetail.setPayment_comment("");
                        listBillDetal.add(billDetail);
                    }
                    bidiwacoBill.setTotal_rows(String.valueOf(total_rows));
                    bidiwacoBill.setTotal_amount(String.valueOf(total_amount));
                    bidiwacoBill.setPayment_data(listBillDetal);
                    payment = new scb.com.vn.payment.bidiwaco.billing.Payment(this.bidiwacoURLAddress, this.bidiwacoTimeout, this.bidiwacoClientid, this.bidiwacoPassword, requestpay, bidiwacoBill);
                    _rp = payment.requestPay();
                    _rp.setTranscode(refpartner);
                    _rp.getResponse().setIdpartner(this.IDPARTNER);
                    
             */
        }
        _rp.setResult(PaymentResultEnum.PARTNER_INVALID.getValue());
        return _rp;
    }

    private String initEVNSPCTranscode(String evnspcLog, RequestPayment requestpay) {
        String result = "";

        //Lay giao dich cua KH, loai tru Ten KH, Dia chi KH
        String temp[] = evnspcLog.split("@");

        for (String s : temp[2].split("#")) {
            //Ref: 2 : ký tự đầu kenh + Ma giao dich (YYYYMMDD + Ref Sequence) + 10 Ky tu Hoa don ID

            String channel = requestpay.getChannel();

            if (channel.equalsIgnoreCase("INTERNET")) {
                channel = "IT";
            } else if (channel.equalsIgnoreCase("MOBILE")) {
                channel = "IT";
            } else {
                channel = "TM";
            }

            //Lay ngay thang nam
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdt = new SimpleDateFormat("yyMMdd");
            String dt = sdt.format(c.getTime());

            //Lay so ref do SCB tu phat sinh
            String seq = String.valueOf(getIdSeqByName("SQ_EVNSPCREFNO"));

            //Lay Ma hoa don            
            String[] strBill = s.split("%");
            String hoadonID = strBill[0];

            //Tao ra so Ref theo format de gui cho EVN SPC
            result = result + channel + dt + seq + hoadonID + "#";
        }

        return result;
    }

    private String getBillCode(String evnspcLog, RequestPayment requestpay) {
        String billCodes = "";

        //Lay giao dich cua KH, loai tru Ten KH, Dia chi KH
        String temp[] = evnspcLog.split("@");

        for (String s : temp[2].split("#")) {
            String[] strBill = s.split("%");
            String billCode = strBill[1] + "#";
            billCodes += billCode;
        }

        return billCodes;
    }

    private int insertPayooLog(String customercode, String serviceID, String providerID, String billInfo) {
        //Luu cac thong tin BillID, Month of Bill 
        try {
            return Helper.getDBI().insertPbl_PayooLog(customercode.toUpperCase(), serviceID, providerID, billInfo);
        } catch (Exception ex) {
            throw new PaymentException(ex);
        }
    }

    private void mapIDPartnerForServices(RequestPayment requestpay) {
        if (requestpay.getProvidercode().equalsIgnoreCase("EVNHN")) {
            //Parse first 4 digits
            //PD17007956825                        
            String areaCode = requestpay.getRequest().getBilling().getCustomercode().substring(0, 4).toUpperCase();
            if (!requestpay.getChannel().equals("ONLPAYMENT")) {
                if (arrEVNHNlistbranch.contains(areaCode)) {
                    this.IDPARTNER = "EVNHN";
                } else {
                    this.IDPARTNER = "PAYOO";
                }
            }
        }
        /*
        //ADD NEW BY LYDTY
        if (requestpay.getProvidercode().equalsIgnoreCase("EVNSPC")) {
            //Parse first 6 digits
            //PD17007956825                        
            String areaCode = requestpay.getRequest().getBilling().getCustomercode().substring(0, 6).toUpperCase();

            if (arrEVNSPClistbranch.contains(areaCode)) {
                this.IDPARTNER = "EVNSPC";
            } else {
                this.IDPARTNER = "PAYOO";
            }
        }*/
    }

    private String getPbl_PayooLog(String customercode, String serviceID, String providerID) {
        //Luu cac thong tin BillID, Month of Bill
        try {
            String billinfo = Helper.getDBI().getBillInfo(customercode.toUpperCase(), serviceID, providerID);
            if (billinfo == null) {
                Helper.writeLog(this.getClass(), Level.WARN, "ROUND 1: CUSTOMERCODE = [" + customercode
                        + "}, SERVICECODE = [" + serviceID + "], PROVIDERCODE = ["
                        + providerID + "] DOES NOT FOUND IN TABLE PAYOO LOG");
                billinfo = Helper.getDBI().getBillInfo(customercode.toUpperCase(), serviceID, providerID);
                if (billinfo == null) {
                    Helper.writeLog(this.getClass(), Level.WARN, "ROUND 2: CUSTOMERCODE = [" + customercode
                            + "}, SERVICECODE = [" + serviceID + "], PROVIDERCODE = ["
                            + providerID + "] DOES NOT FOUND IN TABLE PAYOO LOG");
                    billinfo = Helper.getDBI().getBillInfo(customercode.toUpperCase(), serviceID, providerID);
                    if (billinfo == null) {
                        throw new PaymentException("ROUND 3: CUSTOMERCODE = [" + customercode
                                + "}, SERVICECODE = [" + serviceID + "], PROVIDERCODE = ["
                                + providerID + "] DOES NOT FOUND IN TABLE PAYOO LOG");
                    }
                }
            }
            return billinfo;
        } catch (Exception ex) {
            throw new PaymentException(ex);
        }
    }

    private int getIdSeqByName(String seqname) {
        try {
            return Helper.getDBI().getIdSeqByName(seqname);
        } catch (Exception ex) {
            throw new PaymentException(ex);
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
     * @param serviceCode
     * @return
     * @throws Exception
     */
    public String BANKNETREFUND(String merchantid, String TransID, String RefundTransID, BigDecimal Amount, String CCY, String AddInfo, String Localdatatime, String serviceCode) throws Exception {
        //get account Partner
        String accfrom = "";
        if (serviceCode.equals("VNTOPUP") || serviceCode.equals("GAMETOPUP")) {
            accfrom = Configuration.getProperty("napas.collated.accfrom.vntopup");
        } else {
            accfrom = Configuration.getProperty("napas.collated.accfrom.billing");
        }

        //Check dieu kien refund
        String[] ArrayReult = Helper.getDBI().CheckRefundTransferBanknet("BANKNET", merchantid, TransID,
                RefundTransID, Amount, CCY, AddInfo, Localdatatime);
        String StatusCode = ArrayReult[0].split("#")[0];
        if (StatusCode.equals("00")) {
            String useridfcubs = Configuration.getProperty("fcubs.userid");
            String BranchCode = ArrayReult[0].split("#")[1];
            String AccCustomer = ArrayReult[1];
            String BankTransID = ArrayReult[2];
            // Thuc hien chuyen khoan
            String IDProvider = ArrayReult[3];
            String CustomerCode = ArrayReult[4];
            Fcubs fc = new Fcubs();
            String Desc = "NAPAS HOAN TIEN DO THANH TOAN HOA DON KHONG THANH CONG." + "." + IDProvider
                    + "." + CustomerCode;
            Helper.writeLog(Controller.class, Level.INFO, accfrom + "#" + AccCustomer + "#" + Amount + "#" + Desc);
            //String CoreRef = fc.transferFCUBSWithProduct(ProductTransfer, accfrom, AccCustomer, Amount, Desc);
            String CoreRef = fc.transferFCUBSWithTimeOut(ProductTransfer, useridfcubs, "000", accfrom, BranchCode, AccCustomer, Amount, Desc, 40);
            Helper.writeLog(Controller.class, Level.INFO, CoreRef);
            if (CoreRef == null) {
                StatusCode = "04";
            }
            Helper.getDBI().UpdateRefundTransferNAPAS(BankTransID, CoreRef, StatusCode);
        }
        return StatusCode;
    }

    private static String getChannelEVNHN(RequestPayment request) {
        try {
            String channel = request.getChannel();
            String paymentmethod = request.getPaymentmethod();
            if (channel.equals("INTERNET") || channel.equals("MOBILE") || channel.equals("ONLPAYMENT") || channel.equals("ATM") || channel.equals("SMS")) {
                return "IB";
            } else if (channel.equals("COUNTER") & paymentmethod.equals("ACCOUNT")) {
                return "CK";
            } else if (channel.equals("COUNTER") & !paymentmethod.equals("ACCOUNT")) {
                return "TQ";
            } else if (channel.equals("AUTOPAYBILL")) {
                return "TD";
            }
            return "IB";
        } catch (Exception ex) {
            return "IB";
        }
    }

    /*
     private ResponsePayment requestPay(RequestPayment requestpay) {
     ResponsePayment responsepay = new ResponsePayment();
     scb.com.vn.payment.IPayment payvnpay = null;
     try {
     //0. SET IDPARTNER
     responsepay.getResponse().setIdpartner(IDPARTNER);

     //1. Truy van theo doi tac da lay duoc tu database
     if (IDPARTNER.equals("VNPAY")) {
     payvnpay = new scb.com.vn.payment.vnpay.billing.Payment(wsurlvnpayaddress, wsvnpayislive, wstimeoutvnpay, requestpay);
     ResponsePayment _rp = payvnpay.requestPay();

     Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST PAY-%1$s]", payvnpay.getRequest()));
     Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE PAY-%1$s]", payvnpay.getResponse()));
     return _rp;
     }

     responsepay.setResult(PaymentResultEnum.PARTNER_INVALID.getValue());
     return responsepay;

     } catch (Exception ex) {
     Helper.writeLog(Controller.class, Level.ERROR, String.format("[TYPE-MSG]-[RESPONSE PAY-%1$s]", ex));
     responsepay.setResult(PaymentResultEnum.PARTNER_INVALID.getValue());
     return responsepay;
     }
     }
     */

 /*
     private ResponsePayment requestQuery(RequestPayment requestpay) {
     ResponsePayment responsepay = new ResponsePayment();
     Response resp = new Response();
     responsepay.setResponse(resp);

     try {
     scb.com.vn.payment.IPayment payvnpay = null;

     //1. Truy van theo doi tac da lay duoc tu database
     if (IDPARTNER.equals("VNPAY")) {
     payvnpay = new scb.com.vn.payment.vnpay.billing.Payment(wsurlvnpayaddress, wsvnpayislive, wstimeoutvnpay, requestpay);
     ResponsePayment _rp = payvnpay.requestQuery();

     Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[REQUEST QUERY-%1$s]", payvnpay.getRequest()));
     Helper.writeLog(Controller.class, Level.INFO, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", payvnpay.getResponse()));
     return _rp;
     }

     responsepay.setResult(PaymentResultEnum.PARTNER_INVALID.getValue());
     return responsepay;

     } catch (Exception ex) {
     Helper.writeLog(Controller.class, Level.ERROR, String.format("[TYPE-MSG]-[RESPONSE QUERY-%1$s]", ex));
     responsepay.setResult(PaymentResultEnum.PARTNER_INVALID.getValue());
     return responsepay;
     }
     }
     */
    private static String getAddinfoBill(String billid, String moreinfo) {
        String[] list = moreinfo.split("#");
        for (int i = 0; i < list.length; i++) {
            if (list[i].contains(billid)) {
                String[] detail = list[i].split("%");
                return detail[1] + "%" + detail[2];
            }
        }
        return "";
    }
}
