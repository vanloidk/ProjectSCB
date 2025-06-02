package scb.com.vn.utility;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import scb.com.vn.ultility.Common;

import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.math.BigInteger;
import java.security.spec.RSAPublicKeySpec;
import java.security.KeyFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import scb.com.vn.controller.MobileController;
import vn.com.scb.bian.utility.IIBContext;
import vn.com.sml.ibt.rsa.EncodingType;
import vn.com.sml.ibt.rsa.RSASignature;
import vn.com.sml.ibt.rsa.SignatureKeyStoreMode;


/**
 * @author : Nguyen Ngo Duy Phuc
 * @version 1.1 Update: 27/3/2013
 */
public class Configuration {
    
    private static Properties configprop = new Properties();
    
     /* 
    * wsgateway ~ gw tai quay
    * wsib ~ gw ib
    * wsibho ~ gw ib hoi so
    * wsgateway2 ~ gw mobi
    * wsgateway8 ~ gw dvkh
    * wsodbx ~ gw odbx
    * wsibt ~ gw ibt
    * wshq247 ~ gw hai quan 247
    * wsmomo ~ gw momo
    * wsibpilot ~ gw ebankgw_scb_pilot danh cho team the gui sms
     */
    
    private static final String name_VM_arg = "wsgateway2";//"wsib";//"wsgateway2";//wsodbx";"wsib";//wsodbx";  //for sit  //ws_tnb_chgpwd //gwhome  "gwhome";  //for scb  "gwhome_tnb"; //for TNB
    private static final String CONFIG_FILE = "/config/config.properties";
    public static List<String> lst_permission;
    public static KeyStore keyStore;
    public static X509Certificate publicCert;    
    public static PublicKey pubKeySmartlink;
    public static PrivateKey privateKeySCB_2_SML;    
    public static HashMap hmWSPartners;// = new HashMap();
    public static HashMap hmWSPartnerPermissions;
    private static IIBContext iibContext;
    
    public static  HashMap<String, String> hmapResponse = new HashMap<String, String>();
    public static  HashMap<String, String> hmapRequest = new HashMap<String, String>();

    public static void init() {
        if (configprop.isEmpty()) {          
          String path = System.getProperty(name_VM_arg);
        //String path="C:/EBANKING/SOURCE_CODE/ebankwebservice";
        //String path = "D:\\EBANKING\\SOURCE_CODE\\ebankwebservice\\config";
            try {
                //0. Nap cau hinh
                //for live
                //configprop = Common.loadProperty(path + CONFIG_FILE);
                configprop = Common.loadProperty("/u01/source/gateway/wsgateway/MB/config/config.properties");
                        
                //for test only
                //configprop = Common.loadProperty("D:\\EBANKING\\SOURCE_CODE\\ebankwebservice\\config\\config.properties");
                
                if (configprop.isEmpty()) {
                    throw new Exception("Không tìm thấy link file config.");
                }

                //1. Set path log.properties
                Properties p = new Properties();
                p.load(new FileInputStream(getProperty("file.log.config")));
                PropertyConfigurator.configure(p);
                
                //load IIB Context   
                loadIIBContext();   
                
                //load ServiceBilling
                MobileController controller = new MobileController();
                controller.init();
                Helper.writeLog(Configuration.class, Level.INFO, "MobileController.init()");
                //Load payoo cert
                loadKeySotre(getProperty("ws.payoo.file.privatekey"), getProperty("ws.payoo.privatekey.password"));
                loadPublicCert(getProperty("ws.payoo.file.publiccert"));
                                
                //Update 02/06/2014 to support IBT transaction with Smartlink
                //Load SCB private key to smartlink IBT
                loadPrivateKeySCB_2_SML(getProperty("ws.smartlink.file.scb.privatekey"));
                
                //Load smartlink public cert
               // loadPublicKeySML(getProperty("ws.smartlink.file.partner.publickey"));
                
                
                //08Aug2016: Update by HieuDT to speed up program
                //Load ws partners                
                loadWebServicePartners();
                           
                
            } catch (Exception ex) {
                System.out.println("init : "+ex.getMessage());
            }
        }

        if (lst_permission == null) {
            try {
                lst_permission = Helper.getDBI().getGwPermissions();
                
                hmWSPartnerPermissions = new HashMap();
                
                //08Aug2016: Update by HieuDT to speed up program
                for (String s : Configuration.lst_permission) {                    
                    hmWSPartnerPermissions.put(s.toLowerCase(), s);
                    Helper.writeLog(Configuration.class, Level.INFO, "Loaded permissions: " + s);
                }                                
                //////////
            } catch (Exception ex) {
                Helper.writeLogError(Configuration.class, ex);
            }
        }
    }
    
