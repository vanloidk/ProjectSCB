/*     */ package scb.com.vn.ultility.fcdb;
/*     */ 
/*     */ public final class JFBase64
/*     */ {
/*  48 */   private static final char[] intToBase64 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
/*     */ 
/*  63 */   private static final char[] intToAltBase64 = { '!', '"', '#', '$', '%', '&', '\'', '(', ')', ',', '-', '.', ':', ';', '<', '>', '@', '[', ']', '^', '`', '_', '{', '|', '}', '~', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '?' };
/*     */ 
/*  78 */   private static final byte[] base64ToInt = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };
/*     */ 
/*  92 */   private static final byte[] altBase64ToInt = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, -1, 62, 9, 10, 11, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 12, 13, 14, -1, 15, 63, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1, 18, 19, 21, 20, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 22, 23, 24, 25 };
/*     */ 
/*     */   public static String byteArrayToBase64(byte[] p_bytes)
/*     */   {
/* 106 */     return byteArrayToBase64(p_bytes, false);
/*     */   }
/*     */ 
/*     */   public static String byteArrayToAltBase64(byte[] p_bytes)
/*     */   {
/* 118 */     return byteArrayToBase64(p_bytes, true);
/*     */   }
/*     */ 
/*     */   private static String byteArrayToBase64(byte[] p_bytes, boolean p_alternate)
/*     */   {
/* 125 */     int aLen = p_bytes.length;
/* 126 */     int numFullGroups = aLen / 3;
/* 127 */     int numBytesInPartialGroup = aLen - 3 * numFullGroups;
/* 128 */     int resultLen = 4 * ((aLen + 2) / 3);
/* 129 */     StringBuffer result = new StringBuffer(resultLen);
/* 130 */     char[] intToAlpha = p_alternate ? intToAltBase64 : intToBase64;
/*     */ 
/* 133 */     int inCursor = 0;
/*     */ 
/* 135 */     for (int i = 0; i < numFullGroups; i++) {
/* 136 */       int byte0 = p_bytes[(inCursor++)] & 0xFF;
/* 137 */       int byte1 = p_bytes[(inCursor++)] & 0xFF;
/* 138 */       int byte2 = p_bytes[(inCursor++)] & 0xFF;
/* 139 */       result.append(intToAlpha[(byte0 >> 2)]);
/* 140 */       result.append(intToAlpha[(byte0 << 4 & 0x3F | byte1 >> 4)]);
/* 141 */       result.append(intToAlpha[(byte1 << 2 & 0x3F | byte2 >> 6)]);
/* 142 */       result.append(intToAlpha[(byte2 & 0x3F)]);
/*     */     }
/*     */ 
/* 146 */     if (numBytesInPartialGroup != 0) {
/* 147 */       int byte0 = p_bytes[(inCursor++)] & 0xFF;
/* 148 */       result.append(intToAlpha[(byte0 >> 2)]);
/* 149 */       if (numBytesInPartialGroup == 1) {
/* 150 */         result.append(intToAlpha[(byte0 << 4 & 0x3F)]);
/* 151 */         result.append("==");
/*     */       }
/*     */       else {
/* 154 */         int byte1 = p_bytes[(inCursor++)] & 0xFF;
/* 155 */         result.append(intToAlpha[(byte0 << 4 & 0x3F | byte1 >> 4)]);
/* 156 */         result.append(intToAlpha[(byte1 << 2 & 0x3F)]);
/* 157 */         result.append('=');
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 162 */     return result.toString();
/*     */   }
/*     */ 
/*     */   public static byte[] base64ToByteArray(String p_string)
/*     */   {
/* 175 */     return base64ToByteArray(p_string, false);
/*     */   }
/*     */ 
/*     */   public static byte[] altBase64ToByteArray(String p_string)
/*     */   {
/* 189 */     return base64ToByteArray(p_string, true);
/*     */   }
/*     */ 
/*     */   private static byte[] base64ToByteArray(String p_string, boolean p_alternate)
/*     */   {
/* 196 */     byte[] alphaToInt = p_alternate ? altBase64ToInt : base64ToInt;
/* 197 */     int sLen = p_string.length();
/* 198 */     int numGroups = sLen / 4;
/* 199 */     if (4 * numGroups != sLen) {
/* 200 */       throw new IllegalArgumentException("String length must be a multiple of four.");
/*     */     }
/* 202 */     int missingBytesInLastGroup = 0;
/* 203 */     int numFullGroups = numGroups;
/* 204 */     if (sLen != 0) {
/* 205 */       if (p_string.charAt(sLen - 1) == '=') {
/* 206 */         missingBytesInLastGroup++;
/* 207 */         numFullGroups--;
/*     */       }
/* 209 */       if (p_string.charAt(sLen - 2) == '=')
/* 210 */         missingBytesInLastGroup++;
/*     */     }
/* 212 */     byte[] result = new byte[3 * numGroups - missingBytesInLastGroup];
/*     */ 
/* 215 */     int inCursor = 0; int outCursor = 0;
/* 216 */     for (int i = 0; i < numFullGroups; i++) {
/* 217 */       int ch0 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
/* 218 */       int ch1 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
/* 219 */       int ch2 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
/* 220 */       int ch3 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
/* 221 */       result[(outCursor++)] = (byte)(ch0 << 2 | ch1 >> 4);
/* 222 */       result[(outCursor++)] = (byte)(ch1 << 4 | ch2 >> 2);
/* 223 */       result[(outCursor++)] = (byte)(ch2 << 6 | ch3);
/*     */     }
/*     */ 
/* 227 */     if (missingBytesInLastGroup != 0) {
/* 228 */       int ch0 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
/* 229 */       int ch1 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
/* 230 */       result[(outCursor++)] = (byte)(ch0 << 2 | ch1 >> 4);
/*     */ 
/* 232 */       if (missingBytesInLastGroup == 1) {
/* 233 */         int ch2 = base64toInt(p_string.charAt(inCursor++), alphaToInt);
/* 234 */         result[(outCursor++)] = (byte)(ch1 << 4 | ch2 >> 2);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 239 */     return result;
/*     */   }
/*     */ 
/*     */   private static int base64toInt(char p_char, byte[] p_alphaToInt)
/*     */   {
/* 253 */     int result = p_alphaToInt[p_char];
/* 254 */     if (result < 0)
/* 255 */       throw new IllegalArgumentException("Illegal Entry");
/* 256 */     return result;
/*     */   }
/*     */ }

/* Location:           E:\Dbanking4\system\build\kernel\FCDB_6.0.0.0.0.0.1\
 * Qualified Name:     com.iflex.fcat.infra.JFBase64
 * JD-Core Version:    0.6.0
 */