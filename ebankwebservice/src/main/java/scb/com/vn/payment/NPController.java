package scb.com.vn.payment;

import java.math.BigDecimal;
import scb.com.vn.common.model.payment.*;
import scb.com.vn.utility.Helper;

import java.util.Date;
import org.apache.log4j.Level;
import scb.com.vn.controller.ControllerImpl;
import scb.com.vn.controller.ExchangePaybill;
import static scb.com.vn.controller.ExchangePaybill.getPaymentMsgEnum;
import scb.com.vn.controller.Fcubs;
import scb.com.vn.dbi.dto.PblBillpaid;
import scb.com.vn.dbi.dto.PblPartnerservice;
import scb.com.vn.dbi.dto.PblPartnerserviceId;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.dbi.dto.VwCustAccountNew;
import scb.com.vn.message.Message;
import scb.com.vn.message.Message.PaymentResultEnum;
import scb.com.vn.model.NewportRq;
import scb.com.vn.ultility.Common;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.CommonUtils;

/**
 *
 * @author minhndb
 */
public class NPController {

    /**
     *
     */
    public NPController() {
    }

    /**
     *
     * @param xml
     * @param processingcode
     * @return
     * @throws Exception
     */
    public String newportPayment(String xml, String processingcode) throws Exception {

        String result = "";
        NewportRq npReq = unMarshallerPayment(xml);
        if (processingcode.equals("QUERY")) {
            result = queryNewport(npReq, processingcode);
        } else if (processingcode.equals("PAY")) {
            result = paymentNewport(npReq, processingcode);
        }
        return result;
    }

    /**
     *
     * @param npReq
     * @param processingcode
     * @return
     */
    public String paymentNewport(NewportRq npReq, String processingcode) {
        String result = "";
        try {
            Helper.writeLog(NPController.class, Level.INFO, "Call getCustAccountFcdbByAccountNo: " + npReq.getAccIdaccount());
            //Kiem tra Balance cua giao dich
            VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(npReq.getAccIdaccount());
            Helper.writeLog(NPController.class, Level.INFO, "Call end: getCustAccountFcdbByAccountNo: " + npReq.getAccIdaccount());
            //Kiem tra neu la Thau chi thi khong kiem tra so du TK, cac TK khac deu ktra so du
            if ((!custacc.getAccountClass().substring(0, 3).equals("TCI"))) {
                if (custacc.getAcyAvlBal().compareTo(BigDecimal.valueOf(npReq.getTotalamount())) == -1) {
                    npReq.setErrorcode(Message.PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY.getValue());
                    npReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY));
                    result = Xml.Marshaller(npReq);
                    return result;
                }
            }
            PblBillpaid bp = new PblBillpaid();
            PblPartnerserviceId ppsid = new PblPartnerserviceId();
            ppsid.setIdprovider(npReq.getIdprovider());
            ppsid.setIdservicetype(npReq.getIdservicetype());
            ppsid.setIdpartner(npReq.getIdpartner());

            PblPartnerservice pps = Helper.getDBI().getPartnerServiceById(ppsid);
            npReq.setAccPartnerIdaccount(pps.getAccnofcubs());
            bp.setPblProvider(pps.getPblProvider());
            bp.setPblServicetype(pps.getPblServicetype());
            bp.setIdpartner(npReq.getIdpartner());
            bp.setCustomercode(npReq.getBill_code());
            bp.setAccCust(custacc.getCustNo());
            bp.setAccIdaccount(custacc.getCustAcNo());
            bp.setAccHoldername(custacc.getFullName());
            bp.setAccAddress(custacc.getAddress());
            bp.setIdchannel(npReq.getIdchannel());
            bp.setTotalamount(npReq.getTotalamount());
            bp.setIduserMaker(npReq.getIduserMaker());
            bp.setIdchanneluserMaker(npReq.getIdchanneluserMaker());
            //bp.setBranchcode(custacc.getCustAcNo().substring(0, 3));
            bp.setBranchcode(CommonUtils.getBranchAccount(custacc.getCustAcNo()));
            bp.setInsdate(new Date());
            bp.setInsdateMaker(new Date());
            bp.setIsapproved('W');
            bp.setPaymentmethod(npReq.getPaymentmethod());
            bp.setUsertype("1");

