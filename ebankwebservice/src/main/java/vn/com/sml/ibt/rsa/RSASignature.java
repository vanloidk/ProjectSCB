package vn.com.sml.ibt.rsa;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;


import org.xml.sax.SAXException;
import org.w3c.dom.*;

/**
 * Implement RSA digital signature
 *
 * @author quangtt
 * @version 1.0
 * @since 17/02/2011
 */
public class RSASignature {

    /**
     *
     * @param modulus
     * @param publicExponent
     * @return
     */
    public static PublicKey getPublicKeyFromParameters(String modulus, String publicExponent) {
        try {
            BigInteger modPbInt = new BigInteger(1, Base64.decode(modulus));
            BigInteger expPbInt = new BigInteger(1, Base64.decode(publicExponent));
            RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(modPbInt, expPbInt);
            return KeyFactory.getInstance("RSA").generatePublic(pubKeySpec);
        } catch (InvalidKeySpecException iex) {
            iex.printStackTrace();
        } catch (NoSuchAlgorithmException nex) {
            nex.printStackTrace();
        }
        return null;
    }

    private static PublicKey getPublicKeyFromXMLFile(String filename) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filename);
            return getPublicKeyfromXMLDoc(doc);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    private static PublicKey getPublicKeyfromXMLDoc(Document doc) {

        NodeList list = doc.getElementsByTagName("Modulus");
        if (list.getLength() != 1) {
            System.out.println("Can not get modulus");
            return null;
        }
        String modulus = list.item(0).getTextContent();
        list = doc.getElementsByTagName("Exponent");
        if (list.getLength() != 1) {
            System.out.println("Can not get Exponent");
            return null;
        }
        String publicExponent = list.item(0).getTextContent();
        System.out.println("Modulus: " + modulus + " || exponent: " + publicExponent);
        return getPublicKeyFromParameters(modulus, publicExponent);
    }

    private static PublicKey getPublicKeyfromString(String strPublicKey, EncodingType type) {
        switch (type) {
            case HEX:
                return getPublicKeyfromByteArray(Hex.decode(strPublicKey));
            case BASE64:
                return getPublicKeyfromByteArray(Base64.decode(strPublicKey));
            default:
                return getPublicKeyfromByteArray(Hex.decode(strPublicKey));
        }
        //return getPublicKeyfromByteArray(Base64.decode(strPublicKey));
    }

    /**
     *
     * @param arr
     * @return
     */
    public static PublicKey getPublicKeyfromByteArray(byte[] arr) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(arr);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            return publicKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static PublicKey getPublicKeyfromBinaryFile(String fileName) {
        try {
            return getPublicKeyfromByteArray(FileUtil.readBytesFromFile(fileName));
        } catch (Exception e) {
        }
        return null;
    }

    private static PublicKey getPublicKeyfromStringFile(String fileName, EncodingType type) {
        try {
            return getPublicKeyfromString(new String(FileUtil.readBytesFromFile(fileName)), type);
        } catch (Exception e) {
        }
        return null;
    }

    private static PublicKey getPublicKeyfromXMLString(String xmlString) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(xmlString.getBytes());
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bis);
            return getPublicKeyfromXMLDoc(doc);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param stInput
     * @param prKey
     * @param algorithm
     * @param type
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    public static String makeSignature(String stInput, PrivateKey prKey, String algorithm, EncodingType type)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        Signature sig = Signature.getInstance(algorithm);
        sig.initSign(prKey);
        sig.update(stInput.getBytes());
        switch (type) {
            case HEX:
                return new String(Hex.encode(sig.sign()));
            case BASE64:
                return new String(Base64.encode(sig.sign()));
            default:
                return new String(Base64.encode(sig.sign()));
        }
    }

    /**
     *
     * @param pubKey
     * @param data
     * @param sigMsg
     * @param algorithm
     * @param type
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verifySignature(PublicKey pubKey, String data, String sigMsg, String algorithm, EncodingType type)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sig = Signature.getInstance(algorithm);
        sig.initVerify(pubKey);
        sig.update(data.getBytes());
        switch (type) {
            case HEX:
                return sig.verify(Hex.decode(sigMsg));
            case BASE64:
                return sig.verify(Base64.decode(sigMsg));
            default:
                return sig.verify(Base64.decode(sigMsg));
        }
    }

    /**
     *
     * @param modulus
     * @param publicExponent
     * @param privateExponent
     * @param primeP
     * @param primeQ
     * @param primeExponentP
     * @param primeExponentQ
     * @param crtCoefficient
     * @return
     */
    public static PrivateKey getPrivateKeyFromParameters(String modulus,
            String publicExponent, String privateExponent,
            String primeP, String primeQ,
            String primeExponentP,
            String primeExponentQ, String crtCoefficient) {
        try {
            BigInteger m = new BigInteger(1, Base64.decode(modulus));
            BigInteger pe = new BigInteger(1, Base64.decode(publicExponent));
            BigInteger pre = new BigInteger(1, Base64.decode(privateExponent));
            BigInteger priP = new BigInteger(1, Base64.decode(primeP));
            BigInteger priQ = new BigInteger(1, Base64.decode(primeQ));
            BigInteger priEP = new BigInteger(1, Base64.decode(primeExponentP));
            BigInteger priEQ = new BigInteger(1, Base64.decode(primeExponentQ));
            BigInteger crt = new BigInteger(1, Base64.decode(crtCoefficient));
            RSAPrivateCrtKeySpec crtKeySpec = new RSAPrivateCrtKeySpec(m, pe, pre, priP, priQ, priEP, priEQ, crt);
            return KeyFactory.getInstance("RSA").generatePrivate(crtKeySpec);
        } catch (InvalidKeySpecException iex) {
            iex.printStackTrace();
        } catch (NoSuchAlgorithmException nex) {
            nex.printStackTrace();
        }
        return null;

    }

    private static PrivateKey getPrivateKeyFromXMLDoc(Document doc) {
        //get modulus
        NodeList list = doc.getElementsByTagName("Modulus");
        if (list.getLength() != 1) {
            System.out.println("Can not get modulus");
            return null;
        }
        String modulus = list.item(0).getTextContent();
        //get publicExponent
        list = doc.getElementsByTagName("Exponent");
        if (list.getLength() != 1) {
            System.out.println("Can not get Exponent");
            return null;
        }
        String publicExponent = list.item(0).getTextContent();
        //get primeP
        list = doc.getElementsByTagName("P");
        if (list.getLength() != 1) {
            System.out.println("Can not get Exponent");
            return null;
        }
        String primeP = list.item(0).getTextContent();
        //get primeQ
        list = doc.getElementsByTagName("Q");
        if (list.getLength() != 1) {
            System.out.println("Can not get Exponent");
            return null;
        }
        String primeQ = list.item(0).getTextContent();
        //get primeExponentP
        list = doc.getElementsByTagName("DP");
        if (list.getLength() != 1) {
            System.out.println("Can not get Exponent");
            return null;
        }
        String primeExponentP = list.item(0).getTextContent();
        //get primeExponentQ
        list = doc.getElementsByTagName("DQ");
        if (list.getLength() != 1) {
            System.out.println("Can not get Exponent");
            return null;
        }
        String primeExponentQ = list.item(0).getTextContent();
        //get primeExponentP
        list = doc.getElementsByTagName("D");
        if (list.getLength() != 1) {
            System.out.println("Can not get Exponent");
            return null;
        }
        String privateExponent = list.item(0).getTextContent();
        //get crtCoefficient
        list = doc.getElementsByTagName("InverseQ");
        if (list.getLength() != 1) {
            System.out.println("Can not get Exponent");
            return null;
        }
        String crtCoefficient = list.item(0).getTextContent();
        return getPrivateKeyFromParameters(modulus, publicExponent, privateExponent, primeP, primeQ, primeExponentP, primeExponentQ, crtCoefficient);

    }

    private static PrivateKey getPriateKeyFromXMLFile(String filename) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filename);
            return getPrivateKeyFromXMLDoc(doc);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    private static PrivateKey getPrivateKeyfromXMLString(String xmlString) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(xmlString.getBytes());
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bis);
            return getPrivateKeyFromXMLDoc(doc);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    private static PrivateKey getPrivateKeyfromString(String strPrivateKey, EncodingType type) {
        switch (type) {
            case HEX:
                return getPrivateKeyfromByteArray(Hex.decode(strPrivateKey));
            case BASE64:
                return getPrivateKeyfromByteArray(Base64.decode(strPrivateKey));
            default:
                return getPrivateKeyfromByteArray(Hex.decode(strPrivateKey));
        }
    }

    /**
     *
     * @param arr
     * @return
     */
    public static PrivateKey getPrivateKeyfromByteArray(byte[] arr) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new PKCS8EncodedKeySpec(arr);
            PrivateKey privateKey = keyFactory.generatePrivate(publicKeySpec);
            return privateKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static PrivateKey getPrivateKeyfromStringFile(String filename, EncodingType type) {
        try {
            return getPrivateKeyfromString(new String(FileUtil.readBytesFromFile(filename)), type);
        } catch (Exception e) {
        }
        return null;
    }

    private static PrivateKey getPrivateKeyfromBinaryFile(String fileName) {
        try {
            return getPrivateKeyfromByteArray(FileUtil.readBytesFromFile(fileName));
        } catch (Exception e) {
        }
        return null;
    }

    /**
     *
     * @param str
     * @param storeMode
     * @param type
     * @return
     */
    public static PrivateKey getPrivateKey(String str, SignatureKeyStoreMode storeMode, EncodingType type) {
        switch (storeMode) {
            case STRING:
                return getPrivateKeyfromString(str, type);
            case XMLSTRING:
                return getPrivateKeyfromXMLString(str);
            case BINARYFILE:
                return getPrivateKeyfromBinaryFile(str);
            case STRINGFILE:
                return getPrivateKeyfromStringFile(str, type);
            case XMLFILE:
                return getPriateKeyFromXMLFile(str);
            default:
                return null;
        }
    }

    /**
     *
     * @param str
     * @param storeMode
     * @param type
     * @return
     */
    public static PublicKey getPublicKey(String str, SignatureKeyStoreMode storeMode, EncodingType type) {
        switch (storeMode) {
            case STRING:
                return getPublicKeyfromString(str, type);
            case XMLSTRING:
                return getPublicKeyfromXMLString(str);
            case BINARYFILE:
                return getPublicKeyfromBinaryFile(str);
            case STRINGFILE:
                return getPublicKeyfromStringFile(str, type);
            case XMLFILE:
                return getPublicKeyFromXMLFile(str);
            default:
                return null;
        }
    }

