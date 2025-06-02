/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import org.apache.commons.beanutils.BeanUtils;

import scb.com.vn.model.*;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.Helper;
import java.util.List;

import scb.com.vn.common.model.payment.Billing;
import scb.com.vn.common.model.payment.Request;
import scb.com.vn.common.model.payment.RequestPayment;
import scb.com.vn.common.model.payment.Response;
import scb.com.vn.common.model.payment.ResponsePayment;

import scb.com.vn.controller.ControllerImpl;
import scb.com.vn.controller.ExchangePaybill;
import scb.com.vn.controller.Fcubs;

import scb.com.vn.dbi.dto.EBANKUSER;

import scb.com.vn.dbi.dto.PblBillpaid;
import scb.com.vn.dbi.dto.PblLog;
import scb.com.vn.dbi.dto.PblPartnerservice;
import scb.com.vn.dbi.dto.PblPartnerserviceId;
import scb.com.vn.dbi.dto.PblProvider;
import scb.com.vn.dbi.dto.PblServicetype;

import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.dbi.dto.VwCustAccountNew;

import scb.com.vn.message.Message.PaymentResultEnum;

import scb.com.vn.payment.model.Processor;
import scb.com.vn.ultility.Common;
import scb.com.vn.utility.Configuration;
import scb.com.vn.message.Message;

import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.MessageMB;

import scb.com.vn.utility.MessageMB.MobileResultEnum;
import vn.com.scb.bian.*;
import vn.com.scb.bian.service.IIBBillingPaymentService;

import vn.com.scb.bian.service.IIBCustomerService;
;
import vn.com.scb.bian.service.IIBPaymentService;
import vn.com.scb.bian.utility.IIBConstants;

/**
 *
 * @author
 */


public class ONLPblController {

    final int _SUCESSFUL = 0, _UNSUCESSFUL = -1;
    final char RELOAD_ACC_LIST = 1, UN_RELOAD_ACC_LIST = 0;
    final String ACCOUNT_CASA = "U", ACCOUNT_TD = "Y", ACCOUNT_LOAN = "L", ACCOUNT_MASTERCARD = "M", ACCOUNT_MASTERCARD_MC = "MC", ACCOUNT_MASTERCARD_MD = "MD";
    final String BILL_ISPREPAID = "1", BILL_NOT_ISPREPAID = "2";
    final String BILL_PAYMENTRULE_ALL = "1", BILL_PAYMENTRULE_ORDER = "2", BILL_PAYMENTRULE_ANY = "3", BILL_PAYMENTRULE_AMOUNT = "5";
    final String ATMISSUE_TYPE_NEW = "1", ATMISSUE_TYPE_REISSUE = "2", ATMISSUE_TYPE_EXT = "3", ATMISSUE_TYPE_SLAVE = "4";
    final String ID_CHANNEL_ONLPAYMENT = "12";
    final String ALERTTD_ON = "ON", ALERTTD_OFF = "OFF";
    String MaturityInstr_AutoRoll_Both = "1";
    String MaturityInstr_AutoRoll_Principal = "2";
    String MaturityInstr_Close = "3";
    String MaturityInstr_AutoRoll_Special = "4";
    // String producttransferPaybill = Configuration.getProperty("fcubs.producttransfer.onlinetransfer");    

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

