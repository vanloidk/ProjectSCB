package scb.com.vn.ultility;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
//import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * This program generates a AES key, retrieves its raw bytes, and then
 * reinstantiates a AES key from the key bytes. The reinstantiated key is used
 * to initialize a AES cipher for encryption and decryption.
 */
public class AES {

    SecretKeySpec skeySpec;
    Cipher cipher = null;
    private static final String salt = "phucnguyen_pwd_dbi";
    private static final int ITERATIONS = 2; // So lan ma hoa chong len.
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = new byte[]{'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e',
        't', 'K', 'e', 'y'};

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }

    public static String decrypt(String value, String salt) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);

        String dValue = null;
        String valueToDecrypt = value;
        for (int i = 0; i < ITERATIONS; i++) {
            //byte[] decordedValue = new Base64().decode(valueToDecrypt);
            byte[] decordedValue = new  BASE64Decoder().decodeBuffer(valueToDecrypt);
            byte[] decValue = c.doFinal(decordedValue);
            dValue = new String(decValue).substring(salt.length());
            valueToDecrypt = dValue;
        }
        return dValue;
    }

    public static String decrypt(String value) throws Exception {
        return decrypt(value, salt);
    }

    public static String encrypt(String value, String salt) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);

        String valueToEnc = null;
        String eValue = value;
        for (int i = 0; i < ITERATIONS; i++) {
            valueToEnc = salt + eValue;
            byte[] encValue = c.doFinal(valueToEnc.getBytes());
            eValue = new BASE64Encoder().encode(encValue);
        //    eValue = new Base64().encodeAsString(encValue);
        }
        return eValue;
    }

    public static String encrypt(String value) throws Exception {
        return encrypt(value, salt);
    }

    /*
     * public static void main(String[] args) { try { String s1 =
     * encrypt("sms"); // s1=
     * "nVrn1YMwkH0A8wfbhRjMo2A6w0Q/rAN3Y+s4TV32FLS/mBmPkGN6Og6uxiaoNo5DLY0GjZkcT2urrc8gTld6Jw=="
     * ; System.out.println("en: " + s1 + " leght:" + s1.length());
     * System.out.println("de: " + decrypt(s1)); } catch (Exception e) {
     * e.printStackTrace(); } }
     */
}