    private static void loadKeySotre(String pkcs12File, String pkcs12Password) throws Exception {
        keyStore = KeyStore.getInstance("PKCS12");
        try {
            InputStream inputStream = new FileInputStream(pkcs12File);
            keyStore.load(inputStream, pkcs12Password.toCharArray());
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
    
    public static void loadPublicCert(String publicCertPathFile) throws Exception {        
        try {
            if (Security.getProvider("BC") == null) {
                Security.addProvider(new BouncyCastleProvider());
            }                        

            InputStream inputStream = new FileInputStream(publicCertPathFile);
            CertificateFactory factory = CertificateFactory.getInstance("X509", "BC");
            publicCert = (X509Certificate) factory.generateCertificate(inputStream);
            inputStream.close(); //close stream
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public static void loadWebServicePartners(){
        hmWSPartners = new HashMap();
        
        //Load all partners from db                                
        ArrayList<Hashtable<String, String>> p = null;
        try {
            p = Helper.getDBI().getallPartner();
        } catch (Exception e) {
            Helper.writeLogError(Configuration.class, e);
        }
        
        //Load to HashMap.
        Hashtable<String, String> _ht_temp = null;
        
        for (int i = 0; i < p.size(); i++) {
            _ht_temp = (Hashtable<String, String>) p.get(i);
            String acesskey = _ht_temp.get("accesskey");
            if (!hmWSPartners.containsKey(acesskey)) {
                hmWSPartners.put(acesskey, _ht_temp);
                Helper.writeLog(Configuration.class, Level.INFO, "Loaded acesskey: " + acesskey);
            }            
        }        
    }
    
    
        
    public static String getProperty(String name) {
        return configprop.getProperty(name);
    }
    
    
    public static void loadPrivateKeySCB_2_SML(String filepath) throws Exception {
        PrivateKey prvKey = RSASignature.getPrivateKey(filepath, SignatureKeyStoreMode.STRINGFILE, EncodingType.HEX);
        privateKeySCB_2_SML = prvKey;
    }    

    
    public static void loadPublicKeySML(String filepath) throws Exception {
        PublicKey partnerPubKey = RSASignature.getPublicKey(filepath, SignatureKeyStoreMode.XMLFILE, EncodingType.HEX);
        pubKeySmartlink = partnerPubKey;
    }    
    
      public static IIBContext getIIBContext() {              
            return iibContext;        
    }
      
 public static void loadIIBContext() throws Exception {   
        try {
            iibContext = new IIBContext();
            iibContext.setServiceEndpoint(getProperty("ws.iib.root"));
            iibContext.setBillingServiceEndpoint(getProperty("ws.iib.billing.root"));
            iibContext.setTimeout(Integer.parseInt(getProperty("ws.iib.timeout")));
            iibContext.setClientId(getProperty("ws.iib.clientId"));
            iibContext.setClientPassword(getProperty("ws.iib.clientPassword"));
        } catch (Exception e) {
            Helper.writeLog(Configuration.class, Level.INFO, "Loaded IIBContext: " + e);
            e.printStackTrace();
        }
    }
          
}
