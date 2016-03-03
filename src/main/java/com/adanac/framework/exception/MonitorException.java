package com.adanac.framework.exception;

/**
 * 功能描述： 基本异常类
 * @author adanac
 * @version 1.0
 */
public class MonitorException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5004062064886288402L;

	public MonitorException(String code, Throwable cause) {
		super(code, cause);
	}

	public MonitorException(String code, Throwable cause, Object[] messageArgs) {
		super(code, cause, messageArgs);
	}

	public MonitorException(String code, String logMsg) {
		super(code, logMsg);
	}

	public MonitorException(String code, String logMsg, Object[] messageArgs) {
		super(code, logMsg, messageArgs);
	}

	public MonitorException(String code, String logMsg, Throwable cause) {
		super(code, logMsg, cause);
	}

	public MonitorException(String code, String logMsg, Throwable cause, Object[] messageArgs) {
		super(code, logMsg, cause, messageArgs);
	}

	public MonitorException(String code, Throwable cause, String defaultFriendlyMessage) {
		super(code, cause, defaultFriendlyMessage);
	}

	public MonitorException(String code, Throwable cause, Object[] messageArgs, String defaultFriendlyMessage) {
		super(code, cause, messageArgs, defaultFriendlyMessage);
	}

	public MonitorException(String code, String logMsg, String defaultFriendlyMessage) {
		super(code, logMsg, defaultFriendlyMessage);
	}

	public MonitorException(String code, String logMsg, Object[] messageArgs, String defaultFriendlyMessage) {
		super(code, logMsg, messageArgs, defaultFriendlyMessage);
	}

	public MonitorException(String code, String logMsg, Throwable cause, String defaultFriendlyMessage) {
		super(code, logMsg, cause, defaultFriendlyMessage);
	}

	public MonitorException(String code, String logMsg, Throwable cause, Object[] messageArgs,
			String defaultFriendlyMessage) {
		super(code, logMsg, cause, messageArgs, defaultFriendlyMessage);
	}

}
