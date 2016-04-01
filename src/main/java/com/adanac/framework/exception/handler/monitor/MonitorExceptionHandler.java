package com.adanac.framework.exception.handler.monitor;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;

import com.adanac.framework.exception.BaseException;
import com.adanac.framework.exception.event.MonitorExceptionMessageEvent;
import com.adanac.framework.exception.handler.ExceptionHandler;
import com.adanac.framework.exception.handler.ExceptionInfoExtractor;
import com.adanac.framework.exception.handler.ExceptionInfoFormater;
import com.adanac.framework.exception.model.ExceptionInfo;
import com.adanac.framework.exception.model.RawExceptionInfo;
import com.adanac.framework.exception.utils.ApplicationContextHolder;
import com.adanac.framework.exception.utils.NetUtil;

/**
 * 提取并格式化异常信息的监听器
 * @author adanac
 * @version 1.0
 */
public class MonitorExceptionHandler implements InitializingBean, ExceptionHandler, Ordered {
	private int order = Integer.MIN_VALUE + 1; // default: same as non-Ordered
	private final String SYSTEMID_KEY = "monitor.systemid";
	private final String APPID_KEY = "monitor.appid";
	private String systemId;
	private String appId;
	private String serverIp;
	private String configfile = "monitor-api";
	private String[] exceptionCodes;
	private Class[] exceptionTypes;
	private ExceptionInfoExtractor extractor;
	private ExceptionInfoFormater formater;

	public void handle(RawExceptionInfo rawExceptionInfo) {
		if (null == rawExceptionInfo || null == rawExceptionInfo.getThrowable()) {
			return;
		}

		Throwable throwable = rawExceptionInfo.getThrowable();
		boolean monitor = false;
		if (throwable instanceof BaseException) {
			String exCode = ((BaseException) throwable).getCode();
			// 异常code是否是被监控异常code
			if (null != exCode && null != exceptionCodes) {
				for (String errorCode : exceptionCodes) {
					if (exCode.toUpperCase().matches(errorCode) || exCode.toLowerCase().matches(errorCode)) {
						monitor = true;
						break;
					}
				}
			}
		} else if (!(throwable instanceof BaseException) && null != exceptionTypes) {
			// 异常类是否是被监控的异常类型
			Class<? extends Throwable> exClass = throwable.getClass();
			for (Class exceptionClass : exceptionTypes) {
				if (exceptionClass.isAssignableFrom(exClass)) {
					monitor = true;
					break;
				}
			}
		}

		if (monitor) {
			ExceptionInfo exceptionInfo = extractor.extract(rawExceptionInfo);
			MonitorExceptionInfo monitorExceptionInfo = new MonitorExceptionInfo(exceptionInfo, systemId, appId,
					serverIp);
			String monitorMessage = formater.getFormatString(monitorExceptionInfo);

			MonitorExceptionMessageEvent messageEvent = new MonitorExceptionMessageEvent(this, monitorMessage);
			ApplicationContext appContext = ApplicationContextHolder.getApplicationContext();
			appContext.publishEvent(messageEvent);
		}
	}

	/**
	 * 异常信息提取并初始化
	 * {@inheritDoc}
	 */
	public void afterPropertiesSet() throws Exception {
		List<String> ipLst = NetUtil.getAllLocalIP();
		this.serverIp = StringUtils.collectionToDelimitedString(ipLst, ",");

		if (null == extractor) {
			extractor = new DefaultMonitorMessageExtractor();
		}

		if (null == formater) {
			formater = new MonitorMessageFormater();
		}

		if (!StringUtils.hasText(systemId) || !StringUtils.hasText(appId)) {
			Locale locale = Locale.getDefault();
			ResourceBundle confgResource = ResourceBundle.getBundle(configfile, locale);
			systemId = confgResource.getString(SYSTEMID_KEY);
			appId = confgResource.getString(APPID_KEY);
		}
	}

	/**
	 * 
	 * @return order
	 */
	public final void setOrder(int order) {
		this.order = order;
	}

	/**
	 * 
	 * @return order
	 */
	public final int getOrder() {
		return this.order;
	}

	/**
	 * @param exceptionCodes the exceptionCodes to set
	 */
	public void setExceptionCodes(String[] exceptionCodes) {
		this.exceptionCodes = exceptionCodes;
	}

	/**
	 * @param exceptionTypes the exceptionTypes to set
	 */
	public void setExceptionTypes(Class[] exceptionTypes) {
		this.exceptionTypes = exceptionTypes;
	}

	/**
	 * @param formater the ExceptionInfoFormater to set
	 */
	public void setFormater(ExceptionInfoFormater formater) {
		this.formater = formater;
	}

	/**
	 * @param configfile the configfile to set
	 */
	public void setConfigfile(String configfile) {
		this.configfile = configfile;
	}
}
