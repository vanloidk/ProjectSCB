/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.HashMap;
import org.apache.log4j.Level;
import scb.com.vn.ultility.Xml;
import scb.com.vn.utility.Configuration;
import scb.com.vn.utility.Helper;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import scb.com.vn.common.model.info.GetSCBBranchRp;
import scb.com.vn.common.model.info.GetSCBBranchRq;
import scb.com.vn.common.odbx.SCBBranch;

import scb.com.vn.controller.ControllerImpl;

import scb.com.vn.controller.Fcubs;

import scb.com.vn.controller.Sms;
import scb.com.vn.dbi.dto.CustomerInfoRsDto;
import scb.com.vn.dbi.dto.OnlPaymentByCardDto;
import scb.com.vn.dbi.dto.OnlinePCustomerInfoDto;
import scb.com.vn.enumUtils.ResponseCodeEnum;
import scb.com.vn.message.CommonMessage;
import scb.com.vn.model.OnlinePCustomerInfoReq;

import scb.com.vn.model.RequestQueryOnlTrans;
import scb.com.vn.model.RequestCardVerify;
import scb.com.vn.model.RequestRefundOnlTrans;
import scb.com.vn.model.ResponseCardVerify;
import scb.com.vn.model.ResponseQueryOnlTrans;
import scb.com.vn.model.ResponseRefundOnlTrans;
import scb.com.vn.payment.napas.utility.RSAAlgorithm;
import scb.com.vn.ultility.AES;
import scb.com.vn.ultility.RsaUtils;

import scb.com.vn.utility.CommonUtils;
import scb.com.vn.utility.ResponseStatusCode;
import scb.com.vn.utility.SiUtils;

import scb.com.vn.utility.XMLUtils;
import scb.com.vn.utility.ZipData;

import vn.com.scb.bian.RetrieveCurrentAccountCASA_out_Type;

import vn.com.scb.bian.service.IIBCurrentAccountService;

import vn.com.scb.bian.utility.BianException;
import vn.com.scb.bian.utility.IIBConstants;
import vn.com.sml.ibt.rsa.EncodingType;
import vn.com.sml.ibt.rsa.RSASignature;
import vn.com.sml.ibt.rsa.SignatureKeyStoreMode;

//import scb.com.vn.dbi.dto.PayOnlineUser;
/**
 *
 * @author minhndb
 */

public class OnlinePaymentController {

    String IDCHECKMD5Key = Configuration.getProperty("ws.md5key.idcheck");
    String URLTransfer = Configuration.getProperty("ws.url.transfer");
    String OnePayID = Configuration.getProperty("ws.partnerid.onepay");
    String PayooID = Configuration.getProperty("ws.partnerid.payoo");
    String ProductTransfer = Configuration.getProperty("fcubs.producttransfer.onlinetransfer");
    String mmsQRProductCode = Configuration.getProperty("fcubs.producttransfer.mmsqr");
    String VIMOID = Configuration.getProperty("ws.partnerid.vimo");
    final int vnpayqrTimeout = Integer.parseInt(Configuration.getProperty("vnpayQR.timeout"));
    String listPartnerOtpNumber = Configuration.getProperty("onlinepayment.otpnumber");
    final static Logger logger = Logger.getLogger(OnlinePaymentController.class);


    /**
     *
     */
    public OnlinePaymentController() {
    }

    /**
     * Begin Online payment controller Author:LyDTy
     *
     * @param xml
     * @return
     */
    public String cardVerify(String xml) {
        try {
            RequestCardVerify req = (RequestCardVerify) Xml.unMarshaller(RequestCardVerify.class, xml);

            boolean checkdate = ControllerUtil.isCheckDatetime(req.getLocalDateTime());
            String StatusCode;
            String[] key = Helper.getDBI().ONL_GetMACkeys(req.getProviderID());
            if (key.length == 0) {
                return "99";
            } else {
                String MD5Key = key[0].toString();

                String Input = req.getCommandCode() + req.getTransID() + req.getCardNumber() + req.getCardHolderName() + req.getCardDate() + req.getMerchantId() + req.getProviderID() + req.getAmount()
                        + req.getCURRENCYCODE() + req.getLanguage() + req.getClientID() + req.getLocalDateTime() + req.getAddInfo() + MD5Key;
                String MACCheck = ControllerUtil.EncriptMD5(Input);

                Date currentdate = new Date();
                String IDVerify = ControllerUtil.EncriptMD5(req.getProviderID() + req.getTransID() + currentdate + IDCHECKMD5Key);
                String URLAuthen = URLTransfer + "?id=" + IDVerify;
                String Desc = "";
                String ReturnMac = "";
                if (!req.getMAC().trim().equals(MACCheck) || (checkdate == false)) {
                    Helper.writeLogErrorNonDB(Controller.class, "ONL.checkCustomerInfo.Msg error:MAC không khớp;Hoặc kiểu ngày không đúng địn dạng ddMMyyyyHHmmss");
                    StatusCode = "99";
                } else {
                    StatusCode = Helper.getDBI().checkCustomerInfo(req.getProviderID(), req.getTransID(), req.getCardNumber(), req.getCardHolderName(), req.getCardDate(), req.getMerchantId(), Double.valueOf(req.getAmount()),
                            req.getCURRENCYCODE(), req.getLanguage(), req.getClientID(), req.getLocalDateTime(), req.getAddInfo(), "INT", URLAuthen, IDVerify);
                }
                if (!StatusCode.equals("00")) {
                    URLAuthen = "";
                }
                ReturnMac = ControllerUtil.EncriptMD5(StatusCode + req.getMerchantId() + req.getTransID() + URLAuthen + Desc + MD5Key);
                ResponseCardVerify response = new ResponseCardVerify();
                response.setAUTHENTICATIONURL(URLAuthen);
                response.setDESCRIPTION(Desc);
                response.setMAC(ReturnMac);
                response.setMERCHANTID(req.getMerchantId());
                response.setResponseCode(StatusCode);
                response.setTRANSACTIONID(req.getTransID());
                return Xml.Marshaller(response);
            }
        } catch (Exception ex) {
            throw new PaymentException(ex);
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String refundOnlTrans(String xml) {
        try {
            RequestRefundOnlTrans req = (RequestRefundOnlTrans) Xml.unMarshaller(RequestRefundOnlTrans.class, xml);
            boolean checkdate = ControllerUtil.isCheckDatetime(req.getLocalDateTime());
            String StatusCode = "01";
            String BankTransID = "0";
            String ReturnMac = "";
            String[] key = Helper.getDBI().ONL_GetMACkeys(req.getProviderID());
            if (key.length == 0) {
                return "99";
            } else {
                String MD5Key = key[0].toString();

                String Input = req.getCommandCode() + req.getTransID() + req.getMerchantId() + req.getProviderID() + req.getAmount()
                        + req.getCURRENCYCODE() + req.getRefundTransID() + req.getLocalDateTime() + req.getAddInfo() + MD5Key;

                String MACCheck = ControllerUtil.EncriptMD5(Input);
                String AccCustomer = "0";
                String AccPartner = "0";
                if (!req.getMAC().trim().equals(MACCheck) || (checkdate == false)) {
                    Helper.writeLogErrorNonDB(Controller.class, "ONL.RefundTransfer.Msg error:MAC không khớp;Hoặc kiểu ngày không đúng định dạng ddMMyyyyHHmmss");
                    StatusCode = "99";
                } else {
                    //Check dieu kien refund
                    String[] ArrayReult = Helper.getDBI().CheckRefundTransfer(req.getProviderID(), req.getMerchantId(), req.getTransID(), req.getRefundTransID(), BigDecimal.valueOf(Double.parseDouble(req.getAmount())), req.getCURRENCYCODE(), req.getAddInfo(), req.getLocalDateTime());
                    StatusCode = ArrayReult[0];
                    AccCustomer = ArrayReult[1];
                    AccPartner = ArrayReult[2];
                    BankTransID = ArrayReult[3];
                    if (StatusCode.equals("00")) {
                        // Thuc hien chuyen khoan
                        Fcubs fc = new Fcubs();
                        String Desc = "HOAN TIEN DO TT TRUC TUYEN KHONG THANH CONG." + req.getMerchantId() + "." + req.getTransID();
                        String CoreRef = fc.transferFCUBSWithProduct(ProductTransfer, AccPartner, AccCustomer, BigDecimal.valueOf(Double.valueOf(req.getAmount())), Desc);
                        if (CoreRef == null) {
                            StatusCode = "04";
                        }
                        Helper.getDBI().UpdateRefundTransfer(BankTransID, CoreRef, StatusCode);
                    }
                    ReturnMac = ControllerUtil.EncriptMD5(StatusCode + req.getMerchantId() + req.getTransID() + BankTransID + req.getRefundTransID() + req.getAddInfo() + MD5Key);
                }

                ResponseRefundOnlTrans response = new ResponseRefundOnlTrans();
                response.setADDINFO(req.getAddInfo());
                response.setBANKTRANSID(BankTransID);
                response.setMAC(ReturnMac);
                response.setMERCHANTID(req.getMerchantId());
                response.setREFUNDTRANSID(req.getRefundTransID());
                response.setResponseCode(StatusCode);
                response.setTRANSID(req.getTransID());
                return Xml.Marshaller(response);
            }
        } catch (Exception ex) {
            throw new PaymentException(ex);
        }
    }

    /**
     *
     * @param xml
     * @return
     */
    public String queryOnlTrans(String xml) {
        try {
            RequestQueryOnlTrans req = (RequestQueryOnlTrans) Xml.unMarshaller(RequestQueryOnlTrans.class, xml);
            String StatusCode;

            String Desc = "";
            String BankTransID = "";
            String ReturnMac = "";
            String[] key = Helper.getDBI().ONL_GetMACkeys(req.getProviderID());
            if (key.length == 0) {
                return "99";
            } else {
                String MD5Key = key[0].toString();
                String Input = req.getCommandCode() + req.getTransID() + req.getMerchantId() + req.getProviderID() + req.getAmount()
                        + req.getCURRENCYCODE() + req.getQUERYTransID() + MD5Key;
                String MACCheck = ControllerUtil.EncriptMD5(Input);
                if (!req.getMAC().trim().equals(MACCheck)) {
                    Helper.writeLogErrorNonDB(Controller.class, "OLT.QuerryTransfer. Msg error:MAC không khớp");
                    StatusCode = "99";
                } else {
                    String[] ArrayReult = Helper.getDBI().QuerryTransfer(req.getProviderID(), req.getTransID(), req.getMerchantId(), Double.valueOf(req.getAmount()), req.getCURRENCYCODE(), req.getQUERYTransID());
                    StatusCode = ArrayReult[0].toString();
                    Desc = ArrayReult[1].toString();
                    BankTransID = ArrayReult[2].toString();
                    ReturnMac = ControllerUtil.EncriptMD5(StatusCode + Desc + req.getTransID() + req.getAmount() + BankTransID + MD5Key);
                }
                ResponseQueryOnlTrans response = new ResponseQueryOnlTrans();
                response.setBANKTRANSID(BankTransID);
                response.setDESCRIPTION(Desc);
                response.setAmount(Double.valueOf(req.getAmount()));
                response.setMAC(ReturnMac);
                response.setResponseCode(StatusCode);
                response.setTRANSID(req.getTransID());
                return Xml.Marshaller(response);
            }
        } catch (Exception ex) {
            throw new PaymentException(ex);
        }
    }

    /**
     *
     * @param IDTrans
     * @param UserId
     * @param IPAddress
     * @return
     */
    public int sendOTP(String IDTrans, String UserId, String IPAddress) {
        try {
            int issession = Helper.getDBI().checkSessionForTransfer(IDTrans, UserId, IPAddress);
            if (issession != 0) {
                String phonenum = Helper.getDBI().getuserphonenumber(UserId);

                //1. Tao so random OTP cho SMS
                String rsu = RandomStringUtils.randomAlphabetic(6);
                rsu = rsu.toUpperCase();

                //2. Luu vao table
                Helper.getDBI().insertOTPSMS(Integer.parseInt(IDTrans), phonenum, rsu, IPAddress);

                //3. Gui SMS
                String contentSMS = "Ma xac thuc giao dich ONLINE cua Khach hang la: " + rsu
                        + ". Neu giao dich KHONG do Quy khach thuc hien, vui long goi ngay Hotline SCB 19006538.";

                Sms sms = new Sms();
                int result = sms.sendsms(phonenum, contentSMS, "GW", "SCB");

                return result == 0 ? 1 : 0;
            }

            return issession;
        } catch (Exception ex) {
            throw new PaymentException(ex);
        }
    }

    /**
     *
     * @param requestData
     * @return
     * @throws Exception
     */
    public String SMLOnlinePayment(String requestData) throws Exception {
        try {
            String HASH_KEY = Configuration.getProperty("ws.onlinepayment.SML.hashkey");
            String[] resultRequest = requestData.replace("|", "#").split("#");
            String MTI = resultRequest[0];
            if (MTI.equals("0200")) {
                String CardNumber = resultRequest[1];
                String ProcessingCode = resultRequest[2]; //050000
                String strAmount = resultRequest[3];
                String transferdate = resultRequest[4];//MMddHhmiss
                String AuditNumber = resultRequest[5];
                String MerchantType = resultRequest[6]; //6011 ATM;6012 POS;7399 EBANK
                String AcquiringCode = resultRequest[7]; //970471
                String TermId = resultRequest[8]; //Mã merchant chấp nhận thẻ
                String CardAcceptor = resultRequest[9]; //Tên vị trí chấp nhận thẻ
                String ServiceCode = resultRequest[10];//CARDVER
                String AddInfo = resultRequest[11];
                String[] arrrayAddInfo = AddInfo.split(",");
                //Addinfo: Tên_chủ_thẻ, ngày_hiệu_lực, SML_trans_id, Merchant_code
                String CardName = arrrayAddInfo[0];
                String ExpriDate = arrrayAddInfo[1];
                String transid = arrrayAddInfo[2];
                String merchantcode = arrrayAddInfo[3];
                String HashValue = resultRequest[12];
                String InputData = HASH_KEY + CardNumber + ProcessingCode + strAmount + transferdate + AuditNumber + MerchantType + AcquiringCode + TermId;
                String StatusCode = "";
                Date currentdate = new Date();
                DateFormat df = new SimpleDateFormat("yyyy");
                Date today = Calendar.getInstance().getTime();
                String year = df.format(today);;
                String LocalDateTime = year + transferdate; //yyyyMMddHHmmss
                String IDVerify = ControllerUtil.EncriptMD5("SML" + AuditNumber + currentdate + IDCHECKMD5Key);
                String URLTransfer2 = Configuration.getProperty("ws.url.transfer");
                String URLAuthen = URLTransfer2 + "?id=" + IDVerify;
                String BankTransID = "";
                String AddInfo1 = CardAcceptor + "|" + AuditNumber + "|" + TermId;
                String SCBMerchantType = "";
                if (MerchantType.endsWith("6011")) {
                    SCBMerchantType = "ATM";
                } else if (MerchantType.endsWith("6012")) {
                    SCBMerchantType = "POS";
                } else {
                    SCBMerchantType = "INT";
                }
                Double Amount = Double.valueOf(strAmount);
                if (verifySIGNATURE(InputData, HashValue)) {
                    String[] result = Helper.getDBI().SMLEcomCheckData("SML", transid, CardNumber, CardName, merchantcode, Amount,
                            "VND", LocalDateTime, AddInfo1, SCBMerchantType, URLAuthen, IDVerify);

                    StatusCode = result[0];
                    BankTransID = result[1];
                } else {
                    StatusCode = "63";
                }
                String hashResponse = createREQUEST_SIGNATURE(HASH_KEY + CardNumber + ProcessingCode + strAmount + transferdate + AuditNumber + MerchantType + AcquiringCode + StatusCode);
                String resultResponse = "0210|" + CardNumber + "|" + ProcessingCode + "|" + strAmount + "|" + transferdate + "|"
                        + AuditNumber + "|" + MerchantType + "|" + AcquiringCode + "|" + BankTransID + "|" + StatusCode + "|"
                        + TermId + "|" + URLAuthen + "|" + ServiceCode + "|" + AddInfo + "|" + hashResponse;

                return resultResponse;
            }
            return "96";
        } catch (Exception ex) {
            return "96";
        }
    }

    /**
     *
     * @param inputString
     * @return
     * @throws Exception
     */
    public static String createREQUEST_SIGNATURE(String inputString) throws Exception {
        String privatekey = Configuration.getProperty("ws.onlinepayment.sml.privatekey");
        PrivateKey aKey = RSASignature.getPrivateKey(privatekey, SignatureKeyStoreMode.STRINGFILE, EncodingType.BASE64);
        String mac = "";
        try {
            mac = RSASignature.makeSignature(inputString, aKey, "SHA1withRSA", EncodingType.BASE64);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            System.out.println("Problem while creating request signature");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            System.out.println("Problem while creating request signature");
            e.printStackTrace();
        } catch (SignatureException e) {
            // TODO Auto-generated catch block
            System.out.println("Problem while creating request signature");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            System.out.println("Problem while creating request signature");
            e.printStackTrace();
        }
        return mac;
    }

    /**
     *
     * @param InputString
     * @param iReceivedMac
     * @return
     */
    public static boolean verifySIGNATURE(String InputString, String iReceivedMac) {
        String publickey = Configuration.getProperty("ws.onlinepayment.sml.publickey");
        PublicKey aKey = RSASignature.getPublicKey(publickey, SignatureKeyStoreMode.STRINGFILE, EncodingType.BASE64);
        boolean aResult = false;
        try {
            aResult = RSASignature.verifySignature(aKey, InputString, iReceivedMac, "SHA1withRSA", EncodingType.BASE64);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            System.out.println("Problem while verify signature");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            System.out.println("Problem while verify signature");
            e.printStackTrace();
        } catch (SignatureException e) {
            // TODO Auto-generated catch block
            System.out.println("Problem while verify signature");
            e.printStackTrace();
        }
        return aResult;
    }
    
    /**
     *
     * @param merchantid
     * @param TransID
     * @param RefundTransID
     * @param Amount
     * @param CCY
     * @param AddInfo
     * @param Localdatatime
     * @return
     * @throws Exception
     */
    public String SMLREFUND(String merchantid, String TransID, String RefundTransID, BigDecimal Amount, String CCY, String AddInfo, String Localdatatime) throws Exception {
        //Check dieu kien refund
        String[] ArrayReult = Helper.getDBI().CheckRefundTransfer("SML", merchantid, TransID,
                RefundTransID,
                Amount,
                CCY,
                AddInfo,
                Localdatatime);
        String StatusCode = ArrayReult[0];
        String AccCustomer = ArrayReult[1];
        String AccPartner = ArrayReult[2];
        String BankTransID = ArrayReult[3];
        if (StatusCode.equals("00")) {
            // Thuc hien chuyen khoan
            Fcubs fc = new Fcubs();
            String Desc = "NAPAS HOAN TIEN GIAO DICH TT TRUC TUYEN TAI:" + merchantid + " MA:" + TransID;
            AccPartner = "359206041";
            String CoreRef = fc.transferFCUBSWithProduct(ProductTransfer, AccPartner, AccCustomer, Amount, Desc);
            if (CoreRef == null) {
                StatusCode = "04";
            }
            Helper.getDBI().UpdateRefundTransfer(BankTransID, CoreRef, StatusCode);
        }
        return StatusCode;
    }

    /**
     * End of Online payment controller
     *
     * @param strXML
     * @return
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     */
    //verifyCard
    public String cardVerifyRegister(String strXML) throws ParserConfigurationException, SAXException, IOException {
        try {
            // System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("001")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String CARDNUMBER = eElement.getElementsByTagName("CARDNUMBER").item(0).getTextContent();
            String CARDHOLDERNAME = eElement.getElementsByTagName("CARDHOLDERNAME").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();
            String TYPECARD = "01";
            if (eElement.getElementsByTagName("CARDTYPE").item(0) != null) {
                TYPECARD = eElement.getElementsByTagName("CARDTYPE").item(0).getTextContent();
            }
            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0].toString();
            }

            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + TRANSID + CARDNUMBER + CARDHOLDERNAME + DATETIME + DESCRIPTION + PROVIDERID);
            //check MAC
            if (!strMAC.endsWith(MAC)) {
                return "98";
            }

            String otp;
            if (listPartnerOtpNumber.contains(PROVIDERID)) {
                otp = createOtpNum();
            } else {
                otp = createOTP();
            }
            String[] result = Helper.getDBI().ONL_VERIFICARD(TRANSID, DATETIME, CARDNUMBER, CARDHOLDERNAME, otp, PROVIDERID, DESCRIPTION, TYPECARD);
            String status = result[0].toString();
            String banktransid = "0";

            if (status.endsWith("00")) {
                banktransid = result[1].toString();
                String PhoneNumber = result[2].toString();
                int sendSMS = sendOTPSMS(PhoneNumber, otp, "Ma xac thuc (OTP) dang ky lien ket Vi " + PROVIDERID);
                if (sendSMS == 0) {
                    return "99";
                }
            }
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(status));
            response.appendChild(RESPONSECODE);

