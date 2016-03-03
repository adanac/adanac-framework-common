package com.adanac.framework.exception.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常的输出日志
 * @author adanac
 * @version 1.0
 */
public class ExceptionLogger {
	private static final Logger logger = LoggerFactory.getLogger("com_adanac_exception_log");

	public static void log(String logStr) {
		/**
		 * 所有异常的输出级别为error
		 */
		logger.error(logStr);
	}
}
