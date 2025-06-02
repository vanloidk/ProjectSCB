/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import scb.com.vn.model.LockFeeMobileBankingOfSCBRp;
import scb.com.vn.model.LockFeeMobileBankingOfSCBRq;
import scb.com.vn.model.UnLockFeeMobileBankingOfSCBRp;
import scb.com.vn.model.UnLockFeeMobileBankingOfSCBRq;
import scb.com.vn.vninfo.*;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;
import scb.com.vn.mobile.model.ValidatePinMobileBankingRp;
import scb.com.vn.mobile.model.ValidatePinMobileBankingRq;
import scb.com.vn.mobile.model.ValidatePasswordMobileBankingRp;
import scb.com.vn.mobile.model.ValidatePasswordMobileBankingRq;

/**
 *
 * @author kimncm
 */
public class VnInfoGateway {

    final String _UNSUCESSFUL = "-1";
    String wsurlvninfogateway = Configuration.getProperty("ws.url.vninfogateway.address");
    String SecretKey = Configuration.getProperty("ws.url.vninfogateway.SecretKey");
    String wsurlMobileSCBAuthen = Configuration.getProperty("ws.url.vninfoSCBAuthen.address");

    /**
     *
     */
    public VnInfoGateway() {

    }

    /**
     *
     * @param UserName
     * @param CifNo
     * @param UserLock
     * @return
     */
    public String LockFeeMobileBankingOfSCB(String UserName, String CifNo, String UserLock) {
        try {
            LockFeeMobileBankingOfSCBRq request = new LockFeeMobileBankingOfSCBRq();
            request.setCifNo(CifNo);
            request.setUserName(UserName);
            request.setUserLock(UserLock);
            UnLockFeeLocator wssl = new UnLockFeeLocator();
            wssl.setUnLockFeeSoap12EndpointAddress(wsurlvninfogateway);
            UnLockFeeSoap12Stub wss = (UnLockFeeSoap12Stub) wssl.getUnLockFeeSoap12();
            wss.setTimeout(40000);// 40giay

            String strRequest = Xml.Marshaller(request);

            String strResult = wss.lockFeeMobileBankingOfSCB(strRequest, createSignData(CifNo, UserName));
            LockFeeMobileBankingOfSCBRp response = (LockFeeMobileBankingOfSCBRp) Xml.unMarshaller(LockFeeMobileBankingOfSCBRp.class, strResult);
            String strResponse = null;
            if (response != null) {
                strResponse = response.getErrorCode().concat("#").concat(response.getErrorMsg());
            }
            return strResponse;
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     *
     * @param UserName
     * @param CifNo
     * @param UserLock
     * @return
     */
    public String UnLockFeeMobileBankingOfSCB(String UserName, String CifNo, String UserLock) {
        try {
            UnLockFeeMobileBankingOfSCBRq request = new UnLockFeeMobileBankingOfSCBRq();
            request.setCifNo(CifNo);
            request.setUserName(UserName);
            request.setUserLock(UserLock);
            UnLockFeeLocator wssl = new UnLockFeeLocator();
            //wssl.setRegisterMBSoapEndpointAddress(wsurlvninfogateway);
            wssl.setUnLockFeeSoap12EndpointAddress(wsurlvninfogateway);

            UnLockFeeSoap12Stub wss = (UnLockFeeSoap12Stub) wssl.getUnLockFeeSoap12();
            wss.setTimeout(40000);// 40giay

            String strRequest = Xml.Marshaller(request);

            String strResult = wss.unLockFeeMobileBankingOfSCB(strRequest, createSignData(CifNo, UserName));
            UnLockFeeMobileBankingOfSCBRp response = (UnLockFeeMobileBankingOfSCBRp) Xml.unMarshaller(UnLockFeeMobileBankingOfSCBRp.class, strResult);
            String strResponse = null;
            if (response != null) {
                strResponse = response.getErrorCode().concat("#").concat(response.getErrorMsg());
            }
            return strResponse;
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     * Tao SignData
     *
     * @param CifNo
     * @param UserName
     * @return
     */
    public String createSignData(String CifNo, String UserName) {
        try {
            //String SecretKey = "mobilebankingscb@#%123";
            DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            String DateNow = formatter1.format(new java.util.Date());
            String Define = SecretKey.concat(CifNo).concat(UserName).concat(DateNow);
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(Define.getBytes(), 0, Define.length());
            //String sigdata = new BigInteger(1, m.digest()).toString(16);
            String sigdata = String.format("%032x", new BigInteger(1, m.digest()));
            return sigdata;
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     * ValidatePasswordMobileBanking
     *
     * @param Password
     * @param UserName
     * @param CifNo
     * @return
     */
    public String ValidatePasswordMobileBanking(String Password, String UserName, String CifNo) {
        try {
            ValidatePasswordMobileBankingRq request = new ValidatePasswordMobileBankingRq();
            request.setUserName(UserName);
            request.setPassword(Password);
            request.setCifNo(CifNo);
            SCBAuthenLocator wssl = new SCBAuthenLocator();
            //wssl.setUnLockFeeSoap12EndpointAddress(wsurlvninfogateway);
            wssl.setSCBAuthenSoap12EndpointAddress(wsurlMobileSCBAuthen);
            //SCBAuthenSoap12Stub wss = (SCBAuthenSoap12Stub) wssl.getUnLockFeeSoap12();            
            SCBAuthenSoap12Stub wss = (SCBAuthenSoap12Stub) wssl.getSCBAuthenSoap12();
            wss.setTimeout(40000);// 40giay

            String strRequest = Xml.Marshaller(request);
            String strResult = wss.validatePasswordMobileBanking(strRequest, createSignData(CifNo, UserName));
            //Va =ValidatePasswordMobileBankingRprdRp)   Xml.unMarshaller(EncryptPasswordRp.class, strResult);   

            ValidatePasswordMobileBankingRp response = (ValidatePasswordMobileBankingRp) Xml.unMarshaller(ValidatePasswordMobileBankingRp.class, strResult);
            String strResponse = null;
            if (response != null) {
                strResponse = response.getErrorCode().concat("#").concat(response.getErrorMsg());
                /*
                if (response.getNewPassword()!=null)
                {
                    strResponse= strResponse.concat("#").concat(response.getNewPassword());
                } */
            }
            return strResponse;
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }

    /**
     * ValidatePinMobileBanking
     *
     * @param Password
     * @param UserName
     * @param CifNo
     * @return
     */
    public String ValidatePinMobileBanking(String Password, String UserName, String CifNo) {
        try {
            ValidatePinMobileBankingRq request = new ValidatePinMobileBankingRq();
            request.setUserName(UserName);
            request.setPassword(Password);
            request.setCifNo(CifNo);
            SCBAuthenLocator wssl = new SCBAuthenLocator();
            //wssl.setUnLockFeeSoap12EndpointAddress(wsurlvninfogateway);
            wssl.setSCBAuthenSoap12EndpointAddress(wsurlMobileSCBAuthen);
            //SCBAuthenSoap12Stub wss = (SCBAuthenSoap12Stub) wssl.getUnLockFeeSoap12();            
            SCBAuthenSoap12Stub wss = (SCBAuthenSoap12Stub) wssl.getSCBAuthenSoap12();
            wss.setTimeout(40000);// 40giay

            String strRequest = Xml.Marshaller(request);
            String strResult = wss.validatePinMobileBanking(strRequest, createSignData(CifNo, UserName));
            //Va =ValidatePasswordMobileBankingRprdRp)   Xml.unMarshaller(EncryptPasswordRp.class, strResult);   

            ValidatePinMobileBankingRp response = (ValidatePinMobileBankingRp) Xml.unMarshaller(ValidatePinMobileBankingRp.class, strResult);
            String strResponse = null;
            if (response != null) {
                strResponse = response.getErrorCode().concat("#").concat(response.getErrorMsg());
                /*
                if (response.getNewPassword()!=null)
                {
                    strResponse= strResponse.concat("#").concat(response.getNewPassword());
                } */
            }
            return strResponse;
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e);
            return null;
        }
    }
}