            Element BANKTRANSID = responsedoc.createElement("BANKTRANSID");
            BANKTRANSID.appendChild(responsedoc.createTextNode(banktransid));
            response.appendChild(BANKTRANSID);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eCARDNUMBER = responsedoc.createElement("CARDNUMBER");
            eCARDNUMBER.appendChild(responsedoc.createTextNode(CARDNUMBER));
            response.appendChild(eCARDNUMBER);

            Element eCARDHOLDERNAME = responsedoc.createElement("CARDHOLDERNAME");
            eCARDHOLDERNAME.appendChild(responsedoc.createTextNode(CARDHOLDERNAME));
            response.appendChild(eCARDHOLDERNAME);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String resMAC = ControllerUtil.EncriptMD5(MD5Key + status + banktransid + TRANSID + CARDNUMBER + CARDHOLDERNAME
                    + DATETIME + DESCRIPTION + PROVIDERID);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }

    private String createOTP() {
        //1. Tao so random OTP cho SMS
        String rsu = RandomStringUtils.randomAlphabetic(6);
        rsu = rsu.toUpperCase();
        return rsu;
    }

    private int sendOTPSMS(String phonenumber, String otp, String Content) {
        //3. Gui SMS
        ControllerImpl ci = new ControllerImpl("EBK");
        //ci.sendsms(phonenum, rsu);

        Date currentDate = new Date();
        SimpleDateFormat fTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = fDate.format(currentDate);
        String contentSMS = Content + " luc " + fTime.format(currentDate) + " " + strDate + " la: " + otp + ". Vi ly do BAO MAT, Quy khach KHONG cung cap OTP cho bat ky ai. LH 19006538.";
        //ci.sendsms(phonenum, contentSMS);                   

        contentSMS = contentSMS.replace("AIRPAY", "ShopeePay");

        Sms sms = new Sms();
        int Result = sms.sendsms(phonenumber, contentSMS, "GW", "SCB");

        if (Result == 0) {
            return 1;
        }// thanh cong
        else {
            return 0;
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String otpVerify(String strXML) {
        try {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("002")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String VERIFYTYPE = eElement.getElementsByTagName("VERIFYTYPE").item(0).getTextContent();
            String REFTRANSID = eElement.getElementsByTagName("REFTRANSID").item(0).getTextContent();
            String OTP = eElement.getElementsByTagName("OTP").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0].toString();
            }

            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + TRANSID + VERIFYTYPE + REFTRANSID + OTP + DATETIME + DESCRIPTION + PROVIDERID);
            //check MAC

            if (!strMAC.endsWith(MAC)) {
                return "98";
            }

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            String[] result = Helper.getDBI().ONL_VERIFYOTPSMS(TRANSID, DATETIME, VERIFYTYPE, REFTRANSID, OTP, PROVIDERID);
            String status = result[0].toString();
            String statusOTP = result[6].toString();
            String PBANKID = "";
            if (status.endsWith("00")) {
                if (VERIFYTYPE.endsWith("01")) {
                    String profileid = result[1].toString();
                    Element ePROFILEID = responsedoc.createElement("PROFILEID");
                    ePROFILEID.appendChild(responsedoc.createTextNode(profileid));
                    response.appendChild(ePROFILEID);
                } else if (VERIFYTYPE.endsWith("02")) {
                    //xac thuc thanh toan
                    String PCUSTACCOUNT = result[2].toString();
                    String PPARNERACCOUNT = result[3].toString();
                    String Amount = result[4].toString();
                    PBANKID = result[5].toString();
                    Fcubs fc = new Fcubs();
                    String Desc;
                    if (PROVIDERID.equals("SMARTPAY") || PROVIDERID.equals("PAYOOWL")) {
                        Desc = "NAP TIEN VAO VI VOI THE/TK DA LIEN KET QUA " + PROVIDERID + ".MAGD:" + REFTRANSID;
                    } else {
                        Desc = "TT TRUC TUYEN QUA " + PROVIDERID + ".MAGD:" + REFTRANSID;
                    }
                    Desc = Desc.replace("AIRPAY", "ShopeePay");

                    String CoreRef = fc.transferFCUBSWithProduct(ProductTransfer, PCUSTACCOUNT, PPARNERACCOUNT, BigDecimal.valueOf(Double.valueOf(Amount)), Desc);
                    String PRESPONSECODE = "00";
                    if (CoreRef == null) {
                        PRESPONSECODE = "01";
                        status = "01"; // xac thuc that bai
                    }
                    if (CoreRef.equals("TIMEOUT")) {
                        //return "99";
                        PRESPONSECODE = "99";
                        status = "99"; // Cho doi soat
                    }
                    Helper.getDBI().ONL_UpdatePayment(PBANKID, PRESPONSECODE, CoreRef);
                }
                if (VERIFYTYPE.endsWith("04")) {
                    String profileid = result[1].toString();
                    Element ePROFILEID = responsedoc.createElement("PROFILEID");
                    ePROFILEID.appendChild(responsedoc.createTextNode(profileid));
                    response.appendChild(ePROFILEID);
                    //xac thuc thanh toan
                    String PCUSTACCOUNT = result[2].toString();
                    String PPARNERACCOUNT = result[3].toString();
                    String Amount = result[4].toString();
                    PBANKID = result[5].toString();
                    Fcubs fc = new Fcubs();
                    String Desc = "TT TRUC TUYEN QUA " + PROVIDERID + ".MAGD:" + REFTRANSID;
                    Desc = Desc.replace("AIRPAY", "ShopeePay");
                    String CoreRef = fc.transferFCUBSWithProduct(ProductTransfer, PCUSTACCOUNT, PPARNERACCOUNT, BigDecimal.valueOf(Double.valueOf(Amount)), Desc);
                    String PRESPONSECODE = "00";
                    if (CoreRef == null) {
                        PRESPONSECODE = "01";
                        status = "01"; // xac thuc that bai
                    }
                    if (CoreRef.equals("TIMEOUT")) {
                        // return "99";
                        //return "99";
                        PRESPONSECODE = "99";
                        status = "99"; // Cho doi soat
                    }
                    Helper.getDBI().ONL_UpdatePayment(PBANKID, PRESPONSECODE, CoreRef);
                }
            }

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(status));
            response.appendChild(RESPONSECODE);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eVERIFYTYPE = responsedoc.createElement("VERIFYTYPE");
            eVERIFYTYPE.appendChild(responsedoc.createTextNode(VERIFYTYPE));
            response.appendChild(eVERIFYTYPE);

            Element eREFTRANSID = responsedoc.createElement("REFTRANSID");
            eREFTRANSID.appendChild(responsedoc.createTextNode(REFTRANSID));
            response.appendChild(eREFTRANSID);

            Element eOTP = responsedoc.createElement("OTP");
            eOTP.appendChild(responsedoc.createTextNode(OTP));
            response.appendChild(eOTP);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            Element eRESPONSECODEOTP = responsedoc.createElement("RESPONSECODEOTP");
            eRESPONSECODEOTP.appendChild(responsedoc.createTextNode(statusOTP));
            response.appendChild(eRESPONSECODEOTP);

            String inputResMac = MD5Key + status
                    + TRANSID + VERIFYTYPE + REFTRANSID + OTP
                    + DATETIME + DESCRIPTION + PROVIDERID;
            //System.out.print(inputResMac);
            String resMAC = ControllerUtil.EncriptMD5(inputResMac);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            Element eBANKTRANSID = responsedoc.createElement("BANKTRANSID");
            eBANKTRANSID.appendChild(responsedoc.createTextNode(PBANKID));
            response.appendChild(eBANKTRANSID);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String payment(String strXML) {
        try {
            // System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("003")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String CARDNUMBER = eElement.getElementsByTagName("CARDNUMBER").item(0).getTextContent();
            String CARDHOLDERNAME = eElement.getElementsByTagName("CARDHOLDERNAME").item(0).getTextContent();
            String AMOUNT = eElement.getElementsByTagName("AMOUNT").item(0).getTextContent();
            String CURRENCYCODE = eElement.getElementsByTagName("CURRENCYCODE").item(0).getTextContent();
            String MERCHANTID = eElement.getElementsByTagName("MERCHANTID").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();
            String TYPECARD = eElement.getElementsByTagName("TYPECARD").item(0) == null ? "01" : eElement.getElementsByTagName("TYPECARD").item(0).getTextContent();
            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0].toString();
            }
            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + TRANSID + CARDNUMBER + CARDHOLDERNAME + AMOUNT + CURRENCYCODE + MERCHANTID + DATETIME + DESCRIPTION + PROVIDERID);
            //check MAC

            if (!strMAC.endsWith(MAC)) {
                return "98";
            }

            Double dblAmount = Double.valueOf(AMOUNT);

            String otp;
            if (listPartnerOtpNumber.contains(PROVIDERID)) {
                otp = createOtpNum();
            } else {
                otp = createOTP();
            }

            String vCARDNUMBER = CARDNUMBER;
            if (!TYPECARD.equals("01")) {
                vCARDNUMBER = vCARDNUMBER + "#" + TYPECARD;
            }

            String[] result = Helper.getDBI().ONL_PAYMENT(TRANSID, vCARDNUMBER, CARDHOLDERNAME, "", dblAmount, CURRENCYCODE, DATETIME, PROVIDERID, DESCRIPTION, MERCHANTID, "INT", otp);
            String PRESPONSECODE = result[0].toString();
            String PBANKID = "";
            if (PRESPONSECODE.endsWith("00")) {
                PBANKID = result[1].toString();
                String phonenumber = result[2].toString();
                sendOTPSMS(phonenumber, otp, "Ma xac thuc (OTP) giao dịch qua Vi " + PROVIDERID);
                //Gui OTP
            } else {
                DESCRIPTION = PRESPONSECODE + "|" + getDescription(PRESPONSECODE);
                PRESPONSECODE = PRESPONSECODE.substring(0, 2);
            }
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(PRESPONSECODE));
            response.appendChild(RESPONSECODE);

            Element BANKTRANSID = responsedoc.createElement("BANKTRANSID");
            BANKTRANSID.appendChild(responsedoc.createTextNode(PBANKID));
            response.appendChild(BANKTRANSID);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eCARDNUMBER = responsedoc.createElement("CARDNUMBER");
            eCARDNUMBER.appendChild(responsedoc.createTextNode(CARDNUMBER));
            response.appendChild(eCARDNUMBER);

            Element eCARDHOLDERNAME = responsedoc.createElement("CARDHOLDERNAME");
            eCARDHOLDERNAME.appendChild(responsedoc.createTextNode(CARDHOLDERNAME));
            response.appendChild(eCARDHOLDERNAME);

            Element eAMOUNT = responsedoc.createElement("AMOUNT");
            eAMOUNT.appendChild(responsedoc.createTextNode(AMOUNT));
            response.appendChild(eAMOUNT);

            Element eCURRENCYCODE = responsedoc.createElement("CURRENCYCODE");
            eCURRENCYCODE.appendChild(responsedoc.createTextNode(CURRENCYCODE));
            response.appendChild(eCURRENCYCODE);

            Element eMERCHANTID = responsedoc.createElement("MERCHANTID");
            eMERCHANTID.appendChild(responsedoc.createTextNode(MERCHANTID));
            response.appendChild(eMERCHANTID);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);
            String inputResmac = MD5Key + PRESPONSECODE
                    + PBANKID
                    + TRANSID
                    + CARDNUMBER
                    + CARDHOLDERNAME
                    + AMOUNT
                    + CURRENCYCODE
                    + MERCHANTID
                    + DATETIME + DESCRIPTION + PROVIDERID;
            //System.out.println(inputResmac);
            String resMAC = ControllerUtil.EncriptMD5(inputResmac);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     * @throws RemoteException
     * @throws ParserConfigurationException
     */
    public String paymentWithProfileID(String strXML) throws RemoteException, ParserConfigurationException {
        try {
            // System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("004")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String PROFILEID = eElement.getElementsByTagName("PROFILEID").item(0).getTextContent();
            String AMOUNT = eElement.getElementsByTagName("AMOUNT").item(0).getTextContent();
            String CURRENCYCODE = eElement.getElementsByTagName("CURRENCYCODE").item(0).getTextContent();
            String MERCHANTID = eElement.getElementsByTagName("MERCHANTID").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String isVerify = "0";
            BigDecimal LimitAmount;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0].toString();
                isVerify = key[2].toString();
                LimitAmount = BigDecimal.valueOf(Long.valueOf(key[3].toString()));
            }

            String inpusReqMac = MD5Key + CommandCode + TRANSID + PROFILEID + AMOUNT + CURRENCYCODE + MERCHANTID + DATETIME + DESCRIPTION + PROVIDERID;
            // System.out.println(inpusReqMac);
            String strMAC = ControllerUtil.EncriptMD5(inpusReqMac);
            //check MAC

            if (!strMAC.endsWith(MAC)) {
                return "98";
            }

            Double dblAmount = Double.valueOf(AMOUNT);
            if (isVerify.endsWith("1")) {
                // thanh toan co xac thuc
                if (LimitAmount.compareTo(BigDecimal.valueOf(dblAmount)) > 0) {
                    //check han muc de xac thuc neu nho hơn han muc thi khong xac thuc
                    isVerify = "0";
                }
            }
            String[] result;
            String PBANKID = "";
            String PRESPONSECODE;
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, "paymentbyprofileid with isVerify =" + isVerify);
            if (isVerify.endsWith("0")) {
                // Thuc hien thanh toan khong xac thuc
                result = Helper.getDBI().ONL_PAYMENTWITHPROFILEID(TRANSID, PROFILEID, dblAmount, CURRENCYCODE, DATETIME, PROVIDERID, DESCRIPTION, MERCHANTID, "INT");
                PRESPONSECODE = result[2].toString();
                if (PRESPONSECODE.endsWith("00")) {
                    String PCUSTACCCOUNT = result[0].toString();
                    String PPARNERACCOUNT = result[1].toString();
                    PBANKID = result[3].toString();

                    //Thuc hien thanh toan
                    Fcubs fc = new Fcubs();
                    String Desc;
                    if (PROVIDERID.equals("SMARTPAY") || PROVIDERID.equals("PAYOOWL")) {
                        Desc = "NAP TIEN VAO VI VOI THE/TK DA LIEN KET QUA " + PROVIDERID + ".MAGD:" + TRANSID;
                    } else {
                        Desc = "TT TRUC TUYEN VOI THE/TK DA LIEN KET QUA " + PROVIDERID + ".MAGD:" + TRANSID;
                    }
                    Desc = Desc.replace("AIRPAY", "ShopeePay");

                    String CoreRef = fc.transferFCUBSWithProduct(ProductTransfer, PCUSTACCCOUNT, PPARNERACCOUNT, BigDecimal.valueOf(dblAmount), Desc);
                    if (CoreRef == null) {
                        PRESPONSECODE = "01";
                    } else {
                        if (CoreRef.endsWith("TIMEOUT")) {
                            PRESPONSECODE = "99";
                        }
                    }

                    Helper.getDBI().ONL_UpdatePayment(PBANKID, PRESPONSECODE, CoreRef);
                }
            } else {
                // Thuc hien thanh toan co xac thuc
                String otp;
                if (listPartnerOtpNumber.contains(PROVIDERID)) {
                    otp = createOtpNum();
                } else {
                    otp = createOTP();
                }
                result = Helper.getDBI().ONL_PAYMENTWITHPROFILEIDBYVERIFY(TRANSID, PROFILEID, dblAmount, CURRENCYCODE, DATETIME, PROVIDERID, DESCRIPTION, MERCHANTID, "INT", otp);
                PRESPONSECODE = result[0].toString();
                if (PRESPONSECODE.endsWith("00")) {
                    PBANKID = result[1].toString();
                    String phonenumber = result[2].toString();
                    sendOTPSMS(phonenumber, otp, "Ma xac thuc (OTP) giao dịch qua Vi " + PROVIDERID);
                    //Gui OTP
                }
            }
            if (!PRESPONSECODE.endsWith("00")) {
                DESCRIPTION = PRESPONSECODE + "|" + getDescription(PRESPONSECODE);
                PRESPONSECODE = PRESPONSECODE.substring(0, 2);
            }
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(PRESPONSECODE));
            response.appendChild(RESPONSECODE);

            Element BANKTRANSID = responsedoc.createElement("BANKTRANSID");
            BANKTRANSID.appendChild(responsedoc.createTextNode(PBANKID));
            response.appendChild(BANKTRANSID);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element ePROFILEID = responsedoc.createElement("PROFILEID");
            ePROFILEID.appendChild(responsedoc.createTextNode(PROFILEID));
            response.appendChild(ePROFILEID);

            Element eAMOUNT = responsedoc.createElement("AMOUNT");
            eAMOUNT.appendChild(responsedoc.createTextNode(AMOUNT));
            response.appendChild(eAMOUNT);

            Element eCURRENCYCODE = responsedoc.createElement("CURRENCYCODE");
            eCURRENCYCODE.appendChild(responsedoc.createTextNode(CURRENCYCODE));
            response.appendChild(eCURRENCYCODE);

            Element eMERCHANTID = responsedoc.createElement("MERCHANTID");
            eMERCHANTID.appendChild(responsedoc.createTextNode(MERCHANTID));
            response.appendChild(eMERCHANTID);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            Element eISVERIFY = responsedoc.createElement("ISVERIFY");
            eISVERIFY.appendChild(responsedoc.createTextNode(isVerify));
            response.appendChild(eISVERIFY);

            String inputResMac = MD5Key + PRESPONSECODE
                    + PBANKID
                    + TRANSID
                    + PROFILEID
                    + AMOUNT
                    + CURRENCYCODE
                    + MERCHANTID
                    + DATETIME + DESCRIPTION + PROVIDERID;
            String resMAC = ControllerUtil.EncriptMD5(inputResMac);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String checkCard(String strXML) {
        try {
            //  System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("005")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String DESTCARDNUMBER = eElement.getElementsByTagName("DESTCARDNUMBER").item(0).getTextContent();
            String TYPEDESTCARD = eElement.getElementsByTagName("TYPEDESTCARD").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0].toString();
            }

            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + TRANSID + DESTCARDNUMBER + TYPEDESTCARD + DATETIME + DESCRIPTION + PROVIDERID);
            //check MAC

            if (!strMAC.endsWith(MAC)) {
                return "98";
            }

            Helper.writeLog(OnlinePaymentController.class, Level.INFO, "ONL_checkDestNumber req: " + DESTCARDNUMBER);

            String[] result = Helper.getDBI().ONL_checkDestNumber(DESTCARDNUMBER, TYPEDESTCARD);

            Helper.writeLog(OnlinePaymentController.class, Level.INFO, "ONL_checkDestNumber resp: " + result[0]);

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            String pStatus = result[1].toString();
            String pDestName = "";
            if (pStatus.endsWith("00")) {
                String pAccount = result[2].toString();
                pDestName = result[0].toString();
                Element DESTNAME = responsedoc.createElement("DESTNAME");
                DESTNAME.appendChild(responsedoc.createTextNode(pDestName));
                response.appendChild(DESTNAME);
            }
            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(pStatus));
            response.appendChild(RESPONSECODE);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eDESTCARDNUMBER = responsedoc.createElement("DESTCARDNUMBER");
            eDESTCARDNUMBER.appendChild(responsedoc.createTextNode(DESTCARDNUMBER));
            response.appendChild(eDESTCARDNUMBER);

            Element eTYPEDESTCARD = responsedoc.createElement("TYPEDESTCARD");
            eTYPEDESTCARD.appendChild(responsedoc.createTextNode(TYPEDESTCARD));
            response.appendChild(eTYPEDESTCARD);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String resMAC = ControllerUtil.EncriptMD5(MD5Key + pStatus
                    + pDestName
                    + TRANSID
                    + DESTCARDNUMBER
                    + TYPEDESTCARD
                    + DATETIME + DESCRIPTION + PROVIDERID);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String takeOutWallet(String strXML) {
        try {
            //  System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("006")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String DESTCARDNUMBER = eElement.getElementsByTagName("DESTCARDNUMBER").item(0).getTextContent();
            String TYPEDESTCARD = eElement.getElementsByTagName("TYPEDESTCARD").item(0).getTextContent();
            String SOURCEACCOUNT = eElement.getElementsByTagName("SOURCEACCOUNT").item(0).getTextContent();
            String AMOUNT = eElement.getElementsByTagName("AMOUNT").item(0).getTextContent();
            String CURRENCYCODE = eElement.getElementsByTagName("CURRENCYCODE").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0].toString();
            }
            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + TRANSID + DESTCARDNUMBER + TYPEDESTCARD + SOURCEACCOUNT + AMOUNT + CURRENCYCODE + DATETIME + DESCRIPTION + PROVIDERID);
            //check MAC

            if (!strMAC.endsWith(MAC)) {
                return "98";
            }

            Double dblAmount = Double.valueOf(AMOUNT);
            String[] result = Helper.getDBI().ONL_TAKEOUTWALLET(TRANSID, DESTCARDNUMBER,
                    SOURCEACCOUNT, dblAmount, CURRENCYCODE, DATETIME,
                    DESCRIPTION, PROVIDERID, TYPEDESTCARD);
            String pStatus = result[0].toString();
            String PBANKID = "0";
            if (pStatus.endsWith("00")) {
                PBANKID = result[1].toString();
                String pDestAccount = result[2].toString();
                Fcubs fc = new Fcubs();
                String Desc = "";
                if (DESTCARDNUMBER.endsWith("01")) {
                    Desc = "RUT TIEN TU VI VE THE QUA " + PROVIDERID + ".MA GD:" + TRANSID;
                } else {
                    Desc = "RUT TIEN TU VI VE TAI KHOAN QUA " + PROVIDERID + ".MA GD:" + TRANSID;
                }
                Desc = Desc.replace("AIRPAY", "ShopeePay");
                String CoreRef = fc.transferWithReturnTimeOut(ProductTransfer, SOURCEACCOUNT, pDestAccount, BigDecimal.valueOf(dblAmount), Desc, 40);
                if (CoreRef == null) {
                    pStatus = "15";
                }
                if (CoreRef.equals("TIMEOUT")) {
                    // return "99";
                    //return "99";

                    pStatus = "99"; // Cho doi soat
                }

                Helper.getDBI().ONL_UPDATE_TAKEOUTWALLET(PBANKID, CoreRef);
            }

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(pStatus));
            response.appendChild(RESPONSECODE);

            Element BANKTRANSID = responsedoc.createElement("BANKTRANSID");
            BANKTRANSID.appendChild(responsedoc.createTextNode(PBANKID));
            response.appendChild(BANKTRANSID);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eDESTCARDNUMBER = responsedoc.createElement("DESTCARDNUMBER");
            eDESTCARDNUMBER.appendChild(responsedoc.createTextNode(DESTCARDNUMBER));
            response.appendChild(eDESTCARDNUMBER);

            Element eTYPEDESTCARD = responsedoc.createElement("TYPEDESTCARD");
            eTYPEDESTCARD.appendChild(responsedoc.createTextNode(TYPEDESTCARD));
            response.appendChild(eTYPEDESTCARD);

            Element eSOURCEACCOUNT = responsedoc.createElement("SOURCEACCOUNT");
            eSOURCEACCOUNT.appendChild(responsedoc.createTextNode(SOURCEACCOUNT));
            response.appendChild(eSOURCEACCOUNT);

            Element eAMOUNT = responsedoc.createElement("AMOUNT");
            eAMOUNT.appendChild(responsedoc.createTextNode(AMOUNT));
            response.appendChild(eAMOUNT);

            Element eCURRENCYCODE = responsedoc.createElement("CURRENCYCODE");
            eCURRENCYCODE.appendChild(responsedoc.createTextNode(CURRENCYCODE));
            response.appendChild(eCURRENCYCODE);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String inputRes = MD5Key + pStatus
                    + PBANKID
                    + TRANSID
                    + DESTCARDNUMBER
                    + TYPEDESTCARD
                    + SOURCEACCOUNT
                    + AMOUNT
                    + CURRENCYCODE
                    + DATETIME + DESCRIPTION + PROVIDERID;
            // System.out.println(inputRes);
            String resMAC = ControllerUtil.EncriptMD5(inputRes);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String refundPayment(String strXML) {
        try {
            System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("007")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String REFTRANSID = eElement.getElementsByTagName("REFTRANSID").item(0).getTextContent();
            String AMOUNT = eElement.getElementsByTagName("AMOUNT").item(0).getTextContent();
            String CURRENCYCODE = eElement.getElementsByTagName("CURRENCYCODE").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();
            String TYPETRANS = eElement.getElementsByTagName("TYPETRANS").item(0) == null ? "01" : eElement.getElementsByTagName("TYPETRANS").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0].toString();
            }

            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + TRANSID + REFTRANSID + AMOUNT + CURRENCYCODE + DATETIME + DESCRIPTION + PROVIDERID);
            System.out.print(strMAC);
            //check MAC

            if (!strMAC.toUpperCase().endsWith(MAC.toUpperCase())) {
                return "98";
            }

            Double dblAmount = Double.valueOf(AMOUNT);
            String[] result;
            if (TYPETRANS.equals("01")) {
                result = Helper.getDBI().ONL_REFUND(TRANSID, REFTRANSID, dblAmount, DATETIME, DESCRIPTION, PROVIDERID);
            } else {
                result = Helper.getDBI().ONL_REFUND_FOR_TAKE_OUT(TRANSID, REFTRANSID, dblAmount, DATETIME, DESCRIPTION, PROVIDERID);
            }
            String pStatus = result[0].toString();
            String PBANKID = "0";
            if (pStatus.endsWith("00")) {
                String pCustAccount = result[1].toString();
                String pPartnerAccount = result[2].toString();
                PBANKID = result[3].toString();
                Fcubs fc = new Fcubs();
                String Desc = "HOAN TIEN TT TRUC TUYEN QUA " + PROVIDERID + " CHO MAGD: " + REFTRANSID + " .MAGD HOAN TIEN :" + TRANSID;
                Desc = Desc.replace("AIRPAY", "ShopeePay");
                String CoreRef = null;
                if (TYPETRANS.equals("01")) {
                    //refurd cho giao dich payment
                    CoreRef = fc.transferFCUBSWithProduct(ProductTransfer, pPartnerAccount, pCustAccount, BigDecimal.valueOf(dblAmount), Desc);
                } else {
                    //refurd cho giao dich chuyen tien
                    CoreRef = fc.transferFCUBSWithProduct(mmsQRProductCode, pCustAccount, pPartnerAccount, BigDecimal.valueOf(dblAmount), Desc);
                }
                if (CoreRef == null) {
                    pStatus = "04";
                } else if (CoreRef.equals("TIMEOUT")) {
                    pStatus = "99";
                }
                Helper.getDBI().ONL_UPDATE_REFUND(PBANKID, CoreRef);
            }

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(pStatus));
            response.appendChild(RESPONSECODE);

            Element BANKTRANSID = responsedoc.createElement("BANKTRANSID");
            BANKTRANSID.appendChild(responsedoc.createTextNode(PBANKID));
            response.appendChild(BANKTRANSID);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eREFTRANSID = responsedoc.createElement("REFTRANSID");
            eREFTRANSID.appendChild(responsedoc.createTextNode(REFTRANSID));
            response.appendChild(eREFTRANSID);

            Element eAMOUNT = responsedoc.createElement("AMOUNT");
            eAMOUNT.appendChild(responsedoc.createTextNode(AMOUNT));
            response.appendChild(eAMOUNT);

            Element eCURRENCYCODE = responsedoc.createElement("CURRENCYCODE");
            eCURRENCYCODE.appendChild(responsedoc.createTextNode(CURRENCYCODE));
            response.appendChild(eCURRENCYCODE);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String resMAC = ControllerUtil.EncriptMD5(MD5Key + pStatus
                    + PBANKID
                    + TRANSID
                    + REFTRANSID
                    + AMOUNT
                    + CURRENCYCODE
                    + DATETIME + DESCRIPTION + PROVIDERID);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String revertTakeOutWallet(String strXML) {
        try {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, "REQUEST HUY RUT TIEN VE THE/TK:" + strXML);

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("008")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String REFTRANSID = eElement.getElementsByTagName("REFTRANSID").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0].toString();
            }

            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + TRANSID + REFTRANSID + DATETIME + DESCRIPTION + PROVIDERID);
            //check MAC

            if (!strMAC.endsWith(MAC)) {
                return "98";
            }

            String[] result = Helper.getDBI().ONL_REVERT_TAKEOUTWALLET(REFTRANSID, PROVIDERID);
            String pStatus = result[1].toString();
            if (pStatus.endsWith("00")) {
                String PREFCORE = result[0].toString();
                Fcubs fc = new Fcubs();
                String Desc = "HUY RUT TIEN TU VI VE THE/TK QUA " + PROVIDERID + ".MAGD:" + TRANSID;
                BigDecimal amount = BigDecimal.valueOf(Double.valueOf(result[2]));
                String DestNumber = result[3];
                String SourceAccount = result[4];
                String refcore = fc.transferFCUBSWithProduct(ProductTransfer, DestNumber, SourceAccount, amount, Desc);
                // String resultRV = fc.revertTransferFCUBS(PREFCORE, 60);
                Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, "REFCORE HUY GIAO DICH RUT TIEN:" + refcore);
                if (refcore == null) {
                    pStatus = "02";
                } else {
                    Helper.getDBI().ONL_UPDATE_REVERT_TAKEOUTWALLET(TRANSID, REFTRANSID, PROVIDERID, DATETIME, DESCRIPTION);
                }
            }

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(pStatus));
            response.appendChild(RESPONSECODE);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eREFTRANSID = responsedoc.createElement("REFTRANSID");
            eREFTRANSID.appendChild(responsedoc.createTextNode(REFTRANSID));
            response.appendChild(eREFTRANSID);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String resMAC = ControllerUtil.EncriptMD5(MD5Key + pStatus
                    + TRANSID
                    + REFTRANSID
                    + DATETIME + DESCRIPTION + PROVIDERID);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String queryTransaction(String strXML) {
        try {
            //  System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("009")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String REFTRANSID = eElement.getElementsByTagName("REFTRANSID").item(0).getTextContent();
            String TRANSTYPE = eElement.getElementsByTagName("TRANSTYPE").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0) == null ? "" : eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0].toString();
            }

            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + TRANSID + REFTRANSID + TRANSTYPE + DATETIME + DESCRIPTION + PROVIDERID);
            //check MAC

            if (!strMAC.toUpperCase().equals(MAC.toUpperCase())) {
                return "98";
            }

            String[] result = Helper.getDBI().ONL_queryTransaction(REFTRANSID, TRANSTYPE, PROVIDERID);
            String pStatus = result[0].toString();

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(pStatus));
            response.appendChild(RESPONSECODE);
            if (pStatus.endsWith("00")) {
                String pAmount = result[1].toString();
                String pCCY = result[2].toString();
                String pAddInfo = result[3].toString();

                Element AMOUNT = responsedoc.createElement("AMOUNT");
                AMOUNT.appendChild(responsedoc.createTextNode(pAmount));
                response.appendChild(AMOUNT);

                Element CURRENCYCODE = responsedoc.createElement("CURRENCYCODE");
                CURRENCYCODE.appendChild(responsedoc.createTextNode(pCCY));
                response.appendChild(CURRENCYCODE);

                Element ADDINFO = responsedoc.createElement("ADDINFO");
                ADDINFO.appendChild(responsedoc.createTextNode(pAddInfo));
                response.appendChild(ADDINFO);
            }
            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eREFTRANSID = responsedoc.createElement("REFTRANSID");
            eREFTRANSID.appendChild(responsedoc.createTextNode(REFTRANSID));
            response.appendChild(eREFTRANSID);

            Element eTRANSTYPE = responsedoc.createElement("TRANSTYPE");
            eTRANSTYPE.appendChild(responsedoc.createTextNode(TRANSTYPE));
            response.appendChild(eTRANSTYPE);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String responseData = MD5Key + pStatus + TRANSID + REFTRANSID + TRANSTYPE
                    + DATETIME + DESCRIPTION + PROVIDERID;

            String resMAC = ControllerUtil.EncriptMD5(responseData);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut, format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String destroyProfileID(String strXML) {
        try {
            // System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("010")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String PROFILEID = eElement.getElementsByTagName("PROFILEID").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0].toString();
            }

            String inpusReqMac = MD5Key + CommandCode + TRANSID + DATETIME + PROFILEID + DESCRIPTION + PROVIDERID;
            // System.out.println(inpusReqMac);
            String strMAC = ControllerUtil.EncriptMD5(inpusReqMac);
            //check MAC

            if (!strMAC.endsWith(MAC)) {
                return "98";
            }
            String otp = createOTP();
            String[] result = Helper.getDBI().ONL_DestroyConnectCard(TRANSID, DATETIME, PROFILEID, DESCRIPTION, PROVIDERID, otp);

            String STATUS = result[0].toString();
            /* if(STATUS.endsWith("00"))
                {
                    String phonenumber= result[1].toString();
                    (phonenumber, otp, "Xac thuc huy lien ket the");
                }*/

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(STATUS));
            response.appendChild(RESPONSECODE);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element ePROFILEID = responsedoc.createElement("PROFILEID");
            ePROFILEID.appendChild(responsedoc.createTextNode(PROFILEID));
            response.appendChild(ePROFILEID);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String inputResMac = MD5Key + STATUS
                    + TRANSID + DATETIME
                    + PROFILEID + DESCRIPTION + PROVIDERID;
            String resMAC = ControllerUtil.EncriptMD5(inputResMac);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String paymentAndRegister(String strXML) {
        try {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("011")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String CARDNUMBER = eElement.getElementsByTagName("CARDNUMBER").item(0).getTextContent();
            String CARDHOLDERNAME = eElement.getElementsByTagName("CARDHOLDERNAME").item(0).getTextContent();
            String AMOUNT = eElement.getElementsByTagName("AMOUNT").item(0).getTextContent();
            String CURRENCYCODE = eElement.getElementsByTagName("CURRENCYCODE").item(0).getTextContent();
            String MERCHANTID = eElement.getElementsByTagName("MERCHANTID").item(0).getTextContent();
            String ISRETURNPROFILEID = eElement.getElementsByTagName("ISRETURNPROFILEID").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();
            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0].toString();
            }
            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + TRANSID + CARDNUMBER + CARDHOLDERNAME + AMOUNT + CURRENCYCODE + MERCHANTID + ISRETURNPROFILEID + DATETIME + DESCRIPTION + PROVIDERID);
            //check MAC

            if (!strMAC.endsWith(MAC)) {
                return "98";
            }

            Double dblAmount = Double.valueOf(AMOUNT);
            String otp = createOTP();
            //PAYMENT
            String[] result = Helper.getDBI().ONL_PAYMENT(TRANSID, CARDNUMBER, CARDHOLDERNAME, "", dblAmount, CURRENCYCODE, DATETIME, PROVIDERID, DESCRIPTION, MERCHANTID, "INT", otp);
            String PRESPONSECODE = result[0].toString();
            String PBANKID = "0";
            if (PRESPONSECODE.endsWith("00")) {
                if (ISRETURNPROFILEID.equals("1")) {
                    //UPDATE Loai giao dich xac thuc thanh 04
                    Helper.getDBI().PaymentAndRegister(TRANSID, PROVIDERID);
                    //thuc hien RERGISTER
                    String[] result2 = Helper.getDBI().ONL_VERIFICARD(TRANSID, DATETIME, CARDNUMBER, CARDHOLDERNAME, otp, PROVIDERID, DESCRIPTION, "01");
                    String status = result2[0].toString();
                    if (status.endsWith("00")) {

                        PBANKID = result[1].toString();
                        String phonenumber = result[2].toString();
                        //Gui OTP
                        int sendSMS = sendOTPSMS(phonenumber, otp, "Ma xac thuc thanh toan truc tuyen va dang ky lien ket vi " + PROVIDERID);
                        if (sendSMS == 0) {
                            return "99";
                        }
                    }
                } else {
                    PBANKID = result[1].toString();
                    String phonenumber = result[2].toString();
                    //Gui OTP
                    int sendSMS = sendOTPSMS(phonenumber, otp, "Ma xac thuc thanh toan truc tuyen qua " + PROVIDERID);
                    if (sendSMS == 0) {
                        return "99";
                    }
                }
            } else {
                DESCRIPTION = PRESPONSECODE + "|" + getDescription(PRESPONSECODE);
                PRESPONSECODE = PRESPONSECODE.substring(0, 2);
            }
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(PRESPONSECODE));
            response.appendChild(RESPONSECODE);

            Element BANKTRANSID = responsedoc.createElement("BANKTRANSID");
            BANKTRANSID.appendChild(responsedoc.createTextNode(PBANKID));
            response.appendChild(BANKTRANSID);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eCARDNUMBER = responsedoc.createElement("CARDNUMBER");
            eCARDNUMBER.appendChild(responsedoc.createTextNode(CARDNUMBER));
            response.appendChild(eCARDNUMBER);

            Element eCARDHOLDERNAME = responsedoc.createElement("CARDHOLDERNAME");
            eCARDHOLDERNAME.appendChild(responsedoc.createTextNode(CARDHOLDERNAME));
            response.appendChild(eCARDHOLDERNAME);

            Element eAMOUNT = responsedoc.createElement("AMOUNT");
            eAMOUNT.appendChild(responsedoc.createTextNode(AMOUNT));
            response.appendChild(eAMOUNT);

            Element eCURRENCYCODE = responsedoc.createElement("CURRENCYCODE");
            eCURRENCYCODE.appendChild(responsedoc.createTextNode(CURRENCYCODE));
            response.appendChild(eCURRENCYCODE);

            Element eMERCHANTID = responsedoc.createElement("MERCHANTID");
            eMERCHANTID.appendChild(responsedoc.createTextNode(MERCHANTID));
            response.appendChild(eMERCHANTID);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);
            String inputResmac = MD5Key + PRESPONSECODE
                    + PBANKID
                    + TRANSID
                    + CARDNUMBER
                    + CARDHOLDERNAME
                    + AMOUNT
                    + CURRENCYCODE
                    + MERCHANTID
                    + DATETIME + DESCRIPTION + PROVIDERID;
            // System.out.println(inputResmac);
            String resMAC = ControllerUtil.EncriptMD5(inputResmac);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String getProfileID(String strXML) {
        try {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("012")) {
                return "99";
            }
            String REFTRANSID = eElement.getElementsByTagName("REFTRANSID").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0].toString();
            }

            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + REFTRANSID + DATETIME + PROVIDERID);
            //check MAC

            if (!strMAC.endsWith(MAC)) {
                return "98";
            }

            String profileid = Helper.getDBI().getProfileID(REFTRANSID, PROVIDERID);
            String ResponseCode = "00";
            if (profileid == null) {
                profileid = "";
                ResponseCode = "01";
            }

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(ResponseCode));
            response.appendChild(RESPONSECODE);

            Element eREFTRANSID = responsedoc.createElement("REFTRANSID");
            eREFTRANSID.appendChild(responsedoc.createTextNode(REFTRANSID));
            response.appendChild(eREFTRANSID);

            Element ePROFILEID = responsedoc.createElement("PROFILEID");
            ePROFILEID.appendChild(responsedoc.createTextNode(profileid));
            response.appendChild(ePROFILEID);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String resMAC = ControllerUtil.EncriptMD5(MD5Key + ResponseCode
                    + REFTRANSID
                    + profileid
                    + DATETIME + PROVIDERID);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }

    /* --- SUNRISE --- */

 /* VNPAY */
    /**
     *
     * @param strXML
     * @return
     */
    public String getAccountBalance(String strXML) {
        HashMap<String, String> response = new LinkedHashMap<String, String>();
        try {
            Helper.writeLog(this.getClass(), Level.INFO, "***************** START GET ACCOUNT BALANCE *****************");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element element = (Element) nodeRoot;

            String providerID = element.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String commandCode = element.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            String accountNumber = element.getElementsByTagName("ACCOUNTNUMBER").item(0).getTextContent();
            String mac = element.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key = CommonUtils.getMD5Key(providerID);

            String[] key = Helper.getDBI().ONL_GetMACkeys(providerID);
            MD5Key = key[0].toString();
            if (MD5Key.isEmpty()) {
                return XMLUtils.systemIsError("98", "Does not found MD5 key of provider [" + providerID + "]");
            }
            String AccountPartner = key[4].toString();
            if (!accountNumber.endsWith(AccountPartner)) {
                //return 
                return XMLUtils.systemIsError("99", "Account is mismatched with account of provider [" + providerID + "]");
            }
            if (!"013".equals(commandCode)) {
                return XMLUtils.systemIsError("99", "CommandCode is wrong. [" + commandCode + "] != [007]");
            }

            String data = MD5Key + commandCode + accountNumber + providerID;
            String strMAC = ControllerUtil.EncriptMD5(data);

            if (!strMAC.equals(mac)) {
                return XMLUtils.systemIsError("98", "MAC is wrong. SCB = [" + strMAC + "], " + providerID
                        + " = [" + mac + "]");
            }
            Object[] result;
            if("WU".equals(providerID))
            {
                result = Helper.getDBI().getAccountBalance_wu(accountNumber);
            } else {
                result = Helper.getDBI().getAccountBalance(accountNumber);
            }
            
            String responseCode = "01";
            //String availableBalance = "0"; --> The value is unnecessary
            String currentBalance = "0";
            String description = "";
            if (result != null && result.length == 3) {
                String status = result[2].toString();
                if ("O".equals(status)) {
                    try {
                        BigDecimal nBalance = (BigDecimal) result[1];
                        currentBalance = nBalance.toPlainString();
                        responseCode = "00";
                        description = "Finish to query account balance";

                        /*
                        if(CommonUtils.tryParseDoubleType(result[0]) && CommonUtils.tryParseDoubleType(result[1]))
                        {
                            //availableBalance = result[0]; --> The value is unnecessary
                            currentBalance = BigDecimal.valueOf(Long.valueOf(result[1])).toString();
                            responseCode = "00";
                            description = "Finish to query account balance";
                        }*/
                    } catch (NumberFormatException e) {
                        description = "The formating of account balance is wrong. AccountBlance = [" + currentBalance + "]";
                        Helper.writeLogError(this.getClass(), e.getMessage());
                    }
                } else {
                    description = "Account is not available for query because status = [" + status + "]";
                }
            }
            response.put("RESPONSECODE", responseCode);
            response.put("ACCOUNTNUMBER", accountNumber);
            response.put("ACCOUNTBALANCE", currentBalance);
            response.put("DESCRIPTION", description);
            response.put("PROVIDERID", providerID);
            return XMLUtils.xmlParser(response, MD5Key);
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, e);
            return XMLUtils.systemIsError("99", "System is error.");
        } finally {
            Helper.writeLog(this.getClass(), Level.INFO, "***************** END GET ACCOUNT BALANCE *****************");
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String refundPaymentWithAccount(String strXML) {
        HashMap<String, String> response = new HashMap<>();
        try {
            Helper.writeLog(this.getClass(), Level.INFO, "***************** START REFUND PAYMENT WITH ACCOUNT *****************");
            Helper.writeLog(this.getClass(), Level.INFO, "strXML = [" + strXML + "]");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element element = (Element) nodeRoot;
            String commandCode = element.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            if (!"007".equals(commandCode)) {
                response.put("RESPONSECODE", "99");
                response.put("DESCRIPTION", "CommandCode is wrong. [" + commandCode + "] != [007]");
                Helper.writeLog(this.getClass(), Level.INFO, "CommandCode = [" + commandCode + "] != 007");
                return XMLUtils.xmlParser(response);
            }
            String TRANSID = element.getElementsByTagName("TRANSID").item(0).getTextContent();
            String REFTRANSID = element.getElementsByTagName("REFTRANSID").item(0).getTextContent();
            String AMOUNT = element.getElementsByTagName("AMOUNT").item(0).getTextContent();
            String CURRENCYCODE = element.getElementsByTagName("CURRENCYCODE").item(0).getTextContent();
            String DATETIME = element.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = element.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = element.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = element.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                response.put("RESPONSECODE", "98");
                response.put("DESCRIPTION", "Does not found MAC key of provider [" + PROVIDERID + "]");
                return XMLUtils.xmlParser(response);
            } else {
                MD5Key = key[0];
            }
            String dataToEncrypt = MD5Key + commandCode + TRANSID + REFTRANSID + AMOUNT + CURRENCYCODE
                    + DATETIME + DESCRIPTION + PROVIDERID;
            Helper.writeLog(this.getClass(), Level.INFO, "Data before encryption = ["
                    + dataToEncrypt + "]");

            String strMAC = ControllerUtil.EncriptMD5(dataToEncrypt);
            if (!strMAC.endsWith(MAC)) {
                response.put("RESPONSECODE", "98");
                response.put("DESCRIPTION", "MAC is wrong. SCB = [" + strMAC + "], " + PROVIDERID + " = [" + MAC + "]");
                Helper.writeLog(this.getClass(), Level.INFO, "MD5 = [" + strMAC + "], MAC = [" + MAC + "]");
                return XMLUtils.xmlParser(response);
            }

            Double dblAmount = Double.valueOf(AMOUNT);
            String[] result = Helper.getDBI().REFUND_PAYMENT_WITH_ACCOUNT(TRANSID, REFTRANSID,
                    dblAmount, DATETIME, DESCRIPTION, PROVIDERID);
            String pStatus = result[0];
            String PBANKID = "0";
            Helper.writeLog(this.getClass(), Level.INFO, "ONL_REFUND_PAYMENT_WITH_ACCOUNT DBI --> status = [" + pStatus + "]");
            if ("00".equals(pStatus)) {
                String pCustAccount = result[1];
                String pPartnerAccount = result[2];
                PBANKID = result[3];
                Fcubs fc = new Fcubs();
                String Desc = "HOAN TIEN TT TRUC TUYEN QUA " + PROVIDERID + ". MAGD: " + REFTRANSID;
                String coreRef = fc.transferFCUBSWithProduct(ProductTransfer, pPartnerAccount, pCustAccount,
                        BigDecimal.valueOf(dblAmount), Desc);
                Helper.writeLog(this.getClass(), Level.INFO, "CoreRef = [" + coreRef + "]");

                if (coreRef != null && !coreRef.isEmpty()) {
                    Helper.getDBI().ONL_UPDATE_REFUND(PBANKID, coreRef);
                    DESCRIPTION = "DA HOAN TAT CHUYEN TIEN THANH CONG DEN TAI KHOAN "
                            + pCustAccount;
                } else {
                    pStatus = "04";
                    DESCRIPTION = "CORE BANKING KHONG THE THUC HIEN CHUYEN TIEN DEN TAI KHOAN "
                            + pCustAccount;
                }

            }
            response.put("RESPONSECODE", pStatus);
            response.put("BANKTRANSID", PBANKID);
            response.put("TRANSID", TRANSID);
            response.put("REFTRANSID", REFTRANSID);
            response.put("AMOUNT", AMOUNT);
            response.put("CURRENCYCODE", CURRENCYCODE);
            response.put("DATETIME", DATETIME);
            response.put("DESCRIPTION", DESCRIPTION);
            response.put("PROVIDERID", PROVIDERID);

            Helper.writeLog(this.getClass(), Level.INFO, "***************** END REFUND PAYMENT WITH ACCOUNT *****************");
            return XMLUtils.xmlParser(response, MD5Key);
        } catch (ParserConfigurationException | SAXException | IOException | DOMException | NumberFormatException ex) {
            response.put("RESPONSECODE", "99");
            response.put("DESCRIPTION", "System is error.");
            Helper.writeLog(this.getClass(), Level.ERROR, "refundPaymentWithAccount failed because ["
                    + ex.getMessage() + "]");
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return XMLUtils.xmlParser(response);
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String paymentWithAccount(String strXML) {
        HashMap<String, String> response = new HashMap<>();
        try {
            Helper.writeLog(this.getClass(), Level.INFO, "***************** START PAYMENT WITH ACCOUNT *****************");
            Helper.writeLog(this.getClass(), Level.INFO, "strXML = [" + strXML + "]");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String commandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            if (!"013".equals(commandCode)) {
                response.put("RESPONSECODE", "99");
                response.put("DESCRIPTION", "CommandCode is wrong. [" + commandCode + "] != [013]");
                Helper.writeLog(this.getClass(), Level.INFO, "CommandCode = [" + commandCode + "] != 013");
                return XMLUtils.xmlParser(response);
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String CUSTACCOUNT = eElement.getElementsByTagName("ACCOUNT").item(0).getTextContent();
            String AMOUNT = eElement.getElementsByTagName("AMOUNT").item(0).getTextContent();
            String CURRENCYCODE = eElement.getElementsByTagName("CURRENCYCODE").item(0).getTextContent();
            String MERCHANTID = eElement.getElementsByTagName("MERCHANTID").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                response.put("RESPONSECODE", "98");
                response.put("DESCRIPTION", "Does not found MAC key of provider [" + PROVIDERID + "]");
                return XMLUtils.xmlParser(response);
            } else {
                MD5Key = key[0];
            }

            String inpusReqMac = MD5Key + commandCode + TRANSID + CUSTACCOUNT + AMOUNT
                    + CURRENCYCODE + MERCHANTID + DATETIME + DESCRIPTION + PROVIDERID;
            Helper.writeLog(this.getClass(), Level.INFO, "Data before encryption = ["
                    + inpusReqMac + "]");
            String strMAC = ControllerUtil.EncriptMD5(inpusReqMac);

            //check MAC
            if (!strMAC.endsWith(MAC)) {
                response.put("RESPONSECODE", "98");
                response.put("DESCRIPTION", "MAC is wrong. SCB = [" + strMAC + "], " + PROVIDERID + " = [" + MAC + "]");
                Helper.writeLog(this.getClass(), Level.INFO, "MD5 = [" + strMAC + "], MAC = [" + MAC + "]");
                return XMLUtils.xmlParser(response);
            }

            Double dblAmount = Double.valueOf(AMOUNT);

            String[] result = Helper.getDBI().ONL_PAYMENTWITHACCOUNT(TRANSID, CUSTACCOUNT, dblAmount,
                    CURRENCYCODE, DATETIME, PROVIDERID, DESCRIPTION, MERCHANTID);

            String PBANKID = "0";
            String PRESPONSECODE = result[1];
            String PDESCRIPTION = result[3];
            Helper.writeLog(this.getClass(), Level.INFO, "PRESPONSECODE = [" + PRESPONSECODE + "]");
            if ("00".equals(PRESPONSECODE)) {
                String PPARNERACCOUNT = result[0];
                PBANKID = result[2];
                Fcubs fc = new Fcubs();
                String Desc = "TT TRUC TUYEN VOI THE DA LIEN KET QUA " + PROVIDERID + ".MAGD: " + TRANSID;
                String CoreRef = fc.transferFCUBSWithProduct(ProductTransfer, CUSTACCOUNT, PPARNERACCOUNT,
                        BigDecimal.valueOf(dblAmount), Desc);
                Helper.writeLog(this.getClass(), Level.INFO, "CoreRef = [" + CoreRef + "]");
                if (CoreRef == null) {
                    PRESPONSECODE = "01";
                    PDESCRIPTION = "CORE BANKING KHONG THE THUC HIEN CHUYEN TIEN DEN TAI KHOAN "
                            + CUSTACCOUNT;
                } else {
                    PDESCRIPTION = "DA HOAN TAT CHUYEN TIEN THANH CONG DEN TAI KHOAN "
                            + CUSTACCOUNT;
                }

                Helper.getDBI().ONL_UpdatePayment(PBANKID, PRESPONSECODE, CoreRef);
            }
            IIBCurrentAccountService iibAccService = new IIBCurrentAccountService();
            RetrieveCurrentAccountCASA_out_Type iibCurrentAccountCASA = iibAccService.retrieveCurrentAccountCASARestSimple(Configuration.getIIBContext(), CUSTACCOUNT, IIBConstants.CHANNEL_MOBILE);
            if (iibCurrentAccountCASA != null && iibCurrentAccountCASA.getAccountInfo() != null) {
                response.put("RESPONSECODE", PRESPONSECODE);
                response.put("BANKTRANSID", PBANKID);
                response.put("TRANSID", TRANSID);
                response.put("CUSTACCOUNT", CUSTACCOUNT);
                response.put("AMOUNT", AMOUNT);
                response.put("CURRENCYCODE", CURRENCYCODE);
                response.put("MERCHANTID", MERCHANTID);
                response.put("AVAILABLEBALANCE", String.valueOf(iibCurrentAccountCASA.getAccountInfo().getAccountBalanceAvailable().longValue()));
                response.put("CURRENTBALANCE", String.valueOf(iibCurrentAccountCASA.getAccountInfo().getAccountBalance().longValue()));
                response.put("DATETIME", DATETIME);
                response.put("DESCRIPTION", PDESCRIPTION);
                response.put("PROVIDERID", PROVIDERID);
            } else {
                response.put("RESPONSECODE", PRESPONSECODE);
                response.put("BANKTRANSID", PBANKID);
                response.put("TRANSID", TRANSID);
                response.put("CUSTACCOUNT", CUSTACCOUNT);
                response.put("AMOUNT", AMOUNT);
                response.put("CURRENCYCODE", CURRENCYCODE);
                response.put("MERCHANTID", MERCHANTID);
                response.put("DATETIME", DATETIME);
                response.put("DESCRIPTION", PDESCRIPTION);
                response.put("PROVIDERID", PROVIDERID);
            }

            Helper.writeLog(this.getClass(), Level.INFO, "***************** END PAYMENT WITH ACCOUNT *****************");
            return XMLUtils.xmlParser(response, MD5Key);
        } catch (ParserConfigurationException | SAXException | IOException | DOMException | NumberFormatException | BianException ex) {
            response.put("RESPONSECODE", "99");
            response.put("DESCRIPTION", "System is error.");
            Helper.writeLog(this.getClass(), Level.ERROR, "paymentWithAccount failed because ["
                    + ex.getMessage() + "]");
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return XMLUtils.xmlParser(response);
        }
    }

    /* VNPAY */

 /* ****************************** NGUYEN DAC BINH MINH ****************************** */
    /**
     *
     * @param strXML
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public String OTPREQUEST(String strXML) throws ParserConfigurationException, SAXException, IOException {
        try {
            // System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            if (!CommandCode.endsWith("014")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String PHONENUMBER = eElement.getElementsByTagName("PHONENUMBER").item(0).getTextContent();
            String CHANNELID = eElement.getElementsByTagName("CHANNELID").item(0).getTextContent();
            String TRANSTYPE = eElement.getElementsByTagName("TRANSTYPE").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, "key.length == 0");
                return "98";
            } else {
                MD5Key = key[0];
            }

            String data = MD5Key + CommandCode + TRANSID + PHONENUMBER + CHANNELID + TRANSTYPE
                    + DESCRIPTION + PROVIDERID;

            String strMAC = ControllerUtil.EncriptMD5(data);

            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class,
                    "MD5 truoc khi ma hoa = [" + data + "], MD5 = [" + strMAC + "], MAC = [" + MAC + "]");

            if (!strMAC.endsWith(MAC)) {
                return "98";
            }

            String banktransid = "0";
            String otp = createOTP();

            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, "OTP = [" + otp + "]");

            //String otp = "SUNRISE";
            String[] result = Helper.getDBI().OTP_REQUEST(TRANSID, PROVIDERID, PHONENUMBER,
                    CHANNELID, TRANSTYPE, otp);
            String status = result[0];

            if (status.endsWith("00")) {
                banktransid = result[1];
                int sendSMS = sendOTPSMS(PHONENUMBER, otp, DESCRIPTION);
                if (sendSMS == 0) {
                    return "99";
                }
            }
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(status));
            response.appendChild(RESPONSECODE);

            Element BANKTRANSID = responsedoc.createElement("BANKTRANSID");
            BANKTRANSID.appendChild(responsedoc.createTextNode(banktransid));
            response.appendChild(BANKTRANSID);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element ePHONENUMBER = responsedoc.createElement("PHONENUMBER");
            ePHONENUMBER.appendChild(responsedoc.createTextNode(PHONENUMBER));
            response.appendChild(ePHONENUMBER);

            Element eCHANNELID = responsedoc.createElement("CHANNELID");
            eCHANNELID.appendChild(responsedoc.createTextNode(CHANNELID));
            response.appendChild(eCHANNELID);

            Element eTRANSTYPE = responsedoc.createElement("TRANSTYPE");
            eTRANSTYPE.appendChild(responsedoc.createTextNode(TRANSTYPE));
            response.appendChild(eTRANSTYPE);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String resMAC = ControllerUtil.EncriptMD5(MD5Key + status + banktransid + TRANSID
                    + PHONENUMBER + CHANNELID + TRANSTYPE + DESCRIPTION + PROVIDERID);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut, format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (ParserConfigurationException e) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, e);
            return "99";
        } catch (SAXException e) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, e);
            return "99";
        } catch (IOException e) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, e);
            return "99";
        } catch (DOMException e) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, e);
            return "99";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String OTPRESPONSE(String strXML) {
        try {
            // System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            if (!CommandCode.endsWith("015")) {
                return "99";
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String REFTRANSID = eElement.getElementsByTagName("REFTRANSID").item(0).getTextContent();
            String CHANNELID = eElement.getElementsByTagName("CHANNELID").item(0).getTextContent();
            String TRANSTYPE = eElement.getElementsByTagName("TRANSTYPE").item(0).getTextContent();
            String OTP = eElement.getElementsByTagName("OTP").item(0).getTextContent();
            String TRANSDATE = eElement.getElementsByTagName("TRANSDATE").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return "98";
            } else {
                MD5Key = key[0];
            }

            String data = MD5Key + CommandCode + TRANSID + REFTRANSID + CHANNELID + TRANSTYPE + OTP
                    + TRANSDATE + PROVIDERID;
            String strMAC = ControllerUtil.EncriptMD5(data);

            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class,
                    "MD5 truoc khi ma hoa = [" + data + "], MD5 = [" + strMAC + "], MAC = [" + MAC + "]");

            if (!strMAC.endsWith(MAC)) {
                return "98";
            }

            String[] result = Helper.getDBI().OTP_RESPONSE(TRANSID, REFTRANSID, PROVIDERID,
                    CHANNELID, TRANSTYPE, OTP, TRANSDATE);
            String status = result[0];
            String banktransid = result[1];
            String description = result[2];

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(status));
            response.appendChild(RESPONSECODE);

            Element BANKTRANSID = responsedoc.createElement("BANKTRANSID");
            BANKTRANSID.appendChild(responsedoc.createTextNode(banktransid));
            response.appendChild(BANKTRANSID);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eREFTRANSID = responsedoc.createElement("REFTRANSID");
            eREFTRANSID.appendChild(responsedoc.createTextNode(REFTRANSID));
            response.appendChild(eREFTRANSID);

            Element eCHANNELID = responsedoc.createElement("CHANNELID");
            eCHANNELID.appendChild(responsedoc.createTextNode(CHANNELID));
            response.appendChild(eCHANNELID);

            Element eTRANSTYPE = responsedoc.createElement("TRANSTYPE");
            eTRANSTYPE.appendChild(responsedoc.createTextNode(TRANSTYPE));
            response.appendChild(eTRANSTYPE);

            Element eOTP = responsedoc.createElement("OTP");
            eOTP.appendChild(responsedoc.createTextNode(OTP));
            response.appendChild(eOTP);

            Element eTRANSDATE = responsedoc.createElement("TRANSDATE");
            eTRANSDATE.appendChild(responsedoc.createTextNode(TRANSDATE));
            response.appendChild(eTRANSDATE);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(description));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String resMAC = ControllerUtil.EncriptMD5(MD5Key + status + banktransid + TRANSID
                    + REFTRANSID + CHANNELID + TRANSTYPE + OTP + TRANSDATE + description + PROVIDERID);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut, format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (ParserConfigurationException e) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, e);
            return "99";
        } catch (SAXException e) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, e);
            return "99";
        } catch (IOException e) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, e);
            return "99";
        } catch (DOMException e) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, e);
            return "99";
        }
    }

    /**
     *
     * @param phonenumber
     * @param Content
     * @return
     */
    public String sendSMSOTP(String phonenumber, String Content) {
        //3. Gui SMS
        String otp = createOTP();
        ControllerImpl ci = new ControllerImpl("EBK");
        //ci.sendsms(phonenum, rsu);

        Date currentDate = new Date();
        SimpleDateFormat fTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        String contentSMS = Content + " cua khach hang qua SMS luc " + fTime.format(currentDate) + " ngay " + fDate.format(currentDate) + " la: " + otp;
        //ci.sendsms(phonenum, contentSMS);                   

        Sms sms = new Sms();
        int Result = sms.sendsms(phonenumber, contentSMS, "GW", "SCB");

        if (Result == 0) {
            return otp;
        }// thanh cong
        else {
            return null;
        }
    }

    private String getDescription(String responseCode) {
        switch (responseCode) {
            case "00":
                return "THANH CONG.";
            case "01":
                return "GIAO DICH THAT BAI.";
            case "02":
                return "THE BI DONG HOAC TAM KHOA.";
            case "03":
                return "THE CHUA DUOC DANG KY";
            case "04":
                return "CAC THONG TIN VE THE BI SAI";
            case "05":
                return "THE KHONG DU SO DU";
            case "061":
                return "SO TIEN NHO HON HOAN HAN MUC TOI THIEU";
            case "062":
                return "SO TIEN VUOT HAN MUC 1 LAN GIAO DICH.";
            case "063":
                return "SO TIEN VUOT HAN MUC 1 NGAY GIAO DICH.";
            case "07":
                return "MA GIAO DICH DA TON TAI.";
            case "08":
                return "KHONG TON TAI PROFILEID";
            case "09":
                return "THE HET HAN";
            case "10":
                return "TAI KHOAN BI DONG HOAC KHONG TON TAI.";
            case "12":
                return "THE CHUA DANG KY SO DIEN THOAI NAO.";
            default:
                return "GIAO DICH LOI";
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String checkPayment(String strXML) {
        HashMap<String, String> response = new LinkedHashMap<String, String>();
        try {
            Helper.writeLog(this.getClass(), Level.INFO, "***************** START GET ACCOUNT BALANCE *****************");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element element = (Element) nodeRoot;

            String providerID = element.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String commandCode = element.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            String Amount = element.getElementsByTagName("AMOUNT").item(0).getTextContent();
            String PROFILEID = element.getElementsByTagName("PROFILEID").item(0).getTextContent();
            String mac = element.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key = CommonUtils.getMD5Key(providerID);

            String[] key = Helper.getDBI().ONL_GetMACkeys(providerID);
            MD5Key = key[0].toString();
            if (MD5Key.isEmpty()) {
                return XMLUtils.systemIsError("98", "Does not found MD5 key of provider [" + providerID + "]");
            }
            if (!"014".equals(commandCode)) {
                return XMLUtils.systemIsError("99", "CommandCode is wrong. [" + commandCode + "] != [007]");
            }

            String data = MD5Key + commandCode + PROFILEID + Amount + providerID;
            String strMAC = ControllerUtil.EncriptMD5(data);

            if (!strMAC.equals(mac)) {
                return XMLUtils.systemIsError("98", "MAC is wrong. SCB = [" + strMAC + "], " + providerID
                        + " = [" + mac + "]");
            }

            String[] result = Helper.getDBI().ONL_checkProfileIDForPayment(PROFILEID, Double.valueOf(Amount), providerID);
            String responseCode = result[0];
            String description = result[1];

            response.put("RESPONSECODE", responseCode);
            response.put("PROFILEID", PROFILEID);
            response.put("DESCRIPTION", description);
            response.put("PROVIDERID", providerID);
            return XMLUtils.xmlParser(response, MD5Key);
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, e);
            return XMLUtils.systemIsError("99", "System is error.");
        } finally {
            Helper.writeLog(this.getClass(), Level.INFO, "***************** END GET ACCOUNT BALANCE *****************");
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String checkKYC(String strXML) {
        HashMap<String, String> response = new LinkedHashMap<String, String>();
        try {
            Helper.writeLog(this.getClass(), Level.INFO, "***************** START GET checkKYC *****************");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element element = (Element) nodeRoot;

            String providerID = element.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String commandCode = element.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            String MOBILENO = element.getElementsByTagName("MOBILENO").item(0) == null ? "" : element.getElementsByTagName("MOBILENO").item(0).getTextContent();
            String IDCARD = element.getElementsByTagName("IDCARD").item(0).getTextContent();
            String PROFILEID = element.getElementsByTagName("PROFILEID").item(0).getTextContent();
            String mac = element.getElementsByTagName("MAC").item(0).getTextContent();
            String addinfo = "";

            if (element.getElementsByTagName("ADDINFO").item(0) != null) {
                addinfo = element.getElementsByTagName("ADDINFO").item(0).getTextContent();
            }

            String MD5Key = CommonUtils.getMD5Key(providerID);

            String[] key = Helper.getDBI().ONL_GetMACkeys(providerID);
            MD5Key = key[0].toString();
            if (MD5Key.isEmpty()) {
                return XMLUtils.systemIsError("98", "Does not found MD5 key of provider [" + providerID + "]");
            }
            if (!"016".equals(commandCode)) {
                return XMLUtils.systemIsError("99", "CommandCode is wrong. [" + commandCode + "] != [016]");
            }

            String data = MD5Key + commandCode + PROFILEID + MOBILENO + IDCARD + providerID;
            String strMAC = ControllerUtil.EncriptMD5(data);

            if (!strMAC.equals(mac)) {
                return XMLUtils.systemIsError("98", "MAC is wrong. SCB = [" + strMAC + "], " + providerID
                        + " = [" + mac + "]");
            }
            String responseCode = Helper.getDBI().ONL_checkKYC(providerID, PROFILEID, IDCARD, MOBILENO, addinfo);

            response.put("RESPONSECODE", responseCode);
            response.put("PROFILEID", PROFILEID);
            response.put("PROVIDERID", providerID);

            return XMLUtils.xmlParser(response, MD5Key);
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, e);
            return XMLUtils.systemIsError("99", "System is error.");
        } finally {
            Helper.writeLog(this.getClass(), Level.INFO, "***************** END checkKYC *****************");
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String transferToAccount(String strXML) {
        try {
            System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("017")) {
                return XMLUtils.systemIsError("99", "COMMANDCODE is wrong.");
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String DESTCARDNUMBER = eElement.getElementsByTagName("DESTCARDNUMBER").item(0).getTextContent();
            String TYPEDESTCARD = eElement.getElementsByTagName("TYPEDESTCARD").item(0).getTextContent();
            String AMOUNT = eElement.getElementsByTagName("AMOUNT").item(0).getTextContent();
            String CURRENCYCODE = eElement.getElementsByTagName("CURRENCYCODE").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();

            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0) == null ? "" : eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            String SOURCEACCOUNT = "";
            if (key.length == 0) {
                return XMLUtils.systemIsError("99", "PROVIDERID is wrong.");
            } else {
                MD5Key = key[0].toString();
                SOURCEACCOUNT = key[4].toString();
            }
            String data = MD5Key + CommandCode + TRANSID + DESTCARDNUMBER + TYPEDESTCARD + AMOUNT + CURRENCYCODE + DATETIME + DESCRIPTION + PROVIDERID;
            System.out.print(data);
            String strMAC = ControllerUtil.EncriptMD5(data);
            //check MAC
            if (!strMAC.toUpperCase().endsWith(MAC.toUpperCase())) {
                return XMLUtils.systemIsError("98", "MAC is not match.");
            }

            Double dblAmount = Double.valueOf(AMOUNT);
            String[] result = Helper.getDBI().ONL_TAKEOUTWALLET(TRANSID, DESTCARDNUMBER,
                    SOURCEACCOUNT, dblAmount, CURRENCYCODE, DATETIME,
                    DESCRIPTION, PROVIDERID, TYPEDESTCARD);
            String pStatus = result[0].toString();
            String PBANKID = "0";
            if (pStatus.endsWith("00")) {
                PBANKID = result[1].toString();
                String pDestAccount = result[2].toString();
                Fcubs fc = new Fcubs();
                String Desc = "";
                Desc = PROVIDERID + " CHUYEN TIEN.MA GD:" + TRANSID;
                String CoreRef = fc.transferFCUBSWithProduct(ProductTransfer, SOURCEACCOUNT, pDestAccount, BigDecimal.valueOf(dblAmount), Desc);
                if (CoreRef == null) {
                    pStatus = "01";
                }
                if (CoreRef.equals("TIMEOUT")) {
                    return XMLUtils.systemIsError("99", "transfer is timeout.");
                }
                Helper.getDBI().ONL_UPDATE_TAKEOUTWALLET(PBANKID, CoreRef);
            }
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(pStatus));
            response.appendChild(RESPONSECODE);

            Element BANKTRANSID = responsedoc.createElement("BANKTRANSID");
            BANKTRANSID.appendChild(responsedoc.createTextNode(PBANKID));
            response.appendChild(BANKTRANSID);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eDESTCARDNUMBER = responsedoc.createElement("DESTCARDNUMBER");
            eDESTCARDNUMBER.appendChild(responsedoc.createTextNode(DESTCARDNUMBER));
            response.appendChild(eDESTCARDNUMBER);

            Element eTYPEDESTCARD = responsedoc.createElement("TYPEDESTCARD");
            eTYPEDESTCARD.appendChild(responsedoc.createTextNode(TYPEDESTCARD));
            response.appendChild(eTYPEDESTCARD);

            Element eSOURCEACCOUNT = responsedoc.createElement("SOURCEACCOUNT");
            eSOURCEACCOUNT.appendChild(responsedoc.createTextNode(SOURCEACCOUNT));
            response.appendChild(eSOURCEACCOUNT);

            Element eAMOUNT = responsedoc.createElement("AMOUNT");
            eAMOUNT.appendChild(responsedoc.createTextNode(AMOUNT));
            response.appendChild(eAMOUNT);

            Element eCURRENCYCODE = responsedoc.createElement("CURRENCYCODE");
            eCURRENCYCODE.appendChild(responsedoc.createTextNode(CURRENCYCODE));
            response.appendChild(eCURRENCYCODE);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String inputRes = MD5Key + pStatus
                    + PBANKID
                    + TRANSID
                    + DESTCARDNUMBER
                    + TYPEDESTCARD
                    + SOURCEACCOUNT
                    + AMOUNT
                    + CURRENCYCODE
                    + DATETIME + DESCRIPTION + PROVIDERID;
            System.out.println(inputRes);
            String resMAC = ControllerUtil.EncriptMD5(inputRes);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return XMLUtils.systemIsError("99", "System is error.");
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String revertTranferToAccount(String strXML) {
        try {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, "REQUEST HUY RUT TIEN VE THE/TK:" + strXML);

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("008")) {
                return XMLUtils.systemIsError("99", "CommandCode is wrong.");
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String REFTRANSID = eElement.getElementsByTagName("REFTRANSID").item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0) == null ? "" : eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return XMLUtils.systemIsError("98", "Not exist PROVIDERID");
            } else {
                MD5Key = key[0].toString();
            }

            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + TRANSID + REFTRANSID + DATETIME + DESCRIPTION + PROVIDERID);
            //check MAC

            if (!strMAC.endsWith(MAC)) {
                return XMLUtils.systemIsError("98", "MAC is not match.");
            }

            String[] result = Helper.getDBI().ONL_REVERT_TAKEOUTWALLET(REFTRANSID, PROVIDERID);
            String pStatus = result[1].toString();
            if (pStatus.endsWith("00")) {
                String PREFCORE = result[0].toString();
                Fcubs fc = new Fcubs();
                String Desc = "HUY RUT TIEN TU VI VE THE/TK QUA " + PROVIDERID + ".MAGD:" + TRANSID;
                BigDecimal amount = BigDecimal.valueOf(Double.valueOf(result[2]));
                String DestNumber = result[3];
                String SourceAccount = result[4];
                /// String refcore= fc.transferFCUBSWithProduct(ProductTransfer, DestNumber, SourceAccount,amount ,Desc);
                String resultRV = fc.revertTransferFCUBS(PREFCORE, 60);
                Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, "KQ HUY GIAO DICH RUT TIEN:" + resultRV);
                if (resultRV.equals("0")) {
                    Helper.getDBI().ONL_UPDATE_REVERT_TAKEOUTWALLET(TRANSID, REFTRANSID, PROVIDERID, DATETIME, DESCRIPTION);
                } else {
                    pStatus = "02";
                }

            }

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(pStatus));
            response.appendChild(RESPONSECODE);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eREFTRANSID = responsedoc.createElement("REFTRANSID");
            eREFTRANSID.appendChild(responsedoc.createTextNode(REFTRANSID));
            response.appendChild(eREFTRANSID);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            if (pStatus.equals("00")) {
                DESCRIPTION = "HUY GIAO DICH CHUYEN TIEN THANH CONG";
            } else if (pStatus.equals("01")) {
                DESCRIPTION = "KHONG TON TAI GIAO DICH CHUYEN TIEN";
            } else {
                DESCRIPTION = "HUY GIAO DICH CHUYEN TIEN THAT BAI";
            }

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String resMAC = ControllerUtil.EncriptMD5(MD5Key + pStatus
                    + TRANSID
                    + REFTRANSID
                    + DATETIME + DESCRIPTION + PROVIDERID);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return XMLUtils.systemIsError("99", "System is error.");
        }
    }

    /**
     *
     * @param content
     * @return
     */
    public String MmsTransferAccToAcc(String content) {
        HashMap<String, String> response = new HashMap<>();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(content)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String SOURCEACCOUNT = eElement.getElementsByTagName("SOURCEACCOUNT").item(0).getTextContent();
            String MERCHANTACCOUNT = eElement.getElementsByTagName("MERCHANTACCOUNT").item(0).getTextContent();
            String AMOUNT = eElement.getElementsByTagName("AMOUNT").item(0).getTextContent();
            String CURRENCYCODE = eElement.getElementsByTagName("CURRENCYCODE").item(0).getTextContent();

            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName("DESCRIPTION").item(0).getTextContent();

            String MERCHANTCODE = eElement.getElementsByTagName("MERCHANTCODE").item(0).getTextContent();
            String TERMINALID = eElement.getElementsByTagName("TERMINALID").item(0).getTextContent();

            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                response.put("RESPONSECODE", CommonMessage.CommontEnum.INVALID_CHECKSUM.getValue());
                response.put("DESCRIPTION", "Does not found MAC key of provider [" + PROVIDERID + "]");
                return XMLUtils.xmlParser(response);
            } else {
                MD5Key = key[0];
            }
            String inpusReqMac = MD5Key + TRANSID + SOURCEACCOUNT + MERCHANTACCOUNT + AMOUNT + CURRENCYCODE
                    + DATETIME + DESCRIPTION + MERCHANTCODE + TERMINALID + PROVIDERID;
            String strMAC = ControllerUtil.EncriptMD5(inpusReqMac);
            if (!strMAC.equals(MAC)) {
                response.put("RESPONSECODE", CommonMessage.CommontEnum.INVALID_CHECKSUM.getValue());
                response.put("DESCRIPTION", "MAC is wrong. SCB = [" + strMAC + "], " + PROVIDERID + " = [" + MAC + "]");
                Helper.writeLog(this.getClass(), Level.INFO, "MD5 = [" + strMAC + "], MAC = [" + MAC + "]");
                return XMLUtils.xmlParser(response);
            }

            //boolean merchantValidate = Helper.getDBI().MMSCHECKACCOUNT(MERCHANTCODE, TERMINALID, MERCHANTACCOUNT);
            boolean merchantValidate = true;
            if (!merchantValidate) {
                response.put("RESPONSECODE", CommonMessage.CommontEnum.INVALID_MERCHANTID.getValue());
                response.put("DESCRIPTION", MERCHANTCODE + " IS INVALID");
                Helper.writeLog(this.getClass(), Level.INFO, "MD5 = [" + strMAC + "], MAC = [" + MAC + "]");
                return XMLUtils.xmlParser(response);
            }

            Double dblAmount = Double.valueOf(AMOUNT);
            String[] result = Helper.getDBI().MMSTRANSFERACCTOACC(TRANSID, SOURCEACCOUNT, MERCHANTACCOUNT,
                    dblAmount, CURRENCYCODE, DATETIME, PROVIDERID, DESCRIPTION);

            String PRESPONSECODE = result[0];
            String PDESCRIPTION = result[1];
            String PBANKID = result[2];
            if ("00".equals(PRESPONSECODE)) {
                Fcubs fc = new Fcubs();
                String descCore = DESCRIPTION + "CHO MA GD:" + TRANSID;
                String coreRef = fc.transferFCUBSWithProductWithTimeOut(mmsQRProductCode, SOURCEACCOUNT,
                        MERCHANTACCOUNT, BigDecimal.valueOf(dblAmount), descCore, vnpayqrTimeout);
                if (coreRef == null || coreRef.isEmpty()) {
                    PRESPONSECODE = CommonMessage.CommontEnum.INTERNALERROR.getValue();
                    PDESCRIPTION = "CO LOI XAY RA TRONG QUA TRINH THANH TOAN";
                } else if ("TIMEOUT".equals(coreRef)) {
                    PRESPONSECODE = CommonMessage.CommontEnum.TIMEOUT.getValue();
                    PDESCRIPTION = "TRU TIEN BI TIMEOUT";
                } else {
                    PRESPONSECODE = "00";
                    PDESCRIPTION = "THANH TOAN THANH CONG";
                }
                Helper.getDBI().UPDATEMMSTRANSFERACCTOACC(PBANKID, coreRef);
            }
            response.put("RESPONSECODE", PRESPONSECODE);
            response.put("RESPONSEDESCRIPTION", PDESCRIPTION);
            response.put("BANKTRANSID", PBANKID);
            response.put("TRANSID", TRANSID);
            response.put("SOURCEACCOUNT", SOURCEACCOUNT);
            response.put("MERCHANTACCOUNT", MERCHANTACCOUNT);
            response.put("AMOUNT", AMOUNT);
            response.put("CURRENCYCODE", CURRENCYCODE);
            response.put("DATETIME", DATETIME);
            response.put("DESCRIPTION", DESCRIPTION);
            response.put("MERCHANTCODE", MERCHANTCODE);
            response.put("TERMINALID", TERMINALID);
            response.put("PROVIDERID", PROVIDERID);
            return XMLUtils.xmlParser(response, MD5Key);
        } catch (Exception ex) {
            response.put("RESPONSECODE", CommonMessage.CommontEnum.INTERNALERROR.getValue());
            response.put("DESCRIPTION", "System is error.");
            Helper.writeLog(this.getClass(), Level.ERROR, "MmsTransferAccToAcc failed because ["
                    + ex.getMessage() + "]");
            return XMLUtils.xmlParser(response);
        }
    }

    /**
     *
     * @param req
     * @return
     */
    public String getSCBBranch(GetSCBBranchRq req) {
        GetSCBBranchRp response = new GetSCBBranchRp();
        response.setProviderId(req.getProviderId());
        try {
            SCBBranch scbBranch = Helper.getDBI().getSCBBranch();
            if (scbBranch.isValidData()) {
                String data = XMLUtils.Marshaller(scbBranch);
                String zipData = ZipData.ZipToString(data);
                response.setResponseCode(ResponseCodeEnum.SUCCESS.getText());
                response.setResponseDesc(ResponseCodeEnum.SUCCESS.name());
                response.setListData(zipData);
            } else {
                response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.getText());
                response.setResponseDesc("SCBBranch data is invalid.");
            }
            String md5Key = SiUtils.getMd5Key(req.getProviderId());
            response.setSignatureScb(md5Key);
        } catch (Exception ex) {
            logger.error(ex);
            response.setResponseCode(ResponseCodeEnum.SYSTEMERROR.getText());
            response.setResponseDesc(ResponseCodeEnum.SYSTEMERROR.name());
        }

        return XMLUtils.Marshaller(response);
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String getCARDID(String strXML) {
        try {
            Helper.writeLogErrorNonDB(OnlinePaymentController.class, "REQUEST:" + IBTController.cheThe(strXML, "..."));
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            if (!CommandCode.endsWith("018")) {
                return XMLUtils.systemIsError("99", "CommandCode is wrong.");
            }
            String CARDNUMBER = eElement.getElementsByTagName("CARDNUMBER").item(0).getTextContent();
            String CARDNAME = eElement.getElementsByTagName("CARDNAME").item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key = "";
            if (!PROVIDERID.equals("MOCA")) {

                return XMLUtils.systemIsError("98", "Not exist PROVIDERID");
            } else {
                MD5Key = Configuration.getProperty("moca.md5key");
            }
            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + CARDNUMBER + CARDNAME + PROVIDERID);
            //check MAC

            if (!strMAC.toUpperCase().endsWith(MAC.toUpperCase())) {
                return XMLUtils.systemIsError("98", "MAC is not match:" + MD5Key);
            }

            String status = "01";// THE KHONG HOP LE
            String CARDID = "";

            String BIN = CARDNUMBER.substring(0, 6);
            String strListBINCard = Configuration.getProperty("onlinepayment.listtypecard");

            String listBINCard[] = strListBINCard.split("#");
            Boolean checkBINCard = false;
            for (int i = 0; i < listBINCard.length; i++) {
                if (listBINCard[i].equals(BIN)) {
                    checkBINCard = true;
                }
            }

            String TYPECARD = "";
            String rCARDNAME = "";
            String DESCRIPTION = "THE KHONG HOP LE";
            if (checkBINCard) {
                TYPECARD = "04";
                // GET LOCID
                Helper.writeLogErrorNonDB(OnlinePaymentController.class, "Helper.getDBI().checkCARD");
                String[] MDINFO = Helper.getDBI().checkCARD(CARDNUMBER);
                Helper.writeLogErrorNonDB(OnlinePaymentController.class, "MDINFO[0]:" + MDINFO[0]);
                if (MDINFO != null) {
                    status = MDINFO[0];
                    if (status.equals("00")) {
                        Helper.writeLogErrorNonDB(OnlinePaymentController.class, "MDINFO[1]:" + MDINFO[1]);
                        rCARDNAME = MDINFO[1] == null ? "" : MDINFO[1];
                        if (rCARDNAME.equals(CARDNAME)) {
                            DESCRIPTION = "THE HOP LE";
                            if (MDINFO.length == 4) {
                                CARDID = MDINFO[3] + CARDNUMBER.substring(12, 16);
                            } else {
                                CARDID = CARDNUMBER;
                            }
                        } else {
                            status = "04";
                            DESCRIPTION = "SAI TEN CHU THE";
                        }
                    } else {
                        if (status.equals("01")) {
                            status = "02";
                            DESCRIPTION = "THE KHOA.";
                        } else if (status.equals("02")) {
                            status = "09";
                            DESCRIPTION = "THE HET HAN.";
                        } else if (status.equals("03")) {
                            DESCRIPTION = "THE CHUA KICH HOAT.";
                        } else if (status.equals("04")) {
                            DESCRIPTION = "SAI THONG TIN THE.";
                        } else if (status.equals("20")) {
                            DESCRIPTION = "THE KHOA CHUC NANG THANH TOAN TRUC TUYEN.";
                        } else {
                            status = "01";
                        }
                    }

                }
            }
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(status));
            response.appendChild(RESPONSECODE);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element eCARDID = responsedoc.createElement("CARDID");
            eCARDID.appendChild(responsedoc.createTextNode(CARDID));
            response.appendChild(eCARDID);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            Element eCARDTYPE = responsedoc.createElement("CARDTYPE");
            eCARDTYPE.appendChild(responsedoc.createTextNode(TYPECARD));
            response.appendChild(eCARDTYPE);

            String resMAC = ControllerUtil.EncriptMD5(MD5Key + status + DESCRIPTION
                    + CARDID + PROVIDERID + TYPECARD);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            Helper.writeLogErrorNonDB(OnlinePaymentController.class, "RESPONSE:" + stringOut.toString());
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return XMLUtils.systemIsError("99", "System is error.");
        }
    }

    public String checkCustInfo(String inputXml) {

        try {
            Helper.writeLog(this.getClass(), Level.INFO, "***************** START checkCustInfo *****************");
            OnlinePCustomerInfoReq req = (OnlinePCustomerInfoReq) Xml.unMarshaller(OnlinePCustomerInfoReq.class, inputXml);
            //Validate input
            if (!ResponseStatusCode.VALIDATION_SUCCEED.equals(OnlinePaymentValidate.checkCustInfoValidate(req))) {
                return OnlinePaymentValidate.checkCustInfoValidate(req);
            }

            //set properties
            OnlinePCustomerInfoDto response = new OnlinePCustomerInfoDto();
            response.setProviderID(req.getPROVIDERID());
            response.setProfileid(req.getPROFILEID());
            response.setCardName(req.getCARDNAME());
            response.setBirthDay(req.getBIRTHDAY());
            response.setMobileNo(req.getMOBILENO());
            response.setIdNumber(req.getIDNUMBER());
            response.setCountry(req.getCOUNTRY());
            response.setCommondCode(req.getCOMMANDCODE());

            CustomerInfoRsDto obj = Helper.getDBI().ONL_checkCustInfo(response);

            String xml = Xml.Marshaller(obj);

            return xml;
        } catch (Exception ex) {
            logger.error(ex);
            throw new PaymentException(ex);
        } finally {
            Helper.writeLog(this.getClass(), Level.INFO, "***************** END checkCustInfo *****************");
        }
    }

    /**
     * Online Payment Validate
     */
    protected static class OnlinePaymentValidate {

        /**
         * Check cusinfo validate
         *
         * @param req
         * @return
         * @throws RemoteException
         */
        private static String checkCustInfoValidate(OnlinePCustomerInfoReq req) throws RemoteException {

            //PROVIDERID
            String[] key = Helper.getDBI().ONL_GetMACkeys(req.getPROVIDERID());
            if (key == null) {
                return XMLUtils.systemIsError("98", "Does not found MD5 key of provider [" + req.getPROFILEID() + "]");
            }
            //Commandcode
            if (!"017".equals(req.getCOMMANDCODE())) {
                return XMLUtils.systemIsError("99", "CommandCode is wrong. [" + req.getCOMMANDCODE() + "] != [016]");
            }

            /*
            //Mac
            String MD5Key = CommonUtils.getMD5Key(req.getPROVIDERID());
            MD5Key = key[0].toString();
            String data = MD5Key + req.getCOMMANDCODE() + req.getPROFILEID() + req.getMOBILENO() + req.getIDNUMBER() + req.getPROVIDERID();
            String strMAC = ControllerUtil.EncriptMD5(data);

            if (!strMAC.equals(req.getMAC())) {
                return XMLUtils.systemIsError("98", "MAC is wrong. SCB = [" + strMAC + "], " + req.getPROVIDERID()
                        + " = [" + req.getMAC() + "]");
            }
             */
            return ResponseStatusCode.VALIDATION_SUCCEED;
        }
    }
    // private static final String TOKEN_LENGTH = "6";
    // private static final String TOKEN_FORMAT = "0123456789";

    public static String createOtpNum() {
        int tokenLenght = 6;
        String tokenFormat = "0123456789";
        Random rnd = new SecureRandom();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < tokenLenght; i++) {
            token.append(tokenFormat.charAt(rnd.nextInt(tokenFormat.length())));
        }
        return token.toString();
    }

    public String revertPayment(String strXML) {
        try {
            System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName("REQUEST").item(0);
            Element eElement = (Element) nodeRoot;
            String CommandCode = eElement.getElementsByTagName("COMMANDCODE").item(0).getTextContent();
            //CommandCode='001'
            if (!CommandCode.endsWith("019")) {
                return getMsgError("97", "SAI THAM SO");
            }
            String TRANSID = eElement.getElementsByTagName("TRANSID").item(0).getTextContent();
            String REFTRANSID = eElement.getElementsByTagName("REFTRANSID").item(0).getTextContent();

            String DATETIME = eElement.getElementsByTagName("DATETIME").item(0).getTextContent();

            String PROVIDERID = eElement.getElementsByTagName("PROVIDERID").item(0).getTextContent();
            String MAC = eElement.getElementsByTagName("MAC").item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return getMsgError("98", "SAI PROVIDERID");
            } else {
                MD5Key = key[0].toString();
            }

            String strMAC = ControllerUtil.EncriptMD5(MD5Key + CommandCode + TRANSID + REFTRANSID + DATETIME + PROVIDERID);
            System.out.print(strMAC);
            //check MAC

            if (!strMAC.toUpperCase().endsWith(MAC.toUpperCase())) {
                return getMsgError("98", "SAI MAC");
            }
            Object[] resultCheckDestroy = Helper.getDBI().DESTROY_QR_PAYMENT(TRANSID, REFTRANSID, PROVIDERID, DATETIME);
            String pStatus = resultCheckDestroy[0].toString();
            String DESCRIPTION = resultCheckDestroy[2].toString();
            String PBANKID = "0";
            if (pStatus.endsWith("00")) {
                Double refundAmount = Double.valueOf(resultCheckDestroy[1].toString());
                String[] resultCheckRefund = Helper.getDBI().ONL_REFUND(TRANSID, REFTRANSID, refundAmount, DATETIME, "HUY GIAO DICH", PROVIDERID);
                pStatus = resultCheckRefund[0].toString();

                if (pStatus.endsWith("00")) {
                    String pCustAccount = resultCheckRefund[1].toString();
                    String pPartnerAccount = resultCheckRefund[2].toString();
                    PBANKID = resultCheckRefund[3].toString();
                    BigDecimal refundAmountBD = BigDecimal.valueOf(refundAmount);

                    Fcubs fc = new Fcubs();
                    String Desc = "HOAN TIEN TT TRUC TUYEN QUA " + PROVIDERID + " CHO MAGD: " + REFTRANSID + " .MAGD HOAN TIEN :" + TRANSID;
                    Desc = Desc.replace("AIRPAY", "ShopeePay");
                    String CoreRef = fc.transferFCUBSWithProduct(ProductTransfer, pPartnerAccount, pCustAccount, refundAmountBD, Desc);
                    if (CoreRef == null) {
                        pStatus = "04";
                        DESCRIPTION = "GIAO DICH HOAN TIEN THAT BAI";
                    } else {
                        if (CoreRef.equals("TIMEOUT")) {
                            pStatus = "99";
                            DESCRIPTION = "TIMEOUT CHO DOI SOAT";
                        } else {
                            DESCRIPTION = "HOAN TIEN THANH CONG>";
                        }
                        Helper.getDBI().ONL_UPDATE_REFUND(PBANKID, CoreRef);
                        Helper.getDBI().UPDATE_DESTROY_QR_PAYMENT(CoreRef, REFTRANSID, PROVIDERID);
                    }
                } else {
                    DESCRIPTION = "GIAO DICH HOAN TIEN THAT BAI";
                }
            }
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement("RESPONSE");
            responsedoc.appendChild(response);

            Element RESPONSECODE = responsedoc.createElement("RESPONSECODE");
            RESPONSECODE.appendChild(responsedoc.createTextNode(pStatus));
            response.appendChild(RESPONSECODE);

            Element BANKTRANSID = responsedoc.createElement("BANKTRANSID");
            BANKTRANSID.appendChild(responsedoc.createTextNode(PBANKID));
            response.appendChild(BANKTRANSID);

            Element eTRANSID = responsedoc.createElement("TRANSID");
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eREFTRANSID = responsedoc.createElement("REFTRANSID");
            eREFTRANSID.appendChild(responsedoc.createTextNode(REFTRANSID));
            response.appendChild(eREFTRANSID);

            Element eDATETIME = responsedoc.createElement("DATETIME");
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement("DESCRIPTION");
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement("PROVIDERID");
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String resMAC = ControllerUtil.EncriptMD5(MD5Key + pStatus
                    + PBANKID
                    + TRANSID
                    + REFTRANSID
                    + DATETIME + DESCRIPTION + PROVIDERID);

            Element eMAC = responsedoc.createElement("MAC");
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut,
                    format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (Exception ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, "revertPayment:" + ex);
            return "99";
        }
    }

    private String getMsgError(String errorcode, String desc) {
        String reponse = "<RESPONSE><RESPONSECODE>" + errorcode + "</RESPONSECODE>"
                + "<DESCRIPTION>" + desc + "</DESCRIPTION>"
                + "</RESPONSE>";
        Helper.writeLogError(scb.com.vn.payment.SIController.class, "reponse:" + reponse);
        return reponse;
    }
}
