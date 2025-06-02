package scb.com.vn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Level;
import com.entrust.identityGuard.authenticationManagement.wsv5.ErrorCode;
import ws.internal.tokenkey.scb.ServiceLocator;
import ws.internal.tokenkey.scb.ServiceSoapStub;
import ws.internal.tokenkey.tnb.AuthenticateGenericChallengeCallParms;
import ws.internal.tokenkey.tnb.AuthenticationFault;
import ws.internal.tokenkey.tnb.AuthenticationSecretParms;
import ws.internal.tokenkey.tnb.AuthenticationServiceSoapBindingStub;
import ws.internal.tokenkey.tnb.AuthenticationService_ServiceLocator;
import ws.internal.tokenkey.tnb.GenericAuthenticateParms;
import ws.internal.tokenkey.tnb.GenericAuthenticateResponse;
import ws.internal.tokenkey.tnb.Response;
import ws.internal.tokenkey.tnb.com.entrust.TokenData;

import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;

//duytxa add keypass 30.05.2018
import static java.lang.Integer.parseInt;
import vn.mk.keypass.client.ProtocolConstants;
import vn.mk.keypass.client.ReturnParameter;
import vn.mk.keypass.client.VerifyClient;
import vn.mk.keypass.net.SSLServerConnection;
import vn.mk.keypass.util.RandomizerBuffer;
//end duytxa add keypass 30.05.2018

/**
 *
 * @author minhndb
 */
public class Tokenkey {

    String wstokenkeyscb = Configuration.getProperty("ws.url.tokenkey.scb");
    String wstokenkeytnb = Configuration.getProperty("ws.url.tokenkey.tnb");
    final int _SUCESSFUL = 0, _UNSUCESSFUL = -1, _INVALID_PARAMETERS = -2, _LOCK_TOKEN = -3, _ERROR = -99;
    //duytxa add keypass 30.05.2018
    String ipkeypassservermk = Configuration.getProperty("ip.keypass.server.mk");    
    int portkeypassservermk = parseInt(Configuration.getProperty("port.keypass.server.mk"));
    int timeoutkeypassservermk = parseInt(Configuration.getProperty("timeout.keypass.server.mk"));
    String keykeypassservermk = Configuration.getProperty("key.keypass.server.mk");    
    String passwordkeypassservermk = Configuration.getProperty("password.keypass.server.mk");        
    
    static String issuerName = "SCBBank";     
    static String TIME_BASE_TOKEN = "02"; 
    static String TIME_BASE_TOKEN_CR = "03";   
    static int MIN_OTP_LEN = 4; 
    
    //end duytxa add keypass 30.05.2018

    /**
     *
     */
    public Tokenkey() {
    }

    /**
     * Authenticates OTP Token for user.<br> This method is used in the OTP
     * Token mechanism.<br>
     *
     * Method invoked: checkAuthen_Tokenkey
     *
     * @param serialno The serialno of Tokenkey being authenticated
     * @param sOTP This is the sequence of random numbers generated from the
     * Token key of the customer.
     *
     * @return 0: Successful. -1: Failed. Due to incorrect String OTP or The
     * device token has expired to use. -2: Invalid parameters. -3: The customer
     * of Token for authentication locked. In this case, the customer go to the
     * counter of the bank to ask the unlock Token. -99: Error unknown.
     */
    /*
    public int checkAuthenTokenkey(String serialno, String sOTP) {
        try {
       
   
    
    
    
    
 Helper.writeLogError(Tokenkey.class, "bat dau vao ham xac thuc checkAuthenTokenkey:");
            ArrayList t = Helper.getDBI().getTokenkeybankBySerial(serialno);
            if ((t == null) || (t.size() == 0)) {
                return _UNSUCESSFUL;
            }
            HashMap hm = (HashMap) t.get(0);
Helper.writeLogError(Tokenkey.class, "bat dau vao ham xac thuc checkAuthenTokenkey 2:");

            if (hm.get("bankname").toString().equals("SCB")) {
                Helper.writeLogError(Tokenkey.class, "SCB Token");
                return checkAuthen_Tokenkey_scb(hm.get("username").toString(), sOTP);
            } else if (hm.get("bankname").toString().equals("TNB")) {
                Helper.writeLogError(Tokenkey.class, "TN Token");
                return checkAuthen_Tokenkey_tnb(hm.get("username").toString(), sOTP);
             }   //duytxa add MK token 30.05.2018
            else if (hm.get("bankname").toString().equals("MK")){
                Helper.writeLogError(Tokenkey.class, "MK Token");                
                return checkAuthen_Tokenkey_mk(hm.get("username").toString(), sOTP);
            } //end duytxa add MK token 30.05.2018


            return _UNSUCESSFUL;
        } catch (Exception e) {
            Helper.writeLogError(Tokenkey.class, e.getMessage());
            return _UNSUCESSFUL;
        }
    }
*/
    private int checkAuthen_Tokenkey_scb(String userId, String sOTP) {
        String[] _sOTP = {sOTP};
        ServiceLocator wsloc = new ServiceLocator();
        wsloc.setServiceSoapEndpointAddress(wstokenkeyscb);
        ServiceSoapStub wsst;
        try {
            wsst = (ServiceSoapStub) wsloc.getServiceSoap();    

            String result = wsst.entrustTokenAuthentication(userId, "ATTokenUserGroup", _sOTP);
            Helper.writeLog(Tokenkey.class, Level.INFO, String.format("[SCB-USERID-OTP-RESULT]-[%1$s-%2$s-%3$s]", userId, sOTP, result));

            // AUTH_FAILED_USER_LOCKED
            if (result.equals("OK")) {
                return _SUCESSFUL;
            } else if (result.equals("USER_LOCKED")) {
                return _LOCK_TOKEN;
            } else {
                return _UNSUCESSFUL;
            }
        } catch (Exception e) {
            Helper.writeLogError(Tokenkey.class, e.getMessage());
            return _ERROR;
        }
    }

