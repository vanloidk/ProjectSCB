/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import scb.com.vn.model.IBTDEDetail;

import java.io.IOException;
import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.text.ParseException;

import scb.com.vn.ultility.Common;
import scb.com.vn.utility.Configuration;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.engines.DESEngine;

import org.bouncycastle.crypto.macs.ISO9797Alg3Mac;

import org.bouncycastle.crypto.paddings.ISO7816d4Padding;

import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Arrays;

import org.jpos.iso.ISOUtil;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import scb.com.vn.utility.MAC_Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import scb.com.vn.utility.Helper;
import scb.com.vn.utility.MQClient;

/*
 *
 * @author lydty
 */
/**
 *
 * @author minhndb
 */
public class IBTV2Controller {

    //static final String MaBMNAPAS =Configuration.getProperty("napas.zak");
    static final String ZAK = Configuration.getProperty("napas.zak");
    static final String jsonListDE
            = "[{\"name\":\"2\",\"value\":\"2\",\"adding\":\"1\"},\n"
            + "{\"name\":\"3\",\"value\":\"6\",\"adding\":\"0\"},\n"
            + "{\"name\":\"4\",\"value\":\"12\",\"adding\":\"0\"},\n"
            + "{\"name\":\"5\",\"value\":\"12\",\"adding\":\"0\"},\n"
            + "{\"name\":\"6\",\"value\":\"12\",\"adding\":\"0\"},\n"
            + "{\"name\":\"7\",\"value\":\"10\",\"adding\":\"0\"},\n"
            + "{\"name\":\"9\",\"value\":\"8\",\"adding\":\"0\"},\n"
            + "{\"name\":\"10\",\"value\":\"8\",\"adding\":\"0\"},\n"
            + "{\"name\":\"11\",\"value\":\"6\",\"adding\":\"0\"},\n"
            + "{\"name\":\"12\",\"value\":\"6\",\"adding\":\"0\"},\n"
            + "{\"name\":\"13\",\"value\":\"4\",\"adding\":\"0\"},\n"
            + "{\"name\":\"14\",\"value\":\"4\",\"adding\":\"0\"},\n"
            + "{\"name\":\"15\",\"value\":\"4\",\"adding\":\"0\"},\n"
            + "{\"name\":\"18\",\"value\":\"4\",\"adding\":\"0\"},\n"
            + "{\"name\":\"19\",\"value\":\"3\",\"adding\":\"0\"},\n"
            + "{\"name\":\"22\",\"value\":\"3\",\"adding\":\"0\"},\n"
            + "{\"name\":\"23\",\"value\":\"3\",\"adding\":\"0\"},\n"
            + "{\"name\":\"25\",\"value\":\"2\",\"adding\":\"0\"},\n"
            + "{\"name\":\"32\",\"value\":\"2\",\"adding\":\"1\"},\n"
            + "{\"name\":\"34\",\"value\":\"2\",\"adding\":\"1\"},\n"
            + "{\"name\":\"36\",\"value\":\"3\",\"adding\":\"1\"},\n"
            + "{\"name\":\"37\",\"value\":\"12\",\"adding\":\"0\"},\n"
            + "{\"name\":\"38\",\"value\":\"6\",\"adding\":\"0\"},\n"
            + "{\"name\":\"39\",\"value\":\"2\",\"adding\":\"0\"},\n"
            + "{\"name\":\"41\",\"value\":\"8\",\"adding\":\"0\"},\n"
            + "{\"name\":\"42\",\"value\":\"15\",\"adding\":\"0\"},\n"
            + "{\"name\":\"43\",\"value\":\"40\",\"adding\":\"0\"},\n"
            + "{\"name\":\"45\",\"value\":\"2\",\"adding\":\"1\"},\n"
            + "{\"name\":\"48\",\"value\":\"3\",\"adding\":\"1\"},\n"
            + "{\"name\":\"49\",\"value\":\"3\",\"adding\":\"0\"},\n"
            + "{\"name\":\"50\",\"value\":\"3\",\"adding\":\"0\"},\n"
            + "{\"name\":\"51\",\"value\":\"3\",\"adding\":\"0\"},\n"
            + "{\"name\":\"52\",\"value\":\"6\",\"adding\":\"0\"},\n"
            + "{\"name\":\"54\",\"value\":\"3\",\"adding\":\"1\"},\n"
            + "{\"name\":\"55\",\"value\":\"3\",\"adding\":\"1\"},\n"
            + "{\"name\":\"60\",\"value\":\"3\",\"adding\":\"1\"},\n"
            + "{\"name\":\"62\",\"value\":\"2\",\"adding\":\"1\"},\n"
            + "{\"name\":\"63\",\"value\":\"3\",\"adding\":\"1\"},\n"
            + "{\"name\":\"70\",\"value\":\"3\",\"adding\":\"0\"},\n"
            + "{\"name\":\"90\",\"value\":\"42\",\"adding\":\"0\"},\n"
            + "{\"name\":\"100\",\"value\":\"2\",\"adding\":\"1\"},\n"
            + "{\"name\":\"102\",\"value\":\"2\",\"adding\":\"1\"},\n"
            + "{\"name\":\"103\",\"value\":\"2\",\"adding\":\"1\"},\n"
            + "{\"name\":\"104\",\"value\":\"3\",\"adding\":\"1\"},\n"
            + "{\"name\":\"105\",\"value\":\"3\",\"adding\":\"1\"},\n"
            + "{\"name\":\"120\",\"value\":\"3\",\"adding\":\"1\"},\n"
            + "{\"name\":\"128\",\"value\":\"16\",\"adding\":\"0\"}]";

