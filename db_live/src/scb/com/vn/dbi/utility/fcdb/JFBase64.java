package scb.com.vn.dbi.utility.fcdb;

/**
 *
 * @author system
 */
public final class JFBase64 {

    private static final char[] intToBase64 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    private static final char[] intToAltBase64 = {'!', '"', '#', '$', '%', '&', '\'', '(', ')', ',', '-', '.', ':', ';', '<', '>', '@', '[', ']', '^', '`', '_', '{', '|', '}', '~', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '?'};

    private static final byte[] base64ToInt = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    private static final byte[] altBase64ToInt = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, -1, 62, 9, 10, 11, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 12, 13, 14, -1, 15, 63, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1, 18, 19, 21, 20, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 22, 23, 24, 25};

    /**
     *
     * @param p_bytes
     * @return
     */
    public static String byteArrayToBase64(byte[] p_bytes) {
        return byteArrayToBase64(p_bytes, false);
    }

    /**
     *
     * @param p_bytes
     * @return
     */
    public static String byteArrayToAltBase64(byte[] p_bytes) {
        return byteArrayToBase64(p_bytes, true);
    }

    /**
     *
     * @param p_bytes
     * @param p_alternate
     * @return
     */
    private static String byteArrayToBase64(byte[] p_bytes, boolean p_alternate) {
        int aLen = p_bytes.length;
        int numFullGroups = aLen / 3;
        int numBytesInPartialGroup = aLen - 3 * numFullGroups;
        int resultLen = 4 * ((aLen + 2) / 3);
        StringBuilder result = new StringBuilder(resultLen);
        char[] intToAlpha = p_alternate ? intToAltBase64 : intToBase64;

        int inCursor = 0;

        for (int i = 0; i < numFullGroups; i++) {
            int byte0 = p_bytes[(inCursor++)] & 0xFF;
            int byte1 = p_bytes[(inCursor++)] & 0xFF;
            int byte2 = p_bytes[(inCursor++)] & 0xFF;
            result.append(intToAlpha[(byte0 >> 2)]);
            result.append(intToAlpha[(byte0 << 4 & 0x3F | byte1 >> 4)]);
            result.append(intToAlpha[(byte1 << 2 & 0x3F | byte2 >> 6)]);
            result.append(intToAlpha[(byte2 & 0x3F)]);
        }

        if (numBytesInPartialGroup != 0) {
            int byte0 = p_bytes[(inCursor++)] & 0xFF;
            result.append(intToAlpha[(byte0 >> 2)]);
            if (numBytesInPartialGroup == 1) {
                result.append(intToAlpha[(byte0 << 4 & 0x3F)]);
                result.append("==");
            } else {
                int byte1 = p_bytes[(inCursor++)] & 0xFF;
                result.append(intToAlpha[(byte0 << 4 & 0x3F | byte1 >> 4)]);
                result.append(intToAlpha[(byte1 << 2 & 0x3F)]);
                result.append('=');
            }
        }

        return result.toString();
    }

    /**
     *
     * @param p_string
     * @return
     */
    public static byte[] base64ToByteArray(String p_string) {
        return base64ToByteArray(p_string, false);
    }

    /**
     *
     * @param p_string
     * @return
     */
    public static byte[] altBase64ToByteArray(String p_string) {
        return base64ToByteArray(p_string, true);
    }

    /**
     *
     * @param p_string
     * @param p_alternate
     * @return
     */
    private static byte[] base64ToByteArray(String p_string, boolean p_alternate) {
        byte[] alphaToInt = p_alternate ? altBase64ToInt : base64ToInt;
        int sLen = p_string.length();
        int numGroups = sLen / 4;
        if (4 * numGroups != sLen) {
            throw new IllegalArgumentException("String length must be a multiple of four.");
        }
        int missingBytesInLastGroup = 0;
        int numFullGroups = numGroups;
        if (sLen != 0) {
            if (p_string.charAt(sLen - 1) == '=') {
                missingBytesInLastGroup++;
                numFullGroups--;
            }
            if (p_string.charAt(sLen - 2) == '=') {
                missingBytesInLastGroup++;
            }
        }
        byte[] result = new byte[3 * numGroups - missingBytesInLastGroup];

        int inCursor = 0;
        int outCursor = 0;
        for (int i = 0; i < numFullGroups; i++) {
            int ch0 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
            int ch1 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
            int ch2 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
            int ch3 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
            result[(outCursor++)] = (byte) (ch0 << 2 | ch1 >> 4);
            result[(outCursor++)] = (byte) (ch1 << 4 | ch2 >> 2);
            result[(outCursor++)] = (byte) (ch2 << 6 | ch3);
        }

        if (missingBytesInLastGroup != 0) {
            int ch0 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
            int ch1 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
            result[(outCursor++)] = (byte) (ch0 << 2 | ch1 >> 4);

            if (missingBytesInLastGroup == 1) {
                int ch2 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
                result[(outCursor++)] = (byte) (ch1 << 4 | ch2 >> 2);
            }

        }

        return result;
    }

    /**
     *
     * @param p_char
     * @param p_alphaToInt
     * @return
     */
    private static int base64toInt(char p_char, byte[] p_alphaToInt) {
        int result = p_alphaToInt[p_char];
        if (result < 0) {
            throw new IllegalArgumentException("Illegal Entry");
        }
        return result;
    }
}
