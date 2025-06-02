package scb.com.vn.utility;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.jpos.iso.ISOUtil;

/**
 *
 * @author minhndb
 */
public class CryptoUtils {
    //Do not modify !!!!!!!!!!!
    //public static final String KeyString="Created by Nguyen Hung Nguyen";

    /**
     *
     */

    public static final String KeyString = "Created by Nguyen Hung Nguyen";
    private static Properties GatewayProperties;
    private static Properties CardProperties;

    public static final String TripleDESSecretKey = "9mng65vscb4lxn9scbbf981m";
    public static final String TripleDESVectorKey = "SCB@2022";

    /**
     *
     */
    public static final short MAX_PIN_LENGTH = 12;
    /**
     * PIN Block Format adopted by ANSI (ANSI X9.8) and is one of two formats
     * supported by the ISO (ISO 95641 - format 0).
     */
    public static final byte FORMAT01 = (byte) 01;
    /**
     * PIN Block Format 02 supports Douctel ATMs.
     */
    public static final byte FORMAT02 = (byte) 02;
    /**
     * PIN Block Format 03 is the Diabold Pin Block format.
     */
    public static final byte FORMAT03 = (byte) 03;
    /**
     * PIN Block Format 04 is the PIN block format adopted by the PLUS network.
     */
    public static final byte FORMAT04 = (byte) 04;
    /**
     * PIN Block Format 05 is the ISO 9564-1 Format 1 PIN Block.
     */
    public static final byte FORMAT05 = (byte) 05;
    /**
     * Proprietary PIN Block format.
     *
     * Most Security Modules use a proprietary PIN Block format when encrypting
     * the PIN under the LMK of the Security Module hence this format
     * (FORMAT00).
     *
     * <p>
     * This is not a standard format, every Security Module would interpret
     * FORMAT00 differently.
     *
     * So, no interchange would accept PIN Blocks from other interchanges using
     * this format. It is useful only when working with PIN's inside your own
     * interchange. </p>
     */
    public static final byte FORMAT00 = (byte) 00;

    /**
     *
     * @throws IOException
     */
    public CryptoUtils() throws IOException {
    }

    /**
     *
     * @param cardNo
     * @return
     */
    public static boolean checkCard(String cardNo) {
        if (CardProperties.containsKey(cardNo)) {
            return true;
        }
        return false;
    }
    private final static String ALGORITHM = "MD5";
    private static MessageDigest md = null;
    private static MessageDigest mds1 = null;

    /**
     *
     * @param dataIn
     * @return
     * @throws Exception
     */
    public static String getEncData(String dataIn) throws Exception {
        String key = CryptoUtils.diggest(KeyString.toCharArray());
        //Form the 24 bit key from 16 bit hash data
        key = key.substring(16) + key.substring(0, 16) + key.substring(16);
        int padLength = 0;
        while (dataIn.length() % 8 != 0) {
            dataIn += "0";
            padLength++;
        }
        return "[" + padLength + "]" + ISOUtil.hexString(CryptoUtils.encrypt3D(ISOUtil.hex2byte(key), dataIn.getBytes()));
    }

    /**
     *
     * @param dataIn
     * @return
     * @throws Exception
     */
    public static String getEncKey(String dataIn) throws Exception {
        String key = CryptoUtils.diggest(KeyString.toCharArray());
        //Form the 24 bit key from 16 bit hash data
        key = key.substring(16) + key.substring(0, 16) + key.substring(16);
        int padLength = 0;
        /*
         * while(dataIn.length()%8!=0) { dataIn+="0"; padLength++; }
         */
        return ISOUtil.hexString(CryptoUtils.encrypt3D(ISOUtil.hex2byte(key), ISOUtil.hex2byte(dataIn)));
    }

    //Generate a DES key with odd parity

    /**
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] generateDESKey() throws NoSuchAlgorithmException {
        // Create a secure random number generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        // Get 8 random bytes 
        byte[] randomKey = new byte[8];
        sr.nextBytes(randomKey);
        //Make sure the key has odd parity
        for (int i = 0; i < 8; i++) {
            randomKey[i] = (byte) (randomKey[i] | 0x01);
        }
        return randomKey;
    }

    //Generate a 3DES key with odd parity

    /**
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] generate3DESKey() throws NoSuchAlgorithmException {
        byte[] firstKey = generateDESKey();
        byte[] secondKey = generateDESKey();
        byte[] finalKey = new byte[16];
        System.arraycopy(firstKey, 0, finalKey, 0, 8);
        System.arraycopy(secondKey, 0, finalKey, 8, 8);
        return finalKey;
    }

    /**
     * Zero the contents of the specified array.
     *
     * @param pwd the array to zero
     */
    public static void clearArray(byte pwd[]) {
        if (null != pwd) {
            for (int b = 0; b < pwd.length; b++) {
                pwd[b] = 0;
            }
        }
    }

    /**
     * Perform MD5 hashing on the supplied password and return a char array
     * containing the encrypted password as a printable string. The hash is
     * computed on the low 8 bits of each character.
     *
     * @param pwd The password to encrypt
     * @return a character array containing a 32 character long hex encoded MD5
     * hash of the password
     * @throws java.security.NoSuchAlgorithmException
     */
    public static String diggest(char pwd[]) throws NoSuchAlgorithmException {
        if (md == null) {
            md = MessageDigest.getInstance(ALGORITHM);
        }
        md.reset();
        byte pwdb[] = new byte[pwd.length];
        for (int b = 0; b < pwd.length; b++) {
            pwdb[b] = (byte) pwd[b];
        }
        String passwd = ISOUtil.hexString(md.digest(pwdb));
        clearArray(pwdb);
        return passwd;
    }

