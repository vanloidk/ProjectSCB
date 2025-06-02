package scb.com.vn.payment;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import scb.com.vn.common.model.payment.RequestPayment;
import scb.com.vn.common.model.payment.ResponsePayment;
import scb.com.vn.message.Message;
import scb.com.vn.model.billingvnpt.PblPartnerService;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.Helper;

/**
 *
 * @author phucnnd
 */
public class ControllerUtil {

    private static final String[] CHANNEL = {"INTERNET", "SMS", "MOBILE", "COUNTER", "ATM", "AUTOPAYBILL", "ONLPAYMENT"};

    /**
     *
     */
    public static final String[] PAYMENTMETHOD = {"CASH", "ACCOUNT"};

    /**
     *
     */
    public static final String[] PROCESSINGCODE = {"QUERY", "PAY", "COLLATE"};
    private static final int TIMEOUT = 10;

    /**
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public static RequestPayment unMarshallerPayment(String xml)
            throws Exception {
        if (xml == null) {
            throw new Exception("Tham so string xml marshaller payment khong the phan tich");
        }
        RequestPayment requestpay;
        try {
            requestpay = (RequestPayment) Xml.unMarshaller(RequestPayment.class, xml);
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        if (requestpay == null) {
            throw new Exception("Tham so string xml marshaller payment khong the phan tich");
        }
        return requestpay;
    }

    /*
    public static String checkPreValidation(RequestPayment requestpay) {

        if (isExistInListValid(CHANNEL, requestpay.getChannel()) == false) {
            return Message.PaymentResultEnum.CHANNEL_NOTEXSIST.getValue();
        }

        if (isExistInListValid(servicecode, requestpay.getServicecode()) == false) {
            return Message.PaymentResultEnum.SERVICECODE_NOTEXSIST.getValue();
        }

        if (isExistInListValid(providercode, requestpay.getProvidercode()) == false) {
            return Message.PaymentResultEnum.PROVIDERCODE_NOTEXSIST.getValue();
        }

        if (isExistInListValid(PROCESSINGCODE, requestpay.getProcessingcode()) == false) {
            return Message.PaymentResultEnum.PROCESSINGCODE_NOTEXSIST.getValue();
        }

        if (!isValidDatetime(requestpay.getDatetime())) {
            return Message.PaymentResultEnum.TIMEOUT.getValue();
        }

        if (requestpay.getPaymentmethod().equals("ACCOUNT")) {
            if (requestpay.getAccountno() == null || requestpay.getAccountno().trim().length() == 0) {
                return Message.PaymentResultEnum.ACCNO_NOT_FOUND.getValue();
            }
        }

        return null;
    }
     */

    /**
     *
     * @param requestpay
     * @param servicecode
     * @param providercode
     * @return
     */

    public static String checkPreValidation(RequestPayment requestpay, String[] servicecode, String[] providercode) {
        if (!isExistInListValid(CHANNEL, requestpay.getChannel())) {
            return Message.PaymentResultEnum.CHANNEL_NOTEXSIST.getValue();
        }

        if (!isExistInListValid(servicecode, requestpay.getServicecode())) {
            return Message.PaymentResultEnum.SERVICECODE_NOTEXSIST.getValue();
        }

        if (!isExistInListValid(providercode, requestpay.getProvidercode())) {
            return Message.PaymentResultEnum.PROVIDERCODE_NOTEXSIST.getValue();
        }

        if (!isExistInListValid(PROCESSINGCODE, requestpay.getProcessingcode())) {
            return Message.PaymentResultEnum.PROCESSINGCODE_NOTEXSIST.getValue();
        }

        /*
        if (!isValidDatetime(requestpay.getDatetime())) {
            return Message.PaymentResultEnum.TIMEOUT.getValue();
        }
        */

        if ((requestpay.getPaymentmethod().equals("ACCOUNT")) && ((requestpay.getAccountno() == null) || (requestpay.getAccountno().trim().length() == 0))) {
            return Message.PaymentResultEnum.ACCNO_NOT_FOUND.getValue();
        }

    return null;
    }

    private static boolean isExistInListValid(String[] allvalues, String valuetocheck) {
        for (int i = 0; i < allvalues.length; i++) {
            if (allvalues[i].equals(valuetocheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param sdatetime
     * @return
     */
    public static boolean isValidDatetime(String sdatetime) {
        // Format: DDMMYYYYhh24mmss
        DateFormat datetimeFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date datetime = null;
        try {
            datetime = datetimeFormat.parse(sdatetime);
        } catch (Exception e) {
            return false;
        }

        if ((datetime.getTime() + (TIMEOUT * 60 * 1000)) < System.currentTimeMillis()) {
            return false;
        }

        return true;
    }

    /**
     *
     * @param result
     * @return
     */
    public static String createResponseFromResultCode(String result) {
        ResponsePayment _rp = new ResponsePayment();
        try {
            _rp.setResult(result);
            return Xml.Marshaller(_rp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param responsepay
     * @return
     */
    public static String createResponse(ResponsePayment responsepay) {
        try {
            return Xml.Marshaller(responsepay);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return createResponseFromResultCode(Message.PaymentResultEnum.SYSTEM_ERROR.getValue());
    }

    /*
    public static String toXml(String result) {
        ResponsePayment responsepay = new ResponsePayment();
        try {
            responsepay.setResult(result);
            return Xml.Marshaller(responsepay);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
     */

    /**
     *
     * @param idservicecode
     * @param idprovidercode
     * @return
     */

    public static String getIdPartner(String idservicecode, String idprovidercode) {
        try {
            // SERVICECODE
            ArrayList<?> listServiceType = Helper.getDBI().getPartnerServiceByPs(idservicecode, idprovidercode);
            if (listServiceType.isEmpty()) {
                return null;
            }

            HashMap hm = (HashMap) listServiceType.get(0);
            return (String) hm.get("idpartner");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param idservicecode
     * @param idprovidercode
     * @return
     */

    public static PblPartnerService getPartnerInfo(String idservicecode, String idprovidercode) {
        try {
            // SERVICECODE
            PblPartnerService partnerService = new PblPartnerService();
            
            ArrayList<?> listServiceType = Helper.getDBI().getPartnerServiceByPs(idservicecode, idprovidercode);
            if (listServiceType.isEmpty()) {
                return partnerService;
            }
            HashMap hm = (HashMap) listServiceType.get(0);
            
            partnerService.setAccFcus((String) hm.get("accnofcubs"));
            partnerService.setIdPartner((String) hm.get("idpartner"));
            partnerService.setIdProvider((String) hm.get("idprovider"));
            
            return partnerService;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
            
    //LyDTY added

    /**
     *
     * @param Input
     * @return
     */
    public static String EncriptMD5(String Input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(Input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            //String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            String hashtext = String.format("%032x", number);

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param sdatetime
     * @return
     */
    public static boolean isCheckDatetime(String sdatetime) {
        // Format: DDMMYYYYhh24mmss
        DateFormat datetimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date datetime = null;
        try {
            datetime = datetimeFormat.parse(sdatetime);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