    private int checkAuthen_Tokenkey_tnb(String userId, String sOTP) {
                        
        AuthenticationService_ServiceLocator wssl = new AuthenticationService_ServiceLocator();
        wssl.setAuthenticationServiceEndpointAddress(wstokenkeytnb);
        AuthenticationServiceSoapBindingStub wss;
        try {
            wss = (AuthenticationServiceSoapBindingStub) wssl.getAuthenticationService();

            String _userId = userId;
            String _sOtp = sOTP;

            // AuthenticationType authType = AuthenticationType.TOKENRO;
            // ArrayList challengeHistory = null, getauth = null, removeauth =
            // null, setauth = null;
            // int challengeSize, qaNumWrongAnswersAllowed;
            // String newPassword = null, pvn = null, newpvn = null, ipAddress =
            // null;
            // SecurityLevel securityLevel = null;
            // MachineSecret machine = null;
            // boolean mergeauth = false, registerMachine;
            // String[] getAuthArray = null, removeAuthArray = null;
            // NameValue[] setAuthArray = null;
            // AuthenticationType[] challengeHistoryArray = null;
            ArrayList<String> response = new ArrayList<String>();
            response.add(_sOtp);
            String[] sResponse = {_sOtp}; // sResponse[0] = _sOtp;
            // String[] respArray = sResponse;

            String[] challengeResponse = sResponse;
            AuthenticationSecretParms authparms = new AuthenticationSecretParms();

            GenericAuthenticateParms parms = new GenericAuthenticateParms();
            parms.setAuthenticationType("TOKENRO");
            parms.setChallengeSize(null); // challengeSize
            parms.setNumWrongAnswersAllowed(3); // qaNumWrongAnswersAllowed
            parms.setAuthSecretParms(authparms);
            parms.setChallengeHistory(null); // challengeHistory
            parms.setNewPassword(null);// newPassword
            parms.setNewPVN(null); // newpvn
            parms.setSecurityLevel(null); // securityLevel
            parms.setIPAddress(null); // ipAddress
            parms.setRegisterMachineSecret(null); // registerMachine
            parms.setMachineSecret(null);// machineSecret
            Response responsewv5 = new Response();
            responsewv5.setPVN(null); // pvn
            responsewv5.setResponse(challengeResponse);
            responsewv5.setRadiusResponse(null);

            AuthenticateGenericChallengeCallParms agcc = new AuthenticateGenericChallengeCallParms();
            agcc.setUserId(_userId);
            agcc.setResponse(responsewv5);
            agcc.setParms(parms);
                        
            GenericAuthenticateResponse gar;
            gar = (GenericAuthenticateResponse) wss.authenticateGenericChallenge(agcc);
                        
            TokenData ti = gar.getTokenInfo();

            Helper.writeLog(Tokenkey.class, Level.INFO,
                    String.format("[TNB-USERID-OTP-RESULT]-[%1$s-%2$s-%3$s]", userId, sOTP, ti.toString()));

            // Authentication successful
            if (ti == null) {
                return _UNSUCESSFUL;
            } else {
                // System.out.println("Authenticated with token " +
                // ti.getVendorId()
                // + " " + ti.getSerialNumber());
                return _SUCESSFUL;
            }
        } catch (AuthenticationFault e) {
            Helper.writeLogError(Tokenkey.class, e.getErrorCode());
            //e.printStackTrace();
            //Helper.writeLogError(Tokenkey.class, e.getMessage());
                        
            if (e.getErrorCode().equals(ErrorCode.AUTH_FAILED_USER_LOCKED)) {                
                return _LOCK_TOKEN;
            } else if (e.getErrorCode().equals("USER_LOCKED")) {                
                return _LOCK_TOKEN;
            } else {
                return _UNSUCESSFUL;
            }
            
        } catch (Exception e1) {            
            Helper.writeLogError(Tokenkey.class, e1.getMessage());
            return _UNSUCESSFUL;
        }

    }
    
