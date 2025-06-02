package scb.com.vn.utility;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.xml.crypto.Data;
import javax.xml.crypto.URIDereferencer;
import javax.xml.crypto.URIReference;
import javax.xml.crypto.URIReferenceException;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.keyinfo.X509IssuerSerial;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.crypto.dsig.spec.XPathFilterParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.misc.BASE64Encoder;

/**
 *
 * @author minhndb
 */
public class XMLSigner {

    /**
     *
     * @param fileData
     * @param privateKey
     * @param certificateChain
     * @param tagPath
     * @param tagSign
     * @return
     * @throws Exception
     */
    public byte[] signXMLFileXPath(byte[] fileData, PrivateKey privateKey,
            Certificate[] certificateChain, String[] tagPath, String tagSign)
            throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setNamespaceAware(true);
        Document doc = dbFactory.newDocumentBuilder().parse(
                new ByteArrayInputStream(fileData));
        String providerName = System.getProperty("jsr106Provider",
                "org.jcp.xml.dsig.internal.dom.XMLDSigRI");

        final XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM");
        /*final XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM",
				(Provider) Class.forName(providerName).newInstance());*/
        String referenceURI = null;
        XPathExpression expr = null;
        NodeList nodes;
        // List transforms = null;
        List<Transform> listTransform = null;
        TransformParameterSpec param = null;
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        for (int i = 0; i < tagPath.length; i++) {
            expr = xpath.compile(tagPath[i]);
            nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            if (nodes.getLength() < 1) {
                System.out.println("Không tìm thấy node qua PATH: "
                        + tagPath[i]);
                throw new Exception("Không tìm thấy node qua PATH: "
                        + tagPath[i]);
            }
        }
        String pathArrToStr = "";
        for (int i = 0; i < tagPath.length; i++) {
            pathArrToStr += "ancestor-or-self::"
                    + tagPath[i].substring(tagPath[i].lastIndexOf("/") + 1)
                    + " or ";
        }
        pathArrToStr = pathArrToStr.substring(0, pathArrToStr.length() - 4);
        System.out.println("pathArrToStr=" + pathArrToStr);

        // final String path = "//ancestor-or-self::" +
        // tagPath.substring(tagPath.lastIndexOf("/") + 1);
        final String path = pathArrToStr;

        referenceURI = "";
        Transform transform = null;
        listTransform = Collections
                .synchronizedList(new ArrayList<Transform>());
        transform = sigFactory.newTransform(Transform.XPATH,
                new XPathFilterParameterSpec(path));
        listTransform.add(transform);

        /*
		 * List<XPathType> xpaths = new ArrayList<XPathType>(); xpaths.add(new
		 * XPathType(path, XPathType.Filter.INTERSECT)); listTransform =
		 * Collections.synchronizedList(new ArrayList<Transform>()); transform =
		 * sigFactory.newTransform(Transform.XPATH2, new
		 * XPathFilter2ParameterSpec(xpaths)); listTransform.add(transform);
		 * 
		 * List<String> prefix = Collections.synchronizedList(new
		 * ArrayList<String>()); prefix.add(ExcC14NParameterSpec.DEFAULT); param
		 * = new ExcC14NParameterSpec(prefix); // transform =
		 * sigFactory.newTransform
		 * (CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS, param); //transform
		 * =
		 * sigFactory.newTransform(CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS
		 * ,(TransformParameterSpec) null); transform =
		 * sigFactory.newTransform(CanonicalizationMethod.ENVELOPED,
		 * (TransformParameterSpec) null); listTransform.add(transform);
         */
        X509Certificate cert = (X509Certificate) certificateChain[0];
        PublicKey publicKey = cert.getPublicKey();

        Reference ref = sigFactory.newReference(referenceURI,
                sigFactory.newDigestMethod(DigestMethod.SHA1, null),
                listTransform, null, null);

