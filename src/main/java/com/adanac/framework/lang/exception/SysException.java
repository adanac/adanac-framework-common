package com.adanac.framework.lang.exception;

public class SysException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3151004662252940233L;

	/**
	 * 异常信息为空的SysException构造方法
	 */
	public SysException() {
		super();
	}

	/**
	 * 用指定的cause异常构造一个新的SysException
	 * 
	 * @param cause the exception cause
	 */
	public SysException(Throwable cause) {
		super(cause);
	}

	/**
	 * 用指定的异常日志 message构造一个SysException
	 * 
	 * @param logMsg the detail message
	 */
	public SysException(String logMsg) {
		super(logMsg);
	}

	/**
	 * 用指定code和cause异常构造一个SysException
	 * 
	 * @param code the exception code
	 * @param cause the exception cause
	 */
	public SysException(String code, Throwable cause) {
		super(code, cause);
	}

	/**
	 * 
	 * 用指定的code和cause构造一个SysException,并指定code对应message的参数值
	 * 
	 * @param code the exception code
	 * @param cause the root cause
	 * @param messageArgs the argument array of the message corresponding to code
	 */
	public SysException(String code, Throwable cause, Object[] messageArgs) {
		super(code, cause, messageArgs);
	}

	/**
	 * 用指定的异常code和异常日志 message以及异常cause构造一个SysException, 并传递code对应message的参数值
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message
	 * @param cause the root cause
	 * @param messageArgs the argument array of the message corresponding to code
	 */
	public SysException(String code, String logMsg, Throwable cause, Object[] messageArgs) {
		super(code, logMsg, cause, messageArgs);
	}

	/**
	 * 用指定的code和异常日志message构造一个SysException,并指定缺省的页面友好message
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message
	 * 
	 */
	public SysException(String code, String logMsg) {
		super(code, logMsg);
	}

	/**
	 * 用指定异常code和异常日志message构造一个SysException,传递code对应message的参数值, 并指定缺省的页面友好message
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message
	 * @param messageArgs the argument array of the message corresponding to code
	 * 
	 */
	public SysException(String code, String logMsg, Object[] messageArgs) {
		super(code, logMsg, messageArgs);
	}

	/**
	 * 用指定的code和异常日志message以及异常cause构造一个SysException, 并指定缺省的页面友好message
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message
	 * @param cause the root cause
	 * 
	 */
	public SysException(String code, String logMsg, Throwable cause) {
		super(code, logMsg, cause);
	}

	/**
	 * 用指定的code和异常cause以及缺省的页面友好message构造一个SysException
	 * 
	 * @param code the exception code
	 * @param cause the root cause
	 * @param defaultFriendlyMessage the default friendly message if the friendly message corresponding to code is not
	 *            exist.
	 */
	public SysException(String code, Throwable cause, String defaultFriendlyMessage) {
		super(code, cause, defaultFriendlyMessage);
	}

	/**
	 * 用指定的code和异常cause构造一个SysException,传递code对应message的参数值， 并指定缺省的页面友好message
	 * 
	 * @param code the exception code
	 * @param cause the root cause
	 * @param messageArgs the argument array of the message corresponding to code
	 * @param defaultFriendlyMessage the default friendly message if the friendly message corresponding to code is not
	 *            exist.
	 * 
	 */
	public SysException(String code, Throwable cause, Object[] messageArgs, String defaultFriendlyMessage) {
		super(code, cause, messageArgs, defaultFriendlyMessage);
	}

	/**
	 * 用指定的code和异常日志message构造一个SysException,并指定缺省的页面友好message
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message
	 * @param defaultFriendlyMessage the default friendly message if the friendly message corresponding to code is not
	 *            exist.
	 * 
	 */
	public SysException(String code, String logMsg, String defaultFriendlyMessage) {
		super(code, logMsg, defaultFriendlyMessage);
	}

	/**
	 * 用指定异常code和异常日志message构造一个SysException,传递code对应message的参数值, 并指定缺省的页面友好message
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message
	 * @param messageArgs the argument array of the message corresponding to code
	 * @param defaultFriendlyMessage the default friendly message if the friendly message corresponding to code is not
	 *            exist.
	 * 
	 */
	public SysException(String code, String logMsg, Object[] messageArgs, String defaultFriendlyMessage) {
		super(code, logMsg, messageArgs, defaultFriendlyMessage);
	}

	/**
	 * 用指定的code和异常日志message以及异常cause构造一个SysException, 并指定缺省的页面友好message
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message
	 * @param cause the root cause
	 * @param defaultFriendlyMessage the default friendly message if the friendly message corresponding to code is not
	 *            exist.
	 * 
	 */
	public SysException(String code, String logMsg, Throwable cause, String defaultFriendlyMessage) {
		super(code, logMsg, cause, defaultFriendlyMessage);
	}

	/**
	 * 用指定异常code和异常日志message以及异常cause构造一个SysException,传递code对应message的参数值, 并指定缺省的页面友好message
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message
	 * @param cause the root cause
	 * @param messageArgs the argument array of the message corresponding to code
	 * @param defaultFriendlyMessage the default friendly message if the friendly message corresponding to code is not
	 *            exist.
	 * 
	 */
	public SysException(String code, String logMsg, Throwable cause, Object[] messageArgs,
			String defaultFriendlyMessage) {
		super(code, logMsg, cause, messageArgs, defaultFriendlyMessage);
	}
}