    String useridfcubs = Configuration.getProperty("fcubs.userid");

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
     * @param xml
     * @return
     */
    public String GetBillInfo(String xml) {
        try {
            GetBillInfoRq req = (GetBillInfoRq) Xml.unMarshaller(GetBillInfoRq.class, xml);
            GetBillInfoRp response = new GetBillInfoRp();
            BillPaymentRq billRequest = new BillPaymentRq();

            BeanUtils.copyProperties(billRequest, req);
            if (billRequest.getCustomerCode() != null) {
                billRequest.setCustomerCode(billRequest.getCustomerCode().trim());
            }

            callRequestPayment(billRequest, "QUERY");

            if (!billRequest.getResult().equals("0")) {
                insPblLog(billRequest, "Truy vấn Bill không thành công. - Lỗi giao dịch: " + Message.getMessagePaymentResult(PaymentResultEnum.get(billRequest.getResult())));
                return Xml.Marshaller(response.getMBResponse(PaymentResultEnum.get(billRequest.getResult())));
            } else {
                Response paymentRes = billRequest.getRespay().getResponse();
                response.setIsPrepaid(BILL_NOT_ISPREPAID);
                response.setPaymentRule(BILL_PAYMENTRULE_ALL);
                response.setCustomerCode(req.getCustomerCode());
                if (req.getIdservicetype().equals(Processor.ServiceTypeEnum.DIEN.toString())) {

                    response.setAddress(paymentRes.getElectric().getAddress());
                    response.setCustomername(paymentRes.getElectric().getName());
                    response.setBillAmt(paymentRes.getElectric().getAmount());
                }
                String BillDetail = billRequest.getRespay().getTranscode();
                String[] listResponseBill = BillDetail.replace("#", "").split("\\%");
                BillInfo[] list = new BillInfo[listResponseBill.length];
                for (int j = 0; j < listResponseBill.length; j++) {
                    String[] detail = listResponseBill[j].split("\\|");
                    BillInfo billing = new BillInfo();
                    billing.setBillId(detail[0]);

                    billing.setMoneyAmount(BigDecimal.valueOf(Long.valueOf(detail[1].trim())));
                    list[j] = billing;
                }
                response.setBills(list);
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
    public String BillPayment(String xml) {
        try {
            BillPaymentRq billRequest = (BillPaymentRq) Xml.unMarshaller(BillPaymentRq.class, xml);
            TxnCommitRp response = new TxnCommitRp();
            response.setCifNo(billRequest.getCifNo());
            response.setTxnType(billRequest.getTxnType());
            response.setAccountNo(billRequest.getDebitAccount());
            if (billRequest.getCustomerCode() != null) {
                billRequest.setCustomerCode(billRequest.getCustomerCode().trim());
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
                if (billRequest.getRefBillpaid() == null) {
                    return Xml.Marshaller(response.getMBFinResponse(PaymentResultEnum.get(billRequest.getResult())));
                } else {
                    response.setTxnID(billRequest.getRefBillpaid());
                    response.setTxnCommitTime(getTime());
                }
            }
            response.setErrorCode(MobileResultEnum.OK.getValue());
            response.setErrorMsg(MessageMB.getMessageMBResult(MobileResultEnum.OK));
            return Xml.Marshaller(response);

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
            pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));
            Billing b = new Billing();

            b.setCustomercode(pepe.getCustomerCode());

            if (processingcode.equals("PAY")) {
                b.setAmount(pepe.getPayAmt());

                //Reset customer code for prepaid case, SCTV6, ACS
                if (isFlgPrepaid(pepe.getCustomerCode(), pepe.getIdprovider())) {
                    b.setCustomername(pepe.getCustomerCode());
                    b.setCustomercode(pepe.getCustomerCode().split("@")[0]);

                }
            }
            r.setBilling(b);
            RequestPayment reqpay = new RequestPayment();
            //reqpay.setTranscode(String.valueOf("AT1"));
            //reqpay.setChannel("INTERNET");
            reqpay.setChannel("ONLPAYMENT");
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
                String xml = Xml.Marshaller(reqpay);
                ControllerImpl ci = new ControllerImpl();
                rsxml = ci.requestPayment(xml);
                //thiet lap gia tri tra ve
                responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, rsxml);

            } else if (processingcode.equals("PAY")) {
                if (pepe.getIdpartner().endsWith("EVNHN")) {
                    String listbill = "";
                    for (int i = 0; i < pepe.getBills().length; i++) {
                        listbill = listbill + pepe.getBills()[i].getBillId() + "|" + pepe.getBills()[i].getMoneyAmount() + "%";
                    }
                    reqpay.setTranscode(listbill);

                }
                String xml = Xml.Marshaller(reqpay);
                ControllerImpl ci = new ControllerImpl();
                rsxml = ci.requestPayment(xml);
                //thiet lap gia tri tra ve
                responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, rsxml);
            }
            //Helper.writeLogError(ONLPblController.class, "***- RETURN FROM WS " + rsxml);
            //thiet lap gia tri tra ve

            if (isFlgPrepaid(pepe.getCustomerCode(), pepe.getIdprovider())) {
                pepe.setCustomerCode(pepe.getCustomerCode().split("@")[0]);
                responsepay.getResponse().getBilling().setAmount(pepe.getPayAmt());
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
            Helper.writeLogError(ONLPblController.class, "***- ERROR MB (FUNCTION callRequestPayment): " + ex.getMessage());
            pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue());