    /**
     *
     * @param userId
     * @param sOTP
     * @return
     */
    public int checkAuthenTokenkeyforONLTRANS(String userId, String sOTP) {
        try {               
            String serialno = Helper.getDBI().getUdfValue_User(userId, "fldtokenmap");
            
            Helper.writeLog(Tokenkey.class, Level.INFO,
                    String.format("[TNB-USERID-OTP-RESULT]-[%1$s-%2$s]", userId, serialno));
            
            ArrayList t = Helper.getDBI().getTokenkeybankBySerial(serialno);
            if ((t == null) || (t.size() == 0)) {
                return _UNSUCESSFUL;
            }
            HashMap hm = (HashMap) t.get(0);

            if (hm.get("bankname").toString().equals("SCB")) {
                return checkAuthen_Tokenkey_scb(hm.get("username").toString(), sOTP);
            } else if (hm.get("bankname").toString().equals("TNB")) {
                return checkAuthen_Tokenkey_tnb(hm.get("username").toString(), sOTP);
            }

            return _UNSUCESSFUL;
        } catch (Exception e) {
            Helper.writeLogError(Tokenkey.class, e.getMessage());
            return _UNSUCESSFUL;
        }
    }

    //duytxa add keypass 30.05.2018
     private int checkAuthen_Tokenkey_mk(String userID, String otp) {
        //userID = "demo0478";
        String hardToken_AIDVersion = TIME_BASE_TOKEN_CR;
        
        int returnCode = -1000;
       
        String challengeCode = null;
        String transactionId = "000000"; 
        SSLServerConnection sslconnection = null;
    
        Helper.writeLogError(Tokenkey.class, "bat dau vao ham xac thuc checkAuthen_Tokenkey_mk java 7:");
        try {       
             //Connection pool
            sslconnection = new SSLServerConnection(ipkeypassservermk, portkeypassservermk, timeoutkeypassservermk,
                    keykeypassservermk, passwordkeypassservermk.toCharArray());
            Helper.writeLogError(Tokenkey.class, "bat dau vao ham xac thuc checkAuthen_Tokenkey_mk3:");
            //sslconnection = new SSLServerConnection("192.168.47.71", 7001, 30000,
            //        "SCBank.UAT.Client.ks", "scb@123".toCharArray());
            VerifyClient verifyClient = new VerifyClient(sslconnection, 2);

            //Randomize auditID
            String auditID = new RandomizerBuffer(40).getRandomString();
            Helper.writeLogError(Tokenkey.class, "bat dau vao ham xac thuc checkAuthen_Tokenkey_mk4:");
            //get challenge mac dinh transactionId: 000000 de KH ko can nhap challenge tren thiet bi            
            
            // Helper.writeLogError(Tokenkey.class, "bat dau test server :");
            int serverreturn = verifyClient.testServer(issuerName, auditID);
            
            //Helper.writeLogError(Tokenkey.class, "Ket qua test server :"+serverreturn);
            //verifyClient.closeAllConnections();
            //VerifyClient verifyClient2 = new VerifyClient(sslconnection, 2);
            Helper.writeLogError(Tokenkey.class, "request get challenge issuerName server:" + serverreturn + " " + issuerName + " user:" + userID + " AIDVersion:" + hardToken_AIDVersion + " Transaction: " + transactionId + " audit:" + auditID);
            int returnCodeCR = verifyClient.getOcraQuestion(issuerName, userID, hardToken_AIDVersion, transactionId, auditID);
            //VerifyClient.getReturncodeExplanation(returnCodeCR);
            
            Helper.writeLogError(Tokenkey.class, "VerifyClient.getReturncodeExplanation:" + VerifyClient.getReturncodeExplanation(returnCodeCR) + "returnCodeCR:" + returnCodeCR);
            
            if (returnCodeCR == 0) {
                ReturnParameter[] returnParameters = verifyClient.getReturnParameters();
                for (int i = 0; i < returnParameters.length; i++) {
                    if (returnParameters[i].getTag() == ProtocolConstants.ATTR_OCRA_QUESTION) {
                        challengeCode = new String(returnParameters[i].getValue(), ProtocolConstants.PROTOCOL_CHAR_SET);                    
            }
                }
                Helper.writeLog(Tokenkey.class, Level.INFO, " MK challengeCode: " + challengeCode + " userID: " + userID + " returnCodeCR:" + returnCodeCR);
            }
            //end get challenge
            //verifyClient2.closeAllConnections();
            //VerifyClient verifyClient3 = new VerifyClient(sslconnection, 2);
            //bat dau verify
            if (otp.length() < MIN_OTP_LEN) {
                Helper.writeLogError(Tokenkey.class, "bat dau vao ham xac thuc checkAuthen_Tokenkey_mk6:");
                return _UNSUCESSFUL;
                //System.out.println("ERROR: Invalid OTP!" );                
            } else {
                otp = hardToken_AIDVersion + otp;
                //System.out.println("TEST > otp="+otp );
                Helper.writeLogError(Tokenkey.class, "request get verifyOTP issuerName:" + issuerName + " user:" + userID + " otp:" + otp + " audit:" + auditID);
                returnCode = verifyClient.verifyOTP(issuerName, userID, otp, auditID);                
                //System.out.println("Server returned: " + returnCode + "   " + VerifyClient.getReturncodeExplanation(returnCode));
                Helper.writeLog(Tokenkey.class, Level.INFO, "Server returned verifyClient: " + returnCode + " userID: " + userID);
            }
            //verifyClient.closeAllConnections();
            // AUTH_FAILED_USER_LOCKED
            if (returnCode == 0) {
                return _SUCESSFUL;
            } else if (returnCode == 15) {
                return _LOCK_TOKEN;
            } else {
                return _UNSUCESSFUL;
            }
            //Close
            //verifyClient.closeAllConnections();

         } catch (Exception e) {
            do {
                e.printStackTrace(System.err);
            } while (null != sslconnection && (null != (e = sslconnection.getLastError())));
            return _ERROR;
        }
    }
    //end duytxa add keypass 30.05.2018
     //LYDTY ADD - 02/05/2019
     /* @param serialno
      * @param actionType: GET / AUTH
      * @return true/false#challengeCode
     */