    /**
     *
     * @param request
     * @return
     */
    public static String createMsg(String[] request) {
        //String processingcode= request[3].substring(0,2);

        String msgtype = request[0];
        String bitMap1 = "1";
        String bitMap2 = "";
        String content = "";
        for (int i = 2; i < request.length; i++) {
            if (request[i] != null) {
                content = content + request[i];
                if (i <= 64) {
                    bitMap1 = bitMap1 + "1";
                } else {
                    bitMap2 = bitMap2 + "1";
                }
            } else {
                if (i <= 64) {
                    bitMap1 = bitMap1 + "0";
                } else {
                    bitMap2 = bitMap2 + "0";
                }

            }
        }
        String data = msgtype + convertBinarytoHexa(bitMap1) + convertBinarytoHexa(bitMap2) + content;
        String header = String.valueOf(data.length());
        while (header.length() < 4) {
            header = "0" + header;
        }
        data = header + data;
        return data;
    }

    /**
     *
     * @param msg
     * @return
     * @throws IOException
     */
    public static String[] readMsg(String msg) throws IOException {
        String header = msg.substring(0, 4);
        String messageType = msg.substring(4, 8);
        String bitMap1 = msg.substring(8, 24);
        bitMap1 = convertHexadecimal2Binary(bitMap1);
        char[] arrayBitMap1 = bitMap1.toCharArray();
        String s = "1";
        char c = s.charAt(0);
        String content;
        if (arrayBitMap1[0] == c) {
            content = msg.substring(40);
        } else {
            content = msg.substring(24);
        }
        String bitMap2 = msg.substring(24, 40);
        char[] arrayBitMap2;
        String[] result = new String[129];
        result[0] = messageType;
        int beginIndex = 0;
        for (int i = 1; i < arrayBitMap1.length; i++) {
            int lenght2 = 0;
            int lenght = 0;
            if (arrayBitMap1[i] == c) {
                IBTDEDetail obj = getDEObject(String.valueOf(i + 1));
                lenght = Integer.valueOf(obj.getValue());
                String adding = obj.getAdding();
                if (adding.endsWith("1")) {
                    lenght2 = lenght;
                    lenght = Integer.valueOf(content.substring(beginIndex, beginIndex + lenght));
                }
                //result[i+1] =  content.substring(beginIndex+lenght2,beginIndex+lenght2+lenght);
                result[i + 1] = content.substring(beginIndex, beginIndex + lenght2 + lenght);
            }
            beginIndex = beginIndex + lenght2 + lenght;
        }
        if (arrayBitMap1[0] == c) {
            bitMap2 = convertHexadecimal2Binary(bitMap2);
            arrayBitMap2 = bitMap2.toCharArray();
            for (int j = 0; j < arrayBitMap2.length; j++) {
                int lenght2 = 0;
                int lenght = 0;
                if (arrayBitMap2[j] == c) {
                    IBTDEDetail obj = getDEObject(String.valueOf(j + 1 + 64));
                    lenght = Integer.valueOf(obj.getValue());
                    String adding = obj.getAdding();
                    if (adding.endsWith("1")) {
                        lenght2 = lenght;
                        lenght = Integer.valueOf(content.substring(beginIndex, beginIndex + lenght));
                    }
                    // result[j+1+64] =  content.substring(beginIndex+lenght2,beginIndex+lenght2+lenght);
                    result[j + 1 + 64] = content.substring(beginIndex, beginIndex + lenght2 + lenght);
                }
                beginIndex = beginIndex + lenght2 + lenght;
            }
        }
        return result;
    }

