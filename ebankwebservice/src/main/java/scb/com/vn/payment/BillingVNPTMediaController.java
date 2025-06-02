/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.payment.Billing;
import scb.com.vn.common.model.payment.Response;
import scb.com.vn.common.model.payment.ResponsePayment;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.constant.VNPTConstant;
import scb.com.vn.controller.Fcubs;
import scb.com.vn.controller.MobileController;
import static scb.com.vn.controller.MobileController.getPaymentMsgEnum;
import scb.com.vn.dbi.dto.PblBillpaid;
import scb.com.vn.dbi.dto.PblLog;
import scb.com.vn.dbi.dto.PblProvider;
import scb.com.vn.dbi.dto.PblServicetype;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.message.Message;
import scb.com.vn.model.BillPaymentRq;
import scb.com.vn.model.GetBillInfoRp;
import scb.com.vn.model.GetBillInfoRq;
import scb.com.vn.model.TxnCommitRp;
import scb.com.vn.model.billingvnpt.AddInfoRes;
import scb.com.vn.model.billingvnpt.DATA_OUTPUT;
import scb.com.vn.model.billingvnpt.DataInputQuery;
import scb.com.vn.model.billingvnpt.PaymentVneduVNPTRp;
import scb.com.vn.payment.model.vnpt.BillingVNPTMediaResp;
import scb.com.vn.payment.model.vnpt.BillingVNPTMediaRq;
import scb.com.vn.payment.model.vnpt.PaymentVNPTMediaResp;
import scb.com.vn.payment.model.vnpt.PaymentVNPTMediaRq;
import scb.com.vn.payment.model.vnpt.PaymentVNPTMediaTCRq;
import scb.com.vn.payment.model.vnpt.PaymentVNPTResp;
import scb.com.vn.payment.vnpt.billing.BillingVNPTMediaServices;

import scb.com.vn.ultility.Common;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.MessageMB;

public class BillingVNPTMediaController {

    private static final Logger LOGGER = Logger.getLogger(BillingVNPTController.class);
    private String producttransferPaybill = Configuration.getProperty("fcubs.producttransfer.mobile.paybill");
    private final String urlGetVNPTMedia = Configuration.getProperty("vnpt.media.rest.url.address.collection.v2.get.bill");
    private final String urlPaymentVNPTMedia_tc = Configuration.getProperty("vnpt.media.rest.url.address.collection.v2.pay.bill.tttc");
    private final String urlPaymentVNPTMedia_nc = Configuration.getProperty("vnpt.media.rest.url.address.collection.v2.pay.bill.ttnc");
    private final String token = Configuration.getProperty("vnpt.rest.api.v2.token");
    private final String vnptParnerId = Configuration.getProperty("vnpt.partner.id");

