/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.cw.CardInfo;
import scb.com.vn.common.model.masterpass.PayByQRCodeRq;
import scb.com.vn.common.model.mvisa.MVISAQRRQ;
import scb.com.vn.dbi.dto.MasterSenderInfor;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.message.CommonMessage.CommontEnum;
import scb.com.vn.model.ekyc.ResponseCodeEkyc;
import vn.com.scb.bian.TransactionInfoType;

/**
 *
 * @author kimncm
 */
public class CommonUtils {

    private static final Logger logger = Logger.getLogger(CommonUtils.class);

    /**
     *
     * @param channel
     * @return
     */
    public static TransactionInfoType getTransInfpTypeCommon(String channel) {
        TransactionInfoType transactionInfo = new TransactionInfoType();
        transactionInfo.setClientCode(channel);
        return transactionInfo;

    }

    /**
     *
     * @param account
     * @return
     */
    public static String getBranchAccount(String account) {
        String branchcode;
        try {
            if (account == null || account.isEmpty()) {
                return "";
            }
            /*if (account.length() == 9) {
                return "000";
            }*/
            branchcode = Helper.getDBI().GET_BRANCHCODE_FROM_FCC(account);
            if (branchcode == null) {
                return "";
            }

        } catch (Exception ex) {
            branchcode = null;
            logger.error("Lay ma chi nhanh bi loi: " + ex.getMessage());
        }
        logger.warn("BRANCH of: " + account + " is:" + branchcode);
        return branchcode;
    }

    /**
     *
     * @param providerID
     * @return
     */
    public static String getMD5Key(String providerID) {
        try {
            String[] result = Helper.getDBI().ONL_GetMACkeys(providerID);

            if (result == null || result.length == 0 || result[0] == null) {
                return "";
            } else {
                return result[0];
            }
        } catch (RemoteException e) {
            return "";
        }
    }

    /**
     *
     * @param value
     * @return
     */
    public static boolean tryParseDoubleType(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     *
     * @param oldDate
     * @return
     */
    public static String changeFormatDatePoint(String oldDate) {
        try {
            if (oldDate != null && !oldDate.isEmpty()) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = formatter.parse(oldDate);
                return formatter1.format(date);
            } else {
                return oldDate;
            }
        } catch (Exception e) {
            return oldDate;
        }
    }

    /**
     *
     * @param partern
     * @return
     */
    public static String getDate(String partern) {
        Date date = new Date();
        try {
            DateFormat df = new SimpleDateFormat(partern);
            return df.format(date);
        } catch (Exception e) {
            return date.toString();
        }
    }

