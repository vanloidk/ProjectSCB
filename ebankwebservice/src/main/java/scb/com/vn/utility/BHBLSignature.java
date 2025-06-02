/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import vn.com.sml.ibt.rsa.EncodingType;
import vn.com.sml.ibt.rsa.RSASignature;
import vn.com.sml.ibt.rsa.SignatureKeyStoreMode;

/**
 *
 * @author loinv3
 */
public class BHBLSignature {

    private static final Logger logger = Logger.getLogger(BHBLSignature.class);
    public static final String SIGNING_ALGORITHM = "SHA256withRSA";

    public static String requestSignature(String jsonData, String privateKey) throws IOException {
        String result = "";
        try {
            //privatekey = "D:\\EBANKING\\SOURCE_CODE\\ebankwebservice\\config\\RSAPrivate.key";
            PrivateKey aKey = RSASignature.getPrivateKey(privateKey, SignatureKeyStoreMode.STRINGFILE, EncodingType.HEX);
            //PrivateKey aKey = RSASignature.getPrivateKey(privatekey, SignatureKeyStoreMode.XMLFILE, EncodingType.BASE64);
            Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
            signature.initSign(aKey);
            byte[] messageBytes = jsonData.getBytes(Charset.forName("UTF-8"));
            signature.update(messageBytes);
            byte[] digitalSignature = signature.sign();
            result = Base64.encodeBase64String(digitalSignature);

            return result;
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException ex) {
            logger.error(ex);
        }
        return result;
    }

    /**
     * Verify signature
     *
     * @param _signatureKey
     * @param _jsonData
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Boolean VerifySignature(String _signatureKey, String _jsonData) throws NoSuchAlgorithmException {

        Boolean sigVerified = false;
        try {
            //String path = "D:\\EBANKING\\SOURCE_CODE\\ebankwebservice\\config\\RSAPublic.key";
            String path = Configuration.getProperty("bhbl.ws.rsa.publickey");
            PublicKey publicKey = RSASignature.getPublicKey(path, SignatureKeyStoreMode.XMLFILE, EncodingType.BASE64);
            byte[] sig = _signatureKey.getBytes();
            Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(_jsonData.getBytes(Charset.forName("UTF-8")));
            sigVerified = signature.verify(Base64.decodeBase64(sig));

        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException ex) {
            logger.error("Error InvalidKey or NoSuchAlgorithm or Signature key " + ex);
        } catch (Exception ex01) {
            logger.error("Error get url data from config file " + ex01);
        }

        return sigVerified;
    }

    public static String MD5Hash(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(data.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
