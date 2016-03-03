package com.adanac.framework.exception.model;

import java.io.Serializable;

/**
 * Exception information object. 
 * @author adanac
 * @version 1.0
 */
public class ExceptionInfo implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = -7375024119138901922L;

	private String code; // 异常code

	private String interceptTime; // 异常被拦截的时间

	private String interceptedClass; // 拦截现场类，在此类上拦截到异常
	private String interceptedMethod; // 拦截现场方法，在此方法上拦截到异常
	private String parameterValue; // 方法的参数值

	private String exceptionClass; // 拦截到的异常类名
	private String exceptionMessage; // 拦截到的异常message

	private String rootException; // 根异常类名
	private String rootExceptionMsg; // 根异常消息
	private String rootCauseSpotClass; // throw根异常的类
	private String rootCauseSpotMethod; // throw根异常的方法
	private String rootCauseSpotLine; // throw根异常的行号

	private String fullStackTrace; // 完整的stack trace信息

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInterceptTime() {
		return interceptTime;
	}

	public void setInterceptTime(String interceptTime) {
		this.interceptTime = interceptTime;
	}

	public String getInterceptedClass() {
		return interceptedClass;
	}

	public void setInterceptedClass(String interceptedClass) {
		this.interceptedClass = interceptedClass;
	}

	public String getInterceptedMethod() {
		return interceptedMethod;
	}

	public void setInterceptedMethod(String interceptedMethod) {
		this.interceptedMethod = interceptedMethod;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public String getExceptionClass() {
		return exceptionClass;
	}

	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getRootException() {
		return rootException;
	}

	public void setRootException(String rootException) {
		this.rootException = rootException;
	}

	public String getRootExceptionMsg() {
		return rootExceptionMsg;
	}

	public void setRootExceptionMsg(String rootExceptionMsg) {
		this.rootExceptionMsg = rootExceptionMsg;
	}

	public String getRootCauseSpotClass() {
		return rootCauseSpotClass;
	}

	public void setRootCauseSpotClass(String rootCauseSpotClass) {
		this.rootCauseSpotClass = rootCauseSpotClass;
	}

	public String getRootCauseSpotMethod() {
		return rootCauseSpotMethod;
	}

	public void setRootCauseSpotMethod(String rootCauseSpotMethod) {
		this.rootCauseSpotMethod = rootCauseSpotMethod;
	}

	public String getRootCauseSpotLine() {
		return rootCauseSpotLine;
	}

	public void setRootCauseSpotLine(String rootCauseSpotLine) {
		this.rootCauseSpotLine = rootCauseSpotLine;
	}

	public String getFullStackTrace() {
		return fullStackTrace;
	}

	public void setFullStackTrace(String fullStackTrace) {
		this.fullStackTrace = fullStackTrace;
	}
}
