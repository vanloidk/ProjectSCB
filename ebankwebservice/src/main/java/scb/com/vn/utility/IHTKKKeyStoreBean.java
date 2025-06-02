/*    */ package scb.com.vn.utility;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.security.KeyStore;
/*    */ import java.security.PrivateKey;
/*    */ import java.security.cert.Certificate;
/*    */ import java.util.Enumeration;
/*    */ 
/*    */

/**
 *
 * @author minhndb
 */
 public class IHTKKKeyStoreBean
/*    */ {
/*    */   private PrivateKey privateKey;
/*    */   private Certificate[] certChain;
/*    */ /*    */ 
/*    */

    /**
     *
     * @param certChain
     */
   public void setCertChain(Certificate[] certChain)
/*    */   {
/* 14 */     this.certChain = certChain;
/*    */   }
/*    */ /*    */ 
/*    */

    /**
     *
     * @return
     */
   public Certificate[] getCertChain() {
/* 18 */     return this.certChain;
/*    */   }
/*    */ /*    */ 
/*    */

    /**
     *
     * @param privateKey
     */
   public void setPrivateKey(PrivateKey privateKey) {
/* 22 */     this.privateKey = privateKey;
/*    */   }
/*    */ /*    */ 
/*    */

    /**
     *
     * @return
     */
   public PrivateKey getPrivateKey() {
/* 26 */     return this.privateKey;
/*    */   }
/*    */ /*    */ /*    */ /*    */ 
/*    */

    /**
     *
     * @param keyStoreFile
     * @param keyStorePwd
     * @return
     * @throws Exception
     */
   public static IHTKKKeyStoreBean getKeyStore(String keyStoreFile, String keyStorePwd) throws Exception {
/* 30 */     IHTKKKeyStoreBean keyStore = null;
/* 31 */     KeyStore ks = KeyStore.getInstance("pkcs12");
/* 32 */     ks.load(new FileInputStream(keyStoreFile), keyStorePwd.toCharArray());
/* 33 */     Enumeration aliasesEnum = ks.aliases();
/* 34 */     PrivateKey privateKey = null;
/* 35 */     Certificate[] certificateChain = null;
/* 36 */     if (aliasesEnum.hasMoreElements()) {
/* 37 */       String alias = (String)aliasesEnum.nextElement();
/* 38 */       certificateChain = ks.getCertificateChain(alias);
/* 39 */       privateKey = (PrivateKey)ks.getKey(alias, keyStorePwd.toCharArray());
/*    */ /*    */ 
/* 41 */       keyStore = new IHTKKKeyStoreBean();
/* 42 */       keyStore.setPrivateKey(privateKey);
/* 43 */       keyStore.setCertChain(certificateChain);
/*    */     }
/* 45 */     return keyStore;
/*    */   }
/*    */ }

/* Location:           D:\TCS_SCB\TCS_SCB\fj262s\war\WEB-INF\lib\_wl_cls_gen.jar
 * Qualified Name:     com.fmt.utils.xml.signer.IHTKKKeyStoreBean
 * JD-Core Version:    0.6.2
 */