            return null;
        }
    }

    private void paybill(BillPaymentRq pepe) throws Exception {
        try {
            Request r = new Request();
            pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));
            Billing b = new Billing();
            b.setCustomercode(pepe.getCustomerCode());
            b.setAmount(pepe.getPayAmt());

            r.setBilling(b);
            RequestPayment reqpay = new RequestPayment();

            reqpay.setChannel("ONLPAYMENT");
            reqpay.setServicecode(pepe.getIdservicetype());
            reqpay.setProvidercode(pepe.getIdprovider());
            reqpay.setProcessingcode("QUERY");
            reqpay.setPaymentmethod("ACCOUNT");
            reqpay.setAccountno(pepe.getDebitAccount());

            reqpay.setDatetime(Common.getDate("ddMMyyyyHHmmss"));
            reqpay.setRequest(r);
            //IIB Payment                                                 
            IIBBillingPaymentService iibBillingService = new IIBBillingPaymentService();
            ResponsePayment responsepay = iibBillingService.callPartnerGateway(Configuration.getIIBContext(), reqpay);

            if (responsepay.getResult().equals(Message.PaymentResultEnum.OK.getValue()) && responsepay.getPartnercode().equals(IIBConstants.PARTNER_BANKNET)) {
                executePayBillIIB(reqpay, pepe);
            } else {
                //Với các loại bill trả trước thì ko query lại, do IdBillPaid sẽ thay đổi
                if (isFlgPrepaid(pepe.getCustomerNameBill(), pepe.getIdprovider()) == false) {
                    callRequestPayment(pepe, "QUERY");
                    //Kiem tra danh sach thanh toan va danh sach tra ve co khop
                    String BillDetail = pepe.getRespay().getTranscode();
                    if (pepe.getResult().equals("0")) {
                        String[] listResponseBill = BillDetail.replace("#", "").split("\\%");
                        BillInfo[] list = new BillInfo[listResponseBill.length];
                        for (int i = 0; i < pepe.getBills().length; i++) {
                            String billid = pepe.getBills()[i].getBillId();
                            int check = 0;
                            for (int j = 0; j < listResponseBill.length; j++) {
                                String[] detail = listResponseBill[j].split("\\|");
                                if (billid.equals(detail[0])) {
                                    check = 1;
                                }
                            }
                            if (check == 0) {
                                pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_NOTEXIST.getValue());
                                insPblLog(pepe, "Truy vấn bill để thực hiện trong quá trình thanh toán không thành công. " + Message.getMessagePaymentResult(PaymentResultEnum.get(pepe.getResult())));
                                return;
                            }
                        }
                    } else {
                        //return showmessage(pepe.getPep(), PaymentResultEnum.get(pepe.getResult()), "3", "Truy vấn bill để thực hiện trong quá trình thanh toán không thành công " + getMsgResult(pepe.getResult(), idlanguage));
                        insPblLog(pepe, "Truy vấn bill để thực hiện trong quá trình thanh toán không thành công. " + Message.getMessagePaymentResult(PaymentResultEnum.get(pepe.getResult())));
                        return;
                    }
                } else {
                    pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));
                }

                VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(pepe.getDebitAccount());
                //kiem tra loai tien cua TK TT
                if (!custacc.getCcy().equals("VND")) {
                    pepe.setResult(PaymentResultEnum.NOT_MATCH_CCY_VND.getValue());
                    return;
                }
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
                //pepe.setIdpartner(ControllerUtil.getIdPartner(pepe.getIdservicetype(), pepe.getIdprovider()));
                try {
                    refCub = transferPaybill(pepe, custacc, billpaid, "transfer");
                } catch (Exception e) {
                    //**error 2**/
                    Helper.writeLogError(ONLPblController.class, "***- ERROR MB (FUNCTION paybill): " + e.getMessage());
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
                //billpaid.setIdbillpaid(idBillPaid);

                //2.5 Thuc hien thanh toan Bill
                pepe.setResult("-99");

                //Gan customer code cho customer name
                if (isFlgPrepaid(pepe.getCustomerNameBill(), pepe.getIdprovider())) {
                    pepe.setCustomerCode(pepe.getCustomerNameBill());
                }
                callRequestPayment(pepe, "PAY");

                //2.6 Cap nhap thong tin giao dich neu giao dich khong thanh cong thi tra lai hoa don cho KH
                //cap nhap lai thong tin billpaid
                //billpaid = Helper.getDBI().getPaybillBillPaidById(idBillPaid);
                Date d = new Date();
                billpaid.setPaydate(d);
                billpaid.setDataxml(Xml.Marshaller(pepe.getRespay()));
                billpaid.setRefPartner(pepe.getRespay().getTranscode());
                //pepe.setResult("96");
                if (!pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())) {
                    //pepe.getResult().equals(scb.com.vn.message.Message.PaymentResultEnum.OK.getValue())
                    if (pepe.getResult().equals(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue())) {
                        //Giao dich that bai, thuc hien hoan tien cho KH
                        String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong & hoan tien bill. [idbillpaid-%1$s]-[Trancode-%2$s]";
                        Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(a, billpaid.getIdbillpaid(), billpaid.getRefPartner()));

                        String _msgouttransfer = "TRANSFER UBS. Thuc hien HOAN TIEN HOAN TIEN %1$s. [So but toan-%2$s]";
                        String fccRef = billpaid.getRefFcubs();
                        String result = revertTransferFCUBS(fccRef, 60);

                        if (result == null) {
                            //Revert khong thanh cong, tra soat hoan tien cho KH
                            billpaid.setStatus(Character.valueOf('H'));
                            updateBillStatusToDB(billpaid, 'H');
                            pepe.setResult(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());
                            insPblLog(pepe, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                            Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                            return;
                            //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                        } else if (result.equalsIgnoreCase("0")) {
                            billpaid.setStatus(Character.valueOf('F'));
                            updateBillStatusToDB(billpaid, 'F');
                            insPblLog(pepe, String.format(_msgouttransfer, "THANH CONG", fccRef));
                            Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "THANH CONG", fccRef));
                            return;
                            //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Số giao dịch tham chiếu fcus trả lại tiền cho KH :" + fccRef);
                        } else {
                            //Revert khong thanh cong, tra soat hoan tien cho KH
                            billpaid.setStatus(Character.valueOf('H'));
                            updateBillStatusToDB(billpaid, 'H');
                            pepe.setResult(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());
                            insPblLog(pepe, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                            Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                            return;
                            //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                        }
                    } else if (pepe.getResult().equals(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue())) {
                        if (pepe.getIdservicetype().equals("VNTOPUP")) {
                            String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong & hoan tien bill. [idbillpaid-%1$s]-[Trancode-%2$s]";
                            Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(a, billpaid.getIdbillpaid(), billpaid.getRefPartner()));

                            String _msgouttransfer = "TRANSFER UBS. Thuc hien HOAN TIEN HOAN TIEN %1$s. [So but toan-%2$s]";
                            String fccRef = billpaid.getRefFcubs();
                            String result = revertTransferFCUBS(fccRef, 60);

                            if (result == null) {
                                //Revert khong thanh cong, tra soat hoan tien cho KH
                                billpaid.setStatus(Character.valueOf('H'));
                                updateBillStatusToDB(billpaid, 'H');
                                Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                                insPblLog(pepe, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                                return;
                                //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Chưa hoàn tiền lại cho khách hàng, chờ tra soát với đối tác.");
                            } else if (result.equalsIgnoreCase("0")) {

                                billpaid.setStatus(Character.valueOf('F'));
                                updateBillStatusToDB(billpaid, 'F');
                                pepe.setResult(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue());
                                insPblLog(pepe, String.format(_msgouttransfer, "THANH CONG", fccRef));
                                Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "THANH CONG", fccRef));
                                return;
                                //return showmessage(pep, PaymentResultEnum.get(pepe.getResult()), "4", "Hóa đơn thanh toán không thành công - Số giao dịch tham chiếu fcus trả lại tiền cho KH :" + fccRef);
                            } else {
                                //Revert khong thanh cong, tra soat hoan tien cho KH
                                billpaid.setStatus(Character.valueOf('H'));
                                updateBillStatusToDB(billpaid, 'H');
                                Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
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
                            Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(a, billpaid.getIdbillpaid()));
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
                        Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(a, billpaid.getIdbillpaid()));
                        return;
                    }
                }
                //cap nhap thoi gian cho giao dich voi VNPAY           
                billpaid.setStatus(Character.valueOf('D'));
                Helper.getDBI().updatePaybillBillPaid(billpaid);
            }
        } catch (Exception ex) {
            //**error 3**/
            Helper.writeLogError(ONLPblController.class, "***- ERROR MB (FUNCTION paybill 2): " + ex.getMessage());
            insPblLog(pepe, "Giao dịch thanh toán paybill của khách hàng đã xãy ra ngoại lệ error 3 exception=" + ex.getMessage());
            //return showmessage(pep, PaymentResultEnum.SYSTEM_ERROR, "4", "Giao dịch thanh toán paybill của khách hàng đã xãy ra ngoại lệ error 3 exception=" + ex.getMessage().substring(0, 500));
        }
    }

    //Thuc hien hoac toan chuyen tien thanh toan hoa don
    private String transferPaybill(BillPaymentRq pep, VwCustAccountNew from_custacc, PblBillpaid billpaid, String transfer_type) {
        try {
            String msgpay = "";
            String msgback = "";
            if (pep.getIdservicetype().equalsIgnoreCase("VEMAYBAY")) {
                msgpay = String.format(getPaymentMsgEnum(PaymentMsgEnum.THANHTOAN_VMB), pep.getCustomerCode().toUpperCase());
                msgback = getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_VMB);//String.format(getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_VMB), pep.getCustomercode());
            } else if (pep.getIdservicetype().equalsIgnoreCase("VNTOPUP")) {
                msgpay = String.format(getPaymentMsgEnum(PaymentMsgEnum.THANHTOAN_VNTOPUP), pep.getCustomerCode().toUpperCase());
                msgback = getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_VNTOPUP);//String.format(getPaymentMsgEnum(PaymentMsgEnum.HOAN_THANHTOAN_VNTOPUP), pep.getCustomercode());
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
            Helper.writeLogError(ONLPblController.class, "***- ERROR MB (FUNCTION transferPaybill): transfer_type:" + transfer_type);
            if (transfer_type.equalsIgnoreCase("transfer")) {

                //1.3 Insert bang BillPaid
                //PblBillpaid billpaid =new PblBillpaid();
                // VwMstchanneluser mstchanneluser = Helper.getDBI().getVwMstchanneluser(pep.getUserId());
                billpaid.setIdpartner(pep.getIdpartner());

                PblServicetype pst = new PblServicetype();
                pst.setIdservicetype(pep.getIdservicetype());
                billpaid.setPblServicetype(pst);

                PblProvider pdr = new PblProvider();
                pdr.setIdprovider(pep.getIdprovider());
                billpaid.setPblProvider(pdr);

                billpaid.setCustomercode(pep.getCustomerCode());
                billpaid.setDataxml("");
                billpaid.setIduserMaker(pep.getUserId());
                // billpaid.setIdchanneluserMaker(mstchanneluser.getIdchanneluser());
                billpaid.setIdchanneluserMaker(pep.getUserId());

                // billpaid.setIdchanneluserChecker(mstchanneluser.getIdchanneluser());
                billpaid.setIdchanneluserChecker(pep.getUserId());
                billpaid.setIduserChecker(pep.getUserId());
                billpaid.setIdchannel("01");
                billpaid.setUsertype("1");
                billpaid.setRefPartner("");
                billpaid.setRefFcubs("");
                billpaid.setTotalamount(Long.valueOf(pep.getPayAmt()));
                billpaid.setPaymentmethod(2);
                billpaid.setAccCust(from_custacc.getCustNo());
                billpaid.setAccIdaccount(from_custacc.getCustAcNo());
                billpaid.setAccHoldername(from_custacc.getFullName());
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
                String accountTo = "";
                if (pep.getIdpartner().equals("EVNHN")) {
                    String partnerDetailId = pep.getCustomerCode().substring(0, 4);
                    accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    if (pep.getRespay().getResponse().getElectric() != null && pep.getRespay().getResponse().getElectric().getMaHD() != null) {
                        msgpay = msgpay.concat("_HD:").concat(pep.getRespay().getResponse().getElectric().getMaHD());
                    }
                    refCub = fc.transferFCUBS(pep.getDebitAccount(), accountTo, billAmount, msgpay);
                    //refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), accountTo, billAmount, msgpay);
                } else if (pep.getIdpartner().equals("EVNSPC")) {
                    String partnerDetailId = pep.getCustomerCode().substring(0, 6);
                    accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    refCub = fc.transferFCUBS(pep.getDebitAccount(), accountTo, billAmount, msgpay);
                    //refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), accountTo, billAmount, msgpay);
                } else if (pep.getIdpartner().equals("EVNCPC")) {
                    String partnerDetailId = pep.getCustomerCode().substring(0, 6);
                    accountTo = Helper.getDBI().getAccountProvider(partnerDetailId);
                    refCub = fc.transferFCUBS(pep.getDebitAccount(), accountTo, billAmount, msgpay);
                    //refCub = fc.transferFCUBSWithProductUser(producttransferPaybill, pep.getUserId(), pep.getDebitAccount(), accountTo, billAmount, msgpay);                    
                }
                //refCub="a";
                //BCN edit (S)   
                Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, "***- LOG THANH TOAN ONLPBL (FUNCTION transferPaybill): Ma Khach Hang: " + billpaid.getCustomercode() + " -- So tham chieu trich tien REFCUBS= " + refCub + " -- From Account :" + from_custacc.getCustAcNo() + "--To Account:" + accountTo);
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
                String accountTo = pep.getDebitAccount();
                refCub = fc.transferFCUBS(ps.getAccnofcubs(), accountTo, billAmount, msgback);
                Helper.writeLog(ExchangePaybill.class, org.apache.log4j.Level.INFO, "***- LOG HOAN THANH TOAN ONLPBL (FUNCTION transferPaybill): Ma Khach hang: " + billpaid.getCustomercode() + " --  So tham chieu hoan tien  REFCUBS= " + refCub + " --  To Account :" + from_custacc.getCustAcNo());
                return refCub;
            }
            return null;

        } catch (Exception ex) {
            //Logger.getLogger(ONLPblController.class.getName()).log(Level.SEVERE, null, ex);
            Helper.writeLogError(ONLPblController.class, "***- ERROR ONLPBL (FUNCTION transferPaybill): " + ex.getMessage());
            return "";
        }
    }

    /*
     * History:
     * - 28/03/2014: Bo sung dieu kien kiem tra thau chi.
     */
    private PaymentResultEnum checkAmountToPaybill(String AmountBill, VwCustAccountNew custacc) {

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

            for (BillInfo b : bills) {
                strBills = strBills + b.getBillId() + "%";
                //Month Bill
                strBills = strBills + b.getMonth() + "%";
                //Amount                
                strBills = strBills + String.valueOf(b.getMoneyAmount().intValue()) + "%";
                strBills = strBills + b.getCustomerId() + "%";
                //totalAmountOfBills = totalAmountOfBills.add(b.getMoneyAmount());
                strBills = strBills + "#";
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
            //Helper.writeLogError(ONLPblController.class,String.format("[ADD LOG KIMNCM - VE MAY BAY 1 -%1$s]", ToStringBuilder.reflectionToString(pepe.getRespay().getResponse())));
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
                    pepe.setResult(scb.com.vn.message.Message.PaymentResultEnum.BILL_PAID.getValue());
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
                    || idprovider.equals(Configuration.getProperty("provider.PF"))) {
                return true;
            }
        }
        return false;
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

            EBANKUSER user = (EBANKUSER) Helper.getDBI().ONL_getInfoEbankUser(pepe.getUserName());
            if (user == null) {
                Helper.writeLogError(ONLPblController.class, "***- ERROR OBDX (FUNCTION transferPaybill): " + "not get user from DB OBDX ");
            } else {

                pl.setIdmarker(user.getCUST_NO());
            }
            /*
            List mbMobile = Helper.getDBI().getCustomerFromMobile(pepe.getUserName());

            if (mbMobile == null || mbMobile.isEmpty()) {
                Helper.writeLogError(ONLPblController.class, "***- ERROR MB (FUNCTION transferPaybill): " + "not get customerid from DB VNINFO ");
            } else {
                HashMap hm_MB = (HashMap) mbMobile.get(0);
                pl.setIdmarker(hm_MB.get("cus_id").toString());
            }
             */

            String msg = String.format("[idbillpaid-%1$s]-[idpartner-%2$s]-[idservicetype-%3$s]-[idprovider-%4$s]"
                    + "-[customercode-%5$s]-[idmaker-%6$s]-[cust_acc_no-%7$s]",
                    pepe.getRefBillpaid(), pepe.getIdpartner(), pepe.getIdservicetype(), pepe.getIdprovider(),
                    pepe.getCustomerCode(), pl.getIdmarker(), pepe.getDebitAccount());
            pl.setMsglog(msg);

            Helper.getDBI().insPblLog(pl);
        } catch (Exception e) {
            Helper.writeLogError(ONLPblController.class, "***- ERROR MB (FUNCTION insPblLog): " + e.getMessage());
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

    private void executePayBillIIB(RequestPayment reqpay, BillPaymentRq pep) {
        try {

            String refCub = "";
            int idbillPaid = -1;

            //1.3 Insert bang BillPaid
            PblBillpaid billpaid = new PblBillpaid();
            /*
            List mbMobile = Helper.getDBI().getCustomerFromMobile(pep.getUserName());

            if (mbMobile == null || mbMobile.isEmpty()) {
                Helper.writeLogError(ONLPblController.class, "***- ERROR MB (FUNCTION transferPaybill): " + "not get customerid from DB VNINFO ");
            }*/
            EBANKUSER user = (EBANKUSER) Helper.getDBI().ONL_getInfoEbankUser(pep.getUserName());
            if (user == null) {
                Helper.writeLogError(ONLPblController.class, "***- ERROR OBDX (FUNCTION transferPaybill): " + "not get user from DB OBDX ");
            }
            // HashMap hm_MB = (HashMap) mbMobile.get(0);

            billpaid.setIdpartner(pep.getIdpartner());

            PblServicetype pst = new PblServicetype();
            pst.setIdservicetype(pep.getIdservicetype());
            billpaid.setPblServicetype(pst);

            PblProvider pdr = new PblProvider();
            pdr.setIdprovider(pep.getIdprovider());
            billpaid.setPblProvider(pdr);

            billpaid.setCustomercode(pep.getCustomerCode());
            billpaid.setDataxml("");
            billpaid.setIduserMaker(user.getCUST_NO());
            billpaid.setIdchanneluserMaker(pep.getUserName());
            billpaid.setIduserChecker(user.getCUST_NO());
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
                Helper.writeLogError(ONLPblController.class, "executePayBillIIB không tìm thấy tài khoản KH - :" + pep.getCifNo());
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
                Helper.writeLogError(ONLPblController.class, " Thực hiện insert vào idbillPaid không thành công:" + idbillPaid + pep.getCustomerCode());
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
                Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, "***- LOG THANH TOAN MB FUNCTION executePayBillIIB  iibBillingService): Ma Khach Hang: " + billpaid.getCustomercode() + " -- So tham chieu trich tien REFCUBS= " + refCub + " -- From Account :" + pep.getDebitAccount());
                billpaid.setIdbillpaid(idbillPaid);
                billpaid.setRefFcubs(refCub);
                Helper.getDBI().updatePaybillBillPaid(billpaid);
                pep.setRefBillpaid(String.valueOf(refCub));
            } else {
                Helper.writeLogError(ONLPblController.class, "***- LOG THANH TOAN MB FUNCTION executePayBillIIB  hach toan khong thanh cong: Ma Khach Hang: " + billpaid.getCustomercode() + " - Ma loi:" + responsepay.getResult() + responsepay.getResultMessage());
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
                    Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "THANH CONG", refCub));
                    updateBillStatusToDB(billpaid, 'F');
                    pep.setResult(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue());
                } else if (pep.getResult().equals(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue())) {
                    //Khong hoan tien, yeu cau doi tac gach bu giao dich
                    String a = "PAYBILL IS NOT SUCCESS. Hold tien KH, yeu cau doi tac gach bu tay Result %1$s. [idbillpaid-%2$s]";
                    Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(a, pep.getResult(), billpaid.getIdbillpaid()));
                    updateBillStatusToDB(billpaid, 'H');
                    pep.setResult(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());
                } else {
                    //Hold toan bo giao dich
                    String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong, thuc hien doi soat Result %1$s. [idbillpaid-%2$s]";
                    Helper.writeLog(ONLPblController.class, org.apache.log4j.Level.INFO, String.format(a, pep.getResult(), billpaid.getIdbillpaid()));
                    updateBillStatusToDB(billpaid, 'H');
                    pep.setResult(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());
                }
            }
        } catch (RemoteException ex) {
            Helper.writeLogError(ONLPblController.class, "***- ERROR IB (FUNCTION executePayBillIIB): " + ex.getMessage());
            pep.setResult(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
        } catch (Exception ex) {
            Helper.writeLogError(ONLPblController.class, "***- ERROR IB (FUNCTION executePayBillIIB): " + ex.getMessage());
            pep.setResult(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
        }
    }
}
