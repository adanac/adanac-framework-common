package com.adanac.framework.web.timer;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class DESUtil {
	public static final String ALGORITHM = "DES";
	public static final String FOMART = "RAW";
	public static String SYSTEMKEY;
	public static String SYSTEMCODE;
	public static String CALLBACKROOT;

	static {
		ResourceBundle rb = ResourceBundle.getBundle("autho");
		SYSTEMKEY = rb.getString("SystemKey");
		SYSTEMCODE = rb.getString("SystemCode");
		CALLBACKROOT = rb.getString("ContextRoot");
	}

	public static String genDESKey() {
		String keyStr = null;
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
			SecretKey key = keyGen.generateKey();
			keyStr = String.valueOf(byte2hex(key.getEncoded()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return keyStr;

	}

	public static String byte2hex(byte[] bit) {

		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < bit.length; n++) {
			stmp = (java.lang.Integer.toHexString(bit[n] & 0xFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	public static byte[] hex2byte(String str) {
		if (str == null) {
			return null;
		}
		str = str.trim();
		int len = str.length();
		byte[] bit = new byte[len / 2];
		for (int i = 0; i < str.length(); i += 2) {
			bit[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
		}
		return bit;
	}

	public static String encode(String str, final String keyStr) {
		String encodeStr = null;
		SecretKey key = new SecretKey() {
			private static final long serialVersionUID = 1L;

			public String getAlgorithm() {
				return ALGORITHM;
			}

			public byte[] getEncoded() {
				return hex2byte(keyStr);
			}

			public String getFormat() {
				return FOMART;
			}

		};
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] cipherByte = c.doFinal((str.getBytes()));
			encodeStr = byte2hex(cipherByte);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (InvalidKeyException e) {

			e.printStackTrace();
		} catch (IllegalStateException e) {

			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();
		} catch (BadPaddingException e) {

			e.printStackTrace();
		}

		return encodeStr;
	}

	public static String decode(String str, final String keyStr) {
		String decodeStr = null;
		SecretKey key = new SecretKey() {
			private static final long serialVersionUID = 1L;

			public String getAlgorithm() {
				return ALGORITHM;
			}

			public byte[] getEncoded() {
				return hex2byte(keyStr);
			}

			public String getFormat() {
				return FOMART;
			}

		};

		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] cipherByte = c.doFinal(hex2byte(str));
			decodeStr = new String(cipherByte);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (InvalidKeyException e) {

			e.printStackTrace();
		} catch (IllegalStateException e) {

			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();
		} catch (BadPaddingException e) {

			e.printStackTrace();
		}

		return decodeStr;
	}

	public static void main(String arv[]) {
		System.out.println(DESUtil.SYSTEMCODE);
		System.out.println(DESUtil.SYSTEMKEY);
		System.out.println(DESUtil.encode(DESUtil.SYSTEMCODE, DESUtil.SYSTEMKEY));
		System.out.println(byte2hex("1f".getBytes()));

	}
}
