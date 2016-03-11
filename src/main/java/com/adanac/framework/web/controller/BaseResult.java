package com.adanac.framework.web.controller;

import java.io.Serializable;

/**
 * 功能描述： 返回对象的基础类
 * @author adanac
 * @version 1.0
 */
public class BaseResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6214370880677930583L;

	private String status = "";

	private String errorCode = "";
	private String errorMsg = "";
	public static final String WARN = "warn";
	public static final String SUCCESS = "1";
	public static final String ERROR = "0";
	private Object content;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
