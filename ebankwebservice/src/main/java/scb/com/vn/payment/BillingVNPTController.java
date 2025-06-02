/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.constant.VNPTConstant;
import scb.com.vn.controller.Fcubs;
import scb.com.vn.controller.MobileController;
import static scb.com.vn.controller.MobileController.getPaymentMsgEnum;
import scb.com.vn.dbi.dto.PblBillpaid;
import scb.com.vn.dbi.dto.PblProvider;
import scb.com.vn.dbi.dto.PblServicetype;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.model.BillPaymentRq;
import scb.com.vn.model.GetBillInfoRp;
import scb.com.vn.model.GetBillInfoRq;
import scb.com.vn.model.TxnCommitRp;

import scb.com.vn.model.billingvnpt.BillingVneduRp;
import scb.com.vn.model.billingvnpt.PaymentVneduVNPTRp;
import scb.com.vn.payment.model.vnpt.BillingVNPTResp;
import scb.com.vn.payment.model.vnpt.BillingVNPTRq;
import scb.com.vn.payment.model.vnpt.PaymentVNPTResp;
import scb.com.vn.payment.model.vnpt.PaymentVNPTRq;
import scb.com.vn.payment.vnpt.billing.BillingVNPTPaymentServices;
import scb.com.vn.ultility.Common;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;

/**
 *
 * @author loinv3
 */
public class BillingVNPTController {

    private static final Logger LOGGER = Logger.getLogger(BillingVNPTController.class);
    private String producttransferPaybill = Configuration.getProperty("fcubs.producttransfer.mobile.paybill");
    private final String urlGet = Configuration.getProperty("vnpt.rest.url.address.collection.v2.get.bill");
    private final String urlPayment = Configuration.getProperty("vnpt.rest.url.address.collection.v2.pay.bill");
    
    
    //private final String urlGet = Configuration.getProperty("vnpt.rest.url.address.collection.v2.get.bill");
    //private final String urlPayment = Configuration.getProperty("vnpt.rest.url.address.collection.v2.pay.bill");
    
    private final String token = Configuration.getProperty("vnpt.rest.api.v2.token");
    private final String vnptParnerId = Configuration.getProperty("vnpt.partner.id");

    /**
     * GetBillingWaterVNPT
     *
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    public String getBillingWaterVNPT(GetBillInfoRq req, GetBillInfoRp resp) throws Exception {
        BillingVNPTRq billingRq = new BillingVNPTRq();
        creatDtoQueryVNPT(req, billingRq, vnptParnerId);

        try {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            builder.serializeNulls();
            Gson gson = builder.create();
            
            BillingVNPTPaymentServices vnptService = new BillingVNPTPaymentServices();
            BillingVNPTResp vnptQueryResp = new BillingVNPTResp();
            LOGGER.info("Goi query Nuoc via VNPT: " + urlGet + " " + gson.toJson(billingRq));
            vnptQueryResp = vnptService.getBillingVNPT(urlGet, billingRq, token);
            createResponseQueryWater(resp, vnptQueryResp);

            return Xml.Marshaller(resp);
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            GetBillInfoRp resp01 = new GetBillInfoRp();
            resp01.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
            resp01.setErrorMsg("co loi trong qua trinh xu ly tai gateway!");
            return Xml.Marshaller(resp01);
        }

    }
    
    /**
     * GetBillingHocPhiVNPT
     *
     * @param req
     * @return
     * @throws Exception
     */
    public String getBillingHocPhiVNPT(GetBillInfoRq req) throws Exception {
        BillingVNPTRq billingRq = new BillingVNPTRq();
        BillingVneduRp resp = new BillingVneduRp();
        creatDtoQueryVNPT(req, billingRq, vnptParnerId);

        try {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            builder.serializeNulls();
            Gson gson = builder.create();

            BillingVNPTPaymentServices vnptService = new BillingVNPTPaymentServices();
            BillingVNPTResp vnptQueryResp = new BillingVNPTResp();
            LOGGER.info("Goi query hoc phi via VNPT: " + urlGet + " " + gson.toJson(billingRq));
            vnptQueryResp = vnptService.getBillingVNPT(urlGet, billingRq, token);
            resp.setServicecode(req.getIdservicetype());
            createResponseQueryHocPhi(resp, vnptQueryResp);

            return Xml.Marshaller(resp);
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            BillingVneduRp resp01 = new BillingVneduRp();
            resp01.setResult(CommonConstant.EXCEPTION_GATEWAY);
            resp01.setResultMessage("Co loi khi goi ham tai gateway.");
            return Xml.Marshaller(resp01);
        }
    }
    