    /**
     *
     * @param pwd
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String diggest(byte pwd[]) throws NoSuchAlgorithmException {
        if (md == null) {
            md = MessageDigest.getInstance(ALGORITHM);
        }
        md.reset();

        String passwd = ISOUtil.hexString(md.digest(pwd));
        clearArray(pwd);

        return passwd;
    }

    /**
     *
     * @param pwd
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String diggest_SHA1(String pwd) throws NoSuchAlgorithmException {
        if (mds1 == null) {
            mds1 = MessageDigest.getInstance("SHA1");
        }
        mds1.reset();
        mds1.update(pwd.getBytes(), 0, pwd.length());
        String passwd = ISOUtil.hexString(mds1.digest());
        return passwd;
    }

    /**
     *
     * @param keyData
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encrypt(byte[] keyData, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey key = new SecretKeySpec(keyData, "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        //Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(data);

        return cipherText;
    }

    //Triple des encrypt ecb no padding

    /**
     *
     * @param keyData
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encrypt3D(byte[] keyData, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey key = new SecretKeySpec(keyData, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(data);

        return cipherText;
    }

    //Triple des decrypt ecb no padding

    /**
     *
     * @param keyData
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt3D(byte[] keyData, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey key = new SecretKeySpec(keyData, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(data);

        return cipherText;
    }

    //Triple des encrypt ECB PKCS5 Padding

    /**
     *
     * @param keyData
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encrypt3D_PKCS5(byte[] keyData, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey key = new SecretKeySpec(keyData, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        //Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(data);

        return cipherText;
    }

    /**
     *
     * @param keyData
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt3D_PKCS5(byte[] keyData, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey key = new SecretKeySpec(keyData, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        //Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(data);

        return cipherText;
    }

    /**
     *
     * @param key
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encrypt(Key key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(data);

        return cipherText;
    }

    /**
     *
     * @param keyData
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt(byte[] keyData, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey key = new SecretKeySpec(keyData, "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(data);

        return cipherText;
    }

    /**
     *
     * @param key
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt(Key key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(data);

        return cipherText;
    }

    /**
     *
     * @param components
     * @return
     */
    public static byte[] buildKey(byte[][] components) {
        byte[] finalKey = components[0];
        for (int i = 1; i < components.length; i++) {
            for (int j = 0; j < finalKey.length; j++) {
                finalKey[j] = (byte) (finalKey[j] ^ components[i][j]);
            }
        }
        return finalKey;
    }

    /**
     *
     * @param cardNumber
     * @param clearPIN
     * @return
     */
    public static byte[] buildPINBlock(String cardNumber, String clearPIN) {
        byte[] s1 = new byte[16];
        byte[] s2 = new byte[16];
        byte[] pinBlock = new byte[8];

        // construct s1
        s1[0] = (byte) 0x0;    // 0: filled
        s1[1] = (byte) clearPIN.length();    // PIN Len
        for (int i = 0; i < clearPIN.length(); i++) {
            s1[2 + i] = (byte) (clearPIN.charAt(i) - '0');
        }
        for (int i = 2 + clearPIN.length(); i < 16; i++) {
            s1[i] = 0xF;    // filled
        }

        // construct s2
        s2[0] = (byte) 0x0;    // filled
        s2[1] = (byte) 0x0;    // filled
        s2[2] = (byte) 0x0;    // filled
        s2[3] = (byte) 0x0;    // filled
        for (int i = 0; i < 12; i++) {
            //s2[4+i] = (byte)(cardNumber.charAt(3+i) - '0');
            s2[4 + i] = (byte) (cardNumber.charAt(cardNumber.length() - 13 + i) - '0');
        }

        // construct pinBlock
        for (int i = 0; i < 8; i++) {
            pinBlock[i] = (byte) (((s1[2 * i] ^ s2[2 * i]) << 4) | (s1[2 * i + 1] ^ s2[2 * i + 1]));
        }

        return pinBlock;
    }

    /**
     *
     * @param cardNumber
     * @param pinBlock
     * @return
     */
    public static String getClearPIN(String cardNumber, byte[] pinBlock) {
        byte[] s1 = new byte[16];
        byte[] s2 = new byte[16];

        // construct s2
        s2[0] = (byte) 0x0;    // filled
        s2[1] = (byte) 0x0;    // filled
        s2[2] = (byte) 0x0;    // filled
        s2[3] = (byte) 0x0;    // filled
        for (int i = 0; i < 12; i++) {
            s2[4 + i] = (byte) (cardNumber.charAt(3 + i) - '0');
        }

        for (int i = 0; i < 8; i++) {
            byte leftNipple, rightNipple;
            leftNipple = (byte) ((pinBlock[i] & 0xF0) >> 4);
            rightNipple = (byte) (pinBlock[i] & 0x0F);
            s1[2 * i] = (byte) (leftNipple ^ s2[2 * i]);
            s1[2 * i + 1] = (byte) (rightNipple ^ s2[2 * i + 1]);
        }

        if ((s1[0] != 0) || (s1[1] <= 0) || (s1[1] > 15)) {
            throw new RuntimeException("Invalid PIN format");
        }

        for (int i = 2; i < 2 + s1[1]; i++) {
            s1[i] = (byte) ('0' + s1[i]);
        }
        return new String(s1, 2, s1[1]);
    }

