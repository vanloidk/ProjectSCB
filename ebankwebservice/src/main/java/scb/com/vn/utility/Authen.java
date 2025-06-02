package scb.com.vn.utility;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import org.apache.axis.AxisFault;

import org.apache.axis.Constants;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.log4j.Level;
import org.w3c.dom.NodeList;

//import sun.misc.BASE64Encoder;
import org.apache.commons.codec.binary.*;

/**
 * Class kiem tra chung thuc. Rang buoc kiem tra chung thuc nay bao gom :
 * 1.Check accesskey 2.Check ServiceType 3.Check Timetamp < 5 phut 4.Check
 * Signature 5.Check IP (opt) 6.Check Action
 *
 * <securityHeader> <accesskey></accesskey>
 * <timestamp></timestamp>
 * <signature></signature> </securityHeader>
 */
public class Authen {

    private final int AUTH_INVALIDAUTHEN = -1;
    private final int AUTH_INVALID_PERMISSION = -4;
    private final int AUTH_OVERTIME = -5;
    private final int AUTH_INVALID_SOAPHEADER = -6; // Khong ton tai hoac khong dung voi chuoi SOAPHeader: securityHeader
    private final int AUTH_INVALID_SOAPHEADERELELEMENT = -7;
    private final int AUTH_INVALID_PARTNERCODE = -8;
    private final int AUTH_INVALID_VERIFYSIGN = -9;
    private final int AUTH_VALID = 0;
    private String _accesskey = "", _partnercode = "", _timestamp = "";
    private String _signature = "", _ip_client = "", _methodname = "";
    private Hashtable<String, String> _partnerInfo = null;

    /**
     *
     */
    public Authen() {
    }

    /**
     *
     * @param methodname
     * @param partnercode
     * @return
     * @throws AxisFault
     */
    public int checkAuthen(String methodname, String partnercode) throws AxisFault {
        MessageContext msgContext = MessageContext.getCurrentContext();
        Message msg = null;
        SOAPEnvelope envelope = null;
        SOAPHeader header = null;
        Iterator<?> it = null;
        SOAPHeaderElement hel = null;

        try {
            this._ip_client = msgContext.getStrProp(Constants.MC_REMOTE_ADDR);
            this._methodname = methodname;
            this._partnercode = partnercode;

            msg = msgContext.getRequestMessage();
            envelope = msg.getSOAPEnvelope();
            header = envelope.getHeader();
            it = header.examineAllHeaderElements();

            if (it.hasNext() == false) {
                return AUTH_INVALID_SOAPHEADER;
            }

            String headerName = null;
            while (it.hasNext()) {
                hel = (SOAPHeaderElement) it.next();
                headerName = hel.getNodeName();
                if (headerName.contains("securityHeader")) {
                    return checkHeader(hel);
                } else {
                    return AUTH_INVALID_SOAPHEADER;
                }
            }
            return AUTH_INVALIDAUTHEN;
        } catch (Exception e) {
            Helper.writeLog(Authen.class, Level.ERROR, e.getMessage());
            return AUTH_INVALIDAUTHEN;
        }
    }

    /**
     *
     * @param partnercode
     * @param functionname
     * @return
     */
    public int checkPermission(String partnercode, String functionname) {
        String strcompare = partnercode + "-" + functionname;

        //08Aug2016: Update by HieuDT to speed up program
        if (Configuration.hmWSPartnerPermissions.containsKey(strcompare.toLowerCase())) {
            return 0;
        }

//        for (String s : Configuration.lst_permission) {
//            if (strcompare.equalsIgnoreCase(s)) {
//                return 0;
//            }
//        }
        return AUTH_INVALID_PERMISSION;
    }

    private int checkParameters(SOAPHeaderElement hel) {
        Date dTimestamp = new Date();
        _accesskey = getAccessKey(hel);
        _timestamp = getTimestamp(hel);
        _signature = getSignature(hel);
        if (_timestamp == null & _signature == null) {

        }

        try {
            if (((_accesskey == null) || (_accesskey.trim().length() == 0))
                    || ((_timestamp == null) || (_timestamp.trim().length() == 0))
                    || ((_signature == null) || (_signature.trim().length() == 0))
                    || ((_methodname == null) || (_methodname.trim().length() == 0))
                    || (_partnercode == null) || (_partnercode.trim().length() == 0)) {
                clearHeaderAttrs();
                return AUTH_INVALID_SOAPHEADERELELEMENT;
            }

            // Luu y dong bo comment trong ham checkSign Do`ng 151
            // Kiem tra time, neu time so voi timestamp nho hon 5' thi invalid
            /*             
             if ((dTimestamp.getTime() + (5 * 60 * 1000)) < System.currentTimeMillis()) {
             clearHeaderAttrs(); return AUTH_OVERTIME; }
             */
            return 0;
        } catch (Exception e) {
            Helper.writeLog(Authen.class, Level.ERROR, e.getMessage());
            clearHeaderAttrs();
            return AUTH_INVALIDAUTHEN;
        }
    }