    /**
     * PayBillingVNPT
     *
     * @param billRequest
     * @param response
     * @return
     * @throws Exception
     */
    public String payBillingWaterVNPT(BillPaymentRq billRequest, TxnCommitRp response, String accountTo) throws Exception {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            builder.serializeNulls();
            Gson gson = builder.create();
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
            BillingVNPTValidation.payBillingVNPTValidation(billRequest);
            PblBillpaid billpaid = new PblBillpaid();
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

                return Xml.Marshaller(response);
            }
            if ("TIMEOUT".equals(refCubs)) { //Hoach toan core tra ve timeout
                LOGGER.error("Chuyen khoan FCC Time out.");
                billpaid.setStatus(Character.valueOf('W')); // cat tien ktc
                billpaid.setResponseCode(CommonConstant.PAYMENT_CORE_TIMEOUT);
                Helper.getDBI().updatePaybillBillPaid(billpaid);
                response.setErrorCode(CommonConstant.PAYMENT_CORE_TIMEOUT);
                response.setErrorMsg("Chuyen khoan FCC time out.");

                return Xml.Marshaller(response);
            }
            //update pbl_billpaid
            billpaid.setPaydateFcubs(new Date());
            billpaid.setRefFcubs(refCubs);
            billpaid.setDataxml(billRequest.getBillDetails());
            Helper.getDBI().updatePaybillBillPaid(billpaid);
            PaymentVNPTRq payVNPTRq = new PaymentVNPTRq();
            payVNPTRq.setTRANS_REQUEST_ID(idBillPaid);
            creatPaymentWaterVNPT(payVNPTRq, billRequest);
            try {
                BillingVNPTPaymentServices vnptService = new BillingVNPTPaymentServices();
                //3.Goi qua VNPT gach no
                LOGGER.info("Thanh toan tien nuoc via VNPT: " + urlPayment + " " + gson.toJson(payVNPTRq));
                PaymentVNPTResp payVNPTResp = vnptService.paymentVNPT(urlPayment, payVNPTRq, token);
                billpaid.setStatus(Character.valueOf('D'));//TC
                billpaid.setResponseCode(payVNPTResp.getRESPONSE_CODE());
                billpaid.setRefPartner(payVNPTResp.getTRANS_RESPONSE_ID());
                response.setTxnID(idBillPaid);
                
                Date d = new Date();
                billpaid.setPaydate(d);

                if (!CommonConstant.RESPONSE_SUCCEED.equals(payVNPTResp.getRESPONSE_CODE())) {
                    billpaid.setStatus(Character.valueOf('F')); //KTC
                    response.setErrorCode(payVNPTResp.getRESPONSE_CODE());
                    response.setErrorMsg(payVNPTResp.getDESCRIPTION());
                    //response.setErrorMsg("Co loi khi goi qua api vnpt.");
                    try {
                        if (CommonConstant.SERVICE_VNPT_TIMEOUT.equals(payVNPTResp.getRESPONSE_CODE())) { //khong hoan tien
                            LOGGER.error("Co loi goi qua api vnpt - api vnpt TimeOut ");
                            response.setErrorCode(CommonConstant.SERVICE_VNPT_TIMEOUT);
                        } else { //hoan tien cho kh
                            LOGGER.error("Co loi khi goi qua api vnpt - Thuc hien hoan tien: " + refCubs);
                            String revertStatus = fc.revertTransferFCUBS(refCubs, 40);
                            if (revertStatus != null && "0".equals(revertStatus)) {
                                billpaid.setRefFcubsRevert(refCubs);
                                LOGGER.error("Hoan tien thanh cong: " + refCubs);
                            } else {
                                LOGGER.error("Hoan tien khong thanh cong: " + refCubs);
                                billpaid.setResponseCode(CommonConstant.PARTNER_HOAN_TIEN_ERROR);
                            }
                        }
                    } catch (Exception ex1) {
                        billpaid.setResponseCode(CommonConstant.PARTNER_HOAN_TIEN_ERROR);
                        LOGGER.error("Loi khi goi ham hoan tien: " + ex1.getMessage());
                    }

                    createPaymentResponseWater(response, payVNPTResp);
                    //update db
                    Helper.getDBI().updatePaybillBillPaid(billpaid);
                    return Xml.Marshaller(response);
                }

                createPaymentResponseWater(response, payVNPTResp);
                //4.Cap nhat lai trang thai giao dich 
                Helper.getDBI().updatePaybillBillPaid(billpaid);

                return Xml.Marshaller(response);
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
     * PayBillingVNPT
     *
     * @param billRequest
     * @param response
     * @return
     * @throws Exception
     */
    public String payBillingHocPhiVNPT(BillPaymentRq billRequest, PaymentVneduVNPTRp response, String accountTo) throws Exception {
        LOGGER.info("getCustAccountByAccountNo:" + billRequest.getDebitAccount());
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            builder.serializeNulls();
            Gson gson = builder.create();
            // kt thong tin accout
            VwCustAccount custacc = Helper.getDBI().getCustAccountByAccountNo(billRequest.getDebitAccount());
            if (custacc == null) {
                response.setResult("07");
                response.setResultMessage("TK khong ton tai!");
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
            //1. Validation input
            BillingVNPTValidation.payBillingVNPTValidation(billRequest);
            PblBillpaid billpaid = new PblBillpaid();
            //2. insert db gateway
            String idBillPaid = insertBillPaid(billRequest, custacc, billpaid);
            billpaid.setIdbillpaid(Integer.valueOf(idBillPaid));
            response.setTranscode(idBillPaid);

            if ("-1".equals(idBillPaid)) {
                LOGGER.error("Them du lieu vao pbl_billpaid co loi khi goi qua dbi");
                response.setResult(CommonConstant.EXCEPTION_DBI);
                response.setResultMessage("Co loi khi goi ham DBI.");
                return Xml.Marshaller(response);
            }

            //2.Thuc hien cat tien core
            String refCubs = "";
            Fcubs fc = new Fcubs();
            String msgpay = String.format(getPaymentMsgEnum(MobileController.PaymentMsgEnum.THANHTOAN_BILL), billRequest.getCustomerCode().toUpperCase());
            refCubs = fc.transferFCUBSWithProductUser(producttransferPaybill, billRequest.getUserId(), billRequest.getDebitAccount(), accountTo, new BigDecimal(billRequest.getPayAmt()), msgpay);
            response.setRefcore(refCubs);
            LOGGER.info("Ma Khach Hang: " + billpaid.getCustomercode() + " -- So tham chieu trich tien REFCUBS= " + refCubs + " -- From Account :" + custacc.getCustAcNo());
            if (refCubs == null || "".equals(refCubs)) {
                LOGGER.error("Hoach toan qua core tra ve loi - REF TRA VE:" + refCubs);
                billpaid.setResponseCode(CommonConstant.PAYMENT_CORE_EMPTY);
                billpaid.setStatus(Character.valueOf('W')); // cat tien ktc
                Helper.getDBI().updatePaybillBillPaid(billpaid);
                response.setResult(CommonConstant.PAYMENT_CORE_EMPTY);
                response.setResultMessage("Chuyen khoan FCC tra ve loi: " + refCubs);
                return Xml.Marshaller(response);
            }
            //Hoach toan core tra ve timeout
            if ("TIMEOUT".equals(refCubs)) {
                LOGGER.error("Chuyen khoan FCC Time out.");
                billpaid.setStatus(Character.valueOf('W')); // cat tien ktc
                billpaid.setResponseCode(CommonConstant.PAYMENT_CORE_TIMEOUT);
                Helper.getDBI().updatePaybillBillPaid(billpaid);
                response.setResult(CommonConstant.PAYMENT_CORE_TIMEOUT);
                response.setResultMessage("Chuyen khoan FCC time out.");
                return Xml.Marshaller(response);
            }

            //update pbl_billpaid
            billpaid.setPaydateFcubs(new Date());
            billpaid.setRefFcubs(refCubs);
            billpaid.setDataxml(billRequest.getBillDetails());

            Helper.getDBI().updatePaybillBillPaid(billpaid);
            PaymentVNPTRq payVNPTRq = new PaymentVNPTRq();
            payVNPTRq.setTRANS_REQUEST_ID(idBillPaid);
            creatDtoPaymentHocPhiVNPT(payVNPTRq, billRequest);

            try {
                BillingVNPTPaymentServices vnptService = new BillingVNPTPaymentServices();
                //3. Goi qua VNPT gach no
                LOGGER.info("Thanh toan hoc phi via vnedu VNPT: " + urlPayment + " " + gson.toJson(payVNPTRq));
                PaymentVNPTResp payVNPTResp = vnptService.paymentVNPT(urlPayment, payVNPTRq, token);

                billpaid.setStatus(Character.valueOf('D'));//TC
                billpaid.setResponseCode(payVNPTResp.getRESPONSE_CODE());
                billpaid.setRefPartner(payVNPTResp.getTRANS_RESPONSE_ID());
                response.setTranscode(idBillPaid);

                if (!CommonConstant.RESPONSE_SUCCEED.equals(payVNPTResp.getRESPONSE_CODE())) {
                    billpaid.setStatus(Character.valueOf('F')); //KTC
                    response.setResult(payVNPTResp.getRESPONSE_CODE());
                    response.setResultMessage(payVNPTResp.getDESCRIPTION());
                    try { //khong hoan tien                        
                        if (CommonConstant.SERVICE_VNPT_TIMEOUT.equals(payVNPTResp.getRESPONSE_CODE())) {
                            LOGGER.error("Co loi goi qua api vnpt - api vnpt TimeOut ");
                            response.setResult(CommonConstant.SERVICE_VNPT_TIMEOUT);
                        } else { //hoan tien cho kh
                            LOGGER.error("Co loi khi goi qua api vnpt - Thuc hien hoan tien: " + refCubs);
                            String revertStatus = fc.revertTransferFCUBS(refCubs, 40);
                            if (revertStatus != null && "0".equals(revertStatus)) {
                                billpaid.setRefFcubsRevert(refCubs);
                                LOGGER.error("Hoan tien thanh cong: " + refCubs);
                            } else {
                                LOGGER.error("Hoan tien khong thanh cong: " + refCubs);
                                billpaid.setResponseCode(CommonConstant.PARTNER_HOAN_TIEN_ERROR);
                            }
                        }
                    } catch (Exception ex1) {
                        billpaid.setResponseCode(CommonConstant.PARTNER_HOAN_TIEN_ERROR);
                        LOGGER.error("Loi khi goi ham hoan tien: " + ex1.getMessage());
                    }

                    createPaymentResponseHocPhi(response, payVNPTResp);
                    //update db
                    Helper.getDBI().updatePaybillBillPaid(billpaid);
                    return Xml.Marshaller(response);
                }

                createPaymentResponseHocPhi(response, payVNPTResp);
                //4.Cap nhat lai trang thai giao dich 
                Helper.getDBI().updatePaybillBillPaid(billpaid);

                return Xml.Marshaller(response);
            } catch (Exception ex) {
                LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
                response.setResult(CommonConstant.SERVICE_PARTNER_FAILED);
                response.setResultMessage("Loi chua xac dinh khi goi qua api VNPT.");
                return Xml.Marshaller(response);
            }
        } catch (Exception ex) {
            LOGGER.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            response.setResult(CommonConstant.EXCEPTION_GATEWAY);
            response.setResultMessage("Co loi khi goi ham tai gateway.");
            return Xml.Marshaller(response);
        }
    }

    /**
     * CreateResponseWater
     *
     * @param querySCBResp
     * @param queryVNPTResp
     */
    public void createResponseQueryWater(GetBillInfoRp querySCBResp, BillingVNPTResp queryVNPTResp) {
        
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.serializeNulls();
        Gson gson = builder.create();
        querySCBResp.setErrorCode(queryVNPTResp.getRESPONSE_CODE());
        querySCBResp.setErrorMsg(queryVNPTResp.getDESCRIPTION());
        querySCBResp.setTxnFee(queryVNPTResp.getBILL_FEE());
        querySCBResp.setOptions(queryVNPTResp.getOPTIONS());
        querySCBResp.setBillDetails(gson.toJson(queryVNPTResp.getBILL_DETAIL()));
        querySCBResp.setCustomerCode(queryVNPTResp.getPAYMENT_CODE());
        querySCBResp.setBillAmt(queryVNPTResp.getBILL_AMOUNT());
    }

    /**
     * CreateResponseQueryHocPhi
     *
     * @param querySCBResp
     * @param queryVNPTResp
     */
    public void createResponseQueryHocPhi(BillingVneduRp querySCBResp, BillingVNPTResp queryVNPTResp) {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.serializeNulls();
        Gson gson = builder.create();
        querySCBResp.setResult(queryVNPTResp.getRESPONSE_CODE());
        querySCBResp.setResultMessage(queryVNPTResp.getDESCRIPTION());
        querySCBResp.setTranscode(queryVNPTResp.getTRANS_REQUEST_ID());
        querySCBResp.setResponseCode(queryVNPTResp.getTRANS_RESPONSE_ID());
        querySCBResp.setOptions(queryVNPTResp.getOPTIONS());
        querySCBResp.setPaymentcode(queryVNPTResp.getPAYMENT_CODE());
        querySCBResp.setBillAmount(queryVNPTResp.getBILL_AMOUNT());
        querySCBResp.setBillFree(queryVNPTResp.getBILL_FEE());
        querySCBResp.setBillDetails(gson.toJson(queryVNPTResp.getBILL_DETAIL()));
        querySCBResp.setServicecode(queryVNPTResp.getSECURE_CODE());
    }

    /**
     * CreatDtoQueryVNPT
     *
     * @param req
     * @param billingRq
     * @param idPartner
     * @throws java.lang.Exception
     */
    public void creatDtoQueryVNPT(GetBillInfoRq req, BillingVNPTRq billingRq, String idPartner) throws Exception {
        String channel = this.convertToChannelVNPT(req.getChannel());
        billingRq.setACTION(VNPTConstant.ACTION_INQUIRE);
        billingRq.setVERSION(VNPTConstant.VERSION);
        billingRq.setPARTNER_ID(idPartner);

        String transIdSCB = CommonUtils.getJulianFromDate();
        if ("NUOC".equals(req.getIdservicetype())) { //NUOC:240 - VNEDU:32 
            billingRq.setSERVICE_ID(VNPTConstant.SERVICE_WATER_ID);
            transIdSCB = transIdSCB.concat(idPartner).concat(VNPTConstant.SERVICE_WATER_ID).concat(req.getIdprovider());
        } else {
            billingRq.setSERVICE_ID(VNPTConstant.SERVICE_VNEDU_ID);
            transIdSCB = transIdSCB.concat(idPartner).concat(VNPTConstant.SERVICE_VNEDU_ID).concat(req.getIdprovider());
        }

        billingRq.setSERVICE_PROVIDER_ID(req.getIdprovider());
        billingRq.setCHANNEL_ID(channel);
        billingRq.setPAYMENT_CODE(req.getCustomerCode());
        String transDate = Common.getDate("yyyyMMddHHmmss");
        billingRq.setTRANS_DATE_TIME(transDate);
        billingRq.setTRANS_REQUEST_ID(transIdSCB);
        billingRq.setADDITION_INFO(req.getAddInfo() == null ? "" : req.getAddInfo());

        String secureCode = VNPTConstant.ACTION_INQUIRE.concat("|")
                .concat(VNPTConstant.VERSION).concat("|")
                .concat(idPartner).concat("|")
                .concat(billingRq.getSERVICE_ID()).concat("|")
                .concat(req.getIdprovider()).concat("|")
                .concat(channel).concat("|")
                .concat(req.getCustomerCode()).concat("|")
                .concat(transDate).concat("|")
                .concat(transIdSCB).concat("|")
                .concat(req.getAddInfo() == null ? "" : req.getAddInfo()).concat("|")
                .concat(VNPTConstant.SECRET_KEY);
        String secureCodeSHA256 = DigestUtils.sha256Hex(secureCode); 
        
        System.out.println("secureCodeSHA256:" + secureCodeSHA256);
        LOGGER.info("secureCodeSHA256: " + secureCodeSHA256);
        
        billingRq.setSECURE_CODE(secureCodeSHA256);        
    }

    /**
     * CreatEntityPaymentVNPT
     *
     * @param payVNPTRq
     * @param billRequest
     * @throws java.lang.Exception
     */
    public void creatDtoPaymentHocPhiVNPT(PaymentVNPTRq payVNPTRq, BillPaymentRq billRequest) throws Exception {

        String channel = this.convertToChannelVNPT(billRequest.getChannel());
        payVNPTRq.setACTION(VNPTConstant.ACTION_PAYMENT);
        payVNPTRq.setVERSION(VNPTConstant.VERSION);
        payVNPTRq.setPARTNER_ID(VNPTConstant.PARTNER_ID); //262125 VNPT
        payVNPTRq.setSERVICE_ID(VNPTConstant.SERVICE_VNEDU_ID);
        payVNPTRq.setSERVICE_PROVIDER_ID(VNPTConstant.SERVICE_PROVIDER_VNEDU);
        payVNPTRq.setPAYMENT_CODE(billRequest.getCustomerCode());
        payVNPTRq.setCHANNEL_ID(channel);
        //payVNPTRq.setTRANS_REQUEST_ID("201991214118791255");
        payVNPTRq.setADDITIONAL_INFO(billRequest.getAddInfo());
        payVNPTRq.setOPTIONS(billRequest.getOptions());
        payVNPTRq.setBILL_AMOUNT(billRequest.getPayAmt());
        String billDtlRq = billRequest.getBillDetails();
        payVNPTRq.setBILL_DETAIL(billDtlRq);
        String transDate = Common.getDate("yyyyMMddHHmmss");
        payVNPTRq.setTRANS_DATE_TIME(transDate);

        String secureCode = VNPTConstant.ACTION_PAYMENT.concat("|")
                .concat(VNPTConstant.VERSION).concat("|")
                .concat(VNPTConstant.PARTNER_ID).concat("|")
                .concat(payVNPTRq.getSERVICE_ID()).concat("|")
                .concat(VNPTConstant.SERVICE_PROVIDER_VNEDU).concat("|")
                .concat(billRequest.getCustomerCode()).concat("|")
                .concat(channel).concat("|")
                .concat(payVNPTRq.getTRANS_REQUEST_ID()).concat("|")
                .concat(billRequest.getAddInfo()).concat("|")
                .concat(billRequest.getOptions()).concat("|")
                .concat(billRequest.getPayAmt()).concat("|")
                .concat(billDtlRq).concat("|")
                .concat(transDate).concat("|")
                .concat(VNPTConstant.SECRET_KEY);
        String secureCodeSHA256 = DigestUtils.sha256Hex(secureCode);
        
        System.out.println("secureCodeSHA256:" + secureCodeSHA256);
        LOGGER.info("secureCodeSHA256: " + secureCodeSHA256);
        
        payVNPTRq.setSECURE_CODE(secureCodeSHA256);
    }

    /**
     * CreatEntityPaymentVNPT
     *
     * @param payVNPTRq
     * @param billRequest
     * @throws java.lang.Exception
     */
    public void creatPaymentWaterVNPT(PaymentVNPTRq payVNPTRq, BillPaymentRq billRequest) throws Exception {

        String channel = this.convertToChannelVNPT(billRequest.getChannel());
        payVNPTRq.setACTION(VNPTConstant.ACTION_PAYMENT);
        payVNPTRq.setVERSION(VNPTConstant.VERSION);
        payVNPTRq.setPARTNER_ID(VNPTConstant.PARTNER_ID); //262125 VNPT
        if ("NUOC".equals(billRequest.getIdservicetype())) {//NUOC:240 - VNEDU:32 
            payVNPTRq.setSERVICE_ID(VNPTConstant.SERVICE_WATER_ID);
        } else {
            payVNPTRq.setSERVICE_ID(VNPTConstant.SERVICE_VNEDU_ID);
        }

        payVNPTRq.setSERVICE_PROVIDER_ID(billRequest.getIdprovider());
        payVNPTRq.setPAYMENT_CODE(billRequest.getCustomerCode());
        payVNPTRq.setCHANNEL_ID(channel);
        payVNPTRq.setTRANS_REQUEST_ID(payVNPTRq.getTRANS_REQUEST_ID());
        payVNPTRq.setADDITIONAL_INFO(billRequest.getAddInfo() == null ? "" : billRequest.getAddInfo());
        payVNPTRq.setOPTIONS(billRequest.getOptions());
        payVNPTRq.setBILL_AMOUNT(billRequest.getPayAmt());
        /*
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.serializeNulls();
        Gson gson = builder.create();
        List<BillingDtlRq> BillingWaterDtl = new ArrayList<>();
        for (BillInfo bill : billRequest.getBills()) {
            BillingDtlRq billDtl = new BillingDtlRq();
            billDtl.setAmount(String.valueOf(bill.getMoneyAmount()));
            billDtl.setMonth(bill.getMonth());
            BillingWaterDtl.add(billDtl);
        }
        String billDtlRq = gson.toJson(BillingWaterDtl);
         */
        payVNPTRq.setBILL_DETAIL(billRequest.getBillDetails());
        String transDate = Common.getDate("yyyyMMddHHmmss");
        payVNPTRq.setTRANS_DATE_TIME(transDate);

        String secureCode = VNPTConstant.ACTION_PAYMENT.concat("|")
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
        
        System.out.println("secureCodeSHA256:" + secureCodeSHA256);
        LOGGER.info("secureCodeSHA256: " + secureCodeSHA256);

        payVNPTRq.setSECURE_CODE(secureCodeSHA256);
    }

    /**
     * CreatePaymentResponseSCB
     *
     * @param resp
     * @param payVNPTResp
     */
    public void createPaymentResponseWater(TxnCommitRp resp, PaymentVNPTResp payVNPTResp) {
        resp.setErrorCode(payVNPTResp.getRESPONSE_CODE());
        resp.setErrorMsg(payVNPTResp.getDESCRIPTION());
        resp.setTxnType(resp.getTxnType());
        resp.setTxnCommitTime(payVNPTResp.getTRANS_DATE_TIME());
        resp.setTxnIdPartner(payVNPTResp.getTRANS_RESPONSE_ID());
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
            mbMobile = Helper.getDBI().getCustomerFromMobile(pep.getUserName());
            if (mbMobile == null || mbMobile.isEmpty()) {
                return String.valueOf(idBillPaid);
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
            billpaid.setIdchannel(pep.getChannel());
            billpaid.setUsertype("1");
            billpaid.setRefPartner("");
            billpaid.setRefFcubs("");
            billpaid.setTotalamount(Long.valueOf(pep.getPayAmt()));
            billpaid.setPaymentmethod(2);//bang tk
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

    private static class BillingVNPTValidation {
        
        private static String payBillingVNPTValidation(BillPaymentRq billRequest) {
            String result = "00";
            return result;
        }
    }
}