            int id = Helper.getDBI().insertPaybillBillPaid(bp);
            if (id < 0) {
                Helper.writeLog(NPController.class, Level.INFO, "Lỗi insert billpaid.");
                npReq.setErrorcode(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
                npReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(Message.PaymentResultEnum.SYSTEM_ERROR.getValue())));
                result = Xml.Marshaller(npReq);
                return result;
            }
            bp.setIdbillpaid(id);

            String refCub = "";
            try {
                refCub = transferPaybill(bp, npReq);
//                refCub = "137PB06162170061";
            } catch (Exception e) {
                Helper.writeLogError(NPController.class, "***- ERROR IB (FUNCTION paybill): " + e.getMessage());
                npReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(Message.PaymentResultEnum.SYSTEM_ERROR.getValue())));
                result = Xml.Marshaller(npReq);
                return result;
            }
            if (refCub == null || refCub.equalsIgnoreCase("")) {
                npReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(Message.PaymentResultEnum.CANNOT_TRANSFERFCUBS.getValue())));
                result = Xml.Marshaller(npReq);
                return result;
            }

            String xml = requestPaybillFromPartner(npReq, processingcode);
            if (Common.isEmpty(xml)) {
                npReq.setErrorcode(Message.PaymentResultEnum.TIMEOUT.getValue());
                npReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.TIMEOUT));
                result = Xml.Marshaller(npReq);
                return result;
            }
            ResponsePayment respay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, xml);
            bp.setIduserChecker(npReq.getIduserChecker());
            bp.setIdchanneluserChecker(npReq.getIdchanneluserChecker());
            bp.setInsdateChecker(new Date());
            bp.setRefFcubs(refCub);
            bp.setDataxml(xml);
            bp.setRefPartner(respay.getTranscode());
            bp.setPaydate(new Date());
            bp.setTransdate(new Date());
            bp.setPaydateFcubs(new Date());
            if (!respay.getResult().equals(Message.PaymentResultEnum.OK.getValue())) {
                updateNoneSucessBillStatus(bp, respay);
                npReq.setErrorcode(respay.getResult());
                npReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(respay.getResult())));
                result = Xml.Marshaller(npReq);
                return result;
            }
            bp.setIsapproved('A');
            bp.setStatus('D');
            Helper.getDBI().updatePaybillBillPaid(bp);

            npReq.setBill_code(respay.getResponse().getLogistics().getBill_code());
            npReq.setConsignment_code(respay.getResponse().getLogistics().getConsignment_code());
            npReq.setTax_code(respay.getResponse().getLogistics().getTax_code());
            npReq.setContainer_code(respay.getResponse().getLogistics().getContainer_code());
            npReq.setErrorcode(respay.getResult());
            npReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(respay.getResult())));
            result = Xml.Marshaller(npReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param bp
     * @param respay
     * @throws Exception
     */
    public void updateNoneSucessBillStatus(PblBillpaid bp, ResponsePayment respay) throws Exception {
        /**
         * DIEU CHINH HOAN TIEN THEO QUY DINH DICH VU HOAN TIEN -> TRANG THAI: F
         * TREO -> TRANG HAI: H
         *
         */
        if (respay.getResult().equals(PaymentResultEnum.ERROR_PARTNER_REFUND.getValue())) {
            //Giao dich that bai, thuc hien hoan tien cho KH
            String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong & hoan tien bill. [idbillpaid-%1$s]-[Trancode-%2$s]";
            Helper.writeLog(NPController.class, org.apache.log4j.Level.INFO, "***- LOG THANH TOAN IB (FUNCTION updateNoneSucessBillStatus): Ma Khach Hang: " + bp.getCustomercode() + " -- So tham chieu trich tien REFCUBS= " + bp.getRefFcubs() + " -- refPartner :" + bp.getRefPartner());
            String _msgouttransfer = "TRANSFER UBS. Thuc hien HOAN TIEN HOAN TIEN %1$s. [So but toan-%2$s]";
            String fccRef = bp.getRefFcubs();
            String result = revertTransferFCUBS(fccRef, 60);

            if (result == null) {
                //Revert khong thanh cong, tra soat hoan tien cho KH
                Helper.writeLog(NPController.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                updateBillStatusToDB(bp, 'H');
            } else if (result.equalsIgnoreCase("0")) {
                Helper.writeLog(NPController.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "THANH CONG", fccRef));
                updateBillStatusToDB(bp, 'F');
            } else {
                //Revert khong thanh cong, tra soat hoan tien cho KH
                Helper.writeLog(NPController.class, org.apache.log4j.Level.INFO, String.format(_msgouttransfer, "KHONG THANH CONG", fccRef));
                updateBillStatusToDB(bp, 'H');
            }
        } else if (respay.getResult().equals(PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue())) {
            //Khong hoan tien, yeu cau doi tac gach bu giao dich
            String a = "PAYBILL IS NOT SUCCESS. Hold tien KH, yeu cau doi tac gach bu tay. [idbillpaid-%1$s]";
            Helper.writeLog(NPController.class, org.apache.log4j.Level.INFO, String.format(a, bp.getIdbillpaid()));
            updateBillStatusToDB(bp, 'H');
        } else {
            //Giao dich nghi van, thuc hien doi soat
            //Hold toan bo giao dich
            String a = "PAYBILL IS NOT SUCCESS. TTHD doi tac khong thanh cong, thuc hien doi soat. [idbillpaid-%1$s]";
            Helper.writeLog(NPController.class, org.apache.log4j.Level.INFO, String.format(a, bp.getIdbillpaid()));
            updateBillStatusToDB(bp, 'H');
        }
    }

    private void updateBillStatusToDB(PblBillpaid billpaid, Character status) throws Exception {
        billpaid.setStatus(status);
        Helper.getDBI().updatePaybillBillPaid(billpaid);
    }

    private String revertTransferFCUBS(String fccRef, int timeout) {
        //Kiem tra so ref hop le
        if (fccRef == null) {
            return null;
        }
        Fcubs fc = new Fcubs();
        return fc.revertTransferFCUBS(fccRef, timeout);
    }

    /**
     *
     * @param bp
     * @param npRq
     * @return
     */
    public String transferPaybill(PblBillpaid bp, NewportRq npRq) {
        try {
            String ref = "";
            Fcubs fc = new Fcubs();
            String msgpay = String.format(getPaymentMsgEnum(ExchangePaybill.PaymentMsgEnum.THANHTOAN_BILL), bp.getCustomercode());
            String msgback = getPaymentMsgEnum(ExchangePaybill.PaymentMsgEnum.HOAN_THANHTOAN_BILL);
            ref = fc.transferFCUBS(bp.getAccIdaccount(), npRq.getAccPartnerIdaccount(), BigDecimal.valueOf(bp.getTotalamount()), msgpay);
            Helper.writeLog(NPController.class, org.apache.log4j.Level.INFO, "***- LOG THANH TOAN IB (FUNCTION transferPaybill): Ma Khach Hang: " + bp.getCustomercode() + " -- So tham chieu trich tien REFCUBS= " + ref + " -- From Account :" + bp.getAccIdaccount());
            if (ref == null || ref.equalsIgnoreCase("")) {
                return "";
            } else {
                bp.setRefFcubs(ref);
                Helper.getDBI().updatePaybillBillPaid(bp);
                return ref;
            }
        } catch (Exception e) {
            Helper.writeLogError(NPController.class, "***- ERROR IB (FUNCTION transferPaybill): " + e.getMessage());
            return "";
        }
    }

    /**
     *
     * @param npReq
     * @param processingcode
     * @return
     */
    public String queryNewport(NewportRq npReq, String processingcode) {
        String result = "";
        try {
            String xml = requestPaybillFromPartner(npReq, processingcode);
            if (Common.isEmpty(xml)) {
                npReq.setErrorcode(Message.PaymentResultEnum.TIMEOUT.getValue());
                npReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.TIMEOUT));
                result = Xml.Marshaller(npReq);
                return result;
            }
            ResponsePayment respay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, xml);
            if (!respay.getResult().equals(Message.PaymentResultEnum.OK.getValue())) {
                npReq.setErrorcode(respay.getResult());
                npReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(respay.getResult())));
                result = Xml.Marshaller(npReq);
                return result;
            }
            npReq.setBill_code(respay.getResponse().getLogistics().getBill_code());
            npReq.setConsignment_code(respay.getResponse().getLogistics().getConsignment_code());
            npReq.setTax_code(respay.getResponse().getLogistics().getTax_code());
            npReq.setContainer_code(respay.getResponse().getLogistics().getContainer_code());
            npReq.setTotalamount(Long.valueOf(respay.getResponse().getLogistics().getAmount()));
            npReq.setErrorcode(respay.getResult());
            npReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(respay.getResult())));
            result = Xml.Marshaller(npReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param npReq
     * @param processingcode
     * @return
     */
    public String requestPaybillFromPartner(NewportRq npReq, String processingcode) {
        try {
            Request r = new Request();
            Billing b = new Billing();
            b.setCustomercode(npReq.getBill_code());
            if (processingcode.equals("PAY")) {
                b.setAmount(npReq.getTotalamount().toString());
            }
            r.setBilling(b);
            RequestPayment reqpay = new RequestPayment();
            reqpay.setChannel("INTERNET");
            reqpay.setServicecode(npReq.getIdservicetype());
            reqpay.setProvidercode(npReq.getIdprovider());
            reqpay.setProcessingcode(processingcode);
            reqpay.setPaymentmethod("ACCOUNT");
            reqpay.setAccountno(npReq.getAccIdaccount());
            reqpay.setDatetime(Common.getDate("ddMMyyyyHHmmss"));
            reqpay.setRequest(r);

            String xml = Xml.Marshaller(reqpay);
            ControllerImpl ci = new ControllerImpl();
            String rsxml = ci.requestPayment(xml);

            ResponsePayment responsepay = (ResponsePayment) Xml.unMarshaller(ResponsePayment.class, rsxml);
            //Update for transaction code by Hieu
            if (responsepay != null) {
                npReq.setTranscode(responsepay.getTranscode());
            }
            npReq.setErrorcode(responsepay.getResult());
            return rsxml;
        } catch (Exception e) {
            Helper.writeLogError(NPController.class, "***- ERROR Newport (FUNCTION requestPaybillFromPartner): " + e.getMessage());
            npReq.setErrorcode(scb.com.vn.message.Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
            return null;
        }
    }

    /**
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public NewportRq unMarshallerPayment(String xml) throws Exception {
        if (xml == null) {
            throw new Exception("Tham so string xml marshaller npcontroller khong the phan tich");
        }
        NewportRq request;
        try {
            request = (NewportRq) Xml.unMarshaller(NewportRq.class, xml);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        if (request == null) {
            throw new Exception("Tham so string xml marshaller npcontroller khong the phan tich");
        }
        return request;
    }
}
