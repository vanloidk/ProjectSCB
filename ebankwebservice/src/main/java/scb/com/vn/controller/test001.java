/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Console;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import scb.com.vn.model.NotificationFirbaseResp;
import scb.com.vn.utility.CryptoUtils;
import static scb.com.vn.utility.CryptoUtils.TripleDESSecretKey;
import static scb.com.vn.utility.CryptoUtils.TripleDESVectorKey;
import scb.com.vn.utility.TripleDESCard;

/**
 *
 * @author loinv3
 */
public class test001 {

     private boolean delivered = false;
     
    private static String ACTION_INQUIRE = "INQUIRE_BANK";
    private static String VERSION = "2.0.0";
    private static String idPartner = "262125";
    private static String SERVICE_ID = "10003";
    private static String Idprovider = "VNP";
    private static String transIdSCB = "20051201a3aaaf3fff1a3";
    private static String channel = "7";
    private static String dataInput = "{\"DIEUKIEN\":\"0\",\"MATRACUU\":\"0948900989\"}";
    // ""{\"DIEUKIEN\":\"2\",\"MATRACUU\":\"THA1876212\"}"; //"{\"MATINH\":\"HNI\",\"Ma_TT\":\"THA1876212\"}";  //
    private static String transDate = "20230417094100";
    private static String SECRET_KEY = "7dead089f2960d14148c06b343da1298";
    private static String CustomerCode = "";
    private static String AddInfo = "";

    private static String PACTION_INQUIRE = "RELIEF_BANK";
    private static String PVERSION = "2.0.0";
    private static String PidPartner = "2901";
    private static String PSERVICE_ID = "10003";
    private static String PIdprovider = "VNP";
    private static String PCustomerCode = "DLCDD00341631";
    private static String Pchannel = "4";
    private static String PtransIdSCB = "16399426";
    private static String PAddInfo = "NOCUOC";
    private static String POption = "{\"PROVINCE\":\"DLK\",\"RECEIVE_CODE\":\"MEDIA\"}";
    private static String billAmount = "6134";
    private static String billDetail = "[{\"AMOUNT\":\"47816\"}]";
    private static String PtransDate = "20240819102828";
    private static String Ptype = "IN";


    public  void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public  static void main(String[] args) throws NoSuchAlgorithmException, Exception {
        
        
        
        test aa = new test();
        
        
        

    

    /*
         strBills = strBills + "#";
                LocalDateTime datePaid;
                if (b.getMonth() != null) {
                    datePaid = LocalDateTime.parse(b.getMonth());
                    msgSendCore = msgSendCore + datePaid.getMonthValue() + "/" + datePaid.getYear();
                    if (cnt < bills.length) {
                        msgSendCore = msgSendCore + "-";
                    }
                }
            }
     */
    LocalDateTime myDateObj = LocalDateTime.now();
    String oldDate = "2024-07";
    DateFormat formatter = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat sdfM = new SimpleDateFormat("MM"); // Định dạng lấy tháng
    SimpleDateFormat sdfY = new SimpleDateFormat("yyyy"); // Định dạng lấy tháng
    DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = formatter.parse(oldDate);

    String month = sdfM.format(date);
    String year = sdfY.format(date);

    LocalDateTime currentDate = LocalDateTime.parse(oldDate);
    String nnxxx = currentDate.getMonthValue() + "/" + currentDate.getYear();
    String nnxxx2 = month + "/" + year;

    List<NotificationFirbaseResp.result> vvv = new ArrayList<>();
    NotificationFirbaseResp.result ii = new NotificationFirbaseResp.result();

    ii.setError (

    "aaaa");
        
    vvv.add (ii);

    ii.setError (

    "ccc");
    vvv.add (ii);
    GsonBuilder builder = new GsonBuilder();

    builder.serializeNulls ();
    Gson gson = builder.create();

    String kks = gson.toJson(vvv);

    //TripleDESBankCard tripleDES = new TripleDESBankCard("9mng65vscb4lxn9scbbf981m");          
    //bb = newpassword.substring(0,2) + "AA1A" + newpassword.substring(2, newpassword.length()) + newpassword.substring(0,1);
    String secureCode = ACTION_INQUIRE.concat("|").
            concat(VERSION).concat("|").
            concat(idPartner).concat("|").
            concat(SERVICE_ID).concat("|").
            concat(Idprovider).concat("|").
            concat(transIdSCB).concat("|").
            concat(channel).concat("|").
            concat(dataInput).concat("|").
            concat(transDate).concat("|").
            concat(SECRET_KEY);
    String secureCodeSHA256 = DigestUtils.sha256Hex(secureCode);

    String aaa02 = PACTION_INQUIRE.concat("|").
            concat(PVERSION).concat("|").
            concat(PidPartner).concat("|").
            concat(PSERVICE_ID).concat("|").
            concat(PIdprovider).concat("|").
            concat(PCustomerCode).concat("|").
            concat(Pchannel).concat("|").
            concat(PtransIdSCB).concat("|").
            concat(PAddInfo).concat("|").
            concat(POption).concat("|").
            concat(billAmount).concat("|").
            concat(billDetail).concat("|").
            concat(PtransDate).concat("|").
            concat(Ptype).concat("|").
            concat(SECRET_KEY);
    String secureCodeSHA25602 = DigestUtils.sha256Hex(aaa02);

    System.out.println (secureCodeSHA256);

    System.out.println (secureCodeSHA25602);

    
        try {
            String vv = TripleDESCard.encrypt("800000003385");
        String kk3 = TripleDESCard.decrypt("9A7CC5D9A1820EAA9F50D96C23CE08AC");
        String VVV = CryptoUtils.EncryptionTripleDES("CCCCC");
        String result = DecryptionTripleDES(VVV);
        // String test01 = tripleDES.encryptIso(aaa);
        //String kkv= tripleDES.decryptIso(test01);

        String decrpt = "mye url with spaces";
        String kk = "CCCCC";
        String bb = decrpt.replaceAll("e", "ABC");

        System.out.println(bb);
        System.out.println(result);
    }
    catch (NoSuchAlgorithmException ex

    
        ) {
            Logger.getLogger(test001.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (NoSuchPaddingException ex

    
        ) {
            Logger.getLogger(test001.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (InvalidKeyException ex

    
        ) {
            Logger.getLogger(test001.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (InvalidAlgorithmParameterException ex

    
        ) {
            Logger.getLogger(test001.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IllegalBlockSizeException ex

    
        ) {
            Logger.getLogger(test001.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (BadPaddingException ex

    
        ) {
            Logger.getLogger(test001.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public static String DecryptionTripleDES(String encryptedMessageBytes02) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        /*
        String payloadEncryption = "abc"
        byte[] secretKey = TripleDESSecretKey.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "TripleDES");
        byte[] iv = TripleDESVectorKey.getBytes();
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher encryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
        byte[] secretMessagesBytes = payloadEncryption.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessagesBytes);
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
         */
        
        byte[] decodeMessage = Base64.getDecoder().decode(encryptedMessageBytes02);
        byte[] secretKey02 = TripleDESSecretKey.getBytes();
        byte[] iv02 = TripleDESVectorKey.getBytes();
        SecretKeySpec secretKeySpec02 = new SecretKeySpec(secretKey02, "DESede");
        IvParameterSpec ivSpec02 = new IvParameterSpec(iv02);
        Cipher decryptCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec02, ivSpec02);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(decodeMessage);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);

        return decryptedMessage;
    }
}
