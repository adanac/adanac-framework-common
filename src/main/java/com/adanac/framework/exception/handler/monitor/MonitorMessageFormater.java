package com.adanac.framework.exception.handler.monitor;

import java.util.HashMap;
import java.util.Map;

import com.adanac.framework.exception.handler.ExceptionInfoFormater;
import com.adanac.framework.exception.model.ExceptionInfo;
import com.google.gson.Gson;

public class MonitorMessageFormater implements ExceptionInfoFormater {
	public String getFormatString(ExceptionInfo expInfo) {

		Map<String, String> msgMap = new HashMap<String, String>();
		if (expInfo instanceof MonitorExceptionInfo) {
			MonitorExceptionInfo temp = (MonitorExceptionInfo) expInfo;
			msgMap.put("systemId", temp.getSystemId());
			msgMap.put("appId", temp.getAppId());
			msgMap.put("serverIp", temp.getServerIp());
		}

		String errorMsg = (null == expInfo.getExceptionMessage()) ? "" : expInfo.getExceptionMessage();
		String errorCode = (null == expInfo.getCode()) ? "" : expInfo.getCode();
		msgMap.put("errorMsg", errorMsg);
		msgMap.put("errorCode", errorCode);
		msgMap.put("errorTime", expInfo.getInterceptTime());

		// use Gson to transform ExceptionInfo to JSON String
		Gson gson = new Gson();
		return gson.toJson(msgMap);
	}
}
