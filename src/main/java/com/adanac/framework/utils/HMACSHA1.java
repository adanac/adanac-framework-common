package com.adanac.framework.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HMACSHA1 {
	static Logger log = LoggerFactory.getLogger(HMACSHA1.class);

	private static final String HMAC_SHA1 = "HmacSHA1";

	/**
	 * 生成签名数据
	 * 
	 * @param data 待加密的数据
	 * @param key  加密使用的key
	 * @return 生成MD5编码的字符串 
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSignature(String data, String key) {
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1);
		Mac mac;
		try {
			mac = Mac.getInstance(HMAC_SHA1);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());
			return Base64.encodeBase64URLSafeString(rawHmac);
		} catch (InvalidKeyException e) {
			log.error("ERROR:" + e);
		} catch (NoSuchAlgorithmException e) {
			log.error("ERROR:" + e);
		}
		return null;
	}
}
