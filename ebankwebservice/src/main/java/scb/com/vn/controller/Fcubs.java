/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.controller;

import com.ofss.fcubs.gw.ws.types.FCUBSRTService.FCUBSRTServiceLocator;
import com.ofss.fcubs.gw.ws.types.FCUBSRTService.FCUBSRTServiceSEIBindingStub;

//import com.ofss.fcubs.gw.ws.types.FCUBSRTServiceLocator;
//import com.ofss.fcubs.gw.ws.types.FCUBSRTServiceSEIBindingStub;
import com.ofss.fcubs.service.FCUBSRTService.CREATETRANSACTION_IOPK_REQ;
import com.ofss.fcubs.service.FCUBSRTService.CREATETRANSACTION_IOPK_REQFCUBS_BODY;
import com.ofss.fcubs.service.FCUBSRTService.CREATETRANSACTION_IOPK_RES;
import com.ofss.fcubs.service.FCUBSRTService.CREATETRANSACTION_IOPK_RESFCUBS_BODY;
import com.ofss.fcubs.service.FCUBSRTService.FCUBS_HEADERType;
import com.ofss.fcubs.service.FCUBSRTService.MsgStatType;
import com.ofss.fcubs.service.FCUBSRTService.REVERSETRANSACTION_IOPK_REQ;
import com.ofss.fcubs.service.FCUBSRTService.REVERSETRANSACTION_IOPK_REQFCUBS_BODY;
import com.ofss.fcubs.service.FCUBSRTService.REVERSETRANSACTION_IOPK_RES;
import com.ofss.fcubs.service.FCUBSRTService.REVERSETRANSACTION_IOPK_RESFCUBS_BODY;
import com.ofss.fcubs.service.FCUBSRTService.RetailTellerTypeIO;
import com.ofss.fcubs.service.FCUBSRTService.RetailTellerTypePK;
import com.ofss.fcubs.service.FCUBSRTService.UBSCOMPType;
import java.math.BigDecimal;
import org.apache.axis.AxisFault;
import scb.com.vn.utility.Configuration;

import com.ofss.fcubs.service.fcubsftservice.CREATEFTCONTRACTIOPKREQ;
import com.ofss.fcubs.service.fcubsftservice.TaxDetailType;
import com.ofss.fcubs.service.fcubsftservice.TaxMainType;
import com.ofss.fcubs.service.fcubsftservice.TaxRuleDetailType;
import com.ofss.fcubs.service.fcubsftservice.ChargeDetailsType1;
import com.ofss.fcubs.service.fcubsftservice.CREATEFTCONTRACTIOPKRES;
import com.ofss.fcubs.service.fcubsftservice.FTContractIOType;

import java.math.BigInteger;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Document;
import scb.com.vn.dbi.dto.VwCustAccount;
import scb.com.vn.model.CloseAzAccountRq;
import scb.com.vn.model.CreditCardPayRq;
import scb.com.vn.model.FundTransferRq;
import scb.com.vn.model.OpenAzAccountRq;

import scb.com.vn.utility.Helper;
import com.ofss.fcubs.gw.ws.types.FCUBSFTService;
import com.ofss.fcubs.gw.ws.types.FCUBSFTServiceSEI;
/*
import com.ofss.fcubs.gw.ws.types.FCUBSTDService;
import com.ofss.fcubs.gw.ws.types.FCUBSTDServiceSEI;
 */
import com.ofss.fcubs.service.FCUBSRTService.ChgdetsType;
import com.ofss.fcubs.service.FCUBSRTService.MsgDetsType;
import com.ofss.fcubs.service.FCUBSRTService.RetailTellerTypeFull;
import com.ofss.fcubs.service.FCUBSRTService.CREATETRANSACTION_FSFS_REQ;
import com.ofss.fcubs.service.FCUBSRTService.CREATETRANSACTION_FSFS_REQFCUBS_BODY;
import com.ofss.fcubs.service.FCUBSRTService.CREATETRANSACTION_FSFS_RES;
import com.ofss.fcubs.service.FCUBSRTService.CREATETRANSACTION_FSFS_RESFCUBS_BODY;
import com.ofss.fcubs.service.fcubsaccservice.CREATETDCUSTACCFSFSREQ;
import com.ofss.fcubs.service.fcubsaccservice.CREATETDCUSTACCFSFSRES;

import com.ofss.fcubs.service.fcubsaccservice.TDCustAccFullType;
import com.ofss.fcubs.service.fcubsaccservice.TDCustAccFullType.Tdpayoutdetails;
import com.ofss.fcubs.service.fcubstdservice.CREATETDREDEMFSFSREQ;
import com.ofss.fcubs.service.fcubstdservice.CREATETDREDEMFSFSRES;
import com.ofss.fcubs.service.fcubstdservice.TDRedemFullType;
import com.ofss.fcubs.service.fcubstdservice.TDRedemFullType.IctmsTdredmpayoutDetails;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
//import com.ofss.fcubs.service.fcubstdservice.DocTypeRemarks;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import scb.com.vn.common.model.transfer.napas.TransferMoney247DetailReq;
import scb.com.vn.model.FcubsTransferRp;
import scb.com.vn.utility.CommonUtils;

/**
 *
 * @author FICOMBANK
 */
public class Fcubs {

    private static final Logger logger = Logger.getLogger(Fcubs.class);

    String producttransfer = Configuration.getProperty("fcubs.producttransfer.paybill");
    String wsurladdress = Configuration.getProperty("ws.url.fcubs.address");
    String useridfcubs = Configuration.getProperty("fcubs.userid");
    String wsurladdressTD = Configuration.getProperty("ws.url.fcubs.address.TD");
    String wsurladdressFT = Configuration.getProperty("ws.url.fcubs.address.FT");
    String wsurladdressAcc = Configuration.getProperty("ws.url.fcubs.address.Acc");
    String producttransferMobileOut = Configuration.getProperty("fcubs.producttransfer.mobile.OUT");
    String producttransferMobileID = Configuration.getProperty("fcubs.producttransfer.mobile.ID");
    String productpaymentmastercard = Configuration.getProperty("fcubs.productpayment.Mastercard");
    String useridMobile = Configuration.getProperty("fcubs.userid.mobile");
    String GLPaymentMastercard = Configuration.getProperty("fcubs.GLPayment.mastercard");
    String GLPaymentVisacard = Configuration.getProperty("fcubs.GLPayment.visacard");
    String GLPaymentDomestic = Configuration.getProperty("fcubs.GLPayment.domestic");
    String GLPaymentInternalId = Configuration.getProperty("fcubs.GLPayment.internalId");
    String chargeComp = Configuration.getProperty("fcubs.CHGCOMP");
    String MaturityInstr_AutoRoll_Both = "1";
    String MaturityInstr_AutoRoll_Principal = "2";
    String MaturityInstr_Close = "3";
    String MaturityInstr_AutoRoll_Special = "4";
    String MaturityInstr_Close_Principal = "5";

    /**
     *
     * @param accountFrom
     * @param accountTo
     * @param amount
     * @param narative
     * @return
     * @throws RemoteException
     */
    public String transferFCUBS(String accountFrom, String accountTo, BigDecimal amount, String narative) throws RemoteException {
        String brnAccountFrom = CommonUtils.getBranchAccount(accountFrom);
        String brnAccountTo = CommonUtils.getBranchAccount(accountTo);
        return transferFCUBS(producttransfer, useridfcubs, brnAccountFrom, accountFrom, brnAccountTo, accountTo, amount, narative);
    }

    /**
     *
     * @param brnAccountFrom
     * @param accountFrom
     * @param brnAccountTo
     * @param accountTo
     * @param amount
     * @param narative
     * @return
     */
    public String transferFCUBSWithBranch(String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo, BigDecimal amount, String narative) {
        return transferFCUBS(producttransfer, useridfcubs, brnAccountFrom, accountFrom, brnAccountTo, accountTo, amount, narative);
    }

    private String transferFCUBS(String productname, String userid, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, String narative) {
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;

        CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        // CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(userid);
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("ODC1");
        h.setUSERID(userid);
        h.setBRANCH(brnAccountFrom);

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY
        RetailTellerTypeIO rtio = new RetailTellerTypeIO();
//        rtio.setXREF("PBLP1200013304469");
        rtio.setPRD(productname);
        rtio.setBRN(brnAccountFrom);

        // TK CHUYEN
        rtio.setTXNBRN(brnAccountFrom);
        rtio.setTXNACC(accountFrom);
        rtio.setTXNCCY("VND");

        // TK NHAN
        //Helper.writeLogError(this.getClass(), "Tk nhan: " + brnAccountTo);
        rtio.setOFFSETBRN(brnAccountTo);

        rtio.setOFFSETACC(accountTo);
        rtio.setOFFSETCCY("VND");
        //rtio.setOFFSETAMT(amount);

        // SO TIEN
        rtio.setTXNAMT(amount);

        // GHI CHU
        rtio.setNARRATIVE(narative);

        CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        b.setTransactionDetailsIO(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);

        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);

            if (res == null) {
                return null;
            }

            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

            CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if (hResBody.getFCUBS_ERROR_RESP() != null) {
                System.out.println(hResBody.getFCUBS_ERROR_RESP().toString());
                if (hResBody.getFCUBS_ERROR_RESP()[0] != null) {
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());
                }

