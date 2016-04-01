package com.adanac.framework.statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author adanac
 * @version 1.0
 */
public class VersionStatistics {

	private static final Logger logger = LoggerFactory.getLogger(VersionStatistics.class);

	private static Map<String, String> versionMap = new HashMap<String, String>();

	private static final String COMPONENT_PATH = "component/addComponentInfo.htm";

	private static Timer timer;

	private static String appCode;

	private static String scmServer;

	// 启动后延迟五分钟首次发送数据10*60*1000
	private final static int TIMER_DELAY = 10 * 60 * 1000;
	// 每次发送数据间隔2小时2*60*60*1000
	private final static int TIMER_INTERVAL = 2 * 60 * 60 * 1000;

	@SuppressWarnings("rawtypes")
	public static void reportVersion(String appCode, String scmServer, Class clazz) {
		VersionStatistics.appCode = appCode;
		if (scmServer.endsWith("/")) {
			VersionStatistics.scmServer = scmServer;
		} else {
			VersionStatistics.scmServer = scmServer + "/";
		}
		reportVersion(clazz);
		timer = new Timer("Version info send timer", true);
		timer.schedule(new TimerTask() {
			public void run() {
				sendVersionMap();
			}
		}, TIMER_DELAY, TIMER_INTERVAL);
	}

	public static void destroy() {
		if (timer != null) {
			timer.cancel();
		}
	}

	@SuppressWarnings("rawtypes")
	public static void reportVersion(Class clazz) {
		Package p = clazz.getPackage();
		String componentName = p.getImplementationTitle();
		if (componentName == null) {
			componentName = clazz.getSimpleName();
		}
		String componentVersion = p.getImplementationVersion();
		if (componentVersion == null) {
			componentVersion = "nil";
		}
		versionMap.put(componentName, componentVersion);
	}

	private static void sendVersionMap() {
		String components = mapToJsonStr(versionMap);
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("appCode=").append(appCode);
			sb.append("&components=").append(components);
			HttpClient.getResponseViaPost(scmServer + COMPONENT_PATH, sb.toString(), HttpClient.CONTENT_TYPE_FORM, 5000,
					20000, false);
		} catch (Exception ex) {
			logger.warn("Exception:", ex);
		}
	}

	public static String mapToJsonStr(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return "";
		}
		String jsonStr = "{";
		Set<?> keySet = map.keySet();
		for (Object key : keySet) {
			jsonStr += "\"" + key + "\":\"" + map.get(key) + "\",";
		}
		jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
		jsonStr += "}";
		return jsonStr;
	}
}
