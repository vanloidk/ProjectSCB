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
import scb.com.vn.utility.SMSUtils;

/**
 *
 * @author minhndb - NGUYEN DAC BINH MINH
 */
public class InternalController {

    /**
     *
     * @param strXML
     * @return
     */
    public String OTPREQUEST(String strXML) {
        try {
            System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(strXML)));
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
            String otp = SMSUtils.CreateOTP();
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, "OTP = [" + otp + "]");
            String[] result = Helper.getDBI().OTP_REQUEST(TRANSID, PROVIDERID, PHONENUMBER,
                     CHANNELID, TRANSTYPE, otp);
            String status = result[0];
            if (status.endsWith("00")) {
                banktransid = result[1];
                int sendSMS = SMSUtils.SendOTPSMS(PHONENUMBER, otp, DESCRIPTION);
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
        } catch (ParserConfigurationException | SAXException | IOException | DOMException ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
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
            System.out.print(strXML);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(strXML)));
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
        } catch (ParserConfigurationException | SAXException | IOException | DOMException ex) {
            Helper.writeLogError(scb.com.vn.payment.OnlinePaymentController.class, ex);
            return "99";
        }
    }
}
