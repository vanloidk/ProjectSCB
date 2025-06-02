/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.macs.ISO9797Alg3Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.jpos.iso.ISOUtil;

/**
 *
 * @author ducnn
 */
public class MAC_Util {

    /**
     *
     * @param input
     * @param key
     * @return
     */
    public static byte[] getDigest(String input, String key) {
        try {
            byte[] bkey = ISOUtil.hex2byte(key);
            byte[] data = input.getBytes();
            byte[] sig = new byte[8];
            try {

                ISO9797Alg3Mac mac = new ISO9797Alg3Mac(new DESEngine());

                mac.init(new KeyParameter(ISOUtil.hex2byte(key)));

                data = paddingISO9797Method1(data);
                mac.update(data, 0, data.length);

                mac.doFinal(sig, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return sig;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param d
     * @return
     */
    public static byte[] paddingISO9797Method2(byte[] d) {
        //Padding - first byte 0x80 rest 0x00
        byte[] t = new byte[d.length - d.length % 8 + 8];
        System.arraycopy(d, 0, t, 0, d.length);
        for (int i = d.length; i < t.length; i++) {
            t[i] = (byte) (i == d.length ? 0x80 : 0x00);
        }
        d = t;
        return d;
    }

    /**
     *
     * @param d
     * @return
     */
    public static byte[] paddingISO9797Method1(byte[] d) {
        //Padding - first byte 0x80 rest 0x00

        //ducnn start
        byte[] t;
        if (d.length % 8 == 0) {
            t = new byte[d.length];
        } else {
            t = new byte[d.length - d.length % 8 + 8];
        }

        System.arraycopy(d, 0, t, 0, d.length);
        for (int i = d.length; i < t.length; i++) {
            t[i] = (byte) (i == d.length ? 0x00 : 0x00);
        }
        d = t;
        return d;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean arraysEqual(
            byte[] a,
            byte[] b) {
        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param pRevData
     * @return
     */
    public static String getHumanFormatFromByte(byte[] pRevData) {
        String rs = "";
        try {
            for (int i = 0; i < pRevData.length; i++) {
                if (checkByteHuman(pRevData[i])) {
                    rs = rs + new String(new byte[]{pRevData[i]});
                } else {
                    rs = rs + "(" + String.valueOf((int) pRevData[i]) + ")";
                }
            }
        } catch (Exception ex) {
            rs = "ERROR TO GET HUMAN FORMAT";
        }

        return rs;
    }

    /**
     *
     * @param pByte
     * @return
     */
    public static boolean checkByteHuman(byte pByte) {
        //true is ascii false is byte
        int byteInInt = (int) pByte;
        if ((byteInInt < 32) || (byteInInt > 126)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param encryptKey
     * @return
     */
    public static String getClearKey(String encryptKey) {

        try {
            String LMK = CryptoUtils.diggest(CryptoUtils.KeyString.toCharArray());
            LMK = LMK.substring(16) + LMK.substring(0, 16) + LMK.substring(16);
            return ISOUtil.hexString(CryptoUtils.decrypt3D(ISOUtil.hex2byte(LMK), ISOUtil.hex2byte(encryptKey)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]) {

        String data = "021097041601024791044300000000000000000111135152532913601197041607418014000000010000000000000017049704280900011000";
        String clear_component = getClearKey("AE24E815EC053C3E214EEDD898B27165");

        System.out.println(clear_component);

        byte[] MAC = MAC_Util.getDigest(data, clear_component);
        //String str = String.
        //String mac_str = getHumanFormatFromByte(MAC);
        System.out.println(ISOUtil.hexString(MAC));
    }
}