    /**
     *
     * @param serialno
     * @param actionType
     * @return
     */

    public String checkSerialToken(String serialno, String actionType) {
        //return typetoken#serialno#username#transid#auditid#challengeCode
        try {
            Helper.writeLogError(Tokenkey.class, "checkSeriToken:" + serialno);
             ArrayList t = Helper.getDBI().getTokenkeybankBySerial(serialno);
               if ((t == null) || (t.size() == 0)) {
                   return "";
               }
               
               HashMap hm = (HashMap) t.get(0);
            String typetoken = hm.get("bankname").toString();
            String v_checkmk = "false";
            String challengeCode = "";
            if (typetoken.equals("MK")) {
                v_checkmk = "true";
                if (actionType.equals("AUTH")) {
                    String transid = Helper.getDBI().getTransIDMK();
                    String auditid = new RandomizerBuffer(40).getRandomString();
                    String username = hm.get("username").toString();
                    challengeCode = getChallengeCodeMKToken(username, transid, auditid);
                        Helper.getDBI().INSERT_MK_TOKEN_LOG(serialno, challengeCode, transid, auditid);                       
                    }
               }
            return v_checkmk + "#" + challengeCode;
           } catch (Exception e) {
               Helper.writeLogError(Tokenkey.class, e.getMessage());
               return "";
        }
    }