    private static IBTDEDetail getDEObject(String input) {
        try {
            JSONParser parser = new JSONParser();

            JSONArray a = (JSONArray) parser.parse(jsonListDE);
            for (Object o : a) {
                JSONObject person = (JSONObject) o;
                String name = (String) person.get("name");
                String value = (String) person.get("value");
                String adding = (String) person.get("adding");
                if (name.equals(input)) {
                    IBTDEDetail objIBT = new IBTDEDetail();
                    objIBT.setName(name);
                    objIBT.setAdding(adding);
                    objIBT.setValue(value);
                    return objIBT;
                }
            }
            return null;

        } catch (Exception ex) {
            return null;
        }
    }

    /**
     *
     * @param channel
     * @return
     */
    public static String getF60(String channel) {
        switch (channel) {
            case "EBANK":
                return "04";
            case "CH":
                return "04";
            case "ATM":
                return "01";
            case "COUNTER1":
                return "02";
            case "COUNTER2":
                return "02";
            case "COUNTER3":
                return "02";
            case "CRM1":
                return "02";
            case "CRM2":
                return "02";
            case "CRM3":
                return "02";
            case "POS":
                return "03";
            case "MB":
                return "05";
            case "SMS":
                return "06";
            case "QR":
                return "99";
            default:
                return "00";
        }
    }

    /**
     *
     * @param funtion
     * @return
     */
    public static String getF62(String funtion) {
        switch (funtion) {
            case "QUE":
                return "06" + "IF_INQ";
            case "TRN":
                return "06" + "IF_DEP";
            default:
                return "00";
        }
    }

    /**
     *
     * @param str
     * @param maxlength
     * @param chartoadd
     * @return
     */
    public static String addExtraChar(String str, int maxlength, String chartoadd) {
        String newstr = "";
        for (int j = 0; j < (maxlength - str.length()); j++) {
            newstr += chartoadd;
        }
        return newstr + str;
    }

    /**
     *
     * @param input
     * @return
     * @throws Exception
     */
    public static String GetCS(String input) throws Exception {
        String str = "";
        String strMa = Common.EncriptMD5(input);
        String strKQ = strMa;
        String MaBM = "5" + ZAK + "5";
        if (Common.isNumeric(MaBM)) {//Ma bi mat la so
            char[] chars = MaBM.toCharArray();
            for (int i = 0; i < chars.length - 1; i++) {

                str = str + strMa.substring(Integer.valueOf(String.valueOf(chars[i])), Integer.valueOf(String.valueOf(chars[i])) + 20 - Integer.valueOf(String.valueOf(chars[i + 1])));
            }
            strKQ = Common.EncriptMD5(str);
        }
        return strKQ;
    }

