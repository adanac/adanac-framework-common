package com.adanac.framework.web.controller;

import java.io.Serializable;

public class BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4649148150573785167L;

	private String status = "0";

	private String errorCode = "";
	private String errorMsg = "";
	public static final String WARN = "warn";
	public static final String SUCCESS = "1";
	public static final String ERROR = "0";

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

}