    public String getBillingVNPTMedia(GetBillInfoRq req, GetBillInfoRp resp, ResponsePayment _rp) throws Exception {
        BillingVNPTMediaRq billingRq = new BillingVNPTMediaRq();
        creatQueryVNPTMediaDto(req, billingRq, vnptParnerId);
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            builder.serializeNulls();
            Gson gson = builder.create();

            BillingVNPTMediaServices vnptService = new BillingVNPTMediaServices();
            BillingVNPTMediaResp vnptQueryResp = new BillingVNPTMediaResp();
            LOGGER.info("REQUEST Hoa Don via VNPT: " + urlGetVNPTMedia + " " + gson.toJson(billingRq));
            vnptQueryResp = vnptService.getBillingVNPTMedia(urlGetVNPTMedia, billingRq, token);
            LOGGER.info("RESPONSE VNPT: " + gson.toJson(vnptQueryResp));

            String dataOutput = vnptQueryResp.getDATA_OUTPUT();
            String tongNo = "0";
            if (dataOutput != null && !"".equals(dataOutput)) {
                DATA_OUTPUT[] output = gson.fromJson(dataOutput, DATA_OUTPUT[].class);
                int cnt = output.length;
                if (cnt == 1)//kt hoa don tu vnpt co 2 ma tro len thi bao loi
                {
                    for (DATA_OUTPUT data : output) {
                        if (_rp != null) {
                            Billing bill1 = new Billing();
                            bill1.setAddress(data.getDIACHI_TT());
                            bill1.setCustomercode(data.getMA_TT());
                            bill1.setCustomername(data.getTEN_TT());
                            bill1.setAmount(data.getTONGNO());

                            DataInputQuery dataSearch = gson.fromJson(req.getDataInput(), DataInputQuery.class);
                            AddInfoRes addInfo = new AddInfoRes();
                            addInfo.setMaTinh(data.getMA_TINH());
                            addInfo.setDieuKien(dataSearch.getDIEUKIEN());
                            addInfo.setSoDT(data.getSDT_LH_TT());
                            bill1.setAddinfo(gson.toJson(addInfo));

                            _rp.getResponse().setIdpartner(req.getIdPartner());
                            _rp.getResponse().setBilling(bill1);
                        }
                        tongNo = data.getTONGNO();
                    }
                }
            }
            if (_rp != null) {//kenh tai quay
                _rp.setTranscode(billingRq.getTRANS_REQUEST_ID());
            }
            createResponseQueryVNPTMedia(resp, vnptQueryResp, tongNo);
            return Xml.Marshaller(resp);
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            GetBillInfoRp resp01 = new GetBillInfoRp();
            resp01.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
            resp01.setErrorMsg("co loi trong qua trinh xu ly tai gateway!");
            return Xml.Marshaller(resp01);
        }

    }

    public String checkBillingVNPTMedia(GetBillInfoRq req, BillPaymentRq billRequest) throws Exception {

        BillingVNPTMediaRq billingRq = new BillingVNPTMediaRq();
        creatQueryVNPTMediaDto(req, billingRq, vnptParnerId);
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            builder.serializeNulls();
            Gson gson = builder.create();
            BillingVNPTMediaServices vnptService = new BillingVNPTMediaServices();
            BillingVNPTMediaResp vnptQueryResp = new BillingVNPTMediaResp();
            LOGGER.info("REQUEST Hoa Don via VNPT: " + urlGetVNPTMedia + " " + gson.toJson(billingRq));
            vnptQueryResp = vnptService.getBillingVNPTMedia(urlGetVNPTMedia, billingRq, token);
            LOGGER.info("RESPONSE VNPT: " + urlGetVNPTMedia + " " + gson.toJson(vnptQueryResp));

            String dataOutput = vnptQueryResp.getDATA_OUTPUT();
            billRequest.setResult(vnptQueryResp.getRESPONSE_CODE());
            //khong tim thay hd
            if (!CommonConstant.RESPONSE_SUCCEED.equals(vnptQueryResp.getRESPONSE_CODE())) {
                return CommonConstant.VNPT_IS_CHECK_BILL_KTC;
            }

            if (dataOutput != null && !"".equals(dataOutput)) {
                DATA_OUTPUT[] output = gson.fromJson(dataOutput, DATA_OUTPUT[].class);
                for (DATA_OUTPUT data : output) {
                    int cnt = output.length;
                    LOGGER.info("TONGNO: " + data.getTONGNO());
                    if ("0".equals(data.getTONGNO()) || cnt >= 1) {
                        return CommonConstant.VNPT_IS_CHECK_BILL_KTC;
                    }
                    Billing bill1 = new Billing();
                    bill1.setAddress(data.getDIACHI_TT());
                    bill1.setCustomercode(data.getMA_TT());
                    bill1.setCustomername(data.getTEN_TT());
                    bill1.setAmount(data.getTONGNO());
                    DataInputQuery dataSearch = gson.fromJson(req.getDataInput(), DataInputQuery.class);
                    AddInfoRes addInfo = new AddInfoRes();
                    addInfo.setMaTinh(data.getMA_TINH());
                    addInfo.setDieuKien(dataSearch.getDIEUKIEN());
                    addInfo.setSoDT(data.getSDT_LH_TT());
                    bill1.setAddinfo(gson.toJson(addInfo));
                    ResponsePayment resPay = new ResponsePayment();
                    resPay.setAccountno(billRequest.getDebitAccount());
                    resPay.setChannel(billRequest.getChannel());
                    resPay.setProvidercode(billRequest.getIdprovider());
                    resPay.setServicecode(billRequest.getIdservicetype());
                    Response resp = new Response();
                    resp.setBilling(bill1);
                    resPay.setResponse(resp);
                    billRequest.setRespay(resPay);
                    //billRequest.setCustomerCode(data.getMA_TT());
                }
            }

            return CommonConstant.VNPT_IS_CHECK_BILL_TC;
        } catch (JsonSyntaxException | IOException ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            GetBillInfoRp resp01 = new GetBillInfoRp();
            resp01.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
            resp01.setErrorMsg("co loi trong qua trinh xu ly tai gateway!");
            return Xml.Marshaller(resp01);
        }

    }

    public PaymentVNPTMediaResp payBillingVNPTMedia_TQ(BillPaymentRq billRequest, ResponsePayment resPay, String transCode) throws Exception {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.serializeNulls();
        Gson gson = builder.create();
        String url = null;
        PaymentVNPTMediaResp payVNPTResp = new PaymentVNPTMediaResp();
        //transCode = "123333"; // gia lap
        BillingVNPTMediaServices vnptService = new BillingVNPTMediaServices();
        if (VNPTConstant.VNPT_SERVICE_TYPE_NOCUOC.equals(billRequest.getAddInfo())) {
            PaymentVNPTMediaRq payVNPTRq = new PaymentVNPTMediaRq();
            payVNPTRq.setTRANS_REQUEST_ID(transCode);
            creatPaymentVNPTMediaDto(payVNPTRq, billRequest);
            url = urlPaymentVNPTMedia_nc;
            //Goi qua VNPT gach no
            LOGGER.info("Thanh toan hoa don via VNPT: " + url + " " + gson.toJson(payVNPTRq));
            payVNPTResp = vnptService.paymentVNPTMediaNC(url, payVNPTRq, token);
        } else {
            PaymentVNPTMediaTCRq payVNPTRq = new PaymentVNPTMediaTCRq();
            payVNPTRq.setTRANS_REQUEST_ID(transCode);
            creatPaymentVNPTMediaTCDto(payVNPTRq, billRequest);
            url = urlPaymentVNPTMedia_tc;
            //Goi qua VNPT gach no
            LOGGER.info("Thanh toan hoa don via VNPT: " + url + " " + gson.toJson(payVNPTRq));
            payVNPTResp = vnptService.paymentVNPTMediaTC(url, payVNPTRq, token);
        }

        resPay.setResultMessage(payVNPTResp.getDESCRIPTION());
        resPay.getResponse().setIdpartner(billRequest.getIdpartner());
        resPay.setResult(CommonConstant.RESPONSE_SUCCEED.equals(payVNPTResp.getRESPONSE_CODE()) ? Message.PaymentResultEnum.OK.getValue() : payVNPTResp.getRESPONSE_CODE());
        resPay.setTranscode(payVNPTResp.getTRANS_RESPONSE_ID());
        resPay.setTicketCode(payVNPTResp.getTRANS_RESPONSE_ID());

        return payVNPTResp;
    }

    /**
     * PayBillingVNPT
     *
     * @param billRequest
     * @param response
     * @return
     * @throws Exception
     */
    public String payBillingVNPTMedia(BillPaymentRq billRequest, TxnCommitRp response, String accountTo) throws Exception {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            builder.serializeNulls();
            Gson gson = builder.create();
            //billRequest.setDebitAccount(billRequest.getDebitAccount());
            // kt thong tin accout
            VwCustAccount custacc = Helper.getDBI().getCustAccountByAccountNo(billRequest.getDebitAccount());
            if (custacc == null) {
                response.setErrorCode("07");
                response.setErrorMsg("TK khong ton tai!");
                return Xml.Marshaller(response);
            } else {
                custacc.setCustAcNo(billRequest.getDebitAccount());
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
            /*
            //Kiem tra neu la Thau chi thi khong kiem tra so du TK, cac TK khac deu ktra so du
            if (custacc.getAccountClass().length() > 3 && (!custacc.getAccountClass().substring(0, 3).equals("TCI"))) {
                if (custacc.getAcyAvlBal().compareTo(new BigDecimal(billRequest.getPayAmt())) == -1) // 0:=,1: 1>2; -1:1<2
                {
                    response.setErrorCode("68");
                    response.setErrorMsg("TK khong du so du de thanh toan!");
                    return Xml.Marshaller(response);
                }
            }
             */
            //1.Validation input
            GetBillInfoRq req = new GetBillInfoRq();
            mappingRequestDto(req, billRequest, gson);
            String isCheck = checkBillingVNPTMedia(req, billRequest);
            if ("0".equals(isCheck)) {
                LOGGER.error("So tien thanh toan khac voi so tien hien tai hoac hoa don khong co no cuoc.");
                response.setErrorCode(CommonConstant.VNPT_TIENTT_KHAC_TIENHT);
                response.setErrorMsg("So tien thanh toan khac voi so tien hien tai hoac hoa don khong co no cuoc.");
                insPblLogVNPT(billRequest, "So tien thanh toan khac voi so tien hien tai hoac hoa don khong co no cuoc. ");
                return Xml.Marshaller(response);
            }

            PblBillpaid billpaid = new PblBillpaid();
            billpaid.setDataxml(billRequest.getRespay() != null ? Xml.Marshaller(billRequest.getRespay()) : "");
            //2.insert db gateway
            String idBillPaid = insertBillPaid(billRequest, custacc, billpaid);
            billpaid.setIdbillpaid(Integer.valueOf(idBillPaid));
            response.setTxnID(idBillPaid);

            if ("-1".equals(idBillPaid)) {
                LOGGER.error("Them du lieu vao pbl_billpaid co loi khi goi DBI.");
                response.setErrorCode(CommonConstant.EXCEPTION_DBI);
                response.setErrorMsg("Co loi khi goi ham qua DBI.");
                return Xml.Marshaller(response);
            }

            //2.Thuc hien cat tien core
            String refCubs = "";
            Fcubs fc = new Fcubs();
            String msgpay = String.format(getPaymentMsgEnum(MobileController.PaymentMsgEnum.THANHTOAN_BILL), billRequest.getCustomerCode().toUpperCase());
            refCubs = fc.transferFCUBSWithProductUser(producttransferPaybill, billRequest.getUserId(), billRequest.getDebitAccount(), accountTo, new BigDecimal(billRequest.getPayAmt()), msgpay);
            LOGGER.info("Ma Khach Hang: " + billpaid.getCustomercode() + " -- So tham chieu trich tien REFCUBS= " + refCubs + " -- From Account :" + custacc.getCustAcNo());

            if (refCubs == null || "".equals(refCubs)) {//Hoach toan core tra ve empty
                LOGGER.error("Hoach toan qua core tra ve loi - REF TRA VE:" + refCubs);
                billpaid.setStatus(Character.valueOf('W')); // cat tien ktc
                billpaid.setResponseCode(CommonConstant.PAYMENT_CORE_EMPTY);
                Helper.getDBI().updatePaybillBillPaid(billpaid);
                response.setErrorCode(CommonConstant.PAYMENT_CORE_EMPTY);
                response.setErrorMsg("Chuyen khoan FCC tra ve loi: " + refCubs);
                billRequest.setResult(CommonConstant.PAYMENT_CORE_EMPTY);
                insPblLogVNPT(billRequest, "Hoach toan qua core tra ve loi - REF TRA VE:" + refCubs);
                return Xml.Marshaller(response);
            }
            if ("TIMEOUT".equals(refCubs)) { //Hoach toan core tra ve timeout
                LOGGER.error("Chuyen khoan FCC Time out.");
                billpaid.setStatus(Character.valueOf('W')); // cat tien ktc
                billpaid.setResponseCode(CommonConstant.PAYMENT_CORE_TIMEOUT);
                Helper.getDBI().updatePaybillBillPaid(billpaid);
                response.setErrorCode(CommonConstant.PAYMENT_CORE_TIMEOUT);
                response.setErrorMsg("Chuyen khoan FCC time out.");
                billRequest.setResult(CommonConstant.PAYMENT_CORE_TIMEOUT);
                insPblLogVNPT(billRequest, "Chuyen khoan FCC Time out - REF TRA VE: " + refCubs);

                return Xml.Marshaller(response);
            }
            //update pbl_billpaid
            billpaid.setPaydateFcubs(new Date());
            billpaid.setRefFcubs(refCubs);
            Helper.getDBI().updatePaybillBillPaid(billpaid);
            String url = null;

            BillingVNPTMediaServices vnptService = new BillingVNPTMediaServices();

            PaymentVNPTMediaResp payVNPTResp = new PaymentVNPTMediaResp();
            if (VNPTConstant.VNPT_SERVICE_TYPE_TRUOCCUOC.equals(billRequest.getAddInfo())) {
                PaymentVNPTMediaTCRq payVNPTRq = new PaymentVNPTMediaTCRq();
                payVNPTRq.setTRANS_REQUEST_ID(idBillPaid);
                creatPaymentVNPTMediaTCDto(payVNPTRq, billRequest);
                url = urlPaymentVNPTMedia_tc;
                //Goi qua VNPT gach no
                LOGGER.info("Thanh toan hoa don truoc cuoc via VNPT: " + url + " " + gson.toJson(payVNPTRq));
                payVNPTResp = vnptService.paymentVNPTMediaTC(url, payVNPTRq, token);

            } else {
                PaymentVNPTMediaRq payVNPTRq = new PaymentVNPTMediaRq();
                payVNPTRq.setTRANS_REQUEST_ID(idBillPaid);
                creatPaymentVNPTMediaDto(payVNPTRq, billRequest);
                url = urlPaymentVNPTMedia_nc;
                //Goi qua VNPT gach no
                LOGGER.info("REQUEST Payment via VNPT: " + url + " " + gson.toJson(payVNPTRq));
                payVNPTResp = vnptService.paymentVNPTMediaNC(url, payVNPTRq, token);
                LOGGER.info("RESPONSE VNPT: " + gson.toJson(payVNPTResp));
            }
            try {
                billpaid.setPaydate(new Date());
                billRequest.getRespay().setRefcore(refCubs);
                billRequest.getRespay().setResult(payVNPTResp.getRESPONSE_CODE());
                billRequest.getRespay().setResultMessage(payVNPTResp.getDESCRIPTION());
                billRequest.getRespay().setTranscode(payVNPTResp.getTRANS_RESPONSE_ID());
                billpaid.setDataxml(Xml.Marshaller(billRequest.getRespay()));
                billpaid.setStatus(Character.valueOf('D'));//TC
                billpaid.setResponseCode(payVNPTResp.getRESPONSE_CODE());
                billpaid.setRefPartner(payVNPTResp.getTRANS_RESPONSE_ID());
                response.setTxnID(idBillPaid);

                if (!CommonConstant.RESPONSE_SUCCEED.equals(payVNPTResp.getRESPONSE_CODE())) {
                    billpaid.setStatus(Character.valueOf('F')); //KTC
                    response.setErrorCode(payVNPTResp.getRESPONSE_CODE());
                    response.setErrorMsg(payVNPTResp.getDESCRIPTION());
                    //response.setErrorMsg("Co loi khi goi qua api vnpt.");
                    try {
                        if (CommonConstant.SERVICE_VNPT_TIMEOUT.equals(payVNPTResp.getRESPONSE_CODE())) { //khong hoan tien
                            LOGGER.error("Co loi goi qua api vnpt - api vnpt TimeOut ");
                            response.setErrorCode(CommonConstant.SERVICE_VNPT_TIMEOUT);
                            billRequest.setResult(CommonConstant.SERVICE_VNPT_TIMEOUT);
                            insPblLogVNPT(billRequest, "Loi goi qua api vnpt - api vnpt TimeOut. ");
                        } else { //hoan tien cho kh
                            LOGGER.error("Co loi khi goi qua api vnpt - Thuc hien hoan tien: " + refCubs);
                            String revertStatus = fc.revertTransferFCUBS(refCubs, 60);
                            if (revertStatus != null && "0".equals(revertStatus)) {
                                billpaid.setRefFcubsRevert(refCubs);
                                LOGGER.error("Hoan tien thanh cong: " + refCubs);

                                insPblLogVNPT(billRequest, "Hoan tien thanh cong: " + refCubs);
                            } else {
                                LOGGER.error("Hoan tien khong thanh cong: " + refCubs);
                                billpaid.setStatus(Character.valueOf('H'));
                                billpaid.setResponseCode(CommonConstant.PARTNER_HOAN_TIEN_ERROR);

                                billRequest.setResult(CommonConstant.PARTNER_HOAN_TIEN_ERROR);
                                insPblLogVNPT(billRequest, "Hoan tien khong thanh cong: " + refCubs);
                            }
                        }
                    } catch (Exception ex1) {
                        billpaid.setResponseCode(CommonConstant.PARTNER_HOAN_TIEN_ERROR);
                        LOGGER.error("Loi khi goi ham hoan tien: " + ex1.getMessage());

                        billRequest.setResult(CommonConstant.PARTNER_HOAN_TIEN_ERROR);
                        insPblLogVNPT(billRequest, "Loi khi goi ham hoan tien: " + ex1.getMessage());
                    }

                    createPaymentVNPTMedia(response, payVNPTResp);
                    //update db
                    Helper.getDBI().updatePaybillBillPaid(billpaid);
                    return Xml.Marshaller(response);
                }

                createPaymentVNPTMedia(response, payVNPTResp);
                //4.Cap nhat lai trang thai giao dich 
                Helper.getDBI().updatePaybillBillPaid(billpaid);

                billRequest.setRefBillpaid(idBillPaid);
                insPblLogVNPT(billRequest, String.format("PAYBILL IS OK [idbillpaid: %1$s] - [Transcode: %2$s]", idBillPaid, billpaid.getRefPartner()));

                return Xml.Marshaller(response.getMBFinResponse(MessageMB.MobileResultEnum.OK));
            } catch (Exception ex) {
                LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
                response.setErrorCode(CommonConstant.SERVICE_PARTNER_FAILED);
                response.setErrorMsg("Loi chua xac dinh khi goi qua api VNPT.");
                return Xml.Marshaller(response);
            }
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            response.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
            response.setErrorMsg("Co loi khi goi ham tai gateway.");
            return Xml.Marshaller(response);
        }
    }

    /**
     * CreateResponseWater
     *
     * @param querySCBResp
     * @param queryVNPTResp
     */
    public void createResponseQueryVNPTMedia(GetBillInfoRp querySCBResp, BillingVNPTMediaResp queryVNPTResp, String tongNo) {

        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.serializeNulls();
        Gson gson = builder.create();
        if ("00".equals(queryVNPTResp.getRESPONSE_CODE())) {
            if ("0".equals(tongNo)) {
                querySCBResp.setErrorCode(CommonConstant.VNPT_TIENTT_KHAC_TIENHT);
                querySCBResp.setErrorMsg("Hóa đơn đã được thanh toán.");

            } else {
                querySCBResp.setErrorCode(CommonConstant.RESPONSE_SUCCEED);
                querySCBResp.setErrorMsg("Transaction done");
            }
        } else {
            querySCBResp.setErrorCode(getBillMsgCodeErrors(queryVNPTResp.getRESPONSE_CODE()));
            querySCBResp.setErrorMsg(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(querySCBResp.getErrorCode())));
        }

        querySCBResp.setTransId(queryVNPTResp.getTRANS_REQUEST_ID());
        querySCBResp.setTransResponseId(queryVNPTResp.getTRANS_RESPONSE_ID());
        querySCBResp.setPaymentRule("1"); //thanh toán theo so tien
        querySCBResp.setBillDetails(queryVNPTResp.getDATA_OUTPUT() != null ? gson.toJson(queryVNPTResp.getDATA_OUTPUT()) : "");

    }

    /**
     * CreatDtoQueryVNPT
     *
     * @param req
     * @param billingRq
     * @param idPartner
     * @throws java.lang.Exception
     */
    public void creatQueryVNPTMediaDto(GetBillInfoRq req, BillingVNPTMediaRq billingRq, String idPartner) throws Exception {
        String channel = this.convertToChannelVNPT(req.getChannel());
        billingRq.setVERSION(VNPTConstant.VERSION);
        billingRq.setPARTNER_ID(idPartner);
        String secureCode = VNPTConstant.ACTION_INQUIRE_TTNC.concat("|");
        String transIdSCB = String.valueOf(getIdSeqByName("SQ_VNPTREFNO"));
        if ("HD_TC".equals(req.getIdservicetype())) { //NUOC:240 - VNEDU:32 
            billingRq.setSERVICE_ID(VNPTConstant.SERVICE_HOADON_TRUOCCUOC);
            transIdSCB = transIdSCB.concat(idPartner).concat(VNPTConstant.SERVICE_HOADON_TRUOCCUOC).concat(req.getIdprovider());
            billingRq.setACTION(VNPTConstant.ACTION_INQUIRE_TTTC);
            secureCode = VNPTConstant.ACTION_INQUIRE_TTTC.concat("|");

        } else if ("HD_NC".equals(req.getIdservicetype())) {
            billingRq.setSERVICE_ID(VNPTConstant.SERVICE_HOADON_NOCUOC);
            transIdSCB = transIdSCB.concat(idPartner).concat(VNPTConstant.SERVICE_HOADON_NOCUOC).concat(req.getIdprovider());
            billingRq.setACTION(VNPTConstant.ACTION_INQUIRE_TTNC);

        } else if ("HD_TC_CT".equals(req.getIdservicetype())) {
            billingRq.setSERVICE_ID(VNPTConstant.SERVICE_HOADON_TRUOCCUOC);
            transIdSCB = transIdSCB.concat(idPartner).concat(VNPTConstant.SERVICE_HOADON_TRUOCCUOC).concat(req.getIdprovider());
            billingRq.setACTION(VNPTConstant.ACTION_INQUIRE_TTTC_CT);
            billingRq.setSERVICE_ID(VNPTConstant.SERVICE_HOADON_TRUOCCUOC);
            secureCode = VNPTConstant.ACTION_INQUIRE_TTTC_CT.concat("|");

        } else { //kenh tai quay COUNTER
            billingRq.setSERVICE_ID(VNPTConstant.SERVICE_HOADON_NOCUOC);
            transIdSCB = transIdSCB.concat(idPartner).concat(VNPTConstant.SERVICE_HOADON_NOCUOC).concat(req.getIdprovider());
            billingRq.setACTION(VNPTConstant.ACTION_INQUIRE_TTNC);
        }
        billingRq.setSERVICE_PROVIDER_ID(req.getIdprovider());
        billingRq.setCHANNEL_ID(channel);
        String transDate = Common.getDate("yyyyMMddHHmmss");
        billingRq.setTRANS_DATE_TIME(transDate);
        billingRq.setTRANS_REQUEST_ID(transIdSCB);
        billingRq.setDATA_INPUT(req.getDataInput() == null ? "" : req.getDataInput());

        secureCode = secureCode.concat(VNPTConstant.VERSION).concat("|")
                .concat(idPartner).concat("|")
                .concat(billingRq.getSERVICE_ID()).concat("|")
                .concat(req.getIdprovider()).concat("|")
                .concat(transIdSCB).concat("|")
                .concat(channel).concat("|")
                .concat(req.getDataInput() == null ? "" : req.getDataInput()).concat("|")
                .concat(transDate).concat("|")
                .concat(VNPTConstant.SECRET_KEY);

        String secureCodeSHA256 = DigestUtils.sha256Hex(secureCode);
        billingRq.setSECURE_CODE(secureCodeSHA256);
    }

    /**
     * CreatEntityPaymentVNPT
     *
     * @param payVNPTRq
     * @param billRequest
     * @throws java.lang.Exception
     */
    public void creatPaymentVNPTMediaDto(PaymentVNPTMediaRq payVNPTRq, BillPaymentRq billRequest) throws Exception {
        String channel = this.convertToChannelVNPT(billRequest.getChannel());

        payVNPTRq.setVERSION(VNPTConstant.VERSION);
        payVNPTRq.setPARTNER_ID(VNPTConstant.PARTNER_ID); //262125 VNPT
        if (VNPTConstant.VNPT_SERVICE_TYPE_NOCUOC.equals(billRequest.getAddInfo())) {//HD_TC:10001 - HD_NC:10003
            payVNPTRq.setSERVICE_ID(VNPTConstant.SERVICE_HOADON_NOCUOC);
            payVNPTRq.setACTION(VNPTConstant.ACTION_PAYMENT_NC);
        } else if (VNPTConstant.VNPT_SERVICE_TYPE_TRUOCCUOC.equals(billRequest.getAddInfo())) {
            payVNPTRq.setSERVICE_ID(VNPTConstant.SERVICE_HOADON_TRUOCCUOC);
            payVNPTRq.setACTION(VNPTConstant.ACTION_PAYMENT_TC);
        }
        payVNPTRq.setSERVICE_PROVIDER_ID(billRequest.getIdprovider());
        payVNPTRq.setPAYMENT_CODE(billRequest.getCustomerCode());
        payVNPTRq.setCHANNEL_ID(channel);
        payVNPTRq.setTRANS_REQUEST_ID(payVNPTRq.getTRANS_REQUEST_ID());
        payVNPTRq.setADDITIONAL_INFO(billRequest.getAddInfo() == null ? "" : billRequest.getAddInfo());
        payVNPTRq.setOPTIONS(billRequest.getOptions());
        payVNPTRq.setBILL_AMOUNT(billRequest.getPayAmt());
        payVNPTRq.setBILL_DETAIL(billRequest.getBillDetails());
        payVNPTRq.setTYPE("IN");
        String transDate = Common.getDate("yyyyMMddHHmmss");
        payVNPTRq.setTRANS_DATE_TIME(transDate);

        String secureCode = payVNPTRq.getACTION().concat("|")
                .concat(VNPTConstant.VERSION).concat("|")
                .concat(VNPTConstant.PARTNER_ID).concat("|")
                .concat(payVNPTRq.getSERVICE_ID()).concat("|")
                .concat(billRequest.getIdprovider()).concat("|")
                .concat(billRequest.getCustomerCode()).concat("|")
                .concat(channel).concat("|")
                .concat(payVNPTRq.getTRANS_REQUEST_ID()).concat("|")
                .concat(billRequest.getAddInfo() == null ? "" : billRequest.getAddInfo()).concat("|")
                .concat(billRequest.getOptions()).concat("|")
                .concat(billRequest.getPayAmt()).concat("|")
                .concat(billRequest.getBillDetails()).concat("|")
                .concat(transDate).concat("|")
                .concat("IN").concat("|")
                .concat(VNPTConstant.SECRET_KEY);

        String secureCodeSHA256 = DigestUtils.sha256Hex(secureCode);
        payVNPTRq.setSECURE_CODE(secureCodeSHA256);
    }

    /**
     * CreatEntityPaymentVNPT
     *
     * @param payVNPTRq
     * @param billRequest
     * @throws java.lang.Exception
     */
    public void creatPaymentVNPTMediaTCDto(PaymentVNPTMediaTCRq payVNPTRq, BillPaymentRq billRequest) throws Exception {
        String channel = this.convertToChannelVNPT(billRequest.getChannel());

        payVNPTRq.setVERSION(VNPTConstant.VERSION);
        payVNPTRq.setPARTNER_ID(VNPTConstant.PARTNER_ID); //262125 VNPT
        if (VNPTConstant.VNPT_SERVICE_TYPE_NOCUOC.equals(billRequest.getAddInfo())) {//HD_TC:10001 - HD_NC:10003
            payVNPTRq.setSERVICE_ID(VNPTConstant.SERVICE_HOADON_NOCUOC);
            payVNPTRq.setACTION(VNPTConstant.ACTION_PAYMENT_NC);
        } else if (VNPTConstant.VNPT_SERVICE_TYPE_TRUOCCUOC.equals(billRequest.getAddInfo())) {
            payVNPTRq.setSERVICE_ID(VNPTConstant.SERVICE_HOADON_TRUOCCUOC);
            payVNPTRq.setACTION(VNPTConstant.ACTION_PAYMENT_TC);
        }
        payVNPTRq.setSERVICE_PROVIDER_ID(billRequest.getIdprovider());
        payVNPTRq.setPAYMENT_CODE(billRequest.getCustomerCode());
        payVNPTRq.setCHANNEL_ID(channel);
        payVNPTRq.setTRANS_REQUEST_ID(payVNPTRq.getTRANS_REQUEST_ID());
        payVNPTRq.setADDITIONAL_INFO(billRequest.getAddInfo() == null ? "" : billRequest.getAddInfo());
        payVNPTRq.setOPTIONS(billRequest.getOptions());
        payVNPTRq.setBILL_AMOUNT(billRequest.getPayAmt());
        payVNPTRq.setBILL_DETAIL(billRequest.getBillDetails());
        String transDate = Common.getDate("yyyyMMddHHmmss");
        payVNPTRq.setTRANS_DATE_TIME(transDate);

        String secureCode = payVNPTRq.getACTION().concat("|")
                .concat(VNPTConstant.VERSION).concat("|")
                .concat(VNPTConstant.PARTNER_ID).concat("|")
                .concat(payVNPTRq.getSERVICE_ID()).concat("|")
                .concat(billRequest.getIdprovider()).concat("|")
                .concat(billRequest.getCustomerCode()).concat("|")
                .concat(channel).concat("|")
                .concat(payVNPTRq.getTRANS_REQUEST_ID()).concat("|")
                .concat(billRequest.getAddInfo() == null ? "" : billRequest.getAddInfo()).concat("|")
                .concat(billRequest.getOptions()).concat("|")
                .concat(billRequest.getPayAmt()).concat("|")
                .concat(billRequest.getBillDetails()).concat("|")
                .concat(transDate).concat("|")
                .concat(VNPTConstant.SECRET_KEY);

        String secureCodeSHA256 = DigestUtils.sha256Hex(secureCode);
        payVNPTRq.setSECURE_CODE(secureCodeSHA256);
    }

    /**
     * CreatePaymentResponseSCB
     *
     * @param resp
     * @param payVNPTResp
     */
    public void createPaymentVNPTMedia(TxnCommitRp resp, PaymentVNPTMediaResp payVNPTResp) {
        resp.setErrorCode(payVNPTResp.getRESPONSE_CODE());
        resp.setErrorMsg(payVNPTResp.getDESCRIPTION());
        resp.setTxnCommitTime(payVNPTResp.getTRANS_DATE_TIME());
        resp.setTxnIdPartner(payVNPTResp.getTRANS_RESPONSE_ID());
        resp.setTxnID(payVNPTResp.getTRANS_REQUEST_ID());
        resp.setTxnType(resp.getTxnType());
    }

    /**
     * CreatePaymentResponseSCB
     *
     * @param resp
     * @param payVNPTResp
     */
    public void createPaymentResponseHocPhi(PaymentVneduVNPTRp resp, PaymentVNPTResp payVNPTResp) throws Exception {
        resp.setResult(payVNPTResp.getRESPONSE_CODE());
        resp.setResultMessage(payVNPTResp.getDESCRIPTION());
        resp.setProcessingcode(resp.getProcessingcode());
        resp.setTransDatetime(payVNPTResp.getTRANS_DATE_TIME());
        resp.setResponseCode(payVNPTResp.getTRANS_RESPONSE_ID());
        resp.setPartnercode(payVNPTResp.getTRANS_RESPONSE_ID());
        resp.setProcessingcode("PAY");
        resp.setOptions(payVNPTResp.getOPTIONS());
        resp.setSecureCode(payVNPTResp.getSECURE_CODE());
    }

    private String insertBillPaid(BillPaymentRq pep, VwCustAccount from_custacc, PblBillpaid billpaid) {
        int idBillPaid = -1;
        List mbMobile;
        try {
            if ("01".equals(pep.getChannel())) {// kenh IB
                billpaid.setIduserMaker(pep.getMakerCode());
                billpaid.setIdchanneluserMaker(pep.getMakerCode());
                billpaid.setIduserChecker(pep.getCheckerCode());
                billpaid.setIdchanneluserChecker(pep.getCheckerCode());
            } else { //kenh MB
                mbMobile = Helper.getDBI().getCustomerFromMobile(pep.getUserName());
                if (mbMobile == null || mbMobile.isEmpty()) {
                    return String.valueOf(idBillPaid);
                }
                HashMap hm_MB = (HashMap) mbMobile.get(0);
                billpaid.setIduserMaker(hm_MB.get("cus_id").toString());
                billpaid.setIduserChecker(hm_MB.get("cus_id").toString());
                billpaid.setIdchanneluserMaker(pep.getUserName());
                billpaid.setIdchanneluserChecker(pep.getUserName());
            }
            billpaid.setIdpartner(pep.getIdpartner());
            PblServicetype pst = new PblServicetype();
            pst.setIdservicetype(pep.getIdservicetype());
            billpaid.setPblServicetype(pst);
            PblProvider pdr = new PblProvider();
            pdr.setIdprovider(pep.getIdprovider());
            billpaid.setPblProvider(pdr);
            billpaid.setCustomercode(pep.getCustomerCode());
            // billpaid.setDataxml("");
            billpaid.setIdchannel(pep.getChannel());
            billpaid.setUsertype("1");
            billpaid.setRefPartner("");
            billpaid.setRefFcubs("");
            billpaid.setTotalamount(Long.valueOf(pep.getPayAmt()));
            billpaid.setPaymentmethod(2);//bang tk
            billpaid.setAccCust(from_custacc.getCustNo());
            billpaid.setAccIdaccount(from_custacc.getCustAcNo());
            billpaid.setAccHoldername(from_custacc.getAcDesc());
            billpaid.setAccAddress(from_custacc.getAddress());
            Date d = new Date();
            billpaid.setInsdate(d);
            billpaid.setInsdateMaker(d);
            billpaid.setInsdateChecker(d);
            billpaid.setTransdate(d);
            billpaid.setPaydateFcubs(d);
            billpaid.setIsapproved(Character.valueOf('A'));
            billpaid.setStatus(Character.valueOf('W'));
            //billpaid.setBranchcode(billpaid.getAccIdaccount().substring(0, 3));
            billpaid.setBranchcode(CommonUtils.getBranchAccount(billpaid.getAccIdaccount()));
            idBillPaid = Helper.getDBI().insertPaybillBillPaid(billpaid);

        } catch (RemoteException ex) {
            LOGGER.error("co loi them du lieu vao pbl_billpaid tai gw." + ex.getMessage());
            return String.valueOf(idBillPaid);
        }

        return String.valueOf(idBillPaid);
    }

    public String convertToChannelVNPT(String channelSCB) {

        String channelVNPT = "99"; //kenh khac
        switch (channelSCB) {
            case "01":
                channelVNPT = "1"; //internet banking
                break;
            case "02":
                channelVNPT = "12"; //tu dong
                break;
            case "03":
                channelVNPT = "4"; //mobile banking
                break;
            case "11":
            case "COUNTER":
                channelVNPT = "7"; //tai quay
                break;
            case "41":
                channelVNPT = "5"; //qr code
                break;
            default:
                channelVNPT = "99"; //kenh khac
                break;
        }

        return channelVNPT;
    }

    private int getIdSeqByName(String seqname) {
        try {
            return Helper.getDBI().getIdSeqByName(seqname);
        } catch (RemoteException ex) {
            throw new PaymentException(ex);
        }
    }

    public void mappingRequestDto(GetBillInfoRq req, BillPaymentRq billRequest, Gson gson) {
        req.setIdPartner(billRequest.getIdpartner());
        req.setChannel(billRequest.getIdpartner());
        req.setIdprovider(billRequest.getIdprovider());
        req.setAddInfo(billRequest.getAddInfo());
        req.setCustomerCode(billRequest.getCustomerCode());
        req.setIdservicetype(billRequest.getIdservicetype());
        req.setChannel(billRequest.getChannel());//kenh MB ODBX
        DataInputQuery inputNoCuoc = new DataInputQuery();
        inputNoCuoc.setMATRACUU(billRequest.getCustomerCode());
        inputNoCuoc.setDIEUKIEN(billRequest.getPaymentType());
        req.setDataInput(gson.toJson(inputNoCuoc));
    }

    /**
     *
     * @param pepe
     * @param description
     * @throws Exception
     */
    private void insPblLogVNPT(BillPaymentRq pepe, String description) throws Exception {
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
                if ("01".equals(pepe.getChannel())) {// kenh IB
                    pl.setIdmarker(pepe.getMakerCode());
                } else {
                    LOGGER.error("***- ERROR MB (FUNCTION transferPaybill): " + "not get customerid from DB VNINFO ");
                }
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
            LOGGER.error("***- ERROR MB (FUNCTION insPblLog): " + e.getMessage());
        }
    }

    public String getBillMsgCodeErrors(String vnptCodeError) {
        String result = "";
        switch (vnptCodeError) {
            case "00":
                result = Message.PaymentResultEnum.OK.getValue();
                break;
            case "99":
                result = Message.PaymentResultEnum.SYSTEM_ERROR.getValue();
                break;
            case "07":
            case "16":
                result = Message.PaymentResultEnum.VNPT_CODE_NOT_EXISTED.getValue();
                break;
            case "98":
                result = Message.PaymentResultEnum.TIMEOUT.getValue();
                break;
            default:
                result = Message.PaymentResultEnum.SYSTEM_ERROR.getValue();
                break;
        }
        return result;
    }

}