    /**
     *
     * @param FromNumber
     * @param ProcessingCode
     * @param strAmount
     * @param Transdate
     * @param AuditNumber
     * @param Ref_no_F37
     * @param CCY
     * @param channel
     * @param TypeFunction
     * @param BenID
     * @param ToNumber
     * @param Desc
     * @param FullName
     * @param msgtype
     * @param responsecode
     * @param F48
     * @param F7
     * @param Acquiring_Code
     * @param F15
     * @param F63
     * @param F102
     * @param F9
     * @param F18
     * @param F12
     * @param F13
     * @param F41
     * @return
     * @throws Exception
     */
    public static String[] getDataForCall(String FromNumber, String ProcessingCode,
            String strAmount, String Transdate, String AuditNumber,
            String Ref_no_F37, String CCY, String channel, String TypeFunction,
            String BenID, String ToNumber, String Desc, String FullName, String msgtype,
            String responsecode, String F48, String F7, String Acquiring_Code, String F15,
            String F63, String F102, String F9, String F18, String F12, String F13, String F41) throws Exception {
        String[] paramRequest = new String[129];
        paramRequest[0] = msgtype;
        paramRequest[2] = IBTV2Controller.addExtraChar(String.valueOf(FromNumber.length()), 2, "0") + FromNumber;
        paramRequest[3] = ProcessingCode;
        paramRequest[4] = strAmount;
        paramRequest[7] = F7; //MMddHHmmss
        paramRequest[11] = AuditNumber;

        if (msgtype.equals("0200")) {
            paramRequest[22] = "000";
            paramRequest[25] = "00";
            paramRequest[42] = "000000000000001";
            paramRequest[43] = "IBT SMARTLINK            HANOI       VNM";
            paramRequest[12] = Transdate.substring(4);
            paramRequest[13] = Transdate.substring(0, 4);
            paramRequest[41] = "00000001";
        } else {
            paramRequest[12] = F12;
            paramRequest[13] = F13;
            paramRequest[41] = F41;
            if (F18 != null) {
                paramRequest[18] = F18;
            }
            if (ProcessingCode.substring(0, 2).equals("91")) {
                paramRequest[9] = F9;
            } else {
                paramRequest[120] = IBTV2Controller.addExtraChar(String.valueOf(FullName.length()), 3, "0") + FullName;
            }
            paramRequest[15] = F15;
            paramRequest[63] = F63;
            if (responsecode.equals("00")) {
                //paramRequest[38]= "000000";
                if (ProcessingCode.substring(0, 2).equals("91")) {
                    paramRequest[5] = strAmount;
                    paramRequest[50] = CCY;
                }
            }
            paramRequest[39] = responsecode;
            paramRequest[38] = RandomStringUtils.randomAlphabetic(6);
        }
        paramRequest[32] = "06" + Acquiring_Code;
        paramRequest[37] = Ref_no_F37;

        paramRequest[48] = IBTV2Controller.addExtraChar(String.valueOf(F48.length()), 3, "0") + F48;
        paramRequest[49] = "704";
        paramRequest[60] = "002" + channel;
        paramRequest[62] = IBTV2Controller.getF62(TypeFunction);
        if (ProcessingCode.substring(4, 6).endsWith("20")) {
            //tonumer is account
            paramRequest[100] = IBTV2Controller.addExtraChar(String.valueOf(BenID.length()), 2, "0") + BenID;
        }
        paramRequest[102] = IBTV2Controller.addExtraChar(String.valueOf(FromNumber.length()), 2, "0") + F102;
        paramRequest[103] = IBTV2Controller.addExtraChar(String.valueOf(ToNumber.length()), 2, "0") + ToNumber;
        paramRequest[104] = IBTV2Controller.addExtraChar(String.valueOf(Desc.length()), 3, "0") + Desc;
        String dataForEnscript = "0200" + paramRequest[2] + paramRequest[3] + paramRequest[4]
                + paramRequest[7]
                + paramRequest[11] + paramRequest[37]
                + paramRequest[41]
                + paramRequest[48]
                + paramRequest[102] + paramRequest[103];
        paramRequest[128] = getMAC(paramRequest, "SCB");
        return paramRequest;
    }

