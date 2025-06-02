/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.utility.fcdb;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.log4j.Logger;
import scb.com.vn.dbi.bo.BdobdxBO;

/**
 *
 * @author lydty
 */
public class GenOBDXPassword {

    private static final Logger LOGGER = Logger.getLogger(GenOBDXPassword.class);

    private static final String HASHING_ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final int ITERATIONS = 10000;
    private static final int KEYLENGTH = 512;
    private static final String PEPPER = "DFasdfjkhtyurQ*60PTUredcrsnk12@93p";

    /**
     *
     * @param password
     * @param username
     * @return
     */
    public static String generateHash(String password, String username) {
        password = password + PEPPER;
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(HASHING_ALGORITHM);
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), username.getBytes(), ITERATIONS, KEYLENGTH);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return toHex(res);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.error(e);
        }
        return null;
    }

    /**
     *
     * @param array
     * @return
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = array.length * 2 - hex.length();
        if (paddingLength > 0) {
            return String.format(new StringBuilder().append("%0").append(paddingLength).append("d").toString(),
                    new Object[]{0}) + hex;
        }
        return hex;
    }

    /**
     *
     * @param username
     * @param newpwd
     * @param channel
     * @return
     * @throws Exception
     */
    public int resetPass(String username, String newpwd, String channel) throws java.lang.Exception {
        try {
            BdobdxBO BO = new BdobdxBO();
            ArrayList user = BO.getDetailUserEB(username);
            if (user.isEmpty()) {
                return 0;
            }
            String genPass = GenOBDXPassword.generateHash(newpwd, username);
            //Update pass
            int result = BO.resetPass(username, genPass, channel);
            if (result == 1) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            return -1;
        }
    }
}
