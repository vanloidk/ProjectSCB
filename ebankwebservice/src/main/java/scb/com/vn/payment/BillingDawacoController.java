/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.payment.Billing;
import scb.com.vn.common.model.payment.Request;
import scb.com.vn.common.model.payment.RequestPayment;
import scb.com.vn.common.model.payment.ResponsePayment;
import scb.com.vn.common.model.payment.Vntopup;
import scb.com.vn.constant.CommonConstant;
import scb.com.vn.controller.ControllerImpl;
import scb.com.vn.controller.ExchangePaybill;
import scb.com.vn.controller.Fcubs;
import scb.com.vn.controller.MobileController;
import static scb.com.vn.controller.MobileController.getPaymentMsgEnum;
import static scb.com.vn.controller.MobileController.insPblLog;
import static scb.com.vn.controller.MobileController.isFlgPrepaid;
import scb.com.vn.dbi.dto.PblBillpaid;
import scb.com.vn.dbi.dto.PblPartnerservice;
import scb.com.vn.dbi.dto.PblPartnerserviceId;
import scb.com.vn.dbi.dto.PblProvider;
import scb.com.vn.dbi.dto.PblServicetype;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.message.Message;
import scb.com.vn.model.BillPaymentRq;
import scb.com.vn.model.GetBillInfoRp;
import scb.com.vn.model.GetBillInfoRq;
import scb.com.vn.model.TxnCommitRp;
import scb.com.vn.payment.model.Processor;
import scb.com.vn.ultility.Common;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.MessageMB;
import vn.com.scb.bian.ExecutePaymentTransactionInternal_out_Type;
import vn.com.scb.bian.service.IIBPaymentService;
import vn.com.scb.bian.utility.IIBConstants;

/**
 *
 * @author tamnt12
 */
public class BillingDawacoController {

    //Tham so dawaco    
    String dawacoWsUrladdress = Configuration.getProperty("ws.url.dawaco.address");
    int dawacowstimeout = Integer.parseInt(Configuration.getProperty("ws.url.dawaco.timeout"));
    String dawacoClientid = ControllerUtil.EncriptMD5(Configuration.getProperty("ws.dawaco.clientid"));
    String dawacoClientpassword = ControllerUtil.EncriptMD5(Configuration.getProperty("ws.dawaco.clientpassword"));
    String dawacoAgentCode = Configuration.getProperty("ws.dawaco.agentcode");

    String producttransferPaybill = Configuration.getProperty("fcubs.producttransfer.mobile.paybill");
    String useridMobile = Configuration.getProperty("fcubs.userid.mobile");

    private static final Logger logger = Logger.getLogger(BillingDawacoController.class);

    final String BILL_PAYMENTRULE_ALL = "1", BILL_PAYMENTRULE_ORDER = "2", BILL_PAYMENTRULE_ANY = "3", BILL_PAYMENTRULE_AMOUNT = "5";
    final String BILL_ISPREPAID = "1", BILL_NOT_ISPREPAID = "2";

    //lay thong tin bill
    public String getBillInfoDawaco(GetBillInfoRq req, BillPaymentRq billRequest) throws Exception {
        try {
            GetBillInfoRp response = new GetBillInfoRp();
            billRequest = new BillPaymentRq();

            BeanUtils.copyProperties(billRequest, req);
            if (billRequest.getCustomerCode() != null) {
                billRequest.setCustomerCode(billRequest.getCustomerCode().trim());
            }

            //call dawaco lay thong tin bill
            callRequestPaymentDawaco(billRequest, "QUERY");

            createQueryBillResponse(billRequest,response);
            
            if (!billRequest.getResult().equals("0")) {
                insPblLog(billRequest, "Truy vấn Bill không thành công. - Lỗi giao dịch: " + Message.getMessagePaymentResult(Message.PaymentResultEnum.get(billRequest.getResult())));
                return Xml.Marshaller(response.getMBResponse(Message.PaymentResultEnum.get(billRequest.getResult())));
            }
            

            return Xml.Marshaller(response.getMBResponse(MessageMB.MobileResultEnum.OK));

        } catch (Exception ex) {
            logger.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            GetBillInfoRp resp01 = new GetBillInfoRp();
            resp01.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
            resp01.setErrorMsg("getBillInfoDawaco: co loi trong qua trinh xu ly tai gateway!");
            return Xml.Marshaller(resp01);
        }
    }

