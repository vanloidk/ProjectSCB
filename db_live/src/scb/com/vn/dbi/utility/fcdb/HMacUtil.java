package scb.com.vn.dbi.utility.fcdb;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author system
 */
public final class HMacUtil {

    /**
     *
     * @param p_key
     * @param p_data
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] p_key, byte[] p_data) throws Exception {
        SecretKeySpec l_key = new SecretKeySpec(p_key, 0, p_key.length, "HMACSHA1");
        Mac l_mac = Mac.getInstance("HMACSHA1");
        l_mac.init(l_key);
        byte[] l_result = l_mac.doFinal(p_data);
        return l_result;
    }

    /**
     *
     * @param p_byte1
     * @param p_byte2
     * @return
     */
    public static byte[] mergeArray(byte[] p_byte1, byte[] p_byte2) {
        int l_len1 = 0;
        int l_len2 = 0;
        if (p_byte1 != null) {
            l_len1 = p_byte1.length;
        }
        if (p_byte2 != null) {
            l_len2 = p_byte2.length;
        }
        int l_len = l_len1 + l_len2;
        byte[] l_bytes = new byte[l_len];
        for (int l_i = 0; l_i < l_len1; l_i++) {
            l_bytes[l_i] = p_byte1[l_i];
        }
        for (int l_i = 0; l_i < l_len2; l_i++) {
            l_bytes[(l_len1 + l_i)] = p_byte2[l_i];
        }
        return l_bytes;
    }
}
