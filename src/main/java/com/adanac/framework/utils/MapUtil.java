package com.adanac.framework.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class MapUtil {
	public static Map<String, Object> mapIfNull(Map<String, Object> map) {
		if (map == null) {
			return new HashMap<String, Object>();
		}
		return map;
	}

	public static Map<String, Object> splitParam(String paramStr, String collectionName) {
		if (StringUtils.isEmpty(paramStr)) {
			return new HashMap<String, Object>();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		String[] sceneIds = paramStr.split(",");
		for (int i = 0; i < sceneIds.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(":var" + i);
			params.put("var" + i, sceneIds[i]);
		}
		params.put(collectionName, sb.toString());

		return params;
	}

	/**
	 * 
	 * 功能描述：map 转转成 list
	 * 输入参数：<按照参数定义顺序> 
	 * @param 参数说明
	 * 返回值:  类型 <说明> 
	 * @return 返回值
	 * @throw 异常描述
	 * @see 需要参见的其它内容
	 */
	@SuppressWarnings("unchecked")
	public static List<String> MapToList(String key, Map<String, Object> map) {
		Object obj = map.get(key);
		List<String> result = new ArrayList<String>();

		if (null != obj) {
			if (obj instanceof List) {
				result = (List<String>) obj;
			} else {
				result.add(obj.toString());
			}
		}

		return result;
	}
}
