package com.adanac.framework.exception.handler.monitor;

import com.adanac.framework.exception.model.ExceptionInfo;

/**
 * 监听的异常信息
 */
public class MonitorExceptionInfo extends ExceptionInfo {
	private static final long serialVersionUID = -7757513644193423236L;
	private String systemId;
	private String appId;
	private String serverIp;
	private ExceptionInfo exceptionInfo;

	public MonitorExceptionInfo(ExceptionInfo exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	/**
	 * 构造方法
	 * @param exceptionInfo
	 * @param systemId
	 * @param appId
	 * @param serverIp
	 */
	public MonitorExceptionInfo(ExceptionInfo exceptionInfo, String systemId, String appId, String serverIp) {
		this.exceptionInfo = exceptionInfo;
		this.systemId = systemId;
		this.appId = appId;
		this.serverIp = serverIp;
	}

	/**
	 * return Code
	 */
	public String getCode() {
		return exceptionInfo.getCode();
	}

	/**
	 * return InterceptTime
	 */
	public String getInterceptTime() {
		return exceptionInfo.getInterceptTime();
	}

	/**
	 * return ExceptionClass
	 */
	public String getExceptionClass() {
		return exceptionInfo.getExceptionClass();
	}

	/**
	 * return ExceptionMessage
	 */
	public String getExceptionMessage() {
		return exceptionInfo.getExceptionMessage();
	}

	/**
	 * return SystemId
	 */
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
}
