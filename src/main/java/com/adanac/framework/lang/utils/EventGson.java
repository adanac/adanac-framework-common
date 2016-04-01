package com.adanac.framework.lang.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 统一定义事件json串的输出格式
 * @author adanac
 * @version 1.0
 */
public class EventGson {
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private GsonBuilder gsonBuilder = new GsonBuilder();

	private Gson gson;

	public EventGson() {
		gsonBuilder.setDateFormat(DATE_FORMAT);
		gson = gsonBuilder.create();
	}

	public EventGson(ExclusionStrategy excludeStrategy) {
		gsonBuilder.setDateFormat(DATE_FORMAT);
		if (excludeStrategy != null) {
			gsonBuilder.setExclusionStrategies(excludeStrategy);
		}
		gson = gsonBuilder.create();
	}

	public String toJson(Object src) {
		return gson.toJson(src);
	}
}
