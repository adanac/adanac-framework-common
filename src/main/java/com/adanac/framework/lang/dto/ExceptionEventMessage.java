package com.adanac.framework.lang.dto;

import java.util.HashMap;
import java.util.Map;

import com.adanac.framework.lang.exception.BaseException;
import com.adanac.framework.lang.util.EventUtils;

/**
 * 异常事件
 * 该事件当应用程序发生异常时产生，用于记录异常相关信息
 * @author adanac
 * @version 1.0
 */
public class ExceptionEventMessage extends BaseEventMessage {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1796958657252136561L;

	/**
	 * 异常事件的具体信息
	 */
	private String exceptionMessage;

	/**
	 * 异常名称
	 */
	private String exceptionName;

	/**
	 * 异常堆栈
	 */
	private String exceptionStackTrace;

	/**
	 * 异常编码
	 */
	private String errorCode = "";

	/**
	 * 由业务系统定义与传入，记录异常所需的业务信息
	 */
	private Map<String, Object> bizzContext = new HashMap<String, Object>();

	public ExceptionEventMessage(String funName) {
		super(EventType.EXCEPTION, funName);
	}

	public ExceptionEventMessage(String funName, Throwable t) {
		super(EventType.EXCEPTION, funName);
		this.exceptionMessage = t.getMessage();
		this.exceptionName = t.getClass().getName();
		this.exceptionStackTrace = EventUtils.getExceptionStackTrace(t);
		if (t instanceof BaseException) {
			BaseException baseException = (BaseException) t;
			if (baseException.getCode() != null) {
				this.setErrorCode(baseException.getCode());
			}
		}
	}

	public void generateMsgFromException(Throwable t) {
		this.setExceptionMessage(t.getMessage());
		this.setExceptionName(t.getClass().getName());
		this.setExceptionStackTrace(EventUtils.getExceptionStackTrace(t));
		if (t instanceof BaseException) {
			BaseException baseException = (BaseException) t;
			if (baseException.getCode() != null) {
				this.setErrorCode(baseException.getCode());
			}
		}
	}

	/**
	 * @return the execptionMessage
	 */
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	/**
	 * @param execptionMessage the execptionMessage to set
	 */
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	/**
	 * @return the execptionName
	 */
	public String getExceptionName() {
		return exceptionName;
	}

	/**
	 * @param execptionName the execptionName to set
	 */
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}

	/**
	 * @return the execptionStackTrace
	 */
	public String getExceptionStackTrace() {
		return exceptionStackTrace;
	}

	/**
	 * @param execptionStackTrace the execptionStackTrace to set
	 */
	public void setExceptionStackTrace(String exceptionStackTrace) {
		this.exceptionStackTrace = exceptionStackTrace;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the bizzContext
	 */
	public Map<String, Object> getBizzContext() {
		return bizzContext;
	}

	/**
	 * @param bizzContext the bizzContext to set
	 */
	public void setBizzContext(Map<String, Object> bizzContext) {
		if (this.bizzContext == null) {
			this.bizzContext = bizzContext;
		} else {
			this.bizzContext.putAll(bizzContext);
		}
	}
}
