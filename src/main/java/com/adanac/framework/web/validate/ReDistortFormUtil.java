package com.adanac.framework.web.validate;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ReDistortFormUtil {
	/**
	 * 
	 * 功能描述：Judge FORM 是否篡改过 输入参数：Request
	 * @param 参数说明 返回值: 类型 <说明>
	 * @return 返回值
	 * @throws NoSuchAlgorithmException
	 * @throw 异常描述
	 * @see 需要参见的其它内容
	 */
	public static boolean isDistorToken(HttpServletRequest request) {
		String antiDistorTokenName = request.getParameter("antiDistorTokenName");
		if (null == antiDistorTokenName || antiDistorTokenName.trim().equals("")) {
			return true;
		}
		// 获取表单中的防篡改的所有对象的属性名，然后让人到filedsNames中
		String filedsName = request.getParameter(antiDistorTokenName + "Fileds");
		List<String> filedsNames = Arrays.asList(filedsName.split(","));

		// 获取表单中的防篡改的所有对象的属性名与值，经过加密算法后，放在页面隐藏域中的值
		String value = request.getParameter(antiDistorTokenName + "Value");

		Map<String, String> valueMap = new LinkedHashMap<String, String>();

		for (String fieldName : filedsNames) {
			String filedValue = (String) request.getParameter(fieldName);
			valueMap.put(fieldName, filedValue);
		}
		valueMap.put("suning", antiDistorTokenName);
		// 获取目前表单中的防篡改的所有对象的属性名与值，并执行同样的加密算法
		String mdValue = reDistort(valueMap);

		// 比较针对页面两次加密算法后的值，一样则没有篡改
		if (value.equals(mdValue)) {
			return false;
		}

		return true;
	}

	// 将表单中的防篡改的所有对象的属性名与值组成的hashmap进行加密算法
	public static String reDistort(Map<String, String> valueMap) {
		return String.valueOf(Math.abs(valueMap.hashCode() + valueMap.size()) * 89 / 7);
	}
}
