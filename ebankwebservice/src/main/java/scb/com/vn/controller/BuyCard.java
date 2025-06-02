package scb.com.vn.controller;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import scb.com.vn.common.model.payment.ResponsePayment;
import scb.com.vn.dbi.dto.RTBuyCardDTO;
import scb.com.vn.dbi.dto.RTCardDetailDTO;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.dbi.dto.VwCustAccountNew;
import scb.com.vn.message.Message;
import scb.com.vn.model.RTBuyCardRq;
import scb.com.vn.model.RTCardDetail;
import static scb.com.vn.payment.ControllerUtil.createResponseFromResultCode;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.MessageMB;

/**
 *
 * @author minhndb
 */
public class BuyCard {

    String insProduct = Configuration.getProperty("buycard.fcubs.product");
    String useridfcubs = Configuration.getProperty("fcubs.userid");

    /**
     *
     * @param xml
     * @param processingcode
     * @return
     * @throws Exception
     */
    public String BuycardRequest(String xml, String processingcode) throws Exception {
        String result = "";
        RTBuyCardRq bcReq = unMarshallerPayment(xml);
        if (processingcode.equals("QUERY")) {
            result = queryBuycard(bcReq);
        } else if (processingcode.equals("PAYMENT")) {
            result = paymentBuycard(bcReq);
        }
        return result;
    }

    /**
     *
     * @param bcReq
     * @return
     */
    public String queryBuycard(RTBuyCardRq bcReq) {
        String result = "";
        try {
            Helper.writeLog(BuyCard.class, Level.INFO, "Call getCustAccountFcdbByAccountNo: " + bcReq.getAccIdaccount());
            //Kiem tra Balance cua giao dich
            VwCustAccountNew custacc = Helper.getDBI().getCustAccountByAccountNoNew(bcReq.getAccIdaccount());
            Helper.writeLog(BuyCard.class, Level.INFO, "Call end: getCustAccountFcdbByAccountNo: " + bcReq.getAccIdaccount());
            //Kiem tra neu la Thau chi thi khong kiem tra so du TK, cac TK khac deu ktra so du
            if ((!custacc.getAccountClass().substring(0, 3).equals("TCI"))) {
                if (custacc.getAcyAvlBal().compareTo(BigDecimal.valueOf(bcReq.getTotalamount())) == -1) {
                    bcReq.setErrorcode(Message.PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY.getValue());
                    bcReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.NOT_ENOUGH_AMT_TO_PAY));
                    result = Xml.Marshaller(bcReq);
                    return result;
                }
            }
           
