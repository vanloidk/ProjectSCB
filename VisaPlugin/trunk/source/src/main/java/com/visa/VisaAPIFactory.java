/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.util.IOUtils;
import com.visa.utils.MethodTypes;
import com.visa.utils.MessageType;
import com.visa.utils.Property;
import com.visa.utils.Utils;
import com.visa.utils.VisaAPIClient;
import com.visa.utils.VisaProperties;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import java.util.HashMap;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import scb.com.vn.common.model.mvisa.RequestMessage;
import scb.com.vn.common.model.mvisa.ResponseMessage;
import java.util.logging.Level;

/**
 *
 * @author minhndb
 */
public class VisaAPIFactory implements IVisaFactory
{   
    private static final Logger logger = Logger.getLogger(VisaAPIFactory.class.getName());
    public VisaAPIClient visaAPIClient = new VisaAPIClient();
    private static final String MLE_CLIENT_PRIVATE_KEY_PATH = VisaProperties.getProperty(Property.MLE_CLIENT_PRIVATE_KEY_PATH);

    @Override
    public ResponseMessage execute(RequestMessage request, String testInfo, MessageType messageType) throws Exception
    {
        ResponseMessage result;
        Gson g = new Gson();
        HashMap<String, String> hashMap = new HashMap<>();
        String jsonMessage = request.exportToJsonString();
//        logger.info("Request json before encrypt by MLE: " + jsonMessage);
//        logger.info("SERVER_CERT_PATH: " + VisaProperties.getProperty(Property.SERVER_CERT_PATH));
//        logger.info("KEY_MLE_ID: " + VisaProperties.getProperty(Property.KEY_MLE_ID));
        jsonMessage = getEncryptedPayload(VisaProperties.getProperty(Property.SERVER_CERT_PATH), jsonMessage, VisaProperties.getProperty(Property.KEY_MLE_ID));
        
//        logger.info("Request after encrypt by MLE: " + jsonMessage);
        String resourcePath = Utils.buildResourcePath(messageType);
        if (jsonMessage.isEmpty() || testInfo.isEmpty() || resourcePath.isEmpty())
        {
            result = new ResponseMessage();
            result.setError("-99", "jsonMessage.isEmpty() || testInfo.isEmpty() || resourcePath.isEmpty()"
                    , "testInfo = [" + testInfo + "], resourcePath = [" + resourcePath + "]"
                    , "High", "Invalid data");
            return result;
        }

        CloseableHttpResponse response = visaAPIClient.doMutualAuthRequest(resourcePath, testInfo, jsonMessage, MethodTypes.POST, hashMap);

        String responseMessage = Utils.getResponseMessage(response);
        EncryptedResponse encryptedResponse = new ObjectMapper().readValue(responseMessage, EncryptedResponse.class);
//        logger.info("RESPONSE before decrypt by MLE = [" + encryptedResponse.getEncData() + "]");
        String decryptedResponse = getDecryptedPayload(MLE_CLIENT_PRIVATE_KEY_PATH, encryptedResponse);
//        logger.info("RESPONSE after decrypt by MLE = [" + decryptedResponse + "]");
        response.close();
        
        result = g.fromJson(decryptedResponse, ResponseMessage.class);

        result.setResponseMessage(response.toString());
        
        return result;
        
        
    }
    /**
     * @param mleServerPublicCertificatePath - MLE Server Public Certificate Path
     * @param requestPayload                 - Request Payload
     * @param keyId                          - Key ID
     * @return
     * @throws CertificateException - {@link CertificateException}
     * @throws JOSEException        - {@link JOSEException}
     * @throws IOException          - {@link IOException}
     */
    public static String getEncryptedPayload(String mleServerPublicCertificatePath, String requestPayload, String keyId) throws CertificateException, JOSEException, IOException {
        JWEHeader.Builder headerBuilder = new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A128GCM);
        headerBuilder.keyID(keyId);
        headerBuilder.customParam("iat", System.currentTimeMillis());
        JWEObject jweObject = new JWEObject(headerBuilder.build(), new Payload(requestPayload));
        jweObject.encrypt(new RSAEncrypter(getRSAPublicKey(mleServerPublicCertificatePath)));
        return "{\"encData\":\"" + jweObject.serialize() + "\"}";
    }
    /**
     * Converts PEM file content to RSAPublicKey
     *
     * @param mleServerPublicCertificatePath - MLE Server Public Certificate Path
     * @return RSA Public Key       - {@link PrivateKey}
     * @throws CertificateException - {@link IOException}
     * @throws IOException          - {@link IOException}
     */
    private static RSAPublicKey getRSAPublicKey(final String mleServerPublicCertificatePath) throws CertificateException, IOException {
        final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
        final String END_CERT = "-----END CERTIFICATE-----";
        final String pemEncodedPublicKey = IOUtils.readFileToString(new File(mleServerPublicCertificatePath), StandardCharsets.UTF_8);
        final com.nimbusds.jose.util.Base64 base64 = new com.nimbusds.jose.util.Base64(pemEncodedPublicKey.replaceAll(BEGIN_CERT, "").replaceAll(END_CERT, ""));
        final Certificate cf = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(base64.decode()));
        return (RSAPublicKey) cf.getPublicKey();
    }
    /**
     * Format Local Date Time to yyyy-MM-dd'T'HH:mm:ss
     *
     * @return String
     */
    private static String getLocalTransactionDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    /**
     * Encrypted Response Object
     */
    public static class EncryptedResponse {

        String encData;

        public String getEncData() {
            return encData;
        }

    }

    /**
     * Decrypt response payload
     *
     * @param mleClientPrivateKeyPath - MLE Client Private Key Path
     * @param encryptedPayload        - Encrypted Response Payload
     * @return Decrypted Response
     * @throws ParseException           - {@link ParseException}
     * @throws NoSuchAlgorithmException - {@link NoSuchAlgorithmException}
     * @throws IOException              - {@link IOException}
     * @throws InvalidKeySpecException  - {@link InvalidKeySpecException}
     * @throws JOSEException            - {@link JOSEException}
     */
    public static String getDecryptedPayload(String mleClientPrivateKeyPath, EncryptedResponse encryptedPayload) throws ParseException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, JOSEException {
        String response = encryptedPayload.getEncData();
        JWEObject jweObject = JWEObject.parse(response);
        PrivateKey privateKey = getRSAPrivateKey(mleClientPrivateKeyPath);
        jweObject.decrypt(new RSADecrypter(privateKey));
        response = jweObject.getPayload().toString();
        return response;
    }

    /**
     * Converts PEM file content to PrivateKey
     *
     * @param mleClientPrivateKeyPath - MLE Client Private Key Path
     * @return Private Key              - {@link PrivateKey}
     * @throws IOException              - {@link IOException}
     * @throws NoSuchAlgorithmException - {@link NoSuchAlgorithmException}
     * @throws InvalidKeySpecException  - {@link InvalidKeySpecException}
     */
    private static PrivateKey getRSAPrivateKey(String mleClientPrivateKeyPath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        final String BEGIN_RSA_PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----";
        final String END_RSA_PRIVATE_KEY = "-----END RSA PRIVATE KEY-----";

        final String pemEncodedKey = IOUtils.readFileToString(new File(mleClientPrivateKeyPath), StandardCharsets.UTF_8);
        final com.nimbusds.jose.util.Base64 base64 = new com.nimbusds.jose.util.Base64(pemEncodedKey.replaceAll(BEGIN_RSA_PRIVATE_KEY, "").replaceAll(END_RSA_PRIVATE_KEY, ""));
        final ASN1Sequence primitive = (ASN1Sequence) ASN1Sequence.fromByteArray(base64.decode());
        final Enumeration<?> e = primitive.getObjects();
        final BigInteger v = ((ASN1Integer) e.nextElement()).getValue();
        int version = v.intValue();
        if (version != 0 && version != 1) {
            throw new IllegalArgumentException("wrong version for RSA private key");
        }
        final BigInteger modulus = ((ASN1Integer) e.nextElement()).getValue();
        ((ASN1Integer) e.nextElement()).getValue();
        BigInteger privateExponent = ((ASN1Integer) e.nextElement()).getValue();
        ((ASN1Integer) e.nextElement()).getValue();
        ((ASN1Integer) e.nextElement()).getValue();
        ((ASN1Integer) e.nextElement()).getValue();
        ((ASN1Integer) e.nextElement()).getValue();
        ((ASN1Integer) e.nextElement()).getValue();
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(modulus, privateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(privateKeySpec);
    }
}