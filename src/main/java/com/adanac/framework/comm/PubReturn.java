package com.adanac.framework.comm;

import java.io.Serializable;

/**
 * 公共消息
 * @author adanac
 * @version 1.0
 */
public class PubReturn<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3364469331168115461L;

	/**
	 * 结果 0成功 1失败
	 */
	private String resultCode;

	/**
	 * 失败信息
	 */
	private String resultMessage;

	/**
	 * 数据
	 */
	private T data;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
