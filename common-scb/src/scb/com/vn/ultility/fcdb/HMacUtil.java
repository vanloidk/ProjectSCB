/*    */ package scb.com.vn.ultility.fcdb;
/*    */ 
/*    */ import javax.crypto.Mac;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ 
/*    */ public final class HMacUtil
/*    */ {
/*    */   public static byte[] encrypt(byte[] p_key, byte[] p_data)
/*    */     throws Exception
/*    */   {
/* 44 */     byte[] l_result = null;
/* 45 */     SecretKeySpec l_key = null;
/* 46 */     Mac l_mac = null;
/*    */ 
/* 48 */     l_key = new SecretKeySpec(p_key, 0, p_key.length, "HMACSHA1");
/* 49 */     l_mac = Mac.getInstance("HMACSHA1");
/* 50 */     l_mac.init(l_key);
/* 51 */     l_result = l_mac.doFinal(p_data);
/* 52 */     return l_result;
/*    */   }
/*    */ 
/*    */   public static byte[] mergeArray(byte[] p_byte1, byte[] p_byte2)
/*    */   {
/* 62 */     byte[] l_bytes = null;
/* 63 */     int l_len1 = 0;
/* 64 */     int l_len2 = 0;
/* 65 */     int l_len = 0;
/* 66 */     if (p_byte1 != null) {
/* 67 */       l_len1 = p_byte1.length;
/*    */     }
/* 69 */     if (p_byte2 != null) {
/* 70 */       l_len2 = p_byte2.length;
/*    */     }
/* 72 */     l_len = l_len1 + l_len2;
/* 73 */     l_bytes = new byte[l_len];
/* 74 */     for (int l_i = 0; l_i < l_len1; l_i++) {
/* 75 */       l_bytes[l_i] = p_byte1[l_i];
/*    */     }
/* 77 */     for (int l_i = 0; l_i < l_len2; l_i++) {
/* 78 */       l_bytes[(l_len1 + l_i)] = p_byte2[l_i];
/*    */     }
/* 80 */     return l_bytes;
/*    */   }
/*    */ }

/* Location:           E:\Dbanking4\system\build\kernel\FCDB_6.0.0.0.0.0.1\
 * Qualified Name:     com.iflex.fcat.services.apps.HMacUtil
 * JD-Core Version:    0.6.0
 */