package com.adanac.framework.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 加载系统参数 在系统启动时从配置文件中加载系统参数到内存中
 * @author adanac
 * @version 1.0
 */
public class CustomizedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private static Map<String, Object> ctxPropertiesMap = new HashMap<String, Object>();
	private static Logger logger = LoggerFactory.getLogger(CustomizedPropertyPlaceholderConfigurer.class);

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) {
		logger.info("start to onload Properties.xml");
		super.processProperties(beanFactoryToProcess, props);
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
		logger.info("end to onload Properties.xml,the info of map is " + ctxPropertiesMap);
	}

	public String getProperty(String key) {
		Object value = ctxPropertiesMap.get(key);
		if (value != null) {
			return value.toString();
		}
		return null;
	}

	public static Object getContextProperty(String name) {
		return ctxPropertiesMap.get(name);
	}
}