    /**
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] getMAC(byte[] key, byte[] data) {
        BlockCipher cipher = new DESEngine();

        Mac mac = new ISO9797Alg3Mac(cipher, 64, new ISO7816d4Padding());
        KeyParameter keyP = new KeyParameter(key);
        mac.init(keyP);
        mac.update(data, 0, data.length);

        byte[] out = new byte[8];

        mac.doFinal(out, 0);
        return out;
    }

    /**
     *
     * @param listData
     * @param partner
     * @return
     */
    public static String getMAC(String[] listData, String partner) {
        try {
            //DE2, DE3, DE4, DE5, DE6, DE7, DE11, DE32, DE37, DE38, DE39, DE41, DE42, DE48, DE63, DE90, DE102, DE103
            String data = (listData[0] == null ? "" : listData[0])
                    + (listData[2] == null ? "" : listData[2])
                    + (listData[3] == null ? "" : listData[3])
                    + (listData[4] == null ? "" : listData[4])
                    + (listData[5] == null ? "" : listData[5])
                    + (listData[6] == null ? "" : listData[6])
                    + (listData[7] == null ? "" : listData[7])
                    + (listData[11] == null ? "" : listData[11])
                    + (listData[32] == null ? "" : listData[32])
                    + (listData[37] == null ? "" : listData[37])
                    + (listData[38] == null ? "" : listData[38])
                    + (listData[39] == null ? "" : listData[39])
                    + (listData[41] == null ? "" : listData[41])
                    + (listData[42] == null ? "" : listData[42])
                    + (listData[48] == null ? "" : listData[48])
                    + (listData[63] == null ? "" : listData[63])
                    + (listData[90] == null ? "" : listData[90])
                    + (listData[102] == null ? "" : listData[102])
                    + (listData[103] == null ? "" : listData[103]);

            //String clear_component = MAC_Util.getClearKey(ZAK);
            String clear_component = ZAK;
            byte[] MAC = MAC_Util.getDigest(data, clear_component);
            return ISOUtil.hexString(MAC);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    /**
     *
     * @param listData
     * @return
     */
    public static boolean isVerifyMac(String[] listData) {
        String compareData = getMAC(listData, "NAPAS");
        String encode = listData[128];
        if (compareData.endsWith(encode)) {
            return true;
        }
        return false;
    }

    private static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character
                    .digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    final private static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    private static byte[] retailMac(byte[] key, byte[] data) {
        try {
            // Create Keys
            byte[] key1 = Arrays.copyOf(key, 8);
            byte[] key2 = Arrays.copyOfRange(key, 8, 16);

            // ISO/IEC 9797-1 or ISO 7816d4 Padding for data (adding 80 00 ..)
            byte[] pdata = addPadding(data);

            SecretKeyFactory mySecretKeyFactory = SecretKeyFactory.getInstance("DES");

            DESKeySpec myKeySpec1 = new DESKeySpec(key1);
            SecretKey myKey1 = mySecretKeyFactory.generateSecret(myKeySpec1);
            Cipher cipher1 = Cipher.getInstance("DES/CBC/NoPadding");
            cipher1.init(Cipher.ENCRYPT_MODE, myKey1, new IvParameterSpec(
                    new byte[8]));

            DESKeySpec myKeySpec2 = new DESKeySpec(key2);
            SecretKey myKey2 = mySecretKeyFactory.generateSecret(myKeySpec2);
            Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
            cipher2.init(Cipher.DECRYPT_MODE, myKey2, new IvParameterSpec(
                    new byte[8]));

            byte[] result = cipher1.doFinal(pdata);

            byte[] block = Arrays.copyOfRange(result, result.length - 8, result.length);

            // Decrypt the resulting block with Key-2
            block = cipher2.doFinal(block);

            // Encrypt the resulting block with Key-1
            block = cipher1.doFinal(block);

            return block;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] addPadding(byte[] in) {
        int extra = 8 - (in.length % 8);
        int newLength = in.length + extra;
        byte[] out = Arrays.copyOf(in, newLength);
        int offset = in.length;
//        out[offset] = (byte) 0x80;
//        offset++;
        while (offset < newLength) {
            out[offset] = (byte) 0;
            offset++;
        }
        return out;
    }

    /**
     *
     * @param TypeFromNumber
     * @param TypeToNumber
     * @param TypeFunction
     * @return
     */
    public static String getProcessingCode(String TypeFromNumber, String TypeToNumber, String TypeFunction) {
        if (TypeFunction.endsWith("QUE")) {
            TypeFunction = "43";
        } else {
            TypeFunction = "91";
        }

        if (TypeFromNumber.equals("CARD")) {
            TypeFromNumber = "00";
        } else {
            TypeFromNumber = "20";
        }
        if (TypeToNumber.equals("CARD")) {
            TypeToNumber = "00";
        } else {
            TypeToNumber = "20";
        }
        return TypeFunction + TypeFromNumber + TypeToNumber;
    }

    /**
     *
     */
    public enum ErrorResultEnum {

        /**
         *
         */
        OK("00"),
        /**
         *
         */
        INVALID_ACCOUNT("76"),
        /**
         *
         */
        INVALID_CARD("14"),
        /**
         *
         */
        UNABLE_TO_PROCESS("05");

        private String code;

        private ErrorResultEnum(String c) {
            code = c;
        }

        /**
         *
         * @param value
         * @return
         */
        public static ErrorResultEnum get(String value) {
            for (ErrorResultEnum pce : ErrorResultEnum.values()) {
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
     * @param responsecode
     * @return
     */
    public static String getErrorMessage(String responsecode) {
        switch (responsecode) {
            case "00":
                return "Giao dịch thành công.";
            case "01":
                return "Chủ thẻ nên liên hệ với Ngân hàng phát hành thẻ.";
            case "03":
                return "Mã đại lý không hợp lệ.";
            case "04":
                return "Thẻ của bạn đã bị thu hồi.";
            case "05":
                return "Lỗi trong việc xử lý giao dịch.";
            case "12":
                return "Giao dịch không hợp lệ.";
            case "13":
                return "Giá trị không hợp lệ.";
            case "14":
                return "Số thẻ không hợp lệ.";
            case "15":
                return "Không tìm thấy ngân hàng phát hành";
            case "21":
                return "Chủ thẻ cần kích hoạt lại thẻ";
            case "25":
                return "Không có giao dịch gốc đề nghị liên hệ ngân hàng phát hành.";
            case "30":
                return "Lỗi định dạng thông điệp";
            case "34":
                return "Thu hồi thẻ.";
            case "39":
                return "Tài khoản tín dụng không hợp lệ.";
            case "41":
                return "Thu hồi thẻ.";
            case "43":
                return "Thu hồi thẻ.";
            case "51":
                return "Số dư tài khoản không đủ.";
            case "53":
                return "Tài khoản tiết kiệm không hợp lệ.";
            case "54":
                return "Thẻ hết hạn.";
            case "96":
                return "Hệ thống chuyển mạch NAPAS gặp sự cố.";
            case "68":
                return "Giao dịch bị timeout.";
            case "75":
                return "Tài khoản không hợp lệ";
            case "61":
                return "Số tiền giao dịch bị vượt hạn mức cho phép.";
            case "-1":
                return "Lỗi hệ thống. Giao dịch phản hồi của NAPAS sai chữ ký.";
            case "-2":
                return "Lỗi hệ thống. Không đủ tham số.";
            case "-3":
                return "Thẻ đích thuộc Ngân hàng SCB";
            case "-4":
                return "Thẻ/Tài khoản không đủ số dư";
            case "-5":
                return "Thẻ/Tài khoản SCB không hợp lệ.";
            case "-6":
                return "Sai thông tin tài khoản.";
            case "-7":
                return "Nhỏ hơn số tiền tối thiểu";
            case "-8":
                return "Gửi yêu cầu đến NAPAS timeout.";
            case "-9":
                return "Vượt hạn mức giao dịch 1 lần tại SCB.";
            case "-10":
                return "Hạch toán timeout.";
            case "-11":
                return "Hạch toán thất bại.";
            case "-12":
                return "Vượt hạn mức giao dịch 1 ngày tại SCB.";
            case "-13":
                return "Response Napas sai định dạng.Chờ đối soát.";
            case "99":
                return "Lỗi xử lý.";
            default:
                return "Ngân hàng thụ hưởng từ chối giao dịch.";
        }

    }

    /**
     *
     * @param ProcessingCode
     * @return
     */
    public static boolean isCheckProcessingCode(String ProcessingCode) {
        if (ProcessingCode.equals("430020") || ProcessingCode.equals("432020") || ProcessingCode.equals("430000") || ProcessingCode.equals("432000")
                || ProcessingCode.equals("910000") || ProcessingCode.equals("910020") || ProcessingCode.equals("912000") || ProcessingCode.equals("912020")) {
            return true;
        }
        return false;

    }

    /**
     *
     */
    public enum StatusResultEnum {

        //String status="00"; //00: Thanh cong; 01: NAPAS PHAN HOI THAT BAI HOAN TIEN; 02: TRU TIEN THAT BAI; 03: TRU TIEN THANH CONG DOI TRA SOAT;04: GOI CORE TIMEOUT; 05: SAI CHU KY
        /**
         *
         */
        SUCCESS("00"),
        /**
         *
         */
        UNSUCCESS("01"),
        /**
         *
         */
        TRANSFERUNSUCCESS("19"), // cat tien ko thanh cong hoac giao dịch duoc hoan tien

        /**
         *
         */
        COREFAILED("17"),
        /**
         *
         */
        INVALID_FORMAT("03"),
        /**
         *
         */
        INVALID_SOURCENUMBER("05"),
        /**
         *
         */
        INVALID_DESTNUMBER("08"),
        /**
         *
         */
        NOTEXIST_DESTNUMBER("09"),
        /**
         *
         */
        DOUBT_DESTNUMBER("11"),
        /**
         *
         */
        DESTNUMBER_INSCB("25"),
        /**
         *
         */
        INVALID_BANK("14"),
        /**
         *
         */
        BALANCE("04"), //khong du so du

        /**
         *
         */
        TIMEOUT68("24"),
        /**
         *
         */
        TIMEOUT("18"),
        /**
         *
         */
        QUE_TIMEOUT("07"),
        /**
         *
         */
        QUE_TIMEOUT_68("15"),
        /**
         *
         */
        CORE_TIMEOUT("16"),
        /**
         *
         */
        OVERLIMIT1TIME("23"),
        /**
         *
         */
        OVERLIMITNOTIME("23"),
        /**
         *
         */
        OVERLIMIT1DAY("20"),
        /**
         *
         */
        MINLIMIT("22"),
        /**
         *
         */
        CHECK_RESPONSE_FORMAT_FAILED("26"),
        /**
         *
         */
        CHECK_RESPONSE_SIGN_FAILED("27"),
        COREFAILED_OVERLIMITKYC("28"),
        /**
         *
         */
        ERROR_SYSTEM("99"),
        /**
         *
         */
        Losed_DESTNUMBER("10"),
        /**
         *
         */
        expired_DESTNUMBER("12");

        private String code;

        private StatusResultEnum(String c) {
            code = c;
        }

        /**
         *
         * @param value
         * @return
         */
        public static StatusResultEnum get(String value) {
            for (StatusResultEnum pce : StatusResultEnum.values()) {
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
     * @param binary
     * @return
     */
    public static String convertBinarytoHexa(String binary) {
        int digitNumber = 1;
        int sum = 0;
        String result = "";
        for (int i = 0; i < binary.length(); i++) {
            if (digitNumber == 1) {
                sum += Integer.parseInt(binary.charAt(i) + "") * 8;
            } else if (digitNumber == 2) {
                sum += Integer.parseInt(binary.charAt(i) + "") * 4;
            } else if (digitNumber == 3) {
                sum += Integer.parseInt(binary.charAt(i) + "") * 2;
            } else if (digitNumber == 4 || i < binary.length() + 1) {
                sum += Integer.parseInt(binary.charAt(i) + "") * 1;
                digitNumber = 0;
                if (sum < 10) {
                    result = result + sum;
                } else if (sum == 10) {
                    result = result + "A";
                } else if (sum == 11) {
                    result = result + "B";
                } else if (sum == 12) {
                    result = result + "C";
                } else if (sum == 13) {
                    result = result + "D";
                } else if (sum == 14) {
                    result = result + "E";
                } else if (sum == 15) {
                    result = result + "F";
                }
                sum = 0;
            }
            digitNumber++;
        }
        return result;
    }
    String HEX_STRING = "0123456789ABCDEF";

    /**
     *
     * @param s
     * @return
     */
    public static String convertHexadecimal2Binary(String s) {

        String result = "";
        String bin = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                bin = "1010";
            } else if (s.charAt(i) == 'B') {
                bin = "1011";
            } else if (s.charAt(i) == 'C') {
                bin = "1100";
            } else if (s.charAt(i) == 'D') {
                bin = "1101";
            } else if (s.charAt(i) == 'E') {
                bin = "1110";
            } else if (s.charAt(i) == 'F') {
                bin = "1111";
            } else if (s.charAt(i) == '1') {
                bin = "0001";
            } else if (s.charAt(i) == '1') {
                bin = "0001";
            } else if (s.charAt(i) == '2') {
                bin = "0010";
            } else if (s.charAt(i) == '3') {
                bin = "0011";
            } else if (s.charAt(i) == '4') {
                bin = "0100";
            } else if (s.charAt(i) == '5') {
                bin = "0101";
            } else if (s.charAt(i) == '6') {
                bin = "0110";
            } else if (s.charAt(i) == '7') {
                bin = "0111";
            } else if (s.charAt(i) == '8') {
                bin = "1000";
            } else if (s.charAt(i) == '9') {
                bin = "1001";
            } else if (s.charAt(i) == '0') {
                bin = "0000";
            }
            result = result + bin;
        }
        return result;
    }

    private static String addExtraSpace(String str, int maxlength) {
        return addExtraChar(str, maxlength, " ");
    }

    /**
     *
     * @param listReponse
     * @param listRequest
     * @return
     * @throws ParseException
     */
    public static boolean checkFormatResponse(String[] listReponse, String[] listRequest) throws ParseException {

        // return true;
        try {
            for (int i = 0; i < 129; i++) {
                if (i == 4 || i == 7 || i == 11 || i == 12) {
                    if (!listReponse[i].equals(listRequest[i])) {
                        Helper.writeLogErrorNonDB(IBTV2Controller.class, "loi checkFormatResponse:" + i);
                        return false;
                    }
                }
                if (i == 13) {
                    String type = listReponse[3].substring(0, 2);
                    if (type.equals("91")) {
                        if (!listReponse[i].equals(listRequest[i])) {
                            Helper.writeLogErrorNonDB(IBTV2Controller.class, "loi checkFormatResponse:" + i);
                            return false;
                        }
                    }
                }
                if (i == 15) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
                    Date date = sdf.parse(listReponse[i]);
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     *
     * @param AuditNumber
     * @throws RemoteException
     */
    public void putRequest(String AuditNumber) throws RemoteException, Exception {
        /*try {
            if(!Configuration.initMQInbox)
           {
               Configuration.initMQInbox = Configuration.mq.initInboxQ();
           }
            Configuration.mq.putMsgToINBOXQueue(AuditNumber.getBytes());
        } catch (Exception ex) {
            Helper.writeLogErrorNonDB(IBTV2Controller.class, "ERROR putRequest :"+ ex.getMessage());
            throw ex;
        }*/
    }
    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    private static byte[] encodeUTF8(String string) {
        return string.getBytes(UTF8_CHARSET);
    }

    /**
     *
     * @param auditnumber
     * @return
     * @throws RemoteException
     */
    public static String getResponsefromdb(String auditnumber) throws RemoteException {
        try {
            Helper.writeLogErrorNonDB(IBTV2Controller.class, "check db for trace:" + auditnumber);
            //KIEM TRA DUOI DATABASE
            return scb.com.vn.utility.Helper.getDBI().NAPAS_getResponseIBT(auditnumber);
        } catch (Exception ex1) {
            Helper.writeLogErrorNonDB(IBTV2Controller.class, "ERROR getResponsefromdb() trace:" + auditnumber + "-" + ex1.getMessage());
            return null;
        }
    }

    public String getResponse(String traceandf37) throws Exception {
        /*
       try
       {
           if(!Configuration.initMQOutbox)
           {
               Configuration.initMQOutbox = Configuration.mq.initOutboxMQ();
           }
           String response  = Configuration.mq.getFromOUTBOXMQ(traceandf37);
           return response;
       }
       catch(Exception ex)
       {
           //Helper.writeLogErrorNonDB(IBTV2Controller.class, "ERROR getResponse from mq trace " + auditnumber + ":" + ex.getMessage());
           
       }*/
        return null;
    }

    /*Gia lap ket qya tra ve tu napas*/
    public String simulatorResponseFromNapas(String channel, String typeToNumber, String account) throws Exception {
        List<String> listAccount = new ArrayList<>();
        listAccount.add("18254140002");
        listAccount.add("20625700001");
        listAccount.add("1234567897");
        listAccount.add("4536180593355659");
        listAccount.add("0936730706");
        listAccount.add("100873883763");
        listAccount.add("19027775120017");
        listAccount.add("15248062112");
        listAccount.add("0721000653559");
        listAccount.add("068562437778");
        listAccount.add("19033017257011");
        listAccount.add("336800040063");
        listAccount.add("108000000306");
        listAccount.add("123888886");
        listAccount.add("25665464");
        listAccount.add("327406236");
        listAccount.add("0071000739124");
        listAccount.add("255125909");
        listAccount.add("11377177");
        listAccount.add("18529610001");
        listAccount.add("100873883763");
        listAccount.add("060261437778");
        listAccount.add("4524041873794575");
        
        if (channel.contains("CRM")) {
            if ("CARD".equals(typeToNumber)) {
                if (account.length() > 16) {
                    return "08#08#null";
                }
            } else {
                if (account.length() > 12) {
                    return "08#08#null";
                }
            }
            if (!listAccount.contains(account)) {
                return "09#09#null";
            }
        }
        
        /*Test RIA*/
        if(channel.contains("CH")){
            if ("CARD".equals(typeToNumber)) {
                if (account.length() > 16) {
                    return "08#08#null";
                }
            } 
//            else {
//                if (account.length() > 12) {
//                    return "08#08#null";
//                }
//            }
            if(account.equals("147258369") || account.equals("369258147")){
                return "10#10#null";
            }
            if (!listAccount.contains(account)) {
                return "09#09#null";
            }
            
        }
        return "00#00#NGUYEN THI BE BA";
    }
}