             //kimncm temp
            Helper.writeLog(BuyCard.class, Level.INFO, "queryBuycard bcReq.getProvider()-2: " + bcReq.getProvider() + bcReq.getPricetag() );
            
            
            if (bcReq.getProvider().equals("MOBIFONE")) {            
                bcReq.setErrorcode(Message.PaymentResultEnum.OK.getValue());                
                bcReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.OK));
                bcReq.setQuantity(100);                
            } else {
                ResponsePayment response = null; //bcController.requestQuery(bcReq);
                if (!response.getResult().equals(Message.PaymentResultEnum.OK.getValue())) {
                    bcReq.setErrorcode(response.getResult());
                    bcReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(response.getResult())));
                    result = Xml.Marshaller(bcReq);
                    return result;
                }
            }

            //  bcReq.setErrorcode(response.getResult());
            //bcReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(response.getResult())));
              //kimncm note 
            result = Xml.Marshaller(bcReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param bcReq
     * @return
     */
    public String paymentBuycard(RTBuyCardRq bcReq) throws RemoteException, Exception {

        String result = "";
        String RefFCC = "";
        int idbuycard;
         
        RTBuyCardDTO bcDTO = new RTBuyCardDTO();
        try {
            String branchCust = CommonUtils.getBranchAccount(bcReq.getAccIdaccount());
        String resultCheck =CommonUtils.checkOverKYC(bcReq.getAccIdaccount(), branchCust,BigDecimal.valueOf(bcReq.getTotalamount()));
        if(!resultCheck.equals("00"))
        {
            bcReq.setErrorcode(MessageMB.MobileResultEnum.IBT_OVERKYC.getValue());
            bcReq.setErrorMessage(MessageMB.getMessageMBResult(MessageMB.MobileResultEnum.IBT_OVERKYC));
            result = Xml.Marshaller(bcReq);
           return result;
        }
            idbuycard = insertBuycard(bcReq, bcDTO);
            if (idbuycard < 0) {
                return "";
            } else {
                bcDTO.setId(idbuycard);
            }
            Fcubs fcc = new Fcubs();//Hach toan tru tien voi Core truoc
            String fromAcc = bcReq.getAccIdaccount();
            //String toAcc = bcReq.getAccPartnerIdaccount();
            String toAcc = "1950109012330001";

//            RefFCC = fcc.transferFCUBSWithTimeOut(insProduct, useridfcubs, bcReq.getAccIdaccount().substring(0, 3), bcReq.getAccIdaccount(), bcReq.getAccPartnerIdaccount().substring(0, 3), 
//                    bcReq.getAccPartnerIdaccount(), BigDecimal.valueOf(bcReq.getTotalamount()), "THANH TOAN MUA THE CAO", 30);
            //RefFCC = fcc.transferFCUBSWithTimeOut(insProduct, useridfcubs, fromAcc.substring(0, 3), fromAcc, toAcc.substring(0, 3), 
            //      toAcc, BigDecimal.valueOf(bcReq.getTotalamount()), "THANH TOAN MUA THE CAO", 30);
            
            RefFCC = fcc.transferFCUBSWithTimeOut(insProduct, useridfcubs, CommonUtils.getBranchAccount(fromAcc), fromAcc, CommonUtils.getBranchAccount(toAcc),
                    toAcc, BigDecimal.valueOf(bcReq.getTotalamount()), "THANH TOAN MUA THE CAO", 30);
            Helper.writeLog(BuyCard.class, Level.INFO, "Hach toan FCC, Ref: " + RefFCC);
            if (RefFCC != null) {
                if (RefFCC.equals("TIMEOUT")) {
                    Helper.writeLog(BuyCard.class, Level.INFO, "Chuyen khoan FCC Time out.");
                    bcReq.setErrorcode(Message.PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue());
                    bcReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(Message.PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue())));
                    result = Xml.Marshaller(bcReq);
                    return result;
                } else {
                    BuyCardController bcController = new BuyCardController();
                    ResponsePayment resp = bcController.requestPay(bcReq);
                    bcDTO.setRefFcubs(RefFCC);
                    bcDTO.setRefPartner(resp.getTranscode());
                    bcDTO.setInsdateChecker(new Date());
                    bcDTO.setIduserChecker(bcReq.getIduserChecker());
                    bcDTO.setIdchanneluserChecker(bcReq.getIdchanneluserChecker());
                    bcDTO.setTransdate(new Date());
                    bcDTO.setPaydate(new Date());
                    bcDTO.setPaydateFcubs(new Date());
                    if (!resp.getResult().equals(Message.PaymentResultEnum.OK.getValue())) {
                        updateNoneSucessBillStatus(bcDTO, resp, RefFCC);
                        bcReq.setErrorcode(resp.getResult());
                        bcReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(resp.getResult())));
                        result = Xml.Marshaller(bcReq);
                        return result;
                    }

                    List<RTCardDetail> list = new ArrayList<RTCardDetail>();
                    RTCardDetail carddetail = new RTCardDetail();
                    RTCardDetailDTO cdDTO = new RTCardDetailDTO();
                    cdDTO.setIdbuycard(idbuycard);
                    cdDTO.setIdprovider(bcReq.getProvider());
                    cdDTO.setPricetag(bcReq.getPricetag());
                    for (int i = 0; i < resp.getResponse().getCardinfo().getCarddetail().size(); i++) {
                        scb.com.vn.common.model.payment.CardDetail cd = resp.getResponse().getCardinfo().getCarddetail().get(i);
                        carddetail.setCardcode(cd.getCardcode());
                        carddetail.setSerial(cd.getCardserial());
                        carddetail.setExpire_date(cd.getExpiredate());
                        list.add(carddetail);

                        cdDTO.setCardcode(cd.getCardcode());
                        cdDTO.setSerial(cd.getCardserial());
                        cdDTO.setExpire_date(cd.getExpiredate());
                        int idCardDetail = Helper.getDBI().insertBuyCardDetail(cdDTO);
                    }
                    bcReq.setRtcarddetail(list);
                    //Giao dich thanh cong, UPDATE DB
                    bcDTO.setOrgtransid(resp.getResponse().getCardinfo().getOrgTransID());
                    bcDTO.setIsapproved('A');
                    bcDTO.setStatus('D');
                    Helper.getDBI().updateStatusBuyCard(bcDTO);
                    bcReq.setErrorcode(Message.PaymentResultEnum.OK.getValue());
                    bcReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(Message.PaymentResultEnum.OK.getValue())));
                    bcReq.setTxnID(RefFCC);
                    result = Xml.Marshaller(bcReq);
                    Helper.writeLog(BuyCard.class, Level.INFO, "BuyCard - GetCard thanh cong." + result);
                }
            } else {
                Helper.writeLog(BuyCard.class, Level.INFO, "Chuyen khoan FCC khong thanh cong.");
                bcReq.setErrorcode(Message.PaymentResultEnum.ERROR_PARTNER_REFUND.getValue());
                bcReq.setErrorMessage(Message.getMessagePaymentResult(Message.PaymentResultEnum.get(Message.PaymentResultEnum.ERROR_PARTNER_CASE_TO_REFUND.getValue())));
                result = Xml.Marshaller(bcReq);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     *
     * @param bcDTO
     * @param resp
     * @param refFcubs
     * @throws Exception
     */
    public void updateNoneSucessBillStatus(RTBuyCardDTO bcDTO, ResponsePayment resp, String refFcubs) throws Exception {
        /**
         * DIEU CHINH HOAN TIEN THEO QUY DINH DICH VU HOAN TIEN -> TRAN THAI: F
         * TREO -> TRANG HAI: H
         */
        Fcubs fcc = new Fcubs();
        if (resp.getResult().equals(Message.PaymentResultEnum.ERROR_PARTNER.getValue()) || resp.getResult().equals(Message.PaymentResultEnum.TOPUP_IN_PROCESSING.getValue())
                || resp.getResult().equals(Message.PaymentResultEnum.TIMEOUT.getValue()) || resp.getResult().equals(Message.PaymentResultEnum.SYSTEM_ERROR.getValue())) {
            //Khong hoan tien
            Helper.writeLog(BuyCard.class, Level.INFO, "THANH TOAN BUYCARD KHONG THANH CONG. HOLD TIEN KHACH HANG");
            updateBuyCardStatus(bcDTO, 'H');
        } else {
            //Giao dich that bai, thuc hien hoan tien cho KH
            String result = fcc.revertTransferFCUBS(refFcubs, 60);
            if (result == null) {
                //Revert khong thanh cong, tra soat hoan tien cho KH
                updateBuyCardStatus(bcDTO, 'H');
            } else if (result.equalsIgnoreCase("0")) {
                Helper.writeLog(BuyCard.class, Level.INFO, "REVERT FCC THANH CONG. RESPONSE CORE: " + result);
                updateBuyCardStatus(bcDTO, 'F');
            } else {
                //Revert khong thanh cong, tra soat hoan tien cho KH
                Helper.writeLog(BuyCard.class, Level.INFO, "REVERT FCC KHONG THANH CONG. RESPONSE CORE: " + result);
                updateBuyCardStatus(bcDTO, 'H');
            }
        }
    }

    private void updateBuyCardStatus(RTBuyCardDTO bcDTO, Character status) throws Exception {
        bcDTO.setStatus(status);
        Helper.getDBI().updateStatusBuyCard(bcDTO);
    }

    /**
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public RTBuyCardRq unMarshallerPayment(String xml) throws Exception {
        if (xml == null) {
            throw new Exception("Tham so string xml marshaller buycard khong the phan tich");
        }
        RTBuyCardRq request;
        try {
            request = (RTBuyCardRq) Xml.unMarshaller(RTBuyCardRq.class, xml);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        if (request == null) {
            throw new Exception("Tham so string xml marshaller buycard khong the phan tich");
        }
        return request;
    }

    /**
     *
     * @param responsepay
     * @param rtbuycard
     * @return
     */
    public String createResponse(ResponsePayment responsepay, RTBuyCardRq rtbuycard) {
        try {

            return Xml.Marshaller(responsepay);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return createResponseFromResultCode(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
    }

    /**
     *
     * @param bcReq
     * @param buycardDTO
     * @return
     * @throws RemoteException
     */
    public int insertBuycard(RTBuyCardRq bcReq, RTBuyCardDTO buycardDTO) throws RemoteException {
        VwCustAccount custacc = Helper.getDBI().getCustAccountFcdbByAccountNo(bcReq.getAccIdaccount());
        buycardDTO.setIdpartner(bcReq.getIdpartner());
        buycardDTO.setIdprovider(bcReq.getProvider());
        buycardDTO.setAccCust(custacc.getCustNo());
        buycardDTO.setAccIdaccount(custacc.getCustAcNo());
        buycardDTO.setAccHoldername(custacc.getFullName());
        buycardDTO.setAccAddress(custacc.getAddress());
        buycardDTO.setDiscount(bcReq.getDiscount());
        buycardDTO.setPaymentmethod(bcReq.getPaymentmethod());
        buycardDTO.setPricetag(bcReq.getPricetag());
        buycardDTO.setTotalamount(bcReq.getTotalamount());
        buycardDTO.setIdchannel(bcReq.getIdchannel());
        buycardDTO.setIduserMaker(bcReq.getIduserMaker());
        buycardDTO.setIdchanneluserMaker(bcReq.getIdchanneluserMaker());
        buycardDTO.setInsdate(new Date());
        buycardDTO.setInsdateMaker(new Date());
        buycardDTO.setQuantity(bcReq.getQuantity());
        buycardDTO.setBranchcode(custacc.getBranchCode());
        buycardDTO.setIsapproved('W');
        return Helper.getDBI().insertBuyCard(buycardDTO);
    }
}
