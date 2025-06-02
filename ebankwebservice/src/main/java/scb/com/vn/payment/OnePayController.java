/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.payment;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import scb.com.vn.constant.OnePayConstant;
import scb.com.vn.utility.Helper;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import scb.com.vn.controller.Fcubs;
import scb.com.vn.utility.SMSUtils;

/**
 *
 * @author Administrator
 */
public class OnePayController {

    /**
     *
     * @param strXML
     * @return
     */
    public String CardVerification(String strXML) {
        try {
            System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName(OnePayConstant.REQUEST).item(0);
            Element eElement = (Element) nodeRoot;
            String COMMANDCODE = eElement.getElementsByTagName(OnePayConstant.COMMANDCODE).item(0).getTextContent();
            if (!COMMANDCODE.endsWith(OnePayConstant.CARD_VER_COMMANDCODE)) {
                return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
            }
            String TRANSID = eElement.getElementsByTagName(OnePayConstant.TRANSID).item(0).getTextContent();
            String CARDNUMBER = eElement.getElementsByTagName(OnePayConstant.CARDNUMBER).item(0).getTextContent();
            String CARDHOLDERNAME = eElement.getElementsByTagName(OnePayConstant.CARDHOLDERNAME).item(0).getTextContent();
            String CARDDATE = eElement.getElementsByTagName(OnePayConstant.CARDDATE).item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName(OnePayConstant.DATETIME).item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName(OnePayConstant.DESCRIPTION).item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName(OnePayConstant.PROVIDERID).item(0).getTextContent();
            String MAC = eElement.getElementsByTagName(OnePayConstant.MAC).item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return OnePayConstant.SECURITY_IS_ERROR;
            } else {
                MD5Key = key[0];
            }
            String strMAC = ControllerUtil.EncriptMD5(MD5Key + COMMANDCODE + TRANSID + CARDNUMBER
                    + CARDHOLDERNAME + CARDDATE + DATETIME + DESCRIPTION + PROVIDERID);
            if (!strMAC.endsWith(MAC)) {
                return OnePayConstant.SECURITY_IS_ERROR;
            }

            String[] result = Helper.getDBI().ONEPAY_CARD_VERIFICATION(TRANSID, PROVIDERID, CARDNUMBER,
                     CARDHOLDERNAME, CARDDATE, DATETIME, DESCRIPTION);

            if (result == null || result.length != 3) {
                return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
            }

            String RESPONSECODE = result[0];
            String BANKTRANSID = "0";
            String OTP = SMSUtils.CreateOTP();
            if (OnePayConstant.WAITING_FOR_OTP.equals(RESPONSECODE)) {
                BANKTRANSID = result[1];
                String PHONENUMBER = result[2];
                Helper.getDBI().PAYMENT_OTP_ADDING(TRANSID, PROVIDERID, OTP, PHONENUMBER,
                         OnePayConstant.ONEPAY_CHANNELID, OnePayConstant.CARD_VER_TRANSTYPE);
                int resultSMSSending = SMSUtils.SendOTPSMS(PHONENUMBER, OTP,
                         "Mã xác thực thông tin thẻ ");
                if (resultSMSSending == 0) {
                    return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
                }
            }
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement(OnePayConstant.RESPONSE);
            responsedoc.appendChild(response);

            Element eRESPONSECODE = responsedoc.createElement(OnePayConstant.RESPONSECODE);
            eRESPONSECODE.appendChild(responsedoc.createTextNode(RESPONSECODE));
            response.appendChild(eRESPONSECODE);

            Element eBANKTRANSID = responsedoc.createElement(OnePayConstant.BANKTRANSID);
            eBANKTRANSID.appendChild(responsedoc.createTextNode(BANKTRANSID));
            response.appendChild(eBANKTRANSID);

            Element eTRANSID = responsedoc.createElement(OnePayConstant.TRANSID);
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eCARDNUMBER = responsedoc.createElement(OnePayConstant.CARDNUMBER);
            eCARDNUMBER.appendChild(responsedoc.createTextNode(CARDNUMBER));
            response.appendChild(eCARDNUMBER);

            Element eCARDHOLDERNAME = responsedoc.createElement(OnePayConstant.CARDHOLDERNAME);
            eCARDHOLDERNAME.appendChild(responsedoc.createTextNode(CARDHOLDERNAME));
            response.appendChild(eCARDHOLDERNAME);

            Element eCARDDATE = responsedoc.createElement(OnePayConstant.CARDDATE);
            eCARDDATE.appendChild(responsedoc.createTextNode(CARDDATE));
            response.appendChild(eCARDDATE);

            Element eDATETIME = responsedoc.createElement(OnePayConstant.DATETIME);
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement(OnePayConstant.DESCRIPTION);
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement(OnePayConstant.PROVIDERID);
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String resMAC = ControllerUtil.EncriptMD5(MD5Key + RESPONSECODE + BANKTRANSID + TRANSID
                    + CARDNUMBER + CARDHOLDERNAME + CARDDATE + DATETIME + DESCRIPTION + PROVIDERID);

            Element eMAC = responsedoc.createElement(OnePayConstant.MAC);
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut, format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (ParserConfigurationException | SAXException | IOException | DOMException ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String Payment(String strXML) {
        try {
            System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName(OnePayConstant.REQUEST).item(0);
            Element eElement = (Element) nodeRoot;
            String COMMANDCODE = eElement.getElementsByTagName(OnePayConstant.COMMANDCODE).item(0).getTextContent();
            if (!COMMANDCODE.endsWith(OnePayConstant.CARD_VER_COMMANDCODE)) {
                return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
            }
            String TRANSID = eElement.getElementsByTagName(OnePayConstant.TRANSID).item(0).getTextContent();
            String CARDNUMBER = eElement.getElementsByTagName(OnePayConstant.CARDNUMBER).item(0).getTextContent();
            String CARDHOLDERNAME = eElement.getElementsByTagName(OnePayConstant.CARDHOLDERNAME).item(0).getTextContent();
            String CARDDATE = eElement.getElementsByTagName(OnePayConstant.CARDDATE).item(0).getTextContent();
            String AMOUNT = eElement.getElementsByTagName(OnePayConstant.AMOUNT).item(0).getTextContent();
            String CURRENCYCODE = eElement.getElementsByTagName(OnePayConstant.CURRENCYCODE).item(0).getTextContent();
            String MERCHANTID = eElement.getElementsByTagName(OnePayConstant.MERCHANTID).item(0).getTextContent();
            String WITHOUTOTP = eElement.getElementsByTagName(OnePayConstant.WITHOUTOTP).item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName(OnePayConstant.DATETIME).item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName(OnePayConstant.DESCRIPTION).item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName(OnePayConstant.PROVIDERID).item(0).getTextContent();
            String MAC = eElement.getElementsByTagName(OnePayConstant.MAC).item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return OnePayConstant.SECURITY_IS_ERROR;
            } else {
                MD5Key = key[0];
            }
            String strMAC = ControllerUtil.EncriptMD5(MD5Key + COMMANDCODE + TRANSID + CARDNUMBER
                    + CARDHOLDERNAME + CARDDATE + AMOUNT + CURRENCYCODE + MERCHANTID + WITHOUTOTP
                    + DATETIME + DESCRIPTION + PROVIDERID);
            if (!strMAC.endsWith(MAC)) {
                return OnePayConstant.SECURITY_IS_ERROR;
            }

            String RESPONSECODE;
            String BANKTRANSID = "0";
            Double MONEY = Double.valueOf(AMOUNT);
            String[] result;

            switch (WITHOUTOTP) {
                case OnePayConstant.PAYMENT_WITH_OTP:
                    result = Helper.getDBI().ONEPAY_PAYMENT(TRANSID, TRANSID,
                             PROVIDERID, MERCHANTID, CARDNUMBER, CARDHOLDERNAME, CARDDATE, MONEY,
                             CURRENCYCODE, DATETIME, OnePayConstant.ONEPAY_CHANNELID, DESCRIPTION);

                    if (result == null || result.length != 3) {
                        return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
                    }
                    RESPONSECODE = result[0];
                    String OTP = SMSUtils.CreateOTP();
                    if (OnePayConstant.TRANSACTION_IS_SUCCEED.equals(RESPONSECODE)) {
                        BANKTRANSID = result[1];
                        String PHONENUMBER = result[2];
                        Helper.getDBI().PAYMENT_OTP_ADDING(TRANSID, PROVIDERID, OTP, PHONENUMBER,
                                 OnePayConstant.ONEPAY_CHANNELID, OnePayConstant.PAYMENT_VER_TRANSTYPE);
                        int resultSMSSending = SMSUtils.SendOTPSMS(PHONENUMBER, OTP,
                                 "Mã xác thực thông tin thẻ ");
                        if (resultSMSSending == 0) {
                            return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
                        }
                    }
                    break;
                case OnePayConstant.PAYMENT_WITHOUT_OTP:
                    result = Helper.getDBI().ONEPAY_PAYMENT_WITHOUT_OTP(TRANSID, TRANSID,
                             PROVIDERID, MERCHANTID, CARDNUMBER, CARDHOLDERNAME, CARDDATE, MONEY,
                             CURRENCYCODE, DATETIME, OnePayConstant.ONEPAY_CHANNELID, DESCRIPTION);

                    if (result == null || result.length != 4) {
                        return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
                    }
                    RESPONSECODE = result[0];
                    if (OnePayConstant.TRANSACTION_IS_SUCCEED.equals(RESPONSECODE)) {
                        BANKTRANSID = result[1];
                        String CUSTACCOUNT = result[2];
                        String PARTNERACCOUNT = result[3];
                        Fcubs fc = new Fcubs();
                        String Desc = "THANH TOAN TRUC TUYEN VOI THE DA LIEN KET QUA " + PROVIDERID
                                + ".MAGD:" + TRANSID;
                        String CoreRef = fc.transferFCUBSWithProduct(OnePayConstant.PRODUCTTRANSFER,
                                 CUSTACCOUNT, PARTNERACCOUNT, BigDecimal.valueOf(MONEY), Desc);
                        if (CoreRef == null) {
                            RESPONSECODE = OnePayConstant.NOT_ENOUGH_CONDITION_TO_PAY;
                        }

                        Helper.getDBI().ONL_UpdatePayment(BANKTRANSID, RESPONSECODE, CoreRef);
                    }
                    break;
                default:
                    return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
            }
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement(OnePayConstant.RESPONSE);
            responsedoc.appendChild(response);

            Element eRESPONSECODE = responsedoc.createElement(OnePayConstant.RESPONSECODE);
            eRESPONSECODE.appendChild(responsedoc.createTextNode(RESPONSECODE));
            response.appendChild(eRESPONSECODE);

            Element eBANKTRANSID = responsedoc.createElement(OnePayConstant.BANKTRANSID);
            eBANKTRANSID.appendChild(responsedoc.createTextNode(BANKTRANSID));
            response.appendChild(eBANKTRANSID);

            Element eTRANSID = responsedoc.createElement(OnePayConstant.TRANSID);
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eCARDNUMBER = responsedoc.createElement(OnePayConstant.CARDNUMBER);
            eCARDNUMBER.appendChild(responsedoc.createTextNode(CARDNUMBER));
            response.appendChild(eCARDNUMBER);

            Element eCARDHOLDERNAME = responsedoc.createElement(OnePayConstant.CARDHOLDERNAME);
            eCARDHOLDERNAME.appendChild(responsedoc.createTextNode(CARDHOLDERNAME));
            response.appendChild(eCARDHOLDERNAME);

            Element eCARDDATE = responsedoc.createElement(OnePayConstant.CARDDATE);
            eCARDDATE.appendChild(responsedoc.createTextNode(CARDDATE));
            response.appendChild(eCARDDATE);

            Element eAMOUNT = responsedoc.createElement(OnePayConstant.AMOUNT);
            eAMOUNT.appendChild(responsedoc.createTextNode(AMOUNT));
            response.appendChild(eAMOUNT);

            Element eCURRENCYCODE = responsedoc.createElement(OnePayConstant.CURRENCYCODE);
            eCURRENCYCODE.appendChild(responsedoc.createTextNode(CURRENCYCODE));
            response.appendChild(eCURRENCYCODE);

            Element eMERCHANTID = responsedoc.createElement(OnePayConstant.MERCHANTID);
            eMERCHANTID.appendChild(responsedoc.createTextNode(MERCHANTID));
            response.appendChild(eMERCHANTID);

            Element eWITHOUTOTP = responsedoc.createElement(OnePayConstant.WITHOUTOTP);
            eWITHOUTOTP.appendChild(responsedoc.createTextNode(WITHOUTOTP));
            response.appendChild(eWITHOUTOTP);

            Element eDATETIME = responsedoc.createElement(OnePayConstant.DATETIME);
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement(OnePayConstant.DESCRIPTION);
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement(OnePayConstant.PROVIDERID);
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String resMAC = ControllerUtil.EncriptMD5(MD5Key + RESPONSECODE + BANKTRANSID + TRANSID
                    + CARDNUMBER + CARDHOLDERNAME + CARDDATE + AMOUNT + CURRENCYCODE + MERCHANTID + WITHOUTOTP
                    + DATETIME + DESCRIPTION + PROVIDERID);

            Element eMAC = responsedoc.createElement(OnePayConstant.MAC);
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut, format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (NumberFormatException | ParserConfigurationException | SAXException | IOException | DOMException ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
        }
    }

    /**
     *
     * @param strXML
     * @return
     */
    public String OTPVerification(String strXML) {
        try {
            System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(strXML)));
            Node nodeRoot = doc.getElementsByTagName(OnePayConstant.REQUEST).item(0);
            Element eElement = (Element) nodeRoot;
            String COMMANDCODE = eElement.getElementsByTagName(OnePayConstant.COMMANDCODE).item(0).getTextContent();
            if (!COMMANDCODE.endsWith(OnePayConstant.OTP_VER_COMMANDCODE)) {
                return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
            }
            String TRANSID = eElement.getElementsByTagName(OnePayConstant.TRANSID).item(0).getTextContent();
            String VERIFYTYPE = eElement.getElementsByTagName(OnePayConstant.VERIFYTYPE).item(0).getTextContent();
            String REFTRANSID = eElement.getElementsByTagName(OnePayConstant.REFTRANSID).item(0).getTextContent();
            String OTP = eElement.getElementsByTagName(OnePayConstant.OTP).item(0).getTextContent();
            String DATETIME = eElement.getElementsByTagName(OnePayConstant.DATETIME).item(0).getTextContent();
            String DESCRIPTION = eElement.getElementsByTagName(OnePayConstant.DESCRIPTION).item(0).getTextContent();
            String PROVIDERID = eElement.getElementsByTagName(OnePayConstant.PROVIDERID).item(0).getTextContent();
            String MAC = eElement.getElementsByTagName(OnePayConstant.MAC).item(0).getTextContent();

            String MD5Key;
            String[] key = Helper.getDBI().ONL_GetMACkeys(PROVIDERID);
            if (key.length == 0) {
                return OnePayConstant.SECURITY_IS_ERROR;
            } else {
                MD5Key = key[0];
            }
            String strMAC = ControllerUtil.EncriptMD5(MD5Key + COMMANDCODE + TRANSID + VERIFYTYPE
                    + REFTRANSID + OTP + DATETIME + DESCRIPTION + PROVIDERID);
            if (!strMAC.endsWith(MAC)) {
                return OnePayConstant.SECURITY_IS_ERROR;
            }

            String[] result = Helper.getDBI().ONEPAY_OTP_VERIFICATION(TRANSID, REFTRANSID, PROVIDERID,
                     OnePayConstant.ONEPAY_CHANNELID, VERIFYTYPE, OTP, DATETIME, DESCRIPTION);

            if (result == null || result.length != 5) {
                return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
            }

            String RESPONSECODE = result[0];
            String BANKTRANSID = "0";
            if (OnePayConstant.TRANSACTION_IS_SUCCEED.equals(RESPONSECODE)) {
                BANKTRANSID = result[1];
                System.out.println("OTPVERFICATION - BANKTRANSID = [" + BANKTRANSID + "]");

                switch (VERIFYTYPE) {
                    case OnePayConstant.CARD_VER_TRANSTYPE:
                        break;
                    case OnePayConstant.PAYMENT_VER_TRANSTYPE:
                        Double AMOUNT = Double.valueOf(result[2]);
                        String CUSTACCOUNT = result[3];
                        String PARTNERACCOUNT = result[4];
                        Fcubs fc = new Fcubs();
                        String Desc = "THANH TOAN TRUC TUYEN VOI THE DA LIEN KET QUA " + PROVIDERID
                                + ".MAGD:" + TRANSID;
                        String CoreRef = fc.transferFCUBSWithProduct(OnePayConstant.PRODUCTTRANSFER,
                                 CUSTACCOUNT, PARTNERACCOUNT, BigDecimal.valueOf(AMOUNT), Desc);
                        if (CoreRef == null) {
                            RESPONSECODE = OnePayConstant.NOT_ENOUGH_CONDITION_TO_PAY;
                        }

                        Helper.getDBI().ONL_UpdatePayment(BANKTRANSID, RESPONSECODE, CoreRef);
                        break;
                    default:
                        break;
                }
            }

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document responsedoc = build.newDocument();

            Element response = responsedoc.createElement(OnePayConstant.RESPONSE);
            responsedoc.appendChild(response);

            Element eRESPONSECODE = responsedoc.createElement(OnePayConstant.RESPONSECODE);
            eRESPONSECODE.appendChild(responsedoc.createTextNode(RESPONSECODE));
            response.appendChild(eRESPONSECODE);

            Element eBANKTRANSID = responsedoc.createElement(OnePayConstant.BANKTRANSID);
            eBANKTRANSID.appendChild(responsedoc.createTextNode(BANKTRANSID));
            response.appendChild(eBANKTRANSID);

            Element eTRANSID = responsedoc.createElement(OnePayConstant.TRANSID);
            eTRANSID.appendChild(responsedoc.createTextNode(TRANSID));
            response.appendChild(eTRANSID);

            Element eVERIFYTYPE = responsedoc.createElement(OnePayConstant.VERIFYTYPE);
            eVERIFYTYPE.appendChild(responsedoc.createTextNode(VERIFYTYPE));
            response.appendChild(eVERIFYTYPE);

            Element eREFTRANSID = responsedoc.createElement(OnePayConstant.REFTRANSID);
            eREFTRANSID.appendChild(responsedoc.createTextNode(REFTRANSID));
            response.appendChild(eREFTRANSID);

            Element eOTP = responsedoc.createElement(OnePayConstant.OTP);
            eOTP.appendChild(responsedoc.createTextNode(OTP));
            response.appendChild(eOTP);

            Element eDATETIME = responsedoc.createElement(OnePayConstant.DATETIME);
            eDATETIME.appendChild(responsedoc.createTextNode(DATETIME));
            response.appendChild(eDATETIME);

            Element eDESCRIPTION = responsedoc.createElement(OnePayConstant.DESCRIPTION);
            eDESCRIPTION.appendChild(responsedoc.createTextNode(DESCRIPTION));
            response.appendChild(eDESCRIPTION);

            Element ePROVIDERID = responsedoc.createElement(OnePayConstant.PROVIDERID);
            ePROVIDERID.appendChild(responsedoc.createTextNode(PROVIDERID));
            response.appendChild(ePROVIDERID);

            String str = MD5Key + RESPONSECODE + BANKTRANSID + TRANSID
                    + VERIFYTYPE + REFTRANSID + OTP + DATETIME + DESCRIPTION + PROVIDERID;

            String resMAC = ControllerUtil.EncriptMD5(str);
            System.out.println("OTPVERIFICATION - DATA = [" + str + "], MAC = [" + resMAC + "]");

            Element eMAC = responsedoc.createElement(OnePayConstant.MAC);
            eMAC.appendChild(responsedoc.createTextNode(resMAC));
            response.appendChild(eMAC);

            OutputFormat format = new OutputFormat(responsedoc);
            StringWriter stringOut = new StringWriter();
            XMLSerializer serial = new XMLSerializer(stringOut, format);
            serial.serialize(responsedoc);
            return stringOut.toString();
        } catch (NumberFormatException | ParserConfigurationException | SAXException | IOException | DOMException ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return OnePayConstant.SYSTEM_IS_ERROR_DEFAULT;
        }
    }
}