    /**
     *
     * @param keyData
     * @param cardNumber
     * @param clearPIN
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] DESencryptClearPIN(byte[] keyData, String cardNumber, String clearPIN) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] clearPINBlock = buildPINBlock(cardNumber, clearPIN);
        return encrypt(keyData, clearPINBlock);
    }

    /**
     *
     * @param key
     * @param cardNumber
     * @param clearPIN
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] DESencryptClearPIN(Key key, String cardNumber, String clearPIN) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] clearPINBlock = buildPINBlock(cardNumber, clearPIN);
        return encrypt(key, clearPINBlock);
    }

    /**
     *
     * @param keyData
     * @param cardNumber
     * @param clearPIN
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] TDESencryptClearPIN(byte[] keyData, String cardNumber, String clearPIN) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] clearPINBlock = buildPINBlock(cardNumber, clearPIN);
        return encrypt3D(keyData, clearPINBlock);
    }

    /**
     *
     * @param cardNo
     * @param encPIN
     * @param ePK
     * @param TDES
     * @return
     */
    public static int validatePIN(String cardNo, byte[] encPIN, byte[] ePK, boolean TDES) {
        byte[] clearPINBlk;
        try {
            if (TDES) {
                clearPINBlk = decrypt3D(ePK, encPIN);
            } else {
                clearPINBlk = decrypt(ePK, encPIN);
            }
        } catch (Exception ex) {
            //handle cipher error
            ex.printStackTrace();
            return 81;
        }

        try {
            getClearPIN(cardNo, clearPINBlk);
        } catch (Exception ex) {
            //handle PIN block error
            ex.printStackTrace();
            return 83;
        }
        return 0;
    }

