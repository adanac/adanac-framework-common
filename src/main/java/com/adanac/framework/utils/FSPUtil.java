package com.adanac.framework.utils;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FSPUtil {
	private static final Logger logger = LoggerFactory.getLogger(FSPUtil.class);

	/**
	 * md5 加密生成验签
	 * 
	 * @param paramsDto
	 * @return
	 */
	public static String md5EncryptSign(String encryptString, String md5Key) {
		if (null == encryptString || null == md5Key) {
			logger.error("调用验签，待加密字符串或密钥为null,加密终止!");
			return null;
		}
		StringBuffer waitEncryptStr = new StringBuffer();
		waitEncryptStr.append(sortParameter(encryptString, "&"));
		waitEncryptStr.append(md5Key);
		return MD5.encode(waitEncryptStr.toString());
	}

	/**
	 * 请求参数排序后加密
	 * 
	 * @param parameter 字符串
	 * @param split 分割符:例如:&
	 * @return 自然排序好的字符串
	 */
	public static String sortParameter(String parameter, String split) {
		String[] parameters = parameter.split(split);
		Arrays.sort(parameters);
		return StringUtils.join(parameters, split);
	}
}