    private static boolean tryToPasteDouble(String value) {
        try {
            new Double(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean tryToPasteDateTime(String value) {
        TimeZone tz = TimeZone.getTimeZone("Asia/Bangkok");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        df.setTimeZone(tz);
        try {
            df.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     *
     * @param req
     * @param senderInfor
     * @return
     */
    public static CommontEnum verifyMasterInfo(PayByQRCodeRq req, MasterSenderInfor senderInfor) {
        if (!validateStringValue(req.getProvider())) {
            return CommontEnum.INVALID_PROVIDER;
        }
        if (!validateStringValue(req.getMerCardName())) {
            return CommontEnum.INVALID_MERCARDNAME;
        }
        if (!validateStringValue(req.getFundingS())) {
            return CommontEnum.INVALID_FUNDINGSOURCE;
        }
        if (!validateStringValue(req.getChannel())) {
            return CommontEnum.INVALID_CHANNEL;
        }
        if (req.getRefNo() == null || req.getRefNo().length() > 16) {
            return CommontEnum.INVALID_REFERENCENO;
        }
        if (req.getLoc4Digit() == null || req.getLoc4Digit().length() != 16) {
            return CommontEnum.INVALID_LOC4DIGIT;
        }
        if (!tryToPasteDouble(req.getAmount())) {
            return CommontEnum.INVALID_AMOUNT;
        }
        if (req.getCcy() == null || req.getCcy().length() != 3 || !"VND".equals(req.getCcy())) {
            return CommontEnum.INVALID_CCY;
        }
        if (req.getCcyCode() == null || req.getCcyCode().length() != 3 || !"704".equals(req.getCcyCode())) {
            return CommontEnum.INVALID_CCYCODE;
        }
        if (!validateStringValue(req.getMerchantId()) || req.getMerchantId().length() > 20) {
            return CommontEnum.INVALID_MERID;
        }
        if (!tryToPasteDateTime(req.getDateTime())) {
            return CommontEnum.INVALID_DATETIME;
        }
        if (!validateStringValue(req.getMerFName()) || req.getMerFName().length() > 40) {
            return CommontEnum.INVALID_MERFNAME;
        }
        if (!validateStringValue(req.getMerLName()) || req.getMerLName().length() > 40) {
            return CommontEnum.INVALID_MERLNAME;
        }
        if (!validateStringValue(req.getMerCategory()) || req.getMerCategory().length() > 4) {
            return CommontEnum.INVALID_MERCATEGORY;
        }
        if (!validateStringValue(senderInfor.getPan_clear()) || senderInfor.getPan_clear().length() != 16) {
            return CommontEnum.INVALID_SENDERACC;
        }
        if (!validateStringValue(senderInfor.getFrist_name()) || senderInfor.getFrist_name().length() > 40) {
            return CommontEnum.INVALID_SENDERFNAME;
        }
        if (!validateStringValue(senderInfor.getLast_name()) || senderInfor.getLast_name().length() > 16) {
            return CommontEnum.INVALID_SENDERLNAME;
        }
        if (!validateStringValue(senderInfor.getHme_addr()) || senderInfor.getHme_addr().length() > 50) {
            return CommontEnum.INVALID_SENDERADDRESS;
        }
        if (!validateStringValue(senderInfor.getHme_town()) || senderInfor.getHme_town().length() > 25) {
            return CommontEnum.INVALID_SENDERCITY;
        }
        if (!validateStringValue(senderInfor.getHme_zip()) || senderInfor.getHme_zip().length() > 10) {
            return CommontEnum.INVALID_SENDERPOSTALCODE;
        }
        if (!validateStringValue(senderInfor.getHme_cntry()) || senderInfor.getHme_cntry().length() > 3) {
            return CommontEnum.INVALID_SENDERCOUNTRY;
        }
        if (!validateStringValue(senderInfor.getExpi_date()) || senderInfor.getExpi_date().length() > 10) {
            return CommontEnum.INVALID_SENDEREXPIRE;
        }
        if (!validateStringValue(senderInfor.getBrch_cde()) || senderInfor.getBrch_cde().length() > 10) {
            return CommontEnum.INVALID_CARDBRAND;
        }
        if (!validateStringValue(senderInfor.getSenderCountrySub()) || senderInfor.getSenderCountrySub().length() > 3) {
            return CommontEnum.INVALID_SENDERCOUNTRYSUB;
        }
        if (!validateStringValue(req.getAdd_msg()) || req.getAdd_msg().length() > 65) {
            return CommontEnum.INVALID_ADDITIONAL_MSG;
        }
        if (!"MC".equalsIgnoreCase(senderInfor.getCreditType())) {
            return CommontEnum.INVALID_CREDITCARD;
        }
        if (!"C".equalsIgnoreCase(senderInfor.getCardType())) {
            return CommontEnum.INVALID_CARDTYPE;
        }

        return CommontEnum.DATA_IS_VALID;
    }

    /**
     *
     * @param req
     * @param senderInfor
     * @return
     */
    public static CommontEnum verifyVisaInfo(MVISAQRRQ req, MasterSenderInfor senderInfor) {
        if (!validateStringValue(req.getRefNo())) {
            return CommontEnum.INVALID_REFNO;
        }
        if (!validateStringValue(req.getMerPrimaryAccNum())) {
            return CommontEnum.INVALID_MERPRIMARYACCNUM;
        }
        if (!validateStringValue(req.getAmount())
                || !tryParseDoubleType(req.getAmount())) {
            return CommontEnum.INVALID_AMOUNT;
        }
        if (!validateStringValue(req.getLocalTransDate())) {
            return CommontEnum.INVALID_LOCALTRANSDATE;
        }
        if (!validateStringValue(req.getAcquirerCountryCode())
                || req.getAcquirerCountryCode().length() != 3
                || !"704".equals(req.getAcquirerCountryCode())
                || !isNumeric(req.getAcquirerCountryCode())) {
            return CommontEnum.INVALID_ACQUIRERCOUNTRYCODE;
        }
        if (!validateStringValue(req.getTransCurrencyCode())
                || req.getTransCurrencyCode().length() != 3
                || !"VND".equals(req.getTransCurrencyCode())) {
            return CommontEnum.INVALID_TRANSCURRENCYCODE;
        }
        if (!validateStringValue(req.getProvider())) {
            return CommontEnum.INVALID_PROVIDER;
        }
        if (!validateStringValue(senderInfor.getPan_clear())) {
            return CommontEnum.INVALID_PANCLEAR;
        }
        if (!validateStringValue(senderInfor.getCardName())) {
            return CommontEnum.INVALID_CARDNAME;
        }
        if (!"VS".equalsIgnoreCase(senderInfor.getCreditType())) {
            return CommontEnum.INVALID_CREDITCARD;
        }
        if (!"C".equalsIgnoreCase(senderInfor.getCardType())) {
            return CommontEnum.INVALID_CARDTYPE;
        }

        return CommontEnum.DATA_IS_VALID;
    }

    /**
     *
     * @param input
     * @return
     */
    public static String trim(String input) {
        return (input == null) ? input : input.trim();
    }

    /**
     *
     * @param data
     * @return
     */
    public static boolean validateStringValue(String data) {
        return (data != null && !data.isEmpty());
    }

    /**
     *
     * @param s
     * @return
     */
    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     *
     * @return
     */
    public static String getJulianFromDate() {
        Date date = new Date();
        return getJulianFromDate(date);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getJulianFromDate(Date date) {
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String year = String.valueOf(cal.get(Calendar.YEAR));
        String day = String.format("%03d", cal.get(Calendar.DAY_OF_YEAR));
        String hour = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY));

        return sb.append(year.substring(year.length() - 1)).append(day).append(hour).toString();
    }

    /**
     *
     * @param dateStr
     * @param newFormat
     * @return
     */
    public static String fromISO8601UTCFormat(String dateStr, String newFormat) {
        TimeZone tz = TimeZone.getTimeZone("Asia/Bangkok");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        df.setTimeZone(tz);
        try {
            Date date = df.parse(dateStr);

            DateFormat dfNew = new SimpleDateFormat(newFormat);
            return dfNew.format(date);
        } catch (ParseException e) {
            return dateStr;
        }
    }

    /**
     *
     * @param dateStr
     * @param oldFormat
     * @param newFormat
     * @return
     */
    public static String fromISO8601UTCFormat(String dateStr, String oldFormat, String newFormat) {
        TimeZone tz = TimeZone.getTimeZone("Asia/Bangkok");
        DateFormat df = new SimpleDateFormat(oldFormat);
        df.setTimeZone(tz);
        try {
            Date date = df.parse(dateStr);
            DateFormat dfNew = new SimpleDateFormat(newFormat);
            return dfNew.format(date);
        } catch (ParseException e) {
            return dateStr;
        }
    }

    /**
     *
     * @param phone
     * @return
     */
    public static String swapPhoneNumber(String phone) {
        String headPhone;
        String bodyPhone;
        if (phone.length() == 11) {
            headPhone = phone.substring(0, 4);
            bodyPhone = phone.substring(4);
        } else {
            headPhone = phone.substring(0, 3);
            bodyPhone = phone.substring(3);
        }

        switch (headPhone) {
            case "0120":
                return "070" + bodyPhone;
            case "070":
                return "0120" + bodyPhone;
            case "0121":
                return "079" + bodyPhone;
            case "079":
                return "0121" + bodyPhone;
            case "0122":
                return "077" + bodyPhone;
            case "077":
                return "0122" + bodyPhone;
            case "0126":
                return "076" + bodyPhone;
            case "076":
                return "0126" + bodyPhone;
            case "0128":
                return "078" + bodyPhone;
            case "078":
                return "0128" + bodyPhone;
            case "0162":
                return "032" + bodyPhone;
            case "032":
                return "0162" + bodyPhone;
            case "0163":
                return "033" + bodyPhone;
            case "033":
                return "0163" + bodyPhone;
            case "0164":
                return "034" + bodyPhone;
            case "034":
                return "0164" + bodyPhone;
            case "0165":
                return "035" + bodyPhone;
            case "035":
                return "0165" + bodyPhone;
            case "0166":
                return "036" + bodyPhone;
            case "036":
                return "0166" + bodyPhone;
            case "0167":
                return "037" + bodyPhone;
            case "037":
                return "0167" + bodyPhone;
            case "0168":
                return "038" + bodyPhone;
            case "038":
                return "0168" + bodyPhone;
            case "0169":
                return "039" + bodyPhone;
            case "039":
                return "0169" + bodyPhone;
            case "0123":
                return "083" + bodyPhone;
            case "083":
                return "0123" + bodyPhone;
            case "0124":
                return "084" + bodyPhone;
            case "084":
                return "0124" + bodyPhone;
            case "0125":
                return "085" + bodyPhone;
            case "085":
                return "0125" + bodyPhone;
            case "0127":
                return "081" + bodyPhone;
            case "081":
                return "0127" + bodyPhone;
            case "0129":
                return "082" + bodyPhone;
            case "082":
                return "0129" + bodyPhone;
            case "0186":
                return "056" + bodyPhone;
            case "056":
                return "0186" + bodyPhone;
            case "0188":
                return "058" + bodyPhone;
            case "058":
                return "0188" + bodyPhone;
            case "0199":
                return "059" + bodyPhone;
            case "059":
                return "0199" + bodyPhone;
            default:
                return phone;
        }
    }

    /**
     *
     * @param account
     * @return
     */
    public static BufferedImage createQrCode(String account) {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            @SuppressWarnings("rawtypes")
            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            String content = "".concat("00.02.01").concat("01.02.13").concat("02.02.11").concat("03.06.970429");
            content = content.concat("07.").concat(String.valueOf(account.length())).concat(".").concat(account);
            content = content.concat("10.03.704").concat("6304");
            content = content.replace(".", "");
            String hexcr16 = "0000".concat(Long.toHexString(CRC.calculateCRC(CRC.Parameters.CRC16CCITT, content.getBytes())).toUpperCase());
            hexcr16 = hexcr16.substring(hexcr16.length() - 4, hexcr16.length());
            content = content.concat(hexcr16);
            //000201010213      0306970429071201524370004110037046304    
            //000201010213020211030697042907110152437000410037046304 =>4625 
            //000201010213020211030697042907111176640006010037046304 =>6049                                
/*
                        
             $data1="00.02.01";
             $data2="01.02.13";
             $data3="02.02.11";
             $data4="03.06.970429";
             $data5="07.".strlen($ACC).".$ACC";
             $data6="10.03.704";	
             $data=$data1.$data2.$data3.$data4.$data5.$data6;	
             $data.="6304";	
             $data=str_replace(".","",$data);
             $data7=substr("0000".strtoupper(dechex(CRC16Normal($data))),-4);	
             $data.=$data7
             */
            bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 500, 500,
                    hints);
            BufferedImage img = MatrixToImageWriter.toBufferedImage(bitMatrix);
            return img;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param cards
     * @return
     */
    public static boolean validCusForKHT(List<CardInfo> cards) {
        if (cards != null && !cards.isEmpty()) {
            CardInfo info = cards.get(0);
            String cif = (info != null) ? info.getCif() : "";
            for (CardInfo item : cards) {
                if (!cif.equals(item.getCif())) {
                    logger.warn("Danh sach the lay tu so dien thoai co nhieu hon 1 cif. "
                            + "Cif[0] = [" + cif + "], Cif[1] = [" + item.getCif() + "]");
                    return false;
                }
            }
            logger.info("Danh sach the chi co duy nhat 1 cif [" + cif + "]");
            return true;
        } else {
            logger.warn("Danh sach the bi null or rong.");
        }
        return false;
    }

    public static String getRefno(String type) throws Exception {
        String seq = Helper.getDBI().getSeqRefNo(type);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String refno = type + sdf.format(date) + seq;
        return refno;
    }

    public static String getSHA512(String input) {

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    public static String checkOverKYC(String PCUSTUMERACCOUNT, String branchCust, BigDecimal PAMOUNT) throws RemoteException {
        try {
            String resultCheck = Helper.getDBI().checkOverKYC(PCUSTUMERACCOUNT, branchCust, PAMOUNT);
            logger.info("resultCheck kcy[" + PCUSTUMERACCOUNT + "]" + " amount:" + PAMOUNT + "=>" + resultCheck);
            if (resultCheck != null) {
                String[] resultArr = resultCheck.split("#");
                String statusCheck = resultArr[0];
                String descCheck = resultArr[1];
                if (!statusCheck.equals("00") && !statusCheck.equals("SCB-ERR-153") && !statusCheck.equals("01")) {
                    return MessageMB.MobileResultEnum.IBT_OVERKYC.getValue();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "00";
    }

    public static ResponseCodeEkyc checkOverKYC02(String PCUSTUMERACCOUNT, String branchCust, BigDecimal PAMOUNT, ResponseCodeEkyc response) throws RemoteException {
        try {
            String resultCheck = Helper.getDBI().checkOverKYC(PCUSTUMERACCOUNT, branchCust, PAMOUNT);
            logger.info("resultCheck:" + resultCheck + "account: " + PCUSTUMERACCOUNT + "branch: "  + branchCust + "amount: " + PAMOUNT);
            if (resultCheck == null || resultCheck.isEmpty()) {
                response.setErrorCode("ekyc-02");
                response.setErrorMessage("Ham checkOverKYC tra ve null.");
                return response;
            }
            
            String[] resultArr = resultCheck.split("#");
            response.setErrorCode(resultArr[0]);
            response.setErrorMessage(resultArr[1]);

            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setErrorCode("ekyc-02");
            response.setErrorMessage("Co loi goi ham checkOverKYC tu dbi.");
            return response;
        }

    }

}