//    public static void main(String args[]) {
//        try {
////            PublicKey pub = getPublicKeyFromXMLFile("D:\\Developement\\TestPrjs\\VNGClient\\cfg\\vng_publickey.xml");
////            System.out.println(verifySignature(pub, "-5", "oFo+p5Kc4x0lzvjdnHR1/0IfoWTAN9C8/yjnu8dc92rNCXBZ/QrIIHFkVb3/JtCdcDn50E9vhjk7PGTiBDruk83nxI3nKgdPl4yXzTFn/slqLc9OVQ4nYTpsdK1KeEpj6YsDEuj14+wiTM+eKHpFAcmisb4b1mVC/gFLFnFtVxk="));
////            RSASignature obj = new RSASignature();
////            System.out.println(obj.returnVerifySignature("oFo+p5Kc4x0lzvjdnHR1/0IfoWTAN9C8/yjnu8dc92rNCXBZ/QrIIHFkVb3/JtCdcDn50E9vhjk7PGTiBDruk83nxI3nKgdPl4yXzTFn/slqLc9OVQ4nYTpsdK1KeEpj6YsDEuj14+wiTM+eKHpFAcmisb4b1mVC/gFLFnFtVxk=", "-5"));
////            String str = "-5|oFo+p5Kc4x0lzvjdnHR1/0IfoWTAN9C8/yjnu8dc92rNCXBZ/QrIIHFkVb3/JtCdcDn50E9vhjk7PGTiBDruk83nxI3nKgdPl4yXzTFn/slqLc9OVQ4nYTpsdK1KeEpj6YsDEuj14+wiTM+eKHpFAcmisb4b1mVC/gFLFnFtVxk=";
////            System.out.println(str.split("\\|")[0]);
////            System.out.println(str.split("\\|")[1]);
////            String encodedValue = new BASE64Encoder().encode(publicKeyBytes);
////            writeKeyBytesToFile(encodedValue.getBytes(), "D:\\Developement\\TestPrjs\\VNGClient\\cfg\\vng_publickey_test.rsa");
//            //System.out.println(new String(readBytesFromFile("D:\\Developement\\TestPrjs\\VNGClient\\cfg\\vng_publickey_test.rsa")));
//            PublicKey pub = getPublicKeyfromBinaryFile("D:\\Developement\\TestPrjs\\VNGTopupService\\cfg\\vng_pubkey.rsa");
//            PrivateKey prv = getPrivateKeyfromStringFile("D:\\Developement\\TestPrjs\\VNGTopupService\\cfg\\sml_private.rsa",EncodingType.BASE64);
//            System.out.println(pub==null);
//            System.out.println(prv==null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
