/**
 * @author phucnnd
 */
package scb.com.vn.ultility.fcdb;

import java.security.SecureRandom;
import java.util.Arrays;



public class PasswordDigest {

//	public static void main(String arg[]) throws Exception {
//		ChangePassword t = new ChangePassword();
//
//		// idEntity = "B001"; idUser = "2294"; idChannel = "41"; password =
//		// "7654321";
//		System.out.println(t.getPasswordDigest("B001", "2294", "41", "7654321"));
//		
//		//p_request.authType--> 'L'
//		//		       switch (p_request.authType) {
//		//		       case 'L':
//		//		         l_p = JFBase64.base64ToByteArray(l_rs.getString(1));
//		//		         break;
//		//		       case 'A':
//		//		         l_p = JFBase64.base64ToByteArray(l_rs.getString(2));
//		//		         break;
//		//		       case 'T':
//		
//	}

	public boolean checkAuthenPass(String idEntity, String idChannel, String idUser, String passInput, String passData) throws Exception {
		
		byte[] l_p = null;
		String passwordData = passData;
		l_p = JFBase64.base64ToByteArray(passwordData);
		
		JFCompressor compressor = new JFCompressor();
		String p_xml = compressor.uncompress(l_p);
		String passData_HMAC = p_xml.substring("<PasswordEncryptionData><password>".length(), p_xml.length() - "</password><passwordLength>0</passwordLength></PasswordEncryptionData>".length());
		
		
		byte[] l_b = null;
		byte[] l_c = null;
		PasswordEncryptionData l_data = null;				

		l_c = JFBase64.base64ToByteArray(passData_HMAC);
		l_data = new PasswordEncryptionData();
		l_data.idEntity = idEntity;
		// l_data.idChannelUser = p_request.idChannelUser;
		l_data.idChannel = idChannel;
		l_data.idUser = idUser;
		l_data.password = passInput;
		l_data.randomKey = l_c;

		l_b = encryptData(l_data);
                	
		if (Arrays.equals(l_b, l_c))  return true;
		return false;
	}
	
	public String getPasswordDigest(String idEntity, String idUser, String idChannel, String password) throws Exception {
		PasswordEncryptionData l_data = null;
		l_data = new PasswordEncryptionData();
		l_data.idEntity = idEntity;
		l_data.idUser = idUser;
		l_data.idChannel = idChannel;
		l_data.password = password;

		// l_resp.password =
		// JFBase64.byteArrayToBase64(l_dto.passwordEncryptor.encryptPassword(l_data));
		String a = JFBase64.byteArrayToBase64(encryptPassword(l_data));

		return a;
	}

	private byte[] encryptPassword(PasswordEncryptionData p_data) throws Exception {
		PasswordEncryptionData l_data = new PasswordEncryptionData();

		l_data.idEntity = p_data.idEntity;
		l_data.idChannel = p_data.idChannel;
		l_data.idUser = p_data.idUser;
		l_data.password = JFBase64.byteArrayToBase64(encryptData(p_data));
		JFCompressor compressor = new JFCompressor();

		String a = "<PasswordEncryptionData><password>" + l_data.password
				+ "</password><passwordLength>0</passwordLength></PasswordEncryptionData>";
		// return this.compressor.compress(DTOMarshaller.marshall(l_data));

		return compressor.compress(a);
	}

	private byte[] encryptData(PasswordEncryptionData p_data) throws Exception {
		byte[] l_random = new byte[8];
		byte[] l_bytes = null;
		String l_key = null;
		boolean l_isRandom = false;
		SecureRandom l_r = null;

		if ((p_data.randomKey == null) || (p_data.randomKey.length < 8)) {
			l_isRandom = true;
			l_r = new SecureRandom();
		}

		for (int l_i = 0; l_i < l_random.length; l_i++) {
			l_random[l_i] = (byte) (l_isRandom ? l_r.nextInt() : p_data.randomKey[l_i]);
		}

		l_key = p_data.idEntity + p_data.idUser;
		l_bytes = HMacUtil.mergeArray(l_random, l_key.getBytes("UTF-8"));
		return HMacUtil.mergeArray(l_random, HMacUtil.encrypt(l_bytes, p_data.password.getBytes("UTF-8")));
	}

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
		// public SecurityDetailsDTO[] securityDetails;
		public transient String idCustomer;
		public transient String idSession;
		// public transient UserContextDTO userContext;
	}

}