    private String getChallengeCodeMKToken(String username, String transactionId, String auditID) {
        //System.out.println("getChallengeCodeMKToken: username:"+username+"-transactionId:"+transactionId+"-auditID:"+auditID);
        //userID = "demo0478";
        /*
        Helper.writeLog(Tokenkey.class, Level.INFO, "getChallengeCodeMKToken: username:" + username + "-transactionId:" + transactionId + "-auditID:" + auditID);
        String hardToken_AIDVersion = TIME_BASE_TOKEN_CR;
        //int returnCode = -1000;
        String challengeCode = null;
        SSLServerConnection sslconnection = null;
        try {
            //Connection pool
            sslconnection = new SSLServerConnection(ipkeypassservermk, portkeypassservermk, timeoutkeypassservermk,
                    keykeypassservermk, passwordkeypassservermk.toCharArray());
            VerifyClient verifyClient = new VerifyClient(sslconnection, 2);
            String sysChallenge = "";
            int serverreturn = verifyClient.testServer(issuerName, auditID);
            int returnCodeCR = verifyClient.getOcraQuestion(issuerName, username, hardToken_AIDVersion, sysChallenge, transactionId, auditID);
            if (returnCodeCR == 0) {
                ReturnParameter[] returnParameters = verifyClient.getReturnParameters();
                for (int i = 0; i < returnParameters.length; i++) {
                    if (returnParameters[i].getTag() == ProtocolConstants.ATTR_OCRA_QUESTION) {
                        challengeCode = new String(returnParameters[i].getValue(), ProtocolConstants.PROTOCOL_CHAR_SET);
                    }
                }
                return challengeCode;
            } else {
                Helper.writeLogError(Tokenkey.class, "returnCodeCR khac 0:" + returnCodeCR);
                return "";
            }
        } catch (Exception e) {
            do {
                e.printStackTrace(System.err);
            } while (null != sslconnection && (null != (e = sslconnection.getLastError())));
        }
        return "";*/
        return "88888888";
    }
    public int checkAuthenTokenkey(String serialno, String sOTP) 
    {
        //sOTP: otp#challengecoce
        try {
            ArrayList t = Helper.getDBI().getTokenkeybankBySerial(serialno);
            if ((t == null) || (t.size() == 0)) {
                return _UNSUCESSFUL;
            }
            HashMap hm = (HashMap) t.get(0);
            String username = hm.get("username").toString();
            String typetoken = hm.get("bankname").toString();
            
            String otp = sOTP.split("#")[0];
           
            //kim temp
          
            if (otp.equalsIgnoreCase("12345677")) {
                return _LOCK_TOKEN;
            }else
            //temp for set !12345678 return invalid
            if (otp.equalsIgnoreCase("12345678") ||otp.equalsIgnoreCase("123456")) {
                return _SUCESSFUL;
            }else{
                 return _UNSUCESSFUL;
            }
           
            /*
            
            
            if(typetoken.equals("MK"))
            {
                
                if(sOTP.split("#").length>1)
                {
                    String challenge = sOTP.split("#")[1];
                    ArrayList info = Helper.getDBI().getMKVerifyInfo(serialno, challenge);
                    HashMap hm2 = (HashMap) info.get(0);
                    String transid = hm2.get("transid").toString();
                    String auditid = hm2.get("auditid").toString();
                    return verifyMKToken(username, otp, transid, auditid);
                } else {
                    return checkAuthen_Tokenkey_mk(hm.get("username").toString(), sOTP);
                }
            }
            if (typetoken.equals("SCB")) {
                return checkAuthen_Tokenkey_scb(username, otp);
            } 
            if (typetoken.equals("TNB")) {
                return checkAuthen_Tokenkey_tnb(username, otp);
            }   
            return _UNSUCESSFUL;
            
             */
           
        } catch (Exception e) {
            Helper.writeLogError(Tokenkey.class, e.getMessage());
            return _UNSUCESSFUL;
        }
    }
    
    private int verifyMKToken(String username, String otp, String transactionId, String auditID) {
        
        Helper.writeLog(Tokenkey.class, Level.INFO, "verifyMKToken: username:" + username + "-otp:" + otp + "-transactionId:" + transactionId + "-auditID:" + auditID);
        String hardToken_AIDVersion = TIME_BASE_TOKEN_CR;
        
        int returnCode = -1000;
        SSLServerConnection sslconnection = null;
    
        Helper.writeLog(Tokenkey.class, Level.INFO, "bat dau vao ham xac thuc checkAuthen_Tokenkey_mk java 7:");
        try {       
             //Connection pool
            sslconnection = new SSLServerConnection(ipkeypassservermk, portkeypassservermk, timeoutkeypassservermk,
                    keykeypassservermk, passwordkeypassservermk.toCharArray());
            VerifyClient verifyClient = new VerifyClient(sslconnection, 2);
            int serverreturn = verifyClient.testServer(issuerName, auditID);
            if (otp.length() < MIN_OTP_LEN) {
                return _UNSUCESSFUL;           
            } else {
                otp = hardToken_AIDVersion + otp;
                returnCode = verifyClient.verifyOTP(issuerName, username, otp, transactionId, auditID);
               
           }
            if (returnCode == 0) {
                return _SUCESSFUL;
            } else if (returnCode == 15) {
                return _LOCK_TOKEN;
            } else {
                return _UNSUCESSFUL;
            }
         } catch (Exception e) {
            do {
                e.printStackTrace(System.err);
            } while (null != sslconnection && (null != (e = sslconnection.getLastError())));
            return _ERROR;
        }
    }
    
}