                return null;
            }
            return hResBody.getTransactionDetailsPK().getFCCREF();

        }
        catch (AxisFault af) {
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                af.printStackTrace();
                return null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    private String getBranchFromAccount(String account) {
        if (account.length() == 9) {
            return "000";
        }
        if (account == null || account == "") {
            return "";
        }
        if (account.length() < 3) {
            return "";
        }

        return account.substring(0, 3);
    }
     */

    //LyDTY added on 13Jan2014

    /**
     *
     * @param productName
     * @param accountFrom
     * @param accountTo
     * @param amount
     * @param narative
     * @return
     * @throws RemoteException
     */
    public String transferFCUBSWithProduct(String productName, String accountFrom, String accountTo, BigDecimal amount, String narative) throws RemoteException {
        //  String brnAccountFrom = getBranchFromAccount(accountFrom);
        //  String brnAccountTo = getBranchFromAccount(accountTo);

        String brnAccountFrom = CommonUtils.getBranchAccount(accountFrom);
        String brnAccountTo = CommonUtils.getBranchAccount(accountTo);

        Helper.writeLog(this.getClass(), Level.INFO, "BEFORE TRANSFER FCUBS: " + accountFrom + "#" + accountTo + "#" + productName + "#" + amount + "#" + narative);
        return transferFCUBS(productName, useridfcubs, brnAccountFrom, accountFrom, brnAccountTo, accountTo, amount, narative);
    }

    //LyDTY added on 13Jan2014

    /**
     *
     * @param productName
     * @param accountFrom
     * @param accountTo
     * @param amount
     * @param narative
     * @param timeout
     * @return
     * @throws RemoteException
     */
    public String transferFCUBSWithProductWithTimeOut(String productName, String accountFrom, String accountTo, BigDecimal amount, String narative, int timeout) throws RemoteException {
        String brnAccountFrom = CommonUtils.getBranchAccount(accountFrom);
        String brnAccountTo = CommonUtils.getBranchAccount(accountTo);
        return transferFCUBSWithTimeOut(productName, useridfcubs, brnAccountFrom, accountFrom, brnAccountTo, accountTo, amount, narative, timeout);
    }

    /**
     *
     * @param productname
     * @param userid
     * @param brnAccountFrom
     * @param accountFrom
     * @param brnAccountTo
     * @param accountTo
     * @param amount
     * @param narative
     * @param timeout
     * @return
     */
    public String transferFCUBSWithTimeOut(String productname, String userid, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, String narative, int timeout) {
                Helper.writeLog(Fcubs.class, org.apache.log4j.Level.INFO, "In ra coi addInfo 0000" + wsurladdress);
        String source ="ODC1";
        if(userid.equals("TAIQUAY"))
        {
            source=userid;
            userid="ODC1";
            
        }
                Helper.writeLog(Fcubs.class, org.apache.log4j.Level.INFO, "In ra coi addInfo 0" + wsurladdress);
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;

        CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        // CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(userid);
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE(source);
        h.setUSERID(userid);
        h.setBRANCH(brnAccountFrom);

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY
        RetailTellerTypeIO rtio = new RetailTellerTypeIO();
//        rtio.setXREF("PBLP1200013304469");
        rtio.setPRD(productname);
        rtio.setBRN(brnAccountFrom);

        // TK CHUYEN
        rtio.setTXNBRN(brnAccountFrom);
        rtio.setTXNACC(accountFrom);
        rtio.setTXNCCY("VND");

        // TK NHAN
        rtio.setOFFSETBRN(brnAccountTo);
        rtio.setOFFSETACC(accountTo);
        rtio.setOFFSETCCY("VND");
        //rtio.setOFFSETAMT(amount);

        // SO TIEN
        rtio.setTXNAMT(amount);

        // GHI CHU
        rtio.setNARRATIVE(narative);

        CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        b.setTransactionDetailsIO(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);
                Helper.writeLog(Fcubs.class, org.apache.log4j.Level.INFO, "In ra coi addInfo 5" + wsurladdress);

        try {
                Helper.writeLog(Fcubs.class, org.apache.log4j.Level.INFO, "In ra coi addInfo 1" + wsurladdress);
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            fcubsstub.setTimeout(timeout * 1000);
                Helper.writeLog(Fcubs.class, org.apache.log4j.Level.INFO, "In ra coi addInfo 2" + wsurladdress);
            CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);
            
                Helper.writeLog(Fcubs.class, org.apache.log4j.Level.INFO, "In ra coi addInfo ress 3");
            if (res == null) {
                return null;
            }

                Helper.writeLog(Fcubs.class, org.apache.log4j.Level.INFO, "In ra coi addInfo 4" + wsurladdress);
            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

            CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if (hResBody.getFCUBS_ERROR_RESP() != null) {
                System.out.println(hResBody.getFCUBS_ERROR_RESP().toString());
                if (hResBody.getFCUBS_ERROR_RESP()[0] != null) {
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());
                }
                return null;
            }
            return hResBody.getTransactionDetailsPK().getFCCREF();

        } catch (AxisFault af) {
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                af.printStackTrace();
                return null;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param fccRef
     * @param timeout
     * @return
     */
    public String revertTransferFCUBS(String fccRef, int timeout) {
        String brnAccountFrom = fccRef.substring(0, 3);
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;

        REVERSETRANSACTION_IOPK_REQ r = new REVERSETRANSACTION_IOPK_REQ();
        // CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID(fccRef);
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(useridfcubs);
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("ODC1");
        h.setUSERID(useridfcubs);
        h.setBRANCH(brnAccountFrom);

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("ReverseTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY
        RetailTellerTypePK rtio = new RetailTellerTypePK();

        rtio.setFCCREF(fccRef);

        REVERSETRANSACTION_IOPK_REQFCUBS_BODY b = new REVERSETRANSACTION_IOPK_REQFCUBS_BODY();

        b.setTransactionDetailsIO(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);

        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            fcubsstub.setTimeout(timeout * 1000);

            REVERSETRANSACTION_IOPK_RES res = fcubsstub.reverseTransactionIO(r);

            if (res == null) {
                return null;
            }

            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

            //REVERSETRANSACTION_IOPK_RESFCUBS_BODY
            REVERSETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();

            if (hResBody.getFCUBS_ERROR_RESP() != null) {
                System.out.println(hResBody.getFCUBS_ERROR_RESP().toString());
                return null;
            }

            return "0";
        } catch (AxisFault af) {
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                af.printStackTrace();
                return null;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
    //mobile banking transfer 

    /**
     *
     * @param productName
     * @param userid
     * @param accountFrom
     * @param accountTo
     * @param amount
     * @param narative
     * @return
     * @throws RemoteException
     */
    public String transferFCUBSWithProductUser(String productName, String userid, String accountFrom, String accountTo, BigDecimal amount, String narative) throws RemoteException {
        String brnAccountFrom = CommonUtils.getBranchAccount(accountFrom);
        String brnAccountTo = CommonUtils.getBranchAccount(accountTo);
        Helper.writeLogError(this.getClass(),"FromAccount:"+accountFrom+" ToAccount:"+ accountTo+" amount:"+amount+" productName:"+productName);
        return transferFCUBS(productName, userid, brnAccountFrom, accountFrom, brnAccountTo, accountTo, amount, narative);
    }

    /**
     *
     * @param productname
     * @param brnAccountFrom
     * @param accountFrom
     * @param brnAccountTo
     * @param accountTo
     * @param amount
     * @param charge
     * @param tax
     * @param narative
     * @return
     * @throws RemoteException
     */
    public String transferFCUBSWithProductCharge(String productname, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, BigDecimal charge, BigDecimal tax, String narative) throws RemoteException {

        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;
        //CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        //h.setSOURCE_USERID(useridfcubs);
        h.setSOURCE_USERID(useridMobile);
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("FCAT");
        //h.setUSERID("ODC1");
        h.setUSERID(useridMobile);

        //h.setBRANCH(brnAccountFrom);  
        h.setBRANCH(brnAccountTo);

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY        
        RetailTellerTypeFull rtio = new RetailTellerTypeFull();
        rtio.setXREF("MB" + String.valueOf(System.currentTimeMillis()));
        rtio.setROUTECODE("VND");
        //set for product

        rtio.setPRD(productname);
        //rtio.setBRN(brnAccountFrom);
        rtio.setBRN(brnAccountTo);

        // TK nhan
        rtio.setTXNBRN(brnAccountFrom);
        rtio.setTXNACC(accountFrom);
        rtio.setTXNCCY("VND");
        //rtio.setc

        // TK chuyen        
        rtio.setOFFSETBRN(brnAccountTo);
        rtio.setOFFSETACC(accountTo);
        rtio.setOFFSETCCY("VND");

        // SO TIEN
        rtio.setTXNAMT(charge);

        // GHI CHU
        rtio.setNARRATIVE(narative);

        //add nang cap core for FS - charge
        rtio.setFT(com.ofss.fcubs.service.FCUBSRTService.YesNoType.Y);
        //rtio.SETCHGC
        //charge
        ChgdetsType[] chargeDetails = new ChgdetsType[1];

        ChgdetsType chrg1 = new ChgdetsType();
        chrg1.setCHGCOMP("THU PHI DICH VU SMS");
        if (tax != null) {
            chrg1.setCHGAMT(tax);
        }
        chargeDetails[0] = chrg1;
        rtio.setChargeDetails(chargeDetails);

        //CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        CREATETRANSACTION_FSFS_REQFCUBS_BODY b = new CREATETRANSACTION_FSFS_REQFCUBS_BODY();

        //b.setTransactionDetailsIO(rtio);
        b.setTransactionDetails(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);

        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            fcubsstub.setTimeout(30 * 1000);
            //CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);
            CREATETRANSACTION_FSFS_RES res = fcubsstub.createTransactionFS(r);

            if (res == null) {
                return null;
            }

            CREATETRANSACTION_FSFS_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            /*
             if (hResBody.getFCUBS_ERROR_RESP() != null) {
             Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP().toString());                //System.out.println(hResBody.getFCUBS_ERROR_RESP().toString());                                
             return null;
             }*/
            if (hResBody.getFCUBS_ERROR_RESP() != null) {
                Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP().toString());
                //System.out.println(hResBody.getFCUBSERRORRESP().toString());
                if (hResBody.getFCUBS_ERROR_RESP()[0] != null) {
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());
                }
                return null;
            }

            return hResBody.getTransactionDetails().getFCCREF();

        }
        catch (AxisFault af) {
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                af.printStackTrace();
                return null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //chuyen khoan ngoai he thong

    /**
     *
     * @param fundTransferRq
     * @return
     * @throws RemoteException
     */
    public String transferFCUBSDomesticWithProductAndCharge(FundTransferRq fundTransferRq) throws RemoteException {

        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;
        //CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        //h.setSOURCE_USERID(useridfcubs);
        h.setSOURCE_USERID(fundTransferRq.getUserId());
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("FCAT");

        //h.setUSERID("ODC1");
        //h.setUSERID(useridfcubs);
        h.setUSERID(fundTransferRq.getUserId());

        //h.setBRANCH(fromAccount.getBranchCode());
        h.setBRANCH(CommonUtils.getBranchAccount(fundTransferRq.getFromAccount()));

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY        
        RetailTellerTypeFull rtio = new RetailTellerTypeFull();
        rtio.setXREF("MB" + String.valueOf(System.currentTimeMillis()));
        rtio.setROUTECODE("VND");

        rtio.setPRD(producttransferMobileOut);
        rtio.setBRN(CommonUtils.getBranchAccount(fundTransferRq.getFromAccount()));

        /*
        // TK CHUYEN
        rtio.setTXNBRN("000");        
        rtio.setTXNACC(GLPaymentDomestic);
        rtio.setTXNCCY("VND");        

        // TK NHAN        
        rtio.setOFFSETBRN(getBranchFromAccount(fundTransferRq.getFromAccount()));
        rtio.setOFFSETACC(fundTransferRq.getFromAccount());
        rtio.setOFFSETCCY("VND");
         */
        rtio.setTXNBRN(CommonUtils.getBranchAccount(fundTransferRq.getFromAccount()));
        rtio.setTXNACC(fundTransferRq.getFromAccount());
        rtio.setTXNCCY("VND");

        // TK NHAN        
        rtio.setOFFSETBRN("000");
        rtio.setOFFSETACC(GLPaymentDomestic);
        rtio.setOFFSETCCY("VND");

        // SO TIEN
        rtio.setTXNAMT(new BigDecimal(fundTransferRq.getAmount()));

        // GHI CHU
        rtio.setNARRATIVE(fundTransferRq.getRemark());

        //charge
        ChgdetsType[] chargeDetails = new ChgdetsType[2];

        ChgdetsType chrg1 = new ChgdetsType();
        chrg1.setCHGCOMP("PHI CHUYEN TIEN EBANKING");
        if (fundTransferRq.getTxnFee() != null) {
            chrg1.setCHGAMT(new BigDecimal(fundTransferRq.getTxnFee()));
        }

        ChgdetsType chrg2 = new ChgdetsType();
        if (fundTransferRq.getTxnTax() != null) {
            chrg2.setCHGAMT(new BigDecimal(fundTransferRq.getTxnTax()));
        }
        chrg2.setCHGCOMP("VAT CHUYEN TIEN EBANKING");

        chargeDetails[0] = chrg1;
        chargeDetails[1] = chrg2;
        rtio.setChargeDetails(chargeDetails);

        //thong tin nguoi thu huong
        rtio.setUTLBNF1(fundTransferRq.getToAccount());
        //cut bene name to 35 char for utl2 and continue for utl3
        if (fundTransferRq.getBeneName().length() <= 35) {
            rtio.setUTLBNF2(fundTransferRq.getBeneName().trim());
        } else {
            String oldString35First = fundTransferRq.getBeneName().substring(0, 35);
            int oldSpace35 = oldString35First.lastIndexOf(" ");
            boolean hasSpace = false;
            if (oldSpace35 == -1 || fundTransferRq.getBeneName().substring(35, 36).equals(" ")) {
                oldSpace35 = 35;
            } else {
                hasSpace = true;
            }

            rtio.setUTLBNF2(fundTransferRq.getBeneName().substring(0, oldSpace35).trim());
            if (hasSpace) {
                oldSpace35 += 1;
                hasSpace = false;
            }
            String continueString = fundTransferRq.getBeneName().substring(oldSpace35).trim();
            if (continueString != null && continueString.length() > 0) {
                if (continueString.length() <= 35) {
                    rtio.setUTLBNF3(continueString);
                } else {
                    oldString35First = continueString.substring(0, 35);
                    oldSpace35 = oldString35First.lastIndexOf(" ");
                    if (oldSpace35 == -1 || continueString.substring(35, 36).equals(" ")) {
                        oldSpace35 = 35;
                    } else {
                        hasSpace = true;
                    }
                    rtio.setUTLBNF3(continueString.substring(0, oldSpace35));
                    if (hasSpace) {
                        oldSpace35 += 1;
                    }
                    continueString = continueString.substring(oldSpace35).trim();
                    if (continueString.length() <= 35) {
                        rtio.setUTLBNF4(continueString);
                    } else {
                        rtio.setUTLBNF4(continueString.substring(0, 35));
                    }
                }
            }
            /*
            
             rtio.setUTLBNF2(fundTransferRq.getBeneName().substring(0, 35));                        
             if (fundTransferRq.getBeneName().length() > 70) {
             rtio.setUTLBNF3(fundTransferRq.getBeneName().substring(35, 70));
             if (fundTransferRq.getBeneName().length() > 105) {
             rtio.setUTLBNF4(fundTransferRq.getBeneName().substring(70, 105));
             } else {
             rtio.setUTLBNF4(fundTransferRq.getBeneName().substring(70));
             }
             } else {
             rtio.setUTLBNF3(fundTransferRq.getBeneName().substring(35));
             }
             */
        }
        //
        /*
         if (fundTransferRq.getIDCityCode() != null && !fundTransferRq.getIDCityCode().isEmpty()) {
         rtio.setUTLBNF3(fundTransferRq.getIDCityCode());
         }*/
        if (fundTransferRq.getIDOpenDate() != null && !fundTransferRq.getIDOpenDate().isEmpty()) {
            try {
                //change format date from yyyyMMdd to yyyy-MM-dd                
                DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
                Date date = formatter.parse(fundTransferRq.getIDOpenDate());
                rtio.setUTLBNF4(formatter1.format(date));
                if (fundTransferRq.getIDCityCode() != null && !fundTransferRq.getIDCityCode().isEmpty()) {
                    rtio.setUTLBNF4(rtio.getUTLBNF4().concat(fundTransferRq.getIDCityCode()));
                }
            } catch (Exception e) {
                Helper.writeLogError(this.getClass(), e);
                return null;
            }
        }

        //thong tin nguoi gui
        MsgDetsType msgType = new MsgDetsType();
        msgType.setORDCUST1(fundTransferRq.getFromAccount());

        List customer = Helper.getDBI().getCustomerInfoFCC(fundTransferRq.getCifNo());
        if (customer != null && !customer.isEmpty()) {
            HashMap hm_cust = (HashMap) customer.get(0);
            msgType.setORDCUST2(hm_cust.get("namfirst").toString());
        }

        msgType.setORDCUST3(fundTransferRq.getCifNo());

        //thong tin ngan hang thu huong
        List benebank = Helper.getDBI().getBeneBank(fundTransferRq.getBankCode());
        if (benebank != null && !benebank.isEmpty()) {
            HashMap hm_bene = (HashMap) benebank.get(0);
            msgType.setECONT1(hm_bene.get("bankname").toString());
            rtio.setREMACCOUNT(fundTransferRq.getBankCode());
        }

        List bankCity = Helper.getDBI().getBankCity(fundTransferRq.getCityCode());
        if (bankCity != null && !bankCity.isEmpty()) {
            HashMap hm_city = (HashMap) bankCity.get(0);
            msgType.setECONT2(hm_city.get("nam_city_full").toString());

        }
        msgType.setECONT3(fundTransferRq.getBranchName());
        msgType.setVDATECR("");
        msgType.setVDATEDR("");
        rtio.setMessageDetails(msgType);

        CREATETRANSACTION_FSFS_REQFCUBS_BODY b = new CREATETRANSACTION_FSFS_REQFCUBS_BODY();

        b.setTransactionDetails(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);

        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            //CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);
            CREATETRANSACTION_FSFS_RES res = fcubsstub.createTransactionFS(r);

            if (res == null) {
                return null;
            }

            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

            CREATETRANSACTION_FSFS_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            //CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if (hResBody.getFCUBS_ERROR_RESP() != null) {
                Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP().toString());
                //System.out.println(hResBody.getFCUBSERRORRESP().toString());
                if (hResBody.getFCUBS_ERROR_RESP()[0] != null) {
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());
                }
                return null;
            }
            return hResBody.getTransactionDetails().getFCCREF();

        }
        catch (AxisFault af) {
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                af.printStackTrace();
                return null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     *
     * @param openAzAccountRq
     * @return
     * @throws RemoteException
     */
    public String OpenTD(OpenAzAccountRq openAzAccountRq) throws RemoteException {
        try {
            CREATETDCUSTACCFSFSREQ r = new CREATETDCUSTACCFSFSREQ();
            com.ofss.fcubs.service.fcubsaccservice.FCUBSHEADERType h = new com.ofss.fcubs.service.fcubsaccservice.FCUBSHEADERType();
            // h.setMSGID("");
            // h.setCORRELID("");
            h.setSOURCEOPERATION("CreateTDCustAcc");
            // h.setSOURCEUSERID(openAzAccountRq.getUserId());
            h.setUBSCOMP(com.ofss.fcubs.service.fcubsaccservice.UBSCOMPType.FCUBS);
            h.setSOURCE("FCAT");
            //h.setUSERID("ODC1");
            h.setUSERID(openAzAccountRq.getUserId());

            h.setBRANCH(CommonUtils.getBranchAccount(openAzAccountRq.getFromAccount()));

            //h.setMODULEID("IC");
            h.setSERVICE("FCUBSAccService");
            h.setOPERATION("CreateTDCustAcc");
            h.setMSGSTAT(com.ofss.fcubs.service.fcubsaccservice.MsgStatType.SUCCESS);

            //BODY                          
            TDCustAccFullType tdAccount = new TDCustAccFullType();
            //XREF        
            //tdAccount.setXREF("MB" + String.valueOf(System.currentTimeMillis()));

            tdAccount.setBRN(CommonUtils.getBranchAccount(openAzAccountRq.getFromAccount()));
            tdAccount.setCUSTNO(openAzAccountRq.getCifNo());
            tdAccount.setACCLS(openAzAccountRq.getAccountTypeCode());
            tdAccount.setTDCCY("VND");
            tdAccount.setADESC("Account opened from Mobile Banking");
            tdAccount.setACCTYPE("S");
            tdAccount.setTERMACNO("DUMMY");

            if (!openAzAccountRq.getMaturityInstr().equals(MaturityInstr_AutoRoll_Both) && !openAzAccountRq.getMaturityInstr().equals(MaturityInstr_Close) && openAzAccountRq.getInterestAcc() != null && !openAzAccountRq.getInterestAcc().isEmpty()) {
                com.ofss.fcubs.service.fcubsaccservice.TDCustAccFullType.Intdetails intDetail = new com.ofss.fcubs.service.fcubsaccservice.TDCustAccFullType.Intdetails();
                intDetail.setBOOKACCBRN(CommonUtils.getBranchAccount(openAzAccountRq.getInterestAcc()));
                intDetail.setBOOKACC(openAzAccountRq.getInterestAcc());
                tdAccount.setIntdetails(intDetail);
            }

            //detail TD
            TDCustAccFullType.Tddetails tdDetail = new TDCustAccFullType.Tddetails();
            tdDetail.setINITIALTDAMT(new BigDecimal(openAzAccountRq.getAmount()));

            //change format date
            String s = openAzAccountRq.getMatDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(s);
            GregorianCalendar gregorianCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
            gregorianCalendar.setTime(date);
            //XMLGregorianCalendar result  = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

            XMLGregorianCalendar result = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH) + 1, gregorianCalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);

            tdDetail.setMATDT(result);

            if (openAzAccountRq.getMaturityInstr().equals(MaturityInstr_AutoRoll_Both)) {
                tdDetail.setAUTOROLL("Y");
                tdDetail.setROLLTYPE("I");
                tdDetail.setCLONMAT("N");
            } else if (openAzAccountRq.getMaturityInstr().equals(MaturityInstr_AutoRoll_Principal)) {
                tdDetail.setAUTOROLL("Y");
                tdDetail.setROLLTYPE("P");
                tdDetail.setCLONMAT("N");

            } else if (openAzAccountRq.getMaturityInstr().equals(MaturityInstr_Close) || openAzAccountRq.getMaturityInstr().equals(MaturityInstr_Close_Principal)) {
                tdDetail.setCLONMAT("Y");
                tdDetail.setAUTOROLL("N");
            } else if (openAzAccountRq.getMaturityInstr().equals(MaturityInstr_AutoRoll_Special)) {
                tdDetail.setAUTOROLL("Y");
                tdDetail.setROLLTYPE("S");
                tdDetail.setCLONMAT("N");
                tdDetail.setROLLAMT(new BigDecimal(openAzAccountRq.getRolloverAmount()));
            }

            //set for payin TD
            TDCustAccFullType.Tdpayindetails payin = new TDCustAccFullType.Tdpayindetails();

            payin.setOFFBRN(CommonUtils.getBranchAccount(openAzAccountRq.getFromAccount()));
            payin.setOFFSACC(openAzAccountRq.getFromAccount());
            payin.setPERCENTAGE(BigDecimal.valueOf(100));
            tdAccount.getTdpayindetails().add(payin);

            //set for payout TD
            // if (openAzAccountRq.getMaturityInstr().equals(MaturityInstr_AutoRoll_Principal) && tdAccount.getIntdetails()!=null)
            if (openAzAccountRq.getMaturityInstr().equals(MaturityInstr_Close)
                    || openAzAccountRq.getMaturityInstr().equals(MaturityInstr_AutoRoll_Special)) {
                if (openAzAccountRq.getInterestAcc() == null) {
                    Tdpayoutdetails payout = new Tdpayoutdetails();
                    payout.setOFFBRN(CommonUtils.getBranchAccount(openAzAccountRq.getNominateAcc()));
                    payout.setOFFSACC(openAzAccountRq.getNominateAcc());
                    payout.setPAYOUTTYPE("S");
                    payout.setPERCENTAGE(BigDecimal.valueOf(100));
                    tdAccount.getTdpayoutdetails().add(payout);
                } else {
                    Tdpayoutdetails payout = new Tdpayoutdetails();
                    payout.setOFFBRN(CommonUtils.getBranchAccount(openAzAccountRq.getInterestAcc()));
                    payout.setOFFSACC(openAzAccountRq.getNominateAcc());
                    payout.setPAYOUTTYPE("S");
                    payout.setPERCENTAGE(BigDecimal.valueOf(100));
                    tdAccount.getTdpayoutdetails().add(payout);
                }
            } else if (openAzAccountRq.getMaturityInstr().equals(MaturityInstr_Close_Principal)) {
                Tdpayoutdetails payout = new Tdpayoutdetails();
                payout.setOFFBRN(CommonUtils.getBranchAccount(openAzAccountRq.getNominateAcc()));
                payout.setOFFSACC(openAzAccountRq.getNominateAcc());
                payout.setPAYOUTTYPE("S");
                payout.setPERCENTAGE(BigDecimal.valueOf(100));
                tdAccount.getTdpayoutdetails().add(payout);

                com.ofss.fcubs.service.fcubsaccservice.TDCustAccFullType.Intdetails intDetail = new com.ofss.fcubs.service.fcubsaccservice.TDCustAccFullType.Intdetails();
                intDetail.setBOOKACCBRN(CommonUtils.getBranchAccount(openAzAccountRq.getNominateAcc()));
                intDetail.setBOOKACC(openAzAccountRq.getNominateAcc());
                tdAccount.setIntdetails(intDetail);
            }

            tdAccount.setTddetails(tdDetail);

            CREATETDCUSTACCFSFSREQ.FCUBSBODY b = new CREATETDCUSTACCFSFSREQ.FCUBSBODY();
            b.setCustAccountFull(tdAccount);
            r.setFCUBSHEADER(h);
            r.setFCUBSBODY(b);

            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            //Send SOAP Message to SOAP Server            
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            //envelope.addNamespaceDeclaration("sam", "http://samples.axis2.techdive.in");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().newDocument();
            // SOAP Body
            JAXBContext jaxbContext = JAXBContext.newInstance(CREATETDCUSTACCFSFSREQ.class);

            jaxbContext.createMarshaller().marshal(r, doc);

            SOAPBody soapBody = envelope.getBody();
            soapBody.addDocument(doc);
            soapMessage.saveChanges();

            //soapMessage.writeTo(System.out);
            SOAPMessage soapResponse = soapConnection.call(soapMessage, wsurladdressAcc);

            SOAPPart soapPartRes = soapResponse.getSOAPPart();
            // SOAP Envelope
            SOAPEnvelope envelopeRes = soapPartRes.getEnvelope();

            SOAPBody soapBodyRes = envelopeRes.getBody();

            JAXBContext jaxbContextRes = JAXBContext.newInstance(CREATETDCUSTACCFSFSRES.class);
            CREATETDCUSTACCFSFSRES res = (CREATETDCUSTACCFSFSRES) jaxbContextRes.createUnmarshaller().unmarshal(soapBodyRes.getFirstChild());
            //res.getFCUBSBODY().getCustAccount().
            if (res == null) {
                return null;
            }
            //com.ofss.fcubs.service.fcubsaccservice.FCUBSHEADERType hResHeader = res.getFCUBSHEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());                        
            CREATETDCUSTACCFSFSRES.FCUBSBODY hResBody = res.getFCUBSBODY();

            if (hResBody.getFCUBSERRORRESP() != null && !hResBody.getFCUBSERRORRESP().isEmpty()) {
                Helper.writeLogError(this.getClass(), hResBody.getFCUBSERRORRESP().get(0).getERROR().get(0).getECODE());
                Helper.writeLogError(this.getClass(), hResBody.getFCUBSERRORRESP().get(0).getERROR().get(0).getEDESC());
                return null;
            }
            tdAccount = hResBody.getCustAccountFull();
            return tdAccount.getTERMACNO();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     *
     * @param closeAzAccountRq
     * @return
     * @throws RemoteException
     */
    public String CloseTD(CloseAzAccountRq closeAzAccountRq) throws RemoteException {

        //FCUBSTDServiceLocator fcubsl = new FCUBSTDServiceLocator();
        //FCUBSTDServiceSEIBindingStub fcubsstub;
        CREATETDREDEMFSFSREQ r = new CREATETDREDEMFSFSREQ();
        com.ofss.fcubs.service.fcubstdservice.FCUBSHEADERType h = new com.ofss.fcubs.service.fcubstdservice.FCUBSHEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCEOPERATION("CreateTDRedem");
        h.setSOURCEUSERID(closeAzAccountRq.getUserId());
        h.setUBSCOMP(com.ofss.fcubs.service.fcubstdservice.UBSCOMPType.FCUBS);
        h.setSOURCE("FCAT");
        //h.setUSERID("ODC2");
        h.setUSERID(closeAzAccountRq.getUserId());

        //h.setBRANCH(CommonUtils.getBranchAccount(closeAzAccountRq.getAccountNo()));
        //thong tin tk tiet kiem
        /*
        List tdAccount = Helper.getDBI().getAccountTD(closeAzAccountRq.getAccountNo());
        if (tdAccount != null && !tdAccount.isEmpty()) {
            HashMap hm_bene = (HashMap) tdAccount.get(0);
            h.setBRANCH(hm_bene.get("codbranch").toString());
        } else {
            Helper.writeLogError(this.getClass(), "Cannot found branch of TD");
            return null;
        }*/
        h.setBRANCH(closeAzAccountRq.getBranchCode());

        h.setMODULEID("");
        h.setSERVICE("FCUBSTDService");
        h.setOPERATION("CreateTDRedem");
        h.setMSGSTAT(com.ofss.fcubs.service.fcubstdservice.MsgStatType.SUCCESS);

        //for get xref
        h.setDESTINATION("FCUBS");

        TDRedemFullType tdRedemptionFull = new TDRedemFullType();
        //IcdredmnFullType tdRedemptionFull = new IcdredmnFullType();
        //tdRedemptionFull.setBRNCODE(CommonUtils.getBranchAccount(closeAzAccountRq.getAccountNo()));
        tdRedemptionFull.setACBRN(h.getBRANCH());
        tdRedemptionFull.setTERMACNO(closeAzAccountRq.getAccountNo());
        tdRedemptionFull.setACTIONFLAG("R");
        tdRedemptionFull.setREDEMMODE("N");
        //tdRedemptionFull.setREDEMPTIONAMT(BigDecimal.valueOf(1000000));
        //tdRedemptionFull.setXREF("MB" + String.valueOf(System.currentTimeMillis()));

        IctmsTdredmpayoutDetails tdredmpayoutDetail = new IctmsTdredmpayoutDetails();
        tdredmpayoutDetail.setPAYOUTTYPE("S");
        tdredmpayoutDetail.setPERCENTAGE(BigDecimal.valueOf(100));
        tdredmpayoutDetail.setOFFSETBRN(CommonUtils.getBranchAccount(closeAzAccountRq.getNominateAcc()));
        tdredmpayoutDetail.setOFFSETACC(closeAzAccountRq.getNominateAcc());

        tdRedemptionFull.getIctmsTdredmpayoutDetails().add(tdredmpayoutDetail);

        if (closeAzAccountRq.getRedemptionMode().endsWith("Y")) {
            tdRedemptionFull.setREDEMMODE("Y");
            tdRedemptionFull.setREDEMPTIONAMT(new BigDecimal(closeAzAccountRq.getRedemptionAmount()));
        }

        CREATETDREDEMFSFSREQ.FCUBSBODY b = new CREATETDREDEMFSFSREQ.FCUBSBODY();
        b.setIctmsTdredmpayoutMasterFull(tdRedemptionFull);

        r.setFCUBSHEADER(h);
        r.setFCUBSBODY(b);

        try {

            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            //Send SOAP Message to SOAP Server            
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            //envelope.addNamespaceDeclaration("sam", "http://samples.axis2.techdive.in");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().newDocument();
            // SOAP Body
            JAXBContext jaxbContext = JAXBContext.newInstance(CREATETDREDEMFSFSREQ.class);

            jaxbContext.createMarshaller().marshal(r, doc);

            SOAPBody soapBody = envelope.getBody();
            soapBody.addDocument(doc);
            soapMessage.saveChanges();
            SOAPMessage soapResponse = soapConnection.call(soapMessage, wsurladdressTD);

            SOAPPart soapPartRes = soapResponse.getSOAPPart();
            // SOAP Envelope
            SOAPEnvelope envelopeRes = soapPartRes.getEnvelope();

            SOAPBody soapBodyRes = envelopeRes.getBody();

            JAXBContext jaxbContextRes = JAXBContext.newInstance(CREATETDREDEMFSFSRES.class);
            CREATETDREDEMFSFSRES res = (CREATETDREDEMFSFSRES) jaxbContextRes.createUnmarshaller().unmarshal(soapBodyRes.getFirstChild());

            if (res == null) {
                return null;
            }
            com.ofss.fcubs.service.fcubstdservice.FCUBSHEADERType hResHeader = res.getFCUBSHEADER();
            CREATETDREDEMFSFSRES.FCUBSBODY hResBody = res.getFCUBSBODY();
            if (hResBody.getFCUBSERRORRESP() != null && !hResBody.getFCUBSERRORRESP().isEmpty()) {
                Helper.writeLogError(this.getClass(), hResBody.getFCUBSERRORRESP().get(0).getERROR().get(0).getECODE());
                Helper.writeLogError(this.getClass(), hResBody.getFCUBSERRORRESP().get(0).getERROR().get(0).getEDESC());
                return null;
            }
            TDRedemFullType tdRedemption = hResBody.getIctmsTdredmpayoutMasterFull();

            return tdRedemption.getREDEMREFNO();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //chuyen khoan trong he thong bang cmnd

    /**
     *
     * @param fundTransferRq
     * @return
     */
    public String transferFCUBSInternalID(FundTransferRq fundTransferRq) {
        try {
            CREATEFTCONTRACTIOPKREQ r = new CREATEFTCONTRACTIOPKREQ();
            com.ofss.fcubs.service.fcubsftservice.FCUBSHEADERType h = new com.ofss.fcubs.service.fcubsftservice.FCUBSHEADERType();
            h.setMSGID("");
            h.setCORRELID("");
            h.setSOURCEOPERATION("CreateFTContract");
            h.setSOURCEUSERID(fundTransferRq.getUserId());
            h.setUBSCOMP(com.ofss.fcubs.service.fcubsftservice.UBSCOMPType.FCUBS);
            h.setSOURCE("FCAT");
            //h.setUSERID(useridfcubs);
            h.setUSERID(fundTransferRq.getUserId());
            //h.setUSERID("ODC1");
            h.setBRANCH(CommonUtils.getBranchAccount(fundTransferRq.getFromAccount()));

            h.setMODULEID("FT");
            h.setSERVICE("FCUBSFTService");
            h.setOPERATION("CreateFTContract");
            h.setMSGSTAT(com.ofss.fcubs.service.fcubsftservice.MsgStatType.SUCCESS);

            //BODY
            FTContractIOType contractDetailsIO = new FTContractIOType();

            contractDetailsIO.setPROD(producttransferMobileID);
            //contractDetailsIO.setPROD("EBCM");
            contractDetailsIO.setXREF("MB" + String.valueOf(System.currentTimeMillis()));
            contractDetailsIO.setBNKOPRCD("CRED");
            contractDetailsIO.setCHGWHOM("O");
            contractDetailsIO.setMSGASOF("C");
            contractDetailsIO.setRTASOF("N");
            contractDetailsIO.setACTGASOF("M");
            contractDetailsIO.setOVDOVRDRFT("N");
            contractDetailsIO.setREMITMSG("N");

            // TK CHUYEN
            contractDetailsIO.setDRACCBRN(CommonUtils.getBranchAccount(fundTransferRq.getFromAccount()));
            contractDetailsIO.setDRACC(fundTransferRq.getFromAccount());
            contractDetailsIO.setDRCCY("VND");

            // TK NHAN            
            contractDetailsIO.setCRACCBRN(fundTransferRq.getBranchCode());
            //contractDetailsIO.setCRACC("454001001");
            contractDetailsIO.setCRACC(GLPaymentInternalId);
            contractDetailsIO.setCRCCY("VND");

            // SO TIEN 
            contractDetailsIO.setDRAMT(new BigDecimal(fundTransferRq.getAmount()));
            contractDetailsIO.setCRAMT(new BigDecimal(fundTransferRq.getAmount()));

            //thong tin nguoi thu huong
            contractDetailsIO.setULTBEN1(fundTransferRq.getToAccount());
            contractDetailsIO.setULTBEN2(fundTransferRq.getBeneName());
            //contractDetailsIO.setULTBEN3(fundTransferRq.getIDOpenDate());

            if (fundTransferRq.getIDOpenDate() != null && !fundTransferRq.getIDOpenDate().isEmpty()) {
                try {
                    //change format date from yyyyMMdd to yyyy-MM-dd                
                    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                    DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = formatter.parse(fundTransferRq.getIDOpenDate());
                    fundTransferRq.setIDOpenDate(formatter1.format(date));
                    contractDetailsIO.setULTBEN3(fundTransferRq.getIDOpenDate());
                } catch (Exception e) {
                    Helper.writeLogError(this.getClass(), e);
                    return null;
                }
            }

            contractDetailsIO.setULTBEN4(fundTransferRq.getIDCityCode());

            String narrative = "";

            //thong tin nguoi gui
            contractDetailsIO.setBYORDOF1("/" + fundTransferRq.getFromAccount());
            List customer = Helper.getDBI().getCustomerInfoFCC(fundTransferRq.getCifNo());
            if (customer != null && !customer.isEmpty()) {
                HashMap hm_cust = (HashMap) customer.get(0);
                contractDetailsIO.setBYORDOF2(hm_cust.get("namfirst").toString());
                contractDetailsIO.setBYORDOF3(hm_cust.get("addr1").toString());
                narrative = narrative.concat(contractDetailsIO.getBYORDOF2());
            }

            // GHI CHU: SUA DIEN GIAI: A CT CHO B, CMND:, CN:, NC:.             
            //contractDetailsIO.setREMARKS(fundTransferRq.getRemark());
            narrative = narrative.concat(" CT CHO ").concat(fundTransferRq.getBeneName())
                    .concat(", CMND: ").concat(fundTransferRq.getToAccount())
                    .concat(", NC: ").concat(fundTransferRq.getIDOpenDate());
            /*
             List bankCity = Helper.getDBI().getBankCity(fundTransferRq.getIDCityCode());
             if (bankCity != null && !bankCity.isEmpty()) {
             HashMap hm_city = (HashMap) bankCity.get(0);
             narrative = narrative.concat(", NC: ").concat(hm_city.get("nam_city_full").toString()).concat(". ");

             }
             */
            if (fundTransferRq.getIDCityCode() != null && !fundTransferRq.getIDCityCode().isEmpty()) {
                narrative = narrative.concat(", NC: ").concat(fundTransferRq.getIDCityCode()).concat(". ");
            }

            narrative = narrative.concat(fundTransferRq.getRemark());
            if (narrative.length() > 225) {
                narrative = narrative.substring(0, 225);
            }
            contractDetailsIO.setREMARKS(narrative);

            ChargeDetailsType1 chargeDetailType = new ChargeDetailsType1();
            //chargeDetailType.setCHGCOMP("SW_IN1");
            chargeDetailType.setCHGCOMP(chargeComp);
            chargeDetailType.setEVENT("BOOK");
            chargeDetailType.setCHGCCY("VND");
            chargeDetailType.setCHGAMT(new BigDecimal(fundTransferRq.getTxnFee()));
            chargeDetailType.setWAIVER("N");
            contractDetailsIO.getChargeDetails().add(chargeDetailType);

            //charge Taxdetails
            TaxDetailType taxDetailType = new TaxDetailType();
            taxDetailType.setTAXRULE("TA0039");
            //taxDetailType.setBASAMTTAG("SW_IN1");
            taxDetailType.setBASAMTTAG(chargeComp);
            taxDetailType.setCOMPEVT("INIT");
            taxDetailType.setWAIVER("N");

            TaxRuleDetailType taxruleDetail = new TaxRuleDetailType();
            taxruleDetail.setESN(BigInteger.valueOf(1));
            taxruleDetail.setTAXAMT(new BigDecimal(fundTransferRq.getTxnTax()));
            taxruleDetail.setTAXCCY("VND");

            taxDetailType.getTaxruleDetail().add(taxruleDetail);
            contractDetailsIO.getTaxdetails().add(taxDetailType);

            //Taxdetails-Main
            TaxMainType taxdetailsMain = new TaxMainType();
            taxdetailsMain.setSCHEME("EBCM01");
            taxdetailsMain.setWAIVER("n");
            taxdetailsMain.setSCHEMEDESC("VAT CHUYEN TIEN CMND EBANKING");
            contractDetailsIO.getTaxdetailsMain().add(taxdetailsMain);

            //FORMAT value date  COMMENT TEMP HERE
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(new Date());
            XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);

            contractDetailsIO.setDRVDT(xmlDate);
            contractDetailsIO.setCRVDT(xmlDate);

            CREATEFTCONTRACTIOPKREQ.FCUBSBODY b = new CREATEFTCONTRACTIOPKREQ.FCUBSBODY();
            //b.setTransactionDetailsIO(rtio);
            b.setContractDetailsIO(contractDetailsIO);

            r.setFCUBSHEADER(h);
            r.setFCUBSBODY(b);

            URL url = new URL(wsurladdressFT);
            FCUBSFTService fcubsl = new FCUBSFTService(url);
            FCUBSFTServiceSEI fcubsstub = fcubsl.getFCUBSFTServiceSEI();
            CREATEFTCONTRACTIOPKRES res = fcubsstub.createFTContractIO(r);

            if (res == null) {
                return null;
            }
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());
            CREATEFTCONTRACTIOPKRES.FCUBSBODY hResBody = res.getFCUBSBODY();
            //CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if (hResBody.getFCUBSERRORRESP() != null && !hResBody.getFCUBSERRORRESP().isEmpty()) {
                System.out.println(hResBody.getFCUBSERRORRESP().toString());
                if (hResBody.getFCUBSERRORRESP() != null && !hResBody.getFCUBSERRORRESP().isEmpty()) {
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBSERRORRESP().get(0).getERROR().get(0).getECODE());
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBSERRORRESP().get(0).getERROR().get(0).getEDESC());
                }
                return null;
            }
            return hResBody.getContractDetails().getFCCREF();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param creditCardPayRq
     * @return
     * @throws RemoteException
     */
    public String paymentMatercard(CreditCardPayRq creditCardPayRq) throws RemoteException {

        VwCustAccount fromAccount = Helper.getDBI().getCustAccountFcdbByAccountNo(creditCardPayRq.getFromAccount());
        List accountMC = Helper.getDBI().getMaterCardDetailByCardno(creditCardPayRq.getCardNo());

        if (accountMC == null && accountMC.isEmpty()) {
            return null;
        }
        HashMap hm_accountMC = (HashMap) accountMC.get(0);
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;
        //CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        //h.setSOURCE_USERID(useridfcubs);
        h.setSOURCE_USERID(creditCardPayRq.getUserId());
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("FCAT");
        //h.setUSERID(useridfcubs);
        h.setUSERID(creditCardPayRq.getUserId());

        h.setBRANCH(fromAccount.getBranchCode());

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY        
        RetailTellerTypeFull rtio = new RetailTellerTypeFull();
        rtio.setXREF("MB" + String.valueOf(System.currentTimeMillis()));
        rtio.setROUTECODE("VND");
        //set for product
        rtio.setPRD(productpaymentmastercard);
        rtio.setBRN(fromAccount.getBranchCode());

        // TK CHUYEN
        rtio.setTXNBRN(hm_accountMC.get("brch").toString().substring(0, 3));
        //change GL for master and visa 4: visa, 5:master
        String firstnum = creditCardPayRq.getCardNo().substring(0, 1);
        if (firstnum.equals("5")) {
            rtio.setTXNACC(GLPaymentMastercard);
        } else if (firstnum.equals("4")) {
            rtio.setTXNACC(GLPaymentVisacard);
        }
        rtio.setTXNCCY("VND");

        // TK NHAN
        //rtio.setOFFSETBRN(CommonUtils.getBranchAccount(fundTransferRq.getToAccount()));
        //rtio.setOFFSETACC(fundTransferRq.getToAccount());
        rtio.setOFFSETBRN(CommonUtils.getBranchAccount(creditCardPayRq.getFromAccount()));
        rtio.setOFFSETACC(creditCardPayRq.getFromAccount());
        rtio.setOFFSETCCY("VND");
        // SO TIEN
        rtio.setTXNAMT(new BigDecimal(creditCardPayRq.getAmount()));
        // GHI CHU
        rtio.setNARRATIVE(creditCardPayRq.getRemark());
        //thong tin nguoi tai khoan master card
        rtio.setUTLBNF1(creditCardPayRq.getCardNo());
        rtio.setUTLBNF2(hm_accountMC.get("e_name").toString());
        //thong tin khach hang thanh toan        
        MsgDetsType msgType = new MsgDetsType();
        msgType.setORDCUST1(creditCardPayRq.getFromAccount());
        msgType.setORDCUST2(fromAccount.getAcDesc());
        msgType.setORDCUST3(creditCardPayRq.getCifNo());

        //thong tin don vi mo tai khoan
        msgType.setECONT2("Chá»n");
        msgType.setECONT3(hm_accountMC.get("brch").toString().substring(0, 3));
        msgType.setVDATECR("");
        msgType.setVDATEDR("");

        rtio.setMessageDetails(msgType);

        //CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        CREATETRANSACTION_FSFS_REQFCUBS_BODY b = new CREATETRANSACTION_FSFS_REQFCUBS_BODY();

        //b.setTransactionDetailsIO(rtio);
        b.setTransactionDetails(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);

        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();

            CREATETRANSACTION_FSFS_RES res = fcubsstub.createTransactionFS(r);

            if (res == null) {
                return null;
            }

            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();

            CREATETRANSACTION_FSFS_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();

            if (hResBody.getFCUBS_ERROR_RESP() != null) {
                //System.out.println(hResBody.getFCUBS_ERROR_RESP().toString());                                
                return null;
            }
            return hResBody.getTransactionDetails().getFCCREF();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //chuyen khoan ngoai he thong voi ma san pham

    /**
     *
     * @param fundTransferRq
     * @param ProduceName
     * @param chanelID
     * @return
     * @throws RemoteException
     */
    public String transferFCUBSDomesticWithProductAndCharge2(FundTransferRq fundTransferRq, String ProduceName, String chanelID) throws RemoteException {

        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;
        //CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(useridfcubs);
        //h.setSOURCE_USERID("ODC1");
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("FCAT");

        //h.setUSERID("ODC1");
        h.setUSERID(useridfcubs);
        //h.setUSERID(fundTransferRq.getUserId());

        //h.setBRANCH(fromAccount.getBranchCode());
        h.setBRANCH(CommonUtils.getBranchAccount(fundTransferRq.getFromAccount()));

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY        
        RetailTellerTypeFull rtio = new RetailTellerTypeFull();
        rtio.setXREF(chanelID + String.valueOf(System.currentTimeMillis()));
        // rtio.setROUTECODE("VND");

        rtio.setPRD(ProduceName);
        rtio.setBRN(CommonUtils.getBranchAccount(fundTransferRq.getFromAccount()));

        // TK CHUYEN
        rtio.setTXNBRN("000");
        //rtio.setTXNACC("459901025");
        //rtio.setTXNACC(GLPaymentDomestic);
        rtio.setTXNCCY("VND");
        //rtio.setc

        // TK NHAN        
        rtio.setOFFSETBRN(CommonUtils.getBranchAccount(fundTransferRq.getFromAccount()));
        rtio.setOFFSETACC(fundTransferRq.getFromAccount());
        rtio.setOFFSETCCY("VND");

        // SO TIEN
        //rtio.setTXNAMT(new BigDecimal(fundTransferRq.getAmount()));
        rtio.setOFFSETAMT(new BigDecimal(fundTransferRq.getAmount()));
        // GHI CHU
        rtio.setNARRATIVE(fundTransferRq.getRemark());

        //charge
        ChgdetsType[] chargeDetails = new ChgdetsType[2];

        ChgdetsType chrg1 = new ChgdetsType();
        chrg1.setCHGCOMP("PHI CHUYEN TIEN EBANKING");
        if (fundTransferRq.getTxnFee() != null) {
            chrg1.setCHGAMT(new BigDecimal(fundTransferRq.getTxnFee()));
        }

        ChgdetsType chrg2 = new ChgdetsType();
        if (fundTransferRq.getTxnTax() != null) {
            chrg2.setCHGAMT(new BigDecimal(fundTransferRq.getTxnTax()));
        }
        chrg2.setCHGCOMP("VAT CHUYEN TIEN EBANKING");

        chargeDetails[0] = chrg1;
        chargeDetails[1] = chrg2;
        //rtio.setChargeDetails(chargeDetails);

        //thong tin nguoi thu huong
        rtio.setUTLBNF1(fundTransferRq.getToAccount());
        //cut bene name to 35 char for utl2 and continue for utl3
        if (fundTransferRq.getBeneName().length() <= 35) {
            rtio.setUTLBNF2(fundTransferRq.getBeneName().trim());
        } else {
            String oldString35First = fundTransferRq.getBeneName().substring(0, 35);
            int oldSpace35 = oldString35First.lastIndexOf(" ");
            boolean hasSpace = false;
            if (oldSpace35 == -1 || fundTransferRq.getBeneName().substring(35, 36).equals(" ")) {
                oldSpace35 = 35;
            } else {
                hasSpace = true;
            }

            rtio.setUTLBNF2(fundTransferRq.getBeneName().substring(0, oldSpace35).trim());
            if (hasSpace) {
                oldSpace35 += 1;
                hasSpace = false;
            }
            String continueString = fundTransferRq.getBeneName().substring(oldSpace35).trim();
            if (continueString != null && continueString.length() > 0) {
                if (continueString.length() <= 35) {
                    rtio.setUTLBNF3(continueString);
                } else {
                    oldString35First = continueString.substring(0, 35);
                    oldSpace35 = oldString35First.lastIndexOf(" ");
                    if (oldSpace35 == -1 || continueString.substring(35, 36).equals(" ")) {
                        oldSpace35 = 35;
                    } else {
                        hasSpace = true;
                    }
                    rtio.setUTLBNF3(continueString.substring(0, oldSpace35));
                    if (hasSpace) {
                        oldSpace35 += 1;
                    }
                    continueString = continueString.substring(oldSpace35).trim();
                    if (continueString.length() <= 35) {
                        rtio.setUTLBNF4(continueString);
                    } else {
                        rtio.setUTLBNF4(continueString.substring(0, 35));
                    }
                }
            }
            /*
            
             rtio.setUTLBNF2(fundTransferRq.getBeneName().substring(0, 35));                        
             if (fundTransferRq.getBeneName().length() > 70) {
             rtio.setUTLBNF3(fundTransferRq.getBeneName().substring(35, 70));
             if (fundTransferRq.getBeneName().length() > 105) {
             rtio.setUTLBNF4(fundTransferRq.getBeneName().substring(70, 105));
             } else {
             rtio.setUTLBNF4(fundTransferRq.getBeneName().substring(70));
             }
             } else {
             rtio.setUTLBNF3(fundTransferRq.getBeneName().substring(35));
             }
             */
        }
        //
        /*
         if (fundTransferRq.getIDCityCode() != null && !fundTransferRq.getIDCityCode().isEmpty()) {
         rtio.setUTLBNF3(fundTransferRq.getIDCityCode());
         }*/

        if (fundTransferRq.getIDOpenDate() != null && !fundTransferRq.getIDOpenDate().isEmpty()) {
            try {
                //change format date from yyyyMMdd to yyyy-MM-dd                
                DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
                Date date = formatter.parse(fundTransferRq.getIDOpenDate());
                rtio.setUTLBNF4(formatter1.format(date));
                if (fundTransferRq.getIDCityCode() != null && !fundTransferRq.getIDCityCode().isEmpty()) {
                    rtio.setUTLBNF4(rtio.getUTLBNF4().concat(fundTransferRq.getIDCityCode()));
                }
            } catch (Exception e) {
                Helper.writeLogError(this.getClass(), e);
                return null;
            }
        }

        //thong tin nguoi gui
        MsgDetsType msgType = new MsgDetsType();
        msgType.setORDCUST1(fundTransferRq.getFromAccount());

        List customer = Helper.getDBI().getCustomerInfoFCC(fundTransferRq.getCifNo());
        if (customer != null && !customer.isEmpty()) {
            HashMap hm_cust = (HashMap) customer.get(0);
            msgType.setORDCUST2(hm_cust.get("namfirst").toString());
        }

        msgType.setORDCUST3(fundTransferRq.getCifNo());

        //thong tin ngan hang thu huong
        List benebank = Helper.getDBI().getBeneBank(fundTransferRq.getBankCode());
        if (benebank != null && !benebank.isEmpty()) {
            HashMap hm_bene = (HashMap) benebank.get(0);
            msgType.setECONT1(hm_bene.get("bankname").toString());
            rtio.setREMACCOUNT(fundTransferRq.getBankCode());
        }

        /* List bankCity = Helper.getDBI().getBankCity(fundTransferRq.getCityCode());
         if (bankCity != null && !bankCity.isEmpty()) {
         HashMap hm_city = (HashMap) bankCity.get(0);
         msgType.setECONT2(hm_city.get("nam_city_full").toString());

         }*/
        msgType.setECONT2(fundTransferRq.getCityCode());
        msgType.setECONT3(fundTransferRq.getBranchName());
        msgType.setVDATECR("");
        msgType.setVDATEDR("");
        rtio.setMessageDetails(msgType);
        //them moi
        rtio.setFT(com.ofss.fcubs.service.FCUBSRTService.YesNoType.Y);
        rtio.setROUTECODE("VND");

        CREATETRANSACTION_FSFS_REQFCUBS_BODY b = new CREATETRANSACTION_FSFS_REQFCUBS_BODY();

        b.setTransactionDetails(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);

        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            //CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);
            CREATETRANSACTION_FSFS_RES res = fcubsstub.createTransactionFS(r);

            if (res == null) {
                return null;
            }

            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

            CREATETRANSACTION_FSFS_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            //CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if (hResBody.getFCUBS_ERROR_RESP() != null) {
                Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP().toString());
                //System.out.println(hResBody.getFCUBSERRORRESP().toString());
                if (hResBody.getFCUBS_ERROR_RESP()[0] != null) {
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());
                }
                return null;
            }
            return hResBody.getTransactionDetails().getFCCREF();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    //KIMNCM: chuyá»ƒn khoáº£n 24-7 vá»›i phÃ­ vÃ  thuáº¿

    /**
     *
     * @param productname
     * @param userid
     * @param brnAccountFrom
     * @param accountFrom
     * @param brnAccountTo
     * @param accountTo
     * @param amount
     * @param narative
     * @param timeout
     * @param fee
     * @param vat
     * @param finFee
     * @param finVat
     * @return
     */
    public String transferFCUBSWithTimeOutFeeAndVAT(String productname, String userid, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, String narative, int timeout, BigDecimal fee, BigDecimal vat, BigDecimal finFee, BigDecimal finVat) {
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;

        CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        // CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(userid);
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("ODC1");
        h.setUSERID(userid);
        h.setBRANCH(brnAccountFrom);

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY
        RetailTellerTypeIO rtio = new RetailTellerTypeIO();
//        rtio.setXREF("PBLP1200013304469");
        rtio.setPRD(productname);
        rtio.setBRN(brnAccountFrom);

        // TK CHUYEN
        rtio.setTXNBRN(brnAccountFrom);
        rtio.setTXNACC(accountFrom);
        rtio.setTXNCCY("VND");

        // TK NHAN
        rtio.setOFFSETBRN(brnAccountTo);
        rtio.setOFFSETACC(accountTo);
        rtio.setOFFSETCCY("VND");
        //rtio.setOFFSETAMT(amount);

        // SO TIEN
        rtio.setTXNAMT(amount);

        // GHI CHU
        rtio.setNARRATIVE(narative);

        //add nang cap core for FS - charge
        //   rtio.setFT(com.ofss.fcubs.service.FCUBSRTService.YesNoType.Y);
        //charge       
        //ChgdetsType[] chargeDetails = new ChgdetsType[2];
        /*
        ChgdetsType[] chargeDetails = new ChgdetsType[4];

        ChgdetsType chrg1 = new ChgdetsType();
        chrg1.setCHGCOMP("PHI CHUYEN TIEN EBANKING");
        chrg1.setCHGAMT(fee);
        chrg1.setCHGCCY("VND");

        ChgdetsType chrg2 = new ChgdetsType();
        chrg2.setCHGAMT(vat);
        chrg2.setCHGCOMP("VAT CHUYEN TIEN EBANKING");
         chrg2.setCHGCCY("VND");

        ChgdetsType chrg3 = new ChgdetsType();
        chrg3.setCHGCOMP("PHI TU VAN TAI CHINH EBANKING");
        chrg3.setCHGAMT(finFee);
         chrg3.setCHGCCY("VND");

        ChgdetsType chrg4 = new ChgdetsType();
        chrg4.setCHGAMT(finVat);
        chrg4.setCHGCOMP("VAT TU VAN TAI CHINH EBANKING");
        chrg4.setCHGCCY("VND");
        
        chargeDetails[0] = chrg1;
        chargeDetails[1] = chrg2;
        chargeDetails[2] = chrg3;
       chargeDetails[3] = chrg4;

        rtio.setChargeDetails(chargeDetails);
         */
        CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        b.setTransactionDetailsIO(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);

        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            fcubsstub.setTimeout(timeout * 1000);
            CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);

            if (res == null) {
                return null;
            }

            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

            CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if (hResBody.getFCUBS_ERROR_RESP() != null) {
                System.out.println(hResBody.getFCUBS_ERROR_RESP().toString());
                return null;
            }
            return hResBody.getTransactionDetailsPK().getFCCREF();

        } catch (AxisFault af) {
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                af.printStackTrace();
                return null;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    private String transferFCUBSForCounter(String productname, String userid, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, String narative, String branchcode,String refno) {
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;

        CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        // CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(userid);
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("ODC1");
        h.setUSERID(userid);
        h.setBRANCH(branchcode);

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY
        RetailTellerTypeIO rtio = new RetailTellerTypeIO();
        rtio.setXREF(refno);
        rtio.setPRD(productname);
        rtio.setBRN(branchcode);

        // TK CHUYEN
        rtio.setTXNBRN(brnAccountFrom);
        rtio.setTXNACC(accountFrom);
        rtio.setTXNCCY("VND");

        // TK NHAN
        //Helper.writeLogError(this.getClass(), "Tk nhan: " + brnAccountTo);
        rtio.setOFFSETBRN(brnAccountTo);

        rtio.setOFFSETACC(accountTo);
        rtio.setOFFSETCCY("VND");
        //rtio.setOFFSETAMT(amount);

        // SO TIEN
        rtio.setTXNAMT(amount);

        // GHI CHU
        rtio.setNARRATIVE(narative);

        CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        b.setTransactionDetailsIO(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);

        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            fcubsstub.setTimeout(30 * 1000);
            CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);

            if (res == null) {
                return null;
            }

            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

            CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if (hResBody.getFCUBS_ERROR_RESP() != null) {
                System.out.println(hResBody.getFCUBS_ERROR_RESP().toString());
                if (hResBody.getFCUBS_ERROR_RESP()[0] != null) {
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());
                }

                return null;
            }
            return hResBody.getTransactionDetailsPK().getFCCREF();

        } catch (AxisFault af) {
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                af.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param producttransfer
     * @param accountFrom
     * @param accountTo
     * @param amount
     * @param narative
     * @param branchcode
     * @return
     * @throws RemoteException
     */
    public String transferFCUBSCounter(String producttransfer, String accountFrom, String accountTo,
            BigDecimal amount, String narative, String branchcode,String refno) throws RemoteException {
        String brnAccountFrom = CommonUtils.getBranchAccount(accountFrom);
        String brnAccountTo = CommonUtils.getBranchAccount(accountTo);
        return transferFCUBSForCounter(producttransfer, useridfcubs, brnAccountFrom, accountFrom,
                brnAccountTo, accountTo, amount, narative, branchcode,refno);
    }
    

    /**
     *
     * @param fundTransferRq
     * @return
     */
    public String transferFCUBSWithProduct(FundTransferRq fundTransferRq) {
        String brnAccountFrom = CommonUtils.getBranchAccount(fundTransferRq.getFromAccount());
        String brnAccountTo = CommonUtils.getBranchAccount(fundTransferRq.getToAccount());
        return transferFCUBS(fundTransferRq.getProductCode(), fundTransferRq.getUserId(), brnAccountFrom,
                 fundTransferRq.getFromAccount(), brnAccountTo, fundTransferRq.getToAccount(),
                 new BigDecimal(fundTransferRq.getAmount()), fundTransferRq.getRemark());
    }

    /**
     *
     * @param req
     * @param timeout
     * @return
     */
    public String transferFCUBSWithTimeOut(TransferMoney247DetailReq req, int timeout) {
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;
        CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(req.getUserIdFcubs());
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("ODC1");
        h.setUSERID(req.getUserIdFcubs());
        h.setBRANCH(req.getBranchCust());
        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);
        RetailTellerTypeIO rtio = new RetailTellerTypeIO();
        rtio.setPRD(req.getProduct());
        rtio.setBRN(req.getBranchCust());
        /* TK CHUYEN */
        rtio.setTXNBRN(req.getBranchCust());
        rtio.setTXNACC(req.getFromNumber());
        rtio.setTXNCCY(req.getCcy());
        /* LOAI HINH NGOAI TE GOC NEU CO*/
 /* TK NHAN */
        rtio.setOFFSETBRN(req.getNapasBranch());
        rtio.setOFFSETACC(req.getNapasAccount());
        rtio.setOFFSETCCY(req.getExchangeRateRes().getCcyExchange());
        /* LOAI HINH TIEN TE = VND*/
        rtio.setOFFSETAMT(req.getExchangeRateRes().getMoneyExchange());
        /* SO TIEN DA CHUYEN DOI TU NGOAI TE VE VND*/
        rtio.setXRATE(req.getExchangeRateRes().getRate());
        /* INFO */
        rtio.setTXNAMT(req.getAmount());
        /* SO TIEN NGOAI TE GOC NEU CO*/
        rtio.setNARRATIVE(req.getDescription());
        /* GHI CHU */
        CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        b.setTransactionDetailsIO(rtio);
        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);
        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            fcubsstub.setTimeout(timeout * 1000);
            CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);
            if (res != null) {
                CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
                if (hResBody.getFCUBS_ERROR_RESP() == null) {
                    return hResBody.getTransactionDetailsPK().getFCCREF();
                } else {
                    logger.info(Arrays.toString(hResBody.getFCUBS_ERROR_RESP()));
                }
            }
        } catch (AxisFault af) {
            logger.error(af);
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                return "TIMEOUT";
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /**
     *
     * @param productname
     * @param userid
     * @param brnAccountFrom
     * @param accountFrom
     * @param brnAccountTo
     * @param accountTo
     * @param amount
     * @param narative
     * @param timeout
     * @return
     */
    public String transferFCUBSWithReturnTimeOut(String productname, String userid, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, String narative, int timeout) {
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;

        CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        // CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(userid);
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("ODC1");
        h.setUSERID(userid);
        h.setBRANCH(brnAccountFrom);

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY
        RetailTellerTypeIO rtio = new RetailTellerTypeIO();
//        rtio.setXREF("PBLP1200013304469");
        rtio.setPRD(productname);
        rtio.setBRN(brnAccountFrom);

        // TK CHUYEN
        rtio.setTXNBRN(brnAccountFrom);
        rtio.setTXNACC(accountFrom);
        rtio.setTXNCCY("VND");

        // TK NHAN
        rtio.setOFFSETBRN(brnAccountTo);
        rtio.setOFFSETACC(accountTo);
        rtio.setOFFSETCCY("VND");
        //rtio.setOFFSETAMT(amount);

        // SO TIEN
        rtio.setTXNAMT(amount);

        // GHI CHU
        rtio.setNARRATIVE(narative);

        CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        b.setTransactionDetailsIO(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);

        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            fcubsstub.setTimeout(timeout * 1000);
            CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);

            if (res == null) {
                return null;
            }

            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

            CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if (hResBody.getFCUBS_ERROR_RESP() != null) {
                System.out.println(hResBody.getFCUBS_ERROR_RESP().toString());
                if (hResBody.getFCUBS_ERROR_RESP()[0] != null) {
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());
                }
                return null;
            }
            return hResBody.getTransactionDetailsPK().getFCCREF();

        } catch (AxisFault af) {
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                af.printStackTrace();
                // return null;
            }
            return "TIMEOUT";
        } catch (Exception e) {

            e.printStackTrace();
            //return null;
            return "TIMEOUT";
        }
    }

    /**
     *
     * @param productName
     * @param accountFrom
     * @param accountTo
     * @param amount
     * @param narative
     * @param timeout
     * @return
     * @throws RemoteException
     */
    public String transferWithReturnTimeOut(String productName, String accountFrom, String accountTo, BigDecimal amount, String narative, int timeout) throws RemoteException {
        String brnAccountFrom = CommonUtils.getBranchAccount(accountFrom);
        String brnAccountTo = CommonUtils.getBranchAccount(accountTo);
        return transferFCUBSWithReturnTimeOut(productName, useridfcubs, brnAccountFrom, accountFrom, brnAccountTo, accountTo, amount, narative, timeout);
    }
    public String transferFCUBSWithReturnTimeOutRefno(String productname, String userid, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, String narative, int timeout,String refno) {
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;

        CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        // CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(userid);
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("ODC1");
        h.setUSERID(userid);
        h.setBRANCH(brnAccountFrom);

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY
        RetailTellerTypeIO rtio = new RetailTellerTypeIO();
        rtio.setXREF(refno);
        rtio.setPRD(productname);
        rtio.setBRN(brnAccountFrom);

        // TK CHUYEN
        rtio.setTXNBRN(brnAccountFrom);
        rtio.setTXNACC(accountFrom);
        rtio.setTXNCCY("VND");

        // TK NHAN
        rtio.setOFFSETBRN(brnAccountTo);
        rtio.setOFFSETACC(accountTo);
        rtio.setOFFSETCCY("VND");
        //rtio.setOFFSETAMT(amount);

        // SO TIEN
        rtio.setTXNAMT(amount);

        // GHI CHU
        rtio.setNARRATIVE(narative);

        CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        b.setTransactionDetailsIO(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);

        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            fcubsstub.setTimeout(timeout * 1000);
            CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);

            if (res == null) {
                return null;
            }

            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

            CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if (hResBody.getFCUBS_ERROR_RESP() != null) {
                System.out.println(hResBody.getFCUBS_ERROR_RESP().toString());
                if (hResBody.getFCUBS_ERROR_RESP()[0] != null) {
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());
                }
                return null;
            }
            return hResBody.getTransactionDetailsPK().getFCCREF();

        } catch (AxisFault af) {
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
                return "TIMEOUT";
            } else {
                af.printStackTrace();
                // return null;
            }
            return "TIMEOUT";
        } catch (Exception e) {

            e.printStackTrace();
            //return null;
            return "TIMEOUT";
        }
    }
    public String transferFCUBSWithTimeOutFeeAndVATRefno(String productname, String userid, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, String narative, int timeout, BigDecimal fee, BigDecimal vat, BigDecimal finFee, BigDecimal finVat,String refno) {
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;

        CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        // CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(userid);
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("ODC1");
        h.setUSERID(userid);
        h.setBRANCH(brnAccountFrom);

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY
        RetailTellerTypeIO rtio = new RetailTellerTypeIO();
        rtio.setXREF(refno);
        rtio.setPRD(productname);
        rtio.setBRN(brnAccountFrom);

        // TK CHUYEN
        rtio.setTXNBRN(brnAccountFrom);
        rtio.setTXNACC(accountFrom);
        rtio.setTXNCCY("VND");

        // TK NHAN
        rtio.setOFFSETBRN(brnAccountTo);
        rtio.setOFFSETACC(accountTo);
        rtio.setOFFSETCCY("VND");
        //rtio.setOFFSETAMT(amount);

        // SO TIEN
        rtio.setTXNAMT(amount);

        // GHI CHU
        rtio.setNARRATIVE(narative);

        //add nang cap core for FS - charge
        //   rtio.setFT(com.ofss.fcubs.service.FCUBSRTService.YesNoType.Y);
        //charge       
        //ChgdetsType[] chargeDetails = new ChgdetsType[2];
        /*
        ChgdetsType[] chargeDetails = new ChgdetsType[4];

        ChgdetsType chrg1 = new ChgdetsType();
        chrg1.setCHGCOMP("PHI CHUYEN TIEN EBANKING");
        chrg1.setCHGAMT(fee);
        chrg1.setCHGCCY("VND");

        ChgdetsType chrg2 = new ChgdetsType();
        chrg2.setCHGAMT(vat);
        chrg2.setCHGCOMP("VAT CHUYEN TIEN EBANKING");
         chrg2.setCHGCCY("VND");

        ChgdetsType chrg3 = new ChgdetsType();
        chrg3.setCHGCOMP("PHI TU VAN TAI CHINH EBANKING");
        chrg3.setCHGAMT(finFee);
         chrg3.setCHGCCY("VND");

        ChgdetsType chrg4 = new ChgdetsType();
        chrg4.setCHGAMT(finVat);
        chrg4.setCHGCOMP("VAT TU VAN TAI CHINH EBANKING");
        chrg4.setCHGCCY("VND");
        
        chargeDetails[0] = chrg1;
        chargeDetails[1] = chrg2;
        chargeDetails[2] = chrg3;
       chargeDetails[3] = chrg4;

        rtio.setChargeDetails(chargeDetails);
         */
        CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        b.setTransactionDetailsIO(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);

        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            fcubsstub.setTimeout(timeout * 1000);
            CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);

            if (res == null) {
                return null;
            }

            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

            CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if (hResBody.getFCUBS_ERROR_RESP() != null) {
                logger.info(hResBody.getFCUBS_ERROR_RESP().toString());
                return null;
            }
            return hResBody.getTransactionDetailsPK().getFCCREF();

        }catch (Exception e) {
            logger.info(e.getMessage());
            return "TIMEOUT";
        }
    }
    public String[] transferFCUBSWithTimeOutFeeAndVATRefno_247(String productname, String userid, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, String narative, int timeout, BigDecimal fee, BigDecimal vat, BigDecimal finFee, BigDecimal finVat,String refno,String toaccount,String tobank) {
        try
        {
            FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
            FCUBSRTServiceSEIBindingStub fcubsstub;

            CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
            // CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

            FCUBS_HEADERType h = new FCUBS_HEADERType();
            h.setMSGID("");
            h.setCORRELID("");
            h.setSOURCE_OPERATION("CreateTransaction");
            h.setSOURCE_USERID(userid);
            h.setUBSCOMP(UBSCOMPType.FCUBS);
            h.setSOURCE("ODC1");
            h.setUSERID(userid);
            h.setBRANCH(brnAccountFrom);

            h.setMODULEID("RT");
            h.setSERVICE("FCUBSRTService");
            h.setOPERATION("CreateTransaction");
            h.setMSGSTAT(MsgStatType.SUCCESS);

            //BODY
            RetailTellerTypeIO rtio = new RetailTellerTypeIO();
            rtio.setXREF(refno);
            rtio.setPRD(productname);
            rtio.setBRN(brnAccountFrom);

            // TK CHUYEN
            rtio.setTXNBRN(brnAccountFrom);
            rtio.setTXNACC(accountFrom);
            rtio.setTXNCCY("VND");

            // TK NHAN
            rtio.setOFFSETBRN(brnAccountTo);
            rtio.setOFFSETACC(accountTo);
            rtio.setOFFSETCCY("VND");
            //rtio.setOFFSETAMT(amount);

            // SO TIEN
            rtio.setTXNAMT(amount);

            // GHI CHU
            rtio.setNARRATIVE(narative);

            rtio.setREMACCOUNT(toaccount);
            rtio.setREMBANK(tobank);

            //add nang cap core for FS - charge
            //   rtio.setFT(com.ofss.fcubs.service.FCUBSRTService.YesNoType.Y);
            //charge       
            //ChgdetsType[] chargeDetails = new ChgdetsType[2];
            /*
            ChgdetsType[] chargeDetails = new ChgdetsType[4];

            ChgdetsType chrg1 = new ChgdetsType();
            chrg1.setCHGCOMP("PHI CHUYEN TIEN EBANKING");
            chrg1.setCHGAMT(fee);
            chrg1.setCHGCCY("VND");

            ChgdetsType chrg2 = new ChgdetsType();
            chrg2.setCHGAMT(vat);
            chrg2.setCHGCOMP("VAT CHUYEN TIEN EBANKING");
             chrg2.setCHGCCY("VND");

            ChgdetsType chrg3 = new ChgdetsType();
            chrg3.setCHGCOMP("PHI TU VAN TAI CHINH EBANKING");
            chrg3.setCHGAMT(finFee);
             chrg3.setCHGCCY("VND");

            ChgdetsType chrg4 = new ChgdetsType();
            chrg4.setCHGAMT(finVat);
            chrg4.setCHGCOMP("VAT TU VAN TAI CHINH EBANKING");
            chrg4.setCHGCCY("VND");

            chargeDetails[0] = chrg1;
            chargeDetails[1] = chrg2;
            chargeDetails[2] = chrg3;
           chargeDetails[3] = chrg4;

            rtio.setChargeDetails(chargeDetails);
             */
            CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
            b.setTransactionDetailsIO(rtio);

            r.setFCUBS_HEADER(h);
            r.setFCUBS_BODY(b);
            String[] result=new String[2];
            try {
                fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
                fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
                fcubsstub.setTimeout(timeout * 1000);
                CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);

                if (res == null) {
                    return null;
                }

                FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
                // System.out.println("MSGID:" + hResHeader.getMSGID());
                // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

                CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
                if(hResBody == null)
                {
                    logger.info("hResBody == null");
                    result[0]="-2";
                    result[1]="TIMEOUT";
                }
                else
                if (hResBody.getFCUBS_ERROR_RESP() != null) 
                {
                    result[0]="-1";
                    logger.info(hResBody.getFCUBS_ERROR_RESP().toString());
                    if (hResBody.getFCUBS_ERROR_RESP()[0] != null) {
                        Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                        Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());

                        result[1]= hResBody.getFCUBS_ERROR_RESP()[0].getECODE();
                    }
                    else
                    {
                        result[1]="OTHERS";
                    }
                    //return null;
                }
                else
                {
                    result[0]="0";
                    result[1]=hResBody.getTransactionDetailsPK().getFCCREF();
                }


            }catch (Exception e) {
                logger.info(e.getMessage());
               // return "TIMEOUT";
                result[0]="-2";
                result[1]="TIMEOUT";
            }
            return result;
        }catch(Exception ex)
        {
            logger.info(ex.getMessage());
            return null;
        }
    }
    public String[] transferFCUBSWithReturnTimeOutRefno_247(String productname, String userid, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, String narative, int timeout,String refno,String toAccount,String toBank) {
        
        logger.info("productname:"+productname +" userid:"+ userid+ " brnAccountFrom:"+brnAccountFrom +" accountFrom:" +accountFrom+" brnAccountTo:"+ brnAccountTo+ " accountTo" +accountTo
                 +" amount:"+ amount+" narative:"+narative+" timeout:"+timeout+" refno+"+ refno);
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;

        CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        // CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(userid);
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("ODC1");
        h.setUSERID(userid);
        h.setBRANCH(brnAccountFrom);

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY
        RetailTellerTypeIO rtio = new RetailTellerTypeIO();
        rtio.setXREF(refno);
        rtio.setPRD(productname);
        rtio.setBRN(brnAccountFrom);

        // TK CHUYEN
        rtio.setTXNBRN(brnAccountFrom);
        rtio.setTXNACC(accountFrom);
        rtio.setTXNCCY("VND");

        // TK NHAN
        rtio.setOFFSETBRN(brnAccountTo);
        rtio.setOFFSETACC(accountTo);
        rtio.setOFFSETCCY("VND");
        //rtio.setOFFSETAMT(amount);

        // SO TIEN
        rtio.setTXNAMT(amount);

        // GHI CHU
        rtio.setNARRATIVE(narative);
        
        rtio.setREMACCOUNT(toAccount);
        rtio.setREMBANK(toBank);

        CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        b.setTransactionDetailsIO(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);
        String[] result=new String[2];
        try {
            logger.info("fcus call core");
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            fcubsstub.setTimeout(timeout * 1000);
            CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);
            logger.info("fcus call core xong");
            if (res == null) {
                return null;
            }
 
            logger.info(res);
            
            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            logger.info("MSGID:" + hResHeader.getMSGID());
            logger.info("MSGSTAT:" + hResHeader.getMSGSTAT());
            
           
            CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if(hResBody!=null)
            {
               
                if (hResBody.getFCUBS_ERROR_RESP() != null) {
                    result[0]="-1";
                    System.out.println(hResBody.getFCUBS_ERROR_RESP().toString());
                    if (hResBody.getFCUBS_ERROR_RESP()[0] != null) {
                        Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                        Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());
                        result[1]=hResBody.getFCUBS_ERROR_RESP()[0].getECODE();
                    }
                    else
                    {
                        result[1]="OTHERS";
                    }
                   // return null;
                }
                else
                {
                    result[0]="0";
                    result[1]=hResBody.getTransactionDetailsPK().getFCCREF();
                }
            }
            else
            {
                logger.info("hResBody!=null");
                result[0]="-2";
                result[1]="TIMEOUT";
            }
             

        } catch (AxisFault af) {
            logger.info(af);
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
               // af.printStackTrace();
                //return "TIMEOUT";
            } else {
               // af.printStackTrace();
                // return null;
            }
            result[0]="-2";
            result[1]="TIMEOUT";
          //  return "TIMEOUT";
        } catch (Exception e) {
            logger.info(e);
           // e.printStackTrace();
            //return null;
         //   return "TIMEOUT";
            result[0]="-2";
            result[1]="TIMEOUT";
        }
        return result;
    }
    public String[] transferFCUBSForCounter_247(String productname, String userid, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, String narative, String branchcode,String refno,String toAccount,String toBankid) {
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;

        CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        // CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(userid);
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE("ODC1");
        h.setUSERID(userid);
        h.setBRANCH(branchcode);

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY
        RetailTellerTypeIO rtio = new RetailTellerTypeIO();
        rtio.setXREF(refno);
        rtio.setPRD(productname);
        rtio.setBRN(branchcode);

        // TK CHUYEN
        rtio.setTXNBRN(brnAccountFrom);
        rtio.setTXNACC(accountFrom);
        rtio.setTXNCCY("VND");

        // TK NHAN
        //Helper.writeLogError(this.getClass(), "Tk nhan: " + brnAccountTo);
        rtio.setOFFSETBRN(brnAccountTo);

        rtio.setOFFSETACC(accountTo);
        rtio.setOFFSETCCY("VND");
        //rtio.setOFFSETAMT(amount);

        // SO TIEN
        rtio.setTXNAMT(amount);

        // GHI CHU
        rtio.setNARRATIVE(narative);
        rtio.setREMACCOUNT(toAccount);
        rtio.setREMBANK(toBankid);

        CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        b.setTransactionDetailsIO(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);
        String[] result =new String[2];
        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            fcubsstub.setTimeout(30 * 1000);
            CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);

            if (res == null) {
                return null;
            }

            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

            CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if (hResBody.getFCUBS_ERROR_RESP() != null) 
            {
                result[0]="-1";
                System.out.println(hResBody.getFCUBS_ERROR_RESP().toString());
                if (hResBody.getFCUBS_ERROR_RESP()[0] != null) {
                     result[1]=hResBody.getFCUBS_ERROR_RESP()[0].getECODE();
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());
                }

                
            }
            else
            {
                result[0]="0";
                result[1]=hResBody.getTransactionDetailsPK().getFCCREF();
                  
            }
            return result;

        } catch (AxisFault af) {
            result[0]="-2";
             result[1]="TIMEOUT";
             Helper.writeLogError(this.getClass(), af.getMessage());
            if (af.getFaultString().contains("java.net.SocketTimeoutException")) {
                af.printStackTrace();
              //  return "TIMEOUT";
            } else {
                af.printStackTrace();
               // return null;
            }
        } catch (Exception e) {
            Helper.writeLogError(this.getClass(), e.getMessage());
            e.printStackTrace();
            //return null;
             result[0]="-2";
             result[1]="TIMEOUT";
        }
        return result;
    }
    public FcubsTransferRp ObjectTransferFCUBSWithTimeOut(String productname, String userid, String brnAccountFrom, String accountFrom, String brnAccountTo, String accountTo,
            BigDecimal amount, String narative, int timeout) {
        
        FcubsTransferRp rp= new FcubsTransferRp();
        String source ="ODC1";
        if(userid.equals("TAIQUAY"))
        {
            source=userid;
            userid="ODC1";
            
        }
        FCUBSRTServiceLocator fcubsl = new FCUBSRTServiceLocator();
        FCUBSRTServiceSEIBindingStub fcubsstub;

        CREATETRANSACTION_IOPK_REQ r = new CREATETRANSACTION_IOPK_REQ();
        // CREATETRANSACTION_FSFS_REQ r = new CREATETRANSACTION_FSFS_REQ();

        FCUBS_HEADERType h = new FCUBS_HEADERType();
        h.setMSGID("");
        h.setCORRELID("");
        h.setSOURCE_OPERATION("CreateTransaction");
        h.setSOURCE_USERID(userid);
        h.setUBSCOMP(UBSCOMPType.FCUBS);
        h.setSOURCE(source);
        h.setUSERID(userid);
        h.setBRANCH(brnAccountFrom);

        h.setMODULEID("RT");
        h.setSERVICE("FCUBSRTService");
        h.setOPERATION("CreateTransaction");
        h.setMSGSTAT(MsgStatType.SUCCESS);

        //BODY
        RetailTellerTypeIO rtio = new RetailTellerTypeIO();
//        rtio.setXREF("PBLP1200013304469");
        rtio.setPRD(productname);
        rtio.setBRN(brnAccountFrom);

        // TK CHUYEN
        rtio.setTXNBRN(brnAccountFrom);
        rtio.setTXNACC(accountFrom);
        rtio.setTXNCCY("VND");

        // TK NHAN
        rtio.setOFFSETBRN(brnAccountTo);
        rtio.setOFFSETACC(accountTo);
        rtio.setOFFSETCCY("VND");
        //rtio.setOFFSETAMT(amount);

        // SO TIEN
        rtio.setTXNAMT(amount);

        // GHI CHU
        rtio.setNARRATIVE(narative);

        CREATETRANSACTION_IOPK_REQFCUBS_BODY b = new CREATETRANSACTION_IOPK_REQFCUBS_BODY();
        b.setTransactionDetailsIO(rtio);

        r.setFCUBS_HEADER(h);
        r.setFCUBS_BODY(b);

        try {
            fcubsl.setFCUBSRTServiceSEIEndpointAddress(wsurladdress);
            fcubsstub = (FCUBSRTServiceSEIBindingStub) fcubsl.getFCUBSRTServiceSEI();
            fcubsstub.setTimeout(timeout * 1000);
            CREATETRANSACTION_IOPK_RES res = fcubsstub.createTransactionIO(r);

            if (res == null) {
                return null;
            }

            FCUBS_HEADERType hResHeader = res.getFCUBS_HEADER();
            // System.out.println("MSGID:" + hResHeader.getMSGID());
            // System.out.println("MSGSTAT:" + hResHeader.getMSGSTAT());

            CREATETRANSACTION_IOPK_RESFCUBS_BODY hResBody = res.getFCUBS_BODY();
            if (hResBody.getFCUBS_ERROR_RESP() != null) 
            {
                rp.setStatus("-1");
                System.out.println(hResBody.getFCUBS_ERROR_RESP().toString());
                if (hResBody.getFCUBS_ERROR_RESP()[0] != null) {
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                    Helper.writeLogError(this.getClass(), hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());
                    rp.setErrcode(hResBody.getFCUBS_ERROR_RESP()[0].getECODE());
                    rp.setErrdesc(hResBody.getFCUBS_ERROR_RESP()[0].getEDESC());
                }
               return rp;
            }
            rp.setStatus("0");
            rp.setRefcore(hResBody.getTransactionDetailsPK().getFCCREF());
            return rp;

        }
        catch (AxisFault af) 
        {
            rp.setRefcore("TIMEOUT");
            rp.setStatus("-2");
            af.printStackTrace();
        } catch (Exception e) {
            rp.setRefcore("TIMEOUT");
            rp.setStatus("-2");
            e.printStackTrace();
        }
        return rp;
    }
}
