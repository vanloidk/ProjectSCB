package scb.com.vn.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.IOUtils;
import com.visa.utils.Property;
import com.visa.utils.VisaProperties;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Enumeration;
import org.apache.commons.lang3.RandomStringUtils;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import scb.com.vn.common.model.mvisa.Address;
import scb.com.vn.common.model.mvisa.CardAcceptor;
import scb.com.vn.common.model.mvisa.PurchaseIdentifier;
import scb.com.vn.common.model.mvisa.RequestMessage;
import static scb.com.vn.utility.Configuration.publicCert;

/**
 *
 * @author minhndb
 */
public class RandomString {

    /**
     * Encrypted Response Object
     */
    public static class EncryptedResponse {

        String encData;

        public String getEncData() {
            return encData;
        }

        public void setEncData(String encData) {
            this.encData = encData;
        }

    }

    /**
     *
     */
    public RandomString() {
    }

    final String AMAZON_HEADER_PREFIX = "x-amz-";
    final String ALTERNATIVE_DATE_HEADER = "x-amz-date";

    private static final String MLE_CLIENT_PRIVATE_KEY_PATH = VisaProperties.getProperty(Property.MLE_CLIENT_PRIVATE_KEY_PATH);

    private static final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
    private static final String END_CERT = "-----END CERTIFICATE-----";
    private static final String BEGIN_RSA_PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----";
    private static final String END_RSA_PRIVATE_KEY = "-----END RSA PRIVATE KEY-----";
    private static final String ENC_DATA = "encData";

    private static final String xmltest
            = "<MVISAQRRQ><LOC4DIGIT>8000010596688306</LOC4DIGIT><REFERENCENO>95</REFERENCENO><MERPRIMARYACCNUM>4110610000000021</MERPRIMARYACCNUM><AMOUNT>1</AMOUNT>"
            + " <LOCALTRANSDATE>2023-12-04T09:57:49</LOCALTRANSDATE>"
            + " <MERCATEGORYCODE>5977</MERCATEGORYCODE>"
            + " <ACQUIRERCOUNTRYCODE>704</ACQUIRERCOUNTRYCODE>"
            + " <TRANSFEEAMT>0</TRANSFEEAMT>"
            + " <TRANSCURRENCYCODE>VND</TRANSCURRENCYCODE>"
            + " <SECONDARYID></SECONDARYID>"
            + " <CARDACCNAME>HKDKIMCHAU</CARDACCNAME>"
            + " <CARDACCTERID></CARDACCTERID>"
            + " <CARDACCIDCODE>QTANPHU</CARDACCIDCODE>"
            + " <CARDACCCITY>QTANPHU</CARDACCCITY>"
            + " <CARDACCSTATE></CARDACCSTATE>"
            + " <CARDACCCOUNTY>VND</CARDACCCOUNTY>"
            + " <CARDACCCOUNTRY>VN</CARDACCCOUNTRY>"
            + " <CARDACCZIPCODE></CARDACCZIPCODE>"
            + " <PITYPE>1</PITYPE>"
            + " <PIREFNUM></PIREFNUM>"
            + " <PROVIDER>VNPAYQR</PROVIDER>"
            + " </MVISAQRRQ>";

    private static final String xmltest2 = "{\"acquirerCountryCode\":\"704\",\"acquiringBin\":\"422051\",\"localTransactionDateTime\":\"2023-12-04T09:57:49\",\"amount\":\"1\",\"businessApplicationId\":\"MP\",\"cardAcceptor\":{\"address\":{\"country\":\"VN\",\"county\":\"\",\"state\":\"\",\"zipCode\":\"\",\"city\":\"QTANPHU\"},\"idCode\":\"QTANPHU\",\"name\":\"HKDKIMCHAU\",\"terminalId\":\"13800106\"},\"merchantCategoryCode\":\"5977\",\"purchaseIdentifier\":{\"referenceNumber\":\"\",\"type\":\"1\"},\"recipientName\":\"HKDKIMCHAU\",\"recipientPrimaryAccountNumber\":\"4110610000000021\",\"retrievalReferenceNumber\":\"333915117989\",\"senderAccountNumber\":\"4895180006998306\",\"senderName\":\"NGUYENVANLOI\",\"senderReference\":\"99\",\"systemsTraceAuditNumber\":\"117989\",\"transactionCurrencyCode\":\"VND\"}";

