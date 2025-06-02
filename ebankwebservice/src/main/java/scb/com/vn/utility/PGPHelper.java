package scb.com.vn.utility;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;

import java.io.*;
import java.security.*;
import java.util.Date;
import java.util.Iterator;
// Bouncy castle imports

/**
 *
 * @author vu
 */
public class PGPHelper {

    private static final int BUFFER_SIZE = 1 << 16; // should always be power of 2(one shifted bitwise 16 places)
    private static PGPHelper instance;
    private PGPPublicKey publicKey;
    private PGPSecretKeyRingCollection pgpSec;
    private PGPSecretKey secretKey;
    private char[] password;

    static {
        try {
            Security.addProvider(new BouncyCastleProvider());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private PGPHelper(String publicKeyPath, String privateKeyPath, String password) throws Exception {
        FileInputStream pubStream = new FileInputStream(new File(publicKeyPath));
        FileInputStream priStream = new FileInputStream(new File(privateKeyPath));
        try {
            this.publicKey = readPublicKey(pubStream);
            this.pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(priStream));
            this.secretKey = readSecretKey(pgpSec);
            this.password = password.toCharArray();
        } catch (IOException | PGPException | NoSuchProviderException ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
            try {
                pubStream.close();
                priStream.close();
            } catch (IOException ex) {
                // NOOP
            }
        }
    }

    /**
     *
     * @param privateKeyPath
     * @param publicKeyPath
     * @param password
     * @throws Exception
     */
    public static void init(String privateKeyPath, String publicKeyPath, String password) throws Exception {
        try {
            Security.addProvider(new BouncyCastleProvider());
            instance = new PGPHelper(publicKeyPath, privateKeyPath, password);
        } catch (Exception ex) {
            Helper.writeLogErrorNonDB(PGPHelper.class, "Loi khoi tao PGP:" + ex.getMessage());
        }
    }

    /**
     *
     * @return
     */
    public static PGPHelper getInstance() {
        return instance;
    }

    /**
     *
     * @param encryptData
     * @param decryptData
     * @throws Exception
     */
    public void decryptAndVerifySignature(byte[] encryptData, OutputStream decryptData) throws Exception {
        try {
            InputStream bais = new ByteArrayInputStream(encryptData);
            bais = PGPUtil.getDecoderStream(bais);
            PGPObjectFactory objectFactory = new PGPObjectFactory(bais);
            Object firstObject = objectFactory.nextObject();
            PGPEncryptedDataList dataList = (PGPEncryptedDataList) (firstObject instanceof PGPEncryptedDataList ? firstObject : objectFactory.nextObject());
            Iterator it = dataList.getEncryptedDataObjects();
            PGPPrivateKey privateKey = null;
            PGPPublicKeyEncryptedData encryptedData = null;
            while (privateKey == null && it.hasNext()) {
                encryptedData = (PGPPublicKeyEncryptedData) it.next();
                privateKey = findSecretKey(encryptedData.getKeyID());
            }
            if (encryptedData == null || privateKey == null) {
                throw new IllegalArgumentException("secret key for message not found.");
            }
            InputStream clear = encryptedData.getDataStream(privateKey, "BC");
            PGPObjectFactory clearObjectFactory = new PGPObjectFactory(clear);
            Object message = clearObjectFactory.nextObject();

            if (message instanceof PGPCompressedData) {
                PGPCompressedData cData = (PGPCompressedData) message;
                objectFactory = new PGPObjectFactory(cData.getDataStream());
                message = objectFactory.nextObject();
            }

            PGPOnePassSignature calculatedSignature = null;
            if (message instanceof PGPOnePassSignatureList) {
                calculatedSignature = ((PGPOnePassSignatureList) message).get(0);
                calculatedSignature.initVerify(publicKey, "BC");
                message = objectFactory.nextObject();
            }

            if (message instanceof PGPLiteralData) {
                PGPLiteralData ld = (PGPLiteralData) message;
                InputStream literalDataStream = ld.getInputStream();
                int ch;
                while ((ch = literalDataStream.read()) >= 0) {
                    if (calculatedSignature != null) {
                        calculatedSignature.update((byte) ch);
                    }
                    decryptData.write((byte) ch);
                }
            } else if (message instanceof PGPOnePassSignatureList) {
                throw new PGPException("encrypted message contains a signed message - not literal data.");
            } else {
                throw new PGPException("message is not a simple encrypted file - type unknown.");
            }

            if (calculatedSignature != null) {
                PGPSignatureList signatureList = (PGPSignatureList) objectFactory.nextObject();
                PGPSignature messageSignature = (PGPSignature) signatureList.get(0);
                if (!calculatedSignature.verify(messageSignature)) {
                    throw new PGPException("signature verification failed");
                }
            }

            if (encryptedData.isIntegrityProtected()) {
                if (!encryptedData.verify()) {
                    throw new PGPException("message failed integrity check");
                }
            }
        } catch (IOException | IllegalArgumentException | NoSuchProviderException | PGPException | SignatureException ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param encryptData
     * @return
     * @throws Exception
     */
    public String decrypt(byte[] encryptData) throws Exception {
        InputStream bais = new ByteArrayInputStream(encryptData);
        bais = PGPUtil.getDecoderStream(bais);
        PGPObjectFactory pgpF = new PGPObjectFactory(bais);
        PGPEncryptedDataList enc;
        Object o = pgpF.nextObject();
        if (o instanceof PGPEncryptedDataList) {
            enc = (PGPEncryptedDataList) o;
        } else {
            enc = (PGPEncryptedDataList) pgpF.nextObject();
        }
        Iterator it = enc.getEncryptedDataObjects();
        PGPPrivateKey privateKey = null;
        PGPPublicKeyEncryptedData encryptedData = null;
        while (privateKey == null && it.hasNext()) {
            encryptedData = (PGPPublicKeyEncryptedData) it.next();
            privateKey = findSecretKey(encryptedData.getKeyID());
        }
        if (privateKey == null || encryptedData == null) {
            throw new IllegalArgumentException("secret key for message not found.");
        }
        InputStream clear = encryptedData.getDataStream(privateKey, "BC");
        PGPObjectFactory plainFact = new PGPObjectFactory(clear);
        Object message = plainFact.nextObject();
        if (message instanceof PGPCompressedData) {
            PGPCompressedData cData = (PGPCompressedData) message;
            PGPObjectFactory pgpFact = new PGPObjectFactory(cData.getDataStream());
            message = pgpFact.nextObject();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (message instanceof PGPLiteralData) {
            PGPLiteralData ld = (PGPLiteralData) message;
            InputStream unc = ld.getInputStream();
            int ch;
            while ((ch = unc.read()) >= 0) {
                baos.write(ch);
            }
        } else if (message instanceof PGPOnePassSignatureList) {
            throw new PGPException("encrypted message contains a signed message - not literal data.");
        } else {
            throw new PGPException("message is not a simple encrypted file - type unknown.");
        }
        return new String(baos.toByteArray());
    }

    /**
     *
     * @param data
     * @return
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws PGPException
     */
    public byte[] encrypt(byte[] data) throws IOException, NoSuchProviderException, PGPException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        encrypt(data, baos);
        return baos.toByteArray();

    }

    private static PGPPublicKey readPublicKey(InputStream in) throws IOException, PGPException {
        in = PGPUtil.getDecoderStream(in);
        PGPPublicKeyRingCollection pkCol = new PGPPublicKeyRingCollection(in);
        PGPPublicKeyRing pkRing;
        Iterator it = pkCol.getKeyRings();
        while (it.hasNext()) {
            pkRing = (PGPPublicKeyRing) it.next();
            Iterator pkIt = pkRing.getPublicKeys();
            while (pkIt.hasNext()) {
                PGPPublicKey key = (PGPPublicKey) pkIt.next();
                if (key.isEncryptionKey()) {
                    return key;
                }
            }
        }
        throw new PGPException("Invalid public Key");
    }

    /**
     *
     * @param data
     * @param out
     * @throws Exception
     */
    public void encryptAndSign(byte[] data, OutputStream out) throws Exception {
        try {
            out = new ArmoredOutputStream(out);
            PGPEncryptedDataGenerator encryptedDataGenerator = new PGPEncryptedDataGenerator(PGPEncryptedDataGenerator.CAST5, new SecureRandom(), "BC");
            encryptedDataGenerator.addMethod(publicKey);
            PGPCompressedDataGenerator comData = null;
            try (OutputStream encryptedOut = encryptedDataGenerator.open(out, new byte[BUFFER_SIZE])) {
                comData = new PGPCompressedDataGenerator(PGPCompressedDataGenerator.ZIP);
                try (OutputStream compressedOut = comData.open(encryptedOut)) {
                    PGPSignatureGenerator signatureGenerator = createSignatureGenerator();
                    signatureGenerator.generateOnePassVersion(false).encode(compressedOut);
                    writeToLiteralData(signatureGenerator, compressedOut, data);
                    signatureGenerator.generate().encode(compressedOut);
                }
            } finally {
                if (comData != null) {
                    try {
                        comData.close();
                    } catch (IOException ex) {
                        //NO OP
                    }
                }
                try {
                    encryptedDataGenerator.close();
                } catch (IOException ex) {
                    //NO OP
                }
                close(out);
            }
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | PGPException | SignatureException ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }

    private static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
            closeable = null;
        } catch (Exception ex) {
            // NOOP
        }
    }

    private PGPSignatureGenerator createSignatureGenerator() throws NoSuchProviderException, NoSuchAlgorithmException, PGPException {
        PGPPrivateKey pgpPrivKey = secretKey.extractPrivateKey(password, "BC");
        PGPPublicKey internalPublicKey = secretKey.getPublicKey();
        PGPSignatureGenerator signatureGenerator = new PGPSignatureGenerator(internalPublicKey.getAlgorithm(), HashAlgorithmTags.SHA1, "BC");
        signatureGenerator.initSign(PGPSignature.BINARY_DOCUMENT, pgpPrivKey);
        for (Iterator i = internalPublicKey.getUserIDs(); i.hasNext();) {
            String userId = (String) i.next();
            PGPSignatureSubpacketGenerator spGen = new PGPSignatureSubpacketGenerator();
            spGen.setSignerUserID(false, userId);
            signatureGenerator.setHashedSubpackets(spGen.generate());
            break;
        }
        return signatureGenerator;
    }

    private void encrypt(byte[] data, OutputStream out) throws IOException, NoSuchProviderException, PGPException {
        out = new DataOutputStream(out);
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        PGPCompressedDataGenerator comData = null;
        try {
            comData = new PGPCompressedDataGenerator(PGPCompressedDataGenerator.ZIP);
            writeToLiteralData(comData.open(bOut), PGPLiteralData.BINARY, data);
        } finally {
            if (comData != null) {
                comData.close();
            }
        }
        PGPEncryptedDataGenerator cPk = new PGPEncryptedDataGenerator(PGPEncryptedDataGenerator.CAST5, new SecureRandom(), "BC");
        cPk.addMethod(publicKey);
        byte[] bytes = bOut.toByteArray();
        try (OutputStream cOut = cPk.open(out, bytes.length)) {
            cOut.write(bytes);
        }

    }

    private PGPPrivateKey findSecretKey(long keyID) throws IOException, PGPException, NoSuchProviderException {
        PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);
        if (pgpSecKey == null) {
            return null;
        }
        return pgpSecKey.extractPrivateKey(password, "BC");
    }

    /**
     *
     * @param out
     * @param fileType
     * @param data
     * @throws IOException
     */
    public static void writeToLiteralData(OutputStream out, char fileType, byte[] data) throws IOException {
        PGPLiteralDataGenerator lData = new PGPLiteralDataGenerator();
        OutputStream pOut = lData.open(out, fileType, "temp", data.length, new Date());
        pOut.write(data);
    }

    private static void writeToLiteralData(PGPSignatureGenerator signatureGenerator, OutputStream out, byte[] data) throws IOException, SignatureException {
        PGPLiteralDataGenerator lData = new PGPLiteralDataGenerator();
        ByteArrayInputStream contentStream = new ByteArrayInputStream(data);
        try (OutputStream literalOut = lData.open(out, PGPLiteralData.BINARY, "pgp", new Date(), new byte[BUFFER_SIZE])) {
            byte[] buf = new byte[BUFFER_SIZE];
            int len;
            while ((len = contentStream.read(buf, 0, buf.length)) > 0) {
                literalOut.write(buf, 0, len);
                signatureGenerator.update(buf, 0, len);
            }
        } finally {
            lData.close();
        }
    }

    private PGPSecretKey readSecretKey(PGPSecretKeyRingCollection collection) throws IOException, PGPException, NoSuchProviderException {
        Iterator it = collection.getKeyRings();
        PGPSecretKeyRing pbr;
        while (it.hasNext()) {
            Object readData = it.next();
            if (readData instanceof PGPSecretKeyRing) {
                pbr = (PGPSecretKeyRing) readData;
                return pbr.getSecretKey();
            }
        }
        throw new IllegalArgumentException("secret key for message not found.");
    }

}
