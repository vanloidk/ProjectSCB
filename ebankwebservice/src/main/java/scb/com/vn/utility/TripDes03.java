/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TripDes03 {

    //Algorithm: Triple Des
    private static final String CRYPT_ALGORITHM = "DESede";
    //Block cipher mode: Cipher Block Chaining (CBC), Padding: Zeros
    private static final String PADDING = "DESede/CBC/NoPadding";
    private static final String CHAR_ENCODING = "UTF-8";
    //Initialization vector: 8 bytes with empty
    private static final byte[] MY_IV = "SCB@2022".getBytes(); //"00000000".getBytes();//8-byte
    
    public static void main(String[] args) throws Exception {
        //10/2706; 4895170970496338
        String cardno_encrypt = "_____vvvDD_@VVx^%@";
        System.out.println("Data begin cardno: " + cardno_encrypt);
        String encrypt = encrypt(cardno_encrypt, "9mng65vscb4lxn9scbbf981m");
        System.out.println("Data decrypt cardno: " + encrypt);
        String rp = decrypt("C65644D393C2F4B5", "9mng65vscb4lxn9scbbf981m");
        
        String cardNumber = TripleDESCard.decrypt("DF51DF4AADF263373D441523DF0ED0A7");
        String ccv = TripleDESCard.decrypt("53FBFCF51ACACA85");
        
        System.out.println(rp);
        String expiredate_encrypt = "10/2706";
        System.out.println("begin expiredate_encrypt: " + expiredate_encrypt);
        String encryptexpiredate_encrypt = encrypt(expiredate_encrypt, "9mng65vscb4lxn9scbbf981m");
        System.out.println("Data encryptexpiredate: " + encryptexpiredate_encrypt);
        rp = decrypt(encryptexpiredate_encrypt, "9mng65vscb4lxn9scbbf981m");
        System.out.println(rp);
    }

    public static String encrypt(String text, String secretKey) throws Exception {
        if (text == null) {
            return null;
        }
        String retVal = null;
        try {
            //Key: 24 bytes in length
            final byte[] MY_KEY = secretKey.getBytes();//24-byte
            final SecretKeySpec secretKeySpec = new SecretKeySpec(MY_KEY, CRYPT_ALGORITHM);
            final IvParameterSpec iv = new IvParameterSpec(MY_IV);
            final Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            final byte[] plainTextBytes = text.getBytes(CHAR_ENCODING);
            //==============
            int nguyen = plainTextBytes.length / 8;
            int du = plainTextBytes.length % 8;
            final byte[] encrypted = cipher.doFinal(du > 0 ? Arrays.copyOf(plainTextBytes, (nguyen + 1) * 8) : plainTextBytes);
            //================
            //final byte[] encrypted = cipher.doFinal(text.getBytes(CHAR_ENCODING));
            retVal = new String(HexKey.encodeHex(encrypted));
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new Exception(e.getMessage());
        }
        return retVal;
    }

    public static String decrypt(String text, String secretKey) throws Exception {
        if (text == null) {
            return null;
        }
        String retVal = null;
        try {
            final byte[] MY_KEY = secretKey.getBytes();//24-byte
            final SecretKeySpec secretKeySpec = new SecretKeySpec(MY_KEY, CRYPT_ALGORITHM);
            final IvParameterSpec iv = new IvParameterSpec(MY_IV);
            final Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            final byte[] decrypted = cipher.doFinal(HexKey.decodeHex(text.toCharArray()));
            retVal = new String(decrypted, CHAR_ENCODING).trim();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return retVal;
    }

    public static class HexKey {

        /**
         *
         * @param data a byte[] to convert to Hex characters
         *
         * @return A char[] containing hexadecimal characters
         *
         *
         */
        public static char[] encodeHex(byte[] data) {
            final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            int l = data.length;
            char[] out = new char[l << 1];
            // two characters form the hex value.
            for (int i = 0, j = 0; i < l; i++) {
                out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
                out[j++] = DIGITS[0x0F & data[i]];
            }
            return out;
        }

        /**
         *
         * Converts an array of characters representing hexadecimal values into
         * an array of bytes of those same values. The returned array will be
         * half the length of the passed array, as it takes two characters to
         * represent any given byte. An exception is thrown if the passed char
         * array has an odd number of elements. Portion of Apache Software
         * Foundation
         *
         * @param data An array of characters containing hexadecimal digits
         * @return A byte array containing binary data decoded from the supplied
         * char array.
         * @throws Exception Thrown if an odd number or illegal of characters is
         * supplied
         *
         *
         */
        public static byte[] decodeHex(char[] data) throws Exception {
            int len = data.length;
            if ((len & 0x01) != 0) {
                throw new Exception("Odd number of characters.");
            }
            byte[] out = new byte[len >> 1];
            // two characters form the hex value.
            for (int i = 0, j = 0; j < len; i++) {
                int f = toDigit(data[j], j) << 4;
                j++;
                f = f | toDigit(data[j], j);
                j++;
                out[i] = (byte) (f & 0xFF);
            }
            return out;
        }

        /**
         * Converts a hexadecimal character to an integer. Portion of Apache
         * Software Foundation
         *
         * @param ch A character to convert to an integer digit
         * @param index The index of the character in the source
         * @return An integer
         * @throws Exception Thrown if ch is an illegal hex character
         */
        private static int toDigit(char ch, int index) throws Exception {
            int digit = Character.digit(ch, 16);
            if (digit == -1) {
                throw new Exception("Illegal hexadecimal character " + ch + " at index " + index);
            }
            return digit;
        }

    }
}