    private static final String keyID = "7a490a57-3e6e-4733-ac49-844451551cbf";

    public static void main(String[] args) throws CertificateException, JOSEException, IOException, Exception {

        String hh = "Nguyễn Thành Sơn@026750@false@3@W000000036676673%2024-08-31T00:00:00%492261%026750#W000000036197851%2024-07-31T00:00:00%492261%026750#";
        String[] datas = hh.split("@");
        String[] items = datas[4].split("#");
        String msgSendCore = "";
        int cnt = 0;
        for (String item : items) {
            cnt ++;
            String[] values = item.split("%");
            String datepaid = values[1];
            LocalDateTime localDate;
            if (datepaid != null) {
                localDate = LocalDateTime.parse(datepaid);
                msgSendCore = msgSendCore + localDate.getMonthValue() + "/" + localDate.getYear();
                if (cnt < items.length) {
                    msgSendCore = msgSendCore + "-";
                }
            }
            
        }
        
        String vv = msgSendCore;

        String sigature = "MIAGCSqGSIb3DQEHAqCAMIACAQExCzAJBgUrDgMCGgUAMIAGCSqGSIb3DQEHAQAAMYIBWTCCAVUCAQEwVDBHMRswGQYDVQQDExJ3d3cucGF5bGluay5jb20udm4xDTALBgNVBAoTBENOTkIxCzAJBgNVBAYTAlZOMQwwCgYDVQQIEwNIQ00CCQDGAhb+qUyS8TAJBgUrDgMCGgUAoF0wGAYJKoZIhvcNAQkDMQsGCSqGSIb3DQEHATAcBgkqhkiG9w0BCQUxDxcNMjQwMjI3MDQ0NzUwWjAjBgkqhkiG9w0BCQQxFgQUqVi52pdDIw4GQY5k/v/E8n8LUV8wDQYJKoZIhvcNAQEBBQAEgYCcFyDPnvSNNyLPv8wxh7DFeWhGkrPPPLTpHoUJmb3+XgrNP5VdxBj+xta9o1bYFOKhUJykeOoXcuJR7yNTUSqopUETTbXQX8PMMDvJkIyly97paKzvJRaJxU40sYlToDPKc9bhLOGbX/dvpzeZdtGOmrphe/aO0AkDIqvqOB/KCAAAAAAAAA==";
        String content = "{\n"
                + "  \"QueryBillExResult\": {\n"
                + "    \"ReturnCode\": 0,\n"
                + "    \"Bills\": {\n"
                + "      \"BillInfo\": [\n"
                + "        {\n"
                + "          \"BillId\": 404500,\n"
                + "          \"Month\": \"12/2023\",\n"
                + "          \"MoneyAmount\": 156298,\n"
                + "          \"PaymentFee\": 0,\n"
                + "          \"CustomerName\": \"Chiều Hôm\",\n"
                + "          \"Address\": \"Hẻm 2/3/4 Lê\",\n"
                + "          \"ServiceId\": \"NUOC\",\n"
                + "          \"ServiceName\": \"Nước\",\n"
                + "          \"ProviderId\": \"CNXUANMAI\",\n"
                + "          \"ProviderName\": \"Cấp nước Xuân Mai\",\n"
                + "          \"MonthAmount\": \"\",\n"
                + "          \"InfoEx\": \"Hẻm 2/3/4 Lê\",\n"
                + "          \"ShippingDateNum\": \"\",\n"
                + "          \"PaymentRange\": \"1-1\",\n"
                + "          \"IsPrepaid\": false,\n"
                + "          \"ExpiredDate\": \"\",\n"
                + "          \"RenewalDate\": \"\",\n"
                + "          \"OrderNo\": \"\",\n"
                + "          \"ShopName\": \"\",\n"
                + "          \"CustomerId\": \"XM0025254\",\n"
                + "          \"InfoBill\": \"\",\n"
                + "          \"TitleOfMonth\": \"\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"BillId\": 404501,\n"
                + "          \"Month\": \"01/2024\",\n"
                + "          \"MoneyAmount\": 168032,\n"
                + "          \"PaymentFee\": 0,\n"
                + "          \"CustomerName\": \"Chiều Hôm\",\n"
                + "          \"Address\": \"Hẻm 2/3/4 Lê\",\n"
                + "          \"ServiceId\": \"NUOC\",\n"
                + "          \"ServiceName\": \"Nước\",\n"
                + "          \"ProviderId\": \"CNXUANMAI\",\n"
                + "          \"ProviderName\": \"Cấp nước Xuân Mai\",\n"
                + "          \"MonthAmount\": \"\",\n"
                + "          \"InfoEx\": \"Hẻm 2/3/4 Lê\",\n"
                + "          \"ShippingDateNum\": \"\",\n"
                + "          \"PaymentRange\": \"1-1\",\n"
                + "          \"IsPrepaid\": false,\n"
                + "          \"ExpiredDate\": \"\",\n"
                + "          \"RenewalDate\": \"\",\n"
                + "          \"OrderNo\": \"\",\n"
                + "          \"ShopName\": \"\",\n"
                + "          \"CustomerId\": \"XM0025254\",\n"
                + "          \"InfoBill\": \"\",\n"
                + "          \"TitleOfMonth\": \"\"\n"
                + "        }\n"
                + "      ]\n"
                + "    },\n"
                + "    \"Services\": {\n"
                + "      \"Service\": {\n"
                + "        \"ServiceName\": \"Nước\",\n"
                + "        \"ServiceId\": \"NUOC\",\n"
                + "        \"MatchProviderCount\": 0,\n"
                + "        \"Issuers\": {\n"
                + "          \"Issuer\": {\n"
                + "            \"IssuerId\": \"CNXUANMAI\",\n"
                + "            \"IssuerName\": \"Cấp nước Xuân Mai\",\n"
                + "            \"IsOnline\": true\n"
                + "          }\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"VietUnionId\": \"\",\n"
                + "    \"PaymentRule\": 2,\n"
                + "    \"MatchServiceCount\": 0,\n"
                + "    \"IsUseReceivedBill\": false,\n"
                + "    \"ViewOptions\": \"\",\n"
                + "    \"PaymentFeeType\": 0,\n"
                + "    \"PercentFee\": 0,\n"
                + "    \"ConstantFee\": 0,\n"
                + "    \"MinFee\": 0,\n"
                + "    \"MaxFee\": 0\n"
                + "  }\n";

        VerifySignature("aa".getBytes("UTF-8"), sigature);

        /*
        String encrpkk = "eyJjdHkiOiJhcHBsaWNhdGlvbi9qc29uO2NoYXJzZXQ9VVRGLTgiLCJlbmMiOiJBMTI4R0NNIiwiaWF0IjoxNzAyMjYyNTE3NTc1LCJhbGciOiJSU0EtT0FFUC0yNTYifQ.eLcNoKkN4qnWDlAebYIM7EfKBK0hjP0K2vIFP2u7gAuJYlm_7xGtzSFg22VyP04b9icYGVoOmzqYnfQBUKxDPBcPvtyLP0jgwHfhSN13xG7lTL2Gl2uqfaLdUmT4SWWAtd_vW4tUl2u-loqgtwAYDliQmtiw62GBqrgntJWM7BpaMFlf5lKdBGMuoi8ZlsRC0qJGGknQE94hNMLKdvI5Zk6_Pk5scp9NDT6g8RfEQVifuqBP6Q0lkgm4lR4vjZMkaCq_30-oFHR6sIhCYB4y7WQoI2ILvHwiYntQ2kT_xEkTMhkelLwWtXdBDcIIh_11Rg3nUjq4bE2ByEfrIkBGKQ.EcpotPaqx3kmPXwI.mr92qG8eswayclk-NdVppqF5rOyy8Uzm2OGqMNX7TZSZqkPDabbdy0ysEXu1QJzI9gqKw-mdTnMTtdrcCI1wQRbLTaxZd4U-j_cfiFBj4rGd7Sf7PfspBUB3QbD5MWfAWCGd2mE3-Jsy-QPI6jrHc8YTnheIXlCN5UraIO0xfpY._Qh8fI5aUsxExu-igf_yKQ";
        EncryptedResponse encrp = new EncryptedResponse();
        encrp.setEncData(encrpkk);

        String aa = getEncryptedPayload(xmltest2, keyID);

        
        String vv = getDecryptedPayload(null, encrp, null);
        String ran = RandomStringUtils.randomAlphanumeric(20); //Lay chuoi random
        String ranascii = RandomStringUtils.randomAscii(40);
        System.out.println(ran);
        System.out.println(ranascii);
         */
    }

