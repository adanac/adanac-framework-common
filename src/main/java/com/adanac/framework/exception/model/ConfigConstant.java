package com.adanac.framework.exception.model;

/**
 * 框架用得到的一些常量
 * @author adanac
 * @version 1.0
 */
public interface ConfigConstant {
	/**
	 * 异常处理配置对应的Spring bean 的名字，
	 */
	public static final String EXCEPTION_HANDLE_SETTING_BEAN_NAME = "exceptionHandleSetting";
	/**
	 * JmsTemplate对应的Spring bean 的名字，
	 */
	public static final String EXCEPTION_HANDLE_JMSTEMPLATE_BEAN_NAME = "exJmsTemplate";
	/**
	 * 换行符
	 */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * ErrorCode分隔符
	 */
	public static final String SPLIT_SEPARATOR = "||";

	public static final String LOGGER_MESSAGE_PREFIX = "@SNF-LOG-PREFIX@";

	public static final String LOGGER_MESSAGE_END = "@SNG-LANG-END@";
}