        SignedInfo signedInfo = sigFactory.newSignedInfo(sigFactory
                .newCanonicalizationMethod(
                        CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS,
                        (C14NMethodParameterSpec) param), sigFactory
                        .newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));

        KeyInfoFactory keyInfoFactory = sigFactory.getKeyInfoFactory();
        List x509Content = new ArrayList();
        x509Content.add(cert.getSubjectX500Principal().getName() + "|TGKY="
                + convertDateToString(new Date(), "DDMMYYYYHH24MISS"));
        x509Content.add(cert);
        X509Data x509Data = keyInfoFactory.newX509Data(x509Content);
        KeyValue keyValue = keyInfoFactory.newKeyValue(publicKey);
        KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Arrays.asList(keyValue,
                x509Data));

        Node elmSign = null;
        if (tagSign != null && !tagSign.equalsIgnoreCase("")) {
            elmSign = doc.getElementsByTagName(tagSign).item(0);
        } else {
            elmSign = doc.getDocumentElement();
        }
        DOMSignContext dsc = new DOMSignContext(privateKey, elmSign);
        // DOMSignContext dsc = new DOMSignContext(privateKey,
        // doc.getDocumentElement());
        dsc.setURIDereferencer(new URIDereferencer() {
            @Override
            public Data dereference(URIReference uriReference,
                    XMLCryptoContext context) throws URIReferenceException {
                final String providerName = System.getProperty(
                        "jsr105Provider",
                        "org.jcp.xml.dsig.internal.dom.XMLDSigRI");

                XMLSignatureFactory fac = null;
                try {
                    /*fac = XMLSignatureFactory.getInstance("DOM",
							(Provider) Class.forName(providerName)
									.newInstance());*/
                    fac = XMLSignatureFactory.getInstance("DOM");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Data data = fac.getURIDereferencer().dereference(uriReference,
                        context);
                return data;
            }
        });

        XMLSignature signature = sigFactory
                .newXMLSignature(signedInfo, keyInfo);
        signature.sign(dsc);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Transformer trans = TransformerFactory.newInstance().newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));
        os.flush();
        return os.toByteArray();
    }

    /**
     *
     * @param fileData
     * @param privateKey
     * @param certificateChain
     * @param tagRef
     * @param tagSign
     * @return
     * @throws Exception
     */
    public byte[] signXMLFile(byte[] fileData, PrivateKey privateKey, Certificate[] certificateChain, String tagRef, String tagSign)
            throws Exception {
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        String ndungSign = "";
        if ((tagRef != null) && (!tagRef.equalsIgnoreCase(""))) {
            ndungSign = "#" + tagRef;
        }
        Reference ref = fac.newReference(ndungSign,
                fac.newDigestMethod("http://www.w3.org/2000/09/xmldsig#sha1",
                        null), Collections.singletonList(
                        fac.newTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature",
                                (XMLStructure) null)), null, null);

        SignedInfo si = fac
                .newSignedInfo(fac.newCanonicalizationMethod(
                        "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments",
                        (XMLStructure) null), fac
                                .newSignatureMethod("http://www.w3.org/2000/09/xmldsig#rsa-sha1", null),
                        Collections.singletonList(ref));
        X509Certificate certChain = (X509Certificate) certificateChain[0];
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        List x509Content = new ArrayList();
        x509Content.add(certChain.getSubjectX500Principal().getName());
        x509Content.add(certChain);
        X509Data xd = kif.newX509Data(x509Content);

        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(
                new ByteArrayInputStream(fileData));

        Node elmSign = null;
        if ((tagSign != null) && (!tagSign.equalsIgnoreCase(""))) {
            elmSign = doc.getElementsByTagName(tagSign).item(0);
        } else {
            elmSign = doc.getDocumentElement();
        }
        DOMSignContext dsc = new DOMSignContext(privateKey, elmSign);

        XMLSignature signature = fac.newXMLSignature(si, ki);

        signature.sign(dsc);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Result result = new StreamResult(out);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.transform(new DOMSource(doc), result);
        return out.toByteArray();
    }

    /**
     *
     * @param d
     * @param format
     * @return
     */
    public static String convertDateToString(Date d, String format) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return convertDateToString(c, format);
    }

    /**
     *
     * @param d
     * @param format
     * @return
     */
    public static String convertDateToString(Calendar d, String format) {
        String dd = Integer.toString(d.get(5));
        String mm = Integer.toString(d.get(2) + 1);
        String yyyy = Integer.toString(d.get(1));
        String hh = Integer.toString(d.get(11));
        String mi = Integer.toString(d.get(12));
        String ss = Integer.toString(d.get(13));
        String ms = Integer.toString(d.get(14));

        if (dd.length() == 1) {
            dd = "0" + dd;
        }
        if (mm.length() == 1) {
            mm = "0" + mm;
        }
        if (hh.length() == 1) {
            hh = "0" + hh;
        }
        if (mi.length() == 1) {
            mi = "0" + mi;
        }
        if (ss.length() == 1) {
            ss = "0" + ss;
        }
        if (ms.length() == 1) {
            ms = "0" + ms;
        }
        if ("DD".equalsIgnoreCase(format)) {
            return dd;
        }
        if ("MM".equalsIgnoreCase(format)) {
            return mm;
        }
        if ("YYYY".equalsIgnoreCase(format)) {
            return yyyy;
        }
        if ("MM/YYYY".equals(format)) {
            return mm + "/" + yyyy;
        }
        if ("DD/MM/YYYY".equals(format)) {
            return dd + "/" + mm + "/" + yyyy;
        }
        if ("DD/MM/YYYY HH:MI:SS".equals(format)) {
            return dd + "/" + mm + "/" + yyyy + " " + hh + ":" + mi + ":" + ss;
        }
        if ("DDMMYYYY HH:MI:SS".equals(format)) {
            return dd + mm + yyyy + " " + hh + ":" + mi + ":" + ss;
        }
        if ("DDMMYYYYHH24MISS".equals(format)) {
            return dd + mm + yyyy + hh + mi + ss;
        }
        if ("DDMMYYYYHH24MISSMS".equals(format)) {
            return dd + mm + yyyy + hh + mi + ss + ms;
        }
        if ("DDMMYYYY".equals(format)) {
            return dd + mm + yyyy;
        }
        if ("MMYYYY".equals(format)) {
            return mm + yyyy;
        }
        if ("DD-MMM-YYYY HH:MI:SS".equals(format)) {
            SimpleDateFormat format2 = new SimpleDateFormat(
                    "dd-MMM-yyyy HH:mm:ss");
            return format2.format(d.getTime());
        }
        if ("YYYYMMDDHH24MISSMS".equals(format)) {
            return yyyy + mm + dd + hh + mi + ss + ms;
        }
        if ("yyyyDDDHHmmss".equals(format)) {
            SimpleDateFormat format2 = new SimpleDateFormat(format);
            return format2.format(d.getTime());
        }
        return null;
    }

    /**
     *
     * @param strDate
     * @param format
     * @return
     */
    public static Calendar convertStringToDate(String strDate, String format) {
        Calendar cal = null;
        if ("DD/MM/YYYY".equals(format)) {
            String[] dElement = strDate.split("/");
            cal = Calendar.getInstance();
            cal.set(5, new Integer(dElement[0]).intValue());
            cal.set(2, new Integer(dElement[1]).intValue() - 1);
            cal.set(1, new Integer(dElement[2]).intValue());
        } else if ("DD/MM/YYYY HH:MI:SS".equals(format)) {
            String dateValue = strDate.substring(0, strDate.indexOf(" "));
            String timeValue = strDate.substring(strDate.indexOf(" ") + 1);
            String[] dElement = dateValue.split("/");
            String[] tElement = timeValue.split(":");
            cal = Calendar.getInstance();
            cal.set(5, new Integer(dElement[0]).intValue());
            cal.set(2, new Integer(dElement[1]).intValue() - 1);
            cal.set(1, new Integer(dElement[2]).intValue());
            cal.set(11, new Integer(tElement[0]).intValue());
            cal.set(12, new Integer(tElement[1]).intValue());
            cal.set(13, new Integer(tElement[2]).intValue());
        }

        return cal;
    }

    /**
     *
     * @param strDate
     * @param format
     * @param timezoneID
     * @return
     */
    public static Calendar convertStringToDate(String strDate, String format, String timezoneID) {
        Calendar cal = convertStringToDate(strDate, format);
        cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
        return cal;
    }

    /**
     *
     * @param d
     * @return
     */
    public static String convertDateToString(Date d) {
        if (d == null) {
            return null;
        }
        String dStr = d.toString();
        String yyyy = dStr.substring(0, 4);
        String mm = dStr.substring(5, 7);
        String dd = dStr.substring(8);

        return dd + "/" + mm + "/" + yyyy;
    }

    /**
     *
     * @param nl
     * @param orderNode
     * @return
     * @throws Exception
     */
    public static boolean verify(NodeList nl, int orderNode) throws Exception {
        DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(), nl.item(orderNode));
        XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM");
        XMLSignature signature = sigFactory.unmarshalXMLSignature(valContext);
        boolean result = false;
        result = signature.validate(valContext);
        return result;
    }

    /**
     *
     * @param doc
     * @param privateKey
     * @param certificateChain
     * @param tagPath
     * @param tagSign
     * @return
     * @throws Exception
     */
    public Document signXMLFileXPath(Document doc, PrivateKey privateKey,
            Certificate[] certificateChain, String[] tagPath, String tagSign)
            throws Exception {

        final XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM");
        String referenceURI = null;
        XPathExpression expr = null;
        NodeList nodes;
        // List transforms = null;
        List<Transform> listTransform = null;
        TransformParameterSpec param = null;
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        for (int i = 0; i < tagPath.length; i++) {
            expr = xpath.compile(tagPath[i]);
            nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            if (nodes.getLength() < 1) {
                System.out.println("Không tìm thấy node qua PATH: "
                        + tagPath[i]);
                throw new Exception("Không tìm thấy node qua PATH: " + tagPath[i]);
            }
        }
        referenceURI = "";
        Transform transform = null;
        listTransform = Collections
                .synchronizedList(new ArrayList<Transform>());
        transform = sigFactory.newTransform(CanonicalizationMethod.ENVELOPED,
                (TransformParameterSpec) null);
        listTransform.add(transform);

        X509Certificate cert = (X509Certificate) certificateChain[0];
        PublicKey publicKey = cert.getPublicKey();

        Reference ref = sigFactory.newReference(referenceURI,
                sigFactory.newDigestMethod(DigestMethod.SHA1, null),
                listTransform, null, null);

        SignedInfo signedInfo = sigFactory.newSignedInfo(sigFactory
                .newCanonicalizationMethod(
                        CanonicalizationMethod.EXCLUSIVE_WITH_COMMENTS,
                        (C14NMethodParameterSpec) param), sigFactory
                        .newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));

        KeyInfoFactory keyInfoFactory = sigFactory.getKeyInfoFactory();
        List x509Content = new ArrayList();
        X509IssuerSerial issuer = keyInfoFactory.newX509IssuerSerial(cert.getIssuerX500Principal().getName(),
                 new BigInteger("1492362386097869370206365888731461396864"));
        //, cert.getSerialNumber());

        x509Content.add(issuer);
        x509Content.add(cert);
        X509Data x509Data = keyInfoFactory.newX509Data(x509Content);
        KeyValue keyValue = keyInfoFactory.newKeyValue(publicKey);
        KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(
                x509Data));

        Node elmSign = null;
        if (tagSign != null && !tagSign.equalsIgnoreCase("")) {
            elmSign = doc.getElementsByTagName(tagSign).item(0);
        } else {
            elmSign = doc.getDocumentElement();
        }
        DOMSignContext dsc = new DOMSignContext(privateKey, elmSign);
        // DOMSignContext dsc = new DOMSignContext(privateKey,
        // doc.getDocumentElement());
        dsc.setURIDereferencer(new URIDereferencer() {
            @Override
            public Data dereference(URIReference uriReference,
                    XMLCryptoContext context) throws URIReferenceException {
                final String providerName = System.getProperty(
                        "jsr105Provider",
                        "org.jcp.xml.dsig.internal.dom.XMLDSigRI");

                XMLSignatureFactory fac = null;
                try {
                    /*fac = XMLSignatureFactory.getInstance("DOM",
							(Provider) Class.forName(providerName)
									.newInstance());*/
                    fac = XMLSignatureFactory.getInstance("DOM");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Data data = fac.getURIDereferencer().dereference(uriReference,
                        context);
                return data;
            }
        });

        XMLSignature signature = sigFactory
                .newXMLSignature(signedInfo, keyInfo);
        signature.sign(dsc);

        /*		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.transform(new DOMSource(doc), new StreamResult(os));
		os.flush();*/
        return doc;
    }

    /**
     *
     * @param PathcertFile
     * @return
     */
    public static String readContentCertFile(String PathcertFile) {
        String ContentcertFile = new String();
        try {
            File certFile = new File(PathcertFile);
            FileReader fileReader = new FileReader(certFile);
            BufferedReader bufferReader = new BufferedReader(fileReader);

            String begin = bufferReader.readLine();
            if (begin.equals("-----BEGIN CERTIFICATE-----") == false) {
                throw new IOException("Couldn't find certificate beginning");
            }

            boolean trucking = true;

            while (trucking) {
                String line = bufferReader.readLine();
                if (line.startsWith("-----")) {
                    trucking = false;
                } else {
                    ContentcertFile += line;
                }
            }
            bufferReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // logger.error("readContentCertFile", e);
        } catch (IOException e) {
            e.printStackTrace();
            // logger.error("readContentCertFile", e);
        }

        return ContentcertFile;
    }

    /**
     *
     * @param keyStoreFile
     * @param keyStorePwd
     * @return
     * @throws Exception
     */
    public static String getPublickey(String keyStoreFile, String keyStorePwd) throws Exception {
        IHTKKKeyStoreBean keyStore = IHTKKKeyStoreBean.getKeyStore(keyStoreFile, keyStorePwd);
        Certificate[] certificateChain = keyStore.getCertChain();
        X509Certificate cert = (X509Certificate) certificateChain[0];
        PublicKey publicKey = cert.getPublicKey();
        byte[] publicKeyBytes = publicKey.getEncoded();
        //Convert Public key to String
        BASE64Encoder encoder = new BASE64Encoder();
        String pubKeyStr = encoder.encode(publicKeyBytes);
        return pubKeyStr;
    }

}