    private static X509Certificate publicCert02;

    public static boolean VerifySignature(byte[] data, String digitalSignature) throws Exception {
        boolean result = false;

        try {
            if (Security.getProvider("BC") == null) {
                Security.addProvider(new BouncyCastleProvider());
            }

            //InputStream inputStream = new FileInputStream(publicCertPathFile);
            //CertificateFactory factory = CertificateFactory.getInstance("X509", "BC");
            //X509Certificate publicCert = (X509Certificate) factory.generateCertificate(inputStream);
            CMSProcessableByteArray digestContent = new CMSProcessableByteArray(data);
            CMSSignedData Signed = new CMSSignedData(digestContent, org.bouncycastle.util.encoders.Base64.decode(digitalSignature));
            SignerInformation Signer = (SignerInformation) Signed.getSignerInfos().getSigners().iterator().next();
            loadPublicCert02("D:\\WILCARD.pem");
            result = Signer.verify(RandomString.publicCert02, "BC");
            //inputStream.close(); //close stream
        } catch (Exception e) {

        }

        return result;
    }

    public static String getEncryptedPayload(Object payload, String keyId) throws CertificateException, JOSEException, IOException {

        Gson g = new Gson();
        RequestMessage message = new RequestMessage();
        message.setAcquirerCountryCode("704");
        message.setAcquiringBin(VisaConstant.ACQUIRINGBIN);
        message.setAmount("1");
        message.setBusinessApplicationId(VisaConstant.BUSINESSAPPID);

        CardAcceptor cardAcceptor = new CardAcceptor();
        cardAcceptor.setIdCode("QTANPHU");
        cardAcceptor.setName("HKDKIMCHAU");
        Address address = new Address();
        address.setCity("QTANPHU");
        address.setState("");
        address.setCountry("VN");
        cardAcceptor.setAddress(address);
        // hieunt16 fix visa
        cardAcceptor.setTerminalId("13800106");
        message.setCardAcceptor(cardAcceptor);
        message.setMerchantCategoryCode("5977");
//            message.setMerchantCategoryCode("6012");
        message.setLocalTransactionDateTime("2023-12-04T09:57:49");
        PurchaseIdentifier purchaseIdentifier = new PurchaseIdentifier();
        purchaseIdentifier.setReferenceNumber("");
        purchaseIdentifier.setType("1");
        message.setPurchaseIdentifier(purchaseIdentifier);
        message.setRecipientName("HKDKIMCHAU");
        message.setRecipientPrimaryAccountNumber("4110610000000021");
        message.setRetrievalReferenceNumber("333915117989");
        message.setSenderAccountNumber("4895180006998306");
        message.setSenderName("NGUYEN VAN LOI");
        message.setSenderReference("99");
        message.setSystemsTraceAuditNumber("117989");
        message.setTransactionCurrencyCode("VND");

        //String jsonMessage = g.toJson(xmltest2);// xmltest2.exportToJsonStrin;
        //String jsonMessage = message.exportToJsonString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        String jsonMessage = payload == null ? "" : mapper.writeValueAsString(message);

        JWEHeader.Builder headerBuilder = new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A128GCM);