    /**
     *
     * @param encPIN
     * @param srcKey
     * @param destKey
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] translatePIN(byte[] encPIN, byte[] srcKey, byte[] destKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] clrPIN = CryptoUtils.decrypt3D(srcKey, encPIN);
        return CryptoUtils.encrypt3D(destKey, clrPIN);
    }

    /**
     *
     * @param number
     * @return
     */
    public static int getCheckDigit(String number) { //Luhn check modulus 10
        // follow VISA standard
        char[] digits = number.toCharArray();
        int sum = 0;
        int multiplier = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit;
            try {
                digit = Integer.parseInt(String.valueOf(digits[i]));
            } catch (Exception e) {
                return -1;
            }
            if (multiplier == 1) {
                multiplier = 2;
            } else if (multiplier == 2) {
                multiplier = 1;
            }
            int multiple = digit * multiplier;
            sum = sum + (multiple % 10) + (multiple / 10);
            //System.out.println("sum "+i+"="+sum);
        }
        int chk = 10 - (sum % 10);
        if (chk == 10) {
            chk = 0;
        }
        return chk;
    }

    /**
     *
     * @param txt
     * @param len
     * @param padder
     * @return
     */
    public static String lpad(String txt, int len, String padder) {
        if (txt == null) {
            txt = "";
        }

        while (txt.length() < len) {
            txt = padder + txt;
        }
        return txt;
    }

    /**
     * Calculates the clear PIN Block
     *
     * @param pin as entered by the card holder on the PIN entry device
     * @param pinBlockFormat
     * @param accountNumber (the 12 right-most digits of the account number
     * excluding the check digit)
     * @return The clear PIN Block
     * @throws RuntimeException
     *
     */
    public static byte[] calculatePINBlock(String pin, byte pinBlockFormat, String accountNumber) throws RuntimeException {
        byte[] pinBlock = null;
        accountNumber = accountNumber.substring(accountNumber.length() - 13, accountNumber.length() - 1);

        if (pin.length() > MAX_PIN_LENGTH) {
            throw new RuntimeException("Invalid PIN length: " + pin.length());
        }
        if (accountNumber.length() != 12) {
            throw new RuntimeException("Invalid Account Number: " + accountNumber + ". The length of the account number must be 12 (the 12 right-most digits of the account number excluding the check digit)");
        }
        switch (pinBlockFormat) {
            case FORMAT00: // same as FORMAT01
            case FORMAT01: {
                // Block 1
                String block1 = null;
                byte[] block1ByteArray;
                switch (pin.length()) {
                    // pin length then pad with 'F'
                    case 4:
                        block1 = "04" + pin + "FFFFFFFFFF";
                        break;
                    case 5:
                        block1 = "05" + pin + "FFFFFFFFF";
                        break;
                    case 6:
                        block1 = "06" + pin + "FFFFFFFF";
                        break;
                    default:
                        throw new RuntimeException("Unsupported PIN Length: "
                                + pin.length());
                }
                block1ByteArray = ISOUtil.hex2byte(block1);
                // Block 2
                String block2;
                byte[] block2ByteArray = null;
                block2 = "0000" + accountNumber;
                block2ByteArray = ISOUtil.hex2byte(block2);
                // pinBlock
                pinBlock = ISOUtil.xor(block1ByteArray, block2ByteArray);
            }
            ;
            break;
            case FORMAT03: {
                if (pin.length() < 4 || pin.length() > 12) {
                    throw new RuntimeException("Unsupported PIN Length: "
                            + pin.length());
                }
                pinBlock = ISOUtil.hex2byte(
                        pin + "FFFFFFFFFFFFFFFF".substring(pin.length(), 16));
            }
            break;
            default:
                throw new RuntimeException("Unsupported PIN format: " + pinBlockFormat);
        }
        return pinBlock;
    }

    /**
     * Calculates the clear pin (as entered by card holder on the pin entry
     * device) givin the clear PIN block
     *
     * @param pinBlock clear PIN Block
     * @param pinBlockFormat
     * @param accountNumber
     * @return the pin
     * @throws RuntimeException
     */
    public static String calculatePIN(byte[] pinBlock, byte pinBlockFormat, String accountNumber) throws RuntimeException {
        String pin = null;
        int pinLength;
        accountNumber = accountNumber.substring(accountNumber.length() - 13, accountNumber.length() - 1);

        if (accountNumber.length() != 12) {
            throw new RuntimeException("Invalid Account Number: " + accountNumber + ". The length of the account number must be 12 (the 12 right-most digits of the account number excluding the check digit)");
        }
        switch (pinBlockFormat) {
            case FORMAT00: // same as format 01
            case FORMAT01: {
                // Block 2
                String block2;
                block2 = "0000" + accountNumber;
                byte[] block2ByteArray = ISOUtil.hex2byte(block2);
                // get Block1
                byte[] block1ByteArray = ISOUtil.xor(pinBlock, block2ByteArray);
                pinLength = Math.abs(block1ByteArray[0]);
                if (pinLength > MAX_PIN_LENGTH) {
                    throw new RuntimeException("PIN Block Error");
                }
                // get pin
                String pinBlockHexString = ISOUtil.hexString(block1ByteArray);
                pin = pinBlockHexString.substring(2, pinLength
                        + 2);
                String pad = pinBlockHexString.substring(pinLength + 2);
                pad = pad.toUpperCase();
                int i = pad.length();
                while (--i >= 0) {
                    if (pad.charAt(i) != 'F') {
                        throw new RuntimeException("PIN Block Error");
                    }
                }
            }
            break;
            case FORMAT03: {
                String block1 = ISOUtil.hexString(pinBlock);
                int len = block1.indexOf('F');
                if (len == -1) {
                    len = 12;
                }
                int i = block1.length();
                pin = block1.substring(0, len);

                while (--i >= len) {
                    if (block1.charAt(i) != 'F') {
                        throw new RuntimeException("PIN Block Error");
                    }
                }
                while (--i >= 0) {
                    if (pin.charAt(i) >= 'A') {
                        throw new RuntimeException("PIN Block Error");
                    }
                }

                if (pin.length() < 4 || pin.length() > 12) {
                    throw new RuntimeException("Unsupported PIN Length: "
                            + pin.length());
                }
            }
            break;
            default:
                throw new RuntimeException("Unsupported PIN Block format: " + pinBlockFormat);
        }
        return pin;
    }

    /*
     * public static void storeSessionKey (String eKIRvx) {
     *
     * String KIR = GatewayProperties.getProperty("KIR"); byte[] KS_Spec = new
     * byte[17]; //new byte[]{0x0A,0x00} double length KIS, double length PPK
     * //EracomPHW.receiveBNVInterchangeSessionKey(KIR, eKIRvx, KS_Spec);
     * EracomPHW.receiveInterchangeSessionKey(KIR,EracomPHW.KEY_DOUBLE_LEN_PPK,eKIRvx,KS_Spec);
     *
     * //System.out.println("Session key: "+ISOUtil.hexString(KS_Spec));
     * DBAccessor.storeZPK(ISOUtil.hexString(KS_Spec).substring(2)); }
     *
     * public static void translatePIN (ISOMsg req, int direction) throws
     * Exception {
     *
     * if (req.getString(52)==null) return; if ((direction != ISOMsg.INCOMING)
     * &&(direction != ISOMsg.OUTGOING)) throw new Exception("Invalid
     * direction");
     *
     * //From ACQ to gateway String AWK = GatewayProperties.getProperty("AWK");
     * //From Gateway to ISS String IWK = GatewayProperties.getProperty("IWK");
     * //From Gateway to BNV String ZPK = DBAccessor.getZPK();
     *
     * byte[] newPINBlock = new byte[8]; byte[] PF = EracomPHW.PIN_FORMAT_ANSI;
     * String PINBlock = req.getString(52); String cardNo =
     * TranxUtils.getCardNumber(req); if (direction==ISOMsg.OUTGOING) {
     * EracomPHW.TranslatePIN(PINBlock, AWK, PF, cardNo, PF, ZPK, newPINBlock);
     * } if (direction==ISOMsg.INCOMING) { EracomPHW.TranslatePIN(PINBlock, ZPK,
     * PF, cardNo, PF, IWK, newPINBlock); req.set(52, newPINBlock); } }
     */

    /**
     *
     * @param args
     * @throws Exception
     */

    public static void main(String args[]) throws Exception {
        if ((args != null) && (args.length > 0)) {
            System.out.println(CryptoUtils.getEncData(args[0]));
        }

        //System.out.println(CryptoUtils.getEncKey("C3E8 D4B4 E81B 50CF 2872 AC14 E230 2721C3E8 D4B4 E81B 50CF".replaceAll(" ","")));
        //System.out.println(CryptoUtils.getClearPIN("9704290600007691",CryptoUtils.decrypt3D(ISOUtil.hex2byte("C3E8 D4B4 E81B 50CF 2872 AC14 E230 2721C3E8 D4B4 E81B 50CF".replaceAll(" ","")), ISOUtil.hex2byte("503BEB287EBB2123"))));
        //System.out.println(ISOUtil.hexString(CryptoUtils.decrypt3D(ISOUtil.hex2byte("C3E8 D4B4 E81B 50CF 2872 AC14 E230 2721C3E8 D4B4 E81B 50CF".replaceAll(" ","")),ISOUtil.hex2byte("0B6177DBACB9DBDFB5891210582FF0A00B6177DBACB9DBDF"))));
        /*
         * //SMLC
         * System.out.println(CryptoUtils.getClearPIN("6201630096184617",CryptoUtils.decrypt3D(ISOUtil.hex2byte("D9A3
         * 572D 93C7 6303 0B57 7D79 0FC7 5F9B D9A3 572D 93C7 6303".replaceAll("
         * ","")), ISOUtil.hex2byte("7C98DA0F805FF31B"))));
         *
         * //VAB
         *
         * System.out.println(CryptoUtils.getClearPIN("6201630096184617",CryptoUtils.decrypt3D(ISOUtil.hex2byte("A137
         * 9149 8BA3 DF75 BD45 01D1 F993 BB8B A137 9149 8BA3 DF75".replaceAll("
         * ","")), ISOUtil.hex2byte("7C98DA0F805FF31B"))));          *
         * //SCB - IVB
         * System.out.println(CryptoUtils.getClearPIN("6201630096184617",CryptoUtils.decrypt3D(ISOUtil.hex2byte("860B
         * F183 A21A 7C32 F41F 89B9 546D 3804 860B F183 A21A 7C32".replaceAll("
         * ","")), ISOUtil.hex2byte("7C98DA0F805FF31B"))));
         */
        //503BEB287EBB2123
        //System.out.println(CryptoUtils.getEncData("prepaid"));
        /*
         * //boolean isNumber = Pattern.matches("\\d*","0000");
         * //System.out.println(isNumber); String LMK =
         * CryptoUtils.diggest(CryptoUtils.KeyString.toCharArray()); //Form the
         * 24 bit key from 16 bit hash data LMK = LMK.substring(16) +
         * LMK.substring(0,16) + LMK.substring(16);
         *
         * System.out.println("Test:
         * "+ISOUtil.hexString(CryptoUtils.encrypt3D_PKCS5(ISOUtil.hex2byte("8E05E1F839BAFA16CAA5974129011FD98E05E1F839BAFA16"),
         * "1234567".getBytes())));
         *
         * String encKey = "C238F43D2B3812E77691342DBE229F94C238F43D2B3812E7";
         * System.out.println("Clear key
         * "+ISOUtil.hexString(CryptoUtils.decrypt3D(ISOUtil.hex2byte(LMK),ISOUtil.hex2byte(encKey))));
         *
         * String clearPIN =
         * CryptoUtils.getClearPIN("0000023807294717",CryptoUtils.decrypt3D(CryptoUtils.decrypt3D(ISOUtil.hex2byte(LMK),ISOUtil.hex2byte(encKey)),ISOUtil.hex2byte("13F0C679A1EE341A")));
         *
         *
         * System.out.println("SVB PIN "+ clearPIN);
         *
         *
         * System.out.println(CryptoUtils.diggest("123456".toCharArray()));
         *
         * System.out.println("VTL key" +
         * CryptoUtils.getEncKey("F1EAD93410A815765D3E7A45A24380F8F1EAD93410A81576"));
         * System.out.println("VTL PIN "+
         * ISOUtil.hexString(CryptoUtils.decrypt3D(ISOUtil.hex2byte("F1EAD93410A815765D3E7A45A24380F8F1EAD93410A81576"),ISOUtil.hex2byte("A3BCA388D9B0D591"))));
         * System.out.println("VTL PIN "+ new
         * String(CryptoUtils.decrypt3D_PKCS5(ISOUtil.hex2byte("F1EAD93410A815765D3E7A45A24380F8F1EAD93410A81576"),ISOUtil.hex2byte("A3BCA388D9B0D591"))));
         */
 /*
         * System.out.println("TCB: "+CryptoUtils.getEncKey("E830 093C 144D B7B8
         * F324 78D2 E10A 7890E830 093C 144D B7B8".replaceAll(" ","")));
         * System.out.println("EIB, SVB : "+CryptoUtils.getEncKey("7235 CFDE
         * 06BD 96B8 D481 1B65 5C36 594D7235 CFDE 06BD 96B8".replaceAll("
         * ",""))); System.out.println("EIB ISS : "+CryptoUtils.getEncKey("1AC7
         * 92AE 643E 6716 EAF1 8A29 0B4C 98941AC7 92AE 643E 6716".replaceAll("
         * ",""))); System.out.println("VPB, VIB, IVB :
         * "+CryptoUtils.getEncKey("860B F183 A21A 7C32 F41F 89B9 546D 3804860B
         * F183 A21A 7C32".replaceAll(" ",""))); byte [] zpk =
         * ISOUtil.hex2byte("7235 CFDE 06BD 96B8 D481 1B65 5C36 594D7235 CFDE
         * 06BD 96B8".replaceAll(" ","")); /* for (int
         * i=0;i<randomKey.length;i++) { randomKey[i] =
         * (byte)(randomKey[i]|randomKey2[i]); }
         */
 /*
         * System.out.println("MSB key" +
         * CryptoUtils.getEncKey(ISOUtil.hexString(zpk)));
         *
         * System.out.println("KVC
         * "+ISOUtil.hexString(CryptoUtils.encrypt3D(zpk,ISOUtil.hex2byte("0000000000000000")
         * )));
         *
         * byte[] newClrPIN =
         * CryptoUtils.decrypt3D(zpk,ISOUtil.hex2byte("00A3DE24D289A241"));
         *
         * System.out.println("New clear PIN: " +
         * CryptoUtils.calculatePIN(newClrPIN,CryptoUtils.FORMAT01,"9704270000000000"));
         *
         * //System.out.println(CryptoUtils.diggest("000000".toCharArray()));
         *
         *
         *
         * //System.out.println(CryptoUtils.getEncData("smlrp2510"));
         *
         *
         *
         * //byte[] key = CryptoUtils.generate3DESKey();
         * //System.out.println("ZPK: "+ISOUtil.hexString(key));
         * //System.out.println("KVC
         * "+ISOUtil.hexString(CryptoUtils.encrypt3D(ISOUtil.hex2byte("513DAB171FE11D9DBF0B31E99F65755D513DAB171FE11D9D"),ISOUtil.hex2byte("0000000000000000"))));
         * //System.out.println("ENC KEY:
         * "+CryptoUtils.getEncKey("513DAB171FE11D9DBF0B31E99F65755D513DAB171FE11D9D"));
         * //System.out.println("GPB enc key:
         * "+getEncKey("3BB9C10BDDCD21570535B50F0FC3FD073BB9C10BDDCD2157"));
         *
         *
         *
         *
         *
         * /*
         * String LMK =
         * CryptoUtils.diggest(CryptoUtils.KeyString.toCharArray()); //Form the
         * 24 bit key from 16 bit hash data LMK = LMK.substring(16) +
         * LMK.substring(0,16) + LMK.substring(16); System.out.println("MB enc
         * key"+CryptoUtils.getEncKey("43B1BDF30D3735BFEBCBB9054DB7E1F143B1BDF30D3735BF"));
         * System.out.println(CryptoUtils.getEncData("pos_test"));
         *
         * //Production
         * //System.out.println(CryptoUtils.getEncData("smls0610x")); String
         * encKey = "B101090FCF8DF7770601159308CB6DA3B101090FCF8DF777";
         * System.out.println("Clear key
         * "+ISOUtil.hexString(CryptoUtils.decrypt3D(ISOUtil.hex2byte(LMK),ISOUtil.hex2byte(encKey))));
         *
         * String clearPIN =
         * CryptoUtils.getClearPIN("6201600000000613",CryptoUtils.decrypt3D(CryptoUtils.decrypt3D(ISOUtil.hex2byte(LMK),ISOUtil.hex2byte(encKey)),ISOUtil.hex2byte("3CD79222A75B3430")));
         * System.out.println("Clear pin: "+clearPIN);
         */
 /*
         * String vabkey = "A13791498BA3DF75BD4501D1F993BB8BA13791498BA3DF75";
         * String vcbkey = "860B F183 A21A 7C32 F41F 89B9 546D 3804 860B F183
         * A21A 7C32".replaceAll(" ",""); String testkey =
         * "7235CFDE06BD96B8D4811B655C36594D7235CFDE06BD96B8";
         *
         * byte[] clrPIN = CryptoUtils.decrypt3D(ISOUtil.hex2byte(vcbkey),
         * ISOUtil.hex2byte("79B170133C68704B")); System.out.println("Clear PIN
         * "+CryptoUtils.getClearPIN("6868681924838019", clrPIN));
         */
 /*
         * System.out.println("Test enc key:
         * "+CryptoUtils.getEncKey("7235CFDE06BD96B8D4811B655C36594D7235CFDE06BD96B8"));
         * //String KVC= //String key =
         * ISOUtil.hexString(CryptoUtils.generate3DESKey()); //key = key +
         * key.substring(0,16); //System.out.println(key);
         * System.out.println("MB enc ZPK:
         * "+CryptoUtils.getEncKey("894FFDAF0B394343A7A53BCB4F634D83894FFDAF0B394343"));
         *
         * System.out.println("BNV enc ZPK:
         * "+CryptoUtils.getEncKey("C451D07357732CA1D3467A2A29378045C451D07357732CA1"));
         * System.out.println("BNV enc key:
         * "+CryptoUtils.getEncKey("77E128B7B2B26944A921F3FCF62EB8FC77E128B7B2B26944"));
         * System.out.println("KVC
         * "+ISOUtil.hexString(CryptoUtils.encrypt3D(ISOUtil.hex2byte("860BF183A21A7C32F41F89B9546D3804860BF183A21A7C32"),ISOUtil.hex2byte("0000000000000000"))));
         * System.out.println("KVC
         * "+ISOUtil.hexString(CryptoUtils.encrypt3D(ISOUtil.hex2byte("F1EAD93410A815765D3E7A45A24380F8F1EAD93410A81576"),ISOUtil.hex2byte("0000000000000000"))));
         * //System.out.println(CryptoUtils.getEncKey(vcbkey.trim().replaceAll("
         * ","")));
         */
        //F1EAD93410A81576
        //byte [] comp1 = ISOUtil.hex2byte("AD4A 8C58 4398 3773 C10B 2AC1 BF49 549DAD4A 8C58 4398 3773".replaceAll(" ",""));
        byte[] comp1 = ISOUtil.hex2byte("EC086197E3C8CB7AF2860DF8C79B57AEEC086197E3C8CB7A");
        byte[] comp2 = ISOUtil.hex2byte("7235CFDE06BD96B8D4811B655C36594D7235CFDE06BD96B8".replaceAll(" ", ""));

        byte[][] comps = new byte[][]{comp1, comp2};

        //System.out.println("Clear ZPK BNV: "+ISOUtil.hexString(CryptoUtils.decrypt3D(ISOUtil.hex2byte("77E128B7B2B26944A921F3FCF62EB8FC77E128B7B2B26944"),ISOUtil.hex2byte("975C55603B97285F267FF2B7370B2B78975C55603B97285F"))));
        System.out.println("Final key: " + ISOUtil.hexString(CryptoUtils.buildKey(comps)));

        //System.out.println("Final KVC "+ISOUtil.hexString(CryptoUtils.encrypt3D(ISOUtil.hex2byte("C3E8 D4B4 E81B 50CF 2872 AC14 E230 2721C3E8 D4B4 E81B 50CF".replaceAll(" ","")),ISOUtil.hex2byte("0000000000000000"))));
        //System.out.println("Final KVC "+ISOUtil.hexString(CryptoUtils.encrypt3D(CryptoUtils.buildKey(comps),CryptoUtils.buildKey(comps))));
        //System.out.println(CryptoUtils.getEncData("smlswitch"));
        //System.out.println(CryptoUtils.getEncData("smls"));
        System.out.println("Clear ZPK: " + ISOUtil.hexString(CryptoUtils.decrypt3D(CryptoUtils.buildKey(comps), ISOUtil.hex2byte("60C07C2CC0D4443E6120345208738DF860C07C2CC0D4443E"))));
        System.out.println("Enc ZPK: " + CryptoUtils.getEncKey(ISOUtil.hexString(CryptoUtils.decrypt3D(CryptoUtils.buildKey(comps), ISOUtil.hex2byte("486988DB79CE569BA6A6FD6DD987F8AB486988DB79CE569B")))));

        //System.out.println("Enc key: "+CryptoUtils.getEncKey("1E6B2E6E4FA8D56AC0FED3CA475D26241E6B2E6E4FA8D56A"));
        System.out.println(CryptoUtils.getClearPIN("9704140390015679", CryptoUtils.decrypt3D(ISOUtil.hex2byte("1E6B2E6E4FA8D56AC0FED3CA475D26241E6B2E6E4FA8D56A"), ISOUtil.hex2byte("51EC5C9B396E4A7B"))));
        //System.out.println(CryptoUtils.getClearPIN("1809065001576655",CryptoUtils.decrypt(ISOUtil.hex2byte("0000000000000000"), ISOUtil.hex2byte("42847E6C4C6239A3"))));

        /*
         * System.out.println(CryptoUtils.getEncData("mfc")); String key =
         * CryptoUtils.diggest(CryptoUtils.KeyString.toCharArray()); String
         * UID="[5]1D7CF3C22A13790D"; //Form the 24 bit key from 16 bit hash
         * data key = key.substring(16) + key.substring(0,16) +
         * key.substring(16); String clearUID = new
         * String(CryptoUtils.decrypt3D(ISOUtil.hex2byte(key),
         * ISOUtil.hex2byte(UID.substring(3)))); clearUID =
         * clearUID.substring(0,clearUID.length()-Integer.parseInt(UID.substring(1,2)));
         * System.out.println(clearUID);
         */
 /*
         * byte[] PINblk =
         * CryptoUtils.buildPINBlock("9704230111000272","111111");
         * System.out.println(ISOUtil.hexString(CryptoUtils.encrypt3D(
         * ISOUtil.hex2byte("7235CFDE06BD96B8D4811B655C36594D7235CFDE06BD96B8"),
         * PINblk)));
         */
 /*
         * System.out.println(CryptoUtils.diggest_SHA1("00000000000097049983180100358384984336633F1EAD93410A815765D3E7A45A24380F8"));
         * System.out.println(CryptoUtils.getCheckDigit("970416020230002"));
         * System.out.println(ISOUtil.hexString(CryptoUtils.encrypt3D(ISOUtil.hex2byte("7235CFDE06BD96B8D4811B655C36594D7235CFDE06BD96B8"),ISOUtil.hex2byte("0000000000000000"))));
         * System.out.println(CryptoUtils.getClearPIN("8899880102479104",CryptoUtils.decrypt3D(ISOUtil.hex2byte("7235CFDE06BD96B8D4811B655C36594D7235CFDE06BD96B8"),ISOUtil.hex2byte("4F95AC18B7CC20ED"))));
         * /* System.out.println(CryptoUtils.getEncData("prepaid"));
         * System.out.println(CryptoUtils.getEncKey("F1EAD93410A815765D3E7A45A24380F8F1EAD93410A81576"));
         * System.out.println(CryptoUtils.diggest_SHA1("F1EAD93410A815765D3E7A45A24380F8F1EAD934127E87118952FF331FC3EB09100C034EE0A8157627E87118952FF331FC3EB09100C034EEF1EAD93427E87118952FF331FC3EB09100C034EE10A815765D3E7A45A24380F8F1EAD93410A81576"));
         */
        System.out.println("UID BNV: " + CryptoUtils.getEncData("bnv"));
        System.out.println("PWD BNV: " + CryptoUtils.getEncData("smlrp2510"));

        System.out.println("UID VNBC: " + CryptoUtils.getEncData("vnbc"));
        System.out.println("PWD VNBC: " + CryptoUtils.getEncData("vnbc"));

        System.out.println("UID DBTEST: " + CryptoUtils.getEncData("smlst"));
        System.out.println("PWD DBTEST: " + CryptoUtils.getEncData("sml2011"));

        System.out.println("UID VNBC-IBFT: " + CryptoUtils.getEncData("vnbcibt"));
        System.out.println("PWD VNBC-IBFT: " + CryptoUtils.getEncData("sml2013ibt"));

        System.out.println("UID IPS_7: " + CryptoUtils.getEncData("ips"));
        System.out.println("PWD IPS_7: " + CryptoUtils.getEncData("s25m10l"));

        System.out.println("UID BNV_7: " + CryptoUtils.getEncData("bnv"));
        System.out.println("PWD BNV_7: " + CryptoUtils.getEncData("b10n25v"));

        System.out.println("UID DFS_43: " + CryptoUtils.getEncData("dfs"));
        System.out.println("PWD DFS_43: " + CryptoUtils.getEncData("dfs2510"));

        System.out.println("UID FE_43: " + CryptoUtils.getEncData("smlswitch"));
        System.out.println("PWD FE_43: " + CryptoUtils.getEncData("dev25fe10"));

        System.out.println("UID IPS_43: " + CryptoUtils.getEncData("ips"));
        System.out.println("PWD IPS_43: " + CryptoUtils.getEncData("dev25sml10ips"));

        System.out.println("UID IBFT_43: " + CryptoUtils.getEncData("smlibt"));
        System.out.println("PWD IBFT_43: " + CryptoUtils.getEncData("dev25ibft10"));

        System.out.println("UID BNV_43: " + CryptoUtils.getEncData("bnv"));
        System.out.println("PWD BNV_43: " + CryptoUtils.getEncData("dev25bnv10"));

        System.out.println("UID IST_43: " + CryptoUtils.getEncData("istswitch"));
        System.out.println("PWD IST_43: " + CryptoUtils.getEncData("dev25ist10"));

        System.out.println("UID IST_41: " + CryptoUtils.getEncData("ips_ist"));
        System.out.println("PWD IST_41: " + CryptoUtils.getEncData("ipsist*2510"));
//        String user = "[6]54FB09C7A31E3B08BC424225EBCE4DF0";
//        String pass = "[6]2D628DE8B0DA34AA2CF70B5652107007";

//        String user = "[7]A32D17825134E4469DF72E14F57ACEF1";
//        String pass = "[7]BDD32BFD0BBA5DA767A6B45978706888";
//        String user = "[4]288ADA3284D1F102";
//        String pass = "[7]F510264E4171C5882EE742F7D842B391";
        //[7]A32D17825134E4469DF72E14F57ACEF1
        //[4]A7A738F595B07E07
        //String pass = "[1]ED174AF720039550";
        String pass = "[7]A32D17825134E4469DF72E14F57ACEF1";

        String key = CryptoUtils.diggest(CryptoUtils.KeyString.toCharArray());
        //Form the 24 bit key from 16 bit hash data
        key = key.substring(16) + key.substring(0, 16) + key.substring(16);

        //get the clear UID by decrypting the data and remove the paddings
//        String clearUID = new String(CryptoUtils.decrypt3D(ISOUtil.hex2byte(key), ISOUtil.hex2byte(user.substring(3))));
//        clearUID = clearUID.substring(0, clearUID.length() - Integer.parseInt(user.substring(1, 2)));
        //get the clear PWD by decrypting the data and remove the paddings
        String clearPWD = new String(CryptoUtils.decrypt3D(ISOUtil.hex2byte(key), ISOUtil.hex2byte(pass.substring(3))));
        clearPWD = clearPWD.substring(0, clearPWD.length() - Integer.parseInt(pass.substring(1, 2)));

//        System.out.println(clearUID);
        System.out.println(clearPWD);

//        String ZPK = "AE24E815EC053C3E214EEDD898B27165";
//        System.out.println("NAB: " + MacUtil.getClearKey(ZPK));
    }
/**
 * EncryptionTripleDES
 * 
 * @param payloadEncryption
 * @return
 * @throws NoSuchAlgorithmException
 * @throws NoSuchPaddingException
 * @throws InvalidKeyException
 * @throws InvalidAlgorithmParameterException
 * @throws IllegalBlockSizeException
 * @throws BadPaddingException 
 */
    public static String EncryptionTripleDES(String payloadEncryption) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        byte[] secretKey = TripleDESSecretKey.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "DESede");
        byte[] iv = TripleDESVectorKey.getBytes();
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher encryptCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
        byte[] secretMessagesBytes = payloadEncryption.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessagesBytes);
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);

        /*
        Cipher decryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        */
        return encodedMessage;
    }
}
