/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.ultility;

import java.io.IOException;
import scb.com.vn.ultility.rsa.EncodingType;
import java.io.UnsupportedEncodingException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import scb.com.vn.ultility.rsa.RsaAlgorithm;
import scb.com.vn.ultility.rsa.SignatureKeyStoreMode;
/**
 *
 * @author minhndb
 */
public class RsaUtils {
    private static final Logger logger = Logger.getLogger(RsaUtils.class);
    
    private static final int[] mac_vas_query_res = {0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11};
    private static final int[] mac_query_res = {0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 13};    
    private static final int[] mac_vas_pay_res = {0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11};
    private static final int[] mac_transfer_res = {0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 13};
    
    private static final int[] mac_vas_query_req = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10};
    private static final int[] mac_query_req = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12};
    private static final int[] mac_vas_pay_req = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10};
    private static final int[] mac_transfer_req = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12};  
    
    public static String makeSignatureWithUtf8(String stInput, PrivateKey prKey, String algorithm, EncodingType type)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        Signature sig = Signature.getInstance(algorithm);
        sig.initSign(prKey);
        sig.update(stInput.getBytes(UTF_8));
        switch (type) {
            case HEX:
                return new String(Hex.encode(sig.sign()));
            case BASE64:
                return new String(Base64.encode(sig.sign()));
            default:
                return new String(Base64.encode(sig.sign()));
        }
    }
    public static String makeSignature(String stInput, PrivateKey prKey, String algorithm, EncodingType type)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        Signature sig = Signature.getInstance(algorithm);
        sig.initSign(prKey);
        sig.update(stInput.getBytes());
        switch (type) {
            case HEX:
                return new String(Hex.encode(sig.sign()));
            case BASE64:
                return new String(Base64.encode(sig.sign()));
            default:
                return new String(Base64.encode(sig.sign()));
        }
    }
    
    public static boolean verifySignature(PublicKey pubKey, String data, String sigMsg
            , String algorithm, EncodingType type) {
        boolean result = false;
        try {
            Signature sig = Signature.getInstance(algorithm);
            sig.initVerify(pubKey);
            sig.update(data.getBytes());
            switch (type) {
                case HEX:
                    result = sig.verify(Hex.decode(sigMsg));
                    break;
                case BASE64:
                default:
                    result = sig.verify(Base64.decode(sigMsg));
                    break;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }
    
    public static boolean verifyResponse(String[] items, PublicKey publicKey) {
        if (items != null && items.length > 2 && publicKey != null) {
            StringBuilder sb = new StringBuilder();
            String signature = items[items.length - 1];
            switch (items[2]) {
                case "432000":
                case "430000":
                    for (int i = 0; i < mac_vas_query_res.length; i++) {
                        sb.append(items[mac_vas_query_res[i]]);
                    }
                    break;
                case "432020":
                    for (int i = 0; i < mac_query_res.length; i++) {
                        sb.append(items[mac_query_res[i]]);
                    }
                    break;
                case "912000":
                case "910000":
                    for (int i = 0; i < mac_vas_pay_res.length; i++) {
                        sb.append(items[mac_vas_pay_res[i]]);
                    }
                    break;
                case "912020":
                    for (int i = 0; i < mac_transfer_res.length; i++) {
                        sb.append(items[mac_transfer_res[i]]);
                    }
                    break;
                default:
                    logger.warn("Invalid processing code: " + items[2]);
                    return false;
            }
            return verifySignature(publicKey, sb.toString(), signature, RsaAlgorithm.MD5RSA, EncodingType.BASE64);
        } else {
            logger.warn("items = [" + Arrays.toString(items) + "], publicKey = [" + publicKey + "]");
            return false;
        }
    }
    public static PrivateKey getPrivateKey(String str,SignatureKeyStoreMode storeMode, EncodingType type) throws IOException{
        switch(storeMode){
            case STRING:
                return getPrivateKeyfromString(str, type);
            case BINARYFILE:
                return getPrivateKeyfromByteArray(FileUtil.readBytesFromFile(str));
            case STRINGFILE:
                return getPrivateKeyfromString(new String(FileUtil.readBytesFromFile(str)), type);
            default :
                return null;
        }
    }
    private static PrivateKey getPrivateKeyfromString(String strPrivateKey, EncodingType type) {
        switch(type){
            case HEX:
                return getPrivateKeyfromByteArray(Hex.decode(strPrivateKey));
            case BASE64:
                return getPrivateKeyfromByteArray(Base64.decode(strPrivateKey));
            default:
                return getPrivateKeyfromByteArray(Hex.decode(strPrivateKey));
        }
    }

    public static PrivateKey getPrivateKeyfromByteArray(byte[] arr) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new PKCS8EncodedKeySpec(arr);
            PrivateKey privateKey = keyFactory.generatePrivate(publicKeySpec);
            return privateKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}