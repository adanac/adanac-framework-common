package com.adanac.framework.utils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONObject;

/**
 * Json工具类
 * @author adanac
 * @version 1.0
 */
public class JsonUtils {
	/**
	 * 将bean转化成json
	 * 
	 * @param bean
	 * @return
	 */
	public static String bean2json(Object bean) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(bean);
	}

	/**
	 * 将json串转化成bean对象
	 * 
	 * @param json json传
	 * @param type bean对象的class
	 * @return 转化的bean对象
	 */
	public static <T> T json2bean(String json, Type type) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.fromJson(json, type);
	}

	/**
	 * 将json串转化成bean对象list 如学生Student类的json=[{"id":1,"name":"李坤"},{"id":2,"name":\"曹贵生\"}]，
	 * 则type传入  new TypeToken<List<Student>>(){}.getType()
	 * 
	 * @param json json传
	 * @param type 类型
	 * @return 转化后的对象list
	 */
	public static <T> List<T> json2beanlist(String json, Type type) {
		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}

	/**
	 * 返回map
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<String, String> getMapFromJson(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Set<?> keySet = jsonObject.keySet();
		Map<String, String> resultMap = new HashMap<String, String>(keySet.size());
		Iterator<?> iter = keySet.iterator();
		while (iter.hasNext()) {
			String key = iter.next() + "";
			resultMap.put(key, jsonObject.get(key) + "");
		}

		return resultMap;
	}
}