        headerBuilder.keyID(keyId);
        headerBuilder.customParam("iat", System.currentTimeMillis());

        JWEObject jweObject = new JWEObject(headerBuilder.build(), new Payload(jsonMessage));
        jweObject.encrypt(new RSAEncrypter(getRSAPublicKey()));
        return "{\"encData\":\"" + jweObject.serialize() + "\"}";
    }

    /*
        * Converts PEM file content to RSAPublicKey
     */
    private static RSAPublicKey getRSAPublicKey() throws CertificateException, IOException {
        String pemEncodedPublicKey = IOUtils.readFileToString(new File("D:\\VISA\\cer\\server_cert_7a490a57-3e6e-4733-ac49-844451551cbf.pem"), Charset.forName("UTF-8"));
        Base64 base64 = new Base64(
                pemEncodedPublicKey.replaceAll(BEGIN_CERT, "").replaceAll(END_CERT, ""));
        Certificate cf = CertificateFactory.getInstance("X.509")
                .generateCertificate(new ByteArrayInputStream(base64.decode()));
        return (RSAPublicKey) cf.getPublicKey();
    }

    public static <T> T getDecryptedPayload(String mleClientPrivateKeyPath, EncryptedResponse encryptedPayload, Class<T> returnType) {

        String response = encryptedPayload.getEncData();
        //String decryptedResponse = "";
        T decryptedResponse = null;
        try {
            Gson g = new Gson();
            JWEObject jweObject = JWEObject.parse(response);
            //If you have used passphrase while generating the csr make sure you the same while getting the private key. Otherwise decryption will fail.
            jweObject.decrypt(new RSADecrypter(getRSAPrivateKey()));
            response = jweObject.getPayload().toString();
            ObjectMapper mapper = new ObjectMapper();
            mapper.readValue(response, returnType); //readValue(response, encryptedPayload);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedResponse;
    }

    /*
        * Converts PEM file content to RSAPrivateKey
     */
    private static PrivateKey getRSAPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        //If you have used passphrase while generating the csr make sure you the same while reading the private key. Otherwise decryption will fail.
        String pemEncodedKey = IOUtils.readFileToString(new File("D:\\VISA\\cer\\private-key_rsa.key"), Charset.forName("UTF-8"));
        com.nimbusds.jose.util.Base64 base64 = new com.nimbusds.jose.util.Base64(pemEncodedKey.replaceAll(BEGIN_RSA_PRIVATE_KEY, "").replaceAll(END_RSA_PRIVATE_KEY, ""));

        ASN1Sequence primitive = (ASN1Sequence) ASN1Sequence.fromByteArray(base64.decode());
        Enumeration<?> e = primitive.getObjects();
        BigInteger v = ((ASN1Integer) e.nextElement()).getValue();
        int version = v.intValue();
        if (version != 0 && version != 1) {
            throw new IllegalArgumentException("wrong version for RSA private key");
        }
        BigInteger modulus = ((ASN1Integer) e.nextElement()).getValue();
        ((ASN1Integer) e.nextElement()).getValue();
        BigInteger privateExponent = ((ASN1Integer) e.nextElement()).getValue();
        ((ASN1Integer) e.nextElement()).getValue();
        ((ASN1Integer) e.nextElement()).getValue();
        ((ASN1Integer) e.nextElement()).getValue();
        ((ASN1Integer) e.nextElement()).getValue();
        ((ASN1Integer) e.nextElement()).getValue();
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(modulus, privateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PrivateKey vv = keyFactory.generatePrivate(privateKeySpec);

        return vv;
    }

    public static void loadPublicCert02(String publicCertPathFile) throws Exception {
        try {
            if (Security.getProvider("BC") == null) {
                Security.addProvider(new BouncyCastleProvider());
            }

            InputStream inputStream = new FileInputStream(publicCertPathFile);
            CertificateFactory factory = CertificateFactory.getInstance("X509", "BC");
            RandomString.publicCert02 = (X509Certificate) factory.generateCertificate(inputStream);
            inputStream.close(); //close stream
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
