package scb.com.vn.dbi.utility.fcdb;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.bo.BdobdxBO;

/**
 *
 * @author phucnnd
 */
public class PasswordDigest {

    private static final Logger LOGGER = Logger.getLogger(PasswordDigest.class);

    /**
     *
     * @param username
     * @param pwd
     * @return
     * @throws Exception
     */
    public boolean checkPass(String username, String pwd) throws java.lang.Exception {
        try {
            boolean isAuthSuccess;
            BdobdxBO BO = new BdobdxBO();
            ArrayList user = BO.getDetailUserEB(username);
            if (user.isEmpty()) {
                return false;
            }
            HashMap<?, ?> HMUser = (HashMap<?, ?>) user.get(0);
            String isfistlogin = HMUser.get("isfirsttimelogin").toString();
            String u_password = HMUser.get("u_password").toString();

            //F: fcdb;M: mobile
            String channel = HMUser.get("migratedfrom") == null ? "N" : HMUser.get("migratedfrom").toString();
            if (!channel.equals("N")) {
                if (isfistlogin.equals("Y")) {
                    String id_user = HMUser.get("user_id_fcdb").toString();
                    isAuthSuccess = checkAuthenPass("B001", "01", id_user, pwd, u_password);
                } else {
                    isAuthSuccess = u_password.equals(GenOBDXPassword.generateHash(pwd, username));
                }
            } else {
                isAuthSuccess = u_password.equals(GenOBDXPassword.generateHash(pwd, username));
            }
            return isAuthSuccess;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return false;
        }
    }

    /**
     *
     * @param idEntity
     * @param idChannel
     * @param idUser
     * @param passInput
     * @param passData
     * @return
     * @throws Exception
     */
    public boolean checkAuthenPass(String idEntity, String idChannel, String idUser, String passInput, String passData) throws Exception {
        String passwordData = passData;
        byte[] l_p = JFBase64.base64ToByteArray(passwordData);

        JFCompressor compressor = new JFCompressor();
        String p_xml = compressor.uncompress(l_p);
        String passData_HMAC = p_xml.substring("<PasswordEncryptionData><password>".length(), p_xml.length() - "</password><passwordLength>0</passwordLength></PasswordEncryptionData>".length());

        byte[] l_c = JFBase64.base64ToByteArray(passData_HMAC);
        PasswordEncryptionData l_data = new PasswordEncryptionData();
        l_data.idEntity = idEntity;
        l_data.idChannel = idChannel;
        l_data.idUser = idUser;
        l_data.password = passInput;
        l_data.randomKey = l_c;

        byte[] l_b = encryptData(l_data);

        return Arrays.equals(l_b, l_c);
    }

    /**
     *
     * @param idEntity
     * @param idUser
     * @param idChannel
     * @param password
     * @return
     * @throws Exception
     */
    public String getPasswordDigest(String idEntity, String idUser, String idChannel, String password) throws Exception {
        PasswordEncryptionData l_data = new PasswordEncryptionData();
        l_data.idEntity = idEntity;
        l_data.idUser = idUser;
        l_data.idChannel = idChannel;
        l_data.password = password;
        String a = JFBase64.byteArrayToBase64(encryptPassword(l_data));
        return a;
    }

    /**
     *
     * @param p_data
     * @return
     * @throws Exception
     */
    private byte[] encryptPassword(PasswordEncryptionData p_data) throws Exception {
        PasswordEncryptionData l_data = new PasswordEncryptionData();

        l_data.idEntity = p_data.idEntity;
        l_data.idChannel = p_data.idChannel;
        l_data.idUser = p_data.idUser;
        l_data.password = JFBase64.byteArrayToBase64(encryptData(p_data));
        JFCompressor compressor = new JFCompressor();

        String a = "<PasswordEncryptionData><password>" + l_data.password
                + "</password><passwordLength>0</passwordLength></PasswordEncryptionData>";

        return compressor.compress(a);
    }

    /**
     *
     * @param p_data
     * @return
     * @throws Exception
     */
    private byte[] encryptData(PasswordEncryptionData p_data) throws Exception {
        byte[] l_random = new byte[8];
        boolean l_isRandom = false;
        SecureRandom l_r = null;

        if ((p_data.randomKey == null) || (p_data.randomKey.length < 8)) {
            l_isRandom = true;
            l_r = new SecureRandom();
        }

        for (int l_i = 0; l_i < l_random.length; l_i++) {
            l_random[l_i] = (byte) (l_isRandom ? l_r.nextInt() : p_data.randomKey[l_i]);
        }

        String l_key = p_data.idEntity + p_data.idUser;
        byte[] l_bytes = HMacUtil.mergeArray(l_random, l_key.getBytes("UTF-8"));
        return HMacUtil.mergeArray(l_random, HMacUtil.encrypt(l_bytes, p_data.password.getBytes("UTF-8")));
    }

    /**
     * Create a new class of PasswordEncryptionData
     */
    private final class PasswordEncryptionData {

        public transient String idPolicy;
        public transient String idEntity;
        public transient String idUser;
        public transient String idChannelUser;
        public transient String idChannel;
        public String password;
        public String[] pin;
        public int passwordLength;
        public int[] randomPasswordPosition;
        public transient String encryptionKey;
        public transient byte[] randomKey;
        public transient String idCustomer;
        public transient String idSession;
    }
}