    private int checkHeader(SOAPHeaderElement hel) {
        int iCheckParas = -1;
        try {
            iCheckParas = checkParameters(hel);
            if (iCheckParas < 0) {
                return iCheckParas;
            }

            // *********** 2. Kiem tra du lieu *************
            // Lay thong tin partner
            _partnerInfo = (new PartnerInfo()).getPartnerInfo(_accesskey, _partnercode, _ip_client);
            if (_partnerInfo == null) {
                Helper.writeLog(PartnerInfo.class, Level.ERROR,
                        "[ACCESSKEY]" + _accesskey + "-[PARTNERCODE]:" + _partnercode + "-[IP]"
                        + _ip_client + "-[ERROR]" + "AUTH_INVALID_PARTNERCODE");
                clearHeaderAttrs();
                return AUTH_INVALID_PARTNERCODE;
            }

            if (_partnercode.equals("EBK")) {
                return AUTH_VALID;
            }

            // Check signature
            String signdata = (String) _partnerInfo.get("signature");
            if (checkSign(signdata, _signature, _methodname, _timestamp) == -1) {
                Helper.writeLog(Authen.class, Level.INFO, String.format(
                        "[ACCESSKEY-PARTNERCODE-TIMESTAMP-SIGNATURE-IP-Result]-[%1$s-%2$s-%3$s-%4$s-%5$s-%6$s]", _accesskey, _partnercode,
                        _timestamp, _signature, _ip_client, AUTH_INVALID_VERIFYSIGN));
                clearHeaderAttrs();
                return AUTH_INVALID_VERIFYSIGN;
            }

            return AUTH_VALID;
        } catch (Exception e) {
            Helper.writeLog(Authen.class, Level.INFO, e.getMessage());
            return AUTH_INVALIDAUTHEN;
        }
    }

    private void clearHeaderAttrs() {
        _accesskey = "";
        _partnercode = "";
        _timestamp = "";
        _methodname = "";
        _ip_client = "";
    }

    private int checkSign(String signData, String Sign_partner, String methodname, String timestamp_partner) throws Exception {
        // Tron pass DB voi timetamp HashMAC
        SecretKeySpec signingKey = new SecretKeySpec(signData.getBytes(), "HMACSHA1");

        // Acquire the MAC instance and initialize with the signing key.
        Mac mac = null;
        try {
            mac = Mac.getInstance("HMACSHA1");
        } catch (NoSuchAlgorithmException e) {
            return -1;
        }

        try {
            mac.init(signingKey);
        } catch (InvalidKeyException e) {
            return -1;
        }

        // Compute the HMAC on the digest, and set it.
        String canonicalString = methodname + timestamp_partner;
        //String verifySignature = new BASE64Encoder().encode(mac.doFinal(canonicalString.getBytes("UTF-8")));
        String verifySignature = Base64.encodeBase64String(mac.doFinal(canonicalString.getBytes("UTF-8")));

        // Check signature
        if (!verifySignature.equals(Sign_partner)) {
            return -1;
        }
        return 0;
    }

    /**
     *
     * @return
     */
    public Hashtable<String, String> getPartnerInfo() {
        return _partnerInfo;
    }

    /**
     * ********* GET HEADER SOAP ***********
     */
    private String getAccessKey(SOAPHeaderElement hel) {
        try {
            org.w3c.dom.Node paccessKeyNode = hel.getFirstChild();
            String nodename = paccessKeyNode.getNodeName();
            if (!nodename.contains("accesskey")) {
                return "";
            }
            String accessKey = paccessKeyNode.getFirstChild().getNodeValue();
            return accessKey;
        } catch (Exception e) {
            return "";
        }
    }

    private String getTimestamp(SOAPHeaderElement hel) {
        try {
            NodeList nodelist = hel.getChildNodes();
            org.w3c.dom.Node timestampNode = nodelist.item(1);
            String nodename = timestampNode.getNodeName();

            if (!nodename.contains("timestamp")) {
                return getTimestamp2(hel);
            }
            String timestamp = timestampNode.getFirstChild().getNodeValue();
            try {
                SimpleDateFormat sdate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                sdate.parse(timestamp);
                return timestamp;
            } catch (Exception e) {
                return "";
            }
        } catch (Exception e) {
            return "";
        }

    }

    private String getSignature(SOAPHeaderElement hel) {
        try {
            org.w3c.dom.Node signatureKeyNode = hel.getLastChild();
            String nodename = signatureKeyNode.getNodeName();
            if (!nodename.contains("signature")) {
                return getSignature2(hel);
            }
            String signatureKey = signatureKeyNode.getFirstChild().getNodeValue();
            return signatureKey;
        } catch (Exception e) {
            return "";
        }

    }

    private String getTimestamp2(SOAPHeaderElement hel) {
        try {
            org.w3c.dom.Node signatureKeyNode = hel.getLastChild();
            String nodename = signatureKeyNode.getNodeName();
            if (!nodename.contains("timestamp")) {
                // return getSignature(hel);
                return "";
            }
            String timestamp = signatureKeyNode.getFirstChild().getNodeValue();
            try {
                SimpleDateFormat sdate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                sdate.parse(timestamp);
                return timestamp;
            } catch (Exception e) {
                return "";
            }
        } catch (Exception e) {
            return "";
        }

    }

    private String getSignature2(SOAPHeaderElement hel) {
        try {
            NodeList nodelist = hel.getChildNodes();
            org.w3c.dom.Node timestampNode = nodelist.item(1);
            String nodename = timestampNode.getNodeName();

            if (!nodename.contains("signature")) {
                return "";
            }
            String signature = timestampNode.getFirstChild().getNodeValue();
            return signature;
        } catch (Exception e) {
            return "";
        }

    }
}
