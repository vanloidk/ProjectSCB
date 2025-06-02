package vn.com.sml.ibt.rsa;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * - Base on 'RSA Security' from Shaheryar
 * (http://www.codeproject.com/KB/security/porting_java_public_key.aspx)
 *
 * @author quangtt
 * @version 1.0
 * @since 17/02/2011
 */
public class RSAKeygen {

    private KeyPairGenerator keyGen; //Key pair generator for RSA
    private PrivateKey privateKey; // Private Key Class
    private PublicKey publicKey; // Public Key Class
    private KeyPair keypair; // KeyPair Class
    private String PRIVATE_KEY_FILE = "sml_private.rsa"; // Private key file.
    private String PUBLIC_KEY_FILE = "sml_public.rsa"; // Public key file.
    private String DOT_NET_PUBLIC_KEY_FILE = "dotnet_sml_public.xml"; // File to store .Net Compatible Key Data

    /**
     * Generates the keys for given size.
     *
     * @param size - Key Size [512|1024]
     * @param type
     */
    public void generateKeys(int size, EncodingType type) {
        try {
            System.out.println("Generatign Keys");
            //Get Key Pair Generator for RSA.
            keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(size);
            keypair = keyGen.genKeyPair();
            privateKey = keypair.getPrivate();
            publicKey = keypair.getPublic();

            // Get the bytes of the public and private keys
            byte[] privateKeyBytes = privateKey.getEncoded();
            byte[] publicKeyBytes = publicKey.getEncoded();

            //write bytes to corresponding files.
            switch (type) {
                case HEX:
                    FileUtil.writeBytesToFile(Hex.encode(privateKeyBytes), PRIVATE_KEY_FILE);
                    FileUtil.writeBytesToFile(Hex.encode(publicKeyBytes), PUBLIC_KEY_FILE);
                    break;
                case BASE64:
                    FileUtil.writeBytesToFile(Base64.encode(privateKeyBytes), PRIVATE_KEY_FILE);
                    FileUtil.writeBytesToFile(Base64.encode(publicKeyBytes), PUBLIC_KEY_FILE);
                    break;
                default:
                    FileUtil.writeBytesToFile(Hex.encode(privateKeyBytes), PRIVATE_KEY_FILE);
                    FileUtil.writeBytesToFile(Hex.encode(publicKeyBytes), PUBLIC_KEY_FILE);
            }
//            FileUtil.writeBytesToFile(Base64.encode(privateKeyBytes), PRIVATE_KEY_FILE);
//            FileUtil.writeBytesToFile(Base64.encode(publicKeyBytes), PUBLIC_KEY_FILE);

            //Generate the Private Key, Public Key and Public Key in XML format.
            //PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
            //PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
            //store the public key in XML string to make compatible .Net public key file
            String xml = getRSAPublicKeyAsXMLString(rsaPublicKey);
            //Store the XML (Generated .Net public key file) in file
            FileUtil.writeBytesToFile(xml.getBytes(), DOT_NET_PUBLIC_KEY_FILE);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occure." + e.getCause());
        }

    }

    /**
     * Generates the keys for given size.
     *
     * @param size - Key Size [512|1024]
     * @param private_file_location
     * @param public_file_location
     * @param dotnet_public_file_location
     * @param type
     */
    public void generateKeys(int size, String private_file_location, String public_file_location, String dotnet_public_file_location, EncodingType type) {
        this.PRIVATE_KEY_FILE = private_file_location;
        this.PUBLIC_KEY_FILE = public_file_location;
        this.DOT_NET_PUBLIC_KEY_FILE = dotnet_public_file_location;
        generateKeys(size, type);
    }

    /**
     * Gets the RSA Public key as XML string.
     *
     * @param key RSAPublicKey
     * @return String XML representation of RSA Public Key.
     * @throws UnsupportedEncodingException
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    private String getRSAPublicKeyAsXMLString(RSAPublicKey key) throws
            UnsupportedEncodingException,
            ParserConfigurationException,
            TransformerException {
        Document xml = getRSAPublicKeyAsXML(key);
        Transformer transformer
                = TransformerFactory.newInstance().newTransformer();
        StringWriter sw = new StringWriter();
        transformer.transform(new DOMSource(xml), new StreamResult(sw));
        return sw.getBuffer().toString();
    }

    /**
     * Gets the RSA Public Key as XML. The key idea is to make the key readable
     * for .Net platform.
     *
     * @param key RSAPublicKey
     * @return Document XML document.
     * @throws ParserConfigurationException
     * @throws UnsupportedEncodingException
     */
    private Document getRSAPublicKeyAsXML(RSAPublicKey key) throws
            ParserConfigurationException,
            UnsupportedEncodingException {
        Document result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element rsaKeyValue = result.createElement("RSAKeyValue");
        result.appendChild(rsaKeyValue);
        Element modulus = result.createElement("Modulus");
        rsaKeyValue.appendChild(modulus);

        byte[] modulusBytes = key.getModulus().toByteArray();
        modulusBytes = stripLeadingZeros(modulusBytes);
        modulus.appendChild(result.createTextNode(new String(Base64.encode(modulusBytes))));

        Element exponent = result.createElement("Exponent");
        rsaKeyValue.appendChild(exponent);

        byte[] exponentBytes = key.getPublicExponent().toByteArray();
        exponent.appendChild(result.createTextNode(new String(Base64.encode(exponentBytes))));

        return result;
    }

    /**
     * Utility method to delete the leading zeros from the modulus.
     *
     * @param a modulus
     * @return modulus
     */
    private byte[] stripLeadingZeros(byte[] a) {
        int lastZero = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                lastZero = i;
            } else {
                break;
            }
        }
        lastZero++;
        byte[] result = new byte[a.length - lastZero];
        System.arraycopy(a, lastZero, result, 0, result.length);
        return result;
    }
}