    public String billPaymentDawaco(BillPaymentRq pepe) throws Exception {

        TxnCommitRp response = new TxnCommitRp();
        response.setCifNo(pepe.getCifNo());
        response.setTxnType(pepe.getTxnType());
        response.setAccountNo(pepe.getDebitAccount());

        try {

            //1. Kiem tra Bill & tai khoan phai hop le
            //@TODO:
            //2. Xu ly thanh toan BILL & Chuyen tien.
            //2.1 Lay lai thong tin BIll             
            //IIB Payment   
            Request r = new Request();

            pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));
            Billing b = new Billing();
            b.setCustomercode(pepe.getCustomerCode());
            b.setAmount(pepe.getPayAmt());

            r.setBilling(b);

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

            paybillDawaco(pepe, response);

//            if (!pepe.getResult().equals("0")) {
//                return Xml.Marshaller(response.getMBFinResponse(Message.PaymentResultEnum.get(pepe.getResult())));
//            }
            if (!pepe.getResult().equals("0")) {
                return Xml.Marshaller(response);
            } else {
                if (pepe.getRefBillpaid() != null) {
                }
                if (pepe.getRefBillpaid() == null) {
                    return Xml.Marshaller(response);
                } else {
                    response.setTxnID(pepe.getRefBillpaid());
                    response.setTxnCommitTime(getTime());
                }
            }
            return Xml.Marshaller(response);

        } catch (Exception ex) {
            logger.error(ExceptionUtils.getRootCauseMessage(ex), ex);
            response.setErrorCode(CommonConstant.EXCEPTION_GATEWAY);
            response.setErrorMsg("paybillDawaco: co loi trong qua trinh xu ly tai gateway!");
            return Xml.Marshaller(response);
        }
    }

    public void paybillDawaco(BillPaymentRq pepe, TxnCommitRp response) throws Exception {
        String callPay = "0";
        try {
            //lay thong tin bill
            logger.warn("GET BILL:" + pepe.getIdprovider());
            callRequestPaymentDawaco(pepe, "QUERY");

            //2.2 KIem tra tai khoan du thanh toan
            // Kiem tra partner cua luong thanh toan nay la gi. Neu la VNPAY thi skip qua ko validate tong so tien
            String idPartner = ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider());
            String amountQuery = pepe.getRespay().getResponse().getBilling().getAmount();
            if (!pepe.getResult().equals("0")
                    || (BILL_PAYMENTRULE_ALL.equals(pepe.getPaymentRule())
                    && !"VNPAY".equalsIgnoreCase(idPartner)
                    && !pepe.getPayAmt().equalsIgnoreCase(amountQuery))) {
                logger.warn("Khong the thuc hien thanh toan hoa don cho khach hang. Result = [" + pepe.getResult() + "], PaymentRule = [" + pepe.getPaymentRule() + "], PayAmt = [" + pepe.getPayAmt() + "], Response query amount = [" + amountQuery + "]");
                //return showmessage(pepe.getPep(), PaymentResultEnum.get(pepe.getResult()), "3", "Truy vấn bill để thực hiện trong quá trình thanh toán không thành công " + getMsgResult(pepe.getResult(), idlanguage));
                insPblLog(pepe, "Truy vấn bill để thực hiện trong quá trình thanh toán không thành công. " + Message.getMessagePaymentResult(Message.PaymentResultEnum.get(pepe.getResult())));
                return;
            }

            pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));

            logger.warn("getCustAccountByAccountNo:" + pepe.getDebitAccount());
            VwCustAccount custacc = Helper.getDBI().getCustAccountByAccountNo(pepe.getDebitAccount());
            if (custacc == null) {
                response.setErrorCode("07");
                response.setErrorMsg("TK khong ton tai!");
                return;
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
                pepe.setResult(Message.PaymentResultEnum.NOT_MATCH_CCY_VND.getValue());
                return;
            }
            logger.warn("checkAmountToPaybill:" + pepe.getPayAmt());
            Message.PaymentResultEnum checkbill = checkAmountToPaybill(pepe.getPayAmt(), custacc);

            if (!checkbill.equals(Message.PaymentResultEnum.OK)) {
                pepe.setResult(Message.PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY.getValue());
                return;
            }

            //2.4 Thuc hien cap nhap process            
            //pepe.getPep().setOtptype(reqexchg.getReq().getOtp().getType());
            //pepe.getPep().setOtp(reqexchg.getReq().getOtp().getTxnpin());
            //2.3 Thuc hien chuyen tien
            PblBillpaid billpaid = new PblBillpaid();
            String refCub = "";
            //lay tk treo theo ma xi nghiep
            String BranchDawaco = pepe.getRespay().getProvidercode();

            try {
                logger.warn("transferPaybill:" + pepe.getPayAmt());
                refCub = transferPaybill(pepe, custacc, billpaid, "transfer");
            } catch (Exception e) {
                logger.error("***- ERROR MB (FUNCTION paybill): " + e.getMessage());
                pepe.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
                insPblLog(pepe, "Trích tiền từ tài khoản thanh toán xảy ra ngoại lệ error 2 exception=" + e.getMessage().substring(0, 500));
                response.setErrorCode(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
                response.setErrorMsg("Loi khi goi Core hach toan!");
                return;
            }
            if (refCub == null || refCub.equalsIgnoreCase("")) {
                pepe.setResult(Message.PaymentResultEnum.CANNOT_TRANSFERFCUBS.getValue());
                insPblLog(pepe, "Không thực hiện được trích tiền từ tài khoản thanh toán khách hàng.");
                response.setErrorCode(CommonConstant.PAYMENT_CORE_EMPTY);
                response.setErrorMsg("Chuyen khoan FCC tra ve loi: " + refCub);
                return;
            }
            if (refCub.equalsIgnoreCase("TIMEOUT")) {
                pepe.setResult(Message.PaymentResultEnum.CANNOT_TRANSFERFCUBS.getValue());
                insPblLog(pepe, "Trích tiền từ tài khoản thanh toán khách hàng timeout. Giao dịch thất bại.");
                response.setErrorCode(CommonConstant.PAYMENT_CORE_TIMEOUT);
                response.setErrorMsg("Chuyen khoan FCC tra ve loi: " + refCub);
                return;
            }
            callPay = "1";

            //2.5 Thuc hien thanh toan Bill
            pepe.setResult("-99");

            //Gan customer code cho customer name
//            if (isFlgPrepaid(pepe.getCustomerNameBill(), pepe.getIdprovider())) {
//                pepe.setCustomerCode(pepe.getCustomerNameBill());
//            }
            callPay = "2";
            callRequestPaymentDawaco(pepe, "PAY");
            //gia lap de test
//            pepe.setResult("0");

            //2.6 Cap nhap thong tin giao dich neu giao dich khong thanh cong thi tra lai hoa don cho KH
            Date d = new Date();
            billpaid.setPaydate(d);
            //xoa bot thong tin addinfo billing do vuot qua chieu dai cot DATAXML
            pepe.getRespay().getResponse().getBilling().setAddinfo("");
            
            billpaid.setDataxml(Xml.Marshaller(pepe.getRespay()));
            billpaid.setRefPartner(BranchDawaco + "#" + pepe.getRespay().getTranscode());
            
            response.setErrorCode(pepe.getResult());
            response.setErrorMsg(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(pepe.getResult())));

            if (!pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())) {
                //hoach toan tra lai tai khoan cho khach hang khi thanh toan hoa don khong thanh cong

                //Update code hoan tien moi
                /**
                 * DIEU CHINH HOAN TIEN THEO QUY DINH DICH VU HOAN TIEN -> TRANG
                 * THAI: F TREO -> TRANG HAI: H
                 *
                 */
                if (pepe.getResult().equals(Message.PaymentResultEnum.ERROR_PARTNER_REFUND.getValue())) {
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
                        pepe.setResult(Message.PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());
                        insPblLog(pepe, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                        logger.info(String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                        return;
                    } else if (result.equalsIgnoreCase("0")) {
                        billpaid.setStatus(Character.valueOf('F'));
                        updateBillStatusToDB(billpaid, 'F');
                        insPblLog(pepe, String.format(_msgouttransfer, "THANH CONG", fccRef));
                        logger.info(String.format(_msgouttransfer, "THANH CONG", fccRef));
                        return;
                    } else {
                        //Revert khong thanh cong, tra soat hoan tien cho KH
                        billpaid.setStatus(Character.valueOf('H'));
                        updateBillStatusToDB(billpaid, 'H');
                        pepe.setResult(Message.PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());
                        insPblLog(pepe, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                        logger.info(String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                        return;
                        
                    }
                } else if (pepe.getResult().equals(Message.PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue())) {
                    //Khong hoan tien, yeu cau doi tac gach bu giao dich
                    String a = "PAYBILL IS NOT SUCCESS. Hold tien KH, yeu cau doi tac gach bu tay. [idbillpaid-%1$s]";
                    billpaid.setStatus(Character.valueOf('H'));
                    updateBillStatusToDB(billpaid, 'H');
                    insPblLog(pepe, String.format(a, billpaid.getIdbillpaid()));
                    logger.info(String.format(a, billpaid.getIdbillpaid()));
                    return;
                    //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
//                    }
                } else {
                    //Giao dich nghi van, thuc hien doi soat
                    //Hold toan bo giao dich
                    billpaid.setStatus(Character.valueOf('H'));
                    updateBillStatusToDB(billpaid, 'H');
                    pepe.setResult(Message.PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());
                    String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong, thuc hien doi soat. [idbillpaid-%1$s]";
                    insPblLog(pepe, String.format(a, billpaid.getIdbillpaid()));
                    logger.info(String.format(a, billpaid.getIdbillpaid()));
                    return;
                }
            }   
            billpaid.setStatus(Character.valueOf('D'));
            Helper.getDBI().updatePaybillBillPaid(billpaid);
        } catch (Exception ex) {
            logger.error("***- ERROR MB (FUNCTION paybill 2): " + ex.getMessage());
            insPblLog(pepe, "Giao dịch thanh toán paybill của khách hàng đã xãy ra ngoại lệ error 3 exception=" + ex.getMessage());
            //return showmessage(pep, PaymentResultEnum.SYSTEM_ERROR, "4", "Giao dịch thanh toán paybill của khách hàng đã xãy ra ngoại lệ error 3 exception=" + ex.getMessage().substring(0, 500));
            if (callPay.equals("0")) {
                pepe.setResult(Message.PaymentResultEnum.CANNOT_TRANSFERFCUBS.getValue());
            } else if (callPay.equals("1")) {
                pepe.setResult(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
            }
            response.setErrorCode(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
            response.setErrorMsg("Loi khi goi ham paybillDawaco tại gateway");
        }

    }

    public static String callRequestPaymentDawaco(BillPaymentRq pepe, String processingcode) {
        try {
//
            Request r = new Request();
            pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));
            Billing b = new Billing();

            b.setCustomercode(pepe.getCustomerCode());
            b.setAmount(pepe.getBillAmt());

            if (processingcode.equals("PAY")) {

                if (pepe.getRespay() != null) {
                    if (pepe.getRespay().getResponse() != null) {
                        if (pepe.getRespay().getResponse().getBilling() != null) {
                            String billingAdinfo = pepe.getRespay().getResponse().getBilling().getAddinfo() == null ? "" : pepe.getRespay().getResponse().getBilling().getAddinfo();
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

            }
            r.setBilling(b);

            RequestPayment reqpay = new RequestPayment();
            reqpay.setChannel("MOBILE");
            reqpay.setServicecode(pepe.getIdservicetype());
            reqpay.setProvidercode(pepe.getIdprovider());
            reqpay.setProcessingcode(processingcode);
            reqpay.setPaymentmethod("ACCOUNT");
            reqpay.setAccountno(pepe.getDebitAccount());
            reqpay.setDatetime(Common.getDate("ddMMyyyyHHmmss"));
            reqpay.setRequest(r);

            String rsxml = null;

            ResponsePayment responsepay = null;
            if (processingcode.equals("QUERY")) {
                String xml = Xml.Marshaller(reqpay);
                ControllerImpl ci = new ControllerImpl();
                rsxml = ci.requestPayment(xml);
                responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, rsxml);

            } else if (processingcode.equals("PAY")) {

                String xml = Xml.Marshaller(reqpay);
                ControllerImpl ci = new ControllerImpl();
                rsxml = ci.requestPayment(xml);
                responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, rsxml);
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

    private Message.PaymentResultEnum checkAmountToPaybill(String AmountBill, VwCustAccount custacc) {

        BigDecimal billAmount = new BigDecimal(AmountBill);
        // 2.1 Neu khong tra ve thong bao so du TK khong du de thuc hien GD
        if (billAmount.floatValue() <= 0) {
            return Message.PaymentResultEnum.BILL_PAID;
        }

        if (custacc.getAccountClass().substring(0, 3).equals("CAI") || custacc.getAccountClass().substring(0, 3).equals("CAC")) {
            if (custacc.getAcyAvlBal().compareTo(billAmount) == -1) // 0:=,1: 1>2; -1:1<2
            {
                return Message.PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY;
            }
        }

        return Message.PaymentResultEnum.OK;
    }

    //Thuc hien hoac toan chuyen tien thanh toan hoa don
    private String transferPaybill(BillPaymentRq pep, VwCustAccount from_custacc, PblBillpaid billpaid, String transfer_type) {
        try {
            String msgpay = "";
            String msgback = "";

            //1.1 Lay tai treo theo doi doi voi nha cung cap dich vu
            PblPartnerserviceId psid = new PblPartnerserviceId();
            psid.setIdpartner(pep.getIdpartner());
            psid.setIdprovider(pep.getIdprovider());
            psid.setIdservicetype(pep.getIdservicetype());
            PblPartnerservice ps = Helper.getDBI().getPartnerServiceById(psid);
            //1.2 Thuc hien chuyen khoan
            Fcubs fc = new Fcubs();
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

                String partnerDetailId = pep.getRespay().getProvidercode();
                String accountTo = Helper.getDBI().getAccountProvider("DAWACO_" + partnerDetailId);
                refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), accountTo, billAmount, msgpay);
//                refCub = "069PB01201951524";
                //BCN edit (S)   
                logger.info("***- LOG THANH TOAN MB (FUNCTION transferPaybill): Ma Khach Hang: " + billpaid.getCustomercode() + " -- So tham chieu trich tien REFCUBS= " + refCub + " -- From Account :" + from_custacc.getCustAcNo());
                if (refCub == null || refCub.equalsIgnoreCase("")) {
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

    private static void checkAmount(BillPaymentRq pepe) {
        if (pepe.getRespay().getResponse().getBilling() == null) {
            pepe.setResult(pepe.getRespay().getResult());//chuong
        } else {
            pepe.setBillAmt(pepe.getRespay().getResponse().getBilling().getAmount());
        }
    }
    
    private String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }
    
    private void createQueryBillResponse(BillPaymentRq billRequest, GetBillInfoRp response) throws Exception{
        
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.serializeNulls();
        Gson gson = builder.create();
        
//        logger.info("BILL:" + Xml.Marshaller(billRequest));
        response.setErrorCode(billRequest.getResult());
        response.setErrorMsg(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(billRequest.getResult())));
        response.setAddinfo(billRequest.getAddInfo());
        response.setBillAmt(billRequest.getBillAmt());
        
//        response.setBillDetails(gson.toJson(billRequest.getBillDetails()));
        if(billRequest.getResult().equals("0")){
            response.setIsPrepaid(BILL_NOT_ISPREPAID);
            response.setPaymentRule(BILL_PAYMENTRULE_ALL);
            response.setCustomerCode(billRequest.getCustomerCode());
            response.setCustomername(billRequest.getRespay().getResponse().getBilling().getCustomername());
            response.setAddress(billRequest.getRespay().getResponse().getBilling().getAddress());
        }
        
//        response.setTxnFee(billRequest.get);
        
        
    }

}
